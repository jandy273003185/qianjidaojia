<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.acctsevenbuss.AcctSevenBussPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户账号管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body  onload = "loadAcctSevenBuss()">
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
				<form action='<c:url value="<%=AcctSevenBussPath.BASE+AcctSevenBussPath.LIST %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">商户编号</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="merchantCode" name="merchantCode" maxlength="55" value="${queryBean.merchantCode }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">商户名称</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctName" name="acctName" maxlength="55" value="${queryBean.acctName }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						
					</tr>
					 <tr>
						<td class="td-left">七分钱账号</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctId" name="acctId" maxlength="55" value="${queryBean.acctId }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">状态</td>
						<td class="td-right"> 
							<input type="hidden"  id="statusTemp" name="statusTemp" value="${queryBean.status}">
							<select name="status" id="status" >
							  <option  value="">- 请选择 -</option>
							  <option  value="NORMAL">- 可用 -</option>
							  <option  value="FREEZE">- 冻结 -</option>
							  <option  value="CANCEL">- 注销 -</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<gyzbadmin:function url="<%=AcctSevenBussPath.BASE+AcctSevenBussPath.LIST %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							
							<button  class="btn btn-purple btn-sm btn-margin clearAcctSevenBuss" >
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
					<div class="list-table-header">商户账户列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>商户编号</th>
								<th>七分钱账号</th>
								<th>商户名称</th>
								<th>科目编号</th>
								<th>余额方向</th>
								<th>余额</th>
								<th>冻结金额</th>
								<th>在途金额</th>
								<th>可用余额</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${acctSevenBussList}" var="acctBuss">
						<tr class="acctBuss">
							<td>${acctBuss.merchantCode }</td>
							<td>${acctBuss.acctId }</td>
							<td>${acctBuss.acctName }</td>
							<td>${acctBuss.subjectId }</td>
							<td>
								<c:if test="${acctBuss.balanceFlag == 'CREDIT' }">贷</c:if>
								<c:if test="${acctBuss.balanceFlag == 'DEBIT' }">借</c:if>
							</td>
							<td>${acctBuss.balance}</td>
							<td>${acctBuss.freezeAmt }</td>
							<td>${acctBuss.onwayAmt }</td>
							<td>${acctBuss.usableAmt }</td>
							<td>
								<c:if test="${acctBuss.status == 'NORMAL'}">可用</c:if>
								<c:if test="${acctBuss.status == 'FREEZE'}">冻结</c:if>
								<c:if test="${acctBuss.status == 'CANCEL'}">注销</c:if>
							</td>
							<td>${acctBuss.instDatetime }</td>
							<td>
							<gyzbadmin:function url="<%=AcctSevenBussPath.BASE+AcctSevenBussPath.EDIT%>">
								 <a href="#" class="tooltip-success updateAcctSevenBuss" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateAcctSevenBussModal">
									<span class="green">
										<i class="icon-edit bigger-120"></i>
									</span>
								 </a>
							</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty acctSevenBussList}">
							<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty acctSevenBussList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
		<div class="modal fade" id="updateAcctSevenBussModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户账户修改</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="20%">商户编号</td>
									<td class="td-right" width="80%">
										<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<input type="text" id="acctName" name="acctName" readonly="readonly" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">七分钱账号</td>
									<td class="td-right">
										<input type="text" id="acctId" name="acctId" readonly="readonly" maxlength="200" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">状态</td>
									<td class="td-right">
										<select name="status" id="status" clasS="width-80">
										  <option  value="NORMAL">- 可用 -</option>
										  <option  value="FREEZE">- 冻结 -</option>
										  <option  value="CANCEL">- 注销 -</option>
										</select>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary updateAcctSevenBussBtn">提交</button>
				            </div>
			      		</div>
			         </div>
			      </div><!-- /.modal-content -->
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
	function loadAcctSevenBuss(){
		$(".search-table #status").val($(".search-table #statusTemp").val());
	}
	
	$(function(){
		var acctBussListJson = ${acctSevenBussList};
		var acctBussTrList = $('tr.acctBuss');
		$.each(acctBussListJson, function(idx, obj){
			$.data(acctBussTrList[idx], 'acctBuss', obj);
		});
		
		$(".clearAcctSevenBuss").click(function(){
			$(".search-table #merchantCode").val('');
			$(".search-table #acctId").val('');
			$(".search-table #acctName").val('');
			$(".search-table #status").val('');
		});
		
	  $(".updateAcctSevenBuss").click(function(){
		 var acctBuss = $.data($(this).parent().parent()[0],"acctBuss");
		 $('#updateAcctSevenBussModal').on('show.bs.modal', function () {
    	    $("#updateAcctSevenBussModal #merchantCode").val(acctBuss.merchantCode);
    	    $("#updateAcctSevenBussModal #acctName").val(acctBuss.acctName);
    	    $("#updateAcctSevenBussModal #acctId").val(acctBuss.acctId);
    	    $("#updateAcctSevenBussModal #status").val(acctBuss.status);
	     })
		 $('#updateAcctSevenBussModal').on('hide.bs.modal', function () {
			$("#updateAcctSevenBussModal #merchantCode").val('');
    	    $("#updateAcctSevenBussModal #acctName").val('');
    	    $("#updateAcctSevenBussModal #acctId").val('');
    	    $("#updateAcctSevenBussModal #status").val('');
	     })
	  });
	  
	// 修改
	$('.updateAcctSevenBussBtn').click(function(){
		
		var acctId = $("#updateAcctSevenBussModal #acctId").val();
		if(kong.test(acctId)){
			$.gyzbadmin.alertFailure("商户七分钱账号不能为空");
			return false;
		}
		
		var status = $("#updateAcctSevenBussModal #status").val();
		if(kong.test(status)){
			$.gyzbadmin.alertFailure("请选择账户状态");
			return false;
		}
		// 保存修改
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=AcctSevenBussPath.BASE+AcctSevenBussPath.EDIT%>', {
				'acctId':acctId,
				'status':status
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#updateAcctSevenBussModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('修改失败:' + data.message);
				}
			}, 'json'
		);
	});
});

</script>
</body>
</html>