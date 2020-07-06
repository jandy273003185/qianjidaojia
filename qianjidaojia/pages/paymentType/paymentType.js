// pages/paymentType/paymentType.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ordernum:'',
    TotalPrice: 0,  //产品金额
    expressprice: 0, //运费
    discountedAmount: 0, //减免金额
    walletPayAmount: 0 ,//钱包支付金额
    noPayment:0,
    IsDefault:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.ordernum = options.ordernum;
    this.getBalance();
    this.getAccountData();
  },
  getBalance() {
    let params = {
      url: 'api/Order/GetOrdersAmount',
      data:{
        OrderNo: this.data.ordernum
      }
    }
    // console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          TotalPrice: res.data.TotalPrice,  //产品金额
          expressprice: res.data.expressprice, //运费
          discountedAmount: res.data.discountedAmount, //减免金额
          walletPayAmount: res.data.walletPayAmount, //钱包支付金额
          noPayment: res.data.TotalPrice - res.data.expressprice - res.data.discountedAmount
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getAccountData() {//账户余额
    let params = {
      url: 'api/User/GetMemberInfo'
    }
    http(params).then(res => {
      if (res.code == 0) {
        // console.log(res)
        this.setData({
          balance: res.data.Wallet
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  changeSwitch(e) {
    if (e.detail.value) {
      this.setData({
        IsDefault: 1,
        noPayment: 0,
        balance: this.data.balance - this.data.TotalPrice - this.data.expressprice - this.data.discountedAmount
      });
    } else {
      this.setData({ 
        IsDefault: 0,
        noPayment: this.data.TotalPrice - this.data.expressprice - this.data.discountedAmount,
        balance: this.data.balance +this.data.TotalPrice + this.data.expressprice + this.data.discountedAmount
      });
    }
  },
  toOrder() {//提交订单
    if(this.data.IsDefault){
      this.walletPayment();
    }else{
      let params = {
        url: 'api/Order/ConfirmWeiXinSmallPay',
        data: {
          "OrderNo": this.data.ordernum, //订单编号
          "WxOpenid": wx.getStorageSync('userStorage').openId //小程序code，当没有获取到openid时，需要使用code去获取openid
        }
      };console.log(params)
      http(params).then(res => {
        console.log(res);
        const that = this;
        if (res.code == 0) {
          const JsParam = JSON.parse(res.data.JsParam);
          wx.requestPayment({
            timeStamp: JsParam.timeStamp,
            nonceStr: JsParam.nonceStr,
            package: JsParam.package,
            signType: 'MD5',
            paySign: JsParam.paySign,
            success(res) {
              wx.navigateTo({ url: '/pages/myOrder/myOrder?Status=2' })
            },
            fail(res) { console.log(res);  }
          })
        } else {
          tips(res.msg);
        }
      }, rejected => {
        tips('服务器异常,请稍后重试！')
      })
    }
  },
  walletPayment(){//钱包支付
    
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