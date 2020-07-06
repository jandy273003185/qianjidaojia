<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>批量代付</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
 	$(function(){
 		var objectJson= ${object};
 		if(${objectJson.result}=="SUCCESS"){
 			$.gyzbadmin.alertSuccess("上传文件解析成功");	
 		} else{
 			$.gyzbadmin.alertFailure("上传文件解析失败");
 		}
 	/* 	var detailListJson= ${result};
		var detailTrList=$("tr.detail");
		$.each(detailListJson,function(i,value){
			$.data(detailTrList[i],"detail",value);
		}); */
		$(".addBtn").click(function(){
			/* var productCode = $("#addModal #productCode").val();
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
			
			
			
			
			$.blockUI(); */
			$.ajax({
				type:"POST",
				dataType:"json",
				 url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.SELECTLIST %>',
				data:
				{
					"batNo" 	: ${batNo}
					
				},
				success:function(data){
					var detailListJson= ${data.detailList};		
					var detailTrList=$("tr.detail");
					$.each(detailListJson,function(i,value){
						$.data(detailTrList[i],"detail",value);
					});
					/* $.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					} */
				}
			});
		})
		
		$(".updateBank").click(function(){
		 var detail = $.data($(this).parent().parent()[0],"detail");
	       $('#updateModal').on('show.bs.modal', function () {
	    	    $("#updateModal #batNo").val(detail.batNo);
	    	    $("#updateModal #rowNo").val(detail.rowNo);
				$("#updateModal #recAccountName").val(detail.recAccountName);
				$("#updateModal #recAccountNo").val(detail.recAccountNo);
				$("#updateModal #recBankCode").val(detail.recBankCode);
				$("#updateModal #transAmt").val(detail.transAmt); 
	       })
		  $('#updateModal').on('hide.bs.modal', function () {
			    $("#updateModal #batNo").val('');
			    $("#updateModal #rowNo").val('');
				$("#updateModal #recAccountName").val('');
				$("#updateModal #recAccountNo").val('');
				$("#updateModal #recBankCode").val('');
				$("#updateModal #transAmt").val('');
	       })
		});
		
		$(".updateBtn").click(function(){
			var id = $("#updateModal #batNo").val();
			if(kong.test(id)) {
				$.gyzbadmin.alertFailure('批次号不可为空');
				return;
			}
			var rowNo = $("#updateModal #rowNo").val();
			if(kong.test(rowNo)) {
				$.gyzbadmin.alertFailure('序列号不可为空');
				return;
			}
			var recAccountName = $("#updateModal #recAccountName").val();
			if(kong.test(recAccountName)) {
				$.gyzbadmin.alertFailure('收款人名称不可为空');
				return;
			}
			var recAccountNo = $("#updateModal #recAccountNo").val();
			if(kong.test(recAccountNo)) {
				$.gyzbadmin.alertFailure('收款人账号不可为空');
				return;
			}
			var recBankCode = $("#updateModal #recBankCode").val();
			if(kong.test(recBankCode)) {
				$.gyzbadmin.alertFailure('银行号不可为空');
				return;
			}
			var transAmt = $("#updateModal #transAmt").val();
			if(kong.test(transAmt)) {
				$.gyzbadmin.alertFailure('交易金额不可为空');
				return;
			}
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.UPDATE %>',
				data:
				{
					"id"	: id,
					"rowNo" 	: rowNo,
					"recAccountName" 	: recAccountName,
					"recAccountNo" 	: recAccountNo,
					"recBankCode" 	: recBankCode,
					"transAmt"	   	: transAmt
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
		
			var detail = $.data($(this).parent().parent()[0],"detail");
			$("#deleteBankModal").find("input[name='id']").val(detail.id);
			$("span.sureDel").text(detail.id);
		});
		
		$(".deleteBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				<%-- url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.DELETE %>', --%>
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
							<form action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.FILEUPLOAD %>"/>' name="fileUpload" enctype="multipart/form-data" method="post" >
							<table class="search-table">
								<tr>
									<td class="td-left" >上传文件</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="file"  name="filename" id="filename">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">付款账号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="custName" value="${qeuryBean.custName}" id="custName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.FILEUPLOAD %>">
											<button type="submit" class="btn btn-purple btn-sm">
												导入Excel
												<!-- <i class="icon-search icon-on-right bigger-110"></i> -->
											</button> 
											</gyzbadmin:function>
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.SELECTLIST%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#selectModal">
												预览
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.BATPAYMENTDATA%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#submitModal">
												提交
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>
											</span>
											</td>
											</tr>
								<%--			<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button> 
											
											
										</span>
									</td>
								</tr>--%>
							</table>
							</form>
							<div class="list-table-header">
								批量数据列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>批次号</th>
											<th>序列号</th>
											<th>收款人</th>
											<th>收款账号</th>
											<th>收款金额</th>
											<th>处理状态</th>
											<th>创建人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${detailList}" var="detail" varStatus="status">
											<tr class="detail" >
											    <td>${detail.batNo }</td>
											    <td>${detail.rowNo}</td>

												<td>${detail.recAccountName}</td>

												<td>${detail.recAccountNo }</td>
												<td>${detail.transAmt}</td>
												<td><c:if test="${detail.status =='01'}"> 
												未提交 
												</c:if> <c:if test="${detail.status =='02'}">
													已提交 
 												</c:if></td>
												
												<td>${detail.createId }</td>
												<%-- <td>
													<fmt:formatDate value="${detail.createrTime }" pattern="yyyy-MM-dd HH:mm:ss"/>	
												</td> --%>
												<td>
													<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.UPDATE%>">
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateBank" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<%-- <gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.DELETE %>"> 
														<a href="#deleteBankModal"  data-toggle='modal' class="tooltip-error delBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function> --%>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty detailList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty detailList}">
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
		            <h4 class="modal-title" id="myModalLabel">修改收款人信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            		<tr>
							<td class="td-left" width="20%">批次号</td>
							<td class="td-right" width="30%">
									<input type="text" id="batNo" name="batNo" disabled="disabled" style="width:80%" >
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="20%">序列号</td>
							<td class="td-right" width="30%">
							<%-- <sevenpay:selectProductCodeType id="productCode" name="productCode" style="width:90%"/> --%>
							<input type="text" id="rowNo" name="rowNo" disabled="disabled" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">收款人</td>
							<td class="td-right" width="30%">
							<%-- <sevenpay:selectProductNameType id="channel" name="channel" style="width:90%"/> --%>
    						<input type="text" id="recAccountName" name="recAccountName" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">收款人账号</td>
							<td class="td-right" width="30%">
									<input type="text" id="recAccountNo" name="recAccountNo" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">收款人银行号</td>
							<td class="td-right" width="30%">
							<%-- <sevenpay:selectBusiType id="BusiType" name="BusiType" style="width:90%"/> --%>
							<input type="text" id="recBankCode" name="recBankCode" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">收款人账号</td>
							<td class="td-right" width="30%">
									<input type="text" id="transAmt" name="transAmt" style="width:80%" >
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
		
		<div class="modal fade" id="deleteBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" id="deleteId">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div>
					     </div>
		</div>
</body>
</html>

