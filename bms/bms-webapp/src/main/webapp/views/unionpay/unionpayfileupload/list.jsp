<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.qifenqian.bms.unionPay.unionpayfileupload.UnionpayFilePath"%>
<base target="_self">  
<script src='<c:url value="/static/laydate/laydate.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
<meta charset="utf-8" />
<title>银联对账/清算文件管理</title>
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
<script type="text/javascript">
function loadBalFile(){
	$(".search-table #status").val($(".search-table #statusTemp").val());
	$(".search-table #fileType").val($(".search-table #fileTypeTemp").val());
}
$(function() {
	var balFileList = ${balFileList};
	var balFileTrList = $("tr.balFile");
	$.each(balFileList, function(i, value) {
		$.data(balFileTrList[i], "balFile", value);
	});
	
	$(".fileUploadBtn").click(function(){
		
		var fileType = $('#fileUploadModal #fileType').val();
		if(kong.test(fileType)) {
			$.gyzbadmin.alertFailure('请选择文件类型');
			return;
		}
		var workDate = $('#fileUploadModal #workDate').val();
		if(kong.test(workDate)) {
			$.gyzbadmin.alertFailure('数据日期不可为空');
			return;
		}
	
		var table = document.getElementById("fileTab");
	    
		var fileName = $('#fileUploadModal #fileName').val();
		if(kong.test(fileName)) {
			$.gyzbadmin.alertFailure('请上传对账文件');
			return;
		}
		var inputName= fileType+"@"+workDate;
		
		table.rows[2].cells[1].getElementsByTagName("input")[0].name=inputName;

		    $.blockUI();
			$.ajaxFileUpload({  
		        url : window.Constants.ContextPath +'<%=UnionpayFilePath.BASE + UnionpayFilePath.FILEUPLOAD%>',
		        secureuri : false,
		        fileElementId : ['fileName'],
		        dataType:'json', 
		        success : function(data, status) {  
		        	if(data.result=='SUCCESS'){
		        		$('#fileUploadModal').modal('hide');
						$.gyzbadmin.alertSuccess('上传成功', null, function(){
							window.location.reload();
						});
		        		
		        	}else{
		        		$.gyzbadmin.alertFailure('上传失败:' + data.message);
		        	}
		        } 
			});
	});
	
	$(".clearBalFile").click(function(){
		$(".search-table #workDate").val('');
		$(".search-table #status").val('');
		$(".search-table #fileType").val('');
	});
	
	// 加载删除
	$(".deleteFileLink").click(function(){
		var balFile = $.data($(this).parent().parent()[0],"balFile");
		$('#deleteFileModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteFileModal span.fileId').html(balFile.fileId);
			$('#deleteFileModal input.fileId').val(balFile.fileId);
		});
		
		$('#deleteFileModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteFileModal span.fileId').empty('');
			$('#deleteFileModal input.fileId').val('');
		});
	});
	
	// 删除
	$('.deleteFileBtn').click(function(){
		var fileId = $('#deleteFileModal input.fileId').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=UnionpayFilePath.BASE + UnionpayFilePath.DELETE %>', {
				'fileId' : fileId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteFileModal').modal('hide');
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
<body onload="loadBalFile()">
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
				 <div class="modal fade" id="fileUploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">上传对账/清算文件</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table" id="fileTab">
									<tr>
										<td class="td-left">文件类型:</td>
										<td class="td-right">
											<select name="fileType" id="fileType" style="width:80%;">
											        <option value="">- 请选择  -</option>
													<option value="BALANCE">对账</option>
													<option value="SETTLE">清算</option>
												</select>
										</td>
									</tr>
									<tr>
										<td class="td-left">数据日期:</td>
										<td class="td-right">
											<input type="text" id="workDate" name="workDate" readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/> 
										</td>
									</tr>
									<tr>
										<td class="td-left">文件</td>
										<td class="td-right">	
										<input type ="file" name ="fileName" id="fileName" style="width:80%;">										
										</td>
									</tr>
					            </table>
					         </div>
					          <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary fileUploadBtn">提交</button> 
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
					

					<div class="row">
						<div class="col-xs-12">
							<!-- 查询条件 -->
							<form action='<c:url value="<%=UnionpayFilePath.BASE + UnionpayFilePath.LIST%>"/>' method="post">
								<table class="search-table">
									<tr>
										<td class="td-left">数据日期</td>
										<td class="td-right">
											<input type="text" id="workDate" name="workDate" readonly="readonly" value="${queryBean.workDate }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/> 
										</td>
										
										<td class="td-left">状态</td>
										<td class="td-right">
											<input type="hidden" id="statusTemp" name="statusTemp" value="${queryBean.status }" >
											<span class="input-icon"> 
												<select name="status" id="status">
													<option value="">- 请选择  -</option>
													<option value="INIT">初始化</option>
													<option value="ANALYSIS">解析完成</option>
													<option value="BAL_ING">对账中</option>
													<option value="BAL_OVER">对账完成</option>
													<option value="INVALID">无效</option>
													<option value="ERROR">异常</option>
												</select>
											</span>
										</td>
										<td class="td-left">文件类型</td>
										<td class="td-right">
											<input type="hidden" id="fileTypeTemp" name="fileTypeTemp" value="${queryBean.fileType }" >
											<span class="input-icon"> 
												<select name="fileType" id="fileType">
													<option value="">- 请选择  -</option>
													<option value="BALANCE">对账</option>
													<option value="SETTLE">清算</option>
												</select>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
											<button type="submit" class="btn btn-purple btn-sm">
												查询 <i class="icon-search icon-on-right bigger-110"></i>
											</button>
											<button class="btn btn-purple btn-sm btn-margin clearBalFile">
												清空 <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=UnionpayFilePath.BASE + UnionpayFilePath.FILEUPLOAD%>">
												<button class="btn btn-purple btn-sm btn-margin fileUploadLink" data-toggle='modal' data-target="#fileUploadModal">
													上传文件
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">银联对账/清算文件列表</div>
							
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout: fixed;">
									<thead>
										<tr>
											<th width="12%">文件编号</th>
											<th width="4%">文件类型</th>
											<th width="6%">接收日期</th>
											<th width="6%">数据日期</th>
											<th width="10%">文件名</th>
											<th width="10%">存储路径</th>
											<th width="4%">总记录数</th>
											<th width="9%">创建时间</th>
											<th width="4%">状态</th>
											<th width="9%">处理开始时间</th>
											<th width="9%">处理结束时间</th>
											<th width="9%">最后更新时间</th>
											<th width="4%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${balFileList}" var="balFile">
											<tr class="balFile">
												<td>${balFile.fileId }</td>
												<td>
													<c:if test="${balFile.fileType  == 'BALANCE' }">对账</c:if>
													<c:if test="${balFile.fileType  == 'SETTLE' }">清算</c:if>
												</td>
												<td>${balFile.receiveDate }</td>
												<td>${balFile.workDate }</td>
												<td>${balFile.fileName }</td>
												<td>${balFile.storagePath }</td>
												<td>${balFile.totalCount }</td>
												<td>
													<fmt:formatDate value="${balFile.instDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<c:if test="${balFile.status  == 'INIT' }">初始化</c:if>
													<c:if test="${balFile.status  == 'ANALYSIS' }">解析完成</c:if>
													<c:if test="${balFile.status  == 'BAL_ING' }">对账中</c:if>
													<c:if test="${balFile.status  == 'BAL_OVER' }">对账完成</c:if>
													<c:if test="${balFile.status  == 'INVALID' }">无效</c:if>
													<c:if test="${balFile.status  == 'ERROR' }">异常</c:if>
												</td>
												<td>
													<fmt:formatDate value="${balFile.balBeginDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<fmt:formatDate value="${balFile.balEndDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<fmt:formatDate value="${balFile.updateDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<gyzbadmin:function url="<%=UnionpayFilePath.BASE + UnionpayFilePath.DELETE%>">
														<a href="#" class="tooltip-error deleteFileLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteFileModal">
															<span class="red"><i class="icon-trash bigger-120"></i></span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty balFileList}">
											<tr>
												<td colspan="12" align="center">
												<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty balFileList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
				<div class="modal fade" id="deleteFileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">删除文件</h4>
				         </div>
				         <div class="modal-body" align="center">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该配置文件[<span class="fileId"></span>]么？</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="fileId" class="fileId" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary deleteFileBtn">确定</button>
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
</body>
</html>