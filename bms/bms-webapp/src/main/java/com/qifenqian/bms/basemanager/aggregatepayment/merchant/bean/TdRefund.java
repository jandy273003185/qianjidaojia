package com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean;

import java.math.BigDecimal;
import java.util.Date;

public class TdRefund {

	/** 退款ID */
	private String refundId;
	/** 商户退款流水号 */
	private String merRefundId;
	/** 原订单ID */
	private String orderId;
	
	/** 商户编号 */
	private String mchId;
	
	/** 原订单总金额 */
	private BigDecimal totalAmt;
	/** 退款金额 */
	private BigDecimal refundAmt;
	/** 中信交易号 */
	private String channelTansId;
	/**中信退款ID*/
	private String channelRefundId;
	
	/** 退款渠道 */
	private String refundChannel;
	
	/**渠道返回退款错误码*/
	private String errCode;
	
	/**渠道退款返回结果描述*/
	private String errDesc;
	
	/**退款时间*/
	private Date refundTime;
	
	/**退款状态*/
	private String refundState;
	
	/**退款发起时间*/
	private Date createTime;
	
	/**退款人*/
	private String createId;

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

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
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

	public String getChannelTansId() {
		return channelTansId;
	}

	public void setChannelTansId(String channelTansId) {
		this.channelTansId = channelTansId;
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


	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
	
}
