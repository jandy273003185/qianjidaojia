package com.sevenpay.agentmanager.common.pojo;

public class ImagesUri {

    // 七分钱商户身份证（正面）
    private String identityCardFront;
    // 七分钱商户身份证（反面）
    private String identityCardReverse;
    // 七分钱商户银行卡正面照
    private String bankCardFront;
    // 七分钱商户店面门头照
    private String shopFrontDoor;
    // 七分钱商户店面内景
    private String shopInterior;
    // 七分钱商户营业执照（三合一）
    private String businessLicenseInOne;
    // 七分钱商户开户许可证
    private String licenceForOpeningAccounts;
    // 电子签名
    private String electronicSignaturePhoto;
    // 特殊行业资质照
    private String specialBusiness;
    // 其他图片1
    private String otherPhoto1;
    // 其他图片2
    private String otherPhoto2;


    public String getElectronicSignaturePhoto() {
        return electronicSignaturePhoto;
    }

    public void setElectronicSignaturePhoto(String electronicSignaturePhoto) {
        this.electronicSignaturePhoto = electronicSignaturePhoto;
    }

    public String getSpecialBusiness() {
        return specialBusiness;
    }

    public void setSpecialBusiness(String specialBusiness) {
        this.specialBusiness = specialBusiness;
    }

    public String getOtherPhoto1() {
        return otherPhoto1;
    }

    public void setOtherPhoto1(String otherPhoto1) {
        this.otherPhoto1 = otherPhoto1;
    }

    public String getOtherPhoto2() {
        return otherPhoto2;
    }

    public void setOtherPhoto2(String otherPhoto2) {
        this.otherPhoto2 = otherPhoto2;
    }

    public String getIdentityCardFront() {
        return identityCardFront;
    }

    public void setIdentityCardFront(String identityCardFront) {
        this.identityCardFront = identityCardFront;
    }

    public String getIdentityCardReverse() {
        return identityCardReverse;
    }

    public void setIdentityCardReverse(String identityCardReverse) {
        this.identityCardReverse = identityCardReverse;
    }

    public String getBankCardFront() {
        return bankCardFront;
    }

    public void setBankCardFront(String bankCardFront) {
        this.bankCardFront = bankCardFront;
    }

    public String getShopFrontDoor() {
        return shopFrontDoor;
    }

    public void setShopFrontDoor(String shopFrontDoor) {
        this.shopFrontDoor = shopFrontDoor;
    }

    public String getShopInterior() {
        return shopInterior;
    }

    public void setShopInterior(String shopInterior) {
        this.shopInterior = shopInterior;
    }

    public String getBusinessLicenseInOne() {
        return businessLicenseInOne;
    }

    public void setBusinessLicenseInOne(String businessLicenseInOne) {
        this.businessLicenseInOne = businessLicenseInOne;
    }

    public String getLicenceForOpeningAccounts() {
        return licenceForOpeningAccounts;
    }

    public void setLicenceForOpeningAccounts(String licenceForOpeningAccounts) {
        this.licenceForOpeningAccounts = licenceForOpeningAccounts;
    }
}
