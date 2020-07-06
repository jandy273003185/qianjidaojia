<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>操作记录</title>
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
			<div class="list-table-header">操作记录列表</div>
			<div>
			   <table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th width="4%">顺序</th>
							<th width="15%">业务编号</th>
							<th width="5%">处理类型</th>
							<th width="10%">处理步骤</th>
							<th width="10%">业务类型</th>
							<th width="10%">处理意见</th>
							<th width="5%">处理结果</th>
							<th width="20%">备注</th>
							<th width="8%">处理人</th>
							<th width="12%">处理时间</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${operationRecord}" var="record" varStatus="status">
							<tr>
								<td>${record.dealSeq}</td>
								<td>${record.operationId}</td>
								<td>${record.dealType}</td>
								<td>${record.transStep}</td>
								<td>${record.dealResult}</td>
								<td>${record.dealMemo}</td>
								<td>${record.executeStatus}</td>
								<td>${record.executeMemo}</td>
								<td>${record.userName} (${record.dealUser})</td>
								<td>${record.dealDatetime}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty operationRecord}">
							<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
				<!-- 分页 -->
				<c:if test="${not empty operationRecord}">
				<%@ include file="/include/page.jsp"%>
				</c:if>
			</div>
		</div>
	<script type="text/javascript">
	</script>
</body>
</html>					