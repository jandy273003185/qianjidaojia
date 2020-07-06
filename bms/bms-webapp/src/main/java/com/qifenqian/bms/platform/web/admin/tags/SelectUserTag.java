package com.qifenqian.bms.platform.web.admin.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.platform.web.admin.user.bean.User;


/**
 * 选择用户下拉列表标签
 * @project gyzb-platform-web-admin
 * @fileName SelectUserTag.java
 * @author huiquan.ma
 * @date 2015年12月21日
 * @memo
 */
public class SelectUserTag extends SimpleTagSupport {

	private String id;
	private String defaultValue;
	private String name;

	private String clasS;

	private List<User> users;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		jspWriter.write("<select ");

		if (null != id) {
			jspWriter.write("id = '" + id + "' ");
		}

		if (null != name) {
			jspWriter.write("name = '" + name + "' ");
		}

		if (null != clasS) {
			jspWriter.write("class = '" + clasS + "' ");
		}

		jspWriter.write(">");
		jspWriter.write("<option value=''>-请选择-</option>");

		for (User user : users) {
			if (String.valueOf(user.getUserId()).equals(defaultValue)) {
				jspWriter.write("<option value = '" + user.getUserId() + "'>" + user.getRealName() + "(" + user.getUserId() + ")" + "</option>");
			} else {
				jspWriter.write("<option value = '" + user.getUserId() + "'>" + user.getRealName() + "(" + user.getUserId() + ")" + "</option>");
			}

		}

		jspWriter.write("</select>");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClasS() {
		return clasS;
	}

	public void setClasS(String clasS) {
		this.clasS = clasS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
