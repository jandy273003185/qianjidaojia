package com.qifenqian.bms.v2.biz.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantReportInfoVO implements Serializable {


    private static final long serialVersionUID = -1993154751526062750L;

    private String id;//报备编号

    private String merchantCode;//商户编号

    private String patchNo;//报备批次号

    private String channelNo;//报备渠道

    private String reportStatus;//报备状态 Y 提交报备, N 未报备 ，E 异常，F 报备提交失败，O 报备成功'

    private String status;//状态 00 无效 01有效'

    private String resultMsg;//返回信息

    private String reportTime;//创建时间

    private String outMerchantCode;//渠道商户号

    private String detailStatus;//报备明细状态

    private String channelMark;//渠道查询标识

    private String custName;//商户名称

    private String shortName;//商户简称

    private String email;//邮箱

    private String mobile;//电话

    private String custId;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getOutMerchantCode() {
        return outMerchantCode;
    }

    public void setOutMerchantCode(String outMerchantCode) {
        this.outMerchantCode = outMerchantCode;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public String getChannelMark() {
        return channelMark;
    }

    public void setChannelMark(String channelMark) {
        this.channelMark = channelMark;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
}
