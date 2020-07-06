<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMchGradeType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCompanyCodeType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayIdCardLegalType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayProfessionType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayCycleDays"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayMerSumType"%>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType.SumpayBankNmType"%>

<%@ include file="/include/template.jsp"%>
<%-- <script src='<c:url value="/static/js/jquery.blockUI.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/js/jquery.min.js"/>'></script> --%>
<script src='<c:url value="/static/js/up.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>

<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<title>商盟支付进件</title>
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
					<input type="hidden" id="channlCode" name="channlCode" value="SUM_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="certAttribute0temp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="opentemp"/>
					<input type="hidden" id="bankCardPhototemp"/>
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    	<form  id="merchantForm" method="post" enctype="multipart/form-data" >
					            <!--基本资料-->
					            <div class="aui-content-up-form">
					                <h2>基本资料</h2>
					            </div>
					            <div class="aui-form-group clear" id="merchantCodeType1" style = "display:">
					                <label class="aui-label-control">
					                   	商户编号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="merchantCode" id="merchantCode" 
					                    	   required value="${custInfo.merchantCode }" readonly="readonly">
					                   	<label class="label-tips" id="merchantCodeLab"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="custNameType" style = "display:">
					                <label class="aui-label-control">
					                   	 商户名(简称)<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="custName" id="custName" 
					                    	   onBlur="checkna()" required value="${custInfo.custName }" readonly="readonly">
					                    <label id="custNameLab" class="label-tips"></label>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="outMerchantcodeType" style = "display:">
					                <label class="aui-label-control">
					                   	 外部商户号<em>*</em>
					                </label>
					                <div class="aui-form-input">
					                    <input type="text" class="aui-form-control-two" style="width: 250px;" name="outMerchantCode" id="outMerchantCode" 
					                    	   onBlur="checkna()" required value="${detail.outMerchantCode }" readonly="readonly">
					                    <label id="outMerchantCodeLab" class="label-tips"></label>
					                </div>
					            </div>
					             <!--纸质信息-->
					            
					            <div class="aui-content-up-form" id="infoType" style = "display:">
					                <h2>纸质信息</h2>
					            </div>
					            <div class="aui-form-group clear" id="doorPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 门头照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal"  >
													<label id="doorPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="doorPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="doorPhotoPath" name="doorPhotoPath"  value="${picPath.doorPath }"/>
													<input  type="hidden" id="doorPhotoImageVal02" name="doorPhoto" />  
													<input type="file" name="doorPhoto" id="doorPhoto" onchange="goDoorPhoto(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="openType" style = "display:">
					                <label class="aui-label-control">
					                   	 开户许可证 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success openClick"  data-target="#previewImageModal"  >
													<label id="openDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="openImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="openPath" name="openPath" value="${picPath.openPath }"/>
													<input type="hidden" id="openImageVal02"  name="open" />  
													<input type="file" name="open" id="open" onchange="goOpen(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="idCardType" style = "display:">
					                <label class="aui-label-control">
					                   	 身份证正面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal"  >
													<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="certAttribute1FilePath" name="certAttribute1FilePath" value="${picPath.identityImagePath0 }"/>
													<input type="hidden" id="certAttribute1ImageVal02"  name="certAttribute1"/>  
													<input type="file" name="certAttribute1" id="certAttribute1" onchange="goCertAttribute1(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="idCardBackType" style = "display:">
					                <label class="aui-label-control">
					                   	 身份证反面 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
													<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="certAttribute2FilePath" name="certAttribute2FilePath" value="${picPath.identityImagePath1 }"/>
													<input type="hidden" id="certAttribute2ImageVal02" name="certAttribute2" />  
													<input type="file" name="certAttribute2" id="certAttribute2" onchange="goCertAttribute2(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="businessPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 营业执照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal"  >
													<label id="businessPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="businessPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" value="${picPath.licensePath }"/>
													<input type="hidden" id="businessPhotoImageVal02"  name="businessPhoto" />  
													<input type="file" name="businessPhoto" id="businessPhoto" onchange="goBusinessPhoto(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					      		<div class="aui-form-group clear" id="shopInteriorType" style = "display:">
					                <label class="aui-label-control">
					                   	 店内照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success shopInteriorClick"  data-target="#previewImageModal"  >
													<label id="shopInteriorDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="shopInteriorImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="shopInteriorPath" name="shopInteriorPath" value="${picPath.shopInteriorPath }"/>
													<input type="hidden" id="shopInteriorImageVal02" name="shopInterior"  />  
													<input type="file" name="shopInterior" id="shopInterior" onchange="goShopInterior(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					            <div class="aui-form-group clear" id="bankCardPhotoType" style = "display:">
					                <label class="aui-label-control">
					                   	 银行卡正面照 <em>*</em>
					                    <span>小于5M</span>
					                </label>
					                <div class="aui-form-input">
					                    <div class="aui-content-img-box aui-content-full">
					                    	<div class="aui-photo aui-up-img clear">
					                    	<td class="td-right" colspan="3">
												<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
													<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
													        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
													</label>
												</a>
												<div class="updateImageDiv" style="float:left; margin-top:75" >
													<input  type="hidden" id="bankCardPhotoPath" name="bankCardPhotoPath" value="${picPath.bankCardPath }"/>
													<input type="hidden" id="bankCardPhotoImageVal02" name="bankCardPhoto"  />  
													<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="goBankCardPhoto(this)"/>
													<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
												</div>
											</td>
											</div>
					                    </div>
					                </div>
					            </div>
					        </form>
					    </div>
					    <div class="aui-btn-default">
					    	<%-- <gyzbadmin:function url="<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSMERCHANTREPORT%>"> --%>
								<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitData' data-target="#submitModal">
									保存并提交审核
								</button>
							<%-- </gyzbadmin:function> --%>
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

	function goDoorPhoto(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#doorPhoto').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#doorPhotoPath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });

	    var prevDiv = document.getElementById('doorPhotoDiv');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	} 

	function goOpen(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#open').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#openPath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });

	    var prevDiv = document.getElementById('openDiv');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	}

	function goCertAttribute1(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#certAttribute1').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#certAttribute1FilePath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });

	    var prevDiv = document.getElementById('certAttribute1Div');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	}
	function goCertAttribute2(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#certAttribute2').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#certAttribute2FilePath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });
	    
	    var prevDiv = document.getElementById('certAttribute2Div');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	}

	function goBusinessPhoto(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#businessPhoto').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#businessPhotoPath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });
	    
	    var prevDiv = document.getElementById('businessPhotoDiv');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	}

	function goShopInterior(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#shopInterior').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#shopInteriorPath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });
	    
	    var prevDiv = document.getElementById('shopInteriorDiv');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
	}

	function goBankCardPhoto(file){
	    var formdata=new FormData();
	    formdata.append("file",$('#bankCardPhoto').get(0).files[0]);
	    $.ajax({
	        url:'/common/files/upload',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	             $('#bankCardPhotoPath').val(info.path);
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });

	    var prevDiv = document.getElementById('bankCardPhotoDiv');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
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

      	/** 照片点击预览 **/
      	$('.shopInteriorClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("shopInteriorClick");
      		return previewImage(divObj,imageObj,obj); 
      	});


        //图片预览
        function showShopInteriorImage(obj){  
    		 var divObj = document.getElementById("shopInteriorDiv");  
    		 var imageObj = document.getElementById("shopInteriorImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}
        
        function showDoorPhotoImage(obj){  
    		 var divObj = document.getElementById("doorPhotoDiv");  
    		 var imageObj = document.getElementById("doorPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}
    	
    	function showBusinessPhotoImage(obj){  
    		 var divObj = document.getElementById("businessPhotoDiv");  
    		 var imageObj = document.getElementById("businessPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}

    	function showOpenImage(obj){  
   			 var divObj = document.getElementById("openDiv");  
   			 var imageObj = document.getElementById("openImageDiv");  
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

    	function showBankCardPhotoImage(obj){  
      		 var divObj = document.getElementById("bankCardPhotoDiv");  
      		 var imageObj = document.getElementById("bankCardPhotoImageDiv");  
      		 return previewImage(divObj,imageObj,obj);  
      		}
      	/* 校验渠道 */
    $(function(){

		//判定是新进件还是更新进件
		var status = $("#status").val().trim();
		var custId = $("#custId").val().trim();
		var authId = $("#authId").val().trim();
		
    	$("#businessPhotoImageDiv").show();
    	$("#certAttribute1ImageDiv").show();
    	$("#certAttribute2ImageDiv").show();
    	$("#doorPhotoImageDiv").show();
    	$("#openImageDiv").show();
    	$("#shopInteriorImageDiv").show();
    	$("#bankCardPhotoImageDiv").show();
    	$("#shopInteriorImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=18&authId="+authId);
    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
    	$("#openImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);    
    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
    	$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=05&authId="+authId);
    	
   		$("#submitData").click(function(){
   	   		
   			var channelNo = $("#channlCode").val();
   			var merchantCode = $("#merchantCode").val();
   			var custName = $("#custName").val();
   			var outMerchantCode = $("#outMerchantCode").val();
   			var doorPhotoPath = $("#doorPhotoPath").val(); 
   			if("" == doorPhotoPath){
   	    		alert("门店照不能为空");
   	    		return false;
   	    	}
   			var openPath = $("#openPath").val(); 
   			if("" == openPath){
   	    		alert("开户许可证不能为空");
   	    		return false;
   	    	}
   			var certAttribute1FilePath = $("#certAttribute1FilePath").val();
   			if("" == certAttribute1FilePath){
   	    		alert("身份证正面照不能为空");
   	    		return false;
   	    	}
			var certAttribute2FilePath = $("#certAttribute2FilePath").val();
			if("" == certAttribute2FilePath){
   	    		alert("身份证反面照不能为空");
   	    		return false;
   	    	}
			var businessPhotoPath = $("#businessPhotoPath").val();
			if("" == businessPhotoPath){
   	    		alert("营业执照不能为空");
   	    		return false;
   	    	}
			var shopInteriorPath = $("#shopInteriorPath").val();
			if("" == shopInteriorPath){
   	    		alert("店内照不能为空");
   	    		return false;
   	    	}
   			var bankCardPhotoPath = $("#bankCardPhotoPath").val();
   			if("" == bankCardPhotoPath){
   	    		alert("银行卡照不能为空");
   	    		return false;
   	    	}
   			$.blockUI();
   			//商盟支付渠道
   			if("SUM_PAY" == channelNo){
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
   					            var imgObj = $('#'+_this.attr('id')+'ImageVal02');
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
   		 		);

   				/* var merchantData = new FormData(document.getElementById('testForm')); */
   				var merchantData = new FormData(document.getElementById('merchantForm')); 
   					
				//未报备
  				if("unReported" == status){
			 	   	
  	   				$.ajax({
  	   					type : "POST",
  	   					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>?merchantCode='+merchantCode,
  	   					data :merchantData,
  	   					dataType : "json",
  	                 	cache: false,
	  	                processData: false,
	  	                contentType: false,
  	   					success : function(data) {
  	   					$.unblockUI();
  	   						if(data.result=='SUCCESS')							
  	   							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUMPAYPHOTOINFOMERCHANTREPORT %>",
  	   							{
  	   							"channelNo" : channelNo,
  								"merchantCode" : merchantCode,
  								"custName" : custName,
  		   						"outMerchantCode": outMerchantCode,
  		   						"doorPhotoPath" : doorPhotoPath,
  		   						"openPath" : openPath,
  	   							"identityImagePath0" : certAttribute1FilePath,
  	   							"identityImagePath1" : certAttribute2FilePath,
  	   							"licensePath" : businessPhotoPath,
  	   							"shopInteriorPath" : shopInteriorPath,
  	   							"bankCardPath" : bankCardPhotoPath
  	   							},
  	   							function(data){
  	   								if(data.result=="SUCCESS"){
  	  	   								alert("提交报备成功");
  	   									/* $.gyzbadmin.alertSuccess("提交报备成功！",function(){
  	   										//$("#updateAccountModal").modal("hide");
  	   									},function(){
  	   										this.location.reload();
  	   									}); */
  	   								}else{
  	   									/* $.gyzbadmin.alertFailure(data.message);
  	   									window.location.reload(); */
  	   									/* $.gyzbadmin.alertFailure('提交报备失败:' + data.message,function(){
  	   										//$("#updateAccountModal").modal("hide");
  	   									},function(){
  	   										window.location.reload();
  	   									}); */
  	   									if(null == data.message || "" ==data.message){
											alert("进件失败");
  	  	   								}else{
  	  	   									alert(data.message);
  	  	  	   							}
  	   								}
  	   							},'json'
  	   						  );	
  	   					}
  	   				});
  	  			}
   			
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
   		 		)   */
   	 		}
        });
   	});  
</script>
</body>
</html>