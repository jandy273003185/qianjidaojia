<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatBankType"%>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="<c:url value='/static/css/select2.min.css' />" rel="stylesheet" />
<script src="<c:url value='/static/js/select2.min.js' />"</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>微信进件</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="/static/css/bootstrap-select.css" rel="stylesheet">
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
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channelCode" name="channelNo" value="WX"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
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
									<input type="text" id="merchantCode" name="merchantCode" data-validation="notnull" data-errMsg="商户编号不能为空"  readonly value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" data-validation="notnull" data-errMsg="商户名称不能为空"  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">门店名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="storeName" data-validation="notnull" data-errMsg="门店名称不能为空"  name="storeName" value="${custInfo.custName }" style="width:90%">
								</td>
                                <td class="td-left">商户服务信息：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select name="industry" id="industry" class="width-90" data-validation="notnull" data-errMsg="商户服务信息不能为空" >
								   		<option value="">--请选择售卖商品/提供服务描述--</option>
										<option value="餐饮">餐饮</option>
										<option value="线下零售">线下零售</option>
										<option value="居民生活服务">居民生活服务</option>
										<option value="休闲娱乐">休闲娱乐</option>
										<option value="交通出行">交通出行</option>
										<option value="其他">其他</option>
									</select>	
								</td>
							</tr>
	                        <tr>
	                        	<td class="td-left">客服号码：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" id="contactPhone" data-validation="notnull" data-errMsg="客服号码不能为空"  name="contactPhone" value="${custInfo.contactPhone }" style="width:90%">
									
									<label class="label-tips" id="contactPhoneLab"></label>
								</td>
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
		                                <select class="form-control" name="merchantArea" id="merchantArea" data-validation="notnull" data-errMsg="省市区不能为空"  >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal">
										<label id="doorPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="doorPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="doorPhotoPath" data-validation="notnull" data-errMsg="门头照不能为空"  name="doorPhotoPath" />
										<input type="hidden" id="doorPhotoImageVal02">  
										<input type="file" name="doorPhoto" id="doorPhoto" onChange="showDoorPhotoImage(this)">
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success shopInteriorClick" data-target="#previewImageModal">
										<label id="shopInteriorDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="shopInteriorImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="shopInteriorPath" data-validation="notnull" data-errMsg="店内照不能为空"  name="shopInteriorPath" />
										<input type="hidden" id="shopInteriorImageVal02">  
										<input type="file" name="shopInterior" id="shopInterior" onChange="showShopInteriorImage(this)">
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
									<input type="text" id="representativeName" name="representativeName" data-validation="notnull" data-errMsg="法人真实姓名不能为空"  placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  data-validation="notnull" data-errMsg="手机号码不能为空"  value="${custInfo.mobile }" style="width:90%">
								</td>
							</tr>
                            <tr>
                            	<td class="td-left">邮箱：<span >(可选)</span></td>
								<td class="td-right"> 
									<input type="text" name="email" id="email" placeholder="请输入邮箱"  value="${custInfo.email }" style="width:90%">
								</td>
								<!-- <td class="td-left">法人证件类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" data-validation="notnull" data-errMsg="法人证件类型不能为空"  style="width:90%;"  >
										<option value="00">--身份证--</option>
										<option value="03">--军人证--</option>
										<option value="04">--警察证--</option>
										<option value="05">--港澳居民往来内地通行证--</option>
										<option value="06">--台湾居民来往大陆通行证--</option>
										<option value="07">--护照--</option>
										<option value="98">--单位证件--</option>
										<option value="06">--其他证件--</option>
									</select>
								</td> -->
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" data-validation="notnull" data-errMsg="法人身份证号码不能为空"  placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">身份证有效期：<span style="color:red;">(必填)</span></td>
									<td class="td-right">
										<input type="text" name="identityEffDate" id="identityEffDate" data-validation="notnull" data-errMsg="身份证有效期起始值不能为空"  value="${custInfo.idTermStart }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
	                                    <input type="text" name="identityValDate" id="identityValDate" data-validation="notnull" data-errMsg="身份证有效期结束值不能为空"  value="${custInfo.idTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
	                                    <input type="button" onclick="identityForever()" value="长期" />
									</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证人像面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success legalCertAttribute1Click" data-target="#previewImageModal">
									<label id="legalCertAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="legalCertAttribute1ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" >
									</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="legalCertAttribute1Path" data-validation="notnull" data-errMsg="法人身份证正面不能为空"  name="legalCertAttribute1Path" />
										<input type="hidden" id="legalCertAttribute1Val02">  
										<input type="file" name="legalCertAttribute1" id="legalCertAttribute1" onChange="showlegalCertAttribute1Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证国徽面：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal">
										<label id="legalCertAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="legalCertAttribute2ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" >
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="legalCertAttribute2Path" data-validation="notnull" data-errMsg="法人身份证背面不能为空"  name="legalCertAttribute2Path" />
										<input type="hidden" id="legalCertAttribute2Val02">  
										<input type="file" name="legalCertAttribute2" id="legalCertAttribute2" onChange="showlegalCertAttribute2Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="accountNm" name="accountNm" maxlength="100" placeholder="请输入结算账户名称"  data-validation="notnull" data-errMsg="结算账户名称不能为空"  value="${custInfo.representativeName }" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="accountNo" name="accountNo" maxlength="100" placeholder="请输入银行卡号"  data-validation="notnull" data-errMsg="结算账号不能为空"  value="${merchantBankInfo.accountNumber}" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="hidden" id="wxProvinceName" name="wxProvinceName" value="${wxAreaInfo.provinceName}" >
									<input type="hidden" id="wxAreaId" name="wxAreaId" value="${wxAreaInfo.areaId}" >
									<select name="bankProvince" id="bankProvince" class="width-90"  data-validation="notnull" data-errMsg="开户省份不能为空" >
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
									<select name="bankCity" id="bankCity" class="width-90" data-validation="notnull" data-errMsg="开户城市不能为空"  >
		                                    <option value="">--请选择市--</option>
	                                </select>
	                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									
									<select  name="bank" id="bank"  class="selectpicker show-tick " data-width="91%" data-live-search="true" data-errMsg="开户银行不能为空">
										<option value="">--请选择--</option>	
											<c:forEach items="<%=WeChatBankType.values()%>" var="status">
												<option value="${status.name}" <c:if test="${status eq merchantBankInfo.accountBank}">selected</c:if>>
													${status.name}
												</option>
											</c:forEach>
									</select>				
								
								</td>
	                            <td class="td-left">开户支行<span style="color:red;">(必填)</span></td>
	                            <td class="td-right">
	                            	 <div class="col-xs-12 col-sm-9">
											<div class="clearfix">
													<select class="form-control" id="interBankName" name="interBankName" >
														<option value="">--请选择开户银行全称--</option>
													</select>
												</div>
											</div>
	                               	<label id="interBankNameLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
                                <td class="td-left">结算费率：<span style="color:red;">(必填)</span></td>
							  <td class="td-right"> 
							  		<select id="rate" name="rate" data-validation="notnull" data-errMsg="结算费率不能为空" >
							  			<option value="">--结算费率--</option>
							  			<option value="0.38%">0.38%</option>
							  			<option value="0.39%">0.39%</option>
							  			<option value="0.4%">0.4%</option>
							  			<option value="0.45%">0.45%</option>
							  			<option value="0.48%">0.48%</option>
							  			<option value="0.49%">0.49%</option>
							  			<option value="0.5%">0.5%</option>
							  			<option value="0.55%">0.55%</option>
							  			<option value="0.58%">0.58%</option>
							  			<option value="0.59%">0.59%</option>
							  			<option value="0.6%">0.6%</option>
							  		</select>
							  		<!-- 
									<input type="text" id="rate" name="rate" placeholder="请输入结算费率"  value="" style="width:90%"> %
							   -->
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
$(function(){
	$('#interBankName').select2({
	    placeholder: '请选择开户银行全称',
	    ajax: {
	      url: window.Constants.ContextPath + "/common/info/selectWeChatBankList",
	      dataType: 'json',
	      delay: 300,
	      type: 'POST',
	      data: function (params) {
	        return {
	          bankName: params.term,
	        };
	      },
	      processResults: function (data) {
	        return {
	          results: data
	        };
	      },
	      cache: true
	    },
	    minimumInputLength: 2
	});
});

		//注册省市和开户银行省市转化并回显数据
		$(function(){
			var wxProvinceName = $("#wxProvinceName").val();
			var wxAreaId = $("#wxAreaId").val();
			$("#bankProvince").val(wxProvinceName).trigger("change", wxAreaId);
		})

		//长期
		function identityForever(){
			//$("input[name='identityValDate']").val("长期");
			$("#identityValDate").val("2099-12-31");
		}
		
		//关闭
		function exit() {
			if (confirm("您确定要关闭吗？")) {
				window.opener=null;
			
				window.open("","_self");
			
				window.close();
			}
		};
		
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
					$("#merchantCity").append("<option value=''>--请选择市--</option>");
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
    				$("#merchantArea").append("<option value=''>--请选择区--</option>");
           			for ( var area in areaList) {
           				$("#merchantArea").append(
           						"<option value='"+ areaList[area].areaId +"'>"
           								+ (area == "0" ? merchantCityName : areaList[area].areaName) + "</option>"); 
           			}
    			}else{
    				alert("市不能为空");
    			}
    		},'json'
    		);	
        }
      	
      	/***根据开户省份获取开户城市***/
      	$("#bankProvince").change(function(event, value){
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
					$("#bankCity").append("<option value=''>--请选择市--</option>");
		   			for ( var city in cityList) {
		   				$("#bankCity").append(
		   						"<option value='"+ cityList[city].areaId +"'>"
		   								+ cityList[city].cityName + "</option>"); 
		   			}
		   			//是否赋值
		   			if(null != value && '' != value && undefined != value){
		   				$("#bankCity").val(value);
		   			}
				}else{
					alert("城市不能为空");
				}
			},'json'
			);	
		})
      	
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
      	
		//上传门头照
		function showDoorPhotoImage(file){
			commonFileUpload(file, 'doorPhotoPath', 'doorPhotoDiv');
		} 
		//上传店内照
		function showShopInteriorImage(file){
			commonFileUpload(file, 'shopInteriorPath', 'shopInteriorDiv');
		}
		//上传身份证人像面面照
		function showlegalCertAttribute1Image(file){
			commonFileUpload(file, 'legalCertAttribute1Path', 'legalCertAttribute1Div');
		}
		//上传身份证国徽面照
		function showlegalCertAttribute2Image(file){
			commonFileUpload(file, 'legalCertAttribute2Path', 'legalCertAttribute2Div');
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
      	
      	//校验函数,更多类型待拓展
      	var checkFun = {
      		notnull : function(value){
      			return null != value && "" != value;
      		}
      	};
	
		//表单验证
		function formValidation(formId){
			var flag = false;
			var form = document.getElementById(formId);
			var attr = null;
			$.each(form, function(i, e) {
				attr = $(e).attr("data-validation");
				if(attr){
					if(checkFun[attr](e.value)){
						flag = true;
					} else {
						flag = false;
						alert($(e).attr("data-errMsg"));
						return false;
					}
				}
			})
			return flag;
		}

        
	   	
    	/* 图片显示 */
    	$("#legalCertAttribute1ImageDiv").show();
    	$("#legalCertAttribute2ImageDiv").show();
    	$("#doorPhotoImageDiv").show();
    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
    	$("#legalCertAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#legalCertAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	
    	$("#submitData").click(function(){
    		
   			//渠道
   			var channelNo = $("#channelCode").val();
			//商户号
   			var merchantCode = $("#merchantCode").val();
			//客户名称getCustName getShortName
   			var custName = $("#custName").val();
			//门店名称
			var storeName = $("#storeName").val();
   			// representativeName（法人姓名）representativeName
   			var representativeName = $("#representativeName").val();
   			// representativeCertNo（法人证件号）representativeCertNo
   			var representativeCertNo = $("#representativeCertNo").val();
   			//rate getRate 费率
   			var rate = $("#rate").val();
   			//身份证有效起始期getIdentityValDate
			var identityEffDate =  $("#identityEffDate").val();
			//身份证有效截止期
			var identityValDate =  $("#identityValDate").val();
			//industry（行业信息）getIndustryCode
   			var industryCode = $("#industry").val();
			//商户地址省
   			var merchantProvince = $("#merchantProvince").val();
   			// merchantCity
   			var merchantCity = $("#merchantCity").val();
   			// merchantArea
   			var merchantArea =$("#merchantArea").val();
   			// cprRegAddr（商户详细地址）getCprRegAddr
   			var cprRegAddr = $("#cprRegAddr").val();
   			//accountNm（结算名）representativeName
   			var accountNm = $("#accountNm").val();
   			// accountNo（结算银行卡号）getAccountNo
   			var accountNo = $("#accountNo").val();
   			// interBankName(开户支行名称)getInterBankName
   			var interBankName = $("#interBankName").select2("data")[0].text;
   			//bank（开户行）getBankName
   			var bank = $("#bank").val();
   			//  bankProvince（开户行所在地区）getBankCity
   			var bankProvince = $("#bankProvince").val();
   			// bankCity  
   			var bankCity = $("#bankCity").val();
   			//身份证人像照
			var legalCertAttribute1Path = $("#legalCertAttribute1Path").val(); 
			//身份证国徽照
			var legalCertAttribute2Path = $("#legalCertAttribute2Path").val(); 
			//门头照
			var doorPhotoPath = $("#doorPhotoPath").val(); 
			//店内照
			var shopInteriorPath = $("#shopInteriorPath").val(); 
			//客服电话getCustomerPhone
			var contactPhone =  $("#contactPhone").val(); 
   			//手机号 getPhone
   			var mobileNo = $("#mobileNo").val();
   			//邮箱 email
   			var email = $("#email").val();
   			
			
			//表单数据验证
			if(!formValidation("merchantForm")){
				return false;
			}
			
			$.ajax({
  					type : "POST",
  					url : window.Constants.ContextPath + "/merchant/reported/weChatAppMerchantReportSubmit",
  					data :{
  						"channelNo" : channelNo,
  						"merchantCode" : merchantCode,
  						"custName" : custName,
  						"storeName" : storeName,
  						"representativeName" : representativeName,
  						"representativeCertNo" : representativeCertNo,
  						"rate" : rate,
  						"identityEffDate" : identityEffDate,
  						"identityValDate" : identityValDate,
  						"industryCode" : industryCode,
  						"merchantProvince" : merchantProvince,
  						"merchantCity" : merchantCity,
  						"merchantArea" : merchantArea,
  						"cprRegAddr" : cprRegAddr,
  						"accountNm" : accountNm,
  						"accountNo" : accountNo,
  						"interBankName" : interBankName,
  						"bank" : bank,
  						"bankProvince" : bankProvince,
  						"bankCity" : bankCity,
  						"legalCertAttribute1Path" : legalCertAttribute1Path,
  						"legalCertAttribute2Path" : legalCertAttribute2Path,
  						"doorPhotoPath" : doorPhotoPath,
  						"shopInteriorPath" : shopInteriorPath,
  						"contactPhone" : contactPhone,
  						"mobileNo" : mobileNo,
  						"email" : email,
  					},
  					dataType : "json",
  					success : function(data) {
  						if(data.result=='SUCCESS'){							
  							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
					
  							},function(){
  								window.opener=null;
	   							window.open("","_self");
	   			   				window.close();
							});
						}else{
							alert(data.message);
						}
  						
  					}
  			});
    	
   	});  
</script>
</body>
</html>