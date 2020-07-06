<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
$(function(){
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
		            var imgObj = $('#'+_this.attr('id')+'ImageDiv');
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

		            //优图
		            var param = "{str:\""+base64_string+"\",flag:\""+_this.attr('id')+"\"}"
		    		$.ajax({
	    	   			async:false,
	    	   			type:"POST",
	    	   			contentType:"application/json;charset=utf-8",
	    	   			dataType:"text",
	    	   			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.YOUTU%>',
	    	   	        data:param,
	    	   	        success:function(data){
	    	   	      		var json = eval('(' + data + ')');
	    	   	        	if(json.result=="SUCCESS"){
	    	   	        		 if(_this.attr('id')=="legalCertAttribute1"){                    //身份证
	    	       	  				$("#representativeName").val(json.cardName);
	    	       	  				$("#representativeCertNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="businessPhoto"){                     //营业执照
	    	       	  				$("#registCode").val(json.businessLicense);
	    	       	  				$("#businessEffectiveTerm").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("#businessTerm").val("长期");
	    	       	  				}else{
	    	       	  					$("#businessTerm").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#cprRegAddr").val(json.legalAddress);
	    	       	  				$("#cprRegNmCn").val(json.companyName);
	    	       	  				$("#representativeName").val(json.legalPerson);
	    	       	  			
	    	       	  			}
	    	   				}
	    	   			}
		    	   	});
				}
			});
		}
	);

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
					<input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="SUIXING_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="openPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="legalCertAttribute1temp" />
					<input type="hidden" id="legalCertAttribute2temp" />
					<input type="hidden" id="settleCertAttribute1temp" />
					<input type="hidden" id="settleCertAttribute2temp" />
					<input type="hidden" id="bankCardPhototemp"/>
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
								<td class="td-left" width="18%">商户编号：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly placeholder="请输入商户编号" maxlength=""  value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" placeholder="请输入商户简称" maxlength=""  value="${custInfo.shortName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select  name="mecTypeFlag" id="mecTypeFlag"  onchange = "getMecType();" class="width-90" >
										<option value="00">--普通商户--</option>
										<option value="01">--连锁总店--</option>
										<option value="02">--连锁分店--</option>
										<option value="03">--1+n总店--</option>
										<option value="04">--1+n分店--</option>
									</select>
									<label class="label-tips" id="mecTypeFlagLab"></label>	
								</td>
                                <td class="td-left">商户行业信息：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
								<select name="category" id="category" onchange ="getIndustryName()" data-width="125px" data-live-search="true">
									<option value="">--请选择商户类目--</option>
									<c:if test="${not empty industryList }">
			                           	<c:forEach items="${industryList }" var="industry">
			                           		<option id="${industry.categoryId}" value="${industry.categoryId}">${industry.categoryName}</option>
			                           	</c:forEach>
		                 		  	</c:if>
								</select> 
								<select   id="industry"  name="industry" >
						           <option value="" id="industryDef">--请选择商户行业--</option>        
						        </select>
								<%-- <select name="" id="industry" class="selectpicker show-tick form-control" data-width="125px" data-live-search="true">
									<option value="">--请选择商户行业--</option>
									<c:if test="${not empty industryList }">
			                           	<c:forEach items="${industryList }" var="industry">
			                           		<option id="${industry.industryCode}" value="${industry.industryCode}">${industry.industryName}</option>
			                           	</c:forEach>
		                 		  	</c:if>
								</select> --%>
								</td>
							</tr>
							<tr id="mecTypeFlagType" style = "display:none">
								<td class="td-left" width="18%">所属总店商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="parentMno" name="parentMno" placeholder="请输入所属总店商户编号（对应随行付商户号）" value="" style="width:90%">
								</td>
								<td class="td-left">分店是否独立结算：</td>
								<td class="td-right"> 
								    <select  name="independentModel" id="independentModel" class="width-90">
										<option value="">--结算方式--</option>
										<option value="01"> 独立结算</option>
										<option value="02">统一结算</option>
									</select>
									<label class="label-tips" id="suiXingMerchantLab"></label>
								</td>
							</tr>
	                        <tr>
							    <td class="td-left">是否有证商户：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								    <select  name="suiXingMerchantType" id="suiXingMerchantType" onchange = "getUpdateType()"class="width-90">
										<option value="01">有证商户</option>
										<option value="02">无证商户</option>
									</select>
									<label class="label-tips" id="suiXingMerchantLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="merchantProvince" id="merchantProvince" onchange="getMerchantCity()">
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
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
		                                <select class="form-control" name="merchantCity" id="merchantCity"  onchange="getMerchantArea()">
		                                    <option value="" id="cityDef">--请选择市--</option>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
		                                <select class="form-control" name="merchantArea" id="merchantArea" >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
						 	<tr id="businessPhotoType1" style = "display:">
								<td class="td-left">营业执照名称：</td>
								<td class="td-right">
									<input type="text" name="cprRegNmCn" id="cprRegNmCn"  placeholder="请输入营业名称"  value="" style="width:90%">
								</td>
								<td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
									<td class="td-right">
										<input type="text" name="businessEffectiveTerm" id="businessEffectiveTerm" value="${custInfo.businessTermStart }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
	                                    <input type="text" name="businessTerm" id="businessTerm" value="${custInfo.businessTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
	                                    <input type="button" onclick="businessForever()" value="长期" />
									</td>
							</tr>
							<tr id="businessPhotoType2" style = "display:">
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="registCode" id="registCode"  placeholder="请输入营业编号"  value="${custInfo.businessLicense }" style="width:90%">
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
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" value ="${picturePathVo.bussinessPath }"/>
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="doorPhotoPath" name="doorPhotoPath" />
										<input type="hidden" id="doorPhotoImageVal02"  />
										<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="shopInteriorPath" name="shopInteriorPath" />
										<input type="hidden" id="shopInteriorImageVal02"  />
										<input type="file" name="shopInterior" id="shopInterior" onchange="showShopInteriorImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="shopCheckStandType" style = "display:">
								<td class="td-left">店内前台照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopCheckStandClick" data-target="#previewImageModal" >
										<label id="shopCheckStandDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="shopCheckStandPath" name="shopCheckStandPath" />
										<input type="hidden" id="shopCheckStandImageVal02"  />
										<input type="file" name="shopCheckStand" id="shopCheckStand" onchange="showShopCheckStandImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="qualificationType" style = "display:">
								<td class="td-left">特殊资质照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success qualificationClick" data-target="#previewImageModal" >
										<label id="qualificationDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.qualificationPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="qualificationPath" name="qualificationPath" />
										<input type="hidden" id="qualificationImageVal02"  />
										<input type="file" name="qualification" id="qualification" onchange="showQualificationImage(this)" />
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
									<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  value="${custInfo.mobile }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人证件类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" style="width:90%;"  >
										<option value="00">--身份证--</option>
										<option value="03">--军人证--</option>
										<option value="04">--警察证--</option>
										<option value="05">--港澳居民往来内地通行证--</option>
										<option value="06">--台湾居民来往大陆通行证--</option>
										<option value="07">--护照--</option>
										<option value="98">--单位证件--</option>
										<option value="06">--其他证件--</option>
									</select>
								</td>
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
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
									<div style="float:left;margin-top:75" >
								    	<input  type="hidden" id="legalCertAttribute1Path" name="legalCertAttribute1Path" />
										<input type="file" name="legalCertAttribute1" id="legalCertAttribute1" onChange="showlegalCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div style="float:left;margin-top:75" >
								    	<input  type="hidden" id="legalCertAttribute2Path" name="legalCertAttribute2Path" />
										<input type="file" name="legalCertAttribute2" id="legalCertAttribute2" onChange="showlegalCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="actNm" name="actNm" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="bankProvince" id="bankProvince" class="width-90" onchange="getCity();">
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
								</td>
								<td class="td-left">开户城市：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="bankCity" id="bankCity" class="width-90" >
	                                    <option value="">--请选择市--</option>
	                                </select>
	                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								<select  name="suiXinBank" id="suiXinBank"  class="selectpicker show-tick form-control" data-width="91%" data-live-search="true" onchange="getbranchBank();">
										<option value="">--请选择--</option>
										<c:forEach items="<%=SuixinBankType.values()%>" var="status">
											<option value="${status.code}" <c:if test="${status == queryBean.code}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
								</td>
	                            <td class="td-left">开户支行<span style="color:red;">(必填)</span></td>
	                            <td class="td-right"> 
	                                  <div class="layui-input-inline layui-form" style="width:94%">
									        <select   id="interBankName"  name="interBankName" lay-verify="required" lay-search="" lay-filter="interBankName">
									          <option value="">直接选择或搜索选择</option>        
									        </select>
									  </div>
	                               	<label id="interBankNameLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<select name="actType" id="actType" style="width-90" onchange = "getUpdateType1();" >
										<option value="">--请选择--</option>
										<option value="00">--对公--</option>
										<option value="01">--对私--</option>
									</select> 
								</td>
                                <td class="td-left">结算费率：<span style="color:red;">(必填)</span></td>
							  <td class="td-right"> 
									<input type="text" id="rate" name="rate" placeholder="请输入结算费率"  value="" style="width:90%"> %
							  </td>
							</tr>
                            <tr>
                                <td class="td-left">账户人身份证号码：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
									<input type="text" id="certifyNo" name="certifyNo" placeholder="请输入账户人身份证号码"  value="" style="width:90%">
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="letterOfAuthPath" name="letterOfAuthPath" />
										<input type="file" name="letterOfAuth" id="letterOfAuth" onChange="showLetterOfAuthImage(this)"/> 
										<p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="openAccountPath" name="openAccountPath" />
										<input type="file" name="openAccount" id="openAccount" onChange="showOpenAccountImage(this)"/> 
										<p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="bankCardPhotoPath" name="bankCardPhotoPath" />
										<input type="hidden" id="bankCardPhotoImageVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="settleCertAttribute1Path" name="settleCertAttribute1Path" />
										<input type="hidden" id="settleCertAttribute1ImageVal02"  />  
										<input type="file" name="settleCertAttribute1" id="settleCertAttribute1" onChange="showSettleCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="settleCertAttribute2Path" name="settleCertAttribute2Path" />
										<input type="hidden" id="settleCertAttribute2ImageVal02"  />  
										<input type="file" name="settleCertAttribute2" id="settleCertAttribute2" onChange="showSettleCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
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

		/*
		*
		* file input file dom object
		* pathTarget 路径保存的input元素id 
		* preView 图片预览dom id
		*/
		function commonFileUpload(file, pathTarget, preView) {
			var formdata=new FormData();
		    formdata.append("file",$(file).get(0).files[0]);
		    $.ajax({
		        url:'/common/files/uploadPic',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#' + pathTarget + '').val(info.imagePath);
		        },
		        error:function(err){
		            console.log(err)
		        }
		    });
		
		    var prevDiv = document.getElementById(preView);
		    if (file.files && file.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (evt) {
		        	prevDiv.innerHTML = '<img src="' + evt.target.result + '" onclick="bigImg(this);" style="width:120px;height:100px;" />';
		        }
		        reader.readAsDataURL(file.files[0]);
		    } else {
		        prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		    }
		}
		
		//营业执照上传
		function showBusinessPhotoImage(file){
			commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
		}
		
		//门头上传
		function showDoorPhotoImage(file){
			commonFileUpload(file, 'doorPhotoPath', 'doorPhotoDiv');
		}
		
		//店内照
		function showShopInteriorImage(file){
			commonFileUpload(file, 'shopInteriorPath', 'shopInteriorDiv');
		}
		
		//店内前台照
		function showShopCheckStandImage(file){
			commonFileUpload(file, 'shopCheckStandPath', 'shopCheckStandDiv');
		}
				
		//特殊资质照
		function showQualificationImage(file){
			commonFileUpload(file, 'qualificationPath', 'qualificationDiv');
		}
		//法人身份证正面
		function showlegalCertAttribute1Image(file){
			commonFileUpload(file, 'legalCertAttribute1Path', 'legalCertAttribute1Div');
		}
		
		//法人身份证反面
		function showlegalCertAttribute2Image(file){
			commonFileUpload(file, 'legalCertAttribute2Path', 'legalCertAttribute2Div');
		} 
		
		//上传银行卡照
		function showBankCardPhotoImage(file){
			commonFileUpload(file, 'bankCardPhotoPath', 'bankCardPhotoDiv');
		}
		
		//上传开户许可证
		function showOpenAccountImage(file){
			commonFileUpload(file, 'openAccountPath', 'openAccountDiv');
		}
		
		//上传非法人结算授权函
		function showLetterOfAuthImage(file){
			commonFileUpload(file, 'letterOfAuthPath', 'letterOfAuthDiv');
		}
		
		//结算人身份证正面
		function showSettleCertAttribute1Image(file){
			commonFileUpload(file, 'settleCertAttribute1Path', 'settleCertAttribute1Div');
		}
		
		//结算人身份证反面
		function showSettleCertAttribute2Image(file){
			commonFileUpload(file, 'settleCertAttribute2Path', 'settleCertAttribute2Div');
		} 
		
        /** 点击预览大图 **/
      	function bigImg(obj){
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

      	/** 店内点击预览 **/
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
      	
      	/** 特殊资质照点击预览 **/
      	$('.qualificationClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("qualification");
      		return previewImage(divObj,imageObj,obj); 
      	});
      	
      	/** 非法人结算授权函 **/
      	$('.letterOfAuthClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("letterOfAuth");
      		return previewImage(divObj,imageObj,obj); 
      	});
      	
        
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
		
      	function getbranchBank(){
      		var city = $("#bankCity").val().trim();
      		var suiXinBank = $("#suiXinBank").val();
      		
      		var channelCode ="SUIXING_PAY";
      		$.post(window.Constants.ContextPath +"/common/info/bankCnapsInfo",
    		{
      			"cityCode":city,
    			"bankCode": suiXinBank,
    			"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var branchBankList = data.branchBankList;
    				$("#interBankName").html("");
           			for ( var branchBank in branchBankList) {
           				$("#interBankName").append(
           						"<option value='"+ branchBankList[branchBank].branchBankCode +"'>"
           								+ branchBankList[branchBank].bankName + "</option>"); 
           			}
           			layui.use('form', function (){
           				var form = layui.form; 
           				
           				form.render();
           			 });  
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
      	function getIndustryName(){
      		var categoryId = $("#category").val();
      		$.post(window.Constants.ContextPath +"/common/info/getIndustryNameByCategoryId",
    		{
      			"categoryId":categoryId
    		},
    		function(res){
    			if(res.result=="SUCCESS"){
    				var industryListByCategoryId = res.data;
    				$("#industry").html("");
           			for ( var industryList2 in industryListByCategoryId) {
           				$("#industry").append(
           						"<option value='"+ industryListByCategoryId[industryList2].industryCode +"'>"
           								+ industryListByCategoryId[industryList2].industryName + "</option>"); 
           			}
           			layui.use('form', function (){
           				var form = layui.form; 
           				
           				form.render();
           			 });  
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

      	function getUpdateType(){
			var suiXingMerchantType = $("#suiXingMerchantType").val();
			
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
			
      	}

      	function getUpdateType1(){
      		var actType = $("#actType").val();
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

      	}
      	
      	function getMecType(){
      		var mecTypeFlag = $("#mecTypeFlag").val()
      		if("02" == mecTypeFlag || "04" == mecTypeFlag){
      			$("#mecTypeFlagType").attr("style","display:");
      		}else{
      			$("#mecTypeFlagType").attr("style","display:none");
      		}
      	}
      	
      	function businessForever(){
			$("input[name='businessTerm']").val("长期");
			$("#businessTerm").attr("value","长期");
		}
      	
	   	function exit() {
	   		if (confirm("您确定要关闭吗？")) {
	   			window.opener=null;
	   		
	   			window.open("","_self");
	   		
	   			window.close();
	   		}
	   	};
    	
    /* 校验渠道 */
    $(function(){
    	//判定是新进件还是更新进件
		var status = $("#status").val();
		var custId = $("#custId").val();
		var authId = $("#authId").val();
		if("" ==custId){
			alert(status);
		}else{
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
		}  
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
   			if("" == suiXingMerchantType){
   	    		$("#suiXingMerchantLab").text("资质类型不能为空");
   	    		return false;
   	    	}else{
   	    		$("#suiXingMerchantLab").text('');
   	    	}
   	    	//所属总店商户编号
   			var parentMno = $("#parentMno").val();
   	    	//分店是否独立结算
   			var	independentModel = $("#independentModel").val();
   	    	
   			var openAccountPath =  $("#openAccountPath").val();
   			var letterOfAuthPath = $("#letterOfAuthPath").val();
   	    	var bankCardPhotoPath = $("#bankCardPhotoPath").val();
   	    	var certAttribute2Path = $("#legalCertAttribute2Path").val();
   	    	var certAttribute1Path = $("#legalCertAttribute1Path").val();
   	    	var businessPhotoPath = $("#businessPhotoPath").val();
   	    	var doorPhotoPath = $("#doorPhotoPath").val();
   	    	var shopInteriorPath = $("#shopInteriorPath").val();
   	    	var shopCheckStandPath = $("#shopCheckStandPath").val();
   	    	var settleCertAttribute1Path = $("#settleCertAttribute1Path").val();
   	    	var settleCertAttribute2Path = $("#settleCertAttribute2Path").val();
   	    	var qualificationPath = $("#qualificationPath").val();
   			//上传照片
			var imgDoor = [];
			var imgSrc = [];
			var merchantData = new FormData(document.getElementById('merchantForm'));	
			 $.ajax({
		            type : "POST",
		            url : window.Constants.ContextPath +'<%="/common/files/getPicPath"%>?custId='+custId,
		            data :{
		            	"openAccountPath"          : openAccountPath,                //开户许可证
		            	"letterOfAuthPath"         : letterOfAuthPath,               //非法人结算授权函
		            	"idCardOPath"              : certAttribute1Path,             //身份证正面照
		            	"idCardFPath"              : certAttribute2Path,             //身份证背面照
		            	"bussinessPath"            : businessPhotoPath,              //商户营业执照
		            	"bankCardPath"             : bankCardPhotoPath,              //开户银行照
						"doorPhotoPath"            : doorPhotoPath,					 //门头照
						"shopInteriorPath"         : shopInteriorPath,               //店内照
						"shopCheckStandPath"       : shopCheckStandPath,             //店内前台照
						"settleCertAttribute1Path" : settleCertAttribute1Path,		 //结算人身份证正面
						"settleCertAttribute2Path" : settleCertAttribute2Path,		 //结算人身份证反面
						"qualificationPath"        : qualificationPath		         //特殊资质照
		            },
		            dataType : "json",
		            success : function(data) {
		                if(data.result=='SUCCESS'){
		                	
		                }
		            }
			 })
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?status='+status,
				data :merchantData,
                dataType : "json",
                cache: false,
                processData: false,
                contentType: false,
				success : function(data){
				if(data.result=="SUCCESS"){
					if("" != data.message){
						taskCode = data.message;
						//随行付渠道
			   			if("SUIXING_PAY" == channelNo){

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
					 	   	if("01" == actType){
						 	   	if("" == certifyNo){
				 	   	    		$("#certifyNoLabel").text("结算身份证号不能为空");
				 	   	    	    $("#certifyNo").focus();
				 	   	    		return false;
				 	   	    	}else{
				 	   	    		$("#certifyNoLabel").text("");
				 	   	    	} 
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
		  	   						parentMno :  parentMno,
		  	   						independentModel : independentModel,
		  	   						taskCode : taskCode
		  	   					},
		  	   					dataType : "json",
		  	   					success : function(data) {
		  	   						if(data.result=='SUCCESS'){							
		  	   							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
											},function(){
												this.location.reload();
											}
										);
									}else{
										alert(data.message);
									}
		  	   					}
		  	   				});
		   			
			   	 		}
					}else{
						alert("上传图片返回码异常");
					}
					
				}else{
					if("" == data.message || null == data.message){
						alert("失败");
					}else{
						alert("上传图片失败 ");
					}
				}
			},
			error : function(){
				alert("上传失败");
			},
			})
    		
    	});

    	
   	});  
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
 
  //表单赋值
 /*  layui.$('#LAY-component-form-setval').on('click', function(){
    form.val('example', {
      "username": "贤心" // "name": "value"
      ,"password": "123456"
      ,"interest": 1
      ,"like[write]": true //复选框选中状态
      ,"close": true //开关状态
      ,"sex": "女"
      ,"desc": "我爱 layui"
    });
  }); */
  
  //表单取值
  layui.$('#LAY-component-form-getval').on('click', function(){
    var data = form.val('example');
    alert(JSON.stringify(data));
  }); 
  
});
</script>
</body>
</html>