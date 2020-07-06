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
import com.stc.gateway.unionpay.impl.consumeundo.bean.ConsumeUndoAsynRequestBean;

@Service
public class RevocationImpl implements AsynResponseListener<ConsumeUndoAsynRequestBean> {

	private static Logger logger = LoggerFactory.getLogger(RevocationImpl.class);
	@Autowired
	private TransYlMapper transYlMapper;

	@Override
	public void execute(ConsumeUndoAsynRequestBean paramRequest) {
		
		logger.info("银联交易撤销异步返回报文：{}",JSONObject.toJSONString(paramRequest,true));
		
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
			TransYl updateRevokeBean = new TransYl();
			updateRevokeBean.setTransId(paramRequest.getOrderId());
			updateRevokeBean.setYlRespCodeYb(paramRequest.getRespCode());
			updateRevokeBean.setYlRespMsgYb(paramRequest.getRespMsg());
			updateRevokeBean.setYlRespTimeYb(paramRequest.getTxnTime());
			updateRevokeBean.setSettleCurrencyCode(paramRequest.getSettleCurrencyCode());
			if (!StringUtils.isBlank(paramRequest.getSettleAmt())) {
				updateRevokeBean.setSettleAmt(new BigDecimal(paramRequest.getSettleAmt()));
			}
			updateRevokeBean.setSettleDate(paramRequest.getSettleDate());
			updateRevokeBean.setTraceNo(paramRequest.getTraceNo());
			updateRevokeBean.setTraceTime(paramRequest.getTraceTime());
			updateRevokeBean.setExchangeDate(paramRequest.getExchangeDate());
			if (!StringUtils.isBlank(paramRequest.getExchangeRate())) {
				updateRevokeBean.setExchangeRate(new BigDecimal(paramRequest.getExchangeRate()));
			}
			updateRevokeBean.setQueryId(paramRequest.getQueryId());
			updateRevokeBean.setTxnType(paramRequest.getTxnType());
			updateRevokeBean.setTxnSubType(paramRequest.getTxnSubType());
			updateRevokeBean.setBizType(paramRequest.getBizType());
			updateRevokeBean.setAccessType(paramRequest.getAccessType());
			updateRevokeBean.setMerId(paramRequest.getMerId());
			updateRevokeBean.setReserved(paramRequest.getReserved());
			updateRevokeBean.setReqReserved(paramRequest.getReqReserved());
			updateRevokeBean.setVpcTransData(paramRequest.getVpcTransData());

			transYlMapper.updateTransYl(updateRevokeBean);
			logger.info("银联交易撤销异步返回更新完成");
		} catch (Exception e) {
			logger.error("银联交易撤销异步返回更新异常"+e);
		}
	}
}
