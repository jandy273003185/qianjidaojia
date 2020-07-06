<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.channelroute.countroller.ChannelRouteControlPath"%>
<%@page import="com.seven.micropay.channel.enums.PaychannelType"%>
<%@page import="com.seven.micropay.channel.enums.FeeType"%>
<%@page import="com.seven.micropay.channel.enums.GatewayType"%>
<%@page import="com.seven.micropay.channel.enums.CardType"%>
<%@page import="com.seven.micropay.base.enums.UseFlag"%>
<%@page import="com.seven.micropay.channel.enums.ChannelCode"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>渠道路由管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
 	$(function(){
 		$("#serchSubmit").click(function(){
			var paychannelType =$("#paychannelType").val();
			var name =$("#name").val();
			form.submit();
		});

		//清空
		$('.clearBank').click(function(){
			$(':input','#tradeForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});






		
		var routerListJson= ${routerList};		
		var routerTrList=$("tr.router");
		$.each(routerListJson,function(i,value){
			$.data(routerTrList[i],"router",value);
		});
		
		$(".addBtn").click(function(){
			
			var name = $("#addModal #name").val();
			if(kong.test(name)) {
				$.gyzbadmin.alertFailure('渠道名称不能为空');
				return;
			}
			
			var paychannelType = $("#addModal #paychannelType").val();
			if(kong.test(paychannelType)) {
				$.gyzbadmin.alertFailure('支付渠道类型不能为空');
				return;
			}

			var gatewayType = $("#addModal #gatewayType").val();
			var feeType = $("#addModal #feeType").val();
			var fee = $("#addModal #fee").val();
			var minLim = $("#addModal #minLim").val();
			var maxLim = $("#addModal #maxLim").val();
			var cardType = $("#addModal #cardType").val();
			var weight = $("#addModal #weight").val();
			var status = $("#addModal #status").val();
			var totalCnt = $("#addModal #totalCnt").val();
			var failCnt = $("#addModal #failCnt").val();
			var remark = $("#addModal #remark").val();
			var channels = "";
			$("#addModal #channelCode:checkbox:checked").each(function(){
				if(channels != "") channels += ",";
				channels += $(this).val();
			})
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.ADD %>',
				data:
				{
				
					"name"	   			: name,
					"paychannelType"	: paychannelType,
					"gatewayType"       : gatewayType,
					"feeType"           : feeType,
					"fee"               : fee,
					"minLim"            : minLim,
					"maxLim"            : maxLim,
					"cardType"          : cardType,
					"weight"            : weight,
					"status"            : status,
					"totalCnt"          : totalCnt ,
					"failCnt"           : failCnt,
					"remark"            : remark,
					"channels"          : channels
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					}
				}
			});
		})
		
		
		
		
		
	 	$(".updateBank").click(function(){
		 var router = $.data($(this).parent().parent()[0],"router");
	       $('#updateModal').on('show.bs.modal', function () {
	    	    $("#updateModal #id").val(router.id);
	    	    $("#updateModal #name").val(router.name);
 				$("#updateModal #paychannelType").val(router.paychannelType);
 			    $("#updateModal #gatewayType").val(router.gatewayType);
				 $("#updateModal #feeType").val(router.feeType); 
				$("#updateModal #fee").val(router.fee); 
				
			    $("#updateModal #minLim").val(router.minLim);
		    	$("#updateModal #maxLim").val(router.maxLim);
				$("#updateModal #cardType").val(router.cardType); 
				$("#updateModal #weight").val(router.weight);
				$("#updateModal #status").val(router.status); 
				$("#updateModal #totalCnt").val(router.totalCnt); 
				
				$("#updateModal #failCnt").val(router.failCnt);
				$("#updateModal #remark").val(router.remark); 
			

				
	       })
		  $('#updateModal').on('hide.bs.modal', function () {
			    $("#updateModal #id").val('');
			    $("#updateModal #name").val('');
				$("#updateModal #paychannelType").val('');
				$("#updateModal #gatewayType").val('');
				$("#updateModal #feeType").val('');
				$("#updateModal #fee").val('');
				
				$("#updateModal #minLim").val('');
		    	$("#updateModal #maxLim").val('');
				$("#updateModal #cardType").val('');
				$("#updateModal #weight").val('');
				$("#updateModal #status").val('');
				$("#updateModal #totalCnt").val(''); 
				
				$("#updateModal #failCnt").val('');
				$("#updateModal #remark").val(''); 
				
	       })
		}); 
		
		 $(".updateBtn").click(function(){
			var id = $("#updateModal #id").val();
			var name = $("#updateModal #name").val();
			if(kong.test(name)) {
				$.gyzbadmin.alertFailure('产品类型不可为空');
				return;
			}
			var paychannelType = $("#updateModal #paychannelType").val();
			if(kong.test(paychannelType)) {
				$.gyzbadmin.alertFailure('渠道不可为空');
				return;
			}
			var gatewayType = $("#updateModal #gatewayType").val();
			
			var feeType = $("#updateModal #feeType").val();
			
			var fee = $("#updateModal #fee").val();
			
			var minLim = $("#updateModal #minLim").val();
			var maxLim = $("#updateModal #maxLim").val();

			if(''!=minLim&&''!=maxLim){
				if(minLim > maxLim) 
				{
					$.gyzbadmin.alertFailure("交易最大限额不能小于交易最小限额");
					return false;
				}
			}
			
			var cardType = $("#updateModal #cardType").val();
			var weight = $("#updateModal #weight").val();
			
			var status = $("#updateModal #status").val();
			var totalCnt = $("#updateModal #totalCnt").val();
			
			var failCnt = $("#updateModal #failCnt").val();
			var remark = $("#updateModal #remark").val();
			var channels = "";
			$("#updateModal #channelCode:checkbox:checked").each(function(){
				if(channels != "") channels += ",";
				channels += $(this).val();
			})
			
			
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.UPDATE %>',
				data:
				{
					"id"    : id,
					"name" 	: name,
					"paychannelType" 	: paychannelType,
					"gatewayType" 	: gatewayType,
					"feeType" 	: feeType,
					"fee"	: fee,
					"minLim" : minLim,
					"maxLim" : maxLim,
					"cardType" : cardType,
					"weight" : weight,
					"status" : status,
					"totalCnt" : totalCnt,
					"failCnt" : failCnt,
					"remark" : remark,
					"channels" :channels
					
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateModal").modal("hide");
						},function(){
							window.location.reload();
						});
						
					}else{
						$.gyzbadmin.alertFailure("更新失败！："+data.message);
					}
				}
			});
		}) 
		
		$(".delBank").click(function(){
		
			var router = $.data($(this).parent().parent()[0],"router");
			$("#deleteBankModal").find("input[name='id']").val(router.id);
			$("span.sureDel").text(router.id);
		});
		
		$(".deleteBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.DELETE %>',
				data:"id="+$("#deleteBankModal #deleteType").val(),
				success:function(data){
					$.unblockUI();
					if(data.success){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteBankModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("删除失败！"+data.message);
					}
				}
			});
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
							<form action='<c:url value="<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >渠道名称</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="name" id="name"  value="${router.name}" style="width:88%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >支付渠道类型</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="paychannelType" id="paychannelType"  value="${router.paychannelType}" style="width:88%;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.LIST %>">
											<button type="submit" id="serchSubmit"  class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.ADD%>">
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
								路由列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>编号</th>
											<th>渠道名称</th>
											<th>支付渠道类型</th>
											<th>网关类型</th>
											<th>计费方式</th>
											<th>费率</th>
											<th>交易最小限度</th>
											<th>交易最大额度</th>
											<th>支持卡种</th>
											<th>权重</th>
											<th>状态</th>
											<th>累计发送次数</th>
											<th>累计失败次数</th>
											<th>备注</th>
											<th>创建日期</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${routerList}" var="router" varStatus="status">
											<tr class="router" >
											    <td>${router.id }</td>
											    <td>${router.name}</td>
												<td>${router.paychannelType}</td>
												<td>
													<c:forEach items="<%=GatewayType.values()%>" var="status">
														<c:if test="${status == router.gatewayType}">${status.text}</c:if>
													</c:forEach>
												</td>
												<td>
													<c:forEach items="<%=FeeType.values()%>" var="status">
														<c:if test="${status == router.feeType}">${status.text}</c:if>
													</c:forEach>
												</td>
												<td>${router.fee }</td>
												<td>${router.minLim }</td>
												<td>${router.maxLim }</td>
												<td>
													<c:forEach items="<%=CardType.values()%>" var="status">
														<c:if test="${status == router.cardType}">${status.text}</c:if>
													</c:forEach>
												</td>
												<td>${router.weight }</td>
												<td>
													<c:forEach items="<%=UseFlag.values()%>" var="status">
														<c:if test="${status == router.status}">${status.text}</c:if>
													</c:forEach>
												</td>
												<td>${router.totalCnt }</td>
												<td>${router.failCnt }</td>
												<td>${router.remark }</td>												
												<td>
													<fmt:formatDate value="${router.createTime }" pattern="yyyy-MM-dd"/>
												</td>
												<td>
													<%-- <gyzbadmin:function url="<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.UPDATE %>"> --%>
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateBank" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
												<%-- 	</gyzbadmin:function> --%>
													<gyzbadmin:function url="<%=ChannelRouteControlPath.BASE + ChannelRouteControlPath.DELETE%>">
														<a href="#deleteBankModal"  data-toggle='modal' class="tooltip-error delBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty routerList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty users}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
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
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改路由</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<tr>
							<td class="td-left" width="20%">编号</td>
							<td class="td-right" width="30%">
									<input type="text" id="id" name="id" style="width:50%" readonly="readonly">
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="20%">渠道名称</td>
							<td class="td-right" width="30%">
							
							<input type="text" id="name" name="name" style="width:50%" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">支付渠道类型</td>
							<td class="td-right" width="30%">
						
									<input type="text" id="paychannelType" name="paychannelType" style="width:50%" readonly="readonly">
							</td>
						</tr>
						<tr>
							<!-- <td class="td-left" width="20%">网关类型</td>
							<td class="td-right" width="30%">
									<input type="text" id="gatewayType" name="gatewayType" style="width:80%" >
							</td> -->
							
							<td class="td-left" width="20%">网关类型</td>
							<td class="td-right" width="30%">
								<span class="input-icon">
									<select name="gatewayType" id="gatewayType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=GatewayType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.gatewayType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
							
						</tr>
						<tr>
							<!-- <td class="td-left" width="20%">计费方式</td>
							<td class="td-right" width="30%">
									<input type="text" id="feeType" name="feeType" style="width:80%" >
							</td> -->
							
							<td class="td-left" width="30%">计费方式</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="feeType" id="feeType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=FeeType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.feeType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
						</tr>
							<tr>
							<td class="td-left" width="20%">费率</td>
							<td class="td-right" width="30%">
									<input type="text" id="fee" name="fee" style="width:50%" >
							</td>
						</tr>
							<tr>
							<td class="td-left" width="20%">交易最小限度</td>
							<td class="td-right" width="30%">
									<input type="text" id="minLim" name="minLim" style="width:50%" >
							</td>
						</tr>
							<tr>
							<td class="td-left" width="20%">交易最大额度</td>
							<td class="td-right" width="30%">
									<input type="text" id="maxLim" name="maxLim" style="width:50%" >
							</td>
						</tr>
							<tr>
							<!-- <td class="td-left" width="20%">支持卡种</td>
							<td class="td-right" width="30%">
									<input type="text" id="cardType" name="cardType" style="width:80%" >
							</td> -->
							
							<td class="td-left" width="30%">支持卡种</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="cardType" id="cardType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=CardType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.cardType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
						</tr>
							<tr>
							<td class="td-left" width="20%">权重</td>
							<td class="td-right" width="30%">
									<input type="text" id="weight" name="weight" style="width:50%" >
							</td>
						</tr>
							<tr>
							<!-- <td class="td-left" width="20%">状态</td>
							<td class="td-right" width="30%">
									<input type="text" id="status" name="status" style="width:80%" >
							</td> -->
							
							<td class="td-left" width="30%">状态</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="status" id="status">
										<option value="">--请选择--</option>
										<c:forEach items="<%=UseFlag.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.status}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">累计发送次数</td>
							<td class="td-right" width="30%">
									<input type="text" id="totalCnt" name="totalCnt" style="width:50%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">累计失败次数</td>
							<td class="td-right" width="30%">
									<input type="text" id="failCnt" name="failCnt" style="width:50%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">备注</td>
							<td class="td-right" width="30%">
									<input type="text" id="remark" name="remark" style="width:50%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" >渠道<span style="color:red">*</span></td>
							<td class="td-right"  align ="center" style="border:1px solid;">
												
								<c:forEach items="<%=ChannelCode.values()%>" var="status" varStatus="vs">
									<input type="checkbox" id="channelCode" name="channelCode"  value="${status}" <c:if test="${status == queryBean.channelCode}">checked="checked"</c:if> />
									${status.text}
									<c:if test="${vs.index != 0 && vs.index % 2 == 0}">
										</p>
									</c:if>
								</c:forEach>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
		         </div>
		      </div>
		     </div>
		</div>
		
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增路由</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
	            		<tr>
							<td class="td-left" width="30%">渠道名称</td>
							<td class="td-right" >
								<input type="text" id="name" name="name" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="10%">支付渠道类型</td>
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
						</tr>
						<tr>
							<td class="td-left" width="20%">网关类型</td>
							<td class="td-right" width="30%">
								<span class="input-icon">
									<select name="gatewayType" id="gatewayType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=GatewayType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.gatewayType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="30%">计费方式</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="feeType" id="feeType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=FeeType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.feeType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">费率</td>
							<td class="td-right" width="30%">
								<input type="text" id="fee" name="fee" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">交易最小限度</td>
							<td class="td-right" width="30%">
								<input type="text" id="minLim" name="minLim" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">交易最大额度</td>
							<td class="td-right" width="30%">
								<input type="text" id="maxLim" name="maxLim" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="30%">支持卡种</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="cardType" id="cardType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=CardType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.cardType}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
							
						</tr>
						<tr>
							<td class="td-left" width="20%">权重</td>
							<td class="td-right" width="30%">
								<input type="text" id="weight" name="weight" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="30%">状态</td>
							<td class="td-right" >
								<span class="input-icon">
									<select name="status" id="status">
										<option value="">--请选择--</option>
										<c:forEach items="<%=UseFlag.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.status}">selected</c:if>>${status.text}</option>
										</c:forEach>
									</select>
								</span>
							</td>
							
						</tr>
						<tr>
							<td class="td-left" width="20%">累计发送次数</td>
							<td class="td-right" width="30%">
								<input type="text" id="totalCnt" name="totalCnt" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">累计失败次数</td>
							<td class="td-right" width="30%">
								<input type="text" id="failCnt" name="failCnt" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">备注</td>
							<td class="td-right" width="30%">
								<input type="text" id="remark" name="remark" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" >渠道</td>
							<td class="td-right"  align ="center" style="border:1px solid;">
												
								<c:forEach items="<%=ChannelCode.values()%>" var="status" varStatus="vs">
									<input type="checkbox" id="channelCode" name="channelCode" value="${status}" <c:if test="${status == queryBean.channelCode}">checked="checked"</c:if> />
									${status.text}
									<c:if test="${vs.index != 0 && vs.index % 2 == 0}">
										</p>
									</c:if>
								</c:forEach>
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
		
 		
		<div class="modal fade" id="deleteBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除路由信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该路由[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" id="deleteType">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div>/.modal
</body>
</html>

