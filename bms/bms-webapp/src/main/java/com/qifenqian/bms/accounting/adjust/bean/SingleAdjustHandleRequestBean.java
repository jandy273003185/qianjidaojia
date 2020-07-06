package com.qifenqian.bms.accounting.adjust.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 单边调账请求信息bean
 * 
 * @project sevenpay-bms-web
 * @fileName SingleAdjustHandleRequestBean.java
 * @author kunwang.li
 * @date 2015年9月16日
 * @memo
 */
public class SingleAdjustHandleRequestBean implements Serializable {

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
	List<AccountingSingleAdjustDetail> entryList = null;
	
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

	public List<AccountingSingleAdjustDetail> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<AccountingSingleAdjustDetail> entryList) {
		this.entryList = entryList;
	}

	public boolean isResubmit() {
		return resubmit;
	}

	public void setResubmit(boolean resubmit) {
		this.resubmit = resubmit;
	}

}
