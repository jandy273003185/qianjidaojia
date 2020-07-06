//app.js
import {
   tips, http
} from '/utils/util.js'
App({
    globalData: {
        userInfo: null,
        isIphoneX: false,
    },
    /* 获取用户的手机型号 */
    getSystemInfo: function() {
        let that = this;
        wx.getSystemInfo({
            success: function(res) {
                console.log(res);
                that.globalData.isIphoneX = res.model.indexOf("iPhone X") > -1 ? true : false;
            },
        })
    },
    getAddress(){
      wx.getSetting({
        success(res) {
          console.log("vres.authSetting['scope.address']：", res.authSetting['scope.address'])
          if (res.authSetting['scope.address']) {
            console.log("111")
            wx.chooseAddress({
              success(res) {
                console.log(res)
                let params = {
                  url: 'api/Address/AddAddress',
                  data: {
                    Consignee: res.userName,  //收货人
                    Mobile: res.telNumber,  //手机号
                    IsDefault: 1, //设置默认地址 1：默认 0：不默认
                    // ProvinceCode: this.data.selectProvinceId, //省代码
                    // CityCode: this.data.selectCityId,//市代码
                    // DistrictCode: this.data.selectAreaId, //区代码
                    FullAddress: res.provinceName + res.cityName + res.countyName + res.detailInfo, //全地址，不带省市区

                  }
                }
                // console.log(params);
                http(params).then(res => {
                  console.log(res);
                  if (res.code == 0) {
                    
                  }
                }, rejected => {
                  tips('服务器异常,请稍后重试！')
                })
              }
            })
          } else {
            if (res.authSetting['scope.address'] == false) {
              console.log("222")
              wx.openSetting({
                success(res) {
                  console.log(res.authSetting)

                }
              })
            } else {
              console.log("eee")
              wx.chooseAddress({
                success(res) {
                  
                }
              })
            }
          }
        }
      })
    },
    onLaunch: function(options) {
      // if (options.Id){this.data.id=options.Id;}
        /* 进入小程序后隐藏自带tabbar */
        wx.hideTabBar();
        /* 执行获取用户手机型号 */
      this.queryUsreInfo();
      if (!wx.getStorageSync('userId') || !wx.getStorageSync('token') || !wx.getStorageSync('SmallOpenid')) {
        console.log('----------login')
        wx.navigateTo({ url: '/pages/login/login' })
      } else if (options.Id && wx.getStorageSync('userId') && wx.getStorageSync('token') && wx.getStorageSync('SmallOpenid')) {
        wx.navigateTo({ url: '/pages/goodsDetails/goodsDetails?Id=' + options.Id })
      } else if (wx.getStorageSync('userId') && wx.getStorageSync('token') && wx.getStorageSync('SmallOpenid')) {
        wx.navigateTo({ url: '/pages/index/index'})
      }
      this.getSystemInfo();
      // this.getAddress();
        // 展示本地存储能力
        var logs = wx.getStorageSync('logs') || []
        logs.unshift(Date.now())
        wx.setStorageSync('logs', logs)
        
        // 获取用户信息
        wx.getSetting({
            success: res => {
                if (res.authSetting['scope.userInfo']) {
                    // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
                    wx.getUserInfo({
                        success: res => {
                            // 可以将 res 发送给后台解码出 unionId
                            this.globalData.userInfo = res.userInfo;console.log(res)

                            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
                            // 所以此处加入 callback 以防止这种情况
                            if (this.userInfoReadyCallback) {
                                this.userInfoReadyCallback(res)
                            }
                        }
                    })
                }
            }
        })
    },
  //获取用户信息接口
  queryUsreInfo: function () {
    let params = {
      url: 'api/User/GetMemberInfo'
    }
    http(params).then(res => {
      console.log(res);
      console.log(wx.getStorageSync('userId') + ';' + wx.getStorageSync('token'))
      wx.setStorageSync('SmallOpenid', res.data.SmallOpenid);
      if (res.code == 0 && wx.getStorageSync('userId') && wx.getStorageSync('token') && res.data.SmallOpenid) {
        wx.setStorageSync('SpreadCode', res.data.SpreadCode);
        
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },

})

// onGotUserInfo(){
//   //checkSession检查登录态（通过session_key，openid来得到）是否过期
//   wx.checkSession({
//     success() {
//       console.log("success")
//     },
//     fail() {
//       var that = this;
//       //登录
//       wx.login({
//         success(res) {
//           console.log(res)
//           if (res.code) {
//             //在小程序规定请求地址通过appId，appSecret，登录时获取的code 来获得json数据
//             var url = 'https://api.weixin.qq.com/sns/jscode2session?appid=wx1c8b205cb45deaea&secret=' + that.data.secret + 'SECRET&js_code=' + res.code + '&grant_type=authorization_code'; console.log(url)
//             //向服务器发起请求获取session_key，openid
//             wx.request({
//               url: url,
//               data: {
//                 session_key: "",
//                 openid: ""
//               }
//             })
//           }
//           else {
//             console.log('登录失败！' + res.errMsg)
//           }
//         }
//       })
//     }
//   })
// } 