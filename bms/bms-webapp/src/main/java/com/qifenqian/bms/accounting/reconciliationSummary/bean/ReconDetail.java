package com.qifenqian.bms.accounting.reconciliationSummary.bean;


import com.seven.micropay.channel.enums.PaychannelType;

public class ReconDetail {

	//主键
	private int Id;
	
	//订单号
	private String orderId;
	
	//外部订单号
	private String oldOrderId;
	
	//支付渠道
	private PaychannelType paychannelType;
	
	//交易金额
	private String tradeAmt;
	
	//手续费
	private String tradeFee;
	
	//交易结果状态
	private String reconResult;
	
	//交易状态
	private String  inOut;
	
	//交易时间
	private String tradeTime;
	
	//创建时间
	private String createTime;

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}



	
	
	public String getOldOrderId() {
		return oldOrderId;
	}

	public void setOldOrderId(String oldOrderId) {
		this.oldOrderId = oldOrderId;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public PaychannelType getPaychannelType() {
		return paychannelType;
	}

	public void setPaychannelType(PaychannelType paychannelType) {
		this.paychannelType = paychannelType;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(String tradeFee) {
		this.tradeFee = tradeFee;
	}

	public String getReconResult() {
		return reconResult;
	}

	public void setReconResult(String reconResult) {
		this.reconResult = reconResult;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
