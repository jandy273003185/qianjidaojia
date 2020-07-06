package com.qifenqian.bms.meeting.helper.pushrelation.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @project sevenpay-bms-web
 * @fileName PushRelation.java
 * @author huiquan.ma
 * @date 2015年12月17日
 * @memo 
 */
public class PushRelation implements Serializable {

	private static final long serialVersionUID = 6712766618047429075L;

    /** 客户编号*/
	private String custId;
    /** 登录手机号*/
	private String mobile;
    /** 推送平台注册号*/
	private String registerId;
    /** 注册时间*/
	private Date registerTime;
    /** 终端类型 ANDROID IOS*/
	private String terminalType;
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
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	
	
}


