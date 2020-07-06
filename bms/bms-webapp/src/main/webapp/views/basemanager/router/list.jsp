<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.router.RouterPath"%>

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
		
		var routerListJson= ${routerList};		
		var routerTrList=$("tr.router");
		$.each(routerListJson,function(i,value){
			$.data(routerTrList[i],"router",value);
		});
		
		$(".addBtn").click(function(){
			var productCode = $("#addModal #productCode").val();
			var channel = $("#addModal #channel").val();
			var channelCode = $("#addModal #channelCode").val();
			if(kong.test(channelCode)) {
				$.gyzbadmin.alertFailure('渠道编号不能为空');
				return;
			}
			var busiType = $("#addModal #busiType").val();
			var busiName = $("#addModal #busiName").val();
			if(kong.test(busiName)) {
				$.gyzbadmin.alertFailure('本地业务类型不能为空');
				return;
			}
			
			
			
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=RouterPath.BASE + RouterPath.ADD %>',
				data:
				{
					"productCode" 	: productCode,
					"channel" 	: channel,
					"channelCode" 	: channelCode,
					"busiType" 	: busiType,
					"busiName"	   	: busiName
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
	    	    $("#updateModal #productCode").val(router.productCode);
				$("#updateModal #channel").val(router.channel);
				$("#updateModal #channelCode").val(router.channelCode);
				$("#updateModal #busiType").val(router.busiType);
				$("#updateModal #busiName").val(router.busiName); 
	       })
		  $('#updateModal').on('hide.bs.modal', function () {
			    $("#updateModal #id").val('');
			    $("#updateModal #productCode").val('');
				$("#updateModal #channel").val('');
				$("#updateModal #channelCode").val('');
				$("#updateModal #busiType").val('');
				$("#updateModal #busiName").val('');
	       })
		});
		
		$(".updateBtn").click(function(){
			var id = $("#updateModal #id").val();
			if(kong.test(id)) {
				$.gyzbadmin.alertFailure('编号不可为空');
				return;
			}
			var productCode = $("#updateModal #productCode").val();
			if(kong.test(productCode)) {
				$.gyzbadmin.alertFailure('产品类型不可为空');
				return;
			}
			var channel = $("#updateModal #channel").val();
			if(kong.test(channel)) {
				$.gyzbadmin.alertFailure('渠道不可为空');
				return;
			}
			var channelCode = $("#updateModal #channelCode").val();
			if(kong.test(channelCode)) {
				$.gyzbadmin.alertFailure('渠道编号不可为空');
				return;
			}
			var busiType = $("#updateModal #busiType").val();
			if(kong.test(busiType)) {
				$.gyzbadmin.alertFailure('本地业务类型代码不可为空');
				return;
			}
			var busiName = $("#updateModal #busiName").val();
			if(kong.test(busiName)) {
				$.gyzbadmin.alertFailure('本地业务类型不可为空');
				return;
			}
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=RouterPath.BASE + RouterPath.UPDATE %>',
				data:
				{
					"id"	: id,
					"productCode" 	: productCode,
					"channel" 	: channel,
					"channelCode" 	: channelCode,
					"busiType" 	: busiType,
					"busiName"	   	: busiName
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
				url:window.Constants.ContextPath +'<%=RouterPath.BASE + RouterPath.DELETE %>',
				data:"id="+$("#deleteBankModal #deleteId").val(),
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
							<form action='<c:url value="<%=RouterPath.BASE + RouterPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >用户账号</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="id" id="id"  value="${user.id }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=RouterPath.BASE + RouterPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=RouterPath.BASE + RouterPath.ADD%>">
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
											<th>产品类型</th>
											<th>渠道</th>
											<th>渠道编号</th>
											<th>本地业务类型代码</th>
											<th>本地业务类型</th>
											<th>是否由业务类型控制路由</th>
											<th>权重</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${routerList}" var="router" varStatus="status">
											<tr class="router" >
											    <td>${router.id }</td>
											    <td>${router.productCode}</td>
<!-- 												<td> -->
<%-- 												<c:if test="${router.productCode =='h5.gateway.pay'}"> --%>
<!-- 													H5支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.productCode =='mobile.plugin.pay'}"> --%>
<!-- 													APP支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.productCode =='onecode.coll.pay'}"> --%>
<!-- 													扫码支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.productCode =='pc.gateway.pay'}"> --%>
<!-- 													网关支付 -->
<%-- 												</c:if> --%>
<!-- 											</td> -->
												<td>${router.channel}</td>
<!-- 												<td> -->
<%-- 												<c:if test="${router.channel =='wx'}"> --%>
<!-- 													微信 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.channel =='alipay'}"> --%>
<!-- 													支付宝 -->
<%-- 												</c:if> --%>
<!-- 											</td> -->
												<td>${router.channelCode }</td>
												<td>${router.busiType}</td>
<!-- 												<td> -->
<%-- 												<c:if test="${router.busiType =='H5'}"> --%>
<!-- 													H5 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.busiType =='PC'}"> --%>
<!-- 													PC支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.busiType =='APP'}"> --%>
<!-- 													APP支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.busiType =='MICROPAY'}"> --%>
<!-- 													小额支付 -->
<%-- 												</c:if> --%>
<%-- 												<c:if test="${router.busiType =='ONECODE'}"> --%>
<!-- 													扫码支付 -->
<%-- 												</c:if> --%>
<!-- 											</td> -->
												<td>${router.busiName }</td>
												<td>${router.busiCtrl }</td>
												<td>${router.weight }</td>
												<td>
													<gyzbadmin:function url="<%=RouterPath.BASE + RouterPath.UPDATE%>">
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateBank" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=RouterPath.BASE + RouterPath.DELETE%>">
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
									<input type="text" id="id" name="id" style="width:80%" >
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="20%">产品类型</td>
							<td class="td-right" width="30%">
							<sevenpay:selectProductCodeType id="productCode" name="productCode" style="width:90%"/>
<!-- 									<input type="text" id="productCode" name="productCode" style="width:80%" > -->
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">渠道</td>
							<td class="td-right" width="30%">
							<sevenpay:selectProductNameType id="channel" name="channel" style="width:90%"/>
<!-- 									<input type="text" id="channel" name="channel" style="width:80%" > -->
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">渠道编号</td>
							<td class="td-right" width="30%">
									<input type="text" id="channelCode" name="channelCode" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">本地业务类型代码</td>
							<td class="td-right" width="30%">
							<sevenpay:selectBusiType id="BusiType" name="BusiType" style="width:90%"/>
<!-- 									<input type="text" id="busiType" name="busiType" style="width:80%" > -->
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">本地业务类型</td>
							<td class="td-right" width="30%">
									<input type="text" id="busiName" name="busiName" style="width:80%" >
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
		         </div>
		      </div>/.modal-content
		     </div>
		</div>/.modal
		
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
								<td class="td-left" width="30%">产品类型</td>
								<td class="td-right" >
									<sevenpay:selectProductCodeType id="productCode" name="productCode" style="width:90%"/>
<!-- 									<input type="text" id="productCode" name="productCode" style="width:80%" > -->
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">渠道</td>
								<td class="td-right" >
									<sevenpay:selectProductNameType id="channel" name="channel" style="width:90%"/>
<!-- 									<input type="text" id="channel" name="channel" style="width:80%" > -->
								</td>
							</tr>
						<tr>
							<td class="td-left" width="20%">渠道编号</td>
							<td class="td-right" width="30%">
									<input type="text" id="channelCode" name="channelCode" style="width:80%" >
							</td>
						</tr>
							<tr>
								<td class="td-left" width="30%">本地业务类型代码</td>
								<td class="td-right" >
									<sevenpay:selectBusiType id="BusiType" name="BusiType" style="width:90%"/>
<!-- 										<input type="text" id="BusiType" name="BusiType" style="width:80%" > -->
								</td>
							</tr>
						<tr>
							<td class="td-left" width="20%">本地业务类型</td>
							<td class="td-right" width="30%">
									<input type="text" id="busiName" name="busiName" style="width:80%" >
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
		
<!-- 		<div class="modal fade" id="selectGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!-- 					   <div class="modal-dialog"> -->
<!-- 					      <div class="modal-content" style="width:500px;"> -->
<!-- 					        <div class="modal-header"> -->
<!-- 					           <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button> -->
<!-- 					            <h4 class="modal-title" id="myModalLabel">选择用户组</h4> -->
<!-- 					         </div> -->
<!-- 					         <div class="modal-body" align="center"> -->
<!-- 					         <input type="hidden" id="typeModel" > -->
<!-- 					          <table id="sample-table-2" class="list-table"> -->
<!-- 									<thead> -->
<!-- 										<tr> -->
<!-- 											<th><input type="checkbox" onclick="addCheckItem('addGroupBox');" id="addGroupAllBox" name="addGroupAllBox"/></th> -->
<!-- 											<th>组名称</th> -->
<!-- 										</tr> -->
<!-- 									</thead> -->
				
<!-- 									<tbody>	 -->
<%-- 									<c:forEach items="${groups }" var="group"> --%>
<!-- 										<tr class="role"> -->
<%-- 											<td><input type="checkbox" onclick="addCheckAll('addGroupAllBox');" name="addGroupBox" id="${group.id }" value="${group.name }"/></td> --%>
<%-- 											<td>${group.id }</td> --%>
<!-- 										</tr> -->
<%-- 									</c:forEach> --%>
<!-- 									</tbody> -->
<!-- 								</table> -->
<!-- 					         </div> -->
<!-- 					         <div class="modal-footer"> -->
<!-- 					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> -->
<!-- 					            <button type="button" class="btn btn-primary selectGroupBtn">确定</button> -->
<!-- 					         </div> -->
<!-- 					      </div>/.modal-content -->
<!-- 					     </div> -->
<!-- 					</div>/.modal -->
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
					         	<input type="hidden" name="id" id="deleteId">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div>/.modal-content
					     </div>
		</div>/.modal
</body>
</html>

