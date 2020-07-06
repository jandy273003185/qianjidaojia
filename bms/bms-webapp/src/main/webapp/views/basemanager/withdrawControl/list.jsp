<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.withdrawControl.WithdrawControlPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>提现控制管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

function updateWithdrawControl(obj,option){
	
	if(option=='edit'){
		$('#updateWithdrawControlModal .updateWithdrawControlBtn').show();
	}
	if(option=='preview'){
		$('#updateWithdrawControlModal .updateWithdrawControlBtn').hide();
	}
	var withdrawControl = $.data($(obj).parent().parent()[0],"withdrawControl");

	$('#updateWithdrawControlModal').on('show.bs.modal', function () {
		$("#updateWithdrawControlModal #custId").val(withdrawControl.custId);
		$("#updateWithdrawControlModal #mobile").val(withdrawControl.mobile);
		$("#updateWithdrawControlModal #controlType").val(withdrawControl.controlType);
		$("#updateWithdrawControlModal #controlTypesel").val(withdrawControl.controlType);
		$("#updateWithdrawControlModal #protocolId").val(withdrawControl.protocolId);
		$("#updateWithdrawControlModal #pcWdCntPerDay").val(withdrawControl.pcWdCntPerDay);
		$("#updateWithdrawControlModal #pcWdAmtPerOnce").val(withdrawControl.pcWdAmtPerOnce);
		$("#updateWithdrawControlModal #pcWdAmtPerDay").val(withdrawControl.pcWdAmtPerDay);
		$("#updateWithdrawControlModal #pcWdAmtPerMonth").val(withdrawControl.pcWdAmtPerMonth);
		$("#updateWithdrawControlModal #mbWdCntPerDay").val(withdrawControl.mbWdCntPerDay);
		$("#updateWithdrawControlModal #mbWdAmtPerOnce").val(withdrawControl.mbWdAmtPerOnce);
		$("#updateWithdrawControlModal #mbWdAmtPerDay").val(withdrawControl.mbWdAmtPerDay);
		$("#updateWithdrawControlModal #mbWdAmtPerMonth").val(withdrawControl.mbWdAmtPerMonth);
		$("#updateWithdrawControlModal #isShare").val(withdrawControl.isShare);
		
		if(withdrawControl.controlType=='A'){
			$("#updateWithdrawControlModal .protocolIdClass").hide();
			$("#updateWithdrawControlModal .mobileClass").hide();
		}
		if(withdrawControl.controlType=='P'){
			$("#updateWithdrawControlModal .protocolIdClass").show();
			$("#updateWithdrawControlModal .mobileClass").show();
		}
	 })
	
	 $('#updateWithdrawControlModal').on('hide.bs.modal', function () {
		$("#updateWithdrawControlModal #custId").val('');
		$("#updateWithdrawControlModal #mobile").val('');
		$("#updateWithdrawControlModal #controlType").val('');
		$("#updateWithdrawControlModal #controlTypesel").val('');
		$("#updateWithdrawControlModal #protocolId").val('');
		$("#updateWithdrawControlModal #pcWdCntPerDay").val('');
		$("#updateWithdrawControlModal #pcWdAmtPerOnce").val('');
		$("#updateWithdrawControlModal #pcWdAmtPerDay").val('');
		$("#updateWithdrawControlModal #pcWdAmtPerMonth").val('');
		$("#updateWithdrawControlModal #mbWdCntPerDay").val('');
		$("#updateWithdrawControlModal #mbWdAmtPerOnce").val('');
		$("#updateWithdrawControlModal #mbWdAmtPerDay").val('');
		$("#updateWithdrawControlModal #mbWdAmtPerMonth").val('');
		$("#updateWithdrawControlModal #isShare").val('');
	 })
}

   $(function(){
		var withdrawControls = ${withdrawControlList};
		var withdrawControlList = $("tr.withdrawControl");
		$.each(withdrawControls,function(i,value){
			$.data(withdrawControlList[i],"withdrawControl",value);
		});
		
		$(".clearWithdrawControl").click(function(){
			$(".search-table #mobile").val('');
			$(".search-table #protocolId").val('');
		});
		
		/*增加  */
		$(".addWithdrawControlBtn").click(function(){
			var controlType = $("#addWithdrawControlModal #controlType").val();
			
			if(kong.test(controlType)){
				$.gyzbadmin.alertFailure("控制类型不可为空");
				return;
			}
			var custId = $("#addWithdrawControlModal #custId").val();
			var mobile = $("#addWithdrawControlModal #mobile").val();
			var protocolId = $("#addWithdrawControlModal #protocolId").val();
			
			if(controlType=='A'){
				if(kong.test(custId)){
					$.gyzbadmin.alertFailure("客户编号不可为空");
					return;
				}
			}else if(controlType =='P'){
				if(kong.test(mobile)){
					$.gyzbadmin.alertFailure("客户手机号码不可为空");
					return;
				}
				if(kong.test(protocolId)){
					$.gyzbadmin.alertFailure("签约协议编号不可为空");
					return;
				}
			}
			
			var pcWdCntPerDay	= $("#addWithdrawControlModal #pcWdCntPerDay").val();
			
			if(kong.test(pcWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc)不可为空");
				return;
			}
			if(!isInteger(pcWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc)为整数");
				return;
			}
			var pcWdAmtPerOnce = $("#addWithdrawControlModal #pcWdAmtPerOnce").val();
			if(!kong.test(pcWdAmtPerOnce)){
				if(!isAmount(pcWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcWdAmtPerDay = $("#addWithdrawControlModal #pcWdAmtPerDay").val();
			
			if(!kong.test(pcWdAmtPerDay)){
				if(!isAmount(pcWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcWdAmtPerMonth = $("#addWithdrawControlModal #pcWdAmtPerMonth").val();
			
			if(!kong.test(pcWdAmtPerMonth)){
				if(!isAmount(pcWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdCntPerDay = $("#addWithdrawControlModal #mbWdCntPerDay").val();
			if(kong.test(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)不可为空");
				return;
			}
			if(!isInteger(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)为整数");
				return;
			}
			
			var mbWdAmtPerOnce = $("#addWithdrawControlModal #mbWdAmtPerOnce").val();
			if(!kong.test(mbWdAmtPerOnce)){
				if(!isAmount(mbWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdAmtPerDay = $("#addWithdrawControlModal #mbWdAmtPerDay").val();
			if(!kong.test(mbWdAmtPerDay)){
				if(!isAmount(mbWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var mbWdAmtPerMonth = $("#addWithdrawControlModal #mbWdAmtPerMonth").val();
			if(!kong.test(mbWdAmtPerMonth)){
				if(!isAmount(mbWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var isShare = $("#addWithdrawControlModal #isShare").val();
			if(kong.test(isShare)){
				$.gyzbadmin.alertFailure("请选择是否共同额度");
				return
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=WithdrawControlPath.BASE+WithdrawControlPath.ADD%>',{
					'controlType'	:controlType,
					'custId'	 	:custId,
					'mobile'		:mobile,
					'protocolId':	protocolId,
					'pcWdCntPerDay'	:pcWdCntPerDay,
					'pcWdAmtPerOnce':pcWdAmtPerOnce,
					'pcWdAmtPerDay'	:pcWdAmtPerDay,
					'pcWdAmtPerMonth':pcWdAmtPerMonth,
					'mbWdCntPerDay'	:mbWdCntPerDay,
					'mbWdAmtPerOnce':mbWdAmtPerOnce,
					'mbWdAmtPerDay'	:mbWdAmtPerDay,
					'mbWdAmtPerMonth':mbWdAmtPerMonth,
					'isShare'		:isShare
					},function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addWithdrawControlModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("添加失败！"+data.message,function(){
								$("#addWithdrawControlModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
		/* 修改 */
		$(".updateWithdrawControlBtn").click(function(){
			
			var controlType = $("#updateWithdrawControlModal #controlType").val();
			
			if(kong.test(controlType)){
				$.gyzbadmin.alertFailure("控制类型不可为空");
				return;
			}
			
			var custId = $("#updateWithdrawControlModal #custId").val();
			if(kong.test(custId)){
				$.gyzbadmin.alertFailure("客户编号不可为空");
				return;
			}
			
			var protocolId = $("#updateWithdrawControlModal #protocolId").val();
			
			if(controlType =='P'){
				if(kong.test(protocolId)){
					$.gyzbadmin.alertFailure("签约协议编号不可为空");
					return;
				}
			}
			
			var pcWdCntPerDay	= $("#updateWithdrawControlModal #pcWdCntPerDay").val();
			
			if(kong.test(pcWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc)不可为空");
				return;
			}
			if(!isInteger(pcWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(pc)为整数");
				return;
			}
			var pcWdAmtPerOnce = $("#updateWithdrawControlModal #pcWdAmtPerOnce").val();
			if(!kong.test(pcWdAmtPerOnce)){
				if(!isAmount(pcWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var pcWdAmtPerDay = $("#updateWithdrawControlModal #pcWdAmtPerDay").val();
			
			if(!kong.test(pcWdAmtPerDay)){
				if(!isAmount(pcWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var pcWdAmtPerMonth = $("#updateWithdrawControlModal #pcWdAmtPerMonth").val();
			
			if(!kong.test(pcWdAmtPerMonth)){
				if(!isAmount(pcWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var mbWdCntPerDay = $("#updateWithdrawControlModal #mbWdCntPerDay").val();
			if(kong.test(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)不可为空");
				return;
			}
			if(!isInteger(mbWdCntPerDay)){
				$.gyzbadmin.alertFailure("每日次数(iphone)为整数");
				return;
			}
			
			var mbWdAmtPerOnce = $("#updateWithdrawControlModal #mbWdAmtPerOnce").val();
			if(!kong.test(mbWdAmtPerOnce)){
				if(!isAmount(mbWdAmtPerOnce)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var mbWdAmtPerDay = $("#updateWithdrawControlModal #mbWdAmtPerDay").val();
			if(!kong.test(mbWdAmtPerDay)){
				if(!isAmount(mbWdAmtPerDay)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			
			var mbWdAmtPerMonth = $("#updateWithdrawControlModal #mbWdAmtPerMonth").val();
			if(!kong.test(mbWdAmtPerMonth)){
				if(!isAmount(mbWdAmtPerMonth)){
					$.gyzbadmin.alertFailure("金额最多16位整数。小数点后最多两个数字");
					return
				}
			}
			
			var isShare = $("#updateWithdrawControlModal #isShare").val();
			if(kong.test(isShare)){
				$.gyzbadmin.alertFailure("请选择是否共同额度");
				return
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=WithdrawControlPath.BASE+WithdrawControlPath.UPDATE%>',{
				'controlType':controlType,
				'custId':custId,
				'protocolId':protocolId,
				'pcWdCntPerDay':pcWdCntPerDay,
				'pcWdAmtPerOnce':pcWdAmtPerOnce,
				'pcWdAmtPerDay':pcWdAmtPerDay,
				'pcWdAmtPerMonth':pcWdAmtPerMonth,
				'mbWdCntPerDay':mbWdCntPerDay,
				'mbWdAmtPerOnce':mbWdAmtPerOnce,
				'mbWdAmtPerDay':mbWdAmtPerDay,
				'mbWdAmtPerMonth':mbWdAmtPerMonth,
				'isShare':isShare
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateWithdrawControlModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("更新失败！"+data.message,function(){
							$("#updateWithdrawControlModal").modal("hide");
						});
					}
				},'json'
			);
			
		});
		
		/* 删除*/
		$(".deleteWithdrawControl").click(function(){
			
			var withdrawControl = $.data($(this).parent().parent()[0],"withdrawControl");
			$("#deleteWithdrawControlModal").find("input[name='custId']").val(withdrawControl.custId);
			
			$("span.sureDel").text(withdrawControl.custId);
		});
		
		$(".deleteWithdrawControlBtn").click(function(){
			var custId = $("#deleteWithdrawControlModal #custId").val();
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=WithdrawControlPath.BASE+WithdrawControlPath.DELETE%>',{
						'custId': custId
					},function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deleteWithdrawControlModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deleteWithdrawControlModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
	});
	//增加层 类型触发
	function addSelectChange(v){
		if(v=='A'){
			$("#addWithdrawControlModal .custIdCla").show();
			$("#addWithdrawControlModal #custId").val("0000");
			$("#addWithdrawControlModal #custId").attr("readonly",true);
			$("#addWithdrawControlModal #mobile").val('');
			$("#addWithdrawControlModal .mobileCla").hide();
			$("#addWithdrawControlModal .protocolIdClass").hide();
		}
		if(v=='P'){
			$("#addWithdrawControlModal .mobileCla").show();
			$("#addWithdrawControlModal #mobile").val('');
			$("#addWithdrawControlModal #custId").val('');
			$("#addWithdrawControlModal .custIdCla").hide();
			$("#addWithdrawControlModal .protocolIdClass").show();
		}
	};
	
</script>
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
							<form action='<c:url value="<%=WithdrawControlPath.BASE + WithdrawControlPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">手机号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="mobile" name="mobile" value="${queryBean.mobile }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">签约协议编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="protocolId" name="protocolId" value="${queryBean.protocolId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=WithdrawControlPath.BASE + WithdrawControlPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearWithdrawControl">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
											<gyzbadmin:function url="<%=WithdrawControlPath.BASE + WithdrawControlPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addWithdrawControlModal">
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
								提现控制列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:10%;">客户编号</th>
											<th style="width:10%;">手机号码</th>
											<th style="width:5%;">控制类型</th>
											<th style="width:5%;">签约协议编号</th>
											<th style="width:5%;">每日次数(pc)</th>
											<th style="width:10%;">每笔金额(pc)</th>
											<th style="width:10%;">每月总额(pc)</th>
											<th style="width:5%;">每日次数(mobile)</th>
											<th style="width:10%;">每笔金额(mobile)</th>
											<th style="width:10%;">每月总额(mobile)</th>
											<th style="width:10%;">共同额度</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${withdrawControlList}" var="withdrawControl">
											<tr class="withdrawControl">
												<td>${withdrawControl.custId}</td>
												<td>${withdrawControl.mobile}</td>
												<td>
													<c:if test="${withdrawControl.controlType=='A'}">总控</c:if>
													<c:if test="${withdrawControl.controlType=='P'}">个控</c:if>
												</td>
												<td>${withdrawControl.protocolId}</td>
												<td>${withdrawControl.pcWdCntPerDay}</td>
												<td>${withdrawControl.pcWdAmtPerOnce}</td>
												<td>${withdrawControl.pcWdAmtPerMonth}</td>
												<td>${withdrawControl.mbWdCntPerDay}</td>
												<td>${withdrawControl.mbWdAmtPerOnce}</td>
												<td>${withdrawControl.mbWdAmtPerMonth}</td>
												<td>
													<c:if test="${withdrawControl.isShare=='1'}">共用</c:if>
													<c:if test="${withdrawControl.isShare=='0'}">不共用</c:if>
												</td>
												<td>
													<a href="#" class="tooltip-error" onclick="updateWithdrawControl(this,'preview')" data-rel="tooltip" title="预览" data-toggle='modal' data-target="#updateWithdrawControlModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
													<gyzbadmin:function url="<%=WithdrawControlPath.BASE + WithdrawControlPath.UPDATE%>">
														<a href="#" class="tooltip-success" onclick="updateWithdrawControl(this,'edit')" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateWithdrawControlModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=WithdrawControlPath.BASE + WithdrawControlPath.DELETE%>">
													<c:if test="${withdrawControl.custId!='0000'}">
														<a href="#" class="tooltip-error deleteWithdrawControl" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteWithdrawControlModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
																</a>
													</c:if>
																
													
													</gyzbadmin:function>
													
													
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty withdrawControlList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty withdrawControlList}">
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
	 
	<div class="modal fade" id="addWithdrawControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">提现控制新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">控制类型<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<select id="controlType" name="controlType" onchange="addSelectChange(this.value)"  style="width:80%">
									<option value="">--请选择--</option>
									<option value="A">总控</option>
									<option value="P">个控</option>
								</select>
							</td>
						</tr>
						<tr class="custIdCla">
							<td class="td-left" >客户编号<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="custId" name="custId"  style="width:80%"	>
							</td>
						</tr>
						<tr class="mobileCla">
							<td class="td-left" >手机号码<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mobile" name="mobile"  style="width:80%"	>
							</td>
						</tr>
						<tr class="protocolIdClass">
							<td class="td-left" >签约协议编号<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="protocolId" name="protocolId"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdCntPerDay" name="pcWdCntPerDay"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerOnce" name="pcWdAmtPerOnce" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerDay" name="pcWdAmtPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerMonth" name="pcWdAmtPerMonth" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdCntPerDay" name="mbWdCntPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerOnce" name="mbWdAmtPerOnce" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerDay" name="mbWdAmtPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerMonth" name="mbWdAmtPerMonth" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >是否共同额度<span style="color:red">*</span></td>
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
		            <button type="button" class="btn btn-primary addWithdrawControlBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>

		<div class="modal fade" id="updateWithdrawControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">提现控制修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">控制类型<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<select id="controlTypesel" name="controlTypesel" disabled="disabled" style="width:80%">
									<option value="">--请选择--</option>
									<option value="A">总控</option>
									<option value="P">个控</option>
								</select>
								<input type="hidden" id="controlType" name="controlType">
							</td>
						</tr>
						<tr>
							<td class="td-left" >客户编号<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="custId" name="custId" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr class="mobileClass">
							<td class="td-left" >手机号码<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mobile" name="mobile" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr class="protocolIdClass">
							<td class="td-left" >签约协议编号<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="protocolId" name="protocolId"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdCntPerDay" name="pcWdCntPerDay"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerOnce" name="pcWdAmtPerOnce" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerDay" name="pcWdAmtPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(pc)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="pcWdAmtPerMonth" name="pcWdAmtPerMonth" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日次数(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdCntPerDay" name="mbWdCntPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每笔金额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerOnce" name="mbWdAmtPerOnce" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每日总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerDay" name="mbWdAmtPerDay" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >每月总额(mobile)<span style="color:red">*</span></td>
							<td class="td-right" >
								<input type="text" id="mbWdAmtPerMonth" name="mbWdAmtPerMonth" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" >是否共同额度<span style="color:red">*</span></td>
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
		            <button type="button" class="btn btn-primary updateWithdrawControlBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
		<div class="modal fade" id="deleteWithdrawControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">提现控制删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该提现控制[<span class="sureDel"></span>]么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" name="custId" id="custId">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteWithdrawControlBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>