package com.qifenqian.bms.basemanager.agent.bean;

import java.io.Serializable;
import java.util.Date;

public class AgentSignBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;

	private String agentId;//代理商ID
	private String merchantId;//签约商户ID
	private String merchantArea;//签约商户区域
	private String signId;//签约人ID
	private String signRate;//费率
	private String signAuditStatus;//签约审核状态
	private Date signTime;//签约时间
	private String commission;//佣金
	private String permissionLvl;//权限
	private String businessType;//业务类型
	private String webUrl;//网址
	private String icpId;//ICP编号
	private String icpRecordId;//ICP备案号
	private String webName;//网站名称
	private String appName;//app名称
	private String developerName;//开发者名称
	private String platform;//发布平台
	private String market;//发布市场
	private String auditUserId;//审核人编号
	private String memo;//审核人编号

	
	private String startCreateTime;
	private String endCreateTime;
	
	private String auditUserName;
	
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAuditUserId() {   
		return auditUserId;
	}
	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	public String getStartCreateTime() {
		return startCreateTime;
	}
	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantArea() {
		return merchantArea;
	}
	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getSignRate() {
		return signRate;
	}
	public void setSignRate(String signRate) {
		this.signRate = signRate;
	}
	public String getSignAuditStatus() {
		return signAuditStatus;
	}
	public void setSignAuditStatus(String signAuditStatus) {
		this.signAuditStatus = signAuditStatus;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getPermissionLvl() {
		return permissionLvl;
	}
	public void setPermissionLvl(String permissionLvl) {
		this.permissionLvl = permissionLvl;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getIcpId() {
		return icpId;
	}
	public void setIcpId(String icpId) {
		this.icpId = icpId;
	}
	public String getIcpRecordId() {
		return icpRecordId;
	}
	public void setIcpRecordId(String icpRecordId) {
		this.icpRecordId = icpRecordId;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getDeveloperName() {
		return developerName;
	}
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	
	
	
	
	
	
}
