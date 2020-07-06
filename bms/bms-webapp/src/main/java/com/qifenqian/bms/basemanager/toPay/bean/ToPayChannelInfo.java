package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代付渠道信息
 * @author hongjiagui
 *
 */
public class ToPayChannelInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763430823416825484L;
	
	/**渠道编号**/
	private String channelId;
	
	/**渠道名称**/
	private String channelName;
	
	/**代付费率**/
	private BigDecimal channelTopayRate;
	
	/**充值费率**/
	private BigDecimal channelRechargeRate;
	
	/**描述**/
	private String description;

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
	
	
	
	
}
