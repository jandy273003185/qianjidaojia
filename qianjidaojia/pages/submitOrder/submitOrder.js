// pages/submitOrder/submitOrder.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsid:'',
    allprices:0,
    allnum:0,
    imgArr:[],
    addressinfo:'',
    name:'',
    tel:'',
    addressId:'',
    OrderRemarks:'',

    reasonsArr: [],
    reasonsValue: '',
    reasonsTxt: '不使用',
    hideFlag: true,//true-隐藏  false-显示
    animationData: {},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    this.data.goodsid = options.id||options.goodsid;
    this.data.addressId = options.addressId;
    this.data.goodsNum = options.goodsNum||0; 
    this.setData({ allnum: options.allnum || options.goodsNum, nowBuy : options.nowBuy});
    this.getAddress();
    // this.getCartList(); 
     
  },
  getAddress(){//地址
    let params = {
      url: this.data.addressId ? 'api/Address/GetInfo' : 'api/Address/defaultaddress_New',
      data:{
        Id:this.data.addressId||''
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({ addressinfo: res.data.addressinfo,name:res.data.name,tel:res.data.tel,addressId:res.data.id});
        if (this.data.nowBuy == 'nowBuy') { this.queryFreight(); } else {  this.getCartList();}
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getCartList() {
    let params = {
      url: 'api/Cart/GetConfirmOrderGoods',
      data: {
        CartIdList:this.data.goodsid
      }
    };console.log(params);
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let arr = [];
        for (let i = 0; i < res.data.length; i++) {
          if(i<3){
            arr.push(res.data[i].ProductImg);
          }else{
            break;
          }
        }
        this.setData({ imgArr: arr, ShopId: res.data[0].ShopId });
        this.getTotal();
      }else{
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getTotal() {
    let params = {
      url: 'api/Order/BuyCartShopMoney',
      data: {
        CartIds: this.data.goodsid,
        AddressId: this.data.addressId, //收货地址id
        ShopId: this.data.ShopId, //店铺id
        MemberCouponId: this.data.MemberCouponId||0, //优惠券id
        MemberCouponIds: this.data.MemberCouponId || '' //优惠券id
      }
    };console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          "OrderMoney": res.data.OrderMoney, //付款金额
          "OrderTotal": res.data.OrderTotal, //商品总价
          "AactualAmount": res.data.OrderMoney, //合计
          "AactualAmountOld": res.data.OrderMoney, //合计
          "DiscountedMoney": res.data.DiscountedMoney, //省多少钱
          "ShopFreight": res.data.ShopFreight //运费，0为包邮
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！') 
    })
  },
  
  address(){
    if (this.data.nowBuy == 'nowBuy'){
      wx.redirectTo({
        url: '/pages/myAddress/myAddress?pageName=submitOrder&nowBuy=nowBuy&goodsid=' + this.data.goodsid + '&allprices=' + this.data.OrderTotal + '&allnum=' + this.data.allnum,
      })
    }else{
      wx.redirectTo({
        url: '/pages/myAddress/myAddress?pageName=submitOrder&goodsid=' + this.data.goodsid + '&allprices=' + this.data.OrderTotal + '&allnum=' + this.data.allnum,
      })
    }
    
  },
  goods() {
    let productId = this.data.goodsid
    if (this.data.nowBuy == 'nowBuy') { productId = this.data.productId;return; } ;
    wx.navigateTo({
      url: '/pages/settlementList/settlementList?goodsid=' + productId,
    })
  },
  OrderRemarks(e){
    this.setData({ OrderRemarks:e.detail.value})
  },
  nowBuy() {//从商品详情页点击立即购买
    let params = {
      url: 'api/Goods/BuyNowInfo',
      data: {
        proId: this.data.goodsid
      }
    }; console.log(params);
    http(params).then(res => {
      console.log(res, this.data.goodsNum);
      if (res.code == 0) {
        let arr = [];
        arr.push(res.data.ProductImage);
        this.setData({
          imgArr: arr,
          ShopId: res.data.ShopId,
          "OrderMoney": res.data.Price * this.data.allnum, //付款金额
          "OrderTotal": res.data.Price * this.data.allnum, //商品总价
          // "AactualAmount": res.data.Price * this.data.goodsNum, //合计
          // "ShopFreight": res.data.Freight,//运费，0为包邮
          "AactualAmountOld": (Number(res.data.Price * this.data.allnum) + Number(this.data.ShopFreight)).toFixed(2), //合计
          "AactualAmount": (Number(res.data.Price * this.data.allnum) + Number(this.data.ShopFreight)).toFixed(2)
,
          productId: res.data.ProductId
        });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  cartBuyOrder(){//从购物车下单
    let params = {
      url: 'api/Order/BuyCart',
      data: {
        CartIds: this.data.goodsid,
        AddressId: this.data.addressId, //地址id
        // InvoiceId: invoiceId, //发票id
        // InvoiceType: invoiceType, //发票类型
        // InvoiceEmail: invoiceEmail,
        OrderRemarks: this.data.OrderRemarks,//”[{ ShopId: "10020", Text: "" }, { ShopId: "10021", Text: "" }]”, //商家留言
        MemberCouponId: this.data.MemberCouponId||0, //优惠券id
        MemberCouponIds: this.data.MemberCouponId||'', //优惠券id
        // IsPayWallet: isWallet, //是否钱包支付
        Sourcefrom:2,// 订单渠道 0: 钱记商城 1：小七商城 2：钱记小程序
      }
    };
    console.log(params);
    http(params).then(res => {
      console.log(res);    
      if(res.code == 0){  
        wx.navigateTo({
          url: '/pages/paymentType/paymentType?ordernum=' + res.data,
        })
      }else{
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  queryFreight() {//立即购买查询运费
    let params = {
      url: 'api/Order/BuyNowToFreight',
      data: {
        AddressId: this.data.addressId,//地址id
        Number: this.data.allnum, //数量
        PreferentialId: "0", //0
        ProId: this.data.goodsid ,//商品id
        SpecText: ""  //规格
      }
    };
    console.log(params);
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({ ShopFreight:res.data});
        this.nowBuy();
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  nowBuyOrder() {//立即购买下单
    let params = {
      url: 'api/Order/BuyNowSubmitOrder',
      data: {
        ProId: this.data.goodsid,
        AddressId: this.data.addressId, //地址id
        Remark: this.data.OrderRemarks,//”[{ ShopId: "10020", Text: "" }, { ShopId: "10021", Text: "" }]”, //商家留言
        Sourcefrom: 2,// 订单渠道 0: 钱记商城 1：小七商城 2：钱记小程序
        Number: this.data.allnum,//购买数量
        MemberCouponId: this.data.MemberCouponId || 0, //优惠券id
        MemberCouponIds: this.data.MemberCouponId || '', //优惠券id
      }
    };
    console.log(params);
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        wx.navigateTo({
          url: '/pages/paymentType/paymentType?ordernum=' + res.data,
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  toOrder() {//提交订单
    if (!this.data.addressId){tips('请选择收货地址!'); return;}
    if (this.data.nowBuy == 'nowBuy'){
      this.nowBuyOrder();
    }else{
      this.cartBuyOrder();
    }
  },


  orderReasonsList(e) {//优惠劵列表
    let params = {
      url: 'api/Order/GetCouponList',
      data: {
        // CartIds: this.data.goodsid,  //购物车id
        Type: this.data.nowBuy == 'nowBuy'?0:1 //0：立即购买 1：购物车 
      }
    }
    if (this.data.nowBuy == 'nowBuy'){
      params.data.ProductId = this.data.goodsid;
      params.data.ProductNumber = this.data.allnum;
      params.data.ProductSpec = '';
    }else{
      params.data.CartIds = this.data.goodsid;
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({ reasonsArr: res.data})
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    }) 
  },
  getOption(e) {//选中优惠劵
    this.setData({
      hideFlag: true,
      reasonsValue: e.currentTarget.dataset.value,
      reasonsTxt: e.currentTarget.dataset.txt,
      MemberCouponId:e.currentTarget.dataset.id,
      DiscountType: e.currentTarget.dataset.discounttype
    })
    if (this.data.nowBuy == 'nowBuy'){
      if (e.currentTarget.dataset.discounttype == 2) {
        let _AactualAmount = Number(e.currentTarget.dataset.denomination * this.data.OrderTotal);
        let _AactualAmount2=0;
        let AactualAmount =0;
        if (e.currentTarget.dataset.maxamount !=0 ){
           _AactualAmount2 = _AactualAmount > e.currentTarget.dataset.maxamount ? e.currentTarget.dataset.maxamount : _AactualAmount;
           AactualAmount = (this.data.OrderTotal - _AactualAmount2 + Number(this.data.ShopFreight)).toFixed(2);
        }else{
          _AactualAmount2 = _AactualAmount;
          AactualAmount = (_AactualAmount2 + Number(this.data.ShopFreight)).toFixed(2);
        }
        this.setData({
          AactualAmount: AactualAmount
        });
      } else {
        this.setData({
          AactualAmount: this.data.AactualAmountOld - e.currentTarget.dataset.denomination
        });
      }
    }else{
      this.getTotal();
    }
  },
  //取消
  mCancel: function () {
    this.setData({
      hideFlag: true,
      reasonsValue: '',
      reasonsTxt: '不使用',
      MemberCouponId: 0,
      AactualAmount: this.data.AactualAmountOld
    })
  },

  // ----------------------------------------------------------------------modal
  // 显示遮罩层
  showModal: function () {
    var that = this;
    that.setData({
      hideFlag: false
    })
    // 创建动画实例
    var animation = wx.createAnimation({
      duration: 400,//动画的持续时间
      timingFunction: 'ease',//动画的效果 默认值是linear->匀速，ease->动画以低速开始，然后加快，在结束前变慢
    })
    this.animation = animation; //将animation变量赋值给当前动画
    var time1 = setTimeout(function () {
      that.slideIn();//调用动画--滑入
      clearTimeout(time1);
      time1 = null;
    }, 100)
    this.orderReasonsList();
  },

  // 隐藏遮罩层
  hideModal: function () {
    var that = this;
    var animation = wx.createAnimation({
      duration: 400,//动画的持续时间 默认400ms
      timingFunction: 'ease',//动画的效果 默认值是linear
    })
    this.animation = animation
    that.slideDown();//调用动画--滑出
    var time1 = setTimeout(function () {
      that.setData({
        hideFlag: true
      })
      clearTimeout(time1);
      time1 = null;
    }, 220)//先执行下滑动画，再隐藏模块

  },
  //动画 -- 滑入
  slideIn: function () {
    this.animation.translateY(0).step() // 在y轴偏移，然后用step()完成一个动画
    this.setData({
      //动画实例的export方法导出动画数据传递给组件的animation属性
      animationData: this.animation.export()
    })
  },
  //动画 -- 滑出
  slideDown: function () {
    this.animation.translateY(300).step()
    this.setData({
      animationData: this.animation.export(),
    })
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