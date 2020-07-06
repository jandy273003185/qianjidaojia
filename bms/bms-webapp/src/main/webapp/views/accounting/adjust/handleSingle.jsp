<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="com.qifenqian.bms.accounting.adjust.controller.AdjustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>调账经办(单边账)</title>
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
							<form action='<c:url value="<%=AdjustPath.BASE + AdjustPath.HANDLE_SINGLE_SUBMIT %>"/>' method="post">
							<input type="hidden" name="SUBMIT_TOKEN" value="${SUBMIT_TOKEN }"/>
							<input type="hidden" name="mainInfo.singleFlag" id="singleFlag" value="1" />
							<input type="hidden" name="entryListCount" id="entryListCount" value="1" /> <!-- 记录分录条数 -->
							<table class="search-table">
								<tr>
									<td class="td-left" width="30%">账户类型</td>
									<td class="td-right" width="70%">
										<select name="entryList[0].acctType" id="acctType" onchange="acctTypeChange('0')">
											<option value=""></option>
											<option value="SEVEN_CUST">七分钱客户账户</option>
											<option value="SEVEN_BUSS">七分钱商户账户</option>
											<option value="SEVEN_INNER">七分钱内部账户</option>
											<option value="JGKJ_CARD">交广科技卡</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-left">账号</td>
									<td class="td-right">
										<input type="text" name="entryList[0].acctNo" id="acctNo" style="width: 250px;" onchange="queryActInfo('entryList[0].acctType', 'entryList[0].acctNo', 'entryList[0].acctName', 'entryList[0]._balance', 'entryList[0]._freezeAmt', 'entryList[0]._onwayAmt', 'entryList[0]._usableAmt')">
										<input type="text" name="entryList[0].acctName" id="acctName" style="width: 400px;"> <!-- readonly="readonly" style="background-color: #EEEEEE;" -->
										<input type="hidden" name="entryList[0]._balance" id="_balance"/>
										<input type="hidden" name="entryList[0]._freezeAmt" id="_freezeAmt"/>
										<input type="hidden" name="entryList[0]._onwayAmt" id="_onwayAmt"/>
										<input type="hidden" name="entryList[0]._usableAmt" id="_usableAmt"/>
										<!-- <button type="button" class="btn btn-purple btn-sm btn-margin" onclick="queryActInfo('entryList[0].acctType', 'entryList[0].acctNo', 'entryList[0].acctName')">查询</button> -->
									</td>
								</tr>
								<tr>
									<td class="td-left">调账金额</td>
									<td class="td-right">
										<input type="hidden" name="entryList[0].curcde" value="CNY">
										<input type="text" name="entryList[0].amt" style="width: 250px; text-align: right;"> <span>CNY</span>
									</td>
								</tr>
								<tr class="SEVEN_ACCT0" style="display: none;">
									<td class="td-left">可用金额</td>
									<td class="td-right">
										<input type="text" name="entryList[0].usableAmt" style="width: 250px; text-align: right;"> <span>CNY</span>
									</td>
								</tr>
								<tr class="SEVEN_ACCT0" style="display: none;">
									<td class="td-left">在途金额</td>
									<td class="td-right">
										<input type="text" name="entryList[0].onwayAmt" style="width: 250px; text-align: right;"> <span>CNY</span>
									</td>
								</tr>
								<tr class="YINGTONG0" style="display: none;">
									<td class="td-left">是否联调交广账户</td>
									<td class="td-right">
										<select name="entryList[0].isAdjustJGKJ">
											<option value=""></option>
											<option value="N">否</option>
											<option value="Y">是</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-left">关联交易业务编号</td>
									<td class="td-right" colspan="1">
										<div>
											<textarea rows="3" cols="80" name="mainInfo.relationOpId" ></textarea>
										</div>
										<div>
											<span style="color: red;">注: 多个业务编号以英文','分隔,只包含英文字母,-_@等字符</span>
										</div>
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
				
				<%-- $.post(window.Constants.ContextPath + '<%=AdjustPath.BASE + AdjustPath.HANDLE_SUBMIT %>', {
						'entryList[0].debitAcctNo' 	:  $('input[name="entryList[0].debitAcctNo"]').val(),
						'entryList[0].debitAcctName'	:  $('input[name="entryList[0].debitAcctName"]').val(),
						'entryList[0].creditAcctNo'	:  $('input[name="entryList[0].creditAcctNo"]').val(),
						'entryList[0].creditAcctName'	: $('input[name="entryList[0].creditAcctName"]').val(),
						'entryList[0].curcde'	: $('input[name="entryList[0].curcde"]').val(),
						'entryList[0].amt'	:  $('input[name="entryList[0].amt"]').val(),
						'mainInfo.relationOpId'	:  $('input[name="mainInfo.relationOpId"]').val(),
						'mainInfo.memo'	: $('textare[name="mainInfo.memo"]').val(),
						'SUBMIT_TOKEN' : $('input[name="SUBMIT_TOKEN"]').val()
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$.gyzbadmin.alertSuccess('保存成功', null, function(){
								//window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('保存失败:' + data.message);
						}
					}, 'json'
				); --%>
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