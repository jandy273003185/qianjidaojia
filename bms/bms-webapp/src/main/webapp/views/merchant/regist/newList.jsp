<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.qrcode.min.js?v=${_jsVersion}"/>'></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商户列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style>
</head>

<script type="text/javascript">
	
$(function(){
	//导出商户数据
	$('.exportBut').click(function(){
		var merchantCode = $('.search-table #merchantCode').val();
		var startCreateTime = $('.search-table #startCreateTime').val();
		var endCreateTime = $('.search-table #endCreateTime').val();
		var auditState = $('.search-table #auditState').val();
		var custName = $('.search-table #custName').val();
		var email = $('.search-table #email').val();
		var merchantState = $('.search-table #merchantState').val();
		
		var src ="<%= request.getContextPath()+ MerchantPath.BASE+MerchantPath.NEWEXPORTMERCHANTINFO%>?merchantCode="+
		merchantCode+
		"&startCreateTime="+
		startCreateTime+
		"&endCreateTime="+
		endCreateTime+
		"&auditState="+
		auditState+
		"&custName="+
		custName+
		"&email="+
		email+
		"&merchantState="+
		merchantState;
		$(".exportBut").attr("href",src);
		
	});
   	$("#merchantDiv").draggable({ addClasses: false}); 
	$("#showImageDiv").draggable({ addClasses: false});  
	$("#updateMerchantClose").click(function(){
		$("#merchantDiv").css({"left":"0px","top":"0px"});
	});
	$("#previewImageModal").click(function(){
		$("#showImageDiv").css({"left":"0px","top":"0px"});
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
			    	   	        		 if(_this.attr('id')=="certAttribute1"){//身份證
			    	       	  				$("#representativeName").val(json.cardName);
			    	       	  				$("#representativeCertNo").val(json.cardId);
			    	       	  			}else if(_this.attr('id')=="businessPhoto"){//营业执照
			    	       	  				$("#businessLicense").val(json.businessLicense);
			    	       	  				$("#businessTerm").val(json.businessTermStart);
			    	       	  				/* if("长期"==json.businessTermEnd){
			    	       	  					$("input[value='forever']").click();
			    	       	  				}else{
			    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
			    	       	  				} */
			    	       	  				$("#businessRegAddr").val(json.legalAddress);
			    	       	  				
			    	       	  			} 
			    	   				}
			    	   			}
			    	   		});
			             
			             
			             
					}
				});
			});
	
})
	
function bankNameChange(){
	$("#updateMerchant #bankAcctName").val($("#updateMerchant #custName_04").val());
}
function confirmMerchantInfo(){
	var merchantF = $("#updateMerchant #merchantFlag").val();
	if(merchantF == '0'){
		if(kong.test($("#agentName").val())){
			$.gyzbadmin.alertFailure("联系人不能为空！");
			return false;
		}
		if(kong.test($("#certifyNo").val())){
			$.gyzbadmin.alertFailure("身份证号不能为空！");
			return false;
		}
		
		if(kong.test($("#mobile").val())){
			$.gyzbadmin.alertFailure("手机号吗不能为空！");
			return false;
		}
		if(kong.test($("#updateMerchant  #businessRegAddr").val())){
			$.gyzbadmin.alertFailure("营业执照所在地不能为空！");
				$("#updateMerchant #businessRegAddr").focus();
				 return false;
		};


		if(isNull($("#updateMerchant #custAdd")[0])){
			$.gyzbadmin.alertFailure("请填写企业地址");
			$("#updateMerchant #custAdd").focus();
			return false;
		}
		
		
		if(kong.test($("#categoryId").val())){
			$.gyzbadmin.alertFailure("行目类别不能为空");
			return false;
		}
		if(kong.test($("#mchRole").val())){
			$.gyzbadmin.alertFailure("商户角色不能为空！");
			return false;
		}
		if(kong.test($("#country").val())){
			$.gyzbadmin.alertFailure("省份地区不能为空！");
			return false;
		}
		
		if(isNull($("#updateMerchant #contactPhone")[0])){
			$.gyzbadmin.alertFailure("请填写企业联系电话");
			$("#updateMerchant #contactPhone").focus();
			return false;
		}
		if(!isPhoneNo($("#updateMerchant #contactPhone")[0])){
			$.gyzbadmin.alertFailure("电话格式不对");
			$("#updateMerchant #contactPhone").focus();
			return false;
		}
		
		var orgInstCode =$("#updateMerchant #orgInstCode").val();
		var orgInstCode_02 =$("#updateMerchant #orgInstCode_02").val();
		
		//校验组织机构代码唯一性
		var validateOrgInstCode =true ;
		if(!isNull($("#updateMerchant #orgInstCode")[0])){
			
			if(!isValidateNtpCode($("#updateMerchant #orgInstCode").val())){
				$.gyzbadmin.alertFailure("请填写正确的9-10位组织机构代码");
				$("#updateMerchant #orgInstCode").focus();
				return false;
			}
			
			if(orgInstCode!=orgInstCode_02){
				$.ajax({
					async:false,
					dataType:"json",
					url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATEORGINSTCODE%>',
		            data:{orgInstCode:orgInstCode},
		            success:function(data){
		            	if(data.result=="FAIL"){
		            		$.gyzbadmin.alertFailure("该组织机构代码已经被使用");
		            		validateOrgInstCode = false;
						}else{
							validateOrgInstCode = true;
						}
		   			}
		         });
				if(!validateOrgInstCode){
					return false;
				}
			}
		}
		
		if(isNull($("#updateMerchant #fcontactunitNumber")[0])){
			$.gyzbadmin.alertFailure("来往单位编号不可为空");
			$("#updateMerchant #fcontactunitNumber").focus();
			return false;
		}
		
		
		if(kong.test($("#updateMerchant #branchBank").val())){
			$.gyzbadmin.alertFailure("请输入支行信息");
			 $("#updateMerchant #branchBank").focus();
			 return false;
		}
		/* if(kong.test($("#updateMerchant #openAddressRec").val())){
			$.gyzbadmin.alertFailure("请输入开户行地址");
			 $("#updateMerchant #openAddressRec").focus();
			 return false;
		} */
		if(kong.test($("#updateMerchant #bankAcctName").val())){
			$.gyzbadmin.alertFailure("请输入银行开户名");
			 $("#updateMerchant #bankAcctName").focus();
			 return false;
		}
		
		if(!checkBankCardFormat($("#updateMerchant #bankCard"))){
			$.gyzbadmin.alertFailure("公司对公账户为8-30位的正整数组成");
			 $("#updateMerchant #bankCard").focus();
			 return false;
		 }
		

		//验证银行卡格式
		function checkBankCardFormat($_obj)
		{
			if(!isNumber($_obj[0]) || $_obj.val().length < 8 || $_obj.val().length > 30)
			{
				return false;
			}
			
			return true;
		}
	
		
	}
	
	
	if(isNull($("#updateMerchant #custName_04")[0])){
		$.gyzbadmin.alertFailure("请填写企业名称");
		$("#updateMerchant #custName").focus();
		return false;
	}
	
	if(isNull($("#updateMerchant #businessLicense")[0])){
		$.gyzbadmin.alertFailure("请填写营业执照注册号");
		$("#updateMerchant #businessLicense").focus();
		return false;
	}
		 
	if($("#updateMerchant #businessLicense05").val().trim()!=$("#updateMerchant #businessLicense").val().trim()){
		var validateLicense =true ;		
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATELICENSE%>',
	        data:{businessLicense:$("#updateMerchant #businessLicense").val().trim()},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$.gyzbadmin.alertFailure("该营业执照注册号已经被使用");
	        		validateLicense = false;
				}else{
					validateLicense = true;
				}
				 }});
		if(!validateLicense){
			return false;
		}
	}
	if(isNull($("#updateMerchant #businessTerm")[0])){
		$.gyzbadmin.alertFailure("请选择日期");
		$("#updateMerchant #businessTerm").focus();
		return false;
	}
	
	
	 var selectDate;
     selectDate=$("#updateMerchant #businessTerm").val(); 
   	 selectDate=selectDate.replace(/-/g,   "/");
     var dateA=new Date(selectDate);
     var dateNow=new Date();
     if(Date.parse(dateNow)-Date.parse(dateA)>0){
    	 $.gyzbadmin.alertFailure("营业期限必须大于今天");
    	 $("#updateMerchant #businessTerm").focus();
         return false;
     } 

		// 法人真实姓名
		if(kong.test($("#updateMerchant  #representativeName").val())){
			$.gyzbadmin.alertFailure("请输入法人真实姓名");
				$("#updateMerchant #representativeName").focus();
				 return false;
		};
			// 法人身份证号码
		if(!isCertNo($("#updateMerchant  #representativeCertNo")[0])){
			$.gyzbadmin.alertFailure("请输入正确的身份证号码");
				 $("#updateMerchant #representativeCertNo").focus();
				 return false;
		};

		// 法人手机号码  
		if(!isPhoneNo($("#updateMerchant  #representativeMobile")[0])){
			$.gyzbadmin.alertFailure("请输入正确的手机号码");
				 $("#updateMerchant #representativeMobile").focus();
				 return false;
		};
     
     
     if(isNull($("#updateMerchant #merchantState")[0])){
			$.gyzbadmin.alertFailure("请选择商户登陆状态");
			return false;
		}
		
		// 银行
		if(kong.test($('#updateMerchant #bank').val())) {
			$.gyzbadmin.alertFailure("请选择银行");
			return false;
		}
		// 银行对公账号
		if(kong.test($("#updateMerchant #bankCard").val())){
			$.gyzbadmin.alertFailure("请输入银行对公账号");
			 $("#updateMerchant #bankCard").focus();
			 return false;
		}

	
	var custId05=$("#custName_id_01").val().trim();
	var merchantState= $("#updateMerchant #merchantState").val().trim();
	var merchantCode= $("#updateMerchant #merchantCode").val().trim();
	var email=$("#updateMerchant #email_04").val().trim();
	var custName=$("#updateMerchant #custName_04").val().trim();	
	var businessLicense=$("#updateMerchant #businessLicense").val().trim();
	var custAdd = $("#updateMerchant #custAdd").val();
	var businessTerm=$("#updateMerchant #businessTerm").val().trim();
	if(businessTerm=='长期有效'){
		businessTerm = "forever";
	}
	var contactPhone=$("#updateMerchant #contactPhone").val().trim();
	var fcontactunitNumber=$("#updateMerchant #fcontactunitNumber").val().trim();	
	var representativeName = $("#updateMerchant #representativeName").val().trim();
	var representativeCertNo = $("#updateMerchant #representativeCertNo").val().trim();
	var representativeMobile = $("#updateMerchant #representativeMobile").val().trim();
	var agentMobile = $("#updateMerchant #representativeMobile").val().trim();
	var bankCard = $("#updateMerchant #bankCard").val().trim();
	var branchBank = $("#updateMerchant #branchBank").val().trim();
	var bankAcctName = $("#updateMerchant #bankAcctName").val().trim();
	var bankCode =$("#updateMerchant #bank").val();
	var orgInstCode=$("#updateMerchant #orgInstCode").val().trim();
	var comment = $("#updateMerchant #comment").val().trim();
	var categoryId = 			$("#categoryId").val().trim();
	var mchRole =				$("#mchRole").val().trim();
	var province = $("#province").val().trim();
	var city = $("#city").val().trim();
	var country = $("#country").val().trim();
	var agentName = $("#agentName").val().trim();
	var certifyNo = $("#certifyNo").val().trim();
	var mobile = $("#mobile").val().trim();
			$.ajax({
					type : "POST",
					url :  window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.UPDATEFILEUPLOAD%>?custId='+custId05,
					data :{
						businessPhoto : $('#businessPhototemp').val(),
						certAttribute0 : $('#certAttribute0temp').val(),
						certAttribute1 : $('#certAttribute1temp').val(),
						certAttribute2 : $('#certAttribute2temp').val(),
						doorPhoto : $('#doorPhototemp').val()
					},
					dataType : "json",
					success : function(data) {
						if(data.result=='SUCCESS'){
							$.post(window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.UPDATEMERCHANTINFO%>',{
					 		    'custId':custId05,
					 		    'merchantCode':merchantCode,
					 			'email' : email,
					 			'custName' : custName,
					 			'branchBank'  :branchBank,
					 			'bankAcctName':bankAcctName,
					 			'businessLicense' : businessLicense,
					 			'businessRegAddr' : $("#updateMerchant  #businessRegAddr").val(),
					 			'businessTermEnd' : businessTerm,
					 			'agentMobile':agentMobile,
					 			'custAdd' : custAdd,
					 			'contactPhone' : contactPhone,
					 			'fcontactunitNumber' : fcontactunitNumber,
					 			'orgInstCode' : orgInstCode,
					 			'representativeName' : representativeName,
					 			'representativeCertNo' : representativeCertNo,
					 			'representativeMobile' : representativeMobile,
					 			'compMainAcct' : bankCard,
					 			'compAcctBank' :bankCode,
					 			'merchantState': merchantState,
					 			'comment':comment,
					 			'businessType':data.businessType,
			    	 			'certAttributeType1':data.certAttributeType1,
			    	 			'idCardType_1':data.idCardType_1,
			    	 			'idCardType_2':data.idCardType_2,
			    	 			'doorPhoto':data.doorPhoto,
			    	 			'mchRole' : mchRole,
			        			'custAdd' : custAdd,
			        			'province':province,
			        			'city':city,
			        			'country' :country,
			        			'agentName' :agentName,
			        			'categoryType' : categoryId,
			        			'certifyNo' : certifyNo,
			        			'mobile' : mobile
					 	},function(data){
					 		$.unblockUI();
							if(data.result=="SUCCESS"){
								$("#updateMerchant").hide();
								$.gyzbadmin.alertSuccess('修改商户成功！', null, function(){
									window.location.reload();
								});
							}else{
								$.gyzbadmin.alertFailure("修改商户失败！"+data.message,null, function(){
									window.location.reload();
								});
							}
					 	},'json');
			        	}else{
			        		$.gyzbadmin.alertFailure("扫描件上传失败,请选择合适的类型");
			        	}
					}
				});  
}
</script>
<body  onload="loadMerchant()" style="overflow-x:hidden;">
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
						<input id="state_02" value="${queryBean.auditState }"  type="hidden">
						<input id="merchantState_02" value="${queryBean.merchantState }"  type="hidden">
						<input type="hidden" id="businessPhototemp"/>
						<input type="hidden" id="certAttribute0temp"/>
						<input type="hidden" id="certAttribute1temp" />
						<input type="hidden" id="certAttribute2temp" />
						<input type="hidden" id="doorPhototemp" />
							<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=MerchantPath.BASE + MerchantPath.NEWLIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">注册时间：</td>
									<td class="td-right">
									<input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									</td>
									
									<td class="td-left">审核状态：</td>
									<td class="td-right">								   
										<select name="auditState" id="auditState" >
										  <option  value="" > 请选择 </option>
										  <option  value="00"> 审核通过 </option>
										  <option  value="01"> 待审核 </option>
										  <option  value="04"> 审核不通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
								</tr>
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" id="email"  value="${queryBean.email }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户状态：</td>
									<td class="td-right">								   
										 <select name="merchantState" id="merchantState" >
										  <option  value="" >请选择 </option>
										  <option  value="00"> 正常 </option>
										  <option  value="01"> 停用 </option>
										  <option  value="02"> 登录账户冻结 </option>
										  <option  value="03"> 注册待激活 </option>
										  <option  value="04"> 商户审核中 </option>
										  <option  value="05"> 商户协议待录入 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									</tr>
									<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MerchantPath.BASE + MerchantPath.NEWLIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span> 
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">商户列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th width="10%">商户编号</th>
											<th width="10%">账号</th>
											<th width="10%">商户名称</th>
											<th width="12%">注册时间</th>	
											<th width="10%">银行开户名</th>	
											<th width="7%">商户状态</th>	
										<!--  	<th width="10%">报备状态</th>
											<th width="10%">报备审核状态</th>-->
											<th width="7%">审核状态</th>
											<th width="7%">审核信息</th>	
											<th width="6%">审核人</th>
											<th width="14%">操作</th>
											<th width="12%">查看收款二维码</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${merchantList }" var="merchant">
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<td>${merchant.email }</td>
											<td>${merchant.custName }</td>
											<td>
												${merchant.createTime }
											</td>
											<td>${merchant.bankAcctName }</td>
											<td>
												<c:choose>   
							                        <c:when test="${merchant.merchantState =='00'}">  									  
							                           	 正常								  
							                        </c:when>  
							  						<c:when test="${merchant.merchantState =='01'}">  									  
							                           	停用							  
							                        </c:when> 
							                        <c:when test="${merchant.merchantState =='02'}">  									  
							                           	 登录账户冻结 								  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='03'}">  									  
							                           	注册待激活 								  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='04'}">  									  
							                           	商户审核中							  
							                        </c:when>
							                         <c:when test="${merchant.merchantState =='05'}">  									  
							                           	商户协议待录入							  
							                        </c:when>
	                                             </c:choose>
											</td>							
											<td>
												<c:choose>
													<c:when test="${merchant.auditState eq '00' }">
														审核通过 
													</c:when>
													<c:when test="${merchant.auditState eq '01' }">
														待审核
													</c:when>
													<c:when test="${merchant.auditState eq '04' }">
														审核不通过 
													</c:when>
													<c:otherwise>
														112
													</c:otherwise>
												</c:choose>								
											</td>
											<td>${merchant.aduitMessage}</td>
							
											<td>${merchant.aduitUserName}</td>
											<td>	
												<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}">
												<input type="hidden" name="emailMerchant" id="emailMerchant" value="${merchant.email}">	
												<input type="hidden" name="custName_01" id="custName_01" value="${merchant.custName}">
												<input type="hidden" name="attachStr" id="attachStr" value="${merchant.attachStr}">
												<input type="hidden" name="authId_01" id="authId_01" value="${merchant.authId}">	
												
												<gyzbadmin:function url="<%=MerchantPath.BASE + MerchantPath.UPDATEMERCHANTINFO%>">  	
													<button type="button" onclick="updateMerchantInfo(this,'edit')" data-toggle='modal'  data-target="#updateMerchant"  class="btn btn-primary btn-xs" >修改商户信息</button>
												 </gyzbadmin:function>
												 <button type="button" onclick="updateMerchantInfo(this,'preview')" data-toggle='modal'  data-target="#updateMerchant"  class="btn btn-primary btn-xs" >预览</button> 
												<c:if test="${merchant.merchantFlag=='0' }">
													<gyzbadmin:function url="<%=MerchantPath.BASE + MerchantPath.SENDEMAIL%>"> 
														<c:choose>   
									  						<c:when test="${merchant.auditState=='00'}">  									  
									                          <button type="button"  id="btnEmail2" onclick="sendEmail(this)"  class="btn btn-primary btn-xs"  style="margin-top:5px;" >重发邮件</button>				  
									                        </c:when> 
									                        <c:otherwise>  								  
									                            <button type="button"  id="btnEmail" class="btn  btn-xs"  disabled="disabled"  style="margin-top:5px;">重发邮件</button>		
									                        </c:otherwise>    
		                                            	 </c:choose>  
	                                            	</gyzbadmin:function>
	                                            	<gyzbadmin:function url="<%=MerchantPath.BASE + MerchantPath.UPDATEEMAIL%>">	
														<button type="button" onclick="toUpdateEmail(this)" data-toggle='modal' style="margin-top:5px;" class="btn btn-primary btn-xs" data-target="#updateEmail" >修改邮箱</button>
													</gyzbadmin:function>
                                            	</c:if> 
											</td>
											<td>
												<button type="button"  data-toggle='modal' data-target="#buildMerchant"  class="btn btn-primary btn-xs qifenqian_code_tc" >付款二维码</button> 
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty merchantList}">
										<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty merchantList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
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

	<div class="modal fade" id="updateEmail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" >
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改商户邮箱</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=MerchantPath.BASE + MerchantPath.UPDATEEMAIL %>' method="post" id="addadForm">
		            <table class="modal-input-table" >
						<tr>	
							<td class="td-left" width="20%">当前邮箱：</td>
							<td class="td-right" width="30%">
								<span class="input-icon">
								<input type="hidden" name="custId_02" id="custId_02" >	
									<input type="text" id="emailup" name="emailup" value="" readonly="readonly">
								</span>
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">修改邮箱：</td>
							<td class="td-right" width="30%">
								<span class="input-icon">
									<input type="text" id="email_05" name="email_05">
									<label id="emailLab" class="label-tips"></label>
								</span>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary addadBtn" onclick="updateEmail()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" style="z-index:1040;" id="updateMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:60%;z-index:90;" >
		      <div class="modal-content" style="width:950px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">商户审核</h4>
		            <input type="hidden" id="merchantCode"/>
		             <input type="hidden" id="merchantFlag"/>
		         </div>
		         <div class="modal-body">
						<table id="sample-table-2" class="list-table" >
						<tr>
							
							<td colspan="4" class="headlerPreview">企业信息</td>
						</tr>
						<tr>
							<td class="td-left" width="18%">账号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="email_04" name="email" readonly="readonly" style="width:90%">
									<input type="hidden" name="custName_id_01" id="custName_id_01"  />
								</td>
								
								<td class="td-left" width="18%">企业名称：</td>
								<td class="td-right" width="32%"> 
										<input type="text" id="custName_04" name="custName_04" onchange="bankNameChange()" maxlength="100"  placeholder="企业名称" style="width:90%">
								</td>
						</tr>
						 <tr class="merchant_Flag0">
						 		<td class="td-left">联系人<span style="color:red"></span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="agentName" name="agentName" placeholder="联系人" maxlength="30">
										<i class="icon-leaf blue"></i>
										<label id="agentNameLabel" class="label-tips"></label>
									</span>
								</td>
								<td class="td-left">联系电话：</td>
								<td class="td-right">
										<input type="text" id="contactPhone" name="contactPhone" placeholder="联系电话" style="width:90%">
								</td>
								
						</tr>
					 	<tr class="merchant_Flag0">
					 		<td class="td-left">身份证号码：</td>
							<td class="td-right">
								<input type="text" id="certifyNo" name="certifyNo" placeholder="身份证号码" style="width:90%"> 
							</td>
							<td class="td-left">手机：</td>
							<td class="td-right">
									<input type="text" id="mobile" name="mobile" placeholder="联系电话" style="width:90%">
							</td>
							
						</tr>	
						<tr>
								<td class="td-left">营业执照注册号：</td>
								<td class="td-right">
										<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号" style="width:90%">
										<input type="hidden" id="businessLicense05" name="businessLicense05" >
								</td>
								<td class="td-left">营业期限：</td>
								<td class="td-right">
									<input type="text" name="businessTerm"   id="businessTerm" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
						 </tr>
					
				
						<!-- 报备信息BEGIN  -->							
						 <tr class="merchant_Flag0">
						 	<td class="td-left">营业执照所在地：</td>
							<td class="td-right">
								<input type="text" id="businessRegAddr" name="businessRegAddr" placeholder="营业执照注册所在地" style="width:90%">
							</td>
							<td class="td-left">商户角色<span style="color:red"></span></td>
							<td class="td-right"> 
								<span class="input-icon">
									<select name="mchRole" style="width:100;" id="mchRole" onchange="roleChange();">
										<option value="" >- 请选择商户角色 -</option>
										<option value="0">线上商户</option>
										<option value="1">线下商户</option>
									</select>		
									<label class="label-tips" id="mchRoleLab"></label>
								</span>
							</td>
							
						</tr>
							
						<tr class="merchant_Flag0">
							<td class="td-left">行业类目<span style="color:red"></span></td>
							<td class="td-right" colspan="3">
								<span class="input-icon">
								<select name="categoryTypeId" id="categoryTypeId" style="width:180px;" onchange="getCategoryList();" >
									<option value="">--请选择类目类别--</option>
									<option value="100">实体</option>
									<option value="200">虚拟</option>
									<option value="300">其他</option>
								</select>
								<select name="categoryId" id="categoryId" style="width:300px;">
									<option value="" id="category">--请选择类目--</option>
								</select>
								<label id="categoryIdLabel" class="label-tips"></label>
								</span>
							</td>
						</tr>
							
							<tr class="merchant_Flag0">
								<td class="td-left">省市地区<span style="color:red"></span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-4 pd0" style="padding:0;">
                                    <select class="form-control" id="province" onchange="getCityList();">
                                         	<option value="">--请选择--</option>
                                    </select>
	                                </div>
	                                <div class="col-xs-4 pd0" style="width:31.33%;margin:0 1%;padding:0;">
	                                <select class="form-control" id="city" onchange="getAreaList();">
	                                    <option value="" id="cityDef">--请选择--</option>
	                                </select>
	                                </div>
	                                <div class="col-xs-4 pd0" style="padding:0;">
	                                <select class="form-control" id="country">
	                                    <option value="" id="areaDef">--请选择--</option>
	                                </select>
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>
							<!-- 报备信息END -->
							
							<tr class="merchant_Flag0">
								
								<td class="td-left">企业地址：</td>
								<td class="td-right">
									<input type="text" id="custAdd" name="custAdd" maxlength="100"  placeholder="企业地址" style="width:90%">
								</td>
								<td class="td-left">组织机构码：</td>
								<td class="td-right">
										<input type="text" id="orgInstCode" name="orgInstCode" placeholder="组织机构码" style="width:90%">
										<input type="hidden"  id="orgInstCode_02" name="orgInstCode_02" >
								</td>
							</tr>
							<tr class="merchant_Flag0">
								<td class="td-left">来往单位编号：</td>
								<td class="td-right">
										<input type="text" id="fcontactunitNumber" name="fcontactunitNumber" placeholder="来往单位编号" style="width:90%">
								</td>
								<td class="td-left">商户登陆状态：</td>
								<td class="td-right">
									<select id="merchantState" name ="merchantState" class="width-28">
										<option value="">--登陆状态--</option>
										<option value="00">正常</option>
										<option value="01">停用</option>
										<option value="02">冻结</option>
										<option value="03">注册待激活</option>
										<option  value="04"> 商户审核中 </option>
										<option value="05"> 商户协议待录入 </option>
									</select>								
								</td>
								
							</tr>
							<tr >
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" placeholder="法人真实姓名" maxlength="50" style="width:90%">
								</td>
								<td class="td-left" >法人身份证号码：</td>
								<td class="td-right" > 
									<input type="text"  name="representativeCertNo" id="representativeCertNo" placeholder="法人身份证号码" style="width:90%">
								</td>
							</tr >
							<tr >
								<td class="td-left" >法人手机号码：</td>
								<td class="td-right" colspan="3"> 
									<input type="text" id="representativeMobile"  name="representativeMobile" placeholder="法人手机号码" style="width:35%">
								</td>
							</tr>
							<tr >
								<td class="td-left">营业执照扫描件：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img  id="businessPhotoImageDiv" onclick="bigImg(this);"  style="width:100%;height:100%;display:none"  />										  
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="businessPhotoImageVal02"  />  
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							
							<tr class="merchant_Flag0">
								<td class="td-left">门头照：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img  id="doorPhotoImageDiv" onclick="bigImg(this);" src=""  style="width:100%;height:100%;display:none"  />										  
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="doorPhotoImageVal02"  />  
										<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhotoImage(this)" />
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							
							
							
							<tr >
								<td class="td-left" >身份证图片正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
									<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute1Val02"  />  
										<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片背面：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
										<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute2Val02"  />  
										<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
						<tr>
							<td  colspan="4" class="headlerPreview">银行信息</td>
						</tr>
						<tr>
								<td class="td-left" >银行开户名</td>
								<td class="td-right"> 
										<input type="text"  id="bankAcctName" name="bankAcctName" readonly="readonly" maxlength="100" style="width:90%">
								</td>
							
								<td class="td-left" >开户银行：</td>
								<td class="td-right" > 
									  <sevenpay:selectBankTag id="bank" name ="bank"  banks="${banklist}"  clasS="width-90"/>
								</td>
							</tr>
							<tr>
								<td class="td-left merchant_Flag0" >支行信息：</td>
								<td class="td-right merchant_Flag0" > 
									<input type="text"  id="branchBank" name="branchBank" maxlength="200" style="width:90%">  
								</td>
								<td class="td-left" >公司对公账户：</td>
								<td class="td-right" > 
									<input type="text" id="bankCard" name="bankCard" placeholder="公司账号" style="width:90%">
								</td>
							</tr>
							<!-- <tr>
								<td class="td-left" >开户行地址：</td>
								<td class="td-right" colspan="3"> 
									<input type="text"  id="openAddressRec" name="openAddressRec" maxlength="200" style="width:90%">  
								</td>
								
							</tr> -->
							<tr>
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success certAttribute0Click"  data-target="#previewImageModal" >
										<label id="certAttribute0Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="certAttribute0ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute0Val02"  />  
										<input type="file" name="certAttribute0" id="certAttribute0" onchange="showCertAttribute0Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
																			
								</td>
							</tr>
						
						
							<tr class="merchant_Flag0">
								<td colspan="4" class="headlerPreview">商户协议</td></tr>
							<tr>
							<tr class="merchant_Flag0">
								<td class="td-left">商户协议名称:</td>
								<td class="td-right"  > 
									<input type="text" id="protocolName" name="protocolName" readonly="readonly"/>
								</td>
								<td class="td-left">商户协议内容:</td>
								<td class="td-right"  > 
									<textarea rows="6" cols="50" name="protocolContent" id="protocolContent"></textarea>
								</td>
							</tr>
							<tr >
								<td colspan="4" class="headlerPreview">修改备注</td></tr>
							<tr>
							<tr>
								<td class="td-left">修改备注:</td>
								<td class="td-right" colspan="3" > 
									<textarea rows="5" cols="50" name="comment" id="comment"></textarea>
								</td>
							</tr>
						
						</table>
		         </div>
		         <div class="modal-footer">
						<button type="button" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>
		            	<button type="button" class="btn btn-primary updateMerchantBtn" onclick="confirmMerchantInfo()">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		    </div>
		</div>
		<!-- 图片预览 -->
	 <div class="modal fade" id="previewImageModal"  aria-hidden="true">
	   <div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
	</div> 
	
	<!-- 付款二维码 -->
	<div class="modal fade" style="z-index:1040;" id="buildMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:20%;z-index:90;" >
			<div class="modal-content" style="width:370px;height:410px;" id="merchantDiv">
				<div id="qrcode" style="position: relative ;left:30px;top:30px; "></div>
				<br><br><br>
				<div>
					<tr>
					 	<td class="td-left"><font size=3>&nbsp;商户名称：</font></td>
					 	<td class="td-right" >
					 		<input type="text" id="custName_tc" name="custName" readonly="readonly" style="width:60%; font-size:6px">
					 	</td>
					</tr>
				</div>
			</div>
		</div>
	</div>       
</body>

<script type="text/javascript">

function roleChange(){
	
	var r =	$("#mchRole").val()
	if(r == '0'){
		$("#categoryTypeId").val("100");
		getCategoryList();
	}
	if(r == '1'){
		$("#categoryTypeId").val("100");
		getCategoryList();
	}
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
		url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.AREAINFO %>',
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
		url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.AREAINFO %>',
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
			"channlCode" : "iCr"
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

function updateMerchantInfo(obj,option){
	$(".merchant_Flag0").hide();
	$("#updateMerchant img").attr("src","");
	if(option=='edit'){
		$('#updateMerchant .updateMerchantBtn').show();
		$('#updateMerchant .updateImageDiv').show();
		$('#updateMerchant .firstAuditNotPassBtn').hide();
		$('#updateMerchant .firstAuditPassBtn').hide();
		$('#updateMerchant .secondAuditNotPassBtn').hide();
		$('#updateMerchant .secondAuditPassBtn').hide();
		$('#updateMerchant input,select').prop("disabled", false);
		$('#updateMerchant #categoryTypeId').prop("disabled", true);
	}
	if(option=='preview'){
		$('#updateMerchant .updateMerchantBtn').hide();
		$('#updateMerchant .updateImageDiv').hide();
		$('#updateMerchant .firstAuditNotPassBtn').hide();
		$('#updateMerchant .firstAuditPassBtn').hide();
		$('#updateMerchant .secondAuditNotPassBtn').hide();
		$('#updateMerchant .secondAuditPassBtn').hide();
		$('#updateMerchant input,select').prop("disabled", true);
	}
	$('#updateMerchant').modal({backdrop: 'static', keyboard: false});
	var authId=$(obj).parent().find('#authId_01').val();
	var custId=$(obj).parent().find('#custId_01').val();
 	var merchant = $.data($(obj).parent().parent()[0],"merchant");
 	
 
 	$("#updateMerchant #merchantState").val(merchant.merchantState);
 	$("#updateMerchant #merchantCode").val(merchant.merchantCode);
	$("#updateMerchant #merchantFlag").val(merchant.merchantFlag);
	$("#updateMerchant #businessPhotoImageDiv").show();
	$("#updateMerchant #certAttribute0ImageDiv").show();
	$("#updateMerchant #certAttribute1ImageDiv").show();
	$("#updateMerchant #certAttribute2ImageDiv").show();
	$("#merchantDiv #doorPhotoImageDiv").show();
	$("#updateMerchant #businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
	$("#updateMerchant #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	$("#updateMerchant #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	$("#updateMerchant #certAttribute0ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
	$("#merchantDiv #doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+ MerchantPath.FINDMERCHANTINFO %>?t='+ Math.random(),
		data:
		{
			"custId" 	: custId
		},
		success:function(data){
		
		if(null != data.bmsProtocolContent){
			$("#updateMerchant #protocolName").val(data.bmsProtocolContent.protocolName);
			$("#updateMerchant #protocolContent").val(data.bmsProtocolContent.protocolContent);
		}
		
		$("#updateMerchant #comment").val(data.merchantVo.comment);
		$("#updateMerchant #custName_id_01").val(data.merchantVo.custId);
		$("#updateMerchant #email_04").val(data.merchantVo.email);
		$("#updateMerchant #custName_04").val(data.merchantVo.custName);
		$("#updateMerchant #bankAcctName").val(data.merchantVo.bankAcctName);
		$("#updateMerchant #branchBank").val(data.merchantVo.branchBank);
		$("#updateMerchant #businessLicense").val(data.merchantVo.businessLicense);
		$("#updateMerchant #businessLicense05").val(data.merchantVo.businessLicense);
		$("#updateMerchant #businessRegAddr").val(data.merchantVo.businessRegAddr);
		$("#updateMerchant #custAdd").val(data.merchantVo.custAdd);
		if(data.merchantVo.merchantFlag=='0'){
			$(".merchant_Flag0").show();
			if(data.merchantVo.businessTerm != ''){
				$("#updateMerchant #businessTerm").val(data.merchantVo.businessTerm);
			}else{
				if(data.merchantVo.businessTermEnd == "forever"){
					$("#updateMerchant #businessTerm").val("长期有效");
				}else{
					$("#updateMerchant #businessTerm").val(data.merchantVo.businessTermEnd);
				}
			}		
		}else{
			
			if(data.merchantVo.businessTermEnd == "forever"){
				$("#updateMerchant #businessTerm").val("长期有效");
			}else{
				$("#updateMerchant #businessTerm").val(data.merchantVo.businessTermEnd);
			}
		}
		
		if(data.merchantVo.contactPhone !='' && data.merchantVo.contactPhone != null){
			$("#updateMerchant #contactPhone").val(data.merchantVo.contactPhone);			
		}else{
			$("#updateMerchant #contactPhone").val(data.merchantVo.merchantMobile);
		}
		$("#updateMerchant #fcontactunitNumber").val(data.merchantVo.fcontactunitNumber);
		$("#updateMerchant #mchRole").val(data.merchantVo.mchRole);
		$("#updateMerchant #agentName").val(data.merchantVo.agentName);
		$("#updateMerchant #categoryType").val(data.merchantVo.categoryType);
		$("#updateMerchant #orgInstCode").val(data.merchantVo.orgInstCode);
		$("#updateMerchant #orgInstCode_02").val(data.merchantVo.orgInstCode)
		$("#updateMerchant #bank").val(data.merchantVo.compAcctBank);
		$("#updateMerchant #bankCard").val(data.merchantVo.compMainAcct);
		$("#updateMerchant #representativeName").val(data.merchantVo.representativeName);
		$("#updateMerchant #representativeCertNo").val(data.merchantVo.representativeCertNo);
		if(data.merchantVo.representativeMobile != '' && data.merchantVo.representativeMobile != null){
			$("#updateMerchant #representativeMobile").val(data.merchantVo.representativeMobile);		
		}else{
			$("#updateMerchant #representativeMobile").val(data.merchantVo.agentMobile);		
		}
		if(data.merchantVo.mobile !='' && data.merchantVo.mobile != null){
			$("#updateMerchant #mobile").val(data.merchantVo.mobile);
		}else{
			$("#updateMerchant #mobile").val(data.merchantVo.agentMobile);	
		}
		if(data.merchantVo.certifyNo != '' && data.merchantVo.certifyNo != null){
			$("#updateMerchant #certifyNo").val(data.merchantVo.certifyNo);	
		}else{
			$("#updateMerchant #certifyNo").val(data.merchantVo.representativeCertNo);
		}
		
		//加载行目数据
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETCATEGORYTYPEBYID %>',
			data:
			{
				"categoryId" 	: data.merchantVo.categoryType
			},
			success:function(data){
				if(data.result=="SUCCESS"){
					var categoryList = data.categoryList;
					for ( var category in categoryList) {
						$("#categoryId").append(
								"<option value='"+ categoryList[category].categoryId +"'>"
										+ categoryList[category].categoryName + "</option>");
					}
					$("#updateMerchant #categoryTypeId").val(data.categoryTypeId);
					$("#updateMerchant #categoryId").val(merchant.categoryType);
				}
			}
		});
		
		// 加载省份地区
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETAREAINFOBYID %>',
			data:
			{
				"province" 	: data.merchantVo.province,
				"city" 		: data.merchantVo.city,
				"country" 	: data.merchantVo.country
			},
			success:function(data){
				if(data.result=="SUCCESS"){
					var provinceList = data.provinceList;
					for ( var province in provinceList) {
						$("#province").append(
								"<option value='"+ provinceList[province].provinceId +"'>"
										+ provinceList[province].provinceName + "</option>");
					}
					
					var cityList = data.cityList;
					for ( var city in cityList) {
						$("#city").append(
								"<option value='"+ cityList[city].cityId +"'>"
										+ cityList[city].cityName + "</option>");
					}
					
					var areaList = data.areaList;
					for ( var area in areaList) {
						$("#country").append(
								"<option value='"+ areaList[area].areaId +"'>"
										+ areaList[area].areaName + "</option>");
					}
					
					$("#updateMerchant #province").val(merchant.province);
					$("#updateMerchant #city").val(merchant.city);
					$("#updateMerchant #country").val(merchant.country);
				}
			}
		})
	
		}
	});
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

//图片预览
function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}

function showDoorPhotoImage(obj){  
	 var divObj = document.getElementById("doorPhotoDiv");  
	 var imageObj = document.getElementById("doorPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}

function showCertAttribute0Image(obj){  
	 var divObj = document.getElementById("certAttribute0Div");  
	 var imageObj = document.getElementById("certAttribute0ImageDiv");  
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


function loadMerchant(){
	$('.search-table #auditState').val($("#state_02").val());
	$('.search-table #merchantState').val($("#merchantState_02").val());
	
}
$(document).ready(function(){
	var resultList= ${merchantList};	
	var merchants=$("tr.merchant");
	$.each(resultList,function(i,value){		
		$.data(merchants[i],"merchant",value);				
	});
	
	$('.clearMerchantSearch').click(function(){
		
		$('.search-table #startCreateTime').val('');
		$('.search-table #endCreateTime').val('');
		$('.search-table #merchantState').val('');
		$('.search-table #custName').val('');
		$('.search-table #email').val('');
		$('.search-table #auditState').val('');
		$('.search-table #merchantCode').val('');
	}) 
	
	$('.buttonSearch').click(function(){
		var startDate = $("#startCreateTime").val();
		var endDate= $("#endCreateTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#merchantForm');
		form.submit();
	});
});	

/**修改邮箱**/
function toUpdateEmail(obj){
	var custId=$(obj).parent().find('#custId_01').val();
	var emailMerchant=$(obj).parent().find('#emailMerchant').val();
	$("#emailup").val(emailMerchant);	
	$("#custId_02").val(custId);
}

function updateEmail(){
	$("#emailLab").text("");
	/*邮箱校验*/
	if(isNull($("#email_05")[0])){
		$("#emailLab").text("请设置邮箱地址");
		$("#email_05").focus();
		return false;
	}
	if(!verifyEmailAddress($("#email_05")[0])){
		$("#emailLab").text("邮箱格式不对");
		$("#email_05").focus();
		return false;
	}
	
	var validate =true ;
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATE%>',
        data:{email:$("#email_05").val()},
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
	
	var custId=$("#custId_02").val();
	var email=$("#email_05").val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE + MerchantPath.UPDATEEMAIL %>',
		data:
		{
			"custId" :custId,
			"email":email
		},
		success:function(data){
			if(data.result=="success"){
				$.gyzbadmin.alertSuccess("修改成功！",function(){
				},function(){
					window.location.reload();
				});
				
			}else{
				$.gyzbadmin.alertFailure("修改失败！："+data.message);
			}
		}
	});
}

//发送邮件
function sendEmail(obj){	
var custId=$(obj).parent().find('#custId_01').val();
var custName=$(obj).parent().find('#custName_01').val();
var emailMerchant=$(obj).parent().find('#emailMerchant').val();
var attachStr=$(obj).parent().find('#attachStr').val();

$.ajax({
	type:"POST",
	dataType:"json",
	url:window.Constants.ContextPath +'<%=MerchantPath.BASE + MerchantPath.SENDEMAIL %>',
	data:
	{ 
		"custId":custId,
		"custName" :custName,
		"email":emailMerchant,
		"attachStr":attachStr
	},
	success:function(data){
		if(data.result=="success"){
			$.gyzbadmin.alertSuccess("发送邮件成功！",function(){
			},function(){
				window.location.reload();
			});
			
		}else{
			$.gyzbadmin.alertFailure("发送邮件失败！：");
		}
	}
});
}

$(".qifenqian_code_tc").click(function(){
	 $('#qrcode').empty();
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	var merchantCode =merchant.merchantCode;
	$("#buildMerchant #custName_tc").val(merchant.custName);
	var path = "http://zt.qifenmall.com/pub/merchantqr.do?mid=" + merchantCode;
	$("#qrcode").qrcode({
	  	render: "table", //table方式
		width: 300, //宽度
		height:300, //高度
		text: path 
		
		// https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=M9144030035873982X0 //正式环境链接
		// http://zt.qifenmall.com/pub/merchantqr.do?mid=M9144030035873982X0           //测试环境链接 
	}); 

});

</script>
</html>