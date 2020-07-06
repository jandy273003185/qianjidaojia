package com.qifenqian.bms.accounting.exception.base.bean;

import java.io.Serializable;

import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName TransAction.java
 * @author huiquan.ma
 * @date 2015年10月26日
 * @memo
 */
public class TransAction implements Serializable {

	private static final long serialVersionUID = -854634427837554370L;

	/** 交易流水号 */
	private String transFlowId;
	/** 交易流水号 */
	private String requestId;
	/** 结果状态 */
	private RequestColumnValues.TransStatus resultStatus;
	/** 返回流水号 */
	private String returnId;
	/** 返回码 */
	private String returnCode;
	/** 返回描述 */
	private String returnInfo;
	/** 操作步骤 */
	private RequestColumnValues.TransFlowOperate transFlowOperate;
	/** 报文编号 */
	private String msgId;
	/** 报文类型 */
	private RequestColumnValues.MsgType msgType;
	/** 交易流水 */
	private TransRecordFlow transRecordFlow;
	
	private String operationId;
	
	private String 	opinion;

	public String getTransFlowId() {
		return transFlowId;
	}

	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public RequestColumnValues.TransStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(RequestColumnValues.TransStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public RequestColumnValues.TransFlowOperate getTransFlowOperate() {
		return transFlowOperate;
	}

	public void setTransFlowOperate(RequestColumnValues.TransFlowOperate transFlowOperate) {
		this.transFlowOperate = transFlowOperate;
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

	public TransRecordFlow getTransRecordFlow() {
		return transRecordFlow;
	}

	public void setTransRecordFlow(TransRecordFlow transRecordFlow) {
		this.transRecordFlow = transRecordFlow;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	

}
