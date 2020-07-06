package com.qifenqian.bms.basemanager.merchant.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.dao.MerchantDao;
import com.qifenqian.bms.basemanager.merchant.mapper.BmsProtocolContentMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantEnterMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.StoreManageMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.GenSN;
import com.qifenqian.bms.platform.web.myWorkSpace.service.WorkSpaceService;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.merchant.dao.MerchantListDao;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

import cn.jpush.api.utils.StringUtils;

/**
 * 商户注册服务类v2
 */
@Service
public class MerchantEnterService {
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
    private MerchantEnterMapper merchantEnterMapper;
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
    private MerchantListDao merchantListDao;

    ExecutorService smsExecutors = Executors.newFixedThreadPool(10);
    private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);

    /**
     * 注册通过后的商户信息v2
     *
     * @param merchantVo
     * @return
     */
    public List<MerchantVo> selectAuditMerchants(MerchantVo merchantVo) {
        List<MerchantVo> list = dao.auditList2(merchantVo);
        return list;
    }
    
    /**
     * 导出商户信息
     * 
     * @param merchantVo
     * @return
     */
    public List<MerchantExport> proExportMerchantInfo(MerchantVo merchantVo) {
      return merchantEnterMapper.newExportlist(merchantVo);
    }
    /**
     * 校验商户账号是否存在
     * @param merchantAccount
     * @param roleId
     * @return
     */
    public TdLoginUserInfo validateMerchantAccount(String merchantAccount, String roleId) {
        TdLoginUserInfo tdLoginUserInfo = null;
        if(merchantAccount.contains("@")){
            //账号为邮箱
            tdLoginUserInfo = tdLoginUserInfoMapper.selectByEmail(merchantAccount, null, roleId);
        }else {
            tdLoginUserInfo = tdLoginUserInfoMapper.selectByPhone(merchantAccount,roleId);
        }
        return tdLoginUserInfo;
    }

    /**
     * 审核结果
     * @param result 审核结果，pass通过   noPass不通过
     * @param merchantId 商户编号
     * @param AuditInfo	审核信息
     * @return
     */
    public String updateResultOfAudit(String result, String merchantId, TdAuditRecodeInfo AuditInfo) {
        String applyStatus = null;
        String custStatus = null;
        String userStatus = null;
        String[] tos = null;
        String content = null;
        String subject = null;
        String pwd = GenSN.getRandomNum(6);
        String attachStr = GenSN.getRandomNum(5);
        final IPlugin plugin = commonService.getIPlugin();
        final MessageBean messageBean = new MessageBean();



        //获取custId
        List<MerchantRegisterInfo> list = queryMerchantInfo(merchantId);
        MerchantRegisterInfo merchantInfo = null;
        merchantInfo = list.get(0);
        String custId = merchantInfo.getCustId();
        //获取登陆表信息
        com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo userInfo = merchantListDao.getUserInfoByCustId(custId);
        String userEmail = userInfo.getEmail();
        String userMobile = userInfo.getMobile();

        //获取商户信息
        TdCustInfo custInfo = merchantListDao.getCustInfoByCustId(custId);
        String custName = custInfo.getCustName();
        //审核不通过
        if("noPass".equals(result)) {
            applyStatus = "99";
            custStatus = "04";
            userStatus = "07";
            if(userEmail==null||("").equals(userEmail)) {
                //审核不通过，手机短信通知
                tos = new String[] { userMobile };
                messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
                messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
                messageBean.setContent("【七分钱支付】您的商户注册未能通过审核，原因是：" + AuditInfo.getAuditInfo() +"请前往商户网站修改商户信息，如有任何问题，请拨打400-633-0707。");

            }else {
                //审核不通过，邮件通知
                tos = new String[] {userEmail};
                content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                        + "<div style=\"margin-bottom:10px;\">"
                        + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                        + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                        + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
                        + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
                        + "审核不通过，具体原因为:"+AuditInfo.getAuditInfo()+"您可以通过 "
                        + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                        + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                        + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                        + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
                messageBean.setContent(content);
                messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
                messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
            }
            //审核通过
        }else if("pass".equals(result)) {
            applyStatus = "00";
            custStatus = "00";
            userStatus = "00";
            //密码加密
            String loginPwd_02 = MD5Security.getMD5String(custId + pwd + attachStr);
            userInfo.setLoginPwd(loginPwd_02);
            userInfo.setAttachStr(attachStr);
            //将密码以及attachStr字段写入登录表中
            merchantListDao.updateLoginUserInfo(userInfo);
            if(userEmail==null||("").equals(userEmail)) {
                //审核通过，手机短信通知
                tos = new String[] { userMobile };
                messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
                messageBean.setSubject("【七分钱支付】商户审核已通过");// 标题
                messageBean.setContent("【七分钱支付】您的商户注册已通过审核，初始密码为"+pwd+"请您尽快登录系统修改初始密码，如有任何问题，请拨打400-633-0707。");


            }else {
                tos = new String[] { userEmail };
                content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                        + "<div style=\"margin-bottom:10px;\">"
                        + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                        + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                        + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
                        + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
                        + "已经审核通过，初始密码为"+pwd+",您可以通过 "
                        + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                        + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                        + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                        + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
                messageBean.setContent(content);
                messageBean.setSubject("七分钱--亲爱的" + custInfo.getCustName() + "，你的七分钱商户账号已经审核通过，欢迎登录！");// 标题
                messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
            }
        }
        String applyId = merchantListDao.getApplyIdByMerchantId(merchantId);
        if(StringUtils.isNotEmpty(applyId)) {
            //服务商内新增的商户，会在TD_MERCHANT_APPLY_INFO中有数据，因此状态要更新到APPLY表中
            merchantListDao.updateStatusForAuditResult(applyStatus, custStatus, userStatus, merchantId);
        }else {
            merchantListDao.updateStatusForAuditResultOld(custStatus, userStatus, merchantId);
        }
        //先删除已有审核信息，避免读取列表时信息重复
        merchantListDao.deleteAuditInfo(AuditInfo);
        merchantListDao.insertAuditInfo(AuditInfo);
        messageBean.setTos(tos);
        messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);


        smsExecutors.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("发送邮件");
                if (messageBean.getMsgType().equals(MessageColumnValues.MsgType.SMS)) {
                    plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS
                } else {
                    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); // 邮件EMAIL
                }
            }
        });

        return "success";
    }

    /**
     * 查询商户信息
     * @param merchantCode 商户编号
     * @return
     */
    public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode){
        return merchantListDao.queryMerchantInfo(merchantCode);
    }

	//获取图片路径
	public PicturePath getPicPath(MerchantVo merchantVo) {
		CustScan custScan = new CustScan();
		custScan.setCustId(merchantVo.getCustId());
		custScan.setAuthId(merchantVo.getAuthId());
		custScan.setStatus("00");
		PicturePath picturePath = new PicturePath();
		//营业执照
		custScan.setCertifyType("02");
		String bussinessPath = findPicturePath(custScan);
		picturePath.setBussinessPath("/pic/" + bussinessPath);
		//身份证正面照
		custScan.setCertifyType("04");
		String idCardOPath= findPicturePath(custScan);
		picturePath.setIdCardOPath("/pic/" + idCardOPath);
		//身份证反面照
		custScan.setCertifyType("16");
		String idCardFPath= findPicturePath(custScan);
		picturePath.setIdCardFPath("/pic/" + idCardFPath);
		//开户许可证
		custScan.setCertifyType("03");
		String openAccountPath = findPicturePath(custScan);
		picturePath.setOpenAccountPath("/pic/" + openAccountPath);
		//银行卡照
		custScan.setCertifyType("07");
		String bankCardPath = findPicturePath(custScan);
		picturePath.setBankCardPath("/pic/" + bankCardPath);
		//门头照
		custScan.setCertifyType("08");
		String doorPhotoPath = findPicturePath(custScan);
		picturePath.setDoorPhotoPath("/pic/" + doorPhotoPath);
		//结算人身份证正面
		custScan.setCertifyType("30");
		String settleCertAttribute1Path = findPicturePath(custScan);
		picturePath.setSettleCertAttribute1Path("/pic/" + settleCertAttribute1Path);		
		//结算人身份证反面
		custScan.setCertifyType("31");
		String settleCertAttribute2Path = findPicturePath(custScan);
		picturePath.setSettleCertAttribute2Path("/pic/" + settleCertAttribute2Path);
		//银行卡反面照
		custScan.setCertifyType("14");
		String bankCardBackPath = findPicturePath(custScan);
		picturePath.setBankCardBackPath("/pic/" + bankCardBackPath);
		//手持身份证照
		custScan.setCertifyType("13");
		String handIdCardPath = findPicturePath(custScan);
		picturePath.setHandIdCardPath("/pic/" + handIdCardPath);
		//店内照
		custScan.setCertifyType("18");
		String shopInteriorPath = findPicturePath(custScan);
		picturePath.setShopInteriorPath("/pic/" + shopInteriorPath);
		//行业资质照
		custScan.setCertifyType("11");
		String qualificationPath = findPicturePath(custScan);
		picturePath.setQualificationPath("/pic/" + qualificationPath);
		//电子签名照
		custScan.setCertifyType("12");
		String signaturePath = findPicturePath(custScan);
		picturePath.setSignaturePath("/pic/" + signaturePath);
		//公众号页面截图
		custScan.setCertifyType("32");
		String mpAppScreenShotsPath = findPicturePath(custScan);
		picturePath.setMpAppScreenShotsPath("/pic/" + mpAppScreenShotsPath);
		//小程序页面截图
		custScan.setCertifyType("33");
		String miniprogramAppidPath = findPicturePath(custScan);
		picturePath.setMiniprogramAppidPath("/pic/" + miniprogramAppidPath);
		//APP截图
		custScan.setCertifyType("34");
		String appAppidPath = findPicturePath(custScan);
		picturePath.setAppAppidPath("/pic/" + appAppidPath);
		//网站授权函
		custScan.setCertifyType("35");
		String webUrlPath = findPicturePath(custScan);
		picturePath.setWebUrlPath("/pic/" + webUrlPath);
		//23其他资料照1
		custScan.setCertifyType("23");
		String otherMaterialPath = findPicturePath(custScan);
		picturePath.setOtherMaterialPath("/pic/" + otherMaterialPath);
		//15 非法人结算授权函
		custScan.setCertifyType("15");
		String letterOfAuthPath = findPicturePath(custScan);
		picturePath.setLetterOfAuthPath("/pic/" + letterOfAuthPath);
		//37经营场所证明文件
		custScan.setCertifyType("37");
		String businessPlacePath = findPicturePath(custScan);
		picturePath.setBusinessPlacePath("/pic/" + businessPlacePath);
		//36 微信联系人信息确认二维码
		custScan.setCertifyType("36");
		String infoQrcodePath = findPicturePath(custScan);
		picturePath.setInfoQrcodePath("/pic/" + infoQrcodePath);
		//21 店内收银台照
		custScan.setCertifyType("21");
		String shopCheckStandPath = findPicturePath(custScan);
		picturePath.setShopCheckStandPath("/pic/" + shopCheckStandPath);
		return picturePath;		
	}

	//获取对应类型图片路径
	private String findPicturePath(CustScan custScan) {
		String tdCustScanCopy = merchantMapper.findPath(custScan);
		return tdCustScanCopy;
		
	}
}
