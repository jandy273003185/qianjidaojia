<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.expresspay.cardaccount.controller.CardAccountPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>卡账户信息查询</title>
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
				<form action='<c:url value="<%=CardAccountPath.BASE+CardAccountPath.CARDACCOUNTMAIN %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">手机号码：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="mobile" name="mobile" value="${queryBean.mobile}" maxlength="55" >
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">交广卡号：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="cardNo" name="cardNo" value="${queryBean.cardNo}" maxlength="55" >
									<i class="icon-leaf blue"></i>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center" >
							<gyzbadmin:function url="<%=CardAccountPath.BASE+CardAccountPath.CARDACCOUNTMAIN  %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button class="btn btn-purple btn-sm btn-margin clearAccountList" >
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
					<div class="list-table-header">卡账户信息</div>
					<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>客户号</th>
								<th>卡号</th>
								<th>余额</th>
								<th>状态</th>
								<th>卡类型</th>
								<th>激活日期</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${cardAccountList}" var="cardAccount">
						<tr class="cardAccount">
							<td>${cardAccount.custId }</td>
							<td>${cardAccount.cardNo }</td>
							<td>${cardAccount.cdBal }</td>
							<td>
								<c:if test="${cardAccount.status == '00' }">
									正常
								</c:if>
								<c:if test="${cardAccount.status == '01' }">
									挂失
								</c:if>
								<c:if test="${cardAccount.status == '02' }">
									冻结
								</c:if>
								<c:if test="${cardAccount.status == '03' }">
									注销
								</c:if>
								<c:if test="${cardAccount.status == '04' }">
									已换卡
								</c:if>
								<c:if test="${cardAccount.status == '09' }">
									已退卡
								</c:if>
							</td>
							<td>
								<c:if test="${cardAccount.cardType == '00' }">
									记名磁条卡
								</c:if>
								<c:if test="${cardAccount.cardType == '01' }">
									非记名磁条卡
								</c:if>
								<c:if test="${cardAccount.cardType == '10' }">
									IC卡
								</c:if>
							</td>
							<td>${cardAccount.activeDay}</td>
						</tr>
						</c:forEach>
						<c:if test="${empty cardAccountList}">
							<tr><td colspan="6" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
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
<script type="text/javascript">

		$(".clearAccountList").click(function(){
			$(".search-table #mobile").val('');
			$(".search-table #cardNo").val('');
		});

</script>
</body>
</html>