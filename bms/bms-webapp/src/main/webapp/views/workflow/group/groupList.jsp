<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.bank.BankPath"%>
<%@page import="com.qifenqian.bms.workflow.group.controller.GroupPath"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>工作流用户组管理</title>
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
			$('#name').val('');
		});
		/*
		var bankListJson= ${bankList};		
		var bankTrList=$("tr.bank");
		$.each(bankListJson,function(i,value){
			$.data(bankTrList[i],"bank",value);
		}); */
		
		$(".addBtn").click(function(){
			var name = $("#addModal #name").val();
			if(kong.test(name)) {
				$.gyzbadmin.alertFailure('名称不可为空');
				return;
			}
			
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=GroupPath.BASE + GroupPath.ADD %>',
				data:
				{
					"name" 	: name
					
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addBModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					}
				}
			});
		})
		
		<%--
		$(".updateBank").click(function(){
		 var bank = $.data($(this).parent().parent()[0],"bank");
	       $('#updateBankModal').on('show.bs.modal', function () {
	    	    $("#updateBankModal #bankType").val(bank.bankType);
				$("#updateBankModal #bankName").val(bank.bankName);
				$("#updateBankModal #myBankType").val(bank.myBankType);
				$("#updateBankModal #cityCode").val(bank.cityCode);
				$("#updateBankModal #ccpc").val(bank.ccpc);
				$("#updateBankModal #orderBy").val(bank.orderBy);
				$("#updateBankModal #bankCode").val(bank.bankCode);  
	       })
		  $('#updateBankModal').on('hide.bs.modal', function () {
			    $("#updateBankModal #bankType").val('');
				$("#updateBankModal #bankName").val('');
				$("#updateBankModal #myBankType").val('');
				$("#updateBankModal #cityCode").val('');
				$("#updateBankModal #ccpc").val('');
				$("#updateBankModal #orderBy").val('');
				$("#updateBankModal #bankCode").val('');  
	       })
		});
		
		$(".updateBankBtn").click(function(){
			var bankCode = $("#updateBankModal #bankCode").val();
			if(kong.test(bankCode)) {
				$.gyzbadmin.alertFailure('银行名称不可为空');
				return;
			}
			var bankName = $("#updateBankModal #bankName").val();
			if(kong.test(bankName)) {
				$.gyzbadmin.alertFailure('银行名称不可为空');
				return;
			}
			var bankType = $("#updateBankModal #bankType").val();
			if(kong.test(bankType)) {
				$.gyzbadmin.alertFailure('银行行别不可为空');
				return;
			}
			var cityCode = $("#updateBankModal #cityCode").val();
			var ccpc = $("#updateBankModal #ccpc").val();
			var orderBy = $("#updateBankModal #orderBy").val();
			var myBankType =$("#updateBankModal #myBankType").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=BankPath.BASE + BankPath.UPDATE %>',
				data:
				{
					"bankCode" 	: bankCode,
					"bankName" 	: bankName,
					"bankType" 	: bankType,
					"cityCode" 	: cityCode,
					"ccpc"	   	: ccpc,
					"orderBy"  	: orderBy,
					"myBankType": myBankType
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateBankModal").modal("hide");
						},function(){
							window.location.reload();
						});
						
					}else{
						$.gyzbadmin.alertFailure("更新失败！："+data.message);
					}
				}
			});
		})--%>
		$(".delGroup").click(function(){
			var id = $(this).prev().val();
			$("#deleteModal").find("input[name='groupId']").val(id);
			$("span.sureDel").text(id);
		});
		
		$(".deleteBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=GroupPath.BASE + GroupPath.DELETE %>',
				data:"id="+$("#deleteModal #groupId").val(),
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteModal").modal("hide");
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
							<form action='<c:url value="<%=GroupPath.BASE + GroupPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >用户组名称</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="name" id="name"  value="${group.name }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=GroupPath.BASE + GroupPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=GroupPath.BASE + GroupPath.ADD%>">
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
								用户组列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>用户组编号</th>
											<th>用户组名称</th>
											<th>用户组类型</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${groups}" var="groupTamp" varStatus="status">
											<tr class="bank" >
												<td>${groupTamp.id}</td>
												<td>${groupTamp.name }</td>
												<td>${groupTamp.type}</td>
												<td>
													<%-- <gyzbadmin:function url="<%=GroupPath.BASE + GroupPath.UPDATE %>">
														<a href="#updateBankModal"  data-toggle='modal' class="tooltip-success updateBank" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function> --%>
													<gyzbadmin:function url="<%=GroupPath.BASE + GroupPath.DELETE%>">
														<input type="hidden" value="${groupTamp.id}" id="hiddenValue">
														<a href="#deleteModal"  data-toggle='modal' class="tooltip-error delGroup" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty groups}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty groups}">
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
		            <h4 class="modal-title" id="myModalLabel">新增用户组</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">用户组名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="name" name="name" style="width:80%" >
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
		
		<div class="modal fade" id="updateBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新银行信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=BankPath.BASE + BankPath.UPDATE %>' method="post" id="updateBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">支付系统行号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="bankCode" name="bankCode"  readonly="readonly" style="width:80%" >
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">支付系统行别<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="bankType" name="bankType"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="bankName" name="bankName" style="width:80%" >
							</td>
						<tr>
							<td class="td-left">7分钱自定义行别</td>
							<td class="td-right">
									<input type="text" id="myBankType" name="myBankType" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">城市编码</td>
							<td class="td-right">
									<input type="text" id="cityCode" name="cityCode" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属CCPC代码</td>
							<td class="td-right">
									<input type="text" id="ccpc" name="ccpc"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">排序号</td>
							<td class="td-right">
									<input type="text" id="orderBy" name="orderBy" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBankBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除用户组信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该用户组[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="groupId" id="groupId">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

