<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.acctingworkdate.AcctWorkDatePath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css?v=${_cssVersion}"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js?v=${_jsVersion}"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js?v=${_jsVersion}"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>客户账号管理</title>
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
				<form action='<c:url value="<%=AcctWorkDatePath.BASE+AcctWorkDatePath.LIST %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">会计日期</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="workDate" name="workDate"  value="${queryBean.workDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
							</span>
						</td>
						<td class="td-left">创建人</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="instUser" name="instUser" maxlength="55" value="${queryBean.instUser }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<gyzbadmin:function url="<%=AcctWorkDatePath.BASE+AcctWorkDatePath.LIST %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button  class="btn btn-purple btn-sm btn-margin clearAcctSevenCust" >
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
					<div class="list-table-header">会计日期信息</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>会计日期</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${acctWorkDateList}" var="acctWorkDate">
						<tr class="acctWorkDate">
							<td>${acctWorkDate.workDate }</td>
							<td>${acctWorkDate.instUser }</td>
							<td>${acctWorkDate.instDatetime }</td>
							<td>${acctWorkDate.memo}</td>
							<td> 	
								<gyzbadmin:function url="<%=AcctWorkDatePath.BASE + AcctWorkDatePath.EDIT%>">
									<a href="#updateAcctingDateModal"  data-toggle='modal' class="tooltip-success updateAcctingDate" data-rel="tooltip" title="Edit">
										<span class="green">
											<i class="icon-edit bigger-120"></i>
										</span>
									</a>
								</gyzbadmin:function>
								
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty acctWorkDateList}">
							<tr><td colspan="5" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty acctWorkDateList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
			
		<div class="modal fade" id="updateAcctingDateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">会计日期修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	
						<tr>	
							<td class="td-left">会计日期</td>
							<td class="td-right">
								<input type="text" id="workDate" name="workDate" readonly="readonly" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea rows="10" cols="" id="memo" name="memo" style="width:80%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateAcctingDateBtn">提交</button>
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
	$(function(){
		var acctDateListJson = '<c:out value="${gyzb:toJSONString(acctWorkDateList)}" escapeXml="false"/>';
		var acctDateTrList = $('tr.acctWorkDate');
		$.each($.parseJSON(acctDateListJson), function(idx, obj){
			$.data(acctDateTrList[idx], 'acctWorkDate', obj);
		});
		
		$(".clearAcctSevenCust").click(function(){
			$(".search-table #workDate").val('');
			$(".search-table #instUser").val('');
		});
		
		$(".updateAcctingDate").click(function(){
			var acctingDate = $.data($(this).parent().parent()[0], 'acctWorkDate');
			$('#updateAcctingDateModal').on('show.bs.modal', function () {
				// 赋值
				$("#updateAcctingDateModal #workDate").val(acctingDate.workDate);
				$("#updateAcctingDateModal #memo").val(acctingDate.memo);
				
			});
			$('#updateAcctingDateModal').on('hide.bs.modal', function () {
				// 清除
				$("#updateAcctingDateModal #workDate").val('');
				$("#updateAcctingDateModal #memo").val('');
				
			});
		});
		
		// 修改
		$('.updateAcctingDateBtn').click(function(){
			// 客户编号
			var workDate = $('#updateAcctingDateModal #workDate').val();
			if(kong.test(workDate)) {
				$.gyzbadmin.alertFailure('会计日期不可为空');
				return;
			}
			// 七分钱账号
			var memo = $('#updateAcctingDateModal #memo').val();
		
			// 保存修改
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=AcctWorkDatePath.BASE + AcctWorkDatePath.EDIT %>', {
					'workDate'		: workDate,
					'memo'		: memo
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#updateAcctingDateModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
					}
				}, 'json'
			);
		});
});

</script>
</body>
</html>