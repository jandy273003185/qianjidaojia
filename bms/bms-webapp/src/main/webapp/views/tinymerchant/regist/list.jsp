<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>微商户列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=TinyMerchantPath.BASE + TinyMerchantPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户二维码编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">注册时间：</td>
									<td class="td-right">
									<input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									</td>
								</tr>
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" id="email"  value="${queryBean.email }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TinyMerchantPath.BASE + TinyMerchantPath.LIST %>">
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
						
						<div class="list-table-header">商户列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="10%">商户二维码编号</th>
										<th width="10%">手机号</th>
										<th width="10%">账号</th>
										<th width="10%">商户名称</th>
										<th width="12%">注册时间</th>
										<th width="12%">商户状态</th>
										<!-- <th width="10%">银行开户名</th> -->
										<!-- <th width="12%">报备状态</th> -->
										<!-- <th width="12%">报备审核状态</th> -->		
										<th width="12%">操作</th>
										<!-- <th width="12%">查看收款二维码</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${merchantList }" var="merchant" varStatus="i">
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<td>${merchant.mobile}</td>
											<td>${merchant.email }</td>
											<td>${merchant.custName }</td>
											<%-- <td><fmt:formatDate value="${merchant.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
											<td>${merchant.createTime }</td>
											<td>
												<c:choose>
													<c:when test="${merchant.merchantState eq 00 }">
														正常
													</c:when>
													<c:when test="${merchant.merchantState eq 01 }">
														停用
													</c:when>
													<c:when test="${merchant.merchantState eq 02 }">
														登录账户冻结
													</c:when>
													<c:when test="${merchant.merchantState eq 03 }">
														注册待激活
													</c:when>
													<c:when test="${merchant.merchantState eq 04 }">
														商户审核中
													</c:when>
													<c:when test="${merchant.merchantState eq 05 }">
														前台商户协议待录入
													</c:when>
													<c:when test="${merchant.merchantState eq 06 }">
														后台商户协议待录入
													</c:when>
												</c:choose>
											</td>
											<%-- <td>
												<c:choose>
													<c:when test="${merchant.filingStatus eq 00 }">
														未报备
													</c:when>
													<c:when test="${merchant.filingStatus eq 01 }">
														已报备
													</c:when>
												</c:choose>
											</td> --%>
											<%-- <td>
												<c:choose>
													<c:when test="${merchant.filingAuditStatus eq 00 }">
														成功
													</c:when>
													<c:when test="${merchant.filingAuditStatus eq 99 }">
														失败
													</c:when>
													<c:when test="${merchant.filingAuditStatus eq 01 }">
														待审核
													</c:when>
													<c:when test="${merchant.filingAuditStatus eq 02 }">
														提交审核
													</c:when>
												</c:choose>
											</td> --%>
											<%-- <td>${merchant.bankAcctName }</td> --%>
											<!-- <td>测试账户</td> -->
											<td>	
												<!-- ${i.index } -->
												<%-- 
												<input type="hidden" id="qifenqian_flag" value="${i.index + 1 }" />
												<input type="hidden" name="custId_0${i.index + 1 }" id="custId_0${i.index + 1 }" value="${merchant.custId}"> 
												--%>
												<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}"> 
												<%-- 
												<input type="hidden" name="emailMerchant" id="emailMerchant" value="${merchant.email}">	
												<input type="hidden" name="custName_01" id="custName_01" value="${merchant.custName}">
												<input type="hidden" name="attachStr" id="attachStr" value="${merchant.attachStr}">
												<input type="hidden" name="authId_01" id="authId_01" value="${merchant.authId}"> 
												--%>
												
												<%-- 
												<gyzbadmin:function url="<%=TinyMerchantPath.BASE + TinyMerchantPath.UPDATEEMAIL %>">	
													<button type="button" onclick="toUpdateEmail(this)" data-toggle='modal'  class="btn btn-primary btn-xs" data-target="#updateEmail" >修改邮箱</button>
												</gyzbadmin:function> 
												--%>
												
												<gyzbadmin:function url="<%=TinyMerchantPath.BASE + TinyMerchantPath.UPDATETINYMERCHANTINFO%>">  	
													<button type="button" onclick="updateTinyMerchantInfo(this,'edit')" data-toggle='modal' data-target="#updateMerchant" class="btn btn-primary btn-xs qifenqian_update_tc" >修改商户信息</button>
												</gyzbadmin:function>
                                            	
                                            	<%-- <c:if test="${merchant.filingAuditStatus == 99 }">
                                            		<button type="button" onclick="updateTinyMerchantInfo(this,'edit')" data-toggle='modal' data-target="#updateMerchant" class="btn btn-primary btn-xs qifenqian_update_tc" >修改报备信息</button>
                                            	</c:if> --%>
                                            	<button type="button" onclick="updateTinyMerchantInfo(this,'preview')" data-toggle='modal' data-target="#updateMerchant" class="btn btn-primary btn-xs qifenqian_view_tc" >预览</button> 
											</td>
											<!-- <td>
												<button type="button"  data-toggle='modal' data-target="#buildMerchant"  class="btn btn-primary btn-xs qifenqian_code_tc" >付款二维码</button> 
											</td> -->
										</tr>
									</c:forEach>
									<c:if test="${empty merchantList}">
										<tr>
											<td colspan="9" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty merchantList}">
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
	<div class="modal fade" style="z-index:1040;" id="buildMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:20%;z-index:90;" >
			<div class="modal-content" style="width:370px;height:410px;" id="merchantDiv">
				<div id="qrcode" style="position: relative ;left:30px;top:30px; "></div>
				<br><br><br>
				<div>
					<tr>
					 	<td class="td-left"><font size=3>&nbsp;商户名称：</font></td>
					 	<td class="td-right" >
					 		<input type="text" id="custName_tc" name="custName" readonly="readonly" style="width:60%; font-size:6px">
					 	</td>
					</tr>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" style="z-index:1040;" id="updateMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:60%;z-index:90;" >
			<div class="modal-content" style="width:950px;" id="merchantDiv">
		    	<div class="modal-header" style="background-color:0099CC">
		        	<button type="button" class="close" data-dismiss="modal" id="updateMerchantClose" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">微商户信息</h4>
		        </div>
		        <div class="modal-body">
					<table id="sample-table-2" class="list-table" >
						<tr class="edit_email">
							<td class="td-left" width="18%">账号：</td>
							<td class="td-right" width="32%"> 
								<input type="text" id="email_04" name="email" readonly="readonly" style="width:35%">
								<input type="hidden" name="custName_id_01" id="custName_id_01"  />
							</td>
						</tr>
						<!-- spring 20170407 13:50 pm add -->
						<tr class="edit_merchantCode">
						 	<td class="td-left">商户二维码编号：</td>
						 	<td class="td-right" colspan="3">
						 		<input type="text" id="merchantCode_tc" name="merchantCode" readonly="readonly" placeholder="商户编号" style="width:35%">
						 		<i class="icon-leaf blue"></i>
								<label class="label-tips" id="merchantCodeLabel"></label>
						 	</td>
						</tr>
						<tr>
						 	<td class="td-left">商户名称：</td>
						 	<td class="td-right" colspan="3">
						 		<input type="text" id="custName_tc" name="custName" placeholder="客户姓名" style="width:35%">
						 		<i class="icon-leaf blue"></i>
								<label id="custNameLabel" class="label-tips"></label>
						 	</td>
						</tr>
						<tr>
						 	<td class="td-left">商户类型：</td>
						 	<td class="td-right" colspan="3">
						 		<select name="custType" id="custType" style="width:150px;" onchange="getCategoryList();">
										<option value="">--请选择商户类型--</option>
										<option value="0">个人</option>
										<option value="1">企业</option>
									</select>
								<label id="custTypeLab" class="label-tips"></label>
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
						<tr>
								<td class="td-left">商户角色<span style="color:red">*</span></td>
								<td class="td-right"> 
									<span class="input-icon">
										<select name="mchRole" style="width:100;" id="mchRole" onchange="roleChange();">
											<option value="" >- 请选择商户角色 -</option>
											<option value="0">线上商户</option>
											<option value="1">线下商户</option>
										</select>		
										<label class="label-tips" id="mchRoleLab"></label>
									</span>
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
								<td class="td-left">行业类目<span style="color:red">*</span></td>
								<td class="td-right">
									<span class="input-icon">
									<select name="categoryTypeId" id="categoryTypeId" style="width:180px;" onchange="getCategoryList();" >
										<option value="">--请选择类目类别--</option>
										<option value="100">实体</option>
										<option value="200">虚拟</option>
										<option value="300">其他</option>
									</select>
									<select name="categoryId" id="categoryId" style="width:300px;">
										<option value="" id="category">--请选择类目--</option>
									</select>
									<label id="categoryIdLabel" class="label-tips"></label>
									</span>
								</td>
						</tr>
						<tr>
								<td class="td-left">省市地区<span style="color:red">*</span></td>
								<td class="td-right">
									<div class="col-xs-4 pd0" style="padding:0;">
                                    <select class="form-control" id="province" onchange="getCityList();">
                                         	<option value="">--请选择--</option>
                                    </select>
	                                </div>
	                                <div class="col-xs-4 pd0" style="width:31.33%;margin:0 1%;padding:0;">
	                                <select class="form-control" id="city" onchange="getAreaList();">
	                                    <option value="" id="cityDef">--请选择--</option>
	                                </select>
	                                </div>
	                                <div class="col-xs-4 pd0" style="padding:0;">
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
										<input type="text" id="custAdd" name="custAdd" placeholder="商户地址" maxlength="100" style="width:250px;">
										<i class="icon-leaf blue"></i>
										<label id="custAddLabel" class="label-tips"></label>
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
						<tr class="edit_hide_fcontactunitnumber">
							<td class="td-left">往来单位编号：</td>
							<td class="td-right">
								<input type="text" id="fcontactunitNumber" name="fcontactunitNumber" placeholder="往来单位编号" style="width:35%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="fcontactunitNumberLabel"></label>
							</td>
							
						</tr>
						<tr class="businessLicenseNumberTr">
							<td class="td-left">营业执照号码或统一社会信用代码：</td>
							<td class="td-right">
								<input type="text" id="businessLicense" name="businessLicense" placeholder="营业执照注册号" style="width:35%">
								<input type="hidden" id="businessLicenseHiddenData" name="businessLicense" >
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="businessLicenseLabel"></label>
							</td>
							
							
						 </tr>
						  <tr class="businessLicensePhotoTr">
						  	<td class="td-left">商户登陆状态：</td>
								<td class="td-right">
									<select id="merchantState" name ="merchantState" class="width-28">
										<option value="">--登陆状态--</option>
										<option value="00">正常</option>
										<option value="01">停用</option>
										<option value="02">冻结</option>
										<option value="03">注册待激活</option>
										<option  value="04"> 商户审核中 </option>
										<option value="05"> 前台商户协议待录入 </option>
										<option value="06"> 后台商户协议待录入 </option>
									</select>								
								</td>
						  </tr>
						 <tr class="businessLicensePhotoTr">
						 	<td class="td-left">营业执照扫描件：</td>
							<td class="td-right" colspan="3">
								<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
									<label id="businessPhotoDiv" class="uploadImage"  style="background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
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
						<tr>
							<td class="td-left" >手持身份证图片正面：</td>
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
						<tr id="iDCardPhotoTr">
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
					 
						
						  <tr id="hiddenopenAccountDiv">
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
										<label id="openAccount0Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="openAccountImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="openAccountVal02"  />  
										<input type="file" name="openAccount" id="openAccount" onchange="showOpenAccountImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
																			
								</td>
							</tr>
							<tr id="hiddenbankCardPhotoDiv">
								<td class="td-left" >银行卡照：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showbankCardPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							
							<tr id="hidenetWorkPhoto">
								<td class="td-left" >网络文化经营许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle='modal' class="tooltip-success netWorkPhotoClick"  data-target="#previewImageModal"  >
										<label id="netWorkPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="netWorkPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="netWorkPhotoVal02"  />  
										<input type="file" name="netWorkPhoto" id="netWorkPhoto" onchange="shownetWorkPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							
						
						
						<tr>
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
						 	<td class="td-left">身份证号码：</td>
						 	<td class="td-right"  colspan="3"> 
								<span class="input-icon">
									<input type="text" id="certifyNo" name="certifyNo" placeholder="身份证号码"/>
									<i class="icon-leaf blue"></i>
									<label id="certifyNoLabel" class="label-tips"></label>
								</span>
							</td>
						</tr>
						<tr class ="isFiling">
							<td class="td-left">是否报备商户信息：</td>
							<td class="td-right">
								<select  id="isFiling"  name="isFiling">
										<option value ="">--请选择--</option>
										<option value ="YES">是</option>
										<option value ="NO">否</option>
								</select>
							</td>
						</tr>
					</table>
		        </div>
		        <div class="modal-footer">
		        	<input type="hidden" id="tc_test" name="tc_test" value="" />
					<button type="button" id="" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>
		           	<button type="button" class="btn btn-primary updateMerchantBtn" onclick="confirmMerchantInfo()">提交</button>
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

<%-- <script type="text/javascript" src="/static/js/jquery.qrcode.min.js?v=${_jsVersion}"></script> --%>
<script src='<c:url value="/static/js/jquery.qrcode.min.js?v=${_jsVersion}"/>'></script>
<script type="text/javascript">
 $(".qifenqian_code_tc").click(function(){
	 $('#qrcode').empty();
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	var merchantCode =merchant.merchantCode;
	$("#buildMerchant #custName_tc").val(merchant.custName);
	var path = "http://zt.qifenmall.com/pub/merchantqr.do?mid=" + merchantCode;
	$("#qrcode").qrcode({
	  	render: "table", //table方式
		width: 300, //宽度
		height:300, //高度
		text: path 
	
		// https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=M9144030035873982X0 //正式环境链接
		// http://zt.qifenmall.com/pub/merchantqr.do?mid=M9144030035873982X0           //测试环境链接 
	}); 

    


	
 });

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

$(function(){
	/** 缓存 **/
	var custIds = ${merchantList };
	var custId = $("tr.merchant");
	$.each(custIds,function(i,value){
		$.data(custId[i],"merchant",value);
	});
	
});

/** 修改弹出层修改 **/
$(".qifenqian_update_tc").click(function(){
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	$("#custNameLabel").text("");
	$("#representativeMobileLabel").text("");
	$("#certifyTypeLabel").text("");
	$("#certifyNoLabel").text("");
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	$("#tc_test").val(merchant.custId);
	$("#updateMerchant #email_04").val(merchant.email);
	$("#updateMerchant #merchantCode_tc").val(merchant.merchantCode);
	$("#updateMerchant #custName_tc").val(merchant.custName);
	$("#updateMerchant #fcontactunitNumber").val(merchant.fcontactunitNumber);
	$("#updateMerchant #businessLicense").val(merchant.businessLicense);
	$("#updateMerchant #businessLicenseHiddenData").val(merchant.businessLicense);
	$("#updateMerchant #certifyType").val(merchant.certifyType);
	$("#updateMerchant #certifyNo").val(merchant.certifyNo);
	$("#updateMerchant #mchRole").val(merchant.mchRole);
	$("#updateMerchant #representativeMobile").val(merchant.mobile);
	$("#updateMerchant #isClear").val(merchant.isClear);
	$("#updateMerchant #custAdd").val(merchant.custAdd);
	$("#updateMerchant #custType").val(merchant.custType);
	$("#updateMerchant #merchantState").val(merchant.merchantState);
	
	
	
});

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
			"categoryType" 	: categoryType
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
/** 修改弹出层预览 */
$(".qifenqian_view_tc").click(function(){
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	var merchant = $.data($(this).parent().parent()[0],"merchant");
	$("#updateMerchant #email_04").val(merchant.email);
	$("#updateMerchant #merchantCode_tc").val(merchant.merchantCode);
	$("#updateMerchant #custName_tc").val(merchant.custName);
	$("#updateMerchant #representativeMobile").val(merchant.mobile);
	$("#updateMerchant #fcontactunitNumber").val(merchant.fcontactunitNumber);
	$("#updateMerchant #businessLicense").val(merchant.businessLicense);
	$("#updateMerchant #businessLicenseHiddenData").val(merchant.businessLicense);
	$("#updateMerchant #certifyType").val(merchant.certifyType);
	$("#updateMerchant #certifyNo").val(merchant.certifyNo);
	$("#updateMerchant #custType").val(merchant.custType);
	$("#updateMerchant #custAdd").val(merchant.custAdd);
	$("#updateMerchant #isClear").val(merchant.isClear);
	$("#updateMerchant #mchRole").val(merchant.mchRole);
	$("#updateMerchant #merchantState").val(merchant.merchantState);
	//$("#updateMerchant #compMainAcct").val(merchant.compMainAcct);
});

/** 更新微商户信息提交 **/
function confirmMerchantInfo(){
	/* $("#custNameLabel").text("");
	$("#representativeMobileLabel").text("");
	$("#certifyTypeLabel").text("");
	$("#certifyNoLabel").text(""); */
	var flag;
	// 校验商户名称
	
	flag = Register.validateCustName($("#updateMerchant #custName_tc").val(),$("#custNameLabel"));
	if(!flag){return false;}
	if(kong.test($("#custType").val())){
		$("#custTypeLab").text("商户类型不能为空！");
		return false;
	}
	// 校验手机号码
	flag = Register.validatePhone($("#representativeMobile").val(),$("#representativeMobileLabel"));
	if(!flag){return false;}
	
	if(kong.test($("#categoryId").val())){
		$("#categoryIdLabel").text("行目类别不能为空");
		return false;
	}
	if(kong.test($("#mchRole").val())){
		$("#mchRoleLabel").text("商户角色不能为空！");
		return false;
	}
	if(kong.test($("#isClear").val())){
		$.gyzbadmin.alertFailure("是否直清不能为空");
		return false;
	}
	if(kong.test($("#country").val())){
		$("#countryLab").text("省份地区不能为空！");
		return false;
	}
	if(kong.test($("#custAdd").val())){
		$("#custAddLabel").text("商户地址不能为空！");
		return false;
	}
	// 校验营业执照注册号
	if ($("#businessLicense").val()!= "" && $("#businessLicense").val() != null) {
		flag = Register.validateBusinessLicense($("#businessLicense").val(),$("#businessLicenseLabel"));
		if(!flag){return false;}
	}

	if(isNull($("#updateMerchant #merchantState")[0])){
		$.gyzbadmin.alertFailure("请选择商户登陆状态");
		return false;
	}
	
	// 校验银行卡号
	// flag = Register.validateCompMainAcct($("#compMainAcct").val().trim(),$("#compMainAcctLabel"));
	// if(!flag){return false;}
	// 校验证件类型
	flag = Register.validateCertifyType($("select[name='certifyType']").val(),$("#certifyTypeLabel"),$("#certifyNo").val());
	if(!flag){return false;}
	// 校验身份证号码
	flag = Register.validateCertifyNo($("#certifyNo").val(),$("#certifyNoLabel"),$("select[name='certifyType']").val());
	if(!flag){return false;}
	
	if($("#businessLicense").val() != $("#businessLicenseHiddenData").val()){
		if(!checkAttach($("#businessPhoto")[0])){
			$.gyzbadmin.alertFailure("由于营业执照注册号已修改，必须重新提交营业执照扫描件");
			return false;
		}
	}
	if(checkAttach($("#businessPhoto")[0])){
		if(isNull($("#businessLicense")[0])){
			$("#businessLicenseLabel").text("必须填写营业执照注册号");
			return false;
		}
		var flag = Register.validateBusinessLicense($("#businessLicense").val(),$("#businessLicenseLab"));
		if(!flag){return false;}
	}
	// 提交前清空所有错误提示栏
	Register.clearAllErrorMsgLabel();
	// 组装修改表单数据
	var custId = $("#tc_test").val().trim();
	var merchantCode = $("#merchantCode_tc").val().trim(); 					// 商户编号
	var custName = $("#updateMerchant #custName_tc").val().trim(); 		    // 客户名称
	var representativeMobile = $("#representativeMobile").val().trim(); 	// 手机号码
	var fcontactunitNumber = $("#fcontactunitNumber").val().trim(); 		// 来往单位编号
	var businessLicense = $("#businessLicense").val().trim(); 				// 营业执照注册号
	var certifyType = $("#certifyType").val();								// 证件类型
	var certifyNo = $("#certifyNo").val().trim(); 							// 身份证号码
	//var compMainAcct = $("#compMainAcct").val().trim();					// 银行卡号
	var agentName = 			$("#agentName").val().trim();
	var categoryId = 			$("#categoryId").val().trim();
	var mchRole =				$("#mchRole").val().trim();
	var custAdd =  				$("#custAdd").val().trim();
	var province = $("#province").val().trim();
	var city = $("#city").val().trim();
	var country = $("#country").val().trim();
 	var isFiling = $("#updateMerchant #isFiling").val(); 
 	var custType = $("#custType").val();
 	var merchantState = $("#merchantState").val().trim();
 	var isClear=$("#isClear").val().trim();
 	
 	
 	
	$.ajaxFileUpload({
		url : window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.UPDATEFILEUPLOAD%>?custId=' + custId,
		secureuri : false,
        fileElementId : ['businessPhoto','certAttribute1','certAttribute2','doorPhoto0','doorPhoto1','doorPhoto2','doorPhoto3','doorPhoto4','openAccount','bankCardPhoto','netWorkPhoto'],
        dataType : 'json',  
        success : function(data, status) {
        	if(data.result=='SUCCESS'){
        		var paths = data.paths;
        		$.post(window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.UPDATETINYMERCHANTINFO%>',{
        			'custId':custId,
        			'custName':custName,
        			'mobile':representativeMobile,
        			'fcontactunitNumber':fcontactunitNumber,
        			'businessLicense':businessLicense,
        			'certifyType':certifyType,
        			'certifyNo':certifyNo,
        			//'compMainAcct':compMainAcct,					// 银行卡号
        			'businessType':data.businessType,				// 营业执照扫描件
    	 			'idCardType_1':data.idCardType_1, 				// 身份证号正面
    	 			'idCardType_2':data.idCardType_2,				// 身份证号反面
    	 			'doorPhotoPath':data.doorPhotoPath,
    	 			'netWorkPhoto':data.netWorkPhoto,
    	 			'openAccount':data.openAccount,
    	 			'bankCardPhoto':data.bankCardPhoto,
    	 			'agentName' :agentName,
    	 			"categoryType" : categoryId,
        			"mchRole" : mchRole,
        			"custAdd" : custAdd,
        			"province":province,
        			"city":city,
        			"country" :country,
        			"isFiling" : isFiling,
        			"custType" : custType,
        			"merchantState" : merchantState,
        			"isClear" :isClear
        		},function(data){
        			$.unblockUI();
    				if(data.result=="SUCCESS"){
    					$("#updateMerchant").hide();
    					$.gyzbadmin.alertSuccess('修改商户成功！', null, function(){
    						window.location.reload(); // 强迫浏览器刷新当前页面
    					});
    				}else{
    					$.gyzbadmin.alertFailure("修改商户失败！" + data.message,null, function(){
    						window.location.reload();
    					});
    				}
        		},'json')
        	} else {
        		$.gyzbadmin.alertFailure("扫描件上传失败,请选择合适的类型");
        	}
		}
	});
}

/** 点击预览和更新 **/
function updateTinyMerchantInfo(obj,option){
	var merchant = $.data($(obj).parent().parent()[0],"merchant");
	$("#updateMerchant .businessLicenseNumberTr").show();
	$("#updateMerchant .businessLicensePhotoTr").show();
	$("#updateMerchant #custType").val(merchant.custType);
	
	$("#updateMerchant #isClear").val(merchant.isClear);
	$("#updateMerchant img").attr("src","");
/* 	$("#updateMerchant #businessPhotoImageDiv").attr("src","");
	$("#updateMerchant #certAttribute1ImageDiv").attr("src","");
	$("#updateMerchant #certAttribute2ImageDiv").attr("src",""); */
	/** 预览 **/
	if(option == 'preview'){ 
		// 隐藏提交按钮
		$('#updateMerchant .updateMerchantBtn').hide(); 
		// 预览的时候显示往来单位编号
		$("#updateMerchant .edit_hide_fcontactunitnumber").show();	
		// 让input框和select框
		$('#updateMerchant input,select').prop("disabled", true); 
		$("#updateMerchant .businessLicenseNumberTr").show();
		$("#updateMerchant .businessLicensePhotoTr").show();
		var custType= $("#updateMerchant #custType").val();
	    var grade=merchant.categoryType;
	    if(grade=="177"){
	    	 $("#hidenetWorkPhoto").show();
			 $("#netWorkPhotoImageDiv").show();
			 $("#updateMerchant #netWorkPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=09");
				
	    }else{
	    	$("#updateMerchant #hidenetWorkPhoto").hide();
	    }
		var custId = $(obj).parent().find('#custId_01').val();
		if(null!=custType && custType=="0"){
			$("#updateMerchant #hiddenopenAccountDiv").hide();
			$("#updateMerchant #hiddenbankCardPhotoDiv").show();
			$("#bankCardPhotoImageDiv").show();
			$("#updateMerchant #bankCardPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=07");
			
		}
		if(null!=custType && custType=="1"){
			$("#updateMerchant #hiddenbankCardPhotoDiv").hide();
			$("#updateMerchant #hiddenopenAccountDiv").show();
			$("#openAccountImageDiv").show();
			$("#updateMerchant #hidenetWorkPhoto").show();
			$("#updateMerchant #openAccountImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=03");
			
		}
			
		
			
		 
	} 
	/** 编辑 **/
	if(option == 'edit'){
		$('#updateMerchant .updateMerchantBtn').show();
		// 编辑的时候隐藏掉往来单位编号
		$("#updateMerchant .edit_email").hide();
		$("#updateMerchant .edit_merchantCode").hide();
		$("#updateMerchant .edit_hide_fcontactunitnumber").hide();	
		$('#updateMerchant input,select').prop("disabled", false);
		$('#updateMerchant #categoryTypeId').prop("disabled", true);
		var custType= $("#updateMerchant #custType").val();
		var custId = $(obj).parent().find('#custId_01').val();
		var grade=merchant.categoryType;
		    if(grade=="177"){
		    	 $("#hidenetWorkPhoto").show();
				 $("#netWorkPhotoImageDiv").show();
				 $("#updateMerchant #netWorkPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=09");
					
		    }else{
		    	$("#updateMerchant #hidenetWorkPhoto").hide();
		    }
		if(null!=custType && custType=="0"){
			$("#updateMerchant #hiddenopenAccountDiv").hide();
			$("#updateMerchant #hiddenbankCardPhotoDiv").show();
			$("#bankCardPhotoImageDiv").show();
			$("#updateMerchant #bankCardPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=07");
		}
		if(null!=custType && custType=="1"){
			$("#updateMerchant #hiddenbankCardPhotoDiv").hide();
			$("#updateMerchant #hiddenopenAccountDiv").show();
			$("#openAccountImageDiv").show();
			$("#updateMerchant #openAccountImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=03");
			
		}
	}
	
	/* if(merchant.custType == '0'){
		$("#updateMerchant .businessLicenseNumberTr").hide();
		$("#updateMerchant .businessLicensePhotoTr").hide();
	}else{
		$("#updateMerchant .businessLicenseNumberTr").show();
		$("#updateMerchant .businessLicensePhotoTr").show();
	} */
	if(merchant.filingAuditStatus != '99'){
		$("#updateMerchant .isFiling").hide();
	}else{
		$("#updateMerchant .isFiling").show();
	}
 	
	$("#updateMerchant #businessPhotoImageDiv").show();
	$("#updateMerchant #certAttribute1ImageDiv").show();
	$("#updateMerchant #certAttribute2ImageDiv").show(); 
	
	$("#updateMerchant #doorPhotoImageDiv").show(); 
	// $("#updateMerchant #bankCardImage").show();
	// $("#updateMerchant #otherPapersImage").show(); 
  
     
	
    $("#updateMerchant #businessPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=02");
    $("#updateMerchant #doorPhotoImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=08");
	$("#updateMerchant #certAttribute1ImageDiv").attr("src","<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=04&front=0");
	$("#updateMerchant #certAttribute2ImageDiv").attr("src",//
			"<%=request.getContextPath() + TinyMerchantPath.BASE 
			+ TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=04&front=1");
	<%-- $("#updateMerchant #bankCardImage").attr("src",//
			"<%=request.getContextPath() + TinyMerchantPath.BASE 
			+ TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=05"); --%>
	<%-- $("#updateMerchant #otherPapersImage").attr("src",//
			"<%=request.getContextPath() + TinyMerchantPath.BASE 
			+ TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId=" + custId + "&certifyType=06"); --%>
			$("#updateMerchant #agentName").val(merchant.agentName);
			
			//加载行目数据
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETCATEGORYTYPEBYID %>',
				data:
				{
					"categoryId" 	: merchant.categoryType
				},
				success:function(data){
					if(data.result=="SUCCESS"){
						var categoryList = data.categoryList;
						for ( var category in categoryList) {
							$("#categoryId").append(
									"<option value='"+ categoryList[category].categoryId +"'>"
											+ categoryList[category].categoryName + "</option>");
						 }
						$("#updateMerchant #categoryTypeId").val(data.categoryTypeId);
						$("#updateMerchant #categoryId").val(merchant.categoryType);
					}
				}
			});
			
			$.ajax({//加载门头照
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETMERCHANTIMG %>',
				data:
				{
					"custId" 	: merchant.custId
				},
				success:function(data){
					if(data.result=="SUCCESS"){
						$("#doorPhotoTr").html('');
				         var doorPhotos=data.doorPhoto;
			   	        	for (var v = 0; v < doorPhotos.length; v++) {
			   	        	 $("#sample-table-2 #iDCardPhotoTr").after('<tr  id="doorPhotoTr"><td class="td-left">门头照：</td><td class="td-right" colspan="3">'+
				    		    	 	'<a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal" >'+
				    		    	 	'<label id="doorPhotoDiv'+v+'"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">'+
				    		    	 	'<img  id="doorPhotoImageDiv'+v+'" onclick="bigImg(this)"  style="width:100%;height:100%;"  src="<%=request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE %>?custId='+custId+'&certifyType=08&front='+v+'"/></label></a>'+
				    		    	 	'<div class="updateImageDiv" style="float:left; margin-top:75 " >'+
				    		    	 	'<input type="hidden" id="doorPhotoImageVa'+v+'"/> '+
				    		    	 	'<input type="file" name="doorPhoto'+v+'"  id="doorPhoto'+v+'"  onchange="showDoorPhotoImage(this)" />'+
				    		    	 	'<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>'+
				    		    	 	'</div>'+
				    		    	 	'</td>'+
				    		    	 	'</tr>'
				    		    	 	
				    		    	 	
				    		     );
							}
					}
				}
			});
			// 加载省份地区
			
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TinyMerchantPath.BASE+ TinyMerchantPath.GETAREAINFOBYID %>',
				data:
				{
					"province" 	: merchant.province,
					"city" 		: merchant.city,
					"country" 	: merchant.country
				},
				success:function(data){
					if(data.result=="SUCCESS"){
						var provinceList = data.provinceList;
						for ( var province in provinceList) {
							$("#province").append(
									"<option value='"+ provinceList[province].provinceId +"'>"
											+ provinceList[province].provinceName + "</option>");
						}
						
						var cityList = data.cityList;
						for ( var city in cityList) {
							$("#city").append(
									"<option value='"+ cityList[city].cityId +"'>"
											+ cityList[city].cityName + "</option>");
						}
						
						var areaList = data.areaList;
						for ( var area in areaList) {
							$("#country").append(
									"<option value='"+ areaList[area].areaId +"'>"
											+ areaList[area].areaName + "</option>");
						}
						
						$("#updateMerchant #province").val(merchant.province);
						$("#updateMerchant #city").val(merchant.city);
						$("#updateMerchant #country").val(merchant.country);
					}
				}
			})
}

/** 导出微商户列表 */
$(".exportBut").click(function(){
	var merchantCode = $('.search-table #merchantCode').val().trim();
	var startCreateTime = $('.search-table #startCreateTime').val().trim();
	var endCreateTime = $('.search-table #endCreateTime').val().trim();
	var custName = $('.search-table #custName').val().trim();
	var email = $('.search-table #email').val().trim();	
	var src ="<%= request.getContextPath() + TinyMerchantPath.BASE + TinyMerchantPath.EXPORTMERCHANTINFO%>?merchantCode=" +
		merchantCode + "&startCreateTime=" + startCreateTime + "&endCreateTime=" + endCreateTime
		+ "&custName=" + custName + "&email=" + email;
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

function showOpenAccountImage(obj){  
	 var divObj = document.getElementById("openAccount0Div");  
	 var imageObj = document.getElementById("openAccountImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}
function showDoorPhotoImage(obj){ 
	 var  door_id=obj.id.substring(9);
	 var divObj = document.getElementById("doorPhotoDiv"+door_id);  
	 var imageObj = document.getElementById("doorPhotoImageDiv"+door_id); 
	 return previewImage(divObj,imageObj,obj);  
}
function showbankCardPhotoImage(obj){  
	 var divObj = document.getElementById("bankCardPhotoDiv");  
	 var imageObj = document.getElementById("bankCardPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}

function shownetWorkPhotoImage(obj){  
	 var divObj = document.getElementById("netWorkPhotoDiv");  
	 var imageObj = document.getElementById("netWorkPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}


/** 银行卡扫描件 */
/* function showBankCardImage(obj){  
	 var divObj = document.getElementById("bankCardDiv");  
	 var imageObj = document.getElementById("bankCardImage");  
	 return previewImage(divObj,imageObj,obj);
} */
/** 其他扫描件 */
/* function showOtherPapersImage(obj){  
	 var divObj = document.getElementById("otherPapersDiv");  
	 var imageObj = document.getElementById("otherPapersImage");  
	 return previewImage(divObj,imageObj,obj);
} */

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