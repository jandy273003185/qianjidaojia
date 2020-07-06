<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.agent.controller.AgreementPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>协议管理</title>
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
		
		$(".addBtn").click(function(){
			
			var agentCode=$("#addModal").find("#agentCode").val();
			var merCode=$("#addModal").find("#merCode").val();
			var prodCode=$("#addModal").find("#prodCode").val();
			var agentAgreeRate=$("#addModal").find("#agentAgreeRate").val();
			if(kong.test(agentCode)){
				$.gyzbadmin.alertFailure("代理商不可为空");
				return;
			}
			if(kong.test(merCode)){
				$.gyzbadmin.alertFailure("商户不可为空");
				return;
			}
			if(kong.test(prodCode)){
				$.gyzbadmin.alertFailure("产品不可为空");
				return;
			}
			if(kong.test(agentAgreeRate)){
				$.gyzbadmin.alertFailure("费率不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=AgreementPath.BASE + AgreementPath.AGREEADD%>",
				{
					'agentCode':agentCode,
					'merCode':merCode,
					'prodCode':prodCode,
					'agentAgreeRate':agentAgreeRate
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){

						$.gyzbadmin.alertSuccess("新增成功！",function(){
							$("#addModal").modal("hide");
						},function(){
							window.location.reload();
						});
							
						
					}else{
							$.gyzbadmin.alertFailure("新增失败！"+data.message,function(){
								$("#addModal").modal("hide");
							});
					}
				},'json'
				);
		});

		$(".updateModal").click(function(){
			
			agentCode = $(this).parents("tr").find("#agentCode").text();
			merCode=$(this).parents("tr").find("#merCode").text();
			prodCode=$(this).parents("tr").find("#prodCode").text();
			agentAgreeRate= $(this).parents("tr").find("#agentAgreeRate").text();
			agentAgreementCode = $(this).parents("tr").find("#agentAgreementCode").text();
			$("#updateModal").find("#agentCode").val(agentCode);
			$("#updateModal").find("#merCode").val(merCode);
			$("#updateModal").find("#prodCode").val(prodCode);
			$("#updateModal").find("#agentAgreeRate").val(agentAgreeRate);
			$("#updateModal").find("#agentAgreementCode").val(agentAgreementCode);
		});

		
		$(".updateBtn").click(function(){
			var agentCode=$("#updateModal").find("#agentCode").val();
			var merCode=$("#updateModal").find("#merCode").val();
			var prodCode=$("#updateModal").find("#prodCode").val();
			var agentAgreeRate=$("#updateModal").find("#agentAgreeRate").val();
			var agentAgreementCode = $("#updateModal").find("#agentAgreementCode").val();
			if(kong.test(agentCode)){
				$.gyzbadmin.alertFailure("代理商不可为空");
				return;
			}
			if(kong.test(merCode)){
				$.gyzbadmin.alertFailure("商户不可为空");
				return;
			}
			if(kong.test(prodCode)){
				$.gyzbadmin.alertFailure("产品不可为空");
				return;
			}
			if(kong.test(agentAgreeRate)){
				$.gyzbadmin.alertFailure("费率不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=AgreementPath.BASE + AgreementPath.AGREEUPDATE%>",
				{
					'agentAgreementCode':agentAgreementCode,
					'agentCode':agentCode,
					'merCode':merCode,
					'prodCode':prodCode,
					'agentAgreeRate':agentAgreeRate
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
							
							window.location.reload();
							$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
								$("#updateModal").modal("hide");
							});
					}
				},'json'

				);
		});

		$(".deleteModal").click(function(){
			agentCode = $(this).parents("tr").find("#agentCode").text();
			merCode=$(this).parents("tr").find("#merCode").text();
			prodCode=$(this).parents("tr").find("#prodCode").text();
			agentAgreementCode= $(this).parents("tr").find("#agentAgreementCode").text();
			
			$("#deleteModal").find("#agentCode").val(agentCode);
			$("#deleteModal").find("#merCode").val(merCode);
			$("#deleteModal").find("#prodCode").val(prodCode);
			$("#deleteModal").find("#agentAgreementCode").val(agentAgreementCode);
			
		});
		
		$(".deleteBtn").click(function(){
			var agentCode=$("#deleteModal").find("#agentCode").val();
			var merCode=$("#deleteModal").find("#merCode").val();
			var prodCode=$("#deleteModal").find("#prodCode").val();
			var agentAgreementCode=$("#deleteModal").find("#agentAgreementCode").val();
			
			$.post(window.Constants.ContextPath +"<%=AgreementPath.BASE + AgreementPath.AGREEDELETE%>",
				{
					'agentCode':agentCode,
					'merCode':merCode,
					'prodCode':prodCode,
					'agentAgreementCode':agentAgreementCode
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
		
	$(".clearBtn").click(function(){
		$("#merCode,#agentCode,#prodCode").val("");
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
							<form action='<c:url value="<%=AgreementPath.BASE + AgreementPath.AGREELIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merCode" name="merCode" value="${queryBean.merCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">代理商编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="agentCode" name="agentCode" value="${queryBean.agentCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">产品编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="prodCode" name="prodCode" value="${queryBean.prodCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AgreementPath.BASE + AgreementPath.AGREELIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AgreementPath.BASE + AgreementPath.AGREEADD%>">
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
								协议信息列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:5%;">协议编号</th>
											<th style="width:5%;">代理商编号</th>
											<th style="width:10%;">商户编号</th>
											<th style="width:10%;">产品编号</th>
											<th style="width:5%;">代理商佣金比例</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${agreementList}" var="bean">
											<tr >
												
												<td id="agentAgreementCode">${bean.agentAgreementCode}</td>
												<td id="agentCode">${bean.agentCode}</td>
												<td id="merCode">${bean.merCode}</td>
												<td id="prodCode">${bean.prodCode}</td>
												<td id="agentAgreeRate">${bean.agentAgreeRate}</td>
												<td>
													<gyzbadmin:function url="<%=AgreementPath.BASE + AgreementPath.AGREEUPDATE%>">
														<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=AgreementPath.BASE + AgreementPath.AGREEDELETE%>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty agreementList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty agreementList}">
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
		            <h4 class="modal-title" id="myModalLabel">协议新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<input type="hidden" id="agentAgreementCode" name="agentAgreementCode"/>
		            		<tr class="custIdCla">
								<td class="td-left" >代理商编号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="agentCode" name="agentCode"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">商户编号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="merCode" name="merCode"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >产品编号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="prodCode" name="prodCode"  style="width:80%"	>
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >单笔交易限额<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="agentAgreeRate" name="agentAgreeRate" style="width:80%">
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
		            		<input type="hidden" id="agentAgreementCode"/>
		            		<input type="hidden" id="agentCode"/>
		            		<input type="hidden" id="merCode"/>
		            		<input type="hidden" id="prodCode"/>
		            		<tr class="protocolIdClass">
								<td class="td-left" >佣金费率<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="agentAgreeRate" name="agentAgreeRate" style="width:80%">
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
		            <h4 class="modal-title" id="myModalLabel">协议删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该协议信息么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden"  id="agentAgreementCode"/>
		         	<input type="hidden"  id ="agentCode"/>
		         	<input type="hidden"  id ="prodCode"/>
		         	<input type="hidden"  id ="merCode"/>
		         	
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>