package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

public class BalBocResultException {
    private String batchId;

    private String sevenpayId;

    private String bankReciveTime;

    private String transType;

    private String transCurrency;

    private BigDecimal transAmt;

    private String transStatus;

    private String bankFinishTime;

    private String instTime;

    private String balResult;

    private String balTime;
   

	private String balMemo;

    private String dealFlag;

    private String dealUser;

    private String dealTime;

    private String dealMemo;
    
    private String fileId;

    private Integer seq;

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

    public String getBankReciveTime() {
        return bankReciveTime;
    }

    public void setBankReciveTime(String bankReciveTime) {
        this.bankReciveTime = bankReciveTime;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransCurrency() {
        return transCurrency;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getBankFinishTime() {
        return bankFinishTime;
    }

    public void setBankFinishTime(String bankFinishTime) {
        this.bankFinishTime = bankFinishTime;
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

    public String getBalMemo() {
        return balMemo;
    }

    public void setBalMemo(String balMemo) {
        this.balMemo = balMemo;
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
}