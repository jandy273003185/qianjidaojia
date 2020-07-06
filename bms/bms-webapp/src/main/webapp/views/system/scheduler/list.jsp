<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.scheduler.SchedulerJobPath"%>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>任务调度配置管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadSchedulerJob()">
	<!-- 任务调度配置信息 -->
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
						 <input type="hidden"  name="isEnable2"  id="isEnable2" value="${isEnable}"/>
							<!-- 查询条件 -->
							<form action='<c:url value="<%=SchedulerJobPath.BASE + SchedulerJobPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
								
									<td class="td-left" width="20%">任务名称</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="jobName"   id="jobName"  value="${jobName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="20%">执行类路径</td>
									<td class="td-right" >
									<span class="input-icon">
											<input type="text" name="classPath"  id="classPath"  value="${classPath }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
								
									<td class="td-left">执行主机</td>
									<td class="td-right">
									<span class="input-icon">
											<input type="text" name="hostName"  id="hostName"  value="${hostName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">是否开启</td>
									<td class="td-right">
										<span class="input-icon">
											<select id="isEnable" name="isEnable" class="width-90">
												<option value="">- 请选择 -</option>
												<option value="Y">- 开启 -</option>
												<option value="N">- 停止 -</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=SchedulerJobPath.BASE + SchedulerJobPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin clearSchedulerJob" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzbadmin:function url="<%=SchedulerJobPath.BASE + SchedulerJobPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addJobLink" data-toggle='modal' data-target="#addJobModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">任务调度配置列表</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="5%">执行主机</th>
											<th width="10%">任务名称</th>
											<th width="18%">执行类路径</th>
											<th width="8%">执行CRON时间</th>
											<th width="5%">是否启用</th>
											<th width="5%">任务状态</th>
											<th width="18%">业务参数</th>
											<th width="6%">创建人</th>
											<th width="12%">创建时间</th>
											<th width="6%">备注</th>
											<th width="10%">操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${jobList }" var="jobVo">
										<tr class="jobVo" >
											<td>${jobVo.hostName }</td>
											<td>${jobVo.jobName }</td>
											<td>${jobVo.classPath }</td>
											<td>${jobVo.cron }</td>
											<td>
											<c:if test="${jobVo.isEnable=='Y' }">
												开启
											</c:if>
											<c:if test="${jobVo.isEnable=='N' }">
												停止
											</c:if>
											</td>
											<td>${jobVo.state}</td>
											<td>${jobVo.parameter }</td>
											<td>${jobVo.instUserName}</td>
											<td>
												<fmt:formatDate value="${jobVo.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>${jobVo.memo }</td>
											<td>
												<input type="hidden" name="id" value="${jobVo.id}"/>
												<gyzbadmin:function url="<%=SchedulerJobPath.BASE + SchedulerJobPath.EDIT%>">
													<a href="#" class="tooltip-success editJobLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#editJobModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=SchedulerJobPath.BASE + SchedulerJobPath.DELETE%>">
													<a href="#" class="tooltip-error deleteJobLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteJobModal">
														<span class="red"><i class="icon-trash bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=SchedulerJobPath.BASE + SchedulerJobPath.EXECUTE%>">
													<button type="button" class="btn btn-primary btn-xs executeJobLink" data-toggle='modal' data-target="#executeJobModal">
														执行
													</button>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty jobList}">
										<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty jobList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
							
							
						</div>
					</div>
						 
					<!-- 新增任务调度配置模态框（Modal） -->
					<div class="modal fade" id="addJobModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">任务新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
					            		<td class="td-left" width="20%">任务名称<span style="color:red">*</span></td>
										<td class="td-right" width="80%">
											<input type="text" id="jobName" name="jobName" maxlength="50" class="width-90">
										</td>
									</tr>
									<tr>
									    <td class="td-left" >执行类路径<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="classPath" name="classPath" maxlength="200" class="width-90">
										</td>
									</tr>
									<tr>
									    <td class="td-left" >执行CRON时间<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="cron" name="cron" maxlength="20" class="width-90">
										</td>
									</tr>
									<tr>
									    <td class="td-left" >执行主机<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="hostName" name="hostName" maxlength="20" class="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">是否开启<span style="color:red">*</span></td>
										<td class="td-right">
											<select id="isEnable" name="isEnable" class="width-90">
												<option value="Y">- 开启 -</option>
												<option value="N">- 停止 -</option>
											</select>
										</td>
									</tr>
									<tr>
									    <td class="td-left" >业务参数</td>
										<td class="td-right" >
											<input type="text" id="parameter" name="parameter" maxlength="200" class="width-90">
										</td>
									</tr>
									<tr>
									    <td class="td-left" >备注</td>
										<td class="td-right" >
											<textarea rows="2" id="memo" name="memo" maxlength="300" class="width-90"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addJobBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<!-- 删除任务调度配置模态框（Modal） -->
					<div class="modal fade" id="deleteJobModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">任务删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该任务调度配置[<span class="id"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" class="id" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteJobBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<!-- 修改任务调度配置模态框（Modal） -->
					<div class="modal fade" id="editJobModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">任务修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
										 <tr>
											<td class="td-left" width="20%">任务名称<span style="color:red">*</span></td>
											<td class="td-right" width="80%">
												<input type="text" id="jobName" name="jobName" maxlength="50" class="width-90">
												<input type="hidden" id="id" name="id" readonly="readonly" style="background-color: #EEEEEE;">
											</td>
										</tr>
										<tr>
											<td class="td-left">执行类路径<span style="color:red">*</span></td>
											<td class="td-right">
												<input type="text" id="classPath" name="classPath" maxlength="200" class="width-90">
											</td>
										</tr>
								   		<tr>
											<td class="td-left">执行CRON时间<span style="color:red">*</span></td>
											<td class="td-right">
												<input type="text" id="cron" name="cron" maxlength="20" class="width-90">
											</td>
										</tr>
										<tr>
										    <td class="td-left" >执行主机<span style="color:red">*</span></td>
											<td class="td-right" >
												<input type="text" id="hostName" name="hostName" maxlength="20" class="width-90">
											</td>
									    </tr>
										<tr>
											<td class="td-left">是否开启<span style="color:red">*</span></td>
											<td class="td-right">
												<select id="isEnable" name="isEnable" class="width-90">
												<option value="Y">- 开启 -</option>
												<option value="N">- 停止 -</option>
											</select>
											</td>
										</tr>
										<tr>
											<td class="td-left">业务参数</td>
											<td class="td-right">
												<input type="text" id="parameter" name="parameter" maxlength="200" class="width-90">
											</td>
										</tr>
										<tr>
											<td class="td-left">备注</td>
											<td class="td-right" >
												<textarea rows="2" id="memo" name="memo" maxlength="300" class="width-90"></textarea>
											</td>
										</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editJobBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					
					<!-- 执行任务调度配置模态框（Modal） -->
					<div class="modal fade" id="executeJobModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">任务执行</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
										 <tr>
											<td class="td-left" width="20%">任务名称<span style="color:red">*</span></td>
											<td class="td-right" width="80%">
												<input type="text" id="jobName" name="jobName" maxlength="50" class="width-90">
												<input type="hidden" id="id" name="id" readonly="readonly" style="background-color: #EEEEEE;">
											</td>
										</tr>
										<tr>
											<td class="td-left">执行类路径<span style="color:red">*</span></td>
											<td class="td-right">
												<input type="text" id="classPath" name="classPath" readonly="readonly" maxlength="200" class="width-90">
											</td>
										</tr>
										<tr>
										    <td class="td-left" >执行主机<span style="color:red">*</span></td>
											<td class="td-right" >
												<input type="text" id="hostName" name="hostName" readonly="readonly" maxlength="20" class="width-90">
											</td>
									    </tr>
									    <tr>
											<td class="td-left">执行参数<span style="color:red">*</span></td>
											<td class="td-right">
												<input type="text" id="executeParameter" name="executeParameter" maxlength="200" class="width-90">
											</td>
										</tr>
										<tr>
											<td class="td-left">业务参数</td>
											<td class="td-right">
												<input type="text" id="parameter" name="parameter" readonly="readonly" maxlength="200" class="width-90">
											</td>
										</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary executeJobBtn">执行</button>
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
		function loadSchedulerJob(){
			var isEnable2=$("#isEnable2").val();
			$("#isEnable").val(isEnable2);
		}
		jQuery(function($){
			$('.clearSchedulerJob').click(function(){
				$('.search-table #jobName').val('');
				$('.search-table #classPath').val('');
				$('.search-table #hostName').val('');
				$('.search-table #isEnable').val('');
			}) 
				var jobs= ${jobList};
				var jobList=$("tr.jobVo");
				$.each(jobs,function(i,value){
					$.data(jobList[i],"jobs",value);
				});
				
			// 新增单个任务调度配置
			$('.addJobBtn').click(function(){
				//任务名称
				var jobName = $('#addJobModal #jobName').val();
				if(kong.test(jobName)) {
					$.gyzbadmin.alertFailure('任务名称不可为空');
					return;
				}
				
				// 执行类路径
				var classPath = $('#addJobModal #classPath').val();
				if(kong.test(classPath)) {
					$.gyzbadmin.alertFailure('执行类路径不可为空');
					return;
				}
				// 执行主机
				var hostName = $('#addJobModal #hostName').val();
				if(kong.test(hostName)) {
					$.gyzbadmin.alertFailure('服务名称不可为空');
					return;
				}
				// 执行CRON时间 
				var cron = $('#addJobModal #cron').val();
				if(kong.test(cron)) {
					$.gyzbadmin.alertFailure('执行时间不可为空');
					return;
				}
				// 业务参数
				var parameter = $('#addJobModal #parameter').val();
				
			
				// 保存
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=SchedulerJobPath.BASE + SchedulerJobPath.ADD %>', {
						'jobName'	: jobName,
						'classPath'	: classPath,
						'cron'	: cron,
						'hostName'	: hostName,
						'parameter':parameter,
						'isEnable'	: $('#addJobModal #isEnable').val(),
						'memo'	: $('#addJobModal #memo').val(),
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#addJobModal').modal('hide');
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
			$('.deleteJobLink').click(function(){
				var id = $(this).parent().find('input[name="id"]').val();
				$('#deleteJobModal').on('show.bs.modal', function () {
					// 赋值
					$('#deleteJobModal span.id').html(id);
					$('#deleteJobModal input.id').val(id);
				});
				$('#deleteJobModal').on('hide.bs.modal', function () {
					// 清除
					$('#deleteJobModal span.id').empty();
					$('#deleteJobModal input.id').val('');
				});
			});
	
			// 删除任务调度配置
			$('.deleteJobBtn').click(function(){
				var id = $('#deleteJobModal input.id').val();
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=SchedulerJobPath.BASE + SchedulerJobPath.DELETE %>', {
						'id' : id
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#deleteJobModal').modal('hide');
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
			$('.editJobLink').click(function(){
				
				var job = $.data($(this).parent().parent()[0],"jobs");
				$('#editJobModal').on('show.bs.modal', function () {
					// 赋值
					$('#editJobModal #id').val(job.id);
					$('#editJobModal #jobName').val(job.jobName);
					$('#editJobModal #classPath').val(job.classPath);
					$('#editJobModal #hostName').val(job.hostName);
					$('#editJobModal #parameter').val(job.parameter);
					$('#editJobModal #memo').val(job.memo);
					$('#editJobModal #isEnable').val(job.isEnable);
					$('#editJobModal #cron').val(job.cron);
					
				});
				
				$('#editJobModal').on('hide.bs.modal', function () {
					// 清除
					$('#editJobModal #id').val('');
					$('#editJobModal #jobName').val('');
					$('#editJobModal #classPath').val('');
					$('#editJobModal #hostName').val('');
					$('#editJobModal #parameter').val('');
					$('#editJobModal #memo').val('');
					$('#editJobModal #isEnable').val('');
					$('#editJobModal #cron').val('');
				});
			});
			
			// 修改任务调度配置
			$('.editJobBtn').click(function(){
				var jobName = $('#editJobModal #jobName').val();
				if(kong.test(jobName)) {
					$.gyzbadmin.alertFailure('任务名称不可为空');
					return;
				}
				
				// 执行类路径
				var classPath = $('#editJobModal #classPath').val();
				if(kong.test(classPath)) {
					$.gyzbadmin.alertFailure('执行类路径不可为空');
					return;
				}
				// 服务名称
				var hostName = $('#editJobModal #hostName').val();
				if(kong.test(hostName)) {
					$.gyzbadmin.alertFailure('服务名称不可为空');
					return;
				}
				// 执行时间
				var cron = $('#editJobModal #cron').val();
				if(kong.test(cron)) {
					$.gyzbadmin.alertFailure('执行时间不可为空');
					return;
				}
				// 业务参数
				var parameter = $('#editJobModal #parameter').val();
				
			
				// 保存修改
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=SchedulerJobPath.BASE + SchedulerJobPath.EDIT %>', {
					'jobName'	: jobName,
					'classPath'	: classPath,
					'cron'	: cron,
					'hostName'	: hostName,
					'parameter':parameter,
					'isEnable'	: $('#editJobModal #isEnable').val(),
					'memo'	: $('#editJobModal #memo').val(),
					'id'	: $('#editJobModal #id').val()
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#editJobModal').modal('hide');
							$.gyzbadmin.alertSuccess('修改成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
						}
					}, 'json'
				);
			});



			// 弹出执行层准备工作
			$('.executeJobLink').click(function(){
				
				var job = $.data($(this).parent().parent()[0],"jobs");
				$('#executeJobModal').on('show.bs.modal', function () {
					// 赋值
					$('#executeJobModal #id').val(job.id);
					$('#executeJobModal #jobName').val(job.jobName);
					$('#executeJobModal #classPath').val(job.classPath);
					$('#executeJobModal #hostName').val(job.hostName);
					$('#executeJobModal #parameter').val(job.parameter);
					$('#executeJobModal #executeParameter').val('');
					
				});
				
				$('#executeJobModal').on('hide.bs.modal', function () {
					// 清除
					$('#executeJobModal #id').val('');
					$('#executeJobModal #jobName').val('');
					$('#executeJobModal #classPath').val('');
					$('#executeJobModal #hostName').val('');
					$('#executeJobModal #parameter').val('');
					$('#executeJobModal #executeParameter').val('');
				});
			});
			
			// 修改任务调度配置
			$('.executeJobBtn').click(function(){
				// 保存
				$.blockUI();
				$.post(window.Constants.ContextPath + '<%=SchedulerJobPath.BASE + SchedulerJobPath.EXECUTE %>', {
					'id'				: $('#executeJobModal #id').val(),
					'parameter'			: $('#executeJobModal #parameter').val(),
					'hostName'			: $('#executeJobModal #hostName').val(),
					'executeParameter'	: $('#executeJobModal #executeParameter').val()
					}, function(data) {
						$.unblockUI();
						if(data.result == 'SUCCESS'){
							$('#executeJobModal').modal('hide');
							$.gyzbadmin.alertSuccess('执行成功', null, function(){
								window.location.reload();
							});
						} else {
							$.gyzbadmin.alertFailure('执行失败:' + data.message);
						}
					}, 'json'
				);
			});
		});
	</script>
</body>
</html>