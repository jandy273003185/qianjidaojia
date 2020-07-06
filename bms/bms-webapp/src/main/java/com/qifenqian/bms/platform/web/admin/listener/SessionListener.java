package com.qifenqian.bms.platform.web.admin.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.login.service.LoginService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName SessionListener.java
 * @author huiquan.ma
 * @date 2015年12月3日
 * @memo
 */
public class SessionListener implements HttpSessionListener {

	/**
	 * Session创建事件
	 */
	@Override
	public void sessionCreated(HttpSessionEvent hse) {

	}

	/**
	 * Session失效事件
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {

		User user = WebUtils.getUserInfo();

		// 用户退出
		SpringUtils.getBean(LoginService.class).logout(user);
		// 清空线程变量
		WebUtils.destroy();
	}
}