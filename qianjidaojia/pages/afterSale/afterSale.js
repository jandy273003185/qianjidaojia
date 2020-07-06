// pages/afterSale/afterSale.js
import {
  wxCommon, http, tips
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    statusArr: [
      { id: 0, name: '全部' },
      { id: 1, name: '待付款' },
      { id: 2, name: '待发货' },
      { id: 3, name: '配送中' },
      { id: 4, name: '待评价' }
    ],
    idx: 0,
    warpHeight: "", //初始高度置空
    listArr: [],
    page: 1,//页码
    pageSize: 15,
    count: 0,//总记录数
    loadMoreFlag: false,
    Status: 0,
    listArrIndex: 0,

    reasonsArr: [],
    reasonsValue: '',
    hideFlag: true,//true-隐藏  false-显示
    animationData: {},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wxCommon.listApi.getSystemInfo((res) => {
      const height = res.platform == 'android' ? 20 : 10;
      const warpHeight = (res.windowHeight - Math.ceil(height / res.pixelRatio) || 560) + "px";
      this.setData({
        warpHeight: warpHeight
      })
    }, (er) => {
      this.setData({
        warpHeight: "560px"
      })
    });
    if (options) {
      this.data.Status = options.Status;
      this.setData({ idx: options.Status });
    }
  },
  getOrder() {
    let params = {
      url: 'api/Order/RefundOrderList',
      data: {
        page: this.data.page,
        pageSize: 20,
        Status: this.data.Status,//0:全部订单 1：待付款 2:待发货 3：配送中 4：待评价
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let arr = this.data.listArr;
        if (!this.data.loadMoreFlag) { arr = []; this.setData({ loadMoreFlag: false }); }
        arr = arr.concat(res.data);
        this.setData({
          listArr: arr,
          count: res.count
        });
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  loadMore() {//分页
    if (this.data.listArr.length >= this.data.count) { return };
    let page = this.data.page;
    ++page;
    this.setData({ loadMoreFlag: true, page });
    this.getOrder();
  },
  delOrder(e) {//删除订单
    let that = this;
    wx.showModal({
      title: '是否确认删除订单',
      // content: '',
      cancelText: '取消',
      confirmText: '确定',
      success(res) {
        if (res.confirm) {
          // 用户点击了确定属性的按钮，对应选择了'男'
          let params = {
            url: 'api/Order/DeleteOrders',
            data: { OrderNo: e.currentTarget.dataset.ordernum }
          };
          // console.log(params)
          http(params).then(res => {
            //console.log(res);
            if (res.code == 0) {
              // this.setData({ listArr:this.data.listArr.splice(e.currentTarget.dataset.index,1)})
              tips(res.msg);
              that.onShow();
            } else {
              tips(res.msg);
            }
          }, rejected => {
            tips('服务器异常,请稍后重试！')
          })
        }
      }
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
    this.getOrder();
  },
  
  checkLogistics(e) {//查看物流
    wx.navigateTo({
      url: '/pages/logistics/logistics?ordernum=' + e.currentTarget.dataset.ordernum,
    })
  },

  withdraw(e) {//撤销申请
    let params = {
      url: 'api/Order/CanelRefund',
      data: {
        OrderNo: e.currentTarget.dataset.ordernum,
        OrderItemId: e.currentTarget.dataset.productid
      }
    };
    console.log(params)
    http(params).then(res => {
      console.log(res);
      tips(res.msg);
      this.onShow();
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  afterSaleDetails(e) {
    wx.navigateTo({
      url: '/pages/afterSaleDetails/afterSaleDetails?ordernum=' + e.currentTarget.dataset.ordernum + '&RefundId=' + e.currentTarget.dataset.refundid,
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