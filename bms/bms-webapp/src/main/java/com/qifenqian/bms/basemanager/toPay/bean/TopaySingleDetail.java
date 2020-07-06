package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代付单条记录
 * @author hongjiagui
 *
 */
public class TopaySingleDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4115918545414390019L;

	 /**单笔批次号（日期YYYYMMDD+序列组成）**/
	private String batNo;
	 
	/**序号**/
    private String rowNo;
	 /**本次操作批次号**/
    private String orderNo;
  
  	/**收款人名称**/
  	private String recAccountName;
  
  	/**收款人账号**/
  	private String recAccountNo;
  
  	/**收款人银行编号**/
  	private String recBankCode;
  	
  	/**收款人银行名称**/
  	private String recCardName;
  
	/**收款银行开户省份**/
  	private String recBankProvince;
  	
	/**收款银行开户城市**/
  	private String recBankCity;
  	/**收款人联行号**/
  	private String recBankCnaps;
  	/**省份编号**/
  	private String provinceCode;
  	
  	/**城市编号**/
  	private String cityCode;
  	
  	/**收款金额**/
  	private BigDecimal transAmt;
  	
  	/**代付商户code*/
  	private String paerMerchantCode;
  	
  	/**单笔代付手续费**/

  	private BigDecimal SingleFeeAmt;

  	/**
  	 * 交易状态：01 带审核；02 审核通过；04前台审核通过；
  	 * 03 审核不通过 ；00 交易成功; 99 交易失败 06 发送银行成功 ；
  	 * 60：银行打款成功；07已撤销;05 清洁算审核通过；08 清洁算审核未通过;
  	 * 09 财务审核未通过;80 核销异常；90交易异常；\r\n94：撤销失败\r\n97：撤销异常',
  	 */
  	private String tradeStatus;
  
  	/**00 银行卡;  01余额 **/
  	private String topayType;
  
  	/**处理情况摘要**/
  	private String procMemo;
  	
  	/**创建人**/
  	private String createId;
  
  	/**创建时间**/
  	private Date createTime;
  	
  	/**修改人**/
  	private String modifyId;
  	
  	/**修改时间**/
  	private String modifyTime;
  	
  	/**核心流水号**/
  	private String coreSn;
  	
  	/**核心返回码**/
  	private String coreReturnCode;
  	
  	/**核心返回信息**/
  	private String coreReturnInfo;
  	
  	/**核心返回时间**/
  	private String coreReturnTime;
  	
  	/**审核内容**/
  	private String memo;
  	
  	
  	
  	

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getRecAccountName() {
		return recAccountName;
	}

	public void setRecAccountName(String recAccountName) {
		this.recAccountName = recAccountName;
	}

	public String getRecAccountNo() {
		return recAccountNo;
	}

	public void setRecAccountNo(String recAccountNo) {
		this.recAccountNo = recAccountNo;
	}

	public String getRecBankCode() {
		return recBankCode;
	}

	public void setRecBankCode(String recBankCode) {
		this.recBankCode = recBankCode;
	}

	public String getRecCardName() {
		return recCardName;
	}

	public void setRecCardName(String recCardName) {
		this.recCardName = recCardName;
	}

	public String getRecBankProvince() {
		return recBankProvince;
	}

	public void setRecBankProvince(String recBankProvince) {
		this.recBankProvince = recBankProvince;
	}

	public String getRecBankCity() {
		return recBankCity;
	}

	public void setRecBankCity(String recBankCity) {
		this.recBankCity = recBankCity;
	}

	public String getRecBankCnaps() {
		return recBankCnaps;
	}

	public void setRecBankCnaps(String recBankCnaps) {
		this.recBankCnaps = recBankCnaps;
	}

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

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
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

	public String getProcMemo() {
		return procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
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

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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

	public String getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(String coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}

	public String getPaerMerchantCode() {
		return paerMerchantCode;
	}

	public void setPaerMerchantCode(String paerMerchantCode) {
		this.paerMerchantCode = paerMerchantCode;
	}

	public BigDecimal getSingleFeeAmt() {
		return SingleFeeAmt;
	}

	public void setSingleFeeAmt(BigDecimal SingleFeeAmt) {
		this.SingleFeeAmt = SingleFeeAmt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
  
  
}
