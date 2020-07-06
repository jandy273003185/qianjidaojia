<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.seven.micropay.channel.enums.MerchantApplyType"%>
<%@page import="com.seven.micropay.channel.enums.CertificateType"%>
<%@page import="com.seven.micropay.channel.enums.CardType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
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
	<title>翼支付更新进件</title>
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
					<input type="hidden" id="channlCode" name="channlCode" value="BEST_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="certAttribute0temp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="opentemp"/>
					<div id="door_temp"></div>
					<table id="merchant_table" class="list-table">
						<tbody>
					    	<form  id="merchantForm" action='<c:url value="<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.BESTPAYMERCHANTREPORT %>"/>' method="post">
					        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">进件选择</td>
							</tr>
						    <tr>
						    </tr>
                            <tr>
                            	<td class="td-left">更新类型：</td>
                            	<td class="td-right"> 
									<select name="updateType" id="updateType" style="width:250px;" onchange="getUpdateType();">
										<option value="">--请选择--</option>
										<c:forEach items="<%=MerUpdateType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.merUpdateType}">selected</c:if>>
												${status.text}
											</option>
										</c:forEach>
									</select>
								</td>
								<td class="td-left">进件类型：</td>
								<td class="td-right"> 
									<select class="width-90" id="merchantType" onchange = "getMerchantType();">
											<option value="0">企业</option>
											<option value="1">个人</option>
								   </select>	
								</td>
							</tr>
							<tr id="updateFileType" style = "display:none">
								<td>更新纸质类型</td>
								<td>
		                            <select name="qualificationType" id="qualificationType" style="width:250px;"  >
										<option value="">--请选择--</option>
										<option value="IDCard">--身份证正面--</option>
										<option value="IDCardBack">--身份证反面--</option>
										<option value="businessLicense">--营业执照--</option>
										<option value="storeInterior">--店铺内景--</option>
										<option value="doorPhoto">--店铺招牌照--</option>
									</select>
				                    <label id="qualificationTypeLab" class="label-tips"></label>
					             </td>
					        </tr>
                            <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
							</tr>
						    <tr></tr>
                            <tr>
								<td class="td-left" width="18%">商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户简称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" placeholder="请输入商户简称" maxlength=""  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr id="industryType" style = "display:">
                                <td class="td-left">商户行业信息：</td>
								<td class="td-right">
								 	<span>
									   <select name="industry" id="industry" style="width-90;" >
											<option value="">--请选择商户行业--</option>
											<c:if test="${not empty industryList }">
					                           	<c:forEach items="${industryList }" var="industry">
					                           		<option id="${industry.industryCode}" value="${industry.industryCode}">${industry.industryName}</option>
					                           	</c:forEach>
				                 		  	</c:if>
									   </select>
									   <label id="industryLabel" class="label-tips"></label>
								   </span> 
								</td>
								<td class="td-left">创建人：</td>
								<td class="td-right"> 
									<input type="text" id="createdBy" name="createdBy" placeholder="请输入创建人" maxlength=""  value="" style="width:90%">
								</td>
							</tr>
	                        <tr id="merchantApplyType1" style="display:">
	                        	<td class="td-left">商户申请类型：</td>
								<td class="td-right"> 
									<select name="merchantApplyType" id="merchantApplyType" style="width:width-90;"  >
										<option value="">--请选择--</option>
										<c:forEach items="<%=MerchantApplyType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.merchantApplyType}">selected</c:if>>
												${status.text}
											</option>
										</c:forEach>
									</select>
								</td>
							    <td class="td-left">证件类型：</td>
								<td class="td-right"> 
									<select name="certificateType" id="certificateType" style="width-90;"  >
										<option value="">--请选择--</option>
										<c:forEach items="<%=CertificateType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.certificateType}">selected</c:if>>
												${status.text}
											</option>
										</c:forEach>
									</select>
								</td>
                                
							</tr>
 	                        <tr id="businessScopeType" style="display:">
							    <td class="td-left">经营范围：</td>
								<td class="td-right"> 
									 <input type="text" id="businessScope" name="businessScope" placeholder="请输入经营范围" maxlength=""  value="${custInfo.businessScope }" style="width:90%">
								</td>
                                <td class="td-left">工商注册号：</td>
								<td class="td-right"> 
									<input type="text" id="registrationNumber" name="registrationNumber" placeholder="请输入工商注册号"  value="" style="width:90%">
									<label class="label-tips" id="registrationNumberLabel"></label>
								</td>
							</tr>
							<tr id="addressType" style="display:">
								<td class="td-left">注册地址：</td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
										<select name="province" id="province" class="form-control" onchange="getCity();">
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
	                                    <input type="text" id="cprRegAddr" name="cprRegAddr"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
							<tr id="businessPhotoType" style = "display:">
								<td class="td-left">营业执照照片：</td>
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
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick"  data-target="#previewImageModal"  >
										<label id="shopInteriorDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="shopInteriorImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoVal02"  />  
										<input type="file" name="shopInterior" id="shopInterior" onchange="shopInteriorImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照：</td>
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
							</tr>
                        	<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                            <tr id="mobileNoType" style = "display:">
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名"  value="张老板" maxlength="50" style="width:90%">
								</td>
								<td class="td-left">手机号码：</td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  value="${custInfo.merchantMobile }" style="width:90%">
								</td>
							</tr>
                            <tr id="certifyNoType" style = "display:">
								<td class="td-left">法人证件类型：</td>
								<td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" style="width-90;"  >
										<option value="">--请选择--</option>
										<option value="ID">--身份证--</option>
										<option value="HMPASS">--港澳居民往来内地通行证--</option>
										<option value="MTPS">--台湾居民来往大陆通行证--</option>
										<option value="PASSPORT">--护照--</option>
									</select>
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码"  value="5255456685555" style="width:90%">
								</td>
							</tr>
                            <tr id="residentCityType" style = "display:">
								<td class="td-left">户口所在地：</td>
								<td class="td-right">
									<input type="text" id="residentCity" name="residentCity"  placeholder="请输入户口所在地"  value="" style="width:90%">
								</td>
								<td class="td-left">法人身份证有效期：</td>
								<td class="td-right">
									<input type="text" name="" id="" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> -
                                    <input type="text" name="validDate" id="validDate" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> <input name="" type="radio" value=""> 长期
								</td>
							</tr>
							<tr id="idCardType" style = "display:">
								<td class="td-left">法人身份证正面：</td>
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
							</tr>
							<tr id="idCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：</td>
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
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr id="bankAcctNameType" style="dsplay:">
								<td class="td-left">银行账户名称：</td>
								<td class="td-right"> 
									<input type="text" id="bankAcctName" name="bankAcctName" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width-90;">
								</td>
                                 <td class="td-left">银行卡号：</td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width-90;">
								</td>
							</tr>
							<tr id="bestBankType" style = "display:">
								<td class="td-left">开户银行：</td>
								<td class="td-right"> 
									<span class="input-icon">	
										<select name="bestBank" id="bestBank" style="width-90">
											<option value="">--请选择--</option>
											<c:forEach items="<%=BestBankCode.values()%>" var="status">
												<option value="${status}" <c:if test="${status == queryBean.bestBankCode}">selected</c:if>>
													${status.code}
												</option>
											</c:forEach>
										</select>
									</span>
									<!-- <input type="text" class="aui-form-control-two" style="width:90%;" name="interBankName"  required id="interBankName" placeholder="请输入银行名称">
					                <label class="label-tips" id="interBankNameLabel"></label> -->
								</td>
                                <td class="td-left">开户支行名称：</td>
								<td class="td-right"> 
									<input type="text" id="interBankName" name="interBankName" maxlength="100" placeholder="请输入开户支行名称"  value="${custInfo.branchBANK }" style="width-90">
								</td>
							</tr>
							<tr id="bankCardType1" style = "display:">
								<td class="td-left">结算类型：</td>
								<td class="td-right"> 
									<select class="width-90" id="perEntFlag">
	                                    <option value="" id="0">对公</option>
                                        <option value="" id="1">对私</option>
	                                </select>
								</td>
								<td class="td-left">银行账户类型 ：</td>
								<td class="td-right"> 
									<select name="bankCardType" id="bankCardType" style="width-90">
										<option value="">--请选择--</option>
										<c:forEach items="<%=CardType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.cardType}">selected</c:if>>
												${status.text}
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr id="merchantType1" style="display:">
								<td class="td-left">是否有证商户：</td>
								<td class="td-right"> 
									<select name="bestMerchantType" id="bestMerchantType" style="width-90;"  >
										<option value="">--请选择--</option>
										<option value="Unlicensed">--无证商户--</option>
										<option value="Licensed">--有证商户--</option>
									</select>
								</td>
								<td class="td-left"></td>
								<td class="td-right"></td>
							</tr>
							<tr id="rateType" style="display:">	
								<td class="td-left" >费率：</td>
								<td class="td-right"> 
									<input type="text" id="rate" name="rate" maxlength="100" placeholder="请输入费率"  value="" style="width-90">
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr id="openType" style = "display:">
								<td class="td-left">开户许可证照片：</td>
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
							</tr>
					        </form>
					    </div>
					</tbody>
					</table>
					<div style="margin:50px 0 0 0;text-align:center">
						<button type="button"  class="btn btn-primary" data-toggle='modal' id='submitData' data-target="#submitModal">提交报备</button> 
						<button type="button"  class="btn btn-default"  onclick="exit()">关闭</button> 
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
	
	function getMerchantType(){
		var merchantType = $("merchantType").val();
		if("0" == merchantType){
			
		}else{
			
		}
		
	}
	function exit() {
		if (confirm("您确定要关闭吗？")) {
			window.opener=null;
		
			window.open("","_self");
		
			window.close();
		}
	};
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
      		var province = $("#province").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELCITY %>",
    		{
    			"province":province
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#city").html("");
           			for ( var city in cityList) {
           				$("#city").append(
           						"<option value='"+ cityList[city].cityId +"'>"
           								+ cityList[city].cityName + "</option>"); 
           			}
           			getArea();
    			}else{
    				alert("省份不能为空");
    			}
    		},'json'
    		);	
      	}
      	
      	/***获取区县***/
      	function getArea(){
      		var city = $("#city").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELAREA %>",
    		{
    			"city":city
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var areaList = data.areaList;
    				$("#area").html("");
           			for ( var area in areaList) {
           				$("#area").append(
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

    	function showDoorPhotoImage(obj){  
    		 var divObj = document.getElementById("doorPhotoDiv");  
    		 var imageObj = document.getElementById("doorPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}

    	function showCertAttribute0Image(obj){  
    		 var divObj = document.getElementById("certAttribute0Div");  
    		 var imageObj = document.getElementById("certAttribute0ImageDiv");  
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
    	
    	function showOpenImage(obj){  
  			 var divObj = document.getElementById("openDiv");  
  			 var imageObj = document.getElementById("openImageDiv");  
  			 return previewImage(divObj,imageObj,obj);  
  		}

		function getUpdateType(){
			var updateType = $("#updateType").val();
			if("BASE_INFO" == updateType){
				$("#intoType").attr("style","display:");
				$("#merchantCodeType").attr("style","display:");
				$("#custNameType").attr("style","display:");
				$("#industryType").attr("style","display:");
				$("#merchantApplyType1").attr("style","display:");
				$("#businessScopeType").attr("style","display:");
				$("#addressType").attr("style","display:");
				$("#mobileNoType").attr("style","display:");
				$("#certifyNoType").attr("style","display:");
				$("#bankAcctNameType").attr("style","display:");
				$("#bankCardType1").attr("style","display:");
				$("#bestBankType").attr("style","display:");
				$("#interBankNameType").attr("style","display:");
				$("#merchantType1").attr("style","display:");
				$("#rateType").attr("style","display:");
				$("#updateFileType").attr("style","display:none");
				$("#shopInteriorType").attr("style","display:none");
				$("#doorPhotoType").attr("style","display:none");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:none");
				$("#openType").attr("style","display:none");
				
			}else if("FILE_INFO" == updateType){
				$("#intoType").attr("style","display:");
				$("#merchantCodeType").attr("style","display:none");
				$("#custNameType").attr("style","display:none");
				$("#mobileNoType").attr("style","display:none");
				$("#businessScopeType").attr("style","display:none");
				$("#addressType").attr("style","display:none");
				$("#certifyNoType").attr("style","display:none");
				$("#bankAcctNameType").attr("style","display:none");
				$("#industryType").attr("style","display:none");
				$("#infoType").attr("style","display:none");
				$("#residentCityType").attr("style","display:none");
				$("#bankCardType1").attr("style","display:none");
				$("#bestBankType").attr("style","display:none");
				$("#interBankNameType").attr("style","display:none");
				$("#merchantType1").attr("style","display:");
				$("#rateType").attr("style","display:none");
				$("#updateFileType").attr("style","display:");
				$("#shopInteriorType").attr("style","display:");
				$("#doorPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:");
				$("#idCardBackType").attr("style","display:");
				$("#businessPhotoType").attr("style","display:");
				$("#openType").attr("style","display:none");
				$("#merchantApplyType1").attr("style","display:none");
			}else{
				$("#intoType").attr("style","display:");
				$("#merchantCodeType").attr("style","display:");
				$("#custNameType").attr("style","display:");
				$("#mobileNoType").attr("style","display:");
				$("#addressType").attr("style","display:");
				$("#certifyNoType").attr("style","display:");
				$("#bankAcctNameType").attr("style","display:");
				$("#industryType").attr("style","display:");
				$("#merchantApplyType1").attr("style","display:");
				$("#businessScopeType").attr("style","display:");
				$("#infoType").attr("style","display:");
				$("#residentCityType").attr("style","display:");
				$("#bankCardType1").attr("style","display:");
				$("#bestBankType").attr("style","display:");
				$("#interBankNameType").attr("style","display:");
				$("#merchantType1").attr("style","display:");
				$("#rateType").attr("style","display:");
				$("#updateFileType").attr("style","display:none");
				$("#shopInteriorType").attr("style","display:");
				$("#doorPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:");
				$("#idCardBackType").attr("style","display:");
				$("#businessPhotoType").attr("style","display:");
				$("#openType").attr("style","display:");
			}
		}
    	
      	/* 校验渠道 */
    $(function(){
		
		//判定是新进件还是更新进件
		var status = $("#status").val();
		var custId = $("#custId").val();
		var authId = $("#authId").val();
		
		if("unReported" == status){
			$("#intoType").attr("style","display:none");
		}else{
			$("#intoType").attr("style","display:");
		}
		if("" ==custId){
			alert(status);
		}else{
			$("#businessPhotoImageDiv").show();
	    	$("#certAttribute0ImageDiv").show();
	    	$("#certAttribute1ImageDiv").show();
	    	$("#certAttribute2ImageDiv").show();
	    	$("#doorPhotoImageDiv").show();
	    	$("#openImageDiv").show();
	    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
	    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#certAttribute0ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
	    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
	    	$("#openImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);    
		}

    	
    	
    	
   		$("#submitData").click(function(){
   	   		
   			var channelNo = $("#channlCode").val();
   			var merchantCode = $("#merchantCode").val();
   			var mchName = $("#custName").val();
   			var mobileNo = $("#mobileNo").val();
   			var province = $("#province").val();
   			var city = $("#city").val();
   			var country = $("#area").val();
   			var certifyNo = $("#certifyNo").val();
   			var industryCode = $("#industry").val();

   			var interName= $("#InterName").val();
   			var bankCardNo = $("#bankCardNo").val();
   			var bankCode = $("#bestBank").val();
   			var interBank = $("#InterBankCode").val();
   			var interBankName = $("#interBankName").val();
   			var bestMerchantType = $("#bestMerchantType").val();
   			var rate = $("#rate").val();
   			var updateType = $("#updateType").val();
   			
   			//翼支付渠道
   			if("BEST_PAY" == channelNo){
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
  				if("unReported" == status){

  					if("" == merchantCode){
  	   	   	    		$("#merchantCodeLab").text("商户编号不能为空");
  	   	   	    		$("#merchantCode").focus();
  	   	   	    		return false;
  	   	   	    	}else{
  	   	   	    		$("#merchantCodeLab").text('');
  	   	   	    	}
		  	   	   	if("" == mchName){
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
	   	   			if("" == certifyNo){
	   		    		$("#certifyNoLab").text("身份证件号不能为空");
	   		    		$("#certifyNo").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#certifyNoLab").text("");
	   		    	}
	   		    	if("" == industry){
	   		    		$("#industryLabel").text("行业信息不能为空");
	   		    		$("#industry").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#industryLabel").text("");
	   		    	}

	   		    	if("" == interName){
  	   		    		$("#interNameLab").text("开户人姓名不能为空");
  	   		    		$("#InterName").focus();
  	   		    		return false;
  	   		    	}else{
  	   		    		$("#interNameLab").text("");
  	   		    	}
	   		    	if("" == bankCardNo){
  	   		    		$("#bankCardNoLab").text("银行卡号不能为空");
  	   		    		$("#bankCardNo").focus();
  	   		    		return false;
  	   		    	}else{
  	   		    		$("#bankCardNoLab").text("");
  	   		    	}
  					if("" == bankCode){
  	   					$("#bestBankLab").text("报备开户行不能为空");
  	   					return false;
  	   				}else{
  	   		    		$("#bestBankLab").text("");
  	   		    	} 
  	   		   		if("" == interBankName){
	   		    		$("#interBankNameLabel").text("开户支行不能为空");
	   		    		$("#interBankName").focus();
	   		    		return false;
	   		    	}else{
	   		    		$("#interBankNameLabel").text("");
	   		    	}
  	   		   		if("" == bestMerchantType){
	   					$("#bestMerchantLab").text("是否有证商户不能为空");
	   					return false;
	   				}else{
	   		    		$("#bestMerchantLab").text("");
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
  	   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>?merchantCode='+merchantCode,
  	   					data :{
  	   						open : $('#opentemp').val(),
  	   						shopInterior : $('#shopInteriortemp').val(),
  	   						businessPhoto : $('#businessPhototemp').val(),
  	   						certAttribute0 : $('#certAttribute0temp').val(),
  	   						certAttribute1 : $('#certAttribute1temp').val(),
  	   						certAttribute2 : $('#certAttribute2temp').val(),
  	   						doorPhoto : imgDoor,
  	   						doorSrc : imgSrc
  	   					},
  	   					dataType : "json",
  	   					success : function(data) {
  	   						if(data.result=='SUCCESS')							
  	   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BESTPAYSUBMITREPORT %>",
  	   							{
  	   								"merchantCode":merchantCode,
  	   								"province":province,
  	   								"city":city,
  	   								"country":country,
  	   								"bankCode":bankCode,
  	   								"interBank":interBank,
  	   								"interBankName":interBankName,
  	   								"channelNo":channelNo,
  	   								"mobileNo":mobileNo,
  	   								"rate":rate,
  	   								"bankCardNo":bankCardNo,
  	   								"certifyNo":certifyNo,
  	   								"industryCode":industryCode,
  	   								"bestMerchantType":bestMerchantType,
  	   								"interName":interName
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
  	   									alert(data.message);
  	   								}
  	   							},'json'
  	   						  );	
  	   					}
  	   				});
  	  			}else{
  	  	  			//更新基本信息
  	  	  			if("BASE_INFO" == updateType){
  	  	  	  			
	  	  	  			if("" == merchantCode){
	  	   	   	    		$("#merchantCodeLab").text("商户编号不能为空");
	  	   	   	    		$("#merchantCode").focus();
	  	   	   	    		return false;
	  	   	   	    	}else{
	  	   	   	    		$("#merchantCodeLab").text('');
	  	   	   	    	}
			  	   	   	if("" == mchName){
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
		   	   			if("" == certifyNo){
		   		    		$("#certifyNoLab").text("身份证件号不能为空");
		   		    		$("#certifyNo").focus();
		   		    		return false;
		   		    	}else{
		   		    		$("#certifyNoLab").text("");
		   		    	}
		   		    	if("" == industry){
		   		    		$("#industryLabel").text("行业信息不能为空");
		   		    		$("#industry").focus();
		   		    		return false;
		   		    	}else{
		   		    		$("#industryLabel").text("");
		   		    	}
	
		   		    	if("" == interName){
	  	   		    		$("#interNameLab").text("开户人姓名不能为空");
	  	   		    		$("#InterName").focus();
	  	   		    		return false;
	  	   		    	}else{
	  	   		    		$("#interNameLab").text("");
	  	   		    	}
		   		    	if("" == bankCardNo){
	  	   		    		$("#bankCardNoLab").text("银行卡号不能为空");
	  	   		    		$("#bankCardNo").focus();
	  	   		    		return false;
	  	   		    	}else{
	  	   		    		$("#bankCardNoLab").text("");
	  	   		    	}
	  					if("" == bankCode){
	  	   					$("#bestBankLab").text("报备开户行不能为空");
	  	   					return false;
	  	   				}else{
	  	   		    		$("#bestBankLab").text("");
	  	   		    	} 
	  	   		   		if("" == interBankName){
		   		    		$("#interBankNameLabel").text("开户支行不能为空");
		   		    		$("#interBankName").focus();
		   		    		return false;
		   		    	}else{
		   		    		$("#interBankNameLabel").text("");
		   		    	}
	  	   		   		if("" == bestMerchantType){
		   					$("#bestMerchantLab").text("是否有证商户不能为空");
		   					return false;
		   				}else{
		   		    		$("#bestMerchantLab").text("");
		   		    	} 
		   		    	if("" == rate){
		   		    		$("#rateLabel").text("费率不能为空");
		   		    		$("#rate").focus();
		   		    		return false;
		   		    	}else{
		   		    		$("#rateLabel").text("");
		   		    	}
  	  	  	  			
	  	  	  			$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.UPDATESUBMITREPORT %>?updateType="+updateType,
						{
							"merchantCode":merchantCode,
							"province":province,
							"city":city,
							"country":country,
							"bankCode":bankCode,
							"interBank":interBank,
							"interBankName":interBankName,
							"channelNo":channelNo,
							"mobileNo":mobileNo,
							"rate":rate,
							"bankCardNo":bankCardNo,
							"certifyNo":certifyNo,
							"industryCode":industryCode,
							"bestMerchantType":bestMerchantType,
							"interName":interName
						},
						function(data){
							if(data.result=="SUCCESS"){
								$.gyzbadmin.alertSuccess("进件成功！",function(){
									//$("#updateAccountModal").modal("hide");
								},function(){
									this.location.reload();
								});
							}else{
								/* $.gyzbadmin.alertFailure(data.message);
								window.location.reload(); */
								/* $.gyzbadmin.alertFailure('进件失败:' + data.message,function(){
									//$("#updateAccountModal").modal("hide");
								},function(){
									window.location.reload();
								}); */
								alert(data.message);
							}
						},'json'
					  	);
	  	  	  	  	}else if("FILE_INFO" == updateType){//更新资质信息
	 	  	  	  	 	var qualificationType =  $("#qualificationType").val();

	 	  	  	  	 	
		 	  	  	  	if("" == qualificationType){
	  	   	   	    		$("#qualificationTypeLab").text("纸质类型不能为空");
	  	   	   	    		return false;
	  	   	   	    	}else{
	  	   	   	    		$("#qualificationTypeLab").text('');
	  	   	   	    	}

			 	  	  	if("" == bestMerchantType){
		   					$("#bestMerchantLab").text("是否有证商户不能为空");
		   					return false;
		   				}else{
		   		    		$("#bestMerchantLab").text("");
		   		    	} 
	 	  	  	  	 	
		  	  	  	  	$.ajax({
			   					type : "POST",
			   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>',
			   					data :{
			   						shopInterior : $('#shopInteriortemp').val(),
			   						businessPhoto : $('#businessPhototemp').val(),
			   						certAttribute0 : $('#certAttribute0temp').val(),
			   						certAttribute1 : $('#certAttribute1temp').val(),
			   						certAttribute2 : $('#certAttribute2temp').val(),
			   						doorPhoto : imgDoor,
			   						doorSrc : imgSrc,
			   						"merchantCode":merchantCode
			   					},
			   					dataType : "json",
			   					success : function(data) {
			   						if(data.result=='SUCCESS')							
			   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.UPDATESUBMITREPORT %>?updateType="+updateType+"&qualificationType="+qualificationType,
			   							{
			   								"merchantCode":merchantCode,
			   								"province":province,
			   								"city":city,
			   								"country":country,
			   								"bankCode":bankCode,
			   								"interBank":interBank,
			   								"interBankName":interBankName,
			   								"channelNo":channelNo,
			   								"mobileNo":mobileNo,
			   								"rate":rate,
			   								"bankCardNo":bankCardNo,
			   								"certifyNo":certifyNo,
			   								"industryCode":industryCode,
			   								"bestMerchantType":bestMerchantType,
			   								"interName":interName
			   							},
			   							function(data){
			   								if(data.result=="SUCCESS"){
			   									$.gyzbadmin.alertSuccess("进件成功！",function(){
			   									},function(){
			   										this.location.reload();
			   									});
			   								}else{
			   									/* $.gyzbadmin.alertFailure(data.message);
			   									window.location.reload(); */
			   									alert(data.message);
			   								}
			   							},'json'
			   						  );	
			   					}
			   			});	
	  	  	  	  	}else{

		  	  	  	    if("" == mchName){
		 	   	    		$("#custNameLab").text("商户名不能为空");
		 	   	    		$("#custName").focus();
		 	   	    		return false;
		 	   	    	}else{
		 	   	    		$("#custNameLab").text('');
		 	   	    	}
		  	  	  	  	$.ajax({
		   					type : "POST",
		   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>',
		   					data :{
		   						shopInterior : $('#shopInteriortemp').val(),
		   						businessPhoto : $('#businessPhototemp').val(),
		   						certAttribute0 : $('#certAttribute0temp').val(),
		   						certAttribute1 : $('#certAttribute1temp').val(),
		   						certAttribute2 : $('#certAttribute2temp').val(),
		   						doorPhoto : imgDoor,
		   						doorSrc : imgSrc,
		   						"merchantCode":merchantCode
		   					},
		   					dataType : "json",
		   					success : function(data) {
		   						if(data.result=='SUCCESS')							
		   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.UPDATESUBMITREPORT %>?updateType="+updateType,
		   							{
		   								"merchantCode":merchantCode,
		   								"province":province,
		   								"city":city,
		   								"country":country,
		   								"bankCode":bankCode,
		   								"interBank":interBank,
		   								"interBankName":interBankName,
		   								"channelNo":channelNo,
		   								"mobileNo":mobileNo,
		   								"rate":rate,
		   								"bankCardNo":bankCardNo,
		   								"certifyNo":certifyNo,
		   								"industryCode":industryCode,
		   								"bestMerchantType":bestMerchantType,
		   								"interName":interName
		   							},
		   							function(data){
		   								if(data.result=="SUCCESS"){
		   									$.gyzbadmin.alertSuccess("进件成功！",function(){
		   									},function(){
		   										this.location.reload();
		   									});
		   								}else{
		   									/* $.gyzbadmin.alertFailure(data.message);
		   									window.location.reload(); */
		   									/* $.gyzbadmin.alertFailure('进件失败:' + data.message,function(){
		   									},function(){
		   										window.location.reload();
		   									}); */
		   									alert(data.message);
		   								}
		   							},'json'
		   						  );	
		   					}
		   				});	
  	  	  	  	  	}
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