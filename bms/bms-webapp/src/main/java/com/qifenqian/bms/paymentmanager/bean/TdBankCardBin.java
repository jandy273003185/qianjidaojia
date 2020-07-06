package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

public class TdBankCardBin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 卡bin号
	 */
	private String cardBin = null;
	/**
	 * 卡名称
	 */
	private String cardName = null;
	/**
	 * 卡类型
	 */
	private String cardType = null;
	/**
	 * 银行号
	 */
	private String bankCode = null;
	/**
	 * 银行名称
	 */
	private String bankName = null;
	/**
	 * 
	 */
	private String createTime = null;

	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
