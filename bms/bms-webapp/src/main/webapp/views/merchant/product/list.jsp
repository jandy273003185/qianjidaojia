<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户产品管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>
<script type="text/javascript">


jQuery(function($){
			
	// 为每个tr缓存数据
	 var merchantProducts= '<c:out value="${gyzb:toJSONString(merchantProductList)}" escapeXml="false"/>';
	 var merchantProductList=$("tr.merchantProduct");
	 $.each($.parseJSON(merchantProducts),function(i,value){		 
	 	$.data(merchantProductList[i],"merchantProduct",value);
		}); 
	

	
	//新增
	$('.addMerchantProductBtn').click(function(){
		var merchantId = $('#addMerchantProductModal #merchantId').val();
		// 商户编码
		var merchantCode = $('#addMerchantProductModal #merchantCode').val();
		if(kong.test(merchantCode)) {
			$.gyzbadmin.alertFailure('商户编码不可为空');
			return;
		}
		// 产品编码
		var productId = $('#addMerchantProductModal #productId').val();
		if(kong.test(productId)) {
			$.gyzbadmin.alertFailure('产品编码不可为空');
			return;
		}
		
		// 产品汇率
		var productRate = ($('#addMerchantProductModal #productRate ').val())/100;
		
		// 充值汇率
		var rechargeRate = ($('#addMerchantProductModal #rechargeRate').val())/100;
		//产品开通状态
		/* var productStatus = $('#addMerchantProductModal #productStatus').val();			
		if(kong.test(productStatus)) {
			$.gyzbadmin.alertFailure('产品开通状态不可为空');
			return;
		}	 */
		// 通道
		var aisle = $('#addMerchantProductModal #aisle').val();
		
		
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantProductPath.BASE + MerchantProductPath.ADD %>', 
		{
			 'merchantCode'		: merchantCode,
			 'productId'		: productId,
			 'productRate'		: productRate,
			 'rechargeRate'		: rechargeRate,
			 'aisle'			: aisle
		}, function(data) {
				$.unblockUI();   
				if(data.result == 'SUCCESS'){
					$('#addMerchantProductModal').modal('hide');
					$.gyzbadmin.alertSuccess('新增成功', null, function(){
						window.location.reload();
					});
				}else {
					$.gyzbadmin.alertFailure('保存失败:' + data.message);
				}
			}, 'json'
		);
	});
	
	//弹出修改框
    $('.editMerchantProduct').click(function(){
		var merchantProduct = $.data($(this).parent().parent()[0], 'merchantProduct');
		var mer = merchantProduct.merchantName+'-'+merchantProduct.merchantCode
		var pro = merchantProduct.productName+'-'+merchantProduct.productId
		
       $('#editMerchantProductModal').on('show.bs.modal', function () {
    	    $('#editMerchantProductModal #merchantCode').val(mer);
			$('#editMerchantProductModal #productId').val(pro);
			$('#editMerchantProductModal #productRate').val(merchantProduct.productRate);
			$('#editMerchantProductModal #rechargeRate').val(merchantProduct.rechargeRate);
			//$('#editMerchantProductModal #productStatus').val(merchantProduct.productStatus);
			$('#editMerchantProductModal #aisle').val(merchantProduct.aisle);

			
		});
       $('#editMerchantProductModal').on('hide.bs.modal', function () {
			// 清除				
			$('#editMerchantProductModal #merchantCode').val('');
			$('#editMerchantProductModal #productId').val('');
			$('#editMerchantProductModal #productRate').val('');
			$('#editMerchantProductModal #rechargeRate').val('');
			//$('#editMerchantProductModal #productStatus').val('');
			$('#editMerchantProductModal #aisle').val('');
			$('#editMerchantProductModal #machineId').val('');
	   
		});
	}); 
	
	//弹出审核框
    $('.auditMerchantProduct').click(function(){
		var merchantProduct = $.data($(this).parent().parent()[0], 'merchantProduct');
		var mer = merchantProduct.merchantName+'-'+merchantProduct.merchantCode
		var pro = merchantProduct.productName+'-'+merchantProduct.productId
		
       $('#auditMerchantProductModal').on('show.bs.modal', function () {
    	    $('#auditMerchantProductModal #merchantCode').val(mer);
			$('#auditMerchantProductModal #productId').val(pro);
			$('#auditMerchantProductModal #productRate').val(merchantProduct.productRate);
			$('#auditMerchantProductModal #rechargeRate').val(merchantProduct.rechargeRate);
			//$('#auditMerchantProductModal #productStatus').val(merchantProduct.productStatus);
			$('#auditMerchantProductModal #aisle').val(merchantProduct.aisle);

			
		});
       $('#auditMerchantProductModal').on('hide.bs.modal', function () {
			// 清除				
			$('#auditMerchantProductModal #merchantCode').val('');
			$('#auditMerchantProductModal #productId').val('');
			$('#auditMerchantProductModal #productRate').val('');
			$('#auditMerchantProductModal #rechargeRate').val('');
			//$('#auditMerchantProductModal #productStatus').val('');
			$('#auditMerchantProductModal #aisle').val('');
			$('#auditMerchantProductModal #machineId').val('');
	   
		});
	}); 
	
	//审核
    $('.auditMerchantProductBtn').click(function(){
		// 商户编码
		var merchantCode = $('#auditMerchantProductModal #merchantCode').val();		
		merchantCode =merchantCode.split("-")[1];
		if(kong.test(merchantCode)) {
			$.gyzbadmin.alertFailure('商户编码不可为空');
			return;
		}		
		// 产品编码
		var productId = $('#auditMerchantProductModal #productId').val();
		if(kong.test(productId)) {
			$.gyzbadmin.alertFailure('产品编码不可为空');
			return;
		}	
		productId =productId.split("-")[1];
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantProductPath.BASE + MerchantProductPath.AUDIT %>', 
		{ 
			 'merchantCode'		: merchantCode,
			 'productId'		: productId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#auditMerchantProductModal').modal('hide');
					$.gyzbadmin.alertSuccess('审核成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('审核失败:' + data.message);
				}
			}, 'json'
		);
});
	
  //保存修改
	$('.editMerchantProductBtn').click(function(){
				
		// 商户编码
		var merchantCode = $('#editMerchantProductModal #merchantCode').val();		
		merchantCode =merchantCode.split("-")[1];
		if(kong.test(merchantCode)) {
			$.gyzbadmin.alertFailure('商户编码不可为空');
			return;
		}		
		// 产品编码
		var productId = $('#editMerchantProductModal #productId').val();
		if(kong.test(productId)) {
			$.gyzbadmin.alertFailure('产品编码不可为空');
			return;
		}	
		productId =productId.split("-")[1];
		//产品汇率
		var productRateStr = $('#editMerchantProductModal #productRate').val() ;	
		
		//充值汇率
		var rechargeRateStr = $('#editMerchantProductModal #rechargeRate').val();
		//开通状态
		var productStatus = $('#editMerchantProductModal #productStatus').val();
		if(kong.test(productStatus)) {
			$.gyzbadmin.alertFailure('产品开通状态不可为空');
			return;
		}		
		// 渠道
		var aisle = $('#editMerchantProductModal #aisle').val();

		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantProductPath.BASE + MerchantProductPath.EDIT %>', 
		{ 
			 'merchantCode'		: merchantCode,
			 'productId'		: productId,
			 'productRateStr'	: productRateStr,
			 'rechargeRateStr'	: rechargeRateStr, 
			 'productStatus'	: productStatus,
			 'aisle'			: aisle
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#editMerchantProductModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
				}
			}, 'json'
		);
	});
  
	// 弹出删除层准备工作
	$('.deleteMerchantProduct').click(function(){
		var merchantProduct = $.data($(this).parent().parent()[0], 'merchantProduct');
		
		$('#deleteMerchantProductModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteMerchantProductModal #merchantCode').val(merchantProduct.merchantCode);
			$('#deleteMerchantProductModal #productCode').val(merchantProduct.productCode);
			$('#deleteMerchantProductModal #productId').val(merchantProduct.productId);
		});
		$('#deleteMerchantProductModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteMerchantProductModal #merchantCode').val('');
			$('#deleteMerchantProductModal #productCode').val('');
			$('#deleteMerchantProductModal #productId').val('');
		});
	});
	
	// 删除商户产品信息
	$('.deleteMerchantProductBtn').click(function(){
		var productCode = $('#deleteMerchantProductModal #productCode').val();
		var merchantCode = $('#deleteMerchantProductModal #merchantCode').val();
		var productId = $('#deleteMerchantProductModal #productId').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantProductPath.BASE + MerchantProductPath.DELETE %>', 
			{
				'merchantCode' 	: merchantCode,
				'productCode' 	: productCode,
				'productId' 	: productId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteMerchantProductModal').modal('hide');
					$.gyzbadmin.alertSuccess('删除成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('删除失败:' + data.message);
				}
			}, 'json'
		);
	});
	
	



});



</script>	

<body>
	<!-- 物料信息 -->
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
							<form action='<c:url value="<%=MerchantProductPath.BASE + MerchantProductPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=merchantName  id="merchantName"  value="${merchantProduct.merchantName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >产品名称：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=productName  id="productName"  value="${merchantProduct.productName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >产品开通状态：</td>
										<td class="td-right" > 
										<span class="input-icon">
											<select name="productStatus" id="productStatus" >
											  <option  value="" >请选择 </option>
											  <option  value="00">开通成功 </option>
											  <option  value="09">开通失败</option>
											  <option  value="99">未开通 </option>
										 	</select>
										</span>
									</td>
								</tr>

								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addMerchantProduct" data-toggle='modal' data-target="#addMerchantProductModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												
																				
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户产品信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>商户名称</th>
											<th>产品名称</th>
											<th>商户代码</th>
											<th>产品汇率(%)</th>
											<th>充值汇率</th>
											<th>产品开通状态</th>
											<th>审核人</th>
											<th>渠道</th>
											<th>创建时间</th>
											<th>修改时间</th>											
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${merchantProductList}" var="merchantProduct">
											<tr class="merchantProduct">
												<td>${merchantProduct.merchantName}</td>
												<td>${merchantProduct.productName}</td>
												<td>${merchantProduct.merchantCode}</td>																				
												<td>${merchantProduct.productRate}*100</td>
												<td>${merchantProduct.rechargeRate}</td>
												<td>
													<c:if test="${merchantProduct.productStatus =='00'}">
														开通成功
													</c:if>
													<c:if test="${merchantProduct.productStatus =='01'}">
														待审核
													</c:if>
													<c:if test="${merchantProduct.productStatus =='09'}">
														开通失败
													</c:if>
													<c:if test="${merchantProduct.productStatus =='99'}">
														未开通
													</c:if>
												
												</td>
												<td>${merchantProduct.realName}</td>	
												<td>${merchantProduct.aisle}</td>	
												<td>
												<fmt:formatDate value="${merchantProduct.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
												<fmt:formatDate value="${merchantProduct.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>									
												<td>
													<input type="hidden" name="id" value="${materiel.id }"/>
													<c:if test="${merchantProduct.productStatus !='00'}">
														<gyzbadmin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.EDIT %>">
														<a href="#" class="tooltip-success editMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#editMerchantProductModal" title="Edit" >
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
														</gyzbadmin:function>
													</c:if>
													
													<c:if test="${merchantProduct.productStatus !='00'}">
														<a href="#" class="tooltip-success auditMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#auditMerchantProductModal" title="Audit" >
															<span class="green">
																<i class="icon-check bigger-120"></i>
															</span>
														</a>
													</c:if>
													
													<gyzbadmin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.DELETE %>">
													<a href="#" class="tooltip-success deleteMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#deleteMerchantProductModal" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>													
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty merchantProductList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantProductList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
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
					<div class="modal fade" id="addMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 700px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%">
											<select name="merchantCode" id="merchantCode" class="selectpicker show-tick form-control" data-width="250px" data-live-search="true">
												<option value="">--输入商户名查询--</option>
												<c:if test="${not empty merchantList }">
													<c:forEach items="${merchantList }" var="bean">
														<option value="${bean.merchantCode }">${bean.custName }-${bean.merchantCode }</option>
													</c:forEach>
												</c:if>
											</select>
											<%--<select id="merchantCode" name="merchantCode">
												<option value="">输入商户名查询</option>
												<c:forEach items="${merchantList }" var="bean">
													<option value="${bean.merchantCode }">${bean.custName }-${bean.merchantCode }</option>
												</c:forEach>
											</select>--%>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select id="productId" name="productId">
												<option value="">输入产品名查询</option>
												<c:forEach items="${productList }" var="bean">
													<option value="${bean.productId }">${bean.productName }</option>												
												</c:forEach>
											</select>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品汇率(%)</td>
										<td class="td-right" >
												<input type="text" id="productRate" name="productRate" clasS="width-90"/> 										
											
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >充值费率</td>
										<td class="td-right" >
												<input type="text" id="rechargeRate" name="rechargeRate" clasS="width-90"/>
										</td>
									</tr>
																													
									<!-- <tr>
										<td class="td-left" >产品开通状态<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="productStatus" name="productStatus" clasS="width-90"/>
													<option value="">选择产品开通状态</option>
													<option value="00">开通成功</option>
													<option value="09">开通失败</option>
													<option value="99">未开通</option>
												</select>
										</td>
									</tr>	 -->
									<!-- <tr>
										<td class="td-left" >渠道</td>
										<td class="td-right" >
												<input type="text" id="aisle" name="aisle" clasS="width-90"/>
										</td>
									</tr>	 -->
					
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMerchantProductBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			
			
						<div class="modal fade" id="auditMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品审核</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品汇率</td>
										<td class="td-right" >
												<input type="text" id="productRate" readonly="readonly" name="productRate" clasS="width-90"/> 										
											
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >充值费率</td>
										<td class="td-right" >
												<input type="text" id="rechargeRate" readonly="readonly" name="rechargeRate" clasS="width-90"/>
										</td>
									</tr>
																													
									<!-- <tr>
										<td class="td-left" >渠道</td>
										<td class="td-right" >
												<input type="text" id="aisle" name="aisle" clasS="width-90"/>
										</td>
									</tr> -->	
				
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary auditMerchantProductBtn">审核通过</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div>
			
			<div class="modal fade" id="editMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品汇率</td>
										<td class="td-right" >
												<input type="text" id="productRate" name="productRate" clasS="width-90"/> 										
											
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >充值费率</td>
										<td class="td-right" >
												<input type="text" id="rechargeRate" name="rechargeRate" clasS="width-90"/>
										</td>
									</tr>
																													
									<!-- <tr>
										<td class="td-left" >产品开通状态<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="productStatus" name="productStatus" clasS="width-90"/>
													<option value="">选择产品开通状态</option>
													<option value="00">开通成功</option>
													<option value="09">开通失败</option>
													<option value="99">未开通</option>
												</select>
										</td>
									</tr>	 -->
									<!-- <tr>
										<td class="td-left" >渠道</td>
										<td class="td-right" >
												<input type="text" id="aisle" name="aisle" clasS="width-90"/>
										</td>
									</tr> -->	
				
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editMerchantProductBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
						
					<div class="modal fade" id="deleteMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品编号<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productCode" name="productCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品编号</td>
										<td class="td-right" >
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>											
								        </td>
									</tr>
							
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMerchantProductBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
				
				
										
			
				
  </body>
</html>