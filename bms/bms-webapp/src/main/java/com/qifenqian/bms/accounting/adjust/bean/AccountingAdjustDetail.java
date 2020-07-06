package com.qifenqian.bms.accounting.adjust.bean;

import java.math.BigDecimal;

import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.CurrCode;

/**
* 记录调账的主要业务表
*/
public class AccountingAdjustDetail extends AccountingAdjustDetailKey {

	/**
	 * 借方账户类型
	 */
    private AcctType debitAcctType;

	/**
	 * 借方账号
	 */
    private String debitAcctNo;

	/**
	 * 借方账户名称
	 */
    private String debitAcctName;

	/**
	 * 借方客户号
	 */
    private String debitCustId;

	/**
	 * 借方科目
	 */
    private String debitSubjectId;

	/**
	 * 贷方账户类型
	 */
    private AcctType creditAcctType;

	/**
	 * 贷方账号
	 */
    private String creditAcctNo;

	/**
	 * 贷方账户名称
	 */
    private String creditAcctName;

	/**
	 * 贷方客户号
	 */
    private String creditCustId;

	/**
	 * 贷方科目
	 */
    private String creditSubjectId;

	/**
	 * 币别
	 */
    private CurrCode curcde;

	/**
	 * 金额
	 */
    private BigDecimal amt;

    public AcctType getDebitAcctType() {
        return debitAcctType;
    }

    public void setDebitAcctType(AcctType debitAcctType) {
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

    public String getDebitCustId() {
        return debitCustId;
    }

    public void setDebitCustId(String debitCustId) {
        this.debitCustId = debitCustId;
    }

    public String getDebitSubjectId() {
        return debitSubjectId;
    }

    public void setDebitSubjectId(String debitSubjectId) {
        this.debitSubjectId = debitSubjectId;
    }

    public AcctType getCreditAcctType() {
        return creditAcctType;
    }

    public void setCreditAcctType(AcctType creditAcctType) {
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

    public String getCreditCustId() {
        return creditCustId;
    }

    public void setCreditCustId(String creditCustId) {
        this.creditCustId = creditCustId;
    }

    public String getCreditSubjectId() {
        return creditSubjectId;
    }

    public void setCreditSubjectId(String creditSubjectId) {
        this.creditSubjectId = creditSubjectId;
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
}
