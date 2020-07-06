package com.qifenqian.bms.platform.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class GenSN
{

	/** 按长度要求生成一串随机数字*/
	public static String getRandomNum(int length)
	{
		String chose = "0123456789";
		char temp;
		StringBuffer random = new StringBuffer();
		Random rand = new Random();

		for (int i = 0; i < length; i++)
		{
			temp = chose.charAt(rand.nextInt(chose.length()));

			random.append(temp);
		}

		return random.toString();

	}

	/**
	 * 生成32位的UUID，不含横杠 
	 */
	public static String getSN()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 用户注册时生成注册用户ID 
	 */
	public static String getLoginID()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 登录日志流水ID 
	 */
	public static String getLoginLogID()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 操作日志流水ID 
	 */
	public static String getOperateID()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 生成form_token_id(防止form重复提交使用)
	 * 
	 */
	public static String getFormTokenId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getQuerySerialId(String userId)
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String returnStr = simpledateformat.format(new Date());
		return userId + returnStr + getRandomNum(6);
	}

	public static void main(String args[])
	{
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}

}
