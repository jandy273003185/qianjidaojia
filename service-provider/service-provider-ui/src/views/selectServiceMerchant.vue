<template>
  <div class="sales" v-if="isLoad">
    <div v-for="(item, index) in serviceMerchantList" :key="index">
      <router-link :to="{name:pageName,params:{openId:item.openId,userId:item.userId,userType:item.userType,custId:item.custId}}">
        <div class="list" >
          <div class="list-left">
            <img src="../assets/images/person.png" alt="">
            <div>
              <p>{{item.mobileAccount}}</p>
              <p>{{item.name}}</p>
            </div>
          </div>
          <div class="list-right">
            <span>选择</span>
            <van-icon name="arrow" size="20"/>
          </div>
        </div>
      </router-link>
    </div>
    <router-link :to="{name:'login'}">
      <div class="addService">
        <img src="../assets/images/home/add.png" alt=""/><span>角色账号绑定</span>
      </div>
    </router-link>
    <van-row>
      <van-col class="loaded" span="24" align="center">{{loaded}}</van-col>
    </van-row>
  </div>
</template>
<script>
import { login } from "@/assets/api/interface";
import { mapState,mapMutations } from "vuex";
/*import axios from 'axios';*/

export default {
  name: "sales",
  components: {},
  data() {
    return {
      isLoad:false,
      title:'请选择服务商',
      serviceMerchantList: [],
      loaded:'暂无数据',
      pageName:'',
      roleId:''
    };
  },
  computed: {
    ...mapState(["openId",'role','code'])
  },
  created() {

    let wxStr = location.href.split("=")[1] ;
    if(wxStr){
      if(wxStr.indexOf("&")!= -1){
        wxStr = wxStr.split("&")[0];
      }
    }

    const roles = this.getUrlParam("roleCode") || this.$route.query.roleCode || wxStr;
    // alert(roles);
    if(roles == 'agent' || roles == 'finance'){
      this.setRole(roles);
      this.setRoleId("2");
    }else if(roles == 'salesman'){
      this.setRole(roles);
      this.setRoleId("3");
    }
    //console.log('created方法--','roles:'+roles,'role:'+this.role,'openId:'+this.openId);
    if(roles == 'agent' || roles == 'finance' || roles == 'salesman'){
      if(roles == 'salesman'){
        this.pageName='salesman'
      }else if(roles=='agent' || roles=='finance'){
        this.pageName='Administrator'
      }
    }else{
      if(this.role == 'salesman'){
        this.pageName='salesman'
      }else if(this.role=='agent' || this.role=='finance'){
        this.pageName='Administrator'
      }
    }

    //判断是否有code
      if (!this.code) {
        var code = this.getUrlParam("code");
        if (!code) {
          let REDIRECT_URI = encodeURIComponent(
            "https://sp-uat.qifenqian.com/wx/index.html#selectServiceMerchant?roleCode="+roles
          );
          window.location.href =
             "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3a39d7744ca89257&redirect_uri=" +
            //"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxce65746e62998dce&redirect_uri=" +
            REDIRECT_URI +
            "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
        } else {
          this.isLoad = true;
          this.setCode(code);
          this.getOpenId(code);
        }
      } else {
        this.isLoad = true;
        this.getInitList();
      }
  },

  mounted(){
   /*//本地测试
    this.isLoad = true;
    this.setRole('agent');
    this.setRoleId("2");
    this.setOpenID('P70000108');
    this.getInitList();*/
  },
  methods: {
    async getInitList() {
      let params = {openId:this.openId,roleCode:this.role};
      let list = await login.getBindingList(params);
      console.log(list);
      this.serviceMerchantList = list.data.data;
    },
       //获取openid
     async getOpenId(code){ 
          console.log('getOpenId方法code值：',code);       
          let res = await login.getOpenId({
            code: code
          });
          let _openId = res.data.data;
          console.log('_openId:',_openId);
          if (_openId) {
            this.setOpenID(_openId);
            this.getInitList();
          }
        },
         //获取code
        getUrlParam(name){
          var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");//正则
          var r = window.location.search.substr(1).match(reg);//截取当前网页URL中“?”后面的数据并进行正则匹配，返回数组
          if (r != null) return unescape(r[2]);//数组的索引[2]为code等号后面的值
          return null;
        },
         ...mapMutations(['setOpenID','setRoleId','setRole','setCode','setToken','setUserId'])
  },
 
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
  display: none;
}
select{
  width:vw(150);
  text-align: center;
  text-align-last: center;
}

.list{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #eeeeee;
  margin: vw(30) vw(30) 0 vw(30);
  height: vw(157);
  padding: vw(50) vw(30);
  border-radius: vw(4);
}
.list>div{
  display: flex;
  justify-content: center;
  align-items: center;
}
.list-left>div{
  display: flex;
  flex-direction: column;
  margin-left: vw(20);
}
.list-left>div>p:first-child{
  font-size: vw(34);
  color: #333333; 
}
.list-left>div>p:last-child{
  padding-left: vw(6); 
}
.list>div>img{
  width: vw(64);
  height: vw(72);
}
.list-right{
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #bb3500;
  color: #ffffff;
  width: vw(157);
	height: vw(46);
  border-radius: vw(30);
}
.list-right>span{
  font-size: vw(28);
  margin-left: vw(16);
}
.van-icon-arrow{
  margin-left: vw(10);
}
.addService{
  display: flex;
  justify-content: center;
  align-items: center;
  height: vw(70);
  border-radius: vw(4);
	border: 1px dashed #699dd7;
  margin: vw(100) vw(30);
  padding:vw(40);
}
.addService img{
  width: vw(50);
  height: vw(50);
  margin-right: vw(30);
}
.addService span{
  font-size: vw(36);
  color: #699dd7;
}
</style>