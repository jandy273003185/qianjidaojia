package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TdPaymentBatInfoBean  implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 7217816649061769925L;

	/**
	 * 批次号（日期YYYYMMDD+序列组成）
	 */
	private String batNo = null;

	/**
	 * 付款账号
	 */
	private String paerAcctNo = null;
	/**
	 * 总金额
	 */
	private String sumAmt = null;
	/**
	 * 成功总金额
	 */
	private String succAmt = null;
	/**
	* 总笔数
	*/
	private String sumCount = null;
	
	/**
	 * 成功笔数
	 */
	private String succCount = null;
	/**
	 * 失败总金额
	 */
	private String failAmt = null;
	/**
	 * 失败笔数
	 */
	private String failCount = null;
	/**
	 *'交易状态：01 带审核；02 审核通过；03 审核不通过 ；00 交易成功; 99 交易失败 08交易进行中',
	 */
	private String tradeStatus = null;
	
	/**
	 * 创建时间
	 */
	private  Date createTime = null;
	/**
	 * 创建人
	 */
	private String createId = null;
	
	/**
	 * 修改人
	 */
	private String modifyId = null;
	/**
	 * 修改时间
	 */
	private String modifyTime = null;

	
	private String endCreateTime = null;
	
	
	/**
	 * 代付类型 '00 银行卡；01 余额',
	 */
	private String toPayType=null;
	
	
	/**
	 * 
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 
	 * 用来标识单笔还是批量
	 */
	private String  type;
	/**
	 * 
	 * 起始时间
	 */
	private String startCreateTime;
	
	/**
	 * 
	 *支行名称
	 */
	private String payeeAcctBankName;
	
	/**
	 *服务费
	 */
	private String serviceFee;
	
	/**
	 * 付款金额
	 * */
	private String fkje;
	
	/***
	 * 核心流水号
	 */
	private String coreSn;
	
	/***
	 * 核心返回码
	 */
	private String coreReturnCode;
	
	/***
	 * 核心返回信息
	 */
	private String coreReturnInfo;
	
	/***
	 * 核心返回时间
	 */
	private Date coreReturnTime;
	
	
	private String bankPaymentNo;//凭证号
	
	private String  feeAmt;
	
	private String reason;
	
	private String auditTime;
	
	private String[] toPayStatus;
	
	private String orderNo;
	
	private String paerMerchantCode;
	
	private String merchantProductId; //关联商户产品ID
	
	
	
	public String getMerchantProductId() {
		return merchantProductId;
	}

	public void setMerchantProductId(String merchantProductId) {
		this.merchantProductId = merchantProductId;
	}

	public String getPaerMerchantCode() {
		return paerMerchantCode;
	}

	public void setPaerMerchantCode(String paerMerchantCode) {
		this.paerMerchantCode = paerMerchantCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public String getCoreReturnCode() {
		return coreReturnCode;
	}

	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}

	public String getCoreReturnInfo() {
		return coreReturnInfo;
	}

	public void setCoreReturnInfo(String coreReturnInfo) {
		this.coreReturnInfo = coreReturnInfo;
	}

	public Date getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(Date coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}

	public String getFkje() {
		if(this.serviceFee==null||"".equals(this.serviceFee)){
			this.serviceFee = "0.00";
		}
		if(this.sumAmt==null||"".equals(this.sumAmt)){
			this.sumAmt = "0.00";
		}
		return String.valueOf(new BigDecimal(sumAmt).add(new BigDecimal(serviceFee)));
	}

	public void setFkje(String fkje) {
		this.fkje = fkje;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getPaerAcctNo() {
		return paerAcctNo;
	}

	public void setPaerAcctNo(String paerAcctNo) {
		this.paerAcctNo = paerAcctNo;
	}

	public String getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getSuccAmt() {
		return succAmt;
	}

	public void setSuccAmt(String succAmt) {
		this.succAmt = succAmt;
	}

	public String getSumCount() {
		return sumCount;
	}

	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}

	
	public String getSuccCount() {
		return succCount;
	}

	public void setSuccCount(String succCount) {
		this.succCount = succCount;
	}

	public String getFailAmt() {
		return failAmt;
	}

	public void setFailAmt(String failAmt) {
		this.failAmt = failAmt;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getToPayType() {
		return toPayType;
	}

	public void setToPayType(String toPayType) {
		this.toPayType = toPayType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getPayeeAcctBankName() {
		return payeeAcctBankName;
	}

	public void setPayeeAcctBankName(String payeeAcctBankName) {
		this.payeeAcctBankName = payeeAcctBankName;
	}

	public String getBankPaymentNo() {
		return bankPaymentNo;
	}

	public void setBankPaymentNo(String bankPaymentNo) {
		this.bankPaymentNo = bankPaymentNo;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String[] getToPayStatus() {
		return toPayStatus;
	}

	public void setToPayStatus(String[] toPayStatus) {
		this.toPayStatus = toPayStatus;
	}

}
