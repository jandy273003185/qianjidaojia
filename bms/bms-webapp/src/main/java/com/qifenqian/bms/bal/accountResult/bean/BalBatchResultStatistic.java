package com.qifenqian.bms.bal.accountResult.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
* 对账--对账结果统计表
*/
public class BalBatchResultStatistic extends ResultStatisticBase{

	/**
	 * 编号
	 */
    private Integer statisticId;

	/**
	 * 批次编号
	 */
    private String batchId;

	/**
	 * 对账日期
	 */
    private String balDate;

	/**
	 * 会计日期
	 */
    private String workDate;

	/**
	 * 文件编号
	 */
    private String fileId;

	/**
	 * 渠道编号
	 */
    private Integer channelId;

	/**
	 * 数据方：EXTERNAL/SEVENMALL
	 */
    private String dataOwner;

	/**
	 * 交易结果
	 */
    private String transStatus;

	/**
	 * 统计类型：PRECHARGE充值
	 */
    private String transType;

	/**
	 * 币别
	 */
    private String transCurrency;

	/**
	 * 总数
	 */
    private Integer totalCount;

	/**
	 * 总金额
	 */
    private BigDecimal totalAmt;

	/**
	 * 对账一致总数
	 */
    private Integer balEqualCount;

	/**
	 * 对账一致总额
	 */
    private BigDecimal balEqualAmt;

	/**
	 * 对账存疑总数
	 */
    private Integer balDoubtCount;

	/**
	 * 对账存疑总额
	 */
    private BigDecimal balDoubtAmt;

	/**
	 * 对账差错总数
	 */
    private Integer balErrorCount;

	/**
	 * 对账差错总额
	 */
    private BigDecimal balErrorAmt;

	/**
	 * 自身存疑总数
	 */
    private Integer selfDoubtCount;

	/**
	 * 自身存疑总金额
	 */
    private BigDecimal selfDoubtAmt;

	/**
	 * 写入时间
	 */
    private Date instDatetime;

	/**
	 * 状态
	 */
    private String status;

	/**
	 * 备注
	 */
    private String memo;

	/**
	 * 返回更新时间
	 */
    private Date updateDatetime;
    
    private String channelName;
    
    

    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(Integer statisticId) {
        this.statisticId = statisticId;
    }

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getDataOwner() {
        return dataOwner;
    }

    public void setDataOwner(String dataOwner) {
        this.dataOwner = dataOwner;
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

    public String getTransCurrency() {
        return transCurrency;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Integer getBalEqualCount() {
        return balEqualCount;
    }

    public void setBalEqualCount(Integer balEqualCount) {
        this.balEqualCount = balEqualCount;
    }

    public BigDecimal getBalEqualAmt() {
        return balEqualAmt;
    }

    public void setBalEqualAmt(BigDecimal balEqualAmt) {
        this.balEqualAmt = balEqualAmt;
    }

    public Integer getBalDoubtCount() {
        return balDoubtCount;
    }

    public void setBalDoubtCount(Integer balDoubtCount) {
        this.balDoubtCount = balDoubtCount;
    }

    public BigDecimal getBalDoubtAmt() {
        return balDoubtAmt;
    }

    public void setBalDoubtAmt(BigDecimal balDoubtAmt) {
        this.balDoubtAmt = balDoubtAmt;
    }

    public Integer getBalErrorCount() {
        return balErrorCount;
    }

    public void setBalErrorCount(Integer balErrorCount) {
        this.balErrorCount = balErrorCount;
    }

    public BigDecimal getBalErrorAmt() {
        return balErrorAmt;
    }

    public void setBalErrorAmt(BigDecimal balErrorAmt) {
        this.balErrorAmt = balErrorAmt;
    }

    public Integer getSelfDoubtCount() {
        return selfDoubtCount;
    }

    public void setSelfDoubtCount(Integer selfDoubtCount) {
        this.selfDoubtCount = selfDoubtCount;
    }

    public BigDecimal getSelfDoubtAmt() {
        return selfDoubtAmt;
    }

    public void setSelfDoubtAmt(BigDecimal selfDoubtAmt) {
        this.selfDoubtAmt = selfDoubtAmt;
    }

    public Date getInstDatetime() {
        return instDatetime;
    }

    public void setInstDatetime(Date instDatetime) {
        this.instDatetime = instDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
