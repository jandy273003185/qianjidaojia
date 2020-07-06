<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.schedulerLog.SchedulerLogPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>调度日志</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadSchedulerLog()">
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
						<div class="col-xs-12">
						 <input type="hidden"  name="idval"  id="idval" />
				         <input type="hidden"  name="executeFlag3"  id="executeFlagTemp" value="${queryBean.executeFlag}"/>
				         <input type="hidden"  name="executeType3"  id="executeTypeTemp" value="${queryBean.executeType}"/>
							<!-- 查询条件 -->
							<form  id="tradeForm"  method="post" action='<c:url value="<%=SchedulerLogPath.BASE + SchedulerLogPath.LIST %>"/>' >
							<table class="search-table">	
								<tr>							    																																
									<td class="td-left" width="15%">执行日期</td>
									<td class="td-right" width="35%">
								 		 <input type="text" name="executeDate"   id="executeDate" value="${queryBean.executeDate}" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left" width="15%">执行状态</td>
									<td class="td-right" width="35%">
										<select id="executeFlag"  name="executeFlag">
										  <option value ="">---请选择---</option>
										  <option value ="EXECUTING">执行中</option>
										  <option value="SUCCESS">成功</option>
										  <option value="FAILURE">失败</option>
										  <option value="EMPTY">参数为空</option>
										</select>
									</td>
								</tr>	
								<tr>
									<td class="td-left" width="15%">任务名称</td>
									<td class="td-right" width="35%">
										<span class="input-icon">
											 <input type="text" name="jobName"   id="jobName" value="${queryBean.jobName}" >
											 <i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left"  style="width:15%;">触发类型</td>
									<td class="td-right"  style="width:35%;">
										<select id="executeType"  name="executeType">
										  <option value ="">---请选择---</option>
										  <option value ="HANDLE">手动</option>
										  <option value="AUTO">自动</option>
										</select>
									</td>
								</tr>	
								<tr>
									<td colspan="4" align="center" >
										<gyzbadmin:function url="<%=SchedulerLogPath.BASE + SchedulerLogPath.LIST %>">		
										<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm"  >
											查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
										</gyzbadmin:function>
									 	<button class="btn btn-purple btn-sm btn-margin clearschedulerLog" >
											清空
											<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
									</td>													
							   </tr>
							</table>
						</form>							
							<div class="list-table-header" >
								调度日志
							</div>

						<div class="table-responsive">
						<table id="sample-table-2" class="list-table">
						  <thead>
							<tr>
								<th style="width:8%">执行日期</th>
								<th style="width:10%">任务名称</th>
								<th style="width:12%">任务参数</th>
								<th style="width:6%">执行主机名</th>
								<th style="width:12%">执行开始时间</th>
								<th style="width:12%">执行结束时间</th>
								<th style="width:5%">执行状态</th>
								<th style="width:5%">触发类型</th>
								<th style="width:15%">详细描述</th>
								<th style="width:5%">执行人</th>										
							</tr>
						  </thead>
						<tbody id="impeachData">
							<c:forEach items="${schedulerLogList}" var="result" >
								<tr class="result" >
									<td>${result.executeDate}</td>
									<td>${result.jobName}</td>
									<td>${result.parametr}</td>
									<td>${result.hostname}</td>
									<td>
										<fmt:formatDate value="${result.startDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
									</td>
									<td>
										<fmt:formatDate value="${result.endDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
									</td>
									<td>${result.executeFlag2}</td>
									<td>${result.executeType2}</td>
									<td>${result.executeDesc }</td>	
									<td>${result.executeOper }</td>																						
								</tr>											
							</c:forEach>
								<c:if test="${empty schedulerLogList}">
									<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody>
						</table>
						<!-- 分页 -->
						<c:if test="${not empty schedulerLogList}">
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
<script type="text/javascript">

	function loadSchedulerLog(){
		$("#executeFlag").val($("#executeFlagTemp").val());
		$("#executeType").val($("#executeTypeTemp").val());
	}
	$(document).ready(function(){
		$('.clearschedulerLog').click(function(){
			$('.search-table #executeDate').val('');
			$('.search-table #executeFlag').val('');
			$('.search-table #executeType').val('');
			$('.search-table #jobName').val('');
		}) 
	});
</script>
</body>
</html>