<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.expresspay.tradereport.controller.TradeReportPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>交广科技应收应付查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
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
			<div class="page-content" >
				<!-- 账户邮箱 -->
				<div >
				<form action='<c:url value="<%=TradeReportPath.BASE+TradeReportPath.LIST %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">日期：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="workDate" name="workDate" value="${queryBean.workDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
							</span>
						</td>
						<td>
							<gyzbadmin:function url="<%=TradeReportPath.BASE+TradeReportPath.LIST %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<!-- <button class="btn btn-purple btn-sm btn-margin clearTradeReport" >
									清空
									<i class=" icon-move icon-on-right bigger-110"></i>
							</button> -->
						</td>
					  </tr>
					</table>
					</form>
				</div>
				<!-- 持卡人信息-->
				<div >
					<div class="list-table-header">交广科技应收应付凭证</div>
					<table id="sample-table-2" style="border:0px; width:100%">
					 <c:forEach items="${reportList}" var="report">
						<tr class="report" style="border:0px" >
							<td>${report.dateSource }</td>
						</tr>
						</c:forEach>
						<c:if test="${empty reportList}">
							<tr><td style="text-align: center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
		</div><!-- /.modal -->
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
<script type="text/javascript">
$(function(){
	$(".clearTradeReport").click(function(){
		$(".search-table #workDate").val('');
	})
})
</script>
</html>