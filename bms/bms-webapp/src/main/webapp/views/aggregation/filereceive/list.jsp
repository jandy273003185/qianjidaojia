<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.qifenqian.bms.bal.filereceive.controller.FileReceivePath"%>
<base target="_self">  
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
<meta charset="utf-8" />
<title>聚合对账</title>
<meta name="keywords" content="聚合对账系统" />
<meta name="description" content="聚合对账" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}
.channelIdCls{
 width:60%;
}
</style>
</head>
<script type="text/javascript">
function loadBalFile(){
	$(".search-table #status").val($(".search-table #statusTemp").val());
	$(".search-table #fileType").val($(".search-table #fileTypeTemp").val());
}
$(function() {
	var fileReceives = ${fileReceiveList};
	var fileReceiveTrList = $("tr.fileReceive");
	$.each(fileReceives, function(i, value) {
		$.data(fileReceiveTrList[i], "fileReceive", value);
	});
	
	$(".fileUploadBtn").click(function(){
		
		var channelId = $('#fileUploadModal #channelId').val();
		if(kong.test(channelId)) {
			$.gyzbadmin.alertFailure('请选择渠道');
			return;
		}
		
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
		var inputName= fileType+"@"+workDate+"@"+channelId;
		
		table.rows[3].cells[1].getElementsByTagName("input")[0].name=inputName;
		
		    $.blockUI();
			$.ajaxFileUpload({  
		        url : window.Constants.ContextPath +'<%=FileReceivePath.BASE + FileReceivePath.FILEUPLOAD%>',
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
			}) 
	})
	
	$(".clearBalFile").click(function(){
		$(':input','#searchForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
});
</script>
<body onload="loadBalFile()">
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
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
				 <div class="modal fade" id="fileUploadModal" tabindex="-1" >
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" > &times;</button>
					            <h4 class="modal-title" id="myModalLabel">上传对账文件</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table" id="fileTab">
					            	<tr>
										<td class="td-left">渠道:</td>
										<td class="td-right">
											<internal:selectChannelTag id="channelId" name ="channelId" clasS="channelIdCls"/>			
										</td>
									</tr>
									<tr>
										<td class="td-left">文件类型:</td>
										<td class="td-right">
											<select name="fileType" id="fileType" style="width:60%;">
											        <option value="">- 请选择  -</option>
													<option value="BALANCE">对账</option>
													<option value="SETTLE">清算</option>
												</select>
										</td>
									</tr>
									
									<tr>
										<td class="td-left">数据日期:</td>
										<td class="td-right">
											<input type="text" id="workDate" name="workDate" readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:60%;"/> 
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
							<form id="searchForm" action='<c:url value="<%=FileReceivePath.BASE + FileReceivePath.LIST%>"/>' method="post">
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
										<td class="td-left">渠道</td>
										<td class="td-right">
										
											<external:selectChannelTag id="channelId" name ="channelId" defaultValue="${queryBean.channelId} }"/>	
											
											<span class="input-icon"> 		
											  	<select name="channelId">
													<option value="">请选择</option>					
													<c:forEach items="${baseChannels}" var="channel">
														<option value="${channel.channelId }" <c:if test="${channel.channelId==fileReceive.channelId}">selected</c:if>>
															${channel.channelName }
														</option>
													</c:forEach>
												</select>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="8" align="center">
											<gyzbadmin:function url="<%=FileReceivePath.BASE + FileReceivePath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询 <i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											
											<button class="btn btn-purple btn-sm btn-margin clearBalFile">
												清空 <i class="icon-move icon-on-right bigger-110"></i>
											</button>
											
											<gyzbadmin:function url="<%=FileReceivePath.BASE + FileReceivePath.FILEUPLOAD%>">
												<button class="btn btn-purple btn-sm btn-margin fileUploadLink" data-toggle='modal' data-target="#fileUploadModal">
													上传文件
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">聚合对账文件列表</div>
							
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout: fixed;">
									<thead>
										<tr>
											<th width="8%">文件编号</th>
											<th width="8%">渠道</th>
											<th width="4%">文件类型</th>
											<th width="6%">接收日期</th>
											<th width="6%">数据日期</th>
											<th width="9%">文件名</th>
											<th width="9%">存储路径</th>
											<th width="4%">总记录数</th>
											<th width="9%">创建时间</th>
											<th width="4%">状态</th>
											<th width="9%">处理开始时间</th>
											<th width="10%">处理结束时间</th>
											<th width="10%">最后更新时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${fileReceiveList}" var="fileReceive">
											<tr class="fileReceive">
												<td>${fileReceive.fileId }</td>
												<td>${fileReceive.channelName }</td>
												<td>
													<c:if test="${fileReceive.fileType  == 'BALANCE' }">对账</c:if>
													<c:if test="${fileReceive.fileType  == 'SETTLE' }">清算</c:if>
												</td>
												<td>${fileReceive.receiveDate }</td>
												<td>${fileReceive.workDate }</td>
												<td>${fileReceive.fileName }</td>
												<td>${fileReceive.storagePath }</td>
												<td>${fileReceive.totalCount }</td>
												<td>${fileReceive.instDatetime }</td>
												<td>
													<c:if test="${fileReceive.status  == 'INIT' }">初始化</c:if>
													<c:if test="${fileReceive.status  == 'ANALYSIS' }">解析完成</c:if>
													<c:if test="${fileReceive.status  == 'BAL_ING' }">对账中</c:if>
													<c:if test="${fileReceive.status  == 'BAL_OVER' }">对账完成</c:if>
													<c:if test="${fileReceive.status  == 'INVALID' }">无效</c:if>
													<c:if test="${fileReceive.status  == 'ERROR' }">异常</c:if>
												</td>
												<td>${fileReceive.balBeginDatetime }</td>
												<td>${fileReceive.balEndDatetime }</td>
												<td>${fileReceive.updateDatetime }</td>
											</tr>
										</c:forEach>
										<c:if test="${empty fileReceiveList}">
											<tr>
												<td colspan="13" align="center">
												<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty fileReceiveList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
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
</body>
</html>