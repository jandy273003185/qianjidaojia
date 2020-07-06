<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.admin.role.RolePath" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>角色管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	li{list-style-type:none;}
	.displayUl{display:none;}
</style>
</head>
<script type="text/javascript">
	$(function(){
		var role= ${roleList};
		var roleList=$("tr.role");
		$.each(role,function(i,value){
			$.data(roleList[i],"role",value);
		});
		
		var fun = ${fun};
		var funObject = $("table.list-table");
		$.data(funObject,"fun",fun);
		
		/*删除*/
		$(".deleteRole").click(function(){
			
			var role = $.data($(this).parent().parent()[0],"role");
			$("#deleteRoleModal").find("input[name='roleId']").val(role.roleId);
			$("span.sureDel").text(role.roleId);
		});
		
		$(".deleteRoleBtn").click(function(){
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=RolePath.BASE + RolePath.DELETE%>',{

				'roleId':$("#deleteRoleModal #delroleId").val()
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteRoleModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
							$("#deleteRoleModal").modal("hide");
						});
					}
				},'json'
			
			);
		});
		
		
		/**修改 */
		$(".editRole").click(function(){
			var role = $.data($(this).parent().parent()[0],"role");
			$("#editRoleModal #roleId").val(role.roleId);
			$("#editRoleModal #roleCode").val(role.roleCode);
			$("#editRoleModal #roleName").val(role.roleName);
			$("#editRoleModal #memo").val(role.memo);
			$("#editRoleModal #isValid").val(role.isValid);
			
			var object =$.data(funObject,"fun");
			
			/* 生成权限菜单树 */
			var htm ='';
		 	htm = menu(object);
			
			$("#editRoleModal .roleUl").html(htm);
			
			/* ul 样式*/
			$("#editRoleModal .roleUl ul").toggleClass("displayUl"); 
			
			$("#editRoleModal .roleUl > li .textClass").click(function(){
				
				$(this).parent().children("ul").toggleClass("displayUl");
			});
			
			
			/* 级联选择 */
			$("#editRoleModal input[name='chk']").click(function(){
				$(this).parent("li").find('input[name="chk"]').prop("checked",this.checked);
				
			});
			
			/* 已经存在的菜单标记选中 */
			$.post(window.Constants.ContextPath +'<%=RolePath.BASE + RolePath.FUNCTION%>',{

				   'roleId':role.roleId
					},function(data){
						if(data.result=="success"){
							
							var obj = data.data;
							for(var i=0;i<obj.length;i++){
								var ob = $("#editRoleModal input[name='chk']");
								for(var j=0 ;j<ob.length;j++){
									if(ob[j].value == obj[i].functionId){
										ob[j].checked = true;
									}
								}
							}
						}else{
							
						}
					},'json'
				);
		});
		
		$(".editRoleBtn").click(function(){
			var roleId = $("#editRoleModal #roleId").val();
			
			var roleName = $("#editRoleModal #roleName").val();
			if(kong.test(roleName)) {
				$.gyzbadmin.alertFailure("角色名称不可为空");
				$("#editRoleModal #roleName").focus();
				return;
			}
			var roleCode = $("#editRoleModal #roleCode").val();
			if(kong.test(roleCode)) {
				$.gyzbadmin.alertFailure("角色代码不可为空");
				$("#editRoleModal #roleCode").focus();
				return;
			}
			var memo = $("#editRoleModal #memo").val();
			if(kong.test(memo)) {
				$.gyzbadmin.alertFailure("角色说明不可为空");
				$("#editRoleModal #memo").focus();
				return;
			}
			var isValid = $("#editRoleModal #isValid").val();
			if(kong.test(isValid)) {
				$.gyzbadmin.alertFailure("角色是否有效不可为空");
				$("#editRoleModal #isValid").focus();
				return;
			}
			
			/* 获取被选中的值  */
			var check = $("#editRoleModal input[name='chk']:checked");
			var value='';
			if(null != check || check.length >0){
				for(var i=0; i<check.length;i++){
					if(i!=check.length-1){
						value+= check[i].value+',';
					}else{
						value+= check[i].value;
					}
				}
			}
			
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=RolePath.BASE+RolePath.EDIT %>',{

				'roleId':roleId,
				'roleName':roleName,
				'roleCode':roleCode,
				'memo':memo,
				'isValid':isValid,
				'checkValue':value
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更改成功！",function(){
							$("#editRoleModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("更改失败！"+data.message,function(){
							$("#editRoleModal").modal("hide");
						});
					}
				},'json'
					
			);
		});
		
		/* 增加层准备工作*/
		$(".addRole").click(function(){
			var object =$.data(funObject,"fun");
			
			/* 生成权限菜单树 */
			var htm ='';
		 	htm = menu(object);
			
			$(".addroleUl").html(htm);
			
			/* ul 样式*/
			$(".addroleUl ul").toggleClass("displayUl"); 
			
			$(".addroleUl > li .textClass").click(function(){
				
				$(this).parent().children("ul").toggleClass("displayUl");
			});
			
			
			/* 级联选择 */
			$("input[name='chk']").click(function(){
				$(this).parent("li").find('input[name="chk"]').prop("checked",this.checked);
				
			});
			
		});
		
		/* 增加 */
		$(".addRoleBtn").click(function(){
			var roleCode = $("#addRoleModal #roleCode").val();
			if(kong.test(roleCode)) {
				
				$.gyzbadmin.alertFailure("角色代码不可为空");
				$("#addRoleModal #roleCode").focus();
				return;
			}
			var roleName = $("#addRoleModal #roleName").val();
			if(kong.test(roleName)) {
				
				$.gyzbadmin.alertFailure("角色名称不可为空");
				$("#addRoleModal #roleName").focus();
				return;
			}
			var memo = $("#addRoleModal #memo").val();
			if(kong.test(memo)) {
				
				$.gyzbadmin.alertFailure("角色说明不可为空");
				$("#addRoleModal #memo").focus();
				return;
			}
			
			var isValid = $("#addRoleModal #isValid").val();
			if(kong.test(isValid)) {
				
				$.gyzbadmin.alertFailure("角色是否有效不可为空");
				$("#addRoleModal #isValid").focus();
				return;
			}
			
			/* 获取被选中的值  */
			var check = $("input[name='chk']:checked");
			var value='';
			if(null != check || check.length >0){
				for(var i=0; i<check.length;i++){
					if(i!=check.length-1){
						value+= check[i].value+',';
					}else{
						value+= check[i].value;
					}
				}
			}
			
			$.blockUI();

			$.post(window.Constants.ContextPath +'<%=RolePath.BASE+RolePath.ADD %>',{
				'roleCode':roleCode,
				'roleName':roleName,
				'memo':memo,
				'isValid':isValid,
				'checkValue':value
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("增加成功！",function(){
							$("#addRoleModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("增加失败！"+data.message,function(){
							$("#addRoleModal").modal("hide");
						});
					}
				},'json'
					
			);
		});
		
		/**预览 */
		$(".previewRole").click(function(){
			var role = $.data($(this).parent().parent()[0],"role");
			
			$("#previewRoleModal #roleId").val(role.roleId);
			$("#previewRoleModal #roleName").val(role.roleName);
			$("#previewRoleModal #roleCode").val(role.roleCode);
			$("#previewRoleModal #memo").val(role.memo);
			$("#previewRoleModal #isValid").val(role.isValid);
			
			var object =$.data(funObject,"fun");
			
			/* 生成权限菜单树 */
			var htm ='';
		 	htm = menu(object);
			
			$("#previewRoleModal .roleUl").html(htm);
			
			/* ul 样式*/
			$("#previewRoleModal .roleUl ul").toggleClass("displayUl"); 
			
			$("#previewRoleModal .roleUl > li .textClass").click(function(){
				
				$(this).parent().children("ul").toggleClass("displayUl");
			});
			
			/* 级联选择 */
			$("input[name='chk']").click(function(){
				$(this).parent("li").find('input[name="chk"]').prop("checked",this.checked);
				
			});
			
			/* 已经存在的菜单标记选中 */
			$.post(window.Constants.ContextPath +'<%=RolePath.BASE + RolePath.FUNCTION%>',{

				   'roleId':role.roleId
					},function(data){
						if(data.result=="success"){
							
							var obj = data.data;
							for(var i=0;i<obj.length;i++){
								var ob = $("input[name='chk']");
								for(var j=0 ;j<ob.length;j++){
									if(ob[j].value == obj[i].functionId){
										ob[j].checked = true;
									}
								}
							}
						}else{
							
						}
					},'json'
				);
		});
	});
	
	function menu(object){
		var htm = '';
		if(null != object && object.length>0){
			for(var i=0;i<object.length;i++){
				htm = htm + '<li><input type="checkbox" name="chk" value='+object[i].functionId+'><h class="textClass">'+object[i].functionName+'</h>';
				
				if( object[i].subFunctionList!=null && object[i].subFunctionList.length>0){
					var subHtm ='<ul>';
					subHtm = subHtm + menu(object[i].subFunctionList)+'</ul>';
					htm = htm + subHtm;
				}

				htm = htm + '</li>';
			}
		}
		return htm;		
	}
		 
</script>
<body>
	<!-- 角色信息 -->
	<%@ include file="/include/top.jsp"%>
	<div class="modal fade" id="previewRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width: 600px;">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal"  aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">角色预览</h4>
	         </div>
	         <div class="modal-body">
	            <table class="modal-input-table">
	            	<tr>
						<td class="td-left" width="30%">角色编号</td>
						<td class="td-right" width="70%">
							<input type="text" id="roleId" name="roleId" readonly="readonly" style="width:80%">
						</td>
					</tr>
	            	<tr>
						<td class="td-left" width="30%">角色代码</td>
						<td class="td-right" width="70%">
							<input type="text" id="roleCode" name="roleCode" readonly="readonly" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left" >角色名称</td>
						<td class="td-right" >
							<input type="text" id="roleName" name="roleName" readonly="readonly" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left" >角色说明</td>
						<td class="td-right" >
							<input type="text" id="memo" name="memo" readonly="readonly" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left" >是否有效</td>
						<td class="td-right" >
							<select id="isValid" name="isValid" disabled="disabled" style="width:80%">
								<option value="">选择角色状态</option>
								<option value="Y">有效</option>
								<option value="N">无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td-left" >角色权限</td>
						<td class="td-right">
							<ul	class="roleUl">
							</ul>
						</td>
					</tr>
	            </table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	         </div>
	      </div>
	     </div>
	</div>	

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
							<form action='<c:url value="<%=RolePath.BASE + RolePath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								<tr>
									<td class="td-left"  >角色代码</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name=roleCode id="roleCode"  value="${requestRole.roleCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >角色名称</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="roleName"  id="roleName" value="${requestRole.roleName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=RolePath.BASE + RolePath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzbadmin:function url="<%=RolePath.BASE + RolePath.ADD%>">
												<button type="button" class="btn btn-purple btn-sm btn-margin addRole" data-toggle='modal' data-target="#addRoleModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">角色列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
										    <th>编号</th>
											<th>角色代码</th>
											<th>角色名称</th>
											<th>角色说明</th>
											<th>是否有效</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>最后更改人</th>
											<th>最后更改时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${roles }" var="role">
											<tr class="role">
												<td>${role.roleId }</td>
												<td>${role.roleCode }</td>
												<td>${role.roleName }</td>
												<td>${role.memo }</td>
												<td>
													<c:if test="${role.isValid=='Y'}">
					    								<c:out value="有效"/>
													</c:if>
													<c:if test="${role.isValid=='N'}">
														<c:out value="无效"/>
													</c:if>
												</td>
												<td>${role.instUserName }</td>
												<td><fmt:formatDate value="${role.instDatetime }" type="both"/></td>
												<td>${role.lupdUserName }</td>
												<td><fmt:formatDate value="${role.lupdDatetime }" type="both"/></td>
												<td>
													<gyzbadmin:function url="<%=RolePath.BASE + RolePath.EDIT%>">
													<a href="#"  data-toggle='modal' class="tooltip-error previewRole" data-rel="tooltip" title="预览" data-target="#previewRoleModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=RolePath.BASE + RolePath.EDIT%>">
													<a href="#" class="tooltip-success editRole" data-rel="tooltip" data-toggle='modal' data-target="#editRoleModal" title="Edit" >
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty roles}">
											<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty roles}">
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
	
					<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">角色新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
									<tr>
										<td class="td-left" width="30%">角色名称<span style="color: red">*</span></td>
										<td class="td-right" width="70%">
											<input type="text" id="roleName" name="roleName" style="width:80%">
											<label id="roleNameLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色代码<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="roleCode" name="roleCode" style="width:80%">
											<label id="memoLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色说明<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="memo" name="memo" style="width:80%">
											<label id="memoLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >是否有效<span style="color: red">*</span></td>
										<td class="td-right" >
											<select id="isValid" name="isValid" style="width:80%">
												<option value="">选择角色状态</option>
												<option value="Y">有效</option>
												<option value="N">无效</option>
											</select>
											<label id="isValidLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色权限<span style="color: red">*</span></td>
										<td class="td-right" >
											<ul	class="addroleUl" >
											</ul>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addRoleBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			
			
			
			<div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">角色修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="30%">角色编号<span style="color: red">*</span></td>
										<td class="td-right" width="70%">
											<input type="text" id="roleId" name="roleId" readonly="readonly" style="width:80%">
											<label id="roleIdLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">角色代码<span style="color: red">*</span></td>
										<td class="td-right" width="70%">
											<input type="text" id="roleCode" name="roleCode"  style="width:80%">
											<label id="roleIdLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色名称<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="roleName" name="roleName" style="width:80%">
											<label id="roleNameLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色说明<span style="color: red">*</span></td>
										<td class="td-right" >
											<input type="text" id="memo" name="memo" style="width:80%">
											<label id="memoLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >是否有效<span style="color: red">*</span></td>
										<td class="td-right" >
											<select id="isValid" name="isValid" style="width:80%">
												<option value="">选择角色状态</option>
												<option value="Y">有效</option>
												<option value="N">无效</option>
											</select>
											<label id="isValidLabelADD" class="label-tips"></label>
										</td>
									</tr>
									<tr>
										<td class="td-left" >角色权限</td>
										<td class="td-right" 	>
											<ul	class="roleUl" >
											</ul>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editRoleBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
					
					<div class="modal fade" id="deleteRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">角色信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该信息[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="roleId" id="delroleId">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteRoleBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
			

  </body>
</html>