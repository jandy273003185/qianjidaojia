package com.qifenqian.bms.basemanager.merchantwithdraw.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawChild;
import com.qifenqian.bms.accounting.withdraw.mapper.WithdrawChildMapper;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdrawExcel;
import com.qifenqian.bms.basemanager.merchantwithdraw.dao.MerchantWithdrawDao;
import com.qifenqian.bms.basemanager.merchantwithdraw.mapper.MerchantWithdrawMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.CurrCode;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.TransType;
import com.sevenpay.invoke.transaction.trade.TradeRequest;
import com.sevenpay.invoke.transaction.trade.TradeResponse;
import com.sevenpay.invoke.transaction.trade.bean.TransBean;
import com.sevenpay.invoke.transaction.withdrawapplybuss.WithdrawApplyBussRequest;
import com.sevenpay.invoke.transaction.withdrawapplybuss.WithdrawApplyBussResponse;

@Service
public class MerchantWithdrawService {

	private static Logger logger = LoggerFactory.getLogger(MerchantWithdrawService.class);
	@Autowired
	private MerchantWithdrawDao merchantWithdrawDao;

	@Autowired
	private MerchantWithdrawMapper merchantWithdrawMapper;

	@Autowired
	private WithdrawChildMapper withdrawChildMapper;

	/***
	 * 商户提现列表
	 * 
	 * @param withdrawBean
	 * @return
	 */
	public List<MerchantWithdraw> selectMerchantWithdrawList(MerchantWithdraw withdrawBean) {
		return merchantWithdrawDao.selectMerchantWithdrawList(withdrawBean);
	}

	/**
	 * 导出商户提现列表
	 * 
	 * @param withdrawBean
	 * @return
	 */
	public List<MerchantWithdrawExcel> selectWithdrawExcelByMerchant(MerchantWithdraw withdrawBean) {
		return merchantWithdrawMapper.selectWithdrawExcelByMerchant(withdrawBean);
	}

	/**
	 * 商户提现核销
	 * 
	 * @param withdrawSn
	 */
	public void merchantWithdrawVerification(MerchantWithdraw merchantWithdraw) {
		merchantWithdraw.setVerificationUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		merchantWithdrawMapper.merchantWithdrawVerification(merchantWithdraw);

	}

	/**
	 * 提现审核通过
	 * 
	 * @param withdraw
	 * @throws Exception
	 */
	public JSONObject merchantWithdrawAuditPass(MerchantWithdraw withdraw) throws Exception {

		JSONObject json = new JSONObject();
		User user = WebUtils.getUserInfo();

		String snId = BusinessUtils.getMsgId();
		withdraw.setModifyId(String.valueOf(user.getUserId()));
		withdraw.setWithdrawState("02");
		withdraw.setAuditState(Constant.WITHDRAW_AUDIT_SUCCESS);
		merchantWithdrawMapper.merchantWithdrawAudit(withdraw);

		/** 记录商户提现申请信息和流水信息 **/
		WithdrawChild child = new WithdrawChild();
		child.setWithdrawSn(withdraw.getWithdrawSn());
		child.setWithdrawReqserialid(snId);
		child.setCustId(withdraw.getCustId());
		child.setOperType(Constant.OPER_TYPE_WITHDRAW);
		child.setCreateId(String.valueOf(user.getUserId()));
		withdrawChildMapper.saveWithdrawChild(child);

		ResponseMessage<TradeResponse> response = bussWithdrawApply(withdraw, snId,
				RequestColumnValues.MsgType.BUSS_WITHDRAW, RequestColumnValues.AcctType.SEVEN_BUSS);

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用提现接口撤销接口异常：无返回结果");
			withdraw.setWithdrawState("03");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			withdraw.setWithdrawState("05");
			withdraw.setCoreReturnMsg(response.getRtnCode().toString());
			child.setCoreReturnCode(response.getRtnCode().toString());
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用提现异常：" + response.getRtnInfo());
			withdraw.setWithdrawState("03");
			withdraw.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
			child.setCoreReturnCode(response.getRtnCode().toString());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用提现失败：" + response.getRtnInfo());
			return json;
		}

		child.setCoreSn(response.getMsgId());
		child.setCoreReturnMsg(response.getRtnInfo());
		withdrawChildMapper.updateWithdrawChild(child);
		merchantWithdrawMapper.merchantWithdrawAudit(withdraw);
		return json;

	}

	/**
	 * 商户提现申请
	 */
	public ResponseMessage<TradeResponse> bussWithdrawApply(MerchantWithdraw withdraw, String sn,
			RequestColumnValues.MsgType msgtype, RequestColumnValues.AcctType acctType) {
		SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
				.getBean("coreHttpInvokerProxy");
		logger.info("提现流水:" + withdraw.getWithdrawSn() + " 开始调用核心提现功能。");

		ResponseMessage<TradeResponse> rsp = null;
		try {
			RequestMessage<TradeRequest> requestMessage = new RequestMessage<TradeRequest>();
			{
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);

				requestMessage.setReqDatetime(new Date());
				requestMessage.setReqSerialId(sn);
				requestMessage.setMsgType(msgtype);
				requestMessage.setReqMsgNum(1);

				TradeRequest request = new TradeRequest();
				{
					List<TransBean> list = new ArrayList<TransBean>();
					TransBean transBean = new TransBean();
					transBean.setTransType(TransType.SEVEN_TO_BANK_CARD);
					transBean.setPayCustId(withdraw.getCustId());
					transBean.setPayAcctType(acctType);
					transBean.setPayAcctId(merchantWithdrawMapper.getAcctIdByCustId(withdraw.getCustId()));
					transBean.setRcvCustId(withdraw.getCustId());
					transBean.setRcvAcctType(AcctType.BANK_DEBIT);
					transBean.setRcvAcctId(withdraw.getBankAcctNoInternal());
					transBean.setCurrCode(CurrCode.CNY);
					transBean.setTransAmt(withdraw.getWithdrawAmt());
					transBean.setFeePay(new BigDecimal(0));
					transBean.setFeeRcv(new BigDecimal(0));
					transBean.setBrief("七分钱-提现");
					transBean.setOriginMsgId(withdraw.getCoreSn());
					list.add(transBean);
					request.setTransList(list);
				}

				requestMessage.setRequest(request);
			}

			logger.info("核心提现开始发送报文：" + JSONObject.toJSONString(requestMessage, true));
			rsp = sevenpayCoreServiceInterface.trade(requestMessage);
			logger.info("核心提现接收结果报文：" + JSONObject.toJSONString(rsp, true));

		} catch (Exception e) {
			logger.error("核心提现异常", e);
		}
		return rsp;

	}

	/**
	 * 商户提现审核不通过
	 * 
	 * @param withdraw
	 * @throws Exception
	 */
	public JSONObject merchantWithdrawAuditNoPass(MerchantWithdraw withdraw) throws Exception {
		JSONObject json = new JSONObject();
		User user = WebUtils.getUserInfo();
		withdraw.setAuditState(Constant.WITHDRAW_AUDIT_FAIL);
		withdraw.setModifyId(String.valueOf(user.getUserId()));
		withdraw.setWithdrawState("02");
		merchantWithdrawMapper.merchantWithdrawAudit(withdraw);

		/** 撤销 **/
		String snId = BusinessUtils.getMsgId();
		/** 记录核心返回信息和流水信息 **/
		WithdrawChild child = new WithdrawChild();
		child.setWithdrawSn(withdraw.getWithdrawSn());
		child.setWithdrawReqserialid(snId);
		child.setCustId(withdraw.getCustId());

		child.setCreateId(String.valueOf(user.getUserId()));
		child.setOperType(Constant.OPER_TYPE_REVOKE);
		withdrawChildMapper.saveWithdrawChild(child);

		ResponseMessage<WithdrawApplyBussResponse> response = bussWithdrawRevoke(withdraw, snId,
				MsgType.BUSS_WITHDRAW_APPLY);

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用提现接口撤销接口异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			withdraw.setCoreReturnMsg(response.getRtnCode().toString());
			withdraw.setWithdrawState("99");
			child.setCoreReturnCode(response.getRtnCode().toString());
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用提现异常：" + response.getRtnInfo());
			withdraw.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
			withdraw.setWithdrawState("03");
			child.setCoreReturnCode(response.getRtnCode().toString());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用提现失败：" + response.getRtnInfo());
			return json;
		}

		child.setCoreSn(response.getMsgId());
		child.setCoreReturnMsg(response.getRtnInfo());
		withdrawChildMapper.updateWithdrawChild(child);

		// 变更提现申请流水表 提现状态 99... 银行处理中
		merchantWithdrawMapper.merchantWithdrawAudit(withdraw);
		return json;
	}

	/**
	 * 商户提现撤销
	 * 
	 * @return
	 */
	public ResponseMessage<WithdrawApplyBussResponse> bussWithdrawRevoke(MerchantWithdraw withdraw, String snId,
			MsgType type) {
		SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
				.getBean("coreHttpInvokerProxy");
		logger.info("撤销提现：" + withdraw.getWithdrawSn() + "开始调用核心");
		ResponseMessage<WithdrawApplyBussResponse> responseMessage = null;
		try {
			RequestMessage<WithdrawApplyBussRequest> requestMessage = new RequestMessage<WithdrawApplyBussRequest>();
			{
				requestMessage.setMsgType(type);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
				requestMessage.setReqSerialId(snId);
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());

				WithdrawApplyBussRequest request = new WithdrawApplyBussRequest();
				{
					request.setAcctId(merchantWithdrawMapper.getAcctIdByCustId(withdraw.getCustId()));
					request.setCustId(withdraw.getCustId());
					request.setOperateType(RequestColumnValues.WithdrawApplyOperate.REVOKE);
					request.setCurrCode(RequestColumnValues.CurrCode.CNY);
					request.setWithdrawAmt(withdraw.getWithdrawAmt());
					request.setOriginMsgId(withdraw.getCoreSn());
					request.setBrief("提现申请撤销");

				}
				requestMessage.setRequest(request);
			}
			logger.info("发送报文：" + JSONObject.toJSONString(requestMessage, true));
			responseMessage = sevenpayCoreServiceInterface.withdrawApplyBuss(requestMessage);
			logger.info("返回报文：" + JSONObject.toJSONString(responseMessage, true));
		} catch (Exception e) {
			logger.error("核心提现异常", e);
		}

		return responseMessage;
	}

	/**
	 * 修改商户提现流水表核销状态
	 * 
	 * @param id
	 */
	public void updateMerchantWithdraw(List<String> id) {
		merchantWithdrawMapper.updateMerchantWithdraw(id);
	}

}
