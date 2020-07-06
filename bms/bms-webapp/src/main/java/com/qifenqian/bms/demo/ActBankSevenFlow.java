package com.qifenqian.bms.demo;

import java.io.Serializable;

/**
 * @project sevenpay-bms-web
 * @fileName ActBankSevenFlow.java
 * @author huiquan.ma
 * @date 2015年7月22日
 * @memo 
 */
public class ActBankSevenFlow implements Serializable{

	private static final long serialVersionUID = 2639681966224703842L;
	
	private String onlyId;
	private String transId;
	private String acctId;
	private String cdFlag;
	private String currCode;
	private String transAmt;
	private String insertTime;
	private String workDate;
	private String remark;
	public String getOnlyId() {
		return onlyId;
	}
	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getCdFlag() {
		return cdFlag;
	}
	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}


