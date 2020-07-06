<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.bank.BankPath"%>
<%@page import="com.qifenqian.bms.workflow.user.controller.UserPath"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>工作流用户管理</title>
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
			$('.search-table #id').val('');
		});
		
		
		var usersJson= ${users};		
		var userTrList=$("tr.user");
		$.each(usersJson,function(i,value){
			$.data(userTrList[i],"user",value);
		}); 
		
		$(".addBtn").click(function(){
			var id = $("#addModal #id").val();
			if(kong.test(id)) {
				$.gyzbadmin.alertFailure('用户id不可为空');
				return;
			}
			
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=UserPath.BASE + UserPath.ADD %>',
				data:
				{
					"id" 	: id,
					"firstName" :  $("#addModal #first").val(),
					"lastName" :  $("#addModal #last").val(),
					"userInGroup":  $("#addModal #userInGroup").val()
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
		});
		
		$(".selectAddLink").click(function(){
			$("#typeModel").val("add");
		});
		
		$(".selectUpdateLink").click(function(){
			$("#typeModel").val("update");
		});
		$(".selectGroupBtn").click(function(){
			var group = document.getElementsByName("addGroupBox");
			var userIn="";
			for(var i=0;i<group.length;i++){
				if(group[i].checked){
					userIn =userIn+ group[i].id+",";
				}
			}
			
			userIn = userIn.substring(0,userIn.length-1);
			var type = $("#typeModel").val();
			if(type=='add'){
				$("#addModal  #userInGroup").val(userIn);
			}
			if(type=='update'){
				$("#updateModal  #group").val(userIn);
			}
			$('#selectGroupModal').modal('hide');
		});
		
		
		
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
		
		$(".updateUser").click(function(){
			var user = $.data($(this).parent().parent()[0],"user");
			$("#updateModal #id").val(user.id);
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=UserPath.BASE + UserPath.USERINGROUP %>',
				data:"id="+user.id,
				success:function(data){
					
					if(data.result=="SUCCESS"){
						$("#updateModal #group").val(data.resultString);
					}else{
						$.gyzbadmin.alertFailure("查询失败！");
					}
				}
			});
			
		});
		
		$(".updateBtn").click(function(){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=UserPath.BASE + UserPath.UPDATEUSER %>',
				data:{
					"id" : $("#updateModal  #id").val(),
					"group": $("#updateModal  #group").val()
				},
				success:function(data){
					
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("查询失败！");
					}
				}
			});
		});
		
	
		$(".delUser").click(function(){
			var id = $(this).prev().val();
			$("#deleteModal").find("input[name='userId']").val(id);
			$("span.sureDel").text(id);
		});
		
	
		$(".deleteBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=UserPath.BASE + UserPath.DELETE %>',
				data:"id="+$("#deleteModal #userId").val(),
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
	function addCheckItem(str){
		  var allche = document.getElementById("addGroupAllBox");    
		  var che = document.getElementsByName(str);    
		  if(allche.checked == true){   
			  for(var i=0;i<che.length;i++){    
				  che[i].checked="checked";    
				  }   
			  }else{     
			 for(var i=0;i<che.length;i++){   
					  che[i].checked=false;    
					 }  
			  }   
		
	}
	function addCheckAll(str){
	  var allche = document.getElementById(str);    
	  var che = document.getElementsByName("addGroupBox");    
		 for(var i=0;i<che.length;i++){    
			  if(!che[i].checked){
				  allche.checked=false;  
				  break;
			  } else{
				  allche.checked="checked";   
			  }
		 }   
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
							<form action='<c:url value="<%=UserPath.BASE + UserPath.LIST %>"/>' method="post">
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
											<gyzbadmin:function url="<%=UserPath.BASE + UserPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=UserPath.BASE + UserPath.ADD%>">
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
											<th>用户账号</th>
											<th>用户名称</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${users}" var="userTamp" varStatus="status">
											<tr class="user" >
												<td>${userTamp.id}</td>
												<td>${userTamp.last }${userTamp.first }</td>
												<td>
													<a href="#" class="tooltip-success updateUser" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
													</a>
													<gyzbadmin:function url="<%=UserPath.BASE + UserPath.DELETE%>">
														<input type="hidden" value="${userTamp.id}" id="hiddenValue">
														<a href="#deleteModal"  data-toggle='modal' class="tooltip-error delUser" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty users}">
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
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改工作流用户所属组信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">用户账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="id" name="id" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">用户组<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" name="group" id="group" maxlength="100" clasS="width-80">
									<a href="#" class="tooltip-error selectUpdateLink" data-rel="tooltip" title="Select" data-toggle='modal' data-target="#selectGroupModal">
										<span><button type="button"  id="queryGroup"  class="btn btn-warning btn-xs">选择用户组</button></span>
									</a>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
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
		
		<div class="modal fade" id="selectGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width:500px;">
					        <div class="modal-header">
					           <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">选择用户组</h4>
					         </div>
					         <div class="modal-body" align="center">
					         <input type="hidden" id="typeModel" >
					          <table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th><input type="checkbox" onclick="addCheckItem('addGroupBox');" id="addGroupAllBox" name="addGroupAllBox"/></th>
											<th>组名称</th>
										</tr>
									</thead>
				
									<tbody>	
									<c:forEach items="${groups }" var="group">
										<tr class="role">
											<td><input type="checkbox" onclick="addCheckAll('addGroupAllBox');" name="addGroupBox" id="${group.id }" value="${group.name }"/></td>
											<td>${group.id }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary selectGroupBtn">确定</button>
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
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该用户[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="userId" id="userId">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

