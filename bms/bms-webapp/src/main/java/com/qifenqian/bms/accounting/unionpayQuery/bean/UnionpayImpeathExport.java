package com.qifenqian.bms.accounting.unionpayQuery.bean;

import java.math.BigDecimal;
import java.util.Date;

public class UnionpayImpeathExport  {
	
	/**
	 * 
	 */
	

	private String fileId;

    private Integer seq;
    
   
	  
	/**
	 * 会计日期(数据日期)
	 */
    private String workDate;

	/**
	 * 批次编号
	 */
    private String batchId;

	/**
	 * 交易代码
	 */
    private String txnCode;

	/**
	 * 代理机构标识码
	 */
    private String acqInsCode;

	/**
	 * 发送机构标识码
	 */
    private String issInsCode;

	/**
	 * 系统跟踪号
	 */
    private String traceNo;

	/**
	 * 交易传输时间
	 */
    private String txnTime;

	/**
	 * 帐号
	 */
    private String payCardNo;

	/**
	 * 交易金额
	 */
    private BigDecimal txnAmt;

	/**
	 * 商户类别
	 */
    private String merCatCode;

	/**
	 * 终端类型
	 */
    private String termType;

	/**
	 * 查询流水号
	 */
    private String queryId;

	/**
	 * 支付方式（旧）
	 */
    private String oldPayType;

	/**
	 * 商户订单号
	 */
    private String orderId;

	/**
	 * 支付卡类型
	 */
    private String payCardType;

	/**
	 * 原始交易的系统跟踪号
	 */
    private String origTraceNo;

	/**
	 * 原始交易日期时间
	 */
    private String origTxnTime;

	/**
	 * 商户手续费X+n12标示D/C
	 */
    private String mchntFeeFlag;

	/**
	 * 商户手续费X+n12
	 */
    private BigDecimal mchntFee;

	/**
	 * 结算金额X+n12标示D/C
	 */
    private String settleAmtFlag;

	/**
	 * 结算金额X+n12
	 */
    private BigDecimal settleAmt;

	/**
	 * 支付方式
	 */
    private String payType;

	/**
	 * 集团商户代码
	 */
    private String groupMerId;

	/**
	 * 交易类型
	 */
    private String txnType;

	/**
	 * 交易子类
	 */
    private String txnSubType;

	/**
	 * 业务类型
	 */
    private String bizType;

	/**
	 * 帐号类型
	 */
    private String accType;

	/**
	 * 账单类型
	 */
    private String billType;

	/**
	 * 账单号码
	 */
    private String billNo;

	/**
	 * 交互方式
	 */
    private String interactMode;

	/**
	 * 原交易查询流水号
	 */
    private String origQryId;

	/**
	 * 商户代码
	 */
    private String merId;

	/**
	 * 分账入账方式
	 */
    private String splitInWay;

	/**
	 * 二级商户代码
	 */
    private String subMerId;

	/**
	 * 二级商户简称
	 */
    private String subMerAbbr;

	/**
	 * 二级商户分账入账金额X+n12标示D/C
	 */
    private String subMerSplitInAmtFlag;

	/**
	 * 二级商户分账入账金额X+n12
	 */
    private BigDecimal subMerSplitInAmt;

	/**
	 * 清算净额X+n12标示D/C
	 */
    private String settleNetAmtFlag;

	/**
	 * 清算净额X+n12
	 */
    private BigDecimal settleNetAmt;

	/**
	 * 终端号
	 */
    private String termId;

	/**
	 * 商户自定义域
	 */
    private String merReserved;

	/**
	 * 优惠金额X+n12标示D/C
	 */
    private String discountAmtFlag;

	/**
	 * 优惠金额X+n12
	 */
    private BigDecimal discountAmt;

	/**
	 * 发票金额X+n12标示D/C
	 */
    private String invoiceAmtFlag;

	/**
	 * 发票金额X+n12
	 */
    private BigDecimal invoiceAmt;

	/**
	 * 分期付款附加手续费X+n11标示D/C
	 */
    private String periodPayPlusFeeFlag;

	/**
	 * 分期付款附加手续费X+n11
	 */
    private BigDecimal periodPayPlusFee;

	/**
	 * 分期付款期数
	 */
    private String payPeriods;

	/**
	 * 交易介质
	 */
    private String tradeMedium;

	/**
	 * 保留使用
	 */
    private String reserve;

	/**
	 * 写入日期：YYYY-MM-DD
	 */
    private String instDate;

	/**
	 * 写入时间
	 */
    private String instDatetime;

	/**
	 * 对账结果：SELF_DOUBT/BAL_DOUBT/BAL_ERROR
	 */
    private String balResult;

	/**
	 * 对账处理时间
	 */
    private String balDatetime;

	/**
	 * 对账处理备注
	 */
    private String balMemo;

	/**
	 * 异常处理标记：挂账/抹账/销账
	 */
    private String dealFlag;

	/**
	 * 异常处理人
	 */
    private String dealUser;

	/**
	 * 异常处理时间
	 */
    private Date dealDatetime;

	/**
	 * 异常处理备注
	 */
    private String dealMemo;
    

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getAcqInsCode() {
		return acqInsCode;
	}

	public void setAcqInsCode(String acqInsCode) {
		this.acqInsCode = acqInsCode;
	}

	public String getIssInsCode() {
		return issInsCode;
	}

	public void setIssInsCode(String issInsCode) {
		this.issInsCode = issInsCode;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getMerCatCode() {
		return merCatCode;
	}

	public void setMerCatCode(String merCatCode) {
		this.merCatCode = merCatCode;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getOldPayType() {
		return oldPayType;
	}

	public void setOldPayType(String oldPayType) {
		this.oldPayType = oldPayType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}

	public String getOrigTraceNo() {
		return origTraceNo;
	}

	public void setOrigTraceNo(String origTraceNo) {
		this.origTraceNo = origTraceNo;
	}

	public String getOrigTxnTime() {
		return origTxnTime;
	}

	public void setOrigTxnTime(String origTxnTime) {
		this.origTxnTime = origTxnTime;
	}

	public String getMchntFeeFlag() {
		return mchntFeeFlag;
	}

	public void setMchntFeeFlag(String mchntFeeFlag) {
		this.mchntFeeFlag = mchntFeeFlag;
	}

	public BigDecimal getMchntFee() {
		return mchntFee;
	}

	public void setMchntFee(BigDecimal mchntFee) {
		this.mchntFee = mchntFee;
	}

	public String getSettleAmtFlag() {
		return settleAmtFlag;
	}

	public void setSettleAmtFlag(String settleAmtFlag) {
		this.settleAmtFlag = settleAmtFlag;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getGroupMerId() {
		return groupMerId;
	}

	public void setGroupMerId(String groupMerId) {
		this.groupMerId = groupMerId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getInteractMode() {
		return interactMode;
	}

	public void setInteractMode(String interactMode) {
		this.interactMode = interactMode;
	}

	public String getOrigQryId() {
		return origQryId;
	}

	public void setOrigQryId(String origQryId) {
		this.origQryId = origQryId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSplitInWay() {
		return splitInWay;
	}

	public void setSplitInWay(String splitInWay) {
		this.splitInWay = splitInWay;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getSubMerAbbr() {
		return subMerAbbr;
	}

	public void setSubMerAbbr(String subMerAbbr) {
		this.subMerAbbr = subMerAbbr;
	}

	public String getSubMerSplitInAmtFlag() {
		return subMerSplitInAmtFlag;
	}

	public void setSubMerSplitInAmtFlag(String subMerSplitInAmtFlag) {
		this.subMerSplitInAmtFlag = subMerSplitInAmtFlag;
	}

	public BigDecimal getSubMerSplitInAmt() {
		return subMerSplitInAmt;
	}

	public void setSubMerSplitInAmt(BigDecimal subMerSplitInAmt) {
		this.subMerSplitInAmt = subMerSplitInAmt;
	}

	public String getSettleNetAmtFlag() {
		return settleNetAmtFlag;
	}

	public void setSettleNetAmtFlag(String settleNetAmtFlag) {
		this.settleNetAmtFlag = settleNetAmtFlag;
	}

	public BigDecimal getSettleNetAmt() {
		return settleNetAmt;
	}

	public void setSettleNetAmt(BigDecimal settleNetAmt) {
		this.settleNetAmt = settleNetAmt;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getMerReserved() {
		return merReserved;
	}

	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}

	public String getDiscountAmtFlag() {
		return discountAmtFlag;
	}

	public void setDiscountAmtFlag(String discountAmtFlag) {
		this.discountAmtFlag = discountAmtFlag;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getInvoiceAmtFlag() {
		return invoiceAmtFlag;
	}

	public void setInvoiceAmtFlag(String invoiceAmtFlag) {
		this.invoiceAmtFlag = invoiceAmtFlag;
	}

	public BigDecimal getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(BigDecimal invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public String getPeriodPayPlusFeeFlag() {
		return periodPayPlusFeeFlag;
	}

	public void setPeriodPayPlusFeeFlag(String periodPayPlusFeeFlag) {
		this.periodPayPlusFeeFlag = periodPayPlusFeeFlag;
	}

	public BigDecimal getPeriodPayPlusFee() {
		return periodPayPlusFee;
	}

	public void setPeriodPayPlusFee(BigDecimal periodPayPlusFee) {
		this.periodPayPlusFee = periodPayPlusFee;
	}

	public String getPayPeriods() {
		return payPeriods;
	}

	public void setPayPeriods(String payPeriods) {
		this.payPeriods = payPeriods;
	}

	public String getTradeMedium() {
		return tradeMedium;
	}

	public void setTradeMedium(String tradeMedium) {
		this.tradeMedium = tradeMedium;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
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

	public String getBalResult() {
		return balResult;
	}

	public void setBalResult(String balResult) {
		this.balResult = balResult;
	}

	public String getBalDatetime() {
		return balDatetime;
	}

	public void setBalDatetime(String balDatetime) {
		this.balDatetime = balDatetime;
	}

	public String getBalMemo() {
		return balMemo;
	}

	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}

	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public Date getDealDatetime() {
		return dealDatetime;
	}

	public void setDealDatetime(Date dealDatetime) {
		this.dealDatetime = dealDatetime;
	}

	public String getDealMemo() {
		return dealMemo;
	}

	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}
    
    
}
