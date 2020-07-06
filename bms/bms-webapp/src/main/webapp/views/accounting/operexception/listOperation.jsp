<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.Operation"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.type.OperationStatus"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>

<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>业务异常处理</title>
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
							<form action='<c:url value="<%=OperationExceptionPath.BASE + OperationExceptionPath.LIST_OPERATION %>"/>' method="post" id="searchForm">
							<% Operation requestBean = (Operation)pageContext.findAttribute("requestBean"); %>
							<table class="search-table">
								<tr>
									<td class="td-left" width="15%">业务编号</td>
									<td class="td-right" width="35%">
										<span class="input-icon">
											<input type="text" name="operId"  id="operId" value="${requestBean.operId }" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="15%">业务类型</td>
									<td class="td-right" width="35%">
									   <span class="input-icon">
											<select name="operType" id="operType">
												<option value="">--请选择--</option>
												<% 
													for(RequestColumnValues.MsgType operType : RequestColumnValues.MsgType.values()) {
														if(Arrays.asList(RequestColumnValues.MsgType.CUST_RECHARGE,
																RequestColumnValues.MsgType.RED_PACKET_RECHARGE,
																RequestColumnValues.MsgType.SMALL_QUICK_RECHARGE,
																RequestColumnValues.MsgType.BALANCE_PAYMENT,
																RequestColumnValues.MsgType.BANK_CARD_PAYMENT,
																RequestColumnValues.MsgType.RED_PACKET_PAYMENT,
																RequestColumnValues.MsgType.RED_PACKET_PAYMENT_REFUND,
																RequestColumnValues.MsgType.BANK_CARD_PAYMENT_REFUND,
																RequestColumnValues.MsgType.BALANCE_PAYMENT_REFUND,
																RequestColumnValues.MsgType.CUST_WITHDRAW,
																RequestColumnValues.MsgType.CUST_WITHDRAW_APPLY,
																RequestColumnValues.MsgType.CUST_TRANSFER,
																RequestColumnValues.MsgType.CUST_TRANSFER_REVOKE,
																RequestColumnValues.MsgType.BUSS_SETTLE,
																RequestColumnValues.MsgType.BUSS_SETTLE_APPLY,
																RequestColumnValues.MsgType.CUST_FULL_FREEZE,
																RequestColumnValues.MsgType.CUST_RECHARGE_REVOKE,
																RequestColumnValues.MsgType.BALANCE_PAYMENT_REVOKE,
																RequestColumnValues.MsgType.BANK_CARD_PAYMENT_REVOKE,
																RequestColumnValues.MsgType.CUST_FULL_UNFREEZE).contains(operType)) {
												%>
													<option value="<%=operType.name() %>" <% if(operType == requestBean.getOperType()){ %>selected<% } %>>--<%=ReflectUtils.getDesc(operType) %>--</option>
												<% }} %>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="15%">付方用户号</td>
									<td class="td-right" width="35%">
										<span class="input-icon">
											<input type="text" name="payCustId"  id="payCustId" value="${requestBean.payCustId }" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="15%">付方用户名称</td>
									<td class="td-right" width="35%">
									   <span class="input-icon">
											<input type="text" name="payCustName"  id="payCustName" value="${requestBean.payCustName }" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>										
								<tr>
									<td class="td-left">业务时间</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" name="operDateMin"  id="operDateMin" value="${requestBean.operDateMin }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
											<input type="text" name="operDateMax"  id="operDateMax" value="${requestBean.operDateMax }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										</span>
									</td>
									<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
											<select name="status" id="status">
												<option value="">--请选择--</option>
												<% for(OperationStatus status : OperationStatus.values()) { %>
													<option value="<%=status.name() %>" <% if(status == requestBean.getStatus()){ %>selected<% } %>>--<%=ReflectUtils.getDesc(status) %>--</option>
												<% } %>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.LIST_OPERATION %>">
												<button type="submit" class="btn btn-purple btn-sm searchBtn" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											&nbsp;
											<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											&nbsp;
											<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.EXPORTEXCEL%>">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">业务异常列表</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>业务编号</th>
											<th>业务类型</th>
											<th>付方用户号</th>
											<th>付方用户名称</th>
											<th>交易金额</th>
											<th>收方用户号</th>
											<th>收方用户名称</th>
											<th>状态</th>
											<th>发生时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${exceptionList }" var="exception">
										<%
										  Operation exception = (Operation)pageContext.findAttribute("exception");
										%>
										<tr class="settle">
											<td>${exception.operId }</td>
											<td><%=ReflectUtils.getDesc(exception.getOperType())%></td>
											<td>${exception.payCustId }</td>
											<td>${exception.payCustName }</td>
											<td>${exception.transAmt }</td>
											<td>${exception.rcvCustId }</td>
											<td>${exception.rcvCustName }</td>
											<td><%=ReflectUtils.getDesc(exception.getStatus())%></td>
											<td>${exception.operDatetime }</td>
											<td class="buttonTd">
												<input type="hidden" name="operId" value="${exception.operId }"/>
												<input type="hidden" name="operType" value="${exception.operType }"/>
												<input type="hidden" name="status" value="${exception.status }"/>
												<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION%>">
												   <c:if test="${exception.status !='DEAL_SUCCESS'&& exception.status !='DEAL_CANCELLED'}">
													<button type="button" class="btn btn-primary btn-xs detailLink" data-toggle='modal'>
														处理
													</button>
													</c:if>
													<c:if test="${exception.status =='DEAL_SUCCESS'||exception.status =='DEAL_CANCELLED'}">
													<button type="button" class="btn btn-primary btn-xs detailLink" data-toggle='modal'>
														查看
													</button>
													</c:if>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty exceptionList}">
										<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty exceptionList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
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
	</div>

	<script type="text/javascript">
		jQuery(function($){

			$('.clearBtn').click(function(){
				$(':input','#searchForm')  
				 .not(':button, :submit, :reset, :hidden')  
				 .val('')  
				 .removeAttr('checked')  
				 .removeAttr('selected'); 
			});
			
			
			$('.searchBtn').click(function(){
				var startDate = $("#operDateMin").val();
				var endDate= $("#operDateMax").val();
				if("" != startDate && "" != endDate && startDate > endDate) {
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			});

			$('.detailLink').click(function(){
				$.blockUI();
				$.gyzbadmin.postForm('<c:url value="<%=OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION %>" />',{
						'operId' : $(this).parent().find(':hidden[name="operId"]').val(),
						'operType' : $(this).parent().find(':hidden[name="operType"]').val(),
						'status' : $(this).parent().find(':hidden[name="status"]').val()
					}
				);
			});
			
			$(".exportBut").click(function(){
				var operId = $(".search-table #operId").val();
				var operType = $(".search-table #operType").val();
				var payCustName = $(".search-table #payCustName").val();
				var payCustId = $(".search-table #payCustId").val();
				var operDateMax = $(".search-table #operDateMax").val();
				var operDateMin = $(".search-table #operDateMin").val();
				var status = $(".search-table #status").val();
				var src ="<%= request.getContextPath()+ OperationExceptionPath.BASE+OperationExceptionPath.EXPORTEXCEL%>?operDateMin="+
				operDateMin+"&operDateMax="+operDateMax+"&status="+status+"&payCustId="+payCustId+"&payCustName="+payCustName+"&operType="+operType+"&operId="+operId;
				$(".exportBut").attr("href",src);
			});
		});
		
	</script>
  </body>	
</html>