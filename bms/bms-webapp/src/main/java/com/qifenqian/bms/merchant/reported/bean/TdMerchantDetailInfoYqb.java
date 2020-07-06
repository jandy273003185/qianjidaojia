package com.qifenqian.bms.merchant.reported.bean;

import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 平安付报备Bean
 * @date 2020/5/6 18:41
 */
public class TdMerchantDetailInfoYqb extends TdMerchantReportDetail implements Serializable {
    /**
     * 报备批次号
     */
    private String patchNo;
    /**
     * 商户号
     */
    private String merchantCode;
    /**
     * 商户名称
     */
    private String custName;
    /**
     * 版本号
     */
    private String version;
    /**
     * 请求流水号
     */
    private String reqSerialNo;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 服务商编号；平安付分配
     */
    private String channelNo;
    /**
     * 商户类型：10-企业 11-个体工商户 12-个人
     */
    private String merchantType;
    /**
     * 营业执照号
     */
    private String businessLicenseNo;
    /**
     * 法人姓名
     */
    private String legalRepresent;
    /**
     * 营业执照照片路径
     */
    private String businessLicensePath;
    /**
     * 营业执照生效日
     */
    private String businessLicenseEffDate;
    /**
     * 营业执照到期日
     */
    private String businessLicenseValDate;
    /**
     * 费率
     */
    private String feeRate;
    /**
     * 成立日期
     */
    private String establishDate;
    /**
     * 商户简称
     */
    private String merchantShortName;
    /**
     * 法人身份证号
     */
    private String identityNo;
    /**
     * 法人身份证正面照路径
     */
    private String identityPosPath;
    /**
     * 法人身份证反面照路径
     */
    private String identityNegPath;
    /**
     * 法人身份证有效期开始日
     */
    private String identityEffDate;
    /**
     * 法人身份证有效期到期日
     */
    private String identityValDate;
    /**
     * 行业编码
     */
    private String industryCode;
    /**
     * 省代码
     */
    private String provinceCode;
    /**
     * 市代码
     */
    private String cityCode;
    /**
     * 区县代码
     */
    private String countyCode;
    /**
     * 商户营业地址
     */
    private String businessAddress;
    /**
     * 店铺门面照
     */
    private String storeFacadePath;
    /**
     * 商户经营场所或仓库照
     */
    private String storeBussinessPlacePath;
    /**
     * 经营者手持身份证拍照图片
     */
    private String storeWithOwnerPath;
    /**
     * 特殊行业资质图片
     */
    private String specialIndustryPaths;
    /**
     * 其他材料图片
     */
    private String otherMaterialPaths;
    /**
     * 电子签名照
     */
    private String electronicSignPath;
    /**
     * 结算账户名称
     */
    private String bankAccountName;
    /**
     * 结算账户账号
     */
    private String bankAccountNo;
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 支行名称
     */
    private String branchBankName;
    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 联行号
     */
    private String subBankCode;
    /**
     * 结算卡照片
     */
    private String bankCardPath;
    /**
     * 银行卡预留手机号
     */
    private String openBankPhoneNo;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 管理员手机号
     */
    private String adminCellPhoneNo;
    /**
     * 进件结果回调地址
     */
    private String callBackUrl;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;

    @Override
    public String getPatchNo() {
        return patchNo;
    }

    @Override
    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String getChannelNo() {
        return channelNo;
    }

    @Override
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getLegalRepresent() {
        return legalRepresent;
    }

    public void setLegalRepresent(String legalRepresent) {
        this.legalRepresent = legalRepresent;
    }

    public String getBusinessLicensePath() {
        return businessLicensePath;
    }

    public void setBusinessLicensePath(String businessLicensePath) {
        this.businessLicensePath = businessLicensePath;
    }

    public String getBusinessLicenseEffDate() {
        return businessLicenseEffDate;
    }

    public void setBusinessLicenseEffDate(String businessLicenseEffDate) {
        this.businessLicenseEffDate = businessLicenseEffDate;
    }

    public String getBusinessLicenseValDate() {
        return businessLicenseValDate;
    }

    public void setBusinessLicenseValDate(String businessLicenseValDate) {
        this.businessLicenseValDate = businessLicenseValDate;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getMerchantShortName() {
        return merchantShortName;
    }

    public void setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getIdentityPosPath() {
        return identityPosPath;
    }

    public void setIdentityPosPath(String identityPosPath) {
        this.identityPosPath = identityPosPath;
    }

    public String getIdentityNegPath() {
        return identityNegPath;
    }

    public void setIdentityNegPath(String identityNegPath) {
        this.identityNegPath = identityNegPath;
    }

    public String getIdentityEffDate() {
        return identityEffDate;
    }

    public void setIdentityEffDate(String identityEffDate) {
        this.identityEffDate = identityEffDate;
    }

    public String getIdentityValDate() {
        return identityValDate;
    }

    public void setIdentityValDate(String identityValDate) {
        this.identityValDate = identityValDate;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getStoreFacadePath() {
        return storeFacadePath;
    }

    public void setStoreFacadePath(String storeFacadePath) {
        this.storeFacadePath = storeFacadePath;
    }

    public String getStoreBussinessPlacePath() {
        return storeBussinessPlacePath;
    }

    public void setStoreBussinessPlacePath(String storeBussinessPlacePath) {
        this.storeBussinessPlacePath = storeBussinessPlacePath;
    }

    public String getStoreWithOwnerPath() {
        return storeWithOwnerPath;
    }

    public void setStoreWithOwnerPath(String storeWithOwnerPath) {
        this.storeWithOwnerPath = storeWithOwnerPath;
    }

    public String getSpecialIndustryPaths() {
        return specialIndustryPaths;
    }

    public void setSpecialIndustryPaths(String specialIndustryPaths) {
        this.specialIndustryPaths = specialIndustryPaths;
    }

    public String getOtherMaterialPaths() {
        return otherMaterialPaths;
    }

    public void setOtherMaterialPaths(String otherMaterialPaths) {
        this.otherMaterialPaths = otherMaterialPaths;
    }

    public String getElectronicSignPath() {
        return electronicSignPath;
    }

    public void setElectronicSignPath(String electronicSignPath) {
        this.electronicSignPath = electronicSignPath;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSubBankCode() {
        return subBankCode;
    }

    public void setSubBankCode(String subBankCode) {
        this.subBankCode = subBankCode;
    }

    public String getBankCardPath() {
        return bankCardPath;
    }

    public void setBankCardPath(String bankCardPath) {
        this.bankCardPath = bankCardPath;
    }

    public String getOpenBankPhoneNo() {
        return openBankPhoneNo;
    }

    public void setOpenBankPhoneNo(String openBankPhoneNo) {
        this.openBankPhoneNo = openBankPhoneNo;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminCellPhoneNo() {
        return adminCellPhoneNo;
    }

    public void setAdminCellPhoneNo(String adminCellPhoneNo) {
        this.adminCellPhoneNo = adminCellPhoneNo;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
