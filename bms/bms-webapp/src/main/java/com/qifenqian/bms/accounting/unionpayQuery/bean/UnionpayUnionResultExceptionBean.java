package com.qifenqian.bms.accounting.unionpayQuery.bean;


public class UnionpayUnionResultExceptionBean extends BalUnionpayUnionResultException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5785830023550095381L;
	
	
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
