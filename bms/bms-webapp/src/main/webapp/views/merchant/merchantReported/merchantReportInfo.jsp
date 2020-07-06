<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ page import="java.util.*" %>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>进件详情</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>

<script type="text/javascript">

	$(function() {

		if($("#custType").val() =='1' ||$("#custType").val() =='2' ){
			//个体户、企业
			$("#businessPhotoType").attr("style","display:");
		}
		if($("#custType").val() =='0'){
			//个人
			$("#businessPhotoType").attr("style","display:none");
		}
		if($("#compMainAcctType").val() =='02'){
			//对私
			$("#bankCardPhoto_").attr("style","display:");
			$("#openAccount_").attr("style","display:none");
		}
		if($("#compMainAcctType").val() =='01'){
			//对公
			$("#bankCardPhoto_").attr("style","display:none");
			$("#openAccount_").attr("style","display:");
		}
		

		var custId = $("#custId").val();
		var authId = $("#authId").val();
		
		$("#openPhotoImageDiv").show();
    	$("#businessPhotoImageDiv").show();
    	$("#certAttribute1ImageDiv").show();
    	$("#certAttribute2ImageDiv").show();
    	$("#legalCertAttribute1ImageDiv").show();
    	$("#legalCertAttribute2ImageDiv").show();
    	$("#bankCardBackPhotoDiv").show();
    	$("#settleIdCardDiv").show();
    	$("#doorPhotoImageDiv").show();
    	$("#bankCardPhotoImageDiv").show();
    	$("#cooperateImageDiv").show();
    	
    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
    	$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=07&authId="+authId);
    	$("#openPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
    	$("#legalCertAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#legalCertAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#bankCardBackPhotoDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=14&authId="+authId);
    	$("#settleIdCardDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=13&authId="+authId);
    	$("#cooperateImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=15&authId="+authId);

    });


</script>
<body>
<!-- 用户信息 -->
<%@ include file="/include/top.jsp"%>

<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner">
		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>
			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<input type="hidden" id="channlCode" name="channlCode" value="${channlCode}"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" name="custType" id="custType" value="${merchantVo.custType}">
					<input type="hidden" name="compMainAcctType" id="compMainAcctType" value="${merchantVo.compMainAcctType}">
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="openPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="bankCardPhototemp"/>
					<input type="hidden" id="bankCardBackPhototemp"/>
					<input type="hidden" id="settleIdCardtemp"/>
					<input type="hidden" id="cooperatetemp"/>
					
					
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    <form  id="merchantForm"  method="post">
					    <table id="merchant_table" class="list-table">
					    <tbody>
                        	<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
							</tr>
						    <tr>
							</tr>
                            <tr>
								<td class="td-left" width="18%">商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly maxlength=""  value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户简称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="shortName" name="shortName" readonly placeholder="请输入商户简称" maxlength=""  value="${custInfo.shortName }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">商户名称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" readonly placeholder="请输入营业执照名称/店名" maxlength=""  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0'}">
										<td class="td-right">个人</td>
									</c:when>
									<c:when test="${merchantVo.custType =='1'}">
										<td class="td-right">企业</td>
									</c:when>
									<c:when test="${merchantVo.custType =='2'}">
										<td class="td-right">个体户</td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td class="td-left">注册地址：</td>
								<td class="td-right" colspan="3">
	                                <input type="text" name="cprRegAddr" id="cprRegAddr" readonly placeholder="详细地址"  value="${custInfo.custAdd }">
								</td>
							</tr>
						 	<tr>
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="businessLicense" id="businessLicense" readonly  placeholder="请输入营业执照"  value="${custInfo.businessLicense }" style="width:90%">
									<label class="label-tips" id="businessLicenseLab"></label>
								</td>
							</tr>
							<tr id="businessPhotoType" style = "display:">
								<td class="td-left">营业执照照片：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle="modal" class="tooltip-success businessPhotoClick" data-target="#previewImageModal">
										<label id="businessPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="businessPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;display:none">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="businessPhotoImageVal02">  
										<input type="file" name="businessPhoto" id="businessPhoto" onChange="showBusinessPhotoImage(this)">
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bussinessPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal"  >
										<label id="doorPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="doorPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="doorPhotoPath" name="doorPhotoPath" />
										<input type="hidden" id="doorPhotoImageVal02"  />  
										<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.doorPhotoPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<!-- <tr id="cooperateType" style = "display:">
								<td class="td-left">合作函：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success cooperateClick"  data-target="#previewImageModal"  >
										<label id="cooperateDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="cooperateImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="cooperatePath" name="cooperatePath" />
										<input type="hidden" id="cooperateImageVal02"  />  
										<input type="file" name="cooperate" id="cooperate" onchange="showCooperateImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr> -->
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" readonly placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
									<label class="label-tips" id="representativeNameLab"></label>
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" readonly  placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
									<label class="label-tips" id="representativeCertNoLab"></label>
								</td>
							</tr>
                            <tr>
							</tr>
							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right"> 
									<input type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名" readonly  value="${custInfo.contactName }" maxlength="" style="width:90%">
									<label class="label-tips" id="contactNameLab"></label>
								</td>
								<td class="td-left">联系人手机号：</td>
								<td class="td-right"> 
									<input type="text" id="contactMobile" name="contactMobile" placeholder="请输入联系人电话" readonly  value="${custInfo.contactMobile }" maxlength="" style="width:90%">
									<label class="label-tips" id="contactMobileLab"></label>
								</td>
							</tr>
							<tr>
								
								<td class="td-left">联系人邮箱：</td>
								<td class="td-right"> 
									<input type="text" id="merchantEmail" name="merchantEmail" placeholder="请输入联系人邮箱" readonly  value="${custInfo.merchantEmail }" maxlength="" style="width:90%">
									<label class="label-tips" id="merchantEmailLab"></label>
								</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证正面：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle="modal" class="tooltip-success certAttribute1Click" data-target="#previewImageModal">
									<label id="certAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="certAttribute1ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%; display:none">
									</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="certAttribute1FilePath" name="certAttribute1FilePath" />
										<input type="hidden" id="certAttribute1Val02">  
										<input type="file" name="certAttribute1" id="certAttribute1" onChange="showCertAttribute1Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success certAttribute1Click" data-target="#previewImageModal" >
										<label id="certAttribute1Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardOPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：</td>
								<td class="td-right" colspan="3"> 
									<!-- <a data-toggle="modal" class="tooltip-success certAttribute2Click" data-target="#previewImageModal">
										<label id="certAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="certAttribute2ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%; display:none">
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="certAttribute2FilePath" name="certAttribute2FilePath" />
										<input type="hidden" id="certAttribute2Val02">  
										<input type="file" name="certAttribute2" id="certAttribute2" onChange="showCertAttribute2Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success certAttribute2Click" data-target="#previewImageModal" >
										<label id="certAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardFPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：</td>
								<td class="td-right"> 
									<input type="text" id="actNm" name="actNm" maxlength="100" placeholder="请输入结算账户名称" readonly  value="${custInfo.custName }" style="width:90%">
									<label class="label-tips" id="actNmLab"></label>
								</td>
								<td class="td-left">银行卡号：</td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号" readonly  value="${custInfo.compMainAcct }" style="width:90%">
									<label class="label-tips" id="bankCardNoLab"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：</td>
									<c:choose>
										<c:when test="${merchantVo.compMainAcctType =='02'}">
											<td class="td-right">对私</td>
										</c:when>
										<c:when test="${merchantVo.compMainAcctType =='01'}">
											<td class="td-right">对公</td>
										</c:when>
										<c:otherwise>
											<td class="td-right"></td>
										</c:otherwise>
									</c:choose>
								<td class="td-left">结算费率：</td>
								<td class="td-right"> 
									<input type="text" id="rate" name="rate" maxlength="100" readonly  value="${tdMerchantDetailInfo.rate }" style="width:90%">
									<label class="label-tips" id="rateLab"></label>
								</td>
							</tr>
							<tr id="openAccount_" style = "display:none">
								<td class="td-left">开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<!-- <a data-toggle="modal" class="tooltip-success openPhotoClick" data-target="#previewImageModal">
										<label id="openPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="openPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" >
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="certAttribute0Val02">  
										<input type="file" name="certAttribute0" id="certAttribute0" onChange="showCertAttribute0Image(this)">
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success openPhotoClick" data-target="#previewImageModal" >
										<label id="openPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.openAccountPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr id="bankCardPhoto_" style = "display:">
				                <td class="td-left">银行卡正面照：</td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoImageVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showBankCardPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick" data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bankCardPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr id="bankCardBackPhotoType" style = "display:">
				                <td class="td-left"> 银行卡反面照： </td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success bankCardBackPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardBackPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardBackPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardBackPhotoVal02"  />   
										<input type="file" name="bankCardBackPhoto" id="bankCardBackPhoto" onchange="showbankCardBackPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success bankCardBackPhotoClick" data-target="#previewImageModal" >
										<label id="bankCardBackPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bankCardBackPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
					         </tr>
					         <tr id="settleIdCardType" style = "display:">
				                <td class="td-left">手持身份证正面照： </td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success settleIdCardClick"  data-target="#previewImageModal"  >
										<label id="settleIdCardDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleIdCardImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleIdCardVal02"  />  
										<input type="file" name="settleIdCard" id="settleIdCard" onchange="showsettleIdCardImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success settleIdCardClick" data-target="#previewImageModal" >
										<label id="settleIdCardDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.handIdCardPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							 </tr>
							 <tr id="infoQrcodeType" style = "display:">
				                <td class="td-left">微信联系人信息确认二维码： </td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success infoQrcodeClick" data-target="#previewImageModal" >
										<label id="infoQrcodeDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.infoQrcodePath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							 </tr>
						</tbody>
						</table>
					    </form>
					    </div>
					    <div style="margin:50px 0 0 0;text-align:center">
					    	<button type="button"  class="btn btn-default" onclick="exit()">关闭</button> 
					    </div>
					</section>
					</div>
				</div>
			</div><!-- /.modal -->
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
<div class="modal fade" id="previewImageModal"  aria-hidden="true">
	<div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
    	<div id="showImageDiv" style="width:100%;height:100%;">
        	<img id="showImage" style="width:100%;height:100%;">
        </div>
     </div>
</div>   
<script type="text/javascript">

     /** 点击预览大图 **/
   	function bigImg(obj){
        /* $('#showImageDiv #showImage').attr("src",obj.src); */
        var realWidth;
     	var realHeight
     	$('#showImageDiv #showImage').attr("src",obj.src).load(function(){
     		realWidth = this.width;
     		realHeight = this.height;
     		var scale =  realWidth/realHeight;
     		if(realWidth >800){
     			realWidth = 800;
     			realHeight = realWidth / scale;
     		}
     		
     		$("#imageDiv").css("width",realWidth+"px").css("height",realHeight+"px");
     	});
    }
        
   	/** 二维码预览 **/
   	$('.infoQrcodeClick').click(function(){
   		var divObj = document.getElementById("showImageDiv");
   		var imageObj = document.getElementById("showImage");
   		var obj = document.getElementById("shopInteriorClick");
   		return previewImage(divObj,imageObj,obj); 
   	});

   	/** 门头照点击预览 **/
  	$('.doorPhotoClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("doorPhoto");
  		return previewImage(divObj,imageObj,obj);
  	});
	
  	/** 银行卡点击预览 **/
  	$('.bankCardPhotoClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("bankCardPhoto");
  		return previewImage(divObj,imageObj,obj);
  	});
  	
  	/** 开户许可证背面点击预览 **/
  	$('.openAccountClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("openAccount");
  		return previewImage(divObj,imageObj,obj);
  	});
  	
  	/** 身份证背面点击预览 **/
  	$('.legalCertAttribute2Click').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("legalCertAttribute2");
  		return previewImage(divObj,imageObj,obj);
  	});
  	
  	/** 身份证正面点击预览 **/
  	$('.legalCertAttribute1Click').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("legalCertAttribute1");
  		return previewImage(divObj,imageObj,obj);
  	});
  	
  	/** 营业执照点击预览 **/
  	$('.businessPhotoClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("businessPhoto");
  		return previewImage(divObj,imageObj,obj);
  	});

  	/** 店内点击预览 **/shopCheckStandPath
  	$('.shopInteriorClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("shopInterior");
  		return previewImage(divObj,imageObj,obj); 
  	});
  	
  	/** 店内前台点击预览 **/
  	$('.shopCheckStandClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("shopCheckStand");
  		return previewImage(divObj,imageObj,obj); 
  	});
  	
  	/** 非法人结算授权函 **/
  	$('.letterOfAuthClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("letterOfAuth");
  		return previewImage(divObj,imageObj,obj); 
  	});
   /*  //图片预览
    function shopInteriorImage(obj){  
   		var divObj = document.getElementById("shopInteriorDiv");  
   		var imageObj = document.getElementById("shopInteriorImageDiv");  
   		return previewImage(divObj,imageObj,obj);  
   }
    
    function showCooperateImage(obj){  
		 var divObj = document.getElementById("cooperateDiv");  
		 var imageObj = document.getElementById("cooperateImageDiv");  
		 return previewImage(divObj,imageObj,obj);  
	}
   function showBusinessPhotoImage(obj){  
   		 var divObj = document.getElementById("businessPhotoDiv");  
   		 var imageObj = document.getElementById("businessPhotoImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   }

	function showOpenPhotoImage(obj){  
	 	var divObj = document.getElementById("openPhotoDiv");  
	 	var imageObj = document.getElementById("openPhotoImageDiv");  
	 	return previewImage(divObj,imageObj,obj);  
	}

	function showDoorPhotoImage(obj){  
		 var divObj = document.getElementById("doorPhotoDiv");  
		 var imageObj = document.getElementById("doorPhotoImageDiv");  
		 return previewImage(divObj,imageObj,obj);  
	}

   	function showCertAttribute1Image(obj){  
   		 var divObj = document.getElementById("certAttribute1Div");  
   		 var imageObj = document.getElementById("certAttribute1ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showCertAttribute2Image(obj){  
   		 var divObj = document.getElementById("certAttribute2Div");  
   		 var imageObj = document.getElementById("certAttribute2ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showLegalCertAttribute1Image(obj){  
  		 var divObj = document.getElementById("legalCertAttribute1Div");  
  		 var imageObj = document.getElementById("legalCertAttribute1ImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showLegalCertAttribute2Image(obj){  
   		 var divObj = document.getElementById("legalCertAttribute2Div");  
   		 var imageObj = document.getElementById("legalCertAttribute2ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showBankCardPhotoImage(obj){  
  		 var divObj = document.getElementById("bankCardPhotoDiv");  
  		 var imageObj = document.getElementById("bankCardPhotoImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
  		}
   	function showbankCardBackPhotoImage(obj){  
  		 var divObj = document.getElementById("bankCardBackPhotoDiv");  
  		 var imageObj = document.getElementById("bankCardBackPhotoImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showsettleIdCardImage(obj){  
   		 var divObj = document.getElementById("settleIdCardDiv");  
   		 var imageObj = document.getElementById("settleIdCardImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	} */
   	
   	function exit() {
   		if (confirm("您确定要关闭吗？")) {
   			window.opener=null;
   		
   			window.open("","_self");
   		
   			window.close();
   		}
   	};
    	
</script>
</body>
</html>