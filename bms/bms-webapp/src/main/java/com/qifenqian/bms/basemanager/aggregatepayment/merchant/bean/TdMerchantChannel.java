package com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean;

import java.math.BigDecimal;

public class TdMerchantChannel {

	/** 商户ID*/          	private String mchId;
	/** 渠道*/            	private String chanel;
	/** 渠道状态，0关闭，1打开*/  	private String chanelStatus;
	/** 交易限额*/          	private BigDecimal limitPerAmt;
	/** 交易总限额*/            private BigDecimal limitTotAmt;
	/** 修改时间*/             private String modifyTime;
	/** 修改人*/              	private String modifyId;
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getChanel() {
		return chanel;
	}
	public void setChanel(String chanel) {
		this.chanel = chanel;
	}
	public String getChanelStatus() {
		return chanelStatus;
	}
	public void setChanelStatus(String chanelStatus) {
		this.chanelStatus = chanelStatus;
	}
	public BigDecimal getLimitPerAmt() {
		return limitPerAmt;
	}
	public void setLimitPerAmt(BigDecimal limitPerAmt) {
		this.limitPerAmt = limitPerAmt;
	}
	public BigDecimal getLimitTotAmt() {
		return limitTotAmt;
	}
	public void setLimitTotAmt(BigDecimal limitTotAmt) {
		this.limitTotAmt = limitTotAmt;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
