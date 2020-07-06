package com.qifenqian.bms.meeting.redPacket.bean;

public class MeetRedPacketBillConditionBean extends MeetRedPacketBill{
	
	private String sendBeginTime;
	
	private String sendEndTime;
	
	private String receiveBeginTime;
	
	private String receiveEndTime;

	public String getSendBeginTime() {
		return sendBeginTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public String getReceiveBeginTime() {
		return receiveBeginTime;
	}

	public String getReceiveEndTime() {
		return receiveEndTime;
	}

	public void setSendBeginTime(String sendBeginTime) {
		this.sendBeginTime = sendBeginTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public void setReceiveBeginTime(String receiveBeginTime) {
		this.receiveBeginTime = receiveBeginTime;
	}

	public void setReceiveEndTime(String receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}

	
	
}
