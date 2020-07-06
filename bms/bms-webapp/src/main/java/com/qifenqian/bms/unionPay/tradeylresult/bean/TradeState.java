package com.qifenqian.bms.unionPay.tradeylresult.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeState implements Serializable {

	private static final long serialVersionUID = -5105243917820265336L;
	

	/**
	 * 交易类型
	 */
	private String txnType;
	
	/**
	 * 交易子类
	 */
	private String txnSubType;
	
	/**
	 * 接入类型
	 */
	private String accessType;
	
	/**
	 * 商户代码
	 */
	private String merId;
	
	/**
	 * 订单发送时间
	 */
	private String txnTime;
	/**
	 * 商户订单号
	 */
	private String orderId;
	/**
	 * 交易查询流水号
	 */
	private String queryId;
	/**
	 * 系统跟踪号
	 */
	private String traceNo;
	/**
	 * 交易传输时间
	 */
	private String traceTime;
	/**
	 *清算日期
	 */
	private String settleDate;
	/**
	 *清算币种
	 */
	private String settleCurrencyCode;
	/**
	 *清算金额
	 */
	private BigDecimal settleAmt;
	/**
	 *清算汇率
	 */
	private String exchangeRate;
	/**
	 *兑换日期
	 */
	private String exchangeDate;
	/**
	 *交易币种
	 */
	private String currencyCode;
	
	/**
	 *交易金额
	 */
	private BigDecimal txnAmt;
	/**
	 *原交易应答码
	 */
	private String origRespCode;
	
	/**
	 *原交易应答信息
	 */
	private String origRespMsg;
	/**
	 *应答码
	 */
	private String respCode;
	/**
	 *应答信息
	 */
	private String respMsg;
	/**
	 *账号
	 */
	private String accNo;
	/**
	 * 支付卡类型
	 */
	private String payCardType;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 支付卡标识
	 */
	private String payCardNo;
	/**
	 * 支付卡名称
	 */
	private String payCardIssueName;
	
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
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getTraceTime() {
		return traceTime;
	}
	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}
	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getOrigRespCode() {
		return origRespCode;
	}
	public void setOrigRespCode(String origRespCode) {
		this.origRespCode = origRespCode;
	}
	public String getOrigRespMsg() {
		return origRespMsg;
	}
	public void setOrigRespMsg(String origRespMsg) {
		this.origRespMsg = origRespMsg;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getPayCardType() {
		return payCardType;
	}
	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getPayCardIssueName() {
		return payCardIssueName;
	}
	public void setPayCardIssueName(String payCardIssueName) {
		this.payCardIssueName = payCardIssueName;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}
	public BigDecimal getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}
	
	
	
}
