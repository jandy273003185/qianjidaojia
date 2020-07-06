package com.qifenqian.bms.bal.accountResult.bean;

import java.util.Date;

/**
* 对账-外部系统源数据表
*/
public class BalExternalData {

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
    
    private String channelName;
    private String details;
    
    

    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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
}
