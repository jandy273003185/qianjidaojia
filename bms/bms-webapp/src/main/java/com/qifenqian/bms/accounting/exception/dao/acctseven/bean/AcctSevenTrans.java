package com.qifenqian.bms.accounting.exception.dao.acctseven.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 账户交易bean
 * @project sevenpay-bms-web
 * @fileName AcctSevenTrans.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class AcctSevenTrans extends TransAction{
	
	private static final long serialVersionUID = -1982740046955223035L;

	/** 流水号*/		private String id;
	/** 账号*/		private String acctId;
	/** 业务编号*/		private String transFlowId;
	/** 交易前余额*/	private BigDecimal balance;
	/** 币别*/		private RequestColumnValues.CurrCode currCode;
	/** 发生额*/		private BigDecimal transAmt;
	/** 在途*/		private BigDecimal onwayAmt;
	/** 可用额*/		private BigDecimal usableAmt;
	/** 七分钱会计日期*/	private String workDate;
	/** 摘要*/		private String brief;
	/** 记账日期*/		private String instDate;
	/** 记账时间*/		private Date instDatetime;
	/** 收付标记：D付-减/C收-加*/	private RequestColumnValues.LoanFlag loanFlag;
	/** 交易类型：充值/提现/消费*/	private RequestColumnValues.BusinessType businessType;
	/** 产品代码*/	private String productCode;
	/** 渠道代码*/	private String channelCode;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public BigDecimal getOnwayAmt() {
		return onwayAmt;
	}
	public void setOnwayAmt(BigDecimal onwayAmt) {
		this.onwayAmt = onwayAmt;
	}
	public BigDecimal getUsableAmt() {
		return usableAmt;
	}
	public void setUsableAmt(BigDecimal usableAmt) {
		this.usableAmt = usableAmt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getTransFlowId() {
		return transFlowId;
	}
	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
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
	public RequestColumnValues.LoanFlag getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(RequestColumnValues.LoanFlag loanFlag) {
		this.loanFlag = loanFlag;
	}
	public RequestColumnValues.BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(RequestColumnValues.BusinessType businessType) {
		this.businessType = businessType;
	}
}


