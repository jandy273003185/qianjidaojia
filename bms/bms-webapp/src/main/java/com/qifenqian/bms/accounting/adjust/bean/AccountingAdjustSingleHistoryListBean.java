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
public class AccountingAdjustSingleHistoryListBean extends AccountingAdjustMain {

	/**
	 * 业务编号
	 */
	private String opId;

	/**
	 * 账户类型
	 */
	private String acctType;
	/**
	 * 账号
	 */
	private String acctNo;

	/**
	 * 账户名称
	 */
	private String acctName;

	/**
	 * 币别
	 */
	private CurrCode curcde;

	/**
	 * 金额
	 */
	private BigDecimal amt;

	/**
	 * 可用金额, 如果涉及到可用金额和在途金额, 可用金额与在途金额之和等于amt
	 */
	private BigDecimal usableAmt;

	/**
	 * 在途金额
	 */
	private BigDecimal onwayAmt;

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

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
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

	public BigDecimal getUsableAmt() {
		return usableAmt;
	}

	public void setUsableAmt(BigDecimal usableAmt) {
		this.usableAmt = usableAmt;
	}

	public BigDecimal getOnwayAmt() {
		return onwayAmt;
	}

	public void setOnwayAmt(BigDecimal onwayAmt) {
		this.onwayAmt = onwayAmt;
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
