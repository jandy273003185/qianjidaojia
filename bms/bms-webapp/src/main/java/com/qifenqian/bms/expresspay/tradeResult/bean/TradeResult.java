package com.qifenqian.bms.expresspay.tradeResult.bean;

import java.io.Serializable;

public class TradeResult implements Serializable {

	private static final long serialVersionUID = -6300295322721919878L;
	
	private String custId;

	private String merchantSeq;

	private String amount;

	private String cardNo;

	private String errCode;

	private String platformSeq;

	private String txnCode;

	private String txnDate;

	private String txnTime;
	
	private String rtnInfo;
	
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMerchantSeq() {
		return merchantSeq;
	}

	public void setMerchantSeq(String merchantSeq) {
		this.merchantSeq = merchantSeq;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getPlatformSeq() {
		return platformSeq;
	}

	public void setPlatformSeq(String platformSeq) {
		this.platformSeq = platformSeq;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getRtnInfo() {
		return rtnInfo;
	}

	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}
	
}
