<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.acctseveninner.AcctSevenInnerPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css?v=${_cssVersion}"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js?v=${_jsVersion}"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>内部账号管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
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
			<div class="page-content" >
				<!-- 账户邮箱 -->
				<div >
				<form action='<c:url value="<%=AcctSevenInnerPath.BASE+AcctSevenInnerPath.LIST %>"/>' method="post">
					<table class="search-table">
					   <tr>
					   <td class="td-left">七分钱账号</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctId" name="acctId" maxlength="55" value="${queryBean.acctId }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">账户名称</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctName" name="acctName" maxlength="55" value="${queryBean.acctName }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<gyzbadmin:function url="<%=AcctSevenInnerPath.BASE+AcctSevenInnerPath.LIST %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button  class="btn btn-purple btn-sm btn-margin clearAcctSevenInner" >
								清空
								<i class=" icon-move icon-on-right bigger-110"></i>
							</button>	
						</td>
					  </tr>
					</table>
					</form>
				</div>
				<!-- 持卡人信息-->
				<div >
					<div class="list-table-header">内部账户信息</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>七分钱账号</th>
								<th>账户名称</th>
								<th>科目编号</th>
								<th>余额</th>
								<th>余额方向</th>
								<th>状态</th>
								<th>创建时间</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${acctSevenInnerList}" var="acctInner">
						<tr class="acctInner">
							<td>${acctInner.acctId }</td>
							<td>${acctInner.acctName }</td>
							<td>${acctInner.subjectId }</td>
							<td>${acctInner.balance}</td>
							<td>
								<c:if test="${acctInner.balanceFlag == 'CREDIT' }">贷</c:if>
								<c:if test="${acctInner.balanceFlag == 'DEBIT' }">借</c:if>
							</td>
							<td>
								<c:if test="${acctInner.status == 'NORMAL'}">正常</c:if>
								<c:if test="${acctInner.status == 'FREEZE'}">冻结</c:if>
							</td>
							<td>${acctInner.instDatetime }</td>
						</tr>
						</c:forEach>
						<c:if test="${empty acctSevenInnerList}">
							<tr><td colspan="7" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty acctSevenInnerList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
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
	<script type="text/javascript">
	$(function(){
		var acctInnerListJson = '<c:out value="${gyzb:toJSONString(acctSevenInnerList)}" escapeXml="false"/>';
		var acctInnerTrList = $('tr.acctInner');
		$.each($.parseJSON(acctInnerListJson), function(idx, obj){
			$.data(acctInnerTrList[idx], 'acctInner', obj);
		});
		$(".clearAcctSevenInner").click(function(){
			$(".search-table #acctId").val('');
			$(".search-table #acctName").val('');
		});
	
});

</script>
</body>
</html>