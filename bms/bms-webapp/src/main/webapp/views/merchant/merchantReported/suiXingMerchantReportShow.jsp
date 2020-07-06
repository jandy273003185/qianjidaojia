<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.seven.micropay.channel.enums.suixinpay.SuixinBankType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<script src="<c:url value='/static/topayProfit/layui/layui.js'/>"></script>
<script src="<c:url value='/static/topayProfit/layui/layui.all.js'/>"></script>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>随行付进件</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<c:url value='/static/topayProfit/layui/css/layui.css' />" />	
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.btn, .btn-default, .btn:focus, .btn-default:focus {
		background-color:#fff !important;
    	border: 1px solid #ccc !important;
    	color: #464444 !important;
    	font-family: inherit !important;
    	text-shadow: none !important;
    	font-weight:300 !important;
    }
	</style>
</head>
<script type="text/javascript">

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
					<input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="SUIXING_PAY"/>
			
					<!-- zhanggc 回显  -->
					<input type="hidden" id="mecTypeFlagQ" value="${merchantDetailInfo.mecTypeFlag}" />
					<input type="hidden" id="suiXingMerchantTypeQ" value="${merchantDetailInfo.suiXingMerchantType}" />
					
					<input type="hidden" id="merchantProvinceQ" value="${merchantDetailInfo.merchantProvince}" />
					
					<input type="hidden" id="merchantCityQ" value="${merchantDetailInfo.merchantCity}" />
					<input type="hidden" id="merchantAreaQ" value="${merchantDetailInfo.merchantArea}" />
					<input type="hidden" id="bankProvinceQ" value="${merchantDetailInfo.bankProvince}" />
					<input type="hidden" id="bankCityQ" value="${merchantDetailInfo.bankCity}" />
					
					<input type="hidden" id="suiXinBankQ" value="${merchantDetailInfo.suiXinBank}" />
					
					<input type="hidden" id="interBankNameQ" value="${merchantDetailInfo.interBankName}" />
					<input type="hidden" id="actTypeQ" value="${merchantDetailInfo.actType}" />
					
					
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
								<td class="td-left" width="18%">商户编号：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly  maxlength=""  value="${merchantDetailInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" maxlength=""  value="${merchantDetailInfo.custName }" style="width:90%" >
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select  name="mecTypeFlag" id="mecTypeFlag" disabled="disabled" class="width-90" >
										<option value="00" <c:if test="${'00' == merchantDetailInfo.mecTypeFlag}">selected</c:if> >--普通商户--</option>
										<option value="01" <c:if test="${'01' == merchantDetailInfo.mecTypeFlag}">selected</c:if> >--连锁总店--</option>
										<option value="02" <c:if test="${'02' == merchantDetailInfo.mecTypeFlag}">selected</c:if> >--连锁分店--</option>
										<option value="03" <c:if test="${'03' == merchantDetailInfo.mecTypeFlag}">selected</c:if> >--1+n总店--</option>
										<option value="04" <c:if test="${'04' == merchantDetailInfo.mecTypeFlag}">selected</c:if> >--1+n分店--</option>
									</select>
									<label class="label-tips" id="mecTypeFlagLab"></label>	
								</td>
                                <td class="td-left">商户行业信息：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								<select name="industry" id="industry" disabled="disabled" class="selectpicker show-tick form-control" data-width="250px" data-live-search="true">
									<option value="">--请选择商户行业--</option>
									<c:if test="${not empty industryList }">
			                           	<c:forEach items="${industryList }" var="industry">                           		
			                           		<option id="${industry.industryCode}" value="${industry.industryCode}" <c:if test="${industry.industryCode == merchantDetailInfo.industryCode}">selected</c:if> >${industry.industryName}</option>
			                           	</c:forEach>
		                 		  	</c:if>
								</select>
								
								</td>
							</tr>
							<tr id="mecTypeFlagType" style = "display:none">
								<td class="td-left" width="18%">所属总店商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="parentMno"  name="parentMno" readonly="readonly" value="" style="width:90%">
								</td>
								<td class="td-left">分店是否独立结算：</td>
								<td class="td-right"> 
								    <select  name="independentModel" disabled="disabled" id="independentModel" class="width-90">
										<option value="">--结算方式--</option>
										<option value="01" <c:if test="${'01' == merchantDetailInfo.industryCode}">selected</c:if> > 独立结算</option>
										<option value="02"<c:if test="${'02' == merchantDetailInfo.industryCode}">selected</c:if> >统一结算</option>
									</select>
									<label class="label-tips" id="suiXingMerchantLab"></label>
								</td>
							</tr>
	                        <tr>
							    <td class="td-left">是否有证商户：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								    <select  name="suiXingMerchantType" id="suiXingMerchantType" disabled="disabled" class="width-90">
										<option value="01" <c:if test="${'01' == merchantDetailInfo.suiXingMerchantType}">selected</c:if> >有证商户</option>
										<option value="02" <c:if test="${'01' == merchantDetailInfo.suiXingMerchantType}">selected</c:if> >无证商户</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" disabled="disabled" name="merchantProvince" id="merchantProvince" >
	                                       	<option value="">--请选择省--</option>
		                                    <c:if test="${not empty merchantProvinceList }">
		                                        <c:forEach items="${merchantProvinceList }" var="merchantProvince">
		                                            <option id="${merchantProvince.areaId}"
		                                                    value="${merchantProvince.areaId}" <c:if test="${merchantProvince.areaId == merchantDetailInfo.merchantProvince}">selected</c:if>>
		                                                ${merchantProvince.areaName}
		                                            </option>
		                                        </c:forEach>
		                                    </c:if>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
		                                <select class="form-control" name="merchantCity" id="merchantCity" disabled="disabled" >
		                                    <option value="" id="cityDef">--请选择市--</option>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
		                                <select class="form-control" name="merchantArea" id="merchantArea" disabled="disabled" >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr" readonly="readonly" value="${merchantDetailInfo.cprRegAddr }" style="width:100%">
	                                </div>
								</td>
							</tr>	
						 	<tr id="businessPhotoType1" style = "display:">
								<td class="td-left">营业执照名称：</td>
								<td class="td-right">
									<input type="text" name="cprRegNmCn" id="cprRegNmCn" readonly="readonly"  value="" style="width:90%">
								</td>
								<td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
									<td class="td-right">
										<input type="text" readonly="readonly" name="businessEffectiveTerm" id="businessEffectiveTerm" value="" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
	                                    <input type="text" readonly="readonly" name="businessTerm" id="businessTerm" value="" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
	                                    <input type="button" onclick="businessForever()" value="长期" />
									</td>
							</tr>
							<tr id="businessPhotoType2" style = "display:">
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="registCode" id="registCode" readonly="readonly" value="${merchantDetailInfo.registCode }" style="width:90%">
								</td>
							</tr>
							<tr id="businessPhotoType" style = "display:">
								
								<td class="td-left">营业执照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bussinessPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
								
							</tr>
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									
									<a data-toggle='modal' class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.doorPhotoPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								
								</td>
							</tr>
							<tr id="shopCheckStandType" style = "display:">
								<td class="td-left">店内前台照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopCheckStandClick" data-target="#previewImageModal" >
										<label id="shopCheckStandDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" readonly="readonly" value="${merchantDetailInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" readonly="readonly" value="${merchantDetailInfo.mobileNo }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人证件类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" disabled="disabled" style="width:90%;"  >
										
										<option value="00" <c:if test="${'00' == merchantDetailInfo.representativeCertType}">selected</c:if> >--身份证--</option>
										<option value="03" <c:if test="${'03' == merchantDetailInfo.representativeCertType}">selected</c:if> >--军人证--</option>
										<option value="04" <c:if test="${'04' == merchantDetailInfo.representativeCertType}">selected</c:if> >--警察证--</option>
										<option value="05" <c:if test="${'05' == merchantDetailInfo.representativeCertType}">selected</c:if> >--港澳居民往来内地通行证--</option>
										<option value="06" <c:if test="${'06' == merchantDetailInfo.representativeCertType}">selected</c:if> >--台湾居民来往大陆通行证--</option>
										<option value="07" <c:if test="${'07' == merchantDetailInfo.representativeCertType}">selected</c:if> >--护照--</option>
										<option value="98" <c:if test="${'98' == merchantDetailInfo.representativeCertType}">selected</c:if> >--单位证件--</option>
										<option value="06" <c:if test="${'06' == merchantDetailInfo.representativeCertType}">selected</c:if> >--其他证件--</option>
									</select>
								</td>
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" value="${merchantDetailInfo.representativeCrtNo }" readonly="readonly" style="width:90%">
								</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证正面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
								
									<a data-toggle='modal' class="tooltip-success legalCertAttribute1Click" data-target="#previewImageModal" >
										<label id="legalCertAttribute1Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardOPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									
									<a data-toggle='modal' class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal" >
										<label id="legalCertAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardFPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="actNm" name="actNm" maxlength="100" readonly="readonly" value="${merchantDetailInfo.actNm }" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" readonly="readonly" value="${merchantDetailInfo.bankCardNo }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="bankProvince" id="bankProvince" disabled="disabled" class="width-90" >
	                                    <option value="">--请选择省--</option>
	                                    <c:if test="${not empty provinceList }">
	                                        <c:forEach items="${provinceList }" var="province">
	                                            <option id="${province.provinceId}"
	                                                    value="${province.provinceId}" <c:if test="${province.provinceId == merchantDetailInfo.bankProvince}">selected</c:if>>
	                                                ${province.provinceName}
	                                            </option>
	                                        </c:forEach>
	                                    </c:if>
	                                </select>
	                                <label id="bankProvinceLabel" class="label-tips"></label>
								</td>
								<td class="td-left">开户城市：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="bankCity" id="bankCity" class="width-90"  disabled="disabled" >
	                                    <option value="">--请选择市--</option>
	                                </select>
	                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								<select  name="suiXinBank" id="suiXinBank" disabled="disabled" class="selectpicker show-tick form-control" data-width="91%" data-live-search="true" onchange="getbranchBank();">
										<option value="">--请选择--</option>
										<c:forEach items="<%=SuixinBankType.values()%>" var="status">
											<option value="${status.code}" <c:if test="${status.code} == merchantDetailInfo.suiXinBank}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
								</td>
	                            <td class="td-left">开户支行<span style="color:red;">(必填)</span></td>
	                            <td class="td-right"> 
									<!-- <select name="interBankName" id="interBankName" class="width-90" >
	                                    <option value="">--请选择支行--</option>
	                                </select> -->
	                                  <div class="layui-input-inline layui-form" style="width:94%">
									         <select id="interBankName"  name="interBankName" disabled="disabled" lay-verify="required" lay-search="" lay-filter="interBankName">
									          <option value="">直接选择或搜索选择</option>        
									        </select>
									  </div>
	                               	<label id="interBankNameLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<select name="actType" id="actType" disabled="disabled" style="width-90"  >
										<option value="">--请选择--</option>
										<option value="00" <c:if test="${ merchantDetailInfo.actType == '00' }">selected</c:if> >--对公--</option>
										<option value="01" <c:if test="${ merchantDetailInfo.actType == '01'}" >selected</c:if>>--对私--</option>
									</select> 
								</td>
                                <td class="td-left">结算费率：<span style="color:red;">(必填)</span></td>
							  <td class="td-right"> 
									<input type="text" id="rate" name="rate" readonly="readonly" value="${ merchantDetailInfo.rate }" style="width:90%"> %
							  </td>
							</tr>
                            <tr>
                                <td class="td-left">账户人身份证号码：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
									<input type="text" id="certifyNo" name="certifyNo" readonly="readonly" value="${ merchantDetailInfo.certifyNo }" style="width:90%">
									<label id="certifyNoLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr id="letterOfAuthType" style = "display:">
								<td class="td-left">非法人结算授权函：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success letterOfAuthClick" data-target="#previewImageModal" >
										<label id="letterOfAuthDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.letterOfAuthPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								
								</td>
							</tr>
							<tr id="openPhotoType" style = "display:">
								<td class="td-left">开户许可证：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									
									<a data-toggle='modal' class="tooltip-success openAccountClick" data-target="#previewImageModal" >
										<label id="openAccountDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.openAccountPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="bankCardPhotoType" style = "display:">
				                <td class="td-left">银行卡正面照：<span style="color:red;">(必填)</span></td>
		                    	<td class="td-right" colspan="3">
									
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick" data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bankCardPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="settleIdCardType" style = "display:">
				                <td class="td-left"> 结算人身份证正面 <span style="color:red;">(必填)</span></td>
		                    	<td class="td-right" colspan="3">
								
									<a data-toggle='modal' class="tooltip-success settleCertAttribute1Click" data-target="#previewImageModal" >
										<label id="settleCertAttribute1Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.settleCertAttribute1Path }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									
								</td>
					         </tr>
					         <tr id="settleIdCardBackType" style = "display:">
				                <td class="td-left"> 结算人身份证反面 <span style="color:red;">(必填)</span></td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success settleCertAttribute2Click" data-target="#previewImageModal" >
										<label id="settleCertAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.settleCertAttribute2Path }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							 </tr>		
							 <!-- 新增 zhanggc -->
							  <tr id="" style = "display:">
				                <td class="td-left">签约二维码<span style="color:red;">(必填)</span></td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success settleCertAttribute2Click" data-target="#previewImageModal" >
										<label id="settleCertAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${merchantDetailInfo.signQrcode }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							 </tr>	
							 
							  <tr>
								<td class="td-left" width="18%">微信申请单编号：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="wxApplyNo" name="wxApplyNo" readonly  maxlength=""  value="${tdMerchantDetailInfo.wxApplyNo }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">微信实名认证状态：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="idenStatus" name="idenStatus" readonly="readonly" maxlength=""  value="${tdMerchantDetailInfo.idenStatus }" style="width:90%">
								</td>
							  </tr>
							 
							  <tr>
								<td class="td-left" width="18%">渠道微信子商户号：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="wxChildNo" name="wxChildNo" readonly  maxlength=""  value="${tdMerchantDetailInfo.wxChildNo }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">渠道支付宝子商户号：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="zfbChildNo" name="zfbChildNo" readonly="readonly" maxlength=""  value="${tdMerchantDetailInfo.zfbChildNo }" style="width:90%">
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

			var mecTypeFlag = $("#mecTypeFlagQ").val()
			if("02" == mecTypeFlag || "04" == mecTypeFlag){
				$("#mecTypeFlagType").attr("style","display:");
			}else{
				$("#mecTypeFlagType").attr("style","display:none");
			}

			var suiXingMerchantType = $("#suiXingMerchantTypeQ").val();
			
			//是否有证商户回显
			//有证商户 
			if("01" == suiXingMerchantType){
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:");
				$("#businessPhotoType1").attr("style","display:");
				$("#businessPhotoType2").attr("style","display:");
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
			}else if("02" == suiXingMerchantType){
				$("#legalIdCardType").attr("style","display:none");
				$("#legalIdCardBackType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:none");
				$("#businessPhotoType1").attr("style","display:none");
				$("#businessPhotoType2").attr("style","display:none");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				
			}
			
			/***获取商户注册所在地区城市***/
	      		var merchantProvinceId = $("#merchantProvinceQ").val().trim();
	      		var merchantCity = $("#merchantCityQ").val().trim();
	      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
	    		{
	    			"superiorAreaCode":merchantProvinceId
	    		},
	    		function(data){
	    			if(data.result=="SUCCESS"){
	    				var areaList = data.areaList;
	    				$("#merchantCity").html("");
	    				
	           			for ( var area in areaList) {
	           				var aa = "<option value='"+ areaList[area].areaId +"'";
	           				
	           				if ( areaList[area].areaId == merchantCity) 
	      					  {
	      						 aa += "selected";
	      					  }	       				
	           					aa += ">"+ areaList[area].areaName + "</option>";
	           				$("#merchantCity").append(aa);
	           			}
	           			getMerchantArea();;
	    			}
	    		},'json'
	    		);	
	   
	      		/***获取商户注册所在地区县区***/
	      			var merchantArea = $("#merchantAreaQ").val().trim();
	          		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
	          	    		{
	          	    			"superiorAreaCode":merchantCity
	          	    		},
	          	    		function(data){
	          	    			if(data.result=="SUCCESS"){
	          	    				var areaList = data.areaList;
	          	    				$("#merchantArea").html("");
	          	           			for ( var area in areaList) {
	          	           				var aa = "<option value='"+ areaList[area].areaId +"'";
	    	           				
	    	           					if ( areaList[area].areaId == merchantArea) 
	    	      						  {
	    	      							 aa += "selected";
	    	      						  }	       				
	    	           					 aa += ">"+ areaList[area].areaName + "</option>";
	    	           					 $("#merchantArea").append(aa);
	          	           				
	          	           			}
	          	    			}
	          	    		},'json'
	          	    		);	

			
			
	          		/***获取银行所在地区***/
	              
	              		var province = $("#bankProvinceQ").val().trim();
	              		var bankCity = $("#bankCityQ").val().trim();
	              		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGCITY %>",
	            		{
	            			"province":province
	            		},
	            		function(data){
	            			if(data.result=="SUCCESS"){
	            				var cityList = data.cityList;
	            				$("#bankCity").html("");
	                   			for ( var city in cityList) {
	                   				
	                   				var aa ="<option value='"+ cityList[city].cityId  +"'";
	                				if ( cityList[city].cityId == bankCity) 
	                				  {
	                					 aa+="selected";
	                				  }
	                				aa += ">" +cityList[city].cityName + "</option>";
	                				$("#bankCity").append(aa)
	                   			/* 	$("#bankCity").append(
	                   						"<option value='"+ cityList[city].cityId +"'>"
	                   								+ cityList[city].cityName + "</option>");  */
	                   			}
	            			}
	            		},'json'
	            		);	
	        
	             
	          	      //开户支行
	                  		var suiXinBank = $("#suiXinBankQ").val();
	                  		
	                  		var interBankNameQ = $("#interBankNameQ").val();
	                  		var channelCode ="SUIXING_PAY";
	                  		$.post(window.Constants.ContextPath +"/common/info/bankCnapsInfo",
	                		{
	                  			"cityCode":bankCity,
	                			"bankCode": suiXinBank,
	                			"channelCode":channelCode
	                		},
	                		function(data){
	                			if(data.result=="SUCCESS"){
	                				var branchBankList = data.branchBankList;
	                				$("#interBankName").html("");
	                       			for ( var branchBank in branchBankList) {
	                       				
	                       				var aa ="<option value='"+ branchBankList[branchBank].branchBankCode  +"'";
	                    				if ( branchBankList[branchBank].branchBankCode == interBankNameQ) 
	                    				  {
	                    					 aa+="selected";
	                    				  }
	                    				aa += ">" + branchBankList[branchBank].bankName + "</option>";
	                       				
	                    				$("#interBankName").append(aa);
	                       			/* 	$("#interBankName").append(
	                       						"<option value='"+ branchBankList[branchBank].branchBankCode +"'>"
	                       								+ branchBankList[branchBank].bankName + "</option>"); */ 
	                       			}
	                       			layui.use('form', function (){
	                       				var form = layui.form; 
	                       				
	                       				form.render();
	                       			 });  
	                			}
	                			
	                		},'json'
	                		);	
	               
	                  
	              		
	          		
		function businessForever(){
			$("input[name='businessTerm']").val("2099-12-31");
			$("#businessTerm").attr("value","2099-12-31");
		}
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
        
      
		
      
      
      	
      	
      	/***获取开户支行***/
      	function getInterBank(){
      		var bank = $("#interBank").val().trim();
      		
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELINTERBANK %>",
    		{
    			"bank":bank
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var interBankList = data.interBankList;
    				$("#interBank_").html("");
    				$("#interBank_").append("<option value=''>--请选择--</option>");
           			for ( var interBank in interBankList) {
           				$("#interBank_").append(
           						"<option value='"+ interBankList[interBank].bankBranchId +"'>"
           								+ interBankList[interBank].bankBranchName + "</option>");
           			}
    			}
    		},'json'
    		);	
      	}
      	
      	
 
      		var actType = $("#actTypeQ").val();
      		//对公
			if("00" == actType){
				$("#settleIdCardType").attr("style","display:none");
				$("#settleIdCardBackType").attr("style","display:none");
				$("#openPhotoType").attr("style","display:");
				$("#bankCardPhotoType").attr("style","display:none");
				$("#letterOfAuthType").attr("style","display:none");
			}else if("01" == actType){
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				$("#openPhotoType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				$("#letterOfAuthType").attr("style","display:");
			}


      
      	/***获取开户支行***/
      	function setInterBank(){
      		var interBankName = $("#interBank_").find("option:selected").text().trim();
      		var interBank = $("#interBank_").val();
      		$("#InterBankCode").val(interBank);
      		$("#interBankName").val(interBankName);
      	}


      
      	
      
      	
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
	   	
	   	function exit() {
	   		if (confirm("您确定要关闭吗？")) {
	   			window.opener=null;
	   		
	   			window.open("","_self");
	   		
	   			window.close();
	   		}
	   	};
    	
  
    	
</script>

<script>
//页面加载时重新加载专用js脚本
//页面加载时重新加载一下输入下拉框
	layui.use('form', function (){
		var form = layui.form; 
		
		form.render();
	 });  
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');
 
  //自定义验证规则
 form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  }); 
  
  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });
 
 
  
  //表单取值
  layui.$('#LAY-component-form-getval').on('click', function(){
    var data = form.val('example');
    alert(JSON.stringify(data));
  }); 
  
});
</script>
</body>
</html>