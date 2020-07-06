package com.qifenqian.bms.basemanager.certify.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class IdentityDetailBean implements Serializable{
	
	private static final long serialVersionUID = -682833961334225889L;

	private String validateId;//验证编号
	private String fileId;//文件编号
	private String custId;//客户编号
	private String mobile;//手机号码
	private String custName;//客户名称
	private String certifyNo;//证件号码
	private String workDate;//数据日期
	private String status;//验证状态
	private String instDate;//写入日期
	private Timestamp instDatetime;//写入时间
	private String rtnCode;//返回码
	private String rtnMsg;//返回信息
	private String dealStatus;//处理状态
	private String dealUser;//处理人
	private Date dealDatetime;//处理时间
	private String dealMemo;//处理备注
	private Date updateDatetime;//返回更新时间
	
	private String dealUserName;
	
	public String getDealUserName() {
		return dealUserName;
	}
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	public String getValidateId() {
		return validateId;
	}
	public void setValidateId(String validateId) {
		this.validateId = validateId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCertifyNo() {
		return certifyNo;
	}
	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstDate() {
		return instDate;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public Timestamp getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(Timestamp instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public String getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	public String getDealUser() {
		return dealUser;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public Date getDealDatetime() {
		return dealDatetime;
	}
	public void setDealDatetime(Date dealDatetime) {
		this.dealDatetime = dealDatetime;
	}
	public String getDealMemo() {
		return dealMemo;
	}
	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
}
