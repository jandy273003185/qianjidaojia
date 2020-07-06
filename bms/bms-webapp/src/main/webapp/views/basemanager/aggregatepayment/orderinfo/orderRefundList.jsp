<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller.OrderRefundPath" %> 
<html>
<script src='<c:url value="/static/js/layer/layer.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>

<head>
	<meta charset="utf-8" />
	<title>订单信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	function loadQueryBean(){
		
		$("#refundState").val($("#queryRefundState").val());
		$("#channel").val($("#queryChannel").val());
	}
	$(function(){
		
		$('.clearTradeBill').click(function(){
			$('.search-table #refundId').val('');
			$('.search-table #mchId').val('');
			$('.search-table #refundState').val('');
			$('.search-table #createTimeB').val('');
			$('.search-table #createTimeE').val('');
			$('.search-table #orderId').val('');
			$('.search-table #channel').val('');
		}) ;

		$(".exportBut").click(function(){
			var refundId = $("#refundId").val();
			var merchantCode = $("#merchantCode").val();
			var createTimeB = $("#createTimeB").val();
			var createTimeE = $("#createTimeE").val();
			var refundState = $("#refundState").val();
			var orderId = $("#orderId").val();
			var channel = $("#channel").val();
			
			var arr1=createTimeE.split('-');
			var arr2=createTimeB.split('-');
			var d1=new Date(arr1[0],arr1[1],arr1[2]);
			var d2=new Date(arr2[0],arr2[1],arr2[2]);
			var diff =(d1.getTime()-d2.getTime())/(1000*3600*24);			 
			if(diff>90){
				
				$.gyzbadmin.alertFailure("您查询时间间隔超过三个月");
				return;
			}else{
			var src ="<%= request.getContextPath()+ OrderRefundPath.BASE + OrderRefundPath.ORDERREFUNDEXPORT%>?createTimeB="+
			createTimeB+"&refundId="+refundId+"&channel="+channel+"&merchantCode="+merchantCode+"&createTimeE="+createTimeE+"&refundState="+refundState+"&orderId="+orderId;
			$(".exportBut").attr("href",src);
			}
		});
	});
	

</script>
<body onload="loadQueryBean()">
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
							<form id="tradeForm" action='<c:url value="<%=OrderRefundPath.BASE + OrderRefundPath.ORDERREFUNDLIST %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" id="queryRefundState" value="${queryBean.refundState}"/>
								<input type="hidden" id="queryChannel" value="${queryBean.channel}"/>
								<input type="hidden" id="isFirst" name="isFirst" value="${isFirst}"/>
								<tr>
									<td class="td-left" >退款编号：</td>
									<td class="td-right">
										<input type="text" id="refundId" name="refundId" value="${queryBean.refundId }"/>
										
									</td>
									
									<td class="td-left" >商户编号：</td>
									<td class="td-right">
										 <input type="text" id="merchantCode" name="merchantCode" value="${queryBean.merchantCode }"/>
										
									</td>
									
									<td class="td-left">退款发起时间:</td>
									<td class="td-right">
										 <input type="text" name="createTimeB"  id="createTimeB" readonly="readonly"  value="${queryBean.createTimeB}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="createTimeE" id="createTimeE" readonly="readonly" value="${queryBean.createTimeE}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td class="td-left" >退款状态：</td>
									<td class="td-right">
										 <select id="refundState" name="refundState">
										 	<option value="">--请选择--</option>
											<option value="1">--申请退款--</option>
											<option value="0">--退款成功--</option>	
											<option value="9">--退款失败--</option>	
											
										 </select>
									</td>
									
									<td class="td-left" >原订单渠道：</td>
									<td class="td-right" >
										 <select id="channel" name="channel">
										 	<option value="">--请选择--</option>
											<option value="wx">--微信--</option>
											<option value="alipay">--支付宝--</option>	
											<option value="sevenpay">--七分钱--</option>	
										 </select>
									</td>
									
									<td class="td-left" >原订单号：</td>
									<td class="td-right">
										<input type="text" id="orderId" name="orderId" value="${queryBean.orderId }"/>
									</td>
								</tr>
								<tr>
								<td align="center" colspan="6" >
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=OrderRefundPath.BASE + OrderRefundPath.ORDERREFUNDLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeBill" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=OrderRefundPath.BASE + OrderRefundPath.ORDERREFUNDEXPORT%>">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</gyzbadmin:function> 
										</span>
									</td>
								</tr>
							</table>
							</form>
						
						
							<%-- <div class="list-table-header">
								当前列表交易总笔数为${sumTrade},交易总金额${sum }元,总结算金额${sumSettAmt }。
							</div> --%>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>退款编号</th>
											<th>原订单编号</th>
											<th>商户编号</th>
											<th>原订单总金额</th>
											<th>退款金额</th>
											<th>手续费</th>
											<th>退款时间</th>
											<th>退款状态</th>
											<th>退款渠道</th>
											<th>产品名称</th>
											<th>原订单渠道</th>
											<th>退款发起时间</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${orderRefundList}" var="bean">
											<tr class="bean">
												<td>${bean.refundId }</td>
												<td>${bean.orderId }</td>
												<td>${bean.merchantCode}</td>
												<td>${bean.totalAmt}</td>
												<td>${bean.refundAmt }</td>
												<td>${bean.settleAmt}</td>
												<td>${bean.refundTime}</td>
												<td>
													<c:choose>
														<c:when test="${bean.refundState=='0'}">
															退款成功
														</c:when>
														<c:when test="${bean.refundState=='1'}">
															申请退款
														</c:when>
														<c:when test="${bean.refundState=='9'}">
															退款失败
														</c:when>
														
													</c:choose>
												</td>
												<td>${bean.refundChannel }</td>
												<td>${bean.prodName }</td>
												<td>${bean.channel }</td>
												
												<td>${bean.createTime}</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty orderRefundList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty orderRefundList}">
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


