<template>
  <!-- 商户列表操作 (新增1 + 预览0 + 商户更新2 + 商户审核3) 不同字段，对应不同状态-->
  <div class='merchanrts-list-operation-page'>
    <el-col :span="24">
      <h2>商户信息</h2>
    </el-col>

    <el-form :model="basicInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :rules="rules" :show-message="false">
      <el-col :span="8">
        <el-form-item label="商户账号" prop="merchantAccount" required>
          <el-input maxlength="11" v-model.trim="basicInfo.merchantAccount" placeholder="请输入手机号" :disabled="basicInfo.editMerchantAccount"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <h2>基本信息</h2>
      </el-col>

      <el-col :span="8">
        <el-form-item label="商户类型" prop="custType" required>
          <el-select v-model="basicInfo.custType" placeholder="请选择">
            <el-option label="企业" value="1"></el-option>
            <el-option label="小微商户" value="0"></el-option>
            <el-option label="个体户" value="2"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="商户名称" prop="custName" required>
          <el-input v-model.trim="basicInfo.custName" placeholder="请输入商户名称"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="商户简称" prop="shortName" required>
          <el-input v-model.trim="basicInfo.shortName" placeholder="请输入商户简称"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="8"><!--报备-->
        <el-form-item label="是否有证商户" prop="suiXingMerchantType" required>
          <el-select v-model="basicInfo.suiXingMerchantType" placeholder="请选择">
            <el-option label="有证商户" value="01"></el-option>
            <el-option label="无证商户" value="02"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="商户邮箱" prop="merchantEmail" required>
          <el-input v-model.trim="basicInfo.merchantEmail" placeholder="请输入商户邮箱"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="客服号码" prop="contactPhone" required>
          <el-input v-model.trim="basicInfo.contactPhone" placeholder="请输入客服号码"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <el-form-item label="商户地址" prop="province" required class="form-item-address">
          <el-select v-model="basicInfo.province" placeholder="请选择">
            <el-option v-for="(item,index) in province" :key="index" :label="item.provinceName" :value="item.provinceId" />
          </el-select>
        </el-form-item>
        &nbsp;
        <el-form-item prop="city" required class="form-item-address">
          <el-select v-model="basicInfo.city" placeholder="请选择">
            <el-option v-for="(item,index) in cityList" :key="index" :label="item.cityName" :value="item.cityId" />
          </el-select>
        </el-form-item>
        &nbsp;
        <el-form-item prop="country" required class="form-item-address">
          <el-select v-model="basicInfo.country" placeholder="请选择">
            <el-option v-for="(item,index) in countryList" :key="index" :label="item.areaName" :value="item.areaId" />
          </el-select>
        </el-form-item>
        &nbsp;
        <el-form-item prop="custAdd" required class="form-item-address">
          <el-input class="address-input" v-model.trim="basicInfo.custAdd" placeholder="详细地址" />
        </el-form-item>
      </el-col>

      <el-col :span="8"><!--报备-->
        <el-form-item label="营业执照名称" prop="cprRegNmCn" required>
          <el-input v-model.trim="basicInfo.cprRegNmCn" placeholder="请输入营业执照名称"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="营业执照编号" prop="businessLicense" required>
          <el-input v-model.trim="basicInfo.businessLicense" placeholder="请输入营业执照编号"></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="所属业务人员" prop="custManager">
          <el-select v-model="basicInfo.custManager" filterable placeholder="请输入内容">
            <el-option v-for="(item,index) in comUserList" :key="index" :label="item.userName" :value="item.userId" />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="16">
        <div class="business-Term-choose">
          <el-form-item prop="businessTermStart" label="营业执照有效期" required>
            <el-date-picker v-model="basicInfo.businessTermStart" format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="请选择" />
          </el-form-item>
          <span class="item">-</span>
          <el-form-item prop="businessTermEnd" class="finite-item" required>
            <el-date-picker :disabled="setBusinessTermEndFiniteChecked" format="yyyy-MM-dd" value-format="yyyy-MM-dd" v-model="basicInfo.businessTermEnd" :placeholder="setBusinessTermEndFinite" />
            <el-checkbox class="pl" v-model="setBusinessTermEndFiniteChecked">长期</el-checkbox>
          </el-form-item>
        </div>
      </el-col>

      <el-col :span="8">
        <el-form-item label="所属代理商" prop="agentName">
          <el-select v-model="basicInfo.agentName" filterable placeholder="请输入内容">
            <el-option v-for="(item,index) in comAgentList" :key="index" :label="item.custName" :value="item.merchantCode" />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="businessPhoto" label="营业执照扫描件" required>
          <SiteUpload :src.sync="basicInfo.businessPhoto" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="doorPhoto" label="门头照照片" required>
          <SiteUpload :src.sync="basicInfo.doorPhoto" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="shopInterior" label="店内照" required>
          <SiteUpload :src.sync="basicInfo.shopInterior" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="shopCheckStand" label="店内前台照" required>
          <SiteUpload :src.sync="basicInfo.shopCheckStand" />
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <h2>法人信息</h2>
      </el-col>

      <el-col :span="8">
        <el-form-item label="法人真实姓名" prop="representativeName" required>
          <el-input v-model.trim="basicInfo.representativeName" placeholder="请输入法人真实姓名"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="法人身份证号码" prop="representativeCertNo" required>
          <el-input v-model="basicInfo.representativeCertNo" placeholder="请输入法人身份证号码"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <div class="business-Term-choose">
          <el-form-item prop="idTermStart" label="身份证有效期" required>
            <el-date-picker v-model="basicInfo.idTermStart" format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="请选择" />
          </el-form-item>
          <span class="item center">-</span>
          <el-form-item prop="idTermEnd" class="finite-item">
            <el-date-picker :disabled="setIdtermEndFiniteChecked" format="yyyy-MM-dd" value-format="yyyy-MM-dd" v-model="basicInfo.idTermEnd" :placeholder="setIdTermTermEndFinite" />
            <el-checkbox class="pl" v-model="setIdtermEndFiniteChecked">长期</el-checkbox>
          </el-form-item>
        </div>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="certAttribute1" label="法人身份证正面" required>
          <SiteUpload :src.sync="basicInfo.certAttribute1" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="certAttribute2" label="法人身份证背面" required>
          <SiteUpload :src.sync="basicInfo.certAttribute2" />
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <h2>联系信息</h2>
      </el-col>

      <el-col :span="8">
        <el-form-item label="联系人姓名" prop="contactName" required>
          <el-input v-model.trim="basicInfo.contactName" placeholder="请输入联系人姓名"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="联系人手机号码" prop="contactMobile" required>
          <el-input maxlength="11" v-model.trim="basicInfo.contactMobile" placeholder="请输入联系人手机号码" ></el-input>
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <h2>结算信息</h2>
      </el-col>

      <el-col :span="8">
        <el-form-item label="结算账号" prop="compMainAcct" required>
          <el-input v-model.trim="basicInfo.compMainAcct" placeholder="请输入法人结算账号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户人" prop="bankAcctName" required>
          <el-input v-model.trim="basicInfo.bankAcctName" placeholder="请输入开户人"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户省份" prop="bankProvinceName" required>
          <el-select v-model="basicInfo.bankProvinceName" placeholder="请选择">
            <el-option v-for="(item,index) in province" :key="index" :label="item.provinceName" :value="item.provinceId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="开户城市" prop="bankCityName" required>
          <el-select v-model="basicInfo.bankCityName" placeholder="请选择" no-match-text="请选择开户城市">
            <el-option v-for="(item,index) in mapBankCityList" :key="index" :label="item.cityName" :value="item.cityId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户银行" prop="compAcctBank" required>
          <el-select v-model="basicInfo.compAcctBank" filterable placeholder="请输入内容">
            <el-option v-for="(item,index) in bankList" :key="index" :label="item.bankName" :value="item.bankCode" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户支行" prop="branchBank" required>
          <el-select v-model="basicInfo.branchBank" filterable placeholder="请输入内容">
            <el-option v-for="(item,index) in mapBankChildList" :key="index" :label="item.bankName" :value="item.bankCode" />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="结算类型" prop="compMainAcctType" required>
          <el-select v-model="basicInfo.compMainAcctType" placeholder="请选择">
            <el-option label="对公" value="1"></el-option>
            <el-option label="对私" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <el-form-item prop="openAccount" label="开户许可证" required>
          <SiteUpload :src.sync="basicInfo.openAccount" />
        </el-form-item>
      </el-col>
    </el-form>

    <template v-if="type === '0' || type === '3'">
      <el-col :span="24">
        <h2>操作记录</h2>
      </el-col>
      <el-form :model="operationInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm2" :show-message="false">
        <el-col :span="8">
          <el-form-item label="录入人" prop="name1" required>
            <el-input v-model="operationInfo.name1"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="录入时间" prop="name2" required>
            <el-input v-model="operationInfo.name2"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item v-if="type === '0'" label="审核人" prop="name3" required>
            <el-input v-model="operationInfo.name3"></el-input>
          </el-form-item>
        </el-col>

        <template v-if="type === '0'">
          <el-col :span="8">
            <el-form-item label="审核时间" prop="name4" required>
              <el-input v-model="operationInfo.name4"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="审核记录" prop="name5" required>
              <el-input v-model="operationInfo.name5"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

          </el-col>
        </template>
      </el-form>
    </template>

    <div class="btns">
      <!-- 新增及更新 -->
      <template v-if="type === '1' || type === '2'">
        <el-button type="primary" @click="addSubmit">保存</el-button>
      </template>
      <!-- 审核状态 -->
      <template v-if="type === '3'">
        <el-button type="primary" @click="goToPass">审核通过</el-button>
        <el-button type="primary" @click="goToNoPass">审核不通过</el-button>
      </template>
      <!-- 报备 -->
      <template v-if="type === '4'">
        <el-button type="primary" @click="submitReport">提交报备</el-button>
      </template>
      <el-button type="info" @click="goToBack">关闭</el-button>
    </div>

  </div>
</template>

<script>
import SiteUpload from '@/components/SiteUpload';
import fetch from '@/fetch';
import { isPhoneNo, isBusCode, isEmail, isBankCard } from '@/tools/validate';

export default {
  name: 'Operation',
  components: { SiteUpload },
  data() {
    return {
      type: '',
      isLook: false,
      basicInfo: {
        editMerchantAccount:false,
        merchantAccount: '',
        custType: '',
        custName: '',
        shortName: '',
        merchantEmail: '',
        contactPhone: '',
        province: '',
        city: '',
        country: '',
        custAdd: '',
        businessLicense: '',
        businessTermStart: '',
        businessTermEnd: '',
        custManager: '',
        agentName: '',
        businessPhoto: '',
        doorPhoto: '',
        shopInterior: '',
        shopCheckStand: '',
        representativeName: '',
        representativeCertNo: '',
        idTermStart: '',
        idTermEnd: '',
        certAttribute1: '',
        certAttribute2: '',
        contactName: '',
        contactMobile: '',
        compMainAcct: '',
        bankAcctName: '',
        bankProvinceName: '',
        bankCityName: '',
        compAcctBank: '',
        branchBank: '',
        compMainAcctType: '',
        openAccount: '',
        suiXingMerchantType:'',
        cprRegNmCn:''
      },
      operationInfo: {},
      rules: {
        // merchantAccount: [{ validator: isPhoneNo, trigger: 'blur' }],
        // contactMobile: [{ validator: isPhoneNo, trigger: 'blur' }],
        // merchantEmail: [{ validator: isEmail, trigger: 'blur' }],
        // businessLicense: [{ validator: isBusCode, trigger: 'blur' }]
      },
      // 地址选择
      province: [], //省
      cityList: [],
      countryList: [],
      uploadFileName: '未选择任何文件',
      uploadImageSrc: '',
      setBusinessTermEndFinite: '请选择', //设置营业执照长期
      setBusinessTermEndFiniteChecked: false,
      setIdtermEndFiniteChecked: false,
      setIdTermTermEndFinite: '请选择',
      bankList: [], //银行列表
      comAgentList: [],
      comUserList: [],
      mapBankChildList: [],
      mapBankCityList: [] //
    };
  },
  watch: {
    setBusinessTermEndFiniteChecked(v) {
      if (v) {
        this.basicInfo.businessTermEnd = '';
        this.setBusinessTermEndFinite = '长期';
      } else {
        this.setBusinessTermEndFinite = '请选择';
      }
    },
    setIdtermEndFiniteChecked(v) {
      if (v) {
        this.basicInfo.idTermEnd = '';
        this.setIdTermTermEndFinite = '长期';
      } else {
        this.setIdTermTermEndFinite = '请选择';
      }
    },
    // 地址回显有问题 待改
    // -----------------------  待改  ------------------------------
    'basicInfo.province'(nv, ov) {
      console.log(nv, ov);
      if (nv === ov) return;
      const info = this.basicInfo;
      const city = this.province.find(e => info.province === e.provinceId);
      if(ov){info.city = '';};

      this.cityList = city ? city.cityVOS : [];
    },
    'basicInfo.city'(nv, ov) {
      if (nv === ov) return;
      const info = this.basicInfo;
      const area = this.cityList.find(e => info.city === e.cityId);
      if(ov){info.country = '';}
      this.countryList = area.areaVOS || [];
    },
    'basicInfo.bankProvinceName'(nv, ov) {
      if (nv === ov) return;
      const info = this.basicInfo;
      const city = this.province.find(e => info.bankProvinceName === e.provinceId);

      const data = Object.assign({}, this.basicInfo);
      if(ov){data.bankCityName = '';}
      this.basicInfo = data;
      this.mapBankCityList = city ? city.cityVOS : [];
    },
    // 根据开户银行获取开户支行
    'basicInfo.compAcctBank'(nv, ov) {
      if (nv === ov) return;

      const data = {
        channelCode: '',
        queryBean: { bankCode: nv }
      };
      if (ov) this.basicInfo.branchBank = '';
      fetch.comBranchbankList(data).then(({ data }) => {
        // console.log(data)
        this.mapBankChildList = data;
      });
    }
    // ---------------------  待改  --------------------------------
  },
  created() {
    // 新增1 + 预览0 + 商户更新2 + 商户审核3
    const { type } = this.$route.params;
    const otherType = ['商户预览', '新增商户', '商户更新', '商户审核'];
    const operation = this.getStorage('merchants_operation') || {};

    if (type === '0' || type === '3') this.isLook = true;

    this.type = type;
    this.$emit('update:changeOtherBreadcrumbItem', otherType[type]);
    this.getProvinceList(() => {
      if (type !== '1') {
        fetch.mcMchDetail(operation).then(res => {
          const data = res.data;
          if(data){
            if (data.businessTermEnd === '长期') {
              data.businessTermEnd = '';
              this.setBusinessTermEndFiniteChecked = true;
            }

            if (data.idTermEnd === '长期') {
              data.idTermEnd = '';
              this.setIdtermEndFiniteChecked = true;
            }
            this.basicInfo = res.data || {};
            this.basicInfo.merchantAccount = res.data.mobile;
            this.basicInfo.editMerchantAccount = true;

            const getPicData = {
              ...operation,
              authId: res.data.authId
            };

            fetch.comPicPath(getPicData).then(res => {
              console.log(res);
            });
          }
        });
      }
    });
    this.getBankList();
    // this.getComAgentList();
    // this.getComUserList();
  },
  beforeDestroy() {
    this.$emit('update:changeOtherBreadcrumbItem', '');
  },
  computed: {
    // mapCity(v) {
    //   const city =
    //     v.province.find(e => this.basicInfo.province === e.provinceId) || {};
    //   console.log('city arr:', city.cityVOS);
    //   return city.cityVOS || [];
    // },
    // mapArea(v) {
    //   const area = v.mapCity.find(e => this.basicInfo.city === e.cityId) || {};
    //   console.log('basicInfo.city', this.basicInfo);
    //   return area.areaVOS || [];
    // },
    // mapBankCityName(v) {
    //   const city =
    //     v.province.find(
    //       v => this.basicInfo.bankProvinceName === v.provinceId
    //     ) || {};
    //   return city.cityVOS || [];
    // }
  },
  methods: {
    // 省市区列表
    getProvinceList(callback) {
      fetch.comProvinceList().then(({ data = [] }) => {
        this.province = data;
        callback && callback();
      });
    },
    // 开户银行列表
    getBankList() {
      fetch.comBankList().then(({ data = [] }) => {
        this.bankList = data;
      });
    },
    // 代理商
    getComAgentList() {
      fetch.comAgentList().then(({ data = [] }) => {
        console.log(data, 'comAgentList');
        this.comAgentList = data;
      });
    },
    // 所属业务人员
    getComUserList() {
      fetch.comUserList().then(({ data = [] }) => {
        console.log(data, 'comUserList');
        this.comUserList = data;
      });
    },
    addSubmit() {
      const data = Object.assign({}, this.basicInfo);

      if (this.setBusinessTermEndFiniteChecked) {
        data.businessTermEnd = '长期';
      }

      if (this.setIdtermEndFiniteChecked) {
        data.idTermEnd = '长期';
      }

      const form = this.$refs['controlQueryForm'];
      console.log(data, 'this.basicInfo');

      this.checkFormSubmit(form, () => {
        fetch.mcMchAdd(data).then(res => {
          this.$message(res.msg || '新增异常');
        });
      });
    },
    goToPass() {
      const merchantCode = this.basicInfo.merchantCode;
      const status = '00';
      const data = {merchantCode,status};
      fetch.mcMchAudit(data).then(res => {
        this.$message(res.msg || '审核异常');
        this.$router.back();
      });
    },
    goToNoPass() {
      const merchantCode = this.basicInfo.merchantCode;
      const status = '99';
      const data = {merchantCode,status};
      fetch.mcMchAudit(data).then(res => {
        this.$message(res.msg || '审核异常');
        this.$router.back();
      });
    },
    // getPicPath(){
    //   const data = Object.assign({}, this.basicInfo);
    //   this.basicInfo.businessPhotoPath = this.basicInfo.businessPhoto;
    //   this.basicInfo.doorPhotoPath = this.basicInfo.doorPhoto;
    //   this.basicInfo.shopInteriorPath = this.basicInfo.shopInterior;
    //   this.basicInfo.shopCheckStandPath = this.basicInfo.shopCheckStand;
    //   this.basicInfo.legalCertAttribute1Path = this.basicInfo.certAttribute1;
    //   this.basicInfo.legalCertAttribute2Path = this.basicInfo.certAttribute2;
    //   this.basicInfo.openAccountPath = this.basicInfo.openAccount;
      
    //   fetch.getPicPath(data).then(res => {
    //     this.$message(res.msg || '图片入库异常');
    //   });
    // },
    submitReport(){
      // const data = Object.assign({}, this.basicInfo);
      const data = {};

      if (this.setBusinessTermEndFiniteChecked) {
        data.businessTermEnd = '长期';
      }

      if (this.setIdtermEndFiniteChecked) {
        data.idTermEnd = '长期';
      }

      //data.reportStatus = '';
      const form = this.$refs['controlQueryForm'];
      const tdMerchantReport = {},reportDetailInfo={};
      tdMerchantReport.channelNo = this.getStorage('merchants_operation').channelNo;
      tdMerchantReport.merchantCode = this.basicInfo.merchantCode;
      this.basicInfo.mecTypeFlag = this.basicInfo.custType;
      this.basicInfo.merchantProvince = this.basicInfo.province||this.basicInfo.provinceName;
      this.basicInfo.merchantCity = this.basicInfo.city||this.basicInfo.cityName;
      this.basicInfo.merchantArea = this.basicInfo.country||this.basicInfo.areaName;
      this.basicInfo.cprRegAddr = this.basicInfo.custAdd;
      this.basicInfo.registCode = this.basicInfo.businessLicense;
      this.basicInfo.businessPhotoPath = this.basicInfo.businessPhoto;
      this.basicInfo.doorPhotoPath = this.basicInfo.doorPhoto;
      this.basicInfo.shopInteriorPath = this.basicInfo.shopInterior;
      this.basicInfo.shopCheckStandPath = this.basicInfo.shopCheckStand;
      this.basicInfo.mobileNo = this.basicInfo.contactMobile;
      this.basicInfo.bankCardNo = this.basicInfo.compMainAcct;
      this.basicInfo.legalCertAttribute1Path = this.basicInfo.certAttribute1;
      this.basicInfo.legalCertAttribute2Path = this.basicInfo.certAttribute2;
      this.basicInfo.bankProvince = this.basicInfo.bankProvinceName;
      this.basicInfo.bankCity = this.basicInfo.bankCityName;
      this.basicInfo.suiXinBank = this.basicInfo.compAcctBank;
      this.basicInfo.interbankName = this.basicInfo.branchBank;
      this.basicInfo.actType = this.basicInfo.compMainAcctType;
      this.basicInfo.openAccountPath = this.basicInfo.openAccount;
      // this.basicInfo.suiXingMerchantType;

      data.tdMerchantReport = tdMerchantReport;
      data.reportDetailInfo = JSON.stringify(this.basicInfo);
      // data.reportDetailInfo = this.basicInfo;
      console.log(data, 'this.basicInfo');
  
      // this.checkFormSubmit(form, () => {
        fetch.mcReportedAdd(data).then(res => {
          this.$message(res.msg || '报备异常');
          this.$router.back();
        });
      // });
    },
    goToBack() {
      this.$router.back();
    }
  }
};
</script>

<style lang="scss">
.merchanrts-list-operation-page {
  h2 {
    height: 30px;
    line-height: 30px;
    padding: 0 0 0 10px;
    font-size: 17px;
    background-color: #ccc;
    margin-bottom: 10px;
  }

  overflow-y: scroll;
  overflow-x: hidden;

  .btns {
    padding: 20px 0;
    text-align: center;
  }

  .item {
    display: inline-block;
    padding: 4px 10px 0 0;
  }

  .pl {
    padding-left: 10px;
  }

  .el-date-editor.el-input,
  .el-date-editor.el-input__inner {
    width: auto;
  }
}
</style>