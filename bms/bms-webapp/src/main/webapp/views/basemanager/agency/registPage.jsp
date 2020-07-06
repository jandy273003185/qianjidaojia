<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>

<html>
<head>
	<meta charset="utf-8" />
	<title>代理商注册</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	.uploadImage{ 
			float:left; 
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
	}
</style>
</head>
<script type="text/javascript">
	
</script>
<body>
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{
				ace.settings.check('main-container' , 'fixed')
			}catch(e){}
		</script>
		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				<!-- 主内容 -->
				<div class="page-content">
					<div class="tinymerchantinfo">
						<input type="hidden" id="businessPhototemp"/>
						<input type="hidden" id="certAttribute1temp"/>
						<input type="hidden" id="certAttribute2temp" />
						<div class="list-table-header">代理商注册</div>
						<table class="search-table">
							<tr>
								<td class="td-left">代理商类型<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="custType" style="width:100;" id="custType">
											<option value="0">个人</option>
											<option value="1">企业</option>
										</select>		
										<label class="label-tips" id="custTypeLab"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left">邮箱地址<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
											<input type="text" id="email" name="email" maxlength="55" placeholder="邮箱地址">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="emailLab"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left">代理商名称<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="custName" name="custName" placeholder="商户名称" maxlength="30">
										<i class="icon-leaf blue"></i>
										<label id="custNameLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >手机号码<span style="color:red">*</span></td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" id="representativeMobile"  name="representativeMobile" placeholder="手机号码">
										<i class="icon-leaf blue"></i>
										<label id="representativeMobileLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >联系人<span style="color:red">*</span></td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" id="agentName" name="agentName" placeholder="联系人">
										<i class="icon-leaf blue"></i>
										<label id="agentNameLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr name="ent">
								<td class="td-left">营业执照注册号或统一社会信用代码<span style="color:red">*</span></td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号或统一社会信用代码">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="businessLicenseLab"></label>
									</span>
								</td>
							</tr>
							<tr name="ent">
								<td class="td-left">营业期限<span style="color:red">*</span></td>
								<td class="td-right">
								<span class="input-icon">
									起始：<input type="text" name="businessTermStart"   id="businessTermStart" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermEnd\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									
									终止：<input type="radio" checked="checked" name="end" value="sel"/><input type="text" name="businessTermEnd"   id="businessTermEnd" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermStart\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> <input type="radio" name="end" value="forever">长期
									<label id="businessTermStartLabel" class="label-tips"></label>
								</td>
								</span>
								
								
							</tr>
							<tr name="ent">
								<td class="td-left">营业执照扫描件<span style="color:red">*</span></td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal">
										<label id="businessPhotoDiv" class="uploadImage">
											<img  id="businessPhotoImage" style="width:100%;height:100%; display: none" >
										</label>
									</a>
									<div style="float:left;margin-top:75" >
										<input type="file" name="businessPhoto" id="businessPhoto"  onchange="showBusinessPhotoImage(this)" /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> 
									<label class="label-tips" id="businessPhotoLabel" style="float:left;margin-top:88" ></label>
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
										<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									<label class="label-tips" id="certAttribute1Label" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片背面<span style="color:red">*</span></td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal" >
										<label id="certAttribute2Div2" class="uploadImage">  
										        <img  id="certAttribute2Image" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div style="float:left;margin-top:75" >
									<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
									<label class="label-tips" id="certAttribute2Label" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left" >证件类型<span style="color:red">*</span></td>
								<td class="td-right" > 
									<span class="input-icon">
										<select  id="certifyType"  name="certifyType">
											<option value ="10">--请选择--</option>
											<option value ="00">大陆居民身份证</option>
											<option value ="01">香港居民身份证</option>
											<option value ="02">澳门居民身份证</option>
											<option value ="03">台湾居民身份证</option>
										</select>
										<label id="certifyTypeLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							
							<tr>
								<td class="td-left" >身份证号码<span style="color:red">*</span></td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" id="certifyNo" name="certifyNo" placeholder="身份证号码">
										<i class="icon-leaf blue"></i>
										<label id="certifyNoLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" class="combine">
									<input type="button" value="注　　册" id="registerTinyMerchant" class="registerTinyMerchant">
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

/******************************jQuery*******************************/

$(function(){
	//$(".tinymerchantinfo #fcontactunitNumber").prop("disabled",true);
	$("tr[name='ent']").hide();
	$("input[value='forever']").click(function(){
		$(this).prev().val('').attr("disabled",true);
	});
	$("input[value='sel']").click(function(){
		$(this).next().attr("disabled",false);
	});
	
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
						/* alert(_this.attr('id'));
						alert($('#'+_this.attr('id')+'temp').val()); */
						
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
			    	       	  				$("#agentName").val(json.cardName);
			    	       	  				$("#certifyNo").val(json.cardId);
			    	       	  			}else if(_this.attr('id')=="businessPhoto"){//营业执照
			    	       	  				$("#businessLicense").val(json.businessLicense);
			    	       	  				$("#businessTermStart").val(json.businessTermStart);
			    	       	  				$("#custName").val(json.companyName);
			    	       	  				if("长期"==json.businessTermEnd){
			    	       	  					$("input[value='forever']").click();
			    	       	  				}else{
			    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
			    	       	  				}
			    	       	  				//$("#merchantAdress").val(json.legalAddress);
			    	       	  				
			    	       	  			} 
			    	   				}
			    	   			}
			    	   		});
			             
			             
					}
				});
			});
});

/** 提交 */
$("#registerTinyMerchant").click(function(){
	// 校验邮箱地址
	var flag;
	flag = Register.validateEmail($("#email").val().trim(),$("#emailLab"));
	// 组装提交表单数据
	var email = 				$("#email").val().trim();
	var custName = 				$("#custName").val().trim();
	var representativeMobile = 	$("#representativeMobile").val().trim();
	//var fcontactunitNumber = 	$("#fcontactunitNumber").val().trim();
	var businessLicense = 		$("#businessLicense").val().trim();
	var certifyNo = 			$("#certifyNo").val().trim();
	var certifyType = 			$("#certifyType").val();
	var custType = 				$("#custType").val().trim();
	var agentName = 			$("#agentName").val().trim();
	var businessTermStart = 	$("#businessTermStart").val();
	var businessTermEnd = "forever";
	
	
	if(!flag){
		//$("#email").focus();
		return false;
	}else{
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATEEMAIL%>',
	        data:{email:email},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$emailLab.text("您要注册的邮箱已经被占用，请重新输入");
	        		$("#email").val("");
	        		return false;
				}
			}
		});
	}
	// 校验商户名称
	flag = Register.validateCustName($("#custName").val().trim(),$("#custNameLabel"));
	if(!flag){return false;}
	// 校验真实姓名
	flag = Register.validateAgentName($("#agentName").val().trim(),$("#agentNameLabel"));
	if(!flag){return false;}
	// 校验手机号码
	flag = Register.validatePhone($("#representativeMobile").val().trim(),$("#representativeMobileLabel"));
	if(!flag){return false;}else{
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATEMOBILE%>',
	        data:{mobile:representativeMobile},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$representativeMobileLabel.text("您要注册的手机号已经被占用，请重新输入");
	        		$("#representativeMobile").val("");
	        		return false;
				}
			}
		});
	}
	if(custType=='1'){
		// 校验营业时间
		if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){return false;}
		if($("input:radio[name='end']:checked").val()=='sel'){
			if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){return false;}
			businessTermEnd = $("#businessTermEnd").val();
		}
		//营业执照号
		var flag = Register.validateBusinessLicense($("#businessLicense").val().trim(),$("#businessLicenseLab"));
		
		if(!flag){return false;}else{
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATELICENSE%>',
		        data:{License:businessLicense},
		        success:function(data){
		        	if(data.result=="FAIL"){
		        		$businessLicenseLab.text("该营业执照已经被注册，请重新输入");
		        		$("#businessLicense").val("");
		        		return false;
					}
				}
			});
		}
	}
	// 校验证件类型
	flag = Register.validateCertifyType($("select[name='certifyType']").val(),$("#certifyTypeLabel"),$("#certifyNo").val());
	if(!flag){return false;}
	// 校验身份证号码
	flag = Register.validateCertifyNo($("#certifyNo").val().trim(),$("#certifyNoLabel"),$("select[name='certifyType']").val());
	<%-- if(!flag){return false;}else{
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATECARDID%>',
	        data:{cardId:certifyNo},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$certifyNoLabel.text("该身份证已经被注册，请重新输入");
	        		$("#certifyNo").val("");
	        		return false;
				}
			}
		});
	} --%>
	
	
	if(!checkAttach($("#certAttribute1")[0])){
		$.gyzbadmin.alertFailure("必须提交身份证正反面扫描件");
		return false;
	}
	if(!checkAttach($("#certAttribute2")[0])){
		$.gyzbadmin.alertFailure("必须提交身份证正反面扫描件");
		return false;
	}
	// 提交前校验，把错误扼杀在摇篮中
	/* var flag = Register.allErrorMsgLabelValue();
	if(flag){
		return false;
	} */
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	
	var fileElementId = ['certAttribute1','certAttribute2'];
	if(custType=='1'){
		fileElementId.push('businessPhoto');
		if(!checkAttach($("#businessPhoto")[0])){
			$.gyzbadmin.alertFailure("必须提交营业执照扫描件");
			return false;
		}
	}
	
	$.blockUI();
	$.ajax({
		type : "POST",
		url : window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.FILEUPLOAD%>',
		data :{
			businessPhoto : $('#businessPhototemp').val(),
			certAttribute1 : $('#certAttribute1temp').val(),
			certAttribute2 : $('#certAttribute2temp').val()
		},
		dataType : "json",
		success : function(data) {
			if(data.result=='SUCCESS'){
				$.post(window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.REGISTER%>',{
					"custId":data.custId,							// 回传custId
        			"email":email, 									// 账户/电子邮件
        			"custName":custName, 							// 客户姓名
        			"representativeMobile":representativeMobile, 	// 手机号码
        			//"fcontactunitNumber":fcontactunitNumber, 		// 往来单位编号
        			"businessLicense":businessLicense, 				// 营业执照注册号
        			"certifyType":certifyType,						// 证件类型
        			"certifyNo":certifyNo, 							// 身份证号
        			"custType":custType,							// 客户类型
        			"agentName":agentName,						    // 真实姓名
        			"businessTermStart":businessTermStart,
        			"businessTermEnd" : businessTermEnd
        		},function(data){
        			if(data.result=="SUCCESS"){
    					$.gyzbadmin.alertSuccess("注册申请成功",null,function(){
   						window.location.href = window.Constants.ContextPath + '<%=AgencyPath.BASE + AgencyPath.LIST %>';
    					});
    				} else {
    					$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
    				}
        		},'json')
        	}else{
        		$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
        	}
		}
	});
	
	<%-- $.ajaxFileUpload({  
        url : window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.FILEUPLOAD%>',
        secureuri : false,  // 是否启用安全提交，默认为false
        /* 需要上传的文件域的ID，即<input type="file">的ID */ 
        fileElementId : fileElementId, 
        dataType : 'json', 
        success : function(data, status) {
        	$.unblockUI();
        	if(data.result=='SUCCESS'){
        		$.post(window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.REGISTER%>',{
        			"custId":data.custId,							// 回传custId
        			"email":email, 									// 账户/电子邮件
        			"custName":custName, 							// 客户姓名
        			"representativeMobile":representativeMobile, 	// 手机号码
        			//"fcontactunitNumber":fcontactunitNumber, 		// 往来单位编号
        			"businessLicense":businessLicense, 				// 营业执照注册号
        			"certifyType":certifyType,						// 证件类型
        			"certifyNo":certifyNo, 							// 身份证号
        			"custType":custType,							// 客户类型
        			"agentName":agentName,						    // 真实姓名
        			"businessTermStart":businessTermStart,
        			"businessTermEnd" : businessTermEnd
        		},function(data){
    				if(data.result=="SUCCESS"){
    					$.gyzbadmin.alertSuccess("注册申请成功",null,function(){
   						window.location.href = window.Constants.ContextPath + '<%=AgencyPath.BASE + AgencyPath.LIST %>';
    					});
    				} else {
    					$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
    				}
        		},'json')
        	} else if (data.result=='fail'){
        		$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
        	} else {
        		$.gyzbadmin.alertFailure("扫描件上传失败,请选择合适的类型");
        	}
        }
	}) --%>
});

/************************验证相关**********************/

/* 邮箱验证 */
$("#email").focus(function(){
	$("#emailLab").text("");
}).blur(function(){
	var $email = $(this).val().trim();
	var $emailLab = $("#emailLab");
	var flag = Register.validateEmail($email,$emailLab);
	if (flag) {
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATEEMAIL%>',
	        data:{email:$email},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$emailLab.text("您要注册的邮箱已经被占用，请重新输入");
	        		$("#email").val("");
				}
			}
		});
	}
});

/** 商户名称验证 */
$("#custName").focus(function(){
	$("#custNameLabel").text("");
}).blur(function(){
	var $custName = $(this).val().trim();
	var $custNameLabel = $("#custNameLabel");
	Register.validateCustName($custName,$custNameLabel);
});

/** 真实姓名验证 */
$("#agentName").focus(function(){
	$("#agentNameLabel").text("");
}).blur(function(){
	var $agentName = $(this).val().trim();
	var $agentNameLabel = $("#agentNameLabel");
	Register.validateCustName($agentName,$agentNameLabel);
});

/** 手机号码验证--手机号码唯一 */
$("#representativeMobile").focus(function(){
	$("#representativeMobileLabel").text("");
}).blur(function(){
	var $representativeMobile = $(this).val().trim();
	var $representativeMobileLabel = $("#representativeMobileLabel");
	var flag = Register.validatePhone($representativeMobile,$representativeMobileLabel);
	
	if (flag) {
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATEMOBILE%>',
	        data:{mobile:$representativeMobile},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$representativeMobileLabel.text("您要注册的手机号已经被占用，请重新输入");
	        		$("#representativeMobile").val("");
				}
			}
		});
	}
});

/** 证件类型非空验证 */
$("#certifyType").focus(function(){
	$("#certifyTypeLabel").text("");
}).blur(function(){
	var $certifyType = $("select[name='certifyType']").val();
	var $certifyTypeLabel = $("#certifyTypeLabel");
	var $certifyNo = $("#certifyNo").val().trim();
	Register.validateCertifyType($certifyType,$certifyTypeLabel,$certifyNo);
});


/** 身份证校验 */
$("#certifyNo").focus(function(){
	$("#certifyNoLabel").text("");
}).blur(function(){
	var $certifyNoLabel = $("#certifyNoLabel");
	var $certifyNo = $(this).val().trim();
	var $roleId = $("#custType").val().trim();
	
	var $certifyType = $("select[name='certifyType']").val();
	var flag = Register.validateCertifyNo($certifyNo,$certifyNoLabel,$certifyType);
	if (flag) {
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATECARDID%>',
	        data:{cardId:$certifyNo,roleId:$roleId},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$certifyNoLabel.text("该身份证已经被注册，请重新输入");
	        		$("#certifyNo").val("");
				}
			}
		});
	}
});

/** 营业执照注册号 */
$("#businessLicense").focus(function(){
	$("#businessLicenseLab").text("");
}).blur(function(){
	 var $businessLicense = $(this).val().trim();
	var $businessLicenseLab = $("#businessLicenseLab"); 
	var flag = Register.validateBusinessLicense($businessLicense,$businessLicenseLab);
	if (flag) {
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.VALIDATELICENSE%>',
	        data:{License:$businessLicense},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$businessLicenseLab.text("该营业执照已经被注册，请重新输入");
	        		$("#businessLicense").val("");
				}
			}
		});
	}
});

/********************图片预览***********************/
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
$('select[name="custType"]').change(function(){
	if($(this).val()=='0'){
		$('tr[name="ent"]').hide();
	}else{
		$('tr[name="ent"]').show();
	}
});


</script>
</html>	