// pages/goodsDetails/goodsDetails.js
import {
    http,
    tips
} from '../../utils/util.js';

Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '', //商品id
        role:wx.getStorageSync('role'),
        showCurrent: 1,
        length: '',
        /* 头部banner图片 */
        goodsImgList: ['https://mall.qianjidaojia.com//upload/admin/2020-01-16/2020011618142020262s08514.jpg', 'https://mall.qianjidaojia.com//upload/admin/2020-01-16/2020011618142020262s08514.jpg', 'https://mall.qianjidaojia.com//upload/admin/2020-01-16/2020011618142020262s08514.jpg'],
        /* 购物车商品的数量 */
        isNum: false, //购物车右上角的数字是否显示
        goodsNumber: 0, //购物车里的数量
        /* 点击购物车 */
        isCart: '',
        animation: '',
        goodsName: '',
        stockNum: '',
        nowPrice: '',
        oldPrice: '',
        goodsImg: '',

        toastText: '', //弹窗内容
        isToast: false, //弹窗显示
        toastNum: 0, //弹窗监听次数

        goodsInfo: {},
        isAttention: false,
        estimateList: [], //评论

        /* 需要传到订单页的信息 */
        toOrderInfo: {}

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
      if (!wx.getStorageSync('userId') || !wx.getStorageSync('token')) {
        wx.navigateTo({ url: '/pages/login/login' })
      }
        console.log(options);
        this.setData({
            id: options.Id
        });
        this.queryGoodsList();
        this.querycartNum();

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
    /* 方法列表 */
    /* 顶部banner轮播图切换change方法 */
    swiperChange(e) {
        let source = e.detail.source;
        if (source == 'touch' || source == 'autoplay') {
            let current = e.detail.current;
            this.setData({
                showCurrent: current + 1
            });
        }
    },
    /* 点击加入购物车或立即购买传参数给购物车组件*/
    addCart(e) {
        console.log(e);
        this.setData({
            isCart: true,
            animation: 'riseToast',
            goodsName: e.currentTarget.dataset.proname,
            stockNum: e.currentTarget.dataset.prostock || 100000,
            nowPrice: e.currentTarget.dataset.proprice,
            oldPrice: e.currentTarget.dataset.prooldp,
            goodsImg: e.currentTarget.dataset.proimg,
            click: e.currentTarget.dataset.click,
            proId: this.data.id
        });
      this.selectComponent("#cart").findSpecification(this.data.id);
    },



    /* 商品详细信息 */
    queryGoodsList() {
        let params = {
            url: 'api/Goods/ProductInfo',
            data: {
                proId: this.data.id
            }
        };console.log(params)
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                /* 截取评论前两条 */
                let estimateList = res.data.EvaluateList.slice(0, 2);
                /* 评论图片是逗号分离的字符串，需转换成数组 */
                estimateList = estimateList.map((item) => {
                    if (item.EvaluateImgList) { //有图片
                        item.EvaluateImgList = item.EvaluateImgList.split(',');

                    } else { //没有图片
                        item.EvaluateImgList = [];
                    }
                    item.MemberName = item.MemberName.substring(0, 4) + "****" + item.MemberName.substring(8);
                    return item
                });
                /* 将手机号中间加密隐私 */
              let ContentDetail2 = (res.data.ContentDetail).replace(/<p([\s\w"=\/\.:;]+)((?:(style="[^"]+")))/ig, '<p')
                .replace(/<p>/ig, '<p style="display:flex;margin:0;padding:0;flex-direction:column;">')
                .replace(/<img([\s\w"-=\.:;])/ig,'<img$1 mode="widthFix" style="width: 100%;" ',)
                
                this.setData({
                    goodsInfo: res.data,
                    estimateList: estimateList,
                    length: res.data.ProductImgList.length,
                  ContentDetail: ContentDetail2
                });
              console.log(this.data.ContentDetail)
                this.queryattention(res.data.ShopId);
            }
            //console.log(this.data.goodsInfo);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 购物车数量 */
    querycartNum() {
        let params = {
            url: 'api/Cart/GetAllCartNumber',
            data: {}
        }
        http(params).then(res => {
            //console.log(res);
            if (res.code == 0 && res.data.AllNumber > 0) {
                this.setData({
                    isNum: true,
                    goodsNumber: res.data.AllNumber
                });
            }
            //console.log(this.data.goodsInfo);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 请求添加进购物车 */
    queryAddCart(count) {
        let params = {
            url: 'api/Cart/AddCart',
            data: {
                'ProId': this.data.id, //商品id
                'Count': count, //添加数量
                'SpecText': this.data.selectedSpec || '' //规格名称，没有规格传空
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    /* 弹窗 */
                    toastText: res.msg,
                    isToast: true,
                    toastNum: this.data.toastNum + 1
                });
                this.querycartNum();
            }
            //console.log(this.data.goodsInfo);
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 查看是否已关注商店 */
    queryattention(ShopId) {
        let params = {
            url: 'api/Shop/ShopIsCollect',
            data: {
                'ShopId': ShopId
            }
        }
        http(params).then(res => {
            //console.log(res);
            if (res.code == 0) {
                this.setData({
                    isAttention: true
                });
            } else if (res.code == 1) {
                this.setData({
                    isAttention: false
                });
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 点击关注、取消商店 */
    clickAttention() {
        let params = {
            url: 'api/Goods/ShopCollection',
            data: {
                'ShopId': this.data.goodsInfo.ShopId
            }
        }
        http(params).then(res => {
            //console.log(res);
            if (res.code == 0) {
                this.setData({
                    /* 弹窗 */
                    toastText: res.msg,
                    isToast: true,
                    toastNum: this.data.toastNum + 1
                });
                this.queryattention(this.data.goodsInfo.ShopId);
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 进入店铺页面 */
    intoShop(){
        wx.navigateTo({
            url: '/pages/shop/shop?shopId=' + this.data.goodsInfo.ShopId,
        })
    },
   specification(val){
     this.data.selectedSpec = val.detail;
   },
    /* 点击确定添加进购物车 */
    confirmAdd(e) {
        console.log(e);

        this.queryAddCart(e.detail.count);
    },
    /* 点击确定立即购买 */
    confirmBuy(e) {
        console.log(e);
        this.setData({
            toOrderInfo: {
                'count': e.detail.count,
                'proId': this.data.id,
                'SpecText': ''
            }
        });
        wx.navigateTo({
            // url: "/pages/submitOrder/submitOrder?toOrderInfo=" + JSON.stringify(this.data.toOrderInfo),
          url: "/pages/submitOrder/submitOrder?id=" + this.data.id + "&nowBuy=nowBuy&goodsNum=" + e.detail.count,
        });
    },
    /* 点击查看更多评价 */
    toLookAll(){
       wx.navigateTo({
           url: '/pages/estimate/estimate?proId='+this.data.id,
       }) 
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
      console.log(88888888888,wx.getStorageSync('SpreadCode'));
      // return {
      //   title: '钱记到家',
      //   desc: '',
      //   path: '/pages/login/login?SpreadCode=' + wx.getStorageSync('SpreadCode') // 路径，传递参数到指定页面。
      // }
      return {
        title: '钱记到家',
        desc: '',
        path: '/pages/goodsDetails/goodsDetails?Id='+this.data.id + '&SpreadCode=' + wx.getStorageSync('SpreadCode')
      }

    },
  copyText(){
    wx.setClipboardData({
      data: '/pages/goodsDetails/goodsDetails?Id=' + this.data.id,
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            tips('复制成功')
          }
        })
      }
    })
  }
})