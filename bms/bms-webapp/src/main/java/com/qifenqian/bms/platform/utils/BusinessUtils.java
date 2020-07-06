package com.qifenqian.bms.platform.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.common.utils.InetUtils;

/**
 * 号码生成规则
 */
public class BusinessUtils {
	
	private static long CYC_NUM2 = 1;
	
	/** 循环值 上送交广科技订单编号 */
	private static long CYC_NUM4 = 1;
	
	/**
	 * 生成工号:U000001
	 */
	public static String getUserId(String number) {
		if (null == number || "".equals(number)) {
			return "U000001";
		}
		int num = Integer.parseInt(number.substring(number.length() - 6, number.length())) + 1;
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(6);
		String result = "U" + format.format(num).replace(",", "");
		return result;
	}

	/**
	 * 生成角色:101
	 */
	public static String getRoleId(String number) {
		if (null == number || "".equals(number)) {
			return "101";
		}
		String result = String.valueOf(Integer.parseInt(number) + 1);
		return result;
	}

	/**
	 * 生成部门编号:D0001
	 */
	public static String getDeptId(String number) {
		if (null == number || "".equals(number)) {
			return "D0001";
		}
		int num = Integer.parseInt(number.substring(number.length() - 4, number.length())) + 1;
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(4);
		String result = "D" + format.format(num).replace(",", "");
		return result;
	}

	/**
	 * 生成问题编号:1
	 */
	public static String getQuestionId(String number) {
		if (null == number || "".equals(number)) {
			return "1";
		}
		String result = String.valueOf(Integer.parseInt(number) + 1);
		return result;
	}

	/**
	 * 生成费率编号:F0001
	 */
	public static String getRuleId(String number) {

		if (null == number || "".equals(number)) {
			return "F0001";
		}
		int num = Integer.parseInt(number.substring(number.length() - 4, number.length())) + 1;
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(4);
		String result = "F" + format.format(num).replace(",", "");
		return result;
	}

	/**
	 * 生成省市编号:1
	 */
	public static int getCityId(int number) {
		if (number > 0) {
			int result = number + 1;
			return result;
		} else {
			return 1;
		}

	}


	/**
	 * 生成商户编号:M+营业执照
	 */
	public static String getMerchantId(String businessLicense) {
		
		if (null == businessLicense || "".equals(businessLicense)) {
			return "";
		}
		String merchantId = "M" + businessLicense;
		return merchantId;
	}
	
	/**
	 * 生成报文流水号【28位】 
	 * 
	 * @return  yyyyMMddHHmmssSSS(17) +ip(3) SEQ(8)
	 */
	public static String getMsgId() {
		
		String seqNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+InetUtils.getLastSplitIp3() 
				+ fillLeftWith0(String.valueOf(getCycNum2()), 8);
		
		return seqNo;
	}
	
	/**交广科技独用
	 * 使用“0”左填充至指定长度<br>
	 * @return
	 */
	private static String fillLeftWith0(String currSeq, int length) {
		currSeq = "00000000000000000000" + currSeq;
		
		// 长度超长则截取
		if (currSeq.length() > length) {
			currSeq = currSeq.substring(currSeq.length() - length);
		}
		return currSeq;
	}
	
	/**
	 * 获取自增长为 1，循环周期为 1000000000  的数值
	 * @return
	 */
	private synchronized static long getCycNum2() {
		CYC_NUM2 %= 1000000000;
		return CYC_NUM2++;
	}
	
	public static String getMsgId(String idName) {
		String seqNo = idName 
				+ InetUtils.getLastSplitIp3() 
				+ DatetimeUtils.datetime() 
				+ fillLeftWith0(String.valueOf(getCycNum2()), 8);
		return seqNo;
	}
	
	public static String getOrderId() {
		String currSeq = String.valueOf(getCycNum4());
		
		String seqNo = "P" 
				+ InetUtils.getLastSplitIp3() 
				+ DatetimeUtils.shortDate() 
				+ fillLeftWith0(currSeq, 8);
		
		return seqNo;
	}
	
	/**
	 * 交广科技独用 获取自增长为 1，循环周期为 1000000000  的数值
	 * @return
	 */
	private synchronized static long getCycNum4() {
		CYC_NUM4 %= 1000000000;
		
		return CYC_NUM4++;
	}
}
