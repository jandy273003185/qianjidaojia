package com.qifenqian.bms.basemanager.merchant.bean;

import java.util.Date;

/**
 * @desc			微商户导出列表
 * @author 			tanchuan
 * @date			2017年3月25日上午11:52:28
 * @version			v1.0
 */
public class TinyMerchantExport {
	/** 商户二维码编号 **/
	private String merchantCode;
	/** 商户名称 **/
	private String custName;
	/** 证件号码/身份证号码 **/
	private String certifyNo;
	/** 客户类型：0-个人 1-企业 **/
	private char custType;
	/** 商户标志:0 商户，1 非商户,2 微商户 **/
	private char merchantFlag;
	/** 营业执照注册号 **/
	private String businessLicense;
	/** 客户状态：00 有效；01 待审核；02 注销；03 冻结；04 审核不通过 **/
	private char state;
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
	private char merchantState;

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

	public char getCustType() {
		return custType;
	}

	public void setCustType(char custType) {
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

	public char getState() {
		return state;
	}

	public void setState(char state) {
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

	public char getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(char merchantState) {
		this.merchantState = merchantState;
	}

}
