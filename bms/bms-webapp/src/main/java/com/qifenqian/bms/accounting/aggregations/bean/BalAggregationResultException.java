package com.qifenqian.bms.accounting.aggregations.bean;

import java.util.Date;

/**
* 中信对账结果存疑表
*/
public class BalAggregationResultException extends BalAggregationResultExceptionBase{
	
	/**
	 * 对账日期
	 */
	private String balDate;
	
	/**
	 * 异常编号
	 */
    private Integer exceptionId;

	/**
	 * 源数据编号
	 */
    private Integer dataId;

	/**
	 * 渠道编号
	 */
    private Integer channelId;

	/**
	 * 文件编号
	 */
    private String fileId;

	/**
	 * 序号/行号
	 */
    private Integer seq;

	/**
	 * 清算编号
	 */
    private String clearId;

	/**
	 * 会计日期(数据日期)
	 */
    private String workDate;

	/**
	 * 对账批次号
	 */
    private String batchId;

	/**
	 * 对账结果：SELF_DOUBT/BAL_DOUBT/BAL_ERROR
	 */
    private String balResult;

	/**
	 * 对账处理备注
	 */
    private String balMemo;

	/**
	 * 初始写入人
	 */
    private Integer instUser;

	/**
	 * 写入日期：YYYY-MM-DD
	 */
    private String instDate;

	/**
	 * 初始时间
	 */
    private Date instDatetime;

	/**
	 * 异常处理标记：挂账/抹账/销账
	 */
    private String dealFlag;

	/**
	 * 异常处理备注
	 */
    private String dealMemo;

	/**
	 * 异常处理人
	 */
    private Integer dealUser;

	/**
	 * 异常处理时间
	 */
    private Date dealDatetime;
    
    private String channelName;
    private String balResultName;
    
    
    
    public String getBalDate() {
		return balDate;
	}

	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}


    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getBalResultName() {
		return balResultName;
	}

	public void setBalResultName(String balResultName) {
		this.balResultName = balResultName;
	}

	public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBalResult() {
        return balResult;
    }

    public void setBalResult(String balResult) {
        this.balResult = balResult;
    }

    public String getBalMemo() {
        return balMemo;
    }

    public void setBalMemo(String balMemo) {
        this.balMemo = balMemo;
    }

    public Integer getInstUser() {
        return instUser;
    }

    public void setInstUser(Integer instUser) {
        this.instUser = instUser;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public Date getInstDatetime() {
        return instDatetime;
    }

    public void setInstDatetime(Date instDatetime) {
        this.instDatetime = instDatetime;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public String getDealMemo() {
        return dealMemo;
    }

    public void setDealMemo(String dealMemo) {
        this.dealMemo = dealMemo;
    }

    public Integer getDealUser() {
        return dealUser;
    }

    public void setDealUser(Integer dealUser) {
        this.dealUser = dealUser;
    }

    public Date getDealDatetime() {
        return dealDatetime;
    }

    public void setDealDatetime(Date dealDatetime) {
        this.dealDatetime = dealDatetime;
    }
}
