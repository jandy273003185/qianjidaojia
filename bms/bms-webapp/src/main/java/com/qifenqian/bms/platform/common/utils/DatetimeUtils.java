package com.qifenqian.bms.platform.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 日期时间工具类
 */
public class DatetimeUtils {

  private static Logger logger = Logger.getLogger(DatetimeUtils.class.getName());

  // 不采用全局变量的方式，保证高并发情况下的准确性
  // private static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
  // private static final SimpleDateFormat SDF_yyyyMMddHHmmssSSS = new
  // SimpleDateFormat("yyyyMMddHHmmssSSS");
  // private static final SimpleDateFormat SDF_yyyyMMddHH =new SimpleDateFormat("yyyyMMddHH");
  // private static final SimpleDateFormat SDF_mmssSSS = new SimpleDateFormat("mmssSSS");

  /**
   * 当前日期
   * 
   * @return Date
   */
  public static Date now() {
    return new Date();
  }

  /**
   * 当前日期
   * 
   * @return yyyyMMdd(8)
   */
  public static String shortDate() {
    return new SimpleDateFormat("yyyyMMdd").format(now());
  }

  /**
   * 日期转换
   * 
   * @return yyyyMMdd(8)
   */
  public static String shortDate(Date date) {
    return new SimpleDateFormat("yyyyMMdd").format(date);
  }

  /**
   * 当前时间
   * 
   * @return yyyyMMddHHmmssSSS(17)
   */
  public static String datetime() {
    return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now());
  }

  /**
   * 时间转换
   * 
   * @return yyyyMMddHHmmssSSS(17)
   */
  public static String datetime(Date date) {
    return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
  }

  /**
   * 时间转换
   * 
   * @return yyyyMMddHH(10)
   */
  public static String dateHour(Date date) {
    return new SimpleDateFormat("yyyyMMddHH").format(date);
  }

  /**
   * 时间转换
   * 
   * @return HHMMSS(10)
   */
  public static String currHour() {
    return new SimpleDateFormat("HHmmss").format(now());
  }

  /**
   * 时间转换
   * 
   * @return mmssSSS(7)
   */
  public static String dateMillis(Date date) {
    return new SimpleDateFormat("mmssSSS").format(date);
  }

  /**
   * 时间转换
   * 
   * @return HHmmss(7)
   */
  public static String currentShortTime() {
    return new SimpleDateFormat("HHmmss").format(new Date());
  }

  /**
   * 判断字符串的日期是否有效
   * 
   * @param value
   * @param format
   * @return
   */
  public static boolean isValidDatetime(String value, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      sdf.parse(value);
    } catch (ParseException e) {
      logger.log(Level.WARNING, "以格式[" + format + "]解析日期[" + value + "]时异常:" + e.getMessage(), e);
      return false;
    }
    return true;
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

  public static void main(String[] args) {
    System.out.println(DatetimeUtils.datetime());;
  }

}
