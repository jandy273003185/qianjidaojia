package com.qifenqian.bms.platform.web.admin.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.PrivilegeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 判断是否拥有功能权限标签 无则不显示功能
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionAuthorityTag.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class FunctionAuthorityTag extends TagSupport{

	private static final long serialVersionUID = -7085553558705376441L;
	
	private String url;

	/**
	 * 开始时
	 */
	@Override
	public int doStartTag() throws JspException {
		// 获取用户
		User user = WebUtils.getUserInfo();
		// 用户为空 不显示标签体内容
		if(null == user) {
			return SKIP_BODY;
		}
		// 校验是否拥有权限
		Function func = new Function();
		func.setFunctionUrl(this.getUrl());
		if(PrivilegeUtils.hasFunc(user, func)) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
	
	/**
	 * 结束
	 */
	@Override
    public void release() {
        super.release();
        this.url = null;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}


