package com.qifenqian.bms.merchant.reported.bean;


import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author LiBin
 * @Description 阿里报备
 * @Param
 * @Return
 * @Date 2019/12/19 0019 14:49
 */
public class TdMerchantDetailInfoAlipay extends TdMerchantReportDetail implements Serializable {

    private static final long serialVersionUID = 1154853797960240258L;


    /**
     * 报备批次号
     */
    private String patchNo;

    /**
     * 商户编号
     */
    private String merchantCode;

    /**
     * 商户名称
     */
    private String custName;

    /**
     * 支付宝账号
     */
    private String account;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人手机号码
     */
    private String contactMobile;

    /**
     * 联系人手机号码
     */
    private String contactEmail;

    /**
     * 经营类目编码
     */
    private String mccCode;

    /**
     * 企业特殊资质图片路径
     */
    private String qualificationPath;

    /**
     * 营业执照号码
     */
    private String businessLicense;

    /**
     * 营业执照图片路径
     */
    private String businessPhotoPath;

    /**
     * 营业执照授权函图片路径
     */
    private String businessAuthPhotoPath;

    /**
     * 营业期限是否长期有效
     */
    private String longTerm;

    /**
     * 营业执照有效期
     */
    private String businessTerm;

    /**
     * 店铺内景图片路径
     */
    private String shopInteriorPath;

    /**
     * 店铺门头照图片路径
     */
    private String doorPhotoPath;

    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 报备明细状态
     */
    private String reportStatus;

    /**
     * 支付宝返回唯一事务编号
     */
    private String zfbBatchNo;

    /**
     * 支付宝返回事务状态
     */
    private String zfbBatchStatus;

    /**
     * 支付宝授权商户的user_id
     */
    private String zfbUserId;

    /**
     * 授权商户的appid
     */
    private String zfbAuthAppId;

    /**
     * 应用授权令牌
     */
    private String zfbAppAuthToken;

    /**
     * 刷新令牌
     */
    private String zfbAppRefreshToken;

    /**
     * 应用授权令牌的有效时间（从接口调用时间作为起始时间），单位到秒
     */
    private String zfbExpiresIn;

    /**
     * 刷新令牌的有效时间（从接口调用时间作为起始时间），单位到秒
     */
    private String zfbReExpiresIn;

    /**
     * 支付宝商户确认链接
     */
    private String zfbConfirmUrl;

    /**
     * 支付宝pid
     */
    private String merchantPid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 标识状态 1 待审核通过后更新
     */
    private String flagStatus;

    public String getPatchNo() {
        return patchNo;
    }

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public String getQualificationPath() {
        return qualificationPath;
    }

    public void setQualificationPath(String qualificationPath) {
        this.qualificationPath = qualificationPath;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessPhotoPath() {
        return businessPhotoPath;
    }

    public void setBusinessPhotoPath(String businessPhotoPath) {
        this.businessPhotoPath = businessPhotoPath;
    }

    public String getBusinessAuthPhotoPath() {
        return businessAuthPhotoPath;
    }

    public void setBusinessAuthPhotoPath(String businessAuthPhotoPath) {
        this.businessAuthPhotoPath = businessAuthPhotoPath;
    }

    public String getLongTerm() {
        return longTerm;
    }

    public void setLongTerm(String longTerm) {
        this.longTerm = longTerm;
    }

    public String getBusinessTerm() {
        return businessTerm;
    }

    public void setBusinessTerm(String businessTerm) {
        this.businessTerm = businessTerm;
    }

    public String getShopInteriorPath() {
        return shopInteriorPath;
    }

    public void setShopInteriorPath(String shopInteriorPath) {
        this.shopInteriorPath = shopInteriorPath;
    }

    public String getDoorPhotoPath() {
        return doorPhotoPath;
    }

    public void setDoorPhotoPath(String doorPhotoPath) {
        this.doorPhotoPath = doorPhotoPath;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getZfbBatchNo() {
        return zfbBatchNo;
    }

    public void setZfbBatchNo(String zfbBatchNo) {
        this.zfbBatchNo = zfbBatchNo;
    }

    public String getZfbBatchStatus() {
        return zfbBatchStatus;
    }

    public void setZfbBatchStatus(String zfbBatchStatus) {
        this.zfbBatchStatus = zfbBatchStatus;
    }

    public String getZfbUserId() {
        return zfbUserId;
    }

    public void setZfbUserId(String zfbUserId) {
        this.zfbUserId = zfbUserId;
    }

    public String getZfbAuthAppId() {
        return zfbAuthAppId;
    }

    public void setZfbAuthAppId(String zfbAuthAppId) {
        this.zfbAuthAppId = zfbAuthAppId;
    }

    public String getZfbAppAuthToken() {
        return zfbAppAuthToken;
    }

    public void setZfbAppAuthToken(String zfbAppAuthToken) {
        this.zfbAppAuthToken = zfbAppAuthToken;
    }

    public String getZfbAppRefreshToken() {
        return zfbAppRefreshToken;
    }

    public void setZfbAppRefreshToken(String zfbAppRefreshToken) {
        this.zfbAppRefreshToken = zfbAppRefreshToken;
    }

    public String getZfbExpiresIn() {
        return zfbExpiresIn;
    }

    public void setZfbExpiresIn(String zfbExpiresIn) {
        this.zfbExpiresIn = zfbExpiresIn;
    }

    public String getZfbReExpiresIn() {
        return zfbReExpiresIn;
    }

    public void setZfbReExpiresIn(String zfbReExpiresIn) {
        this.zfbReExpiresIn = zfbReExpiresIn;
    }

    public String getZfbConfirmUrl() {
        return zfbConfirmUrl;
    }

    public void setZfbConfirmUrl(String zfbConfirmUrl) {
        this.zfbConfirmUrl = zfbConfirmUrl;
    }

    public String getMerchantPid() {
        return merchantPid;
    }

    public void setMerchantPid(String merchantPid) {
        this.merchantPid = merchantPid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(String flagStatus) {
        this.flagStatus = flagStatus;
    }
}
