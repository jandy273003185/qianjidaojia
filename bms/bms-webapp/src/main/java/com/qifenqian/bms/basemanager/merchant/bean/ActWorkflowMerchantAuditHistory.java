package com.qifenqian.bms.basemanager.merchant.bean;

public class ActWorkflowMerchantAuditHistory {
	/**
	 * ID
	 */
    private int id;

	/**
	 * 商户ID
	 */
    private String merchantid;

	/**
	 * 流程实例ID
	 */
    private String procInstId;

	/**
	 * 审核状态 01 一级审核通过；02 二级审核通过；03 审核不通过
	 */
    private String status;

	/**
	 * 审核信息
	 */
    private String message;

	/**
	 * 审核人
	 */
    private String auditer;

	/**
	 * 审核时间：YYYY-MM-DD HH:MI:SS
	 */
    private String auditTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}
