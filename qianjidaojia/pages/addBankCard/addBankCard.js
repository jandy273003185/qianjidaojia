// pages/addBankCard/addBankCard.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bankArr: [],
    bankValue: '',
    BankCardName: '请选择',
    hideFlag: true,//true-隐藏  false-显示
    animationData: {},

    buttonDisable: false,//倒计时是否不可用 
    buttonTitle: '获取验证码',//按钮标题
    code: ''//验证码
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAccountData();
  },
  bankList(e) {//银行卡列表
    let params = {
      url: '/api/Bank/BankinfoList'
    };
    // console.log(params)
    http(params).then(res => {
       console.log(res);
      this.setData({ bankArr: res.data })
      //  tips(res.msg);
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getAccountData() {
    let params = {
      url: 'api/User/GetMemberInfo'
    }
    http(params).then(res => {
      if (res.code == 0) {
        console.log(res)
        this.setData({
          MobileOld: res.data.Mobile,
          Mobile: res.data.Mobile.substring(0, 3) + '****' + res.data.Mobile.substring(7)
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  code(){
    if (this.data.buttonDisable) return false;//倒计时状态不可点击
    this.getCodeSuccess();
    let params = {
      url: 'api/Login/GetUserSms',
      data: {
        Mobile: this.data.MobileOld, //手机号
        VerifyType: 11, //验证码类型，此处填写0
        userInfo:null,
      }
    }
    console.log(params)
    http(params).then(res => {
        console.log(res)
        tips(res.msg);
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  getCodeSuccess() {
    let count = 120;
    var intervalId = setInterval( ()=>{
        count -= 1;
        this.setData({
            buttonTitle: count + 's后重发',
            buttonDisable: true
        })
        if (count == 0) {
            clearInterval(intervalId);//倒计时结束，停止interval
            this.setData({
                buttonTitle: '获取验证码',
                buttonDisable: false
            })
        }
    }, 1000)
  },
  getOption(e) {// 点击选项
    this.setData({
      hideFlag: true,
      bankValue: e.currentTarget.dataset.value,
      BankCardName: e.currentTarget.dataset.txt
    })
  },
  BankName(e) { this.setData({ BankName:e.detail.value})},
  BankCardNo(e) { this.setData({ BankCardNo: e.detail.value }) },
  BankAddress(e) { this.setData({ BankAddress: e.detail.value }) },
  VerifyCode(e) { this.setData({ VerifyCode: e.detail.value }) },
  submit() {console.log()
    if (this.data.BankCardName == '请选择' || !this.data.BankCardNo || !this.data.BankName || !this.data.BankAddress){tips('请完善银行卡信息！');return;};
    let params = {
      url: 'api/Bank/AddBank',
      data: {
        "BankName": this.data.BankCardName, //持卡人姓名（因接口文档修改所以字段名称互换）
        "BankCardNo": this.data.BankCardNo, //卡号
        "BankCardName": this.data.BankName, //银行名称（因接口文档修改所以字段名称互换）
        "BankAddress": this.data.BankAddress, //开户行
        "Mobile": this.data.MobileOld, //手机号
        "VerifyCode": this.data.VerifyCode, //手机验证码
      }
    };
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        wx.navigateTo({
          url: '/pages/bankCardList/bankCardList',
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
  bankCard: function () {
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
    this.bankList();
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