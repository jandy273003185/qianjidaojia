<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.payIn.PayInPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>代付垫资费率管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearPayIn').click(function(){
			$('#feeNameClr').val('');
			$('#feeCodeClr').val('');
		})
		var payInListJson= ${payInList};		
		var payInTrList=$("tr.payIn");
		$.each(payInListJson,function(i,value){
			$.data(payInTrList[i],"payIn",value);
		});
		
		$(".addPayInBtn").click(function(){
			var feeCode = $("#addPayInModal #feeCode").val();
			if(kong.test(feeCode)) {
				$.gyzbadmin.alertFailure('费用代码不可为空');
				return;
			}
			var feeName = $("#addPayInModal #feeName").val();
			if(kong.test(feeName)) {
				$.gyzbadmin.alertFailure('费用名称不可为空');
				return;
			}
			var feeRate = $("#addPayInModal #feeRate").val();
			if(kong.test(feeRate)) {
				$.gyzbadmin.alertFailure('费率不可为空');
				return;
			}
			var memo = $("#addPayInModal #memo").val();
			var status =$("#addPayInModal #status").val();
			var merchantCode =$("#addPayInModal #merchantCode").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PayInPath.BASE + PayInPath.ADD %>',
				data:
				{
					"feeCode" 	: feeCode,
					"feeName" 	: feeName,
					"feeRate" 	: feeRate,
					"memo"  	: memo,
					"status"    : status,
					"merchantCode"    : merchantCode
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addPayInModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					}
				}
			});
		})
		
		$(".updatePayIn").click(function(){
		 var payIn = $.data($(this).parent().parent()[0],"payIn");
	       $('#updatePayInModal').on('show.bs.modal', function () {
	    	    $("#updatePayInModal #feeCode").val(payIn.feeCode);
				$("#updatePayInModal #feeName").val(payIn.feeName);
				$("#updatePayInModal #feeRate").val(payIn.feeRate);
				$("#updatePayInModal #memo").val(payIn.memo);
				$("#updatePayInModal #status").val(payIn.status);
				$("#updatePayInModal #merchantCode").val(payIn.merchantCode);
	       })
		  $('#updatePayInModal').on('hide.bs.modal', function () {
			    $("#updatePayInModal #feeCode").val('');
				$("#updatePayInModal #feeName").val('');
				$("#updatePayInModal #feeRate").val('');
				$("#updatePayInModal #memo").val('');
				$("#updatePayInModal #status").val('');
				$("#updatePayInModal #merchantCode").val('');
	       })
		});
		
		$(".updatePayInBtn").click(function(){
			var feeCode = $("#updatePayInModal #feeCode").val();
			if(kong.test(feeCode)) {
				$.gyzbadmin.alertFailure('费用代码不可为空');
				return;
			}
			var feeName = $("#updatePayInModal #feeName").val();
			if(kong.test(feeName)) {
				$.gyzbadmin.alertFailure('费用名称不可为空');
				return;
			}
			var feeRate = $("#updatePayInModal #feeRate").val();
			if(kong.test(feeRate)) {
				$.gyzbadmin.alertFailure('费率不可为空');
				return;
			}
			var memo = $("#updatePayInModal #memo").val();
			var status = $("#updatePayInModal #status").val();
			var merchantCode = $("#updatePayInModal #merchantCode").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PayInPath.BASE + PayInPath.UPDATE %>',
				data:
				{
					"feeCode" 	: feeCode,
					"feeName" 	: feeName,
					"feeRate" 	: feeRate,
					"memo"  	: memo,
					"status"    : status,
					"merchantCode"    : merchantCode
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updatePayInModal").modal("hide");
						},function(){
							window.location.reload();
						});
						
					}else{
						$.gyzbadmin.alertFailure("更新失败！："+data.message);
					}
				}
			});
		})
		
		$(".stopPayIn").click(function(){
			var payIn = $.data($(this).parent().parent()[0],"payIn");
			$("#stopPayInModal").find("input[name='feeCode']").val(payIn.feeCode);
			$("span.sureStop").text(payIn.feeCode);
		});
		
		$(".stopPayInBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PayInPath.BASE + PayInPath.STOP %>',
				data:"feeCode="+$("#stopPayInModal #stopfeeCode").val(),
				success:function(data){
					$.unblockUI();
					if(data.success){
						$.gyzbadmin.alertSuccess("停用成功！",function(){
							$("#stopPayInModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("停用失败！"+data.message);
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
							<form action='<c:url value="<%=PayInPath.BASE + PayInPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >费用代码</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="feeCode" id="feeCodeClr"  value="${queryBean.feeCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">费用名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="feeName" id="feeNameClr"   value="${queryBean.feeName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PayInPath.BASE + PayInPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearPayIn" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=PayInPath.BASE + PayInPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addPayInModal">
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
								代付垫资费率信息
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">费用代码</th>
											<th width="10%">费用名称</th>
											<th width="10%">费率</th>
											<th width="10%">描述</th>
											<th width="10%">状态</th>
											<th width="10%">商户编号</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${payInList}" var="payIn" varStatus="status">
											<tr class="payIn" >
												<td>${payIn.feeCode }</td>
												<td>${payIn.feeName }</td>
												<td>${payIn.feeRate }</td>
												<td>${payIn.memo }</td>
												
												<td>
													<c:if test="${payIn.status == '00'}">有效</c:if>
													<c:if test="${payIn.status == '99'}">失效</c:if>
												</td>
												<td>${payIn.merchantCode }</td>
												<td>
													<gyzbadmin:function url="<%=PayInPath.BASE + PayInPath.UPDATE%>">
														<a href="#updatePayInModal"  data-toggle='modal' class="tooltip-success updatePayIn" data-rel="tooltip" title="Edit">
															<span class="green">
																编辑
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=PayInPath.BASE + PayInPath.STOP%>">
														<a href="#stopPayInModal"  data-toggle='modal' class="tooltip-error stopPayIn" data-rel="tooltip" title="Stop">
															<span class="red">
																停用
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty payInList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty payInList}">
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
	
	<div class="modal fade" id="addPayInModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增代付垫资费率</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<c:url value="<%=PayInPath.BASE + PayInPath.ADD %>"/>' method="post" id="addPayInForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">费用代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="feeCode" name="feeCode" style="width:80%" onkeyup="this.value=this.value.replace(/\D/g,'')">
							</td>
						</tr>
						<tr>
							<td class="td-left">费用名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="feeName" name="feeName" style="width:80%">
							</td>
						<tr>
							<td class="td-left">费率</td>
							<td class="td-right">
								<input type="text" id="feeRate" name="feeRate" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">描述</td>
							<td class="td-right">
									<input type="text" id="memo" name="memo" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left">状态</td>
							<td class="td-right">
								<input type="text" id="status" name="status" style="width:80%" value="可用" readonly="readonly">
									<!-- <select name="status" id="status" style="width:80%">
												<option value="">--请选择--</option>
												<option value="00">可用</option>
												<option value="99">失效</option>
									</select> -->
									
							</td>
						</tr>
						<tr>
							<td class="td-left">商户编号</td>
							<td class="td-right">
									<input type="text" id="merchantCode" name="merchantCode" style="width:80%" >
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addPayInBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updatePayInModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新代付垫资费率信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=PayInPath.BASE + PayInPath.UPDATE %>' method="post" id="updatePayInForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">费用代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="feeCode" name="feeCode"  readonly="readonly" style="width:80%" >
							</td>
						</tr>
						
						<tr>
							<td class="td-left">费用名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="feeName" name="feeName" style="width:80%" >
							</td>
						<tr>
							<td class="td-left">费率</td>
							<td class="td-right">
									<input type="text" id="feeRate" name="feeRate" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">描述</td>
							<td class="td-right">
									<input type="text" id="memo" name="memo"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">状态</td>
							<td class="td-right">
								<select name="status" id="status" style="width:80%" disabled>
												<option value="00">可用</option>
												<option value="99">失效</option>
								</select> 
							</td>
						</tr>
						<tr>
							<td class="td-left">商户编号</td>
							<td class="td-right">
									<input type="text" id="merchantCode" name="merchantCode"  style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updatePayInBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="stopPayInModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">停用代付垫资费率信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要停用该费率[<span class="sureStop"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="feeCode" id="stopfeeCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary stopPayInBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
		
		
		
		<div class="modal fade" id="deletePayInModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除代付垫资费率信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该信息[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="feeCode" id="delfeeCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deletePayInBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

