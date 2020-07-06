<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.kingdee.controller.BmsClearKingdeePayPATH"%>
<%@page import="com.qifenqian.bms.accounting.accountMaintain.controller.BmsBaseBankAccountPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js?v=${_jsVersion}"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我方账户查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body>
	<!-- 科目配置信息 -->
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
							<ul id="myTab" class="nav nav-tabs">
								<li><a href="#kingdee" data-toggle="tab" id="kingdeeData">金蝶待付数据信息</a></li>
						   		<li class="active"><a href="#MyAccount" data-toggle="tab" id="MyAccountData">我方账户查询</a></li>
						   		<!-- <li><a href="#otherAccount"  data-toggle="tab"  id="otherAccountData">对方账户信息</a></li> -->
						    	
							</ul>
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="MyAccount">
							   			<form action='<c:url value="<%=BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left">编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="accountId" id="accountId" value="${bean.accountId}">
													</span>
												</td>
												<td class="td-left" width="15%">账号</td>
												<td class="td-right" width="35%">
													<span class="input-icon">
														<input type="text" name="accountNo" id="accountNo" value="${bean.accountNo}">
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">科目编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" name="subjectId" id="subjectId" value="${bean.subjectId}">
													</span>
												</td>
												<td class="td-left" width="15%">账户名称</td>
												<td class="td-right" width="35%">
													<span class="input-icon">
														<input type="text" name="accountName" id="accountName" value="${bean.accountName}">
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">状态</td>
												<td class="td-right" colspan="3">
													<span class="input-icon">
														<select name="status" id="status" >
														
															<option value=''>--请选择--</option>
															<option value='VALID' <c:if test="${bean.status == 'VALID' }">selected</c:if> >--生效--</option>
															<option value='DISABLE' <c:if test="${bean.status == 'DISABLE' }">selected</c:if>>--无效--</option>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST %>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.ADD%>">
														<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addAccountModel">
															新增
															<i class="icon-plus-sign icon-on-right bigger-110"></i>
														</button>
														</gyzbadmin:function>
													</span>
												</td>
											</tr>
										</table>
										</form>
										<div class="list-table-header">我方账户查询</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>编号</th>
															<th>账号</th>
															<th>科目编号</th>
															<th>账户名称</th>
															<th>币别</th>
															<th>所属银行(总行)</th>
															<th>所属开户行联行号</th>
															<th>状态</th>
															<th>备注</th>
															<th>初始写入人</th>
															<th>初始写入时间</th>
															<th>最后更改人</th>
															<th>最后更改时间</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${beanList }" var="bean">
														<tr class="account">
															<td>${bean.accountId }</td>
															<td>${bean.accountNo }</td>
															<td>${bean.subjectId }</td>
															<td>${bean.accountName }</td>
															<td>${bean.currCode }</td>
															<td>${bean.bankCode }</td>
															<td>${bean.bankCnaps }</td>
															<td>${bean.status }</td>
															<td>${bean.memo }</td>
															<td>${bean.instUser }</td>
															<td>${bean.instDatetime }</td>
															<td>${bean.lupdUser }</td>
															<td>${bean.lupdDatetime }</td>
															<td>
																<gyzbadmin:function url="<%=BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.UPDATE%>">
																	<a href="#" class="tooltip-success updateAccountLink" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateAccountModal">
																		<button type="button"   id="btnAccountEdit"  class="btn btn-primary btn-xs"  >修改</button>	
																	</a>
																</gyzbadmin:function>
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty beanList}">
														<tr><td colspan="30" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty beanList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									   </div>
									</div>
								</div>	
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
	
	<div class="modal fade" id="addAccountModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增我方账户信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="accountNo" name="accountNo" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">科目编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="subjectId_add" name="subjectId" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">账户名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="accountName" name="accountName" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">币别</td>
							<td class="td-right">
								<input type="text" id="currCode" name="currCode" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属银行(总行)</td>
							<td class="td-right">
									<input type="text" id="bankCode" name="bankCode" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属开户行联行号</td>
							<td class="td-right">
									<input type="text" id="bankCnaps" name="bankCnaps" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">状态</td>
							<td class="td-right">
								<select name="status" id="status">
									<option value="">--请选择--</option>
									<option value="DISABLE">--无效--</option>
									<option value="VALID">--生效--</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addAccountBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="updateAccountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改我方账户信息</h4>
		         </div>
		         <div class="modal-body">
		         	<input type="hidden" id="temporaryAccountId"/>
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="accountNo" name="accountNo" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">科目编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="subjectId_update" name="subjectId" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">账户名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="accountName" name="accountName" style="width:80%">
							</td>
						<tr>
							<td class="td-left">币别</td>
							<td class="td-right">
								<input type="text" id="currCode" name="currCode" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属银行(总行)</td>
							<td class="td-right">
									<input type="text" id="bankCode" name="bankCode" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属开户行联行号</td>
							<td class="td-right">
									<input type="text" id="bankCnaps" name="bankCnaps" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">状态</td>
							<td class="td-right">
								<select name="status" id="status">
									<option value="">--请选择--</option>
									<option value="DISABLE">--无效--</option>
									<option value="VALID">--生效--</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateAccountBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
	<script type="text/javascript">
	
	 
	
	$(function () {	
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		var accountList= ${beanJson};		
		var account=$("tr.account");
		$.each(accountList,function(i,value){
			$.data(account[i],"account",value);
		});
		
		  $(".updateAccountLink").click(function(){
			 	var account = $.data($(this).parent().parent()[0],"account");
			 	
			 	$("#updateAccountModal").on('show.bs.modal',function(){
			 		$("#updateAccountModal #accountNo").val(account.accountNo);
				 	$("#updateAccountModal #subjectId_update").val(account.subjectId);
				 	$("#updateAccountModal #accountName").val(account.accountName);
				 	$("#updateAccountModal #currCode").val(account.currCode);
				 	$("#updateAccountModal #bankCode").val(account.bankCode);
				 	$("#updateAccountModal #bankCnaps").val(account.bankCnaps);
				 	$("#updateAccountModal #status").val(account.status);
				 	$("#updateAccountModal #temporaryAccountId").val(account.accountId);
			 	});
				$("#updateAccountModal").on('hide.bs.modal',function(){
			 		$("#updateAccountModal #accountNo").val('');
				 	$("#updateAccountModal #subjectId_update").val('');
				 	$("#updateAccountModal #accountName").val('');
				 	$("#updateAccountModal #currCode").val('');
				 	$("#updateAccountModal #bankCode").val('');
				 	$("#updateAccountModal #bankCnaps").val('');
				 	$("#updateAccountModal #status").val('');
			 	});
			 	
			 	
		  })
		
		  $("#updateAccountModal .updateAccountBtn").click(function(){
			  var accountNo = $('#updateAccountModal #accountNo').val();
			  
			  if(kong.test(accountNo)) {
					$.gyzbadmin.alertFailure('账号不可为空');
					return;
				}
			  
			  var subjectId = $('#updateAccountModal #subjectId_update').val();
			  if(kong.test(subjectId)) {
					$.gyzbadmin.alertFailure('科目编号不可为空');
					return;
				}
		  	  var subjectId2 = document.getElementById("subjectId_update");
			  if(!isNumber(subjectId2)){
				$.gyzbadmin.alertFailure("科目编号必须为数字", null, null);
				return false;
			  }
			  
			  var accountName = $('#updateAccountModal #accountName').val();
			  
			  if(kong.test(accountName)) {
					$.gyzbadmin.alertFailure('账户名称不可为空');
					return;
				}
			  var currCode = $('#updateAccountModal #currCode').val();
			  
			  if(kong.test(currCode)) {
					$.gyzbadmin.alertFailure('币别不可为空');
					return;
				}
			  var bankCode = $('#updateAccountModal #bankCode').val();
			  if(kong.test(bankCode)) {
					$.gyzbadmin.alertFailure('所属银行不可为空');
					return;
				}
			  var bankCnaps = $('#updateAccountModal #bankCnaps').val();
			  if(kong.test(bankCnaps)) {
					$.gyzbadmin.alertFailure('所属开户行联行号不可为空');
					return;
				}
			  var status = $('#updateAccountModal #status').val();
			  if(kong.test(status)) {
					$.gyzbadmin.alertFailure('请选择状态');
					return;
				}
			  
			  $.post(window.Constants.ContextPath+'<%=BmsBaseBankAccountPath.BASE+BmsBaseBankAccountPath.UPDATE%>',{
					 "accountNo" : accountNo,
					 "subjectId" : subjectId,
					 "accountName" : accountName,
					 "currCode" : currCode,
					 "bankCode" : bankCode,
					 "bankCnaps" : bankCnaps,
					 "status" : status,
					 "accountId" : $("#updateAccountModal #temporaryAccountId").val()
					 },
					 function(data){
						 if(data.result=="SUCCESS"){
								$.gyzbadmin.alertSuccess("修改成功！",function(){
									$("#updateAccountModal").modal("hide");
								},function(){
									window.location.reload();
								});
							}else{
								
								$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
									$("#updateAccountModal").modal("hide");
								});
							}
					 },'json'
				  );
			  
			  
		  });
		  
	    $("#addAccountModel").on('hide.bs.modal',function(){
			  	$("#addAccountModel #accountNo").val('');
			 	$("#addAccountModel #subjectId_add").val('');
			 	$("#addAccountModel #accountName").val('');
			 	$("#addAccountModel #currCode").val('');
			 	$("#addAccountModel #bankCode").val('');
			 	$("#addAccountModel #bankCnaps").val('');
			 	$("#addAccountModel #status").val('');
		  });
		  
		  $("#addAccountModel .addAccountBtn").click(function(){
			  
			  var accountNo = $('#addAccountModel #accountNo').val();
			  
			  if(kong.test(accountNo)) {
					$.gyzbadmin.alertFailure('账号不可为空');
					return;
				}
			  
			  var subjectId = $('#addAccountModel #subjectId_add').val();
			  if(kong.test(subjectId)) {
					$.gyzbadmin.alertFailure('科目编号不可为空');
					return;
				}
		  	  var subjectId2 = document.getElementById("subjectId_add");
			  if(!isNumber(subjectId2)){
				$.gyzbadmin.alertFailure("科目编号必须为数字", null, null);
				return false;
			  }
			  
			  
			  var accountName = $('#addAccountModel #accountName').val();
			  
			  if(kong.test(accountName)) {
					$.gyzbadmin.alertFailure('账户名称不可为空');
					return;
				}
			  var currCode = $('#addAccountModel #currCode').val();
			  
			  if(kong.test(currCode)) {
					$.gyzbadmin.alertFailure('币别不可为空');
					return;
				}
			  var bankCode = $('#addAccountModel #bankCode').val();
			  if(kong.test(bankCode)) {
					$.gyzbadmin.alertFailure('所属银行不可为空');
					return;
				}
			  var bankCnaps = $('#addAccountModel #bankCnaps').val();
			  if(kong.test(bankCnaps)) {
					$.gyzbadmin.alertFailure('所属开户行联行号不可为空');
					return;
				}
			  var status = $('#addAccountModel #status').val();
			  if(kong.test(status)) {
					$.gyzbadmin.alertFailure('请选择状态');
					return;
				}
			  
			  $.post(window.Constants.ContextPath+'<%=BmsBaseBankAccountPath.BASE+BmsBaseBankAccountPath.ADD%>',{
				 "accountNo" : accountNo,
				 "subjectId" : subjectId,
				 "accountName" : accountName,
				 "currCode" : currCode,
				 "bankCode" : bankCode,
				 "bankCnaps" : bankCnaps,
				 "status" : status
				 },
				 function(data){
					 if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addAccountModel").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("新增失败！"+data.message,function(){
								$("#addAccountModel").modal("hide");
							});
						}
				 },'json'
			  );
		  })
	 	  
		  $("#searchSubmit").click(function(){
		  	var accountId = $('.search-table #accountId').val();
			if(!kong.test(accountId)){
				var accountId = document.getElementById("accountId");
				if(!isNumber(accountId)){
					$.gyzbadmin.alertFailure("编号必须为数字", null, null);
					return false;
				}
				
			}
			
			
			var subjectId = $('.search-table #subjectId').val();
			if(!kong.test(subjectId)){
				var subjectId = document.getElementById("subjectId");
				if(!isNumber(subjectId)){
					$.gyzbadmin.alertFailure("科目编号必须为数字", null, null);
					return false;
				}
				
			}
		
		});   
		
		  var flag2=true;
			 $("#kingdeeData").click(function(){
				 
					window.location.href="<%=request.getContextPath()+BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.LIST%>"
				 });
			 $("#MyAccountData").click(function(){
				 flag2=true;
					window.location.href="<%=request.getContextPath()+BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST%>";
				 });
			 $("#otherAccountData").click(function(){
					window.location.href="<%=request.getContextPath()+BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST%>";
				 });
			if(flag2==true){
		    	   $('#myTab li:eq(1) a').tab('show');	    
		       }
	    
	   });
	</script>
</body>
</html>					