package com.qifenqian.bms.basemanager.agency.bean;


public class AgencyExport {
	/**
	 * @desc			代理商导出列表
	 * @author 			linhui
	 * @date			2017年6月12日上午8:52:28
	 * @version			v1.0
	 */
		/** 商户二维码编号 **/
		private String merchantCode;
		
		private String email;
		/** 商户名称 **/
		private String custName;
		
		private String agentRate;
		
		private String bankCardName;
		
		private String bankCardNo;
		
		private String bankName;
		
		private String custManager;
		
		private String protocolState;
		
		/** 创建时间 **/
		private String createTime;
		
		/** 状态：00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中 **/
		private String merchantState;
		
		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getMerchantState() {
			return merchantState;
		}

		public void setMerchantState(String merchantState) {
			this.merchantState = merchantState;
		}

		public String getAgentRate() {
			return agentRate;
		}

		public void setAgentRate(String agentRate) {
			this.agentRate = agentRate;
		}

		public String getBankCardName() {
			return bankCardName;
		}

		public void setBankCardName(String bankCardName) {
			this.bankCardName = bankCardName;
		}

		public String getBankCardNo() {
			return bankCardNo;
		}

		public void setBankCardNo(String bankCardNo) {
			this.bankCardNo = bankCardNo;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getCustManager() {
			return custManager;
		}

		public void setCustManager(String custManager) {
			this.custManager = custManager;
		}

		public String getProtocolState() {
			return protocolState;
		}

		public void setProtocolState(String protocolState) {
			this.protocolState = protocolState;
		}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

}
