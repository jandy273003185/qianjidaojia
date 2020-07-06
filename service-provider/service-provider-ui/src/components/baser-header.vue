<template>
  <div class="base-header">
    <router-link :to="role=='agent'||role=='finance'?'/Administrator':'/salesman'">
      <img v-show="!isHome" src="../assets/images/header/home.png" alt />
      <img v-show="isHome" src="../assets/images/header/home1.png" alt />
      <span>主页</span>
    </router-link>
    <router-link to="/shop">
      <img v-show="!isShop" src="../assets/images/header/shop.png" alt />
      <img v-show="isShop" src="../assets/images/header/shop1.png" alt />

      <span>商户</span>
    </router-link>
    <router-link to="/goods">
      <img v-show="!isGoods" src="../assets/images/header/goods.png" alt />
      <img v-show="isGoods" src="../assets/images/header/goods1.png" alt />
      <span>交易</span>
    </router-link>
    <router-link to="/mine">
      <img v-show="!isMine" src="../assets/images/header/mine.png" alt />
      <img v-show="isMine" src="../assets/images/header/mine1.png" alt />
      <span>我的</span>
    </router-link>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapState } from "vuex";
export default {
  name: "baser-header",
  data() {
    return {
      currRouter: "",
      isHome: true,
      isShop: true,
      isGoods: true,
      isMine: true
    };
  },
  computed: {
    ...mapState(["role"])
  },
  watch: {
    currRouter: function() {
      this.isHome = this.$route.path == "/Administrator" || this.$route.path == "/salesman"? true : false;
      this.isShop = this.$route.path == "/shop" ? true : false;
      this.isGoods = this.$route.path == "/goods" ? true : false;
      this.isMine = this.$route.path == "/mine" ? true : false;
    }
  },
  mounted() {
    this.currRouter = this.$route.path;
  }
};
</script>

<style scoped lang="stylus" ref="stylesheet/stylus">
@import '../style/common/base.styl';

.base-header {
  width: 100%;
  height: vw(100);
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 vw(60);
  box-sizing: border-box;
  z-index: 999;
  position: fixed;
  bottom: 0;
  left: 0;

  a {
    width: vw(120);
    height: 80%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: vw(24);
    color: #000;

    img {
      width: vw(44);
      height: vw(44);
    }
  }

  .router-link-exact-active {
    color: #699dd7;
  }
}
</style>
