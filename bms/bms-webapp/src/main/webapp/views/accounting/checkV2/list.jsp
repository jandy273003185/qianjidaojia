
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>
<%@page import="com.qifenqian.bms.accounting.checkV2.CheckV2Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>对账汇总（新版）</title>
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
							   			<form id="tradeForm" action='<c:url value="<%=CheckV2Path.BASE + CheckV2Path.LIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left" width="10%">对账日期</td>
												<td class="td-right" width="15%">
													<span class="input-icon">
														<input type="text" name="checkDateStr" id="checkDateStr"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
												<td class="td-left" width="10%">交易渠道</td>
												<td class="td-right" width="15%">
													<span class="input-icon">
														<select name="channelType" id="channelType">
															<option value="">--请选择--</option>

															<c:forEach items="<%=PaychannelType.values()%>" var="status">
																<option value="${status}" <c:if test="${status == checkStatsRequestBean.channelType}">selected</c:if>>${status.text}</option>
															</c:forEach>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=CheckV2Path.BASE + CheckV2Path.LIST%>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" onclick="this.submit();" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
													</span>
												</td>
												
											</tr>
											<tr>
											<td  colspan="6" align="left"><font size="2"><strong>掉单</strong></font>：<font size="2">我们有银行没有</font>   <font size="2"><strong>丢单</strong>：我们没有银行有</font></td> 
											</tr>
										</table>
										</form>
										<div class="list-table-header">对账汇总（新版）</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>对账日期</th>
															<th>交易渠道</th>
															<th>对账总笔数</th>
															<th>对账总金额</th>
															<th>成功笔数</th>
															<th>成功总金额</th>
															<th>掉单笔数</th>
															<th>掉单总金额</th>
															<th>丢单笔数</th>
															<th>丢单总金额</th>	
															<th>操作</th>	
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${checkStatsList}" var="checkStats">
														<tr class="statistic">
															<td>
																 <fmt:formatDate value="${checkStats.checkDate }" pattern="yyyy-MM-dd"/>
															</td>														
															<td>
															<c:forEach items="<%=PaychannelType.values()%>" var="status">
																<c:if test="${status == checkStats.channelType}">${status.text}</c:if>
															</c:forEach>
															</td>
															
															<c:if test="${checkStats.checkAllNum eq null || checkStats.checkAllNum eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${checkStats.checkAllNum ne null && checkStats.checkAllNum ne '0'}">
															<td><a href="#" data-toggle="tab" onclick="selectResult('${checkStats.channelType}', '', '<fmt:formatDate value="${checkStats.checkDate}" pattern="yyyy-MM-dd"/>');">${checkStats.checkAllNum}</a></td>
															<td>${checkStats.checkAllAmt}</td>
															</c:if>
															
															<c:if test="${checkStats.checkSuccessNum eq null || checkStats.checkSuccessNum eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${checkStats.checkSuccessNum ne null && checkStats.checkSuccessNum ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${checkStats.channelType}', 'SUCCESS', '<fmt:formatDate value="${checkStats.checkDate }" pattern="yyyy-MM-dd"/>');">${checkStats.checkSuccessNum }</a>
															</td>
															<td>${checkStats.checkSuccessTradeAmt}</td>
															</c:if>
															
															<c:if test="${checkStats.checkClackNum eq null || checkStats.checkClackNum eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${checkStats.checkClackNum ne null && checkStats.checkClackNum ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${checkStats.channelType}', 'CLACK', '<fmt:formatDate value="${checkStats.checkDate }" pattern="yyyy-MM-dd"/>');">${checkStats.checkClackNum }</a>
															</td>
															<td>${checkStats.checkClackAmt}</td>
															</c:if>
															
															<c:if test="${checkStats.checkPlackNum eq null || checkStats.checkPlackNum eq '0'}">
															<td>0</td>
															<td>0.00</td>
															</c:if>
															<c:if test="${checkStats.checkPlackNum ne null && checkStats.checkPlackNum ne '0'}">
															<td>
																<a href="#" data-toggle="tab" onclick="selectResult('${checkStats.channelType}', 'PLACK', '<fmt:formatDate value="${checkStats.checkDate }" pattern="yyyy-MM-dd"/>');">${checkStats.checkPlackNum}</a>
															</td>
															<td>${checkStats.checkPlackAmt}</td>
															</c:if>
															
															<td>
																 
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty checkStatsList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty checkStatsList}">
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
	function selectResult(channelType, checkResult, checkDateStr) {
		window.location.href="<%=request.getContextPath()+CheckV2Path.BASE + CheckV2Path.DETAIL%>?channelType=" + channelType + "&checkDateStr=" + checkDateStr
		+ (checkResult == "" ? "" : "&checkResult=" + checkResult);
	}
	//清空
	$('.clearBtn').click(function(){
		$(':input','#tradeForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	var d = new Date();
    $("#createTime").val('${queryBean.createTime}');
</script>
</body>
</html>					