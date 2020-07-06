// pages/login/login.js
import {http, tips} from '../../utils/util.js'
Page({
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    qrcodeHide: 'qrcodeHide'
  },
  onLoad: function (options) {
    console.log(77777777,options)
    if (options.SpreadCode) { this.data.SpreadCode = options.SpreadCode}
    // this.queryUsreInfo();
    //查看是否授权
    // wx.getSetting({
    //   success: function (res) {
    //     if (res.authSetting['scope.userInfo']) {
    //       wx.getUserInfo({
    //         success: function (res) {
    //           //从数据库获取用户信息
    //           that.queryUsreInfo();
    //         }
    //       });
    //     }
    //   }
    // })
  },
  
  bindGetUserInfo: function (e) {
    if (e.detail.userInfo) {//用户按了允许授权按钮
      // 登录
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          console.log(res)
          var that = this;
          if (res.code) {
            that.setData({ qrcodeHide: '' });
            //在小程序规定请求地址通过appId，appSecret，登录时获取的code 来获得json数据
            //向服务器发起请求获取session_key，openid
            that.data.code = res.code;
            wx.setStorageSync('WxCode', res.code);
            const params = {
              url: 'api/Login/GetLoginOpenId',
              data: {
                code: res.code
              },
              method: 'GET'
            };
            http(params).then(res => {
              console.log(res);
              if (res.code == 0) {
                wx.setStorageSync('userStorage', {
                  openId: res.data.OpenId,
                  SessionKey: res.data.SessionKey,
                  nickName: e.detail.userInfo.nickName,
                  avatarUrl: e.detail.userInfo.avatarUrl,
                  sex: e.detail.userInfo.gender,
                  city: e.detail.userInfo.city,
                  province: e.detail.userInfo.province,
                  country: e.detail.userInfo.country
                });
              } else {
                tips(res.msg);
              }
            }, rejected => {
              tips('服务器异常,请稍后重试！')
            })
          } 
        }
      }); 
    } else {
      //用户按了拒绝按钮
      wx.showModal({
        title: '警告',
        content: '您点击了拒绝授权，将无法进入小程序，请授权之后再进入!!!',
        showCancel: false,
        confirmText: '返回授权',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击了“返回授权”')
          }
        }
      })
    }
  },
  
  // getSession_key() {//获取session_key
  //   const that = this; console.log(this.data.code)
  //   wx.request({
  //     url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx1c8b205cb45deaea&secret=7be8c66ddf1f40876a28613265c04852&js_code=' + this.data.code +'&grant_type=authorization_code',
  //     success(res) {
  //       console.log(res);
  //       that.data.session_key = res.data.session_key;
  //     }
  //   })
  // },
  getPhoneNumber(e) {//获取手机号
    // console.log(e.detail.errMsg)
    // console.log(e.detail.iv)
    console.log(e.detail.encryptedData)
    this.data.encryptedData = e.detail.encryptedData;
    this.data.iv = e.detail.iv;
    if (e.detail.encryptedData){
      this.saveUser();
    }
  },
  //保存用户信息
  saveUser() {
    const params = {
      url: 'api/Login/XCXBindOrRegister',
      data: {
        encryptedData: this.data.encryptedData, //加密数据
        sessionKey: wx.getStorageSync('userStorage').SessionKey,
        iv: this.data.iv,//加密算法的初始向量
        unionid: '', //微信unionid，可为空
        openId: wx.getStorageSync('userStorage').openId,//微信openId
        token: '', //商城的token，可为空
        SpreadCode: this.data.SpreadCode||'', //团长/营长推广码
        nickname: wx.getStorageSync('userStorage').nickName, //微信昵称
        avatarUrl: wx.getStorageSync('userStorage').avatarUrl, //微信头像
        sex: wx.getStorageSync('userStorage').sex,
        city: wx.getStorageSync('userStorage').city,
        province: wx.getStorageSync('userStorage').province,
        country: wx.getStorageSync('userStorage').country
      }
    }; 
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        wx.setStorageSync(
          'token',res.data.Token
        );
        wx.setStorageSync(
        'userId',res.data.UserId
        );
        //授权成功后，跳转进入小程序首页
        wx.switchTab({
          url: '/pages/index/index'
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
})