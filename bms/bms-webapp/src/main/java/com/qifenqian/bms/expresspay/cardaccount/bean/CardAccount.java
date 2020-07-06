package com.qifenqian.bms.expresspay.cardaccount.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CardAccount implements Serializable{

	private static final long serialVersionUID = -4714454664544153989L;
	
	private String mobile;
	
	/**
	 * 客户号
	 */
	private String custId;

	/**
	 * 卡号
	 */
	private String cardNo;
	
	/**
	 * 卡余额
	 */
	private BigDecimal cdBal;
	
	/**
	 * 卡状态
	 */
	private String status;
	
	/**
	 * 卡类型
	 */
	private String cardType;
	
	
	
	/**
	 * 卡片激活日期
	 */
	
	private String activeDay;
	
	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getCdBal() {
		return cdBal;
	}

	public void setCdBal(BigDecimal cdBal) {
		this.cdBal = cdBal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getActiveDay() {
		return activeDay;
	}

	public void setActiveDay(String activeDay) {
		this.activeDay = activeDay;
	}
	
	
	
}
