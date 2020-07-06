package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代付收益查询结果辅助类
 * @author hongjiagui
 *
 */
/**
 * @author dengtongbai
 *
 */
public class ProfitBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1478526394501089835L;
	
	
	
	/**收入总数**/
	private BigDecimal incomeAmt;
	
	/**支出总数**/
	private BigDecimal expenseAmt;
	
	/**利润**/
	private BigDecimal profitAmt;

	public BigDecimal getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public BigDecimal getExpenseAmt() {
		return expenseAmt;
	}

	public void setExpenseAmt(BigDecimal expenseAmt) {
		this.expenseAmt = expenseAmt;
	}

	public BigDecimal getProfitAmt() {
		return this.incomeAmt.subtract(expenseAmt);
	}

	public void setProfitAmt(BigDecimal profitAmt) {
		this.profitAmt = this.incomeAmt.subtract(expenseAmt);
	}
	
	

	

}
