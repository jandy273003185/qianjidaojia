<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.agent.controller.PayChannelPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>支付渠道管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$(".clearBtn").click(function(){
			$("#payChannelCode,#payChannelName").val("");
		});
		$(".addBtn").click(function(){
			var payChannelCode=$("#addModal").find("#payChannelCode").val();
			var payChannelMemo=$("#addModal").find("#payChannelMemo").val();
			var payChannelName=$("#addModal").find("#payChannelName").val();
			var payChannelRate=$("#addModal").find("#payChannelRate").val();
			var supllyOrg=$("#addModal").find("#supllyOrg").val();
			var ourBankAcctNo = $("#addModal").find("#ourBankAcctNo").val();
			var ourBankAcctName = $("#addModal").find("#ourBankAcctName").val();

			if(kong.test(payChannelCode)){
				$.gyzbadmin.alertFailure("渠道编码不可为空");
				return;
			}
			if(kong.test(payChannelMemo)){
				$.gyzbadmin.alertFailure("渠道说明不可为空");
				return;
			}
			
			if(kong.test(payChannelName)){
				$.gyzbadmin.alertFailure("渠道名称不可为空");
				return;
			}
			if(kong.test(payChannelRate)){
				$.gyzbadmin.alertFailure("成本费率不可为空");
				return;
			}
			if(kong.test(supllyOrg)){
				$.gyzbadmin.alertFailure("渠道提供机构不可为空");
				return;
			}
			if(kong.test(ourBankAcctNo)){
				$.gyzbadmin.alertFailure("结算账户号不可为空");
				return;
			}
			if(kong.test(ourBankAcctName)){
				$.gyzbadmin.alertFailure("结算账户名不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELADD%>",
				{
					'payChannelCode':payChannelCode,
					'payChannelMemo':payChannelMemo,
					'payChannelName':payChannelName,
					'payChannelRate':payChannelRate,
					'supllyOrg':supllyOrg,
					'ourBankAcctNo':ourBankAcctNo,
					'ourBankAcctName':ourBankAcctName
				},
				function(data){
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addModal").modal("hide");
						},function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message);
						$("#addModal").modal("hide");
					}

					
					
				},'json'
				);
		});

		$(".updateModal").click(function(){
			var payChannelMemo = $(this).parents("tr").find("#payChannelMemo").text();
			var payChannelName = $(this).parents("tr").find("#payChannelName").text();
			var payChannelRate = $(this).parents("tr").find("#payChannelRate").text();
			var payChannelCode = $(this).parents("tr").find("#payChannelCode").text();
			var supllyOrg = $(this).parents("tr").find("#supllyOrg").text();
			var ourBankAcctNo = $(this).parents("tr").find("#ourBankAcctNo").text();
			var ourBankAcctName = $(this).parents("tr").find("#ourBankAcctName").text();
			
			$("#updateModal").find("#payChannelMemo").val(payChannelMemo);
			$("#updateModal").find("#payChannelName").val(payChannelName);
			$("#updateModal").find("#payChannelRate").val(payChannelRate);
			$("#updateModal").find("#payChannelCode").val(payChannelCode);
			$("#updateModal").find("#supllyOrg").val(supllyOrg);
			$("#updateModal").find("#ourBankAcctNo").val(ourBankAcctNo);
			$("#updateModal").find("#ourBankAcctName").val(ourBankAcctName);
		});

		
		$(".updateBtn").click(function(){
			var payChannelMemo=$("#updateModal").find("#payChannelMemo").val();
			var payChannelName=$("#updateModal").find("#payChannelName").val();
			var payChannelRate=$("#updateModal").find("#payChannelRate").val();
			var payChannelCode=$("#updateModal").find("#payChannelCode").val();
			var supllyOrg=$("#updateModal").find("#supllyOrg").val();
			var ourBankAcctNo = $("#updateModal").find("#ourBankAcctNo").val();
			var ourBankAcctName = $("#updateModal").find("#ourBankAcctName").val();
			if(kong.test(payChannelMemo)){
				$.gyzbadmin.alertFailure("渠道说明不可为空");
				return;
			}
			if(kong.test(payChannelName)){
				$.gyzbadmin.alertFailure("渠道名称不可为空");
				return;
			}
			if(kong.test(payChannelRate)){
				$.gyzbadmin.alertFailure("成本费率不可为空");
				return;
			}
			if(kong.test(supllyOrg)){
				$.gyzbadmin.alertFailure("渠道提供机构不可为空");
				return;
			}
			if(kong.test(ourBankAcctNo)){
				$.gyzbadmin.alertFailure("结算账户号不可为空");
				return;
			}
			if(kong.test(ourBankAcctName)){
				$.gyzbadmin.alertFailure("结算账户名不可为空");
				return;
			}
			$.post(window.Constants.ContextPath +"<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELUPDATE%>",
				{
				'payChannelCode':payChannelCode,
				'payChannelMemo':payChannelMemo,
				'payChannelName':payChannelName,
				'payChannelRate':payChannelRate,
				'supllyOrg':supllyOrg,
				'ourBankAcctNo':ourBankAcctNo,
				'ourBankAcctName':ourBankAcctName
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("修改成功！",function(){
								$("#updateModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
								$("#updateModal").modal("hide");
							},function(){
								window.location.reload();
							}); 
					}
				},'json'

				);
		});

		$(".deleteModal").click(function(){
			var payChannelCode = $(this).parents("tr").find("#payChannelCode").text();
			
			$("#deleteModal").find("#payChannelCode").val(payChannelCode);
			
		});
		
		$(".deleteBtn").click(function(){
			var payChannelCode=$("#deleteModal").find("#payChannelCode").val();
			
			
			$.post(window.Constants.ContextPath +"<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELDELETE%>",
				{
					'payChannelCode':payChannelCode
					
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deleteModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deleteModal").modal("hide");
							});
					}
				},'json'
				);
		});
		



		
	});

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
							<form action='<c:url value="<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELLIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">渠道编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="payChannelCode" name="payChannelCode" value="${queryBean.payChannelCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">渠道名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="payChannelName" name="payChannelName" value="${queryBean.payChannelName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addModal">
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
								渠道信息列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:5%;">渠道编号</th>
											<th style="width:5%;">渠道名称</th>
											<th style="width:10%;">渠道说明</th>
											<th style="width:10%;">成本费率</th>
											<th style="width:5%;">渠道提供机构</th>
											<th style="width:5%;">我方结算账户号</th>
											<th style="width:5%;">我方结算账户名</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${payChannelList}" var="bean">
											<tr >
												<td id="payChannelCode">${bean.payChannelCode}</td>
												<td id="payChannelName">${bean.payChannelName}</td>
												<td id="payChannelMemo">${bean.payChannelMemo}</td>
												<td id="payChannelRate">${bean.payChannelRate}</td>
												<td id="supllyOrg">${bean.supllyOrg}</td>
												<td id="ourBankAcctNo">${bean.ourBankAcctNo}</td>
												<td id="ourBankAcctName">${bean.ourBankAcctName}</td>
												<td>
													<gyzbadmin:function url="<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELUPDATE%>">
														<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=PayChannelPath.BASE + PayChannelPath.PAYCHANNELDELETE%>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty payChannelList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty payChannelList}">
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
	 
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		      
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">支付渠道新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		          			<tr class="custIdCla">
								<td class="td-left" >渠道编号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="payChannelCode" name="payChannelCode"  style="width:80%"	>
								</td>
							</tr>
		            		<tr class="custIdCla">
								<td class="td-left" >渠道名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="payChannelName" name="payChannelName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">渠道说明<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="payChannelMemo" name="payChannelMemo"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >成本费率<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="payChannelRate" name="payChannelRate"  style="width:80%"	>
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >渠道提供机构<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="supllyOrg" name="supllyOrg" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >我方结算账户号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="ourBankAcctNo" name="ourBankAcctNo" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >我方结算账户名<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="ourBankAcctName" name="ourBankAcctName" style="width:80%">
								</td>
							</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn" >提交</button>
		         </div>
		         
		      </div>
		     </div>
		</div>

		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">协议修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<input type="hidden" id="payChannelCode"/>
							<tr class="custIdCla">
								<td class="td-left" >渠道名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="payChannelName" name="payChannelName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">渠道说明<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="payChannelMemo" name="payChannelMemo"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >成本费率<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="payChannelRate" name="payChannelRate"  style="width:80%"	>
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >渠道提供机构<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="supllyOrg" name="supllyOrg" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >我方结算账户号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="ourBankAcctNo" name="ourBankAcctNo" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >我方结算账户名<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="ourBankAcctName" name="ourBankAcctName" style="width:80%">
								</td>
							</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		        	<input type="hidden"  name="channelId" id="channelId"/>
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">渠道删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该渠道信息么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden"  id="payChannelCode"/>
		         	
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>