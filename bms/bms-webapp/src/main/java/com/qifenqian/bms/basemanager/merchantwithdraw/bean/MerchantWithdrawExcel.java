package com.qifenqian.bms.basemanager.merchantwithdraw.bean;

import java.math.BigDecimal;

public class MerchantWithdrawExcel {
	private String withdrawSn;
	private String custId;
	private String custName;
	private String bankAcctName;
	private String bankCode;
	private String bankName;
	private BigDecimal withdrawAmt;
	private BigDecimal fee;
	private String createtime;
	private String auditstate;
	private String withdrawstate;
	private String verificationstate;
	private String modifyId;
	private String auditTime;
	private String verificationUser;
	private String verificationdate;

	public String getWithdrawSn() {
		return withdrawSn;
	}

	public void setWithdrawSn(String withdrawSn) {
		this.withdrawSn = withdrawSn;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getWithdrawAmt() {
		return withdrawAmt;
	}

	public void setWithdrawAmt(BigDecimal withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}

	public String getWithdrawstate() {
		return withdrawstate;
	}

	public void setWithdrawstate(String withdrawstate) {
		this.withdrawstate = withdrawstate;
	}

	public String getVerificationstate() {
		return verificationstate;
	}

	public void setVerificationstate(String verificationstate) {
		this.verificationstate = verificationstate;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	public String getVerificationUser() {
		return verificationUser;
	}

	public void setVerificationUser(String verificationUser) {
		this.verificationUser = verificationUser;
	}
	public String getVerificationdate() {
		return verificationdate;
	}

	public void setVerificationdate(String verificationdate) {
		this.verificationdate = verificationdate;
	}

}
