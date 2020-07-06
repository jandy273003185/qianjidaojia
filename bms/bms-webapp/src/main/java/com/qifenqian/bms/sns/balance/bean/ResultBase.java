package com.qifenqian.bms.sns.balance.bean;



public class ResultBase{

	/** 写入时间 */
	private String instDatetime;
	
	/** 对账结果：SELF_DOUBT/BAL_DOUBT/BAL_ERROR */
	private String balResult;

	/** 对账处理时间 */
	private String balDatetime;
	
	private String workBeginDate;
	
	private String workEndDate;

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getBalResult() {
		return balResult;
	}

	public void setBalResult(String balResult) {
		this.balResult = balResult;
	}

	public String getBalDatetime() {
		return balDatetime;
	}

	public void setBalDatetime(String balDatetime) {
		this.balDatetime = balDatetime;
	}

	public String getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(String workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

	
}
