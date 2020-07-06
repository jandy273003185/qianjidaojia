<template>
  <div>
    <!--管理员-->
    <BaseHeader></BaseHeader>
    <div class="admin">
      <div class="serachBox">
        <div class="search" @click="searchSalesman">
          <img class="searchIcon" src="../assets/images/home/search.png" alt/>
          <div>搜索业务员名字</div>
        </div>
      </div>
      <div v-if="role=='agent'" class="incomingBox" @click="toIncoming">
        <div class="buttonAdd">
          <img src="../assets/images/home/add.png" alt="添加"/>
          <p>商户进件</p>
        </div>
      </div>
      <timeSelect v-if="islogin" @getDataRange="getDataRange"></timeSelect>
      <!--引入时间选择组件-->
      <div class="salesmanInfo">
        <van-tabs
            v-model="sortType"
            title-active-color="#699dd7"
            color="#699dd7"
            swipeable
            @change="channgeTab"
        >
          <van-tab title="交易额排名">
            <div class="isRanking" v-if="isHaveNum">暂无业务员交易额排名数据</div>
            <ul>
              <li class="flex_r" v-for="(item, index) in sumList" :key="index">
                <div>
                  <span class="num">NO.{{ index+1 }}</span>
                  <span class="name">{{ item.userName }}</span>
                </div>
                <div>
                  <span
                      :style="'width:'+(parseFloat(item.tradeAmtSum)*100/sumMax)+'%'"
                      class="process"
                  ></span>
                  <span class="sum">{{ item.tradeAmtSum }}元</span>
                </div>
              </li>
            </ul>
          </van-tab>
          <van-tab title="进件数排名">
            <div class="isRanking" v-if="isHaveSum">暂无业务员进件数排名数据</div>
            <ul>
              <li v-for="(item, index) in numList" :key="index">
                <div>
                  <span class="num">NO.{{ index+1 }}</span>
                  <span class="name">{{ item.userName }}</span>
                </div>
                <div>
                    <span :style="'width:'+(parseFloat(item.effectiveNum)*100/numMax)+'%'"
                          class="process">
                    </span>
                  <span class="sum">{{ item.effectiveNum }}件</span>
                </div>
              </li>
            </ul>
          </van-tab>
        </van-tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import timeSelect from "../components/time-select";
  import BaseHeader from "../components/baser-header.vue";
  import {login, adminIndex} from "../assets/api/interface";
  /*   import { mapState } from 'vuex';*/
  import {mapMutations,mapState} from "vuex";
  import storage from "../assets/modeljs/storage.js";
  import axios from "axios";
  import util from "@/lib/util.js";

  export default {
    name: "Administrator",
    components: {timeSelect, BaseHeader},
    data() {
      return {
        sortType: 0,
        openId: "",
        roleId: "",
        islogin: false,
        /*timeselect:true,*/
        queryStartDate: util.fun_date(7).timeStart,
        queryEndDate: util.fun_date(7).timeEnd,
        sumMax: 1, //计算业绩百分比，由第一名的值决定，
        numMax: 1, //计算笔数百分比，由第一名的值决定
        isHaveNum:true,//是否有业务员交易额排名数据
        isHaveSum:true,//是否有业务员进件排名数据
        sumList: [],
        numList: []
      };
    },
    created() {
      //进件初始化
      this.setincoming('');
      this.setPhotos('');
      this.setCheckedState('');
      if(this.$route.params.userType == 'agent' || this.$route.params.userType == 'finance'){
        this.firstLogin();
      }else{
          this.getSalesRanking(1);
          this.islogin=true;
      }
      
      console.log('line:111,role------------',this.role);
    },
      mounted(){
        
      },
      computed: {
        ...mapState(['role','userId','custId'])
      },
      methods: {
        async firstLogin() {
          //初次进入主页，传OpenId到后台，判断是否有绑定过账户
          const params = {
            openId:this.$route.params.openId,
            userId:this.$route.params.userId,
            userType:this.$route.params.userType
          };
          const res = await login.firstLogin(params); //获取绑定状态
          console.log(res);
        
          //已绑定
          localStorage.setItem("token", res.data.data.token);
          axios.defaults.headers.common["token"] = res.data.data.token;
          this.setToken(res.data.data.token);
          this.setUserId(this.$route.params.userId);
          this.setCustId(this.$route.params.custId);
          this.setMerchantCode(res.data.data.userInfo.merchantCode);
          this.setUserName(res.data.data.userName);
          storage.set("userId", this.$route.params.userId);
          this.getSalesRanking(1);
          this.islogin=true;
          console.log(storage.get("userId"));
        },
       
        channgeTab(e){
          console.log(e);
          if(e=='0'){
            this.getSalesRanking(1);
          }
          if(e=='1'){
            this.getSalesRanking(0);
          }
        }
      ,
        getDataRange(obj){
          //获取组件时间
          this.queryStartDate = obj.timeStart;
          this.queryEndDate = obj.timeEnd;
          this.sortType = 0;
          this.getSalesRanking("1");
        }
      ,
        toIncoming(){
          //路由跳转到商户进件页面
          console.log("商户进件");
          this.setMCustId('');
          this.$router.push("baseInfo");
        }
      ,
        searchSalesman(){
          //搜索业务员
          this.$router.push({
            name: "searchSalesman",
            params: {//参数
              fname: "Administrator"
            }
          });

        }
      ,
        async getSalesRanking(type) {
          //业务员排名
          const params = {
            sortType: type ,
            // userId: '7a25180eefee423a992d29d9712b6f9d',
            userId:this.role == 'finance'?this.custId:this.userId,
            queryStartDate: this.queryStartDate,
            queryEndDate: this.queryEndDate,
            roleId: 2
          };
          console.log(params)
          let sales = await adminIndex.SalesRanking(params);
          let list = sales.data.data;
          console.log(list);
          if (type == "1") {//交易额
            if (list[0] && list[0].tradeAmtSum) {
              this.sumMax = parseFloat(list[0].tradeAmtSum) * 1.5;
            }
            if(list && list.length >0){
              this.isHaveNum = false;
            }
            this.sumList = list;
          }
          if (type == "0") {//进件数
            if (list[0] && list[0].effectiveNum) {
              this.numMax = parseFloat(list[0].effectiveNum) * 1.5;
            }
            if(list && list.length >0){
              this.isHaveSum = false;
            }
            this.numList = list;
          }
        },
       
        ...mapMutations([
          
          "setToken",
          "setUserId",
          "setRoleId",
          'setMerchantCode',
          "setCustId",
          'setMCustId',
          "setUserName",
          "setincoming",
          "setPhotos",
          "setCheckedState"
        ])
      }
  }
  ;
</script>
<style scoped lang="stylus" ref="stylesheet/stylus">
  @import '../style/common/base.styl';

  .admin {
    width: 100%;
    background-color: #EEEEEE;

    .serachBox {
      width: 100%;
      height: vw(92);
      background-color: #699dd7;
      display: flex;
      align-items: center;
      margin-bottom: vw(36);

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
          height: vw(50);
          line-height: vw(50);
          font-size: vw(26);
          color: #999999;
        }

      }
    }

    .incomingBox {
      width: vw(690);
      height: vw(80);
      margin: 0 auto vw(36) auto;
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

    .salesmanInfo {
      width: vw(690);
      margin: vw(20) auto;
      background-color: #fff;
      .isRanking{
        width: 100%;
        padding: 0 vw(20);
        height:vw(110);
        display: flex;
        justify-content: center;
        align-items: center;
      }
      ul {
        width: 100%;
        padding: 0 vw(20) vw(20) vw(20);
        margin-bottom vw(100)

        li {
          height: vw(110);
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: flex-start;

          div {
            width: 100%;

            .process {
              display: inline-block;
              background: #b0b0b0;
              height: vw(20);
              border-radius: vw(6);
              line-height: vw(60);
            }

            .num {
              height: vw(20);
              font-size: vw(20);
              color: #929292;
            }

            .name {
              font-size: vw(20);
              margin-left: vw(10);
            }

            .sum {
              height: vw(20);
              font-size: vw(20);
              color: #8d8d8d;
              margin-left: vw(10);
            }

          }
        }

        li:nth-child(1) {

          .num {
            color: #557fae;
          }

          .process {
            background: #557fae;
          }

        }

        li:nth-child(2) {

          .num {
            color: #6698d1;
          }

          .process {
            background: #6698d1;
          }

        }

        li:nth-child(3) {

          .num {
            color: #7ab7fa;
          }

          .process {
            background: #7ab7fa;
          }

        }
      }
    }
  }
</style>