package com.qifenqian.bms.basemanager.tradeControl.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.qifenqian.bms.basemanager.type.AcctType;

public class TradeControl implements Serializable {

	private static final long serialVersionUID = -932608130472369100L;

	/**
	 * 交易类型
	 */
	private String tradeType;
	
	/**
	 * 付方账户类型
	 */
	private AcctType payerAcctType;
	
	/**
	 * 收方账户类型
	 */
	private AcctType payeeAcctType;
	
	/**
	 * 电脑端每日交易次数限制
	 */
	private int pcNoCntPerDay;
	
	/**
	 * 电脑端每笔交易金额限制
	 */
	private BigDecimal pcNoAmtPerOnce;
	
	/**
	 * 电脑端每日交易总额限制
	 */
	private BigDecimal pcNoAmtPerDay;
	
	/**
	 * 电脑端每月交易总额限制
	 */
	private BigDecimal pcNoAmtPerMonth;
	
	/**
	 * 电脑端每日交易次数限制
	 */
	private int pcYesCntPerDay;
	
	/**
	 * 电脑端每笔交易金额限制
	 */
	private BigDecimal pcYesAmtPerOnce;
	
	/**
	 * 电脑端每日交易总额限制
	 */
	private BigDecimal pcYesAmtPerDay;
	
	/**
	 * 电脑端每月交易总额限制
	 */
	private BigDecimal pcYesAmtPerMonth;
	
	/**
	 * 移动端每日交易次数限制
	 */
	private int mbWdCntPerDay;
	
	/**
	 * 移动端每笔交易金额限制
	 */
	private BigDecimal mbWdAmtPerOnce;
	
	/**
	 * 移动端每日交易总额限制
	 */
	private BigDecimal mbWdAmtPerDay;
	
	/**
	 * 移动端每月交易总额限制
	 */
	private BigDecimal mbWdAmtPerMonth;
	
	/**
	 * 电脑端与移动端是否共同额度
	 */
	private String isShare;


	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public int getPcNoCntPerDay() {
		return pcNoCntPerDay;
	}

	public void setPcNoCntPerDay(int pcNoCntPerDay) {
		this.pcNoCntPerDay = pcNoCntPerDay;
	}

	public BigDecimal getPcNoAmtPerOnce() {
		return pcNoAmtPerOnce;
	}

	public void setPcNoAmtPerOnce(BigDecimal pcNoAmtPerOnce) {
		this.pcNoAmtPerOnce = pcNoAmtPerOnce;
	}

	public BigDecimal getPcNoAmtPerDay() {
		return pcNoAmtPerDay;
	}

	public void setPcNoAmtPerDay(BigDecimal pcNoAmtPerDay) {
		this.pcNoAmtPerDay = pcNoAmtPerDay;
	}

	public BigDecimal getPcNoAmtPerMonth() {
		return pcNoAmtPerMonth;
	}

	public void setPcNoAmtPerMonth(BigDecimal pcNoAmtPerMonth) {
		this.pcNoAmtPerMonth = pcNoAmtPerMonth;
	}

	public int getPcYesCntPerDay() {
		return pcYesCntPerDay;
	}

	public void setPcYesCntPerDay(int pcYesCntPerDay) {
		this.pcYesCntPerDay = pcYesCntPerDay;
	}

	public BigDecimal getPcYesAmtPerOnce() {
		return pcYesAmtPerOnce;
	}

	public void setPcYesAmtPerOnce(BigDecimal pcYesAmtPerOnce) {
		this.pcYesAmtPerOnce = pcYesAmtPerOnce;
	}

	public BigDecimal getPcYesAmtPerDay() {
		return pcYesAmtPerDay;
	}

	public void setPcYesAmtPerDay(BigDecimal pcYesAmtPerDay) {
		this.pcYesAmtPerDay = pcYesAmtPerDay;
	}

	public BigDecimal getPcYesAmtPerMonth() {
		return pcYesAmtPerMonth;
	}

	public void setPcYesAmtPerMonth(BigDecimal pcYesAmtPerMonth) {
		this.pcYesAmtPerMonth = pcYesAmtPerMonth;
	}

	public int getMbWdCntPerDay() {
		return mbWdCntPerDay;
	}

	public void setMbWdCntPerDay(int mbWdCntPerDay) {
		this.mbWdCntPerDay = mbWdCntPerDay;
	}

	public BigDecimal getMbWdAmtPerOnce() {
		return mbWdAmtPerOnce;
	}

	public void setMbWdAmtPerOnce(BigDecimal mbWdAmtPerOnce) {
		this.mbWdAmtPerOnce = mbWdAmtPerOnce;
	}

	public BigDecimal getMbWdAmtPerDay() {
		return mbWdAmtPerDay;
	}

	public void setMbWdAmtPerDay(BigDecimal mbWdAmtPerDay) {
		this.mbWdAmtPerDay = mbWdAmtPerDay;
	}

	public BigDecimal getMbWdAmtPerMonth() {
		return mbWdAmtPerMonth;
	}

	public void setMbWdAmtPerMonth(BigDecimal mbWdAmtPerMonth) {
		this.mbWdAmtPerMonth = mbWdAmtPerMonth;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public AcctType getPayerAcctType() {
		return payerAcctType;
	}

	public void setPayerAcctType(AcctType payerAcctType) {
		this.payerAcctType = payerAcctType;
	}

	public AcctType getPayeeAcctType() {
		return payeeAcctType;
	}

	public void setPayeeAcctType(AcctType payeeAcctType) {
		this.payeeAcctType = payeeAcctType;
	}
	
	
}
