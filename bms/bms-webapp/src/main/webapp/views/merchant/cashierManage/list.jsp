<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.CashierManagePath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>商户收银员维护</title>
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> 
<script src="<c:url value='/static/js/jquery-1.11.3.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
function loadQueryBean(){
	$("#merchantCustIdQr").val($("#merchantIdHidden").val());
	$("#queryForm").find(".combo-input").val($("#merchantCustIdQr option:selected").text());
}
$(document).ready(function(){
	$("#merchantCustIdQr").comboSelect();
  //  $("#shopId").comboSelect();  
	$("#merchantCustId").comboSelect();
	$(".clearQuery").click(function(){
		$("#merchantCustIdQr,#cashierMobile").val('');
	});



	
    $("#merchantCustId").change(function(){
	  	var custId = $(this).val();
	  	$("#shopId").html("");
	  	var obj =$(this);
	  	$.post(window.Constants.ContextPath+'/merchant/cashierManage/check', {
			'custId':custId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					/* obj.parents("tr").next().find("td[class='td-right']").append("<div class= combo-select combo-open>" + "<select id=" + obj.id 
						+ "name=" +obj.name +"tabindex ="+ -1+ "><option value="+ ${bean.custId } + "></option>"
						+ "<div class = combo-arrow></div>"+ "<ul class=combo-dropdown>" + "<li class = option-item"+"data-index = " +"data-value>="
						+ "</li></ul>" + "<input type=text placeholder = "+ "输入商户名或客户名查询" + "class = combo-input text-input>" 
						+ "</div>"); */
					 for(var i=0;i<data.storeManageList.length;i++){
						$("#shopId").append("<option value='"+data.storeManageList[i].shopId+"'>"+data.storeManageList[i].shopName+"</option>");
					}
					
					 				
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
	     );
	  });
	 	   
	$(".addCashierBtn").click(function(){

		var cashierMobile = $(this).parents('#addCashierModal').find("input[name='cashierMobile']").val();
		var cashierName =  $(this).parents('#addCashierModal').find('#cashierName').val();
		var merchantCustId = $(this).parents('#addCashierModal').find('#merchantCustId').val();
		var shopId = $(this).parents('#addCashierModal').find('#shopId').val();
		var refundAuth = $(this).parents('#addCashierModal').find('#refundAuth').val();
		var queryAuth = $(this).parents('#addCashierModal').find('#queryAuth').val();
		var custId = $(this).parents('#addCashierModal').find('#custId').val();
		var loginPw = $(this).parents('#addCashierModal').find('#loginPw').val();
		var refundPw = $(this).parents('#addCashierModal').find('#refundPw').val();
		if(cashierMobile==null||trim(cashierMobile)==''){
			$.gyzbadmin.alertFailure("手机号不能为空！");
			return;
		}

		if(merchantCustId==null||trim(merchantCustId)==''){
			$.gyzbadmin.alertFailure("商户不能为空！");
			return;
		}
		if(shopId==null||trim(shopId)==''){
			$.gyzbadmin.alertFailure("门店ID不能为空！");
			return;
		}
		if(loginPw==null||trim(loginPw)==''){
			$.gyzbadmin.alertFailure("登录密码不能为空！");
			return;
		}
		if(refundPw==null||trim(refundPw)==''){
			$.gyzbadmin.alertFailure("退款密码不能为空！");
			return;
		}
		$.post(window.Constants.ContextPath+'/merchant/cashierManage/add', {
			'cashierMobile':cashierMobile,
			'cashierName':cashierName,
			'merchantCustId':merchantCustId,
			'shopId':shopId,
			'refundAuth':refundAuth,
			'queryAuth' : queryAuth,
			'loginPw' : loginPw,
			'refundPw' : refundPw
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addCashierModal").modal("hide");
					},function(){
						window.location.reload();
					});
					
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
		); 
	});

	$(".updateCashier").click(function(){
		var cashierMobile = $(this).parents('tr').find("#cashierMobile").text();
		var cashierName =  $(this).parents('tr').find("#cashierName").text();
		var onlyId = $(this).parents('tr').find('#onlyId').val();
		var merchantCustId = $(this).parents('tr').find('#merchantCustIdU').val();
		var shopId = $(this).parents('tr').find('#shopId').val();
		var refundAuth = $(this).parents('tr').find("#refundAuth").val();
		var queryAuth = $(this).parents('tr').find('#queryAuth').val();
		var merchantName = $(this).parents('tr').find('#merchantName').val();
		var shopName = $(this).parents('tr').find('#shopName').val();
		var status = $(this).parents('tr').find('#status').val();

		$("#updateModal").find("#merchantName").val(merchantName);
		$("#updateModal").find("#shopName").val(shopName);
		$("#updateModal").find("#cashierMobile").val(cashierMobile);
		$("#updateModal").find("#cashierName").val(cashierName);
		$("#updateModal").find("#onlyId").val(onlyId);
		$("#updateModal").find("#refundAuth").val(refundAuth);
		$("#updateModal").find("#queryAuth").val(queryAuth);
		$("#updateModal").find("#merchantCustIdU").val(merchantCustId);
		$("#updateModal").find("#shopId").val(shopId);
		$("#updateModal").find("#status").val(status);

	});
	
	$(".updateCashierBtn").click(function(){
		var cashierMobile = $(this).parents('#updateModal').find("#cashierMobile").val();
		var cashierName =  $(this).parents('#updateModal').find("#cashierName").val();
		var onlyId = $(this).parents('#updateModal').find('#onlyId').val();
		var merchantCustId =  $(this).parents('#updateModal').find("#merchantCustIdU").val();
		var shopId = $(this).parents('tr').find('#shopId').val();
		var refundAuth = $(this).parents('#updateModal').find("#refundAuth").val();
		var queryAuth = $(this).parents('#updateModal').find("#queryAuth").val();
		var status = $(this).parents('#updateModal').find("#status").val();
		if(cashierMobile==null||trim(cashierMobile)==''){
			$.gyzbadmin.alertFailure("手机号不能为空！");
			return;
		}

		
		$.post(window.Constants.ContextPath+'/merchant/cashierManage/update', {
			'cashierMobile':cashierMobile,
			'cashierName':cashierName,
			'onlyId':onlyId,
			'queryAuth' :queryAuth,	
			'merchantCustId':merchantCustId,
			'shopId':shopId,
			'status':status,
			'refundAuth':refundAuth
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$.gyzbadmin.alertSuccess("修改成功！",function(){
						$("#updateModal").modal("hide");
					},function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
		); 
	});

	
	$(".deleteCashier").click(function(){
		var onlyId = $(this).parents("tr").find("input[name='onlyId']").val();
		$('#deleteCashierModal').find("#onlyId").val(onlyId);
	});
	
	
	$(".deleteCashierBtn").click(function(){
		var onlyId =  $(this).parents('#deleteCashierModal').find('#onlyId').val();
		$.post(window.Constants.ContextPath+'/merchant/cashierManage/delete', {
			'onlyId':onlyId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteCashierModal").modal("hide");
					},function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
		);  
	});


});	
</script>
<body onload="loadQueryBean()">
	
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
							<form action='<c:url value="<%=CashierManagePath.BASE + CashierManagePath.LIST %>"/>' method="post" id="queryForm">
							<table class="search-table">
								<tr>
									<input type="hidden" id="merchantIdHidden" value="${queryBean.merchantCustId }"/>
									<td class="td-left">商户</td>
									<td class="td-right">
										<select id="merchantCustIdQr" name="merchantCustId">
											<option value="">输入商户名或客户号查询</option>
											<c:forEach items="${merchantList }" var="bean">
												<option value="${bean.custId }">${bean.custName }</option>
											</c:forEach>
										</select>
									</td>
									
									<td class="td-left">收银员手机号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="cashierMobile" value="${queryBean.cashierMobile }" id="cashierMobile">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CashierManagePath.BASE + CashierManagePath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearQuery"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
											<gyzbadmin:function url="<%=CashierManagePath.BASE + CashierManagePath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addCashierModal">
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
								商户收银员维护
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>商户</th>	
											<th>门店名称</th> 								
											<th>收银员手机号</th>
											<th>收银员姓名</th>
											<th>是否有退款权限</th>
											<!-- <th>是否有全门店权限</th> --> 
											<th>是否有效</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${cashierInfoList}" var="result">
											<tr class="result">
												<input type="hidden" name="merchantCustIdU" id="merchantCustIdU" value="${result.merchantCustId }"/>
												<input type="hidden" name="onlyId" id="onlyId" value="${result.onlyId }"/>
												<input type="hidden" name="refundAuth" id="refundAuth" value="${result.refundAuth }"/>
												<input type="hidden" name="queryAuth" id="queryAuth" value="${result.queryAuth }"/>
												<input type="hidden" name="merchantName" id="merchantName" value="${result.merchantName }"/>
												<input type="hidden" name="shopName" id="shopName" value="${result.shopName }"/>
												<input type="hidden" name="status" id="status" value="${result.status }"/>
												<td >${result.merchantName }</td>
												<td >${result.shopName }</td> 
												<td id="cashierMobile">${result.cashierMobile }</td>
												<td id="cashierName">${result.cashierName }</td>
												<td >
													<c:choose>
														<c:when test="${result.refundAuth=='0' }">
															否
														</c:when>
														
														<c:when test="${result.refundAuth=='1' }">
															是
														</c:when>
													</c:choose>
													
												</td>
												 <%-- <td >
													<c:choose>
														<c:when test="${result.queryAuth=='0' }">
															否
														</c:when>
														
														<c:when test="${result.queryAuth=='1' }">
															是
														</c:when>
													</c:choose>
													
												</td> --%>
												<td>
													<c:choose>
														<c:when test="${result.status=='0' }">
															无效
														</c:when>

														<c:when test="${result.status=='1' }">
															有效
														</c:when>
													</c:choose>

												</td>
												<td>
													${result.createTime }
<%--													<fmt:formatDate value="${result.createTime }" pattern="yyyy-MM-dd HH:mm"/>--%>
												</td>
												<td>
													<gyzbadmin:function url="<%=CashierManagePath.BASE + CashierManagePath.UPDATE%>">
														<a href="#" class="tooltip-success updateCashier" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=CashierManagePath.BASE + CashierManagePath.DELETE%>">
														<a href="#deleteCashierModal" data-toggle='modal' class="tooltip-error deleteCashier" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty cashierInfoList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty cashierInfoList}">
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
	<!-- 添加商户配置 -->  
	<div class="modal fade" id="addCashierModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加收银员</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
		            	<tr>
							<td class="td-left">商户<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="merchantCustId" name="merchantCustId" >
									<option value="">输入商户名或客户号查询</option>
									<c:forEach items="${merchantList }" var="bean">
										<option value="${bean.custId }">${bean.custName }</option>	
									</c:forEach>									
								</select>			
							</td>
						</tr>
						  <tr>
							<td class="td-left">门店<span style="color:red"></span></td>
							<td class="td-right">
								 <select id="shopId" name="shopId">
									  <option value="">输入门店查询</option> 
								<!--<c:forEach items="${storeManageList }" var="bean">
										<option value="${bean.shopId }">${bean.shopId }</option>
									</c:forEach>-->
								</select>
							</td>
						</tr>  
						
						
						<%--
						 <tr>	
							<td class="td-left" width="30%">门店ID<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="shopId" name="shopId"  clasS="width-90">
								<label id="cashierMobile" class="label-tips"></label>
							</td>
						</tr> --%>
						
						<tr>	
							<td class="td-left" width="30%">手机号<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="cashierMobile" name="cashierMobile"  clasS="width-90">
								<label id="cashierMobile" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">姓名</td>
							<td class="td-right">
								<input type="text" id="cashierName" name="cashierName"  clasS="width-90">
								<label id="cashierName" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">是否有退款权限</td>
							<td class="td-right">
								
								<select id="refundAuth" name="refundAuth">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<label id="refundAuth" class="label-tips"></label>
							</td>
						</tr>
						<!-- <tr>
							<td class="td-left">是否有全门店权限</td>
							<td class="td-right">
								<select id="queryAuth" name="queryAuth">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<label id="queryAuth" class="label-tips"></label>
							</td>
						</tr> -->
						<tr>
							<td class="td-left">登录密码<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="loginPw" name="loginPw"  clasS="width-90" value="123456">
								<label id="loginPw" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">退款密码<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="refundPw" name="refundPw"  clasS="width-90" value="123456">
								<label id="refundPw" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addCashierBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改收银员</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
		            	<input type="hidden" id="onlyId" />
		            	<input type="hidden" id="merchantCustIdU">
		            	<tr>
							<td class="td-left">商户</td>
							<td class="td-right">
								<input type="text" id="merchantName" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店</td>
							<td class="td-right">
								<input type="text" id="shopName" readonly="readonly"/>
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="30%">手机号<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="cashierMobile" name="cashierMobile"  clasS="width-90">
								<label id="cashierMobile" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">姓名</td>
							<td class="td-right">
								<input type="text" id="cashierName" name="cashierName"  clasS="width-90">
								<label id="cashierName" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">是否有退款权限</td>
							<td class="td-right">
								
								<select id="refundAuth" name="refundAuth">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<label id="refundAuth" class="label-tips"></label>
							</td>
						</tr>
						<!-- <tr>
							<td class="td-left">是否有全门店权限</td>
							<td class="td-right">
								
								<select id="queryAuth" name="queryAuth">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<label id="queryAuth" class="label-tips"></label>
							</td>
						</tr> -->
						<tr>
							<td class="td-left">是否生效<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="status" name="status">
									<option value="1">生效</option>
									<option value="0">失效</option>
								</select>
								<label id="status" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateCashierBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div>
		
		<div class="modal fade" id="deleteCashierModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除收银员</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该收银员么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" id="onlyId" />
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteCashierBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>

