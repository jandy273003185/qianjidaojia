package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class SumpayMcc implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3776212407330781059L;
	//mcc码
	private String mcc;
	//阿里类目code
	private String AlipayMchntType;
	//一级
	private String levelOne;
	//二级
	private String levelTwo;
	//三级
	private String levelThree;
	//微信类目Code
	private String wechatMchntType;
	//微信结算费率
	private String rate;
	//微信结算周期
	private String cycleDays;
	//支付宝分类
	private String type;
	//支付宝品类ID
	private String mchntType;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMchntType() {
		return mchntType;
	}
	public void setMchntType(String mchntType) {
		this.mchntType = mchntType;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getAlipayMchntType() {
		return AlipayMchntType;
	}
	public void setAlipayMchntType(String alipayMchntType) {
		AlipayMchntType = alipayMchntType;
	}
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
	public String getWechatMchntType() {
		return wechatMchntType;
	}
	public void setWechatMchntType(String wechatMchntType) {
		this.wechatMchntType = wechatMchntType;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCycleDays() {
		return cycleDays;
	}
	public void setCycleDays(String cycleDays) {
		this.cycleDays = cycleDays;
	}
	
	

}
