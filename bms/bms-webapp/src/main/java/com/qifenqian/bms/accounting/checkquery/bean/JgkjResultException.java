package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class JgkjResultException extends JgkjResultExceptionBase{
	private String balDate;
	private String channelId ;
	private String fileId;
	private int seq;
	private String workDate;
	private String batchId;
	private String sevenpayId;
	private String platformId;
	private String cardNo;
	private String receiveDatetime;
	private String returnDatetime;
	private BigDecimal transAmt;
	private String transType;
	private String transStatus;
	private String reserve;
	private String instDate;
	private String instDatetime;
	private String balResult;
	private String balDatetime;
	private String balMemo;
	private String dealFlag;
	private String dealUser;
	private String dealDatetime;
	private String dealMemo;
	
	public String getFileId() {
		return fileId;
	}
	public int getSeq() {
		return seq;
	}
	public String getWorkDate() {
		return workDate;
	}
	public String getCardNo() {
		return cardNo;
	}
	public String getReceiveDatetime() {
		return receiveDatetime;
	}
	public String getReturnDatetime() {
		return returnDatetime;
	}
	public String getTransType() {
		return transType;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public String getReserve() {
		return reserve;
	}
	public String getInstDate() {
		return instDate;
	}
	public String getBalResult() {
		return balResult;
	}
	public String getBalDatetime() {
		return balDatetime;
	}
	public String getDealFlag() {
		return dealFlag;
	}
	public String getDealUser() {
		return dealUser;
	}
	public String getDealDatetime() {
		return dealDatetime;
	}
	public String getDealMemo() {
		return dealMemo;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setReceiveDatetime(String receiveDatetime) {
		this.receiveDatetime = receiveDatetime;
	}
	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public void setBalResult(String balResult) {
		this.balResult = balResult;
	}
	public void setBalDatetime(String balDatetime) {
		this.balDatetime = balDatetime;
	}
	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}
	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}
	public String getBalDate() {
		return balDate;
	}
	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
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
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getBalMemo() {
		return balMemo;
	}
	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}
}
