package com.qifenqian.bms.accounting.adjust.bean;

import java.math.BigDecimal;

import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.CurrCode;
import com.sevenpay.invoke.common.type.RequestColumnValues.IsAdjustJGKJ;

/**
* 单边调账业务明细表
*/
public class AccountingSingleAdjustDetail extends AccountingSingleAdjustDetailKey {

	/**
	 * 账号
	 */
    private String acctNo;

	/**
	 * 账户名称
	 */
    private String acctName;

	/**
	 * 账户类型
	 */
    private AcctType acctType;

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

	/**
	 * 是否联通交广科技调账
	 */
    private IsAdjustJGKJ isAdjustJGKJ;

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

    public AcctType getAcctType() {
        return acctType;
    }

    public void setAcctType(AcctType acctType) {
        this.acctType = acctType;
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

    public IsAdjustJGKJ getIsAdjustJGKJ() {
        return isAdjustJGKJ;
    }

    public void setIsAdjustJGKJ(IsAdjustJGKJ isAdjustJGKJ) {
        this.isAdjustJGKJ = isAdjustJGKJ;
    }
}
