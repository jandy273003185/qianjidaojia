package com.qifenqian.bms.merchant.merchantReported.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class KFTCoBean implements Serializable{

	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8014619642253430904L;
	//
	private String channelNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//商户简称
	private String shortName;
	//营业执照号 
	private String businessLicense;
	//商户详细地址
	private String cprRegAddr;
	//商户地址省
	private String province;
	//商户地址市
	private String city;
	//商户地址区
	private String country;
	//法人姓名
	private String representativeName;
	//法人证件号
	private String certifyNo;
	//对公对私标识
	private String perEntFlag;
	//开户总行联行号
	private String bankCode;
	//开户支行号
	private String interBankCode;
	//开户支行名称
	private String interBankName;
	//预留电话
	private String mobile;
	//联系人名称
	private String attentionName;
	//联系人电话
	private String attentionMobile;
	//联系人邮箱
	private String attentionEmail;
	//商户登录邮箱号
	private String merchantLoginEmail;
	//商户登录手机号
	private String merchantLoginMobile;
	//客服号码
	private String customerPhone;
	//商户级别
	private String merchantLev;
	//企业代码类型
	private String merchantCodeType;
	//法人手机号
	private String representativePhone;
	//法人证件类型
	private String kftIdCardLegalType;
	//商户类型
	private String kftProfessionType;
	//结算周期
	private String kftCycleDays;
	//业务类型
	private String category;
	//结算账号名称
	private String accountNm;
	//结算账号
	private String accountNo;
	//开户行
	private String settleBankNo;
	//渠道返回商户号
	private String outMerchantCode;
	//营业执照路径
	private String licensePath;
	//身份证正面照
	private String identityImagePath0;
	//身份证反面照
	private String identityImagePath1;
	//开户许可证
	private String openPath;
	//银行卡照
	private String bankCardPath;
	//门头照
	private String doorPhotoPath;
	//店内照
	private String shopInteriorPath;
	//商户类别
	private String custType;
	//手持身份证照路径
	private String identityHandImagePath;
	//银行卡反面路径
	private String bankCardBackPath;
	//商户属性
	private String merchantAttribute;
	//报备批次号
	private String patchNo;
 	//业务场景说明
	private String businessScene;
	//备注说明
	private String remark;
	//身份证有效期
	private String certifyTermEnd;
	//营业执照有效期
	private String businessTermEnd;
	//费用类型
	private String feeType;
	//附加费率
	private String feeOfAttach;
	//百分比费率
	private String feeOfRate;
	//产品
	private String productId;
	//商户类型
	private String merchantProperty;
	//客户银行账户类型  个人 企业
	private String settleBankAcctType;
	
	private List<Map> feeList;
	
	/*private String feeList;
	
	
	public String getFeeList() {
		return feeList;
	}
	public void setFeeList(String feeList) {
		this.feeList = feeList;
	}*/
	public String getSettleBankAcctType() {
		return settleBankAcctType;
	}
	public void setSettleBankAcctType(String settleBankAcctType) {
		this.settleBankAcctType = settleBankAcctType;
	}
	public List<Map> getFeeList() {
		return feeList;
	}
	public void setFeeList(List<Map> feeList) {
		this.feeList = feeList;
	}
	public String getSettleBankNo() {
		return settleBankNo;
	}
	public void setSettleBankNo(String settleBankNo) {
		this.settleBankNo = settleBankNo;
	}
	public String getMerchantProperty() {
		return merchantProperty;
	}
	public void setMerchantProperty(String merchantProperty) {
		this.merchantProperty = merchantProperty;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFeeOfAttach() {
		return feeOfAttach;
	}
	public void setFeeOfAttach(String feeOfAttach) {
		this.feeOfAttach = feeOfAttach;
	}
	public String getFeeOfRate() {
		return feeOfRate;
	}
	public void setFeeOfRate(String feeOfRate) {
		this.feeOfRate = feeOfRate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getKftIdCardLegalType() {
		return kftIdCardLegalType;
	}
	public void setKftIdCardLegalType(String kftIdCardLegalType) {
		this.kftIdCardLegalType = kftIdCardLegalType;
	}
	public String getKftProfessionType() {
		return kftProfessionType;
	}
	public void setKftProfessionType(String kftProfessionType) {
		this.kftProfessionType = kftProfessionType;
	}
	public String getKftCycleDays() {
		return kftCycleDays;
	}
	public void setKftCycleDays(String kftCycleDays) {
		this.kftCycleDays = kftCycleDays;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCertifyTermEnd() {
		return certifyTermEnd;
	}
	public void setCertifyTermEnd(String certifyTermEnd) {
		this.certifyTermEnd = certifyTermEnd;
	}
	public String getBusinessTermEnd() {
		return businessTermEnd;
	}
	public void setBusinessTermEnd(String businessTermEnd) {
		this.businessTermEnd = businessTermEnd;
	}
	public String getBusinessScene() {
		return businessScene;
	}
	public void setBusinessScene(String businessScene) {
		this.businessScene = businessScene;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPatchNo() {
		return patchNo;
	}
	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}
	public String getMerchantAttribute() {
		return merchantAttribute;
	}
	public void setMerchantAttribute(String merchantAttribute) {
		this.merchantAttribute = merchantAttribute;
	}
	public String getBankCardBackPath() {
		return bankCardBackPath;
	}
	public void setBankCardBackPath(String bankCardBackPath) {
		this.bankCardBackPath = bankCardBackPath;
	}
	public String getIdentityHandImagePath() {
		return identityHandImagePath;
	}
	public void setIdentityHandImagePath(String identityHandImagePath) {
		this.identityHandImagePath = identityHandImagePath;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getLicensePath() {
		return licensePath;
	}
	public void setLicensePath(String licensePath) {
		this.licensePath = licensePath;
	}
	public String getIdentityImagePath0() {
		return identityImagePath0;
	}
	public void setIdentityImagePath0(String identityImagePath0) {
		this.identityImagePath0 = identityImagePath0;
	}
	public String getIdentityImagePath1() {
		return identityImagePath1;
	}
	public void setIdentityImagePath1(String identityImagePath1) {
		this.identityImagePath1 = identityImagePath1;
	}
	public String getOpenPath() {
		return openPath;
	}
	public void setOpenPath(String openPath) {
		this.openPath = openPath;
	}
	public String getBankCardPath() {
		return bankCardPath;
	}
	public void setBankCardPath(String bankCardPath) {
		this.bankCardPath = bankCardPath;
	}
	public String getDoorPhotoPath() {
		return doorPhotoPath;
	}
	public void setDoorPhotoPath(String doorPhotoPath) {
		this.doorPhotoPath = doorPhotoPath;
	}
	public String getShopInteriorPath() {
		return shopInteriorPath;
	}
	public void setShopInteriorPath(String shopInteriorPath) {
		this.shopInteriorPath = shopInteriorPath;
	}
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
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
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCertifyNo() {
		return certifyNo;
	}
	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}
	public String getPerEntFlag() {
		return perEntFlag;
	}
	public void setPerEntFlag(String perEntFlag) {
		this.perEntFlag = perEntFlag;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getInterBankCode() {
		return interBankCode;
	}
	public void setInterBankCode(String interBankCode) {
		this.interBankCode = interBankCode;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAttentionName() {
		return attentionName;
	}
	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}
	public String getAttentionMobile() {
		return attentionMobile;
	}
	public void setAttentionMobile(String attentionMobile) {
		this.attentionMobile = attentionMobile;
	}
	public String getAttentionEmail() {
		return attentionEmail;
	}
	public void setAttentionEmail(String attentionEmail) {
		this.attentionEmail = attentionEmail;
	}
	public String getMerchantLoginEmail() {
		return merchantLoginEmail;
	}
	public void setMerchantLoginEmail(String merchantLoginEmail) {
		this.merchantLoginEmail = merchantLoginEmail;
	}
	public String getMerchantLoginMobile() {
		return merchantLoginMobile;
	}
	public void setMerchantLoginMobile(String merchantLoginMobile) {
		this.merchantLoginMobile = merchantLoginMobile;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getMerchantLev() {
		return merchantLev;
	}
	public void setMerchantLev(String merchantLev) {
		this.merchantLev = merchantLev;
	}
	public String getMerchantCodeType() {
		return merchantCodeType;
	}
	public void setMerchantCodeType(String merchantCodeType) {
		this.merchantCodeType = merchantCodeType;
	}
	public String getRepresentativePhone() {
		return representativePhone;
	}
	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}
	public String getAccountNm() {
		return accountNm;
	}
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	
	
	
	
	
	
	
	
}