package com.qifenqian.bms.basemanager.transferRevoke.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.refund.bean.TradeFlow;
import com.qifenqian.bms.accounting.refund.mapper.TradeFlowMapper;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.transfer.mapper.TransferMapper;
import com.qifenqian.bms.basemanager.transferRevoke.bean.TransferRevoke;
import com.qifenqian.bms.basemanager.transferRevoke.mapper.TransferRevokeMapper;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.revoke.RevokeRequest;
import com.sevenpay.invoke.transaction.revoke.RevokeResponse;

@Service
public class TransferRevokeService {

	private static Logger logger = LoggerFactory.getLogger(TransferRevokeService.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private TradeFlowMapper tradeFlowMapper;

	@Autowired
	private TransferMapper transferMapper;

	@Autowired
	private TransferRevokeMapper transferRevokeMapper;

	public JSONObject transferRevokeApply(TransferRevoke applyBean) {
		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (StringUtils.isBlank(applyBean.getOriginOrderId())) {
			json.put("message", "原交易编号为空");
			return json;
		}
		if (StringUtils.isBlank(applyBean.getRevokeMemo())) {
			json.put("message", "撤销原因为空");
			return json;
		}
		TdTradeBillMainVO tradeVo = transferMapper.selectTransferByOrderId(applyBean.getOriginOrderId());
		if (tradeVo == null) {
			json.put("message", "原交易不存在");
			return json;
		}
		String reqSerialId = BusinessUtils.getMsgId();
		applyBean.setOrderId(reqSerialId);
		applyBean.setPayerCustId(tradeVo.getPayerCustId());
		applyBean.setPayerAcctId(tradeVo.getPayerAcctId());
		applyBean.setPayerCustName(tradeVo.getPayerCustName());
		applyBean.setPayeeCustId(tradeVo.getPayeeCustId());
		applyBean.setPayeeAcctId(tradeVo.getPayeeAcctId());
		applyBean.setPayeeCustName(tradeVo.getPayeeCustName());
		applyBean.setRevokeAmt(tradeVo.getSumAmt());
		applyBean.setCurrCode(RequestColumnValues.CurrCode.CNY);
		applyBean.setOriginTransTime(tradeVo.getOrderCoreReturnTime());
		applyBean.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		applyBean.setOrderStatus(Constant.PAYMENT_REVOKE_INIT);
		applyBean.setAuditState(Constant.WITHDRAW_AUDIT_WAIT);
		transferRevokeMapper.insertTransferRevoke(applyBean);
		json.put("result", "SUCCESS");
		return json;
	}

	public JSONObject transferRevokeAudit(TransferRevoke auditBean) throws ParseException {
		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (StringUtils.isBlank(auditBean.getOriginOrderId())) {
			json.put("message", "原交易编号为空");
			return json;
		}
		if (StringUtils.isBlank(auditBean.getAuditState())) {
			json.put("message", "审核状态为空");
			return json;
		}
		auditBean.setAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));

		if (auditBean.getAuditState().equals(Constant.WITHDRAW_AUDIT_SUCCESS)) {
			auditBean.setOrderStatus(Constant.ACCOUNT_EDIT_CORE_DEAL);
			transferRevokeMapper.updateByAudit(auditBean);
			this.revokeBill(auditBean);
			json.put("result", "SUCCESS");
		} else if (auditBean.getAuditState().equals(Constant.WITHDRAW_AUDIT_FAIL)) {
			auditBean.setOrderStatus(Constant.ACCOUNT_EDIT_CANCEL);
			transferRevokeMapper.updateByAudit(auditBean);
			json.put("result", "SUCCESS");
		}
		return json;
	}

	public void revokeBill(TransferRevoke tradeBean) throws ParseException {

		ResponseMessage<RevokeResponse> response = revoke(tradeBean, RequestColumnValues.MsgType.CUST_TRANSFER_REVOKE);
		if (null == response) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
			tradeBean.setCoreReturnCode(response.getRtnCode().name());
			tradeBean.setCoreReturnMsg(response.getRtnInfo());
			tradeBean.setCoreSn(response.getMsgId());
			tradeBean.setCoreReturnTime(response.getRtnDatetime());

		} else if (RequestColumnValues.RtnResult.FAILURE == response.getRtnResult()) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
			tradeBean.setCoreReturnCode(response.getRtnCode().name());
			tradeBean.setCoreReturnMsg(response.getRtnInfo());
			tradeBean.setCoreSn(response.getMsgId());
			tradeBean.setCoreReturnTime(response.getRtnDatetime());
		}

		transferRevokeMapper.updateByRevoke(tradeBean);

	}

	/***
	 * 交易撤销调用核心
	 * 
	 * @param tradeFlow
	 * @param msgType
	 * @return
	 */

	public ResponseMessage<RevokeResponse> revoke(TransferRevoke tradeRevoke, RequestColumnValues.MsgType msgType) {
		ResponseMessage<RevokeResponse> response = null;
		TdTradeBillMainVO tradeVo = transferMapper.selectTransferByOrderId(tradeRevoke.getOriginOrderId());

		List<TradeFlow> tradeFlowList = tradeFlowMapper.selectTransferFlowById(tradeVo.getCoreSn());
		RequestMessage<RevokeRequest> request = new RequestMessage<RevokeRequest>();
		{
			request.setMsgType(msgType);
			request.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			request.setReqSerialId(tradeRevoke.getOrderId());
			request.setReqMsgNum(1);
			request.setReqDatetime(new Date());
			RevokeRequest revokeRequest = new RevokeRequest();
			{
				revokeRequest.setBrief(tradeRevoke.getRevokeMemo());
				revokeRequest.setCurrCode(RequestColumnValues.CurrCode.CNY);
				revokeRequest.setOriginTransAmt(tradeFlowList.get(0).getTransAmt());
				revokeRequest.setOriginMsgId(tradeFlowList.get(0).getMsgId());
				revokeRequest.setOriginMsgType(tradeFlowList.get(0).getMsgType());
			}
			request.setRequest(revokeRequest);
		}
		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		/** 调用交广科技撤销接口 **/

		logger.info("撤销调用核心请求[{}]", JSONObject.toJSONString(request, true));
		response = coreServiceInterface.revoke(request);
		logger.info("撤销调用核心返回[{}]", JSONObject.toJSONString(response, true));
		if (null == response) {
			return null;
		}
		return response;
	}

}
