package com.qifenqian.bms.platform.web.admin.user.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * 性别
 * @project gyzb-platform-web-admin
 * @fileName Sex.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public enum Sex {

	/**
	 * 男
	 */
	@Description("男")
	MEN,
	
	/**
	 * 女
	 */
	@Description("女")
	WOMEN;
	
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


