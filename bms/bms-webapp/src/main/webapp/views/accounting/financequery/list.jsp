<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.accounting.withdraw.WithdrawPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchantwithdraw.MerchantWithdrawPath"%>
<%@page import="com.qifenqian.bms.accounting.withdrawrevoke.WithdrawRevokePath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>汇总余额</title>
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
											<form  id="tradeForm" action='<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.LIST%>' method="post">
													<table class="search-table"  >		
													  <tr>						    																			
															<td class="td-left" width="20%">账户名称</td>
															<td class="td-right" colspan="3">
																<span class="input-icon">
																    <input type="text"   name="subjectName" id="subjectName" value="${subjectName}">
																    <i class="icon-leaf blue"></i>
																 </span>
															</td>
														</tr>	
														<tr>
														<td colspan="4" align="center">	

															<gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.LIST %>">						
																<button type="submit" class="btn btn-purple btn-sm"   >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
															</gyzbadmin:function>
															<button class="btn btn-purple btn-sm btn-margin clearSubjectForm" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
															</button>
															<gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.EXPORTBALANCE%>">
																<span class="input-group-btn" style="display:inline;">
																	<a class="btn btn-purple btn-sm"  id="exportFitButA"  >
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
											<th>对账ID</th>
											<th>账户名称</th>
											<th>实时余额</th>
											<th>对账日期</th>
											<th>操作</th>								
										</tr>
									</thead>
									<tbody id="impeachData">
										<c:forEach items="${financeList}" var="result" >
											<tr class="result" >
												<td style="width:10%">${result.subjectCode}</td>
												<td>${result.subjectName}</td>
												<td>${result.finalAmount}</td>
												<td>${result.workDate}</td>
												<td>
													<input type="hidden" id="subjectId" value="${ result.subjectId}">
													<a id="btnEmail2" onclick="queryWater(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"   id="queryWaterBtn"  class="btn btn-primary btn-xs"  >查询流水</button>
													</a> 
												</td>
											</tr>											
										</c:forEach>
										<c:if test="${empty financeList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody> 
								</table>
								</div>	
								<c:if test="${not empty financeList}">														
									<%@ include file="/include/page.jsp"%>		
								</c:if>					
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
	
	$("#changeBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.CHANGEBALANCELIST%>";
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
	
	var winChild ;
	
	function queryWater(obj){
		
		var subjectId=$(obj).prev().val();

		var url=window.Constants.ContextPath+"<%=FinanceQueryPath.BASE+ FinanceQueryPath.QUERYSUMWATER%>?subjectId="+subjectId+""; 
	     var name="window";                        //网页名称，可为空;
	     var iWidth=1200;                          //弹出窗口的宽度;
	     var iHeight=600;                       //弹出窗口的高度;
	     //获得窗口的垂直位置
	     var iTop = (window.screen.availHeight-30-iHeight)/2;
	     //获得窗口的水平位置
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	      var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft; 
	 $.blockUI(); 
	
	
     winChild =  window.open(url, name,params); 
  	/* $('#main-container').OpenDiv();
  	$('#main-container').CloseDiv(); */

	} 
	
	function forCloseDiv(){
		 $.unblockUI();
	} 
	
	$(function () {	
		
		var loop =	setInterval(function() {     
		    if(winChild.closed) { 
		    	$.unblockUI();
		    }    
		}, 200); 
		
		 $(".clearSubjectForm").click(function(){
			 $(".search-table #subjectName").val(''); 
		 })
		
	     $('#myTab li:eq(0) a').tab('show');	
		
	     $("#exportFitButA").click(function(){
	 		var subjectName  =$(".search-table #subjectName").val().trim();
	 		var src="<%=request.getContextPath()+FinanceQueryPath.BASE+FinanceQueryPath.EXPORTBALANCE%>?subjectName="+subjectName+"";

	 		$("#exportFitButA").attr("href",src);
	 	});
		
	});
	
</script>
</body>
</html>					