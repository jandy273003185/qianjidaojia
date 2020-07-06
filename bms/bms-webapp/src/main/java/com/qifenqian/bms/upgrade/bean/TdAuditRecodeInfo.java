package com.qifenqian.bms.upgrade.bean;

import java.io.Serializable;

/**
 * * 产品审核表
 *
 * @author houmianmian
 */
public class TdAuditRecodeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 审核编号 * */
  private String id;
  /** 商户编号 * */
  private String merchantCode;
  /** 审核状态 * */
  private String auditStatus;
  /** 审核信息 * */
  private String auditInfo;
  /** 审核类型 * */
  private String auditType;
  /** 审核时间 * */
  private String auditTime;
  /** 审核时间 * */
  private String userId;

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

  public String getAuditStatus() {
    return auditStatus;
  }

  public void setAuditStatus(String auditStatus) {
    this.auditStatus = auditStatus;
  }

  public String getAuditInfo() {
    return auditInfo;
  }

  public void setAuditInfo(String auditInfo) {
    this.auditInfo = auditInfo;
  }

  public String getAuditType() {
    return auditType;
  }

  public void setAuditType(String auditType) {
    this.auditType = auditType;
  }

  public String getAuditTime() {
    return auditTime;
  }

  public void setAuditTime(String auditTime) {
    this.auditTime = auditTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
