<template>
  <div class="incoming baseInfo">
    <van-nav-bar title="基本信息" left-text="返回" left-arrow @click-left="changePrepage" />
    <Step currStep="1" />
    <div class="stepInfo">
      <div ref="baseform">
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.merchantAccount)}">商户账号</span>
          <input
            type="number"
            :readonly="pagetype=='corvidae'"
            v-model="params.merchantAccount"
            @blur="checkPhone(params.merchantAccount)"
            placeholder="请输入手机号"
          />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.custType)}">商户类型</span>
          <input type="text" readonly v-model="params.custTypeShow" placeholder="请选择" @click="showcustTypePicker"/>
        </div>
        <van-picker
          v-if="custpicker"
          show-toolbar
          title="商户类型"
          :columns="[{name:'小微商户',val:'0'},{name:'企业',val:'1'},{name:'个体',val:'2'}]"
          value-key="name"
          @cancel="onCancelCusttype"
          @confirm="onConfirmCusttype"
        />
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.custName)}">商户名字</span>
          <input v-model="params.custName" placeholder="请输入商户名称" />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.shortName)}">商户简称</span>
          <input v-model="params.shortName" placeholder="请输入商户简称" />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.contactPhone)}">客服号码</span>
          <input type="number" v-model="params.contactPhone" placeholder="请输入客服电话号码" />
        </div>
        <div class="row">
          <span class="must">*</span>
          <span class="label" :class="{'active':(clickedNext&&!params.custAdd)}">商户地址</span>
          <input
            class="address"
            v-model="params.provinceName"
            placeholder="选择省份"
            readonly
            @click="getInitAddress"
          />
          <input
            class="address"
            v-model="params.cityName"
            placeholder="选择市"
            @click="getTapCity"
            readonly
          />
          <input
            class="address"
            v-model="params.countryName"
            placeholder="选择区"
            @click="getTapCountry"
            readonly
          />
        </div>
        <van-picker
          v-show="provincepicker"
          show-toolbar
          title="省份"
          :columns="provinceList"
          value-key="provinceName"
          @cancel="onCancelAdd"
          @confirm="onConfirmProvince"
        />
        <van-picker
          v-show="citypicker"
          show-toolbar
          title="城市"
          :columns="cityList"
          value-key="cityName"
          @cancel="onCancelAdd"
          @confirm="onConfirmCity"
        />
        <van-picker
          v-show="blockpicker"
          show-toolbar
          title="区/县"
          :columns="blockList"
          value-key="areaName"
          @cancel="onCancelAdd"
          @confirm="onConfirmBlock"
        />
        <div class="row">
          <input v-model="params.custAdd" placeholder="请输入详细地址" />
        </div>
        <div class="row-img" v-show="params.custType&&params.custType!='0'">
          <div class="stit" :class="{'active':(clickedNext&&!params.businessLicense)}">
            营业执照照片
            <span>(必须)</span>
          </div>
          <van-uploader
            name="businessPhoto"
            :after-read="afterReadImg"
            :before-delete="deleteImg"
            :max-count="1"
            v-model="photos.businessPhoto"
            preview-size="auto"
          >
            <van-button icon="photo" type="primary">上传营业执照</van-button>
          </van-uploader>
        </div>

        <div class="row" v-show="params.custType&&params.custType!='0'">
          <span class="label">营业执照编号</span>
          <input v-model="params.businessLicense" placeholder="请输入编号" />
        </div>
        <div class="row" v-show="params.custType&&params.custType!='0'">
          <span class="label">营业执照有效期</span>
          <div class="data">
            <input
              placeholder="开始日期"
              v-model="params.businessTermStart"
              readonly
              @click="datepickerVisiable('businessTermStart')"
            /> -
            <input
              placeholder="截止日期"
              v-model="params.businessTermEnd"
              readonly
              @click="datepickerVisiable('businessTermEnd')"
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

        <div class="row-img">
          <div class="stit" :class="{'active':(clickedNext&&!params.shopFrontDesk)}">
            门头照照片
            <span>(必须)</span>
          </div>
          <van-uploader
            name="shopFrontDesk"
            :after-read="uploadImg"
            v-model="photos.shopFrontDesk"
            :max-count="1"
            preview-size="auto"
            :before-delete="deleteImg"
          >
            <van-button icon="photo" type="primary">上传门头照照片</van-button>
          </van-uploader>
        </div>
        <div class="row-img" :class="{'active':(clickedNext&&!params.shopInterior)}">
          <div class="stit" :class="{'active':(clickedNext&&!params.shopInterior)}">
            店内照
            <span>(必须)</span>
          </div>
          <van-uploader
            name="shopInterior"
            ref="shopInterior"
            v-model="photos.shopInterior"
            :after-read="uploadImg"
            :max-count="1"
            preview-size="auto"
            :before-delete="deleteImg"
          >
            <van-button icon="photo" type="primary">上传店内照片</van-button>
          </van-uploader>
        </div>
        <div class="row-img"> <!--  :class="{'active':(clickedNext&&!params.specialBusiness)}" -->
          <div class="stit">特殊行业资质照</div>
          <div>
            <van-uploader
              name="specialBusiness"
              :after-read="uploadImg"
              v-model="photos.specialBusiness"
              :max-count="1"
              preview-size="auto"
              :before-delete="deleteImg"
            >
              <van-button icon="photo" type="primary">上传特殊行业资质照</van-button>
            </van-uploader>
          </div>
        </div>
        <div class="row-img">
          <div
            class="stit"
            :class="{'active':(clickedNext&&!params.electronicSignaturePhoto)}"
          >电子签名照</div>

          <van-uploader
            name="electronicSignaturePhoto"
            v-model="photos.electronicSignaturePhoto"
            :after-read="uploadImg"
            :max-count="1"
            preview-size="auto"
            :before-delete="deleteImg"
          >
            <van-button icon="photo" type="primary">上传电子签名照</van-button>
          </van-uploader>
        </div>
        <div class="row-img">
          <div class="stit">其他资料照</div>
          <div class="img-col">
            <van-uploader
              name="otherPhoto1"
              v-model="photos.otherPhoto1"
              :after-read="uploadImg"
              :max-count="1"
              preview-size="auto"
              :before-delete="deleteImg"
            >
              <van-button icon="photo" type="primary">其他资料照</van-button>
            </van-uploader>
            <van-uploader
              name="otherPhoto2"
              :after-read="uploadImg"
              v-model="photos.otherPhoto2"
              :max-count="1"
              preview-size="auto"
              :before-delete="deleteImg"
            >
              <van-button icon="photo" type="primary">其他资料照</van-button>
            </van-uploader>
          </div>
        </div>
        <div class="btn" @click="getNextStep">下一步</div>
      </div>
    </div>
  </div>
</template>
<script>
import form from "@/lib/form.js";
import util from "@/lib/util.js";
import upload from "@/lib/upload.js";
import { mapState, mapMutations } from "vuex";
import { common, incoming } from "@/assets/api/interface";
import { Dialog } from "vant";
export default {
  name: "baseInfo",
  components: {
    Step: () => import("@/components/step")
  },
  data() {
    return {
      pagetype: "",
      custpicker:false,
      provincepicker: false,
      citypicker: false,
      blockpicker: false,
      provinceList: [],
      cityList: [],
      blockList: [],
      provinceId: "",
      cityId: "",
      clickedNext: false,
      longterm: false,
      minDate: new Date(2000, 1, 1),
      maxDate: new Date(2099, 1, 1),
      showDatepicker: false,
      dateType: "",
      photos: {
        businessPhoto: [], //营业执照
        shopFrontDesk: [], //门头照
        shopInterior: [], //店内照
        specialBusiness: [], //特殊行业照
        electronicSignaturePhoto: [], //电子签名照
        otherPhoto1: [], //其他资料照 1
        otherPhoto2: [] //其他资料照 2
      },
      params: {
        merchantAccount: "",
        custTypeShow:'',
        custType: "",
        custName: "",
        shortName: "",
        contactPhone: "",
        province: "", // 省份代码
        provinceName: "",
        city: "", // 城市代码
        cityName: "",
        country: "", // 区县码
        countryName: "",
        custAdd: "", //地址
        businessLicense: "", //营业执照编号
        businessTermStart: "", //有效期
        businessTermEnd: "",
        shopFrontDesk: "", //门头照
        shopInterior: "", //店内照
        specialBusiness: "", //特殊行业照
        electronicSignaturePhoto: "" //电子签名照
      }
    };
  },
  computed: {
    ...mapState(["incoming", "savephotos", "incomingReturn", "McustId", "role"])
  },
  created() {
    let type = this.$route.params.type; //'corvidae' ;
    this.photos = Object.assign({}, this.savephotos);
    if (type) {
      this.pagetype = type;
      this.$store.commit("setCheckedState", type);
      if (type == "new") {
        this.params = {};
      }
      if (type == "corvidae") {
        //待完善
        this.photos = {};
        this.$store.commit("setPhotos", {});
        this.getIncomingInfo();
      }
    } else {
      this.params = Object.assign(this.params, this.incoming);
    }
  },

  methods: {
    ...mapMutations(["setincomingReturn", "setincoming", "setPhotos"]),
    changePrepage() {
      //返回上一页
      if (this.role == "salesman") {
        this.$router.push("/salesman");
      }
      if (this.role == "agent") {
        this.$router.push("/Administrator");
      }
    },
    getNextStep() {
      //到下一步 法人信息
      this.clickedNext = true;
      let count = form.validParams(this, this.params);
      if (count == 0) {
        let fullParams = Object.assign(this.incoming, this.params);
        this.setincoming(fullParams);
        let incomingReturn = this.incomingReturn;
        let custInfo = this.incomingReturn.custInfo || {};
        let all = Object.assign(custInfo, fullParams);
        incomingReturn.custInfo = all;
        this.setincomingReturn(incomingReturn);
        let newPhotos = this.photos;
        this.setPhotos(newPhotos);
        this.$router.push("legalInfo");
      }
    },
    async checkPhone(merchantAccount) {
      //校验商户账号
      if (merchantAccount) {
        let res = await incoming.checkPhone({
          merchantAccount: merchantAccount
        });
        if (res.data.code == 0) {
          //已绑定
          Dialog({ message: "该商户账号已进件！" });
        }
      }
    },
    async getIncomingInfo() {
      //获取之前提交进件数据
      this.$toast.loading({
        message: "加载中..",
        forbidClick: true,
        duration: 0
      });
      let res = await incoming.getIncoming({ custId: this.McustId }); // custId:'c838b2ed3d524b1bb1db444a48572b19'
      this.$toast.clear();
      this.setincomingReturn(res.data.data);
      let custInfo = res.data.data.custInfo;
      let provinces = res.data.data.provinces[0];
      let custTypeShow;
      if(custInfo.custType=='0'){
        custTypeShow='小微商户'
      }
      if(custInfo.custType=='1'){
        custTypeShow='企业'
      }
      if(custInfo.custType=='2'){
        custTypeShow='个体户'
      }
      let params = {
        merchantAccount: custInfo.merchantAccount,
        custTypeShow:custTypeShow,
        custType: custInfo.custType,
        custName: custInfo.custName,
        shortName: custInfo.shortName,
        contactPhone: custInfo.contactPhone,
        province: provinces.provinceId, // 省份代码
        provinceName: provinces.provinceName,
        city: provinces.cityId, // 城市代码
        cityName: provinces.cityName,
        country: provinces.areaId, // 区县码
        countryName: provinces.areaName,
        custAdd: custInfo.custAdd, //地址
        businessLicense: custInfo.businessLicense, //营业执照编号
        businessTermStart: custInfo.businessTermStart, //有效期开始
        businessTermEnd: custInfo.businessTermEnd,//有效期结束
        shopFrontDesk: "", //门头照
        shopInterior: "", //店内照
        specialBusiness: "", //特殊行业照
        electronicSignaturePhoto: "" //电子签名照
      };
      this.params = Object.assign({}, params);
      let photos = res.data.data.custScanInfoList;
      let urlHead = res.data.data.uri + "" + res.data.data.url;
      let getPhotos = util.getPhotos(this, urlHead, photos);
      this.photos = Object.assign({}, getPhotos);
    },
    showcustTypePicker(){
       this.custpicker=true;
    },
    onCancelCusttype(){
      this.custpicker=false;
    },
    onConfirmCusttype(value){
      this.params.custTypeShow=value.name;
      this.params.custType=value.val;
      this.custpicker=false;
    },
    async getInitAddress() {
      //获取省份
      let res = await common.getAddress();
      if(res.data&&res.data.data){
        this.provincepicker = true;
        this.provinceList = res.data.data;
      }
    },
    async getTapCity() {
      if (this.params.province) {
        let res = await common.getAddress({
          provinceId: this.params.province
        });
        this.cityList = res.data.data;
        this.citypicker = true;
      } else {
        this.$toast("请先选择省份");
      }
    },
    async getTapCountry() {
      if (this.params.province && this.params.city) {
        let res = await common.getAddress({
          cityId: this.params.city
        });
        this.blockList = res.data.data;
        this.blockpicker = true;
      } else {
        this.$toast("请先选择省市");
      }
    },
    onCancelAdd() {
      this.provincepicker = false;
      this.citypicker = false;
      this.blockpicker = false;
    },
    async onConfirmProvince(value) {
      //确认省
      this.params.province = value.provinceId;
      this.params.provinceName = value.provinceName;
      this.provincepicker = false;
      let res = await common.getAddress({
        provinceId: value.provinceId
      });
      this.cityList = res.data.data;
      this.citypicker = true;
    },
    async onConfirmCity(value) {
      //确认市
      this.params.city = value.cityId;
      this.params.cityName = value.cityName;
      this.citypicker = false;
      let res = await common.getAddress({
        cityId: value.cityId
      });
      if(res.data.data&&res.data.data.length>0){
      this.blockList = res.data.data;
      this.blockpicker = true;
      }else{
        if(res.data.data.length==0){
           this.params.country = value.cityId;
           this.params.countryName = value.cityName;
        }
      }
    },
    async onConfirmBlock(value) {
      //确认区
      this.params.country = value.areaId;
      this.params.countryName = value.areaName;
      this.blockpicker = false;
    },
    afterReadImg(file,detail) {
      upload.onReadImg(file,detail.name, this)
    },
    deleteImg(file, detail) {
      if(detail.name!="businessPhoto"){
        this.params[detail.name] = "";
      }
      return true;
    },
    uploadImg(file, detail) {
      //图片上传
      upload.onReadUpload(file,detail.name,this)
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
      if (this.dateType == "businessTermStart") {
        this.minDate = new Date(getData);
      } else {
        this.minDate = new Date(2000, 1, 1);
      }
      this.showDatepicker = false;
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
</style>