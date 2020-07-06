package com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ClearJgkjTrade {
	
    /** 编号*/
	private String id;
    /** 交易编号*/
	private String transFlowId;
    /** 交易码*/
	private String transCode;
    /** 交易类型*/
	private String businessType;
    /** 交广科技卡号*/
	private String cardNo;
    /** 币别*/
	private String currCode;
    /** 金额：*/
	private BigDecimal transAmt;
    /** 订单编号*/
	private String orderNo;
    /** 订单描述*/
	private String orderMemo;
    /** 密码*/
	private String pin;
    /** 交易请求时间*/
	private String sendDatetime;
    /** 七分钱会计日期*/
	private String workDate;
    /** 七分钱交易发送日期YYYYMMDD*/
	private String sendDate;
    /** 七分钱交易发送时间HHmmss*/
	private String sendTime;
    /** 状态*/
	private String status;
    /** 写入日期*/
	private String instDate;
    /** 写入时间*/
	private Date instDatetime;
    /** 交广科技返回日期*/
	private String rtnDate;
    /** 交广科技返回时间*/
	private String rtnTime;
    /** 交广科技平台流水号*/
	private String rtnSeq;
    /** 交广科技返回代码*/
	private String rtnCode;
    /** 交广科技返回描述*/
	private String rtnInfo;
    /** 返回卡号*/
	private String rtnCardNo;
    /** 交广科技返回金额*/
	private BigDecimal rtnAmt;
    /** 交广科技返回余额*/
	private BigDecimal rtnBalance;
    /** 交广科技返回写入时间*/
	private Date updateDatetime;
    /** 对账状态*/
	private String balStatus;
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
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getSendDatetime() {
		return sendDatetime;
	}
	public void setSendDatetime(String sendDatetime) {
		this.sendDatetime = sendDatetime;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getRtnDate() {
		return rtnDate;
	}
	public void setRtnDate(String rtnDate) {
		this.rtnDate = rtnDate;
	}
	public String getRtnTime() {
		return rtnTime;
	}
	public void setRtnTime(String rtnTime) {
		this.rtnTime = rtnTime;
	}
	public String getRtnSeq() {
		return rtnSeq;
	}
	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
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
	public String getRtnCardNo() {
		return rtnCardNo;
	}
	public void setRtnCardNo(String rtnCardNo) {
		this.rtnCardNo = rtnCardNo;
	}
	public BigDecimal getRtnAmt() {
		return rtnAmt;
	}
	public void setRtnAmt(BigDecimal rtnAmt) {
		this.rtnAmt = rtnAmt;
	}
	public BigDecimal getRtnBalance() {
		return rtnBalance;
	}
	public void setRtnBalance(BigDecimal rtnBalance) {
		this.rtnBalance = rtnBalance;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getBalStatus() {
		return balStatus;
	}
	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}

}