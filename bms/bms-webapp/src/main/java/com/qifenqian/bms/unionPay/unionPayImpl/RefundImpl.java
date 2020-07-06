package com.qifenqian.bms.unionPay.unionPayImpl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;
import com.qifenqian.bms.accounting.exception.dao.transyl.mapper.TransYlMapper;
import com.stc.gateway.unionpay.AsynResponseListener;
import com.stc.gateway.unionpay.impl.refund.bean.RefundAsynRequestBean;

@Service
public class RefundImpl implements AsynResponseListener<RefundAsynRequestBean> {

	private static Logger logger = LoggerFactory.getLogger(RevocationImpl.class);

	@Autowired
	private TransYlMapper transYlMapper;

	@Override
	public void execute(RefundAsynRequestBean paramRequest) {
		logger.info("银联退款异步返回报文：{}", JSONObject.toJSONString(paramRequest, true));

		if (StringUtils.isBlank(paramRequest.getOrderId())) {
			throw new IllegalArgumentException("原始交易编号为空");
		}
		if (StringUtils.isBlank(paramRequest.getOrigQryId())) {
			throw new IllegalArgumentException("原始交易查询编号为空");
		}
		if (StringUtils.isBlank(paramRequest.getOrderId())) {
			throw new IllegalArgumentException("原始交易编号为空");
		}
		TransYl resultBean = transYlMapper.selectTransYlByTransId(paramRequest.getOrderId());
		if (resultBean.getTransAmt().compareTo(new BigDecimal(paramRequest.getTxnAmt())) != 0) {
			throw new IllegalArgumentException("退款金额与原交易金额不一致");
		}
		try {
			TransYl updateRefundBean = new TransYl();
			updateRefundBean.setTransId(paramRequest.getOrderId());
			updateRefundBean.setYlRespCodeYb(paramRequest.getRespCode());
			updateRefundBean.setYlRespMsgYb(paramRequest.getRespMsg());
			updateRefundBean.setYlRespTimeYb(paramRequest.getTxnTime());
			updateRefundBean.setSettleCurrencyCode(paramRequest.getSettleCurrencyCode());
			if (!StringUtils.isBlank(paramRequest.getSettleAmt())) {
				updateRefundBean.setSettleAmt(new BigDecimal(paramRequest.getSettleAmt()));
			}
			updateRefundBean.setSettleDate(paramRequest.getSettleDate());
			updateRefundBean.setTraceNo(paramRequest.getTraceNo());
			updateRefundBean.setTraceTime(paramRequest.getTraceTime());
			updateRefundBean.setExchangeDate(paramRequest.getExchangeDate());
			if (!StringUtils.isBlank(paramRequest.getExchangeRate())) {
				updateRefundBean.setExchangeRate(new BigDecimal(paramRequest.getExchangeRate()));
			}
			updateRefundBean.setQueryId(paramRequest.getQueryId());
			updateRefundBean.setTxnType(paramRequest.getTxnType());
			updateRefundBean.setTxnSubType(paramRequest.getTxnSubType());
			updateRefundBean.setBizType(paramRequest.getBizType());
			updateRefundBean.setAccessType(paramRequest.getAccessType());
			updateRefundBean.setMerId(paramRequest.getMerId());
			updateRefundBean.setReserved(paramRequest.getReserved());
			updateRefundBean.setReqReserved(paramRequest.getReqReserved());
			updateRefundBean.setVpcTransData(paramRequest.getVpcTransData());

			transYlMapper.updateTransYl(updateRefundBean);
			logger.info("银联退款异步返回更新完成");
		} catch (Exception e) {
			logger.error("银联退款异步返回更新异常" + e);
		}
	}
}
