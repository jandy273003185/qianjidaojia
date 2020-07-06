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
					<input type="hidden" id="channelCode" name="channelNo" value="WX"/>
				<%-- 	<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/> --%>
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
									<input type="text" id="merchantCode" name="merchantCode"  readonly value="${merchantDetailInfoWechat.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" readonly  value="${merchantDetailInfoWechat.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">门店名称：<span style="color:red;"></span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="storeName"  name="storeName" readonly value="${merchantDetailInfoWechat.storeName }" style="width:90%">
								</td>
                                <td class="td-left">商户服务信息：<span style="color:red;"></span></td>
								<td class="td-right"> 
								   <select name="industry" id="industry" class="width-90" disabled="disabled" data-validation="notnull" data-errMsg="商户服务信息不能为空" >
								   		<option value="">--请选择售卖商品/提供服务描述--</option>
										<option value="餐饮"       <c:if test="${'餐饮' == merchantDetailInfoWechat.industryCode}">selected</c:if> >餐饮</option>
										<option value="线下零售"    <c:if test="${'线下零售' == merchantDetailInfoWechat.industryCode}">selected</c:if>>线下零售</option>
										<option value="居民生活服务" <c:if test="${'居民生活服务' == merchantDetailInfoWechat.industryCode}">selected</c:if>>居民生活服务</option>
										<option value="休闲娱乐"    <c:if test="${'休闲娱乐' == merchantDetailInfoWechat.industryCode}">selected</c:if>>休闲娱乐</option>
										<option value="交通出行"    <c:if test="${'休闲娱乐' == merchantDetailInfoWechat.industryCode}">selected</c:if>>交通出行</option>
										<option value="其他"       <c:if test="${'其他' == merchantDetailInfoWechat.industryCode}">selected</c:if>>其他</option>
									</select>	
								</td>
							</tr>
	                        <tr>
	                        	<td class="td-left">客服号码：<span style="color:red;"></span></td>
								<td class="td-right">
									<input type="text" id="contactPhone" readonly  name="contactPhone" value="${merchantDetailInfoWechat.contactPhone }" style="width:90%">
									
									<label class="label-tips" id="contactPhoneLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
										 <input type="text" id="merchantProvince" readonly name="merchantProvince" value="${merchantDetailInfoWechat.merchantProvince }" style="width:90%">
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
	                              		  <input type="text" id="merchantCity" readonly name="merchantCity" value="${merchantDetailInfoWechat.merchantCity }" style="width:90%">
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
	                              		<input type="hidden" id="merchantAreaQ"   name="merchantAreaQ" value="${merchantDetailInfoWechat.merchantArea }" style="width:90%">
		                                <select class="form-control" name="merchantArea" disabled="disabled" id="merchantArea"   >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr" readonly  value="${merchantDetailInfoWechat.cprRegAddr }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal">
										<label id="doorPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="doorPhotoImageDiv" src="${merchantDetailInfoWechat.doorPhotoPath }" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									
								</td>
							</tr>
							<tr id="shopInteriorType" style = "display:">
								<td class="td-left">店内照：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success shopInteriorClick" data-target="#previewImageModal">
										<label id="shopInteriorDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="shopInteriorImageDiv" src="${merchantDetailInfoWechat.shopInteriorPath }" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" id="representativeName" readonly name="representativeName"  value="${merchantDetailInfoWechat.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" readonly value="${merchantDetailInfoWechat.mobileNo }" style="width:90%">
								</td>
							</tr>
                            <tr>
                            	<td class="td-left">邮箱：<span >(可选)</span></td>
								<td class="td-right"> 
									<input type="text" name="email" id="email" readonly    value="${merchantDetailInfoWechat.email }" style="width:90%">
								</td>
								
								<td class="td-left">法人身份证号码：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" readonly id="representativeCertNo"  value="${merchantDetailInfoWechat.representativeCertNo }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">身份证有效期：<span style="color:red;"></span></td>
									<td class="td-right">
										<input type="text" name="identityEffDate" id="identityEffDate" readonly  value="${merchantDetailInfoWechat.identityEffDate }"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
	                                    <input type="text" name="identityValDate" id="identityValDate" readonly value="${merchantDetailInfoWechat.identityValDate }"   style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证人像面：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success legalCertAttribute1Click" data-target="#previewImageModal">
									<label id="legalCertAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="legalCertAttribute1ImageDiv" src="${merchantDetailInfoWechat.legalCertAttribute1Path }" onClick="bigImg(this);" style="width: 100%; height: 100%;" >
									</label>
									</a>
									
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证国徽面：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal">
										<label id="legalCertAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="legalCertAttribute2ImageDiv" src="${merchantDetailInfoWechat.legalCertAttribute2Path }"  onClick="bigImg(this);" style="width: 100%; height: 100%;" >
										</label>
									</a>
									
								</td>
							</tr>
							
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" id="accountNm" name="accountNm" readonly  maxlength="100"  value="${merchantDetailInfoWechat.accountNm }" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" id="accountNo" name="accountNo" readonly maxlength="100"  value="${merchantDetailInfoWechat.accountNo}" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;"></span></td>
								<td class="td-right"> 			
									<input type="text" id="bankProvince" name="bankProvince" readonly value="${merchantDetailInfoWechat.bankProvince}" >
									
	                                <label id="bankProvinceLabel" class="label-tips"></label>
								</td>
								<td class="td-left">开户城市：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="hidden" id="bankCityQ" name="bankCityQ" value="${merchantDetailInfoWechat.bankCity}" >
									<select name="bankCity" id="bankCity" class="width-90" disabled="disabled"  >
		                                    <option value="">--请选择市--</option>
	                                </select>
	                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：<span style="color:red;"></span></td>
								<td class="td-right"> 
									<input type="text" id="bank" name="bank" readonly value="${merchantDetailInfoWechat.bank}" >
									
								
								</td>
	                            <td class="td-left">开户支行<span style="color:red;"></span></td>
	                            <td class="td-right">
	                            	 <div class="col-xs-12 col-sm-9">
											<div class="clearfix">
													<input type="text" id="interBankName" readonly  name="interBankName" value="${merchantDetailInfoWechat.interBankName}" >
											
												
												</div>
											</div>
	                               	<label id="interBankNameLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
                                <td class="td-left">结算费率：<span style="color:red;"></span></td>
							  <td class="td-right"> 
							  		<input type="text" id="rate" name="rate"  readonly value="${merchantDetailInfoWechat.rate}" >
							  </td>
							    <td class="td-left">公众号APPID：<span style="color:red;"></span></td>
							    <td class="td-right"> 
							  		<input type="text" id="mpAppid" name="mpAppid"  readonly value="${merchantDetailInfoWechat.mpAppid}" >
							    </td>
							</tr>
							 <tr>
                                <td class="td-left">微信返回签约链接：<span style="color:red;"></span></td>
							    <td class="td-right"> 
							  		<input type="text" id="signUrl" name="signUrl"   value="${merchantDetailInfoWechat.signUrl}" >
							    </td>
							   
							</tr>
							
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">签约二维码：<span style="color:red;"></span></td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal">
										<label id="signQrcode" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="signQrcode" src="${merchantDetailInfoWechat.signQrcode }"  onClick="bigImg(this);" style="width: 100%; height: 100%;" >
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
		/***根据开户省份获取开户城市***/
		var province = $("#bankProvince").val();
		var channelCode =$("#channelCode").val();
		
		var bankCityQ =$("#bankCityQ").val();
		
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
						var aa = "<option value='"+ cityList[city].areaId +"'";
		   				
		   				if ( cityList[city].areaId == bankCityQ) 
							  {
								 aa += "selected";
							  }	       				
		   					aa += ">"+ cityList[city].cityName  + "</option>";
						$("#bankCity").append(aa); 
					}
			}
		},'json'
		);	



/***根据城市获取注册地县区***/

	var merchantCityName = $("#merchantCity").val();
	var channelCode =$("#channelCode").val();
	
	var merchantAreaQ =$("#merchantAreaQ").val();
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
					
					var aa = "<option value='"+ areaList[area].areaId +"'";
	   				
	   				if ( areaList[area].areaId == merchantAreaQ) 
						  {
							 aa += "selected";
						  }	       				
	   					aa += ">"+ areaList[area].areaName  + "</option>";
					
					
					$("#merchantArea").append(aa); 
				}
		}
	},'json'
	);




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
	
		

</script>
</body>
</html>