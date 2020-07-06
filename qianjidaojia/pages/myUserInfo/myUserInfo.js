// pages/myUserInfo/myUserInfo.js
import {
  tips,
  imgUpload,
  http
} from '../../utils/util.js';
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: app.globalData.userInfo,
    photo:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAccountData();
  },
  getAccountData() {//账户信息
    let params = {
      url: 'api/User/GetMemberInfo'
    }
    http(params).then(res => {
      if (res.code == 0) {
        console.log(res)
        this.setData({
          Avatar: res.data.Avatar,
          NickName: res.data.NickName,
          Mobile: res.data.Mobile,
          Sex: res.data.Sex,
          photo:res.data.Avatar
        });
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  chooseimage: function () {
    var that = this;
    wx.showActionSheet({
      itemList: ['从相册中选择', '拍照'],
      itemColor: "#333333",
      success: function (res) {
        if (!res.cancel) {
          if (res.tapIndex == 0) {
            that.chooseWxImage('album')
          } else if (res.tapIndex == 1) {
            that.chooseWxImage('camera')
          }
        }
      }
    })
  },
  // 绘制图片到canvas上
  chooseWxImage: function (type) {
    const ctx = wx.createCanvasContext('myCanvas')//画布
    var that = this;
    wx.chooseImage({//上传图片事件
      count: 9,//数量
      sizeType: ['original', 'compressed'],//original 原图，compressed 压缩图，默认二者都有
      sourceType: ['album', 'camera'], //album 从相册选图，camera 使用相机，默认二者都有
      success: function (res) {
        console.log(res)
        ctx.drawImage(res.tempFilePaths[0], 0, 0, 100, 100)//成功选定第一张。后面的四个值分别是left，top，width，height，来控制画布上的图片的位置和大小
        ctx.draw();
        that.setData({ photo: res.tempFilePaths[0]});
        wx.getFileSystemManager().readFile({
          filePath: res.tempFilePaths[0], //选择图片返回的相对路径
          encoding: 'base64', //编码格式
          success: res => { //成功的回调
            const params = {
              url: 'api/User/EditHeadImage',
              data: {
                Avatar: 'data:image/png;base64,' + res.data
              }
            };
            http(params).then((result) => {
              console.log(result)

            });
          }
        })
      }
    })
  },
  onNickName(){
    wx.navigateTo({
      url: '/pages/nickName/nickName?pageName=nickName&NickName=' + this.data.NickName + '&Sex=' + this.data.Sex,
    })
  },
  onSex() { 
    wx.navigateTo({
      url: '/pages/nickName/nickName?pageName=sex&NickName=' + this.data.NickName + '&Sex=' + this.data.Sex,
    })
  },
  onAddress() {
    wx.navigateTo({
      url: '/pages/myAddress/myAddress',
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