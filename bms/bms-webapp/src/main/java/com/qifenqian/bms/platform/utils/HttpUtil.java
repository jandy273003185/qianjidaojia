package com.qifenqian.bms.platform.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.sevenpay.plugin.IPlugin;

public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static  String invoker(String parameUrl){
		HttpURLConnection conn = null;
		OutputStream outStrm = null;
		String line = null;
		IPlugin plugin = (IPlugin) SpringUtils.getBean("pluginInvokerProxy");
		
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			URL	url = new URL(parameUrl);
			if("true".equals(plugin.findDictByPath("httpProxyFlag"))){
				String proxyHost = plugin.findDictByPath("httpProxyHost."+hostName);	
				String proxyPort = plugin.findDictByPath("httpProxyPort."+hostName);	
				logger.info("***************httpProxyHost:"+proxyHost+",httpProxyPort:"+proxyPort);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort))); 
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.connect();
			conn.setConnectTimeout(3000); // 设置连接超时1s
//			conn.setRequestMethod("get"); // 设定请求方式
			outStrm = conn.getOutputStream();
			// 给服务端传递的正文
			outStrm.write("心跳验证".getBytes());
			outStrm.flush();
			outStrm.close();
			// 获取http调用返回码：200 成功，401未授权，404找不到，500服务端内部异常
			int code = conn.getResponseCode();
			if (200 == code) {
				InputStream input = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(isr);
				if (null != br) {
					line = br.readLine();
					logger.debug("MasterJob.java 调用的返回JSON：" + line );
				} 
			} else {
				line = code + ":" + conn.getResponseMessage();
				throw new IllegalArgumentException(code + ":" + conn.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return line;
	}
}
