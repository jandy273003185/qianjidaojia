package com.qifenqian.bms.platform.web.admin.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.platform.web.admin.role.bean.Role;

/**
 * 选择角色下拉列表标签
 *
 * @project gyzb-platform-web-admin
 * @fileName SelectRoleTag.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class SelectRoleTag extends SimpleTagSupport {

  private String id;
  private String defaultValue;
  private String name;

  private String clasS;

  private List<Role> roles;

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
    jspWriter.write("<option value=''>-请选择角色-</option>");

    for (Role role : roles) {
      if (String.valueOf(role.getRoleId()).equals(defaultValue)) {
        jspWriter.write(
            "<option value = '" + role.getRoleId() + "'>" + role.getRoleName() + "</option>");
      } else {
        jspWriter.write(
            "<option value = '" + role.getRoleId() + "'>" + role.getRoleName() + "</option>");
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }
}
