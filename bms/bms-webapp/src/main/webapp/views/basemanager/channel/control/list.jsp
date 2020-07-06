<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.channel.controller.ChannelControlPath" %> 
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
			var mchId=$("#addModal").find("#mchId").val();
			var channel=$("#addModal").find("#channel").val();
			var status=$("#addModal").find("#status").val();
			var subChannel=$("#addModal").find("#subChannel").val();
			var limitPerAmt=$("#addModal").find("#limitPerAmt").val();
			var limitDayCount=$("#addModal").find("#limitDayCount").val();
			var limitDayTotAmt=$("#addModal").find("#limitDayTotAmt").val()
			if(kong.test(mchId)){
				$.gyzbadmin.alertFailure("商户不可为空");
				return;
			}
			if(kong.test(channel)){
				$.gyzbadmin.alertFailure("渠道不可为空");
				return;
			}
			if(kong.test(status)){
				$.gyzbadmin.alertFailure("状态不可为空");
				return;
			}
			if(kong.test(subChannel)){
				$.gyzbadmin.alertFailure("下级渠道不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=ChannelControlPath.BASE + ChannelControlPath.ADD%>",
				{
					'mchId':mchId,
					'channel':channel,
					'status':status,
					'subChannel':subChannel,
					'limitPerAmt':limitPerAmt,
					'limitDayCount':limitDayCount,
					'limitDayTotAmt':limitDayTotAmt
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
			
			mchId = $(this).parents("tr").find("#mchId").text();
			
			channel=$(this).parents("tr").find("#channel").text();
			status=$(this).parents("tr").find("#status").val();
			subChannel= $(this).parents("tr").find("#subChannel").text();
			
			limitPerAmt=$(this).parents("tr").find("#limitPerAmt").text();
			limitDayCount=$(this).parents("tr").find("#limitDayCount").text();
			limitDayTotAmt=$(this).parents("tr").find("#limitDayTotAmt").text();
			$("#updateModal").find("#mchId").val(mchId);
			$("#updateModal").find("#channel").val(channel);
			$("#updateModal").find("#status").val(status);
			$("#updateModal").find("#subChannel").val(subChannel);
			$("#updateModal").find("#limitPerAmt").val(limitPerAmt);
			$("#updateModal").find("#limitDayCount").val(limitDayCount);
			$("#updateModal").find("#limitDayTotAmt").val(limitDayTotAmt);
		});

		
		$(".updateBtn").click(function(){
			var mchId=$("#updateModal").find("#mchId").val();
			var channel=$("#updateModal").find("#channel").val();
			var status=$("#updateModal").find("#status").val();
			var subChannel=$("#updateModal").find("#subChannel").val();
			var limitPerAmt=$("#updateModal").find("#limitPerAmt").val();
			var limitDayCount=$("#updateModal").find("#limitDayCount").val();
			var limitDayTotAmt=$("#updateModal").find("#limitDayTotAmt").val()
			if(kong.test(mchId)){
				$.gyzbadmin.alertFailure("商户不可为空");
				return;
			}
			if(kong.test(channel)){
				$.gyzbadmin.alertFailure("渠道不可为空");
				return;
			}
			if(kong.test(status)){
				$.gyzbadmin.alertFailure("状态不可为空");
				return;
			}
			if(kong.test(subChannel)){
				$.gyzbadmin.alertFailure("下级渠道不可为空");
				return;
			}
			
			$.post(window.Constants.ContextPath +"<%=ChannelControlPath.BASE + ChannelControlPath.UPDATE%>",
				{
					'mchId':mchId,
					'channel':channel,
					'status':status,
					'subChannel':subChannel,
					'limitPerAmt':limitPerAmt,
					'limitDayCount':limitDayCount,
					'limitDayTotAmt':limitDayTotAmt
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
			mchId = $(this).parents("tr").find("#mchId").text();
			channel = $(this).parents("tr").find("#channel").text();
			$("#deleteModal").find("#mchId").val(mchId);
			$("#deleteModal").find("#channel").val(channel);
		});
		
		$(".deleteBtn").click(function(){
			
			$.post(window.Constants.ContextPath +"<%=ChannelControlPath.BASE + ChannelControlPath.DELETE%>",
				{
					'mchId':$("#deleteModal").find("#mchId").val(),
					'channel':$("#deleteModal").find("#channel").val()
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
							<form action='<c:url value="<%=ChannelControlPath.BASE + ChannelControlPath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">商户号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="mchId" name="mchId" value="${queryBean.mchId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">渠道号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="channel" name="channel" value="${queryBean.channel }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">下级渠道</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="subChannel" name="subChannel" value="${queryBean.subChannel }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ChannelControlPath.BASE + ChannelControlPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearChannelControl">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=ChannelControlPath.BASE + ChannelControlPath.ADD%>">
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
											<th style="width:5%;">商户编号</th>
											<th style="width:5%;">渠道</th>
											<th style="width:10%;">下级渠道</th>
											<th style="width:10%;">渠道状态</th>
											<th style="width:5%;">单笔交易限额</th>
											<th style="width:10%;">单日交易笔数</th>
											<th style="width:5%;">单日交易总额限额</th>
											<th style="width:5%;">创建人Id</th>
											<th style="width:10%;">创建时间</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${channelControlList}" var="bean">
											<tr class="channelControl">
												<input type="hidden"  id="status" value="${bean.status}"/>
												<td id="mchId">${bean.mchId}</td>
												<td id="channel">${bean.channel}</td>
												<td id="subChannel">${bean.subChannel}</td>
												<td >
													<c:choose>
														<c:when test="${bean.status==0}">关闭</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status==1}">启用</c:when>
													</c:choose>
												</td>
												<td id="limitPerAmt">${bean.limitPerAmt}</td>
												<td id="limitDayCount">${bean.limitDayCount}</td>
												<td id="limitDayTotAmt">${bean.limitDayTotAmt}</td>
												<td>${bean.createId}</td>
												<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												
												<td>
													<gyzbadmin:function url="<%=ChannelControlPath.BASE + ChannelControlPath.UPDATE%>">
														<a href="#" class="tooltip-success updateModal" data-rel="tooltip" title="编辑" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=ChannelControlPath.BASE + ChannelControlPath.DELETE%>">
													
														<a href="#" class="tooltip-error deleteModal" data-rel="tooltip" title="删除" data-toggle="modal" data-target="#deleteModal">
																<span class="red">
																<i class="icon-trash bigger-120"></i>
																</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty channelControlList}">
											<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty channelControlList}">
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
		            		<tr class="custIdCla">
								<td class="td-left" >商户号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="mchId" name="mchId"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">渠道号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="channel" name="channel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >下级渠道<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="subChannel" name="subChannel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >渠道状态<span style="color:red">*</span></td>
								<td class="td-right" >
									
									<select id="status">
										<option value="">--请选择--</option>	
										<option value="0">关闭</option>	
										<option value="1">启用</option>	
									</select>
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >单笔交易限额<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >单日交易笔数<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitDayCount" name="limitDayCount" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >单日交易总限额<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitDayTotAmt" name="limitDayTotAmt" style="width:80%">
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
						<tr class="custIdCla">
								<td class="td-left" >商户号<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="mchId" name="mchId"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">渠道号<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="channel" name="channel"  style="width:80%"	>
								</td>
							</tr>
							<tr class="custIdCla">
								<td class="td-left" >下级渠道<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="subChannel" name="subChannel"  style="width:80%"	>
								</td>
							</tr>
							
							<tr class="protocolIdClass">
								<td class="td-left" >渠道状态<span style="color:red">*</span></td>
								<td class="td-right" >
									<select id="status">
										<option value="">--请选择--</option>	
										<option value="0">关闭</option>	
										<option value="1">启用</option>	
									</select>
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >单笔交易限额<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >单日交易笔数<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitDayCount" name="limitDayCount" style="width:80%">
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >单日交易总限额<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="limitDayTotAmt" name="limitDayTotAmt" style="width:80%">
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
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该渠道控制信息么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden"  name="mchId" id ="mchId">
		         	<input type="hidden"  name="channel" id ="channel">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteBtn" >确定</button>
		         </div>
		      </div>
		     </div>
		</div>
</body>
</html>