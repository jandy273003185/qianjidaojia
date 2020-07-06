package com.qifenqian.bms.accounting.withdraw.bean;

/**
 * 提现请求bean
 * 
 * @author pc
 *
 */
public class WithdrawRequestBean extends Withdraw {

	private static final long serialVersionUID = 5744091944097488981L;

	private String beginTime;

	private String endTime;

	private String auditBeginTime;

	private String auditEndTime;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAuditBeginTime() {
		return auditBeginTime;
	}

	public void setAuditBeginTime(String auditBeginTime) {
		this.auditBeginTime = auditBeginTime;
	}

	public String getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(String auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

}
