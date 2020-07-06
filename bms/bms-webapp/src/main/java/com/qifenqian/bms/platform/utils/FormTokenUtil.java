package com.qifenqian.bms.platform.utils;

import javax.servlet.http.HttpServletRequest;

public class FormTokenUtil
{
	/**
	 * 生成form的tokenID，并set到session里
	 * 
	 */
	public static String creatToken(HttpServletRequest request){
		String token = GenSN.getFormTokenId();
		request.getSession().setAttribute("_TOKEN_ID", token);
	    return token;
	}
	
	/**
	 * 从session里取出tokenID，并将tokenID从session里删去
	 * 此方法为线程安全方法
	 */
	private static synchronized String getTokenFromSession(HttpServletRequest request){
		String token = "";
		if(null!=request.getSession().getAttribute("_TOKEN_ID")){
			token =  (String) request.getSession().getAttribute("_TOKEN_ID");
			request.getSession().removeAttribute("_TOKEN_ID"); //取出来就删掉
		}
		return token;
	}
	
	/**
	 * 从request里取tokenID
	 * 
	 */
	private static String getTokenFromRequest(HttpServletRequest request){
		String token = "";
		if(null!=request.getParameter("_TOKEN_ID")){
			token = request.getParameter("_TOKEN_ID");
		}
		return token;
	}
	
	/**
	 * 验证tokenID
	 * 
	 */
	public static boolean validateToken(HttpServletRequest request){
		boolean flag = false;
		String sessionFormToken = getTokenFromSession(request);
		String requestToken = getTokenFromRequest(request);
		if(sessionFormToken.equalsIgnoreCase(requestToken)){
			flag = true;
		}
		return flag;
	}
	
}