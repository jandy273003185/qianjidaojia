package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class YQBIndustry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204279809909580035L;
	
	//行业名称
	private String levelOne;
	
	private String levelTwo;
	
	private String levelFour;
	//行业编码
	private String levelCode;
	
	
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
	public String getLevelFour() {
		return levelFour;
	}
	public void setLevelFour(String levelFour) {
		this.levelFour = levelFour;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	
	
	
}
