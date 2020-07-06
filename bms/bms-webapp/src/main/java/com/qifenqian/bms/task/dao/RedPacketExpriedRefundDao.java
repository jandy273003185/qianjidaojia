package com.qifenqian.bms.task.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.bean.RefundRecord;
import com.qifenqian.bms.expresspay.CommonService;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.refund.RefundRequest;
import com.sevenpay.invoke.transaction.refund.RefundResponse;

@Service
public class RedPacketExpriedRefundDao {
	
	private Logger logger = LoggerFactory.getLogger(RedPacketExpriedRefundDao.class);
	
	@Autowired
	private CommonService commonService;
	
	public ResponseMessage<RefundResponse> refund(RefundRecord recordBean,RefundBill refundBean) {

		ResponseMessage<RefundResponse> response = new ResponseMessage<RefundResponse>();

		RequestMessage<RefundRequest> requestMessage = new RequestMessage<RefundRequest>();
		{
			requestMessage.setMsgType(recordBean.getMsgType());
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			requestMessage.setReqSerialId(recordBean.getId());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());

			RefundRequest refundRequest = new RefundRequest();
			{
				refundRequest.setBrief(recordBean.getBrief());
				refundRequest.setCurrCode(recordBean.getCurrCode());
				refundRequest.setOriginTransAmt(refundBean.getOriginTransAmt());
				refundRequest.setRebackAmt(recordBean.getRefundAmt());
				refundRequest.setOriginMsgId(refundBean.getOriginCoreSn());
				refundRequest.setOriginMsgType(RequestColumnValues.MsgType.RED_PACKET_PAYMENT);
				String fee = "0.00";
				BigDecimal amt = new BigDecimal(fee);
				refundRequest.setFeePay(amt);
				refundRequest.setFeeRcv(amt);

			}
			requestMessage.setRequest(refundRequest);
		}
		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		logger.info("红包支付退款调用核心请求[{}]", JSONObject.toJSONString(requestMessage, true));
		response = coreServiceInterface.refund(requestMessage);
		logger.info("红包支付退款调用核心返回[{}]", JSONObject.toJSONString(response, true));
		if (null == response) {
			return null;
		}
		return response;
	}
}
