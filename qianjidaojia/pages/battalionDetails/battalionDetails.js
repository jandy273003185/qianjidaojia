// pages/battalionDetails/battalionDetails.js
import {
  wxCommon,
  _mobile,
  http, tips
} from '../../utils/util.js'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    /* 修改备注弹窗 */
    isModal:false,
    groupremark:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   console.log(options)
   this.data.id = options.id;
    
   },
  queryList() {
    let params = {
      url: 'api/User/getSecondGroupUserInfo',
      data: {
        offineId: this.data.id,//下级主键id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          "headimgurl": res.data.headimgurl,
          "nickname": res.data.nickname,  //昵称
          "groupremark": res.data.groupremark, //备注
          "offineuserTotal": res.data.offineuserTotal, //累计客户
          "offineuserTodayTotal": res.data.offineuserTodayTotal, //今日客户
          "offineorderTotal": res.data.offineorderTotal,  //累计订单
          "offineorderTodayTotal": res.data.offineorderTodayTotal, //今日购买订单
          "commissionPriceTodayTotal": res.data.commissionPriceTodayTotal, //今日预估团队收益
          "commissionDelayPriceTotal": res.data.commissionDelayPriceTotal, //待结算团队收益
          "commissionPriceTotal": res.data.commissionPriceTotal, //累计团队收益
          "commissionDelayPriceTotalY": res.data.commissionDelayPriceTotalY, //待结算收益
          "commissionPriceTotalY": res.data.commissionPriceTotalY,//累计收益
        });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  inputRemark(e) { this.setData({ GroupRemark: e.detail.value }) },
  confirm(e) {//修改备注
    let params = {
      url: 'api/User/UpdGroupRemark',
      data: {
        Id: this.data.id,  //用户下级主键id
        GroupRemark: this.data.GroupRemark //备注内容
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.onShow();
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
    this.queryList();
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

  },
    /* 弹窗修改备注名称 */
    ament() {
        this.setData({
            isModal: true
        });
    },
    concel(e) {
        console.log(e);
    },
})