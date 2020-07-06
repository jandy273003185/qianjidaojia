package com.qifenqian.bms.configure.log;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.util.NestedServletException;

import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

public class GeneralFilter implements Filter {
  private static final Logger LOGGER = LoggerFactory.getLogger(GeneralFilter.class);

  /** 过滤 */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    String requestUri = httpServletRequest.getRequestURI();

    // 定义常规静态资源请求不输出日志
    String[] skipSuffixs = new String[] {".js", ".css", ".jpg", "ico"};
    // 是否跳过过滤
    boolean skip = false;
    for (String suffix : skipSuffixs) {
      if (requestUri.endsWith(suffix)) {
        skip = true;
        break;
      }
    }

    try {
      if (skip) {
        chain.doFilter(request, response);
      } else {
        // 相关初始化
        WebUtils.init(httpServletRequest, httpServletResponse);

        // 日志设值
        MDC.clear();
        User user = WebUtils.getUserInfo();
        MDC.put("user_id", null != user ? String.valueOf(user.getUserId()) : "");
        LOGGER.debug(
            "[request ip:{}, path:{}] begin", httpServletRequest.getRemoteHost(), requestUri);

        // 执行
        try {
          chain.doFilter(request, response);
        } catch (Throwable t) {
          // 如果是一个已经打印过的异常,则不再进行打印
          if (t instanceof NestedServletException) {
            t = t.getCause();
          }
          // if(!(t instanceof PrintedExpcetion)){
          // logger.error("拦截器拦截到一个服务器异常, 转发到error.jsp进行响应", t);
          // } else {
          // logger.error("拦截器拦截到一个服务器异常, 转发到error.jsp进行响应, 异常堆栈信息已经打印, 故不再重复打印");
          // }
          LOGGER.error("拦截器拦截到一个服务器异常, 转发到error.jsp进行响应", t);

          httpServletRequest.setAttribute(Constants.Platform.EXCEPTION_KEY, t);
          if (response.isCommitted()) {
            LOGGER.error("该服务器异常出现前,由于输出缓存已经刷新,故不再跳转到系统异常显示页面");
            LOGGER.info(
                "[request ip:{}, path:{}] end", httpServletRequest.getRemoteHost(), requestUri);
            // 移除
            MDC.clear();
            return;
          }
          RequestDispatcher dispatcher =
              httpServletRequest.getRequestDispatcher("/views/error.jsp");
          dispatcher.forward(httpServletRequest, httpServletResponse);
        }

        LOGGER.debug(
            "[request ip:{}, path:{}] end", httpServletRequest.getRemoteHost(), requestUri);
        // 移除
        MDC.clear();
      }
    } finally {
      // 清除线程变量,防止下一个请求套用上一个线程的无效信息
      WebUtils.destroy();
      ThreadUtils.clearAll();
    }
  }
}
