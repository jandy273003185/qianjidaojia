package com.qifenqian.bms.expresspay.cardholderInfo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.expresspay.cardholderInfo.bean.Cardholder;
import com.sevenpay.gateway.jgkj.IJgkj;
import com.sevenpay.gateway.jgkj.RequestHead;
import com.sevenpay.gateway.jgkj.impl.txn1201.bean.Txn12010;
import com.sevenpay.gateway.jgkj.impl.txn1201.bean.Txn1201RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn1201.bean.Txn1201ResponseBean;
import com.sevenpay.gateway.jgkj.impl.txn1301.bean.Txn13010;
import com.sevenpay.gateway.jgkj.impl.txn1301.bean.Txn1301RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn1301.bean.Txn1301ResponseBean;


/**
 * 
 * @author shen
 *
 */
@Service
public class CardholderService {

	public static Logger logger = LoggerFactory.getLogger(CardholderService.class);

	@Autowired
	@Qualifier("expressPayHttpInvokerProxy")
	private IJgkj expressPay;
	
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;

	/**
	 * 持卡人信息修改
	 * 
	 * @param cardNo
	 * @return
	 */
	public Cardholder getCardholderInfo(String cardNo) {
		Cardholder cardholder = null;
		try {
			Txn1301RequestBean request = new Txn1301RequestBean();
			RequestHead head = new RequestHead();
			head.setMerchantSeq(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			head.setTxnDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			head.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
			head.setSysId("S004");
			Txn13010 txn13010 = new Txn13010();
			txn13010.setCardNo(cardNo);

			request.setBody(txn13010);
			request.setHead(head);
			logger.info("卡信息查询调用网关请求[{}]", JSONObject.toJSONString(request, true));
			Txn1301ResponseBean response = expressPay.txn1301(request);
			logger.info("卡信息查询调用网关返回[{}]", JSONObject.toJSONString(response, true));

			if (null == response || null == response.getBody()) {
				return null;
			}
			if(!StringUtils.isBlank(response.getBody().getCardNo())){
				cardholder = new Cardholder();
				cardholder.setAddr(response.getBody().getAddr());
				cardholder.setBirthday(response.getBody().getBirthday());
				cardholder.setCardNo(response.getBody().getCardNo());
				cardholder.setEmail(response.getBody().getEmail());
				cardholder.setIdCode(response.getBody().getIdCode());
				cardholder.setIdType(response.getBody().getIdType());
				cardholder.setMobile(response.getBody().getMobile());
				cardholder.setName(response.getBody().getName());
				cardholder.setPost(response.getBody().getPost());
				cardholder.setReserve(response.getBody().getReserve());
			}
			
		} catch (Exception e) {
			logger.error("卡信息查询调用网关异常", e);
			throw e;
		}
		return cardholder;
	}

	/**
	 * 持卡人信息维护
	 * 
	 * @param cardNo
	 * @return
	 */
	public Cardholder updateJgkjCard(Cardholder card) {
		Cardholder cardholder = null;
		try {
			Txn1201RequestBean request = new Txn1201RequestBean();
			RequestHead head = new RequestHead();
			head.setMerchantSeq(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			head.setTxnDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			head.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
			head.setSysId("S004");
			Txn12010 body = new Txn12010();
			body.setCardNo(card.getCardNo());
			body.setBirthday(card.getBirthday());
			body.setEmail(card.getEmail());
			body.setMobile(card.getMobile());
			body.setPost(card.getPost());
			body.setAddr(card.getAddr());
			body.setReserve(card.getReserve());
			body.setOldMobile(card.getOldMobile());
			body.setIdType(card.getIdType());
			body.setIdCode(card.getIdCode());
			body.setName(card.getName());
			
			request.setHead(head);
			request.setBody(body);

			logger.info("卡信息维护调用网关请求[{}]", JSONObject.toJSONString(request, true));
			Txn1201ResponseBean response = expressPay.txn1201(request);
			logger.info("卡信息维护调用网关返回[{}]", JSONObject.toJSONString(response, true));

			if (null == response || null == response.getBody()) {
				return null;
			}
			if (Constant.JGKJ_SUCC.equals(response.getHead().getErrCode())) {
				// 成功操作
				TdLoginUserInfo loginUserInfo = new TdLoginUserInfo();
				loginUserInfo.setCustId(card.getCustId());
				loginUserInfo.setMobile(card.getMobile());
				loginUserInfo.setEmail(card.getEmail());
				
				tdLoginUserInfoMapper.updateLoginUserInfo(loginUserInfo);
				cardholder = new Cardholder();
				cardholder.setCardNo(response.getBody().getCardNo());
			}
		} catch (Exception e) {
			logger.error("卡信息维护调用网关异常", e);
			throw e;
		}
		return cardholder;
	}

}
