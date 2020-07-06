<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.accounting.withdraw.WithdrawPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchantwithdraw.MerchantWithdrawPath"%>
<%@page import="com.qifenqian.bms.accounting.withdrawrevoke.WithdrawRevokePath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>余额变动</title>
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
						    <li><a href="#balanceWhole"  data-toggle="tab"  id="balanceWhole">汇总余额</a></li>
							<li><a href="#changeBalance" data-toggle="tab" id="changeBalance">余额变动</a></li>						   
							<li><a href="#commerciaBalance" data-toggle="tab" id="commerciaBalance"> 商户余额</a></li>
							<li><a href="#userBalance" data-toggle="tab" id="userBalance">用户余额</a></li>
							<li><a href="#cashSettlement" data-toggle="tab" id="cashSettlement">客戶提现结算</a></li>
							<li><a href="#merchantSettlement" data-toggle="tab" id="merchantSettlement">商户提现结算</a></li>
							<li><a href="#withdrawrevoke" data-toggle="tab" id="withdrawrevoke">客户提现撤销列表</a></li>
						</ul>
						<div class="table-responsive">
							<div id="myTabContent" class="tab-content">
								<div class="table-responsive">
									<form  id="tradeForm" action='<c:url value="<%=FinanceQueryPath.BASE + FinanceQueryPath.CHANGEBALANCELIST %>"/>' method="post">
											<table class="search-table"  >								    																			
													<tr>
													<td class="td-left" width="20%">对账日期</td>
													<td class="td-right">										
												 		 <input type="text" name="workDate" value="${workDate }"  id="workDate" readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</td>
													</tr>
													<tr>
													<td colspan="4" align="center">
														<button type="submit" class="btn btn-purple btn-sm"  >
															查询
															<i class="icon-search icon-on-right bigger-110"></i>
														</button>
														<button class="btn btn-purple btn-sm btn-margin clearbalanceForm" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
															</button>
														<gyzbadmin:function url="<%=FinanceQueryPath.BASE+FinanceQueryPath.EXPORTCHANGEBALANCE%>">
															<span class="input-group-btn" style="display:inline;">
																<a class="btn btn-purple btn-sm"  id="exportBalance"  >
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
											<th>会计科目</th>
											<th>期初余额</th>
											<th>借</th>
											<th>贷</th>
											<th>期末余额</th>	
											<th>日期</th>							
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${changeBalanceLists}" var="result" >
											<tr class="result" >
												<td>${result.subjectName}</td>
												<td>${result.lastAmount}</td>
												<td>${result.amountD}</td>
												<td>${result.amountC}</td>							
												<td>${result.finalAmount}</td>	
												<td>${result.workDate}</td>																		
											</tr>											
										</c:forEach>
										<c:if test="${empty changeBalanceLists}">
											<tr><td colspan="6" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody> 
								</table>
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
	
	$("#balanceWhole").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.LIST%>";
	 });
	
	$("#commerciaBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.COMMERCIABALANCELIST%>";
	 });
	
	$("#userBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.USERBALANCELIST%>";
	 });
	$("#merchantSettlement").click(function(){
		window.location.href="<%=request.getContextPath()+MerchantWithdrawPath.BASE + MerchantWithdrawPath.LIST%>";
	 });
	
	$("#cashSettlement").click(function(){
		window.location.href="<%=request.getContextPath()+WithdrawPath.BASE + WithdrawPath.WITHDRAWLIST%>";
	 });
	
	$("#withdrawrevoke").click(function(){
		window.location.href="<%=request.getContextPath()+WithdrawRevokePath.BASE + WithdrawRevokePath.WITHDRAWREVOKE%>";
	 });
	
	$(function () {	
	     $('#myTab li:eq(1) a').tab('show');
	   });
	
	$("#exportBalance").click(function(){
		 var workDate =$("#workDate").val();
		var src="<%=request.getContextPath()+FinanceQueryPath.BASE+FinanceQueryPath.EXPORTCHANGEBALANCE%>?workDate="+workDate;
		$("#exportBalance").attr("href",src);
	})
	
	$(".clearbalanceForm").click(function(){
		 $(".search-table #workDate").val('');
	})
	</script>
</body>
</html>					