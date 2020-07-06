// pages/shop/shop.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isActive: false,
    sideNav: [{ 'name': '秒杀' }, { 'name': '直降' }, { 'name': '减运专区' }],
    acitveSideIdx: 0,
    topNav: [{ 'name': '全部商品' }, { 'name': '减运专区' }],
    topActiveIdx: 0,
    goods: [{ 'name': '特仑苏 纯牛奶 250ml*12包', 'saleNum': '1522', 'appraise': '95', 'price': 49.9, 'discount': 39.9 }, { 'name': '特仑苏 纯牛奶 250ml*12包', 'saleNum': '1522', 'appraise': '95', 'price': 49.9, 'discount': 39.9 }],
    carList: [{ 'name': '特仑苏 纯牛奶 250ml * 12包', 'price': 49.9, 'discount': 39.9, 'num': 1, checked: true }],
    selectedAll: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  showCarList() {//购物车列表弹窗
    this.setData({
      isActive: !this.data.isActive
    })
  },
  changeSideNav(e) {//侧边分类导航
    const theIdx = e.target.dataset.idx;
    this.setData({
      acitveSideIdx: theIdx
    })
  },
  changeTopNav(e) {//头部分类导航
    const theIdx = e.target.dataset.idx;
    this.setData({
      topActiveIdx: theIdx
    })
  },
  checkAllboxChange(e) {//全选
    let carList = this.data.carList;
    carList = carList.map(function (item) {
      item.checked = Boolean(e.detail.value.length);
      return item
    })
    this.setData({
      carList: carList
    })
  },
  addCarNum(e) {//购物车产品数量更改
    const theIdx = e.target.dataset.idx;
    let price = 'carList[' + theIdx + '].num'
    this.setData({
      [price]: this.data.carList[theIdx].num + 1
    })
  },
  cutCarNum(e) {
    const theIdx = e.target.dataset.idx;
    if (this.data.carList[theIdx].num > 1) {
      let price = 'carList[' + theIdx + '].num'
      this.setData({
        [price]: this.data.carList[theIdx].num - 1
      })
    } else{
      wx.showToast({
        icon:'none',
        title: '数量不能小于1',
      })
    }
  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },
  scroll() {

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