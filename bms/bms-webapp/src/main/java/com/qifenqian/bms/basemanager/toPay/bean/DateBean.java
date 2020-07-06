package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;

/**
 * 查询代付收益查询日期辅助类
 * @author hongjiagui
 *
 */
public class DateBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6897017432126228376L;

	/**查询手续费利润起始日期**/
	private String rechargeStart; 
	
	/**查询手续费利润截止日期**/
	private String rechargeEnd;
	
	/**查询代付收益起始日期**/
	private String poundageStart;
	
	/**查询代付收益截止日期**/
	private String poundageEnd;

	public String getRechargeStart() {
		return rechargeStart;
	}

	public void setRechargeStart(String rechargeStart) {
		this.rechargeStart = rechargeStart;
	}

	public String getRechargeEnd() {
		return rechargeEnd;
	}

	public void setRechargeEnd(String rechargeEnd) {
		this.rechargeEnd = rechargeEnd;
	}

	public String getPoundageStart() {
		return poundageStart;
	}

	public void setPoundageStart(String poundageStart) {
		this.poundageStart = poundageStart;
	}

	public String getPoundageEnd() {
		return poundageEnd;
	}

	public void setPoundageEnd(String poundageEnd) {
		this.poundageEnd = poundageEnd;
	}
	
	
}
