package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class SuiXingBean implements Serializable{

	/**
	 * 随行付Bean
	 */
	private static final long serialVersionUID = -1347626948280820225L;

	/***渠道***/
	private String channelNo;
	/***商户号***/
	private String merchantCode;
	/***商户简称***/
	private String custName;
	/***手机号***/
	private String mobileNo;
	/***资质类型***/
	private String suiXingMerchantType;
	/***商户类型***/
	private String mecTypeFlag;
	/***注册名***/
	private String cprRegNmCn;
	/***注册号***/
	private String registCode;
	/***注册省***/
	private String merchantProvince;
	/***注册市***/
	private String merchantCity;
	/***注册地区***/
	private String merchantArea;
	/***注册详细地址***/
	private String cprRegAddr;
	/***行业信息***/
	private String industryCode;
	/***法人姓名***/
	private String representativeName;
	/***法人证件类型***/
	private String representativeCertType;
	/***法人证件号***/
	private String representativeCertNo;
	/***结算名***/
	private String actNm;
	/***结算类型***/
	private String actType;
	/***结算身份证号***/
	private String certifyNo;
	/***结算银行卡号***/
	private String bankCardNo;
	/***开户行***/
	private String suiXinBank;
	/***开户行所在省***/
	private String bankProvince;
	/***开户行所在市***/
	private String bankCity;
	/***开户支行名称***/
	private String interBankName;
	/***费率***/
	private String rate;
	/***照片压缩返回码***/
	private String taskCode;
	/***联行号***/
	private String lbnkNo;
	/***所属总店商户编号***/
	private String parentMno;
	/***分店是否独立结算***/
	private String independentModel;
	
	
	public String getParentMno() {
		return parentMno;
	}
	public void setParentMno(String parentMno) {
		this.parentMno = parentMno;
	}
	public String getIndependentModel() {
		return independentModel;
	}
	public void setIndependentModel(String independentModel) {
		this.independentModel = independentModel;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getSuiXingMerchantType() {
		return suiXingMerchantType;
	}
	public void setSuiXingMerchantType(String suiXingMerchantType) {
		this.suiXingMerchantType = suiXingMerchantType;
	}
	public String getMecTypeFlag() {
		return mecTypeFlag;
	}
	public void setMecTypeFlag(String mecTypeFlag) {
		this.mecTypeFlag = mecTypeFlag;
	}
	public String getCprRegNmCn() {
		return cprRegNmCn;
	}
	public void setCprRegNmCn(String cprRegNmCn) {
		this.cprRegNmCn = cprRegNmCn;
	}
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
	public String getMerchantProvince() {
		return merchantProvince;
	}
	public void setMerchantProvince(String merchantProvince) {
		this.merchantProvince = merchantProvince;
	}
	public String getMerchantCity() {
		return merchantCity;
	}
	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}
	public String getMerchantArea() {
		return merchantArea;
	}
	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getRepresentativeCertType() {
		return representativeCertType;
	}
	public void setRepresentativeCertType(String representativeCertType) {
		this.representativeCertType = representativeCertType;
	}
	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}
	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
	}
	public String getActNm() {
		return actNm;
	}
	public void setActNm(String actNm) {
		this.actNm = actNm;
	}
	public String getActType() {
		return actType;
	}
	public void setActType(String actType) {
		this.actType = actType;
	}
	public String getCertifyNo() {
		return certifyNo;
	}
	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getSuiXinBank() {
		return suiXinBank;
	}
	public void setSuiXinBank(String suiXinBank) {
		this.suiXinBank = suiXinBank;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getLbnkNo() {
		return lbnkNo;
	}
	public void setLbnkNo(String lbnkNo) {
		this.lbnkNo = lbnkNo;
	}
	
	
	
}
