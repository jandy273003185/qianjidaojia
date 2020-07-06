// pages/logistics/logistics.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    logisticsList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.ordernum = options.ordernum;
    this.getGoods();
    this.getExpressage();
  },
  getGoods() {
    let params = {
      url: 'api/Order/OrderDetails',
      data: { OrderNo: this.data.ordernum }
    };
    // console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({ 
          ShopName: res.data.ShopName,
          StatusName: res.data.StatusName,
          ProductImg: res.data.orderDetails[0].ProductImg,
          ProductName: res.data.orderDetails[0].ProductName,
          UnitPrice: res.data.orderDetails[0].UnitPrice,
          ProductCount: res.data.orderDetails[0].ProductCount,
          Address: res.data.Address
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getExpressage() {//快递信息
    let params = {
      url: 'api/Order/GetLogistics',
      data: { OrderNo: this.data.ordernum, rId: 0 }
    };
    // console.log(params)
    http(params).then(res => {
      const data = JSON.parse(res.data);
      console.log(data);
      if (res.code == 0) {
        this.setData({
          companyName: data.companyName,
          kuaidiNo: data.kuaidiNo,
          logisticsList:data.data
        })
      } else {
        tips(res.msg);
      }
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