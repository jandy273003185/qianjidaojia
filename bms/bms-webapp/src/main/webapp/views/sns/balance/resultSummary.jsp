<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.sns.balance.RedenvResultPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>红包汇总报表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadResultSummary()">
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
						   		<li><a href="#resultStatistic" data-toggle="tab" id="resultStatisticReport">结果统计报表</a></li>
						   		<li><a href="#resultEqual"  data-toggle="tab"  id="resultEqualReport">一致报表</a></li>
						    	<li><a href="#resultFailure"  data-toggle="tab"  id="resultFailureReport">差错报表</a></li>
						   		<li><a href="#redenvDoubt" data-toggle="tab"  id="redenvDoubtReport">红包存疑报表</a></li>
						   		<li><a href="#sevenDoubt" data-toggle="tab"  id="sevenDoubtReport">七分钱存疑报表</a></li>
						   		<li><a href="#resultSummary" data-toggle="tab"  id="resultSummaryReport">红包汇总报表</a></li>
							</ul>
							<div class="table-responsive">
							   <div class="tab-pane fade" id="resultStatistic"></div>
							   <div class="tab-pane fade" id="resultEqual"></div>
							   <div class="tab-pane fade" id="resultFailure"></div>
							   <div class="tab-pane fade" id="redenvDoubt"></div>
							   <div class="tab-pane fade" id="sevenDoubt"></div>
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active">
							   			<form action='<c:url value="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left" width="5%">对账日期</td>
												<td class="td-right" width="20%">
													<span class="input-icon">
														<input type="text" name="balDate"  id="balDate" value="${queryBean.balDate}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="5%">会计日期</td>
												<td class="td-right" width="25%">
													<span class="input-icon">
														<input type="text" name="workBeginDate"  id="workBeginDate" value="${queryBean.workBeginDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														-
														<input type="text" name="workEndDate"  id="workEndDate" value="${queryBean.workEndDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left">出账状态</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="hidden" name="transStatusTemp"  id="transStatusTemp" value = "${queryBean.transStatus}"/>
														<select name="transStatus" id="transStatus">
															<option value="">--请选择--</option>
															<option value="00">--成功--</option>
															<option value="99">--失败--</option>
														</select>
													</span>
												</td>
												<td class="td-left">退款状态</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="hidden" name="refundStatusTemp"  id="refundStatusTemp" value = "${queryBean.refundStatus}"/>
														<select name="refundStatus" id="refundStatus">
															<option value="">--请选择--</option>
															<option value="00">--成功--</option>
															<option value="99">--失败--</option>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">红包编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="redenvId"  id="redenvId" value="${queryBean.redenvId}"/>
													</span>
												</td>
												<td class="td-left">红包日期</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="redenvDate"  id="redenvDate" value="${queryBean.redenvDate}" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left">对账状态</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="hidden" name="balStatusTemp"  id="balStatusTemp" value = "${queryBean.balStatus}"/>
														<select name="balStatus" id="balStatus">
															<option value="">--请选择--</option>
															<option value="EXCEPTION">--异常--</option>
															<option value="REUAL">--一致--</option>
														</select>
													</span>
												</td>
												<td class="td-left">处理状态</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="hidden" name="dealStatusTemp"  id="dealStatusTemp" value = "${queryBean.dealStatus}"/>
														<select name="dealStatus" id="dealStatus">
															<option value="">--请选择--</option>
															<option value="01">--待处理--</option>
															<option value="00">--处理成功--</option>
															<option value="99">--无需处理--</option>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="8" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY%>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY_EXPORT%>">
															<span class="input-group-btn" style="display:inline;">
																<a class="btn btn-purple btn-sm exportBut">
																	导出报表
																</a> 
															</span>
														</gyzbadmin:function>
													</span>
												</td>
											</tr>
										</table>
										</form>
										<div class="list-table-header">红包汇总报表</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															    <th>批次编号</th>
															    <th>对账日期</th>
															    <th>会计日期</th>
															    <th>红包日期</th>
															    <th>红包流水号</th>
															    <th>红包个数</th>
															    <th>总金额</th>
															    <th>出账状态</th>
															    <th>入账成功个数</th>
															    <th>入账成功金额</th>
															    <th>入账失败个数</th>
															    <th>入账失败金额</th>
															    <th>未入账个数</th>
															    <th>未入账金额</th>
															    <th>退款金额</th>
															    <th>退款状态</th>
															    <th>对账状态</th>
															    <th>对账备注</th>
															    <th>处理状态</th>
															    <th>处理备注</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${resultSummaryList}" var="summary">
														<tr class="summary">
															<td>${summary.batchId }</td>
															<td>${summary.balDate }</td>
															<td>${summary.workDate }</td>
															<td>${summary.redenvDate }</td>
															<td>${summary.redenvId }</td>
															<td>${summary.redenvCount }</td>
															<td>${summary.redenvAmt }</td>
															<td>${summary.transStatus }</td>
															<td>${summary.inSuccessCount }</td>
															<td>${summary.inSuccessAmt}</td>
															<td>${summary.inFailureCount }</td>
															<td>${summary.inFailureAmt}</td>
															<td>${summary.notInCount }</td>
															<td>${summary.notInAmt }</td>
															<td>${summary.refundAmt }</td>
															<td>${summary.refundStatus }</td>
															<td>${summary.balStatus }</td>
															<td>${summary.balMemo }</td>
															<td>${summary.dealStatusDesc }</td>
															<td>${summary.dealMemo }</td>
															<td>
															<c:if test="${summary.dealStatus!='01' }">
																<a href="#" class="tooltip-success dealRedenvLink" data-rel="tooltip" title="Edit" data-toggle='modal'  data-target="#dealRedenvModal">
																	<button type="button" id="btnEmail2" class="btn btn-purple btn-xs">详情</button>										
																</a>
															</c:if>	
															<gyzbadmin:function url="<%=RedenvResultPath.BASE + RedenvResultPath.DEAL_REDENV%>">
																<c:if test="${summary.dealStatus=='01'}">
																	<a href="#" class="tooltip-success dealRedenvLink" data-rel="tooltip" title="Edit" data-toggle='modal'  data-target="#dealRedenvModal">
																		<button type="button" id="btnEmail2" class="btn btn-primary btn-xs">处理</button>
																	</a>	
																</c:if>
															</gyzbadmin:function>
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty resultSummaryList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty resultSummaryList}">
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
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	<div class="modal fade" id="dealRedenvModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog" style="width:50%">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
			            <h4 class="modal-title" id="myModalLabel">红包汇总处理</h4>
			         </div>
			         <div class="modal-body">
			            <table class="modal-input-table" style="width: 100%;">
			            	<tr>
								<td class="td-left">批次编号</td>
								<td class="td-right">
									<input type="text" id="batchId" name="batchId" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left" >对账日期</td>
								<td class="td-right">
									<input type="text" id="balDate" name="balDate" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">会计日期</td>
								<td class="td-right">
								    <input type="text" id="workDate" name="workDate"  readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">红包日期</td>
								<td class="td-right">
									<input type="text" id="redenvDate" name="redenvDate" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">红包编号</td>
								<td class="td-right">
									<input type="text" id="redenvId" name="redenvId" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">红包数量</td>
								<td class="td-right">
									<input type="text" id="redenvCount" name="redenvCount" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left" >红包金额</td>
								<td class="td-right">
									<input type="text" id="redenvAmt" name="redenvAmt" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">出账状态</td>
								<td class="td-right">
									<input type="text" id="transStatus" name="transStatus" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>	
								<td class="td-left">入账成功个数</td>
								<td class="td-right">
									<input type="text" id="inSuccessCount" name="inSuccessCount" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">入账成功金额</td>
								<td class="td-right">
									<input type="text" id="inSuccessAmt" name="inSuccessAmt" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">入账失败个数</td>
								<td class="td-right">
									<input type="text" id="inFailureCount" name="inFailureCount" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">入账失败金额</td>
								<td class="td-right">
									<input type="text" id="inFailureAmt" name="inFailureAmt" readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">未入账个数</td>
								<td class="td-right">
									<input type="text" id="notInCount" name="notInCount" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">未入账金额</td>
								<td class="td-right">
									<input type="text" id="notInAmt" name="notInAmt"  readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">退款金额</td>
								<td class="td-right">
									<input type="text" id="refundAmt" name="refundAmt"  readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">退款状态</td>
								<td class="td-right">
									<input type="text" id="refundStatus" name="refundStatus"  readonly="readonly" clasS="width-90">
								</td>
							</tr>
							<tr>
								<td class="td-left">对账状态</td>
								<td class="td-right">
									<input type="text" id="balStatus" name="balStatus" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">对账备注</td>
								<td class="td-right">
									<textarea rows="2" cols="" id="balMemo" name="balMemo" readonly="readonly" clasS="width-90"></textarea>
								</td>
							</tr>
							<tr>
								<td class="td-left">处理人</td>
								<td class="td-right">
									<input type="text" id="dealUser" name="dealUser" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">处理状态</td>
								<td class="td-right">
									<select id="dealStatus" name="dealStatus" clasS="width-90">
										<option value="01">待处理</option>
										<option value="00">处理完成</option>
										<option value="99">无需处理</option>
									</select>
								</td>
							<tr>
								<td class="td-left">处理时间</td>
								<td class="td-right">
									<input type="text" id="dealDatetime" name="dealDatetime" readonly="readonly" clasS="width-90">
								</td>
								<td class="td-left">处理备注</td>
								<td class="td-right">
									<textarea rows="2" cols="" id="dealMemo" name="dealMemo" clasS="width-90"></textarea>
								</td>
							</tr>
							
			            </table>	             
			         </div>
			         <div class="modal-footer">
			            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			            <button type="button"  id="dealRedenvBtn"  class="btn btn-primary dealRedenvBtn">提交</button>
			         </div>
			      </div><!-- /.modal-content -->
			     </div>
			</div><!-- /.modal -->
		</div><!-- /.page-content -->		
<script type="text/javascript">

	function loadResultSummary(){
		$('.search-table #transStatus').val($('.search-table #transStatusTemp').val());
		$('.search-table #refundStatus').val($('.search-table #refundTemp').val());
		$('.search-table #balStatus').val($('.search-table #balStatusTemp').val());
		$('.search-table #dealStatus').val($('.search-table #dealStatusTemp').val());
	}

	 $("#resultStatisticReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC%>"
	 });
	 
	 $("#resultEqualReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_EQUAL%>";
	 });
	 
	 $("#resultFailureReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_FAILURE%>";
	 });
	 
	 $("#redenvDoubtReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.REDENV_DOUBT%>";
	 });
	 
	 $("#sevenDoubtReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.SEVEN_DOUBT%>";
	 });

	 $("#resultSummaryReport").click(function(){
		 window.location.href="<%=request.getContextPath()+RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY%>";
	 });
	 
	$(function () {	
		
		var resultSummaryList = ${resultSummaryList};
		var resultSummaryTrList = $('tr.summary');
		$.each(resultSummaryList, function(idx, obj){
			$.data(resultSummaryTrList[idx], 'summary', obj);
		});	
		
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		//按条件查询
		$("#searchSubmit").click(function(){
			var balDate =$("#workBeginDate").val();
			var endBalTime= $("#workEndDate").val();
			if(''!=balDate&&''!=endBalTime){
				if(balDate > endBalTime) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			}
		});
		
		//弹出修改框
	    $('.dealRedenvLink').click(function(){
			var summary = $.data($(this).parent().parent()[0], 'summary');
	       $('#dealRedenvModal').on('show.bs.modal', function () {
				$('#dealRedenvModal #batchId').val(summary.batchId);
				$('#dealRedenvModal #balDate').val(summary.balDate);
				$('#dealRedenvModal #workDate').val(summary.workDate);
				$('#dealRedenvModal #redenvDate').val(summary.redenvDate);
				$('#dealRedenvModal #redenvId').val(summary.redenvId);
				$('#dealRedenvModal #redenvCount').val(summary.redenvCount);
				$('#dealRedenvModal #redenvAmt').val(summary.redenvAmt);
				$('#dealRedenvModal #transStatus').val(summary.transStatus);
				$('#dealRedenvModal #inSuccessCount').val(summary.inSuccessCount);
				$('#dealRedenvModal #inSuccessAmt').val(summary.inSuccessAmt);
				$('#dealRedenvModal #inFailureCount').val(summary.inFailureCount);
				$('#dealRedenvModal #inFailureAmt').val(summary.inFailureAmt);
				$('#dealRedenvModal #notInCount').val(summary.notInCount);
				$('#dealRedenvModal #notInAmt').val(summary.notInAmt);
				$('#dealRedenvModal #refundAmt').val(summary.refundAmt);
				$('#dealRedenvModal #refundStatus').val(summary.refundStatus);
				$('#dealRedenvModal #balStatus').val(summary.balStatus);
				$('#dealRedenvModal #balMemo').val(summary.balMemo);
				$('#dealRedenvModal #dealStatus').val(summary.dealStatus);
				$('#dealRedenvModal #dealMemo').val(summary.dealMemo);
				$('#dealRedenvModal #dealUser').val(summary.dealUser);
				$('#dealRedenvModal #dealDatetime').val(summary.dealDatetime);

				if(summary.dealStatus=='01'){
					$('#dealRedenvModal #dealStatus').attr('disabled',false); 
					$('#dealRedenvBtn').show(); 
				}else{
					$('#dealRedenvModal #dealStatus').attr('disabled',true); 
					$('#dealRedenvBtn').hide(); 
				}  
				
			});
	       $('#dealRedenvModal').on('hide.bs.modal', function () {
				// 清除
	    	   
			});
		}); 
		
		$('.approveBtn').click(function(){
			var redenvId = $('#dealRedenvModal #redenvId').val();
			if(kong.test(redenvId)) {
				$.gyzbadmin.alertFailure('红包编号不可为空');
				return;
			}
			var dealStatus = $('#dealRedenvModal #dealStatus').val();
			if(kong.test(dealStatus)||dealStatus=='01'||dealStatus=='99') {
				$.gyzbadmin.alertFailure('请选择处理状态');
				return;
			}
			var merchantCustId = $('#approveModal #merchantCustId').val();
			if(kong.test(merchantCustId)) {
				$.gyzbadmin.alertFailure('商户编号不能为空');
				return;
			}
			var dealMemo = $('#dealRedenvModal #dealMemo').val();
			
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=RedenvResultPath.BASE + RedenvResultPath.DEAL_REDENV %>', {
					'redenvId'		:redenvId,
					'dealStatus' 	:dealStatus,
					'dealMemo' 		:dealMemo
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#dealRedenvModal').modal('hide');
						$.gyzbadmin.alertSuccess('处理成功', null, function(){
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
			var balDate = $('.search-table #balDate').val();
			var redenvDate = $('.search-table #redenvDate').val();
			var redenvId = $('.search-table #redenvId').val();
			var workBeginDate = $('.search-table #workBeginDate').val();
			var workEndDate = $('.search-table #workEndDate').val();
			var transStatus = $('.search-table #transStatus').val();
			var refundStatus = $('.search-table #refundStatus').val();
			var balStatus = $('.search-table #balStatus').val();
			var dealStatus = $('.search-table #dealStatus').val();
			var src ="<%= request.getContextPath()+ RedenvResultPath.BASE+RedenvResultPath.RESULT_SUMMARY_EXPORT%>?balDate="+
				balDate+
				"&redenvDate="+
				redenvDate+
				"&redenvId="+
				redenvId+
				"&transStatus="+
				transStatus+
				"&refundStatus="+
				refundStatus+
				"&balStatus="+
				balStatus+
				"&dealStatus="+
				dealStatus+
				"&workBeginDate="+
				workBeginDate+
				"&workEndDate="+
				workEndDate;
			$(".exportBut").attr("href",src);
		});
    	$('#myTab li:eq(5) a').tab('show');	
	   });
	</script>
</body>
</html>					