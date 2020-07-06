<template>
  <div class="about">
    <van-nav-bar title="关于" left-text="返回" left-arrow @click-left="changePrepage" />
    <div class="logo">
      <img src="../../assets/images/login/login.png" />
      深圳市七分钱网络科技有限公司
    </div>
    <router-link to="/companyInfo" class="item">
      关于我们
      <van-icon class="icon" name="arrow" />
    </router-link>
    <router-link to="/feedback" class="item">
      意见反馈
      <van-icon class="icon" name="arrow" />
    </router-link>
    <div class="copyright">
      <div>七分钱</div>©2014-2019 qifenqian.com.All rights reserved.
    </div>
  </div>
</template>
<script>
  export default {
    name: "about",
    components: {},
    computed: {},
    created() {},
    data() {
      return {};
    },
    methods: {
      changePrepage() {
        this.$router.go(-1);
      }
    }
  };
</script>
<style lang="scss" scoped>
  @import "../../style/views/mine.scss";
</style>