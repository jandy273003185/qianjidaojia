<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.ResultStatisticPath"%>
<%@page import="com.qifenqian.bms.accounting.checkquery.JgkjResultExceptionPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<%@page import="com.qifenqian.bms.accounting.checkquery.QfqexceptionPath"%>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>交广科技差错报表</title>
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
						   <li><a href="#result" data-toggle="tab" id="resultReport"> 结果统计报表</a></li>
						   <li><a href="#impeach"  data-toggle="tab"  id="impeachReport">交广科技存疑报表</a></li>
						   <li><a href="#qfq"  data-toggle="tab"  id="qfqReport">七分钱存疑报表</a></li>
						   <li><a href="#slip" data-toggle="tab"  id="slipReport">差错报表</a></li>
						   <li><a href="#fit" data-toggle="tab" id="fitReport">一致报表</a></li>
						</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									   <div class="tab-pane fade in active" id="result">						
									   </div>
									   <div class="tab-pane fade" id="impeach">
									   </div>
									   <div class="tab-pane fade" id="qfq">
									   </div>
									   <div class="tab-pane fade" id="slip">
									      	<div class="table-responsive">
												   	<form  id="tradeForm" action='<c:url value="<%=ResultStatisticPath.BASE + ResultStatisticPath.SLIPREPORT%>"/>'method="post">
													<table class="table search-table"  style="border:none;">	
														<tr>							    																																
															<td class="td-left" width="10%">对账日期</td>
															<td class="td-right" width="20%">
														 		 <input type="text" name="balDate"   id="balDate" value="${queryBean.balDate }" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
															</td>
															<td class="td-left" width="10%">会计日期</td>
															<td class="td-right" width="30%">
																 <input type="text" name="beginBalTime"  id="beginBalTime" value="${queryBean.beginBalTime }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
																	-
																 <input type="text" name="endBalTime"  id="endBalTime" value="${queryBean.endBalTime }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
															</td>
															<td class="td-left"  style="width:10%;">七分钱-交广清算流水号</td>
															<td class="td-right"  style="width:20%;">
																<span class="input-icon">
																	<input type="text"  name="clearId" value="${queryBean.clearId }" id="clearId" style="width:88%;">
																	<i class="icon-leaf blue"></i>
																</span>
															</td>
														</tr>	
														<tr>
															<td colspan="6" align="center">
															<gyzbadmin:function url="<%=ResultStatisticPath.BASE + ResultStatisticPath.SLIPREPORT%>">						
																<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm"   >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
															<button type="button" class="btn btn-purple btn-sm btn-margin clearSlip" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
															</button>
															</gyzbadmin:function> 
															<gyzbadmin:function url="<%=ResultStatisticPath.BASE + ResultStatisticPath.EXPORTSLIP%>">	
																<span class="input-group-btn" style="display:inline;">
																	<a class="btn btn-purple btn-sm"  id="exportSilpBut"  >
																		导出报表
																		<i class="icon-download bigger-110"></i> 
																	</a> 
																</span>		
															</gyzbadmin:function>																												
															</td>													
														</tr>
													</table>
												</form>
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>对账日期</th>
											<th>七分钱会计日期</th>
											<th>渠道名称</th>
											<th>七分钱-交广清算流水号</th>
											<th>交广平台流水号</th>
											<th>交易类型</th>
											<th>七分钱交易金额</th>
											<th>交广交易金额</th>
											<th>交易发送时间</th>
											<th>交易返回时间</th>	
											<th width="15%">差错信息</th>
										</tr>
									</thead>
									<tbody id="impeachData">
										<c:forEach items="${errorList}" var="result" >
											<tr class="result" >
												<td>${result.balDate}</td>
												<td>${result.workDate}</td>
												<td>${result.channelId}</td>
												<td>${result.clearId}</td>
												<td>${result.platformId }</td>
												<td>${result.transType}</td>
												<td>${result.transAmt}</td>
												<td>${result.transAmtC}</td>
												<td>${result.sendTime }</td>	
												<td>${result.resultTime }</td>	
												<td>${result.balMemo }</td>	
											</tr>											
										</c:forEach>
										<c:if test="${empty errorList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								</div>	
									<c:if test="${not empty errorList}">
										<%@ include file="/include/page.jsp"%>
									</c:if>				
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
		$("#exportSilpBut").click(function(){
			var balDate  =$("#balDate").val();
			var beginBalTime =$("#beginBalTime").val();
			var endBalTime =$("#endBalTime").val();
			var clearId =$("#clearId").val();
			var src="<%=request.getContextPath()+ResultStatisticPath.BASE+ResultStatisticPath.EXPORTSLIP%>?balDate="+balDate+
					"&endBalTime="+endBalTime+
					"&beginBalTime="+beginBalTime+
					"&clearId="+clearId+"";
			$("#exportSilpBut").attr("href",src);
		})
		//按条件查询
		$("#serchSubmit").click(function(){
			var beginBalTime =$("#beginBalTime").val();
			var endBalTime= $("#endBalTime").val();
			if(''!=beginBalTime&&''!=endBalTime){
				if(beginBalTime > endBalTime) 
				{
					alert("结束日期不能小于开始日期");
					return false;
				}
			}
			var form = $('#tradeForm');
			form.submit();
		});
		
		$('.clearSlip').click(function(){
			$(':input','#tradeForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
	     $('#myTab li:eq(3) a').tab('show');	    
	 });
	</script>
</body>
</html>		