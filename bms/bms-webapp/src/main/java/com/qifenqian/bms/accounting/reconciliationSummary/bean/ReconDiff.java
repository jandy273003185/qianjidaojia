package com.qifenqian.bms.accounting.reconciliationSummary.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReconDiff {
	//id
	private int Id;
	
	//订单号
	private String orderId;
	
	//支付渠道
	private String paychannelType;
	
	//交易金额
	private String tradeAmt;
	
	//交易结果
	private String reconResult;
	
	//交易状态
	private String inOut;
	
	//对账时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	
	//交易时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tradeTime;
	
	//对账日期起始时间
	private String createTimeMin;
	
	//对账日期结束时间
	private String createTimeMax;
	
	
	//差错标记
	private String dealFlag;
	
	
	
	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getCreateTimeMin() {
		return createTimeMin;
	}

	public void setCreateTimeMin(String createTimeMin) {
		this.createTimeMin = createTimeMin;
	}

	public String getCreateTimeMax() {
		return createTimeMax;
	}

	public void setCreateTimeMax(String createTimeMax) {
		this.createTimeMax = createTimeMax;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaychannelType() {
		return paychannelType;
	}

	public void setPaychannelType(String paychannelType) {
		this.paychannelType = paychannelType;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getReconResult() {
		return reconResult;
	}

	public void setReconResult(String reconResult) {
		this.reconResult = reconResult;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
