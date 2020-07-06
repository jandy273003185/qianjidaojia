<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller.TdRefundPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户渠道金额限制</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	$(function(){
		$('.clearBank').click(function(){
			$(':input','#form_merchant')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		
	
		$(".exportBut").click(function(){
			var orderId = $("#orderId").val();
			var mchId = $("#mchId").val();
			var refundState = $("#refundState").val();
			var src ="<%= request.getContextPath()+ TdRefundPath.BASE + TdRefundPath.EXPORT%>?orderId="+
			orderId+"&mchId="+mchId+"&refundState="+refundState;
			$(".exportBut").attr("href",src);
		});
		
	});
	function load(){
		$("#refundState").val( $("#refundStateHidden").val());
		
	}

	
</script>
<body onload="load()">
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
						<!-- 查询条件 -->
							<form id="form_merchant" action='<c:url value="<%=TdRefundPath.BASE + TdRefundPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >原订单号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="orderId" id="orderId"  value="${queryBean.orderId}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="mchId" id="mchId"   value="${queryBean.mchId}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">退款状态</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="hidden" id="refundStateHidden" value="${queryBean.refundState}">
										 	<select id="refundState" name="refundState" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="0">退款成功</option>
												<option value="1">退款申请 </option>
												<option value="9">退款失败 </option>
											</select>
										</span>   
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TdRefundPath.BASE + TdRefundPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=TdRefundPath.BASE + TdRefundPath.EXPORT%>">
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
								银行列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="11%">退款编号</th>
											<th width="11%">原订单号</th>
											<th width="11%">商户号</th>
											<th width="11%">原订单总金额</th>
											<th width="11%">退款金额</th>
											<th width="11%">退款渠道</th>
											<th width="11%">退款时间</th>
											<th width="11%">退款状态</th>
											<th width="12%">退款人编号</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${refundList}" var="bean" varStatus="status">
											<tr  >
												<td id="refundId">${bean.refundId}</td>
												<td id="orderId">${bean.orderId}</td>
												<td id="mchId">${bean.mchId}</td>
												<td id="totalAmt">${bean.totalAmt}</td>
												<td id="refundAmt">${bean.refundAmt}</td>
												<td id="refundChannel">${bean.refundChannel}</td>
												<td id="refundTime"><fmt:formatDate value="${bean.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												
												<td id="refundState">
													<c:choose>
														<c:when test="${bean.refundState=='0'}">
															退款成功
														</c:when>
														<c:when test="${bean.refundState=='1'}">
															退款申请
														</c:when>
														<c:when test="${bean.refundState=='9'}">
															退款失败
														</c:when>
													</c:choose>
												</td>
												
												<td id="createId">${bean.createId}</td>
												
											</tr>
										</c:forEach>
										<c:if test="${empty refundList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty refundList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
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

