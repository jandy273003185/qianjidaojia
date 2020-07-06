package com.qifenqian.bms.meeting.luckdraw.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.meeting.prize.type.AmountType;
import com.qifenqian.bms.meeting.prize.type.CustScope;
import com.qifenqian.bms.meeting.prize.type.IsRepeatWin;
import com.qifenqian.bms.meeting.prize.type.LimitSex;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;

/**
 * @project sevenpay-bms-web
 * @fileName Prize.java
 * @author huiquan.ma
 * @date 2015年12月15日
 * @memo 
 */
public class Prize implements Serializable{

	private static final long serialVersionUID = 1L;

    /** 奖项索引编号*/
	private int prizeId;
    /** 奖项编码*/
	private String prizeNo;
    /** 归属活动编号*/
	private int activityId;
    /** 奖项英文编码:SPECIAL/FIRST/...*/
	private String prizeCode;
    /** 奖项中文名称*/
	private String prizeName;
    /** 名额数量*/
	private int quotaNumber;
    /** 金额设置类型：RANDOM随机/FIXED固定*/
	private AmountType amountType;
    /** 固定金额大小*/
	private BigDecimal fixedAmount;
    /** 随机金额下限*/
	private BigDecimal randomAmountMin;
    /** 随机金额上限*/
	private BigDecimal randomAmountMax;
	/** 奖项排序*/
	private int prizeSort;
    /** 中奖后有效天数*/
	private int effectiveDays;
    /** 活动内是否可重复中奖：Y可以/N不刻意*/
	private IsRepeatWin isRepeatWin;
    /** 可抽奖客户范围：ALL所有/LIMIT有限制的*/
	private CustScope custScope;
    /** 性别限制：ALL所有/MAN男/WOMAN女（cust_scope有限制时填写）*/
	private LimitSex limitSex;
    /** 注册时间开始限制（cust_scope有限制时填写）*/
	private String limitRegisterDateStart;
    /** 注册时间结束限制（cust_scope有限制时填写）*/
	private String limitRegisterDateEnd;
    /** 备注*/
	private String memo;
    /** 状态：VALID有效/DISABLE失效*/
	private PrizeStatus status;
    /** 初始写入人*/
	private String instUser;
    /** 初始时间*/
	private Date instDatetime;
    /** 最后更改人*/
	private String lupdUser;
    /** 最后更改时间*/
	private Date lupdDatetime;
	
	private String dynamicSql;
	/** 已中奖数量*/
	private int winNumber;
	/** 剩余数量*/
	private int remainNumber;
	
	private String activityName;
	
	
	public String getDynamicSql() {
		return dynamicSql;
	}
	public void setDynamicSql(String dynamicSql) {
		this.dynamicSql = dynamicSql;
	}
	public int getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeNo() {
		return prizeNo;
	}
	public void setPrizeNo(String prizeNo) {
		this.prizeNo = prizeNo;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getPrizeCode() {
		return prizeCode;
	}
	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public int getQuotaNumber() {
		return quotaNumber;
	}
	public void setQuotaNumber(int quotaNumber) {
		this.quotaNumber = quotaNumber;
	}
	public AmountType getAmountType() {
		return amountType;
	}
	public void setAmountType(AmountType amountType) {
		this.amountType = amountType;
	}
	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public BigDecimal getRandomAmountMin() {
		return randomAmountMin;
	}
	public void setRandomAmountMin(BigDecimal randomAmountMin) {
		this.randomAmountMin = randomAmountMin;
	}
	public BigDecimal getRandomAmountMax() {
		return randomAmountMax;
	}
	public void setRandomAmountMax(BigDecimal randomAmountMax) {
		this.randomAmountMax = randomAmountMax;
	}
	public int getEffectiveDays() {
		return effectiveDays;
	}
	public void setEffectiveDays(int effectiveDays) {
		this.effectiveDays = effectiveDays;
	}
	public IsRepeatWin getIsRepeatWin() {
		return isRepeatWin;
	}
	public void setIsRepeatWin(IsRepeatWin isRepeatWin) {
		this.isRepeatWin = isRepeatWin;
	}
	public CustScope getCustScope() {
		return custScope;
	}
	public void setCustScope(CustScope custScope) {
		this.custScope = custScope;
	}
	public LimitSex getLimitSex() {
		return limitSex;
	}
	public void setLimitSex(LimitSex limitSex) {
		this.limitSex = limitSex;
	}
	public String getLimitRegisterDateStart() {
		return limitRegisterDateStart;
	}
	public void setLimitRegisterDateStart(String limitRegisterDateStart) {
		this.limitRegisterDateStart = limitRegisterDateStart;
	}
	public String getLimitRegisterDateEnd() {
		return limitRegisterDateEnd;
	}
	public void setLimitRegisterDateEnd(String limitRegisterDateEnd) {
		this.limitRegisterDateEnd = limitRegisterDateEnd;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public PrizeStatus getStatus() {
		return status;
	}
	public void setStatus(PrizeStatus status) {
		this.status = status;
	}
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	public Date getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getLupdUser() {
		return lupdUser;
	}
	public void setLupdUser(String lupdUser) {
		this.lupdUser = lupdUser;
	}
	public Date getLupdDatetime() {
		return lupdDatetime;
	}
	public void setLupdDatetime(Date lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}
	public int getPrizeSort() {
		return prizeSort;
	}
	public void setPrizeSort(int prizeSort) {
		this.prizeSort = prizeSort;
	}
	public int getWinNumber() {
		return winNumber;
	}
	public void setWinNumber(int winNumber) {
		this.winNumber = winNumber;
	}
	public int getRemainNumber() {
		return remainNumber;
	}
	public void setRemainNumber(int remainNumber) {
		this.remainNumber = remainNumber;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
}


