package com.qifenqian.bms.platform.web.admin.tags;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;

/**
 * 功能菜单操作相关
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionUtilsTag.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class FunctionUtilsTag {

	/**
	 * 判断源功能是否包含目标功能
	 * @param src
	 * @param target
	 * @return
	 */
	public static boolean pathHasFunc(Function src, Function target) {
		if(null == src || null == target) {
			return false;
		}
		if(src.getFunctionId() == target.getFunctionId()) {
			return true;
		}
		if(null == src.getParentFunctionList()) {
			return false;
		}
		for(Function func : src.getParentFunctionList()) {
			if(func.getFunctionId() == target.getFunctionId()) {
				return true;
			}
		}
		return false;
	}
}


