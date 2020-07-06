package com.qifenqian.bms.platform.common.utils;

import java.net.InetAddress;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @project gyzb-platform-common
 * @fileName InetUtils.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
public class InetUtils {

	private InetUtils(){}
	
	private static String hostAddress = null;
	
	private static String lastSplitIp3 = null;
	
	/**
	 * 获取ip
	 * @return
	 */
	public static String getHostAddress() {
		try {
			if(StringUtils.isBlank(hostAddress)) {
				hostAddress = InetAddress.getLocalHost().getHostAddress();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hostAddress;
	}
	
	/**
	 * 获取ip最后一段 3位
	 * @return
	 */
	public static String getLastSplitIp3() {
		if(StringUtils.isBlank(lastSplitIp3)) {
			String[] ips = InetUtils.getHostAddress().split("\\.");
			lastSplitIp3 = StringUtils.leftPad(ips[ips.length-1], 3, '0');
		}
		return lastSplitIp3;
	}
}


