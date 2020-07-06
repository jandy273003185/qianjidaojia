package com.sevenpay.agentmanager.common.utils;

public class StrCombineSplit {
	
	/**
	 * 按 空格 或逗号， 或分号进行拆分
	 * @param str
	 * @return
	 */
	public static String splitStr(String str) {

		StringBuffer sb = new StringBuffer();
		String[] temp = str.split("\\s+|,+|;+");
		for (int i = 0; i < temp.length; i++) {
			sb.append(temp[i] + "%");
		}

		/*
		 * for (int i = 0; i < str.length(); i++) { if(sb.toString()!=null &&
		 * !sb.toString().equals("")){ sb.append("%"); }
		 * sb.append(str.charAt(i)); }
		 */

		return sb.toString();
	}
	
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(splitStr("看 发到付地方  发的说法上的浪费发了  浪费大姐夫水电费"));
		System.out.println(splitStr("看,,发到付地方,发的说法上的浪费发了,,,浪费大姐夫水电费"));
		System.out.println(splitStr("看;发到付地方   发的说法上的浪费发了,,,浪费大姐夫水电费"));
	}

}
