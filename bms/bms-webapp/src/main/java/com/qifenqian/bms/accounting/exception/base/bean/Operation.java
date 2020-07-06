package com.qifenqian.bms.accounting.exception.base.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.qifenqian.bms.accounting.exception.base.type.OperationStatus;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName Operation.java
 * @author huiquan.ma
 * @date 2015年10月19日
 * @memo
 */
public class Operation implements Serializable {

	private static final long serialVersionUID = 1648676739837331310L;

	/** 业务编号 */
	private String operId;
	/** 业务类型 */
	private RequestColumnValues.MsgType operType;
	/** 业务类型 */
	private String operTypeName;
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
	private OperationStatus status;
	/** 订单时间 */
	private String operDatetime;
	/** 订单日期 */
	private String operDate;
	/** 订单日期大 */
	private String operDateMax;
	/** 订单日期小 */
	private String operDateMin;
	/** 订单日期小 */
	private String operationOpinion;
	/** 01 待支付；02 网银返回成功；03 网银返回失败；04：提交核心处理；05 核心返回失败；00 充值成功 */
	private String chargeNetpayState;
	
	private String statusOther;

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperTypeName() {
		return operTypeName;
	}

	public void setOperTypeName(String operTypeName) {
		this.operTypeName = operTypeName;
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

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

	public String getOperDatetime() {
		return operDatetime;
	}

	public void setOperDatetime(String operDatetime) {
		this.operDatetime = operDatetime;
	}

	public RequestColumnValues.MsgType getOperType() {
		return operType;
	}

	public void setOperType(RequestColumnValues.MsgType operType) {
		this.operType = operType;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getOperDateMax() {
		return operDateMax;
	}

	public void setOperDateMax(String operDateMax) {
		this.operDateMax = operDateMax;
	}

	public String getOperDateMin() {
		return operDateMin;
	}

	public void setOperDateMin(String operDateMin) {
		this.operDateMin = operDateMin;
	}

	public String getOperationOpinion() {
		return operationOpinion;
	}

	public void setOperationOpinion(String operationOpinion) {
		this.operationOpinion = operationOpinion;
	}

	public String getChargeNetpayState() {
		return chargeNetpayState;
	}

	public void setChargeNetpayState(String chargeNetpayState) {
		this.chargeNetpayState = chargeNetpayState;
	}

	public String getStatusOther() {
		return statusOther;
	}

	public void setStatusOther(String statusOther) {
		this.statusOther = statusOther;
	}
	
}
