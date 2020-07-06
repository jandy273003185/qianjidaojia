<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>服务商列表</title>
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

<body onload="loadfun()" style="overflow-x:hidden;">
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
						<input type="hidden" id="certAttribute1temp" />
						<input type="hidden" id="certAttribute2temp" />
						<input type="hidden" id="settlEmentCardtemp" />
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="/merchant/serviceParenter/list"/>' method="post">
							<input type="hidden" name="isFirst" value="${isFirst}"> 
							<table class="search-table">
								<tr>
									<td class="td-left" >服务商编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >服务商名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<%-- <td class="td-left" >协议状态：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="hidden" id="protocolStateTemp" value="${queryBean.protocolState}">
											<select id="protocolState" name="protocolState">
												<option value=''>--请选择--</option>
												<option value='VALID'>有效</option>
												<option value='DISABLE'>无效</option>
												<option value='AUDIT'>待审核</option>
												<option value='AUDIT_NO'>审核不通过</option>
												<option value='STOP'>终止</option>
											</select>
										</span>
									</td> --%>
								</tr>
								<tr>
									<%-- 
									<td class="td-left" >联系人：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="agentName" id="agentName"  value="${queryBean.agentName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td> --%>
									<td class="td-left" >时间：</td>
									<td>
										<input type="text" id="startTime" name="startTime" value="${queryBean.startTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
										<label class="label-tips" id="startTimeLabStart"></label>
											-
										<input type="text" id="endTime" name="endTime" value="${queryBean.endTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
										<label class="label-tips" id="endTimeLabEnd"></label>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="/merchant/serviceParenter/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<!-- <span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
											</span>  -->
										</span>
									</td>
								</tr>
							</table>
						</form>
						<div style="margin:30px 0 10px 0">
							<a href="<%=request.getContextPath() + "/merchant/serviceParenter/addPage"%>"  class="btn btn-primary" >新增</a> 
						</div>
						<div class="list-table-header">服务商列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="10%">服务商编号</th>
										<th width="10%">服务商名称</th>
										<!-- <th width="10%">服务产品及费率</th> -->
										<th width="7%">服务商类型</th>
										<th width="10%">联系人姓名</th>
										<th width="10%">联系人电话</th>
										<th width="10%">推荐人</th>
										<th width="10%">服务商级别</th>
										<th width="5%">状态</th>
										<th width="12%">创建时间</th>
										<th width="23%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${agencyList }" var="merchant" varStatus="i">
									
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<td>${merchant.custName }</td>
											<%-- <td>${merchant.agentRate }</td> --%>
											<td>${merchant.custType }</td>
											
											<td>${merchant.contactName }</td>
											<td>${merchant.contactMobile }</td>
											
											<td>${merchant.referrer }</td>
											<td>${merchant.serviceLevel }</td>	
											<td>
												<c:choose>
													<c:when test="${merchant.state =='00'}">
														有效
													</c:when>
													<c:when test="${merchant.state =='01'}">
														待审核
													</c:when>
													<c:when test="${merchant.state =='02'}">
														注销
													</c:when>
													<c:when test="${merchant.state =='03'}">
														冻结
													</c:when>
													<c:when test="${merchant.state =='04'}">
														审核不通过
													</c:when>
												</c:choose>
											</td>
											<td>${merchant.createTime}</td>
											<td>	
												<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}"> 
												<a href="<%=request.getContextPath() + "/merchant/serviceParenter/showPage?custId="%>${merchant.custId}"  class="btn btn-primary btn-xs qifenqian_view_tc" >浏览</a> 	
												<c:if test="${merchant.state =='01'}">
													<a href="<%=request.getContextPath() + "/merchant/serviceParenter/auditPage?custId="%>${merchant.custId}"  class="btn btn-primary btn-xs qifenqian_view_tc" >审核</a> 
												</c:if>
													<a href="<%=request.getContextPath() + "/merchant/serviceParenter/updatePage?custId="%>${merchant.custId}"  class="btn btn-primary btn-xs qifenqian_view_tc" >修改</a> 	
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty agencyList}">
										<tr>
											<td colspan="15" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty agencyList}">
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
	

	
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal"  aria-hidden="true">
		<div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
	    	<div id="showImageDiv" style="width:100%;height:100%;">
	        	<img id="showImage" style="width:100%;height:100%;">
	        </div>
	     </div>
	</div>        
</body>

<script type="text/javascript">
$(function(){
	/** 缓存 **/
	var custIds = ${agencyList };
	var custId = $("tr.merchant");
	$.each(custIds,function(i,value){
		$.data(custId[i],"merchant",value);
	});
	
});


/** 修改弹出层修改 **/
$(".qifenqian_update_tc").click(function(){
	$("#updateAgency .tempBusinessLicense").show();
	$("#updateAgency .tempBusinessLicenseTime").show();
	$("#updateAgency .tempBusinessPhoto").show();
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	$("#custNameLabel").text("");
	$("#representativeMobileLabel").text("");
	$("#certifyTypeLabel").text("");
	$("#certifyNoLabel").text("");
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	$("#tc_test").val(merchant.custId);
	$("#updateAgency #agentName").val(merchant.agentName);
	$("#updateAgency #email_04").val(merchant.email);
	$("#updateAgency #merchantCode_tc").val(merchant.merchantCode);
	$("#updateAgency #custName_tc").val(merchant.custName);
	$("#updateAgency #representativeMobile").val(merchant.mobile);
	$("#updateAgency #businessLicense").val(merchant.businessLicense);
	$("#updateAgency #businessLicenseHiddenData").val(merchant.businessLicense);
	$("#updateAgency #certifyType").val(merchant.certifyType);
	$("#updateAgency #certifyNo").val(merchant.certifyNo);
	$("#updateAgency #custType").val(merchant.custType);
	//$("#updateAgency #compMainAcct").val(merchant.compMainAcct);
	if(merchant.custType == '个人'){
		$("#updateAgency .tempBusinessLicense").hide();
		$("#updateAgency .tempBusinessPhoto").hide();
		$("#updateAgency .tempBusinessLicenseTime").hide();
	}
});
/** 修改弹出层预览 */
$(".qifenqian_view_tc").click(function(){
	$("#updateAgency .tempBusinessLicense").show();
	$("#updateAgency .tempBusinessLicenseTime").show();
	$("#updateAgency .tempBusinessPhoto").show();
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	
	$("#updateAgency #agentName").val(merchant.agentName);
	$("#updateAgency #email_04").val(merchant.email);
	$("#updateAgency #merchantCode_tc").val(merchant.merchantCode);
	$("#updateAgency #custName_tc").val(merchant.custName);
	$("#updateAgency #representativeMobile").val(merchant.mobile);
	$("#updateAgency #businessLicense").val(merchant.businessLicense);
	$("#updateAgency #businessLicenseHiddenData").val(merchant.businessLicense);
	$("#updateAgency #certifyType").val(merchant.certifyType);
	$("#updateAgency #certifyNo").val(merchant.certifyNo);
	
	//$("#updateAgency #compMainAcct").val(merchant.compMainAcct);
	if(merchant.custType == '个人'){
		$("#updateAgency .tempBusinessLicense").hide();
		$("#updateAgency .tempBusinessPhoto").hide();
		$("#updateAgency .tempBusinessLicenseTime").hide();
	}
});

$('input:radio[name="end"]').change(function(){
	if($("#endSel").is(':checked')){
		$('#updateAgency #businessTermEnd').prop("disabled", false); 
	}else{
		$('#updateAgency #businessTermEnd').prop("disabled", true); 
		$('#updateAgency #businessTermEnd').val("");
	}
	
});
function loadfun(){
	$("#merchantState").val($("#stateTemp").val());
	$("#protocolState").val($("#protocolStateTemp").val());
}



/** 导出服务商列表 */
$(".exportBut").click(function(){
	var merchantCode = $('.search-table #merchantCode').val().trim();
	var custName = $('.search-table #custName').val().trim();
	var protocolState = $('.search-table #protocolState').val().trim();	
	var agentName = $('.search-table #agentName').val().trim();	
	var src ="<%= request.getContextPath() + AgencyPath.BASE + AgencyPath.EXPORTAGENCYINFO%>?merchantCode=" + merchantCode + "&custName=" + custName + "&agentName=" + agentName+"&protocolState="+protocolState;
	$(".exportBut").attr("href",src);
});

/******************* 非空验证 *******************/
/** 商户名称验证 */
$("#custName_tc").focus(function(){
	$("#custNameLabel").text("");
}).blur(function(){
	var $custName = $(this).val().trim();
	var $custNameLabel = $("#custNameLabel");
	Register.validateCustName($custName,$custNameLabel);
});

/** 手机号码验证 */
$("#representativeMobile").focus(function(){
	$("#representativeMobileLabel").text("");
}).blur(function(){
	var $representativeMobile = $(this).val().trim();
	var $representativeMobileLabel = $("#representativeMobileLabel");
	Register.validatePhone($representativeMobile,$representativeMobileLabel);
});

/** 营业执照注册号 */
$("#businessLicense").focus(function(){
	$("#businessLicenseLab").text("");
}).blur(function(){
	var $businessLicense = $(this).val().trim();
	var $businessLicenseLab = $("#businessLicenseLabel");
	Register.validateBusinessLicense($businessLicense,$businessLicenseLab);
});

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

/*************** 图片预览 ***************/
/** 营业执照扫描件 */
function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}
/** 身份证正面 */
function showCertAttribute1Image(obj){  
	 var divObj = document.getElementById("certAttribute1Div");  
	 var imageObj = document.getElementById("certAttribute1ImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}
/** 身份证背面 */
function showCertAttribute2Image(obj){  
	 var divObj = document.getElementById("certAttribute2Div");  
	 var imageObj = document.getElementById("certAttribute2ImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}

/** 结算银行卡 */
function showsettlEmentCardImage(obj){  
	 var divObj = document.getElementById("settlEmentCardDiv");  
	 var imageObj = document.getElementById("settlEmentCardPhotoDiv");  
	 return previewImage(divObj,imageObj,obj);  
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

/** 清空按钮 **/
$('.clearMerchantSearch').click(function(){
	$('.search-table #merchantCode').val('');
	$('.search-table #custName').val('');
//	$('.search-table #email').val('');
//	$('.search-table #agentName').val('');
	$('.search-table #startTime').val('');
	$('.search-table #endTime').val('');
}); 

/** 验证条件组合查询之注册时间 */
$('.buttonSearch').click(function(){
	var startDate = $("#startCreateTime").val().trim();
	var endDate= $("#endCreateTime").val().trim();
	if("" != startDate && "" != endDate && startDate > endDate) {
		$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
		return false;
	}
	var form = $('#merchantForm');
	form.submit();
});


</script>
</html>