package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 话费充值-清算七分钱
 * @author Roy.Li
 *
 */
public class ClearSevenpayPayment implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2445044509724853622L;

	/** 清算编号*/
	private String clearId;
	
    /** 订单编号*/
	private int orderId;
    
	/** 商品名称*/
	private String commodityName;
    
	/** 支付类型*/
	private String payType;
    
	/** 收方客户号*/
	private String rcvCustId;
    
	/** 交易金额*/
	private BigDecimal transAmt;
    
	/** 币别*/
	private SevenmallRequestValues.SevenmallCurrCode currCode;
    
	/** 订单描述*/
	private String orderMemo;
    
	/** 摘要*/
	private String brief;
    
	/** 七分钱会计日期*/
	private String workDate;
    
	/** 状态：SUCCESS/FAILURE/EXCEPTION/CONFIRM_SUCCESS/CONFIRM_FAILURE*/
	private SevenmallRequestValues.SevenmallClearStatus status;
    
	/** 写入日期*/
	private String instDate;
    
	/** 写入时间*/
	private Date instDatetime;
    
	/** 返回流水号*/
	private String rtnSeq;
    
	/** 返回代码*/
	private SevenmallRequestValues.SevenpayRtnCode rtnCode;
    
	/** 返回描述*/
	private String rtnInfo;
    
	/** 返回支付类型：BALANCE/BANK_CARD*/
	private SevenmallRequestValues.SevenpayPayType rtnPayType;
    
	/** 返回付款渠道*/
	private String rtnPayChannel;
    
	/** 返回付款简介*/
	private String rtnPayBrief;
    
	/** 返回本地写入时间*/
	private Date updateDatetime;
    
	/** 对账状态*/
	private String balStatus;
	
	/** 当前状态，用于修改状态时加上状态控制*/
	private SevenmallRequestValues.SevenmallClearStatus currStatus;

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRcvCustId() {
		return rcvCustId;
	}

	public void setRcvCustId(String rcvCustId) {
		this.rcvCustId = rcvCustId;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public SevenmallRequestValues.SevenmallCurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(SevenmallRequestValues.SevenmallCurrCode currCode) {
		this.currCode = currCode;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
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

	public SevenmallRequestValues.SevenmallClearStatus getStatus() {
		return status;
	}

	public void setStatus(SevenmallRequestValues.SevenmallClearStatus status) {
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

	public String getRtnSeq() {
		return rtnSeq;
	}

	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}

	public SevenmallRequestValues.SevenpayRtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(SevenmallRequestValues.SevenpayRtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnInfo() {
		return rtnInfo;
	}

	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}

	public SevenmallRequestValues.SevenpayPayType getRtnPayType() {
		return rtnPayType;
	}

	public void setRtnPayType(SevenmallRequestValues.SevenpayPayType rtnPayType) {
		this.rtnPayType = rtnPayType;
	}

	public String getRtnPayChannel() {
		return rtnPayChannel;
	}

	public void setRtnPayChannel(String rtnPayChannel) {
		this.rtnPayChannel = rtnPayChannel;
	}

	public String getRtnPayBrief() {
		return rtnPayBrief;
	}

	public void setRtnPayBrief(String rtnPayBrief) {
		this.rtnPayBrief = rtnPayBrief;
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

	public SevenmallRequestValues.SevenmallClearStatus getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(SevenmallRequestValues.SevenmallClearStatus currStatus) {
		this.currStatus = currStatus;
	}
}
