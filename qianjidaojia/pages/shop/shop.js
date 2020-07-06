// pages/shop/shop.js
import {
    http,
    tips
} from '../../utils/util.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isActive: false,
    searchHide:true,
    sideNav: [{ 'name': '秒杀' }, { 'name': '直降' }, { 'name': '减运专区' }],
    acitveSideIdx: 0,
    topNav: [{ 'name': '全部商品' }, { 'name': '减运专区' }],
    topActiveIdx: 0,
    goods: [],
      carList: [],
      toastText: '', //弹窗内容
      isToast: false, //弹窗显示
      toastNum: 0, //弹窗监听次数
      focus:'关注',//是否关注
      shopId:'',
      shopInfo:{},//店铺信息
      productType:'',//分类id
      // page:'',//第几页
    searchKeyword:'',

    allselected: false,
    allnum: 0,
    allprices: 0,
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        shopId:options.shopId
      });
      this.data.flag=true;
      this.queryattention();
      this.queryProductClass();
      this.queryShopInfo();
      this.carList();
     
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
  showCarList() {//购物车列表弹窗
    this.setData({
      isActive: !this.data.isActive
    }); 
  },
  changeSideNav(e) {//侧边分类导航
    const theIdx = e.target.dataset.idx;
    this.setData({
      acitveSideIdx: theIdx,
        productType: e.target.dataset.producttype,
        // page:1,
        goods:[]
    });
      this.queryProductList();
  },
  changeTopNav(e) {//头部分类导航
    const theIdx = e.target.dataset.idx;
    this.setData({
      topActiveIdx: theIdx
    })
  },
  
    /* 查看是否已关注商店 */
    queryattention() {
        let params = {
            url: 'api/Shop/ShopIsCollect',
            data: {
                'ShopId': this.data.shopId
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    focus: '已关注'
                });
            } else if (res.code == 1) {
                this.setData({
                    focus: '关注'
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
                'ShopId': this.data.shopId
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
                this.queryattention(this.data.shopId);
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 获取店铺分类列表 */
    queryProductClass() {
        let params = {
          url: 'api/Shop/shopProductTypeList',
            data: {
                'ShopId': this.data.shopId
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    sideNav:res.data,
                    productType: res.data[0].Id,
                    // page:1
                });
                this.queryProductList();
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
  search() { this.setData({ goods:[]}); this.queryProductList()},
  inputListener(e) { this.setData({ searchKeyword: e.detail.value })},
    /* 获取店铺商品分类对应的商品列表 */
    queryProductList() {
        let params = {
          url: 'api/Shop/shopProductByType',
            data: {
              ShopId: this.data.shopId, //店铺id 经Encrypt2加密
              productType: this.data.productType, //产品分类id
              searchKeyword: this.data.searchKeyword,//搜索关键词
              // page: this.data.page,
              // pageSize:20,
                
            }
        };console.log(params)
        http(params).then(res => {
            console.log(res);            
            if (res.code == 0) {
                if(res.data.length>0){
                    let goods = this.data.goods.push(...res.data);
                    this.setData({
                        goods: this.data.goods,
                        // page: this.data.page + 1
                    });
                }
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
    /* 获取店铺信息 */
    queryShopInfo() {
        let params = {
            url: 'api/Shop/GetMerchantDetail',
            data: {
                'ShopId': this.data.shopId
            }
        }
        http(params).then(res => {
            console.log(res);
            if (res.code == 0) {
                this.setData({
                    shopInfo: res.data
                });
                wx.setNavigationBarTitle({
                    title: res.data.ShopNick
                });
            }
        }, rejected => {
            tips('服务器异常,请稍后重试！')
        })
    },
  iconadd(e){
    let params = {
      url: 'api/Cart/AddCart',
      data: {
        ProId: e.currentTarget.dataset.proid, //商品id
        Count: 1, //添加数量
        SpecText: this.data.selectedSpec || '',//规格名称，没有规格传空
        shopId:  e.currentTarget.dataset.shopid //店铺id
      }
    };console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        tips(res.msg);
        this.data.flag = true;
        this.carList();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  carList(){//购物车列表
    this.setData({ flagAllselected:true})
    let params = {
      url: 'api/Shop/shopCartList',
      data: {
        ShopId: this.data.shopId //店铺id
      }
    }; console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 0) {
        if (res.data.list){
          this.setData({
            allnum: res.data.count,
            allprices: res.data.totalAmount,
            carList: res.data.list
          })
          this.tapallallprices();
        }else{
          this.setData({
            allnum: 0,
            allprices: 0,
            isActive: false,
            carList: []
          })
        }
        
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },

  ////////////////////////////////////
  //计算总价格  所有选中商品的 （价格*数量）相加  
  getallprices: function () {
    let carList = this.data.carList
    //购物车数据    
    let allprices = 0
    let allnum = 0
    for (let i = 0; i < carList.length; i++) {
      if (carList[i].selected) {      //当前商品价格*数量 +          
        let price = Number(carList[i].Price)
        let num = parseInt(carList[i].Number) //防止num为字符 *1或parseInt Number          
          allprices += price * num
          allnum += num
        }
    }
    //跟新已选数量    
    this.setData({
      allnum: allnum,
      allprices: allprices.toFixed(2)
    })
    this.data.flag = false;
    this.data.flagAllselected = false;
  },

  //全选条件 条件->商铺全选择全选 反之  all
  allallprices: function () {
    let carList = this.data.carList
    let storenum = carList.length;
    let allselected = this.data.allselected
    let allselectednum = 0;
    for (let i = 0; i < carList.length; i++) {
      if (carList[i].selected == true) {
        allselectednum++
      }
    }
    if (storenum == allselectednum) {
      allselected = true
    } else {
      allselected = false
    }
    this.setData({ allselected: allselected })
    this.getallprices();
  },
  //全选按钮点击 
  tapallallprices: function () {
    let allselected = this.data.allselected
    let carList = this.data.carList //购物车数据   
    if (allselected) {
      allselected = false
    } else {
      allselected = true
    }
    //选择  
    if (this.data.flag) { allselected = true };
    if (this.data.flagAllselected) { allselected = true };
    for (let i = 0; i < carList.length; i++) {
      carList[i].selected = allselected
    }
    this.setData({
      carList: carList, //店铺下商品的数量     
      allselected: allselected
    })
    this.getallprices();
  },

  // 商品的选中  
  goodsselected: function (e) {
    let carList = this.data.carList //购物车数据   
    let index = e.currentTarget.dataset.index;
    if (carList[index].selected){
      carList[index].selected = false; 
    }else{
      carList[index].selected = true; 
    }
      // 更新   
    this.setData({
      carList: carList //店铺下商品的数量   
    })
    this.getallprices();
    this.allallprices();
  },
  // 点击+号，num加1，点击-号，如果num > 1，则减1  
  addCount: function (e) {
    let carList = this.data.carList //购物车数据   
    let index = e.currentTarget.dataset.index;  
    let num = carList[index].Number; //当前商品的数量    
    num++;
    carList[index].Number = num //点击后当前店铺下当前商品的数量   
    let params = {
      url: 'api/Cart/AddCart',
      data: {
        ProId: e.currentTarget.dataset.productid, //商品id
        Count: 1, //添加数量
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {
        tips('库存不足！');
      } else if (res.code == 0) {
        this.setData({
          carList: carList //店铺下商品的数量  
        })
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
    //计算总价格    
    this.getallprices();
  },

  minusCount: function (e) {
    let carList = this.data.carList //购物车数据   
    let index = e.currentTarget.dataset.index;
    let num = carList[index].Number; //当前商品的数量    
    if (num <= 1) {
      let params = {
        url: 'api/Cart/DelCartXQ',
        data: {
          CartIdList: e.currentTarget.dataset.id, //商品id
        }
      }; console.log(params)
      http(params).then(res => {
        console.log(res);
        if (res.code == 0) {
          tips('删除成功');
          this.carList();
        } else {
          tips('服务器异常,请稍后重试！')
        }
      }, rejected => {
        tips('服务器异常,请稍后重试！')
      })
      return false;
    }
    num--;
    carList[index].Number = num //点击后当前店铺下当前商品的数量   
    let params = {
      url: 'api/Cart/EditCartXQ',
      data: {
        CartId: e.currentTarget.dataset.id, //商品id
        Count: num, //添加数量
      }
    }
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {

      } else if (res.code == 0) {
        this.setData({
          carList: carList //店铺下商品的数量  
        })
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
    this.getallprices();
  },
  delAll() {
    let arr = [];
    for (let i = 0; i < this.data.carList.length; i++) {
      if (this.data.carList[i].selected) {
        arr.push(this.data.carList[i].Id);
      }
    }
    let params = {
      url: 'api/Cart/DelCartXQ',
      data: {
        CartIdList: arr.toString(), //商品id
      }
    }; console.log(params)
    http(params).then(res => {
      console.log(res);
      if (res.code == 1) {
        tips('请选择删除的商品！')
      } else {
        tips('删除成功');
        this.carList();
      }
    }, rejected => {
      tips('服务器异常,请稍后重试！')
    })
  },
  settlement() {//结算
    let arr = [];
    for (let i = 0; i < this.data.carList.length; i++) {
      if (this.data.carList[i].selected) {
        arr.push(this.data.carList[i].Id);
      }
    }
    if (arr.length < 1) { tips('请选择商品！'); return; }
    wx.navigateTo({
      url: '/pages/submitOrder/submitOrder?id=' + arr.toString() + '&allprices=' + this.data.allprices + '&allnum=' + this.data.allnum,
    })
  },
  /* 加入购物车 */
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
      proId: e.currentTarget.dataset.proid,
    });
    this.selectComponent("#cart").findSpecification();
  },
  onPageScroll: function (e) {
    this.setData({
      scrollTop: e.scrollTop
    })
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
      console.log('已经触底');

      // this.queryProductList();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})