<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.springframework.web.util.NestedServletException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%
	Throwable throwable = (Throwable) request.getAttribute(Constants.Platform.EXCEPTION_KEY);
	if(throwable != null){
		// 剥离SpringMVC的封装获取内部最原始的异常
		if(throwable instanceof NestedServletException){
			throwable = throwable.getCause();
		}
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>登陆后主页</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<style type="text/css">
		.error-detail {
			width: 900px;
			background-color: black;
			border: 1px solid #ccc;
			padding: 0;
			-webkit-border-radius: 10px;
			-moz-border-radius: 10px;
			height: 600px;
			color: #74E54E;
			font-weight: 500;
			left:50%;
			margin-left: -450px;
		}
	</style>
</head>

<body>
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<font style="font-size: 15px;font-weight: bold;color: rgb(71,143,202)">错误页</font>
							</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content" style="width: 80%;margin: 0 auto;">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-random"></i>
												Something Went Wrong
											</span>
										</h1>
										<hr />
										<div style="padding-left: 150px;">
											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<h3 class="lighter smaller">error message follow:</h3>
												</li>
												<li style="color: red;padding-left: 50px;">
													<%
														if(throwable != null){
															String errorMessage = throwable.getMessage();
															if(errorMessage != null){
																if(errorMessage.length() > 100){
																	errorMessage = errorMessage.substring(0, 100) + "...";
																}
															}
															out.print(errorMessage);
														}
													%>
												</li>
												<li>
													若无法处理该异常情况,请联系管理员进行处理...
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="#" class="btn btn-grey" onclick="window.history.back();">
												<i class="icon-arrow-left"></i>
												返回
											</a>
											<a href="#" class="btn btn-primary" data-toggle='modal' data-target="#errorDetailModal">
												查看异常明细
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
						
						<!-- 异常明细模态框（Modal） -->
						<div class="modal fade" id="errorDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						   <div class="modal-dialog">
						      <div class="modal-content error-detail">
							       <div class="modal-header">
							            <button type="button" data-dismiss="modal" aria-hidden="true" style="padding:0;cursor:pointer;background:#74E54E;border:0;-webkit-appearance:none;float: right;width: 20px;"> &times;</button>
							            <h4 class="modal-title" id="myModalLabel">异常明细</h4>
							         </div>
							         <div class="modal-body" style="overflow: scroll;height:550px;">
							         	<%
											if(throwable != null){
												StringWriter sw = new StringWriter();
												PrintWriter pw = new PrintWriter(sw);
												throwable.printStackTrace(pw);
												String stackTrace = sw.toString();
												
												// 替换转义字符
												stackTrace = stackTrace.replace("&", "&amp;")
																	   .replace("<", "&lt;")
																	   .replace(">", "&gt;")
																	   .replace("\"", "&quot;")
																	   .replace(" ", "&nbsp;");
												// 替换换行符
												stackTrace = stackTrace.replaceAll("[\\r]?\\n", "<br/>");
												
												// 替换行缩进符
												stackTrace = stackTrace.replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
												pageContext.setAttribute("stackTrace", stackTrace);
											}
										%>
										${stackTrace}
							         </div>
						      </div><!-- /.modal-content -->
						     </div>
						</div><!-- /.modal -->
						
					</div><!-- /.page-content -->
				
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
</body>
</html>

