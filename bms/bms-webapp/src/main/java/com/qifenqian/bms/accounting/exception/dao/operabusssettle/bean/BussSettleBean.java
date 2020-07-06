package com.qifenqian.bms.accounting.exception.dao.operabusssettle.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;

public class BussSettleBean extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1065529293374343959L;

	/** 编号 */
	private String id;
	/** 客户id */
	private String custId;
	/** 七分钱执行结算的会计日期（对应存储过程的参数） */
	private String workDate;
	/** 结算开始日期 */
	private String settleBeginDate;
	/** 结算结束日期 */
	private String settleEndDate;
	/** 协议编号 */
	private String protocolId;
	/** 收款笔数 */
	private String receiveCount;
	/** 收款总额 */
	private BigDecimal receiveTotalAmt;
	/** 收款总费用 */
	private BigDecimal receiveTotalFee;
	/** 撤销笔数 */
	private String revokeCount;
	/** 撤销总额 */
	private BigDecimal revokeTotalAmt;
	/** 撤销总费用 */
	private BigDecimal revokeTotalFee;
	/** 退款笔数 */
	private String refundCount;
	/** 退款总额 */
	private BigDecimal refundTotalAmt;
	/** 退款总费用 */
	private BigDecimal refundTotalFee;
	/** 提现笔数 */
	private String withdrawCount;
	/** 提现总额 */
	private BigDecimal withdrawTotalAmt;
	/** 提现总费用 */
	private BigDecimal withdrawTotalFee;
	/** 商户应收金额 */
	private BigDecimal merchantReceivable;
	/** 商户应付金额 */
	private BigDecimal merchantPayable;
	/** 结算金额 */
	private BigDecimal settleAmt;
	/** 完成日期 */
	private String finishDate;
	/** 备注 */
	private String memo;
	/** 生成人 */
	private String instUser;
	/** 记账日期 */
	private String instDate;
	/** 生成时间 */
	private Date instDatetime;
	/** 复核人 */
	private String auditUser;
	/** 复核时间 */
	private Date auditDatetime;
	/** 核销人 */
	private String verifiedUser;
	/** 核销时间 */
	private Date verifiedDatetime;
	/** 结算申请核心编号 */
	private String applyCoreId;
	/** 结算核心编号 */
	private String settleCoreId;
	/** 商户名称 */
	private String merchantName;
	/** 银行卡号 */
	private String bankCardNo;
	/** 银行卡名 */
	private String bankCardName;
	/** 银行信息 */
	private String bankName;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String coreReturnCode;
	/** 核心返回信息 */
	private String coreReturnMsg;

	private String businessType;
	private String settleStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getSettleBeginDate() {
		return settleBeginDate;
	}

	public void setSettleBeginDate(String settleBeginDate) {
		this.settleBeginDate = settleBeginDate;
	}

	public String getSettleEndDate() {
		return settleEndDate;
	}

	public void setSettleEndDate(String settleEndDate) {
		this.settleEndDate = settleEndDate;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(String receiveCount) {
		this.receiveCount = receiveCount;
	}

	public BigDecimal getReceiveTotalAmt() {
		return receiveTotalAmt;
	}

	public void setReceiveTotalAmt(BigDecimal receiveTotalAmt) {
		this.receiveTotalAmt = receiveTotalAmt;
	}

	public BigDecimal getReceiveTotalFee() {
		return receiveTotalFee;
	}

	public void setReceiveTotalFee(BigDecimal receiveTotalFee) {
		this.receiveTotalFee = receiveTotalFee;
	}

	public String getRevokeCount() {
		return revokeCount;
	}

	public void setRevokeCount(String revokeCount) {
		this.revokeCount = revokeCount;
	}

	public BigDecimal getRevokeTotalAmt() {
		return revokeTotalAmt;
	}

	public void setRevokeTotalAmt(BigDecimal revokeTotalAmt) {
		this.revokeTotalAmt = revokeTotalAmt;
	}

	public BigDecimal getRevokeTotalFee() {
		return revokeTotalFee;
	}

	public void setRevokeTotalFee(BigDecimal revokeTotalFee) {
		this.revokeTotalFee = revokeTotalFee;
	}

	public String getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(String refundCount) {
		this.refundCount = refundCount;
	}

	public BigDecimal getRefundTotalAmt() {
		return refundTotalAmt;
	}

	public void setRefundTotalAmt(BigDecimal refundTotalAmt) {
		this.refundTotalAmt = refundTotalAmt;
	}

	public BigDecimal getRefundTotalFee() {
		return refundTotalFee;
	}

	public void setRefundTotalFee(BigDecimal refundTotalFee) {
		this.refundTotalFee = refundTotalFee;
	}

	public String getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(String withdrawCount) {
		this.withdrawCount = withdrawCount;
	}

	public BigDecimal getWithdrawTotalAmt() {
		return withdrawTotalAmt;
	}

	public void setWithdrawTotalAmt(BigDecimal withdrawTotalAmt) {
		this.withdrawTotalAmt = withdrawTotalAmt;
	}

	public BigDecimal getWithdrawTotalFee() {
		return withdrawTotalFee;
	}

	public void setWithdrawTotalFee(BigDecimal withdrawTotalFee) {
		this.withdrawTotalFee = withdrawTotalFee;
	}

	public BigDecimal getMerchantReceivable() {
		return merchantReceivable;
	}

	public void setMerchantReceivable(BigDecimal merchantReceivable) {
		this.merchantReceivable = merchantReceivable;
	}

	public BigDecimal getMerchantPayable() {
		return merchantPayable;
	}

	public void setMerchantPayable(BigDecimal merchantPayable) {
		this.merchantPayable = merchantPayable;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDatetime() {
		return auditDatetime;
	}

	public void setAuditDatetime(Date auditDatetime) {
		this.auditDatetime = auditDatetime;
	}

	public String getVerifiedUser() {
		return verifiedUser;
	}

	public void setVerifiedUser(String verifiedUser) {
		this.verifiedUser = verifiedUser;
	}

	public Date getVerifiedDatetime() {
		return verifiedDatetime;
	}

	public void setVerifiedDatetime(Date verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}

	public String getApplyCoreId() {
		return applyCoreId;
	}

	public void setApplyCoreId(String applyCoreId) {
		this.applyCoreId = applyCoreId;
	}

	public String getSettleCoreId() {
		return settleCoreId;
	}

	public void setSettleCoreId(String settleCoreId) {
		this.settleCoreId = settleCoreId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	
}
