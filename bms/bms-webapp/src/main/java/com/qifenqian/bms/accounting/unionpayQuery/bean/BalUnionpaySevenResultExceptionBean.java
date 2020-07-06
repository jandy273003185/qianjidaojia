package com.qifenqian.bms.accounting.unionpayQuery.bean;

public class BalUnionpaySevenResultExceptionBean extends BalUnionpaySevenResultException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4557623034137875050L;

	private String beginWorkDate;
	
	private String endWorkDate;

	public String getBeginWorkDate() {
		return beginWorkDate;
	}

	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	
	
}
