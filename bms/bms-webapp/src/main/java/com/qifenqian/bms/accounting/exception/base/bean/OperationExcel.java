package com.qifenqian.bms.accounting.exception.base.bean;

import java.math.BigDecimal;

/**
 * @project sevenpay-bms-web
 * @fileName Operation.java
 * @author huiquan.ma
 * @date 2015年10月19日
 * @memo
 */
public class OperationExcel {

	/** 业务编号 */
	private String operId;
	/** 业务类型 */
	private String operType;
	/** 付方用户编号 */
	private String payCustId;
	/** 付方用户名称 */
	private String payCustName;
	/** 金额 */
	private BigDecimal transAmt;
	/** 收方用户编号 */
	private String rcvCustId;
	/** 收方用户名称 */
	private String rcvCustName;
	/** 状态 */
	private String status;
	/** 订单日期 */
	private String operDate;
	/** 订单时间 */
	private String operDatetime;
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getPayCustId() {
		return payCustId;
	}
	public void setPayCustId(String payCustId) {
		this.payCustId = payCustId;
	}
	public String getPayCustName() {
		return payCustName;
	}
	public void setPayCustName(String payCustName) {
		this.payCustName = payCustName;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getRcvCustId() {
		return rcvCustId;
	}
	public void setRcvCustId(String rcvCustId) {
		this.rcvCustId = rcvCustId;
	}
	public String getRcvCustName() {
		return rcvCustName;
	}
	public void setRcvCustName(String rcvCustName) {
		this.rcvCustName = rcvCustName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public String getOperDatetime() {
		return operDatetime;
	}
	public void setOperDatetime(String operDatetime) {
		this.operDatetime = operDatetime;
	}
	
}
