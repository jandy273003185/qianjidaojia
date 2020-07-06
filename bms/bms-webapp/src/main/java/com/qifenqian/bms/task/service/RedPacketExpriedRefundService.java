package com.qifenqian.bms.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.bean.RefundRecord;
import com.qifenqian.bms.accounting.refund.bean.TradeFlow;
import com.qifenqian.bms.accounting.refund.mapper.RefundBillMapper;
import com.qifenqian.bms.accounting.refund.mapper.RefundRecordMapper;
import com.qifenqian.bms.accounting.refund.mapper.TradeFlowMapper;
import com.qifenqian.bms.accounting.refund.service.RefundBillService;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.trade.mapper.TdTradeBillMainMapper;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.sns.redpacket.mapper.RedEnvelopeInfoMapper;
import com.qifenqian.bms.task.dao.RedPacketExpriedRefundDao;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.refund.RefundResponse;

@Service
public class RedPacketExpriedRefundService {

	private Logger logger = LoggerFactory.getLogger(RedPacketExpriedRefundService.class);

	@Autowired
	private RefundBillService refundBillService;
	@Autowired
	private RefundBillMapper refundBillMapper;
	@Autowired
	private TdTradeBillMainMapper tdTradeBillMainMapper;
	@Autowired
	private TradeFlowMapper tradeFlowMapper;
	@Autowired
	private RefundRecordMapper refundRecordMapper;
	@Autowired
	private RedPacketExpriedRefundDao redPacketExpriedRefundDao;
	@Autowired
	private RedEnvelopeInfoMapper redEnvelopeInfoMapper;

	/**
	 * 查询红包
	 * 
	 * @return
	 */
	@Transactional
	public List<RedEnvelopeInfo> selectExpriedRedPacket() {
		List<RedEnvelopeInfo> redEnvelopeInfoList = redEnvelopeInfoMapper.selectExpriedRedPacket();
		if (redEnvelopeInfoList.size() > 0) {
			redEnvelopeInfoMapper.updateRedEnvelopeInfoState(redEnvelopeInfoList);
		}
		return redEnvelopeInfoList;
	}

	/***
	 * 过期红包退款
	 * 
	 * @param expriedInfo
	 */
	public void refund(RedEnvelopeInfo expriedInfo) {
		
		logger.info("========红包支付退款对象{} :", JSONObject.toJSONString(expriedInfo), true);
		try {

			TdTradeBillMainVO tradeBean = tdTradeBillMainMapper.selectRedpacketPaymentByOrderId(expriedInfo.getOrderId());
			if (tradeBean == null) {
				throw new IllegalArgumentException("原红包支付订单不存在：" + expriedInfo.getOrderId());
			}
			if (tradeBean.getSumAmt().compareTo(expriedInfo.getRedEnvAmt()) != 0) {
				throw new IllegalArgumentException("原红包支付金额不相等：" + expriedInfo.getOrderId());
			}

			RefundRecord recordBean = new RefundRecord();
			TradeFlow tradeFlow = tradeFlowMapper.selectTransFlowById(tradeBean.getCoreSn());
			if (tradeFlow == null) {
				throw new IllegalArgumentException("原始交易信息不存在");
			}
			logger.info("退货申请原交易核心流水信息：{}", JSONObject.toJSONString(tradeFlow, true));

			RefundBill refundBean = new RefundBill();
			String orderId = BusinessUtils.getMsgId();
			refundBean.setOrderId(orderId);
			refundBean.setOriginOrderId(tradeBean.getOrderId());
			refundBean.setRefundCustId(tradeBean.getPayerCustId());
			refundBean.setMerchantCustId(tradeBean.getPayerCustId());
			refundBean.setOriginCoreSn(tradeBean.getCoreSn());
			refundBean.setCurrCode(RequestColumnValues.CurrCode.CNY);
			refundBean.setOriginTransAmt(tradeBean.getSumAmt());
			refundBean.setOriginTransTime(tradeBean.getOrderCoreReturnTime());
			refundBean.setRefundAmt(expriedInfo.getExpiredLeftAmt());
			refundBean.setRefundMemo("红包支付退款");
			refundBean.setRefundState(Constant.REFUND_STATE_CORE_HANDER);
			refundBean.setAuditState(Constant.WITHDRAW_AUDIT_SUCCESS);
			refundBean.setVerificationState(Constant.VERIFICATION_STATE_WAIT);

			refundBillMapper.insertRefundBill(refundBean);

			expriedInfo.setExpiredBalProcOrderId(orderId);
			redEnvelopeInfoMapper.updateRedEnvelopeInfoByRefund(expriedInfo);

			String recordId = BusinessUtils.getMsgId();
			recordBean.setId(recordId);
			recordBean.setBrief("红包退款");
			recordBean.setBusinessType(tradeFlow.getBusinessType());
			recordBean.setCurrCode(tradeFlow.getCurrCode());
			recordBean.setRefundAmt(refundBean.getRefundAmt());
			recordBean.setCustId(refundBean.getRefundCustId());
			recordBean.setInstDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			recordBean.setInstDatetime(new Date());
			recordBean.setOriginMsgId(refundBean.getOriginCoreSn());
			recordBean.setOriginMsgType(RequestColumnValues.MsgType.RED_PACKET_PAYMENT.name());
			recordBean.setMsgType(RequestColumnValues.MsgType.RED_PACKET_PAYMENT_REFUND);
			recordBean.setRefundOrderId(refundBean.getOrderId());

			refundRecordMapper.insert(recordBean);
			RedEnvelopeInfo redEnvelopeInfo = new RedEnvelopeInfo();
			redEnvelopeInfo.setRedEnvId(expriedInfo.getRedEnvId());

			ResponseMessage<RefundResponse> response = redPacketExpriedRefundDao.refund(recordBean, refundBean);

			if (null != response && null != response.getRtnResult()) {

				if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
					refundBean.setRefundState(Constant.REFUND_STATE_SUCCESS);
					refundBean.setCoreReturnMsg(response.getRtnCode().toString());
					recordBean.setRtnCode(response.getRtnCode().name());
					recordBean.setRtnInfo(response.getRtnInfo());
					recordBean.setRtnDatetime(response.getRtnDatetime());
					redEnvelopeInfo.setExpiredBalProcStatus(Constant.REFUND_STATE_SUCCESS);

				} else if (RequestColumnValues.RtnResult.FAILURE == response.getRtnResult()) {

					refundBean.setCoreReturnMsg(response.getRtnInfo());
					refundBean.setRefundState(Constant.REFUND_STATE_CROE_FAIL);
					recordBean.setRtnCode(response.getRtnCode().name());
					recordBean.setRtnInfo(response.getRtnInfo());
					recordBean.setRtnDatetime(response.getRtnDatetime());
					redEnvelopeInfo.setExpiredBalProcStatus(Constant.RED_REFUND_STATE_FAIL);
				} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {

					refundBean.setRefundState(Constant.REFUND_STATE_CROE_EXCEPTION);
					refundBean.setCoreReturnMsg(response.getRtnInfo());
					recordBean.setRtnCode(response.getRtnCode().name());
					recordBean.setRtnInfo(response.getRtnInfo());
					recordBean.setRtnDatetime(response.getRtnDatetime());
					redEnvelopeInfo.setExpiredBalProcStatus(Constant.RED_REFUND_STATE_FAIL);
				}

				refundRecordMapper.update(recordBean);
				refundBean.setCoreSn(response.getMsgId());
				refundBean.setCoreReturnCode(response.getRtnCode().name());
				refundBean.setCoreReturnMsg(response.getRtnInfo());
				refundBillMapper.update(refundBean);
			}
			redEnvelopeInfoMapper.updateRedEnvelopeInfo(redEnvelopeInfo);
		} catch (Exception e) {
			logger.error("过期红包退款异常:", e);
		}
	}
}
