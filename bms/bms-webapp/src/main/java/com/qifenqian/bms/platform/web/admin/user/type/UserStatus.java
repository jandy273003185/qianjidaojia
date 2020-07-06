package com.qifenqian.bms.platform.web.admin.user.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * 用户状态
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserStatus.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public enum UserStatus {

	/**
	 * 生效
	 */
	@Description("生效")
	VALID,
	
	/**
	 * 冻结
	 */
	@Description("冻结")
	FREEZE,
	
	/**
	 * 离职
	 */
	@Description("离职")
	LEAVE,
	
	/**
	 * 已登陆
	 */
	@Description("已登陆")
	LOGIN;
	
	/**	获取描述性内容	*/
	public String getDesc() {
		Class<?> clazz = this.getClass();
		Field filed = null;
		try {
			filed = clazz.getField(this.name());
		} catch (Exception e) {
		}
		Description description = filed.getAnnotation(Description.class);
		if (description == null) {
			return null;
		} else {
			return description.value();
		}
	}
}


