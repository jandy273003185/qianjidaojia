package com.qifenqian.bms.basemanager.transfer.bean;

import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMain;

public class TransferBean extends TdTradeBillMain {
	private String channelId;
	private String workDate;
	private String startWorkDate;
	private String endWorkDate;
	private String beginTime;
	private String endTime;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
