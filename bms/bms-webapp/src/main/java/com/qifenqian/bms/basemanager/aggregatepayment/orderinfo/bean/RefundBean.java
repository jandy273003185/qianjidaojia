package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean;

import java.math.BigDecimal;

public class RefundBean extends DealOperation{
	
	private String refundId;
	private String merRefundId;
	private String orderId;
	private String merchantCode;
	private BigDecimal totalAmt;
	private BigDecimal refundAmt;
	private String settleAmt;
	private String channelTransId;
	private String channelRefundId;
	private String refundChannel;
	private String errCode;
	private String errDesc;
	private String refundTime;
	private String refundState;
	private String createTime;
	private String createId;
	private String channel;
	private String prodName;
	private String orderType;
	private String coreSubmitstate;
	private String coreSn;
	private String orderCoreReturnCode;
	private String orderCoreReturnMsg;
	private String orderCoreReturnTime;
	
	private static final long serialVersionUID = -1974101805090576902L;
	
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getMerRefundId() {
		return merRefundId;
	}
	public void setMerRefundId(String merRefundId) {
		this.merRefundId = merRefundId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}
	public String getChannelTransId() {
		return channelTransId;
	}
	public void setChannelTransId(String channelTransId) {
		this.channelTransId = channelTransId;
	}
	public String getChannelRefundId() {
		return channelRefundId;
	}
	public void setChannelRefundId(String channelRefundId) {
		this.channelRefundId = channelRefundId;
	}
	public String getRefundChannel() {
		return refundChannel;
	}
	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	public String getRefundState() {
		return refundState;
	}
	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCoreSubmitstate() {
		return coreSubmitstate;
	}
	public void setCoreSubmitstate(String coreSubmitstate) {
		this.coreSubmitstate = coreSubmitstate;
	}
	public String getCoreSn() {
		return coreSn;
	}
	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}
	public String getOrderCoreReturnCode() {
		return orderCoreReturnCode;
	}
	public void setOrderCoreReturnCode(String orderCoreReturnCode) {
		this.orderCoreReturnCode = orderCoreReturnCode;
	}
	public String getOrderCoreReturnMsg() {
		return orderCoreReturnMsg;
	}
	public void setOrderCoreReturnMsg(String orderCoreReturnMsg) {
		this.orderCoreReturnMsg = orderCoreReturnMsg;
	}
	public String getOrderCoreReturnTime() {
		return orderCoreReturnTime;
	}
	public void setOrderCoreReturnTime(String orderCoreReturnTime) {
		this.orderCoreReturnTime = orderCoreReturnTime;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
}
