<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ page import="com.qifenqian.bms.basemanager.city.CityPath" %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户录入更新</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.scss' />" />
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
	<link href="/static/css/bootstrap-select.css" rel="stylesheet">
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.uploadImage{ float:left;
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
		}
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
	    	   	        		 if(_this.attr('id')=="certAttribute1"){                    //身份证
	    	       	  				$("#representativeName").val(json.cardName);
	    	       	  				$("#representativeCertNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="businessPhoto"){                //营业执照
	    	       	  				$("#businessLicense").val(json.businessLicense);
	    	       	  				$("#businessTermStart").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("#businessTermEnd").val("2099-12-31");
	    	       	  				}else{
	    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#custAdd").val(json.legalAddress);
	    	       	  				$("#custName").val(json.companyName);
	    	       	  			}else if(_this.attr('id')=="bankCardPhoto"){                //银行卡
    	       	  					$("#compMainAcct").val(json.creditCardId);
    	       	  					// $("#compAcctBank").val(json.cardNo);
    	       	  				
	    	       	  			}
	    	   				}
	    	   			}
		    	   	});
				}
			});
		}
	);
	$("#agentName").comboSelect();
	$("#custManager").comboSelect();
});

    $(function() {

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

        /**商户类型 **/
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

    });
    
    function getCityList(){

    	var provVal = $("#province").val().trim();
    	$("#cityDef").siblings().remove();
    	$("#areaDef").siblings().remove();
    	if ("" == provVal || provVal.length == 0) {
    		return false;
    	}
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
    					$("#city").append(
    						"<option value='"+ cityList[city].cityId +"'>" + cityList[city].cityName + "</option>");
    				}
    			}else{
    			}
    		}
    	})
    }

    function getAreaList(){

    	var city = $("#city").val().trim();
    	$("#areaDef").siblings().remove();
    	if ("" == city || city.length == 0) {
    		return false;
    	}

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
    					$("#country").append(
    						"<option value='"+ areaList[area].areaId +"'>" + areaList[area].areaName + "</option>");
    				}
    			}else{
    			}
    		}
    	})
    }


    function getBankCityList(){
        var provVal = $("#bankProvinceName").val().trim();

        $("#cityDef").siblings().remove();

        if ("" == provVal || provVal.length == 0) {
            return false;
        }

        $.ajax({
    		type:"POST",
    		dataType:"json",
    		url:window.Constants.ContextPath +'<%=CityPath.BASE+CityPath.GET_CITY_BY_PROVINCEID %>',
    		data:
    		{
    			"provinceId" 	: provVal
    		},
    		success:function(data){
    			var cityList = data.cityList;
    			for ( var city in cityList) {
    				$("#bankCityName").append(
    					"<option value='"+ cityList[city].cityId +"'>" + cityList[city].cityName + "</option>");
    			}
    		}
    	})
    }
	
    function getbranchBank(){
    	var provVal = $("#bankProvinceName").val().trim();
    	var city = $("#bankCityName").val().trim();
    	var compAcctBank = $("#compAcctBank").val();
    	
    	var channelCode ="";
    	$.post(window.Constants.ContextPath +"/common/info/bankCnapsInfo",
    	{
    		"provinceId": provVal,
    		"cityCode":   city,
    		"bankCode":   compAcctBank,
    		"channelCode":channelCode
    	},
    	function(data){
    		if(data.result=="SUCCESS"){
    			var branchBankList = data.branchBankList;
    			$("#branchBank").html("");
       			for ( var branchBank in branchBankList) {
       				$("#branchBank").append(
       						"<option value='"+ branchBankList[branchBank].branchBankCode +"'>"
       								+ branchBankList[branchBank].bankName + "</option>"); 
       			}
    		}
    		else{
    			alert("银行和开户城市不能为空");
    		}
    	},'json'
    	);	
    }
    function updateMerchantBtn(){

        if(!(isNull($("#merchantEmail")[0])) && (!verifyEmailAddress($("#merchantEmail")[0]))){
            $("#merchantEmailLab").text("邮箱格式不对,可使用字母、数字、下划线 ");
            $("#merchantEmail").focus();
            return false;
        }

        /*客服号码校验*/
        if(isNull($("#contactPhone")[0])){
            $("#contactPhoneLab").text("请设置客服号码");
            $("#contactPhone").focus();
            return false;
        }

        if(!isPhoneNo($("#contactPhone")[0])){
            $("#contactPhoneLab").text("客服号码格式不对 ");
            $("#contactPhone").focus();
            return false;
        }
        /*商户地址*/
        if(isNull($("#custAdd")[0])){
            $("#custAddLab").text("请填写地址");
            $("#custAdd").focus();
            return false;
        }

        var businessLicense =$("#businessLicense").val();
        //校验营业执照注册号唯一性
        var custType =$("#custType").val();
        /*个人*/
        if(custType=='0'){
        }
        /*企业*/
        if(custType=='1' || custType=='2'){

            //校验营业执照注册号唯一性
            var businessLicense =$("#businessLicense").val();
            var validateLicense =true ;

            if ($("#businessLicense").val() == $("#businessLicenseNumber").val() ) {
                //相等不去校验
			}<%-- else {
                $.ajax({
                    async:false,
                    dataType:"json",
                    url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATELICENSE%>',
                    data:{businessLicense:businessLicense},
                    success:function(data){
                        if(data.result=="FAIL"){
                            $("#businessLicenseLab").text("该营业执照注册号已经被使用");
                            validateLicense = false;
                        }else{
                            validateLicense = true;
                        }
                    }});
                if(!validateLicense){
                    return false;
                }
			}; --%>


            /*营业执照号*/
            if(isNull($("#businessLicense")[0])){
                $("#businessLicenseLab").text("请填写营业执照注册号");
                $("#businessLicense").focus();
                return false;
            }

            /*营业执照有限期 */
            if(isNull($("#businessTermStart")[0])){
                $("#businessTermLabStart").text("请选择日期");
                $("#businessTermStart").focus();
                return false;
            }

            if(isNull($("#businessTermEnd")[0])){
                $("#businessTermLabEnd").text("请选择日期");
                $("#businessTermEnd").focus();
                return false;
            }

            /*起始日期判断 */
            var startDate = $("#businessTermStart").val();
            var endDate= $("#businessTermEnd").val();
            if("" != startDate && "" != endDate && startDate > endDate)
            {
                $.gyzbadmin.alertFailure("结束日期不能小于开始日期");
                return false;
            }


            // 校验营业时间
            if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){return false;}
            if($("input:radio[name='end']:checked").val()=='sel'){
                if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){return false;}
                businessTermEnd = $("#businessTermEnd").val();
            }
            //营业执照号
            var flag = Register.validateBusinessLicense($("#businessLicense").val().trim(),$("#businessLicenseLab"));

            if(!flag){return false;}
        }

        /*法人姓名*/
        if(isNull($("#representativeName")[0])){
            $("#representativeNameLab").text("请填写法人姓名");
            $("#representativeName").focus();
            return false;
        }
        /*法人身份证*/
        if(isNull($("#representativeCertNo")[0])){
            $("#representativeCertNoLab").text("请填写法人身份证");
            $("#representativeCertNo").focus();
            return false;
        }

        /*联系人姓名*/
        if(isNull($("#contactName")[0])){
            $("#contactNameLab").text("请填写联系人姓名");
            $("#contactName").focus();
            return false;
        }
        /*联系人手机号*/
        if(isNull($("#contactMobile")[0])){
            $("#contactMobileLab").text("请填写联系人手机号");
            $("#contactMobile").focus();
            return false;
        }
        /*银行卡号*/
        if(isNull($("#compMainAcct")[0])){
            $("#compMainAcctLab").text("请填写银行卡号");
            $("#compMainAcct").focus();
            return false;
        }
        var compMainAcct = $("#compMainAcct").val();
        if(!checkBankCardFormat($("#compMainAcct"))){
            $("#compMainAcctLab").text("账号需用银行卡号 ");
            $("#compMainAcct").focus();
            return false;
        }
        /*开户银行*/
        var compAcctBank = $("#compAcctBank").val();
        if ("" == compAcctBank || compAcctBank.length == 0) {
            $("#compMainAcctLab").text("请填写开户银行");
            return false;
        }
        /*开户行*/
        if(isNull($("#branchBank")[0])){
            $("#branchBankLab").text("请填写开户行");
            $("#branchBank").focus();
            return false;
        }
        /*开户人 */
        if(isNull($("#bankAcctName")[0])){
            $("#bankAcctNameLab").text("请填写开户人");
            $("#bankAcctName").focus();
            return false;
        }
        /*结算类型*/
        if(isNull($("#compMainAcctType")[0])){
            $("#compMainAcctTypeLab").text("请填写结算类型");
            $("#compMainAcctType").focus();
            return false;
        }

        // 提交前清空所有错误提示栏
        Register.clearAllErrorMsgLabel();

        var merchantAccount = $("#merchantAccount").val();
        var custType = $("#custType").val();
        var custName = $("#custName").val();
        var shortName = $("#shortName").val();
        var merchantEmail = $("#merchantEmail").val();
        var contactPhone =  $("#contactPhone").val();
        var province =  $("#province").val();
        var city = $("#city").val();
        var country = $("#country").val();
        var custAdd = $("#custAdd").val();
        var businessLicense = $("#businessLicense").val();
        var businessTermStart = $("#businessTermStart").val();
        var businessTermEnd = $("#businessTermEnd").val();
        var custManager = $("#custManager").val();
        var agentName = $("#agentName").val();
        var representativeName = $("#representativeName").val();
        var representativeCertNo = $("#representativeCertNo").val();
        var contactName = $("#contactName").val();
        var contactMobile = $("#contactMobile").val();
        var compMainAcct = $("#compMainAcct").val();
        var compAcctBank = $("#compAcctBank").val();
        var branchBank = $("#branchBank").val();
        var bankAcctName = $("#bankAcctName").val();
        var bankProvinceName = $("#bankProvinceName").val();
        var bankCityName = $("#bankCityName").val();
        var compMainAcctType = $("#compMainAcctType").val();
        var custId = $("#custId").val();
        var idTermStart = $("#idTermStart").val().trim();
    	var idTermEnd = $("#idTermEnd").val().trim();
    	var openAccountPath =  $("#openAccountPath").val();
    	var bankCardPhotoPath = $("#bankCardPhotoPath").val();
    	var certAttribute2Path = $("#certAttribute2Path").val();
    	var certAttribute1Path = $("#certAttribute1Path").val();
    	var businessPhotoPath = $("#businessPhotoPath").val();
    	var shopInteriorPath = $("#shopInteriorPath").val();
    	var doorPhotoPath = $("#doorPhotoPath").val();
    	var shopCheckStandPath = $("#shopCheckStandPath").val();
        $.blockUI();
        $.ajax({
            type : "POST",
            url : window.Constants.ContextPath +'<%="/common/files/getPicPath"%>?custId='+custId,
            data :{
            	"openAccountPath"    : openAccountPath,                //开户许可证
            	"idCardOPath"        : certAttribute1Path,             //身份证正面照
            	"idCardFPath"        : certAttribute2Path,             //身份证背面照
            	"bussinessPath"      : businessPhotoPath,              //商户营业执照
            	"bankCardPath"       : bankCardPhotoPath,              //开户银行照
            	"shopInteriorPath"   : shopInteriorPath,               //店内照
            	"shopCheckStandPath" : shopCheckStandPath,             //店内前台照
            	"doorPhotoPath"      : doorPhotoPath                   //门头照

            },
            dataType : "json",
            success : function(data) {
                if(data.result=='SUCCESS'){
                    $.post(window.Constants.ContextPath +'<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATE%>',{
                        "custId":custId,							 // 回传custId
                        "merchantAccount":merchantAccount, 			 // 商户二维码编号
                        "custType":custType,
                        "custName":custName, 						 // 客户姓名
                        "shortName":shortName,                       // 客户简称
                        "merchantEmail":merchantEmail,
                        "contactPhone":contactPhone,
                        "province":province,
                        "city":city,
                        "country" :country,
                        "custAdd" : custAdd,
                        "businessLicense":businessLicense,           // 营业执照注册号
                        "businessTermStart":businessTermStart,
                        "businessTermEnd" : businessTermEnd,
                        "custManager":custManager,
                        "agentName":agentName,
                        "representativeName":representativeName,
                        "representativeCertNo":representativeCertNo,
                        "contactName":contactName,
                        "contactMobile":contactMobile,
                        "compMainAcct":compMainAcct,
                        "compAcctBank":compAcctBank,
                        "branchBank":branchBank,
                        "bankAcctName" :bankAcctName,
                        "bankProvinceName":bankProvinceName,
                        "bankCityName":bankCityName,
                        "idTermStart"     :      idTermStart,						// 法人身份有效起始期
                        "idTermEnd"       :      idTermEnd,						    // 法人身份有效截止期
                        "compMainAcctType":compMainAcctType
                    },function(data){
                        if(data.result=="SUCCESS"){
                            $.gyzbadmin.alertSuccess("注册申请成功",null,function(){
                            	window.close();
                            	window.opener.location.href=window.opener.location.href;
                            });
                        }else {
                            $.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
                        }
                    },'json')
                }else{
                    $.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);

                }
            }
        });

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

    $('.openAccountClick').click(function(){
        var divObj = document.getElementById("showImageDiv");
        var imageObj = document.getElementById("showImage");
        var obj = document.getElementById("openAccount");
        return previewImage(divObj,imageObj,obj);
    });

    /** 银行卡点击预览 **/
    $('.bankCardPhotoClick').click(function(){
        var divObj = document.getElementById("showImageDiv");
        var imageObj = document.getElementById("showImage");
        var obj = document.getElementById("bankCardPhoto");
        return previewImage(divObj,imageObj,obj);
    });
    
    /** 门头照点击预览 **/
    $('.doorPhotoClick').click(function(){
    	var divObj = document.getElementById("showImageDiv");
    	var imageObj = document.getElementById("showImage");
    	var obj = document.getElementById("doorPhoto");
    	return previewImage(divObj,imageObj,obj);
    });

    /** 店内前台点击预览 **/
    $('.shopCheckStandClick').click(function(){
    	var divObj = document.getElementById("showImageDiv");
    	var imageObj = document.getElementById("showImage");
    	var obj = document.getElementById("shopCheckStand");
    	return previewImage(divObj,imageObj,obj); 
    });
    	
    /** 店内照点击预览 **/
    $('.shopInteriorClick').click(function(){
    	var divObj = document.getElementById("showImageDiv");
    	var imageObj = document.getElementById("showImage");
    	var obj = document.getElementById("shopInterior");
    	return previewImage(divObj,imageObj,obj); 
    });

    function exit() {
        if (confirm("您确定要关闭吗？")) {
            window.opener=null;

            window.open("","_self");

            window.close();
        }
    };
    
    function fun(){
        $("input[name='businessTermEnd']").val("长期");
        $("#businessTermEnd").attr("value","长期");
    }
    
    function idCardTremForever(){
    	$("input[name='idTermEnd']").val("长期");
    	$("#idTermEnd").attr("value","长期");
    }
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
    	commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
    }

  //门头照
    function showDoorPhotoImage(file){
    	commonFileUpload(file, 'doorPhotoPath', 'doorPhotoDiv');
    }

    //店内前台照
    function showShopCheckStandImage(file){
    	commonFileUpload(file, 'shopCheckStandPath', 'shopCheckStandDiv');
    }

    //店内照
    function showShopInteriorImage(file){
    	commonFileUpload(file, 'shopInteriorPath', 'shopInteriorDiv');
    }
    
    //身份证正面
    function showCertAttribute1Image(file){
    	commonFileUpload(file, 'certAttribute1Path', 'certAttribute1Div');
    }

    //身份证反面
    function showCertAttribute2Image(file){
    	commonFileUpload(file, 'certAttribute2Path', 'certAttribute2Div');
    } 

    //上传银行卡照
    function showBankCardPhotoImage(file){
    	commonFileUpload(file, 'bankCardPhotoPath', 'bankCardPhotoDiv');
    }

    //上传开户许可证
    function showOpenAccountImage(file){
    	commonFileUpload(file, 'openAccountPath', 'openAccountDiv');
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
						<input type="hidden" id="businessLicenseNumber" value="${merchantVo.businessLicense }"/>
						<table id="merchant_table" class="list-table">
							<tbody>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">商户账号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									${merchantVo.email }
								</td>

							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left">商户类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0'}">
										<td>小微商户</td>
									</c:when>
									<c:when test="${merchantVo.custType =='1'}">
										<td>企业</td>
									</c:when>
									<c:when test="${merchantVo.custType =='2'}">
										<td>个体户</td>
									</c:when>
								</c:choose>
							</tr>
							<tr>
								<td class="td-left"  width="18%">商户名称：</td>
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">
									<input type="text" id="custName" name="custName" maxlength="100" style="width:90%" value="${merchantVo.custName }" >
								</td>
								<td class="td-left"  width="18%">商户简称：</td>
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">
									<input type="text" id="shortName" name="shortName" style="width:90%" value="${merchantVo.shortName }" >
								</td>
							</tr>
							<tr>
								<td class="td-left">商户邮箱：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="merchantEmail" name="merchantEmail" placeholder="请输入商户邮箱" style="width:90%" value="${merchantVo.merchantEmail }" >
								</td>
								<td class="td-left">客服号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="contactPhone" name="contactPhone" placeholder="请输入客服号码" style="width:90%" value ="${merchantVo.contactPhone }" >
								</td>
							</tr>
							<tr>
								<td class="td-left">商户地址：</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="province" onchange="getCityList();">
											<c:if test="${not empty provincelist_ }">
												<option value="${merchantVo.province }">${merchantVo.provinceName }</option>
												<c:forEach items="${provincelist_ }" var="prov">
													<option value="${prov.provinceId }">${prov.provinceName }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
										<select class="form-control" id="city" onchange="getAreaList();">
											<option value="${merchantVo.city }" id="cityDef">${merchantVo.cityName }</option>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="country">
											<option value="${merchantVo.country }" id="areaDef">${merchantVo.areaName }</option>
										</select>
									</div>
									<div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
										<input type="text" id="custAdd" name="custAdd"  placeholder="详细地址" style="width:100%" value ="${merchantVo.custAdd }"  >
										<label class="label-tips" id="custAddLab"></label>
									</div>
								</td>
							</tr>
							<tr  id="businessCodeId_"  style="display: " class="tab-pane active">
								<td class="td-left" id="businessCodeId">营业执照编号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="businessLicense" name="businessLicense" style="width:90%" placeholder="请输入营业执照" value="${merchantVo.businessLicense }" >
									<label class="label-tips" id="businessLicenseLab"></label>
								</td>
								<td class="td-left" id="businessTimeId">营业执照有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">

									<input type="text" id="businessTermStart" name="businessTermStart" value = "${merchantVo.businessTermStart }" onfocus="WdatePicker({skin:'whyGreen'})" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="businessTermLabStart"></label>
									-
									<input type="text" id="businessTermEnd" name="businessTermEnd" value =
									<c:choose>
									<c:when test="${merchantVo.businessTermEnd =='forever'}">
										"长期"
									</c:when>
									<c:when test="${merchantVo.businessTermEnd =='2099-12-31'}">
										"长期"
									</c:when>
									<c:when test="${merchantVo.businessTermEnd =='长期'}">
										"长期"
									</c:when>
									<c:otherwise>
										"${merchantVo.businessTermEnd }"
									</c:otherwise>
									</c:choose>
										   onfocus="WdatePicker({skin:'whyGreen'})" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="businessTermLabEnd"></label>
									<input type="button" onclick="fun()" value="长期" />
								</td>
							</tr>
							<tr id="businessPhotoId_"  style="display: ">
								<td class="td-left" id="businessPhotoId">营业执照扫描件：</td>
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
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" />
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<!-- <div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->

								</td>
							</tr>
							<tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal">
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.doorPhotoPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="doorPhotoPath" name="doorPhotoPath" />
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
											<img src="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
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
											<img src="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
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
								<td class="td-left">所属业务人员：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<%-- <sevenpay:selectSysUserTag name="custManager" id="custManager" defaultValue="${merchantVo.custManager}"/> --%>
									<select id="custManager" name="custManager">
										<option value="${merchantVo.custManager }">${merchantVo.custManager }</option>
										<c:forEach items="${userlist }" var="bean">
											<option value="${bean.userName }">${bean.userName }</option>
										</c:forEach>
									</select>
								</td>
								<td class="td-left">所属代理商：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select id="agentName" name="agentName">
										<option value="${merchantVo.agentName }">${merchantVo.agentName }</option>
										<c:forEach items="${agentList }" var="bean">
											<option value="${bean.custName }">${bean.custName }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td></tr>
							<tr>
							</tr><tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名" value="${merchantVo.representativeName }" maxlength="50" style="width:90%">
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码" value="${merchantVo.representativeCertNo }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left" id="idTermId">法人身份证有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">

									<input type="text" id="idTermStart" name="idTermStart" value = "${merchantVo.idTermStart }" onfocus="WdatePicker({skin:'whyGreen'})" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="idTermLabStart"></label>
									-
									<input type="text" id="idTermEnd" name="idTermEnd" value =
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
										   onfocus="WdatePicker({skin:'whyGreen'})" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="idTermLabEnd"></label>
									<input type="button" onclick="idCardTremForever()" value="长期" />
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片正面：</td>
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
									<div style="float:left;margin-top:75" >
								    	<input  type="hidden" id="certAttribute1Path" name="certAttribute1Path" />
										<input type="file" name="certAttribute1" id="certAttribute1" onChange="showCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<!-- <div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute1Val02"  />
										<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片背面：</td>
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="certAttribute2Path" name="certAttribute2Path" />
										<input type="file" name="certAttribute2" id="certAttribute2" onChange="showCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<!-- <div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute2Val02"  />
										<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->
									
								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td></tr>
							<tr>
							</tr>

							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名" maxlength="50" style="width:90%" value=${merchantVo.contactName } >
								</td>
								<td class="td-left">联系人手机号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" name="contactMobile" id="contactMobile" placeholder="请输入联系人手机号码"  style="width:90%" value=${merchantVo.contactMobile }>
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
							<tr>
								<td class="td-left">结算账号</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="compMainAcct" name="compMainAcct" maxlength="100" placeholder="请输入结算账号" style="width:90%" value=${merchantVo.compMainAcct } >
								</td>
								<td class="td-left">开户人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="bankAcctName" name="bankAcctName" placeholder="请输入开户人" value="${merchantVo.bankAcctName }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">开户省份：</td>
								<td class="td-right" style="color:#666;padding:10px 8px;">
									<select class="width-90" id="bankProvinceName" onchange="getBankCityList();">
										<c:if test="${not empty provincelist }">
											<option value=${merchantVo.bankProvinceName }>${merchantVo.bankProName }</option>
											<c:forEach items="${provincelist }" var="prov">
												<option value="${prov.provinceId}">${prov.provinceName}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
								<td class="td-left">开户城市：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="bankCityName">
										<option value=${merchantVo.bankCityName } id="cityDef">${merchantVo.bankCitName }</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select  name="compAcctBank" id="compAcctBank"  class="selectpicker show-tick form-control" data-width="91%" data-live-search="true">
										<option value="">--请选择--</option>
										<c:if test="${not empty banklist }">
											<c:forEach items="${banklist }" var="bank">
												<option id="${bank.bankCode}" value="${bank.bankCode}">${bank.bankName}</option>
											</c:forEach>
										</c:if>
									</select>
									<%-- <select class="width-90" id="compAcctBank" name="compAcctBank" onchange="getbranchBank();">
										<c:if test="${not empty banklist }">
											<option value=${merchantVo.compAcctBank }>${merchantVo.bankName }</option>
											<c:forEach items="${banklist }" var="bank">
												<option value="${bank.bankCode }">${bank.bankName }</option>
											</c:forEach>
										</c:if>
									</select> --%>
									<label class="label-tips" id="compMainAcctLab"></label>
								</td>
								<td class="td-left">开户支行：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select name="branchBank" id="branchBank" class="width-90" >
	                                    <option value=${merchantVo.branchBank }>${merchantVo.branchBank }</option>
	                                </select>
	                               	<label id="branchBankLab" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">结算类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="compMainAcctType">
										<option value="01">对公</option>
										<option value="02">对私</option>
									</select>
								</td>
							</tr>
							<tr id="openAccount_" style="display: none">
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success openAccountClick" data-target="#previewImageModal" >
										<label id="openAccountDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.openAccountPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<!-- <a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
										<label id="openAccountDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="openAccountImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a> -->
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="openAccountPath" name="openAccountPath" />
										<input type="file" name="openAccount" id="openAccount" onChange="showOpenAccountImage(this)"/> 
										<p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<!-- <div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="openAccountVal02"  />
										<input type="file" name="openAccount" id="openAccount" onchange="showopenAccountImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->

								</td>
							</tr>
							<tr id="bankCardPhoto_">
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
									<div style="float:left;margin-top:75" >
										<input  type="hidden" id="bankCardPhotoPath" name="bankCardPhotoPath" />
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<!-- <div style="float:left;margin-top:75" >
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> -->

									<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							</tbody></table>
						<div style="margin:50px 0 0 0;text-align:center">
							<button type="button"  class="btn btn-primary updateMerchantBtn" onclick="updateMerchantBtn()">保存</button>
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