<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.myWorkSpace.WorkSpacePath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户注册修改</title>
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
$(function(){

	
	//费率选择设置
	$("#ruleB").change(function(){
		$(".PackageTr").hide();
		$(".FixedTr").hide();
		$("input[name='rule']").removeAttr("checked");
		
		var value =	$("#ruleB").val();
		if(value=='Package'){
			$(".PackageTr").show();
		}
		if(value=='Fixed'){
			$(".FixedTr").show();
		}
		
	});

$(".business").css("display","none"); 
$(".publicAccount").css("display","none");
$(".legalPerson").css("display","none");
$(".rate").css("display","none");
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
        data:{email:$("#email").val(),
        	  custId:$("#custId").val()
        	},
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
var businessRegAddr ;
var businessRegAddrCode


var businessImageIsChange = "false";
var certAttribute0IsChage = "false";
var certAttribute1IsChage = "false";
var certAttribute2IsChage = "false";
$(".businessNextButton").click(function(){
	
	hideLabel();
	if(isNull($("#custName")[0])){
		$("#custNameLab").text("请填写企业名称");
		$("#custName").focus();
		return false;
	}
	
	if(isNull($("#businessLicense")[0])){
		$("#businessLicenseLab").text("请填写营业执照注册号");
		$("#businessLicense").focus();
		return false;
	}
	
	  // 验证营业执照号 
	 if(!isNumber($("#businessLicense")[0])){
		$("#businessLicenseLab").text("营业执照为15位正整数组成");
		$("#businessLicense").focus();
		return false;
	} 
	
	/* if(!isValidBusCode($(".business #businessLicense").val())){
		$("#businessLicenseLab").text("营业执照为6位首次登记机关码、8位顺序码，1位数字校验码组成");
		$("#businessLicense").focus();
		return false;
	}     */
	
	var businessLicense =$(".business #businessLicense").val();
	//校验营业执照注册号唯一性
	 var validateLicense =true ;
	
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATELICENSE%>',
        data:{businessLicense:$(".business #businessLicense").val(),
        	 custId:$("#custId").val()
        	},
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
	
	/* businessRegAddrCode = $('#province').val()+','+$('#city').val();
	
	var province = $('#province').find('option:selected').text();
	var city = $('#city').find('option:selected').text();
	if(province==""||city==""){
		$("#businessRegAddrLab").text("请选择地址");
		return false;
	}
	
	
	//显示具体地名
	businessRegAddr = province+","+city;
	if(province==city){
		businessRegAddr=city;
	} */
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
	
	if(!isPhoneNo($("#contactPhone")[0])){
		$("#contactPhoneLab").text("手机号码必须为 11 位");
		$("#contactPhone").focus();
		return false;
	}
	
	
	if(isNull($("#orgInstCode")[0])){
		$("#orgInstCodeLab").text("组织机构码不能为空");
		$("#orgInstCode").focus();
		return false;
	}
	
	if(!isValidateNtpCode($("#orgInstCode").val())){
		$("#orgInstCodeLab").text("请填写正确的9-10位组织机构代码");
		$("#orgInstCode").focus();
		return false;
	}
	var orgInstCode =$("#orgInstCode").val();
	 //校验组织机构代码唯一性
	var validateOrgInstCode =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATEORGINSTCODE%>',
        data:{orgInstCode:orgInstCode,
        	custId:$("#custId").val()
        	},
        success:function(data){
        	if(data.result=="FAIL"){
        		$("#orgInstCodeLab").text("该组织机构代码已经被使用");
        		validateOrgInstCode = false;
			}else{
				validateOrgInstCode = true;
			}
			 }});
	if(!validateOrgInstCode){
		return false;
	} 
	
	$(".publicAccount #custNameLabel").text($("#custName").val());

	
	if(kong.test($(".businessImageName").text())){
		businessImageIsChange = "true";
		if(!checkAttach($("#businessPhoto")[0])){
			$("#businessPhotoLab").text("请选择合适的营业执照扫描件");
			return false;
		} 
	}
	
	hideDiv();
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
	// 银行
	if(kong.test($('.publicAccount #bank').val())) {
		$(".publicAccount #bankLabel").text("请选择银行");
		return false;
	}
	 $(".publicAccount #bankLabel").text("");
	// 银行对公账号
	if(kong.test($(".publicAccount #bankCard").val())){
		$(".publicAccount #bankCardLabel").text("请输入银行卡账号");
		 $(".publicAccount #bankCard").focus();
		 return false;
	}
	
	$(".publicAccount #bankCardLabel").text("");
	
	if(!checkBankCardFormat($(".publicAccount #bankCard"))){
		 $(".publicAccount #bankCardLabel").text("银行卡为12-19位的正整数组成").addClass("small-icon tips-icon02");;
		 $(".publicAccount #bankCard").focus();
		 return false;
	 }
	 $(".publicAccount #bankCardLabel").text("");
	 
	 if(kong.test($(".certAttributeName").text())){
		 certAttribute0IsChage= "true";
		  // 开户许可证照片
		 if(!checkAttach($(".publicAccount #certAttribute0")[0])){
			 $(".publicAccount #certAttribute0Label").text("请选择符合要求的开户许可证照片");
			 $(".publicAccount #certAttribute0").focus();
			 return false;
		 } 
	}
	
	
	 $(".publicAccount #certAttribute0Label").text("");
	hideDiv();
	$(".legalPerson").css("display","block");
	
});


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
$(".legalPerson #representativeMobileLabel").text("");
  $(".legalPerson #representativeCertNoLabel").text("");
  
  
  if(kong.test($(".idcard1Name").text())){
	  
	  certAttribute1IsChage = "true";
	//法人身份证正面照片
	  if(!checkAttach($(".legalPerson #certAttribute1")[0])){
	 	 $(".legalPerson #certAttribute1Label").text("请选择符合要求的身份证正面照片");
	 	 $(".legalPerson #certAttribute1").focus();
	 	 return false;
	  }
	}

 $(".legalPerson #certAttribute1Label").text("");
 
 if(kong.test($(".idcard2Name").text())){
	 certAttribute2IsChage = "true";
	//法人身份证背面照片
	 if(!checkAttach($(".legalPerson #certAttribute2")[0])){
		 $(".legalPerson #certAttribute2Label").text("请选择符合要求的身份证背面照片");
		 $(".legalPerson #certAttribute2").focus();
		 return false;
	 }
 }

 $(".legalPerson #certAttribute2Label").text("");

	hideDiv();
	$(".rate").css("display","block");
});

$(".ratePreButton").click(function(){
	hideDiv();
	$(".legalPerson").css("display","block");
});
var fee = '';
$(".affirmPre").click(function(){
	hideDiv();
	$(".rate").css("display","block");
});


/******** 费率下一步 ********/
$(".rateNextButton").click(function(){
	$(".fixedTable").hide();
	$(".packageTable").hide();
	hideLabel();
	// 费率
	var value = $('.rate #ruleB').val();
	if(kong.test(value)) {
		$(".rate #ruleLabel").text("请选择费率");
		return false;
	}
	
	var val= $(".rate input[name='rule']:checked").val();
	if(typeof(val)== "undefined"){
		$(".rate #ruleLabel").text("请选择费率");
		return false;
	}
	fee = val; 
	hideDiv();
	$(".affirm").css("display","block");
	affirmInfo();
	
	//确定信息页面展示所选费率
	if(value=="Package"){
		$(".packageTable").show();
		var ht= $("input[name='rule']:checked").parent().parent();
		var htm1 = ht.html();
		var htm2 = ht.next().html();
		var htm3 = ht.next().next().html();
		var htm4 = ht.next().next().next().html();
		
		$(".htm1").html(htm1);
		$(".htm2").html(htm2);
		$(".htm3").html(htm3);
		$(".htm4").html(htm4);
		
	   $(".htm1 input[name='rule']").parent().remove();	
	}
	
	if(value=="Fixed"){
		$(".fixedTable").show();
		var ht= $("input[name='rule']:checked").parent().parent();
		
		$(".htmFixed").html(ht.html());
		$(".htmFixed input[name='rule']").parent().remove();	
	}
});

/* 提交数据 */
$(".submitbutton").click(function(){
	var auditName = $("#auditName").val();
	
	if(kong.test(auditName)) {
		$("#auditNameLabel").text("请选择审核人员");
		return false;
	}
	
 	var email =	$(".email input[name='email']").val();
 	var custName = $("input[name='custName']").val();
 	var businessLicense = $("input[name='businessLicense']").val();
 	var businessTerm =$("input[name='businessTerm']").val();
 	var custAdd = $("input[name='custAdd']").val();
 	var contactPhone = $("input[name='contactPhone']").val();
 	var orgInstCode = $("input[name='orgInstCode']").val();
 	var representativeName = $("input[name='representativeName']").val();
	var representativeCertNo = $("input[name='representativeCertNo']").val();
	var representativeMobile = $("input[name='representativeMobile']").val();
	var openName = $("input[name='custName']").val();
	var bankCard = $("input[name='bankCard']").val();
	var bankCode =$(".publicAccount #bank").val();
	var custId = $("#custId").val();
	var feeCode =  fee;
	$.ajaxFileUpload({  
        url : window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.UPDATEFILEUPLOAD%>?custId='+custId,
        secureuri : false,
        fileElementId : ['businessPhoto','certAttribute0','certAttribute1','certAttribute2'],
        dataType : 'json',  
        success : function(data, status) {
        	if(data.result=='SUCCESS'){
        		$.post(window.Constants.ContextPath +'<%=WorkSpacePath.BASE+WorkSpacePath.UPDATEREGIST%>',{
        			'custId': $("#custId").val(),
    	 			'email' : email,
    	 			'custName' : custName,
    	 			'businessLicense' : businessLicense,
    	 			'businessRegAddr' : businessRegAddr,
    	 			'businessTerm' : businessTerm,
    	 			'custAdd' : custAdd,
    	 			'contactPhone' : contactPhone,
    	 			'orgInstCode' : orgInstCode,
    	 			'representativeName' : representativeName,
    	 			'representativeCertNo' : representativeCertNo,
    	 			'representativeMobile' : representativeMobile,
    	 			'compMainAcct' : bankCard,
    	 			'compAcctBank' :bankCode,
    	 			'feeCode':feeCode,
    	 			'businessType':data.businessType,
    	 			'certAttributeType1':data.certAttributeType1,
    	 			'idCardType_1':data.idCardType_1,
    	 			'idCardType_2':data.idCardType_2,
    	 			'taskId':$("#taskId").val(),
    	 			'businessImageIsChange' : businessImageIsChange,
    	 			'certAttribute0IsChage' : certAttribute0IsChage,
    	 			'certAttribute1IsChage' : certAttribute1IsChage,
    	 			'certAttribute2IsChage' : certAttribute2IsChage,
    	 			'auditName' : auditName
    	 			
    	 	},function(data){
    	 		$.unblockUI();
    			if(data.result=="SUCCESS"){
    				$.gyzbadmin.alertSuccess("注册申请已提交,请等待审批！",null,function(){
    					window.location.href = window.Constants.ContextPath + '<%=MerchantPath.BASE+MerchantPath.LIST %>';
    				});
    			}else{
    				$.gyzbadmin.alertFailure("注册申请失败！"+data.message);
    			}
    	 	},'json');
        		
        	}else{
        		$.gyzbadmin.alertFailure("扫描件上传失败！"+data.message);
        		$("#businessPhotoLab").text("扫描件上传失败,请选择合适的类型");
        	}
        	
        }
		 }); 
});
 	
/* 清空提示信息*/
function hideLabel(){
	$("#emailLab").text("");
	$("#custNameLab").text("");
	$("#businessLicenseLab").text("");
	$("#businessRegAddrLab").text("");
	$("#businessTermLab").text("");
	$("#custAddLab").text("");
	$("#contactPhoneLab").text("");
	$("#businessPhotoLab").text("");
	$("#orgInstCodeLab").text("");
	$(".rate #ruleLabel").text("");
}

/* 隐藏div */
function hideDiv(){
	$(".business").css("display","none"); 
	$(".email").css("display","none");
	$(".publicAccount").css("display","none");
	$(".legalPerson").css("display","none");
	$(".rate").css("display","none");
	$(".affirm").css("display","none");
	
}

/* 赋值  */
function affirmInfo(){
		var custName = $("input[name='custName']").val();
		var businessLicense = $("input[name='businessLicense']").val();
		var businessTerm =$("input[name='businessTerm']").val();
		var custAdd = $("input[name='custAdd']").val();
		var contactPhone = $("input[name='contactPhone']").val();
		var openName = $("input[name='custName']").val();
		var bankCard = $("input[name='bankCard']").val();
		var bankName =$(".publicAccount #bank").find("option:selected").text();
		var representativeName = $("input[name='representativeName']").val();
		var representativeCertNo = $("input[name='representativeCertNo']").val();
		var representativeMobile = $("input[name='representativeMobile']").val();
		
		//营业执照赋值
		var businessPhoto ;
		if(kong.test($(".businessImageName").text())){
			businessPhoto = $("input[name='businessPhoto']").val();
		}else{
			businessPhoto = $(".businessImageName").text();
		}
		
		var certAttribute0;
		if(kong.test($(".certAttributeName").text())){
			certAttribute0 = $("input[name='certAttribute0']").val();
		}else{
			certAttribute0 = $(".certAttributeName").text();
		}
		
		var certAttribute1 ;
		if(kong.test($(".idcard1Name").text())){
			certAttribute1 = $("input[name='certAttribute1']").val();
		}else{
			certAttribute1 = $(".idcard1Name").text();
		}
		
		var certAttribute2 ;
		if(kong.test($(".idcard2Name").text())){
			certAttribute2 = $("input[name='certAttribute2']").val();
		}else{
			certAttribute2 = $(".idcard2Name").text();
		}
		
		var feeName =$('.rate #rule').find("option:selected").text();
		$(".affirm .custName").text(custName);
		$(".affirm .businessLicense").text(businessLicense);
		$(".affirm .businessRegAddr").text(businessRegAddr);
		$(".affirm .businessTerm").text(businessTerm);
		$(".affirm .custAdd").text(custAdd);
		$(".affirm .contactPhone").text(contactPhone);
		$(".affirm .openName").text(openName);
		$(".affirm .bankCard").text(bankCard);
		$(".affirm .bank").text(bankName);
		$(".affirm .representativeName").text(representativeName);
		$(".affirm .representativeCertNo").text(representativeCertNo);
		$(".affirm .representativeMobile").text(representativeMobile);
		$(".affirm .bankCardLast").text(bankCard);
		$(".affirm .businessPhoto").text(businessPhoto);
		$(".affirm .certAttribute0").text(certAttribute0);
		$(".affirm .certAttribute1").text(certAttribute1);
		$(".affirm .certAttribute2").text(certAttribute2);
	}

});



function load(){
	/* var proCity =$("#provinceCity").val();
	var pro = proCity.split(",")[0];
	$("#province").val(pro); */
	
	/* // 初始化城市
	$.getJSON(window.Constants.ContextPath +'/basemanager/city/getCitys', { 
			provinceId : pro
		}, function(data) { 
			// 如果有子选项，则创建子下拉框 
			if(data != null){ 
				//清空子下拉框内容 
				$("#city").empty();  
				// 遍历json串，填充子下拉框 
				$.each(data.city, function(i, item) { 
					var isSelect = proCity.split(",")[1] == item.cityId ? 'selected' : '';   
					$("#city").append( 
					"<option class='cityOption' value='"+item.cityId+"' "+isSelect+">" + item.cityName 
					+ "</option>"); 
					
				}); 
			} 
		});  */
	
	var bankCode = $("#compAcctBank").val();
	$("#bank").val(bankCode);
	
	var feeCode = $("#feeCode").val();
	
	var feeType = $("#feeType").val();
	$("#ruleB").val(feeType);
	
	/* $("input[name='rule'][value='"+feeCode+"']").attr("checked","checked"); */
	$("input[name='rule'][value='"+feeCode+"']").prop('checked', true);
	if(feeType=="Fixed"){
		
		$(".FixedTr").show();
	}
	if(feeType=="Package"){
		
		$(".PackageTr").show();
	}
	
	
}
</script>
<body onload="load()">
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
						<input type="hidden" value="${info.custId }" id="custId">
						<input type="hidden" value="${info.compAcctBank }" id="compAcctBank">
						<input type="hidden" value="${info.feeCode }" id="feeCode">
						<input type="hidden" value="${feeType }" id="feeType">
						<table class="search-table">
							<tr>
								<td class="td-left">邮箱地址：</td>
								<td class="td-right"> 
									<span class="input-icon">
											<input type="text" id="email" name="email" maxlength="55" value="${info.email}">
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
					<div class="business">
						<div class="list-table-header">企业信息</div>
						<table class="search-table">
								<tr>
									<td class="td-left" >企业名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="custName" name="custName" value="${info.custName}" maxlength="100"  placeholder="企业名称">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="custNameLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业执照注册号：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="businessLicense" value="${info.businessLicense}" name="businessLicense" placeholder="营业执照注册号">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="businessLicenseLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业执照所在地：</td>
									<td class="td-right">
									<input type="text" id="businessRegAddr" name="businessRegAddr" value="${info.businessRegAddr }"  placeholder="营业执照注册所在地" style="width:90%">
									   <%--  <div id="base">
										<label> 
											<input type="hidden" id="provinceCity" value="${info.businessRegAddr }">
											<select id="province" name="province" onchange="refreshCity()" > 
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
									<td class="td-left">企业地址：</td>
									<td class="td-right">
										<input type="text" id="custAdd" name="custAdd" value="${info.custAdd}"  maxlength="100" placeholder="企业地址" style="width:80%">
										<label class="label-tips" id="custAddLab"></label>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业期限：</td>
									<td class="td-right">
										<input type="text" id="businessTerm" name="businessTerm" value="${info.businessTerm}" readonly="readonly" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" style="width:30%;"/>
										 <label class="label-tips" id="businessTermLab"></label>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">联系电话：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="contactPhone" value="${info.contactPhone}" name="contactPhone" placeholder="联系电话">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="contactPhoneLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">组织机构码：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="orgInstCode" value="${info.orgInstCode}" name="orgInstCode" placeholder="组织机构码">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="orgInstCodeLab"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">营业执照扫描件：</td>
									<td class="td-right">
										<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal">
											<label id="businessPhotoDiv" class="uploadImage">
												<img  id="businessPhotoImage" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${businessImage}" style="width:100%;height:100%; " >
											</label>
											<input type="hidden" id="businessPhotoPath" value="${businessImage}">
										</a>
										<div style="float:left;margin-top:75" >
											<span class="businessImageName">文件名称：${businessImageName}</span>
											<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />  <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div> 
										<label class="label-tips" id="businessPhotoLab" style="float:left;margin-top:88" ></label>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center" class="combine">
										<input type="button" value="上一步" class="businessPreButton">
										<input type="button" value="下一步" class="businessNextButton">
									</td>
								</tr>
							</table>
					</div>
					
					<!-- 对公账户  -->
					<div class="publicAccount">
						<div class="list-table-header">账户信息</div>
						<table class="search-table">
								<tr>
									<td class="td-left" style="width:50%">银行开户名</td>
									<td class="td-right" style="width:50%"> 
										<span class="input-icon">
											<label id="custNameLabel">${info.custName }</label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >开户银行：</td>
									<td class="td-right" > 
										  <sevenpay:selectBankTag id="bank" name ="bank"  banks="${banklist}"  clasS="width-20"/>
										  <label id="bankLabel" class="label-tips"></label>
									</td>
								</tr>
								<tr>
									<td class="td-left" >公司账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="bankCard" value="${info.compMainAcct}" name="bankCard" placeholder="公司账号">
											<i class="icon-leaf blue"></i>
											<label id="bankCardLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >开户许可证：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute0Click"  data-target="#previewImageModal">
											<label id="certAttribute0Div" class="uploadImage">  
											        <img  id="certAttribute0Image" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${certAttribute}" style="width:100%;height:100%;"/>
											</label>
											<input type="hidden" id="certAttribute0Path" value="${certAttribute}">
										</a>
										<div style="float:left;margin-top:75" >
											<span class="certAttributeName">文件名称：${ certAttributeName}</span>
											<input type="file" name="certAttribute0" id="certAttribute0" onchange="showCertAttribute0Image(this)"/><p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
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
					<div class="legalPerson">
						<div class="list-table-header">法人信息</div>
						<table class="search-table">
								<tr>
									<td class="td-left" style="width:50%">法人真实姓名：</td>
									<td class="td-right" style="width:50%"> 
										<span class="input-icon">
											<input type="text" id="representativeName" value="${info.representativeName}" name="representativeName" placeholder="法人真实姓名" maxlength="50">
											<i class="icon-leaf blue"></i>
											<label id="representativeNameLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人身份证号码：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="representativeCertNo" value="${info.representativeCertNo}" id="representativeCertNo" placeholder="法人身份证号码">
											<i class="icon-leaf blue"></i>
											<label id="representativeCertNoLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人手机号码：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text" id="representativeMobile" value="${info.representativeMobile}"  name="representativeMobile" placeholder="法人手机号码">
											<i class="icon-leaf blue"></i>
											<label id="representativeMobileLabel" class="label-tips"></label>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >身份证图片正面：</td>
									<td class="td-right" >
										<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
										<label id="certAttribute1Div" class="uploadImage">  
										        <img  id="certAttribute1Image" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard1}" style="width:100%;height:100%;"/>
										</label>
										<input type="hidden" id="certAttribute1Path" value="${idcard1}">
										</a>
										<div style="float:left;margin-top:75" >
										<span class="idcard1Name">文件名称：${ idcard1Name}</span>
										<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>
										<label class="label-tips" id="certAttribute1Label" style="float:left;margin-top:88"></label>
									</td>
								</tr>
								<tr>
									<td class="td-left" >身份证图片背面：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal" >
											<label id="certAttribute2Div" class="uploadImage">  
											        <img  id="certAttribute2Image" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard2}" style="width:100%;height:100%;"/>
											</label>
											<input type="hidden" id="certAttribute2Path" value="${idcard2}">
										</a>
										<div style="float:left;margin-top:75" >
										<span class="idcard2Name">文件名称：${ idcard2Name}</span>
										<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
										</div>
										
										<label class="label-tips" id="certAttribute2Label" style="float:left;margin-top:88"></label>
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
					<div class="rate">
					<div class="list-table-header">费率信息</div>
						<table class="search-table">
							<tr>
								<td class="td-left" style="width:50%">请选择费率方式：</td>
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
								<sevenpay:selectRulePackageTag name="rule" style="width:40%;font-size:10px;text-align:center;"/>
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
					</div>
					
					
					<!-- 确认信息  -->
					<div class="affirm" style="width:1100px;">
						<div class="list-table-header">请确认你提交的信息  <a href="#" class="affirmPre" style="color:yellow;">返回修改</a> </div>
						<table id="sample-table-2" class="list-table" >
						    <tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">企业信息：</td>
							</tr>
							<tr>
								<td class="td-left" width="18%" >公司名称：</td>
								<td class="td-right" width="32%" > 
									<span class="custName"></span>
								</td>
						
								<td class="td-left" width="18%" >营业执照注册号：</td>
								<td class="td-right" width="32%"> 
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
								<td class="td-left" >营业执照扫描件：</td>
								<td class="td-right" colspan="3" > 
									<a data-toggle='modal' class="tooltip-success businessPhotoImage2Click"  data-target="#previewImageModal" >
										<label id="businessPhotoDiv2" class="uploadImage">
											<img  id="businessPhotoImage2" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${businessImage}" style="width:100%;height:100%; " >
										</label>
									</a>
									<span class="businessPhoto"></span>
								</td>
							</tr>
							<tr>
							<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">银行信息：</td>
							</tr>
							<tr>
								<td class="td-left" >银行开户名：</td>
								<td class="td-right" > 
									<span class="openName"></span>
								</td>
							
								<td class="td-left" >开户银行：</td>
								<td class="td-right" > 
									<span class="bank"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >银行账号：</td>
								<td class="td-right"  > 
									<span class="bankCard"></span>
								</td>
								<td class="td-left" >开户证件：</td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success certAttribute0Image2Click"  data-target="#previewImageModal" >
									<label id="certAttribute0Div2" class="uploadImage">  
											        <img  id="certAttribute0Image2" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${certAttribute}" style="width:100%;height:100%;"/>
									</label> 
									</a>
									<span class="certAttribute0"></span>
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
										        <img  id="certAttribute1Image2" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard1}" style="width:100%;height:100%;"/>
									</label>
									</a>
									<span class="certAttribute1"></span>
								</td>
								<td class="td-left" >身份证背面：</td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success certAttribute2Image2Click"  data-target="#previewImageModal" >
									<label id="certAttribute2Div2" class="uploadImage">  
											        <img  id="certAttribute2Image2" src="<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path=${idcard2}" style="width:100%;height:100%;"/>
									</label> 
									</a>
									<span class="certAttribute2"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >法人手机号码：</td>
								<td class="td-right" colspan="3" > 
									<span class="representativeMobile"></span>
								</td>
							</tr>	
							
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">费率方式：</td></tr>
							<tr>
								<td class="td-left" >费率方式：</td>
								<td class="td-right" colspan="3"> 
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
							</tr>
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">审核人员：</td>
							</tr>
							<tr>
								<td class="td-left" >审核人员：</td>
								<td class="td-right" colspan="3"> 
									<gyzbadmin:selectUserTag id="auditName" name="user"  users="${userlist}" clasS="width-20"/>
									<label id="auditNameLabel" class="label-tips"></label>
								</td>
							<tr>
								<td colspan="4" align="center">
									<input type="button" value="提交"  class="submitbutton">
								</td>
							</tr>
						</table>
					</div>
					
					
					<!-- 图片预览 -->
					
					<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-dialog" style="width:60%;height:80%;">
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
	
	

<script type="text/javascript">
/*图片预览*/
function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImage");  
	 
	 var divObj2 = document.getElementById("businessPhotoDiv2");  
	 var imageObj2 = document.getElementById("businessPhotoImage2");
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 
	 $(".businessImageName").text("");
	 
	 return result2&&result1;
}

function showCertAttribute0Image(obj){  
	 var divObj = document.getElementById("certAttribute0Div");  
	 var imageObj = document.getElementById("certAttribute0Image");
	 var divObj2 = document.getElementById("certAttribute0Div2");  
	 var imageObj2 = document.getElementById("certAttribute0Image2");
	 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 
	 $(".certAttributeName").text("");
	 return result2&&result1;  
}

function showCertAttribute1Image(obj){  
	 var divObj = document.getElementById("certAttribute1Div");  
	 var imageObj = document.getElementById("certAttribute1Image");
	 var divObj2 = document.getElementById("certAttribute1Div2");  
	 var imageObj2 = document.getElementById("certAttribute1Image2");
	 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 
	 $(".idcard1Name").text("");
	 return result2&&result1;  
}

function showCertAttribute2Image(obj){  
	 var divObj = document.getElementById("certAttribute2Div");  
	 var imageObj = document.getElementById("certAttribute2Image"); 
	 var divObj2 = document.getElementById("certAttribute2Div2");  
	 var imageObj2 = document.getElementById("certAttribute2Image2"); 
	 var result2 = previewImage(divObj2,imageObj2,obj);
	 var result1 = previewImage(divObj,imageObj,obj);
	 $(".idcard2Name").text("");
	 return result2&&result1;  
}


	$('.businessPhotoClick').click(function(){
		var obj = document.getElementById("businessPhoto"); 
		var path = $("#businessPhotoPath").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("businessPhoto"); 
			return previewImage(divObj,imageObj,obj);
		}
		
	
	});  
	 
	 $('.businessPhotoImage2Click').click(function(){
		 	var obj = document.getElementById("businessPhoto"); 
			var path = $("#businessPhotoPath").val();
			var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
			if(obj.value == ''){
				$('#showImage').attr("src",src);
			}else{
				var divObj = document.getElementById("showImageDiv");
				var imageObj = document.getElementById("showImage");
				var obj = document.getElementById("businessPhoto"); 
				return previewImage(divObj,imageObj,obj); 
			}
			
		});   
	 
	$('.certAttribute0Click').click(function(){
		var obj = document.getElementById("certAttribute0"); 
		var path = $("#certAttribute0Path").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("certAttribute0");
			return previewImage(divObj,imageObj,obj); 
		}
		
	});

	$('.certAttribute0Image2Click').click(function(){
		var obj = document.getElementById("certAttribute0"); 
		var path = $("#certAttribute0Path").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("certAttribute0");
			return previewImage(divObj,imageObj,obj); 
		}
	}); 

	$('.certAttribute1Click').click(function(){
		var obj = document.getElementById("certAttribute1"); 
		var path = $("#certAttribute1Path").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("certAttribute1");
			return previewImage(divObj,imageObj,obj); 
		}
		
	});
	$('.certAttribute1Image2Click').click(function(){
		var obj = document.getElementById("certAttribute1"); 
		var path = $("#certAttribute1Path").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("certAttribute1");
			return previewImage(divObj,imageObj,obj); 
		}
	}); 

	$('.certAttribute2Click').click(function(){
		var obj = document.getElementById("certAttribute2"); 
		var path = $("#certAttribute2Path").val();
		var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
		if(obj.value == ''){
			$('#showImage').attr("src",src);
		}else{
			var divObj = document.getElementById("showImageDiv");
			var imageObj = document.getElementById("showImage");
			var obj = document.getElementById("certAttribute2");
			return previewImage(divObj,imageObj,obj); 
		}
	});
	 $('.certAttribute2Image2Click').click(function(){
		 	var obj = document.getElementById("certAttribute2"); 
			var path = $("#certAttribute2Path").val();
			var src = "<%=request.getContextPath()+WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path="+path;
			if(obj.value == ''){
				$('#showImage').attr("src",src);
			}else{
				var divObj = document.getElementById("showImageDiv");
				var imageObj = document.getElementById("showImage");
				var obj = document.getElementById("certAttribute2");
				return previewImage(divObj,imageObj,obj); 
			}
	}); 

 

</script>