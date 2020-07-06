package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.accounting.subjectInfo.bean.SubjectInfo;
import com.qifenqian.bms.accounting.subjectInfo.mapper.SubjectInfoMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

public class SelectSubjecInfoIdTag extends SimpleTagSupport {
	/** 默认值 */
	private String defaultValue = "0";
	/** id */
	private String id;
	/** 名称 */
	private String name;
	/** 样式 */
	private String style;
	/** 样式表 */
	private String clasS;
	private String disabled;

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jspContext = this.getJspContext();

		SubjectInfoMapper mapperList = SpringUtils.getBean(SubjectInfoMapper.class);

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

		List<SubjectInfo> prtList = mapperList.list();

		if (null != prtList && !prtList.isEmpty()) {
			for (SubjectInfo pInfo : prtList) {
				if (pInfo.getId().equals(Integer.valueOf(defaultValue))) {
					jspWriter.write("<option selected='selected' value = '" + pInfo.getId() + "'>" + pInfo.getSubjectName() + "(" + pInfo.getSubjectCode() + ")" + "</option>");

				} else {
					jspWriter.write("<option value = '" + pInfo.getId() + "'>" + pInfo.getSubjectName() + "(" + pInfo.getSubjectCode() + ")" + "</option>");
				}
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

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

}
