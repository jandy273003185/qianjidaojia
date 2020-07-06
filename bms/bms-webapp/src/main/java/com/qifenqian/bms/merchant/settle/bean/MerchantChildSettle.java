package com.qifenqian.bms.merchant.settle.bean;

import java.util.Date;

public class MerchantChildSettle {

	/** 结算请求流水号 */
	private String orderId;
	/** 结算编号 */
	private String settleId;
	/** 商户编号 */
	private String custId;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private Date createTime;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String coreReturnCode;
	/** 核心返回信息 */
	private String coreReturnMsg;
	/** 核心返回时间 */
	private Date coreReturnTime;
	/** 状态 01 提交核心处理；02 核心返回异常；03核心处理成功；04 核心返回失败 */
	private String status;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private Date modifyTime;
	/** 操作类型：SETTLE_APPLY 结算申请，SETTLE_REVOKE 结算申请撤销,SETTLE_VERIFIED 结算核销 */
	private String operType;

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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public String getCoreReturnCode() {
		return coreReturnCode;
	}

	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}

	public String getCoreReturnMsg() {
		return coreReturnMsg;
	}

	public void setCoreReturnMsg(String coreReturnMsg) {
		this.coreReturnMsg = coreReturnMsg;
	}

	public Date getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(Date coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

}
