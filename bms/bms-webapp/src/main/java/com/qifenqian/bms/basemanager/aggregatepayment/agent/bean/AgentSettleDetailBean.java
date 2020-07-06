package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentSettleDetailBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;

	private String detailId;
	private String settleId;
	private String agentId;
	private Date workDate;
	
	private String merchantId;
	private String channelCode;
	private Date settleBeginDate;
	private Date settleEndDate;
	private BigDecimal transTotalFee;
	
	private Date instDate;
	private Date instDatetime;
	private String auditUser;
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getSettleId() {
		return settleId;
	}
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public Date getSettleBeginDate() {
		return settleBeginDate;
	}
	public void setSettleBeginDate(Date settleBeginDate) {
		this.settleBeginDate = settleBeginDate;
	}
	public Date getSettleEndDate() {
		return settleEndDate;
	}
	public void setSettleEndDate(Date settleEndDate) {
		this.settleEndDate = settleEndDate;
	}
	public BigDecimal getTransTotalFee() {
		return transTotalFee;
	}
	public void setTransTotalFee(BigDecimal transTotalFee) {
		this.transTotalFee = transTotalFee;
	}
	public Date getInstDate() {
		return instDate;
	}
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}
	public Date getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	
}
