<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerchantApplyType"%>
<%@page import="com.seven.micropay.channel.enums.CertificateType"%>
<%@page import="com.seven.micropay.channel.enums.CardType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMchGradeType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCompanyCodeType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayIdCardLegalType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayProfessionType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCycleDays"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMerSumType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayBankNmType"%>

<%@ include file="/include/template.jsp"%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.min.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>商盟支付进件</title>
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
					<input type="hidden" id="channlCode" name="channlCode" value="SUM_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="certAttribute0temp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="opentemp"/>
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    	<form  id="merchantForm" action='<c:url value="<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYMERCHANTREPORTS %>"/>' method="post">
					            <!--基本资料-->
					            <div class="aui-content-up-form">
					                <h2>基本资料</h2>
					            </div>
					            <div class="aui-form-group clear" id="merchantCodeType1" style = "display:">
					                <label class="aui-label-control">
					                   	商户编号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="merchantCode" id="merchantCode" 
					                    	   required value="${custInfo.merchantCode }" readonly="readonly">
					                   	<label class="label-tips" id="merchantCodeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="custNameType" style = "display:">
					                <label class="aui-label-control">
					                   	 商户名(简称)<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="custName" id="custName" 
					                    	   onBlur="checkna()" required value="${custInfo.custName }" placeholder="请输入身份证名字">
					                    <label id="custNameLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="businessLicenseType" style = "display:">
					                <label class="aui-label-control">
					                   	 营业执照号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="businessLicense" id="businessLicense" 
					                    	   onBlur="checkna()" required value="${custInfo.businessLicense }" placeholder="请输入营业执照号">
					                    <label id="businessLicenseLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="cprRegAddrType" style = "display:">
					                <label class="aui-label-control">
					                   	商户详细地址<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="cprRegAddr" id="cprRegAddr"  
					                    	   value="${custInfo.custAdd }" placeholder="请输入详细地址"  required/>
					                    <label class="label-tips" id="cprRegAddrLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="addressType" style = "display:">
					                <label class="aui-label-control">
					                   	地址 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper pay-select-address clear">
					                            <div class="pay-select fl" >
					                                <select name="province" id="province" class="col-xs-11 aui-form-control-two" onchange="getCity();">
					                                    <option value="">--请选择省--</option>
					                                    <c:if test="${not empty provinceList }">
					                                        <c:forEach items="${provinceList }" var="province">
					                                            <option id="${province.areaId}"
					                                                    value="${province.areaId}">
					                                                ${province.areaName}
					                                            </option>
					                                        </c:forEach>
					                                    </c:if>
					                                </select>
					                                <label id="provinceLabel" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl" >
					                                <select name="city" id="city" class="col-xs-11 aui-form-control-two" onchange="getArea();">
					                                    <option value="">--请选择市--</option>
					                                </select>
					                               	<label id="cityLabel" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl">
					                                <select name="area" id="area" class="col-xs-11 aui-form-control-two">
					                                    <option value="">--请选择区--</option>
					                                </select>
					                                <label id="areaLabel" class="label-tips"></label>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="interNameType" style = "display:">
					                <label class="aui-label-control">
					                   	法人姓名 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="InterName" onBlur="checkna()" 
					                    		required id="InterName" placeholder="请输入姓名" value="">
					                    <label id="interNameLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="certifyNoType" style = "display:">
					                <label class="aui-label-control">
					                   	证件号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
											<input type="text" class="aui-form-control-two"  style="width: 250px;"  id="certifyNo" name="certifyNo"   
													placeholder="请填写经营人身份证号码" value="${custInfo.certifyNo }" onBlur="checkpsd1()">
											<label class="label-tips" id="certifyNoLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="perEntFlagType" style = "display:">
					                <label class="aui-label-control">
					                   	对公对私标识 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="perEntFlag" id="perEntFlag" style="width:250px;"  >
													<option value="">--请选择--</option>
													<option value="SUMPAY_0">--对公--</option>
													<option value="SUMPAY_1">--对私--</option>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="perEntFlagLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="bankCodeType" style = "display:">
					                <label class="aui-label-control">
					                   	开户总行联行号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="bankCode"  required 
					                    		id="bankCode" placeholder="请输入开户总行联行号">
					                   <label class="label-tips" id="bankCodeLabel"></label>
					                </div>
					            </div>
					          	<div class="aui-form-group clear" id="interBankCodeType" style = "display:">
					                <label class="aui-label-control">
					                   	开户支行号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="interBankCode"  required 
					                    		id="interBankCode" placeholder="请输入开户支行号">
					                   <label class="label-tips" id="interBankCodeLabel"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="interBankNameType" style = "display:">
					                <label class="aui-label-control">
					                   	开户支行名称<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width:250px;" name="interBankName"  required 
					                    		id="interBankName" placeholder="请输入银行名称">
					                   <label class="label-tips" id="interBankNameLabel"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="mobileType" style = "display:">
					                <label class="aui-label-control">
					                   	 预留电话 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="mobile" id="mobile"  
					                    	   value="${custInfo.merchantMobile }" placeholder="请输入11位的手机号码" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="mobileLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="attentionNameType" style = "display:">
					                <label class="aui-label-control">
					                   	 联系人名称 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="attentionName" id="attentionName"  
					                    	   value="" placeholder="请输入联系人名称" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="attentionNameLab"></label>
					                </div>
					                
					            </div>
					            <div class="aui-form-group clear" id="attentionMobileType" style = "display:">
					                <label class="aui-label-control">
					                   	 联系人手机号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="attentionMobile" id="attentionMobile"  
					                    	   value="" placeholder="请输入11位的手机号码" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="attentionMobileLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="attentionEmailType" style = "display:">
					                <label class="aui-label-control">
					                   	  联系人邮箱 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="attentionEmail" id="attentionEmail"  
					                    	   value="" placeholder="请输入 联系人邮箱" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="attentionEmailLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantLoginEmailType" style = "display:">
					                <label class="aui-label-control">
					                   	  商户登陆邮箱号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="merchantLoginEmail" id="merchantLoginEmail"  
					                    	   value="" placeholder="请输入商户登陆邮箱号" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="merchantLoginEmailLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantLoginMobileType" style = "display:">
					                <label class="aui-label-control">
					                   	  商户登陆手机号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="merchantLoginMobile" id="merchantLoginMobile"  
					                    	   value="" placeholder="请输入商户登陆手机号" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="merchantLoginMobileLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="customerPhoneType" style = "display:">
					                <label class="aui-label-control">
					                   	  客服电话<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="customerPhone" id="customerPhone"  
					                    	   value="" placeholder="请输入客服电话" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="customerPhoneLab"></label>
					                </div>
					            </div>
					       		<div class="aui-form-group clear" id="merchantLevType" style = "display:">
					                <label class="aui-label-control">
					                   	商户级别 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="merchantLev" id="merchantLev" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayMchGradeType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.merchantLev}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="merchantLevLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantCodeType1" style = "display:">
					                <label class="aui-label-control">
					                   	企业代码类型 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="merchantCodeType" id="merchantCodeType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayCompanyCodeType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.merchantCodeType}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="merchantCodeTypeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="representativePhoneType" style = "display:">
					                <label class="aui-label-control">
					                   	  法人手机号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="representativePhone" id="representativePhone"  
					                    	   value="" placeholder="请输入法人手机号" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="representativePhoneLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="representativeAddrType" style = "display:">
					                <label class="aui-label-control">
					                   	  联系地址<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="representativeAddr" id="representativeAddr"  
					                    	   value="" placeholder="请输入联系地址" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="representativeAddrLab"></label>
					                </div>
					            </div>
					            
					             <div class="aui-form-group clear" id="addressType" style = "display:">
					                <label class="aui-label-control">
					                   	联系省市区地址 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper pay-select-address clear">
					                            <div class="pay-select fl" >
					                                <select name="provinceAdd" id="provinceAdd" class="col-xs-11 aui-form-control-two" onchange="getCityAdd();">
					                                    <option value="">--请选择省--</option>
					                                    <c:if test="${not empty provinceList }">
					                                        <c:forEach items="${provinceList }" var="province">
					                                            <option id="${province.areaId}"
					                                                    value="${province.areaId}">
					                                                ${province.areaName}
					                                            </option>
					                                        </c:forEach>
					                                    </c:if>
					                                </select>
					                                <label id="provinceLabel1" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl" >
					                                <select name="cityAdd" id="cityAdd" class="col-xs-11 aui-form-control-two" onchange="getAreaAdd();">
					                                    <option value="">--请选择市--</option>
					                                </select>
					                               	<label id="cityLabel1" class="label-tips"></label>
					                            </div>
					                            <div class="pay-select fl">
					                                <select name="areaAdd" id="areaAdd" class="col-xs-11 aui-form-control-two">
					                                    <option value="">--请选择区--</option>
					                                </select>
					                                <label id="areaLabel1" class="label-tips"></label>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="sumpayIdCardLegalType1" style = "display:">
					                <label class="aui-label-control">
					                   	法人证件类型 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="sumpayIdCardLegalType" id="sumpayIdCardLegalType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayIdCardLegalType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.sumpayIdCardLegalType}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="sumpayIdCardLegalTypeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="merchantType1" style = "display:">
					                <label class="aui-label-control">
					                   	商户类别 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="sumpayProfessionType" id="sumpayProfessionType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayProfessionType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.sumpayProfessionType}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="sumpayProfessionTypeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="sumpayCycleDaysType" style = "display:">
					                <label class="aui-label-control">
					                   	 结算周期 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="sumpayCycleDays" id="sumpayCycleDays" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayCycleDays.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.sumpayCycleDays}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="sumpayCycleDaysLab"></label>
					                </div>
					            </div>
					           
					            <div class="aui-form-group clear" id="residentCityType" style = "display:">
					                <label class="aui-label-control">
					                   	结算账户开户所在城市 <em>*</em>
					                </label>
					                <div class="aui-form-input">
											<input type="text" class="aui-form-control-two"  style="width: 250px;"  id="residentCity" name="residentCity"   
													placeholder="请填写开户行所在地" value="" onBlur="checkpsd1()">
											<label class="label-tips" id="residentCityLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="sumpayMerSumType1" style = "display:">
					                <label class="aui-label-control">
					                   	  业务类型 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="sumpayMerSumType" id="sumpayMerSumType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayMerSumType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.sumpayMerSumType}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="sumpayMerSumTypeLab"></label>
					                </div>
					            </div>
					           <div class="aui-form-group clear" id="accountNmType" style = "display:">
					                <label class="aui-label-control">
					                                              结算账号名称<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="accountNm" id="accountNm"  
					                    	   value="" placeholder="请输入 结算账号名称" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="accountNmLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="accountNoType" style = "display:">
					                <label class="aui-label-control">
					                                              结算账号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="accountNo" id="accountNo"  
					                    	   value="" placeholder="请输入 结算账号" onBlur="checkpsd1()" required/>
					                    <label class="label-tips" id="accountNoLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="sumpayBankNmType1" style = "display:">
					                <label class="aui-label-control">
					                   	  开户行号 <em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <div class="form-item-control ">
					                        <div class="pay-select-wrapper">
					                            <select name="sumpayBankNmType" id="sumpayBankNmType" style="width:250px;"  >
													<option value="">--请选择--</option>
													<c:forEach items="<%=SumpayBankNmType.values()%>" var="status">
														<option value="${status}" <c:if test="${status == queryBean.sumpayBankNmType}">selected</c:if>>
															${status.text}
														</option>
													</c:forEach>
												</select>
					                        </div>
					                    </div>
					                    <label class="label-tips" id="sumpayBankNmTypeLab"></label>
					                </div>
					            </div>
					            
					             <!--纸质信息-->
					            
					            <div class="aui-content-up-form" id="infoType" style = "display:none">
					                <h2>纸质信息</h2>
					            </div>
					            <div class="aui-form-group clear" id="doorPhotoType" style = "display:none">
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
					            <div class="aui-form-group clear" id="openType" style = "display:none">
					                <label class="aui-label-control">
					                   	 开户许可证 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success openClick"  data-target="#previewImageModal"  >
													<label id="openDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="openImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="openImageVal02"  />  
													<input type="file" name="open" id="open" onchange="showOpenImage(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="idCardType" style = "display:none">
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
					            <div class="aui-form-group clear" id="idCardBackType" style = "display:none">
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
					            <div class="aui-form-group clear" id="businessPhotoType" style = "display:none">
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
					            <div class="aui-form-group clear" id="shopInteriorType" style = "display:none">
					                <label class="aui-label-control">
					                   	 店内照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success shopInteriorClick"  data-target="#previewImageModal"  >
													<label id="shopInteriorDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="shopInteriorImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input type="hidden" id="shopInteriorImageVal02"  />  
													<input type="file" name="shopInterior" id="shopInterior" onchange="showShopInteriorImage(this)"/>
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
					    	<%-- <gyzbadmin:function url="<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSMERCHANTREPORT%>"> --%>
								<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitData' data-target="#submitModal">
									保存并提交审核
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
        
      	/***获取市***/
      	function getCity(){
      		var area = $("#province").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYSELCITY %>",
    		{
    			"area":area
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var sumPayAreaList = data.sumPayAreaList;
    				$("#city").html("");
           			for ( var sumPayArea in sumPayAreaList) {
           				$("#city").append(
           						"<option value='"+ sumPayAreaList[sumPayArea].areaId +"'>"
           								+ sumPayAreaList[sumPayArea].areaName + "</option>"); 
           			}
           			getArea();
    			}
    		},'json'
    		);	
      	}
      	
      	/***获取区县***/
      	function getArea(){
      		var area = $("#city").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYSELCITY %>",
    		{
    			"area":area
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var sumPayAreaList = data.sumPayAreaList;
    				$("#area").html("");
           			for ( var sumPayArea in sumPayAreaList) {
           				$("#area").append(
           						"<option value='"+ sumPayAreaList[sumPayArea].areaId +"'>"
           								+ sumPayAreaList[sumPayArea].areaName + "</option>");
           			}
    			}
    		},'json'
    		);	
      	}



      	/***获取市***/
      	function getCityAdd(){
      		var area = $("#provinceAdd").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYSELCITY %>",
    		{
    			"area":area
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var sumPayAreaList = data.sumPayAreaList;
    				$("#cityAdd").html("");
           			for ( var sumPayArea in sumPayAreaList) {
           				$("#cityAdd").append(
           						"<option value='"+ sumPayAreaList[sumPayArea].areaId +"'>"
   								+ sumPayAreaList[sumPayArea].areaName  + "</option>"); 
           			}
           			getAreaAdd();
    			}else{
    				alert("省份不能为空");
    			}
    		},'json'
    		);	
      	}
      	
      	/***获取区县***/
      	function getAreaAdd(){
      		var area = $("#cityAdd").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYSELCITY %>",
    		{
    			"area":area
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var sumPayAreaList = data.sumPayAreaList;
    				$("#areaAdd").html("");
           			for ( var sumPayArea in sumPayAreaList) {
           				$("#areaAdd").append(
           						"<option value='"+ sumPayAreaList[sumPayArea].areaId +"'>"
           								+ sumPayAreaList[sumPayArea].areaName + "</option>");
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


        //图片预览
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
		var status = $("#status").val().trim();
		var custId = $("#custId").val().trim();
		var authId = $("#authId").val().trim();
    	$("#businessPhotoImageDiv").show();
    	$("#certAttribute1ImageDiv").show();
    	$("#certAttribute2ImageDiv").show();
    	$("#doorPhotoImageDiv").show();
    	$("#openImageDiv").show();
    	$("#shopInteriorImageDiv").show();
    	$("#shopInteriorImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=18&authId="+authId);
    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#openImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);    
    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
    	
   		$("#submitData").click(function(){
   	   		
   			var channelNo = $("#channlCode").val();
   			var merchantCode = $("#merchantCode").val();
   			var custName = $("#custName").val();
   			var businessLicense = $("#businessLicense").val();//营业执照号
   			var cprRegAddr = $("#cprRegAddr").val();//商户详细地址
   			var province = $("#province").val();
   			var city = $("#city").val();
   			var country = $("#area").val();
   			var interName = $("#InterName").val();
   			var certifyNo = $("#certifyNo").val();
   			var perEntFlag = $("#perEntFlag").val();
   			var bankCode = $("#bankCode").val();
   			var interBankCode = $("#interBankCode").val();//开户支行号
   			var interBankName = $("#interBankName").val();
   			var mobile = $("#mobile").val();
   			var attentionName = $("#attentionName").val();
   			var attentionMobile = $("#attentionMobile").val();
   			var attentionEmail = $("#attentionEmail").val();
			var merchantLoginEmail = $("#merchantLoginEmail").val();
			var merchantLoginMobile = $("#merchantLoginMobile").val();
			var customerPhone = $("#customerPhone").val();
			var merchantLev = $("#merchantLev").val();
			var merchantCodeType = $("#merchantCodeType").val();
			var representativePhone = $("#representativePhone").val();
			var representativeAddr = $("#representativeAddr").val();
   			//联系省市区
   			var provinceAdd = $("#provinceAdd").val();
   			var cityAdd = $("#cityAdd").val();
   			var countryAdd = $("#areaAdd").val();
   			var sumpayIdCardLegalType = $("#sumpayIdCardLegalType").val(); 
			var sumpayProfessionType = $("#sumpayProfessionType").val();  
			var sumpayCycleDays = $("#sumpayCycleDays").val(); 
			var residentCity = $("#residentCity").val();  
			var sumpayMerSumType = $("#sumpayMerSumType").val(); 
			var accountNm = $("#accountNm").val();  
			var accountNo = $("#accountNo").val();  
   			var sumpayBankNmType = $("#sumpayBankNmType").val();  
   			 
   			
   			//商盟支付渠道
   			if("SUM_PAY" == channelNo){
   		    	//上传照片
   				/* var imgDoor = [];
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
   				}); */
				//未报备
  				if("unReported" == status){

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
			  	   	if("" == businessLicense){
	 	   	    		$("#businessLicenseLab").text("营业执照号不能为空");
	 	   	    		$("#businessLicense").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#businessLicenseLab").text('');
	 	   	    	}
			  	    if("" == cprRegAddr){
	   		    		$("#cprRegAddrLab").text("详细地址不能为空");
	   		    		$("#cprRegAddr").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#cprRegAddrLab").text("");
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
	   	   			
	   	   			if("" == country){
	   	   				$("#areaLabel").text("报备区不能为空");
	   	   				return false;
	   	   			}else{
	   	   	    		$("#areaLabel").text("");
	   	   	    	} 
	   	   			if("" == interName){
	   		    		$("#interNameLab").text("法人姓名不能为空");
	   		    		$("#InterName").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interNameLab").text("");
	   		    	}
		   	   		if("" == certifyNo){
	   		    		$("#certifyNoLab").text("身份证件号不能为空");
	   		    		$("#certifyNo").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#certifyNoLab").text("");
	   		    	}
			   	   	if("" == perEntFlag){
	   		    		$("#perEntFlagLab").text("对公对私标识不能为空");
	   		    		$("#perEntFlag").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#perEntFlagLab").text("");
	   		    	}
			   	 
				   	if("" == bankCode){
	   		    		$("#bankCodeLabel").text("开户总行联行号不能为空");
	   		    		$("#bankCode").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#bankCodeLabel").text("");
	   		    	}
				   	
				   	if("" == interBankCode){
	   		    		$("#interBankCodeLabel").text("开户支行号不能为空");
	   		    		$("#interBankCode").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interBankCodeLabel").text("");
	   		    	}
	   		    	if("" == interBankName){
	   		    		$("#interBankNameLabel").text("开户银行名称不能为空");
	   		    		$("#interBankName").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interBankNameLabel").text("");
	   		    	}
	   		    	
	   		    	if("" == mobile){
	 	   	    		$("#mobileLab").text("手机号不能为空");
	 	   	    		$("#mobile").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#mobileLab").text('');
	 	   	    	}
	   		    	if("" == attentionName){
	 	   	    		$("#attentionNameLab").text("联系人名称不能为空");
	 	   	    		$("#attentionName").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#attentionNameLab").text('');
	 	   	    	}
	   		    	if("" == attentionMobile){
	 	   	    		$("#attentionMobileLab").text("联系人手机号不能为空");
	 	   	    		$("#attentionMobile").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#attentionMobileLab").text('');
	 	   	    	}
	   		    	if("" == attentionEmail){
	 	   	    		$("#attentionEmailLab").text("联系人邮箱不能为空");
	 	   	    		$("#attentionEmail").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#attentionEmailLab").text('');
	 	   	    	}
	   		    	
	   		    	if("" == merchantLoginEmail){
	 	   	    		$("#merchantLoginEmailLab").text("登录邮箱不能为空");
	 	   	    		$("#merchantLoginEmail").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#merchantLoginEmailLab").text('');
	 	   	    	}
	   		    	if("" == merchantLoginMobile){
	 	   	    		$("#merchantLoginMobileLab").text("登录手机号不能为空");
	 	   	    		$("#merchantLoginMobile").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#merchantLoginMobileLab").text('');
	 	   	    	}
	   		    	if("" == customerPhone){
	 	   	    		$("#customerPhoneLab").text("客户手机号不能为空");
	 	   	    		$("#customerPhone").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#customerPhoneLab").text('');
	 	   	    	}
	   		    	if("" == merchantLev){
	 	   	    		$("#merchantLevLab").text("商户级别不能为空");
	 	   	    		$("#merchantLev").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#merchantLevLab").text('');
	 	   	    	}
	   		    	if("" == merchantCodeType){
	 	   	    		$("#merchantCodeTypeLab").text("企业代码类型不能为空");
	 	   	    		$("#merchantCodeType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#merchantCodeTypeLab").text('');
	 	   	    	}
	   		    	if("" == representativePhone){
	 	   	    		$("#representativePhoneLab").text("法人手机号不能为空");
	 	   	    		$("#representativePhone").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#representativePhoneLab").text('');
	 	   	    	}
	   		    	if("" == representativeAddr){
	 	   	    		$("#representativeAddrLab").text("法人联系地址不能为空");
	 	   	    		$("#representativeAddr").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#representativeAddrLab").text('');
	 	   	    	}
	   		    	//法人联系省市区
	   		    	if("" == sumpayIdCardLegalType){
	 	   	    		$("#sumpayIdCardLegalTypeLab").text("法人证件类型不能为空");
	 	   	    		$("#sumpayIdCardLegalType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#sumpayIdCardLegalTypeLab").text('');
	 	   	    	}
	 	   	    	if("" == sumpayProfessionType){
	 	   	    		$("#sumpayProfessionTypeLab").text("商户类型不能为空");
	 	   	    		$("#sumpayProfessionType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#sumpayProfessionTypeLab").text('');
	 	   	    	}
		 	   	    if("" == sumpayCycleDays){
	 	   	    		$("#sumpayCycleDaysLab").text("结算周期不能为空");
	 	   	    		$("#sumpayCycleDays").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#sumpayCycleDaysLab").text('');
	 	   	    	}
			 	   	if("" == residentCity){
	 	   	    		$("#residentCityLab").text("结算开户行所在城市不能为空");
	 	   	    		$("#residentCity").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#residentCityLab").text('');
	 	   	    	}
			 	    if("" == sumpayMerSumType){
	 	   	    		$("#sumpayMerSumTypeLab").text("业务类型不能为空");
	 	   	    		$("#sumpayMerSumType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#sumpayMerSumTypeLab").text('');
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
			 	    if("" == sumpayBankNmType){
	 	   	    		$("#sumpayBankNmTypeLab").text("开户行号不能为空");
	 	   	    		$("#sumpayBankNmType").focus();
	 	   	    		return false;
	 	   	    	}else{
	 	   	    		$("#sumpayBankNmTypeLab").text('');
	 	   	    	}
			 	   
  	   				<%-- $.ajax({
  	   					type : "POST",
  	   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>?merchantCode='+merchantCode,
  	   					data :{
  	   						shopInterior : $('#shopInteriortemp').val(),
  	   						open : $('#opentemp').val(),
	   						businessPhoto : $('#businessPhototemp').val(),
	   						certAttribute1 : $('#certAttribute1temp').val(),
	   						certAttribute2 : $('#certAttribute2temp').val(),
	   						doorPhoto : imgDoor,
	   						doorSrc : imgSrc	
  	   					},
  	   					dataType : "json",
  	   					success : function(data) {
  	   						if(data.result=='SUCCESS')		 --%>					
  	   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYINFOMERCHANTREPORT %>",
  	   							{
  	   							"channelNo" : channelNo,
  								"merchantCode" : merchantCode,
  								"custName" : custName,
  								"businessLicense" : businessLicense,
  								"cprRegAddr" : cprRegAddr,
  								"province" : province,
  								"city" : city,
  								"country" : country,
  								"interName" : interName,
  								"certifyNo" : certifyNo,
  								"perEntFlag" : perEntFlag,
  								"bankCode" : bankCode,
  								"interBankCode" : interBankCode,
  								"interBankName" : interBankName,
  								"mobile" : mobile,
  								"attentionName" : attentionName,
  								"attentionMobile" : attentionMobile,
  								"attentionEmail" : attentionEmail,
  								"merchantLoginEmail" : merchantLoginEmail,
  								"merchantLoginMobile" : merchantLoginMobile,
  								"customerPhone" : customerPhone,
  								"merchantLev" : merchantLev,
  								"merchantCodeType" : merchantCodeType,
  								"representativePhone" : representativePhone,
  								"representativeAddr" : representativeAddr,
  								"provinceAdd" : provinceAdd,
  								"cityAdd" : cityAdd,
  								"countryAdd" : countryAdd,
  								"sumpayIdCardLegalType" : sumpayIdCardLegalType,
  								"sumpayProfessionType" : sumpayProfessionType,
  								"sumpayCycleDays" : sumpayCycleDays,
  								"residentCity" : residentCity,
  								"sumpayMerSumType" : sumpayMerSumType,
  								"accountNm" : accountNm,
  								"accountNo" : accountNo,
  								"sumpayBankNmType" : sumpayBankNmType
  	   								
  	   							},
  	   							function(data){
  	   								if(data.result=="SUCCESS"){
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
  	   									if(null == data.message || "" ==data.message){
											alert("进件失败");
  	  	   								}else{
  	  	   									alert(data.message);
  	  	  	   							}
  	   								}
  	   							},'json'
  	   						  );	
  	   					/* }
  	   				}); */
  	  			}
   			
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