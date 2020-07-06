<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.settle.MerchantSettlePath"%>
<%@page import="com.qifenqian.bms.merchant.settle.type.MerchantSettleStatus"%>

<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/selectAll.js"/>'></script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户结算</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr th{word-wrap:break-word;white-space:nowrap;} 
 table tr td{word-wrap:break-word;white-space:nowrap;} 
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<script type="text/javascript">

	function load(){
		var isZero = $("#settleAmtIsZeroHidden").val();
		$('.search-table #settleAmtIsZero').val(isZero);
		
		var isT0 = $("#isT_OHidden").val();
		$('.search-table #isT_O').val(isT0);
	}
	$(function(){
		$(".exportBut").click(function(){
			var merchantCode = $('.search-table #merchantCode').val();
			var workDateMin = $('.search-table #workDateMin').val();
			var workDateMax = $('.search-table #workDateMax').val();
			var status = $('.search-table #status').val();
			var settleAmtIsZero = $('.search-table #settleAmtIsZero').val();
			var applyCoreId =  $('.search-table #applyCoreId').val();
			var clearId = $('.search-table #clearId').val();
			var protocolId = $('.search-table #protocolId').val();
			var batNo = $('.search-table #batNo').val();
			var isT_O = $('.search-table #isT_O').val();
		//	var merchantType = $('.search-table #merchantType').val();
			var id = $('.search-table #id').val();
			var src ="<%= request.getContextPath()+ MerchantSettlePath.BASE+MerchantSettlePath.EXPORT%>?"+
				"&merchantCode="+merchantCode+
				"&status="+status+
				"&workDateMin="+workDateMin+
				"&workDateMax="+workDateMax+
				"&settleAmtIsZero="+settleAmtIsZero+
				"&applyCoreId="+applyCoreId+
				"&clearId="+clearId+
				"&protocolId="+protocolId+
				"&id="+id+
				"&batNo="+batNo+
				"&isT_O="+isT_O;
		//		"&merchantType="+merchantType;
			
			$(".exportBut").attr("href",src);
		});
		
		  $.fn.check({ checkall_name: "batchSettleDeposit", checkbox_name: "settleDeposit" });
		
		  
		  $(".batchSettleList").click(function(){
			  if($("input[type='checkbox'][name='settleDeposit']").is(':checked')==false){
				  $.gyzbadmin.alertFailure("请选择商户");
				  return false;
			  }
			  
			  var settleObj = document.getElementsByName('settleDeposit');
			 
			  var settleObjString = '';
			  for(var i=0;i<settleObj.length;i++){
				 
				 if(settleObj[i].checked){
					 var statu = settleObj[i].value.split('#')[5];
					 if(statu == 'INIT'){
						 settleObjString += settleObj[i].value+'@';
					 }
					
				 }
			  }
				
			  if(settleObjString == ''){
				  $.gyzbadmin.alertFailure("选择列表无待确定商户！");
				  return false;
			  }
			  
			  else{
				 
				  $("#bathSettleModal #merchantSettleInfoS tr:gt(0)").remove();
				  $("#bathSettleModal #settleIds").remove();
				 
				 settleObjString = settleObjString.substring (0,settleObjString.length-1);
				 
				 var settleArray = settleObjString.split('@');
				 var str = '';
				 var settleIds = '';
				 for(var j= 0 ;j<settleArray.length;j++){
					 var custId  = settleArray[j].split('#')[0];
					 var merchantCode  = settleArray[j].split('#')[1];
					 var machantName  = settleArray[j].split('#')[2];
					 var settleAmt  = settleArray[j].split('#')[3];
					 var settleId = settleArray[j].split('#')[4];
					 str+="<tr><td>"+merchantCode+"</td><td>"+machantName+"</td><td>"+settleAmt+"</td></tr>"
					 settleIds += custId+'#'+settleId+'*';
				 }
				 settleIds = settleIds.substring(0,settleIds.length-1);
				 var ipt = "<input type='hidden' id='settleIds' value='"+settleIds+"'/>";
				 $("#bathSettleModal #merchantSettleInfoS").append(str).append(ipt);
			  }
			  
			//存token
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
				}, function(data) {
					var token = data.token ;
					$("#_token").val(token);
				}, 'json'
			);
		  });
		  
		  $(".batchSettle").click(function(){
			  var settleString = $("#bathSettleModal #settleIds").val();
			  $.blockUI();
			  $.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.BATCHSETTLE%>', {
					'settleIds' 	: settleString,
					'_TOKEN_ID':$("#_token").val()
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#bathSettleModal').modal('hide');
						
						var successMechant = data.settleSuccessIds.split('@');
						var failMechant = data.settleFailIds.split('@');
						
						var successTr = '';
						for(var i =0; i< successMechant.length;i++){
							if(successMechant[i] !=''){
								successTr += "<tr><td>"+successMechant[i]+"</td><td>成功</td></tr>"
							}
						}
						
						var failTr = '';
						for(var i =0; i< failMechant.length;i++){
							if(failMechant[i] != ''){
								failTr += "<tr><td>"+failMechant[i]+"</td><td style='color:red;'>失败</td></tr>"
							}
						}
						
						$("#bathSettleReturnModal").on('show.bs.modal', function () {
							$("#bathSettleReturnModal #merchantSettleInfoS").append(successTr).append(failTr);
						});
						$("#bathSettleReturnModal").modal('show');	
						
						$("#bathSettleReturnModal").on('hide.bs.modal', function () {
							window.location.reload();
						});
						
					} else {
						$('#bathSettleModal').modal('hide');
						$.gyzbadmin.alertFailure('结算异常:' + data.message);
					}
				}, 'json'
			);
		  });
		  
		  $(".batchVerified").click(function(){
			  var settleString = $("#batchVerifiedModal #settleIds").val();
			  $.blockUI();
			  $.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.BATCHVERIFIED%>', {
					'settleIds' 	: settleString,
					'_TOKEN_ID':	  $("#_token").val()
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#batchVerifiedModal').modal('hide');
						
						var successMechant = data.settleSuccessIds.split('@');
						var failMechant = data.settleFailIds.split('@');
						
						var successTr = '';
						
						for(var i =0; i< successMechant.length;i++){
							if(successMechant[i] !=''){
								successTr += "<tr><td>"+successMechant[i]+"</td><td>成功</td></tr>"
							}
							
						}
						
						var failTr = '';
						for(var i =0; i< failMechant.length;i++){
							if(failMechant[i] != ''){
								failTr += "<tr><td>"+failMechant[i]+"</td><td style='color:red;'>失败</td></tr>"
							}
							
						}
						
						$("#bathVerifiedReturnModal").on('show.bs.modal', function () {
							$("#bathVerifiedReturnModal #merchantSettleInfoS").append(successTr).append(failTr);
							
						});
						$("#bathVerifiedReturnModal").modal('show');	
						
						$("#bathVerifiedReturnModal").on('hide.bs.modal', function () {
							window.location.reload();
							
						});
						
					} else {
						$('#batchVerifiedModal').modal('hide');
						$.gyzbadmin.alertFailure('核销异常:' + data.message);
					}
				}, 'json'
			);
		  });
		  
		  $(".batchVerifiedList").click(function(){
			  if($("input[type='checkbox'][name='settleDeposit']").is(':checked')==false){
				  $.gyzbadmin.alertFailure("请选择商户");
				  return false;
			  }
			  var settleObj = document.getElementsByName('settleDeposit');
				 
			  var settleObjString = '';
			  for(var i=0;i<settleObj.length;i++){
				 
				 if(settleObj[i].checked){
					 var statu = settleObj[i].value.split('#')[5];
					 if(statu == 'PAY_SUCC'){
						 settleObjString += settleObj[i].value+'@';
					 }
					
				 }
			  }
				
			  if(settleObjString == ''){
				  $.gyzbadmin.alertFailure("选择列表无待核销商户！");
				  return false;
			  }
			  
			  else{
				 
				  $("#batchVerifiedModal #merchantSettleInfoS tr:gt(0)").remove();
				  $("#batchVerifiedModal #settleIds").remove();
				 
				 settleObjString = settleObjString.substring (0,settleObjString.length-1);
				 
				 var settleArray = settleObjString.split('@');
				 var str = '';
				 var settleIds = '';
				 for(var j= 0 ;j<settleArray.length;j++){
					 var custId  = settleArray[j].split('#')[0];
					 var merchantCode  = settleArray[j].split('#')[1];
					 var machantName  = settleArray[j].split('#')[2];
					 var settleAmt  = settleArray[j].split('#')[3];
					 var settleId = settleArray[j].split('#')[4];
					 str+="<tr><td>"+merchantCode+"</td><td>"+machantName+"</td><td>"+settleAmt+"</td></tr>"
					 settleIds += custId+'#'+settleId+'*';
				 }
				 settleIds = settleIds.substring(0,settleIds.length-1);
				 var ipt = "<input type='hidden' id='settleIds' value='"+settleIds+"'/>";
				 $("#batchVerifiedModal #merchantSettleInfoS").append(str).append(ipt);
			  }
			  
			//存token
			$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
			}, function(data) {
				var token = data.token ;
				$("#_token").val(token);
			}, 'json'
			);
		  });
	});
	
	
</script>
<body >
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
							<input type="hidden" id="settleAmtIsZeroHidden" value="${selectBean.settleAmtIsZero }"> 
							<input type="hidden" id="isT_OHidden" value="${selectBean.isT_O }"> 
							<form action='<c:url value="<%=MerchantSettlePath.BASE + MerchantSettlePath.LIST %>"/>' method="post" id="searchForm">
							<input type="hidden" id="isFirst" name="isFirst" value="${isFirst}"/>
							<input type="hidden" id="_token" name="_token"/>
							<table class="search-table">
								<tr>
									<td class="td-left">编号</td>
									<td class="td-right">
									   <span class="input-icon">
										<input type="text" name="id"  id="id" value="${selectBean.id }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">协议编号</td>
									<td class="td-right">
									   <span class="input-icon">
										<input type="text" name="protocolId"  id="protocolId" value="${selectBean.protocolId }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">金蝶清算编号</td>
									<td class="td-right">
										<span class="input-icon">
										<input type="text" name="clearId"  id="clearId" value="${selectBean.clearId }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
									
								</tr>
								<tr>
								<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
											<select name="status" id="status">
												<option value="">--请选择--</option>
												<c:forEach items="<%=MerchantSettleStatus.values() %>" var="status">
													<option value="${status }" <c:if test="${status == selectBean.status }">selected</c:if>>--${status.desc }--</option>
												</c:forEach>
											</select>
										</span>
									</td>
									<td class="td-left">商户编号</td>
									<td class="td-right">
									   <span class="input-icon">
										<input type="text" name="merchantCode"  id="merchantCode" value="${selectBean.merchantCode }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">结算申请编号</td>
									<td class="td-right">
										<span class="input-icon">
										<input type="text" name="applyCoreId"  id="applyCoreId" value="${selectBean.applyCoreId }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									
								</tr>
								<tr>
									<!-- <td class="td-left">商户类型</td>
									<td class="td-right">
										<select name="merchantType" id="merchantType" >
											<option value="">--请选择--</option>
											<option value="normal">普通商户</option>
											<option value="agent">代理商</option>
										</select>
									</td> -->
									<td class="td-left">是否T+0</td>
									<td class="td-right">
										<select name="isT_O" id="isT_O" >
											<option value="">--请选择--</option>
											<option value="YES">是</option>
											<option value="NO">否</option>
										</select>
									</td>
									<td class="td-left">结算金额是否为0</td>
									<td class="td-right">
										<select name="settleAmtIsZero" id="settleAmtIsZero" >
											<option value="">--结算金额--</option>
											<option value="yes">是</option>
											<option value="no">否</option>
										</select>
									</td>
									<td class="td-left">执行日</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="workDateMin"  id="workDateMin" value="${selectBean.workDateMin }"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
											<input type="text" name="workDateMax"  id="workDateMax" value="${selectBean.workDateMax }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</span>
									</td>
									
								</tr>
								<tr>
									<td class="td-left">批次号</td>
									<td class="td-right">
										 <span class="input-icon">
										<input type="text" name="batNo"  id="batNo" value="${selectBean.batNo }">
										<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm searchBtn" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											&nbsp;
											<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.EXPORT%>">
												<span class="input-group-btn" style="display:inline;">
													<a class="btn btn-purple btn-sm exportBut">
														导出报表
													</a> 
												</span>
											</gyzbadmin:function>
											<span class="input-group-btn" style="display:inline;">
															<a class="btn btn-purple btn-sm batchSettleList"  href="#"  data-rel="tooltip"  data-toggle='modal' data-target="#bathSettleModal">
																批量结算
																<i class="icon-download-alt bigger-110"></i> 
															</a> 
											</span>		
											<span class="input-group-btn" style="display:inline;">
															<a class="btn btn-purple btn-sm batchVerifiedList"  href="#"  data-rel="tooltip"  data-toggle='modal' data-target="#batchVerifiedModal">
																批量核销
																<i class="icon-download-alt bigger-110"></i> 
															</a> 
											</span>	
											<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.TOGETHER%>">
												<span class="input-group-btn" style="display:inline;">
													<a class="btn btn-purple btn-sm togetherBtn"  href="#"  data-rel="tooltip"  data-toggle='modal' data-target="#togetherModal">
														联合
														<i class="icon-download-alt bigger-110"></i> 
													</a> 
												</span>
											</gyzbadmin:function>
											&nbsp;	
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">结算列表</div>

							<div class="table-responsive" style="overflow:scroll">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th><label ><input type="checkbox" name="batchSettleDeposit"  id="batchSettleDeposit" title="全选" /></label></th>
											<th >编号</th>
											<th>结算申请编号</th>
											<th>金蝶清算编号</th>
											<th>结算批次号</th>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>开始日期</th>
											<th>结束日期</th>
											<th>协议编号</th>
											<th>收款笔数</th>
											<th>收款总额</th>
											<th>收款总费用</th>
											<th>撤销笔数</th>
											<th>撤销总额</th>
											<th>撤销总费用</th>
											<th>退款笔数</th>
											<th>退款总额</th>
											<th>退款总费用</th>
											<th>提现笔数</th>
											<th>提现总额</th>
											<th>提现总费用</th>
											<th>转入笔数</th>
											<th>转入总额</th>
											<th>转入总费用</th>
											<th>转出笔数</th>
											<th>转出总额</th>
											<th>转出总费用</th>
											<th>应收总额</th>
											<th>应付总额</th>
											<th>结算金额</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${settleList }" var="settle">
										<tr class="settle"  >
											<td>
											<%-- <c:if test="${settle.settleAmt== '0.00' }">
												<input type="checkbox" name="settleNoDeposit"  id="settleNoDeposit" disabled="disabled" />
											</c:if>
											<c:if test="${settle.settleAmt != '0.00' }"> --%>
												<input type="checkbox" name="settleDeposit"  id="settleDeposit" value="${settle.custId}#${settle.merchantCode}#${settle.merchantName}#${settle.settleAmt}#${settle.id}#${settle.status}" />
											<%-- </c:if> --%>
											</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.id }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.applyCoreId }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.clearId }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.batNo }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.merchantCode }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.merchantName }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.settleBeginDate }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.settleEndDate }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.protocolId }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.receiveCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.receiveTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.receiveTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.revokeCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.revokeTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.revokeTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.refundCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.refundTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.refundTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.withdrawCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.withdrawTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.withdrawTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferInCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferInTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferInTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferOutCount }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferOutTotalAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.transferOutTotalFee }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.merchantReceivable }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.merchantPayable }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.settleAmt }</td>
											<td title="${settle.merchantName }" onmouseover="titleMouseOver(this,event);" onmouseout="titleMouseOut(this);">${settle.status.desc }</td>
											<td class="buttonTd">
												<input type="hidden" name="id" value="${settle.id }"/>
												<c:if test="${settle.status == 'INIT'}">
													<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.AUDIT%>">
														<button type="button" class="btn btn-primary btn-xs auditLink" title="Audit" data-toggle='modal' data-target="#auditModal">
															确认
														</button>
													</gyzbadmin:function>
													<c:if test="${settle.isTogether == 'Y'}">
													<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.SEPARATE%>">
														<button type="button" class="btn btn-primary btn-xs separateLink" title="Separate" data-toggle='modal' data-target="#separateModal">
															分离
														</button>
													</gyzbadmin:function>
													</c:if>
												</c:if>
												   <c:if test="${settle.status == 'AUDIT_PASS'||settle.status == 'PAY_FAIL'}">
													<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.CANCEL%>">
														<button type="button" class="btn btn-primary btn-xs cancelLink" title="Cancel" data-toggle='modal' data-target="#cancelModal">
															确认撤销
														</button>
													</gyzbadmin:function>
													</c:if>
													<c:if test="${settle.status == 'PAY_SUCC'||settle.status == 'VERIFIED_FAIL'}">
													<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.VERIFIED%>">
														<button type="button" class="btn btn-primary btn-xs verifiedLink" title="Verified" data-toggle='modal' data-target="#verifiedModal">
															核销
														</button>
													</gyzbadmin:function>
													</c:if>
													
												<gyzbadmin:function url="<%=MerchantSettlePath.BASE + MerchantSettlePath.DETAIL%>">
													<button type="button" class="btn btn-primary btn-xs detailLink" title="Detail" data-toggle='modal' data-target="#detailModal">
														明细
													</button>
												</gyzbadmin:function>
											</td>
										</tr>
										<c:forEach items="${settle.detailList }" var="detail">
											<tr class="detail">
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>${detail.settleBeginDate }</td>
											<td>${detail.settleEndDate }</td>
											<td>${detail.protocolId }</td>
											<td>${detail.receiveCount }</td>
											<td>${detail.receiveTotalAmt }</td>
											<td>${detail.receiveTotalFee }</td>
											<td>${detail.revokeCount }</td>
											<td>${detail.revokeTotalAmt }</td>
											<td>${detail.revokeTotalFee }</td>
											<td>${detail.refundCount }</td>
											<td>${detail.refundTotalAmt }</td>
											<td>${detail.refundTotalFee }</td>
											<td>${detail.withdrawCount }</td>
											<td>${detail.withdrawTotalAmt }</td>
											<td>${detail.withdrawTotalFee }</td>
											<td>${detail.transferInCount }</td>
											<td>${detail.transferInTotalAmt }</td>
											<td>${detail.transferInTotalFee }</td>
											<td>${detail.transferOutCount }</td>
											<td>${detail.transferOutTotalAmt }</td>
											<td>${detail.transferOutTotalFee }</td>
											<td>${detail.merchantReceivable }</td>
											<td>${detail.merchantPayable }</td>
											<td>${detail.settleAmt }</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										</c:forEach>
									</c:forEach>
									<c:if test="${empty settleList}">
										<tr><td colspan="26" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								
							</div>
							<c:if test="${not empty settleList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
							
						</div>
					</div>
				   
				   <!-- 批量结算反馈 -->
				   <div class="modal fade" id="bathSettleReturnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户批量结算确认</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <table class="list-table" id="merchantSettleInfoS">
					           <thead>
									<tr>
										<th>商户编号</th>
										<th>是否成功</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
				   
				   
				     <!-- 批量核销反馈 -->
				   <div class="modal fade" id="bathVerifiedReturnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户批量核销确认</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <table class="list-table" id="merchantSettleInfoS">
					           <thead>
									<tr>
										<th>商户编号</th>
										<th>是否成功</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
					
					<!-- 联合 -->
				   <div class="modal fade" id="togetherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户结算联合</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         		<input type="hidden" name="togetherIds" id="togetherIds"/>
				         	  <table class="list-table" id="merchantSettleList">
					           <thead>
									<tr>
										<th>商户编号</th>
										<th>商户名称</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th>结算金额</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary togetherModalBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
					
					<!-- 分离 -->
				   <div class="modal fade" id="separateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户结算分离</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <div class="modal-body" align="left" style="margin-left: 150px;">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">
					         		商户编号：<span class="custId"></span><br/>
					         		结算金额：<span class="settleAmt"></span><br/>
					         	</font>
					         </div>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="togetherId" id="togetherId"/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary separateModalBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
				   
				   <!-- 批量核销 -->
				   <div class="modal fade" id="batchVerifiedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户批量核销</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <table class="list-table" id="merchantSettleInfoS">
					           <thead>
									<tr>
										<th>商户编号</th>
										<th>商户名称</th>
										<th>结算金额</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" class="id" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary batchVerified">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
				   
					
					<!-- 批量结算 -->
				   <div class="modal fade" id="bathSettleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户批量结算</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <table class="list-table" id="merchantSettleInfoS">
					           <thead>
									<tr>
										<th>商户编号</th>
										<th>商户名称</th>
										<th>结算金额</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" class="id" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary batchSettle">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
					
					<!-- 明细模态框（Modal） -->
					<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 800px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">明细</h4>
					         </div>
					         <div class="modal-body">
					            <table class="list-table">
					            	<tr>
					            		<td class="td-left" width="10%">结算编号</td><td class="td-right id" width="90%" colspan="5"></td>
									</tr>
									<tr>
										<td class="td-left" width="10%">商户编号</td><td class="td-right merchantCode" width="40%" colspan="2"></td>
										
					            		<td class="td-left">协议编号</td><td class="td-right protocolId" colspan="2"></td>
									</tr>
									<tr>
										<td class="td-left" width="10%">商户名称</td><td class="td-right merchantName" width="40%" colspan="2"></td>
										<td class="td-left" width="10%">执行日期</td><td class="td-right workDate" width="23%" colspan="2"></td>
									</tr>
									<tr>
										<td class="td-left" width="10%">开始日期</td><td class="td-right settleBeginDate" width="23%" colspan="2"></td>
										<td class="td-left" width="10%">结束日期</td><td class="td-right settleEndDate" width="24%" colspan="2"></td>
									</tr>
									<tr>
					            		<td class="td-left">银行卡号</td><td class="td-right bankCardNo" colspan="2"></td>
					            		<td class="td-left">户名</td><td class="td-right bankCardName" colspan="2"></td>
									</tr>
									<tr>
										<td class="td-left">开户行</td><td class="td-right bankName" colspan="5"></td>
									</tr>
									<tr>
										<td class="td-left">收款笔数</td><td class="td-right receiveCount"></td>
					            		<td class="td-left">收款总额</td><td class="td-right receiveTotalAmt"></td>
										<td class="td-left">收款费用</td><td class="td-right receiveTotalFee"></td>
									</tr>
									<tr>
					            		<td class="td-left">撤销笔数</td><td class="td-right revokeCount"></td>
										<td class="td-left">撤销总额</td><td class="td-right revokeTotalAmt"></td>
					            		<td class="td-left">撤销费用</td><td class="td-right revokeTotalFee"></td>
									</tr>
									<tr>
										<td class="td-left">退款笔数</td><td class="td-right refundCount"></td>
					            		<td class="td-left">退款总额</td><td class="td-right refundTotalAmt"></td>
										<td class="td-left">退款费用</td><td class="td-right refundTotalFee"></td>
									</tr>
									<tr>
					            		<td class="td-left">提现笔数</td><td class="td-right withdrawCount"></td>
										<td class="td-left">提现总额</td><td class="td-right withdrawTotalAmt"></td>
					            		<td class="td-left">提现费用</td><td class="td-right withdrawTotalFee"></td>
									</tr>
									<tr>
					            		<td class="td-left">转入笔数</td><td class="td-right transferInCount"></td>
										<td class="td-left">转入总额</td><td class="td-right transferInTotalAmt"></td>
					            		<td class="td-left">转入费用</td><td class="td-right transferInTotalFee"></td>
									</tr>
									<tr>
					            		<td class="td-left">转出笔数</td><td class="td-right transferOutCount"></td>
										<td class="td-left">转出总额</td><td class="td-right transferOutTotalAmt"></td>
					            		<td class="td-left">转出费用</td><td class="td-right transferOutTotalFee"></td>
									</tr>
									<tr>
										<td class="td-left">应收总额</td><td class="td-right merchantReceivable"></td>
					            		<td class="td-left">应付总额</td><td class="td-right merchantPayable"></td>
										<td class="td-left">结算金额</td><td class="td-right settleAmt"></td>
									</tr>
									<tr>
					            		<td class="td-left">完成日期</td><td class="td-right finishDate"></td>
										<td class="td-left">状态</td><td class="td-right status" colspan="3"></td>
									</tr>
									<tr>
					            		<td class="td-left">备注</td><td class="td-right memo" colspan="5"></td>
									</tr>
									<tr>
					            		<td class="td-left">生成人</td><td class="td-right instUser"></td>
										<td class="td-left">生成日期</td><td class="td-right instDate"></td>
					            		<td class="td-left">生成时间</td><td class="td-right instDatetime"></td>
									</tr>
									<tr>
										<td class="td-left">确认人</td><td class="td-right auditUser"></td>
					            		<td class="td-left">确认时间</td><td class="td-right auditDatetime"></td>
					            		<td class="td-left">确认编号</td><td class="td-right applyCoreId"></td>
									</tr>
									<tr>
										<td class="td-left">核销人</td><td class="td-right verifiedUser"></td>
					            		<td class="td-left">核销时间</td><td class="td-right verifiedDatetime"></td>
					            		<td class="td-left">核销编号</td><td class="td-right settleCoreId"></td>
									</tr>
								</table>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
				</div><!-- /.page-content -->
				<!-- 确认模态框（Modal） -->
				<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户结算确认</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		商户编号：<span class="custId"></span><br/>
				         		结算金额：<span class="settleAmt"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" class="id" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary auditBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				
				<!-- 确认撤销模态框（Modal） -->
				<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户结算确认撤销</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		商户编号：<span class="custId"></span><br/>
				         		结算金额：<span class="settleAmt"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" class="id" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary cancelBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				
				<!-- 核销模态框（Modal） -->
				<div class="modal fade" id="verifiedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">商户结算核销</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		商户编号：<span class="custId"></span><br/>
				         		结算金额：<span class="settleAmt"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" class="id" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary verifiedBtn">核销</button>
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
	</div>

	<script type="text/javascript">
		jQuery(function($){
			// 为没个tr缓存数据
			var settleListJson = '<c:out value="${gyzb:toJSONString(settleList)}" escapeXml="false"/>';
			var settleTrList = $('tr.settle');
			$.each($.parseJSON(settleListJson), function(idx, obj){
				$.data(settleTrList[idx], 'settle', obj);
			});

			$('.clearBtn').click(function(){
				$(':input','#searchForm')  
				 .not(':button, :submit, :reset, :hidden')  
				 .val('')  
				 .removeAttr('checked')  
				 .removeAttr('selected'); 
			});
			
			
			$('.searchBtn').click(function(){
				var startDate = $("#workDateMin").val();
				var endDate= $("#workDateMax").val();
				if("" != startDate && "" != endDate && startDate > endDate) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			});
			$('.detailLink').click(function(){
				var settle = $.data($(this).parent().parent()[0], 'settle');
				$('#detailModal').on('show.bs.modal', function () {
					// 赋值
					$('#detailModal .id').html(settle.id);
					$('#detailModal .merchantCode').html(settle.merchantCode);
					$('#detailModal .workDate').html(settle.workDate);
					$('#detailModal .settleBeginDate').html(settle.settleBeginDate);
					$('#detailModal .settleEndDate').html(settle.settleEndDate);
					$('#detailModal .protocolId').html(settle.protocolId);
					$('#detailModal .receiveCount').html(settle.receiveCount);
					$('#detailModal .receiveTotalAmt').html(settle.receiveTotalAmt);
					$('#detailModal .receiveTotalFee').html(settle.receiveTotalFee);
					$('#detailModal .revokeCount').html(settle.revokeCount);
					$('#detailModal .revokeTotalAmt').html(settle.revokeTotalAmt);
					$('#detailModal .revokeTotalFee').html(settle.revokeTotalFee);
					$('#detailModal .refundCount').html(settle.refundCount);
					$('#detailModal .refundTotalAmt').html(settle.refundTotalAmt);
					$('#detailModal .refundTotalFee').html(settle.refundTotalFee);
					$('#detailModal .withdrawCount').html(settle.withdrawCount);
					$('#detailModal .withdrawTotalAmt').html(settle.withdrawTotalAmt);
					$('#detailModal .withdrawTotalFee').html(settle.withdrawTotalFee);
					$('#detailModal .transferInCount').html(settle.transferInCount);
					$('#detailModal .transferInTotalAmt').html(settle.transferInTotalAmt);
					$('#detailModal .transferInTotalFee').html(settle.transferInTotalFee);
					$('#detailModal .transferOutCount').html(settle.transferOutCount);
					$('#detailModal .transferOutTotalAmt').html(settle.transferOutTotalAmt);
					$('#detailModal .transferOutTotalFee').html(settle.transferOutTotalFee);
					$('#detailModal .merchantReceivable').html(settle.merchantReceivable);
					$('#detailModal .merchantPayable').html(settle.merchantPayable);
					$('#detailModal .settleAmt').html(settle.settleAmt);
					$('#detailModal .finishDate').html(settle.finishDate);
					$('#detailModal .status').html(settle.statusStr);
					$('#detailModal .memo').html(settle.memo);
					$('#detailModal .instUser').html(settle.instUserName);
					$('#detailModal .instDate').html(settle.instDate);
					$('#detailModal .instDatetime').html(settle.instDatetimeStr);
					$('#detailModal .auditUser').html(settle.auditUserName);
					$('#detailModal .auditDatetime').html(settle.auditDatetimeStr);
					$('#detailModal .verifiedUser').html(settle.verifiedUserName);
					$('#detailModal .verifiedDatetime').html(settle.verifiedDatetimeStr);
					$('#detailModal .merchantName').html(settle.merchantName);
					$('#detailModal .bankCardNo').html(settle.bankCardNo);
					$('#detailModal .bankCardName').html(settle.bankCardName);
					$('#detailModal .bankName').html(settle.bankName);
					$('#detailModal .applyCoreId').html(settle.applyCoreId);
					$('#detailModal .settleCoreId').html(settle.settleCoreId);
				});
				$('#detailModal').on('hide.bs.modal', function () {
					// 清除
					$('#detailModal .id').html('');
					$('#detailModal .merchantCode').html('');
					$('#detailModal .workDate').html('');
					$('#detailModal .settleBeginDate').html('');
					$('#detailModal .settleEndDate').html('');
					$('#detailModal .protocolId').html('');
					$('#detailModal .receiveCount').html('');
					$('#detailModal .receiveTotalAmt').html('');
					$('#detailModal .receiveTotalFee').html('');
					$('#detailModal .revokeCount').html('');
					$('#detailModal .revokeTotalAmt').html('');
					$('#detailModal .revokeTotalFee').html('');
					$('#detailModal .refundCount').html('');
					$('#detailModal .refundTotalAmt').html('');
					$('#detailModal .refundTotalFee').html('');
					$('#detailModal .withdrawCount').html('');
					$('#detailModal .withdrawTotalAmt').html('');
					$('#detailModal .withdrawTotalFee').html('');
					$('#detailModal .transferInCount').html('');
					$('#detailModal .transferInTotalAmt').html('');
					$('#detailModal .transferInTotalFee').html('');
					$('#detailModal .transferOutCount').html('');
					$('#detailModal .transferOutTotalAmt').html('');
					$('#detailModal .transferOutTotalFee').html('');
					$('#detailModal .merchantReceivable').html('');
					$('#detailModal .merchantPayable').html('');
					$('#detailModal .settleAmt').html('');
					$('#detailModal .finishDate').html('');
					$('#detailModal .status').html('');
					$('#detailModal .memo').html('');
					$('#detailModal .instUser').html('');
					$('#detailModal .instDate').html('');
					$('#detailModal .instDatetime').html('');
					$('#detailModal .auditUser').html('');
					$('#detailModal .auditDatetime').html('');
					$('#detailModal .verifiedUser').html('');
					$('#detailModal .verifiedDatetime').html('');
					$('#detailModal .merchantName').html('');
					$('#detailModal .bankCardNo').html('');
					$('#detailModal .bankCardName').html('');
					$('#detailModal .bankName').html('');
					$('#detailModal .applyCoreId').html('');
					$('#detailModal .settleCoreId').html('');
				});
			});
			
			// 弹出层准备工作
			$('.auditLink').click(function(){
				var settle = $.data($(this).parent().parent()[0], 'settle');
				var id = $(this).parent().find('input[name="id"]').val();
				$('#auditModal').on('show.bs.modal', function () {
					// 赋值
					$('#auditModal span.custId').html(settle.custId);
					$('#auditModal span.settleAmt').html(settle.settleAmt);
					$('#auditModal input.id').val(id);
				});
				$('#auditModal').on('hide.bs.modal', function () {
					// 清除
					$('#auditModal span.custId').empty();
					$('#auditModal span.settleAmt').empty();
					$('#auditModal input.id').val('');
				});
				
				//存token
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
				}, function(data) {
					var token = data.token ;
					$("#_token").val(token);
				}, 'json'
			);
				
			});
	
			// 确认
			$('.auditBtn').click(function(){
				var id = $('#auditModal input.id').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.AUDIT %>', {
						'id' 	: id,
						'_TOKEN_ID':$("#_token").val()
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#auditModal').modal('hide');
							$.gyzbadmin.alertSuccess('确认成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('确认失败:' + data.message);
						}
					}, 'json'
				);
			});

			// 弹出层准备工作
			$('.cancelLink').click(function(){
				var settle = $.data($(this).parent().parent()[0], 'settle');
				var id = $(this).parent().find('input[name="id"]').val();
				$('#cancelModal').on('show.bs.modal', function () {
					// 赋值
					$('#cancelModal span.custId').html(settle.custId);
					$('#cancelModal span.settleAmt').html(settle.settleAmt);
					$('#cancelModal input.id').val(id);
				});
				$('#cancelModal').on('hide.bs.modal', function () {
					// 清除
					$('#cancelModal span.custId').empty();
					$('#cancelModal span.settleAmt').empty();
					$('#cancelModal input.id').val('');
				});
			});
	
			// 确认
			$('.cancelBtn').click(function(){
				var id = $('#cancelModal input.id').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.CANCEL %>', {
						'id' 	: id
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#cancelModal').modal('hide');
							$.gyzbadmin.alertSuccess('撤销成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('撤销失败:' + data.message);
						}
					}, 'json'
				);
			});

			// 弹出层准备工作
			$('.verifiedLink').click(function(){
				var settle = $.data($(this).parent().parent()[0], 'settle');
				var id = $(this).parent().find('input[name="id"]').val();
				$('#verifiedModal').on('show.bs.modal', function () {
					// 赋值
					$('#verifiedModal span.custId').html(settle.custId);
					$('#verifiedModal span.settleAmt').html(settle.settleAmt);
					$('#verifiedModal input.id').val(id);
				});
				$('#verifiedModal').on('hide.bs.modal', function () {
					// 清除
					$('#verifiedModal span.custId').empty();
					$('#verifiedModal span.settleAmt').empty();
					$('#verifiedModal input.id').val('');
				});
				
				//存token
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
				}, function(data) {
					var token = data.token ;
					$("#_token").val(token);
				}, 'json'
			);
			});

			// 核销
			$('.verifiedBtn').click(function(){
				var id = $('#verifiedModal input.id').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.VERIFIED %>', {
						'id' 	: id,
						'_TOKEN_ID':$("#_token").val()
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#verifiedModal').modal('hide');
							$.gyzbadmin.alertSuccess('核销成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('核销失败:' + data.message);
						}
					}, 'json'
				);
			});

			// 联合弹出层准备工作
			// 展示 
			$('#togetherModal').on('show.bs.modal', function () {
				if($("input[type='checkbox'][name='settleDeposit']").is(':checked')==false){
					$.gyzbadmin.alertFailure("请选择结算记录");
				  	return false;
			  	}
				var settleObj = $('[name="settleDeposit"]:checked');
				if(settleObj.length < 2) {
					$.gyzbadmin.alertFailure("必须勾选多个记录");
					return false;
				}
			  	var settleStringHtml = '';
			  	var settleAmtSum = 0;
			  	var settleMerchantId = '';
			  	var togetherIds = '';
				for(var i=0;i<settleObj.length;i++){
					var settle = $.data($(settleObj[i]).parent().parent()[0], 'settle');
					 if(settle.status != 'INIT'){
						 $.gyzbadmin.alertFailure('只能勾选待确认的记录');
						 return false;
					 }
					 // 同一商户
					 if(i==0) {
						 settleMerchantId = settle.custId;
					 } else {
						 if(settleMerchantId != settle.custId) {
							 $.gyzbadmin.alertFailure('只能勾选同一商户的记录');
							 return false;
						 }
					 }
					 settleAmtSum += settle.settleAmt;
					 togetherIds += settle.id + "#";
					 settleStringHtml += '<tr><td>'+settle.merchantCode+'</td><td>'
					 				+(null == settle.merchantName ? '' : settle.merchantName)+'</td><td>'
					 				+settle.settleBeginDate+'</td><td>'
					 				+settle.settleEndDate+'</td><td>'
					 				+settle.settleAmt+'</td></tr>';
				  }
				 // 结算金额大于等于0
				  if(settleAmtSum < 0){
					  $.gyzbadmin.alertFailure("合计结算金额小于0！");
					  return false;
				  }
				  togetherIds = togetherIds.substring(0,togetherIds.length-1);
				// 赋值
				$("#togetherModal #merchantSettleList").append(settleStringHtml);
				$("#togetherModal #togetherIds").val(togetherIds);
			});
			// 隐藏
			$('#togetherModal').on('hide.bs.modal', function () {
				// 清除
				 $("#togetherModal #merchantSettleList tr:gt(0)").remove();
				 $("#togetherModal #togetherIds").val('');
			});
			// 联合
			$('.togetherModalBtn').click(function(){
				var togetherIds = $('#togetherModal #togetherIds').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.TOGETHER %>', {
						'togetherIds' 	: togetherIds
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#togetherModal').modal('hide');
							$.gyzbadmin.alertSuccess('联合成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('联合失败:' + data.message);
						}
					}, 'json'
				);
			});

			// 分离弹出层准备工作
			$('.separateLink').click(function(){
				var settle = $.data($(this).parent().parent()[0], 'settle');
				var togetherId = $(this).parent().find('input[name="id"]').val();
				$('#separateModal').on('show.bs.modal', function () {
					// 赋值
					$('#separateModal span.custId').html(settle.merchantCode);
					$('#separateModal span.settleAmt').html(settle.settleAmt);
					$('#separateModal #togetherId').val(togetherId);
				});
				$('#separateModal').on('hide.bs.modal', function () {
					// 清除
					$('#separateModal span.custId').empty();
					$('#separateModal span.settleAmt').empty();
					$('#separateModal #togetherId').val('');
				});
			});

			// 分离
			$('.separateModalBtn').click(function(){
				var togetherId = $('#separateModal #togetherId').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SEPARATE %>', {
						'togetherId' 	: togetherId
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#separateModal').modal('hide');
							$.gyzbadmin.alertSuccess('分离成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('分离失败:' + data.message);
						}
					}, 'json'
				);
			});
		});
		
		
		/**
		 * className 类名
		 * tagname HTML标签名，如div,td,ul等
		 * @return Array 所有class对应标签对象组成的数组
		 * @example
		 <div class="abc">abc</div>
		 var abc = getClass('abc');
		 for(i=0;i<abc.length;i++){
		     abc[i].style.backgroundColor='red';
		 }
		*/
		function getClass(className,tagname) {
		    //tagname默认值为'*'，不能直接写成默认参数方式getClass(className,tagname='*')，否则IE下报错
		    if(typeof tagname == 'undefined') tagname = '*';
		    if(typeof(getElementsByClassName) == 'function') {
		        return getElementsByClassName(className);
		    }else {
		        var tagname = document.getElementsByTagName(tagname);
		        var tagnameAll = [];
		        for(var i = 0; i < tagname.length; i++) {
		            if(tagname[i].className == className) {
		                tagnameAll[tagnameAll.length] = tagname[i];
		            }
		        }
		        return tagnameAll;
		    }
		}

		/**
		 * JS字符切割函数
		 * @params     string                原字符串
		 * @params    words_per_line        每行显示的字符数
		 */
		function split_str(string,words_per_line) {
		    //空串，直接返回
		    if(typeof string == 'undefined' || string.length == 0) return '';
		    //单行字数未设定，非数值，则取默认值50
		    if(typeof words_per_line == 'undefined' || isNaN(words_per_line)){
		        words_per_line = 50;
		    }
		    //格式化成整形值
		    words_per_line = parseInt(words_per_line);
		    //取出i=0时的字，避免for循环里换行时多次判断i是否为0
		    var output_string = string.substring(0,1);
		    //循环分隔字符串
		    for(var i=1;i<string.length;i++) {
		        //如果当前字符是每行显示的字符数的倍数，输出换行
		        if(i%words_per_line == 0) {
		            output_string += "<br/>";
		        }
		        //每次拼入一个字符
		        output_string += string.substring(i,i+1);
		    }
		    return output_string;
		}

		/**
		 * 鼠标悬停显示TITLE
		 * @params     obj        当前悬停的标签
		 *
		 */
		function titleMouseOver(obj,event,words_per_line) {
		    //无TITLE悬停，直接返回
		    if(typeof obj.title == 'undefined' || obj.title == '') return false;
		    //不存在title_show标签则自动新建
		    var title_show = document.getElementById("title_show");
		    if(title_show == null){
		        title_show = document.createElement("div");                            //新建Element
		        document.getElementsByTagName('body')[0].appendChild(title_show);    //加入body中
		        var attr_id = document.createAttribute('id');                        //新建Element的id属性
		        attr_id.nodeValue = 'title_show';                                    //为id属性赋值
		        title_show.setAttributeNode(attr_id);                                //为Element设置id属性
		        
		        var attr_style = document.createAttribute('style');                    //新建Element的style属性
		        attr_style.nodeValue = 'position:absolute;'                            //绝对定位
		            +'border:solid 1px #999999; background:#EDEEF0;'                //边框、背景颜色
		            +'border-radius:2px;box-shadow:2px 3px #999999;'                //圆角、阴影
		            +'line-height:18px;'                                            //行间距
		            +'font-size:12px; padding: 2px 5px;';                            //字体大小、内间距
		        try{
		            title_show.setAttributeNode(attr_style);                        //为Element设置style属性
		            
		           // obj.style.background = "red";
		            
		        }catch(e){
		            //IE6
		            title_show.style.position = 'absolute';
		            title_show.style.border = 'solid 1px #999999';
		            title_show.style.background = '#EDEEF0';
		            title_show.style.lineHeight = '18px';
		            title_show.style.fontSize = '18px';
		            title_show.style.padding = '2px 5px';
		           // obj.style.background = "red";
		        }
		    }
		    //存储并删除原TITLE
		    document.title_value = obj.title;
		   
		    obj.title = '';
		    //单行字数未设定，非数值，则取默认值50
		    if(typeof words_per_line == 'undefined' || isNaN(words_per_line)){
		        words_per_line = 50;
		    }
		    //格式化成整形值
		    words_per_line = parseInt(words_per_line);
		    //在title_show中按每行限定字数显示标题内容，模拟TITLE悬停效果
		    title_show.innerHTML = split_str(document.title_value,words_per_line);
		    //显示悬停效果DIV
		    title_show.style.display = 'block';
		   // obj.style.background = "red";
		    //根据鼠标位置设定悬停效果DIV位置
		    event = event || window.event;                            //鼠标、键盘事件
		    var top_down = 5;                                        //下移15px避免遮盖当前标签
		    //最左值为当前鼠标位置 与 body宽度减去悬停效果DIV宽度的最小值，否则将右端导致遮盖
		    var e=window.event?window.event:event;
		    		
		    var x,y; 
				//x = e.clientX; 
				//y = e.clientY; 
				var mousePos = mouseCoords(e); 			
				 
				    title_show.style.left = mousePos.x+"px";            //设置title_show在页面中的X轴位置。
				    title_show.style.top =  mousePos.y+"px";    //设置title_show在页面中的Y轴位置。
		    
		   /*  var left = Math.min(event.clientX,document.body.clientWidth-title_show.clientWidth);
		    title_show.style.left = left+"px";            //设置title_show在页面中的X轴位置。
		    title_show.style.top = (event.clientY + top_down)+"px";    //设置title_show在页面中的Y轴位置。 */
		}
		/**
		 * 鼠标离开隐藏TITLE
		 * @params    obj        当前悬停的标签
		 *
		 */
		function titleMouseOut(obj) {
		    var title_show = document.getElementById("title_show");
		    //不存在悬停效果，直接返回
		    if(title_show == null) return false;    
		    //存在悬停效果，恢复原TITLE
		    obj.title = document.title_value;
		    //隐藏悬停效果DIV
		    title_show.style.display = "none";
		    obj.style.background = "";
		}

		/**
		 * 悬停事件绑定
		 * @params    objs        所有需要绑定事件的Element
		 *
		 */
		function attachEvent(objs,words_per_line){
		    if(typeof objs != 'object') return false;
		    //单行字数未设定，非数值，则取默认值50
		    if(typeof words_per_line == 'undefined' || isNaN(words_per_line)){
		        words_per_line = 50;
		    }
		    for(i=0;i<objs.length;i++){
		        objs[i].onmouseover = function(event){
		            titleMouseOver(this,event,words_per_line);
		        }
		        objs[i].onmouseout = function(event){
		            titleMouseOut(this);
		        }
		    }
		}


		//初始化，当页面onload的时候，对所有class="title_hover"的标签绑定TITLE悬停事件
		window.onload = function(){
		    attachEvent(getClass('title_hover'),18);    //行字数设定为18
			load();	
		}
		
		function stylebackgrount(obj){
			 
			
			var flag = $(obj).hasClass("background"); 
			if(flag){ 
				obj.style.background = "";
				
			}else{
				obj.style.background = "red";
				$(obj).unbind("mouseenter").unbind("mouseleave");
				$(obj).unbind("onBlur");
				$(obj).unbind("blur");
				 
			}	
			
		}
		function mouseCoords(ev) 
		{ 
		if(ev.pageX || ev.pageY){ 
		return {x:ev.pageX, y:ev.pageY}; 
		} 
		return{ 
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft-10, 
		y:ev.clientY + document.body.scrollTop - document.body.clientTop 
		}; 
		} 
	</script>
  </body>	
</html>