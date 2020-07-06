package com.qifenqian.bms.meeting.prize.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.qifenqian.bms.meeting.prize.type.AmountType;
import com.qifenqian.bms.meeting.prize.type.CustScope;
import com.qifenqian.bms.meeting.prize.type.IsRepeatWin;
import com.qifenqian.bms.meeting.prize.type.LimitSex;
import com.qifenqian.bms.meeting.prize.type.OverallControlType;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;
import com.qifenqian.bms.meeting.prize.type.PrizeType;

public class PrizeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2924260528691276380L;

	/** 奖项索引编号 */
	private Integer prizeId;
	/** 奖项编码 */
	private String prizeNo;
	/** 归属活动编号 */
	private Integer activityId;
	/** 活动名称 */
	private String activityName;
	/** 奖项英文编码:SPECIAL/FIRST/... */
	private String prizeCode;
	/** 奖项中文名称 */
	private String prizeName;
	/** 奖项类型：LUCK_DRAW抽奖/FULL_AWARD全员奖项 */
	private PrizeType prizeType;
	/** 总控类型:AMOUNT总额度/NUMBER总数量 */
	private OverallControlType overallControlType;
	/** 名额数量 */
	private Integer quotaNumber;
	/** 已中奖数量 */
	private Integer winNumber;
	/** 总额数量 */
	private BigDecimal quotaAmount;
	/** 已中奖总额 */
	private BigDecimal winAmount;
	/** 中奖概率因子:ceil(rand() * 2) = 1 */
	private Integer oddsFactor;
	/** 金额设置类型：RANDOM随机/FIXED固定 */
	private AmountType amountType;
	/** 固定金额大小 */
	private BigDecimal fixedAmount;
	/** 随机金额下限 */
	private BigDecimal randomAmountMin;
	/** 随机金额上限 */
	private BigDecimal randomAmountMax;
	/** 排序 */
	private Integer prizeSort;
	/** 中奖后有效小时 */
	private Integer effectiveHours;
	/** 活动内是否可重复中奖：Y可以/N不刻意 */
	private IsRepeatWin isRepeatWin;
	/** 可抽奖客户范围：ALL所有/LIMIT有限制的 */
	private CustScope custScope;
	/** 性别限制：ALL所有/MAN男/WOMAN女（cust_scope有限制时填写） */
	private LimitSex limitSex;
	/** 注册时间开始限制（cust_scope有限制时填写） */
	private String limitRegisterDateStart;
	/** 注册时间结束限制（cust_scope有限制时填写） */
	private String limitRegisterDateEnd;
	/** 备注 */
	private String memo;
	/** 状态：INIT初始/DRAW_IN抽奖中/DISABLE失效/OVER已抽完 */
	private PrizeStatus status;
	/** 初始写入人 */
	private Integer instUser;
	/** 初始时间 */
	private String instDatetime;
	/** 最后更改人 */
	private Integer lupdUser;
	/** 最后更改时间 */
	private String lupdDatetime;
	
	private String instUserName;
	
	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeNo() {
		return prizeNo;
	}

	public void setPrizeNo(String prizeNo) {
		this.prizeNo = prizeNo;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public PrizeType getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(PrizeType prizeType) {
		this.prizeType = prizeType;
	}

	public OverallControlType getOverallControlType() {
		return overallControlType;
	}

	public void setOverallControlType(OverallControlType overallControlType) {
		this.overallControlType = overallControlType;
	}

	public Integer getQuotaNumber() {
		return quotaNumber;
	}

	public void setQuotaNumber(Integer quotaNumber) {
		this.quotaNumber = quotaNumber;
	}

	public Integer getWinNumber() {
		return winNumber;
	}

	public void setWinNumber(Integer winNumber) {
		this.winNumber = winNumber;
	}

	public BigDecimal getQuotaAmount() {
		return quotaAmount;
	}

	public void setQuotaAmount(BigDecimal quotaAmount) {
		this.quotaAmount = quotaAmount;
	}

	public BigDecimal getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}

	public Integer getOddsFactor() {
		return oddsFactor;
	}

	public void setOddsFactor(Integer oddsFactor) {
		this.oddsFactor = oddsFactor;
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

	public Integer getPrizeSort() {
		return prizeSort;
	}

	public void setPrizeSort(Integer prizeSort) {
		this.prizeSort = prizeSort;
	}

	public Integer getEffectiveHours() {
		return effectiveHours;
	}

	public void setEffectiveHours(Integer effectiveHours) {
		this.effectiveHours = effectiveHours;
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

	public Integer getInstUser() {
		return instUser;
	}

	public void setInstUser(Integer instUser) {
		this.instUser = instUser;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public Integer getLupdUser() {
		return lupdUser;
	}

	public void setLupdUser(Integer lupdUser) {
		this.lupdUser = lupdUser;
	}

	public String getLupdDatetime() {
		return lupdDatetime;
	}

	public void setLupdDatetime(String lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

}
