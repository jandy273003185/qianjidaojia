<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>调账经办</title>
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
							<form action='<c:url value="<%=AdjustPath.BASE + AdjustPath.HANDLE_SUBMIT %>"/>' method="post">
							<input type="hidden" name="SUBMIT_TOKEN" value="${SUBMIT_TOKEN }"/>
							<input type="hidden" name="mainInfo.singleFlag" id="singleFlag" value="0" />
							<input type="hidden" name="entryListCount" id="entryListCount" value="1" /> <!-- 记录分录条数 -->
							<table class="search-table">
								<tr>
									<td class="td-left" width="30%">借</td>
									<td class="td-right" width="70%">
										<input type="text" name="entryList[0].debitAcctNo" id="debitAcctNo" style="width: 260px;" onchange="queryActInfo('debitAcctNo', 'debitAcctName', 'debitCustId', 'debitSubjectId', 'debitAcctType', 'debitAcctBalance')">
										<input type="text" name="entryList[0].debitAcctName" id="debitAcctName" style="width: 400px;"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
										<input type="hidden" name="entryList[0].debitAcctType" id="debitAcctType" value="SEVEN_INNER"/>
										<input type="hidden" name="entryList[0].debitCustId" id="debitCustId"/>
										<input type="hidden" name="entryList[0].debitSubjectId" id="debitSubjectId"/>
										<input type="hidden" name="entryList[0].debitAcctBalance" id="debitAcctBalance"/>
										<!-- <button type="button" class="btn btn-purple btn-sm btn-margin" onclick="queryActInfo('debitAcctNo', 'debitAcctName', 'debitCustId', 'debitSubjectId', 'debitAcctType')">查询</button> -->
									</td>
								</tr>
								<tr>
									<td class="td-left">贷</td>
									<td class="td-right">
										<input type="text" name="entryList[0].creditAcctNo" id="creditAcctNo" style="width: 260px;" onchange="queryActInfo('creditAcctNo', 'creditAcctName', 'creditCustId', 'creditSubjectId', 'creditAcctType', 'creditAcctBalance')">
										<input type="text" name="entryList[0].creditAcctName" id="creditAcctName" style="width: 400px;"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
										<input type="hidden" name="entryList[0].creditAcctType" id="creditAcctType" value="SEVEN_INNER"/>
										<input type="hidden" name="entryList[0].creditCustId" id="creditCustId"/>
										<input type="hidden" name="entryList[0].creditSubjectId" id="creditSubjectId"/>
										<input type="hidden" name="entryList[0].creditAcctBalance" id="creditAcctBalance"/>
										<!-- <button type="button" class="btn btn-purple btn-sm btn-margin" onclick="queryActInfo('creditAcctNo', 'creditAcctName', 'creditCustId', 'creditSubjectId', 'creditAcctType')">查询</button> -->
									</td>
								</tr>
								<tr>
									<td class="td-left">金额</td>
									<td class="td-right">
										<input type="hidden" name="entryList[0].curcde" value="CNY">
										<input type="text" name="entryList[0].amt" style="width: 260px; text-align: right;"> <span>CNY</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">关联交易业务编号</td>
									<td class="td-right" colspan="1">
										<div>
											<textarea rows="3" cols="80" name="mainInfo.relationOpId" ></textarea>&nbsp;
										</div>
										<div><span style="color: red;">注: 多个业务编号以英文','分隔,只包含英文字母,-_@等字符</span></div>
									</td>
								</tr>
								<tr>
									<td class="td-left">备注</td>
									<td class="td-right" colspan="1">
										<textarea rows="3" cols="80" name="mainInfo.memo" ></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="reset" class="btn btn-purple btn-sm btn-margin resetBtn">
												重置
											</button>
											<button type="submit" class="btn btn-purple btn-sm btn-margin submitBtn" >
												提交
											</button>
										</span>
									</td>
								</tr>
							</table>
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
		
		jQuery(function($){
			
			// 重置按钮
			$('.resetBtn').click(function(){
				$('#entryListCount').val("1");
			});
			
			// 提交按钮
			$('.submitBtn').click(function(){
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
						$('input[name="entryList[0].debitAcctName"]').focus();});
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
						$('input[name="entryList[0].creditAcctName"]').focus();});
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
				
				// 关联交易编号
				var relationOpId = $('textarea[name="mainInfo.relationOpId"]').val();
				if(!kong.test(relationOpId)) {
					var relationOpIdRegular = /^[a-zA-Z0-9_@\-]+?(,\s*[a-zA-Z0-9_@\-]+?)*$/
					if(!relationOpIdRegular.test(relationOpId)) {
						$.gyzbadmin.alertFailure('关联交易编号格式非法', undefined, function(){
							$('textarea[name="mainInfo.relationOpId"]').focus();});
						return false;
					}
					if (relationOpId.length > 500) {
						$.gyzbadmin.alertFailure('关联交易编号长度超过了最大限制500', undefined, function(){
							$('textarea[name="mainInfo.relationOpId"]').focus();});
						return false;
					}
				}
				
				// 保存提交
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