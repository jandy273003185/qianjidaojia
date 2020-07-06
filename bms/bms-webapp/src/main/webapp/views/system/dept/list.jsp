<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.admin.dept.DeptPath" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>部门管理</title>
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
	var depts= '<c:out value="${gyzb:toJSONString(deptList)}" escapeXml="false"/>';
	var deptList=$("tr.dept");
	$.each($.parseJSON(depts),function(i,value){
		$.data(deptList[i],"dept",value);
	});
	//新增
	$('.addDeptBtn').click(function(){
		
		// 部门代码
		var deptName = $('#addDeptModal #deptName').val();
		if(kong.test(deptName)) {
			$.gyzbadmin.alertFailure('部门名称不可为空');
			return;
		}
		// 部门代码
		var deptCode = $('#addDeptModal #deptCode').val();
		if(kong.test(deptCode)) {
			$.gyzbadmin.alertFailure('部门代码不可为空');
			return;
		}
		
		// 部门级别
		var deptLevel = $('#addDeptModal #deptLevel').val();
		if(kong.test(deptLevel)) {
			$.gyzbadmin.alertFailure('部门级别不可为空');
			return;
		}
		
		// 上级部门编号
		var upDeptId = $('#addDeptModal #upDeptId').val();
		if(kong.test(upDeptId)) {
			$.gyzbadmin.alertFailure('上级部门不可为空');
			return;
		}
		// 部门说明
		var memo = $('#addDeptModal #memo').val();
		
		// 部门地址
		var deptAddress = $('#addDeptModal #deptAddress').val();
		
		var status = $('#addDeptModal #status').val();
		
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure('部门状态不可为空');
			return;
		}
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=DeptPath.BASE + DeptPath.ADD %>', 
		{
			 'deptName'	: deptName,
			 'deptCode'	: deptCode,
			 'deptLevel': deptLevel,
			 'upDeptId'	: upDeptId,
			 'deptAddress'	: deptAddress,
			 'status'	: status,
			 'memo'	: memo
		}, function(data) {
				$.unblockUI();   
				if(data.result == 'SUCCESS'){
					$('#addDeptModal').modal('hide');
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
    $('.editDept').click(function(){
		var dept = $.data($(this).parent().parent()[0], 'dept');
       $('#editDeptModal').on('show.bs.modal', function () {
			$('#editDeptModal #deptId').val(dept.deptId);
			$('#editDeptModal #deptName').val(dept.deptName);
			$('#editDeptModal #deptCode').val(dept.deptCode);
			$('#editDeptModal #deptLevel').val(dept.deptLevel);
			$('#editDeptModal #deptAddress').val(dept.deptAddress);
			$('#editDeptModal #upDeptId').val(dept.upDeptId);
			$('#editDeptModal #memo').val(dept.memo);
			$('#editDeptModal #status').val(dept.status);
		});
       $('#editDeptModal').on('hide.bs.modal', function () {
			// 清除
    	    $('#editDeptModal #deptId').val('');
			$('#editDeptModal #deptName').val('');
			$('#editDeptModal #deptCode').val('');
			$('#editDeptModal #deptLevel').val('');
			$('#editDeptModal #deptAddress').val('');
			$('#editDeptModal #upDeptId').val('');
			$('#editDeptModal #memo').val('');
			$('#editDeptModal #status').val('');
		});
	}); 
	
  //保存修改
	$('.editDeptBtn').click(function(){
		// 部门编号
		var deptId = $('#editDeptModal #deptId').val();
		if(kong.test(deptId)) {
			$.gyzbadmin.alertFailure('部门编号不可为空');
			return;
		}
		// 部门名称
		var deptName = $('#editDeptModal #deptName').val();
		if(kong.test(deptName)) {
			$.gyzbadmin.alertFailure('部门名称不可为空');
			return;
		}
		// 部门代码
		var deptCode = $('#editDeptModal #deptCode').val();
		if(kong.test(deptCode)) {
			$.gyzbadmin.alertFailure('部门代码不可为空');
			return;
		}
		
		// 部门级别
		var deptLevel = $('#editDeptModal #deptLevel').val();
		if(kong.test(deptLevel)) {
			$.gyzbadmin.alertFailure('部门级别不可为空');
			return;
		}
		
		// 上级部门
		var upDeptId = $('#editDeptModal #upDeptId').val();
		if(kong.test(upDeptId)|| upDeptId==null) {
			$.gyzbadmin.alertFailure('上级部门不可为空');
			return;
		}
		// 部门说明
		var memo = $('#editDeptModal #memo').val();
		
		// 部门地址
		var deptAddress = $('#editDeptModal #deptAddress').val();
		
		var status = $('#editDeptModal #status').val();
		
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure('部门状态不可为空');
			return;
		}
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=DeptPath.BASE + DeptPath.EDIT %>', 
		{
			 'deptId' 	: deptId,
			 'deptName'	: deptName,
			 'deptCode'	: deptCode,
			 'deptLevel': deptLevel,
			 'upDeptId'	: upDeptId,
			 'deptAddress':deptAddress,
			 'status'	: status,
			 'memo'	: memo
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#editDeptModal').modal('hide');
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
	$('.deleteDept').click(function(){
		var deptId = $(this).parent().find('input[name="deptId"]').val();
		$('#deleteDeptModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteDeptModal span.deptId').html(deptId);
			$('#deleteDeptModal #deptId').val(deptId);
		});
		$('#deleteDeptModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteDeptModal span.deptId').empty();
			$('#deleteDeptModal #deptId').val('');
		});
	});
	
	// 删除部门
	$('.deleteDeptBtn').click(function(){
		var deptId = $('#deleteDeptModal #deptId').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=DeptPath.BASE + DeptPath.DELETE %>', {
				'deptId' 	: deptId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteDeptModal').modal('hide');
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
	<!-- 部门信息 -->
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
							<form action='<c:url value="<%=DeptPath.BASE + DeptPath.LIST %>"/>' method="post" id="form">
							<table class="search-table">
								<tr>
									<td class="td-left" >部门代码</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=deptCode  id="deptCode"  value="${requestDept.deptCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >部门名称</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="deptName"   id="deptName"  value="${requestDept.deptName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzb-admin:function url="<%=DeptPath.BASE + DeptPath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="<%=DeptPath.BASE + DeptPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addDept" data-toggle='modal' data-target="#addDeptModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">部门列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>部门编号</th>
											<th>部门名称</th>
											<th>部门代码</th>
											<th>部门级别</th>
											<th>上级部门</th>
											<th>是否有效</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>更新人</th>
											<th>更新时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${deptList}" var="dept">
											<tr class="dept">
												<td>${dept.deptId }</td>
												<td>${dept.deptName }</td>
												<td>${dept.deptCode }</td>
											    <td>${dept.deptLevel }</td>
											    <td>${dept.upDeptId }</td>
												<td>
													<c:if test="${dept.status=='VALID'}">
					    								<c:out value="有效"/>
													</c:if>
													<c:if test="${dept.status=='DISABLE'}">
														<c:out value="无效"/>
													</c:if>
												</td>
												<td>${dept.instUserName }</td>
												<td>${dept.instDatetime }</td>
												<td>${dept.lupdUserName }</td>
												<td>${dept.lupdDatetime }</td>
												<td>
													<input type="hidden" name="deptId" value="${dept.deptId }"/>
													<gyzbadmin:function url="<%=DeptPath.BASE + DeptPath.EDIT %>">
													<a href="#" class="tooltip-success editDept" data-rel="tooltip" data-toggle='modal' data-target="#editDeptModal" title="Edit" >
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty deptList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty deptList}">
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
					<div class="modal fade" id="addDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">部门新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="25%">部门名称<span style="color: red">*</span></td>
										<td class="td-right" width="75%">
												<input type="text" id="deptName" name="deptName" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>
										<td class="td-left" >部门代码<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="deptCode" name="deptCode" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >部门级别<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="deptLevel" name="deptLevel" onkeyup="value=value.replace(/[^\d]/g,'')" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >上级部门<span style="color: red">*</span></td>
										<td class="td-right" >
											<gyzbadmin:selectDept id="upDeptId" name="upDeptId" clasS="width-90"/>
										</td>
									</tr>
								
									<tr>
										<td class="td-left" >是否有效<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="status" name="status" clasS="width-90"/>
													<option value="">选择部门状态</option>
													<option value="VALID">有效</option>
													<option value="DISABLE">无效</option>
												</select>
										</td>
									</tr>
									<tr>
										 <td class="td-left" >部门地址</td>
									    <td class="td-right" >
											<input type="text" id="deptAddress" name="deptAddress" clasS="width-90"/>
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
					            <button type="button" class="btn btn-primary addDeptBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			

			
			<div class="modal fade" id="editDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">部门修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="25%">部门编号<span style="color: red">*</span></td>
										<td class="td-right" width="75%">
											<input type="text" id="deptId" name="deptId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >部门名称<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="deptName" name="deptName" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >部门代码<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="deptCode" name="deptCode" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >部门级别<span style="color: red">*</span></td>
										<td class="td-right" >
												<input type="text" id="deptLevel" name="deptLevel" onkeyup="value=value.replace(/[^\d]/g,'')" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" >上级部门<span style="color: red">*</span></td>
										<td class="td-right" >
											<gyzbadmin:selectDept id="upDeptId" name="upDeptId" clasS="width-90"/>
										</td>
									</tr>
								
									<tr>
										<td class="td-left" >是否有效<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="status" name="status" clasS="width-90"/>
													<option value="">选择部门状态</option>
													<option value="VALID">有效</option>
													<option value="DISABLE">无效</option>
												</select>
										</td>
									</tr>
									<tr>
										 <td class="td-left" >部门地址</td>
									    <td class="td-right" >
											<input type="text" id="deptAddress" name="deptAddress" clasS="width-90"/>
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
					            <button type="button" class="btn btn-primary editDeptBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
						
					<div class="modal fade" id="deleteDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">部门信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该部门[<span class="deptId"></span>]么？</font>
					         	<input type="hidden" id="deptId" />
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteDeptBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
  </body>
</html>