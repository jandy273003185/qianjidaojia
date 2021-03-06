package com.qifenqian.bms.platform.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.accounting.utils.AcctType;





public class SelectTyeAcctTag extends SimpleTagSupport{
	/** 默认值*/	private String defaultValue;
	/** id*/	private String id;
	/** 名称*/	private String name;
	/** 样式*/	private String style;
	/** 样式表*/	private String clasS;
	@Override
	public void doTag() throws JspException, IOException {
		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		jspWriter.write("<select");

		if (null != id) {
			jspWriter.write(" id='" + id + "' ");
		}
		if (null != name) {
			jspWriter.write(" name='" + name + "' ");
		}
		if (null != style) {
			jspWriter.write(" style='" + style + "' ");
		}
		if (null != clasS) {
			jspWriter.write(" class='" + clasS + "' ");
		}

		jspWriter.write(">");

		jspWriter.write("<option value=''>--请选择--</option>");

		for (AcctType status : AcctType.values()) {
			if (status.name().equals(defaultValue)) {
				jspWriter.write("<option selected='selected' value='" + status.name() + "'>"  + status.getDesc() + "</option>");
			} else {
				jspWriter.write("<option value='" + status.name() + "'>" + status.getDesc() + "</option>");
			}
		}

		jspWriter.write("</select>");

	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getClasS() {
		return clasS;
	}
	public void setClasS(String clasS) {
		this.clasS = clasS;
	}
}
