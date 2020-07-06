package com.qifenqian.bms.sns.balance.bean;

import java.math.BigDecimal;

public class ResultSevenDoubt extends ResultBase {

	/** 对账批次号 */
	private String batchId;

	/** 写入日期：YYYY-MM-DD */
	private String instDate;

	/** 七分钱流水号 */
	private String clearId;

	/** 会计日期(数据日期) */
	private String workDate;

	/** 客户号 */
	private String custId;

	private String transCode;

	/** 交易金额 */
	private BigDecimal transAmt;

	/** 交易状态：00或99（只有成功和失败两种状态） */
	private String transStatus;
	
	private String balMemo;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	public String getBalMemo() {
		return balMemo;
	}

	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}

}
