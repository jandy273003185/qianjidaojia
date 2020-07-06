<template>
  <div>
    <BaseHeader></BaseHeader>
    <div class="shop">
      <div class="searchBar">
        <div class="searchName">
          <van-search
                  v-model="mchName"
                  placeholder="请输入商户名称"
                  show-action
                  shape="round"
                  @search="onSearch"
          >
            <div slot="action" @click="onSearch">搜索</div>
          </van-search>
        </div>
        <div class="dateSelect">
          <img src="../assets/images/home/time.png" alt="" plain type="primary" @click="selectTime">
          <span>日期</span>
          <div class="modal-wrap" v-show="showTime">
            <div class="timePopup">
              <div class="text flex_center"><span class="titleBar">时间</span></div>
              <div class="text flex_s_b" plain type="primary" @click="showPopFn('timeStart')">
                <button>开始时间</button>
                <span>{{timeStart}}</span>
              </div>
              <div class="text flex_s_b "  plain type="primary" @click="showPopFn('timeEnd')">
                <button>结束时间</button>
                <span>{{timeEnd}}</span>
              </div>
              <van-popup v-model="show" position="bottom" :style="{ height: '40%' }">
                <van-datetime-picker v-model="currentDate" type="date" :max-date="maxDate" @change="changeFn()" @confirm="confirmFn()" @cancel="cancelFn()" />
              </van-popup>
              <div class="btn flex_r">
                <button class="cancel" @click="cancel">取消</button>
                <button class="affirm" @click="affirm">确认</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <van-tabs v-model="active" swipeable @change="changeTab" title-active-color="#699dd7" color="#699dd7">
        <van-tab title="全部">
          <ul>
            <van-list v-if="active==0"
                    v-model="loading"
                    :finished="finished"
                    finished-text="暂无更多数据"
                    @load="loadList"
            >
            <li v-for="(item, index) in allStateList" :key="index"
                @click="toDetail(item.state,item.filingAuditStatus,item.custId)">
              <div>
                <span class="shopName">{{ item.custName }}</span>
                <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                <span v-if="intoRole=='2'&& item.type=='agent' " class="time">管理员: {{item.userName}}</span>
              </div>
              <span v-if="item.state=='00'&&item.filingAuditStatus=='00'" class=" state state_0">审核通过</span>
              <span v-if="item.state=='00'&&item.filingAuditStatus=='01'" class=" state state_2">审核中</span>
              <span v-if="item.state=='01'"  class=" state state_1">待审核</span>
              <span v-if="item.state=='04'" class=" state state_4">审核失败</span>
              <span v-if="item.state=='05'" class=" state state_5">待完善</span>


            </li>
          </van-list>
          </ul>
        </van-tab>

        <van-tab title="待完善">
          <ul>
            <van-list v-if="active==1"
                      v-model="loading"
                      :finished="finished"
                      finished-text="暂无更多数据"
                      @load="loadList"
            >
              <li v-for="(item, index) in allStateList" :key="index" @click="toDetail(item.state,item.filingAuditStatus,item.custId)">
                <div>
                  <span class="shopName">{{ item.custName }}</span>
                  <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                  <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                  <span v-if="intoRole=='2'&& item.type=='agent' " class="time">管理员: {{item.userName}}</span>
                </div>
                <span v-if="item.state=='05'" class=" state state_5">待完善</span>


              </li>
            </van-list>

          </ul>
        </van-tab>
        <van-tab title="待审核">
          <ul>
            <van-list v-if="active==2"
                      v-model="loading"
                      :finished="finished"
                      finished-text="暂无更多数据"
                      @load="loadList"
            >
              <li v-for="(item, index) in allStateList" :key="index" >
                <div>
                  <span class="shopName">{{ item.custName }}</span>
                  <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                  <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                  <span v-if="intoRole=='2'&& item.type=='agent' " class="time">管理员: {{item.userName}}</span>
                </div>
                <span v-if="item.state=='01'" class=" state state_1">待审核</span>

              </li>
            </van-list>
          </ul>
        </van-tab>
        <van-tab title="审核中">
          <ul>
            <van-list v-if="active==3"
                      v-model="loading"
                      :finished="finished"
                      finished-text="暂无更多数据"
                      @load="loadList"
            >
              <li v-for="(item, index) in allStateList" :key="index" >
                <div>
                  <span class="shopName">{{ item.custName }}</span>
                  <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                  <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                  <span v-if="intoRole=='2'&& item.type=='agent'" class="time">管理员: {{item.userName}}</span>
                </div>
                <span v-if="item.state=='00'&&item.filingAuditStatus=='01'" class=" state state_2">审核中</span>

              </li>
            </van-list>
          </ul>
        </van-tab>
        <van-tab title="审核失败">
          <ul>
            <van-list v-if="active==4"
                      v-model="loading"
                      :finished="finished"
                      finished-text="暂无更多数据"
                      @load="loadList"
            >
              <li v-for="(item, index) in allStateList" :key="index" @click="toDetail(item.state,item.filingAuditStatus,item.custId)">
                <div>
                  <span class="shopName">{{ item.custName }}</span>
                  <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                  <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                  <span v-if="intoRole=='2'&& item.type=='agent' " class="time">管理员: {{item.userName}}</span>
                </div>
                <span  v-if="item.state=='04'" class=" state state_4">审核失败</span>
              </li>
            </van-list>
          </ul>
        </van-tab>
        <van-tab title="审核通过">
          <ul>
            <van-list v-if="active==5"
                      v-model="loading"
                      :finished="finished"
                      finished-text="暂无更多数据"
                      @load="loadList"
            >
              <li v-for="(item, index) in allStateList" :key="index" @click="toDetail(item.state,item.filingAuditStatus,item.custId)">
                <div>
                  <span class="shopName">{{ item.custName }}</span>
                  <span v-if="intoRole=='3' " class="time">{{ item.createTime }}</span>
                  <span v-if="intoRole=='2'&& item.type=='salesman' " class="time">业务员: {{item.userName}}</span>
                  <span v-if="intoRole=='2'&& item.type=='agent' " class="time">管理员: {{item.userName}}</span>
                </div>
                <span v-if="item.state=='00'&&item.filingAuditStatus=='00'" class=" state state_0">审核通过</span>
              </li>
            </van-list>
          </ul>
        </van-tab>


      </van-tabs>
    </div>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex';
  import BaseHeader from "../components/baser-header.vue";
  import { Dialog } from 'vant';//弹窗函数，可直接调用
  import {shopAuditInfo} from "../assets/api/interface";
export default {
  name: "shop",
  components: { BaseHeader },
  data() {
    return {
      //按页加载
      loading:false,
      finished: false,
      /*搜索商户*/
      mchName:"",
      active:'',
      pageNum:0,//页数
      /*自定义查看时间的选择*/
      maxDate: new Date(),
      currentDate: new Date(),
      changeDate: new Date(),
      show: false, // 用来显示日期弹出层
      timeStart:'',//选取的开始时间
      startMs:'',//选取的开始时间的毫秒数
      timeEnd:'',//选取的结束时间
      endMs:'',//选取的结束时间的毫秒数
      showTime:false,//点击选取时间时打开的遮罩层判断
      type:'',
     intoRole:'',
      /*商户审核信息*/
      allStateList:[]
    };
  },

  computed:{
          ...mapState(['roleId','userId','role','custId'])
},
  created(){
    this.setincoming({});
    this.setPhotos([]);
    this.setCheckedState('');
  },
  mounted() {
    console.log(this.userId + '商户');
    this.intoRole=this.roleId;
  },
  methods: {

    //tab栏切换时重置参数信息
    changeTab(){//绑定到change事件中，滑动和点击tab时均可以触发
      this.loading=false;
      this.finished=false;
      this.pageNum=0;//页码重置
      this.timeStart='';//选取的开始时间
      this.timeEnd='';//选取的结束时间
      this.mchName='';//搜索的商户名
      this.allStateList=[];//清空数组
    },

    //tab栏调用请求

    loadList(){
      this.pageNum+=1;
      this.getAllShopList();
    },

//异步请求

    async getAllShopList(){
        let filingAuditStatus='';
        let stateCode='';
      if(this.active==0) stateCode='';

      if(this.active==1) stateCode='05';//待完善

      if(this.active==2) stateCode='01';//待审核

      if(this.active==3){ stateCode='00'; filingAuditStatus='01';}//审核中

      if(this.active==4) stateCode='04';//审核失败

      if(this.active==5){//审核成功
        stateCode='00';
        filingAuditStatus='00';
      }
      let params={
        custName:this.mchName,//商户名
        queryStartDate:this.timeStart,//开始时间
        queryEndDate:this.timeEnd,//结束时间
        stateCode:stateCode,//审核状态
        filingAuditStatus:filingAuditStatus,
        userId:this.role == 'finance'?this.custId:this.userId,
        pageSize:'20',
        pageNum:this.pageNum,
        roleId:this.roleId
      };
      console.log(this.intoRole);
      var listInfo;
      if(this.intoRole=='3'){//业务员的商户数据
         listInfo=await shopAuditInfo.shopAuditInfo(params);
      }
      if(this.intoRole=='2'){//管理员的商户数据
          listInfo=await shopAuditInfo.allShopAuditInfo(params);
      }
      let list= listInfo.data.data.data;
      this.allStateList = this.allStateList.concat(list);
      let total=listInfo.data.data.total;
      console.log(listInfo);
      this.loading = false;
      if(this.allStateList.length>=total){//判断已加载完成
        this.finished=true;
      }
      
    },

  //搜索商户数据


    onSearch(){////将this.value传到后台
      if(this.mchName){
        this.loading=false;
        this.finished=false;
        this.pageNum=1;
        this.timeStart='';//选取的开始时间
        this.timeEnd='';
        this.allStateList=[];
        this.getAllShopList();
      }else {
        this.$toast('请输入商户名称');
        return;
      }

    },

    selectTime(){//点击选择查看自定义时间
      this.showTime = true;
    },

    showPopFn(tm) {//点击打开日历，通过传值tm判断是开始时间还是结束时间
      this.show = true;
      this.type = tm;
    },
    changeFn() { // 值变化是触发
      this.changeDate = this.currentDate; // Tue Sep 08 2020 00:00:00 GMT+0800 (中国标准时间)
    },
    confirmFn() { // 日历上的确定按钮
      console.log(this.type);
      if(this.type=='timeStart'){//选择开始时间
        this.timeStart = this.timeFormat(this.currentDate);
      }
      if(this.type=='timeEnd'){//选择结束时间
        this.timeEnd = this.timeFormat(this.currentDate);
      }
      this.show = false;

    },
    cancelFn(){//取消按钮
      this.show = false;
    },
    timer(time){//将个位数日期变两位数
      return time < 10 ? '0' + time : '' + time;
    },
    timeFormat(time) { // 时间格式化 2019-09-08
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      month = this.timer(month);
      let day = time.getDate();
      day = this.timer(day);
      return year + '-' + month + '-' + day
    },
    cancel(){//取消选择自定义日期
      this.showTime = false;
    },
    affirm(){//确认选择的自定义日期
      this.startMs = new Date(this.timeStart).getTime();//开始日期的毫秒数
      this.endMs = new Date(this.timeEnd).getTime();//结束日期的毫秒数
      if(this.startMs && this.endMs){
        if(this.startMs <= this.endMs){
          this.loading=false;//初始化
          this.finished=false;//初始化
          this.allStateList=[];//清空数据
          this.showTime = false;//关闭时间选择
          console.log(this.timeStart,this.timeEnd);
          this.pageNum=1;
          this.getAllShopList();
        }else {
          Dialog({ message: '查看的开始时间必须小于或等于结束时间！！' })
        }
      }else {
        Dialog({ message: '请选择查看开始时间及结束时间！！' })
      }

    },

    //查看审核失败信息和审核成功信息、进入待完善继续编辑
    toDetail(state,filingAuditStatus,custId){
      if(this.role=='finance'){
        return false;
      }
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

      ...mapMutations(['setMCustId','setincoming','setPhotos','setCheckedState'])

  }
};


</script>
<style lang="stylus" scoped>

  @import "../style/common/base.styl";
  .shop{
    width: 100%;
    height: 100%;
    .searchBar{
        height: vw(120);
        width: 100%;
        display: flex;
        justify-content: space-around;
        align-items: center;
        margin-bottom:vw(20);
        background-color:#ffffff;

        .searchName{
          width: 85%;
        }
        .dateSelect{
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;

          img{
            width: vw(50);
            height: vw(40);
          }

          span{
            font-size: vw(20);
            color: #666666;
          }

          .modal-wrap{
            width: 100%;
            height: 100%;
            position: fixed;
            top:0;
            right: 0;
            bottom: 0;
            left: 0;
            background-color: rgba(0,0,0,0.5);
            z-index: 999;
            .timePopup{
              width: 95%;
              margin: auto;
              /* height: vw(340);*/
              border-radius: vw(10);
              position: relative;
              top: 30%;
              background-color: #FFFFFF;
              padding:0 vw(30);
              box-sizing: border-box;
              .text{
                height: vw(80);
                button{
                  width: vw(160);
                  height: vw(60);
                  background-color: #699dd7;
                  color: #fff;
                  border-radius: 3px;
                }
                span{
                  font-size: vw(30);
                }
                .titleBar{
                  font-size: vw(40);
                }
              }

              .btn{
                height: vw(100);

                button{
                  width: vw(170);
                  height: vw(60);
                  border-radius: 3px;

                }
                .cancel{
                  border:none;
                  color: #699dd7;
                  background-color: #fff;
                }
                .affirm{
                  background-color: #699dd7;
                  color: #fff;
                  border: none;
                  margin-left: vw(10);
                }
              }
            }
          }

        }
    }
    ul{
      width: 100%;
      margin: vw(20) 0 vw(100) 0;
      background-color: #fff;
      /*padding-bottom vw(100)*/


      li{
        width: vw(690);
        height: vw(94);
        margin: 0 auto;
        display: flex;
        justify-content:space-between;
        align-items: center;
        border-bottom: 1px solid #EEEEEE;

        div{
          color: #333333;
          height: 100%;
          margin: 0;
          display: flex;
          flex-direction: column;
          justify-content:space-around;
          align-items: flex-start;
          .shopName{
            color: #030303;
            font-size: vw(32);
          }
          .time{
            color: #969696;
            font-size: vw(22);
          }
        }
        .state{
          font-size: vw(32);
        }
        .state_0{ color: #3fd016}
        .state_1{color: #aeaeae;}
        .state_2{color: #37ae9a;}
        .state_4{color: #ff495d;}
        .state_5{ color: #3f9aff;}
      }
    }
  }
</style>
