<%@page import="com.qifenqian.bms.accounting.checkquery.type.BalResultStatus"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.type.ChannelId"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.ResultStatisticPath"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.JgkjResultExceptionPath"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.QfqexceptionPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>交广科技对账结果统计报表</title>
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
						   		<li><a href="#result" data-toggle="tab" id="resultReport">结果统计报表</a></li>
						   		<li><a href="#impeach"  data-toggle="tab"  id="impeachReport">交广科技存疑报表</a></li>
						    	<li><a href="#qfq"  data-toggle="tab"  id="qfqReport">七分钱存疑报表</a></li>
						   		<li><a href="#slip" data-toggle="tab"  id="slipReport">差错报表</a></li>
						   		<li><a href="#fit" data-toggle="tab"  id="fitReport">一致报表</a></li>
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active">
							   			<form action='<c:url value="<%=ResultStatisticPath.BASE + ResultStatisticPath.LIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left" width="10%">对账日期</td>
												<td class="td-right" width="20%">
													<span class="input-icon">
														<input type="text" name="balDate"  id="balDate" value="${selectBean.balDate}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">会计日期</td>
												<td class="td-right" width="30%">
													<span class="input-icon">
														<input type="text" name="workDateMin"  id="workDateMin" value="${selectBean.workDateMin }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														-
														<input type="text" name="workDateMax"  id="workDateMax" value="${selectBean.workDateMax }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">状态</td>
												<td class="td-right" width="20%">
													<span class="input-icon">
														<select name="status" id="status">
															<option value="">--请选择--</option>
															<c:forEach items="<%=BalResultStatus.values()%>" var="status">
																<option value="${status }" <c:if test="${status == selectBean.status }">selected</c:if>>--${status.desc }--</option>
															</c:forEach>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=ResultStatisticPath.BASE + ResultStatisticPath.LIST%>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=ResultStatisticPath.BASE + ResultStatisticPath.EXPORT%>">
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
															<th>对账日期</th>
															<th>会计日期</th>
															<th>渠道</th>
															<th>交易结果</th>
															<th>业务类型</th>
															<th>数据平台</th>
															<th>总笔数</th>
															<th>总金额</th>
															<th>一致笔数</th>
															<th>一致总额</th>
															<th>存疑笔数</th>
															<th>存疑总额</th>
															<th>差错笔数</th>
															<th>差错总额</th>
															<th>自身存疑笔数</th>
															<th>自身存疑总额</th>
															<th>状态</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${statisticList }" var="statistic">
														<tr class="statistic">
															<td>${statistic.balDate }</td>
															<td>${statistic.workDate }</td>
															<td>${statistic.channelIdDesc }</td>
															<td>${statistic.transStatusDesc }</td>
															<td>${statistic.transTypeDesc }</td>
															<td>${statistic.systemDesc }</td>
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
															<td>${statistic.statusDesc }</td>
														</tr>
													</c:forEach>
													<c:if test="${empty statisticList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty statisticList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									   </div>
									  
									   <div class="tab-pane fade" id="impeach">
									   </div>
									   <div class="tab-pane fade" id="qfq">
									   </div>
									   <div class="tab-pane fade" id="slip">
									   </div>
									   <div class="tab-pane fade" id="fit">
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

	 $("#resultReport").click(function(){
		 window.location.href="<%=request.getContextPath()+ResultStatisticPath.BASE + ResultStatisticPath.LIST%>"
	 });
	 
	 $("#impeachReport").click(function(){
		 window.location.href="<%=request.getContextPath()+JgkjResultExceptionPath.BASE + JgkjResultExceptionPath.JGKJLIST%>";
	 });
	 
	 $("#qfqReport").click(function(){
		 window.location.href="<%=request.getContextPath()+QfqexceptionPath.BASE + QfqexceptionPath.QFQLIST%>";
	 });
	 
	 $("#slipReport").click(function(){
		 window.location.href="<%=request.getContextPath()+ResultStatisticPath.BASE + ResultStatisticPath.SLIPREPORT%>";
	 });
	 
	 $("#fitReport").click(function(){
		 window.location.href="<%=request.getContextPath()+ResultStatisticPath.BASE + ResultStatisticPath.FITREPORT%>";
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
			var balDate =$("#workDateMin").val();
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
			var channelId = $('.search-table #channelId').val();
			var balDate = $('.search-table #balDate').val();
			var workDateMin = $('.search-table #workDateMin').val();
			var workDateMax = $('.search-table #workDateMax').val();
			var status = $('.search-table #status').val();
			var src ="<%= request.getContextPath()+ ResultStatisticPath.BASE+ResultStatisticPath.EXPORT%>?channelId="+
				channelId+
				"&status="+
				status+
				"&balDate="+
				balDate+
				"&workDateMin="+
				workDateMin+
				"&workDateMax="+
				workDateMax;
			$(".exportBut").attr("href",src);
		});
		
    	$('#myTab li:eq(0) a').tab('show');	
    	
	   });
	</script>
</body>
</html>					