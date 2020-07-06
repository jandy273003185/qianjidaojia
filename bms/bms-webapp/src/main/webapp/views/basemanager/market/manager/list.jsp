<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.market.controller.ManagerPath"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>市场部分组管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearBank').click(function(){
			$('.search-table #userCode').val('');
			$('.search-table #userName').val('');
			$('.search-table #teamLeaderId').val('');
			$('.search-table #createStartTime').val('');
			$('.search-table #createEndTime').val('');
		})
		var managers = ${managerList};
		var managerTrList = $("tr.manager");
		$.each(managers,function(i,value){
			$.data(managerTrList[i],"manager",value);
		});

		/* 修改弹出层  */
		$(".updateManagerLink").click(function(){
			
			var manager = $.data($(this).parent().parent()[0],"manager");
			
			 $('#updateManagerModal').on('show.bs.modal', function () {
			 	$("#updateManagerModal #userCode").val(manager.userCode);
				$("#updateManagerModal #userName").val(manager.userName);
				$("#updateManagerModal #teamLeaderId").val(manager.teamLeaderId);
				$("#updateManagerModal #createTime").val(manager.createTime);
		     });
		     
			 $('#updateManagerModal').on('hide.bs.modal', function () {
				$("#updateManagerModal #userCode").val('');
				$("#updateManagerModal #userName").val('');
				$("#updateManagerModal #teamLeaderId").val('');
				$("#updateManagerModal #createTime").val('');
		     });
		});
		
		/* 修改 */
		 $(".updateManagerBtn").click(function(){
			var userCode = $("#updateManagerModal #userCode").val();
			var teamLeaderId = $("#updateManagerModal #teamLeaderId").val();
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=ManagerPath.BASE+ManagerPath.EDIT%>',{
				'userCode'          :userCode,
				'teamLeaderId'		:teamLeaderId
				
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateManagerModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("更新失败:"+data.message,function(){
							$("#updateManagerModal").modal("hide");
						});
					}
				},'json'
			);
		}); 
		
	});
		
	var winChild;
	function queryWater(obj){
		var custId=$(obj).prev().val();
		var url=window.Constants.ContextPath+"<%=ManagerPath.BASE+ ManagerPath.ADD%>"; 
	     var name="window";                        //网页名称，可为空;
	     var iWidth=1200;                          //弹出窗口的宽度;
	     var iHeight=600;                       //弹出窗口的高度;
	     //获得窗口的垂直位置
	     var iTop = (window.screen.availHeight-30-iHeight)/2;
	     //获得窗口的水平位置
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft;
		winChild = window.open(url, name,params);
		$.blockUI();
	}

		function forCloseDiv(){
			 $.unblockUI();
		} 
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
				
					<div class="row">
						<div class="col-xs-12">
						<!-- 查询条件 -->
							<form action='<c:url value="<%=ManagerPath.BASE + ManagerPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >客户经理账号</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="userCode" id="userCode"  value="${manager.userCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								
									<td class="td-left" >团队负责人</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="teamLeaderId" id="teamLeaderId"  value="${manager.teamLeaderName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >客户经理名称</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="userName" id="userName"  value="${manager.userName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >创建时间</td>
									<td class="td-right" colspan="3"> 
										 <input type="text" name="createStartTime"  id="createStartTime" readonly="readonly"  value="${manager.createStartTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="createEndTime" id="createEndTime" readonly="readonly" value="${manager.createEndTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ManagerPath.BASE + ManagerPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=ManagerPath.BASE + ManagerPath.ADD%>">
											<a  onclick="queryWater(this)" data-toggle='modal'  class="tooltip-success detailLink" >
												<button type="button"   id="queryWaterBtn"  class="btn btn-purple btn-sm"  >新增</button>
											</a> 
											
											<!-- <button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button> -->
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								市场部人员列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>客户经理账号</th> 
											<th>客户经理名称</th> 
											<th>团队负责人</th> 
											<th>创建时间</th> 
											<th>更新人</th>
											<th>更新时间</th>  
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${managerList}" var="manager" varStatus="status">
											<tr class="manager">
												 <td>${manager.userCode}</td> 
												<td>${manager.userName}</td>
												<td>${manager.teamLeaderName}</td>
												<td>${manager.createTime}</td>
												<%-- <td><fmt:formatDate value="${manager.createTime}" pattern="yyyy-MM-dd"/></td> --%>
												<td>${manager.updateId}</td>
												<td>${manager.updateTime}</td>
												<td>
													<a href="#" class="tooltip-success updateManagerLink" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateManagerModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty managerList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty managerList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
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
	<div class="modal fade" id="updateManagerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">分组修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">客户经理账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="userCode" name="userCode" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">客户经理名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="userName" name="userName" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">创建时间<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="createTime" name="createTime" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">团队负责人<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
							<!--  <input type="text" name="teamLeaderId" id="teamLeaderId" maxlength="100" clasS="width-80">-->	
								<select  name="teamLeaderId" id="teamLeaderId" maxlength="100" clasS="width-80" >
									<option value="">--请选择--</option>
									<c:forEach items="${marketRoleList}" var="marketRole" varStatus="status">
										<option value=${marketRole.userId}>${marketRole.userName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateManagerBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增用户</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">用户账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="id" name="id" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">用户名</td>
							<td class="td-right" width="30%">
									<input type="text" id="first" name="first" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">用户姓</td>
							<td class="td-right" width="30%">
									<input type="text" id="last" name="last" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">用户组</td>
							<td class="td-right" width="30%">
									<input type="text" id="userInGroup" name="userInGroup" style="width:80%" >
									<a href="#" class="tooltip-error selectAddLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#selectGroupModal">
										<span><button type="button"  id="queryGroup"  class="btn btn-warning btn-xs">选择用户组</button></span>
									</a>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		
</body>
</html>

