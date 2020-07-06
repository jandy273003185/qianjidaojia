package com.qifenqian.bms.sns.balance.bean;

import java.math.BigDecimal;

public class ResultStatistic extends ResultBase{
	/** 批次编号 */
	private String batchId;
	/** 对账日期 */
	private String balDate;
	/** 会计日期 */
	private String workDate;
	/** 系统：REDENV：红包，SEVEN:七分钱 */
	private String system;
	/** 交易结果 */
	private String transStatus;
	/** 统计类型：支付PAYMENT/充值RECHARGE/退款REFUND */
	private String transType;
	/** 总数 */
	private String totalCount;
	/** 总金额 */
	private BigDecimal totalAmt;
	/** 自身存疑总数 */
	private String selfDoubtCount;
	/** 自身存疑总金额 */
	private BigDecimal selfDoubtAmt;
	/** 对账存疑总数 */
	private String balDoubtCount;
	/** 对账存疑总额 */
	private BigDecimal balDoubtAmt;
	/** 对账差错总数 */
	private String balErrorCount;
	/** 对账差错总额 */
	private BigDecimal balErrorAmt;
	/** 对账一致总数 */
	private String balEqualCount;
	/** 对账一致总额 */
	private BigDecimal balEqualAmt;


	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBalDate() {
		return balDate;
	}

	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getSelfDoubtCount() {
		return selfDoubtCount;
	}

	public void setSelfDoubtCount(String selfDoubtCount) {
		this.selfDoubtCount = selfDoubtCount;
	}

	public BigDecimal getSelfDoubtAmt() {
		return selfDoubtAmt;
	}

	public void setSelfDoubtAmt(BigDecimal selfDoubtAmt) {
		this.selfDoubtAmt = selfDoubtAmt;
	}

	public String getBalDoubtCount() {
		return balDoubtCount;
	}

	public void setBalDoubtCount(String balDoubtCount) {
		this.balDoubtCount = balDoubtCount;
	}

	public BigDecimal getBalDoubtAmt() {
		return balDoubtAmt;
	}

	public void setBalDoubtAmt(BigDecimal balDoubtAmt) {
		this.balDoubtAmt = balDoubtAmt;
	}

	public String getBalErrorCount() {
		return balErrorCount;
	}

	public void setBalErrorCount(String balErrorCount) {
		this.balErrorCount = balErrorCount;
	}

	public BigDecimal getBalErrorAmt() {
		return balErrorAmt;
	}

	public void setBalErrorAmt(BigDecimal balErrorAmt) {
		this.balErrorAmt = balErrorAmt;
	}

	public String getBalEqualCount() {
		return balEqualCount;
	}

	public void setBalEqualCount(String balEqualCount) {
		this.balEqualCount = balEqualCount;
	}

	public BigDecimal getBalEqualAmt() {
		return balEqualAmt;
	}

	public void setBalEqualAmt(BigDecimal balEqualAmt) {
		this.balEqualAmt = balEqualAmt;
	}

}
