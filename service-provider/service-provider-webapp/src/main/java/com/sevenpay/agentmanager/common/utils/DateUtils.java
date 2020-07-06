package com.sevenpay.agentmanager.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @project gyzb-platform-common
 * @fileName DateUtils.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
public class DateUtils {

	/**
	 * 获取当前系统日期(10位) 格式为:yyyy-MM-dd
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 获取当前系统日期(10位),并且在此日期上面进行天数的加减 格式为:yyyy-MM-dd
	 * 
	 * @param diff
	 *            天数差额,传入-1得到前一天,传入1得到后一天,以此类推
	 * @return 字符串日期
	 */
	public static String getDateStr(int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, diff);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 获取当前系统日期(8位),并且在此日期上面进行天数的加减 格式为:yyyyMMdd
	 * 
	 * @param diff
	 *            天数差额,传入-1得到前一天,传入1得到后一天,以此类推
	 * @return 字符串日期
	 */
	public static String getDateStrNo(int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, diff);
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 根据指点日期和天数，返回几天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getDateStrNo(Date date, int days) {

		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DAY_OF_YEAR, days);
		Date dayAfter = cl.getTime();
		return getDateStr8(dayAfter);
	}

	/**
	 * 将传入的日期转为yyyy-MM-dd的格式
	 * 
	 * @return
	 */
	public static String getDateStr10(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 获取日期时间(8位) 格式为:yyyyMMdd
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateStr8(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 获取系统日期时间(8位) 格式为:yyyyMMdd
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateStr8() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	/**
	 * 获取系统日期时间(8位) 格式为:yyyyMMdd
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateStrNO() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	/**
	 * 获取期时间(8位) 格式为:yyyyMMdd
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateStrNO(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 根据年月日返回"YYYY-MM-DD"格式
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getDateStr(int year, int month, int day) {

		Calendar cl = Calendar.getInstance();
		cl.set(year, 0, 1);
		cl.add(Calendar.MONTH, (month - 1));
		cl.add(Calendar.DATE, (day - 1));
		year = cl.get(Calendar.YEAR);
		month = cl.get(Calendar.MONTH) + 1;
		day = cl.get(Calendar.DATE);
		String date = String.valueOf(year * 10000 + month * 100 + day);
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}

	/**
	 * 根据指点日期和天数，返回几天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getDateStr(Date date, int days) {

		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DAY_OF_YEAR, days);
		Date dayAfter = cl.getTime();
		return getDateStr10(dayAfter);
	}

	/**
	 * 获取系统日期时间（字符串格式）
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 获取系统日期时间（字符串格式）
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateTimeStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 不带下划线
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateTimeStrNo() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 不带下划线
	 * 
	 * @return 字符串日期时间
	 */
	public static String getDateTimeStrNo(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	/**
	 * 长度20
	 * @return 字符串日期时间 + random
	 */
	public static String getDateTimeOpId() {
		return new SimpleDateFormat("MMddHHmmss").format(new Date())+RandomStringUtils.randomAlphanumeric(10);
	}

	/**
	 * 获取系统当前时间 格式为:HH:mm:ss
	 * 
	 * @return the system time
	 */
	public static String getTimeStr() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	/**
	 * 获取距离当前时间[时/分/秒]偏移的时间格式
	 * 
	 * @param hour
	 *            时,负数代表时间往前
	 * @param minute
	 *            分,负数代表时间往前
	 * @param second
	 *            秒,负数代表时间往前
	 * @return
	 */
	public static String getTimeStr(int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);
		Date d = c.getTime();
		return new SimpleDateFormat("HH:mm:ss").format(d);
	}

	/**
	 * 获取系统当前时间 格式为HHmmss
	 * 
	 * @return the system time
	 */
	public static String getTimeStrNo() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}

	/**
	 * 获取系统当前时间 格式为HHmmss
	 * 
	 * @return the system time
	 */
	public static String getTimeStrNo(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	/**
	 * 获取yyyyMMddHHmmssSSS格式字符串
	 * 
	 * @return 17位长度
	 */
	public static String getTimestampStrNo() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 获取毫秒级时间戳，具有防止重复时间戳的功能
	 */
	public static synchronized String getSysDateTimeToStrEX() {
		String str = "";
		try {
			// 先暂停 30 毫秒，防止出现获取到重复时间戳
			Thread.sleep(30);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date currentDate = new Date();
			str = dateFormat.format(currentDate);
		} catch (Exception e) {
			str = "";
		}
		return str;
	}

	/**
	 * 按照标准的格式yyyy-MM-dd HH:mm:ss进行解析日期
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseDate(String value) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("转换Date对象时异常,值为:" + value, e);
		}
	}

	/**
	 * 按照标准的格式yyyy-MM-dd进行解析日期
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseDate10(String value) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("转换Date对象时异常,值为:" + value, e);
		}
	}

	/**
	 * 按照标准的格式yyyyMMdd进行解析日期
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseDate8(String value) {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("转换Date对象时异常,值为:" + value, e);
		}
	}

	public static void main(String[] args) {
		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse("2012-04-17 00:00:01.0");
			System.out.println(date.getTime());
			Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse("2012-04-17 00:00:01.001002");
			System.out.println(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算两天的时间间隔,相邻两天返回1
	 * 
	 * @param date10_1
	 *            较小的日期
	 * @param date10_2
	 *            较大的日期
	 * @return 返回日期差
	 */
	public static int calcDayInterval_10(String date10_1, String date10_2) {
		Date d1 = DateUtils.parseDate10(date10_1);
		Date d2 = DateUtils.parseDate10(date10_2);
		return (int) ((d2.getTime() - d1.getTime()) / 1000 / 60 / 60 / 24);
	}

	/**
	 * 计算两天的时间间隔,相邻两天返回1
	 * 
	 * @param date8_1
	 *            较小的日期
	 * @param date8_2
	 *            较大的日期
	 * @return 返回日期差
	 */
	public static int calcDayInterval_8(String date8_1, String date8_2) {
		Date d1 = DateUtils.parseDate8(date8_1);
		Date d2 = DateUtils.parseDate8(date8_2);
		return (int) ((d2.getTime() - d1.getTime()) / 1000 / 60 / 60 / 24);
	}




	public static Date changeTime(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		Date formatData = (Date) sdf.parseObject(format);
        return formatData;
	}

}
