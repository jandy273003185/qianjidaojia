package com.qifenqian.bms.merchant.channel.bean;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ShiLi
 * @Description:
 * @date 2020/5/11 15:48
 */
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  ChannelMerRegist {
    CR_ULOPAY("iCr","华润银行-优络"),

    SPD_QHWR("iSpdb","浦发-前海万融"),

    CNCB_SWIFT("iCncb","中信-威富通"),

    CEB_SWIFT("iCeb","中信-威富通"),

    WX("WX","微信支付"),

    HELIPAY("HELIPAY","合利宝支付"),

    FM_COMBINEDPAY("FM_COMBINEDPAY","富民银行"),

    KFT_PAY("KFT_PAY","快付通"),

    ALIPAY("ALIPAY","支付宝"),

    GHXB("GHXB","广东华兴银行"),

    QM_PAY("QM_PAY","易宝-钱麦"),

    KQ_PAY("KQ_PAY","快钱"),

    TX_PAY("TX_PAY","腾讯云支付"),

    XL_PAY("XL_PAY","迅联"),

    PING_AN("PING_AN","平安银行"),

    BEST_PAY("BEST_PAY","翼支付"),

    SUIXING_PAY("SUIXING_PAY","随行付"),

    SUM_PAY("SUM_PAY","商盟支付"),

    YQB("YQB","平安付-壹钱包"),

    FM_UNIONPAY("FM_UNIONPAY","富民银联"),

    ALLIN_PAY("ALLIN_PAY","通联"),

    LKL("LKL","拉卡拉"),

    DFD("1","1"),

    DFDf("1","1");

    private String code;
    private String text;

    private ChannelMerRegist(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }
}
