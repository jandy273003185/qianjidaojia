package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantReportInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8673547233560020026L;
    /**
     * 报备编号
     */
    private String id;

    /**
     * 商户编号
     */
    private String merchantCode;

    /**
     * 报备批次号
     */
    private String patchNo;

    /**
     * 报备渠道
     */
    private String channelNo;

    /**
     * 报备状态 : y 提交报备, n 未报备 ，e 异常，f 报备提交失败，o 报备成功
     */
    private String reportStatus;

    /**
     * 报备时间
     */
    private String reportTime;

    /**
     * 状态：00 无效 01有效
     */
    private String status;

    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 渠道商户号
     */
    private String outMerchantCode;

    /**
     * 报备明细状态
     */
    private String detailStatus;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
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
}
