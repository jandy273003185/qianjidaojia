<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.accounting.utils.CommonConfig"%>
<%@page import="com.qifenqian.platform.web.admin.user.bean.User"%>
<%@page import="com.qifenqian.platform.web.admin.utils.WebUtils"%>
<%@page import="com.qifenqian.platform.web.Constants"%>
<%@page import="com.qifenqian.platform.web.admin.function.bean.Function"%>
<%@page import="com.qifenqian.platform.common.utils.SpringUtils"%>
<%@page import="com.qifenqian.platform.common.config.SystemInfo"%>
<%@page import="java.util.List"%>

<!-- taglib -->
<%@ taglib prefix="c"		 uri="http://java.sun.com/jsp/jstl/core"		%>
<%@ taglib prefix="fmt"		 uri="http://java.sun.com/jsp/jstl/fmt"			%>
<%@ taglib prefix="fn"		 uri="http://java.sun.com/jsp/jstl/functions"	%>
<%@ taglib prefix="x"		 uri="http://java.sun.com/jsp/jstl/xml"			%>
<%@ taglib prefix="sql"		 uri="http://java.sun.com/jsp/jstl/sql"			%>
<%@ taglib prefix="gyzb"     uri="http://web.platform.gyzb.org/taglib/1.0"		%>
<%@ taglib prefix="gyzbadmin"     uri="http://admin.web.platform.gyzb.org/taglib/1.0"	%>
<%@ taglib prefix="sevenpay" uri="http://bms.sevenpay.org/taglib/1.0"		%>

<%
	CommonConfig commonConfig=CommonConfig.getInstance();
     String _jsVersion=commonConfig.getValue("VERSION");
     String _cssVersion=commonConfig.getValue("VERSION");
	// 登陆对象
	User user = WebUtils.getUserInfo();

	// 获取请求function
	Function requestFunction = null;
	String requestUrl = (String)request.getAttribute("javax.servlet.forward.request_uri");
	if(null != user && null != requestUrl) {
		requestUrl = requestUrl.replaceAll(request.getContextPath(), "");
		List<Function> functionList = user.getFunctionList();
		if(null != functionList && !functionList.isEmpty()) {
	for(Function function : functionList) {
		if(requestUrl.equals(null == function.getFunctionUrl() ? null : function.getFunctionUrl().replaceAll("\\?.*", ""))) {
	requestFunction = function;
	break;
		}
	}
		}
	}
	pageContext.setAttribute("requestFunction", requestFunction);
%>
<c:set var="_jsVersion" value="<%=_jsVersion%>" />
<c:set var="_cssVersion" value="<%=_cssVersion%>" />
<!-- basic styles -->

<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.min.css?v=${_cssVersion}"/>' />
<link rel="stylesheet" href='<c:url value="/assets/css/font-awesome.min.css?v=${_cssVersion}"/>' />
<!-- page specific plugin styles -->
<link rel="stylesheet" href='<c:url value="/assets/css/dropzone.css?v=${_cssVersion}"/>' />

<link rel="stylesheet" href='<c:url value="/assets/css/ace.min.css?v=${_cssVersion}"/>' />
<link rel="stylesheet" href='<c:url value="/assets/css/ace-rtl.min.css?v=${_cssVersion}"/>' />
<link rel="stylesheet" href='<c:url value="/assets/css/ace-skins.min.css?v=${_cssVersion}"/>' />
<link rel="stylesheet" href='<c:url value="/static/css/gyzb-admin.css?v=${_cssVersion}"/>' />
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css?v=${_cssVersion}"/>' />

<!-- 使用服务端的调用生成一些前端需要的信息,模拟JS文件引入 -->
<script type="text/javascript">
	
	// 定义JS端的常量等信息
	window.Constants = {};
	
	// 上下文路径
	window.Constants.ContextPath = '<%= request.getContextPath() %>';

	// 正则定义
	var kong = /^\s*$/;
	
</script>

<script src='<c:url value="/assets/js/ace-extra.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/assets/js/jquery-2.0.3.min.js?v=${_jsVersion}"/>'/></script>
<script src='<c:url value="/static/js/jquery.blockUI.js?v=${_jsVersion}"/>'/></script>
<script src='<c:url value="/static/js/jquery.pagination.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/assets/js/bootstrap.min.js?v=${_jsVersion}"/>'></script>
<!-- page specific plugin scripts -->
<script src='<c:url value="/assets/js/typeahead-bs2.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/assets/js/dropzone.min.js?v=${_jsVersion}"/>'></script>
		
<script src='<c:url value="/assets/js/ace-elements.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/assets/js/ace.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/gyzb-admin.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/service.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js?v=${_jsVersion}"/>'></script>


