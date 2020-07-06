<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.fee.FeePath" %> 

<%@page import="com.qifenqian.bms.basemanager.tradeControl.TradeControlPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>交易控制管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
function loadTradeControl(){
	$(".search-table #tradeType").val($(".search-table #tradeTypeTemp").val());
	$(".search-table #payerAcctType").val($(".search-table #payerAcctTypeTemp").val());
	$(".search-table #payeeAcctType").val($(".search-table #payeeAcctTypeTemp").val());
	
}

/* 修改弹出层  */

function updateTradeControl(obj,option){
	
	if(option=='edit'){
		$('#updateTradeControlModal .updatetradeControlBtn').show();
	}
	if(option=='preview'){
		$('#updateTradeControlModal .updatetradeControlBtn').hide();
	}
	var tradeControl = $.data($(obj).parent().parent()[0],"tradeControl");
	
	 $('#updateTradeControlModal').on('show.bs.modal', function () {
		$("#updateTradeControlModal #tradeType").val(tradeControl.tradeType);
		$("#updateTradeControlModal #payerAcctType").val(tradeControl.payerAcctType);
		$("#updateTradeControlModal #payeeAcctType").val(tradeControl.payeeAcctType);
		$("#updateTradeControlModal #pcNoCntPerDay").val(tradeControl.pcNoCntPerDay);
		$("#updateTradeControlModal #pcNoAmtPerOnce").val(tradeControl.pcNoAmtPerOnce);
		$("#updateTradeControlModal #pcNoAmtPerDay").val(tradeControl.pcNoAmtPerDay);
		$("#updateTradeControlModal #pcNoAmtPerMonth").val(tradeControl.pcNoAmtPerMonth);
		$("#updateTradeControlModal #pcYesCntPerDay").val(tradeControl.pcYesCntPerDay);
		$("#updateTradeControlModal #pcYesAmtPerOnce").val(tradeControl.pcYesAmtPerOnce);
		$("#updateTradeControlModal #pcYesAmtPerDay").val(tradeControl.pcYesAmtPerDay);
		$("#updateTradeControlModal #pcYesAmtPerMonth").val(tradeControl.pcYesAmtPerMonth);
		$("#updateTradeControlModal #mbWdCntPerDay").val(tradeControl.mbWdCntPerDay);
		$("#updateTradeControlModal #mbWdAmtPerOnce").val(tradeControl.mbWdAmtPerOnce);
		$("#updateTradeControlModal #mbWdAmtPerDay").val(tradeControl.mbWdAmtPerDay);
		$("#updateTradeControlModal #mbWdAmtPerMonth").val(tradeControl.mbWdAmtPerMonth);
		$("#updateTradeControlModal #isShare").val(tradeControl.isShare);
		$("#updateTradeControlModal #tradeTypesel").val(tradeControl.tradeType)
		$("#updateTradeControlModal #payerAcctTypesel").val(tradeControl.payerAcctType)
		$("#updateTradeControlModal #payeeAcctTypesel").val(tradeControl.payeeAcctType);
		$("#updateTradeControlModal #tradeTypesel").attr("disabled",true);
		$("#updateTradeControlModal #payerAcctTypesel").attr("disabled",true);
		$("#updateTradeControlModal #payeeAcctTypesel").attr("disabled",true); 
		 
	 })
	
	 $('#updateTradeControlModal').on('hide.bs.modal', function () {
		$("#updateTradeControlModal #tradeType").val('');
		$("#updateTradeControlModal #payerAcctType").val('');
		$("#updateTradeControlModal #payeeAcctType").val('');
		$("#updateTradeControlModal #pcNoCntPerDay").val('');
		$("#updateTradeControlModal #pcNoAmtPerOnce").val('');
		$("#updateTradeControlModal #pcNoAmtPerDay").val('');
		$("#updateTradeControlModal #pcNoAmtPerMonth").val('');
		$("#updateTradeControlModal #pcYesCntPerDay").val('');
		$("#updateTradeControlModal #pcYesAmtPerOnce").val('');
		$("#updateTradeControlModal #pcYesAmtPerDay").val('');
		$("#updateTradeControlModal #pcYesAmtPerMonth").val('');
		$("#updateTradeControlModal #mbWdCntPerDay").val('');
		$("#updateTradeControlModal #mbWdAmtPerOnce").val('');
		$("#updateTradeControlModal #mbWdAmtPerDay").val('');
		$("#updateTradeControlModal #mbWdAmtPerMonth").val('');
		$("#updateTradeControlModal #isShare").val('');
		$("#updateTradeControlModal #tradeTypesel").val('');
		$("#updateTradeControlModal #payerAcctTypesel").val('');
		$("#updateTradeControlModal #payeeAcctTypesel").val('');
	
	 })
};

	$(function(){
		var tradeControls = ${tradeControlList};
		var tradeControlList = $("tr.tradeControl");
		$.each(tradeControls,function(i,value){
			$.data(tradeControlList[i],"tradeControl",value);
		});
		
		$(".clearTradeControl").click(function(){
			$(".search-table #tradeType").val('');
			$(".search-table #payerAcctType").val('');
			$(".search-table #payeeAcctType").val('');
		});
		/*增加  */
		$(".addTradeControlBtn").click(function(){

			var tradeType = $("#addTradeControlModal #tradeType").val();
			
			if(kong.test(tradeType)){
				$.gyzbadmin.alertFailure("交易类型不可为空");
				return;
			}
			
			var payerAcctType = $("#addTradeControlModal #payerAcctType").val();
			if(kong.test(payerAcctType)){
				$.gyzbadmin.alertFailure("付方账户类型不可为空");
				return;
			}
			
			var payeeAcctType = $("#addTradeControlModal #payeeAcctType").val();
			if(kong.test(payeeAcctType)){
				$.gyzbadmin.alertFailure("收方账户类型不可为空");
				return;
			}
			
			
			var pcNoCntPerDay	= $("#addTradeControlModal #pcNoCntPerDay").val();
			
			if(kong.test(pcNoCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc无证)不可为空");
				return;
			}
			if(!isInteger(pcNoCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc无证)为整数");
				return;
			}
			var pcNoAmtPerOnce = $("#addTradeControlModal #pcNoAmtPerOnce").val();
			if(!kong.test(pcNoAmtPerOnce)){
				if(!isAmount(pcNoAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}	
			
			var pcNoAmtPerDay = $("#addTradeControlModal #pcNoAmtPerDay").val();
			
			if(!kong.test(pcNoAmtPerDay)){
				if(!isAmount(pcNoAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var pcNoAmtPerMonth = $("#addTradeControlModal #pcNoAmtPerMonth").val();
			
			if(!kong.test(pcNoAmtPerMonth)){
				if(!isAmount(pcNoAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var pcYesCntPerDay = $("#addTradeControlModal #pcYesCntPerDay").val();
			if(kong.test(pcYesCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc有证)不可为空");
				return;
			}
			
			if(!isInteger(pcYesCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc有证)为整数");
				return;
			}
			
			var pcYesAmtPerOnce = $("#addTradeControlModal #pcYesAmtPerOnce").val();
			if(!kong.test(pcYesAmtPerOnce)){
				if(!isAmount(pcYesAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var pcYesAmtPerDay = $("#addTradeControlModal #pcYesAmtPerDay").val();
			if(!kong.test(pcYesAmtPerDay)){
				if(!isAmount(pcYesAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var pcYesAmtPerMonth = $("#addTradeControlModal #pcYesAmtPerMonth").val();
			if(!kong.test(pcYesAmtPerMonth)){
				if(!isAmount(pcYesAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdCntPerDay = $("#addTradeControlModal #mbWdCntPerDay").val();
			if(kong.test(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)不可为空");
				return;
			}
			if(!isInteger(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)为整数");
				return;
			}
			
			var mbWdAmtPerOnce = $("#addTradeControlModal #mbWdAmtPerOnce").val();
			if(!kong.test(mbWdAmtPerOnce)){
				if(!isAmount(mbWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var mbWdAmtPerDay = $("#addTradeControlModal #mbWdAmtPerDay").val();
			if(!kong.test(mbWdAmtPerDay)){
				if(!isAmount(mbWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var mbWdAmtPerMonth = $("#addTradeControlModal #mbWdAmtPerMonth").val();
			if(!kong.test(mbWdAmtPerMonth)){
				if(!isAmount(mbWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=TradeControlPath.BASE+TradeControlPath.ADD%>',{
					'tradeType':tradeType,
					'payerAcctType':payerAcctType,
					'payeeAcctType':payeeAcctType,
					'pcNoCntPerDay':$("#addTradeControlModal #pcNoCntPerDay").val(),
					'pcNoAmtPerOnce':$("#addTradeControlModal #pcNoAmtPerOnce").val(),
					'pcNoAmtPerDay':$("#addTradeControlModal #pcNoAmtPerDay").val(),
					'pcNoAmtPerMonth':$("#addTradeControlModal #pcNoAmtPerMonth").val(),
					'pcYesCntPerDay':$("#addTradeControlModal #pcYesCntPerDay").val(),
					'pcYesAmtPerOnce':$("#addTradeControlModal #pcYesAmtPerOnce").val(),
					'pcYesAmtPerDay':$("#addTradeControlModal #pcYesAmtPerDay").val(),
					'pcYesAmtPerMonth':$("#addTradeControlModal #pcYesAmtPerMonth").val(),
					'mbWdCntPerDay':$("#addTradeControlModal #mbWdCntPerDay").val(),
					'mbWdAmtPerOnce':$("#addTradeControlModal #mbWdAmtPerOnce").val(),
					'mbWdAmtPerDay':$("#addTradeControlModal #mbWdAmtPerDay").val(),
					'mbWdAmtPerMonth':$("#addTradeControlModal #mbWdAmtPerMonth").val(),
					'isShare':$("#addTradeControlModal #isShare").val()
					},function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addTradeControlModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("添加失败！"+data.message,function(){
								$("#addTradeControlModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
		
		/* 修改 */
		$(".updatetradeControlBtn").click(function(){

			var tradeType = $("#updateTradeControlModal #tradeType").val();
			
			if(kong.test(tradeType)){
				$.gyzbadmin.alertFailure("交易类型不可为空");
				return;
			}
			
			var payerAcctType = $("#updateTradeControlModal #payerAcctType").val();
			if(kong.test(payerAcctType)){
				$.gyzbadmin.alertFailure("付方账户类型不可为空");
				return;
			}
			
			var payeeAcctType = $("#updateTradeControlModal #payeeAcctType").val();
			if(kong.test(payeeAcctType)){
				$.gyzbadmin.alertFailure("收方账户类型不可为空");
				return;
			}
			
			var pcNoCntPerDay	= $("#updateTradeControlModal #pcNoCntPerDay").val();
			
			if(kong.test(pcNoCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc无证)不可为空");
				return;
			}
			if(!isInteger(pcNoCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc无证)为整数");
				return;
			}
			var pcNoAmtPerOnce = $("#updateTradeControlModal #pcNoAmtPerOnce").val();
			if(!kong.test(pcNoAmtPerOnce)){
				if(!isAmount(pcNoAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcNoAmtPerDay = $("#updateTradeControlModal #pcNoAmtPerDay").val();
			
			if(!kong.test(pcNoAmtPerDay)){
				if(!isAmount(pcNoAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcNoAmtPerMonth = $("#updateTradeControlModal #pcNoAmtPerMonth").val();
			
			if(!kong.test(pcNoAmtPerMonth)){
				if(!isAmount(pcNoAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcYesCntPerDay = $("#updateTradeControlModal #pcYesCntPerDay").val();
			if(kong.test(pcYesCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc有证)不可为空");
				return;
			}
			if(!isInteger(pcYesCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc有证)为整数");
				return;
			}
			
			var pcYesAmtPerOnce = $("#updateTradeControlModal #pcYesAmtPerOnce").val();
			if(!kong.test(pcYesAmtPerOnce)){
				if(!isAmount(pcYesAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcYesAmtPerDay = $("#updateTradeControlModal #pcYesAmtPerDay").val();
			if(!kong.test(pcYesAmtPerDay)){
				if(!isAmount(pcYesAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcYesAmtPerMonth = $("#updateTradeControlModal #pcYesAmtPerMonth").val();
			if(!kong.test(pcYesAmtPerMonth)){
				if(!isAmount(pcYesAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdCntPerDay = $("#updateTradeControlModal #mbWdCntPerDay").val();
			if(kong.test(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)不可为空");
				return;
			}
			if(!isInteger(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)为整数");
				return;
			}
			var mbWdAmtPerOnce = $("#updateTradeControlModal #mbWdAmtPerOnce").val();
			if(!kong.test(mbWdAmtPerOnce)){
				if(!isAmount(mbWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdAmtPerDay = $("#updateTradeControlModal #mbWdAmtPerDay").val();
			if(!kong.test(mbWdAmtPerDay)){
				if(!isAmount(mbWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdAmtPerMonth = $("#updateTradeControlModal #mbWdAmtPerMonth").val();
			if(!kong.test(mbWdAmtPerMonth)){
				if(!isAmount(mbWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=TradeControlPath.BASE+TradeControlPath.UPDATE%>',{
				'tradeType':tradeType,
				'payerAcctType':payerAcctType,
				'payeeAcctType':payeeAcctType,
				'pcNoCntPerDay':$("#updateTradeControlModal #pcNoCntPerDay").val(),
				'pcNoAmtPerOnce':$("#updateTradeControlModal #pcNoAmtPerOnce").val(),
				'pcNoAmtPerDay':$("#updateTradeControlModal #pcNoAmtPerDay").val(),
				'pcNoAmtPerMonth':$("#updateTradeControlModal #pcNoAmtPerMonth").val(),
				'pcYesCntPerDay':$("#updateTradeControlModal #pcYesCntPerDay").val(),
				'pcYesAmtPerOnce':$("#updateTradeControlModal #pcYesAmtPerOnce").val(),
				'pcYesAmtPerDay':$("#updateTradeControlModal #pcYesAmtPerDay").val(),
				'pcYesAmtPerMonth':$("#updateTradeControlModal #pcYesAmtPerMonth").val(),
				'mbWdCntPerDay':$("#updateTradeControlModal #mbWdCntPerDay").val(),
				'mbWdAmtPerOnce':$("#updateTradeControlModal #mbWdAmtPerOnce").val(),
				'mbWdAmtPerDay':$("#updateTradeControlModal #mbWdAmtPerDay").val(),
				'mbWdAmtPerMonth':$("#updateTradeControlModal #mbWdAmtPerMonth").val(),
				'isShare':$("#updateTradeControlModal #isShare").val()
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateTradeControlModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("更新失败！"+data.message,function(){
							$("#updateTradeControlModal").modal("hide");
						});
					}
				},'json'
			);
			
		});
		
		/* 删除*/
		$(".deleteTradeControl").click(function(){
			var tradeControl = $.data($(this).parent().parent()[0],"tradeControl");
			
			$("#deleteTradeControlModal").find("input[name='tradeType']").val(tradeControl.tradeType);
			$("#deleteTradeControlModal").find("input[name='payerAcctType']").val(tradeControl.payerAcctType);
			$("#deleteTradeControlModal").find("input[name='payeeAcctType']").val(tradeControl.payeeAcctType);
			document.getElementById('tradeType').options[document.getElementById('tradeType').selectedIndex].text;
			
			$("span.sureDel").text(tradeControl.tradeType+"-"+tradeControl.payerAcctType+"-"+tradeControl.payeeAcctType);
		});
		
		$(".deleteTradeControlBtn").click(function(){
			var tradeType = $("#deleteTradeControlModal #tradeType").val();
			var payerAcctType = $("#deleteTradeControlModal #payerAcctType").val();
			var payeeAcctType = $("#deleteTradeControlModal #payeeAcctType").val();
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=TradeControlPath.BASE+TradeControlPath.DELETE%>',{
						'tradeType': tradeType,
						'payerAcctType':payerAcctType,
						'payeeAcctType':payeeAcctType
					},function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deleteTradeControlModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deleteTradeControlModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
	});
</script>
<body onload="loadTradeControl()">
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
							<form action='<c:url value="<%=TradeControlPath.BASE + TradeControlPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">交易类型</td>
									<td class="td-right">
										<input type="hidden" name="tradeTypeTemp" id="tradeTypeTemp" value="${queryBean.tradeType }" />
										<sevenpay:selectTradeType name="tradeType" id="tradeType" style="width:60%"/>
									</td>
									<td class="td-left" >付方账户类型</td>
									<td class="td-right" >
										<input type="hidden" name="payerAcctTypeTemp" id="payerAcctTypeTemp" value="${queryBean.payerAcctType }" />
										<sevenpay:selectAcctType name="payerAcctType" id="payerAcctType" style="width:60%"/>
									</td>
									<td class="td-left" >收方账户类型</td>
									<td class="td-right" >
										<input type="hidden" name="payeeAcctTypeTemp" id="payeeAcctTypeTemp" value="${queryBean.payeeAcctType }" />
										<sevenpay:selectAcctType name="payeeAcctType" id="payeeAcctType" style="width:60%"/>
									</td>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TradeControlPath.BASE + TradeControlPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeControl">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=TradeControlPath.BASE + TradeControlPath.ADD%>">
												<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addTradeControlModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								交易控制列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
										    <th style="width:16%;">编号</th>
											<th style="width:10%;">交易类型</th>
											<th style="width:10%;">付方账户类型</th>
											<th style="width:10%;">收方账户类型</th>
											<th style="width:8%;">每日次数(pc)</th>
											<th style="width:10%;">每笔金额(pc)</th>
											<th style="width:10%;">每日次数(mobile)</th>
											<th style="width:10%;">每笔金额(mobile)</th>
											<th style="width:8%;">共用额度</th>
											<th style="width:8%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tradeControls}" var="tradeControl">
											<tr class="tradeControl">
											 <td>${tradeControl.tradeType}-${tradeControl.payerAcctType}-${tradeControl.payeeAcctType}</td>
												<td>
													<c:if test="${tradeControl.tradeType=='1001'}">转账到7分钱</c:if>
													<c:if test="${tradeControl.tradeType=='1002'}">转账到银行卡</c:if>
													<c:if test="${tradeControl.tradeType=='1101'}">商户接入-收款-无担保</c:if>
													<c:if test="${tradeControl.tradeType=='1102'}">商户接入-收款-有担保</c:if>
													<c:if test="${tradeControl.tradeType=='1201'}">转入7分宝</c:if>
													<c:if test="${tradeControl.tradeType=='1202'}">转出7分宝</c:if>
													<c:if test="${tradeControl.tradeType=='8001'}">退款</c:if>
												</td>
												<td>${tradeControl.payerAcctType.desc}</td>
												<td>${tradeControl.payeeAcctType.desc}</td>
												<td>${tradeControl.pcNoCntPerDay}</td>
												<td>${tradeControl.pcNoAmtPerOnce}</td>
												<td>${tradeControl.mbWdCntPerDay}</td>
												<td>${tradeControl.mbWdAmtPerOnce}</td>
												<td>
													<c:if test="${tradeControl.isShare=='1'}">共用</c:if>
													<c:if test="${tradeControl.isShare=='0'}">不共用</c:if>
												</td>
												<td>
													<a href="#"  data-toggle='modal' class="tooltip-error"  onclick="updateTradeControl(this,'preview')" data-rel="tooltip" title="预览" data-target="#updateTradeControlModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
													<gyzbadmin:function url="<%=TradeControlPath.BASE + TradeControlPath.UPDATE%>">
														<a href="#" class="tooltip-success" data-rel="tooltip" onclick="updateTradeControl(this,'edit')" data-toggle="modal" title="修改"  data-target="#updateTradeControlModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=TradeControlPath.BASE + TradeControlPath.DELETE%>">
														<a href="#" class="tooltip-error deleteTradeControl" data-rel="tooltip" title="Delete" data-toggle="modal" data-target="#deleteTradeControlModal">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty tradeControls}">
											<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty tradeControls}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
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
	 
	<div class="modal fade" id="addTradeControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">交易控制新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">交易类型<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<sevenpay:selectTradeType name="tradeType" id="tradeType" style="width:80%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >付方账户类型<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectAcctType name="payerAcctType" id="payerAcctType" style="width:80%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >收方账户类型<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectAcctType name="payeeAcctType" id="payeeAcctType" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoCntPerDay" name="pcNoCntPerDay"  style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerOnce" name="pcNoAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerDay" name="pcNoAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerMonth" name="pcNoAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesCntPerDay" name="pcYesCntPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerOnce" name="pcYesAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerDay" name="pcYesAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerMonth" name="pcYesAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdCntPerDay" name="mbWdCntPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerOnce" name="mbWdAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerDay" name="mbWdAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerMonth" name="mbWdAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >是否共用额度<span style="color:red">*</span></td>
							<td class="td-right" >
								<select name="isShare" id="isShare" style="width:80%" >
									<option value="">--请选择--</option>
									<option value="1">共用</option>
									<option value="0">不共用</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addTradeControlBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
		<div class="modal fade" id="updateTradeControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">交易控制预览</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">交易类型<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<sevenpay:selectTradeType name="tradeTypesel" id="tradeTypesel" style="width:80%"/>
								<input type="hidden" name="tradeType" id="tradeType">
							</td>
						</tr>
						<tr>
							<td class="td-left">付方账户类型<span style="color:red">*</span></td>
							<td class="td-right">
								<sevenpay:selectAcctType name="payerAcctTypesel" id="payerAcctTypesel" style="width:80%"/>
								<input type="hidden" name="payerAcctType" id="payerAcctType">
							</td>
						</tr>
						<tr>
							<td class="td-left" >收方账户类型<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectAcctType name="payeeAcctTypesel" id="payeeAcctTypesel" style="width:80%"/>
								<input type="hidden" name="payeeAcctType" id="payeeAcctType">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoCntPerDay" name="pcNoCntPerDay"  style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerOnce" name="pcNoAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerDay" name="pcNoAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)(无证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcNoAmtPerMonth" name="pcNoAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesCntPerDay" name="pcYesCntPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerOnce" name="pcYesAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerDay" name="pcYesAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)(有证)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcYesAmtPerMonth" name="pcYesAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdCntPerDay" name="mbWdCntPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerOnce" name="mbWdAmtPerOnce" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerDay" name="mbWdAmtPerDay" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerMonth" name="mbWdAmtPerMonth" style="width:80%"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" >是否共用额度<span style="color:red">*</span></td>
							<td class="td-right" >
								<select name="isShare" id="isShare" style="width:80%">
									<option value="">--请选择--</option>
									<option value="1">共用</option>
									<option value="0">不共用</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updatetradeControlBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
		<div class="modal fade" id="deleteTradeControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">交易控制删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该交易控制[<span class="sureDel"></span>]么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" name=tradeType id="tradeType">
		         	<input type="hidden" name="payerAcctType" id="payerAcctType">
		         	<input type="hidden" name="payeeAcctType" id="payeeAcctType">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteTradeControlBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>

