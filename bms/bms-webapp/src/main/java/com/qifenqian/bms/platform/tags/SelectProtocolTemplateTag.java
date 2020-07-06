package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.mapper.ProtocolTemplateMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 选择协议模板下拉列表标签
 * 
 * @author PC
 *
 */
@Component
public class SelectProtocolTemplateTag extends SimpleTagSupport {

	private String id;

	private String name;

	private String clasS;

	private String defaultValue;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		ProtocolTemplateMapper protocolTemplateMapper = SpringUtils.getBean(ProtocolTemplateMapper.class);

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

		ProtocolTemplate protocolTemplate = new ProtocolTemplate();
		protocolTemplate.setStatus("VALID");
		List<ProtocolTemplate> templateList = protocolTemplateMapper.select(protocolTemplate);;

		jspWriter.write(">");
		jspWriter.write("<option value=''>  --请选择--  </option>");
		if (null != templateList && !templateList.isEmpty()) {
			for (ProtocolTemplate template : templateList) {
				if (template.getId().equals(defaultValue)) {
					jspWriter.write("<option selected='selected' value='" + template.getId() + "'>" + template.getProtocolTemplateName() + "</option>");
				} else {
					jspWriter.write("<option value='" + template.getId() + "'>" + template.getProtocolTemplateName() + "</option>");
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
