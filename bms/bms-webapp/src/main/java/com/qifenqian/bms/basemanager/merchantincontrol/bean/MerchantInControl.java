package com.qifenqian.bms.basemanager.merchantincontrol.bean;

import java.io.Serializable;
import java.util.Date;

public class MerchantInControl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9164780757518826891L;

	private String custId;

	private String tradeType;

	private String isSupportCreditCard;

	private String bandIp;

	private String merchantCertFilePath;

	private String merchantPubKey;

	private Date createrTime;

	private String creater;

	private String modified;

	private Date modifiedTime;

	private String custName;

	private String comment;

	private String merchantCode;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getIsSupportCreditCard() {
		return isSupportCreditCard;
	}

	public void setIsSupportCreditCard(String isSupportCreditCard) {
		this.isSupportCreditCard = isSupportCreditCard;
	}

	public String getBandIp() {
		return bandIp;
	}

	public void setBandIp(String bandIp) {
		this.bandIp = bandIp;
	}

	public String getMerchantCertFilePath() {
		return merchantCertFilePath;
	}

	public void setMerchantCertFilePath(String merchantCertFilePath) {
		this.merchantCertFilePath = merchantCertFilePath;
	}

	public String getMerchantPubKey() {
		return merchantPubKey;
	}

	public void setMerchantPubKey(String merchantPubKey) {
		this.merchantPubKey = merchantPubKey;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

}
