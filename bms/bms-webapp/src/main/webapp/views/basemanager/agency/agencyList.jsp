<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代理商列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=AgencyPath.BASE + AgencyPath.LIST %>"/>' method="post">
							<input type="hidden" name="isFirst" value="${isFirst}"> 
							<table class="search-table">
								<tr>
									<td class="td-left" >代理商编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >协议状态：</td>
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
									</td>
								</tr>
								<tr>
									<td class="td-left" >代理商名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >联系人：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="agentName" id="agentName"  value="${queryBean.agentName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AgencyPath.BASE + AgencyPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
											</span> 
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代理商列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="10%">代理商编号</th>
										<!-- <th width="10%">账号</th> -->
										<th width="10%">代理商名称</th>
										<th width="10%">代理产品及费率</th>
										<th width="10%">代理商类型</th>
										<th width="10%">手机号码</th>
										<th width="10%">联系人</th>
										<th width="10%">开户名</th>
										<th width="10%">开户账号</th>
										<th width="10%">开户行</th>
										<th width="10%">客户经理</th>
										<th width="10%">协议生效时间</th>
										<th width="10%">协议失效时间</th>
										<th width="10%">协议状态</th>
										
										<!-- <th width="12%">注册时间</th> -->
										<th width="12%">状态</th>
										<!-- <th width="10%">银行开户名</th> -->		
										<th width="23%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${agencyList }" var="merchant" varStatus="i">
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<%-- <td>${merchant.email }</td> --%>
											<td>${merchant.custName }</td>
											<td>${merchant.agentRate }</td>
											<td>${merchant.custType }</td>
											<td>${merchant.mobile}</td>
											<td>${merchant.agentName }</td>
											<td>${merchant.bankCardName }</td>
											<td>${merchant.bankCardNo }</td>
											<td>${merchant.bankName }</td>
											<td>${merchant.custManager }</td>
											<td>${merchant.validDate }</td>
											<td>${merchant.disableDate }</td>
											<td>${merchant.protocolState }</td>
											<%-- <td>${merchant.createTime}</td> --%>
											<td>
												${merchant.merchantState}
											</td>
											<%-- <td>${merchant.bankAcctName }</td> --%>
											<!-- <td>测试账户</td> -->
											<td>	
												<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}"> 
												
												<gyzbadmin:function url="<%=AgencyPath.BASE + AgencyPath.UPDATEAGENCYINFO%>">  	
													<button type="button" onclick="updateAgencyInfo(this,'edit')" data-toggle='modal' data-target="#updateAgency" class="btn btn-primary btn-xs qifenqian_update_tc" >修改代理商信息</button>
												</gyzbadmin:function>
                                            	
                                            	<button type="button" onclick="updateAgencyInfo(this,'preview')" data-toggle='modal' data-target="#updateAgency" class="btn btn-primary btn-xs qifenqian_view_tc" >详情</button> 
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
	
	<div class="modal fade" style="z-index:1040;" id="updateAgency" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:60%;z-index:90;" >
			<div class="modal-content" style="width:950px;" id="merchantDiv">
		    	<div class="modal-header" style="background-color:0099CC">
		        	<button type="button" class="close" data-dismiss="modal" idupdateAgencyntClose" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">代理商信息</h4>
		        </div>
		        <div class="modal-body">
					<table id="sample-table-2" class="list-table" >
						<tr class="edit_email">
							<td class="td-left" width="18%">邮箱：</td>
							<td class="td-right" width="32%"> 
								<input type="text" id="email_04" name="email" readonly="readonly" style="width:35%">
								<input type="hidden" name="custName_id_01" id="custName_id_01"  />
							</td>
						</tr>
						<!-- spring 20170407 13:50 pm add -->
						<tr class="edit_merchantCode">
						 	<td class="td-left">代理商编号：</td>
						 	<td class="td-right" colspan="3">
						 		<input type="text" id="merchantCode_tc" name="merchantCode" readonly="readonly" placeholder="商户编号" style="width:35%">
						 		<i class="icon-leaf blue"></i>
								<label class="label-tips" id="merchantCodeLabel"></label>
						 	</td>
						</tr>
						<tr>
						 	<td class="td-left">代理商名称：</td>
						 	<td class="td-right" colspan="3">
						 		<input type="text" id="custName_tc" name="custName" placeholder="客户姓名" style="width:35%">
						 		<i class="icon-leaf blue"></i>
								<label id="custNameLabel" class="label-tips"></label>
						 	</td>
						</tr>
						<tr>
						 	<td class="td-left">联系人：</td>
						 	<td class="td-right" colspan="3">
						 		<input type="text" id="agentName" name="agentName" placeholder="联系人" style="width:35%">
						 		<i class="icon-leaf blue"></i>
								<label id="agentNameLabel" class="label-tips"></label>
						 	</td>
						</tr>
						<tr>
							<td class="td-left" >手机号码：</td>
							<td class="td-right" colspan="3"> 
								<input type="text" id="representativeMobile"  name="representativeMobile" placeholder="手机号码" style="width:35%">
								<i class="icon-leaf blue"></i>
								<label id="representativeMobileLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr class="edit_hide_fcontactunitnumber">
							<td class="td-left">往来单位编号：</td>
							<td class="td-right">
								<input type="text" id="fcontactunitNumber" name="fcontactunitNumber" placeholder="往来单位编号" style="width:35%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="fcontactunitNumberLabel"></label>
							</td>
						</tr>
						<tr class="tempBusinessLicense">
							<td class="td-left">营业执照号码或统一社会信用代码：</td>
							<td class="td-right">
								<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号" style="width:35%">
								<input type="hidden" id="businessLicenseHiddenData" name="businessLicense" >
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="businessLicenseLabel"></label>
							</td>
						 </tr>
						 <tr class="tempBusinessPhoto">
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
						<tr class="tempBusinessLicenseTime">
							<td class="td-left">营业期限</td>
							<td class="td-right">
							<span class="input-icon">
								起始：<input type="text" name="businessTermStart"   id="businessTermStart" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermEnd\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								
								终止：<input type="radio" id="endSel" name="end" value="sel"/>
								<input type="text" name="businessTermEnd"   id="businessTermEnd" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermStart\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								<input type="radio" id="endForever" name="end" value="forever">长期
								<label id="businessTermStartLabel" class="label-tips"></label>
							</td>
							</span>
						 </tr>
						<tr>
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
							<td class="td-left" >结算银行卡照片：</td>
							<td class="td-right" colspan="3"> 
								<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
									<label id="settlEmentCardDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										<img  id="settlEmentCardPhotoDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
									</label>
								</a>
								<div class="updateImageDiv" style="float:left; margin-top:75" >
									<input type="file" name="settlEmentCard" id="settlEmentCard" onchange="showsettlEmentCardImage(this)"/> 
									<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
							</td>
						</tr>
						
						
						
						<tr>
							<td class="td-left" >证件类型</td>
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
						 	<td class="td-left">身份证号码：</td>
						 	<td class="td-right"  colspan="3"> 
								<span class="input-icon">
									<input type="text" id="certifyNo" name="certifyNo" placeholder="身份证号码"/>
									<i class="icon-leaf blue"></i>
									<label id="certifyNoLabel" class="label-tips"></label>
								</span>
							</td>
						</tr>
					</table>
		        </div>
		        <div class="modal-footer">
		        	<input type="hidden" id="tc_test" name="tc_test" value="" />
		        	<input type="hidden" id="custType" name="custType" />
					<button type="button" id="" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>
		           	<button type="button" class="btn btn-primary updateAgencyBtn" onclick="confirmAgencyInfo()">提交</button>
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
</body>

<script type="text/javascript">

$(function(){
	/** 缓存 **/
	var custIds = ${agencyList };
	var custId = $("tr.merchant");
	$.each(custIds,function(i,value){
		$.data(custId[i],"merchant",value);
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
				    	       	  				$("#updateAgency #agentName").val(json.cardName);
				    	       	  				$("#updateAgency #certifyNo").val(json.cardId);
				    	       	  			}else if(_this.attr('id')=="businessPhoto"){//营业执照
				    	       	  				$("#updateAgency #businessLicense").val(json.businessLicense);
				    	       	  				$("#updateAgency #businessTermStart").val(json.businessTermStart);
				    	       	  				if("长期"==json.businessTermEnd){
				    	       	  					$("input[value='forever']").click();
				    	       	  				}else{
				    	       	  					$("#updateAgency #businessTermEnd").val(json.businessTermEnd);
				    	       	  				} 
				    	       	  				//$("#businessRegAddr").val(json.legalAddress);
				    	       	  				
				    	       	  			} 
				    	   				}
				    	   			}
				    	   		});
			             
			             
					}
				});
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
	$("#updateAgency #fcontactunitNumber").val(merchant.fcontactunitNumber);
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
	$("#updateAgency #fcontactunitNumber").val(merchant.fcontactunitNumber);
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
/** 更新代理商信息提交 **/
function confirmAgencyInfo(){
	var flag;
	// 校验联系人
	flag = Register.validateCustName($("#updateAgency #agentName").val().trim(),$("#agentNameLabel"));
	if(!flag){return false;}
	// 校验商户名称
	flag = Register.validateCustName($("#updateAgency #custName_tc").val().trim(),$("#custNameLabel"));
	if(!flag){return false;}
	// 校验手机号码
	flag = Register.validatePhone($("#updateAgency #representativeMobile").val().trim(),$("#representativeMobileLabel"));
	if(!flag){return false;}
	
	if($("#updateAgency #custType").val() == '企业'){
		// 校验营业执照注册号
		if ($("#updateAgency #businessLicense").val().trim() != "" && $("#updateAgency #businessLicense").val() != null) {
			flag = Register.validateBusinessLicense($("#updateAgency #businessLicense").val().trim(),$("#updateAgency #businessLicenseLabel"));
			if(!flag){return false;}
		}
		
		var startTime = $("#updateAgency #businessTermStart").val();
		if(kong.test(startTime)) {
			$.gyzbadmin.alertFailure('营业期限起始时间不能为空');
			return;
		}
		var endTime;
		if($("#endSel").is(':checked')){
			 endTime = $("#updateAgency #businessTermEnd").val();
			if(kong.test(endTime)) {
				$.gyzbadmin.alertFailure('营业期限终止时间不能为空');
				return;
			}
		}else{
			endTime = 'forever';
		}
	}
	
	// 校验证件类型
	flag = Register.validateCertifyType($("#updateAgency  select[name='certifyType']").val(),$("#updateAgency #certifyTypeLabel"),$("#updateAgency #certifyNo").val());
	if(!flag){return false;}
	// 校验身份证号码
	flag = Register.validateCertifyNo($("#updateAgency #certifyNo").val().trim(),$("#updateAgency #certifyNoLabel"),$("#updateAgency select[name='certifyType']").val());
	if(!flag){return false;}
	
	
	if($("#updateAgency #businessLicense").val() != $("#updateAgency #businessLicenseHiddenData").val()){
		if(!checkAttach($("#updateAgency #businessPhoto")[0])){
			$.gyzbadmin.alertFailure("由于营业执照已修改，必须重新提交营业执照扫描件");
			return false;
		}
	}
	if(checkAttach($("#updateAgency #businessPhoto")[0])){
		if(isNull($("#updateAgency #businessLicense")[0])){
			$("#businessLicenseLabel").text("必须填写营业执照号码或统一社会信用代码");
			return false;
		}
		var flag = Register.validateBusinessLicense($("#updateAgency #businessLicense").val().trim(),$("#updateAgency #businessLicenseLab"));
		if(!flag){return false;}
	}
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	// 组装修改表单数据
	var agentName=$("#updateAgency #agentName").val().trim();
	var custId = $("#updateAgency #tc_test").val().trim();
	var merchantCode = $("#updateAgency #merchantCode_tc").val().trim(); 					// 商户编号
	var custName = $("#updateAgency #custName_tc").val().trim(); 							// 客户名称
	var mobile = $("#updateAgency #representativeMobile").val().trim(); 	// 手机号码
	var fcontactunitNumber = $("#updateAgency #fcontactunitNumber").val().trim(); 		// 来往单位编号
	var businessLicense = $("#updateAgency #businessLicense").val().trim(); 				// 营业执照注册号
	var certifyType = $("#updateAgency #certifyType").val();								// 证件类型
	var certifyNo = $("#updateAgency #certifyNo").val().trim(); 							// 身份证号码
	var url = window.Constants.ContextPath +'<%=AgencyPath.BASE + AgencyPath.UPDATEFILEUPLOAD%>?custId=' + custId;
	$.ajax({
		type : "POST",
		url :  url,
		data :{
			businessPhoto :  $('#businessPhototemp').val(),
			certAttribute1 : $('#certAttribute1temp').val(),
			certAttribute2 : $('#certAttribute2temp').val(),
			settlEmentCard:  $('#settlEmentCardtemp').val()
		},
		dataType : "json",
		success : function(data) {
			if(data.result=='SUCCESS'){
				$.post(window.Constants.ContextPath +'<%=AgencyPath.BASE + AgencyPath.UPDATEAGENCYINFO%>',{
        			'custId':custId,
        			'custName':custName,
        			'agentName':agentName,
        			'mobile':mobile,
        			'fcontactunitNumber':fcontactunitNumber,
        			'businessLicense':businessLicense,
        			'certifyType':certifyType,
        			'certifyNo':certifyNo,
        			'businessTermEnd': endTime,
        			'businessTermStart' :startTime,
        			'businessType':data.businessType,				// 营业执照扫描件
    	 			'idCardType_1':data.idCardType_1, 				// 身份证号正面
    	 			'idCardType_2':data.idCardType_2,				// 身份证号反面
    	 			'settlEmentCard':data.settlEmentCard
        		},function(data){
        			$.unblockUI();
    				if(data.result=="SUCCESS"){
    					$("#updateAgency").hide();
    					$.gyzbadmin.alertSuccess('修改代理商成功！', null, function(){
    						window.location.reload(); // 强迫浏览器刷新当前页面
    					});
    				}else{
    					$.gyzbadmin.alertFailure("修改代理商失败！" + data.message,null, function(){
    						window.location.reload();
    					});
    				}
        		},'json')
        	}else{
        		$.gyzbadmin.alertFailure("扫描件上传失败,请选择合适的类型");
        	}
		}
	});  
	
}


/** 点击预览和更新 **/
function updateAgencyInfo(obj,option){
	/** 预览 **/
	
	$("#updateAgency img").attr("src","");
	var merchant = 	$.data($(obj).parent().parent()[0],"merchant");
	if(option == 'preview'){ 
		// 隐藏提交按钮
		$('#updateAgency .updateAgencyBtn').hide(); 
		// 让input框和select框
		$('#updateAgency .edit_hide_fcontactunitnumber').hide(); 
		$('#updateAgency input,select').prop("disabled", true); 
	
		$('#updateAgency #certifyType').val(merchant.certifyType);
		$('#updateAgency #certifyNo').val(merchant.certifyNo);
		$('#updateAgency #businessLicense').val(merchant.businessLicense);
		$('#updateAgency #representativeMobile').val(merchant.mobile);
		if(merchant.custType == '0'){
			$('#updateAgency .tempBusinessLicense').prop("disabled", true); 
			$('#updateAgency .tempBusinessPhoto').prop("disabled", true);
		}else{
			$('#updateAgency .tempBusinessLicense').prop("disabled", false); 
			$('#updateAgency .tempBusinessPhoto').prop("disabled", false);
		}
	} 
	/** 编辑 **/
	if(option == 'edit'){
		$('#updateAgency .updateAgencyBtn').show();
		// 编辑的时候隐藏掉往来单位编号
		$('#updateAgency .edit_hide_fcontactunitnumber').hide(); 
	
	
		$('#updateAgency input,select').prop("disabled", false);
		if(merchant.custType == '0'){
			$('#updateAgency .tempBusinessLicense').prop("disabled", true); 
			$('#updateAgency .tempBusinessPhoto').prop("disabled", true);
		}else{
			$('#updateAgency .tempBusinessLicense').prop("disabled", false); 
			$('#updateAgency .tempBusinessPhoto').prop("disabled",false);
		}
	}
	$("#updateAgency #businessTermStart").val(merchant.businessTermStart);
	
	if(merchant.businessTermEnd == 'forever'){
		$("#endForever").attr("checked","checked");
		$('#updateAgency #businessTermEnd').prop("disabled", true); 
	}else{
		$("#updateAgency #businessTermEnd").val(merchant.businessTermEnd);
		$("#endSel").attr("checked","checked");
	}
	
 	var custId = $(obj).parent().find('#custId_01').val();
	$("#updateAgency #businessPhotoImageDiv").show();
	$("#updateAgency #certAttribute1ImageDiv").show();
	$("#updateAgency #certAttribute2ImageDiv").show(); 
	$("#updateAgency #settlEmentCardPhotoDiv").show(); 
	$("#updateAgency #settlEmentCardPhotoDiv").attr("src","<%=request.getContextPath() + AgencyPath.BASE + AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=10");
	$("#updateAgency #businessPhotoImageDiv").attr("src","<%=request.getContextPath() + AgencyPath.BASE + AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=02");
	$("#updateAgency #certAttribute1ImageDiv").attr("src",//
			"<%=request.getContextPath() + AgencyPath.BASE 
			+ AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=04&front=0");
	$("#updateAgency #certAttribute2ImageDiv").attr("src",//
			"<%=request.getContextPath() + AgencyPath.BASE 
			+ AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=04&front=1");
	$("#updateAgency #bankCardImage").attr("src",//
			"<%=request.getContextPath() + AgencyPath.BASE 
			+ AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=05");
	$("#updateAgency #otherPapersImage").attr("src",//
			"<%=request.getContextPath() + AgencyPath.BASE 
			+ AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=06");
	
}

/** 导出代理商列表 */
$(".exportBut").click(function(){
	var merchantCode = $('.search-table #merchantCode').val().trim();
	var custName = $('.search-table #custName').val().trim();
	var protocolState = $('.search-table #protocolState').val().trim();	
	var agentName = $('.search-table #agentName').val().trim();	
	var src ="<%= request.getContextPath() + AgencyPath.BASE + AgencyPath.EXPORTAGENCYINFO%>?merchantCode=" +
		merchantCode + "&custName=" + custName + "&agentName=" + agentName+"&protocolState="+protocolState;
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
	$('.search-table #startCreateTime').val('');
	$('.search-table #endCreateTime').val('');
	$('.search-table #custName').val('');
	$('.search-table #email').val('');
	$('.search-table #agentName').val('');
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