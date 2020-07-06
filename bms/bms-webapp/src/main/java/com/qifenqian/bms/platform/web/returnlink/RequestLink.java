package com.qifenqian.bms.platform.web.returnlink;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求链接
 * 
 * @project gyzb-platform-web
 * @fileName RequestLink.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class RequestLink implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 请求URI
	 */
	private String uri;

	/**
	 * 请求参数
	 */
	private Map<String, String[]> params = new HashMap<String, String[]>();

	/**
	 * 使用传入的链接和参数将请求链接缓存
	 * @param uri
	 * @param params
	 */
	public RequestLink(String uri, Map<String, String[]> params){
		this.uri = uri;
		this.params = params;
	}
	
	/**
	 * 将请求链接,参数缓存解析
	 * @param request
	 */
	public RequestLink(HttpServletRequest request) {

		this.uri = request.getRequestURI();
		
		// 不能直接对引用进行赋值,因为web容器会在后面的线程中更新同一个ParamMap的值
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			String name = entry.getKey();
			String[] value = entry.getValue();
			params.put(name, value);
		}

	}
	
	public String getUri() {
		return uri;
	}

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

}
