<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller.WechatAudingPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAudingPath"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
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
<title>代付充值审核列表</title>
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
						
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.AUDITRECHARGEPAYMENT %>"/>' method="post">
							
							<table class="search-table">
								<tr>
									<td class="td-left" >凭证号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="id" id="id"  value="${queryBean.id }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >代付账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="recAccountNo" id="recAccountNo"  value="${queryBean.recAccountNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									</tr>
									<tr>
									<%-- <td class="td-left">一级审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="fristAuditTime"   id="fristAuditTime" readonly="readonly" value="${queryBean.fristAuditTime}"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="fristEndAuditTime"   id="fristEndAuditTime" readonly="readonly" value="${queryBean.fristEndAuditTime}"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td> --%>
								    
								    
								    <td class="td-left">审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="secondAuditTime"   id="secondAuditTime" readonly="readonly" value="${queryBean.secondAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="secondEndAuditTime"   id="secondEndAuditTime" readonly="readonly" value="${queryBean.secondEndAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
								    
								    <td class="td-left">提交审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								   </td> 
								</tr>
								
									
									
							
								
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									
									<td class="td-left">审核状态：</td>
									<td class="td-right">								   
										 <select name="auditStatus" id="auditStatus" >
										   <option  value="" > 请选择 </option>
										  <option  value="01" >待审核 </option>
										  <!-- <option  value="03">一级审核通过 </option>
										  <option  value="02">一级审核不通过 </option> -->
										  <option  value="04">审核不通过</option>
										  <option  value="05">审核通过</option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
								
						
								</tr>
								<%-- <tr>
								  <td class="td-left">提交审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								   </td>
								
								
								
								</tr> --%>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.AUDITRECHARGEPAYMENT %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代付充值审核列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="8%">凭证号</th>
										<th width="8%">商户号</th>
										<th width="11%">商户名称</th>
										<th width="8%">充值金额</th>
										<th width="8%">支付渠道手续费 </th>
										<th width="8%">收取商户手续费 </th>
										<th width="12%">提交审核时间</th>
									    <th width="12%">审核状态</th>
									    <th width="12%">充值状态</th>
									    <!--  <th width="12%">一级审核时间</th> -->
									    <th width="12%">审核时间</th>
										<th width="15%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${payCreditList}" var="payment" varStatus="i">
										<tr class="payment" id="payment">
											
											<td>${payment.id }</td>
											<td>
											${payment.recMerchantNo}
											<%-- <c:if test="${payment.recAccountNo != null}">
												${payment.recAccountNo }
											</c:if>
											<c:if test="${payment.recAccountNo == null}">
												${payment.email }
											</c:if> --%>
											</td>
											<td>${payment.custName}</td>
											<td>${payment.rechargeAmt}</td>
											<td>${payment.channelRechargeFeeamt}</td>
											<td>${payment.rechargeFeeamt}</td>
                                            <td>${payment.createTime}</td>

                                              <td>
												<c:if test="${payment.auditStatus =='01'}">
													待审核
												</c:if>
												<c:if test="${payment.auditStatus =='02'}">
													<!-- 一级审核不通过 -->
													审核不通过 
												</c:if>
												<c:if test="${payment.auditStatus =='03'}">
													<!-- 一级审核通过 -->
													审核通过 
												</c:if>	  
									  			<c:if test="${payment.auditStatus =='04'}">
													<!-- 二级审核不通过	 -->
													审核不通过 
												</c:if>			
									            <c:if test="${payment.auditStatus =='05'}">
													审核通过 	
												</c:if>
												
										 	</td>
										 	 <td>
												<c:if test="${payment.payStatus =='99'}">
													未打款
												</c:if>
												<c:if test="${payment.payStatus =='00'}">
													充值成功
												</c:if>
												<c:if test="${payment.payStatus =='90'}">
													已撤销
												</c:if>	  
									  			<c:if test="${payment.payStatus =='80'}">
													充值失败
												</c:if>			
									            <c:if test="${payment.payStatus =='60'}">
													交易异常	
												</c:if>
												<c:if test="${payment.payStatus =='91'}">
													撤销异常
												</c:if>
												<c:if test="${payment.payStatus =='92'}">
													撤销失败	
												</c:if>
												<c:if test="${payment.payStatus == null}">
													待充值	
												</c:if>
												
										 	</td>
										 	 <td>${payment.secondAuditTime}</td>
										 	<%-- <td>${payment.fristAuditTime }</td> --%>
										 	<%-- <c:if test="${payment.fristAuditTime==payment.secondAuditTime }">
										 	  <td>${payment.secondAuditTime}</td>
										 	
										 	</c:if>
										 	
										 	<c:if test="${payment.fristAuditTime != payment.secondAuditTime }">
										 	  <td>${payment.secondAuditTime}</td>
										 	
										 	</c:if> --%>
										 	
											<td>
										 	
                                               <input type="hidden" name="bank_Id" id="bank_Id" value="${payment.id}">
                                                <input type="hidden" name="payment_auditStatus" id="payment_auditStatus" value="${payment.auditStatus}">
                                               <c:choose>
                                               <c:when test="${payment.auditStatus =='01'}">
                                                	
                                                <!-- <button type="button" onclick="AuditRechargePayment(this,'Clste','edit')"   data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >清结算审核</button>
												<button type="button" onclick="AuditRechargePayment(this,'Finance','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" disabled="disabled">财务审核</button>
                                                --><button type="button" onclick="AuditRechargePayment(this,'Finance','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >充值审核</button>
                                                	
                                               </c:when>
                                                <c:when test="${payment.auditStatus =='03'}">
                                                <!-- <button type="button" onclick="AuditRechargePayment(this,'Clste','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" disabled="disabled">清结算审核</button>
													<button type="button" onclick="AuditRechargePayment(this,'Finance','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"  >财务审核</button>
                                                --><button type="button" onclick="AuditRechargePayment(this,'Finance','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >充值审核</button>
                                                
                                                </c:when>
                                                
                                                <c:when test="${payment.payStatus =='00'}">
                                                	<!-- <button type="button" onclick="verificationPayment(this)" data-toggle='modal' data-target="#verificationModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >撤销</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                                	 --><button type="button" onclick="AuditRechargePayment(this,'pr', 'preview')" data-toggle='modal' data-target="#updateMerchantModel_2" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchantModel" >详情</button> 
                                                </c:when>
                                                 <c:when test="${payment.payStatus =='60'}">
                                                	<!-- <button type="button" onclick="verificationPayment(this)" data-toggle='modal' data-target="#verificationModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >撤销</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                                	 --><button type="button" onclick="AuditRechargePayment(this,'pr', 'preview')" data-toggle='modal' data-target="#updateMerchantModel_2" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchantModel" >详情</button> 
                                                </c:when>
                                                <%-- <c:when test="${payment.payStatus =='90'}">
                                                	<button type="button" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchantModel" >已撤销</button>
                                                </c:when>
                                                <c:when test="${payment.payStatus =='92'}">
                                                	<button type="button" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchantModel" >撤销失败</button>
                                                </c:when>   --%>
                                               </c:choose>
                                            	
                                               <c:if test="${payment.auditStatus =='02' || payment.auditStatus =='04'}">
	                                               <button type="button" onclick="AuditRechargePayment(this,'pr', 'preview')" data-toggle='modal' data-target="#updateMerchantModel_2" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchantModel" >详情</button> 
	                                              <!-- <button type="button" onclick="AuditRechargePayment(this,'his','history')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
                                               --></c:if>
                                              
											</td>

										</tr>
									</c:forEach>
									<c:if test="${empty payCreditList}">
										<tr>
											<td colspan="11" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty payCreditList}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->

				<div class="modal fade" style="z-index: 1040;" id="updateMerchantModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 60%; z-index: 90;">
						<div class="modal-content" style="width: 1000px;" id="merchantDiv">
							<div class="modal-header" style="background-color: 0099CC">
								<input type="hidden" id="audit_flag" name="audit_flag" value="" />
								
								<button type="button" class="close" data-dismiss="modal"
									id="updateMerchantClose" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">代付充值详情</h4>
							</div>
							<div class="modal-body">
								<table id="sample-table-2" class="list-table">
									<tr>
										<td colspan="4" class="headlerPreview"></td>
									</tr>
									<tr>
										<td class="td-left" width="18%">商户名称：</td>
										<td class="td-right" width="32%"><input type="text"
											id="custName_audit" readonly="readonly" name="custName_audit"
											maxlength="100" placeholder="商户名称" style="width: 90%">

										</td>
										<td class="td-left" width="18%">银行凭证号：</td>
										<td class="td-right" width="32%"><input type="text"
											id="bank_id" name="bank_id" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>
										<td class="td-left">代付账号：</td>
										<td class="td-right"><input type="text" id="paerAcctNo_1"
											name="paerAcctNo_1" placeholder="登录账号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">提交审核时间：</td>
										<td class="td-right"><input type="text" id="sumCount_1"
											name="sumCount_1" placeholder="提交审核时间" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									
									
									

									<tr id="hiddenbusinessPhotoDiv">
										<td class="td-left">转账凭证：</td>
										<td class="td-right" >
											<a data-toggle='modal' class="tooltip-success certAttribute0Click"   data-target="#previewImageModal" >
											<label id="certAttribute0Div"style="float:left;background-color:rgb(222, 222, 222); width:280px;height:200px; margin: 10 10 10 10">  
											        <img  id="certAttribute0ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
											</label>
											</a>
											
										</td>
										</tr>
										<tr>
										<td id="rechargeAmt1"  class="td-left">充值金额：<br />
										 <td id="rechargeAmt2"  class="td-right"><input type="text" id="rechargeAmt"
											name="rechargeAmt" placeholder="请确认金额数" >
										</td>
										<!-- <td id="td1"  class="td-left">确认金额：<br /><span style="color: #939192; font-size: 18px">（请确认输入金额数与凭证一致）</span></td>
										 -->
										 <td id="td2"  class="td-right"><input type="text" id="sumMoney"
											name="sumMoney" placeholder="请确认金额数" >
										
									
										</td>

									  </tr>
                                       <tr>
                                       <td id="td3" class="td-left" >确认金额:</td>
									   <td id="td4" class="td-right" ><input type="text" id="auditAmt" name="auditAmt" placeholder="确认金额"  style="width: 90%"></td>
                                      
										 
									   <!--  <td id='td5' class="td-right">一级审核时间
									    <input type="text" id="fristAuditTime" name="fristAuditTime" placeholder="一级审核时间" readonly="readonly">
										</td>  -->
										

									  </tr>



								</table>
								

								

							</div>
							<!-- <div id="hiddenCheck" class="modal-footer">
								<button type="button"
									class="btn btn-primary firstAuditNotPassBtn"
									data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
								<button type="button" class="btn btn-primary firstAuditPassBtn">审核通过</button>
							</div> -->
							<div id="hiddenCheck_2" class="modal-footer">
								<button type="button" class="btn btn-primary secondAuditPassBtn"
									data-toggle='modal'  data-target="#secondAuditMessageModel">确认打款并审核通过</button>
								<button type="button"
									class="btn btn-primary firstAuditNotPassBtn"
									data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
							</div>
						</div>
						

					</div>

				</div>


<div class="modal fade" style="z-index: 1040;" id="updateMerchantModel_2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 60%; z-index: 90;">
						<div class="modal-content" style="width: 1000px;" id="merchantDiv">
							<div class="modal-header" style="background-color: 0099CC">
								<input type="hidden" id="audit_flag" name="audit_flag" value="" />
								
								<button type="button" class="close" data-dismiss="modal"
									id="updateMerchantClose" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabe2">代付充值详情</h4>
							</div>
							<div class="modal-body">
								<table id="sample-table-2" class="list-table">
									<tr>
										<td colspan="4" class="headlerPreview"></td>
									</tr>
									<tr>
										<td class="td-left" width="18%">商户名称：</td>
										<td class="td-right" width="32%"><input type="text"
											id="custName_2" readonly="readonly" name="custName_2"
											maxlength="100" placeholder="商户名称" style="width: 90%">

										</td>
										<td class="td-left" width="18%">银行凭证号：</td>
										<td class="td-right" width="32%"><input type="text"
											id="bank_2" name="bank_2" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>
										<td class="td-left">代付账号：</td>
										<td class="td-right"><input type="text" id="paerAcctNo_2"
											name="paerAcctNo_2" placeholder="代付账号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">提交审核时间：</td>
										<td class="td-right"><input type="text" id="sumCount_2"
											name="sumCount_2" placeholder="提交审核时间" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									<!-- <tr>
									<td class="td-left">一级审核时间：</td>
									<td class="td-right"><input type="text" id="fristAuditTime_2"
											name="fristAuditTime_2" placeholder="一级审核时间" readonly="readonly"
											style="width: 90%"></td>
									<td id='td8' class="td-left">二级审核时间：</td>
									<td id='td9' class="td-right"><input type="text" id="secondAuditTime_2"
											name="secondAuditTime_2" placeholder="二级审核时间" readonly="readonly"
											style="width: 90%"></td>
									
						
									</tr> -->
									<tr id='hiddenAmt'>
                                          <td class="td-left" >打款金额：</td>
                                          <td class="td-right" >
                                          <input type="text" id="recharge_Amt" readonly="readonly" name="recharge_Amt"  placeholder="打款金额" style="width: 90%">
                                          
                                          <td id='td8' class="td-left">审核时间：</td>
										  <td id='td9' class="td-right"><input type="text" id="secondAuditTime_2"
											name="secondAuditTime_2" placeholder="审核时间" readonly="readonly"
											style="width: 90%"></td>

									  </td>
									</tr>

									

									<tr id="hiddenbusinessPhotoDiv_2">
										<td class="td-left">转账凭证：</td>
										<td class="td-right" >
											<a data-toggle='modal' class="tooltip-success certAttribute0Click"   data-target="#previewImageModal" >
											<label id="certAttribute0Div"style="float:left;background-color:rgb(222, 222, 222); width:280px;height:200px; margin: 10 10 10 10">  
											        <img  id="certAttribute0ImageDiv_2" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
											</label>
											</a>
											
										</td>
										</tr>
										

								</table>
								

								

							</div>
							
							
						</div>
						

					</div>

				</div>



				<div class="modal fade" style="z-index: 1043;" id="firstAuditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 30%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">审核不通过</h4>
							</div>
							<div class="modal-body">

								<table id="selectCheckbox">

									<label><input name="test" type="checkbox"
										value="银行凭证正面照片不清晰" />&nbsp;银行凭证正面照片不清晰</label>
									</br>
									<label><input name="test" type="checkbox"
										value="账户还未收到此款项" />&nbsp;账户还未收到此款项</label>
									</br>
									
									</br> 其他原因
									<tr>
										<td><textarea rows="5" cols="40" id="auditMessage"></textarea>
										</td>
									</tr>


								</table>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn"
									onclick="firstAuditNotPassBtn();">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
				<!-- /.modal -->   
	
	
				<!-- 财务打款静态框 -->
				<div class="modal fade" style="z-index: 1043;" id="secondAuditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">确认打款</h4>
							</div>
							

						<div id="u1577" class="text" style="visibility: visible;margin: 50px 0 0 100px;">
                                <p style="font-size:16px;">
                                <span style="font-family:'黑体 Bold', '黑体';font-weight:700;">凭证号：<span id='payment_No'></span></span></p>
                                <p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;"><br></span></p>
                                <p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;">代付账户：<span id='payment_Account'></span></span></p>
                                <p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;"><br></span></p>
                                <p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;">商户名称：<span id='payment_Name'></span></span></p><p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;"><br></span></p>
                                <p style="font-size:16px;"><span style="font-family:'黑体 Bold', '黑体';font-weight:700;">打款金额：<span id='payment_Amt'></span></span></p><p style="font-size:13px;"><span style="font-family:'Arial Normal', 'Arial';font-weight:400;"><br></span></p><p style="font-size:16px;"><span style="font-family:'Arial Negreta', 'Arial';font-weight:700;color:#0033FF;"><br></span><span style="font-family:'Arial Negreta', 'Arial';font-weight:700;"></span></p>
                                <p style="font-size:16px;"><span style="font-family:'Arial Negreta', 'Arial';font-weight:700;"><br></span></p>
                          </div>


							 
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn"
									onclick="secoundAuditPassBtn();">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
				<!-- /.modal -->   
	
				<div class="modal fade" style="z-index: 1043;" id="verificationModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">核销</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" value="" id="toPay_Type"/>
								<div id="selectCheckbox">
									确认核销<span id='toPayBatNo'></span> 批次号 ？
								</div>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn"
									onclick="verificationToPayBtn();">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
	
	
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
	
	<div class="list-table-header">代付充值审核记录</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">凭证号</th>
		<th width="10%">代付账号</th>
		<th width="10%">商户名称</th>
		<!-- <th width="12%">一级审核时间</th> -->
		<th width="12%">审核时间</th>
		<th width="12%">原因</th>
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
function verificationToPayBtn(){
	 var id=$('#verificationModel #toPayBatNo').text();
	 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.PAYMENTRECHARGEREVOKE %>',
			data:
			{
					"id" 	: id
					
			},
			success:function(data){
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess(data.message,function(){
							$("#verificationModel").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("撤销失败！"+data.message);
					}
				}
			}) ;
        }

function verificationPayment(obj){
	var id=$(obj).parent().find('#bank_Id').val();
	$("#toPayBatNo").text(id);
	
}


function AuditRechargePayment(obj,audit_type,type) {
   var id=$(obj).parent().find('#bank_Id').val();
   var auditStatus=$(obj).parent().find('#payment_auditStatus').val();
     
	$.post(window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.SELRECHARGEINFO%>',{
 			"id":id
 			
 	},function(data){
 		if(data.result=='SUCCESS'){
				$("#updateMerchantModel #custName_audit").val(data.Info.custName);
				$("#updateMerchantModel #bank_id").val(data.Info.id);
				$("#updateMerchantModel #paerAcctNo_1").val(data.Info.email);
				$("#updateMerchantModel #sumCount_1").val(data.Info.createTime);
				$("#updateMerchantModel #audit_flag").val(audit_type);
				$("#updateMerchantModel #rechargeAmt").val(data.Info.rechargeAmt);
				$("#updateMerchantModel #sumMoney").val(data.Info.rechargeAmt);
				var custId = data.Info.id;
				
				$("#updateMerchantModel #certAttribute0ImageDiv").show();
				$("#updateMerchantModel #certAttribute0ImageDiv").attr("src","<%=request.getContextPath()+PaymentManagerPath.BASE+ PaymentManagerPath.GETPICTURE %>?custId="+custId+"&certifyType=99");
				
				if(type =='edit'){
					if(audit_type=='Clste'){
						$("#updateMerchantModel #hiddenCheck").attr("style","");
	 					$("#updateMerchantModel #hiddenCheck_2").attr("style","display:none");
	 				    $("#updateMerchantModel #td1").attr("style","");
	 					$("#updateMerchantModel #td2").attr("style",""); 
	 					$("#updateMerchantModel #td3").attr("style","display:none");
	 					$("#updateMerchantModel #td4").attr("style","display:none"); 
	 					$("#updateMerchantModel #td5").attr("style","display:none");
	 					$("#updateMerchantModel #td5").attr("style","display:none");
					}else if(audit_type=='Finance'){
						$("#updateMerchantModel #td1").attr("style","display:none");
		 				$("#updateMerchantModel #td2").attr("style","display:none"); 
						$("#updateMerchantModel #td3").attr("style","");
	 					$("#updateMerchantModel #td4").attr("style",""); 
	 					$("#updateMerchantModel #td5").attr("style","");
	 					$("#updateMerchantModel #td5").attr("style","");
	 					/* $("#updateMerchantModel #fristAuditTime").val(data.Info.fristAuditTime); */
	 					$("#updateMerchantModel #auditAmt").val(data.Info.rechargeAmt);
						$("#updateMerchantModel #hiddenCheck").attr("style","display:none");
	 					$("#updateMerchantModel #hiddenCheck_2").attr("style","display:block");
						
					
					}
				}else if(type=='preview'){
					if(auditStatus=='02'){
						$("#updateMerchantModel_2 #hiddenAmt").attr("style","display:none");
					}else{
						
						$("#updateMerchantModel_2 #hiddenAmt").attr("style","");
						$("#updateMerchantModel_2 #recharge_Amt").val(data.Info.auditAmt);
					}
					$("#updateMerchantModel_2 #custName_2").val(data.Info.custName);
					$("#updateMerchantModel_2 #bank_2").val(data.Info.id);
					$("#updateMerchantModel_2 #paerAcctNo_2").val(data.Info.email);
					$("#updateMerchantModel_2 #sumCount_2").val(data.Info.createTime);
					var  fristAuditTime=data.Info.fristAuditTime;
					var  secondAuditTime=data.Info.secondAuditTime;
					if(fristAuditTime==secondAuditTime){
						$("#updateMerchantModel_2 #fristAuditTime_2").val(data.Info.fristAuditTime);
						$("#updateMerchantModel_2 #td8").attr("style","display:none");
		 				$("#updateMerchantModel_2 #td9").attr("style","display:none");
					}else{
						$("#updateMerchantModel_2 #td8").attr("style","");
		 				$("#updateMerchantModel_2 #td9").attr("style","");
						$("#updateMerchantModel_2 #secondAuditTime_2").val(data.Info.secondAuditTime);
						var custId = data.Info.id;
					}
					
					
					$("#updateMerchantModel_2 #certAttribute0ImageDiv_2").show();
					$("#updateMerchantModel_2 #certAttribute0ImageDiv_2").attr("src","<%=request.getContextPath()+PaymentManagerPath.BASE+ PaymentManagerPath.GETPICTURE %>?custId="+custId+"&certifyType=99");
					
					
 					 
				}else{
					var id=data.Info.id;
					 $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.GETRECHRAGEHISTORY %>', {
					  
				 		"id" 	: id
				 		
				 		},function(data){
				 			var json = eval('(' + data + ')'); 
				 			var columns=$("#sample-table-5 #getHistory");
				 			$(columns).html('');
				 			var infos='';
				 			if(json.result=="FAILE"){
			  	 				infos="<tr><td colspan='9' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>";
			  	 			 }
			  	 		      var  checkHistorys=json.checkHistory;
			  	 				$('#showHistory').on('show.bs.modal', function () {
			  	 				 });
			  	 				  for (var i = 0; i < checkHistorys.length; i++) {
			  	 					infos+="<tr><td>"+checkHistorys[i].creditId+"</td><td>"+checkHistorys[i].email+"</td><td>"+checkHistorys[i].custName+"</td></td><td>"+/* checkHistorys[i].fristAuditTime+ */"</td><td>"+checkHistorys[i].secondAuditTime+"</td><td>"+checkHistorys[i].memo+"</td></tr>";
								}  
			  	 			 
				 		 $(columns).append(infos);
				 		});
				
		           }
				
				 
 		}else{
			$.gyzbadmin.alertFailure("数据查询失败！"+data.message);
		}
 	},'json'); 
	    
}	

	
/** 清空按钮 **/
$(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
});

</script>
<script type="text/javascript">
 $(function(){
	 
	 var topayType = $("#toPayType_3").val();
	 var type_2 = $("#type_2").val();
		
	$("#toPayType option[value='"+topayType+"']").removeAttr("selected");//根据值去除选中状态  
	$("#toPayType option[value='"+topayType+"']").attr("selected","selected");
	
	$("#type option[value='"+type_2+"']").removeAttr("selected");//根据值去除选中状态  
	$("#type option[value='"+type_2+"']").attr("selected","selected");
	 
	 
	/** 缓存 **/
	var custIds = ${payCreditList};
	var custId = $("tr.payment");
	$.each(custIds,function(i,value){
		$.data(custId[i],"payment",value);
	});
	
	 function isPercent(c){
		 var r= /^[1-9]?[0-9]*\.[0-9]+[%]$/;
		 return r.test(c);
	}
	 /** 审核通过 */
	$(".firstAuditPassBtn").click(function(){
		 var id=$('#updateMerchantModel #bank_id').val();
		 var sumMoney=$('#updateMerchantModel #sumMoney').val();
		 if(null==sumMoney || ''==sumMoney){
			 alert("请填写充值金额");
		 }else if(!isTradeAmt(sumMoney)){
			 alert("输入金额不合法 只能输入数字!");
		 }else{
			 $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITRECHARGEPASS %>',
					data:
					{
						"id" 	     :id,
						"sumMoney"   :sumMoney
					},
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
		 
		      }
	      });
	 
	 $(".secondAuditPassBtn").click(function(){
		var payment_Name =$("#updateMerchantModel #custName_audit").val();
		var payment_No =$("#updateMerchantModel #bank_id").val();
		var payment_Account =$("#updateMerchantModel #paerAcctNo_1").val();
		var payment_Amt= $("#updateMerchantModel #auditAmt").val();
		  $("#payment_Name").text(payment_Name);
		  $("#payment_No").text(payment_No);
		  $("#payment_Account").text(payment_Account);
		  $("#payment_Amt").text(payment_Amt);
	     });
       });
 
 
 function bigImg(obj){
	    $('#showImageDiv #showImage').attr("src",obj.src);
	};
	
/*审核不通过  */
	
function firstAuditNotPassBtn(){
	 var id=$('#updateMerchantModel #bank_id').val();
	 var email=$('#updateMerchantModel #paerAcctNo_1').val();
	 var custName=$('#updateMerchantModel #custName_audit').val();
	 var reason=$("#auditMessage").val();
	 var auditFlag=$('#updateMerchantModel #audit_flag').val();
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
	  
	    var messages=null;
		   if(null!=chk_value && chk_value.length>=1 && ""!=chk_value){
			   for (var j = 0; j < chk_value.length; j++) {
				 messages = chk_value[j];
			   }
			   if(chk_value.length>=2){
				   messages=chk_value.join();
			    }
			   if(null!=reason && 'null'!=reason && ""!=reason){
				   messages=chk_value.join()+","+reason;
			   }
		   }else{
			   messages=reason;
		   }
		   
	   $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITRECHARGENOTPASS %>',
				data:
				{
					"id" 	:id,
					"message"	:messages,
					"email"     :email,
					"custName"  :custName,
					"auditFlag" :auditFlag
					
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
	
function forCloseDiv(){
	 $.unblockUI();
} 
function isTradeAmt(money){
	var regx =/^\d+(\.\d{1,3})?$/;
	return regx.test(money);
}

function secoundAuditPassBtn(){
	 var id=$('#updateMerchantModel #bank_id').val();
	 var auditAmt=$("#updateMerchantModel #auditAmt").val();
	 var accountNo =$("#updateMerchantModel #paerAcctNo_1").val();
		  $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.PAYMENTRECHARGEAUDITPASS %>',
				data:
				{
						"id" 	: id,
						"auditAmt":auditAmt,
						"recAccountNo":accountNo
				},
				success:function(data){
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess(data.message,function(){
								$("#secondAuditMessageModel").modal("hide");
								$("#updateMerchantModel").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							$.gyzbadmin.alertFailure("代付失败！"+data.message);
						}
					}
				}) ;
	         
           }

	
</script>
</html>