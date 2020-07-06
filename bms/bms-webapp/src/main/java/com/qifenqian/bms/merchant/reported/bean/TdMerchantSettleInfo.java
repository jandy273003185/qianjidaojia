package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantSettleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1132663875769864883L;
	/**
	 * 自增长ID
	 */
	private String id;
	/**
	 * 客户号
	 */
	private String custId;
	/**
	 * 支付宝分账方账号
	 */
	private String alipayTransOutAccount;
	/**
	 * 支付宝分账方全称
	 */
	private String alipayTransOutName;
	/**
	 * 支付宝分账接收方账号
	 */
	private String alipayTransInAccount;
	/**
	 * 微信分账接收方账号
	 */
	private String wxTransInAccount;
	/**
	 * 微信分账接收方类型 MERCHANT_ID：商户ID ，PERSONAL_WECHATID：个人微信号
	 */
	private String wxTransInType;
	/**
	 * 微信分账接收方全称
	 */
	private String wxTransInName;
	/**
	 * 与分账方关系类型  SERVICE_PROVIDER：服务商 STORE：门店  STAFF：员工 STORE_OWNER：店主
	 */
	private String wxRelationType;
	/**
	 * 分账描述
	 */
	private String settleDesc;
	/**
	 * 分账百分比费率
	 */
	private String settleRate;
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 分账接收方商户号
	 */
	private String transInMchId;

	public String getSettleDesc() {
		return settleDesc;
	}

	public void setSettleDesc(String settleDesc) {
		this.settleDesc = settleDesc;
	}

	public String getAlipayTransOutName() {
		return alipayTransOutName;
	}

	public void setAlipayTransOutName(String alipayTransOutName) {
		this.alipayTransOutName = alipayTransOutName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAlipayTransOutAccount() {
		return alipayTransOutAccount;
	}

	public void setAlipayTransOutAccount(String alipayTransOutAccount) {
		this.alipayTransOutAccount = alipayTransOutAccount;
	}

	public String getAlipayTransInAccount() {
		return alipayTransInAccount;
	}

	public void setAlipayTransInAccount(String alipayTransInAccount) {
		this.alipayTransInAccount = alipayTransInAccount;
	}

	public String getWxTransInAccount() {
		return wxTransInAccount;
	}

	public void setWxTransInAccount(String wxTransInAccount) {
		this.wxTransInAccount = wxTransInAccount;
	}

	public String getWxTransInType() {
		return wxTransInType;
	}

	public void setWxTransInType(String wxTransInType) {
		this.wxTransInType = wxTransInType;
	}

	public String getWxTransInName() {
		return wxTransInName;
	}

	public void setWxTransInName(String wxTransInName) {
		this.wxTransInName = wxTransInName;
	}

	public String getWxRelationType() {
		return wxRelationType;
	}

	public void setWxRelationType(String wxRelationType) {
		this.wxRelationType = wxRelationType;
	}

	public String getSettleRate() {
		return settleRate;
	}

	public void setSettleRate(String settleRate) {
		this.settleRate = settleRate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTransInMchId() {
		return transInMchId;
	}

	public void setTransInMchId(String transInMchId) {
		this.transInMchId = transInMchId;
	}
	
	
}
