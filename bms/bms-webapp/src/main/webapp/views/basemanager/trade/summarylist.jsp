<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.trade.TradeBillMainPath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<head>
	<meta charset="utf-8" />
	<title>交易汇总查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
		function loadTradeBillMain(){
			
			$("#tradeType").val("consume");
			
			
		}
	$(function(){
		
		$('.clearTradeBillMain').click(function(){
			$('.search-table #beginTime').val('');
			$('.search-table #endCreateTime').val('');
			$('.search-table #custName').val('');
			$('.search-table #tradeType').val('');
		}) 
		//按条件查询
		$('.btn-sm').click(function(){
			var startDate =$("#beginTime").val();
			var endDate= $("#endCreateTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				alert("结束日期不能小于开始日期");
				return false;
			}
			var form = $('#tradeForm');
			form.submit();
		});
		
		var tradeBills = ${tradeBillList};
		var tradeBillList = $("tr.tradeBill");
		$.each(tradeBills,function(i,value){
			$.data(tradeBillList[i],"tradeBill",value);
		}); 
		
		
		$(".exportBut").click(function(){
			var beginTime = $("#beginTime").val();
			var endCreateTime = $("#endCreateTime").val();
			var custName = $("#custName").val();
			var src ="<%= request.getContextPath()+ TradeBillMainPath.BASE+TradeBillMainPath.SUMMARYEXPORT%>?beginTime="+beginTime+"&endCreateTime="+endCreateTime+"&custName="+custName;
			$(".exportBut").attr("href",src);
		});
		
	});
	

</script>
<body onload="loadTradeBillMain()">
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
						<input type="hidden"  name="tradeType2"  id="tradeType2" value="${tradeType}"/>
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=TradeBillMainPath.BASE + TradeBillMainPath.SUMMARY %>"/>' method="post">
							<table class="search-table">
							
								
								<tr>
									<td class="td-left" width="18%">开始日期：</td>
									<td class="td-right" width="32%">
										     <input type="text" name="beginTime"  id="beginTime" readonly="readonly"  value="${beginTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left" width="18%">结束日期：</td>
									<td class="td-right">
										     <input type="text" name="endCreateTime" id="endCreateTime" readonly="readonly" value="${endCreateTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="custName"  name="custName"  style="width:88%;" value="${ custName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >交易类型</td>
									<td class="td-right">
										<span class="input-icon">
											<select id="tradeType" name="tradeType">
												<option value="consume">消费</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
								<td align="center" colspan="4" >
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TradeBillMainPath.BASE + TradeBillMainPath.SUMMARY %>">
											<button type="button" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeBillMain" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=TradeBillMainPath.BASE + TradeBillMainPath.SUMMARYEXPORT%>">
											<a class="btn btn-purple btn-sm exportBut">
												导出报表
											</a> 
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
						
						
							<div class="list-table-header">
								当前列表交易总笔数为${sumTrade},交易总金额${sum }元,总结算金额${sumSettAmt }。
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:20%;">开始账期</th>
											<th style="width:13%;">结束账期</th>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>交易笔数</th>
											<th>交易金额</th>
											<th>交易类型</th>
											<th>商户结算金额</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${tradeBills}" var="tradeBillVO">
											<tr class="tradeBill">
												<td>${tradeBillVO.beginTime }</td>
												<td>${tradeBillVO.endCreateTime }</td>
												<td>${tradeBillVO.merchantCode}</td>
												<td>${tradeBillVO.custName}</td>
												<td>${tradeBillVO.sumCount }</td>
												<td>${tradeBillVO.sumCountAmount}</td>
												<td>${tradeBillVO.tradeType}</td>
												<td>${tradeBillVO.sumSettleAmt}</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty tradeBills}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty tradeBills}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
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


