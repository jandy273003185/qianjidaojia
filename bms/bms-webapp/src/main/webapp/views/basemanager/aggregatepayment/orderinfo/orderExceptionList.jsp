<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller.OrderInfoPath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>

<head>
	<meta charset="utf-8" />
	<title>订单异常信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
		
	$(function(){
		$("#orderType").val($("#queryOrderType").val());
		$('.clearTradeBill').click(function(){
			$('.search-table #createTimeB').val('');
			$('.search-table #createTimeE').val('');
			$('.search-table #orderId').val('');
		}) ;
		
		$(".detailLink").click(function(){
			$.gyzbadmin.postForm('<c:url value="<%=OrderInfoPath.BASE + OrderInfoPath.DETAILEXCEPTION %>" />',{
				'orderType':$(this).parent().find(':hidden[name="hiddenOrderType"]').val(),
				'orderId':$(this).parent().find(':hidden[name="hiddenOrderId"]').val(),
				'refundId':$(this).parent().find(':hidden[name="hiddenRefundId"]').val()
			}
		);
		});
	});
	

</script>
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
				<div class="page-content">
				
					<div class="row">
						<div class="col-xs-12">
						
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=OrderInfoPath.BASE + OrderInfoPath.ORDEREXCEPTIONLIST %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" id="queryOrderType" value="${queryBean.orderType}"/>
								<tr>
<!-- 									<td class="td-left" >订单号：</td> -->
<!-- 									<td class="td-right"> -->
<%-- 										<input type="text" name="orderId"  id="orderId" value="${queryBean.orderId }" size="35"> --%>
<!-- 									</td>	 -->
									
									<td class="td-left" >订单类型：</td>
									<td class="td-right" colspan="3">
										 <select id="orderType" name="orderType">
										 	<option value="">--全部--</option>
											<option value="1">--支付订单--</option>
											<option value="2">--退款订单--</option>												
										 </select>
									</td>							
									
									<td class="td-left">创建时间</td>
									<td class="td-right">
										 <input type="text" name="createTimeB"  id="createTimeB" readonly="readonly"  value="${queryBean.createTimeB}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="createTimeE" id="createTimeE" readonly="readonly" value="${queryBean.createTimeE}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>								
								<tr>
								<td align="center" colspan="6" >
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=OrderInfoPath.BASE + OrderInfoPath.ORDEREXCEPTIONLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeBill" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>											
										</span>
									</td>
								</tr>
							</table>
							</form>					
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>订单号</th>
											<th>商户名称</th>
											<th>商户号</th>
											<th>付款金额</th>
											<th>订单状态</th>
											<th>订单创建时间</th>
											<th>核心流水号</th>
											<th>核心返回码</th>
											<th>核心返回信息</th>																						
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${orderInfoList}" var="bean">
											<tr class="bean">
											    <td>${bean.orderId }</td>
											    <td>${bean.mchName }</td>
												<td>${bean.mchId }</td>											
												<td>${bean.tradeAmt}</td>
												
												<td>
												
												<c:if test="${bean.orderType=='1'}">
													<c:choose>
														<c:when test="${bean.orderState=='01'}">
															等待支付
														</c:when>
														<c:when test="${bean.orderState=='02'}">
															支付处理中
														</c:when>
														<c:when test="${bean.orderState=='00'}">
															支付成功
														</c:when>
														<c:when test="${bean.orderState=='09'}">
															支付失败
														</c:when>
														<c:when test="${bean.orderState=='90'}">
															已退款
														</c:when>
														<c:when test="${bean.orderState=='91'}">
															部分退款
														</c:when>
														<c:when test="${bean.orderState=='99'}">
															支付失败
														</c:when>														
													</c:choose>
													</c:if>
													<c:if test="${bean.orderType=='2'}">
													<c:choose>
													<c:when test="${bean.orderState=='0'}">
													退款成功
													</c:when>														
													</c:choose>
													</c:if>
												</td>
												<td>${bean.orderTimeStamp }</td>
												<td>${bean.coreSn}</td>
												<td>${bean.coreSubmitstate}</td>
												<td>${bean.orderCoreReturnMsg}</td>		
																			
												<td>
												   <input type="hidden" name="hiddenOrderType" value="${bean.orderType }">
												   <input type="hidden" name="hiddenOrderId" value="${bean.orderId }">
												   <input type="hidden" name="hiddenRefundId" value="${bean.refundId }">
												   <button type="button" class="btn btn-primary btn-xs detailLink" data-toggle='modal'>
														处理
													</button>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty orderInfoList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty orderInfoList}">
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


