package com.qifenqian.bms.accounting.withdraw.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提现业务表
 * 
 * @author pc
 * 
 */
public class Withdraw implements Serializable {

	private static final long serialVersionUID = -4044510311102213473L;
	private String withdrawSn;
	private String withdrawReqserialid;
	private String custId;
	private String custName;
	private String withdrawAcctType;
	private String cardNo;
	private String bnakAcctNoInternal;
	private String bankType;
	private String bankCode;
	private String bankName;
	private String bankAcctName;
	private String bankBranchName;
	private String currCode;
	private BigDecimal withdrawAmt;
	private String withdrawType;
	private String opTerm;
	private String withdrawState;
	private BigDecimal fee;
	private String feeCustId;
	private String feeState;
	private String coreSn;
	private String coreReturnCode;
	private String coreReturnMsg;
	private String submitTime;
	private String resultReturnTime;
	private String createId;
	private String createTime;
	private String modifyId;
	private String auditTime;
	private String modifyTime;
	private String auditState;
	private String verificationDate;
	private String verificationState;
	private String verificationUser;
	private String mobile;
	private String tradeId;
	private String clearId;

	public String getWithdrawSn() {
		return withdrawSn;
	}

	public void setWithdrawSn(String withdrawSn) {
		this.withdrawSn = withdrawSn;
	}

	public String getWithdrawReqserialid() {
		return withdrawReqserialid;
	}

	public void setWithdrawReqserialid(String withdrawReqserialid) {
		this.withdrawReqserialid = withdrawReqserialid;
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

	public String getWithdrawAcctType() {
		return withdrawAcctType;
	}

	public void setWithdrawAcctType(String withdrawAcctType) {
		this.withdrawAcctType = withdrawAcctType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBnakAcctNoInternal() {
		return bnakAcctNoInternal;
	}

	public void setBnakAcctNoInternal(String bnakAcctNoInternal) {
		this.bnakAcctNoInternal = bnakAcctNoInternal;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
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

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getWithdrawAmt() {
		return withdrawAmt;
	}

	public void setWithdrawAmt(BigDecimal withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}

	public String getWithdrawType() {
		return withdrawType;
	}

	public void setWithdrawType(String withdrawType) {
		this.withdrawType = withdrawType;
	}

	public String getOpTerm() {
		return opTerm;
	}

	public void setOpTerm(String opTerm) {
		this.opTerm = opTerm;
	}

	public String getWithdrawState() {
		return withdrawState;
	}

	public void setWithdrawState(String withdrawState) {
		this.withdrawState = withdrawState;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getFeeCustId() {
		return feeCustId;
	}

	public void setFeeCustId(String feeCustId) {
		this.feeCustId = feeCustId;
	}

	public String getFeeState() {
		return feeState;
	}

	public void setFeeState(String feeState) {
		this.feeState = feeState;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public String getCoreReturnCode() {
		return coreReturnCode;
	}

	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}

	public String getCoreReturnMsg() {
		return coreReturnMsg;
	}

	public void setCoreReturnMsg(String coreReturnMsg) {
		this.coreReturnMsg = coreReturnMsg;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getResultReturnTime() {
		return resultReturnTime;
	}

	public void setResultReturnTime(String resultReturnTime) {
		this.resultReturnTime = resultReturnTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getVerificationState() {
		return verificationState;
	}

	public void setVerificationState(String verificationState) {
		this.verificationState = verificationState;
	}

	public String getVerificationUser() {
		return verificationUser;
	}

	public void setVerificationUser(String verificationUser) {
		this.verificationUser = verificationUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

}
