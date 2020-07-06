package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;
import java.util.Date;

public class MerchantCashier implements Serializable{

	private static final long serialVersionUID = -7779227039149624034L;
		/**
	 * @desc			收银员列表
	 * @author 			
	 * @date			2017年6月12日上午8:52:28
	 * @version			v1.0
	 */
		/** id号 **/
	    private String onlyId;
	    /** 商户id **/
		private String mechantCustId;
		/** 收银员编号 **/
		private String shopId;
		/** 收银员客户编号 **/
		private String cashierCustId;
		/** 收银员手机号 **/
		private String cashierMobile;
		/** 收银员姓名 **/
		private String cashierName;
		/** 是否有退款操作权限 **/
		private String refundAuth;
		/** 是否有查询权限 **/
		private String queryAuth;
		
		/** 创建人 **/
		private String createId;
		/** 创建时间 **/
		private Date createTime;
		
		/** 修改人 **/
		private String modifyId;
		/** 修改时间 **/
		private String modifyTime;
		
		/** 银行账号 **/
		private String compMainAcct;
		
		/** 银行名称 **/
		private String compAcctBank;
		
		/** 支行名称 **/
		private String branchBank;

		public String getOnlyId() {
			return onlyId;
		}

		public void setOnlyId(String onlyId) {
			this.onlyId = onlyId;
		}

		public String getMechantCustId() {
			return mechantCustId;
		}

		public void setMechantCustId(String mechantCustId) {
			this.mechantCustId = mechantCustId;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getCashierCustId() {
			return cashierCustId;
		}

		public void setCashierCustId(String cashierCustId) {
			this.cashierCustId = cashierCustId;
		}

		public String getCashierMobile() {
			return cashierMobile;
		}

		public void setCashierMobile(String cashierMobile) {
			this.cashierMobile = cashierMobile;
		}

		public String getCashierName() {
			return cashierName;
		}

		public void setCashierName(String cashierName) {
			this.cashierName = cashierName;
		}

		public String getRefundAuth() {
			return refundAuth;
		}

		public void setRefundAuth(String refundAuth) {
			this.refundAuth = refundAuth;
		}

		public String getQueryAuth() {
			return queryAuth;
		}

		public void setQueryAuth(String queryAuth) {
			this.queryAuth = queryAuth;
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

		public String getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(String modifyTime) {
			this.modifyTime = modifyTime;
		}

		public String getCompMainAcct() {
			return compMainAcct;
		}

		public void setCompMainAcct(String compMainAcct) {
			this.compMainAcct = compMainAcct;
		}

		public String getCompAcctBank() {
			return compAcctBank;
		}

		public void setCompAcctBank(String compAcctBank) {
			this.compAcctBank = compAcctBank;
		}

		public String getBranchBank() {
			return branchBank;
		}

		public void setBranchBank(String branchBank) {
			this.branchBank = branchBank;
		}
		
		
		
}
