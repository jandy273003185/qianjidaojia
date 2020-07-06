// pages/estimate/estimate.js
import {http,tips, wxCommon} from '../../utils/util.js';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        /* 查看按钮 */
        btnList: [{
                name: "全部",
                idx: 0
            },
            {
                name: "好评",
                idx: 1
            }, {
                name: "中评",
                idx: 2
            },
            {
                name: "差评",
                idx: 3
            }
        ],
        isActive:0,
        selected:'selected',
        listArr: [],
        warpHeight: "", //初始高度置空
        page: 1,//页码
        pageSize: 20,
        count: 0,//总记录数
        loadMoreFlag: false
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
      this.data.proId = options.proId;
      wxCommon.listApi.getSystemInfo((res) => {
        const height = res.platform == 'android' ? 560 : 20;
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
  selectLook(e) {
    this.setData({
      isActive: e.currentTarget.dataset.index
    });
    // console.log(this.data.isActive);
    this.queryList() ;
  },
  queryList() {
    let params = {
      url: 'api/Goods/GetEvaluate',
      data: {
        grade: this.data.isActive  ,//0:全部 1：好评 2：中评 3：差评
        proId:  this.data.proId, //商品id
        page: this.data.page,//当前第几页
        pageSize: 20,//一页显示几条数据
      }
    }; console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        let arr = this.data.listArr;
        if (!this.data.loadMoreFlag) { arr = []; this.setData({ loadMoreFlag: false }); }
        arr = arr.concat(res.data.EvaluateList);
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
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
    
})