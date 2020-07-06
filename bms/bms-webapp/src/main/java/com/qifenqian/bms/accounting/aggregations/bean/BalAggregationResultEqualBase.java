package com.qifenqian.bms.accounting.aggregations.bean;

import java.io.Serializable;

public class BalAggregationResultEqualBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8959283072884812015L;
	

	private String batchId;

    private String balDate;

    private String workDate;



    private String fileId;

    private String externalTotalCount;

    private String sevenmallTotalCount;

    private String dealUser;

    private String dealDatetime;

    private String dealMemo;

    private String status;
    
    private String updateDatetime;
    private String beginBalTime;
    private String endBalTime;
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBalDate() {
		return balDate;
	}
	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getExternalTotalCount() {
		return externalTotalCount;
	}
	public void setExternalTotalCount(String externalTotalCount) {
		this.externalTotalCount = externalTotalCount;
	}
	public String getSevenmallTotalCount() {
		return sevenmallTotalCount;
	}
	public void setSevenmallTotalCount(String sevenmallTotalCount) {
		this.sevenmallTotalCount = sevenmallTotalCount;
	}
	public String getDealUser() {
		return dealUser;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public String getDealDatetime() {
		return dealDatetime;
	}
	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}
	public String getDealMemo() {
		return dealMemo;
	}
	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
   
    
}
