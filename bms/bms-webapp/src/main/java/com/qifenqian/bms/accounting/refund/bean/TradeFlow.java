package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sevenpay.invoke.common.type.RequestColumnValues;

public class TradeFlow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3914112163608027185L;
	/** 交易编号 **/
	private String id;
	/** 业务类型 **/
	private String businessType;
	/** 交易时间 **/
	private String  insertDatetime;
	/** 交易金额 **/
	private BigDecimal transAmt;
	/** 币别 **/
	private RequestColumnValues.CurrCode currCode;
	/** 摘要 **/
	private String brief;
	/** 客户号 **/
	private String custId;
	/** 报文编号 **/
	private String msgId;
	/** 报文类型 **/
	private RequestColumnValues.MsgType msgType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getInsertDatetime() {
		return insertDatetime;
	}

	public void setInsertDatetime(String insertDatetime) {
		this.insertDatetime = insertDatetime;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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

}
