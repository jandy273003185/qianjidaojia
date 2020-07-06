package com.qifenqian.bms.basemanager.custInfo.bean;

import java.util.Date;

/**
* 客户密码修改流水表
*/
public class TbPasswordModifyLog {

	/**
	 * 修改请求流水号
	 */
    private String id;

	/**
	 * 客户编号
	 */
    private String custId;

	/**
	 * 操作人
	 */
    private String createId;

	/**
	 * 操作时间
	 */
    private Date createTime;

	/**
	 * 01失败；00成功；
	 */
    private String status;

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

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
