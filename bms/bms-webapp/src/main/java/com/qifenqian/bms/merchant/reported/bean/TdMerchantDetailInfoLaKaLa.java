package com.qifenqian.bms.merchant.reported.bean;

import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;

import java.io.Serializable;

public class TdMerchantDetailInfoLaKaLa extends TdMerchantReportDetail implements Serializable {


    private static final long serialVersionUID = -1180181854571599988L;
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
     * 渠道类型
     */
    private String channelType;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 商户经营名称
     */
    private String bizName;

    /**
     * 营业执照号
     */
    private String merLicenseNo;

    /**
     * 营业执照有效期
     */
    private String linceseExpire;

    /**
     * 商户注册地址省代码
     */
    private String provinceCode;

    /**
     * 商户注册地址市代码
     */
    private String cityCode;

    /**
     * 商户注册地址区代码
     */
    private String countyCode;

    /**
     * 商户详细地址
     */
    private String address;

    /**
     * 商户经营内容
     */
    private String bizContent;

    /**
     * 商户法人姓名
     */
    private String crLicenceName;

    /**
     * 商户法人身份证号码
     */
    private String crLicenceNo;

    /**
     * 商户法人身份证有效期
     */
    private String idCardExpire;

    /**
     * 联系人手机号码
     */
    private String contactMobile;

    /**
     * 开户行号
     */
    private String openningBankNo;

    /**
     * 开户支行名称
     */
    private String openningBankName;

    /**
     * 清算行号
     */
    private String clearingBankNo;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户性质
     */
    private String accountKind;

    /**
     * 入账人身份证号码
     */
    private String idCard;

    /**
     * 结算周期
     */
    private String settlePeriod;

    /**
     * 行业代码
     */
    private String mccCode;

    /**
     * 借记卡手续费
     */
    private String debitRate;

    /**
     * 扫码类型
     */
    private String wechatType;

    /**
     * 扫码手续费
     */
    private String wechatRate;

    /**
     * 终端数量
     */
    private String termNum;

    /**
     * 通知地址
     */
    private String retUrl;

    /**
     * 身份证正面
     */
    private String idCardFront;

    /**
     * 身份证反面
     */
    private String idCardBehind;
    /**
     * 银行卡
     */
    private String bankCard;
    /**
     * 营业执照
     */
    private String businessLicence;
    /**
     * 合影照片
     */
    private String personalPhoto;
    /**
     * 商户照片
     */
    private String merchantPhoto;
    /**
     * 其它附件
     */
    private String others;
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

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getMerLicenseNo() {
        return merLicenseNo;
    }

    public void setMerLicenseNo(String merLicenseNo) {
        this.merLicenseNo = merLicenseNo;
    }

    public String getLinceseExpire() {
        return linceseExpire;
    }

    public void setLinceseExpire(String linceseExpire) {
        this.linceseExpire = linceseExpire;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getCrLicenceName() {
        return crLicenceName;
    }

    public void setCrLicenceName(String crLicenceName) {
        this.crLicenceName = crLicenceName;
    }

    public String getCrLicenceNo() {
        return crLicenceNo;
    }

    public void setCrLicenceNo(String crLicenceNo) {
        this.crLicenceNo = crLicenceNo;
    }

    public String getIdCardExpire() {
        return idCardExpire;
    }

    public void setIdCardExpire(String idCardExpire) {
        this.idCardExpire = idCardExpire;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getOpenningBankNo() {
        return openningBankNo;
    }

    public void setOpenningBankNo(String openningBankNo) {
        this.openningBankNo = openningBankNo;
    }

    public String getOpenningBankName() {
        return openningBankName;
    }

    public void setOpenningBankName(String openningBankName) {
        this.openningBankName = openningBankName;
    }

    public String getClearingBankNo() {
        return clearingBankNo;
    }

    public void setClearingBankNo(String clearingBankNo) {
        this.clearingBankNo = clearingBankNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKind() {
        return accountKind;
    }

    public void setAccountKind(String accountKind) {
        this.accountKind = accountKind;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSettlePeriod() {
        return settlePeriod;
    }

    public void setSettlePeriod(String settlePeriod) {
        this.settlePeriod = settlePeriod;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public String getDebitRate() {
        return debitRate;
    }

    public void setDebitRate(String debitRate) {
        this.debitRate = debitRate;
    }

    public String getWechatType() {
        return wechatType;
    }

    public void setWechatType(String wechatType) {
        this.wechatType = wechatType;
    }

    public String getWechatRate() {
        return wechatRate;
    }

    public void setWechatRate(String wechatRate) {
        this.wechatRate = wechatRate;
    }

    public String getTermNum() {
        return termNum;
    }

    public void setTermNum(String termNum) {
        this.termNum = termNum;
    }

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBehind() {
        return idCardBehind;
    }

    public void setIdCardBehind(String idCardBehind) {
        this.idCardBehind = idCardBehind;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getPersonalPhoto() {
        return personalPhoto;
    }

    public void setPersonalPhoto(String personalPhoto) {
        this.personalPhoto = personalPhoto;
    }

    public String getMerchantPhoto() {
        return merchantPhoto;
    }

    public void setMerchantPhoto(String merchantPhoto) {
        this.merchantPhoto = merchantPhoto;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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

    @Override
    public String toString() {
        return "TdMerchantDetailInfoLaKaLa{" +
                "patchNo='" + patchNo + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", custName='" + custName + '\'' +
                ", channelType='" + channelType + '\'' +
                ", bizType='" + bizType + '\'' +
                ", bizName='" + bizName + '\'' +
                ", merLicenseNo='" + merLicenseNo + '\'' +
                ", linceseExpire='" + linceseExpire + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", address='" + address + '\'' +
                ", bizContent='" + bizContent + '\'' +
                ", crLicenceName='" + crLicenceName + '\'' +
                ", crLicenceNo='" + crLicenceNo + '\'' +
                ", idCardExpire='" + idCardExpire + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                ", openningBankNo='" + openningBankNo + '\'' +
                ", openningBankName='" + openningBankName + '\'' +
                ", clearingBankNo='" + clearingBankNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountKind='" + accountKind + '\'' +
                ", idCard='" + idCard + '\'' +
                ", settlePeriod='" + settlePeriod + '\'' +
                ", mccCode='" + mccCode + '\'' +
                ", debitRate='" + debitRate + '\'' +
                ", wechatType='" + wechatType + '\'' +
                ", wechatRate='" + wechatRate + '\'' +
                ", termNum='" + termNum + '\'' +
                ", retUrl='" + retUrl + '\'' +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardBehind='" + idCardBehind + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", businessLicence='" + businessLicence + '\'' +
                ", personalPhoto='" + personalPhoto + '\'' +
                ", merchantPhoto='" + merchantPhoto + '\'' +
                ", others='" + others + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}
