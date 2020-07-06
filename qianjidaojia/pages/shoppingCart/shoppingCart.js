// pages/shoppingCart/shoppingCart.js
import {
  wxCommon, http,tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allselected: false,
    allnum: 0,
    allprices: 0,
    cartsdata: [] ,
    hide:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // this.getCartList();
  },
  getCartList(){
    let params = {
      url: 'api/Cart/CartList',
      data: {
        // page: 1,
        // pageSize: 10
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        const list = res.data;
        let map = {},dest = [];
        for (let i = 0; i < res.data.length; i++) {
          let ai = res.data[i];
          if (!map[ai.ShopId]) {
            dest.push({
              id: ai.ShopId,
              ShopName: ai.ShopName,
              goodsinfo: [ai],
              selected: false
            });
            map[ai.ShopId] = ai;
          } else {
            for (let j = 0; j < dest.length; j++) {
              let dj = dest[j];
              if (dj.id == ai.ShopId) {
                dj.goodsinfo.push(ai);
                break;
              }
            }
          }
        }
        // console.log(dest);
        this.setData({
          cartsdata: dest,
          count: res.count,
          allprices:0,
          allnum:0
        });
        if (this.data.cartsdata) { this.tapallallprices(); }
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // this.getTotalPrice();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getCartList();
  },

  //计算总价格  所有选中商品的 （价格*数量）相加  
  getallprices: function () {
    let cartsdata = this.data.cartsdata
    //购物车数据    
    let allprices = 0
    let allnum = 0
    for (let i = 0; i < cartsdata.length; i++) {     
      let goodsinfo = cartsdata[i].goodsinfo
      for (let a = 0; a < goodsinfo.length; a++) {
        // console.log(goodsinfo.length)
        if (goodsinfo[a].selected) {      //当前商品价格*数量 +          
          let price = Number(goodsinfo[a].SalePrice)
          let num = parseInt(goodsinfo[a].Number) //防止num为字符 *1或parseInt Number          
          allprices += price * num
          allnum += num
        }
      }
    }
    //跟新已选数量    
    this.setData({
      allnum: allnum,
      allprices: allprices.toFixed(2)
    })
  },

  //全选条件 条件->商铺全选择全选 反之  all
  allallprices: function () {
    let cartsdata = this.data.cartsdata
    let storenum = cartsdata.length;
    let allselected = this.data.allselected
    let allselectednum = 0;
    for (let i = 0; i < cartsdata.length; i++) {
      if (cartsdata[i].selected == true) {
        allselectednum++
      }
    }
    if (storenum == allselectednum) {
      allselected = true
    } else {
      allselected = false
    }
    this.setData({ allselected: allselected })
    this.getallprices();
  },
  //全选按钮点击 
  tapallallprices: function () {
    let allselected = this.data.allselected
    let cartsdata = this.data.cartsdata //购物车数据   
    if (allselected) {
      allselected = false
    } else {
      allselected = true
    }
    //选择  

    for (let i = 0; i < cartsdata.length; i++) {
      cartsdata[i].selected = allselected
      let goodsinfo = cartsdata[i].goodsinfo
      for (let a = 0; a < goodsinfo.length; a++) {
        goodsinfo[a].selected = allselected
      }
    }
    this.setData({
      cartsdata: cartsdata, //店铺下商品的数量     
      allselected: allselected
    })
    this.getallprices();
  },

  // 店铺的选中 
  storeselected: function (e) {
    let cartsdata = this.data.cartsdata //购物车数据  
    let index = e.currentTarget.dataset.index //当前店铺下标
    let thisstoredata = cartsdata[index].goodsinfo //当前店铺商品数据    
    //改变当前店铺状态    
    if (cartsdata[index].selected) {
      cartsdata[index].selected = false    //改变当前店铺所有商品状态      
      for (let i in thisstoredata) { cartsdata[index].goodsinfo[i].selected = false }
    } else {
      cartsdata[index].selected = true
      //改变当前店铺所有商品状态      
      for (let i in thisstoredata) {
        cartsdata[index].goodsinfo[i].selected = true
      }
    }
    this.setData({
      cartsdata: cartsdata //店铺下商品的数量   
    })
    this.getallprices();
    this.allallprices();
  },

  // 商品的选中  
  goodsselected: function (e) {
    let cartsdata = this.data.cartsdata //购物车数据   
    let index = e.currentTarget.dataset.index //当前商品所在店铺中的下标
    let idx = e.currentTarget.dataset.selectIndex //当前店铺下标   
    let cai = cartsdata[idx].goodsinfo; //当前商品的店铺data.goodsinfo    
    let curt = cai[index]; //当前商品数组    
    if (curt.selected) {
      cartsdata[idx].goodsinfo[index].selected = false //点击后当前店铺下当前商品的状态     
      cartsdata[idx].selected = false
    } else {
      cartsdata[idx].goodsinfo[index].selected = true //点击后当前店铺下当前商品的状态     

      //当店铺选中商品数量与店铺总数量相等时 改变店铺状态    

      let storegoodsleg = cartsdata[idx].goodsinfo.length
      let goodsinfo = cartsdata[idx].goodsinfo
      let selectedleg = 0
      for (let i in goodsinfo) {
        if (goodsinfo[i].selected == true) {
          selectedleg++
        }
      }
      if (storegoodsleg == selectedleg) {
        cartsdata[idx].selected = true
      }
    }   // 更新   
    this.setData({
      cartsdata: cartsdata //店铺下商品的数量   
    })
    this.getallprices();
    this.allallprices();
  },
  // 点击+号，num加1，点击-号，如果num > 1，则减1  
  addCount: function (e) {
    let cartsdata = this.data.cartsdata //购物车数据   
    let index = e.currentTarget.dataset.index //当前商品所在店铺中的下标   
    let idx = e.currentTarget.dataset.selectIndex //当前店铺下标    
    let cai = cartsdata[idx].goodsinfo; //当前商品的店铺data.goodsinfo   
    let curt = cai[index]; //当前商品数组    
    let num = curt.Number; //当前商品的数量    
     
    let params = {
      url: 'api/Cart/AddCart',
      data: {
        ProId: e.currentTarget.dataset.productid, //商品id
        Count: 1, //添加数量
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {
        tips('库存不足！');
      }else if(res.code == 0){
        num++;
        cartsdata[idx].goodsinfo[index].Number = num //点击后当前店铺下当前商品的数量  
        this.setData({
          cartsdata: cartsdata //店铺下商品的数量  
        })
        this.selectComponent("#tabbar").querycartNum();
        //计算总价格    
        this.getallprices();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },

  minusCount: function (e) {
    let cartsdata = this.data.cartsdata //购物车数据   
    let index = e.currentTarget.dataset.index //当前商品所在店铺中的下标 
    let idx = e.currentTarget.dataset.selectIndex //当前店铺下标   
    let cai = cartsdata[idx].goodsinfo; //当前商品的店铺data.goodsinfo   
    let curt = cai[index]; //当前商品数组    
    let num = curt.Number; //当前商品的数量    
    if (num <= 1) {
      return false;
    }
    num--;
    cartsdata[idx].goodsinfo[index].Number = num //点击后当前店铺下当前商品的数量   
    let params = {
      url: 'api/Cart/EditCartXQ',
      data: {
        CartId: e.currentTarget.dataset.id, //商品id
        Count: num, //添加数量
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {
        
      } else if (res.code == 0) {
        this.setData({
          cartsdata: cartsdata //店铺下商品的数量  
        })
        this.selectComponent("#tabbar").querycartNum();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
    this.getallprices();
  },
  del(e) {
    let params = {
      url: 'api/Cart/DelCartXQ',
      data: {
        CartIdList: e.currentTarget.dataset.id, //商品id
      }
    };console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code != 0) {
        tips('服务器异常,请稍后重试！')
      }else{
        tips('删除成功');
        this.getCartList();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  delAll() {
    console.log(this.data.cartsdata);
    let arr = [];
    for (let i = 0; i < this.data.cartsdata.length; i++) {
      if (this.data.cartsdata[i].selected) {
        for (let j = 0; j < this.data.cartsdata[i].goodsinfo.length; j++)
          if (this.data.cartsdata[i].goodsinfo[j].selected) {
            arr.push(this.data.cartsdata[i].goodsinfo[j].Id);
          }
      }
    }
    let params = {
      url: 'api/Cart/DelCartXQ',
      data: {
        CartIdList: arr.toString(), //商品id
      }
    }; console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {
        tips('请选择删除的商品！')
      } else {
        tips('删除成功');
        this.getCartList();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  settlement(){//结算
    let arr = []; 
    for (let i = 0; i < this.data.cartsdata.length; i++) {
      for (let j = 0; j < this.data.cartsdata[i].goodsinfo.length; j++){
        if (this.data.cartsdata[i].goodsinfo[j].selected) {
          arr.push(this.data.cartsdata[i].goodsinfo[j].Id);
        }
      }
    }
    if(arr.length <1){tips('请选择商品！'); return;}
    wx.navigateTo({
      url: '/pages/submitOrder/submitOrder?id=' + arr.toString() + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum,
    })
  },
  goBuy(){
    wx.switchTab({
      url: '/pages/classify/classify',
    })
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