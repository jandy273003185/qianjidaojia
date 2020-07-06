package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgentSettleBean implements Serializable{
	private String settleId;
	private String agentId;
	private String workDate;
	private String settleBeginDate;
	private String settleEndDate;
	private String protocolId;
	private BigDecimal settleAmt;
	private String finishDate;
	private String status;
	private String memo;
	private String instUser;
	private String instDate;
	private String instDatetime;
	private String auditUser;
	private String auditDatetime;
	private String verifiedUser;
	private String verifiedDatetime;
	private static final long serialVersionUID = -1974101805090576902L;
	
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
	
	public String getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	public String getVerifiedUser() {
		return verifiedUser;
	}
	public void setVerifiedUser(String verifiedUser) {
		this.verifiedUser = verifiedUser;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getSettleBeginDate() {
		return settleBeginDate;
	}
	public void setSettleBeginDate(String settleBeginDate) {
		this.settleBeginDate = settleBeginDate;
	}
	public String getSettleEndDate() {
		return settleEndDate;
	}
	public void setSettleEndDate(String settleEndDate) {
		this.settleEndDate = settleEndDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getInstDate() {
		return instDate;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getAuditDatetime() {
		return auditDatetime;
	}
	public void setAuditDatetime(String auditDatetime) {
		this.auditDatetime = auditDatetime;
	}
	public String getVerifiedDatetime() {
		return verifiedDatetime;
	}
	public void setVerifiedDatetime(String verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}
	
	
}
