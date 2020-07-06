package com.qifenqian.bms.basemanager.photo.bean;

import java.io.Serializable;

public class CertificateAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6500709394504923189L;

	/** pk主键(审核流水id) */
	private int authId;

	/** 客户编号 */
	private String custId;

	private String custName;

	private String mobile;
	/**
	 * 实名认证状态: 0 审核通过 1 待审核 2 审核不通过 9 用户取消认证
	 */
	private String certificateState;
	/** 审核人工号 */
	private String certifyUser;
	/** 审核时间 */
	private String certifyDatetime;
	/** 审核结果代码 */
	private String authResultCode;
	/** 审核情况描述 */
	private String authResultDesc;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private String createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private String modifyTime;
	
	private String custType;
	/** 公安审核结果 */
	private String status;
	
	/** 公安审核意见 */
	private String rtnMsg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertificateState() {
		return certificateState;
	}

	public void setCertificateState(String certificateState) {
		this.certificateState = certificateState;
	}

	public String getCertifyUser() {
		return certifyUser;
	}

	public void setCertifyUser(String certifyUser) {
		this.certifyUser = certifyUser;
	}

	public String getCertifyDatetime() {
		return certifyDatetime;
	}

	public void setCertifyDatetime(String certifyDatetime) {
		this.certifyDatetime = certifyDatetime;
	}

	public String getAuthResultCode() {
		return authResultCode;
	}

	public void setAuthResultCode(String authResultCode) {
		this.authResultCode = authResultCode;
	}

	public String getAuthResultDesc() {
		return authResultDesc;
	}

	public void setAuthResultDesc(String authResultDesc) {
		this.authResultDesc = authResultDesc;
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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}
	
}
