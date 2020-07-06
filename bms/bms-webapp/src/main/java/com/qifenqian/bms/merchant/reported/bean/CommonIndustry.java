package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class CommonIndustry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7664412815893923310L;
	/**
	 * 一级行业类目
	 */
	private String levelOne;
	/**
	 * 二级行业类目
	 */
	private String levelTwo;
	/**
	 * 三级行业类目
	 */
	private String levelThree;
	/**
	 * 经营类目编码
	 */
	private String levelCode;
	/**
	 * 需要的特殊资质证书
	 */
	private String specialCertificate;
	public String getLevelOne() {
		return levelOne;
	}
	public void setLevelOne(String levelOne) {
		this.levelOne = levelOne;
	}
	public String getLevelTwo() {
		return levelTwo;
	}
	public void setLevelTwo(String levelTwo) {
		this.levelTwo = levelTwo;
	}
	public String getLevelThree() {
		return levelThree;
	}
	public void setLevelThree(String levelThree) {
		this.levelThree = levelThree;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getSpecialCertificate() {
		return specialCertificate;
	}
	public void setSpecialCertificate(String specialCertificate) {
		this.specialCertificate = specialCertificate;
	}
	
	

}
