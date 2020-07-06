package com.qifenqian.bms.basemanager.protocolcontent.bean;

import java.io.Serializable;

/**
 * 协议导出bean
 * 
 * @author shen
 * 
 */
public class ProtocolContentExportBean implements Serializable {

	private String id;
	private String merchantCode;
	private String merchantName;
	private String protocolTemplateName;
	private String period;
	private String isSkipHoliday;
	private String bankCardNo;
	private String bankCardName;
	private String bankName;
	private String OpenAddressRec;
	
	private String CNAPS;
	private String serviceProvider;
	private String custManager;
	private String h5Gateway;
	private String mobilePlugin;
	private String onecodeColl;
	private String pcGateway;
	private String h5tGateway;
	private String instUserName;
	private String disableDate;
	private String status;
	private String updateDatetime;
	private String insertDatetime;
	private static final long serialVersionUID = -4494534696191982299L;
	
	
	public String getInsertDatetime() {
		return insertDatetime;
	}
	public void setInsertDatetime(String insertDatetime) {
		this.insertDatetime = insertDatetime;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}
	public String getH5tGateway() {
		return h5tGateway;
	}
	public void setH5tGateway(String h5tGateway) {
		this.h5tGateway = h5tGateway;
	}
	public String getH5Gateway() {
		return h5Gateway;
	}
	public void setH5Gateway(String h5Gateway) {
		this.h5Gateway = h5Gateway;
	}
	public String getMobilePlugin() {
		return mobilePlugin;
	}
	public void setMobilePlugin(String mobilePlugin) {
		this.mobilePlugin = mobilePlugin;
	}
	public String getOnecodeColl() {
		return onecodeColl;
	}
	public void setOnecodeColl(String onecodeColl) {
		this.onecodeColl = onecodeColl;
	}
	public String getPcGateway() {
		return pcGateway;
	}
	public void setPcGateway(String pcGateway) {
		this.pcGateway = pcGateway;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getIsSkipHoliday() {
		return isSkipHoliday;
	}
	public void setIsSkipHoliday(String isSkipHoliday) {
		this.isSkipHoliday = isSkipHoliday;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankCardName() {
		return bankCardName;
	}
	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getOpenAddressRec() {
		return OpenAddressRec;
	}
	public void setOpenAddressRec(String openAddressRec) {
		OpenAddressRec = openAddressRec;
	}
	public String getCNAPS() {
		return CNAPS;
	}
	public void setCNAPS(String cNAPS) {
		CNAPS = cNAPS;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getCustManager() {
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
