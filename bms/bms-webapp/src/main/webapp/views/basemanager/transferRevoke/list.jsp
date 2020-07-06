<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.transferRevoke.TransferRevokePath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>转账撤销管理</title>
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
				<form id="transferRevokeForm" action='<c:url value="<%=TransferRevokePath.BASE+TransferRevokePath.LIST%>"/>' method="post">
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
							<td class="td-left">原付方账号：</td>
							<td class="td-right"> 
								<span class="input-icon">
									<input type="text" id="payerCustId" name="payerCustId" value="${queryBean.payerCustId}" maxlength="55" >
									<i class="icon-leaf blue"></i>
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<gyzbadmin:function url="<%=TransferRevokePath.BASE+TransferRevokePath.LIST%>">
									<button type="submit" class="btn btn-purple btn-sm searchTransferRevoke">
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button> 
									<button  class="btn btn-purple btn-sm btn-margin clearTransferRevoke" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>
								</gyzbadmin:function>
								<gyzbadmin:function url="<%=TransferRevokePath.BASE+TransferRevokePath.TRANSFER_REVOKE_APPLY%>">
									<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addTransferRevokeModal">
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
								<th style="width:6%">订单号</th>
								<th style="width:6%">原交易订单号</th>
								<th style="width:4%">交易金额</th>
								<th style="width:7%">原交易时间</th>
								<th style="width:6%">原付方账号</th>
								<th style="width:5%">原付方姓名</th>
								<th style="width:6%">原收方账号</th>
								<th style="width:5%">原收方姓名</th>
								<th style="width:6%">申请人</th>
								<th style="width:7%">申请时间</th>
								<th style="width:5%">状态</th>
								<th style="width:5%">撤销描述信息</th>
								<th style="width:5%">审核人</th>
								<th style="width:5%">审核状态</th>
								<th style="width:5%">审核时间</th>
								<th style="width:5%">备注</th>
								<th style="width:3%">操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${transferRevokeList}" var="transferRevoke">
						<tr class="transferRevoke">
							<td>${transferRevoke.orderId}</td>
							<td>${transferRevoke.originOrderId}</td>
							<td>${transferRevoke.revokeAmt}</td>
							<td><fmt:formatDate value="${transferRevoke.originTransTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${transferRevoke.payerCustId}</td>
							<td>${transferRevoke.payerCustName}</td>
							<td>${transferRevoke.payeeCustId}</td>
							<td>${transferRevoke.payeeCustName}</td>
							<td>${transferRevoke.createId}</td>
							<td>${transferRevoke.createTime}</td>
							<td>
								<c:if test="${transferRevoke.orderStatus=='00'}">撤销成功</c:if>
								<c:if test="${transferRevoke.orderStatus=='01'}">待处理</c:if> 
								<c:if test="${transferRevoke.orderStatus=='02'}">核心处理中</c:if> 
								<c:if test="${transferRevoke.orderStatus=='03'}">核心处理失败</c:if> 
								<c:if test="${transferRevoke.orderStatus=='04'}">核心处理成功</c:if> 
								<c:if test="${transferRevoke.orderStatus=='05'}">银联处理中</c:if> 
								<c:if test="${transferRevoke.orderStatus=='06'}">银联处理失败</c:if> 
								<c:if test="${transferRevoke.orderStatus=='07'}">银联处理成功</c:if> 
								<c:if test="${transferRevoke.orderStatus=='99'}">取消</c:if>  
							</td>
							<td>${transferRevoke.revokeMemo}</td>
							<td>${transferRevoke.auditId}</td>
							<td>
								<c:if test="${transferRevoke.auditState=='01'}">待审核</c:if>
								<c:if test="${transferRevoke.auditState=='02'}">审核通过</c:if> 
								<c:if test="${transferRevoke.auditState=='04'}">审核不通过</c:if>
							</td>
							<td>${transferRevoke.auditTime}</td>
							<td>${transferRevoke.coreReturnMsg}</td>
							<td>
							   <gyzbadmin:function url="<%=TransferRevokePath.BASE+TransferRevokePath.TRANSFER_REVOKE_AUDIT%>">
									 <c:if test="${transferRevoke.auditState=='01'}">
									    <a href="#" class="tooltip-error transferRevokeAuditLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#transferRevokeAuditModal">
										 <button type="button"  class="btn btn-primary btn-xs" >审核</button> 								  
									  </a>  
									 </c:if>
								</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty transferRevokeList}">
							<tr><td colspan="19" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty transferRevokeList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
		<div class="modal fade" id="addTransferRevokeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:36%">
		      <div class="modal-content" >
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">转账撤销新增申请</h4>
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
		            <button type="button" class="btn btn-primary addTransferRevokeBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="transferRevokeAuditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:36%">
		      <div class="modal-content" >
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">转账撤销审核</h4>
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
							<td class="td-left" width="20%">原交易时间:</td>
							<td class="td-right">
								<input type="text" id="originTransTime" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">原付方账号:</td>
							<td class="td-right">
								<input type="text" id="payerCustId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">原收方账号:</td>
							<td class="td-right">
								<input type="text" id="payeeCustId" disabled="disabled" style=" width:100%"/>
							</td>
						</tr>
						<tr >
							<td class="td-left" width="20%">交易金额:</td>
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
		            <button type="button" class="btn btn-primary transferRevokeAuditBtn">提交</button>
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
		var transferRevokeListJson = ${transferRevokeList};
		
		var transferRevokeListTrList = $('tr.transferRevoke');
		
		$.each(transferRevokeListJson, function(idx, obj){
			$.data(transferRevokeListTrList[idx], 'transferRevoke', obj);
		});
		
		$(".searchTransferRevoke").click(function(){
			
			var startDate =$('.search-table #revokdBeginTime').val();
			var endDate=$('.search-table #revokeEndTime').val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("申请结束日期不能小于开始日期");
				return false;
			}
			var form = $('#transferRevokeForm');
			form.submit();
		});
		
		$(".clearTransferRevoke").click(function(){
			$('.search-table #orderId').val('');
			$('.search-table #originOrderId').val('');
			$('.search-table #payerCustId').val('');
			$('.search-table #revokeBeginTime').val('');
			$('.search-table #revokeEndTime').val('');
		});
		
		$('.addTransferRevokeBtn').click(function (){
			  var originOrderId = $("#addTransferRevokeModal #originOrderId").val();
			  if(kong.test(originOrderId)) {
					$.gyzbadmin.alertFailure('原交易订单号不可为空');
					return;
				}
			  
			  var revokeMemo = $("#addTransferRevokeModal #revokeMemo").val();
			  if(kong.test(revokeMemo)) {
				  $.gyzbadmin.alertFailure('撤销原因不可为空');
					return;
				}
			  
			  $.blockUI();
				$.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=TransferRevokePath.BASE + TransferRevokePath.TRANSFER_REVOKE_APPLY %>',
					data:
					{
						"originOrderId" : originOrderId,
						"revokeMemo" 	: revokeMemo
					},
					success:function(data){
						$.unblockUI();
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addTransferRevokeBtn").modal("hide");
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
		$(".transferRevokeAuditLink").click(function(){
			var transferRevoke = $.data($(this).parent().parent()[0],"transferRevoke");
			$('#transferRevokeAuditModal').on('show.bs.modal', function () {
				// 赋值
				$("#transferRevokeAuditModal #orderId").val(transferRevoke.orderId);
				$("#transferRevokeAuditModal #originOrderId").val(transferRevoke.originOrderId);
				var originTransTime = new Date(transferRevoke.originTransTime);
				$("#transferRevokeAuditModal #originTransTime").val(format1(originTransTime,"yyyy-MM-dd HH:mm:ss"));
				$("#transferRevokeAuditModal #payerCustId").val(transferRevoke.payerCustId);
				$("#transferRevokeAuditModal #payeeCustId").val(transferRevoke.payeeCustId);
				$("#transferRevokeAuditModal #revokeAmt").val(transferRevoke.revokeAmt);
				$("#transferRevokeAuditModal #revokeMemo").val(transferRevoke.revokeMemo);
				$("#transferRevokeAuditModal #createId").val(transferRevoke.createId);
				$("#transferRevokeAuditModal #createTime").val(transferRevoke.createTime);
				
			});
			$('#transferRevokeAuditModal').on('hide.bs.modal', function () {
				// 清除
				$("#transferRevokeAuditModal #orderId").val('');
				$("#transferRevokeAuditModal #originOrderId").val('');
				$("#transferRevokeAuditModal #originTransTime").val('');
				$("#transferRevokeAuditModal #payerCustId").val('');
				$("#transferRevokeAuditModal #payeeCustId").val('');
				$("#transferRevokeAuditModal #revokeAmt").val('');
				$("#transferRevokeAuditModal #revokeMemo").val('');
				$("#transferRevokeAuditModal #createId").val('');
				$("#transferRevokeAuditModal #createTime").val('');
				
			});
		});
		
		/**审核 **/
		$('.transferRevokeAuditBtn').click(function (){
			  var orderId = $("#transferRevokeAuditModal #orderId").val();
			  var originOrderId = $("#transferRevokeAuditModal #originOrderId").val();
			  var revokeMemo = $("#transferRevokeAuditModal #revokeMemo").val();
			  var auditState = $("#transferRevokeAuditModal #auditState").val();
			  $.blockUI();
				$.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=TransferRevokePath.BASE + TransferRevokePath.TRANSFER_REVOKE_AUDIT%>',
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
								$("#transferRevokeAuditModal").modal("hide");
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