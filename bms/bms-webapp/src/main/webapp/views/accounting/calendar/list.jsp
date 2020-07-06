<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.calendar.CalendarPath" %> 
<html>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' /> --%>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<head>
	<meta charset="utf-8" />
	<title>节假日管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadCalendar()">
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
				
				<div class="row" id="selectCalendar">
					<div class="col-xs-12">
					<!-- 查询条件 -->
					<input type="hidden" value="${queryBean.isWork}" id="isWorkTemp">
						<form  action='<c:url value="<%=CalendarPath.BASE + CalendarPath.LIST %>"/>' method="post">
						<table class="search-table" >
							<tr>
								<td class="td-left" >日期</td>
								<td class="td-right"  >
									 <input type="text" id="date" name="date" readonly="readonly" value="${queryBean.date }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'2032-12-20'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" />
								</td>
								<td class="td-left" >是否工作日</td>
								<td class="td-right">
									<select name="isWork" id="isWork" >
										  <option  value="">- 请选择 -</option>
										  <option  value="Y">- 是 -</option>
										  <option  value="N">- 否 -</option>
									</select>
								</td>
								</tr>
								<tr>
								<td colspan="4" align="center" >
									<span class="input-group-btn">
										<gyzbadmin:function url="<%=CalendarPath.BASE + CalendarPath.LIST %>">
										<button type="submit" class="btn btn-purple btn-sm">
												查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button> 
										<button  class="btn btn-purple btn-sm btn-margin clearCalendar" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</gyzbadmin:function>
									</span>
								</td>
							</tr>
						</table>
						</form>
						<div class="list-table-header" >
							日期列表
						</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th width="5%">编号</th>
										<th width="10%">日期</th>
										<th width="10%">星期</th>
										<th width="10%">日期详情</th>
										<th width="5%">工作日</th>
										<th width="10%">备注</th>
										<th width="10%">创建人</th>
										<th width="12%">创建时间</th>
										<th width="10%">更新人</th>
										<th width="12%">更新时间</th>
										<th width="6%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${calendarList}" var="calendarVo">
										<tr class="calendarVo">
											<td>${calendarVo.calendarId}</td>
											<td>${calendarVo.date}</td>
											<td>${calendarVo.dateType}</td>
											<td>${calendarVo.holiday}</td>
											<td>
												<c:if test="${calendarVo.isWork=='Y' }">是</c:if>
												<c:if test="${calendarVo.isWork=='N' }">否</c:if>
											</td>
											<td>${calendarVo.comment}</td>
											<td>${calendarVo.instUserName}</td>
											<td>
												<fmt:formatDate value="${calendarVo.instDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>${calendarVo.lastupdateUser}</td>
											<td>
												<fmt:formatDate value="${calendarVo.lastupdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>
												<gyzbadmin:function url="<%=CalendarPath.BASE + CalendarPath.EDIT%>">
													<a href="#" class="tooltip-success updateCalendarLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#updateCalendarModal" >
													<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>	
												</gyzbadmin:function>		
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty calendarList}">
										<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty calendarList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
					<!-- 修改 -->
				<div class="modal fade" id="updateCalendarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">节假日修改</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="30%">编号</td>
									<td class="td-right" width="70%">
										<input type="text" id="calendarId" name="calendarId" readonly="readonly" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">日期<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="date" name="date" readonly="readonly" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">星期<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="dateType" name="dateType" readonly="readonly" maxlength="200" clasS="width-80">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >日期详情</td>
									<td class="td-right">
										<input type="text" id="holiday" name="holiday"  maxlength="200" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">是否工作日<span style="color:red">*</span></td>
									<td class="td-right">
										<select name="isWork" id="isWork" >
										  <option  selected ="selected" value="" >- 请选择 -</option>
										  <option  value="Y">- 是 -</option>
										  <option  value="N">- 否 -</option>
									</select>
									</td>
								</tr>
								<tr>
									<td class="td-left">修改备注</td>
									<td class="td-right">
										<textarea name="comment" id="comment" rows="3" clasS="width-90"></textarea>  
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary updateCalendarBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
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
</body>
<script type="text/javascript">

function loadCalendar(){
	$('.search-table #isWork').val($("#isWorkTemp").val());
}

$(function(){
	var calendarListJson = '<c:out value="${gyzb:toJSONString(calendarList)}" escapeXml="false"/>';
	
	var calendarTrList = $('tr.calendarVo');
	
	$.each($.parseJSON(calendarListJson), function(idx, obj){
		$.data(calendarTrList[idx], 'calendarVo', obj);
	});	
	
	
	$('.clearCalendar').click(function(){
		$('.search-table #date').val('');
		$('.search-table #isWork').val('');

	}) 
	//弹出修改框
    $('.updateCalendarLink').click(function(){
    	
		var calendarVo = $.data($(this).parent().parent()[0], 'calendarVo');
       $('#updateCalendarModal').on('show.bs.modal', function () {
			$('#updateCalendarModal #calendarId').val(calendarVo.calendarId);
			$('#updateCalendarModal #date').val(calendarVo.date);
			$('#updateCalendarModal #dateType').val(calendarVo.dateType);
			$('#updateCalendarModal #holiday').val(calendarVo.holiday);
			$('#updateCalendarModal #isWork').val(calendarVo.isWork);
			$('#updateCalendarModal #comment').val(calendarVo.comment);
		});
       
       $('#updateCalendarModal').on('hide.bs.modal', function () {
			// 清除
    	    $('#updateCalendarModal #calendarId').val('');
			$('#updateCalendarModal #date').val('');
			$('#updateCalendarModal #dateType').val('');
			$('#updateCalendarModal #holiday').val('');
			$('#updateCalendarModal #isWork').val('');
			$('#updateCalendarModal #comment').val('');
		});
	}); 
	
	$('.updateCalendarBtn').click(function(){
		
		var calendarId = $('#updateCalendarModal #calendarId').val();
		
		if(kong.test(calendarId)) {
			$.gyzbadmin.alertFailure('编号不可为空');
			return;
		}
		var date = $('#updateCalendarModal #date').val();
		
		var holiday = $('#updateCalendarModal #holiday').val();
		
		var isWork = $('#updateCalendarModal #isWork').val();
		if(kong.test(isWork)) {
			$.gyzbadmin.alertFailure('是否工作日不可为空');
			return;
		}
		
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=CalendarPath.BASE + CalendarPath.EDIT %>', {
				'calendarId'	:calendarId,
				'date'			:date,
				'holiday'		: holiday,
				'isWork'		: isWork,
				'comment'		: $("#updateCalendarModal #comment").val()
			}, function(data) {
				$.unblockUI();
				if(data.result == 'success'){
					$('#updateCalendarModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('修改失败:' + data.message);
				}
			}, 'json'
		);
	});
})

</script>
</html>