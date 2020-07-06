package com.qifenqian.bms.merchant.settle.bean;

import java.math.BigDecimal;

import com.qifenqian.bms.merchant.settle.type.MerchantSettleStatus;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleExcel.java
 * @author huiquan.ma
 * @date 2015年10月14日
 * @memo
 */
public class MerchantSettleExport {

	/** 编号 */
	private String id;
	/** 结算申请核心编号 */
	private String applyCoreId;
	/**
	 * 批次号
	 */
	private String batNo;
	private String clearId;
	/** 客户id */
	private String merchantCode;
	/** 商户名称 */
	private String merchantName;
	/** 七分钱执行结算的会计日期（对应存储过程的参数） */
	private String workDate;
	/** 结算开始日期 */
	private String settleBeginDate;
	/** 结算结束日期 */
	private String settleEndDate;
	/** 收款笔数 */
	private int receiveCount;
	/** 收款总额 */
	private BigDecimal receiveTotalAmt;
	/** 收款总费用 */
	private BigDecimal receiveTotalFee;
	/** 撤销笔数 */
	private int revokeCount;
	/** 撤销总额 */
	private BigDecimal revokeTotalAmt;
	/** 撤销总费用 */
	private BigDecimal revokeTotalFee;
	/** 退款笔数 */
	private int refundCount;
	/** 退款总额 */
	private BigDecimal refundTotalAmt;
	/** 退款总费用 */
	private BigDecimal refundTotalFee;
	/** 提现笔数 */
	private int withdrawCount;
	/** 提现总额 */
	private BigDecimal withdrawTotalAmt;
	/** 提现总费用 */
	private BigDecimal withdrawTotalFee;
	/** 转入笔数 */
	private int transferInCount;
	/** 转入总额 */
	private BigDecimal transferInTotalAmt;
	/** 转入总费用 */
	private BigDecimal transferInTotalFee;
	/** 转出笔数 */
	private int transferOutCount;
	/** 转出总额 */
	private BigDecimal transferOutTotalAmt;
	/** 转出总费用 */
	private BigDecimal transferOutTotalFee;
	/** 商户应收金额 */
	private BigDecimal merchantReceivable;
	/** 商户应付金额 */
	private BigDecimal merchantPayable;
	/** 结算金额 */
	private BigDecimal settleAmt;
	/** 完成日期 */
	private String finishDate;
	/** 状态 */
	private String status;
	/** 协议编号 */
	private String protocolId;
	/** 银行卡号 */
	private String bankCardNo;
	/** 银行卡名 */
	private String bankCardName;
	/** 银行信息 */
	private String bankName;
	/** 备注 */
	private String memo;
	/** 生成人 */
	private String instUserName;
	/** 记账日期 */
	private String instDate;
	/** 生成时间 */
	private String instDatetime;
	/** 复核人 */
	private String auditUserName;
	/** 复核时间 */
	private String auditDatetime;
	/** 核销人 */
	private String verifiedUserName;
	/** 核销时间 */
	private String verifiedDatetime;
	
	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getApplyCoreId() {
		return applyCoreId;
	}

	public void setApplyCoreId(String applyCoreId) {
		this.applyCoreId = applyCoreId;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public int getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(int receiveCount) {
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

	public int getRevokeCount() {
		return revokeCount;
	}

	public void setRevokeCount(int revokeCount) {
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

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
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

	public int getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(int withdrawCount) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = MerchantSettleStatus.valueOf(status).getDesc();
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
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

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getAuditDatetime() {
		return auditDatetime;
	}

	public void setAuditDatetime(String auditDatetime) {
		this.auditDatetime = auditDatetime;
	}

	public String getVerifiedUserName() {
		return verifiedUserName;
	}

	public void setVerifiedUserName(String verifiedUserName) {
		this.verifiedUserName = verifiedUserName;
	}

	public String getVerifiedDatetime() {
		return verifiedDatetime;
	}

	public void setVerifiedDatetime(String verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}

	public int getTransferInCount() {
		return transferInCount;
	}

	public BigDecimal getTransferInTotalAmt() {
		return transferInTotalAmt;
	}

	public BigDecimal getTransferInTotalFee() {
		return transferInTotalFee;
	}

	public int getTransferOutCount() {
		return transferOutCount;
	}

	public BigDecimal getTransferOutTotalAmt() {
		return transferOutTotalAmt;
	}

	public BigDecimal getTransferOutTotalFee() {
		return transferOutTotalFee;
	}

	public void setTransferInCount(int transferInCount) {
		this.transferInCount = transferInCount;
	}

	public void setTransferInTotalAmt(BigDecimal transferInTotalAmt) {
		this.transferInTotalAmt = transferInTotalAmt;
	}

	public void setTransferInTotalFee(BigDecimal transferInTotalFee) {
		this.transferInTotalFee = transferInTotalFee;
	}

	public void setTransferOutCount(int transferOutCount) {
		this.transferOutCount = transferOutCount;
	}

	public void setTransferOutTotalAmt(BigDecimal transferOutTotalAmt) {
		this.transferOutTotalAmt = transferOutTotalAmt;
	}

	public void setTransferOutTotalFee(BigDecimal transferOutTotalFee) {
		this.transferOutTotalFee = transferOutTotalFee;
	}

}
