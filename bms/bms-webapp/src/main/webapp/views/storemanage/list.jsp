<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.StoreManagePath" %>
<%@ page import="java.util.ResourceBundle"%>  
<%-- <%ResourceBundle res = ResourceBundle.getBundle("uploadFileConfig"); %>  --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店列表</title>
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
</style>
</head>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script src='<c:url value="/static/js/jquery.qrcode.min.js?v=${_jsVersion}"/>'></script>
<script type="text/javascript">

$(document).ready(function(){
	/* $("#mchId").comboSelect(); */ 
	$("#merchantCodeAdd").comboSelect();
	var storeManageInfoLists = ${storeManageInfoList};
	var storeManageInfoList = $("tr.store");

		
	 $.each(storeManageInfoLists,function(i,value){
	 	$.data(storeManageInfoList[i],"store",value);
	 }); 

	 $('.clearStoreManageInControClr').click(function(){			
			$(".page-content #mchId").val("");
			$(".page-content #custName").val("");
			$(".page-content #shopNo").val("");
			$(".page-content #shopName").val("");
			
		}) 
		
	// 添加商户新门店 
	$(".addStoreManageBtn").click(function(){
		var mchId = $("#addStoreManageModal #merchantCodeAdd").val().trim();
		
		if(kong.test(mchId)) {
			$.gyzbadmin.alertFailure("商户编号不可为空");
			$("#addStoreManageModal #mchId").focus();
			return;
		}

		var shopName = $("#addStoreManageModal #shopName").val().trim();
		if(kong.test(shopName)) {
			$.gyzbadmin.alertFailure("门店名称不可为空");
			$("#addStoreManageModal #shopName").focus();
			return;
		}
		
		var addr = $("#addStoreManageModal #addr").val().trim();
	    if(kong.test(addr)) {
			$.gyzbadmin.alertFailure("门店地址不可为空");
			$("#addStoreManageModal #addr").focus();
			return;
		} 
		
		var contact = $("#addStoreManageModal #contact").val().trim();
		if(kong.test(contact)) {
			$.gyzbadmin.alertFailure("门店联系方式不可为空");
			$("#addStoreManageModal #contact").focus();
			return;
		}
	
		var status = $("#addStoreManageModal input[type='radio']:checked").val().trim();
			
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure("请选择状态");
			return;
		}
	
		
		$.blockUI();
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=StoreManagePath.BASE + StoreManagePath.ADD %>',
			data:
			{
				"mchId" 		: mchId,
			//	"shopNo" 		: shopNo,	
				"shopName" 		: shopName,
				"addr" 			: addr,
				"contact"		: contact,
				"status" 		: status
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addStoreManageModal").modal("hide");
						window.location.reload();
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
				}
			}
		});
	});


	//添加门店
	$(".addStoreManageLink").click(function(){

		 var store = $.data($(this).parent().parent()[0],"store");
		 
		 $('#addStoreManageModalS').on('show.bs.modal', function () {

				$("#addStoreManageModalS #mchId").val(store.mchId);
			    $("#addStoreManageModalS #custName").val(store.custName);
		 });
	}); 
		
	$(".addStoreManageBtns").click(function(){

		var mchId = $("#addStoreManageModalS #mchId").val().trim();
		if(kong.test(mchId)) {
			$.gyzbadmin.alertFailure("商户编号不可为空");
			$("#addStoreManageModalS #mchId").focus();
			return;
		}
		
		var custName = $("#addStoreManageModalS #custName").val().trim();
		if(kong.test(custName)) {
			$.gyzbadmin.alertFailure("商户不可为空");
			$("#addStoreManageModalS #custName").focus();
			return;
		}
		
		var shopName = $("#addStoreManageModalS #shopName").val().trim();
		
		if(kong.test(shopName)) {
			$.gyzbadmin.alertFailure("门店名称不可为空");
			$("#addStoreManageModalS #shopName").focus();
			return;
		}
		
		var addr = $("#addStoreManageModalS #addr").val().trim();
	    if(kong.test(addr)) {
			$.gyzbadmin.alertFailure("门店地址不可为空");
			$("#addStoreManageModalS #addr").focus();
			return;
		} 
		
		var contact = $("#addStoreManageModalS #contact").val().trim();
		if(kong.test(contact)) {
			$.gyzbadmin.alertFailure("门店联系方式不可为空");
			$("#addStoreManageModalS #contact").focus();
			return;
		}

		var status = $("#addStoreManageModalS input[type='radio']:checked").val().trim();
		
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure("状态");
			$("#addStoreManageModalS #status").focus();
			return;
		}
		
		var validate =false ;
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=StoreManagePath.BASE + StoreManagePath.ADD %>',
			data:
			{	
				"mchId"			: mchId,
				"custName" 		: custName,
			//	"shopNo" 		: shopNo,
				"shopName" 		: shopName,
				"addr" 			: addr,
				"contact"		: contact,
				"status" 		: status
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addStoreManageModalS").modal("hide");
						window.location.reload();
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
				}
			}
		});
			
	});
	 
	//编辑
	$(".updateStoreManageLink").click(function(){

		 var store = $.data($(this).parent().parent()[0],"store");
		 
		 $('#updateStoreManageModal').on('show.bs.modal', function () {

				$("#updateStoreManageModal #mchId").val(store.mchId);
				$("#updateStoreManageModal #shopId").val(store.shopId); 
			    $("#updateStoreManageModal #custName").val(store.custName);   
			 	$("#updateStoreManageModal #shopNo").val(store.shopNo);
			 	$("#updateStoreManageModal #shopName").val(store.shopName);
			 	$("#updateStoreManageModal #addr").val(store.addr);
			 	$("#updateStoreManageModal #contact").val(store.contact);
				$("input[name='status'][value='" + store.status + "']").attr("checked",true);  
		 });
		  $('#updateStoreManageModal').on('hide.bs.modal', function () {
			  	$("#updateStoreManageModal #mchId").val('');
			    $("#updateStoreManageModal #shopId").val('');
	 	    	$("#updateStoreManageModal #custName").val(''); 
				$("#updateStoreManageModal #shopNo").val('');
				$("#updateStoreManageModal #shopName").val('');
				$("#updateStoreManageModal #addr").val('');
				$("#updateStoreManageModal #contact").val('');
				$("#updateStoreManageModal input[name='status'][checked]").val('');
		  }); 
	});
	
	$(".updateStoreManageBtn").click(function(){

		var mchId = $("#updateStoreManageModal #mchId").val().trim();
		if(kong.test(mchId)) {
			$.gyzbadmin.alertFailure("商户编号不可为空");
			$("#updateStoreManageModal #mchId").focus();
			return;
		}
	
		var shopId = $("#updateStoreManageModal #shopId").val().trim();
		if(kong.test(shopId)) {
			$.gyzbadmin.alertFailure("门店名称不可为空");
			$("#updateStoreManageModal #shopId").focus();
			return;
		}
	
		var custName = $("#updateStoreManageModal #custName").val().trim();
		if(kong.test(custName)) {
			$.gyzbadmin.alertFailure("商户不可为空");
			$("#updateStoreManageModal #custName").focus();
			return;
		}
		
		var shopName = $("#updateStoreManageModal #shopName").val().trim();
		if(kong.test(shopName)) {
			$.gyzbadmin.alertFailure("门店名称不可为空");
			$("#updateStoreManageModal #shopName").focus();
			return;
		}
		
		var addr = $("#updateStoreManageModal #addr").val().trim();
	    if(kong.test(addr)) {
			$.gyzbadmin.alertFailure("门店地址不可为空");
			$("#updateMerchantModal #addr").focus();
			return;
		} 
		
		var contact = $("#updateStoreManageModal #contact").val().trim();
		if(kong.test(contact)) {
			$.gyzbadmin.alertFailure("门店联系方式不可为空");
			$("#updateStoreManageModal #contact").focus();
			return;
		}

		var status = $("#updateStoreManageModal input[type='radio']:checked").val().trim();
		
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure("请选择状态");
			return;
		}

		
		$.blockUI();
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=StoreManagePath.BASE + StoreManagePath.EDIT %>',
			data:
			{	
				"mchId"			: mchId,
				"shopId"        : shopId,
				"custName" 		: custName,
		//		"shopNo" 		: shopNo,
				"shopName" 		: shopName,
				"addr" 			: addr,
				"contact"		: contact,
				"status" 		: status
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("修改成功！",function(){
						$("#updateStoreManageModal").modal("hide");
						window.location.reload();
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("修改失败！"+data.message);
				}
			}
		});
	});

	$(".qifenqian_code_tc").click(function(){
		 $('#qrcode').empty();
		var store = $.data($(this).parent().parent()[0],"store");
		var shopNo =store.shopNo;
		var merchantCode = store.merchantCode;
		$("#buildMerchant #custName_tc").val(store.custName);
		$("#buildMerchant #shopNo_tc").val("NO." + store.shopNo);
		var path = "https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=" +merchantCode;
		if(shopNo == merchantCode){
			path = "https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=" +merchantCode;
		}else {
			path = "https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=" +merchantCode +"&sn=" + shopNo;
		}
		
		$("#qrcode").qrcode({
		  	render: "table", //table方式
			width: 200, //宽度
			height:200, //高度
			text: path 
			
			// https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=M9144030035873982X0 //正式环境链接
			// http://zt.qifenmall.com/pub/merchantqr.do?mid=M9144030035873982X0           //测试环境链接 
		}); 

	});
	
});

	<!--商家二维码 -->
    function showAgencyCode(obj) {
    	/* var store = $.data($(this).parent().parent()[0],"store");
		var shopNo =store.shopNo;
		var merchantCode = store.merchantCode; */
    	var mchId=$(obj).parent().find('#custId_Code').val();
    	var shopNo=$(obj).parent().find('#shopNo_Code').val();
    	var merchantCode=$(obj).parent().find('#merchantCode_Code').val();
    	    $("#custId_Old").val(mchId);
    	    $("#code_1").hide();
    		$("#code_2").show();
    		$("#hiddenOldDown").hide();
    		$("#downLoad").show();
		$.ajax({
   			dataType:"json",
   			url:window.Constants.ContextPath +'<%=StoreManagePath.BASE + StoreManagePath.QRCODE %>',
   	        data:{
   	   	        "mchId" : mchId,
   	   	  		"shopNo" : shopNo,
   	   	  		"merchantCode" : merchantCode
   	   	   	},
   	        success:function(data){
   	        	var url=data.url;
   	        	$("#url").val(url);
   	         var cust_url = $("#url").val();
   	         var custId=$("#custId_Old").val();
   	        
   	         $("#showNewRecode").attr('src',"<%=request.getContextPath()+StoreManagePath.BASE+ StoreManagePath.GETIMAGE %>?custId=" + custId + "&certifyType=re");
   	 	     qrcode(cust_url);
   	 	   function preview(file)  
   	 	      {  
   	 	    	 var prevDiv = document.getElementById('img-' + file.id);  
   	 	    	 if (file.files && file.files[0]){  
   	 	    		 var reader = new FileReader();  
   	 	    		 reader.onload = function(evt){  
   	 	    		 	prevDiv.innerHTML = '<img  style="width:100%;height:100%;" src="' + evt.target.result + '"   />'; 
   	 	    		 };
   	 	    	 	reader.readAsDataURL(file.files[0]);  
   	 	    	 } else{  
   	 	    	 	prevDiv.innerHTML = '<div style="width:100%;height:100%;" class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' 
   	 	    	 			+ file.value + '\'"></div>'; 
   	 	    	 }
   	 	    }  
   	 	     function check(obj){
   	 	    	var maxsize = '<%="5120000"%>';
   	 	    	var fileType = '<%=".jpg|.jpeg|.gif|.bmp|.png"%>'; 
   	 	     	if(!checkFileSize(maxsize,fileType,obj)){
   	 	     		var prevDiv = document.getElementById('img-' + obj.id);  
   	 	     		$(prevDiv).html('<a href="javascript:;" >点击上传</a>');
   	 	     	 	obj.value='';
   	 	     		return false;
   	 	     	  }
   	 	     	preview(obj);
   	 	      } 
   	 	     function qrcode(url){
   	 	     	$("#code_1").html("");
   	 	     	$("#code_1").qrcode(url);
   	 	        $("#code_3").qrcode(url);
   	 	     	var mycanvas = $("#code_1").find("canvas")[0];
   	 	     	var image = mycanvas.toDataURL("image/png");
   	 	     	$("#code_1").html("<img id='qr_img' download='' src='"+image+"' width='220' height='220'  alt='from canvas'/>");
   	 	      }
   	        },
   	     error: function(data) {
  	    	  
 	       }
   	     }); 
     
     }
    function exportCanvasAsPNG(canvas, fileName) {   
        var MIME_TYPE = "image/png";  
        var dlLink = document.createElement('a');  
        dlLink.download = fileName;  
        dlLink.href = canvas.toDataURL("image/png");  
        dlLink.dataset.downloadurl = [MIME_TYPE, dlLink.href].join(':');  
        document.body.appendChild(dlLink);  
        dlLink.click();  
        document.body.removeChild(dlLink);  
     }  
    function downloadPicture(){
        var radio = document.getElementsByName("newRecode");
        var custId=$("#custId_Old").val();
        	for (var i=0; i<radio.length; i++) {  
            if (radio[i].checked) {  
               var redioValue= radio[i].value;
               if(redioValue=="oldRecodeClass"){
            	   exportCanvasAsPNG($("#code_3").find("canvas")[0], "qrcoder.png");
               }
            }
           } 
	    }
       //判断浏览器类型
    function myBrowser(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        if (isOpera) {
            return "Opera"
        }; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "FF";
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1){
            return "Chrome";
        }
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } //判断是否Safari浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }; //判断是否IE浏览器
        if (userAgent.indexOf("Trident") > -1) {
            return "Edge";
        } //判断是否Edge浏览器
    }
 
    function SaveAs5(imgURL)
    {
        var oPop = window.open(imgURL,"","width=1, height=1, top=5000, left=5000");
        for(; oPop.document.readyState != "complete"; )
        {
            if (oPop.document.readyState == "complete")break;
        }
        oPop.document.execCommand("SaveAs");
        oPop.close();
    }
    
    function oDownLoad(url) {
    	 var odownLoad=document.getElementById("downLoad");
        myBrowser();
        if (myBrowser()==="IE"||myBrowser()==="Edge"){
            //IE
            odownLoad.href="#";
            var oImg=document.createElement("img");
            oImg.src=url;
            oImg.id="downImg";
            var odown=document.getElementById("down");
            odown.appendChild(oImg);
            SaveAs5(document.getElementById('downImg').src)
        }else{
            //!IE
            odownLoad.href=url;
            odownLoad.download="";
         }
     }  
   
   
	 function hiddenBackPic() {
	  $("#hiddenOldDown").show();
	  $("#code_1").show();
	  $("#code_2").hide();
	  $("#downLoad").hide();
	  }
	 
	 function showBackPic() {
		 $("#code_1").hide();
		 $("#code_2").show();
		 $("#downLoad").show();
		 $("#hiddenOldDown").hide();
	}  
	   	    
	 function downLoadCode() {
	     var custId=$("#custId_Old").val();
         oDownLoad("<%=request.getContextPath()+StoreManagePath.BASE+ StoreManagePath.RECODE %>?custId="+custId+"&certifyType=re");
   	 }
  




</script>

<!-- <body onload="loadQueryBean()"> -->
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
					<div class="row">
						<div class="col-xs-12">
						<!-- 查询条件 -->
						<form  id="storeForm" action='<c:url value="<%=StoreManagePath.BASE + StoreManagePath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户名称:</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td> 	
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>						
									<td class="td-left" >门店名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="shopName" id="shopName"  value="${queryBean.shopName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">门店编号：</td>
									<td class="td-right">
										<span class="input-icon">
											<input onkeyup="value=value.replace(/[^\d|^\w.//]/ig,'')" type="text" name="shopNo"   id="shopNo"  value="${queryBean.shopNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>																					
								</tr>
								<tr>
									<td colspan="10" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=StoreManagePath.BASE + StoreManagePath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											
											<button class="btn btn-purple btn-sm btn-margin clearStoreManageInControClr"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
									
											<gyzbadmin:function url="<%=StoreManagePath.BASE + StoreManagePath.ADD%>">
												<button class="btn btn-purple btn-sm " data-toggle='modal' data-target="#addStoreManageModal" >
													添加新门店
													<i class=" icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">门店列表</div>
						<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="display:none">门店唯一ID</th>
											<th >商户编号</th>
											<th >商户名称</th>
											<th >门店编号</th>
											<th >门店名称</th>
											<th >门店地址</th>
											<th >门店联系方式</th>
											<th >门店状态</th>
											<th >操作</th>
											<th >查看收款二维码</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${storeManageInfoList }" var="store">
										<tr class="store">
											<td style="display:none">${store.shopId }</td>
											<td>${store.merchantCode }</td>
											<td>${store.custName }</td>
											<td>${store.shopNo }</td>
											<td>${store.shopName }</td>
											<td>${store.addr }</td>
											<td>${store.contact }</td>
											<td>
												<c:if test="${store.status=='1'}">可用</c:if>
												<c:if test="${store.status=='0'}">不可用</c:if>
											</td>
											<td>
												<gyzbadmin:function url="<%=StoreManagePath.BASE + StoreManagePath.EDIT%>">
													<a href="#updateStoreManageModal" class="tooltip-success updateStoreManageLink" data-rel="tooltip" title="Edit" data-toggle='modal'>				
														<span class="green">
															【编辑】
															<!-- <i class="icon-edit bigger-120"></i> -->			
														</span>
													</a>
												</gyzbadmin:function>
												<%-- <gyzbadmin:function url="<%=StoreManagePath.BASE + StoreManagePath.DELETE %>">
													<a href="#deleteStoreManageModal" data-toggle='modal' class="tooltip-error deleteStoreManageLink" data-rel="tooltip" title="Delete">
														<span class="red">
															【删除】
															<!-- <i class="icon-trash bigger-120"></i> -->
														</span>
													</a>
												</gyzbadmin:function>  --%>
												<gyzbadmin:function url="<%=StoreManagePath.BASE + StoreManagePath.ADD%>">
													<a href="#addStoreManageModalS"  class="tooltip-success addStoreManageLink" data-toggle='modal'  data-rel="tooltip" title="Add">
														<span class="blue">
															【添加】
															<!-- <i class="icon-trash bigger-120"></i> -->
														</span>
													</a>
												</gyzbadmin:function>
											</td>
											<td>
												<!-- <button type="button"  data-toggle='modal' data-target="#buildMerchant"  class="btn btn-primary btn-xs qifenqian_code_tc" >收款二维码</button> --> 
												<input type="hidden" name="custId_Code" id="custId_Code" value="${store.mchId}">
												<input type="hidden" name="shopNo_Code" id="shopNo_Code" value="${store.shopNo}">
												<input type="hidden" name="merchantCode_Code" id="merchantCode_Code" value="${store.merchantCode}">
							  					<a href="javascript:;"  onclick="showAgencyCode(this)" data-toggle="modal" data-target="#myModal3">点击查看</a> 
											</td>
										</tr>
									</c:forEach>
										<c:if test="${empty storeManageInfoList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>			
						</div>
						<!-- 分页 -->
						<c:if test="${not empty storeManageInfoList}">
							<%@ include file="/include/page.jsp"%>
						</c:if>
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
	
	     <!-- 模态框（商户列表 收款二维码） -->
            <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <!-- <div class="modal-dialog wid560 mt100"> -->
                <div class="modal-dialog" style="width:500px">
                
                    <div class="modal-content">
                      <form>
                        <div class="modal-header">
                            <button type="button" class="close mt5" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">收款二维码</h4>
                        </div>
                        <div class="modal-body">
                        
                            <div class="row mt20">
                                 <div class="col-xs-3 pd0 text-center col-xs-offset-3"><label>
                                 	<input name="newRecode" onclick="showBackPic();" type="radio" checked="checked"  value="newRecodeClass"> 
                                 	七分钱样式
                                 </label></div>
                                 <div class="col-xs-3 pd0 text-center"><label>
                                 	<input name="newRecode" type="radio" onclick="hiddenBackPic();" value="oldRecodeClass"> 
                                 	原始样式
                                 </label></div>
                            </div>
                             <input type="hidden" name="url" id="url"  />
                             <input type="hidden" id="custId_Old" name="custId_Old">
                            <!-- 七分钱样式 -->
                              <div class="row">
                           	   <div id="code_1" style="display: none;margin-top:30px" class="col-xs-12 text-center">
                               <!-- <div id="code_1" style="display: none" class="col-xs-10 col-xs-offset-1 mt20 pd0 text-center"> -->
                               </div> 
                               <div id="code_2" class="col-xs-12 text-center" style="text-align:center;">
                                <img id="showNewRecode" alt="" src="" style="text-align:center;">
                               </div>
                               <div id="code_3" class="col-xs-12 text-center"  style="text-align:center; display: none">
                               </div>
                               <div class="col-xs-12 text-center">二维码收款可打印张贴在店门口</div>
                            </div>
                                
                        </div>
                        <div class="modal-footer text-right">
                            <a   id="downLoad" onclick="downLoadCode()" class="btn btn-primary btn-lg font14">下载</a>
                            <button id="hiddenOldDown" type="button" class="btn btn-primary btn-lg font14"  onclick="downloadPicture();" data-dismiss="modal">下载</button>
                        </div>
                        
                  </form>
                  </div>
                </div>
            </div>
	
	<!-- 添加商户新门店配置 -->  
	<div class="modal fade" id="addStoreManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加门店</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
						<tr>	
							<td class="td-left" width="30%">商户<span style="color:red">*</span></td>
							<td class="td-right" width="70%">	
								<select id="merchantCodeAdd" name="merchantCode">
									<option value="">输入商户名查询</option>
									<c:forEach items="${merchantList }" var="bean">
										<option value="${bean.custId }">${bean.custName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<!-- <tr>
							<td class="td-left">门店编号<span style="color:red">*</span></td>
							<td class="td-right">
								<input onkeyup="value=value.replace(/[^\d|^\w.//]/ig,'')" type="text" id="shopNo" name="shopNo" clasS="width-90">
								<label id="shopNoLabel" class="label-tips"></label>
							</td>
						</tr>	 -->
						<tr>
							<td class="td-left">门店名称<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="shopName"  id="shopName"  rows="1" clasS="width-90"></textarea>  
								<label id="shopNameLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店地址<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="addr" name="addr" clasS="width-90" >
								<label id="addrLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店联系方式<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="contact" id="contact" rows="1" clasS="width-90"></textarea>  
								<label id="contactLabel" class="label-tips"></label>
							</td>
						</tr>
					
						<tr>
							<td class="td-left">状态<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="radio" name="status" value="1" checked="checked" />可用 
								<input type="radio" name="status" value="0" />不可用
								<label id="statusLabel" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addStoreManageBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
	
	<!-- 修改门店配置 -->  
	<div class="modal fade" id="updateStoreManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改门店资料</h4>
		         </div>
		          <div class="modal-body">
		          	 <input type="hidden" id="mchId" name="mchId">
		         	<form action='<%=StoreManagePath.BASE + StoreManagePath.ADD %>' method="post" id="addadForm"> 
		            <table class="modal-input-table" style="width: 100%;">
		            	
						<tr>	
							<td class="td-left" width="30%">商户<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="custName" name="custName"  clasS="width-90" readonly>
								<label id="custNameLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr style="display:none">
							<td class="td-left"> <span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="shopId" name="shopId"  clasS="width-90">
								<label id="shopNoLabel" class="label-tips"></label>
							</td>
						</tr>	
						<tr>
							<td class="td-left">门店编号<span style="color:red">*</span></td>
							<td class="td-right">
								<input onkeyup="value=value.replace(/[^\d|^\w.//]/ig,'')" type="text" id="shopNo" name="shopNo" readonly="readonly"  clasS="width-90">
								<label id="shopNoLabel" class="label-tips"></label>
							</td>
						</tr>						
						<tr>
							<td class="td-left">门店名称<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="shopName"  id="shopName"  rows="1" clasS="width-90" ></textarea>  
								<label id="shopNameLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店地址<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="addr" name="addr" clasS="width-90" >
								<label id="addrLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店联系方式<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="contact" id="contact" rows="1" clasS="width-90"></textarea>  
								<label id="contactLabel" class="label-tips"></label>
							</td>
						</tr>					
						<tr>
							<td class="td-left">状态<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="radio" name="status" value="1"  />可用 
								<input type="radio" name="status" value="0" />不可用
								<label id="statusLabel" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateStoreManageBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->	
		
	<!-- 添加门店配置 -->  
	<div class="modal fade" id="addStoreManageModalS" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加门店资料</h4>
		         </div>
		          <div class="modal-body">
		          	 <input type="hidden" id="mchId" name="mchId"/>
		         	<form action='<%=StoreManagePath.BASE + StoreManagePath.ADD %>' method="post" id="addadForm"> 
		            <table class="modal-input-table" style="width: 100%;">
		            	
						<tr>	
							<td class="td-left" width="30%">商户<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="custName" name="custName"  clasS="width-90" readonly>
								<label id="custNameLabel" class="label-tips"></label>
							</td>
						</tr>
							
						<!-- <tr>
							<td class="td-left">门店编号<span style="color:red">*</span></td>
							<td class="td-right">
								<input onkeyup="value=value.replace(/[^\d|^\w.//]/ig,'')" type="text" id="shopNo" name="shopNo"  clasS="width-90">
								<label id="shopNoLabel" class="label-tips"></label>
							</td>
						</tr>		 -->				
						<tr>
							<td class="td-left">门店名称<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="shopName"  id="shopName"  rows="1" clasS="width-90"></textarea>  
								<label id="shopNameLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店地址<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="addr" name="addr" clasS="width-90" >
								<label id="addrLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">门店联系方式<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="contact" id="contact" rows="1" clasS="width-90"></textarea>  
								<label id="contactLabel" class="label-tips"></label>
							</td>
						</tr>					
						<tr>
							<td class="td-left">状态<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="radio" name="status" value="1" checked="checked" />可用 
								<input type="radio" name="status" value="0" />不可用
								<label id="statusLabel" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addStoreManageBtns">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->		
	 	
	<!-- 删除门店配置 -->
	<!-- <div class="modal fade" id="deleteStoreManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除门店配置</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该商户此门店?</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" name="shopId" id="shopId">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteStoreManageBtn">确定</button>
		         </div>
		      </div>/.modal-content
		     </div>
		</div>/.modal 
	-->	 
	<!-- 付款二维码 -->
	<div class="modal fade" style="z-index:1040;" id="buildMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:20%;z-index:90;">
			<div class="modal-content" style="width:250px;height:265px;" id="merchantDiv">
				<div id="qrcode" style="position: relative ;left:25px;top:15px; "></div>
				<br>
				<div style="text-align: center;">
					 <input type="text" id="shopNo_tc" name="shopNo" style="border-width: 0;text-align: center;">
				</div>
			</div>
		</div>
	</div>     	
</body>
</html>