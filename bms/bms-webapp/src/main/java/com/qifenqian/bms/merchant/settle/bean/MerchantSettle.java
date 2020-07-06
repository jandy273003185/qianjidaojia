package com.qifenqian.bms.merchant.settle.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qifenqian.bms.merchant.settle.type.MerchantSettleStatus;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettle.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo
 */
public class MerchantSettle implements Serializable {

	private static final long serialVersionUID = -8528225287463830382L;

	/** 编号 */
	private String id;
	/** 客户id */
	private String custId;
	/** 商户协议编号 */
	private String merchantCode;
	/** 七分钱执行结算的会计日期（对应存储过程的参数） */
	private String clearId;
	private String workDate;
	// ---------------------
	/** 商户名称 */
	private String merchantName;
	/** 银行卡号 */
	private String bankCardNo;
	/** 银行卡名 */
	private String bankCardName;
	/** 银行信息 */
	private String bankName;

	/** 结算开始日期 */
	private String settleBeginDate;
	/** 结算结束日期 */
	private String settleEndDate;
	/** 协议编号 */
	private String protocolId;
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
	private MerchantSettleStatus status;
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

	// ---------------------
	/** 执行日期-上限 */
	private String workDateMax;
	/** 执行日期-下限 */
	private String workDateMin;

	/** 结算金额是否为0 */
	private String settleAmtIsZero;
	
	/**
	 * 商户类型
	 */
	private String merchantType;
	// -------------------报表字段
	/** 状态 */
	private String statusStr;
	/** 写入时间 */
	private String instDatetimeStr;
	/** 确认时间 */
	private String auditDatetimeStr;
	/** 核销时间 */
	private String verifiedDatetimeStr;
	/** 写入人 */
	private String instUserName;
	/** 复核人 */
	private String auditUserName;
	/** 核销人 */
	private String verifiedUserName;

	/** 联合结算编号 */
	private String togetherId;

	/** 是否联合结算 */
	private String isTogether;

	/** 联合结算明细 */
	private List<MerchantSettle> detailList;
	
	/**
	 * 是否T+0
	 */
	private String isT_O;
	
	private String batNo;
	
	private String tFlag;
	
	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String gettFlag() {
		return tFlag;
	}

	public void settFlag(String tFlag) {
		this.tFlag = tFlag;
	}

	public String getIsT_O() {
		return isT_O;
	}

	public void setIsT_O(String isT_O) {
		this.isT_O = isT_O;
	}

	public String getSettleAmtIsZero() {
		return settleAmtIsZero;
	}

	public void setSettleAmtIsZero(String settleAmtIsZero) {
		this.settleAmtIsZero = settleAmtIsZero;
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

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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

	public MerchantSettleStatus getStatus() {
		return status;
	}

	public void setStatus(MerchantSettleStatus status) {
		this.status = status;
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

	public String getWorkDateMax() {
		return workDateMax;
	}

	public void setWorkDateMax(String workDateMax) {
		this.workDateMax = workDateMax;
	}

	public String getWorkDateMin() {
		return workDateMin;
	}

	public void setWorkDateMin(String workDateMin) {
		this.workDateMin = workDateMin;
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

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getInstDatetimeStr() {
		return instDatetimeStr;
	}

	public void setInstDatetimeStr(String instDatetimeStr) {
		this.instDatetimeStr = instDatetimeStr;
	}

	public String getAuditDatetimeStr() {
		return auditDatetimeStr;
	}

	public void setAuditDatetimeStr(String auditDatetimeStr) {
		this.auditDatetimeStr = auditDatetimeStr;
	}

	public String getVerifiedDatetimeStr() {
		return verifiedDatetimeStr;
	}

	public void setVerifiedDatetimeStr(String verifiedDatetimeStr) {
		this.verifiedDatetimeStr = verifiedDatetimeStr;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getVerifiedUserName() {
		return verifiedUserName;
	}

	public void setVerifiedUserName(String verifiedUserName) {
		this.verifiedUserName = verifiedUserName;
	}

	public String getTogetherId() {
		return togetherId;
	}

	public void setTogetherId(String togetherId) {
		this.togetherId = togetherId;
	}

	public String getIsTogether() {
		return isTogether;
	}

	public void setIsTogether(String isTogether) {
		this.isTogether = isTogether;
	}

	public List<MerchantSettle> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<MerchantSettle> detailList) {
		this.detailList = detailList;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
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

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

}
