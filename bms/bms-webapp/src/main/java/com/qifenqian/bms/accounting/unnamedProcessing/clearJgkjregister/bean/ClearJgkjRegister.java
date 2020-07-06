package com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.bean;

import java.util.Date;

public class ClearJgkjRegister {
    /** 编号/发往交广科技流水号*/
	private String id;
    /** 交易编号*/
	private String transFlowId;
    /** 客户号*/
	private String custId;
    /** 交易码*/
	private String transCode;
    /** 手机号*/
	private String mobile;
    /** 密码*/
	private String pin;
    /** 名称*/
	private String name;
    /** 证件类型*/
	private String idType;
    /** 证件号码*/
	private String idCode;
    /** 生日*/
	private String birthday;
    /** 持卡人地址*/
	private String addr;
    /** 持卡人邮编*/
	private String post;
    /** 持卡人电邮*/
	private String email;
    /** 预留字段*/
	private String reserve;
    /** 七分钱交易发送日期YYYYMMDD*/
	private String sendDate;
    /** 七分钱交易发送时间HHmmss*/
	private String sendTime;
    /** 状态*/
	private String status;
    /** 写入时间*/
	private Date instDatetime;
    /** 交广科技返回日期YYYYMMDD*/
	private String rtnDate;
    /** 交广科技交易返回时间HHmmss*/
	private String rtnTime;
    /** 交广科技返回交易流水号*/
	private String rtnSeq;
    /** 返回码*/
	private String rtnCode;
    /** 返回信息*/
	private String rtnInfo;
    /** 返回卡号*/
	private String rtnCardNo;
    /** 报文响应时间（七分钱系统）*/
	private Date updateDatetime;
    /** 对账状态*/
	private String balStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransFlowId() {
		return transFlowId;
	}
	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
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
	public String getRtnSeq() {
		return rtnSeq;
	}
	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnInfo() {
		return rtnInfo;
	}
	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}
	public String getRtnCardNo() {
		return rtnCardNo;
	}
	public void setRtnCardNo(String rtnCardNo) {
		this.rtnCardNo = rtnCardNo;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getBalStatus() {
		return balStatus;
	}
	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}
	
   }