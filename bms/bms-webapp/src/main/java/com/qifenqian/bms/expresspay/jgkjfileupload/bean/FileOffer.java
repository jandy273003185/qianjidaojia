package com.qifenqian.bms.expresspay.jgkjfileupload.bean;

import java.io.Serializable;
import java.util.Date;

public class FileOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8968993541346794972L;

	/** 文件编号 */
	private String fileId;
	/** 渠道编号 */
	private String channelId;
	/** 文件类型 */
	private String fileType;
	/** 接收日期 */
	private String receiveDate;
	/** 数据日期 */
	private String workDate;
	/** 文件名 */
	private String fileName;
	/** 存储路径 */
	private String storagePath;
	/** 总记录数 */
	private long totalCount;
	/** 写入日期 */
	private String instDate;
	/** 写入时间 */
	private Date instDatetime;
	/** 状态 */
	private String status;
	/** 处理开始时间 */
	private Date balBeginDatetime;
	/** 处理结束时间 */
	private Date balEndDatetime;
	/** 处理备注 */
	private String memo;
	/** 返回更新时间 */
	private Date updateDatetime;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBalBeginDatetime() {
		return balBeginDatetime;
	}

	public void setBalBeginDatetime(Date balBeginDatetime) {
		this.balBeginDatetime = balBeginDatetime;
	}

	public Date getBalEndDatetime() {
		return balEndDatetime;
	}

	public void setBalEndDatetime(Date balEndDatetime) {
		this.balEndDatetime = balEndDatetime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
