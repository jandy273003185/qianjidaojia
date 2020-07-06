// pages/index/index.js
import {
    http,
    tips
} from '../../utils/util.js';


const APP = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        /* 用户角色 团长1 营长2 普通会员0 */
        role: 0,
        /* 字体图标颜色 */
        iconColor: '#333',
        /* 头部横向banner图片 */
        indicatorDots: true,
        indicatorColor: 'rgba(0,0,0,0.2)',
        indicatorActiveColor: "#007aff",
        autoplay: true,
        interval: 2000,
        duration: 500,
        circular: true,
        bannerList: [],
        /* 顶部介绍 */
        introduceList: ['../../images/index/introduce1.png', '../../images/index/introduce2.png', '../../images/index/introduce3.png', '../../images/index/introduce4.png'],
        /* 纵向文字轮播 */
        noticeList: [],
        /* 钱记精选 */
        selectionList: [],

        /* 厨房宝典等分类商品 */
        homeGoodsList: [],

        /* 进入时优惠券弹窗 */
        istoast: true,
        imgList: {
            "bg": "../../images/index/bg.png",
            "toastgoods": "../../images/index/goodstoast.png",
            "discount_coupon": "../../images/index/discount_coupon.png"
        },
        /* 团长入口图标位置 */
        x: 400,
        y: 300,

        /* 点击购物车 */
        isCart: '',
        animation: '',
        goodsName: '',
        stockNum: '',
        nowPrice: '',
        oldPrice: '',
        goodsImg: '',
       ProductSpecList:[],
       moveImg:''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        
    },
   groupUserLevel() {//0代表普通用户，1代表团长，2代表营长
     const params = {
       url: 'api/User/GroupUserLevel'
     }
     http(params).then(res => {
       console.log(res);
       wx.setStorageSync(
         'role',res.data 
       );
       if (res.code == 0) {
         this.setData({ role: res.data});
         if (res.data == 1) { 
           this.setData({ moveImg:'../../images/index/regimentalInto.png'})
         } else if (res.data == 2){
           this.setData({ moveImg: '../../images/index/regimentalInto2.png' })
         }
         this.queryBannerList();
         this.queryNoticeList();
         this.querySelectionList();
         this.queryGoodsList(); //商品分类
       }else{
         tips(res.msg)
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
      wx.hideTabBar();
      this.groupUserLevel();
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
      return {
        title: '钱记到家',
        desc: '',
        path: '/pages/login/login?SpreadCode=' + wx.getStorageSync('SpreadCode') // 路径，传递参数到指定页面。
      }
    },

    /* 关闭首页弹窗 */
    closeToast() {
        this.setData({
            istoast: false
        });
    },
    /* 进入商品详情 */
    toDetails(e) {
      console.log(e);
      if (e.currentTarget.dataset.id){
        wx.navigateTo({
          url: "/pages/goodsDetails/goodsDetails?Id=" + e.currentTarget.dataset.id,
        })
      } 
    },
    
    /* 请求头部banner图片 */
    queryBannerList() {
        let params = {
            url: 'api/Banner/BannerList',
            data: {
                type: 1
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    bannerList: res.data
                });
            }
            //console.log(this.data.bannerList);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 通知消息 */
    queryNoticeList() {
        let params = {
            url: 'api/News/NoticeList',
            data: {
                type: 0,
                page: 1,
                pagesize: 20
            }
        }
        http(params).then(res => {
            //console.log(res);
            if (res.code == 0) {
                this.setData({
                    noticeList: res.data
                });
            }
            //console.log(this.data.noticeList);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 精选商品 */
    querySelectionList() {
        let params = {
            url: 'api/Goods/GetAdInfoById',
            data: {
                cid: 10
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    selectionList: res.data
                });
            }
            //console.log(this.data.selectionList);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        });
    },
    /* 厨房宝典、餐后美食、特色美食等等分类商品 */
    queryGoodsList() {
        let params = {
            url: 'api/Goods/GetProductListByCids',
            data: {}
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    homeGoodsList: res.data
                });
            }
            //console.log(this.data.homeGoodsList);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 进入团长或者营长个人中心 */
    intoCentre() {
        wx.navigateTo({
            url: "/pages/regimentalInfo/regimentalInfo",
        })
    },
  
  /* 加入购物车 */
  addCart(e) {
    console.log(e);
    const proId = e.currentTarget.dataset.proid;
    this.setData({
      isCart: true,
      animation: 'riseToast',
      goodsName: e.currentTarget.dataset.proname,
      stockNum: e.currentTarget.dataset.prostock || 100000,
      nowPrice: e.currentTarget.dataset.proprice,
      oldPrice: e.currentTarget.dataset.prooldp,
      goodsImg: e.currentTarget.dataset.proimg,
      proId: proId
    });
    this.selectComponent("#cart").findSpecification();
  },
  cartNum() {
    this.selectComponent("#tabbar").querycartNum();
  }
})