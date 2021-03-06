package com.qifenqian.bms.accounting.exception.dao.clearunionpay.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 银联清算: clear_unionpay
 * 
 * @project sevenpay-bms-web
 * @fileName ClearUnionpay.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class ClearUnionpay extends TransAction {

	private static final long serialVersionUID = 845124552675749541L;

	/** 编号 **/
	private String id;
	/** 流水编号 **/
	private String transFlowId;
	/** 客户号 **/
	private String custId;
	/** 付方内部账户ID */
	private String acctId;
	/** 交易类型 */
	private String transType;
	/** 账号 */
	private String acctNo;
	/** 支付卡类型 */
	private String payCardType;
	/** 七分钱订单号 */
	private String orderId;
	/** 交易查询流水号 */
	private String queryId;
	/** 原始交易流水号 */
	private String origQryId;
	/** 订单发送时间 */
	private String txnTime;
	/** 交易币种 */
	private String currencyCode;
	/** 交易金额 */
	private BigDecimal txnAmt;
	/** 响应码 */
	private String respCode;
	/** 响应信息 */
	private String respMsg;
	/** 响应时间 */
	private String respTime;
	/** 清算金额 */
	private BigDecimal settleAmt;
	/** 清算币种 */
	private String settleCurrencyCode;
	/** 清算日期 */
	private String settleDate;
	/** 系统跟踪号 */
	private String traceNo;
	/** 交易传输时间 */
	private String traceTime;
	/** 交易类型 */
	private String txnType;
	/** 交易子类 */
	private String txnSubType;
	/** 产品类型 */
	private String bizType;
	/** 接入类型 */
	private String accessType;
	/** 商户代码 */
	private String merId;
	/** 请求方保留域 */
	private String reqReserved;
	/** 保留域 */
	private String reserved;
	/** 兑换日期 */
	private String exchangeDate;
	/** 汇率 */
	private String exchangeRate;
	/** VPC交易信息域 */
	private String vpcTransData;
	/** 支付方式 */
	private String payType;
	/** 支付卡标识 */
	private String payCardNo;
	/** 支付卡名称 */
	private String payCardIssueName;
	/** 绑定标识号 */
	private String bindId;
	/** 摘要 */
	private String brief;
	/** 本系统会计日期 **/
	private String workDate;
	/** 写入日期 **/
	private String instDate;
	/** 写入时间 **/
	private Date instDatetime;
	/** 对账状态 **/
	private RequestColumnValues.BalStatus balStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransFlowId() {
		return transFlowId;
	}

	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
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

	public String getOrigQryId() {
		return origQryId;
	}

	public void setOrigQryId(String origQryId) {
		this.origQryId = origQryId;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
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

	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}

	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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

	public RequestColumnValues.BalStatus getBalStatus() {
		return balStatus;
	}

	public void setBalStatus(RequestColumnValues.BalStatus balStatus) {
		this.balStatus = balStatus;
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

	public String getReqReserved() {
		return reqReserved;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getVpcTransData() {
		return vpcTransData;
	}

	public void setVpcTransData(String vpcTransData) {
		this.vpcTransData = vpcTransData;
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

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}
}
