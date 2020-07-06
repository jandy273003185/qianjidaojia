package com.qifenqian.bms.basemanager.tradeRevoke.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.trade.mapper.TdTradeBillMainMapper;
import com.qifenqian.bms.basemanager.tradeRevoke.bean.CustTransRevoke;
import com.qifenqian.bms.basemanager.tradeRevoke.dao.CustTransRevokeDao;
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

/**
 * 
 * @author shen
 * 
 */
@Service
public class CustTransRevokeService {

	private static Logger logger = LoggerFactory.getLogger(CustTransRevokeService.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private TransYlDao transYlDao;

	@Autowired
	private CustTransRevokeDao custTransRevokeDao;

	@Autowired
	private TdTradeBillMainMapper tdTradeBillMainMapper;

	@Autowired
	private TradeFlowMapper tradeFlowMapper;

	/**
	 * 撤销
	 * 
	 * @param acctSevenCust
	 * @return
	 * @throws ParseException
	 */

	/**
	 * 申请
	 * 
	 * @param applyBean
	 * @return
	 */
	public JSONObject transRevokeApply(CustTransRevoke applyBean) {
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
		TdTradeBillMainVO tradeVo = tdTradeBillMainMapper.selectTdradeBillMainByOrderId(applyBean.getOriginOrderId());
		if (tradeVo == null) {
			json.put("message", "原交易不存在");
			return json;
		}
		if(tradeVo.getTradeType()==RequestColumnValues.MsgType.RED_PACKET_PAYMENT.name()){
			json.put("message", "暂不支持红包支付撤销交易");
			return json;
		}
		String reqSerialId = BusinessUtils.getMsgId();
		applyBean.setOrderId(reqSerialId);
		applyBean.setOriginOrderName(tradeVo.getOrderName());
		applyBean.setOriginTradeType(tradeVo.getTradeType());
		applyBean.setRevokeCustId(tradeVo.getPayerCustId());
		applyBean.setMerchantCustId(tradeVo.getPayeeCustId());
		applyBean.setRevokeAmt(tradeVo.getSumAmt());
		applyBean.setCurrCode(RequestColumnValues.CurrCode.CNY);
		applyBean.setOriginTransTime(tradeVo.getOrderCoreReturnTime());
		applyBean.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		applyBean.setOrderStatus(Constant.PAYMENT_REVOKE_INIT);
		applyBean.setAuditState(Constant.WITHDRAW_AUDIT_WAIT);
		custTransRevokeDao.insert(applyBean);
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
	public JSONObject transRevokeAudit(CustTransRevoke auditBean) throws ParseException {

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
		if (StringUtils.isBlank(auditBean.getOriginTradeType())) {
			json.put("message", "原交易类型为空");
			return json;
		}
		auditBean.setAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));

		if (auditBean.getAuditState().equals(Constant.WITHDRAW_AUDIT_SUCCESS)) {
			auditBean.setOrderStatus(Constant.ACCOUNT_EDIT_CORE_DEAL);
			custTransRevokeDao.updateByAudit(auditBean);
			this.revokeBill(auditBean);
			json.put("result", "SUCCESS");
		} else if (auditBean.getAuditState().equals(Constant.WITHDRAW_AUDIT_FAIL)) {
			auditBean.setOrderStatus(Constant.ACCOUNT_EDIT_CANCEL);
			custTransRevokeDao.updateByAudit(auditBean);
			json.put("result", "SUCCESS");
		}
		return json;
	}

	public void revokeBill(CustTransRevoke tradeBean) throws ParseException {
		RequestColumnValues.MsgType msgType = null;
		if (tradeBean.getOriginTradeType().equals(RequestColumnValues.MsgType.BALANCE_PAYMENT.name())) {
			msgType = RequestColumnValues.MsgType.BALANCE_PAYMENT_REVOKE;
		} else if (tradeBean.getOriginTradeType().equals(RequestColumnValues.MsgType.BANK_CARD_PAYMENT.name())) {
			msgType = RequestColumnValues.MsgType.BANK_CARD_PAYMENT_REVOKE;
		}

		ResponseMessage<RevokeResponse> response = revoke(tradeBean, msgType);
		if (null == response) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_SUCC);
			tradeBean.setCoreReturnCode(response.getRtnCode().name());
			tradeBean.setCoreReturnMsg(response.getRtnInfo());
			tradeBean.setCoreSn(response.getMsgId());
			tradeBean.setCoreReturnTime(response.getRtnDatetime());
			if (tradeBean.getOriginTradeType().equals(RequestColumnValues.MsgType.BALANCE_PAYMENT.name())) {
				tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
			}
		} else if (RequestColumnValues.RtnResult.FAILURE == response.getRtnResult()) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_CORE_FAIL);
			tradeBean.setCoreReturnCode(response.getRtnCode().name());
			tradeBean.setCoreReturnMsg(response.getRtnInfo());
			tradeBean.setCoreSn(response.getMsgId());
			tradeBean.setCoreReturnTime(response.getRtnDatetime());
		}

		custTransRevokeDao.updateByRevoke(tradeBean);

		if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()
				&& tradeBean.getOriginTradeType().equals(RequestColumnValues.MsgType.BANK_CARD_PAYMENT.name())) {
			TransYl transyl = transYlDao.selectTransYlByTransSn(tradeBean.getOriginOrderId());
			
			logger.info("撤销申请银联流水信息：{}", JSONObject.toJSONString(transyl, true));

			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_DEAL);
			custTransRevokeDao.updateByRevoke(tradeBean);

			ConsumeUndoResponseBean consumeUndoResponse = IUnionPayRevocation(transyl, tradeBean.getOrderId());

			if (null == consumeUndoResponse) {

			} else if (consumeUndoResponse.getRespCode().equals("00")) {
				tradeBean.setCoreReturnCode(response.getRtnCode().name());
				tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
				tradeBean.setCoreReturnMsg(response.getRtnInfo());
				tradeBean.setCoreReturnTime(response.getRtnDatetime());

			} else if (consumeUndoResponse.getRespCode().equals("G8")) {

				tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
				tradeBean.setCoreReturnCode(response.getRtnCode().name());
				tradeBean.setCoreReturnMsg(response.getRtnInfo());
				tradeBean.setCoreReturnTime(response.getRtnDatetime());
			} else {
				tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_YL_FAIL);
				tradeBean.setCoreReturnCode(response.getRtnCode().name());
				tradeBean.setCoreReturnMsg(response.getRtnInfo());
				tradeBean.setCoreReturnTime(response.getRtnDatetime());
			}
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()
				&& tradeBean.getOriginTradeType().equals(RequestColumnValues.MsgType.BALANCE_PAYMENT.name())) {
			tradeBean.setOrderStatus(Constant.PAYMENT_REVOKE_SUCCESS);
		}
		custTransRevokeDao.updateByRevoke(tradeBean);

	}

	/***
	 * 交易撤销调用核心
	 * 
	 * @param tradeFlow
	 * @param msgType
	 * @return
	 */

	public ResponseMessage<RevokeResponse> revoke(CustTransRevoke tradeRevoke, RequestColumnValues.MsgType msgType) {
		ResponseMessage<RevokeResponse> response = null;
		TdTradeBillMainVO tradeVo = tdTradeBillMainMapper.selectTdradeBillMainByOrderId(tradeRevoke.getOriginOrderId());

		TradeFlow tradeFlow = tradeFlowMapper.selectTransFlowById(tradeVo.getCoreSn());
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

		logger.info("撤销调用核心请求[{}]", JSONObject.toJSONString(request, true));
		response = coreServiceInterface.revoke(request);
		logger.info("撤销调用核心返回[{}]", JSONObject.toJSONString(response, true));
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

}
