//index.js
//获取应用实例
const app = getApp()
import { hexMD5} from '../../utils/md5.js'  
Page({
  data: {
  },

  onReady: function () {
/*     wx.login({
      success(res) {
        if (res.code) {
          //发起网络请求
           console.log(res);
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    }) */
    let timeStamp = Date.parse(new Date());
    timeStamp = String(timeStamp / 1000);
    let nonceStr=this.getRandom(32);
    console.log(nonceStr);
    let paramObj={
      'appId': 'wx1c8b205cb45deaea',//小程序appId
      'timeStamp': timeStamp,//时间戳
      'nonceStr': nonceStr,//随机串
      'package': 'prepay_id=wx2017033010242291fcfe0db70013231072',//数据包
      'signType': 'MD5',//签名方式
    };
    let paySign=this.paramsWithASCII(paramObj)
    console.log(paramObj);
    wx.requestPayment(
      {
        ...paramObj,
        'paySign': paySign,//签名
        'success': function (res) {
          console.log(res);
        },
        'fail': function (res) { console.log(res); },
        'complete': function (res) { }
      })
    
  },
  getRandom(min, max) {//获取随机数
    var returnStr = "",
      range = (max ? Math.round(Math.random() * (max - min)) + min : min),
      arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    for (var i = 0; i < range; i++) {
      var index = Math.round(Math.random() * (arr.length - 1));
      returnStr += arr[index];
    }
    return returnStr;
  },
  paramsWithASCII(obj) {//参数名按照ASCII码升序排列   
    const _this = this;
    var strArr = Object.keys(obj);
    var s1 = Array.prototype.sort.call(strArr, function (a, b) {
      for (var i = 0; i < a.length; i++) {
        if (obj[a]) {//参数为空不参与签名
          if (a.charCodeAt(i) == b.charCodeAt(i)) continue;
          return a.charCodeAt(i) - b.charCodeAt(i);
        }
      }
    });
    var signStr = "";
    s1.map(function (item, index) {
      let param = item + "=" + obj[item];
      if (index == 0 && obj[item]) {
        signStr = param
      } else if (obj[item]) {
        signStr = signStr + "&" + param;
      }
    })
    signStr = signStr +"&key=cBgPoRDaIV7Gw4TDcTLUlWbSyMnDPV0X"
    signStr = hexMD5(signStr ).toUpperCase()
    return signStr;
  },

})
