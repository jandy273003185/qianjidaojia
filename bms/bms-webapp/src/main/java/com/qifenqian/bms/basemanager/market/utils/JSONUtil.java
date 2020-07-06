package com.qifenqian.bms.basemanager.market.utils;

import java.util.Date;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

public class JSONUtil {

	/**
	 * * JSON 时间解析器 ，注：此方法功能是将java中的java.util.Date类型转换为JSON中的时间字符串
	 * 
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig dateConfigJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(datePattern));

		return jsonConfig;
	}

	static DateMorpher dp3 = new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
	static{
		JSONUtils.getMorpherRegistry().registerMorpher(dp3);
	}
	
	/**
	 * 将JSON集合字符串转换为java对象集合，注：此方法功能是将JSON字符串中的时间转换为java.util.Date类型
	 * 
	 * @param jsonArray
	 * @param obj
	 * @param datePattern
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> List<T> jsonArrayToList(String jsonArray, Object obj, String datePattern) {
		return JSONArray.toList(JSONArray.fromObject(jsonArray), obj, dateConfigJson(datePattern));
	}

	/**
	 * 将JSON集合转换为java对象集合，注：此方法功能是将JSON中的时间转换为java.util.Date类型
	 * 
	 * @param jsonArray
	 * @param obj
	 * @param datePattern
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> List<T> jsonArrayToList(JSONArray jsonArray, Object obj, String datePattern) {
		return JSONArray.toList(jsonArray, obj, dateConfigJson(datePattern));
	}
}