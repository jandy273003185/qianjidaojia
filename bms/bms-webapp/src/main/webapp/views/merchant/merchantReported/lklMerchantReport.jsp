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
	<title>拉卡拉进件</title>
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
	    	       	  				$("#crLicenceName").val(json.cardName);
	    	       	  				$("#crLicenceNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="businessPhoto"){                     //营业执照
	    	       	  				$("#merLicenseNo").val(json.businessLicense);
	    	       	  				$("#businessEffectiveTerm").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("#linceseExpire").val("长期");
	    	       	  				}else{
	    	       	  					$("#linceseExpire").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#address").val(json.legalAddress);
	    	       	  				$("#cprRegNmCn").val(json.companyName);
	    	       	  				$("#crLicenceName").val(json.legalPerson);
	    	       	  			
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
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channelCode" name="channelCode" value="LKL"/>
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
									<input type="text" id="custName" name="custName" placeholder="请输入商户名称" maxlength=""  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								 <td class="td-left" width="18%">商户经营名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="bizName" name="bizName" placeholder="请输入商户简称" maxlength=""  value="${custInfo.shortName }" style="width:90%">
								</td>
							    <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select  name="channelType" id="channelType" class="width-90" >
										<option value="TP_MERCHANT">--商户--</option>
										<option value="TP_PERSONAL">--个人--</option>
									</select>
									<label class="label-tips" id="mecTypeFlagLab"></label>	
								</td>
							</tr>
							<tr>
							    <td class="td-left">业务类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select  name="bizType" id="bizType"  class="width-90" >
										<option value="1">--扫码支付--</option>
										<option value="2">--普通pos--</option>
										<option value="3">--策略收单--</option>
										<option value="4">--小程序终端--</option>
									</select>
									<label class="label-tips" id="bizTypeLab"></label>	
								</td>
							</tr>
	                        <tr>
                                <td class="td-left">商户行业信息：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
								<select name="mccCode" id="mccCode" data-width="125px" data-live-search="true">
									<option value="">--请选择商户类目--</option>
									<c:if test="${not empty mccInfos }">
			                           	<c:forEach items="${mccInfos }" var="industry">
			                           		<option id="${industry.mccId}" value="${industry.mccId}">${industry.mccName}</option>
			                           	</c:forEach>
		                 		  	</c:if>
								</select> 
								
								</td>
							</tr>
	                        <tr>
	                        	<td class="td-left">商户经营内容：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
								<select name="bizContent" id="bizContent" data-width="125px" data-live-search="true">
									<option value="">--商户经营内容--</option>
									<option value="10001">餐饮、宾馆、娱乐、珠宝金饰、工艺美术品</option>
									<option value="10002">房地产汽车类</option>
									<option value="10003">百货、中介、培训、景区门票等</option>
									<option value="10004">批发类商户</option>
									<option value="10005">加油、超市类</option>
									<option value="10006">交通运输售票</option>
									<option value="10007">水电气缴费</option>
									<option value="10008">政府类</option>
									<option value="10009">便民类</option>
									<option value="10010">公立医院、公立学校、慈善</option>
									<option value="10011">宾馆餐饮娱乐类</option>
									<option value="10012">房产汽车类</option>
									<option value="10013">批发类</option>
									<option value="10014">超市加油类</option>
									<option value="10015">一般类商户</option>
									<option value="10016">三农商户</option>
								</select> 
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="provinceCode" id="provinceCode" onchange="getMerchantCity()">
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
		                                <select class="form-control" name="cityCode" id="cityCode"  onchange="getMerchantArea()">
		                                    <option value="" id="cityDef">--请选择市--</option>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
		                                <select class="form-control" name="countyCode" id="countyCode" >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="address" id="address"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
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
	                                    <input type="text" name="linceseExpire" id="linceseExpire" value="${custInfo.businessTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
	                                    <input type="button" onclick="businessForever()" value="长期" />
									</td>
							</tr>
							<tr id="businessPhotoType2" style = "display:">
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="merLicenseNo" id="merLicenseNo"  placeholder="请输入营业编号"  value="${custInfo.businessLicense }" style="width:90%">
								</td>
								<td class="td-left">终端数量：</td>
								<td class="td-right">
									<input type="text" name="termNum" id="termNum"  placeholder="请输入1-10"  value="" style="width:90%">
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
										<input  type="hidden" id="businessLicence" name="businessLicence" value ="${picturePathVo.bussinessPath }"/>
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
								
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">商户照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="merchantPhoto" name="merchantPhoto" />
										<input type="hidden" id="shopInteriorImageVal02"  />
										<input type="file" name="shopInterior" id="shopInterior" onchange="showShopInteriorImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="shopCheckStandType" style = "display:">
								<td class="td-left">合影照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopCheckStandClick" data-target="#previewImageModal" >
										<label id="shopCheckStandDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="personalPhoto" name="personalPhoto" />
										<input type="hidden" id="shopCheckStandImageVal02"  />
										<input type="file" name="shopCheckStand" id="shopCheckStand" onchange="showShopCheckStandImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="othersType" style = "display:">
								<td class="td-left">其他图片照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success othersClick" data-target="#previewImageModal" >
										<label id="othersDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img id="${picturePathVo.otherMaterialPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="others" name="others" />
										<input type="hidden" id="othersImageVal02"  />
										<input type="file" name="othersPhoto" id="othersPhoto" onchange="showOthersImage(this)" />
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
									<input type="text" id="crLicenceName" name="crLicenceName" placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="contactMobile" id="contactMobile" placeholder="请输入手机号码"  value="${custInfo.mobile }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人证件类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" style="width:90%;"  >
										<option value="1">--身份证--</option>
										<option value="2">--护照--</option>
										<option value="5">--其他证件--</option>
									</select>
								</td>
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="crLicenceNo" id="crLicenceNo" placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">法人身份证有效期：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<input type="text" name="identityEffDate" id="identityEffDate" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
                                    <input type="text" name="idCardExpire" id="idCardExpire" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
                                    <input type="button" onclick="identityForever()" value="长期" />
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
								    	<input  type="hidden" id="idCardFront" name="idCardFront" />
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
								    	<input  type="hidden" id="idCardBehind" name="idCardBehind" />
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
									<input type="text" id="accountName" name="accountName" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="accountNo" name="accountNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">开户行地区：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="provinceBankCode" id="provinceBankCode" onchange="getMerchantBankCity()">
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
		                                <select class="form-control" name="cityBankCode" id="cityBankCode"  onchange="getMerchantBankArea()">
		                                    <option value="" id="cityBankDef">--请选择市--</option>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
		                                <select class="form-control" name="countyBankCode" id="countyBankCode" >
		                                    <option value="" id="areaBankDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="address" id="address"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户支行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="bankName" name="bankName" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
									<input type="button" onclick="selBankInfo()" value="银行信息" />
								</td>
								 <td class="td-left">银行信息：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
								<select   id="openningBankNo"  name="openningBankNo" >
						           <option value="" id="openningBankNoDef">--请选择银行信息--</option>        
						        </select>
						        </td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<select name="accountKind" id="accountKind" style="width-90" >
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
                            	<td class="td-left">结算周期：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
							  		<select name="settlePeriod" id="settlePeriod" style="width-90" >
										<option value="">--请选择--</option>
										<option value="T+1">--T+1--</option>
										<option value="D+1">--D+1--</option>
									</select> 
								</td>
                                <td class="td-left">账户人身份证号码：<span style="color:red;">(必填)</span></td>
							  	<td class="td-right"> 
									<input type="text" id="idCard" name="idCard" placeholder="请输入账户人身份证号码"  value="" style="width:90%">
									<label id="idCardLabel" class="label-tips"></label>
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
										<input  type="hidden" id="bankCard" name="bankCard" />
										<input type="hidden" id="bankCardPhotoImageVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
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
			commonFileUpload(file, 'businessLicence', 'businessPhotoDiv');
		}
		
		//商户照片
		function showShopInteriorImage(file){
			commonFileUpload(file, 'merchantPhoto', 'shopInteriorDiv');
		}
		
		//合影照片
		function showShopCheckStandImage(file){
			commonFileUpload(file, 'personalPhoto', 'shopCheckStandDiv');
		}
				
		//其他资料照
		function showOthersImage(file){
			commonFileUpload(file, 'others', 'othersDiv');
		}
		//法人身份证正面
		function showlegalCertAttribute1Image(file){
			commonFileUpload(file, 'idCardFront', 'legalCertAttribute1Div');
		}
		
		//法人身份证反面
		function showlegalCertAttribute2Image(file){
			commonFileUpload(file, 'idCardBehind', 'legalCertAttribute2Div');
		} 
		
		//上传银行卡照
		function showBankCardPhotoImage(file){
			commonFileUpload(file, 'bankCard', 'bankCardPhotoDiv');
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
        
      	
    	
      	/** 银行卡点击预览 **/
      	$('.bankCardPhotoClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("bankCardPhoto");
      		return previewImage(divObj,imageObj,obj);
      	});
      	/** 银行卡点击预览 **/
      	$('.othersClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("othersPhoto");
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

      	/** 商户照片点击预览 **/
      	$('.shopInteriorClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("shopInterior");
      		return previewImage(divObj,imageObj,obj); 
      	});
      	
      	/** 合影点击预览 **/
      	$('.shopCheckStandClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("shopCheckStand");
      		return previewImage(divObj,imageObj,obj); 
      	});
      	
      	
      	/***获取银行所在地区城市***/
      	function getMerchantBankCity(){
      		var provinceBankCode = $("#provinceBankCode").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getCityInfo",
    		{
      			"provinceId":provinceBankCode,
				"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#cityBankCode").html("");
           			for ( var city in cityList) {
           				$("#cityBankCode").append(
           						"<option value='"+ cityList[city].areaCode +"'>"
           								+ cityList[city].areaName + "</option>"); 
           			}
           			getMerchantBankArea();;
    			}else{
    				alert("省份不能为空");
    			}
    		},'json'
    		);	
        }
      	
      	/***获取银行所在地区县区***/
      	function getMerchantBankArea(){
      		var cityBankCode = $("#cityBankCode").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getAreaInfo",
    		{
      			"areaCode":cityBankCode,
    			"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var areaList = data.areaList;
    				$("#countyBankCode").html("");
           			for ( var area in areaList) {
           				$("#countyBankCode").append(
           						"<option value='"+ areaList[area].areaCode +"'>"
           								+ areaList[area].areaName + "</option>"); 
           			}
    			}else{
    				alert("市不能为空");
    			}
    		},'json'
    		);	
        }
      	
      	//查询银行信息
      	function selBankInfo(){
      		var countyBankCode = $("#countyBankCode").val();
      		var bankName = $("#bankName").val();
      		$.post(window.Constants.ContextPath +"/common/info/bankListByBarchBankName",
    		{
      			"countyBankCode":countyBankCode,
      			"bankName" : bankName
    		},
    		function(res){
    			if(res.result=="SUCCESS"){
    				var bankListByBarchBankName = res.data;
    				$("#openningBankNo").html("");
           			for ( var bankInfoList in bankListByBarchBankName) {
           				$("#openningBankNo").append(
           						"<option value='"+ bankListByBarchBankName[bankInfoList].openingBank +"'>"
           								+ bankListByBarchBankName[bankInfoList].bankName + "</option>"); 
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
      		var provinceId = $("#provinceCode").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getCityInfo",
    		{
      			"provinceId":provinceId,
				"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#cityCode").html("");
           			for ( var city in cityList) {
           				$("#cityCode").append(
           						"<option value='"+ cityList[city].areaCode +"'>"
           								+ cityList[city].areaName + "</option>"); 
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
      		var areaCode = $("#cityCode").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getAreaInfo",
    		{
      			"areaCode":areaCode,
    			"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var areaList = data.areaList;
    				$("#countyCode").html("");
           			for ( var area in areaList) {
           				$("#countyCode").append(
           						"<option value='"+ areaList[area].areaCode +"'>"
           								+ areaList[area].areaName + "</option>"); 
           			}
    			}else{
    				alert("市不能为空");
    			}
    		},'json'
    		);	
        }
      	
      
      	function businessForever(){
			$("input[name='linceseExpire']").val("长期");
			$("#linceseExpire").attr("value","长期");
		}
      	
      	function identityForever(){
		    $("#idCardExpire").attr("value","长期");
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
   			var channelCode = $("#channelCode").val();
			//商户号
   			var merchantCode = $("#merchantCode").val();
			//客户名称
   			var custName = $("#custName").val();
			//渠道类型
			var channelType = $("#channelType").val();
			//业务类型
			var bizType = $("#bizType").val();
			//商户经营名称
			var bizName = $("#shortName").val();
			//营业执照号
			var merLicenseNo = $("#merLicenseNo").val();
			//营业执照有效期
			var linceseExpire = $("#linceseExpire").val();
			//商户注册地址省代码
   			var provinceCode = $("#provinceCode").val();
   			//商户注册地址市代码
   			var cityCode = $("#cityCode").val();
   			//商户注册地址区代码
   			var countyCode =$("#countyCode").val();
   			//商户详细地址
   			var address = $("#address").val();
   			//商户经营内容
   			var bizContent = $("#bizContent").val();
   			//商户法人姓名
   			var crLicenceName = $("#crLicenceName").val();
   			//商户法人身份证号码
   			var crLicenceNo = $("#crLicenceNo").val();
   			//商户法人身份证有效期
   			var idCardExpire = $("#idCardExpire").val();
   			//手机号
   			var contactMobile = $("#contactMobile").val();
   			//开户行号
   			var openningBankNo = $("#openningBankNo").val();
   			//开户支行名称
   			var openningBankName = $("#openningBankName").val();
   			//清算行号
   			var clearingBankNo = $("#clearingBankNo").val();
   			//账户号
   			var accountNo = $("#accountNo").val();
   			//账户名称
   			var accountName = $("#accountName").val();
   			//账户性质
   			var accountKind = $("#accountKind").val();
   			//入账人身份证号码
   			var idCard = $("#idCard").val();
   			//结算周期
   			var settlePeriod = $("#settlePeriod").val();
   			//行业代码
   			var mccCode = $("#mccCode").val();
   			//费率
   			var rate = $("#rate").val();
			//终端数量
   			var termNum = $("#termNum").val();
   			
   			//图片路径
   			var idCardFront = $("#idCardFront").val();
   	    	var idCardBehind = $("#idCardBehind").val();
   	    	var businessLicence = $("#businessLicence").val();
   	    	var bankCard = $("#bankCard").val();
   	    	var personalPhoto = $("#personalPhoto").val();
   	    	var merchantPhoto = $("#merchantPhoto").val();
   	    	var others = $("#others").val();

   			if("" == merchantCode){
   	    		$("#merchantCodeLab").text("商户编号不能为空");
   	    		$("#merchantCode").focus();
   	    		return false;
   	    	}else{
   	    		$("#merchantCodeLab").text('');
   	    	}
   			if("" == custName){
   	    		$("#custNameLab").text("客户名称不能为空");
   	    		$("#custName").focus();
   	    		return false;
   	    	}else{
   	    		$("#custNameLab").text('');
   	    	}
			if("" == channelType){
   	    		$("#channelTypeLab").text("渠道类型不能为空");
   	    		$("#channelType").focus();
   	    		return false;
   	    	}else{
   	    		$("#channelTypeLab").text('');
   	    	}
			if("" == bizType){
   	    		$("#bizTypeLab").text("业务类型不能为空");
   	    		$("#bizType").focus();
   	    		return false;
   	    	}else{
   	    		$("#bizTypeLab").text('');
   	    	}
			if("" == bizName){
   	    		$("#bizNameLab").text("商户经营名称不能为空");
   	    		$("#bizName").focus();
   	    		return false;
   	    	}else{
   	    		$("#bizNameLab").text('');
   	    	}
   			if("" == address){
   	    		$("#addressLab").text("商户地址不能为空");
   	    		$("#address").focus();
   	    		return false;
   	    	}else{
   	    		$("#addressLab").text('');
   	    	}
   			if("" == bizContent){
   	    		$("#bizContentLab").text("商户经营内容不能为空");
   	    		$("#bizContent").focus();
   	    		return false;
   	    	}else{
   	    		$("#bizContentLab").text('');
   	    	}
   			if("" == crLicenceName){
   	    		$("#crLicenceNameLab").text("商户法人姓名不能为空");
   	    		$("#crLicenceName").focus();
   	    		return false;
   	    	}else{
   	    		$("#crLicenceNameLab").text('');
   	    	}
   			if("" == crLicenceNo){
   	    		$("#crLicenceNoLab").text("商户法人身份证号不能为空");
   	    		$("#crLicenceNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#crLicenceNoLab").text('');
   	    	}
   			if("" == idCardExpire){
   	    		$("#idCardExpireLab").text("商户法人身份证有效期不能为空");
   	    		$("#idCardExpire").focus();
   	    		return false;
   	    	}else{
   	    		$("#idCardExpireLab").text('');
   	    	}
   			if("" == contactMobile){
   	    		$("#contactMobileLab").text("手机号不能为空");
   	    		$("#contactMobile").focus();
   	    		return false;
   	    	}else{
   	    		$("#contactMobileLab").text('');
   	    	}
   			if("" == accountNo){
   	    		$("#accountNoLab").text("账户号不能为空");
   	    		$("#accountNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#accountNoLab").text('');
   	    	}
   			if("" == accountName){
   	    		$("#accountNameLab").text("账户名称不能为空");
   	    		$("#accountName").focus();
   	    		return false;
   	    	}else{
   	    		$("#accountNameLab").text('');
   	    	}
   			if("" == idCard){
   	    		$("#idCardLab").text("入账人身份证号码不能为空");
   	    		$("#idCard").focus();
   	    		return false;
   	    	}else{
   	    		$("#idCardLab").text('');
   	    	}
   			if("" == rate){
   	    		$("#rateLab").text("费率不能为空");
   	    		$("#rate").focus();
   	    		return false;
   	    	}else{
   	    		$("#rateLab").text('');
   	    	}
   	    	
  	   	   	if("" == custName){
   	    		$("#custNameLab").text("商户名不能为空");
   	    		$("#custName").focus();
   	    		return false;
   	    	}else{
   	    		$("#custNameLab").text('');
   	    	}
 	   	    if("" == contactMobile){
   	    		$("#contactMobileLab").text("手机号不能为空");
   	    		$("#contactMobile").focus();
   	    		return false;
   	    	}else{
   	    		$("#contactMobileLab").text('');
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
 	   	    if("" == merLicenseNo){
	   	    		$("#registCodeLab").text("营业执照注册号不能为空");
	   	    		$("#merLicenseNo").focus();
	   	    		return false;
	   	    	}else{
	   	    		$("#registCodeLab").text('');
	   	    	}
	   	    }
 	   		if("" == provinceCode){
   	    		$("#provinceCodeLabel").text("注册地址省份不能为空");
   	    		return false;
   	    	}else{
   	    		$("#provinceCodeLabel").text("");
   	    	}
 	   		if("" == cityCode){
 	   	    		$("#cityCodeLabel").text("注册地址城市不能为空");
 	   	    		return false;
 	   	    	}else{
 	   	    		$("#cityCodeLabel").text("");
 	   	    	}
 	    	if("" == countyCode){
 	   	    		$("#countyCodeLabel").text("注册地址地区不能为空");
 	   	    		return false;
 	   	    	}else{
 	   	    		$("#countyCodeLabel").text("");
 	   	    	}
  	   	    if("" == address){
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
	   	    if("" == crLicenceName){
   	    		$("#crLicenceNameLabel").text("法人姓名不能为空");
   	    	    $("#crLicenceName").focus();
   	    		return false;
   	    	}else{
   	    		$("#crLicenceNameLabel").text("");
   	    	}
	   	    if("" == representativeCertType){
   	    		$("#representativeCertTypeLabel").text("法人证件类型不能为空");
   	    	    $("#representativeCertType").focus();
   	    		return false;
   	    	}else{
   	    		$("#representativeCertTypeLabel").text("");
   	    	}
	   	    if("" == crLicenceNo){
   	    		$("#crLicenceNoLabel").text("法人证件号不能为空");
   	    	    $("#crLicenceNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#crLicenceNoLabel").text("");
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
	 	   		if("" == idCard){
	   	    		$("#idCardLabel").text("结算身份证号不能为空");
	   	    	    $("#idCard").focus();
	   	    		return false;
	   	    	}else{
	   	    		$("#idCardLabel").text("");
	   	    	} 
 	   		}
	   	    
	   	    if("" == bankCardNo){
   	    		$("#bankCardNoLabel").text("结算开户行卡号不能为空");
   	    	    $("#bankCardNo").focus();
   	    		return false;
   	    	}else{
   	    		$("#bankCardNoLabel").text("");
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
	    	
	    	if("" == termNum){
	    		$("#termNumLabel").text("终端数量不能为空");
	    		$("#termNum").focus();
	    		return false;
	    	}else{
	    		$("#termNumLabel").text("");
	    	}
 	   	   	    
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SUXINGPAYSUBMITREPORT%>',
				data :{
		   			channelCode : channelCode,
					merchantCode : merchantCode,
					custName : custName,
					channelType : channelType,
					bizType : bizType,
					bizName : bizName,
					merLicenseNo : merLicenseNo,
					linceseExpire : linceseExpire,
					provinceCode : provinceCode,
		   			cityCode : cityCode,
		   			countyCode : countyCode, 
		   			address : address,
		   			bizContent : bizContent,
		   			crLicenceName : crLicenceName,
		   			crLicenceNo : crLicenceNo, 
		   			idCardExpire : idCardExpire,
		   			contactMobile : contactMobile,
		   			openningBankNo : openningBankNo,
		   			openningBankName : openningBankName,
		   			clearingBankNo : clearingBankNo,
		   			accountNo : accountNo,
		   			accountName : accountName,
		   			accountKind : accountKind,
		   			idCard : idCard,
		   			settlePeriod : settlePeriod,
		   			mccCode : mccCode,
		   			rate : rate,
		   			termNum  : termNum,
		   			idCardFront : certAttribute1Path,
			   		idCardBehind : idCardBehind,
			   		bankCard  : bankCard,
			   		businessLicence  : businessLicence,
			   		personalPhoto  : personalPhoto,
			   		merchantPhoto  : merchantPhoto,
			   		others : others
		   		
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