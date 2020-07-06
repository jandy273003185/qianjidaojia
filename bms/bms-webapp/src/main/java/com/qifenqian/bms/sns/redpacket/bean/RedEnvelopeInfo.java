package com.qifenqian.bms.sns.redpacket.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 红包汇总
 * @author shen
 *
 */
public class RedEnvelopeInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6479833606334911733L;
	/** 暂用UUID*/
	private String redEnvId;
    /** 红包的标题*/
	private String redEnvTitle;
    /** 红包总金额*/
	private BigDecimal redEnvAmt;
    /** 红包总个数*/
	private int redEnvCount;
    /** 红包种类：0：随机；1：固定金额*/
	private String redEnvType;
    /** 随机种子，是一个long型的大数，*/
	private long ranSeed;
    /** 有效期，24小时内有效*/
	private int validSeconds;
    /** 扣账状态，01：待提交；02 ：提交处理；03：处理失败；00 ：处理成功；99 取消；  90 未明*/
	private String orderState;
    /** 扣账订单号*/
	private String orderId;
    /** 已领个数*/
	private int pickCount;
    /** 过期后剩余金额*/
	private BigDecimal expiredLeftAmt;
    /** 过期状态，0：未过期；1：已过期*/
	private String expiredStatus;
    /** 过期后余款退款处理状态，01：未处理；02：处理失败；00：处理成功*/
	
	private String expiredBalSubmitTime;
	
	private String expiredBalProcOrderId;
	
	private String expiredBalProcStatus;
    /** 创建人*/
	private String createId;
    /** 创建时间*/
	private String createTime;
    /** 修改人*/
	private String modifyId;
    /** 修改时间*/
	private String modifyTime;
	
	private String redpacketTimeout;
	
	
	private String createBeginTime;
	
	private String createEndTime;
	
	public String getRedEnvId() {
		return redEnvId;
	}
	public void setRedEnvId(String redEnvId) {
		this.redEnvId = redEnvId;
	}
	public String getRedEnvTitle() {
		return redEnvTitle;
	}
	public void setRedEnvTitle(String redEnvTitle) {
		this.redEnvTitle = redEnvTitle;
	}
	public BigDecimal getRedEnvAmt() {
		return redEnvAmt;
	}
	public void setRedEnvAmt(BigDecimal redEnvAmt) {
		this.redEnvAmt = redEnvAmt;
	}
	public int getRedEnvCount() {
		return redEnvCount;
	}
	public void setRedEnvCount(int redEnvCount) {
		this.redEnvCount = redEnvCount;
	}
	public String getRedEnvType() {
		return redEnvType;
	}
	public void setRedEnvType(String redEnvType) {
		this.redEnvType = redEnvType;
	}
	public long getRanSeed() {
		return ranSeed;
	}
	public void setRanSeed(long ranSeed) {
		this.ranSeed = ranSeed;
	}
	public int getValidSeconds() {
		return validSeconds;
	}
	public void setValidSeconds(int validSeconds) {
		this.validSeconds = validSeconds;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getPickCount() {
		return pickCount;
	}
	public void setPickCount(int pickCount) {
		this.pickCount = pickCount;
	}
	public BigDecimal getExpiredLeftAmt() {
		return expiredLeftAmt;
	}
	public void setExpiredLeftAmt(BigDecimal expiredLeftAmt) {
		this.expiredLeftAmt = expiredLeftAmt;
	}
	public String getExpiredBalSubmitTime() {
		return expiredBalSubmitTime;
	}
	public void setExpiredBalSubmitTime(String expiredBalSubmitTime) {
		this.expiredBalSubmitTime = expiredBalSubmitTime;
	}
	public String getExpiredBalProcOrderId() {
		return expiredBalProcOrderId;
	}
	public void setExpiredBalProcOrderId(String expiredBalProcOrderId) {
		this.expiredBalProcOrderId = expiredBalProcOrderId;
	}
	public String getExpiredStatus() {
		return expiredStatus;
	}
	public void setExpiredStatus(String expiredStatus) {
		this.expiredStatus = expiredStatus;
	}
	public String getExpiredBalProcStatus() {
		return expiredBalProcStatus;
	}
	public void setExpiredBalProcStatus(String expiredBalProcStatus) {
		this.expiredBalProcStatus = expiredBalProcStatus;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRedpacketTimeout() {
		return redpacketTimeout;
	}
	public void setRedpacketTimeout(String redpacketTimeout) {
		this.redpacketTimeout = redpacketTimeout;
	}
	public String getCreateBeginTime() {
		return createBeginTime;
	}
	public void setCreateBeginTime(String createBeginTime) {
		this.createBeginTime = createBeginTime;
	}
	public String getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
