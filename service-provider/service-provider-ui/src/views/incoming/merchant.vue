<template>
  <div class="incoming merchant">
    <van-nav-bar title="结算信息" left-text="返回" left-arrow @click-left="changePrepage" />
    <Step currStep="3" />
    <div class="stepInfo">
      <div ref="baseform">
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&(!params.compMainAcct||params.compMainAcct.length<5))}">结算账号</span>
          <input
            type="number"
            v-model="params.compMainAcct"
            placeholder="请输入结算账号"
          />
           <!--   @blur="getBankName(params.compMainAcct)" -->
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.compAcctBank)}">开户银行</span>
          <!-- v-model="params.compAcctBank" -->
          <input v-model.trim="params.bankName" @focus="focusBankName" placeholder="请输入开户银行" />
        </div>
        <van-picker
          v-show="bankpicker"
          show-toolbar
          title="开户银行"
          :columns="bankList"
          value-key="bankName"
          @cancel="onCancelBank"
          @confirm="onConfirmBank"
        />
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.bankProvinceName)}">开户省份</span>
          <input
            v-model="params.bankProvinceShow"
            placeholder="请选择省份"
            readonly
            @click="getInitAddress"
          />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.bankCityName)}">开户城市</span>
          <input v-model="params.bankCityShow" placeholder="请选择城市" readonly @click="getCity" />
        </div>
        <van-picker
          v-if="provincepicker"
          show-toolbar
          title="省份"
          :columns="provinceList"
          value-key="bankProvinceName"
          @cancel="onCancelAdd"
          @confirm="onConfirmProvince"
        />
        <van-picker
          v-if="citypicker"
          show-toolbar
          title="城市"
          :columns="cityList"
          value-key="bankCityName"
          @cancel="onCancelAdd"
          @confirm="onConfirmCity"
        />
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.branchBank)}">开户支行</span>
          <input
            v-model="params.bankbranchName"
            readonly
            placeholder="选择开户支行"
            @click="getBankBranch"
          />
        </div>
        <van-picker
          v-show="bankbranchpicker"
          show-toolbar
          title="开户支行"
          :columns="bankbranchList"
          value-key="branchBankName"
          @cancel="onCancelBankbranch"
          @confirm="onConfirmBankbranch"
        />

        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.bankAcctName)}">开户人</span>
          <input v-model="params.bankAcctName" placeholder="请输入开户人" />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.compMainAcctType)}">结算类型</span>
          <input type="text" v-model="params.compMainAcctTypeShow" readonly placeholder="请选择" @click="showcomPicker"/>
           <van-picker
          v-if="compicker"
          show-toolbar
          title="结算类型"
          :columns="[{name:'对公',val:'01'},{name:'对私',val:'02'}]"
          value-key="name"
          @cancel="onCancelAcctType"
          @confirm="onConfirmAcctType"
        />
        </div>
        <div class="row-img" v-if="params.compMainAcctType=='01'">
          <div class="stit" :class="{'active':(clickedNext&&!params.licenceForOpeningAccounts)}">
            开户许可证照片
            <span>(必须)</span>
          </div>
          <van-uploader
            name="licenceForOpeningAccounts"
            :after-read="uploadImg"
            :before-delete="deleteImg"
            v-model="photos.licenceForOpeningAccounts"
            :max-count="1"
            preview-size="auto"
          >
            <van-button icon="photo" type="primary">上传开户许可证照</van-button>
          </van-uploader>
        </div>
        <div class="row-img" v-if="params.compMainAcctType=='02'">
          <div class="stit" :class="{'active':(clickedNext&&!params.bankCardFront)}">
            银行卡照片
            <span>(必须)</span>
          </div>
          <van-uploader
            name="bankCardFront"
            :after-read="uploadImg"
            :before-delete="deleteImg"
            v-model="photos.bankCardFront"
            :max-count="1"
            preview-size="auto"
          >
            <van-button icon="photo" type="primary">上传银行卡照片</van-button>
          </van-uploader>
        </div>
        <div class="btn" @click="getNextStep">下一步</div>
        <div class="btn back" @click="changePrepage">返回</div>
      </div>
    </div>
  </div>
</template>
<script>
import form from "@/lib/form.js";
import upload from "@/lib/upload.js";
import util from "@/lib/util.js";
/* import bankInfo from "@/lib/bankInfo.js"; */
import { mapState } from "vuex";
import { common, incoming } from "@/assets/api/interface";
export default {
  name: "merchant",
  components: {
    Step: () => import("@/components/step")
  },
  data() {
    return {
      maxSize: 2097152,
      clickedNext: false,
      compicker:false,
      provincepicker: false,
      citypicker: false,
      bankpicker: false,
      bankbranchpicker: false,
      bankList: [],
      provinceList: [],
      cityList: [],
      bankbranchList: [],
      provinceId: "",
      cityId: "",
      canChange: false,
      photos: {
        licenceForOpeningAccounts: [],
        bankCardFront: []
      },
      params: {
        compMainAcct: "",//账号
        compAcctBank: "",//开户行
        bankProvinceName: "", //省
        bankProvinceShow: "",
        bankCityName: "", //市
        bankCityShow: "",
        branchBank: "",//开户支行
        bankAcctName: "",//开户人
        bankName: "", //显示 开户行
        bankbranchName: "", //显示  支行
        compMainAcctType: "",
        compMainAcctTypeShow:'',
        licenceForOpeningAccounts: "",
        bankCardFront: ""
      }
    };
  },
  computed: {
    ...mapState(["checkedState", "incoming", "incomingReturn", "savephotos"])
  },
  watch: {
    "params.bankName": function(newVal, oldVal) {
      if (newVal && oldVal) {
        this.changeBankHead(newVal);
      } else {
        if (this.canChange) {
          this.changeBankHead(newVal);
        }
      }
    }
  },
  created() {
    this.params = Object.assign(this.params, this.incoming);
    this.photos = this.savephotos;
    if (this.checkedState == "corvidae") {
      let custInfo = this.incomingReturn.custInfo;
      let banks;
      if(this.incomingReturn.banks&&this.incomingReturn.banks.length>0){
        banks=this.incomingReturn.banks[0];
      }else{
        banks=[{
          bankName:'', //显示 开户行
          bankbranchName:'', //显示  支行
        }];
      }
      let photos = this.incomingReturn.custScanInfoList;
      let urlHead = this.incomingReturn.uri + "" + this.incomingReturn.url;
      util.getPhotos(this, urlHead, photos);
      let bankProvinces;
      if(this.incomingReturn.bankProvinces&&this.incomingReturn.bankProvinces.length>0){
         bankProvinces = this.incomingReturn.bankProvinces[0];
      }else{
       bankProvinces=[{
          bankProvinceName:'', //省
          bankProvinceShow:'',//省 显示
          bankCityName:'', //市
          bankCityShow:'',//市 显示
       }];
      }
      let compMainAcctTypeShow;
      if(custInfo.compMainAcctType=='01'){
        compMainAcctTypeShow="对公"
      }
       if(custInfo.compMainAcctType=='02'){
        compMainAcctTypeShow="对私"
      }
      let params = {
        compMainAcct: custInfo.compMainAcct||'',//银行卡号
        compAcctBank: custInfo.compAcctBank||'',//开户行
        bankProvinceName: bankProvinces.bankProvinceId||'', //省
        bankProvinceShow: bankProvinces.bankProvinceName||'',//省 显示
        bankCityName: bankProvinces.bankCityId||'', //市
        bankCityShow: bankProvinces.bankCityName||'',//市 显示
        branchBank: custInfo.branchBank||'',//开户支行
        bankAcctName: custInfo.bankAcctName||'',//开户人
        bankName:banks.bankName||'', //显示 开户行
        bankbranchName:banks.branchBankName||'', //显示  支行
        compMainAcctType: custInfo.compMainAcctType||'',
        compMainAcctTypeShow:compMainAcctTypeShow||''
      };
      this.params = Object.assign(this.params, params);
    }
  },

  methods: {
    changePrepage() {
      this.$router.push("legalInfo");
    },
    showcomPicker(){
       this.compicker=true;
    },
    onCancelAcctType(){
      this.compicker=false;
    },
    onConfirmAcctType(value){
      this.params.compMainAcctTypeShow=value.name;
      this.params.compMainAcctType=value.val;
      this.compicker=false;
    },
    focusBankName(){
      this.canChange = true;
    },
      //识别银行卡号
    /* getBankName(cardNo) {
      this.canChange = true;
      if (cardNo && cardNo.length > 0) {
        let bank = bankInfo(cardNo);
        if (bank && bank.bankName) {
          this.params.bankName = bank.bankName;
        } else {
          this.params.bankName = "";
          this.bankList = [];
          this.$toast("请检查您结算账号！");
        }
      }
    }, */
    async changeBankHead(name = "") {
      let res = await incoming.bankHead({ bankName: name });
      if (res.data.code == 200) {
        this.bankpicker = true;
        this.bankList = res.data.data;
        this.bankbranchName = ""; //清除支行数据
        this.params.branchBank = "";
        this.bankbranchpicker = false;
      } else {
        this.$toast("无法获取开户银行");
      }
    },
    onCancelBank() {
      //开户行取消
      this.params.bankName = "";
      this.params.compAcctBank = "";
      this.bankpicker = false;
    },
    onConfirmBank(value) {
      //开户行确认
      this.params.bankName = value.bankName;
      this.params.compAcctBank = value.bankCode;
      this.bankpicker = false;
      this.params.bankbranchName = ""; //清除支行数据
      this.params.branchBank = "";
      this.bankbranchpicker = false;
    },
    async getBankBranch() {
      //获取支行信息
      if (!this.params.compAcctBank || !this.params.bankCityName) {
        this.$toast("请确认是否选择省市及开户行！");
      } else {
        if (this.provincepicker || this.citypicker) {
          this.$toast("请先确认省市！");
        } else {
          let res = await incoming.bankBranch({
            cityCode: this.params.bankCityName,
            bankCode: this.params.compAcctBank
          });
          if (res.data.code == 200) {
            this.bankbranchpicker = true;
            this.bankbranchList = res.data.data;
          }
        }
      }
    },
    onCancelBankbranch() {
      //取消支行
      this.bankbranchName = "";
      this.params.branchBank = "";
      this.bankbranchpicker = false;
    },
    onConfirmBankbranch(value) {
      //确认支行
      this.params.bankbranchName = value.branchBankName;
      this.params.branchBank = value.branchBankCode;
      this.bankbranchpicker = false;
    },
    async getInitAddress() {
      //获取省份
      if (this.bankpicker) {
        this.$toast("请确认开户银行！");
      } else {
        let res = await common.bankAddress();
        if (res.data.code == 200) {
          this.provincepicker = true;
          this.provinceList = res.data.data;
        } else {
          this.$toast("无法获取省份！");
        }
      }
    },
    async getCity() {
      if (this.params.bankProvinceName) {
        this.citypicker = true;
        let res = await common.bankAddress({
          bankProvinceId: this.params.bankProvinceName
        });
        console.log(res);
        this.cityList = res.data.data;
      } else {
        this.$toast("请先选择省份！");
      }
    },
    onCancelAdd() {
      this.provincepicker = false;
      this.citypicker = false;
      this.blockpicker = false;
    },
    async onConfirmProvince(value) {
      //确认省
      this.params.bankProvinceName = value.bankProvinceId;
      this.params.bankProvinceShow = value.bankProvinceName;
      this.params.bankCityName = ""; //清除市数据
      this.params.bankCityShow = "";
      this.provincepicker = false;
      this.params.bankbranchName = ""; //清除支行数据
      this.params.branchBank = "";
      this.bankbranchpicker = false;
    },

    async onConfirmCity(value) {
      //确认市
      this.params.bankCityName = value.bankCityId;
      this.params.bankCityShow = value.bankCityName;
      this.citypicker = false;
      this.params.bankbranchName = ""; //清除支行数据
      this.params.branchBank = "";
      this.bankbranchpicker = false;
    },
    getNextStep() {
      //到下一步  签约产品
      this.clickedNext = true;
      let count = form.validParams(this, this.params);
      if (count == 0) {
        let fullParams = Object.assign(this.incoming, this.params);
        this.$store.commit("setincoming", fullParams);
        let incomingReturn = this.incomingReturn;
        let custInfo = this.incomingReturn.custInfo || {};
        let bankProvinces = [
          {
            bankProvinceId: this.params.bankProvinceName,
            bankProvinceName: this.params.bankProvinceShow,
            bankCityId: this.params.bankCityName,
            bankCityName: this.params.bankCityShow
          }
        ];
        let all = Object.assign(custInfo, fullParams);
        incomingReturn.custInfo = all;
        incomingReturn.bankProvinces = bankProvinces;
        this.$store.commit("setincomingReturn", incomingReturn);
        this.$store.commit("setPhotos", this.photos);
        this.$store.commit("setCheckedState", this.checkedState);
        this.$router.push("sign");
      }
    },
    deleteImg(file, detail) {
      this.params[detail.name] = "";
      return true;
    },
    uploadImg(file, detail) {
      //图片上传
       upload.onReadUpload(file,detail.name,this)
/*       this.$toast.loading({
        message: "图片上传中..",
        forbidClick: true,
        duration: 0
      });
      upload.blobToBase64(file.file, detail.name, this); */
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
</style>