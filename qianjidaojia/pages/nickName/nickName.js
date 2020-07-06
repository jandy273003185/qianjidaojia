// pages/nickName/nickName.js
import {
  tips,
  http
} from '../../utils/util.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageName:'',
    NickName:'',
    items: [
      { Sex: '男',value:1 },
      { Sex: '女',value:0 },
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.items.forEach((item,index)=>{
      var sexChecked = `items[${index}].checked`;
      item.Sex == options.Sex ? this.setData({ [sexChecked]: true }) : '';
    });
    this.setData({
      pageName: options.pageName,
      NickName: options.NickName
    });
    wx.setNavigationBarTitle({ title: this.data.pageName == 'nickName'?'修改昵称':'修改性别'})
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
  listenerInput(e){
    this.setData({NickName: e.detail.value});
  },
  nameVid(){
    const _nickName = this.data.NickName;
    const ret = /^[A-Za-z0-9_\u4e00-\u9fa5]+$/;
    if (ret.test(_nickName) === false) {
      tips('非法字符!');
    }
    if (ret.test(_nickName) === true && _nickName.length > 20) {
      tips('昵称长度超过限制!');
    }
  },
  del(){
    this.setData({ NickName: ''})
  },
  radioChange: function (e) {
    console.log( e.detail.value);
    this.setData({Sex:e.detail.value});
  },
  finish(){
    // this.nameVid();
    let params = {
      url: 'api/User/UpdateMemberInfo',
      data: {
        NickName: this.data.NickName,
        Sex: this.data.Sex,
        Birthday:'2020-02-02'
      }
    }
    // console.log(params)
    http(params).then(res => {
      // console.log(res)
      if (res.code == 0) {
        wx.navigateTo({
          url: '/pages/myUserInfo/myUserInfo',
        })
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
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