<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.YqbMerchantType"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<%@ include file="/include/template.jsp"%>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.min.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>平安付进件</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
<!-- 用户信息 -->
<%@ include file="/include/top.jsp"%>

<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner">
		<%-- <!-- 菜单 -->
		<%@ include file="/include/left.jsp"%> --%>
		
		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>
			
			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="YQB"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="qualificationtemp" />
					<input type="hidden" id="signaturetemp" />
					<input type="hidden" id="opentemp"/>
					<input type="hidden" id="bankCardPhototemp"/>
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    	<form  id="merchantForm" action='<c:url value="<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.YQBMERCHANTREPORT %>"/>' method="post">
					            <table id="merchant_table" class="list-table">
								<tbody>
                            	<tr>
									<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
								</tr>
						   		<tr></tr>
                            	<tr>
									<td class="td-left" width="18%">商户编号：<span style="color:red;">(必填)</span></td>
									<td class="td-right" width="32%"> 
										<input type="text" id="merchantCode" name="merchantCode" readonly maxlength=""  value="${custInfo.merchantCode }" style="width:90%">
										<label class="label-tips" id="merchantCodeLab"></label>
									</td>
	                                <td class="td-left" width="18%">商户简称：<span style="color:red;">(必填)</span></td>
									<td class="td-right" width="32%"> 
										<input type="text" id="shortName" name="shortName" placeholder="请输入商户简称" maxlength=""  value="${custInfo.shortName }" style="width:90%">
										<label class="label-tips" id="shortNameLab"></label>
									</td>
								</tr>
		                        <tr>
									<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
								</tr>
								<tr id="merchantType1" style = "display:">
									<td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span></td>
									<td class="td-right" width="32%"> 
										<input type="text" id="custName" name="custName" placeholder="请输入商户名称" maxlength=""  value="${custInfo.custName }" style="width:90%">
										<label class="label-tips" id="custNameLab"></label>
									</td>
								    <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
									<td class="td-right"> 
									   <select name="merchantType" id="merchantType" style="width:250px;" onchange="getType();" >
											<option value="">--请选择--</option>
											<c:forEach items="<%=YqbMerchantType.MerchantType.values()%>" var="status">
												<option value="${status.value}" <c:if test="${status == queryBean.merchantType}">selected</c:if>>
													${status.text}
												</option>
											</c:forEach>
										</select>	
										<label class="label-tips" id="merchantTypeLab"></label>
									</td>
								</tr>
								<tr id="industryType" style = "display:">
	                                <td class="td-left">商户行业信息:<span style="color:red;">(必填)</span></td>
									<td class="td-right"> 
		                                <select name="levelOne" id="levelOne" class="width-80" onchange="getLevel();">
		                                    <option value="">--请选择商户行业--</option>
											<c:if test="${not empty industryList }">
					                           	<c:forEach items="${industryList }" var="industry">
					                           		<option id="${industry.levelOne}" value="${industry.levelOne}">${industry.levelOne}</option>
					                           	</c:forEach>
				                 		  	</c:if>
		                                </select>
		                             </td>
		                             <td class="td-left">行业信息:<span style="color:red;">(必填)</span></td>
		                             <td class="td-right">
		                                <label id="levelOneLabel" class="label-tips"></label>
		                                <select name="levelTwo" id="levelTwo" class="width-80" >
		                                    <option value="">--请选择行业--</option>
		                                </select>
		                               	<label id="levelTwoLabel" class="label-tips"></label>
									</td>
								</tr>
		                        <tr>
								    <td class="td-left">营业执照：<span style="color:red;">(必填)</span></td>
									<td class="td-right"> 
										<input type="text" id="businessLicense" name="businessLicense" placeholder="请输入营业执照" maxlength=""  value="${custInfo.businessLicense }" style="width:90%">
										<label id="businessLicenseLab" class="label-tips"></label>	
									</td>
	                                <td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
									<td class="td-right">
										<input type="text" name="businessEffectiveTerm" id="businessEffectiveTerm" value="${custInfo.businessTermStart }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
	                                    <input type="text" name="businessTerm" id="businessTerm" value="${custInfo.businessTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
	                                    <input type="button" onclick="businessForever()" value="长期" />
									</td>
								</tr>
                        		<tr id="establishDateType" style = "display:">
								    <td class="td-left">成立时间：<span style="color:red;">(必填)</span></td>
									<td class="td-right"> 
										 <input type="text" name="establishDate" id="establishDate" placeholder="日期选择" onfocus="WdatePicker({skin:'whyGreen'})"  style="width:90%;background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" >
										 <label id="establishDateLab" class="label-tips"></label>
									</td>
								</tr>
								<tr id="addressType" style = "display:">
									<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
									<td class="td-right" colspan="3">
										<div class="col-xs-2 pd0" style="padding:0;">
											<select name="province" id="province" class="form-control" onchange="getCity();">
			                                    <option value="">--请选择省--</option>
			                                    <c:if test="${not empty provinceList }">
			                                        <c:forEach items="${provinceList }" var="province">
			                                            <option id="${province.areaCode}"
			                                                    value="${province.areaCode}">
			                                                ${province.provinceName}
			                                            </option>
			                                        </c:forEach>
			                                    </c:if>
			                                </select>
			                                <label id="provinceLabel" class="label-tips"></label>
		                                </div>
		                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
			                                <select name="city" id="city" class="form-control" onchange="getArea();">
			                                    <option value="">--请选择市--</option>
			                                </select>
			                               	<label id="cityLabel" class="label-tips"></label>
		                                </div>
		                                <div class="col-xs-2 pd0" style="padding:0;">
		                                	<select name="area" id="area" class="form-control">
			                                    <option value="">--请选择区--</option>
			                                </select>
			                                <label id="areaLabel" class="label-tips"></label>
		                                </div>
	                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
		                                    <input type="text"  style="width:100%;" name="cprRegAddr" id="cprRegAddr"  value="${custInfo.custAdd }" placeholder="请输入详细地址"  required/>
					                    	<label class="label-tips" id="cprRegAddrLab"></label>
		                                </div>
									</td>
								</tr>	
							<tr id="businessPhotoType" style = "display:">
								<td class="td-left">营业执照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal"  >
										<label id="businessPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="businessPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" />
										<input type="hidden" id="businessPhotoImageVal02"  />  
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhoto(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
                            <tr id="qualificationType" style = "display:">
								<td class="td-left">特殊行业资质照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success qualificationClick"  data-target="#previewImageModal"  >
										<label id="qualificationDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="qualificationImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="qualificationPath" name="qualificationPath" />
										<input type="hidden" id="qualificationImageVal02"  />  
										<input type="file" name="qualification" id="qualification" onchange="showQualification(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal"  >
										<label id="doorPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="doorPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="doorPhotoPath" name="doorPhotoPath" />
										<input type="hidden" id="doorPhotoImageVal02"  />  
										<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhoto(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
                            <tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick"  data-target="#previewImageModal"  >
										<label id="shopInteriorDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="shopInteriorImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="shopInteriorPath" name="shopInteriorPath" />
										<input type="hidden" id="shopInteriorImageVal02"  />  
										<input type="file" name="shopInterior" id="shopInterior" onchange="showShopInterior(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
                            <tr id="signatureType" style = "display:">
								<td class="td-left">电子签名照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success signatureClick"  data-target="#previewImageModal"  >
										<label id="signatureDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="signatureImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="signaturePath" name="signaturePath" />
										<input type="hidden" id="signatureImageVal02"  />  
										<input type="file" name="signature" id="signature" onchange="showSignature(this)"/>
										<span style="color:gray">支持*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="otherMaterialType" style = "display:">
								<td class="td-left">其他材料照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success otherMaterialClick"  data-target="#previewImageModal"  >
										<label id="otherMaterialDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="otherMaterialImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="otherMaterialPath" name="otherMaterialPath" />
										<input type="hidden" id="otherMaterialImageVal02"  />  
										<input type="file" name="otherMaterial" id="otherMaterial" onchange="showOtherMaterial(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="InterName" name="InterName" placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="50" style="width:90%">
									<label id="interNameLab" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="certifyNo" id="certifyNo" placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
									<label id="certifyNoLab" class="label-tips"></label>
								</td>
								<td class="td-left">法人身份证有效期：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<input type="text" name="identityEffDate" id="identityEffDate" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
                                    <input type="text" name="identityValDate" id="identityValDate" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
                                    <input type="button" onclick="identityForever()" value="长期" />
								</td>
							</tr>
							<tr id="idCardType" style = "display:">
								<td class="td-left">法人身份证正面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal"  >
										<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										    <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="certAttribute1FilePath" name="certAttribute1FilePath" />
										<input type="hidden" id="certAttribute1Val02"  />  
										<input type="file" name="certAttribute1" id="certAttribute1" onchange="showCertAttribute1(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="idCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
										<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="certAttribute2FilePath" name="certAttribute2FilePath" />
										<input type="hidden" id="certAttribute2Val02"  />  
										<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">银行账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="accountNm" name="accountNm" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
								</td>
                                  <td class="td-left">银行卡号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="accountNo" name="accountNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width:90%">
								</td>
							</tr>
						   	<tr>
								<td class="td-left">开户支行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									  <input type="text" id="interBankName" name="interBankName" maxlength="100" placeholder="请输入开户支行"  value="${custInfo.branchBANK }" style="width:90%">
									  <label id="interBankNameLabel" class="label-tips"></label>
								</td>
								<td class="td-left">银行预留号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									  <input type="text" id="mobile" name="mobile" maxlength="100" placeholder="请输入银行预留号码"  value="${custInfo.merchantMobile }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">银行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								  	<select name="bank" id="bank" class="col-xs-11 aui-form-control-two" >
	                                    <option value="">--请选择银行--</option>
	                                    <c:if test="${not empty bankIdList }">
	                                        <c:forEach items="${bankIdList }" var="bank">
	                                            <option id="${bank.bankId}" value="${bank.bankId}">${bank.bankName}</option>
	                                        </c:forEach>
	                                    </c:if>
	                                </select>
								</td>
								<td class="td-left">开户联行号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									  <input type="text" id="bankCode" name="bankCode" maxlength="100" placeholder="请输入联行号"  value="" style="width:90%">
									  <label id="bankCodeLabel" class="label-tips"></label>
								</td>
							</tr>
                          	<tr>
								<td class="td-left">结算费率：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									  <input type="text" id="rate" name="rate" maxlength="100" placeholder="请输入结算费率"  value="" style="width:90%"> %
									  <label id="rateLab" class="label-tips"></label>
								</td>
							</tr>
							<tr id="openType" style = "display:">
								<td class="td-left">开户许可证照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success openClick"  data-target="#previewImageModal"  >
										<label id="openDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="openImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="openPath" name="openPath" />
										<input type="hidden" id="openImageVal02"  />  
										<input type="file" name="open" id="open" onchange="showOpen(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="bankCardPhotoType" style = "display:none">
								<td class="td-left">银行卡正面照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="bankCardPhotoPath" name="bankCardPhotoPath" />
										<input type="hidden" id="bankCardPhotoImageVal02" name="bankCardPhoto"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showBankCardPhoto(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
	                        <tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">管理员信息</td>
							</tr>
	                        <tr>
								<td class="td-left">管理员名字：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="attentionName" name="attentionName" maxlength="100" placeholder="请输入管理员名字"  value="${custInfo.contactName }" style="width:90%">
									<label id="attentionNameLab" class="label-tips"></label>
								</td>
                                  <td class="td-left">管理员手机：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="attentionMobile" name="attentionMobile" maxlength="100" placeholder="请输入管理员手机"  value="${custInfo.contactMobile }" style="width:90%">
									<label id="attentionMobileLab" class="label-tips"></label>
								</td>
							</tr>
						</tbody>
						</table>
					    </form>
					    </div>
					    <div style="margin:50px 0 0 0;text-align:center">
					    	<button type="button"  class="btn btn-primary" id='submitData'>提交报备</button> 
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

		//查询银行控件
		$(function(){
			$("#bank").comboSelect();
		})
		
		function businessForever(){
		    /* $("input[name='businessTerm']").val("2099-12-31").focus(); */
		    $("#businessTerm").attr("value","长期");
		}
		function identityForever(){
		    /* $("input[name='businessTerm']").val("2099-12-31").focus(); */
		    $("#identityValDate").attr("value","长期");
		}
		
		
		function exit() {
	   		if (confirm("您确定要关闭吗？")) {
	   			window.opener=null;
	   		
	   			window.open("","_self");
	   		
	   			window.close();
	   		}
	   	};
		
		function getType(){
			var merchantType = $("#merchantType").val();
			if("12" == merchantType){//12-个人
				$("#otherMaterialType").attr("style","display:");
				$("#mobileType").attr("style","display:");
				$("#bankCardPhotoType").attr("style","display:");
				$("#openType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:none");
			}else if("11" == merchantType){//11-个体工商户
				$("#mobileType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				$("#openType").attr("style","display:none");
				$("#otherMaterialType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:");
			} 
			else{//10-企业
				$("#mobileType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:none");
				$("#openType").attr("style","display:");
				$("#otherMaterialType").attr("style","display:");
				$("#businessPhotoType").attr("style","display:");
			}
			
		}

		
		function showBusinessPhoto(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#businessPhoto').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#businessPhotoPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('businessPhotoDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showCertAttribute1(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#certAttribute1').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#certAttribute1FilePath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });
		    
		    var prevDiv = document.getElementById('certAttribute1Div');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showCertAttribute2(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#certAttribute2').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#certAttribute2FilePath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('certAttribute2Div');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showDoorPhoto(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#doorPhoto').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#doorPhotoPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('doorPhotoDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		} 

		function showShopInterior(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#shopInterior').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#shopInteriorPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('shopInteriorDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showQualification(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#qualification').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#qualificationPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('qualificationDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showOtherMaterial(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#otherMaterial').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#otherMaterialPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('otherMaterialDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showSignature(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#signature').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#signaturePath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('signatureDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showOpen(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#open').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#openPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('openDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
		}

		function showBankCardPhoto(file){
		    var formdata=new FormData();
		    formdata.append("file",$('#bankCardPhoto').get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#bankCardPhotoPath').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });

		    var prevDiv = document.getElementById('bankCardPhotoDiv');
	        if (file.files && file.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (evt) {
	                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
	            }
	            reader.readAsDataURL(file.files[0]);
	        } else {
	            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	        }
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
        
      	/***获取市***/
      	function getCity(){
      		var areaCode = $("#province").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.YQBSELCITY %>",
    		{
    			"areaCode":areaCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var yqbAreaList = data.yqbAreaList;
    				$("#city").html("");
           			for ( var yqbArea in yqbAreaList) {
           				$("#city").append(
           						"<option value='"+ yqbAreaList[yqbArea].areaCode +"'>"
           								+ yqbAreaList[yqbArea].cityName + "</option>"); 
           			}
           			getArea();
    			}
    		},'json'
    		);	
      	}
      	
      	/***获取区县***/
      	function getArea(){
      		var areaCode = $("#city").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.YQBSELAREA %>",
    		{
        		"areaCode" : areaCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var yqbAreaList = data.yqbAreaList;
    				$("#area").html("");
           			for ( var yqbArea in yqbAreaList) {
           				$("#area").append(
           						"<option value='"+ yqbAreaList[yqbArea].areaCode +"'>"
           								+ yqbAreaList[yqbArea].areaName + "</option>");
           			}
    			}
    		},'json'
    		);	
      	}

      	/***获取行业信息***/
      	function getLevel(){
      		var levelOne = $("#levelOne").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.YQBSELINDUSTRY %>",
    		{
    			"levelOne":levelOne
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var yqbIndustryList = data.yqbIndustryList;
    				$("#levelTwo").html("");
           			for ( var yqbIndustry in yqbIndustryList) {
           				$("#levelTwo").append(
           						"<option value='"+ yqbIndustryList[yqbIndustry].levelCode +"'>"
           								+ yqbIndustryList[yqbIndustry].levelTwo + "</option>"); 
           			}
    			}
    		},'json'
    		);	
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

      	/***获取开户支行***/
      	function setInterBank(){
      		var interBankName = $("#interBank_").find("option:selected").text().trim();
      		var interBank = $("#interBank_").val();
      		$("#InterBankCode").val(interBank);
      		$("#interBankName").val(interBankName);
      	}

      	/** 照片点击预览 **/
      	$('.shopInteriorClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("shopInteriorClick");
      		return previewImage(divObj,imageObj,obj); 
      	});


        //图片预览
        function showSignatureImage(obj){  
    		 var divObj = document.getElementById("signatureDiv");  
    		 var imageObj = document.getElementById("signatureImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}

    	function showOtherMaterialImage(obj){  
   		 var divObj = document.getElementById("otherMaterialDiv");  
   		 var imageObj = document.getElementById("otherMaterialImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   		}
       	
        function showQualificationImage(obj){  
    		 var divObj = document.getElementById("qualificationDiv");  
    		 var imageObj = document.getElementById("qualificationImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}
    	
        function showShopInteriorImage(obj){  
    		 var divObj = document.getElementById("shopInteriorDiv");  
    		 var imageObj = document.getElementById("shopInteriorImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}
        
        function showDoorPhotoImage(obj){  
    		 var divObj = document.getElementById("doorPhotoDiv");  
    		 var imageObj = document.getElementById("doorPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}
    	
    	function showBusinessPhotoImage(obj){  
    		 var divObj = document.getElementById("businessPhotoDiv");  
    		 var imageObj = document.getElementById("businessPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}

    	function showOpenImage(obj){  
   			 var divObj = document.getElementById("openDiv");  
   			 var imageObj = document.getElementById("openImageDiv");  
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

    	
      	/* 校验渠道 */
    $(function(){

		//判定是新进件还是更新进件
		var status = $("#status").val();
		var custId = $("#custId").val();
		var authId = $("#authId").val();
		if("" ==custId){
			alert(status);
		}else{
	    	$("#businessPhotoImageDiv").show();
	    	$("#certAttribute1ImageDiv").show();
	    	$("#certAttribute2ImageDiv").show();
	    	$("#doorPhotoImageDiv").show();
	    	$("#openImageDiv").show();
	    	$("#shopInteriorImageDiv").show();
	    	$("#qualificationImageDiv").show();
	    	$("#otherMaterialImageDiv").show();
	    	$("#signatureImageDiv").show();
	    	$("#otherMaterialImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=06&authId="+authId);
	    	$("#signatureImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=12&authId="+authId);
	    	$("#qualificationImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=11&authId="+authId);
	    	$("#shopInteriorImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=18&authId="+authId);
	    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
	    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#openImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);    
	    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
		}
   		$("#submitData").click(function(){
   	   		
   			var channelNo = $("#channlCode").val();
   			var merchantCode = $("#merchantCode").val();
   			var merchantCodeType = $("#merchantType").val();
   			var custName = $("#custName").val();
   			var shortName = $("#shortName").val();
   			var businessLicense = $("#businessLicense").val();//营业执照号
   			var interName = $("#InterName").val();
   			var businessEffectiveTerm = $("#businessEffectiveTerm").val();//营业起始时间
			var businessTerm = $("#businessTerm").val();//营业终止时间
			var rate = $("#rate").val();
			var establishDate = $("#establishDate").val();
			var certifyNo = $("#certifyNo").val();
			var identityEffDate = $("#identityEffDate").val();
			var identityValDate = $("#identityValDate").val();
			var industryCode = $("#levelTwo").val();
			var province = $("#province").val(); 
			var city = $("#city").val(); 
			var area = $("#area").val();
   			var cprRegAddr = $("#cprRegAddr").val();//商户详细地址
   			var accountNm = $("#accountNm").val();  
			var accountNo = $("#accountNo").val(); 
			var interBankName = $("#interBankName").val();
			var bank = $("#bank").val(); 
			var bankCode = $("#bankCode").val();
			var mobile = $("#mobile").val();
			var attentionName = $("#attentionName").val();
   			var attentionMobile = $("#attentionMobile").val();
			var businessPhotoPath = $("#businessPhotoPath").val();
			var certAttribute1FilePath = $("#certAttribute1FilePath").val();
			var certAttribute2FilePath = $("#certAttribute2FilePath").val();
			var doorPhotoPath = $("#doorPhotoPath").val();
			var shopInteriorPath = $("#shopInteriorPath").val();
   			var qualificationPath = $("#qualificationPath").val();
			var otherMaterialPath = $("#otherMaterialPath").val();
			var signaturePath = $("#signaturePath").val();
			var openPath = $("#openPath").val();
			
			var bankCardPhotoPath = $("#bankCardPhotoPath").val();
   			
   			//平安付支付渠道
   			if("YQB" == channelNo){
   		    	//上传照片
   				var imgDoor = [];
   				var imgSrc = [];
   				$("#door_temp input[type='hidden']").each(function(){
   					if($(this).attr('id').indexOf('Src')>=0){
   						imgSrc.push($(this).attr('id')+"="+$(this).val());
   					}else{
   						if($(this).val()==""){
   							imgDoor.push($(this).attr('id')+"="+"无");
   						}else{
   							imgDoor.push($(this).attr('id')+"="+$(this).val());
   						}
   					}
   				});
				//未报备
  				if("unReported" == status || "" ==status ){

  					if("" == merchantCode){
  	   	   	    		$("#merchantCodeLab").text("商户编号不能为空");
  	   	   	    		$("#merchantCode").focus();
  	   	   	    		return false;
  	   	   	    	}else{
  	   	   	    		$("#merchantCodeLab").text('');
  	   	   	    	}
  					if("" == merchantType){
  	   	   	    		$("#merchantTypeLab").text("商户类型不能为空");
  	   	   	    		$("#merchantType").focus();
  	   	   	    		return false;
  	   	   	    	}else{
  	   	   	    		$("#merchantTypeLab").text('');
  	   	   	    	}
		  	   	   	if("" == custName){
	 	   	    		$("#custNameLab").text("商户名不能为空");
	 	   	    		$("#custName").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#custNameLab").text('');
	 	   	    	}
			  	   	if("" == businessLicense){
	 	   	    		$("#businessLicenseLab").text("营业执照号不能为空");
	 	   	    		$("#businessLicense").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#businessLicenseLab").text('');
	 	   	    	}
			  	   	
			  	  if("" == businessLicense){
	 	   	    		$("#businessLicenseLab").text("营业执照号不能为空");
	 	   	    		$("#businessLicense").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#businessLicenseLab").text('');
	 	   	    	}
			  	   	
			  	  	if("" == interName){
	   		    		$("#interNameLab").text("法人姓名不能为空");
	   		    		$("#InterName").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interNameLab").text("");
	   		    	}
			  	  	//营业执照时间单选是否选中
			  	 	//alert(businessTerm);
			  	  	if("" == businessEffectiveTerm || "" == businessTerm){
	   		    		$("#businessEffectiveTermLab").text("营业执照时间不能为空");
	   		    		$("#businessEffectiveTerm").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#businessEffectiveTermLab").text("");
	   		    	}
			  	  	
			  	    if("" == rate){
	   		    		$("#rateLab").text("费率不能为空");
	   		    		$("#rate").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#rateLab").text("");
	   		    	}
			  	    
			  		
	   		    	if("" == establishDate){
	   		    		$("#establishDateLab").text("成立时间不能为空");
	   		    		$("#establishDate").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#establishDateLab").text("");
	   		    	}
	   		    	
	   		    	
	   		    	if("" == certifyNo){
	   		    		$("#certifyNoLab").text("身份证件号不能为空");
	   		    		$("#certifyNo").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#certifyNoLab").text("");
	   		    	}
	   		    	
	   		   		//身份证时间单选是否选中
			  	 	//alert(identityValDate);
	   		    	if("" == identityEffDate || "" == identityValDate){
	   		    		$("#identityEffDateLab").text("法人身份证有效时间不能为空");
	   		    		$("#identityEffDate").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#identityEffDateLab").text("");
	   		    	}
	   		    	if("" == levelTwo){
	   		    		$("#levelTwoLabel").text("行业信息不能为空");
	   		    		$("#levelTwo").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#levelTwoLabel").text("");
	   		    	}
	   		    	if("" == province){
	   	   	    		$("#provinceLabel").text("报备省份不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#provinceLabel").text("");
	   	   	    	}
	   	   	    	
	   	   			if("" == city){
	   	   				$("#cityLabel").text("报备市不能为空");
	   	   				return false;
	   	   			}else{
	   	   	    		$("#cityLabel").text("");
	   	   	    	}
	   	   			
	   	   			if("" == area){
	   	   				$("#areaLabel").text("报备区不能为空");
	   	   				return false;
	   	   			}else{
	   	   	    		$("#areaLabel").text("");
	   	   	    	} 
			  	    if("" == cprRegAddr){
	   		    		$("#cprRegAddrLab").text("详细地址不能为空");
	   		    		$("#cprRegAddr").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#cprRegAddrLab").text("");
	   		    	}
			  	  	if("" == accountNm){
	 	   	    		$("#accountNmLab").text("结算账户名称不能为空");
	 	   	    		$("#accountNm").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#accountNmLab").text('');
	 	   	    	}
			 	    if("" == accountNo){
	 	   	    		$("#accountNoLab").text("结算账户不能为空");
	 	   	    		$("#accountNo").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#accountNoLab").text('');
	 	   	    	}
			 	   if("" == interBankName){
	   		    		$("#interBankNameLabel").text("开户银行名称不能为空");
	   		    		$("#interBankName").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interBankNameLabel").text("");
	   		    	}
	   		    	if("" == bank){
	   	   				$("#bankLabel").text("开户行不能为空");
	   	   				return false;
	   	   			}else{
	   	   	    		$("#bankLabel").text("");
	   	   	    	}
		   	   	    if("" == bankCode){
	 		    		$("#bankCodeLabel").text("开户行联行号不能为空");
	 		    		$("#bankCode").focus();
	 		    		return false;
	 		    	}else{
	 		    		$("#bankCodeLabel").text("");
	 		    	}
			   	   	/* if("" == mobile){
	 	   	    		$("#mobileLab").text("手机号不能为空");
	 	   	    		$("#mobile").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#mobileLab").text('');
	 	   	    	} */
			   	    if("" == attentionName){
	 	   	    		$("#attentionNameLab").text("管理员名称不能为空");
	 	   	    		$("#attentionName").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#attentionNameLab").text('');
	 	   	    	}
	   		    	if("" == attentionMobile){
	 	   	    		$("#attentionMobileLab").text("管理员手机号不能为空");
	 	   	    		$("#attentionMobile").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#attentionMobileLab").text('');
	 	   	    	}

			 	   
  	   				$.ajax({
  	   					type : "POST",
  	   					url : window.Constants.ContextPath +'<%="/common/files/getPicPath"%>?custId='+custId,
  	   					data :{
  	   						
  	   						bussinessPath : $('#businessPhotoPath').val(),//营业执照
  	   				    	qualificationPath : $('#qualificationPath').val(),//11特殊行业资质照
  	   				 		doorPhotoPath : $('#doorPhotoPath').val(),//08门头照
  	   				 		shopInteriorPath : $('#shopInteriorPath').val(),//18店内照
  	   				 		signaturePath : $('#signaturePath').val(),//12电子签名照
  	   				 		otherMaterialPath : $('#otherMaterialPath').val(),//特殊行业资质照
  	   				 		idCardOPath : $('#certAttribute1FilePath').val(),//04 个人身份证正面
  	   				 		idCardFPath : $('#certAttribute2FilePath').val(),//16 个人身份证反面
  	   						openAccountPath : $('#openPath').val(), //开户许可证照片
  	   					
  	   						bankCardPath : $('#bankCardPhotoPath').val()//开户银行卡正面照
  	   				 		
  	   					},
  	   					dataType : "json",
  	   					success : function(data) {
  	   						if(data.result=='SUCCESS')							
  	   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.YQBINFOMERCHANTREPORT %>",
  	   							{																							 
  	   							"channelNo" : channelNo,
  								"merchantCode" : merchantCode,
  								"merchantCodeType" : merchantCodeType,
  								"custName" : custName,
  								"shortName" : shortName,
  								"businessLicense" : businessLicense,
  								"interName" : interName,
  								"businessEffectiveTerm" : businessEffectiveTerm,
  								"businessTerm" : businessTerm,
  								"rate" : rate,
  								"establishDate" : establishDate,
  								"certifyNo" : certifyNo,
  								"identityEffDate" : identityEffDate,
  								"identityValDate" : identityValDate,
  								"industryCode" : industryCode,
  								"province" : province,
  								"city" : city,
  								"area" : area,
  								"cprRegAddr" : cprRegAddr,
  								"accountNm" : accountNm,
  								"accountNo" : accountNo,
  								"interBankName" : interBankName,
  								"bank" : bank,
  								"bankCode" : bankCode,
  								"mobile" : mobile,
  								"attentionName" : attentionName,
  								"attentionMobile" : attentionMobile,
  								"businessPhotoPath" : businessPhotoPath,
  								"certAttribute1Path" : certAttribute1FilePath,
  								"certAttribute2Path" : certAttribute2FilePath,
  								"doorPhotoPath" : doorPhotoPath,
  								"shopInteriorPath" : shopInteriorPath,
  								"qualificationPath" : qualificationPath,
  								"otherMaterialPath" : otherMaterialPath,
  								"signaturePath" : signaturePath,
  								"openPath" : openPath,
  								"bankCardPath" : bankCardPhotoPath
  	   							},
  	   							function(data){
  	   								if(data.result=="SUCCESS"){
  	  	   								alert("提交报备成功");
  	   									/* $.gyzbadmin.alertSuccess("提交报备成功！",function(){
  	   										//$("#updateAccountModal").modal("hide");
  	   									},function(){
  	   										this.location.reload();
  	   									}); */
  	   								}else{
  	   									/* $.gyzbadmin.alertFailure(data.message);
  	   									window.location.reload(); */
  	   									/* $.gyzbadmin.alertFailure('提交报备失败:' + data.message,function(){
  	   										//$("#updateAccountModal").modal("hide");
  	   									},function(){
  	   										window.location.reload();
  	   									}); */
  	   									if(null == data.message || "" ==data.message){
											alert("进件失败");
  	  	   								}else{
  	  	   									alert(data.message);
  	  	  	   							}
  	   								}
  	   							},'json'
  	   						  );	
  	   					}
  	   				});
  	  			}
   			
   				//照片
   		 		/* $("input[type=file]").each(
   					function() {
   						var _this = $(this);
   						_this.localResizeIMG({
   							quality : 0.8,
   							success : function(result,file) {
   								var att = pre.substr(pre.lastIndexOf("."));
   								//压缩后图片的base64字符串
   								var base64_string = result.clearBase64;
   								$('#'+_this.attr('id')+'temp').val(att+","+base64_string);
   								//图片预览
   					             var imgObj = $('#'+_this.attr('id')+'Image');
   					             imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
   					             
   					             var width = result.width;
   					             var height = result.height;
   					             
   					             var scale =  width/height;
   						     	 if(width >800){
   						     		width = 800;
   						     		height = width / scale;
   						     	 }
   					             $(".showDiv").width(width+"px");
   					             $(".showDiv").height(height+"px");
   							}
   						});
   					}
   		 		) */
   	 		}
        });
   	});  
</script>
</body>
</html>