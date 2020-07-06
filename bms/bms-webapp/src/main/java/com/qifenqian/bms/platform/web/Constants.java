package com.qifenqian.bms.platform.web;

/**
 * 常量
 * 
 * @project sevenpay-platform-web
 * @fileName Constants.java
 * @author huiquan.ma
 * @date 2015年5月7日
 * @memo 
 */
public final class Constants {
	
	/** 系统常量*/
	public final static class Platform {
		/**	防重标签	*/
		public static final String SUBMIT_TOKEN = "SUBMIT_TOKEN";
		/** 用户*/
		public static final String USER_INFO_IN_SESSION = "SESSION.USER_INFO";
		/** 用户信息存入request*/
		public static final String USER_IN_REQUEST_SCOPE = "REQUEST.USER";
		/** 返回对象存入request*/
		public static final String RESPONSE_IN_REQUEST_SCOPE = "REQUEST.RESPOSNE";
		/** session对象存入request*/
		public static final String SESSION_IN_REQUEST_SCOPE = "REQUEST.SESSION";
		/** 请求历史存入session*/
		public static final String REQUEST_HISTORY = "REQUEST_HISTORY";
		/** 请求映射存入session*/
		public static final String REQUEST_MAP = "REQUEST_MAP";
		/** 返回的链接*/
		public static final String RETURN_LINKS = "RETURN_LINKS";
		/**	返回页面的提示	*/
		public static final String RETURN_INFO = "RETURN_INFO";
		/** 禁止显示返回上一页	 */
		public static final String DISABLE_LASTPAGE = "DISABLE_LASTPAGE";
		/** 分页对象*/
		public static final String $_PAGEINFO = "PAGEINFO";
		/** 是否使用分页标志*/
		public static final String $_BY_PAGE = "BY_PAGE";
		/** 当前页面*/
		public static final String $_PAGEINFO_CURRENT_PAGE = "PAGEINFO_CURRENT_PAGE";
		/** 总页数*/
		public static final String $_PAGEINFO_PAGE_SIZE = "PAGEINFO_PAGE_SIZE";
		/**	表示请求是AJAX请求	*/
		public static final String AJAX = "AJAX";
		/**	表示请求是AJAX请求	*/
		public static final String MODAL_DIALOG = "MODAL_DIALOG";
		/** 异常专递Key */
		public static final String EXCEPTION_KEY = "EXCEPTION_KEY";
	}
	
}


