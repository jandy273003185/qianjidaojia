// pages/promoteOrder/promoteOrder.js
import {
  wxCommon,
  _mobile,
  http, tips
} from '../../utils/util.js'

Page({

    /**
     * 页面的初始数据
     */
    data: {
        role: wx.getStorageSync('role'),//团长1，营长2
        search:'',
        timeList:[
            {
                time:"全部",
                parmas:''
            },
            {
                time: "今日",
                parmas: ''
            },
            {
                time: "昨日",
                parmas: ''
            },
            {
                time: "近七日",
                parmas: ''
            }
        ],
        isActive:0,
        active:'fontRed',//团长红色，营长蓝色
        searchbg:'searchRed',//搜索框背景色，默认团长红色
        warpHeight: "", //初始高度置空
        pageIndex:1,//第几页
        pageSize:20,//一页几条
        count: 0,//总记录数
        loadMoreFlag: false,
        listArr: [],
        Day: '',//全部 0：今日 1：昨日 7：近七天
        Tel:''//订单手机号

    },

    
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        if(this.data.role==2){
            this.setData({
                active: 'fontBlue',
                searchbg:'searchBlue'
            });
            
        }
      wxCommon.listApi.getSystemInfo((res) => {
        const height = res.platform == 'android' ? 560 : 180;
        const warpHeight = (res.windowHeight - Math.ceil(height / res.pixelRatio) || 560) + "px";
        this.setData({
          warpHeight: warpHeight
        })
      }, (er) => {
        this.setData({
          warpHeight: "560px"
        })
      });
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
      this.queryList();
    },
  onStatus(e) {//状态查询（全部。。。）
    let index = e.currentTarget.dataset.index;
    this.setData({
      isActive: index
    });
    let Day = '';
    if (index == 0) {
      Day = '';
    } else if (index == 1) {
      Day = '0';
    } else if (index == 2) {
      Day = '1';
    } else if (index == 3) {
      Day = '7';
    }
    // let startTimeStamp = this.data.timeStamp;
    // this.setData({ timeStamp: e.timeStamp });
    // if (e.timeStamp - startTimeStamp > 350) {
    this.setData({
      listArr: [],
      Day: Day
    });
    // } 
    this.queryList();
  },
  queryList() {
    let params = {
      url: 'api/User/getGroupOffineOrderInfo',
      data: {
        Day: this.data.Day, //””：全部 1：今日 2：昨日 7：近七天
        PageIndex: this.data.pageIndex, //当前第几页
        PageSize: this.data.pageSize, //一页显示几条数据
        Tel: this.data.Tel, //手机号
      }
    }
    console.log(params)
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
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  loadMore() {//分页
    if (this.data.listArr.length >= this.data.count) { return };
    let page = this.data.page;
    ++page;
    this.setData({ loadMoreFlag: true, page });
    this.queryList();
  },
  inputListener(e) {
    this.setData({ Tel: e.detail.value })
  },
  /* 搜索确定时 */
  search() {
    this.setData({
      // isShow: false,//隐藏热门搜索及历史提示
      isResult: true,//显示搜索结果
      listArr: [],
      page: 1,
      pageSize: 20
    });
    this.queryList();
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


})