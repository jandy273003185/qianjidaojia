package com.qifenqian.bms.expresspay.tradeDetail.bean;

import java.io.Serializable;
/**
 * 
 * @author shen
 *
 */
public class TradeDetail implements Serializable {

	private static final long serialVersionUID = 3840005223177518795L;
	
	private String custId;

	private String merchantSeq;

	private String amount;

	private String cardNo;

	private String errCode;

	private String platformSeq;

	private String txnCode;

	private String txnDate;

	private String txnTime;
	
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

}
