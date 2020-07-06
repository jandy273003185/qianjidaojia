package com.qifenqian.bms.basemanager.merchant.bean;

import java.util.Date;

/**
* 后台管理系统-协议表
*/
public class BmsProtocolContent {

	/**
	 * 编号
	 */
    private String id;

	/**
	 * 商户编号
	 */
    private String custId;

	/**
	 * 模板编号
	 */
    private String templateId;

	/**
	 * 协议内容
	 */
    private String protocolContent;

	/**
	 * 状态 生效VALID/失效DISABLE
	 */
    private String status;

	/**
	 * 备注
	 */
    private String memo;

	/**
	 * 写入人
	 */
    private String instUser;

	/**
	 * 写入时间
	 */
    private Date instDatetime;

	/**
	 * 更新人
	 */
    private String updateUser;

	/**
	 * 更新时间
	 */
    private Date updateDatetime;

	/**
	 * 
	 */
    private String protocolName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getProtocolContent() {
        return protocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
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

    public String getInstUser() {
        return instUser;
    }

    public void setInstUser(String instUser) {
        this.instUser = instUser;
    }

    public Date getInstDatetime() {
        return instDatetime;
    }

    public void setInstDatetime(Date instDatetime) {
        this.instDatetime = instDatetime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }
}
