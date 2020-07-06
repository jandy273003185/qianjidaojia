package com.qifenqian.bms.platform.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{

	public static final String getDate(int i)
	{
		String as[] = { "yyyyMM", "yyyyMMddHHmmssSSS", "yyyyMMddHHmmss", "yyMMddHHmmss", "yyyyMMdd", "yyyy-MM-dd", "HHmmssSSS", "HHmmss" };
		SimpleDateFormat simpledateformat = null;
		try
		{
			simpledateformat = new SimpleDateFormat(as[i]);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();

			return exception.toString();
		}
		return simpledateformat.format(new Date());
	}
	
	public static String getDateString(String format){
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		String resultDate = simpledateformat.format(new Date());
		return resultDate;
	}
	public static Date getAddDate(Date now,int addDate){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE,addDate);
		return cal.getTime();
	}
	
	public static String removeZero(double val)
	{
		long temp = Math.round(val * 100); // 四舍五入到分
		long integer = temp / 100;
		return "" + integer;
	}

	public static boolean isValidDate(String s)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			dateFormat.parse(s);
			return true;
		}
		catch (Exception e)
		{
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static String remvoeNull(String str)
	{
		if (str == null)
		{
			return "";
		}
		else
		{
			return str;
		}
	}

	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches())
		{
			return false;
		}
		return true;
	}

	public static double parserStr2Dbl(String str)
	{
		if (str == null || "".equals(str))
		{
			return 0;
		}
		else
		{
			return Double.parseDouble(str);
		}
	}

	public static String formatNum2Int(Double dbl)
	{
		if (dbl == null)
		{
			return "";
		}
		DecimalFormat df1 = new DecimalFormat("########");
		return df1.format(dbl);
	}

	public static String formatNum(Double dbl)
	{
		if (dbl == null)
		{
			return "";
		}
		DecimalFormat df1 = new DecimalFormat("#######0.00");
		return df1.format(dbl);
	}

	public static String formatNum(String dbl)
	{
		if (dbl == null || "".equals(dbl) || "0".equals(dbl) || "0.00".equals(dbl))
		{
			return "0.00";
		}
		else
		{
			DecimalFormat df1 = new DecimalFormat("#######0.00");
			double temp = Double.parseDouble(dbl);
			return df1.format(temp);
		}
	}
	
	
	/**
	 * 替换字符串为特殊字符
	 * @param str 需要替换的字符串
	 * @param beginIndex 开始位置
	 * @param endIndex 结束位置
	 * @param sign 替换的特殊字符
	 * @return
	 */
	public static String replaceSign(String str,int beginIndex,int endIndex,String sign)
	{
		StringBuffer result = new StringBuffer("");
		if(null != str && !"".equals(str))
		{
			int len = str.length();
			if(beginIndex > len || endIndex > len || beginIndex >= endIndex || beginIndex <= 0
					|| null == sign || "".equals(sign))
			{
				//若参数超过范围，则返回原值
				result.append(str);
			}
			else
			{
				result.append(str.substring(0,beginIndex-1));
				result.append(str.substring(beginIndex-1,endIndex-1).replaceAll("\\w",sign));
				result.append(str.substring(endIndex-1,len));
			}
		}
		
		return result.toString();
	}

}
