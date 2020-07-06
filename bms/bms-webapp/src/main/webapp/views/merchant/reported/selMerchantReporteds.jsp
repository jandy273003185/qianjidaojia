<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>进件渠道选择</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style>



</head>

<body style="overflow-x:hidden;">
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
							<input type="hidden" name="count" id="count" value="">					
							<table class="search-table">
								<tr>
									<td class="td-left" >填写商户编号<span style="color:red">*</span></td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="merchantCode" name="merchantCode" maxlength="300"  placeholder="请填写商户编号">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="merchantCodeLab"></label>
									</span>
								</td>
									<td class="td-left">报备渠道<span style="color:red">*</span></td>
									<td class="td-right">
										<select name="channlCode" id="channlCode" style="width:300px;" onchange="selectChannlCode();">
											<option value="">--请选择报备渠道--</option>
											 <c:if test="${not empty channlInfoList }">
					                            <c:forEach items="${channlInfoList }" var="info">
					                            	<option id="${info.channlId}" value="${info.channlCode}">${info.channlName}</option>
					                            </c:forEach>
			                    		  	 </c:if>
										</select>
										<label id="channlCodeLabel" class="label-tips"></label>
									</td>
									
									
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
												查询<i class="icon-search icon-on-right bigger-110"></i>
											</button>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											 
										</span>
									</td>
								</tr>
							</table>
						
						</div>
					</div>
				</div><!-- /.page-content -->


	
	<!-- 底部-->
	<%@ include file="/include/bottom.jsp"%>
	</div><!-- /.main-content -->
	<!-- 设置 -->
	<%@ include file="/include/setting.jsp"%>
	</div>
	
</body>
<script type="text/javascript">
/** 清空按钮 **/
$(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
});


</script>
<script type="text/javascript">
$(".buttonSearch").click(function(){
	var channelNo = $("#channlCode").val();
	var merchantCode = $("#merchantCode").val();

	if(kong.test($("#merchantCode").val())){
		$("#merchantCodeLab").text("请填写商户号");
		return false;
	}
	if(kong.test($("#channlCode").val())){
		$("#channlCodeLabel").text("请选择渠道");
		return false;
	}

	if('BEST_PAY' == channelNo){
		window.location.href = window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.BESTPAYMERCHANTREPORTS%>?'+
		"&merchantCode="+merchantCode+
		"&channelNo="+channelNo;
		
	}else if('SUIXING_PAY' == channelNo){
		window.location.href = window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS%>?'+
		"&merchantCode="+merchantCode+
		"&channelNo="+channelNo;
	}else if('YQB' == channelNo){
		window.location.href = window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.YQBMERCHANTREPORTS%>?'+
		"&merchantCode="+merchantCode+
		"&channelNo="+channelNo;
	}else if('SUM_PAY' == channelNo){
		window.location.href = window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYMERCHANTREPORTS%>?'+
		"&merchantCode="+merchantCode+
		"&channelNo="+channelNo;
	}else{
		window.location.href = window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSMERCHANTREPORT%>';
	}
});
	
</script>
</html>