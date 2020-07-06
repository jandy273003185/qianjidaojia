// pages/withdrawDeposit/withdrawDeposit.js
import { http, tips} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listArr:[],
    Amount:'',
    bankId:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAccountData();
    console.log(options)
    if (options.bankId) { 
      this.data.bankId = options.bankId;
      let listArr = 
      this.setData({
        listArr: [{ BankName: options.bankName}],
        BankCardNoArr: [options.bankNo]
      });
    }else{
      this.memberBankList();
    }
  },
  memberBankList() {
    let params = {
      url: 'api/DrawMoney/memberBankList'
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let arr = []
        for(let i=0; i<1; i++){
          arr.push(res.data[i].BankCardNo.replace(/\s/g, '').replace(/(\d{4})\d+(\d{4})$/, "**** **** **** $2"));
        }
        this.setData({
          listArr:res.data,
          BankCardNoArr:arr,
          bankId:res.data[0].Id
        });
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
        console.log(res)
        this.setData({
          Wallet: res.data.Wallet,
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  inputMoney(e){
    if (Number(e.detail.value) > this.data.Wallet) { tips('余额不足!'); this.setData({ Amount: ''});return;}
    this.setData({Amount:e.detail.value});
  },
  withdrawDeposit() {
    let params = {
      url: 'api/DrawMoney/memberDrawMoneyApply',
      data: {
        Amount: this.data.Amount,  //提现金额
        BankId: this.data.bankId //银行卡号id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      tips(res.msg);
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