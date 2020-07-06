package com.qifenqian.bms.accounting.withdraw.bean;

import java.io.Serializable;
import java.util.Date;

public class WithdrawChild implements Serializable{

	private static final long serialVersionUID = 8477375719055389889L;
	
	/**
	 * 提现流水号
	 */
	private String withdrawSn;
	/**
	 * 提现请求流水号
	 */
	private String withdrawReqserialid;
	/**
	 * 客户编号
	 */
	private String custId;
	
	/**
	 * 核心流水号
	 */
	private String coreSn;
	/**
	 * 核心返回码
	 */
	private String coreReturnCode;
	/**
	 * 核心返回信息
	 */
	private String coreReturnMsg;
	
	/**
	 * 操作类型  withdraw 提现，revoke 撤销
	 */
	private String operType;
	/**
	 * 创建人iD
	 */
	private String createId;
	/**
	 * 创建时间
	 */
	private Date   createTime;
	/**
	 * 修改人
	 */
	private String modifyId;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	public String getWithdrawSn() {
		return withdrawSn;
	}
	public void setWithdrawSn(String withdrawSn) {
		this.withdrawSn = withdrawSn;
	}
	public String getWithdrawReqserialid() {
		return withdrawReqserialid;
	}
	public void setWithdrawReqserialid(String withdrawReqserialid) {
		this.withdrawReqserialid = withdrawReqserialid;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
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
	public String getCoreReturnMsg() {
		return coreReturnMsg;
	}
	public void setCoreReturnMsg(String coreReturnMsg) {
		this.coreReturnMsg = coreReturnMsg;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	
	
	
	

}
