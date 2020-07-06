package com.qifenqian.bms.basemanager.merchant.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.BmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.bean.TdCustBelongInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TinyMerchantExport;
import com.qifenqian.bms.basemanager.merchant.dao.MerchantDao;
import com.qifenqian.bms.basemanager.merchant.mapper.BmsProtocolContentMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.StoreManageMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdCertificateAuthMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.common.util.RedisUtil;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.platform.web.myWorkSpace.service.WorkSpaceService;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.RtnResult;
import com.sevenpay.invoke.transaction.bindbankcard.BindBankCardRequest;
import com.sevenpay.invoke.transaction.createbuss.CreateAcctSevenBussRequest;
import com.sevenpay.invoke.transaction.createbuss.CreateAcctSevenBussResponse;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

import redis.clients.jedis.Jedis;

/**
 * 商户注册服务类
 * 
 * @author dayet
 * @data 2015-06-8
 * 
 */
@Service
public class MerchantService {

  private static Base64 base64 = new Base64();

  @Autowired
  private CommonService commonService;
  @Autowired
  private BankService bankService;
  @Autowired
  SevenpayCoreServiceInterface sevenpayCoreServiceInterface;
  @Autowired
  private TdLoginUserInfoMapper mapper;

  @Autowired
  private MerchantMapper merchantMapper;

  @Autowired
  private CustScanMapper custScanMapper;

  @Autowired
  private MerchantDao dao;

  @Resource
  private RepositoryService repositoryService;

  @Resource
  private RuntimeService runtimeService;

  @Resource
  private TaskService taskService;

  @Resource
  private IdentityService identityService;

  @Autowired
  private BmsProtocolContentMapper bmsProtocolContentMapper;

  @Autowired
  private TdCustInfoMapper tdCustInfoMapper;

  @Autowired
  private WorkSpaceService workSpaceService;

  @Autowired
  private TdLoginUserInfoMapper tdLoginUserInfoMapper;

  @Autowired
  private AcctSevenBussMapper acctSevenBussMapper;

  @Autowired
  private StoreManageMapper storeManageMapper;

  @Autowired
  private TdCertificateAuthMapper tdCertificateAuthMapper;
  
  @Value("${CF_FILE_SAVE_PATH}")
  private String CF_FILE_SAVE_PATH;

  private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);

  /**
   * 获取核心交易接口（新）
   * 
   * @return
   */
  private SevenpayCoreServiceInterface getSevenpayCoreServiceInterface() {
    SevenpayCoreServiceInterface sevenpayCoreServiceInterface =
        (SevenpayCoreServiceInterface) SpringUtils.getBean("coreHttpInvokerProxy");
    return sevenpayCoreServiceInterface;
  }

  /**
   * 根据ID修改custSan信息
   */
  public void updateCustSanById(CustScan custScan) {
    if (null == custScan) {
      throw new IllegalArgumentException("证件信息为空");
    }
    if (StringUtils.isEmpty(custScan.getCustId())) {
      throw new IllegalArgumentException("用户ID为空");
    }
    custScanMapper.updateCustScanById(custScan);
  }


  public ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> requestBindBank(
      TdCustInfo vo) {
    ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> response =
        null;
    try {
      // InterfaceService service = this.getTranCoreIntfService();

      RequestMessage<BindBankCardRequest> requestMessage =
          new RequestMessage<BindBankCardRequest>();

      requestMessage.setMsgType(MsgType.BANK_CARD_BIND);
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
      requestMessage.setReqSerialId(DatetimeUtils.datetime());
      requestMessage.setReqMsgNum(1);
      requestMessage.setReqDatetime(new Date());

      BindBankCardRequest request = new BindBankCardRequest();
      Bank bank = null;
      if (vo.getCompAcctBank() != null) {
        bank = bankService.selectBankByBankCode(vo.getCompAcctBank());
        if (bank != null) {
          logger.info("商户使用的银行为：" + bank.getBankName());
        } else {
          logger.error("商户使用的银行系统中不存在：" + vo.getCompAcctBank());
        }

      }
      request.setOperateType(RequestColumnValues.BindOperateType.BIND);
      request.setCustType(RequestColumnValues.CustType.BUSINESS);
      request.setCustId(vo.getCustId());
      request.setAcctType(RequestColumnValues.AcctType.BANK_DEBIT);//
      request.setBankCardNo(vo.getCompMainAcct());
      request.setBankCardName(vo.getCustName());// 必填
      request.setBusinessType(RequestColumnValues.BusinessType.WITHDRAW);//
      request.setPhone(vo.getRepresentativeMobile());
      request.setBankCode12(vo.getCompAcctBank());
      if (bank != null) {
        request.setBankName(bank.getBankName());
      }

      // request.setIdType(RequestColumnValues.IdType.IDENTITY);
      // request.setIdNo("个人身份证");//
      request.setBrief("提现卡");

      // requestMessage.setMsgType(RequestColumnValues.MsgType.BND_CAD_UN);
      // requestMessage.setReqMsgNum(1);
      // requestMessage.setReqSerialId(GenSN.getSN());
      // BindBankCardRequest bankCardRequest=new BindBankCardRequest();
      // bankCardRequest.setBindType(RequestColumnValues.BindOperateType.BIND);
      // bankCardRequest.setCustId(vo.getCustId());
      // bankCardRequest.setCustName(vo.getCustName());
      // bankCardRequest.setCustType(RequestColumnValues.CustType.CUSTOMER);
      // bankCardRequest.setMsgTypeUse(RequestColumnValues.MsgType.SEV_CASH_O);
      // bankCardRequest.setAcctType(RequestColumnValues.AcctType.BANK_CUS_0);
      // bankCardRequest.setAcctNo(vo.getCompMainAcct());
      // bankCardRequest.setAcctName(vo.getCustName());
      // bankCardRequest.setIdType(RequestColumnValues.IdType.IDENTITY);
      // bankCardRequest.setIdNo("个人身份证");
      // bankCardRequest.setQuickPass(RequestColumnValues.QuickPass.Y);
      //
      // bankCardRequest.setBankCode12(vo.getCompAcctBank());

      // bankCardRequest.setBankName(bankCard.getBankName());
      // bankCardRequest.setPhone(vo.getRepresentativeMobile());

      /*
       * for(IdType rate:IdType.values()){ System.out.println(rate.getCode());
       * 
       * //bankCardRequest.setIdType(rate); System.out.println(rate); }
       */
      // String idType =vo.getCertifyType();
      // if(idType.equals(RequestColumnValues.IdType.))
      // bankCardRequest.setIdType(RequestColumnValues.IdType);
      // requestMessage.setReqDatetime(new Date());
      // requestMessage.setRequest(bankCardRequest);
      //
      // requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
      requestMessage.setRequest(request);
      response = sevenpayCoreServiceInterface.bindBankCard(requestMessage);
      response.setRtnResult(RequestColumnValues.RtnResult.SUCCESS);
      return response;
    } catch (Exception e) {
      logger.error("创建七分钱账户异常", e);
    }
    
    return response;

  }

  /**
   * 保存商户登陆信息
   */
  public void saveLoginMerchant(String email, String custId, Merchant merchant) {

    if (StringUtils.isEmpty(email)) {
      throw new IllegalArgumentException("邮箱地址为空");
    }

    // 设置附加串
    String attachStr = GenSN.getRandomNum(5);

    // 角色ID
    String roleId = Constant.ROLE_ENT;
    // 登陆状态
    String state = Constant.LOGIN_STATE_AUDITORING;

    User user = WebUtils.getUserInfo();
    // 创建人ID
    String createId = String.valueOf(user.getUserId());
    TdLoginUserInfo userInfo = new TdLoginUserInfo();

    {
      userInfo.setEmail(email.toLowerCase());
      userInfo.setCustId(custId);
      userInfo.setAttachStr(attachStr);
      userInfo.setRoleId(roleId);
      userInfo.setState(state);
      userInfo.setCreateId(createId);
      userInfo.setMobile(merchant.getMobile());
    }

    this.saveLoginUserInfo(userInfo);

  }

  /**
   * 保存商户登陆信息v2
   */
  public void saveLoginMerchant2(Merchant merchant) {

    if (StringUtils.isEmpty(merchant.getMerchantAccount())) {
      throw new IllegalArgumentException("手机号或邮箱为空");
    }

    // 设置附加串
    String attachStr = GenSN.getRandomNum(5);

    // 角色ID
    String roleId = Constant.ROLE_AGENT;
    //merchant.getRoleId()) 不等于空  表示为服务商的
    if (! StringUtils.isBlank(merchant.getRoleId())) {
    	roleId=merchant.getRoleId();
	}
    // 登陆状态
    String state = Constant.LOGIN_STATE_AUDITORING;

    User user = WebUtils.getUserInfo();
    // 创建人ID
    String createId = String.valueOf(user.getUserId());
    TdLoginUserInfo userInfo = new TdLoginUserInfo();

    if(merchant.getMerchantAccount().contains("@")){
        //账号为邮箱
        userInfo.setEmail(merchant.getMerchantAccount().toLowerCase());
    }else {
        userInfo.setMobile(merchant.getMerchantAccount().toLowerCase());
    }
    {
      //userInfo.setEmail(merchant.getEmail().toLowerCase());
      userInfo.setCustId(merchant.getCustId());
      userInfo.setAttachStr(attachStr);
      userInfo.setRoleId(roleId);
      userInfo.setState(state);
      userInfo.setCreateId(createId);
      //userInfo.setMobile(merchant.getMobile());
    }

    this.saveLoginUserInfo(userInfo);

  }

  /** 微商户保存登录信息 */
  public void saveLoginTinyMerchant(String email, String custId, Merchant merchant) {
    if (StringUtils.isBlank(email))
      throw new IllegalArgumentException("邮箱地址为空");
    logger.info("========== 微商户保存登录信息开始 ==========");
    try {
      TdLoginUserInfo userInfo = new TdLoginUserInfo();
      userInfo.setEmail(email.toLowerCase()); /** Email地址 */
      userInfo.setCustId(custId); /** 客户编号 */
      userInfo.setAttachStr(GenSN.getRandomNum(5)); /** 附加串，用于生成加密密码 */
      userInfo.setRoleId(merchant.getRoleId()); /** 角色ID：per 个人角色,ent 企业角色 */
      userInfo.setState(Constant.LOGIN_STATE_MANAGER_AGREEMENTING); /**
                                                                     * 状态：00 正常；01 停用；02 登录账户冻结 ; 03
                                                                     * 注册待激活；04 商户审核中
                                                                     */
      userInfo.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId())); /** 创建人 */
      userInfo.setMobile(merchant.getMobile());
      this.saveLoginUserInfo(userInfo);
      logger.info("========== 微商户保存登录信息成功 ==========");
    } catch (Exception e) {
      logger.error("微商户保存登录信息失败", e.getMessage());
      throw e;
    }
  }

  // private String getAuthId(){
  // SimpleDateFormat sdf = new SimpleDateFormat("");
  // }

  /**
   * 保存登陆用户信息
   * 
   * @param userInfo
   */
  public void saveLoginUserInfo(TdLoginUserInfo userInfo) {
    mapper.saveLoginUserInfo(userInfo);
  }

  /**
   * 发送信息
   * 
   * @param custId
   * @param content
   */
  public boolean sendInfo(String email, String content, String subject,
      MessageColumnValues.MsgType msgType, MessageColumnValues.busType busType) {

    IPlugin plugin = commonService.getIPlugin();

    MessageBean messageBean = new MessageBean();
    String[] tos = new String[] {email};
    messageBean.setSubject(subject);
    messageBean.setContent(content);
    messageBean.setTos(tos);
    messageBean.setMsgType(msgType);
    messageBean.setBusType(busType);

    return plugin.sendMessage(msgType, messageBean);
  }

  /**
   * 保存商户信息
   */
  public void saveMerchant(Merchant merchant) {
    logger.info("保存商户信息[{}]", JSONObject.toJSONString(merchant));

    MerchantVo merchantVo = new MerchantVo();
    merchantVo.setCustId(merchant.getCustId());
    try {
      /** 附加串，用于生成加密的交易密码 **/
      String attachStr = GenSN.getRandomNum(5);
      merchant.setAttachStr(attachStr);
      /** 创建人 **/
      String createId = String.valueOf(WebUtils.getUserInfo().getUserId());
      merchant.setCreateId(createId);
      /** 实名认证状态:审核中 **/
      merchant.setTrustCertifyAuditState(Constant.TRUST_AUDIT_PASSING);// 后台注册标识
      /** 客户类型 :企业 **/
      merchant.setCustType(Constant.CUST_TYPE_COMPANY);
      /** 商户标识:商户 **/
      merchant.setMerchantFlag(Constant.MERCHANT_FLAG_TRADE);
      /** 客户状态:待审核 **/
      merchant.setState(Constant.CUST_STATE_WAITINGVERIFY);
      /** 等级 **/
      merchant.setTrustCertifyLvl(Constant.MERCHANT_NO_CERTIFY);

      merchantMapper.saveMerchant(merchant);

      // 开通七分钱 获取七分钱内部Id
      // ResponseMessage<CreateAccountResponse> response =
      // this.getQfqId(merchant.getCustId());
      ResponseMessage<CreateAcctSevenBussResponse> response =
          this.getBussQfqId(merchant.getCustId(), merchant.getCustName());

      if (RtnResult.SUCCESS == response.getRtnResult()) {
        merchantVo.setQfqAccId(response.getResponse().getAccId());
      } else if (RtnResult.FAILURE == response.getRtnResult()) {
        merchantVo.setState(Constant.CUST_STATE_LOGOUT);
      } else {
        merchantVo.setState(Constant.CUST_STATE_LOGOUT);
      }
      merchantMapper.updateMerchant(merchantVo);
      logger.info("商户信息保存成功");
    } catch (Exception e) {
      logger.error("商户信息保存异常", e);
      throw e;

    }
  }

    /**
     * 保存商户信息
     */
    public void saveMerchant2(Merchant merchant) {
        logger.info("保存商户信息[{}]", JSONObject.toJSONString(merchant));

        MerchantVo merchantVo = new MerchantVo();

        if(merchant.getMerchantAccount().contains("@")){
            //账号为邮箱
        	merchant.setMerchantEmail(merchant.getMerchantAccount().toLowerCase());
        }else {
        	merchant.setMerchantMobile(merchant.getMerchantAccount().toLowerCase());
        }
        merchantVo.setCustId(merchant.getCustId());
        try {
            /** 附加串，用于生成加密的交易密码 **/
            String attachStr = GenSN.getRandomNum(5);
            merchant.setAttachStr(attachStr);
            /** 创建人 **/
            String createId = String.valueOf(WebUtils.getUserInfo().getUserId());
            merchant.setCreateId(createId);
            /** 实名认证状态:审核中 **/
            merchant.setTrustCertifyAuditState(Constant.TRUST_AUDIT_PASSING);// 后台注册标识
            /** 客户类型 :企业 **/
            //merchant.setCustType(Constant.CUST_TYPE_COMPANY);
            /** 商户标识:商户 **/
            if(null == merchant.getMerchantFlag()) {
            	merchant.setMerchantFlag(Constant.MERCHANT_FLAG_TRADE);	
            }
            /** 客户状态:待审核 **/
            merchant.setState(Constant.CUST_STATE_WAITINGVERIFY);
            /** 等级 **/
            merchant.setTrustCertifyLvl(Constant.MERCHANT_NO_CERTIFY);

            merchantMapper.saveMerchant(merchant);

            // 开通七分钱 获取七分钱内部Id
            // ResponseMessage<CreateAccountResponse> response =
            // this.getQfqId(merchant.getCustId());
            ResponseMessage<CreateAcctSevenBussResponse> response =
                    this.getBussQfqId(merchant.getCustId(), merchant.getCustName());

            if (RtnResult.SUCCESS == response.getRtnResult()) {
                merchantVo.setQfqAccId(response.getResponse().getAccId());
            } else if (RtnResult.FAILURE == response.getRtnResult()) {
                merchantVo.setState(Constant.CUST_STATE_LOGOUT);
            } else {
                merchantVo.setState(Constant.CUST_STATE_LOGOUT);
            }
            merchantMapper.updateMerchant(merchantVo);
            logger.info("商户信息保存成功");
        } catch (Exception e) {
            logger.error("商户信息保存异常", e);
            throw e;

        }
    }

  /** 保存微商户信息 */
  public void saveTinyMerchant(Merchant merchant) {
    logger.info("保存商户信息[{}]", JSONObject.toJSONString(merchant));
    MerchantVo merchantVo = new MerchantVo();
    String custId = merchant.getCustId();
    merchantVo.setCustId(custId);
    try {
      /** 保存微商户信息 **/
      merchant.setAttachStr(GenSN.getRandomNum(5)); /** 附加串，用于生成加密的交易密码 */
      merchant.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId())); /** 创建人 */ // 创建人
      merchant.setTrustCertifyAuditState(Constant.CERTIFY_STATUS_30); /** 实名认证状态： 30 认证通过 */
      merchant.setCustType(merchant.getCustType()); /** 客户类型 0-个人 */
      merchant.setMerchantFlag(Constant.MERCHANT_FLAG_TINYTRADE); /** 商户标识 2-微商户 */
      merchant.setState(Constant.CUST_STATE_VALID); /** 商户状态 00-有效 */
      merchant.setTrustCertifyLvl(Constant.MERCHANT_NO_TINYCERTIFY); /** 商户注册认证等级 3 */
      merchantMapper.saveMerchant(merchant); /** TD_CUST_INFO **/
      /** 开通七分钱、获取七分钱内部Id */
      ResponseMessage<CreateAcctSevenBussResponse> response =
          this.getBussQfqId(merchant.getCustId(), merchant.getCustName());
      if (RtnResult.SUCCESS == response.getRtnResult()) {
        merchantVo.setQfqAccId(response.getResponse().getAccId()); /** 七分钱账号 */
      } else if (RtnResult.FAILURE == response.getRtnResult()) {
        merchantVo.setState(Constant.CUST_STATE_LOGOUT); /** 商户状态 02-注销 */
      } else {
        merchantVo.setState(Constant.CUST_STATE_LOGOUT); /** 商户状态 02-注销 */
      }
      /** 获取七分钱内部ID后，更新状态 */
      merchantMapper.updateMerchant(merchantVo);
      logger.info("商户信息保存成功");
    } catch (Exception e) {
      logger.error("商户信息保存异常", e.getMessage());
      throw e;
    }
    /** 绑卡操作 */
    // try {
    // TdCustInfo custInfo = tdCustInfoService.selectById(custId);
    // ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> response =
    // this.requestBindBank(custInfo);
    // if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
    // logger.info("绑卡操作成功");
    // }
    // } catch (Exception e) {
    // logger.error("绑卡操作失败", e.getMessage());
    // throw e;
    // }
  }

  /**
   * 开通七分钱--商户
   * 
   * @param custId
   * @param custName
   * @return
   */
  @SuppressWarnings("finally")
  public ResponseMessage<CreateAcctSevenBussResponse> getBussQfqId(String custId, String custName) {
    ResponseMessage<CreateAcctSevenBussResponse> response = null;

    logger.info("创建七分钱 --商户账户[{}]", custId);

    try {
      SevenpayCoreServiceInterface sevenpayCoreServiceInterface =
          this.getSevenpayCoreServiceInterface();
      RequestMessage<CreateAcctSevenBussRequest> requestMessage =
          new RequestMessage<CreateAcctSevenBussRequest>();

      requestMessage.setMsgType(RequestColumnValues.MsgType.ACCT_BUSS_CREATE);
      requestMessage.setReqDatetime(new Date());
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
      requestMessage.setReqSerialId(DatetimeUtils.datetime());
      requestMessage.setReqMsgNum(1);

      CreateAcctSevenBussRequest request = new CreateAcctSevenBussRequest();

      request.setCustId(custId);
      request.setCustName(custName);

      requestMessage.setRequest(request);

      response = sevenpayCoreServiceInterface.createAcctSevenBuss(requestMessage);

    } catch (Exception e) {
      logger.error("创建七分钱 --商户账户[{}]异常", custId, e);
    } finally {
      return response;
    }

  }

  public TdLoginUserInfo selectLoginUserInfoByEmail(String email, String custId, String roleId) {

    if (StringUtils.isEmpty(email)) {
      throw new IllegalArgumentException("邮箱地址为空");
    }

    return mapper.selectByEmail(email.toLowerCase(), custId, roleId);
  }

  public TdLoginUserInfo selectLoginUserInfoByMobile(String mobile, String custId, String roleId) {

    if (StringUtils.isEmpty(mobile)) {
      throw new IllegalArgumentException("手机号为空");
    }

    return mapper.selectByPhone(mobile, roleId);
  }

  /** 验证微商户二维码编号是否被占用 **/
  public int validateTinyMerchantCode(String merchantCode) {
    if (StringUtils.isEmpty(merchantCode)) {
      throw new IllegalArgumentException("微商户二维码编号为空");
    }
    return merchantMapper.validateTinyMerchantCode(merchantCode);
  }

  /**
   * 前台商户注册审核列表
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantVo> selectMerchants(MerchantVo merchantVo) {
    return dao.list(merchantVo);
  }

  /**
   * 后台商户注册审核列表
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantVo> selectBackMerchants(MerchantVo merchantVo) {
    return dao.backList(merchantVo);
  }

  /**
   * 注册通过后的商户信息
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantVo> selectAuditMerchants(MerchantVo merchantVo) {
    List<MerchantVo> list = dao.auditList(merchantVo);
    return list;
  }

  /**
   * 导出商户信息
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantExport> exportMerchantInfo(MerchantVo merchantVo) {
    return merchantMapper.exportlist(merchantVo);
  }

  /** 导出微商户信息 */
  public List<TinyMerchantExport> exportTinyMerchantInfo(MerchantVo merchantVo) {
    return merchantMapper.exportTinyMerchantsList(merchantVo);
  }

  /**
   * 导出前台商户审核信息
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantExport> proExportMerchantInfo(MerchantVo merchantVo) {
    return merchantMapper.proExportlist(merchantVo);
  }

  // 修改商户信息
  public void updateEmail(MerchantVo merchantVo) {
    if (null == merchantVo) {
      throw new IllegalArgumentException("商户对象为空");
    }
    merchantMapper.updateEmail(merchantVo);
  }

  public void updateUserState(String custId) {
    merchantMapper.updateUserState(custId);

  }

  /**
   * 压缩上传
   * 
   * @param request
   * @param custId
   * @return
   * @throws IOException
   */
  public Map<String, String> compressUpload(HttpServletRequest request, String custId)
      throws IOException {

    Map<String, String> result = new HashMap<String, String>();
    try {
      HashMap<String, String> nameType = new HashMap<String, String>();
      String filename = null;
      // 绝对路径
     


      Map map = request.getParameterMap();


      Set keSet = map.entrySet();
      for (Iterator itr = keSet.iterator(); itr.hasNext();) {
        Map.Entry me = (Map.Entry) itr.next();
        String ok = (String) me.getKey();
        String ov = ((String[]) (me.getValue()))[0];
        if (StringUtils.isEmpty(ov)) {
          continue;
        }
        String imgString = null;
        String cf_path = CF_FILE_SAVE_PATH;

        if ("certNoValidDate".equals(ok)) {
          String certNoValidDate = ov;
          nameType.put("certNoValidDate", certNoValidDate);
        }

        if ("doorPhoto[]".equals(ok)) {
          String[] door = (String[]) map.get("doorPhoto[]");
          String[] doorSrc = (String[]) map.get("doorSrc[]");

          Boolean flag = false;
          String doorPath = "";
          String doorflag = "";
          if (null != doorSrc && doorSrc.length > 0) {
            // 修改
            nameType.put("doorNum", String.valueOf(door.length));
            for (int i = 0; i < door.length; i++) {
              String[] door_ = door[i].split("=");
              if ("无".equals(door_[1])) {
                if (i == door.length - 1) {
                  doorPath += doorSrc[i].split("=")[1];
                } else {
                  doorPath += doorSrc[i].split("=")[1] + ";";
                }

              } else {
                String ov_ = door_[1];
                filename = "doorPhotoAlert" + i + GenSN.getMerchantPictureNo() + ov_.split(",")[0];
                cf_path = CF_FILE_SAVE_PATH + File.separator
                    + Constant.CERTIFY_TYPE_MERCHANT_DOORID + File.separator + custId;
                if (i == door.length - 1) {
                  doorPath += cf_path + File.separator + filename;
                } else {
                  doorPath += cf_path + File.separator + filename + ";";
                }

                imgString = ov_.split(",")[1];

                File saveFile = new File(cf_path);
                if (!saveFile.exists()) {
                  saveFile.mkdirs();
                }
                doorflag = "true";
                flag = GenerateImage(imgString, filename, cf_path);
              }

            }
            nameType.put("doorFlag", doorflag);
            nameType.put("doorPhoto", doorPath);
          } else {
            nameType.put("doorNum", String.valueOf(door.length));
            for (int i = 0; i < door.length; i++) {
              String ov_ = door[i];
              filename = "doorPhoto" + i + GenSN.getMerchantPictureNo() + ov_.split(",")[0];
              cf_path = CF_FILE_SAVE_PATH + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_DOORID + File.separator + custId;
              nameType.put("doorPhoto" + i, filename);
              imgString = ov_.split(",")[1];

              File saveFile = new File(cf_path);
              if (!saveFile.exists()) {
                saveFile.mkdirs();
              }
              // delImagFile(cf_path + File.separator + filename);
              flag = GenerateImage(imgString, filename, cf_path);
            }
          }

        } else {
          switch (ok) {
            case "businessPhoto":
              // 营业执照
              filename = "attach1" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS + File.separator
                  + custId;
              nameType.put("businessType", filename);
              imgString = ov.split(",")[1];
              break;
            case "certAttribute0":
              filename = "attach1" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path =
                  cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN + File.separator + custId;
              nameType.put("certAttributeType1", filename);
              imgString = ov.split(",")[1];
              break;
            case "certAttribute1":
              filename = "attach1" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                  + File.separator + custId;
              nameType.put("idCardType_1", filename);
              imgString = ov.split(",")[1];
              break;
            case "certAttribute2":
              filename = "attach2" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                  + File.separator + custId;
              nameType.put("idCardType_2", filename);
              imgString = ov.split(",")[1];
              break;
            case "openAccount":// 开户许可证
              filename = "openAccount" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_ACCTID
                  + File.separator + custId;
              nameType.put("openAccount", filename);
              imgString = ov.split(",")[1];
              break;
            case "bankCardPhoto":// 银行卡照片
              filename = "bankCardPhoto" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD
                  + File.separator + custId;
              nameType.put("bankCardPhoto", filename);
              imgString = ov.split(",")[1];
              break;
            case "doorPhoto":// 门头照
              filename = "doorPhoto" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
                  + File.separator + custId;
              nameType.put("doorPhoto", filename);
              imgString = ov.split(",")[1];
              break;
            case "netWorkPhoto":// 网络文化经营许可证
              filename = "netWorkPhoto" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID
                  + File.separator + custId;
              nameType.put("netWorkPhoto", filename);
              imgString = ov.split(",")[1];
              break;
            case "settlEmentCard":// 结算银行卡照片
              filename = "settlEmentCard" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SETTLEMENT
                  + File.separator + custId;
              nameType.put("settlEmentCard", filename);
              imgString = ov.split(",")[1];
              break;
            case "shopInterior":// 店内照
              filename = "shopInterior" + GenSN.getMerchantPictureNo() + ov.split(",")[0];
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SHOPINTERIOR
                  + File.separator + custId;
              nameType.put("shopInterior", filename);
              imgString = ov.split(",")[1];
              break;
			case "qualification"://行业资质图片
				filename ="qualification"+GenSN.getMerchantPictureNo()+ov.split(",")[0];
				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_QUALIFICATION + File.separator + custId;
				nameType.put("qualification", filename);
				imgString = ov.split(",")[1];
				break;
			case "signature"://电子签名照
				filename ="signature"+GenSN.getMerchantPictureNo()+ov.split(",")[0];
				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SIGNATURE + File.separator + custId;
				nameType.put("signature", filename);
				imgString = ov.split(",")[1];
				break;
			case "bankCardBackPhoto"://银行卡反面照
				filename ="bankCardBackPhoto"+GenSN.getMerchantPictureNo()+ov.split(",")[0];
				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARDBACK + File.separator + custId;
				nameType.put("bankCardBackPhoto", filename);
				imgString = ov.split(",")[1];
				break;
			case "settleIdCard"://手持身份证
				filename ="settleIdCard"+GenSN.getMerchantPictureNo()+ov.split(",")[0];
				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_HANDIDCARD + File.separator + custId;
				nameType.put("settleIdCard", filename);
				imgString = ov.split(",")[1];
				break;
            default:
              continue;
          }
          File saveFile = new File(cf_path);
          if (!saveFile.exists()) {
            saveFile.mkdirs();
          }
          // delImagFile(cf_path + File.separator + filename);
          boolean flag = GenerateImage(imgString, filename, cf_path);
        }



      }

      result.put("result", "SUCCESS");
      result.put("message", "上传成功");
      result.putAll(nameType);

    } catch (Exception e) {
      logger.error("上传失败", e);
      result.put("result", "fail");
      result.put("message", e.getMessage());
    }

    return result;

  }

  // 将base64字符文件输出图像
  public static boolean GenerateImage(String imgStr, String fileName, String path)
      throws IOException {
    // 对字节数组字符串进行Base64解码并生成图片
    if (imgStr == null) // 图像数据为空
      return false;

    OutputStream out = null;
    try {
      // Base64解码
      byte[] b = base64.decode(imgStr);
      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {// 调整异常数据
          b[i] += 256;
        }
      }
      // 生成jpeg图片
      String imgFilePath = path + File.separator + fileName;// 新生成的图片
      out = new FileOutputStream(imgFilePath);
      out.write(b);
      out.flush();
      out.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  /**
   * 文件上传
   * 
   * @param request
   */
  public Map<String, String> fileUpload(HttpServletRequest request, String custId) {

    logger.info("文件上传开始");
    Map<String, String> result = new HashMap<String, String>();
    try {
      // 使用Apache文件上传组件处理文件上传步骤：
      // 创建一个DiskFileItemFactory工厂
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // 创建一个文件上传解析器
      ServletFileUpload upload = new ServletFileUpload(factory);
      // 解决上传文件名的中文乱码
      upload.setHeaderEncoding("UTF-8");
      List<FileItem> list = upload.parseRequest(request);
      InputStream in = null;
      HashMap<String, String> nameType = new HashMap<String, String>();
      for (FileItem item : list) {
        String filename = null;
        if (!item.isFormField()) {
          filename = item.getName();
          String filedName = item.getFieldName();
          if (StringUtils.isEmpty(filename)) {
            continue;
          }

          String type = filename.substring(filename.lastIndexOf("."));
          String[] photoTypes = {".jpg", ".jpeg", ".gif", ".bmp", ".png"};

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

          in = item.getInputStream();

          String cf_path = CF_FILE_SAVE_PATH;

          switch (filedName) {
            case "businessPhoto":
              filename = "attach1" + type;
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS + File.separator
                  + custId;
              nameType.put("businessType", filename);
              break;
            case "certAttribute0":
              filename = "attach1" + type;
              cf_path =
                  cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN + File.separator + custId;
              nameType.put("certAttributeType1", filename);
              break;
            case "certAttribute1":
              filename = "attach1" + type;
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                  + File.separator + custId;
              nameType.put("idCardType_1", filename);
              break;
            case "certAttribute2":
              filename = "attach2" + type;
              cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
                  + File.separator + custId;
              nameType.put("idCardType_2", filename);
              break;
            case "openAccount":// 开户许可证
                filename = "openAccount" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_ACCTID
                    + File.separator + custId;
                nameType.put("openAccount", filename);
                break;
              case "bankCardPhoto":// 银行卡照片
                filename = "bankCardPhoto" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD
                    + File.separator + custId;
                nameType.put("bankCardPhoto", filename);
                break;
              case "doorPhoto":// 门头照
                filename = "doorPhoto" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
                    + File.separator + custId;
                nameType.put("doorPhoto", filename);
                break;
              case "netWorkPhoto":// 网络文化经营许可证
                filename = "netWorkPhoto" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID
                    + File.separator + custId;
                nameType.put("netWorkPhoto", filename);
                break;
              case "settlEmentCard":// 结算银行卡照片
                filename = "settlEmentCard" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SETTLEMENT
                    + File.separator + custId;
                nameType.put("settlEmentCard", filename);
                break;
              case "shopInterior":// 店内照
                filename = "shopInterior" + type;
                cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SHOPINTERIOR
                    + File.separator + custId;
                nameType.put("shopInterior", filename);
                break;
  			case "qualification"://行业资质图片
  				filename ="qualification" + type;
  				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_QUALIFICATION + File.separator + custId;
  				nameType.put("qualification", filename);
  				break;
  			case "signature"://电子签名照
  				filename ="signature" + type;
  				cf_path = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_SIGNATURE + File.separator + custId;
  				nameType.put("signature", filename);
  				break;
            default:
              break;
          }

          File saveFile = new File(cf_path);
          if (!saveFile.exists()) {
            saveFile.mkdirs();
          }
          delImagFile(cf_path + File.separator + filename);
          FileOutputStream out = new FileOutputStream(cf_path + File.separator + filename);
          byte buffer[] = new byte[1024];
          int len = 0;
          while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
          }
          out.flush();
          in.close();
          out.close();
          item.delete();
        }

      }
      result.put("result", "SUCCESS");
      result.put("message", "上传成功");
      result.putAll(nameType);
      logger.info("文件上传成功");

    } catch (Exception e) {
      logger.error("上传失败", e);
      result.put("result", "fail");
      result.put("message", e.getMessage());
    }

    return result;
  }

  public MerchantVo findMerchantInfo(String custId) {
	MerchantVo merchantVo  = merchantMapper.findMerchantInfo(custId);
	return merchantVo;

  }

  /** 根据id查找微商户信息 */
  public MerchantVo findTinyMerchantInfo(String custId) {
    return merchantMapper.findTinyMerchantInfo(custId);
  }

  /**
   * 营业执照注册号验证
   * 
   * @param businessLicense
   * @return
   */
  public Merchant validateLicense(String businessLicense,String custId) {
    if (StringUtils.isEmpty(businessLicense)) {
      throw new IllegalArgumentException("营业执照注册号为空");
    }
    return merchantMapper.validateLicense(businessLicense, custId);
  }

  /**
   * 组织机构代码验证
   * 
   * @param orgInstCode
   * @return
   */
  public Merchant validateOrgInstCode(String orgInstCode, String custId) {
    if (StringUtils.isEmpty(orgInstCode)) {
      throw new IllegalArgumentException("组织机构代码为空");
    }
    return merchantMapper.validateOrgInstCode(orgInstCode, custId);
  }

  public void saveRegist(String email, String custId, Merchant merchant, String paths) {

    this.saveLoginMerchant(email, custId, merchant);
    // ruleService.saveFee(custId, feeCode);
    this.saveMerchant(merchant);
    this.saveCertificateAuth(custId);
  }


  public void addMerchant(Merchant merchant, String paths) {
    this.saveLoginMerchant2(merchant);
    this.saveCustBelongInfo(merchant);
    this.insertStore(merchant);
    // ruleService.saveFee(custId, feeCode);
    this.saveMerchant2(merchant);
    this.saveCertificateAuth(merchant.getCustId());
  }
  private void saveCustBelongInfo(Merchant merchant) {
	  if(StringUtils.isEmpty(merchant.getCustId())) {
			throw new IllegalArgumentException("客户号为空");
		}
	  TdCustBelongInfo tdCustBelongInfo = new TdCustBelongInfo();
	  tdCustBelongInfo.setCustId(merchant.getCustId());
	  tdCustBelongInfo.setStatus("1");
	  Date date = new Date(); 
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  tdCustBelongInfo.setCreateTime(sdf.format(date));
	  if(StringUtils.isNotBlank(merchant.getCustManager())) {
		  tdCustBelongInfo.setManagerId(merchant.getCustManager());
		  merchantMapper.saveCustBelongInfo(tdCustBelongInfo);
	  }
	  if(StringUtils.isNotBlank(merchant.getAgentName())) {
		  tdCustBelongInfo.setManagerId(null);
		  tdCustBelongInfo.setServicePartnerId(merchant.getAgentName()); 
		  merchantMapper.saveCustBelongInfo(tdCustBelongInfo);
	  }
	  
	  
	  
	  
  }

/** 微商户注册，带事务操作 */

  public void saveTinyMerchantRegist(String email, String custId, Merchant merchant,
      Map<String, String> custScanMap) {
    this.insertStore(merchant); /** 插入门店信息 */
    this.saveLoginTinyMerchant(email, custId, merchant); /** 保存微商户登录信息 */
    this.saveTinyMerchant(merchant); /** 保存微商户信息(客户信息表) */
    this.saveTinyCertificateAuth(merchant); /** 保存微商户审核结果 */
    this.insertTinyMerchantCustScan(custId, custScanMap, merchant); /** 插入客户扫描件审核表 */

  }

  /** 保存审核结果 */
  private void saveTinyCertificateAuth(Merchant merchant) {
    logger.info("保存微商户审核结果[{}]", JSONObject.toJSONString(merchant));
    try {
      /** 保存审核结果 */
      CertificateAuth certificateAuth = new CertificateAuth();
      certificateAuth.setCustId(merchant.getCustId()); /** 客户编号 */
      certificateAuth.setCertificateState(Constant.AUDIT_PASS); /** 审核通过 */
      certificateAuth.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId())); /** 创建人 */ // 创建人
      custScanMapper.saveTinyCertificateAuth(certificateAuth);
      logger.info("保存审核结果成功");
      /** 商户表绑定审核ID */
      TdCustInfo idCustInfo = new TdCustInfo(); /** 客户管理 */
      idCustInfo.setCustId(merchant.getCustId()); /** 客户编号 */
      idCustInfo.setAuthId(String.valueOf(certificateAuth.getAuthId())); /** 证件审核ID */
      tdCustInfoMapper.updateInfo(idCustInfo); /** 更新商户表AuthID */
      logger.info("商户表绑定审核ID成功");
    } catch (Exception e) {
      logger.error("保存审核结果异常", e.getMessage());
      throw e;
    }
  }

  // 启动工作流，并执行到下一步
  public void startProcess(String custId, String taskId, String auditName) {

    logger.info("执行工作流程，转到下一步");

    try {
      if (StringUtils.isEmpty(taskId)) {
        String businessKey = Merchant.class.getSimpleName() + "." + custId;

        Map<String, Object> registerVar = new LinkedHashMap<String, Object>();
        registerVar.put("merchantId", String.valueOf(WebUtils.getUserInfo().getUserId()));

        identityService.setAuthenticatedUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        ProcessInstance processInstance =
            this.startProcess(Constant.MERCHANTAUDIT_KEY, businessKey, registerVar);
        String processInstanceId = processInstance.getProcessInstanceId();
        Task task = this.findTask(processInstanceId);

        Map<String, Object> auditVar = new LinkedHashMap<String, Object>();
        auditVar.put("auditId", auditName);
        this.endTask(task.getId(), auditVar);
      } else {
        Map<String, Object> auditVar = new LinkedHashMap<String, Object>();
        auditVar.put("auditId", auditName);
        this.endTask(taskId, auditVar);
      }
      logger.info("工作流执行到下一步");
    } catch (Exception e) {

      logger.error("工作流执行异常", e);
    }
  }

  /**
   * 证件审批信息
   * 
   * @param custId
   * @return
   */
  public String saveCertificateAuth(String custId) {
    /** 创建人 **/
    String createId = String.valueOf(WebUtils.getUserInfo().getUserId());
    /** 证件审批信息 **/
    CertificateAuth insertAuthBean = new CertificateAuth();
    insertAuthBean.setCustId(custId);
    insertAuthBean.setCertificateState(Constant.AUDIT_WAIT);
    insertAuthBean.setCreateId(createId);

    int resultAuthId = custScanMapper.insertCertificateAuth(insertAuthBean);
    if (resultAuthId != 1) {
      throw new IllegalArgumentException("保存证件信息异常");
    }
    // 用户信息绑定审核ID
    TdCustInfo info = new TdCustInfo();
    info.setCustId(custId);
    info.setAuthId(String.valueOf(insertAuthBean.getAuthId()));
    tdCustInfoMapper.updateInfo(info);

    return String.valueOf(insertAuthBean.getAuthId());
  }

  public void insertCustScan(String custId, Map<String, String> fileNames) {

    if (StringUtils.isEmpty(custId)) {
      throw new IllegalArgumentException("商户ID为空！");
    }
    TdCustInfo info = tdCustInfoMapper.selectById(custId);
    /** 创建人 **/
    String createId = String.valueOf(WebUtils.getUserInfo().getUserId());
    String cf_path = CF_FILE_SAVE_PATH;
    String startTime = "";
    String endTime = "";
    // 获取身份证有效期
    String ValidDate = fileNames.get("certNoValidDate");

    if (!("".equals(ValidDate)) && null != ValidDate) {
      ValidDate = ValidDate.replace(".", "");
      startTime = ValidDate.split("-")[0];
      endTime = ValidDate.split("-")[1];

    }

    try {
      /** 营业执照 **/
      CustScan businessLicenseBean = new CustScan();
      businessLicenseBean.setCustId(custId);
      businessLicenseBean.setAuthId(info.getAuthId());
      businessLicenseBean.setCertifyType(Constant.CERTIFY_TYPE_BUSINESS);
      businessLicenseBean.setScanCopyPath(cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS
          + File.separator + custId + File.separator + fileNames.get("businessType"));
      businessLicenseBean.setCustName(info.getCustName());
      businessLicenseBean.setCreateId(createId);
      businessLicenseBean.setCertifyNo(info.getBusinessLicense());
      custScanMapper.insertCustScan(businessLicenseBean);
      /** 开户银行 **/
      CustScan bankAccountBean = new CustScan();
      bankAccountBean.setCustId(custId);
      bankAccountBean.setAuthId(info.getAuthId());
      bankAccountBean.setCertifyType(Constant.CERTIFY_TYPE_OPEN);
      bankAccountBean.setScanCopyPath(cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN
          + File.separator + custId + File.separator + fileNames.get("certAttributeType1"));
      bankAccountBean.setCustName(info.getCustName());
      bankAccountBean.setCreateId(createId);
      bankAccountBean.setCertifyNo(info.getCompMainAcct());
      custScanMapper.insertCustScan(bankAccountBean);

      /** 银行卡照 **/
      CustScan bankCardBean = new CustScan();
      bankCardBean.setCustId(custId);
      bankCardBean.setAuthId(info.getAuthId());
      bankCardBean.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_BANKCARD);
      bankCardBean.setScanCopyPath(cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD
              + File.separator + custId + File.separator + fileNames.get("bankCardPhoto"));
      bankCardBean.setCustName(info.getCustName());
      bankCardBean.setCreateId(createId);
      bankCardBean.setCertifyNo(info.getCompMainAcct());
      custScanMapper.insertCustScan(bankCardBean);


      /** 身份证 **/
      CustScan certifyCardBean = new CustScan();
      certifyCardBean.setCustId(custId);
      certifyCardBean.setAuthId(info.getAuthId());
      certifyCardBean
          .setScanCopyPath(cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
              + File.separator + custId + File.separator + fileNames.get("idCardType_1") + ";"
              + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD + File.separator
              + custId + File.separator + fileNames.get("idCardType_2"));
      certifyCardBean.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_IDCARD);
      certifyCardBean.setCustName(info.getCustName());
      certifyCardBean.setCreateId(createId);
      certifyCardBean.setCertifyNo(info.getRepresentativeCertNo());
      certifyCardBean.setCertifyBeginTime(startTime);
      certifyCardBean.setCertifyEndTime(endTime);
      custScanMapper.insertCustScan(certifyCardBean);

      /** 门头照 **/
      String path = "";
      String doorNum = fileNames.get("doorNum");
      if (null != doorNum && !("".equals(doorNum))) {
        int j = Integer.parseInt(doorNum);
        for (int i = 0; i < j - 1; i++) {
          path = path + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
              + File.separator + custId + File.separator + fileNames.get("doorPhoto" + i) + ";";
        }
        path = path + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
            + File.separator + custId + File.separator + fileNames.get("doorPhoto" + (j - 1));
      }
      CustScan doorPhoto = new CustScan();
      doorPhoto.setCustId(custId); // 客户编号
      doorPhoto.setAuthId(info.getAuthId()); // 证件审核id
      doorPhoto.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_DOORID); // 证件类型，03-开户许可证
      doorPhoto.setScanCopyPath(path);// 扫描件文件路径，多个用逗号分隔
      doorPhoto.setCustName(info.getCustName()); // 客户名称
      doorPhoto.setCreateId(createId); // 创建人
      // doorPhoto.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
      custScanMapper.insertCustScan(doorPhoto);

      /**/ /* 网络文化经营许可证 **/
      CustScan netWorkPhoto = new CustScan();
      netWorkPhoto.setCustId(custId); // 客户编号
      netWorkPhoto.setAuthId(info.getAuthId()); // 证件审核id
      netWorkPhoto.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_NETWORKID); // 证件类型，09-网络文化经营许可证
      netWorkPhoto.setScanCopyPath(// 扫描件文件路径，多个用逗号分隔
          /** /data/nfsshare/upload/certificate/02/custId/? **/
          cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID + File.separator
              + custId + File.separator + fileNames.get("netWorkPhoto"));
      netWorkPhoto.setCustName(info.getCustName()); // 客户名称
      netWorkPhoto.setCreateId(createId); // 创建人
      // doorPhoto.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
      custScanMapper.insertCustScan(netWorkPhoto);

    } catch (Exception e) {
      logger.error("商户证件信息保存异常", e);
    }

  }

  /** 插入微商户 */
  public void insertTinyMerchantCustScan(String custId, Map<String, String> fileNames,
      Merchant merchant) {
    if (StringUtils.isBlank(custId)) {
      throw new IllegalArgumentException("商户ID为空！");
    }

    String startTime = "";
    String endTime = "";
    // 获取身份证有效期
    String ValidDate = merchant.getCertNoValidDate();

    if (!("".equals(ValidDate)) && null != ValidDate) {
      ValidDate = ValidDate.replace(".", "");
      startTime = ValidDate.split("-")[0];
      endTime = ValidDate.split("-")[1];
    }

    /** TD_CUST_INFO **/
    TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(custId);
    /** 创建人 **/
    String createId = String.valueOf(WebUtils.getUserInfo().getUserId());
    String cf_path = CF_FILE_SAVE_PATH;
    try {
      /******************* 分两次存DB *******************/
      /** 营业执照 **/
      String str = fileNames.get("businessType");
      if (str != null && str.length() >= 0) {
        CustScan businessLicenseBean = new CustScan();
        businessLicenseBean.setCustId(custId); // 客户编号
        businessLicenseBean.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
        businessLicenseBean.setCertifyType(Constant.CERTIFY_TYPE_BUSINESS); // 证件类型，02-营业执照
        businessLicenseBean.setScanCopyPath(// 扫描件文件路径，多个用逗号分隔
            /** /data/nfsshare/upload/certificate/02/custId/? **/
            cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS + File.separator + custId
                + File.separator + str);
        businessLicenseBean.setCustName(tdCustInfo.getCustName()); // 客户名称
        businessLicenseBean.setCreateId(createId); // 创建人
        businessLicenseBean.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
        custScanMapper.insertCustScan(businessLicenseBean);
      }
      /** 身份证 **/
      CustScan certifyCardBean = new CustScan();
      certifyCardBean.setCustId(custId); // 客户编号
      certifyCardBean.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
      certifyCardBean.setScanCopyPath( // 扫描件文件路径，多个用逗号分隔
          /**
           * /data/nfsshare/upload/certificate/04/custId/?;/data/nfsshare/upload/certificate/04/custId/?
           **/
          cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD + File.separator + custId
              + File.separator + fileNames.get("idCardType_1") + ";" //
              + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD + File.separator
              + custId + File.separator + fileNames.get("idCardType_2"));
      certifyCardBean.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_IDCARD); // 证件类型，04-微商身份证
      certifyCardBean.setCustName(tdCustInfo.getCustName()); // 客户名称
      certifyCardBean.setCreateId(createId); // 创建人
      certifyCardBean.setCertifyNo(tdCustInfo.getCertifyNo()); // 证件号码
      certifyCardBean.setCertifyBeginTime(startTime);
      certifyCardBean.setCertifyEndTime(endTime);
      custScanMapper.insertCustScan(certifyCardBean);

      // 开户许可证
      String str1 = fileNames.get("openAccount");
      if (str1 != null && str1.length() >= 0) {
        CustScan openAccount = new CustScan();
        openAccount.setCustId(custId); // 客户编号
        openAccount.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
        openAccount.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_ACCTID); // 证件类型，03-开户许可证
        openAccount.setScanCopyPath(// 扫描件文件路径，多个用逗号分隔
            /** /data/nfsshare/upload/certificate/02/custId/? **/
            cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_ACCTID + File.separator
                + custId + File.separator + str1);
        openAccount.setCustName(tdCustInfo.getCustName()); // 客户名称
        openAccount.setCreateId(createId); // 创建人
        // openAccount.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
        custScanMapper.insertCustScan(openAccount);
      }

      // 手持银行卡照片
      String str2 = fileNames.get("bankCardPhoto");
      if (str2 != null && str2.length() >= 0) {
        CustScan bankCardPhoto = new CustScan();
        bankCardPhoto.setCustId(custId); // 客户编号
        bankCardPhoto.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
        bankCardPhoto.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_BANKCARD); // 证件类型，03-开户许可证
        bankCardPhoto.setScanCopyPath(// 扫描件文件路径，多个用逗号分隔
            /** /data/nfsshare/upload/certificate/02/custId/? **/
            cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD + File.separator
                + custId + File.separator + str2);
        bankCardPhoto.setCustName(tdCustInfo.getCustName()); // 客户名称
        bankCardPhoto.setCreateId(createId); // 创建人
        // businessLicenseBean.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
        custScanMapper.insertCustScan(bankCardPhoto);
      }



      String path = "";
      String doorNum = fileNames.get("doorNum");
      if (null != doorNum && !("".equals(doorNum))) {
        int j = Integer.parseInt(doorNum);
        for (int i = 0; i < j - 1; i++) {
          path = path + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
              + File.separator + custId + File.separator + fileNames.get("doorPhoto" + i) + ";";
        }
        path = path + cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
            + File.separator + custId + File.separator + fileNames.get("doorPhoto" + (j - 1));
      }

      // 门头照
      if (path != null && path.length() >= 0) {
        CustScan doorPhoto = new CustScan();
        doorPhoto.setCustId(custId); // 客户编号
        doorPhoto.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
        doorPhoto.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_DOORID); // 证件类型，03-开户许可证
        doorPhoto.setScanCopyPath(path);
        doorPhoto.setCustName(tdCustInfo.getCustName()); // 客户名称
        doorPhoto.setCreateId(createId); // 创建人
        // doorPhoto.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
        custScanMapper.insertCustScan(doorPhoto);
      }


      // 网络营业许可证
      String str4 = fileNames.get("netWorkPhoto");
      if (str4 != null && str4.length() >= 0) {
        CustScan netWorkPhoto = new CustScan();
        netWorkPhoto.setCustId(custId); // 客户编号
        netWorkPhoto.setAuthId(tdCustInfo.getAuthId()); // 证件审核id
        netWorkPhoto.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_NETWORKID); // 证件类型，09
        netWorkPhoto.setScanCopyPath(// 扫描件文件路径，多个用逗号分隔
            /** /data/nfsshare/upload/certificate/02/custId/? **/
            cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID + File.separator
                + custId + File.separator + str4);
        netWorkPhoto.setCustName(tdCustInfo.getCustName()); // 客户名称
        netWorkPhoto.setCreateId(createId); // 创建人
        // doorPhoto.setCertifyNo(tdCustInfo.getBusinessLicense()); // 证件号码
        custScanMapper.insertCustScan(netWorkPhoto);
      }

      /** 银行卡扫描件 */
      // CustScan bankCardBean = new CustScan();
      // bankCardBean.setCustId(custId);
      // bankCardBean.setAuthId(tdCustInfo.getAuthId());
      // bankCardBean.setScanCopyPath(//
      // cf_path + File.separator + Constant.CERTIFY_TYPE_BANK_CARD + File.separator + custId +
      // File.separator + fileNames.get("bankCard"));
      // bankCardBean.setCertifyType(Constant.CERTIFY_TYPE_BANK_CARD); // 证件类型，05银行卡扫描件
      // bankCardBean.setCustName(tdCustInfo.getCustName()); // 客户名称
      // bankCardBean.setCreateId(createId); // 创建人
      // bankCardBean.setCertifyNo(tdCustInfo.getCompMainAcct()); // 银行卡号
      // custScanMapper.insertCustScan(bankCardBean);
      /** 其他证件扫描件 */
      // String otherPapersType = fileNames.get("otherPapers");
      // if (StringUtils.isNotBlank(otherPapersType)) {
      // CustScan otherPapersBean = new CustScan();
      // otherPapersBean.setCustId(custId);
      // otherPapersBean.setAuthId(tdCustInfo.getAuthId());
      // otherPapersBean.setScanCopyPath(//
      // cf_path + File.separator + Constant.CERTIFY_TYPE_OTHER_PAPERS + File.separator + custId +
      // File.separator + fileNames.get("otherPapers"));
      // otherPapersBean.setCertifyType(Constant.CERTIFY_TYPE_OTHER_PAPERS); // 证件类型，06其他扫描件
      // otherPapersBean.setCustName(tdCustInfo.getCustName());
      // otherPapersBean.setCreateId(createId);
      // custScanMapper.insertCustScan(otherPapersBean);
      // }
    } catch (Exception e) {
      logger.error("商户证件信息保存异常", e.getMessage());
      throw e;
    }
  }

  public void updateMerchant(MerchantVo merchantVo) {
    if (null == merchantVo) {
      throw new IllegalArgumentException("商户对象为空");
    }

    try {
      if (null != merchantVo.getCustName()) {
        merchantMapper.updateAcctNameByCustName(merchantVo);
      }
      if (StringUtils.isEmpty(merchantVo.getMerchantCode())) {
        merchantVo.setMerchantCode("M" + merchantVo.getBusinessLicense());
      }

      merchantMapper.updateMerchantLoginInfo(merchantVo);
      merchantMapper.updateMerchant(merchantVo);
    } catch (Exception e) {
      logger.error("修改异常", e);
      throw e;
    }

  }

  public void updateMerchantEnter(MerchantVo merchantVo) {
    if (null == merchantVo) {
      throw new IllegalArgumentException("商户对象为空");
    }

    try {
      MerchantVo merchantInfo = merchantMapper.findMerchantInfo(merchantVo.getCustId());
      if (null != merchantVo.getCustName()) {
        merchantMapper.updateAcctNameByCustName(merchantVo);
      }
      if (StringUtils.isEmpty(merchantVo.getMerchantCode())) {
        merchantVo.setMerchantCode("M" + merchantVo.getBusinessLicense());
      }

      merchantMapper.updateMerchantLoginInfo(merchantVo);
//      merchantMapper.updateByPrimaryKeySelective(merchantVo);
      merchantVo.setMerchantCode(null);
      merchantMapper.updateMerchant(merchantVo);
      
      TdCertificateAuth tdCertificateAuth = new TdCertificateAuth();
      tdCertificateAuth.setCertificateState("1");
      tdCertificateAuth.setAuthId(Integer.parseInt(merchantInfo.getAuthId()));
      tdCertificateAuth.setCustId(merchantVo.getCustId());
      tdCertificateAuthMapper.updateByPrimaryKeySelective(tdCertificateAuth);

    } catch (Exception e) {
      logger.error("修改异常", e);
      throw e;
    }

  }

  public void updateMerchantAndFeeRule(MerchantVo merchantVo, Map<String, String> filePath) {

    try {
      // 更新商户信息
      updateMerchant(merchantVo);
      // 修改费率
      // FeeRule feeRule = new FeeRule();
      // feeRule.setCustId(merchantVo.getCustId());
      // feeRule.setFeeCode(merchantVo.getFeeCode());
      // FeeRule rule = feeRuleService.selectFeeRule(feeRule);
      // if (rule != null) {
      // feeRuleService.updateFeeRule(feeRule);
      // } else {
      //
      // Rule ruleZ = new Rule();
      // User user = WebUtils.getUserInfo();
      // ruleZ.setMappingId(GenSN.getSN().substring(0, 30));
      // ruleZ.setInstUser(String.valueOf(user.getUserId()));
      // ruleZ.setOperType(Constant.FEE_RATE_WITHDRAW);// 提现
      // ruleZ.setStatus(Constant.FEE_RATE_STATUS_VALID); // 有效
      // ruleZ.setFeeCode(merchantVo.getFeeCode());
      // ruleZ.setCustId(merchantVo.getCustId());
      // ruleMapper.saveFee(ruleZ);
      // }

      workSpaceService.updateCustScanInfo(merchantVo.getCustId(), merchantVo, filePath);

    } catch (Exception e) {
      throw e;
    }
  }

  
  public void updateMerchantEnterAndFeeRule(MerchantVo merchantVo, Map<String, String> filePath) {

    try {
      // 更新商户信息
      //updateMerchant(merchantVo);
      updateMerchantEnter(merchantVo);
      workSpaceService.updateCustScanInfo(merchantVo.getCustId(), merchantVo, filePath);
      workSpaceService.updateEnterCustScanInfo(merchantVo.getCustId(), merchantVo, filePath);

    } catch (Exception e) {
      //throw e;
    }
  }

  /** 修改微商户信息，带事务 **/
  
  public void updateTinyMerchantInfo(MerchantVo merchantVo, Map<String, String> filePath) {
    if (null == merchantVo) {
      throw new IllegalArgumentException("微商户对象为空");
    }
    try {
      // if (!merchantVo.getMerchantCode().contains("M")) {
      // merchantVo.setMerchantCode("M" + merchantVo.getCertifyNo());
      // }
      /** 修改微商户登录状态 **/
      merchantMapper.updateMerchantLoginInfo(merchantVo);
      /** 修改微商户信息 **/
      merchantMapper.updateMerchant(merchantVo);

      /**
       * 修改微商手机号码
       */
      merchantMapper.updateLoginInfo(merchantVo);

      /** 修改客户扫描件审核表 **/
      updateCustScanInfo(merchantVo.getCustId(), merchantVo, filePath);
      /** 修改核心账户表 */
      AcctSevenBuss acctSevenBuss = new AcctSevenBuss();
      acctSevenBuss.setCustId(merchantVo.getCustId());
      acctSevenBuss.setAcctName(merchantVo.getCustName());
      acctSevenBussMapper.updateAcctSevenBussByCustId(acctSevenBuss);
      /** 删除商户在redis中的缓存 */
      this.delKey(merchantVo.getMerchantCode(), RedisUtil.MERCHANT_DB);
      this.delKey("cust" + merchantVo.getMerchantCode(), RedisUtil.MERCHANT_DB);
    } catch (Exception e) {
      logger.error("修改异常", e);
      throw e;
    }
  }

  public void delKey(String key, int db) {
    Jedis jedis = RedisUtil.getJedis();
    try {
      jedis.select(db);
      /*
       * Set<String> str = jedis.keys(key+"_cashier_ref");
       * System.out.println(key+"_cashier_ref-------------str:"+str);
       */
      jedis.del(key + "_cashier_ref"); // 删除key
      /*
       * Set<String> strs = jedis.keys(key+"_cashier_ref");
       * System.out.println(key+"_cashier_ref-------------str:"+"strs:"+strs);
       */
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      RedisUtil.returnResource(jedis);
    }
  }

  /** 修改客户扫描件审核表 **/
  
  private void updateCustScanInfo(String custId, MerchantVo merchant,
      Map<String, String> fileNames) {
    if (StringUtils.isEmpty(custId)) {
      throw new IllegalArgumentException("商户ID为空");
    }
    logger.info("修改商户证件信息[{}]", JSONObject.toJSONString(merchant));
    String businessType = fileNames.get("businessType");
    String idCardType_1 = fileNames.get("idCardType_1");
    String idCardType_2 = fileNames.get("idCardType_2");
    String doorPhotoPath = fileNames.get("doorPhotoPath");
    String netWorkPhoto = fileNames.get("netWorkPhoto");
    String openAccount = fileNames.get("openAccount");
    String bankCardPhoto = fileNames.get("bankCardPhoto");
    String cf_path = CF_FILE_SAVE_PATH;
    try {
      /** 更新营业执照扫描件 **/
      /** 由于营业执照注册号和营业执照扫描件不是必须提交的，所以这里做一次查询，判断 **/
      CustScan custScan = null;
      custScan = new CustScan();
      custScan.setCertifyType(Constant.CERTIFY_TYPE_BUSINESS);
      custScan.setCustId(merchant.getCustId());
      int count = custScanMapper.findCountCustScanInfo(custScan);
      if (count > 0) {
        if (!StringUtils.isBlank(businessType)) {
          this.updateScanPath(//
              custId, Constant.CERTIFY_TYPE_BUSINESS, //
              // /data/nfsshare/upload/certificate/02/custId/businessType
              cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS + File.separator + custId
                  + File.separator + businessType, //
              merchant.getCustName(), merchant.getBusinessLicense());
        }
      } else {
        if (!StringUtils.isBlank(businessType)) {
          this.insertScanPath(custId, Constant.CERTIFY_TYPE_BUSINESS, //
              cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS + File.separator + custId
                  + File.separator + businessType, //
              merchant.getCustName(), merchant.getBusinessLicense());
        }
      }
      /** 更新身份证扫描件 **/
      custScan = new CustScan();
      custScan.setCustId(custId);
      custScan.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_IDCARD); // 扫描件类型 04-商户身份信息
      String idcardPath = custScanMapper.findPathByIdAndType(custScan); // 根据客户编号和扫描件类型查找
      if (idcardPath != null) {
        String path[] = idcardPath.split(";");
        String idCard_1 = path[0];
        if (!StringUtils.isBlank(idCardType_1)) {
          // /data/nfsshare/upload/certificate/04/custId/idCard_1
          idCard_1 = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
              + File.separator + custId + File.separator + idCardType_1;
        }
        String idCard_2 = "";
        if (path.length >= 2) {
          idCard_2 = path[1];
        }
        if (!StringUtils.isBlank(idCardType_2)) {
          // cf_path/04/custId/idCardType_2
          idCard_2 = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD
              + File.separator + custId + File.separator + idCardType_2;
        }
        idcardPath = idCard_1 + ";" + idCard_2; // 拼接身份证号图片路径
      }
      this.updateScanPath(custId, Constant.CERTIFY_TYPE_MERCHANT_IDCARD, idcardPath,
          merchant.getCustName(), merchant.getCertifyNo());

      /** 更新门头照扫描件 */
      custScan = new CustScan();
      custScan.setCustId(custId);
      String[] split = doorPhotoPath.split(";");
      custScan.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_DOORID);
      String doorPhoto = custScanMapper.findPathByIdAndType(custScan);
      if (null != doorPhoto) {
        if (null != split && split.length > 0) {
          String path[] = doorPhoto.split(";");
          doorPhoto = "";

          for (int j = 0; j < path.length; j++) {
            for (int i = 0; i < split.length; i++) {
              String dataBaseNum = getDoorPhotoNum(split[i]);// 把split[i]的序号取出来;
              String doorName = getDoorPhotoNum(path[j]);
              String doorPath = split[i].toString();
              if (dataBaseNum != null && dataBaseNum.equals(doorName)) {
                path[i] = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
                    + File.separator + custId + File.separator + doorPath;
              } else {
                path[i] = cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_DOORID
                    + File.separator + custId + File.separator + doorPath;
              }
            }
            doorPhoto += path[j] + ";";
          }
        }

      }

      if (!StringUtils.isBlank(doorPhotoPath)) {
        this.updateScanPath(custId, Constant.CERTIFY_TYPE_MERCHANT_DOORID, doorPhoto, //
            merchant.getCustName(), null);

      }

      /** 更新手持银行照扫描件 */
      custScan = new CustScan();
      custScan.setCustId(custId);
      custScan.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_BANKCARD);// 07
      if (!StringUtils.isBlank(bankCardPhoto)) {
        this.updateScanPath(//
            // String custId, String certifyType, String path, String custName, String certifyNo
            // cf_path + File.separator + Constant.CERTIFY_TYPE_BANK_CARD + File.separator + custId
            // + File.separator + fileNames.get("bankCard"));
            custId, Constant.CERTIFY_TYPE_MERCHANT_BANKCARD, //
            cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD + File.separator
                + custId + File.separator + bankCardPhoto, //
            merchant.getCustName(), null);
      }
      /** 更新开户行照扫描件 */
      custScan = new CustScan();
      custScan.setCustId(custId);
      custScan.setCertifyType(Constant.CERTIFY_TYPE_OPEN);// 03
      if (!StringUtils.isBlank(openAccount)) {
        this.updateScanPath(//
            // String custId, String certifyType, String path, String custName, String certifyNo
            // cf_path + File.separator + Constant.CERTIFY_TYPE_BANK_CARD + File.separator + custId
            // + File.separator + fileNames.get("bankCard"));
            custId, Constant.CERTIFY_TYPE_OPEN, //
            cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN + File.separator + custId
                + File.separator + openAccount, //
            merchant.getCustName(), null);
      }
      // 网络文化经营许可证
      custScan = new CustScan();
      custScan.setCustId(custId);
      custScan.setCertifyType(Constant.CERTIFY_TYPE_MERCHANT_NETWORKID);
      int ret = custScanMapper.findCountCustScanInfo(custScan);
      if (ret > 0) {
        if (!StringUtils.isBlank(netWorkPhoto)) {
          this.updateScanPath(custId, Constant.CERTIFY_TYPE_MERCHANT_NETWORKID, //
              cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID + File.separator
                  + custId + File.separator + netWorkPhoto, //
              merchant.getCustName(), null);
        }
      }


    } catch (Exception e) {
      logger.error("更新证件信息异常", e);
      throw e;
    }
  }

  /** 修改扫描件信息 **/
  private void updateScanPath(String custId, String certifyType, String path, String custName,
      String certifyNo) {
    CustScan custScan = new CustScan();
    custScan.setCustId(custId); // 客户编号
    custScan.setCertifyType(certifyType); // 扫描件类型
    custScan.setScanCopyPath(path); // 扫描件路径
    custScan.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId())); // 修改人
    custScan.setCertifyNo(certifyNo); // 身份证号号码/证件号码
    custScan.setCustName(custName); // 客户名称
    custScanMapper.updateCustScan(custScan);
  }

  /** 插入证件/身份证号路径 **/
  private void insertScanPath(String custId, String certifyType, String path, String custName,
      String certifyNo) {
    /** TD_CUST_INFO **/
    TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(custId);
    CustScan custScan = new CustScan();
    custScan.setCustId(custId); // 客户编号
    custScan.setAuthId(tdCustInfo.getAuthId()); // 证件审核ID
    custScan.setCertifyType(certifyType); // 扫描件类型
    custScan.setScanCopyPath(path); // 扫描件路径
    custScan.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId())); // 修改人
    custScan.setCertifyNo(certifyNo); // 身份证号码
    custScan.setCustName(custName); // 客户名称
    custScanMapper.insertCustScan(custScan);
  }

  // 删除服务器图片
  public void delImagFile(String path) {
    File imgFile = new File(path);
    if (imgFile.exists()) {
      imgFile.delete();
    }
  }

  /**
   * 启动流程
   */
  public ProcessInstance startProcess(String processKey, String businessKey,
      Map<String, Object> var) {
    return runtimeService.startProcessInstanceByKey(processKey, businessKey, var);
  }

  /**
   * 根据流程实例ID查找任务
   * 
   * @param processInstanceId
   * @return
   */
  public Task findTask(String processInstanceId) {
    return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
  }

  /**
   * 完成任务
   * 
   * @param taskId
   * @param var
   */
  public void endTask(String taskId, Map<String, Object> var) {
    taskService.complete(taskId, var);
  }

  /**
   * 查找商户协议
   * 
   * @param merchantCode
   * @return
   */
  public List<BmsProtocolContent> selectContentByCustId(String custId) {
    return bmsProtocolContentMapper.selectContentByCustId(custId);
  }

  public List<MerchantExport> backExportMerchantInfo(MerchantVo merchantVo) {
    return merchantMapper.backExportlist(merchantVo);
  }

  public TdLoginUserInfo selectLoginUserInfo(String custId) {
    return tdLoginUserInfoMapper.selectLoginUserInfo(custId);
  }

  /*******************************************
   * 微商户相关
   *************************************************/
  /** 微商户列表查询 **/
  public List<MerchantVo> selectTinyMerchants(MerchantVo merchantVo) {
    return dao.tinyMerchantsList(merchantVo);
  }

  /** 自己权限微商户列表查询 **/
  public List<MerchantVo> tinyMyMerchantsList(MerchantVo merchantVo) {
    return dao.tinyMyMerchantsList(merchantVo);
  }

  /** 微商户查找图片路径 */
  public String findTinyMerchantImagePathById(String custId, String certifyType) {
    CustScan custScan = new CustScan();
    custScan.setCustId(custId);
    custScan.setCertifyType(certifyType);
    return dao.findPath(custScan);
  }



  /** 微商户文件上传 **/
  public Map<String, String> tinyMerchantFileUpload(HttpServletRequest request, String custId) {
    logger.info("*********微商户文件上传开始*********");
    Map<String, String> result = new HashMap<String, String>();
    try {
      DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建一个DiskFileItemFactory工厂
      ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个文件上传解析器
      upload.setHeaderEncoding("UTF-8"); // 解决上传文件名的中文乱码
      List<FileItem> list = upload.parseRequest(request);
      InputStream inputStream = null;
      
      HashMap<String, String> nameType = new HashMap<String, String>();
      for (FileItem item : list) {
        String filename = null; //
        if (!item.isFormField()) { // 是否是表单项
          filename = item.getName();
          String filedName = item.getFieldName();
          if (StringUtils.isEmpty(filename)) {
            continue;
          }
          /** 验证图片后缀名 **/
          String type = filename.substring(filename.lastIndexOf("."));
          String[] photoTypes = {".jpg", ".jpeg", ".gif", ".bmp", ".png"};
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
          String fileUploadPath = CF_FILE_SAVE_PATH; // 服务器上传路径
          String name = filedName.substring(filedName.length() - 1);
          /** 根据文件名生成路径规则 **/
          switch (filedName) {
            case "businessPhoto":
              filename = "attach1" + GenSN.getMerchantPictureNo() + type;
              /** /data/nfsshare/upload/certificate/02/custId **/
              fileUploadPath = fileUploadPath + File.separator + Constant.CERTIFY_TYPE_BUSINESS
                  + File.separator + custId;
              nameType.put("businessType", filename);
              break;
            case "certAttribute1":
              filename = "attach1" + GenSN.getMerchantPictureNo() + type;
              /** /data/nfsshare/upload/certificate/04/custId **/
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_IDCARD + File.separator + custId;
              nameType.put("idCardType_1", filename);
              break;
            case "certAttribute2":
              filename = "attach2" + GenSN.getMerchantPictureNo() + type;
              /** /data/nfsshare/upload/certificate/04/custId **/
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_IDCARD + File.separator + custId;
              nameType.put("idCardType_2", filename);
              break;

            case "openAccount":// 开户许可证
              filename = "openAccount" + GenSN.getMerchantPictureNo() + type;
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_ACCTID + File.separator + custId;
              nameType.put("openAccount", filename);
              break;
            case "bankCardPhoto":// 银行卡照片
              filename = "bankCardPhoto" + GenSN.getMerchantPictureNo() + type;
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_BANKCARD + File.separator + custId;
              nameType.put("bankCardPhoto", filename);
              break;
            case "doorPhoto":// 门头照
              filename = "doorPhoto" + GenSN.getMerchantPictureNo() + type;
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_DOORID + File.separator + custId;
              nameType.put("doorPhoto", filename);
              break;
            case "netWorkPhoto":// 网络文化经营许可证
              filename = "doorPhoto" + GenSN.getMerchantPictureNo() + type;
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_NETWORKID + File.separator + custId;
              nameType.put("netWorkPhoto", filename);
              break;
            case "shopInterior":// 店内照
              filename = "shopInterior" + GenSN.getMerchantPictureNo() + type;
              fileUploadPath = fileUploadPath + File.separator
                  + Constant.CERTIFY_TYPE_MERCHANT_SHOPINTERIOR + File.separator + custId;
              nameType.put("shopInterior", filename);
              break;
            default:
              if (filedName.indexOf("doorPhoto") != -1) {
                filename = filedName + GenSN.getMerchantPictureNo() + type;
                /** /data/nfsshare/upload/certificate/08/custId **/
                fileUploadPath = fileUploadPath + File.separator
                    + Constant.CERTIFY_TYPE_MERCHANT_DOORID + File.separator + custId;
                // nameType.put(filedName, fileUploadPath + File.separator + filename);
                nameType.put("doorPhoto" + name, filename);
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

  /** 验证营业执照注册号是否被注册 **/
  public int validateBusinessLicense(String businessLicense, String roleId) {
    if (StringUtils.isEmpty(businessLicense)) {
      throw new IllegalArgumentException("营业执照注册号为空");
    }
    return merchantMapper.validateBusinessLicense(businessLicense, roleId);
  }

  /** 验证手机号是否被注册 **/
  public int validateMobile(String mobile) {
    if (StringUtils.isEmpty(mobile)) {
      throw new IllegalArgumentException("手机号为空");
    }
    return merchantMapper.validateMobile(mobile);
  }

  /**
   * 添加默认门店
   * 
   * @param registerInfo
   * @return
   */
  private void insertStore(Merchant merchant) {
    StoreManage shop = new StoreManage();
    shop.setAddr(merchant.getCustAdd());
    shop.setContact(merchant.getRepresentativeMobile());
    shop.setMchId(merchant.getCustId());
    shop.setShopId(merchant.getCustId());
    shop.setShopName(merchant.getCustName());
    shop.setShopNo(merchant.getMerchantCode());
    shop.setStatus("1");
    shop.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    storeManageMapper.insert(shop);
  }

  public List<MerchantVo> selectMyAuditMerchants(MerchantVo merchantVo) {
    // TODO Auto-generated method stub
    return merchantMapper.myAuditList(merchantVo);
  }

  private String getDoorPhotoNum(String path) {
    Pattern pattern = Pattern.compile("(doorPhoto)([0-9])");
    Matcher matcher = pattern.matcher(path);
    while (matcher.find()) {
      return matcher.group(2);
    }
    return null;
  }

  public List<MerchantVo> selectNewMyAuditMerchants(MerchantVo merchantVo) {
    return dao.newMerchantList(merchantVo);
  }


  /**
   * 新版导出商户信息
   * 
   * @param merchantVo
   * @return
   */
  public List<MerchantExport> newExportMerchantInfo(MerchantVo merchantVo) {
    return merchantMapper.newExportlist(merchantVo);
  }

    public String findAreaNameByAreaId(String country) {
        return merchantMapper.findAreaNameByAreaId(country);
    }

	public MerchantVo getMerchantInfo(TdMerchantDetailInfo detailInfo) {
		return merchantMapper.getMerchantInfo(detailInfo);
	}

	public TdMerchantDetailInfo findMerchantDetailInfo(TdMerchantDetailInfo detailInfo) {
		return merchantMapper.findMerchantDetailInfo(detailInfo);
	}
}
