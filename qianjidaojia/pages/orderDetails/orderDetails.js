// pages/orderDetails/orderDetails.js
import {
  wxCommon,
  _mobile,
  http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    OrderNo:'',
    qrcodeHide: 'qrcodeHide',
    odId:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.setData({ OrderNo: options.OrderNo, odId: options.odId});
    this.queryList();
  },
  queryList() {
    let params = {
      url: 'api/Order/OrderDetails',
      data: {
        OrderNo: this.data.OrderNo, //订单编号
        odId: '0'//this.data.odId //订单详情id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          "ContactName": res.data.ContactName, //收货人
          "TelephoneNumber": res.data.TelephoneNumber, //电话
          "Address": res.data.Address, //收货地址
          "StatusName": res.data.StatusName,
          orderDetails: res.data.orderDetails,
          TotalPrice: res.data.TotalPrice,
          TotalAmount: res.data.TotalAmount,
          ExpressPrice: res.data.ExpressPrice,
          DiscountedAmount: res.data.DiscountedAmount,
          AddTime: res.data.AddTime,
          PayTime: res.data.PayTime,
          // pay: (res.data.TotalPrice - res.data.DiscountedAmount).toFixed(2)
        });
      } else {
        tips(res.msg);
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
  afterSale() {
    this.setData({ qrcodeHide: '' });
  },
  qrcode() {
    this.setData({ qrcodeHide: 'qrcodeHide' });
  },

  refund(e) {//退款
    wx.navigateTo({
      url: '/pages/refund/refund?ordernum=' + this.data.OrderNo + '&id=' + e.currentTarget.dataset.id,
    })
  },
  cancelRefund(e){//取消申请退款
    let params = {
      url: 'api/Order/CanelRefund',
      data: {
        OrderNo: this.data.OrderNo, //订单编号
        OrderItemId: e.currentTarget.dataset.id //订单详情id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      tips(res.msg);
      wx.navigateTo({
        url: '/pages/myOrder/myOrder',
      })
    }, rejected => {
      tips('服务器异常,请稍后重试！')
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