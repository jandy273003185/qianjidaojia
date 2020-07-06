<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.toPayProduct.controller.ToPayProductPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>代付产品列表</title>
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
			$("#custName,#merchantCode,#description").val("");
		});

		$(".updateModal").click(function(){
			var merchantCode = $(this).parents("tr").find("#merchantCode").text();
			var custName = $(this).parents("tr").find("#custName").text();
			var productName = $(this).parents("tr").find("#productName").text();
			var aisle = $(this).parents("tr").find("#aisle").val();
			var productRate = $(this).parents("tr").find("#productRate").text().trim();
			var rechargeRate = $(this).parents("tr").find("#rechargeRate").val().trim();
			var description = $(this).parents("tr").find("#description").text().trim();
			var productId = $(this).parents("tr").find("#productId").val().trim();
			var sevenpayRechargeType = $(this).parents("tr").find("#sevenpayRechargeType").text().trim();
			
			$("#updateModal").find("#merchantCode").val(merchantCode);
			$("#updateModal").find("#custName").val(custName);
			$("#updateModal").find("#productName").val(productName);
			$("#updateModal").find("#aisle").val(aisle);
			$("#updateModal").find("#productRate").val(productRate);
			$("#updateModal").find("#rechargeRate").val(rechargeRate);
			$("#updateModal").find("#channelName").val(description);
			$("#updateModal").find("#productId").val(productId);
			$("#updateModal").find("#sevenpayRechargeType").val(sevenpayRechargeType);
		});



		$(".updateBtn").click(function(){
			
			
			var merchantCode = $("#updateModal").find("#merchantCode").val();
			var custName = $("#updateModal").find("#custName").val();
			var productId = $("#updateModal").find("#productId").val();
			var aisle = $("#updateModal").find("#aisle").val();
			var productRate=$("#updateModal").find("#productRate").val();
			var rechargeRate=$("#updateModal").find("#rechargeRate").val();
			
			if(kong.test(merchantCode)){
				$.gyzbadmin.alertFailure("商户编号不可为空");
				return;
			}
			/**
			if(kong.test(custName)){
				$.gyzbadmin.alertFailure("商户名称不可为空");
				return;
			}**/
			if(kong.test(productId)){
				$.gyzbadmin.alertFailure("产品编号不可为空");
				return;
			}
			if(kong.test(productRate)){
				$.gyzbadmin.alertFailure("产品费率不可为空");
				return;
			}
			if(kong.test(rechargeRate)){
				$.gyzbadmin.alertFailure("充值费率不可为空");
				return;
			}
			
			
			$.post(window.Constants.ContextPath +"<%=ToPayProductPath.BASE + ToPayProductPath.UPDATEPRODUCTRATE%>",
				{
					'merchantCode':merchantCode,
					'custName':custName,
					'productId':productId,
					'aisle':aisle,
					'productRate':productRate,
					'rechargeRate':rechargeRate
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
							<form action='<c:url value="<%=ToPayProductPath.BASE + ToPayProductPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merchantCode" name="merchantCode" value="${queryBean.merchantCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="custName" name="custName" value="${queryBean.custName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">渠道名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="description" name="description" value="${queryBean.description }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ToPayProductPath.BASE + ToPayProductPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<%-- <gyzbadmin:function url="<%=PayProdPath.BASE + PayProdPath.PAYPRODADD %>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function> --%>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								代付产品列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:10%;">商户编号</th>
											<th style="width:10%;">商户名称</th>
											<th style="width:5%;">产品名称</th>
	
											<th style="width:5%;">渠道名称</th>
											<th style="width:5%;">代付手续费</th>
											<th style="width:5%;">充值手续费</th>
											<th style="width:5%;">充值收费方式</th>
											<th style="width:5%;">产品开通状态</th>
											<th style="width:10%;">创建时间</th>
											<th style="width:10%;">修改时间</th>
											<th style="width:5%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${productList}" var="bean">
											<tr >
												<td id="merchantCode">${bean.merchantCode}</td>
												<td id="custName">${bean.custName}</td>
												
												<td id="productName">${bean.productName}</td>
												<td id="description">${bean.description}</td>
												<td >													
													<span id="productRate"><fmt:formatNumber value="${bean.productRate}" pattern="#0.00" type="number" /></span><span>元/笔</span>
												</td>
												<td >
													<input id="rechargeRate" type="hidden" value="${bean.rechargeRate}">
													<input id="productId" type="hidden" value="${bean.productId}">
													<input id="aisle" type="hidden" value="${bean.aisle}">
													<c:choose>
														<c:when test="${bean.sevenpayRechargeType=='rate'}">  
															<span>万</span><span ><fmt:formatNumber value="${bean.rechargeRate * 10000}" pattern="#0" type="number" /></span>
														</c:when> 
														<c:otherwise>
															<span ><fmt:formatNumber value="${bean.rechargeRate}" pattern="#0.00" type="number" /></span><span>元/笔</span>
														</c:otherwise>
													</c:choose>
			
												</td>
												<td id="sevenpayRechargeType">
													<c:choose>
														<c:when test="${bean.sevenpayRechargeType=='rate'}"><span>费率</span></c:when>
														<c:otherwise><span>固定</span></c:otherwise>
													</c:choose>
													
												</td>
												<td id="productStatus">
													<c:if test="${bean.productStatus =='00'}">
														已开通		
													</c:if>
													<c:if test="${bean.productStatus =='09'}">
														开通失败		
													</c:if>
													<c:if test="${bean.productStatus =='99'}">
														已过期		
													</c:if>
												</td>
													<td id="createTime"><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
													<td id="modifyTime"><fmt:formatDate value="${bean.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
												<td>
													<c:if test="${bean.productStatus =='00'}">
														<gyzbadmin:function url="<%=ToPayProductPath.BASE + ToPayProductPath.LIST%>">
															<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
																<span class="green">
																	<i class="icon-edit bigger-120"></i>
																</span>
															</a>
														</gyzbadmin:function>
													</c:if>
													<!-- <button type="button" onclick="updateProduct" data-toggle='modal' data-target="#updateModal" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >修改产品</button> -->
													<%-- <gyzbadmin:function url="<%=ToPayProductPath.BASE + ToPayProductPath.PAYPRODDELETE %>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function> --%>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty productList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty productList}">
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
	 

		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">产品修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<input type="hidden" id="aisle"/>
							<input type="hidden" id="productId"/>
							<tr>
								<td class="td-left" width="30%">商户编号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="merchantCode" name="merchantCode"  style="width:80%"	readonly="readonly">
								</td>
							</tr>
		            		<tr class="custIdCla">
								<td class="td-left" >商户名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="custName" name="custName"  style="width:80%"	readonly="readonly">
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="productName" name="productName"  style="width:80%" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">渠道名称<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="channelName" name="channelName"  style="width:80%" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">代付手续费<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="productRate" name="productRate"  style="width:80%"	>
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">充值手续费<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="rechargeRate" name="rechargeRate"  style="width:80%"	>
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">充值收费方式<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="sevenpayRechargeType" name="sevenpayRechargeType" readonly="readonly" style="width:80%"	>
								</td>
							</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		        	<input type="hidden"  name="productId" id="productId"/>
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
		
</body>
</html>