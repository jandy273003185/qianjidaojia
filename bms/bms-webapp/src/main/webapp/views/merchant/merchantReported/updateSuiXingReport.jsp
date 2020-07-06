<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
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
	<title>随行付更新进件</title>
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
					<div class="col-xs-12">
					<input type="hidden" id="reportStatus" name="reportStatus" value="${reportStatus}"/>
					<input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="patchNo" name="patchNo" value="${patchNo}"/>
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
								<td class="td-left" width="18%">商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly   value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" readonly  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							
	                        <tr>
							    <td class="td-left">是否有证商户：</td>
								<td class="td-right"> 
								    <select  name="suiXingMerchantType" id="suiXingMerchantType" onchange = "getUpdateType();"class="width-90">
										<option value="01">有证商户</option>
										<option value="02">无证商户</option>
									</select>
									<label class="label-tips" id="suiXingMerchantLab"></label>
								</td>
							</tr>
								
							<tr id="businessPhotoType" style = "display:">
								
								<td class="td-left">营业执照照片：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle="modal" class="tooltip-success businessPhotoClick" data-target="#previewImageModal">
										<label id="businessPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="businessPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="businessPhotoImageVal02">  
										<input type="file" name="businessPhoto" id="businessPhoto" onChange="showBusinessPhotoImage(this)">
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
									</div>
								</td>
								
							</tr>
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal">
										<label id="doorPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="doorPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="doorPhotoImageVal02">  
										<input type="file" name="doorPhoto" id="doorPhoto" onChange="showDoorPhotoImage(this)">
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
								<td class="td-left">店内照：</td>
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
								<td class="td-left">店内前台照：</td>
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
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证正面：</td>
								<td class="td-right" colspan="3">
									<!-- <a data-toggle="modal" class="tooltip-success legalCertAttribute1Click" data-target="#previewImageModal">
									<label id="legalCertAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="legalCertAttribute1ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
									</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="legalCertAttribute1Val02">  
										<input type="file" name="legalCertAttribute1" id="legalCertAttribute1" onChange="showlegalCertAttribute1Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
								<td class="td-left">法人身份证背面：</td>
								<td class="td-right" colspan="3"> 
									<!-- <a data-toggle="modal" class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal">
										<label id="legalCertAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="legalCertAttribute2ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="legalCertAttribute2Val02">  
										<input type="file" name="legalCertAttribute2" id="legalCertAttribute2" onChange="showlegalCertAttribute2Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
								<td class="td-left">结算类型：</td>
								<td class="td-right">
									<select name="actType" id="actType" style="width-90" onchange = "getUpdateType1();" >
										<option value="">--请选择--</option>
										<option value="00">--对公--</option>
										<option value="01">--对私--</option>
									</select> 
								</td>
								<td class="td-left">账户人身份证号码：</td>
							  	<td class="td-right"> 
									<input type="text" id="certifyNo" name="certifyNo" placeholder="请输入账户人身份证号码"  value="" style="width:90%">
								</td>
							</tr>
							<tr id="letterOfAuthType" style = "display:">
								<td class="td-left">非法人结算授权函：</td>
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
								<td class="td-left">开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<!-- <a data-toggle="modal" class="tooltip-success certAttribute0Click" data-target="#previewImageModal">
										<label id="certAttribute0Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="certAttribute0ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="certAttribute0Val02">  
										<input type="file" name="certAttribute0" id="certAttribute0" onChange="showCertAttribute0Image(this)">
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
				                <td class="td-left">银行卡正面照：</td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoImageVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showBankCardPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
				                <td class="td-left"> 结算人身份证正面 </td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success settleCertAttribute1Click"  data-target="#previewImageModal"  >
										<label id="settleCertAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleCertAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleCertAttribute1Val02"  />   
										<input type="file" name="settleCertAttribute1" id="settleCertAttribute1" onchange="showSettleCertAttribute1Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
				                <td class="td-left"> 结算人身份证反面 </td>
		                    	<td class="td-right" colspan="3">
									<!-- <a data-toggle='modal' class="tooltip-success settleCertAttribute2Click"  data-target="#previewImageModal"  >
										<label id="settleCertAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleCertAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleCertAttribute2Val02"  />  
										<input type="file" name="settleCertAttribute2" id="settleCertAttribute2" onchange="showSettleCertAttribute2Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
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
		
		//结算人身份证正面
		function showSettleCertAttribute1Image(file){
			commonFileUpload(file, 'settleCertAttribute1Path', 'settleCertAttribute1Div');
		}
		
		//结算人身份证反面
		function showSettleCertAttribute2Image(file){
			commonFileUpload(file, 'settleCertAttribute2Path', 'settleCertAttribute2Div');
		} 
		
		//上传非法人结算授权函
		function showLetterOfAuthImage(file){
			commonFileUpload(file, 'letterOfAuthPath', 'letterOfAuthDiv');
		}
		
		
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

      	/** 店内点击预览 **/shopCheckStandPath
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

      	/** 非法人结算授权函 **/
      	$('.letterOfAuthClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("letterOfAuth");
      		return previewImage(divObj,imageObj,obj); 
      	});
	   	
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
   			//certifyNo（账户身份证号）
   			var certifyNo = $("#certifyNo").val();
   			//（资质类型）
   			var suiXingMerchantType = $("#suiXingMerchantType").val();
   			//actType（结算类型）
   			var actType = $("#actType").val();
			var patchNo = $("#patchNo").val();
   			var reportStatus = $("#reportStatus");
   			var taskCode = $("#taskCode").val();
   			if("" == suiXingMerchantType){
   	    		$("#suiXingMerchantLab").text("资质类型不能为空");
   	    		return false;
   	    	}else{
   	    		$("#suiXingMerchantLab").text('');
   	    	}
   	    	
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
   			//上传照片
			var imgDoor = [];
			var imgSrc = [];
			var merchantData = new FormData(document.getElementById('merchantForm'));	
			<%-- var mer_upload = document.getElementById('merchantForm');
			mer_upload.action=window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?merchantCode='+merchantCode;
			mer_upload.submit(); --%>
			/* $("#door_temp input[type='hidden']").each(function(){
				if($(this).attr('id').indexOf('Src')>=0){
					imgSrc.push($(this).attr('id')+"="+$(this).val());
				}else{
					if($(this).val()==""){
						imgDoor.push($(this).attr('id')+"="+"无");
					}else{
						imgDoor.push($(this).attr('id')+"="+$(this).val());
					}
				}
			});  */
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
						"settleCertAttribute2Path" : settleCertAttribute2Path		 //结算人身份证反面
		            },
		            dataType : "json",
		            success : function(data) {
		                if(data.result=='SUCCESS'){
		                	
		                }
		            }
			 })
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?patchNo='+patchNo+'&status='+status,
				data :merchantData,
                dataType : "json",
                cache: false,
                processData: false,
                contentType: false,
				success : function(data){
				if(data.result=="SUCCESS"){
					if("" != data.message){
						taskCode = $("#taskCode").val(data.message);
						alert("更新进件成功");
	   					//照片
	   		 			/* $("input[type=file]").each(
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
	   		 			)  */
					}else{
						alert("上传图片返回码异常");
					}
					
				}else{
					if("" == data.message || null == data.message){
						alert("失败");
					}else{
						alert(data.message);
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
</body>
</html>