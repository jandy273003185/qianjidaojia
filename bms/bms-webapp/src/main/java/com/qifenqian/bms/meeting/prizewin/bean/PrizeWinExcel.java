package com.qifenqian.bms.meeting.prizewin.bean;

import java.math.BigDecimal;

/**
 * @project sevenpay-bms-web
 * @fileName PrizeWin.java
 * @author huiquan.ma
 * @date 2015年12月15日
 * @memo
 */
public class PrizeWinExcel {

	/** 中奖流水索引编号 */
	private int winId;
	/** 奖项编号 */
	private int prizeId;
	/** 奖项名称 */
	private String prizeName;
	/** 中奖人客户号 */
	private String winCustId;
	/** 中奖人姓名 */
	private String winCustName;
	/** 中奖人手机号 */
	private String winCustPhone;
	/** 中奖金额 */
	private BigDecimal winAmount;
	/** 有效截止时间 */
	private String effectiveDeadline;
	/** 状态：WAIT_RECEIVE待领取/HAVE_RECEIVED已领取/TAKE_BACK已收回/DISABLE无效 */
	private String status;
	/** 初始写入人 */
	private int instUser;
	/** 写入日期 */
	private String instDate;

	private String winMark;
	/** 初始时间 */
	private String instDatetime;
	/** 最后更改人 */
	private int lupdUser;
	/** 最后更改时间 */
	private String lupdDatetime;

	public int getWinId() {
		return winId;
	}

	public void setWinId(int winId) {
		this.winId = winId;
	}

	public int getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getWinCustId() {
		return winCustId;
	}

	public void setWinCustId(String winCustId) {
		this.winCustId = winCustId;
	}

	public String getWinCustName() {
		return winCustName;
	}

	public void setWinCustName(String winCustName) {
		this.winCustName = winCustName;
	}

	public String getWinCustPhone() {
		return winCustPhone;
	}

	public void setWinCustPhone(String winCustPhone) {
		this.winCustPhone = winCustPhone;
	}

	public BigDecimal getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}

	public String getEffectiveDeadline() {
		return effectiveDeadline;
	}

	public void setEffectiveDeadline(String effectiveDeadline) {
		this.effectiveDeadline = effectiveDeadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getInstUser() {
		return instUser;
	}

	public void setInstUser(int instUser) {
		this.instUser = instUser;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getWinMark() {
		return winMark;
	}

	public void setWinMark(String winMark) {
		this.winMark = winMark;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public int getLupdUser() {
		return lupdUser;
	}

	public void setLupdUser(int lupdUser) {
		this.lupdUser = lupdUser;
	}

	public String getLupdDatetime() {
		return lupdDatetime;
	}

	public void setLupdDatetime(String lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}
}
