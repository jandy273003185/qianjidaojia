//index.js
import {
  tips,http
} from '../../utils/util.js';
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    Avatar:'',
    ID:'',
    balance: 0,
    coupon:0,
    integral:0,
    listArr:[],
    qrcodeHide:'qrcodeHide',
    /* 点击购物车 */
    isCart: '',
    animation: '',
    goodsName: '',
    stockNum: '',
    nowPrice: '',
    oldPrice: '',
    goodsImg: ''
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    
  },
  onShow:function(){
    this.getAccountData();
    this.getGoodsList();
    this.getOrderNum();
  },
  onUser(){
    wx.navigateTo({
      url: '/pages/myUserInfo/myUserInfo',
    })
  },
  onOrder(){
    wx.navigateTo({
      url: '/pages/myOrder/myOrder',
    })
  },
  getOrderNum(){//获取订单状态数量
    let params = {
      url: 'api/User/GetMemberOrder'
    }
    http(params).then(res => {
      if (res.code == 0) {
        console.log(res)
        this.setData({
          "paymentNum": res.data.PendingPayment, //待付款
          "sendGoodsNum": res.data.PendingShipment, //待发货
          "distributionNum": res.data.Shipped, //配送中
          "evaluateNum": res.data.Received, //待评价
          "afterSaleNum": res.data.ReturnsAfterSale //售后
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  orderStatus(e){
    wx.navigateTo({
      url: '/pages/myOrder/myOrder?Status='+e.currentTarget.dataset.status,
    })
  },
  afterSale(){
    this.setData({ qrcodeHide: '' });
  },
  qrcode(){
    this.setData({ qrcodeHide: 'qrcodeHide' });
  },
  getAccountData(){//账户信息
    let params = {
      url: 'api/User/GetMemberInfo'
    }
    http(params).then(res => {
      if (res.code == 0) {
        console.log(res)
        this.setData({ 
          Avatar: res.data.Avatar,
          nickName: res.data.NickName,
          ID:res.data.MemberID,
          balance: res.data.Wallet,
          coupon: res.data.CouponSum,
          integral: res.data.Score,
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getGoodsList(){
    let params = {
      url: 'api/Goods/GoodsList',
      data: {
        page: 1,
        pageSize:4
      }
    }
    http(params).then(res => {
      if (res.code == 0) {
        console.log(res)
        this.setData({
          listArr:res.data
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  copyText(e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text,
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            tips('复制成功')
          }
        })
      }
    })
  },
  goodsDetails(e){
    wx.navigateTo({
      url: '/pages/goodsDetails/goodsDetails?Id='+e.currentTarget.dataset.id,
    })
  },
  /* 加入购物车 */
  addCart(e) {
    console.log(e);
    this.setData({
      isCart: true,
      animation: 'riseToast',
      goodsName: e.currentTarget.dataset.proname,
      stockNum: e.currentTarget.dataset.prostock || 100000,
      nowPrice: e.currentTarget.dataset.proprice,
      oldPrice: e.currentTarget.dataset.prooldp,
      goodsImg: e.currentTarget.dataset.proimg,
      proId: e.currentTarget.dataset.proid,
    });
    this.selectComponent("#cart").findSpecification();
  },
  cartNum(){
    this.selectComponent("#tabbar").querycartNum();
  }
})

