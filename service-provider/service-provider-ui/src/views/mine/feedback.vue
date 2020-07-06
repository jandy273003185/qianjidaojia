<template>
    <div class="feedback">
      <van-nav-bar
              title="意见反馈"
              left-text="返回"
              left-arrow
              @click-left="onClickLeft"
      />
      <div class="textBox">
        <van-cell-group>
          <van-field
                  v-model="message"
                  rows="2"
                  type="textarea"
                  maxlength="200"
                  placeholder="请输入您的宝贵意见"
                  show-word-limit
          />

        </van-cell-group>
        <div class="phonenumber">
          <van-field v-model="phoneNumber" placeholder="请输入您的联系方式（选填）" />
          <button class="submit" @click="submit">提 交</button>
        </div>

      </div>

    </div>
</template>
<script>
export default {
  name: "feedback",
  components: {

  },
  computed: {},
  created() {
  },
  data() {
    return {
      message:'',
      phoneNumber:''
    };
  },
  methods: {
    onClickLeft() {
      this.$router.go(-1);
    },
    submit(){
      console.log(565465);
      this.$toast({//轻提示，1s后自动关闭
        message: "暂未开通意见反馈功能，敬请期待",
        duration:1500,   //提示展示时间
        closeOnClickOverlay:true

      });
    }
  }
};
</script>
<style scoped lang="stylus" ref="stylesheet/stylus">
@import '../../style/common/base.styl';
.van-button--normal{
  margin: auto;
}

.feedback{
  width:100%;

  /*.van-nav-bar{//topbar修改默认样式
    background-color: #26a7e9;
    .van-icon-arrow-left:before,.van-nav-bar__text,.van-nav-bar__title{
      color: #FFFFFF;
    }
  }*/

  .textBox{
    width: vw(690);
    margin: vw(30) auto;

    .phonenumber{
      width: 100%;
      height: vw(280);
      margin-top: vw(50);
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      .submit{
        width: vw(600);
        height: vw(60);
        font-size: vw(30);
        border:none;
        outline:none;
        color: #FFFFFF;
        background-color: #26a7e9;
        border-radius:vw(5);
      }
    }
  }
}





</style>