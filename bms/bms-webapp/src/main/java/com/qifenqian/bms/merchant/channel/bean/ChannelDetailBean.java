package com.qifenqian.bms.merchant.channel.bean;

import com.alibaba.fastjson.JSON;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.PayType;

import java.util.Date;

public class ChannelDetailBean {
    // 商户在渠道的ID
    private String merchantChannelId;
    private String custId;
    private String custName;
    // 支付通道编码
    private ChannelCode channelCode;
    // 微信支付APPID
    private String wxAppId;
    private String wxAppsecret;
    // 支付通道产品编码
    private PayType subCode;
    private Date createTime;
    private Date modifyTime;
    private ChannelStatus status;
    private String comment;
    // 商户在渠道的KEY
    private String merchantChannelKey;

    // 查询使用
    private String merchantCode;

	/**
	 * 渠道微信子商户号
	 */
	private String wxChildNo;
	/**
	 * 渠道支付宝子商户号
	 */
    private String zfbChildNo;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public PayType getSubCode() {
        return subCode;
    }

    public void setSubCode(PayType subCode) {
        this.subCode = subCode;
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

    public ChannelStatus getStatus() {
        return status;
    }

    public void setStatus(ChannelStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ChannelCode getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(ChannelCode channelCode) {
        this.channelCode = channelCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getWxAppsecret() {
        return wxAppsecret;
    }

    public void setWxAppsecret(String wxAppsecret) {
        this.wxAppsecret = wxAppsecret;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getMerchantChannelKey() {
        return merchantChannelKey;
    }

    public void setMerchantChannelKey(String merchantChannelKey) {
        this.merchantChannelKey = merchantChannelKey;
    }

    public String getMerchantChannelId() {
        return merchantChannelId;
    }

    public void setMerchantChannelId(String merchantChannelId) {
        this.merchantChannelId = merchantChannelId;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
