<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ page import="com.qifenqian.bms.basemanager.merchant.bean.Merchant" %>
<%@ page import="com.qifenqian.bms.basemanager.city.CityPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>服务商审核</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.scss' />" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	.uploadImage{ float:left;
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
			}
	</style>
</head>
<script type="text/javascript">


/********************图片预览***********************/

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


/** 营业执照预览 **/
function showBusinessPhotoImage(obj){
	 var divObj = document.getElementById("businessPhotoDiv");
	 var imageObj = document.getElementById("businessPhotoImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}

/** 营业执照点击预览 **/
$('.businessPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("businessPhoto");
	return previewImage(divObj,imageObj,obj);
});


/** 身份证正面预览 **/
function showCertAttribute1Image(obj){
	 var divObj = document.getElementById("certAttribute1Div");
	 var imageObj = document.getElementById("certAttribute1Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}


/** 身份证正面点击预览 **/
$('.certAttribute1Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute1");
	return previewImage(divObj,imageObj,obj);
});


/** 身份证背面预览 **/
function showCertAttribute2Image(obj){
	 var divObj = document.getElementById("certAttribute2Div");
	 var imageObj = document.getElementById("certAttribute2Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}

/** 身份证背面点击预览 **/
$('.certAttribute2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute2");
	return previewImage(divObj,imageObj,obj);
});

/** 开户许可证预览 **/
function showOpenAccountImage(obj){
	 var divObj = document.getElementById("openAccountDiv");
	 var imageObj = document.getElementById("openAccountImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}

/** 开户许可证背面点击预览 **/
$('.openAccountClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("openAccount");
	return previewImage(divObj,imageObj,obj);
});

/** 银行卡预览 **/
function showBankCardPhotoImage(obj){
	 var divObj = document.getElementById("bankCardPhotoDiv");
	 var imageObj = document.getElementById("bankCardPhotoImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}

/** 银行卡点击预览 **/
$('.bankCardPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("bankCardPhoto");
	return previewImage(divObj,imageObj,obj);
});

//对公对私账户图片
$(function(){
	$('#compMainAcctType').on('change', function (e) {
		//bankCardPhoto_
		let compMainAcctType = $('#compMainAcctType').val();
		if(compMainAcctType == '01'){
			$('#openAccount_').show();
			$('#bankCardPhoto_').hide();
		}else{
			$('#bankCardPhoto_').show();
			$('#openAccount_').hide();
		}
	});
})

/**服务商类型 **/
function selCustType(){
	//个人
	if($("#custType").val() =='0'  ){
		$('#businessCodeId_').hide();
		$('#businessPhotoId_').hide();
	}
	//企业 个体户
	if($("#custType").val() =='1' ||$("#custType").val() =='2'){
		$('#businessCodeId_').show();
		$('#businessPhotoId_').show();
	}
}

function businessForever(){
	$("input[name='businessTermEnd']").val("2099-12-31");
	$("#businessTermEnd").attr("value","2099-12-31");
}

</script>
<body>
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>

			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>

				<!-- 主内容 -->
				<div class="page-content">
					<div class="row">
					<div class="col-xs-12">
						
						<!-- 回显对公对视 -->
						<input type="hidden" id="compMainAcctTypeQ" value="${merchantVo.compMainAcctType}" />
						<!-- 回显个人企业 -->
						<input type="hidden" id="custTypeQ"  value="${merchantVo.custType}" />
					
						<!-- 市回显用到 -->
						<input type="hidden" id="cityQ"  value="${merchantVo.city}"/>
						<input type="hidden" id="countryQ"  value="${merchantVo.country}"/>
						<!-- 开户银行市回显用 -->
						<input type="hidden" id="bankCityNameQ"  value="${merchantVo.bankCityName}"/>
						<input type="hidden" id="branchBankQ"  value="${merchantVo.branchBank}"/>
						
						<input type="hidden" id="custId"  value="${merchantVo.custId}"/>
                    <table id="merchant_table" class="list-table">
					<tbody>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">服务商信息</td>
						</tr>
						<tr></tr>
                        <tr>
							<td class="td-left">服务商编号：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="merchantCode" readonly="readonly" name="merchantCode" value="${merchantVo.merchantCode }" placeholder="请输入手机号或邮箱"  maxlength="50" style="width:90%">
								
								<label class="label-tips" id="merchantAccountLab"></label>
							</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
						</tr>
						<tr>
							<td class="td-left">服务商类型：<span style="color:red;">（必填）</span></td>
							<td class="td-right">
							   <select name="custType" class="width-90" id="custType"  disabled="disabled">
								
										<option value="1" <c:if test="${merchantVo.custType =='1'}">selected</c:if>>企业</option>								
									
										<option value="0" <c:if test="${merchantVo.custType =='0'}">selected</c:if>>个人</option>
		
										<option value="2" <c:if test="${merchantVo.custType =='2' }">selected</c:if>>个体户</option>
									
								</select>
							</td>
							<td class="td-left">服务商标志：</td>
							<td class="td-right">
							   <select name="merchantFlag" class="width-90" id="merchantFlag" disabled="disabled" >
							   		<option value="3">代理商</option>
								</select>
							</td>
						</tr>
                        <tr>
						    <td class="td-left" width="18%">服务商名称：<span style="color:red;">（必填)</span></td>
							<td class="td-right" width="32%">
								<input type="text" id="custName" name="custName"  readonly="readonly" value="${merchantVo.custName}" maxlength="100"  placeholder="请输入服务商名称" style="width:90%">
								<label class="label-tips" id="custNameLab"></label>
							</td>
							<td class="td-left" width="18%">服务商简称：<span style="color:red;">（必填)</span></td>
							<td class="td-right" width="32%">
							    <input type="text" id="shortName" name="shortName" readonly="readonly"  value="${merchantVo.shortName}" placeholder="请输入服务商简称" style="width:90%">
								<label class="label-tips" id="shortNameLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">服务商邮箱：</td>
							<td class="td-right">
								<input type="text" id="merchantEmail" name="merchantEmail" readonly="readonly"  value="${merchantVo.merchantEmail}" placeholder="请输入服务商邮箱" style="width:90%">
								
								<label class="label-tips" id="merchantEmailLab"></label>
							</td>
							<td class="td-left">客服号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="contactPhone" name="contactPhone" readonly="readonly"  value="${merchantVo.contactPhone}" placeholder="请输入客服号码" style="width:90%">
								
								<label class="label-tips" id="contactPhoneLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">服务商地址：<span style="color:red;">（必填)</span></td>
							<td class="td-right" colspan="3">
								<div class="col-xs-2 pd0" style="padding:0">
                                   <select class="form-control" id="province" disabled="disabled">
                                       <c:if test="${not empty provincelist_ }">
                                        	<option value="">--请选择--</option>
							               <c:forEach items="${provincelist_ }" var="prov">
							              			 <option value="${prov.provinceId }" <c:if test='${merchantVo.province == prov.provinceId }'>selected</c:if>>${prov.provinceName }</option>
							               </c:forEach>
		               				   </c:if>
                                   </select>
									<label class="label-tips" id="provinceLab"></label>
                                </div>
                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
                                <select class="form-control" id="city"  disabled="disabled">
                                    <option value="" id="cityDef">--请选择--</option>
                                </select>
									<label class="label-tips" id="cityLab"></label>
                                </div>
                                <div class="col-xs-2 pd0" style="padding:0">
                                <select class="form-control" id="country" disabled="disabled">
                                    <option value="" id="areaDef">--请选择--</option>
                                </select>
									<label class="label-tips" id="countryLab"></label>
                                </div>
                                <div class="col-xs-5 pd0" >
                                    <input type="text" id="custAdd" name="custAdd" readonly="readonly"  value="${merchantVo.custAdd}" placeholder="详细地址" style="width:100%">
                                	<label class="label-tips" id="custAddLab"></label>
                                </div>
							</td>
					    </tr>
						<tr id="businessCodeId_"  style="display: " class="tab-pane active">
							<td class="td-left" id="businessCodeId">营业执照编号：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="businessLicense" name="businessLicense" readonly="readonly" value="${merchantVo.businessLicense}" placeholder="请输入营业执照" style="width:90%">
								
								<label class="label-tips" id="businessLicenseLab"></label>
							</td>
							<td class="td-left" id="businessTimeId">营业执照有效期：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="businessTermStart" name="businessTermStart" readonly="readonly"  value="${merchantVo.businessTermStart}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
								<label class="label-tips" id="businessTermLabStart"></label>
								-
								<input type="text" id="businessTermEnd" name="businessTermEnd" readonly="readonly" value="${merchantVo.businessTermEnd}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
								<label class="label-tips" id="businessTermLabEnd"></label>
								
							</td>
						</tr>
						<tr id="businessPhotoId_"  style="display: ">
							<td class="td-left" id="businessPhotoId">营业执照扫描件：<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
									<label id="businessPhotoDiv" class="uploadImage" >
										<img  id="businessPhotoImage" src="${picturePathOld.bussinessPath}" onclick="bigImg(this);"  style="width:100%;height:100%;"  />
									</label>
								</a>
								
								<label class="label-tips" id="businessPhotoLabel" style="float:left;margin-top:88" ></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">所属业务人员：</td>
							<td class="td-right">
								<sevenpay:selectSysUserTag name="custManager" id="custManager" defaultValue="${merchantVo.custManager}"/>
							</td>
							<%-- <td class="td-left" width="18%">所属代理商：</td>
							<td class="td-right" width="32%">
								<sevenpay:selectAgentMerchantTag name="agentName" id="agentName" defaultValue="${merchantVo.agentName }"/>
							</td> --%>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="td-left">法人真实姓名：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="representativeName" name="representativeName" readonly="readonly"  value="${merchantVo.representativeName}" placeholder="请输入法人真实姓名" maxlength="50" style="width:90%">
								<label class="label-tips" id="representativeNameLab"></label>
							</td>
							<td class="td-left">法人身份证号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" name="representativeCertNo" id="representativeCertNo" readonly="readonly" value="${merchantVo.representativeCertNo}" placeholder="请输入法人身份证号码" style="width:90%">
								<label class="label-tips" id="representativeCertNoLab"></label>
							</td>
						</tr>
						<tr >
							<td class="td-left" >法人身份证正面<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
								<label id="certAttribute1Div" class="uploadImage">
								        <img  id="certAttribute1Image" onClick="bigImg(this);"   src="${picturePathOld.idCardOPath}"  style="width:100%;height:100%;"/>
								</label>
								</a>
								
							</td>
						</tr>
						<tr>
							<td class="td-left" >法人身份证背面<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal" >
									<label id="certAttribute2Div" class="uploadImage">
									        <img  id="certAttribute2Image" onClick="bigImg(this);" src="${picturePathOld.idCardFPath}"   style="width:100%;height:100%;"/>
									</label>
								</a>
								
							</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td>
						</tr>
						<tr></tr>
                        <tr>
							<td class="td-left">联系人姓名：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="contactName" name="contactName" readonly="readonly"  value="${merchantVo.contactName}" placeholder="请输入联系人姓名" maxlength="50" style="width:90%">
								<label class="label-tips" id="contactNameLab"></label>
							</td>
							<td class="td-left">联系人手机号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" name="contactMobile" id="contactMobile" readonly="readonly"  value="${merchantVo.contactMobile }" placeholder="请输入联系人手机号码" style="width:90%">
								<label class="label-tips" id="contactMobileLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">推荐人：</td>
							<td class="td-right">
								<input type="text" id="referrer" name="referrer" readonly="readonly"  value="${merchantVo.referrer }" placeholder="请输入推荐人姓名" maxlength="50" style="width:90%">
								<label class="label-tips" id="referrerLab"></label>
							</td>
							<td class="td-left">服务商级别：</td>
							<td class="td-right">
								<input type="text" name="serviceLevel" id="serviceLevel"  readonly="readonly" value="${merchantVo.serviceLevel }" placeholder="请输入服务商级别" style="width:90%">
								<label class="label-tips" id="serviceLevelLab"></label>
							</td>
						</tr>
						<tr id="next_id">
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
						</tr>
						<tr>
							<td class="td-left">结算账号<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="compMainAcct" name="compMainAcct" readonly="readonly" value="${merchantVo.compMainAcct }" maxlength="100" placeholder="请输入结算账号" style="width:90%">
								<label class="label-tips" id="compMainAcctLab"></label>
							</td>
							<td class="td-left">开户人：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="bankAcctName" name="bankAcctName" readonly="readonly"  value="${merchantVo.bankAcctName }" placeholder="请输入开户人" style="width:90%">
								
								<label class="label-tips" id="bankAcctNameLab"></label>
							</td>
						</tr>
                        <tr>
							<td class="td-left">开户省份：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select class="width-90" id="bankProvinceName" disabled="disabled">
	                                   <c:if test="${not empty provincelist }">
                                        	<option value="">--请选择--</option>
						               <c:forEach items="${provincelist }" var="prov">   
						                   <option value="${prov.provinceId }" <c:if test='${merchantVo.bankProvinceName == prov.provinceId }'>selected</c:if>>${prov.provinceName }</option>
						               </c:forEach>
		               				</c:if>
	                               </select>
							</td>
							<td class="td-left">开户城市：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
							
							   <select class="width-90" id="bankCityName" disabled="disabled">
                                  <option value="" id="backCityDef">--请选择--</option>
                               </select>
							</td>
						</tr>
						<tr>
							<td class="td-left">开户银行：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select class="width-90" id="compAcctBank" name="compAcctBank" disabled="disabled">
                                <c:if test="${not empty banklist }">
                                   <option value="">--请选择--</option>
					               <c:forEach items="${banklist }" var="bank">
					                   <option value="${bank.bankCode }" <c:if test='${merchantVo.compAcctBank == bank.bankCode  }'>selected</c:if> >${bank.bankName }</option>
					               </c:forEach>
	               				</c:if>
                                </select>
								<label class="label-tips" id="compAcctBankLab"></label>
							</td>
							<td class="td-left">开户支行：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select name="branchBank" id="branchBank" class="width-90" disabled="disabled" >
                                    <option value="">--请选择支行--</option>
                                </select>
                               	<label id="branchBankLab" class="label-tips"></label>
							</td>
						</tr>
                        <tr>
							<td class="td-left">结算类型：<span style="color:red;">（必填)</span></td>
							<td class="td-right" class="width-90" >
								<select class="width-90 form-control"   id="compMainAcctType" disabled="disabled">
                                   
                                    <option  value="01"  <c:if test="${merchantVo.compMainAcctType == '01'  }">selected</c:if>>对公</option>
                                    <option  value="02"  <c:if test="${merchantVo.compMainAcctType == '02'  }">selected</c:if>>对私</option>
                                </select>
							</td>
						</tr>
						<tr id="bankCardPhoto_"  style="display: none" class="tab-pane active">
							<td class="td-left" id="cnm">银行卡照<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal" >
									<label id="bankCardPhotoDiv" class="uploadImage">
										<img  id="bankCardPhotoImage" onClick="bigImg(this);" src="${picturePathOld.bankCardPath}"  style="width:100%;height:100%;"/>
									</label>
								</a>
								
							</td>
						</tr>

						<tr id="openAccount_" class="tab-pane " >
							<td class="td-left" >开户许可证<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
									<label id="openAccountDiv" class="uploadImage">
									        <img  id="openAccountImage" onClick="bigImg(this);" src="${picturePathOld.openAccountPath}"  style="width:100%;height:100%;"/>
									</label>
								</a>
								
							</td>
						</tr>
					</tbody>
					</table>
                        <div style="margin:50px 0 0 0;text-align:center">
                        	<a href="<%=request.getContextPath()+"/merchant/serviceParenter/list"%>"  class="btn btn-default" >关闭</a>    		
                        	
                        	<button type="button" class="btn btn-primary addServiceBtn" onclick="confirmAgencyAudit('yes')">审核通过</button>
		           			<button type="button" class="btn btn-primary addServiceBtn" onclick="confirmAgencyAudit('no')">审核不通过</button>
                        
                        </div>
                        
					</div>
					</div>
				</div><!-- /.page-content -->
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				    <div class="modal-dialog showDiv" >
	         			<div id="showImageDiv" style="width:100%;height:100%;">
				           <img id="showImage" style="width:100%;height:100%;">
				        </div>
				     </div>
				</div>
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>

			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->

</body>
<script>

/* 审核 */
function confirmAgencyAudit(obj){
	var custId = $("#custId").val();;
	var fals =obj;

	$.post(window.Constants.ContextPath +'/merchant/serviceParenter/audit',{
		'custId':custId,
		'fals':fals
	},function(data){
		
		$.gyzbadmin.alertSuccess(data.message, null, function(){	
			window.location.href=window.Constants.ContextPath +"/merchant/serviceParenter/list"; // 强迫浏览器刷新当前页面
		});
		/* if(data.result=="SUCCESS"){
			$.gyzbadmin.alertSuccess('修改服务商成功！', null, function(){	
				window.location.href=window.Constants.ContextPath +"/merchant/serviceParenter/list"; // 强迫浏览器刷新当前页面
			});
		}
		if(data.result=="messageErr"){
			$.gyzbadmin.alertSuccess('审核成功发送信息异常', null, function(){	
				window.location.href=window.Constants.ContextPath +"/merchant/serviceParenter/list";
			});
		} */

	},'json')
}	
//个人 和企业 显示和隐藏标签
	//个人
	if($("#custTypeQ").val() =='0'  ){
		$('#businessCodeId_').hide();
		$('#businessPhotoId_').hide();
	}
	//企业 个体户
	if($("#custTypeQ").val() =='1' ||$("#custTypeQ").val() =='2'){
		$('#businessCodeId_').show();
		$('#businessPhotoId_').show();
	}
	
	//对公对视回显
	var compMainAcctType = $('#compMainAcctTypeQ').val();
	if(compMainAcctType == '01'){
		$('#openAccount_').show();
		$('#bankCardPhoto_').hide();
	}else{
		$('#bankCardPhoto_').show();
		$('#openAccount_').hide();
	}

//市回显
var cityQ =$("#cityQ").val().trim();
var provVal = $("#province").val().trim();


$.ajax({
	type:"POST",
	dataType:"json",
	url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.GRTCITYLIST %>',
	data:
	{
		"province" 	: provVal,
		"choiceType" : "city"
	},
	success:function(data){
		if(data.result=="SUCCESS"){
			var cityList = data.cityList;
			for ( var city in cityList) {
				var aa ="<option value='"+ cityList[city].cityId +"'";
				 if (cityList[city].cityId == cityQ) {
					 aa+="selected";
				 }
				 aa += ">" + cityList[city].cityName + "</option>";
				$("#city").append(aa);	
			}
		}
	}
})
//回显区==========
var countryQ =  $("#countryQ").val().trim();
var city = $("#city").val().trim();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.GRTCITYLIST %>',
		data:
		{
			"city" 	: city,
			"choiceType" : "area"
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				var areaList = data.areaList;
				for ( var area in areaList) {
					var aa ="<option value='"+ areaList[area].areaId +"'";
					if ( areaList[area].areaId  == countryQ) 
					  {
						 aa+="selected";
					  }
					aa += ">" +  areaList[area].areaName+ "</option>";
					$("#country").append(aa);
				}
			}else{
			}
		}
	})	
//==开户银行市回显=====

	var provValv = $("#bankProvinceName").val().trim();
	//获取婴孩回显市
	var bankCityNameQ =	$("#bankCityNameQ").val().trim();
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=CityPath.BASE+CityPath.GET_CITY_BY_PROVINCEID %>',
		data:
		{
			"provinceId" 	: provValv
		},
		success:function(data){
			var cityList = data.cityList;
			for ( var city in cityList) {

				var aa ="<option value='"+ cityList[city].cityId  +"'";
				if ( cityList[city].cityId  == bankCityNameQ) 
				  {
					 aa+="selected";
				  }
				aa += ">" + cityList[city].cityName + "</option>";
				
				$("#bankCityName").append(aa);
			}
		}
	})
// 开户银行分行回显
	var provVala = $("#bankProvinceName").val().trim();
	var citya =$("#bankCityNameQ").val().trim();// 获取开户城市
	var compAcctBanka = $("#compAcctBank").val();
	var channelCode ="";
	//获取回显比对值
	var branchBankQ = $("#branchBankQ").val();
	$.post(window.Constants.ContextPath +"/common/info/bankCnapsInfo",
	{
		"provinceId": provVala,
		"cityCode":   citya,
		"bankCode":   compAcctBanka,
		"channelCode":channelCode
	},
	function(data){
		if(data.result=="SUCCESS"){
			var branchBankList = data.branchBankList;
   			for ( var branchBank in branchBankList) {
   				var aa ="<option value='"+ branchBankList[branchBank].branchBankCode  +"'";
				if ( branchBankList[branchBank].branchBankCode == branchBankQ) 
				  {
					 aa+="selected";
				  }
				aa += ">" + branchBankList[branchBank].bankName + "</option>";
   				$("#branchBank").append(aa); 		
   			}
		}
		
	},'json'
	);	
	
	
	
</script>
</html>