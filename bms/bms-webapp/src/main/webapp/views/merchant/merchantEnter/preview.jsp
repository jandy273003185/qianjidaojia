<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="java.util.ResourceBundle"%>
<%-- <%ResourceBundle res = ResourceBundle.getBundle("uploadFileConfig"); %> --%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.StoreManagePath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.qrcode.min.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户录入预览</title>
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

	$(function() {

		//营业执照信息显示
		if($("#custType").val() =='0' ){
			//个人
			$("#businessPhotoImg_").attr("style","display:none");
			$("#businessPhoto_").attr("style","display:none");
		}
		if($("#custType").val() =='1' ||$("#custType").val() =='2' ){
			//企业
			$("#businessPhotoImg_").attr("style","display:");
			$("#businessPhoto_").attr("style","display:");
		}
		//结算卡显示
		if($("#compMainAcctType").val() =='01'){
			 $("#bankCardPhoto_").attr("style","display:none");
			 $("#openAccount_").attr("style","display:");
		}else{
			$("#bankCardPhoto_").attr("style","display:");
            $("#openAccount_").attr("style","display:none");
		}
		//显示授权二维码
		if($("#state").val() =='00' ){
			$("#recodeShow_").attr("style","display:");
			$("#recode_").attr("style","display:");
		}else{
			$("#recodeShow_").attr("style","display:none");
			$("#recode_").attr("style","display:none");
		}
		
		var custId = $("#custId").val();
		var authId = $("#authId").val();
		<%-- 
		$("#businessPhotoImageDiv").show();
		$("#bankCardPhotoImageDiv").show();
		$("#certAttribute1ImageDiv").show();
		$("#certAttribute2ImageDiv").show();
		$("#openAccountImageDiv").show();
		$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId); //07
		$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=07&authId="+authId); //08
		$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId); //04
		$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId); //04
		$("#openAccountImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId); //03
 		--%>
		var cust_url = 'https://openauth.alipay.com/oauth2/appToAppAuth.htm?&application_type=WEBAPP,MOBILEAPP&custId='
			+ custId +'&app_id=2018013002109989&redirect_uri=https%3A%2F%2Fwww.qifenqian.com%2Fenterprise%2Fpub%2Falipayauth.do';
        qrcode(cust_url);
		function qrcode(url){
			$("#code_2").html("");
			$("#code_2").qrcode(url);
			}

		function preview(file)
		{
			var prevDiv = document.getElementById('img-' + file.id);
			if (file.files && file.files[0]){
				var reader = new FileReader();
				reader.onload = function(evt){
					prevDiv.innerHTML = '<img  style="width:50%;height:50%;" src="' + evt.target.result + '"   />';
				};
				reader.readAsDataURL(file.files[0]);
			} else{
				prevDiv.innerHTML = '<div style="width:50%;height:50%;" class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
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
		};



    });


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
						<input type="hidden" id="bankCardPhototemp" />
						<input type="hidden" id="certAttribute1temp">
						<input type="hidden" id="certAttribute2temp">
						<input type="hidden" id="openAccounttemp" />
						<input type="hidden" name="state" id="state" value="${merchantVo.state}">
						<input type="hidden" name="custId" id="custId" value="${merchantVo.custId}">
						<input type="hidden" name="merchantCode" id="merchantCode" value="${merchantVo.merchantCode}">
						<input type="hidden" name="authId" id="authId" value="${merchantVo.authId}">
						<input type="hidden" name="custType" id="custType" value="${merchantVo.custType}">
						<input type="hidden" name="compMainAcctType" id="compMainAcctType" value="${merchantVo.compMainAcctType}">
						<table id="merchant_table" class="list-table">
							<tbody>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
							</tr>
							<tr></tr>
							<tr>
								<td class="td-left">商户账号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.email }</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left" style="color:#666;padding:10px 8px">商户类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0'}">
										<td>个人</td>
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
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">${merchantVo.custName }</td>
								<td class="td-left"  width="18%">商户简称：</td>
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">${merchantVo.shortName }</td>
							</tr>
							<tr>
								<td class="td-left">商户邮箱：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.merchantEmail }</td>
								<td class="td-left">客服号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactPhone }</td>
							</tr>

							<tr>
								<td class="td-left">商户地址：</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="province" onchange="getCityList();">
											<c:if test="${not empty provincelist_ }">
												<option value="">${merchantVo.provinceName }</option>
												<c:forEach items="${provincelist_ }" var="prov">
													<option value="${prov.provinceId }">${prov.provinceName }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
										<select class="form-control" id="city1" onchange="getAreaList();">
											<option value="" id="cityDef">${merchantVo.cityName }</option>
										</select>
									</div>
									<div class="col-xs-2 pd0" style="padding:0">
										<select class="form-control" id="country">
											<option value="" id="areaDef"> ${merchantVo.areaName }</option>
										</select>
									</div>
									<div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
										<input type="text" id="custAdd" name="custAdd" readonly value ="${merchantVo.custAdd }" style="width:100%">
										<label class="label-tips" id="custAddLab"></label>
									</div>
								</td>
							</tr>

							<tr id ="businessPhoto_" style= "display:"> 
								<td class="td-left">营业执照编号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.businessLicense }</td>
								<td class="td-left">营业执照有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.businessTermStart } -
									<c:choose>
										<c:when test="${merchantVo.businessTermEnd =='forever'}">
											长期
										</c:when>
										<c:when test="${merchantVo.businessTermEnd =='2099-12-31'}">
											长期
										</c:when>
										<c:when test="${merchantVo.businessTermEnd =='长期'}">
											长期
										</c:when>
										<c:otherwise>
											${merchantVo.businessTermEnd }
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr id ="businessPhotoImg_" style= "display:">
								<td class="td-left">营业执照扫描件：</td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bussinessPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">门头照：</td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >
										<label id="doorPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.doorPhotoPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">店内照：</td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success shopInteriorClick" data-target="#previewImageModal" >
										<label id="shopInteriorDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopInteriorPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
								<td class="td-left">店内前台照：</td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success shopCheckStandClick" data-target="#previewImageModal" >
										<label id="shopCheckStandDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.shopCheckStandPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left">所属业务人员：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.custManager }</td>
								<td class="td-left">所属代理商：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.agentName }</td>

							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeName }</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeCertNo }</td>
							</tr>
							<tr>
							<td class="td-left">身份证有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.idTermStart } -
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
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片正面：</td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click" data-target="#previewImageModal" >
										<label id="certAttribute1Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardOPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							
								<td class="td-left" >身份证图片背面：</td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success certAttribute2Click" data-target="#previewImageModal" >
										<label id="certAttribute2Div"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.idCardFPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactName }</td>
								<td class="td-left">联系人手机号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactMobile }</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
							<tr>
								<td class="td-left">银行卡号</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.compMainAcct }</td>
								<td class="td-left">开户人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankAcctName }</td>
							</tr>	
							<tr>
								<td class="td-left">开户省份：</td>
								<td class="td-right" style="color:#666;padding:10px 8px" id="province1">${merchantVo.bankProName }</td>
								<td class="td-left">开户城市：</td>
								<td class="td-right" style="color:#666;padding:10px 8px" id="city">${merchantVo.bankCitName }</td>
							</tr>
							<tr>
								<td class="td-left">银行类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankName }</td>
								<td class="td-left">开户行：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.branchBank }</td>
							</tr>
							<tr>
								<td class="td-left">银行联行号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.cnaps }</td>
								<td class="td-left">结算类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
                                    <c:choose>
                                        <c:when test="${merchantVo.compMainAcctType =='01'}">
                                            	对公
                                        </c:when>
                                        <c:otherwise>
                                            	对私
                                        </c:otherwise>
                                    </c:choose>
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
								</td>
							</tr>
							<tr id="bankCardPhoto_" style="display: ">
								<td class="td-left" >银行卡照<span style="color:red"></span></td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick" data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${picturePathVo.bankCardPath }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
									<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							<tr id="recode_" style="display:none">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">支付宝直连授权二维码</td>
							</tr>
							<tr id="recodeShow_" style="display:none">
                                <td class="td-left">支付宝直连授权二维码</td>
                                <td class="td-right" >
                                    <div style="display: none; width: 10px;height: 10px;" id="code_1" >
                                   </div>
                                    <div style="width:300px;height:300px; margin: 10 auto;" id="code_2" >
                                       <input type="file" value=${qrCode }/>
                                       <img id="showNewRecode" alt="" src="" style="text-align:center;">
                                    </div>
                                </td>
                            </tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">操作记录</td>
							</tr>
							<tr></tr>
							<tr>
								<td class="td-left">录入人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createName }</td>
								<td class="td-left">录入时间：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createTime }</td>
							</tr>
							<tr>
								<td class="td-left">审核人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.aduitUserName }</td>
								<td class="td-left">审核时间：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.auditTime }
								</td>
							</tr>
							<tr>
								<td class="td-left">审核记录：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									${merchantVo.aduitMessage }
								</td>
							</tr>
							
							<tr></tr>
							</tbody>
						</table>
						<div style="margin:50px 0 0 0;text-align:center">
							<button type="button"  class="btn btn-primary" onclick="exit()">关闭</button>
						</div>
					</div>
				</div>
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

<script type="text/javascript">



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

	/********************图片预览***********************/
	/** 营业执照预览 **/
	function showBusinessPhotoImage(obj){
		var divObj = document.getElementById("businessPhotoDiv");
		var imageObj = document.getElementById("businessPhotoImage");
		var obj = document.getElementById("businessPhoto");
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
	/** 店内照预览 **/
	function showShopInteriorImage(obj){
		 var divObj = document.getElementById("shopInteriorDiv");
		 var imageObj = document.getElementById("shopInteriorImage");
		 var result1 = previewImage(divObj,imageObj,obj);
		 return result1;
	}
	/** 店内照点击预览 **/
	$('.shopInteriorClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("shopInterior");
		return previewImage(divObj,imageObj,obj);
	});
	/** 店内收银台照点击预览 **/
	$('.shopCheckStandClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("shopCheckStand");
		return previewImage(divObj,imageObj,obj);
	});
	/** 门头照预览 **/
	function showDoorPhotoImage(obj){
		 var divObj = document.getElementById("doorPhotoDiv");
		 var imageObj = document.getElementById("doorPhotoImage");
		 var result1 = previewImage(divObj,imageObj,obj);
		 return result1;
	}
	/** 门头照点击预览 **/
	$('.doorPhotoClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("doorPhoto");
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

	/** 开户许可证预览 **/
	function showOpenAccountImage(obj){
		var divObj = document.getElementById("openAccountDiv");
		var imageObj = document.getElementById("openAccountImage");
		var result1 = previewImage(divObj,imageObj,obj);
		return result1;
	}
	/** 开户许可证背面点击预览 **/
	$('.openAccountClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("openAccount");
		return previewImage(divObj,imageObj,obj);
	});

	/** 银行卡预览 **/
	function showBankCardPhotoImage(obj){
		var divObj = document.getElementById("bankCardPhotoDiv");
		var imageObj = document.getElementById("bankCardPhotoImage");
		var result1 = previewImage(divObj,imageObj,obj);
		return result1;
	}
	/** 银行卡点击预览 **/
	$('.bankCardPhotoClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("bankCardPhoto");
		return previewImage(divObj,imageObj,obj);
	});


	function exit() {
		if (confirm("您确定要关闭吗？")) {
			window.opener=null;

			window.open("","_self");

			window.close();
		}
	};


</script>
</html>