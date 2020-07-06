package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

public class AcctSevenBussAgentpay  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313812373539432240L;
	
	
	private String    acctId; // '账号',
	private String    acctName; // '户名',
	private String    custId; //'客户号',
	private String    subjectId;//'科目编号',
	private String    currCode; // '币别',
	private String    balance; // '余额：',
	private String    balanceFlag; //'余额方向',
	private String    freezeAmt; // '冻结金额：',
	private String    onwayAmt; // '在途金额',
	private String    usableAmt; // '可用余额',
	private String    status; // '状态：NORMAL/FREEZE/CANCEL',
	private String    instDate; // '开户日期',
	private String    instDatetime; // '开户时间',
	
	private String email;
	
	private String  mobile;
	
	private String custName;
	
	private String startCreateTime;
	
	private String endCreateTime;
	
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBalanceFlag() {
		return balanceFlag;
	}
	public void setBalanceFlag(String balanceFlag) {
		this.balanceFlag = balanceFlag;
	}
	public String getFreezeAmt() {
		return freezeAmt;
	}
	public void setFreezeAmt(String freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	public String getOnwayAmt() {
		return onwayAmt;
	}
	public void setOnwayAmt(String onwayAmt) {
		this.onwayAmt = onwayAmt;
	}
	public String getUsableAmt() {
		return usableAmt;
	}
	public void setUsableAmt(String usableAmt) {
		this.usableAmt = usableAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getStartCreateTime() {
		return startCreateTime;
	}
	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	
}
