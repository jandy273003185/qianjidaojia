package com.qifenqian.bms.accounting.exception.dao.acctseven.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.sevenpay.invoke.common.type.RequestColumnValues;

public class AcctSevenFreeze extends TransAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6594841981556969295L;

	private String id;

	/** 账号 **/
	private String acctId;

	/** 业务编号 **/
	private String transFlowId;

	/** 操作类型FREEZE/UNFREEZE **/
	private RequestColumnValues.FreezeFlowOperate operate;

	/** 操作前冻结金额 **/
	private BigDecimal freezeAmtBefore;

	private RequestColumnValues.CurrCode currCode;

	/** 操作金额 **/
	private BigDecimal operateAmt;

	/** 状态FREEZE/UNFREEZE **/
	private RequestColumnValues.FreezeFlowOperate status;

	private String workDate;

	private String brief;

	/*** 关联编号（解冻时录入对应冻结编号） **/
	private String relateId;

	private String instDate;

	private Date instDatetime;

	private Date updateDatetime;

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

	public RequestColumnValues.FreezeFlowOperate getOperate() {
		return operate;
	}

	public void setOperate(RequestColumnValues.FreezeFlowOperate operate) {
		this.operate = operate;
	}

	public BigDecimal getFreezeAmtBefore() {
		return freezeAmtBefore;
	}

	public void setFreezeAmtBefore(BigDecimal freezeAmtBefore) {
		this.freezeAmtBefore = freezeAmtBefore;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getOperateAmt() {
		return operateAmt;
	}

	public void setOperateAmt(BigDecimal operateAmt) {
		this.operateAmt = operateAmt;
	}

	public RequestColumnValues.FreezeFlowOperate getStatus() {
		return status;
	}

	public void setStatus(RequestColumnValues.FreezeFlowOperate status) {
		this.status = status;
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

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
