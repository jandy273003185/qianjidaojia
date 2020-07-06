<%@page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>调账历史查询</title>
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
							<!-- 查询条件 -->
							<form id="historyForm" action='<c:url value="<%=AdjustPath.BASE + AdjustPath.HISTORY_LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" width="10%">借方账号</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<input type="text" name="debitAcctNo" id="debitAcctNo" value="${queryBean.debitAcctNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="10%">贷方账号</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<input type="text" name="creditAcctNo" id="creditAcctNo" value="${queryBean.creditAcctNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="10%">经办日期</td>
									<td class="td-right" width="30%">
										<span class="input-icon">
											<input type="text" name="handleDateStart" id="handleDateStart" value="${queryBean.handleDateStart }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</span>
											-
										<span class="input-icon">
											<input type="text" name="handleDateEnd" id="handleDateEnd" value="${queryBean.handleDateEnd }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AdjustPath.BASE + AdjustPath.HISTORY_LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin searchBtn" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											
											<button class="btn btn-purple btn-sm btn-margin clearUser" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AdjustPath.BASE + AdjustPath.HISTORY_LIST_EXCEL%>">
												<a class="btn btn-purple btn-sm" id="exportHistoryList"  >
													导出报表
													<i class="icon-download bigger-110"></i> 
												</a> 
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">调账历史列表</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>业务编号</th>
											<th>借方账户类型</th>
											<th>借方账号</th>
											<th>借方账户名称</th>
											<th>贷方账户类型</th>
											<th>贷方账号</th>
											<th>贷方账户名称</th>
											<th>币别</th>
											<th>金额</th>
											<th>经办日期</th>
											<th>经办人</th>
											<th>关联业务编号</th>
											<th>备注</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${list }" var="adjustDetail">
										<tr class="user">
											<td>${adjustDetail.opId }</td>
											<td>${adjustDetail.debitAcctType}</td>
											<td>${adjustDetail.debitAcctNo }</td>
											<td>${adjustDetail.debitAcctName }</td>
											<td>${adjustDetail.creditAcctType}</td>
											<td>${adjustDetail.creditAcctNo }</td>
											<td>${adjustDetail.creditAcctName }</td>
											<td><nobr>${adjustDetail.curcde }</nobr></td>
											<td><nobr><fmt:formatNumber value="${adjustDetail.amt }" pattern="#,##0.00#"/></nobr></td>
											<td>
												${adjustDetail.handleDatetime }
											</td>
											<td>${adjustDetail.handlerUid }</td>
											<td>${adjustDetail.relationOpId }</td>
											<td>${adjustDetail.memo }</td>
										</tr>
									</c:forEach>
									<c:if test="${empty list}">
											<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty list}">
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

	<script type="text/javascript">
	
		jQuery(function($){
		
			$('.clearUser').click(function(){
				$('.search-table #debitAcctNo').val('');
				$('.search-table #creditAcctNo').val('');
				$('.search-table #handleDateStart').val('');
				$('.search-table #handleDateEnd').val('');
			}); 
			
			$('.searchBtn').click(function(){
				var compBeginTime = $("#handleDateStart").val();
				var compEndTime= $("#handleDateEnd").val();
				if("" != compBeginTime && "" != compEndTime && compBeginTime > compEndTime) 
				{
					$.gyzbadmin.alertFailure("调账结束日期不能小于起始日期");
					return false;
				}
				
				var form = $('#historyForm');
				form.submit();
			});
			
			$("#exportHistoryList").click(function(){
				var compBeginTime = $("#handleDateStart").val();
				var compEndTime= $("#handleDateEnd").val();
				if("" != compBeginTime && "" != compEndTime && compBeginTime > compEndTime) 
				{
					$.gyzbadmin.alertFailure("调账结束日期不能小于起始日期");
					return false;
				}
				var src = '<c:url value="<%=AdjustPath.BASE + AdjustPath.HISTORY_LIST_EXCEL %>"/>'
					+ '?handleDateStart=' + $("#handleDateStart").val()
					+ '&handleDateEnd=' + $("#handleDateEnd").val()
					+ '&debitAcctNo=' + $("#debitAcctNo").val()
					+ '&creditAcctNo=' + $("#creditAcctNo").val();
					
		 		$("#exportHistoryList").attr("href", src);
				
		 	});
		});
		
	</script>
  </body>	
</html>