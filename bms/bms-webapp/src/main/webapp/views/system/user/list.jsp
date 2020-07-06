<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.admin.user.UserPath"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>用户管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
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
							<form action='<c:url value="<%=UserPath.BASE + UserPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								<tr>
									<td class="td-left" width="14%">真实姓名</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<input type="text" name="realName"  id="realName" value="${userBean.realName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="13%">部门名称</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<gyzbadmin:selectDept id="deptId" name="deptId" clasS="width-100" defaultValue="${userBean.deptId }"/>
										</span>
									</td>
										<td class="td-left" width="13%">状态</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<gyzbadmin:selectUserStatus id="status" name="status" clasS="width-100" defaultValue="${userBean.status }"/>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=UserPath.BASE + UserPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=UserPath.BASE + UserPath.ADD%>">
												<button type="button" class="btn btn-purple btn-sm btn-margin addUserLink" data-toggle='modal' data-target="#addUserModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">用户列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
										    <th>编号</th>
											<th>员工编号</th>
											<th>用户名</th>
											<th>部门名称</th>
											<th>性别</th>
											<th>个人电话</th>
											<th>状态</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>更新人</th>
											<th>更新时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${userList }" var="user" varStatus="status">
										<tr class="user">
											<td>${user.userId }</td>
											<td>${user.userCode }</td>
											<td>${user.userName }</td>
											<td>${user.deptName }</td>
											<td>
												<c:if test="${user.sex =='MEN'}">
													男
												</c:if>
												<c:if test="${user.sex =='WOMEN'}">
													女
												</c:if>
											</td>
											<td>${user.selfPhone }</td>
											<td>
											   <c:if test="${user.status=='VALID'}">
													生效
												</c:if>
												<c:if test="${user.status=='FREEZE'}">
													冻结
												</c:if>
												<c:if test="${user.status=='LEAVE'}">
													离职
												</c:if>
												<c:if test="${user.status=='LOGIN'}">
													已登陆
												</c:if>
											</td>
											<td>${user.instUserName }</td>
											<td>
											<fmt:formatDate value="${user.instDatetime}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>${user.lupdUserName }</td>
											<td>
											<fmt:formatDate value="${user.lupdDatetime}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<input type="hidden" name="userId" value="${user.userId }"/>
												<gyzbadmin:function url="<%=UserPath.BASE + UserPath.EDIT%>">
													<a href="#" class="tooltip-success editUserLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#editUserModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty userList}">
										<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty userList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
					<!-- 新增用户模态框（Modal） -->
					<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">用户新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
					            		<td class="td-left" width="20%">员工编号<span style="color: red">*</span></td>
										<td class="td-right">
											<input type="text" id="userCode" name="userCode"  clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">密码<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="password" id="password" name="password" maxlength="20" clasS="width-90">
										</td>
									</tr>
					            	<tr>
										<td class="td-left" width="20%">用户名<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="userName" name="userName" clasS="width-90">
										</td>
									</tr>
					            	<tr>
					            		<td class="td-left">真实姓名<span style="color: red">*</span></td>
										<td class="td-right">
											<input type="text" id="realName" name="realName" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">昵称</td>
										<td class="td-right">
											<input type="text" id="nickName" name="nickName" clasS="width-90">
										</td>
									</tr>
					            	<tr>
										<td class="td-left">性别<span style="color: red">*</span></td>
										<td class="td-right">
											<select id="sex" name="sex" clasS="width-90">
												<option value="">-请选择性别-</option>
												<option value="MEN">-男-</option>
												<option value="WOMEN">-女-</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="td-left">所属部门<span style="color: red">*</span></td>
										<td class="td-right">
											<gyzbadmin:selectDept id="deptId" name="deptId" style="width:90%"/>
										</td>
									</tr>
									<tr>
									<td class="td-left">角色<span style="color: red">*</span></td>
										<td class="td-right">
											   <input type="hidden" id="roleId" name="roleId" readonly="readonly"/> 
											   <textarea id="roleName" name="roleName" readonly="readonly" rows="2" clasS="width-70"></textarea>
												<a href="#" class="tooltip-error selectRoleLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#selectRoleModal">
													<span><button type="button"  id="queryRole"  class="btn btn-warning btn-xs">选择角色</button></span>
												</a>
										</td>
									</tr>
									<tr>
										<td class="td-left">办公电话</td>
										<td class="td-right">
											<input type="text" id="workPhone" name="workPhone" clasS="width-90">
										</td>
									</tr>
					            	<tr>
										<td class="td-left">个人电话</td>
										<td class="td-right">
											<input type="text" id="selfPhone" name="selfPhone" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">办公邮箱</td>
										<td class="td-right">
											<input type="text" id="workEmail" name="workEmail" clasS="width-90">
										</td>
									</tr>
					            	<tr>
										<td class="td-left">个人邮箱</td>
										<td class="td-right">
											<input type="text" id="selfEmail" name="selfEmail" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">备注</td>
										<td class="td-right" >
											<textarea id="memo" name="memo" maxlength="500" clasS="width-90"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addUserBtn">提交</button>
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
					            <h4 class="modal-title" id="myModalLabel">选择角色</h4>
					         </div>
					         <div class="modal-body" align="center">
					          <table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th><input type="checkbox" onclick="addCheckItem('addRoleBox');" id="addRoleAllBox" name="addRoleAllBox"/></th>
											<th>角色编号</th>
											<th>角色名称</th>
										</tr>
									</thead>
				
									<tbody>
									<c:forEach items="${roleList }" var="role">
										<tr class="role">
											<td><input type="checkbox" onclick="addCheckAll('addRoleAllBox');" name="addRoleBox" id="${role.roleId }" value="${role.roleName }"/></td>
											<td>${role.roleId }</td>
											<td>${role.roleName }</td>
										</tr>
									</c:forEach>
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
					<!-- 删除用户模态框（Modal） -->
					<div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">用户删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该用户[<span class="userId"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="userId" class="userId" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteUserBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<!-- 修改用户模态框（Modal） -->
					<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">用户修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="20%">编号<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="userId" name="userId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">员工编号<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="userCode" name="userCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">用户名<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="userName" name="userName"  clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">密码<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="password" id="password" name="password" maxlength="20" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">真实姓名<span style="color: red">*</span></td>
										<td class="td-right">
											<input type="text" id="realName" name="realName" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">昵称</td>
										<td class="td-right">
											<input type="text" id="nickName" name="nickName" clasS="width-90">
										</td>
									</tr>
									<tr>
									    <td class="td-left">性别<span style="color: red">*</span></td>
										<td class="td-right">
											<select id="sex" name="sex" clasS="width-90">
												<option value="">-请选择性别-</option>
												<option value="MEN">-男-</option>
												<option value="WOMEN">-女-</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="td-left">状态<span style="color: red">*</span></td>
										<td class="td-right" >
											<gyzbadmin:selectUserStatus id="status" name="status" style="width:90%"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">所属部门<span style="color: red">*</span></td>
										<td class="td-right">
											<gyzbadmin:selectDept id="deptId" name="deptId" style="width:90%"/>
										</td>
										
									</tr>
									<tr>
										<td class="td-left">角色<span style="color: red">*</span></td>
										<td class="td-right">
											   <input type="hidden" id="roleId" name="roleId" readonly="readonly"/> 
											   <textarea id="roleName" id="roleName" name="roleName" readonly="readonly" rows="2" clasS="width-70"></textarea>
											<a href="#" class="tooltip-error editRoleLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#editRoleModal">
													<span><button type="button"  id="editRole"  class="btn btn-warning btn-xs">选择角色</button></span>
											</a>
										</td>
									</tr>
									<tr>
										<td class="td-left">办公电话</td>
										<td class="td-right">
											<input type="text" id="workPhone" name="workPhone" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">个人电话</td>
										<td class="td-right">
											<input type="text" id="selfPhone" name="selfPhone" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">办公邮箱</td>
										<td class="td-right">
											<input type="text" id="workEmail" name="workEmail"class=" width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">个人邮箱</td>
										<td class="td-right">
											<input type="text" id="selfEmail" name="selfEmail" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">备注</td>
										<td class="td-right">
											<textarea  id="memo" name="memo" maxlength="500" clasS="width-90"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editUserBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<!-- 修改选择角色 -->
					<div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width:500px;">
					        <div class="modal-header">
					           <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">选择角色</h4>
					         </div>
					         <div class="modal-body" align="center">
					          <table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th><input type="checkbox" onclick="editCheckItem('editRoleBox');" id="editRoleAllBox" name="editRoleAllBox"/></th>
											<th>角色编号</th>
											<th>角色名称</th>
										</tr>
									</thead>
				
									<tbody>
									<c:forEach items="${roleList }" var="role">
										<tr class="role">
											<td><input type="checkbox" onclick="editCheckAll('editRoleAllBox');" name="editRoleBox" id="${role.roleId }" value="${role.roleName }"/></td>
											<td>${role.roleId }</td>
											<td>${role.roleName }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary editRoleBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
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

	<script type="text/javascript">
	/* function findUser(){
		alert()
		  
	} */
		jQuery(function($){
		
			// 为没个tr缓存数据
			var userListJson = ${userList};
			var userTrList = $('tr.user');
			$.each(userListJson, function(idx, obj){
				$.data(userTrList[idx], 'user', obj);
			});

			$('.addUserLink').click(function(){
				$('#addUserModal').on('hide.bs.modal', function () {
					// 清除
					$('#addUserModal #userCode').val('');
					$('#addUserModal #userName').val('');
					$('#addUserModal #password').val('');
					$('#addUserModal #realName').val('');
					$('#addUserModal #nickName').val('');
					$('#addUserModal #deptId').val('');
					$('#addUserModal #sex').val('');
					$('#addUserModal #workPhone').val('');
					$('#addUserModal #selfPhone').val('');
					$('#addUserModal #workEmail').val('');
					$('#addUserModal #selfEmail').val('');
					$('#addUserModal #status').val('');
					$('#addUserModal #memo').val('');
				});
			});
			
			// 新增单个用户
			$('.addUserBtn').click(function(){
				// 员工号
				var userCode = $('#addUserModal #userCode').val();
				if(kong.test(userCode)) {
					$.gyzbadmin.alertFailure('员工号不可为空');
					return;
				}
				// 用户名
				var userName = $('#addUserModal #userName').val();
				if(kong.test(userName)) {
					$.gyzbadmin.alertFailure('用户名不可为空');
					return;
				}
				// 真实姓名
				var realName = $('#addUserModal #realName').val();
				if(kong.test(realName)) {
					$.gyzbadmin.alertFailure('真实姓名不可为空');
					return;
				}
				// 密码
				var password = $('#addUserModal #password').val();
				if(kong.test(password)) {
					$.gyzbadmin.alertFailure('密码不可为空');
					return;
				}
				// 所属部门
				var deptId = $('#addUserModal #deptId').val();
				if(kong.test(deptId)) {
					$.gyzbadmin.alertFailure('所属部门不可为空');
					return;
				}
				// 性别
				var sex = $('#addUserModal #sex').val();
				if(kong.test(sex)) {
					$.gyzbadmin.alertFailure('性别不可为空');
					return;
				}
				
				//角色
				var role = $('#addUserModal #roleId').val();
				if(kong.test(role)) {
					$.gyzbadmin.alertFailure('角色不可为空');
					return;
				}
				//办公电话
				if(!kong.test($('#addUserModal #workPhone').val())){
					
					if(!isPhoneNo($('#addUserModal #workPhone')[0])){
						$.gyzbadmin.alertFailure("办公电话格式不正确");
						return false;
					}
				}
				
				//个人电话
				if(!kong.test($('#addUserModal #selfPhone').val())){
					if(!isPhoneNo($('#addUserModal #selfPhone')[0])){
						$.gyzbadmin.alertFailure("个人电话格式不正确");
						return false;
					}
				}
				
				//办公邮箱
				if(!kong.test($('#addUserModal #workEmail').val())){
					
					if(!verifyEmailAddress($("#addUserModal #workEmail")[0])){
						$.gyzbadmin.alertFailure("办公邮箱格式不正确");
						return false;
					}
				}
				//个人邮箱
				if(!kong.test($('#addUserModal #selfEmail').val())){
					if(!verifyEmailAddress($("#addUserModal #selfEmail")[0])){
						$.gyzbadmin.alertFailure("个人邮箱格式不正确");
						return false;
					}
				}
				// 保存
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=UserPath.BASE + UserPath.ADD %>', {
						'userCode'	: userCode,
						'userName'	: userName,
						'password'	: password,
						'realName'	: realName,
						'nickName'	: $('#addUserModal #nickName').val(),
						'deptId'	: deptId,
						'sex'	: sex,
						'workPhone'	: $('#addUserModal #workPhone').val(),
						'selfPhone'	: $('#addUserModal #selfPhone').val(),
						'workEmail'	: $('#addUserModal #workEmail').val(),
						'selfEmail'	: $('#addUserModal #selfEmail').val(),
						'memo'	: $('#addUserModal #memo').val(),
						'role' : role
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#addUserModal').modal('hide');
							$.gyzbadmin.alertSuccess('新增成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('保存失败:' + data.message);
						}
					}, 'json'
				);
			});
			
			// 弹出删除层准备工作
			$('.deleteUserLink').click(function(){
				var userId = $(this).parent().find('input[name="userId"]').val();
				$('#deleteUserModal').on('show.bs.modal', function () {
					// 赋值
					$('#deleteUserModal span.userId').html(userId);
					$('#deleteUserModal input.userId').val(userId);
				});
				$('#deleteUserModal').on('hide.bs.modal', function () {
					// 清除
					$('#deleteUserModal span.userId').empty();
					$('#deleteUserModal input.userId').val('');
				});
			});
	
			// 删除用户
			$('.deleteUserBtn').click(function(){
				var userId = $('#deleteUserModal input.userId').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=UserPath.BASE + UserPath.DELETE %>', {
						'userId' 	: userId
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#deleteUserModal').modal('hide');
							$.gyzbadmin.alertSuccess('删除成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('删除失败:' + data.message);
						}
					}, 'json'
				);
			});
	
			// 弹出修改层准备工作
			$('.editUserLink').click(function(){
				var user = $.data($(this).parent().parent()[0], 'user');
				var userId= $(this).parent().find('input[name="userId"]').val();
				
				//查找用户角色 并赋值
				$.post(window.Constants.ContextPath + '<%=UserPath.BASE + UserPath.SEARCH %>',{
						'userId':userId
						},function(data){
							if(data.result == 'SUCCESS'){
								$('#editUserModal #roleId').val(data.roleId);
								$('#editUserModal #roleName').val(data.roleName);
							} else {
								$.gyzbadmin.alertFailure('用户角色不存在',function(){
									$('#editUserModal').modal('hide');
								});
							}
						},'json'
				);
				
				$('#editUserModal').on('show.bs.modal', function () {
					// 赋值
					$('#editUserModal #userId').val(user.userId);
					$('#editUserModal #userCode').val(user.userCode);
					$('#editUserModal #userName').val(user.userName);
					$('#editUserModal #password').val(user.password);
					$('#editUserModal #realName').val(user.realName);
					$('#editUserModal #nickName').val(user.nickName);
					$('#editUserModal #deptId').val(user.deptId);
					$('#editUserModal #sex').val(user.sex);
					$('#editUserModal #workPhone').val(user.workPhone);
					$('#editUserModal #selfPhone').val(user.selfPhone);
					$('#editUserModal #workEmail').val(user.workEmail);
					$('#editUserModal #selfEmail').val(user.selfEmail);
					$('#editUserModal #status').val(user.status);
					$('#editUserModal #memo').val(user.memo);
					$('#editUserModal #role').val(user.roleId);
				});
				$('#editUserModal').on('hide.bs.modal', function () {
					// 清除
					$('#editUserModal #userId').val('');
					$('#editUserModal #userCode').val('');
					$('#editUserModal #userName').val('');
					$('#editUserModal #password').val('');
					$('#editUserModal #realName').val('');
					$('#editUserModal #nickName').val('');
					$('#editUserModal #deptId').val('');
					$('#editUserModal #sex').val('');
					$('#editUserModal #workPhone').val('');
					$('#editUserModal #selfPhone').val('');
					$('#editUserModal #workEmail').val('');
					$('#editUserModal #selfEmail').val('');
					$('#editUserModal #status').val('');
					$('#editUserModal #memo').val('');
					$('#editUserModal #role').val('');
				});
			});
	
			// 修改用户
			$('.editUserBtn').click(function(){
				// 用户编号
				var userId = $('#editUserModal #userId').val();
				if(kong.test(userId)) {
					$.gyzbadmin.alertFailure('用户编号不可为空');
					return;
				}
				// 员工号
				var userCode = $('#editUserModal #userCode').val();
				if(kong.test(userCode)) {
					$.gyzbadmin.alertFailure('员工编号不可为空');
					return;
				}
				// 密码
				var password = $('#editUserModal #password').val();
				if(kong.test(password)) {
					$.gyzbadmin.alertFailure('密码不可为空');
					return;
				}
				
				var userName = $('#editUserModal #userName').val();
				if(kong.test(userName)) {
					$.gyzbadmin.alertFailure('用户名不可为空');
					return;
				}
				// 真是姓名
				var realName = $('#editUserModal #realName').val();
				if(kong.test(realName)) {
					$.gyzbadmin.alertFailure('真实姓名不可为空');
					return;
				}
				// 所属部门
				var deptId = $('#editUserModal #deptId').val();
				if(kong.test(deptId)) {
					$.gyzbadmin.alertFailure('所属部门不可为空');
					return;
				}
				// 性别
				var sex = $('#editUserModal #sex').val();
				if(kong.test(sex)) {
					$.gyzbadmin.alertFailure('性别不可为空');
					return;
				}
				// 用户状态
				var status = $('#editUserModal #status').val();
				if(kong.test(status)) {
					$.gyzbadmin.alertFailure('用户状态不可为空');
					return;
				}
				
				//角色
				var role = $('#editUserModal #roleId').val();
				if(kong.test(role)) {
					$.gyzbadmin.alertFailure('角色不可为空');
					return;
				}
				if(!kong.test($("#editUserModal #workPhone").val())){
					if(!isPhoneNo($('#editUserModal #workPhone')[0])){
						$.gyzbadmin.alertFailure("办公电话格式不正确");
						return false;
					}
				}
				if(!kong.test($('#editUserModal #selfPhone').val())){
					if(!isPhoneNo($('#editUserModal #selfPhone')[0])){
						$.gyzbadmin.alertFailure("个人电话格式不正确");
						return false;
					}
				}
				
				if(!kong.test($("#editUserModal #workEmail").val())){
					if(!verifyEmailAddress($("#editUserModal #workEmail")[0])){
						$.gyzbadmin.alertFailure("办公邮箱格式不正确");
						return false;
					}
				}
				if(!kong.test($("#editUserModal #selfEmail").val())){
					if(!verifyEmailAddress($("#editUserModal #selfEmail")[0])){
						$.gyzbadmin.alertFailure("个人邮箱格式不正确");
						return false;
					}
				}
				// 保存修改
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=UserPath.BASE + UserPath.EDIT %>', {
						'userId' 	: userId,
						'userCode'	: userCode,
						'password'	: password,
						'userName'	: userName,
						'realName'	: realName,
						'nickName'	: $('#editUserModal #nickName').val(),
						'deptId'	: deptId,
						'sex'	: sex,
						'workPhone'	: $('#editUserModal #workPhone').val(),
						'selfPhone'	: $('#editUserModal #selfPhone').val(),
						'workEmail'	: $('#editUserModal #workEmail').val(),
						'selfEmail'	: $('#editUserModal #selfEmail').val(),
						'status'	: status,
						'memo'	: $('#editUserModal #memo').val(),
						'role':role
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#editUserModal').modal('hide');
							$.gyzbadmin.alertSuccess('修改成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
						}
					}, 'json'
				);
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
				$('#addUserModal #roleId').val(roleId);
				$('#addUserModal #roleName').val(roleName);
				$('#selectRoleModal').modal('hide');
			});
			
			//加载修改角色
			$('.editRoleLink').click(function(){
				var roleId=	$('#editUserModal #roleId').val();
				var role  =roleId.split(",");
				var box = document.getElementsByName("editRoleBox");
				var allche= document.getElementById("editRoleAllBox");  
				
				for (var k=0;k<box.length;k++){
					box[k].checked=false;
				}
				allche.checked=false;
		
				if(box.length==role.length){
					allche.checked="checked";
				}
				for (var j=0;j < box.length;j++){
					for(var i=0;i<role.length;i++){
					if(box[j].id==role[i]){
						box[j].checked="checked";
					}
				}
			  }
			});
			//修改选择角色
			$('.editRoleBtn').click(function(){
				var roleId="";
				var roleName="";
				var box = document.getElementsByName("editRoleBox");
				for (var i=0;i<box.length;i++){
					 if(box[i].checked){ //判断复选框是否选中
						 roleName=roleName+box[i].value + ",";
					 	 roleId=roleId+box[i].id + ",";
					 }
				}
				roleId=roleId.substring(0,roleId.length-1);
				roleName=roleName.substring(0,roleName.length-1);
				$('#editUserModal #roleId').val(roleId);
				$('#editUserModal #roleName').val(roleName);
				$('#editRoleModal').modal('hide');
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
		
		function editCheckItem(str){
			  var allche = document.getElementById("editRoleAllBox");    
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
		function editCheckAll(str){
		  var allche = document.getElementById(str);    
		  var che = document.getElementsByName("editRoleBox");    
			 for(var i=0;i<che.length;i++){    
				  if(!che[i].checked){
					  allche.checked=false;  
					  break;
				  } else{
					  allche.checked="checked";   
				  }
			 }   
		}
	</script>
  </body>	
</html>