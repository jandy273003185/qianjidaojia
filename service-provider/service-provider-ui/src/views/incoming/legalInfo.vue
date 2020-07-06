<template>
  <div class="incoming legalInfo">
    <van-nav-bar title="法人信息" left-text="返回" left-arrow @click-left="changePrepage" />
    <Step currStep="2" />
    <div class="stepInfo" ref="baseform">
      <div class="row-img">
        <div class="stit"  :class="{'active':(clickedNext&&(!params.identityCardFront||!params.identityCardReverse))}" >
          法人身份证照片
          <span>(必须)</span>
        </div>
        <div class="img-col">
          <van-uploader
            name="certAttribute1"
            v-model="photos.identityCardFront"
            :after-read="afterReadImg"
            :max-count="1"
            preview-size="auto"
          >
            <van-button icon="photo" type="primary">身份证正面照</van-button>
          </van-uploader>
          <van-uploader
             name="certAttribute2"
            :after-read="afterReadImg"
            v-model="photos.identityCardReverse"
            :max-count="1"
            preview-size="auto"
          >
            <van-button icon="photo" type="primary">身份证反面照</van-button>
          </van-uploader>
        </div>
      </div>
      <div class="row">
        <span class="must">*</span>
        <span class="label" :class="{'active':(clickedNext&&!params.representativeName)}">法人名字</span>
        <input v-model="params.representativeName" placeholder="请输入法人名字" />
      </div>
      <div class="row">
        <span class="must">*</span>
        <span class="label" :class="{'active':(clickedNext&&!params.representativeCertNo)}">法人身份证号</span>
        <input v-model="params.representativeCertNo" placeholder="请输入法人身份证号" />
      </div>
      <div class="row">
        <span class="must">*</span>
        <span
          class="label"
          :class="{'active':(clickedNext&&!params.idTermStart&&!params.idTermEnd)}"
        >身份证有效期</span>
        <div class="data">
          <input
            placeholder="日期选择"
            v-model="params.idTermStart"
            readonly
            @click="datepickerVisiable('idTermStart')"
          /> -
          <input
            placeholder="日期选择"
            v-model="params.idTermEnd"
            readonly
            @click="datepickerVisiable('idTermEnd')"
          />
        </div>
      </div>
      <van-datetime-picker
        title="日期选择"
        class="datepicker"
        v-show="showDatepicker"
        type="year-month"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="confirmDate"
        @cancel="datepickerHide"
      />
      <div class="row">
        <span class="must">*</span>
        <span class="label" :class="{'active':(clickedNext&&!params.contactName)}">联系人名字</span>
        <input v-model="params.contactName" placeholder="请输入联系人名字" />
      </div>
      <div class="row">
        <span class="must">*</span>
        <span class="label" :class="{'active':(clickedNext&&!params.contactMobile)}">联系人手机</span>
        <input type="number" v-model="params.contactMobile" placeholder="请输入联系人手机" />
      </div>
      <div class="btn" @click="getNextStep">下一步</div>
      <div class="btn back" @click="changePrepage">返回</div>
    </div>
  </div>
</template>
<script>
import form from "@/lib/form.js";
import upload from "@/lib/upload.js";
import util from "@/lib/util.js";
import { mapState } from "vuex";
//import { common } from "@/assets/api/interface";
export default {
  name: "legalInfo",
  components: {
    Step: () => import("@/components/step")
  },
  data() {
    return {
      clickedNext: false,
       minDate:new Date(2000, 1, 1),
      maxDate: new Date(2099,1,1),
      showDatepicker: false,
      dateType: "",
      photos: {
        identityCardFront: [],
        identityCardReverse: []
      },
      params: {
        identityCardFront: "",
        identityCardReverse: "",
        representativeName: "", //法人名字
        representativeCertNo: "", //法人身份证号
        idTermStart: "",
        idTermEnd: "",
        contactName: "",
        contactMobile: ""
      }
    };
  },
  computed: {
    ...mapState(["savephotos", "checkedState", "incoming", "incomingReturn"])
  },
  created() {
    //this.$route.params.type
    this.params = Object.assign(this.params, this.incoming);
    this.photos = this.savephotos;
    if (this.checkedState == "corvidae") {
      let custInfo = this.incomingReturn.custInfo;
      let photos = this.incomingReturn.custScanInfoList;
      let urlHead = this.incomingReturn.uri + "" + this.incomingReturn.url;
      util.getPhotos(this, urlHead, photos);
      let params = {
        representativeName: custInfo.representativeName, //法人名字
        representativeCertNo: custInfo.representativeCertNo, //法人身份证号
        idTermStart: custInfo.idTermStart,
        idTermEnd: custInfo.idTermEnd,
        contactName: custInfo.contactName,
        contactMobile: custInfo.contactMobile
      };
      this.params = Object.assign(this.params, params);
    }
  },

  methods: {
    changePrepage() {//返回
      this.$router.push("baseInfo");
    },
    getNextStep() {
      //到下一步 法人信息
      this.clickedNext = true;
       console.log(this.params);
      let count = form.validParams(this,this.params);
      if (count == 0) {
        let fullParams = Object.assign(this.incoming, this.params);
        this.$store.commit("setincoming", fullParams);
        let incomingReturn = this.incomingReturn;
        let custInfo = this.incomingReturn.custInfo || {};
        let all = Object.assign(custInfo, fullParams);
        incomingReturn.custInfo = all;
        this.$store.commit("setincomingReturn", incomingReturn);
        this.$store.commit("setPhotos", this.photos);
        this.$store.commit("setCheckedState", this.checkedState);
        this.$router.push("merchant");
      }
    },
    afterReadImg(file,detail) {
      upload.onReadImg(file,detail.name, this)
    },
    datepickerVisiable(type) {
      this.dateType = type;
      this.showDatepicker = true;
    },
    datepickerHide() {
      this.showDatepicker = false;
    },
    confirmDate(e) {
      let getData = util.timeFormat(e);
      this.params[this.dateType] = getData;
       if(this.dateType=='idTermStart'){
        this.minDate=new Date(getData)
      }else{
        this.minDate=new Date(2000, 1, 1)
      } 
      this.showDatepicker = false;
    },
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
</style>