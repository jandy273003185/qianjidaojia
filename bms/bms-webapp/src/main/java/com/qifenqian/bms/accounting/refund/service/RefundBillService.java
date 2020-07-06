package com.qifenqian.bms.accounting.refund.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.dao.transyl.mapper.TransYlMapper;
import com.qifenqian.bms.accounting.exception.service.TransDealService;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.bean.RefundExcel;
import com.qifenqian.bms.accounting.refund.bean.RefundRecord;
import com.qifenqian.bms.accounting.refund.bean.SevenmallRequestValues;
import com.qifenqian.bms.accounting.refund.bean.TradeFlow;
import com.qifenqian.bms.accounting.refund.dao.RefundBillDao;
import com.qifenqian.bms.accounting.refund.mapper.RefundBillMapper;
import com.qifenqian.bms.accounting.refund.mapper.RefundRecordMapper;
import com.qifenqian.bms.accounting.refund.mapper.SmallMapper;
import com.qifenqian.bms.accounting.refund.mapper.TradeFlowMapper;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.refund.RefundRequest;
import com.sevenpay.invoke.transaction.refund.RefundResponse;

@Service
public class RefundBillService {

	private static Logger logger = LoggerFactory.getLogger(RefundBillService.class);

	@Autowired
	private RefundBillDao refundBillDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private TradeFlowMapper tradeFlowMapper;

	@Autowired
	private RefundBillMapper refundBillMapper;

	@Autowired
	private RefundRecordMapper refundRecordMapper;

	@Autowired
	private TransDealService transDealService;

	@Autowired
	private TransYlMapper transYlMapper;
	
	@Autowired
	private SmallMapper smallMapper;

	public List<RefundBill> select(RefundBill queryBean) {
		return refundBillDao.select(queryBean);
	}

	public List<RefundExcel> selectRefundExcel(RefundBill queryBean) {
		return refundBillMapper.selectRefundExcel(queryBean);
	}

	public int refundVerification(RefundBill refundBill) {
		refundBill.setVerificationUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		return refundBillMapper.refundVerification(refundBill);

	}

	public int auditRefund(RefundBill updateBean) {
		return refundBillMapper.auditRefund(updateBean);
	}
	
	public List<RefundRecord> selectRefundRecordByOrderId(String orderId){
		return refundRecordMapper.selectRefundRecordByOrderId(orderId);
	}
	
	/**
	 * 退货
	 * 
	 * @param acctSevenCust
	 * @return
	 * @throws ParseException
	 */
	public JSONObject refundBill(RefundBill refundBill) throws ParseException {

		JSONObject json = new JSONObject();
		RefundBill updateBean = new RefundBill();
		RefundRecord insertBean = new RefundRecord();

		ResponseMessage<RefundResponse> response = new ResponseMessage<RefundResponse>();
		
			List<TradeFlow> tradeFlows = tradeFlowMapper.selectTransferFlowById(refundBill.getOriginCoreSn());
			TradeFlow tradeFlow = new TradeFlow();
			
			if (tradeFlows==null) {
				throw new IllegalArgumentException("原始交易信息不存在");
			}
			
			if(tradeFlows!=null && tradeFlows.size()>0){
				if(tradeFlows.size()>1){
					for(TradeFlow trade: tradeFlows){
						if("PAYMENT".equals(trade.getBusinessType())){
							tradeFlow = trade;
						}
					}
				}else{
					tradeFlow = tradeFlows.get(0);
				}
			}
			logger.info("退货申请核心流水信息：{}", JSONObject.toJSONString(tradeFlow, true));

			RequestColumnValues.MsgType msgType = null;
			if (tradeFlow.getMsgType() == RequestColumnValues.MsgType.BALANCE_PAYMENT) {
				msgType = RequestColumnValues.MsgType.BALANCE_PAYMENT_REFUND;
			} else if (tradeFlow.getMsgType() == RequestColumnValues.MsgType.BANK_CARD_PAYMENT) {
				msgType = RequestColumnValues.MsgType.BANK_CARD_PAYMENT_REFUND;
			} else {
				json.put("result", "FAIL");
				json.put("message", "暂不支持此类型");
				return json;
			}
			String id = BusinessUtils.getMsgId();
			insertBean.setId(id);
			insertBean.setBrief(tradeFlow.getBrief());
			insertBean.setBusinessType(tradeFlow.getBusinessType());
			insertBean.setCurrCode(tradeFlow.getCurrCode());
			insertBean.setRefundAmt(refundBill.getRefundAmt());
			insertBean.setCustId(refundBill.getRefundCustId());
			insertBean.setInstDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			insertBean.setInstDatetime(new Date());
			insertBean.setOriginMsgId(refundBill.getOriginCoreSn());
			insertBean.setOriginMsgType(tradeFlow.getMsgType().name());
			insertBean.setMsgType(msgType);
			insertBean.setCreator(String.valueOf(WebUtils.getUserInfo().getUserId()));
			insertBean.setRefundOrderId(refundBill.getOrderId());
			refundRecordMapper.insert(insertBean);
			
			updateBean.setOrderId(refundBill.getOrderId());
			updateBean.setRefundState(Constant.REFUND_STATE_CORE_HANDER);
			updateBean.setAuditState(refundBill.getAuditState());
			refundBillMapper.update(updateBean);
			response = refund(tradeFlow, msgType, id,refundBill.getRefundAmt());
			if (null == response || null == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用核心退款接口异常：无返回结果");
				return json;
			} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
				json.put("result", "SUCCESS");
				updateBean.setRefundState(Constant.REFUND_STATE_SUCCESS);
				updateBean.setCoreReturnMsg(response.getRtnCode().toString());
				insertBean.setRtnCode(response.getRtnCode().name());
				insertBean.setRtnInfo(response.getRtnInfo());
				insertBean.setRtnDatetime(response.getRtnDatetime());
				String outOrderId = refundBillMapper.findOutOrderId(refundBill.getOriginOrderId());
				smallMapper.updateBussorder(outOrderId, SevenmallRequestValues.SevenmallOrderStatus.REFUND.name());

			} else if (RequestColumnValues.RtnResult.FAILURE == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用核心退款失败：" + response.getRtnInfo());
				updateBean.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
				updateBean.setRefundState(Constant.REFUND_STATE_CROE_FAIL);
				insertBean.setRtnCode(response.getRtnCode().name());
				insertBean.setRtnInfo(response.getRtnInfo());
				insertBean.setRtnDatetime(response.getRtnDatetime());
			} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用核心退款异常：" + response.getRtnInfo());
				updateBean.setRefundState(Constant.REFUND_STATE_CROE_EXCEPTION);
				updateBean.setCoreReturnMsg(response.getRtnCode().toString() + " " + response.getRtnInfo());
				insertBean.setRtnCode(response.getRtnCode().name());
				insertBean.setRtnInfo(response.getRtnInfo());
				insertBean.setRtnDatetime(response.getRtnDatetime());
			}
			refundRecordMapper.update(insertBean);
			updateBean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			updateBean.setCoreSn(response.getMsgId());
			updateBean.setCoreReturnCode(response.getRtnCode().name());
			updateBean.setCoreReturnMsg(response.getRtnInfo());
			refundBillMapper.update(updateBean);
			return json;
	}

	/**
	 * 调用核心退款
	 * 
	 * @param tradeFlow
	 * @param msgType
	 * @return
	 */
	private ResponseMessage<RefundResponse> refund(TradeFlow tradeFlow, MsgType msgType, String reqSerialId,BigDecimal rebackAmt) {

		ResponseMessage<RefundResponse> response = new ResponseMessage<RefundResponse>();

		RequestMessage<RefundRequest> requestMessage = new RequestMessage<RefundRequest>();
		{
			requestMessage.setMsgType(msgType);
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			requestMessage.setReqSerialId(reqSerialId);
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());

			RefundRequest refundRequest = new RefundRequest();
			{
				refundRequest.setBrief(tradeFlow.getBrief());
				refundRequest.setCurrCode(tradeFlow.getCurrCode());
				refundRequest.setOriginTransAmt(tradeFlow.getTransAmt());
				refundRequest.setRebackAmt(rebackAmt);
				refundRequest.setOriginMsgId(tradeFlow.getMsgId());
				refundRequest.setOriginMsgType(tradeFlow.getMsgType());
				String fee = "0.00";
				BigDecimal amt = new BigDecimal(fee);
				refundRequest.setFeePay(amt);
				refundRequest.setFeeRcv(amt);

			}
			requestMessage.setRequest(refundRequest);
		}
		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		logger.info("退款调用核心请求[{}]", JSONObject.toJSONString(requestMessage, true));
		response = coreServiceInterface.refund(requestMessage);
		logger.info("退款调用核心返回[{}]", JSONObject.toJSONString(response, true));
		if (null == response) {
			return null;
		}
		return response;
	}
}
