package com.qifenqian.bms.platform.web.admin.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;

/**
 * 用户状态选择标签
 *
 * @project gyzb-platform-web-admin
 * @fileName SelectUserStatusTag.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class SelectUserStatusTag extends SimpleTagSupport {

  /** 默认值 */
  private String defaultValue;
  /** id */
  private String id;
  /** 名称 */
  private String name;
  /** 样式 */
  private String style;
  /** 样式表 */
  private String clasS;

  /** 执行 */
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

    jspWriter.write("<option value=''>--请选择用户状态--</option>");

    for (UserStatus status : UserStatus.values()) {
      if (status.name().equals(defaultValue)) {
        jspWriter.write(
            "<option selected='selected' value='"
                + status.name()
                + "'>("
                + status.name()
                + ")-"
                + status.getDesc()
                + "</option>");
      } else {
        jspWriter.write(
            "<option value='"
                + status.name()
                + "'>("
                + status.name()
                + ")-"
                + status.getDesc()
                + "</option>");
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
