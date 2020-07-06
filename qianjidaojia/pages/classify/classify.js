// pages/classify/classify.js
import { http, tips, wxCommon } from '../../utils/util.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    role: wx.getStorageSync('role'),
    isShow: true,//热门搜索及历史提示
    isResult: false,//显示搜索结果
    searchkey: '',
    listArr: [],
    /* 点击购物车 */
    isCart: '',
    animation: '',
    goodsName: '',
    stockNum: '',
    nowPrice: '',
    oldPrice: '',
    goodsImg: '',
    cartToggle: false,
    warpHeight: "", //初始高度置空
    page: 1,//页码
    pageSize: 20,
    count: 0,//总记录数
    loadMoreFlag: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wxCommon.listApi.getSystemInfo((res) => {
      const height = res.platform == 'android' ? 260 : 20;
      const warpHeight = (res.windowHeight - Math.ceil(height / res.pixelRatio) || 560) + "px";
      this.setData({
        warpHeight: warpHeight
      })
    }, (er) => {
      this.setData({
        warpHeight: "560px"
      })
    });
    this.queryList();
  },
  loadMore() {//分页
    if (this.data.listArr.length >= this.data.count) { return };
    let page = this.data.page;
    ++page;
    this.setData({ loadMoreFlag: true, page });
    this.queryList();
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

  },
  /* input聚焦时 */
  focus() {
    this.setData({
      isShow: true,//显示热门搜索及历史提示
      // isResult: false,//隐藏搜索结果
    });
  },
  inputListener(e) {
    this.setData({ searchkey: e.detail.value })
  },
  /* 搜索确定时 */
  confirm() {
    this.setData({
      // isShow: false,//隐藏热门搜索及历史提示
      isResult: true,//显示搜索结果
      listArr: [],
      page: 1,
      pageSize: 20
    });
    this.queryList();
  },
  queryList() {
    let params = {
      url: 'api/Goods/GoodsList',
      data: {
        ShopId: '',//店铺id 经Encrypt2加密
        searchkey: this.data.searchkey,
        cid: '', //分类id
        page: this.data.page,//当前第几页
        pageSize: 20,//一页显示几条数据
        // sortorder:0 //排序，暂时没有排序功能
      }
    }; console.log(params)
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
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  /* 进入商品详情 */
  toDetails(e) {
    console.log(e);
    wx.navigateTo({
      url: "/pages/goodsDetails/goodsDetails?Id=" + e.currentTarget.dataset.id,
    })
  },
  /* 加入购物车 */
  addCart(e) {
    console.log(e);
    this.setData({
      isCart: true,
      cartToggle: true,
      animation: 'riseToast',
      goodsName: e.currentTarget.dataset.proname,
      stockNum: e.currentTarget.dataset.prostock || 100000,
      nowPrice: e.currentTarget.dataset.proprice,
      oldPrice: e.currentTarget.dataset.prooldp,
      goodsImg: e.currentTarget.dataset.proimg,
      proId: e.currentTarget.dataset.proid,
    });
    this.selectComponent("#cart").findSpecification();
  },
  cartNum() {
    this.selectComponent("#tabbar").querycartNum();
  }
})