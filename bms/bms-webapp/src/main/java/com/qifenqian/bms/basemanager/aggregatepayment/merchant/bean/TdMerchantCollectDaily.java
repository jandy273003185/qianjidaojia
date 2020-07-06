package com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean;

import java.math.BigDecimal;

public class TdMerchantCollectDaily {

	/** 编号*/            	private int dailyId;
	/** 商户id*/          	private String merchantId;
	/** 执行结算的会计日期*/     	private String workDate;
	/** 产品渠道码*/         	private String channelCode;
	/** 交易类型*/          	private String transType;
	/** 笔数*/            	private int transCount;
	/** 应收金额*/          	private BigDecimal transTotalAmt;
	/** 应付金额*/          	private BigDecimal transTotalFee;
	/** 状态：待清算WAIT_SETTL*/	private String status;
	/** 记账日期*/          	private String instDate;
	/** 生成时间*/          	private String instDatetime;
	private String beginWorkDate;
	private String endWorkDate;
	
	
	public String getBeginWorkDate() {
		return beginWorkDate;
	}
	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}
	public String getEndWorkDate() {
		return endWorkDate;
	}
	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDailyId() {
		return dailyId;
	}
	public void setDailyId(int dailyId) {
		this.dailyId = dailyId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public int getTransCount() {
		return transCount;
	}
	public void setTransCount(int transCount) {
		this.transCount = transCount;
	}
	public BigDecimal getTransTotalAmt() {
		return transTotalAmt;
	}
	public void setTransTotalAmt(BigDecimal transTotalAmt) {
		this.transTotalAmt = transTotalAmt;
	}
	public BigDecimal getTransTotalFee() {
		return transTotalFee;
	}
	public void setTransTotalFee(BigDecimal transTotalFee) {
		this.transTotalFee = transTotalFee;
	}
	public String getInstDate() {
		return instDate;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	
	
}
