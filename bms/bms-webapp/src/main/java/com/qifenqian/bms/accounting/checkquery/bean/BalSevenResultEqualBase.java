package com.qifenqian.bms.accounting.checkquery.bean;

import java.io.Serializable;

public class BalSevenResultEqualBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6554832056733032934L;

	private String batchId;

    private String dataDate;

    private String transType;

    private String transCurrency;

    private String transStatus;

    private String bankBackId;

    private String bankBackResult;

    private String bankBackTime;

    private String instTime;

    private String balResult;

    private String balTime;

    private String balMemo;
    private String beginBalTime;
    private String endBalTime;
    public String getEndBalTime() {
		return endBalTime;
	}

	public void setEndBalTime(String endBalTime) {
		this.endBalTime = endBalTime;
	}
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
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

    public String getBeginBalTime() {
		return beginBalTime;
	}

	public void setBeginBalTime(String beginBalTime) {
		this.beginBalTime = beginBalTime;
	}

	public String getBalMemo() {
        return balMemo;
    }

    public void setBalMemo(String balMemo) {
        this.balMemo = balMemo;
    }
   
}
