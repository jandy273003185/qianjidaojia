package com.qifenqian.bms.upgrade.merchant.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 审核记录信息表
 * @author hongjiagui
 *
 */
public class TdAuditRecodeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4624879819849868259L;
	
	/**
	 * 审核编号
	 */
	private String id;
	/**
	 * 商户编号
	 */
	private String merchantCode;
	/**
	 * 审核状态: 00 通过，99 不通过
	 */
	private String status;
	/**
	 * 审核信息
	 */
	private String auditInfo;
	/**
	 * 审核类型 ：01 注册， 02 扫码 ，03 H5，04 APP ，05 公众号 ，06 小程序
	 */
	private String auditType;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核人
	 */
	private String userId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
