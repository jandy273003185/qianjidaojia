<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>经办修改</title>
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
						<form action='<c:url value="<%=AdjustPath.BASE + AdjustPath.EDIT %>"/>' method="post">
							<input type="hidden" name="mainInfo.opId" value="${opId }"/>
							<div class="list-table-header">账务分录</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>借</th>
											<th>贷</th>
											<th>金额</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${entryList }" var="entry">
										<tr>
											<td>
												<input type="text" name="entryList[0].debitAcctNo" id="debitAcctNo" value="${entry.debitAcctNo }" onchange="queryActInfo('debitAcctNo', 'debitAcctName', 'debitCustId', 'debitSubjectId', 'debitAcctType', 'debitAcctBalance')">
												<input type="text" name="entryList[0].debitAcctName" id="debitAcctName" value="${entry.debitAcctName }" style="width: 400px;"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
												<input type="hidden" name="entryList[0].debitAcctType" id="debitAcctType" value="SEVEN_INNER"/>
												<input type="hidden" name="entryList[0].debitCustId" id="debitCustId" value="${entry.debitCustId }"/>
												<input type="hidden" name="entryList[0].debitSubjectId" id="debitSubjectId" value="${entry.debitSubjectId }"/>
												<input type="hidden" name="entryList[0].debitAcctBalance" id="debitAcctBalance"/>
											</td>
											<td>
												<input type="text" name="entryList[0].creditAcctNo" id="creditAcctNo" value="${entry.creditAcctNo }" onchange="queryActInfo('creditAcctNo', 'creditAcctName', 'creditCustId', 'creditSubjectId', 'creditAcctType', 'creditAcctBalance')">
												<input type="text" name="entryList[0].creditAcctName" id="creditAcctName" value="${entry.creditAcctName }" style="width: 400px;"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
												<input type="hidden" name="entryList[0].creditAcctType" id="creditAcctType" value="SEVEN_INNER"/>
												<input type="hidden" name="entryList[0].creditCustId" id="creditCustId" value="${entry.creditCustId }"/>
												<input type="hidden" name="entryList[0].creditSubjectId" id="creditSubjectId" value="${entry.creditSubjectId }"/>
												<input type="hidden" name="entryList[0].creditAcctBalance" id="creditAcctBalance"/>
											</td>
											<td>
												<input type="text" name="entryList[0].amt" value="${entry.amt }"> ${entry.curcde }
												<input type="hidden" name="entryList[0].curcde" value="${entry.curcde }">
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty entryList}">
										<tr><td colspan="3" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
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
							
							<div class="table-responsive">
								<input type="hidden" name="resubmit" id="resubmit" value="false"/>
								<button type="button" class="btn btn-purple btn-sm btn-margin goback">返回</button>
				            	<button type="submit" class="btn btn-purple btn-sm btn-margin delBtn">删除</button>
				            	<button type="submit" class="btn btn-purple btn-sm btn-margin resubmitBtn">保存修改</button>
							</div>
						</form>
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
	
		function init() {
			
			// 1. 账户信息初始化
			queryActInfo('debitAcctNo', 'debitAcctName', 'debitCustId', 'debitSubjectId', 'debitAcctType', 'debitAcctBalance');
			
			queryActInfo('creditAcctNo', 'creditAcctName', 'creditCustId', 'creditSubjectId', 'creditAcctType', 'creditAcctBalance');
			
		}
	
		jQuery(function($){
			
			init();
			
			// 返回
			$('.goback').click(function(){
				$.blockUI();
				window.history.back();
			});
			
			// 重新提交
			$('.resubmitBtn').click(function(){
				
				$('#resubmit').val("true");
				
				// 借方账户
				var debitAcctNo = $('input[name="entryList[0].debitAcctNo"]').val();
				if(kong.test(debitAcctNo)) {
					$.gyzbadmin.alertFailure('借方账号不可为空', undefined, function(){
						$('input[name="entryList[0].debitAcctNo"]').focus();});
					return false;
				}
				var debitAcctName = $('input[name="entryList[0].debitAcctName"]').val();
				if(kong.test(debitAcctName)) {
					$.gyzbadmin.alertFailure('借方户名不可为空', undefined, function(){
						$('input[name="entryList[0].debitAcctNo"]').focus();});
					return false;
				}
				
				// 贷方账户
				var creditAcctNo = $('input[name="entryList[0].creditAcctNo"]').val();
				if(kong.test(creditAcctNo)) {
					$.gyzbadmin.alertFailure('贷方账号不可为空', undefined, function(){
						$('input[name="entryList[0].creditAcctNo"]').focus();});
					return false;
				}
				var creditAcctName = $('input[name="entryList[0].creditAcctName"]').val();
				if(kong.test(creditAcctName)) {
					$.gyzbadmin.alertFailure('贷方户名不可为空', undefined, function(){
						$('input[name="entryList[0].creditAcctNo"]').focus();});
					return false;
				}
				
				// 金额
				var amt = $('input[name="entryList[0].amt"]').val();
				if(kong.test(amt)) {
					$.gyzbadmin.alertFailure('金额不可为空', undefined, function(){
						$('input[name="entryList[0].amt"]').focus();});
					return false;
				} else {
					if (!isAmount(amt)) {
						$.gyzbadmin.alertFailure('金额格式不对', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
					}
					
					if (parseFloat(amt) <= 0) {
						$.gyzbadmin.alertFailure('调账金额必须大于0', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
					}
					
					// 判断借方账户金额是否足够
					if (parseFloat($('input[name="entryList[0].debitAcctBalance"]').val()) - parseFloat(amt) < 0) {
						$.gyzbadmin.alertFailure('借方账户余额不足', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
        			}
				}
				
				$.blockUI();
				
				
			});
			
			// 删除申请
			$('.delBtn').click(function() {
				$('#resubmit').val("false");
				$.blockUI();
			});
		});
		
		// 查询账号信息
		function queryActInfo(actNoId, actNameId, custId, subjectId, acctType, acctBalance) {
			
			var actNo = $("#" + actNoId).val();
			if (kong.test(actNo)) {
				/* $.gyzbadmin.alertFailure('请输入账号！', undefined, function(){
        			$("#" + actNoId).focus();}); */
				return false;
			}
			var acctType = $("#" + acctType).val();
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=AdjustPath.BASE + AdjustPath.QUERY_ACCT_INFO%>',
		        data:{"acctId":actNo, "acctType":acctType},
		        success:function(data){
		        	if(data.result == "FAILURE"){
		        		$("#" + actNameId).val("");
		        		$("#" + custId).val("");
	        			$("#" + subjectId).val("");
	        			$("#" + acctBalance).val("");
		        	}else if(data.result == "SUCCESS"){
		        		if (undefined == data.acctSeven) {
		        			$("#" + actNameId).val("");
		        			$("#" + custId).val("");
		        			$("#" + subjectId).val("");
		        			$("#" + acctBalance).val("");
		        		} else {
		        			$("#" + actNameId).val(data.acctSeven.acctName);
		        			$("#" + custId).val(data.acctSeven.custId);
		        			$("#" + subjectId).val(data.acctSeven.subjectId);
		        			$("#" + acctBalance).val(data.acctSeven.balance);
		        		}
		        	}
		        	if (kong.test($("#" + actNameId).val())) {
		        		$.gyzbadmin.alertFailure('账号不存在', undefined, function(){
		        			$("#" + actNoId).focus();});
		        	}
				}
			});
			
			return false;
		}
	</script>
</body>
</html>