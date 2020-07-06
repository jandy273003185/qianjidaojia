package com.qifenqian.bms.basemanager.dictData.bean;

/***
 * 数据字典对象
 * @author ganzheng.ge
 *
 */
public class DictData {
	private String id;// 主键id
	private String name;// 字典名字
	private String dictPath;// 字典路径
	private String dictType;// 字典类型
	private String dataType;// 数据类型
	private String level;// 级别
	private String value;// 字典值
	private String orderBy;// 序号
	private String description;// 字典描述
	private String role;// 角色
	private String language;// 语言
	private String creator;// 创建者
	private String createTime;// 创建时间
	private String lastupdateUser;// 最后更新人
	private String lastupdateTime;// 最后更新时间
	private String status;// 字典状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDictPath() {
		return dictPath;
	}

	public void setDictPath(String dictPath) {
		this.dictPath = dictPath;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastupdateUser() {
		return lastupdateUser;
	}

	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}

	public String getLastupdateTime() {
		return lastupdateTime;
	}

	public void setLastupdateTime(String lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
