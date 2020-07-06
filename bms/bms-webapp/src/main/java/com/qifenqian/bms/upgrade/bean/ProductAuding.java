package com.qifenqian.bms.upgrade.bean;

import java.io.Serializable;
import java.util.Date;

/***
 * 产品审核表
 * @author houmianmian
 *
 */
public class ProductAuding implements Serializable{

	private static final long serialVersionUID = 1L;
		/** 商户编号 **/
	    private String id;
	
		/** 商户编号 **/
	    private String custId;
	
		/** 商户编号 **/
	    private String merchantCode;
	    
		/** 产品代码 **/
		private String productCode;
		
		/** 签约状态: 00 已签约，01 审核中，08 待提交审核，09 未签约 **/
		private String signStatus;
		
		/** 签约费率 **/
		private String signRate;
		
		/**客户类型 : 0 商户，3 服务商 **/
		private String custType;
		
		/**商户名称 **/
		private String custName;
		
		/**注册方式 PC:PC端；H5:H5端；HT:后台**/
		private String registerWay;
		
		/**网站地址**/
		private String website;
		
		/**APP名称**/
		private String appName;
		
		/**APP下载地址**/
		private String appDownloadAddress;
		
		/**商户公众微信号**/
		private String qcPlay;
		
		/**公众服务号APPID**/
		private String qcAppId;
		
		/**推荐关注服务号**/
		private String serviceNumber;
		
		/** 创建时间 **/
		private Date createTime;
		
		/**注册地址**/
		private String representativeAddr;
		
		/**审核结果**/
		private String auditInfo;
		
		/**appId**/
		private String appId;
		
		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getAuditInfo() {
			return auditInfo;
		}

		public void setAuditInfo(String auditInfo) {
			this.auditInfo = auditInfo;
		}

		public String getCustId() {
			return custId;
		}

		public void setCustId(String custId) {
			this.custId = custId;
		}

		public String getRepresentativeAddr() {
			return representativeAddr;
		}

		public void setRepresentativeAddr(String representativeAddr) {
			this.representativeAddr = representativeAddr;
		}

		public String getMerchantCode() {
			return merchantCode;
		}

		public void setMerchantCode(String merchantCode) {
			this.merchantCode = merchantCode;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getSignStatus() {
			return signStatus;
		}

		public void setSignStatus(String signStatus) {
			this.signStatus = signStatus;
		}

		public String getSignRate() {
			return signRate;
		}

		public void setSignRate(String signRate) {
			this.signRate = signRate;
		}

		public String getCustType() {
			return custType;
		}

		public void setCustType(String custType) {
			this.custType = custType;
		}

		public String getCustName() {
			return custName;
		}

		public void setCustName(String custName) {
			this.custName = custName;
		}

		public String getRegisterWay() {
			return registerWay;
		}

		public void setRegisterWay(String registerWay) {
			this.registerWay = registerWay;
		}

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}

		public String getAppName() {
			return appName;
		}

		public void setAppName(String appName) {
			this.appName = appName;
		}

		public String getAppDownloadAddress() {
			return appDownloadAddress;
		}

		public void setAppDownloadAddress(String appDownloadAddress) {
			this.appDownloadAddress = appDownloadAddress;
		}

		public String getQcPlay() {
			return qcPlay;
		}

		public void setQcPlay(String qcPlay) {
			this.qcPlay = qcPlay;
		}

		public String getQcAppId() {
			return qcAppId;
		}

		public void setQcAppId(String qcAppId) {
			this.qcAppId = qcAppId;
		}

		public String getServiceNumber() {
			return serviceNumber;
		}

		public void setServiceNumber(String serviceNumber) {
			this.serviceNumber = serviceNumber;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
		
		
}
