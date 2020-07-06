<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.refund.RefundBillPath" %> 
<html>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>

<head>
	<meta charset="utf-8" />
	<title>退款管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadTransfer()">
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
						<form id="refundForm" action='<c:url value="<%=RefundBillPath.BASE + RefundBillPath.LIST %>"/>' method="post">
						<table class="search-table" >
							<tr>
								<td class="td-left">七分钱退款编号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="orderId" id="orderId" value="${refundBean.orderId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >原始交易时间</td>
								<td class="td-right">
										<input type="text"  name="originBeginTime" id="originBeginTime" value="${refundBean.originBeginTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="originEndTime" id="originEndTime" value="${refundBean.originEndTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
								<td class="td-left" >渠道订单号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="rtnSeq" id="rtnSeq" value="${refundBean.rtnSeq }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >退款类型</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="msgTypeTemp" value="${refundBean.msgType}">
											<select id="msgType" name="msgType">
												<option value="">- 请选择 -</option>
												<option value="BALANCE_PAYMENT_REFUND">- 余额支付退款 -</option>
												<option value="BANK_CARD_PAYMENT_REFUND">- 卡支付退款 -</option>
												<option value="RED_PACKET_PAYMENT_REFUND">- 红包支付退款 -</option>
											</select>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >客户账号</td>
								<td class="td-right"  >
									<span class="input-icon">
										<input type="text"  name="mobile" id="mobile" value="${refundBean.mobile }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								
								<td class="td-left" >退款申请时间</td>
								<td class="td-right">
										<input type="text"  name="startTime" id="startTime" value="${refundBean.startTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="endTime" id="endTime" value="${refundBean.endTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
							
								<td class="td-left" >商户编号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="merchantCustId" id="merchantCustId" value="${refundBean.merchantCustId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >退款状态</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="refundStateTemp" value="${refundBean.refundState}">
											<select id="refundState" name="refundState">
												<option value="">- 请选择 -</option>
												<option value="00">- 成功 -</option>
												<option value="01">- 待审核 -</option>
												<option value="02">- 处理中 -</option>
												<option value="04">- 失败 -</option>
												<option value="05">- 审核不通过 -</option>
											</select>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >原七分钱订单号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="originOrderId" id="originOrderId" value="${refundBean.originOrderId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >账期</td>
								<td class="td-right">
										<input type="text"  name="startWorkDate" id="startWorkDate" value="${refundBean.startWorkDate }"  readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="endWorkDate" id="endWorkDate" value="${refundBean.endWorkDate }"  readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
								<td colspan="4" align="center" >
									<span class="input-group-btn" style="display:inline;">
										<gyzbadmin:function url="<%=RefundBillPath.BASE + RefundBillPath.LIST %>">
										<button type="button" class="btn btn-purple btn-sm buttonSearch">
												查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button> 
										<button class="btn btn-purple btn-sm btn-margin clearRefund" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</gyzbadmin:function>
										<gyzbadmin:function url="<%=RefundBillPath.BASE + RefundBillPath.REFUNDEXPORT%>">
										<a class="btn btn-purple btn-sm exportBut">
												导出报表
										</a> 
										</gyzbadmin:function>
									</span>
								</td>
							</tr>
						</table>
						</form>
						<div class="list-table-header" >
							退款申请列表
						</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th width="8%">七分钱退款编号</th>
										<th width="6%">渠道订单号</th>
										<th width="6%">原七分钱订单号</th>
										<th width="6%">退款类型</th>
										<th width="4%">原始交易金额</th>
										<th width="6%">原始交易时间</th>
										<th width="6%">客户账号</th>
										<th width="10%">商户编号</th>
										<th width="4%">退款金额</th>
										<th width="6%">退款申请时间</th>
										<th width="5%">账期</th>
										<th width="5%">退款状态</th>
										<th width="6%">审核状态</th>
										<th width="4%">核销状态</th>
										<th width="6%">备注</th>
										<th width="12%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${refundBillList}" var="refund">
										<tr class="refund">
											<td><a onclick="queryRefundRecord(this)">${refund.orderId}</a></td>
											<td>${refund.rtnSeq}</td>
											<td>${refund.originOrderId}</td>
											<td>
												<c:if test="${refund.msgType == 'BALANCE_PAYMENT_REFUND'}">余额支付退款</c:if>
												<c:if test="${refund.msgType == 'BANK_CARD_PAYMENT_REFUND'}">卡支付退款</c:if>
												<c:if test="${refund.msgType == 'RED_PACKET_PAYMENT_REFUND'}">红包支付退款</c:if>
											</td>
											<td>${refund.originTransAmt}</td>
											<td>${refund.originTransTime}</td>
											<td>${refund.mobile}</td>
											<td>${refund.merchantCustId}</td>
											<td>${refund.refundAmt}</td>
											<td>${refund.submitTime}</td>
											<td>${refund.workDate}</td>
											<td>
											    <c:if test="${refund.refundState=='00' }">退款成功</c:if>
												<c:if test="${refund.refundState=='01' }">待审核</c:if>
												<c:if test="${refund.refundState=='02' }">处理中</c:if>
												<c:if test="${refund.refundState=='03' }">处理中</c:if>
												<c:if test="${refund.refundState=='04' }">退款失败</c:if>
												<c:if test="${refund.refundState=='05' }">审核不通过</c:if>
											</td>
											<td>
												<c:if test="${refund.auditState=='01' }">待审核</c:if>
												<c:if test="${refund.auditState=='02' }">审核通过</c:if>
												<c:if test="${refund.auditState=='04' }">审核不通过</c:if>
											</td>
											
											<td>
												<c:if test="${refund.verificationState=='01' }">未核销</c:if>
												<c:if test="${refund.verificationState=='02' }">已核销</c:if>
											</td>
											<td>${refund.coreReturnMsg}</td>
											<td>
												<c:if test="${refund.auditState!='01' }">
													<a href="#" class="tooltip-success approveLink" data-rel="tooltip" title="Edit" data-toggle='modal'  data-target="#approveModal">
														<button type="button"   id="btnEmail2"  class="btn btn-purple btn-xs" >查看详情</button>										
													</a>
												</c:if>	
												<gyzbadmin:function url="<%=RefundBillPath.BASE + RefundBillPath.APPROVAL%>">
													<c:if test="${refund.auditState=='01'}">
														<a href="#" class="tooltip-success approveLink" data-rel="tooltip" title="Edit" data-toggle='modal'  data-target="#approveModal">
															<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >退款审核</button>
														</a>	
													</c:if>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=RefundBillPath.BASE + RefundBillPath.VERIFICATION%>">
													<c:if test="${refund.verificationState=='01' and refund.auditState=='02' and refund.refundState=='00'}">
														<a href="#verificationRefundModal"  data-toggle='modal' class="tooltip-error verificationRefund" data-rel="tooltip" title="核销">
															<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >确认核销</button>	
														</a>
													</c:if>
												</gyzbadmin:function>	
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty refundBillList}">
										<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty refundBillList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 审核 -->
				<div class="modal fade" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:50%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">退货审批</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left">七分钱退款编号</td>
									<td class="td-right">
										<input type="text" id="orderId" name="orderId" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left" >客户账号</td>
									<td class="td-right">
										<input type="hidden" id="refundCustId" name="refundCustId" readonly="readonly" clasS="width-90">
										<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">原七分钱订单号</td>
									<td class="td-right">
									    <input type="hidden" id="originCoreSn" name="originCoreSn"  readonly="readonly" clasS="width-90">
										<input type="text" id="originOrderId" name="originOrderId"  readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<input type="text" id="merchantCustId" name="merchantCustId" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">渠道订单号</td>
									<td class="td-right">
										<input type="text" id="rtnSeq" name="rtnSeq" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<input type="text" id="custName" name="custName" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left" >原商户订单号</td>
									<td class="td-right">
										<input type="text" id="originMerOrderId" name="originMerOrderId" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">退款申请时间</td>
									<td class="td-right">
										<input type="text" id="submitTime" name="submitTime" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left">原始交易金额</td>
									<td class="td-right">
										<input type="text" id="originTransAmt" name="originTransAmt" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">退款金额</td>
									<td class="td-right">
										<input type="text" id="refundAmt" name="refundAmt" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">原始交易时间</td>
									<td class="td-right">
										<input type="text" id="originTransTime" name="originTransTime" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">账期</td>
									<td class="td-right">
										<input type="text" id="workDate" name="workDate" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">手续费</td>
									<td class="td-right">
										<input type="text" id="fee" name="fee" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">付手续费方</td>
									<td class="td-right">
										<input type="text" id="feeCustId" name="feeCustId"  readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">审核</td>
									<td class="td-right">
										<select id="auditState" name="auditState" clasS="width-90">
											<option value="01">待审核</option>
											<option value="02">审核通过</option>
											<option value="04">审核不通过</option>
										</select>
									</td>
									<td class="td-left">核销状态</td>
									<td class="td-right">
										<select id="verificationState" name="verificationState" clasS="width-90" disabled="disabled">
											<option value="01">未核销</option>
											<option value="02">已核销</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="td-left">审核人</td>
									<td class="td-right">
										<input type="text" id="modifyId" name="modifyId" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">核销人</td>
									<td class="td-right">
										<input type="text" id="verificationUser" name="verificationUser" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">审核时间</td>
									<td class="td-right">
										<input type="text" id="modifyTime" name="modifyTime" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left">核销时间</td>
									<td class="td-right">
										<input type="text" id="verificationTime" name="verificationTime" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">退款理由</td>
									<td class="td-right" colspan="3">
										<textarea rows="2" cols="" id="refundMemo" name="refundMemo" readonly="readonly" clasS="width-90"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button"  id="approveBtn"  class="btn btn-primary approveBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
			</div><!-- /.page-content -->
			
		 <div class="modal fade" id="verificationRefundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">确认核销</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定已核销[<span class="orderId"></span>]么？</font>
		         	<input type="hidden" name="orderId" id="orderId" />
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary verificationRefundBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
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
</body>
<script type="text/javascript">

function loadTransfer(){
	$(".search-table #refundState").val($(".search-table #refundStateTemp").val());
	$(".search-table #msgType").val($(".search-table #msgTypeTemp").val());

}
$(function(){
	var refundListJson = '<c:out value="${gyzb:toJSONString(refundBillList)}" escapeXml="false"/>';
	
	var refundTrList = $('tr.refund');
	
	$.each($.parseJSON(refundListJson), function(idx, obj){
		$.data(refundTrList[idx], 'refund', obj);
	});	

	$('.clearRefund').click(function(){
		$('.search-table #orderId').val('');
		$('.search-table #originOrderId').val('');
		$('.search-table #rtnSeq').val('');
		$('.search-table #mobile').val('');
		$('.search-table #merchantCustId').val('');
		$('.search-table #startTime').val('');
		$('.search-table #endTime').val('');
		$('.search-table #originBeginTime').val('');
		$('.search-table #originEndTime').val('');
		$('.search-table #refundState').val('');
		$('.search-table #msgType').val('');
		$(".search-table #startWorkDate").val('');
		$(".search-table #endWorkDate").val('');
	}) 
	
	$('.buttonSearch').click(function(){
			var startDate = $(".search-table #startTime").val();
			var endDate= $(".search-table #endTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("申请结束日期不能小于开始日期");
				return false;
			}
			var originStartDate = $(".search-table #originBeginTime").val();
			var originEndDate= $(".search-table #originEndTime").val();
			if("" != originStartDate && "" != originEndDate && originStartDate > originEndDate) 
			{
				$.gyzbadmin.alertFailure("原始交易结束日期不能小于开始日期");
				return false;
			}
			
			var startWorkDate = $(".search-table #startWorkDate").val();
			var endWorkDate= $(".search-table #endWorkDate").val();
			if("" != startWorkDate && "" != endWorkDate && startWorkDate > endWorkDate) 
			{
				$.gyzbadmin.alertFailure("账期结束日期不能小于开始日期");
				return false;
			}
			
			var form = $('#refundForm');
			form.submit();
		});
	
	//弹出修改框
    $('.approveLink').click(function(){
		var refund = $.data($(this).parent().parent()[0], 'refund');
       $('#approveModal').on('show.bs.modal', function () {
			$('#approveModal #orderId').val(refund.orderId);
			$('#approveModal #originOrderId').val(refund.originOrderId);
			$('#approveModal #originCoreSn').val(refund.originCoreSn);
			$('#approveModal #rtnSeq').val(refund.rtnSeq);
			$('#approveModal #originMerOrderId').val(refund.originMerOrderId);
			$('#approveModal #refundCustId').val(refund.refundCustId);
			$('#approveModal #mobile').val(refund.mobile);
			$('#approveModal #originTransAmt').val(refund.originTransAmt);
			$('#approveModal #originTransTime').val(refund.originTransTime);
			$('#approveModal #merchantCustId').val(refund.merchantCustId);
			$('#approveModal #custName').val(refund.custName);
			$('#approveModal #refundAmt').val(refund.refundAmt);
			$('#approveModal #fee').val(refund.fee);
			$('#approveModal #feeCustId').val(refund.feeCustId);
			$('#approveModal #workDate').val(refund.workDate);
			$('#approveModal #refundMemo').val(refund.refundMemo);
			$('#approveModal #auditState').val(refund.auditState);
			$('#approveModal #modifyId').val(refund.modifyId);
			$('#approveModal #modifyTime').val(refund.modifyTime);
			$('#approveModal #submitTime').val(refund.submitTime);
			$('#approveModal #verificationState').val(refund.verificationState);
			$('#approveModal #verificationUser').val(refund.verificationUser);
			$('#approveModal #verificationTime').val(refund.verificationTime);
			$('#approveModal #coreReturnMsg').val(refund.coreReturnMsg);
			
			if(refund.auditState=='01'){
				$('#approveModal #auditState').attr('disabled',false); 
				$('#approveBtn').show(); 
			}else{
				$('#approveModal #auditState').attr('disabled',true); 
				$('#approveBtn').hide(); 
			}  
			
		});
       $('#approveModal').on('hide.bs.modal', function () {
			// 清除
    	    $('#approveModal #orderId').val('');
			$('#approveModal #originOrderId').val('');
			$('#approveModal #originCoreSn').val('');
			$('#approveModal #rtnSeq').val(refund.rtnSeq);
			$('#approveModal #originMerOrderId').val('');
			$('#approveModal #refundCustId').val('');
			$('#approveModal #mobile').val('');
			$('#approveModal #originTransAmt').val('');
			$('#approveModal #originTransTime').val('');
			$('#approveModal #merchantCustId').val('');
			$('#approveModal #custName').val('');
			$('#approveModal #refundAmt').val('');
			$('#approveModal #fee').val('');
			$('#approveModal #feeCustId').val('');
			$('#approveModal #workDate').val('');
			$('#approveModal #refundMemo').val('');
			$('#approveModal #auditState').val('');
			$('#approveModal #modifyId').val('');
			$('#approveModal #modifyTime').val('');
			$('#approveModal #submitTime').val('');
			$('#approveModal #verificationState').val('');
			$('#approveModal #verificationUser').val('');
			$('#approveModal #verificationTime').val('');
			$('#approveModal #coreReturnMsg').val('');
		});
	}); 
	
	$('.approveBtn').click(function(){
		
		var orderId = $('#approveModal #orderId').val();
		if(kong.test(orderId)) {
			$.gyzbadmin.alertFailure('七分钱订单号不可为空');
			return;
		}
		
		var originOrderId = $('#approveModal #originOrderId').val();
		if(kong.test(originOrderId)) {
			$.gyzbadmin.alertFailure('七分钱原交易订单号不可为空');
			return;
		}
		var refundCustId = $('#approveModal #refundCustId').val();
		if(kong.test(refundCustId)) {
			$.gyzbadmin.alertFailure('客户编号不可为空');
			return;
		}
		var originCoreSn = $('#approveModal #originCoreSn').val();
		if(kong.test(originCoreSn)) {
			$.gyzbadmin.alertFailure('原交易流水号不可为空');
			return;
		}
		var originTransAmt = $('#approveModal #originTransAmt').val();
		if(kong.test(originTransAmt)) {
			$.gyzbadmin.alertFailure('原交易金额不可为空');
			return;
		}
		
		var refundAmt = $('#approveModal #refundAmt').val();
		if(kong.test(refundAmt)) {
			$.gyzbadmin.alertFailure('退款金额不可为空');
			return;
		}
		var originTransTime = $('#approveModal #originTransTime').val();
		if(kong.test(originTransTime)) {
			$.gyzbadmin.alertFailure('原始交易时间不能为空');
			return;
		}
		var auditState = $('#approveModal #auditState').val();
		if(kong.test(auditState)||auditState=='01'||auditState=='03') {
			$.gyzbadmin.alertFailure('请选择审核信息');
			return;
		}
		
		var merchantCustId = $('#approveModal #merchantCustId').val();
		if(kong.test(merchantCustId)) {
			$.gyzbadmin.alertFailure('商户编号不能为空');
			return;
		}
		
		var fee = $('#approveModal #fee').val();
		var feeCustId = $('#approveModal #feeCustId').val();
		
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=RefundBillPath.BASE + RefundBillPath.APPROVAL %>', {
				'orderId'			:orderId,
				'originOrderId' 	:originOrderId,
				'refundCustId' 		:refundCustId,
				'originCoreSn'		:originCoreSn,
				'originTransAmt' 	:originTransAmt,
				'refundAmt'			:refundAmt,
				'auditState'		:auditState,
				'feeCustId'			:feeCustId,
				'fee'				:fee,
				'merchantCustId'	:merchantCustId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#approveModal').modal('hide');
					$.gyzbadmin.alertSuccess('审核成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message,function(){
					},function(){
						window.location.reload();
					});
				}
			}, 'json'
		);
	});
	
	$(".exportBut").click(function(){
		var orderId = $('.search-table #orderId').val();
		var originOrderId = $('.search-table #originOrderId').val();
		var rtnSeq = $('.search-table #rtnSeq').val();
		var mobile = $('.search-table #mobile').val();
		var startTime = $('.search-table #startTime').val();
		var endTime = $('.search-table #endTime').val();
		var merchantCustId = $('.search-table #merchantCustId').val();
		var refundState = $('.search-table #refundState').val();
		var originBeginTime = $(".search-table #originBeginTime").val();
		var originEndTime= $(".search-table #originEndTime").val();
		var startWorkDate = $(".search-table #startWorkDate").val();
		var endWorkDate= $(".search-table #endWorkDate").val();
		var msgType= $(".search-table #msgType").val();
			var src ="<%= request.getContextPath()+ RefundBillPath.BASE+RefundBillPath.REFUNDEXPORT%>?orderId="+
			orderId+"&originOrderId="+originOrderId+"&rtnSeq="+rtnSeq+"&mobile="+mobile+"&startTime="+startTime+"&endTime="+endTime+"&originBeginTime="+originBeginTime+"&originEndTime="+originEndTime+"&refundState="+refundState+"&merchantCustId="+merchantCustId+"&startWorkDate="+startWorkDate+"&endWorkDate="+endWorkDate+"&msgType="+msgType;
			$(".exportBut").attr("href",src);
		});
	
	$(".verificationRefund").click(function(){
		var refund =$.data($(this).parent().parent()[0],"refund");
		
		$('#verificationRefundModal').on('show.bs.modal', function () {
			// 赋值
			$('#verificationRefundModal .orderId').html(refund.orderId);
			$('#verificationRefundModal #orderId').val(refund.orderId);
		
		});
		$('#verificationRefundModal').on('hide.bs.modal', function () {
			// 清除
			$('#verificationRefundModal .orderId').empty();
			$('#verificationRefundModal #orderId').val('');
			
		});
	})
	
	$('.verificationRefundBtn').click(function(){
		var orderId = $('#verificationRefundModal #orderId').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%= RefundBillPath.BASE+RefundBillPath.VERIFICATION%>', {
			'orderId':orderId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#verificationRefundModal').modal('hide');
					$.gyzbadmin.alertSuccess('核销成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message,function(){
					},function(){
						window.location.reload();
					});
				}
			}, 'json'
		);
	});
})

	/**退款审核记录**/
	function queryRefundRecord(obj){
		var orderId= $(obj).text();
		 var url=window.Constants.ContextPath+"<%=RefundBillPath.BASE + RefundBillPath.REFUNDRECORD%>?orderId="+orderId; 
	     var name="newwindow";                        
	     var iWidth=1500;                          
	     var iHeight=700;    
	     var iTop = (window.screen.availHeight-30-iHeight)/2;
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
	   	winChild = window.open(url, name,params);
	}
</script>
</html>