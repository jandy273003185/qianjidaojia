<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@ page import="com.qifenqian.bms.basemanager.city.CityPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script src="<c:url value='/static/topayProfit/layui/layui.js'/>"></script>
<script src="<c:url value='/static/topayProfit/layui/layui.all.js'/>"></script>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>流程管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
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
	    	       	  			}else if(_this.attr('id')=="certAttribute2"){ 
	    	       	  				var ss = json.cardValidDate;
	    	       	  				$("#idTermStart").val(ss.substr(0,10));
	    	       	  				$("#idTermEnd").val(ss.substr(11,21));
	    	       	  			}
	    	   	        		 else if(_this.attr('id')=="businessPhoto"){                //营业执照
	    	       	  				$("#businessLicense").val(json.businessLicense);
	    	       	  				$("#businessTermStart").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("#businessTermEnd").val("长期");
	    	       	  				}else{
	    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#custAdd").val(json.legalAddress);
	    	       	  				$("#custName").val(json.companyName);
	    	       	  			}else if(_this.attr('id')=="bankCardPhoto"){                //银行卡
    	       	  					$("#compMainAcct").val(json.creditCardId);
    	       	  				
	    	       	  			}
	    	   				}
	    	   			}
		    	   	});
				}
			});
		}
	);
});

/**注册城市**/
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
/**注册区县**/
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


/*
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

/** 营业执照点击预览 **/
$('.businessPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("businessPhoto");
	return previewImage(divObj,imageObj,obj);
});

//营业执照上传
function showBusinessPhotoImage(file){
	commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
}


function addProcessBtn(){
	
	if(isNull($("#titleName")[0])){
		$("#titleNameLab").text("标题不能为空");
		$("#titleName").focus();
		return false;
	}

	if($("#titleName").val().length > 25){
		$("#titleNameLab").text("标题过长");
		$("#titleName").focus();
		return false;
	}

	if($("#partnerCustId").val().length > 25){
		$("#partnerCustIdLab").text("服务商名称过长");
		$("#partnerCustId").focus();
		return false;
	}

	if($("#custId").val().length > 25){
		$("#custIdLab").text("商户名称过长");
		$("#custId").focus();
		return false;
	}

	if($("#shopId").val().length > 25){
		$("#shopIdLab").text("商户门店名称过长");
		$("#shopId").focus();
		return false;
	}

	if($("#remark").val().length > 50){
		$("#remarkLab").text("备注过长");
		$("#remark").focus();
		return false;
	}

	if(isNull($("#model")[0])){
		$("#modelLab").text("规格不能为空");
		$("#model").focus();
		return false;
	}
	
	if(isNull($("#demandNum")[0])){
		$("#demandNumLab").text("数量不能为空");
		$("#demandNum").focus();
		return false;
	}
	
	if(isNull($("#remittance")[0])){
		$("#remittanceLab").text("是否打款不能为空");
		$("#remittance").focus();
		return false;
	}
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();

	var titleName =  $("#titleName").val();
	var processTime =  $("#processTime").val();
	var emergencyDegree =  $("#emergencyDegree").val();
	var partnerCustId =  $("#partnerCustId").val();
	var custId =  $("#custId").val();
	var shopId =  $("#shopId").val();
	var model =  $("#model").val();
	var demandNum =  $("#demandNum").val();
	var receiveTime =  $("#receiveTime").val();
	var remittance =  $("#remittance").val();
	var remark =  $("#remark").val();
	var mail =  $("#mail").val();
	var consignee =  $("#consignee").val();
	var deliveryAddress =  $("#deliveryAddress").val();
	var contact =  $("#contact").val();
	var attachment =  $("#businessPhotoPath").val();
	if("1" == mail){
		if(isNull($("#consignee")[0])){
			$("#consigneeLab").text("收货人不能为空");
			$("#consignee").focus();
			return false;
		}
		if(isNull($("#contact")[0])){
			$("#contactLab").text("收货人联系方式不能为空");
			$("#contact").focus();
			return false;
		}
		if(!isMobilePhone($("#contact")[0])){
			$("#contactLab").text("货人联系方式需用手机号 ");
			$("#contact").focus();
			return false;
		}
		if(isNull($("#deliveryAddress")[0])){
			$("#deliveryAddressLab").text("收货人联系地址不能为空");
			$("#deliveryAddress").focus();
			return false;
		}
		if(($("#deliveryAddress").val().length > 50)){
			$("#deliveryAddressLab").text("收货人联系地址过长");
			$("#deliveryAddress").focus();
			return false;
		}
	}
	$.blockUI();

    $.ajax({
        type : "POST",
        url : window.Constants.ContextPath +'<%="/process/add"%>',
        data :{
            "titleName"      :       titleName, 					// 标题
            "processTime"    :       processTime,                   // 申请时间
			"emergencyDegree":       emergencyDegree,               // 紧急程度
            "partnerCustId"  :       partnerCustId,					// 服务商客户号
            "custId"         :       custId,						// 商户客户号
            "shopId"         :       shopId, 						// 门店号
            "model"          :       model,                         // 规格型号
            "demandNum"      :       demandNum,						// 需求数量
            "receiveTime"    :       receiveTime,					// 最迟领取时间
            "remittance"     :       remittance,					// 是否打款
            "remark"         :       remark,						// 说明备注
            "mail"           :       mail,							// 是否邮寄
            "consignee"      :       consignee,                     // 收货人
            "contact"        :       contact,						// 收货人联系方式
            "deliveryAddress":       deliveryAddress,               // 收货人邮寄地址
            "attachment"     :       attachment					    // 附件
        },
        dataType : "json",
        success : function(data) {
            if (data.result == 'SUCCESS') {
            	$.gyzbadmin.alertSuccess("申请成功,请等待审核！",null,function(){
                    window.location.href = window.Constants.ContextPath + '<%="/process/apply" %>';
                });
            } else {
            	$.unblockUI();
            	$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员");
            }
        }
    });
}
</script>	

<body>
	<!-- 流程信息 -->
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
							<table id="sample-table-2" class="list-table" >
							<tbody>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">领取设备申请</td>
							</tr>
						
                        	<tr>
							    <td class="td-left" width="18%"><span style="color:red;">*</span>标题：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="titleName" name="" placeholder="申请领取设备型号数量" style="width:90%">
									<label class="label-tips" id="titleNameLab"></label>
								</td>
								<td class="td-left" width="18%">申请部门：</td>
								<td class="td-right" width="32%"> 
								    <input type="text" id="deptId" value="${queryBean.deptName }" style="width:90%" readonly>
								</td>
							</tr>
							<tr>
								<td class="td-left">申请人：</td>
								<td class="td-right">
									<input type="text" id="userName" value="${queryBean.realName }" style="width:90%" readonly>
								</td>
								<td class="td-left">申请日期：</td>
								<td class="td-right">
								<input type="text" id="processTime" readonly style="width:90%" value="${nowTime }">
								</td>
						 	</tr>
                         	<tr>
								<td class="td-left">紧急程度：</td>
								<td class="td-right">
									<select class="form-control" id="emergencyDegree" style="width:90%">
	                                    <option value="0">--正常--</option>
	                                    <option value="1">--重要--</option>
	                                    <option value="2">--紧急--</option>
	                                </select>
						        </td>
								<td class="td-left">服务商名称：</td>
								<td class="td-right">
									<input type="text" id="partnerCustId" name="" placeholder="输入服务商名称" style="width:90%">
								</td>
							 </tr>
	                         <tr>
								<td class="td-left">商户名称：</td>
								<td class="td-right">
									<input type="text" id="custId" name="" placeholder="输入商户名称" style="width:90%">
								</td>
								<td class="td-left">门店名称：</td>
								<td class="td-right">
									<input type="text" id="shopId" name="" placeholder="输入门店名称" style="width:90%">
								</td>
							 </tr>
	                         <tr>
								<td class="td-left"><span style="color:red;">*</span>规格/型号： </td>
								<td class="td-right">
								    <select class="form-control" id="model" style="width:90%">
	                                    <option value="" id="modelDef">--请选择--</option>
	                                    <option value="蜻蜓">--蜻蜓--</option>
	                                    <option value="青蛙">--青蛙--</option>
	                                    <option value="青蛙PRO">--青蛙PRO--</option>
	                                </select>
	                                <label class="label-tips" id="modelLab"></label>
								</td>
								<td class="td-left"><span style="color:red;">*</span>需求数量：</td>
								<td class="td-right">
									<input type="text" id="demandNum" name="" placeholder="输入需求数量" style="width:90%">
									<label class="label-tips" id="demandNumLab"></label>
								</td>
						 	</tr>
                         	<tr>
								<td class="td-left">最迟领取日期：</td>
								<td class="td-right">
									<input type="text" id="receiveTime" name="receiveTime" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
								</td>
								<td class="td-left"><span style="color:red;">*</span>是否打款：</td>
								<td class="td-right">
									<select class="form-control" id="remittance" style="width:90%">
	                                    <option value="">--请选择--</option>
	                                    <option value="0">--未打款--</option>
	                                    <option value="1">--打款--</option>
	                                </select>
	                                <label class="label-tips" id="remittanceLab"></label>
								</td>
						 	</tr>
                          	<tr>
								<td class="td-left">说明备注：</td>
								<td class="td-right">
									<input type="text" id="remark" name="" placeholder="" style="width:90%">
								</td>
								<td class="td-left">是否邮寄：</td>
								<td class="td-right">
									<select class="form-control" id="mail"style="width:90%">
	                                    <option value="0">--否--</option>
	                                    <option value="1">--是--</option>
	                                </select>
								    
								</td>
							 </tr>
	                         <tr>
								<td class="td-left">收货人：</td>
								<td class="td-right">
									<input type="text" id="consignee" name="" placeholder="输入收货人" style="width:90%">
									<label class="label-tips" id="consigneeLab"></label>
								</td>
								<td class="td-left">联系方式：</td>
								<td class="td-right">
									<input type="text" id="contact" name="" placeholder="输入联系方式" style="width:90%">
									<label class="label-tips" id="contactLab"></label>
								</td>
						 	 </tr>
						 	 <tr>
								<td class="td-left">收货地址：</td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0">
	                                   <select class="form-control" id="province" onchange="getCityList();">
	                                       <c:if test="${not empty provincelist_ }">
	                                        	<option value="">--请选择--</option>
							               <c:forEach items="${provincelist_ }" var="prov">
							                   <option value="${prov.provinceId }">${prov.provinceName }</option>
							               </c:forEach>
			               				</c:if>
	                                   </select>
										<label class="label-tips" id="provinceLab"></label>
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
	                                <select class="form-control" id="city" onchange="getAreaList();">
	                                    <option value="" id="cityDef">--请选择--</option>
	                                </select>
										<label class="label-tips" id="cityLab"></label>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0">
	                                <select class="form-control" id="country">
	                                    <option value="" id="areaDef">--请选择--</option>
	                                </select>
										<label class="label-tips" id="countryLab"></label>
	                                </div>
	                                <div class="col-xs-5 pd0" >
	                                    <input type="text" id="deliveryAddress" name="deliveryAddress"  placeholder="详细地址" style="width:100%">
	                                	<label class="label-tips" id="deliveryAddressLab"></label>
	                                </div>
								</td>
							</tr>
							<tr>
								<td class="td-left"><span style="color:red;">*</span>上传附件：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success businessPhotoClick" data-target="#previewImageModal">
										<label id="businessPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="businessPhotoImage" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="businessPhotoPath" name="businessPhotoPath" />
										<input type="hidden" id="businessPhotoImageVal02">  
										<input type="file" name="businessPhoto" id="businessPhoto" onChange="showBusinessPhotoImage(this)">
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							</tbody>
							</table>
							<div style="margin:50px 0 0 0;text-align:center">
								<button type="button" class="btn btn-primary" onclick="addProcessBtn()">提交</button> 
							</div>
						</div>
					</div><!-- /.modal -->
				</div><!-- /.page-content -->
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				    <div class="modal-dialog showDiv" >
	         			<div id="showImageDiv" style="width:100%;height:100%;">
				           <img id="showImage" style="width:100%;height:100%;">
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
										
				
  </body>
</html>