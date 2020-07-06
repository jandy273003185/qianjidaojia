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
	<title>差错处理</title>
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
				<!-- <ul id="myTab" class="nav nav-tabs">
							   <li><a href="#return" data-toggle="tab" id="returnList">返回</a></li>
							 </ul> -->
					<div class="main-container" id="main-container">
					<form  id="tradeForm" action='<c:url value="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.ERRORDISPOSAL%>"/>' method="post">
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
								 <%-- <td class="td-left"  style="width:10%;">外部订单号</td>
								<td class="td-right"  style="width:20%;">
									<span class="input-icon">
										<input type="text"  name="oldOrderId"  id="oldOrderId" value="${queryBean.oldOrderId }"  style="width:88%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>  --%>
								<td  class="td-left" width="10%">处理状态</td>
								<td class="td-right" width="15%">
									<span class="input-icon">
										<select name="dealFlag" id="dealFlag">										
											<option value="">--请选择--</option>
											 <option value="0" <c:if test="${'0' eq queryBean.dealFlag}">selected</c:if> >未处理</option>
											 <option value="1" <c:if test="${'1' eq queryBean.dealFlag}">selected</c:if> >已处理</option>  
										</select>
									</span>
								</td>
							</tr>
							<tr>								
								<td class="td-left" width="10%">交易日期</td>
								<td class="td-right" width="30%">
									<span class="input-icon">
										<input type="text" name="createTimeMin"  id="createTimeMin" value="${queryBean.createTimeMin }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										<input type="text" name="createTimeMax"  id="createTimeMax" value="${queryBean.createTimeMax }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
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
							  	<gyzbadmin:function url="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.ERRORDISPOSAL%>">						
							  		<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button type="button" class="btn btn-purple btn-sm btn-margin clearImpeach" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>
									<%--  <c:if test="${ReconciliationDetailsList != null}"><button type="button" class="btn btn-purple btn-sm btn-margin exportBut">导出报表</button></c:if>
									 
									 <c:if test="${queryBean.reconResult != 'LACK' and queryBean.paychannelType ne 'SEVENPAY'}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportCheckBut">导出原数据</button>
									 </c:if> --%>
								  </gyzbadmin:function> 
							  </td>
							</tr>
						</table>
						</form>
						<div class="list-table-header">差错详情列表</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="18%">订单号</th>
										<!--<th width="8%">外部订单号</th> -->
										<th width="8%">支付渠道</th>
										<th width="10%">交易金额</th>
										<!-- <th width="5%">手续费</th> -->
										<th width="5%">交易状态</th>
										<c:if test="${queryBean.reconResult != 'DIFF_SUCCESS'}">
											<th width="5%">交易类型</th>
										</c:if>
										<!--<th width="11%">交易时间</th>-->
 										<th width="8%">交易时间</th>
 										<th width="8%">处理状态</th>
										<th width="8%">差错处理</th>
								    </tr>
								</thead>
								<tbody>
									<c:forEach items="${ReconciliationDetailsList}" var="reconciliation" varStatus="status">
										<tr>
											<td id="orderId">${reconciliation.orderId}</td>
											<%-- <td>${reconciliation.oldOrderId}</td> --%>
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
											<%-- <td>
												${fn:substring(reconciliation.tradeTime, 0, 19)}
												<fmt:formatDate value="${reconciliation.tradeTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td> --%>
											<td>												
												 <fmt:formatDate value="${reconciliation.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>
												<c:if test="${reconciliation.dealFlag == '1'}">已处理</c:if>
												<c:if test="${reconciliation.dealFlag != '1'}">未处理</c:if>
											</td>
											<td>
												<c:if test="${reconciliation.dealFlag != '1'}">
													<button type="button" class="btn btn-purple btn-sm btn-margin" id = "disposal" style="margin: 0 0 0 30px" onclick="disposalBut('${reconciliation.orderId}');">处理</button>
												</c:if>
												 <%-- <c:if test="${reconciliation.dealFlag == '1'}" >
													<button type="button" class="btn btn-purple btn-sm btn-margin " id = "disposal" style="margin: 0 0 0 20px">已处理</button>
												</c:if> --%> 
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
	<%-- //返回对账汇总
	$("#returnList").click(function(){
		 window.location.href="<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST %>?createTime=${queryBean.createTime}";
	 }); 

	--%>
	//差错处理	
	function disposalBut(orderId) {
		
		var url = "<%= request.getContextPath()+ ReconciliationSummaryPath.BASE+ReconciliationSummaryPath.DISPOSAL%>?orderId=" + orderId;
		$.post(url, function(result){
			
			if(result == 'success'){
				<%-- window.location.href = "<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.ERRORDISPOSAL%>"; --%>
				 window.location.reload();
				alert("处理成功");
			} else {
				alert("处理失败");
			}
		});
	}
	
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
			var createTime =$("#createTime").val();
			var dealFlag =$("#dealFlag").val();
			var createTimeMin =$("#createTimeMin").val();
			var createTimeMax= $("#createTimeMax").val();
			if(''!=createTimeMin&&''!=createTimeMax){
				if(createTimeMin > createTimeMax) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			}
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