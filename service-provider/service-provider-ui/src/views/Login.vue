<template>
  <div class="login">
    <div class="box">
      <div class="title">
        <img src="../assets/images/login/login.png" alt />
        <span class="titleText">七分钱进件系统</span>
        <span v-if="role=='agent'">管理员登录</span>
        <span v-if="role=='salesman'">业务员登录</span>
        <span v-if="role=='finance'">财务员登录</span>
      </div>
      <p v-if="selectLogin=='psd'" class="logintype">账号密码登录</p>
      <p v-if="selectLogin=='code'" class="logintype">验证码登录</p>
      <div v-if="selectLogin=='psd'" ><!--账号密码登录-->
        <div class="item userName">
          <input v-model.trim="userName" type="text" placeholder="手机号码/邮箱" />
          <van-icon @click="clearName" name="close" />
        </div>
        <div class="item">
          <input v-model.trim="password" type="password" placeholder="登录密码" />
          <van-icon @click="clearPsd" name="close" />
        </div>
        <div class="findPsd">
          <span @click="findPsd">找回密码</span>
        </div>
      </div>
      <div v-if="selectLogin=='code'"><!--手机验证码登录-->
        <div class="item userName">
          <input v-model.trim="userPhone" type="text" placeholder="输入手机号码" maxlength="11"/>
          <van-icon @click="clearName" name="close" />
        </div>
        <div class="item">
          <input v-model.trim="userCode" type="text" placeholder="输入验证码" maxlength="6" />
          <div v-show="!showCountDown" class="getCode" @click="getCode(userPhone)">{{textCode}}</div>
          <div v-show="showCountDown" class="getCode count">{{count}} S后重试</div>
        </div>
      </div>
      <div class="item edter">
        <button @click="submitLogin">绑定</button>
      </div>
      <div v-if="selectLogin=='psd'" class="selectLogin" @click="selectInto('code')">短信验证码登录</div>
      <div v-if="selectLogin=='code'" class="selectLogin" @click="selectInto('psd')">账号密码登录</div>
    </div>
    <!--找回密码弹窗-->
    <div class="modal-wrap" v-if="showFind">
      <div class="findBox">
        <div class="amendPsd">修改密码</div>
        <div class="inputBox">
          <i>*</i>
          <span>手机号:</span>
          <input v-model="forgetPhone" type="number" placeholder="请输入手机号">
        </div>
        <div class="inputBox">
          <i>*</i>
          <span>新密码:</span>
          <input v-model="forgetNewPsd" type="password" placeholder="请输入新密码">
        </div>
        <div class="inputBox">
          <i>*</i>
          <span>验证码:</span>
          <input class="codeBox" v-model="forgetCode" type="number" placeholder="请输入验证码">
          <span v-show="!showForgetCount" class="getCode mar_t" @click="getForgetCode(forgetPhone)">{{FtextCode}}</span>
          <span v-show="showForgetCount" class="getCode count mar_t" >{{forgetCount}} S后重试</span>
        </div>
        <div class="affirmBox">
          <button class="cancel" @click="cancelSet">取消</button>
          <button class="affirm" @click="affirmSet">确认</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script >
import { mapState } from "vuex";
import { login } from "../assets/api/interface";
/*import { Dialog } from "vant"; //弹窗函数，可直接调用*/
export default {
  name: "Login",
  data() {
    return {
      userName: null,
      password: null,
      selectLogin:'psd',
      userCode:'',//短信验证码
      userPhone:'',//手机号
      showFind:false,//显示设置密码遮罩
      forgetPhone:'',//管理员手机号
      forgetNewPsd:'',//新密码
      forgetCode:'',//修改密码的验证码
      showCountDown:false,//是否显示倒计时,登录时
      showForgetCount:false,//是否显示倒计时，忘记密码
      count:60,//登录倒计时
      forgetCount:60,//忘记密码倒计时
      textCode:'获取验证码',
      timer:'',
      FtextCode:'获取验证码',
      Ftimer:''
    };
  },
  mounted() {
    console.log(this.$store.state.role);
    console.log(this.openId);
  },
  computed: {
    ...mapState(["role", "openId"])
  },
  methods: {
    //选择登录方式
    selectInto(way){
       this.selectLogin=way;
    },
    //忘记密码获取验证码
    async getForgetCode(Phone){
      if(Phone&&(/^1[3456789]\d{9}$/.test(Phone))){
        this.forgetCountTime();
        let loginData = await login.forgetCode({mobile:Phone,roleCode:this.role});
        if(loginData.data.code == 200){
          this.$toast({ message: "已发送短信验证码，请查收" });
        }else {
          // this.$toast({ message: '验证码发送失败'});
        }
      }else {
        this.$toast({ message: "请输入手机号" });
      }
    },
    //登录获取短信验证码
   async getCode(Phone){//codeLogin
      if(Phone&&(/^1[3456789]\d{9}$/.test(Phone))){
        this.countTime();
        let loginData = await login.code({mobile:Phone,roleCode:this.role});
        if(loginData.data.code == 200){
          this.$toast({ message: "已发送短信验证码，请查收" });
        }else {
          // this.$toast({ message: loginData.data.data});
        }
      }else {
        this.$toast({ message: "请输入手机号" });
      }
    },
    //登录获取验证码后倒计时
    countTime(){
      const _this=this;
      if (this.count==0) {
        this.count=60;
        this.textCode='重新获取';
        clearTimeout(this.timer);
        this.showCountDown=false;
      }else{
        this.showCountDown=true;
        this.count--;
        this.timer=setTimeout(function(){
          _this.countTime();
        },1000);
      }
    },
    //忘记密码获取验证码后倒计时
    forgetCountTime(){
      const _this=this;
      if (this.forgetCount==0) {
        this.forgetCount=60;
        this.FtextCode='重新获取';
        clearTimeout(this.Ftimer);
        this.showForgetCount=false;
      }else{
        this.showForgetCount=true;
        this.forgetCount--;
        this.Ftimer=setTimeout(function(){
          _this.forgetCountTime();
        },1000);
      }
    },
    //点击登录
    submitLogin() {
      let params={};
      if(this.selectLogin=='psd'){//账号密码登录
        if (!this.userName) {
          this.$toast({message: "请填写用户名",duration:1000 });
          return;
        }
        if (!this.password) {
          this.$toast({message: "请填写密码",duration:1000 });
          return;
        }
       params = {
         userName: this.userName,
         openId: this.openId,
         password: this.password,
         roleCode: this.role
       };
        console.log(params);
        this.loginPost(params); //登录请求
      }else if(this.selectLogin=='code'){//短信验证码登录
        if (!this.userPhone) {
          this.$toast({message: "请填写手机号",duration:1000 });
          return;
        }
        if (!this.userCode) {
          this.$toast({message: "请填写验证码",duration:1000 });
          return;
        }
        params = {
          mobile:this.userPhone,
          verifyCode:this.userCode,
          openId: this.openId,
          roleCode: this.role
        };
        console.log(params);
        this.codeLogin(params); //登录请求
      }


    },
    clearName() {
      this.userName = "";
      this.userPhone='';
    },
    clearPsd() {
      this.password = "";
    },
    //修改密码
    findPsd(){
      this.showFind=true;
    },
    //验证修改密码时的验证码
    async FVerifyCode(){
      let codeData=await login.fVerifyCode({
        mobile:this.forgetPhone,
        roleCode:this.role,
        verifyCode:this.forgetCode
      });
      if(codeData.data&&codeData.data.code==200){//验证码正确
        this.setNewPsd();
      }
      // else{
      //   this.$toast({message:codeData.data.data});
      // }
    },
    //设置新密码
    async setNewPsd(){
        let params={
          mobile:this.forgetPhone,
          newPw:this.forgetNewPsd,
          roleCode:this.role,
        };
        let psdData = await login.resetNewPsd(params);
        if(psdData.data&&psdData.data.code==200){
          this.showFind=false;
          this.forgetPhone='';
          this.forgetNewPsd='';
          this.forgetCode='';
          clearTimeout(this.Ftimer);
          this.$toast('密码修改成功，请登录');
        }else {
         // this.$toast('密码修改失败');
        }
        console.log(psdData);
    },
    //取消设置新密码
    cancelSet(){
      this.showFind=false;
      this.forgetPhone='';
      this.forgetNewPsd='';
      this.forgetCode='';
    },
    //确认设置新密码
    affirmSet(){
      if(this.forgetPhone && this.forgetNewPsd && this.forgetCode && (/^1[3456789]\d{9}$/.test(this.forgetPhone))){
       this.FVerifyCode();
      }else {
        this.$toast({message: "*号标记项为必填项",duration:1000 });
      }
    },
    //账号密码登录
    async loginPost(params) {
      let loginData = await login.login(params);
      console.log(loginData);
      if(loginData.data.code == 200){
          this.$router.replace({
          name: "selectServiceMerchant",
          params: {
            fname: "selectServiceMerchant"
          }
        });
      }
    },
    //短信验证码登录
    async codeLogin(params) {
      let loginData;
      if(this.role == "agent" || this.role == "finance"){
        loginData = await login.agentCodeLogin(params);
      }else if(this.role == "salesman"){
        loginData = await login.salesCodeLogin(params);
      }
      console.log(loginData);
      if (loginData.data.code == 200) {
        clearTimeout(this.timer);
        // this.$toast("登录成功");
        console.log(this.role);
        this.$router.replace({
          name: "selectServiceMerchant",
          params: {
            fname: "selectServiceMerchant"
          }
        });
        // if (this.role == "agent" || this.role == "finance") {
        //   console.log(this.role);
        //   this.$router.replace({
        //     name: "Administrator",
        //     params: {
        //       fname: "login"
        //     }
        //   });
        // } else if(this.role == "salesman") {
        //   console.log(this.role);
        //   this.$router.replace({
        //     name: "salesman",
        //     params: {
        //       fname: "login"
        //     }
        //   });
        // }
      } else {
        //Dialog({ message: loginData.data.data });
      }
    }
    /*...mapMutations(['setToken','setGrade'])*/
  }
};
</script>

<style scoped lang="stylus" ref="stylesheet/stylus">
@import '../style/common/base.styl';

.login {
  width: 100%;
  height: 100%;

  .box {
    width: 90%;
    height: auto;
    margin: auto;

    .title {
      height: vw(500);
      font-weight: 600;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      .titleText{
        font-size vw(30)
      }

      span {
        font-size: vw(20);
        margin-top: vw(10);
        color #6e6e6e
      }

      img {
        width: vw(150);
        height: vw(150);
      }
    }

    .logintype {
      font-size: vw(50);
      font-weight: vw(20);
      color #4c4c4c
      height: vw(80);
      line-height: vw(80);
    }

    .item {
      width: 100%;
      height: vw(100);
      display: flex;
      align-items: center;
      justify-content: center;
      box-sizing: border-box;
      border-bottom: 1px solid #d9d9d9;

      input {
        height: 80%;
        width: 100%;
        font-size: vw(32);
        background-color: #f7f7f7;
        color: #222222;
        border: none;
        outline: none;
        text-indent: vw(20);
      }

      button {
        height: 100%;
        width: 100%;
        background-color: #689ED7;
        border-radius: vw(12);
        border: none;
        outline: none;
        color: #ffffff;
        font-size: vw(36);
        cursor: pointer;

        button:hover {
          background-color: #271fb4;
        }
      }
      .getCode{
        width vw(300)
        height 80%
        color #fff
        display: flex;
        align-items: center;
        justify-content:center;
        background-color: #07c160
        border-radius vw(5)
      }
      .count{
        background-color: #d4d4d4
      }
    }
    .findPsd{
      width: 100%;
      height: vw(100);
      display: flex;
      align-items: center;
      justify-content: flex-end;
      box-sizing: border-box;
      span{
        color #424242
        height vw(80)
        line-height vw(80)
      }

    }

    .edter {
      margin-top: vw(80);
      font-size vw(30)
      border: none;
      height vw(70)
    }

    .userName {
      border-top: 1px solid #d9d9d9;
    }
    .selectLogin{
      font-size:vw(28);
      margin-top:vw(30);
      text-align:center;
      color #424242
    }
  }
  .modal-wrap {
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
    .findBox{
      width: 95%;
      margin: auto;
      height: vw(480);
      border-radius: vw(10);
      position: relative;
      top: 30%;
      background-color: #FFFFFF;
      padding:0 vw(30);
      box-sizing: border-box;
      .amendPsd{
        width 100%
        height vw(100)
        display flex
        justify-content center
        align-items center
        font-size vw(40)
        font-weight 500

      }
      .inputBox{
        height vw(80)
        display flex
        /*justify-content center*/
        align-items center
        i{
          color red
          width vw(20)
          height vw(20)
          display inline-block
        }
        span{
          display flex
          justify-content center
          align-items center

          width vw(100)
          height vw(50)
        }
        input{
          border none
          padding-left vw(10)
          width vw(500)
          height vw(50)
          border-bottom 1px solid #959595
        }
        .codeBox{
          border none
          width vw(335)
          height vw(50)
          border-bottom 1px solid #959595
        }
        .getCode{
          width vw(160)
          height vw(60)
          border-radius 5px
          margin-left vw(10)
          background-color: #07c160
        }
        .count{
          background-color: #d4d4d4
        }
        .mar_t{
          margin-bottom vw(20)
        }
      }
      .affirmBox{
        width 100%
        height vw(100)
        display flex
        justify-content center
        align-items flex-end
        button{
          width vw(150)
          height vw(50)
        }
        .cancel{
          background-color: #fff
        }
        .affirm{
          margin-left vw(20)
          background-color: #4660d7
          border-radius vw(5)
          color #fff
        }
      }
    }
  }
}
</style>
