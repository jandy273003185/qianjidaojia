/**
 * 
 */
package com.qifenqian.bms.accounting.adjust.bean;

import java.math.BigDecimal;

import com.sevenpay.invoke.common.type.RequestColumnValues.CurrCode;

/**
 * 调账历史查询信息bean
 * 
 * @project sevenpay-bms-web
 * @fileName AccountingAdjustHistoryListBean.java
 * @author kunwang.li
 * @date 2015年9月30日
 * @memo
 */
public class AccountingAdjustHistoryListBean extends AccountingAdjustMain {

	/**
	 * 业务编号
	 */
	private String opId;

	/**
	 * 借方账户类型
	 */
	private String debitAcctType;

	/**
	 * 借方账号
	 */
	private String debitAcctNo;

	/**
	 * 借方账户名称
	 */
	private String debitAcctName;

	/**
	 * 贷方账户类型
	 */
	private String creditAcctType;

	/**
	 * 贷方账号
	 */
	private String creditAcctNo;

	/**
	 * 贷方账户名称
	 */
	private String creditAcctName;

	/**
	 * 币别
	 */
	private CurrCode curcde;

	/**
	 * 金额
	 */
	private BigDecimal amt;

	private String handleDatetime;

	private String handlerUid;

	private String relationOpId;

	private String memo;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getDebitAcctType() {
		return debitAcctType;
	}

	public void setDebitAcctType(String debitAcctType) {
		this.debitAcctType = debitAcctType;
	}

	public String getDebitAcctNo() {
		return debitAcctNo;
	}

	public void setDebitAcctNo(String debitAcctNo) {
		this.debitAcctNo = debitAcctNo;
	}

	public String getDebitAcctName() {
		return debitAcctName;
	}

	public void setDebitAcctName(String debitAcctName) {
		this.debitAcctName = debitAcctName;
	}

	public String getCreditAcctType() {
		return creditAcctType;
	}

	public void setCreditAcctType(String creditAcctType) {
		this.creditAcctType = creditAcctType;
	}

	public String getCreditAcctNo() {
		return creditAcctNo;
	}

	public void setCreditAcctNo(String creditAcctNo) {
		this.creditAcctNo = creditAcctNo;
	}

	public String getCreditAcctName() {
		return creditAcctName;
	}

	public void setCreditAcctName(String creditAcctName) {
		this.creditAcctName = creditAcctName;
	}

	public CurrCode getCurcde() {
		return curcde;
	}

	public void setCurcde(CurrCode curcde) {
		this.curcde = curcde;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getHandleDatetime() {
		return handleDatetime;
	}

	public void setHandleDatetime(String handleDatetime) {
		this.handleDatetime = handleDatetime;
	}

	public String getHandlerUid() {
		return handlerUid;
	}

	public void setHandlerUid(String handlerUid) {
		this.handlerUid = handlerUid;
	}

	public String getRelationOpId() {
		return relationOpId;
	}

	public void setRelationOpId(String relationOpId) {
		this.relationOpId = relationOpId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
