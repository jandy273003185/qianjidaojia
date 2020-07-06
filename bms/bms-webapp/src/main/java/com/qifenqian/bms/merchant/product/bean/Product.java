package com.qifenqian.bms.merchant.product.bean;

import java.io.Serializable;

/**
 * 产品bean
 * 
 * @project qifenqian-bms
 * @fileName Product.java
 * @author wuzz
 * @date 2019年11月8日
 * @memo
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 5148542939255364238L;
	
	/** 产品ID */
	private String productId;
	
	/** 产品代码 */
	private String productCode;
			
	/** 产品名称 */
	private String productName;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	


}
