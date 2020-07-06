<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.fee.FeePath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>费用管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearFee').click(function(){
			$('#feeCodeClr').val('');
			$('#feeNameClr').val('');
		})
		var citys = ${feeList};
		var cityList = $("tr.fee");
		$.each(citys,function(i,value){
			$.data(cityList[i],"fee",value);
		});
		
		/*增加  */
		$(".addFeeBtn").click(function(){
			
			clearAddTips();
			var feeCode = $("#addFeeModal #feeCode").val();
			
			if(kong.test(feeCode)){
				$("#addFeeModal #feeCodeLabelAdd").text("费用编号不可为空");
				$("#addFeeModal #feeCode").focus();
				return;
			}
			var validate =true ;
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=FeePath.BASE+FeePath.VALIDATE%>',
		        data:{'feeCode':feeCode},
		        success:function(data){
		        	if(data.result=="FAIL"){
						$("#addFeeModal #feeCodeLabelAdd").text("费率代码已经被占用");
						validate = false;
					}else{
						validate = true;
					}
					 }});
			if(!validate){
				return false;
			}
			
			var feeName = $("#addFeeModal #feeName").val();
			if(kong.test(feeName)){
				$("#addFeeModal #feeNameLabelAdd").text("费用名称不可为空");
				$("#addFeeModal #feeName").focus();
				return;
			}
			
			var feeCodeDesc = $("#addFeeModal #feeCodeDesc").val();
			if(kong.test(feeCodeDesc)){
				$("#addFeeModal #feeCodeDescLabelAdd").text("费用描述不可为空");
				$("#addFeeModal #feeCodeDesc").focus();
				return;
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=FeePath.BASE+FeePath.ADD%>',{
					'feeCode':feeCode,
					'feeName':feeName,
					'feeCodeDesc':feeCodeDesc
					},function(data){
						$.unblockUI();
						if(data.result=="success"){
							$.gyzbadmin.alertSuccess("添加成功！",function(){
								$("#addFeeModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("添加失败！"+data.message,function(){
								$("#addFeeModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
		
		/* 修改弹出层  */
		$(".updateFee").click(function(){
			var fee = $.data($(this).parent().parent()[0],"fee");
			$("#updateFeeModal #feeCode").val(fee.feeCode);
			$("#updateFeeModal #feeName").val(fee.feeName);
			$("#updateFeeModal #feeCodeDesc").val(fee.feeCodeDesc);
		});
		
		
		/* 修改 */
		$(".updateFeeBtn").click(function(){
			var feeCode = $("#updateFeeModal #feeCode").val();
			
			if(kong.test(feeCode)){
				$("#updateFeeModal #feeCodeLabelAdd").text("费用编号不可为空");
				$("#updateFeeModal #feeCode").focus();
				return;
			}
			
			var feeName = $("#updateFeeModal #feeName").val();
			if(kong.test(feeName)){
				$("#updateFeeModal #feeNameLabelAdd").text("费用名称不可为空");
				$("#updateFeeModal #feeName").focus();
				return;
			}
			
			var feeCodeDesc = $("#updateFeeModal #feeCodeDesc").val();
			if(kong.test(feeCodeDesc)){
				$("#updateFeeModal #feeCodeDescLabelAdd").text("费用描述不可为空");
				$("#updateFeeModal #feeCodeDesc").focus();
				return;
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=FeePath.BASE+FeePath.UPDATE%>',{
				'feeCode':feeCode,
				'feeName':feeName,
				'feeCodeDesc':feeCodeDesc
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateFeeModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("更新失败！"+data.message,function(){
							$("#updateFeeModal").modal("hide");
						});
					}
				},'json'
			);
			
		});
		
		/* 删除*/
		$(".deleteFee").click(function(){
			
			var fee = $.data($(this).parent().parent()[0],"fee");
			$("#deleteFeeModal").find("input[name='feeCode']").val(fee.feeCode);
			$("span.sureDel").text(fee.feeCode);
		});
		
		$(".deleteFeeBtn").click(function(){
			var feeCode = $("#deleteFeeModal #delFeeCode").val();
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=FeePath.BASE+FeePath.DELETE%>',{
						'feeCode': feeCode
					},function(data){
						$.unblockUI();
						if(data.result=="success"){
							$.gyzbadmin.alertSuccess("删除成功！",function(){
								$("#deleteFeeModal").modal("hide");
							},function(){
								window.location.reload();
							});
						}else{
							
							$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
								$("#deleteFeeModal").modal("hide");
							});
						}
					},'json'
				);
		});
		
	});
	
	
	function clearAddTips(){
		
		$("#addFeeModal #feeCodeLabelAdd").text("");
		$("#addFeeModal #feeNameLabelAdd").text("");
		$("#addFeeModal #feeCodeDescLabelAdd").text("");
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
							<form action='<c:url value="<%=FeePath.BASE + FeePath.LIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">费用代码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="feeCode"  id="feeCodeClr" placeholder="费用代码"  value="${feeCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">费用名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="feeName" id="feeNameClr" placeholder="费用名称"  value="${feeName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=FeePath.BASE + FeePath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearFee" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=FeePath.BASE + FeePath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addFeeModal">
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
								费用列表
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">费用代码</th>
											<th width="40%">费用名称</th>
											<th width="40%">描述</th>
											<th width="10%">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${fees}" var="fee">
											<tr class="fee">
												<td>${fee.feeCode }</td>
												<td>${fee.feeName }</td>
												<td>${fee.feeCodeDesc}</td>
												<td>
													<gyzbadmin:function url="<%=FeePath.BASE + FeePath.UPDATE%>">
													<a href="#" class="tooltip-success updateFee" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateFeeModal">
														<span class="green">
															<i class="icon-edit bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=FeePath.BASE + FeePath.DELETE%>">
													<a href="#" class="tooltip-error deleteFee" data-rel="tooltip" title="Delete" data-toggle="modal" data-target="#deleteFeeModal">
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty fees}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty fees}">
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
	
	<div class="modal fade" id="addFeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增费用</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">费用代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="feeCode" name="feeCode" style="IME-MODE: disabled;" onkeyup="this.value=this.value.replace(/\D/g,'')">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">费用名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="feeName" name="feeName">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">描述</td>
							<td class="td-right" width="30%">
								<input type="text" id="feeCodeDesc" name="feeCodeDesc">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addFeeBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="updateFeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新费用</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">费用代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="feeCode" name="feeCode" readonly="readonly">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">费用名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="feeName" name="feeName">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">描述</td>
							<td class="td-right" width="30%">
								<input type="text" id="feeCodeDesc" name="feeCodeDesc">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateFeeBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="deleteFeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">费用删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该费用[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="feeCode" id="delFeeCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteFeeBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

