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
	<title>对账结果统计报表</title>
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
						   		<li><a href="#sevenmall_data" data-toggle="tab"  id="sevenmall_data_Report">聚合源数据表</a></li>
						   		<li><a href="#sevenmall_result_equal" data-toggle="tab"  id="sevenmall_result_equal_Report">聚合对账一致表</a></li>
						   		<li><a href="#sevenmall_result_exception" data-toggle="tab"  id="sevenmall_result_exception_Report">聚合对账异常表</a></li>
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="result">
							   			<form action='<c:url value="<%=AccountResultPath.BASE + AccountResultPath.STATISTICSLIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left">状态</td>
												<td class="td-right">
													<span class="input-icon">
														<select name="status" id="status">
															<option value="">--请选择--</option>
															<c:forEach items="<%=BalResultStatus.values() %>" var="status">
																<option value="${status }" <c:if test="${status == balBatchResultStatistic.status }">selected</c:if>>--${status.desc }--</option>
															</c:forEach>
														</select>
													</span>
												</td>
												
												<td class="td-left" width="15%">渠道</td>
												<td class="td-right" width="15%">
													<span class="input-icon">
													<select name="channelId" id="channelId">
														<option value="">--请选择--</option>
														<c:forEach items="${baseChannels}" var="channel">
															<option value="${channel.channelId }" <c:if test="${channel.channelId==balBatchResultStatistic.channelId}">selected</c:if>>${channel.channelName }</option>
														</c:forEach>
													</select>
													</span>
												</td>
												
												<td class="td-left" width="15%">会计日期</td>
												<td class="td-right" width="35%">
													<span class="input-icon">
														<input type="text" name="workDateMin"  id="workDateMin" value="${balBatchResultStatistic.workDateMin }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														-
														<input type="text" name="workDateMax"  id="workDateMax" value="${balBatchResultStatistic.workDateMax }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=AccountResultPath.BASE + AccountResultPath.STATISTICSLIST %>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=AccountResultPath.BASE + AccountResultPath.STATISTICSEXPORT%>">
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
										<div class="list-table-header">对账结果统计报表</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>批次编号</th>
															<th>对账日期</th>
															<th>会计日期</th>
															<th>文件编号</th>
															<th>渠道</th>
															<th>数据方</th>
															<th>交易结果</th>
															<th>统计类型</th>
															<th>币别</th>
															<!-- <th>数据平台</th> -->
															<th>总笔数</th>
															<th>总金额</th>
															<th>对账一致笔数</th>
															<th>对账一致总额</th>
															<th>对账存疑笔数</th>
															<th>对账存疑总额</th>
															<th>对账差错笔数</th>
															<th>对账差错总额</th>
															<th>对账自身存疑笔数</th>
															<th>对账自身存疑总额</th>
															<th>写入时间</th>
															<th>状态</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${resultList }" var="statistic">
														<tr class="statistic">
															<td>${statistic.batchId }</td>
															<td>${statistic.balDate }</td>
															<td>${statistic.workDate }</td>
															<td>${statistic.fileId }</td>
															<td>${statistic.channelName }</td>
															<td>${statistic.dataOwner }</td>															
															<td>${statistic.transStatus }</td>
															<td>${statistic.transType }</td>
															<td>${statistic.transCurrency }</td>
															<%-- <td>${statistic.systemDesc }</td> --%>
															<td>${statistic.totalCount }</td>
															<td>${statistic.totalAmt }</td>
															<td>${statistic.balEqualCount }</td>
															<td>${statistic.balEqualAmt }</td>
															<td>${statistic.balDoubtCount }</td>
															<td>${statistic.balDoubtAmt }</td>
															<td>${statistic.balErrorCount }</td>
															<td>${statistic.balErrorAmt }</td>
															<td>${statistic.selfDoubtCount }</td>
															<td>${statistic.selfDoubtAmt }</td>
															<td>${statistic.instDatetime }</td>
															<td>${statistic.status}</td>
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
	
	 
	
	$(function () {	
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		
		//按条件查询
		$("#searchSubmit").click(function(){
			
			var balDate =$("#workDateMin").val();
			//alert(balDate)
			var endBalTime= $("#workDateMax").val();
			if(''!=balDate&&''!=endBalTime){
				if(balDate > endBalTime) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			}
		
		});

		$(".exportBut").click(function(){
			
			var workDateMin = $('.search-table #workDateMin').val();
			var workDateMax = $('.search-table #workDateMax').val();
			var status = $('.search-table #status').val();
			
			var src ="<%= request.getContextPath()+ AccountResultPath.BASE+AccountResultPath.STATISTICSEXPORT%>?status="+
				status+
				"&workDateMin="+
				workDateMin+
				"&workDateMax="+
				workDateMax;
			
			$(".exportBut").attr("href",src);
		}); 
		
		
		var flag2=true;
		 $("#resultReport").click(function(){
			 flag2=true;
				window.location.href="<%=request.getContextPath()+AccountResultPath.BASE + AccountResultPath.STATISTICSLIST%>"
			 });
		 $("#external_data_Report").click(function(){
			 
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
		if(flag2==true){
	    	   $('#myTab li:eq(0) a').tab('show');	    
	       }
	    
	   });
	</script>
</body>
</html>					