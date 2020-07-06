<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller.MerchantTradePath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> 
<script src='<c:url value="/static/js/layer/layer.js"/>'></script>
<head>
	<meta charset="utf-8" />
	<title>交易汇总查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
function loadQueryBean(){
	
	$("#tradeType").val($("#tradeTypeQuery").val());
}

	$(function(){
		
		$('.clearTradeBillMain').click(function(){
			/* $('.search-table #createTimeB').val('');
			$('.search-table #createTimeE').val(''); */
			$('.search-table #merchantName').val('');
			$('.search-table #tradeType').val('');
			$('.search-table #merchantCode').val('');
		});
		
		$(".exportBut").click(function(){
			var createTimeB = $("#createTimeB").val();
			var createTimeE = $("#createTimeE").val();
			var merchantName = $("#merchantName").val();
			var tradeType = $("#tradeType").val();

			var arr1=createTimeE.split('-');
			var arr2=createTimeB.split('-');
			var d1=new Date(arr1[0],arr1[1],arr1[2]);
			var d2=new Date(arr2[0],arr2[1],arr2[2]);
			var diff =(d1.getTime()-d2.getTime())/(1000*3600*24);			 
			if(diff>90){
				
				$.gyzbadmin.alertFailure("您查询时间间隔超过三个月");
				return;
			
			}else{
			var src ="<%= request.getContextPath()+ MerchantTradePath.BASE+MerchantTradePath.EXPORTLIST%>?createTimeB="+createTimeB+"&createTimeE="+createTimeE+"&merchantName="+merchantName+"&tradeType="+tradeType;
			$(".exportBut").attr("href",src);
			}
		}); 
		
	});
	

</script>
<body onload="loadQueryBean();">
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
							<form id="tradeForm" action='<c:url value="<%=MerchantTradePath.BASE + MerchantTradePath.LIST %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" id="isFirst" name="isFirst" value="${isFirst}" />
								
								<input type="hidden" id="tradeTypeQuery" name="tradeTypeQuery" value="${queryBean.tradeType}" />
								
								<tr>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merchantName"  name="merchantName"  style="width:88%;" value="${queryBean.merchantName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merchantCode"  name="merchantCode"  style="width:88%;" value="${queryBean.merchantCode}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >交易类型</td>
									<td class="td-right">
										<span class="input-icon">
											<select id="tradeType" name="tradeType">
												<option value="consume">消费</option>
												<option value="refund">退款</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="18%">开始账期：</td>
									<td class="td-right" width="32%">
										     <input type="text" name="createTimeB"  id="createTimeB" readonly="readonly"  value="${queryBean.createTimeB }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left" width="18%">结束账期：</td>
									<td class="td-right">
										     <input type="text" name="createTimeE" id="createTimeE" readonly="readonly" value="${queryBean.createTimeE }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
								<td align="center" colspan="6" >
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MerchantTradePath.BASE + MerchantTradePath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm btn-margin">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearTradeBillMain" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=MerchantTradePath.BASE + MerchantTradePath.EXPORTLIST%>">
											<a class="btn btn-purple btn-sm exportBut" id="export">
												导出报表
											</a> 
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
						
						
							<div class="list-table-header">
								交易总笔数为${allCount},交易总金额${allSum }元,总结算金额${allSettle }。
							</div> 

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:20%;">开始账期</th>
											<th style="width:13%;">结束账期</th>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>交易渠道</th>
											<th>交易笔数</th>
											<th>交易金额</th>
											<th>交易类型</th>
											<th>商户结算金额</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${list}" var="tradeBillVO">
											<tr class="tradeBill">
												<td>${queryBean.createTimeB }</td>
												<td>${queryBean.createTimeE }</td>
												<td>${tradeBillVO.merchantCode}</td>
												<td>${tradeBillVO.merchantName}</td>
												<td>${tradeBillVO.channel }</td>
												<td>${tradeBillVO.countTrade }</td>
												<td>${tradeBillVO.sumTrade}</td>
												<td>
													<c:choose>
														<c:when test="${queryBean.tradeType=='consume'}">
															消费
														</c:when>
														<c:when test="${queryBean.tradeType=='refund'}">
															退款
														</c:when>
													</c:choose>
												</td>
												<td>${tradeBillVO.settleAmt}</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty list}">
											<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty list}">
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
			
			
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
</body>
</html>


