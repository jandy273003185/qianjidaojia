<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.subjectSummary.SubjectSummaryPath"%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>科目汇总查询</title>
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
							<form action='<c:url value="<%=SubjectSummaryPath.BASE + SubjectSummaryPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
								
									<td class="td-left" width="18%">科目</td>
									<td class="td-right" width="32%">
										<span class="input-icon">
											<input type="text" name="subjectName"  id="subjectName"  value="${subjectName }"> 
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="18%">会计日期</td>
									<td class="td-right" width="32%">
										<input type="text" id="businessTerm" name="workDate"   value="${workDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
										<label class="label-tips" id="businessTermLab"></label>
											
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
												<gyzbadmin:function url="<%=SubjectSummaryPath.BASE + SubjectSummaryPath.LIST %>">
													<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
														查询
														<i class="icon-search icon-on-right bigger-110"></i>
													</button>
												</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin "  id="clearSubjectSummary">
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">科目汇总</div>

							<div class="table-responsive">
								
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
										    <th>编号</th>
											<th>科目名称</th>
											<th>期初余额</th>
											<th>本期借方余额</th>
											<th>本期贷方余额</th>
											<th>期末余额</th>
											<th>会计日期</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${summaryList}" var="summary" varStatus="status">
											<tr class="summary" >
											    <td>${summary.id }</td>
												<td>${summary.subjectName}</td>
												<td>${summary.lastAmount}</td>
												<td>${summary.amountD}</td>
												<td>${summary.amountC}</td>
												<td>${summary.finalAmount}</td>
												<td>${summary.workDate}</td>
											</tr>
										</c:forEach>
										<c:if test="${empty summaryList}">
											<tr><td colspan="7" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								</div>	
							</div>
						</div>
					</div>
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

	$(document).ready(function(){
		
		$("#clearSubjectSummary").click(function(){
			$(".search-table #subjectName").val("");
			$(".search-table #businessTerm").val("");
		});
		
		var summarys= ${summaryList};
		
		var summaryList=$("tr.summary");
		
		$.each(summarys,function(i,value){
			
			$.data(summaryList[i],"summary",value);
		});
	});
	
	$('#datetimepicker2').datetimepicker({
		pickTime : false,
		language: 'cn'
	});
</script>
</body>
</html>					