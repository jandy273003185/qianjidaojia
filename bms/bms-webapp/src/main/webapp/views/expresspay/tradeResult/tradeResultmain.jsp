<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.expresspay.tradeResult.controller.TradeResultPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>交广科技交易结果查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadTradeResult()">
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
				<form action='<c:url value="<%=TradeResultPath.BASE+TradeResultPath.TRADERESULTMAIN %>"/>' method="post">
					<table class="search-table">
					   <tr>
							<td class="td-left">七分钱请求流水号：</td>
							<td class="td-right"> 
								<span class="input-icon">
										<input type="text" id="transId" name="transId" value="${queryBean.transId }" maxlength="55" >
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="emailLab"></label>
								</span>
							</td>
							<td class="td-left">交广科技返回流水号：</td>
							<td class="td-right"> 
								<span class="input-icon">
										<input type="text" id="rtnSeq" name="rtnSeq" value="${queryBean.rtnSeq }" maxlength="55" >
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="emailLab"></label>
								</span>
							</td>
							<td class="td-left">交易类型：</td>
							<td class="td-right"> 
							<input type="hidden" id="businessTypeTemp" name="businessTypeTemp" value="${queryBean.businessType}" >
								<select id="businessType" name="businessType" >
								    <option value = "">- 请选择  -</option>
								    <option value = "RECHARGE">- 充值 -</option>
								    <option value = "RECHARGE_ONLINE">- 在线充值 -</option>
								    <option value = "PAYMENT">- 消费 -</option>
									<option value = "TRANSFER">- 转账 -</option>
									<option value = "WITHDRAW_APPLY">- 提现申请 -</option>
								</select>
							</td>
							
					 </tr>
					 <tr>				
				 		<td colspan="6" align="center">
							<gyzbadmin:function url="<%=TradeResultPath.BASE+TradeResultPath.TRADERESULTMAIN %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button class="btn btn-purple btn-sm btn-margin clearTradeResult" >
								清空
								<i class=" icon-move icon-on-right bigger-110"></i>
							</button>
							<gyzbadmin:function url="<%=TradeResultPath.BASE + TradeResultPath.TRADERESULTEXPORT%>">
								<a class="btn btn-purple btn-sm exportTradeResultBut">
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
					<div class="list-table-header">交广科技交易结果列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th width='12%'>七分钱请求流水号</th>
								<th width='12%'>交易编号</th>
								<th width='5%'>交易类型</th>
								<th width='10%'>交广科技卡号</th>
								<th width='5%'>金额</th>
								<th width='11%'>订单编号</th>
								<th width='10%'>交易请求时间</th>
								<th width='5%'>七分钱会计日期</th>
								<th width='5%'>状态</th>
								<th width='5%'>交广科技返回时间</th>
								<th width='5%'>交广科技返回流水号</th>
								<th width='5%'>交广科技返回代码</th>
								<th width='10%'>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${jgkjTradeList}" var="jgkjTrade">
						<tr class="jgkjTrade">
							<td>${jgkjTrade.transId }</td>
							<td>${jgkjTrade.transFlowId }</td>
							<td>${jgkjTrade.businessType}</td>
							<td>${jgkjTrade.cardNo }</td>
							<td>${jgkjTrade.transAmt }</td>
							<td>${jgkjTrade.orderNo}</td>
							<td>${jgkjTrade.sendDatetime}</td>
							<td>${jgkjTrade.workDate }</td>
							<td>${jgkjTrade.status }</td>
							<td>${jgkjTrade.rtnTime }</td>
							<td>${jgkjTrade.rtnSeq }</td>
							<td>${jgkjTrade.rtnCode }</td>
							<td>
								<gyzbadmin:function url="<%=TradeResultPath.BASE + TradeResultPath.TRADERESULT%>">
									<a href="#" class="tooltip-success selectTradeResultLink" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#selectTradeResult">
										<button type="button"  id="btnEmail2"  class="btn btn-primary btn-xs"  >查询交广科技</button>	
									</a>
								</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty jgkjTradeList}">
							<tr><td colspan="14" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
					</table>
					<c:if test="${not empty jgkjTradeList}">
							<%@ include file="/include/page.jsp"%>
					</c:if>
				</div>
			</div><!-- /.page-content -->
		<div class="modal fade" id="selectTradeResult" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog" style="width:60%">
					      <div class="modal-content" >
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">查询交广科技</h4>
					         </div>
					         <div class="modal-body">
					           <table class="list-table" id="tbPreviewTradeResult">
									<thead>
										<tr>
											<th>卡号</th>
											<th>交易代码</th>
											<th>交易结果</th>
											<th>返回信息</th>
											<th>交易金额</th>
											<th>平台流水号</th>
											<th>平台交易日期</th>
											<th>平台交易时间</th>
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
function loadTradeResult(){
	$(".search-table #businessType").val($(".search-table #businessTypeTemp").val());
}

$(function(){
	var jgkjTradeList = '<c:out value="${gyzb:toJSONString(jgkjTradeList)}" escapeXml="false"/>';
	var jgkjTradeTrList = $('tr.jgkjTrade');
	$.each($.parseJSON(jgkjTradeList), function(idx, obj){
		$.data(jgkjTradeTrList[idx], 'jgkjTrade', obj);
	});
	
	
	$(".clearTradeResult").click(function(){
		$(".search-table #rtnSeq").val('');
		$(".search-table #transId").val('');
		$(".search-table #businessType").val('');
	});
	
	$('.selectTradeResultLink').click(function(){
		var jgkjTrade = $.data($(this).parent().parent()[0], 'jgkjTrade');
		$("#tbPreviewTradeResult tr:gt(0)").remove();
		$.blockUI();
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TradeResultPath.BASE + TradeResultPath.TRADERESULT%>',
		        data:{transId:jgkjTrade.transId,
		        	  cardNo:jgkjTrade.cardNo
		        	  },
		        success:function(data){
		        	$.unblockUI();
		        	if(data.result=="fail"){
		        		$('#tbPreviewTradeResult').append("<tr><td colspan='8' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>");
		        	}else if(data.result=="success"){
		        		for(i in data.message){
		        			$('#tbPreviewTradeResult').append("<tr><td>"+data.message[i].cardNo+"</td><td>"+data.message[i].txnCode+"</td><td>"+data.message[i].errCode+"</td><td>"+data.message[i].rtnInfo+"</td><td>"+data.message[i].amount+"</td><td>"+data.message[i].platformSeq+"</td><td>"+data.message[i].txnDate+"</td><td>"+data.message[i].txnTime+"</td></tr>");	
		        		}
		        	}
				}
			});
		});
	
	$(".exportTradeResultBut").click(function(){
		var rtnSeq = $(".search-table #rtnSeq").val();
		var transId = $(".search-table #transId").val();
		var businessType = $(".search-table #businessType").val();
		var src ="<%= request.getContextPath()+ TradeResultPath.BASE + TradeResultPath.TRADERESULTEXPORT%>?businessType="+
		businessType+"&rtnSeq="+rtnSeq+"&transId="+transId;
		$(".exportTradeResultBut").attr("href",src);
	});
	
});

</script>
</body>
</html>