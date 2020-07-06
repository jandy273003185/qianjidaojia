package com.qifenqian.bms.sns.redpacketdetail.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class RedPacketDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1211951375791656799L;

	/** 红包ID */
	private String redEnvId;
	/** 客户编号 */
	private String custId;
	
	private String custName;
	
	/** 抢包时间 */
	private String pickTime;
	/** 抢到的金额 */
	private BigDecimal pickAmt;
	/** 祝福语 */
	private String greeting;
	/** 是否运气王：0：非；1：是 */
	private String isLucky;
	/** 是否已入账：0：非；1：是 */
	private String inOrderState;
	/** 入账订单号 */
	private String inOrderId;
	
	private String inSubmitTime;
	
	private String inAccountFailInfo;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private String createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private String modifyTime;

	private int pickCount;

	private String pickBeginTime;

	private String pickEndTime;

	public String getRedEnvId() {
		return redEnvId;
	}

	public void setRedEnvId(String redEnvId) {
		this.redEnvId = redEnvId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPickTime() {
		return pickTime;
	}

	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}

	public BigDecimal getPickAmt() {
		return pickAmt;
	}

	public void setPickAmt(BigDecimal pickAmt) {
		this.pickAmt = pickAmt;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getIsLucky() {
		return isLucky;
	}

	public void setIsLucky(String isLucky) {
		this.isLucky = isLucky;
	}

	public String getInOrderState() {
		return inOrderState;
	}

	public void setInOrderState(String inOrderState) {
		this.inOrderState = inOrderState;
	}

	public String getInOrderId() {
		return inOrderId;
	}

	public void setInOrderId(String inOrderId) {
		this.inOrderId = inOrderId;
	}

	public String getInSubmitTime() {
		return inSubmitTime;
	}

	public void setInSubmitTime(String inSubmitTime) {
		this.inSubmitTime = inSubmitTime;
	}

	public String getInAccountFailInfo() {
		return inAccountFailInfo;
	}

	public void setInAccountFailInfo(String inAccountFailInfo) {
		this.inAccountFailInfo = inAccountFailInfo;
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

	public int getPickCount() {
		return pickCount;
	}

	public void setPickCount(int pickCount) {
		this.pickCount = pickCount;
	}

	public String getPickBeginTime() {
		return pickBeginTime;
	}

	public void setPickBeginTime(String pickBeginTime) {
		this.pickBeginTime = pickBeginTime;
	}

	public String getPickEndTime() {
		return pickEndTime;
	}

	public void setPickEndTime(String pickEndTime) {
		this.pickEndTime = pickEndTime;
	}

}
