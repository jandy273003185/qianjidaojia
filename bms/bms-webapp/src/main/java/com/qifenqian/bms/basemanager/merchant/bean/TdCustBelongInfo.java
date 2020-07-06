package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;

public class TdCustBelongInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9204903816317007611L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 客户经理Id
	 */
	private String managerId;
	/**
	 * 服务商custId
	 */
	private String servicePartnerId;
	/**
	 * 业务员Id
	 */
	private String salesmanId;
	/**
	 * 客户号
	 */
	private String custId;
	/**
	 * 商户状态
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改时间
	 */
	private String modiftyTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	public String getServicePartnerId() {
		return servicePartnerId;
	}
	public void setServicePartnerId(String servicePartnerId) {
		this.servicePartnerId = servicePartnerId;
	}
	public String getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModiftyTime() {
		return modiftyTime;
	}
	public void setModiftyTime(String modiftyTime) {
		this.modiftyTime = modiftyTime;
	}
	
	
	
}
