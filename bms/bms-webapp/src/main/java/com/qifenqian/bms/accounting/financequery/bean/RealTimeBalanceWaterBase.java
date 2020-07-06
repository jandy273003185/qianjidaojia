package com.qifenqian.bms.accounting.financequery.bean;

import java.io.Serializable;

import com.sevenpay.invoke.common.type.RequestColumnValues;

public class RealTimeBalanceWaterBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238607334560147739L;
	private String custId;
	private RequestColumnValues.BusinessType businessType;
	private String loanFlag;
	private String beginWorkDate;
	private String endWorkDate;
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public RequestColumnValues.BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(RequestColumnValues.BusinessType businessType) {
		this.businessType = businessType;
	}
	public String getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
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
}
