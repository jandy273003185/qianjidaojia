<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.workflow.user.controller.UserPath"%>
<%@page import="com.qifenqian.bms.platform.web.myWorkSpace.WorkSpacePath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的待办事项</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		
		$('.btn-sm').click(function(){
			var startDate =$("#taskBeginTime").val();
			var endDate= $("#taskEndTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				alert("结束日期不能小于开始日期");
				return false;
			}
			var form = $('#watingTaskForm');
			form.submit();
		});
	});
	

	
</script>
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
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				
				<!-- 主内容 -->
				<div class="page-content">
				
					<form action='<c:url value="<%=WorkSpacePath.BASE+WorkSpacePath.TASKS %>"/>' method="post" id="watingTaskForm">
							<table class="search-table">
								<tr>
									<td class="td-left" >任务编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="id" id="id" placeholder="任务编号" value="${id }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">任务名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="name" id="name" placeholder="任务名称"  value="${name }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">申请人</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text"  name="startUser" id="startUser" placeholder="申请人"  value="${startUser }">
												<i class="icon-leaf blue"></i>
											</span>
									</td>
									<td class="td-left">申请开始日期</td>
									<td class="td-right" style="width:390px;">
										 <input type="text" name="taskBeginTime"  id="taskBeginTime" readonly="readonly" value="${taskBeginTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="taskEndTime" id="taskEndTime" readonly="readonly" value="${taskEndTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center" >
										<span class="input-group-btn">
											
											<button type="button" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</span>
									</td>
								</tr>
							</table>
							</form>
					<div class="row">
						<div class="col-xs-12">
							
							<div class="list-table-header">待办事项</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>任务编号</th>
											<th>申请单编号</th>
											<th>任务名称</th>
											<th>任务流程名称</th>
											<th>申请人</th>
											<th>提交时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${tasks }" var="task">
										<tr class="task">
											<td><a href="<%=request.getContextPath()%>${task.url }?processInstanceId=${task.procInstId}">${task.id }</a></td>
											<td>${task.procInstId }</td>
											<td>${task.name }</td>
											<td>${task.proName }</td>
											<td>${task.startUser }</td>
											<td>
												<fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>
												<a href="<%=request.getContextPath()+WorkSpacePath.BASE + WorkSpacePath.URL%>?taskId=${task.id }"  class="tooltip-success transactionLink" title="办理任务" >
													<span class="green"><i class="icon-edit bigger-120"></i></span>
												</a>
											
												<%-- <gyzbadmin:function url="<%=UserPath.BASE + UserPath.DELETE %>">
													<a href="#" class="tooltip-error deleteUserLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteUserModal">
														<span class="red"><i class="icon-trash bigger-120"></i></span>
													</a>
												</gyzbadmin:function> --%>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty tasks}">
										<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty tasks}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
							
							
						</div>
					</div>
					
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