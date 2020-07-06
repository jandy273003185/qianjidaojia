<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.workflow.WorkflowPath" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>请假</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
 <div>
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				<!-- 主内容 -->
				
					
					<div class="list-table-header">申请请假</div>
						<table class="search-table">
							<tr>
								<td>
								<label>领导：</label>
								<select id="userId" name="userId">
									<option value="U000001">马会全</option>
									<option value="U000003">沈崇淦</option>
									<option value="U000005">吴瑊</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>
									<input type="button" value="申请请假" id="leaveOrder">
								</td>
								
							</tr>
						</table>
				      
					</div>
				
				<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
	 		   
					  
<script type="text/javascript">
	$(function(){
		$("#leaveOrder").click(function(){
			$.post(window.Constants.ContextPath +'<%=WorkflowPath.BASE+WorkflowPath.LEAVE%>',{
				userId : $("#userId").val()
	 	},function(data){
	 		$.unblockUI();
			if(data.result=="SUCCESS"){
				$.gyzbadmin.alertSuccess("已提交申请");
			}else{
				$.gyzbadmin.alertFailure("申请提交失败"+data.message);
			}
	 	},'json');
		});
	});
</script>				  
</body>
</html>

