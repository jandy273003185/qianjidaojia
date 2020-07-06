package com.qifenqian.bms.accounting.withdraw.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.mapper.TransRecordFlowMapper;
import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawChild;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawExcel;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;
import com.qifenqian.bms.accounting.withdraw.dao.WithdrawDAO;
import com.qifenqian.bms.accounting.withdraw.mapper.WithdrawChildMapper;
import com.qifenqian.bms.accounting.withdraw.mapper.WithdrawMapper;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.CurrCode;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.TransType;
import com.sevenpay.invoke.transaction.adjust.AdjustRequest;
import com.sevenpay.invoke.transaction.adjust.AdjustResponse;
import com.sevenpay.invoke.transaction.adjust.bean.AdjustCust;
import com.sevenpay.invoke.transaction.trade.TradeRequest;
import com.sevenpay.invoke.transaction.trade.TradeResponse;
import com.sevenpay.invoke.transaction.trade.bean.TransBean;
import com.sevenpay.invoke.transaction.withdrawapplycust.WithdrawApplyCustRequest;
import com.sevenpay.invoke.transaction.withdrawapplycust.WithdrawApplyCustResponse;

@Service
public class WithdrawService {

	private Logger logger = LoggerFactory.getLogger(WithdrawService.class);

	@Autowired
	private WithdrawMapper withdrawMapper;

	@Autowired
	private WithdrawChildMapper withdrawChildMapper;

	@Autowired
	private TransRecordFlowMapper transRecordFlowMapper;

	@Autowired
	private WithdrawDAO withdrawDAO;

	/**
	 * 消费者提现列表
	 * 
	 * @param withdrawRequestBean
	 * @return
	 */
	public List<Withdraw> selectCustomerWithdrawList(WithdrawRequestBean withdrawRequestBean) {
		return withdrawDAO.selectCustomerWithdrawList(withdrawRequestBean);
	}

	/**
	 * 导出客户提现信息
	 * 
	 * @param withdrawRequestBean
	 * @return
	 */
	public List<WithdrawExcel> selectWithdrawExcelByUser(WithdrawRequestBean withdrawRequestBean) {
		return withdrawMapper.selectWithdrawExcelByUser(withdrawRequestBean);
	}

	/**
	 * 客户提现核销
	 * 
	 * @param withdraw
	 * @return
	 */
	public int withdrawVerification(WithdrawRequestBean withdraw) {

		if (null == withdraw) {
			throw new UnsupportedOperationException("提现核销处理对象不能为空!");
		}
		if (StringUtils.isBlank(withdraw.getWithdrawSn())) {
			throw new UnsupportedOperationException("提现流水号不能为空!");
		}
		withdraw.setWithdrawState(Constant.WITHDRAW_SUCCESS);
		withdraw.setVerificationState(Constant.VERIFICATION_STATE_SUCCESS);
		withdraw.setVerificationUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		return withdrawMapper.withdrawVerification(withdraw);
	}

	/**
	 * 消费者提现通过
	 * 
	 * @param withdraw
	 * @throws Exception
	 */
	public JSONObject withdrawAuditPass(Withdraw withdraw) throws Exception {

		JSONObject json = new JSONObject();
		int currentUser = WebUtils.getUserInfo().getUserId();
		String withdrawReqserialid = BusinessUtils.getMsgId();

		/** 记录提现请求流水信息 **/
		WithdrawChild withdrawChild = new WithdrawChild();
		withdrawChild.setWithdrawSn(withdraw.getWithdrawSn());
		withdrawChild.setWithdrawReqserialid(withdrawReqserialid);
		withdrawChild.setCustId(withdraw.getCustId());
		withdrawChild.setOperType(Constant.OPER_TYPE_WITHDRAW);
		withdrawChild.setCreateId(String.valueOf(currentUser));
		withdrawChildMapper.saveWithdrawChild(withdrawChild);

		ResponseMessage<TradeResponse> response = withdrawApply(withdraw, withdrawReqserialid,
				RequestColumnValues.MsgType.CUST_WITHDRAW, RequestColumnValues.AcctType.SEVEN_CUST);

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用提现接口撤销接口异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			withdraw.setWithdrawState(Constant.WITHDRAW_CORE_SUCCESS);
			withdraw.setAuditState(Constant.WITHDRAW_AUDIT_SUCCESS);
			withdraw.setCoreReturnMsg(response.getRtnCode().toString());
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			withdraw.setWithdrawState(Constant.WITHDRAW_CORE_FAIL);
			json.put("result", "FAIL");
			json.put("message", "调用提现异常：" + response.getRtnInfo());
			withdraw.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用提现失败：" + response.getRtnInfo());
			return json;
		}

		/** 记录核心返回信息和流水信息 **/
		withdrawChild.setCoreSn(response.getMsgId());
		withdrawChild.setCoreReturnMsg(response.getRtnInfo());
		withdrawChild.setCoreReturnCode(response.getRtnCode().toString());
		withdrawChild.setModifyId(String.valueOf(currentUser));
		withdrawChildMapper.updateWithdrawChild(withdrawChild);

		/** 变更提现申请流水表 提现核心处理状态 **/
		withdraw.setModifyId(String.valueOf(currentUser));
		withdrawMapper.custWithdrawAudit(withdraw);
		return json;
	}
	/***
	 * 提现退回
	 * 
	 * @param withdraw
	 * @return
	 */
	public JSONObject withdrawRefund(Withdraw withdraw) {

		JSONObject json = new JSONObject();
		SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
				.getBean("coreHttpInvokerProxy");

		TransRecordFlow transRecordFlow = transRecordFlowMapper.selectRecordFlowByReqId(withdraw.getWithdrawSn());
		if (transRecordFlow == null) {
			throw new IllegalArgumentException("关联操作对象为空");
		}
		
		String withdrawReqserialid =BusinessUtils.getMsgId();
		int currentUser = WebUtils.getUserInfo().getUserId();

		/** 记录提现退回流水信息 **/
		WithdrawChild withdrawChild = new WithdrawChild();
		withdrawChild.setWithdrawSn(withdraw.getWithdrawSn());
		withdrawChild.setWithdrawReqserialid(withdrawReqserialid);
		withdrawChild.setCustId(withdraw.getCustId());
		withdrawChild.setOperType(Constant.OPER_TYPE_REFUND);
		withdrawChild.setCreateId(String.valueOf(currentUser));
		withdrawChildMapper.saveWithdrawChild(withdrawChild);

		ResponseMessage<AdjustResponse> response = null;
		RequestMessage<AdjustRequest<AdjustCust>> requestMessage = new RequestMessage<AdjustRequest<AdjustCust>>();
		{
			requestMessage.setMsgType(MsgType.CUST_ADJUST);
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			requestMessage.setReqSerialId(withdrawReqserialid);
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());

			AdjustRequest<AdjustCust> request = new AdjustRequest<AdjustCust>();
			{
				AdjustCust adjustBean = new AdjustCust();
				adjustBean.setAcctId(transRecordFlow.getAcctId());
				adjustBean.setCurrCode(transRecordFlow.getCurrCode());
				adjustBean.setAdjustAmt(withdraw.getWithdrawAmt());
				adjustBean.setOnwayAmt(new BigDecimal("0.00"));
				adjustBean.setUsableAmt(withdraw.getWithdrawAmt());
				adjustBean.setBrief("提现退回");
				adjustBean.setIsAdjustJGKJ(RequestColumnValues.IsAdjustJGKJ.Y);
				adjustBean.setOriginMsgId(transRecordFlow.getMsgId());
				adjustBean.setRebackAmt(transRecordFlow.getTransAmt());
				adjustBean.setOriginId(transRecordFlow.getId());
				request.setAdjustBean(adjustBean);
			}
			requestMessage.setRequest(request);
		}
		logger.info("客户账户-调用核心调账服务请求[{}]", JSONObject.toJSONString(requestMessage, true));
		response = sevenpayCoreServiceInterface.custAdjust(requestMessage);
		logger.info("客户账户-调用核心调账服务返回[{}]", JSONObject.toJSONString(response, true));

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用调账服务接口异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			withdraw.setWithdrawState(Constant.WITHDRAW_REFUND_CORE_SUCCESS);
			withdraw.setCoreReturnMsg(response.getRtnCode().toString());
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			withdraw.setWithdrawState(Constant.WITHDRAW_REFUND_CORE_FAIL);
			json.put("result", "FAIL");
			json.put("message", "调用调账服务异常：" + response.getRtnInfo());
			withdraw.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用调账服务失败：" + response.getRtnInfo());
			return json;
		}

		/** 记录核心返回信息和流水信息 **/
		withdrawChild.setCoreSn(response.getMsgId());
		withdrawChild.setCoreReturnMsg(response.getRtnInfo());
		withdrawChild.setCoreReturnCode(response.getRtnCode().toString());
		withdrawChild.setModifyId(String.valueOf(currentUser));
		withdrawChildMapper.updateWithdrawChild(withdrawChild);

		/** 变更提现申请流水表 提现核心处理状态 **/
		withdraw.setModifyId(String.valueOf(currentUser));
		withdrawMapper.custWithdrawAudit(withdraw);
		return json;
	}
	/**
	 * 调用核心 七分钱消费请求
	 */
	public ResponseMessage<TradeResponse> withdrawApply(Withdraw withdraw, String withdrawReqserialid,
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
				requestMessage.setReqSerialId(withdrawReqserialid);
				requestMessage.setMsgType(msgtype);
				requestMessage.setReqMsgNum(1);
				TradeRequest request = new TradeRequest();
				{
					List<TransBean> list = new ArrayList<TransBean>();
					TransBean transBean = new TransBean();
					transBean.setTransType(TransType.SEVEN_TO_BANK_CARD);
					transBean.setPayCustId(withdraw.getCustId());
					transBean.setPayAcctType(acctType);
					transBean.setRcvCustId(withdraw.getCustId());
					transBean.setPayAcctId(withdrawMapper.getAcctIdByCustId(withdraw.getCustId()));
					transBean.setRcvAcctType(AcctType.BANK_DEBIT);
					transBean.setRcvAcctId(withdraw.getBnakAcctNoInternal());
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
	 * 消费者提现审核不通过
	 * 
	 * @param id
	 * @throws Exception
	 */
	public JSONObject custWithdrawAuditNoPass(Withdraw withdraw) throws Exception {

		JSONObject json = new JSONObject();
		String withdrawReqserialid = BusinessUtils.getMsgId();
		int currentUser = WebUtils.getUserInfo().getUserId();

		/** 记录提现撤销流水信息 **/
		WithdrawChild withdrawChild = new WithdrawChild();
		withdrawChild.setWithdrawSn(withdraw.getWithdrawSn());
		withdrawChild.setWithdrawReqserialid(withdrawReqserialid);
		withdrawChild.setCustId(withdraw.getCustId());
		withdrawChild.setOperType(Constant.OPER_TYPE_REVOKE);
		withdrawChild.setCreateId(String.valueOf(currentUser));
		withdrawChildMapper.saveWithdrawChild(withdrawChild);

		/** 提现撤销 **/
		ResponseMessage<WithdrawApplyCustResponse> response = withdrawRevoke(withdraw, withdrawReqserialid,
				MsgType.CUST_WITHDRAW_APPLY);

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "提现撤销异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			withdraw.setWithdrawState(Constant.WITHDRAW_REVOKE_CORE_SUCCESS);
			withdraw.setAuditState(Constant.WITHDRAW_AUDIT_FAIL);
			withdraw.setCoreReturnMsg(response.getRtnCode().toString());
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "提现撤销异常：" + response.getRtnInfo());
			withdraw.setWithdrawState(Constant.WITHDRAW_REVOKE_CORE_FAIL);
			withdraw.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
		} else {
			json.put("result", "FAIL");
			json.put("message", "提现撤销失败：" + response.getRtnInfo());
			return json;
		}

		/** 修改核心提现撤销流水信息 **/
		withdrawChild.setCoreSn(response.getMsgId());
		withdrawChild.setCoreReturnMsg(response.getRtnInfo());
		withdrawChild.setCoreReturnCode(response.getRtnCode().toString());
		withdrawChild.setModifyId(String.valueOf(currentUser));
		withdrawChildMapper.updateWithdrawChild(withdrawChild);

		/** 变更提现申请流水表提现撤销核心处理状态 **/
		withdraw.setModifyId(String.valueOf(currentUser));
		withdrawMapper.custWithdrawAudit(withdraw);
		return json;

	}

	/**
	 * 消费者审核不通过 - 撤销提现
	 * 
	 * @return
	 */
	public ResponseMessage<WithdrawApplyCustResponse> withdrawRevoke(Withdraw withdraw, String snId, MsgType type) {
		SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
				.getBean("coreHttpInvokerProxy");
		logger.info("撤销提现：" + withdraw.getWithdrawSn() + "开始调用核心");
		ResponseMessage<WithdrawApplyCustResponse> responseMessage = null;
		try {
			RequestMessage<WithdrawApplyCustRequest> requestMessage = new RequestMessage<WithdrawApplyCustRequest>();
			{
				requestMessage.setMsgType(type);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
				requestMessage.setReqSerialId(snId);
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());

				WithdrawApplyCustRequest request = new WithdrawApplyCustRequest();
				{
					request.setCustId(withdraw.getCustId());
					request.setAcctId(withdrawMapper.getAcctIdByCustId(withdraw.getCustId()));
					request.setOperateType(RequestColumnValues.WithdrawApplyOperate.REVOKE);
					request.setCurrCode(RequestColumnValues.CurrCode.CNY);
					request.setWithdrawAmt(withdraw.getWithdrawAmt());
					request.setOriginMsgId(withdraw.getCoreSn());
					request.setBrief("提现申请撤销");

				}
				requestMessage.setRequest(request);
			}
			logger.info("发送报文：" + JSONObject.toJSONString(requestMessage, true));
			responseMessage = sevenpayCoreServiceInterface.withdrawApplyCust(requestMessage);
			logger.info("返回报文：" + JSONObject.toJSONString(responseMessage, true));
		} catch (Exception e) {
			logger.error("核心提现异常", e);
		}

		return responseMessage;
	}

	/**
	 * 修改用户提现流水表核销状态 -- Subject
	 * 
	 * @param id
	 */
	public void updateCustomerWithdraw(List<String> id) {
		withdrawMapper.updateCustomerWithdraw(id);
	}

	/**
	 * 查询商户提现核销流水 -- Subject
	 * 
	 * @return
	 */
	public List<WithdrawChild> selectMerchantVerification() {
		return withdrawChildMapper.selectMerchantVerification();
	}

	/**
	 * 查询用户提现核销流水 -- Subject
	 * 
	 * @return
	 */
	public List<WithdrawChild> selectCustomerVerification() {
		return withdrawChildMapper.selectCustomerVerification();
	}

}
