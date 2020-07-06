package com.qifenqian.bms.platform.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.accounting.utils.AcountCustType;

public class SelectAcountCustTypeTag extends SimpleTagSupport{
	/** 默认值*/	private String defaultValue;
	/** id*/	private String id;
	/** 名称*/	private String name;
	/** 样式*/	private String style;
	/** 样式表*/	private String clasS;
				private String disabled;
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
		if (null != disabled) {
			jspWriter.write(" disabled='" + disabled + "' ");
		}
		jspWriter.write(">");

		jspWriter.write("<option value=''>--请选择--</option>");

		for (AcountCustType status : AcountCustType.values()) {
			if (status.getCode().equals(defaultValue)) {
				jspWriter.write("<option selected='selected' value='" + status.getCode() + "'>"+ status.getDesc() + "</option>");
			} else {
				jspWriter.write("<option value='" + status.getCode() + "'>" + status.getDesc() + "</option>");
			}
		}

		jspWriter.write("</select>");

	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
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
