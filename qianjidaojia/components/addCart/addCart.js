// components/addCart/addCart.js
import {
  tips, http
} from '../../utils/util.js';
Component({
    /**
     * 组件的属性列表
     */
    properties: {
      animation:{
          type: 'string',
          value: ''
      },
      goodsName: {
          type: 'string',
          value: ''
      },
      stockNum: {
          type: 'number',
          value: ''
      },
      nowPrice: {
          type: 'number',
          value: ''
      },
      oldPrice: {
          type: 'number',
          value: ''
      },
      goodsImg: {
          type: 'string',
          value: ''
      },
      isCart:{
          type:'boolean',
          value:false
      },
      click:{
          type:'string',
          value:'add'
      },
      proId: {
        type: 'number',
        value: ''
      },
      flag: {
        type: 'boolean',
        value: false
      },
      ProductSpecList:{
        type:'array',
        value:[]
      }

    },

    /**
     * 组件的初始数据
     */
    data: {
        /* 购物车商品的数量 */
        addNumber: 1,//购买数量
        animation: '',//点击加入购物车动画

        toastText: '',//弹窗内容
        isToast: false,//弹窗显示
        toastNum: 0,//弹窗监听次数
        active:'',
    },
    /**
     * 组件的方法列表
     */
    methods: {
        /* 减少购买数量 */
        reduce() {
            let currentNum = this.data.addNumber;
            if (currentNum > 1) {
                this.setData({
                    addNumber: currentNum - 1
                });
            }
        },
        /* 增加购买数量 */
        add() {
            let currentNum = this.data.addNumber;
            if (currentNum < this.properties.stockNum) {//小于库存数量才能继续增加
                this.setData({
                    addNumber: currentNum + 1
                });
            } else {
                this.setData({
                    toastText: '超出库存数量',
                    isToast: true,
                    toastNum: this.data.toastNum + 1
                });
            }
        },
        /* 点击加入购物车 
        addCart() {
            this.setData({
                animation: 'riseToast'
            });
        },*/
        // 提交至购物车
        submitCart(){
          let params = {
            url: 'api/Cart/AddCart',
            data:{
              ProId: this.data.proId, //商品id
              Count: this.data.addNumber, //添加数量
              SpecText: this.data.selectedSpec||''//“超辣” //规格名称，没有规格传空
            }
          }; console.log(params);
          http(params).then(res => {
            tips(res.msg);
            if (res.code == 0) {
              this.triggerEvent('callSomeFun');
              this.triggerEvent('cartNum');
              console.log(res);
              this.setData({
                addNumber: 1,
              });
            }
          }, rejected => {
            tips('服务器异常,请稍后重试！')
          })
        },
        /* 确定商品放入购物车 */
        confirm() {
          if (this.data.ProductSpecList.length>0){
            if (!this.data.selectedSpec){tips('请选择商品规格!');return;}
          }else{
            this.setData({ selectedSpec:''})
          }     
          if (this.properties.click == "add") {
                this.triggerEvent("confirmAdd", { 'count': this.data.addNumber });
                this.setData({
                  isCart: false,
                  addNumber: this.data.addNumber,
                  animation: 'dropToast',
                });
            if (this.data.flag) { return;}
              this.submitCart();
            } else if (this.properties.click == "buy") {                
                this.triggerEvent("confirmBuy", { 'count': this.data.addNumber });
                this.setData({
                  isCart: false,
                  addNumber: this.data.addNumber,
                  animation: 'dropToast',
                });
                
            }           
        },
        /* 点击空白时关闭购物车 */
        close(){
            this.setData({
                isCart: false,
                addNumber: 1,
                animation: 'dropToast',
            });
        },
      findSpecification(proId) {//查规格
        this.setData({selectedSpec: ''});
        let params = {
          url: 'api/Goods/ProductInfo',
          data: { proId: proId||this.data.proId }
        };console.log(params)
        http(params).then(res => {
          console.log(res);
          if (res.code == 0) {
            this.setData({ ProductSpecList: res.data.ProductSpecList });
          }
          //console.log(this.data.homeGoodsList);
        }, rejected => {
          tips('服务器异常,请稍后重试！')
        })
      },
      specification(e){
        this.setData({ 
          selectedSpec: e.currentTarget.dataset.spectext,
          stockNum: e.currentTarget.dataset.prostock,
          nowPrice: e.currentTarget.dataset.punitprice,
          
        })
        this.triggerEvent('myevent', e.currentTarget.dataset.spectext);
      }

    }
})
