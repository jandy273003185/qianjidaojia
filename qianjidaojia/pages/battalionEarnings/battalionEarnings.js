// pages/battalionEarnings/battalionEarnings.js
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
      /* 团长与营长颜色显示 */
      role: wx.getStorageSync('role'),//团长1，营长2
      active:'activeRed',
      fontc:'fontRed',
      bortc:'borRed',
      vline:'vlineRed',

    statusArr: [
      { id: 0, name: '全部' },
      { id: 1, name: '今日' },
      { id: 2, name: '昨日' },
      { id: 3, name: '近7日' },
    ],
    idx:0,
    Day: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      if(this.data.role==2){
          this.setData({
              active: 'activeBlue',
              fontc: 'fontBlue',
              bortc: 'borBlue',
              vline: 'vlineBlue',
          });
      }
    this.queryList();
  },
  onStatus(e) {//状态查询（全部。。。）
    let index = e.currentTarget.dataset.index;
    this.setData({
      idx: index
    });
    let Day = ''; 
    if (index == 0) {
      Day = '';
    } else if (index == 1) {
      Day = '0';
    } else if (index == 2) {
      Day = '1';
    } else if (index == 3) {
      Day = '7';
    }
    // let startTimeStamp = this.data.timeStamp;
    // this.setData({ timeStamp: e.timeStamp });
    // if (e.timeStamp - startTimeStamp > 350) {
    this.setData({
      listArr: [],
      Day: Day
    });
    // } 
    this.queryList();
  },
  queryList() {
    let params = {
      url: 'api/User/getGroupOffineProfitInfo',
      data: {
        Day: this.data.Day, //””：全部 1：今日 2：昨日 7：近七天
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          "offineuserTodayTotal": res.data.offineuserTodayTotal, //累计客户
          "offineorderTodayTotal": res.data.offineorderTodayTotal,//累计订单(笔)
          "commissionPriceTotal": res.data.commissionPriceTotal, //累计收益
          "commissionDelayPriceTotal": res.data.commissionDelayPriceTotal, //待结算
          "offineuserUserTotal": res.data.offineuserUserTotal, //营长总人数(人)  值为空或null，不显示
          "offinecommissionPriceTotal": res.data.offinecommissionPriceTotal,//团队收益(元) 值为空或null，不显示
          "grouplevel": res.data.grouplevel, //用户身份，为0代表普通用户，1代表团长，2代表营长
          "balanceTotal": res.data.balanceTotal //累计提现(元)
        });
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