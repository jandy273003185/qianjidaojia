<template>
    <div class="amendPsd">
      <van-nav-bar
              title="修改登录密码"
              left-text="返回"
              left-arrow
              @click-left="onClickLeft"
      />
      <van-cell-group>
        <van-field
                v-model="password_old"
                type="password"
                label="原密码"
                placeholder="请输入原密码"
                required
        />

        <van-field
                v-model="password_new1"
                type="password"
                label="新密码"
                placeholder="请输入新密码"
                required
        />

        <van-field
                v-model="password_new2"
                type="password"
                label="确认密码"
                placeholder="请再次输入新密码"
                required
        />
      </van-cell-group>
      <button class="submit" @click="affirmAmend">确认修改</button>
    </div>
</template>
<script>
  import { Dialog } from 'vant';//弹窗函数，可直接调用
  import { common } from "@/assets/api/interface";

export default {
  name: "feedback",
  components: {

  },
  computed: {},
  created() {
  },
  data() {
    return {
      password_old:'',
      password_new2:'',
      password_new1:''
    };
  },
  methods: {
    onClickLeft() {
      this.$router.go(-1);
    },
    async affirmAmend(){
      let _this=this;
        if(this.password_old && this.password_new1 && this.password_new2){
           if( this.password_new1== this.password_new2){
             const params = {
               loginPw:this.password_old,
               loginNewPw :this.password_new1,
               userId:this.$store.state.userId,
               userType:this.$store.state.role
             };
             const codeBack = await common.updatePwd(params);
              console.log(codeBack);
             if( codeBack.data.code == 200 ){
               Dialog({ message: '修改密码成功' });
               setTimeout(function(){
                 _this.$router.go(-1);
               },1500)
             }else if( codeBack.data.code == 0 ){
              //  Dialog({ message: '原密码填写错误' });
               return;
             }
           }else {
             Dialog({ message: '新密码不一致' });
             return;
           }

        }else {
          Dialog({ message: '请填写密码' });
          return;
        }

    }
  }
};
</script>
<style scoped lang="stylus" ref="stylesheet/stylus">
  @import "../../style/common/base.styl";

  .amendPsd{
    width:100%;

     /* .van-nav-bar{//更改topbar背景色和字体颜色
        background-color: #26a7e9;
        .van-icon-arrow-left:before,.van-nav-bar__text,.van-nav-bar__title{
          color: #FFFFFF;
        }
      }*/

      .van-cell--required{
        margin: vw(20) auto;
      }
      .submit{
        display: block;
        width: vw(600);
        height: vw(70);
        border-radius vw(5)
        font-size: cw(40);
        color: #FFFFFF;
        background-color: #26a7e9;
        margin: vw(50) auto;
      }
  }





</style>