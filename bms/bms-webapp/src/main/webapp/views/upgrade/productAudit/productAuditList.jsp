<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.upgrade.product.ProductAudingPath" %>
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
<title>产品审核列表</title>
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
						<input id="productCode_" value="${product.productCode }"  type="hidden">
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=ProductAudingPath.BASE + ProductAudingPath.PRODUCTAUDITLIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${product.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${product.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">产品类型：</td>
									<td class="td-right">								   
										 <select name="productCode" id="productCode" >
										  <option  value="" > 请选择 </option>
										  <option  value="SCAN_PAY"> 扫码支付 </option>
										  <option  value="APP_PAY"> APP支付 </option>
										  <option  value="OA_PAY"> 公众号支付 </option>
										  <option  value="H5_PAY"> H5支付 </option>
										  <option  value="MINI_PAY"> 小程序支付 </option>
										  <option  value="BLUESEA_PAY"> 蓝海计划 </option>
										 </select>
									    <label class="label-tips" id="productCodeLab"></label>
									</td>
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ProductAudingPath.BASE + ProductAudingPath.PRODUCTAUDITLIST %>">
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
						
						<div class="list-table-header">产品列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th>商户编号</th>
										<th>商户名称</th>
										<th>产品类型</th>
										<th>期望费率</th>
										<th>提交时间</th>
										<th>注册方式</th>
										<th>审核状态</th>
										<th>审核信息</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${productList}" var="product" varStatus="i">
										<tr class="product">
											<td>${product.merchantCode }</td>
											<td>${product.custName }</td>
											<td>
												<c:if test="${product.productCode=='SCAN_PAY'}">
													扫码支付
										    	</c:if>
										    	<c:if test="${product.productCode=='APP_PAY'}">
													APP支付
										    	</c:if>
										    	<c:if test="${product.productCode=='OA_PAY'}">
													公众号支付
												</c:if>
												<c:if test="${product.productCode=='H5_PAY'}">
													H5支付
												</c:if>
												<c:if test="${product.productCode=='MINI_PAY'}">
													小程序支付
												</c:if>
												<c:if test="${product.productCode=='BLUESEA_PAY'}">
													蓝海计划
												</c:if>
											</td>
											<td>${product.signRate}</td>
											<td><fmt:formatDate value="${product.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											
											<td>
												<c:if test="${product.registerWay == 'PC'}">
													PC
										    	</c:if>
										    	<c:if test="${product.registerWay == 'H5'}">
													H5
										    	</c:if>
										    	<c:if test="${product.registerWay == 'HT'}">
													后台
										    	</c:if>
											</td>
											<td>
												<c:if test="${product.signStatus == '02'}">
													待审核
												</c:if>
												<c:if test="${product.signStatus == '04'}">
													审核不通过
												</c:if>
												<c:if test="${product.signStatus == '00'}">
													审核通过
												</c:if>
											</td>
											<td>
												<c:if test="${product.signStatus == '02'}">
													待审核
												</c:if>
												<c:if test="${product.signStatus == '00'}">
													审核通过
												</c:if>
												<c:if test="${product.signStatus == '04'}">
													${product.auditInfo}
												</c:if>
											</td>
											<td>
												<c:if test="${product.signStatus == '02'}">
													<button type="button" onclick="auditProduct(this,0);" data-toggle='modal' data-target="#auditProductModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >审核</button> 
												</c:if>
												<c:if test="${product.signStatus == '04'||product.signStatus == '00'}">
													<button type="button" onclick="auditProduct(this,1);" data-toggle='modal' data-target="#auditProductModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >预览</button> 
												</c:if>
												
											</td>
											 
										</tr>
									</c:forEach>
									<c:if test="${empty productList}">
										<tr>
											<td colspan="9" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty productList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
			</div><!-- /.page-content -->
			
		
		<div class="modal fade in" style="z-index: 1040;" id="auditProductModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width:60%;z-index:90;">
		      <div class="modal-content" style="width:950px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		         	<input type="hidden" id="merchantCode" />
		         	<input type="hidden" id="registerWay" >
		         	<input type="hidden" id="productCode" >
		         	<input type="hidden" id="id" >
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel">产品信息</h4>
		         </div>
		         <div class="modal-body">
						<table id="sample-table-2" class="list-table">
						<tbody>
                            <tr>
						    	<td colspan="8" class="headlerPreview">基本资料</td>
					        </tr>
						    <tr>
								<td class="td-left" width="18%">注册方式：</td>
								<td  class="td-right" width="32%"> 
									<input type="text" id="registerWay_"  readonly="readonly" name="registerWay_" maxlength="100"  placeholder="注册方式" style="width:90%">
								</td>
								
								<td class="td-left" width="18%">商户名称：</td>
								<td  class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" readonly="readonly"  style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">注册地址：</td>
                                <td class="td-right" width="32%"> 
								   <input style="width:90%" type="text" id="representativeAddr" name="representativeAddr" readonly>
							    </td>
						    </tr>
                            <tr>
						    	<td colspan="8" class="headlerPreview">签约产品资料</td>
					        </tr>
						    <tr>
								<td class="td-left" width="18%">签约类型：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="product_type" readonly name="product_type" maxlength="100" placeholder="微商户名称" style="width:90%">
								</td>
								
								<td class="td-left" width="18%">期望费率：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="signRate" name="signRate" readonly  style="width:90%">
								</td>
								</tr>
							<!-- <tr>
								<td class="td-left" width="18%">网站URL：</td>
								<td class="td-right" width="32%">
									<input style="width:90%" type="text" id="categoryDef" readonly>
                                </td>
	
						    </tr>
						     -->
						<tr id="H5" style="display: none">
							<td class="td-left" width="18%"><span >网站URL：</span></td>
							<td class="td-right" width="32%"> 
								<input type="text" id="H5address" name="H5address" readonly="readonly"  style="width:90%">
							</td>
						</tr>
						
						<tr id="App"  style="display: none">
							<td class="td-left" width="18%"><span >APP地址：</span></td>
							<td class="td-right" width="32%"> 
								<input type="text" id="AppAddress" name="AppAddress" readonly="readonly"  style="width:90%">
							</td>
							<td class="td-left" width="18%"><span >APP名称：</span></td>
							<td class="td-right" width="32%"> 
								<input type="text" id="APPName" name="APPName" readonly="readonly"  style="width:90%">
							</td>
						</tr>
						
						
						<tr id="Account" style="display: none">
							<td class="td-left" width="18%"><span >公众号信息：</span></td>
							<td class="td-right" width="32%"> 
								<input type="text" id="AccountInfo" name="AccountInfo" readonly="readonly"  style="width:90%">
							</td>
						</tr>
							
						<tr>
							<td class="td-left">门头照：</td>
							<td class="td-right" colspan="8" id="hiddendoorPhotoDiv">
									
							</td>
						</tr>
						
						<tr id="xiaoP" style="display: none">
								<td class="td-left">打印小票：</td>
								<td class="td-right" colspan="8">
									<a data-toggle="modal" class="tooltip-success printReceiptsClick" data-target="#previewImageModal">
									<label id="printReceiptsDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="printReceiptsImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="printReceiptsVal02">  
									</div>
								</td>
							</tr>
							<tr id="faP" style="display: none">
								<td class="td-left">发票：</td>
								<td class="td-right" colspan="8">
									<a data-toggle="modal" class="tooltip-success invoiceClick" data-target="#previewImageModal">
									<label id="invoiceDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="invoiceImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="invoiceVal02">  
									</div>
								</td>
							</tr>
							
							<tr id="gzh" style="display: none">
								<td class="td-left">公众号流程截图：</td>
								<td class="td-right" colspan="8">
									<a data-toggle="modal" class="tooltip-success publicAccountClick" data-target="#previewImageModal">
									<label id="publicAccountDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="publicAccountImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="publicAccountVal02">  
									</div>
								</td>
							</tr>
							
							<tr id="ymba" style="display: none">
								<td class="td-left">域名备案截图：</td>
								<td class="td-right" colspan="8">
									<a data-toggle="modal" class="tooltip-success h5DomainNameClick" data-target="#previewImageModal">
									<label id="h5DomainNameDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="h5DomainNameImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
									</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5DomainNameVal02">  
									</div>
								</td>
							</tr>
							 <tr id="ymzs" style="display: none">
								<td class="td-left">域名证书：</td>
								<td class="td-right" colspan="8"> 
									<a data-toggle="modal" class="tooltip-success h5DomainCertificateClick" data-target="#previewImageModal">
										<label id="h5DomainCertificateDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="h5DomainCertificateImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5DomainCertificateVal02"> 
									</div>
								</td>
							</tr>
                            <tr id="www" style="display: none">
								<td class="td-left">文网文：</td>
								<td class="td-right" colspan="8"> 
									<a data-toggle="modal" class="tooltip-success h5TextSnippetsClick" data-target="#previewImageModal">
										<label id="h5TextSnippetsDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="h5TextSnippetsImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5TextSnippetsVal02"> 
									</div>
								</td>
							</tr>
							<tr id="cs" style="display: none">
								<td class="td-left">收银台照：</td>
								<td class="td-right" colspan="8"> 
									<a data-toggle="modal" class="tooltip-success h5TextSnippetsClick" data-target="#previewImageModal">
										<label id="blueSeaCheckstandDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="blueSeaCheckstandImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5TextSnippetsVal02"> 
									</div>
								</td>
							</tr>
							<tr id="se" style="display: none">
								<td class="td-left">店内环境照：</td>
								<td class="td-right" colspan="8"> 
									<a data-toggle="modal" class="tooltip-success h5TextSnippetsClick" data-target="#previewImageModal">
										<label id="blueSeaStoreEnviromentDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="blueSeaStoreEnviromentImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5TextSnippetsVal02"> 
									</div>
								</td>
							</tr>
							<tr id="pl" style="display: none">
								<td class="td-left">主流餐饮平台入驻商户展示页面照：</td>
								<td class="td-right" colspan="8"> 
									<a data-toggle="modal" class="tooltip-success h5TextSnippetsClick" data-target="#previewImageModal">
										<label id="blueSeaPlatformDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="blueSeaPlatformImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" src="">
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75">
										<input type="hidden" id="h5TextSnippetsVal02"> 
									</div>
								</td>
							</tr>
							</tbody></table><table id="sample-table-3" class="list-table">
							
							</table>		
		         </div>
		         <div id="hiddenView" class="modal-footer" style="display: none;">
		            	<button type="button" id="" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>
		         </div> 
		         <div id="hiddenCheck"  class="modal-footer" style="display: none">
		            	<button type="button" onclick="notpass();" class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal'  data-target="#firstAuditMessageModel" >审核不通过</button>
		            	<button type="button" onclick="pass();" class="btn btn-primary firstAuditPassBtn">审核通过</button>
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
	
		<label><input name="test"   type="checkbox" value="门头照不清晰" />&nbsp;门头照不清晰</label></br>			
		<label><input name="test"   type="checkbox" value="期望费率不符" />&nbsp;期望费率不符</label></br>
		<div id="h5_pay" style="display: none">
			<label><input name="test"   type="checkbox" value="网站URL填写错误" />&nbsp;网站URL填写错误</label></br>
			<label><input name="test"   type="checkbox" value="域名备案截图不清晰 " />&nbsp;域名备案截图不清晰</label></br>		
			<label><input name="test"   type="checkbox" value="域名备案截图不符" />&nbsp;域名备案截图不符 </label> </br>
			<label><input name="test"   type="checkbox" value="域名证书不清晰" />&nbsp;域名证书不清晰</label></br> 
			<label><input name="test"   type="checkbox" value="域名证书不符" />&nbsp;域名证书不符 </label> </br>
			<label><input name="test"   type="checkbox" value="文网文证书不清晰" />&nbsp;文网文证书不清晰 </label></br> 			
			<label><input name="test"   type="checkbox" value="文网文证书不符" />&nbsp;文网文证书不符 </label></br> 
		</div>
		<div id="app_pay" style="display: none">
			<label><input name="test"   type="checkbox" value="App下载链接填写错误" />&nbsp;App下载链接填写错误</label></br>
			<label><input name="test"   type="checkbox" value="AppID填写错误 " />&nbsp;AppID填写错误</label></br>		
		</div>
		<div id="blueSea_pay" style="display: none">
			<label><input name="test"   type="checkbox" value="收银台照不清晰" />&nbsp;收银台照不清晰</label></br>
			<label><input name="test"   type="checkbox" value="店内环境照片不清晰 " />&nbsp;店内环境照片不清晰</label></br>
			<label><input name="test"   type="checkbox" value="主流餐饮平台入驻商户展示页面照片不清晰 " />&nbsp;主流餐饮平台入驻商户展示页面照片不清晰 </label></br>			
		</div>
		
		<div id="gzh_pay" style="display: none">
			<label><input name="test"   type="checkbox" value="公众号AppID填写错误" />&nbsp;公众号AppID填写错误</label></br>
		</div>
			
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
$(function(){
	/** 缓存 **/
	var products = ${productList };
	var product = $("tr.product");
	$.each(products,function(i,value){
		$.data(product[i],"product",value);
	});
	
	var productCode_ = $("#productCode_").val();
	$("#productCode").val(productCode_);
	
	$('input[type="checkbox"][name="test"]').each(
            function() {
                   $(this).attr("checked",false);  
            }
        );
});
function auditProduct(obj,flag_){
	var merchant = $.data($(obj).parent().parent()[0],"product");
	
	$("#auditProductModel #registerWay").val(merchant.registerWay);
	$("#auditProductModel #id").val(merchant.id);
	
	if("H5"==merchant.registerWay){
		$("#auditProductModel #registerWay_").val("H5注册");
	}else if("PC"==merchant.registerWay){
		$("#auditProductModel #registerWay_").val("PC端注册");
	}else if("HT"==merchant.registerWay){
		$("#auditProductModel #registerWay_").val("后台注册");
	}else{
		$("#auditProductModel #registerWay_").val("其他");
	}
	
	$("#auditProductModel #custName").val(merchant.custName);
	$("#auditProductModel #representativeAddr").val(merchant.representativeAddr);
	$("#auditProductModel #signRate").val(merchant.signRate);
	
	
	$("#auditProductModel #merchantCode").val(merchant.merchantCode);
	$("#auditProductModel #productCode").val(merchant.productCode);
	
	if(flag_==1){
		$("#hiddenCheck").css("display","none");
		$("#hiddenView").css("display","");
	}else{
		$("#hiddenCheck").css("display","");
		$("#hiddenView").css("display","none");
	}
	
	$.post(window.Constants.ContextPath +'<%=ProductAudingPath.BASE + ProductAudingPath.SELDOORSCAN%>',{
		"merchantCode":merchant.merchantCode
	},function(data){
         $("#hiddendoorPhotoDiv").html('');
         
        if("SCAN_PAY"==merchant.productCode){
        	 $("#lowRate").text("扫码支付(低价费率："+data.SCAN_PAY+")：");
        	 $("#auditProductModel #product_type").val("扫码支付");
        	 $("#App").css("display","none");
        	 $("#Account").css("display","none");
        	 $("#H5").css("display","none");
     	}else if("APP_PAY"==merchant.productCode){
     		$("#lowRate").text("APP支付(低价费率："+data.APP_PAY+")：");
     		$("#App").css("display","");
     		$("#AppAddress").val(merchant.appDownloadAddress);
     		$("#auditProductModel #product_type").val("APP支付");
     		$("#APPName").val(merchant.appId);
       	 	$("#Account").css("display","none");
       	 	$("#H5").css("display","none");
     	}else if("OA_PAY"==merchant.productCode){
     		$("#lowRate").text("公众号支付(低价费率："+data.OA_PAY+")：");
     		$("#auditProductModel #product_type").val("公众号支付");
     		$("#Account").css("display","");
     		$("#AccountInfo").val(merchant.qcAppId);
     		$("#App").css("display","none");
       		$("#H5").css("display","none");
     	}else if("H5_PAY"==merchant.productCode){
     		$("#lowRate").text("H5支付(低价费率："+data.H5_PAY+")：");
     		$("#auditProductModel #product_type").val("H5支付");
     		$("#H5").css("display","");
     		$("#H5address").val(merchant.website);
     		$("#App").css("display","none");
       	 	$("#Account").css("display","none");
     	}else if("MINI_PAY"==merchant.productCode){
     		$("#lowRate").text("小程序支付(低价费率："+data.MINI_PAY+")：");
     		$("#auditProductModel #product_type").val("小程序支付");
     		$("#App").css("display","none");
	       	$("#Account").css("display","none");
	       	$("#H5").css("display","none");
     	}
         
         var doorPhotos=data.scanPath;
         var doorPhoto = doorPhotos.split(";");
        /*  $("#hiddendoorPhotoDiv").append("<td class='td-left' id='doorPhotos'>门头照：</td>"+
        		 "<td class='td-right' colspan='8'>"
         ); */
         for(var i=0;i<doorPhoto.length;i++){
        	if(doorPhoto[i]!=""){
 				 $("#hiddendoorPhotoDiv").append(
     		    	 	'<a data-toggle="modal" class="tooltip-success doorPhotoClick"  data-target="#previewImageModal" >'+
     		    	 	'<label id="doorPhotoDiv1"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">'+
     		    	 	'<img  name="doorPhotoImageDiv'+i+'" onclick="bigImg(this)"  style="width:100%;height:100%;"  src="<%=request.getContextPath()+ProductAudingPath.BASE+ ProductAudingPath.IMAGE %>?scanPath='+doorPhoto[i]+'&certifyType=08'+'"'+
     		    	 	'</label></a>'
 				 );
     		    
        	}
         }
        /*  $("#hiddendoorPhotoDiv").append('</td>'); */
        
        //小票
         if(data.receiptsScanPath!=""&&data.receiptsScanPath!=null){
        	 $("#auditProductModel #printReceiptsImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?scanPath=" + data.receiptsScanPath + "&certifyType=17");
        	 $("#xiaoP").css("display","");
         }
       //发票
         if(data.invoiceScanPath!=""&&data.invoiceScanPath!=null){
        	 $("#auditProductModel #invoiceImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?scanPath=" + data.invoiceScanPath + "&certifyType=12");
        	 $("#faP").css("display","");
         }
       
       //如果是h5则显示 域名备案截图，域名证书，文网文
     	if("H5_PAY"==merchant.productCode){//扫码支付
     		//alert(merchant.custId);
     		if(data.h5DomainNameScanPath!=""&&data.h5DomainNameScanPath!=null){
     			$("#auditProductModel #h5DomainNameImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=13");
     			$("#ymba").css("display","");
     		}else{
     			$("#ymba").css("display","none");
     		}
     		if(data.h5DomainCertificateScanPath!=""&&data.h5DomainCertificateScanPath!=null){
     			$("#auditProductModel #h5DomainCertificateImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=14");			$("#ymba").css("display","");
     			$("#ymzs").css("display","");
     		}else{
     			$("#ymzs").css("display","none");
     		}
     		if(data.h5TextSnippetsScanPath!=""&&data.h5TextSnippetsScanPath!=null){
     			$("#auditProductModel #h5TextSnippetsImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=15");
     			$("#www").css("display","");
     		}else{
     			$("#www").css("display","none");
     		}
     		$("#gzh").css("display","none");
     		$("#cs").css("display","none");
     		$("#se").css("display","none");
     		$("#pl").css("display","none");
     	}else if("OA_PAY"==merchant.productCode){
     		if(data.publicAccountScanPath!=""&&data.publicAccountScanPath!=null){
     			$("#auditProductModel #publicAccountImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=16");
     			$("#gzh").css("display","");
     		}else{
     			$("#gzh").css("display","none");
     		}
     		$("#www").css("display","none");
     		$("#ymzs").css("display","none");
     		$("#ymba").css("display","none");
     		$("#cs").css("display","none");
     		$("#se").css("display","none");
     		$("#pl").css("display","none");
     		
     		//蓝海计划
     	}else if("BLUESEA_PAY"==merchant.productCode){
     		if(data.checkstandPath!=""&&data.checkstandPath!=null){
     			$("#auditProductModel #blueSeaCheckstandImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=18");
     			$("#cs").css("display","");
     		}else{
     			$("#cs").css("display","none");
     		}
     		if(data.storeEnviromentPath!=""&&data.storeEnviromentPath!=null){
     			$("#auditProductModel #blueSeaStoreEnviromentImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=19");
     			$("#se").css("display","");
     		}else{
     			$("#se").css("display","none");
     		}
     		if(data.platformPath!=""&&data.platformPath!=null){
     			$("#auditProductModel #blueSeaPlatformImageDiv").attr('src',"<%=request.getContextPath() + ProductAudingPath.BASE + ProductAudingPath.IMAGE %>?custId=" + merchant.custId + "&certifyType=20");
     			$("#pl").css("display","");
     		}else{
     			$("#pl").css("display","none");
     		}
     		$("#www").css("display","none");
     		$("#ymzs").css("display","none");
     		$("#ymba").css("display","none");
     		$("#gzh").css("display","none");
     	}else{
     		$("#www").css("display","none");
     		$("#ymzs").css("display","none");
     		$("#ymba").css("display","none");
     		$("#gzh").css("display","none");
     		$("#cs").css("display","none");
     		$("#se").css("display","none");
     		$("#pl").css("display","none");
     	}
         
	},'json');
	
	
	
	
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
 	$('.search-table #productCode').val('');
 }); 
 
 function firstAuditNotPassBtn(){
	   var merchantCode = $("#auditProductModel #merchantCode").val();
	   var productCode = $("#auditProductModel #productCode").val();
	   var id = $("#auditProductModel #id").val();
	   
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
	   if(null!=chk_value && chk_value.length>0 && 'null'!=chk_value){
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
			url:window.Constants.ContextPath +'<%=ProductAudingPath.BASE+ ProductAudingPath.AUDITNOTPASS %>',
			data:
			{
				"merchantCode" 	: merchantCode,
				"message"	:messages,
				"productCode"     :productCode,
				"id"            :id
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
 };
 
 function notpass(){
	   var productCode = $("#auditProductModel #productCode").val();
		if("APP_PAY"==productCode){
	   		$("#h5_pay").css("display","none");
	   		$("#app_pay").css("display","");
	   		$("#gzh_pay").css("display","none");
	   		$("#blueSea_pay").css("display","none");
	   	}else if("OA_PAY"==productCode){
	   		$("#h5_pay").css("display","none");
	   		$("#app_pay").css("display","none");
	   		$("#gzh_pay").css("display","");
	   		$("#blueSea_pay").css("display","none");
	   	}else if("H5_PAY"==productCode){
	   		$("#h5_pay").css("display","");
	   		$("#app_pay").css("display","none");
	   		$("#gzh_pay").css("display","none");
	   		$("#blueSea_pay").css("display","none");
	   	}else if("BLUESEA_PAY"==productCode){   		
	   		$("#blueSea_pay").css("display","");
	   		$("#h5_pay").css("display","none");
	   		$("#app_pay").css("display","none");
	   		$("#gzh_pay").css("display","none");
	   		
	   	}
	   	else{
	   		$("#h5_pay").css("display","none");
	   		$("#app_pay").css("display","none");
	   		$("#gzh_pay").css("display","none");
	   		$("#blueSea_pay").css("display","none");
	   	}
}
 
 function pass(){
	   var merchantCode = $("#auditProductModel #merchantCode").val();
	   var productCode = $("#auditProductModel #productCode").val();
	   var id = $("#auditProductModel #id").val();
		   
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=ProductAudingPath.BASE+ ProductAudingPath.AUDITPASS %>',
			data:
			{
				"merchantCode" 	: merchantCode,
				"productCode"   :productCode,
				"id"            :id
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
		var productCode = $('.search-table #productCode').val();
		
		var src ="<%= request.getContextPath()+ ProductAudingPath.BASE+ ProductAudingPath.EXPORTMERCHANPRODUCT %>?merchantCode="+
		 merchantCode+
		"&custName="+
		 custName+
		"&productCode="+
		productCode;
		$(".exportBut").attr("href",src);
		
	});
</script>
</html>