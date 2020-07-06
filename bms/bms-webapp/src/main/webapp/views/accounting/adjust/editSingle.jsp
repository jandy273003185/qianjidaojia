<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>经办修改(单边账)</title>
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
							<form action='<c:url value="<%=AdjustPath.BASE + AdjustPath.EDIT_SINGLE %>"/>' method="post">
							<input type="hidden" name="mainInfo.singleFlag" id="singleFlag" value="1" />
							<input type="hidden" name="mainInfo.opId" value="${opId }"/>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>账户类型</th>
											<th>账号</th>
											<th>金额</th>
											<th>可用金额</th>
											<th>在途金额</th>
											<th>是否联调交广账户</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${entryList }" var="entry">
										<tr>
											<td>
												<select name="entryList[0].acctType" id="acctType" onchange="acctTypeChange('0')" >
													<option value=""></option>
													<option value="SEVEN_CUST" <c:if test="${entry.acctType eq 'SEVEN_CUST'}"> selected="selected"</c:if>>七分钱客户账户</option>
													<option value="SEVEN_BUSS" <c:if test="${entry.acctType eq 'SEVEN_BUSS'}"> selected="selected"</c:if>>七分钱商户账户</option>
													<option value="SEVEN_INNER" <c:if test="${entry.acctType eq 'SEVEN_INNER'}"> selected="selected"</c:if>>七分钱内部账户</option>
													<option value="JGKJ_CARD" <c:if test="${entry.acctType eq 'JGKJ_CARD'}"> selected="selected"</c:if>>交广科技卡</option>
												</select>
											</td>
											<td>
												<input type="text" name="entryList[0].acctNo" id="acctNo" style="width: 250px;" value="${entry.acctNo }" onchange="queryActInfo('entryList[0].acctType', 'entryList[0].acctNo', 'entryList[0].acctName', 'entryList[0]._balance', 'entryList[0]._freezeAmt', 'entryList[0]._onwayAmt', 'entryList[0]._usableAmt')">
												<input type="text" name="entryList[0].acctName" id="acctName" style="width: 400px;" value="${entry.acctName }"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
												<input type="hidden" name="entryList[0]._balance" id="_balance"/>
												<input type="hidden" name="entryList[0]._freezeAmt" id="_freezeAmt"/>
												<input type="hidden" name="entryList[0]._onwayAmt" id="_onwayAmt"/>
												<input type="hidden" name="entryList[0]._usableAmt" id="_usableAmt"/>
											</td>
											<td>
												<input type="hidden" name="entryList[0].curcde" value="CNY">
												<input type="text" name="entryList[0].amt" value="${entry.amt }" style="width: 150px; text-align: right;"> ${entry.curcde }
											</td>
											<td>
												<input type="text" name="entryList[0].usableAmt" value="${entry.usableAmt }" style="width: 150px; text-align: right;"> ${entry.curcde }
											</td>
											<td>
												<input type="text" name="entryList[0].onwayAmt" value="${entry.onwayAmt }" style="width: 150px; text-align: right;"> ${entry.curcde }
											</td>
											<td>
												<select name="entryList[0].isAdjustJGKJ" id="isAdjustJGKJ" >
													<option value=""></option>
													<option value="N" <c:if test="${entry.isAdjustJGKJ eq 'N'}"> selected="selected"</c:if>>否</option>
													<option value="Y" <c:if test="${entry.isAdjustJGKJ eq 'Y'}"> selected="selected"</c:if>>是</option>
												</select>
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
			queryActInfo('entryList[0].acctType', 'entryList[0].acctNo', 'entryList[0].acctName', 'entryList[0]._balance', 'entryList[0]._freezeAmt', 'entryList[0]._onwayAmt', 'entryList[0]._usableAmt');
			
		}
		
		jQuery(function($){
			
			// 页面初始化工作begin
			init();
			// 页面初始化工作end
			
			// 返回
			$('.goback').click(function(){
				$.blockUI();
				window.history.back();
			});
			
			// 重新提交
			$('.resubmitBtn').click(function(){
				
				$('#resubmit').val("true");
				
				// 借贷标识
				var acctType = $('select[name="entryList[0].acctType"]').val();
				if(kong.test(acctType)) {
					$.gyzbadmin.alertFailure('账户类型不可为空', undefined, function(){
						$('input[name="entryList[0].acctType"]').focus();});
					return false;
				}
				
				// 账户
				var acctNo = $('input[name="entryList[0].acctNo"]').val();
				if(kong.test(acctNo)) {
					$.gyzbadmin.alertFailure('账号不可为空', undefined, function(){
						$('input[name="entryList[0].acctNo"]').focus();});
					return false;
				}
				var acctName = $('input[name="entryList[0].acctName"]').val();
				if(kong.test(acctName)) {
					$.gyzbadmin.alertFailure('账号不存在', undefined, function(){
						$('input[name="entryList[0].acctNo"]').focus();});
					return false;
				}
				
				// 金额
				var amt = $('input[name="entryList[0].amt"]').val();
				if(kong.test(amt)) {
					$.gyzbadmin.alertFailure('调账金额不可为空', undefined, function(){
						$('input[name="entryList[0].amt"]').focus();});
					return false;
				} else {
					if(!isAmount(amt)) {
						$.gyzbadmin.alertFailure('调账金额格式不对', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
					}
					
					// 调账金额是否足够
					if (parseFloat($('input[name="entryList[0]._balance"]').val()) + parseFloat(amt) < 0) {
						$.gyzbadmin.alertFailure('账户余额不足', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
        			}
				}
				
				if (acctType == 'SEVEN_CUST' || acctType == 'SEVEN_BUSS') {
					
					// 可用金额
					var usableAmt = $('input[name="entryList[0].usableAmt"]').val();
					if(kong.test(usableAmt)) {
						$.gyzbadmin.alertFailure('可用金额不可为空', undefined, function(){
							$('input[name="entryList[0].usableAmt"]').focus();});
						return false;
					} else {
						if(!isAmount(usableAmt)) {
							$.gyzbadmin.alertFailure('可用金额格式不对', undefined, function(){
								$('input[name="entryList[0].usableAmt"]').focus();});
							return false;
						}
						
						// 可用金额是否足够
						if (parseFloat($('input[name="entryList[0]._usableAmt"]').val()) + parseFloat(usableAmt) < 0) {
							$.gyzbadmin.alertFailure('账户可用金额不足', undefined, function(){
								$('input[name="entryList[0].usableAmt"]').focus();});
							return false;
	        			}
					}
					
					// 在途金额
					var onwayAmt = $('input[name="entryList[0].onwayAmt"]').val();
					if(kong.test(onwayAmt)) {
						$.gyzbadmin.alertFailure('在途金额不可为空', undefined, function(){
							$('input[name="entryList[0].onwayAmt"]').focus();});
						return false;
					} else {
						if(!isAmount(onwayAmt)) {
							$.gyzbadmin.alertFailure('在途金额格式不对', undefined, function(){
								$('input[name="entryList[0].onwayAmt"]').focus();});
							return false;
						}
						
						// 在途金额是否足够
						if (parseFloat($('input[name="entryList[0]._onwayAmt"]').val()) + parseFloat(onwayAmt) < 0) {
							$.gyzbadmin.alertFailure('账户在途金额不足', undefined, function(){
								$('input[name="entryList[0].onwayAmt"]').focus();});
							return false;
	        			}
					}
					
					// 可用金额与在途金额之和必须等于调账金额
					if (Math.abs(Number(amt) - (Number(usableAmt) + Number(onwayAmt))) > 0.001) {
						$.gyzbadmin.alertFailure('可用金额与在途金额之和必须等于调账金额', undefined, function(){
							$('input[name="entryList[0].amt"]').focus();});
						return false;
					}
				}
				if (acctType == 'SEVEN_CUST') {
					// 是否联调交广科技账户
					var onwayAmt = $('select[name="entryList[0].isAdjustJGKJ"]').val();
					if(kong.test(onwayAmt)) {
						$.gyzbadmin.alertFailure('是否联调交广科技账户不可为空', undefined, function(){
							$('select[name="entryList[0].isAdjustJGKJ"]').focus();});
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
		function queryActInfo(acctType_Name, acctNo_Name, acctName_Name, balance_Name, freezeAmt_Name, onwayAmt_Name, usableAmt_Name) {
			
			var acctType = $('select[name="' + acctType_Name + '"]').val();
			if(kong.test(acctType)) {
				/* $.gyzbadmin.alertFailure('账户类型不可为空', undefined, function(){
					$('select[name="' + acctType_Name + '"]').focus();}); */
				return false;
			}
			
			var acctNo = $('input[name="' + acctNo_Name + '"]').val();
			if (kong.test(acctNo)) {
				$.gyzbadmin.alertFailure('请输入账号！', undefined, function(){
        			$('input[name="' + acctNo_Name + '"]').focus();});
				return false;
			}
			
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=AdjustPath.BASE + AdjustPath.QUERY_ACCT_INFO%>',
		        data:{"acctId":acctNo, "acctType":acctType},
		        success:function(data){
		        	if(data.result == "FAILURE"){
		        		$('input[name="' + acctName_Name + '"]').val("");
		        		
		        		$('input[name="' + balance_Name + '"]').val("");
	        			$('input[name="' + freezeAmt_Name + '"]').val("");
	        			$('input[name="' + onwayAmt_Name + '"]').val("");
	        			$('input[name="' + usableAmt_Name + '"]').val("");
		        	}else if(data.result == "SUCCESS"){
		        		if (undefined == data.acctSeven) {
		        			$('input[name="' + acctName_Name + '"]').val("");
		        			
		        			$('input[name="' + balance_Name + '"]').val("");
		        			$('input[name="' + freezeAmt_Name + '"]').val("");
		        			$('input[name="' + onwayAmt_Name + '"]').val("");
		        			$('input[name="' + usableAmt_Name + '"]').val("");
		        		} else {
		        			$('input[name="' + acctName_Name + '"]').val(data.acctSeven.acctName);
		        			
		        			$('input[name="' + balance_Name + '"]').val(data.acctSeven.balance);
		        			$('input[name="' + freezeAmt_Name + '"]').val(data.acctSeven.freezeAmt);
		        			$('input[name="' + onwayAmt_Name + '"]').val(data.acctSeven.onwayAmt);
		        			$('input[name="' + usableAmt_Name + '"]').val(data.acctSeven.usableAmt);
		        		}
		        	}
		        	if (kong.test($('input[name="' + acctName_Name + '"]').val())) {
		        		$.gyzbadmin.alertFailure('账号不存在', undefined, function(){
		        			$('input[name="' + acctNo_Name + '"]').focus();});
		        	}
				}
			});
			
			return false;
		}
		
		// 账户类型onchange事件
		function acctTypeChange(i) {
			var acctType = $('select[name="entryList[' + i + '].acctType"]').val();
			if (acctType == 'SEVEN_CUST' || acctType == 'SEVEN_BUSS') {
				$('.SEVEN_ACCT' + i).show();
			} else {
				$('.SEVEN_ACCT' + i).hide();
				$('input[name="entryList[' + i + '].usableAmt"]').val("");
				$('input[name="entryList[' + i + '].onwayAmt"]').val("");
			}
			
			if (acctType == 'SEVEN_CUST') {
				$('.YINGTONG' + i).show();
			} else {
				$('.YINGTONG' + i).hide();
				$('select[name="entryList[' + i + '].isAdjustJGKJ"]').val("");
			}
			
			var acctNo = $('input[name="entryList[0].acctNo"]').val();
			if (!kong.test(acctNo)) {
				queryActInfo('entryList[0].acctType', 'entryList[0].acctNo', 'entryList[0].acctName', 'entryList[0]._balance', 'entryList[0]._freezeAmt', 'entryList[0]._onwayAmt', 'entryList[0]._usableAmt')
			}
		}
	</script>
</body>
</html>