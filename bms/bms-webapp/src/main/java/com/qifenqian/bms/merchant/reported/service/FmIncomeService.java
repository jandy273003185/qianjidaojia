package com.qifenqian.bms.merchant.reported.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.merchant.reported.bean.Area;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.BankBranch;
import com.qifenqian.bms.merchant.reported.bean.BestPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.FmWxCategory;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.SuiXingBean;
import com.qifenqian.bms.merchant.reported.bean.SumPayArea;
import com.qifenqian.bms.merchant.reported.bean.SumPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.SumpayMcc;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.YQBArea;
import com.qifenqian.bms.merchant.reported.bean.YQBCoBean;
import com.qifenqian.bms.merchant.reported.bean.YQBIndustry;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.merchant.BestCoMerChantInfo;
import com.seven.micropay.channel.domain.merchant.BestMerChantInfo;
import com.seven.micropay.channel.domain.merchant.BestPayBankInfo;
import com.seven.micropay.channel.domain.merchant.FmMerChantInfo;
import com.seven.micropay.channel.domain.merchant.SumpayMerchantInfo;
import com.seven.micropay.channel.domain.merchant.SumpayUploadFileInfo;
import com.seven.micropay.channel.domain.merchant.SumpayUploadFileInfoList;
import com.seven.micropay.channel.domain.merchant.YqbMerchantInfo;
import com.seven.micropay.channel.domain.merchant.BestCoProdAuthInfo.MctProdAuthBankInfoDTO;
import com.seven.micropay.channel.domain.merchant.BestCoProdAuthInfo.MctProdAuthBaseInfoDTO;
import com.seven.micropay.channel.domain.merchant.BestCoProdAuthInfo.MctProdAuthContactInfoDTO;
import com.seven.micropay.channel.domain.merchant.BestCoProdAuthInfo.MctProdIdentifyApplyReqDTO;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayMerchantInfo;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayRequestInfo;
import com.seven.micropay.channel.enums.BestBankCode;
import com.seven.micropay.channel.enums.BestMerchantType;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayAccountType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayBankNmType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayBusinessType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCompanyCodeType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCycleDays;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayIdCardLegalType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMchGradeType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMerSumType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpayProfessionType;
import com.seven.micropay.channel.enums.SumpayMerchantType.SumpaySuffixNameType;
import com.seven.micropay.channel.enums.YqbMerchantType.MerchantType;
import com.seven.micropay.channel.enums.suixinpay.SuixinBankType;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;

@Service
public class FmIncomeService {
  private Logger logger = LoggerFactory.getLogger(FmIncomeService.class);


  @Autowired
  private IMerChantIntoService iMerChantIntoService;

  @Autowired
  private FmIncomeMapperDao fmIncomeMapperDao;

  @Autowired
  private FmIncomeMapper fmIncomeMapper;

  @Autowired
  private MerchantService merchantService;
  //zhanggc
  @Autowired
  private MerchantMapper merchantMapper;
  @Autowired
  private MerchantEnterService merchantEnterService;
  
  public static final String EXECUTE_SUCCESS = "SUCCESS";
  public static final String EXECUTE_FAILURE = "FAILURE";
  public static final String EXECUTE_FLAG = "EXECUTEFLAG";
  public static final String EXECUTE_DESC = "EXECUTEDESC";
  
  @Value("${ADMANAGE_FILE_SAVE_PATH}")
  private String ADMANAGE_FILE_SAVE_PATH;
  @Value("${SX_FILE_SAVE_PATH}")
  private String SX_FILE_SAVE_PATH;


  /*********************************** 富民报备 **************************************/

  /****
   * 查询商户报备信息
   * 
   * @param detail
   * @return
   */
  public List<TdMerchantDetailInfo> getMerchantDetailInfoList(TdMerchantDetailInfo detail) {

    return fmIncomeMapperDao.selMerchantDetailInfoList(detail);
  }

  /***
   * 查询省份
   * 
   * @return
   */
  public List<Province> getprovinceList() {
    return fmIncomeMapperDao.selProvinceList();
  }

  public List<Bank> getBankList() {
    return fmIncomeMapperDao.selBankList();
  }

  public List<TbFmTradeInfo> getPowerIdList() {
    return fmIncomeMapperDao.selPowerIdList();
  }

  public List<City> getCityList(String province) {

    return fmIncomeMapperDao.selCityList(province);
  }

  public List<Area> getAreaList(String city) {

    return fmIncomeMapperDao.selAreaList(city);
  }

  public List<BankBranch> getInterBankList(String interBank) {

    return fmIncomeMapperDao.selInterBankList(interBank);
  }

  public List<FmWxCategory> getAlipayList() {

    return fmIncomeMapperDao.selAlipayList();
  }

  public List<FmWxCategory> getWxList() {
    
    return fmIncomeMapperDao.selWxList();
  }


  public List<FmWxCategory> getWxSecondTypeList(FmWxCategory category) {
   
    return fmIncomeMapperDao.selWxSecondTypeList(category);
  }

  public List<FmWxCategory> getWxThirdTypeList(FmWxCategory category) {
   
    return fmIncomeMapperDao.selWxThirdTypeList(category);
  }

  public List<FmWxCategory> getJdList() {
  
    return fmIncomeMapperDao.selJdList();
  }

  public List<FmWxCategory> getJdThirdTypeList(FmWxCategory category) {
   
    return fmIncomeMapperDao.selJdThirdTypeList(category);
  }

  // 查询报备商户（td_merchant_report）
  public TdMerchantDetailInfo getTdMerchantReport(CrInComeBean cr) {
   
    return fmIncomeMapperDao.selTdMerchantReport(cr);
  }

  // 添加商户报备详情表（td_merchant_detail_info）
  public void insertTdMerchantReport(TdMerchantDetailInfo info) {
   
    fmIncomeMapperDao.insertTdMerchantReport(info);
  }

  // 添加商户报备表（td_merchant_report）
  public void inserTdMerchantDetailInfo(TdMerchantDetailInfo info) {
  
    fmIncomeMapperDao.inserTdMerchantDetailInfo(info);
  }

  /***
   * 富民报备
   * 
   * @param cr
   * @return
   */
  public String fmReported(CrInComeBean cr) {
    try {
      String merchantCode = cr.getMerchantCode().trim();

      // 查询商户报备信息
      TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
      FmMerChantInfo info = new FmMerChantInfo();

      /** 结算账户名称 */
      info.setAccountNm(custInfo.getRepresentativeName());
      /** 结算账号 */
      info.setAccountNo(custInfo.getCompMainAcct());
      /** 账号类型 账户类型 0-公户、1-私户 */
      info.setAccountType("1");
      /** 代理商商户ID */
      info.setAgentMchntId(merchantCode);
      /** 开户行号 */
      info.setBankCd(cr.getBankCode());
      /* info.setBankCd("BCM"); */
      /** 城市代码 */
      info.setCityCd(cr.getCity());
      /** 区县代码 */
      info.setContryCd(cr.getCountry());
      /** 商户登录邮箱号（平台唯一校验） */
      if (null != custInfo.getEmail() && (!"".equals(custInfo.getEmail()))) {
        info.setEmail(custInfo.getEmail());
      } else {
        info.setEmail(custInfo.getMerchantEmail());
      }

      /** 联系人邮箱 */
      info.setHeaderEmail(custInfo.getMerchantEmail());
      /** 联系人手机号 */
      info.setHeaderMobile(custInfo.getMerchantMobile());
      /** 联系人名称 */
      info.setHeaderName(custInfo.getAgentName());
      /** 联系人QQ */
      info.setHeaderQq(cr.getQq());
      /** 企业法人身份证或者个体负责人姓名 */
      info.setIdCardLegalName(custInfo.getRepresentativeName());
      /** 企业身份证或个体负责人身份证号 */
      info.setIdCardLegalNum(custInfo.getRepresentativeCertNo());
      /** 开户支行号 */
      info.setInterBankCd(cr.getInterBank());
      /** 开户支行名称 */
      info.setInterBankNm(cr.getInterBankName());

      /** 开户支行号 */
      /*
       * info.setInterBankCd("301100000031");
       *//** 开户支行名称 *//*
                       * info.setInterBankNm("交通银行北京东单支行");
                       */
      /** 营业执照号 */
      info.setLicenseRegistNum(custInfo.getBusinessLicense());
      /** 客服电话 */
      info.setMchCsPhone(cr.getCustomerPhone());
      /** */
      // info.setMchGrade("");
      /** 商户名称 */
      info.setMchName(custInfo.getCustName());
      /** 商户custID */
      info.setMerCustId(custInfo.getCustId());
      /** 商户登录手机号（平台唯一校验） */
      if (null != custInfo.getMobile() && (!"".equals(custInfo.getMobile()))) {
        info.setMobile(custInfo.getMobile());
      } else {
        info.setMobile(custInfo.getMerchantMobile());
      }

      /** 银行卡预留手机号（法人必传） */
      info.setMobileNo(cr.getMobileNo());
      /** 订单号 */
      info.setOrderNo("");
      /** 批次号 */
      info.setPatchNo("");
      /** 省份代码 */
      info.setProvCd(cr.getProvince());
      /** 商户注册地址 */
      info.setRgstAdrs(custInfo.getCustAdd());
      /*** 商户级别 1:企业；2:个体工商户； */
      info.setMchGrade("2");

      Map<String, Object> req = new HashMap<String, Object>();
      req.put("merList", info);
      req.put("channelType", ChannelMerRegist.FM_COMBINEDPAY);
      logger.info("富民进件请求报文：" + JSONObject.toJSONString(req));
      ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
      System.out.println("富民进件响应报文：" + JSONObject.toJSONString(channelResult));

      if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
        Map<String, Object> data = channelResult.getData();
        String status = String.valueOf(data.get("status"));
        String mchntStatus = String.valueOf(data.get("mchntStatus"));
        if ("success".equals(status)) {
          String mchntId = String.valueOf(data.get("mchntId"));
          // 更新商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
          tdInfo.setMerchantCode(cr.getMerchantCode().trim());
          tdInfo.setChannelNo(cr.getChannelNo());
          tdInfo.setReportStatus("O");
          tdInfo.setOutMerchantCode(mchntId);

          // 查询商户报备信息
          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
          tdInfo.setPatchNo(tdInfo_.getPatchNo());
          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
        } else {
          logger.error("富民进件明确失败:" + channelResult.getReMsg());
          return EXECUTE_FAILURE;
        }

      } else {
        logger.error("富民进件明确失败:" + channelResult.getReMsg());
        return EXECUTE_FAILURE;
      }


    } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      return EXECUTE_FAILURE;
    }

    return EXECUTE_SUCCESS;
  }

  public void UpdateMerReportAndMerDetailInfo(TdMerchantDetailInfo tdInfo, String status) {

    fmIncomeMapper.updateTdMerchantReport(tdInfo);
    tdInfo.setReportStatus(status);
    fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);
  }



  /***
   * 翼支付报备
   * 
   * @param best
   * @return
   */
  public JSONObject bestReported(CrInComeBean cr) {
    JSONObject object = new JSONObject();
    try {
      String merchantCode = cr.getMerchantCode().trim();

      // 查询商户报备信息
      TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);

      BestMerChantInfo info1 = new BestMerChantInfo();
      if (("Licensed").equals(cr.getBestMerchantType())) {
        /** 进件商户类型 */
        info1.setMerchantType(BestMerchantType.Licensed);
      } else {
        /** 进件商户类型 */
        info1.setMerchantType(BestMerchantType.Unlicensed);
      }

      /** 请求流水号 */
      info1.setRequestSeqId("Best" + String.valueOf(System.currentTimeMillis()) + "SP");
      /** 商户在平台商侧的商户号 */
      info1.setMerchantNo(merchantCode);
      /** 商户名称 */
      info1.setMerchantName(custInfo.getCustName());
      /** 商户简称 */
      info1.setMerchantNameShort(custInfo.getCustName());
      /** 营业范围 */
      // 线上线下？
      info1.setBusinessScope("线下");
      /** 营业期限 */
      // info1.setBusinessTerm(custInfo.getBusinessTerm());
      if ("forever".equals(custInfo.getBusinessTermEnd())) {
        info1.setBusinessTerm("2199-12-31");
      } else if (custInfo.getBusinessTermEnd() != "" && custInfo.getBusinessTermEnd() != null) {
        info1.setBusinessTerm(custInfo.getBusinessTermEnd());
      } else {
        info1.setBusinessTerm(custInfo.getBusinessTerm());
      }
      if (BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()) != null) {
        info1.setBankCode(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()));
      } else {
        object.put("message", "开户行信息错误");
        object.put("result", "FAILURE");
      }
      /** 省份编码 */
      info1.setProvinceCode(cr.getProvince());
      /** 城市编码 */
      info1.setCityCode(cr.getCity());
      /** 营业地址 */
      // info1.setBusinessAddress(custInfo.getBusinessRegAddr());
      info1.setBusinessAddress(custInfo.getCustAdd());
      /** 联系人手机号 */
      info1.setContactPhone(custInfo.getMobile());
      /** 身份证用户姓名 */
      info1.setIdentityCardUserName(cr.getInterName());

      /*
       * if(custInfo.getAgentName() =="" && custInfo.getAgentName() == null ){
       * info1.setIdentityCardUserName(custInfo.getRepresentativeName()); }else {
       * info1.setIdentityCardUserName(custInfo.getAgentName()); }
       */

      /** 身份证件号 */
      info1.setIdentityCardNo(cr.getCertifyNo());
      /*
       * if(custInfo.getCertifyNo() =="" && custInfo.getCertifyNo() == null){
       * info1.setIdentityCardNo(custInfo.getRepresentativeCertNo()); }else{
       * info1.setIdentityCardNo(custInfo.getCertifyNo()); }
       */
      /** 身份证正面图片 */
      info1.setIdentityCardFrontPic(cr.getIdentityCardFrontPic());
      // info1.setIdentityCardFrontPic("D:/data/nfsshare/upload.432999706307142687.jpg");
      /** 身份证反面图片 */
      info1.setIdentityCardReversePic(cr.getIdentityCardReversePic());
      // info1.setIdentityCardReversePic("D:/data/nfsshare/upload.432999706307142687.jpg");
      /** 商户营业执照号 */
      info1.setBusiLicenseNo(custInfo.getBusinessLicense());

      /** 营业执照用户姓名 */
      info1.setBusiLicenseUserName(cr.getInterName());

      /*
       * if(custInfo.getAgentName() =="" && custInfo.getAgentName() == null ){
       * info1.setBusiLicenseUserName(custInfo.getRepresentativeName()); }else {
       * info1.setBusiLicenseUserName(custInfo.getAgentName()); }
       */

      /** 营业许可证类型 */
      info1.setLicenseType("integrateLicense");
      /** 营业许可证图片 */
      info1.setLicensePic(cr.getLicensePic());
      // info1.setLicensePic("D:/data/nfsshare/upload.432999706307142687.jpg");
      /** 店铺内景照片 */
      info1.setStoreInteriorPic(cr.getStoreInteriorPic());
      // info1.setStoreInteriorPic("D:/data/nfsshare/upload.432999706307142687.jpg");
      /** 店铺招牌照片 */
      info1.setStoreSignBoardPic(cr.getStoreSignBoardPic());
      // info1.setStoreSignBoardPic("D:/data/nfsshare/upload.432999706307142687.jpg");
      /** 结算银行名称 */
      info1.setSettleBankName(cr.getInterBankName());
      /** 结算银行编码 */
      BestBankCode bestBankCode = BestBankCode.getBestBankCodeByBankCode(cr.getBankCode());
      info1.setBankCode(bestBankCode);
      /** 结算银行编码 */
      info1.setSettleBankNo("");
      /** 结算银行卡号 */
      info1.setSettleBankcardNo(cr.getBankCardNo());
      // info1.setSettleBankcardNo(custInfo.getCompMainAcct());
      /** 结算银行卡用户姓名 */
      info1.setSettleBankcardUserName(cr.getInterName());

      // info1.setSettleBankcardUserName(custInfo.getBankAcctName());

      // info.setSettleBankcardLineNumber("308391026010");//银行联行号
      /** 银行预留手机号 */
      info1.setSettlePhoneNo(cr.getMobileNo());
      /** 商户签约交易费率 */
      info1.setMerchantTxnRate(cr.getRate());
      /** 商户交易结算周期 */
      info1.setMerchantTxnSettlePeriod("1");
      /** 行业分类编码 */
      info1.setMccCode(cr.getIndustryCode());

      Map<String, Object> req = new HashMap<String, Object>();
      req.put("merList", info1);
      req.put("channelType", ChannelMerRegist.BEST_PAY);
      logger.info("翼支付进件请求报文：" + JSONObject.toJSONString(req));
      ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
      logger.info("翼支付进件响应报文：" + JSONObject.toJSONString(channelResult));

      if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
        Map<String, Object> data = channelResult.getData();
        String status = String.valueOf(data.get("status"));
        String mchntStatus = String.valueOf(data.get("mchntStatus"));
        // if(ReStatus.SUCCESS.equals(status) &&
        // ChannelRtnCode.SUCCESS.equals(data.get("channelCode"))){
        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
            && "00".equals(channelResult.getChannelCode())) {
          String mchntId = String.valueOf(channelResult.getOrderNo());
          // 更新商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
          tdInfo.setMerchantCode(cr.getMerchantCode().trim());
          tdInfo.setChannelNo(cr.getChannelNo());
          tdInfo.setReportStatus("O");
          tdInfo.setOutMerchantCode(mchntId);

          // 查询商户报备信息
          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
          tdInfo.setPatchNo(tdInfo_.getPatchNo());
          mchntStatus = "0";
          tdInfo.setFileStatus("Y");
          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
        } else {
          logger.error("翼支付进件明确失败:" + channelResult.getData().get("errorMsg"));

          object.put("message", channelResult.getData().get("errorMsg"));
          object.put("result", "FAILURE");
          return object;
        }

      } else {
        logger.error("翼支付进件明确失败:" + channelResult.getReMsg());
        object.put("result", "FAILURE");
        object.put("message", channelResult.getReMsg());
        return object;
      }


    } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      object.put("result", "FAILURE");
      object.put("message", e);
      return object;
    }
    object.put("result", "SUCCESS");
    return object;
  }


  public List<Industry> getIndustryList() {
    return fmIncomeMapperDao.selIndustryList();
  }

  public List<Industry> getSuiXingIndustryList() {
    return fmIncomeMapperDao.selSuiXingIndustryList();
  }

  public List<Province> getSuiXingProvinceList() {
    return fmIncomeMapperDao.selSuiXingProvinceList();
  }

  public List<City> getSuiXingCityList(String province) {
    return fmIncomeMapperDao.selSuiXingCityList(province);
  }

  public List<MerchantCity> getSuiXingAreaList(String superiorAreaCode) {
    return fmIncomeMapperDao.selSuiXingAreaList(superiorAreaCode);
  }

  public List<MerchantCity> getSuiXingMerchantCityList(String areaType) {
    return fmIncomeMapperDao.selSuiXingMerchantCityList(areaType);
  }


  /***
   * 随行付报备
   * 
   * @param best
   * @return
   */
  public JSONObject suiXingReported(SuiXingBean cr) {
    JSONObject object = new JSONObject();
    try {
      String merchantCode = cr.getMerchantCode().trim();

      SxPayMerchantInfo merchantInfo = new SxPayMerchantInfo();
      
      merchantInfo.setIndependentModel(cr.getIndependentModel());
      merchantInfo.setParentMno(cr.getParentMno());
      merchantInfo.setRegistCode(cr.getRegistCode());
	  merchantInfo.setCprRegNmCn(cr.getCprRegNmCn());
      merchantInfo.setMecDisNm(cr.getCustName());
      merchantInfo.setMblNo(cr.getMobileNo());
      merchantInfo.setHaveLicenseNo(cr.getSuiXingMerchantType());
      merchantInfo.setMecTypeFlag(cr.getMecTypeFlag());
      merchantInfo.setCprRegAddr(cr.getCprRegAddr());
      merchantInfo.setRegProvCd(cr.getMerchantProvince());
      merchantInfo.setRegCityCd(cr.getMerchantCity());
      merchantInfo.setRegDistCd(cr.getMerchantArea());
      merchantInfo.setQrcodeRate(cr.getRate());
      merchantInfo.setMccCd(cr.getIndustryCode());
      merchantInfo.setIdentityName(cr.getRepresentativeName());
      merchantInfo.setIdentityTyp(cr.getRepresentativeCertType());
      merchantInfo.setIdentityNo(cr.getRepresentativeCertNo());
      merchantInfo.setActNm(cr.getActNm());
      merchantInfo.setActTyp(cr.getActType());
      merchantInfo.setStmManIdNo(cr.getCertifyNo());
      merchantInfo.setActNo(cr.getBankCardNo());
      merchantInfo.setBnkCd(SuixinBankType.valueOf(cr.getSuiXinBank()).getCode()); // 银行编码
      merchantInfo.setBnkNm(SuixinBankType.valueOf(cr.getSuiXinBank()).getName()); // 开户行名称
      merchantInfo.setLbnkProv(cr.getBankProvince());
      merchantInfo.setLbnkCity(cr.getBankCity());
      merchantInfo.setLbnkNo(cr.getLbnkNo()); // 联行号
      merchantInfo.setLbnkNm(cr.getInterBankName());
      merchantInfo.setTaskCode(cr.getTaskCode()); // 照片压缩返回码
      merchantInfo.setMerUrl("https://combinedpay.qifenqian.com/gateway.do");

      SxPayRequestInfo requestInfo = new SxPayRequestInfo();
      requestInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
      requestInfo.setReqData(merchantInfo);
      requestInfo.setTimestamp(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
      Map<String, Object> req = new HashMap<>();
      req.put("merList", requestInfo);
      req.put("channelType", ChannelMerRegist.SUIXING_PAY);

      logger.info("随行付进件请求报文：" + JSONObject.toJSONString(req));
      ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
      logger.info("随行付进件响应报文：" + JSONObject.toJSONString(channelResult));

      if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
        Map<String, Object> data = channelResult.getData();
        /*
         * String status =String.valueOf(data.get("status")); String mchntStatus
         * =String.valueOf(data.get("mchntStatus"));
         */
        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
            && "00".equals(channelResult.getChannelCode())) {
//          String mchntId = String.valueOf(channelResult.getOrderNo());
          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
          tdInfo.setMerchantCode(cr.getMerchantCode().trim());
          tdInfo.setChannelNo(cr.getChannelNo());
          tdInfo.setReportStatus("Y");
          tdInfo.setDetailStatus("0");
//          tdInfo.setOutMerchantCode(mchntId);

          // 查询商户报备信息
          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
          tdInfo.setPatchNo(tdInfo_.getPatchNo());
          String mchntStatus = "0";
          tdInfo.setFileStatus("Y");
          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
        } else {
          logger.error("随行付进件明确失败:" + channelResult.getData().get("errorMsg"));

          object.put("message", channelResult.getData().get("errorMsg"));
          object.put("result", "FAILURE");
          return object;
        }

      } else {
        logger.error("随行付进件明确失败:" + channelResult.getReMsg());
        object.put("result", "FAILURE");
        object.put("message", channelResult.getReMsg());
        return object;
      }


    } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      object.put("result", "FAILURE");
      object.put("message", e);
      return object;
    }
    object.put("result", "SUCCESS");
    return object;
  }

  /**
   * 随行付文件上传
   * 
   * @param request
   */
  public Map<String, String> download(HttpServletRequest request, HttpServletResponse response,
      SuiXingBean cr) {
    logger.info("*********随行付文件上传开始*********");
    Map<String, String> result = new HashMap<String, String>();
    try {
      DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建一个DiskFileItemFactory工厂
      ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个文件上传解析器
      upload.setHeaderEncoding("UTF-8"); // 解决上传文件名的中文乱码
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      CommonsMultipartResolver multipartResolver =
          new CommonsMultipartResolver(request.getSession().getServletContext());
      if (multipartResolver.isMultipart(request)) {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        // 获取multiRequest 中所有的文件名
        Iterator iter = multiRequest.getFileNames();
        List<File> fileList = new ArrayList<>();
        String filePath =
            cr.getMerchantCode() + DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS);
        while (iter.hasNext()) {
          // 一次遍历所有文件
          MultipartFile file = multiRequest.getFile(iter.next().toString());
          if (file != null) {
            if (file.getSize() == 0) {
              continue;
            }
            String typeName = file.getName();// 图片类型名称
            String fileName = file.getOriginalFilename();// 图片名称
            // 门头照
            if ("doorPhoto".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "storePic" + type;
            }
            // 营业执照
            if ("businessPhoto".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "licensePic" + type;
            }
            // 非法人结算授权函
            if ("letterOfAuth".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "letterOfAuthPic" + type;
            }
            // 开户许可证
            if ("openAccount".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "openingAccountLicensePic" + type;
            }
            // 银行卡正面照
            if ("bankCardPhoto".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "bankCardPositivePic" + type;
            }
            // 法人身份证正面照
            if ("legalCertAttribute1".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "legalPersonidPositivePic" + type;
            }
            // 法人身份证反面照
            if ("legalCertAttribute2".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "legalPersonidOppositePic" + type;
            } 
            // 结算人身份证正面 
            if ("settleCertAttribute1".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "settlePersonIdcardPositive" + type;
            }
            // 结算人身份证反面 
            if ("settleCertAttribute2".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "settlePersonIdcardOpposite" + type;
            }
            // 店内照
            if ("shopInterior".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "insideScenePic" + type;
            }
            // 店内前台照
            if ("shopCheckStand".equals(typeName)) {
              String type = fileName.substring(fileName.lastIndexOf("."));
              fileName = "businessPlacePic" + type;
            }
            // 特殊资质照
            if ("qualification".equals(typeName)) {
                String type = fileName.substring(fileName.lastIndexOf("."));
                fileName = "otherMaterialPictureOne" + type;
              }
            File filew = new File(SX_FILE_SAVE_PATH + File.separator + filePath);
            if (!filew.exists()) {
              filew.mkdirs();
            }
            // 上传
            file.transferTo(new File(filew + File.separator + fileName));

            fileList.add(new File(filew + File.separator + fileName));
          }
        }
        // 将文件压缩
        // FileOutputStream fos2 = new FileOutputStream(new File("D:"+ File.separator +
        // p.getProperty("SX_FILE_SAVE_PATH") + File.separator + filePath + File.separator +
        // cr.getMerchantCode() +".zip"));
        // 文件压缩并上传至服务器指定地址
        FileOutputStream fos2 = new FileOutputStream(new File(SX_FILE_SAVE_PATH + File.separator + cr.getMerchantCode() + ".zip"));
        ZipUtils.toZip(fileList, fos2);
        result.put("filePath", filePath);
      }

      // 文件从本地上传至服务器
      List<FileItem> list = upload.parseRequest(multipartRequest);
      InputStream inputStream = null;


      HashMap<String, String> nameType = new HashMap<String, String>();
      for (FileItem item : list) {
        String filename = null; //
        if (!item.isFormField()) { // 是否是表单项
          filename = item.getName(); // 文件类型名称
          String filedName = item.getFieldName(); // 文件名称
          // filename = ".zip";
          // filedName = cr.getMerchantCode();
          if (StringUtils.isEmpty(filename)) {
            continue;
          }
          /** 后缀名 **/
          String type = filename.substring(filename.lastIndexOf("."));
          String[] photoTypes = {".zip"};
          boolean isType = false;
          for (int i = 0; i < photoTypes.length; i++) {
            if (photoTypes[i].equalsIgnoreCase(type)) {
              isType = true;
              break;
            }
          }
          if (!isType) {
            result.put("result", "FAIL");
            result.put("message", "文件类型不匹配");
            return result;
          }
          inputStream = item.getInputStream();
          String fileUploadPath =SX_FILE_SAVE_PATH; // properties.getProperty("SX_FILE_SAVE_PATH"); // 服务器上传路径
          //判断服务器路径是否存在，不存在则创建路径
          File file = new File(fileUploadPath);
          if (!file.exists()) {
        	  file.mkdirs();
          }
          String name = filedName.substring(filedName.length() - 1);
          /** 根据文件名生成路径规则 **/
          switch (filedName) {
            case "suiXingPhoto":
              filename = cr.getMerchantCode() + type;
              /** /data/ **/
              fileUploadPath = fileUploadPath;
              nameType.put("suiXingType", filename);
              break;
            default:
              if (filedName.indexOf("suiXingPhoto") != -1) {
                filename = cr.getMerchantCode() + type;
                /** /data/ **/
                fileUploadPath = fileUploadPath;
                // nameType.put(filedName, fileUploadPath + File.separator + filename);
                nameType.put("suiXingPhoto" + name, filename);
              }
              break;

          }
          File saveFile = new File(fileUploadPath);
          if (!saveFile.exists()) {
            saveFile.mkdirs();
          }
          // delImagFile(fileUploadPath + File.separator + filename); // 删除服务器图片
          FileOutputStream fileOutputStream =
              new FileOutputStream(fileUploadPath + File.separator + filename);
          byte buffer[] = new byte[1024];
          int len = 0;
          while ((len = inputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, len);
          }
          /** 刷新此输出流并强制写出所有缓冲的输出字节 **/
          fileOutputStream.flush();
          inputStream.close();
          fileOutputStream.close();
          /**
           * Deletes the underlying storage for a file item, including deleting any associated
           * temporary disk file.
           **/
          item.delete();
        }
      }
      result.put("result", "SUCCESS");
      result.put("message", "上传成功");

      result.putAll(nameType);
      logger.info("文件上传成功");
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("上传失败[{}]", e);
      result.put("result", "fail");
      result.put("message", e.getMessage());
    }
    return result;

  }

  /**
   * 压缩成ZIP
   * 
   * @param srcFiles 需要压缩的文件列表
   * @param out 压缩文件输出流
   * @throws RuntimeException 压缩失败会抛出运行时异常
   */
  public static class ZipUtils {
    private static final int BUFFER_SIZE = 2 * 1024;

    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {

      long start = System.currentTimeMillis();
      ZipOutputStream zos = null;
      try {

        zos = new ZipOutputStream(out);

        for (File srcFile : srcFiles) {

          byte[] buf = new byte[BUFFER_SIZE];

          zos.putNextEntry(new ZipEntry(srcFile.getName()));

          int len;
          FileInputStream in = new FileInputStream(srcFile);
          while ((len = in.read(buf)) != -1) {
            zos.write(buf, 0, len);
          }
          zos.closeEntry();
          in.close();
        }

        long end = System.currentTimeMillis();

        System.out.println("压缩完成，耗时：" + (end - start) + " ms");
      } catch (Exception e) {

        throw new RuntimeException("zip error from ZipUtils", e);
      } finally {

        if (zos != null) {
          try {

            zos.close();

          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
  
  
  
  /***
   * 翼支付企业报备
   * 
   * @param best
   * @return
   */
  public JSONObject bestCoReported(BestPayCoBean cr) {
    JSONObject object = new JSONObject();
    try {
      String merchantCode = cr.getMerchantCode().trim();
      String uuid = DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS);
	  String loginNo = "C" + uuid;
      // 查询商户报备信息
      TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);

      BestCoMerChantInfo addCoMer = new BestCoMerChantInfo();
      	addCoMer.setTraceLogId("T"+ uuid);
		addCoMer.setLoginNo(loginNo);
		addCoMer.setContactPhone(cr.getMobileNo());
		addCoMer.setGenerateLoginPwd(false);
      //认证商户基本信息
      MctProdAuthBaseInfoDTO mctProdAuthBaseInfoDTO = new MctProdAuthBaseInfoDTO();
	      mctProdAuthBaseInfoDTO.setLoginNo(loginNo);
	      mctProdAuthBaseInfoDTO.setMerchantName(cr.getCustName());
	      mctProdAuthBaseInfoDTO.setMerchantApplyType(cr.getMerchantApplyType());
	      mctProdAuthBaseInfoDTO.setCertificateType(cr.getCertificateType());
	      mctProdAuthBaseInfoDTO.setRegistrationNumber(cr.getRegistrationNumber());
	      mctProdAuthBaseInfoDTO.setMccCode(cr.getIndustryCode());
	      mctProdAuthBaseInfoDTO.setBusinessScope(cr.getBusinessScope());
	      //mctProdAuthBaseInfoDTO.setLicenseBegin(custInfo.getBusinessTermStart());
	      if ("forever".equals(custInfo.getBusinessTermEnd())) {
	    	  mctProdAuthBaseInfoDTO.setLicenseEnd("2199-12-31");
	        } else if (custInfo.getBusinessTermEnd() != "" && custInfo.getBusinessTermEnd() != null) {
	        	mctProdAuthBaseInfoDTO.setLicenseEnd(custInfo.getBusinessTermEnd());
	        } else {
	        	mctProdAuthBaseInfoDTO.setLicenseEnd(custInfo.getBusinessTerm());
	        }
	      mctProdAuthBaseInfoDTO.setContactPhone(cr.getMobileNo());
	      mctProdAuthBaseInfoDTO.setProvinceCode(cr.getProvince());
	      mctProdAuthBaseInfoDTO.setCityCode(cr.getCity());
	      mctProdAuthBaseInfoDTO.setBusinessAddress(cr.getCprRegAddr());
	      mctProdAuthBaseInfoDTO.setCreatedBy(cr.getCreatedBy());
      addCoMer.setMctProdAuthBaseInfoDTO(mctProdAuthBaseInfoDTO);
      
      //查询银行信息
      BestPayBankInfo bankInfo = new BestPayBankInfo();
		bankInfo.setRequestSeqId(""+System.currentTimeMillis());
		bankInfo.setBankName(cr.getInterBankName());
		bankInfo.setBankCode(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()));
		Map<String, Object> req1 = new HashMap<String, Object>();
		req1.put("merList", bankInfo);
		req1.put("channelType", ChannelMerRegist.BEST_PAY);
	  ChannelResult result = iMerChantIntoService.queryBankInfo(req1);
	  String cnapsNo = null;
	  String areaCode = null;
	  if("SUCCESS".equals(result.getStatus().name())){
		  List<Map<String,String>> list = (List<Map<String, String>>) result.getData().get("bankList");
		  cnapsNo = list.get(0).get("financeAreaCode");
		  areaCode = list.get(0).get("bankLineNumber");
	  }else{
		  logger.error("翼支付企业进件明确失败:" + result.getReMsg());
	      object.put("result", "FAILURE");
	      object.put("message", result.getReMsg());
	      return object;
	  }
      //认证商户银行卡信息
      MctProdAuthBankInfoDTO authBankInfoDTO = new MctProdAuthBankInfoDTO();
	      authBankInfoDTO.setSettleCardNo(cr.getBankCardNo());
	      authBankInfoDTO.setBankBranchName(cr.getInterBankName());
	      authBankInfoDTO.setCnapsNo(cnapsNo);
	      authBankInfoDTO.setBankAcctName(cr.getBankAcctName());
	      authBankInfoDTO.setBankCode(cr.getBankCode());
	      authBankInfoDTO.setPerEntFlag(cr.getPerEntFlag());
	      authBankInfoDTO.setBankCardType(cr.getBankCardType());
	      authBankInfoDTO.setAreaCode(areaCode);
      addCoMer.setMctProdAuthBankInfoDTO(authBankInfoDTO);
      
      
      //认证商户联系人列表
      List<MctProdAuthContactInfoDTO> contactInfoDTOList = new ArrayList<MctProdAuthContactInfoDTO>();
      MctProdAuthContactInfoDTO authContactInfoDTO = new MctProdAuthContactInfoDTO();
	      authContactInfoDTO.setContactName(cr.getInterName());
	      authContactInfoDTO.setContactType("LEGAL");
	      authContactInfoDTO.setCertificateType(cr.getRepresentativeCertType());
	      authContactInfoDTO.setCertificateNo(cr.getCertifyNo());
	      authContactInfoDTO.setValidDate(cr.getValidDate());
	      authContactInfoDTO.setResidentCity(cr.getResidentCity());
	      contactInfoDTOList.add(authContactInfoDTO);
	  addCoMer.setContactInfoDTOList(contactInfoDTOList);
      
      List<MctProdIdentifyApplyReqDTO> identifyList = new ArrayList<MctProdIdentifyApplyReqDTO>();
	  MctProdIdentifyApplyReqDTO identify = new MctProdIdentifyApplyReqDTO();
	    identify.setId(cr.getIdentityCardFrontPic());  //身份证正面照
		identify.setId_BACK(cr.getIdentityCardReversePic());  //身份证反面照
		identify.setBusiness_LICENSE(cr.getLicensePic());  //营业执照
		identify.setBank_OPEN_PROVE(cr.getOpenPic());  //银行开户许可证
		identifyList.add(identify);
	  addCoMer.setIdentifyList(identifyList);
	  
		
      Map<String, Object> req = new HashMap<String, Object>();
      req.put("merList", addCoMer);
      req.put("isCompany", true);
      req.put("channelType", ChannelMerRegist.BEST_PAY);
      logger.info("翼支付企业进件请求报文：" + JSONObject.toJSONString(req));
      ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
      logger.info("翼支付企业进件响应报文：" + JSONObject.toJSONString(channelResult));

      if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
        Map<String, Object> data = channelResult.getData();
        String status = String.valueOf(data.get("status"));
        String mchntStatus = String.valueOf(data.get("mchntStatus"));
        // if(ReStatus.SUCCESS.equals(status) &&
        // ChannelRtnCode.SUCCESS.equals(data.get("channelCode"))){
        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
            && "00".equals(channelResult.getReCode())) {
//          String mchntId = String.valueOf(channelResult.getOrderNo());
          // 更新商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
          tdInfo.setMerchantCode(cr.getMerchantCode().trim());
          tdInfo.setChannelNo(cr.getChannelNo());
          tdInfo.setReportStatus("O");
//          tdInfo.setOutMerchantCode(mchntId);
          tdInfo.setLoginNo(loginNo);

          // 查询商户报备信息
          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
          tdInfo.setPatchNo(tdInfo_.getPatchNo());
          mchntStatus = "0";
          tdInfo.setFileStatus("Y");
          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
        } else {
          logger.error("翼支付企业进件明确失败:" + channelResult.getData().get("errorMsg"));

          object.put("message", channelResult.getData().get("errorMsg"));
          object.put("result", "FAILURE");
          return object;
        }

      } else {
        logger.error("翼支付企业进件明确失败:" + channelResult.getReMsg());
        object.put("result", "FAILURE");
        object.put("message", channelResult.getReMsg());
        return object;
      }


    } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      object.put("result", "FAILURE");
      object.put("message", e);
      return object;
    }
    object.put("result", "SUCCESS");
    return object;
  }

public List<Bank> getSumPayBankList() {
	return fmIncomeMapperDao.selSumPayBankList();
}


/***
 * 商盟报备
 * 
 * @param sumpay
 * @return
 */
public JSONObject sumpayReported(SumPayCoBean cr) {
  JSONObject object = new JSONObject();
  try {
	  SumpayMerchantInfo merchantInfo = new SumpayMerchantInfo();
		merchantInfo.setMchName(cr.getCustName()); // 商户名称(必填)
		merchantInfo.setLicenseRegistNum(cr.getBusinessLicense()); // 营业执照号(必填)
		merchantInfo.setMchShortName(cr.getCustName()); // 商户简称(必填)
		merchantInfo.setRgstAdrs(cr.getCprRegAddr()); // 注册地址(必填)
		merchantInfo.setRgstProvCd(cr.getProvince()); // 省份代码(必填)
		merchantInfo.setRgstCityCd(cr.getCity()); // 城市代码(必填)
		merchantInfo.setRgstContryCd(cr.getCountry()); // 区县代码(必填)
		merchantInfo.setIdCardLegalName(cr.getInterName()); // 法人姓名(必填)
		merchantInfo.setIdCardLegalNum(cr.getCertifyNo()); // 法人身份证号(必填)
		merchantInfo.setAccountType(SumpayAccountType.valueOf(cr.getPerEntFlag())); // 账号类型(必填) 0:对公 1:对私
		merchantInfo.setBankCd(cr.getBankCode()); // 开户总行联行号(必填)
		merchantInfo.setInterBankCd(cr.getInterBankCode()); // 开户支行号(必填)
		merchantInfo.setInterBankNm(cr.getInterBankName()); // 开户支行名称(必填)
		merchantInfo.setMobileNo(cr.getMobile()); // 预留电话(法人结算时必填)
		merchantInfo.setHeaderName(cr.getAttentionName()); // 联系人名称(必填)
		merchantInfo.setHeaderMobile(cr.getAttentionMobile()); // 联系人手机号(必填)
		merchantInfo.setHeaderEmail(cr.getAttentionEmail()); // 联系人邮箱(必填)
		merchantInfo.setEmail(cr.getMerchantLoginEmail()); // 商户登陆邮箱号(必填)
		merchantInfo.setMobile(cr.getMerchantLoginMobile()); // 商户登陆手机号(必填)
		merchantInfo.setAgentMchntId(""); // 代理商平台商户ID(非必填)
		merchantInfo.setMchCsPhone(cr.getCustomerPhone()); // 客服电话(必填)
		merchantInfo.setHeaderQq(""); // 联系人QQ(非必填)
		merchantInfo.setMchGrade(SumpayMchGradeType.valueOf(cr.getMerchantLev())); // 商户级别(必填) 1:企业 2:个体商户
		merchantInfo.setTransType(""); // 结算类型(非必填)
		merchantInfo.setCompanyCodeType(SumpayCompanyCodeType.valueOf(cr.getMerchantCodeType())); // 企业代码类型(必填) 0:对公 1:对私
		merchantInfo.setCorporatePhone(cr.getRepresentativePhone()); // 法人手机号(必填)
		merchantInfo.setDetailAdrs(cr.getRepresentativeAddr()); // 联系地址(必填)
		merchantInfo.setProvCd(cr.getProvinceAdd()); // 联系省份代码(必填)
		merchantInfo.setCityCd(cr.getCityAdd()); // 联系城市代码(必填)
		merchantInfo.setContryCd(cr.getCountryAdd()); // 联系区县代码(必填)
		merchantInfo.setIdCardLegalType(SumpayIdCardLegalType.valueOf(cr.getSumpayIdCardLegalType())); // 法定代表人证件类型(必填) 0:身份证 1:护照
		merchantInfo.setProfession(SumpayProfessionType.valueOf(cr.getSumpayProfessionType())); // 商户类别(必填)
		merchantInfo.setCycleDays(SumpayCycleDays.valueOf(cr.getSumpayCycleDays())); // 结算周期(必填) 0:T+1 4:D0代付 5:商户提现 6:D1代付
		merchantInfo.setBankAddress(cr.getResidentCity()); // 结算账户开户城市地址(必填)
		merchantInfo.setMerSumType(SumpayMerSumType.valueOf(cr.getSumpayMerSumType())); // 业务类型(必填)
		merchantInfo.setAccountNm(cr.getAccountNm()); // 结算账号名称(必填)
		merchantInfo.setAccountNo(cr.getAccountNo()); // 结算账号(必填)
		merchantInfo.setBankNm(SumpayBankNmType.valueOf(cr.getSumpayBankNmType())); // 开户行号(必填)
		Map<String, Object> req = new HashMap<>();
		req.put("merList", merchantInfo);
		req.put("channelType", ChannelMerRegist.SUM_PAY);
		
		logger.info("商盟进件请求报文：" + JSONObject.toJSONString(req));
	    ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
	    logger.info("商盟进件响应报文：" + JSONObject.toJSONString(channelResult));
	    
	    
	    if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
	        Map<String, Object> data = channelResult.getData();
	        /*
	         * String status =String.valueOf(data.get("status")); String mchntStatus
	         * =String.valueOf(data.get("mchntStatus"));
	         */
	        String reMessage = StringUtils.isBlank(channelResult.getReMsg()) ? "请联系客服": channelResult.getReMsg();
	        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
	            && "00".equals(channelResult.getReCode())) {
	        	  TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
	        	  String outMerchantCode = StringUtils.isBlank((String)channelResult.getData().get("mchntId")) ? "" :(String)channelResult.getData().get("mchntId");
		          tdInfo.setOutMerchantCode(outMerchantCode);
	        	  tdInfo.setMerchantCode(cr.getMerchantCode().trim());
		          tdInfo.setChannelNo(cr.getChannelNo());
		          tdInfo.setReportStatus("O");

		          // 查询商户报备信息
		          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
		          tdInfo.setPatchNo(tdInfo_.getPatchNo());
		          String mchntStatus = "15";
		          tdInfo.setFileStatus("Y");
		          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
		         object.put("outMerchantCode", outMerchantCode);
	        	 object.put("message", "商盟进件信息报备成功");
	        } else {
	          logger.error("商盟进件明确失败:" + reMessage);

	          object.put("message", reMessage);
	          object.put("result", "FAILURE");
	          return object;
	        }

	      } else {
	        logger.error("商盟进件明确失败:" + channelResult.getReMsg());
	        object.put("result", "FAILURE");
	        object.put("message", channelResult.getReMsg());
	        return object;
	      }

  } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      object.put("result", "FAILURE");
      object.put("message", e);
      return object;
    }
    object.put("result", "SUCCESS");
    return object;
}

public JSONObject sumpayFileUpload(SumPayCoBean cr) {
	 JSONObject object = new JSONObject();
	 try {
			//获取图片路径
			TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
			
			//获取开户许可证路径
			String openPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "03");
			
			//获取身份证正反面
			String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
			String[] paths = null;
			paths = identityImagePath.split(";");
			//正面
			String identityImagePath0 = paths[0];
			//反面
			String identityImagePath1 = paths[1];
			
			//获取营业执照
			String licensePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
			
			//获取银行卡照
			String bankCardPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "07");
			
			//获取门头照
			String doorPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
			
			//获取店内照
			String shopInteriorPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "18");
			if(null == shopInteriorPath && null != doorPath){
				String[] pathDoorImage = null;
				pathDoorImage = doorPath.split(";");
				if(pathDoorImage.length >1){
					shopInteriorPath = pathDoorImage[1];
				}else{
					//内景
					shopInteriorPath = doorPath;
				}
			}
			
			SumpayUploadFileInfoList list = new SumpayUploadFileInfoList();
			list.setMchntId(cr.getOutMerchantCode());
			List<SumpayUploadFileInfo>  sumpayUploadFileInfos = new ArrayList<SumpayUploadFileInfo>();
			//营业执照
			SumpayUploadFileInfo businessLicenseFileInfo = new SumpayUploadFileInfo();
			if(null != cr.getLicensePath()){
				businessLicenseFileInfo.setPicPath(cr.getLicensePath());
			}else{
				businessLicenseFileInfo.setPicPath(licensePath);
			}
			paths = null;
			paths =cr.getLicensePath().split("\\.");
			SumpaySuffixNameType suffixName = null;
			if(0 == paths.length){
				logger.error("营业执照路径为空");
			    object.put("result", "FAILURE");
		        object.put("message", "营业执照路径为空");
		        return object;
			}
			for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
				if(type.getValue().equals(paths[(paths.length-1)])){
					suffixName = type;
				}
			}
			businessLicenseFileInfo.setSuffixName(suffixName);
			businessLicenseFileInfo.setBusinessType(SumpayBusinessType.business_license_pic);
				
			sumpayUploadFileInfos.add(businessLicenseFileInfo);
			//身份证正面照
			SumpayUploadFileInfo idCardZFileInfo = new SumpayUploadFileInfo();
			if(null != cr.getIdentityImagePath0()){
				idCardZFileInfo.setPicPath(cr.getIdentityImagePath0());
			}else{
				idCardZFileInfo.setPicPath(identityImagePath0);
			}
			paths = null;
			paths = cr.getIdentityImagePath0().split("\\.");
			suffixName = null;
			if(0 == paths.length){
				logger.error("身份证正面照路径为空");
		        object.put("result", "FAILURE");
		        object.put("message", "身份证正面照路径为空");
		        return object;
			}
			for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
				if(type.getValue().equals(paths[(paths.length-1)])){
					suffixName = type;
				}
			}
			idCardZFileInfo.setSuffixName(suffixName);
			idCardZFileInfo.setBusinessType(SumpayBusinessType.id_card_legal_z_pic);
			
			
			sumpayUploadFileInfos.add(idCardZFileInfo);
			
			//身份证反面照
			SumpayUploadFileInfo idCardFFileInfo = new SumpayUploadFileInfo();
			if(null != cr.getIdentityImagePath1()){
				idCardFFileInfo.setPicPath(cr.getIdentityImagePath1());
			}else{
				idCardFFileInfo.setPicPath(identityImagePath1);
			}
			paths = null;
			paths = cr.getIdentityImagePath1().split("\\.");
			suffixName = null;
			if(0 == paths.length){
				logger.error("身份证反面照路径为空");
		        object.put("result", "FAILURE");
		        object.put("message", "身份证反面照路径为空");
		        return object;
			}
			for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
				if(type.getValue().equals(paths[(paths.length-1)])){
					suffixName = type;
				}
			}
			idCardFFileInfo.setSuffixName(suffixName);
			idCardFFileInfo.setBusinessType(SumpayBusinessType.id_card_legal_f_pic);
			
			sumpayUploadFileInfos.add(idCardFFileInfo);
			
			//开户许可证（没有则传送对私代付银行卡）
			SumpayUploadFileInfo openAccFileInfo = new SumpayUploadFileInfo();
			if(null == cr.getOpenPath() || null == openPath){
				if(null == cr.getBankCardPath()){
					openAccFileInfo.setPicPath(bankCardPath);
				}else{
					openAccFileInfo.setPicPath(cr.getBankCardPath());
				}
				
				paths = null;
				paths = cr.getBankCardPath().split("\\.");
				suffixName = null;
				if(0 == paths.length){
					logger.error("银行卡照路径为空");
			        object.put("result", "FAILURE");
			        object.put("message", "银行卡照路径为空");
			        return object;
				}
				for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
					if(type.getValue().equals(paths[(paths.length-1)])){
						suffixName = type;
					}
				}
				openAccFileInfo.setSuffixName(suffixName);
			}else{
				if(null != cr.getOpenPath()){
					openAccFileInfo.setPicPath(cr.getOpenPath());
				}else{
					openAccFileInfo.setPicPath(openPath);
				}
				
				paths = null;
				paths = cr.getOpenPath().split("\\.");
				suffixName = null;
				if(0 == paths.length){
					logger.error("开户许可证照路径为空");
			        object.put("result", "FAILURE");
			        object.put("message", "开户许可证照路径为空");
			        return object;
				}
				for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
					if(type.getValue().equals(paths[(paths.length-1)])){
						suffixName = type;
					}
				}
				openAccFileInfo.setSuffixName(suffixName);
			}
			
			openAccFileInfo.setBusinessType(SumpayBusinessType.open_account_pic);
			sumpayUploadFileInfos.add(openAccFileInfo);
			
			//门头照
			SumpayUploadFileInfo shopFileInfo = new SumpayUploadFileInfo();
			
			if(null != cr.getDoorPhotoPath()){
				shopFileInfo.setPicPath(cr.getDoorPhotoPath());
			}else{
				shopFileInfo.setPicPath(doorPath);
			}
			paths = null;
			paths = cr.getDoorPhotoPath().split("\\.");
			suffixName = null;
			if(0 == paths.length){
				logger.error("门头照路径为空");
		        object.put("result", "FAILURE");
		        object.put("message", "门头照路径为空");
		        return object;
			}
			for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
				if(type.getValue().equals(paths[(paths.length-1)])){
					suffixName = type;
				}
			}
			shopFileInfo.setSuffixName(suffixName);
			shopFileInfo.setBusinessType(SumpayBusinessType.shop_entrance_pic);
			
			sumpayUploadFileInfos.add(shopFileInfo);
			
			SumpayUploadFileInfo storeInteriorInfo = new SumpayUploadFileInfo();
			
			if(null != cr.getShopInteriorPath()){
				storeInteriorInfo.setPicPath(cr.getShopInteriorPath());
			}else{
				storeInteriorInfo.setPicPath(shopInteriorPath);
			}	
			paths = null;
			paths = cr.getShopInteriorPath().split("\\.");
			suffixName = null;
			if(0 == paths.length){
				logger.error("店内照路径为空");
		        object.put("result", "FAILURE");
		        object.put("message", "店内照路径为空");
		        return object;
			}
			for(SumpaySuffixNameType type : SumpaySuffixNameType.values()){
				if(type.getValue().equals(paths[(paths.length-1)])){
					suffixName = type;
				}
			}
			storeInteriorInfo.setSuffixName(suffixName);
			storeInteriorInfo.setBusinessType(SumpayBusinessType.manage_location_pic);
			
			
			sumpayUploadFileInfos.add(storeInteriorInfo);

			
			list.setSumpayUploadFileInfos(sumpayUploadFileInfos);
			Map<String, Object> req = new HashMap<>();
			req.put("merList", list);
			req.put("channelType", ChannelMerRegist.SUM_PAY);
			
			ChannelResult channelResult = iMerChantIntoService.fileUpload(req);
			logger.info("更新图片请求返回>>>>>{}",channelResult);
		    if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())){
		        Map<String, Object> data = channelResult.getData();
		        
		        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
		            && "00".equals(channelResult.getReCode())) {
		          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
		          tdInfo.setMerchantCode(cr.getMerchantCode().trim());
		          tdInfo.setChannelNo(cr.getChannelNo());
		          tdInfo.setReportStatus("O");

		          // 查询商户报备信息
		          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
		          tdInfo.setPatchNo(tdInfo_.getPatchNo());
		          String mchntStatus = "0";
		          tdInfo.setFileStatus("Y");
		          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
		        } else {
		          logger.error("商盟进件明确失败:" + channelResult.getData().get("errorMsg"));

		          object.put("message", channelResult.getData().get("errorMsg"));
		          object.put("result", "FAILURE");
		          return object;
		        }

		      } else {
		        logger.error("商盟进件明确失败:" + channelResult.getReMsg());
		        object.put("result", "FAILURE");
		        object.put("message", "");
		        return object;
		      }
	} catch (Exception e) {
		 logger.error("FmIncomingServlet execute exception", e);
	      object.put("result", "FAILURE");
	      object.put("message", e);
	      return object;
	}
	 object.put("result", "SUCCESS");
	    return object;
	
}

/***
 * 查询省份
 * 
 * @return
 */
public List<SumPayArea> getSumPayProvinceList(String superiorAreaCode) {
  return fmIncomeMapperDao.selSumPayProvinceList(superiorAreaCode);
}

public List<SumpayMcc> getSumpayMccList() {
	 return fmIncomeMapperDao.selSumpayMccList();
}

public List<SumpayMcc> getSumPayMccTwoList(String one) {
	return fmIncomeMapperDao.selSumPayMccTwoList(one);
}

public List<SumpayMcc> getSumPayMccThreeList(SumpayMcc sumpayMcc) {
	return fmIncomeMapperDao.selSumPayMccThreeList(sumpayMcc);
}

public List<SumpayMcc> getSumpayMccWXList() {
	 return fmIncomeMapperDao.selSumpayMccWXList();
} 
public List<SumpayMcc> getSumPayMccTwoWXList(String one) {
	return fmIncomeMapperDao.selSumPayMccTwoWXList(one);
}

public List<SumpayMcc> getSumPayMccThreeWXList(SumpayMcc sumpayMcc) {
	return fmIncomeMapperDao.selSumPayMccThreeWXList(sumpayMcc);
}

public List<SumpayMcc> getSumpayMccZFBList() {
	return fmIncomeMapperDao.selSumpayMccZFBList();
}

public List<YQBArea> getYQBProvinceList() {
	return fmIncomeMapperDao.selYQBProvinceList();
}

public List<YQBArea> getYQBCityList(YQBArea yabArea) {
	return fmIncomeMapperDao.selYQBCityList(yabArea);
}

public List<YQBArea> getYQBAreaList(YQBArea yabArea) {
	return fmIncomeMapperDao.selYQBAreaList(yabArea);
}

public List<YQBIndustry> getYQBIndustryList() {
    return fmIncomeMapperDao.selYQBIndustryList();
  }

public List<YQBIndustry> getYQBIndustryCodeList(YQBIndustry yqbIndustry) {
	return fmIncomeMapperDao.selYQBIndustryCodeList(yqbIndustry);
}

public List<Bank> getYQBBankList(Bank bank) {
	return fmIncomeMapperDao.selYQBBankList(bank);
}



/***
 * 平安付报备 zhanggc
 *  
 * @param yqb
 * @return
 */
@SuppressWarnings("null")
public JSONObject yqbReported(YQBCoBean cr) {
  JSONObject object = new JSONObject();
  try {
	  YqbMerchantInfo merchantInfo = new YqbMerchantInfo();
	  //获取图片路径
	  TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
	  
	  	MerchantVo merchantVo = merchantMapper.newFindMerchantInfo(custInfo.getCustId());
		PicturePath picturePathOld = merchantEnterService.getPicPath(merchantVo);
		String removeStr = "/pic/";
		merchantInfo.setOutMerchantNo(cr.getMerchantCode());		//服务商侧商户号			--必填
		merchantInfo.setMerchantType(cr.getMerchantCodeType());	//商户类型		--必填
		merchantInfo.setBusinessLicenseNo(cr.getBusinessLicense());		//营业执照号	--企业与个体工商必填
		merchantInfo.setMerchantName(cr.getCustName());		//商户名称		--必填
		merchantInfo.setMerchantShortName(cr.getShortName());		//商户简称		--必填
		merchantInfo.setLegalRepresent(cr.getInterName());		//法人姓名		--必填
		
		//营业执照照片路径		--企业与个体工商必填
		if(null == cr.getBusinessPhotoPath() && "".equals(cr.getBusinessPhotoPath())){
			if(null != picturePathOld.getBussinessPath().replaceAll(removeStr, null)) {
				String licensePath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getBussinessPath().replaceAll(removeStr, null);
				merchantInfo.setBusinessLicensePath(licensePath);
			}
		}else{
			merchantInfo.setBusinessLicensePath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getBusinessPhotoPath());
		}
		
		merchantInfo.setBusinessLicenseEffDate(cr.getBusinessEffectiveTerm());		//营业执照生效日  格式：yyyy-MM-dd
		merchantInfo.setBusinessLicenseValDate(cr.getBusinessTerm());		//营业执照到期日  格式：yyyy-MM-dd
		merchantInfo.setFeeRate(cr.getRate());		//费率		--必填
		merchantInfo.setEstablishDate(cr.getEstablishDate());		//成立日期  格式：yyyy-MM-dd		--企业与个体工商必填
		merchantInfo.setIdentityNo(cr.getCertifyNo());		//法人身份证号		--必填
		
		//法人身份证正面照路径		--必填
		if(null ==cr.getCertAttribute1Path() && "".equals(cr.getCertAttribute1Path())){
			if(null != picturePathOld.getIdCardOPath().replaceAll(removeStr, null)) {
				//正面
				String identityImagePath0 =ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getIdCardOPath().replaceAll(removeStr, null);
				merchantInfo.setIdentityPosPath(identityImagePath0);
			}else {
				object.put("message", "身份证正面为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			merchantInfo.setIdentityPosPath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getCertAttribute1Path());
		}
		
		//法人身份证反面照路径		--必填
		if(null == cr.getCertAttribute2Path() && "".equals(cr.getCertAttribute2Path())){
			if(null != picturePathOld.getIdCardFPath().replaceAll(removeStr, null)) {
				//反面
				String identityImagePath1 = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getIdCardFPath().replaceAll(removeStr, null);
				merchantInfo.setIdentityNegPath(identityImagePath1);
			}else {
				object.put("message", "身份证反面为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			merchantInfo.setIdentityNegPath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getCertAttribute2Path());
		}
		
		merchantInfo.setIdentityEffDate(cr.getIdentityEffDate());		//法人身份证有效期开始日  格式：yyyy-MM-dd		--必填
		merchantInfo.setIdentityValDate(cr.getIdentityValDate());		//法人身份证有效期到期日  格式：yyyy-MM-dd		--必填
		merchantInfo.setIndustryCode(cr.getIndustryCode());		//行业编码		--必填
		merchantInfo.setProvinceCode(cr.getProvince());		//省代码		--必填
		merchantInfo.setCityCode(cr.getCity());		//市代码		--必填
		merchantInfo.setCountyCode(cr.getArea());		//区县代码		--必填
		merchantInfo.setBusinessAddress(cr.getCprRegAddr());		//商户营业地址		--必填
		
		//店铺门面照		--必填
		if(null == cr.getDoorPhotoPath() && "".equals(cr.getDoorPhotoPath())){
			if(null != picturePathOld.getDoorPhotoPath().replaceAll(removeStr, null)) {
				//获取门头照
				String doorPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getDoorPhotoPath().replaceAll(removeStr, null);
				merchantInfo.setStoreFacadePath(doorPath);
			}else if(null == picturePathOld.getDoorPhotoPath().replaceAll(removeStr, null) && null != picturePathOld.getShopInteriorPath().replaceAll(removeStr, null)) {
				//门头照为空店内照不为空
				String shopInteriorPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getShopInteriorPath().replaceAll(removeStr, null);
				merchantInfo.setStoreFacadePath(shopInteriorPath);
			}else {
				object.put("message", "门头照为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			merchantInfo.setStoreFacadePath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getDoorPhotoPath());
		}
		
		//商户经营场所或仓库照		--必填
		if(null == cr.getShopInteriorPath() && "".equals(cr.getShopInteriorPath())){
			if(null != picturePathOld.getShopInteriorPath().replaceAll(removeStr, null)) {
				//获取店内照
				String shopInteriorPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getShopInteriorPath().replaceAll(removeStr, null);
				merchantInfo.setStoreBussinessPlacePath(shopInteriorPath);
			}else if(null != picturePathOld.getDoorPhotoPath().replaceAll(removeStr, null) && null == picturePathOld.getShopInteriorPath().replaceAll(removeStr, null)){
				//店内照为空门头照不为空
				String doorPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getDoorPhotoPath().replaceAll(removeStr, null);
				merchantInfo.setStoreBussinessPlacePath(doorPath);
			}else {
				object.put("message", "商户经营场所或仓库照为空");
		        object.put("result", "FAILURE");
		        return object;
			}
			
		}else{
			merchantInfo.setStoreBussinessPlacePath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getShopInteriorPath());
		}
		
		//经营者手持身份证拍照图片		--非必填
		//merchantInfo.setStoreWithOwnerPath(null);		
		
		//特殊行业资质图片		--企业与个体工商必填
		List<String> strs = new ArrayList<String>();
		if(null ==cr.getQualificationPath() && "".equals(cr.getQualificationPath())){
			if(null != picturePathOld.getQualificationPath().replaceAll(removeStr, null)) {
				String qualificationPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getQualificationPath().replaceAll(removeStr, null);
				strs.add(qualificationPath);
			}else if((!("12".equals(cr.getMerchantCodeType()))) && (null == picturePathOld.getQualificationPath().replaceAll(removeStr, null))) {
				object.put("message", "特殊行业资质图片为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			strs.add(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getQualificationPath());
		}
		merchantInfo.setSpecialIndustryPaths(strs);
		
		//其他材料图片			--个人商户必填
		List<String> strss = new ArrayList<String>();
		if(null == cr.getOtherMaterialPath() && "".equals(cr.getOtherMaterialPath())){
			if(null != picturePathOld.getOtherMaterialPath().replaceAll(removeStr, null)) {
				String otherMaterialPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getOtherMaterialPath().replaceAll(removeStr, null);
				strss.add(otherMaterialPath);
			}else if("12".equals(cr.getMerchantCodeType()) && (null == picturePathOld.getOtherMaterialPath().replaceAll(removeStr, null))) {
				object.put("message", "其他材料图片为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			strss.add(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getOtherMaterialPath());
		}
		merchantInfo.setOtherMaterialPaths(strss);		
		
		//电子签名照   只支持png格式		--必填
		if(null == cr.getSignaturePath() && "".equals(cr.getSignaturePath())){
			if(null != picturePathOld.getSignaturePath().replaceAll(removeStr, null)) {
				String signaturePath = ADMANAGE_FILE_SAVE_PATH + "/" +  picturePathOld.getSignaturePath().replaceAll(removeStr, null);
				merchantInfo.setElectronicSignPath(signaturePath);
			}else {
				object.put("message", "电子签名照 为空");
		        object.put("result", "FAILURE");
		        return object;
			}
		}else{
			merchantInfo.setElectronicSignPath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getSignaturePath());
		}
		
		merchantInfo.setBankAccountName(cr.getAccountNm());		//结算账户名称  个体：法人本人借记卡银行卡("");企业：企业账户名称("");个人：本人借记卡银行卡	--必填
		merchantInfo.setBankAccountNo(cr.getAccountNo());		//结算账户账号   个体：法人本人借记卡账号("");企业：企业账号("");个人：本人借记卡账号		--必填
		merchantInfo.setBankName(cr.getBankName());		//开户行名称		--必填
		merchantInfo.setBranchBankName(cr.getInterBankName());		//支行名称		--企业必填
		merchantInfo.setBankCode(cr.getBank());		//银行编码		--必填
		merchantInfo.setSubBankCode(cr.getBankCode());		//联行号			--企业必填
		
		//结算卡照片  个体/个人：银行卡正面照片("");企业：开户许可证、印鉴卡或流水单   		--必填
		if(MerchantType.business.getValue().equals(merchantInfo.getMerchantType())){
			if(null == cr.getOpenPath()){
				if(null != picturePathOld.getOpenAccountPath().replaceAll(removeStr, null)) {
					//获取开户许可证路径
					String openPath = ADMANAGE_FILE_SAVE_PATH + "/" + picturePathOld.getOpenAccountPath().replaceAll(removeStr, null);
					merchantInfo.setBankCardPath(openPath);
				}
			}else{
				merchantInfo.setBankCardPath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getOpenPath());
			}
		}else{
			if(null == cr.getBankCardPath()){
				if(null != picturePathOld.getBankCardPath().replaceAll(removeStr, null)) {
					//获取银行卡照
					String bankCardPath =ADMANAGE_FILE_SAVE_PATH + "/" +  picturePathOld.getBankCardPath().replaceAll(removeStr, null);
					merchantInfo.setBankCardPath(bankCardPath);
				}
			}else{
				merchantInfo.setBankCardPath(ADMANAGE_FILE_SAVE_PATH + "/" + cr.getBankCardPath());
			}
			
		}
		
		//银行卡预留手机号		--个体/个人必填
		if(!"10".equals(cr.getMerchantCodeType())){
			merchantInfo.setOpenBankPhoneNo(cr.getMobile());	
		}
		merchantInfo.setAdminName(cr.getAttentionName());		//管理员姓名		--必填
		merchantInfo.setAdminCellPhoneNo(cr.getAttentionMobile());		//管理员手机号		--必填
		
		Map<String, Object> req = new HashMap<>();
		req.put("merList", merchantInfo);
		req.put("channelType", ChannelMerRegist.YQB);
		
		logger.info("平安付进件请求报文：" + JSONObject.toJSONString(req));
	    ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
	    logger.info("平安付进件响应报文：" + JSONObject.toJSONString(channelResult));
	    
	    
	    if (channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
	        Map<String, Object> data = channelResult.getData();
	        /*
	         * String status =String.valueOf(data.get("status")); String mchntStatus
	         * =String.valueOf(data.get("mchntStatus"));
	         */
	        String reMessage = StringUtils.isBlank(channelResult.getReMsg()) ? "请联系客服": channelResult.getReMsg();
	        if (ReStatus.SUCCESS.equals(channelResult.getStatus())
	            && "00".equals(channelResult.getChannelCode())) {
	        	  TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
	        	  /*String outMerchantCode = StringUtils.isBlank((String)channelResult.getData().get("mchntId")) ? "" :(String)channelResult.getData().get("mchntId");
		          tdInfo.setOutMerchantCode(outMerchantCode);*/
	        	  tdInfo.setMerchantCode(cr.getMerchantCode().trim());
		          tdInfo.setChannelNo(cr.getChannelNo());
		          tdInfo.setReportStatus("Y");
		          tdInfo.setDetailStatus("00");

		          // 查询商户报备信息
		          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
		          tdInfo.setPatchNo(tdInfo_.getPatchNo());
		          String mchntStatus = "00";
		          tdInfo.setFileStatus("Y");
		          UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
	        	 object.put("message", "平安付进件信息报备成功");
	        } else {
	          logger.error("平安付进件明确失败:" + reMessage);

	          object.put("message", reMessage);
	          object.put("result", "FAILURE");
	          return object;
	        }

	      } else {
	        logger.error("平安付进件明确失败:" + channelResult.getReMsg());
	        object.put("result", "FAILURE");
	        object.put("message", channelResult.getReMsg());
	        return object;
	      }

  } catch (Exception e) {
      logger.error("FmIncomingServlet execute exception", e);
      object.put("result", "FAILURE");
      object.put("message", e);
      return object;
    }
    object.put("result", "SUCCESS");
    return object;
}

}


