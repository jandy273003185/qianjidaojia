<template>
  <div class="sales">
    <van-nav-bar
      title="财务管理"
      left-text="返回"
      left-arrow
      @click-left="changePrepage"
      @click-right="onClickRight"
    >
      <van-icon name="add-o" slot="right" size="1.8em" />
    </van-nav-bar>
    <!-- <van-tabs v-model="tabActive"  title-active-color="#699dd7" color="#699dd7" @click="onClick" > -->
      <!-- <van-tab title="未冻结"> -->
        <van-row class="row salesInfo">
          <van-col span="10" offset="2" class="rowTitle">姓名</van-col>
          <van-col span="12" class="rowTitle">账号</van-col>
        </van-row>
        <van-row
          class="salesInfo row"
          v-for="(item,idx) in financeList"
          :key="idx"
          @click="financeDetail(item.financeId)"
        >
          <van-col span="7" offset="2">{{item.financeName}}</van-col>
          <van-col span="7">{{item.financeMobile}}</van-col>
          <van-col class="textBox" span="7" @click.stop="resetPwd(item.financeId)">
            <span class="text">重置密码</span>
          </van-col>
          <van-col span="1"><van-icon name="arrow" /></van-col>
        </van-row>
      <van-row>
        <van-col class="loaded" span="24" align="center">{{loaded}}</van-col>
      </van-row>
    <!-- 添加财务员 -->
    <div class="modal-wrap" v-show="addingShow">
      <div class="addSales">
        <div class="tit">{{financeTitle}}</div>
        <div class="item">
          <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
          <input v-model="inpName" placeholder="请输入名字" />
        </div>
        <div class="item">
          <span>手机号码：</span>
          <input type="number" v-model="inpAccount" placeholder="请输入手机号" />
        </div>
        <div class="item" style="display:none;">
          <span>分润查询权限:</span>
          <van-radio-group v-model="queryAuth">
            <van-radio name="1">启用</van-radio>
            <van-radio name="0">不启用</van-radio>
          </van-radio-group>
        </div>
        <div class="item" v-show="editShow">
          <span>状态：</span>
          <!-- <van-dropdown-menu z-index="999" >
            <van-dropdown-item v-model="status" :options="statusArr" />
          </van-dropdown-menu> -->
          <select name="" id="" v-model="status">
              <option value="1">生效</option>
              <option value="0">失效</option>
          </select>
        </div>

        <div class="item buttons">
          <span class="btn" @click="cancelAdding">取消</span>
          <span class="btn save" @click="saveFinancial">保存</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { adminIndex, common } from "@/assets/api/interface";
import { Dialog} from "vant";
import { mapState } from "vuex";

export default {
  name: "sales",
  components: {},
  data() {
    return {
      addingShow: false,
      financeTitle:'添加财务员',
      financeList: [],
      inpName: "",
      inpAccount: "",
      tabActive: 0,
      financialName:'',
      queryAuth:'1',
      statusArr:[{text:'生效',value:'1'},{text:'失效',value:'0'}],
      status:'1',
      loaded:'暂无数据',
      financeId:'',
      editShow:false
    };
  },
  computed: {
    ...mapState(["userId"])
  },
  created() {

    console.log(this.userId);
  },

  mounted(){
    let params = {custId: this.userId};
    this.getInitList(params);
  },
  methods: {
    onSearch(){//搜索财务员
      if(this.financialName){
        let params = {userName:this.financialName};
        this.getInitList(params);
      }else {
        Dialog({ message: "请输入财务员名字" });
      }
    },

    onClick(){
      this.financialName='';
      let params = {custId: this.userId};
      this.getInitList(params);
    },

    async getInitList(params) {
      let list = await adminIndex.searchFinance(params);
      console.log(list.data);
      this.financeList = list.data.data;
      if(list.data.data.length>0){
        this.loaded = '已加载全部数据'
      }else{
        this.loaded = '暂无数据'
      }
    },
    changePrepage() {
      //;返回
      this.$router.go(-1);
    },
    onClickRight() {
      this.addingShow = true;
      this.editShow = false;
      this.financeTitle = '添加财务员';
    },
    cancelAdding() {
      //取消财务员添加
      this.inpName = "";
      this.inpAccount = "";
      this.addingShow = false;
    },
    async financeDetail(id) {
      //查看详情
      this.editShow = true;
      let res = await common.financeDetail({
        // custId: custId,
        financeId: id
      });
      this.addingShow = true;
      this.financeId = id;
      this.financeTitle = '编辑财务员';
      this.inpName = res.data.data.financeName;
      this.inpAccount = res.data.data.financeMobile;
      this.queryAuth = res.data.data.queryAuth;
      this.status = res.data.data.status;
      console.log(res)
    },
    deleteSale(custId,id, status) {
      //冻结财务员
      Dialog.confirm({
        title: "提示",
        message: "是否确定冻结账号"
      })
        .then(() => {
          // on confirm
          console.log(status);
          common.updateFinancial({
              custId: custId,
              financeId: id,
              status: 0
            })
            .then(res => {
              let params = {custId: this.userId};
              this.getInitList(params);
              console.log("冻结" + res);
            });
        })
        .catch(() => {
          // on cancel
        });
    },
    undeleteSale(custId, id, status) {
      //解冻财务员
      Dialog.confirm({
        title: "提示",
        message: "是否确定解冻账号"
      })
        .then(() => {
          // on confirm
          console.log(status);
          common.updateFinancial({
              custId: custId,
              financeId: id,
              status: 1
            })
            .then(res => {
              console.log(res);
              let params = {custId: this.userId};
              this.getInitList(params);
              console.log("解冻");
            });
        })
        .catch(() => {
          // on cancel
        });
    },
    async saveFinancial() {
      //保存新增财务员addFinancial
      if(!this.financeId){//新增
        if (this.inpName && this.inpAccount && (/^1[3456789]\d{9}$/.test(this.inpAccount))) {
          this.$toast.loading({
            message: "注册中..",
            forbidClick: true,
            duration: 0
          });
          let phoneCode = await common.addFinancial({mobile:this.inpAccount,custId:this.userId});//号码校验是否绑定过
          console.log(phoneCode);
          this.$toast.clear();
            let res = await common.insertFinancial({
              financeName: this.inpName,
              password: "123456",
              financeMobile: this.inpAccount,
              custId: this.userId, //财务员的userId
              queryAuth: this.queryAuth
            });
              let params = {custId: this.userId};
              this.getInitList(params);
              this.cancelAdding();
        } else {
          this.$toast("请检查是否正确填写财务员姓名和手机号！");
        }
      }else{//修改
          let res = await common.updateFinance({
            financeId:this.financeId,
            financeName: this.inpName,
            financeMobile: this.inpAccount,
            queryAuth: this.queryAuth,
            status: this.status
          });
          let params = {custId: this.userId};
          this.getInitList(params);
          this.cancelAdding();
      }
      this.financeId = '';
    },
    resetPwd(id){
      Dialog.confirm({
        title: '是否确定重置密码'
      }).then(() => {
        let res = common.resetPwd2({
          financeId:id
        });
      }).catch(() => {
        // on cancel
      });
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/mine.scss";
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

.addSales{
  transform:none;
  top: 30%;
  left: 10%;
}
</style>