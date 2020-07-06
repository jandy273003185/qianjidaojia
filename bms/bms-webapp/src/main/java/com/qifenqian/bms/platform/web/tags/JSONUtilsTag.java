package com.qifenqian.bms.platform.web.tags;

import com.alibaba.fastjson.JSONObject;

/**
 * json相关工具
 * 
 * @project gyzb-platform-web
 * @fileName JSONUtilsTag.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class JSONUtilsTag {

	/**
	 * 转换为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		System.out.println(JSONObject.toJSONString(obj));
		return JSONObject.toJSONString(obj);
	}
}


