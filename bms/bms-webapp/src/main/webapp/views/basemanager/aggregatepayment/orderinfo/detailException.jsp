<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.DealOperation"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller.OrderInfoPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>订单异常处理明细页面</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<script type="text/javascript">

</script>
<body>
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
							<!-- 业务基本信息 -->
							<%
								String orderType = (String)pageContext.findAttribute("orderType");
							%>
							<%
								if(orderType.equals("1")){
							%>
							
							<table class="search-table">
								<tr class="bg-e" colspan="6" align="center" style="border-left:0px;">
									<button type="button" class="btn btn-purple btn-sm backListBtn">返回列表</button>
								</tr>
								<tr>
									<td class="bg-e" colspan="6"  align="center" style="font: 16px;font-weight: bold;">订单信息</td>
								</tr>
								<tr>
									<td class="td-left bg-e" width="11%">订单号</td>
									<td class="td-right" width="22%">${operation.orderId}</td>
									<td class="td-left bg-e" width="11%">商户号</td>
									<td class="td-right" width="22%">${operation.mchId }</td>
									<td class="td-left bg-e" width="11%">商户名称</td>
									<td class="td-right" width="23%">${operation.mchName}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">产品</td>
									<td class="td-right">${operation.service}</td>
									<td class="td-left bg-e">渠道</td>
									<td class="td-right" >${operation.channel}</td>
									<td class="td-left bg-e">下级渠道</td>
									<td class="td-right">${operation.channelSub}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">中信订单号</td>
									<td class="td-right" >${operation.zxOrderId}</td>
									<td class="td-left bg-e">渠道订单号</td>
									<td class="td-right">${operation.channelOrderId}</td>
									<td class="td-left bg-e">订单支付成功时间</td>
									<td class="td-right">
									<fmt:formatDate value="${operation.finishTime}" type="both"/></td>
								</tr>
								<tr>
									<td class="td-left bg-e">订单金额</td>
									<td class="td-right" style="color: red" colspan="5">
										${operation.orderAmt}
									</td>
								</tr>
							</table>
							<%
								}
								if(orderType.equals("2")){
							%>
								<table class="search-table">
								<tr class="bg-e" colspan="6" align="center" style="border-left:0px;">
									<button type="button" class="btn btn-purple btn-sm backListBtn">返回列表</button>
								</tr>
								<tr>
									<td class="bg-e" colspan="6"  align="center" style="font: 16px;font-weight: bold;">订单信息</td>
								</tr>
								<tr>
									<td class="td-left bg-e" width="11%">退款编号</td>
									<td class="td-right" width="22%">${operation.refundId}</td>
									<td class="td-left bg-e" width="11%">商户退款流水号</td>
									<td class="td-right" width="22%">${operation.merRefundId }</td>
									<td class="td-left bg-e" width="11%">商户编号</td>
									<td class="td-right" width="23%">${operation.merchantCode}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">原订单编号</td>
									<td class="td-right">${operation.orderId}</td>
									<td class="td-left bg-e">原订单总金额</td>
									<td class="td-right" style="color: red">${operation.totalAmt}</td>
									<td class="td-left bg-e">退款金额</td>
									<td class="td-right" style="color: red">${operation.refundAmt}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">中信交易号</td>
									<td class="td-right" >${operation.channelTransId}</td>
									<td class="td-left bg-e">中信退款ID</td>
									<td class="td-right">${operation.channelRefundId}</td>
									<td class="td-left bg-e">退款渠道</td>
									<td class="td-right">${operation.refundChannel}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">退款时间</td>
									<td class="td-right" >${operation.refundTime}</td>
									<td class="td-left bg-e">退款状态</td>
									<td class="td-right">${operation.refundState}</td>
								</tr>
							</table>
							<%
								}
							%>
							
							<table class="search-table">
								<tr>
									<td class="bg-e" colspan="6"  align="center" style="font: 16px;font-weight: bold;">核心信息</td>
								</tr>
								<tr>
									<td class="bg-e" colspan="2" width="33%" align="right" style="padding-right: 60px;border-right:0px;font-size: 15px;font-weight: bold;">
										核心状态：&nbsp;[<font color="red">
										<c:if test="${operation.coreSubmitstate =='00'}">核心记账成功</c:if>
										<c:if test="${operation.coreSubmitstate =='04'}">核心记账异常</c:if>
										</font>]
									</td>
									<td class="bg-e" colspan="4" width="67%" align="right" style="padding-right: 50px;border-left:0px;">
										<input type="hidden" id="tempOrderId" name="tempOrderId" value="${operation.orderId }">
										<input type="hidden" id="tempOrderType" name="tempOrderType" value="${operation.orderType }">
										<input type="hidden" id="temprefundId" name="temprefundId" value="${operation.refundId }">
										<c:if test="${operation.coreSubmitstate =='04'}">
										<gyzbadmin:function url="<%=OrderInfoPath.BASE + OrderInfoPath.SEQUEL_NEXT_STEP_OPERATION %>">
												<a href="#transBaseModal"  class="tooltip-success sequelNextStepOperation" data-rel="tooltip"  data-toggle='modal' title="续作下一步">
													<button type="submit" class="btn btn-purple btn-sm">续作下一步</button>
												</a>
										</gyzbadmin:function>
										</c:if>
									</td>
								</tr>
								<tr>
									<td class="td-left bg-e" width="11%">核心流水号</td>
									<td class="td-right" width="22%">${operation.coreSn}</td>
									<td class="td-left bg-e" width="11%">核心返回码</td>
									<td class="td-right" width="22%">${operation.orderCoreReturnCode }</td>
									<td class="td-left bg-e" width="11%">核心返回信息</td>
									<td class="td-right" width="23%">${operation.orderCoreReturnMsg}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">核心返回时间</td>
									<td class="td-right">${operation.orderCoreReturnTime}</td>
								</tr>
							</table>
					
						</div>
					</div>
				<!-- <div class="modal fade" id="operationBaseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:50%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title operationCls" id="myModalLabel"></h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
								<tr>
									<td class="td-left" style="width:15%">业务编号</td>
									<td class="td-right" style="width:35%">
										<input type="text" id="operId" name="operId" readonly="readonly" clasS="width-90">
										<input type="hidden" id="operType" name="operType" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left" style="width:15%">状态</td>
									<td class="td-right" style="width:35%">
										<select id="chargeNetpayState" name="chargeNetpayState" clasS="width-90">
										  <option value="99">-取消-</option>
										  <option value="00">-成功-</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left">备注</td>
									<td class="td-right" colspan="3">
										<textarea  id="operationOpinion" name="operationOpinion" rows="2" maxlength="200" clasS="width-95"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer" id="operationChildBtn">
				            <button type="button" class="btn btn-default" id="operationBtn" data-dismiss="modal">关闭</button>
				            <button type='button' class='btn btn-primary' id='closureOperationBtn'>提交</button>
				         </div>
				      </div>/.modal-content
				     </div>
				</div> --><!-- /.modal -->
				<div class="modal fade" id="transBaseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:50%">
				      <div class="modal-content">
				         <div class="modal-header">
				        	<input type="hidden" id="orderType" name="orderType">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title transCls" id="myModalLabel"></h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
								<tr class="orderType_1" >
									<td class="td-left">订单编号</td>
									<td class="td-right">
										<input type="text" id="orderId" name="orderId">
									</td>
								</tr>
								<tr class="orderType_2">
									<td class="td-left">退款编号</td>
									<td class="td-right">
										<input type="text" id="refundId" name="refundId">
									</td>
								</tr>
				            </table>
				            	             
				         </div>
				         <div class="modal-footer" id="transChildBtn">
				            <button type="button" class="btn btn-primary" id="sequelNextStepOperationBtn">续作下一步</button>
				         </div>
				      </div>
				     </div>
				</div><!-- /.modal -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	</div>
	<script type="text/javascript">
	/* function loadBalancePayment(){
		var operationStatus =$("#operationStatus").val();
		if(operationStatus=='DEAL_SUCCESS'||operationStatus=='DEAL_CANCELLED'){
			$(".tooltip-success").hide();
		}else{
			$(".tooltip-success").show();
		}
	} */
	 
	function forCloseDiv(){
		 $.unblockUI();
	} 
	$(function(){
		$('.backListBtn').click(function(){
			$.blockUI();
			window.history.go(-1);
		});
		
		$('.sequelNextStepOperation').click(function(){
			$("#transBaseModal .orderType_1").hide();
			$("#transBaseModal .orderType_2").hide();
			var orderId  = $(this).parent().find('input[name="tempOrderId"]').val();
			var refundId = $(this).parent().find('input[name="temprefundId"]').val();
			var orderType  = $(this).parent().find('input[name="tempOrderType"]').val();
			$("#transBaseModal #orderId").val(orderId);
			$("#transBaseModal #orderType").val(orderType);
			$("#transBaseModal #refundId").val(refundId);
			if(orderType == '1'){
				$("#transBaseModal .orderType_1").show();
			}
			if(orderType == '2'){
				$("#transBaseModal .orderType_2").show();
			}
		});
		
		/**续作下一步**/
		$("#sequelNextStepOperationBtn").click(function(){
			var orderId = $("#transBaseModal #orderId").val();	
			var orderType = $("#transBaseModal #orderType").val();	
			var refundId = $("#transBaseModal #refundId").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OrderInfoPath.BASE + OrderInfoPath.SEQUEL_NEXT_STEP_OPERATION%>', {
				'orderId': orderId,
				'orderType':orderType,
				'refundId' :refundId
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('续作下一步完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
	})
	<%-- jQuery(function($){
		var loop =	setInterval(function() {     
		    if(winChild.closed) { 
		    	$.unblockUI();
		    }    
		}, 200); 
		
		$('.backListBtn').click(function(){
			$.blockUI();
			window.history.go(-1);
		});
		
		/** 加载结束流程  **/
		$(".closureOperation").click(function(){
			var operId= $(this).parent().find('input[name="operId"]').val();
			var operType= $(this).parent().find('input[name="operType"]').val();
			$("#operationBaseModal #operId").val(operId);	
			$("#operationBaseModal #operType").val(operType);	
			$('.operationCls').text("结束流程");
		});
		
		/**结束流程**/
		$("#closureOperationBtn").click(function(){
			var operId = $("#operationBaseModal #operId").val();	
			var operType = $("#operationBaseModal #operType").val();	
			var operationOpinion = $("#operationBaseModal #operationOpinion").val();	
			var chargeNetpayState = $("#operationBaseModal #chargeNetpayState").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CLOSURE_OPERATION%>', {
				'operId':operId,
				'operType':operType,
				'operationOpinion':operationOpinion,
				'chargeNetpayState' :chargeNetpayState
				}, function(data) {
					$.unblockUI();
					$('#operationBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('流程结束成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载确认成功  **/
		$(".confirmSuccess").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("确认成功");
			$('#transBtn').siblings().hide();
			$('#confirmSuccessBtn').show();
		});
		/**确认成功**/
		$("#confirmSuccessBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_SUCCESS_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('确认成功完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载确认失败 **/
		$(".confirmFailure").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("确认失败");
			$('#transBtn').siblings().hide();
			$('#confirmFailureBtn').show();
		});
		/**确认失败**/
		$("#confirmFailureBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_FAILURE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('确认失败完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载撤销当前步骤**/
		$(".revokeTrans").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate = $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("撤销当前步骤");
			$('#transBtn').siblings().hide();
			$('#revokeTransBtn').show();
		});
		/**撤销当前步骤**/
		$("#revokeTransBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.REVOKE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('撤销当前步骤完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载退回当前步骤**/
		$(".rollbackOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("退回当前步骤");
			$('#transBtn').siblings().hide();
			$('#rollbackOperationBtn').show();
		});
		/**退回当前步骤**/
		$("#rollbackOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.ROLLBACK_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('退回当前步骤完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载重新执行**/
		$(".rexecuteTrans").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("重新执行");
			$('#transBtn').siblings().hide();
			$('#rexecuteTransBtn').show();
		});
		/**重新执行**/
		$("#rexecuteTransBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.REXECUTE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('重新执行完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载续作下一步**/
		$(".sequelNextStepOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("续作下一步");
			$('#transBtn').siblings().hide();
			$('#sequelNextStepOperationBtn').show();
		});
		/**续作下一步**/
		$("#sequelNextStepOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.SEQUEL_NEXT_STEP_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('续作下一步完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载续作整个流程**/
		$(".restartOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("续作整个流程");
			$('#transBtn').siblings().hide();
			$('#restartOperationBtn').show();
		});
		/**续作整个流程**/
		$("#restartOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.RESTART_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('续作整个流程完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
	});
	
	/**交易结果自查**/
	var winChild;
	function queryResultTrans(obj){
	 var transFlowId = $(obj).parent().find('input[name="transFlowId"]').val();
	 var transFlowOperate= $(obj).parent().find('input[name="transFlowOperate"]').val();
	 
	 var url=window.Constants.ContextPath+"<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>?transFlowId="+transFlowId+"&transFlowOperate="+transFlowOperate; 
     var name="newwindow";                        
     var iWidth=1500;                          
     var iHeight=600;                      
     var iTop = (window.screen.availHeight-30-iHeight)/2;
     var iLeft = (window.screen.availWidth-10-iWidth)/2;
     var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
   	winChild = window.open(url, name,params);
  }
	
	/**查看操作记录**/
	function queryOperationRecord(obj){
		var operId= $(obj).parent().find('input[name="operId"]').val();
		 var url=window.Constants.ContextPath+"<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_OPERATION_RECORD%>?operId="+operId; 
	     var name="newwindow";                        
	     var iWidth=1500;                          
	     var iHeight=700;    
	     var iTop = (window.screen.availHeight-30-iHeight)/2;
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
	   	winChild = window.open(url, name,params);
	} --%>
	</script>
  </body>	
</html>