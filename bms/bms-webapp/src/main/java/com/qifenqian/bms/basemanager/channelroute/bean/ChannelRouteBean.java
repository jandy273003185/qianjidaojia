package com.qifenqian.bms.basemanager.channelroute.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChannelRouteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3726340373762750777L;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*渠道ID*/
	private String id;
	
	/*渠道名称*/
	private String name;

	/*支付渠道类型*/
	private String paychannelType;
	
	/*网关类型*/
	private String gatewayType;
	
	/*计费方式(按单笔:each,  按费率:rate)*/
	private String feeType;
	
	/*费率(百分比)*/
	private Integer fee;
	
	/*交易最小限度*/
	private BigDecimal minLim;
	
	/*交易最大额度*/
	private BigDecimal maxLim;
	
	/*支持卡种(借记卡：debit，贷记卡：credit, 两者皆可：both)*/
	private String cardType;
	
	/*权重*/
	private Integer weight;
	
	/*状态 (启用enable/停用unable)*/
	private String status;
	
	/*累计发送次数*/
	private Integer totalCnt;
	
	/*累计失败次数*/
	private Integer failCnt;
	
	/*备注*/
	private String remark;
	
	/*创建日期*/
	private Date createTime;

	
	
	/*渠道编码*/
	private String channelCode;
	
	/*渠道名称*/
	private String channelName;
	
	/*编码*/
	private String code;
	
	/*渠道路由ID*/
	private String channelRouteId;
	
	/*渠道ID*/
	private String channelId;
	
	private String channels;
	
	
	

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getChannelRouteId() {
		return channelRouteId;
	}

	public void setChannelRouteId(String channelRouteId) {
		this.channelRouteId = channelRouteId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
		

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaychannelType() {
		return paychannelType;
	}

	public void setPaychannelType(String paychannelType) {
		this.paychannelType = paychannelType;
	}

	public String getGatewayType() {
		return gatewayType;
	}

	public void setGatewayType(String gatewayType) {
		this.gatewayType = gatewayType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public BigDecimal getMinLim() {
		return minLim;
	}

	public void setMinLim(BigDecimal minLim) {
		this.minLim = minLim;
	}

	public BigDecimal getMaxLim() {
		return maxLim;
	}

	public void setMaxLim(BigDecimal maxLim) {
		this.maxLim = maxLim;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Integer getFailCnt() {
		return failCnt;
	}

	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
	
	
}
