<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller.OrderInfoPath" %> 
<%@ page import="java.util.Date"%> 
<%@ page import="java.text.DateFormat"%> 
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
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
		function loadTradeBillMain(){
			
			$("#orderState").val($("#queryOrderState").val());
			$("#payProd").val($("#queryPayProd").val());
			$("#payChannel").val($("#queryPayChannel").val());
			$("#channel").val($("#queryChannel").val());
			
		}
	$(function(){
		
		$('.clearTradeBill').click(function(){
			$('.search-table #bank').val('');
			$('.search-table #payProd').val('');
			$('.search-table #payChannel').val('');
			$('.search-table #channel').val('');
			$('.search-table #orderState').val('');
			$('.search-table #merchantCode').val('');
			$('.search-table #orderId').val('');
			$('.search-table #channelOrderId').val('');
			$('.search-table #zxOrderId').val('');
		}) ;

		$(".exportBut").click(function(){
			var payProd = $("#payProd").val();
			var payChannel = $("#payChannel").val();
			var createTimeB = $("#createTimeB").val();
			var createTimeE = $("#createTimeE").val();
			var finishTimeE = $("#finishTimeE").val();
			var finishTimeB = $("#finishTimeB").val();
			var orderState = $("#orderState").val();
			var channel = $("#channel").val();
			var merchantCode = $("#merchantCode").val();
			var orderId = $("#orderId").val();
			var channelOrderId = $("#channelOrderId").val();
			var zxOrderId = $("#zxOrderId").val();

			var arr1=createTimeE.split('-');
			var arr2=createTimeB.split('-');
			var d1=new Date(arr1[0],arr1[1],arr1[2]);
			var d2=new Date(arr2[0],arr2[1],arr2[2]);
			var diff =(d1.getTime()-d2.getTime())/(1000*3600*24);			 
			if(diff>90){
				$.gyzbadmin.alertFailure("您查询时间间隔超过三个月");
				return;
				/*layer.msg('您查询时间间隔超过三个月');
				 alert('您查询时间间隔超过三个月了');
				return false; */
			}else{
			var src ="<%= request.getContextPath()+ OrderInfoPath.BASE + OrderInfoPath.ORDERINFOEXPORT%>?createTimeB="+
			createTimeB+"&payProd="+payProd+"&payChannel="+payChannel+"&finishTimeE="+finishTimeE+"&finishTimeB="+finishTimeB+"&createTimeE="+createTimeE+"&orderState="+orderState+"&channel="+channel+"&merchantCode="+merchantCode+"&orderId="+orderId+"&channelOrderId="+channelOrderId+"&zxOrderId="+zxOrderId;
			$(".exportBut").attr("href",src);
			}
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
						<div class="col-xs-22">
						
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=OrderInfoPath.BASE + OrderInfoPath.ORDERINFOLIST %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" id="queryOrderState" value="${queryBean.orderState}"/>
								<input type="hidden" id="queryPayProd" value="${queryBean.payProd }"/>
								<input type="hidden" id="queryPayChannel" value="${queryBean.payChannel}"/>
								<input type="hidden" id="queryChannel" value="${queryBean.channel}"/>
								<input type="hidden" id="isFirst" name="isFirst" value="${isFirst}"/>
								<tr>
									<td class="td-left" >支付产品：</td>
									<td class="td-right">
										 <select id="payProd" name="payProd">
										 	<option value="">--请选择--</option>
										 	<c:forEach items="${payProdList }" var="bean">
										 			<option value="${bean.prodCode }">${bean.prodName }</option>
										 	</c:forEach>
										 </select>
									</td>
									
									<td class="td-left" >支付渠道：</td>
									<td class="td-right">
										 <select id="payChannel" name="payChannel">
										 	<option value="">--请选择--</option>
											<c:forEach items="${payChannelList}" var="bean">
												<option value="${bean.payChannelCode }">${bean.payChannelName }</option>
											</c:forEach>
										 </select>
									</td>
									
									<td class="td-left">创建时间：</td>
									<td class="td-right">
										 <input type="text" name="createTimeB"  id="createTimeB" readonly="readonly"  value="${queryBean.createTimeBv}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="createTimeE" id="createTimeE" readonly="readonly" value="${queryBean.createTimeEv}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td class="td-left" >订单状态：</td>
									<td class="td-right" >
										 <select id="orderState" name="orderState">
										 	<option value="">--请选择--</option>
											<option value="01">--等待支付--</option>
											<option value="02">--支付处理中--</option>	
											<option value="00">--支付成功--</option>	
											<option value="09">--支付失败--</option>	
											<!-- <option value="90">--已退款--</option>	
											<option value="91">--部分非退款--</option>	 -->
											<option value="99">--取消--</option>	
										 </select>
									</td>
									
									<td class="td-left" >渠道：</td>
									<td class="td-right">
										 <select id="channel" name="channel">
										 	<option value="">--请选择--</option>
										 	<option value="wx">微信</option>
										 	<option value="sevenpay">七分钱</option>
										 	<option value="alipay">支付宝</option>
										 </select>
									</td>
									<td class="td-left" >商户编号：</td>
									<td class="td-right">
										<input type="text" id="merchantCode" name="merchantCode" value="${queryBean.merchantCode}"/>
									</td>
								</tr>
								<tr>
									<td class="td-left" >订单号：</td>
									<td class="td-right">
										<input type="text" id="orderId" name="orderId" value="${queryBean.orderId}"/>
									</td>
									<td class="td-left" >中信订单号：</td>
									<td class="td-right">
										<input type="text" id="zxOrderId" name="zxOrderId" value="${queryBean.zxOrderId}"/>
									</td>
									<td class="td-left" >渠道订单号：</td>
									<td class="td-right">
										<input type="text" id="channelOrderId" name="channelOrderId" value="${queryBean.channelOrderId}"/>
									</td>
								</tr>
								<tr>
									<td class="td-left">账期时间：</td>
									<td class="td-right">
										 <input type="text" name="finishTimeB"  id="finishTimeB" readonly="readonly"  value="${queryBean.finishTimeB}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="finishTimeE" id="finishTimeE" readonly="readonly" value="${queryBean.finishTimeE}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
								<td align="center" colspan="6" >
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=OrderInfoPath.BASE + OrderInfoPath.ORDERINFOLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeBill" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=OrderInfoPath.BASE + OrderInfoPath.ORDERINFOEXPORT%>">
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
											<th>订单号</th>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>渠道编号</th>
											<th>下级渠道</th>
											<th>中信订单号</th>
											<th>渠道订单号</th>
											<th>订单金额</th>
											<th>付款金额</th>
											<th>结算金额</th>
											<!-- <th>代金券</th> -->
											<th>商品描述</th>
											<th>支付产品</th>
											<th>订单状态</th>
											<th>付款方式</th>
											<th>创建时间</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${orderInfoList}" var="bean">
											<tr class="bean">
												<td>${bean.orderId }</td>
												<td>${bean.merchantCode }</td>
												<td>${bean.mchName }</td>
												<td>${bean.channel}</td>
												<td>${bean.channelSubName}</td>
												<td>${bean.zxOrderId }</td>
												<td>${bean.channelOrderId}</td>
												<td>${bean.orderAmt}</td>
												<td>${bean.tradeAmt}</td>
												<td>${bean.settleAmt}</td>
												<%-- <td>${bean.couponAmt}</td> --%>
												<td>${bean.prodDesc}</td>
												<td>${bean.prodName}</td>
												
												<td>
													<c:choose>
														<c:when test="${bean.orderState=='01'}">
															等待支付
														</c:when>
														<c:when test="${bean.orderState=='02'}">
															支付处理中
														</c:when>
														<c:when test="${bean.orderState=='00' ||bean.orderState=='90'||bean.orderState=='91'}">
															支付成功
														</c:when>
														<c:when test="${bean.orderState=='09'}">
															支付失败
														</c:when>
														<%-- <c:when test="${bean.orderState=='90'}">
															已退款
														</c:when>
														<c:when test="${bean.orderState=='91'}">
															部分退款
														</c:when> --%>
														<c:when test="${bean.orderState=='99'}">
															支付失败
														</c:when>
														
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${bean.payType == 'SM'}">
															扫码
														</c:when>
														<c:when test="${bean.payType=='SK'}">
															刷卡
														</c:when>
														<c:when test="${bean.payType=='SL'}">
															刷脸
														</c:when>
														<c:when test="${bean.payType=='1'}">
															刷卡
														</c:when>
														<c:when test="${bean.payType=='3'}">
															扫码
														</c:when>
														<c:otherwise>
															${bean.payType }
														</c:otherwise>	
													</c:choose>
													
												</td>
												<td>${bean.createTime }</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty orderInfoList}">
											<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
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


