package com.qifenqian.bms.platform.common.message;

public class MessageDetailBean {
	/** 文件编号 */
	private String fileId;
	/** 序号/行号 */
	private long seq;
	/** 工作日期*/	
	private String workDate;
	/**渠道编号*/
	private String channelId;
	/**栏位索引编号*/
	private String columnId;
	/**栏位名称*/
	private String fieldCode;
	/**栏位值*/
	private String fieldValue;
	/** 写入时间 */
	private String instDatetime;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
}
