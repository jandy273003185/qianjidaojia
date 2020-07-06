<template>
  <div class="failedBox">
    <van-nav-bar class="navBar" left-text="返回" title="详情"  left-arrow @click-left="onClickLeft" />
    <div class="failed">

      <div>
        <img src="../assets/images/home/warning.png" alt="失败" />
        <span class="time">审核失败原因</span>
        <span class="font_E">{{allInfoList.audit_time}}</span>
      </div>
      <div>
        <span class="font_E">{{allInfoList.message}}</span>
      </div>
    </div>
    <div class="buttonBox">
      <button  @click="toUpdatePage" >更新资料</button>
      <div class="retuan" @click="onClickLeft">返回</div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapState } from "vuex";
import { shopAuditInfo } from "../assets/api/interface";
export default {
  name: "searchShop",
  data() {
    return {
      value: "",
      allInfoList: ""
    };
  },
  computed: {
    ...mapState(["McustId"])
  },
  mounted() {
    this.getShopDetail();
  },
  methods: {
    toUpdatePage() {//进入进件，修改资料
      this.$router.push({
        name: "baseInfo",
        params: { type: "corvidae" }
      });
    },
    onClickLeft() {
      this.$router.go(-1);
    },

    async getShopDetail() {
      //请求商户基本信息
      let listInfo = await shopAuditInfo.shopDefeatedInfo({
        custId: this.McustId //商户Id
      });

      this.allInfoList = listInfo.data.data[0];
    }
  }
};
</script>

<style scoped lang="stylus" ref="stylesheet/stylus">
@import '../style/common/base.styl';

.failedBox {

  .failed {
    width: 100%;
    height: vw(400);
    margin: vw(20) auto;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #ffffff;



    div {
      width 90%
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      flex-wrap: wrap;
      img {
        width: vw(120);
        height: vw(120);
      }
      .time{
        margin-top vw(20)
      }
      .font_E {
        color: #767676;
        margin-top vw(20)
      }
    }
  }

  .buttonBox {
    width: 90%;
    margin: vw(60) auto;
    height: vw(150);
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: column;
    font-size vw(34)
    button{
      height vw(70)
      width 100%
      color #fff
      border-radius vw(5)
      background-color: #699dd7;
    }
    .retuan {
      color: #699dd7;
      width vw(100)
      height vw(50)
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>
