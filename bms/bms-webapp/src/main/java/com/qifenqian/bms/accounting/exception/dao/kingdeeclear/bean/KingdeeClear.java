package com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean;

import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;

public class KingdeeClear extends TransAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2611805270900466032L;

	/** 清算编号/单据编号 */
	private String clearId;
	/** 业务类型:个人提现PERSONAL_WITHDRAW，商户结算MERCHANT_SETTLE */
	private String businessType;
	/** 业务编号 */
	private String operId;
	/** 业务日期 */
	private String fDate;
	/** 往来单位名称 */
	private String fcontactunitName;
	/** 往来单位编码 */
	private String fcontactunitNumber;
	/** 往来单位类型 */
	private String fcontactunitType;
	/** 收款单位名称 */
	private String frectunitName;
	/** 收款单位编码 */
	private String frectunitNumber;
	/** 收款单位类型 */
	private String frectunitType;
	/** 是否期初单据 */
	private String fisinit;
	/** 七分钱会计日期 */
	private String workDate;
	/** 七分钱交易发送日期YYYYMMDD */
	private String sendDate;
	/** 七分钱交易发送时间HHmmss */
	private String sendTime;
	/** 状态 */
	private String status;
	/** 写入人 */
	private String instUser;
	/** 写入日期 */
	private String instDate;
	/** 写入时间 */
	private Date instDatetime;
	/** 返回日期 */
	private String rtnDate;
	/** 返回时间 */
	private String rtnTime;
	/** 返回ID */
	private String rtnId;
	/** 返回number */
	private String rtnNumber;
	/** 交易是否成功 */
	private String rtnIsSuccess;
	/** 返回错误码 */
	private String rtnErrorCode;
	/** 返回信息 */
	private String rtnMessage;
	/** 返回堆栈跟踪 */
	private String rtnStackTrace;
	/** 返回错误信息列表： */
	private String rtnErrors;
	/** 返回写入时间 */
	private String updateDatetime;
	/** 对账状态 */
	private String balStatus;
	/** 银行处理状态 代表：A空； B银行处理中； C银行交易成功；D银行交易失败；E银行交易未确认 **/
	private String bankStatus;
	
	private String fEbMsg;
	
	private String fBankMsg;
	
	public String getfEbMsg() {
		return fEbMsg;
	}

	public void setfEbMsg(String fEbMsg) {
		this.fEbMsg = fEbMsg;
	}

	public String getfBankMsg() {
		return fBankMsg;
	}

	public void setfBankMsg(String fBankMsg) {
		this.fBankMsg = fBankMsg;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public String getFcontactunitName() {
		return fcontactunitName;
	}

	public void setFcontactunitName(String fcontactunitName) {
		this.fcontactunitName = fcontactunitName;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public String getFcontactunitType() {
		return fcontactunitType;
	}

	public void setFcontactunitType(String fcontactunitType) {
		this.fcontactunitType = fcontactunitType;
	}

	public String getFrectunitName() {
		return frectunitName;
	}

	public void setFrectunitName(String frectunitName) {
		this.frectunitName = frectunitName;
	}

	public String getFrectunitNumber() {
		return frectunitNumber;
	}

	public void setFrectunitNumber(String frectunitNumber) {
		this.frectunitNumber = frectunitNumber;
	}

	public String getFrectunitType() {
		return frectunitType;
	}

	public void setFrectunitType(String frectunitType) {
		this.frectunitType = frectunitType;
	}

	public String getFisinit() {
		return fisinit;
	}

	public void setFisinit(String fisinit) {
		this.fisinit = fisinit;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
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

	public String getRtnDate() {
		return rtnDate;
	}

	public void setRtnDate(String rtnDate) {
		this.rtnDate = rtnDate;
	}

	public String getRtnTime() {
		return rtnTime;
	}

	public void setRtnTime(String rtnTime) {
		this.rtnTime = rtnTime;
	}

	public String getRtnId() {
		return rtnId;
	}

	public void setRtnId(String rtnId) {
		this.rtnId = rtnId;
	}

	public String getRtnNumber() {
		return rtnNumber;
	}

	public void setRtnNumber(String rtnNumber) {
		this.rtnNumber = rtnNumber;
	}

	public String getRtnIsSuccess() {
		return rtnIsSuccess;
	}

	public void setRtnIsSuccess(String rtnIsSuccess) {
		this.rtnIsSuccess = rtnIsSuccess;
	}

	public String getRtnErrorCode() {
		return rtnErrorCode;
	}

	public void setRtnErrorCode(String rtnErrorCode) {
		this.rtnErrorCode = rtnErrorCode;
	}

	public String getRtnMessage() {
		return rtnMessage;
	}

	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}

	public String getRtnStackTrace() {
		return rtnStackTrace;
	}

	public void setRtnStackTrace(String rtnStackTrace) {
		this.rtnStackTrace = rtnStackTrace;
	}

	public String getRtnErrors() {
		return rtnErrors;
	}

	public void setRtnErrors(String rtnErrors) {
		this.rtnErrors = rtnErrors;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getBalStatus() {
		return balStatus;
	}

	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

}
