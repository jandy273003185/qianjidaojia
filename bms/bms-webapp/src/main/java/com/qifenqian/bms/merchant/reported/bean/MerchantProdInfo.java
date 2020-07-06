package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class MerchantProdInfo implements Serializable{

	private static final long serialVersionUID = 463843954437206617L;

	private String custId;
	
	private String mobile;
	
	private String h5;
	
	private String pc;
	
	private String h5t;
	
	private String onecode;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getH5() {
		return h5;
	}

	public void setH5(String h5) {
		this.h5 = h5;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getH5t() {
		return h5t;
	}

	public void setH5t(String h5t) {
		this.h5t = h5t;
	}

	public String getOnecode() {
		return onecode;
	}

	public void setOnecode(String onecode) {
		this.onecode = onecode;
	}
	
}
