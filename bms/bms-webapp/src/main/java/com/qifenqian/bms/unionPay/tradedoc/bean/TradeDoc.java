package com.qifenqian.bms.unionPay.tradedoc.bean;

import java.io.Serializable;
import java.util.Date;

public class TradeDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917648211805902203L;

	private String fileId;
	private String seq;
	private String workDate;
	private String dateSource;
	private String instDate;
	private Date instDatetime;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getDateSource() {
		return dateSource;
	}

	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
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

}
