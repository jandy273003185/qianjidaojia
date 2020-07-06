package com.qifenqian.bms.platform.web.admin.dept.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * 部门状态
 * 
 * @project gyzb-platform-web-admin
 * @fileName DeptStatus.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public enum DeptStatus {

	/**
	 * 生效
	 */
	@Description("生效")
	VALID,
	
	/**
	 * 失效
	 */
	@Description("失效")
	DISABLE;
	
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


