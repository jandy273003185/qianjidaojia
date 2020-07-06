<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.channel.controller.ChannelInfoPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>渠道信息管理</title>
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
		$(".addBtn").click(function(){
			var channel=$("#addModal").find("#channel").val();
			var channelName=$("#addModal").find("#channelName").val();
			if(kong.test(channel)){
				$.gyzbadmin.alertFailure("渠道不可为空");
				return;
			}
			if(kong.test(channelName)){
				$.gyzbadmin.alertFailure("渠道名称不可为空");
				return;
			}
			$.post(window.Constants.ContextPath +"<%=ChannelInfoPath.BASE + ChannelInfoPath.ADD%>",
				{
					'channel':channel,
					'channelName':channelName,
					'subChannel':$("#addModal").find("#subChannel").val(),
					'subChannelName':$("#addModal").find("#subChannelName").val()
				},
				function(data){
					
					if(data.result=="SUCCESS"){
							$("#addModal").modal("hide");
							window.location.reload();
					}else{
							
							$("#addModal").modal("hide");
					}
				},'json'
				);
		});

		$(".updateModal").click(function(){
			
			channelId = $(this).parents("tr").find("#channelId").val();
			
			channel=$(this).parents("tr").find("#channel").text();
			channelName=$(this).parents("tr").find("#channelName").text();
			subChannel= $(this).parents("tr").find("#subChannel").text();
			subChannelName=$(this).parents("tr").find("#subChannelName").text();
			$("#updateModal").find("#channelId").val(channelId);
			$("#updateModal").find("#channel").val(channel);
			$("#updateModal").find("#channelName").val(channelName);
			$("#updateModal").find("#subChannel").val(subChannel);
			$("#updateModal").find("#subChannelName").val(subChannelName);
		});

		
		$(".updateBtn").click(function(){
			var channel=$("#updateModal").find("#channel").val();
			var channelName=$("#updateModal").find("#channelName").val();
			if(kong.test(channel)){
				$.gyzbadmin.alertFailure("渠道不可为空");
				return;
			}
			if(kong.test(channelName)){
				$.gyzbadmin.alertFailure("渠道名称不可为空");
				return;
			}
			$.post(window.Constants.ContextPath +"<%=ChannelInfoPath.BASE + ChannelInfoPath.UPDATE%>",
				{
					'channelId':$("#updateModal").find("#channelId").val(),
					'channel':$("#updateModal").find("#channel").val(),
					'channelName':$("#updateModal").find("#channelName").val(),
					'subChannel':$("#updateModal").find("#subChannel").val(),
					'subChannelName':$("#updateModal").find("#subChannelName").val()
				},
				function(data){
					
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("修改成功！",function(){
								$("#updateModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							
							window.location.reload();
							$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
								$("#updateModal").modal("hide");
							});
					}
				},'json'

				);
		});

		$(".deleteModal").click(function(){
			channelId = $(this).parents("tr").find("#channelId").val();
			$("#deleteModal").find("#channelId").val(channelId);
		});
		
		$(".deleteBtn").click(function(){
			
			$.post(window.Constants.ContextPath +"<%=ChannelInfoPath.BASE + ChannelInfoPath.DELETE%>",
				{
					'channelId':$("#deleteModal").find("#channelId").val()
				},
				function(data){
					
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deleteModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deleteModal").modal("hide");
							});
					}
				},'json'
				);
		});
		



		
	});

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
							<form action='<c:url value="<%=ChannelInfoPath.BASE + ChannelInfoPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">渠道号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="channel" name="channel" value="${queryBean.channel }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">渠道名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="channelName" name="channelName" value="${queryBean.channelName }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ChannelInfoPath.BASE + ChannelInfoPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearChannelControl">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=ChannelInfoPath.BASE + ChannelInfoPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								渠道信息列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:5%;">渠道号</th>
											<th style="width:5%;">渠道名称</th>
											<th style="width:10%;">下级渠道号</th>
											<th style="width:10%;">下级渠道名称</th>
											<th style="width:5%;">修改人ID</th>
											<th style="width:10%;">修改时间</th>
											<th style="width:5%;">创建人ID</th>
											<th style="width:10%;">创建时间</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${channelInfoList}" var="bean">
											<tr class="channelInfoControl">
												<input type="hidden" value="${bean.channelId }" id="channelId">
												<td id="channel">${bean.channel}</td>
												<td id="channelName">${bean.channelName}</td>
												<td id="subChannel">${bean.subChannel}</td>
												<td id="subChannelName">${bean.subChannelName}</td>
												<td>${bean.modifyId}</td>
												<td><fmt:formatDate value="${bean.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${bean.createId}</td>
												<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												
												<td>
													<gyzbadmin:function url="<%=ChannelInfoPath.BASE + ChannelInfoPath.UPDATE%>">
														<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=ChannelInfoPath.BASE + ChannelInfoPath.DELETE%>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty channelInfoList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty channelInfoList}">
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
	 
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		      
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">渠道信息新增</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	
		            		<tr>
								<td class="td-left" width="30%">渠道号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="channel" name="channel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >渠道名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="channelName" name="channelName"  style="width:80%"	>
								</td>
							</tr>
							<tr class="mobileCla">
								<td class="td-left" >下级渠道号<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="subChannel" name="subChannel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >下级渠道名称<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="subChannelName" name="subChannelName" style="width:80%">
								</td>
							</tr>
		            	

		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn" >提交</button>
		         </div>
		         
		      </div>
		     </div>
		</div>

		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">提现控制修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
						<tr>
								<td class="td-left" width="30%">渠道号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="channel" name="channel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >渠道名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="channelName" name="channelName"  style="width:80%"	>
								</td>
							</tr>
							<tr class="mobileCla">
								<td class="td-left" >下级渠道号<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="subChannel" name="subChannel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >下级渠道名称<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="subChannelName" name="subChannelName"  style="width:80%">
								</td>
							</tr>
					
						
		            </table>
		         </div>
		         <div class="modal-footer">
		        	<input type="hidden"  name="channelId" id="channelId"/>
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn" >提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">渠道信息删除</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该渠道信息么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden"  name="channelId" id ="channelId">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>