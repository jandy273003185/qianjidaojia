package com.qifenqian.bms.basemanager.payIn.bean;

import java.io.Serializable;

public class PayIn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1541879315610527443L;
	
	//费用代码
	private String feeCode;
	
	//费用名称
	private String feeName;
	
	//费率
	private String feeRate;
	
	//描述
	private String memo;
	
	//状态
	private String status;
	
	//商户编号
	private String merchantCode;
	
	

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
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
	
	
	

}
