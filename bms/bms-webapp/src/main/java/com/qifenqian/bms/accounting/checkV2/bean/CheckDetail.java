package com.qifenqian.bms.accounting.checkV2.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.seven.micropay.commons.util.DateUtil;

public class CheckDetail {
    private Integer id;

    private String tradeId;

    private String tradeType;

    private String agentMchId;

    private String mchOrderId;

    private String mchId;

    private String mchName;

    private String channel;

    private String channelSub;

    private String channelType;

    private String channelOrderId;

    private BigDecimal tradeAmt;

    private BigDecimal channelFee;

    private BigDecimal tradeFee;

    private BigDecimal transferAmt;

    private String tradeCurrency;

    private String refundOriginOrderId;

    private BigDecimal refundOriginOrderAmt;

    private Date orderCreateTime;

    private Date orderFinishTime;

    private String checkResult;

    private Date checkDate;

    private Date createTime;
    

	private String checkDateStr;
	
    public String getCheckDateStr() {
		return checkDateStr;
	}

	public void setCheckDateStr(String checkDateStr) {
		this.checkDateStr = checkDateStr;
		if(!StringUtils.isEmpty(checkDateStr)){
			this.checkDate = DateUtil.parse(checkDateStr, DateUtil.YYYY_MM_DD);
		}
		
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getAgentMchId() {
        return agentMchId;
    }

    public void setAgentMchId(String agentMchId) {
        this.agentMchId = agentMchId == null ? null : agentMchId.trim();
    }

    public String getMchOrderId() {
        return mchOrderId;
    }

    public void setMchOrderId(String mchOrderId) {
        this.mchOrderId = mchOrderId == null ? null : mchOrderId.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName == null ? null : mchName.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getChannelSub() {
        return channelSub;
    }

    public void setChannelSub(String channelSub) {
        this.channelSub = channelSub == null ? null : channelSub.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId == null ? null : channelOrderId.trim();
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

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency == null ? null : tradeCurrency.trim();
    }

    public String getRefundOriginOrderId() {
        return refundOriginOrderId;
    }

    public void setRefundOriginOrderId(String refundOriginOrderId) {
        this.refundOriginOrderId = refundOriginOrderId == null ? null : refundOriginOrderId.trim();
    }

    public BigDecimal getRefundOriginOrderAmt() {
        return refundOriginOrderAmt;
    }

    public void setRefundOriginOrderAmt(BigDecimal refundOriginOrderAmt) {
        this.refundOriginOrderAmt = refundOriginOrderAmt;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}