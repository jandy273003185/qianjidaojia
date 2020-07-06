package com.qifenqian.bms.accounting.adjust.bean;

import java.io.Serializable;
import java.util.List;

public class AdjustHandleRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主体信息
	 */
	AccountingAdjustMain mainInfo = null;

	/**
	 * 分录列表
	 */
	List<AccountingAdjustDetail> entryList = null;
	
	/**
	 * 是否再次提交
	 */
	private boolean resubmit;

	public AccountingAdjustMain getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(AccountingAdjustMain mainInfo) {
		this.mainInfo = mainInfo;
	}

	public List<AccountingAdjustDetail> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<AccountingAdjustDetail> entryList) {
		this.entryList = entryList;
	}

	public boolean isResubmit() {
		return resubmit;
	}

	public void setResubmit(boolean resubmit) {
		this.resubmit = resubmit;
	}

}
