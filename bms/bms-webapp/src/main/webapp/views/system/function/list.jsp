<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.admin.function.FunctionPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>菜单管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	$(function(){
	    var functions= ${functionList};
		var functionList=$("tr.function");
		$.each(functions,function(i,value){
			$.data(functionList[i],"function",value);
			
		});
		
		/* 修改弹出层 */
		$(".updatefunction").click(function(){
			var func = $.data($(this).parent().parent()[0],"function");
			 $('#updatefunctionModal').on('show.bs.modal', function () {
				$("#updatefunctionModal #functionId").val(func.functionId);
				$("#updatefunctionModal #functionCode").val(func.functionCode);
				$("#updatefunctionModal #functionName").val(func.functionName);
				$("#updatefunctionModal #parentFunctionId").val(func.parentFunctionId);
				$("#updatefunctionModal #functionLevel").val(func.functionLevel);
				$("#updatefunctionModal #functionUrl").val(func.functionUrl);
				$("#updatefunctionModal #isMenu").val(func.isMenu);
				$("#updatefunctionModal #isValid").val(func.isValid);
				$("#updatefunctionModal #accessType").val(func.accessType);
				$("#updatefunctionModal #functionOrder").val(func.functionOrder);
				$("#updatefunctionModal #memo").val(func.memo);
				$("#updatefunctionModal #iconClass").val(func.iconClass); 
			 })
			
			$('#updatefunctionModal').on('hide.bs.modal', function () {
				$("#updatefunctionModal #functionId").val('');
				$("#updatefunctionModal #functionCode").val('');
				$("#updatefunctionModal #functionName").val('');
				$("#updatefunctionModal #parentFunctionId").val('');
				$("#updatefunctionModal #functionLevel").val('');
				$("#updatefunctionModal #functionUrl").val('');
				$("#updatefunctionModal #isMenu").val('');
				$("#updatefunctionModal #isValid").val('');
				$("#updatefunctionModal #accessType").val('');
				$("#updatefunctionModal #functionOrder").val('');
				$("#updatefunctionModal #memo").val('');
				$("#updatefunctionModal #iconClass").val('');
			})
			
		});
		
		/* 修改 */
		$(".updatefunctionBtn").click(function(){
			clearUpdateTip();
			
			var functionId = $("#updatefunctionModal #functionId").val();
			if(kong.test(functionId)) {
				$.gyzbadmin.alertFailure("菜单编号不可为空");
				$("#updatefunctionModal #functionId").focus();
				return;
			}

			var functionCode = $("#updatefunctionModal #functionCode").val();
			if(kong.test(functionCode)) {
				$.gyzbadmin.alertFailure("菜单代码不可为空");
				$("#updatefunctionModal #functionCode").focus();
				return;
			}
			
			var functionName = $("#updatefunctionModal #functionName").val();
			if(kong.test(functionName)) {
				$.gyzbadmin.alertFailure("菜单名称不可为空");
				$("#updatefunctionModal #functionName").focus();
				return;
			}
			
			var parentFunctionId = $("#updatefunctionModal #parentFunctionId").val();
			if(kong.test(parentFunctionId)) {
				$.gyzbadmin.alertFailure("上级菜单不可为空");
				$("#updatefunctionModal #parentFunctionId").focus();
				return;
			}
			
			var functionLevel = $("#updatefunctionModal #functionLevel").val();
			if(kong.test(functionLevel)) {
				$.gyzbadmin.alertFailure("菜单级别不可为空");
				$("#updatefunctionModal #functionLevel").focus();
				return;
			}
			
			var functionUrl = $("#updatefunctionModal #functionUrl").val();
			if(kong.test(functionUrl)) {
				$.gyzbadmin.alertFailure("url不可为空");
				$("#updatefunctionModal #functionUrl").focus();
				return;
			}
			
			var isMenu = $("#updatefunctionModal #isMenu").val();
			if(kong.test(isMenu)) {
				$.gyzbadmin.alertFailure("是否菜单不可为空");
				$("#updatefunctionModal #isMenu").focus();
				return;
			}
			
			var isValid = $("#updatefunctionModal #isValid").val();
			if(kong.test(isValid)) {
				$.gyzbadmin.alertFailure("是否有效不可为空");
				$("#updatefunctionModal #isValid").focus();
				return;
			}
			
			var accessType = $("#updatefunctionModal #accessType").val();
			if(kong.test(accessType)) {
				$.gyzbadmin.alertFailure("是否授权不可为空");
				$("#updatefunctionModal #accessType").focus();
				return;
			}
			
			var functionOrder = $("#updatefunctionModal #functionOrder").val();
			if(kong.test(functionOrder)) {
				$.gyzbadmin.alertFailure("菜单排序不可为空");
				$("#updatefunctionModal #functionOrder").focus();
				return;
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=FunctionPath.BASE+FunctionPath.EDIT %>',{
				'functionId':functionId,
				'functionCode':functionCode,
				'functionName':functionName,
				'parentFunctionId':parentFunctionId,
				'functionLevel':functionLevel,
				'functionUrl':functionUrl,
				'isMenu':isMenu,
				'isValid':isValid,
				'accessType':accessType,
				'memo':$("#updatefunctionModal #memo").val(),
				'functionOrder':$("#updatefunctionModal #functionOrder").val(),
				'iconClass':$("#updatefunctionModal #iconClass").val()
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("修改成功！",function(){
							$("#updatefunctionModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
							$("#updatefunctionModal").modal("hide");
						});
					}
				},'json'
					
			);
			
			
		});
		
		
		/* 菜单删除层 */
		$(".delfunction").click(function(){
			var func = $.data($(this).parent().parent()[0],"function");
			$("#deletefunctionModal").find("input[name='functionId']").val(func.functionId);
			$("span.sureDel").text(func.functionId);
		});
		/* 删除 */
		$(".deletefunctionBtn").click(function(){
			
			$.post(window.Constants.ContextPath +'<%=FunctionPath.BASE + FunctionPath.DELETE %>',{
					'functionId':$("#deletefunctionModal #functionIdCode").val()
					},function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deletefunctionModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deletefunctionModal").modal("hide");
							});
						}
					},'json'

				);
		});
		
		
		/* 菜单功能增加  */
		$(".addfunctionBtn").click(function(){
			
			clearAddTip();
			
			var functionCode = $("#addfunctionModal #functionCode").val();
			if(kong.test(functionCode)) {
				$.gyzbadmin.alertFailure("菜单代码不可为空");
				$("#addfunctionModal #functionCode").focus();
				return;
			}
			
			var functionName = $("#addfunctionModal #functionName").val();
			if(kong.test(functionName)) {
				$.gyzbadmin.alertFailure("菜单名称不可为空");
				$("#addfunctionModal #functionName").focus();
				return;
			}
			
			var parentFunctionId = $("#addfunctionModal #parentFunctionId").val();
			if(kong.test(parentFunctionId)) {
				$.gyzbadmin.alertFailure("上级菜单不可为空");
				$("#addfunctionModal #parentFunctionId").focus();
				return;
			}
			
			var functionLevel = $("#addfunctionModal #functionLevel").val();
			if(kong.test(functionLevel)) {
				$.gyzbadmin.alertFailure("菜单级别不可为空");
				$("#addfunctionModal #functionLevel").focus();
				return;
			}
			
			var functionUrl = $("#addfunctionModal #functionUrl").val();
			if(kong.test(functionUrl)) {
				$.gyzbadmin.alertFailure("url不可为空");
				$("#addfunctionModal #functionUrl").focus();
				return;
			}
			
			var isMenu = $("#addfunctionModal #isMenu").val();
			if(kong.test(isMenu)) {
				$.gyzbadmin.alertFailure("是否菜单不可为空");
				$("#addfunctionModal #isMenu").focus();
				return;
			}
			
			var isValid = $("#addfunctionModal #isValid").val();
			if(kong.test(isValid)) {
				$.gyzbadmin.alertFailure("是否有效不可为空");
				$("#addfunctionModal #isValid").focus();
				return;
			}
			
			var accessType = $("#addfunctionModal #accessType").val();
			if(kong.test(accessType)) {
				$.gyzbadmin.alertFailure("是否授权不可为空");
				$("#addfunctionModal #accessType").focus();
				return;
			}
			
			var functionOrder = $("#addfunctionModal #functionOrder").val();
			if(kong.test(functionOrder)) {
				$.gyzbadmin.alertFailure("菜单排序不可为空");
				$("#addfunctionModal #functionOrder").focus();
				return;
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=FunctionPath.BASE+FunctionPath.ADD %>',{
				'functionCode':functionCode,
				'functionName':functionName,
				'parentFunctionId':parentFunctionId,
				'functionLevel':functionLevel,
				'functionUrl':functionUrl,
				'isMenu':isMenu,
				'isValid':isValid,
				'accessType':accessType,
				'memo':$("#addfunctionModal #memo").val(),
				'functionOrder':$("#addfunctionModal #functionOrder").val(),
				'iconClass':$("#addfunctionModal #iconClass").val()
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("增加成功！",function(){
							$("#addfunctionModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("增加失败！"+data.message,function(){
							$("#addfunctionModal").modal("hide");
						});
					}
				},'json'
					
			);
			
		});
	});
	
	function clearAddTip(){
		$("#addfunctionModal #functionCodeLabel").text("");
		$("#addfunctionModal #functionNameLabel").text("");
		$("#addfunctionModal #parentFunctionIdLabel").text("");
		$("#addfunctionModal #functionLevelLabel").text("");
		$("#addfunctionModal #functionUrlLabel").text("");
		$("#addfunctionModal #isMenuLabel").text("");
		$("#addfunctionModal #isValidLabel").text("");
		$("#addfunctionModal #accessTypeLabel").text("");
		$("#addfunctionModal #functionOrderLabel").text("");
		$("#addfunctionModal #memoLabel").text("");
		$("#addfunctionModal #iconClassLabel").text("");
		
	}
	
	function clearUpdateTip(){
		$("#updatefunctionModal #functionIdLabel").text("");
		$("#updatefunctionModal #functionCodeLabel").text("");
		$("#updatefunctionModal #functionNameLabel").text("");
		$("#updatefunctionModal #parentFunctionIdLabel").text("");
		$("#updatefunctionModal #functionLevelLabel").text("");
		$("#updatefunctionModal #functionUrlLabel").text("");
		$("#updatefunctionModal #isMenuLabel").text("");
		$("#updatefunctionModal #isValidLabel").text("");
		$("#updatefunctionModal #accessTypeLabel").text("");
		$("#updatefunctionModal #functionOrderLabel").text("");
		$("#updatefunctionModal #memoLabel").text("");
		$("#updatefunctionModal #iconClassLabel").text("");
	}
	
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
							<form action='<c:url value="<%=FunctionPath.BASE + FunctionPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								<tr>
									<td class="td-left" >菜单代码</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="functionCode" id="functionCode" value="${requestBean.functionCode }" >
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">菜单名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="functionName" id="functionName" value="${requestBean.functionName }" >
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">URL</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="functionUrl" id="functionUrl" value="${requestBean.functionUrl }" >
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=FunctionPath.BASE + FunctionPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=FunctionPath.BASE + FunctionPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addfunctionModal">
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
								菜单列表
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>菜单编号</th>
											<th>菜单代码</th>
											<th>菜单名称</th>
											<th>上级编号</th>
											<th>功能级别</th>
											<th>URL</th>
											<th>是否是菜单</th>
											<th>是否有效</th>
											<th>是否要授权</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${functions}" var="function" varStatus="status">
											<tr class="function" >
												<td>${function.functionId }</td>
												<td>${function.functionCode }</td>
												<td>${function.functionName }</td>
												<td>${function.parentFunctionId }</td>
												<td>${function.functionLevel }</td>
												<td>${function.functionUrl }</td>
												<td>${function.isMenu }</td>
												<td>${function.isValid }</td>
												<td>${function.accessType}</td>
												<td>${function.instUserName}</td>
												<td>
												<fmt:formatDate value="${function.instDatetime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<gyzbadmin:function url="<%=FunctionPath.BASE + FunctionPath.EDIT%>">
													<a href="#updatefunctionModal"  data-toggle='modal' class="tooltip-success updatefunction" data-rel="tooltip" title="Edit">
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty functions}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty functions}">
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
	
	<div class="modal fade" id="addfunctionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增菜单</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="25%">功能代码<span style="color: red">*</span></td>
							<td class="td-right" width="75%">
								<input type="text" id="functionCode" name="functionCode" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">功能名称<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionName" name="functionName" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">上级编号<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="parentFunctionId" name="parentFunctionId" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						<tr>
							<td class="td-left">功能级别<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionLevel" name="functionLevel" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">功能排序<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionOrder" name="functionOrder" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">URL<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionUrl" name="functionUrl" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">是否是菜单<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="isMenu" name="isMenu" style="width:90%">
									<option value="">-选择是否菜单-</option>
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">是否要授权<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="accessType" name="accessType" style="width:90%">
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="td-left">是否有效<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="isValid" name="isValid" style="width:90%">
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">图标样式</td>
							<td class="td-right">
								<input type="text" id="iconClass" name="iconClass" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea rows="2" cols="" id="memo" name="memo" style="width:90%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addfunctionBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updatefunctionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">编辑菜单</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="25%">功能编号<span style="color: red">*</span></td>
							<td class="td-right" width="75%">
								<input type="text" id="functionId" name="functionId" readonly="readonly" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="25%">功能代码<span style="color: red">*</span></td>
							<td class="td-right" width="75%">
								<input type="text" id="functionCode" name="functionCode" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">功能名称<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionName" name="functionName" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">上级编号<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="parentFunctionId" name="parentFunctionId" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						<tr>
							<td class="td-left">功能级别<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionLevel" name="functionLevel" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">功能排序<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionOrder" name="functionOrder" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">URL<span style="color: red">*</span></td>
							<td class="td-right">
								<input type="text" id="functionUrl" name="functionUrl" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">是否是菜单<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="isMenu" name="isMenu" style="width:90%">
									<option value="">-选择是否菜单-</option>
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">是否要授权<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="accessType" name="accessType" style="width:90%">
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="td-left">是否有效<span style="color: red">*</span></td>
							<td class="td-right">
								<select id="isValid" name="isValid" style="width:90%">
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">图标样式</td>
							<td class="td-right">
								<input type="text" id="iconClass" name="iconClass" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea rows="2" cols="" id="memo" name="memo" style="width:90%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updatefunctionBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="deletefunctionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					            <h4 class="modal-title" id="myModalLabel">菜单删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该菜单[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="functionId" id="functionIdCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deletefunctionBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

