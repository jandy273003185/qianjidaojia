<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%@page import="com.qifenqian.bms.accounting.unionpayQuery.UnionpayQueryPath"%>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>七分钱存疑查询</title>
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
						   <li><a href="#result" data-toggle="tab" id="resultReport">结果报表</a></li>
						   <li><a href="#impeach" data-toggle="tab"  id="impeachReport">银联存疑报表</a></li>
						    <li ><a href="#qfqImpeach" data-toggle="tab" id="qfqReport">七分钱存疑报表</a></li>
						   <li><a href="#slip" data-toggle="tab"  id="slipReport">差错报表</a></li>
						   <li><a href="#fit" data-toggle="tab" id="fitReport">一致报表</a></li>
						</ul>

							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									   <div class="tab-pane fade in active" id="qfqImpeach">
									      	<div class="table-responsive">
												  <form  id="tradeForm"  action='<c:url value="<%=UnionpayQueryPath.BASE + UnionpayQueryPath.QFQIMPEACH%>"/>' method="post">
													<table class="table search-table"  style="border:none;" >	
															<tr>							    																																
															<td class="td-left" width="14%">会计开始日期</td>
															<td class="td-right" width="20%">
															
														 		 <input type="text" name="beginWorkDate"   id="beginWorkDate" readonly="readonly" value="${requestBean.beginWorkDate }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
															</td>
															
															<td class="td-left" width="14%">会计结束日期</td>
															<td class="td-right" width="20%">
																
														 		 <input type="text" name="endWorkDate"   id="endWorkDate" readonly="readonly" value="${requestBean.endWorkDate }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
															</td>
															
															<td class="td-left"  style="width:10%;">批次号</td>
															<td class="td-right"  style="width:18%;">
																<span class="input-icon">
																	<input type="text"  name="batchId"  id="batchId" value="${requestBean.batchId }"  style="width:88%;">
																	<i class="icon-leaf blue"></i>
																</span>
															</td>
															
															</tr>	
															<tr>
															<td class="td-left"  style="width:10%;">查询流水号</td>
															<td class="td-right"  style="width:18%;">
																<span class="input-icon">
																	<input type="text"  name="queryId"  id="queryId" value="${requestBean.queryId }"  style="width:88%;">
																	<i class="icon-leaf blue"></i>
																</span>
															</td>
															<td class="td-left"  style="width:10%;">七分钱订单号</td>
															<td class="td-right"  style="width:18%;">
																<span class="input-icon">
																	<input type="text"  name="orderId"  id="orderId" value="${requestBean.orderId }"  style="width:88%;">
																	<i class="icon-leaf blue"></i>
																</span>
															</td>
															<td class="td-left"  style="width:10%;">清算流水号</td>
															<td class="td-right"  style="width:18%;">
																<span class="input-icon">
																	<input type="text"  name="clearId"  id="clearId" value="${requestBean.clearId }"  style="width:88%;">
																	<i class="icon-leaf blue"></i>
																</span>
															</td>
															</tr>
																														
															<tr>
															<td colspan="6" align="center">
															<gyzbadmin:function url="<%=UnionpayQueryPath.BASE + UnionpayQueryPath.QFQIMPEACH%>">						
																<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
															<button type="button" class="btn btn-purple btn-sm btn-margin clearImpeach" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
															</button>
															</gyzbadmin:function> 
															<gyzbadmin:function url="<%=UnionpayQueryPath.BASE + UnionpayQueryPath.EXPORTQFQIMPEACH%>">
																<span class="input-group-btn" style="display:inline;">
																	<a class="btn btn-purple btn-sm"  id="exportImpeachBut"  >
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
											<th>清算流水号</th>
											<th>会计日期</th>
											<th>客户号</th>
											<th>账号</th>
											<th>批次编号</th>											
											<th>查询流水号</th>
											<th>七分钱订单号</th>
											<th>响应码</th>
											<th>交易金额</th>
											<th>清算金额</th>
											<th>订单发送时间</th>	
											<th>对账处理备注</th>									
										</tr>
									</thead>

									<tbody id="impeachData">
										<c:forEach items="${qfqimpeachList}" var="result" >
											<tr class="result" >
												<td>${result.clearId}</td>
												<td>${result.workDate}</td>
												<td>${result.custId}</td>
												<td>${result.acctNo}</td>
												<td>${result.batchId}</td>
												<td>${result.queryId }</td>
												<td>${result.orderId}</td>
												<td>${result.respCode}</td>
												<td>${result.txnAmt}</td>
												<td>${result.settleAmt }</td>
												<td>${result.txnTime }</td>	
												<td>${result.balMemo }</td>																					
											</tr>											
										</c:forEach>
									</tbody>
								</table>
								</div>	
																							
											<%@ include file="/include/page.jsp"%>		
															
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
	
	$("#exportImpeachBut").click(function(){
	
		var beginWorkDate =	$('.table-responsive #beginWorkDate').val();
		var endWorkDate=	$('.table-responsive #endWorkDate').val();
		var batchId=	$('.table-responsive #batchId').val();
		var queryId=	$('.table-responsive #queryId').val();
		var orderId=	$('.table-responsive #orderId').val();
		var clearId=	$('.table-responsive #clearId').val();
		var src="<%=request.getContextPath()+UnionpayQueryPath.BASE+UnionpayQueryPath.EXPORTQFQIMPEACH%>?beginWorkDate="+beginWorkDate+"&endWorkDate="+endWorkDate+"&batchId="+batchId+"&queryId="+queryId+"&orderId="+orderId+"&clearId="+clearId+"";
		$("#exportImpeachBut").attr("href",src);
	})
	
	 $("#resultReport").click(function(){
			window.location.href="<%=request.getContextPath()+UnionpayQueryPath.BASE + UnionpayQueryPath.LIST%>"
		 });
	 $("#slipReport").click(function(){
			window.location.href="<%=request.getContextPath()+UnionpayQueryPath.BASE + UnionpayQueryPath.SLIPREPORT%>";
		 });
	 $("#impeachReport").click(function(){
		 window.location.href="<%=request.getContextPath()+UnionpayQueryPath.BASE + UnionpayQueryPath.IMPEACH%>";
		 });
	 
	 $("#qfqReport").click(function(){
			window.location.href="<%=request.getContextPath()+UnionpayQueryPath.BASE + UnionpayQueryPath.QFQIMPEACH%>";
		 });
	 $("#fitReport").click(function(){
			window.location.href="<%=request.getContextPath()+UnionpayQueryPath.BASE + UnionpayQueryPath.FITREPORT%>";
		 });
	
	//按条件查询
	$("#serchSubmit").click(function(){
		
		var balDate =$("#beginWorkDate").val();
		//alert(balDate)
		var endBalTime= $("#endWorkDate").val();
		if(''!=balDate&&''!=endBalTime){
			if(balDate > endBalTime) 
			{
				alert("结束日期不能小于开始日期");
				return false;
			}
		}
		var form = $('#tradeForm');
		form.submit();
	});
	

	$(function () {	
		$('.clearImpeach').click(function(){
			$('.table-responsive #beginWorkDate').val('');
			$('.table-responsive #endWorkDate').val('');
			$('.table-responsive #batchId').val('');
			$('.table-responsive #queryId').val('');
			$('.table-responsive #orderId').val('');
			$('.table-responsive #clearId').val('');
		}) 
	     $('#myTab li:eq(2) a').tab('show');	    

	   });
	</script>
</body>
</html>					