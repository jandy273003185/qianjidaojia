package com.qifenqian.bms.merchant.subAccount.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商户关系表
 * @author Lx
 *
 */
public class MerchantSubAccouontBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2926040873839050957L;

	/** ID */
	private String id;
	/** 客户号 */
	private String custId;
	/**
	 * 关联的CUST_NAME
	 */
	private String custName;
	/**渠道*/
	private String channelCode;
	/** 外部商户号 */
	private String outMerchantCode;
	/**分账接收方客户号custId*/
	private String receiverCustId;
	/** 分账方类型 */
	private String subAccountType;
	/** 分账方账号 */
	private String account;
	/** 分账方全称 */
	private String accountName;
	/** 与分账方的关系类型 */
	private String relationType;
	/** 微信自定义关系 */
	private String customRelation;
	/** 外部请求号 */
	private String outRequestNo;
	/** 分账描述, 微信必填*/
	private String desc;
	/** 分账比例 */
	private String rate;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifyTime;
	/**请求添加分账方状态*/
	private String reportStatus;
	/**使用状态*/
	private String status;
	/**
	 * 分账ID
	 */
	private String relationId;
	
	private List<String> receiverCustIdList;
	
	private List<String> rateList;
	
	
	public List<String> getReceiverCustIdList() {
		return receiverCustIdList;
	}
	public void setReceiverCustIdList(List<String> receiverCustIdList) {
		this.receiverCustIdList = receiverCustIdList;
	}
	public List<String> getRateList() {
		return rateList;
	}
	public void setRateList(List<String> rateList) {
		this.rateList = rateList;
	}
	public String getReceiverCustId() {
		return receiverCustId;
	}
	public void setReceiverCustId(String receiverCustId) {
		this.receiverCustId = receiverCustId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getCustomRelation() {
		return customRelation;
	}
	public void setCustomRelation(String customRelation) {
		this.customRelation = customRelation;
	}
	public String getOutRequestNo() {
		return outRequestNo;
	}
	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
