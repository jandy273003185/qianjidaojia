package com.qifenqian.bms.merchant.merchantReported.bean;

import java.io.Serializable;

public class KFTMccBean implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3178079735443125493L;

	//行业名称
	private String levelOne;
	
	private String levelTwo;
	
	private String levelThree;
	
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
	
	
	

}
