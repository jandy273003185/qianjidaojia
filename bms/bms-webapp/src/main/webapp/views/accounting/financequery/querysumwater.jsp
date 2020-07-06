<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page
import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
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
<title>汇总余额流水查询</title>
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
<body onload="loadTransFlow()">
<%@ include file="/include/alert.jsp"%>
	<div class="modal-content" style="width: 100%;">
		<input type="hidden" name="idval" id="idval" />
		<input type="hidden" name="transType2" id="transType2" value = "${queryBean.transType }" />
		<div class="">
			<form id="tradeForm" action='<c:url value="<%=FinanceQueryPath.BASE + FinanceQueryPath.QUERYSUMWATER %>"/>' method="post">
				<table class="search-table" style="border: none;">
					<tr>
						<td class="td-left" width="15%">记账日期</td>
						<td class="td-right" width="35%">
						 <input type="text" name="workDate" id="workDate"  value = "${queryBean.workDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background: #fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right !important;">
							-
						 <input type="text" name="endWorkDate" id="endWorkDate" value = "${queryBean.endWorkDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background: #fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right !important;">
						</td>
						<td class="td-left" style="width:15%;">业务类型</td>
						<td class="td-right" style="width:35%;">
							<span class="input-icon"> 
								<input type="hidden" name="subjectId" id="subjectId" value="${queryBean.subjectId}" /> 
								<sevenpay:selectTransferTransTypeTag name="transType" id="transType" />
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button type="submit"  class="btn btn-purple btn-sm serchSubmit"> 
								查询 
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							<button class="btn btn-purple btn-sm btn-margin clearTransForm" >
								清空
								<i class=" icon-move icon-on-right bigger-110"></i>
							</button>
							
						    <gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.EXPORTWATERBALANCE %>">
								<span class="input-group-btn" style="display:inline;">
									<a class="btn btn-purple btn-sm" id="exportWaterA"> 
										导出报表 
										<i class="icon-download bigger-110"></i>
								    </a>
							    </span>	
							</gyzbadmin:function>	
						</td>
					</tr>
				</table>
			</form>
			<div class="list-table-header">流水查询</div>
			<div class="table-responsive" id="createtable">
				<table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th>记账时间</th>
							<th>对账ID</th>
							<th>账户名称</th>
							<th>业务类型</th>
							<th>借方</th>
							<th>借方金额</th>
							<th>贷方</th>
							<th>贷方金额</th>
						</tr>
					</thead>

					<tbody id="impeachData">
						<c:forEach items="${querywaterList}" var="result">
							<tr class="result">
								<td>${result.workDate}</td>
								<td>${result.subjectCode}</td>
								<td>${result.subjectName}</td>
								<td>${result.transType2}</td>
								<td>${result.debtor}</td>
								<td>${result.debtorBlance}</td>
								<td>${result.credit}</td>
								<td>${result.creditBlance}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty querywaterList}">
							<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<c:if test="${not empty querywaterList}">														
				<%@ include file="/include/page.jsp"%>		
			</c:if>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary addadBtn" onclick="closeWindow()">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
	<script type="text/javascript">
	
	function loadTransFlow(){
		$(".search-table #transType").val($("#transType2").val());
	}
	function closeWindow(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();
		 window.close(); 
	}
	    
	 $(".serchSubmit").click(function(){
		var startDate = $(".search-table #workDate").val();
		var endDate= $(".search-table #endWorkDate").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#tradeForm');
		form.submit();
	});	
	
     $(document).ready(function(){
		var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		  	 str=str.split("=")[1];		  	 
		  	 $("#idval").val(str);
		   }
	 });
 
     $(".clearTransForm").click(function(){
		$(".search-table #workDate").val('');
		$(".search-table #endWorkDate").val('');
		$(".search-table #transType").val('');
	 })

	 $("#exportWaterA").click(function(){
	    var workDate =$(".search-table #workDate").val();
		var endWorkDate= $(".search-table #endWorkDate").val();
		var transType=$(".search-table #transType").val();
		var subjectId = $(".search-table #subjectId").val();

		var src="<%=request.getContextPath()+FinanceQueryPath.BASE+FinanceQueryPath.EXPORTWATERBALANCE%>?transType="+transType+"&endWorkDate="+endWorkDate+"&workDate="+workDate+"&subjectId="+subjectId+"";
		$("#exportWaterA").attr("href",src);
	})
	</script>
</body>
</html>
