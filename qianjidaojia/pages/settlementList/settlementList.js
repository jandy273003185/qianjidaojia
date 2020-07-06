// pages/settlementList/settlementList.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    goodsArr:[],
    totalNum:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.id = options.goodsid;
    this.getCartList(); 
  },
  getCartList() {
    let params = {
      url: 'api/Cart/GetConfirmOrderGoods',
      data: {
        CartIdList: this.data.id
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        const list = res.data;
        let map = {}, dest = [];
        
        for (let i = 0; i < res.data.length; i++) {
          let ai = res.data[i];
          let num = 0;
          this.setData({totalNum:this.data.totalNum+ai.Number});
          if (!map[ai.ShopId]) {
            dest.push({
              id: ai.ShopId,
              ShopName: ai.ShopName,
              goodsinfo: [ai],
              selected: false,
              num:ai.Number+num
            });
            map[ai.ShopId] = ai;
          } else {
            for (let j = 0; j < dest.length; j++) {
              let dj = dest[j];
              if (dj.id == ai.ShopId) {
                ai.Number += num;
                dj.goodsinfo.push(ai);
                break;
              }
            }
          }
        };
        console.log(dest)
        this.setData({ goodsArr: dest });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  // nowBuy() {//从商品详情页点击立即购买
  //   let params = {
  //     url: 'api/Goods/BuyNowInfo',
  //     data: {
  //       proId: this.data.goodsid
  //     }
  //   }; console.log(params);
  //   http(params).then(res => {
  //     console.log(res);
  //     if (res.code == 0) {
  //         this.setData({ goodsArr: dest });
  //     } else {
  //       tips(res.msg);
  //     }
  //   }, rejected => {
  //     tips('服务器异常,请稍后重试！')
  //   })
  // },
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