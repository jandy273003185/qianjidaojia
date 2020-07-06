package com.qifenqian.bms.basemanager.photo.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.mapper.AcctSevenCustCoreMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.photo.bean.Photo;
import com.qifenqian.bms.basemanager.photo.dao.PhotoDAO;
import com.qifenqian.bms.basemanager.photo.mapper.PhotoMapper;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.editcust.EditAcctSevenCustRequest;
import com.sevenpay.invoke.transaction.editcust.EditAcctSevenCustResponse;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

/**
 * 照片审核服务层
 *
 * @author pc
 */
@Service
public class PhotoService {
  private static Logger logger = LoggerFactory.getLogger(PhotoService.class);
  @Autowired private PhotoDAO photoDAO;

  @Autowired private PhotoMapper photoMapper;

  @Autowired private TdCustInfoMapper custInfoMapper;

  @Autowired private AcctSevenCustCoreMapper acctSevenCustCoreMapper;

  @Autowired private TdCustInfoService tdCustInfoService;

  @Autowired private CommonService commonService;

  /**
   * 证件列表
   *
   * @param photo
   * @return
   */
  public List<CertificateAuth> list(CertificateAuth queryBean) {
    return photoDAO.list(queryBean);
  }

  /**
   * 查看用户证件详情
   *
   * @param authId
   * @return
   */
  public Photo selectCustCertificate(int authId) {
    return photoMapper.selectCustCertificate(authId);
  }

  /**
   * 审核通过
   *
   * @param tdCustScanCopy
   * @param request
   * @return
   */
  
  public String auditYes(CertificateAuth certificate) {
    logger.info("正在通过审核用户编号[{}]", certificate.getCustId());
    TdCustInfo custInfo = new TdCustInfo();
    AcctSevenCust sevenCustInfo = new AcctSevenCust();
    String result = "SUCCESS";
    try {
      // 更新审核数据
      certificate.setCertificateState(Constant.AUDIT_PASS);
      certificate.setAuthResultCode(Constant.AUDIT_RESULT_PASS);
      photoMapper.update(certificate);
      // 更新审核后用户数据
      custInfo.setTrustCertifyAuditState(Constant.CERTIFY_AUDIT_STATE_PASS);
      custInfo.setTrustCertifyLvl(Constant.CERTIFY_LVL_3);
      custInfo.setCustId(certificate.getCustId());
      custInfo.setCustName(certificate.getCustName());
      custInfoMapper.updateInfo(custInfo);

      // 修改核心账户姓名
      sevenCustInfo.setCustId(certificate.getCustId());
      sevenCustInfo.setAcctName(certificate.getCustName());
      acctSevenCustCoreMapper.updateAccountName(sevenCustInfo);

      /*ResponseMessage<EditAcctSevenCustResponse> editAcctSevenCustResponse1 = this.updateCustName(custInfo);
      if(RequestColumnValues.RtnCode.SUCCESS==editAcctSevenCustResponse1.getRtnCode()){
      	result  = "SUCCESS";
      	logger.info("用户名称更新成功");
      }else{
      	result = "FAIL";
      	logger.info("用户名称更新失败");
      }*/
      ResponseMessage<EditAcctSevenCustResponse> editAcctSevenCustResponse =
          this.updateCustLev(certificate.getCustId(), custInfo.getCustName());

      if (RequestColumnValues.RtnCode.SUCCESS == editAcctSevenCustResponse.getRtnCode()) {
        result = "SUCCESS";
        logger.info("用户认证等级更新成功");
      } else {
        result = "FAIL";
        logger.info("用户认证等级更新失败");
      }

    } catch (Exception e) {
      result = "FAIL";
      logger.error("审核异常", e);
    }
    return result;
  }
  /** 修改客户名称 */
  public ResponseMessage<EditAcctSevenCustResponse> updateCustName(TdCustInfo custInfo) {

    logger.info("客户名称等级更新......");
    if (StringUtils.isEmpty(custInfo.getCustId())) {
      throw new IllegalArgumentException("客户ID为空");
    }

    try {
      SevenpayCoreServiceInterface coreinterface =
          SpringUtils.getBean(SevenpayCoreServiceInterface.class);
      TdCustInfo cust = custInfoMapper.selectLoginAndcustInfo(custInfo.getCustId());
      RequestMessage<EditAcctSevenCustRequest> requestMessage =
          new RequestMessage<EditAcctSevenCustRequest>();
      {
        requestMessage.setMsgType(MsgType.ACCT_CUST_EDIT);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(DatetimeUtils.datetime());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());
        EditAcctSevenCustRequest request = new EditAcctSevenCustRequest();
        {
          request.setCustId(cust.getCustId());
          request.setModifyType(RequestColumnValues.ModifyType.INFO_EDIT);
          request.setName(custInfo.getCustName());
          request.setOldMobile(cust.getMobile());
          request.setModifyType(RequestColumnValues.ModifyType.INFO_EDIT);
          request.setIdCode(cust.getCertifyNo());
          request.setMobile(cust.getMobile());
          request.setIdType(RequestColumnValues.IdType.IDENTITY);
        }
        requestMessage.setRequest(request);
        logger.info("==========>>request:" + JSONObject.toJSONString(requestMessage, true));

        ResponseMessage<EditAcctSevenCustResponse> responseMessage =
            coreinterface.editAcctSevenCust(requestMessage);

        logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage, true));
        return responseMessage;
      }
    } catch (Exception e) {
      logger.error("客户信息核心修改异常", e.getMessage());
      throw e;
    }
  }
  /** 修改客户认证级别 */
  public ResponseMessage<EditAcctSevenCustResponse> updateCustLev(String custId, String name) {

    logger.info("客户认证等级更新......");
    if (StringUtils.isEmpty(custId)) {
      throw new IllegalArgumentException("客户ID为空");
    }

    try {
      SevenpayCoreServiceInterface coreinterface =
          SpringUtils.getBean(SevenpayCoreServiceInterface.class);
      TdCustInfo custInfo = custInfoMapper.selectLoginAndcustInfo(custId);
      RequestMessage<EditAcctSevenCustRequest> requestMessage =
          new RequestMessage<EditAcctSevenCustRequest>();
      {
        requestMessage.setMsgType(MsgType.ACCT_CUST_EDIT);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(DatetimeUtils.datetime());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());
        EditAcctSevenCustRequest request = new EditAcctSevenCustRequest();
        {
          request.setCustId(custInfo.getCustId());
          request.setModifyType(RequestColumnValues.ModifyType.INFO_EDIT);
          request.setIdCode(custInfo.getCertifyNo());
          request.setMobile(custInfo.getMobile());
          request.setOldMobile(custInfo.getMobile());
          request.setName(name);
          request.setIdType(RequestColumnValues.IdType.IDENTITY);
          request.setAuthLevel(RequestColumnValues.AcctAuthLevel.L2);
        }
        requestMessage.setRequest(request);
        logger.info("==========>>request:" + JSONObject.toJSONString(requestMessage, true));

        ResponseMessage<EditAcctSevenCustResponse> responseMessage =
            coreinterface.editAcctSevenCust(requestMessage);

        logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage, true));
        return responseMessage;
      }
    } catch (Exception e) {
      logger.error("客户信息核心修改异常", e.getMessage());
      throw e;
    }
  }

  /**
   * 审核未通过
   *
   * @param tdCustScanCopy
   * @param request
   */
  public void auditNo(CertificateAuth certificate) {
    TdCustInfo custInfo = new TdCustInfo();
    try {
      // 更新审核数据
      certificate.setCertificateState(Constant.AUDIT_NOPASS);
      certificate.setAuthResultCode(Constant.AUDIT_RESULT_NOPASS);
      photoMapper.update(certificate);
      // 更新审核后用户数据
      custInfo.setTrustCertifyAuditState(Constant.CERTIFY_AUDIT_STATE_NOPASS);
      custInfo.setCustId(certificate.getCustId());
      custInfoMapper.updateInfo(custInfo);

      /** 发送短信通知 * */
      String content =
          SpringUtils.getBean(DictionaryUtils.class)
              .getDataValueByPath(Constant.CERTIFICATE_AUDIT_MESSAGE)
              .replace("{custName}", certificate.getCustName())
              .replace("{createTime}", certificate.getCreateTime());
      MessageBean messageBean = new MessageBean();
      messageBean.setContent(content);
      messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
      messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
      String[] tos = new String[] {certificate.getMobile()};
      messageBean.setTos(tos);

      IPlugin plugin = commonService.getIPlugin();
      boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
      if (falg) {
        logger.info("发送信息成功！");
      } else {
        logger.error("发送信息失败");
      }
    } catch (Exception e) {
      logger.error("审核异常", e);
    }
  }
}
