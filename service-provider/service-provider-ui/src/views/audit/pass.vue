<template>
  <div class="incoming">
    <van-nav-bar title="审核信息" left-text="返回" left-arrow @click-left="changePrepage" />
    <van-tabs v-model="infoType" color="#699dd7" swipeable>
      <van-tab title="商户基本信息">
        <div class="stepInfo" ref="baseform">
          <div class="row">
            <span class="label">商户编号</span>
            <input v-model="allInfoList.merchantCode" readonly />
          </div>
         <!--  <div class="row">
            <span class="label">商户账号</span>
            <input v-model="allInfoList.merchantAccount" readonly />
          </div> -->
          <div class="row">
            <span class="label">商户类型</span>
            <select v-model="allInfoList.custType" disabled>
              <option value="">--</option>
              <option value="0">个人</option>
              <option value="1">企业</option>
            </select>
          </div>
          <div class="row">
            <span class="label">商户名称</span>
            <input v-model="allInfoList.custName" readonly />
          </div>
          <div class="row">
            <span class="label">商户简称</span>
            <input v-model="allInfoList.shortName" readonly />
          </div>
          <div class="row">
            <span class="label">商户地址</span>
            <input v-model="allInfoList.custAdd" readonly />
          </div>
          <div class="row">
            <span class="label">营业执照编号</span>
            <input v-model="allInfoList.businessLicense" readonly />
          </div>
          <div class="row">
            <span class="label">营业执照截止日期</span>
            <input v-model="allInfoList.businessTermEnd" readonly />
          </div>
        </div>
      </van-tab>
      <van-tab title="商户产品签约">
        <div class="stepInfo">
          <div class="signed contract">
            <div class="signTit">已签约产品</div>
            <div v-for="(item,idx) in alreadyProList" :key="'index_0'+ idx" >
              <div v-if="item.productStatus=='00'">
                <div class="row1">
                  <van-checkbox class="check" v-model="checked" disabled>{{item.productName}}</van-checkbox>
                  <!--<van-checkbox class="check" v-if="item.productId==9 && item.productStatus=='00' " v-model="checked" disabled>蜻蜓产品</van-checkbox>-->
                  <!--<van-checkbox class="check" v-if="item.productId==2 && item.productStatus=='00' " v-model="checked" disabled>app产品</van-checkbox>
                  <van-checkbox class="check" v-if="item.productId==1 && item.productStatus=='00' " v-model="checked" disabled>扫码产品</van-checkbox>-->
                  <span class="name">结算费率：</span>
                  <input class="alreadyInput" type="text" v-model="item.productRate" readonly />
                  <span class="unit">%</span>
                </div>
                <!--<div class="sn" v-if="item.productId==9">
                  <span>SN:</span>
                  <input type="text" v-model="item.sn"  disabled />
                </div>-->
              </div>
            </div>
            <div class="signTit">待审核产品</div>
            <div v-for="(item,idx) in alreadyProList" :key="'index_1'+idx" >
              <div v-if="item.productStatus=='01'">
                <div class="row1" >
                  <van-checkbox class="check" v-model="checked" disabled>{{item.productName}}</van-checkbox>
                  <!--<van-checkbox class="check" v-if="item.productId==9 " v-model="checked" disabled>蜻蜓产品</van-checkbox>
                  <van-checkbox class="check" v-if="item.productId==2 " v-model="checked" disabled>app产品</van-checkbox>
                  <van-checkbox class="check" v-if="item.productId==1  " v-model="checked" disabled>扫码产品</van-checkbox>-->
                  <span class="name">结算费率：</span>
                  <input class="alreadyInput"  type="text" v-model="item.productRate" readonly />
                   <span class="unit">%</span>
                </div>
               <!-- <div class="sn" v-if="item.productId==9">
                  <span>SN:</span>
                  <input type="text" v-model="item.sn"  disabled />
                </div>-->
              </div>
            </div>
          </div>
          <div class="unsigned contract">
            <div class="signTit">可签约产品</div>
            <div class="row1" v-for="(item,idx) in allowList" :key="idx" >
              <van-checkbox class="check" v-model="item.isCheck">{{item.productName}}</van-checkbox>
              <span class="name">结算费率：</span>
              <input type="number" v-model="item.productRate" />
              <span class="unit">%</span>
            </div>
            <!--<div class="row" v-if="qinting">
              <input type="text" v-model="sn" style="border-color:#ccc" placeholder="请输入蜻蜓设备编号SN" />
            </div>
            <div class="row1" ref="scan" v-if="scan">
              <van-checkbox v-if="scan" class="check" v-model="contract_scan">扫码产品</van-checkbox>
              <span class="name">结算费率：</span>
              <input type="number" v-model="scanRate" />
               <span class="unit">%</span>
            </div>
            <div class="row1" ref="app" v-if="app">
              <van-checkbox class="check" v-model="contract_app">app产品</van-checkbox>
              <span class="name">结算费率：</span>
              <input type="number" v-model="appRate" />
              <span class="unit">%</span>
            </div>-->
          </div>
        </div>
        <div class="btn" @click="submitContract">提交</div>
      </van-tab>
    </van-tabs>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { shopAuditInfo } from "../../assets/api/interface";
//import { Dialog } from 'vant';//弹窗函数，可直接调用

export default {
  name: "audit",
  components: {

  },
  created() {
    //this.$route.params.type
  },
  data() {
    return {
      checked: true,
      value: "1",
      infoType: 0,
     /* qtRate: 0.38,
      scanRate: 0.38,
      appRate: 0.38,
      sn: "",*/
      allInfoList: {},
      proList: [], //已签约产品数据格式
      allowList:[], //允许签约的产品
      alreadyProList:[],//已经签约或待审核的产品
      storageList:[],//将可签约的产品存储起来对比费率
    };
  },

  computed: {
    ...mapState(["McustId",'custId'])
  },

  mounted() {
    //this.userId = storage.get('userId');
    this.getShopDetail();
    this.getcontractInfo();
    //this.allowSign();
    console.log(this.$store.state.McustId);
  },
  methods: {
    //返回上一页
    changePrepage() {
      this.$router.go(-1);
    },
    //请求商户基本信息
    async getShopDetail() {
      let listInfo = await shopAuditInfo.shopsucceedInfo({
        custId: this.McustId //商户Id
      });
      //console.log(listInfo);
      this.allInfoList = listInfo.data.data;
    },
    //请求已签约信息
    async getcontractInfo() {
      let contractInfo = await shopAuditInfo.contractInfo({
        mchCustId: this.McustId //商户Id
      });
      console.log(contractInfo);
      this.proList = contractInfo.data.data;
      for (let i = 0; i < this.proList.length; i++) {
        if (this.proList[i].productStatus == '00' || this.proList[i].productStatus == '01') {
          this.alreadyProList = this.alreadyProList.concat(this.proList[i]);
        }
      }
      this.allowSign();
    },
    //查询可签约的产品
    async allowSign(){
      let allowData= await shopAuditInfo.contractInfo({
        mchCustId:this.custId, //进入主页时拿取的值
        productStatus:'00'//签约状态
      });
      let AllallowList=allowData.data.data;
      let Allow_length=AllallowList.length;
      let Already_length=this.alreadyProList.length;
      console.log('Allow_length:'+Allow_length,'Already_length:'+Already_length);
      for(let i=0;i<Allow_length;i++){
        let num=0;
        for(let j=0;j<Already_length;j++){
          if(AllallowList[i].productId==this.alreadyProList[j].productId){
            num++;
          }
        }
        if(num==0){
          //AllallowList[i].isCheck=false;
          let list=JSON.parse(JSON.stringify(AllallowList[i]));
          list.isCheck=false;
          this.allowList=this.allowList.concat(list);
        }
      }
      this.storageList=JSON.parse(JSON.stringify(this.allowList));
      console.log(AllallowList);
      console.log(this.allowList);
    },
    //提交签约
    async submitContract() {
      let obj = {}, newProlist = [];
      let _length=this.allowList.length;
      for(let i=0;i<_length;i++){
        if(this.allowList[i].isCheck){
          if(this.allowList[i].productRate){
            let minRate=parseFloat(this.storageList[i].productRate);
            let maxRate=parseFloat(this.storageList[i].productRate)+0.1;
            console.log('minRate:'+minRate,'maxRate:'+maxRate);
            if(this.allowList[i].productRate>=minRate&&this.allowList[i].productRate<=maxRate){
              obj = {
                productId: this.allowList[i].productId,
                productRate: this.allowList[i].productRate
              };
              newProlist.push(obj);
            }else {
              this.$toast({
                message: '可签约的'+this.allowList[i].productName+'产品进件结算费率区间为'+ minRate+' ~ '+ maxRate+'，若有疑义，请咨询客服。',
                duration:3500
              });
              this.allowList[i].productRate='';
              return false;
            }
          }else {
            this.$toast('已选产品对应的结算费率不能为空');
            return false;
          }
        }
      }
      console.log(newProlist);
      let listInfo = await shopAuditInfo.pro_contract({
        productInfos:JSON.stringify(newProlist) ,
        custId:this.McustId
      });
      if(listInfo.data.code==200){
        this.$toast({ message: listInfo.data.data});
        this.proList=[];
        this.allowList=[];
        this.alreadyProList=[];
        this.storageList=[];
        this.getcontractInfo();
        //this.allowSign();
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
.stepInfo {
  .contract {
    .signTit {
      height: vw(80);
      line-height: vw(80);
      margin: vw(20) 0;
      padding-left: vw(20);
      background: #c7dff9;
      color: #666
    }
    /*.sn{
      height: vw(80);
      font-size: vw(26);
      display: flex;
      justify-content: flex-end;
      align-items: flex-end;
      padding:0 vw(20);
      span{
        color: #c4c4c4;
        margin-right: vw(20);
      }
      input{
        padding-bottom: vw(20);
      }
    }*/
    input {
      border-bottom: vw(1) solid #a5acff;
      width: vw(150);
    }
    .alreadyInput{
      color: #ccc;
      border-bottom: vw(1) solid #cccccc;
    }
  }
  
}
</style>