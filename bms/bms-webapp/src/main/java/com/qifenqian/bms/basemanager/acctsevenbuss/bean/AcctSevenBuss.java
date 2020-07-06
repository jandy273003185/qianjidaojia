package com.qifenqian.bms.basemanager.acctsevenbuss.bean;

import java.math.BigDecimal;

/**
 * 
 * @author shen
 *
 */
public class AcctSevenBuss {

	
	private String merchantCode;
	private String acctId;
	
	private String acctName;
	
	private String custId;
	
	private String subjectId;
	
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
	
	private String status;
	
	private String instDate;
	
	private String instDatetime;
	
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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
}
