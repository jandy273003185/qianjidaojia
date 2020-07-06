<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.bal.accountResult.controller.AccountResultPath"%>
<%@page import="com.qifenqian.bms.common.BalResultStatus"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>外部数据源报表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body>
	<!-- 科目配置信息 -->
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
							<ul id="myTab" class="nav nav-tabs">
						   		<li class="active"><a href="#result" data-toggle="tab" id="resultReport">对账结果统计报表</a></li>
						   		<li><a href="#external_data"  data-toggle="tab"  id="external_data_Report">外部系统源数据</a></li>
						    	<li><a href="#external_result_equal"  data-toggle="tab"  id="external_result_equal_Report">外部系统对账一致表</a></li>
						   		<li><a href="#external_result_exception" data-toggle="tab"  id="external_result_exception_Report">外部系统对账异常表</a></li>
						   		<li><a href="#internal_data" data-toggle="tab"  id="internal_data_Report">聚合源数据表</a></li>
						   		<li><a href="#internal_result_equal" data-toggle="tab"  id="internal_result_equal_Report">聚合对账一致表</a></li>
						   		<li><a href="#internal_result_exception" data-toggle="tab"  id="internal_result_exception_Report">聚合对账异常表</a></li>
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="external_data">
							   			<form action='<c:url value="<%=AccountResultPath.BASE + AccountResultPath.EXTERNALDATALIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left">源数据编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="dataId" id="dataId" value="${balExternalData.dataId }">
													</span>
												</td>
												<td class="td-left">渠道</td>
												<td class="td-right" >
													<span class="input-icon">
													<select name="channelId" id="channelId">
														<option value="">--请选择--</option>
														<c:forEach items="${baseChannels}" var="channel">
															<option value="${channel.channelId }" <c:if test="${channel.channelId==balExternalData.channelId}">selected</c:if>>${channel.channelName }</option>
														</c:forEach>
													</select>
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">文件编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="fileId" id="fileId" value="${balExternalData.fileId }">
													</span>
												</td>
												<td class="td-left">清算编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="clearId" id="clearId" value="${balExternalData.clearId }">
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">会计日期</td>
												<td class="td-right" >
													<span class="input-icon">
														<input type="text" name="workDate"  id="workDate" value="${balExternalData.workDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=AccountResultPath.BASE + AccountResultPath.EXTERNALDATALIST %>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=AccountResultPath.BASE + AccountResultPath.EXTERNALDATAEXPORT%>">
															<span class="input-group-btn" style="display:inline;">
																<a class="btn btn-purple btn-sm exportBut">
																	导出报表
																</a> 
															</span>
														</gyzbadmin:function>
													</span>
												</td>
											</tr>
										</table>
										</form>
										<div class="list-table-header">外部源数据报表</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>源数据编号</th>
															<th>渠道</th>
															<th>文件编号</th>
															<th>清算编号</th>
															<th>会计日期</th>
															<th>初始写入人</th>
															<th>写入日期</th>
															<th>详细信息</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${resultList }" var="externalData">
														<tr class="statistic">
															<td>${externalData.dataId }</td>
															<td>${externalData.channelName }</td>
															<td>${externalData.fileId }</td>
															<td>${externalData.clearId }</td>
															<td>${externalData.workDate }</td>
															<td>${externalData.instUser }</td>
															<td>${externalData.instDate }</td>
															<td width="400">${externalData.details}</td>
														</tr>
													</c:forEach>
													<c:if test="${empty resultList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty resultList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									   </div>
									</div>
								</div>	
							</div>
						</div>
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
		var flag=false;
		var flag2=true;
		$("#resultReport").click(function(){
			 
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.STATISTICSLIST%>"
			 });
		 
		 $("#external_data_Report").click(function(){
			 flag2=true;
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.EXTERNALDATALIST%>";
			 });
		 $("#external_result_equal_Report").click(function(){
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.EXTERNALRESULTEQUALLIST%>";
			 });
		 $("#external_result_exception_Report").click(function(){
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.EXTERNALRESULTEXCEPTIONLIST%>";
			 });
		 $("#internal_data_Report").click(function(){
			 flag2=true;
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.BALINTERNALDATALIST%>";
			 }); 
		 $("#internal_result_equal_Report").click(function(){
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.BALINTERNALRESULTEQUALLIST%>";
			 }); 
		 $("#internal_result_exception_Report").click(function(){
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.BALINTERNALRESULTEXCEPTIONLIST%>";
			 });
	$(function () {	
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		$("#searchSubmit").click(function(){
			
			var dataId = $('.search-table #dataId').val();
			if(!kong.test(dataId)){
				var data = document.getElementById("dataId");
				if(!isNumber(data)){
					$.gyzbadmin.alertFailure("源数据编号必须为数字", null, null);
					return false;
				}
			}
			var channelId = $('.search-table #channelId').val();
			if(!kong.test(channelId)){
				var channel = document.getElementById("channelId");
				if(!isNumber(channel)){
					$.gyzbadmin.alertFailure("渠道编号必须为数字", null, null);
					return false;
				}
				
			}
		});
		
		$(".exportBut").click(function(){
			
			var dataId = $('.search-table #dataId').val();
			if(!kong.test(dataId)){
				var data = document.getElementById("dataId");
				if(!isNumber(data)){
					$.gyzbadmin.alertFailure("源数据编号必须为数字", null, null);
					return false;
				}
			}
			var channelId = $('.search-table #channelId').val();
			if(!kong.test(channelId)){
				var channel = document.getElementById("channelId");
				if(!isNumber(channel)){
					$.gyzbadmin.alertFailure("渠道编号必须为数字", null, null);
					return false;
				}
				
			}
			
			var fileId = $('.search-table #fileId').val();
			var clearId = $('.search-table #clearId').val();
			var workDate = $('.search-table #workDate').val();
			
			var src ="<%= request.getContextPath()+ AccountResultPath.BASE+AccountResultPath.EXTERNALDATAEXPORT%>?dataId="+
				dataId+
				"&channelId="+
				channelId+
				"&fileId="+
				fileId+
				"&clearId="+
				clearId+"&workDate="+workDate;
			
			$(".exportBut").attr("href",src);
		}); 
		if(flag2==true){
	    	   $('#myTab li:eq(1) a').tab('show');	    
	       }
	       
	   });
	</script>
</body>
</html>					