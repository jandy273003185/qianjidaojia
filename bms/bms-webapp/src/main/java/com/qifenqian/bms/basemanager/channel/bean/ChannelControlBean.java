package com.qifenqian.bms.basemanager.channel.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChannelControlBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;

	private String mchId;//商户Id
	private String channel;//渠道
	private String subChannel;//下级渠道
	private String status;//渠道状态0关闭1开启
	private BigDecimal limitPerAmt;//单笔交易限额
	private Integer limitDayCount;//单日交易笔数
	private BigDecimal limitDayTotAmt;//单笔交易限额
	private String memo;//备注
	private String modifyId;//修改人ID
	private Date modifyTime;//修改时间
	private String createId;//创建人Id
	private Date createTime;//创建时间
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSubChannel() {
		return subChannel;
	}
	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getLimitPerAmt() {
		return limitPerAmt;
	}
	public void setLimitPerAmt(BigDecimal limitPerAmt) {
		this.limitPerAmt = limitPerAmt;
	}
	public Integer getLimitDayCount() {
		return limitDayCount;
	}
	public void setLimitDayCount(Integer limitDayCount) {
		this.limitDayCount = limitDayCount;
	}
	public BigDecimal getLimitDayTotAmt() {
		return limitDayTotAmt;
	}
	public void setLimitDayTotAmt(BigDecimal limitDayTotAmt) {
		this.limitDayTotAmt = limitDayTotAmt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
