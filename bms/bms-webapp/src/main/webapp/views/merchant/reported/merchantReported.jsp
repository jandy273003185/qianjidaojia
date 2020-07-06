<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.qifenqian.bms.merchant.settle.MerchantSettlePath"%>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
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

/* $(document).ready(function(){
	$("#channlCode").comboSelect();
	
	var resultList= ${reportedList};	
	var reporteds=$("tr.reported");
	$.each(resultList,function(i,value){		
		$.data(reporteds[i],"reported",value);				
	});
	
});	 */

$('.buttonSearch').click(function(){
	
	var form = $('#merchantForm');
	form.submit();
});
	//商户进件状态查询
	function getStatus(obj){
	  	
		var merchantCode=$(obj).parent().find('#merchantCode_').val();
		var patchNo = $(obj).parent().find("#patchNo_").val();
		var channlCode =$(obj).parent().find('#channelNo_').val();
		var bestMerchantType = $(obj).parent().find('#bestMerchantType_').val();
		var loginNo = $(obj).parent().find('#loginNo_').val();
		var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath+'<%=MerchantReportedPath.BASE+ MerchantReportedPath.SELECTMERCHANTREPORTSTATUS %>',
			data:
			{
				"merchantCode" 	: merchantCode,
				"patchNo" : patchNo,
				"bestMerchantType" : bestMerchantType,
				"loginNo" : loginNo,
				"channelNo" : channlCode,
				"outMerchantCode" : outMerchantCode
			},
			success:function(data){
				if(data.result=="SUCCESS"){
					if(null == data.message || "" ==data.message){
						alert("查询成功");
					}else{
						if("02" == data.message){
							//翼支付企业进件成功后签约页面
							alert("查询成功");
							
						}else{
							alert(data.message);
						}
					}
				}else{
					if(null == data.message || "" ==data.message){
						alert("查询失败");
					}else{
						alert(data.message);
					}
				}
			}
		});
	}	


   	var winChild
   	function bottonSearch() {

   		//判定是否已进件
   		         
   		var merchantCode=$("#merchantCode").val();
   		var channlCode =$("#channlCode").val();
   		var merchantType =$("#merchantType").val();
   		var status ="unReported";
   		if(kong.test($("#merchantCode").val())){
   			$("#merchantCodeLab").text("请填写商户号");
   			return false;
   		}
   		if(kong.test($("#channlCode").val())){
   			$("#channlCodeLabel").text("请选择渠道");
   			return false;
   		}
   		if("BEST_PAY" == channlCode){
   	   		 if("01" == merchantType){
   	   	   		 //翼支付微小型商户页面
	   	   		 var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		 	     var name="window";                        //网页名称，可为空;
		 	     var iWidth=1200;                          //弹出窗口的宽度;
		 	     var iHeight=600;                       //弹出窗口的高度;
		 	     //获得窗口的垂直位置
		 	     var iTop = (window.screen.availHeight-30-iHeight)/2; 
		 	     //获得窗口的水平位置
		 	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
		 	     var params='width='+iWidth
		 	            +',height='+iHeight
		 	            +',top='+iTop
		 	            +',left='+iLeft; 
		 	     /*  $.blockUI();  */
		 	      winChild =  window.open(url, name,params); 
   	   	   	 }else{
   	   	   	   	//翼支付企业页面
	   	   	    var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYCOMERCHANTREPORTS%>?merchantCode="+merchantCode+"&merchantType="+merchantType+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);

   	   	   	 }
	   		 
   	   	}
   	   	if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
   	   	}
		 
   		if("SUM_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}

		if("YQB" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.YQBMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
	   
    }

	function bottonSearch1(obj) {
		var merchantCode=$(obj).parent().find('#merchantCode_').val();      
  		var channlCode =$(obj).parent().find('#channelNo_').val();
  		var outMerchantCode =$(obj).parent().find('#outMerchantCode_').val();    
  		var status ="unReported";
		if("SUM_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTINFOREPORTS%>?merchantCode="+merchantCode+"&status="+status+"&outMerchantCode="+outMerchantCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
	}
   	function updateBottonSearch(obj) {
   	   	
   		 var merchantCode=$(obj).parent().find('#merchantCode_').val();      
   		 var channlCode =$(obj).parent().find('#channelNo_').val();
   		 var status ="reported";
   		 if("BEST_PAY" == channlCode){
   	   		 
   			 var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
	   	     var name="window";                        //网页名称，可为空;
	   	     var iWidth=1200;                          //弹出窗口的宽度;
	   	     var iHeight=600;                       //弹出窗口的高度;
	   	     //获得窗口的垂直位置
	   	     var iTop = (window.screen.availHeight-30-iHeight)/2; 
	   	     //获得窗口的水平位置
	   	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	   	     var params='width='+iWidth
	   	            +',height='+iHeight
	   	            +',top='+iTop
	   	            +',left='+iLeft; 
	   	     /*  $.blockUI();  */
	   	      winChild =  window.open(url, name,params);
   	   	 }	
		 if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
   	   	 }
		 if("SUM_PAY" == channlCode){
	   	   	   	
	   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
	   	  }
		 if("YQB" == channlCode){
	   	   	   	
	   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.YQBMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
	   	  }
	   
    }
    
  	//翼支付企业类型不同样式不同 
  	function selectChannlCode(){
  		if($("#channlCode").val() =='BEST_PAY'){
  			$("#merchantType1").attr("style","display:");
  			$("#merchantType2").attr("style","display:");
  	  	}else{
  	  		$("#merchantType1").attr("style","display:none");
  	  		$("#merchantType2").attr("style","display:none");
  	  	}
  	}

  	
  	function getContarct(obj) {
  		var merchantCode = $(obj).parents("tr").find("#merchantCode_").val();
		$("#contractMerchantModal").find("#merchantCode0").val(merchantCode);
	}

  	function bottonContract(){
  		var merchantCode=$("#contractMerchantModal").find("#merchantCode0").val();
  		var productCode=$("#contractMerchantModal").find("#productCode").val();
  		var feeFlag=$("#contractMerchantModal").find("#feeFlag").val();
  		var feeMode=$("#contractMerchantModal").find("#feeMode").val();
  		var billingMode=$("#contractMerchantModal").find("#billingMode").val();
  		var billingValue=$("#contractMerchantModal").find("#billingValue").val();
  		var feeBeginAmt=$("#contractMerchantModal").find("#feeBeginAmt").val();
  		var bottomAmt=$("#contractMerchantModal").find("#bottomAmt").val();
  		var ceilingAmt=$("#contractMerchantModal").find("#ceilingAmt").val();
  		var feeStatus=$("#contractMerchantModal").find("#feeStatus").val();
  		var feeCycle=$("#contractMerchantModal").find("#feeCycle").val();
  		var refundFeeFlag=$("#contractMerchantModal").find("#refundFeeFlag").val();
		var offlineAgreementPath=$("#contractMerchantModal").find("#offlineAgreementPath").val();
		var createdBy = $("#contractMerchantModal").find("#createdBy").val();
  		if(kong.test(refundFeeFlag)){
			$.gyzbadmin.alertFailure("退款手续费标识不可为空");
			return;
		}
  		if(kong.test(feeCycle)){
			$.gyzbadmin.alertFailure("金额累计周期不可为空");
			return;
		}
  		if(kong.test(feeStatus)){
			$.gyzbadmin.alertFailure("收费状态不可为空");
			return;
		}
  		if(kong.test(ceilingAmt)){
			$.gyzbadmin.alertFailure("封顶金额不可为空");
			return;
		}
  		if(kong.test(merchantCode)){
			$.gyzbadmin.alertFailure("商户号不可为空");
			return;
		}
  		/* if(kong.test(productCode)){
			$.gyzbadmin.alertFailure("产品编号不可为空");
			return;
		} */
  		if(kong.test(feeFlag)){
			$.gyzbadmin.alertFailure("是否收费标识不可为空");
			return;
		}
  		if(kong.test(feeMode)){
			$.gyzbadmin.alertFailure("收费模式不可为空");
			return;
		}
  		if(kong.test(billingMode)){
			$.gyzbadmin.alertFailure("计费模式不可为空");
			return;
		}
  		if(kong.test(billingValue)){
			$.gyzbadmin.alertFailure("费率值不可为空");
			return;
		}
  		if(kong.test(feeBeginAmt)){
			$.gyzbadmin.alertFailure("起费金额不可为空");
			return;
		}
  		if(kong.test(bottomAmt)){
			$.gyzbadmin.alertFailure("封底金额不可为空");
			return;
		}
		if(billingValue !=0.35){
			if(kong.test(offlineAgreementPath)){
				$.gyzbadmin.alertFailure("文件路径不可为空");
				return;
			}
		}
		if(kong.test(createdBy)){
			$.gyzbadmin.alertFailure("创建人不可为空");
			return;
		}
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.CONTRACTPRODUCT%>?productCode="+productCode+"&offlineAgreementPath="+offlineAgreementPath,
				{
					'merchantCode':merchantCode,
					'feeFlag':feeFlag,
					'feeMode':feeMode,
					'billingMode':billingMode,
					'billingValue':billingValue,
					'feeBeginAmt':feeBeginAmt,
					'bottomAmt': bottomAmt,
					'ceilingAmt' :ceilingAmt,
					'feeStatus' :feeStatus,
					'feeCycle': feeCycle,
					'refundFeeFlag':refundFeeFlag,
					'createdBy' : createdBy
				},
				function(data){
					
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("签约成功！",function(){
								$("#contractMerchantModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							
							window.location.reload();
							$.gyzbadmin.alertFailure("签约失败！"+data.message,function(){
								$("#contractMerchantModal").modal("hide");
							});
					}
				},'json'

				);
  	}

  	function getContarct1(obj) {
  		var merchantCode = $(obj).parents("tr").find("#merchantCode_").val();
  		var custName = $(obj).parents("tr").find("#custName_").val();
  		var outMerchantCode = $(obj).parents("tr").find("#outMerchantCode_").val();
		$("#contractMerchantModal1").find("#merchantCode1").val(merchantCode);
		$("#contractMerchantModal1").find("#custName1").val(custName);
		$("#contractMerchantModal1").find("#outMerchantCode1").val(outMerchantCode);

		//存token
		$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
		}, function(data) {
			var token = data.token ;
			$("#_token").val(token);
		}, 'json'
		);
	}

  	function bottonContract1(){
  		var merchantCode = $("#contractMerchantModal1").find("#merchantCode1").val();
  		var custName = $("#contractMerchantModal1").find("#custName1").val();
  		var mcc = $("#contractMerchantModal1").find("#levelThree").val();
  		var wechatMchntType = $("#contractMerchantModal1").find("#levelThreeWx").val();
  		var rate = $("#contractMerchantModal1").find("#rate").val();
  		var mchntType = $("#contractMerchantModal1").find("#levelOneZfb").val();
  		var outMerchantCode = $("#contractMerchantModal1").find("#outMerchantCode1").val();
  		$.blockUI();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.OPENPRODUCT%>?merchantCode="+merchantCode+"&custName="+custName+"&outMerchantCode="+outMerchantCode,
				{
  				'mchntType' : mchntType,
				'mcc' : mcc,
				'wechatMchntType' : wechatMchntType,
				'rate' : rate,
				'_TOKEN_ID':$("#_token").val()
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("开通成功！",function(){
								
								$("#contractMerchantModal1").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							$.gyzbadmin.alertFailure("开通失败！"+data.message,function(){
								$("#contractMerchantModal1").modal("hide");
							});
					}
				},'json'

				);
  	}

  	/***获取二级类目***/
  	function getLevelTwo(){
  		var one = $("#levelOne").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELZFBPRODUCT %>",
		{
			"one":one
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelTwo").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelTwo").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].levelTwo +"'>"
       								+ sumPayMccList[sumPayMcc].levelTwo + "</option>"); 
       			}
       			getLevelThree();
			}
		},'json'
		);	
  	}

	/***获取三级类目***/
  	function getLevelThree(){
  		var levelOne = $("#levelOne").val().trim();
  		var levelTwo = $("#levelTwo").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELZFBPRODUCTMCC %>",
		{
			"levelOne":levelOne,
			"levelTwo":levelTwo
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelThree").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelThree").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].mcc +"'>"
       								+ sumPayMccList[sumPayMcc].levelThree + "</option>");
       			}
			}
		},'json'
		);	
  	}

  	/***获取WX二级类目***/
  	function getLevelTwoWx(){
  		var one = $("#levelOneWx").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELWXPRODUCT %>",
		{
			"one":one
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelTwoWx").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelTwoWx").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].levelTwo +"'>"
       								+ sumPayMccList[sumPayMcc].levelTwo + "</option>"); 
       			}
       			getLevelThreeWx();
			}
		},'json'
		);	
  	}

	/***获取WX三级类目***/
  	function getLevelThreeWx(){
  		var levelOne = $("#levelOneWx").val().trim();
  		var levelTwo = $("#levelTwoWx").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELWXPRODUCTMCC %>",
		{
  			"levelOne":levelOne,
			"levelTwo":levelTwo
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelThreeWx").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelThreeWx").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].wechatMchntType +"'>"
       								+ sumPayMccList[sumPayMcc].levelThree + "</option>");
       			}
			}
		},'json'
		);	
  	}
</script>

<body >
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
					<div class="row">
						<div class="col-xs-12">
							<div class="nino" style="display:none; text-align: center; color: #438eb9;">
								<h3 >商户行业类别信息：<span class="showInfo"></span></h3>
							</div>
							<!-- 查询条件 -->
							<%-- <form action='<c:url value="<%=MerchantReportedPath.BASE +MerchantReportedPath.BMSREPORT %>"/>' name="fileUpload"  method="post" >
							<table class="search-table" style="margin: 40px 0;">
								<tr>
									<td class="td-left" >填写商户编号</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text" style="width: 300px;"  id="merchantCode" name="merchantCode" maxlength="300"  placeholder="请填写商户编号">
											<i class="icon-leaf blue"></i>
											<label class="label-tips" id="merchantCodeLab"></label>
										</span>
									</td>									
								</tr>	
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">										
											<button  class="btn btn-purple btn-sm" onclick="bottonSearch1();" style="width: 300px;margin: 20px;">
												查询
											</button>										
										</span>
									</td>
								</tr>
							</table>
							</form> --%>
							
							<!-- 报备 -->
							<form id ="merchantForm" action='<c:url value="<%=MerchantReportedPath.BASE +MerchantReportedPath.BMSREPORT %>"/>' name="fileUpload" enctype="multipart/form-data" method="post" >
							<input type="hidden" id="_token" name="_token"/>
							<table class="search-table" style="margin: 40px 0;">
							<tr>
								<td class="td-left" >填写商户编号</td>
								<td class="td-right" colspan="3"> 
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
								
								<td class="td-left" style = "display: none" id = "merchantType1" >企业类型<span style="color:red">*</span></td>
								<td class="td-right" style = "display: none" id = "merchantType2">
									<select name="merchantType" id="merchantType" style="width:300px;" >
										<option value="">--请选择企业类型--</option>
										<option  value="01"> 微小型商户 </option>
									    <option  value="02"> 企业</option>
									</select>
									<label id="channlCodeLabel" class="label-tips"></label>
								</td>
							</tr>	
							<tr>
								<td colspan="3" align="center">
										<button type="submit" class="btn btn-purple btn-sm buttonSearch">
											查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button> 
								</td>
								<td colspan="5" align="center">
									<span class="input-group-btn">										
											<button  class="btn btn-purple btn-sm" onclick="bottonSearch();" style="width: 300px;margin: 20px;">
												提交报备
											</button>										
									</span>
								</td>
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
											<!-- <th>报备批次号</th> -->
											<th>商户编号</th>
											<th>商户名称</th>
											<th>报备渠道</th>
											<th>渠道商户号</th>
											<th>商户报备状态</th>
											<!-- <th>文件报备状态</th> -->
											<th>返回信息</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									  <c:forEach items="${reportedList}" var="reported" varStatus="status">
											  <tr class="reported" id="reported">
											   <%--  <td>${reported.patchNo }</td> --%>
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
											     <c:if test="${reported.channelNo == 'SUIXING_PAY'}">
											    	随行付
											    </c:if>
											     <c:if test="${reported.channelNo == 'SUM_PAY'}">
											    	商盟支付
											    </c:if>
											     <c:if test="${reported.channelNo == 'YQB'}">
											    	平安付
											    </c:if>
											    </td>
											    <td>${reported.outMerchantCode}</td>
											    <td>
												   <c:if test="${reported.reportStatus == '00'}">
												    	报备成功
												    </c:if>
												     <c:if test="${reported.reportStatus == '10'}">
												    	产品报备成功
												    </c:if>
												     <c:if test="${reported.reportStatus == '99'}">
												    	报备失败
												    </c:if>
												    <c:if test="${reported.reportStatus == '98'}">
												    	产品报备异常
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
												     <c:if test="${reported.reportStatus == '15'}">
												    	资料信息已提交
												    </c:if>
											    </td>
											    <%-- <td>
											    <c:choose>
											    	<c:when test="${reported.fileStatus == 'Y'}">
												    	已提交
											    	</c:when>
											    	<c:otherwise>
											    		未提交
											    	</c:otherwise>
											    </c:choose>
											    </td> --%>
											    <td>
											   		${reported.resultMsg}
											    </td>
											    <td>
											   		${reported.reportTime}
											    </td>
											    
											    <td>
											    <input type="hidden" id="merchantCode_" value="${reported.merchantCode }">
											    <input type="hidden" id="custName_" value="${reported.custName }">
											    <input type="hidden" id="patchNo_" value="${reported.patchNo }">
											    <input type="hidden" id="channelNo_" value="${reported.channelNo }">
											    <input type="hidden" id="bestMerchantType_" value="${reported.bestMerchantType }">
											    <input type="hidden" id="loginNo_" value="${reported.loginNo }">
											     <input type="hidden" id="outMerchantCode_" value="${reported.outMerchantCode }">
											    <c:if test="${reported.channelNo =='BEST_PAY' && reported.bestMerchantType =='02' }">
											    	<c:if test="${reported.reportStatus =='00' }">
												    	<gyzbadmin:function url="<%=MerchantReportedPath.BASE + MerchantReportedPath.CONTRACTPRODUCT %>">
											    			<button type="button"  class="btn btn-primary btn-xs" onclick="getContarct(this);" data-target="#contractMerchantModal" data-toggle='modal' style="margin-top:5px;" >企业签约</button>
											    		</gyzbadmin:function>
											    	</c:if>
											    	 <c:if  test="${reported.reportStatus !='00' }">
											    	 	<button type="button"  class="btn btn-xs"  disabled="disabled"  style="margin-top:5px;" >企业签约</button>
											    	 </c:if>
											    </c:if>
											    
											    <c:if test="${reported.channelNo =='BEST_PAY' || reported.channelNo =='SUIXING_PAY' || reported.channelNo =='SUM_PAY' }">
											    	<c:if test="${reported.reportStatus =='0' }">
											    		<button type="button"  class="btn btn-primary btn-xs" onclick="getStatus(this);" data-toggle='modal' style="margin-top:5px;" >查询状态</button>
											    	</c:if>
											    	 <c:if  test="${reported.reportStatus !='0' }">
											    	 	<button type="button"  class="btn btn-xs"  disabled="disabled"  style="margin-top:5px;" >查询状态</button>
											    	 </c:if>
											    </c:if>
											    <c:if test="${reported.channelNo =='YQB' && reported.reportStatus =='00' }">
											    		<button type="button"  class="btn btn-primary btn-xs" onclick="getStatus(this);" data-toggle='modal' style="margin-top:5px;" >查询状态</button>
											    </c:if>
											    
											    <c:if test="${reported.channelNo =='SUM_PAY' }">
											    	<c:if test="${reported.reportStatus =='00' || reported.reportStatus =='1'}">
												    	<%-- <gyzbadmin:function url="<%=MerchantReportedPath.BASE + MerchantReportedPath.OPENPRODUCT %>"> --%>
											    			<button type="button"  class="btn btn-primary btn-xs" onclick="getContarct1(this);" data-target="#contractMerchantModal1" data-toggle='modal' style="margin-top:5px;" >开通产品</button>
											    		<%-- </gyzbadmin:function> --%>
											    	</c:if>
											    	 <c:if  test="${reported.reportStatus !='00' && reported.reportStatus !='1'}">
											    	 	<button type="button"  class="btn btn-xs"  disabled="disabled"  style="margin-top:5px;" >开通产品</button>
											    	 </c:if>
											    	<c:if test="${reported.reportStatus =='15' }">
											    		<button type="button"  class="btn btn-primary btn-xs" onclick="bottonSearch1(this);" data-toggle='modal' style="margin-top:5px;" >更新图片</button>
											    	</c:if>
											    	 <c:if  test="${reported.reportStatus !='15' }">
											    	 	<button type="button"  class="btn btn-xs"  disabled="disabled"  style="margin-top:5px;" >更新图片</button>
											    	 </c:if>
											    </c:if>
											   
											    <c:choose>
											    	<c:when test="${reported.reportStatus == '99' || reported.reportStatus == '2'}">
											    		<button type="button"  class="btn btn-primary btn-xs" onclick="updateBottonSearch(this);" data-toggle='modal' style="margin-top:5px;" >进件</button>
											    	</c:when>
											    	<c:otherwise>
											    		<button type="button"  class="btn btn-xs"  disabled="disabled"  style="margin-top:5px;" >进件</button>
											    			
											    	</c:otherwise>
											    </c:choose>
											    </td>
										</tr>
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
	
	<!-- 翼支付企业进件签约-->
	<div class="modal fade" style="z-index: 1040;" id="contractMerchantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 60%; z-index: 90;">
			<div class="modal-content" style="width: 1000px;" id="merchantDiv">
				<div class="modal-header" style="background-color: 0099CC">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<!-- <button type="button" class="close" data-dismiss="modal" id="contractMerchantClose" aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="myModalLabel">进件签约</h4>
				</div>
				<div class="modal-body">
					<table id="sample-table-2" class="list-table">
						<tr>
							<td class="td-left" >商户编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="merchantCode0" name="merchantCode0" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="merchantCodeLab0"></label>
								</span>
							</td>
							<td class="td-left" >产品编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="productCode" name="productCode" maxlength="300"  placeholder="请填写产品编号">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="productCodeLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >是否收费</td>
							<td class="td-right" colspan="3">
									<select id="feeFlag">
										<option value="">--请选择--</option>	
										<option value="TRUE">收费</option>	
										<option value="FALSE">不收费</option>	
									</select>
							</td>
							<td class="td-left" >收费模式</td>
							<td class="td-right" colspan="3">
									<select id="feeMode">
										<option value="">--请选择--</option>	
										<option value="SINGLE">单笔计费</option>	
										<option value="ACCUMUL">累计计费</option>	
										<option value="PACKET">包量计费</option>	
									</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >计费方式</td>
							<td class="td-right" colspan="3">
									<select id="billingMode">
										<option value="">--请选择--</option>	
										<option value="FIXED">固定</option>	
										<option value="RAT">比率</option>	
									</select>
							</td>
							<td class="td-left" >费率值</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="billingValue" name="billingValue" maxlength="300"  placeholder="请填写费率值">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="billingValueLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >起费金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="feeBeginAmt" name="feeBeginAmt" maxlength="300"  placeholder="请填写起费金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="feeBeginAmtLab"></label>
								</span>
							</td>
							<td class="td-left" >封底金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="bottomAmt" name="bottomAmt" maxlength="300"  placeholder="请填写封底金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="bottomAmtLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >封顶金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="ceilingAmt" name="ceilingAmt" maxlength="300"  placeholder="请填写封顶金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="ceilingAmtLab"></label>
								</span>
							</td>
							<td class="td-left" >收费状态</td>
							<td class="td-right" colspan="3"> 
								<select id="feeStatus">
										<option value="">--请选择--</option>	
										<option value="TRUE">收费</option>	
										<option value="FALSE">不收费</option>	
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >金额累计周期</td>
							<td class="td-right" colspan="3">
									<select id="feeCycle">
										<option value="">--请选择--</option>	
										<option value="YEAR">按自然年</option>	
										<option value="MONTH">按自然月</option>
										<option value="WEEK">按自然周</option>
										<option value="DAY">按自然日</option>	
									</select>
							</td>
							<td class="td-left" >退款手续费标识</td>
							<td class="td-right" colspan="3">
									<select id="refundFeeFlag">
										<option value="">--请选择--</option>	
										<option value="TRUE">收手续费</option>	
										<option value="FALSE">不收手续费</option>	
									</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >文件本地路径</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="offlineAgreementPath" name="offlineAgreementPath" maxlength="300"  placeholder="请填写文件路径">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="offlineAgreementPathLab"></label>
								</span>
							</td>
							<td class="td-left" >创建人</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="createdBy" name="createdBy" maxlength="300"  value="孙浔" readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="createdByLab"></label>
								</span>
							</td>
						</tr>
					</table>
				</div>
				<div id="hiddenCheck_2" class="modal-footer">
					<button type="button" class="btn btn-primary contractMerchantBtn"  onclick="bottonContract();">确认</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 商盟开通产品-->
	<div class="modal fade" style="z-index: 1040;" id="contractMerchantModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 60%; z-index: 90;">
			<div class="modal-content" style="width: 1000px;" id="merchantDiv">
				<div class="modal-header" style="background-color: 0099CC">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">开通产品</h4>
				</div>
				<div class="modal-body">
					<table id="sample-table-2" class="list-table">
						<tr>
							<td class="td-left" >商户编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="merchantCode1" name="merchantCode1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="merchantCodeLab1"></label>
								</span>
							</td>
							<td class="td-left" >商户名称</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="custName1" name="custName1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="custNameLab1"></label>
								</span>
							</td>
							
						</tr>
						<tr>
							<td class="td-left" >商盟支付宝MCC</td>
							<td class="td-right" colspan="3">
									<select id="levelOne" onchange="getLevelTwo();">
									<option value="">--请选择一级类目--</option>
                                    <c:if test="${not empty sumpayMccList }">
                                        <c:forEach items="${sumpayMccList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.levelOne}"
                                                    value="${sumpayMcc.levelOne}">
                                                ${sumpayMcc.levelOne}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
							<td class="td-right" colspan="3">
								<select id="levelTwo" onchange="getLevelThree();">
								 <option value="">--请选择二级类目--</option>
								</select>
							</td>
							<td class="td-right" colspan="3">
								<select name="levelThree" id="levelThree">
	                                <option value="">--请选择三级类目--</option>
	                            </select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >商盟微信产品</td>
							<td class="td-right" colspan="3">
									<select id="levelOneWx" onchange="getLevelTwoWx();">
									<option value="">--请选择一级类目--</option>
                                    <c:if test="${not empty sumpayMccWXList }">
                                        <c:forEach items="${sumpayMccWXList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.levelOne}"
                                                    value="${sumpayMcc.levelOne}">
                                                ${sumpayMcc.levelOne}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
							<td class="td-right" colspan="3">
								<select id="levelTwoWx" onchange="getLevelThreeWx();">
								 <option value="">--请选择二级类目--</option>
								</select>
							</td>
							<td class="td-right" colspan="3">
								<select name="levelThreeWx" id="levelThreeWx">
	                                <option value="">--请选择三级类目--</option>
	                            </select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >外部商户号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 200px;"  id="outMerchantCode1" name="outMerchantCode1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="outMerchantCode1Lab"></label>
								</span>
							</td>
							<td class="td-left" >费率</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 200px;"  id="rate" name="rate" maxlength="300"  placeholder="请填写费率">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="rateLab"></label>
								</span>
							</td>
						</tr>
						<tr>
						<td class="td-left" >商盟支付宝产品</td>
							<td class="td-right" colspan="3">
									<select id="levelOneZfb" >
									<option value="">--请选择类目--</option>
                                    <c:if test="${not empty sumpayMccZFBList }">
                                        <c:forEach items="${sumpayMccZFBList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.mchntType}"
                                                    value="${sumpayMcc.mchntType}">
                                                ${sumpayMcc.type}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
						</tr>
						
					</table>
				</div>
				<div id="hiddenCheck_2" class="modal-footer">
					<button type="button" class="btn btn-primary contractMerchantBtn"  onclick="bottonContract1();">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

