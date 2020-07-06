// pages/regimentalInfo/regimentalInfo.js
import { http, tips } from '../../utils/util.js';
import drawQrcode from '../../utils/weapp.qrcode.js';
const APP = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
      role: wx.getStorageSync('role'),//团长1，营长2
        centreData:[],
        /* 团长和营长页面颜色差异控制,默认显示团长颜色 */
        into:'intoRed',
        num:"numRed",
        vline:'vlineRed',
       qrcodeHide: 'qrcodeHide',
      spreadCode:'',

      text: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
  onLoad: function (options) {
        if(this.data.role==2){
            this.setData({
                into: 'blue',
                num: "blue",
                vline: 'vlineBlue'
            });
        }
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
        this.queryCentreList();
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
    /* 团长或营长个人中心数据 */
    queryCentreList() {
        let params = {
            url: 'api/User/getGroupUserInfo',
            data: {
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    centreData:res.data
                });
            }
            //console.log(this.data.centreData);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
  //获取临时路径
  getTempFilePath: function () {
    wx.canvasToTempFilePath({
      canvasId: 'myQrcode',
      success: (res) => {console.log(res)
        this.saveQrcode(res.tempFilePath)
      }
    })
  },
  saveQrcode(url) {
    console.log(url)
    let _url = url.replace('wxfile','https');
    console.log(_url);
    // wx.downloadFile({
    //   url: _url,
    //   success: function (res) {console.log(res)
    //     if (res.statusCode === 200) {
    //       let img = res.tempFilePath;
          wx.saveImageToPhotosAlbum({
            filePath: url,
            success(res) {
              tips('保存成功！')
            },
            fail(res) {
              tips('保存失败')
            }
          });
    //     }
    //   }
    // });
  },
  qrcode() {
    this.setData({ qrcodeHide: 'qrcodeHide' });
    wx.setClipboardData({
      data: 'https://wap.qianjidaojia.com/indexv.html?spreadCode=' + this.data.centreData.spreadCode,//test.qianjidaojia.com
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            tips('复制成功')
          }
        })
      }
    })
  },
  seeUrl(){
    const pages = getCurrentPages() //获取加载的页面
    const currentPage = pages[pages.length - 1] //获取当前页面的对象
    const url = currentPage.route;
    this.setData({ qrcodeHide: '' });
    console.log(decodeURIComponent)
    drawQrcode({
      width: 200,
      height: 200,
      x: 20,
      y: 20,
      canvasId: 'myQrcode',
      // ctx: wx.createCanvasContext('myQrcode'),
      typeNumber: 10,
      text: 'https://wap.qianjidaojia.com/indexv.html?spreadCode='+this.data.centreData.spreadCode,
      image: {
        imageResource: '../../images/icon.png',
        dx: 70,
        dy: 70,
        dWidth: 60,
        dHeight: 60
      },
      callback(e) {
        console.log('e: ', e)
      }
    })
  },
  //点击开始时的时间
  timestart: function (e) {
    　　var _this = this;
    　　_this.setData({ timestart: e.timeStamp });
  },

  //点击结束的时间
  timeend: function (e) {
    　　var _this = this;
    　　_this.setData({ timeend: e.timeStamp });
  },

  //保存图片
  saveImg: function (e) {
    　　var _this = this;
    　　var times = _this.data.timeend - _this.data.timestart;
    　　if (times > 300) {
      　　　　console.log("长按");
      　　　　wx.getSetting({
        　　　　　　success: function (res) {
          　　　　　　　　wx.authorize({
            　　　　　　　　　　scope: 'scope.writePhotosAlbum',
            　　　　　　　　　　success: function (res) {
              　　　　　　　　　　　　console.log("授权成功");
              　　　　　　　　　　　　var imgUrl = '';//图片地址
                　　　　　　　　　　　　wx.downloadFile({//下载文件资源到本地，客户端直接发起一个 HTTP GET 请求，返回文件的本地临时路径
                  　　　　　　　　　　　　　　url: imgUrl,
                  　　　　　　　　　　　　　　success: function (res) {
                    　　　　　　　　　　　　　　　　console.log(res);
                    　　　　　　　　　　　　　　　　// 下载成功后再保存到本地
                    　　　　　　　　　　　　　　　　wx.saveImageToPhotosAlbum({
                      　　　　　　　　　　　　　　　　　　filePath: res.tempFilePath,//返回的临时文件路径，下载后的文件会存储到一个临时文件
                      　　　　　　　　　　　　　　　　　　success: function (res) {
                      　　　　　　　　　　　　　　　　　　　}
                    　　　　　　　　　　　　　　　　})
                  　　　　　　　　　　　　　　}
                　　　　　　　　　　　　})
            　　　　　　　　　　}
          　　　　　　　　})
        　　　　　　}
      　　　　})
    　　}
  },
    /* 进入我的推广订单 */
    toPromoteOrder(){
        wx.navigateTo({
            url: '/pages/promoteOrder/promoteOrder'
        })
    },
    toEarnings(){
        wx.navigateTo({
            url: '/pages/battalionEarnings/battalionEarnings'
        })
    },
    toCustomer(){
        wx.navigateTo({
            url: '/pages/battalionCustomer/battalionCustomer'
        })
    }
})