<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.question.QuestionPath" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>问题管理列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
		<!-- 问题列表 -->
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
							<form action='<c:url value="<%=QuestionPath.BASE + QuestionPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" width="20%">问题编号</td>
									<td class="td-right" width="30%">
										<span class="input-icon">
											<input type="text" name="questNo"  id="questNoClear" value="${questNo}"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="20%">问题详情</td>
									<td class="td-right" width="30%">
										<span class="input-icon">
											<input type="text" name="questContent" id="questContentClear" value="${questContent }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn"> 
											<gyzbadmin:function url="<%=QuestionPath.BASE + QuestionPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin clearQuest" >
														清空
														<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzbadmin:function url="<%=QuestionPath.BASE + QuestionPath.ADD%>">
												<button class="btn btn-purple btn-sm addQuest" data-toggle='modal' data-target="#addQuestModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
						<div class="list-table-header">问题列表</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width ="10%">问题编号</th>
											<th width ="70%">问题详情</th>
											<th width ="10%">状态</th>
											<th width ="10%">操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${questionList }" var="question">
										<tr class="question">
											<td>${question.questNo }</td>
											<td>${question.questContent }</td>
											<td>
											<c:if test="${question.status =='VALID'}">
												有效
											</c:if>
											<c:if test="${question.status =='DISABLE'}">
												无效
											</c:if>
											</td>
											<td>
												<input type="hidden" name="questNo" value="${question.questNo }"/>
												<gyzbadmin:function url="<%=QuestionPath.BASE + QuestionPath.EDIT%>">
													<a href="#" class="tooltip-success editquest" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#editQuestModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=QuestionPath.BASE + QuestionPath.DELETE%>">
													<a href="#" class="tooltip-error deleteQuest" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteQuestModal">
														<span class="red"><i class="icon-trash bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty questionList}">
										<tr><td colspan="4" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty questionList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div>
					<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
	 		   <!-- ADD Qusetion -->
	           <div class="modal fade" id="addQuestModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">问题新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
									<tr>
										<td class="td-left">问题详情<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<textarea rows="1" Style="width:90%" id="questContent" name="questContent" maxlength="500"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addQuestBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
					
				<!-- Update Question -->
				<div class="modal fade" id="editQuestModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">问题修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="20%">问题编号<span style="color:red">*</span>	</td>
										<td class="td-right" width="30%">
											<input type="text" id=questNo name="questNo" readonly="readonly" style="background:#EEE">
										</td>
										<td class="td-right" colspan="2"></td>
										
									</tr>
									<tr>
										<td class="td-left">问题详情<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<textarea rows="1" Style="width:90%" id="questContent" name="questContent" maxlength="500"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editQuestBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
				<!-- Delete Question -->
				<div class="modal fade" id="deleteQuestModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">问题</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该问题[<span class="questNo"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="questNo" class="questNo" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteQuestBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div>
					  
<script type="text/javascript">

	jQuery(function($){
		// 为每个tr缓存数据
		var questions= '<c:out value="${gyzb:toJSONString(questionList)}" escapeXml="false"/>';
		var questList=$("tr.question");
		$.each($.parseJSON(questions),function(i,value){
			$.data(questList[i],"question",value);
		});
		
		$('.clearQuest').click(function(){
			$('#questNoClear').val('');
			$('#questContentClear').val('');
		})
		//新增
		$('.addQuestBtn').click(function(){
			
			// 问题详情 
			var questContent = $('#addQuestModal #questContent').val();
			if(kong.test(questContent)) {
				$.gyzbadmin.alertFailure('问题详情不可为空');
				return;
			}
			// 保存
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=QuestionPath.BASE + QuestionPath.ADD %>', 
			{
				 'questContent'	: questContent
			}, function(data) {
					$.unblockUI();   
					if(data.result == 'SUCCESS'){
						$('#addQuestModal').modal('hide');
						$.gyzbadmin.alertSuccess('新增成功', null, function(){
							window.location.reload();
						});
					}else if(data.result == 'EXISTS'){
						$.gyzbadmin.alertFailure('编号已存在');
					}
					else {
						$.gyzbadmin.alertFailure('保存失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		//弹出修改框
	    $('.editquest').click(function(){
			var quest = $.data($(this).parent().parent()[0], 'question');
	       $('#editQuestModal').on('show.bs.modal', function () {
				$('#editQuestModal #questNo').val(quest.questNo);
				$('#editQuestModal #questContent').val(quest.questContent);
			});
	       $('#editQuestModal').on('hide.bs.modal', function () {
				// 清除
				$('#editQuestModal #questNoClear').val('');
				$('#editQuestModal #questContentClear').val('');
			});
		}); 
		
	  //保存修改
		$('.editQuestBtn').click(function(){
			// 问题编号 
			var questNo = $('#editQuestModal #questNo').val();
			if(kong.test(questNo)) {
				$.gyzbadmin.alertFailure('问题编号不可为空');
				return;
			}
			// 问题详情 
			var questContent = $('#editQuestModal #questContent').val();
			if(kong.test(questContent)) {
				$.gyzbadmin.alertFailure('问题详情不可为空');
				return;
			}
			// 保存修改
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=QuestionPath.BASE + QuestionPath.EDIT %>', {
					'questNo' 	: questNo,
					'questContent'	: questContent
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#editQuestModal').modal('hide');
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
		$('.deleteQuest').click(function(){
			var questNo = $(this).parent().find('input[name="questNo"]').val();
			$('#deleteQuestModal').on('show.bs.modal', function () {
				// 赋值
				$('#deleteQuestModal span.questNo').html(questNo);
				$('#deleteQuestModal input.questNo').val(questNo);
			});
			$('#deleteQuestModal').on('hide.bs.modal', function () {
				// 清除
				$('#deleteQuestModal span.questNo').empty();
				$('#deleteQuestModal input.questNo').val('');
			});
		});
		
		// 删除角色
		$('.deleteQuestBtn').click(function(){
			var questNo = $('#deleteQuestModal input.questNo').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=QuestionPath.BASE + QuestionPath.DELETE %>', {
					'questNo' 	: questNo
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#deleteQuestModal').modal('hide');
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
</body>
</html>

