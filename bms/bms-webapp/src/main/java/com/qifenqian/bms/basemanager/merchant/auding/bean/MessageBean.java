package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

public class MessageBean implements Serializable{

	private static final long serialVersionUID = 5292553633972230058L;

	private String url_short;
	
	private String url_long;
	
	private String type;

	public String getUrl_short() {
		return url_short;
	}

	public void setUrl_short(String url_short) {
		this.url_short = url_short;
	}

	public String getUrl_long() {
		return url_long;
	}

	public void setUrl_long(String url_long) {
		this.url_long = url_long;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
