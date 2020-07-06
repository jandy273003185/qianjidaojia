<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.meeting.activity.ActivityPath" %> 
<%@page import="com.qifenqian.bms.meeting.luckdraw.LuckDrawPath" %> 
<html>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<head>
	<meta charset="utf-8" />
	<title>奖项管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		var activityListJson = ${activityList};
		var activityTrList = $('tr.activity');
		$.each(activityListJson, function(idx, obj){
			$.data(activityTrList[idx], 'activity', obj);
		});	
		
		$('.clearActivity').click(function(){
			$('.search-table #activityNo').val('');
			$('.search-table #activityName').val('');
		}) 
		 
     $('.addActivityBtn').click(function(){
			var activityName = $('#addActivityModal #activityName').val();
			if(kong.test(activityName)) {
				$.gyzbadmin.alertFailure('活动名称不可为空');
				return;
			}	
			var activityNo = $('#addActivityModal #activityNo').val();
			if(kong.test(activityNo)) {
				$.gyzbadmin.alertFailure('活动编码不可为空');
				return;
			}	
			var activityCode = $('#addActivityModal #activityCode').val();
			if(kong.test(activityCode)) {
				$.gyzbadmin.alertFailure('活动英文编码不可为空');
				return;
			}	
			var permitType = $('#addActivityModal #permitType').val();
			if(kong.test(permitType)) {
				$.gyzbadmin.alertFailure('许可类型不可为空');
				return;
			}
			var startDate = $('#addActivityModal #startDate').val();
			var endDate = $('#addActivityModal #endDate').val();
			var activityPlace = $('#addActivityModal #activityPlace').val();
			var permitValue = $('#addActivityModal #permitValue').val();
			var memo = $('#addActivityModal #memo').val();
			var status = $('#addActivityModal #status').val();
			
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=ActivityPath.BASE + ActivityPath.ADD %>', {
					'activityName'		: activityName,
					'activityNo'		: activityNo,
					'activityCode'		: activityCode,
					'permitType'		: permitType,
					'permitValue'		: permitValue,
					'startDate'			: startDate,
					'endDate'			: endDate,
					'activityPlace'		: activityPlace,
					'memo'				: memo,
					'status'			: status
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#addActivityModal').modal('hide');
						$.gyzbadmin.alertSuccess('新增成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(date.message, null, function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
			
		});
		
		/**加载修改**/
		 $('.updateActivityLink').click(function(){
			 
		   var activity = $.data($(this).parent().parent()[0], 'activity');
		   
		   $('#updateActivityModal').on('show.bs.modal', function () {
				$('#updateActivityModal #activityId').val(activity.activityId);
				$('#updateActivityModal #activityName').val(activity.activityName);
				$('#updateActivityModal #activityNo').val(activity.activityNo);
				$('#updateActivityModal #activityCode').val(activity.activityCode);
				$('#updateActivityModal #permitType').val(activity.permitType);
				$('#updateActivityModal #permitValue').val(activity.permitValue);
				$('#updateActivityModal #startDate').val(activity.startDate);
				$('#updateActivityModal #endDate').val(activity.endDate);
				$('#updateActivityModal #activityPlace').val(activity.activityPlace);
				$('#updateActivityModal #memo').val(activity.memo);
				$('#updateActivityModal #status').val(activity.status);
			});
	       $('#updateActivityModal').on('hide.bs.modal', function () {
	    	    $('#updateActivityModal #activityId').val('');
				$('#updateActivityModal #activityName').val('');
				$('#updateActivityModal #activityNo').val('');
				$('#updateActivityModal #activityCode').val('');
				$('#updateActivityModal #permitType').val('');
				$('#updateActivityModal #permitValue').val('');
				$('#updateActivityModal #startDate').val('');
				$('#updateActivityModal #endDate').val('');
				$('#updateActivityModal #activityPlace').val('');
				$('#updateActivityModal #memo').val('');
				$('#updateActivityModal #status').val('');
			});
	      
		}); 
		
		/**奖项新增**/
		$('.updateActivityBtn').click(function(){
			
			var activityId = $('#updateActivityModal #activityId').val();
			var activityName = $('#updateActivityModal #activityName').val();
			if(kong.test(activityName)) {
				$.gyzbadmin.alertFailure('活动名称不可为空');
				return;
			}	
			var activityNo = $('#updateActivityModal #activityNo').val();
			if(kong.test(activityNo)) {
				$.gyzbadmin.alertFailure('活动编码不可为空');
				return;
			}	
			var activityCode = $('#updateActivityModal #activityCode').val();
			if(kong.test(activityCode)) {
				$.gyzbadmin.alertFailure('活动英文编码不可为空');
				return;
			}	
			var permitType = $('#updateActivityModal #permitType').val();
			if(kong.test(permitType)) {
				$.gyzbadmin.alertFailure('许可类型不可为空');
				return;
			}
			var startDate = $('#updateActivityModal #startDate').val();
			var endDate = $('#updateActivityModal #endDate').val();
			var activityPlace = $('#updateActivityModal #activityPlace').val();
			var permitValue = $('#updateActivityModal #permitValue').val();
			/** 备注 */
			var memo = $('#updateActivityModal #memo').val();
			/** 状态：VALID有效/DISABLE失效 */
			var status = $('#updateActivityModal #status').val();
			
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=ActivityPath.BASE + ActivityPath.EDIT %>', {
				    'activityId'			: activityId,
				    'activityName'		: activityName,
					'activityNo'		: activityNo,
					'activityCode'		: activityCode,
					'permitType'		: permitType,
					'permitValue'		: permitValue,
					'startDate'			: startDate,
					'endDate'			: endDate,
					'activityPlace'		: activityPlace,
					'memo'				: memo,
					'status'			: status
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#updateActivityModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(date.message, null, function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		})
		
		$('.prizeListLick').click(function(){
			var activityId= $(this).parent().find(':hidden[name="activityId"]').val();
			$.blockUI();
			$.gyzbadmin.postForm('<c:url value="<%=LuckDrawPath.BASE + LuckDrawPath.PRIZE_LIST %>" />',{
				'activityId':activityId
				}
			);
		});
	})
</script>
<body>
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
					<form  action='<c:url value="<%=ActivityPath.BASE + ActivityPath.LIST %>"/>' method="post">
						<table class="search-table" >
							<tr>
								<td class="td-left" >活动名称</td>
								<td class="td-right"  >
									<span class="input-icon">
									 <input type="text" id="activityName" name="activityName" value="${queryBean.activityName }" />
									 <i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >活动编码</td>
								<td class="td-right">
									<span class="input-icon">
									 <input type="text" id="activityNo" name="activityNo" value="${queryBean.activityNo }" />
									 <i class="icon-leaf blue"></i>
									</span>
								</td>
								</tr>
								<tr>
								<td colspan="4" align="center" >
									<span class="input-group-btn">
										<gyzbadmin:function url="<%=ActivityPath.BASE + ActivityPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
										<button  class="btn btn-purple btn-sm btn-margin clearActivity" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
										<gyzbadmin:function url="<%=ActivityPath.BASE + ActivityPath.ADD%>">
											<button class="btn btn-purple btn-sm btn-margin" data-toggle='modal' data-target="#addActivityModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
									</span>
								</td>
							</tr>
						</table>
						</form>
						<div class="list-table-header" >
							活动列表
						</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th>编号</th>
										<th>活动名称</th>
										<th>活动编码</th>
										<th>活动英文编码</th>
										<th>活动地点</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th>许可类型</th>
										<th>许可内容</th>
										<th>状态</th>
										<th>创建人</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${activityList}" var="activity">
										<tr class="Activity">
											<td>${activity.activityId}</td>
											<td>${activity.activityName}</td>
											<td>${activity.activityNo}</td>
											<td>${activity.activityCode}</td>
											<td>${activity.activityPlace}</td>
											<td>${activity.startDate}</td>
											<td>${activity.endDate}</td>
											<td>
												<c:if test="${activity.permitType == 'ALL'}">全部</c:if>
												<c:if test="${activity.permitType == 'IP'}">IP地址</c:if>
												<c:if test="${activity.permitType == 'USER'}">用户编号</c:if>	
												<c:if test="${activity.permitType == 'MAC'}">MAC地址</c:if>
											</td>
											<td>${activity.permitValue}</td>
											<td>
												<c:if test="${activity.status == 'INIT'}">初始</c:if>
												<c:if test="${activity.status == 'STAGE_ING'}">进行中</c:if>
												<c:if test="${activity.status == 'OVER'}">结束</c:if>
												<c:if test="${activity.status == 'DISABLE'}">失效</c:if>	
											</td>
											<td>${activity.instUserName}</td>
											<td><fmt:formatDate value="${activity.instDatetime}" type="both"/></td>
											<td>
												<input type="hidden" name="activityId" value="${activity.activityId }"/>
												<gyzbadmin:function url="<%=ActivityPath.BASE + ActivityPath.EDIT%>">
													<a href="#" class="tooltip-success updateActivityLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#updateActivityModal" >
													<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>	
												</gyzbadmin:function>	
												<gyzbadmin:function url="<%=LuckDrawPath.BASE + LuckDrawPath.PRIZE_LIST%>">
													<button type="button" class="btn btn-primary btn-xs prizeListLick" data-toggle='modal'>
														抽奖
													</button>	
												</gyzbadmin:function>	
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty activityList}">
										<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty activityList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 新增 -->
				<div class="modal fade" id="addActivityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">添加活动</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="20%">活动名称<span style="color:red">*</span></td>
									<td class="td-right" width="80%">
										<input type="text" id="activityName" name="activityName" maxlength="200"  clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">活动编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="activityNo" name="activityNo"  maxlength="20" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">活动英文编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="activityCode" name="activityCode"  maxlength="50" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >活动地点</td>
									<td class="td-right">
										<input type="text" id="activityPlace" name="activityPlace" maxlength="200" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >开始日期</td>
									<td class="td-right">
										<input type="text" id="startDate" name="startDate" readonly="readonly"  onfocus="WdatePicker()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >结束日期</td>
									<td class="td-right">
										<input type="text" id="endDate" name="endDate" readonly="readonly"  onfocus="WdatePicker()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >许可类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="permitType" name="permitType" clasS="width-90">
										  <option value="ALL">全部</option>
										  <option value="IP">IP地址</option>
										  <option value="USER">用户编号</option>
										  <option value="MAC">MAC地址</option>
										</select>
									</td>
								</tr>
								<tr>	
									<td class="td-left" >许可内容</td>
									<td class="td-right">
										<input type="text" id="permitValue" name="permitValue" maxlength="200"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >状态<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="status" name="status" clasS="width-90">
										  <option value="INIT">初始</option>
										  <option value="STAGE_ING">进行中</option>
										  <option value="OVER">结束</option>
										  <option value="DISABLE">失效</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left" >备注</td>
									<td class="td-right">
										<textarea  id="memo" name="memo" maxlength="200" rows="2" clasS="width-90"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary addActivityBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
			</div><!-- /.page-content -->
					<!-- 修改 -->
				<div class="modal fade" id="updateActivityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">活动修改</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<input type="hidden" id="activityId" name="activityId" readonly="readonly" clasS="width-90">
				            	<tr>
									<td class="td-left" width="20%">活动名称<span style="color:red">*</span></td>
									<td class="td-right" width="80%">
										<input type="text" id="activityName" name="activityName" maxlength="200"  clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">活动编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="activityNo" name="activityNo"  maxlength="20" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left">活动英文编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="activityCode" name="activityCode"  maxlength="50" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >活动地点</td>
									<td class="td-right">
										<input type="text" id="activityPlace" name="activityPlace" maxlength="200"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >开始日期</td>
									<td class="td-right">
										<input type="text" id="startDate" name="startDate" readonly="readonly"  onfocus="WdatePicker()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"  clasS="width-90">
									</td>
								</tr>
								<tr class="addRandomAmountMax">	
									<td class="td-left" >结束日期</td>
									<td class="td-right">
										<input type="text" id="endDate" name="endDate" readonly="readonly"  onfocus="WdatePicker()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >许可类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="permitType" name="permitType" clasS="width-90">
										  <option value="ALL">全部</option>
										  <option value="IP">IP地址</option>
										  <option value="USER">用户编号</option>
										  <option value="MAC">MAC地址</option>
										</select>
									</td>
								</tr>
								<tr>	
									<td class="td-left" >许可内容</td>
									<td class="td-right">
										<input type="text" id="permitValue" name="permitValue" maxlength="200"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >状态<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="status" name="status" clasS="width-90">
										  <option value="INIT">初始</option>
										  <option value="STAGE_ING">进行中</option>
										  <option value="OVER">结束</option>
										  <option value="DISABLE">失效</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left" >备注</td>
									<td class="td-right">
										<textarea  id="memo" name="memo" maxlength="200" rows="2" clasS="width-90"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary updateActivityBtn">提交</button>
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
</html>