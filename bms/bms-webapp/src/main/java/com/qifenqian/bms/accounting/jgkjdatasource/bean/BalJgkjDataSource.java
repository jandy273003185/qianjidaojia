package com.qifenqian.bms.accounting.jgkjdatasource.bean;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class BalJgkjDataSource implements Serializable{
	


	private String fileId;

    private Integer seq;
    
   
    private String workDate;
    private String channelSerialSeq;

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
    
    
    public String getChannelSerialSeq() {
		return channelSerialSeq;
	}

	public void setChannelSerialSeq(String channelSerialSeq) {
		this.channelSerialSeq = channelSerialSeq;
	}

	public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getReceiveDatetime() {
        return receiveDatetime;
    }

    public void setReceiveDatetime(String receiveDatetime) {
        this.receiveDatetime = receiveDatetime;
    }

    public String getReturnDatetime() {
        return returnDatetime;
    }

    public void setReturnDatetime(String returnDatetime) {
        this.returnDatetime = returnDatetime;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
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