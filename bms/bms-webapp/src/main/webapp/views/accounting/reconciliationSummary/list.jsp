
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.reconciliationSummary.controller.ReconciliationSummaryPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>对账汇总</title>
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
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active">
							   			<form id="tradeForm" action='<c:url value="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left" width="10%">对账日期</td>
												<td class="td-right" width="15%">
													<span class="input-icon">
														<input type="text" name="createTime" id="createTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">渠道</td>
												<td class="td-right" width="15%">
													<span class="input-icon">
														<select name="paychannelType" id="paychannelType">
															<option value="">--请选择--</option>

															<c:forEach items="<%=PaychannelType.values()%>" var="status">
																<option value="${status}" <c:if test="${status == queryBean.paychannelType}">selected</c:if>>${status.text}</option>

															</c:forEach>
														</select>
													</span>
												</td>
												<td align="left">
													<button type="button" class="btn btn-purple btn-sm btn-margin exportBut" style="margin: 0 0 0 30px">对账</button>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST%>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" onclick="this.submit();" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=ReconciliationSummaryPath.BASE%>">
															<span class="input-group-btn" style="display:inline;">
														 
															
															</span>
														</gyzbadmin:function>
													</span>
												</td>
												
											</tr>
											<tr>
											<td  colspan="6" align="left"><font size="2"><strong>掉单</strong></font>：<font size="2">我们有银行没有</font>   <font size="2"><strong>丢单</strong>：我们没有银行有</font></td> 
											</tr>
										</table>
										</form>
										<div class="list-table-header">对账汇总</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>渠道</th>
															<th>成功笔数</th>
															<th>成功总金额</th>
															<th>掉单笔数</th>
															<th>掉单金额</th>
															<th>丢单笔数</th>
															<th>丢单金额</th>	
															<th>差错成功笔数</th>
															<th>差错成功金额</th>
															<th>对账日期</th>

														</tr>
													</thead>
													<tbody>
													<c:forEach items="${ReconciliationSummaryList }" var="reconciliationSummary">
														<tr class="statistic">
															<td>
															<a  href="#" data-toggle="tab" onclick="selectResult('${reconciliationSummary.paychannelType}', '', '<fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>');">
																<c:forEach items="<%=PaychannelType.values()%>" var="status">
																	<c:if test="${status == reconciliationSummary.paychannelType}">${status.text}</c:if>
																</c:forEach>
															</a>
															</td>
															
															<c:if test="${reconciliationSummary.success_total eq null || reconciliationSummary.success_total eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${reconciliationSummary.success_total ne null && reconciliationSummary.success_total ne '0'}">
															<td><a href="#" data-toggle="tab" onclick="selectResult('${reconciliationSummary.paychannelType}', 'SUCCESS', '<fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>');">${reconciliationSummary.success_total}</a></td>
															<td>${reconciliationSummary.success_amt}</td>
															</c:if>
															
															<c:if test="${reconciliationSummary.lack_total eq null || reconciliationSummary.lack_total eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${reconciliationSummary.lack_total ne null && reconciliationSummary.lack_total ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${reconciliationSummary.paychannelType}', 'LACK', '<fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>');">${reconciliationSummary.lack_total }</a>
															</td>
															<td>${reconciliationSummary.lack_amt}</td>
															</c:if>
															
															<c:if test="${reconciliationSummary.lose_total eq null || reconciliationSummary.lose_total eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${reconciliationSummary.lose_total ne null && reconciliationSummary.lose_total ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${reconciliationSummary.paychannelType}', 'LOSE', '<fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>');">${reconciliationSummary.lose_total }</a>
															</td>
															<td>${reconciliationSummary.lose_amt}</td>
															</c:if>
															
															<c:if test="${reconciliationSummary.diff_total eq null || reconciliationSummary.diff_total eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${reconciliationSummary.diff_total ne null && reconciliationSummary.diff_total ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${reconciliationSummary.paychannelType}', 'DIFF_SUCCESS', '<fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>');">${reconciliationSummary.diff_total}</a>
															</td>
															<td>${reconciliationSummary.diff_amt}</td>
															</c:if>
															
															<td>
																 <fmt:formatDate value="${reconciliationSummary.create_time }" pattern="yyyy-MM-dd"/>
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty ReconciliationSummaryList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty ReconciliationSummaryList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									   </div>
									  
									   <div class="tab-pane fade" id="impeach">
									   </div>
									   <div class="tab-pane fade" id="jh">
									   </div>
									   <div class="tab-pane fade" id="slip">
									   </div>
									   <div class="tab-pane fade" id="fit">
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
	<script type="text/javascript">
	/**交易明細*/
	function selectResult(paychannelType, reconResult, createTime) {
		window.location.href="<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.DETAILS%>?paychannelType=" + paychannelType + "&createTime=" + createTime
		+ (reconResult == "" ? "" : "&reconResult=" + reconResult);
	}

	//清空
	$('.clearBtn').click(function(){
		$(':input','#tradeForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	
	//对账
	$('.exportBut').click(function(){
		var createTime = $("#createTime").val();
		var paychannelType = $("#paychannelType").val();
		
		if($.trim(createTime) == "") {
			alert("对账日期不能为空");
			return;
		} else if($.trim(paychannelType) == "") {
			alert("渠道类型不能为空");
			return;
		}
		$.blockUI();
		var url = "<%= request.getContextPath()+ ReconciliationSummaryPath.BASE+ReconciliationSummaryPath.RECONCILIATION%>?createTime=" + createTime + "&paychannelType=" + paychannelType;
		$.post(url, function(result){
			$.unblockUI();
			if(result == 'success'){
				window.location.href = "<%=request.getContextPath()+ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST%>";
				alert("对账成功");
			} else {
				alert("对账失败");
			}
		});
	});
	
	var d = new Date();
    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
    $("#createTime").val('${queryBean.createTime}');
	</script>
</body>
</html>					