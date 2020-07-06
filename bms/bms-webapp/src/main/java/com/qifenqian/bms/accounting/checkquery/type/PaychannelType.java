/**
 * Copyright (c) 2011-2014 All Rights Reserved.
 */
package com.qifenqian.bms.accounting.checkquery.type;

/**
 * 支付渠道类型
 * 支付方式 账户（虚户）:ACCOUNT, 储值卡,预付费卡: STORED_CARD, 银行借记卡:DEBIT_CARD, 信用卡:
 * CREDIT_CARD
 *
 * @author fubin
 */
public enum PaychannelType {
	/**
	 * 工商银行
	 */
	ICBC("工商银行"),

	/**
	 * 农业银行
	 */
	ABC("农业银行"),
	
	/**
	 * 中信-威富通
	 */
	CNCB_SWIFT("中信-威富通"),
	
	/**
	 * 浦发-威富通
	 */
	SPD_QHWR("浦发-前海万融");


	private String text;

	PaychannelType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	/**
	 * 根据支付渠道返回序列长度
	 * @param type
	 * @return
	 */
	public static int getSeqLen(PaychannelType type) {
		int len = 10;
		if(CNCB_SWIFT == type) len = 20;
		if(SPD_QHWR == type) len = 20;
		
		return len;
	}
}