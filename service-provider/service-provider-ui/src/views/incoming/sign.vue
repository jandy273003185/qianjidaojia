<template>
  <div class="incoming sign">
    <van-nav-bar title="产品签约" left-text="返回" left-arrow @click-left="changePrepage" />
    <Step currStep="4" />
    <div class="signTit" style="margin-top:0">签约产品</div>
    <div class="stepInfo sign">
      <van-checkbox-group v-model="signPro">
        <div class="row1" v-for="(item,i) in prolist" :key="i">
          <van-checkbox class="check" :name="item" v-model="item.isChecked">{{item.productName}}</van-checkbox>
          <span class="name">结算费率：</span>
          <input type="number" v-model="item.productRate" placeholder="请输入费率" />
          <span class="unit">%</span>
        </div>
      </van-checkbox-group>
      <div class="mix-btn">
        <div class="btn save" v-if="checkedState!='corvidae'" @click="saveIncoming">保存</div>
        <div class="btn" @click="submitIncoming('01')">提交</div>
      </div>
      <!-- 完善保存资料提交 -->
      <div class="btn back" @click="changePrepage">返回</div>
    </div>
  </div>
</template>
<script>
import util from "@/lib/util.js";
import { mapState } from "vuex";
import { Dialog } from "vant";
import { incoming, shopAuditInfo } from "@/assets/api/interface";
export default {
  name: "sign",
  components: {
    Step: () => import("@/components/step")
  },
  data() {
    return {
      initProduct: [], //原始所有产品数据
      signPro: [], //签约产品
      prolist: [], //所有产品
      clickedNext: false,
      params: {}
    };
  },
  computed: {
    ...mapState([
      "checkedState",
      "incoming",
      "incomingReturn",
      "savephotos",
      "roleId",
      "custId",
      "McustId"
    ])
  },
  created() {
    this.getAllProduct(); //获取所有产品
    this.params = Object.assign(
      { userId: this.$store.state.userId },
      this.incoming
    );
  },
  methods: {
    changePrepage() {
      this.$router.push("merchant");
    },
    async getAllProduct() {
      //产品信息
      let params = {
        mchCustId: this.custId,
        productStatus: "00"
      };
      let contractInfo = await shopAuditInfo.contractInfo(params);
      this.prolist = contractInfo.data.data;
      this.initProduct = JSON.parse(JSON.stringify(this.prolist));
    },
    checkProduct() {
      //检验是否签约产品
      let errCount = 0;
      this.signPro.map(item => {
        if (!item.productRate) {
          errCount++;
          this.$toast("请正确填写签约产品费率！");
        } else {
          let productId = item.productId;
          let productRate;
          this.initProduct.map(pro => {
            if (pro.productId == productId) {
              productRate = pro.productRate;
            }
          });
          let minRate = parseFloat(productRate);
          let maxRate = parseFloat(productRate) + 0.1;
          if (parseFloat(item.productRate) < minRate ||parseFloat(item.productRate) > maxRate) {
            errCount++;
            item.productRate ='';
            this.$toast(item.productName + "产品费率需在" + minRate +"~" + maxRate + "之间！");
          }
        }
      });
      return errCount;
    },
    saveIncoming() {//保存
      let errCount = this.checkProduct();
      if (errCount == 0) {
        Dialog.confirm({
          message: "请确认是否保存？"
        })
          .then(() => {
            let count = 0;
            if (count < 1) {
              count++;
              let fullParams = Object.assign(this.incoming, this.params, {
                roleId: this.roleId,
                state: "05",
                productInfos: JSON.stringify(this.signPro), //产品
                custScanCopys: util.getAllPhotos(this.savephotos)
              });
              this.$store.commit("setincoming", fullParams);
              this.insertIncoming(fullParams); //提交请求
            }
          })
          .catch(() => {
            // on cancel
          });
      }
    },
    submitIncoming(state) {
      //提交
      this.clickedNext = true;
      let errCount = this.checkProduct();
      if (errCount == 0) {
        Dialog.confirm({
          title: "提示",
          message: "请确认是否提交？"
        })
          .then(() => {
            let fullParams = Object.assign(this.incoming, this.params, {
              roleId: this.roleId,
              state: state,
              custId: this.McustId,
              productInfos: JSON.stringify(this.signPro), //产品
              custScanCopys: util.getAllPhotos(this.savephotos)
            });
            this.$store.commit("setincoming", fullParams);
            console.log(fullParams);
            this.insertIncoming(fullParams); //提交请求
          })
          .catch(() => {});
      }
    },
    async insertIncoming(params) {
      let info = await incoming.insertIncoming(params);
      if (info.data.code == 200) {
        this.$toast.success("成功");
      } else {
        this.$toast("进件信息添加失败！");
      }
      if (info.data.code == 200) {
        if (this.roleId == "3") {
          //业务员
          this.$router.push("/salesman");
        }
        if (this.roleId == "2") {
          //管理员
          this.$router.push("/Administrator");
        }
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
</style>