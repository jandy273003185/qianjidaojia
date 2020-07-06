package com.qifenqian.bms.basemanager.agency.bean;

import java.io.Serializable;

public class AgenReport implements Serializable{

	
	
	/*************************************************************/
	
	private String id;    			/** 编号  */
	private String beginWorkDate;   /** 开始账期  */
	private String endDate;   	/** 结束账期  */
	private String agentCustId;		/** 代理商CUSTID  */
	private String agentMerchantId;
	private String custName;		/** 代理商名称  */
	private String custManager;		/** 客户经理  */
	private String workDate;		/** 数据日期  */
	private String merCustId;		/** 商户CUSTID  */
	private String receiveCount;	/** 商户收款笔数  */
	private String receiveTotalAmt;	/** 商户收款金额  */
	private String refundCount;		/** 商户退款笔数  */
	private String refundTotalAmt;	/** 商户退款金额  */
	private String validAmt;		/** 有效金额  */
	private String agentRate;	/** 代理商底价费率  */
	private String commision;		/** 佣金收入  */
	
	private static final long serialVersionUID = 2716185073933276322L;
	/*************************************************************/
	
	public String getId() {
		return id;
	}
	public String getAgentMerchantId() {
		return agentMerchantId;
	}
	public void setAgentMerchantId(String agentMerchantId) {
		this.agentMerchantId = agentMerchantId;
	}
	public String getAgentRate() {
		return agentRate;
	}
	public void setAgentRate(String agentRate) {
		this.agentRate = agentRate;
	}
	public String getCustManager() {
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}
	public String getBeginWorkDate() {
		return beginWorkDate;
	}
	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getValidAmt() {
		return validAmt;
	}
	public void setValidAmt(String validAmt) {
		this.validAmt = validAmt;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getAgentCustId() {
		return agentCustId;
	}
	public void setAgentCustId(String agentCustId) {
		this.agentCustId = agentCustId;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getMerCustId() {
		return merCustId;
	}
	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}
	public String getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(String receiveCount) {
		this.receiveCount = receiveCount;
	}
	public String getReceiveTotalAmt() {
		return receiveTotalAmt;
	}
	public void setReceiveTotalAmt(String receiveTotalAmt) {
		this.receiveTotalAmt = receiveTotalAmt;
	}
	public String getRefundCount() {
		return refundCount;
	}
	public void setRefundCount(String refundCount) {
		this.refundCount = refundCount;
	}
	public String getRefundTotalAmt() {
		return refundTotalAmt;
	}
	public void setRefundTotalAmt(String refundTotalAmt) {
		this.refundTotalAmt = refundTotalAmt;
	}
	public String getCommision() {
		return commision;
	}
	public void setCommision(String commision) {
		this.commision = commision;
	}
}
