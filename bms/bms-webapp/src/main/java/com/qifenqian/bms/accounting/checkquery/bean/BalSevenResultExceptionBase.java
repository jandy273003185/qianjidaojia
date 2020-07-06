package com.qifenqian.bms.accounting.checkquery.bean;

import java.io.Serializable;

public class BalSevenResultExceptionBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1681218875928980182L;

    private String channelId;

    private String batchId;

    private String sevenpayId;

    private String transStatus;

    private String bankBackId;

    private String bankBackResult;

    private String bankBackTime;

    private String instTime;

    private String balResult;

    private String balTime;

    private String dealFlag;

    private String dealUser;

    private String dealTime;

    private String dealMemo;
    
    private String beginBalTime;
    private String endBalTime;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSevenpayId() {
		return sevenpayId;
	}

	public void setSevenpayId(String sevenpayId) {
		this.sevenpayId = sevenpayId;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getBankBackId() {
		return bankBackId;
	}

	public void setBankBackId(String bankBackId) {
		this.bankBackId = bankBackId;
	}

	public String getBankBackResult() {
		return bankBackResult;
	}

	public void setBankBackResult(String bankBackResult) {
		this.bankBackResult = bankBackResult;
	}

	public String getBankBackTime() {
		return bankBackTime;
	}

	public void setBankBackTime(String bankBackTime) {
		this.bankBackTime = bankBackTime;
	}

	public String getInstTime() {
		return instTime;
	}

	public void setInstTime(String instTime) {
		this.instTime = instTime;
	}

	public String getBalResult() {
		return balResult;
	}

	public void setBalResult(String balResult) {
		this.balResult = balResult;
	}

	public String getBalTime() {
		return balTime;
	}

	public void setBalTime(String balTime) {
		this.balTime = balTime;
	}

	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealMemo() {
		return dealMemo;
	}

	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}

	public String getBeginBalTime() {
		return beginBalTime;
	}

	public void setBeginBalTime(String beginBalTime) {
		this.beginBalTime = beginBalTime;
	}

	public String getEndBalTime() {
		return endBalTime;
	}

	public void setEndBalTime(String endBalTime) {
		this.endBalTime = endBalTime;
	}

}
