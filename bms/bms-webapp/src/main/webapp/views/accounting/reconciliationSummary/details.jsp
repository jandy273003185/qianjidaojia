<%@page import="com.qifenqian.bms.accounting.reconciliationSummary.controller.ReconciliationSummaryPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="UserStatus.tradeType"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>对账详情</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body>
<!-- 科目配置信息 -->
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
				<ul id="myTab" class="nav nav-tabs">
							   <li><a href="#return" data-toggle="tab" id="returnList">返回</a></li>
							 </ul>
					<div class="main-container" id="main-container">
					<form  id="tradeForm" action='<c:url value="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.DETAILS%>"/>' method="post">
						<input type="hidden" name="createTime" value="${queryBean.createTime}" />
						<input type="hidden" name="reconResult" value="${queryBean.reconResult}" />
						<input type="hidden" name="paychannelType" value="${queryBean.paychannelType}" />
						<table class="search-table" style="border:none;">
							<tr>
								<td class="td-left"  style="width:10%;">订单号</td>
								<td class="td-right"  style="width:20%;">
									<span class="input-icon">
										<input type="text"  name="orderId"  id="orderId" value="${queryBean.orderId }"  style="width:88%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>								
								 <td class="td-left"  style="width:10%;">外部订单号</td>
								<td class="td-right"  style="width:20%;">
									<span class="input-icon">
										<input type="text"  name="oldOrderId"  id="oldOrderId" value="${queryBean.oldOrderId }"  style="width:88%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td> 
								<td class="td-left"  style="width:10%;">交易类型</td>
								<td class="td-right" width="15%">
									<span class="input-icon">
										<select name="inOut" id="inOut">
											<option value="">--请选择--</option>
											<c:forEach items="<%=tradeType.values()%>" var="inOut">
												<option value="${inOut }" <c:if test="${inOut == queryBean.inOut }">selected</c:if>>${inOut.desc }</option>
											</c:forEach>
										</select>
									</span>
								</td>		
							</tr>
							<tr>
							  
							  <td colspan="6" align="center">
							  	<gyzbadmin:function url="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.DETAILS%>">						
							  		<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button type="button" class="btn btn-purple btn-sm btn-margin clearImpeach" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>
									 <c:if test="${ReconciliationDetailsList != null}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportBut">导出报表</button>
									 </c:if>
									 <c:if test="${queryBean.reconResult != 'LACK' and queryBean.paychannelType ne 'SEVENPAY'}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportCheckBut">导出原数据</button>
									 </c:if>
								  </gyzbadmin:function> 
							  </td>
							</tr>
						</table>
						</form>
						<div class="list-table-header">对账详情列表</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="18%">订单号</th>
										<th width="8%">外部订单号</th>
										<th width="8%">支付渠道</th>
										<th width="10%">交易金额</th>
										<!-- <th width="5%">手续费</th> -->
										<th width="5%">交易状态</th>
										<c:if test="${queryBean.reconResult != 'DIFF_SUCCESS'}">
										<th width="5%">交易类型</th>
										</c:if>
										<th width="11%">交易时间</th>
										<th width="8%">对账时间</th>
								    </tr>
								</thead>
								<tbody>
									<c:forEach items="${ReconciliationDetailsList}" var="reconciliation" varStatus="status">
										<tr>
											<td>${reconciliation.orderId}</td>
											<td>${reconciliation.oldOrderId}</td>
											<td id="paychannelType">
												<c:forEach items="<%=PaychannelType.values()%>" var="status">
													 <c:if test="${status == reconciliation.paychannelType}">${status.text}</c:if>
												</c:forEach>
											</td>
											<td>${reconciliation.tradeAmt}</td>
											<%-- <td>${reconciliation.tradeFee}</td> --%>
											<c:if test="${queryBean.reconResult != 'DIFF_SUCCESS'}">
											<td>
												<c:if test="${reconciliation.reconResult == 'SUCCESS'}">成功</c:if>
												<c:if test="${reconciliation.reconResult == 'LACK'}">掉单</c:if>
												<c:if test="${reconciliation.reconResult == 'LOSE'}">丢单</c:if>
												<c:if test="${reconciliation.reconResult == 'DIFF_SUCCESS'}">差错成功</c:if>
											</td>
											</c:if>
											<td>
												<c:if test="${reconciliation.inOut == 'OUT'}">
												支付
												</c:if>
												<c:if test="${reconciliation.inOut == 'IN'}">
												退款
												</c:if>
											</td>
											<td>
												${fn:substring(reconciliation.tradeTime, 0, 19)}
											</td>
											<td>
												${fn:substring(reconciliation.createTime, 0, 10)}
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty ReconciliationDetailsList}">
										<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty ReconciliationDetailsList}">
							<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
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
	<script type="text/javascript">
	//返回对账汇总
	$("#returnList").click(function(){
		 window.location.href="<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST %>?createTime=${queryBean.createTime}";
	 });
	/*对账详情*/
	$("#returnList").click(function(){
		 window.location.href="<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST %>"
	 });
	
	$(function () {	
		$(".exportBut").click(function(){
			var reconResult = $('.search-table #reconResult').val();
			var createTime = $('.search-table #createTime').val();
			var paychannelType = $('.search-table #paychannelType').val();
			var src ="<%= request.getContextPath()+ ReconciliationSummaryPath.BASE+ReconciliationSummaryPath.DETAILSEXPORT%>?paychannelType=${queryBean.paychannelType}&createTime=${queryBean.createTime}&reconResult=${queryBean.reconResult}";
			/* $(".exportBut").attr("href",src); */
			window.location.href = src;
		});

		$(".exportCheckBut").click(function(){
			var reconResult = $('.search-table #reconResult').val();
			var src ="<%= request.getContextPath()+ ReconciliationSummaryPath.BASE+ReconciliationSummaryPath.DETAILSCHECKEXPORT%>?reconResult=${queryBean.reconResult}&paychannelType=${queryBean.paychannelType}&createTime=${queryBean.createTime}"
			window.location.href = src;
		});
		
		
		//按条件查询
		$("#serchSubmit").click(function(){
			var orderId =$("#orderId").val();
			var oldOrderId =$("#oldOrderId").val();
			var createTime =$("#createTime").val();
			var form = $('#tradeForm');
			form.submit();
		});

		//清空
		$('.clearImpeach').click(function(){
			$(':input','#tradeForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
	});	
	
	</script>
</body>
</html>					