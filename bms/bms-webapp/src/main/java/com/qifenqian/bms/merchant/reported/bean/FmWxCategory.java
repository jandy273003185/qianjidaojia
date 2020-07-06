package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class FmWxCategory implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3640254864851206149L;

	private String id;//品类编号
	
	private String firstType;//一级类目
	
	private String secondType;//二级类目
	
	private String thirdType;//三级类目
	
	private String categoryName;//类目名称
	
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstType() {
		return firstType;
	}

	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}

	public String getThirdType() {
		return thirdType;
	}

	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}

	
	
}
