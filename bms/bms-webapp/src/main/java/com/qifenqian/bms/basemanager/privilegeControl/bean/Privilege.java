package com.qifenqian.bms.basemanager.privilegeControl.bean;

import java.io.Serializable;

public class Privilege implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7949441911752914498L;
	
	private String custId;//  客户编号
	private String privilegeCode;//    权限代码
	private String createOper ;//  创建人
	private String createTime ;//  创建时间
	private String modifyOper ;//  修改人
	private String modifyTime;//   修改时间
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPrivilegeCode() {
		return privilegeCode;
	}
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	public String getCreateOper() {
		return createOper;
	}
	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyOper() {
		return modifyOper;
	}
	public void setModifyOper(String modifyOper) {
		this.modifyOper = modifyOper;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
