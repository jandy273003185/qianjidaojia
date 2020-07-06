package com.qifenqian.bms.expresspay.cardaccount.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.expresspay.cardaccount.bean.CardAccount;
import com.sevenpay.gateway.jgkj.IJgkj;
import com.sevenpay.gateway.jgkj.RequestHead;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn20010;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn2001RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn2001ResponseBean;

/**
 * 交广科技卡账户信息维护
 * 
 * @author shen
 */
@Service
public class CardAccountService {

	public static Logger logger = LoggerFactory.getLogger(CardAccountService.class);

	@Autowired
	@Qualifier("expressPayHttpInvokerProxy")
	private IJgkj expressPay;

	/**
	 * 卡账户信息查询
	 * 
	 * @param email
	 * @return CardAccount
	 */
	public CardAccount getCardAccountInfo(String cardNo) {
		CardAccount cardAccount = null;

		Txn2001RequestBean request = new Txn2001RequestBean();

		RequestHead head = new RequestHead();
		head.setMerchantSeq(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		head.setTxnDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		head.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
		head.setSysId("S004");
		Txn20010 txn20010 = new Txn20010();
		txn20010.setCardNo(cardNo);

		request.setHead(head);
		request.setBody(txn20010);

		logger.info("卡账户信息查询调用网关请求[{}]", JSONObject.toJSONString(request, true));
		Txn2001ResponseBean response = expressPay.txn2001(request);
		logger.info("卡账户信息查询调用网关返回[{}]", JSONObject.toJSONString(response, true));

		if (null == response || null == response.getBody()) {
			return null;
		}
		if (!StringUtils.isBlank(response.getBody().getCardNo())) {
			cardAccount = new CardAccount();
			cardAccount.setCardNo(response.getBody().getCardNo());
			if (!StringUtils.isBlank(response.getBody().getCdBal())) {
				cardAccount.setCdBal(new BigDecimal(response.getBody().getCdBal()));
			}
			cardAccount.setActiveDay(response.getBody().getActiveDay());
			cardAccount.setCardType(response.getBody().getCardType());
			cardAccount.setStatus(response.getBody().getStatus());
		}
		
		return cardAccount;
	}
}
