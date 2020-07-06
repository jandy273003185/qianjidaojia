package com.qifenqian.bms.basemanager.protocolcontent.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 协议
 * 
 * @author shen
 * 
 */
public class ProtocolContent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4494534696191982299L;
	/** 协议编号 **/
	private String id;

	private String protocolName;

	private String custId;

	/** 协议模板编号 **/
	private String templateId;
	
	/**
	 * 生效时间
	 */
	private String validDate;
	
	/**
	 * 失效时间
	 */
	private String disableDate;

	/** 协议模板内容 **/
	private String protocolContent;

	/** 状态 **/
	private String status;

	/** 备注 **/
	private String memo;

	/** 创建人 **/
	private String instUser;

	/** 创建时间 **/
	private Date instDatetime;

	/** 修改人 **/
	private String updateUser;

	/** 修改时间 **/
	private Date updateDatetime;
	
	private String upDatetime;
	
	private String inDatetime;

	private String protocolTemplateName;

	private String instUserName;

	private String updateUserName;

	private String merchantCode;

	private String rate;
	
	private String userId;
	
	private String userName;
	
	private String merchantName;
	
	private String tempStatus;
	
	private String isUpdate;
	
		
	public String getInDatetime() {
		return inDatetime;
	}

	public void setInDatetime(String inDatetime) {
		this.inDatetime = inDatetime;
	}

	public String getUpDatetime() {
		return upDatetime;
	}

	public void setUpDatetime(String upDatetime) {
		this.upDatetime = upDatetime;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getProtocolContent() {
		return protocolContent;
	}

	public void setProtocolContent(String protocolContent) {
		this.protocolContent = protocolContent;
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

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getProtocolTemplateName() {
		return protocolTemplateName;
	}

	public void setProtocolTemplateName(String protocolTemplateName) {
		this.protocolTemplateName = protocolTemplateName;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

}
