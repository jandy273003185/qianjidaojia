<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.qifenqian.bms.basemanager.protocolcontent.ProtocolContentPath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<html>
<head>
<meta charset="utf-8" />
<title>代理商协议管理</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
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
<body onload="loadProtocol()">
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
								action='<c:url value="<%=ProtocolContentPath.BASE + ProtocolContentPath.AGENTLIST%>" />'
								method="post" id="queryForm">
								<table class="search-table">
								<input type="hidden" id="merchantCodeHd" value="${queryBean.merchantCode }" />
									<tr>
										<td class="td-left">协议名称</td>
										<td class="td-right">
											<span class="input-icon"> 
												<input type="text" name="protocolName" id="protocolName" value="${queryBean.protocolName }" >
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										<td class="td-left">代理商</td>
										<td class="td-right">
											<select id="merchantCode" name="merchantCode">
											<option value="">输入代理商名称或代理商编号查询</option>
											<c:forEach items="${merchantList }" var="bean">
												<option value="${bean.merchantCode }">${bean.custName }</option>
											</c:forEach>
										</select>
										</td>
										<td class="td-left">协议模板</td>
										<td class="td-right">
											<input type="hidden" name="templateIdTemp" id="templateIdTemp" value="${queryBean.templateId }">
											<sevenpay:selectProtocolTemplateTag id="templateId" name ="templateId"  clasS="width-60"/>
										</td>
										<td class="td-left">状态</td>
										<td class="td-right">
											<input type="hidden" name="statusTemp" id="statusTemp" value="${queryBean.status }">
											<select id="status" name="status">
												<option value="">请选择</option>
												<option value="VALID">有效</option>
												<option value="DISABLE">失效</option>
												<option value="AUDIT">待审核</option>
												<option value="AUDIT_NO">审核不通过</option>
												<option value="STOP">终止</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="8" align="center">
											<span class="input-group-btn"> 
												<gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.AGENTLIST%>">
													<button type="submit" class="btn btn-purple btn-sm">
														查询 
														<i class="icon-search icon-on-right bigger-110"></i>
													</button>
												</gyzbadmin:function>
													<button  class="btn btn-purple btn-sm btn-margin clearProtocol">
														清空 
														<i class=" icon-move icon-on-right bigger-110"></i>
													</button>
												<gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.ADD%>">
													<button class="btn btn-purple btn-sm btn-margin addProtocolContent" data-toggle='modal' data-target="#addProtocolContentModal">
														新增
														<i class="icon-plus-sign icon-on-right bigger-110"></i>
													</button>
											    </gyzbadmin:function>
											    <gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.PROTOCOLCONTENTEXPORT%>">
														<a class="btn btn-purple btn-sm exportBut">
															导出报表
														</a> 
												</gyzbadmin:function> 
											</span>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">协议列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="8%">协议编号</th>
											<th width="8%">协议名称</th>
											<th width="8%">代理商编号</th>
											<th width="5%">协议模板</th>
											<th width="35%">协议内容</th>
											<th width="7%">失效日期</th>
											<th width="7%">创建人</th>
											<th width="5%">状态</th>
											<th whdth="4%">备注</th>
											<th width="6%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${protocolContents}" var="protocolContent">
											<tr class="protocolContent">
												<td>${protocolContent.id}</td>
												<td>${protocolContent.protocolName}</td>
												<td>${protocolContent.merchantCode}</td>
												<td>${protocolContent.protocolTemplateName}</td>
												<td>${protocolContent.protocolContent}</td>
												<td>${protocolContent.disableDate}</td>
												<td>${protocolContent.instUserName}</td>
												<td>
													<c:if test="${protocolContent.status == 'VALID'}">
														有效
													</c:if>
													<c:if test="${protocolContent.status == 'DISABLE'}">
														失效
													</c:if>
													<c:if test="${protocolContent.status == 'AUDIT'}">
														待审核
													</c:if>
													<c:if test="${protocolContent.status == 'AUDIT_NO'}">
														审核不通过
													</c:if>
													<c:if test="${protocolContent.status == 'STOP'}">
														终止
													</c:if>
												</td>
												<td>${protocolContent.memo}</td>
												<td>
													<input type="hidden" name="id" value="${protocolContent.id}">
													<a href="#"  data-toggle='modal' class="tooltip-error " onclick="operateProtocol(this,'preview');" data-rel="tooltip" title="预览" data-target="#previewProtocolContentModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
													<c:if test="${protocolContent.status == 'VALID'}">
														<gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.DELETE%>">
															<a href="#"  data-toggle='modal' class="tooltip-error deleteProtocolContent" data-rel="tooltip" title="终止该协议" data-target="#deleteProtocolContentModal">
																<span class="red"><i class="icon-trash bigger-120"></i></span>
															</a>
														</gyzbadmin:function>
													</c:if>
													<c:if test="${protocolContent.status == 'VALID' && protocolContent.isUpdate == 'NO'}">
														<gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.EDIT%>">
															<a href="#"  data-toggle='modal' class="tooltip-error " onclick="operateProtocol(this,'edit');" data-rel="tooltip" title="修改协议" data-target="#previewProtocolContentModal">
																<span class="green"><i class="icon-edit bigger-120"></i></span>
															</a>
															</gyzbadmin:function>
													</c:if>
													<c:if test="${protocolContent.status == 'AUDIT'}">
														<gyzbadmin:function url="<%=ProtocolContentPath.BASE + ProtocolContentPath.AUDIT%>">
															<a href="#"  data-toggle='modal' class="tooltip-error " onclick="operateProtocol(this,'audit');" data-rel="tooltip" title="审核协议" data-target="#previewProtocolContentModal">
																<span class="green"><i class="icon-ok-sign bigger-120"></i></span>
															</a>
															</gyzbadmin:function>
													</c:if>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty protocolContents}">
											<tr>
												<td colspan="8" align="center"><font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty protocolContents}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				
				 <div class="modal fade" id="addProtocolContentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">新增协议</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table" id="tbProtocolContent">
									<tr>
										<td class="td-left" width="30%">代理商</td>
										<td class="td-right" colspan="3">
											<select id="merchantCodeAdd" name="merchantCode" >
											<option value="">输入代理商名或代理商编号查询</option>
											<c:forEach items="${merchantList }" var="bean">
												<option value="${bean.merchantCode }">${bean.custName }</option>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">协议名称</td>
										<td class="td-right" colspan="3">
											<input type="text" id="protocolName" name="protocolName" class="width-100"/>
											<input type="hidden" id="protocolContentTemp" name="protocolContentTemp" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">协议模板</td>
										<td class="td-right" colspan="3">
										<sevenpay:selectProtocolTemplateTag id="templateId" name ="templateId"  clasS="width-60"/>
											<span class="input-icon">
												<label class="label-tips" id="templateLab"></label>
											</span>
										</td>
									</tr>
									<tr id="memoTemp">
										<td class="td-left" width="30%">备注</td>
										<td class="td-right" colspan="3">
											<textarea rows="2" cols="" name ="memo" id ="memo"  maxlength="300" class="width-100"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addProtocolContentBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
					
					
					<div class="modal fade" id="previewProtocolContentModal" style="z-index:1040;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog" style="z-index:90;">
					      <div class="modal-content" style="width:700px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title title" id="myModalLabel">预览协议</h4>
					         </div>
					         <input type="hidden" id="protocolContentTemp" name="protocolContentTemp"/>
					         <input type="hidden" id="protocolContentId" name="protocolContentId"/>
					         <div class="modal-body">
					            <table class="modal-input-table" id="tbPreviewProtocolContent">
									 <tr>
										<td class="td-left" width="30%">协议名称</td>
										<td class="td-right" colspan="3">
											<input type="text" id="protocolName" name="protocolName" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">代理商</td>
										<td class="td-right" colspan="3">
											<select id="merchantCode" name="merchantCode" >
											<option value="">输入代理商名或代理商编号查询</option>
											<c:forEach items="${merchantList }" var="bean">
												<option value="${bean.merchantCode }">${bean.custName }</option>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">协议模板</td>
										<td class="td-right" colspan="3">
											<sevenpay:selectProtocolTemplateTag id="templateId" name ="templateId"  clasS="width-60"/>
											<span class="input-icon">
												<label class="label-tips" id="templateLab"></label>
											</span>
										</td>
											
										</td>
									</tr>
									<tr>
										<td class="td-left" width="30%">备注</td>
										<td class="td-right" colspan="3">
											<textarea rows="2" cols="" name ="memo" id ="memo"  maxlength="300" class="width-100"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary edit"  >提交</button>
					            <button type="button" class="btn btn-primary auditPassBtn" onclick="firstPass()">审核通过</button>
					            <button type="button" class="btn btn-primary auditNoPassBtn" data-toggle='modal'  data-target="#AuditMessageModel">审核不通过</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
					
					 <div class="modal fade" id="deleteProtocolContentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">协议删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要终止该协议[<span class="id"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" class="id" value=""/>
					            <input type="hidden" name="merchantCode" id="merchantCode" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteProtocolContentBtn">确定</button>
					         </div>
				      </div><!-- /.modal-content -->
				    </div>
				</div><!-- /.modal -->
			<div class="modal fade" style="z-index:1041;" id="AuditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog" style="width:30%;z-index:1042;">
			      <div class="modal-content" >
			         <div class="modal-header" style="background-color:0099CC">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			            <h4 class="modal-title" id="myModalLabel">审核不通过</h4>
			         </div>
			         <div class="modal-body">
			            <table 	 >
							<tr>	
								<td >请输入审核不通过原因：</td>
							</tr>
							<tr>	
								<td>
									<textarea rows="5" cols="40" id="auditMessage" ></textarea>
								</td>
							</tr>
			            </table>
			         </div>
			         <div class="modal-footer">
			            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
			            <button type="button" class="btn btn-primary " onclick="auditNotPass()">确定</button>
			         </div>
			      </div><!-- /.modal-content -->
			     </div>
			</div><!-- /.modal -->
				<!-- /.page-content -->
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
function isPercent(c)
{
    var r= /^[1-9]?[0-9]*\.[0-9]+[%]$/;
    return r.test(c);
}
function isNumber(c){
	var r= /^[0-9]*$/;
    return r.test(c);
}
function isChar(c){
	var r= /^[a-zA-Z0-9\u4e00-\u9fa5]*$/;
    return r.test(c);
}
function isPeriod(c){
	var r= /^[T][+][0-9]*$/;
    return r.test(c);
}
function isSkipHoliday(c){
	var r= /^[Y|N]$/;
    return r.test(c);
}
function loadProtocol(){
	$(".search-table #templateId").val($(".search-table #templateIdTemp").val());
	$(".search-table #status").val($(".search-table #statusTemp").val());
	$("#merchantCode").val($("#merchantCodeHd").val());
	$("#queryForm").find(".combo-input").val($("#merchantCode option:selected").text());
}
function operateProtocol(v,type){
	
	$("#previewProtocolContentModal .edit").show();
	$("#previewProtocolContentModal .auditPassBtn").show();
	$("#previewProtocolContentModal .auditNoPassBtn").show();
	var protocol = $.data($(v).parent().parent()[0],"protocolContent");
	
	
	var protocolContentId = $(v).parent().find('input[name="id"]').val();
	$("#previewProtocolContentModal #protocolContentId").val(protocolContentId);
	var contents=protocol.protocolContent;
	
	var contentCount = contents.split("@_@");
	var protoclTemp = '';
	$("#tbPreviewProtocolContent tr:gt(3)").remove();
	for(var i=0; i<contentCount.length-1; i++){
		var strName =contentCount[i].split(":")[1];
		if(strName == 'custManager'){
			 $('#tbPreviewProtocolContent').append(
			 	"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[0]+"</td><td class='td-right' colspan='3'>"+
				"<sevenpay:selectSysUserTag id='content"+i+"' name ='"+strName+"'  clasS='width-60'/>"+
				"</td></tr>"
					 );
			 $("#previewProtocolContentModal #content"+i).val(contentCount[i].split(":")[2]);
		}else if(strName == 'serviceProvider'){
			$('#tbPreviewProtocolContent').append(
				 	"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[0]+"</td><td class='td-right' colspan='3'>"+
					"<sevenpay:selectAgentMerchantTag id='content"+i+"' name ='"+strName+"'  clasS='width-60'/>"+
					"</td></tr>"
						 );
				 $("#previewProtocolContentModal #content"+i).val(contentCount[i].split(":")[2]);
		}else if(strName.indexOf('rate')>=0){
			$('#tbPreviewProtocolContent').append("<tr><td class='td-left'><label for='rate_"+i+"'><input id='rate_"+i+"' type='checkbox' name='rateflag' value='"+strName+"'/>"+contentCount[i].split(":")[0]+"</lablel></td><td class='td-right' colspan='3'><input type='text' class='width-100' id='content"+i+"' name='"+strName+"' value='"+contentCount[i].split(":")[2]+"'/></td></tr>");
			if(contentCount[i].split(":")[2]!==''){
				$('#tbPreviewProtocolContent #rate_'+i).attr('checked','true');
			}
		}
		
		else{
			 $('#tbPreviewProtocolContent').append("<tr><td class='td-left'>"+contentCount[i].split(":")[0]+"</td><td class='td-right' colspan='3'><input type='text' class='width-100' id='content"+i+"' name='"+strName+"' value='"+contentCount[i].split(":")[2]+"'/></td></tr>");
		}
		 	
	 	 protoclTemp = protoclTemp + contentCount[i].split(":")[1]+':'+ contentCount[i].split(":")[0]+',';
		
	 
	}
	
	$("#tbPreviewProtocolContent").find('select[name="custManager"]').comboSelect();
	$("#tbPreviewProtocolContent").find('select[name="serviceProvider"]').comboSelect();
	
	protoclTemp = protoclTemp.substring(0,protoclTemp.length-1);
	$("#previewProtocolContentModal #protocolContentTemp").val(protoclTemp);
	$("#previewProtocolContentModal #protocolName").val(protocol.protocolName);
	$("#previewProtocolContentModal #templateId").val(protocol.templateId);
	$("#previewProtocolContentModal #memo").val(protocol.memo);
	$("#previewProtocolContentModal #merchantCode").val(protocol.merchantCode);
	if(type == 'audit'){
		$("#previewProtocolContentModal .title").text("协议审核");
		$("#previewProtocolContentModal .edit").hide();
		$("#previewProtocolContentModal").find("input").attr("readonly",true);
		$("#previewProtocolContentModal").find("select").attr("disabled",true);
		$("#previewProtocolContentModal").find("input[type='checkbox']").attr("disabled",true);
	}
	
	if(type == 'preview'){
		$("#previewProtocolContentModal .title").text("协议预览");
		$("#previewProtocolContentModal .edit").hide();
		$("#previewProtocolContentModal .auditPassBtn").hide();
		$("#previewProtocolContentModal .auditNoPassBtn").hide();
		$("#previewProtocolContentModal").find("input").attr("readonly",true);
		$("#previewProtocolContentModal").find("select").attr("disabled",true);
		$("#previewProtocolContentModal").find("input[type='checkbox']").attr("disabled",true);
	}
	if(type == 'edit'){
		$("#previewProtocolContentModal").find("select").attr("disabled",false);
		$("#previewProtocolContentModal .title").text("协议修改");
		$("#previewProtocolContentModal .auditPassBtn").hide();
		$("#previewProtocolContentModal .auditNoPassBtn").hide();
	}
}

function firstPass(){
	$.blockUI();
	$.post(window.Constants.ContextPath + '<%=ProtocolContentPath.BASE + ProtocolContentPath.AUDIT %>', 
	{
		'id':$("#previewProtocolContentModal #protocolContentId").val(),
		'status':'pass',
		'merchantCode' : $("#previewProtocolContentModal #merchantCode").val()
	}, function(data) {
			$.unblockUI();   
			if(data.result == 'success'){
				$('#previewProtocolContentModal').modal('hide');
				$.gyzbadmin.alertSuccess('审核完成', null, function(){
					window.location.reload();
				});
			}else {
				$.gyzbadmin.alertFailure('审核异常:' + data.message);
			}
		}, 'json'
	);
}

function auditNotPass(){
	var message = $("#AuditMessageModel #auditMessage").val();
	if(kong.test(message)) {
		$.gyzbadmin.alertFailure('请填写审核信息');
		return;
	}
	$.blockUI();
	$.post(window.Constants.ContextPath + '<%=ProtocolContentPath.BASE + ProtocolContentPath.AUDIT %>', 
	{
		'id':$("#previewProtocolContentModal #protocolContentId").val(),
		'status':'noPass',
		'memo':trim($("#AuditMessageModel #auditMessage").val())
	}, function(data) {
			$.unblockUI();   
			if(data.result == 'success'){
				$('#previewProtocolContentModal').modal('hide');
				$.gyzbadmin.alertSuccess('审核完成', null, function(){
					window.location.reload();
				});
			}else {
				$.gyzbadmin.alertFailure('审核异常:' + data.message);
			}
		}, 'json'
	);
}

jQuery(function($){
	//商户下拉查询
	$("#merchantCode").comboSelect();
	
	$("#merchantCodeAdd").comboSelect();
	$("#merchantCodeAdd").change(function(){
		$("#addProtocolContentModal #protocolName").val($("#merchantCodeAdd option:selected").text());
	});
	// 缓存数据
	var protocolContents= '<c:out value="${gyzb:toJSONString(protocolContents)}" escapeXml="false"/>';
	
	var protocolContentList=$("tr.protocolContent");
	
	$.each($.parseJSON(protocolContents),function(i,value){
		$.data(protocolContentList[i],"protocolContent",value);
	});
	
	$(".clearProtocol").click(function(){
		$(".search-table #protocolName").val('');
		$(".search-table #templateId").val('');
		$(".search-table #merchantCode").val('');
		$(".search-table #status").val('');
	})
	
	$("#addProtocolContentModal #templateId").change(function(){
		
		var templateId = $('#addProtocolContentModal #templateId').val();
		if(kong.test(templateId)) {
			$.gyzbadmin.alertFailure('协议模板类型不可为空');
			return;
		}
		$("#tbProtocolContent tr:gt(3)").remove();
	var validate =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=ProtocolContentPath.BASE+ProtocolContentPath.QUERYTEMPLATE%>',
        data:{
            'templateId':templateId,
            'number':Math.random()
        },
        success:function(data){
        	if(data.result=="fail"){
				$("#addProtocolContentModal #templateLab").text("协议模板不存在");
				validate = false;
			}else{
				$("#addProtocolContentModal #templateLab").text("");
				$('#addProtocolContentModal #protocolContentTemp').val(data.message);
				var contentCount= data.message.split(",");
				for(var i=0; i<contentCount.length;i++){
					if(contentCount[i].split(":").length>2){
						 $('#tbProtocolContent').append("<tr><td class='td-left'><label for='rate_"+i+"'><input id='rate_"+i+"' type='checkbox' name='rateflag' value='"+contentCount[i].split(":")[0]+"'/>"+contentCount[i].split(":")[1]+"</lablel></td><td class='td-right' colspan='3'><input type='text' value='"+contentCount[i].split(":")[2]+"' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td></tr>");  	
						 if(contentCount[i].split(":")[0].indexOf('onecode.coll.pay_rate')>=0){
							 $("#addProtocolContentModal #rate_"+i).attr('checked','true');
						 }
					}else{
						 var local = window.Constants.ContextPath+'/basemanager/branchbank/list';
						if(contentCount[i].split(":")[0]=='CNAPS'){
							$('#tbProtocolContent').append("<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='2'><input type='text' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td><td><a href='"+local+"' target='_blank'>查找</a></td></tr>");
						}else if(contentCount[i].split(":")[0]=='period'){
							$('#tbProtocolContent').append(
							"<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
							"<select   id='content"+i+"' name='"+contentCount[i].split(":")[0]+"'>"+
									"<option value=''>--请选择--</option>"+
									"<option value='T+1' selected ='selected'>T+1</option>"+
									"<option value='T+0'>T+0</option>"+
									"<option value='T+30'>T+30</option>"+
									"<option value='T+2'>T+2</option>"+
							"</select></td></tr>"
							);
						}else if(contentCount[i].split(":")[0]=='isSkipHoliday'){
							$('#tbProtocolContent').append(
							"<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
							"<select   id='content"+i+"' name='"+contentCount[i].split(":")[0]+"'>"+
									"<option value=''>--请选择--</option>"+
									"<option value='Y' selected ='selected'>是</option>"+
									"<option value='N'>不</option>"+
							"</select></td></tr>"
							);
						}else if(contentCount[i].split(":")[0]=='custManager'){
							$('#tbProtocolContent').append(
								"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
								"<sevenpay:selectSysUserTag id='content"+i+"' name ='"+contentCount[i].split(":")[0]+"'  clasS='width-60'/>"+
								"</td></tr>"
							);
						
						}else if(contentCount[i].split(":")[0]=='serviceProvider'){
							$('#tbProtocolContent').append(
									"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
									"<sevenpay:selectAgentMerchantTag id='content"+i+"' name ='"+contentCount[i].split(":")[0]+"'  clasS='width-60'/>"+
									"</td></tr>"
								);
						}else{
							$('#tbProtocolContent').append("<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'><input type='text' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td></tr>");  	
						}
						$("#tbProtocolContent").find('select[name="custManager"]').comboSelect();
						$("#tbProtocolContent").find('select[name="serviceProvider"]').comboSelect();
						
					}
				 }
				validate = true;
			}
        }
       });
	
		if(!validate){
			return false;
		}
	});
	
	// 新增 
	$(".addProtocolContentBtn").click(function(){
			var protocolName = trim($('#addProtocolContentModal #protocolName').val());
			
			if(kong.test(protocolName)) {
				$.gyzbadmin.alertFailure('协议名称不可为空');
				return;
			}
			var templateId = $('#addProtocolContentModal #templateId').val();
			if(kong.test(templateId)) {
				$.gyzbadmin.alertFailure('协议编号不可为空');
				return;
			}
			var merchantCode = trim($('#addProtocolContentModal #merchantCodeAdd').val());
			if(kong.test(merchantCode)) {
				$.gyzbadmin.alertFailure('代理商编号不可为空');
				return;
			}
			
			var protocolContentTemp = $('#addProtocolContentModal #protocolContentTemp').val();
			
			var contentTempCount= protocolContentTemp.split(",");
			var protocolContent = "";
			for(var i=0; i<contentTempCount.length;i++){
				var	contents = trim($('#addProtocolContentModal #content'+i).val());
				 var arrRate = new Array('h5.gateway.pay_rate','mobile.plugin.pay_rate','h5_t.gateway.pay_rate','onecode.coll.pay_rate','pc.gateway.pay_rate','agent_merchant_rate');
				 var name = $('#addProtocolContentModal #content'+i).attr("name");
				if(contentTempCount[i].split(":")[0]=="custManager" || contentTempCount[i].split(":")[0]=="serviceProvider"){
					
				}else{
					if($.inArray(name,arrRate)==-1){
						if(kong.test(contents)){
							 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'不可为空');
							return; 
						 }
					}
					
				}
				 if('period'==name){
					 	if(!isPeriod(contents)){
					 		$.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'请填正确的周期(如T+1)');
							return;
						}
					 }
				 if('isSkipHoliday'==name){
				 	if(!isSkipHoliday(contents)){
				 		$.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'是否跳过节假日格式不正确');
						return;
					}
				 }
				 
				 if($.inArray(name,arrRate)!=-1){
					 if($("#addProtocolContentModal #rate_"+i).is(':checked')){
						 if(!isPercent(contents)){
							 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'请填正确的百分数(如0.6%)');
							 return;
						 }
					 }
				 }
				 //结算卡号、银行卡号、联行号 只能为数字
				 var arr = new Array('bankCardNo','bankCardNo','CNAPS');
				 if($.inArray(name,arr)!=-1){
					 if(!isNumber(contents)){
						 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'只能为数字');
						 return;
					 }
				 }
				 //结算户名、结算卡开户行及支行信息，开户行地址，账户名称，开户银行
				 var arr = new Array('bankCardName','bankName','openAddressRec');
				 if($.inArray(name,arr)!=-1){
					 if(!isChar(contents)){
						 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'只能为汉字、字母、数字组合');
						 return;
					 }
				 }
				 if($.inArray(name,arrRate)!=-1){
					 if($("#addProtocolContentModal #rate_"+i).is(':checked')){
						 protocolContent = protocolContent.concat(contentTempCount[i].split(":")[1]+':'+contentTempCount[i].split(":")[0]+':'+contents+'@_@');
					 }else{
						 protocolContent = protocolContent.concat(contentTempCount[i].split(":")[1]+':'+contentTempCount[i].split(":")[0]+':'+'@_@');
					 }
				 }else{
					 protocolContent = protocolContent.concat(contentTempCount[i].split(":")[1]+':'+contentTempCount[i].split(":")[0]+':'+contents+'@_@');
				 }
				
			 }
			
			var memo = $('#addProtocolContentModal #memo').val();
		// 保存
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=ProtocolContentPath.BASE + ProtocolContentPath.ADD %>', 
		{
			 'protocolName'		:protocolName,
			 'protocolContent'	:protocolContent,
			 'templateId'		:templateId,
			 'memo'				:memo,
			 'merchantCode'		:merchantCode
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
	
	$(".exportBut").click(function(){
		var protocolName = $("#protocolName").val();
		var merchantCode = $("#merchantCode").val();
		var templateId = $("#templateId").val();
		var status = $("#status").val();
		var src ="<%= request.getContextPath()+ ProtocolContentPath.BASE + ProtocolContentPath.PROTOCOLCONTENTEXPORT%>?protocolName="+
		protocolName+"&merchantCode="+merchantCode+"&templateId="+templateId+"&status="+status;
		$(".exportBut").attr("href",src);
	});

	$("#AuditMessageModel .messageDefault").click(function(){
		$(".modal-backdrop").css("z-index","1030");
	});
	
	$("#previewProtocolContentModal .auditNoPassBtn").click(function(){
		$(".modal-backdrop").css("z-index","1040");
	});
	
	// 弹出删除层准备工作
	$('.deleteProtocolContent').click(function(){
		var id = $(this).parent().find('input[name="id"]').val();
		$('#deleteProtocolContentModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteProtocolContentModal span.id').html(id);
			$('#deleteProtocolContentModal input.id').val(id);
		});
		$('#deleteProtocolContentModal').on('hide.bs.modal', function () {
			$('#deleteProtocolContentModal span.id').html('');
			$('#deleteProtocolContentModal input.id').val('');
		});
	});
	
	// 删除
	$('.deleteProtocolContentBtn').click(function(){
		var id = $('#deleteProtocolContentModal input.id').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=ProtocolContentPath.BASE + ProtocolContentPath.DELETE %>', {
				'id' 	: id
			}, function(data) {
				$.unblockUI();
				if(data.result == 'success'){
					$('#deleteProtocolContentModal').modal('hide');
					$.gyzbadmin.alertSuccess('删除成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('删除失败:' + data.message);
				}
			}, 'json'
		);
	});
	
$("#previewProtocolContentModal #templateId").change(function(){
		
		var templateId = $('#previewProtocolContentModal #templateId').val();
		if(kong.test(templateId)) {
			$.gyzbadmin.alertFailure('协议模板类型不可为空');
			return;
		}
		
	var validate =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=ProtocolContentPath.BASE+ProtocolContentPath.QUERYTEMPLATE%>',
        data:{
            'templateId':templateId,
            'number':Math.random()
        },
        success:function(data){
        	if(data.result=="fail"){
				$("#previewProtocolContentModal #templateLab").text("协议模板不存在");
				validate = false;
			}else{
				$("#tbPreviewProtocolContent tr:gt(3)").remove();
				$("#previewProtocolContentModal #templateLab").text("");
				$('#previewProtocolContentModal #protocolContentTemp').val(data.message);
				var contentCount= data.message.split(",");
				for(var i=0; i<contentCount.length;i++){
					if(contentCount[i].split(":").length>2){
						 $('#tbPreviewProtocolContent').append("<tr><td class='td-left'><label for='rate_"+i+"'><input id='rate_"+i+"' type='checkbox' name='rateflag' value='"+contentCount[i].split(":")[0]+"'/>"+contentCount[i].split(":")[1]+"</lablel></td><td class='td-right' colspan='3'><input type='text' value='"+contentCount[i].split(":")[2]+"' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td></tr>");  	
						 
						 if(contentCount[i].split(":")[0].indexOf('onecode.coll.pay_rate')>=0){
							 $("#previewProtocolContentModal #rate_"+i).attr('checked','true');
						 }
					}else{
						 var local = window.Constants.ContextPath+'/basemanager/branchbank/list';
						if(contentCount[i].split(":")[0]=='CNAPS'){
							$('#tbPreviewProtocolContent').append("<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='2'><input type='text' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td><td><a href='"+local+"' target='_blank'>查找</a></td></tr>");
						}else if(contentCount[i].split(":")[0]=='period'){
							$('#tbPreviewProtocolContent').append(
									"<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
									"<select   id='content"+i+"' name='"+contentCount[i].split(":")[0]+"'>"+
											"<option value=''>--请选择--</option>"+
											"<option value='T+1' selected ='selected'>T+1</option>"+
											"<option value='T+0'>T+0</option>"+
											"<option value='T+30'>T+30</option>"+
											"<option value='T+2'>T+2</option>"+
									"</select></td></tr>"
									);
						}else if(contentCount[i].split(":")[0]=='isSkipHoliday'){
							$('#tbPreviewProtocolContent').append(
							"<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
							"<select   id='content"+i+"' name='"+contentCount[i].split(":")[0]+"'>"+
									"<option value=''>--请选择--</option>"+
									"<option value='Y' selected ='selected'>是</option>"+
									"<option value='N'>不</option>"+
							"</select></td></tr>"
							);
						}else if(contentCount[i].split(":")[0]=='custManager'){
							$('#tbPreviewProtocolContent').append(
								"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
								"<sevenpay:selectSysUserTag id='content"+i+"' name ='"+contentCount[i].split(":")[0]+"'  clasS='width-60'/>"+
								"</td></tr>"
							);
						
						}else if(contentCount[i].split(":")[0]=='serviceProvider'){
							$('#tbPreviewProtocolContent').append(
									"<tr><td class='td-left' width='30%'>"	+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'>"+
									"<sevenpay:selectAgentMerchantTag id='content"+i+"' name ='"+contentCount[i].split(":")[0]+"'  clasS='width-60'/>"+
									"</td></tr>"
								);
						}
						
						else{
							$('#tbPreviewProtocolContent').append("<tr><td class='td-left'>"+contentCount[i].split(":")[1]+"</td><td class='td-right' colspan='3'><input type='text' id='content"+i+"' class='width-100' placeholder='"+contentCount[i].split(":")[1]+"'  name='"+contentCount[i].split(":")[0]+"'></td></tr>");  	
						}
						
						$("#tbPreviewProtocolContent").find('select[name="custManager"]').comboSelect();
						$("#tbPreviewProtocolContent").find('select[name="serviceProvider"]').comboSelect();
					}
				 }
				validate = true;
			}
        }
       });
	
		if(!validate){
			return false;
		}
	});
	$("#previewProtocolContentModal .edit").click(function(){
		var protocolName = trim($('#previewProtocolContentModal #protocolName').val());
		
		if(kong.test(protocolName)) {
			$.gyzbadmin.alertFailure('协议名称不可为空');
			return;
		}
		var templateId = $('#previewProtocolContentModal #templateId').val();
		if(kong.test(templateId)) {
			$.gyzbadmin.alertFailure('协议模板不可为空');
			return;
		}
		var merchantCode = trim($('#previewProtocolContentModal #merchantCode').val());
		if(kong.test(merchantCode)) {
			$.gyzbadmin.alertFailure('代理商编号不可为空');
			return;
		}
		
		var protocolContentTemp = $('#previewProtocolContentModal #protocolContentTemp').val();
		
		var contentTempCount= protocolContentTemp.split(",");
		var protocolContent = "";
		for(var i=0; i<contentTempCount.length;i++){
			var	contents = $("#previewProtocolContentModal #content"+i).val();
			var arrRate = new Array('h5.gateway.pay_rate','mobile.plugin.pay_rate','h5_t.gateway.pay_rate','onecode.coll.pay_rate','pc.gateway.pay_rate');
			 var name = $('#previewProtocolContentModal #content'+i).attr("name");
			if(contentTempCount[i].split(":")[0]=="custManager" || contentTempCount[i].split(":")[0]=="serviceProvider"){
				
			}else{
				if($.inArray(name,arrRate)==-1){
				 if(kong.test(contents)){
					 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'不可为空');
					return; 
				 }
				}
			}
			
			 if('period'==name){
				 	if(!isPeriod(contents)){
				 		$.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'请填正确的周期(如T+1)');
						return;
					}
				 }
			 if('isSkipHoliday'==name){
			 	if(!isSkipHoliday(contents)){
			 		$.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'是否跳过节假日格式不正确');
					return;
				}
			 }
			 if($.inArray(name,arrRate)!=-1){
				if($("#previewProtocolContentModal #rate_"+i).is(":checked")){
				 if(!isPercent(contents)){
					 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'请填正确的百分数(如0.6%)');
					 return;
				 }
				}else{
					contents='';
				}
			 }
			 //结算卡号、银行卡号、联行号 只能为数字
			 var arr = new Array('bankCardNo','bankCardNo','CNAPS');
			 if($.inArray(name,arr)!=-1){
				 if(!isNumber(contents)){
					 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'只能为数字');
					 return;
				 }
			 }
			 //结算户名、结算卡开户行及支行信息，开户行地址，账户名称，开户银行
			 var arr = new Array('bankCardName','bankName','openAddressRec');
			 if($.inArray(name,arr)!=-1){
				 if(!isChar(contents)){
					 $.gyzbadmin.alertFailure(contentTempCount[i].split(":")[1]+'只能为汉字、字母、数字组合');
					 return;
				 }
			 }
			protocolContent = protocolContent.concat(contentTempCount[i].split(":")[1]+':'+contentTempCount[i].split(":")[0]+':'+contents+'@_@');
		 }
		
		var memo = $('#previewProtocolContentModal #memo').val();
	// 保存
	$.blockUI();
	$.post(window.Constants.ContextPath + '<%=ProtocolContentPath.BASE + ProtocolContentPath.EDIT %>', 
	{
		 'protocolName'		:protocolName,
		 'protocolContent'	:protocolContent,
		 'templateId'		:templateId,
		 'memo'				:memo,
		 'merchantCode'		:merchantCode,
		 'id' :$("#previewProtocolContentModal #protocolContentId").val()
	}, function(data) {
			$.unblockUI();   
			if(data.result == 'success'){
				$('#previewProtocolContentModal').modal('hide');
				$.gyzbadmin.alertSuccess('修改成功', null, function(){
					window.location.reload();
				});
			}else {
				$.gyzbadmin.alertFailure('修改失败:' + data.message);
			}
		}, 'json'
	);
	});
});	



</script>
</html>