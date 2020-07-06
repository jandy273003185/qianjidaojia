package com.qifenqian.bms.basemanager.certify.bean;

import java.io.Serializable;
import java.util.Date;

public class FileBean implements Serializable {

	private static final long serialVersionUID = -682833961334225889L;

	/** 文件编号 */
	private String fileId;
	/** 上传日期 */
	private String uploadDate;
	/** 文件名 */
	private String fileName;
	/** 存储路径 */
	private String storagePath;

	private String fileType;

	/** 总记录数 */
	private int totalCount;
	/** 写入日期 */
	private String instDate;
	/** 数据日期 */
	private String workDate;
	/** 写入时间 */
	private Date instDatetime;
	/** UPLOAD_ING:上传中；UPLOAD_OVER：完成；INVALID：无效；ERROR：异常 */
	private String status;
	/** 处理备注 */
	private String memo;
	
	private  String receiptId;
	/** 返回更新时间 */
	private Date updateDatetime;

	private String startUpdateTime;

	private String endUpdateTime;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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

	public String getStartUpdateTime() {
		return startUpdateTime;
	}

	public void setStartUpdateTime(String startUpdateTime) {
		this.startUpdateTime = startUpdateTime;
	}

	public String getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(String endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}
}
