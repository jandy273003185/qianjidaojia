package com.qifenqian.bms.v2.biz.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantReportInfoAO implements Serializable {


    private static final long serialVersionUID = -1993154751526062750L;

    private String merchantCode;//商户编号

    private String patchNo;//报备批次号

    private String channelNo;//报备渠道

    private String custName;//商户名称

    private String email;//邮箱

    private String mobile;//电话

    private String reportStatus;//报备状态 Y 提交报备, N 未报备 ，E 异常，F 报备提交失败，O 报备成功'

    private String status;//状态 00 无效 01有效'

    private String reportTimeStart;//创建时间起始值

    private String reportTimeEnd;//创建时间结束值

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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getReportTimeStart() {
        return reportTimeStart;
    }

    public void setReportTimeStart(String reportTimeStart) {
        this.reportTimeStart = reportTimeStart;
    }

    public String getReportTimeEnd() {
        return reportTimeEnd;
    }

    public void setReportTimeEnd(String reportTimeEnd) {
        this.reportTimeEnd = reportTimeEnd;
    }
}
