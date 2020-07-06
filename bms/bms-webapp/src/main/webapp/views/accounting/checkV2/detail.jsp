<%@page import="com.qifenqian.bms.accounting.checkV2.CheckV2Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.qifenqian.bms.platform.web.admin.user.type.UserStatus"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>对账详情（新版）</title>
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
					<form  id="tradeForm" action='<c:url value="<%=CheckV2Path.BASE + CheckV2Path.DETAIL%>"/>' method="post">
						<table class="search-table" style="border:none;">
						<input type="hidden" name="checkDateStr" value="${checkDetailRequestBean.checkDateStr}" />
						<input type="hidden" name="checkResult" value="${checkDetailRequestBean.checkResult}" />
						<input type="hidden" name="channelType" value="${checkDetailRequestBean.channelType}" />
							<tr>
								<td class="td-left"  style="width:10%;">平台订单号</td>
								<td class="td-right"  style="width:20%;">
									<span class="input-icon">
										<input type="text"  name="tradeId"  id="tradeId" value="${checkDetailRequestBean.tradeId }"  style="width:88%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>								
								 <td class="td-left"  style="width:10%;">渠道订单号</td>
								<td class="td-right"  style="width:20%;">
									<span class="input-icon">
										<input type="text"  name="channelOrderId"  id="channelOrderId" value="${checkDetailRequestBean.channelOrderId}"  style="width:88%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td> 
								<td class="td-left"  style="width:10%;">交易类型</td>
								<td class="td-right" width="15%">
									<span class="input-icon">
										<select name="tradeType" id="tradeType">
											<option value="">--请选择--</option>
											<option value="pay" <c:if test="${'pay' == checkDetailRequestBean.tradeType }">selected</c:if>>支付</option>
											<option value="refund" <c:if test="${'refund' == checkDetailRequestBean.tradeType }">selected</c:if>>退款</option>
										</select>
									</span>
								</td>		
							</tr>
							<tr>
							  <td colspan="6" align="center">
							  		<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button type="button" class="btn btn-purple btn-sm btn-margin clearImpeach" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>
									<c:if test="${checkDetailList != null}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportBut">导出报表</button>
									 </c:if>
									 <%-- <c:if test="${ReconciliationDetailsList != null}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportBut">导出报表</button>
									 </c:if>
									 <c:if test="${checkDetailRequestBean.reconResult != 'LACK' and checkDetailRequestBean.paychannelType ne 'SEVENPAY'}">
									 	<button type="button" class="btn btn-purple btn-sm btn-margin exportCheckBut">导出原数据</button>
									 </c:if> --%>
							  </td>
							</tr>
						</table>
						</form>
						<div class="list-table-header">对账详情列表</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="5%">平台订单号</th>
										<th width="4%">交易类型</th>
										<th width="5%">服务商号</th>
										<th width="5%">商户号</th>
										<th width="5%">商户名称</th>
										<th width="6%">渠道</th>
										<th width="5%">渠道订单号</th>
										<th width="6%">交易金额</th>
										<th width="4%">渠道手续费</th>
										<th width="4%">交易手续费</th>
										<th width="6%">划款金额</th>
										<th width="4%">退款原订单号</th>
										<th width="13%">订单创建时间</th>
										<th width="13%">订单完成时间</th>
										<th width="4%">对账结果</th>
										<th width="8%">对账日期</th>
								    </tr>
								</thead>
								<tbody>
									<c:forEach items="${checkDetailList}" var="checkDetail" varStatus="status">
										<tr>
											<td>${checkDetail.tradeId}</td>
											<td>
												<c:if test="${'pay' == checkDetail.tradeType }">支付</c:if>
												<c:if test="${'refund' == checkDetail.tradeType }">退款</c:if>
											</td>
											<td>${checkDetail.agentMchId}</td>
											<td>${checkDetail.mchId}</td>
											<td>${checkDetail.mchName}</td>
											<td>
												<c:forEach items="<%=PaychannelType.values()%>" var="status">
													<c:if test="${status == checkDetail.channelType}">${status.text}</c:if>
												</c:forEach>
											</td>
											<td>${checkDetail.channelOrderId}</td>
											<td>${checkDetail.tradeAmt}</td>
											<td>${checkDetail.channelFee}</td>
											<td>${checkDetail.tradeFee}</td>
											<td>${checkDetail.transferAmt}</td>
											<td>${checkDetail.refundOriginOrderId}</td>
											<td>
												<fmt:formatDate value="${checkDetail.orderCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>
												<fmt:formatDate value="${checkDetail.orderFinishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											
											<c:if test="${'SUCCESS' == checkDetail.checkResult }"><td>正常</td></c:if>
											<c:if test="${'PLACK' == checkDetail.checkResult }"><td bgcolor="red">丢单</td></c:if>
											<c:if test="${'CLACK' == checkDetail.checkResult }"><td bgcolor="red">掉单</td></c:if>
											<c:if test="${'OTHERS' == checkDetail.checkResult }"><td bgcolor="red">其他差错账</td></c:if>
											
											<td><fmt:formatDate value="${checkDetail.checkDate}" pattern="yyyy-MM-dd"/></td>
										</tr>
									</c:forEach>
									<c:if test="${empty checkDetailList}">
										<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty checkDetailList}">
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
	/*对账详情*/
	$("#returnList").click(function(){
		 window.location.href="<%=request.getContextPath()+CheckV2Path.BASE + CheckV2Path.LIST %>"
	 });
	
	$(function () {	
		$(".exportBut").click(function(){
			var checkResult = $('.search-table #checkResult').val();
			var channelType = $('.search-table #channelType').val();
			var checkDateStr = $('.search-table #checkDateStr').val();
			var tradeId = $('.search-table #tradeId').val();
			var channelOrderId = $('.search-table #channelOrderId').val();
			var tradeType = $('.search-table #tradeType').val();
			var src ="<%= request.getContextPath()+ CheckV2Path.BASE+CheckV2Path.EXPLORER_DETAIL%>?"+
					"checkResult=${checkDetailRequestBean.checkResult}&"+
					"channelType=${checkDetailRequestBean.channelType}&"+
					"checkDateStr=${checkDetailRequestBean.checkDateStr}&"+
					"tradeId=${checkDetailRequestBean.tradeId}&"+
					"channelOrderId=${checkDetailRequestBean.channelOrderId}&"+
					"tradeType=${checkDetailRequestBean.tradeType}";
			/* $(".exportBut").attr("href",src); */
			window.location.href = src;
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