<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.unionPay.tradeylresult.controller.TradeYlResultPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>银联交易结果查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadTradeYlResult()">
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
				<!-- 账户邮箱 -->
				<div >
				<form action='<c:url value="<%= TradeYlResultPath.BASE+TradeYlResultPath.TRADEYLRESULTMAIN %>"/>' method="post">
					<table class="search-table">
					  <tr>
						<td class="td-left">交易流水号：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="transSn" name="transSn" value="${queryBean.transSn }" maxlength="55" >
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">银联返回流水号：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="ylTn" name="ylTn" value="${queryBean.ylTn }" maxlength="55" >
									<i class="icon-leaf blue"></i>
							</span>
						</td>
					 </tr>
					  <tr>
						<td class="td-left">七分钱请求流水号：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="transId" name="transId" value="${queryBean.transId }" maxlength="55" >
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">交易类型：</td>
						<td class="td-right"> 
							<input type="hidden" id="transTypeTemp" name="transTypeTemp" value="${queryBean.transType}" >
							<select id="transType" name="transType" >
							    <option value = "">- 请选择  -</option>
							    <option value = "PAYMENT">- 支付 -</option>
							    <option value = "CHARGE">- 充值 -</option>
								<option value = "REVOCATION">- 消费撤销 -</option>
								<option value = "REFUND">- 退款 -</option>
							</select>
						</td>
					 </tr>
					 <tr>
					 <td colspan="4" align="center">
							<gyzbadmin:function url="<%= TradeYlResultPath.BASE+TradeYlResultPath.TRADEYLRESULTMAIN %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button class="btn btn-purple btn-sm btn-margin clearTradeYlResult" >
								清空
								<i class=" icon-move icon-on-right bigger-110"></i>
							</button>
							<gyzbadmin:function url="<%=TradeYlResultPath.BASE+TradeYlResultPath.TRADEYLRESULTEXPORT%>">
								<a class="btn btn-purple btn-sm exportTradeResultMainBut">
									导出报表
								</a> 
							</gyzbadmin:function>
						</td>
					 </tr>
					</table>
					</form>
				</div>
				<!-- 持卡人信息-->
				<div >
					<div class="list-table-header">银联交易结果列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th width="8%">交易流水号</th>
								<th width="10%">七分钱请求流水号</th>
								<th width="8%">银联返回流水号</th>
								<th width="5%">交易类型</th>
								<th width="8%">客户号</th>
								<th width="5%">交易金额</th>
								<th width="8%">交易提交时间</th>
								<th width="8%">银联同步响应时间</th>
								<th width="8%">银联异步响应时间</th>
								<th width="5%">银联响应码</th>
								<th width="5%">银联返回信息</th>
								<th width="5%">系统跟踪号</th>
								<th width="8%">查询流水号</th>
								<th width="9%">操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${ylResultList}" var="ylResult">
						<tr class="ylResult">
							<td>${ylResult.transSn }</td>
							<td>${ylResult.transId }</td>
							<td>${ylResult.ylTn }</td>
							<td>${ylResult.transType}</td>
							<td>${ylResult.custId }</td>
							<td>${ylResult.transAmt}</td>
							<td>${ylResult.transSubmitTime }</td>
							<td>${ylResult.ylRespTimeTb }</td>
							<td>${ylResult.ylRespTimeYb }</td>
							<td>${ylResult.ylRespCodeYb }</td>
							<td>${ylResult.ylRespMsgYb }</td>
							<td>${ylResult.traceNo }</td>
							<td>${ylResult.queryId}</td>
							<td>
								<gyzbadmin:function url="<%=TradeYlResultPath.BASE + TradeYlResultPath.EDIT%>">
									<a href="#" class="tooltip-success editTransYlResultLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#editTransYlResultModal">
										<span class="green"><i class="icon-edit bigger-120"></i></span>
									</a>
								</gyzbadmin:function>
								<gyzbadmin:function url="<%=TradeYlResultPath.BASE + TradeYlResultPath.QUERYTRADEYLRESULT%>">
									<a href="#" class="tooltip-success selectTradeYlResultLink" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#selectTradeYlResult">
										<button type="button"  id="btnEmail2"  class="btn btn-primary btn-xs"  >查询银联</button>	
									</a>
								</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty ylResultList}">
						<tr><td colspan="14" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
					</table>
					<c:if test="${not empty ylResultList}">
								<%@ include file="/include/page.jsp"%>
					</c:if>
				</div>
			</div><!-- /.page-content -->
			<div class="modal fade" id="editTransYlResultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">银联交易修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="30%">编号</td>
										<td class="td-right" width="70%">
											<input type="text" id="transId" name="transId" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">交易流水号</td>
										<td class="td-right">
											<input type="text" id="transSn" name="transSn" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">银联返回流水号</td>
										<td class="td-right">
											<input type="text" id="ylTn" name="ylTn" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">交易提交时间</td>
										<td class="td-right">
											<input type="text" id="transSubmitTime" name="transSubmitTime" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">同步响应时间</td>
										<td class="td-right">
											<input type="text" id="ylRespTimeTb" name="ylRespTimeTb" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">异步响应时间</td>
										<td class="td-right">
											<input type="text" id="ylRespTimeYb" name="ylRespTimeYb" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">交易金额</td>
										<td class="td-right">
											<input type="text" id="transAmt" name="transAmt" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">撤销交易查询编号</td>
										<td class="td-right">
											<input type="text" id="revokeQueryId" name="revokeQueryId" readonly="readonly" class="width-100">
										</td>
									</tr>
									
									<tr>
										<td class="td-left">系统跟踪号</td>
										<td class="td-right">
											<input type="text" id="traceNo" name="traceNo"  class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">账号</td>
										<td class="td-right" >
											<input type="text" id="accNo" name="accNo" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">卡类型</td>
										<td class="td-right">
											<input type="text" id="payCardType" name="payCardType" readonly="readonly" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">查询流水号</td>
										<td class="td-right" >
											<input type="text" id="queryId" name="queryId" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">交易类型</td>
										<td class="td-right">
											<input type="text" id="transType" name="transType"  readonly="readonly" class="width-100">
												
										</td>
									</tr>
									<tr>
									    <td class="td-left">银联响应码</td>
										<td class="td-right">
											<input type="text" id="ylRespCodeYb" name="ylRespCodeYb"  class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left">银联返回信息</td>
										<td class="td-right" >
											<input type="text" id="ylRespMsgYb" name="ylRespMsgYb" class="width-100">
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editTransYlResultBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					<div class="modal fade" id="selectTradeYlResult" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog" style="width:70%">
					      <div class="modal-content" >
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">查询银联</h4>
					         </div>
					         <div class="modal-body">
					           <table class="list-table" id="tbPreviewTradeYlResult">
									<thead>
										<tr>
											<th>卡账号</th>
											<th>交易金额</th>
											<th>原交易返回码</th>
											<th>原交易返回信息</th>
											<th>商户订单号</th>
											<th>商户号</th>
											<th>清算金额</th>
											<th>清算日期</th>
											<th>平台交易日期</th>
											<th>查询流水号</th>
											<th>系统跟踪号</th>
									    </tr>
								    </thead>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					         </div>
					      </div><!-- /.modal-content -->
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
<script type="text/javascript">
	
	function loadTradeYlResult(){
		$(".search-table #transType").val($(".search-table #transTypeTemp").val());
	}
	
	$('.clearTradeYlResult').click(function(){
		$(".search-table #transType").val('');
		$(".search-table #transId").val('');
		$(".search-table #transSn").val('');
		$(".search-table #ylTn").val('');
	})
	
	$(function(){
		var ylResultListJson = '<c:out value="${gyzb:toJSONString(ylResultList)}" escapeXml="false"/>';
		var ylResultTrList = $('tr.ylResult');
		$.each($.parseJSON(ylResultListJson), function(idx, obj){
			$.data(ylResultTrList[idx], 'ylResult', obj);
	});
		
	$('.selectTradeYlResultLink').click(function(){
		var ylResult = $.data($(this).parent().parent()[0], 'ylResult');
	
		$("#tbPreviewTradeYlResult tr:gt(0)").remove();
		$.blockUI();
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TradeYlResultPath.BASE + TradeYlResultPath.QUERYTRADEYLRESULT%>',
		        data:{transId:ylResult.transId,
		        	  transSubmitTime:ylResult.transSubmitTime
		        	  },
		        success:function(data){
		        	$.unblockUI();
		        	if(data.result=="fail"){
		        		$('#tbPreviewTradeYlResult').append("<tr><td colspan='11' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>");
		        	}else if(data.result=="success"){
		        		for(i in data.message){
		        			$('#tbPreviewTradeYlResult').append("<tr><td>"+data.message[i].accNo+"</td><td>"+data.message[i].txnAmt+"</td><td>"+data.message[i].origRespCode+"</td><td>"+data.message[i].origRespMsg+"</td><td>"+data.message[i].orderId+"</td><td>"+data.message[i].merId+"</td><td>"+data.message[i].settleAmt+"</td><td>"+data.message[i].settleDate+"</td><td>"+data.message[i].txnTime+"</td><td>"+data.message[i].queryId+"</td><td>"+data.message[i].traceNo+"</td></tr>");	
		        		}
		        	}
				}
			});
		});
			
		// 弹出修改层准备工作
		$('.editTransYlResultLink').click(function(){
			var ylResult = $.data($(this).parent().parent()[0], 'ylResult');
			$('#editTransYlResultModal').on('show.bs.modal', function () {
				// 赋值
				$('#editTransYlResultModal #transId').val(ylResult.transId);
				$('#editTransYlResultModal #transSn').val(ylResult.transSn);
				$('#editTransYlResultModal #ylTn').val(ylResult.ylTn);
				$('#editTransYlResultModal #transType').val(ylResult.transType);
				$('#editTransYlResultModal #custId').val(ylResult.custId);
				$('#editTransYlResultModal #transAmt').val(ylResult.transAmt);
				$('#editTransYlResultModal #transSubmitTime').val(ylResult.transSubmitTime);
				$('#editTransYlResultModal #ylRespTimeTb').val(ylResult.ylRespTimeTb);
				$('#editTransYlResultModal #ylRespTimeYb').val(ylResult.ylRespTimeYb);
				$('#editTransYlResultModal #ylRespCodeYb').val(ylResult.ylRespCodeYb);
				$('#editTransYlResultModal #ylRespMsgYb').val(ylResult.ylRespMsgYb);
				$('#editTransYlResultModal #traceNo').val(ylResult.traceNo);
				$('#editTransYlResultModal #accNo').val(ylResult.accNo);
				$('#editTransYlResultModal #payCardType').val(ylResult.payCardType);
				$('#editTransYlResultModal #revokeQueryId').val(ylResult.revokeQueryId);
				$('#editTransYlResultModal #queryId').val(ylResult.queryId);
			});
			$('#editTransYlResultModal').on('hide.bs.modal', function () {
				// 清除
				$('#editTransYlResultModal #transId').val('');
				$('#editTransYlResultModal #transSn').val('');
				$('#editTransYlResultModal #ylTn').val('');
				$('#editTransYlResultModal #transType').val('');
				$('#editTransYlResultModal #custId').val('');
				$('#editTransYlResultModal #transAmt').val('');
				$('#editTransYlResultModal #transSubmitTime').val('');
				$('#editTransYlResultModal #ylRespTimeTb').val('');
				$('#editTransYlResultModal #ylRespTimeYb').val('');
				$('#editTransYlResultModal #ylRespCodeYb').val('');
				$('#editTransYlResultModal #ylRespMsgYb').val('');
				$('#editTransYlResultModal #traceNo').val('');
				$('#editTransYlResultModal #accNo').val('');
				$('#editTransYlResultModal #payCardType').val('');
				$('#editTransYlResultModal #revokeQueryId').val('');
				$('#editTransYlResultModal #queryId').val('');
			});
		});

		// 修改
		$('.editTransYlResultBtn').click(function(){
			// 编号
			var transId = $('#editTransYlResultModal #transId').val();
			if(kong.test(transId)) {
				$.gyzbadmin.alertFailure('编号不可为空');
				return;
			}
			var ylRespCodeYb = $('#editTransYlResultModal #ylRespCodeYb').val();
			if(kong.test(ylRespCodeYb)) {
				$.gyzbadmin.alertFailure('银联返回码不可为空');
				return;
			}
			var ylRespMsgYb = $('#editTransYlResultModal #ylRespMsgYb').val();
			if(kong.test(ylRespMsgYb)) {
				$.gyzbadmin.alertFailure('银联返回信息不可为空');
				return;
			}
			var queryId = $('#editTransYlResultModal #queryId').val();
			var traceNo = $('#editTransYlResultModal #traceNo').val();
			// 保存修改
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=TradeYlResultPath.BASE + TradeYlResultPath.EDIT %>', {
					'transId' 		: transId,
					'ylRespCodeYb'	: ylRespCodeYb,
					'ylRespMsgYb'	: ylRespMsgYb,
					'queryId'		:queryId,
					'traceNo'		:traceNo
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#editTransYlResultModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		$(".exportTradeResultMainBut").click(function(){
			var transType = $(".search-table #transType").val();
			var transId = $(".search-table #transId").val();
			var transSn = $(".search-table #transSn").val();
			var ylTn = $(".search-table #ylTn").val();
			var src ="<%= request.getContextPath()+ TradeYlResultPath.BASE + TradeYlResultPath.TRADEYLRESULTEXPORT%>?transType="+
			transType+"&transId="+transId+"&transSn="+transSn+"&ylTn="+ylTn;
			$(".exportTradeResultMainBut").attr("href",src);
		});
		
	});
 </script>
</body>
</html>