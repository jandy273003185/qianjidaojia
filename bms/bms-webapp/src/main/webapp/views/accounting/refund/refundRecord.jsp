<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>退款审核申请记录</title>
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
		<div class="main-container" id="main-container">
			<div class="list-table-header">退款款审核申请列表</div>
			<div>
			   <table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th width="10%">退款订单号</th>
							<th width="10%">申请编号</th>
							<th width="15%">客户号</th>
							<th width="5%">记录时间</th>
							<th width="5%">交易金额</th>
							<th width="5%">返回码</th>
							<th width="10%">返回信息</th>
							<th width="5%">审核人</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${refundRecordList}" var="record" varStatus="status">
							<tr>
								<td>${record.refundOrderId}</td>
								<td>${record.id}</td>
								<td>${record.custId}</td>
								<td>${record.instDate}</td>
								<td>${record.refundAmt}</td>
								<td>${record.rtnCode}</td>
								<td>${record.rtnInfo}</td>
								<td>${record.creator}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty refundRecordList}">
							<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
				<!-- 分页 -->
				<c:if test="${not empty refundRecordList}">
				<%@ include file="/include/page.jsp"%>
				</c:if>
			</div>
		</div>
	<script type="text/javascript">
	</script>
</body>
</html>					