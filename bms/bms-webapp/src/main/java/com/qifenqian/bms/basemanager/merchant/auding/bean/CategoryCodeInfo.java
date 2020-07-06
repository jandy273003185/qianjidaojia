package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

/**
 * 行业类目信息表
 * @author Roy.Li
 *
 */
public class CategoryCodeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2988296718139562925L;

	/**
	 * 行业类目编号
	 */
	private String categoryId;
	
	/**
	 * 行业类别编号100 实体，200 虚拟；300 其他
	 */
	private String categoryTypeId;
	
	/**
	 * 行业类别
	 */
	private String categoryType;
	
	/**
	 * 行业名称
	 */
	private String categoryName;
	
	

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(String categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
}
