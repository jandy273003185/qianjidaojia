package com.qifenqian.bms.basemanager.acctsevencust.bean;

import java.io.Serializable;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 
 * @author shen
 *
 */
public class AcctSevenCustFreeze implements Serializable {

	private static final long serialVersionUID = -3627049837874689619L;
	
	private String id;

	private String acctId;

	private String custId;

	private RequestColumnValues.MsgType msgType;

	private String status;

	private String brief;

	private String relateId;

	private String instDate;

	private Date instDatetime;

	private String creator;

	private String rtnCode;

	private Date rtnDatetime;

	private String rtnInfo;
	

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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public RequestColumnValues.MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(RequestColumnValues.MsgType msgType) {
		this.msgType = msgType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
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

}
