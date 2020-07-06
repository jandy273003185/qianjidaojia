package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;

/**
 * 行业类目信息
 * @author wulingtong
 *
 */
public class CategoryCodeInfo implements Serializable{

	private static final long serialVersionUID = -2619329176298601860L;
	
	private String categoryId;
	
	private String categoryTypeId;
	
	private String categoryType;
	
	private String categoryName;
	
	/**
	 * 商户类型
	 */
	private String merchantType;
	
	/**
	 * 经营类别
	 */
	private String merchantCategory;
	
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(String merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(String categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
