<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.jgkjsevendatasource.BalJgkjSevenDataSourcePath" %> 
<%@page import="com.qifenqian.bms.accounting.balunionpayuniondatasource.BalUnionpayUnionDataSourcePath" %> 
<%@page import="com.qifenqian.bms.accounting.jgkjdatasource.BalJgkjDataSourcePath" %>
<%@page import="com.qifenqian.bms.accounting.balunionpayuniondatasource.BalUnionpaySevenDataSourcePath" %>    
<%@page import="com.qifenqian.bms.accounting.cncbdatasource.BalCncbDataSourcePath" %> 

<html>
<head>
	<meta charset="utf-8" />
	<title>银联对账源数据</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<script type="text/javascript">
	
function loadClearJgkjTrade(){
	$(".search-table #merReserved").val($("#hiddenMerReserved").val());
	$(".search-table #payCardType").val($(".search-table #payCardTypeTemp").val());
}

$(document).ready(function(){
	 $('.clearJgkjTradeClr').click(function(){
		 $(':input','#searchForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	 });
		
	$("#exportbalancewaterlist").click(function(){	
		
		var orderId = $(".search-table #orderId").val();
		var queryId = $(".search-table #queryId").val();
		var workDate = $(".search-table #workDate").val();   
		var merReserved = $(".search-table #merReserved").val();
		var payCardType = $(".search-table #payCardType").val();
		var id = $(".search-table #id").val();
		var src="<%=request.getContextPath()+BalUnionpayUnionDataSourcePath.BASE+BalUnionpayUnionDataSourcePath.EXPORTUNION%>?orderId="+orderId+
				"&queryId="+queryId+
				"&workDate="+workDate+
				"&merReserved="+merReserved+
				"&payCardType="+payCardType+
				"&id="+id;
		
		$("#exportbalancewaterlist").attr("href",src); 
	}); 
	
	$("#ylSource").click(function(){
		window.location.href="<%=request.getContextPath()+BalUnionpayUnionDataSourcePath.BASE + BalUnionpayUnionDataSourcePath.LISTUNION%>";
	 });
	
	$("#qfqSource").click(function(){
		window.location.href="<%=request.getContextPath()+BalJgkjSevenDataSourcePath.BASE+BalJgkjSevenDataSourcePath.LIST%>";
	 });
	
	$("#zytSource").click(function(){
		window.location.href="<%=request.getContextPath()+BalJgkjDataSourcePath.BASE+ BalJgkjDataSourcePath.JGKJLIST%>";
	 });
	
	$("#ylqSource").click(function(){
		window.location.href="<%=request.getContextPath()+BalUnionpaySevenDataSourcePath.BASE+ BalUnionpaySevenDataSourcePath.LISTUNIONSEVEN%>";
	});

	$("#cncbSource").click(function(){
		window.location.href="<%=request.getContextPath()+BalCncbDataSourcePath.BASE+ BalCncbDataSourcePath.CNCBLIST%>";
	 });
	
	$('#myTab li:eq(1) a').tab('show');	
	
});
</script>
<body onload="loadClearJgkjTrade()">
	<!-- 充值/支付未明处理 -->
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
						<ul id="myTab" class="nav nav-tabs" style="margin-bottom:5px;">
						   <li><a href="#qfq"  data-toggle="tab"  id="qfqSource">七分钱交广科技对账源数据表</a></li>
						    <li><a href="yl"  data-toggle="tab"  id="ylSource">银联对账源数据表</a></li>
						   <li><a href="#zyt" data-toggle="tab"  id="zytSource">交广科技对账源数据表</a></li>
						   <li><a href="#ylq" data-toggle="tab"  id="ylqSource">七分钱银联对账源数据表</a></li>
						   <li><a herf="#cncb" data-toggle="tab" id="cncbSource">中信对账源数据表</a></li>
	
						</ul>
						<input type="hidden"  id="hiddenMerReserved" value="${queryBean.merReserved}">
						<!-- 查询条件 -->
							<form id="searchForm" action='<c:url value="<%=BalUnionpayUnionDataSourcePath.BASE + BalUnionpayUnionDataSourcePath.LISTUNION%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">银联查询流水号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="queryId" id="queryId" value="${queryBean.queryId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户订单号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="orderId" id="orderId" value="${queryBean.orderId}">
											<i class="icon-leaf blue"></i> 
										</span>
									</td>
									<td class="td-left">支付卡类型</td>
									<td class="td-right">
									<input type="hidden"  name="payCardTypeTemp" id="payCardTypeTemp" value="${queryBean.payCardType}">
										<select  id="payCardType" name="payCardType">
											<option value="">--请选择--</option>
											<option value="01">(01)借记账户</option>
											<option value="02">(02)贷记账户</option>
											<option value="03">(03)准贷记账户</option>
											<option value="04">(04)借贷合一账户</option>
											<option value="05">(05)预付费账户</option>
											<option value="06">(06)半开放预付费账户</option>
											<option value="00">(00)未知</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-left">七分钱-交广清算流水号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="id" id="id" value="${queryBean.id}">
											<i class="icon-leaf blue"></i> 
										</span>
									</td>
									<td class="td-left">会计日期</td>
									<td class="td-right">
										<input type="text"  name="workDate" id="workDate" value="${queryBean.workDate}"  readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left">是否七分钱平台</td>
									<td class="td-right">
										<select  id="merReserved" name="merReserved">
											<option value="">--选择平台--</option>
											<option value="yes">是</option>
											<option value="no">否</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<gyzbadmin:function url="<%=BalUnionpayUnionDataSourcePath.BASE + BalUnionpayUnionDataSourcePath.LISTUNION %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
										<button class="btn btn-purple btn-sm btn-margin clearJgkjTradeClr"  >
											清空
											<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
										<gyzbadmin:function url="<%=BalUnionpayUnionDataSourcePath.BASE + BalUnionpayUnionDataSourcePath.EXPORTUNION%>">
										<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm"  id="exportbalancewaterlist"  >
													导出报表
													<i class="icon-download bigger-110"></i> 
												</a> 
										</span>	
										</gyzbadmin:function>										
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								银联对账源数据
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>银联查询流水号</th>
											<th>商户订单号</th>
											<th>七分钱-交广清算流水号</th>
											<th>系统跟踪号</th>
											<th>帐号</th>
											<th>支付卡类型</th>
											<th>交易金额</th>
											<th>交易代码</th>
											<th>会计日期</th>										
											<th>写入时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${unionDataSourceList}" var="result">
											<tr class="result">
											    <td>${result.queryId }</td>												
												<td>${result.orderId }</td>	
												<td>${result.id}</td>
												<td>${result.traceNo }</td>											
												<td>${result.payCardNo }</td>
												<td>${result.payCardType }</td>
												<td>${result.txnAmt }</td>	
												<td>${result.txnCode }</td>		
												<td>${result.workDate }</td>
												<td>${result.instDatetime }</td>
											</tr>
										</c:forEach>
										<c:if test="${empty unionDataSourceList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty unionDataSourceList}">
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
</body>
</html>