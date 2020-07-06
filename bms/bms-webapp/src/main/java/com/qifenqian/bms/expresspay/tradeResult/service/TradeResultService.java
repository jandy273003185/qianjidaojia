package com.qifenqian.bms.expresspay.tradeResult.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.expresspay.tradeResult.bean.JgkjTrade;
import com.qifenqian.bms.expresspay.tradeResult.bean.TradeResult;
import com.qifenqian.bms.expresspay.tradeResult.dao.JgkjTradeDao;
import com.sevenpay.gateway.jgkj.IJgkj;
import com.sevenpay.gateway.jgkj.RequestHead;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn20030;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn2003RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn2003ResponseBean;

@Service
public class TradeResultService {

	private static Logger logger = LoggerFactory.getLogger(TradeResultService.class);

	@Autowired
	private JgkjTradeDao jgkjTradeDao;

	@Autowired
	@Qualifier("expressPayHttpInvokerProxy")
	private IJgkj expressPay;

	public List<JgkjTrade> queryJgkjTradeList(JgkjTrade queryBean) {
		return jgkjTradeDao.queryJgkjTradeList(queryBean);
	}

	/**
	 * 支付类交易结果查询
	 * 
	 * @param cardNo
	 * @return
	 */
	public TradeResult getTradeDetail(JgkjTrade requestBean) {

		Txn2003RequestBean request = new Txn2003RequestBean();
		RequestHead head = new RequestHead();
		head.setMerchantSeq(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		head.setTxnDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		head.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
		head.setSysId("S004");
		Txn20030 body = new Txn20030();
		body.setCardNo(requestBean.getCardNo());
		body.setoMerchantSeq(requestBean.getTransId());
		request.setBody(body);
		request.setHead(head);
		logger.info("交易结果查询调用网关请求[{}]", JSONObject.toJSONString(request, true));
		Txn2003ResponseBean response = expressPay.txn2003(request);
		logger.info("交易结果查询调用网关返回[{}]", JSONObject.toJSONString(response, true));

		if (null == response) {
			return null;
		}
		TradeResult tradeResult = new TradeResult();

		/** 无返回结果 **/
		if (Constant.JGKJ_NO_RESULT.equals(response.getHead().getErrCode())) {
			tradeResult.setPlatformSeq(response.getHead().getPlatformSeq());
			tradeResult.setErrCode(response.getHead().getErrCode());
			tradeResult.setTxnCode(response.getHead().getTxnCode());
			tradeResult.setTxnDate(response.getHead().getTxnDate());
			tradeResult.setTxnTime(response.getHead().getTxnTime());
			tradeResult.setRtnInfo("无返回结果");

		} else {
			tradeResult.setPlatformSeq(response.getBody().getPlatformSeq());
			tradeResult.setErrCode(response.getBody().getErrCode());
			tradeResult.setTxnCode(response.getBody().getTxnCode());
			tradeResult.setTxnDate(response.getBody().getTxnDate());
			tradeResult.setTxnTime(response.getBody().getTxnTime());
			tradeResult.setCardNo(response.getBody().getCardNo());

			if (null != response.getBody().getAmount()) {
				tradeResult.setAmount(response.getBody().getAmount());
			}
			/** 失败操作 **/
			if (Constant.JGKJ_SUCC.equals(response.getBody().getErrCode())) {
				tradeResult.setRtnInfo("交易成功");
			} else if (Constant.isJgkjFailure(response.getBody().getErrCode())) {
				tradeResult.setRtnInfo("交易失败");
			}
		}
		return tradeResult;
	}
}
