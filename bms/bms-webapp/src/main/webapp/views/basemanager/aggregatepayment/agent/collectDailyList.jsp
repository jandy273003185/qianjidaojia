<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.agent.controller.AgentCollectDailyPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>代理商每日汇集列表</title>
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
		$("#status").val($("#hiddenStatus").val());
	}
	
	$(function(){
		$(".exportBut").click(function(){
			var agentId = $("#agentId").val();
			var merchantId = $("#merchantId").val();
			var status = $("#status").val();
			var src ="<%= request.getContextPath()+ AgentCollectDailyPath.BASE + AgentCollectDailyPath.COLLECTDAILYEXPORT%>?agentId="+
			agentId+"&merchantId="+merchantId+"&status="+status;
			$(".exportBut").attr("href",src);
		});

		$(".clearBtn").click(function(){
			$("#agentId,#merchantId,#status").val("");
		});
	});

</script>
<body onload="loadQueryBean()">
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
							<form action='<c:url value="<%=AgentCollectDailyPath.BASE + AgentCollectDailyPath.COLLECTDAILYLIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">代理商编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="agentId" name="agentId" value="${queryBean.agentId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merchantId" name="merchantId" value="${queryBean.merchantId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="hidden" value="${queryBean.status }" id="hiddenStatus"/>
											<select id="status" name="status">
												<option value="">--请选择--</option>
												<option value="WAIT_SETTLE">--待清算--</option>
												<option value="SETTLE_ING">--清算中--</option>
												<option value="SETTLED">--已清算--</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AgentCollectDailyPath.BASE + AgentCollectDailyPath.COLLECTDAILYLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AgentCollectDailyPath.BASE + AgentCollectDailyPath.COLLECTDAILYEXPORT%>">
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
								代理商每日汇集列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:5%;">明细编号</th>
											<th style="width:5%;">代理商编号</th>
											<th style="width:10%;">结算会计日期</th>
											<th style="width:10%;">商户编号</th>
											<th style="width:5%;">产品渠道</th>
											<th style="width:5%;">应收金额</th>
											<th style="width:10%;">状态</th>
											<th style="width:5%;">记账日期</th>
											<th style="width:5%;">生成时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${agentCollectDailyList}" var="bean">
											<tr class="bean">
												<td>${bean.agentId}</td>
												<td>${bean.dailyId}</td>
											    <td>${bean.workDate}</td>
												<td>${bean.merchantId}</td>
												<td>${bean.channelCode}</td>
												<td>${bean.transTotalFee}</td>
												<td >
													<c:choose>
														<c:when test="${bean.status=='WAIT_SETTLE'}">待结算</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='SETTLE_ING'}">结算中</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='SETTLED'}">已结算</c:when>
													</c:choose>
												</td>
												
												<td>${bean.instDate}</td>
												<td>${bean.instDatetime}</td>
												
											</tr>
										</c:forEach>
										<c:if test="${empty agentCollectDailyList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty agentCollectDailyList}">
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