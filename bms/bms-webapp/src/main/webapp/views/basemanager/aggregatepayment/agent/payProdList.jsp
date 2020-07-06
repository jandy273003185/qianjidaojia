<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.agent.controller.PayProdPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>支付产品管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	function isDecimal(c)
	{
	    var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
	    return r.test(c);
	}
	$(function(){
		$(".clearBtn").click(function(){
			$("#prodCode,#prodName").val("");
		});
		$(".addBtn").click(function(){
			var prodCode=$("#addModal").find("#prodCode").val();
			var prodMemo=$("#addModal").find("#prodMemo").val();
			var prodName=$("#addModal").find("#prodName").val();
			var standardRate = $("#addModal").find("#standardRate").val();

			if(kong.test(prodCode)){
				$.gyzbadmin.alertFailure("编码不可为空");
				return;
			}
			if(kong.test(prodMemo)){
				$.gyzbadmin.alertFailure("说明不可为空");
				return;
			}
			
			if(kong.test(prodName)){
				$.gyzbadmin.alertFailure("名称不可为空");
				return;
			}
			if(kong.test(standardRate)){
				$.gyzbadmin.alertFailure("费率不可为空");
				return;
			}
			if(!isDecimal(standardRate)){
				$.gyzbadmin.alertFailure("费率请填小数");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=PayProdPath.BASE + PayProdPath.PAYPRODADD%>",
				{
					'prodCode':prodCode,
					'prodMemo':prodMemo,
					'prodName':prodName,
					'standardRate':standardRate
					
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
						$.gyzbadmin.alertFailure("添加失败！"+data.message,function(){
							$("#addModal").modal("hide");
						});
					}
				},'json'
				);
		});

		$(".updateModal").click(function(){
			var prodMemo = $(this).parents("tr").find("#prodMemo").text();
			var prodName = $(this).parents("tr").find("#prodName").text();
			var prodCode = $(this).parents("tr").find("#prodCode").text();
			var standardRate = $(this).parents("tr").find("#standardRate").text();
			
			$("#updateModal").find("#prodMemo").val(prodMemo);
			$("#updateModal").find("#prodName").val(prodName);
			$("#updateModal").find("#prodCode").val(prodCode);
			var temp = standardRate.replace('%','');
			$("#updateModal").find("#standardRate").val(temp/100);
			
		});

		
		$(".updateBtn").click(function(){
			
			var prodMemo=$("#updateModal").find("#prodMemo").val();
			var prodName=$("#updateModal").find("#prodName").val();
			var prodCode=$("#updateModal").find("#prodCode").val();
			var standardRate=$("#updateModal").find("#standardRate").val();
			
			if(kong.test(prodCode)){
				$.gyzbadmin.alertFailure("编码不可为空");
				return;
			}
			if(kong.test(prodName)){
				$.gyzbadmin.alertFailure("名称不可为空");
				return;
			}
			if(kong.test(standardRate)){
				$.gyzbadmin.alertFailure("费率不可为空");
				return;
			}
			if(!isDecimal(standardRate)){
				$.gyzbadmin.alertFailure("费率请填小数");
				return;
			}
			if(kong.test(prodMemo)){
				$.gyzbadmin.alertFailure("说明不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=PayProdPath.BASE + PayProdPath.PAYPRODUPDATE%>",
				{
					'prodCode':prodCode,
					'prodMemo':prodMemo,
					'prodName':prodName,
					'standardRate':standardRate
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
							});
					}
				},'json'

				);
		});

		$(".deleteModal").click(function(){
			var prodCode = $(this).parents("tr").find("#prodCode").text();
			
			$("#deleteModal").find("#prodCode").val(prodCode);
			
		});
		
		$(".deleteBtn").click(function(){
			var prodCode=$("#deleteModal").find("#prodCode").val();
			
			
			$.post(window.Constants.ContextPath +"<%=PayProdPath.BASE + PayProdPath.PAYPRODDELETE%>",
				{
					'prodCode':prodCode
					
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
							<form action='<c:url value="<%=PayProdPath.BASE + PayProdPath.PAYPRODPLIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">产品编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="prodCode" name="prodCode" value="${queryBean.prodCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">产品名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="prodName" name="prodName" value="${queryBean.prodName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PayProdPath.BASE + PayProdPath.PAYPRODPLIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=PayProdPath.BASE + PayProdPath.PAYPRODADD%>">
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
											<th style="width:5%;">产品编号</th>
											<th style="width:5%;">产品名称</th>
											<th style="width:10%;">产品说明</th>
											<th style="width:10%;">标准费率</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${payProdList}" var="bean">
											<tr >
												<td id="prodCode">${bean.prodCode}</td>
												<td id="prodName">${bean.prodName}</td>
												<td id="prodMemo">${bean.prodMemo}</td>
												<td id="standardRate">${bean.standardRate}</td>
												<td>
													<gyzbadmin:function url="<%=PayProdPath.BASE + PayProdPath.PAYPRODUPDATE%>">
														<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=PayProdPath.BASE + PayProdPath.PAYPRODDELETE%>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty payProdList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty payProdList}">
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
		            <h4 class="modal-title" id="myModalLabel">支付产品新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		          			<tr class="custIdCla">
								<td class="td-left" >产品编号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="prodCode" name="prodCode"  style="width:80%"	>
								</td>
							</tr>
		            		<tr class="custIdCla">
								<td class="td-left" >产品名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="prodName" name="prodName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">产品说明<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="prodMemo" name="prodMemo"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >标准费率<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="standardRate" name="standardRate"  style="width:80%"	><span>请填小数</span>
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
		            <h4 class="modal-title" id="myModalLabel">产品修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<input type="hidden" id="prodCode"/>
							
		            		<tr class="custIdCla">
								<td class="td-left" >产品名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="prodName" name="prodName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">产品说明<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="prodMemo" name="prodMemo"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >标准费率<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="standardRate" name="standardRate"  style="width:80%"	><span>请填小数</span>
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
		            <h4 class="modal-title" id="myModalLabel">支付产品删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该支付产品信息么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden"  id="prodCode"/>
		         	
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>