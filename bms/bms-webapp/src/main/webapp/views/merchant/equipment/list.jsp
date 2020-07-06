<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<%@page import="com.qifenqian.bms.merchant.equipment.MerchantSignPath"%>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户设备管理</title>
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
	 var merchantSigns= '<c:out value="${gyzb:toJSONString(merchantSignList)}" escapeXml="false"/>';
	 var merchantSignList=$("tr.merchantSign");
	 $.each($.parseJSON(merchantSigns),function(i,value){		 
	 	$.data(merchantSignList[i],"merchantSign",value);
		}); 
	

	
	//新增
	$('.addMerchantSignBtn').click(function(){

		// 商户编码
		var merchantId = $('#addMerchantSignModal #merchantId').val();
		if(kong.test(merchantId)) {
			$.gyzbadmin.alertFailure('商户编码不可为空');
			return;
		}
		// 设备编号
		var terminalNo = $('#addMerchantSignModal #terminalNo').val();
		if(kong.test(terminalNo)) {
			$.gyzbadmin.alertFailure('设备编号不可为空');
			return;
		}
				
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantSignPath.BASE + MerchantSignPath.ADD %>', 
		{
			 'merchantId'		: merchantId,
			 'terminalNo'		: terminalNo
		}, function(data) {
				$.unblockUI();   
				if(data.result == 'SUCCESS'){
					$('#addMerchantSignModal').modal('hide');
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
    $('.editMerchantSign').click(function(){
		var merchantSign = $.data($(this).parent().parent()[0], 'merchantSign');
		var mer = merchantSign.merchantName+'-'+merchantSign.merchantId
				
       $('#editMerchantSignModal').on('show.bs.modal', function () {
    	    $('#editMerchantSignModal #id').val(merchantSign.id);
    	    $('#editMerchantSignModal #merchantId').val(mer);
			$('#editMerchantSignModal #terminalNo').val(merchantSign.terminalNo);
		
		});
       $('#editMerchantSignModal').on('hide.bs.modal', function () {
			// 清除		
			$('#editMerchantSignModal #id').val('');
			$('#editMerchantSignModal #merchantId').val('');
			$('#editMerchantSignModal #terminalNo').val('');
   
		});
	}); 
	
  //保存修改
	$('.editMerchantSignBtn').click(function(){
		
		// 编码
		var id = $('#editMerchantSignModal #id').val();
		if(kong.test(id)) {
			$.gyzbadmin.alertFailure('ID不可为空');
			return;
		}
		// 商户编码
		var merchantId = $('#editMerchantSignModal #merchantId').val();		
		merchantId =merchantId.split("-")[1];
		if(kong.test(merchantId)) {
			$.gyzbadmin.alertFailure('商户编码不可为空');
			return;
		}		
		// 设备编号
		var terminalNo = $('#editMerchantSignModal #terminalNo').val();
		if(kong.test(terminalNo)) {
			$.gyzbadmin.alertFailure('设备编号不可为空');
			return;
		}	


		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantSignPath.BASE + MerchantSignPath.EDIT %>', 
		{ 
			 'id'				:id,
			 'merchantId'		: merchantId,
			 'terminalNo'		: terminalNo
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#editMerchantSignModal').modal('hide');
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
	$('.deleteMerchantSign').click(function(){
		var id = $(this).parent().find('input[name="id"]').val();
		$('#deleteMerchantSignModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteMerchantSignModal span.id').html(id);
			$('#deleteMerchantSignModal #id').val(id);
		});
		$('#deleteMerchantSignModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteMerchantSignModal span.id').empty();
			$('#deleteMerchantSignModal #id').val('');
		});
	});
	
	// 删除商户设备信息
	$('.deleteMerchantSignBtn').click(function(){
		var id = $('#deleteMerchantSignModal #id').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=MerchantSignPath.BASE + MerchantSignPath.DELETE %>', {
				'id' 	: id
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteMerchantSignModal').modal('hide');
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

function getInfo(obj,custId,merchantName){
	
  	var url=window.Constants.ContextPath+"/merchant/equipment/showDeviceLogin?custId="+custId+"&merchantName="+merchantName; 
    var name="window";                        //网页名称，可为空;
    var iWidth=1200;                          //弹出窗口的宽度;
    var iHeight=600;                          //弹出窗口的高度;
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
        +',height='+iHeight
        +',top='+iTop
        +',left='+iLeft;
    /*  $.blockUI();  */
    winChild =  window.open(url, name,params);

}

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
							<form action='<c:url value="<%=MerchantSignPath.BASE + MerchantSignPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">								
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=merchantName  id="merchantName"  value="${merchantSign.merchantName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >设备编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=terminalNo  id="terminalNo"  value="${merchantSign.terminalNo }"><i class="icon-leaf blue"></i>
										</span>
									</td>									
								</tr>

								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addMerchantSign" data-toggle='modal' data-target="#addMerchantSignModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												
																				
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户设备信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>编号</th>
											<th>商户名称</th>
											<th>商户编号</th>
											<th>设备编号</th>
											<th>初始时间</th>																				
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${merchantSignList}" var="merchantSign">
											<tr class="merchantSign">
												<td>${merchantSign.id}</td>
												<td>${merchantSign.merchantName}</td>
												<td>${merchantSign.merchantId}</td>																				
												<td>${merchantSign.terminalNo}</td>										
												<td>
												<fmt:formatDate value="${merchantSign.instDatetime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>									
												<td>
													<input type="hidden" name="id" value="${merchantSign.id }"/>
													<gyzbadmin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.EDIT %>">
													<a href="#" class="tooltip-success editMerchantSign" data-rel="tooltip" data-toggle='modal' data-target="#editMerchantSignModal" title="Edit" >
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
													
													<%-- <a onclick="getInfo(this,'${merchantSign.merchantId}','${merchantSign.merchantName}');" href="#" class="tooltip-success " data-rel="tooltip" data-toggle='modal' data-target="" title="详情" >
														<span class="black">
															<i class="icon-zoom-out bigger-120"></i>
														</span>
													</a>	 --%>
															
													<gyzbadmin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.DELETE %>">
													<a href="#" class="tooltip-success deleteMerchantSign" data-rel="tooltip" data-toggle='modal' data-target="#deleteMerchantSignModal" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>													
													</gyzbadmin:function>
																						
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty merchantSignList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantSignList}">
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
	
					<div class="modal fade" id="addMerchantSignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户设备新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select name="merchantId" id="merchantId" class="selectpicker show-tick form-control" data-width="250px" data-live-search="true">
												<option value="">--输入商户名查询--</option>
												<c:if test="${not empty merchantList }">
						                           	<c:forEach items="${merchantList }" var="bean">
														<option value="${bean.custId }">${bean.custName }-${bean.merchantCode }</option>
													</c:forEach>
					                 		  	</c:if>
											</select>
											<%-- <select id="merchantId" name="merchantId">
												<option value="">输入商户名查询</option>
												<c:forEach items="${merchantList }" var="bean">
													<option value="${bean.custId }">${bean.custName }-${bean.merchantCode }</option>
												</c:forEach>
											</select> --%>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >设备编号<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select id="terminalNo" name="terminalNo">
												<option value="">输入设备编号查询</option>
												<c:forEach items="${materielList }" var="bean">
													<option value="${bean.machineId }">${bean.machineId }</option>
												</c:forEach>
											</select>
										</td>

									</tr>

					
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMerchantSignBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			

			
			<div class="modal fade" id="editMerchantSignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户设备修改</h4>
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
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantId" name="merchantId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>	
									<tr>
										<td class="td-left" >设备编号<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select id="terminalNo" name="terminalNo">
												<option value="">输入设备编号查询</option>
												<c:forEach items="${materielList }" var="bean">
													<option value="${bean.machineId }">${bean.machineId }</option>
												</c:forEach>
											</select>
										</td>
									</tr>	
				
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editMerchantSignBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
						
					<div class="modal fade" id="deleteMerchantSignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户设备信息删除</h4>
					         </div>
					          <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该物料[<span class="id"></span>]么？</font>
					         	<input type="hidden" id="id" />
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMerchantSignBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
				
				
										
			
				
  </body>
</html>