<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户录入审核</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
    $(function() {
        /**企业类型 **/
        /* if($("#custType").val() =='0' ||$("#custType").val() =='2' ){
            //个人
        	$("#bankCardPhoto_").attr("style","display:");
            $("#openAccount_").attr("style","display:none");
        }
        if($("#custType").val() =='1'){
            //企业
            $("#bankCardPhoto_").attr("style","display:none");
            $("#openAccount_").attr("style","display:");
        } */
		if($("#compMainAcctType").val() =='01'){
			 $("#bankCardPhoto_").attr("style","display:none");
			 $("#openAccount_").attr("style","display:");
		}else{
			$("#bankCardPhoto_").attr("style","display:");
            $("#openAccount_").attr("style","display:none");
		}
        var custId = $("#custId").val().trim();
        var authId = $("#authId").val().trim();

        $("#businessPhotoImageDiv").show();
        $("#bankCardPhotoImageDiv").show();
        $("#certAttribute1ImageDiv").show();
        $("#certAttribute2ImageDiv").show();
        $("#openAccountImageDiv").show();
        $("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
        $("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=07&authId="+authId);
        $("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
        $("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
        $("#openAccountImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);


    });

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


    //图片预览
    function showBusinessPhotoImage(obj){
        var divObj = document.getElementById("businessPhotoDiv");
        var imageObj = document.getElementById("businessPhotoImageDiv");
        return previewImage(divObj,imageObj,obj);
    }

    function showopenAccountImage(obj){
        var divObj = document.getElementById("openAccountDiv");
        var imageObj = document.getElementById("openAccountImageDiv");
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


    function pass(){

        var isPass="1";
        var merchantCode = $("#merchantCode").val();
        var message = "";
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath +'<%=MerchantEnterPath.BASE+ MerchantEnterPath.AUDIT %>',
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "isPass" : isPass,
                    "message" : message
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    $.gyzbadmin.alertSuccess("审核完成！",function(){
                    },function(){
                        window.close();
                        window.opener.location.href=window.opener.location.href;
                    });
                }else{
                    $.gyzbadmin.alertFailure("审核失败！"+data.message);
                }
            }
        })
    }

    function notPass(){
        var isPass="0";
        var message = $("#auditMessage").val();
        var merchantCode = $("#merchantCode").val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath +'<%=MerchantEnterPath.BASE+ MerchantEnterPath.AUDIT %>',
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "isPass" : isPass,
                    "message" : message
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    $.gyzbadmin.alertSuccess("审核完成！",function(){
                    },function(){
                        window.close();
                        window.opener.location.href=window.opener.location.href;
                    });
                }else{
                    $.gyzbadmin.alertFailure("审核失败！"+data.message);
                }
            }
        })
    }

    function exit() {
        if (confirm("您确定要关闭吗？")) {
            window.opener=null;

            window.open("","_self");

            window.close();
        }
    };
</script>
<body>
<%@ include file="/include/top.jsp"%>

<div class="main-container" id="main-container">
	<script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner">
		<!-- 菜单 -->

		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>

			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<input type="hidden" id="businessPhototemp">
						<input type="hidden" id="certAttribute1temp">
						<input type="hidden" id="certAttribute2temp">
						<input type="hidden" id="openAccounttemp" />
						<input type="hidden" id="bankCardPhototemp" />
						<input type="hidden" name="custId" id="custId" value="${merchantVo.custId}">
						<input type="hidden" name="merchantCode" id="merchantCode" value="${merchantVo.merchantCode}">
						<input type="hidden" name="authId" id="authId" value="${merchantVo.authId}">
						<input type="hidden" name="custType" id="custType" value="${merchantVo.custType}">
						<input type="hidden" name="compMainAcctType" id="compMainAcctType" value="${merchantVo.compMainAcctType}">
						<table id="merchant_table" class="list-table">
							<tbody>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">商户账号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.email }</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left">商户类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">企业</td>
							</tr>
							<tr>
								<td class="td-left"  width="18%">商户名称：</td>
								<td class="td-right" width="32%" style="color:#666;padding:10px 8px">${merchantVo.custName }</td>
								<td class="td-left"  width="18%">商户简称：</td>
								<td class="td-right" width="32%" style="color:#666;padding:10px 8px">${merchantVo.shortName }</td>
							</tr>
							<tr>
								<td class="td-left">商户邮箱：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.merchantEmail }</td>
								<td class="td-left">客服号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactPhone }</td>
							</tr>



							<tr>
								<td class="td-left">商户地址：</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="province" onchange="getCityList();">
											<%--<c:if test="${not empty provincelist_ }">
												<option value="">${merchantVo.provinceName }</option>
												<c:forEach items="${provincelist_ }" var="prov">
													<option value="${prov.provinceId }">${prov.provinceName }</option>
												</c:forEach>
											</c:if>--%>
											<option value="" id="proDef"> ${merchantVo.provinceName }</option>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
										<select class="form-control" id="city1" onchange="getAreaList();">
											<option value="" id="cityDef">${merchantVo.cityName }</option>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="country">
											<option value="" id="areaDef"> ${merchantVo.areaName }</option>
										</select>
									</div>
									<div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
										<input type="text" id="custAdd" name="custAdd"  placeholder="详细地址" value ="${merchantVo.custAdd }" style="width:100%">
										<label class="label-tips" id="custAddLab"></label>
									</div>
								</td>
							</tr>

							<%--<tr>
								<td class="td-left">商户地址：</td>
								<td class="td-right"  style="color:#666;padding:10px 8px">${merchantVo.custAdd }</td>
							</tr>--%>
							<tr>
								<td class="td-left">营业执照编号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.businessLicense }</td>
								<td class="td-left">营业执照有限期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									${merchantVo.businessTermStart } -
									<c:choose>
										<c:when test="${merchantVo.businessTermEnd =='forever'}">
											长期
										</c:when>
										<c:when test="${merchantVo.businessTermEnd =='2099-12-31'}">
											长期
										</c:when>
										<c:when test="${merchantVo.businessTermEnd =='长期'}">
											长期
										</c:when>
										<c:otherwise>
											${merchantVo.businessTermEnd }
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td-left">营业执照扫描件：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img  id="businessPhotoImageDiv" onclick="bigImg(this);"  style="width:100%;height:100%;display:none"  />
										</label>
									</a> -->
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bussinessPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<%--<div class="updateImageDiv" style="float:left; margin-top:75 " >
                                        <input type="hidden" id="businessPhotoImageVal02"  />
                                        <input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
                                         <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                    </div>--%>

								</td>
							</tr>
							<tr>
								<td class="td-left">门头照：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.doorPhotoPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">店内照：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">店内前台照：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success shopCheckStandClick" data-target="#previewImageModal" >
										<label id="shopCheckStandDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">所属业务人员：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.custManager }</td>
								<td class="td-left">所属代理商：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.agentName }</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeName }</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeCertNo }</td>
							</tr>
							<tr>
							<td class="td-left">身份证有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.idTermStart } -
									<c:choose>
										<c:when test="${merchantVo.idTermEnd =='forever'}">
											长期
										</c:when>
										<c:when test="${merchantVo.idTermEnd =='2099-12-31'}">
											长期
										</c:when>
										<c:when test="${merchantVo.idTermEnd =='长期'}">
											长期
										</c:when>
										<c:otherwise>
											${merchantVo.idTermEnd }
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td-left" >法人身份证正面：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
										<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a> -->
									<a data-toggle='modal' class="tooltip-success certAttribute1Click" data-target="#previewImageModal" >
										<label id="certAttribute1Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardOPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<%--<div class="updateImageDiv" style="float:left; margin-top:75" >
                                        <input type="hidden" id="certAttribute1Val02"  />
                                        <input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/>
                                        <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                    </div>--%>

								</td>
							</tr>
							<tr>
								<td class="td-left" >法人身份证背面：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
										<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a> -->
									<a data-toggle='modal' class="tooltip-success certAttribute2Click" data-target="#previewImageModal" >
										<label id="certAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardFPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<%--<div class="updateImageDiv" style="float:left; margin-top:75" >
                                        <input type="hidden" id="certAttribute2Val02"  />
                                        <input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/>
                                        <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                    </div>--%>

								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td></tr>
							<tr></tr>
							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactName }</td>
								<td class="td-left">联系人手机号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactMobile }</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
							<tr>
								<td class="td-left">银行卡号</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.compMainAcct }</td>

								<td class="td-left">银行类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankName }</td>
							</tr>
							<tr>
								<td class="td-left">开户行：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.branchBank }</td>
								<td class="td-left">开户人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankAcctName }</td>
							</tr>
							<tr>
								<td class="td-left">开户省份：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankProName }</td>
								<td class="td-left">开户城市：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankCitName }</td>
							</tr>
							<tr>
								<td class="td-left">网点号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.cnaps }</td>
								<td class="td-left">结算类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px" id ="compMainAcctType"><c:choose>
									<c:when test="${merchantVo.compMainAcctType =='01'}">
										对公
									</c:when>
									<c:otherwise>
										对私
									</c:otherwise>
								</c:choose></td>
							</tr>
							<tr id="openAccount_" style="display: none">
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
										<label id="openAccountDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="openAccountImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a> -->
									<a data-toggle='modal' class="tooltip-success openAccountClick" data-target="#previewImageModal" >
										<label id="openAccountDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.openAccountPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<%--<div class="updateImageDiv" style="float:left; margin-top:75" >
                                        <input type="hidden" id="openAccountVal02"  />
                                        <input type="file" name="openAccount" id="openAccount" onchange="showopenAccountImage(this)"/>
                                        <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                    </div>--%>

								</td>
							</tr>
							<tr id="bankCardPhoto_" style="display:">
								<td class="td-left" >银行卡照<span style="color:red"></span></td>
								<td class="td-right" >
									<!-- <a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv" class="uploadImage">
											<img  id="bankCardPhotoImageDiv" style="width:100%;height:100%;display:none"/>
										</label>
									</a> -->
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick" data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bankCardPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<%--<div style="float:left;margin-top:75" >
                                    <input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                    </div>--%>

									<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">操作记录</td>
							</tr>
							<tr></tr>
							<tr>
								<td class="td-left">录入人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createName }</td>
								<td class="td-left">录入时间：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createTime }</td>
							</tr>
							</tbody>
						</table>
						<div style="margin:50px 0 0 0;text-align:center">
							<button type="button"  class="btn btn-primary" onclick="pass()">审核通过</button>
							<button type="button" class="btn btn-primary auditNotPassBtn" data-toggle='modal'  data-target="#auditMessageModel" >审核不通过</button>
							<button type="button"  class="btn btn-default" onclick="exit()">关闭</button>
						</div>
					</div>
				</div>
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog showDiv" >
						<div id="showImageDiv" style="width:100%;height:100%;">
							<img id="showImage" style="width:100%;height:100%;">
						</div>
					</div>
				</div>
				<!-- 审核不通过弹框 -->
				<div class="modal fade" style="z-index:1043;" id="auditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width:30%;z-index:99;">
						<div class="modal-content" >
							<div class="modal-header" style="background-color:0099CC">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">审核不通过</h4>
							</div>
							<div class="modal-body">
								<table 	 >
									<tr>
										<td >请输入审核不通过理由：</td>
									</tr>
									<tr>
										<td>
											<textarea rows="5" cols="40" id="auditMessage" maxlength="50" placeholder="请输入小于50字的审核意见"  ></textarea>
										</td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn" onclick="notPass()">确定</button>
							</div>
						</div><!-- /.modal-content -->
					</div>
				</div><!-- /.modal -->

			</div><!-- /.page-content -->

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


<script type="text/javascript">

</script>
</html>