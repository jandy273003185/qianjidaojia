package com.qifenqian.bms.platform.web.returnlink;

import java.io.Serializable;

/**
 * 返回链接
 * 
 * @project gyzb-platform-web
 * @fileName ReturnLink.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class ReturnLink implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReturnLink(String title, RequestLink link) {
		this.title = title;
		this.link = link;
	}

	/**
	 * 返回点击链接的名称
	 */
	private String title;

	/**
	 * 点击返回的链接
	 */
	private RequestLink link;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RequestLink getLink() {
		return link;
	}

	public void setLink(RequestLink link) {
		this.link = link;
	}

}
