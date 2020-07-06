// pages/address/address.js
import{
  mobile,tips,http
}from'../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    consignee:'',
    mobile:'',
    fullAddress: '',
    province_list: null,
    province_name: null,
    city_list: null,
    city_name: null,
    area_list: null,
    area_name: null,
    addressCity: null,
    multiArray: [],  // 三维数组数据
    multiIndex: [0, 0, 0], // 默认的下标,
    selectProvinceId: null,
    selectCityId: null,
    selectAreaId: null,
    IsDefault:0,
    remarkHidden:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {console.log(options)
    this.setData({ id: options.id, pageName: options.pageName});
    this.data.goodsid = options.goodsid;
    this.data.allprices = options.allprices;
    this.data.allnum = options.allnum;
    this.data.nowBuy = options.nowBuy || '';

    if (options.id) { this.query();};
    wx.setNavigationBarTitle({ title: this.data.id ? '编辑收货地址' :'添加收货地址' })
    this.getProvince();
  },
  query(){
    let params = {
      url: 'api/Address/GetInfo',
      data: {
        Id:this.data.id
      }
    }
    http(params).then(res => {
      console.log(res);
      this.setData({ 
        consignee:res.data.name,
        mobile:res.data.tel,
        selectProvinceId: res.data.province,
        selectCityId: res.data.city,
        selectAreaId: res.data.district,
        fullAddress: res.data.address,
        addressCity: [res.data.addressstr],
        IsDefault: res.data.is_def
      }); 
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getProvince() {//获取省份列表
    let params = {
      url: 'api/Address/GetSrea',
      data: { 'Types': 'Province' }
    }
   http(params).then(res => {console.log(res)
     let provinceList = [...res.data]  //放到一个数组里面
     let provinceArr = res.data.map((item) => { return item.name })  //获取名称
     this.setData({
       multiArray: [provinceArr, [], []], //更新三维数组  更新完为[['广东','北京'],[],[]]
       province_list: provinceList,  //省级原始数据
       province_name: provinceArr,  //省级全部名称
     })
     let defaultCode = this.data.province_list[0].id //使用第一项当作参数获取市级数据
     if (defaultCode) {
       this.setData({
         currnetProvinceId: defaultCode  //保存当前省份id
       })
       this.getCity(defaultCode) //获取市区数据
     }
   })
  },
  getCity(id) {//根据省份id获取城市
    this.setData({
      currnetProvinceId: id
    })
    let params = {
      url: 'api/Address/GetSrea',
      data: { 'Types': 'City', 'Code': id }
    }
    http(params).then(res => {
      let cityArr = res.data.map((item) => { return item.name }) //返回城市名称
      let cityList = [...res.data]
      this.setData({
        multiArray: [this.data.province_name, cityArr, []],  //更新后[['广东','北京'],['潮州'，'汕头','揭阳'],[]]
        city_list: cityList, //保持市级数据
        city_name: cityArr   //市级名称
      })
      let defaultCode = this.data.city_list[0].id //获取第一个市的区级数据
      if (defaultCode) {
        this.setData({
          currentCityId: defaultCode //保存当下市id
        })
        this.getArea(defaultCode) //获取区域数据
      }
    })
  },
  getArea(id) {//根据城市d获取区域
    this.setData({
      currentCityId: id
    })
    let params = {
      url: 'api/Address/GetSrea',
      data: { 'Types': 'District', 'Code': id }
    }
    http(params).then(res => {
      let areaList = [...res.data]
      let areaArr = res.data.map((item) => { return item.name }) //区域名
      this.setData({
        multiArray: [this.data.province_name, this.data.city_name, areaArr],
        area_list: areaList, //区列表
        area_name: areaArr   //区名字
      })
    })
  },
  //picker确认选择地区
  bindRegionChange: function (e) {
    // 因为在获取省中 北京只有一个选项，导致获取不了北京》北京》第一个
    if (e.detail.value[1] == null || e.detail.value[2] == null) { //如果只滚动了第一列则选取第一列的第一数据
      this.setData({
        multiIndex: e.detail.value,  //更新下标
        addressCity: [this.data.province_list[e.detail.value[0]].name, this.data.city_list[0].name, this.data.area_list[0].name],
        selectProvinceId: this.data.province_list[e.detail.value[0]].id,
        selectCityId: this.data.city_list[0].id,
        selectAreaId: this.data.area_list[0].id
      })
    } else {
      this.setData({
        multiIndex: e.detail.value,  //更新下标
        addressCity: [this.data.province_list[e.detail.value[0]].name, this.data.city_list[e.detail.value[1]].name, this.data.area_list[e.detail.value[2]].name],
        selectProvinceId: this.data.province_list[e.detail.value[0]].id,
        selectCityId: this.data.city_list[e.detail.value[1]].id,
        selectAreaId: this.data.area_list[e.detail.value[2]].id
      })
    }
    // console.log(this.data.selectProvinceId,this.data.selectCityId,this.data.selectAreaId)
  },
  //滑动地区组件
  bindRegionColumnChange: function (e) {
    // console.log(e.detail.column,e.detail.value);
    let that = this
    let column = e.detail.column  //当前改变的列
    let data = {
      multiIndex: JSON.parse(JSON.stringify(that.data.multiIndex)),
      multiArray: JSON.parse(JSON.stringify(that.data.multiArray))
    }
    data.multiIndex[column] = e.detail.value  //第几列改变了就是对应multiIndex的第几个，更新他
    switch (column) {
      case 0:  //第一列改变，省级改变
        let currentProvinceId = that.data.province_list[e.detail.value].id
        if (currentProvinceId != that.data.currentProvinceId) { //判断当前id是不是更新了
          that.getCity(currentProvinceId)   //获取当前id下的市级数据
        }
        data.multiIndex[1] = 0    //将市默认选择第一个
        break
      case 1:  //第二列改变，市级改变
        let currentCityId = that.data.city_list[e.detail.value].id
        if (currentCityId != that.data.currentCityId) {
          that.getArea(currentCityId) //获取区域
        }
        data.multiIndex[2] = 0  //区域默认第一个
        break
    }
    that.setData(data)  //更新数据
  },
  consigneeInput(e) { this.setData({ consignee:e.detail.value})},
  mobileInput(e) { this.setData({ mobile: e.detail.value }) },
  fullAddressInput(e) { this.setData({ fullAddress: e.detail.value }) },
  changeSwitch(e){
    if(e.detail.value){
      this.setData({ IsDefault:1});
    }else{
      this.setData({ IsDefault: 0 });
    }
  },

  finish(){
    const url = this.data.id ? 'api/Address/UpdateAddress' : 'api/Address/AddAddress';
    let params = {
      url: url,
      data: {
        Consignee: this.data.consignee,  //收货人
        Mobile: this.data.mobile,  //手机号
        // IsDefault: isDefault, //设置默认地址 1：默认 0：不默认
        ProvinceCode: this.data.selectProvinceId, //省代码
        CityCode: this.data.selectCityId,//市代码
        DistrictCode: this.data.selectAreaId, //区代码
        // StreetCode: Street, //街道代码
        FullAddress: this.data.fullAddress, //全地址，不带省市区
        IsDefault: this.data.IsDefault
      }
    }
    if (this.data.id){params.data.Id = this.data.id;};
    if (!this.data.consignee){tips('请填写联系人');return};
    if (!this.data.mobile) { tips('请填写手机号'); return};
    if (!this.data.fullAddress) { tips('请填写详细地址'); return };
    console.log(params);
    http(params).then(res => {
      console.log(res);
      if(res.code == 0){
        wx.navigateTo({
          url: '/pages/myAddress/myAddress?pageName=' + this.data.pageName + '&goodsid=' + this.data.goodsid + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum + '&nowBuy=' + this.data.nowBuy
        })
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  delAddress(){
    this.setData({ remarkHidden: false })
  },
  modalConfirm() {
    this.setData({ remarkHidden: true });
    let params = {
      url: 'api/Address/DeleteAddress',
      data: {
        Id: this.data.id,
      }
    }
    // console.log(params);
    http(params).then(res => {
      // console.log(res);
      if (res.code == 0) {
        wx.navigateTo({
          url: '/pages/myAddress/myAddress',
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  modalCancel() {
    this.setData({ remarkHidden: true })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  mobileBlur(e) { //验证手机号或邮箱
    let val = mobile(e.detail.value);
    if (!val) {
      tips('请正确输入手机号！');
      return;
    } else {
      this.setData({ mobile: e.detail.value });
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})