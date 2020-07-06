package com.qifenqian.bms.basemanager.certify.bean;

import java.io.Serializable;

public class Certify implements Serializable{
	
	private static final long serialVersionUID = -682833961334225889L;
	
	/**********************************************************/
	
	/** 证件类型代码  */ 			private String certifyType;
	/** 证件类型  */				private String certifyName;
	
	/***********************************************************/
	
	public String getCertifyType() {
		return certifyType;
	}
	public void setCertifyType(String certifyType) {
		this.certifyType = certifyType;
	}
	public String getCertifyName() {
		return certifyName;
	}
	public void setCertifyName(String certifyName) {
		this.certifyName = certifyName;
	}
	
	
	
	
	
}
