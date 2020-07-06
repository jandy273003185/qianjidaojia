package com.sevenpay.agentmanager.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期时间工具类
 */
public class DatetimeUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DatetimeUtils.class);

	// 不采用全局变量的方式，保证高并发情况下的准确性
//	private static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
//	private static final SimpleDateFormat SDF_yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//	private static final SimpleDateFormat SDF_yyyyMMddHH =new SimpleDateFormat("yyyyMMddHH");
//	private static final SimpleDateFormat SDF_mmssSSS = new SimpleDateFormat("mmssSSS");
	
	/**
	 * 当前日期
	 * @return Date
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 当前日期
	 * @return yyyyMMdd(8)
	 */
	public static String shortDate() {
		return new SimpleDateFormat("yyyyMMdd").format(now());
	}

	/**
	 * 日期转换
	 * @return yyyyMMdd(8)
	 */
	public static String shortDate(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 当前时间
	 * @return yyyyMMddHHmmssSSS(17)
	 */
	public static String datetime() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now());
	}
	
	/**
	 * 当前时间 （秒）
	 * @return
	 */
	public static String dateSecond() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(now());
	}
	
	/**
	 * 时间转换
	 * @return yyyyMMddHHmmssSSS(17)
	 */
	public static String datetime(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}
	
	/**
	 * 时间转换
	 * @return yyyyMMddHH(10)
	 */
	public static String dateHour(Date date){
		return new SimpleDateFormat("yyyyMMddHH").format(date);
	}
	
	/**
	 * 时间转换
	 * @return mmssSSS(7)
	 */
	public static String dateMillis(Date date){
		return new SimpleDateFormat("mmssSSS").format(date);
	}
	
	/**
	 * 判断字符串的日期是否有效
	 * @param value
	 * @param format
	 * @return
	 */
	public static boolean isValidDatetime(String value, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(value);
			
		} catch (ParseException e) {
			logger.error("以格式[" + format + "]解析日期[" + value + "]时异常:" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	public static Date stringForDate(String value, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 格式化日期时间
	 * 
	 * @param date 待格式化的日期
	 * @param format 格式化模板
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String getLastWorkDate() {
		Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String time = matter1.format(as);
		return time;
	}
			
}
