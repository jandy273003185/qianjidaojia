package com.qifenqian.bms.platform.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程缓存工具类
 * @project gyzb-platform-common
 * @fileName ThreadUtils.java
 * @author huiquan.ma
 * @date 2015年12月3日
 * @memo
 */
public class ThreadUtils {

	/**
	 * 静态内部类,利用ThreadLocal模拟线程变量
	 */

	/**	模拟线程缓存	*/
	private static ThreadLocal<Map<String, Object>> data = new ThreadLocal<Map<String, Object>>();

	/**
	 * 在当前线程上下文中存放一个键值对
	 * @param name
	 * @param value
	 */
	public static void put(String key, Object value) {
		if(null == data.get()) {
			data.set(new HashMap<String, Object>());
		}
		Map<String, Object> map = data.get();
		map.put(key, value);
	}

	/**
	 * 从当前线程上线文中查找变量
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Map<String, Object> map = data.get();
		if(null == map) {
			return null;
		}
		return map.get(key);
	}
	
	/**
	 * 利用泛型提前强转类型的get方法
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) {
		Map<String, Object> map = data.get();
		if(null == map) {
			return null;
		}
		return (T) map.get(key);
	}
	
	/**
	 * 从当前线程上线文中清除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		Map<String, Object> map = data.get();
		if(null == map) {
			return;
		}
		map.remove(key);
	}
	
	/**
	 * 从当前线程上线文中清除所有变量
	 * @return
	 */
	public static void clearAll() {
		data.remove();
	}

}