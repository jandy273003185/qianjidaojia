// pages/myAddress/myAddress.js
import{
  wxCommon,http,tips
}from'../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    warpHeight: "", //初始高度置空
    listArr:[
      // { name: '何老板', mobile: 18888888888, address:'广东省 深圳市 南山区 桃源街道 平山村30栋301（泊寓大学城社区）'},
      // { name: '乔碧螺', mobile: 15300001111, address: '广东省 深圳市 南山区 桃源街道 平山村30栋301（泊寓大学城社区）' }
    ],
    page: 1,//页码
    pageSize: 20,
    count: 0,//总记录数
    loadMoreFlag: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {console.log(options)
    this.data.pageName=options.pageName;
    this.data.goodsid = options.goodsid;
    this.data.allprices = options.allprices;
    this.data.allnum = options.allnum;
    this.data.nowBuy = options.nowBuy||'';

    this.queryList();
    wxCommon.listApi.getSystemInfo( (res)=> {
      const height = res.platform == 'android' ? 560 : 280;
      const warpHeight = (res.windowHeight - Math.ceil(height / res.pixelRatio) || 560) + "px";
      this.setData({
        warpHeight: warpHeight
      })
    },  (er)=> {
      this.setData({
        warpHeight: "560px"
      })
    }); 
  },
  queryList(){
    let params = {
      url: 'api/Address/AddressList',
      data:{
        page:this.data.page,
        pageSize:20
      }
    }
    http(params).then(res => {
      console.log(res);
      if(res.code == 0){
        let arr = this.data.listArr;
        if (!this.data.loadMoreFlag) { arr = []; this.setData({ loadMoreFlag: false }); }
        arr = arr.concat(res.data);
        this.setData({
          listArr: arr,
          count: res.count
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  loadMore() {//分页
    if (this.data.listArr.length >= this.data.count) { return };
    let page = this.data.page;
    ++page;
    this.setData({ loadMoreFlag: true, page });
    this.queryList();
  },
  add(){
    wx.navigateTo({
      url: '/pages/address/address?pageName=' + this.data.pageName + '&goodsid=' + this.data.goodsid + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum + '&nowBuy=' + this.data.nowBuy
    })
  },
  edit(e){
    wx.navigateTo({
      url: '/pages/address/address?id=' + e.currentTarget.dataset.id + '&pageName=' + this.data.pageName + '&goodsid=' + this.data.goodsid + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum + '&nowBuy=' + this.data.nowBuy
    })
  },
  selectAddress(e){
    if(this.data.pageName == 'submitOrder'){
      wx.navigateTo({
        url: '/pages/submitOrder/submitOrder?addressId=' + e.currentTarget.dataset.id + '&goodsid=' + this.data.goodsid + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum + '&nowBuy=' + this.data.nowBuy
      })
    }
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