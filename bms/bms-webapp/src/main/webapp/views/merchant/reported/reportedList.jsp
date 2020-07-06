<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>

<script src='<c:url value="/static/js/selectAll.js"/>'></script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户报备查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr th{word-wrap:break-word;white-space:nowrap;} 
 table tr td{word-wrap:break-word;white-space:nowrap;} 
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<script type="text/javascript">
$(function(){
	$("#mchRole").val($("#mchRole_").val());
	$("#auditStatus").val($("#auditStatus_").val());
	$("#channelNo").val($("#channelNo_").val());
	$('.buttonSearch').click(function(){
		var form = $('#merchantForm');
		form.submit();
	});
	$('.clearMerchantSearch').click(function(){
		$("#mchRole").val("");
		$("#auditStatus").val("");
		$("#channelNo").val("");
		$("#merchantCode").val("");
	});
	
	$(".exportBut").click(function(){
		var merchantCode = $("#merchantCode").val();
		var mchRole = $("#mchRole").val();
		var auditStatus = $("#auditStatus").val();
		var channelNo = $("#channelNo").val();
		
		var src ="<%= request.getContextPath()+ MerchantReportedPath.BASE + MerchantReportedPath.EXPORT%>?"+
			"&merchantCode="+merchantCode+
			"&mchRole="+mchRole+
			"&auditStatus="+auditStatus+
			"&channelNo="+channelNo;
		$(".exportBut").attr("href",src);
	});
	
})

</script>
<body >
	<!-- 用户信息 -->
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
							<input type="hidden" id="mchRole_" name="mchRole_" value="${queryBean.mchRole }"/>
							<input type="hidden" id="auditStatus_"  name="auditStatus_" value="${queryBean.auditStatus }"/>
							<input type="hidden" id="channelNo_" name="channelNo_" value="${queryBean.channelNo }"/>
							
							<form  id="merchantForm" action='<c:url value="<%=MerchantReportedPath.BASE + MerchantReportedPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >商户角色：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<select name="mchRole" id="mchRole" >
											  <option  value="" > 请选择 </option>
											  <option  value="0"> 线上商户 </option>
											  <option  value="1"> 线下商户 </option>
										 	</select>
										</span>
									</td>
									
								</tr>
								<tr>
									<td class="td-left">报备审核状态：</td>
									<td class="td-right">								   
										 <select name="auditStatus" id="auditStatus" >
											  <option  value="" > 请选择 </option>
											  <option  value="00"> 成功 </option>
											  <option  value="99"> 失败 </option>
											  <option  value="01"> 待提交审核 </option>
											  <option  value="02"> 提交审核中 </option>
										 </select>
									</td>
									<td class="td-left" >报备渠道：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<select name="channelNo" id="channelNo" >
											  <option  value="" > 请选择 </option>
											  <option  value="iCr"> 华润银行 </option>
											  <option  value="iCr1"> 富民银行 </option>
										 	</select>
										</span>
									</td>
									
									</tr>
									<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MerchantReportedPath.BASE + MerchantReportedPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span> 
										</span>
									</td>
								</tr>
							</table>
							</form>
						
							<div class="list-table-header">结算列表</div>

							<div class="table-responsive" style="overflow:scroll">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th>商户编号</th>
											<th>商户名称</th>
											<th>商户角色</th>
											<th>报备渠道</th>
											<th>报备状态</th>
											<th>审核状态</th>	
											<th>进件状态</th>
											<th>进件描述</th>	
											<th>创建时间</th>
											<!-- <th >操作</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reportedList }" var="reported">
											<tr class="reported" id="reported">
												<td>${reported.merchantCode }</td>
												<td>${reported.custName }</td>
												<td>
													<c:choose>   
								                        <c:when test="${reported.mchRole == '0' }">  									  
								                                                              线上商户  								  
								                        </c:when>  
								  						<c:when test="${reported.mchRole == '1' }">  									  
								                           	      线下商户 								  
								                        </c:when> 
			                                        </c:choose>
													
												</td>
												<td>
													 <c:if test="${reported.channelNo == 'iCr'}">
											    		华润银行
											    	</c:if>
												</td>
												<td>
												 	<c:choose>   
								                        <c:when test="${reported.filingStatus == '01'}">  									  
								                                                              未提交报备  								  
								                        </c:when>  
								  						<c:when test="${reported.filingStatus == '00'}">  									  
								                           	      已提交报备 								  
								                        </c:when> 
			                                        </c:choose>
												</td>
												<td>
													<c:choose>   
								                        <c:when test="${reported.auditStatus=='00' }">  									  
								                                                              成功								  
								                        </c:when>  
								  						<c:when test="${reported.auditStatus=='99' }">  									  
								                           	      失败 								  
								                        </c:when> 
								                        <c:when test="${reported.auditStatus=='01' }">  									  
								                           	      待提交审核 								  
								                        </c:when>
								                        <c:when test="${reported.auditStatus=='02' }">  									  
								                           	      提交审核中 								  
								                        </c:when> 
			                                        </c:choose>
												</td>
												<td>
													<c:choose>   
								                        <c:when test="${reported.merStatus=='200' }">  									  
								                                                         成功								  
								                        </c:when>  
								                        <c:otherwise>
								                        	 失败
								                        </c:otherwise>
			                                        </c:choose>
												</td>
												<td>${reported.merMsg }</td>
												<td>
												<%-- <fmt:formatDate value="${reported.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
												${reported.createTime }
												</td>
												
												<%-- <td>	
													<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}">
												</td> --%>
											</tr>
										</c:forEach>
										<c:if test="${empty reportedList}">
											<tr><td colspan="26" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								
							</div>
							<c:if test="${not empty reportedList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
							
						</div>
					</div>
				   
				  
				</div><!-- /.modal -->
				
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	</div>

	
  </body>	
</html>