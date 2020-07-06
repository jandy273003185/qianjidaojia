package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class ChannlInfo implements Serializable{

	/**
	 * 渠道表
	 */
	private static final long serialVersionUID = 1L;

	private String  channlId; //'报备渠道ID',
	private String  channlCode;  //'报备渠道代码',
	private String  channlName; //'报备渠道名称',
	private String  channlMemo; //'报备渠道说明',
	
	
	
	
	public String getChannlId() {
		return channlId;
	}
	public void setChannlId(String channlId) {
		this.channlId = channlId;
	}
	public String getChannlCode() {
		return channlCode;
	}
	public void setChannlCode(String channlCode) {
		this.channlCode = channlCode;
	}
	public String getChannlName() {
		return channlName;
	}
	public void setChannlName(String channlName) {
		this.channlName = channlName;
	}
	public String getChannlMemo() {
		return channlMemo;
	}
	public void setChannlMemo(String channlMemo) {
		this.channlMemo = channlMemo;
	}
}
