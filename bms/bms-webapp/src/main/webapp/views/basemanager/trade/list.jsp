<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.trade.TradeBillMainPath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<head>  
	<meta charset="utf-8" />
	<title>消费查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		var tradeBills = ${tradeBillList};
		var tradeBillList = $("tr.tradeBill");
		$.each(tradeBills,function(i,value){
			$.data(tradeBillList[i],"tradeBill",value);
		}); 
		
		$('.clearTradeForm').click(function(){
			$('.search-table #orderId').val('');
			$('.search-table #merOrderId').val('');
			$('.search-table #payeeCustId').val('');
			$('.search-table #payeeCustName').val('');
			$('.search-table #channelId').val('');
			$('.search-table #orderState').val('');
			$('.search-table #beginTime').val('');
			$('.search-table #endCreateTime').val('');
			$('.search-table #compBeginTime').val('');
			$('.search-table #compEndCreateTime').val('');
			$('.search-table #mobile').val('');
			$('.search-table #transId').val('');
			$('.search-table #orderType').val('');
			$('.search-table #service').val('');
		})
		//按条件查询
		$('.buttonSearch').click(function(){
			var startDate =$("#beginTime").val();
			var endDate= $("#endCreateTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("订单结束日期不能小于开始日期");
				return false;
			}
			
			var compBeginTime =$("#compBeginTime").val();
			var compEndCreateTime= $("#compEndCreateTime").val();
			if("" != compBeginTime && "" != compEndCreateTime && compBeginTime > compEndCreateTime) 
			{
				$.gyzbadmin.alertFailure("账期结束日期不能小于开始日期");
				return false;
			}
			
			var form = $('#tradeForm');
			form.submit();
		});
		
		/* 预览  */
		$(".view").click(function(){
			var trade = $.data($(this).parent().parent()[0],"tradeBill");
			$('#viewModal').on('show.bs.modal', function () {
				// 赋值
				$("#viewModal #orderId").val(trade.orderId);
				$("#viewModal #channelId").val(trade.channelId);
				$("#viewModal #merOrderId").val(trade.merOrderId);
				$("#viewModal #payeeCustId").val(trade.payeeCustId);
				$("#viewModal #payeeCustName").val(trade.payeeCustName);
				$("#viewModal #payerCustName").val(trade.payerCustName);
				$("#viewModal #orderState").val(trade.orderState);
				$("#viewModal #sumAmt").val(trade.sumAmt);
				$("#viewModal #settleAmt").val(trade.settleAmt);
				$("#viewModal #mobile").val(trade.mobile);
				$("#viewModal #orderName").val(trade.orderName);
				$("#viewModal #orderDesc").val(trade.orderDesc);
				var orderCreateTime = new Date(trade.orderCreateTime)
				$("#viewModal #orderCreateTime").val(format1(orderCreateTime,"yyyy-MM-dd HH:mm:ss"));
				var orderCoreReturnTime = new Date(trade.orderCoreReturnTime)
				$("#viewModal #orderCoreReturnTime").val(format1(orderCoreReturnTime,"yyyy-MM-dd HH:mm:ss"));
				$("#viewModal #orderState").attr("disabled",true);
			});
			$('#viewModal').on('hide.bs.modal', function () {
				// 清除
				$("#viewModal #orderId").val('');
				$("#viewModal #channelId").val('');
				$("#viewModal #merOrderId").val('');
				$("#viewModal #payeeCustId").val('');
				$("#viewModal #payeeCustName").val('');
				$("#viewModal #payerCustName").val('');
				$("#viewModal #mobile").val('');
				$("#viewModal #orderName").val('');
				$("#viewModal #orderDesc").val('');
				$("#viewModal #orderState").val('');
				$("#viewModal #sumAmt").val('');
				$("#viewModal #settleAmt").val('');
				$("#viewModal #orderCreateTime").val('');
				$("#viewModal #orderCoreReturnTime").val('');
			});
		});

		var isShow = $("#isShow").val();
		$(".moreButton").click(function(){
			if(isShow=="NO"){
				isShow="YES";
			}else if(isShow=="YES"){
				isShow="NO";
			};
			$("#isShow").val(isShow);
			$(".moreSelect1").toggle(0,function(){
				
			}); 
			$(".moreSelect2").toggle();
			$(".moreSelect3").toggle();
		});
		
		$(".exportBut").click(function(){
			var orderId = $('.search-table #orderId').val();
			var merOrderId = $('.search-table #merOrderId').val();
			var payeeCustId = $('.search-table #payeeCustId').val();
			var payeeCustName = $('.search-table #payeeCustName').val();
			var channelId = $('.search-table #channelId').val();
			var orderState = $('.search-table #orderState').val();
			var beginTime = $('.search-table #beginTime').val();
			var endCreateTime = $('.search-table #endCreateTime').val();
			var compBeginTime =$('.search-table #compBeginTime').val();
			var compEndCreateTime= $('.search-table #compEndCreateTime').val();
			var mobile = $('.search-table #mobile').val();
			var orderType = $('.search-table #orderType').val();
			var transId =  $('.search-table #transId').val();
			var service =  $('.search-table #service').val();
			var src ="<%= request.getContextPath()+ TradeBillMainPath.BASE+TradeBillMainPath.TRADEEXPORT%>?orderId="+
			         orderId+
					"&merOrderId="+
					merOrderId+
					"&payeeCustId="+
					payeeCustId+
					"&payeeCustName="+
					payeeCustName+
					"&channelId="+
					channelId+
					"&orderState="+
					orderState+
					"&beginTime="+
					beginTime+
					"&endCreateTime="+
					endCreateTime+
					"&mobile="+
					mobile+
					"&compBeginTime="+
					compBeginTime+
					"&compEndCreateTime="+
					compEndCreateTime+
					"&orderType="+
					orderType+
					"&transId="+
					transId+
					"&service="
					+service;
			$(".exportBut").attr("href",src);
		});
	});
	
	function showLoad(){
		$("#orderState").val($("#orderState2").val());
		$("#orderType").val($("#orderTypeload").val());
		$("#service").val($("#serviceload").val());
		
		var isShow = $("#isShow").val();
		if(isShow =="YES"){
			$(".moreSelect1").show();
			$(".moreSelect2").show();
			$(".moreSelect3").show();
		}else if(isShow == "NO"){
			$(".moreSelect1").hide();
			$(".moreSelect2").hide();
			$(".moreSelect3").hide();
		}
	}
</script>
<body onload="showLoad()">
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
				<div class="page-content" >
					<div class="row">
						<div class="col-xs-12">
						  <input type="hidden"  name="orderState2"  id="orderState2" value="${queryBean.orderState}"/>
						  <input type="hidden" id="orderTypeload" name="orderTypeload" value="${queryBean.orderType }">
						  <input type="hidden" id="serviceload" name="serviceload" value="${queryBean.service }">
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=TradeBillMainPath.BASE + TradeBillMainPath.LIST %>"/>' method="post">
							<input type="hidden" id="isShow" name="isShow" value="${isShow}"/>
							<input type="hidden" id="isFirst" name="isFirst" value="${isFirst }">
							<table class="search-table">
								<tr>
									<td class="td-left">七分钱交易流水号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="orderId" name="orderId"  value="${queryBean.orderId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >交广科技订单号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" id="channelId" name="channelId"  value="${queryBean.channelId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">订单开始日期</td>
									<td class="td-right">
										 <input type="text" name="beginTime"  id="beginTime" readonly="readonly"  value="${queryBean.beginTime}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="endCreateTime" id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr class="moreSelect1" style="display:none;">
									<td class="td-left">商户订单号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="merOrderId" name="merOrderId" value="${queryBean.merOrderId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="payeeCustId" id="payeeCustId" value="${queryBean.payeeCustId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="payeeCustName" id="payeeCustName" value="${queryBean.payeeCustName }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr class="moreSelect2" style="display:none;"> 
								    <td class="td-left">手机号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile" value="${queryBean.mobile }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >订单状态</td>
									<td class="td-right" >
										<span class="input-icon">
											<select id="orderState" name="orderState">
												<option value="">- 请选择 -</option>
												<option value="00">成功</option>
												<option value="01">待提交</option>
												<option value="02">提交核心</option>
												<option value="03">核心处理中</option>
												<option value="04">失败</option>
												<option value="99">取消</option>
												<option value="90">未明</option>
											</select>
										</span>	
									</td>
									<td class="td-left">账期</td>
									<td class="td-right">
										 <input type="text" name="compBeginTime"  id="compBeginTime" readonly="readonly"  value="${queryBean.compBeginTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="compEndCreateTime" id="compEndCreateTime" readonly="readonly" value="${queryBean.compEndCreateTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								
								</tr>
								<tr class="moreSelect3" style="display:none;">
									<td class="td-left" >订单类型</td>
									<td class="td-right" >
										<span class="input-icon">
											<select id="orderType" name="orderType">
												<option value="">- 请选择 -</option>
												<option value="BALANCE_PAYMENT">余额支付</option>
												<option value="BANK_CARD_PAYMENT">银行卡支付</option>
												<option value="RED_PACKET_PAYMENT">红包支付</option>
											</select>
										</span>	
									</td>
									<td class="td-left" >银联订单号</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text"  name="transId" id="transId" value="${queryBean.transId }" style="width:100%;">
										</span>	
									</td>
									<td class="td-left" >产品</td>
									<td class="td-right" >
										<span class="input-icon">
											<select id="service" name="service">
												<option value="">- 请选择 -</option>
												<option value="onecode.coll.pay">一码收银</option>
												<option value="mobile.plugin.pay">手机支付插件收银台</option>
												<option value="h5.gateway.pay">手机端Html5收银台</option>
												<option value="pc.gateway.pay">PC端收银台</option>
											</select>
										</span>	
									</td>
								</tr>
								<tr>
								
									<td colspan="6" align="center">
									<span class="input-group-btn"  style="display:inline;">
												<gyzbadmin:function url="<%=TradeBillMainPath.BASE + TradeBillMainPath.LIST %>">
													<button type="button" class="btn btn-purple btn-sm buttonSearch">
															查询
															<i class="icon-search icon-on-right bigger-110"></i>
													</button> 
												</gyzbadmin:function>
													<button class="btn btn-purple btn-sm btn-margin clearTradeForm" >
															清空
															<i class=" icon-move icon-on-right bigger-110"></i>
													</button>
											</span>
											<gyzbadmin:function url="<%=TradeBillMainPath.BASE + TradeBillMainPath.TRADEEXPORT%>">
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span>
											</gyzbadmin:function>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm moreButton">
													更多筛选条件
												</a> 
											</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								消费信息
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="8%">七分钱交易流水号</th>
											<th width="8%">商户订单号</th>
											<th width="8%">银联订单号</th>
											<th width="8%">交广科技订单号</th>
											<th width="5%">手机号码</th>
											<th width="5%">客户名称</th>
											<th width="8%">商户编号</th>
											<th width="5%">商户名称</th>
											<th width="5%">账期</th>
											<th width="8%">订单开始时间</th>
											<th width="8%">订单结束时间</th>
											<th width="5%">订单状态</th>
											<th width="5%">订单类型</th>
											<th width="5%">订单金额</th>
											<th width="5%">商户结算金额</th>
											<th>产品</th>
											<th width="4%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tradeBillList}" var="tradeBillVO">
											<tr class="tradeBill">
												<td>${tradeBillVO.orderId}</td>
												<td>${tradeBillVO.merOrderId}</td>
												<td>${tradeBillVO.transId}</td>
												<td>${tradeBillVO.channelId}</td>
												<td>${tradeBillVO.mobile}</td>
												<td>${tradeBillVO.payerCustName}</td>
												<td>${tradeBillVO.payeeCustId}</td>
												<td>${tradeBillVO.payeeCustName}</td>
												<td>${tradeBillVO.workDate}</td>
												<td><fmt:formatDate value="${tradeBillVO.orderCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${tradeBillVO.orderCoreReturnTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>
													<c:if test="${tradeBillVO.orderState=='00'}">成功</c:if>	
													<c:if test="${tradeBillVO.orderState=='01'}">待提交</c:if>
													<c:if test="${tradeBillVO.orderState=='02'}">提交核心</c:if>
													<c:if test="${tradeBillVO.orderState=='03'}">核心处理中</c:if>
													<c:if test="${tradeBillVO.orderState=='04'}">失败</c:if>
													<c:if test="${tradeBillVO.orderState=='99'}">取消</c:if>
													<c:if test="${tradeBillVO.orderState=='90'}">未明</c:if>
												</td>
												<td>
													<c:if test="${tradeBillVO.orderType=='BALANCE_PAYMENT'}">余额支付</c:if>
													<c:if test="${tradeBillVO.orderType=='BANK_CARD_PAYMENT'}">银行卡支付</c:if>
													<c:if test="${tradeBillVO.orderType=='RED_PACKET_PAYMENT'}">红包支付</c:if>
												</td>
												<td>${tradeBillVO.sumAmt}</td>
												<td>${tradeBillVO.settleAmt}</td>
												<td>
													<c:if test="${tradeBillVO.service == 'onecode.coll.pay'}">扫码支付</c:if>
													<c:if test="${tradeBillVO.service == 'mobile.plugin.pay'}">手机支付插件收银台</c:if>
													<c:if test="${tradeBillVO.service == 'h5.gateway.pay'}">手机端Html5收银台</c:if>
													<c:if test="${tradeBillVO.service == 'pc.gateway.pay'}">PC端收银台</c:if>
													<c:if test="${tradeBillVO.service == 'h5_t.gateway.pay'}">原生H5支付</c:if>
												</td>
												<td>
													<a href="#" class="tooltip-success view" data-rel="tooltip" title="view" data-toggle='modal' data-target="#viewModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty tradeBillList}">
											<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty tradeBillList}">
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
	<!-- 预览 -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:60%">
		      <div class="modal-content" >
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">订单预览</h4>
		         </div>
		         <div class="modal-body">
		           <table class="modal-input-table" border="1">
		           		<tr >
							<td class="td-left" width="18%">订单名称:</td>
							<td class="td-right" width="32%">
								<input type="text" id="orderName" readonly="readonly"  style=" width:100%"/>
							</td>
							<td class="td-left" width="18%">订单描述:</td>
							<td class="td-right" width="32%">
								<input type="text" id="orderDesc" readonly="readonly"  style=" width:100%"/>
							</td>
						</tr>
		            	<tr >
							<td class="td-left" width="18%">七分钱交易流水号:</td>
							<td class="td-right" width="32%">
								<input type="text" id="orderId" readonly="readonly"  style=" width:100%"/>
							</td>
							<td class="td-left" width="18%">交广科技订单号:</td>
							<td class="td-right" width="32%">
								<input type="text" id="channelId" readonly="readonly"  style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left">商户订单号:</td>
							<td class="td-right">
								<input type="text" id="merOrderId" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left">商户编号:</td>
							<td class="td-right">
								<input type="text" id="payeeCustId" readonly="readonly" style=" width:100%"/>
							</td>
						</tr>
						<tr>
						   <td class="td-left">商户名称：</td>
							<td class="td-right">
								<input type="text" id="payeeCustName" readonly="readonly" style=" width:100%"/>
							</td>
							 <td class="td-left">客户手机号：</td>
							<td class="td-right">
								<input type="text" id="mobile" readonly="readonly" style=" width:100%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >客户名称：</td>
							<td class="td-right" >
								<input type="text" id="payerCustName" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left">订单状态:</td>
							<td class="td-right" >
								<sevenpay:selectOrderStatus id="orderState"  name="orderState" style="width:100%" />
							</td>
						</tr>
						<tr>
							<td class="td-left">订单开始日期:</td>
							<td class="td-right" >
								<input type="text" id="orderCreateTime" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left">订单结束日期:</td>
							<td class="td-right" >
								<input type="text" id="orderCoreReturnTime" readonly="readonly" style=" width:100%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">订单金额:</td>
							<td class="td-right" >
								<input type="text" id="sumAmt" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left" width="20%">商户结算金额:</td>
							<td class="td-right" >
								<input type="text" id="settleAmt" readonly="readonly" style="width:100%"/>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>