package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代付批次信息
 * @author hongjiagui
 *
 */
public class TopayBatDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5807978023756821849L;

	/**批次号（日期YYYYMMDD+序列组成）**/
	private String batNo;
	
	/**付款账号**/
	private String paerAcctNo;
	
	/**付款商户商户编号**/
	private String paerMerchantCode;
	/**总金额**/
	private BigDecimal sumAmt;

	/**总笔数**/
	private int sumCount;

	/**交易状态：00 交易成功;01 带审核；02 审核通过；03 审核不通过 ；
	 * 08 发送银行成功；
	 */
  	private String tradeStatus;
  	
  	/**00 银行卡;  01余额 **/
  	private String topayType;
  	
  	/**成功总金额**/
  	private BigDecimal succAmt;
  	
  	/**成功笔数**/
  	private int succCount;
  	
  	/**失败总金额**/
  	private BigDecimal failAmt;
  
  	/**失败笔数**/
  	private int failCount;
  	
  	/**创建人Id**/
  	private String createId;
  	
  	/**创建人名字**/
  	private String createName;
  	
  	/**银行上传凭证图片路径**/
  	private String scanCopyPath;
  	
  	/**商户名称**/
  	private String custName;
  	 
  	 
  	
  	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getScanCopyPath() {
		return scanCopyPath;
	}

	public void setScanCopyPath(String scanCopyPath) {
		this.scanCopyPath = scanCopyPath;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**创建时间**/
  	private Date createTime;
  	
  	/**修改人**/
  	private String modifyId;
  	
  	/**修改时间**/
  	private Date modifyTime;
  	
  	/**手续费**/
  	private BigDecimal feeAmt;
  
  	/**银行付款凭证号**/
  	private String bankPaymentNo;

  	
  	/**审核内容**/
  	private String memo;
  	
 	

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getPaerMerchantCode() {
		return paerMerchantCode;
	}

	public void setPaerMerchantCode(String paerMerchantCode) {
		this.paerMerchantCode = paerMerchantCode;
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

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTopayType() {
		return topayType;
	}

	public void setTopayType(String topayType) {
		this.topayType = topayType;
	}

	public BigDecimal getSuccAmt() {
		return succAmt;
	}

	public void setSuccAmt(BigDecimal succAmt) {
		this.succAmt = succAmt;
	}

	public int getSuccCount() {
		return succCount;
	}

	public void setSuccCount(int succCount) {
		this.succCount = succCount;
	}

	public BigDecimal getFailAmt() {
		return failAmt;
	}

	public void setFailAmt(BigDecimal failAmt) {
		this.failAmt = failAmt;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getBankPaymentNo() {
		return bankPaymentNo;
	}

	public void setBankPaymentNo(String bankPaymentNo) {
		this.bankPaymentNo = bankPaymentNo;
	}


  	
  	
  	
}
