package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

public class RefundRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5627169573695029384L;

	/** 编号 **/
	private String id;
	
	/** 退款订单号  */
	private String refundOrderId;
	
	/** 客户号 **/
	private String custId;

	/** 业务类型 **/
	private String businessType;

	/** 摘要 **/
	private String brief;

	/** 报文编号 **/
	private String originMsgId;

	/** 报文类型 **/
	private RequestColumnValues.MsgType msgType;

	/** 原交易报文类型 **/
	private String originMsgType;

	/** 交易金额 **/
	private BigDecimal refundAmt;

	/** 币别 **/
	private RequestColumnValues.CurrCode currCode;

	/** 操作日期 **/
	private String instDate;

	/** 交易时间 **/
	private Date instDatetime;

	/** 创建人 **/
	private String creator;

	/** 返回码 **/
	private String rtnCode;

	/** 返回时间 **/
	private Date rtnDatetime;

	/** 返回信息 **/
	private String rtnInfo;

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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getOriginMsgId() {
		return originMsgId;
	}

	public void setOriginMsgId(String originMsgId) {
		this.originMsgId = originMsgId;
	}

	public RequestColumnValues.MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(RequestColumnValues.MsgType msgType) {
		this.msgType = msgType;
	}

	public String getOriginMsgType() {
		return originMsgType;
	}

	public void setOriginMsgType(String originMsgType) {
		this.originMsgType = originMsgType;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
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

	public Date getRtnDatetime() {
		return rtnDatetime;
	}

	public void setRtnDatetime(Date rtnDatetime) {
		this.rtnDatetime = rtnDatetime;
	}

	public String getRtnInfo() {
		return rtnInfo;
	}

	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}

	public String getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}
}
