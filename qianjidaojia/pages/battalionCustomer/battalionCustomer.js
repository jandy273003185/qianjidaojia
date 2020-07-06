// pages/battalionCustomer/battalionCustomer.js
import {
    wxCommon,
    _mobile,
  http, tips, getBase64ImageUrl
} from '../../utils/util.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        /* 团长与营长颜色显示 */
      role: wx.getStorageSync('role'),//团长1，营长2
        bgColor: 'bgRed',
        /* 备注弹窗 */
        isModal:false,
        isModal2: false,
        /* 客户是否是营长 */
        statusArr: [{
                id: 0,
                name: '全部'
            },
            {
                id: 1,
                name: '今日'
            },
            {
                id: 2,
                name: '昨日'
            },
            {
                id: 3,
                name: '近7日'
            },
        ],
        statusList: [{
                id: 0,
                name: '全部'
            },
            {
                id: 1,
                name: '营长'
            },
        ],
        idx: 0,
        /* stateCode: '', */
      warpHeight: "", //初始高度置空
      page: 1,//页码
      pageSize: 20,
      count: 0,//总记录数
      loadMoreFlag: false,
      listArr: [],
      remarkHidden: true,
      tel:'',
      Day:'',
      GroupLevel:'',
      Id:'',
      code:''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        if (this.data.role == 2) {
            this.setData({
                bgColor: 'bgBlue'
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
  queryList() {
    let params = {
      url: 'api/User/getGroupOffineUserInfo',
      data: {
        PageIndex: this.data.page,
        PageSize: 20,
        Day: this.data.Day, //””：全部 0：今日 1：昨日 7：近七天
        Tel: this.data.tel, //手机号
        GroupLevel: this.data.GroupLevel
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
    this.setData({ tel: e.detail.value })
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
    onStatus(e) { //状态查询（全部。。。）
        let index = e.currentTarget.dataset.index;
        this.setData({
            idx: index
        });
      let Day = '',GroupLevel='';
        if (index == 0) {
          Day = '';
          GroupLevel='';
        } else if (index == 1) {
          Day = '';
          GroupLevel = '2';
        } else if (index == 2) {
          Day = '2';
        } else if (index == 3) {
          Day = '7';
        } 
        // let startTimeStamp = this.data.timeStamp;
        // this.setData({ timeStamp: e.timeStamp });
        // if (e.timeStamp - startTimeStamp > 350) {
          this.setData({
            listArr: [],
            Day: Day,
            GroupLevel: GroupLevel,
            page: 1
          });
        // } 
      this.queryList();
    },
  inputRemark(e) { this.setData({ GroupRemark:e.detail.value})},
  confirm(e){//修改备注
    let params = {
      url: 'api/User/UpdGroupRemark',
      data: {
        Id: this.data.Id,  //用户下级主键id
        GroupRemark: this.data.GroupRemark //备注内容
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.onShow();
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  setBattalion(e){//设为营长
    this.getCode();
    this.setData({
      isModal2: true,
      MemberId: e.currentTarget.dataset.memberid
    });
  },
  getCode(){
    let params = {
      url: 'api/User/GetVilidCode'
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          mathResult: res.data.mathResult,
          vImg: getBase64ImageUrl(res.data.vImg)
        })
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  inputCode(e){this.setData({code:e.detail.value})},
  confirm2(e) {//确认设为营长
    this.setData({
      isModal2: true
    });
    if (this.data.code != this.data.mathResult){ tips('请正确输入运算结果!'); return;}
    let params = {
      url: 'api/User/SetTgroupuser',
      data: {
        MemberId: this.data.MemberId,  //用户下级主键id
      }
    }
    console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        this.setData({
          isModal2: false
        });
        this.onShow();
      } else {
        tips(res.msg);
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
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
      this.queryList();
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
    /* 弹窗修改备注名称 */
    ament(e){
        this.setData({
            isModal:true,
            Id:e.currentTarget.dataset.id
        });
    },
  
    concel(e){
       console.log(e); 
    },
    /* 前往营长详情 */
  toBattalion(e) {
    if (e.currentTarget.dataset.grouplevel == 2){
        wx.navigateTo({
            url: '/pages/battalionDetails/battalionDetails?id='+e.currentTarget.dataset.id
        })
    }
  }
})