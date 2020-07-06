package com.qifenqian.bms.bal.filereceive.bean;

import java.io.Serializable;

/**
 * @author li.shi
 * @date 2017年2月23日
 */

public class FileReceive implements Serializable{
		
	private static final long serialVersionUID = 2603085161283230911L;
	
	/** 文件编号 */
	private String fileId;
	/** 渠道编号 */
	private String channelId;
	private String channelName;
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
	/** 总记录数 */
	private String instUser;
	/** 写入日期 */
	private String instDate;
	/** 写入时间 */
	private String instDatetime;
	/** 状态：INIT/ANALYSIS/BMS_ING/BMS_OVER/INVALID/ERROR */
	private String status;
	/** 处理开始时间 */
	private String balBeginDatetime;
	/** 处理结束时间 */
	private String balEndDatetime;
	/** 处理备注 */
	private String memo;
	/** 返回更新时间 */
	private String updateDatetime;
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
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBalBeginDatetime() {
		return balBeginDatetime;
	}
	public void setBalBeginDatetime(String balBeginDatetime) {
		this.balBeginDatetime = balBeginDatetime;
	}
	public String getBalEndDatetime() {
		return balEndDatetime;
	}
	public void setBalEndDatetime(String balEndDatetime) {
		this.balEndDatetime = balEndDatetime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
