package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;
import java.util.Date;


public class AgencyAuding implements Serializable{
	
	private static final long serialVersionUID = 553488405815709053L;
		/**
	 * @desc			代理商导出列表
	 * @author 			linhui
	 * @date			2017年6月12日上午8:52:28
	 * @version			v1.0
	 */
		/** 商户二维码编号 **/
	    private String custId;
		private String merchantCode;
		/** 商户名称 **/
		private String custName;
		/** 证件号码/身份证号码 **/
		private String certifyNo;
		/** 客户类型：0-个人 1-企业 **/
		private String custType;
		/** 商户标志:0 商户，1 非商户,2 微商户 **/
		private char merchantFlag;
		/** 营业执照注册号 **/
		private String businessLicense;
		/** 客户状态：00 有效；01 待审核；02 注销；03 冻结；04 审核不通过 **/
		private String state;
		/** 七分钱账户ID **/
		private String qfqAccId;
		/** 创建人 **/
		private String createId;
		/** 创建时间 **/
		private Date createTime;
		/** 修改人 **/
		private String modifyId;
		/** 修改时间 **/
		private Date modifyTime;
		/** 法人手机/商户手机号码 **/
		private String representativeMobile;
		/** 往来单位编号 **/
		private String fcontactunitNumber;
		/** 电子邮件 **/
		private String email;
		/** 状态：00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中 **/
		private String merchantState;
        
		private String bankacctName;//银行开户名
		private String mobile;//手机号
		private String roleid;//手机号
		
		private String historyState;
		
		private String agentRate;
		
		private Integer countNum;
		/**
		 * 代理人真实姓名
		 */
		private String agentName;
		
		public String getRoleid() {
			return roleid;
		}

		public void setRoleid(String roleid) {
			this.roleid = roleid;
		}

		private String authId;
		private String compmainAcct;//对公账户
		private String columnValue;
		
		private String  agencyState;
		
		private String auditState;
		
		
		
		public String getMerchantCode() {
			return merchantCode;
		}

		public void setMerchantCode(String merchantCode) {
			this.merchantCode = merchantCode;
		}

		public String getCustName() {
			return custName;
		}

		public void setCustName(String custName) {
			this.custName = custName;
		}

		public String getCertifyNo() {
			return certifyNo;
		}

		public void setCertifyNo(String certifyNo) {
			this.certifyNo = certifyNo;
		}

		public String getCustType() {
			return custType;
		}

		public void setCustType(String custType) {
			this.custType = custType;
		}

		public char getMerchantFlag() {
			return merchantFlag;
		}

		public void setMerchantFlag(char merchantFlag) {
			this.merchantFlag = merchantFlag;
		}

		public String getBusinessLicense() {
			return businessLicense;
		}

		public void setBusinessLicense(String businessLicense) {
			this.businessLicense = businessLicense;
		}


		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getQfqAccId() {
			return qfqAccId;
		}

		public void setQfqAccId(String qfqAccId) {
			this.qfqAccId = qfqAccId;
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

		public String getRepresentativeMobile() {
			return representativeMobile;
		}

		public void setRepresentativeMobile(String representativeMobile) {
			this.representativeMobile = representativeMobile;
		}

		public String getFcontactunitNumber() {
			return fcontactunitNumber;
		}

		public void setFcontactunitNumber(String fcontactunitNumber) {
			this.fcontactunitNumber = fcontactunitNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
		public String getMerchantState() {
			return merchantState;
		}

		public void setMerchantState(String merchantState) {
			this.merchantState = merchantState;
		}

		public String getBankacctName() {
			return bankacctName;
		}

		public void setBankacctName(String bankacctName) {
			this.bankacctName = bankacctName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getCompmainAcct() {
			return compmainAcct;
		}

		public void setCompmainAcct(String compmainAcct) {
			this.compmainAcct = compmainAcct;
		}

		public String getColumnValue() {
			return columnValue;
		}

		public void setColumnValue(String columnValue) {
			this.columnValue = columnValue;
		}

		public String getCustId() {
			return custId;
		}

		public void setCustId(String custId) {
			this.custId = custId;
		}

		public String getAuthId() {
			return authId;
		}

		public void setAuthId(String authId) {
			this.authId = authId;
		}

		public String getAgencyState() {
			return agencyState;
		}

		public void setAgencyState(String agencyState) {
			this.agencyState = agencyState;
		}

		public String getAuditState() {
			return auditState;
		}

		public void setAuditState(String auditState) {
			this.auditState = auditState;
		}

		public String getHistoryState() {
			return historyState;
		}

		public void setHistoryState(String historyState) {
			this.historyState = historyState;
		}

		public String getAgentRate() {
			return agentRate;
		}

		public void setAgentRate(String agentRate) {
			this.agentRate = agentRate;
		}

		public Integer getCountNum() {
			return countNum;
		}

		public void setCountNum(Integer countNum) {
			this.countNum = countNum;
		}

		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

}
