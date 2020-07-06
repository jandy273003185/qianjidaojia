package com.qifenqian.bms.accounting.exception.dao.opercustwithdraw.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.sevenpay.invoke.common.type.RequestColumnValues;

public class CustWithdrawBean extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7800689413214498132L;

	/** 提现流水号 */
	private String withdrawSn;
	
	private String withdrawReqserialid;
	/** 客户编号 */
	private String custId;
	/** 客户名 */
	private String custName;
	/** 需提现的账户类型：SEVN_CUS_0 七分钱账户； */
	private String withdrawAcctType;
	/** 提现卡号 */
	private String cardNo;
	/** 银行卡号（内部ID） */
	private String bnakAcctNoInternal;
	/** 行别 */
	private String bankType;
	/** 行号 */
	private String bankCode;
	/** 行名 */
	private String bankName;
	/** 户名 */
	private String bankAcctName;
	/** 银行卡所属支行 */
	private String bankBranchName;
	/** 币别 */
	private RequestColumnValues.CurrCode currCode;
	/** 金额 */
	private BigDecimal withdrawAmt;
	/**
	 * 提现类型:1-实时提现 2-非实时提现
	 */
	private String withdrawType;
	/** 操作终端：P：PC端；M：移动手机端 */
	private String opTerm;
	/** 提现状态：01 待提现；02 提交核心；03核心已受理；04 核心返回失败；00 提现成功 */
	private String withdrawState;
	/** 手续费：事前收的手续费 */
	private BigDecimal fee;
	/** 付手续费方 */
	private String feeCustId;
	/**
	 * 手续费状态：00事前未统计 01事前已统计
	 */
	private String feeState;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String coreReturnCode;
	/** 核心返回信息 */
	private String coreReturnMsg;
	/** 提交日期 */
	private Date submitTime;
	/** 最终结果返回日期 */
	private Date resultReturnTime;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private Date modifyTime;
	/** */
	private String auditState;
	/** */
	private Date verificationDate;
	/** */
	private String verificationState;
	/** 核销人 */
	private String verificationUser;

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

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
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

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getResultReturnTime() {
		return resultReturnTime;
	}

	public void setResultReturnTime(Date resultReturnTime) {
		this.resultReturnTime = resultReturnTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
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

}
