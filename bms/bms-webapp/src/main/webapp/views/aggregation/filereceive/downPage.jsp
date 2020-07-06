<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.qifenqian.bms.bal.filereceive.controller.FileReceivePath"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>

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
$(function(){
	var fileReceives = ${fileReceiveList};
	var fileReceiveTrList = $("tr.fileReceive");
	$.each(fileReceives, function(i, value) {
		$.data(fileReceiveTrList[i], "fileReceive", value);
	});
	
	$(".fileUploadBtn").click(function(){
		var workDate = $('#fileUploadModal #workDate').val();
		if(kong.test(workDate)) {
			$.gyzbadmin.alertFailure('数据日期不可为空');
			return;
		}
		
	}) ;
	$("#submitData").click(function(){
		$.blockUI();
		$.ajax({
			type:"POST",
			url: window.Constants.ContextPath +'<%=FileReceivePath.BASE + FileReceivePath.DOWNFILE %>',
			success:function(data){
				$.unblockUI();
			}
		});
	});
});


</script>
<script type="text/javascript">
function loadBalFile(){
	$(".search-table #status").val($(".search-table #statusTemp").val());
	$(".search-table #fileType").val($(".search-table #fileTypeTemp").val());
}
function getCategoryTypeInfo(){
	var merchantId = $("#merchantCode").val().trim();
	if(merchantId!=""){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=FileReceivePath.BASE + FileReceivePath.QUERYMECHANTINFO %>',
			data:
			{
				"merchantId" : merchantId
			},
			success:function(data){
				if(data.result!="SUCCESS"){
					$("#merchantCodeLab").text("该商户不存在，请核对后在查询！");
					$('#submitButton').attr("disabled","disabled");
					
				}else{
					$("#merchantCodeLab").text("");
				}
			},
			error:function (err) {
				$("#merchantCodeLab").text("该商户不存在，请核对后在查询！");
				$('#submitButton').attr("disabled","disabled");
		    }
		})
	}else{
		$('#submitButton').attr("disabled",false);
		$("#merchantCodeLab").text("");
	}
	
};

 function submitFile(){
	 $('#submitButton').submit();
	<%-- var merchantCode = $("#merchantCode").val().trim();
	var channlCode = $("#channlCode").val().trim();
	var workDate = $("#workDate").val().trim();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=FileReceivePath.BASE + FileReceivePath.DOWNFILE %>',
		data:
		{
			"merchantCode" : merchantCode,
			"channlCode" : channlCode,
			"workDate" : workDate
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				//$("#merchantCodeLab").text("该商户不存在，请核对后在查询！");
			}
		}
	}) --%>
	
	
};

</script>
<body >
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
				 <div class="row">
						<div class="col-xs-12">
						<!-- 查询条件 -->
							<form action='<c:url value="<%=FileReceivePath.BASE + FileReceivePath.DOWNFILE %>"/>' method="post" >
							<table class="search-table" style="margin: 40px 0;">
								<tr>
									<td class="td-left">商户编号(选填)：</td>
									<td class="td-right"> 
										<span class="input-icon">
											<input type="text"   id="merchantCode" name="merchantCode" maxlength="300" onblur="getCategoryTypeInfo();"  placeholder="请填写商户编号">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="merchantCodeLab"></label>
										</span>
									</td>
									
									<td class="td-left">渠道：</td>
									<td class="td-right"> 
										<select name="channlCode" id="channlCode"  >
												<option value="">--请选择报备渠道--</option>
												<c:if test="${not empty channlInfoList }">
													<c:forEach items="${channlInfoList }" var="info">
														<option id="${info.channlId}" value="${info.channlCode}">${info.channlName}</option>
													</c:forEach>
												</c:if>
										</select> <label id="channlCodeLabel" class="label-tips"></label>
									</td>
									
									<td class="td-left">文件下载日期：</td>
									<td class="td-right"> 
										<span class="input-icon">
											<input type="text" id="workDate" name="workDate" readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:60%;"/>
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								
									
								</tr>
								
								
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											<button type="submit" id="submitButton" class="btn btn-purple btn-sm" onclick="submitFile();" style="width: 300px;margin: 20px;" >
												下载
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form><!-- /.modal -->
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