package com.qifenqian.bms.accounting.exception.dao.transyl.bean;

import java.math.BigDecimal;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName TransYl.java
 * @author huiquan.ma
 * @date 2015年11月3日
 * @memo
 */
public class TransYl extends TransAction {

	private static final long serialVersionUID = -4695093564550630842L;

	/** 交易主键id,用于防重 */
	private String transId;
	/** 交易流水号 */
	private String transSn;
	/** 商户推送订单后银联移动支付系统返 */
	private String ylTn;
	/** 交易类型: CHARGE 充值, */
	private String transType;
	/** 客户编号 */
	private String custId;
	/** 客户名 */
	private String custName;
	/** 交易币别 */
	private RequestColumnValues.CurrCode transCurrCode;
	/** 交易金额 */
	private BigDecimal transAmt;
	/** 备注 */
	private String memo;
	/** 交易提交时间(格式：yyyyMM */
	private String transSubmitTime;
	/** 银联同步步响应时间(格式：yyy */
	private String ylRespTimeTb;
	/** 银联同步返回码 */
	private String ylRespCodeTb;
	/** 银联异步响应时间(格式：yyyy */
	private String ylRespTimeYb;
	/** 银联响应码 */
	private String ylRespCodeYb;
	/** 银联响应信息 */
	private String ylRespMsgYb;
	/** 清算金额 */
	private BigDecimal settleAmt;
	/** 清算币种 */
	private String settleCurrencyCode;
	/** 清算日期 MMDD */
	private String settleDate;
	/** 系统跟踪号 */
	private String traceNo;
	/** 交易传输时间 MMDDhhmms */
	private String traceTime;
	/** 兑换日期 YYYYMMDD */
	private String exchangeDate;
	/** 汇率 */
	private BigDecimal exchangeRate;
	/** 账号 */
	private String accNo;
	/** 支付卡类型 00：未知01：借记 */
	private String payCardType;
	/** 支付方式 */
	private String payType;
	/** 支付卡标识 */
	private String payCardNo;
	/** 支付卡名称 */
	private String payCardIssueName;
	/** 绑定标识号 */
	private String bindId;
	/** 查询流水号 */
	private String queryId;
	/** 撤销交易查询id */
	private String revokeQueryId;
	/** 交易类型, 取值： 00: 查 */
	private String txnType;
	/** 银联交易子类 */
	private String txnSubType;
	/** 产品类型 */
	private String bizType;
	/** 接入类型, 0：商户直连接入 */
	private String accessType;
	/** 商户代码 */
	private String merId;
	/** 请求方保留域 */
	private String reqReserved;
	/** 保留域 */
	private String reserved;
	/** VPC交易信息域 */
	private String vpcTransData;
	private String workDate;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransSn() {
		return transSn;
	}

	public void setTransSn(String transSn) {
		this.transSn = transSn;
	}

	public String getYlTn() {
		return ylTn;
	}

	public void setYlTn(String ylTn) {
		this.ylTn = ylTn;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
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

	public RequestColumnValues.CurrCode getTransCurrCode() {
		return transCurrCode;
	}

	public void setTransCurrCode(RequestColumnValues.CurrCode transCurrCode) {
		this.transCurrCode = transCurrCode;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTransSubmitTime() {
		return transSubmitTime;
	}

	public void setTransSubmitTime(String transSubmitTime) {
		this.transSubmitTime = transSubmitTime;
	}

	public String getYlRespTimeTb() {
		return ylRespTimeTb;
	}

	public void setYlRespTimeTb(String ylRespTimeTb) {
		this.ylRespTimeTb = ylRespTimeTb;
	}

	public String getYlRespCodeTb() {
		return ylRespCodeTb;
	}

	public void setYlRespCodeTb(String ylRespCodeTb) {
		this.ylRespCodeTb = ylRespCodeTb;
	}

	public String getYlRespTimeYb() {
		return ylRespTimeYb;
	}

	public void setYlRespTimeYb(String ylRespTimeYb) {
		this.ylRespTimeYb = ylRespTimeYb;
	}

	public String getYlRespCodeYb() {
		return ylRespCodeYb;
	}

	public void setYlRespCodeYb(String ylRespCodeYb) {
		this.ylRespCodeYb = ylRespCodeYb;
	}

	public String getYlRespMsgYb() {
		return ylRespMsgYb;
	}

	public void setYlRespMsgYb(String ylRespMsgYb) {
		this.ylRespMsgYb = ylRespMsgYb;
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

	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
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

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getRevokeQueryId() {
		return revokeQueryId;
	}

	public void setRevokeQueryId(String revokeQueryId) {
		this.revokeQueryId = revokeQueryId;
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

	public String getVpcTransData() {
		return vpcTransData;
	}

	public void setVpcTransData(String vpcTransData) {
		this.vpcTransData = vpcTransData;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

}
