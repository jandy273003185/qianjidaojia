package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class BalSevenResultEqual extends BalSevenResultEqualBase {

	private String balDate;
	private String workDate;
	private String channelTime;
	private String sevenTime;
	private String channelId;
	private String rtnSeq;
	private String clearId;
	private BigDecimal transAmt;
	private BigDecimal transAmtc;

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

	public String getChannelTime() {
		return channelTime;
	}

	public void setChannelTime(String channelTime) {
		this.channelTime = channelTime;
	}

	public String getSevenTime() {
		return sevenTime;
	}

	public void setSevenTime(String sevenTime) {
		this.sevenTime = sevenTime;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRtnSeq() {
		return rtnSeq;
	}

	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public BigDecimal getTransAmtc() {
		return transAmtc;
	}

	public void setTransAmtc(BigDecimal transAmtc) {
		this.transAmtc = transAmtc;
	}

}