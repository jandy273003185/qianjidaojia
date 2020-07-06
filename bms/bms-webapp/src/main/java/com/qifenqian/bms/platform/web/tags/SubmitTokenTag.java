package com.qifenqian.bms.platform.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.utils.WebUtils;

/**
 * 防重用的标签，模拟生成一个input值
 * 
 * @project gyzb-platform-web
 * @fileName SubmitTokenTag.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class SubmitTokenTag extends SimpleTagSupport{

	public final static String FORM = "FORM";
	public final static String PROP = "PROP";
	public final static String PROPERTY = "PROPERTY";

	/**
	 * 样式一:	FORM, 	产生类似于<input type="hidden" name="SUBMIT_TOKEN" value="###">的标签
	 * 样式二:	PROP, 产生类似于SUBMIT_TOKEN : '###', 的属性
	 */
	private String type;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = getJspContext();

		String token = (String) WebUtils.getHttpServletRequest().getAttribute(Constants.Platform.SUBMIT_TOKEN);
		
		JspWriter jspWriter = jspContext.getOut();

		if (FORM.equalsIgnoreCase(type)) {
			jspWriter.write("<input type='hidden' name='SUBMIT_TOKEN' value='" + token + "'>");
		} else if (PROP.equalsIgnoreCase(type) || PROPERTY.equalsIgnoreCase(type)) {
			jspWriter.write("SUBMIT_TOKEN : '" + token + "'");
		} else {
			throw new RuntimeException("未知<SubmitToken>类型");
		}

	}
}


