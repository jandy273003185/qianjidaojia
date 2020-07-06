package com.qifenqian.bms.basemanager.channel.bean;

import java.io.Serializable;
import java.util.Date;

public class ChannelInfoBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;

	private String channelId;//渠道id
	private String channel;//渠道
	private String channelName;//渠道名称
	private String subChannel;//下级渠道
	private String subChannelName;//下级渠道名称
	private String modifyId;//修改人ID
	private Date modifyTime;//修改时间
	private String createId;//创建人Id
	private Date createTime;//创建时间
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getSubChannel() {
		return subChannel;
	}
	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}
	public String getSubChannelName() {
		return subChannelName;
	}
	public void setSubChannelName(String subChannelName) {
		this.subChannelName = subChannelName;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
