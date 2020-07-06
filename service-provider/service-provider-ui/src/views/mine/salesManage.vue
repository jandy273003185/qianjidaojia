<template>
  <div class="sales">
    <van-nav-bar
      title="业务员管理"
      left-text="返回"
      left-arrow
      @click-left="changePrepage"
      @click-right="onClickRight"
    >
      <van-icon name="add-o" slot="right" size="1.8em" />
    </van-nav-bar>
    <van-tabs v-model="tabActive"  title-active-color="#699dd7" color="#699dd7" @click="onClick" >
      <van-tab title="未冻结">
        <van-row class="row salesInfo">
          <van-col span="6">姓名</van-col>
          <van-col span="4">账号</van-col>
          <van-col span="13">
            <van-search
                v-model="salesName"
                placeholder="请输入业务员名字"
                show-action
                shape="round"
                background="#f7f7f7"
                @search="onSearch"
            >
              <div slot="action" @click="onSearch">&nbsp;搜&nbsp;索</div>
            </van-search>
          </van-col>
          <van-col span="1"></van-col>
        </van-row>
        <van-row
          class="salesInfo row"
          v-show="item.status==1"
          v-for="(item,idx) in saleList"
          :key="idx"
        >
          <van-col span="6">{{item.userName}}</van-col>
          <van-col span="8">{{item.userPhone}}</van-col>
          <van-col class="textBox" span="5" @click="resetPwd(item.custId,item.salesmanId)">
            <span class="text">重置密码</span>
          </van-col>
          <van-col
            class="textBox"
            span="5"
            @click="deleteSale(item.custId,item.salesmanId,item.status,item.userPhone)"
          >
            <span class="text">冻结</span>
          </van-col>
        </van-row>
      </van-tab>
      <van-tab title="已冻结">
        <van-row class="row salesInfo">
          <van-col span="6">姓名</van-col>
          <van-col span="4">账号</van-col>
          <van-col span="13">
            <van-search
                v-model="salesName"
                placeholder="请输入业务员名字"
                show-action
                shape="round"
                background="#f7f7f7"
                @search="onSearch"
            >
              <div slot="action" @click="onSearch">&nbsp;搜&nbsp;索</div>
            </van-search>
          </van-col>
          <van-col span="1"></van-col>
        </van-row>
        <van-row
          v-show="item.status==0"
          class="salesInfo row"
          v-for="(item,idx) in saleList"
          :key="idx"
        >
          <van-col span="6">{{item.userName}}</van-col>
          <van-col span="8">{{item.userPhone}}</van-col>
          <van-col class="textBox" span="5" >
            <!--<span class="text">重置密码</span>-->
          </van-col>
          <van-col
            class="textBox"
            span="5"
            @click="undeleteSale(item.custId,item.salesmanId,item.status,item.userPhone)"
          >
            <span class="text unfreeze">解冻</span>
          </van-col>
        </van-row>
      </van-tab>
    </van-tabs>
    <!-- 添加业务员 -->
    <div class="modal-wrap" v-show="addingShow">
      <div class="addSales">
        <div class="tit">添加业务员</div>
        <div class="item">
          <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
          <input v-model="inpName" placeholder="请输入名字" />
        </div>
        <div class="item">
          <span>手机号码：</span>
          <input type="number" v-model="inpAccount" placeholder="请输入手机号" />
        </div>

        <div class="item buttons">
          <span class="btn" @click="cancelAdding">取消</span>
          <span class="btn save" @click="saveSales">保存</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { adminIndex, common } from "@/assets/api/interface";
import { Dialog } from "vant";
import { mapState } from "vuex";
export default {
  name: "sales",
  components: {},
  data() {
    return {
      addingShow: false,
      saleList: [],
      inpName: "",
      inpAccount: "",
      tabActive: 0,
      salesName:''
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
    onSearch(){//搜索业务员
      if(this.salesName){
        let params = {custId: this.userId,userName:this.salesName};
        this.getInitList(params);
      }else {
        Dialog({ message: "请输入业务员名字" });
      }
    },

    onClick(){
      this.salesName='';
      let params = {custId: this.userId};
      this.getInitList(params);
    },

    async getInitList(params) {
      let list = await adminIndex.searchSales(params);
      console.log(list);
      this.saleList = list.data.data;
    },
    changePrepage() {
      //;返回
      this.$router.go(-1);
    },
    onClickRight() {
      this.addingShow = true;
    },
    cancelAdding() {
      //取消业务员添加
      this.inpName = "";
      this.inpAccount = "";
      this.addingShow = false;
    },
    async resetPwd(custId, id) {
      //重置密码
      let res = await common.resetPwd({
        custId: custId,
        id: id
      });
      if (res.data.code == 200) {
        console.log("重置密码成功");
        Dialog({ message: "重置密码成功" });
      }
    },
    deleteSale(custId, id, status,userPhone) {
      //冻结业务员
      Dialog.confirm({
        title: "提示",
        message: "是否确定冻结账号"
      })
        .then(() => {
          // on confirm
          console.log(status);
          common.updateSales({
              custId: custId,
              salesmanId: id,
              status: 0,
              userPhone:userPhone
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
    undeleteSale(custId, id, status,userPhone) {
      //解冻业务员
      Dialog.confirm({
        title: "提示",
        message: "是否确定解冻账号"
      })
        .then(() => {
          // on confirm
          console.log(status);
          common.updateSales({
              custId: custId,
              salesmanId: id,
              status: 1,
              userPhone:userPhone
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
    async saveSales() {
      //保存新增业务员addSales
      if (this.inpName && this.inpAccount && (/^1[3456789]\d{9}$/.test(this.inpAccount))) {
        this.$toast.loading({
          message: "注册中..",
          forbidClick: true,
          duration: 0
        });
        let phoneCode = await common.addSales({phone:this.inpAccount,custId:this.userId});//号码校验是否绑定过
        console.log(phoneCode);
        this.$toast.clear();
        if(phoneCode.data.code == 200){//手机号未绑定过，可注册
          let res = await common.insertSales({
            userName: this.inpName,
            password: "123456",
            userPhone: this.inpAccount,
            custId: this.userId, //管理员的userId
            status: 1
          });
          if (res && res.data.code == 200) {
            let params = {custId: this.userId};
            this.getInitList(params);
            this.$toast("添加业务员成功！");
            this.cancelAdding();
          } else {
            // Dialog({ message: "添加业务员失败！" });
          }
        }else{//手机号已有绑定，不可再注册
          // if(phoneCode.data&&phoneCode.data.data){
          //   Dialog({ message: phoneCode.data.data });
          // }else{
          //   Dialog({ message:"请求异常！" });
          // }

        }
      } else {
        this.$toast("请检查是否正确填写业务员姓名和手机号！");
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/mine.scss";
.van-search .van-cell{
  background-color: #efefef;
  border-radius: 6px;
  padding-left: 5px;
}
.van-search__action{
  color: #3f9aff;
}

</style>