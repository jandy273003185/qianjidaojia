package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

public class TdPayCreditAuditInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6770847527605229737L;
	
	
	private String   id;
	private String  creditId;//'凭证编号',
	private String  status; //'审核状态 : 01 清算审核不通过 ， 02 清算审核通过，03 财务审核不通过 ， 00 财务审核通过',
	private String  auditTime;//'审核时间',
	private String  memo; // '审核描述',
	private String  auditUser; // '审核人',
	private String fristAuditTime;
	private String secondAuditTime;
	private String auditAmt;
	private String email;
	private String mobile;
	private String custName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getFristAuditTime() {
		return fristAuditTime;
	}
	public void setFristAuditTime(String fristAuditTime) {
		this.fristAuditTime = fristAuditTime;
	}
	public String getSecondAuditTime() {
		return secondAuditTime;
	}
	public void setSecondAuditTime(String secondAuditTime) {
		this.secondAuditTime = secondAuditTime;
	}
	public String getAuditAmt() {
		return auditAmt;
	}
	public void setAuditAmt(String auditAmt) {
		this.auditAmt = auditAmt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

}
