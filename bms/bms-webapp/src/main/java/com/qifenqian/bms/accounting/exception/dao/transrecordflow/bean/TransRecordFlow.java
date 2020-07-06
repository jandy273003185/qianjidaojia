package com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 
 * @project sevenpay-bms-web
 * @fileName TransRecordFlow.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class TransRecordFlow implements Serializable{

	private static final long serialVersionUID = -3375836065810375784L;

	/** 编号*/			private String id;
	/** 本系统报文编号*/		private String msgId;
	/** 报文类型*/			private RequestColumnValues.MsgType msgType;
	/** 交易步骤*/			private String transStep;
	/** 交易接口入库编号*/	private String transTradeId;
	/** 客户ID*/			private String custId;
	/** 账户类型*/			private RequestColumnValues.AcctType acctType;
	/** 内部账户ID*/		private String acctId;
	/** 币别*/			private RequestColumnValues.CurrCode currCode;
	/** 交易金额*/			private BigDecimal transAmt;
	/** 密码*/			private String pin;
	/** 摘要*/			private String brief;
	/** 原/对应编号*/		private String originId;
	/** 状态*/			private RequestColumnValues.TransStatus status;
	/** 写入时间*/			private Date instDatetime;
	/** 返回码*/			private String rtnCode;
	/** 返回信息*/			private String rtnInfo;
	/** 返回编号*/			private String rtnId;
	/** 返回更新时间*/		private Date updateDatetime;
	/** 业务类型:注册/充值/支付/提现*/	private RequestColumnValues.BusinessType businessType;
	/** 收支标记:DEBIT减/CREDIT加*/	private RequestColumnValues.LoanFlag loanFlag;
	/** 操作类型:充值/消费付款/消费收款/费用/注册/冻结*/	private RequestColumnValues.TransFlowOperate operate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public RequestColumnValues.MsgType getMsgType() {
		return msgType;
	}
	public void setMsgType(RequestColumnValues.MsgType msgType) {
		this.msgType = msgType;
	}
	public String getTransStep() {
		return transStep;
	}
	public void setTransStep(String transStep) {
		this.transStep = transStep;
	}
	public String getTransTradeId() {
		return transTradeId;
	}
	public void setTransTradeId(String transTradeId) {
		this.transTradeId = transTradeId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public RequestColumnValues.AcctType getAcctType() {
		return acctType;
	}
	public void setAcctType(RequestColumnValues.AcctType acctType) {
		this.acctType = acctType;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}
	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public RequestColumnValues.TransStatus getStatus() {
		return status;
	}
	public void setStatus(RequestColumnValues.TransStatus status) {
		this.status = status;
	}
	public Date getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnInfo() {
		return rtnInfo;
	}
	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}
	public String getRtnId() {
		return rtnId;
	}
	public void setRtnId(String rtnId) {
		this.rtnId = rtnId;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public RequestColumnValues.BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(RequestColumnValues.BusinessType businessType) {
		this.businessType = businessType;
	}
	public RequestColumnValues.LoanFlag getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(RequestColumnValues.LoanFlag loanFlag) {
		this.loanFlag = loanFlag;
	}
	public RequestColumnValues.TransFlowOperate getOperate() {
		return operate;
	}
	public void setOperate(RequestColumnValues.TransFlowOperate operate) {
		this.operate = operate;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}


