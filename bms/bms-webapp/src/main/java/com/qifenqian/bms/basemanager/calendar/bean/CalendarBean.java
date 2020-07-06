package com.qifenqian.bms.basemanager.calendar.bean;

/***
 * 日历对象
 * @author ganzheng.ge
 *
 */
public class CalendarBean {
	private String date;// 日期：YYYY-MM-DD
	private String dateType;// 日期类型：Sun Mon Thu Wed The Fri Sat
	private String holiday;// 假期：国庆，元旦...
	private String isWork;// 是否工作日
	private String instUser;//

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getInstDateTime() {
		return instDateTime;
	}

	public void setInstDateTime(String instDateTime) {
		this.instDateTime = instDateTime;
	}

	public String getLastupdateUser() {
		return lastupdateUser;
	}

	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}

	public String getLastupdateTime() {
		return lastupdateTime;
	}

	public void setLastupdateTime(String lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	private String instDateTime;
	private String lastupdateUser;
	private String lastupdateTime;
}
