<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page
import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgenReportPath" %>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<%-- <link rel="stylesheet"
	href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script
	src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代理商商户报表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}

li {
	list-style-type: none;
}

.displayUl {
	display: none;
}
</style>
</head>
<body >
<%@ include file="/include/alert.jsp"%>
	<div class="modal-content" style="width: 100%;">
		<input type="hidden" name="idval" id="idval" />
		<div class="">
			
				<div align="center">
					<span class="input-group-btn" style="display:inline;">
								<a class="btn btn-purple btn-sm" id="exportWaterA"> 
									导出报表 
									<i class="icon-download bigger-110"></i>
							    </a>
						    </span>	
				</div>
			<div class="list-table-header">代理商报表</div>
			<div class="table-responsive" id="createtable">
				<input type="hidden" id="agentId" value="${agentId}">
				<input type="hidden" id="beginWorkDate" value="${beginWorkDate}">
				<input type="hidden" id=endDate value="${endDate}">
				<table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th>开始账期</th>
							<th>结束账期</th>
							<th>商户编号</th>
							<th>商户产品及费率</th>
							<th>代理商名称</th>
							<th>代理商产品及费率</th>
							<th>商户名称</th>
							<th>交易笔数</th>
							<th>交易金额</th>
							<th>退款笔数</th>
							<th>退款金额</th>
							<th>有效金额</th>
							<th>总结算金额</th>
						</tr>
					</thead>

					<tbody id="impeachData">
						<c:forEach items="${agencyReportList}" var="result">
							<tr class="result">
								<td>${result.beginWorkDate}</td>
								<td>${result.endDate}</td>
								<td>${result.merCustId}</td>
								<td>
									${result.merRate.split('/')[0]}
									<br/>
									${result.merRate.split('/')[1]}
									<br/>
									${result.merRate.split('/')[2]}
									<br/>
									${result.merRate.split('/')[3]}
								</td>
								<td>${result.agentName}</td>
								<td>
									${result.agentRate.split('/')[0]}
									<br/>
									${result.agentRate.split('/')[1]}
									<br/>
									${result.agentRate.split('/')[2]}
									<br/>
									${result.agentRate.split('/')[3]}
								</td>
								<td>${result.merchantName}</td>
								<td>${result.receiveCount}</td>
								<td>${result.receiveTotalAmt}</td>
								<td>${result.refundCount}</td>
								<td>${result.refundTotalAmt}</td>
								<td>${result.validAmt}</td>
								<td>${result.commision}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty agencyReportList}">
							<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<c:if test="${not empty agencyReportList}">														
				<%@ include file="/include/page.jsp"%>		
			</c:if>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary addadBtn" onclick="closeWindow()">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
	<script type="text/javascript">
	
	
	function closeWindow(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();
		 window.close(); 
	}
	    
     $(document).ready(function(){
		var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		  	 str=str.split("=")[1];		  	 
		  	 $("#idval").val(str);
		   }
	 });

	  $("#exportWaterA").click(function(){
		var agentId = $("#agentId").val();
		var beginWorkDate = $("#beginWorkDate").val();
		var endDate = $("#endDate").val();
		var src="<%=request.getContextPath()+AgenReportPath.BASE+AgenReportPath.MERCHANTEXPORT%>?agentId="+agentId+"&beginWorkDate="+beginWorkDate+"&endDate="+endDate; 
		$("#exportWaterA").attr("href",src);
	}) 
	</script>
</body>
</html>
