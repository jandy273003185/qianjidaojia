<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatBankType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatEntRateIdType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatPerRateIdType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatPicRateIdType"%>
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
	<title>微信升级进件</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
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
	    	       	  					$("#businessTerm").val("2099-12-31");
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
					<input type="hidden" id="channelCode" name="channelCode" value="WX"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="patchNo" name="patchNo" value="${tdMerchantDetailInfo.patchNo }"/>
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
									<input type="text" id="merchantCode" name="merchantCode" readonly value="${custInfo.merchantCode }" style="width:90%">
									<label class="label-tips" id="merchantCodeLab"></label>
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" value="${custInfo.custName }" style="width:90%">
									<label class="label-tips" id="custNameLab"></label>
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">微信小微商户号：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="outMerchantCode" name="outMerchantCode" value="${tdMerchantDetailInfo.outMerchantCode }" style="width:90%">
									<label class="label-tips" id="outMerchantCodeLab"></label>
								</td>
								<td class="td-left">商户简称：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" id="shortName" name="shortName" value="${custInfo.shortName }" style="width:90%">
									<label class="label-tips" id="shortNameLab"></label>
								</td>
							</tr>
	                        <tr>
	                        	<td class="td-left">主体类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select name="merchantProperty" id="merchantProperty" class="width-90" onchange = "getMerchantProperty();" >
								   		<option value="">--主体类型--</option>
										<option value="2">企业</option>
										<option value="4">个体工商户</option>
										<option value="3">党政、机关及事业单位 </option>
										<option value="1708">其他组织</option>
									</select>	
									<label class="label-tips" id="merchantPropertyLab"></label>
								</td>
								<td class="td-left">经营场景：<span style="color:red;">(必填)</span></td>
							    <td class="td-right">
							        <input type="checkbox" name="businessScene" value="1721">线下&nbsp;  
							        <input type="checkbox" name="businessScene" value="1837">公众号 &nbsp; 
							        <input type="checkbox" name="businessScene" value="1838">小程序  &nbsp;  
							        <input type="checkbox" name="businessScene" value="1724">APP &nbsp; 
							        <input type="checkbox" name="businessScene" value="1840">PC网站 &nbsp;
							    </td>
							    <label class="label-tips" id="businessSceneLab"></label>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="merchantProvince" id="merchantProvince" onchange="getMerchantCity();">
	                                       	<option value="">--请选择省--</option>
		                                    <c:if test="${not empty weChatAppAreaInfoList }">
		                                        <c:forEach items="${weChatAppAreaInfoList }" var="merchantProvince">
		                                            <option id="${merchantProvince.provinceName}"
		                                                    value="${merchantProvince.provinceName}">
		                                                ${merchantProvince.provinceName}
		                                            </option>
		                                        </c:forEach>
		                                    </c:if>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
		                                <select class="form-control" name="merchantCity" id="merchantCity"  onchange="getMerchantArea();">
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
							<tr>
							    <td class="td-left">营业执照编号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="businessLicense" name="businessLicense" placeholder="请输入营业执照" maxlength=""  value="${custInfo.businessLicense }" style="width:90%">
									<label id="businessLicenseLab" class="label-tips"></label>	
								</td>
                                <td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<input type="text" name="businessEffectiveTerm" id="businessEffectiveTerm" value="${custInfo.businessTermStart }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
                                    <input type="text" name="businessTerm" id="businessTerm" value="${custInfo.businessTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                    <input type="button" onclick="businessForever()" value="长期" />
									<label id="businessTermLab" class="label-tips"></label>	
								</td>
							</tr>
							<tr id="businessPhotoType" style = "display:">
								<td class="td-left">营业执照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal"  >
										<label id="businessPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="businessPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" />
										<input type="hidden" id="businessPhotoImageVal02"  />  
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bussinessPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" />
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										<label id="businessPhotoPathLab" class="label-tips"></label>	
									</div>
								</td>
							</tr>
							<tr id="qualificationType" style = "display:">
								<td class="td-left">特殊行业资质照：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success qualificationClick"  data-target="#previewImageModal"  >
										<label id="qualificationDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="qualificationImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="qualificationPath" name="qualificationPath" />
										<input type="hidden" id="qualificationImageVal02"  />  
										<input type="file" name="qualification" id="qualification" onchange="showQualification(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									<a data-toggle='modal' class="tooltip-success qualificationClick" data-target="#previewImageModal" >
										<label id="qualificationDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.qualificationPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="qualificationPath" name="qualificationPath" />
										<input type="hidden" id="qualificationImageVal02"  />
										<input type="file" name="qualification" id="qualification" onchange="showQualificationImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="mpAppScreenShotsType" style = "display:">
								<td class="td-left">公众号APPID：</td>
								<td class="td-right"> 
									<input type="text" id="mpAppid" name="mpAppid" maxlength="100" placeholder="请输入公众号APPID"  value="" style="width:90%">
								</td>
								<td class="td-left">公众号页面截图：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success mpAppScreenShotsClick" data-target="#previewImageModal" >
										<label id="mpAppScreenShotsDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.mpAppScreenShotsPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="mpAppScreenShotsPath" name="mpAppScreenShotsPath" />
										<input type="hidden" id="mpAppScreenShotsImageVal02"  />
										<input type="file" name="mpAppScreenShots" id="mpAppScreenShots" onchange="showMpAppScreenShotsImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="miniprogramAppidType" style = "display:">
								<td class="td-left">小程序APPID：</td>
								<td class="td-right"> 
									<input type="text" id="miniprogramAppid" name="miniprogramAppid" maxlength="100" placeholder="请输入小程序APPID"  value="" style="width:90%">
									<label class="label-tips" id="miniprogramAppidLab"></label>
								</td>
								<td class="td-left">小程序页面截图：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success miniprogramAppidClick" data-target="#previewImageModal" >
										<label id="miniprogramAppidDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.miniprogramAppidPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="miniprogramAppidPath" name="miniprogramAppidPath" />
										<input type="hidden" id="miniprogramAppidImageVal02"  />
										<input type="file" name="miniprogramAppid" id="miniprogramAppid" onchange="showMiniprogramAppidImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="appAppidType1" style = "display:">
								<td class="td-left">应用APPID：</td>
								<td class="td-right"> 
									<input type="text" id="appAppid" name="appAppid" maxlength="100" placeholder="请输入应用APPID"  value="" style="width:90%">
									<label class="label-tips" id="appAppidLab"></label>
								</td>
								<td class="td-left">APP下载链接：</td>
								<td class="td-right"> 
									<input type="text" id="appDownloadUrl" name="appDownloadUrl" maxlength="100" placeholder="请输入APP下载链接"  value="" style="width:90%">
									<label class="label-tips" id="appDownloadUrlLab"></label>
								</td>
							</tr>
							<tr id="appAppidType" style = "display:">
								<td class="td-left">APP截图：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success appAppidClick" data-target="#previewImageModal" >
										<label id="appAppidDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.appAppidPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="appAppidPath" name="appAppidPath" />
										<input type="hidden" id="appAppidImageVal02"  />
										<input type="file" name="appAppid" id="appAppid" onchange="showAppAppidImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="webUrlType1" style = "display:">
								<td class="td-left">PC网站域名：</td>
								<td class="td-right"> 
									<input type="text" id="webUrl" name="webUrl" maxlength="100" placeholder="请输入PC网站域名"  value="" style="width:90%">
									<label class="label-tips" id="webUrlLab"></label>
								</td>
								<td class="td-left">PC网站对应的公众号APPID：</td>
								<td class="td-right"> 
									<input type="text" id="webAppid" name="webAppid" maxlength="100" placeholder="请输入PC网站对应的公众号APPID"  value="" style="width:90%">
									<label class="label-tips" id="webAppidLab"></label>
								</td>
							</tr>
							<tr id="webUrlType" style = "display:">
								<td class="td-left">网站授权函：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success webUrlClick" data-target="#previewImageModal" >
										<label id="webUrlDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.webUrlPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="webUrlPath" name="webUrlPath" />
										<input type="hidden" id="webUrlImageVal02"  />
										<input type="file" name="webUrl" id="webUrl" onchange="showWebUrlImage(this)" />
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
									<label class="label-tips" id="representativeNameLab"></label>
								</td>
								<td class="td-left">手机号码：</td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  value="${custInfo.mobile }" style="width:90%">
									<label class="label-tips" id="mobileNoLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">联系邮箱：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" id="merchantEmail" name="merchantEmail" value="${custInfo.merchantEmail }" style="width:90%">
									<label class="label-tips" id="merchantEmailLab"></label>
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="actNm" name="actNm" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
									<label class="label-tips" id="actNmLab"></label>
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width:90%">
									<label class="label-tips" id="bankCardNoLab"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="bankProvince" id="bankProvince" class="width-90" onchange="getCity();">
	                                    <option value="">--请选择省--</option>
	                                    <c:if test="${not empty weChatAppAreaInfoList }">
	                                        <c:forEach items="${weChatAppAreaInfoList }" var="province">
	                                            <option id="${province.provinceName}" value="${province.provinceName}">
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
									<select name="weChatBank" id="weChatBank" style="width-90;">
										<option value="">--请选择--</option>
										<c:forEach items="<%=WeChatBankType.values()%>" var="status">
											<option value="${status.name}" <c:if test="${status == queryBean.name}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
								</td>
	                            <td class="td-left">开户支行<span style="color:red;">(必填)</span></td>
	                            <td class="td-right">
	                            	<input type="text" id="interBankName" name="interBankName" maxlength="100" placeholder="请输入支行名称"  value="" style="width:90%"> 
	                               	<label id="interBankNameLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr id="weChatEntRateIdType_1" style = "display:">
                                <td class="td-left">企业结算费率：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
							  		<select id="rateEnt" name="rateEnt" >
							  			<option value="">--请选择--</option>
										<c:forEach items="<%=WeChatEntRateIdType.values()%>" var="status">
											<option value="${status.code}" <c:if test="${status == queryBean.code}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
							  	</td>
							</tr>
							<tr id="weChatPerRateIdType_1" style = "display:none">
                                <td class="td-left">个体户结算费率：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
							  		<select id="ratePer" name="ratePer" >
							  			<option value="">--请选择--</option>
										<c:forEach items="<%=WeChatPerRateIdType.values()%>" var="status">
											<option value="${status.code}" <c:if test="${status == queryBean.code}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
							  	</td>
							</tr>
							<tr id="weChatPicRateIdType_1" style = "display:none">
                                <td class="td-left">党政及其他组织费率：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
							  		<select id="ratePic" name="ratePic" >
							  			<option value="">--请选择--</option>
										<c:forEach items="<%=WeChatPicRateIdType.values()%>" var="status">
											<option value="${status.code}" <c:if test="${status == queryBean.code}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
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

		//关闭
		function exit() {
			if (confirm("您确定要关闭吗？")) {
				window.opener=null;
				window.open("","_self");
				window.close();
			}
		};
		
		//根据主体类型判断结算费率类型
		function getMerchantProperty(){
			var merchantProperty = $("#merchantProperty").val();
			if("2" == merchantProperty){
				$("#weChatEntRateIdType_1").attr("style","display:");
				$("#weChatPerRateIdType_1").attr("style","display:none");
				$("#weChatPicRateIdType_1").attr("style","display:none");
			}else if("4" == merchantProperty){
				$("#weChatEntRateIdType_1").attr("style","display:none");
				$("#weChatPerRateIdType_1").attr("style","display:");
				$("#weChatPicRateIdType_1").attr("style","display:none");
			}else{
				$("#weChatEntRateIdType_1").attr("style","display:none");
				$("#weChatPerRateIdType_1").attr("style","display:none");
				$("#weChatPicRateIdType_1").attr("style","display:");
			}
		}
		
		//长期
		function identityForever(){
			$("input[name='identityValDate']").val("长期");
			$("#identityValDate").attr("value","2099-12-31");
			alert($("#identityValDate").val());
		}
		
		/***根据省份获取城市***/
		function getMerchantCity(){
			var province = $("#merchantProvince").val().trim();
			var channelCode =$("#channelCode").val();
			$.post(window.Constants.ContextPath +"/common/info/getCityInfo",
			{
				"provinceId":province,
				"channelCode":channelCode
			},
			function(data){
				if(data.result=="SUCCESS"){
					var cityList = data.cityList;
					$("#merchantCity").html("");
		   			for ( var city in cityList) {
		   				$("#merchantCity").append(
		   						"<option value='"+ cityList[city].cityName +"'>"
		   								+ cityList[city].cityName + "</option>"); 
		   			}
				}else{
					alert("城市不能为空");
				}
			},'json'
			);	
		}
		
      	/***根据城市获取注册地县区***/
      	function getMerchantArea(){
      		var merchantCityName = $("#merchantCity").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getAreaInfo",
    		{
    			"cityName":merchantCityName,
    			"channelCode":channelCode
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
      	
      	/***根据开户省份获取开户城市***/
      	function getCity(){
			var province = $("#bankProvince").val().trim();
			var channelCode =$("#channelCode").val();
			$.post(window.Constants.ContextPath +"/common/info/getCityInfo",
			{
				"provinceId":province,
				"channelCode":channelCode
			},
			function(data){
				if(data.result=="SUCCESS"){
					var cityList = data.cityList;
					$("#bankCity").html("");
		   			for ( var city in cityList) {
		   				$("#bankCity").append(
		   						"<option value='"+ cityList[city].areaId +"'>"
		   								+ cityList[city].cityName + "</option>"); 
		   			}
				}else{
					alert("城市不能为空");
				}
			},'json'
			);	
		}
      	
      	//上传图片
		function commonFileUpload(file, pathTarget, preView) {
			var formdata=new FormData();
		    formdata.append("file",$(file).get(0).files[0]);
		    $.ajax({
		        url:'/common/files/upload',
		        type:'post',
		        contentType:false,
		        data:formdata,
		        processData:false,
		        async:false,
		        success:function(info){ 
		             $('#' + pathTarget + '').val(info.path);
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
      	
		//上传特殊行业资质照
		function showQualificationImage(file){
			commonFileUpload(file, 'qualificationPath', 'qualificationDiv');
		}
	    //营业执照上传
		function showBusinessPhotoImage(file){
			commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
		}
	    //公众号页面截图
		function showMpAppScreenShotsImage(file){
			commonFileUpload(file, 'mpAppScreenShotsPath', 'mpAppScreenShotsDiv');
	    }
		//小程序页面截图
		function showMiniprogramAppidImage(file){
			commonFileUpload(file, 'miniprogramAppidPath', 'miniprogramAppidDiv');
	    }
		//APP截图
		function showAppAppidImage(file){
			commonFileUpload(file, 'appAppidPath', 'appAppidDiv');
	    }
		//网站授权函
		function showWebUrlImage(file){
			commonFileUpload(file, 'webUrlPath', 'webUrlDiv');
	    }
		
      	/** 点击预览大图 **/
      	function bigImg(obj){
            var realWidth;
        	var realHeight;
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
    	
    	$("#submitData").click(function(){
    		
   			//  渠道
   			var channelNo = $("#channelCode").val();
   			//  批次号
   			var patchNo = $("#patchNo").val();
   			//  客户号
   			var custId = $("#custId").val();
			//  商户号
   			var merchantCode = $("#merchantCode").val();
   			if("" == merchantCode){
   	    		$("#merchantCodeLab").text("商户编号不能为空");
   	    		$("#merchantCode").focus();
   	    		return false;
   	    	}else{
   	    		$("#merchantCodeLab").text('');
   	    	}
   			
			//  商户名称
   			var custName = $("#custName").val();
   			if("" == custName){
   	    		$("#custNameLab").text("商户名称不能为空");
   	    		$("#custName").focus();
   	    		return false;
   	    	}else{
   	    		$("#custNameLab").text('');
   	    	}
   			
			//  微信小微商户号
			var outMerchantCode = $("#outMerchantCode").val();
			if("" == outMerchantCode){
   	    		$("#outMerchantCodeLab").text("微信小微商户号不能为空");
   	    		$("#outMerchantCode").focus();
   	    		return false;
   	    	}else{
   	    		$("#outMerchantCodeLab").text('');
   	    	}
			
			//  商户简称
			var shortName = $("#shortName").val();
			if("" == shortName){
   	    		$("#shortNameLab").text("商户简称不能为空");
   	    		$("#shortName").focus();
   	    		return false;
   	    	}else{
   	    		$("#shortNameLab").text('');
   	    	}
			
			//  主体类型
			var merchantProperty = $("#merchantProperty").val();
			if("" == merchantProperty){
   	    		$("#merchantPropertyLab").text("主体类型不能为空");
   	    		$("#merchantProperty").focus();
   	    		return false;
   	    	}else{
   	    		$("#merchantPropertyLab").text('');
   	    	}
			
			//  用来获取多个经营场景复选按钮的值转换成字符串
    		var businessScene ="";
    		var businessScenes=$("input[type='checkbox']:checked").val([]);
	    		for(var i=0;i<businessScenes.length;i++){
	    			businessScene += businessScenes[i].value +",";
	 			}
    		businessScene = businessScene.substring(0, businessScene.length - 1);
    		if(0 >= businessScenes.length){
   	    		$("#businessSceneLab").text("经营场景不能为空");
   	    		return false;
   	    	}else{
   	    		$("#businessSceneLab").text('');
   	    	}
    		//  merchantProvince（注册地区）
   			var merchantProvince = $("#merchantProvince").val();
   			//  merchantCity
   			var merchantCity = $("#merchantCity").val();
   			//  merchantArea
   			var merchantArea =$("#merchantArea").val();
   			//  cprRegAddr（商户详细地址）getCprRegAddr
   			var cprRegAddr = $("#cprRegAddr").val();
   			if("" == cprRegAddr){
   	    		$("#cprRegAddrLab").text("商户地址不能为空");
   	    		$("#cprRegAddr").focus();
   	    		return false;
   	    	}else{
   	    		$("#mcprRegAddrLab").text('');
   	    	}
   			
   			//  营业执照编号
   			var businessLicense = $("#businessLicense").val();
   			if("" == businessLicense){
   	    		$("#businessLicenseLab").text("营业执照编号不能为空");
   	    		$("#businessLicense").focus();
   	    		return false;
   	    	}else{
   	    		$("#businessLicenseLab").text('');
   	    	}
   			
   			//  营业执照有效期
   			var businessEffectiveTerm = $("#businessEffectiveTerm").val();
   			//  营业执照有效期
   			var businessTerm = $("#businessTerm").val();
   			if("" == businessTerm){
   	    		$("#businessTermLab").text("营业执照有效期不能为空");
   	    		$("#businessTerm").focus();
   	    		return false;
   	    	}else{
   	    		$("#businessTermLab").text('');
   	    	}
   			
   			//  营业执照路径
			var businessPhotoPath = $("#businessPhotoPath").val(); 
			if("" == businessPhotoPath){
   	    		$("#businessPhotoPathLab").text("营业执照路径不能为空");
   	    		$("#businessPhotoPath").focus();
   	    		return false;
   	    	}else{
   	    		$("#businessPhotoPathLab").text('');
   	    	}
			
   			//  特殊行业资质照路径
   			var qualificationPath = $("#qualificationPath").val(); 
   			//  公众号APPID
   			var mpAppid =$("#mpAppid").val(); 
   			//  公众号页面截图
   			var mpAppScreenShotsPath = $("#mpAppScreenShotsPath").val();
   			//  小程序APPID
   			var miniprogramAppid = $("#miniprogramAppid").val();
   			//  小程序页面截图
   			var miniprogramAppidPath = $("#miniprogramAppidPath").val();
   			//  应用APPID
   			var appAppid = $("#appAppid").val();
   			//  APP下载链接
   			var appDownloadUrl = $("#appDownloadUrl").val();
   			//  APP截图
   			var appAppidPath = $("#appAppidPath").val();
   			//  PC网站域名
   			var webUrl = $("#webUrl").val();
   			//  PC网站对应的公众号APPID
   			var webAppid = $("#webAppid").val(); 
   			//  网站授权函
   			var webUrlPath = $("#webUrlPath").val(); 
   			//  representativeName（法人姓名）
   			var representativeName = $("#representativeName").val();
   			if("" == representativeName){
   	    		$("#representativeNameLab").text("法人姓名不能为空");
   	    		$("#representativeName").focus();
   	    		return false;
   	    	}else{
   	    		$("#representativeNameLab").text('');
   	    	}
   			
   			//  手机号
   			var mobileNo = $("#mobileNo").val();
   			if("" == mobileNo){
   	    		$("#mobileNoLab").text("手机号不能为空");
   	    		$("#mobileNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#mobileNoLab").text('');
   	    	}
   			//  联系邮箱
			var merchantEmail = $("#merchantEmail").val();
			if("" == merchantEmail){
   	    		$("#merchantEmailLab").text("联系邮箱不能为空");
   	    		$("#merchantEmail").focus();
   	    		return false;
   	    	}else{
   	    		$("#merchantEmailLab").text('');
   	    	}
			
   			//  actNm（结算名）
   			var actNm = $("#actNm").val();
   			if("" == actNm){
   	    		$("#actNmLab").text("结算名不能为空");
   	    		$("#actNm").focus();
   	    		return false;
   	    	}else{
   	    		$("#actNmLab").text('');
   	    	}
   			
   			//  bankCardNo（结算银行卡号）
   			var bankCardNo = $("#bankCardNo").val();
   			if("" == bankCardNo){
   	    		$("#bankCardNoLab").text("结算银行卡号不能为空");
   	    		$("#bankCardNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#bankCardNoLab").text('');
   	    	}
   			
   			//  bankProvince（开户行所在地区）
   			var bankProvince = $("#bankProvince").val();
   			//  bankCity  
   			var bankCity = $("#bankCity").val()
   			//  weChatBank（开户行）
   			var weChatBank = $("#weChatBank").val();
   			//  interBankName(开户支行名称)
   			var interBankName = $("#interBankName").val();
   			if("" == interBankName){
   	    		$("#interBankNameLab").text("开户支行名称不能为空");
   	    		$("#interBankName").focus();
   	    		return false;
   	    	}else{
   	    		$("#interBankNameLab").text('');
   	    	}
   			
   			//  rateId 
   			//  根据主体类型判断结算费率类型
   			if("2" == merchantProperty){
   				rateId = $("#rateEnt").val();
			}else if("4" == merchantProperty){
				rateId = $("#ratePer").val();
			}else{
				rateId = $("#ratePic").val();
			}
   			//上传照片路径
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%="/common/files/getPicPath"%>?custId='+custId,
				data :{
	            	"bussinessPath"          : businessPhotoPath,                //商户营业执照
	            	"qualificationPath"      : qualificationPath,                //特殊行业资质照
	            	"mpAppScreenShotsPath"   : mpAppScreenShotsPath,             //公众号页面截图
	            	"miniprogramAppidPath"   : miniprogramAppidPath,             //小程序页面截图
	            	"appAppidPath"           : appAppidPath,                     //APP截图
					"webUrlPath"             : webUrlPath					     //网站授权函
	            },
                dataType : "json",
                cache: false,
                processData: false,
                contentType: false,
				success : function(data){
				if(data.result=="SUCCESS"){
					if("" != data.message){
	  	   				$.ajax({
	  	   					type : "POST",
	  	   					url : window.Constants.ContextPath +'<%="/merchant/reported/weChatAppUpgradeMerchantReportSubmit"%>',
	  	   					data :{
	  	   						"channelNo"             : channelNo,
	  	   						"patchNo"               : patchNo,
	  	   						"merchantCode"          : merchantCode,
	  	   						"custName"              : custName,
	  	   						"outMerchantCode"       : outMerchantCode,
			  	   				"shortName"             : shortName,
			  	   				"merchantProperty"      : merchantProperty,
			  	   				"businessScene"         : businessScene,
				  	   			"province"              : merchantProvince,
	  	   						"city"                  : merchantCity,
	  	   						"area"                  : merchantArea,
	  	   						"cprRegAddr"            : cprRegAddr,
		  	   					"businessLicense"       : businessLicense,
	  	   						"businessEffectiveTerm" : businessEffectiveTerm,
	  	   						"businessTerm"          : businessTerm,
			  	   				"businessPhotoPath"     : businessPhotoPath,
			  	   				"qualificationPath"     : qualificationPath,
			  	   				"mpAppid"               : mpAppid,
			  	   				"mpAppScreenShotsPath"  : mpAppScreenShotsPath,
			  	   				"miniprogramAppid"      : miniprogramAppid,
			  	   				"miniprogramAppidPath"  : miniprogramAppidPath,
			  	   				"appAppid"              : appAppid,
			  	   				"appDownloadUrl"        : appDownloadUrl,
			  	   				"appAppidPath"          : appAppidPath,
			  	   				"webUrl"                : webUrl,
			  	   				"webAppid"              : webAppid,
			  	   				"webUrlPath"            : webUrlPath,
			  	   				"representativeName"    : representativeName,
			  	   				"mobileNo"              : mobileNo, 
			  	   				"merchantEmail"         : merchantEmail,
			  	   				"actNm"                 : actNm,
			  	   				"accountNo"             : bankCardNo,
			  	   				"bankProvince"          : bankProvince,
  	   							"bankCity"              : bankCity,
  	   							"weChatBank"            : weChatBank,
	  	   						"interBankName"         : interBankName,
	  	   						"rateId"                : rateId
	  	   					},
	  	   					dataType : "json",
	  	   					success : function(data) {
	  	   						if(data.result=='SUCCESS'){							
	  	   							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
									
	  	   							},function(){
										this.location.reload();
									});
								}else{
									alert(data.message);
								}
	  	   						
	  	   					}
	  	   				});
					
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
			
    	
    	});
    	
   	});  
</script>
</body>
</html>