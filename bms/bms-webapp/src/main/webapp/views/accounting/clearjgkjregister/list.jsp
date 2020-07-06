<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.ClearJgkjRegisterPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>交广科技开户未明列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$('.clearClearJgkjRegisterClr').click(function(){			
		$(".page-content #mobileClr").val("");
		$(".page-content #nameClr").val("");
		$(".page-content #transFlowIdClr").val("");
		$(".page-content #idCodeClr").val("");
	})
});
</script>
<body>
	<!-- 开户未明处理 -->
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="main-container-inner">
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<%@ include file="/include/path.jsp"%>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form action='<c:url value="<%=ClearJgkjRegisterPath.BASE + ClearJgkjRegisterPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">手机号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="mobile"  value="${queryBean.mobile }" id="mobileClr">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">客户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="name"  value="${queryBean.name}"  id="nameClr">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">交易编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="transFlowId"  value="${queryBean.transFlowId }" id="transFlowIdClr">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">证件号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="idCode"  value="${queryBean.idCode}"  id="idCodeClr">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ClearJgkjRegisterPath.BASE + ClearJgkjRegisterPath.LIST%>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearClearJgkjRegisterClr"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i></button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">交广科技开户未明列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="12%">发送交广科技流水号</th>
											<th width="12%">交易编号</th>
											<th width="8%">手机号码</th>
											<th width="5%">交易码</th>
											<th width="8%">客户名称</th>
											<th width="10%">证件号码</th>
											<th width="5%">发送日期</th>
											<th width="12%">返回交易流水号</th>
											<th width="5%">返回时间</th>
											<th width="5%">返回码</th>
											<th width="12%">返回信息</th>
											<th width="8%">对账状态</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${clearjgkjregisterList}" var="result">
											<tr class="result">
												<td>${result.id }</td>
												<td>${result.transFlowId }</td>
												<td>${result.mobile }</td>
												<td>${result.transCode }</td>
												<td>${result.name }</td>
												<td>${result.idCode }</td>
												<td>${result.sendDate }</td>
												<td>${result.rtnSeq }</td>
												<td>${result.rtnTime }</td>
												<td>${result.rtnCode }</td>
												<td>${result.rtnInfo }</td>
												<td>${result.balStatus}</td>
											</tr>
										</c:forEach>
										<c:if test="${empty clearjgkjregisterList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty clearjgkjregisterList}">
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