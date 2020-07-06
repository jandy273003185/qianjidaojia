package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean;

import java.io.Serializable;
import java.util.Date;

public class ExportOrderInfoBean implements Serializable{

	
	
	private String orderId;
	private String merchantCode;
	private String mchName;
	private String service;
	
	private String channel;
	
	private String channelSub;
	private String zxOrderId;
	private String channelOrderId;
	
	private String version;
	
	private String orderAmt;
	private String tradeAmt;
	private String settleAmt;
	private String couponAmt;
	private String prodDesc;
	private String payType;
	private String orderTimeStamp;
	private Integer orderTimeOut;
	private String orderExpire;
	
	private String signType;
	private String signMsg;
	
	private String orderState;
	private Date notifyTime;
	private String notifyCount;
	private String notifyResult;
	
	private Date createTime;
	private Date modifyTime;
	private String modifyId;
	private String payerContact;
	private String payerId;
	
	
	private Date finishTime;
	private Date refundTime;
	
	private String coreSubmitstate;
	private String coreSn;
	private String orderCoreReturnCode;
	private String orderCoreReturnMsg;
	private String orderCoreReturnTime;
	
	private String channelSubName;
	private String prodName;
	private static final long serialVersionUID = -1974101805090576902L;
	
	
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getChannelSubName() {
		return channelSubName;
	}
	public void setChannelSubName(String channelSubName) {
		this.channelSubName = channelSubName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getPayerContact() {
		return payerContact;
	}
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMchName() {
		return mchName;
	}
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChannelSub() {
		return channelSub;
	}
	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}
	public String getZxOrderId() {
		return zxOrderId;
	}
	public void setZxOrderId(String zxOrderId) {
		this.zxOrderId = zxOrderId;
	}
	public String getChannelOrderId() {
		return channelOrderId;
	}
	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public String getCouponAmt() {
		return couponAmt;
	}
	public void setCouponAmt(String couponAmt) {
		this.couponAmt = couponAmt;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderTimeStamp() {
		return orderTimeStamp;
	}
	public void setOrderTimeStamp(String orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}
	public Integer getOrderTimeOut() {
		return orderTimeOut;
	}
	public void setOrderTimeOut(Integer orderTimeOut) {
		this.orderTimeOut = orderTimeOut;
	}
	public String getOrderExpire() {
		return orderExpire;
	}
	public void setOrderExpire(String orderExpire) {
		this.orderExpire = orderExpire;
	}
	
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getNotifyCount() {
		return notifyCount;
	}
	public void setNotifyCount(String notifyCount) {
		this.notifyCount = notifyCount;
	}
	public String getNotifyResult() {
		return notifyResult;
	}
	public void setNotifyResult(String notifyResult) {
		this.notifyResult = notifyResult;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	
	
}
