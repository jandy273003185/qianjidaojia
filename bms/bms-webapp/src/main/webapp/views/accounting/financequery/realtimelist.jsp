<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.accounting.financequery.bean.RealTimeBussBalanceWater"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户实时余额流水查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body  onload="loadExportbalancewater()">
<%@ include file="/include/alert.jsp"%>
		      <div class="modal-content" style="width: 100%;">
		         <div class="modal-body">
		     	<form  id="bussBalanceWaterForm" action='<c:url value="<%=FinanceQueryPath.BASE + FinanceQueryPath.REALTIMELIST %>"/>' method="post">
		     	<% RealTimeBussBalanceWater requestBean = (RealTimeBussBalanceWater)pageContext.findAttribute("queryBean"); %>
					<table class="search-table"  style="border:none;" >	
							<tr>							    																																
								<td class="td-left" width="15%">对账日期</td>
								<td class="td-right" width="35%">
							 		 <input type="text" name="beginWorkDate" value="${queryBean.beginWorkDate }"   id="beginWorkDate" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
							 		 <input type="text" name="endWorkDate" value="${queryBean.endWorkDate }"   id="endWorkDate" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
								<td class="td-left"  style="width:15%;">业务类型</td>
								<td class="td-right"  style="width:35%;">
								    <input type="hidden"  name="custId" id="custId" value="${queryBean.custId}"/>
									<input  type="hidden"  name="businessTypeTemp"  id="businessTypeTemp"  value="${queryBean.businessType}" >
									<select id="businessType" name="businessType">
										<option value="">- 请选择 -</option>
										<% for(RequestColumnValues.BusinessType businessType : RequestColumnValues.BusinessType.values()) { %>
												<option value="<%=businessType.name() %>" <% if(businessType == requestBean.getBusinessType()){ %>selected<% } %>>--<%=ReflectUtils.getDesc(businessType) %>--</option>
											<% } %>
									</select>														
								</td>
							</tr>	
							<tr>
								<td colspan="4" align="center">
									<button type="submit"  class="btn btn-purple btn-sm serchSubmit" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button class="btn btn-purple btn-sm btn-margin clearExportbalancewater" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>	
									<gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.EXPORTREALTIMEBALANCE %>">
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
						<div class="list-table-header">商户流水查询</div>						
		         	<div class="table-responsive" id="createtable">
					 	<table id="sample-table-2" class="list-table">
							<thead>
								<tr>
									<th>账户名称</th>
									<th>业务类型</th>
									<th>余额方向</th>
									<th>交易金额</th>
									<th>期初余额</th>
									<th>在途金额变动</th>	
									<th>可用余额变动</th>
									<th>期末余额</th>											
									<th>对账日期</th>	
									<th>交易时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${balanceWaterList}" var="result" >
								<% RealTimeBussBalanceWater result = (RealTimeBussBalanceWater)pageContext.findAttribute("result"); %>
									<tr class="result" >
										<td>${result.acctName}</td>
										<td><%=ReflectUtils.getDesc(result.getBusinessType()) %></td>
										<td>${result.loanFlagDsc}</td>
										<td>${result.transAmt}</td>	
										<td>${result.balance}</td>
										<td>${result.onwayAmt}</td>
										<td>${result.usableAmt}</td>
										<td>${result.finalBalance}</td>
										<td>${result.instDate}</td>
										<td>${result.instDatetime}</td>																		
									</tr>											
								</c:forEach>
								<c:if test="${empty balanceWaterList}">
									<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody> 
						</table>
					</div>			
						<c:if test="${not empty balanceWaterList}">														
							<%@ include file="/include/page.jsp"%>		
						</c:if>	
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-primary addadBtn"  id="closeWindow02">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
	<script type="text/javascript">
	
	function loadExportbalancewater(){
		$(".search-table #businessType").val($('.search-table #businessTypeTemp').val());
	}
	$("#closeWindow02").click(function(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();	
		window.close();
		});
	
	$('.clearExportbalancewater').click(function(){
		$('.search-table #beginWorkDate').val('');
		$('.search-table #endWorkDate').val('');
		$('.search-table #businessType').val('');
	}) 
	
	  
	$(".serchSubmit").click(function(){
		var startDate = $(".search-table #beginWorkDate").val();
		var endDate= $(".search-table #endWorkDate").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#bussBalanceWaterForm');
		form.submit();
	});
	 
	 $("#exportbalancewaterlist").click(function(){
	    var beginWorkDate = $("#beginWorkDate").val();
		var endWorkDate = $("#endWorkDate").val();
		var businessType = $("#businessType").val();
		var custId = $("#custId").val();
		var src="<%=request.getContextPath()+FinanceQueryPath.BASE+FinanceQueryPath.EXPORTREALTIMEBALANCE%>?businessType="+businessType+"&beginWorkDate="+beginWorkDate+"&endWorkDate"+endWorkDate+"&custId="+custId+"";
		$("#exportbalancewaterlist").attr("href",src); 
	 })
	</script>
</body>
</html>					