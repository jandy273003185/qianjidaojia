package com.qifenqian.bms.myworkspace;

import java.io.Serializable;
import java.util.Date;

/**
* 
*/
public class ActViewUrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5392753485198765881L;

	/**
	 * 
	 */
    private String describe;

	/**
	 * 
	 */
    private String createUser;

	/**
	 * 
	 */
    private Date createTime;

	/**
	 * 
	 */
    private String updateUser;

	/**
	 * 
	 */
    private Date updateTime;
    
    private String applicationName;

    private String url;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
