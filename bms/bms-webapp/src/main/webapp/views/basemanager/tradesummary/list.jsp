<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.tradesummary.TransSummaryPath" %> 
<%@page import="com.qifenqian.bms.basemanager.trade.TradeBillMainPath" %> 
<%@page import="com.qifenqian.bms.basemanager.recharge.RechargePath" %>
<%@page import="com.qifenqian.bms.accounting.refund.RefundBillPath" %>  
<%@page import="com.qifenqian.bms.basemanager.transfer.TransferPath" %>
<%@page import="com.qifenqian.bms.basemanager.transferRevoke.TransferRevokePath" %>
<%@page import="com.qifenqian.bms.basemanager.busstransfer.BussTransferPath" %>
<%@page import="com.qifenqian.bms.accounting.withdraw.WithdrawPath"%> 
<%@page import="com.qifenqian.bms.basemanager.merchantwithdraw.MerchantWithdrawPath"%>
<%@page import="com.qifenqian.bms.basemanager.tradeRevoke.CustTransRevokePath"%> 
<%@page import="com.qifenqian.bms.basemanager.rechargeRevoke.RechargeRevokePath"%>

<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
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

 function loadSummary(){
	$('.search-table #operType').val($('.search-table #operTypeTemp').val());
 }
 
 $(function(){
	 $('.tradeClear').click(function(){
		 $(':input','#tradeForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	 });
	 
	 $('.buttonSearch').click(function(){
			var startDate =$("#workBeginDate").val();
			var endDate= $("#workEndDate").val();
			if("" != startDate && "" != endDate && startDate > endDate) {
				$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
				return false;
			}
			var form = $('#tradeForm');
			form.submit();
	 });
	 
	 $(".exportBut").click(function(){
			var operType = $('.search-table #operType').val();
			var workBeginDate = $('.search-table #workBeginDate').val();
			var workEndDate = $('.search-table #workEndDate').val();
			var src ="<%= request.getContextPath()+ TransSummaryPath.BASE+TransSummaryPath.TRADESUMMARYEXPORT%>?operType="+
					operType+
					"&workBeginDate="+
					workBeginDate+
					"&workEndDate="+
					workEndDate;
			$(".exportBut").attr("href",src);
		});
 });
 
 function queryTradeList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+TradeBillMainPath.BASE + TradeBillMainPath.LIST%>"; 
 }
 
 function queryRechargeList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+RechargePath.BASE + RechargePath.LIST%>"; 
 }
 
 function queryRefundList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+RefundBillPath.BASE + RefundBillPath.LIST%>"; 
 }
 
 function queryTransferList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+TransferPath.BASE + TransferPath.LIST%>"; 
 }
 
 function queryBussTransferList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+BussTransferPath.BASE + BussTransferPath.LIST%>"; 
 }
 
 function queryTransferRevokeList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+TransferRevokePath.BASE + TransferRevokePath.LIST%>"; 
 }
 
 function queryCustWithdrawList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+WithdrawPath.BASE + WithdrawPath.WITHDRAWLIST%>"; 
 }
 
 function queryMerchantWithdrawList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+MerchantWithdrawPath.BASE + MerchantWithdrawPath.LIST%>"; 
 }
 
 function queryRechargeRevokeList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+RechargeRevokePath.BASE + RechargeRevokePath.LIST%>"; 
 }
 
 function queryTransRevokeList(){
	 $.blockUI();
	 window.location.href="<%=request.getContextPath()+CustTransRevokePath.BASE + CustTransRevokePath.LIST%>"; 
 }
 
</script>
<body onload="loadSummary()">
	<!-- 用户信息 -->
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
					<div class="row">
						<div class="col-xs-12">
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=TransSummaryPath.BASE+TransSummaryPath.LIST %>"/>' method="post">
								<table class="search-table">
									<tr>
										<td class="td-left">账期开始日期</td>
										<td class="td-right">
											 <input type="text" name="workBeginDate" id="workBeginDate" readonly="readonly" value="${queryBean.workBeginDate}" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</td>
										<td class="td-left">账期结束日期</td>
										<td class="td-right">
											 <input type="text" name="workEndDate" id="workEndDate" readonly="readonly" value="${queryBean.workEndDate}" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</td>
										<td class="td-left">业务类型</td>
										<td class="td-right">
											 <input type="hidden" name="operTypeTemp" id="operTypeTemp" value="${queryBean.operType}">
											 <select id="operType" name="operType">
											   <option value="">- 请选择 -</option>
											   <option value="CUST_PAYMENT">- 消费 -</option>
											   <option value="CUST_RECHARGE">- 充值 -</option>
											   <option value="CUST_REFUND">- 退款 -</option>
											   <option value="CUST_TRANSFER">- 客户转账 -</option>
											   <option value="BUSS_TRANSFER">- 商户转账 -</option>
											   <option value="CUST_TRANSFER_REVOKE">- 转账撤销 -</option>
											   <option value="CUST_PAYMENT_REVOKE">- 交易撤销 -</option>
											   <option value="CUST_RECHARGE_REVOKE">- 充值撤销 -</option>
											   <option value="CUST_WITHDRAW_APPLY">- 客户提现 -</option>
											   <option value="MERCHANT_WITHDRAW_APPLY">- 商户提现 -</option>
											 </select>
										</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
											<span class="input-group-btn">
												<gyzbadmin:function url="<%=TransSummaryPath.BASE+TransSummaryPath.LIST %>">
													<button type="submit" class="btn btn-purple btn-sm buttonSearch">
														查询
														<i class="icon-search icon-on-right bigger-110"></i>
													</button> 
												</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin tradeClear" >
														清空
														<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
												<gyzbadmin:function url="<%=TransSummaryPath.BASE + TransSummaryPath.TRADESUMMARYEXPORT%>">
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
								交易汇总信息
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="20%">业务名称</th>
											<th width="20%">总笔数</th>
											<th width="20%">总金额</th>
											<th width="20%">业务类型</th>
											<th width="20%">操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${tradeSummaryList}" var="tradeSummary">
											<tr class="tradeSummary">
												<td>${tradeSummary.transName}</td>
												<td>${tradeSummary.transCount}</td>
												<td>${tradeSummary.transAmt}</td>
												<c:if test="${tradeSummary.operType == 'CUST_PAYMENT'}">
													<td>消费</td>
													<td>
														<a href="#" data-toggle="tab" id="queryTradeList" onclick="queryTradeList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>	
												<c:if test="${tradeSummary.operType == 'CUST_RECHARGE'}">
													<td>充值</td>
													<td>
														<a href="#" data-toggle="tab" id="queryRechargeList" onclick="queryRechargeList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_REFUND'}">
													<td>退款</td>
													<td>
														<a href="#" data-toggle="tab" id="queryRefundList" onclick="queryRefundList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_TRANSFER'}">
													<td>客户转账</td>
													<td>
														<a href="#" data-toggle="tab" id="queryTransferList" onclick="queryTransferList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'BUSS_TRANSFER'}">
													<td>商户转账</td>
													<td>
														<a href="#" data-toggle="tab" id="queryBussTransferList" onclick="queryBussTransferList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_TRANSFER_REVOKE'}">
													<td>转账撤销</td>
													<td>
														<a href="#" data-toggle="tab" id="queryTransferRevokeList" onclick="queryTransferRevokeList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_WITHDRAW_APPLY'}">
													<td>客户提现</td>
													<td>
														<a href="#" data-toggle="tab" id="queryCustWithdrawList" onclick="queryCustWithdrawList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'MERCHANT_WITHDRAW_APPLY'}">
													<td>商户提现</td>
													<td>
														<a href="#" data-toggle="tab" id="queryMerchantWithdrawList" onclick="queryMerchantWithdrawList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_RECHARGE_REVOKE'}">
													<td>充值撤销</td>
													<td>
														<a href="#" data-toggle="tab" id="queryRechargeRevokeList" onclick="queryRechargeRevokeList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
												<c:if test="${tradeSummary.operType == 'CUST_PAYMENT_REVOKE'}">
													<td>交易撤销</td>
													<td>
														<a href="#" data-toggle="tab" id="queryTransRevokeList" onclick="queryTransRevokeList()">
															<button type="submit"  class="btn btn-primary btn-xs" >查看详情</button>
														</a>
													</td>
												</c:if>
											</tr>
									</c:forEach>
									</tbody>
								</table>
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
	<!-- 预览 -->
</body>
</html>