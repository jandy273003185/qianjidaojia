package com.qifenqian.bms.merchant.reports.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:05
 */
public class TdMerchantReportDetail implements Serializable {
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
