<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.qifenqian.bms.basemanager.protocoltemplate.ProtocolTemplatePath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<html>
<head>
<meta charset="utf-8" />
<title>协议模板管理</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}
.uploadImage {
	float: left;
	background: url(<%=request.getContextPath ()%>/static/images/upload.jpg);
	background-size: 120px 100px;
	width: 100px;
	height: 80px;
}
</style>
</head>
<body onload="loadProtocolTemplate()">
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
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
							<form
								action='<c:url value="<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.LIST%>"/>'
								method="post">
								<input type="hidden" id="fixContent" name="fixContent" value="${fixContent}">
								<table class="search-table">
									<tr>
										<td class="td-left">协议模板名称</td>
										<td class="td-right">
											<span class="input-icon"> 
												<input type="text" name="protocolTemplateName" id="protocolTemplateName" value="${queryBean.protocolTemplateName}">
												 <i class="icon-leaf blue"></i>
											</span>
										</td>
										<td class="td-left">协议模板类型</td>
										<td class="td-right">
											<span class="input-icon"> 
											<input type="hidden" name="protocolTypeTemp" id="protocolTypeTemp" value="${queryBean.protocolType}">
												<input type="text" name="protocolType" id="protocolType">
												 <i class="icon-leaf blue"></i>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<span class="input-group-btn"> 
												<gyzbadmin:function url="<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.LIST%>">
													<button type="submit" class="btn btn-purple btn-sm">
														查询 
														<i class="icon-search icon-on-right bigger-110"></i>
													</button>
												</gyzbadmin:function>
													<button class="btn btn-purple btn-sm btn-margin clearProtocolTemplate">
														清空 
														<i class=" icon-move icon-on-right bigger-110"></i>
													</button>
												<gyzbadmin:function url="<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.ADD%>">
													<button class="btn btn-purple btn-sm btn-margin addProtocolTemplate" data-toggle='modal' data-target="#addProtocolTemplateModal">
														新增
														<i class="icon-plus-sign icon-on-right bigger-110"></i>
													</button>
											    </gyzbadmin:function>
											</span>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">协议模板列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout: fixed;">
									<thead>
										<tr>
											<th width="10%">协议模板编号</th>
											<th width="10%">协议模板名称</th>
											<th width="10%">协议模板类型</th>
											<th width="30%">协议模板内容</th>
											<th width="8%">商户类型</th>
											<th width="8%">生效日期</th>
											<th width="7%">失效日期</th>
											<th width="5%">版本号</th>
											<th width="10%">备注</th>
											<th width="5%">状态</th>
											<th width="5%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${protocolTemplates}" var="protocolTemplate">
											<tr class="protocolTemplate">
												<td>${protocolTemplate.id}</td>
												<td>${protocolTemplate.protocolTemplateName}</td>
												<td>${protocolTemplate.protocolType}</td>
												<td>${protocolTemplate.protocolTemplate}</td>
												<td>
													<c:if test="${protocolTemplate.merchantType == '1'}">
														普通商户
													</c:if>
													<c:if test="${protocolTemplate.merchantType == '2'}">
														代理商户
													</c:if>
												</td>
												<td>${protocolTemplate.validDate}</td>
												<td>${protocolTemplate.disableDate}</td>
												<td>${protocolTemplate.protocolVersion}</td>
												<td>${protocolTemplate.memo}</td>
												<td>
													<c:if test="${protocolTemplate.status == 'VALID'}">
														有效
													</c:if>
													<c:if test="${protocolTemplate.status == 'DISABLE'}">
														失效
													</c:if>
												</td>
												<td>
													<gyzbadmin:function url="<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.EDIT%>">
														<a href="#editProtocolTemplateModal" data-toggle='modal' class="tooltip-success editProtocolTemplate" data-rel="tooltip" title="修改"> 
															<span class="green"> 
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty protocolTemplates}">
											<tr>
												<td colspan="10" align="center"><font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty protocolTemplates}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
				 <!-- ADD ProtocolTemplate -->
	           <div class="modal fade" id="addProtocolTemplateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">新增协议模板</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
									 <tr>
										<td class="td-left" width="20%">协议模板名称<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolTemplateName" name="protocolTemplateName" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">协议模板类型<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolType" name="protocolType" class="width-100">
										</td>
									</tr>
								    <tr>
										<td class="td-left" width="20%">商户类型<span style="color:red">*</span></td>
										<td class="td-right">
											<select name="merchantType" id="merchantType">
												<option value="">--请选择--</option>
												<option value="1">普通商户</option>
												<option value="2">代理商</option>
											</select>
											<!-- <input type="text" id="merchantType" name="merchantType" class="width-100"> -->
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">版本号<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolVersion" name="protocolVersion" class="width-100">
										</td>
									</tr>
									 <tr class="fixedRule">
										<td class="td-left" width="20%">生效时间<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="validDate" name="validDate" readonly="readonly" onfocus="WdatePicker({minDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">协议模板内容（必填）<span style="color:red">*</span></td>
										<td class="td-right">
											<span class="contentSpan" > </span>
											<!-- <textarea rows="4" cols="" name ="templateMustContent" id ="templateMustContent" readonly="readonly"  maxlength="4000" class="width-100"></textarea>
 -->										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">协议模板内容（选填）<span style="color:red"></span></td>
										<td class="td-right">
											<textarea rows="3" cols="" name ="protocolTemplate" id ="protocolTemplate"  maxlength="4000" class="width-100"></textarea>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">备注</td>
										<td class="td-right">
											<textarea rows="2" cols="" name ="memo" id ="memo"  maxlength="300" class="width-100"></textarea>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%"></td>
										<td class="td-right">
											<span style="color:red">（协议模板结算费率不用设置，后台自动根据产品添加。）</span>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addProtocolTemplateBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
						
				<!-- Update ProtocolTemplate -->
				<div class="modal fade" id="editProtocolTemplateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">协议模板修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
						            	<td class="td-left" width="20%">协议模板编号<span style="color:red">*</span></td>
						            	<td class="td-right">
						            		<input type="text" id="id" name="id" readonly="readonly" class="width-100"/>
						            	</td>
					            	</tr>
					            	 <tr>
										<td class="td-left" width="20%">协议模板名称<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolTemplateName" name="protocolTemplateName" readonly="readonly" class="width-100"/>
										</td>
									</tr>
					            	<tr>
						            	<td class="td-left" width="20%">协议模板类型<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolType" name="protocolType" class="width-100">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">商户类型<span style="color:red">*</span></td>
										<td class="td-right">
											<select name="merchantType" id="merchantType">
												<option value="">--请选择--</option>
												<option value="1">普通商户</option>
												<option value="2">代理商</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">版本号<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="protocolVersion" name="protocolVersion" class="width-100">
										</td>
					            	</tr>
					            	<tr>
										<td class="td-left" width="20%">协议模板内容（必填）<span style="color:red">*</span></td>
										<td class="td-right">
											<span class="contentSpan" > </span>
											<!-- <textarea rows="3" cols="" name ="templateMustContent" id ="templateMustContent" readonly="readonly"  maxlength="4000" class="width-100"></textarea> -->
										</td>
									</tr>
					            	<tr>
										<td class="td-left" width="20%">协议模板内容（选填）<span style="color:red"></span></td>
										<td class="td-right" colspan="3">
											<textarea rows="3" cols="" name ="protocolTemplate" id ="protocolTemplate"  maxlength="4000" class="width-100"></textarea>
										</td>
									</tr>
									 <tr >
										<td class="td-left" width="20%">生效时间<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="validDate" name="validDate" readonly="readonly" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">失效时间</td>
										<td class="td-right">
											<input type="text" id="disableDate" name="disableDate" readonly="readonly" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" class="width-100">
										</td>
									</tr>
									<tr >
										<td class="td-left" width="20%">状态<span style="color:red">*</span></td>
										<td class="td-right">
											<select id="status" name="status" class="width-100">
												<option value="">-请选择-</option>
												<option value="VALID">-有效-</option>
												<option value="DISABLE">-失效-</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">备注</td>
										<td class="td-right">
											<textarea rows="2" cols="" name ="memo" id ="memo"  maxlength="300" class="width-100"></textarea>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%"></td>
										<td class="td-right">
											<span style="color:red">（协议模板结算费率不用设置，后台自动根据产品添加。）</span>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editProtocolTemplateBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div>
			<!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div>
		<!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div>
	<!-- /.main-container -->
</body>
<script type="text/javascript">
function loadProtocolTemplate(){
	$(".search-table #protocolType").val($(".search-table #protocolTypeTemp").val());
}

jQuery(function($){
	// 缓存数据
	var protocolTemplateJson= ${protocolTemplates};
	var protocolTemplateList=$("tr.protocolTemplate");
	
	$.each(protocolTemplateJson,function(i,value){
		$.data(protocolTemplateList[i],"protocolTemplate",value);
	});
	
	$(".clearProtocolTemplate").click(function(){
		$(".search-table #protocolType").val('');
		$(".search-table #protocolTemplateName").val('');
	})
	
	$(".addProtocolTemplate").click(function(){
		/* $("#addProtocolTemplateModal #templateMustContent").val(); */
		$("#addProtocolTemplateModal .contentSpan").text($("#fixContent").val());
	});
	// 新增 
	$(".addProtocolTemplateBtn").click(function(){
		
		var protocolTemplateName = $('#addProtocolTemplateModal #protocolTemplateName').val();
		if(kong.test(protocolTemplateName)) {
			$.gyzbadmin.alertFailure('协议模板名称不可为空');
			return;
		}
		
		var protocolType = $('#addProtocolTemplateModal #protocolType').val();
		if(kong.test(protocolType)) {
			$.gyzbadmin.alertFailure('协议模板类型不可为空');
			return;
		}
		
	    var merchantType = $('#addProtocolTemplateModal #merchantType').val();
		if(kong.test(merchantType)) {
			$.gyzbadmin.alertFailure('商户类型不可为空');
			return;
		}
		
		var protocolVersion = $('#addProtocolTemplateModal #protocolVersion').val();
		if(kong.test(protocolVersion)) {
			$.gyzbadmin.alertFailure('版本号不可为空');
			return;
		}
		
		var protocolTemplate =$('#addProtocolTemplateModal #protocolTemplate').val();
		/* if(kong.test(protocolTemplate)){
			protocolTemplate = $("#templateMustContent").val();
		}else{
			protocolTemplate = $("#templateMustContent").val()+','+protocolTemplate;
		} */
		
		var validDate = $('#addProtocolTemplateModal #validDate').val();
		if(kong.test(validDate)) {
			$.gyzbadmin.alertFailure('生效日期不可为空');
			return;
		}
		
		validDate=validDate.replace(/-/g, "/");
	     var dateA=new Date(validDate);
	     var dateNow=new Date();
	     if(Date.parse(dateNow)-Date.parse(dateA)>0){
	    	 $.gyzbadmin.alertFailure("生效日期必须大于今天");
	         return false;
	     }
		//描述
		var memo=$("#addProtocolTemplateModal #memo").val();
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.ADD %>', 
		{
			 'protocolTemplateName'		:protocolTemplateName,
			 'protocolType'		:protocolType,
			 'protocolVersion' 	:protocolVersion,
			 'protocolTemplate' :protocolTemplate,
			 'merchantType'		:merchantType,
			 'validDate'		:validDate,
			 'memo'				:memo
		
		}, function(data) {
				$.unblockUI();   
				if(data.result == 'success'){
					$('#addProtocolTemplateModal').modal('hide');
					$.gyzbadmin.alertSuccess('新增成功', null, function(){
						window.location.reload();
					});
				}else {
					$.gyzbadmin.alertFailure('新增失败:' + data.message);
				}
			}, 'json'
		);
	});
	
	//弹出修改框
    $('.editProtocolTemplate').click(function(){
    	
		var protocolTemplate = $.data($(this).parent().parent()[0], 'protocolTemplate');
       $('#editProtocolTemplateModal').on('show.bs.modal', function () {
    	   
    	    $('#editProtocolTemplateModal #id').val(protocolTemplate.id);
			$('#editProtocolTemplateModal #protocolType').val(protocolTemplate.protocolType);
			$('#editProtocolTemplateModal #protocolTemplateName').val(protocolTemplate.protocolTemplateName);
			$('#editProtocolTemplateModal #protocolVersion').val(protocolTemplate.protocolVersion);
			$('#editProtocolTemplateModal #protocolTemplate').val(protocolTemplate.choiceContext);
			$('#editProtocolTemplateModal #validDate').val(protocolTemplate.validDate);
			$('#editProtocolTemplateModal #disableDate').val(protocolTemplate.disableDate);
			$('#editProtocolTemplateModal #status').val(protocolTemplate.status);
			$('#editProtocolTemplateModal #memo').val(protocolTemplate.memo);
			$("#editProtocolTemplateModal .contentSpan").text($("#fixContent").val());
			$('#editProtocolTemplateModal #merchantType').val(protocolTemplate.merchantType);
			
		});
       $('#editRuleModal').on('hide.bs.modal', function () {
    	   
    	    $('#editProtocolTemplateModal #id').val('');
			$('#editProtocolTemplateModal #protocolType').val('');
			$('#editProtocolTemplateModal #protocolTemplateName').val('');
			$('#editProtocolTemplateModal #protocolVersion').val('');
			$('#editProtocolTemplateModal #protocolTemplate').val('');
			$('#editProtocolTemplateModal #validDate').val('');
			$('#editProtocolTemplateModal #disableDate').val('');
			$('#editProtocolTemplateModal #status').val('');
			$('#editProtocolTemplateModal #memo').val('');
		});
	}); 
	// 修改
	$('.editProtocolTemplateBtn').click(function(){
		
		var id = $('#editProtocolTemplateModal #id').val();
		if(kong.test(id)) {
			$.gyzbadmin.alertFailure('协议模板编号不可为空');
			return;
		}
		var protocolType = $('#editProtocolTemplateModal #protocolType').val();
		if(kong.test(protocolType)) {
			$.gyzbadmin.alertFailure('协议模板类型不可为空');
			return;
		}
		var protocolTemplateName = $('#editProtocolTemplateModal #protocolTemplateName').val();
		if(kong.test(protocolTemplateName)) {
			$.gyzbadmin.alertFailure('协议模板名称不可为空');
			return;
		}
		var merchantType = $('#editProtocolTemplateModal #merchantType').val();
		if(kong.test(merchantType)) {
			$.gyzbadmin.alertFailure('商户类型不可为空');
			return;
		}
		var protocolVersion = $('#editProtocolTemplateModal #protocolVersion').val();
		if(kong.test(protocolVersion)) {
			$.gyzbadmin.alertFailure('版本号不可为空');
			return;
		}
		var protocolTemplate = $('#editProtocolTemplateModal #protocolTemplate').val();
		var status = $('#editProtocolTemplateModal #status').val();
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure('状态不可为空');
			return;
		}
		var disableDate = $('#editProtocolTemplateModal #disableDate').val();
		if(!kong.test(disableDate)) {
			disableDate=disableDate.replace(/-/g, "/");
		     var dateA=new Date(disableDate);
		     var dateNow=new Date();
		     if(Date.parse(dateNow)-Date.parse(dateA)>0){
		    	 $.gyzbadmin.alertFailure("失效日期必须大于今天");
		         return false;
		     }
		}
		
		//描述
		var memo=$("#editProtocolTemplateModal #memo").val();

		// 保存修改
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=ProtocolTemplatePath.BASE + ProtocolTemplatePath.EDIT %>', {
			 'id'				:id,
			 'protocolType'		:protocolType,
			 'protocolVersion' 	:protocolVersion,
			 'protocolTemplate' :protocolTemplate,
			 'disableDate'		:disableDate,
			 'status'			:status,
			 'merchantType' 	:merchantType,
			 'memo'				:memo
			}, function(data) {
				$.unblockUI();
				if(data.result == 'success'){
					$('#editProtocolTemplateModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
				}
			}, 'json'
		);
	});
});	
</script>
</html>