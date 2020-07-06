package com.qifenqian.bms.merchant.subAccountOrder.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanggc 商户分账订单表
 *
 */
public class MerchantSubAccouontOrderBean implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	
	/** 
	 * 内部支付订单编号'
	*/
	private String orderId;
	/** 
	 * 内部分账订单号 随机串
	 * */
	private String settleId;
	
	/** 分账渠道  wx  alipay*/
	private String channel;
	
	/** 渠道分账订单号 分账后渠道返回*/
	private String channelSettleId;
	
	/** 分账状态  INITIAL-初始化 
	 *  FAIL-受理失败 
	 *   ACCEPTED—受理成功
	 *    PROCESSING—处理中 FINISHED—处理完成 CLOSED—处理失败，已关单  ' */
	private String status;
	
	/** 分账返回信息 */
	private String msg;
	/** 分账描述 */
	private String desc;
	/** 客户号 */
	private String queryCount;
	/** 入库时间' */
	private Date inputTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSettleId() {
		return settleId;
	}
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChannelSettleId() {
		return channelSettleId;
	}
	public void setChannelSettleId(String channelSettleId) {
		this.channelSettleId = channelSettleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(String queryCount) {
		this.queryCount = queryCount;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
}
