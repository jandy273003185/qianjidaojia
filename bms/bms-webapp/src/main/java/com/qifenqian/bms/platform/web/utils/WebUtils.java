package com.qifenqian.bms.platform.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.page.bean.PageInfo;
import com.qifenqian.bms.platform.web.returnlink.RequestLink;
import com.qifenqian.bms.platform.web.returnlink.ReturnLink;


/**
 * 和WEB操作的相关工具方法
 * 
 * @project gyzb-platform-web
 * @fileName WebUtils.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@SuppressWarnings("unchecked")
public class WebUtils {

  private static final Logger logger = Logger.getLogger(WebUtils.class.getName());

  private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();
  private static ThreadLocal<HttpServletResponse> responses =
      new ThreadLocal<HttpServletResponse>();
  private static ThreadLocal<HttpSession> sessions = new ThreadLocal<HttpSession>();
  private static ServletContext servletContext = null;


  /**
   * 清除线程变量
   */
  public static void destroy() {

    HttpServletRequest request = requests.get();
    if (request != null) {
      request.setAttribute(Constants.Platform.RESPONSE_IN_REQUEST_SCOPE, responses.get());
      request.setAttribute(Constants.Platform.SESSION_IN_REQUEST_SCOPE, sessions.get());
    }

    requests.remove();
    responses.remove();
    sessions.remove();
  }

  public static void init(HttpServletRequest request, HttpServletResponse response) {

    // 线程变量保存request
    requests.set(request);

    // 线程变量保存session
    HttpSession httpSession = request.getSession();
    sessions.set(httpSession);

    // 请求历史列表(增强按照点击深度来返回页面的业务逻辑)
    LinkedList<RequestLink> requestHistory =
        (LinkedList<RequestLink>) httpSession.getAttribute(Constants.Platform.REQUEST_HISTORY);

    // 请求映射(增强按照名称来返回页面的业务逻辑)
    Map<String, RequestLink> requestMap =
        (Map<String, RequestLink>) httpSession.getAttribute(Constants.Platform.REQUEST_MAP);

    // 如果session中请求历史记录列表为空,则进行初始化后再存入session中
    if (requestHistory == null) {
      requestHistory = new LinkedList<RequestLink>();
      httpSession.setAttribute(Constants.Platform.REQUEST_HISTORY, requestHistory);
    }

    // 同上
    if (requestMap == null) {
      requestMap = new HashMap<String, RequestLink>();
      httpSession.setAttribute(Constants.Platform.REQUEST_MAP, requestMap);
    }

    // 线程变量保存response
    responses.set(response);

    // 全局变量保存上下文
    if (servletContext == null) {
      synchronized (WebUtils.class) {
        if (servletContext == null) {
          servletContext = httpSession.getServletContext();
        }
      }
    }

    // 如果ajax请求/模态对话框,则不进行保存请求参数
    if (request.getParameter(Constants.Platform.AJAX) == null
        && request.getParameter(Constants.Platform.MODAL_DIALOG) == null) {
      // 如果是脚本获取,也不保存请求信息
      String requestURI = request.getRequestURI();
      if (!requestURI.endsWith("/script")) {
        // 解析请求信息
        RequestLink returnLink = new RequestLink(request);
        // 将请求链接放到队列头部
        requestHistory.addFirst(returnLink);
      }
    }

    // 初始化page对象
    PageInfo pageInfo = (PageInfo) ThreadUtils.get(Constants.Platform.$_PAGEINFO);
    if (pageInfo == null) {
      ThreadUtils.put(Constants.Platform.$_PAGEINFO, pageInfo = new PageInfo());
    }

    // 解析reuest中的参数当前页
    String currentPage = request.getParameter(Constants.Platform.$_PAGEINFO_CURRENT_PAGE);
    if (!StringUtils.isEmpty(currentPage)) {
      pageInfo.setCurrentPage(Integer.parseInt(currentPage));
    }

    // 解析reuest中的参数每页行数
    String pageSize = request.getParameter(Constants.Platform.$_PAGEINFO_PAGE_SIZE);
    if (!StringUtils.isEmpty(pageSize)) {
      pageInfo.setPageSize(Integer.parseInt(pageSize));
    }

    // 初始化返回信息缓存
    request.setAttribute(Constants.Platform.RETURN_INFO, new ArrayList<String>());
  }

  /**
   * 用一个逻辑名称保存当前的请求信息
   * 
   * @param linkId 逻辑名称
   */
  public static void setReturnLink(String linkId) {
    RequestLink returnLink = new RequestLink(requests.get());
    Map<String, RequestLink> requestMap =
        (Map<String, RequestLink>) getHttpSession().getAttribute(Constants.Platform.REQUEST_MAP);
    requestMap.put(linkId, returnLink);
  }

  /**
   * 显示链接菜单
   * 
   * @param returnLinkTitle 链接名字("返回上一页,返回列表"之类的)
   * @param linkId 逻辑名称
   */
  public static void showReturnLinkWithLinkId(String returnLinkTitle, String linkId) {
    showReturnLinkWithLinkId(returnLinkTitle, linkId, null);
  }

  /**
   * 显示链接菜单
   * 
   * @param returnLinkTitle 链接名字("返回上一页,返回列表"之类的)
   * @param linkId 逻辑名称
   * @param params 自定义参数,主要用于回显原页面提交的参数,该功能必须要求开发人员手工编码,平台不支持本身不支持回显,平台下个版本再对提交参数回显功能进行优化
   */
  public static void showReturnLinkWithLinkId(String returnLinkTitle, String linkId,
      Map<String, String[]> params) {
    Map<String, RequestLink> requestMap =
        (Map<String, RequestLink>) getHttpSession().getAttribute(Constants.Platform.REQUEST_MAP);
    RequestLink returnLink = requestMap.get(linkId);
    if (returnLink == null) {
      logger.log(Level.WARNING, "当前会话中并未保存链接ID为" + linkId + "的链接信息");
      return;
    }
    if (params != null) {
      returnLink.getParams().putAll(params);
    }

    HttpServletRequest request = getHttpServletRequest();

    // 获取自定义返回链接,如果为空,则先初始化再存入作用域
    List<ReturnLink> returnLinks =
        (List<ReturnLink>) request.getAttribute(Constants.Platform.RETURN_LINKS);
    if (returnLinks == null) {
      returnLinks = new LinkedList<ReturnLink>();
      request.setAttribute(Constants.Platform.RETURN_LINKS, returnLinks);
    }

    returnLinks.add(new ReturnLink(returnLinkTitle, returnLink));
  }

  /**
   * 显示链接菜单
   * 
   * @param returnLinkTitle 返回链接标题
   * @param linkUri 返回链接uri
   * @param params 返回链接参数
   */
  public static void showReturnLink(String returnLinkTitle, String linkUri,
      Map<String, String[]> params) {

    if (linkUri == null) {
      throw new IllegalArgumentException("链接url不能为空");
    }

    // 如果是以'/'打头,则认为该'/'是一个应用上下文根,则需要在前面拼上上下文根路径
    if (linkUri.startsWith("/")) {
      linkUri = servletContext.getContextPath() + linkUri;
    } else {
      // 否则认为是一个完整的跨域网址,则需要在前面补上http协议头
      if (!linkUri.matches("https?://.*")) {
        linkUri += "http://";
      }
    }

    if (params == null) {
      params = new HashMap<String, String[]>();
    }

    RequestLink returnLink = new RequestLink(linkUri, params);

    HttpServletRequest request = getHttpServletRequest();

    // 获取自定义返回链接,如果为空,则先初始化再存入作用域
    List<ReturnLink> returnLinks =
        (List<ReturnLink>) request.getAttribute(Constants.Platform.RETURN_LINKS);
    if (returnLinks == null) {
      returnLinks = new LinkedList<ReturnLink>();
      request.setAttribute(Constants.Platform.RETURN_LINKS, returnLinks);
    }

    returnLinks.add(new ReturnLink(returnLinkTitle, returnLink));
  }

  public static HttpServletRequest getHttpServletRequest() {
    return requests.get();
  }

  public static HttpServletResponse getHttpServletResponse() {
    return responses.get();
  }

  public static HttpSession getHttpSession() {
    return sessions.get();
  }

  public static ServletContext getServletContext() {
    return servletContext;
  }

  public static PageInfo getPage() {
    return (PageInfo) getHttpServletRequest().getAttribute(Constants.Platform.$_PAGEINFO);
  }

  public static void disableShowReturnLastPage() {
    requests.get().setAttribute(Constants.Platform.DISABLE_LASTPAGE,
        Constants.Platform.DISABLE_LASTPAGE);
  }

  /** 成功/失败响应页面增加提示信息 */
  public static void addInfomation(String info) {
    ((List<String>) requests.get().getAttribute(Constants.Platform.RETURN_INFO)).add(info);
  }

  /** 产生防重标志 */
  public static void setSubmitToken() {

    // 检查request已被绑定
    HttpServletRequest request = getHttpServletRequest();
    if (request == null) {
      logger.warning("设置防重token时检测到当前线程未绑定HttpServletRequest对象");
      throw new IllegalArgumentException("当前线程未绑定HttpServletRequest对象");
    }

    // 检查session已被绑定
    HttpSession session = getHttpSession();
    if (session == null) {
      logger.warning("设置防重token时检测到当前线程未绑定HttpSession对象");
      throw new IllegalArgumentException("当前线程未绑定HttpSession对象");
    }

    // 产生防重ID
    String token = RandomStringUtils.randomAlphanumeric(10);
    logger.fine("针对请求" + request.getRequestURI() + "响应页面前,产生防重token:" + token);

    // 放入request作用域
    request.setAttribute(Constants.Platform.SUBMIT_TOKEN, token);

    // 放入session作用域
    session.setAttribute(Constants.Platform.SUBMIT_TOKEN, token);
  }

  /** 防重检查,如果重复提交,则返回true */
  public static void checkSubmitToken() {

    String sessionToken = (String) getHttpSession().getAttribute(Constants.Platform.SUBMIT_TOKEN);
    String requestToken = getHttpServletRequest().getParameter(Constants.Platform.SUBMIT_TOKEN);

    // 之前有提交过
    if (sessionToken == null) {
      /*
       * 本次请求视为非法请求,将会擦除最新的请求历史记录,否则返回上一页会再一次进行相同提交 这里没有考虑到ajax方式的防重.ajax方式的防重是不会记录请求的,所以无须删除
       */
      LinkedList<RequestLink> links =
          (LinkedList<RequestLink>) sessions.get().getAttribute(Constants.Platform.REQUEST_HISTORY);
      if (!links.isEmpty()) {
        links.remove(0);
      }
      throw new IllegalStateException("请勿重复提交");
    } else if (sessionToken.equals(requestToken)) {
      // 第一次提交
      getHttpSession().removeAttribute(Constants.Platform.SUBMIT_TOKEN);
      logger.info("防重token相匹配:" + requestToken);
    } else {
      // token不等时认为是重复提交(客户可能回退N页后点击F5进行手工刷新)
      LinkedList<RequestLink> links =
          (LinkedList<RequestLink>) sessions.get().getAttribute(Constants.Platform.REQUEST_HISTORY);
      if (!links.isEmpty()) {
        links.remove(0);
      }
      throw new IllegalStateException("请勿重复提交");
    }
  }

  /**
   * 在传入的uri后面添加后缀
   */
  public static String paddingSuffix(String uri) {
    if (StringUtils.isEmpty(uri)) {
      throw new IllegalArgumentException("要处理的URI不能为空");
    }
    String suffix = servletContext.getInitParameter("suffix");
    if (StringUtils.isNotEmpty(suffix)) {
      return uri + "." + suffix;
    }
    return uri;
  }
}
