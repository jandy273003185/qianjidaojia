package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import com.qifenqian.bms.basemanager.sysuser.bean.SysUser;
import com.qifenqian.bms.basemanager.sysuser.dao.SysUserDAO;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 员工信息
 * 
 * @author PC
 *
 */
@Component
public class SelectUserInfoTag extends SimpleTagSupport {

	private String id;

	private String name;

	private String clasS;

	private String defaultValue;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		SysUserDAO sysUserDAO = SpringUtils.getBean(SysUserDAO.class);

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

		List<SysUser> sysusers = sysUserDAO.selectSysUser();

		jspWriter.write(">");
		jspWriter.write("<option value=''>  --请选择--  </option>");
		if (null != sysusers && !sysusers.isEmpty()) {
			for (SysUser sysuser : sysusers) {
				if (sysuser.getUserId().equals(defaultValue)) {
					jspWriter.write("<option selected='selected' value='" + sysuser.getUserId()+ "'>" + sysuser.getUserName()+ "</option>");
				} else {
					jspWriter.write("<option value='" + sysuser.getUserId() + "'>" + sysuser.getUserName() + "</option>");
				}
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
