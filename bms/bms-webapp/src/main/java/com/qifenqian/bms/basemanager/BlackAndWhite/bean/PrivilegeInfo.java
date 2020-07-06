package com.qifenqian.bms.basemanager.BlackAndWhite.bean;

import java.io.Serializable;

public class PrivilegeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8902476636867238327L;
	
	private String privilegeCode;
	private String privilegeName;
	public String getPrivilegeCode() {
		return privilegeCode;
	}
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

}
