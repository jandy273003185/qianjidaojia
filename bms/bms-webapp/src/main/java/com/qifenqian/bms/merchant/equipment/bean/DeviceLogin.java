package com.qifenqian.bms.merchant.equipment.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户设备详情表
 */
public class DeviceLogin implements Serializable {	

	private static final long serialVersionUID = 5703178835215104781L;

	/** id */
	private String loginId;
	
	/** 客户号 */
	private String custId;
	
	/** 手机MAC */
	private String mac;

	/** 手机MAC */
	private String mobile;
			
	/**客户端类型 ANDROID/IOS */
	private String clientType;
	
	/**客户端信息(手机型号，系统版本)*/
	private String clientInfo;
	
	/**登录手机时间 */
	private String loginTimeM;
	
	/** 首次登录服务器时间 */
	private Date loginTimeS;
	
	/** 最近一次登录服务器时间 */
	private Date loginTimeL;
	
	/** 最近一次登录服务器时间 */
	private Date logoutTime;
	
	/**盐值 */
	private String salt;
	
	/**客户端类型 ANDROID/IOS */
	private String status;
	
	/**商户公钥 */
	private String merchantPublickey;
	
	/**商户私钥*/
	private String merchantPrivatekey;
	
	/**  七分钱公钥 */
	private String qifenqianPublickey;
	
	/** 七分钱私钥 */
	private String qifenqianPrivatekey;
	
	/**商户名字 */
	private String merchantName;
	
	/**商户编号*/
	private String merchantCode;
	
	
	
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getMerchantPublickey() {
		return merchantPublickey;
	}
	public void setMerchantPublickey(String merchantPublickey) {
		this.merchantPublickey = merchantPublickey;
	}
	public String getMerchantPrivatekey() {
		return merchantPrivatekey;
	}
	public void setMerchantPrivatekey(String merchantPrivatekey) {
		this.merchantPrivatekey = merchantPrivatekey;
	}
	public String getQifenqianPublickey() {
		return qifenqianPublickey;
	}
	public void setQifenqianPublickey(String qifenqianPublickey) {
		this.qifenqianPublickey = qifenqianPublickey;
	}
	public String getQifenqianPrivatekey() {
		return qifenqianPrivatekey;
	}
	public void setQifenqianPrivatekey(String qifenqianPrivatekey) {
		this.qifenqianPrivatekey = qifenqianPrivatekey;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginTimeM() {
		return loginTimeM;
	}
	public void setLoginTimeM(String loginTimeM) {
		this.loginTimeM = loginTimeM;
	}
	public Date getLoginTimeS() {
		return loginTimeS;
	}
	public void setLoginTimeS(Date loginTimeS) {
		this.loginTimeS = loginTimeS;
	}
	public Date getLoginTimeL() {
		return loginTimeL;
	}
	public void setLoginTimeL(Date loginTimeL) {
		this.loginTimeL = loginTimeL;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
