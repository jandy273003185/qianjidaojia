package com.qifenqian.bms.merchant.reported.bean;

import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;

import java.io.Serializable;

/**
 * @description:
 * @author: LiBin
 * @String: 2019/12/25 0025 17:04
 */
public class TdMerchantDetailInfoSuixingPay extends TdMerchantReportDetail implements Serializable {


    private static final long serialVersionUID = -6420272759436694320L;
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
     * 联系人手机号
     */
    private String mobileNo;

    /**
     * 资质类型
     */
    private String suiXingMerchantType;

    /**
     * 商户类型
     */
    private String mecTypeFlag;

    /**
     * 营业执照注册名称
     */
    private String cprRegNmCn;

    /**
     * 营业执照注册号
     */
    private String registCode;

    /**
     * 注册省
     */
    private String merchantProvince;

    /**
     * 注册市
     */
    private String merchantCity;

    /**
     * 注册地区
     */
    private String merchantArea;

    /**
     * 注册详细地址
     */
    private String cprRegAddr;

    /**
     * 行业信息mcc
     */
    private String industryCode;

    /**
     * 法人姓名
     */
    private String representativeName;

    /**
     * 法人证件类型
     */
    private String representativeCertType;

    /**
     * 法人证件号
     */
    private String representativeCertNo;

    /**
     * 结算账户名
     */
    private String actNm;

    /**
     * 结算账户类型
     */
    private String actType;

    /**
     * 结算证件号
     */
    private String certifyNo;

    /**
     * 结算账号
     */
    private String bankCardNo;

    /**
     * 开户行
     */
    private String suiXinBank;

    /**
     * 开户行所在省
     */
    private String bankProvince;

    /**
     * 开户行所在市
     */
    private String bankCity;

    /**
     * 开户支行名称
     */
    private String interbankName;

    /**
     * 费率
     */
    private String rate;

    /**
     * 上传文件返回标识码
     */
    private String taskCode;

    /**
     * 联行号
     */
    private String lbnkNo;

    /**
     * 所属总店商户编号
     */
    private String parentMno;

    /**
     * 分店是否独立结算
     */
    private String independentModel;

    /**
     * 回调地址
     */
    private String merUrl;

    /**
     * 上传文件路径
     */
    private String filePath;

    /**
     * 渠道返回商户号
     */
    private String outMerchantCode;

    /**
     * 签约二维码
     */
    private String signQrcode;

    /**
     * 微信申请单编号
     */
    private String wxApplyNo;

    /**
     * 微信实名认证状态
     */
    private String idenStatus;

    /**
     * 渠道微信子商户号
     */
    private String wxChildNo;

    /**
     * 渠道支付宝子商户号
     */
    private String zfbChildNo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 标识状态 1 待审核通过后更新
     */
    private String flagStatus;

    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 门头照路径
     */
    private  String doorPhotoPath;

    /**
     * 店内照路径
     */
    private String shopInteriorPath;

    /**
     * 营业执照路径
     */
    private String businessPhotoPath;

    /**
     * 法人身份证人像照
     */
    private String legalCertAttribute1Path;

    /**
     * 法人身份证国徽照
     */
    private String legalCertAttribute2Path;

    /**
     * 非法人结算授权函
     */
    private String letterOfAuthPath;

    /**
     * 开户许可证
     */
    private  String openAccountPath;

    /**
     * 银行卡正面照
     */
    private String bankCardPhotoPath;

    /**
     * 结算人身份证人像照
     */
    private String settleCertAttribute1Path;

    /**
     * 结算人身份证国徽照
     */
    private String settleCertAttribute2Path;

    /**
     * 店内收银台照
     */
    private String shopCheckStandPath;

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

    public String getBusinessPhotoPath() {
        return businessPhotoPath;
    }

    public void setBusinessPhotoPath(String businessPhotoPath) {
        this.businessPhotoPath = businessPhotoPath;
    }

    public String getLegalCertAttribute1Path() {
        return legalCertAttribute1Path;
    }

    public void setLegalCertAttribute1Path(String legalCertAttribute1Path) {
        this.legalCertAttribute1Path = legalCertAttribute1Path;
    }

    public String getLegalCertAttribute2Path() {
        return legalCertAttribute2Path;
    }

    public void setLegalCertAttribute2Path(String legalCertAttribute2Path) {
        this.legalCertAttribute2Path = legalCertAttribute2Path;
    }

    public String getLetterOfAuthPath() {
        return letterOfAuthPath;
    }

    public void setLetterOfAuthPath(String letterOfAuthPath) {
        this.letterOfAuthPath = letterOfAuthPath;
    }

    public String getOpenAccountPath() {
        return openAccountPath;
    }

    public void setOpenAccountPath(String openAccountPath) {
        this.openAccountPath = openAccountPath;
    }

    public String getBankCardPhotoPath() {
        return bankCardPhotoPath;
    }

    public void setBankCardPhotoPath(String bankCardPhotoPath) {
        this.bankCardPhotoPath = bankCardPhotoPath;
    }

    public String getSettleCertAttribute1Path() {
        return settleCertAttribute1Path;
    }

    public void setSettleCertAttribute1Path(String settleCertAttribute1Path) {
        this.settleCertAttribute1Path = settleCertAttribute1Path;
    }

    public String getSettleCertAttribute2Path() {
        return settleCertAttribute2Path;
    }

    public void setSettleCertAttribute2Path(String settleCertAttribute2Path) {
        this.settleCertAttribute2Path = settleCertAttribute2Path;
    }

    public String getShopCheckStandPath() {
        return shopCheckStandPath;
    }

    public void setShopCheckStandPath(String shopCheckStandPath) {
        this.shopCheckStandPath = shopCheckStandPath;
    }

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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSuiXingMerchantType() {
        return suiXingMerchantType;
    }

    public void setSuiXingMerchantType(String suiXingMerchantType) {
        this.suiXingMerchantType = suiXingMerchantType;
    }

    public String getMecTypeFlag() {
        return mecTypeFlag;
    }

    public void setMecTypeFlag(String mecTypeFlag) {
        this.mecTypeFlag = mecTypeFlag;
    }

    public String getCprRegNmCn() {
        return cprRegNmCn;
    }

    public void setCprRegNmCn(String cprRegNmCn) {
        this.cprRegNmCn = cprRegNmCn;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }

    public String getMerchantProvince() {
        return merchantProvince;
    }

    public void setMerchantProvince(String merchantProvince) {
        this.merchantProvince = merchantProvince;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public String getMerchantArea() {
        return merchantArea;
    }

    public void setMerchantArea(String merchantArea) {
        this.merchantArea = merchantArea;
    }

    public String getCprRegAddr() {
        return cprRegAddr;
    }

    public void setCprRegAddr(String cprRegAddr) {
        this.cprRegAddr = cprRegAddr;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getRepresentativeCertType() {
        return representativeCertType;
    }

    public void setRepresentativeCertType(String representativeCertType) {
        this.representativeCertType = representativeCertType;
    }

    public String getRepresentativeCertNo() {
        return representativeCertNo;
    }

    public void setRepresentativeCertNo(String representativeCertNo) {
        this.representativeCertNo = representativeCertNo;
    }

    public String getActNm() {
        return actNm;
    }

    public void setActNm(String actNm) {
        this.actNm = actNm;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getCertifyNo() {
        return certifyNo;
    }

    public void setCertifyNo(String certifyNo) {
        this.certifyNo = certifyNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getSuiXinBank() {
        return suiXinBank;
    }

    public void setSuiXinBank(String suiXinBank) {
        this.suiXinBank = suiXinBank;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getInterbankName() {
        return interbankName;
    }

    public void setInterbankName(String interbankName) {
        this.interbankName = interbankName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getLbnkNo() {
        return lbnkNo;
    }

    public void setLbnkNo(String lbnkNo) {
        this.lbnkNo = lbnkNo;
    }

    public String getParentMno() {
        return parentMno;
    }

    public void setParentMno(String parentMno) {
        this.parentMno = parentMno;
    }

    public String getIndependentModel() {
        return independentModel;
    }

    public void setIndependentModel(String independentModel) {
        this.independentModel = independentModel;
    }

    public String getMerUrl() {
        return merUrl;
    }

    public void setMerUrl(String merUrl) {
        this.merUrl = merUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOutMerchantCode() {
        return outMerchantCode;
    }

    public void setOutMerchantCode(String outMerchantCode) {
        this.outMerchantCode = outMerchantCode;
    }

    public String getSignQrcode() {
        return signQrcode;
    }

    public void setSignQrcode(String signQrcode) {
        this.signQrcode = signQrcode;
    }

    public String getWxApplyNo() {
        return wxApplyNo;
    }

    public void setWxApplyNo(String wxApplyNo) {
        this.wxApplyNo = wxApplyNo;
    }

    public String getIdenStatus() {
        return idenStatus;
    }

    public void setIdenStatus(String idenStatus) {
        this.idenStatus = idenStatus;
    }

    public String getWxChildNo() {
        return wxChildNo;
    }

    public void setWxChildNo(String wxChildNo) {
        this.wxChildNo = wxChildNo;
    }

    public String getZfbChildNo() {
        return zfbChildNo;
    }

    public void setZfbChildNo(String zfbChildNo) {
        this.zfbChildNo = zfbChildNo;
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

    public String getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(String flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Override
    public String toString() {
        return "TdMerchantDetailInfoSuixingPay{" +
                "patchNo='" + patchNo + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", custName='" + custName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", suiXingMerchantType='" + suiXingMerchantType + '\'' +
                ", mecTypeFlag='" + mecTypeFlag + '\'' +
                ", cprRegNmCn='" + cprRegNmCn + '\'' +
                ", registCode='" + registCode + '\'' +
                ", merchantProvince='" + merchantProvince + '\'' +
                ", merchantCity='" + merchantCity + '\'' +
                ", merchantArea='" + merchantArea + '\'' +
                ", cprRegAddr='" + cprRegAddr + '\'' +
                ", industryCode='" + industryCode + '\'' +
                ", representativeName='" + representativeName + '\'' +
                ", representativeCertType='" + representativeCertType + '\'' +
                ", representativeCertNo='" + representativeCertNo + '\'' +
                ", actNm='" + actNm + '\'' +
                ", actType='" + actType + '\'' +
                ", certifyNo='" + certifyNo + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", suiXinBank='" + suiXinBank + '\'' +
                ", bankProvince='" + bankProvince + '\'' +
                ", bankCity='" + bankCity + '\'' +
                ", interbankName='" + interbankName + '\'' +
                ", rate='" + rate + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", lbnkNo='" + lbnkNo + '\'' +
                ", parentMno='" + parentMno + '\'' +
                ", independentModel='" + independentModel + '\'' +
                ", merUrl='" + merUrl + '\'' +
                ", filePath='" + filePath + '\'' +
                ", outMerchantCode='" + outMerchantCode + '\'' +
                ", signQrcode='" + signQrcode + '\'' +
                ", wxApplyNo='" + wxApplyNo + '\'' +
                ", idenStatus='" + idenStatus + '\'' +
                ", wxChildNo='" + wxChildNo + '\'' +
                ", zfbChildNo='" + zfbChildNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", flagStatus='" + flagStatus + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", doorPhotoPath='" + doorPhotoPath + '\'' +
                ", shopInteriorPath='" + shopInteriorPath + '\'' +
                ", businessPhotoPath='" + businessPhotoPath + '\'' +
                ", legalCertAttribute1Path='" + legalCertAttribute1Path + '\'' +
                ", legalCertAttribute2Path='" + legalCertAttribute2Path + '\'' +
                ", letterOfAuthPath='" + letterOfAuthPath + '\'' +
                ", openAccountPath='" + openAccountPath + '\'' +
                ", bankCardPhotoPath='" + bankCardPhotoPath + '\'' +
                ", settleCertAttribute1Path='" + settleCertAttribute1Path + '\'' +
                ", settleCertAttribute2Path='" + settleCertAttribute2Path + '\'' +
                ", shopCheckStandPath='" + shopCheckStandPath + '\'' +
                '}';
    }
}
