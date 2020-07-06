<%@page import="com.qifenqian.bms.platform.web.admin.filter.GeneralFilter"%>
<%@page import="com.qifenqian.bms.platform.common.config.SystemInfo"%>
<%@page import="com.qifenqian.bms.platform.common.utils.SpringUtils"%>
<%@page import="com.qifenqian.bms.platform.web.admin.login.LoginPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style type="text/css">
	.logout:hover{text-decoration: none}
	.updatePwd:hover{text-decoration: none}
</style>

<script type="text/javascript">
	
	jQuery(function($) {
		$('.logout').click(function(){
			$.post(window.Constants.ContextPath + '<%=LoginPath.LOGOUT%>', {
				}, function(data) {
					if(data.result == 'SUCCESS'){
						window.location.href = window.Constants.ContextPath + '<%=LoginPath.ROOT%>';
					} else {
						alert('登出失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		$('.updatePwd').click(function(){
			window.location.href = window.Constants.ContextPath + '<%=LoginPath.TOUPDATEPWD%>';
		});
	});

</script>

<!-- 公共弹出层 -->
<%@ include file="/include/alert.jsp"%>

<div class="navbar navbar-default" id="navbar" style="height:47px;">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-leaf"></i>
					<%=SpringUtils.getBean(SystemInfo.class).getSystemName().split(",")[0]%>
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">

				<li class="light-blue">
					<% if(null != user) { %> 
					<span style="color:#FFFFFF"><a href=<%=SpringUtils.getBean(SystemInfo.class).getSystemUrl().split(",")[1]%> style="color: #FFFFFF;"><%=SpringUtils.getBean(SystemInfo.class).getSystemName().split(",")[1]%></a>&nbsp;|</span>
					<span style="color:#FFFFFF"><%=user.getRealName() %>&nbsp;|</span>
					<span style="color:#FFFFFF">员工编号：<%=user.getUserId() %>&nbsp;|</span>
					<span style="color:#FFFFFF"><%=user.getDeptName() %>&nbsp;|</span>
					<span style="color:#FFFFFF"><a href="#" class="updatePwd" style="color: #FFFFFF;"><i class="icon-edit"></i>修改密码</a>&nbsp;|</span>
					<span><a href="#" class="logout" style="color: #FFFFFF;"><i class="icon-off"></i>退出</a></span>
					
					<% } %> 
				</li>
			</ul><!-- /.ace-nav -->
		</div><!-- /.navbar-header -->
	</div><!-- /.container -->
</div>

		