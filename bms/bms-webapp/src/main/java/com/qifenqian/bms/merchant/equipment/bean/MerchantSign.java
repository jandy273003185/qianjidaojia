package com.qifenqian.bms.merchant.equipment.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户设备bean
 * 
 * @project qifenqian-bms
 * @fileName MerchantSign.java
 * @author wuzz
 * @date 2019年11月8日
 * @memo
 */
public class MerchantSign implements Serializable {	

	private static final long serialVersionUID = 5703178835215104781L;

	/** id */
	private Integer id;
	
	/** 商户id */
	private String merchantId;
	
	/** 商户名称 */
	private String merchantName;
	
	/** 设备编号 */
	private String terminalNo;
			
	/** 秘钥 */
	private String merchantPublicKey;
	
	/** 初始时间 */
	private Date instDatetime;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getMerchantPublicKey() {
		return merchantPublicKey;
	}

	public void setMerchantPublicKey(String merchantPublicKey) {
		this.merchantPublicKey = merchantPublicKey;
	}

	public Integer getId() {
		return id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}


}
