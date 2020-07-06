package com.qifenqian.bms.basemanager.acctsevencust.bean;

import java.math.BigDecimal;

public class AcctSevenCust {
	private String mobile;
	/** 账号 */
	private String acctId;
	/** 户名 */
	private String acctName;
	/** 客户号 */
	private String custId;
	/** 科目编号 */
	private String subjectId;
	/** 币别 */
	private String currCode;
	/** 余额 */
	private BigDecimal balance;
	/** 余额方向 */
	private String balanceFlag;
	/** 冻结金额 */
	private BigDecimal freezeAmt;
	/** 在途金额 */
	private BigDecimal onwayAmt;
	/** 可用余额 */
	private BigDecimal usableAmt;
	/** 开户日期 */
	private String instDate;
	/** 开户时间 */
	private String instDatetime;
	/** 交广科技卡号 */
	private String jgkjCardNo;
	
	/** 状态：NORMAL/FREEZE/CANCEL */
	private String status;
	
	private String createBeginTime;
	private String createEndTime;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getBalanceFlag() {
		return balanceFlag;
	}
	public void setBalanceFlag(String balanceFlag) {
		this.balanceFlag = balanceFlag;
	}
	public BigDecimal getFreezeAmt() {
		return freezeAmt;
	}
	public void setFreezeAmt(BigDecimal freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	public BigDecimal getOnwayAmt() {
		return onwayAmt;
	}
	public void setOnwayAmt(BigDecimal onwayAmt) {
		this.onwayAmt = onwayAmt;
	}
	public BigDecimal getUsableAmt() {
		return usableAmt;
	}
	public void setUsableAmt(BigDecimal usableAmt) {
		this.usableAmt = usableAmt;
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
	
	public String getJgkjCardNo() {
		return jgkjCardNo;
	}
	public void setJgkjCardNo(String jgkjCardNo) {
		this.jgkjCardNo = jgkjCardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBeginTime() {
		return createBeginTime;
	}
	public void setCreateBeginTime(String createBeginTime) {
		this.createBeginTime = createBeginTime;
	}
	public String getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}
	
}
