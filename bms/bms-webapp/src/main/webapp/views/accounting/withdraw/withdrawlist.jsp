<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.accounting.withdraw.WithdrawPath"%>	
<%@page import="com.qifenqian.bms.basemanager.merchantwithdraw.MerchantWithdrawPath"%>
<%@page import="com.qifenqian.bms.accounting.withdrawrevoke.WithdrawRevokePath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>

<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户提现结算</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}

li {
	list-style-type: none;
}

.displayUl {
	display: none;
}
</style>
</head>
<body onload="load()">
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
							   <li><a href="#balanceWhole"  data-toggle="tab"  id="balanceWhole">汇总余额</a></li>
							   <li><a href="#changeBalance" data-toggle="tab" id="changeBalance">余额变动</a></li>						   
							   <li><a href="#commerciaBalance" data-toggle="tab" id="commerciaBalance"> 商户余额</a></li>
							   <li><a href="#userBalance" data-toggle="tab" id="userBalance">用户余额</a></li>
							   <li><a href="#cashSettlement" data-toggle="tab" id="cashSettlement">客戶提现结算</a></li>
							   <li><a href="#merchantSettlement" data-toggle="tab" id="merchantSettlement">商户提现结算</a></li>
							   <li><a href="#withdrawrevoke" data-toggle="tab" id="withdrawrevoke">客户提现撤销列表</a></li>
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="balanceWhole">
										<div class="table-responsive">
										<input id="auditState2" type="hidden" value="${withdrawBean.auditState }">
										<input id="withdrawState2" type="hidden" value="${withdrawBean.withdrawState }">
										<input id="verificationState2" type="hidden" value="${withdrawBean.verificationState }">
											<form id="tradeForm"  action='<c:url value="<%=WithdrawPath.BASE + WithdrawPath.WITHDRAWLIST %>"/>' method="post">
												<table class="table search-table" >
													<tr>
													    <td class="td-left" >七分钱提现流水号</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text"	name="withdrawSn" id="withdrawSn" value="${withdrawBean.withdrawSn }">
																	<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left" >平台流水号</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text" id="tradeId" name="tradeId" value="${withdrawBean.tradeId }">
																<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left">提现审核日期</td>
														<td class="td-right">
															 <input type="text" name="auditBeginTime"  id="auditBeginTime" value="${withdrawBean.auditBeginTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
															 	-
															 <input type="text" name="auditEndTime" id="auditEndTime" value="${withdrawBean.auditEndTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														</td>
													</tr>
													<tr>
													    <td class="td-left" >客户手机</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text" name="mobile"  id="mobile" value="${withdrawBean.mobile }">
																<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left" >提现银行卡号</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text" id="cardNo" name="cardNo" value="${withdrawBean.cardNo }">
																<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left">提现申请日期</td>
														<td class="td-right">
															 <input type="text" name="beginTime"  id="beginTime" value="${withdrawBean.beginTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
															 	-
															 <input type="text" name="endTime" id="endTime" value="${withdrawBean.endTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														</td>
													</tr>												
													<tr>
														<td class="td-left" >金蝶清算编号</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text"	name="clearId" id="clearId" value="${withdrawBean.clearId }">
																	<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left" >关联流水号</td>
														<td class="td-right">
															<span class="input-icon">
																<input type="text"	name="withdrawReqserialid" id="withdrawReqserialid" value="${withdrawBean.withdrawReqserialid }">
																	<i class="icon-leaf blue"></i>
															</span>
														</td>
														<td class="td-left" >提现状态</td>
														<td class="td-right" >
															<select name="withdrawState" id="withdrawState">
															    <option value="">-请选择-</option>
															    <option value="00">提现成功</option>
																<option value="01">待提现</option>
																<option value="02">提现申请提交核心</option>
																<option value="03">待审核</option>
																<option value="04">提现申请失败</option>
																<option value="22">提现提交核心</option>
																<option value="23">提现核心处理成功</option>
																<option value="24">提现核心处理失败</option>
																<option value="31">提现提交金蝶</option>
																<option value="32">提现金蝶处理中</option>
																<option value="33">提现金蝶处理成功</option>
																<option value="34">提现金蝶处理失败</option>
																<option value="42">提现撤销提交核心</option>
																<option value="43">提现撤销核心处理成功</option>
																<option value="44">提现撤销核心处理失败</option>
																<option value="52">提现退回提交核心</option>
																<option value="53">提现退回成功</option>
																<option value="54">提现退回失败</option>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td-left" >审核状态</td>
														<td class="td-right" >
															<select name="auditState" id="auditState">
																<option value="">-请选择-</option>
																<option value="01">待审核</option>
																<option value="02">审核通过</option>
																<option value="04">审核不通过</option>
															</select>
														</td>	
														<td class="td-left" >核销状态</td>
														<td class="td-right" colspan="3">
															<select  name="verificationState" id="verificationState">
																<option value="">-请选择-</option>
																<option value="01">未核销</option>
																<option value="02">已核销</option>
															</select>
														</td>
													</tr>
													<tr>
													<td colspan="6" align="center">
																<button type="submit" class="btn btn-purple btn-sm buttonSearch">
																	查询
																 <i class="icon-search icon-on-right bigger-110"></i>
																</button>
																<button  class="btn btn-purple btn-sm btn-margin clearWithdraw" >
																	清空
																	<i class=" icon-move icon-on-right bigger-110"></i>
																</button>
																<gyzbadmin:function url="<%=WithdrawPath.BASE+WithdrawPath.WITHDRAWEXPORT%>">
																<a class="btn btn-purple btn-sm exportBut">
																	导出报表
																</a> 
																</gyzbadmin:function>
														</td>
													</tr>
												</table>
											</form>
											<table id="sample-table-2" class="list-table">
												<thead>
													<tr>
														<th width="8%">七分钱提现流水号</th>
														<th width="8%">关联流水号</th>
														<th width="8%">平台流水号</th>
														<th width="8%">金蝶清算编号</th>
														<th width="5%">客户手机</th>
														<th width="5%">收款名</th>
														<th width="8%">提现银行卡号</th>
														<th width="5%">提现金额</th>
														<th width="8%">提现申请日期</th>
														<th width="5%">审核状态</th>
														<th width="8%">审核时间</th>
														<th width="8%">提现状态</th>
														<th width="5%">核销状态</th>
														<th width="12%">操作</th>
													</tr>
												</thead>
												<tbody id="impeachData">
													<c:forEach items="${withdrawList}" var="withdraw">
														<tr class="withdraw">
															<td>${withdraw.withdrawSn }</td>
															<td>${withdraw.withdrawReqserialid }</td>
															<td>${withdraw.tradeId }</td>
															<td>${withdraw.clearId }</td>
															<td>${withdraw.mobile }</td>
															<td>${withdraw.bankAcctName }</td>
															<td>${withdraw.cardNo }</td>
															<td>${withdraw.withdrawAmt}</td>
															<td>${withdraw.createTime }</td>
															<td>
																<c:if test="${withdraw.auditState=='01' }">待审核</c:if>
																<c:if test="${withdraw.auditState=='02' }">审核通过</c:if>
																<c:if test="${withdraw.auditState=='04' }">审核不通过</c:if>
															</td>
															<td>${withdraw.auditTime}</td>
															<td>
																<c:if test="${withdraw.withdrawState=='00' }">提现成功</c:if>
																<c:if test="${withdraw.withdrawState=='01' }">待提现</c:if>
																<c:if test="${withdraw.withdrawState=='02' }">提现申请提交核心</c:if>
																<c:if test="${withdraw.withdrawState=='03' }">提现申请成功</c:if>
																<c:if test="${withdraw.withdrawState=='04' }">提现申请失败</c:if>
																<c:if test="${withdraw.withdrawState=='22' }">提现提交核心</c:if>
																<c:if test="${withdraw.withdrawState=='23' }">提现核心处理成功</c:if>
																<c:if test="${withdraw.withdrawState=='24' }">提现核心处理失败</c:if>
																<c:if test="${withdraw.withdrawState=='31' }">提现提交金蝶</c:if>
																<c:if test="${withdraw.withdrawState=='32' }">提现金蝶处理中</c:if>
																<c:if test="${withdraw.withdrawState=='33' }">提现金蝶处理成功</c:if>
																<c:if test="${withdraw.withdrawState=='34' }">提现金蝶处理失败</c:if>
																<c:if test="${withdraw.withdrawState=='42' }">提现撤销提交核心</c:if>
																<c:if test="${withdraw.withdrawState=='43' }">提现撤销成功</c:if>
																<c:if test="${withdraw.withdrawState=='44' }">提现撤销核心处理失败</c:if>
																<c:if test="${withdraw.withdrawState=='52' }">提现退回提交核心</c:if>
																<c:if test="${withdraw.withdrawState=='53' }">提现退回成功</c:if>
																<c:if test="${withdraw.withdrawState=='54' }">提现退回失败</c:if>
															</td>
															<td>
																<c:if test="${withdraw.verificationState=='01' }">未核销</c:if>
																<c:if test="${withdraw.verificationState=='02' }">已核销</c:if>
															</td>
															<td>
																<c:if test="${withdraw.auditState!='01' }">
																	<a href="#reviewWithdrawModal"  data-toggle='modal' class="tooltip-error auditWithdraw" data-rel="tooltip" title="查看详情">
																		<button type="button"   id="btnEmail2"  class="btn btn-purple btn-xs"  >查看详情</button>	
																	</a>
																</c:if>
															   <gyzbadmin:function url="<%=WithdrawPath.BASE+WithdrawPath.WITHDRAWAUDITPASS%>">
																	<c:if test="${withdraw.auditState=='01' }">
																		<a href="#reviewWithdrawModal"  data-toggle='modal' class="tooltip-error auditWithdraw" data-rel="tooltip" title="审核">
																			<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >提现审核</button>	
																		</a>
																	</c:if>
																</gyzbadmin:function>
																<gyzbadmin:function url="<%=WithdrawPath.BASE+WithdrawPath.WITHDRAWVERIFICATION%>">
																	<c:if test="${withdraw.verificationState=='01' and withdraw.withdrawState=='33'}">
																		<a href="#verificationWithdrawModal"  data-toggle='modal' class="tooltip-error verificationWithdraw" data-rel="tooltip" title="核销">
																			<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >确认核销</button>	
																		</a>
																	</c:if>
																</gyzbadmin:function>
																<gyzbadmin:function url="<%=WithdrawPath.BASE+WithdrawPath.WITHDRAW_REFUND%>">
																	<c:if test="${withdraw.withdrawState=='34'}">
																		<a href="#refundWithdrawModal"  data-toggle='modal' class="tooltip-error refundWithdraw" data-rel="tooltip" title="提现退回">
																			<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >提现退回</button>	
																		</a>
																	</c:if>
																</gyzbadmin:function>
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty withdrawList}">
														<tr>
															<td colspan="14" align="center"><font
																style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
														</tr>
													</c:if>
												</tbody>
											</table>
										</div>
										<c:if test="${not empty withdrawList}">
											<%@ include file="/include/page.jsp"%>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
		</div>
		<!-- /.main-content -->
		<!-- 设置 -->
		<%@ include file="/include/setting.jsp"%>
	</div>
	<!-- /.main-container-inner -->
	<div>
	<!-- 向上置顶 -->
	<%@ include file="/include/up.jsp"%>
	</div>
	<!-- /.main-container -->
	<div class="modal fade" id="reviewWithdrawModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:50%">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核信息</h4>
		         </div>
		         <input type="hidden" id="coreSn" />
		         <input type="hidden" id="custId"/>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		                <tr>
							<td class="td-left" width="15%">七分钱提现流水号</td>
							<td class="td-right" width="35%">
								<input type="text" id=withdrawSn name="withdrawSn" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left" width="15%">提现金额</td>
							<td class="td-right" width="35%">
								<input type="text" id="withdrawAmt" name="withdrawAmt" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						 <tr>
							<td class="td-left" width="15%">平台流水号</td>
							<td class="td-right" width="35%">
								<input type="text" id=tradeId name="tradeId" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left" width="15%">提现银行卡编号</td>
							<td class="td-right" width="35%">
								<input type="text" id="bnakAcctNoInternal" name="bnakAcctNoInternal" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						  <tr>
							<td class="td-left">客户手机</td>
							<td class="td-right">
								<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">提现申请时间</td>
							<td class="td-right">
								<input type="text" id="createTime" name="createTime" readonly="readonly" clasS="width-90">
							</td>
							
						</tr>
		            	<tr>
							<td class="td-left">客户名</td>
							<td class="td-right">
									<input type="text" id=custName name="custName" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">审核状态</td>
							<td class="td-right">
								<select id="auditState" name="auditState" clasS="width-90" disabled="disabled">
									<option value="01">待审核</option>
									<option value="02">审核通过</option>
									<option value="04">审核不通过</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">收款名</td>
							<td class="td-right" >
									<input type="text" id="bankAcctName" name="bankAcctName" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">审核人</td>
							<td class="td-right">
								<input type="text" id="modifyId" name="modifyId" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						<tr>
							<td class="td-left" >开户行</td>
							<td class="td-right">
									<input type="text" id="bankName" name="bankName" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">审核时间</td>
							<td class="td-right">
								<input type="text" id="auditTime" name="auditTime" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						<tr>
							<td class="td-left" >支行信息</td>
							<td class="td-right" >
									<input type="text" id="bankBranchName" name="bankBranchName" readonly="readonly" clasS="width-90">
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
							
							<td class="td-left" >银行卡</td>
							<td class="td-right" >
									<input type="text" id="cardNo" name="cardNo" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">核销人</td>
							<td class="td-right">
								<input type="text" id="verificationUser" name="verificationUser" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						<tr>
							<td class="td-left" >手续费</td>
							<td class="td-right">
									<input type="text" id="fee" name="fee" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left">核销时间</td>
							<td class="td-right">
								<input type="text" id="verificationDate" name="verificationDate" readonly="readonly" clasS="width-90">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default noAuditBtn" >审核不通过</button>
		            <button type="button" class="btn btn-primary yesAuditBtn">审核通过</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
         <div class="modal fade" id="verificationWithdrawModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">确认核销</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定已核销[<span class="withdrawSn"></span>]么？</font>
		         	<input type="hidden" name="withdrawSn" id="withdrawSn" />
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary verificationWithdrawBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		    </div>
		</div>
		<div class="modal fade" id="refundWithdrawModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:50%">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核信息</h4>
		         </div>
		         <input type="hidden" id="coreSn" />
		         <input type="hidden" id="custId"/>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		                <tr>
							<td class="td-left" width="15%">七分钱提现流水号</td>
							<td class="td-right" width="35%">
								<input type="text" id=withdrawSn name="withdrawSn" readonly="readonly" clasS="width-90">
							</td>
							<td class="td-left" width="15%">金额</td>
							<td class="td-right" width="35%">
								<input type="text" id="withdrawAmt" name="withdrawAmt" readonly="readonly" clasS="width-90">
							</td>
						</tr>
						 <tr>
							<td class="td-left">客户手机</td>
							<td class="td-right">
								<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-90">
							</td>
							
							<td class="td-left">客户名</td>
							<td class="td-right">
									<input type="text" id=custName name="custName" readonly="readonly" clasS="width-90">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		             <button type="button" class="btn btn-primary refundWithdrawBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
				
<script type="text/javascript">
	function load(){
		var auditState2=$("#auditState2").val();
		$(".search-table #auditState").val(auditState2);
		var withdrawState2=$("#withdrawState2").val();
		$(".search-table #withdrawState").val(withdrawState2);
		var verificationState2=$("#verificationState2").val();
		$(".search-table #verificationState").val(verificationState2);
	}
	
	$(function () {	
		
		$('.clearWithdraw').click(function(){
			$('.search-table #auditState').val('');
			$('.search-table #withdrawState').val('');
			$('.search-table #verificationState').val('');
			$('.search-table #beginTime').val('');
			$('.search-table #endTime').val('');
			$('.search-table #auditBeginTime').val('');
			$('.search-table #auditEndTime').val('');
			$('.search-table #withdrawSn').val('');
			$('.search-table #tradeId').val('');
			$('.search-table #clearId').val('');
			$('.search-table #cardNo').val('');
			$('.search-table #mobile').val('');
			$('.search-table #withdrawReqserialid').val('');
			
		}) 
		
		//按条件查询
		$('.buttonSearch').click(function(){
			var startDate =$("#beginTime").val();
			var endDate= $("#endTime").val();
			var auditBeginTime= $("#auditBeginTime").val();
			var auditEndTime= $("#auditEndTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
				return false;
			}
			
			if("" != auditBeginTime && "" != auditEndTime && auditBeginTime > auditEndTime) 
			{
				$.gyzbadmin.alertFailure("审核结束日期不能小于开始日期");
				return false;
			}
			
			var form = $('#tradeForm');
			form.submit();
		});
		
		var withdraws= ${withdraws};
		var withdrawList=$("tr.withdraw");
		$.each(withdraws,function(i,value){
			$.data(withdrawList[i],"withdraw",value);
			
		});
		
		$(".exportBut").click(function(){
			var auditState = $('.search-table #auditState').val();
			var withdrawState =$('.search-table #withdrawState').val();
			var verificationState =$('.search-table #verificationState').val();
			var beginTime =$('.search-table #beginTime').val();
			var endTime =$('.search-table #endTime').val();
			var withdrawSn =$('.search-table #withdrawSn').val();
			var mobile =$('.search-table #mobile').val();
			var tradeId =$('.search-table #tradeId').val();
			var cardNo = $('.search-table #cardNo').val();
			var auditBeginTime = $('.search-table #auditBeginTime').val();
			var clearId = $('.search-table #clearId').val();
			var auditEndTime = $('.search-table #auditEndTime').val();
			var withdrawReqserialid =  $('.search-table #withdrawReqserialid').val();
			var src ="<%= request.getContextPath()+ WithdrawPath.BASE+WithdrawPath.WITHDRAWEXPORT%>?auditState="+
			auditState+"&withdrawState="+withdrawState+"&verificationState="+verificationState+"&beginTime="+beginTime+"&endTime="+endTime+"&mobile="+mobile+"&withdrawSn="+withdrawSn+"&cardNo="+cardNo+"&tradeId="+tradeId+"&auditBeginTime="+auditBeginTime+"&auditEndTime="+auditEndTime+"&clearId="+clearId+"&withdrawReqserialid="+withdrawReqserialid;
			$(".exportBut").attr("href",src);
		});
		
		
		/**加载退回**/
		$(".refundWithdraw").click(function(){
			var withdraw =$.data($(this).parent().parent()[0],"withdraw");
			
			$('#refundWithdrawModal').on('show.bs.modal', function () {
				$("#refundWithdrawModal #coreSn").val(withdraw.coreSn);
				$("#refundWithdrawModal #withdrawSn").val(withdraw.withdrawSn);
				$("#refundWithdrawModal #mobile").val(withdraw.mobile);
				$("#refundWithdrawModal #custId").val(withdraw.custId);
				$("#refundWithdrawModal #custName").val(withdraw.custName);
				$("#refundWithdrawModal #withdrawAmt").val(withdraw.withdrawAmt);
			});
			$('#refundWithdrawModal').on('hide.bs.modal', function () {
				$("#refundWithdrawModal #coreSn").val('');
				$("#refundWithdrawModal #withdrawSn").val('');
				$("#refundWithdrawModal #mobile").val('');
				$("#refundWithdrawModal #custId").val('');
				$("#refundWithdrawModal #custName").val('');
				$("#refundWithdrawModal #withdrawAmt").val('');
			});
			
		});
		
	/**退回 **/
	$(".refundWithdrawBtn").click(function(){
		$.blockUI();
		$.post(window.Constants.ContextPath +'<%=WithdrawPath.BASE+WithdrawPath.WITHDRAW_REFUND%>',{
			'withdrawSn'	:$("#refundWithdrawModal #withdrawSn").val(),
			'mobile'		:$("#refundWithdrawModal #mobile").val(),
			'custId'		:$("#refundWithdrawModal #custId").val(),
			'withdrawAmt'	:$("#refundWithdrawModal #withdrawAmt").val(),
			'coreSn'		:$("#refundWithdrawModal #coreSn").val()
		},function(data){
			$.unblockUI();
			if(data.result=='SUCCESS'){
				$.gyzbadmin.alertSuccess("退回成功！",function(){
					$("#refundWithdrawModal").modal("hide");
				},function(){
					window.location.reload();
				});
			}else{
				$.gyzbadmin.alertFailure(data.message,function(){
					window.location.reload();
				});
			}
		},'json'
	 );
	});
		
		
		$(".verificationWithdraw").click(function(){
			var withdraw =$.data($(this).parent().parent()[0],"withdraw");
			
			$('#verificationWithdrawModal').on('show.bs.modal', function () {
				// 赋值
				$('#verificationWithdrawModal .withdrawSn').html(withdraw.withdrawSn);
				$('#verificationWithdrawModal #withdrawSn').val(withdraw.withdrawSn);
			
			});
			$('#verificationWithdrawModal').on('hide.bs.modal', function () {
				// 清除
				$('#verificationWithdrawModal .withdrawSn').empty();
				$('#verificationWithdrawModal #withdrawSn').val('');
				
			});
		})
		

	$('.verificationWithdrawBtn').click(function(){
		var withdrawSn = $('#verificationWithdrawModal #withdrawSn').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%= WithdrawPath.BASE+WithdrawPath.WITHDRAWVERIFICATION%>', {
			'withdrawSn':withdrawSn
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#verificationWithdrawModal').modal('hide');
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
		
		$(".auditWithdraw").click(function(){
			var withdraw =$.data($(this).parent().parent()[0],"withdraw");
			
			if(withdraw.auditState!='01'){
				$("#reviewWithdrawModal .modal-footer").hide();
			}else{
				$("#reviewWithdrawModal .modal-footer").show();
			}
			
			$('#reviewWithdrawModal').on('show.bs.modal', function () {
			    $("#reviewWithdrawModal #tradeId").val(withdraw.tradeId);
				$("#reviewWithdrawModal #coreSn").val(withdraw.coreSn);
				$("#reviewWithdrawModal #withdrawSn").val(withdraw.withdrawSn);
				$("#reviewWithdrawModal #mobile").val(withdraw.mobile);
				$("#reviewWithdrawModal #custId").val(withdraw.custId);
				$("#reviewWithdrawModal #custName").val(withdraw.custName);
				$("#reviewWithdrawModal #bankAcctName").val(withdraw.bankAcctName);
				$("#reviewWithdrawModal #bankName").val(withdraw.bankName);
				$("#reviewWithdrawModal #bankBranchName").val(withdraw.bankBranchName);
				$("#reviewWithdrawModal #createTime").val(withdraw.createTime);
				$("#reviewWithdrawModal #cardNo").val(withdraw.cardNo);
				$("#reviewWithdrawModal #withdrawAmt").val(withdraw.withdrawAmt);
				$("#reviewWithdrawModal #fee").val(withdraw.fee);
				$("#reviewWithdrawModal #bnakAcctNoInternal").val(withdraw.bnakAcctNoInternal);
				$("#reviewWithdrawModal #verificationState").val(withdraw.verificationState);
				$("#reviewWithdrawModal #verificationUser").val(withdraw.verificationUser);
				$("#reviewWithdrawModal #verificationDate").val(withdraw.verificationDate);
				$("#reviewWithdrawModal #auditState").val(withdraw.auditState);
				$("#reviewWithdrawModal #modifyId").val(withdraw.modifyId);
				$("#reviewWithdrawModal #auditTime").val(withdraw.auditTime);
				
				
			});
			$('#reviewWithdrawModal').on('hide.bs.modal', function () {
			    $("#reviewWithdrawModal #tradeId").val('');
				$("#reviewWithdrawModal #coreSn").val('');
				$("#reviewWithdrawModal #withdrawSn").val('');
				$("#reviewWithdrawModal #mobile").val('');
				$("#reviewWithdrawModal #custId").val('');
				$("#reviewWithdrawModal #custName").val('');
				$("#reviewWithdrawModal #bankAcctName").val('');
				$("#reviewWithdrawModal #bankName").val('');
				$("#reviewWithdrawModal #bankBranchName").val('');
				$("#reviewWithdrawModal #createTime").val('');
				$("#reviewWithdrawModal #cardNo").val('');
				$("#reviewWithdrawModal #withdrawAmt").val('');
				$("#reviewWithdrawModal #fee").val('');
				$("#reviewWithdrawModal #bnakAcctNoInternal").val('');
				$("#reviewWithdrawModal #verificationState").val('');
				$("#reviewWithdrawModal #verificationUser").val('');
				$("#reviewWithdrawModal #verificationDate").val('');
				$("#reviewWithdrawModal #auditState").val('');
				$("#reviewWithdrawModal #modifyId").val('');
				$("#reviewWithdrawModal #auditTime").val('');
			});
			
		});
		
		$(".noAuditBtn").click(function(){
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=WithdrawPath.BASE+WithdrawPath.WITHDRAWAUDITNOPASS%>',{
				'withdrawSn'	:$("#reviewWithdrawModal #withdrawSn").val(),
				'mobile'		:$("#reviewWithdrawModal #mobile").val(),
				'tradeId'		:$("#reviewWithdrawModal #tradeId").val(),
				'custId'		:$("#reviewWithdrawModal #custId").val(),
				'withdrawAmt'	:$("#reviewWithdrawModal #withdrawAmt").val(),
				'cardNo'	:$("#reviewWithdrawModal #cardNo").val(),
				'coreSn'		:$("#reviewWithdrawModal #coreSn").val(),
				'bnakAcctNoInternal' 	:$("#reviewWithdrawModal #bnakAcctNoInternal").val()
			},function(data){
				$.unblockUI();
				if(data.result=='SUCCESS'){
					$.gyzbadmin.alertSuccess("审核成功！",function(){
						$("#reviewWithdrawModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure(data.message,function(){
					},function(){
						window.location.reload();
					});
				}
			},'json'
				);
		});
		
		$(".yesAuditBtn").click(function(){
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=WithdrawPath.BASE+WithdrawPath.WITHDRAWAUDITPASS%>',{
				'withdrawSn'	:$("#reviewWithdrawModal #withdrawSn").val(),
				'mobile'		:$("#reviewWithdrawModal #mobile").val(),
				'tradeId'		:$("#reviewWithdrawModal #tradeId").val(),
				'custId'		:$("#reviewWithdrawModal #custId").val(),
				'withdrawAmt'	:$("#reviewWithdrawModal #withdrawAmt").val(),
				'cardNo'	:$("#reviewWithdrawModal #cardNo").val(),
				'coreSn'		:$("#reviewWithdrawModal #coreSn").val(),
				'bnakAcctNoInternal' 	:$("#reviewWithdrawModal #bnakAcctNoInternal").val()
			},function(data){
				$.unblockUI();
				if(data.result=='SUCCESS'){
					$.gyzbadmin.alertSuccess("审核成功！",function(){
						$("#reviewWithdrawModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure(data.message,function(){
					},function(){
						window.location.reload();
					});
				}
			},'json'
				);
		});
		
	     $('#myTab li:eq(4) a').tab('show');
		 	
	   });
	
	$("#balanceWhole").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.LIST%>";
	 });
	
	$("#changeBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.CHANGEBALANCELIST%>";
	 });
	
	$("#commerciaBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.COMMERCIABALANCELIST%>";
	 });
	
	$("#userBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.USERBALANCELIST%>";
	 });
	
	$("#merchantSettlement").click(function(){
		window.location.href="<%=request.getContextPath()+MerchantWithdrawPath.BASE + MerchantWithdrawPath.LIST%>";
	 });
	
	$("#withdrawrevoke").click(function(){
		window.location.href="<%=request.getContextPath()+WithdrawRevokePath.BASE + WithdrawRevokePath.WITHDRAWREVOKE%>";
	 });
	
	</script>
</body>
</html>
