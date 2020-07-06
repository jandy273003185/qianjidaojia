<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.sns.balance.RedenvResultPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>红包对账结果统计报表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadResultStatistic()">
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
						   		<li><a href="#resultStatistic" data-toggle="tab" id="resultStatisticReport">结果统计报表</a></li>
						   		<li><a href="#resultEqual"  data-toggle="tab"  id="resultEqualReport">一致报表</a></li>
						    	<li><a href="#resultFailure"  data-toggle="tab"  id="resultFailureReport">差错报表</a></li>
						   		<li><a href="#redenvDoubt" data-toggle="tab"  id="redenvDoubtReport">红包存疑报表</a></li>
						   		<li><a href="#sevenDoubt" data-toggle="tab"  id="sevenDoubtReport">七分钱存疑报表</a></li>
						   		<li><a href="#resultSummary" data-toggle="tab"  id="resultSummaryReport">红包汇总报表</a></li>
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active">
							   			<form action='<c:url value="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left" width="10%">对账日期</td>
												<td class="td-right" width="20%">
													<span class="input-icon">
														<input type="text" name="balDate"  id="balDate" value="${queryBean.balDate}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">会计日期</td>
												<td class="td-right" width="30%">
													<span class="input-icon">
														<input type="text" name="workBeginDate"  id="workBeginDate" value="${queryBean.workBeginDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														-
														<input type="text" name="workEndDate"  id="workEndDate" value="${queryBean.workEndDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">状态</td>
												<td class="td-right" width="20%">
													<span class="input-icon">
														<input type="hidden" name="transTypeTemp"  id="transTypeTemp" value = "${queryBean.transType}"/>
														<select name="transType" id="transType">
															<option value="">--请选择--</option>
															<option value="RECHARGE">--红包入账--</option>
															<option value="PAYMENT">--红包支付--</option>
															<option value="REFUND">--红包退款--</option>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC%>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC_EXPORT%>">
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
															<th>对账批次</th> 
															<th>对账日期</th> 
															<th>会计日期</th> 
															<th>系统</th> 
															<th>交易结果</th> 
															<th>交易类型</th> 
															<th>总笔数</th> 
															<th>总金额</th> 
															<th>自身存疑笔数</th>
															<th>自身存疑金额</th> 
															<th>存疑笔数</th> 
															<th>存疑金额</th> 
															<th>差错笔数</th> 
															<th>差错金额</th> 
															<th>一致笔数</th> 
															<th>一致金额</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${resultStatisticList}" var="statistic">
														<tr class="statistic">
															<td>${statistic.batchId }</td>
															<td>${statistic.balDate }</td>
															<td>${statistic.workDate }</td>
															<td>${statistic.system }</td>
															<td>${statistic.transStatus }</td>
															<td>${statistic.transType }</td>
															<td>${statistic.totalCount }</td>
															<td>${statistic.totalAmt }</td>
															<td>${statistic.selfDoubtCount }</td>
															<td>${statistic.selfDoubtAmt}</td>
															<td>${statistic.balDoubtCount }</td>
															<td>${statistic.balDoubtAmt }</td>
															<td>${statistic.balErrorCount }</td>
															<td>${statistic.balErrorAmt }</td>
															<td>${statistic.balEqualCount }</td>
															<td>${statistic.balEqualAmt }</td>
														</tr>
													</c:forEach>
													<c:if test="${empty resultStatisticList}">
														<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty resultStatisticList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									   </div>
									   <div class="tab-pane fade" id="resultEqual"></div>
									   <div class="tab-pane fade" id="resultFailure"></div>
									   <div class="tab-pane fade" id="redenvDoubt"></div>
									   <div class="tab-pane fade" id="sevenDoubt"></div>
									   <div class="tab-pane fade" id="resultSummary"></div>
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
	function loadResultStatistic(){
		$('.search-table #transType').val($('.search-table #transTypeTemp').val());
	}

	 $("#resultStatisticReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC%>"
	 });
	 
	 $("#resultEqualReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_EQUAL%>";
	 });
	 
	 $("#resultFailureReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_FAILURE%>";
	 });
	 
	 $("#redenvDoubtReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.REDENV_DOUBT%>";
	 });
	 
	 $("#sevenDoubtReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.SEVEN_DOUBT%>";
	 });
	 
	 $("#resultSummaryReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY%>";
	 });
	
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
			var balDate =$("#workBeginDate").val();
			var endBalTime= $("#workEndDate").val();
			if(''!=balDate&&''!=endBalTime){
				if(balDate > endBalTime) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			}
		});

		$(".exportBut").click(function(){
			var balDate = $('.search-table #balDate').val();
			var workBeginDate = $('.search-table #workBeginDate').val();
			var workEndDate = $('.search-table #workEndDate').val();
			var transType = $('.search-table #transType').val();
			var src ="<%= request.getContextPath()+ RedenvResultPath.BASE+RedenvResultPath.RESULT_STATISTIC_EXPORT%>?transType="+
				transType+
				"&balDate="+
				balDate+
				"&workBeginDate="+
				workBeginDate+
				"&workEndDate="+
				workEndDate;
			$(".exportBut").attr("href",src);
		});
		
    	$('#myTab li:eq(0) a').tab('show');	
	   });
	</script>
</body>
</html>					