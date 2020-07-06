package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class MerchantFilingInfo implements Serializable{
	
	

	private String id;//报备编号
	
	private String merchantCode;//商户编号
	
	private String mchRole;//商户角色 0 线上商户,1 线下商户
	
	private String patchNo;//报备批次号
	
	private String channelNo;//报备渠道
	
	private String filingStatus;//报备状态 01 未提交报备，00已提交报备
	
	private String fileStatus;//报备文件上传状态 00 成功 ，99 失败
	
	private String auditStatus;//报备审核状态 00：成功 99：失败 01：待提交审核 02：提交审核中
	
	private String merStatus;//商户进件状态
	
	private String merMsg;//进件描述
	
	private String createTime;//创建时间
	
	private String modifyTime;//修改时间
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3029777393710061965L;
	
	private String custName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMchRole() {
		return mchRole;
	}

	public void setMchRole(String mchRole) {
		this.mchRole = mchRole;
	}

	public String getPatchNo() {
		return patchNo;
	}

	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getMerStatus() {
		return merStatus;
	}

	public void setMerStatus(String merStatus) {
		this.merStatus = merStatus;
	}

	public String getMerMsg() {
		return merMsg;
	}

	public void setMerMsg(String merMsg) {
		this.merMsg = merMsg;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
}
