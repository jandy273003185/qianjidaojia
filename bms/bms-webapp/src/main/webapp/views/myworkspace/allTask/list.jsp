<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.myworkspace.allTask.AllTaskPath"%>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>

<html>
<head>
	<meta charset="utf-8" />
	<title>所有任务</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
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
			var form = $('#allTaskForm');
			form.submit();
		});
		
		
		$(".preview").click(function(){
			var taskId = $(this).parent().siblings(".taskId").text();
			
			$.post(window.Constants.ContextPath + '<%=AllTaskPath.BASE + AllTaskPath.VIEW %>',{
				'taskId' : taskId
			},function(data){
				if(data.result=='SUCCESS'){
					$("#viewFormModal .id").text(data.task.id);
					$("#viewFormModal .procDefId").text(data.task.procDefId);
					$("#viewFormModal .name").text(data.task.name);
					$("#viewFormModal .proName").text(data.task.proName);
					$("#viewFormModal .realName").text(data.task.realName);
					if(data.task.endTime==null){
						$("#viewFormModal .state").text('未审核');
					}
					if(data.task.endTime!=null){
						$("#viewFormModal .state").text('已审核');
					}
					$("#viewFormModal .startTime").text(format1(data.task.startTime,"yyyy-MM-dd"));
					$("#viewFormModal .endTime").text(format1(data.task.endTime,"yyyy-MM-dd"));
					
				}
			},'json'
			);
		});
		
		
	});
	function load(){
		var value = $("#hiddenAuditStatu").val();
		$("#auditStatu").val(value);
	}
</script>
<body onload="load()">
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
				
					<div class="row">
						<form action='<c:url value="<%=AllTaskPath.BASE+AllTaskPath.LIST%>"/>' method="post" id="allTaskForm">
							<input type="hidden" id="hiddenAuditStatu" value="${auditStatu }">
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
											<input type="text"  name="startUserId" id="startUserId" placeholder="申请人"  value="${startUserId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">任务开始日期</td>
									<td class="td-right" style="width:390px;">
										 <input type="text" name="taskBeginTime"  id="taskBeginTime" readonly="readonly" value="${taskBeginTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="taskEndTime" id="taskEndTime" readonly="readonly" value="${taskEndTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								
								<tr>
									<td class="td-left">审批状态</td>
									<td class="td-right">
										 <select id="auditStatu" name="auditStatu">
										 	<option value="">--选择审批状态--</option>
										 	<option value="yes">已审批</option>
										 	<option value="no">未审批</option>
										 </select>
									</td>
									<td colspan="2" align="center" >
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
					
						<div class="col-xs-12">
							<div class="list-table-header">
								所有任务
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>任务编号</th>
											<th>申请单编号</th>
											<th>任务名称</th>
											<th>申请人</th>
											<th>任务流名称</th>
											<th>审批状态</th>
											<th>任务开始时间</th>
											<th>任务办理时间</th>
											<th></th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${allTask}" var="task">
											<tr >
												<td class="taskId">${task.id }</td>
												<td>${task.procInstId }</td>
												<td>${task.name }</td>
												<td>${task.realName }</td>
												<td>${task.proName }</td>
												<td>
													<c:if test="${task.endTime==null}">
														未审核
													</c:if>
													<c:if test="${task.endTime!=null}">
														已审核
													</c:if>
												</td>
												<td>
												<fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
												<fmt:formatDate value="${task.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
												<!-- <a href="#"  data-toggle='modal' class="tooltip-error preview" data-rel="tooltip" title="详情" data-target="#viewFormModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
												</a> -->
												<c:if test="${task.endTime==null }">
													<a href="<%=request.getContextPath()+AllTaskPath.BASE + AllTaskPath.URL%>?taskId=${task.id }"  class="tooltip-success transactionLink" title="办理任务" >
													<span class="green"><i class="icon-edit bigger-120"></i></span>
												</a>
												</c:if>
												
												</td>
												
											</tr>
										</c:forEach>
										
										<c:if test="${empty allTask}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty allTask}">
								<%@ include file="/include/page.jsp"%>
							</c:if> 
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
	
	<div class="modal fade" id="viewFormModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 700px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">任务详情</h4>
		         </div>
		         <div class="modal-body">
		           <table id="sample-table-2" class="list-table" >
								<tr>
									<td class="td-left" width="22%" >任务编号：</td>
									<td class="td-right" width="25%" > 
										<span class="id"></span>
									</td>
							
									<td class="td-left" width="22%" >任务流编号：</td>
									<td class="td-right" width="30%"> 
										<span class="procDefId"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="22%" >任务名称：</td>
									<td class="td-right" width="25%" > 
										<span class="name"></span>
									</td>
							
									<td class="td-left" width="22%" >任务流名称：</td>
									<td class="td-right" width="30%"> 
										<span class="proName"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="22%" >申请人：</td>
									<td class="td-right" width="25%" > 
										<span class="realName"></span>
									</td>
							
									<td class="td-left" width="22%" >审批状态：</td>
									<td class="td-right" width="30%"> 
										<span class="state"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="22%" >任务提交时间：</td>
									<td class="td-right" width="25%" > 
										<span class="startTime"></span>
									</td>
							
									<td class="td-left" width="22%" >任务完成时间：</td>
									<td class="td-right" width="30%"> 
										<span class="endTime"></span>
									</td>
								</tr>
								
							</table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>

