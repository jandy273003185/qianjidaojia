package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BussOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 969934418819319675L;

	/** 订单索引号 */
	private int orderId;

	/** 订单编号 */
	private String orderSn;

	/** 订单标记PRECHARGE话费充值 */
	private SevenmallRequestValues.SevenmallAppCode orderMark;

	/** 卖家店铺id */
	private int storeId;

	/** 卖家店铺名称 */
	private String storeName;

	/** 买家id */
	private String buyerId;

	/** 买家姓名 */
	private String buyerName;

	/** 买家电子邮箱 */
	private String buyerEmail;

	/** 商品总价格 */
	private BigDecimal goodsAmount;

	/** 订单总价格 */
	private BigDecimal orderAmount;

	/** 币别 */
	private SevenmallRequestValues.SevenmallCurrCode currCode;

	/** WEB/MOBILE */
	private SevenmallRequestValues.SevenmallTerminalType orderFrom;

	/** 订单生成日期 */
	private String instDate;

	/** 订单生成时间 */
	private Date instDatetime;

	/** 支付方式代码（SEVENPAY/ALIPAY/UNIONPAY） */
	private SevenmallRequestValues.SevenmallPaymentWay paymentCode;

	/** 支付单号 */
	private String payId;

	/** 支付类型:DIRECT是即时到帐,GUARANTEE是担保交易 */
	private SevenmallRequestValues.SevenmallOrderTradeType payType;

	/** 支付时间 */
	private Date payDatetime;

	/** 支付附言 */
	private String payMessage;

	/** 外接渠道代码（例如WANHUI万汇通/YINTONG中银通编号/电信...） */
	private SevenmallRequestValues.SevenmallOutSystemCode outCode;

	/** 外接编号（例如万汇通、中银通编号） */
	private String outId;

	/** 订单留言 */
	private String orderMessage;

	/** 订单状态：INIT待付款/PAID已付款/DELIVERED已发货/RECEIVED已收货/FINISHED已完成/CANCELLED已取消 */
	private SevenmallRequestValues.SevenmallOrderStatus orderState;

	/** 订单完成时间 */
	private Date finnshedDatetime;

	/** 退款状态:NO无退款/PART部分退款/ALL全部退款 */
	private SevenmallRequestValues.SevenmallRefundState refundState;

	/** 退货状态:NO无退货/PART部分退货/ALL全部退货 */
	private SevenmallRequestValues.SevenmallReturnState returnState;

	/** 退款金额 */
	private BigDecimal refundAmount;

	/** 锁定状态:N正常/Y锁定 */
	private String lockState;

	/** 物流单号 */
	private String shippingCode;

	/** 更新时间 */
	private Date updateDatetime;
	
	/** 当前状态*/
	private SevenmallRequestValues.SevenmallOrderStatus currOrderState;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public SevenmallRequestValues.SevenmallAppCode getOrderMark() {
		return orderMark;
	}

	public void setOrderMark(SevenmallRequestValues.SevenmallAppCode orderMark) {
		this.orderMark = orderMark;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public SevenmallRequestValues.SevenmallCurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(SevenmallRequestValues.SevenmallCurrCode currCode) {
		this.currCode = currCode;
	}

	public SevenmallRequestValues.SevenmallTerminalType getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(SevenmallRequestValues.SevenmallTerminalType orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public SevenmallRequestValues.SevenmallPaymentWay getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(
			SevenmallRequestValues.SevenmallPaymentWay paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public SevenmallRequestValues.SevenmallOrderTradeType getPayType() {
		return payType;
	}

	public void setPayType(SevenmallRequestValues.SevenmallOrderTradeType payType) {
		this.payType = payType;
	}

	public Date getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(Date payDatetime) {
		this.payDatetime = payDatetime;
	}

	public String getPayMessage() {
		return payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}

	public SevenmallRequestValues.SevenmallOutSystemCode getOutCode() {
		return outCode;
	}

	public void setOutCode(SevenmallRequestValues.SevenmallOutSystemCode outCode) {
		this.outCode = outCode;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public SevenmallRequestValues.SevenmallOrderStatus getOrderState() {
		return orderState;
	}

	public void setOrderState(SevenmallRequestValues.SevenmallOrderStatus orderState) {
		this.orderState = orderState;
	}

	public Date getFinnshedDatetime() {
		return finnshedDatetime;
	}

	public void setFinnshedDatetime(Date finnshedDatetime) {
		this.finnshedDatetime = finnshedDatetime;
	}

	public SevenmallRequestValues.SevenmallRefundState getRefundState() {
		return refundState;
	}

	public void setRefundState(
			SevenmallRequestValues.SevenmallRefundState refundState) {
		this.refundState = refundState;
	}

	public SevenmallRequestValues.SevenmallReturnState getReturnState() {
		return returnState;
	}

	public void setReturnState(
			SevenmallRequestValues.SevenmallReturnState returnState) {
		this.returnState = returnState;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getLockState() {
		return lockState;
	}

	public void setLockState(String lockState) {
		this.lockState = lockState;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public SevenmallRequestValues.SevenmallOrderStatus getCurrOrderState() {
		return currOrderState;
	}

	public void setCurrOrderState(SevenmallRequestValues.SevenmallOrderStatus currOrderState) {
		this.currOrderState = currOrderState;
	}
	
}
