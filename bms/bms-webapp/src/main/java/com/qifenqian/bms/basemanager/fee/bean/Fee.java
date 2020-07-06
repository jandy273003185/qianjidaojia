package com.qifenqian.bms.basemanager.fee.bean;

import java.io.Serializable;

public class Fee implements Serializable {
	
	private static final long serialVersionUID = -682833961334225889L;
	
	/**********************************************************/
	
	/** 费用代码 */	 	private String feeCode;
	/** 费用名称  */		private String feeName;
	/** 描述 */		private String feeCodeDesc;
	
	/***********************************************************/
	
	
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getFeeCodeDesc() {
		return feeCodeDesc;
	}
	public void setFeeCodeDesc(String feeCodeDesc) {
		this.feeCodeDesc = feeCodeDesc;
	}
	
}
