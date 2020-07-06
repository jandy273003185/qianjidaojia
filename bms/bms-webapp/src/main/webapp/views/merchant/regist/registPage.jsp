<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户注册</title>
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

var mechantName = true;
function validateMerchantName(object){
	var name = object.value;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATEMERCHANTNAME%>',
        data:{name:name},
        success:function(data){
        	if(data.result=="FAIL"){
				$("#custNameLab").text("该商户名称已经被占用");
				mechantName = false;
			}else{
				$("#custNameLab").text("");
				mechantName = true;
			}
		}});
} 	


var door_id = 0;

function delDoorImg(obj){
	$("#trDoor"+obj.id).remove();
	$("#_trDoor"+obj.id).remove();
}

function createTr(){
    var tab=document.getElementById('merchant_table');
    var n=document.getElementById('next_id').rowIndex;
    
    var tr=tab.insertRow(n);
    tr.setAttribute('id','trDoor'+door_id);
    var td1=tr.insertCell(0);
    /* td1.innerHTML='门头照<span style="color:red">*</span>'; */
    td1.setAttribute("class", "td-left");
    var td2=tr.insertCell(1);
  	td2.innerHTML=
	'<a data-toggle="modal" id="dor_'+door_id+'" onclick="showDoorImgs(this);" class="tooltip-success doorPhoto'+door_id+'Click"  data-target="#previewImageModal" >'+
    	'<label id="doorPhoto'+door_id+'Div2"  class="uploadImage">'+
    		'<img  id="doorPhoto'+door_id+'Image" style="width:100%;height:100%;display:none"/>'+
    	'</label>'+
	'</a>'+
	'<div style="float:left;margin-top:50px" id="_doorPhoto'+door_id+'" onclick="putDoorPhotoImage(this)">'+
	'<input type="file"  name="doorPhoto'+door_id+'" id="doorPhoto'+door_id+'"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>'+
 	'</div>'+
 	'<label class="label-tips" id="doorPhoto'+door_id+'Label" style="float:left;margin-top:88"></label>'+
 	'<input type="button" style="float:right;margin:60px 30px 30px 30px" onclick="delDoorImg(this);" value="删      除" id="'+door_id+'" >';
	td2.setAttribute("class", "td-right");
	$("#door_temp").append(
		'<input type="hidden" id="doorPhoto'+door_id+'temp" />'
	);
	
	createShowTr();
	
}

function showDoorImgs(obj){
	var id_ = obj.id.substring(4);
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto"+id_);
	return previewImage(divObj,imageObj,obj); 
}

function createShowTr(){
    var tab=document.getElementById('sample-table-2');
    var n=document.getElementById('_trDoor0').rowIndex+1;
    
    var tr=tab.insertRow(n);
    tr.setAttribute('id','_trDoor'+door_id);
    tr.setAttribute('style','display:none');
    var td1=tr.insertCell(0);
    td1.setAttribute("class", "td-left");
    var td2=tr.insertCell(1);
  	td2.innerHTML='<a data-toggle="modal" class="tooltip-success doorPhoto'+door_id+'Image2Click"  data-target="#previewImageModal" >'+
 						'<label id="doorPhoto'+door_id+'Div2" class="uploadImage">'+
 							'<img  id="doorPhoto'+door_id+'Image2" style="width:100%;height:100%; display: none" >'+
 						'</label>'+
  				  '</a>'+
  				  '<span class="doorPhoto'+door_id+'" style="float:left;margin-top:95px;"></span>';
  					
	td2.setAttribute("class", "td-right");
  	td2.setAttribute("colspan",3);

}


function putDoorPhotoImage(obj_){
	var obj = obj_.id.substring(1);
	var _this = $("#"+obj);
	
	 //压缩图片
	 $(_this).localResizeIMG({
		 width: 400,//宽度
	      quality: 1,//质量
	      success: function (result,file) {
				var att = pre.substr(pre.lastIndexOf("."));
				//压缩后图片的base64字符串
				var base64_string = result.clearBase64;
				
				$('#'+_this.attr('id')+'temp').val(att+","+base64_string);
				//图片预览
	             var imgObj = $('#'+_this.attr('id')+'Image');
	             var imgObj2 = $('#'+_this.attr('id')+'Image2');
	             imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
	             imgObj2.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
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
	
	 //判断是否门头照
     if(obj=="doorPhoto"+door_id){
   	   door_id++;
   	   createTr();
     }
	
	 $("#_trDoor"+obj.substring(9)).attr("style","display:");
	 var divObj = document.getElementById("showImageDiv");
	 var imageObj = document.getElementById(obj.id+"Image");
	return previewImage(divObj,imageObj,obj);
}


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
			             var imgObj = $('#'+_this.attr('id')+'Image');
			             imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
			             
			             var imgObj2 = $('#'+_this.attr('id')+'Image2');
			             imgObj2.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
			             
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
			    	   	        		 if(_this.attr('id')=="certAttribute1"){//身份證
			    	       	  				$("#representativeName").val(json.cardName);
			    	       	  				$("#representativeCertNo").val(json.cardId);
			    	       	  			}else if(_this.attr('id')=="businessPhoto"){//营业执照
			    	       	  				$("#businessLicense").val(json.businessLicense);
			    	       	  			    $("#custName").val(json.companyName);
			    	       	  				//$("#businessTerm").val(json.businessTermStart);
			    	       	  				if("长期"==json.businessTermEnd){
			    	       	  					//$("input[value='forever']").click();
			    	       	  				}else{
			    	       	  				   $("#businessTerm").val(json.businessTermEnd);
			    	       	  					//$("#businessTermEnd").val(json.businessTermEnd);
			    	       	  				} 
			    	       	  				//$("#custAdd").val(json.legalAddress);
			    	       	  				$("#businessRegAddr").val(json.legalAddress);
			    	       	  			
			    	       	  			}else if(_this.attr('id')=="certAttribute2"){
			    	       	  				$("#certNoValidDate").val(json.cardValidDate);
			    	       	  			    $("#certNoValidDate_").text(json.cardValidDate);
			    	       	  			
			    	       	  			}
			    	   				}
			    	   			}
			    	   		});
			             
			             
			           //判断是否门头照
			             if(_this.attr('id')=="doorPhoto"){
			            	 door_id++;
			            	 createTr();
			            	 $("#0").attr("disabled",false);
			            }
			             
			             
			             
					}
				});
			});
	
	
$(".business").css("display","none"); 
$(".publicAccount").css("display","none");
$(".legalPerson").css("display","none"); 
$(".affirm").css("display","none");

$(".emailButton").click(function(){
	hideLabel();
	
	/*邮箱校验*/
	if(isNull($("#email")[0])){
		$("#emailLab").text("请设置邮箱地址");
		$("#email").focus();
		return false;
	}
	
	if(!verifyEmailAddress($("#email")[0])){
		$("#emailLab").text("邮箱格式不对,可使用字母、数字、下划线");
		$("#email").focus();
		return false;
	}
	
	var validate =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATE%>',
        data:{email:$("#email").val()},
        success:function(data){
        	if(data.result=="FAIL"){
				$("#emailLab").text("该邮箱已经被占用");
				validate = false;
			}else{
				validate = true;
			}
		}});
	
	if(!validate){
		return false;
	}

	hideDiv();
	$(".business").css("display","block"); 
});

$(".businessPreButton").click(function(){
	hideLabel();
	hideDiv();
	$(".email").css("display","block");
});

/* 营业执照所在地 */
var businessRegAddr = "" ;
var businessRegAddrCode = "";
$(".businessNextButton").click(function(){
	
	if(isNull($("#custName")[0])){
		$("#custNameLab").text("请填写企业名称");
		$("#custName").focus();
		return false;
	}
	/** 商户简称验证 */
	if(isNull($("#custShopName")[0])){
		$("#custShopNameLab").text("请填写企业名称");
		$("#custShopName").focus();
		return false;
	}
	
	var mchRole = $("#mchRole").val();
	var categoryId = $("#categoryId").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var country = $("#country").val();
	var agentName = $("#agentName").val();
	
	
	if(null==mchRole||""==mchRole){
		$("#mchRoleLab").text("请选择商户角色");
		$("#mchRole").focus();
		return false;
	}
	
	if(null==categoryId||""==categoryId){
		$("#categoryIdLabel").text("请选择行业类目");
		$("#categoryId").focus();
		return false;
	}
	
/* $(".publicAccount #bankAcctName").val($(".business #custName").val()); */
	
	if(isNull($("#businessLicense")[0])){
		$("#businessLicenseLab").text("请填写营业执照注册号");
		$("#businessLicense").focus();
		return false;
	}
	
	if(null==province||""==province){
		$("#countryLab").text("请选择省");
		$("#province").focus();
		return false;
	}
	
	if(null==city||""==city){
		$("#countryLab").text("请选择省");
		$("#city").focus();
		return false;
	} 
	
	if(null==country||""==country){
		$("#countryLab").text("请选择区");
		$("#country").focus();
		return false;
	}
	
	if(null == agentName || ""==agentName){
		$("#agentNameLab").text("联系人不能为空");
		$("#agentName").focus();
		return false;
		
	}
	
	if(!isCertNo($(".business  #CertNo")[0])){
		$("#CertNoLabel").text("身份证号码不正确");
		$("#CertNo").focus();
			 return false;
	};
	

	
	var businessLicense =$(".business #businessLicense").val();
	//校验营业执照注册号唯一性
	var validateLicense =true ;
	
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
	
	businessRegAddr = $(".business #businessRegAddr").val();
	if(kong.test(businessRegAddr)) {
		$("#businessRegAddrLab").text("请填写营业注册地址！");
		return false;
	}
	if(isNull($("#custAdd")[0])){
		$("#custAddLab").text("请填写企业地址");
		$("#custAdd").focus();
		return false;
	}
	if(isNull($(".business #businessTerm")[0])){
		$(".business #businessTermLab").text("请选择日期");
		$(".business #businessTerm").focus();
		return false;
	}
	 var selectDate;
     selectDate=$(".business #businessTerm").val();
     selectDate=selectDate.replace(/-/g,   "/");
     var dateA=new Date(selectDate);
     var dateNow=new Date();
     if(Date.parse(dateNow)-Date.parse(dateA)>0){
    	 $(".business #businessTermLab").text("营业期限必须大于今天");
    	 $(".business #businessTerm").focus();
         return false;
     }
	
	if(isNull($("#contactPhone")[0])){
		$("#contactPhoneLab").text("请填写企业联系电话");
		$("#contactPhone").focus();
		return false;
	}
	
	if(!isPhoneNo($("#contactPhone")[0])&&!isSetPhone($("#contactPhone")[0])){
		$("#contactPhoneLab").text("请输入正确的电话号码");
		$("#contactPhone").focus();
		return false;
	}
	
	//校验手机号唯一性
	var validateMobile =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATEMOBILE%>',
        data:{mobile:$("#contactPhone").val()},
        success:function(data){
        	if(data.result=="FAIL"){
				$("#contactPhoneLab").text("该手机号已经被占用");
				validateMobile = false;
			}else{
				validateMobile = true;
			}
		}});
	
	if(!validateMobile){
		return false;
	}
	
	
	
	
	
	
	var orgInstCode =$("#orgInstCode").val();
	var validateOrgInstCode =true ;
	
	if(!isNull($("#orgInstCode")[0])){
		if(!isValidateNtpCode(orgInstCode)){
			$("#orgInstCodeLab").text("请填写正确的9-10位组织机构代码");
			$("#orgInstCode").focus();
			return false;
		}
		//校验组织机构代码
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATEORGINSTCODE%>',
	        data:{orgInstCode:orgInstCode},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$("#orgInstCodeLab").text("该组织机构代码已经被使用");
	        		validateOrgInstCode = false;
				}else{
					validateOrgInstCode = true;
				}
			}
	    });
	}

	if(!validateOrgInstCode){
		return false;
	}
	
	if(isNull($("#fcontactunitNumber")[0])){
		$("#fcontactunitNumberLab").text("来往单位编号不可为空");
		$("#fcontactunitNumber").focus();
		return false;
	}
	
	if(!checkAttach($("#doorPhoto")[0])){
		$("#doorPhotoLab").text("请选择.jpg.jpeg.gif.bmp.png类型的门头照扫描件");
		return false;
	}
	if(categoryId=="177"){
		if(!checkAttach($("#netWorkPhoto")[0])){
			$("#netWorkPhotoLabel").text("请选择.jpg.jpeg.gif.bmp.png类型的网络文化经营许可证扫描件");
			return false;
		}
	}
	
	if(!checkAttach($("#businessPhoto")[0])){
		$("#businessPhotoLab").text("请选择.jpg.jpeg.gif.bmp.png类型的营业执照扫描件");
		return false;
	}
	
	if(!mechantName){
		return false;
	}
	hideDiv();
	hideLabel();
	$(".publicAccount").css("display","block");
	 
	
});

$(".publicAccountPreButton").click(function(){
	hideDiv();
	$(".business").css("display","block");
});

$(".legalPersonPreButton").click(function(){
	hideDiv();
	$(".publicAccount").css("display","block");
});

/********对公账号********/			
$(".publicAccountNextButton").click(function(){
	
	if(kong.test($('.publicAccount #bankAcctName').val())) {
		$(".publicAccount #bankAcctNameLabel").text("银行开户名不能为空");
		return false;
	}
	// 银行
	if(kong.test($('.publicAccount #bank').val())) {
		$(".publicAccount #bankLabel").text("请选择银行");
		return false;
	}
	if(isNull($(".publicAccount #branchBank")[0])){
		$("#branchBankLab").text("支行信息不能为空");
		$("#branchBank").focus();
		return false;
	}
	
	/* if(isNull($(".publicAccount #openAddressRec")[0])){
		$("#openAddressRecLab").text("开户行地址不能为空");
		$("#openAddressRec").focus();
		return false;
	} */
	 $(".publicAccount #bankLabel").text("");
	// 银行对公账号
	if(kong.test($(".publicAccount #bankCard").val())){
		$(".publicAccount #bankCardLabel").text("请输入银行卡账号");
		 $(".publicAccount #bankCard").focus();
		 return false;
	}
	
	$(".publicAccount #bankCardLabel").text("");
	
	if(!checkBankCardFormat($(".publicAccount #bankCard"))){
		 $(".publicAccount #bankCardLabel").text("公司对公账户为8-30位的正整数组成").addClass("small-icon tips-icon02");;
		 $(".publicAccount #bankCard").focus();
		 return false;
	 }
	 $(".publicAccount #bankCardLabel").text("");
	
	// 开户许可证照片
	 if(!checkAttach($(".publicAccount #certAttribute0")[0])){
		 $(".publicAccount #certAttribute0Label").text("请选择符合要求的开户许可证照片");
		 $(".publicAccount #certAttribute0").focus();
		 return false;
	 }
	 $(".publicAccount #certAttribute0Label").text("");
	hideDiv();
	$(".legalPerson").css("display","block");
	
});

//验证银行卡格式
function checkBankCardFormat($_obj)
{
	if(!isNumber($_obj[0]) || $_obj.val().length < 8 || $_obj.val().length > 30)
	{
		return false;
	}
	return true;
}



/********法人代表信息********/		
$(".legalPersonNextButton").click(function(){
// 法人真实姓名
if(kong.test($(".legalPerson  #representativeName").val())){	
	$(".legalPerson #representativeNameLabel").text("请输入法人真实姓名");
		$(".legalPerson #representativeName").focus();
		 return false;
};
  $(".legalPerson #representativeNameLabel").text("");
	// 法人身份证号码
if(!isCertNo($(".legalPerson  #representativeCertNo")[0])){
	$(".legalPerson #representativeCertNoLabel").text("身份证号码不正确");
		 $(".legalPerson #representativeCertNo").focus();
		 return false;
};
// 法人手机号码  
if(!isPhoneNo($(".legalPerson  #representativeMobile")[0])){
	$(".legalPerson #representativeMobileLabel").text("手机号码必须为 11 位");
		 $(".legalPerson #representativeMobile").focus();
		 return false;
};
//法人身份证有效期
if(!iscertNoValidDate($(".legalPerson  #certNoValidDate")[0])){
	$(".legalPerson #certNoValidDateLabel").text("身份证有效期格式不正确（例：20160923-20360923或2016.09.23-2036.09.23）");
		 $(".legalPerson #certNoValidDateLabel").focus();
		 return false;
};
$(".legalPerson #representativeMobileLabel").text("");
  $(".legalPerson #representativeCertNoLabel").text("");
//法人身份证正面照片
 if(!checkAttach($(".legalPerson #certAttribute1")[0])){
	 $(".legalPerson #certAttribute1Label").text("请选择符合要求的身份证正面照片");
	 $(".legalPerson #certAttribute1").focus();
	 return false;
 }
 $(".legalPerson #certAttribute1Label").text("");
//法人身份证背面照片
 if(!checkAttach($(".legalPerson #certAttribute2")[0])){
	 $(".legalPerson #certAttribute2Label").text("请选择符合要求的身份证背面照片");
	 $(".legalPerson #certAttribute2").focus();
	 return false;
 }
 $(".legalPerson #certAttribute2Label").text("");

	hideDiv();
	$(".affirm").css("display","block");
	affirmInfo();
});

/* $(".ratePreButton").click(function(){
	hideDiv();
	$(".legalPerson").css("display","block");
}); */

$(".affirmPre").click(function(){
	hideDiv();
	$(".legalPerson").css("display","block");
});

/* 提交数据 */
$(".submitbutton").click(function(){
	hideLabel();
	var auditName = $("#auditName").val();
	
	if(kong.test(auditName)) {
		$("#auditNameLabel").text("请选择审核人员");
		return false;
	}
	
 	var email =	$(".email input[name='email']").val();
 	var custName = $("input[name='custName']").val();
 	var custShopName = $("input[name='custShopName']").val();
 	var businessLicense = $("input[name='businessLicense']").val();
 	var businessTerm =$("input[name='businessTerm']").val();
 	var custAdd = $("input[name='custAdd']").val();
 	var contactPhone = $("input[name='contactPhone']").val();
 	var fcontactunitNumber = $("input[name='fcontactunitNumber']").val();
 	var orgInstCode = $("input[name='orgInstCode']").val();
 	var representativeName = $("input[name='representativeName']").val();
	var representativeCertNo = $("input[name='representativeCertNo']").val();
	var representativeMobile = $("input[name='representativeMobile']").val();
	var branchBank = $(".publicAccount input[name='branchBank']").val();
	var bankCard = $("input[name='bankCard']").val();
	var bankCode =$(".publicAccount #bank").val();
	var bankAcctName =$(".publicAccount #bankAcctName").val();
	
	var mchRole = $("#mchRole").val();
	var categoryId = $("#categoryId").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var country = $("#country").val();
	var CertNo = $("#CertNo").val();
	var agentName = $("#agentName").val();
	var mobile = $("#contactPhone").val();
	var certNoValidDate = $("#certNoValidDate").val();
	
	/* var feeCode =$("input[name='rule']:checked").val(); */
/* 	var openAddressRec = $("input[name='openAddressRec']").val(); */
	if(!checkAttach($("#businessPhoto")[0])){
		$.gyzbadmin.alertFailure("重新选择所有证件信息！");
		return false;
	}
	if(!checkAttach($("#certAttribute0")[0])){
		$.gyzbadmin.alertFailure("重新选择所有证件信息");
		return false;
	}
	if(!checkAttach($("#certAttribute1")[0])){
		$.gyzbadmin.alertFailure("重新选择所有证件信息");
		return false;
	}
	if(!checkAttach($("#certAttribute2")[0])){
		$.gyzbadmin.alertFailure("重新选择所有证件信息");
		return false;
	}
	
	var imgDoor = [];
	$("#door_temp input[type='hidden']").each(function(){
		if($(this).val()!=""){
			imgDoor.push($(this).val());
		 }
	 });
	
	$.blockUI();
    $.post(window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.REGIST%>',{
  	 			'email' : email,
  	 			'branchBank' : branchBank,
  	 			'bankAcctName' : bankAcctName,
  	 			'custName' : custName,
  	 			'businessLicense' : businessLicense,
  	 			'businessRegAddr' : businessRegAddr,
  	 			'businessTerm' : businessTerm,
  	 			'custAdd' : custAdd,
  	 			'contactPhone' : contactPhone,
  	 			'fcontactunitNumber':fcontactunitNumber,
  	 			'orgInstCode' : orgInstCode,
  	 			'representativeName' : representativeName,
  	 			'representativeCertNo' : representativeCertNo,
  	 			'representativeMobile' : representativeMobile,
  	 			'compMainAcct' : bankCard,
  	 			'compAcctBank' :bankCode,
  	 			'taskId':$("#taskId").val(),
  	 			'auditName':auditName,
  	 			'categoryType':categoryId,
  	 			'mchRole':mchRole,
  	 			'province':province,
  	 			'city':city,
  	 			'country':country,
  	 			'certifyNo':CertNo,
  	 			'mobile':mobile,
  	 			'agentName':agentName,
  	 			'shortName':custShopName
  	 			
  	 	},function(data){
  	 		$.unblockUI();
  			if(data.result=="SUCCESS"){
  				$.ajax({
					type : "POST",
					url : window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.FILEUPLOAD%>?custId='+data.custId,
					data :{
						businessPhoto : $('#businessPhototemp').val(),
						certAttribute0 : $('#certAttribute0temp').val(),
						certAttribute1 : $('#certAttribute1temp').val(),
						certAttribute2 : $('#certAttribute2temp').val(),
						doorPhoto : imgDoor,
						netWorkPhoto : $('#netWorkPhototemp').val(),
						certNoValidDate:certNoValidDate
						
					},
					dataType : "json",
					success : function(data) {
						if(data.result=='SUCCESS'){
			    				$.gyzbadmin.alertSuccess("注册申请成功,请等待审批！",null,function(){
			    					window.location.href = window.Constants.ContextPath + '<%=MerchantPath.BASE+MerchantPath.LIST %>';
			    				});
			        	}else{
			        		$.gyzbadmin.alertFailure("扫描件上传失败,请选择合适的类型");
		    				
			        	}
					}
				});  
  			}else{
  			
  				if (typeof(data.message) == "undefined") { 
  					$.gyzbadmin.alertFailure("注册申请失败,内部连接错误！请联系相关技术人员");
  				}  else{
  					$.gyzbadmin.alertFailure("注册申请失败！"+data.message);
  				}
  			}
  	 	},'json');
});
 	

/* 清空提示信息*/
function hideLabel(){
	$("#emailLab").text("");
	$("#custNameLab").text("");
	$("#branchBankLab").text("");
	$("#bankAcctNameLabel").text("");
	$("#businessLicenseLab").text("");
	$("#businessRegAddrLab").text("");
	$("#businessTermLab").text("");
	$("#custAddLab").text("");
	$("#contactPhoneLab").text("");
	$("#businessPhotoLab").text("");
	$("#orgInstCodeLab").text("");
	$("#auditNameLabel").text("");
	/* $(".rate #ruleLabel").text(""); */
}

/* 隐藏div */
function hideDiv(){
	$(".business").css("display","none"); 
	$(".email").css("display","none");
	$(".publicAccount").css("display","none");
	$(".legalPerson").css("display","none");
	/* $(".rate").css("display","none"); */
	$(".affirm").css("display","none");
	
}

/* 赋值  */
function affirmInfo(){
		var custName = $("input[name='custName']").val();
		var custShopName = $("input[name='custShopName']").val();
		var businessLicense = $("input[name='businessLicense']").val();
		var businessTerm =$("input[name='businessTerm']").val();
		var custAdd = $("input[name='custAdd']").val();
		var contactPhone = $("input[name='contactPhone']").val();
		var fcontactunitNumber = $("input[name='fcontactunitNumber']").val();
		/* var openName = $("input[name='custName']").val(); */
		/* var openAddressRec = $("input[name='openAddressRec']").val(); */
		var bankCard = $("input[name='bankCard']").val();
		var branchBank = $(".publicAccount input[name='branchBank']").val();
		var bankAcctName = $(".publicAccount input[name='bankAcctName']").val();
		var bankName =$(".publicAccount #bank").find("option:selected").text();
		var representativeName = $("input[name='representativeName']").val();
		var representativeCertNo = $("input[name='representativeCertNo']").val();
		var representativeMobile = $("input[name='representativeMobile']").val();
		var businessPhoto = $("input[name='businessPhoto']").val();
		var certAttribute0 = $("input[name='certAttribute0']").val();
		var certAttribute1= $("input[name='certAttribute1']").val();
		var certAttribute2 =$("input[name='certAttribute2']").val();
		/* var feeName =$("input[name='rule']:checked").val(); */
		$(".affirm .custName").text(custName);
		$(".affirm .custShopName").text(custShopName);
		/* $(".affirm .openAddressRec").text(openAddressRec); */
		$(".affirm .businessLicense").text(businessLicense);
		$(".affirm .businessRegAddr").text(businessRegAddr);
		$(".affirm .businessTerm").text(businessTerm);
		$(".affirm .custAdd").text(custAdd);
		$(".affirm .contactPhone").text(contactPhone);
		$(".affirm .fcontactunitNumber").text(fcontactunitNumber);
		/* $(".affirm .openName").text(openName); */
		$(".affirm .bankCard").text(bankCard);
		$(".affirm .branchBank").text(branchBank);
		$(".affirm .bankAcctName").text(bankAcctName);
		$(".affirm .bank").text(bankName);
		$(".affirm .representativeName").text(representativeName);
		$(".affirm .representativeCertNo").text(representativeCertNo);
		$(".affirm .representativeMobile").text(representativeMobile);
		$(".affirm .bankCardLast").text(bankCard);
		/* $(".affirm .rule").text(feeName); */
		$(".affirm .businessPhoto").text(businessPhoto);
		$(".affirm .certAttribute0").text(certAttribute0);
		$(".affirm .certAttribute1").text(certAttribute1);
		$(".affirm .certAttribute2").text(certAttribute2);
	}

});

//点击选择行业类目
function getCategoryIdValue(){
    var objS = document.getElementById("categoryId");
    var grade = objS.options[objS.selectedIndex].value;
    
    if(grade=="177"){
    	$("#netWorkPhoto_").attr("style","display:");
    	$("#netWorkPhoto1_").attr("style","display:");
    	$("#netWorkPhoto2_").attr("style","display:");
    	
    }else{
    	$("#netWorkPhoto_").attr("style","display:none");
    	$("#netWorkPhoto1_").attr("style","display:none");
    	$("#netWorkPhoto2_").attr("style","display:none");
    }
};


function selMchRole(){
	  var mchRole = $("#mchRole").val();
	  if("0"==mchRole){//线上
		  $("#xn").attr("style","display:none");
		  $("#st").attr("style","display:");
		  $("#qt").attr("style","display:none");
		  $("#categoryTypeId").val(100);
	  }else if("1"==mchRole){//线下
		  $("#xn").attr("style","display:none");
		  $("#st").attr("style","display:");
		  $("#qt").attr("style","display:none");
		  $("#categoryTypeId").val(100);
	  }else{//其他
		  $("#xn").attr("style","display:none");
		  $("#st").attr("style","display:");
		  $("#qt").attr("style","display:none");
		  $("#categoryTypeId").val(100);
	  }
	  getCategoryList();
}

//获取行业类目
function getCategoryList()
{
	var categoryType = $("#categoryTypeId").val().trim();
	
	$("#category").siblings().remove();

	if ("" == categoryType || categoryType.length == 0) {
		return false;
	}
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETCATEGORYTYPE %>',
		data:
		{
			"categoryType" 	: categoryType,
			"channlCode"	: 'iCr'
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				var categoryList = data.categoryList;
				for ( var category in categoryList) {
					$("#categoryId").append(
							"<option value='"+ categoryList[category].categoryId +"'>"
									+ categoryList[category].categoryName + "</option>");
				}
			}
		}
	})
}


function getCityList()
{
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
							"<option value='"+ cityList[city].cityId +"'>"
									+ cityList[city].cityName + "</option>");
				}
			}else{
			}
		}
	})
}

function getAreaList()
{
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
							"<option value='"+ areaList[area].areaId +"'>"
									+ areaList[area].areaName + "</option>");
				}
			}else{
			}
		}
	})
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
					<!-- 账户邮箱 -->
					
					<div class="email">
					<div class="list-table-header">注册邮箱</div>
						<input type="hidden" id="taskId" value="${taskId }">
						<input type="hidden" id="businessPhototemp"/>
						<input type="hidden" id="certAttribute0temp"/>
						<input type="hidden" id="certAttribute1temp" />
						<input type="hidden" id="certAttribute2temp" />
						<input type="hidden" id="netWorkPhototemp" />
						<div id="door_temp">
							<input type="hidden" id="doorPhototemp" />
						</div>
						
						<table class="search-table">
							<tr>
								<td class="td-left">邮箱地址<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
											<input type="text" id="email" name="email" maxlength="55" >
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="emailLab"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" class="combine">
									<input type="button" value="下一步" class="emailButton">
								</td>
							</tr>
						</table>
					</div>
					
					<!-- 企业信息 -->
					<div class="business" >
						<div class="list-table-header">企业信息</div>
						<table class="search-table" id="merchant_table">
								<tr>
									<td class="td-left" >企业名称<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="custName" name="custName" maxlength="100"   placeholder="企业名称">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="custNameLab"></label>
										</span>
									</td>
								</tr>
								
								<tr>
									<td class="td-left" >企业简称<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="custShopName" name="custShopName" maxlength="100" onblur="validateMerchantName(this)"  placeholder="企业简称">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="custShopNameLab"></label>
										</span>
									</td>
								</tr>
								
								<tr>
								<td class="td-left">商户角色<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="mchRole" style="width:100;" id="mchRole" onchange="selMchRole()">
											<option value="0" selected>线上商户</option>
											<option value="1">线下商户</option>
										</select>		
										<label class="label-tips" id="mchRoleLab"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left">行业类目<span style="color:red">*</span></td>
								<td class="td-right">
									<span class="input-icon">
									<select name="categoryTypeId" id="categoryTypeId" style="width:180px;" onchange="getCategoryList();">
										<option value="">--请选择类目类别--</option>
										<option value="100" id="st" style="display:">实体</option>
										<option value="200" id="xn" style="display: none">虚拟</option>
										<option value="300" id="qt" style="display: none">其他</option>
									</select>
									<select name="categoryId" id="categoryId" style="width:300px;" onchange="getCategoryIdValue();">
										<option value="" id="category">--请选择类目--</option>
									</select>
									<label id="categoryIdLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
								
								<tr>
									<td class="td-left">营业执照注册号<span style="color:red">*</span></td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="businessLicenseLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业执照所在地<span style="color:red">*</span></td>
									<td class="td-right">
									<input type="text" id="businessRegAddr" name="businessRegAddr" placeholder="营业执照注册所在地" style="width:60%">
									   <%--  <div id="base">
										<label> 
											<select id="province" name="province" onchange="refreshCity()"> 
												<option value="">选择省份</option> 
												<c:forEach items="${provincelist}" var="pro"> 
												<option value="${pro.provinceId}">${pro.provinceName}</option> 
												</c:forEach> 
											</select> 
										</label> 
										<label> 
											<select id="city" name="city" > 
												<option value="">选择城市</option> 
											</select> 
										</label>  --%>
										<label class="label-tips" id="businessRegAddrLab"></label>
								</div>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">省市地区<span style="color:red">*</span></td>
									<td class="td-right">
										<div class="col-xs-4 pd0" style="padding:0">
	                                    <select class="form-control" id="province" onchange="getCityList();">
	                                        <c:if test="${not empty provincelist_ }">
	                                         	<option value="qxz">--请选择--</option>
								               <c:forEach items="${provincelist_ }" var="prov">
								                   <option value="${prov.provinceId}">${prov.provinceName}</option>
								               </c:forEach>
				               				</c:if>
	                                    </select>
		                                </div>
		                                <div class="col-xs-4 pd0" style="width:31.33%;margin:0 1%;padding:0;">
		                                <select class="form-control" id="city" onchange="getAreaList();">
		                                    <option value="" id="cityDef">--请选择--</option>
		                                </select>
		                                </div>
		                                <div class="col-xs-4 pd0" style="padding:0">
		                                <select class="form-control" id="country">
		                                    <option value="" id="areaDef">--请选择--</option>
		                                </select>
		                                </div>
		                                <label class="label-tips" id="countryLab"></label>
									</td>
							    </tr>
								
								<tr>
									<td class="td-left">商户地址<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="custAdd" name="custAdd" maxlength="100" placeholder="商户地址" style="width:40%">
										<label class="label-tips" id="custAddLab"></label>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业期限<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="businessTerm" name="businessTerm" readonly="readonly" onfocus="WdatePicker({minDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
										<label class="label-tips" id="businessTermLab"></label>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">联系人<span style="color:red">*</span></td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="agentName" name="agentName" placeholder="联系人">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="agentNameLab"></label>
										</span>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">联系电话<span style="color:red">*</span></td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="contactPhone" name="contactPhone" placeholder="联系电话">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="contactPhoneLab"></label>
										</span>
									</td>
								</tr>
								
								
								<tr>
									<td class="td-left" >联系人身份证号码<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="CertNo" id="CertNo" placeholder="联系人身份证号码">
											<i class="icon-leaf blue"></i>
											<label id="CertNoLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								
								
								<tr>
									<td class="td-left">组织机构码：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="orgInstCode" name="orgInstCode" placeholder="组织机构码">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="orgInstCodeLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">来往单位编号：<span style="color:red">*</span></td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="fcontactunitNumber" name="fcontactunitNumber" placeholder="来往单位编号">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="fcontactunitNumberLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业执照扫描件<span style="color:red">*</span></td>
									<td class="td-right">
										<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal">
										<label id="businessPhotoDiv" class="uploadImage">
											<img  id="businessPhotoImage" style="width:100%;height:100%; display: none" >
										</label>
										</a>
										<div style="float:left;margin-top:75" >
										<!-- <input type="file"  name="uploadfile" id="businessPhoto"  onchange="showBusinessPhotoImage(this)" accept="image/*" multiple  /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span> -->
										<input type="file"   name="uploadfile" id="businessPhoto" accept="image/*"  /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div> 
										<label class="label-tips" id="businessPhotoLab" style="float:left;margin-top:88" ></label>
									</td>
								</tr>
								<tr id="netWorkPhoto_" style="display: none">
									<td class="td-left" >网络文化经营许可证<span style="color:red">*</span></td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success netWorkPhotoClick"  data-target="#previewImageModal" >
											<label id="netWorkPhotoDiv2" class="uploadImage">  
											        <img  id="netWorkPhotoImage" style="width:100%;height:100%;display:none"/>
											</label>
										</a>
										<div style="float:left;margin-top:75" >
										<input type="file" name="netWorkPhoto" id="netWorkPhoto"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>
										
										<label class="label-tips" id="netWorkPhotoLabel" style="float:left;margin-top:88"></label>
									</td>
								</tr>
								<tr id="trDoor0">
									<td class="td-left">门头照<span style="color:red">*</span></td>
									<td class="td-right">
										<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal">
										<label id="doorPhotoDiv" class="uploadImage">
											<img  id="doorPhotoImage" style="width:100%;height:100%; display: none" >
										</label>
										</a>
										<div style="float:left;margin-top:75" >
										<input type="file"   name="uploadfile" id="doorPhoto" accept="image/*"  /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div> 
										<label class="label-tips" id="doorPhotoLab" style="float:left;margin-top:88" ></label>
										<!-- <input type="button" style="float:right;margin:60px 30px 30px 30px" value="删      除" onclick="delDoorImg(this);" id="0" disabled="disabled"> -->
									</td>
								</tr>
								<tr  id="next_id">
									<td colspan="2" align="center" class="combine">
										<input type="button" value="上一步" class="businessPreButton">
										<input type="button" value="下一步" class="businessNextButton">
									</td>
								</tr>
							</table>
					</div>
					<!-- 对公账户  -->
					<div class="publicAccount" >
						<div class="list-table-header">账户信息</div>
						<table class="search-table">
								<tr>
									<td class="td-left" >公司对公账户<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="bankCard" name="bankCard" placeholder="公司账号">
											<i class="icon-leaf blue"></i>
											<label id="bankCardLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" style="width:50%">账户开户名<span style="color:red">*</span></td>
									<td class="td-right" style="width:50%"> 
										<span class="input-icon">
											<input type="text" id="bankAcctName" name="bankAcctName">
											<label id="bankAcctNameLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >开户银行<span style="color:red">*</span></td>
									<td class="td-right" > 
										  <sevenpay:selectBankTag id="bank" name ="bank"  banks="${banklist}"  clasS="width-20"/>
										  <label id="bankLabel" class="label-tips"></label>
									</td>
								</tr>
								<tr>
								<td class="td-left">支行信息<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
											<input type="text" id="branchBank" name="branchBank" maxlength="55" >
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="branchBankLab"></label>
									</span>
								</td>
								</tr>
								<!-- <tr>
									<td class="td-left">开户行地址：<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="openAddressRec" name="openAddressRec" maxlength="55" >
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="openAddressRecLab"></label>
									</span>
								</td>
								</tr> -->
								
								<tr>
									<td class="td-left" >开户许可证<span style="color:red">*</span></td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute0Click"  data-target="#previewImageModal">
											<label id="certAttribute0Div" class="uploadImage">  
											        <img  id="certAttribute0Image" style="width:100%;height:100%;display:none"/>
											</label>
										</a>
										<div style="float:left;margin-top:75" >
											<input type="file" name="uploadfile" id="certAttribute0"  accept="image/*" multiple /><p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>										
										<label class="label-tips" id="certAttribute0Label" style="float:left;margin-top:88"></label>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="combine">
										<input type="button" value="上一步" class="publicAccountPreButton">
										<input type="button" value="下一步" class="publicAccountNextButton">
									</td>
								</tr>
						</table>
					</div>
					
					<!-- 法人信息 -->
					<div class="legalPerson" >
						<div class="list-table-header">法人信息</div>
						<table class="search-table">
								<tr>
									<td class="td-left" style="width:50%">法人真实姓名<span style="color:red">*</span></td>
									<td class="td-right" style="width:50%"> 
										<span class="input-icon">
											<input type="text" id="representativeName" name="representativeName" placeholder="法人真实姓名" maxlength="50">
											<i class="icon-leaf blue"></i>
											<label id="representativeNameLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人身份证号码<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="representativeCertNo" id="representativeCertNo" placeholder="法人身份证号码">
											<i class="icon-leaf blue"></i>
											<label id="representativeCertNoLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人手机号码<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="representativeMobile"  name="representativeMobile" placeholder="法人手机号码">
											<i class="icon-leaf blue"></i>
											<label id="representativeMobileLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >身份证图片正面<span style="color:red">*</span></td>
									<td class="td-right" >
										<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
										<label id="certAttribute1Div" class="uploadImage">  
										        <img  id="certAttribute1Image" style="width:100%;height:100%;display:none"/>
										</label>
										</a>
										<div style="float:left;margin-top:75" >
										<input type="file" name="uploadfile" id="certAttribute1"  accept="image/*" multiple /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>
										<label class="label-tips" id="certAttribute1Label" style="float:left;margin-top:88"></label>
									</td>
								</tr>
								
								<tr>
									<td class="td-left" >身份证图片背面<span style="color:red">*</span></td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal" >
											<label id="certAttribute2Div" class="uploadImage">  
											        <img  id="certAttribute2Image" style="width:100%;height:100%;display:none"/>
											</label>
										</a>
										<div style="float:left;margin-top:75" >
										<input type="file" name="uploadfile" id="certAttribute2"  accept="image/*" multiple /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>
										
										<label class="label-tips" id="certAttribute2Label" style="float:left;margin-top:88"></label>
									</td>
								</tr>
								
								<tr>
									<td class="td-left" >身份证有效期<span style="color:red">*</span></td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="certNoValidDate" id="certNoValidDate" placeholder="法人身份证号码有效期">
											<i class="icon-leaf blue"></i>
											<label id="certNoValidDateLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="combine">
										<input type="button" value="上一步" class="legalPersonPreButton">
										<input type="button" value="下一步" class="legalPersonNextButton">
									</td>
								</tr>
						</table>
					</div>
					
					<!-- 费率 -->
					<%-- <div class="rate" >
					<div class="list-table-header">费率信息</div>
						<table class="search-table">
							<tr>
								<td class="td-left" style="width:50%">请选择费率方式<span style="color:red">*</span></td>
								<td class="td-right" style="width:50%"> 
								
									<select id="ruleB" name ="ruleB" class="width-28">
										<option value="">--请选择费率--</option>
										<option value="Fixed">固定费率</option>
										<option value="Package">套餐费率</option>
									</select>
									<label id="ruleLabel" class="label-tips"></label>
									
								</td>
							</tr>
							<tr class="PackageTr" style="display:none">
							<td align="center" colspan="2" >
								<sevenpay:selectRulePackageTag name="rule"  style="width:40%;font-size:10px;text-align:center;"/>
							</td>
							</tr>
							<tr class="FixedTr" style="display:none">
								<td align="center" colspan="2" >
									<sevenpay:selectRuleFixedTag name="rule" style="width:40%;font-size:10px;text-align:center;"/>
								</td>
							</tr>
							
							<tr>
									<td colspan="2" align="center" class="combine">
										<input type="button" value="上一步" class="ratePreButton">
										<input type="button" value="下一步" class="rateNextButton">
									</td>
								</tr>
						</table>
					</div> --%>
					
					
					<!-- 确认信息  -->
					<div class="affirm" style="width:1100px;">
						<div class="list-table-header">请确认你提交的信息  <a href="#" class="affirmPre" style="color:yellow;">返回修改</a> </div>
						<table id="sample-table-2" class="list-table"  >
						    <tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">企业信息：</td>
							</tr>
							<tr>
								<td class="td-left" width="18%" >公司名称：</td>
								<td class="td-right" width="32%" > 
									<span class="custName"></span>
								</td>
								
								<td class="td-left" width="18%" >公司简称：</td>
								<td class="td-right" width="32%" > 
									<span class="custShopName"></span>
								</td>
							</tr>
							
							<tr>
								<td class="td-left" >营业执照注册号：</td>
								<td class="td-right" colspan="3"> 
									<span class="businessLicense"></span>
								</td>
							</tr>
							
							<tr>
								<td class="td-left" >营业执照所在地：</td>
								<td class="td-right" > 
									<span class="businessRegAddr"></span>
								</td>
							
								<td class="td-left" >营业期限：</td>
								<td class="td-right" > 
									<span class="businessTerm"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >企业地址：</td>
								<td class="td-right" > 
									<span class="custAdd"></span>
								</td>
							
								<td class="td-left" >联系电话：</td>
								<td class="td-right" > 
									<span class="contactPhone"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >来往单位编号：</td>
								<td class="td-right" colspan="3" > 
									<span class="fcontactunitNumber"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >营业执照扫描件：</td>
								<td class="td-right"> 
									<a data-toggle='modal' class="tooltip-success businessPhotoImage2Click"  data-target="#previewImageModal" >
										<label id="businessPhotoDiv2" class="uploadImage">
												<img  id="businessPhotoImage2" style="width:100%;height:100%; display: none" >
										</label>
									</a>
									<span class="businessPhoto" style="float:left;margin-top:95px;"></span>
								</td>
								
								<td style="display: none" id="netWorkPhoto1_" class="td-left" >网络文化经营许可证：</td>
								<td class="td-right" id="netWorkPhoto2_"> 
									<a data-toggle='modal' class="tooltip-success netWorkPhotoImage2Click"  data-target="#previewImageModal" >
										<label id="netWorkPhotoDiv2" class="uploadImage">
												<img  id="netWorkPhotoImage2" style="width:100%;height:100%; display: none" >
										</label>
									</a>
									<span class="netWorkPhoto" style="float:left;margin-top:95px;"></span>
								</td>
							</tr>
							
							<tr id="_trDoor0">
								<td class="td-left" >门头照：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success doorPhotoImage2Click"  data-target="#previewImageModal" >
										<label id="doorPhotoDiv2" class="uploadImage">
												<img  id="doorPhotoImage2" style="width:100%;height:100%; display: none" >
										</label>
									</a>
									<span class="doorPhoto" style="float:left;margin-top:95px;"></span>
								</td>
							</tr>
							
							
							<tr>
							<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">银行信息：</td>
							</tr>
							<tr>
								<td class="td-left" >银行开户名：</td>
								<td class="td-right" > 
									<span class="bankAcctName"></span>
								</td>
							
								<td class="td-left" >开户银行：</td>
								<td class="td-right" > 
									<span class="bank"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >公司对公账户：</td>
								<td class="td-right" > 
									<span class="bankCard"></span>
								</td>
								<td class="td-left" >支行信息：</td>
								<td class="td-right" > 
									<span class="branchBank"></span>
								</td>
								
							</tr>
							<tr>
						<!-- 	<td class="td-left" >开户行地址：</td>
								<td class="td-right" > 
									<span class="openAddressRec"></span>
								</td> -->
							<td class="td-left">开户许可证：</td>
								<td class="td-right" colspan="3" > 
								<a data-toggle='modal' class="tooltip-success certAttribute0Image2Click"  data-target="#previewImageModal" >
									<label id="certAttribute0Div2" class="uploadImage">  
									        <img  id="certAttribute0Image2" style="width:100%;height:100%;display:none"/>
									</label>
								</a>
									<span class="certAttribute0" style="float:left;margin-top:95px;"></span>
								</td>
							</tr>
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">法人信息：</td></tr>
							<tr>
								<td class="td-left" >法人真实姓名：</td>
								<td class="td-right" > 
									<span class="representativeName"></span>
								</td>
								<td class="td-left" >法人身份证号码：</td>
								<td class="td-right" > 
									<span class="representativeCertNo"></span>
								</td>
							</tr>
							<tr>
							   <td class="td-left" >身份证正面：</td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success certAttribute1Image2Click"  data-target="#previewImageModal" >
										<label id="certAttribute1Div2" class="uploadImage">  
											   <img  id="certAttribute1Image2" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<span class="certAttribute1" style="float:left;margin-top:95px;"></span>
								</td>
								<td class="td-left" >身份证背面：</td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success certAttribute2Image2Click"  data-target="#previewImageModal" >
									<label id="certAttribute2Div2" class="uploadImage">  
										<img  id="certAttribute2Image2" style="width:100%;height:100%;display:none"/>
									</label>
									</a>
									<span class="certAttribute2" style="float:left;margin-top:95px;"></span>
								</td>
							</tr>
							
							
							
							<tr>
								<td class="td-left" >身份证有效期：</td>
								<td class="td-right" colspan="3" > 
									<span class="certNoValidDate" id="certNoValidDate_"></span>
								</td>
							</tr>
							
							
							<tr>
								<td class="td-left" >法人手机号码：</td>
								<td class="td-right" colspan="3" > 
									<span class="representativeMobile"></span>
								</td>
							</tr>	
							
							<!-- <tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">费率方式：</td>
							</tr> -->
						<!-- 	<tr>
								<td class="td-left" >费率方式：</td>
								<td class="td-right " colspan="3"> 
									<table class="packageTable" style="width:50%;font-size:10px;text-align:center;display:none;">
										<tr>
											<th>收费方式</th>
											<th>交易量(元)</th>
											<th>费率</th>
											<th>结算周期</th>
										</tr>
										<tr class="htm1">
										</tr>
										<tr class="htm2">
										</tr>
										<tr class="htm3">
										</tr>
										<tr class="htm4">
										</tr>
									</table>
									<table class="fixedTable" style="width:50%;font-size:10px;text-align:center;display:none;">
										<tr>
											<th>收费方式</th>
											<th>费率</th>
											<th>结算周期</th>
										</tr>
										<tr class="htmFixed">
										</tr>
									</table>
									
								</td>
							</tr> -->
							
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">审核人员：</td>
							</tr>
							<tr>
								<td class="td-left" >审核人员：</td>
								<td class="td-right" colspan="3"> 
									<gyzbadmin:selectUserTag id="auditName" name="user" users="${userlist}" clasS="width-20"/>
									<label id="auditNameLabel" class="label-tips"></label>
								</td>
								
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="button" value="提交"  class="submitbutton">
								</td>
							</tr>
						</table>
					</div>
					<!-- 图片预览 -->
					<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-dialog showDiv" style="width:60%;height:80%;">
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
/*图片预览*/
/* function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImage"); 
	 var divObj2 = document.getElementById("businessPhotoDiv2");  
	 var imageObj2 = document.getElementById("businessPhotoImage2");
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result2&&result1;  
}

function showCertAttribute0Image(obj){  
	 var divObj = document.getElementById("certAttribute0Div");  
	 var imageObj = document.getElementById("certAttribute0Image"); 
	 
	 var divObj2 = document.getElementById("certAttribute0Div2");  
	 var imageObj2 = document.getElementById("certAttribute0Image2");
	 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result2&&result1;  
}

function showCertAttribute1Image(obj){  
	 var divObj = document.getElementById("certAttribute1Div");  
	 var imageObj = document.getElementById("certAttribute1Image");
	 
	 var divObj2 = document.getElementById("certAttribute1Div2");  
	 var imageObj2 = document.getElementById("certAttribute1Image2"); 
	 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result2&&result1;  
}

function  showCertAttribute2Image(obj){  
	 var divObj = document.getElementById("certAttribute2Div");  
	 var imageObj = document.getElementById("certAttribute2Image");  
	 
	 var divObj2 = document.getElementById("certAttribute2Div2");  
	 var imageObj2 = document.getElementById("certAttribute2Image2"); 
	 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result2&&result1;  
} */


 $('.businessPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("businessPhoto"); 
	return previewImage(divObj,imageObj,obj); 
});

$('.netWorkPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("netWorkPhoto"); 
	return previewImage(divObj,imageObj,obj); 
});



/** 门头照背面点击预览 **/
$('.doorPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto");
	return previewImage(divObj,imageObj,obj); 
});

$('.doorPhotoImage2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto");
	return previewImage(divObj,imageObj,obj); 
});

 
 $('.businessPhotoImage2Click').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("businessPhoto"); 
		return previewImage(divObj,imageObj,obj); 
});  
 
 $('.netWorkPhotoImage2Click').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("netWorkPhoto"); 
		return previewImage(divObj,imageObj,obj); 
});  

 
 
$('.certAttribute0Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute0");
	return previewImage(divObj,imageObj,obj); 
});

$('.certAttribute0Image2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute0");
	return previewImage(divObj,imageObj,obj); 
});

$('.certAttribute1Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute1");
	return previewImage(divObj,imageObj,obj); 
});

$('.certAttribute1Image2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute1");
	return previewImage(divObj,imageObj,obj); 
});
$('.certAttribute2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute2");
	return previewImage(divObj,imageObj,obj); 
});
$('.certAttribute2Image2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute2");
	return previewImage(divObj,imageObj,obj); 
}); 


</script>
</html>	