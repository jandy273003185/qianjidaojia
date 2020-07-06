package com.qifenqian.bms.accounting.checkV2.bean;

import java.math.BigDecimal;

public class ExplorerCheckDetail {
    private String tradeId;

    private String tradeTypeStr;

	private String agentMchId;

    private String mchId;

    private String mchName;

    private String channelTypeStr;

    private String channelOrderId;

    private BigDecimal tradeAmt;

    private BigDecimal channelFee;

    private BigDecimal tradeFee;

    private BigDecimal transferAmt;

    private String refundOriginOrderId;

    private String orderCreateTimeStr;

    private String orderFinishTimeStr;

    private String checkResultStr;

    private String checkDateStr;
    

    public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeTypeStr() {
		return tradeTypeStr;
	}

	public void setTradeTypeStr(String tradeTypeStr) {
		this.tradeTypeStr = tradeTypeStr;
	}

	public String getAgentMchId() {
		return agentMchId;
	}

	public void setAgentMchId(String agentMchId) {
		this.agentMchId = agentMchId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getChannelTypeStr() {
		return channelTypeStr;
	}

	public void setChannelTypeStr(String channelTypeStr) {
		this.channelTypeStr = channelTypeStr;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public BigDecimal getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(BigDecimal tradeFee) {
		this.tradeFee = tradeFee;
	}

	public BigDecimal getTransferAmt() {
		return transferAmt;
	}

	public void setTransferAmt(BigDecimal transferAmt) {
		this.transferAmt = transferAmt;
	}

	public String getRefundOriginOrderId() {
		return refundOriginOrderId;
	}

	public void setRefundOriginOrderId(String refundOriginOrderId) {
		this.refundOriginOrderId = refundOriginOrderId;
	}

	public String getOrderCreateTimeStr() {
		return orderCreateTimeStr;
	}

	public void setOrderCreateTimeStr(String orderCreateTimeStr) {
		this.orderCreateTimeStr = orderCreateTimeStr;
	}

	public String getOrderFinishTimeStr() {
		return orderFinishTimeStr;
	}

	public void setOrderFinishTimeStr(String orderFinishTimeStr) {
		this.orderFinishTimeStr = orderFinishTimeStr;
	}

	public String getCheckResultStr() {
		return checkResultStr;
	}

	public void setCheckResultStr(String checkResultStr) {
		this.checkResultStr = checkResultStr;
	}

	public String getCheckDateStr() {
		return checkDateStr;
	}

	public void setCheckDateStr(String checkDateStr) {
		this.checkDateStr = checkDateStr;
	}

}
