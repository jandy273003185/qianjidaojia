package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class AllinPayProductInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6426828823008737489L;
	
	//id
	private String id;
	//商户号
	private String merchantCode;
	//产品类型
	private String productId;
	//交易类型
	private String mtrxCode;
	//类型名称
	private String productCode;
	//费率
	private String feeRate;
	//信用卡费率
	private String creditRate;
	//保底费率
	private String lowLimit;
	//封顶费率
	private String topLimit;
	//创建时间
	private String createTime;
	//修改时间
	private String modifyTime;
	//标识状态
	private String flagStatus;
	
	
	
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMtrxCode() {
		return mtrxCode;
	}
	public void setMtrxCode(String mtrxCode) {
		this.mtrxCode = mtrxCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}
	public String getCreditRate() {
		return creditRate;
	}
	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getTopLimit() {
		return topLimit;
	}
	public void setTopLimit(String topLimit) {
		this.topLimit = topLimit;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	

}
