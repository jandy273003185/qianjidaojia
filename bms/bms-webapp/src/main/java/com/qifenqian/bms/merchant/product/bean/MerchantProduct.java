package com.qifenqian.bms.merchant.product.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户产品管理bean
 * 
 * @project qifenqian-bms
 * @fileName MerchantProduct.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
public class MerchantProduct implements Serializable {

	private static final long serialVersionUID = 1428363965023568317L;

	/** 商户名称 */
	private String merchantName;
	
	/** 产品名称 */
	private String productName;
	
	/** 商户编号 */
	private String merchantCode;
	
	/** 产品ID */
	private String productId;
	
	/** 产品代码 */
	private String productCode;
			
	/** 产品汇率 */
	private BigDecimal productRate;
	
	/** 充值汇率 */
	private BigDecimal rechargeRate;
	
	/** 产品开通状态 */
	private String productStatus;
	
	/** 渠道 */
	private String aisle;	
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date modifyTime;
	
	/** 设备编号 */
	private String machineId;
	
	
	/** 产品汇率 */
	private String productRateStr;
	
	/** 充值汇率 */
	private String rechargeRateStr;
	/**id*/
	private String id;
	/**
	 * 审核人Id
	 */
	private String auditId;
	/**
	 * 审核人真实姓名
	 */
	private String realName;
	
	

	

	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public BigDecimal getRechargeRate() {
		return rechargeRate;
	}

	public void setRechargeRate(BigDecimal rechargeRate) {
		this.rechargeRate = rechargeRate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductRateStr() {
		return productRateStr;
	}

	public void setProductRateStr(String productRateStr) {
		this.productRateStr = productRateStr;
	}

	public String getRechargeRateStr() {
		return rechargeRateStr;
	}

	public void setRechargeRateStr(String rechargeRateStr) {
		this.rechargeRateStr = rechargeRateStr;
	}
	
}
