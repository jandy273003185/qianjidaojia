package com.qifenqian.bms.v2.biz.merchant.serviceparentermerchant.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.merchant.serviceparenter.dao.ServiceParenterMapper;
import com.qifenqian.bms.merchant.serviceparenter.service.ServiceParenterService;
import com.qifenqian.bms.upgrade.merchant.mapper.MerchantListMapper;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LiBin
 * @Description: 服务商
 * @date 2020/4/15 17:23
 */
@Service
public class BizServiceParenterService extends BaseService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MerchantListMapper merchantListMapper;
    @Autowired
    private TdCustInfoMapper custInfoMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private ServiceParenterMapper serviceParenterMapper;
    @Autowired
    private ServiceParenterService serviceParenterService;
    @Autowired
    private TdCustInfoService tdCustInfoService;
    @Autowired
    private MerchantService merchantService;

    public PageInfo<MerchantVo> findServiceParenterList(MerchantVo merchantVo) {
        List<MerchantVo> merchantVos;
        if (StringUtils.isBlank(merchantVo.getUserId())) {
            merchantVos = serviceParenterMapper.serviceNewList(merchantVo);
        } else {
            merchantVos = serviceParenterMapper.myServicesNewList(merchantVo);
        }
        return new PageInfo<>(merchantVos);
    }

    public MerchantVo newFindMerchantInfo(String custId) {
        return merchantMapper.newFindMerchantInfo(custId);
    }

    public void add(CurrentAccount currentAccount, Merchant merchant) {
        String userId = currentAccount.getLoginId().toString();
        boolean isAddMerchant = tdCustInfoService.isAddMerchant(userId);
        if (!isAddMerchant) {
            throw new BizException("没有新增权限");
        }
        // 设置商户custId
        String merchantCode = null;
        if (merchant.getBusinessLicense() == null || "".equals(merchant.getBusinessLicense())) {
            //表示个人
            merchantCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + GenSN.getRandomNum(3);
        } else {
            merchant.setMerchantCode("P" + GenSN.getRandomNum(18));
//				merchant  Code = BusinessUtils.getMerchantId(merchant.getBusinessLicense());
        }
        if ("".equals(merchant.getMerchantCode()) || null == merchant.getMerchantCode()) {
            merchant.setMerchantCode(GenSN.getRandomNum(19));
        }

        String custId = GenSN.getSN();

        merchant.setCustId(custId);
        // 任务ID
        merchantService.addMerchant(merchant, null);
        // 流程到下一步
        merchantService.startProcess(custId, merchant.getTaskId(), merchant.getAuditName());
    }

    public void update(MerchantVo merchantVo) {
        merchantMapper.updateMerchant(merchantVo);
        //修改完成改成待审核状态
        serviceParenterService.updateState(merchantVo.getCustId(), "update");
    }


    public ResultData audit(Merchant merchant) {
        String custId = merchant.getCustId();
        String fals = merchant.getFals();
        //服务商审核状态
        String updateState = serviceParenterService.updateState(custId, fals);
        if ("SUCCESS".equals(updateState)) {
            //发消息通知
            if ("yes".equals(fals)) {
                sendEews(custId, fals);
            }
            if ("no".equals(fals)) {
                sendEewsNo(custId, fals);
            }
        } else {
            return ResultData.error("审核成功发送信息异常");
        }
        return ResultData.success();
    }

    /**
     * 审核通过后发生消息 zhanggc
     */
    public void sendEews(String custId, String fals) {
        TdLoginUserInfo loginInfo = tdCustInfoService.selectTdLoginInfo(custId);

        String pwd = GenSN.getRandomNum(6);
        String attachStr = GenSN.getRandomNum(5);
        //密码加密
        String loginPwd_02 = MD5Security.getMD5String(custId + pwd + attachStr);
        loginInfo.setLoginPwd(loginPwd_02);
        loginInfo.setAttachStr(attachStr);
        merchantListMapper.updateLoginUserInfo(loginInfo);
        if (!"".equals(loginInfo.getMobile()) && loginInfo.getMobile() != null) {
            //审核通过发短信
            smsMessage(loginInfo, fals, pwd);
            return;
        } else if (!"".equals(loginInfo.getEmail()) && loginInfo.getEmail() != null) {
            //发邮件
            sendEmail(custId, loginInfo, pwd);
            return;
        }

    }

    /**
     * @param custId 审核不通过后发生消息 zhanggc
     * @param fals   审核表示
     */
    public void sendEewsNo(String custId, String fals) {
        TdLoginUserInfo loginInfo = tdCustInfoService.selectTdLoginInfo(custId);

        if (!"".equals(loginInfo.getMobile()) && loginInfo.getMobile() != null) {
            //审核不通过发短信
            smsMessage(loginInfo, fals, null);
            return;
        } else if (!"".equals(loginInfo.getEmail()) && loginInfo.getEmail() != null) {
            //发邮件
            unEPass(custId, loginInfo);
            return;
        }
    }


    /**
     * @param loginInfo
     * @param fals      发送短信
     */
    public void smsMessage(TdLoginUserInfo loginInfo, String fals, String pwd) {
        //发送短信
        final IPlugin plugin = commonService.getIPlugin();
        final MessageBean messageBean = new MessageBean();
        messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
        if ("yes".equals(fals)) {
            messageBean.setContent("【七分钱支付】用户审核已通过,初始密码为<" + pwd + " >,如有任何问题，请拨打400-633-0707。");
        }
        if ("no".equals(fals)) {
            messageBean.setContent("【七分钱支付】用户审核未通过,如有任何问题，请拨打400-633-0707。");
        }
        messageBean.setSubject("【七分钱支付】用户审核通过");// 标题
        String[] tos = new String[]{loginInfo.getMobile()};
        messageBean.setTos(tos);
        messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
        plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS
    }

    /**
     * @param custId    审核通过发生邮件
     * @param loginInfo
     * @return
     */
    private JSONObject sendEmail(String custId, TdLoginUserInfo loginInfo, String pwd) {
        /**
         * 启动流程完成任务
         */
        JSONObject ob = new JSONObject();
        logger.info("启动流程完成任务");
        //查找登录信息表
        TdCustInfo tdCustInfo = custInfoMapper.selectLoginAndcustInfo(custId);
        /**
         * 发送邮件
         */
        MerchantVo merchant = merchantMapper.findMerchantInfo(tdCustInfo.getCustId());

        String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                + "<div style=\"margin-bottom:10px;\">"
                + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
                + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + loginInfo.getEmail() + "</b>"
                + "已经审核通过，可以通过 密码为<" + pwd + ">"
                + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
        String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号已经审核通过，欢迎登录！登录密码为:123456";
        logger.info("{}发送邮件(审核通过)!", tdCustInfo.getCustId());
        boolean flag = false;
        flag = merchantService.sendInfo(loginInfo.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
        if (flag) {
            logger.info("{}审核通过发送邮件成功(审核通过)!", tdCustInfo.getCustId());
        }
        return ob;
    }

    private JSONObject unEPass(String custId, TdLoginUserInfo loginInfo) {
        logger.info("开始商户{}一级审核不通过流程", custId);
        JSONObject ob = new JSONObject();
        TdCustInfo custInfo = custInfoMapper.selectLoginAndcustInfo(custId);
        ob.put("result", "SUCCESS");

        /**
         * 发送邮件
         */
        MerchantVo merchant = merchantMapper.findMerchantInfo(custInfo.getCustId());
        String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                + "<div style=\"margin-bottom:10px;\">"
                + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
                + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + loginInfo.getEmail() + "</b>"
                + "审核不通过，可以通过 "
                + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8md8\"></div>"
                + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
        String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号没有审核通过，请重新提交！";
        logger.info("{}发送邮件()!", custInfo.getCustId());
        boolean flag = merchantService.sendInfo(loginInfo.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
        if (flag) {
            logger.info("{}发送邮件成功()!", custInfo.getCustId());
        }
        return ob;
    }
}
