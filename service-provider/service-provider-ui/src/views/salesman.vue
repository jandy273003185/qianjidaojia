<template>
  <div >
    <BaseHeader></BaseHeader>
    <div class="salesman">
      <div class="serachBox">
        <div class="search" @click="searchshop">
          <img class="searchIcon" src="../assets/images/home/search.png" alt />
          <div>搜索商户名称</div>
        </div>
      </div>
      <div class="incomingBox" @touchstart="toIncoming">
        <div class="buttonAdd">
          <img src="../assets/images/home/add.png" alt="添加" />
          <p>商户进件</p>
        </div>
      </div>
      <timeSelect v-if="islogin"></timeSelect>
      <!--引入时间选择组件-->
      <div class="situation">
        <div class="titleBox">
          <div class="line"></div>
          <p class="titleBar">商户进件情况</p>
          <div class="line"></div>
        </div>
        <div class="isRanking" v-if="isHave">暂无商户数据</div>
        <ul>
          <li
            v-for="(item, index) in statesList"
            :key="index"
            @click="toDetail(item.state,item.filingAuditStatus,item.custId)"
          >
            <div>
              <span class="shopName">{{item.custName }}</span>
              <span class="time">{{ item.createTime }}</span>
            </div>
            <span v-if="item.state=='00'&&item.filingAuditStatus=='00'" class=" state state_0">审核通过</span>
            <span v-if="item.state=='00'&&item.filingAuditStatus=='01'" class=" state state_2">审核中</span>
            <span v-if="item.state=='01'"  class=" state state_1">待审核</span>
            <span v-if="item.state=='04'" class=" state state_4">审核失败</span>
            <span v-if="item.state=='05'" class=" state state_5">待完善</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import timeSelect from "../components/time-select";
import BaseHeader from "../components/baser-header.vue";
import { shopAuditInfo, login } from "../assets/api/interface";
import { mapMutations } from "vuex";
import storage from "../assets/modeljs/storage.js";
import axios from "axios";
export default {
  name: "salesman",
  components: { timeSelect, BaseHeader },
  data() {
    return {
      islogin: false,//组件事件选择控制
      openId: "",
      roleId: "salesman",
      statesList: [],
      userId: "",
      isHave:true  //无数据时显示：暂无数据
    };
  },
  created() {
    //进件初始化
    this.setincoming('');
    this.setPhotos('');
    this.setCheckedState('');console.log(this.$route.params.userType);
    if(this.$route.params.userType == 'salesman'){
      this.firstLogin();
    }else{
      this.islogin = true;
      this.salesShopNew();
    }
  },
  mounted() {},

  methods: {
    async firstLogin() {
      //初次进入主页，传OpenId到后台，判断是否有绑定过账户
      const params = {openId:this.$route.params.openId,userId:this.$route.params.userId,userType:this.$route.params.userType};
      const res = await login.firstLogin(params); //获取绑定状态
      console.log(res);
    
       //已绑定
          localStorage.setItem("token", res.data.data.token);
          axios.defaults.headers.common["token"] = res.data.data.token;
          this.setToken(res.data.data.token);
          this.setUserId(this.$route.params.userId);
          this.setCustId(this.$route.params.custId);
          this.setUserName(res.data.data.userName);
          storage.set("userId", this.$route.params.userId);
          console.log(storage.get("userId"));
          this.islogin = true;
          this.salesShopNew();
    },
    toIncoming() {
      //路由跳转到商户进件页面
      console.log("商户进件");
      this.setMCustId('');
      this.$router.push("baseInfo");
    },
    searchshop() {
      this.$router.push("searchShop");
    },
    
    //业务员主页下部分商户进件最新十条信息
    async salesShopNew() {
      console.log("userId"+this.$store.state.userId);
      let listInfo = await shopAuditInfo.shopAuditInfo({
        userId:this.$store.state.userId,// storage.get("userId"),
        pageSize: "10",
        pageNum: "1",
        roleId: "3"
      });
      console.log(listInfo);
      this.statesList = listInfo.data.data.data;
      let list = listInfo.data.data.data;
      if(list && list.length>0 ){
        this.isHave = false;//有数据时隐藏掉
      }
      /*let total=listInfo.data.data.total;*/
    },
    //查看审核失败信息、待审核和审核成功信息
    toDetail(state,filingAuditStatus,custId){
      this.setMCustId(custId);
      if(state=='04'){
        this.$router.push('whyFailed');
      }
      if(state=='00'&&filingAuditStatus=='00'){
        this.$router.push('/audit/pass');
      }
      if (state == "05") {
        this.$router.push({
          name: "baseInfo",
          params: { type: "corvidae" }
        });
      }
    },



    ...mapMutations([
      "setRole",
      "setRoleId",
      "setUserId",
      "setToken",
      "setCustId",
      "setMCustId",
      "setOpenID",
      "setCode",
      "setUserName",
      "setincoming",
      "setPhotos",
      "setCheckedState"
    ])
  }
};
</script>
<style scoped lang="stylus" ref="stylesheet/stylus">
@import '../style/common/base.styl';

.salesman {
  width: 100%;
  height: 100%;
  .serachBox {
    width: 100%;
    height: vw(92);
    background-color: #699dd7;
    display: flex;
    justifu-content: center;
    align-items: center;

    .search {
      width: vw(690);
      height: vw(58);
      line-height: vw(58);
      margin:0 auto;
      background-color: #eeeeee;
      display: flex;
      align-items: center;
      justifu-content: center;
      border-radius: vw(10);

      .searchIcon {
        width: vw(40);
        height: vw(40);
      }

      div {
        font-size: vw(32);
        color: #999999;
      }
    }
  }

  .incomingBox {
    width: vw(690);
    height: vw(80);
    margin: vw(36) auto;
    border: vw(1) dashed #699dd7;

    .buttonAdd {
      width: vw(220);
      height: vw(40);
      margin: vw(20) auto;
      /* border: 1px solid red; */
      display: flex;
      align-items: center;
      justify-content: space-between;

      img {
        width: vw(40);
        height: vw(40);
      }

      p {
        height: vw(40);
        line-height: vw(40);
        font-size: vw(40);
        color: #699dd7;
      }
    }
  }

  .situation {
    width: 100%;
    height: vw(600);
    box-sizing: border-box;

    .titleBox {
      display: flex;
      justify-content: space-between;
      align-items: center;

      .line {
        height: vw(2);
        width: vw(100);
        background-color: #c0c0c0;
        flex-grow: 1;
      }

      .titleBar {
        height: vw(30);
        line-height: vw(30);
        text-align: center;
        color: #333333;
        font-size: vw(30);
      }
    }
    .isRanking{
      width: 100%;
      padding: 0 vw(20);
      box-sizing :border-box;
      height:vw(110);
      display: flex;
      justify-content: center;
      align-items: center;
      background:#fff;
    }
    ul {
      width: 100%;
      margin: auto;
      margin-bottom  vw(120)
      background-color: #fff;

      li {
        width: vw(680);
        height: vw(94);
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: vw(1) solid #EEEEEE;

        div {
          color: #333333;
          height: 100%;
          margin: 0;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          align-items: flex-start;

          .shopName {
            color: #030303;
            font-size: vw(32);
          }

          .time {
            color: #969696;
            font-size: vw(22);
          }
        }

        .state {
          font-size: vw(32);
        }

        .state_0 {
          color: #3fd016
        }

        .state_1 {
          color: #aeaeae;
        }

        .state_2{color: #37ae9a;}

        .state_4 {
          color: #ff495d;
        }

        .state_5 {
          color: #3f9aff;
        }
      }
    }
  }
}
</style>