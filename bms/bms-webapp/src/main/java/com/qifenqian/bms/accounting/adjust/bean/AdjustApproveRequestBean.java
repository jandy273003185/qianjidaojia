package com.qifenqian.bms.accounting.adjust.bean;

import java.io.Serializable;

/**
 * 审批请求信息bean
 * 
 * @project sevenpay-bms-web
 * @fileName AdjustApproveRequestBean.java
 * @author kunwang.li
 * @date 2015年8月5日
 * @memo
 */
public class AdjustApproveRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String opId = null;

	private String memo = null;

	private boolean checkPass;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isCheckPass() {
		return checkPass;
	}

	public void setCheckPass(boolean checkPass) {
		this.checkPass = checkPass;
	}

}
