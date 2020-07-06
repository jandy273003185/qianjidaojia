package com.qifenqian.bms.basemanager.merchant.bean;


import java.io.Serializable;
import java.util.Date;

public class TdLoginUserInfo implements Serializable
{

	private static final long serialVersionUID = 6324768503483274189L;

	/** 
	 * 客户编号，自动编号，规则：user+年+月+日+时+分+秒+8位随机数.
	 */
	private String custId;

	
	/** 
	 * 角色ID：per 个人角色,ent 企业角色.
	 */
	private String roleId;

	/** 
	 * 登录密码，规则MD5(客户ID+pwd明文+附加串).
	 */
	private String loginPwd;

	/** 
	 * 手机号码.
	 */
	private String mobile;

	/** 
	 * EMAIL地址.
	 */
	private String email;

	/** 
	 * 状态：00 正常；01 停用；02 登录账户冻结.
	 */
	private String state;

	/** 
	 * 连续输入错误密码次数，超过3次账户自动锁定，三小时后自动解锁。或通过手机找回密码重置后马上解锁.
	 */
	private Short wrongPwdCount;

	/** 
	 * 是否开启绑定手机登录，如开启，则登录时除密码外，还需输入手机验证码
	           1开启，0不开启.
	 */
	private String isBandMobileForLogin;

	/** 
	 * 在线状态：0 离线，1在线.
	 */
	private Short onlineState;

	/** 
	 * 最后一次登录时间.
	 */
	private Date lastLoginTime;

	/** 
	 * 附加串，用于生成加密密码.
	 */
	private String attachStr;

	/** 
	 * 创建人.
	 */
	private String createId;

	/** 
	 * 创建时间.
	 */
	private Date createTime;

	/** 
	 * 修改人.
	 */
	private String modifyId;

	/** 
	 * 修改时间.
	 */
	private Date modifyTime;



	public TdLoginUserInfo()
	{
	}

	public TdLoginUserInfo(String custId, String roleId, String loginPwd, Date createTime, Date modifyTime)
	{
		this.custId = custId;
		this.roleId = roleId;
		this.loginPwd = loginPwd;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	public TdLoginUserInfo(String custId, String roleId, String loginPwd, String mobile, String email, String state, Short wrongPwdCount, String isBandMobileForLogin, Short onlineState,
			Date lastLoginTime, String attachStr, String createId, Date createTime, String modifyId, Date modifyTime)
	{
		this.custId = custId;
		this.roleId = roleId;
		this.loginPwd = loginPwd;
		this.mobile = mobile;
		this.email = email;
		this.state = state;
		this.wrongPwdCount = wrongPwdCount;
		this.isBandMobileForLogin = isBandMobileForLogin;
		this.onlineState = onlineState;
		this.lastLoginTime = lastLoginTime;
		this.attachStr = attachStr;
		this.createId = createId;
		this.createTime = createTime;
		this.modifyId = modifyId;
		this.modifyTime = modifyTime;
	}

	public String getCustId()
	{
		return this.custId;
	}

	public void setCustId(String custId)
	{
		this.custId = custId;
	}

	public String getRoleId()
	{
		return this.roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getLoginPwd()
	{
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd)
	{
		this.loginPwd = loginPwd;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Short getWrongPwdCount()
	{
		return this.wrongPwdCount;
	}

	public void setWrongPwdCount(Short wrongPwdCount)
	{
		this.wrongPwdCount = wrongPwdCount;
	}

	public String getIsBandMobileForLogin()
	{
		return this.isBandMobileForLogin;
	}

	public void setIsBandMobileForLogin(String isBandMobileForLogin)
	{
		this.isBandMobileForLogin = isBandMobileForLogin;
	}

	public Short getOnlineState()
	{
		return this.onlineState;
	}

	public void setOnlineState(Short onlineState)
	{
		this.onlineState = onlineState;
	}

	public Date getLastLoginTime()
	{
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public String getAttachStr()
	{
		return this.attachStr;
	}

	public void setAttachStr(String attachStr)
	{
		this.attachStr = attachStr;
	}

	public String getCreateId()
	{
		return this.createId;
	}

	public void setCreateId(String createId)
	{
		this.createId = createId;
	}

	public Date getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getModifyId()
	{
		return this.modifyId;
	}

	public void setModifyId(String modifyId)
	{
		this.modifyId = modifyId;
	}

	public Date getModifyTime()
	{
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime)
	{
		this.modifyTime = modifyTime;
	}

}
