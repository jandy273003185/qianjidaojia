package com.qifenqian.bms.meeting.luckdraw.bean;

import java.io.Serializable;
import java.util.Date;

import com.qifenqian.bms.meeting.activity.type.ActivityPermitType;
import com.qifenqian.bms.meeting.activity.type.ActivityStatus;

/**
 * @project sevenpay-bms-web
 * @fileName Activity.java
 * @author huiquan.ma
 * @date 2015年12月19日
 * @memo 
 */
public class Activity implements Serializable {

	private static final long serialVersionUID = -7798476217526372873L;
	
	/** 活动索引编号*/
	private int activityId;
    /** 活动编码*/
	private String activityNo;
    /** 活动英文编码*/
	private String activityCode;
    /** 活动中文名称*/
	private String activityName;
    /** 活动地点*/
	private String activityPlace;
    /** 开始日期*/
	private String startDate;
    /** 结束日期*/
	private String endDate;
    /** 许可类型：ALL全部/IP/USER用户/MAC地址...*/
	private ActivityPermitType permitType;
    /** 备注多个以分号分割*/
	private String permitValue;
    /** 备注*/
	private String memo;
    /** 状态：INIT初始/STAGE_ING进行中/OVER已结束/DISABLE无效*/
	private ActivityStatus status;
    /** 初始写入人*/
	private String instUser;
    /** 初始时间*/
	private Date instDatetime;
    /** 最后更改人*/
	private String lupdUser;
    /** 最后更改时间*/
	private Date lupdDatetime;
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ActivityPermitType getPermitType() {
		return permitType;
	}
	public void setPermitType(ActivityPermitType permitType) {
		this.permitType = permitType;
	}
	public String getPermitValue() {
		return permitValue;
	}
	public void setPermitValue(String permitValue) {
		this.permitValue = permitValue;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public ActivityStatus getStatus() {
		return status;
	}
	public void setStatus(ActivityStatus status) {
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
}


