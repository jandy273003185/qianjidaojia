<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>

<html>
<head>
	<meta charset="utf-8" />
	<title>商户报备</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
     /* 校验渠道 */
     $(function(){
		$("#submitData").click(function(){
			var merchantCode = $("#merchantCode").val();
			var channelNo = $("#channlCode").val();
			var province = $("#province").val();
			var city = $("#city").val();
			var country = $("#area").val();
			
			var interBank = $("#InterBankCode").val();
			var interBankName = $("#interBankName").val();
			var jylb = $("#jylb").val();
			var firstType ;
			var secondType;
			var thirdType;
			var jylb = $("#jylb").val();
			
			var appId = $("#appId").val();
			var mobileNo = $("#mobileNo").val();
			var customerPhone = $("#customerPhone").val();
			var qq = $("#qq").val();
			var rate = $("#rate").val();
			var bankCardNo = $("#bankCardNo").val();
			var certifyNo = $("#certifyNo").val();
			
			var industryCode = $("#industry").val();
			var bestMerchantType = $("#bestMerchantType").val();
			var box = document.getElementsByName("box");
			var interName= $("#InterName").val();
			var objArray = box.length;
			var powerId ="" ;
			
			for(var i=0;i<objArray;i++){
				if(box[i].checked == true){
					powerId+=box[i].value+",";
				} 
			} 
			//alert(powerId);
			
			if("alipay"==$("#jylb").val()){
				thirdType = $("#alipayType").val();
			}else if("wx"==$("#jylb").val()){
				firstType = $("#wxFirstType").val();
				secondType = $("#wxSecondType").val();
				thirdType = $("#wxThirdType").val();
			}else{//京东
				secondType = $("#jdSecondType").val();
				thirdType = $("#jdThridType").val();
			}
			
			
			if("" == merchantCode){
	    		$("#merchantCodeLab").text("商户编号不能为空");
	    		$("#merchantCode").focus();
	    		return false;
	    	}else{
	    		$("#merchantCodeLab").text('');
	    	}
			if("" == channelNo){
	    		$("#channlCodeLabel").text("商户渠道不能为空");
	    		return false;
	    	}else{
	    		$("#channlCodeLabel").text('');
	    	 }
			if("" == province){
	    		$("#provinceLabel").text("报备省份不能为空");
	    		return false;
	    	}else{
	    		$("#provinceLabel").text("");
	    	}
			if("" == city){
				$("#cityLabel").text("报备市不能为空");
				return false;
			}else{
	    		$("#cityLabel").text("");
	    	}
			
			if("" == country){
				$("#areaLabel").text("报备区不能为空");
				return false;
			}else{
	    		$("#areaLabel").text("");
	    	}
			
			
			
			

	    	//富民渠道
			if("FM_COMBINEDPAY" == channelNo){
				var bankCode = $("#bank").val();

				if("" == bankCode){
					$("#bankLabel").text("报备开户行不能为空");
					return false;
				}else{
		    		$("#bankLabel").text("");
		    	}
				
				if("" == interBank){
					$("#interBankLabel").text("报备开户支行不能为空");
					return false;
				}else{
		    		$("#interBankLabel").text("");
		    	}
				
				
				if("" == jylb){
					$("#jylbLabel").text("报备经营类别不能为空");
					return false;
				}else{
		    		$("#jylbLabel").text("");
		    	}
				
				if("alipay"==$("#jylb").val()){
					if("" == thirdType){
						$("#alipayTypeLabel").text("支付宝行业类目不能为空");
						return false;
					}else{
			    		$("#alipayTypeLabel").text("");
			    	}
				}else if("wx"==$("#jylb").val()){
					if("" == firstType){
						$("#wxFirstTypeLabel").text("微信一级类目不能为空");
						return false;
					}else{
			    		$("#wxFirstTypeLabel").text("");
			    	}
					
					if("" == secondType){
						$("#wxSecondTypeLabel").text("微信二级类目不能为空");
						return false;
					}else{
			    		$("#wxSecondTypeLabel").text("");
			    	}
					
					if("" == thirdType){
						$("#wxThirdTypeLabel").text("微信三级类目不能为空");
						return false;
					}else{
			    		$("#wxThirdTypeLabel").text("");
			    	}
				}else{//京东
					if("" == secondType){
						$("#jdSecondTypeLabel").text("京东二级类目不能为空");
						return false;
					}else{
			    		$("#jdSecondTypeLabel").text("");
			    	}
					
					if("" == thirdType){
						$("#jdThridTypeLabel").text("京东三级类目不能为空");
						return false;
					}else{
			    		$("#jdThridTypeLabel").text("");
			    	}
				}
				
				if("" == powerId){
					$("#powerIdLabel").text("支付功能ID不能为空");
					return false;
				}else{
		    		$("#powerIdLabel").text("");
		    	}
				
				if("52"==powerId||"53"==powerId){
					if("" == appId){
						$("#appIdLabel").text("信公众号的appid或者小程序的appid不能为空");
						return false;
					}else{
			    		$("#appIdLabel").text("");
			    	}
				}
				
				if("" == customerPhone){
		    		$("#customerPhoneLab").text("客服电话不能为空");
		    		$("#customerPhone").focus();
		    		return false;
		    	}else{
		    		$("#customerPhoneLab").text("");
		    	}

				
				$.blockUI();
				$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUBMITREPORT %>",
					{
						"merchantCode":merchantCode,
						"province":province,
						"city":city,
						"country":country,
						"bankCode":bankCode,
						"interBank":interBank,
						"interBankName":interBankName,
						"thirdType":thirdType,
						"powerId":powerId,
						"channelNo":channelNo,
						"appId":appId,
						"mobileNo":mobileNo,
						"customerPhone":customerPhone,
						"qq":qq
					},
					function(data){
						if(data.result=="SUCCESS"){
							
							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
								//$("#updateAccountModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							/* $.gyzbadmin.alertFailure(data.message);
							window.location.reload(); */
							$.gyzbadmin.alertFailure('提交报备失败:' + data.message,function(){
								//$("#updateAccountModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}
					},'json'
			  	);
			
			}//富民渠道
			
			//翼支付渠道
			if("BEST_PAY" == channelNo){
				var bankCode = $("#bestBank").val();

				if("" == bankCode){
					$("#bankLabel").text("报备开户行不能为空");
					return false;
				}else{
		    		$("#bankLabel").text("");
		    	} 
				
				if("" == interName){
		    		$("#interNameLab").text("开户人姓名不能为空");
		    		$("#InterName").focus();
		    		return false;
		    	}else{
		    		$("#interNameLab").text("");
		    	}
				
		    	if("" == bankCardNo){
		    		$("#bankCardNoLab").text("银行卡号不能为空");
		    		$("#bankCardNo").focus();
		    		return false;
		    	}else{
		    		$("#bankCardNoLab").text("");
		    	}
				if("" == certifyNo){
		    		$("#certifyNoLab").text("身份证件号不能为空");
		    		$("#certifyNo").focus();
		    		return false;
		    	}else{
		    		$("#certifyNoLab").text("");
		    	}
				
				$.blockUI();
				$.ajax({
					type : "POST",
					url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.FILEUPLOAD%>',
					data :{
						
						shopInterior : $('#shopInteriortemp').val(),
						"merchantCode":merchantCode
					},
					dataType : "json",
					success : function(data) {
						if(data.result=='SUCCESS')							
							$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SUBMITREPORT %>",
							{
								"merchantCode":merchantCode,
								"province":province,
								"city":city,
								"country":country,
								"bankCode":bankCode,
								"interBank":interBank,
								"interBankName":interBankName,
								"thirdType":thirdType,
								"powerId":powerId,
								"channelNo":channelNo,
								"appId":appId,
								"mobileNo":mobileNo,
								"customerPhone":customerPhone,
								"rate":rate,
								"bankCardNo":bankCardNo,
								"certifyNo":certifyNo,
								"industryCode":industryCode,
								"bestMerchantType":bestMerchantType,
								"interName":interName,
								"qq":qq
							},
							function(data){
								if(data.result=="SUCCESS"){
									
									$.gyzbadmin.alertSuccess("提交报备成功！",function(){
										//$("#updateAccountModal").modal("hide");
									},function(){
										this.location.reload();
									});
								}else{
									/* $.gyzbadmin.alertFailure(data.message);
									window.location.reload(); */
									$.gyzbadmin.alertFailure('提交报备失败:' + data.message,function(){
										//$("#updateAccountModal").modal("hide");
									},function(){
										window.location.reload();
									});
								}
							},'json'
						  );	
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
		 		)
	 		
	 		}//翼支付渠道

	 				
     	});
	});  
    	
  	/***获取市***/
  	function getCity(){
  		var province = $("#province").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELCITY %>",
		{
			"province":province
		},
		function(data){
			if(data.result=="SUCCESS"){
				var cityList = data.cityList;
				$("#city").html("");
       			for ( var city in cityList) {
       				$("#city").append(
       						"<option value='"+ cityList[city].cityId +"'>"
       								+ cityList[city].cityName + "</option>"); 
       			}
       			getArea();
			}else{
				alert("省份不能为空");
			}
		},'json'
	);	
  	}
  	
  	/***获取区县***/
  	function getArea(){
  		var city = $("#city").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELAREA %>",
		{
			"city":city
		},
		function(data){
			if(data.result=="SUCCESS"){
				var areaList = data.areaList;
				$("#area").html("");
       			for ( var area in areaList) {
       				$("#area").append(
       						"<option value='"+ areaList[area].areaId +"'>"
       								+ areaList[area].areaName + "</option>");
       			}
			}else{
				alert("市不能为空");
			}
		},'json'
	);	
  	}
  	
  	/***获取开户支行***/
  	function getInterBank(){
  		var bank = $("#interBank").val().trim();
  		
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELINTERBANK %>",
		{
			"bank":bank
		},
		function(data){
			if(data.result=="SUCCESS"){
				var interBankList = data.interBankList;
				$("#interBank_").html("");
				$("#interBank_").append("<option value=''>--请选择--</option>");
       			for ( var interBank in interBankList) {
       				$("#interBank_").append(
       						"<option value='"+ interBankList[interBank].bankBranchId +"'>"
       								+ interBankList[interBank].bankBranchName + "</option>");
       			}
			}
		},'json'
		);	
  	}

  	/***获取开户支行***/
  	function setInterBank(){
  		var interBankName = $("#interBank_").find("option:selected").text().trim();
  		var interBank = $("#interBank_").val();
  		$("#InterBankCode").val(interBank);
  		$("#interBankName").val(interBankName);
  	}
  	
  	/***获取行业类目***/
  	function getCategoryList(){
  		var jylb = $("#jylb").val().trim();
  		if(jylb!=""){
  			$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELFIRSTTYPE %>",
  					{
  						"type":jylb,
  						"flag":"first"
  					},
  					function(data){
  						if(data.result=="SUCCESS"){
  							var categoryList = data.categoryList;
  							if("alipay"==jylb){//支付宝
  								$("#alipayType").html("");
  								$("#alipayType").append("<option value=''>--请选择--</option>");
  					       			for ( var category in categoryList) {
  					       				$("#alipayType").append(
  					       						"<option value='"+ categoryList[category].id +"'>"
  					       								+ categoryList[category].categoryName + "</option>");
  					       			}
  					       			
  					       			$("#wx").css("display","none");
  					       			$("#alipay").css("display","");
  					       			$("#jd").css("display","none");
  					  		}else if("wx"==jylb){//微信
  					  			$("#wxFirstType").html("");
  					  			$("#wxFirstType").append("<option value=''>--请选择--</option>");
  				       			for ( var category in categoryList) {
  				       				$("#wxFirstType").append(
  				       						"<option value='"+ categoryList[category].id +"'>"
  				       								+ categoryList[category].firstType + "</option>");
  				       			}
  				       			
  				       			$("#wx").css("display","");
  				       			$("#alipay").css("display","none");
  				       			$("#jd").css("display","none");
  					  		}else{//京东
  					  			$("#jdSecondType").html("");
  					  			$("#jdSecondType").append("<option value=''>--请选择--</option>");
  				       			for ( var category in categoryList) {
  				       				$("#jdSecondType").append(
  				       						"<option value='"+ categoryList[category].id +"'>"
  				       								+ categoryList[category].secondType + "</option>");
  				       			}
  				       			
  				       			$("#wx").css("display","none");
  				       			$("#alipay").css("display","none");
  				       			$("#jd").css("display","");
  					  		}
  			       		
  						}else{
  							alert("行目编号查询失败");
  						}
  					},'json'
  				);
  		}
  		
  	}
  	
  	
  	/***获取行业类目***/
  	function getOtherTypeList(type,flag){
  		var firstType = $("#"+type+"FirstType").find("option:selected").text().trim();
  		var secondType = $("#"+type+"SecondType").find("option:selected").text().trim();
  		var flag_ = true;
  		if(flag=="second"){
  			if("wx"==type){
  				if(firstType==""){
  	  				flag_ = false;
  	  				return false;
  	  			}
  			}else{
  				if(secondType==""){
  	  				flag_ = false;
  	  				return false;
  	  			}
  			}
  			
  		}else if(flag=="third"){
  			if(firstType==""||secondType==""){
  				flag_ = false;
  				return false;
  			}
  		}
  		if(flag_){
  			$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELFIRSTTYPE %>",
  					{
  						"type":type,
  						"firstType":firstType,
  						"secondType":secondType,
  						"flag":flag
  					},
  					function(data){
  						if(data.result=="SUCCESS"){
  							var categoryList = data.categoryList;
  							if(flag=="second"){
  								if("wx"==type){//微信
  						  			$("#wxSecondType").html("");
  						  			$("#wxSecondType").append("<option value=''>--请选择--</option>");
  					       			for ( var category in categoryList) {
  					       				$("#wxSecondType").append(
  					       						"<option value='"+ categoryList[category].id +"'>"
  					       								+ categoryList[category].secondType + "</option>");
  					       			}
  					       			$("#wx").css("display","");
  					       			$("#alipay").css("display","none");
  					       			$("#jd").css("display","none");
  						  		}else{//京东
  						  			$("#jdThridType").html("");
  						  			$("#jdThridType").append("<option value=''>--请选择--</option>");
  					       			for ( var category in categoryList) {
  					       				$("#jdThridType").append(
  					       						"<option value='"+ categoryList[category].id +"'>"
  					       								+ categoryList[category].thirdType + "</option>");
  					       			}
  					       			$("#wx").css("display","none");
  					       			$("#alipay").css("display","none");
  					       			$("#jd").css("display","");
  						  		}
  							}else{
  								$("#wxThirdType").html("");
  								$("#wxThirdType").append("<option value=''>--请选择--</option>");
  				       			for ( var category in categoryList) {
  				       				$("#wxThirdType").append(
  				       						"<option value='"+ categoryList[category].id +"'>"
  				       								+ categoryList[category].thirdType + "</option>");
  				       			}
  				       			
  				       			$("#wx").css("display","");
  				       			$("#alipay").css("display","none");
  				       			$("#jd").css("display","none");
  							}
  						}else{
  							alert("行目编号查询失败");
  						}
  					},'json'
  				);
  		}
  			 
  		
  	}
  	
  	 //报备渠道：翼支付店内照

  	/** 照片点击预览 **/
  	$('.shopInteriorClick').click(function(){
  		var divObj = document.getElementById("showImageDiv");
  		var imageObj = document.getElementById("showImage");
  		var obj = document.getElementById("shopInteriorClick");
  		return previewImage(divObj,imageObj,obj); 
  	});

  	//不同渠道样式不同 
  	function selectChannlCode(){
   		if($("#channlCode").val() =='BEST_PAY' || $("#channlCode").val() == ""){
   			//翼支付
   			$("#shopInterior3").attr("style","display:");
   			$("#shopInterior33").attr("style","display:");
   			$("#InterName_").attr("style","display:");
   			$("#InterBankName_").attr("style","display:");
   			$("#bestBankName_").attr("style","display:");
   			$("#bestBankCode_").attr("style","display:");

   			$("#BankName_").attr("style","display:none");
   			$("#BankCode_").attr("style","display:none");
   			$("#InterBank_").attr("style","display:none");
   			$("#InterBankCode_").attr("style","display:none");
   			$("#fmbx").attr("style","display:none");
   			$("#fmbx1").attr("style","display:none");
   		}else{
   			//非翼支付
   			$("#shopInterior3").attr("style","display:none");
   			$("#shopInterior33").attr("style","display:none");
   			$("#InterName_").attr("style","display:none");
   			$("#InterBankName_").attr("style","display:none");
   			$("#bestBankName_").attr("style","display:none");
   			$("#bestBankCode_").attr("style","display:none");

   			$("#BankName_").attr("style","display:");
   			$("#BankCode_").attr("style","display:");
   			
   			$("#InterBank_").attr("style","display:");
   			$("#InterBankCode_").attr("style","display:");
   			$("#fmbx").attr("style","display:");
   			$("#fmbx1").attr("style","display:");
   		}
   		
   	}


	//商户进件资料详情
  	function signUp(obj){
  	  	
  		var merchantCode=$(obj).parent().find('#merchantCode_').val();
  		var patchNo = $(obj).parent().find("#patchNo_").val();
  		
  		$.ajax({
  			type:"POST",
  			dataType:"json",
  			url:window.Constants.ContextPath+'<%=MerchantReportedPath.BASE+ MerchantReportedPath.SELECTMERCHANTREPORTINFO %>',
  			data:
  			{
  				"merchantCode" 	: merchantCode,
  				"patchNo" : patchNo
  			},
  			success:function(data){
  				$("#merchantCode_01").val(data.merchantDetailInfo.merchantCode);
  				$("#channelNo_").val(data.merchantDetailInfo.channelNo);
  				$("#custName_").val(data.merchantDetailInfo.custName);
  				$("#mobileNo_").val(data.merchantDetailInfo.mobileNo);
  				$("#rate_").val(data.merchantDetailInfo.rate);
  				$("#interBankName_").val(data.merchantDetailInfo.interBankName);
  				
  			}
  		});
  	}	

	// 翼支付更新进件
  	function sureAuditor(){
		var merchantCode = $("#merchantCode_01").val();
		var custName = $("#custName_").val(); 
		var mobileNo = $("#mobileNo_").val();
		var rate = $("#rate_").val();
		var interBankName = $("#interBankName_").val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.UPDATESUBMITREPORT %>',
			data:
			{
				"merchantCode" : merchantCode,
				"mchName" : custName,
				"mobileNo" : mobileNo,
				"rate" : rate,
				"interBankName" : interBankName
			},
			
			success:function(data){
				if(data.result=="SUCCESS"){
					
					$.gyzbadmin.alertSuccess('报备商户更新成功！', null, function(){
						window.location.href = window.Constants.ContextPath + '<%=MerchantReportedPath.BASE+MerchantReportedPath.BMSMERCHANTREPORT %>';
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure('报备商户更新失败',null ,function(){
					},function(){
						window.location.reload();
					});

				}
			}
		});

  	}
</script>

<body>
	<!-- 用户信息 -->
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
			<!-- 	<input type="hidden" id="InterBankCode" name="InterBankCode" value=""/> -->
					<input type="hidden" id="shopInteriortemp"/>
					<div class="row">
						<div class="col-xs-12">
							<div class="nino" style="display:none; text-align: center; color: #438eb9;">
								<h3 >商户行业类别信息：<span class="showInfo"></span></h3>
							</div>
						<!-- 查询条件 -->
							<form action='<c:url value="<%=MerchantReportedPath.BASE +MerchantReportedPath.BMSMERCHANTREPORT %>"/>' name="fileUpload" enctype="multipart/form-data" method="post" >
							<table class="search-table" style="margin: 40px 0;">
							<tr>
								<td class="td-left" >填写商户编号</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="merchantCode" name="merchantCode" maxlength="300"  placeholder="请填写商户编号">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="merchantCodeLab"></label>
									</span>
								</td>
								
								<td class="td-left">报备渠道<span style="color:red">*</span></td>
								<td class="td-right">
									<select name="channlCode" id="channlCode" style="width:300px;" onchange="selectChannlCode();">
										<option value="">--请选择报备渠道--</option>
										 <c:if test="${not empty infoList }">
				                            <c:forEach items="${infoList }" var="info">
				                            	<option id="${info.channlId}" value="${info.channlCode}">${info.channlName}</option>
				                            </c:forEach>
		                    		  	 </c:if>
									</select>
									<label id="channlCodeLabel" class="label-tips"></label>
								</td>
								
								<td class="td-left" >银行卡预留手机号</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="mobileNo" name="mobileNo" maxlength="300"  placeholder="请填写银行卡预留手机号">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="mobileNoLab"></label>
									</span>
								</td>
								
							</tr>
							
							<tr>
								<td class="td-left">所在省<span style="color:red">*</span></td>
								<td class="td-right">
									<select name="province" id="province" style="width:300px;" onchange="getCity();">
										<option value="">--请选择省--</option>
			                            <c:if test="${not empty provinceList }">
				                            <c:forEach items="${provinceList }" var="province">
				                            	<option id="${province.provinceId}" value="${province.provinceId}">${province.provinceName}</option>
				                            </c:forEach>
		                    		  	</c:if>
									</select>
									<label id="provinceLabel" class="label-tips"></label>
								</td>
								<td class="td-left">所在市<span style="color:red">*</span></td>
								<td class="td-right">
									<select name="city" id="city" style="width:300px;" onchange="getArea();">
										<option value="">--请选择市--</option>
									</select>
									<label id="cityLabel" class="label-tips"></label>
								</td>
								
								<td class="td-left">所在区<span style="color:red">*</span></td>
								<td class="td-right">
									<select name="area" id="area" style="width:300px;" >
										<option value="">--请选择区--</option>
									</select>
									<label id="areaLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left" id="BankName_" style="display:none">开户行：</td>
								<td class="td-right" id="BankCode_" style="display:none">
									<span class="input-icon">
									<select name="bank" id="bank" style="width:300px;" >
										<option value="">--请选择开户行--</option>
										<c:if test="${not empty bankList }">
				                            <c:forEach items="${bankList }" var="bank">
				                            	<option id="${bank.bankId}" value="${bank.bankId}">${bank.bankName}</option>
				                            </c:forEach>
		                    		  	</c:if>
									</select>
									<label id="bankLabel" class="label-tips"></label> 
								</span>
								</td>	
								
								<td class="td-left" id="bestBankName_" style="display:">开户行：</td>
								<td class="td-right" id="bestBankCode_" style="display:">
									<span class="input-icon">	
									<select name="bestBank" id="bestBank">
										<option value="">--请选择--</option>

										<c:forEach items="<%=BestBankCode.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.bestBankCode}">selected</c:if>>${status.code}</option>

										</c:forEach>
									</select>
									</span>
								</td>
								
								<td class="td-left" id="InterBank_" style="display:none" >开户支行号：</td>
								<td class="td-right" id="InterBankCode_"  style="display:none">
									<span class="input-icon">
									<input type="text" style="width: 300px;"  id="InterBankCode" name="InterBankCode"   placeholder="请填写开户支行">
									<!-- <select name="interBank_" id="interBank_" style="width:180px;"  onchange="setInterBank();">
										<option value="">--请选择开户支行--</option>
									</select> -->
									<label id="interBankLabel" class="label-tips"></label>
									</span>
								</td>
								
								<td class="td-left" id="InterName_"  style="display:">开户人姓名：</td>
								<td class="td-right" id="InterBankName_"  style="display:">
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="InterName" name="InterName"   placeholder="请填写开户人姓名">
										<i class="icon-leaf blue"></i>
										<label id="interNameLabel" class="label-tips"></label>
									</span>
								</td>
								
								
								<td class="td-left" >开户支行名称</td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="interBankName" name="interBankName" maxlength="300"  placeholder="请填写开户支行名称">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="interBankNameLabel"></label>
									</span>
								</td>
							</tr>
							<tr id="fmbx" style="display: none ">
								<td class="td-left" >经营类别：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="jylb" id="jylb" style="width:180px;" onchange="getCategoryList();" >
										<option value="">--请选择--</option>
										<option value="alipay">--支付宝--</option>
										<option value="wx">--微信--</option>
										<option value="jd">--京东--</option>
									</select>
									<label id="jylbLabel" class="label-tips"></label>
									</span>
								</td>
								<td class="td-left" >客服电话</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="customerPhone" name="customerPhone" maxlength="300"  placeholder="请填写客服电话">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="customerPhoneLab"></label>
									</span>
								</td>
								
								<td class="td-left" >联系人QQ</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="qq" name="qq" maxlength="300"  placeholder="请填写联系人QQ">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="mobileNoLab"></label>
									</span>
								</td>
							</tr>
							
							<tr id="wx" style="display: none">
								<td class="td-left" >一级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="wxFirstType" id="wxFirstType" style="width:180px;" onchange="getOtherTypeList('wx','second');" >
										<option value="">--请选择--</option>
										
									</select>
									<label id="wxFirstTypeLabel" class="label-tips"></label>
									</span>
								</td>
								<td class="td-left" >二级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="wxSecondType" id="wxSecondType" style="width:180px;" onchange="getOtherTypeList('wx','third');" >
										<option value="">--请选择--</option>
									</select>
									<label id="wxSecondTypeLabel" class="label-tips"></label>
									</span>
								</td>
								<td class="td-left" >三级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="wxThirdType" id="wxThirdType" style="width:180px;">
										<option value="">--请选择--</option>
									</select>
									<label id="wxThirdTypeLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							
							<tr id="alipay" style="display: none">
								<td class="td-left" >一级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="alipayType" id="alipayType" style="width:180px;" >
										<option value="">--请选择--</option>
									</select>
									<label id="alipayTypeLabel" class="label-tips"></label>
									</span>
								</td>
								
							</tr>
							
							<tr id="jd" style="display: none">
								<td class="td-left" >二级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="jdSecondType" id="jdSecondType" style="width:180px;" onchange="getOtherTypeList('jd','second');" >
										<option value="">--请选择--</option>
									</select>
									<label id="jdSecondTypeLabel" class="label-tips"></label>
									</span>
								</td>
								
								<td class="td-left" >三级类目：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="jdThridType" id="jdThridType" style="width:180px;" >
										<option value="">--请选择--</option>
									</select>
									<label id="jdThridTypeLabel" class="label-tips"></label>
									</span>
								</td>
								
							</tr>
							
							
							
							
							<tr id="fmbx1" style="display: none">
								<td class="td-left" >支付功能Id：</td>
								<td class="td-right">
									<span class="input-icon">

									<div id="powerId">
										<c:if test="${not empty powerIdList }">
				                            <c:forEach items="${powerIdList }" var="powerId">
<%-- 				                            	<option id="${powerId.powerId}" value="${powerId.powerId}">${powerId.powerName}</option>
 --%>				                            	<input type="checkbox" name="box" id="box" value="${powerId.powerId}">${powerId.powerName}<BR>
				                            </c:forEach>
		                    			</c:if>
									</div>
										
									
									<%-- <select name="powerId" id="powerId" style="width:180px;" >
										<option value="">--请选择--</option>
										<c:if test="${not empty powerIdList }">
				                            <c:forEach items="${powerIdList }" var="powerId">
				                            	<option id="${powerId.powerId}" value="${powerId.powerId}">${powerId.powerName}</option>
				                            </c:forEach>
		                    			</c:if>
									</select> --%>
									<label id="powerIdLabel" class="label-tips"></label>
									</span>
								</td>
								
								<td class="td-left" >微信公众号的appid或者小程序的appid：</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="appId" name="appId" maxlength="300"  placeholder="请填写商户编号">
										<i class="icon-leaf blue"></i>
										<label id="appIdLabel" class="label-tips"></label>
									</span>
								</td>
								
							
							</tr>
							
							
							
							<tr id="shopInterior3" style = "display:">
								
								<td class="td-left" >银行卡号：</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="bankCardNo" name="bankCardNo"   placeholder="请填写结算银行卡号">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="bankCardNoLab"></label>
									</span>
								</td>
								
								<td class="td-left" >是否有证商户：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="bestMerchantType" id="bestMerchantType" style="width:300px;"  >
										<option value="">--请选择--</option>
										<option value="Unlicensed">--无证商户--</option>
										<option value="Licensed">--有证商户--</option>
										
									</select>
									<label id="bestMerchantTypeLabel" class="label-tips"></label>
									</span>
								</td>
								
								<td class="td-left" >费率：</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="rate" name="rate"   placeholder="请填写商户费率">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="rateNoLab"></label>
									</span>
								</td>
								
								
								
							</tr>
							
							<tr id="shopInterior33" style = "display:">
							
								<td class="td-left" >身份证件号：</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" style="width: 300px;"  id="certifyNo" name="certifyNo"   placeholder="请填写经营人身份证号码">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="certifyNoLab"></label>
									</span>
								</td>
								
								
								
								<td class="td-left" >翼支付商户行业信息：</td>
								<td class="td-right">
									<span class="input-icon">
									<select name="industry" id="industry" style="width:300px;" >
										<option value="">--请选择商户行业--</option>
										<c:if test="${not empty industryList }">
				                            <c:forEach items="${industryList }" var="industry">
				                            	<option id="${industry.industryCode}" value="${industry.industryCode}">${industry.industryName}</option>
				                            </c:forEach>
		                    		  	</c:if>
									</select>
									<label id="industryLabel" class="label-tips"></label>
									</span>
								</td>
							
								<td class="td-left" id="shopInterior_" style="display:">店内照：</td>
								<td class="td-right"  id="shopInteriorName_" style="display:">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv" class="uploadImage">  
										        <img  id="shopInteriorImage"   style="width:100%;height:100%;"/>
										</label>
									</a>
									<div  style="float:left; margin-top:75 " >
										<input type="file" name="shopInterior" id="shopInterior" />
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							
							</tr>
							
							<tr>
								<td colspan="8" align="center"><span
									class="input-group-btn"> <gyzbadmin:function
											url="<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSMERCHANTREPORT%>">
											<button class="btn btn-purple btn-sm" data-toggle='modal'
												id='submitData' data-target="#submitModal"
												style="width: 300px; margin: 20px;">
												提交报备 <i class="icon-plus-sign icon-on-center bigger-110"></i>
											</button>
										</gyzbadmin:function>
								</span></td>
							</tr>

							</table>
							</form>
							<div class="list-table-header">
								商户报备数据列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>报备渠道</th>
											<th>商户报备状态</th>
											<th>文件报备状态</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									  <c:forEach items="${reportedList}" var="reported" varStatus="status">
											  <tr class="reported" id="reported">
												<td>${reported.merchantCode }</td>
											    <td>${reported.custName}</td>
											    
											    <td>
											    <c:if test="${reported.channelNo == 'iCr'}">
											    	华润银行
											    </c:if>
											    <c:if test="${reported.channelNo == 'CR'}">
											    	华润银行
											    </c:if>
											     <c:if test="${reported.channelNo == 'HELIPAY'}">
											    	合利宝
											    </c:if>
											    
											    <c:if test="${reported.channelNo == 'KFT_PAY'}">
											    	快付通
											    </c:if>
											     <c:if test="${reported.channelNo == 'FM_COMBINEDPAY'}">
											    	富民
											    </c:if>
											    <c:if test="${reported.channelNo == 'WX'}">
											    	微信
											    </c:if>
											    <c:if test="${reported.channelNo == 'ALIPAY'}">
											    	支付宝
											    </c:if>
											     <c:if test="${reported.channelNo == 'BEST_PAY'}">
											    	翼支付
											    </c:if>
											    
											    </td>
											    <td>
												   <c:if test="${reported.reportStatus == '00'}">
												    	商户报备成功
												    </c:if>
												     <c:if test="${reported.reportStatus == '10'}">
												    	商户产品报备成功
												    </c:if>
												     <c:if test="${reported.reportStatus == '99'}">
												    	商户报备异常
												    </c:if>
												    <c:if test="${reported.reportStatus == '98'}">
												    	商户产品报备异常
												    </c:if>
												    <c:if test="${reported.reportStatus == '0'}">
												    	待审核
												    </c:if>
												    <c:if test="${reported.reportStatus == '1'}">
												    	审核通过
												    </c:if>
												    <c:if test="${reported.reportStatus == '2'}">
												    	审核失败
												    </c:if>
												    <c:if test="${reported.reportStatus == '3'}">
												    	未认证
												    </c:if>
												     <c:if test="${reported.reportStatus == '4'}">
												    	审核成功但未同意协议
												    </c:if>
												     <c:if test="${reported.reportStatus == '9'}">
												    	子商户被删除
												    </c:if>
											    </td>
											    <td>
											    <c:choose>
											    	<c:when test="${reported.fileStatus == 'Y'}">
												    	已提交
											    	</c:when>
											    	<c:otherwise>
											    		未提交
											    	</c:otherwise>
											    </c:choose>
											    </td>
											    <td>
											   		${reported.reportTime}
											    </td>
											    
											    <td>
											    <c:choose>
											    	<c:when test="${reported.fileStatus == 'Y'}">
											    		<input type="hidden" id="merchantCode_" value="${reported.merchantCode }">
											    		<input type="hidden" id="patchNo_" value="${reported.patchNo }">
											    		
											    		<button type="button"  id="btnEmail" class="btn btn-primary btn-xs" onclick="signUp(this);" data-toggle='modal' style="margin-top:5px;" data-target="#updateMerchantBtn" >报名</button>	
											    	</c:when>
											    	<c:otherwise>
											    		<button type="button" class="btn  btn-xs"  disabled="disabled"  style="margin-top:5px;">报名</button>	
											    	</c:otherwise>
											    </c:choose>
											    </td>
									   </c:forEach> 
										<c:if test="${empty reportedList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if> 
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							 <c:if test="${not empty reportedList}">
								<%@ include file="/include/page.jsp"%>
							</c:if> 
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
	
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
	</div>    
	
	
	<div class="modal fade" id="updateMerchantBtn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width: 800px;">
	         <div class="modal-header" style="background-color:0099CC">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">商户进件</h4>
	         </div>
	         <div class="modal-body">
	         	
	         		
					<table id="sample-table-2" class="list-table" >
					    <tr>
							<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">商户进件信息：</td>
							
						</tr>
						<tr>
							<td class="td-left" width="18%" >商户编号名称：</td>
							<td class="td-right" width="32%" > 
								<input type="text" style="width: 200px;" id="merchantCode_01" value="" />
							</td>
					
							<td class="td-left" width="18%" >报备渠道：</td>
							<td class="td-right" width="32%"> 
								<span class="channelNo"></span>
								<input  type="text"  id="channelNo_" value="" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="18%" >公司名称：</td>
							<td class="td-right" width="32%" > 
								<span class="custName"></span>
								<input type="text" style="width: 200px;"  id="custName_" value="" />
							</td>
					
							<td class="td-left" width="18%" >银行预留手机号：</td>
							<td class="td-right" width="32%"> 
								<span class="mobileNo"></span>
								<input type="text" style="width: 200px;"  id="mobileNo_" value="" />
							</td>
						</tr>
						<!-- <tr>
							<td class="td-left" width="18%" >签约费率：</td>
							<td class="td-right" width="32%" > 
								<span class="rate"></span>
								<input type="text" style="width: 200px;"  id="rate_" value="" />
							</td>
					
							<td class="td-left" width="18%" >开户支行名称：</td>
							<td class="td-right" width="32%"> 
								<span class="interBankName"></span>
								<input type="text" style="width: 200px;"  id="interBankName_" value="" />
							</td>
						</tr> -->
						<!-- <tr>
							<td class="td-left" >营业执照所在地：</td>
							<td class="td-right" >
								<input type="hidden" id="create_id" value="" />
								<input type="hidden"  id="email_02" value="" />
								<input type="hidden"  id="compAcctBank" value="" />
								<span class="businessRegAddr"></span>
							</td>
						
							<td class="td-left" >营业期限：</td>
							<td class="td-right" > 
								<span class="businessTerm"></span>
							</td>
						</tr> -->
						<tr>
							<td class="td-left">营业执照扫描件：</td>
							<td class="td-right">
								<a >									
								  <img  id="businessPhotoImage" src="" style="width:120px;height:100px;"   />										
								</a>
								
							</td>
							<td class="td-left">门头照：</td>
							<td class="td-right" > 
								<a >									
									  <img  id="certAttribute0Image" src=""  style="width:120px;height:100px;"  />										
								</a>
							</td>
						</tr>
						
						<tr>
						   <td class="td-left" >身份证正面：</td>
							<td class="td-right" > 
								<a >									
									  <img  id="certAttribute1" src=""  style="width:120px;height:100px;"  />										
								</a>
								
							</td>
							<td class="td-left" >身份证背面：</td>
							<td class="td-right" > 
								<a >									
									  <img  id="certAttribute2" src=""  style="width:120px;height:100px;"  />										
								</a>
							</td>
						</tr>
						<!-- <tr>
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
							<td class="td-right" colspan="3" > 
								<span class="bankCard"></span>
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
								<span class="rule"></span>
							</td>
						</tr> -->
					</table>

	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn btn-primary addadBtn" onclick="sureAuditor()">进件</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->  
</body>
</html>

