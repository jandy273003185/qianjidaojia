<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller.WechatAudingPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAudingPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>微商审核列表</title>
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

<body style="overflow-x:hidden;">
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
						
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=WechatAudingPath.BASE + WechatAudingPath.WECHATAUDITLIST %>"/>' method="post">
							<input type="hidden" name="count" id="count" value="">
							<table class="search-table">
								<tr>
									<td class="td-left" >微商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
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
									<td class="td-left">手机号码：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile"  value="${queryBean.mobile}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
							
								</tr>
								<tr>
									<td class="td-left" >微商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >邮箱地址：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" id="email"  value="${queryBean.email }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								
								<td class="td-left">注册时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
								
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=WechatAudingPath.BASE + WechatAudingPath.WECHATAUDITLIST %>">
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
						
						<div class="list-table-header">微商户列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="10%">微商户编号</th>
										<th width="10%">微商户名称</th>
										<th width="10%">产品类型</th>
										<th width="10%">联系人</th>
										<th width="10%">手机号码</th>
										<th width="12%">开户名</th>
										<th width="12%">开户账号</th>
										<th width="12%">邮箱地址</th>
										<th width="12%">注册时间</th>
										<th width="8%">类型</th>
									    <th width="12%">审核状态</th>
										<th width="23%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${audingList}" var="merchant" varStatus="i">
										<tr class="merchant" id="merchant">
											
											<td>${merchant.merchantCode }</td>
											<td>${merchant.custName }</td>
											<td>${merchant.agentRate}</td>
											<td>${merchant.agentName}</td>
											<td>${merchant.mobile }</td>
											<td>${merchant.bankacctName }</td>
											<td>${merchant.compmainAcct }</td>
											<td>${merchant.email }</td>
											
											<td><fmt:formatDate value="${merchant.createTime }" pattern="yyyy-MM-dd"/></td>
											<td>
											
											<c:if test="${merchant.custType =='个人'}">
												个体户
										    </c:if>
										    <c:if test="${merchant.custType =='企业'}">
												企业
										    </c:if>
										    
											</td>
											<td>
										<c:if test="${merchant.state =='00'}">
											已通过	
										</c:if>
										<c:if test="${merchant.state =='01'}">
											<font color="green">待审核</font>	
										 </c:if>	  
							  			<c:if test="${merchant.state =='04'}">
											<font color="red">审核不通过 </font>		
										 </c:if>			
							            
										 </td>
											
											<td>	
												
												<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}">
                                            	<input type="hidden" name="authId_01" id="authId_01" value="${merchant.authId}">
                                            	<input type="hidden" name="certifyNo" id="certifyNo" value="${merchant.certifyNo}">
                                            	<input type="hidden" name="mobile_01" id="mobile_01" value="${merchant.mobile}">
                                            	
                                            	
                                            	<c:if test="${merchant.state =='01'}">
                                            	  <c:if test="${merchant.countNum > 0 }">
                                                   <button type="button" onclick="updateAgencyInfo(this,'history')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc" >查看审核记录</button>
                                                    &nbsp; &nbsp;
                                                  </c:if>
                                                <button type="button" onclick="updateAgencyInfo(this,'edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >审核 </button> 
                                                
                                                </c:if>
                                                
                                            	<c:if test="${merchant.state =='00'}">
                                                <button type="button" onclick="updateAgencyInfo(this,'preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看</button> 
                                                </c:if>
                                            	<c:if test="${merchant.state =='04'}">
                                                <button type="button" onclick="updateAgencyInfo(this,'history')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
                                                </c:if>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty audingList}">
										<tr>
											<td colspan="9" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty audingList}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
			
			<div class="modal fade" style="z-index:1040;" id="updateMerchantModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:60%;z-index:90;">
		      <div class="modal-content" style="width:950px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		         	<input type="hidden" id="authId_update" />
		         	<input type="hidden" id="merchantCodeTemp" />
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">微商户信息</h4>
		         </div>
		         <div class="modal-body">
						<table id="sample-table-2" class="list-table" >
						<tr>
							<td colspan="4" class="headlerPreview">微商户基本信息</td>
						</tr>
						       <tr>
								<td class="td-left" width="18%">微商户名称：</td>
								<td class="td-right" width="32%"> 
										<input type="text" id="custName_1"  readonly="readonly" name="custName_1" maxlength="100"  placeholder="微商户名称" style="width:90%">
										<input type="hidden" name="custName_id_01" id="custName_id_01"   />
										<input type="hidden" name="mobile_id_01" id="mobile_id_01"  />
								</td>
								<td class="td-left" width="18%">邮箱：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="email_1" name="email_1" readonly="readonly"  style="width:90%">
								</td>
								</tr>
							<tr>
								<td class="td-left" width="18%">行业类目：</td>
								<td class="td-right" width="32%">
								
								<input style="width:50%" type="text" id="categoryDef"  readonly="readonly" style="width:50%">
                                    </td>
								     <td class="td-left" width="18%">商户所在地:</td>
							         <td class="td-right" width="32%"> 
							         <span class="input-icon">
									 <select  class="td-right" id="provinceId_1" readonly="readonly"  disabled="disabled"  onchange="getCityList_1();">
		    		   	                 <option value="" id="showProvince_1">--请选择--</option>
		           	                    </select>
		           	                    <select class="td-right"  id="cityId_1" readonly="readonly"  disabled="disabled" onchange="getAreaList_1();">
               			                    <option value="" id="cityDef_1">--请选择--</option>
	                                     </select>
	                                      <select class="td-right" readonly="readonly" disabled="disabled" id="areaId_1">
               			                    <option value="" id="areaDef_1">--请选择--</option>
	               	                      </select>  
	               	                      </span>    
								         </td>
						       </tr>
						        <tr>
								<td class="td-left">身份证号：</td>
								<td class="td-right">
									<input type="text" id="certifyNo_1" name="certifyNo_1" placeholder="身份证号" readonly="readonly" style="width:90%">
								</td>
								<td class="td-left">审核状态：</td>
								<td class="td-right">
									<select id="merchantState_1" name ="merchantState_1"  readonly="readonly" class="width-28">
										<option value="" >--请选择--</option>
										<option id="ok_02" value="00">审核通过</option>
										<option value="04" id="chech_0" selected="selected">待审核</option>
									</select>								
								</td>
								</tr>
							<tr>
							    <td class="td-left">商户角色：</td>
								<td class="td-right">
								<span  class="input-icon">
									<select disabled="disabled" id="mchRole" class="width-88">
	                                 <option value="0">线上商户</option>
                                       <option value="1">线下商户</option>
                                    </select>	
                                </span>							
								</td>  
							    <td class="td-left">详细地址：</td>
								<td class="td-right">
								<input type="text" id="merchantAdress" name="merchantAdress" readonly="readonly" placeholder="详细地址"  style="width:90%">
								</td>
								
								
								
						     </tr>
						<tr>
							<td class="td-left">是否直清<span style="color:red"></span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="isClear" style="width:100;" id="isClear">
											<option value="" >- 请选择-</option>
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>		
										<label class="label-tips" id="isClearLab"></label>
									</span>
						   </td>
					   </tr>
							<tr>
							 
							    <td class="td-left">手机号：</td>
								<td class="td-right">
									<input type="text" id="showMobile" name="showMobile" placeholder="手机号" readonly="readonly" style="width:90%">
							    </td>
							   <td class="td-left">联系人：</td>
								<td class="td-right">
									<input type="text" id="showAgentName" name="showAgentName" placeholder="联系人" readonly="readonly" style="width:90%">
								</td>
							
							</tr>
							
							<tr>
							 
							    <td class="td-left">证件类型：</td>
								<td class="td-right">
									<input type="text" id="certifyType" name="certifyType" placeholder="证件类型" readonly="readonly" style="width:90%">
							    </td>
							   <td class="td-left">结算银行：</td>
								<td class="td-right">
									<input type="text" id="showBackName" name="showBackName" placeholder="结算银行" readonly="readonly" style="width:90%">
								</td>
							
							</tr>
							<tr>
							 
							   
							   <td class="td-left">结算卡开户行：</td>
								<td class="td-right">
									<input type="text" id="showBackNameInfo" name="showBackNameInfo" placeholder="结算卡开户行" readonly="readonly" style="width:90%">
								</td>
							
							</tr>
							
							<tr id="hideBusinessTerm" >
							
							   <td class="td-left">营业执照：</td>
								<td class="td-right">
									<input type="text" id="showBusinessLicense" name="showBusinessLicense" placeholder="营业执照" readonly="readonly" style="width:90%">
							    </td>
							    <td class="td-left">营业期限：</td>
								<td class="td-right">
									<input type="text" id="showBusinessTermStart" name="showBusinessTermStart" placeholder="开始时间" readonly="readonly" style="width:90%">
									<input type="text" id="showBusinessTermEnd" name="showBusinessTermEnd" placeholder="结束时间" readonly="readonly" style="width:90%">
								</td>
							
							</tr>
							
							
							
							<tr id="hiddenbusinessPhotoDiv">
								<td class="td-left">营业执照正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success businessPhoto1Click"  data-target="#previewImageModal" >
										<label id="businessPhotoDiv1"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img  id="businessPhotoImageDiv1" onclick="bigImg(this);" src=""  style="width:100%;height:100%;display:none"  />										  
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="businessPhotoImageVal01"  />  
										
									</div>
									
								</td>
							</tr>
							
							 <tr id="hiddendoorPhotoDiv">
							
							
							 
								
							</tr>
							
							
							
							
							<tr>
								<td style="right: " class="td-left" >身份证图片正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
									<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute1Val02"  />  
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
									</div>
								</td>
							</tr>
						    <tr id="hiddenopenAccountDiv" >
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal"  >
										<label id="openAccountDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="openAccountImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="openAccountVal02"  /> 
									</div>
								</td>
							</tr>
							<tr id="hiddenbankCardPhotoDiv">
								<td class="td-left" >手持银行卡照：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoVal02"  /> 
									</div>
								</td>
							</tr>
							
							<tr id="hidenetWorkPhoto_1">
								<td class="td-left" >网络文化经营许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success netWorkPhotoClick"  data-target="#previewImageModal"  >
										<label id="netWorkPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id=imgnetWorkPhoto_1 onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="netWorkPhotoVal02"  /> 
									</div>
								</td>
							</tr>
							
							
							<table id="sample-table-3" class="list-table">
							
							</table>		
				
					</table>
					
				<div id="cashier0">
							
				</div>
		         </div>
		         <div id="hiddenCheck"  class="modal-footer">
		            	<button type="button" class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal'  data-target="#firstAuditMessageModel" >审核不通过</button>
		            	<button type="button" class="btn btn-primary firstAuditPassBtn">审核通过</button>
		         </div>
		         
		      </div><!-- /.modal-content -->
		      
		   </div>
		   	
		</div>
		
		
		
		
		 <div class="modal fade" style="z-index:1043;" id="firstAuditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:30%;z-index:99;">
		      <div class="modal-content" >
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核不通过</h4>
		         </div>
		   <div class="modal-body">
		         
 <table id="selectCheckbox">
	
<label><input name="test"   type="checkbox" value="代理商名称填写错误" />&nbsp;代理商名称填写错误</label></br>			
<label><input name="test"   type="checkbox" value="联系人填写有误" />&nbsp;联系人填写有误</label></br>
<label><input name="test"   type="checkbox" value="邮箱填写错误" />&nbsp;邮箱填写错误</label></br>
<label><input name="test"   type="checkbox" value="身份证正面照片不清晰" />&nbsp;身份证正面照片不清晰</label></br>
<label><input name="test"   type="checkbox" value="身份证反面照片不清晰 " />&nbsp;身份证反面照片不清晰 </label></br>		
<label><input name="test"   type="checkbox" value="身份证照片正反面调换" />&nbsp;身份证照片正反面调换 </label> </br>
<label><input name="test"   type="checkbox" value="身份证信息与填写身份证号码不符" />&nbsp;身份证信息与填写身份证号码不符</label></br> 
<label><input name="test"   type="checkbox" value="银行卡卡号与开户行不匹配" />&nbsp;银行卡卡号与开户行不匹配 </label> </br>
<label><input name="test"   type="checkbox" value="营业执照不清晰" />&nbsp;营业执照不清晰 </label></br> 			
<label><input name="test"   type="checkbox" value="营业执照信息与填写营业执照注册号不符" />&nbsp;营业执照信息与填写营业执照注册号不符</label></br> 
<label><input name="test"   type="checkbox" value="营业执照信息与填写统一社会信用代码不符" />&nbsp;营业执照信息与填写统一社会信用代码不符 </label></br> 
<label><input name="test"   type="checkbox" value="营业执照期限与填写填写营业期限不符" />&nbsp;营业执照期限与填写填写营业期限不符</label></br> 
<label><input name="test"   type="checkbox" value="银行账户不存在" />&nbsp;银行账户不存在 </label></br> 
<label><input name="test"   type="checkbox" value="商户名称填写有误" />&nbsp;商户名称填写有误 </label></br>
<label><input name="test"   type="checkbox" value="商户费率配置不符合平台要求" />&nbsp;商户费率配置不符合平台要求</label></br>
	
	
 其他原因<tr>	
	<td>
	   <textarea rows="5" cols="40" id="auditMessage" ></textarea>
	</td>
	</tr>	 

     
 </table>	
		            
		            
		    </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary addadBtn" onclick="firstAuditNotPassBtn();">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->   
	
	<!-- 底部-->
	<%@ include file="/include/bottom.jsp"%>
	</div><!-- /.main-content -->
	<!-- 设置 -->
	<%@ include file="/include/setting.jsp"%>
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" id="imageDiv" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
	</div> 
	
	<div class="modal fade" style="z-index:1040;" id="showHistory" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:60%;z-index:90;">
	<div class="modal-content" style="width:950px;" id="merchantDiv">
	<button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
	
	<div class="list-table-header">微商户审核记录</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">代理商名称</th>
		<th width="10%">代理商类型</th>
		<th width="10%">注册时间</th>
		<th width="12%">审核时间</th>
		<th width="15%">原因</th>
		
		</tr>
		</thead>
       <tbody id="getHistory">
         
      </tbody>

	 </table>
	</div>
	</div>
	</div>
	</div>
	</div>
	
</body>
<script type="text/javascript">

function updateAgencyInfo(obj,type) {
	
	$("#updateMerchantModel img").attr("src","");
	if(type =='edit'){
		
		$("#cashier0").empty();
		   var custId=$(obj).parent().find('#custId_01').val();
			var merchant = $.data($(obj).parent().parent()[0],"merchant");
			
			$("#updateMerchantModel #merchantCodeTemp").val(merchant.merchantCode);
			$("#updateMerchantModel #merchantState_1").val(merchant.agencyState);
			$("#updateMerchantModel #merchantState_1").attr("disabled", true);
			$("#updateMerchantModel #businessPhotoImageDiv1").show();
			$("#updateMerchantModel #businessPhotoImageDiv2").show();
			$("#updateMerchantModel #certAttribute1ImageDiv").show();
			$("#updateMerchantModel #certAttribute2ImageDiv").show();
			$("#updateMerchantModel #doorPhotoImageDiv1").show();
			$("#updateMerchantModel #hiddenCheck").show();
			$("#updateMerchantModel #authId_update").val(merchant.authId);
			$("#updateMerchantModel #businessPhotoImageDiv1").attr('src',"<%=request.getContextPath() + AgencyPath.BASE + AgencyPath.PREVIEWAGENCYIMAGE %>?custId=" + custId + "&certifyType=02");
			$("#updateMerchantModel #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0");
			$("#updateMerchantModel #doorPhotoImageDiv1").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=08&front=0");
			$("#updateMerchantModel #businessPhotoImageDiv2").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1");
			$("#updateMerchantModel #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1");
			$.post(window.Constants.ContextPath +'<%=WechatAudingPath.BASE + WechatAudingPath.WECHATEDIT%>',{
				
	  			"custId":custId	
	  			
	  		},function(data){
	  			var custId=data.custId;
	  			if(data.custType =='0'){
 	 				$("#hiddenopenAccountDiv").hide();
 	 				$("#hiddenbankCardPhotoDiv").show();
 	 				$("#bankCardPhotoImageDiv").show();
 	 				$("#updateMerchantModel #bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=07");
	 				 
 	 			   }
 	 			 if(data.custType=='1'){
 	 				$("#hiddenbankCardPhotoDiv").hide();
 	 				$("#hiddenbusinessPhotoDiv").show();
 	 				$("#openAccountImageDiv").show();
 	 				$("#updateMerchantModel #openAccountImageDiv").attr("src","<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId="+custId+"&certifyType=03");
  				    
 	 			  }
 	 			 
	  		   var columns=$("#sample-table-3");
	  		   var mo=$("#sample-table-4");
	  		   var mobile=$(obj).parent().find('#mobile_01').val();
	  			$("#updateMerchantModel #mobile_id_01").val(mobile);
	  		    $("#updateMerchantModel #custName_id_01").val(data.custId);
				$("#updateMerchantModel #custName_1").val(data.custName);
				/* $("#updateMerchantModel #custPhone_1").val(data.agentName); */
				$("#updateMerchantModel #email_1").val(data.email);
				$("#updateMerchantModel #certifyNo_1").val(data.certifyNo);
				
				$("#updateMerchantModel #merchantAdress").val(data.custAdd);
			    $("#updateMerchantModel #showMobile").val(data.mobile);
			    $("#updateMerchantModel #showAgentName").val(data.agentName);
			    var certifyType=data.certifyType;
			    if(certifyType=='00'){
			    $("#updateMerchantModel #certifyType").val("大陆居民身份证");
			    }
			    if(certifyType=='01'){
				    $("#updateMerchantModel #certifyType").val("香港居民身份证");
				    }
			    if(certifyType=='02'){
				    $("#updateMerchantModel #certifyType").val("澳门居民身份证");
				    }
			    if(certifyType=='03'){
				    $("#updateMerchantModel #certifyType").val("台湾居民身份证");
				    }
			     $("#updateMerchantModel #isClear").val(data.isClear);
			    
			     
			     $("#isClear").find("option[value="+data.isClear+"]").attr("selected",true);
			     var grade=data.categoryType;
		        	if(grade=="177"){
	    				 $("#hidenetWorkPhoto_1").show();
	    				 $("#imgnetWorkPhoto_1").show();
	    				 $("#updateMerchantModel #imgnetWorkPhoto_1").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=09");
	    		      }else{
	    		    	   $("#hidenetWorkPhoto_1").hide();
	    		      }
		         $("#hiddendoorPhotoDiv").html('');
		         var doorPhotos=data.doorPhoto;
		         $("#hiddendoorPhotoDiv").append("<td class='td-left' id='doorPhotos'>门头照：</td>");
	   	        	for (var v = 0; v < doorPhotos.length; v++) {
						$("#hiddendoorPhotoDiv").append(
		    		    	 	'<a data-toggle="modal" class="tooltip-success doorPhotoClick"  data-target="#previewImageModal" >'+
		    		    	 	'<label id="doorPhotoDiv1"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">'+
		    		    	 	'<img  name="doorPhotoImageDiv'+v+'" onclick="bigImg(this)"  style="width:100%;height:100%;"  src="<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId='+custId+'&certifyType=08&front='+v+'"'+
		    		    	 	'</label></a>'
		    		     );
					}
			     
	   	         $("#dooText").append("</td>");
			     $("#updateMerchantModel #showBackName").val(data.compAcctBank);
			     $("#updateMerchantModel #showBackNameInfo").val(data.bankName);
			     $("#updateMerchantModel #showBusinessLicense").val(data.businessLicense);
			     $("#updateMerchantModel #showBusinessTermStart").val(data.businessTermStart);
			     if(data.businessTermEnd=="forever"){
			    	  $("#updateMerchantModel #showBusinessTermEnd").val("长期有效");
			     }else{
			    	 $("#updateMerchantModel #showBusinessTermEnd").val(data.businessTermEnd);
			     }
			     
				$("#updateMerchantModel #categoryDef").text('');
				if(data.province!=null){
	    	    $("#updateMerchantModel #categoryDef").val(data.categoryInfo.categoryName);
	    	    
				  var province= $("#showProvince_1").val(data.province);
     	       	  var provListsInfo =data.provList;
     	       	  var province_1=$("#provinceId_1");
     	       	  var provinceInfos_1="";
     	          for(var x = 0; x< provListsInfo.length; x++) {
     	       		province= $("#showProvince_1").val();
     	       	    provinceInfos_1+="<option value='"+ provListsInfo[x].provinceId+"'>"+ provListsInfo[x].provinceName+ "</option>";
     	       		if(province==provListsInfo[x].provinceId){
     	       			$("#showProvince_1").text(provListsInfo[x].provinceName);
     	       			$("#showProvince_1").val(provListsInfo[x].provinceId);
     	       		   } 
     			     }
     	       	 $(province_1).append(provinceInfos_1);
     	         $("#updateMerchantModel #cityDef_1").text(data.tbCity.cityName).attr("selected", "selected");
   	       		 $("#updateMerchantModel #cityDef_1").val(data.tbCity.cityId);
   	       		 $("#updateMerchantModel #areaDef_1").text(data.tbAreaInfo.areaName).attr("selected", "selected");;
   	       		 $("#updateMerchantModel #areaDef_1").val(data.tbAreaInfo.areaId);
     	          
				 }
				$("#bankAcctName_1").val(data.bankAcctName);
				$("#branchBankName_1").val(data.branchBank);
				columns.html('');
				mo.html('');
	 			/* 协议内容 */
	 			 if(null!=data.bmsProtocolColumns  && data.bmsProtocolColumns.length>0){
	 				 var infos='';
					 infos="<tr><td colspan='4' class='headlerPreview'>微商户结算信息</td></tr>";
					for (var i = 0; i < data.bmsProtocolColumns.length; i++) {
						 var  columnDesc=data.bmsProtocolColumns[i].columnDesc;
						 var  columnCode=data.bmsProtocolColumns[i].columnCode;
						 var columnValue= data.bmsProtocolColumns[i].columnValue;
						 if(null!=columnValue && columnValue!="null"){
						 if(columnCode=="onecode.coll.pay_rate" || columnCode=="pc.gateway.pay_rate" || columnCode=="h5.gateway.pay_rate" || columnCode=="mobile.plugin.pay_rate" ||columnCode=="h5_t.gateway.pay_rate" ){
							 infos +="</tr><tr><td  class='td-left "+ data.bmsProtocolColumns[i].id +"'>"+columnDesc+":</td><td class='td-right'  ><input type='text' name='bmscolumnValue' id="+data.bmsProtocolColumns[i].id+" class="+columnCode+"  value="+data.bmsProtocolColumns[i].columnValue+"><span class='columnDesc' style='display:none'>"+columnDesc+"</span>";
						 }else{
							 infos +="</tr><tr><td class='td-left'>"+columnDesc+":</td><td class='td-right'  ><span>"+data.bmsProtocolColumns[i].columnValue+"</span></tr>";
						      };
						      }
					      }
				  $(columns).append(infos);
	 			 };
				
			 var j = 0 ;
			//收银员信息
			$("#cashier0").html("");
			if(null!=data.cashierList  && data.cashierList.length>0){
				$.each(data.cashierList, function(i, item) {
					$("#cashier0").append(
							"<table id='sample-table-2' class='list-table' ><tr><tr><td colspan='4' class='headlerPreview'>收银员信息</td></tr>"+
							"<input type='hidden' name='onlyId"+i+"' id='onlyId"+i+"' value='"+item.onlyId+"'>"+
								"<td class='td-left' >姓名</td>" +
								"<td class='td-right'>" + 
									"<input type='text'  id='cashierName_1' value='"+item.cashierName+"' readonly='readonly' name='bankAcctName_1' maxlength='100' style='width:90%'>"+ 
								"</td>"+
								"<td class='td-left' >手机号：</td> <td class='td-right' >"+
								 	"<input type='text'  id='cashierMobile_1' readonly='readonly' value='"+item.cashierMobile+"' name='cashierMobile_1' maxlength='200' style='width:90%'>  "+ 
								"</td>"+
								 "</tr>"+
								"<tr>"+
									"<td class='td-left' >退款权限：</td>"+
									"<td class='td-right'>"+
										"<select id='refundAuthority_"+i+"' value = '"+item.queryAuth+"' name ='refundAuthority_"+i+"' class='width-28'>"+
											"<option value='0'>否</option>"+
											"<option value='1'>是</option>"+
									    "</select>"+
									"</td>"+
									"</tr>"+
								"</table>"
					         ); 
					     j = i+1;
		              });
				 $("#count").val(j);
			    }
	  		  },'json');
	        }
	
    if(type =='preview'){
    	$("#cashier0").empty();
		   var custId=$(obj).parent().find('#custId_01').val();
			var merchant = $.data($(obj).parent().parent()[0],"merchant");
			$("#updateMerchantModel #merchantState_1").val(merchant.agencyState);
			$("#updateMerchantModel #merchantState_1").attr("disabled", true);
			$("#updateMerchantModel #businessPhotoImageDiv1").show();
			$("#updateMerchantModel #businessPhotoImageDiv2").show();
			$("#updateMerchantModel #certAttribute1ImageDiv").show();
			$("#updateMerchantModel #certAttribute2ImageDiv").show();
			$("#doorPhotoImageDiv1").show();
			$("#updateMerchantModel #hiddenCheck").hide();
			$("#updateMerchantModel #doorPhotoImageDiv1").attr('src',"<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId=" + custId + "&certifyType=08");
			$("#updateMerchantModel #businessPhotoImageDiv1").attr('src',"<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId=" + custId + "&certifyType=02");
			$("#updateMerchantModel #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=0");
			$("#updateMerchantModel #businessPhotoImageDiv2").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=1");
			$("#updateMerchantModel #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=1");
			$.post(window.Constants.ContextPath +'<%=WechatAudingPath.BASE + WechatAudingPath.SHOW%>',{
	  			"custId":custId	
	  		},function(data){
	  			
	  			if(data.custType =='0'){
	  				$("#bankCardPhotoImageDiv").show();
 	 				$("#hiddenopenAccountDiv").hide();
 	 				
 	 				$("#updateMerchantModel #bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=07");
 	 			   }
 	 			if(data.custType=='1'){
 	 				$("#hiddenbankCardPhotoDiv").hide();
 	 				$("#hiddenbusinessPhotoDiv").show();
 	 				$("#openAccountImageDiv").show();
 	 				$("#updateMerchantModel #openAccountImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=03");
 	 			 }
	  			$("#ok_02").attr('selected','selected');
	  			$("#chech_0").attr("selected", "");
	  		   var columns=$("#sample-table-3");
	  		   var mo=$("#sample-table-4");
	  		   
			    $("#updateMerchantModel #showMobile").val(data.mobile);
			    $("#updateMerchantModel #showAgentName").val(data.agentName);
			    var certifyType=data.certifyType;
			    if(certifyType=='00'){
			    $("#updateMerchantModel #certifyType").val("大陆居民身份证");
			    }
			    if(certifyType=='01'){
				    $("#updateMerchantModel #certifyType").val("香港居民身份证");
				    }
			    if(certifyType=='02'){
				    $("#updateMerchantModel #certifyType").val("澳门居民身份证");
				    }
			    if(certifyType=='03'){
				    $("#updateMerchantModel #certifyType").val("台湾居民身份证");
				    }
			    var grade=data.categoryType;
	        	if(grade=="177"){
    				 $("#hidenetWorkPhoto_1").show();
    				 $("#imgnetWorkPhoto_1").show();
    				 $("#updateMerchantModel #imgnetWorkPhoto_1").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=09");
    		      }else{
    		    	   $("#hidenetWorkPhoto_1").hide();
    		      }
	        	$("#hiddendoorPhotoDiv").html('');
		         var doorPhotos=data.doorPhoto;
		         $("#hiddendoorPhotoDiv").append("<td class='td-left' id='doorPhotos'>门头照：</td>");
	   	        	for (var v = 0; v < doorPhotos.length; v++) {
						$("#hiddendoorPhotoDiv").append(
		    		    	 	'<a data-toggle="modal" class="tooltip-success doorPhotoClick"  data-target="#previewImageModal" >'+
		    		    	 	'<label id="doorPhotoDiv1"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">'+
		    		    	 	'<img  name="doorPhotoImageDiv'+v+'" onclick="bigImg(this)"  style="width:100%;height:100%;"  src="<%=request.getContextPath()+WechatAudingPath.BASE+ WechatAudingPath.IMAGE %>?custId='+custId+'&certifyType=08&front='+v+'"'+
		    		    	 	'</label></a>'
		    		     );
					}
			    $("#updateMerchantModel #showBackName").val(data.compAcctBank);
			    $("#updateMerchantModel #showBackNameInfo").val(data.bankName);
			    $("#updateMerchantModel #isClear").val(data.isClear);
			    $("#updateMerchantModel #showBusinessLicense").val(data.businessLicense);
			    $("#updateMerchantModel #showBusinessTermStart").val(data.businessTermStart);
			     if(data.businessTermEnd=="forever"){
			    	  $("#updateMerchantModel #showBusinessTermEnd").val("长期有效");
			     }else{
			    	 $("#updateMerchantModel #showBusinessTermEnd").val(data.businessTermEnd);
			     }
			    $("#isClear").find("option[value="+data.isClear+"]").attr("selected",true); 
	  		    $("#updateMerchantModel #custName_id_01").val(data.custId);
				$("#updateMerchantModel #custName_1").val(data.custName);
				$("#updateMerchantModel #custPhone_1").val(data.agentName);
				$("#updateMerchantModel #email_1").val(data.email);
				$("#updateMerchantModel #certifyNo_1").val(data.certifyNo);
				$("#bankAcctName_1").val(data.bankAcctName);
				$("#branchBankName_1").val(data.branchBank);
				$("#updateMerchantModel #categoryDef").text('');
				$("#updateMerchantModel #merchantAdress").val(data.custAdd);
				if(data.province!=null){
	    	    $("#updateMerchantModel #categoryDef").val(data.categoryInfo.categoryName);
					  var province= $("#showProvince_1").val(data.province);
	     	       	  var provListsInfo =data.provList;
	     	       	  var province_1=$("#provinceId_1");
	     	       	  var provinceInfos_1="";
	     	          for(var x = 0; x< provListsInfo.length; x++) {
	     	       		province= $("#showProvince_1").val();
	     	       	    provinceInfos_1+="<option value='"+ provListsInfo[x].provinceId+"'>"+ provListsInfo[x].provinceName+ "</option>";
	     	       		if(province==provListsInfo[x].provinceId){
	     	       			$("#showProvince_1").text(provListsInfo[x].provinceName);
	     	       			$("#showProvince_1").val(provListsInfo[x].provinceId);
	     	       		   } 
	     			     }
	     	       	 $(province_1).append(provinceInfos_1); 
					
	    	         $("#updateMerchantModel #cityDef_1").text(data.tbCity.cityName).attr("selected", "selected");
		       		 $("#updateMerchantModel #cityDef_1").val(data.tbCity.cityId);
		       		 $("#updateMerchantModel #areaDef_1").text(data.tbAreaInfo.areaName).attr("selected", "selected");;
		       		 $("#updateMerchantModel #areaDef_1").val(data.tbAreaInfo.areaId);
	     	       	 $("#updateMerchantModel #categoryId").val(data.tbCity.cityId);
	     	       	
					 }
				
				columns.html('');
				mo.html('');
	 			/* 协议内容 */
	 			 if(null!=data.bmsProtocolColumns  && data.bmsProtocolColumns.length>0){
	 				 var infos='';
					 infos="<tr><td colspan='4' class='headlerPreview'>微商户结算信息</td></tr>";
					for (var i = 0; i < data.bmsProtocolColumns.length; i++) {
						 var  columnDesc=data.bmsProtocolColumns[i].columnDesc;
						 var  columnCode=data.bmsProtocolColumns[i].columnCode;
						 var columnValue= data.bmsProtocolColumns[i].columnValue;
						 if(null!=columnValue && columnValue!="null"){
						 if(columnCode=="onecode.coll.pay_rate" || columnCode=="pc.gateway.pay_rate" || columnCode=="h5.gateway.pay_rate" || columnCode=="mobile.plugin.pay_rate" ||columnCode=="h5_t.gateway.pay_rate" ){
							 infos +="</tr><tr><td  class='td-left "+ data.bmsProtocolColumns[i].id +"'>"+columnDesc+":</td><td class='td-right'  ><input type='text' name='bmscolumnValue' id="+data.bmsProtocolColumns[i].id+" class="+columnCode+"  readonly='readonly' value="+data.bmsProtocolColumns[i].columnValue+"><span class='columnDesc' style='display:none'>"+columnDesc+"</span>";
						 }else{
							 infos +="</tr><tr><td class='td-left'>"+columnDesc+":</td><td class='td-right'  ><span>"+data.bmsProtocolColumns[i].columnValue+"</span></tr>";
						    };
						    }
					      }
				  $(columns).append(infos);
	 			 };
				
			 var j = 0 ;
			//收银员信息
			$("#cashier0").html("");
			if(null!=data.cashierList  && data.cashierList.length>0){
				$.each(data.cashierList, function(i, item) {
					$("#cashier0").append(
							"<table id='sample-table-2' class='list-table' ><tr><tr><td colspan='4' class='headlerPreview'>收银员信息</td></tr>"+
							"<input type='hidden' name='onlyId"+i+"' id='onlyId"+i+"' value='"+item.onlyId+"'>"+
								"<td class='td-left' >姓名</td>" +
								"<td class='td-right'>" + 
									"<input type='text'  id='cashierName_1' value='"+item.cashierName+"' readonly='readonly' name='bankAcctName_1' maxlength='100' style='width:90%'>"+ 
								"</td>"+
								"<td class='td-left' >手机号：</td> <td class='td-right' >"+
								 	"<input type='text'  id='cashierMobile_1' readonly='readonly' value='"+item.cashierMobile+"' name='cashierMobile_1' maxlength='200' style='width:90%'>  "+ 
								"</td>"+
								 "</tr>"+
								"<tr>"+
									"<td class='td-left' >退款权限：</td>"+
									"<td class='td-right'>"+
										"<select id='refundAuthority_"+i+"' value = '"+item.queryAuth+"' name ='refundAuthority_"+i+"' class='width-28'>"+
											"<option id='refundNo' value='0'>否</option>"+
											"<option  id='refundYes' value='1'>是</option>"+
									    "</select>"
									    +
									"</td>"+
									"</tr>"+
								"</table>"
								
					         ); 
					if(item.refundAuth == '1'){
						$("#refundYes").attr('selected','selected');
                                 
					      } 
					if(item.refundAuth == '0'){
						$("#refundNo").attr('selected','selected');
                                 
					      } 
					     j = i+1;
		              });
				 $("#count").val(j);
			    }
	  		  },'json');
			
	        }
    if(type == 'history'){
    	 var custId=$(obj).parent().find('#custId_01').val();
		 $.post(window.Constants.ContextPath + '<%=WechatAudingPath.BASE + WechatAudingPath.GETHISTORY %>',
		   {
	 		"custId" 	: custId
	 		},function(data){
	 			var json = eval('(' + data + ')'); 
	 			var columns=$("#sample-table-5 #getHistory");
	 			$(columns).html('');
	 			var infos='';
	 			if(json.result=="FAILE"){
  	 				infos="<tr><td colspan='9' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>";
  	 			 }
	 			 if(json.checkHistory){
  	 				 var  checkHistorys=json.checkHistory;
  	 				$('#showHistory').on('show.bs.modal', function () {
  	 				 });
  	 				  for (var i = 0; i < checkHistorys.length; i++) {
  	 					infos+="<tr><td>"+checkHistorys[i].custName+"</td><td>"+checkHistorys[i].roleId+"</td></td><td>"+checkHistorys[i].addTime+"</td><td>"+checkHistorys[i].updateTime+"</td><td>"+checkHistorys[i].authResultDesc+"</td></tr>";
					}  
  	 			 } 
	 		 $(columns).append(infos);
	 		});
	    
       }
	   
		 
}	
/** 清空按钮 **/
$('.clearMerchantSearch').click(function(){
	$('.search-table #merchantCode').val('');
	$('.search-table #auditState').val('');
	$('.search-table #custName').val('');
	$('.search-table #email').val('');
	$('.search-table #mobile').val('');
}); 

</script>
<script type="text/javascript">
 $(function(){
	/** 缓存 **/
	var custIds = ${audingLists};
	var custId = $("tr.merchant");
	$.each(custIds,function(i,value){
		$.data(custId[i],"merchant",value);
	});
	
	 function isPercent(c){
		 var r= /^[1-9]?[0-9]*\.[0-9]+[%]$/;
		 return r.test(c);
	}
	 /** 审核通过 */
	$(".firstAuditPassBtn").click(function(){
		var _submit=false;
		var custId = $("#updateMerchantModel #custName_id_01").val();
		var email=$("#updateMerchantModel #email_1").val();
		var mobile=$("#updateMerchantModel #mobile_id_01").val();
		var custName=$("#updateMerchantModel #custName_1").val();
		var merchantCode = $("#updateMerchantModel #merchantCodeTemp").val();
		var isClear=$("#isClear").val().trim();
		if(kong.test($("#isClear").val())){
			$.gyzbadmin.alertFailure("是否直清不能为空");
			return false;
		}
		
		var authId = $("#updateMerchantModel #authId_update").val();
		 var params={};  //提交的参数
		 var name;
		 var name2;
		 var contents;
		  
		 $('#sample-table-3 input').each(function(i,x){
			 name = $(x).attr("id");
			 params[name]=$(x).val();
			 contents=$(x).val();
             name2=$(x).attr("class"); 
            
           var arr = new Array('onecode.coll.pay_rate','h5.gateway.pay_rate','h5_t.gateway.pay','mobile.plugin.pay_rate','pc.gateway.pay_rate','agent_merchant_rate');
			 if($.inArray(name2,arr)!=-1){
					 if(!isPercent(contents)){
		            	 var msgCls = "." + name;
		            	 var msg=$(msgCls).html();
		            	 $.gyzbadmin.alertFailure(msg+'请填正确的百分数(如0.6%)');
						   _submit = true; //只要出现不合法的,弹框提示,并且拒绝提交表单
						 return false;
		             };

			     };  
		    });
		 
		  if(_submit){
				
			return false;
					
		  }
		 params["isClear"] = isClear;
		 params["custId"] = custId;
		 params["email"] = email;
		 params["mobile"] = mobile;
		 params["custName"] = custName;
		 params["authId"] = authId;
		 params["merchantCode"] = merchantCode;
		 
		 var onlyId = "";
		 var refundAuth = "";
		 var num = $("#count").val();
		 for(var i = 0 ; i<num ; i++){
			 onlyId = onlyId + $("#onlyId"+i).val()+"@";
			 refundAuth = refundAuth + $("#refundAuthority_"+i).val()+"@";
		  }
		 
		 params["onlyId"]=onlyId;
		 params["refundAuth"]=refundAuth;
		 
		 
		 $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=WechatAudingPath.BASE+ WechatAudingPath.PASS %>',
				data: params ,
				dataType:"json",
				success:function(data){
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("审核完成！",function(){
							$("#updateMerchantModel").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("审核失败！"+data.message);
					};
				 
				}
			});
		
	});
	
});
 
 
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
	
function firstAuditNotPassBtn(){
	
	var custId = $("#updateMerchantModel #custName_id_01").val();
	var email=$("#updateMerchantModel #email_1").val();
	var mobile=$("#updateMerchantModel #mobile_id_01").val();
	var custName=$("#updateMerchantModel #custName_1").val();
	var reason=$("#auditMessage").val();
	 var obj = document.getElementsByName("test"); // 获取多选框数组
	    var objLen = obj.length;
	    var objYN = false; // 是否有选择
	    for (var i = 0; i < objLen; i++) {
	        if (obj [i].checked == true) {
	            objYN = true;
	            break;
	          }
	        }
	    if (!objYN   &&  reason.length<=0) {
	    	$.gyzbadmin.alertFailure("请选择或填写不通过的理由！");
	        return false;
	    } 
	    
	    var chk_value =[]; 
	    $('input[name="test"]:checked').each(function(){ 
	        chk_value.push($(this).val()); 
	    });
	  
	   var messages; 
	   if(null!=chk_value && chk_value.length>=0 && 'null'!=chk_value){
		   messages=chk_value.join(',');
		   if(null!=reason && 'null'!=reason && '' != reason){
			   messages=chk_value.join(',')+","+reason;
		   }
	   }else{
		   messages=reason;
	   }
	   
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=WechatAudingPath.BASE+ WechatAudingPath.SECONDNOTPASS %>',
			data:
			{
				"custId" 	: custId,
				"message"	:messages,
				"email"     :email,
				"mobile"    :mobile,
				"custName"  :custName
					
			},
			success:function(data){
				
				if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess("审核完成！",function(){
						$("#updateMerchantModel").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("审核失败！"+data.message);
				}
			}
		})
	}

	//导出商户数据
	$('.exportBut').click(function(){
		var merchantCode = $('.search-table #merchantCode').val();
		var custName = $('.search-table #custName').val();
		var email = $('.search-table #email').val();
		var startCreateTime = $('.search-table #startCreateTime').val();
		var endCreateTime = $('.search-table #endCreateTime').val();
		var auditState = $('.search-table #auditState').val();
		var src ="<%= request.getContextPath()+ WechatAudingPath.BASE+ WechatAudingPath.TINYMERCHANTEXPORTMERCHAN %>?merchantCode="+
		 merchantCode+
		"&custName="+
		 custName+
		"&email="+
		 email+
		"&auditState="+
		 auditState+
		"&startCreateTime="+
		  startCreateTime+
		"&endCreateTime="+
		  endCreateTime;
		$(".exportBut").attr("href",src);
		
	});
	
</script>
</html>