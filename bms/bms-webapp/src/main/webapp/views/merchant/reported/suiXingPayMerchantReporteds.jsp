<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.seven.micropay.channel.enums.suixinpay.SuixinBankType"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
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
	<title>随行付进件</title>
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
		
		
		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>
			
			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<!-- <input type="hidden" id="taskCode" name="taskCode"/> -->
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="SUIXING_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="openPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="bankCardPhototemp"/>
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    	<form  id="merchantForm"  method="post">
					            <!--基本资料-->
					            <div class="aui-content-up-form" id ="fileReport" style = "display:">
					                <h2>图片上传</h2>
					            </div>
					            <div class="aui-content-up-form" id ="merchantReport" style = "display:none">
					                <h2>商户进件</h2>
					            </div>
					            <input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					            <div class="aui-form-group clear" id="merchantCodeType" style = "display:">
					                <label class="aui-label-control">
					                   	商户编号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="merchantCode" id="merchantCode" 
					                    	   required value="${custInfo.merchantCode }"  readonly="readonly">
					                   	<label class="label-tips" id="merchantCodeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="custNameType" style = "display:">
					                <label class="aui-label-control">
					                   	 商户简称<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="custName" id="custName" 
					                    	   onBlur="checkna()" required value="${custInfo.custName }" placeholder="请输入身份证名字">
					                    <label id="custNameLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="mobileNoType" style = "display:none">
					                <label class="aui-label-control">
					                   	手机号码 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="mobileNo" id="mobileNo"  
					                    	   value="${custInfo.mobile }" placeholder="请输入11位的手机号码" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="mobileNoLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantType" style = "display:">
					                <label class="aui-label-control">
					                   	是否有证商户 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="suiXingMerchantType" id="suiXingMerchantType" style="width:250px;" onchange = "getUpdateType();" >
													<option value="">--请选择--</option>
													<option value="02">--无证商户--</option>
													<option value="01">--有证商户--</option>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="suiXingMerchantLab"></label>
					                </div>
					            </div>
					           <div class="aui-form-group clear" id="mecType" style = "display:none">
					                <label class="aui-label-control">
					                   	商户类型 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="mecTypeFlag" id="mecTypeFlag" style="width:250px;"  >
													<option value="">--请选择--</option>
													<option value="00">--普通商户--</option>
													<option value="01">--连锁总店--</option>
													<option value="02">--连锁分店--</option>
													<option value="03">--1+n总店--</option>
													<option value="04">--1+n总店--</option>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="mecTypeFlagLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="cprRegNmCnType" style = "display:none">
					                <label class="aui-label-control">
					                   	营业执照注册名<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="cprRegNmCn" id="cprRegNmCn"  
					                    	   value="" placeholder="请输入注册名称(与证件相同)"  required/>
					                    <label class="label-tips" id="cprRegNmCnLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="registCodeType" style = "display:none">
					                <label class="aui-label-control">
					                   	注册号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="registCode" id="registCode"  
					                    	   value="${custInfo.businessLicense }" placeholder="营业执照号|统一信用编码" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="registCodeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantProvinceType" style = "display:none">
					                <label class="aui-label-control">
					                   	注册地区 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper pay-select-address clear">
					                            <div class="pay-select fl" >
					                                <select name="merchantProvince" id="merchantProvince" class="col-xs-11 aui-form-control-two" onchange="getMerchantCity();">
					                                    <option value="">--请选择省--</option>
					                                    <c:if test="${not empty merchantProvinceList }">
					                                        <c:forEach items="${merchantProvinceList }" var="merchantProvince">
					                                            <option id="${merchantProvince.areaId}"
					                                                    value="${merchantProvince.areaId}">
					                                                ${merchantProvince.areaName}
					                                            </option>
					                                        </c:forEach>
					                                    </c:if>
					                                </select>
					                                <label id="merchantProvinceLabel" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl" >
					                                <select name="merchantCity" id="merchantCity" class="col-xs-11 aui-form-control-two" onchange="getMerchantArea();">
					                                    <option value="">--请选择市--</option>
					                                </select>
					                               	<label id="merchantCityLabel" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl">
					                                <select name="merchantArea" id="merchantArea" class="col-xs-11 aui-form-control-two">
					                                    <option value="">--请选择区--</option>
					                                </select>
					                                <label id="merchantAreaLabel" class="label-tips"></label>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="cprRegAddrType" style = "display:none">
					                <label class="aui-label-control">
					                   	商户详细地址<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="cprRegAddr" id="cprRegAddr"  
					                    	   value="" placeholder="请输入详细地址"  required/>
					                    <label class="label-tips" id="cprRegAddrLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="industryType" style = "display:none">
					                <label class="aui-label-control">
					                   	随行付商户行业信息 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                   <span class="input-icon">
										   <select name="industry" id="industry" class="aui-form-control-two"  style="width:250px;" >
												<option value="">--请选择商户行业--</option>
												<c:if test="${not empty industryList }">
						                           	<c:forEach items="${industryList }" var="industry">
						                           		<option id="${industry.industryCode}" value="${industry.industryCode}">${industry.industryName}</option>
						                           	</c:forEach>
					                 		  	</c:if>
										   </select>
										   <label id="industryLabel" class="label-tips"></label>
									   </span>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="representativeNameType" style = "display:none">
					                <label class="aui-label-control">
					                   	法人姓名<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="representativeName" onBlur="checkna()" 
					                    		required id="representativeName" placeholder="请输入姓名" value="${custInfo.representativeName }">
					                    <label id="representativeNameLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="representativeCert" style = "display:none">
					                <label class="aui-label-control">
					                   	法人证件类型<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="representativeCertType" id="representativeCertType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<option value="00">--身份证--</option>
													<option value="03">--军人证--</option>
													<option value="04">--警察证--</option>
													<option value="05">--港澳居民往来内地通行证--</option>
													<option value="06">--台湾居民来往大陆通行证--</option>
													<option value="07">--护照--</option>
													<option value="98">--单位证件--</option>
													<option value="06">--其他证件--</option>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="representativeCertTypeLab"></label>
					                </div>
					            </div>
					            
					           <div class="aui-form-group clear" id="representativeCertNoType" style = "display:none">
					                <label class="aui-label-control">
					                   	法人证件号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
											<input type="text" class="aui-form-control-two"  style="width: 250px;"  id="representativeCertNo" name="representativeCertNo"   
													placeholder="请填写经营人证件号码" value="" >
											<label class="label-tips" id="representativeCertNoLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="actNmType" style = "display:none">
					                <label class="aui-label-control">
					                   	结算账户名 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="actNm" onBlur="checkna()" 
					                    		required id="actNm" placeholder="请输入结算账务人姓名" value="${custInfo.representativeName }">
					                    <label id="actNmTypeLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="actType1" style = "display:">
					                <label class="aui-label-control">
					                   	结算账户类型 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="actType" id="actType" style="width:250px;" onchange = "getUpdateType1();" >
													<option value="">--请选择--</option>
													<option value="00">--对公--</option>
													<option value="01">--对私--</option>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="actNoLab"></label>
					                </div>
					            </div>
					            
					            
					            <div class="aui-form-group clear" id="certifyNoType" style = "display:none">
					                <label class="aui-label-control">
					                   	账户人身份证号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
											<input type="text" class="aui-form-control-two"  style="width: 250px;"  id="certifyNo" name="certifyNo"   
													placeholder="请填写账户人身份证号码" value="" >
											<label class="label-tips" id="certifyNoLab"></label>
					                </div>
					            </div>
					           <div class="aui-form-group clear" id="bankCardType" style = "display:none">
					                <label class="aui-label-control">
					                   	结算银行卡号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="bankCardNo" id="bankCardNo" 
					                    		placeholder="请输入银行卡卡号" value="${custInfo.compMainAcct }" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="bankCardNoLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="suiXinBankType" style = "display:none">
					                <label class="aui-label-control">
					                   	开户行 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                        <span class="input-icon">	
												<select name="suiXinBank" id="suiXinBank" style="width:250px;">
													<option value="">--请选择--</option>
													<c:forEach items="<%=SuixinBankType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.code}">selected</c:if>>
															${status.name}
														</option>
													</c:forEach>
												</select>
											</span>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="bestBankLab"></label>
					                </div>
					            </div>
					            
					            <div class="aui-form-group clear" id="iBankAddressType" style = "display:none">
					                <label class="aui-label-control">
					                   	开户行所在地区 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper pay-select-address clear">
					                            <div class="pay-select fl" >
					                                <select name="bankProvince" id="bankProvince" class="col-xs-11 aui-form-control-two" onchange="getCity();">
					                                    <option value="">--请选择省--</option>
					                                    <c:if test="${not empty provinceList }">
					                                        <c:forEach items="${provinceList }" var="province">
					                                            <option id="${province.provinceId}"
					                                                    value="${province.provinceId}">
					                                                ${province.provinceName}
					                                            </option>
					                                        </c:forEach>
					                                    </c:if>
					                                </select>
					                                <label id="bankProvinceLabel" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl" >
					                                <select name="bankCity" id="bankCity" class="col-xs-11 aui-form-control-two" >
					                                    <option value="">--请选择市--</option>
					                                </select>
					                               	<label id="bankCityLabel" class="label-tips"></label>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="interBankNameType" style = "display:none">
					                <label class="aui-label-control">
					                   	开户支行名称<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="interBankName"  required 
					                    		id="interBankName" placeholder="请输入支行名称">
					                   <label class="label-tips" id="interBankNameLabel"></label>
					                </div>
					            </div>
					            
					            
					            <div class="aui-form-group clear" id="rateType" style = "display:none">
					                <label class="aui-label-control">
					                  	 费率<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="rate"  required id="rate" placeholder="请输入费率" value="0.38">
					                    <label class="label-tips" id="rateLabel"></label>
					                </div>
					            </div>
					            
					            <div class="aui-form-group clear" id="doorPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 门头照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal"  >
													<label id="doorPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="doorPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="doorPhotoImageVal02"  />  
													<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhotoImage(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="shopInteriorType" style = "display:">
					                <label class="aui-label-control">
					                   	 店内照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
													<label id="shopInteriorDiv"  class="uploadImage" >
														<img id="shopInteriorImageDiv" style="width:100%;height:100%;"onclick="bigImg(this);" >
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75 " >
													<input type="hidden" id="shopInteriorPath" name="shopInteriorPath" />
													<input type="hidden" id="shopInteriorImageVal02"  />
													<input type="file" name="shopInterior" id="shopInterior" onchange="showShopInteriorImage(this)" />
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="idCardType" style = "display:">
					                <label class="aui-label-control">
					                   	 身份证正面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal"  >
													<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="certAttribute1Val02"  />  
													<input type="file" name="certAttribute1" id="certAttribute1" onchange="showCertAttribute1Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="idCardBackType" style = "display:">
					                <label class="aui-label-control">
					                   	 身份证反面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
													<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="certAttribute2Val02"  />  
													<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="settleIdCardType" style = "display:">
					                <label class="aui-label-control">
					                   	 结算人身份证正面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success settleCertAttribute1Click"  data-target="#previewImageModal"  >
													<label id="settleCertAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="settleCertAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="settleCertAttribute1Val02"  />   
													<input type="file" name="settleCertAttribute1" id="settleCertAttribute1" onchange="showSettleCertAttribute1Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="settleIdCardBackType" style = "display:">
					                <label class="aui-label-control">
					                   	 结算身份证反面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success settleCertAttribute2Click"  data-target="#previewImageModal"  >
													<label id="settleCertAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="settleCertAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="settleCertAttribute2Val02"  />  
													<input type="file" name="settleCertAttribute2" id="settleCertAttribute2" onchange="showSettleCertAttribute2Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="legalIdCardType" style = "display:">
					                <label class="aui-label-control">
					                   	 法人身份证正面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success legalCertAttribute1Click"  data-target="#previewImageModal"  >
													<label id="legalCertAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="legalCertAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="legalCertAttribute1Val02"  />  
													<input type="file" name="legalCertAttribute1" id="legalCertAttribute1" onchange="showLegalCertAttribute1Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="legalIdCardBackType" style = "display:">
					                <label class="aui-label-control">
					                   	 法人身份证反面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success legalCertAttribute2Click"  data-target="#previewImageModal"  >
													<label id="legalCertAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="legalCertAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="legalCertAttribute2Val02"  />  
													<input type="file" name="legalCertAttribute2" id="legalCertAttribute2" onchange="showLegalCertAttribute2Image(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="businessPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 营业执照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal"  >
													<label id="businessPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="businessPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="businessPhotoImageVal02"  />  
													<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="openPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 开户许可证 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success openPhotoClick"  data-target="#previewImageModal"  >
													<label id="openPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="openPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="openPhotoImageVal02"  />  
													<input type="file" name="openPhoto" id="openPhoto" onchange="showOpenPhotoImage(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="bankCardPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 银行卡正面照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
													<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="bankCardPhotoImageVal02"  />  
													<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showBankCardPhotoImage(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					        </form>
					    </div>
					    <div class="aui-btn-default">
					    	<%-- <gyzbadmin:functionc url="<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSMERCHANTREPORT%>"> --%>
								<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitFile' data-target="#submitModal" style = "display:">
									上传文件
								</button>
								<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitData' data-target="#submitModal" style = "display:none">
									提交审核
								</button>
							<%-- </gyzbadmin:function> --%>
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
        
      	/***获取银行所在地区***/
      	function getCity(){
      		var province = $("#bankProvince").val().trim();
      		
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGCITY %>",
    		{
    			"province":province
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#bankCity").html("");
           			for ( var city in cityList) {
           				$("#bankCity").append(
           						"<option value='"+ cityList[city].cityId +"'>"
           								+ cityList[city].cityName + "</option>"); 
           			}
    			}else{
    				alert("城市不能为空");
    			}
    		},'json'
    		);	
      	}

      
      	/***获取商户注册所在地区城市***/
      	function getMerchantCity(){
      		var merchantProvinceId = $("#merchantProvince").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
      	    		{
      	    			"superiorAreaCode":merchantProvinceId
      	    		},
      	    		function(data){
      	    			if(data.result=="SUCCESS"){
      	    				var areaList = data.areaList;
      	    				$("#merchantCity").html("");
      	           			for ( var area in areaList) {
      	           				$("#merchantCity").append(
      	           						"<option value='"+ areaList[area].areaId +"'>"
      	           								+ areaList[area].areaName + "</option>"); 
      	           			}
      	           			getMerchantArea();;
      	    			}else{
      	    				alert("省份不能为空");
      	    			}
      	    		},'json'
      	    		);	
        }
      	/***获取商户注册所在地区县区***/
      	function getMerchantArea(){
      		var merchantCityId = $("#merchantCity").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
      	    		{
      	    			"superiorAreaCode":merchantCityId
      	    		},
      	    		function(data){
      	    			if(data.result=="SUCCESS"){
      	    				var areaList = data.areaList;
      	    				$("#merchantArea").html("");
      	           			for ( var area in areaList) {
      	           				$("#merchantArea").append(
      	           						"<option value='"+ areaList[area].areaId +"'>"
      	           								+ areaList[area].areaName + "</option>"); 
      	           			}
      	    			}else{
      	    				alert("市不能为空");
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

      	function getUpdateType(){
			var suiXingMerchantType = $("#suiXingMerchantType").val();
			
			//有证商户
			if("01" == suiXingMerchantType){
				$("#bankCardPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:");
				$("#openPhotoType").attr("style","display:");
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				
			}else if("02" == suiXingMerchantType){
				$("#legalIdCardType").attr("style","display:none");
				$("#legalIdCardBackType").attr("style","display:none");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				$("#businessPhotoType").attr("style","display:none");
				$("#openPhotoType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				
			}
			
      	}

      	function getUpdateType1(){
      		var actType = $("#actType").val();
      		//对公
			if("00" == actType){
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
				$("#settleIdCardType").attr("style","display:none");
				$("#settleIdCardBackType").attr("style","display:none");
				$("#openPhotoType").attr("style","display:");
				$("#bankCardPhotoType").attr("style","display:none");
				
			}else if("01" == actType){
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				$("#openPhotoType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				
			}

      	}

        //图片预览
        function shopInteriorImage(obj){  
       		var divObj = document.getElementById("shopInteriorDiv");  
       		var imageObj = document.getElementById("shopInteriorImageDiv");  
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
    	function showSettleCertAttribute1Image(obj){  
   		 var divObj = document.getElementById("settleCertAttribute1Div");  
   		 var imageObj = document.getElementById("settleCertAttribute1ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
	   	}
	
	   	function showSettleCertAttribute2Image(obj){  
	   		 var divObj = document.getElementById("settleCertAttribute1Div");  
	   		 var imageObj = document.getElementById("settleCertAttribute2ImageDiv");  
	   		 return previewImage(divObj,imageObj,obj);  
	   	}
    	
    /* 校验渠道 */
    $(function(){
    	//判定是新进件还是更新进件
		var status = $("#status").val().trim();
		var custId = $("#custId").val().trim();
		var authId = $("#authId").val().trim();
    	$("#openPhotoImageDiv").show();
    	$("#businessPhotoImageDiv").show();
    	$("#certAttribute1ImageDiv").show();
    	$("#certAttribute2ImageDiv").show();
    	$("#legalCertAttribute1ImageDiv").show();
    	$("#legalCertAttribute2ImageDiv").show();
    	$("#settleCertAttribute1Div").show();
    	$("#settleCertAttribute2Div").show();
    	$("#doorPhotoImageDiv").show();
    	$("#bankCardPhotoImageDiv").show();
    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
    	$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=05&authId="+authId);
    	$("#openPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
    	$("#legalCertAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#legalCertAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#settleCertAttribute1Div").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#settleCertAttribute2Div").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	  
    	$("#submitFile").click(function(){
        	
    		var channelNo = $("#channlCode").val();
   			var merchantCode = $("#merchantCode").val();
   			var suiXingMerchantType = $("#suiXingMerchantType").val();

   			if("" == suiXingMerchantType){
   	    		$("#suiXingMerchantLab").text("资质类型不能为空");
   	    		return false;
   	    	}else{
   	    		$("#suiXingMerchantLab").text('');
   	    	}
   	    	
   			//上传照片
			var imgDoor = [];
			var imgSrc = [];
			var merchantData = new FormData(document.getElementById('merchantForm'));	
			<%-- var mer_upload = document.getElementById('merchantForm');
			mer_upload.action=window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?merchantCode='+merchantCode;
			mer_upload.submit(); --%>
			/* $("#door_temp input[type='hidden']").each(function(){
				if($(this).attr('id').indexOf('Src')>=0){
					imgSrc.push($(this).attr('id')+"="+$(this).val());
				}else{
					if($(this).val()==""){
						imgDoor.push($(this).attr('id')+"="+"无");
					}else{
						imgDoor.push($(this).attr('id')+"="+$(this).val());
					}
				}
			});  */
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?merchantCode='+merchantCode+'&status='+status,
				data :merchantData,
                dataType : "json",
                cache: false,
                processData: false,
                contentType: false,
				success : function(data){
				if(data.result=="SUCCESS"){
					//样式改变
					$("#doorPhotoType").attr("style","display:none");
					$("#idCardType").attr("style","display:none");
					$("#idCardBackType").attr("style","display:none");
					$("#businessPhotoType").attr("style","display:none");
					$("#submitFile").attr("style","display:none");
					$("#fileReport").attr("style","display:none");
					$("#bankCardPhotoType").attr("style","display:none");
					$("#openPhotoType").attr("style","display:none");
					$("#legalIdCardType").attr("style","display:none");
					$("#legalIdCardBackType").attr("style","display:none");
					$("#settleIdCardType").attr("style","display:none");
					$("#settleIdCardBackType").attr("style","display:none");
					
					$("#merchantReport").attr("style","display:");
					$("#submitData").attr("style","display:");
					$("#merchantCodeType").attr("style","display:");
					$("#custNameType").attr("style","display:");
					$("#mobileNoType").attr("style","display:");
					$("#merchantType").attr("style","display:");
					$("#mecType").attr("style","display:");
					$("#merchantProvinceType").attr("style","display:");
					$("#cprRegAddrType").attr("style","display:");
					$("#industryType").attr("style","display:");
					$("#representativeNameType").attr("style","display:");
					$("#representativeCert").attr("style","display:");
					$("#representativeCertNoType").attr("style","display:");
					$("#actNmType").attr("style","display:");
					$("#actType1").attr("style","display:");
					$("#certifyNoType").attr("style","display:");
					$("#bankCardType").attr("style","display:");
					$("#suiXinBankType").attr("style","display:");
					$("#iBankAddressType").attr("style","display:");
					$("#interBankNameType").attr("style","display:");
					$("#rateType").attr("style","display:");
					if("01" == $("#suiXingMerchantType").val()){
						$("#registCodeType").attr("style","display:");
						$("#cprRegNmCnType").attr("style","display:");
					}else{
						$("#registCodeType").attr("style","display:none");
						$("#cprRegNmCnType").attr("style","display:none");
					}
					if("" != data.message){
						$("#taskCode").val(data.message);
					}else{
						alert("上传图片返回码异常");
					}
					
				}else{
					if("" == data.message || null == data.message){
						alert("失败");
					}else{
						alert(data.message);
					}
				}
			},
			error : function(){
				alert("上传失败");
			},
			})
    		
    	});

    	
   		$("#submitData").click(function(){

   	   		//渠道
   			var channelNo = $("#channlCode").val();
			//商户号
   			var merchantCode = $("#merchantCode").val();
			//客户简称
   			var custName = $("#custName").val();
   			//手机号
   			var mobileNo = $("#mobileNo").val();
   			//（资质类型）
   			var suiXingMerchantType = $("#suiXingMerchantType").val();
   			//mecTypeFlag（商户类型）
   			var mecTypeFlag = $("#mecTypeFlag").val();
   			//cprRegNmCn(注册名)
   			var cprRegNmCn =$("#cprRegNmCn").val(); 
   			//registCode（注册号）
			var registCode = $("#registCode").val();
   			// merchantProvince（注册地区）
   			var merchantProvince = $("#merchantProvince").val();
   			// merchantCity
   			var merchantCity = $("#merchantCity").val();
   			// merchantArea
   			var merchantArea =$("#merchantArea").val();
   			// cprRegAddr（商户详细地址）
   			var cprRegAddr = $("#cprRegAddr").val();
   			//industry（行业信息）
   			var industryCode = $("#industry").val();
   			// representativeName（法人姓名）
   			var representativeName = $("#representativeName").val();
   			// representativeCertType（法人证件类型）
   			var representativeCertType = $("#representativeCertType").val();
   			// representativeCertNo（法人证件号）
   			var representativeCertNo = $("#representativeCertNo").val();
   			//actNm（结算名）
   			var actNm = $("#actNm").val();
   			//actType（结算类型）
   			var actType = $("#actType").val();
   			//certifyNo（账户身份证号）
   			var certifyNo = $("#certifyNo").val();
   			// bankCardNo（结算银行卡号）
   			var bankCardNo = $("#bankCardNo").val();
   			//suiXinBank（开户行）
   			var suiXinBank = $("#suiXinBank").val();
   			// bankProvince（开户行所在地区）
   			var bankProvince = $("#bankProvince").val();
   			// bankCity
   			var bankCity = $("#bankCity").val()
   			// interBankName(开户支行名称)
   			var interBankName = $("#interBankName").val();
   			//rate
   			var rate = $("#rate").val();

   			var taskCode = $("#taskCode").val();
   			
   			//随行付渠道
   			if("SUIXING_PAY" == channelNo){
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
  				/* if("unReported" == status){ */

  					if("" == merchantCode){
  	   	   	    		$("#merchantCodeLab").text("商户编号不能为空");
  	   	   	    		$("#merchantCode").focus();
  	   	   	    		return false;
  	   	   	    	}else{
  	   	   	    		$("#merchantCodeLab").text('');
  	   	   	    	}
		  	   	   	if("" == custName){
	 	   	    		$("#custNameLab").text("商户名不能为空");
	 	   	    		$("#custName").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#custNameLab").text('');
	 	   	    	}
		 	   	    if("" == mobileNo){
	 	   	    		$("#mobileNoLab").text("手机号不能为空");
	 	   	    		$("#mobileNo").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#mobileNoLab").text('');
	 	   	    	}
			 	   	if("" == suiXingMerchantType){
	 	   	    		$("#suiXingMerchantLab").text("资质类型不能为空");
	 	   	    		$("#suiXingMerchantType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#suiXingMerchantLab").text('');
	 	   	    	}
			 	    if("" == mecTypeFlag){
	 	   	    		$("#mecTypeFlagLab").text("商户类型不能为空");
	 	   	    		$("#mecTypeFlag").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#mecTypeFlagLab").text('');
	 	   	    	}
	 	   	    	if("01" == mecTypeFlag){
		 	   	    	if("" == cprRegNmCn){
		 	   	    		$("#cprRegNmCnLab").text("营业执照注册名称不能为空");
		 	   	    		$("#cprRegNmCn").focus();
		 	   	    		return false;
		 	   	    	}else{
		 	   	    		$("#cprRegNmCnLab").text('');
		 	   	    	}
			 	   	    if("" == registCode){
		 	   	    		$("#registCodeLab").text("营业执照注册号不能为空");
		 	   	    		$("#registCode").focus();
		 	   	    		return false;
		 	   	    	}else{
		 	   	    		$("#registCodeLab").text('');
		 	   	    	}
		 	   	    }
			 	   	if("" == merchantProvince){
	   	   	    		$("#merchantProvinceLabel").text("注册地址省份不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#merchantProvinceLabel").text("");
	   	   	    	}
			 	   if("" == merchantCity){
	   	   	    		$("#merchantCityLabel").text("注册地址城市不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#merchantCityLabel").text("");
	   	   	    	}
			 	    if("" == merchantArea){
	   	   	    		$("#merchantAreaLabel").text("注册地址地区不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#merchantAreaLabel").text("");
	   	   	    	}
		   	   	    if("" == cprRegAddr){
	 	   	    		$("#cprRegAddrLabel").text("注册详细地址不能为空");
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#cprRegAddrLabel").text("");
	 	   	    	}
		 	   	    if("" == industryCode){
	 	   	    		$("#industryCodeLabel").text("行业信息不能为空");
	 	   	    	    $("#industryCode").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#industryCodeLabel").text("");
	 	   	    	}
		 	   	    if("" == representativeName){
	 	   	    		$("#representativeNameLabel").text("法人姓名不能为空");
	 	   	    	    $("#representativeName").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#representativeNameLabel").text("");
	 	   	    	}
		 	   	    if("" == representativeCertType){
	 	   	    		$("#representativeCertTypeLabel").text("法人证件类型不能为空");
	 	   	    	    $("#representativeCertType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#representativeCertTypeLabel").text("");
	 	   	    	}
		 	   	    if("" == representativeCertNo){
	 	   	    		$("#representativeCertNoLabel").text("法人证件号不能为空");
	 	   	    	    $("#representativeCertNo").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#representativeCertNoLabel").text("");
	 	   	    	}
		 	   	    if("" == actNm){
	 	   	    		$("#actNmLabel").text("结算姓名不能为空");
	 	   	    	    $("#actNm").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#actNmLabel").text("");
	 	   	    	}
			 	   	if("" == actType){
	 	   	    		$("#actLabel").text("结算账号不能为空");
	 	   	    	    $("#actType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#actLabel").text("");
	 	   	    	}
		 	   	    if("" == certifyNo){
	 	   	    		$("#certifyNoLabel").text("结算身份证号不能为空");
	 	   	    	    $("#certifyNo").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#certifyNoLabel").text("");
	 	   	    	}
		 	   	    if("" == bankCardNo){
	 	   	    		$("#bankCardNoLabel").text("结算开户行卡号不能为空");
	 	   	    	    $("#bankCardNo").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#bankCardNoLabel").text("");
	 	   	    	}
		 	   	    if("" == suiXinBank){
	 	   	    		$("#suiXinBankLabel").text("结算开户行不能为空");
	 	   	    	    $("#suiXinBank").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#suiXinBankLabel").text("");
	 	   	    	}
			 	   	if("" == bankProvince){
	   	   	    		$("#bankProvinceLabel").text("开户行所在省份不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#bankProvinceLabel").text("");
	   	   	    	}
			 	    if("" == bankCity){
	   	   	    		$("#bankCityLabel").text("开户行所在城市不能为空");
	   	   	    		return false;
	   	   	    	}else{
	   	   	    		$("#bankCityLabel").text("");
	   	   	    	}
		   	   	    if("" == interBankName){
	 	   	    		$("#interBankNameLabel").text("开户支行名称不能为空");
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#interBankNameLabel").text("");
	 	   	    	}
	   		    	if("" == rate){
	   		    		$("#rateLabel").text("费率不能为空");
	   		    		$("#rate").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#rateLabel").text("");
	   		    	}
  	   		    	
  	   	   	    
  	   				$.ajax({
  	   					type : "POST",
  	   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SUXINGPAYSUBMITREPORT%>',
  	   					data :{
  	   						channelNo : channelNo,
  	   						merchantCode : merchantCode,
  	   					 	custName : custName,
  	   						mobileNo : mobileNo,
  	   						suiXingMerchantType : suiXingMerchantType,
  	   						mecTypeFlag : mecTypeFlag,
  	   						cprRegNmCn : cprRegNmCn,
  	   						registCode : registCode,
  	   						merchantProvince : merchantProvince,
  	   						merchantCity : merchantCity,
  	   						merchantArea : merchantArea,
  	   						cprRegAddr : cprRegAddr,
  	   						industryCode : industryCode,
  	   						representativeName : representativeName,
  	   						representativeCertType : representativeCertType,
  	   					    representativeCertNo : representativeCertNo,
  	   					    actNm : actNm,
  	   						actType : actType,
  	   						certifyNo : certifyNo,
  	   						bankCardNo : bankCardNo,
  	   						suiXinBank : suiXinBank,
  	   						bankProvince : bankProvince,
  	   						bankCity : bankCity,
  	   						interBankName : interBankName,
  	   						rate : rate,
  	   						taskCode : taskCode
  	   					},
  	   					dataType : "json",
  	   					success : function(data) {
  	   						if(data.result=='SUCCESS'){							
  	   							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
										//$("#updateAccountModal").modal("hide");
									},function(){
										this.location.reload();
									});
								}else{
									/* $.gyzbadmin.alertFailure(data.message);
									window.location.reload(); */
									/* $.gyzbadmin.alertFailure('提交报备失败:' + data.message,function(){
										//$("#updateAccountModal").modal("hide");
									},function(){
										window.location.reload();
									}); */
									alert(data.message);
								}
  	   					}
  	   				});
  	  			/* } */
   			
   				//照片
   		 		$("input[type=file]").each(
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
   		 		) 
   	 		}
        });
   	});  
</script>
</body>
</html>