<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller.TdMerchantChannelPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户渠道金额限制</title>
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
			$(':input','#form_merchant')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		var listJson= ${list};		
		var lists=$("tr.merchatChannel");
		$.each(listJson,function(i,value){
			$.data(lists[i],"channel",value);
		});
		
		$(".addBtn").click(function(){
			var mchId = $("#addModal #mchId").val();
			if(kong.test(mchId)) {
				$.gyzbadmin.alertFailure('商户编号不可为空');
				return;
			}
			var chanel = $("#addModal #chanel").val();
			if(kong.test(chanel)) {
				$.gyzbadmin.alertFailure('渠道不可为空');
				return;
			}
			var chanelStatus = $("#addModal #chanelStatus").val();
			if(kong.test(chanelStatus)) {
				$.gyzbadmin.alertFailure('渠道状态不可为空');
				return;
			}
			var limitPerAmt = $("#addModal #limitPerAmt").val();
			if(kong.test(limitPerAmt)) {
				$.gyzbadmin.alertFailure('交易每次限额不可为空');
				return;
			}
			var limitTotAmt = $("#addModal #limitTotAmt").val();
			if(kong.test(limitTotAmt)) {
				$.gyzbadmin.alertFailure('交易总限额不可为空');
				return;
			}
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.ADD %>',
				data:
				{
					"mchId" 		: mchId,
					"chanel" 		: chanel,
					"chanelStatus" 	: chanelStatus,
					"limitPerAmt" 	: limitPerAmt,
					"limitTotAmt"	: limitTotAmt
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
						$.gyzbadmin.alertFailure("添加失败！"+data.messgage);
					}
				}
			});
		})
		
		$(".updateChannel").click(function(){
		 var merchantChannel = $.data($(this).parent().parent()[0],"channel");
	       $('#updateModal').on('show.bs.modal', function () {
	    	    $("#updateModal #mchId").val(merchantChannel.mchId);
				$("#updateModal #chanel").val(merchantChannel.chanel);
				$("#updateModal #chanelStatus").val(merchantChannel.chanelStatus);
				$("#updateModal #limitPerAmt").val(merchantChannel.limitPerAmt);
				$("#updateModal #limitTotAmt").val(merchantChannel.limitTotAmt);
	       })
		  $('#updateModal').on('hide.bs.modal', function () {
			  $("#updateModal #mchId").val('');
				$("#updateModal #chanel").val('');
				$("#updateModal #chanelStatus").val('');
				$("#updateModal #limitPerAmt").val('');
				$("#updateModal #limitTotAmt").val('');
	       })
		});
		
		$(".updateBtn").click(function(){
			var mchId = $("#updateModal #mchId").val();
			if(kong.test(mchId)) {
				$.gyzbadmin.alertFailure('商户编号不可为空');
				return;
			}
			var chanel = $("#updateModal #chanel").val();
			if(kong.test(chanel)) {
				$.gyzbadmin.alertFailure('渠道不可为空');
				return;
			}
			var chanelStatus = $("#updateModal #chanelStatus").val();
			if(kong.test(chanelStatus)) {
				$.gyzbadmin.alertFailure('渠道状态不可为空');
				return;
			}
			var limitPerAmt = $("#updateModal #limitPerAmt").val();
			if(kong.test(limitPerAmt)) {
				$.gyzbadmin.alertFailure('交易每次限额不可为空');
				return;
			}
			var limitTotAmt = $("#updateModal #limitTotAmt").val();
			if(kong.test(limitTotAmt)) {
				$.gyzbadmin.alertFailure('交易总限额不可为空');
				return;
			}
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.UPDATE %>',
				data:
				{
					"mchId" 		: mchId,
					"chanel" 		: chanel,
					"chanelStatus" 	: chanelStatus,
					"limitPerAmt" 	: limitPerAmt,
					"limitTotAmt"	: limitTotAmt
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
						$.gyzbadmin.alertFailure("更新失败！："+data.messgage);
					}
				}
			});
		})
		
		$(".delBank").click(function(){
			var merchantChannel = $.data($(this).parent().parent()[0],"channel");
			$("#deleteModal").find("input[name='mchId']").val(merchantChannel.mchId);
			$("#deleteModal").find("input[name='chanel']").val(merchantChannel.chanel);
			$("span.sureDel").text("商户编号："+merchantChannel.mchId+",渠道号："+merchantChannel.chanel);
		});
		
		$(".deleteBtn").click(function(){
			var mchId = $("#deleteModal #mchId").val();
			var chanel = $("#deleteModal #chanel").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.DELETE %>',
				data:
				{
					"mchId" 		: mchId,
					"chanel" 		: chanel
				},
				success:function(data){
					$.unblockUI();
					if(data.result = 'SUCCESS'){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("删除失败！"+data.messgage);
					}
				}
			});
		});
	});
	function load(){
		$("#chanelStatus").val( $("#chanelStatushidden").val());
		$("#chanel").val( $("#chanelhidden").val());
	}
</script>
<body onload="load()">
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
							<form id="form_merchant" action='<c:url value="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="mchId" id="mchId"  value="${bean.mchId}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">渠道</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="hidden"  name="chanelhidden" id="chanelhidden"   value="${bean.chanel}">
											<select id="chanel" name="chanel" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="wx">微信</option>
												<option value="ali">支付宝</option>
												<option value="sevenpay">七分钱</option>
											</select>
										</span>
									</td>
									<td class="td-left">渠道状态</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="hidden" id="chanelStatushidden" value="${bean.chanelStatus}">
										 	<select id="chanelStatus" name="chanelStatus" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="0">关闭</option>
												<option value="1">打开 </option>
											</select>
										</span>   
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.ADD%>">
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
								银行列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">商户编号</th>
											<th width="10%">渠道</th>
											<th width="30%">渠道状态</th>
											<th width="10%">交易限额</th>
											<th width="10%">交易总限额</th>
											<th width="10%">修改时间</th>
											<th width="10%">修改人ID</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listBean}" var="merchantChannel" varStatus="status">
											<tr class="merchatChannel" >
												<td>${merchantChannel.mchId}</td>
												<td>${merchantChannel.chanel}</td>
												<td>${merchantChannel.chanelStatus}</td>
												<td>${merchantChannel.limitPerAmt}</td>
												<td>${merchantChannel.limitTotAmt}</td>
												<td>${merchantChannel.modifyTime}</td>
												<td>${merchantChannel.modifyId}</td>
												<td>
													<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.UPDATE%>">
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateChannel" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.DELETE%>">
														<a href="#deleteModal"  data-toggle='modal' class="tooltip-error delBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty list}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty list}">
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
		            <h4 class="modal-title" id="myModalLabel">新增商户渠道金额限制</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<c:url value="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.ADD %>"/>' method="post" id="addBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">商户编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="mchId" name="mchId" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">渠道<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="chanel" name="chanel" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">渠道状态<span style="color:red">*</span></td>
							<td class="td-right">
									<select id="chanelStatus" name="chanelStatus" class="width-50">
												<option value="">- 请选择 -</option>
												<option value="0">关闭</option>
												<option value="1">打开 </option>
									</select>
							</td>
						<tr>
							<td class="td-left">每次交易限额</td>
							<td class="td-right">
								<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">交易总限额</td>
							<td class="td-right">
									<input type="text" id="limitTotAmt" name="limitTotAmt" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新商户渠道信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.UPDATE %>' method="post" id="updateBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">商户编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="mchId" name="mchId"  readonly="readonly" style="width:80%" >
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">渠道<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="chanel" name="chanel"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">渠道状态<span style="color:red">*</span></td>
							<td class="td-right">
									<select id="chanelStatus" name="chanelStatus" class="width-80">
											<option value="">- 请选择 -</option>
												<option value="0">关闭</option>
												<option value="1">打开 </option>
									</select>
							</td>
						<tr>
							<td class="td-left">每次交易限额</td>
							<td class="td-right">
									<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">总交易限额</td>
							<td class="td-right">
									<input type="text" id="limitTotAmt" name="limitTotAmt"  style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除商户渠道金额信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该商户金额限制[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="mchId" id="mchId">
					         	<input type="hidden" name="chanel" id="chanel">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

