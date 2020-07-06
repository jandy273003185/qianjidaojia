package com.qifenqian.bms.bal.accountResult.bean;

import java.util.Date;

public class BaseChannel {
	
	
	
	private String channelId;
	private String channelNo;
	private String channelCode;
	private String channelName;
	private String version;
	private String certificate;
	private String channelServers;
	private String gatewayServers;
	private String memo;
	private String status;
	private String instUser;
	private Date instDatetime;
	private Date lupdUser;
	private Date lupdDatetime;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getChannelServers() {
		return channelServers;
	}
	public void setChannelServers(String channelServers) {
		this.channelServers = channelServers;
	}
	public String getGatewayServers() {
		return gatewayServers;
	}
	public void setGatewayServers(String gatewayServers) {
		this.gatewayServers = gatewayServers;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public Date getLupdUser() {
		return lupdUser;
	}
	public void setLupdUser(Date lupdUser) {
		this.lupdUser = lupdUser;
	}
	public Date getLupdDatetime() {
		return lupdDatetime;
	}
	public void setLupdDatetime(Date lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}
	
	
}
