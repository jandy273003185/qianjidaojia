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
<title>代付审核列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.AUDITPAYMENT %>"/>' method="post">
							<input type="hidden" name="count" id="count" value="">
							<input type="hidden" name="toPayType_3" id="toPayType_3" value="${queryBean.toPayType }">
							<input type="hidden" name="type_2" id="type_2" value="${queryBean.type }">
							<input type="hidden" name="trade_Status" id="trade_Status" value="${queryBean.tradeStatus }">
							<table class="search-table">
								<tr>
									<td class="td-left" >交易号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="batNo" id="batNo"  value="${queryBean.batNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >代付账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="paerAcctNo" id="paerAcctNo"  value="${queryBean.paerAcctNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">提交审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
								</tr>
								
								<tr>
									<!-- <td class="td-left">代付类型：</td>
									<td class="td-right">								   
										 <select name="toPayType" id="toPayType" >
										  <option  value="" > 请选择 </option>
										  <option  value="00">银行卡 </option>
										  <option  value="01">余额 </option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td> -->
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">代付方式：</td>
									<td class="td-right">								   
										 <select name="type" id="type" >
										  <option  value="" > 请选择 </option>
										  <option  value="0">批量 </option>
										  <option  value="1">单笔 </option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									
									<td class="td-left">一级审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="startfristAuditTime"   id="startfristAuditTime" readonly="readonly" value="${queryBean.startfristAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endfristAuditTime"   id="endfristAuditTime" readonly="readonly" value="${queryBean.endfristAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
								
								</tr>
								
								<tr>
									<td class="td-left">审核状态：</td>
									<td class="td-right" colspan="3">								   
										 <select name="tradeStatus" id="tradeStatus" >
										  <option  value="" > 请选择 </option>
										  <option  value="04">待审核 </option>
										  <option  value="06">一级审核通过 </option>
										  <option  value="08">二级审核通过</option>
										  <option  value="07">已撤销</option>
										  <option  value="60">银行打款成功</option>
										  
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									
									<td class="td-left">二级审核时间：</td>
									 <td class="td-right">
									 <input type="text" name="startsecondAuditTime"   id="startsecondAuditTime" readonly="readonly" value="${queryBean.startsecondAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endsecondAuditTime"   id="endsecondAuditTime" readonly="readonly" value="${queryBean.endsecondAuditTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.AUDITPAYMENT %>">
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
						
						<div class="list-table-header">代付审核列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="7%">交易号</th>
										<th width="6%">代付账号</th>
										<th width="7%">商户名称</th>
										<!-- <th width="6%">代付类型</th> -->
										<th width="5%">代付方式</th>
										<th width="5%">代付笔数</th>
										<th width="7%">代付金额</th>
										<th width="5%">手续费</th>
										<th width="9%">提交审核时间</th>
										<th width="9%">一级审核时间</th>
										<th width="9%">二级审核时间</th>
									    <th width="10%">审核状态</th>
										<th width="14%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${audingList}" var="payment" varStatus="i">
										<tr class="payment" id="payment">
											
											<td>${payment.batNo }</td>
											<td>${payment.paerAcctNo }</td>
											<td>${payment.custName}</td>
											<%-- <td>
	                                            <c:choose>
													<c:when test="${payment.toPayType =='00'}">  									  
							                                         	  银行卡       								  
							              			</c:when>
													<c:when test="${payment.toPayType =='01'}">  									  
							                                        	余额              								  
							             			</c:when>
												</c:choose>
											</td> --%>
											<td>
		                                         <c:choose>
										              <c:when test="${payment.type =='0'}">  									  
										                                           批量          								  
										              </c:when>
									                 <c:when test="${payment.type =='1'}">  									  
										                                          单笔              								  
										              </c:when>
										         </c:choose>
								          	</td>
											<td>${payment.sumCount }</td>
											<td>${payment.sumAmt }</td>
											<td>${payment.serviceFee }</td>
											<td><fmt:formatDate value="${payment.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td>${payment.fristAuditTime }</td>
											<td>${payment.secondAuditTime }</td>
                                           <td>
												<c:if test="${payment.tradeStatus =='02'}">
													已通过	
												</c:if>
												<c:if test="${payment.tradeStatus =='06'}">
													一级审核通过	
												</c:if>
												<c:if test="${payment.tradeStatus =='08'}">
													二级审核通过	
												</c:if>
												<c:if test="${payment.tradeStatus =='04'}">
													<font color="green">待审核</font>	
												</c:if>	  
									  			<c:if test="${payment.tradeStatus =='03'}">
													<font color="red">审核不通过 </font>		
												</c:if>			
									            <c:if test="${payment.tradeStatus =='92'}">
													<font color="red">审核失败 </font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='93'}">
													<font color="red">审核失败</font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='80'}">
													<font color="red">核销失败 </font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='60'}">
													<font color="red">银行打款成功 </font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='90'}">
													<font color="red">提交银行付款失败 </font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='99'}">
													<font color="red">银行打款失败 </font>		
												</c:if>
										 	<td>	
												
										 	    <input type="hidden" name="batNo" id="batNo" value="${payment.batNo}">
										 	    <input type="hidden" name="type_" id="type_" value="${payment.type}">
                                            	<input type="hidden" name="tradeStatus" id="tradeStatus" value="${payment.tradeStatus}">
                                            	<input type="hidden" name="toPayType" id="toPayType" value="${payment.toPayType}">
                                            	<input type="hidden" name="paerAcctNo_" id="paerAcctNo_" value="${payment.paerAcctNo }">
                                            	
                                            	<c:if test="${payment.tradeStatus =='04'}">
	                                            	 <c:if test="${payment.type =='0'}">
		                                            	 <button type="button" onclick="undoTopayinfo(this)" data-toggle='modal' data-target="#undoModel"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
		                                                 <button type="button" onclick="batchAuditPayment(this,'edit','Clste')"   class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">清结算审核</button>
														 <button type="button" onclick="batchAuditPayment(this,'edit','Finance')"   class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" disabled="disabled">财务审核</button>
	                                                </c:if>
	                                                <c:if test="${payment.type =='1'}">
														<button type="button" onclick="undoTopayinfo(this)"  data-toggle='modal' data-target="#undoModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
														<button type="button" onclick="singleAuditPayment(this,'single','qjs','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">清结算审核</button>
														<button type="button" onclick="singleAuditPayment(this,'single','cw','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"disabled="disabled">财务审核</button>
													</c:if>
                                                   </c:if> 
                                                
                                               <c:choose>
                                               <c:when test="${payment.tradeStatus =='02'}">
                                              	   <c:if test="${payment.type =='0'}">
						                              <button type="button" onclick="batchAuditPayment(this,'preview','pre')" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >详情</button>
						                           </c:if>
					                               <c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="singleAuditPayment(this,'single','xq','preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">详情</button>
                                                   </c:if>
                                               </c:when>
                                               <c:when test="${payment.tradeStatus =='06'}">
                                                	<c:if test="${payment.type =='0'}">
														<button type="button" onclick="undoTopayinfo(this)" data-toggle='modal' data-target="#undoModel"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
	                                              		<button type="button" onclick="batchAuditPayment(this,'edit','Clste')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" disabled="disabled">清结算审核</button>
														<button type="button" onclick="batchAuditPayment(this,'edit','Finance')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">财务审核</button>
                                                	</c:if>
                                                	<c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="undoTopayinfo(this)"  data-toggle='modal' data-target="#undoModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
                                                		<button type="button" onclick="singleAuditPayment(this,'single','qjs','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" disabled="disabled">清结算审核</button>
														<button type="button" onclick="singleAuditPayment(this,'single','cw','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >财务审核</button>
                                                	</c:if>
                                               </c:when>
                                                <c:when test="${payment.tradeStatus =='08'}">
                                                	<c:if test="${payment.type =='0'}">
                                                       <button type="button" onclick="batchAuditPayment(this,'preview','pr')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >详情</button>
						                           </c:if>
					                               <c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="singleAuditPayment(this,'single','xq','preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">详情</button>
                                                   </c:if> 
                                                </c:when>
                                                <c:when test="${payment.tradeStatus =='80'||payment.tradeStatus =='60'}">
                                                	<button type="button" onclick="verificationPayment(this)" data-toggle='modal' data-target="#verificationModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >核销</button>
                                                </c:when>
                                                <c:when test="${payment.tradeStatus =='99'}">
                                                	<c:if test="${payment.type =='0'}">
                                                		<button type="button" onclick="undoTopayinfo(this)" data-toggle='modal' data-target="#undoModel"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
	                                                	<button type="button" onclick="batchAuditPayment(this,'preview','pr')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >详情</button> 
                                                	</c:if>
                                                	<c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="undoTopayinfo(this)" data-toggle='modal' data-target="#undoModel"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">撤销</button>
                                                		<button type="button" onclick="singleAuditPayment(this,'single','xq','preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">详情</button>
                                                	</c:if>
                                                </c:when>
                                               </c:choose>
                                            	
                                               <c:if test="${payment.tradeStatus =='03'||payment.tradeStatus =='92'||payment.tradeStatus =='93'}">
	                                                <c:if test="${payment.type =='0'}">
	                                                	<button type="button" onclick="batchAuditPayment(this,'preview','pr')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >详情</button> 
	                                                	<button type="button" onclick="batchAuditPayment(this,'history','his')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
                                                	</c:if>
                                                	<c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="singleAuditPayment(this,'single','xq','preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">详情</button>
	                                                	<button type="button" onclick="batchAuditPayment(this,'history','his')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
                                                	</c:if>
	                                                
                                              </c:if>
                                              
                                              <c:if test="${payment.tradeStatus =='90'}">
	                                                <c:if test="${payment.type =='0'}">
	                                                	<button type="button" onclick="batchAuditPayment(this,'preview','pr')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >详情</button> 
	                                                	<button type="button" onclick="batchAuditPayment(this,'history','his')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
	                                                	<button type="button" onclick="batchAuditPayment(this,'edit','Finance')"  class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">财务审核</button>
                                                	</c:if>
                                                	<c:if test="${payment.type =='1'}">
                                                		<button type="button" onclick="singleAuditPayment(this,'single','xq','preview')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">详情</button>
	                                                	<button type="button" onclick="batchAuditPayment(this,'history','his')" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >查看审核记录</button> 
	                                                	<button type="button" onclick="singleAuditPayment(this,'single','cw','edit')" data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1" >财务审核</button>
                                                	</c:if>
	                                                
                                              </c:if>
											</td>

										</tr>
									</c:forEach>
									<c:if test="${empty audingList}">
										<tr>
											<td colspan="13" align="center">
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

				<div class="modal fade" style="z-index: 1040;"
					id="updateMerchantModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 60%; z-index: 90;">
						<div class="modal-content" style="width: 1000px;" id="merchantDiv">
							<div class="modal-header" style="background-color: 0099CC">
								<input type="hidden" id="authId_update" /> 
								<input type="hidden" id="single_flag" value="" />
								<input type="hidden" id="audit_flag" value="" />
								<input type="hidden" id="audit_flag" value="" />
								<button type="button" class="close" data-dismiss="modal"
									id="updateMerchantClose" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">代付详情</h4>
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
										<td class="td-left" width="18%">代付金额：</td>
										<td class="td-right" width="32%"><input type="text"
											id="sumAmt" name="sumAmt" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>
										<td class="td-left">代付账号：</td>
										<td class="td-right"><input type="text" id="paerAcctNo_1"
											name="paerAcctNo_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付笔数：</td>
										<td class="td-right"><input type="text" id="sumCount_1"
											name="sumCount_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									<tr>
										<td class="td-left">手续费：</td>
										<td class="td-right"><input type="text" id="serviceFee_1"
											name="serviceFee_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付类型：</td>
										<td class="td-right">
											<input type="hidden" id="toPayType_1"
												name="toPayType_1" placeholder="代付类型" readonly="readonly"
												style="width: 90%">
												
												<input type="text" id="toPayType_2"
												name="toPayType_2" placeholder="代付类型" readonly="readonly"
												style="width: 90%">
										</td>


									</tr>
									<tr>


										<td class="td-left">代付方式：</td>
										<td class="td-right"><input type="text" id="pay_1"
											name="pay_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>

										<td class="td-left">实付总额：</td>
										<td class="td-right"><input type="text" id="totalMoney"
											name="totalMoney" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>

										<td class="td-left">交易号：</td>
										<td class="td-right"><input type="text" id="bataNo_1"
											name="bataNo_1" placeholder="交易号" readonly="readonly"
											style="width: 90%"></td>

										<td class="td-left">时间：</td>
										<td class="td-right"><input type="text" id="createTime"
											name="createTime" placeholder="时间" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									
									<tr id="hidden_cw">
										<td class="td-left">代付渠道：</td>
										<td class="td-right">
											<select id="channelType" name="channelType">
												<!-- <option value="CR">华润</option>
												<option value="ECITIC">中信</option> -->
												<option value="Z_BANK">众邦</option>
											</select>
											<select id="channelMerchantid" name="channelMerchantid" style="display:none">
												
											</select> 
										</td>
										
										<td class="td-left"> 联行号：</td>
										<td class="td-right">
											<input type="text" id="lineNumber" name="lineNumber" 
											placeholder="联行号" style="width:90%">
										</td>
										
									</tr>

									<!-- <tr id="hiddenbusinessPhotoDiv">
										<td class="td-left">转账凭证：</td>
										<td class="td-right" >
											<a data-toggle='modal' class="tooltip-success certAttribute0Click"   data-target="#previewImageModal" >
											<label id="certAttribute0Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
											        <img  id="certAttribute0ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
											</label>
											</a>
											
										</td>
										
									</tr> -->


								</table>
								<div id="">
									代付金额为：<span id="toPatSumAmt"></span>(元)&nbsp;&nbsp; 代付笔数：<span
										id="toPayCount"></span>笔&nbsp;&nbsp;手续费：<span id="toPayFee"></span>(元)
									&nbsp;&nbsp;实付总额：<span id='toPaySumMoney'></span>(元)
								</div>

								<table id="sample-table-4" class="list-table">
									<thead>
										<tr>
											<th width="10%">名称</th>
											<th width="10%">银行信息</th>
											<th width="10%">银行卡号</th>
											<th width="12%">代付金额</th>
											<th width="15%">手续费（2元/笔）</th>

										</tr>
									</thead>
									<tbody id="showPaymentList">

									</tbody>

								</table>

							</div>
							<div id="hiddenCheck" class="modal-footer">
								<button type="button"
									class="btn btn-primary firstAuditNotPassBtn"
									data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
								<button type="button" class="btn btn-primary firstAuditPassBtn">审核通过</button>
							</div>
							<div id="hiddenCheck_2" class="modal-footer">
								<button type="button" class="btn btn-primary secondAuditPassBtn"
									data-toggle='modal' onclick="secondAuditPassBtn_()" data-target="#secondAuditMessageModel">确认打款并审核通过</button>
								<button type="button"
									class="btn btn-primary firstAuditNotPassBtn"
									data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
							</div>
						</div>
						<!-- /.modal-content -->

					</div>

				</div>


				<div class="modal fade" style="z-index: 1043;" id="firstAuditMessageModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
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
										value="银行付款凭证号审核不通过" />&nbsp;银行付款凭证号审核不通过</label>
									</br>
									<!-- <label><input name="test" type="checkbox"
										value="银行卡卡号与开户行不匹配" />&nbsp;银行卡卡号与开户行不匹配</label>
									</br>
									<label><input name="test" type="checkbox"
										value="银行账户不存在" />&nbsp;银行账户不存在</label>
									</br> -->
									
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
				<div class="modal fade" style="z-index: 1043;"
					id="secondAuditMessageModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">确认打款并通过审核</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" value="" id="_token"/>
								<div id="selectCheckbox">

									您确认完成代付审核？
								</div>


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
	
				<div class="modal fade" style="z-index: 1043;"
					id="verificationModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">核销</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" value="" id="toPay_Type"/>
								<input type="hidden" value="" id="_token"/>
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
				
				<div class="modal fade" style="z-index: 1043;"
					id="undoModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">撤销</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" value="" id="toPay_Type"/>
								<input type="hidden" value="" id="paerAcctNo_"/>
								<input type="hidden" value="" id="_token"/>
								
								<div id="selectCheckbox">
									确认撤销<span id='toPayBatNo'></span> 批次号 ？
								</div>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn"
									onclick="undoToPayBtn();">确定</button>
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
	
	<div class="list-table-header">代付审核记录</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">交易号</th>
		<th width="10%">商户名称</th>
		<th width="10%">代付账号</th>
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

function secondAuditPassBtn_(){
	
	var channelType=$('#updateMerchantModel #channelType').val();
	var lineNumber=$('#updateMerchantModel #lineNumber').val();
    var flag = true;
	if(channelType==""){
		alert("代付渠道不能为空");
		window.location.reload();
		return false;
		
	}
	
	if(lineNumber==""){
		alert("联行号不能为空");
		window.location.reload();
		return false;
	}
	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.SAVE_TOKEN_TOPAY %>',{
	 }, function(data) {
		var token = data.token ;
		$("#secondAuditMessageModel #_token").val(token);
	}, 'json'
	);
	
	
	$("#toAccountPayMoney").text($("#toPatSumAmt").text());
}


function verificationToPayBtn(){
	var batNo = $("#verificationModel #toPayBatNo").text();
	var toPay_Type = $("#verificationModel #toPay_Type").val();
	var token = $("#verificationModel #_token").val();
	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.VERIFICATIONPASS %>', {
		"batNo" :batNo,
		"toPay_Type":toPay_Type,
		"_TOKEN_ID":token
 		},function(data){
 			var json = eval('(' + data + ')'); 
 			if("SUCCESS"==json.result){
 				window.location.reload();
 			}else{
 				alert(json.message);
 				window.location.reload();
 			}
 			
 	});
}

function undoToPayBtn(){
	var batNo = $("#undoModel #toPayBatNo").text();
	var toPay_Type = $("#undoModel #toPay_Type").val();
	var paerAcctNo_ = $("#undoModel #paerAcctNo_").val();
	var token = $("#undoModel #_token").val();

	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.UNDOTOPAY %>', {
		  
		"batNo" :batNo,
		"type":toPay_Type,
		"paerAcctNo":paerAcctNo_,
		"_TOKEN_ID":token
 		},function(data){
 			var json = eval('(' + data + ')'); 
 			if("SUCCESS"==json.result){
 				window.location.reload();
 			}else{
 				alert(json.message);
 				window.location.reload();
 			}
 			
 	});
}



function verificationPayment(obj){
	
	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.SAVE_TOKEN_TOPAY %>',{
	 }, function(data) {
		var token = data.token ;
		$("#verificationModel #_token").val(token);
	}, 'json'
   );
	
	var batNo=$(obj).parent().find('#batNo').val();
	var type=$(obj).parent().find('#type_').val();
	$("#verificationModel #toPayBatNo").text(batNo);
	$("#verificationModel #toPay_Type").val(type);
}

function undoTopayinfo(obj){
	
	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.SAVE_TOKEN_TOPAY %>',{
	 }, function(data) {
		var token = data.token ;
		$("#undoModel #_token").val(token);
	}, 'json'
   );
	
	var batNo=$(obj).parent().find('#batNo').val();
	var type=$(obj).parent().find('#type_').val();
	var paerAcctNo_=$(obj).parent().find('#paerAcctNo_').val();
	$("#undoModel #toPayBatNo").text(batNo);
	$("#undoModel #toPay_Type").val(type);
	$("#undoModel #paerAcctNo_").val(paerAcctNo_);
}


function singleAuditPayment(obj,flag,audit_type,type) {
   var batNo=$(obj).parent().find('#batNo').val();
   
	$.post(window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.SELSINGLETOPAYINFO%>',{
 			"batNo":batNo
 			
 	},function(data){
 		if(data.result=='SUCCESS'){
				$("#updateMerchantModel #custName_audit").val(data.Info.custName);
				$("#updateMerchantModel #sumAmt").val(data.Info.sumAmt);
				$("#updateMerchantModel #paerAcctNo_1").val(data.Info.paerAcctNo);
				$("#updateMerchantModel #sumCount_1").val(1);
				$("#updateMerchantModel #serviceFee_1").val(data.Info.feeAmt);
				$("#updateMerchantModel #toPayType_1").val(data.Info.toPayType);
				var channelMerchantSelect = $("#updateMerchantModel #channelMerchantid");
				var channelMerchantidArray = new Array();
				channelMerchantidArray = data.channelMerchantidArray.split(",");
				if(channelMerchantidArray.length != 0){
					$("#updateMerchantModel #channelMerchantid").find("option").remove(); 
					channelMerchantSelect.css("display","");
					for(var i=0; i<channelMerchantidArray.length; i++){
						channelMerchantSelect.append("<option value='"+channelMerchantidArray[i]+"'>"+channelMerchantidArray[i]+"</option>");
					}
				}			 
				var id = data.Info.bankPaymentNo;
				//$("#updateMerchantModel #certAttribute0ImageDiv").show();
				
				if("00"==data.Info.toPayType){
					$("#updateMerchantModel #toPayType_2").val("银行卡");
				}else{
					$("#updateMerchantModel #toPayType_2").val("余额");
				}
				$("#updateMerchantModel #pay_1").val("单笔");
				var  serviceFee=Number(data.Info.feeAmt);
				var  sumAmt=Number(data.Info.sumAmt);
				$("#updateMerchantModel #totalMoney").val(serviceFee+sumAmt);
				$("#updateMerchantModel #bataNo_1").val(data.Info.batNo);
				$("#updateMerchantModel #createTime").val(data.Info.startCreateTime);
				$("#updateMerchantModel #toPatSumAmt").text(data.Info.sumAmt);
				$("#updateMerchantModel #toPayCount").text(1);
				$("#updateMerchantModel #toPayFee").text(data.Info.feeAmt);
				$("#updateMerchantModel #toPaySumMoney").text(serviceFee+sumAmt);
				$("#updateMerchantModel #single_flag").val(flag);
				$("#updateMerchantModel #audit_flag").val(audit_type);
				$("#updateMerchantModel #lineNumber").val(data.Info.lineNumber);
				if(type =='edit'){
					if(audit_type=='qjs'){
						$("#updateMerchantModel #hiddenCheck").attr("style","display:block");
	 					$("#updateMerchantModel #hiddenCheck_2").attr("style","display:none");
	 					$("#updateMerchantModel #hidden_cw").attr("style","display:none");
	 					
					}else{
						$("#updateMerchantModel #hiddenCheck").attr("style","display:none");
	 					$("#updateMerchantModel #hiddenCheck_2").attr("style","display:block");
	 					$("#updateMerchantModel #hidden_cw").attr("style","display:");
					}
				}else{
					$("#updateMerchantModel #hiddenCheck").attr("style","display:none");
 					$("#updateMerchantModel #hiddenCheck_2").attr("style","display:none");
 					$("#updateMerchantModel #hidden_cw").attr("style","display:none");
				}
				
				 var columns=$("#sample-table-4 #showPaymentList");
					$(columns).html('');
					var infos='';
					if(data.result=="fail"){
			 				infos="<tr><td colspan='9' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>";
			 		}
					
					$('#showPaymentList').on('show.bs.modal', function () {
					 });
					 infos+="<tr><td>"+data.Info.custName+"</td><td>"+data.Info.payeeAcctBankName+"</td></td><td>"+data.Info.recAccountNo+"</td><td>"+data.Info.sumAmt+"</td><td>"+data.Info.feeAmt+"</td></tr>";
				   $(columns).append(infos);
 		}else{
			$.gyzbadmin.alertFailure("数据查询失败！"+data.message);
		}
 	},'json'); 
	    
}	


var winChild
function batchAuditPayment(obj,type_1,audit_type) {
	         
	         if(type_1 =='edit'){
		     var batNo=$(obj).parent().find('#batNo').val();
		     var type=$(obj).parent().find('#type_').val();
			 var toPayType=$(obj).parent().find('#toPayType').val();
			 var url=window.Constants.ContextPath+"<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITPAYMENTTINFOS%>?batNo="+batNo+"&type="+type+"&toPayType="+toPayType+"&audit_type="+audit_type; 
		     var name="window";                        //网页名称，可为空;
		     var iWidth=1200;                          //弹出窗口的宽度;
		     var iHeight=600;                       //弹出窗口的高度;
		     //获得窗口的垂直位置
		     var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     //获得窗口的水平位置
		     var iLeft = (window.screen.availWidth-10-iWidth)/2;
		      var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		      $.blockUI(); 
		      winChild =  window.open(url, name,params);
		   
		   
	        }
        
	
    if(type_1 =='preview'){
    	var batNo=$(obj).parent().find('#batNo').val();
	     var type=$(obj).parent().find('#type_').val();
		 var toPayType=$(obj).parent().find('#toPayType').val();
		 var url=window.Constants.ContextPath+"<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITPAYMENTTINFOS%>?batNo="+batNo+"&type="+type+"&toPayType="+toPayType; 
	     var name="window";                        //网页名称，可为空;
	     var iWidth=1200;                          //弹出窗口的宽度;
	     var iHeight=600;                       //弹出窗口的高度;
	     //获得窗口的垂直位置
	     var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     //获得窗口的水平位置
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	      var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	      $.blockUI(); 
	      winChild =  window.open(url, name,params);
    	
    	
	   }
    if(type_1 == 'history'){
    	 var batNo=$(obj).parent().find('#batNo').val();
    	 var type_=$(obj).parent().find('#type_').val();
    	 
		 $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.GETHISTORY %>', {
		  
	 		"batNo" 	: batNo,
	 		"type_"     :type_
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
  	 					infos+="<tr><td>"+checkHistorys[i].batNO+"</td><td>"+checkHistorys[i].custName+"</td></td><td>"+checkHistorys[i].paerAcctNo+"</td><td>"+checkHistorys[i].auditTime+"</td><td>"+checkHistorys[i].memo+"</td></tr>";
					}  
  	 			 
	 		 $(columns).append(infos);
	 		});
	    
       }
	   
		 
}

function forCloseDiv(){
	 $.unblockUI();
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
	
	 var loop =	setInterval(function() {     
		 $.unblockUI();  
		      
		}, 200);
	 
	 var topayType = $("#toPayType_3").val();
	 var type_2 = $("#type_2").val();
		
	$("#toPayType option[value='"+topayType+"']").removeAttr("selected");//根据值去除选中状态  
	$("#toPayType option[value='"+topayType+"']").attr("selected","selected");
	
	$("#type option[value='"+type_2+"']").removeAttr("selected");//根据值去除选中状态  
	$("#type option[value='"+type_2+"']").attr("selected","selected");
	
	var tradeStatus = $("#trade_Status").val();
	
	$("#tradeStatus option[value='"+tradeStatus+"']").removeAttr("selected");//根据值去除选中状态  
	$("#tradeStatus option[value='"+tradeStatus+"']").attr("selected","selected");
	 
	 
	/** 缓存 **/
	var custIds = ${audingList};
	var custId = $("tr.payment");
	$.each(custIds,function(i,value){
		$.data(custId[i],"payment",value);
	});
	
	
	 function isPercent(c){
		 var r= /^[1-9]?[0-9]*\.[0-9]+[%]$/;
		 return r.test(c);
	}
	 
	 
	//导出商户代付数据
		$('.exportBut').click(function(){
			
			var batNo = $(".search-table #batNo").val();
			var paerAcctNo = $(".search-table #paerAcctNo").val();
			var startCreateTime = $(".search-table #startCreateTime").val();
			var endCreateTime = $(".search-table #endCreateTime").val();
			/* var toPayType = $(".search-table #toPayType").val(); */
			var toPayType = '00';
			var type = $(".search-table #type").val();
			var tradeStatus = $(".search-table #tradeStatus").val(); 
			alert(startCreateTime);
			var src ="<%= request.getContextPath()+ PaymentManagerPath.BASE+PaymentManagerPath.PROEXPORTMERCHANTINFO%>?batNo="+
			batNo+
			"&paerAcctNo="+
			paerAcctNo+
			"&startCreateTime="+
			startCreateTime+
			"&endCreateTime="+
			endCreateTime+
			"&toPayType="+
			toPayType+
			"&type="+
			type+
			"&tradeStatus="+
			tradeStatus;
			$(".exportBut").attr("href",src);			
		});
	 
	 /** 审核通过 */
	$(".firstAuditPassBtn").click(function(){
		
		 var singleFlag=$('#updateMerchantModel #single_flag').val();
		 var auditFlag=$('#updateMerchantModel #audit_flag').val();
		 var batNo=$('#updateMerchantModel #bataNo_1').val();
		 var email=$('#updateMerchantModel #paerAcctNo_1').val();
		 var custName=$('#updateMerchantModel #custName_audit').val();
		 
		 
		 if("single"==singleFlag){
			 $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.SINGLEAUDITFRISTPASS %>',
					data:
					{
						"batNo" :batNo
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
		 }else{
			 $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITFRISTPASS %>',
					data:
					{
						"batNo" 	:batNo,
						"email"     :email,
						"custName"  :custName
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
	
});
 
 
 function bigImg(obj){
	    $('#showImageDiv #showImage').attr("src",obj.src);
	};
	
function firstAuditNotPassBtn(){
	 var batNo=$('#updateMerchantModel #bataNo_1').val();
	 var email=$('#updateMerchantModel #paerAcctNo_1').val();
	 var custName=$('#updateMerchantModel #custName_audit').val();
	 var reason=$("#auditMessage").val();
	 var singleFlag=$('#updateMerchantModel #single_flag').val();
	 var auditFlag=$('#updateMerchantModel #audit_flag').val();
	 
	 var tradeStatus ="92";
	 if("qjs"==auditFlag){
		 tradeStatus ="92";
	 }else{
		 tradeStatus ="93";
	 }
	 
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
	   
	   if("single"==singleFlag){
		   $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.SINGLEAUDITNOTPASS %>',
				data:
				{
					"batNo" 	:batNo,
					"email"     :email,
					"custName"  :custName,
					"auditFlag": auditFlag,
					"tradeStatus":tradeStatus,
					"message"	 :messages
					
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
	   }else{
		   $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITFRISTNOTPASS %>',
				data:
				{
					"batNo" 	:batNo,
					"message"	:messages,
					"email"     :email,
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
	   
	   
	}
	
 


function secoundAuditPassBtn(){
	 var batNo=$('#updateMerchantModel #bataNo_1').val();
	 var audit_flag=$('#updateMerchantModel #audit_flag').val();
	 var single_flag=$('#updateMerchantModel #single_flag').val();
	 var token = $("#secondAuditMessageModel #_token").val();
	 var channelType=$('#updateMerchantModel #channelType').val();
	 var lineNumber=$('#updateMerchantModel #lineNumber').val();
	 var channelMerchantid=$("#updateMerchantModel #channelMerchantid option:selected").val();

	 if("single"==single_flag){
		 $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.SINGLEPAYMENTPASS %>',
				data:
				{
					"batNo" 	: batNo,
					"_TOKEN_ID":token,
					channelType:channelType,
					lineNumber:lineNumber,
					channelMerchantid:channelMerchantid
				},
				success:function(data){
					
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess(data.message,function(){
							$("#updateMerchantModel").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("代付失败！"+data.message,function(){
							$("#updateMerchantModel").modal("hide");
						},function(){
							window.location.reload();
						});
						
					}
				}
			}) ;
	 }else{
			 $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.BATPAYMENTPASS %>',
					data:
					{
						"batNo" 	: batNo
					},
					success:function(data){
						
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess(data.message,function(){
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
	   
}

	
</script>
</html>