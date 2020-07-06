// components/tabbar/tabbar.js
import{http,tips} from '../../utils/util.js';
const APP = getApp();
Component({
    /* 组件的属性列表 */
    properties:{

    },
    /**
     * 页面的初始数据
     */
    data: {
        cartNum:0,
        isIphoneX:APP.globalData.isIphoneX,
        tabBar:{
            "color":"#333",
            "selectedColor":"#09B007",
            "borderStyle":"white",
            "list":[
                {
                    "selectedIconPath":"/images/tabbar/index.png",
                    "iconPath": "/images/tabbar/index1.png",
                    "pagePath":"/pages/index/index",
                    "text":"首页"
                },
                {
                    "selectedIconPath": "/images/tabbar/classify.png",
                    "iconPath": "/images/tabbar/classify1.png",
                    "pagePath": "/pages/classify/classify",
                    "text": "全部商品"
                },
                {
                    "selectedIconPath": "/images/tabbar/shoppingCart.png",
                    "iconPath": "/images/tabbar/shoppingCart1.png",
                    "pagePath": "/pages/shoppingCart/shoppingCart",
                    "text": "购物车"
                },
                {
                    "selectedIconPath": "/images/tabbar/my.png",
                    "iconPath": "/images/tabbar/my1.png",
                    "pagePath": "/pages/my/my",
                    "text": "我的"
                }
            ]

        }
    },
    /* 组件的生命周期函数 */
    lifetimes:{
        attached:function(){
          this.editTabBar();
          this.querycartNum();
        }
    },
    /* 组件的方法列表 */
    methods:{
        editTabBar:function(){
            let currentPage = getCurrentPages();//当前路由信息 
            let pagePath = currentPage[currentPage.length-1].route;//如首页路由为pages/index/index
            (pagePath.indexOf("/") != 0) && (pagePath = "/" + pagePath);//将路由前面添加上“/”
            let tabBar = this.data.tabBar;
            for(let i in tabBar.list){
                tabBar.list[i].selected = false;
                (tabBar.list[i].pagePath == pagePath)&&(tabBar.list[i].selected = true);

            }
            this.setData({
                tabBar:tabBar
            });
        },
      /* 购物车数量 */
      querycartNum() {
        let params = {
          url: 'api/Cart/GetAllCartNumber'
        }
        http(params).then(res => {
          console.log(res);
            this.setData({
              cartNum: res.data.AllNumber
            });
        }, rejected => {
          tips('服务器异常,请稍后重试！')
        })
      },
    },
})