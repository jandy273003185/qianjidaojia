package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TdPayInInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***费用编号**/
	private String feeId;
	/***费用代码（待清算金额可用比例：1001；垫资费率（工作日）：1002；垫资费率（非工作日）：1003；代付手续费：1004）***/
	private String feeCode;
	/***费用名称***/
	private String feeName;
	/***费率***/
	private BigDecimal feeRate;
	/***描述***/
	private String memo;
	/***状态***/
	private String status;
	/***商户编号***/
	private String merchantCode;
	public String getFeeId() {
		return feeId;
	}
	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	
}
