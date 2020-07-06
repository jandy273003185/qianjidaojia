package com.qifenqian.bms.platform.web.admin.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.platform.common.config.SystemInfo;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;

/**
 * @project gyzb-platform-web-admin
 * @fileName GeneralServlet.java
 * @author huiquan.ma
 * @date 2015年12月3日
 * @memo
 */
public class GeneralServlet extends HttpServlet {

  private static final long serialVersionUID = -6026396710073595781L;

  private Logger logger = LoggerFactory.getLogger(GeneralServlet.class);

  @Override
  public void init(ServletConfig config) throws ServletException {

    logger.info("System Info: {}", SpringUtils.getBean(SystemInfo.class).toString());
  }

  @Override
  public void destroy() {

    try {

      SpringUtils.getBean(UserService.class).loginOutAllUsers();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
