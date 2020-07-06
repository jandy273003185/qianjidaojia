package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;
import java.util.Date;


public class TdPaymentBatInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8640542418322868694L;

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
	 *'交易状态：00 交易成功; 
      01 带审核；02 审核通过；03 审核不通过 ；04 前台审核通过 ；06 清洁算审核通过 ；07已撤销08发送银行成功；99 交易失败 ；
      90交易异常；60：银行打款成功；92 清洁算审核未通过;93 财务审核未通过;95 申请失败;98 申请异常；96 核销异常;80 核销失败'
      97 撤销异常；94 撤销失败;
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
	 * 银行卡号
	 *
	 */
	private String recAccountNo;
	
	/**
	 * 
	 *收款人姓名
	 */
	private String recAccountName;
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
	
	private String custName;
	
	private String feeAmt;
	/**
	 * 处理状态：01 未提交；02 已提交；03 银行处理中；00 银行处理完成
	 */
	private String batStatus = null;
	

	private String  recBankCnaps;

	private String message;

    private String scanCopyPath;
    
    private String recCardName;

    
    private String custId;
    
    
    private String bankPaymentNo;

	
    private String payFee;
    
    /**
	 * 
	 * 一级审核时间
	 */
	private String fristAuditTime;
	
	/**
	 * 
	 * 二级审核时间
	 */
	private String secondAuditTime;
	
	private String startfristAuditTime;
	private String endfristAuditTime;
	private String startsecondAuditTime;
	private String endsecondAuditTime;
	private String channelType;
	private String lineNumber;
	private String provinceCode;
	private String cityCode;
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getStartfristAuditTime() {
		return startfristAuditTime;
	}

	public void setStartfristAuditTime(String startfristAuditTime) {
		this.startfristAuditTime = startfristAuditTime;
	}

	public String getEndfristAuditTime() {
		return endfristAuditTime;
	}

	public void setEndfristAuditTime(String endfristAuditTime) {
		this.endfristAuditTime = endfristAuditTime;
	}

	public String getStartsecondAuditTime() {
		return startsecondAuditTime;
	}

	public void setStartsecondAuditTime(String startsecondAuditTime) {
		this.startsecondAuditTime = startsecondAuditTime;
	}

	public String getEndsecondAuditTime() {
		return endsecondAuditTime;
	}

	public void setEndsecondAuditTime(String endsecondAuditTime) {
		this.endsecondAuditTime = endsecondAuditTime;
	}

	public String getFristAuditTime() {
		return fristAuditTime;
	}

	public void setFristAuditTime(String fristAuditTime) {
		this.fristAuditTime = fristAuditTime;
	}

	public String getSecondAuditTime() {
		return secondAuditTime;
	}

	public void setSecondAuditTime(String secondAuditTime) {
		this.secondAuditTime = secondAuditTime;
	}

	public String getRecAccountName() {
		return recAccountName;
	}

	public void setRecAccountName(String recAccountName) {
		this.recAccountName = recAccountName;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecAccountNo() {
		return recAccountNo;
	}

	public void setRecAccountNo(String recAccountNo) {
		this.recAccountNo = recAccountNo;
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

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getFkje() {
		return fkje;
	}

	public void setFkje(String fkje) {
		this.fkje = fkje;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBatStatus() {
		return batStatus;
	}

	public void setBatStatus(String batStatus) {
		this.batStatus = batStatus;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getRecBankCnaps() {
		return recBankCnaps;
	}

	public void setRecBankCnaps(String recBankCnaps) {
		this.recBankCnaps = recBankCnaps;
	}

	public String getScanCopyPath() {
		return scanCopyPath;
	}

	public void setScanCopyPath(String scanCopyPath) {
		this.scanCopyPath = scanCopyPath;
	}

	public String getRecCardName() {
		return recCardName;
	}

	public void setRecCardName(String recCardName) {
		this.recCardName = recCardName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getBankPaymentNo() {
		return bankPaymentNo;
	}

	public void setBankPaymentNo(String bankPaymentNo) {
		this.bankPaymentNo = bankPaymentNo;
	}

	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

}
