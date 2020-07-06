<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
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
<title>代理商审核列表</title>
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
<script type="text/javascript">
function loadMerchant(){
	$('.search-table #auditState').val($("#state_02").val());
	$('.search-table #merchantState').val($("#merchantState_02").val());
	
 }

</script>
</head>

<body style="overflow-x:hidden;" onload="loadMerchant()">
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
						<form  id="merchantForm" action='<c:url value="<%=AgencyAudingPath.BASE + AgencyAudingPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >代理商编号：</td>
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
									<td class="td-left" >代理商名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >客户经理</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custManagerName" id="custManagerName"  value="${queryBean.custManagerName}">
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
											<gyzbadmin:function url="<%=AgencyAudingPath.BASE + AgencyAudingPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button id="clearMerchantSearch2" class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
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
										<th width="10%">代理商名称</th>
										<th width="10%">产品类型</th>
										<th width="10%">联系人</th>
										<th width="10%">手机号码</th>
										<th width="12%">开户名</th>
										<th width="12%">开户账号</th>
										<th width="12%">邮箱地址</th>
										<th width="9%">客户经理</th>
										<th width="12%">注册时间</th>
										<th width="10%">类型</th>
									    <th width="9%">审核状态</th>
										<th width="10%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${audingList}" var="merchant" varStatus="i">
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<td>${merchant.custName }</td>
											<td>${merchant.agentRate }</td>
											<td>${merchant.agentName}</td>
											<td>${merchant.mobile }</td>
											<td>${merchant.bankacctName }</td>
											<td>${merchant.compmainAcct }</td>
											<td>${merchant.email }</td>
											<td>${merchant.columnValue}</td>
											<td><fmt:formatDate value="${merchant.createTime}" pattern="yyyy-MM-dd"/></td>
											<td>${merchant.custType}</td>
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
                                            	<input type="hidden" name="mobile_01" id="mobile_01" value="${merchant.mobile}">
                                            	
                                                 <c:if test="${merchant.state =='01'}">
                                                  <c:if test="${merchant.countNum > 0 }">
                                                   <button type="button" onclick="updateAgencyInfo(this,'history')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc" >查看审核记录</button>
                                                    &nbsp; &nbsp;
                                                  </c:if>
                                                <button type="button" onclick="updateAgencyInfo(this,'edit')" data-toggle='modal' data-target="#updateAgency" class="btn btn-primary btn-xs qifenqian_view_tc" >审核 </button> 
                                                </c:if>
                                            	<c:if test="${merchant.state =='00'}">
                                                <button type="button" onclick="updateAgencyInfo(this,'preview')" data-toggle='modal' data-target="#updateAgency" class="btn btn-primary btn-xs qifenqian_view_tc" >查看</button> 
                                                </c:if>
                                            	<c:if test="${merchant.state =='04'}">
                                                <button type="button" onclick="updateAgencyInfo(this,'history')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc" >查看审核记录</button> 
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
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
	
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
 		
 其他 原因<tr>	
	<td>
	   <textarea rows="5" cols="40" id="auditMessage" ></textarea>
	</td>
	</tr>	 
 </table>				
		    </div> 
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary addadBtn" onclick="firstNotPass();">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" style="z-index:1040;" id="updateMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:60%;z-index:90;">
		      <div class="modal-content" style="width:950px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">代理商信息</h4>
		         </div>
		         <div class="modal-body">
						<table id="sample-table-2" class="list-table" >
						<tr>
							<td colspan="4" class="headlerPreview">代理商信息</td>
						</tr>
						<tr>
							<td class="td-left" width="18%">账号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="email_04" name="email" readonly="readonly"  style="width:90%">
									<input type="hidden" name="custName_id_01" id="custName_id_01"  />
									<input type="hidden" name="mobile_id_01" id="mobile_id_01"  />
								</td>
								<td class="td-left" width="18%">代理商名称：</td>
								<td class="td-right" width="32%"> 
										<input type="text" id="custName_04"  readonly="readonly" name="custName_04" maxlength="100"  placeholder="企业名称" style="width:90%">
								
								</td>
						 </tr>
						
							<input type="hidden"  id="orgInstCode_02" name="orgInstCode_02" >
						
							<tr>
								
						<div id="angetInfos">
						
							<tr>
								<td class="td-left">联系人：</td>
								<td class="td-right"> 
									<input type="text" id="representativeName" readonly="readonly" name="representativeName" placeholder="联系人" maxlength="50" style="width:90%">
								</td>
								<td class="td-left" >身份证号：</td>
								<td class="td-right" > 
									<input type="text"  name="representativeCertNo"  readonly="readonly" id="representativeCertNo" placeholder="身份证号" style="width:90%">
								</td>
								
							</tr>
							<tr>
								<td class="td-left" >手机号码：</td>
								<td class="td-right"> 
									<input type="text" id="representativeMobile" readonly="readonly"  name="representativeMobile" placeholder="手机号码" style="width:90%">
								</td>
								
								<td class="td-left" >证件类型：</td>
								<td class="td-right" > 
									<input type="text"  name="certifyType"  readonly="readonly" id="certifyType" placeholder="证件类型" style="width:90%">
								</td>
							</tr>
						  <tbody  id="hiddentBusinessLicense" name="hiddentBusinessLicense">
						      <tr>
								<td class="td-left">统一社会信用代码
								或营业执照注册号：</td>
								<td class="td-right">
										<input type="text" id="businessLicense" name="businessLicense" placeholder="统一社会信用代码或营业执照注册号" readonly="readonly" style="width:90%">
										<input type="hidden" id="businessLicense05" name="businessLicense05" >
								</td>
								 <input type="hidden" id="protocolContentId" name="protocolContentId"/>  
						        <input type="hidden" id="protocolContentTemp" name="protocolContentTemp"/>  
								<input type="hidden" id="Number" name="Number" value="1000073" placeholder="来往单位编号" style="width:88%" readonly="readonly"/>
							</tbody>
								<td class="td-left">审核状态：</td>
								<td class="td-right">
									<select id="merchantState" name ="merchantState" class="width-90">
										<option value="">--登陆状态--</option>
										<option value="00">审核通过</option>
										<option value="04">待审核</option>
									</select>								
								  </td>
					
						    </tr>
							
						    </div>	
						   <tr id="hiddenbusinessPhotoDiv">
							  <td class="td-left">营业执照扫描件：</td>
							  <td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img  id="businessPhotoImageDiv" onclick="bigImg(this);" src=""  style="width:100%;height:100%;display:none"  />										  
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="businessPhotoImageVal02"  />  
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							
						<tr>
						  
							<td  colspan="4" class="headlerPreview">银行信息</td>
						</tr>
						<tr>
								<td class="td-left" >开户名</td>
								<td class="td-right"> 
										<input type="text"  id="bankAcctName" readonly="readonly" name="bankAcctName" maxlength="100" style="width:90%">
								 </td>
							    <td class="td-left" >开户行：</td>
								<td class="td-right" > 
									<input type="text"  id="branchBank" readonly="readonly" name="branchBank" maxlength="200" style="width:90%">  
								</td>
								
							</tr>
							<tr>
								
								<td class="td-left" >开户账号：</td>
								<td class="td-right" > 
									<input type="text" id="bankCard" readonly="readonly" name="bankCard" placeholder="公司账号" style="width:90%">
								</td>
							</tr>
						
							<tr>
								<td class="td-left" >身份证图片正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
									<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);"  style="width:100%;height:100%;display:none"/>
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute1Val02"  />  
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
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
						   <tr>
						    <td class="td-left" >结算银行卡照片：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
										<label id="ettlEmentCardDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settlEmentCardPhotoDiv" onclick="bigImg(this);" style="width:100%;height:100%;" />
										</label>
									</a>
								</td>
						   </tr>
						   
								<table id="sample-table-3" class="list-table"  >
									
								</table>
							
						
						</table>
		         </div>
		         <div class="modal-footer" id="check" >
		            <button type="button" class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal'  data-target="#firstAuditMessageModel" >审核不通过</button>
		            <button type="button" class="btn btn-primary firstAuditPassBtn" onclick="firstPass()">审核通过</button>
		         </div>
		      </div><!-- /.modal-content -->
		   </div>
		</div>
	
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal"  aria-hidden="true">
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
	
	
	
	<div class="list-table-header">代理商审核记录</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">代理商名称</th>
		<th width="10%">代理商类型</th>
		<th width="12%">注册时间</th>
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
 $(function(){
	/** 缓存 **/
	var custIds = ${audingLists};
	var custId = $("tr.merchant");
	$.each(custIds,function(i,value){
		$.data(custId[i],"merchant",value);
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
 
 /** 点击预览和更新 **/
 function updateAgencyInfo(obj,type){
	 
	 $("#updateMerchant img").attr("src","");
	 if(type == 'edit'){
		 $('#updateMerchant').on('show.bs.modal', function () {
		 		$('#updateMerchant .updateImageDiv').hide();
		 		$('#updateMerchant .firstAuditNotPassBtn').show();
		 		$('#updateMerchant .firstAuditPassBtn').show();
		 		$('#updateMerchant .commentHide').hide();
		 		$('#updateMerchant .commentHideTarea').hide();
		 		$('#updateMerchant .numberHide').hide();
		 		$('#updateMerchant .numberHide_').hide();
		 		$('#updateMerchant input,select').prop("disabled", false);
		 		$('#updateMerchant input,select').not("#updateMerchant input[name='rule']").prop("disabled", true);
		 		$('#updateMerchant input,select').not($("#updateMerchant #fcontactunitNumber")).prop("disabled", false);
		 	});
		 	$('#updateMerchant').modal({backdrop: 'static', keyboard: false});
			var custId=$(obj).parent().find('#custId_01').val();
			
			var mobile=$(obj).parent().find('#mobile_01').val();
			var merchant = $.data($(obj).parent().parent()[0],"merchant");
			$("#updateMerchant #merchantState").val(merchant.agencyState);
			$("#updateMerchant #merchantState").attr("disabled", true);
			$("#updateMerchant #businessPhotoImageDiv").show();
			$("#updateMerchant #certAttribute1ImageDiv").show();
			$("#updateMerchant #certAttribute2ImageDiv").show();
			$("#updateMerchant #check").show();
			$("#updateMerchant #businessPhotoImageDiv").attr('src',"<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId=" + custId + "&certifyType=02");
			$("#updateMerchant #settlEmentCardPhotoDiv").attr('src',"<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId=" + custId + "&certifyType=10");
			$("#updateMerchant #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=0");
			$("#updateMerchant #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=1");
		 	$.post(window.Constants.ContextPath + '<%=AgencyAudingPath.BASE + AgencyAudingPath.AGENCY %>',
		 		{
		 		"custId" 	: custId
		 		},function(data){
		 			
		 			$("#protocolContentId").val(data.protocolContent.id);
		 			$("#protocolContentTemp").val(data.protocolContent.protocolContent);
		 			
		 			if(data.custType =='0'){
	 	 				$("#hiddentBusinessLicense").hide();
	 	 				$("#hiddenbusinessPhotoDiv").hide();
	 	 				$("#updateMerchant #branchBank").val(data.bankName);
	 	 				
	 	 			   }
	 	 			if(data.custType=='1'){
	 	 				$("#hiddentBusinessLicense").show();
	 	 				$("#hiddenbusinessPhotoDiv").show();
	 	 				
	 	 			 }
		 			var columns=$("#sample-table-3");
		 			$("#updateMerchant #custName_id_01").val(data.custId);
		 			$("#updateMerchant #email_04").val(data.email);
		 			$("#updateMerchant #mobile_id_01").val(mobile);
		 			$("#updateMerchant #custName_04").val(data.custName);
		 			$("#updateMerchant #bankAcctName").val(data.bankCardName);
		 			$("#updateMerchant #merchantState").val(merchant.agencyState);
		 	 		$("#updateMerchant #merchantState").attr("disabled", true);
		 			$("#updateMerchant #branchBank").val(data.bankName);
		 			$("#updateMerchant #businessLicense").val(data.businessLicense);
		 			$("#updateMerchant #businessLicense05").val(data.businessLicense);
		 			$("#updateMerchant #orgInstCode_02").val(data.orgInstCode);
		 			$("#updateMerchant #bank").val(data.compAcctBank);
		 			$("#updateMerchant #bankCard").val(data.bankCardNo);
		 			$("#updateMerchant #representativeName").val(data.agentName);
		 	 		$("#updateMerchant #representativeCertNo").val(data.certifyNo);
		 	 		$("#updateMerchant #representativeMobile").val(data.mobile);
		 	 		$("#updateMerchant #certifyType").val(data.certifyType);
		 			columns.html('');
		 			/* 协议内容 */
		 			 if(null!=data.bmsProtocolColumns  && data.bmsProtocolColumns.length>0){
		 				 var infos='';
						 infos="<tr><td colspan='4' class='headlerPreview'>代理产品</td></tr><tr><tr>";
						for (var i = 0; i < data.bmsProtocolColumns.length; i++) {
							 var  columnDesc=data.bmsProtocolColumns[i].columnDesc;
							 var  columnCode=data.bmsProtocolColumns[i].columnCode;
							 var columnValue= data.bmsProtocolColumns[i].columnValue;
							 if(null!=columnValue && columnValue!="null"){
							 if(columnCode=="onecode.coll.pay_rate" || columnCode=="pc.gateway.pay_rate" || columnCode=="h5.gateway.pay_rate" || columnCode=="mobile.plugin.pay_rate" || columnCode=="h5_t.gateway.pay_rate"){
								 infos +="</tr><tr><td  class='td-left "+ data.bmsProtocolColumns[i].id +"'>"+columnDesc+":</td><td class='td-right'  ><input type='text' name='bmscolumnValue' id="+data.bmsProtocolColumns[i].id+" class="+columnCode+"  value="+data.bmsProtocolColumns[i].columnValue+"><span class='columnDesc' style='display:none'>"+columnDesc+"</span>";
							 }else{
								 infos +="</tr><tr><td class='td-left'>"+columnDesc+":</td><td class='td-right'  ><span>"+data.bmsProtocolColumns[i].columnValue+"</span></tr>";
							     };
							
						       }
						   }
					  $(columns).append(infos);
					  
		 			 };
		 
		 		});
	          }
 	if(type=='preview'){
 		$('#updateMerchant').on('show.bs.modal', function () {
 	 		$('#updateMerchant .updateImageDiv').hide();
 	 		$("#merchantState").attr("disabled", true);
 	 		$('#updateMerchant .firstAuditNotPassBtn').show();
 	 		$('#updateMerchant .firstAuditPassBtn').show();
 	 		$('#updateMerchant .commentHide').hide();
 	 		$('#updateMerchant .commentHideTarea').hide();
 	 		$('#updateMerchant .numberHide').hide();
 	 		$('#updateMerchant .numberHide_').hide();
 	 		$('#updateMerchant input,select').prop("disabled", false);
 	 		$('#updateMerchant input,select').not("#updateMerchant input[name='rule']").prop("disabled", true);
 	 		$('#updateMerchant input,select').not($("#updateMerchant #fcontactunitNumber")).prop("disabled", false);
 	 	   });
 	 	$('#updateMerchant').modal({backdrop: 'static', keyboard: false});
 	 	var custId=$(obj).parent().find('#custId_01').val();
 		var merchant = $.data($(obj).parent().parent()[0],"merchant");
 		$("#updateMerchant #merchantState").val(merchant.agencyState);
 		$("#updateMerchant #merchantState").attr("disabled", true);
 		$("#updateMerchant #businessPhotoImageDiv").show();
 		$("#updateMerchant #certAttribute1ImageDiv").show();
 		$("#updateMerchant #certAttribute2ImageDiv").show();
 		$("#updateMerchant #check").hide();
 		$("#updateMerchant #businessPhotoImageDiv").attr('src',"<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId=" + custId + "&certifyType=02");
 		$("#updateMerchant #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=0");
 		$("#updateMerchant #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AgencyAudingPath.BASE+ AgencyAudingPath.GETIMG %>?custId="+custId+"&certifyType=04&front=1");
 	 	$.post(window.Constants.ContextPath + '<%=AgencyAudingPath.BASE + AgencyAudingPath.SHOW %>',
 	 		{
 	 		"custId" 	: custId
 	 		},function(data){
 	 			
 	 			if(data.custType =='0'){
 	 				$("#hiddentBusinessLicense").hide();
 	 				$("#hiddenbusinessPhotoDiv").hide();
 	 				$("#updateMerchant #branchBank").val(data.bankName);
 	 				
 	 			   }
 	 			if(data.custType=='1'){
 	 				$("#hiddentBusinessLicense").show();
 	 				$("#hiddenbusinessPhotoDiv").show();
 	 				
 	 			 }
 	 			var columns=$("#sample-table-3");
 	 			$("#updateMerchant #custName_id_01").val(data.custId);
 	 			$("#updateMerchant #email_04").val(data.email);
 	 			$("#updateMerchant #custName_04").val(data.custName);
 	 			$("#updateMerchant #bankAcctName").val(data.bankCardName);
 	 			$("#updateMerchant #branchBank").val(data.bankName);
	 			$("#updateMerchant #businessLicense").val(data.businessLicense);
	 			$("#updateMerchant #businessLicense05").val(data.businessLicense);
	 			$("#updateMerchant #orgInstCode_02").val(data.orgInstCode);
	 			$("#updateMerchant #bank").val(data.compAcctBank);
	 			$("#updateMerchant #bankCard").val(data.bankCardNo);
	 			$("#updateMerchant #representativeName").val(data.agentName);
	 	 		$("#updateMerchant #representativeCertNo").val(data.certifyNo);
	 	 		$("#updateMerchant #representativeMobile").val(data.mobile);
	 	 		$("#updateMerchant #certifyType").val(data.certifyType);
 	 			columns.html('');
 	 			/* 协议内容 */
 	 			 if(null!=data.bmsProtocolColumns  && data.bmsProtocolColumns.length>0){
 	 				 var infos='';
 					 infos="<tr><td colspan='4' class='headlerPreview'>代理产品</td></tr><tr><tr>";
 					for (var i = 0; i < data.bmsProtocolColumns.length; i++) {
 						 var  columnDesc=data.bmsProtocolColumns[i].columnDesc;
 						 var  columnCode=data.bmsProtocolColumns[i].columnCode;
 						 var columnValue= data.bmsProtocolColumns[i].columnValue;
 						 if(null!=columnValue && columnValue!="null"){
 						 if(columnCode=="onecode.coll.pay_rate" || columnCode=="pc.gateway.pay_rate" || columnCode=="h5.gateway.pay_rate" || columnCode=="mobile.plugin.pay_rate" || columnCode=="h5_t.gateway.pay_rate"){
 							 infos +="</tr><tr><td  class='td-left "+ data.bmsProtocolColumns[i].id +"'>"+columnDesc+":</td><td class='td-right'  ><input type='text' name='bmscolumnValue' readonly='readonly'   id="+data.bmsProtocolColumns[i].id+" class="+columnCode+"  value="+data.bmsProtocolColumns[i].columnValue+"><span class='columnDesc' style='display:none'>"+columnDesc+"</span>";
 							 
 						 }else{
 							 infos +="</tr><tr><td class='td-left'>"+columnDesc+":</td><td class='td-right'  ><span>"+data.bmsProtocolColumns[i].columnValue+"</span></tr>";
 							 
 						     };
 						
 					       }
 					   }
 				  $(columns).append(infos);
 				  
 	 			 };
 	 
 	 		  });
 		
 	      }
 	   /* 查看审核历史记录 */
 	  if(type == 'history'){
 		  var custId=$(obj).parent().find('#custId_01').val();
 		 $.post(window.Constants.ContextPath + '<%=AgencyAudingPath.BASE + AgencyAudingPath.GETHISTORY %>',
 		   {
  	 		"custId" 	: custId
  	 		},function(data){
  	 			var columns=$("#sample-table-5 #getHistory");
  	 			var json = eval('(' + data + ')'); 
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
 function firstNotPass(){
	var custId = $("#updateMerchant #custName_id_01").val();
	var reason=$("#auditMessage").val();
	var email=$("#updateMerchant #email_04").val();
	var mobile=$("#updateMerchant #mobile_id_01").val();
	var authId=$("#authId_01").val();
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
		   if(null!=reason && 'null'!=reason){
			   messages=chk_value.join(',')+","+reason;
		   }
	   }else{
		   messages=reason;
	   }
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgencyAudingPath.BASE+ AgencyAudingPath.SECONDNOTPASS %>',
			data:
			 {
				"custId" 	: custId,
				"messages"	:messages,
				"email":email,
				"mobile":mobile,
				"authId":authId
			  },
			success:function(data){
				if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess("审核完成！",function(){
						$("#updateMerchant").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("审核失败！"+data.message);
				};
			}
		});
	}
 /** 清空按钮 **/
 $('#clearMerchantSearch2').click(function(){
 	$('.search-table #merchantCode').val("");
 	$('.search-table #auditState').val("");
 	$('.search-table #custName').val("");
 	$('.search-table #custName').val("");
 	$('.search-table #custManagerName').val("");
 	$('.search-table #mobile').val('');
 	$('.search-table #startCreateTime  ').val('');
 	$('.search-table #endCreateTime').val('');
 	
 }); 
  function isPercent(c){
	    var r= /^[1-9]?[0-9]*\.[0-9]+[%]$/;
	    return r.test(c);
   }

 function firstPass(){
	 var email=$("#updateMerchant #email_04").val();
	 var mobile=$("#updateMerchant #mobile_id_01").val();
	 var  protocolContentTemp=$("#updateMerchant #protocolContentTemp").val();
	  var _submit=false;
	 var protoclTemp = '';
	 var contentCount = protocolContentTemp.split("@_@");
	 for (var i = 0; i <contentCount.length; i++) {
		 protoclTemp = protoclTemp + contentCount[i].split(":")[1]+':'+ contentCount[i].split(":")[0]+',';
	  }
	 var contentTempCount= protoclTemp.split(",");
	 alert(contentTempCount);
	 var protocolContent = "";
		for(var i=0; i<contentTempCount.length;i++){
			var	contents = $("# #content"+i).val();
			
			protocolContent = protocolContent.concat(contentTempCount[i].split(":")[1]+':'+contentTempCount[i].split(":")[0]+':'+contents+'@_@');
		 }
	
	 var mobile=$("#updateMerchant #mobile_id_01").val();
	
	 var custId = $("#updateMerchant #custName_id_01").val();
	 var fcontactunitNumber = $("#updateMerchant #Number").val();
		if(kong.test(fcontactunitNumber)) {
			$.gyzbadmin.alertFailure("请填写来往单位编号！");
			return false;
		   } 
		 var params={};  //提交的参数
		 var name;
		 var name2;
		 var contents;
		 $('#sample-table-3 input').each(function(i,x){
			 name = $(x).attr("id");
			 params[name]=$(x).val();
			 contents=$(x).val();
             name2=$(x).attr("class"); 
           var arr = new Array('onecode.coll.pay_rate','h5_t.gateway.pay_rate','h5.gateway.pay_rate','mobile.plugin.pay_rate','pc.gateway.pay_rate','agent_merchant_rate');
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
		 params["custId"] = custId;
		 params["number"] = fcontactunitNumber;
		 params["email"] = email;
		 params["mobile"] = mobile;
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AgencyAudingPath.BASE+ AgencyAudingPath.PASS %>',
			data: params ,
			dataType:"json",
			success:function(data){
				if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess("审核完成！",function(){
						$("#updateMerchant").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("审核失败！"+data.message);
				};
			  }
		}); 
       } 
//导出商户数据
	$('.exportBut').click(function(){
		var merchantCode = $('.search-table #merchantCode').val();
		alert(merchantCode);
		var custName = $('.search-table #custName').val();
		var custManagerName = $('.search-table #custManagerName').val();
		var auditState = $('.search-table #auditState').val();
		var startCreateTime = $('.search-table #startCreateTime').val();
		var endCreateTime = $('.search-table #endCreateTime').val();
		var src ="<%= request.getContextPath()+ AgencyAudingPath.BASE+AgencyAudingPath.AGENTEXPORTMERCHANTINFO %>?merchantCode="+
		 merchantCode+
		"&custName="+
		custName+
		"&custManagerName="+
		custManagerName+
		"&auditState="+
		 auditState+
		"&startCreateTime="+
		  startCreateTime+
		"&endCreateTime="+
		 endCreateTime;
		$(".exportBut").attr("href",src);
		
	});
/* 点击图片预览 */
 function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImageDiv");  
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

</script>
</html>