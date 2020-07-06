<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>复核</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
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
							
							<div class="list-table-header">账务分录</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>账户</th>
											<th>户名</th>
											<th>账户类型</th>
											<th>调账金额</th>
											<th>可用金额</th>
											<th>在途金额</th>
											<th>是否联调交广科技</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${entryList }" var="entry">
										<tr>
											<td>${entry.acctNo }</td>
											<td>${entry.acctName }</td>
											<td>${entry.acctType }</td>
											<td>${entry.amt }</td>
											<td>${entry.usableAmt }</td>
											<td>${entry.onwayAmt }</td>
											<td>${entry.isAdjustJGKJ }</td>
										</tr>
									</c:forEach>
									<c:if test="${empty entryList}">
										<tr><td colspan="7" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
							</div>
							
							<div class="list-table-header">调账流水</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>操作日期</th>
											<th>操作人员</th>
											<th>操作意见</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${commentList }" var="comment">
										<tr>
											<td class="td-center" width="25%"><fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td class="td-left" width="25%">${comment.userId }</td>
											<td class="td-right" width="25%">${comment.fullMessage }</td>
										</tr>
									</c:forEach>
									<c:if test="${empty commentList}">
										<tr><td colspan="3" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
							</div>
							
							<div class="list-table-header">审批</div>
							<div class="table-responsive">
								<form action='<c:url value="<%=AdjustPath.BASE + AdjustPath.CHECK %>"/>' method="post">
									<input type="hidden" name="opId" id="opId" value="${opId }"/>
									<input type="hidden" name="checkPass" id="checkPass" value="false"/>
									<textarea name="memo" id="memo" cols="60" rows="3"></textarea>
					            	<button type="submit" class="btn btn-purple btn-sm btn-margin checkNotPassBtn">不通过</button>
					            	<button type="submit" class="btn btn-purple btn-sm btn-margin checkPassBtn">通过</button>
								</form>
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
	
	<script type="text/javascript">
		jQuery(function($){
	
			// 审批通过
			$('.checkPassBtn').click(function(){
				$.blockUI();
				$('#checkPass').val("true");
				return true;
			});
			
			
			// 审批不通过
			$('.checkNotPassBtn').click(function(){
				$.blockUI();
				$('#checkPass').val("false");
				return true;
			});
		});
	</script>
</body>
</html>