package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

/**
 * 
 * @author 代付审核记录表
 *
 */
public class TdPaymentAuditRecode implements  Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  id;
	private String  batNO;
	private String  auditStatus;//'01 前台审核通过 ；02 前台审核未通过；06清洁算审核通过 ； 05清洁算审核未通过;00 财务审核通过 ； 04财务审核未通过;07已撤销',
	private String  auditTime ;
	private String  memo; //'审核内容',
	private String  auditUser;
	private String  custName;
	
	private String paerAcctNo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBatNO() {
		return batNO;
	}
	public void setBatNO(String batNO) {
		this.batNO = batNO;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPaerAcctNo() {
		return paerAcctNo;
	}
	public void setPaerAcctNo(String paerAcctNo) {
		this.paerAcctNo = paerAcctNo;
	}
}
