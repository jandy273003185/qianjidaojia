<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
<%@page import="com.qifenqian.bms.materiel.MaterielPath"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>物料管理</title>
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
	var materiels= '<c:out value="${gyzb:toJSONString(materielList)}" escapeXml="false"/>';
	var materielList=$("tr.materiel");
	$.each($.parseJSON(materiels),function(i,value){
		$.data(materielList[i],"materiel",value);
	});
	
		

	
	//新增
	$('.addMaterielBtn').click(function(){
		
		// 设备编号
		var machineId = $('#addMaterielModal #machineId').val();
		if(kong.test(machineId)) {
			$.gyzbadmin.alertFailure('机器编码不可为空');
			return;
		}
		
		// 设备类型
		var machineType = $('#addMaterielModal #machineType').val();
		
		// 领取人
		var receiver = $('#addMaterielModal #receiver').val();
		
		// 所用商户
		var usedMerchant = $('#addMaterielModal #usedMerchant').val();
		
		// 所用门店
		var usedStores = $('#addMaterielModal #usedStores').val();
		
		// 所属服务商
		var serviceParenterName = $('#addMaterielModal #serviceParenterName').val();
		
		// 供应商
		var supplier = $('#addMaterielModal #supplier').val();
		
		if(kong.test(supplier)) {
			$.gyzbadmin.alertFailure('供应商不可为空');
			return;
		}
		
		//机器状态
		var machineState = $('#addMaterielModal #machineState').val();			
		if(kong.test(machineState)) {
			$.gyzbadmin.alertFailure('机器状态不可为空');
			return;
		}
		
		// 说明
		var memo = $('#addMaterielModal #memo').val();
		
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MaterielPath.BASE + MaterielPath.ADD %>', 
		{
			 'machineId'	: machineId,
			 'machineType'	: machineType,
			 'receiver'		: receiver,
			 'usedMerchant'	: usedMerchant,
			 'usedStores'	: usedStores,
			 'serviceParenterName': serviceParenterName,
			 'supplier'		: supplier,
			 'machineState'	: machineState,
			 'memo'			: memo
		}, function(data) {
				$.unblockUI();   
				if(data.result == 'SUCCESS'){
					$('#addMaterielModal').modal('hide');
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
    $('.editMateriel').click(function(){
		var materiel = $.data($(this).parent().parent()[0], 'materiel');
       $('#editMaterielModal').on('show.bs.modal', function () {
    	    $('#editMaterielModal #id').val(materiel.id);
			$('#editMaterielModal #machineId').val(materiel.machineId);
			$('#editMaterielModal #machineType').val(materiel.machineType);
			$('#editMaterielModal #receiver').val(materiel.receiver);
			$('#editMaterielModal #usedMerchant').val(materiel.usedMerchant);
			$('#editMaterielModal #usedStores').val(materiel.usedStores);
			$('#editMaterielModal #serviceParenterName').val(materiel.serviceParenterName);
			$('#editMaterielModal #supplier').val(materiel.supplier);
			$('#editMaterielModal #machineState').val(materiel.machineState);
			$('#editMaterielModal #memo').val(materiel.memo);			
		});
       $('#editMaterielModal').on('hide.bs.modal', function () {
			// 清除			
			$('#editMaterielModal #id').val('');
			$('#editMaterielModal #machineId').val('');
			$('#editMaterielModal #machineType').val('');
			$('#editMaterielModal #receiver').val('');
			$('#editMaterielModal #usedMerchant').val('');
			$('#editMaterielModal #usedStores').val('');
			$('#editMaterielModal #serviceParenterName').val('');
			$('#editMaterielModal #supplier').val('');
			$('#editMaterielModal #machineState').val('');
			$('#editMaterielModal #memo').val('');    	   
		});
	}); 
	
  //保存修改
	$('.editMaterielBtn').click(function(){
		
		// 编码
		var id = $('#editMaterielModal #id').val();
		if(kong.test(id)) {
			$.gyzbadmin.alertFailure('物料编码不可为空');
			return;
		}
		
		// 设备编码
		var machineId = $('#editMaterielModal #machineId').val();
		if(kong.test(machineId)) {
			$.gyzbadmin.alertFailure('机器编码不可为空');
			return;
		}
		
		// 设备类型
		var machineType = $('#editMaterielModal #machineType').val();
		
		// 领取人
		var receiver = $('#editMaterielModal #receiver').val();
		
		// 所用商户
		var usedMerchant = $('#editMaterielModal #usedMerchant').val();
		
		// 所用门店
		var usedStores = $('#editMaterielModal #usedStores').val();
		
		// 所属服务商
		var serviceParenterName = $('#editMaterielModal #serviceParenterName').val();
		
		// 供应商
		var supplier = $('#editMaterielModal #supplier').val();
		if(kong.test(supplier)) {
			$.gyzbadmin.alertFailure('供应商不可为空');
			return;
		}
		
		//机器状态
		var machineState = $('#editMaterielModal #machineState').val();			
		if(kong.test(machineState)) {
			$.gyzbadmin.alertFailure('机器状态不可为空');
			return;
		}
		
		// 说明
		var memo = $('#editMaterielModal #memo').val();
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MaterielPath.BASE + MaterielPath.EDIT %>', 
		{ 
			 'id'			:id,
			 'machineId'	: machineId,
			 'machineType'	: machineType,
			 'receiver'		: receiver,
			 'usedMerchant'	: usedMerchant,
			 'usedStores'	: usedStores,
			 'serviceParenterName' : serviceParenterName,
			 'supplier'		: supplier,
			 'machineState'	: machineState,
			 'memo'			: memo
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#editMaterielModal').modal('hide');
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
	$('.deleteMateriel').click(function(){
		var id = $(this).parent().find('input[name="id"]').val();
		$('#deleteMaterielModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteMaterielModal span.id').html(id);
			$('#deleteMaterielModal #id').val(id);
		});
		$('#deleteMaterielModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteMaterielModal span.id').empty();
			$('#deleteMaterielModal #id').val('');
		});
	});
	
	// 删除物料信息
	$('.deleteMaterielBtn').click(function(){
		var id = $('#deleteMaterielModal #id').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MaterielPath.BASE + MaterielPath.DELETE %>', {
				'id' 	: id
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteMaterielModal').modal('hide');
					$.gyzbadmin.alertSuccess('删除成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('删除失败:' + data.message);
				}
			}, 'json'
		);
	});
	
	//导入excel文件
	$("#saveZipButton").on('click', function(){			
		  var formData = new FormData();
		    var name = $("#articleImageFile").val();
		    formData.append("file",$("#articleImageFile")[0].files[0]);
		    formData.append("name",name);//这个地方可以传递多个参数
		    $.ajax({
		        url :  window.Constants.ContextPath + '<%=MaterielPath.BASE + MaterielPath.FILEUPLOAD %>',
		        type : 'POST',
		        async : false,
		        data : formData,
		        // 告诉jQuery不要去处理发送的数据
		        processData : false,
		        // 告诉jQuery不要去设置Content-Type请求头
		        contentType : false,		        
		        success : function(data) {
					if(data.result == 'SUCCESS'){						
						$.gyzbadmin.alertSuccess('导入成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('导入失败:' + data.message);
					}
		           
		        }
		    });
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
					
						<div class="form-group" id="thumbnailUploadContainer" class="col-sm-10" style="float: left; margin-right: 50px;">
							<input id="articleImageFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
							<input id="saveZipButton" type="button" style="width: 120px;height: 30px;" value="批量导入物料信息" />
						</div>

						<div class="col-xs-12">

						<!-- 查询条件 -->
							<form action='<c:url value="<%=MaterielPath.BASE + MaterielPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >机器编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=machineId  id="machineId"  value="${materiel.machineId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >领用人：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=receiver  id="receiver"  value="${materiel.receiver }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >所用商户：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=usedMerchant  id="usedMerchant"  value="${materiel.usedMerchant }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >所属服务商：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=serviceParenterName  id="serviceParenterName"  value="${materiel.serviceParenterName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >机器状态：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<select name="machineState" id="machineState" >
											  <option  value="" >请选择 </option>
											  <option  value="0">已领用 </option>
											  <option  value="1">未领用</option>
											  <option  value="2">已激活 </option>
											  <option  value="3">未激活</option>
										 	</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="<%=MaterielPath.BASE + MaterielPath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="<%=MaterielPath.BASE + MaterielPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addMateriel" data-toggle='modal' data-target="#addMaterielModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												
																				
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">物料信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>编号</th>
											<th>设备编号</th>
											<th>设备类型</th>
											<th>领取人</th>
											<th>所用商户</th>
											<th>所用门店</th>
											<th>所属服务商</th>
											<th>供应商</th>
											<th>机器状态</th>
											<th>创建人</th>
											<th>创建时间</th>											
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${materielList}" var="materiel">
											<tr class="materiel">
												<td>${materiel.id}</td>
												<td>${materiel.machineId}</td>
												<td>${materiel.machineType}</td>
												<td>${materiel.receiver}</td>
												<td>${materiel.usedMerchant}</td>									
												<td>${materiel.usedStores}</td>	
												<td>${materiel.serviceParenterName}</td>									
												<td>${materiel.supplier}</td>											
												<td>
													<c:if test="${materiel.machineState =='0'}">
														已领用
													</c:if>
													<c:if test="${materiel.machineState =='1'}">
														未领用
													</c:if>
													<c:if test="${materiel.machineState =='2'}">
														已激活
													</c:if>
													<c:if test="${materiel.machineState =='3'}">
														未激活
													</c:if>
												</td>
												<td>${materiel.creator}</td>
												<td>
												<fmt:formatDate value="${materiel.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>											
												<td>${materiel.memo}</td>
												<td>
													<input type="hidden" name="id" value="${materiel.id }"/>
													<gyzbadmin:function url="<%=MaterielPath.BASE + MaterielPath.EDIT %>">
													<a href="#" class="tooltip-success editMateriel" data-rel="tooltip" data-toggle='modal' data-target="#editMaterielModal" title="Edit" >
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
													
													<gyzbadmin:function url="<%=MaterielPath.BASE + MaterielPath.DELETE %>">
													<a href="#" class="tooltip-success deleteMateriel" data-rel="tooltip" data-toggle='modal' data-target="#deleteMaterielModal" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>													
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty materielList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty materielList}">
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
					<div class="modal fade" id="addMaterielModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">物料新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="25%">设备编号<span style="color: red">*</span></td>
										<td class="td-right" width="75%">
												<input type="text" id="machineId" name="machineId" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="25%">设备类型</td>
										<td class="td-right" width="75%">
												<input type="text" id="machineType" name="machineType" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>
										<td class="td-left" >领取人</td>
										<td class="td-right" >
												<input type="text" id="receiver" name="receiver" clasS="width-90"/> 										
											<%-- <select id="receiver" name="receiver">
		         								<option value="">输入所属业务人员或所属业务人员查询</option>
		         										<c:forEach items="${userlist }" var="bean"><option value="${bean.userName }">${bean.userName }</option>
										         </c:forEach>
									        </select> --%>
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >所用商户</td>
										<td class="td-right" >
												<input type="text" id="usedMerchant" name="usedMerchant" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >所用门店</td>
										<td class="td-right" >
												<input type="text" id="usedStores" name="usedStores" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >所属服务商</td>
										<td class="td-right" >
												<input type="text" id="serviceParenterName" name="serviceParenterName" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >供应商<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="supplier" name="supplier" clasS="width-90"/>
										</td>
									</tr>
																										
								
									<tr>
										<td class="td-left" >机器状态<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="machineState" name="machineState" clasS="width-90"/>
													<option value="">选择机器状态</option>
													<option value="0">已领用</option>
													<option value="1">未领用</option>
													<option value="2">已激活</option>
													<option value="3">未激活</option>
												</select>
										</td>
									</tr>
							
									<tr>
										<td class="td-left" >备注</td>
										<td class="td-right" >
												<textarea rows="2" cols="" id="memo" name="memo" clasS="width-90"/></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMaterielBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			

			
			<div class="modal fade" id="editMaterielModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">物料修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="25%">编号<span style="color: red">*</span></td>
										<td class="td-right" width="75%">
											<input type="text" id="id" name="id" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
					            
					            	<tr>
										<td class="td-left" width="25%">设备编号<span style="color: red">*</span></td>
										<td class="td-right" width="75%">
												<input type="text" id="machineId" name="machineId" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="25%">设备类型</td>
										<td class="td-right" width="75%">
												<input type="text" id="machineType" name="machineType" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >领取人</td>
										<td class="td-right" >
												<input type="text" id="receiver" name="receiver" clasS="width-90"/>									
											<%-- <select id="receiver" name="receiver">
		         								<option value="">输入所属业务人员或所属业务人员查询</option>
		         										<c:forEach items="${userlist }" var="bean"><option value="${bean.userName }">${bean.userName }</option>
										         </c:forEach>
									        </select> --%>
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >所用商户</td>
										<td class="td-right" >
												<input type="text" id="usedMerchant" name="usedMerchant" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >所用门店</td>
										<td class="td-right" >
												<input type="text" id="usedStores" name="usedStores" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >所属服务商</td>
										<td class="td-right" >
												<input type="text" id="serviceParenterName" name="serviceParenterName" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >供应商<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="supplier" name="supplier" clasS="width-90"/>
										</td>
									</tr>
																										
								
									<tr>
										<td class="td-left" >机器状态<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="machineState" name="machineState" clasS="width-90"/>
													<option value="">选择机器状态</option>
													<option value="0">已领用</option>
													<option value="1">未领用</option>
													<option value="2">已激活</option>
													<option value="3">未激活</option>
												</select>
										</td>
									</tr>
							
									<tr>
										<td class="td-left" >备注</td>
										<td class="td-right" >
												<textarea rows="2" cols="" id="memo" name="memo" clasS="width-90"/></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editMaterielBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
						
					<div class="modal fade" id="deleteMaterielModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">物料信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该物料[<span class="id"></span>]么？</font>
					         	<input type="hidden" id="id" />
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMaterielBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
				
				
										
			
				
  </body>
</html>