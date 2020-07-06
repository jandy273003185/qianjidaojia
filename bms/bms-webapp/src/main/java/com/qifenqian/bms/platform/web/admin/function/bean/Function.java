package com.qifenqian.bms.platform.web.admin.function.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qifenqian.bms.platform.web.admin.function.type.AccessType;
import com.qifenqian.bms.platform.web.admin.function.type.IsMenu;
import com.qifenqian.bms.platform.web.admin.function.type.IsValid;

/**
 * 功能bean
 *
 * @project gyzb-platform-web-admin
 * @fileName Function.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class Function implements Serializable {

	private static final long serialVersionUID = -3223838669643833227L;

	/** 功能编号 */
	private int functionId;
	/** 功能代码 */
	private String functionCode;
	/** 功能名称 */
	private String functionName;
	/** 上级编号 */
	private String parentFunctionId;
	/** 功能级别 */
	private int functionLevel;
	/** URL */
	private String functionUrl;
	/** 页面URL */
	private String routePage;
	/** 功能排序 */
	private int functionOrder;
	/** 是否是菜单Y是/N否 */
	private IsMenu isMenu;
	/** 是否有效Y有效，N无效 */
	private IsValid isValid;
	/** 是否要授权，Y需要，N不需要 */
	private AccessType accessType;
	/** 图标样式 */
	private String iconClass;
	/** 备注 */
	private String memo;
	/** 初始写入人 */
	private int instUser;
	/** 初始时间：YYYY-MM-DD HH:MI:SS */
	private Date instDatetime;
	/** 最后更改人 */
	private int lupdUser;
	/** 最后更改时间：YYYY-MM-DD HH:MI:SS */
	private Date lupdDatetime;

	private String instUserName;
	private String lupdUserName;

	/** 子功能列表 */
	private List<Function> subFunctionList;
	/** 父功能列表 */
	private List<Function> parentFunctionList;

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public int getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(int functionLevel) {
		this.functionLevel = functionLevel;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public int getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(int functionOrder) {
		this.functionOrder = functionOrder;
	}

	public IsMenu getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(IsMenu isMenu) {
		this.isMenu = isMenu;
	}

	public IsValid getIsValid() {
		return isValid;
	}

	public void setIsValid(IsValid isValid) {
		this.isValid = isValid;
	}

	public AccessType getAccessType() {
		return accessType;
	}

	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getInstUser() {
		return instUser;
	}

	public void setInstUser(int instUser) {
		this.instUser = instUser;
	}

	public int getLupdUser() {
		return lupdUser;
	}

	public void setLupdUser(int lupdUser) {
		this.lupdUser = lupdUser;
	}

	public List<Function> getSubFunctionList() {
		return subFunctionList;
	}

	public void setSubFunctionList(List<Function> subFunctionList) {
		this.subFunctionList = subFunctionList;
	}

	public List<Function> getParentFunctionList() {
		return parentFunctionList;
	}

	public void setParentFunctionList(List<Function> parentFunctionList) {
		this.parentFunctionList = parentFunctionList;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public Date getLupdDatetime() {
		return lupdDatetime;
	}

	public void setLupdDatetime(Date lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

	public String getLupdUserName() {
		return lupdUserName;
	}

	public void setLupdUserName(String lupdUserName) {
		this.lupdUserName = lupdUserName;
	}

	public String getRoutePage() {
		return routePage;
	}

	public void setRoutePage(String routePage) {
		this.routePage = routePage;
	}
}
