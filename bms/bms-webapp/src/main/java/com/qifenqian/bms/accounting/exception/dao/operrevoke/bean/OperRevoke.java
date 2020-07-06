package com.qifenqian.bms.accounting.exception.dao.operrevoke.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;

public class OperRevoke extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4808754777626688917L;

	/** 原交易订单号 **/
	private String originOrderId;
	/** 订单名称 **/
	private String orderName;
	/** 原交易时间 **/
	private Date orderCreateTime;
	/** 付方账号 **/
	private String mobile;
	/** 收方账号 **/
	private String payeeCustId;
	/** 交易金额 **/
	private BigDecimal sumAmt;
	/** 支付类型 **/
	private String orderType;
	/** 交易撤销流水号 **/
	private String orderId;
	/** 交易撤销申请时间 **/
	private Date instDatetime;
	/** 创建人 **/
	private String creator;
	/** 撤销核心返回码 **/
	private String rtnCode;
	/** 撤销核心返回信息 **/
	private String rtnInfo;
	
	private String coreSn;

	private String revokeStatus;

	public String getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPayeeCustId() {
		return payeeCustId;
	}

	public void setPayeeCustId(String payeeCustId) {
		this.payeeCustId = payeeCustId;
	}

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getRevokeStatus() {
		return revokeStatus;
	}

	public void setRevokeStatus(String revokeStatus) {
		this.revokeStatus = revokeStatus;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

}
