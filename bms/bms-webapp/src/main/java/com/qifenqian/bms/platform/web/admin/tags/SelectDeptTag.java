package com.qifenqian.bms.platform.web.admin.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.mapper.DeptMapper;
import com.qifenqian.bms.platform.web.admin.dept.type.DeptStatus;

/**
 * 部门选择标签
 * 
 * @project gyzb-platform-web-admin
 * @fileName SelectDeptTag.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class SelectDeptTag extends SimpleTagSupport{

	/** 默认值*/	private String defaultValue;
	/** id*/	private String id;
	/** 名称*/	private String name;
	/** 样式*/	private String style;
	/** 样式表*/	private String clasS;
	
	/**
	 * 执行
	 */
	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		DeptMapper deptMapper = SpringUtils.getBean(DeptMapper.class);
		
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

		jspWriter.write("<option value=''>--请选择部门--</option>");
		
		Dept queryBean =new Dept();
		queryBean.setStatus(DeptStatus.VALID);
		List<Dept> deptList = deptMapper.selectDeptList(queryBean);

		if(null != deptList && !deptList.isEmpty()) {
			for (Dept dept : deptList) {
				if (String.valueOf(dept.getDeptId()).equals(defaultValue)) {
					jspWriter.write("<option selected='selected' value='" + dept.getDeptId() + "'>(" + dept.getDeptCode() + ")-" + dept.getDeptName() + "</option>");
				} else {
					jspWriter.write("<option value='" + dept.getDeptId() + "'>(" + dept.getDeptCode() + ")-" + dept.getDeptName() + "</option>");
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
}


