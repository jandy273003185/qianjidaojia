// pages/myOrder/myOrder.js
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
    listArr: [
      // { type: '[普通订单]', merchant: '钱记到家', img: 'https://mall.qianjidaojia.com//upload/admin/2020-02-04/2020020415044663600s02233.jpg', 'dealState': '交易关闭', 'goodsTitle': '【限广东省】A级-进口车厘子A级-进口车厘子A级-进口车厘子', 'price': '88.00',num:10,totalNum:10,totalMoney:'880.00' },
      // { type: '[普通订单]', merchant: '钱记到家', img: 'https://mall.qianjidaojia.com//upload/admin/2020-02-04/2020020415044663600s02233.jpg', 'dealState': '交易关闭', 'goodsTitle': '【限广东省】A级-进口车厘子A级-进口车厘子A级-进口车厘子', 'price': '88.00', num: 10, totalNum: 10, totalMoney: '880.00' },
      // { type: '[普通订单]', merchant: '钱记到家', img: 'https://mall.qianjidaojia.com//upload/admin/2020-02-04/2020020415044663600s02233.jpg', 'dealState': '交易关闭', 'goodsTitle': '【限广东省】A级-进口车厘子A级-进口车厘子A级-进口车厘子', 'price': '88.00', num: 10, totalNum: 10, totalMoney: '880.00' },
      // { type: '[普通订单]', merchant: '钱记到家', img: 'https://mall.qianjidaojia.com//upload/admin/2020-02-04/2020020415044663600s02233.jpg', 'dealState': '交易关闭', 'goodsTitle': '【限广东省】A级-进口车厘子A级-进口车厘子A级-进口车厘子', 'price': '88.00', num: 10, totalNum: 10, totalMoney: '880.00' },
      // { type: '[普通订单]', merchant: '钱记到家', img: 'https://mall.qianjidaojia.com//upload/admin/2020-02-04/2020020415044663600s02233.jpg', 'dealState': '交易关闭', 'goodsTitle': '【限广东省】A级-进口车厘子A级-进口车厘子A级-进口车厘子', 'price': '88.00', num: 10, totalNum: 10, totalMoney: '880.00' }
    ],
    page: 1,//页码
    pageSize: 15,
    count: 0,//总记录数
    loadMoreFlag: false,
    Status:0,
    listArrIndex:0,

    reasonsArr: [],
    reasonsValue:'',
    hideFlag: true,//true-隐藏  false-显示
    animationData: {},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wxCommon.listApi.getSystemInfo((res) => {
      const height = res.platform == 'android' ? 160 : 140;
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
      url: 'api/Order/OrderList',
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
  delOrder(e){//删除订单
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
  onStatus(e) {//状态查询（全部。。。）
    let index = e.currentTarget.dataset.index;
    this.setData({
      idx: index
    });
    let Status = '';
    if (index == 0) {
      Status = 0;
    } else if (index == 1) {
      Status = 1;
    } else if (index == 2) {
      Status = 2;
    } else if (index == 3) {
      Status = 3;
    } else if (index == 4) {
      Status = 4;
    }
    let startTimeStamp = this.data.timeStamp;
    this.setData({ timeStamp: e.timeStamp });
    // if (e.timeStamp - startTimeStamp > 350) {
      this.setData({
        listArr: [],
        Status: Status,
        page: 1
      });
      this.getOrder();
    // }
  },
 orderReasonsList(e){//取消订单原因列表
   let params = {
     url: 'api/Order/CancelReason',
     method:'GET'
   };
   // console.log(params)
   http(params).then(res => {
    //  console.log(res);
     this.setData({ reasonsArr: res.data})
    //  tips(res.msg);
   }, rejected => {
     tips('服务器异常,请稍后重试！')
   })
 },
  cancelOrder(e) {//取消订单
    this.data.ordernum = e.currentTarget.dataset.ordernum;
    this.data.listArrIndex = e.currentTarget.dataset.index; 
    this.showModal();
  },
  payment(e){//付款
    wx.navigateTo({
      url: '/pages/paymentType/paymentType?ordernum=' + e.currentTarget.dataset.ordernum,
    })
  },
  refund(e){//退款
    wx.navigateTo({
      url: '/pages/refund/refund?ordernum=' + e.currentTarget.dataset.ordernum,
    })
  },
  remindDelivery(e){//提醒发货
    let params = {
      url: 'api/Order/Remind',
      data: {
        OrderNo: e.currentTarget.dataset.ordernum
      }
    };
    // console.log(params)
    http(params).then(res => {
      // console.log(res);
      tips(res.msg)
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  checkLogistics(e){//查看物流
    wx.navigateTo({
      url: '/pages/logistics/logistics?ordernum=' + e.currentTarget.dataset.ordernum,
    })
  },
  confirm(e){//确认收货
    let that = this;
    wx.showModal({
      title: '是否确认收货',
      // content: '',
      cancelText: '取消',
      confirmText: '确定',
      success(res) {
        if (res.confirm) {
          // 用户点击了确定属性的按钮，对应选择了'男'
          let params = {
            url: 'api/Order/ConfirmReceipt',
            data: {
              OrderNo: e.currentTarget.dataset.ordernum
            }
          };
          http(params).then(res => {
            // console.log(res);
            tips(res.msg)
            that.onShow();
          }, rejected => {
            tips('服务器异常,请稍后重试！')
          })
        }
      }
    })
  },
  withdraw(e){//撤销申请
    let params = {
      url: 'api/Order/CanelRefund',
      data:{
        OrderNo: e.currentTarget.dataset.ordernum,
        OrderItemId: 0,//e.currentTarget.dataset.productid
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
  evaluation(e){//去评价
    wx.navigateTo({
      url: '/pages/evaluation/evaluation?ordernum=' + e.currentTarget.dataset.ordernum,
    })
  },
  getOption(e) {// 点击选项
    let params = {
      url: 'api/Order/CancelOrders',
      data: {
        OrderNo: this.data.ordernum,
        ReMarks: e.currentTarget.dataset.value
      }
    };
    // console.log(params)
    http(params).then(res => {
      // console.log(res);
      this.setData({
        hideFlag: true
      })
      if (res.code == 0) {
        // this.setData({ listArr: this.data.listArr.splice(this.data.listArrIndex, 1) })
        this.onShow();
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