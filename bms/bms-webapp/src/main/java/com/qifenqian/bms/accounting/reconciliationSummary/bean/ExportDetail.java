package com.qifenqian.bms.accounting.reconciliationSummary.bean;



public class ExportDetail {
	//主键
	private int Id;
	
	//订单号
	private String orderId;
	
	//外部订单号
	private String oldOrderId;

	//支付渠道
	private String paychannelTypeStr;
	
	//交易金额
	private String tradeAmt;
	
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

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
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

	public String getPaychannelTypeStr() {
		return paychannelTypeStr;
	}

	public void setPaychannelTypeStr(String paychannelTypeStr) {
		this.paychannelTypeStr = paychannelTypeStr;
	}
	
}
