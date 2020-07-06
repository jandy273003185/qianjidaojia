<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.qifenqian.bms.meeting.prizewin.PrizeWinPath"%>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<html>
<head>
<meta charset="utf-8" />
<title>幸运用户查询</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}
</style>
</head>
<script type="text/javascript">
function loadLottery(){
	$(".search-table #status").val($(".search-table #statusTemp").val());
}

$(function() {
	
	var lotteryList = ${lotteryList};
	var lotteryTrList = $("tr.lottery");
	
	$.each(lotteryList, function(i, value) {
		$.data(lotteryTrList[i], "lottery", value);
	});
	
	$(".clearLottery").click(function(){
		$(".search-table #winCustPhone").val('');
		$(".search-table #instDate").val('');
		$(".search-table #status").val('');
		$(".search-table #prizeName").val('');
	});
	
	$(".exportLottery").click(function(){
		var prizeName = $(".search-table #prizeName").val();
		var winCustPhone = $(".search-table #winCustPhone").val();
		var instDate = $(".search-table #instDate").val();
		var status = $(".search-table #status").val();
		
		var src ="<%=request.getContextPath()+ PrizeWinPath.BASE+PrizeWinPath.LOTTERYEXPORT%>?prizeName="+
		prizeName+"&winCustPhone="+winCustPhone+"&instDate="+instDate+"&status="+status;
		$(".exportLottery").attr("href",src);
	});
	
	// 弹出删除层准备工作
	$('.deleteLotteryLink').click(function(){
		var lottery = $.data($(this).parent().parent()[0], 'lottery');
		
		$('#deleteLotteryModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteLotteryModal span.winId').html(lottery.winId);
			$('#deleteLotteryModal #winId').val(lottery.winId);
		});
		$('#deleteLotteryModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteLotteryModal span.winId').empty();
			$('#deleteLotteryModal #winId').val('');
		});
	});
	
	// 删除
	$('.deleteLotteryBtn').click(function(){
		var winId = $('#deleteLotteryModal #winId').val();
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=PrizeWinPath.BASE + PrizeWinPath.DELETE%>', {
				'winId' 	: winId
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#deleteLotteryModal').modal('hide');
					$.gyzbadmin.alertSuccess('删除成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message, null, function(){
						window.location.reload();
					});
				}
			}, 'json'
		);
	});
	
});
</script>

<body onload="loadLottery()">
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
							<form action='<c:url value="<%=PrizeWinPath.BASE + PrizeWinPath.LIST%>"/>' method="post">
								<table class="search-table">
									<tr>
										</td>
										<td class="td-left">中奖客户手机号码</td>
										<td class="td-right">
										<span class="input-icon">
											<input type="text" id="winCustPhone" name="winCustPhone"  value="${queryBean.winCustPhone }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
										<td class="td-left">奖项名称</td>
										<td class="td-right">
										<span class="input-icon">
											<input type="text" id="prizeName" name="prizeName"  value="${queryBean.prizeName }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
									</tr>
									<tr>
										<td class="td-left">活动日期</td>
										<td class="td-right">
											<input type="text" id="instDate" name="instDate" value="${queryBean.instDate }" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/> 
										</td>
										<td class="td-left">状态</td>
										<td class="td-right">
										    <input type="hidden" id="statusTemp" name="statusTemp"  value="${queryBean.status}"/>
											<select id="status" name="status">
												<option value="">- 请选择 -</option>
												<option value="WAIT_RECEIVE">- 待领取 -</option>
												<option value="HAVE_RECEIVED">- 已领取 -</option>
												<option value="TAKE_BACK">- 已收回 -</option>
												<option value="DISABLE">- 无效 -</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<button type="submit" class="btn btn-purple btn-sm">
												查询 <i class="icon-search icon-on-right bigger-110"></i>
											</button>
											<button class="btn btn-purple btn-sm btn-margin clearLottery">
												清空 <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=PrizeWinPath.BASE + PrizeWinPath.LOTTERYEXPORT%>">
												<a class="btn btn-purple btn-sm exportLottery">
													导出报表
												</a> 
											</gyzbadmin:function>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">中奖用户列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout: fixed;">
									<thead>
										<tr>
											<th width="5%">中奖编号</th>
											<th width="8%">奖项名称</th>
											<th width="8%">中奖客户姓名</th>
											<th width="8%">中奖手机号码</th>
											<th width="5%">中奖金额</th>
											<th width="12%">有效截止时间</th>
											<th width="8%">活动日期</th>
											<th width="8%">创建人</th>
											<th width="12%">创建时间</th>
											<th width="8%">状态</th>
											<th width="12%">领取时间</th>
											<th width="5%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${lotteryList}" var="lottery">
											<tr class="lottery">
												<td>${lottery.winId }</td>
												<td>${lottery.prizeName }</td>
												<td>${lottery.winCustName }</td>
												<td>${lottery.winCustPhone }</td>
												<td>${lottery.winAmount }</td>
												<td><fmt:formatDate value="${lottery.effectiveDeadline}" type="both"/></td>
												<td>${lottery.instDate }</td>
												<td>${lottery.instUser }</td>
												<td>${lottery.instDatetime}</td>
												<td>
													<c:if test="${lottery.status=='WAIT_RECEIVE'}">待领取</c:if>
													<c:if test="${lottery.status=='HAVE_RECEIVED'}">已领取</c:if>
													<c:if test="${lottery.status=='TAKE_BACK'}">已回收</c:if>
													<c:if test="${lottery.status=='DISABLE'}">无效</c:if>
												</td>
												<td><fmt:formatDate value="${lottery.lupdDatetime}" type="both"/></td>
												<td>
													<gyzbadmin:function url="<%=PrizeWinPath.BASE + PrizeWinPath.DELETE%>">
														<a href="#" class="tooltip-error deleteLotteryLink" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteLotteryModal">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty lotteryList}">
											<tr>
												<td colspan="12" align="center">
												<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty lotteryList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<%@ include file="/include/bottom.jsp"%>
			</div>
			<%@ include file="/include/setting.jsp"%>
		</div>
		<%@ include file="/include/up.jsp"%>
	</div>
	
	<div class="modal fade" id="deleteLotteryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除中奖用户信息</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该中奖用户信息[<span class="winId"></span>]么？</font>
		         	<input type="hidden" id="winId" />
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteLotteryBtn" >确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		 </div>
	</div><!-- /.modal -->
</body>
</html>