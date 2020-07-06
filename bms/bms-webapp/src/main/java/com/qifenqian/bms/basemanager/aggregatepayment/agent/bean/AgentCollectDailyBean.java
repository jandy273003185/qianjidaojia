package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgentCollectDailyBean implements Serializable{
	private String dailyId;//明细Id
	private String agentId;//代理Id
	private String workDate;//七分钱执行结算的会计日期（对应存储过程的参数）
	private String merchantId;//商户id
	private String channelCode;//渠道编码
	private BigDecimal transTotalFee;//应收金额
	private String status;//状态
	private String instDate;//记账日期
	private String instDatetime;	//生成日期
	private static final long serialVersionUID = -1974101805090576902L;
	public String getDailyId() {
		return dailyId;
	}
	public void setDailyId(String dailyId) {
		this.dailyId = dailyId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
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
	public BigDecimal getTransTotalFee() {
		return transTotalFee;
	}
	public void setTransTotalFee(BigDecimal transTotalFee) {
		this.transTotalFee = transTotalFee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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

	
}
