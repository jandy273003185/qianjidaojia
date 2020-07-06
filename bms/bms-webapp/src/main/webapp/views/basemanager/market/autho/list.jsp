<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.market.controller.MarketAuthoControllerPath" %> 

<html>
<head>
	<meta charset="utf-8" />
	<title>市场部权限</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.uploadImage{ float:left; 
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
			}
	</style>
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
$(function(){
	var marketRoleLists = ${marketRoleList};
	var marketRoleList = $("tr.marketRole");
	$.each(marketRoleLists,function(i,value){
		$.data(marketRoleList[i],"marketRole",value);
	});
	
	//清除查询条件
	$(".clearSearchMessage").click(function(){
		$('.search-table #userCode').val("");
		$('.search-table #userName').val("");
		$('.search-table #userRole').val("");
		$('.search-table #searchUserRole').val("");
	});
	//新增选择角色
	$('.selectRoleBtn').click(function(){
		var roleId="";
		var roleName="";
		var box = document.getElementsByName("addRoleBox");
		for (var i=0;i<box.length;i++){
			 if(box[i].checked){ //判断复选框是否选中
				 roleName=roleName+box[i].value + ",";
			 	 roleId=roleId+box[i].id + ",";
			 }
		}
		roleId=roleId.substring(0,roleId.length-1);
		roleName=roleName.substring(0,roleName.length-1);
		$('#addUserModal #role').val(roleName);
		$('#addUserModal #userRole').val(roleId);
		$('#selectRoleModal').modal('hide');
	});
	
	//新增用户详情信息提交按钮
	$(".addBtn").click(function(){
	
		var userCode=$("#addUserModal").find("#userCode").val();
		if(kong.test(userCode)){
			$.gyzbadmin.alertFailure("用户账号不可为空");
			return;
		}
		var userName=$("#addUserModal").find("#userName").val();
		if(kong.test(userName)){
			$.gyzbadmin.alertFailure("用户姓名不可为空");
			return;
		}
		var userRole=$("#addUserModal").find("#userRole").val();
		if(kong.test(userRole)){
			$.gyzbadmin.alertFailure("用户组不可为空");
			return;
		}
		
		$.post(window.Constants.ContextPath +"<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.ADD %>",
			{
				'userCode':userCode,
				'userName':userName,
				'userRole':userRole
			},
			function(data){
				$.unblockUI();
				if(data.result=="SUCCESS"){

					$.gyzbadmin.alertSuccess("新增成功！",function(){
						$("#addUserModal").modal("hide");
					},function(){
						window.location.reload();
					});	
				}else{
						$.gyzbadmin.alertFailure("新增失败！原因:"+data.result,function(){
							$("#addUserModal").modal("hide");
					});
				}
			},'json'
		);
	});
	
	//删除确认提示框
	$(".deleteChannelLink").click(function(){
		var marketRole = $.data($(this).parent().parent()[0],"marketRole");		
		$("#deleteMarketRoleModal").find("input[name='userCode']").val(marketRole.userCode);
		$("#deleteMarketRoleModal").find("input[name='userName']").val(marketRole.userName);
		$("#deleteMarketRoleModal").find("input[name='userRole']").val(marketRole.userRole);
		$("span.sureDel").text("["+ marketRole.userCode + "]");		
	});
	
	//确认删除
	$(".deleteMarketRoleBtn").click(function(){
		var userCode= $("#deleteMarketRoleModal #userCode").val();
		var userName= $("#deleteMarketRoleModal #userName").val();
		var userRole= $("#deleteMarketRoleModal #userRole").val();
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.DELETE %>',
			data:{
				'userCode':userCode,
				'userName':userName,
				'userRole':userRole
				
			},
			success:function(data){
				$.unblockUI();			
				if(data.result=='SUCCESS'){
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteMarketRoleModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("删除失败！"+data.message);
				}
			}
		});
	});
	//修改角色信息回显
	$('.updateUserLink').click(function(){
		var marketRole = $.data($(this).parent().parent()[0],"marketRole");
		$('#updateUserModal').on('show.bs.modal',function(){
			var presidentId=${presidentId};
			var vicePresidentId=${vicePresidentId};
			$('#updateUserModal #userCode').val(marketRole.userCode);
			$('#updateUserModal #userName').val(marketRole.userName);
			$('#updateUserModal #oldUserName').val(marketRole.userName);
			$('#updateUserModal #oldUserRole').val(marketRole.userRole);
			$('#updateUserModal #userRole').val(marketRole.userRole);
			if(marketRole.userRole==presidentId){
				$('#updateUserModal #role').val("总裁组");
			}
			if(marketRole.userRole==vicePresidentId){
				$('#updateUserModal #role').val("副总裁组");
			}
			
		});
		$('#updateUserModal').on('hide.bs.modal',function(){
			$('#updateUserModal #userCode').val('');
			$('#updateUserModal #userName').val('');
			$('#updateUserModal #userRole').val('');
			$('#updateUserModal #oldUserName').val('');
			$('#updateUserModal #oldUserRole').val('');
			
		});
	});
	
	//修改选择角色
	$('.updateSelectRoleBtn').click(function(){
		var roleId="";
		var roleName="";
		var box = document.getElementsByName("addUpdateRoleBox");
		for (var i=0;i<box.length;i++){
			 if(box[i].checked){ //判断复选框是否选中
				 roleName=roleName+box[i].value + ",";
			 	 roleId=roleId+box[i].id + ",";
			 }
		}
		roleId=roleId.substring(0,roleId.length-1);
		roleName=roleName.substring(0,roleName.length-1);
		$('#updateUserModal #role').val(roleName);
		$('#updateUserModal #userRole').val(roleId);
		$('#updateSelectRoleModal').modal('hide');
	});
	
	//用户编辑提交更新
	$(".updateBtn").click(function(){
		
		var userCode=$("#updateUserModal").find("#userCode").val();
		
		if(kong.test(userCode)){
			$.gyzbadmin.alertFailure("用户账号不可为空");
			return;
		}
		var newUserName=$("#updateUserModal").find("#userName").val();
		if(kong.test(userName)){
			$.gyzbadmin.alertFailure("用户姓名不可为空");
			return;
		}
		var newUserRole=$("#updateUserModal").find("#userRole").val();
		if(kong.test(userRole)){
			$.gyzbadmin.alertFailure("用户角色不可为空");
			return;
		}
		var userName=$("#updateUserModal").find("#oldUserName").val();
		var userRole=$("#updateUserModal").find("#oldUserRole").val();
		if(userName == newUserName){
			newUserName="";
		}
		if(userRole == newUserRole){
			newUserRole="";
		}
		$.post(window.Constants.ContextPath +"<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.UPDATE %>",
				{
					'userCode':userCode,
					'userName':userName,
					'userRole':userRole,
					'newUserName':newUserName,
					'newUserRole':newUserRole
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){

						$.gyzbadmin.alertSuccess("修改成功！",function(){
							$("#updateUserModal").modal("hide");
						},function(){
							window.location.reload();
						});		
					}else{
							$.gyzbadmin.alertFailure("修改失败！",function(){
								$("#updateUserModal").modal("hide");
							});
					}
				},'json'
				);
		
	});
});
function addCheckItem(str){
	  var allche = document.getElementById("addRoleAllBox");    
	  var che = document.getElementsByName(str);    
	  if(allche.checked == true){   
		  for(var i=0;i<che.length;i++){    
			  che[i].checked="checked";    
			  }   
		  }else{     
		 for(var i=0;i<che.length;i++){   
				  che[i].checked=false;    
				 }  
		  }   
	
}
function addCheckAll(str){
  var allche = document.getElementById(str);    
  var che = document.getElementsByName("addRoleBox");    
	 for(var i=0;i<che.length;i++){    
		  if(!che[i].checked){
			  allche.checked=false;  
			  break;
		  } else{
			  allche.checked="checked";   
		  }
	 }   
}	
function onLoadSearch(){
	$('.search-table #userRole').val($("#searchUserRole").val());	
}
</script>
<body onload="onLoadSearch()">
	<!-- 市场部权限管理-->
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
							<form action='<c:url value="<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.LIST %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" value="${queryBean.userRole }"  id="searchUserRole"/>
								
								<tr>
									<td class="td-left">用户账号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="userCode" value="${queryBean.userCode }" id="userCode">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">用户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="userName" value="${queryBean.userName }" id="userName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">用户角色</td>
									<td class="td-right">
										<select id="userRole" name="userRole"  >
											<option value = "">- 请选择  -</option>
										    <option value = "${presidentId}">总裁</option>
										    <option value = "${vicePresidentId}">副总裁</option>  
										
										</select>
									</td>	
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.LIST  %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button  type="button" style="margin-left:10px;margin-right:10px;"class="btn btn-purple btn-sm  clearSearchMessage"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										
											<gyzbadmin:function url="<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addUserModal">
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
								用户列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>用户账号</th>
											<th>用户名称</th>							
											<th>用户角色</th>
											<gyzbadmin:function url="<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.UPDATE%>">
												<th>操作</th>
											</gyzbadmin:function>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${marketRoleList}" var="marketRole">
											
											<tr class="marketRole">
												<td>${marketRole.userCode}</td>
												<td>${marketRole.userName}</td>
												<td>
													<c:if test="${marketRole.userRole==presidentId}">总裁</c:if>
													<c:if test="${marketRole.userRole==vicePresidentId}">副总裁</c:if>
												</td>
												<gyzbadmin:function url="<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.UPDATE%>">								
												<td>
														<a href="#updateUserModal"  data-toggle='modal' class="tooltip-success updateUserLink"  data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
														<a href="#deleteMarketRoleModal" data-toggle='modal' class="tooltip-error deleteChannelLink" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
												</td>
												</gyzbadmin:function>
											</tr>
										</c:forEach>
										<c:if test="${empty marketRoleList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty marketRoleList}">
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

	<!-- 新增用户-->
	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" style="width:40%">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">新增用户</h4>
	         </div>
	         <div class="modal-body">
	         	<form action='<%=MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.ADD %>' method="post" id="addChannelForm">
	            <table class="modal-input-table" style="width: 100%;">
					<tr>	
						<td class="td-left" width="20%">用户账号<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="userCode" name="userCode" style="width:80%">
						</td>
					</tr>
					<tr>	
						<td class="td-left" width="20%">用户姓名<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="userName" name="userName" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">用户组<span style="color:red">*</span></td>
						<td class="td-right">
						   <input type="hidden" id="userRole" name="userRole" readonly="readonly"/> 
						   <textarea id="role" name="role" readonly="readonly" rows="2" clasS="width-70"></textarea>
							<a href="#" class="tooltip-error selectRoleLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#selectRoleModal">
								<span><button type="button"  id="queryRole"  class="btn btn-warning btn-xs">选择用户组</button></span>
							</a>
						</td>
					</tr>
					
	            </table>
	            </form>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary addBtn">提交</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	
	<!-- 新增选择角色 -->
	<div class="modal fade" id="selectRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width:500px;">
	        <div class="modal-header">
	           <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">选择用户组</h4>
	         </div>
	         <div class="modal-body" align="center">
	          <table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<!-- <th><input type="radio" onclick="addCheckItem('addRoleBox');" id="addRoleAllBox" name="addRoleAllBox"/></th> -->
							<th></th> 
							<th>组名称</th>
							
						</tr>
					</thead>

					<tbody>
						<tr class="role">
							<td><input type="radio" onclick="addCheckAll('addRoleAllBox');" name="addRoleBox" id="${presidentId}" value="总裁组"/></td>
							<td>总裁组</td>
							
						</tr>
						<tr class="role">
							<td><input type="radio" onclick="addCheckAll('addRoleAllBox');" name="addRoleBox" id="${vicePresidentId}" value="副总裁组"/></td>
							<td>副总裁组</td>				
						</tr>
					
					</tbody>
				</table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn btn-primary selectRoleBtn">确定</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	<!-- 修改用户信息 -->  
	<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" style="width:40%">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">编辑广告</h4>
	         </div>
	         <div class="modal-body">
	         	
	            <table class="modal-input-table" style="width: 100%;">
	          
	            	<input type="hidden" id="oldUserName" name="oldUserName"" >

	            	<input type="hidden" id="oldUserRole" name="oldUserRole" >
					<tr>	
						<td class="td-left" width="20%">用户账号<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="userCode" name="userCode" readonly="readonly" style="width:80%">
						</td>
					</tr>
					<tr>	
						<td class="td-left" width="20%">用户姓名<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="userName" name="userName" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">用户组<span style="color:red">*</span></td>
						<td class="td-right">
						   <input type="hidden" id="userRole" name="userRole" readonly="readonly"/> 
						   <textarea id="role" name="role" readonly="readonly" rows="2" clasS="width-70"></textarea>
							<a href="#" class="tooltip-error selectRoleLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#updateSelectRoleModal">
								<span><button type="button"  id="queryRole"  class="btn btn-warning btn-xs">选择用户组</button></span>
							</a>
						</td>
					</tr>
	            </table>
	          
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary updateBtn">提交</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	
	<!-- 修改选择角色 -->
	<div class="modal fade" id="updateSelectRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width:500px;">
	        <div class="modal-header">
	           <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">选择用户组</h4>
	         </div>
	         <div class="modal-body" align="center">
	          <table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th></th>
							<th>组名称</th>
							
						</tr>
					</thead>

					<tbody>
						<tr class="role">
							<td><input type="radio" onclick="addCheckAll('addUpdateRoleAllBox');" name="addUpdateRoleBox" id="${presidentId}" value="总裁组"/></td>
							<td>总裁组</td>
							
						</tr>
						<tr class="role">
							<td><input type="radio" onclick="addCheckAll('addUpdateRoleAllBox');" name="addUpdateRoleBox" id="${vicePresidentId}" value="副总裁组"/></td>
							<td>副总裁组</td>				
						</tr>
					
					</tbody>
				</table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn btn-primary updateSelectRoleBtn">确定</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	<!-- 删除确认 -->
	<div class="modal fade" id="deleteMarketRoleModal" style="margin-top:150px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width: 600px;">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">删除商户详情</h4>
	         </div>
	         <div class="modal-body" align="center">
	         	<font style="font-weight: bold; font-size: 15px;">您确定要删除该用户<span style="color:red;" class="sureDel"></span>么？</font>
	         </div>
	         <div class="modal-footer">
	         	<input type="hidden" name="userCode" id="userCode">
	         	<input type="hidden" name="userName" id="userName">
	         	<input type="hidden" name="userRole" id="userRole">
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn btn-primary deleteMarketRoleBtn">确定</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	
	
</body>
</html>

