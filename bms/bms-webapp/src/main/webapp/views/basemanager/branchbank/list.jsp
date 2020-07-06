<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.branchbank.BranchBankPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>银行支行信息管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		var branchBankListJson= ${branchBankList};		
		var branchBankTrList=$("tr.branchBank");
		$.each(branchBankListJson,function(i,value){
			$.data(branchBankTrList[i],"branchBank",value);
		});
		
		$(".clearBranchBank").click(function (){
			$(".search-table #bankCode").val('');
			$(".search-table #bankName").val('');
			$(".search-table #bankAddress").val('');
		})
		
		/**新增**/
		$(".addBranchBankBtn").click(function(){
			var bankCnaps = $("#addBranchBankModal #bankCnaps").val();
			if(kong.test(bankCnaps)) {
				$.gyzbadmin.alertFailure('银行联行号不可为空');
				return;
			}
			var bankCode = $("#addBranchBankModal #bankCode").val();
			if(kong.test(bankCode)) {
				$.gyzbadmin.alertFailure('银行代码不可为空');
				return;
			}
			var areaId = $("#addBranchBankModal #areaId").val();
			if(kong.test(areaId)) {
				$.gyzbadmin.alertFailure('区域编号不可为空');
				return;
			}
			var bankName = $("#addBranchBankModal #bankName").val();
			if(kong.test(bankName)) {
				$.gyzbadmin.alertFailure('银行名称不可为空');
				return;
			}
			var bankAddress = $("#addBranchBankModal #bankAddress").val();
			if(kong.test(bankAddress)) {
				$.gyzbadmin.alertFailure('银行地址不可为空');
				return;
			}
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=BranchBankPath.BASE + BranchBankPath.ADD %>',
				data:
				{
					"bankCnaps" 	: bankCnaps,
					"bankCode" 		: bankCode,
					"areaId" 		: areaId,
					"bankName" 		: bankName,
					"bankAddress"	: bankAddress
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addBranchBankModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					}
				}
			})
		})
		
		/**加载修改**/
		$(".updateBranchBank").click(function(){
			 var branchBank = $.data($(this).parent().parent()[0],"branchBank");
		       $('#updateBranchBankModal').on('show.bs.modal', function () {
		    	    $("#updateBranchBankModal #bankCnaps").val(branchBank.bankCnaps);
					$("#updateBranchBankModal #bankCode").val(branchBank.bankCode);
					$("#updateBranchBankModal #areaId").val(branchBank.areaId);
					$("#updateBranchBankModal #bankName").val(branchBank.bankName);
					$("#updateBranchBankModal #bankAddress").val(branchBank.bankAddress);
		       })
			  $('#updateBranchBankModal').on('hide.bs.modal', function () {
				    $("#updateBranchBankModal #bankCnaps").val('');
					$("#updateBranchBankModal #bankCode").val('');
					$("#updateBranchBankModal #areaId").val('');
					$("#updateBranchBankModal #bankName").val('');
					$("#updateBranchBankModal #bankAddress").val('');
		       })
		});
		
		/**修改**/
		$(".updateBranchBankBtn").click(function(){
			var bankCnaps = $("#updateBranchBankModal #bankCnaps").val();
			if(kong.test(bankCnaps)) {
				$.gyzbadmin.alertFailure('银行联行号不可为空');
				return;
			}
			var bankCode = $("#updateBranchBankModal #bankCode").val();
			if(kong.test(bankCode)) {
				$.gyzbadmin.alertFailure('银行代码不可为空');
				return;
			}
			var areaId = $("#updateBranchBankModal #areaId").val();
			if(kong.test(areaId)) {
				$.gyzbadmin.alertFailure('区域编号不可为空');
				return;
			}
			var bankName = $("#updateBranchBankModal #bankName").val();
			if(kong.test(bankName)) {
				$.gyzbadmin.alertFailure('银行名称不可为空');
				return;
			}
			var bankAddress = $("#updateBranchBankModal #bankAddress").val();
			if(kong.test(bankAddress)) {
				$.gyzbadmin.alertFailure('银行地址不可为空');
				return;
			}
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=BranchBankPath.BASE + BranchBankPath.UPDATE %>',
				data:
				{	"bankCnaps" 	: bankCnaps,
					"bankCode" 		: bankCode,
					"areaId" 		: areaId,
					"bankName" 		: bankName,
					"bankAddress"	: bankAddress
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("修改成功！",function(){
							$("#updateBranchBankModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("修改失败！"+data.message);
					}
				}
			})
		})
		
		/**加载删除**/
		$(".deleteBranchBank").click(function(){
			var branchBank = $.data($(this).parent().parent()[0],"branchBank");
			$("#deleteBranchBankModal").find("input[name='bankCnaps']").val(branchBank.bankCnaps);
			$("span.bankCnapsDel").text(branchBank.bankCnaps);
		});
		
		/**删除**/
		$(".deleteBranchBankBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=BranchBankPath.BASE + BranchBankPath.DELETE %>',
				data:"bankCnaps="+$("#deleteBranchBankModal #bankCnaps").val(),
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteBranchBankModal").modal("hide");
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
							<form action='<c:url value="<%=BranchBankPath.BASE + BranchBankPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >银行支付系统行号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="bankCode" id="bankCode"  value="${queryBean.bankCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">银行名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="bankName" id="bankName"   value="${queryBean.bankName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">银行地址</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="bankAddress" id="bankAddress"   value="${queryBean.bankAddress }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											<button class="btn btn-purple btn-sm btn-margin clearBranchBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=BranchBankPath.BASE + BranchBankPath.ADD %>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addBranchBankModal">
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
								银行支行信息列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">银行联行号</th>
											<th width="10%">银行支付系统行号</th>
											<th width="10%">区域编码</th>
											<th width="30%">银行名称</th>
											<th width="30%">银行地址</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${branchBankList}" var="branchBank" varStatus="status">
											<tr class="branchBank" >
												<td>${branchBank.bankCnaps}</td>
												<td>${branchBank.bankCode }</td>
												<td>${branchBank.areaId }</td>
												<td>${branchBank.bankName }</td>
												<td>${branchBank.bankAddress }</td>
												<td>
													<gyzbadmin:function url="<%=BranchBankPath.BASE + BranchBankPath.UPDATE%>">
														<a href="#updateBranchBankModal"  data-toggle='modal' class="tooltip-success updateBranchBank" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=BranchBankPath.BASE + BranchBankPath.DELETE%>">
														<a href="#deleteBranchBankModal"  data-toggle='modal' class="tooltip-error deleteBranchBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty branchBankList}">
											<tr><td colspan="6" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty branchBankList}">
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
	
	<div class="modal fade" id="addBranchBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增银行支行信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">银行联行号<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="bankCnaps" name="bankCnaps" style="width:90%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">银行支付系统行号<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankCode" name="bankCode" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">区域编号<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="areaId" name="areaId" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行名称<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankName" name="bankName" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行地址<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankAddress" name="bankAddress" style="width:90%">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBranchBankBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updateBranchBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新银行支行信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">银行联行号<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<input type="text" id="bankCnaps" name="bankCnaps" disabled="disabled" style="width:90%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">银行支付系统行号<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankCode" name="bankCode" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">区域编号<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="areaId" name="areaId" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行名称<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankName" name="bankName" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行地址<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="bankAddress" name="bankAddress" style="width:90%">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBranchBankBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="deleteBranchBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除银行支行信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该银行支行信息[<span class="bankCnapsDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden"  name="bankCnaps" id="bankCnaps">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBranchBankBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>