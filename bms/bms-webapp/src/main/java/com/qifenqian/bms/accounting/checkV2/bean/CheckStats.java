package com.qifenqian.bms.accounting.checkV2.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.seven.micropay.commons.util.DateUtil;

public class CheckStats {
    private Integer id;

    private String channelType;

    private Integer checkAllNum;

    private BigDecimal checkAllAmt;

    private Integer checkSuccessNum;

    private BigDecimal checkSuccessTradeAmt;

    private Integer checkPlackNum;

    private BigDecimal checkPlackAmt;

    private Integer checkClackNum;

    private BigDecimal checkClackAmt;

    private Integer checkOthrerNum;

    private BigDecimal checkOtherAmt;

    private Date checkDate;

    private Date createTime;

	private String checkDateStr;
	
    public String getCheckDateStr() {
		return checkDateStr;
	}

	public void setCheckDateStr(String checkDateStr) {
		this.checkDateStr = checkDateStr;
		if(!StringUtils.isEmpty(checkDateStr)){
			this.checkDate = DateUtil.parse(checkDateStr, DateUtil.YYYY_MM_DD);
		}
		
	}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public Integer getCheckAllNum() {
        return checkAllNum;
    }

    public void setCheckAllNum(Integer checkAllNum) {
        this.checkAllNum = checkAllNum;
    }

    public BigDecimal getCheckAllAmt() {
        return checkAllAmt;
    }

    public void setCheckAllAmt(BigDecimal checkAllAmt) {
        this.checkAllAmt = checkAllAmt;
    }

    public Integer getCheckSuccessNum() {
        return checkSuccessNum;
    }

    public void setCheckSuccessNum(Integer checkSuccessNum) {
        this.checkSuccessNum = checkSuccessNum;
    }

    public BigDecimal getCheckSuccessTradeAmt() {
        return checkSuccessTradeAmt;
    }

    public void setCheckSuccessTradeAmt(BigDecimal checkSuccessTradeAmt) {
        this.checkSuccessTradeAmt = checkSuccessTradeAmt;
    }

    public Integer getCheckPlackNum() {
        return checkPlackNum;
    }

    public void setCheckPlackNum(Integer checkPlackNum) {
        this.checkPlackNum = checkPlackNum;
    }

    public BigDecimal getCheckPlackAmt() {
        return checkPlackAmt;
    }

    public void setCheckPlackAmt(BigDecimal checkPlackAmt) {
        this.checkPlackAmt = checkPlackAmt;
    }

    public Integer getCheckClackNum() {
        return checkClackNum;
    }

    public void setCheckClackNum(Integer checkClackNum) {
        this.checkClackNum = checkClackNum;
    }

    public BigDecimal getCheckClackAmt() {
        return checkClackAmt;
    }

    public void setCheckClackAmt(BigDecimal checkClackAmt) {
        this.checkClackAmt = checkClackAmt;
    }

    public Integer getCheckOthrerNum() {
        return checkOthrerNum;
    }

    public void setCheckOthrerNum(Integer checkOthrerNum) {
        this.checkOthrerNum = checkOthrerNum;
    }

    public BigDecimal getCheckOtherAmt() {
        return checkOtherAmt;
    }

    public void setCheckOtherAmt(BigDecimal checkOtherAmt) {
        this.checkOtherAmt = checkOtherAmt;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}