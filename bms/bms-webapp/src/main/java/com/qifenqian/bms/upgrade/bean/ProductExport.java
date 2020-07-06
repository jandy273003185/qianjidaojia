package com.qifenqian.bms.upgrade.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 2017年7月12日 下午5:20:12
 */
@SuppressWarnings("serial")
public class ProductExport implements Serializable{
	

	/** 商户编号 **/
    private String merchantCode;
    
    /**商户名称 **/
	private String custName;
    
	/** 产品代码 **/
	private String productCode;
	
	/** 签约费率 **/
	private String signRate;
	
	/** 创建时间 **/
	private Date createTime;
	
	/**注册方式 PC:PC端；H5:H5端；HT:后台**/
	private String registerWay;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSignRate() {
		return signRate;
	}

	public void setSignRate(String signRate) {
		this.signRate = signRate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRegisterWay() {
		return registerWay;
	}

	public void setRegisterWay(String registerWay) {
		this.registerWay = registerWay;
	}
	
	
	
}
