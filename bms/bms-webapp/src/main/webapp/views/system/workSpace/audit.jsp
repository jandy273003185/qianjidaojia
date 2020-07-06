<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.myWorkSpace.WorkSpacePath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>

<link rel="stylesheet"  href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />

<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>

<html>
<head>
	<meta charset="utf-8" />
	<title>审核商户信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>

<body onload="loadFunction()">
	<!-- 用户信息 -->
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
				
					<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="list-table-header">审核商户</div>

							<div class="table-responsive">
							<input type="hidden" id="email_02" value="${info.email }">
							<input type="hidden" id="create_id" value="${info.createId }">
							<input type="hidden" id="taskId" value="${taskId }">
							<input type="hidden" id="custId" value="${info.custId }">
							<table id="sample-table-2" class="list-table" >
							    <tr>
									<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">企业信息：</td>
									
								</tr>
								
								<tr>
									<td class="td-left" width="18%" >商户编号：</td>
									<td class="td-right" width="32%" > 
										<span class="custId">${info.custId }</span>
									</td>
							
									<td class="td-left" width="18%" >邮箱：</td>
									<td class="td-right" width="32%"> 
										<span class="email">${info.email }</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="18%" >公司名称：</td>
									<td class="td-right" width="32%" > 
										<span class="custName">${info.custName }</span>
									</td>
							
									<td class="td-left" width="18%" >营业执照注册号：</td>
									<td class="td-right" width="32%"> 
										<span class="businessLicense">${info.businessLicense }</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >营业执照所在地：</td>
									<td class="td-right" >
										
										<span class="businessRegAddr">${info.businessRegAddr }</span>
									</td>
								
									<td class="td-left" >营业期限：</td>
									<td class="td-right" > 
										<span class="businessTerm">
											${info.businessTerm}
											</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >企业地址：</td>
									<td class="td-right" > 
										<span class="custAdd">${info.custAdd }</span>
									</td>
								
									<td class="td-left" >联系电话：</td>
									<td class="td-right" > 
										<span class="contactPhone">${info.contactPhone }</span>
									</td>
								</tr>
								
								
								<tr>
										<td class="td-left">营业执照扫描件：</td>
										<td class="td-right">
											<a data-toggle='modal' class="tooltip-success businessPhotoImage"  data-target="#previewImageModal">
																			
											  <img  id="businessPhotoImage" onclick="bigImg(this)"  src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${businessImage}" style="width:120px;height:100px;"   />										
											</a>
											
										</td>
										<td class="td-left">开户许可证：</td>
										<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute0Image"  data-target="#previewImageModal">									
											  <img  id="certAttribute0Image" onclick="bigImg(this)" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${certAttribute}"  style="width:120px;height:100px;"  />										
										</a>
									</td>
									</tr>
									
								<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">银行信息：</td>
								</tr>
								<tr>
									<td class="td-left" >银行开户名：</td>
									<td class="td-right" > 
										<span class="openName">${info.custName }</span>
									</td>
									
									<td class="td-left" >开户银行：</td>
									<td class="td-right" > 
										  <input type="hidden" id="bankHidden" value="${info.compAcctBank}">
										  <sevenpay:selectBankTag id="bank" name ="bank"  banks="${banklist}"  clasS="width-90"/>
									</td>
								</tr>
								<tr>
									<td class="td-left" >银行账号：</td>
									<td class="td-right"  > 
										<span class="bankCard">${info.compMainAcct }</span>
									</td>
									<td class="td-left" >支行信息：</td>
									<td class="td-right"  > 
										<span class="bankCard">${info.branchBank }</span>
									</td>
								</tr>
								<%-- <tr>
									<td class="td-left" >开户行地址：</td>
									<td class="td-right" colspan="3" > 
										<span class="bankCard">${info.openAddressRec }</span>
									</td>
								</tr> --%>
								<tr>
									<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">法人信息：</td></tr>
								<tr>
									<td class="td-left" >法人真实姓名：</td>
									<td class="td-right" > 
										<span class="representativeName">${info.representativeName }</span>
									</td>
									<td class="td-left" >法人身份证号码：</td>
									<td class="td-right" > 
										<span class="representativeCertNo">${info.representativeCertNo }</span>
									</td>
								</tr>
								<tr>
								   <td class="td-left" >身份证正面：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute1"  data-target="#previewImageModal">									
											  <img  id="certAttribute1" onclick="bigImg(this)" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard1}"  style="width:120px;height:100px;"  />										
										</a>
										
									</td>
									<td class="td-left" >身份证背面：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute2"  data-target="#previewImageModal">									
											  <img  id="certAttribute2" onclick="bigImg(this)" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard2}"  style="width:120px;height:100px;"  />										
										</a>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人手机号码：</td>
									<td class="td-right" colspan="3" > 
										<span class="representativeMobile">${info.representativeMobile }</span>
									</td>
								</tr>	
							<%-- 	
								<tr>
									<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">费率方式：</td></tr>
								<tr>
									<td class="td-left" >费率方式：</td>
									<td class="td-right" colspan="3"> 
										<c:if test="${ruleType=='Package' }">
											<sevenpay:selectRulePackageTag name="rule" feeCode="${info.feeCode }"  style="width:50%;font-size:10px;text-align:center;"/>
										</c:if>
										<c:if test="${ruleType=='Fixed' }">
											<sevenpay:selectRuleFixedTag name="rule" feeCode="${info.feeCode }"  style="width:50%;font-size:10px;text-align:center;"/>
										</c:if>
									</td>
								</tr> --%>
							</table>
							 <div class="modal-footer" style="text-align:center;">
					            <button type="button" class="btn btn-primary " onclick="noPassAuditor()"style="margin-right: 329px;">审核不通过</button>
					            <button type="button" class="btn btn-primary " onclick="sureAuditor()">确定审核</button>
					         </div>
							
							</div>
					</div>
					
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
	</div>
	<!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:60%;height:80%;">
		         <div id="showImageDiv" style="width:100%;height:100%;">
		           <img id="showImage" style="width:100%;height:100%;">
		         </div>
		     </div>
		</div>
</body>
<script type="text/javascript">

function loadFunction(){
	$("#bank").val($("#bankHidden").val());
}
function bigImg(obj){
    $('#showImageDiv #showImage').attr("src",obj.src);
};
function sureAuditor(){
	var email=$("#email_02").val();
	var custName=$(".custName").text();
	$.blockUI();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=AuditorPath.BASE + AuditorPath.SUREAUDITOR %>',
		data:
		{
			"custId" :$("#custId").val(),
			"email":email,
			"custName":custName,
			"taskId" :$("#taskId").val(),
			"compMainAcct":$(".bankCard").text(),
			"representativeMobile":$(".representativeMobile").text(),
			"compAcctBank":$(".bank").text()
		},
		
		success:function(data){
			$.unblockUI();
			if(data.result=="success"){
				
				$.gyzbadmin.alertSuccess('审核商户成功！', null, function(){
					window.location.href = window.Constants.ContextPath + '/system/workSpace/myTaskList';
					//window.location.reload();
				});
			}else{
				$.gyzbadmin.alertFailure("审核商户失败！"+data.message);
			}
		}
	});
}


function noPassAuditor(){
	var email=$("#email_02").val();
	var createId=$("#create_id").val();
	$.blockUI();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=AuditorPath.BASE + AuditorPath.NOPASSAUDITOR %>',
		data:
		{
			"custId" :$("#custId").val(),
			"email":email,
			"createId":createId,
			"taskId" :$("#taskId").val()
		},
		success:function(data){
			
			$.unblockUI();
			window.location.href = window.Constants.ContextPath + '/system/workSpace/myTaskList';
			<%-- if(data.result=="success"){
				
				/* $.gyzbadmin.alertSuccess('审核邮件发送成功！', null, function(){ */
					window.location.href = window.Constants.ContextPath + '<%=AuditorPath.BASE+AuditorPath.LIST %>';
					//window.location.reload();
			/* 	}); */
			}else{
				$.gyzbadmin.alertFailure("审核邮件发送失败！"+data.message);
			} --%>
		}
	});
}
</script>