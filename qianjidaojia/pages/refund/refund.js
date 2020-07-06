// pages/refund/refund.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    reasonsArr: [],
    reasonsValue: '',
    reasonsTxt:'请选择',
    hideFlag: true,//true-隐藏  false-显示
    animationData: {},
    num:0,
    id:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.ordernum = options.ordernum;
    if (options.id){this.data.id = options.id};
    this.queryList();
  },
  queryList() {
    let params = {
      url: 'api/Order/OrderDetails',
      data: {
        OrderNo: this.data.ordernum, //订单编号
        odId: this.data.id || 0, //订单详情id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let actualPay = 0, saveMoney = 0,arr=[];
        for (let i = 0; i < res.data.orderDetails.length; i++) {
          if (res.data.orderDetails[i].OrderStatusId != 16 && res.data.orderDetails[i].OrderStatusId != 17){
            actualPay += res.data.orderDetails[i].actualPay;
            saveMoney += res.data.orderDetails[i].saveMoney;
            arr.push(res.data.orderDetails[i].Id);
          }
        }
        this.setData({
          actualPay: actualPay,
          saveMoney: saveMoney,
          id:arr.toString(),
          "ContactName": res.data.ContactName, //收货人
          "TelephoneNumber": res.data.TelephoneNumber, //电话
          "Address": res.data.Address, //收货地址
          "StatusName": res.data.StatusName,
          orderDetails: res.data.orderDetails,
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
  orderReasonsList(e) {//取消订单原因列表
    let params = {
      url: 'api/Order/CancelReason',
      method: 'GET'
    };
    // console.log(params)
    http(params).then(res => {
      //  console.log(res);
      this.setData({ reasonsArr: res.data })
      //  tips(res.msg);
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getOption(e) {// 点击选项
    this.setData({
      hideFlag: true,
      reasonsValue: e.currentTarget.dataset.value,
      reasonsTxt: e.currentTarget.dataset.txt
    })
  },
  inputDescription(e){
    var value = e.detail.value;
    var len = parseInt(value.length);
    this.setData({
      num:len,
      RefundContent:value
    })
  },
  submit(){
    // let url = 'api/Order/SubmitReturn';
    // if(this.data.id){
    let url = 'api/Order/SubmitReturnMultiple';
    // }
    let params = {
      url: url,
      data: {
        OrderNo:  this.data.ordernum,//订单号
        OrderItemIds: this.data.id,
        RefundContent: this.data.RefundContent, //退货原因详细描述
        RefundReasonId: this.data.reasonsValue //退货原因id
      }
    };
    // if (this.data.id) { params.data.OrderItemIds=this.data.id;};
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        wx.navigateTo({
          url: '/pages/myOrder/myOrder',
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },

  //取消
  mCancel: function () {
    this.hideModal();
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