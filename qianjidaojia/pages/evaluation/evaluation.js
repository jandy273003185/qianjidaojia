// pages/evaluation/evaluation.js
import {
  wxCommon, http, tips, dateFormat
} from '../../utils/util.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    star1:'images/star1.png',
    star2: 'images/star22.png',
    star3: 'images/star33.png',
    Starnum:5,
    Content:'',
    ordernum:'',
    ProductImg:'',
    num: 0,
    array: [0],//默认显示一个
    array2: [0],//默认显示一个
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.ordernum = options.ordernum;
    this.getOrder();
  },
  getOrder(e) {
    let params = {
      url: 'api/Order/OrderDetails',
      data: { OrderNo:this.data.ordernum}
    };
    // console.log(params)
    http(params).then(res => {
      //  console.log(res);
      this.setData({ ProductImg: res.data.orderDetails[0].ProductImg, ProductId: res.data.orderDetails[0].ProductId})
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  inputDescription(e) {
    var value = e.detail.value;
    var len = parseInt(value.length);
    this.setData({
      num: len,
      Content: value
    })
  },

  //删除img
  delInput: function (e) {
    let nowidx = e.currentTarget.dataset.idx;//当前索引
    let oldarr = this.data.array;//循环内容
    oldarr.splice(nowidx, 1);    //删除当前索引的内容，这样就能删除view了
    let oldarr2 = this.data.array2;//循环内容
    oldarr2.splice(nowidx, 1);
    if (oldarr.length < 1) {
      oldarr = [0]  //如果循环内容长度为0即删完了，必须要留一个默认的。这里oldarr只要是数组并且长度为1，里面的值随便是什么
      oldarr2 = [0]
    }
    this.setData({
      array: oldarr,
      array2: oldarr2
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
        // that.setData({ photo: res.tempFilePaths[0] });
        let old = that.data.array;
        old.push({'PicUrl':res.tempFilePaths[0]});//这里不管push什么，只要数组长度增加1就行
        that.setData({
          array: old
        })
        wx.getFileSystemManager().readFile({
          filePath: res.tempFilePaths[0], //选择图片返回的相对路径
          encoding: 'base64', //编码格式
          success: res => { //成功的回调
            // console.log('data:image/png;base64,' + res.data);  
            let old2 = that.data.array2;
            old2.push({ 'PicUrl': 'data:image/png;base64,' + res.data});//这里不管push什么，只要数组长度增加1就行
            that.setData({
              array2: old2
            })
          }
        })
      }
    })
  },
  star(e){
    if(e.currentTarget.dataset.starval == 1){
      this.setData({ star1: 'images/star1.png', star2: 'images/star22.png', star3: 'images/star33.png', Starnum: 5 })
    } else if (e.currentTarget.dataset.starval == 2){
      this.setData({ star2: 'images/star2.png', star1: 'images/star11.png', star3: 'images/star33.png', Starnum: 3 })
    } else if (e.currentTarget.dataset.starval == 3) {
      this.setData({ star3: 'images/star3.png', star1: 'images/star11.png', star2: 'images/star22.png', Starnum: 1 })
    }
  },
  issue(){
    let old2 = this.data.array2;
    old2.splice(0,1);
    let params = {
      url: 'api/Order/CommentProduct',
      data: { 
        OrderNo: this.data.ordernum,
        Content: this.data.Content, //评论内容
        PicList: old2,//"[{“PicUrl”:base64地址}]" ,//图片
        PicNo: dateFormat("YYYYmmddHHMMSS", new Date()), //图片id
        Pid: this.data.ProductId, //商品id
        Starnum: this.data.Starnum //几颗星 5很满意，3满意，1不满意 
      }
    };
    // console.log(params);
    http(params).then(res => {
      console.log(res);
      wx.navigateTo({
        url: '/pages/myOrder/myOrder'
      })
    }, rejected => {
      tips('服务器异常,请稍后重试！')
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