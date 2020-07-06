// pages/bankCardList/bankCardList.js
import { http, tips } from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    startX: 0, //开始坐标
    startY: 0,
    withdrawDeposit:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.memberBankList();
    if (options.pageName){this.data.withdrawDeposit = options.pageName;}
  },
  memberBankList() {
    let params = {
      url: 'api/Bank/BankList',
      data: { page: 1}
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let arr = []
        for (let i = 0; i < res.data.length; i++) {
          arr.push(res.data[i].BankCardNo.replace(/\s/g, '').replace(/(\d{4})\d+(\d{4})$/, "**** **** **** $2"));
        }
        this.setData({
          listArr: res.data,
          BankCardNoArr: arr
        });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    this.data.listArr.forEach(function (v, i) {
      if (v.isTouchMove)//只操作为true的
        v.isTouchMove = false;
    })
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      listArr: this.data.listArr
    })
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this,
      index = e.currentTarget.dataset.index,//当前索引
      startX = that.data.startX,//开始X坐标
      startY = that.data.startY,//开始Y坐标
      touchMoveX = e.changedTouches[0].clientX,//滑动变化坐标
      touchMoveY = e.changedTouches[0].clientY,//滑动变化坐标
      //获取滑动角度
      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    that.data.listArr.forEach(function (v, i) {
      v.isTouchMove = false
      //滑动超过30度角 return
      if (Math.abs(angle) > 30) return;
      if (i == index) {
        if (touchMoveX > startX) //右滑
          v.isTouchMove = false
        else //左滑
          v.isTouchMove = true
      }
    })
    //更新数据
    that.setData({
      listArr: that.data.listArr
    })
  },
  /**
   * 计算滑动角度
   * @param {Object} start 起点坐标
   * @param {Object} end 终点坐标
   */
  angle: function (start, end) {
    var dX = end.X - start.X,
      dY = end.Y - start.Y
    //返回角度 /Math.atan()返回数字的反正切值
    return 360 * Math.atan(dY / dX) / (2 * Math.PI);
  },
  //删除事件
  del: function (e) {
    this.data.listArr.splice(e.currentTarget.dataset.index, 1)
    this.setData({
      listArr: this.data.listArr
    })
    this.delBankCard(e.currentTarget.dataset.id);
  },
  delBankCard(id){
    let params = {
      url: 'api/Bank/DeleteBank',
      data: { Id:id}
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      tips(res.msg);
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  selectBank(e){
    if (this.data.withdrawDeposit == 'withdrawDeposit'){
      if (!e.currentTarget.dataset.isbankaut) { tips('该银行卡正在认证中,请耐心等待!'); return; };
      wx.navigateTo({
        url: '/pages/withdrawDeposit/withdrawDeposit?bankId=' + e.currentTarget.dataset.id + '&bankName=' + e.currentTarget.dataset.bankname + '&bankNo=' + e.currentTarget.dataset.bankno,
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