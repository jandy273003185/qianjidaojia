package com.qifenqian.bms.unionPay.tradeylresult;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeState;
import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeYlResut;
import com.qifenqian.bms.unionPay.tradeylresult.dao.TradeYlResultDao;
import com.qifenqian.bms.unionPay.tradeylresult.mapper.TradeYlResultMapper;
import com.stc.gateway.unionpay.IUnionPay;
import com.stc.gateway.unionpay.impl.query.bean.QueryRequestBean;
import com.stc.gateway.unionpay.impl.query.bean.QueryResponseBean;

@Service
public class TradeYlResultService {

	private static Logger logger = LoggerFactory.getLogger(TradeYlResultService.class);

	@Autowired
	private TradeYlResultDao tradeYlResultDao;

	@Autowired
	private TradeYlResultMapper tradeYlResultMapper;
	@Autowired
	private CommonService commonService;

	public List<TradeYlResut> selectTransYlResut(TradeYlResut queryBean) {
		return tradeYlResultDao.selectTransYlResut(queryBean);
	}

	/**
	 * 查询银联交易结果
	 * 
	 * @param transSn
	 * @return
	 * @throws Exception
	 */
	public TradeState selectTradeStateResult(TradeYlResut queryBean) throws Exception {
		logger.info("查询银联交易结果  orderId:" + queryBean + "调用核心");

		IUnionPay iUnionPay = commonService.getIUnionPay();
		QueryRequestBean queryRequestBean = new QueryRequestBean();
		queryRequestBean.setOrderId(queryBean.getTransId());
		queryRequestBean.setTxnTime(queryBean.getTransSubmitTime());
		queryRequestBean.setSysId("S004");

		logger.info("发送银联报文：" + JSONObject.toJSONString(queryRequestBean));
		QueryResponseBean queryResponseBean = iUnionPay.query(queryRequestBean);
		logger.info("银联返回报文：" + JSONObject.toJSONString(queryResponseBean));
		if (queryResponseBean == null) {
			return null;
		}
		TradeState tradeState = new TradeState();
		if (Constant.YINLIAN_SUCC.equals(queryResponseBean.getRespCode())) {
			tradeState.setQueryId(queryResponseBean.getQueryId());
			tradeState.setSettleDate(queryResponseBean.getSettleDate());
			tradeState.setSettleCurrencyCode(queryResponseBean.getSettleCurrencyCode());
			tradeState.setOrigRespCode(queryResponseBean.getOrigRespCode());
			tradeState.setOrigRespMsg(queryResponseBean.getOrigRespMsg());
			tradeState.setExchangeRate(queryResponseBean.getExchangeRate());
			tradeState.setExchangeDate(queryResponseBean.getExchangeDate());
			tradeState.setAccNo(queryResponseBean.getAccNo());
			tradeState.setTraceNo(queryResponseBean.getTraceNo());
			if (null != queryResponseBean.getSettleAmt()) {
				tradeState.setSettleAmt(new BigDecimal(queryResponseBean.getSettleAmt()));
			}
			if (null != queryResponseBean.getTxnAmt()) {
				tradeState.setTxnAmt(new BigDecimal(queryResponseBean.getTxnAmt()));
			}
		} else {
			tradeState.setExchangeDate(queryResponseBean.getExchangeDate());
			tradeState.setOrigRespCode(queryResponseBean.getRespCode());
			tradeState.setOrigRespMsg(queryResponseBean.getRespMsg());
			tradeState.setSettleAmt(new BigDecimal("0.00"));
			tradeState.setTxnAmt(new BigDecimal("0.00"));
		}

		tradeState.setOrderId(queryResponseBean.getOrderId());
		tradeState.setMerId(queryResponseBean.getMerId());
		tradeState.setTxnTime(queryResponseBean.getTxnTime());
		return tradeState;
	}

	public int updateTransYlResut(TradeYlResut updateBean) {
		return tradeYlResultMapper.updateTransYlResut(updateBean);
	}
}
