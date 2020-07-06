package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class PicPath implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375686111920209381L;

	
	private String openPath; //开户许可证
	
	private String identityImagePath0;//身份证正面
	
	private String identityImagePath1;//身份证反面
	
	private String licensePath;// 营业执照
	
	private String bankCardPath;//银行卡
	
	private String doorPath; //门头照
	
	private String shopInteriorPath;// 店内照

	public String getOpenPath() {
		return openPath;
	}

	public void setOpenPath(String openPath) {
		this.openPath = openPath;
	}

	public String getIdentityImagePath0() {
		return identityImagePath0;
	}

	public void setIdentityImagePath0(String identityImagePath0) {
		this.identityImagePath0 = identityImagePath0;
	}

	public String getIdentityImagePath1() {
		return identityImagePath1;
	}

	public void setIdentityImagePath1(String identityImagePath1) {
		this.identityImagePath1 = identityImagePath1;
	}

	public String getLicensePath() {
		return licensePath;
	}

	public void setLicensePath(String licensePath) {
		this.licensePath = licensePath;
	}

	public String getBankCardPath() {
		return bankCardPath;
	}

	public void setBankCardPath(String bankCardPath) {
		this.bankCardPath = bankCardPath;
	}

	public String getDoorPath() {
		return doorPath;
	}

	public void setDoorPath(String doorPath) {
		this.doorPath = doorPath;
	}

	public String getShopInteriorPath() {
		return shopInteriorPath;
	}

	public void setShopInteriorPath(String shopInteriorPath) {
		this.shopInteriorPath = shopInteriorPath;
	}
	
	
	
}
