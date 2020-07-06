package com.qifenqian.bms.basemanager.merchant.bean;

import java.util.Date;

/**
* 
*/
public class TdCertificateAuth {

	/**
	 * pk主键(审核流水id)
	 */
    private Integer authId;

	/**
	 * 客户编号
	 */
    private String custId;

	/**
	 * 实名认证状态: 0 审核通过  1 待审核  2 审核不通过
9 用户取消认证
	 */
    private String certificateState;

	/**
	 * 审核人工号
	 */
    private String certifyUser;

	/**
	 * 审核时间
	 */
    private Date certifyDatetime;

	/**
	 * 审核结果代码
	 */
    private String authResultCode;

	/**
	 * 审核情况描述
	 */
    private String authResultDesc;

	/**
	 * 创建人
	 */
    private String createId;

	/**
	 * 创建时间
	 */
    private Date createTime;

	/**
	 * 修改人
	 */
    private String modifyId;

	/**
	 * 修改时间
	 */
    private Date modifyTime;
    
    private String updateTime;
    
    private String custName;
    
    private String roleId;
    
    private String addTime;
   
    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(String certificateState) {
        this.certificateState = certificateState;
    }

    public String getCertifyUser() {
        return certifyUser;
    }

    public void setCertifyUser(String certifyUser) {
        this.certifyUser = certifyUser;
    }

    public Date getCertifyDatetime() {
        return certifyDatetime;
    }

    public void setCertifyDatetime(Date certifyDatetime) {
        this.certifyDatetime = certifyDatetime;
    }

    public String getAuthResultCode() {
        return authResultCode;
    }

    public void setAuthResultCode(String authResultCode) {
        this.authResultCode = authResultCode;
    }

    public String getAuthResultDesc() {
        return authResultDesc;
    }

    public void setAuthResultDesc(String authResultDesc) {
        this.authResultDesc = authResultDesc;
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

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
   
}
