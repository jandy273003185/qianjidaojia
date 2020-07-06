<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.certify.CertifyPath" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>证件管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>

<script type="text/javascript">

$(function(){
	$('.clearCertify').click(function(){
		$('#certifyNameClr').val('');
		$('#certifyTypeClr').val('');
	})
	var certifys = ${certifyList};
	var certifyList = $("tr.certify");
	$.each(certifys,function(i,value){
		$.data(certifyList[i],"certify",value);
	});
	
	/*删除弹出层准备  */
	$(".deleteCertify").click(function(){
		var certify = $.data($(this).parent().parent()[0],"certify");
		$("#deleteCertifyModal #delcertifyType").val(certify.certifyType);
		$("#deleteCertifyModal span.sureDel").text(certify.certifyType);
	});
	
	/*删除  */
	$(".deleteCertifyBtn").click(function(){
		var certifyType = $("#deleteCertifyModal #delcertifyType").val();
		$.blockUI();
		
		$.post(window.Constants.ContextPath +'<%=CertifyPath.BASE+CertifyPath.DELETE %>',{
			'certifyType':certifyType
			},function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteCertifyModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					
					$.gyzbadmin.alertFailure("删除失败！"+data.message,function(){
						$("#deleteCertifyModal").modal("hide");
					});
				}
			},'json'
				
		);
		
	});
	
	/*增加  */
	$(".addCertifyBtn").click(function(){
		
		clearAddTips();
		
		var certifyType = $("#addCertifyModal #certifyType").val();
		if(kong.test(certifyType)){
			$("#addCertifyModal #certifyTypeLabelAdd").text("证件编号不可为空");
			$("#addCertifyModal #certifyType").focus();
			return;
		}
		
		var certifyName = $("#addCertifyModal #certifyName").val();
		if(kong.test(certifyName)){
			$("#addCertifyModal #certifyNameLabelAdd").text("证件名称不可为空");
			$("#addCertifyModal #certifyName").focus();
			return;
		}
		
		$.blockUI();
		$.post(window.Constants.ContextPath +'<%=CertifyPath.BASE+CertifyPath.ADD%>',{
				'certifyType':certifyType,
				'certifyName':certifyName
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addCertifyModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("添加失败！"+data.message,function(){
							$("#addCertifyModal").modal("hide");
						});
					}
				},'json'
			);
	});
	
	/* 修改 */
	$(".updateCertify").click(function(){
		var certify = $.data($(this).parent().parent()[0],"certify");
		
		$("#updateCertifyModal").find("input[name='certifyType']").val(certify.certifyType);
		$("#updateCertifyModal").find("input[name='certifyName']").val(certify.certifyName);
	});
	
	
	
	$(".updateCertifyBtn").click(function(){
		
		
		clearUpdateTips();
		var certifyType = $("#updateCertifyModal #certifyType").val();
		
		var certifyName = $("#updateCertifyModal #certifyName").val();
		
		if(kong.test(certifyType)){
			$("#updateCertifyModal #certifyTypeLabelUpdate").text("证件编号不可为空");
			$("#updateCertifyModal #certifyType").focus();
			return;
		}
		
		if(kong.test(certifyName)){
			
			$("#updateCertifyModal #certifyNameLabelUpdate").text("证件名称不可为空");
			$("#updateCertifyModal #certifyName").focus();
			return;
		}
		
		$.blockUI();
		$.post(window.Constants.ContextPath +'<%=CertifyPath.BASE+CertifyPath.UPDATE%>',{
					'certifyType':certifyType,
					'certifyName':certifyName
				},function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateCertifyModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("更新失败！"+data.message,function(){
							$("#updateCertifyModal").modal("hide");
						});
					}
				},'json'
			);
	});
	
});

function clearAddTips(){
	
	$("#addCertifyModal #certifyTypeLabelAdd").text("");
	$("#addCertifyModal #certifyNameLabelAdd").text("");
}

function clearUpdateTips(){
	$("#updateCertifyModal #certifyTypeLabelUpdate").text("");
	$("#updateCertifyModal #certifyNameLabelUpdate").text("");
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
							<form action='<c:url value="<%=CertifyPath.BASE + CertifyPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">证件编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="certifyType" id="certifyTypeClr"  value="${certifyType }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">证件类型</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="certifyName" id="certifyNameClr"  value="${certifyName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearCertify" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addCertifyModal">
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
								证件列表
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="15%">证件代码</th>
											<th width="70%">证件类型</th>
											<th width="15%">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${certifys }" var="certify">
											<tr class="certify">
												<td>${certify.certifyType }</td>
												<td>${certify.certifyName }</td>
												<td>
													<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.UPDATE%>">
														<a href="#" class="tooltip-success updateCertify" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateCertifyModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.DELETE%>">
														<a href="#" class="tooltip-error deleteCertify" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteCertifyModal">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty certifys}">
											<tr><td colspan="3" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
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
	
	
	<div class="modal fade" id="deleteCertifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">用户删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该证件[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="certifyType" id="delcertifyType">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteCertifyBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updateCertifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新证件信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=CertifyPath.BASE + CertifyPath.UPDATE %>' method="post" id="updateBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">证件代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="certifyType" name="certifyType" readonly="readonly" clasS="width-80">
									<label id="certifyTypeLabelUpdate" class="label-tips"></label>
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">证件名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" name="certifyName" id="certifyName" maxlength="100" clasS="width-80">
									<label id="certifyNameLabelUpdate" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateCertifyBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="addCertifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增证件</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">证件代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="certifyType" name="certifyType" maxlength="2" style="IME-MODE: disabled;" onkeyup="this.value=this.value.replace(/\D/g,'')" clasS="width-80">
									<label id="certifyTypeLabelAdd" class="label-tips"></label>
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">证件类型<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" name="certifyName" id="certifyName" maxlength="100" clasS="width-80">
									<label id="certifyNameLabelAdd" class="label-tips"></label>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addCertifyBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>

