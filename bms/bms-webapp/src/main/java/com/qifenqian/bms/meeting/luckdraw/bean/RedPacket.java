package com.qifenqian.bms.meeting.luckdraw.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @project sevenpay-bms-web
 * @fileName RedPacket.java
 * @author huiquan.ma
 * @date 2015年12月17日
 * @memo 
 */
public class RedPacket implements Serializable{

	private static final long serialVersionUID = 935049340114697317L;

    /** 红包流水号*/
	private String redPacketSn;
    /** 收方客户编号，自动编号，规则：user+年+月+日+时+分+秒+8位随机数*/
	private String receiveCustId;
    /** 发方客户编号，自动编号，规则：user+年+月+日+时+分+秒+8位随机数*/
	private String giveCustId;
    /** 红包类型： REGISTER 注册红包，LOTTERY 抽奖红包*/
	private String redPacketType;
    /** 红包名称*/
	private String redPacketName;
    /** 红包金额*/
	private BigDecimal redPacketAmt;
    /** 红包状态： 0 未领取，1 已领取，2 已回收*/
	private String status;
    /** 备注*/
	private String memo;
    /** 关联id*/
	private String relationId;
    /** 创建时间*/
	private Date createTime;
    /** 修改时间*/
	private Date modifyTime;
	
	public String getRedPacketSn() {
		return redPacketSn;
	}
	public void setRedPacketSn(String redPacketSn) {
		this.redPacketSn = redPacketSn;
	}
	public String getReceiveCustId() {
		return receiveCustId;
	}
	public void setReceiveCustId(String receiveCustId) {
		this.receiveCustId = receiveCustId;
	}
	public String getGiveCustId() {
		return giveCustId;
	}
	public void setGiveCustId(String giveCustId) {
		this.giveCustId = giveCustId;
	}
	public String getRedPacketType() {
		return redPacketType;
	}
	public void setRedPacketType(String redPacketType) {
		this.redPacketType = redPacketType;
	}
	public String getRedPacketName() {
		return redPacketName;
	}
	public void setRedPacketName(String redPacketName) {
		this.redPacketName = redPacketName;
	}
	public BigDecimal getRedPacketAmt() {
		return redPacketAmt;
	}
	public void setRedPacketAmt(BigDecimal redPacketAmt) {
		this.redPacketAmt = redPacketAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}


