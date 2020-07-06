package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class BalResultStatistic extends QfqexceptionBase {

	private String batchId;
	private String balDate;
	private String workDate;
	private String channelIdDesc;
	private String fileId;
	private String transStatusDesc;
	private String transTypeDesc;
	private String systemDesc;
	private Integer totalCount;
	private BigDecimal totalAmt;
	private Integer balEqualCount;
	private BigDecimal balEqualAmt;
	private Integer balDoubtCount;
	private BigDecimal balDoubtAmt;
	private Integer balErrorCount;
	private BigDecimal balErrorAmt;
	private Integer selfDoubtCount;
	private BigDecimal selfDoubtAmt;
	private String statusDesc;
	private String memo;
	

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
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


	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Integer getSelfDoubtCount() {
		return selfDoubtCount;
	}

	public void setSelfDoubtCount(Integer selfDoubtCount) {
		this.selfDoubtCount = selfDoubtCount;
	}

	public BigDecimal getSelfDoubtAmt() {
		return selfDoubtAmt;
	}

	public void setSelfDoubtAmt(BigDecimal selfDoubtAmt) {
		this.selfDoubtAmt = selfDoubtAmt;
	}

	public Integer getBalDoubtCount() {
		return balDoubtCount;
	}

	public void setBalDoubtCount(Integer balDoubtCount) {
		this.balDoubtCount = balDoubtCount;
	}

	public BigDecimal getBalDoubtAmt() {
		return balDoubtAmt;
	}

	public void setBalDoubtAmt(BigDecimal balDoubtAmt) {
		this.balDoubtAmt = balDoubtAmt;
	}

	public Integer getBalErrorCount() {
		return balErrorCount;
	}

	public void setBalErrorCount(Integer balErrorCount) {
		this.balErrorCount = balErrorCount;
	}

	public BigDecimal getBalErrorAmt() {
		return balErrorAmt;
	}

	public void setBalErrorAmt(BigDecimal balErrorAmt) {
		this.balErrorAmt = balErrorAmt;
	}

	public Integer getBalEqualCount() {
		return balEqualCount;
	}

	public void setBalEqualCount(Integer balEqualCount) {
		this.balEqualCount = balEqualCount;
	}

	public BigDecimal getBalEqualAmt() {
		return balEqualAmt;
	}

	public void setBalEqualAmt(BigDecimal balEqualAmt) {
		this.balEqualAmt = balEqualAmt;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBalDate() {
		return balDate;
	}

	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}

	public String getChannelIdDesc() {
		return channelIdDesc;
	}

	public void setChannelIdDesc(String channelIdDesc) {
		this.channelIdDesc = channelIdDesc;
	}


	public String getTransStatusDesc() {
		return transStatusDesc;
	}

	public void setTransStatusDesc(String transStatusDesc) {
		this.transStatusDesc = transStatusDesc;
	}

	public String getTransTypeDesc() {
		return transTypeDesc;
	}

	public void setTransTypeDesc(String transTypeDesc) {
		this.transTypeDesc = transTypeDesc;
	}

	public String getSystemDesc() {
		return systemDesc;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}