<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.transfer.TransferPath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<head>  
	<meta charset="utf-8" />
	<title>转账查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
function showLoad(){
	var orderStateTemp = $('#orderStateTemp').val();
	var orderNameTemp = $('#orderNameTemp').val();
	$('.search-table #orderState').val(orderStateTemp);
	$('.search-table #orderName').val(orderNameTemp);
}
	$(function(){
		var transfers = ${transferList};
		var transferTdList = $("tr.transfer");
		$.each(transfers,function(i,value){
			$.data(transferTdList[i],"transfer",value);
		}); 
		
		$('.clearTradeForm').click(function(){
				$(':input','#tradeForm')  
				 .not(':button, :submit, :reset, :hidden')  
				 .val('')  
				 .removeAttr('checked')  
				 .removeAttr('selected'); 
		});
		//按条件查询
		$('.buttonSearch').click(function(){
			var startDate =$("#beginTime").val();
			var endDate= $("#endTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("订单结束日期不能小于开始日期");
				return false;
			}
			
			var compBeginTime =$("#startWorkDate").val();
			var compEndCreateTime= $("#endWorkDate").val();
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
			var transfer = $.data($(this).parent().parent()[0],"transfer");
			$('#viewModal').on('show.bs.modal', function () {
				// 赋值
				$("#viewModal #orderId").val(transfer.orderId);
				$("#viewModal #channelId").val(transfer.channelId);
				$("#viewModal #payeeCustId").val(transfer.payeeCustId);
				$("#viewModal #payeeCustName").val(transfer.payeeCustName);
				$("#viewModal #payerCustId").val(transfer.payerCustId);
				$("#viewModal #payerCustName").val(transfer.payerCustName);
				$("#viewModal #orderState").val(transfer.orderState);
				$("#viewModal #sumAmt").val(transfer.sumAmt);
				$("#viewModal #orderName").val(transfer.orderName);
				$("#viewModal #orderDesc").val(transfer.orderDesc);
				var orderCreateTime = new Date(transfer.orderCreateTime);
				$("#viewModal #orderCreateTime").val(format1(orderCreateTime,"yyyy-MM-dd HH:mm:ss"));
				$("#viewModal #workDate").val(transfer.workDate);
				
				$("#viewModal #orderState").attr("disabled",true);
			});
			$('#viewModal').on('hide.bs.modal', function () {
				// 清除
				$("#viewModal #orderId").val('');
				$("#viewModal #channelId").val('');
				$("#viewModal #payeeCustId").val('');
				$("#viewModal #payeeCustName").val('');
				$("#viewModal #payerCustId").val('');
				$("#viewModal #payerCustName").val('');
				$("#viewModal #orderName").val('');
				$("#viewModal #orderDesc").val('');
				$("#viewModal #orderState").val('');
				$("#viewModal #sumAmt").val('');
				$("#viewModal #orderCreateTime").val('');
				$("#viewModal #workDate").val('');
			});
		});
		
		$(".exportBut").click(function(){
			var orderId = $('.search-table #orderId').val();
			var channelId = $('.search-table #channelId').val();
			var payerCustId = $('.search-table #payerCustId').val();
			var payerCustName = $('.search-table #payerCustName').val();
			var payeeCustId = $('.search-table #payeeCustId').val();
			var payeeCustName = $('.search-table #payeeCustName').val();
			var orderState = $('.search-table #orderState').val();
			var beginTime = $('.search-table #beginTime').val();
			var endTime = $('.search-table #endTime').val();
			var startWorkDate =$('.search-table #startWorkDate').val();
			var endWorkDate= $('.search-table #endWorkDate').val();
			var orderName = $('.search-table #orderName').val();
			var src ="<%= request.getContextPath()+ TransferPath.BASE+TransferPath.TRANSFEREXPORT%>?orderId="+
			         orderId+
			         "&channelId="+
					 channelId+
					 "&payerCustId="+
					 payerCustId+
					 "&payerCustName="+
					 payerCustName+
					 "&payeeCustId="+
					 payeeCustId+
					 "&payeeCustName="+
					 payeeCustName+
					 "&orderState="+
					 orderState+
					 "&beginTime="+
					 beginTime+
					 "&endTime="+
					 endTime+
					 "&startWorkDate="+
					 startWorkDate+
					 "&endWorkDate="+
					  endWorkDate+
					  "&orderName="+
					  orderName;
			$(".exportBut").attr("href",src);
		});
	});
	

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
						<!-- 查询条件 -->
							<form id="tradeForm" action='<c:url value="<%=TransferPath.BASE + TransferPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">七分钱订单号</td>
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
										 <input type="text" name="endTime" id="endTime" readonly="readonly" value="${queryBean.endTime}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td class="td-left">付方客户手机号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="payerCustId" id="payerCustId" value="${queryBean.payerCustId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">付方客户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="payerCustName" name="payerCustName" value="${queryBean.payerCustName }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">收方客户手机号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="payeeCustId" id="payeeCustId" value="${queryBean.payeeCustId }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr> 
									<td class="td-left">收方客户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="payeeCustName" id="payeeCustName" value="${queryBean.payeeCustName }" style="width:100%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
							
									<td class="td-left" >订单状态</td>
									<td class="td-right" >
										<span class="input-icon">
										<input type="hidden" name="orderStateTemp" id="orderStateTemp" value ="${queryBean.orderState}">
											<select id="orderState" name="orderState">
												<option value="">- 请选择 -</option>
												<option value="00">成功</option>
												<option value="01">待提交</option>
												<option value="02">提交核心</option>
												<option value="03">提交处理中</option>
												<option value="04">失败</option>
												<option value="99">取消</option>
												<option value="90">未明</option>
											</select>
										</span>	
									</td>
									<td class="td-left">账期</td>
									<td class="td-right">
										 <input type="text" name="startWorkDate"  id="startWorkDate" readonly="readonly"  value="${queryBean.startWorkDate }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="endWorkDate" id="endWorkDate" readonly="readonly" value="${queryBean.endWorkDate }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td class="td-left" >订单类型</td>
									<td class="td-right" colspan="5" >
										<span class="input-icon">
										<input type="hidden" name="orderNameTemp" id="orderNameTemp" value ="${queryBean.orderName}">
											<select id="orderName" name="orderName">
												<option value="">- 请选择 -</option>
												<option value="转帐到七分钱">转帐到七分钱</option>
												<option value="红包入账">红包入账</option>
											</select>
										</span>	
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
									<span class="input-group-btn"  style="display:inline;">
												<gyzbadmin:function url="<%=TransferPath.BASE + TransferPath.LIST %>">
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
											<gyzbadmin:function url="<%=TransferPath.BASE + TransferPath.TRANSFEREXPORT%>">
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span>
											</gyzbadmin:function>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								转账列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">七分钱订单号</th>
											<th width="10%">交广科技订单号</th>
											<th width="10%">订单名称</th>
											<th width="5%">订单描述</th>
											<th width="8%">付方客户手机号</th>
											<th width="8%">付方客户名称</th>
											<th width="8%">收方客户手机号</th>
											<th width="8%">收方客户名称</th>
											<th width="10%">订单开始时间</th>
											<th width="5%">订单金额</th>
											<th width="6%">订单状态</th>
											<th width="5%">账期</th>
											<th width="5%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${transferList}" var="transfer">
											<tr class="transfer">
												<td>${transfer.orderId}</td>
												<td>${transfer.channelId}</td>
												<td>${transfer.orderName}</td>
												<td>${transfer.orderDesc}</td>
												<td>${transfer.payerCustId}</td>
												<td>${transfer.payerCustName}</td>
												<td>${transfer.payeeCustId}</td>
												<td>${transfer.payeeCustName}</td>
												<td>
													<fmt:formatDate value="${transfer.orderCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>${transfer.sumAmt}</td>
												<td>	
													<c:if test="${transfer.orderState=='00'}">成功</c:if>
													<c:if test="${transfer.orderState=='01'}">待提交</c:if>
													<c:if test="${transfer.orderState=='02'}">提交核心</c:if>
													<c:if test="${transfer.orderState=='03'}">核心处理中</c:if>
													<c:if test="${transfer.orderState=='04'}">失败</c:if>
													<c:if test="${transfer.orderState=='99'}">取消</c:if>
													<c:if test="${transfer.orderState=='90'}">未明</c:if>
												</td>
												<td>${transfer.workDate}</td>
												<td>
													<a href="#" class="tooltip-success view" data-rel="tooltip" title="view" data-toggle='modal' data-target="#viewModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
													</a>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty transferList}">
											<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty transferList}">
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
							<td class="td-left">七分钱订单号:</td>
							<td class="td-right" >
								<input type="text" id="orderId" readonly="readonly"  style=" width:100%"/>
							</td>
							<td class="td-left">交广科技订单号:</td>
							<td class="td-right">
								<input type="text" id="channelId" readonly="readonly"  style=" width:100%"/>
							</td>
						</tr>
						<tr>
						   <td class="td-left">付方客户手机号:</td>
							<td class="td-right">
								<input type="text" id="payerCustId" readonly="readonly" style=" width:100%"/>
							</td>
							 <td class="td-left">付方客户名称:</td>
							<td class="td-right">
								<input type="text" id="payerCustName" readonly="readonly" style=" width:100%"/>
							</td>
						</tr>
						<tr>
						   <td class="td-left">收方客户手机号:</td>
							<td class="td-right">
								<input type="text" id="payeeCustId" readonly="readonly" style=" width:100%"/>
							</td>
							 <td class="td-left">收方客户名称:</td>
							<td class="td-right">
								<input type="text" id="payeeCustName" readonly="readonly" style=" width:100%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">订单开始日期:</td>
							<td class="td-right" >
								<input type="text" id="orderCreateTime" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left">订单状态:</td>
							<td class="td-right" >
								<sevenpay:selectOrderStatus id="orderState"  name="orderState" style="width:100%" />
							</td>
						</tr>
						<tr>
							<td class="td-left">订单金额:</td>
							<td class="td-right" >
								<input type="text" id="sumAmt" readonly="readonly" style=" width:100%"/>
							</td>
							<td class="td-left">账期:</td>
							<td class="td-right" >
								<input type="text" id="workDate" readonly="workDate" style="width:100%"/>
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