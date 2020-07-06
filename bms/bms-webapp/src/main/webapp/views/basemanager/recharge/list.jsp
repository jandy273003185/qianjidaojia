<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.recharge.RechargePath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<head>
	<meta charset="utf-8" />
	<title>充值查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	function loadRecharge(){
		$("#chargeType").val($("#chargeType2").val());
		$("#chargeNetpayState").val($("#chargeNetpayState2").val());
		$("#chargeWay").val($("#chargeWayLoad").val());
		$("#transType").val($("#transTypeTemp").val());
		
	}
	$(function(){
		$('.clearRecharge').click(function(){
			$('.search-table #custName').val('');
			$('.search-table #chargeType').val('');
			$('.search-table #chargeNetpayState').val('');
			$('.search-table #beginTime').val('');
			$('.search-table #endTime').val('');
			$('.search-table #chargeSn').val('');
			$('.search-table #channelId').val('');
			$('.search-table #ylTn').val('');
			$('.search-table #compBeginTime').val('');
			$('.search-table #compEndTime').val('');
			$('.search-table #merOrderId').val('');
			$('.search-table #mobile').val('');
			$('.search-table #transId').val('');
			$('.search-table #chargeWay').val('');
			$('.search-table #transType').val('');
		}) 
		
		//按条件查询
		$('.buttonSearch').click(function(){
			
			var startDate = $("#beginTime").val();
			var endDate= $("#endTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("充值结束日期不能小于开始日期");
				return false;
			}
			var compBeginTime = $("#compBeginTime").val();
			var compEndTime= $("#compEndTime").val();
			if("" != compBeginTime && "" != compEndTime && compBeginTime > compEndTime) 
			{
				$.gyzbadmin.alertFailure("账期结束日期不能小于开始日期");
				return false;
			}
			
			var form = $('#tradeForm');
			form.submit();
		});
 
		$(".exportBut").click(function(){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var custName = $("#custName").val();
			var chargeType = $("#chargeType").val();
			var transId = $("#transId").val();
			var channelId = $("#channelId").val();
			var ylTn = $("#ylTn").val();
			var merOrderId = $("#merOrderId").val();
			var compDate = $("#compDate").val();
			var chargeNetpayState = $("#chargeNetpayState").val();
			var compBeginTime = $(".search-table #compBeginTime").val();
			var compEndTime= $(".search-table #compEndTime").val();
			var mobile= $(".search-table #mobile").val();
			var chargeWay= $(".search-table #chargeWay").val();
			var chargeSn= $(".search-table #chargeSn").val();
			var transType= $(".search-table #transType").val();
			var src ="<%= request.getContextPath()+ RechargePath.BASE+RechargePath.RECHARGEEXPORT%>?beginTime="+
					beginTime+"&endTime="+endTime+"&compBeginTime="+compBeginTime+"&mobile="+mobile+"&compEndTime="+compEndTime+"&custName="+custName+"&chargeType="+chargeType+"&transId="+transId+"&channelId="+channelId+"&ylTn="+ylTn+"&compDate="+compDate+"&chargeNetpayState="+chargeNetpayState+"&merOrderId="+merOrderId+"&chargeWay="+chargeWay+"&chargeSn="+chargeSn+"&transType="+transType;
			$(".exportBut").attr("href",src);
		});
	});
	
</script>
<body onload="loadRecharge()">
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
				<div class="page-content">
				
					<div class="row">
						<div class="col-xs-12">
						<input id="chargeType2" type="hidden" value="${queryBean.chargeType }">
						<input id="chargeNetpayState2" type="hidden" value="${queryBean.chargeNetpayState }">
						<input id="chargeWayLoad" type="hidden" value="${queryBean.chargeWay }">
						<input id="transTypeTemp" type="hidden" value="${queryBean.transType }">
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=RechargePath.BASE + RechargePath.LIST %>"/>' method="post">
							<table class="search-table" >
								<tr>
									<td class="td-left">七分钱流水号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name="chargeSn"  id="chargeSn" value="${queryBean.chargeSn }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
										<td class="td-left">交广科技流水号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name="channelId"  id="channelId" value="${queryBean.channelId }"  style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">充值方式</td>
									<td class="td-right">
										<span class="input-icon">
											<select name="chargeType" id="chargeType">
												<option value="">- 请选择 -</option>
												<option value="1">快捷支付（已开通）</option>
												<option value="2">快捷支付（未开通）</option>
												<option value="3">网银</option>
												<option value="4">银联在线</option>
												<option value="5">小快捷支付</option>
												<option value="6">红包充值</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">手机号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="mobile"  id="mobile" value="${queryBean.mobile }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">客户名</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="custName"  id="custName" value="${queryBean.custName }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">交易类型</td>
									<td class="td-right" >
										<span class="input-icon">
											<select  id="transType" name="transType">
												<option value="">--请选择--</option>
												<option value="UNIONPAY_PAYMENT">充值</option>
												<option value="UNIONPAY_REVOKE">撤销</option>
												<option value="UNIONPAY_REFUND">退款</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">充值开始日期：</td>
									<td class="td-right" >
										 <input type="text" name="beginTime"  id="beginTime" value="${queryBean.beginTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text"  name="endTime" id="endTime"  readonly="readonly" value="${queryBean.endTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left">账期：</td>
									<td class="td-right" >
										<input type="text" id="compBeginTime" name="compBeginTime" readonly="readonly" value="${queryBean.compBeginTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
											-
										<input type="text" id="compEndTime" name="compEndTime" value="${queryBean.compEndTime }"  readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
									</td>
									<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
											<select name="chargeNetpayState" id="chargeNetpayState">
												<option value="">- 请选择 -</option>
												<option value="00">成功</option>
												<option value="01">待支付</option>
												<option value="02">银联处理成功</option>
												<option value="03">银联处理失败</option>
												<option value="04">核心处理中</option>
												<option value="05">核心处理失败</option>
												<option value="99">取消</option>
												<option value="90">未明</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">商户订单号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name="merOrderId"  id="merOrderId" value="${queryBean.merOrderId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">银联流水号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name="transId"  id="transId" value="${queryBean.transId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">订单类型</td>
									<td class="td-right" >
										<span class="input-icon">
											<select  id="chargeWay" name="chargeWay">
												<option value="">--请选择--</option>
												<option value="0">充值</option>
												<option value="1">支付</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
								<td colspan="6" align="center" >
									<span class="input-group-btn">
										<gyzbadmin:function url="<%=RechargePath.BASE + RechargePath.LIST %>">
											<button type="button" class="btn btn-purple btn-sm buttonSearch">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
											<button  class="btn btn-purple btn-sm btn-margin clearRecharge" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										<gyzbadmin:function url="<%=RechargePath.BASE + RechargePath.RECHARGEEXPORT%>">
											<a class="btn btn-purple btn-sm exportBut">
												导出报表
											</a> 
										</gyzbadmin:function>
									</span>
								</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header" >
								充值信息
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th width="10%">七分钱流水号</th>
											<th width="9%">商户订单号</th>
											<th width="8%">交广科技流水号</th>
											<th width="8%">银联流水号</th>
											<th width="5%">充值方式</th>
											<th width="7%">手机号码</th>
											<th width="5%">账期</th>
											<th width="9%">充值开始时间</th>
											<th width="9%">充值结束时间</th>
											<th width="5%">客户名</th>
											<th width="5%">充值金额</th>
											<th width="5%">渠道结算金额</th>
											<th width="5%">订单类型</th>
											<th width="5%">交易类型</th>
											<th width="5%">状态</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${recharges}" var="recharge">
											<tr class="tradeBill">
												<td>${recharge.chargeSn}</td>
												<td>${recharge.merOrderId}</td>
												<td>${recharge.channelId}</td>
												<td>${recharge.transId}</td>
												<td>
													<c:if test="${recharge.chargeType=='1' }">快捷支付（已开通）</c:if>
													<c:if test="${recharge.chargeType=='2' }">快捷支付（未开通）</c:if>
													<c:if test="${recharge.chargeType=='3' }">网银</c:if>
													<c:if test="${recharge.chargeType=='4' }">银联在线</c:if>
													<c:if test="${recharge.chargeType=='5' }">小快捷支付</c:if>
													<c:if test="${recharge.chargeType=='6' }">红包充值</c:if>	
												</td>
												<td>${recharge.mobile}</td>
												<td>${recharge.compDate}</td>
												<td><fmt:formatDate value="${recharge.chargeSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${recharge.chargeReturnTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${recharge.custName}</td>
												<td>${recharge.chargeAmt}</td>
												<td>${recharge.settleAmt}</td>
												<td>
													<c:if test="${recharge.chargeWay=='0' }">充值</c:if>
													<c:if test="${recharge.chargeWay=='1' }">支付</c:if>						
												</td>
												<td>
													<c:if test="${recharge.transType=='UNIONPAY_PAYMENT' }">充值</c:if>
													<c:if test="${recharge.transType=='UNIONPAY_REVOKE' }">撤销</c:if>						
													<c:if test="${recharge.transType=='UNIONPAY_REFUND' }">退款</c:if>
												</td>
												<td>
													<c:if test="${recharge.chargeNetpayState=='00'}">成功</c:if>
												    <c:if test="${recharge.chargeNetpayState=='01'}">待支付</c:if>
                                               		<c:if test="${recharge.chargeNetpayState=='02'}">银联处理成功</c:if>
													<c:if test="${recharge.chargeNetpayState=='03'}">银联处理失败</c:if>
													<c:if test="${recharge.chargeNetpayState=='04'}">核心处理中</c:if>
													<c:if test="${recharge.chargeNetpayState=='05'}">核心处理失败</c:if>
													<c:if test="${recharge.chargeNetpayState=='99'}">取消</c:if>
													<c:if test="${recharge.chargeNetpayState=='90'}">未明</c:if>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty recharges}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty recharges}">
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
	<!-- 预览 -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width: 700px;">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">订单预览</h4>
	         </div>
	         <div class="modal-body">
	            <table class="modal-input-table" style="border: #c0c0c0;">
	            	<tr style="background-color: #E4E4E4;">
	            		<td colspan="4">
	            			订单号
	            		</td>
	            	</tr>
	            	<tr >
						<td class="td-left" width="20%">七分钱订单号:</td>
						<td class="td-right" width="30%">
							<!-- <input type="text" id=orderId name="orderId" readonly="readonly"> -->
							<span id="orderId"></span>
						</td>
						<td class="td-left" width="20%">商户订单号:</td>
						<td class="td-right" width="30%">
							<!-- <input type="text" id="orderName" name="outOrderId" readonly="readonly" style="background:#EEE"> -->
							<span id="outOrderId"></span>
						</td>
					</tr>
					<tr style="background-color: #E4E4E4;">
						<td colspan="4">
	            			金额
	            		</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">订单金额:</td>
						<td class="td-right" >
							<span id="sumAmt"></span>
						</td>
						<td class="td-left" width="20%">商户结算金额:</td>
						<td class="td-right" >
							<span id="orderAmount"></span>
						</td>
						
					</tr>
					
					<tr style="background-color: #E4E4E4;">
						<td colspan="4">
	            			状态
	            		</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">订单交易类型:</td>
						<td class="td-right" >
							<sevenpay:selectTradeType id="tradeType" name="tradeType" clasS="width-95"/>
						</td>
						<td class="td-left" width="20%">订单状态:</td>
						<td class="td-right" >
							<sevenpay:selectOrderStatus id="orderState" name="orderState" clasS="width-95"/>
						</td>
					</tr>
					<tr style="background-color: #E4E4E4;">
						<td colspan="4">
	            			时间
	            		</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">订单日期:</td>
						<td class="td-right creatTime" >
							<span id="createTime"></span>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr style="background-color: #E4E4E4;">
						<td colspan="4">
							其他
						</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">商户编号：</td>
						<td class="td-right" >
							<span id="custId"></span>
						</td>
						<td class="td-left" width="20%">商户名称：</td>
						<td class="td-right" >
							<span id="custName"></span>
						</td>
					</tr>
					
	            </table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
</body>
</html>


