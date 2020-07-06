package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户渠道实体类
 * 对应td_merchant_channel表
 * @author hongjiagui
 *
 */
public class TdChannelInfo implements Serializable{

	
	private static final long serialVersionUID = -1991423270943941203L;

	/**
	 * 渠道编号
	 */
	private String channelId;
    /**
     * 渠道名称
     */
	private String channelName;
	/**
	 * 代付费率
	 */
	private BigDecimal channelTopayRate;
	/**
	 * 充值费率
	 */
	private BigDecimal channelRechargeRate;
	
	/**
	 * 充值扣费方式：
	 * fixed 固定
	 * rate 费率；
	 */
	private String channelRechargeType;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 七分钱代付充值费率（收取商户的）
	 */
	private BigDecimal sevenpayRechargeRate;
	/**
	 * 七分钱代付充值收费：fixed 固定，rate 费率；（收取商户的）
	 */
	private String sevenpayRechargeType;
	
	
	

	public BigDecimal getSevenpayRechargeRate() {
		return sevenpayRechargeRate;
	}

	public void setSevenpayRechargeRate(BigDecimal sevenpayRechargeRate) {
		this.sevenpayRechargeRate = sevenpayRechargeRate;
	}

	public String getSevenpayRechargeType() {
		return sevenpayRechargeType;
	}

	public void setSevenpayRechargeType(String sevenpayRechargeType) {
		this.sevenpayRechargeType = sevenpayRechargeType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public BigDecimal getChannelTopayRate() {
		return channelTopayRate;
	}

	public void setChannelTopayRate(BigDecimal channelTopayRate) {
		this.channelTopayRate = channelTopayRate;
	}

	public BigDecimal getChannelRechargeRate() {
		return channelRechargeRate;
	}

	public void setChannelRechargeRate(BigDecimal channelRechargeRate) {
		this.channelRechargeRate = channelRechargeRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelRechargeType() {
		return channelRechargeType;
	}

	public void setChannelRechargeType(String channelRechargeType) {
		this.channelRechargeType = channelRechargeType;
	}
	
	
	@Override
	public String toString() {
		return "TdChannelInfo [channelId=" + channelId + ", channelName=" + channelName + ", channelTopayRate="
				+ channelTopayRate + ", channelRechargeRate=" + channelRechargeRate + ", channelRechargeType="
				+ channelRechargeType + ", description=" + description + "]";
	}

}
