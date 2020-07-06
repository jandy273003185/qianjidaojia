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
<link href="<c:url value='/static/css/select2.min.css' />" rel="stylesheet" />
<script src="<c:url value='/static/js/select2.min.js' />"</script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>微信修改结算、联系信息</title>
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
					<div class="col-md-12 col-xs-12">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab">
								<li class="active">
									<a data-toggle="tab" href="#home" aria-expanded="false">结算账户</a>
								</li>

								<li class="">
									<a data-toggle="tab" href="#profile" aria-expanded="true">联系信息</a>
								</li>

							</ul>

							<div class="tab-content">
								<div id="home" class="tab-pane active">
									<form class="form-horizontal" id="accountForm" novalidate="novalidate">
										<input type="hidden" id="channelCode" name="channelCode" value="WX" >
										<input type="hidden" id="patchNo" name="patchNo" value="${merchantDetailInfo.patchNo}" >
										<input type="hidden" id="subMchId" name="subMchId" value="${merchantDetailInfo.outMerchantCode}" >
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="merchantCode">商户号:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="merchantCode" id="merchantCode" readonly value="${custInfo.merchantCode }" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>

										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="merchantName">商户名称:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="merchantName" id="merchantName" readonly value="${custInfo.custName }" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>

										<div class="space-2"></div>
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="accountNo">银行卡号:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="accountNo" id="accountNo" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>

										<div class="space-2"></div>

										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="bank">开户银行:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<select name="bank" id="bank" >
														<option value="">--请选择--</option>
														<c:forEach items="<%=WeChatBankType.values()%>" var="status">
															<option value="${status.name}" >
																${status.name}
															</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="interBankName">开户银行全称:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<select class="form-control" id="interBankName" name="interBankName" >
														<option value="">--请选择开户银行全称--</option>
													</select>
													
													<%-- <input type="text" name="interBankName" id="interBankName" value="${merchantDetailInfo.branchBankName}" class="col-xs-12 col-sm-6"  > --%>
												</div>
											</div>
										</div>

										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="bankProvince"><span style="color:red;" >*</span>开户省份:</label>
											
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<select name="bankProvince" id="bankProvince" data-validation="notnull" data-errMsg="开户省份不能为空"  onchange="getCity();" >
					                                    <option value="">--请选择省--</option>
					                                    <c:if test="${not empty weChatAppAreaInfoList }">
					                                        <c:forEach items="${weChatAppAreaInfoList }" var="province">
					                                            <option id="${province.provinceName}" value="${province.provinceName}" >
					                                                ${province.provinceName}
					                                            </option>
					                                        </c:forEach>
					                                    </c:if>
					                                </select>
												</div>
											</div>
										</div>
										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="bankCity"><span style="color:red;" >*</span>开户城市:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<select name="bankCity" id="bankCity" data-validation="notnull" data-errMsg="开户城市不能为空"  >
		                                    			<option value="">--请选择市--</option>
	                                				</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3"></label>
											<div class="col-xs-12 col-sm-9">
												<input type="button" class="btn btn-info" onclick="submitBank()" value="提交" >
												
											</div>
										</div>
									</form>
								</div>

								<div id="profile" class="tab-pane">
									<form class="form-horizontal" id="contactForm" novalidate="novalidate">
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="merchantCode">商户号:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="merchantCode" id="merchantCode" readonly value="${custInfo.merchantCode }" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>

										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="merchantName">商户名称:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="merchantName" id="merchantName" readonly value="${custInfo.custName }" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>

										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="accountNo">商户简称:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="shortName" id="shortName" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>
										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="accountNo">手机号码:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="mobileNo" id="mobileNo" value="${merchantDetailInfo.mobileNo}" class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>


										<div class="space-2"></div>
										
										<div class="form-group">
											<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="accountNo">邮箱:</label>
											<div class="col-xs-12 col-sm-9">
												<div class="clearfix">
													<input type="text" name="email" id="email"  class="col-xs-12 col-sm-6"  >
												</div>
											</div>
										</div>


										<div class="space-2"></div>
									
										<div class="form-group">
											<label class="col-sm-3"></label>
											<div class="col-xs-12 col-sm-9">
												<input type="button" class="btn btn-info" onclick="submitContact()" value="提交" >
												
											</div>
										</div>
									</form>
								</div>

							</div>
						</div>
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


<script type="text/javascript">
/* jQuery(function($) {
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: false,
		ignore: "",
		rules: {
			bankProvince: {
				required: true
			},
			bankCity: {
				required: true
			}
		},
		messages: {
			bankProvince: "请选择开户省份",
			bankCity: "请选择开户城市"
		},
		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
			$(e).remove();
		},
		errorPlacement: function (error, element) {
			if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			
		},
		invalidHandler: function (form) {
			
		}
	});
}) */

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


//表单验证组件
var checkFun = {
      		notnull : function(value){
      			if(null == value || "" == value) {
      				return false;
      			} else {
      				return true;
      			}
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

		//修改结算信息
		function submitBank(){
			//debugger;
			//表单数据验证
			if(!formValidation("accountForm")){
				return false;
			}
			
			var patchNo = $("#patchNo").val();
			var subMchId = $("#subMchId").val();
			var merchantCode = $("#merchantCode").val();
			var merchantName = $("#merchantName").val();
			var accountNo = $("#accountNo").val();
			var bank = $("#bank").val();
			var interBankName = $("#interBankName").select2("data")[0].text;
			var bankProvince = $("#bankProvince").val();
			var bankCity = $("#bankCity").val();
			
			$.ajax({
					type : "POST",
					url : window.Constants.ContextPath + "/merchant/merchantReported/weChatAppModifySettlement",
					data :{
						"patchNo" : patchNo,
						"subMchId" : subMchId,
						"merchantCode" : merchantCode,
						"merchantName" : merchantName,
						"accountNo" : accountNo,
						"bank" : bank,
						"interBankName" : interBankName,
						"bankProvince" : bankProvince,
						"bankCity" : bankCity
					},
					dataType : "json",
					success : function(data) {
						if(data.code=='SUCCESS'){
							alert(data.message);
							window.opener=null;
   							window.open("","_self");
   			   				window.close();
						}else{
							alert(data.message);
						}
						
					}
			});
		}
		
		//修改联系信息
		function submitContact(){
			debugger;
			
			var patchNo = $("#patchNo").val();
			var subMchId = $("#subMchId").val();
			var merchantCode = $("#merchantCode").val();
			var merchantName = $("#merchantName").val();
			
			var shortName = $("#shortName").val();
			var mobileNo = $("#mobileNo").val();
			var email = $("#email").val();
			
			$.ajax({
					type : "POST",
					url : window.Constants.ContextPath + "/merchant/merchantReported/weChatAppModifyContact",
					data :{
						"patchNo" : patchNo,
						"subMchId" : subMchId,
						"merchantCode" : merchantCode,
						"merchantName" : merchantName,
						"shortName" : shortName,
						"mobileNo" : mobileNo,
						"email" : email
					},
					dataType : "json",
					success : function(data) {
						if(data.code=='SUCCESS'){
							alert(data.message);
							window.opener=null;
   							window.open("","_self");
   			   				window.close();
						}else{
							alert(data.message);
						}
						
					}
			});
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
			$("#bankCity").append("<option value=''>--请选择市--</option>");
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
</script>
</body>
</html>