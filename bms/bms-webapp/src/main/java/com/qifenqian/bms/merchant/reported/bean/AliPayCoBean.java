package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class AliPayCoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9044922753778506056L;
	/**
	 * 渠道号
	 */
	private String channelNo;
	/**
	 * 商户号
	 */
	private String merchantCode;
	private String custName;
	/**
	 * 商户支付宝账号
	 */
	private String account;
	
	/**
	 * 联系人名称
	 */
	private String contactName;
	/**
	 * 联系人手机号码
	 */
	private String contactMobile;
	/**
	 * 联系人邮箱
	 */
	private String contactEmail;
	
	/**
	 * 经营类目编码
	 */
	private String mccCode;
	
	/**
	 * 企业特殊资质图片路径
	 */
	private String qualificationPath;
	
	/**
	 * 营业执照号码
	 */
	private String businessLicense;
	
	/**
	 * 营业执照图片路径
	 */
	private String businessPhotoPath;
	
	/**
	 * 营业执照授权函图片路径
	 */
	private String businessAuthPhotoPath;
	
	/**
	 * 营业期限是否长期有效
	 */
	private boolean longTerm;
	
	/**
	 * 营业执照有效期
	 */
	private String businessTerm;
	
	/**
	 * 店铺内景图片路径
	 */
	private String shopInteriorPath;
	
	/**
	 * 店铺门头照图片路径
	 */
	private String doorPhotoPath;

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getQualificationPath() {
		return qualificationPath;
	}

	public void setQualificationPath(String qualificationPath) {
		this.qualificationPath = qualificationPath;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getBusinessPhotoPath() {
		return businessPhotoPath;
	}

	public void setBusinessPhotoPath(String businessPhotoPath) {
		this.businessPhotoPath = businessPhotoPath;
	}

	public String getBusinessAuthPhotoPath() {
		return businessAuthPhotoPath;
	}

	public void setBusinessAuthPhotoPath(String businessAuthPhotoPath) {
		this.businessAuthPhotoPath = businessAuthPhotoPath;
	}

	public boolean isLongTerm() {
		return longTerm;
	}

	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}

	public String getBusinessTerm() {
		return businessTerm;
	}

	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}

	public String getShopInteriorPath() {
		return shopInteriorPath;
	}

	public void setShopInteriorPath(String shopInteriorPath) {
		this.shopInteriorPath = shopInteriorPath;
	}

	public String getDoorPhotoPath() {
		return doorPhotoPath;
	}

	public void setDoorPhotoPath(String doorPhotoPath) {
		this.doorPhotoPath = doorPhotoPath;
	}
	
	
	
}
