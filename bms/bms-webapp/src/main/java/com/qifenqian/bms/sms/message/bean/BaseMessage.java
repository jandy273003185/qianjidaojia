package com.qifenqian.bms.sms.message.bean;

import java.math.BigDecimal;
/**
 * 
 * @author shen
 *
 */
public class BaseMessage {

	/** 编号 */
	private String id;
	/** 客户姓名 */
	private String custName;
	/** 客户金额 */
	private BigDecimal custAmt;
	/** 手机号码 */
	private String mobile;
	/** 数据日期 */
	private String dateData;
	/** 信息内容 */
	private String content;
	/** 状态：INIT,SUCCESS,EXCEPTION,FAILURE */
	private String status;
	/** 创建人 */
	private String instUser;
	
	private String sheetIndex;
	/** 创建时间 */
	private String instDatetime;
	/** 发送时间 */
	private String sendTime;

	private String sendStartTime;

	private String sendEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getCustAmt() {
		return custAmt;
	}

	public void setCustAmt(BigDecimal custAmt) {
		this.custAmt = custAmt;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDateData() {
		return dateData;
	}

	public void setDateData(String dateData) {
		this.dateData = dateData;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(String sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

}
