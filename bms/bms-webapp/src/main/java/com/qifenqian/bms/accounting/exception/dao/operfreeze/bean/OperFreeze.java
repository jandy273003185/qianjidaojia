package com.qifenqian.bms.accounting.exception.dao.operfreeze.bean;

import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;

public class OperFreeze extends Operation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1873529138152221273L;

	private String id;

	private String acctId;

	private String custId;

	private String brief;

	private String relateId;

	private String instDate;

	private Date instDatetime;

	private String creator;

	private String rtnCode;

	private Date rtnDatetime;

	private String rtnInfo;

	private String freezeStatus;

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

	public String getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

}
