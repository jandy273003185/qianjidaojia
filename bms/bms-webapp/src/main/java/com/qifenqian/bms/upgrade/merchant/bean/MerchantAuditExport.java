package com.qifenqian.bms.upgrade.merchant.bean;

import java.io.Serializable;
/**
 * 商户审核列表导出报表辅助Bean
 * @author hongjiagui
 *
 */
public class MerchantAuditExport implements Serializable{



	/**
	 * 商户编号
	 */
	private String merchantCode;
	
	/**
	 * 邮箱账号
	 */
	private String email;
	/**
	 * 手机帐号
	 */
	private String mobile;
	/**
	 * 商户名称
	 */
	private String custName;
	/**
	 * 注册时间
	 */
	private String createTime;
	/**
	 * 银行开户名
	 */
	private String bankAcctName;
	/**
	 * 商户状态
	 */
	private String merchantState;
	/**
	 * 审核状态
	 */
	private String auditState;
	/**
	 * 审核信息
	 */
	private String aduitMessage;
	/**
	 * 审核人
	 */
	private String aduitUserName;
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBankAcctName() {
		return bankAcctName;
	}
	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}
	public String getMerchantState() {
		return merchantState;
	}
	public void setMerchantState(String merchantState) {
		/*00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中； 
		05 前台 商户协议待录入； 06 后台商户协议待录入;07 审核不通过'*/
		if("00".equals(merchantState)) {
			this.merchantState = "正常";
		}else if("01".equals(merchantState)) {
			this.merchantState = "停用";
		}else if("02".equals(merchantState)) {
			this.merchantState = "登录账户冻结";
		}else if("03".equals(merchantState)) {
			this.merchantState = "注册待激活";
		}else if("04".equals(merchantState)) {
			this.merchantState = "商户审核中";
		}else if("05".equals(merchantState)) {
			this.merchantState = "前台商户协议待录入";
		}else if("06".equals(merchantState)) {
			this.merchantState = "后台商户协议待录入";
		}else if("07".equals(merchantState)) {
			this.merchantState = "审核不通过";
		}else {
			this.merchantState = merchantState;
		}
		
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		//00 有效；01 待审核；02 注销；03 冻结；04 审核不通过
		if("00".equals(auditState)) {
			this.auditState = "有效";
		}else if("01".equals(auditState)) {
			this.auditState = "待审核";
		}else if("02".equals(auditState)) {
			this.auditState = "注销";
		}else if("03".equals(auditState)) {
			this.auditState = "冻结";
		}else if("04".equals(auditState)) {
			this.auditState = "审核不通过";
		}else {
			this.auditState = auditState;
		}
		 
	}
	public String getAduitMessage() {
		return aduitMessage;
	}
	public void setAduitMessage(String aduitMessage) {
		this.aduitMessage = aduitMessage;
	}
	public String getAduitUserName() {
		return aduitUserName;
	}
	public void setAduitUserName(String aduitUserName) {
		this.aduitUserName = aduitUserName;
	}
	
}
