package com.qifenqian.bms.basemanager.feerule.bean;

import java.io.Serializable;

public class FeeRule implements Serializable{

	   /**
	 * 
	 */
	private static final long serialVersionUID = 5949290080608332825L;
	private String mappingId;//对应编号
	   private String  custId;//客户编号
	   private String  operType;//业务类型：充值RECHARGE/提现WITHDRAW/转账TRANSFER
	   private String feeCode;//费用代码
	   private String status;//状态：生效VALID/失效DISABLE
	   private String memo;//备注
	   private String instUser;//L写入人
	   private String instTime;//写入时间：YYYY-MM-DD HH:MI:SS
	   
	public String getMappingId() {
		return mappingId;
	}
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
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
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	public String getInstTime() {
		return instTime;
	}
	public void setInstTime(String instTime) {
		this.instTime = instTime;
	}
}
