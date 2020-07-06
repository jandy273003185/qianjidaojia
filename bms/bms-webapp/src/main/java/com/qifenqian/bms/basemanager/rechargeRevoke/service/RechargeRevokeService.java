package com.qifenqian.bms.basemanager.rechargeRevoke.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;
import com.qifenqian.bms.accounting.exception.dao.transyl.dao.TransYlDao;
import com.qifenqian.bms.accounting.refund.bean.TradeFlow;
import com.qifenqian.bms.accounting.refund.mapper.TradeFlowMapper;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;
import com.qifenqian.bms.basemanager.recharge.mapper.RechargeMapper;
import com.qifenqian.bms.basemanager.rechargeRevoke.bean.RechargeRevoke;
import com.qifenqian.bms.basemanager.rechargeRevoke.mapper.RechargeRevokeMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.revoke.RevokeRequest;
import com.sevenpay.invoke.transaction.revoke.RevokeResponse;
import com.stc.gateway.unionpay.impl.consumeundo.bean.ConsumeUndoRequestBean;
import com.stc.gateway.unionpay.impl.consumeundo.bean.ConsumeUndoResponseBean;
import com.stc.gateway.unionpay.impl.refund.bean.RefundRequestBean;
import com.stc.gateway.unionpay.impl.refund.bean.RefundResponseBean;

@Service
public class RechargeRevokeService {
	private static Logger logger = LoggerFactory.getLogger(RechargeRevokeService.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private RechargeRevokeMapper rechargeRevokeMapper;

	@Autowired
	private RechargeMapper rechargeMapper;

	@Autowired
	private TradeFlowMapper tradeFlowMapper;

	@Autowired
	private TransYlDao transYlDao;

	/**
	 * 申请
	 * 
	 * @param applyBean
	 * @return
	 */
	public JSONObject rechargeRevokeApply(RechargeRevoke applyBean) {
		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (StringUtils.isBlank(applyBean.getOriginOrderId())) {
			json.put("message", "原充值编号为空");
			return json;
		}
		if (StringUtils.isBlank(applyBean.getRevokeMemo())) {
			json.put("message", "撤销原因为空");
			return json;
		}
		RechargeBean rechargeVo = rechargeMapper.selectRechargeBeanByChargeSn(applyBean.getOriginOrderId());

		if (rechargeVo == null) {
			json.put("message", "原交易不存在");
			return json;
		}
		if("6".equals(rechargeVo.getChargeType())){
			json.put("message", "暂不支持红包充值撤销交易");
			return json;
		}
		logger.info("充值撤销原交易信息：{}", JSONObject.toJSONString(rechargeVo, true));

		String reqSerialId = BusinessUtils.getMsgId();
		applyBean.setOrderId(reqSerialId);

		applyBean.setRechargeCustId(rechargeVo.getCustId());
		applyBean.setRevokeAmt(rechargeVo.getChargeAmt());
		applyBean.setCurrCode(RequestColumnValues.CurrCode.CNY);
		applyBean.setOriginRechargeTime(rechargeVo.getChargeReturnTime());
		applyBean.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		applyBean.setOrderStatus(Constant.PAYMENT_REVOKE_INIT);
		applyBean.setAuditState(Constant.WITHDRAW_AUDIT_WAIT);
		rechargeRevokeMapper.insertRechargeRevoke(applyBean);
		json.put("result", "SUCCESS");
		return json;
	}

	/**
	 * 审核
	 * 
	 * @param auditBean
	 * @return
	 * @throws ParseException
	 */
	public JSONObject rechargeRevokeAudit(RechargeRevoke auditBean) throws ParseException {
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
			rechargeRevokeMapper.updateByAudit(auditBean);
			this.rechargeRevokeBill(auditBean);
			json.put("result", "SUCCESS");
		} else if (auditBean.getAuditState().equals(Constant.WITHDRAW_AUDIT_FAIL)) {
			auditBean.setOrderStatus(Constant.ACCOUNT_EDIT_CANCEL);
			rechargeRevokeMapper.updateByAudit(auditBean);
			json.put("result", "SUCCESS");
		}
		return json;
	}

	private void rechargeRevokeBill(RechargeRevoke rechargeBean) throws ParseException {
		
		/**调用核心撤销**/
		ResponseMessage<RevokeResponse> response = revoke(rechargeBean,RequestColumnValues.MsgType.CUST_RECHARGE_REVOKE);
		
		if (null == response) {
			rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_SUCC);
			rechargeBean.setCoreReturnCode(response.getRtnCode().name());
			rechargeBean.setCoreReturnMsg(response.getRtnInfo());
			rechargeBean.setCoreSn(response.getMsgId());
			rechargeBean.setCoreReturnTime(response.getRtnDatetime());

		} else if (RequestColumnValues.RtnResult.FAILURE == response.getRtnResult()) {
			rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
			rechargeBean.setCoreReturnCode(response.getRtnCode().name());
			rechargeBean.setCoreReturnMsg(response.getRtnInfo());
			rechargeBean.setCoreSn(response.getMsgId());
			rechargeBean.setCoreReturnTime(response.getRtnDatetime());
		}

		rechargeRevokeMapper.updateRechargeRevoke(rechargeBean);

		if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			TransYl transyl = transYlDao.selectTransYlByTransSn(rechargeBean.getOriginOrderId());
			logger.info("撤销申请银联流水信息：{}", JSONObject.toJSONString(transyl, true));

			rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_DEAL);
			rechargeRevokeMapper.updateRechargeRevoke(rechargeBean);

			RechargeBean rechargeVo = rechargeMapper.selectRechargeBeanByChargeSn(rechargeBean.getOriginOrderId());
			
			/**当天撤销**/
			if (DateFormatUtils.format(DateUtils.addDays(new Date(), -0), "yyyy-MM-dd").compareTo(
					DateFormatUtils.format(rechargeVo.getChargeReturnTime(), "yyyy-MM-dd")) > 0) {
				/**退款**/
				RefundResponseBean refundResponse = IUnionPayRollBack(transyl, rechargeBean.getOrderId());

				if (null == refundResponse) {
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
				} else if (refundResponse.getRespCode().equals("00")) {
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());

				} else if (refundResponse.getRespCode().equals("G8")) {

					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());
				} else {
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());
				}
				
			}else{
				
				ConsumeUndoResponseBean revokeResponse = IUnionPayRevocation(transyl, rechargeBean.getOrderId());

				if (null == revokeResponse) {
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
				} else if (revokeResponse.getRespCode().equals("00")) {
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());

				} else if (revokeResponse.getRespCode().equals("G8")) {

					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());
				} else {
					rechargeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
					rechargeBean.setCoreReturnCode(response.getRtnCode().name());
					rechargeBean.setCoreReturnMsg(response.getRtnInfo());
					rechargeBean.setCoreReturnTime(response.getRtnDatetime());
				}
			}
		}
		rechargeRevokeMapper.updateRechargeRevoke(rechargeBean);

	}

	/***
	 * 交易撤销调用核心
	 * 
	 * @param tradeFlow
	 * @param msgType
	 * @return
	 */

	public ResponseMessage<RevokeResponse> revoke(RechargeRevoke tradeRevoke, RequestColumnValues.MsgType msgType) {
		ResponseMessage<RevokeResponse> response = null;
		RechargeBean rechargeVo = rechargeMapper.selectRechargeBeanByChargeSn(tradeRevoke.getOriginOrderId());

		TradeFlow tradeFlow = tradeFlowMapper.selectTransFlowByRecharge(rechargeVo.getCoreSn());
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
				revokeRequest.setCurrCode(tradeFlow.getCurrCode());
				revokeRequest.setOriginTransAmt(tradeFlow.getTransAmt());
				revokeRequest.setOriginMsgId(tradeFlow.getMsgId());
				revokeRequest.setOriginMsgType(tradeFlow.getMsgType());
			}
			request.setRequest(revokeRequest);
		}
		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		/** 调用交广科技撤销接口 **/

		logger.info("充值撤销调用核心请求[{}]", JSONObject.toJSONString(request, true));
		response = coreServiceInterface.revoke(request);
		logger.info("充值撤销调用核心返回[{}]", JSONObject.toJSONString(response, true));
		if (null == response) {
			return null;
		}
		return response;
	}

	/**
	 * 银联交易撤销
	 * 
	 * @param transBean
	 * @return
	 */
	public ConsumeUndoResponseBean IUnionPayRevocation(TransYl resultBean, String transSn) throws ParseException {

		ConsumeUndoRequestBean request = new ConsumeUndoRequestBean();

		String transId = GenSN.getOperateID();
		String transSubmitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		request.setSysId("S004");
		request.setOrderId(transId);
		request.setTxnTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		if (null == resultBean.getTransAmt()) {
			return null;
		}
		request.setTxnAmt(resultBean.getTransAmt().toString());
		request.setOrigQryId(resultBean.getQueryId());
		resultBean.setTransId(transId);
		resultBean.setTransSn(transSn);
		resultBean.setTransSubmitTime(transSubmitTime);
		resultBean.setTransType("REVOCATION");
		resultBean.setRevokeQueryId(resultBean.getQueryId());
		resultBean.setWorkDate(commonService.getIPlugin().findDictByPath("ACCTING_WORK_DATE"));
		transYlDao.insertTransYl(resultBean);

		ConsumeUndoResponseBean response = null;

		logger.info("调用银联交易撤销发送报文：" + JSONObject.toJSONString(request));
		response = commonService.getIUnionPay().consumeUndo(request);
		logger.info("调用银联交易撤销返回报文：" + JSONObject.toJSONString(response));
		if (null == response) {
			return null;
		}
		/** 修改返回 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transId);
		updateBean.setYlRespCodeTb(response.getRespCode());
		updateBean.setYlRespTimeTb(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		updateBean.setQueryId(response.getQueryId());
		updateBean.setBizType(response.getBizType());
		updateBean.setMerId(response.getMerId());
		updateBean.setTxnType(response.getTxnType());
		updateBean.setTxnSubType(response.getTxnSubType());
		updateBean.setAccessType(response.getAccessType());
		transYlDao.updateTransYl(updateBean);

		return response;
	}

	/**
	 * 银联退款接口
	 * 
	 * @param transBean
	 * @return
	 */
	public RefundResponseBean IUnionPayRollBack(TransYl transBean, String transSn) throws ParseException {
		RefundResponseBean response = null;
		
		RefundRequestBean request = new RefundRequestBean();
		request.setSysId("S004");
		String transId = GenSN.getOperateID();
		String transSubmitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setOrderId(transId);
		request.setTxnTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

		if (null == transBean.getTransAmt()) {
			return null;
		}
		request.setTxnAmt(transBean.getTransAmt().toString());
		request.setOrigQryId(transBean.getQueryId());

		/** 记录 **/
		transBean.setTransId(transId);
		transBean.setTransSubmitTime(transSubmitTime);
		transBean.setTransType("REFUND");
		transBean.setTransSn(transSn);
		transBean.setRevokeQueryId(transBean.getQueryId());
		transBean.setWorkDate(commonService.getIPlugin().findDictByPath("ACCTING_WORK_DATE"));
		transYlDao.insertTransYl(transBean);

	

		logger.info("调用银联退款发送报文：" + JSONObject.toJSONString(request));
		response = commonService.getIUnionPay().refund(request);
		logger.info("调用银联退款返回报文：" + JSONObject.toJSONString(response));
		if (null == response) {
			return null;
		}

		/** 修改返回 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transId);
		updateBean.setYlRespCodeTb(response.getRespCode());
		updateBean.setYlRespTimeTb(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		updateBean.setQueryId(response.getQueryId());
		updateBean.setBizType(response.getBizType());
		updateBean.setMerId(response.getMerId());
		updateBean.setTxnType(response.getTxnType());
		updateBean.setTxnSubType(response.getTxnSubType());
		updateBean.setAccessType(response.getAccessType());
		transYlDao.updateTransYl(updateBean);

		return response;
	}
}
