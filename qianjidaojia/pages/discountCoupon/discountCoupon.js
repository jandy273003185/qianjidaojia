// packageB/pages/discountCoupon/discountCoupon.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    warpHeight: "", //初始高度置空
    statusArr: [
      { id: 1, name: '未领取' },
      { id: 2, name: '未使用' },
      { id: 3, name: '优惠码' }
    ],
    idx: 1,
    type:1,
    listArr:[],
    ExchangeCode:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wxCommon.listApi.getSystemInfo((res) => {
      const height = res.platform == 'android' ? 160 : 20;
      const warpHeight = (res.windowHeight - Math.ceil(height / res.pixelRatio) || 560) + "px";
      this.setData({
        warpHeight: warpHeight
      })
    }, (er) => {
      this.setData({
        warpHeight: "560px"
      })
    });
  },
  queryList() {
    let params = {
      url: 'api/Coupon/CouponCenterList',
      data: {
        Type: this.data.type //类型 1：未领取 2：未使用
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          listArr: res.data
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  inputExchangeCode(e) { this.setData({ExchangeCode:e.detail.value})},
  exchangeCode() {//优惠码
    let params = {
      url: 'api/Coupon/CheckExchangeCodeApi',
      data: {
        ExchangeCode: this.data.ExchangeCode
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        const arr = []; arr.push(res.data);
        this.setData({
          listArr: arr
        });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  exchange(e) {//领取
    let params = {
      url: 'api/Coupon/ReceiveCoupon',
      data: {
        CounponDetailId: e.currentTarget.dataset.coudetailid || 0, //对应一卡一码id
        CouponId: e.currentTarget.dataset.couid || e.currentTarget.dataset.id,//优惠券id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code != 0) { tips(res.msg);return;}
      this.setData({
        idx: 2,
        type:2
      });
      this.queryList();
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  confirm() { this.exchangeCode()},
  onStatus(e) {//状态查询（全部。。。）
    let index = e.currentTarget.dataset.index;
    this.setData({
      idx: index
    });
    let type = '';
    if (index == 1) {
      type = 1;
    } else if (index == 2) {
      type = 2;
    } else if (index == 3) {
      // type = 3;
    }
    let startTimeStamp = this.data.timeStamp;
    this.setData({ timeStamp: e.timeStamp });
    // if (e.timeStamp - startTimeStamp > 350) {
    this.setData({
      listArr: [],
      type: type
    });
    // }
    if (index == 3) { 
      // this.exchangeCode();
    }else{
      this.queryList();
    }
  },
  selectedCoupon(e){

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

  }
})