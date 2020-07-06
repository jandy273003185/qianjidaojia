package com.sevenpay.agentmanager.common.utils;

public class MD5Security {
	public static String getMD5String(String plainletter) {
		MD5 md = new MD5();
		return md.getMD5ofStr(plainletter);
	}
}
