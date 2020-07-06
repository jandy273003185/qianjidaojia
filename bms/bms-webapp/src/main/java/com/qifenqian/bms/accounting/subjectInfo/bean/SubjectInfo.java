package com.qifenqian.bms.accounting.subjectInfo.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubjectInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5535086109807078690L;
	/**
	 * 科目编号
	 */
	private String subjectId;
	
	private Integer id;
	

	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 科目类型
	 */
	private String subjectType;
	/**
	 * 科目余额
	 */
	private BigDecimal subjectAmt;
	/**
	 * 科目级别
	 */
	private String subjectLevel;
	/**
	 * 科目代码
	 */
	private String subjectCode;
	/**
	 * 父级科目编号
	 */
	private String parentCode;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 科目状态（状态：VALID有效/DISABLE失效）
	 */
	private String state;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 最后更新人
	 */
	private String lastUpdateUser;
	/**
	 * 最后更新时间
	 */
	private String lastUpdateTime;
	/**
	 * 币别
	 */
	private String currCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public BigDecimal getSubjectAmt() {
		return subjectAmt;
	}

	public void setSubjectAmt(BigDecimal subjectAmt) {
		this.subjectAmt = subjectAmt;
	}

	public String getSubjectLevel() {
		return subjectLevel;
	}

	public void setSubjectLevel(String subjectLevel) {
		this.subjectLevel = subjectLevel;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}



	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
}