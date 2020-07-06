package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class BalSevenResultException extends BalSevenResultExceptionBase {

	private String balDate;
	private String workDate;
	private String channelId;
	private String clearId;
	private String platformId;
	private String transType;
	private BigDecimal transAmt;
	private BigDecimal transAmtC;
	private String sendTime;
	private String resultTime;
	private String balMemo;

	public String getBalDate() {
		return balDate;
	}

	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public BigDecimal getTransAmtC() {
		return transAmtC;
	}

	public void setTransAmtC(BigDecimal transAmtC) {
		this.transAmtC = transAmtC;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getResultTime() {
		return resultTime;
	}

	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}

	public String getBalMemo() {
		return balMemo;
	}

	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}

}