<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath" %> 
<html>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<head>
	<meta charset="utf-8" />
	<title>金蝶交易明细</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadCalendar()">
	<!-- 用户信息 -->
			<div class="main-container" id="main-container">
					<div class="col-xs-12">
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th width='10%'>编号</th>
									
										<th width='8%'>付款用途名称</th>
										<th width='5%'>付款用途编码</th>
										<th width='5%'>应付金额 </th>
										<th width='5%'>折后金额</th>
										<th width='5%'>实付金额</th>
										<th width='7%'>我方银行账号编码</th>
										<th width='5%'>收款银行名称</th>
										<th width='5%'>收款银行编码</th>
										<th width='8%'>对方银行账号</th>
										<th width='8%'>对方账户名称</th>
										<th width='9%'>对方开户行</th>
										<th width='5%'>联行号</th>
										<th width='5%'>费用项目编码</th>
										<th width='5%'>手续费</th>
										<th width='5%'>手续费本位币</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${kingdeePayEntryList}" var="kingdeePay">
										<tr class="kingdeePay">
											<td>${kingdeePay.clearId}</td>
											<td>${kingdeePay.fpurposeidName}</td>
											<td>${kingdeePay.fpurposeidNumber}</td>
											<td>${kingdeePay.fpaytotalAmountFor}</td>
											<td>${kingdeePay.fsettlepayAmountFor}</td>
											<td>${kingdeePay.frealpayAmountForD}</td>
											<td>${kingdeePay.faccountidNumber}</td>
											<td>${kingdeePay.fbanktypeRecName}</td>
											<td>${kingdeePay.fbanktypeRecNumber}</td>
											<td>${kingdeePay.foppositeBankAccount}</td>
											<td>${kingdeePay.foppositeCcountName}</td>
											<td>${kingdeePay.foppositeBankName}</td>
											<td>${kingdeePay.fCnaps}</td>
											<td>${kingdeePay.fCostid}</td>
											<td>${kingdeePay.fhandlingChargeFor}</td>
											<td>${kingdeePay.fhandlingCharge}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty kingdeePayEntryList}">
										<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty kingdeePayEntryList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
</body>
<script type="text/javascript">
$(function(){
	var kingdeePayEntryListJson = ${kingdeePayEntryList};
	var kingdeePayTrList = $('tr.kingdeePay');
	$.each($.parseJSON(kingdeePayEntryListJson), function(idx, obj){
		$.data(kingdeePayTrList[idx], 'kingdeePay', obj);
	});	
})

</script>
</html>