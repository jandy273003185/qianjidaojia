<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.ClearJgkjTradePath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>交广科技交易未明列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<script type="text/javascript">
function loadClearJgkjTrade(){
	$('.search-table #businessType').val($(".search-table #businessTypeTemp").val());
}
$(document).ready(function(){
	 $('.clearJgkjTradeClr').click(function(){			
		$(".page-content #businessType").val("");
		$(".page-content #cardNoClr").val("");
		$(".page-content #transFlowIdClr").val("");
		$(".page-content #workDateClr").val("");      
	}) 
});
</script>
<body onload="loadClearJgkjTrade()">
	<!-- 充值/支付未明处理 -->
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
							<form action='<c:url value="<%=ClearJgkjTradePath.BASE+ ClearJgkjTradePath.LIST%>"/>' method="post">
								<table class="search-table">
									<tr>
										<td class="td-left">交易编号</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text"  name="transFlowId"  value="${queryBean.transFlowId }" id="transFlowIdClr">
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										<td class="td-left">交易类型</td>
										<td class="td-right">
											<span class="input-icon">
												<input id="businessTypeTemp" value="${queryBean.businessType }"  type="hidden">
												<select name="businessType" id="businessType" >
												 <option value = "">--请选择--</option>
												 <option value = "PAYMENT">支付</option>
												 <option value = "RECHARGE">充值</option>
												 <option value = "WITHDRAW_APPLY">提现申请</option>
												</select>
											</span>
										</td>
									</tr>
									<tr>
										<td class="td-left">交广科技卡号</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text"  name="cardNo" value="${queryBean.cardNo }" id="cardNoClr">
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										<td class="td-left">七分钱会计日期</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text"  name="workDate" value="${queryBean.workDate}"  id="workDateClr"  readonly="readonly"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
												<i class="icon-leaf blue"></i>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<span class="input-group-btn">
												<gyzbadmin:function url="<%=ClearJgkjTradePath.BASE + ClearJgkjTradePath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button> 
												</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin clearJgkjTradeClr"  >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											</span>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">交广科技交易未明列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="12%">发送交广科技流水号</th>
											<th width="12%">交易编号</th>
											<th width="5%">交易类型</th>
											<th width="10%">交广科技卡号</th>
											<th width="5%">金额</th>
											<th width="5%">会计日期</th>
											<th width="5%">发送时间</th>
											<th width="12%">返回流水号</th>
											<th width="5%">返回时间</th>
											<th width="5%">返回码</th>
											<th width="15%">返回信息</th>
											<th width="8%">对账状态</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${clearJgkjTradeList}" var="result">
											<tr class="result">
												<td>${result.id }</td>
												<td>${result.transFlowId }</td>
												<td>
							                        <c:if test="${result.businessType=='PAYMENT' }">  									  
							                        	 支付								  
							                        </c:if>  
							  						<c:if test="${result.businessType =='RECHARGE'}">  									  
							                           	 充值  								  
							                        </c:if> 
							                        <c:if test="${result.businessType=='WITHDRAW_APPLY' }">  									  
							                          	 提现申请								  
							                        </c:if>  
                                              	</td> 
												<td>${result.cardNo }</td>
												<td>${result.transAmt }</td>
												<td>${result.workDate }</td>	
												<td>${result.sendTime }</td>											
												<td>${result.rtnSeq }</td>
												<td>${result.rtnTime }</td>	
												<td>${result.rtnCode }</td>
												<td>${result.rtnInfo }</td>
												<td>${result.balStatus}</td>
											</tr>
										</c:forEach>
										<c:if test="${empty clearJgkjTradeList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty clearJgkjTradeList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div><!-- /.page-content -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
</body>
</html>