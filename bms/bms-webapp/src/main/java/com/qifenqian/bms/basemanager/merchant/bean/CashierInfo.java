package com.qifenqian.bms.basemanager.merchant.bean;



import java.io.Serializable;

public class CashierInfo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2131395679153030392L;

	private String onlyId;
	
	private String merchantCustId;
	
	private String cashierCustId;
	
	private String cashierMobile;

	private String cashierName;
	
	private String refundAuth;//是否有退款权限 0 否 ，1 是

	private String merchantName;
	
	private String userId;
	
	private String userName;
	
	private String createId;
	private String createName;
	private String createTime;
	
	private String modifyId;
	private String modifyName;
	private String modifyTime;
	/** 门店名称*/
	private String shopName;
	/** 是否有全门店权限*/
	private String queryAuth;
	
	/** 门店ID*/
	private String shopId;

	private String status;

	/**
	 * 登录密码
	 */
	private String loginPw;

	/**
	 * 登录密码盐值
	 */
	private String loginSalt;

	/**
	 * 退款密码
	 */
	private String refundPw;

	/**
	 * 退款密码盐值
	 */
	private String refundSalt;

	/**
	 * 网站退款权限 0:无 1:有
	 */
	private String webRefundAuth;

	/**
	 * 机器退款权限 0:无 1:有
	 */
	private String machineRefundAuth;

	/**
	 * APP退款权限 0:无 1:有
	 */
	private String appRefundAuth;

	/**
	 * 公众号退款权限 0:无 1:有
	 */
	private String officialRefundAuth;


	/**
	 * 小程序退款权限 0:无 1:有
	 */
	private String miniRefundAuth;


	/**
	 * 外部关联编号（可以用此编号登录）
	 */
	private String externalId;

	public String getWebRefundAuth() {
		return webRefundAuth;
	}

	public void setWebRefundAuth(String webRefundAuth) {
		this.webRefundAuth = webRefundAuth;
	}

	public String getMachineRefundAuth() {
		return machineRefundAuth;
	}

	public void setMachineRefundAuth(String machineRefundAuth) {
		this.machineRefundAuth = machineRefundAuth;
	}

	public String getAppRefundAuth() {
		return appRefundAuth;
	}

	public void setAppRefundAuth(String appRefundAuth) {
		this.appRefundAuth = appRefundAuth;
	}

	public String getOfficialRefundAuth() {
		return officialRefundAuth;
	}

	public void setOfficialRefundAuth(String officialRefundAuth) {
		this.officialRefundAuth = officialRefundAuth;
	}

	public String getMiniRefundAuth() {
		return miniRefundAuth;
	}

	public void setMiniRefundAuth(String miniRefundAuth) {
		this.miniRefundAuth = miniRefundAuth;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getQueryAuth() {
		return queryAuth;
	}

	public void setQueryAuth(String queryAuth) {
		this.queryAuth = queryAuth;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getRefundAuth() {
		return refundAuth;
	}

	public void setRefundAuth(String refundAuth) {
		this.refundAuth = refundAuth;
	}

	public String getOnlyId() {
		return onlyId;
	}

	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}

	public String getMerchantCustId() {
		return merchantCustId;
	}

	public void setMerchantCustId(String merchantCustId) {
		this.merchantCustId = merchantCustId;
	}

	public String getCashierCustId() {
		return cashierCustId;
	}

	public void setCashierCustId(String cashierCustId) {
		this.cashierCustId = cashierCustId;
	}

	public String getCashierMobile() {
		return cashierMobile;
	}

	public void setCashierMobile(String cashierMobile) {
		this.cashierMobile = cashierMobile;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getLoginSalt() {
		return loginSalt;
	}

	public void setLoginSalt(String loginSalt) {
		this.loginSalt = loginSalt;
	}

	public String getRefundPw() {
		return refundPw;
	}

	public void setRefundPw(String refundPw) {
		this.refundPw = refundPw;
	}

	public String getRefundSalt() {
		return refundSalt;
	}

	public void setRefundSalt(String refundSalt) {
		this.refundSalt = refundSalt;
	}
}
