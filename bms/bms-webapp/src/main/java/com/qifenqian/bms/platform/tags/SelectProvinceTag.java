package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 选择省份下拉列表标签
 * 
 * @author PC
 * 
 */
public class SelectProvinceTag extends SimpleTagSupport {
	private String id;

	private String name;

	private String clasS;

	private String defaultValue;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();
		CityService cityService = SpringUtils.getBean(CityService.class);
		List<City> province = cityService.selectAllProvince();

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
		jspWriter.write("<option value=''>选择省份</option>");

		if (null != province && !province.isEmpty()) {
			for (City city : province) {
				if (String.valueOf(city.getProvinceId()).equals(defaultValue)) {
					jspWriter.write("<option value = '" + city.getProvinceId() + "'>" + city.getProvinceName()+ "</option>");

				} else {
					jspWriter.write("<option value = '" + city.getProvinceId() + "'>" + city.getProvinceName()+ "</option>");
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
