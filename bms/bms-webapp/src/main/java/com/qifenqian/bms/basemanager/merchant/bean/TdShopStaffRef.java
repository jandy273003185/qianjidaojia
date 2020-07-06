package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;
import java.util.Date;

public class TdShopStaffRef implements Serializable {


	private static final long serialVersionUID = -6287989521897700114L;
	
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 门店ID
	 */
	private String shopId;

	/**
	 * 收银员编号
	 */
	private String cashierId;

	/**
	 * 店长编号
	 */
	private String shopmanagerId;

	/**
	 * 财务员编号
	 */
	private String financeId;

	/**
	 * 商户编号
	 */
	private String custId;

	/**
	 * 门店状态
	 */
	private String status;

	/**
	 * 创建人
	 */
	private String createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改人
	 */
	private String modifyId;

	/**
	 * 修改时间
	 */
	private Date modifyTime;


	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCashierId() {
		return cashierId;
	}

	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}

	public String getShopmanagerId() {
		return shopmanagerId;
	}

	public void setShopmanagerId(String shopmanagerId) {
		this.shopmanagerId = shopmanagerId;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
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
}
