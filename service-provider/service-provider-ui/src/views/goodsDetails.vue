<template>
  <div class="sales">
    <van-nav-bar
      :title="title"
      left-text="返回"
      left-arrow
      @click-left="changePrepage"
    >
    </van-nav-bar>
    <!-- <van-tabs v-model="tabActive"  title-active-color="#699dd7" color="#699dd7" @click="onClick" > -->
        <div class="total">
          <div class="total-title">{{dateTitle}}经营数据</div>
          <div class="total-content">
            <div><p>{{this.$route.params.transactionAmount}}</p><p>交易额(元)</p></div>
            <div><p>{{this.$route.params.transactionNum}}</p><p>交易笔数(笔)</p></div>
          </div>
          <div class="total-content">
            <div><p>{{this.$route.params.refundAmount}}</p><p>退款额(元)</p></div>
            <div><p>{{this.$route.params.refundCount}}</p><p>退款笔数(笔)</p></div>
          </div>
        </div>
        <van-list
                v-model="loading"
                :finished="finished"
                finished-text="暂无更多数据"
                @load="loadList"
        >
          <div class="list" v-for="(item, index) in goodsList" :key="index">
            <div class="list-left">
              <div>
                <img width="30" :src="item.channel | payImg" alt="">
              </div>
              <div>
                <p>{{item.channel | payType}}</p>
                <p>{{item.finishTime}}</p>
              </div>
            </div>
            <div class="list-right">
              <div>
                <p>￥{{item.tradeAmt}}</p>
                <p :class="{'redColor':item.orderState == '0'}">{{item.orderState | payState}}</p>
              </div>
              <!-- <div>
               <van-icon name="arrow" />
             </div> -->
            </div>
          </div>
        </van-list>
  </div>
</template>
<script>
import { goodsInfo } from "@/assets/api/interface";
/*import { Dialog,RadioGroup, Radio  } from "vant";*/
import { mapState } from "vuex";

export default {
  name: "sales",
  components: {},
  data() {
    return {
      /*分页加载*/
      loading:false,
      finished:false,

      title:'',
      dateTitle:this.$route.params.queryStartDate?this.$route.params.queryStartDate +'至'+ this.$route.params.queryEndDate:'全部',
      goodsList: [],
      lastTime:''
    };
  },
  computed: {
    ...mapState(["userId"])
  },
  created() {
    this.title = this.$route.params.custName;
    console.log(this.userId);
  },
  mounted(){},
  methods: {
    async getInitList() {
      let params = {
        mchId: this.$route.params.custId,
        queryStartDate:this.$route.params.queryStartDate,
        queryEndDate:this.$route.params.queryEndDate,
        lastTradeTime:this.lastTime
      };
      console.log(params);
      let list = await goodsInfo.goodsDetails(params);
      this.loading = false;
      console.log(list);
      let newList=list.data.data;
      
      if(list.data.code == 200){
        if(newList.length>0){
          this.lastTime=newList[newList.length-1].lastTradeTime;
          this.goodsList=this.goodsList.concat(newList);
        }else {
          this.finished=true;
        }
      }else if(list.data.code == 500){
          this.finished=true;
      }
      
    },
    changePrepage() {
      //;返回
      this.$router.go(-1);
    },
    //按时间分页加载
    loadList(){
      this.getInitList();
    }
  },
  filters:{
    payType(payTxt){
      if(payTxt == 'wx'){
        return '微信支付'
      }else if(payTxt == 'alipay'){
        return '支付宝'
      }else if(payTxt == 'unionpay'){
        return '银联支付'
      }
    },
    payState(val){
      if(val == '0'){
        return '退款成功';
      }else if(val == '1' || val == '2'){
        return '退款中'
      }else if(val == '9'){
        return '退款失败'
      }else if(val == '01'){
        return '待支付'
      }else if(val == '02'){
        return '支付处理中'
      }else if(val == '00'){
        return '支付成功'
      }else if(val == '09'){
        return '支付失败'
      }else if(val == '99'){
        return '取消'
      }
    },
    payImg(val){
      if(val == 'wx'){
        return require('../assets/images/wx.png');
      }else if(val == 'alipay'){
        return require('../assets/images/ali.png');
      }else if(val == 'unionpay'){
        return require('../assets/images/yinlian.png');
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../style/views/mine.scss";
.rowTitle{
  font-size: 16px;
  color: #999999;
}
.van-search .van-cell{
  background-color: #efefef;
  border-radius: 6px;
  padding-left: 5px;
}
.van-search__action{
  color: #3f9aff;
}
.van-radio-group{
  display: inline-flex;
  margin-left: 10px;
}
.van-radio{
  margin-right: 14px;
}
.item{
  display: flex;
  align-items: center;
}
.sales .row{
  padding-left: vw(35);
  // height: vw(85);
  // line-height: vw(85);
}
.loaded{
  padding: vw(22);
  color: #999999;
}
select{
  width:vw(150);
  text-align: center;
  text-align-last: center;
}


.total{
  display: flex;
  background-color: #ffffff;
  flex-direction: column;
  margin: vw(30);
  padding: vw(20) vw(20) 0 vw(20)
}
.total-title{
  font-size: vw(28);
  color: #666666;
  line-height: vw(60);
}
.total-content{
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #f8f6f6;
}
.total-content>div{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 50%;
  padding: vw(20);
}
.total-content>div>p:first-child{
  font-size: vw(36);
  color: #333333;
}
.total-content>div>p:last-child{
  font-size: vw(24);
  color: #999999;
}

.list{
  display: flex;
  justify-content: space-between;
  padding: vw(10) vw(20);
  margin:0 vw(30);
  background-color: #ffffff;
  border-bottom: 1px solid #f8f6f6;
}
.list-left,.list-right{
  display: flex;
}
.list-left>div,.list-right>div{
  display: flex;
  flex-direction: column;
  margin-right: vw(10);
}
.list-right>div:last-child{
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: vw(20);
}
.list-left>div:last-child>p:first-child, .list-right>div:first-child>p:first-child{
  font-size: vw(30);
  color: #333333;
}
.list-left>div:last-child>p:last-child, .list-right>div:first-child>p:last-child{
  font-size: vw(26);
  color: #999999;
}
.redColor{
  color: #bb3500 !important;
}
</style>