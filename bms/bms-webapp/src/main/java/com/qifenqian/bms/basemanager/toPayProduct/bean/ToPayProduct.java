package com.qifenqian.bms.basemanager.toPayProduct.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ToPayProduct implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7299481457903767178L;

	
	/**
	 * 商户号
	 */
	private String merchantCode;
	
	/**
	 * 产品编号
	 */
 	private String productId;
 	/**
 	 * 产品名称
 	 */
 	private String productName;
 	
 	/**
 	 * 产品费率
 	 */
	private BigDecimal productRate;
	
	/**
	 * 充值费率
	 */
	private BigDecimal rechargeRate;
	
	/**
	 * 产品开通状态
	 */
	private String productStatus;

	/**
	 * 渠道编号
	 */
	private String aisle;
	
	/**
	 * 渠道名称
	 */
	private String description;
	
	/**
	 * 商户名称
	 * @return
	 */
	private String custName;
	
	/**
	 * 唯一id
	 * @return
	 */
	private String id;
	
	private Date modifyTime;
	
	private Date createTime;
	
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道代付手续费
	 * @return
	 */
	private BigDecimal channelTopayRate;
	/**
	 * 渠道充值手续费
	 * @return
	 */
	private BigDecimal channelRechargeRate;
	/**
	 * 渠道收费方式
	 * fixed固定,rate费率
	 * @return
	 */
	private String channelRecharType;
	
	/**
	 * 七分钱默认充值费率
	 * @return
	 */
	private BigDecimal sevenpayRechargeRate;
	/**
	 * 七分钱默认充值费率收费方式
	 * fixed固定 ,rate费率
	 * @return
	 */
	private String sevenpayRechargeType;
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public BigDecimal getChannelTopayRate() {
		return channelTopayRate;
	}

	public void setChannelTopayRate(BigDecimal channelTopayRate) {
		this.channelTopayRate = channelTopayRate;
	}

	public BigDecimal getChannelRechargeRate() {
		return channelRechargeRate;
	}

	public void setChannelRechargeRate(BigDecimal channelRechargeRate) {
		this.channelRechargeRate = channelRechargeRate;
	}

	public String getChannelRecharType() {
		return channelRecharType;
	}

	public void setChannelRecharType(String channelRecharType) {
		this.channelRecharType = channelRecharType;
	}

	public BigDecimal getSevenpayRechargeRate() {
		return sevenpayRechargeRate;
	}

	public void setSevenpayRechargeRate(BigDecimal sevenpayRechargeRate) {
		this.sevenpayRechargeRate = sevenpayRechargeRate;
	}

	public String getSevenpayRechargeType() {
		return sevenpayRechargeType;
	}

	public void setSevenpayRechargeType(String sevenpayRechargeType) {
		this.sevenpayRechargeType = sevenpayRechargeType;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
