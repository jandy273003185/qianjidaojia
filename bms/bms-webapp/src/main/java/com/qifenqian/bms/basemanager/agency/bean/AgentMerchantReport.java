package com.qifenqian.bms.basemanager.agency.bean;

import java.io.Serializable;

public class AgentMerchantReport implements Serializable{


	/*************************************************************/
	
	private String beginWorkDate;   /** 开始账期  */
	private String endDate;   	/** 结束账期  */
	private String merCustId;		/** 商户ID  */
	private String merRate;		/** 商户费率  */
	private String agentId;		/** 代理商ID  */
	private String agentName;		/** 代理商名称  */
	private String agentRate;		/** 代理商费率*/
	private String merchantName;	/** 商户名称  */
	private String receiveCount;	/** 商户收款笔数  */
	private String receiveTotalAmt;	/** 商户收款金额  */
	private String refundCount;		/** 商户退款笔数  */
	private String refundTotalAmt;	/** 商户退款金额  */
	private String validAmt;		/** 有效金额  */
	private String commision;		/** 佣金收入  */

	/*************************************************************/
	private static final long serialVersionUID = 5074163096618883445L;
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBeginWorkDate() {
		return beginWorkDate;
	}

	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}

	public String getMerCustId() {
		return merCustId;
	}

	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}


	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public String getValidAmt() {
		return validAmt;
	}

	public void setValidAmt(String validAmt) {
		this.validAmt = validAmt;
	}

	public String getCommision() {
		return commision;
	}

	public void setCommision(String commision) {
		this.commision = commision;
	}

	public String getMerRate() {
		return merRate;
	}

	public void setMerRate(String merRate) {
		this.merRate = merRate;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(String agentRate) {
		this.agentRate = agentRate;
	}

}
