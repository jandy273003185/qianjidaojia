<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/comm.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>
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
						
						<input type="hidden" id="openAccounttemp" />
						<input type="hidden" id="bankCardPhototemp" />
						<input type="hidden" id="netWorkPhototemp" />
						<div id="door_temp">
							<input type="hidden" id="doorPhototemp" />
						</div>
						<div class="list-table-header">商户注册</div>
						<table class="search-table" id="merchant_table">
						    <tr>
								<td class="td-left">商户类型<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="custType" style="width:100;" id="custType" onchange="selectBusiness();">
											<option value="0" selected>个体户</option>
											<option value="1">企业</option>
										</select>		
										<label class="label-tips" id="custTypeLab"></label>
									</span>
								</td>
							</tr>
							 <tr>
								<td class="td-left" >商户二维码编号（可选）</td>
								<td class="td-right" > 
									<span class="input-icon">
										<input type="text" id="merchantCode" name="merchantCode" maxlength="100" placeholder="商户二维码编号">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="merchantCodeLab"></label>
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
								<td class="td-left">商户名称<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="custName" name="custName" placeholder="商户名称" maxlength="100">
										<i class="icon-leaf blue"></i>
										<label id="custNameLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left">商户简称<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="custShopName" name="custShopName" placeholder="商户名称" maxlength="100">
										<i class="icon-leaf blue"></i>
										<label id="custShopNameLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left">商户角色<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="mchRole" style="width:150;" id="mchRole" onchange="roleChange();">
											<option value="" >- 请选择商户角色 -</option>
											<option value="0">线上商户</option>
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
									<select name="categoryTypeId" id="categoryTypeId" style="width:180px;" onchange="getCategoryList();" disabled="disabled">
										<option value="">--请选择类目类别--</option>
										<option value="100">实体</option>
										<option value="200">虚拟</option>
										<option value="300">其他</option>
									</select>
									<select name="categoryId" id="categoryId" style="width:300px;" onchange="getCategoryIdValue();">
										<option value="" id="category">--请选择类目--</option>
									</select>
									<label id="categoryIdLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							
							<tr>
								<td class="td-left">省市地区<span style="color:red">*</span></td>
								<td class="td-right">
									<div class="col-xs-4 pd0" style="padding:0">
                                    <select class="form-control" id="province" onchange="getCityList();">
                                        <c:if test="${not empty provinceList }">
                                         	<option value="qxz">--请选择--</option>
							               <c:forEach items="${provinceList }" var="prov">
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
									<span class="input-icon">
										<input type="text" id="custAdd" name="custAdd" placeholder="商户地址" maxlength="30" style="width:250px;">
										<i class="icon-leaf blue"></i>
										<label id="custAddLabel" class="label-tips"></label>
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
								<td class="td-left">联系人<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" id="agentName" name="agentName" placeholder="联系人" maxlength="30">
										<i class="icon-leaf blue"></i>
										<label id="agentNameLabel" class="label-tips"></label>
									</span>
								</td>
							</tr>
							<tr name="ent">
								<td class="td-left">营业执照注册号或统一社会信用代码<span style="color:red" class="businessLicenseSpan"></span></td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号或统一社会信用代码">
										<i class="icon-leaf blue"></i>
										<label class="label-tips" id="businessLicenseLab"></label>
									</span>
								</td>
							</tr>
							
							<tr name="ent">
								<td class="td-left">营业期限<span style="color:red" class="businessTermSpan"></span></td>
								
									<td class="td-right">
										<span class="input-icon">
											起始：<input type="text" name="businessTermStart"   id="businessTermStart" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermEnd\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
											
											终止：<input type="radio" checked="checked" name="end" value="sel"/><input type="text" name="businessTermEnd"   id="businessTermEnd" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermStart\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> <input type="radio" name="end" value="forever">长期
											<label id="businessTermStartLabel" class="label-tips"></label>
										</span>
									</td>
							</tr>
							<tr name="ent">
								<td class="td-left">营业执照扫描件<span style="color:red" class="businessPhotoImageSpan"></span></td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal">
										<label id="businessPhotoDiv" class="uploadImage">
											<img  id="businessPhotoImage" style="width:100%;height:100%; display: none" >
										</label>
									</a>
									<div style="float:left;margin-top:75" >
										<input type="file" name="businessPhoto" id="businessPhoto" /> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div> 
									<label class="label-tips" id="businessPhotoLabel" style="float:left;margin-top:88" ></label>
								</td>
							</tr>
							<tr>
								<td class="td-left" >手持身份证图片正面<span style="color:red">*</span></td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
									<label id="certAttribute1Div" class="uploadImage">  
									        <img  id="certAttribute1Image" style="width:100%;height:100%;display:none"/>
									</label>
									</a>
									<div style="float:left;margin-top:75" >
										<input type="file" name="certAttribute1" id="certAttribute1"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
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
									<input type="file" name="certAttribute2" id="certAttribute2"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
									<label class="label-tips" id="certAttribute2Label" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							
							<tr id="openAccount_" style="display: none">
								<td class="td-left" >开户许可证<span style="color:red">*</span></td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
										<label id="openAccountDiv2" class="uploadImage">  
										        <img  id="openAccountImage" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div style="float:left;margin-top:75" >
									<input type="file" name="openAccount" id="openAccount"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
									<label class="label-tips" id="openAccountLabel" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							
							<tr id="bankCardPhoto_">
								<td class="td-left" >银行卡照<span style="color:red"></span></td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv2" class="uploadImage">  
										        <img  id="bankCardPhotoImage" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div style="float:left;margin-top:75" >
									<input type="file" name="bankCardPhoto" id="bankCardPhoto"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
									<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
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
								<td class="td-left" >门头照<span style="color:red">*</span></td>
								<td class="td-right" > 
									<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal" >
										<label id="doorPhotoDiv2" class="uploadImage">  
										        <img  id="doorPhotoImage" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div style="float:left;margin-top:50px" >
									<input type="file" name="doorPhoto" id="doorPhoto"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
									<label class="label-tips" id="doorPhotoLabel" style="float:left;margin-top:88"></label>
									<!-- <input type="button" style="float:right;margin:50px" value="删      除" onclick="delDoorImg(this);" id="0" disabled="disabled"> -->
								</td>
							</tr>
							
						
							<tr id="certifyType_id">
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
									<input type="button" value="注　　册" id="registerTinyMerchant" class="registerTinyMerchant">
								</td>
							</tr>
						</table>
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

/******************************jQuery*******************************/
 
  //点击选择行业类目
   function getCategoryIdValue(){
	    var objS = document.getElementById("categoryId");
	    var grade = objS.options[objS.selectedIndex].value;
	    
	    if(grade=="177"){
	    	$("#netWorkPhoto_").attr("style","display:");
	    }else{
	    	$("#netWorkPhoto_").attr("style","display:none");
	    }
   }
 
 //个人类型 营业执照可选
 
 function selectBusiness(){
	if($("#custType").val() =='0'){
		//个人
		$(".businessLicenseSpan").text("");
		$(".businessTermSpan").text("");
		$(".businessPhotoImageSpan").text("");
		
		$("#bankCardPhoto_").attr("style","display:");
		$("#openAccount_").attr("style","display:none");
	}
	if($("#custType").val() =='1'){
		//企业
		$(".businessLicenseSpan").text("*");
		$(".businessTermSpan").text("*");
		$(".businessPhotoImageSpan").text("*");
		
		$("#bankCardPhoto_").attr("style","display:none");
		$("#openAccount_").attr("style","display:");
	}
	
}
 
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
 var door_id = 0;
$(function(){
	$(".tinymerchantinfo #fcontactunitNumber").prop("disabled",true);
	 /*  $("tr[name='ent']").hide();   */
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
			    	       	  				$("#custAdd").val(json.legalAddress);
			    	       	  				
			    	       	  			}else if(_this.attr('id')=="certAttribute2"){
			    	       	  				$("#certNoValidDate").val(json.cardValidDate);
			    	       	  			
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
});


function delDoorImg(obj){
	$("#trDoor"+obj.id).remove();
}

function createTr(){
    var tab=document.getElementById('merchant_table');
    var n=document.getElementById('certifyType_id').rowIndex;
    
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
 	'<input type="button" style="float:right;margin:50px" onclick="delDoorImg(this);" value="删      除" id="'+door_id+'" >';
	td2.setAttribute("class", "td-right");
	$("#door_temp").append(
		'<input type="hidden" id="doorPhoto'+door_id+'temp" />'
	);

}

function showDoorImgs(obj){
	var id_ = obj.id.substring(4);
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto"+id_);
	return previewImage(divObj,imageObj,obj); 
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
	
	 //判断是否门头照
     if(obj=="doorPhoto"+door_id){
   	   door_id++;
   	   createTr();
     }
	
	 var divObj = document.getElementById("showImageDiv");
	 var imageObj = document.getElementById(obj.id+"Image");
	return previewImage(divObj,imageObj,obj);
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
 
/** 提交 */
$("#registerTinyMerchant").click(function(){
	
	$(".label-tips").text("");
	var custType =$("#custType").val().trim();
	var businessTermStart = 	$("#businessTermStart").val();
	var businessTermEnd = "forever";
	
	var flag;
	// 验证二维码编号
	/* var $merchantCode = $(".tinymerchantinfo #merchantCode").val().trim();
	flag = Register.validateMerchantCode($merchantCode,$("#merchantCodeLab")); */
	<%-- if(flag){
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath + '<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATEMERCHANTCODE%>',
			data:{merchantCode:$merchantCode},
			success:function(data){
				//alert(data);
				if(data.result == "FAIL"){
					$.gyzbadmin.alertFailure("商户二维码编号已经被占用，请重新输入");
					//$("#merchantCodeLab").text("商户二维码编号已经被占用，请重新输入");
					flag = false;
				}
			}
		});
	} --%>
	//if(!flag){return false;}
	// 校验邮箱地址
	flag = Register.validateEmail($("#email").val().trim(),$("#emailLab"));
	if(!flag){
		//$("#email").focus();
		return false;
	}
	// 校验商户名称
	flag = Register.validateCustName($("#custName").val().trim(),$("#custNameLabel"));
	if(!flag){return false;}
	// 校验商户简称
	flag = Register.validateCustShopName($("#custShopName").val().trim(),$("#custShopNameLabel"));
	if(!flag){return false;}
	
	if(kong.test($("#mchRole").val().trim())){
		$("#mchRoleLab").text("商户角色不能为空");
		return false;
	}
	
	if(kong.test($("#categoryId").val().trim())){
		$("#categoryIdLabel").text("行目类别不能为空");
		return false;
	}
	if(kong.test($("#mchRole").val().trim())){
		$("#mchRoleLabel").text("商户角色不能为空！");
		return false;
	}
	if(kong.test($("#country").val().trim())){
		$("#countryLab").text("省份地区不能为空！");
		return false;
	}
	if(kong.test($("#custAdd").val().trim())){
		$("#custAddLabel").text("商户地址不能为空！");
		return false;
	}
	
	// 校验手机号码
	flag = Register.validatePhone($("#representativeMobile").val().trim(),$("#representativeMobileLabel"));
	if(!flag){return false;}
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath + '<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATEMOBILE%>',
		data:{mobile:$("#representativeMobile").val()},
		success:function(data){
			if(data.result == "FAIL"){
				$.gyzbadmin.alertFailure("手机号码已经被占用，请重新输入");
				flag = false;
			}
		}
	});
	if(!flag){return false;}
	// 校验银行卡号
	// flag = Register.validateCompMainAcct($("#compMainAcct").val().trim(),$("#compMainAcctLabel"));
	// if(!flag){return false;}
	// 校验联系人
	flag = Register.validateAgentName($("#agentName").val().trim(),$("#agentNameLabel"));
	if(!flag){return false;}
	
	if(custType=='0'){
		if(checkAttach($("#businessPhoto")[0])){
			if(isNull($("#businessLicense")[0])){
				$("#businessLicenseLab").text("必须填写营业执照注册号");
				return false;
			}
			
			// 校验营业时间
			if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){return false;}
			if($("input:radio[name='end']:checked").val()=='sel'){
				if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){return false;}
				businessTermEnd = $("#businessTermEnd").val();
			}
			var flag = Register.validateBusinessLicense($("#businessLicense").val(),$("#businessLicenseLab"));
			if(!flag){return false;}
		}
		
		if(!kong.test($("#businessLicense").val().trim())){
			if(!checkAttach($("#businessPhoto")[0])){
				$.gyzbadmin.alertFailure("必须上传营业执照扫描件");
				return false;
			}
		}
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

		if(!flag){return false;}

		<%-- if(!flag){return false;}else{

			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATELICENSE%>',
		        data:{License:businessLicense},
		        success:function(data){
		        	if(data.result=="FAIL"){
		        		$("#businessLicenseLab").text("该营业执照已经被注册，请重新输入");
		        		$("#businessLicense").val("");
		        		return false;
					}
				}
			});
		} --%>
		if(!checkAttach($("#businessPhoto")[0])){
			$.gyzbadmin.alertFailure("必须提交营业执照扫描件");
			return false;
		}
		
		if(!checkAttach($("#openAccount")[0])){
			$.gyzbadmin.alertFailure("必须提交开户许可证");
			return false;
		} 
	}/**else{
		if(!checkAttach($("#bankCardPhoto")[0])){
			$.gyzbadmin.alertFailure("必须提交手持银行卡照片");
			return false;
		} 
	}**/
	
	// 校验证件类型
	flag = Register.validateCertifyType($("select[name='certifyType']").val(),$("#certifyTypeLabel"),$("#certifyNo").val());
	if(!flag){return false;}
	// 校验身份证号码
	flag = Register.validateCertifyNo($("#certifyNo").val().trim(),$("#certifyNoLabel"),$("select[name='certifyType']").val());
	if(!flag){return false;}
	
	if(!checkAttach($("#certAttribute1")[0])){
		$.gyzbadmin.alertFailure("必须提交身份证正反面扫描件");
		return false;
	}
	if(!checkAttach($("#certAttribute2")[0])){
		$.gyzbadmin.alertFailure("必须提交身份证正反面扫描件");
		return false;
	}
	
	 if(!checkAttach($("#doorPhoto")[0])){
		$.gyzbadmin.alertFailure("必须提交门头照");
		return false;
	} 
	
	 if($("#categoryId").val()=="177"){
		 if(!checkAttach($("#netWorkPhoto")[0])){
				$.gyzbadmin.alertFailure("必须提交网络文化经营许可证");
				return false;
		} 
	 }
	 
	//法人身份证有效期
	var reg_ = /^[-.\d]+$/;
		
	if (!reg_.test($("#certNoValidDate").val()))
	{
		$("#certNoValidDateLabel").text("身份证有效期格式不正确（例：20160923-20360923或2016.09.23-2036.09.23）");
		$("#certNoValidDateLabel").focus();
		return false;
	}
	
	/* if(!checkAttach($("#bankCard")[0])){
		$.gyzbadmin.alertFailure("必须提交银行卡扫描件");
		return false;
	} */
	
	// 提交前校验，把错误扼杀在摇篮中
	/* var flag = Register.allErrorMsgLabelValue();
	if(flag){
		return false;
	} */
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	// 组装提交表单数据
	var merchantCode = 			$("#merchantCode").val().trim();
	var email = 				$("#email").val().trim();
	var custName = 				$("#custName").val().trim();
	var custShopName = 			$("#custShopName").val().trim();
	var representativeMobile = 	$("#representativeMobile").val().trim();
	//var fcontactunitNumber = 	$("#fcontactunitNumber").val().trim();
	var businessLicense = 		$("#businessLicense").val().trim();
	var certifyNo = 			$("#certifyNo").val().trim().trim();
	var certifyType = 			$("#certifyType").val();
	var agentName = 			$("#agentName").val().trim();
	
	var categoryId = 			$("#categoryId").val().trim();
	var mchRole =				$("#mchRole").val().trim();
	var custAdd =  				$("#custAdd").val().trim();
	//var compMainAcct = 			$("#compMainAcct").val().trim();
	var province = $("#province").val().trim();
	var city = $("#city").val().trim();
	var country = $("#country").val().trim();
	
	var imgDoor = [];
	$("#door_temp input[type='hidden']").each(function(){
		if($(this).val()!=""){
			imgDoor.push($(this).val());
		 }
	 });
	
	
	$.blockUI();
 	$.ajax({
		type : "POST",
		url : window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.FILEUPLOAD%>',
		data :{
			businessPhoto : $('#businessPhototemp').val(),
			certAttribute1 : $('#certAttribute1temp').val(),
			certAttribute2 : $('#certAttribute2temp').val(),
			doorPhoto : imgDoor,
			openAccount : $('#openAccounttemp').val(),
			bankCardPhoto : $('#bankCardPhototemp').val(),
			netWorkPhoto: $('#netWorkPhototemp').val()
			
		},
		dataType : "json",
		success : function(data) {
			if(data.result=='SUCCESS'){
				$.post(window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.REGISTER%>',{
        			"custId":data.custId,							// 回传custId
        			"merchantCode":merchantCode, 					// 商户二维码编号
        			"email":email, 									// 账户/电子邮件
        			"custName":custName, 							// 客户姓名
        			"shortName":custShopName,                       // 客户简称
        			"representativeMobile":representativeMobile, 	// 手机号码
        			//"fcontactunitNumber":fcontactunitNumber, 		// 往来单位编号
        			"businessLicense":businessLicense, 				// 营业执照注册号
        			"certifyType":certifyType,						// 证件类型
        			"certifyNo":certifyNo, 							// 身份证号
        			"custType":custType,							//客户类型 
       			//"compMainAcct":compMainAcct					 	// 银行卡号
        			"mobile":representativeMobile,					//登录表电话
        			"businessTermStart":businessTermStart,
        			"businessTermEnd" : businessTermEnd,
        			"categoryType" : categoryId,
        			"mchRole" : mchRole,
        			"custAdd" : custAdd,
        			"agentName":agentName,
        			"province":province,
        			"city":city,
        			"country" :country,
        			'certNoValidDate':$("#certNoValidDate").val()
        		},function(data){
    				if(data.result=="SUCCESS"){
    					$.gyzbadmin.alertSuccess("注册申请成功",null,function(){
    						window.location.href = window.Constants.ContextPath + '<%=TinyMerchantPath.BASE + TinyMerchantPath.LIST %>';
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
	
	<%-- $.ajaxFileUpload({  
        url : window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.FILEUPLOAD%>',
        secureuri : false,  // 是否启用安全提交，默认为false
        /* 需要上传的文件域的ID，即<input type="file">的ID */ 
        //fileElementId : ['businessPhoto','certAttribute1','certAttribute2','bankCard','otherPapers'], 
        fileElementId : ['businessPhoto','certAttribute1','certAttribute2'], 
        dataType : 'json', 
        success : function(data, status) {
        	$.unblockUI();
        	if(data.result=='SUCCESS'){
        		$.post(window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.REGISTER%>',{
        			"custId":data.custId,							// 回传custId
        			"merchantCode":merchantCode, 					// 商户二维码编号
        			"email":email, 									// 账户/电子邮件
        			"custName":custName, 							// 客户姓名
        			"representativeMobile":representativeMobile, 	// 手机号码
        			//"fcontactunitNumber":fcontactunitNumber, 		// 往来单位编号
        			"businessLicense":businessLicense, 				// 营业执照注册号
        			"certifyType":certifyType,						// 证件类型
        			"certifyNo":certifyNo, 							// 身份证号
        			"custType":custType,							//客户类型 
       			//"compMainAcct":compMainAcct					 	// 银行卡号
        			"mobile":representativeMobile,					//登录表电话
        			"businessTermStart":businessTermStart,
        			"businessTermEnd" : businessTermEnd,
        			"agentName":agentName
        		},function(data){
    				if(data.result=="SUCCESS"){
    					$.gyzbadmin.alertSuccess("注册申请成功",null,function(){
    						window.location.href = window.Constants.ContextPath + '<%=TinyMerchantPath.BASE + TinyMerchantPath.LIST %>';
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
/* 微商户二维码编号 */
$(".tinymerchantinfo #merchantCode").focus(function(){
	$(".tinymerchantinfo #merchantCodeLab").text("");
}).blur(function(){
	var $merchantCode = $(this).val().trim();
	var $merchantCodeLabel = $(".tinymerchantinfo #merchantCodeLab");
	var flag = Register.validateMerchantCode($merchantCode,$merchantCodeLabel);
	if(flag){
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath + '<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATEMERCHANTCODE%>',
			data:{merchantCode:$merchantCode},
			success:function(data){
				//alert(data);
				if(data.result == "FAIL"){
					$("#merchantCodeLab").text("商户二维码编号已经被占用，请重新输入");
				}
			}
		});
	}
});

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
			url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATEEMAIL%>',
	        data:{email:$email},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$emailLab.text("您要注册的邮箱已经被占用，请重新输入");
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

/** 商户简称验证 */
$("#custShopName").focus(function(){
	$("#custShopNameLabel").text("");
}).blur(function(){
	var $custShopName = $(this).val().trim();
	var $custShopNameLabel = $("#custShopNameLabel");
	Register.validateCustShopName($custShopName,$custShopNameLabel);
});



/** 手机号码验证 */
$("#representativeMobile").focus(function(){
	$("#representativeMobileLabel").text("");
}).blur(function(){
	var $representativeMobile = $(this).val().trim();
	var $representativeMobileLabel = $("#representativeMobileLabel");
	Register.validatePhone($representativeMobile,$representativeMobileLabel);
});
/**联系人 */
$("#agentName").focus(function(){
	$("#agentNameLabel").text("");
}).blur(function(){
	var $agentName = $(this).val().trim();
	var $agentNameLabel = $("#agentNameLabel");
	Register.validateCustName($agentName,$agentNameLabel);
});
/** 营业执照注册号 */
/* $("#businessLicense").focus(function(){
	$("#businessLicenseLab").text("");
}).blur(function(){
	var $businessLicense = $(this).val().trim();
	alert(businessLicense);
	var $businessLicenseLab = $("#businessLicenseLab");
	Register.validateBusinessLicense($businessLicense,$businessLicenseLab);
}); */

/** 银行卡号非空验证 */
/* $("#compMainAcct").focus(function(){
	$("#compMainAcctLabel").text("");
}).blur(function(){
	var $compMainAcct = $(this).val().trim();
	var $compMainAcctLabel = $("#compMainAcctLabel");
	Register.validateCompMainAcct($compMainAcct,$compMainAcctLabel);
}); */

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
	var $certifyType = $("select[name='certifyType']").val();
	Register.validateCertifyNo($certifyNo,$certifyNoLabel,$certifyType);
});

/** 营业执照注册号 */
<%-- $("#businessLicense").focus(function(){
	$("#businessLicenseLab").text("");
}).blur(function(){
	var $businessLicense = $(this).val().trim();
	var $businessLicenseLab = $("#businessLicenseLab"); 
	var flag = Register.validateBusinessLicense($businessLicense,$businessLicenseLab);
	if (flag) {
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.VALIDATELICENSE%>',
	        data:{businessLicense:$businessLicense},
	        success:function(data){
	        	if(data.result=="FAIL"){
	        		$businessLicenseLab.text("该营业执照已经被注册，请重新输入");
	        		$("#businessLicense").val("");
				}
			}
		});
	}
}); --%>
/********************图片预览***********************/
/** 营业执照预览 **/
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
/** 门头照背面点击预览 **/
$('.doorPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto");
	return previewImage(divObj,imageObj,obj); 
});

/** 门头照背面点击预览 **/
$('.doorPhoto0Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("doorPhoto");
	return previewImage(divObj,imageObj,obj); 
});



/** 开户许可证背面点击预览 **/
$('.openAccountClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("openAccount");
	return previewImage(divObj,imageObj,obj); 
});

/** 手持银行卡照背面点击预览 **/
$('.bankCardPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("bankCardPhoto");
	return previewImage(divObj,imageObj,obj); 
});


/**网络文化经营许可证背面点击预览 **/
$('.netWorkPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("netWorkPhoto");
	return previewImage(divObj,imageObj,obj); 
});


/*  $('select[name="custType"]').change(function(){
	if($(this).val()=='0'){
		$('tr[name="ent"]').hide();
	}else{
		$('tr[name="ent"]').show();
	}
});  */
/** 银行卡扫描件预览 **/
/* function showBankCardImage(obj){  
	 var divObj = document.getElementById("bankCardDiv");  
	 var imageObj = document.getElementById("bankCardImage");  
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;  
} */
/** 银行卡扫描件点击预览 **/
/* $('.bankCardClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("bankCard");
	return previewImage(divObj,imageObj,obj); 
}); */
/** 其他证件预览 **/  
/* function showOtherPapersImage(obj){  
	 var divObj = document.getElementById("otherPapersDiv");  
	 var imageObj = document.getElementById("otherPapersImage");  
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;  
} */
/** 其他证件点击预览 **/
/* $('.otherPapersClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("otherPapers");
	return previewImage(divObj,imageObj,obj); 
}); */
</script>
</html>	