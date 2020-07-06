<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.rechargeRevoke.RechargeRevokePath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>充值撤销管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
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
				<!-- 账户邮箱 -->
				<div >
				<form id="transRevokeForm" action='<c:url value="<%=RechargeRevokePath.BASE+RechargeRevokePath.LIST%>"/>' method="post">
					<table class="search-table" border="1">
						<tr>
							<td class="td-left">撤销流水号：</td>
							<td class="td-right"> 
								<span class="input-icon">
									<input type="text" id="orderId" name="orderId" value="${queryBean.orderId}" maxlength="55" >
									<i class="icon-leaf blue"></i>
								</span>
							</td>
							<td class="td-left">原交易订单号：</td>
							<td class="td-right"> 
								<span class="input-icon">
									<input type="text" id="originOrderId" name="originOrderId" value="${queryBean.originOrderId}" maxlength="55" >
									<i class="icon-leaf blue"></i>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left">撤销申请时间：</td>
							<td class="td-right"> 
								<input type="text" name=revokeBeginTime  id="revokeBeginTime" value="${queryBean.revokeBeginTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
								<input type="text"  name="revokeEndTime" id="revokeEndTime"  value="${queryBean.revokeEndTime }" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
							</td>
							<td class="td-left">客户账号：</td>
							<td class="td-right"> 
								<span class="input-icon">
									<input type="text" id="rechargeCustId" name="rechargeCustId" value="${queryBean.rechargeCustId}" maxlength="55" >
									<i class="icon-leaf blue"></i>
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<gyzbadmin:function url="<%=RechargeRevokePath.BASE+RechargeRevokePath.LIST%>">
									<button type="submit" class="btn btn-purple btn-sm searchRechargeRevoke">
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button> 
									<button  class="btn btn-purple btn-sm btn-margin clearRechargeRevoke" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>
								</gyzbadmin:function>
								<gyzbadmin:function url="<%=RechargeRevokePath.BASE+RechargeRevokePath.RECHARGE_REVOKE_APPLY%>">
									<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addTransRevokeModal">
										新增申请
										<i class="icon-plus-sign icon-on-right bigger-110"></i>
									</button>
								</gyzbadmin:function>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<!-- 持卡人信息-->
				<div >
					<div class="list-table-header">订单列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th style="width:8%">订单号</th>
								<th style="width:8%">原交易订单号</th>
								<th style="width:8%">原交易时间</th>
								<th style="width:5%">充值金额</th>
								<th style="width:6%">客户账号</th>
								<th style="width:8%">申请人</th>
								<th style="width:8%">申请时间</th>
								<th style="width:8%">状态</th>
								<th style="width:8%">撤销描述信息</th>
								<th style="width:8%">审核人</th>
								<th style="width:8%">审核状态</th>
								<th style="width:8%">审核时间</th>
								<th style="width:5%">备注</th>
								<th style="width:3%">操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${rechargeRevokeList}" var="rechargeRevoke">
						<tr class="rechargeRevoke">
							<td>${rechargeRevoke.orderId}</td>
							<td>${rechargeRevoke.originOrderId}</td>
							<td><fmt:formatDate value="${rechargeRevoke.originRechargeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${rechargeRevoke.revokeAmt}</td>
							<td>${rechargeRevoke.rechargeCustId}</td>
							<td>${rechargeRevoke.createId}</td>
							<td>${rechargeRevoke.createTime}</td>
							<td>
								<c:if test="${rechargeRevoke.orderStatus=='00'}">撤销成功</c:if>
								<c:if test="${rechargeRevoke.orderStatus=='01'}">待处理</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='02'}">核心处理中</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='03'}">核心处理失败</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='04'}">核心处理成功</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='05'}">银联处理中</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='06'}">银联处理失败</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='07'}">银联处理成功</c:if> 
								<c:if test="${rechargeRevoke.orderStatus=='99'}">取消</c:if>  
							</td>
							<td>${rechargeRevoke.revokeMemo}</td>
							<td>${rechargeRevoke.auditId}</td>
							<td>
								<c:if test="${rechargeRevoke.auditState=='01'}">待审核</c:if>
								<c:if test="${rechargeRevoke.auditState=='02'}">审核通过</c:if> 
								<c:if test="${rechargeRevoke.auditState=='04'}">审核不通过</c:if>
							</td>
							<td>${rechargeRevoke.auditTime}</td>
							<td>${rechargeRevoke.coreReturnMsg}</td>
							<td>
							   <gyzbadmin:function url="<%=RechargeRevokePath.BASE+RechargeRevokePath.RECHARGE_REVOKE_AUDIT%>">
									 <c:if test="${rechargeRevoke.auditState=='01'}">
									    <a href="#" class="tooltip-error rechargeRevokeAuditLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#rechargeRevokeAuditModal">
										 <button type="button"  class="btn btn-primary btn-xs" >审核</button> 								  
									  </a>  
									 </c:if>
								</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty rechargeRevokeList}">
							<tr><td colspan="14" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty rechargeRevokeList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
		<div class="modal fade" id="addTransRevokeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:36%">
		      <div class="modal-content" >
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">交易撤销新增申请</h4>
		         </div>
		         <div class="modal-body">
		           <table class="modal-input-table">
		           		<tr >
							<td class="td-left" width="20%">原交易订单号:</td>
							<td class="td-right">
								<input type="text" id="originOrderId" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left">撤销原因:</td>
							<td class="td-right">
								<textarea rows="2" id="revokeMemo" style=" width:100%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addRechargeRevokeBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="rechargeRevokeAuditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:36%">
		      <div class="modal-content" >
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">充值撤销审核</h4>
		         </div>
		         <div class="modal-body">
		           <table class="modal-input-table">
		           		<tr >
							<td class="td-left" width="20%">订单号:</td>
							<td class="td-right">
								<input type="text" id="orderId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">原交易订单号:</td>
							<td class="td-right">
								<input type="text" id="originOrderId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">充值时间:</td>
							<td class="td-right">
								<input type="text" id="originRechargeTime" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">撤销方账号:</td>
							<td class="td-right">
								<input type="text" id="rechargeCustId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">撤销金额:</td>
							<td class="td-right">
								<input type="text" id="revokeAmt" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left">撤销原因:</td>
							<td class="td-right">
								<textarea rows="2" id="revokeMemo" disabled="disabled"  style=" width:100%"></textarea>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">创建人:</td>
							<td class="td-right">
								<input type="text" id="createId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">创建时间:</td>
							<td class="td-right">
								<input type="text" id="createTime" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">审核状态:</td>
							<td class="td-right">
								<select id ="auditState" name ="auditState" style="width:50%">
								<option value="02">审核通过</option>
								<option value="04">审核不通过</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary rechargeRevokeAuditBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
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
<script type="text/javascript">
	$(function(){
		var rechargeRevokeListJson = ${rechargeRevokeList};
		
		var rechargeRevokeListTrList = $('tr.rechargeRevoke');
		
		$.each(rechargeRevokeListJson, function(idx, obj){
			$.data(rechargeRevokeListTrList[idx], 'rechargeRevoke', obj);
		});
		
		$(".searchRechargeRevoke").click(function(){
			
			var startDate =$('.search-table #revokdBeginTime').val();
			var endDate=$('.search-table #revokeEndTime').val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("申请结束日期不能小于开始日期");
				return false;
			}
			var form = $('#transRevokeForm');
			form.submit();
		});
		
		$(".clearRechargeRevoke").click(function(){
			$('.search-table #orderId').val('');
			$('.search-table #originOrderId').val('');
			$('.search-table #rechargeCustId').val('');
			$('.search-table #revokeBeginTime').val('');
			$('.search-table #revokeEndTime').val('');
		});
		
		$('.addRechargeRevokeBtn').click(function (){
			  var originOrderId = $("#addTransRevokeModal #originOrderId").val();
			  if(kong.test(originOrderId)) {
					$.gyzbadmin.alertFailure('原交易订单号不可为空');
					return;
				}
			  
			  var revokeMemo = $("#addTransRevokeModal #revokeMemo").val();
			  if(kong.test(revokeMemo)) {
				  $.gyzbadmin.alertFailure('撤销原因不可为空');
					return;
				}
			  
			  $.blockUI();
				$.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=RechargeRevokePath.BASE + RechargeRevokePath.RECHARGE_REVOKE_APPLY %>',
					data:
					{
						"originOrderId" : originOrderId,
						"revokeMemo" 	: revokeMemo
					},
					success:function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addRechargeRevokeBtn").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							$.gyzbadmin.alertFailure(data.message);
						}
					}
				});
			});
		
		/* 加载  */
		$(".rechargeRevokeAuditLink").click(function(){
			var rechargeRevoke = $.data($(this).parent().parent()[0],"rechargeRevoke");
			$('#rechargeRevokeAuditModal').on('show.bs.modal', function () {
				// 赋值
				$("#rechargeRevokeAuditModal #orderId").val(rechargeRevoke.orderId);
				$("#rechargeRevokeAuditModal #originOrderId").val(rechargeRevoke.originOrderId);
				var originRechargeTime = new Date(rechargeRevoke.originRechargeTime);
				$("#rechargeRevokeAuditModal #originRechargeTime").val(format1(originRechargeTime,"yyyy-MM-dd HH:mm:ss"));
				$("#rechargeRevokeAuditModal #rechargeCustId").val(rechargeRevoke.rechargeCustId);
				$("#rechargeRevokeAuditModal #revokeAmt").val(rechargeRevoke.revokeAmt);
				$("#rechargeRevokeAuditModal #revokeMemo").val(rechargeRevoke.revokeMemo);
				$("#rechargeRevokeAuditModal #createId").val(rechargeRevoke.createId);
				$("#rechargeRevokeAuditModal #createTime").val(rechargeRevoke.createTime);
				
			});
			$('#rechargeRevokeAuditModal').on('hide.bs.modal', function () {
				// 清除
				$("#rechargeRevokeAuditModal #orderId").val('');
				$("#rechargeRevokeAuditModal #originOrderId").val('');
				$("#rechargeRevokeAuditModal #originRechargeTime").val('');
				$("#rechargeRevokeAuditModal #rechargeCustId").val('');
				$("#rechargeRevokeAuditModal #revokeAmt").val('');
				$("#rechargeRevokeAuditModal #revokeMemo").val('');
				$("#rechargeRevokeAuditModal #createId").val('');
				$("#rechargeRevokeAuditModal #createTime").val('');
				
			});
		});
		
		/**审核 **/
		$('.rechargeRevokeAuditBtn').click(function (){
			  var orderId = $("#rechargeRevokeAuditModal #orderId").val();
			  var originOrderId = $("#rechargeRevokeAuditModal #originOrderId").val();
			  var revokeMemo = $("#rechargeRevokeAuditModal #revokeMemo").val();
			  var auditState = $("#rechargeRevokeAuditModal #auditState").val();
			  
			  $.blockUI();
				$.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=RechargeRevokePath.BASE + RechargeRevokePath.RECHARGE_REVOKE_AUDIT%>',
					data:
					{
						'originOrderId'  : originOrderId,
						'revokeMemo' 	 : revokeMemo,
						'orderId'		 : orderId,
						'auditState'	 : auditState
					},
					success:function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("审核成功",function(){
								$("#rechargeRevokeAuditModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							$.gyzbadmin.alertFailure(data.message);
						}
					}
				});
			});
	});
</script>
</body>
</html>