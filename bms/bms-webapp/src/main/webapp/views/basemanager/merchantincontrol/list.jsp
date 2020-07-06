<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchantincontrol.TdMerchantInControPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>商户网关维护</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#custId").comboSelect();
	
	var merchantInControlLists = ${merchantInControlList};
	var merchantInControlList = $("tr.result");
	
	$.each(merchantInControlLists,function(i,value){
		$.data(merchantInControlList[i],"result",value);
	});
	
	$('.clearMerchantInControClr').click(function(){			
		$(".page-content #merchantCode").val("");
		$(".page-content #custName").val("");
	}) 
	// 添加 
	$(".addMerchantBtn").click(function(){
		
		var custId = $("#addMerchantModal #custId").val().trim();
		if(kong.test(custId)) {
			$.gyzbadmin.alertFailure("商户编号不可为空");
			$("#addMerchantModal #custId").focus();
			return;
		}
		
		var validate =false ;
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TdMerchantInControPath.BASE+TdMerchantInControPath.VALIDATE%>',
	        data:{'custId':custId},
	        success:function(data){
	        	if(data.result=="success"){
					validate = true;
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
					validate = false;
				}
				 }});
		if(!validate){
			return false;
		}
		var tradeType = $("#addMerchantModal #tradeType").val().trim();
		if(kong.test(tradeType)) {
			$.gyzbadmin.alertFailure("交易类型");
			$("#addMerchantModal #tradeType").focus();
			return;
		}
		
		var isSupportCreditCard = $("#addMerchantModal input[type='radio']:checked").val().trim();
		
		if(kong.test(isSupportCreditCard)) {
			$.gyzbadmin.alertFailure("信用卡不可为空");
			$("#addMerchantModal #isSupportCreditCard").focus();
			return;
		}
		
		var bandIp = $("#addMerchantModal #bandIp").val().trim();
		if(kong.test(bandIp)) {
			$.gyzbadmin.alertFailure("商户端IP不可为空");
			$("#addMerchantModal #bandIp").focus();
			return;
		}
		
		var merchantCertFilePath = $("#addMerchantModal #merchantCertFilePath").val().trim();
		/* if(kong.test(merchantCertFilePath)) {
			$.gyzbadmin.alertFailure("存放路径不可为空");
			$("#addMerchantModal #merchantCertFilePath").focus();
			return;
		} */
		
		var merchantPubKey = $("#addMerchantModal #merchantPubKey").val().trim();
		if(kong.test(merchantPubKey)) {
			$.gyzbadmin.alertFailure("商户证书公钥不可为空");
			$("#addMerchantModal #merchantPubKey").focus();
			return;
		}
		var comment = $("#addMerchantModal #comment").val().trim();
		$.blockUI();
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TdMerchantInControPath.BASE + TdMerchantInControPath.ADD %>',
			data:
			{
				"custId" 				: custId,
				"tradeType" 			: tradeType,
				"isSupportCreditCard" 	: isSupportCreditCard,
				"bandIp" 				: bandIp,
				"merchantCertFilePath"	: merchantCertFilePath,
				"merchantPubKey" 		: merchantPubKey,
				"comment" 				: comment
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addMerchantModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
				}
			}
		});
	})
	
	$(".updateMerchantLink").click(function(){
		
		 var result = $.data($(this).parent().parent()[0],"result");
		 
		 $('#updateMerchantModal').on('show.bs.modal', function () {
			 	$("#updateMerchantModal #custId").val(result.custId);
				$("#updateMerchantModal #tradeType").val(result.tradeType);
				$("#updateMerchantModal input[type='radio']:checked").val(result.isSupportCreditCard);
				$("#updateMerchantModal #bandIp").val(result.bandIp);
				$("#updateMerchantModal #merchantCertFilePath").val(result.merchantCertFilePath);
				$("#updateMerchantModal #merchantPubKey").val(result.merchantPubKey);
				$("#updateMerchantModal #comment").val(result.comment);
		 });
	      $('#updateMerchantModal').on('hide.bs.modal', function () {
				// 清除
	    	    $("#updateMerchantModal #custId").val('');
				$("#updateMerchantModal #tradeType").val('');
				$("#updateMerchantModal input[type='radio']:checked").val('');
				$("#updateMerchantModal #bandIp").val('');
				$("#updateMerchantModal #merchantCertFilePath").val('');
				$("#updateMerchantModal #merchantPubKey").val('');
				$("#updateMerchantModal #comment").val('');
		  });
	});

	$(".updateMerchantBtn").click(function(){
		
		var custId = $("#updateMerchantModal #custId").val();
		
		if(kong.test(custId)) {
			$.gyzbadmin.alertFailure("商户编号不可为空");
			$("#updateMerchantModal #custId").focus();
			return;
		}
		
		var tradeType = $("#updateMerchantModal #tradeType").val().trim();
		if(kong.test(tradeType)) {
			$.gyzbadmin.alertFailure("交易类型不能为空");
			$("#updateMerchantModal #tradeType").focus();
			return;
		}
		
		var isSupportCreditCard = $("#updateMerchantModal input[type='radio']:checked").val().trim();
		
		if(kong.test(isSupportCreditCard)) {
			$.gyzbadmin.alertFailure("信用卡不可为空");
			$("#updateMerchantModal #isSupportCreditCard").focus();
			return;
		}
		
		var bandIp = $("#updateMerchantModal #bandIp").val().trim();
		if(kong.test(bandIp)) {
			$.gyzbadmin.alertFailure("商户端IP不可为空");
			$("#updateMerchantModal #bandIp").focus();
			return;
		}
		
		var merchantCertFilePath = $("#updateMerchantModal #merchantCertFilePath").val().trim();
		/* if(kong.test(merchantCertFilePath)) {
			$.gyzbadmin.alertFailure("存放路径不可为空");
			$("#updateMerchantModal #merchantCertFilePath").focus();
			return;
		} */
		
		var merchantPubKey = $("#updateMerchantModal #merchantPubKey").val().trim();
		if(kong.test(merchantPubKey)) {
			$.gyzbadmin.alertFailure("商户证书公钥不可为空");
			$("#updateMerchantModal #merchantPubKey").focus();
			return;
		}
		
		$.blockUI();
		 $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TdMerchantInControPath.BASE + TdMerchantInControPath.UPDATE %>',
			data:
			{
				"custId" 				: custId,
				"tradeType" 			: tradeType,
				"isSupportCreditCard" 	: isSupportCreditCard,
				"bandIp" 				: bandIp,
				"merchantCertFilePath"	: merchantCertFilePath,
				"merchantPubKey" 		: merchantPubKey,
				"comment" 				:$("#updateMerchantModal #comment").val()
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("修改成功！",function(){
						$("#updateMerchantModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("修改失败！"+data.message);
				}
			}
		});
	});
	
	$(".deleteMerchantLink").click(function(){
		var result = $.data($(this).parent().parent()[0],"result");		
		$("#deleteMerchantModal").find("input[name='custId']").val(result.custId);
		$("span.sureDel").text(result.custId);
		
	});
	
	$(".deleteMerchantBtn").click(function(){
		
		var custId= $("#deleteMerchantModal #custId").val();
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=TdMerchantInControPath.BASE + TdMerchantInControPath.DELETE %>',
			data:"custId="+custId,
			success:function(data){
				$.unblockUI();
				
				if(data.result=='success'){
					
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteMerchantModal").modal("hide");
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
	<!-- 商户网关维护-->
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
							<form action='<c:url value="<%=TdMerchantInControPath.BASE + TdMerchantInControPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="merchantCode" value="${queryBean.merchantCode }" id="merchantCode">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户名称</td>
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
											<gyzbadmin:function url="<%=TdMerchantInControPath.BASE + TdMerchantInControPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantInControClr"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
											<gyzbadmin:function url="<%=TdMerchantInControPath.BASE + TdMerchantInControPath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addMerchantModal">
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
								商户网关维护
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>商户编号</th>
											<th>商家名称</th>
											<th>交易类型</th>
											<th>支持信用卡</th>
											<th>绑定的商户端IP</th>
											<th>证书路径</th>
											<th>备注</th>
											<th>创建人</th>											
											<th>创建时间</th>
											<th>修改人</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${merchantInControlList}" var="result">
											<tr class="result">
												<td>${result.merchantCode }</td>
												<td>${result.custName }</td>
												<td>${result.tradeType }</td>
												<td>${result.isSupportCreditCard }</td>
												<td>${result.bandIp }</td>
												<td>${result.merchantCertFilePath }</td>
												<td>${result.comment }</td>
												<td>${result.creater }</td>
												<td>
													<fmt:formatDate value="${result.createrTime }" pattern="yyyy-MM-dd HH:mm:ss"/>	
												</td>												
												<td>${result.modified }</td>
												<td>
													<fmt:formatDate value="${result.modifiedTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>												
												<td>
													<gyzbadmin:function url="<%=TdMerchantInControPath.BASE + TdMerchantInControPath.UPDATE%>">
														<a href="#updateMerchantModal"  data-toggle='modal' class="tooltip-success updateMerchantLink" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=TdMerchantInControPath.BASE + TdMerchantInControPath.DELETE%>">
														<a href="#deleteMerchantModal" data-toggle='modal' class="tooltip-error deleteMerchantLink" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty merchantInControlList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantInControlList}">
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
	<!-- 添加商户配置 -->  
	<div class="modal fade" id="addMerchantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加商户配置</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
						<tr>	
							<td class="td-left" width="30%">商户<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<select id="custId" name="custId">
									<option value="">输入商户名查询</option>
									<c:forEach items="${merchantList }" var="bean">
										<option value="${bean.custId }">${bean.custName }</option>
									</c:forEach>
									<label id="custIdLabel" class="label-tips"></label>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">交易类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="tradeType" name="tradeType"  clasS="width-90">
								<label id="tradeTypeLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">支持信用卡<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="radio" name="isSupportCreditCard" value="Y" checked="checked" />是 
								<input type="radio" name="isSupportCreditCard" value="N" />否
								<label id="isSupportCreditCardLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">绑定的商户端IP<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="bandIp"  id="bandIp" rows="3" clasS="width-90"></textarea>  
								<label id="bandIpLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">存放路径<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="merchantCertFilePath" name="merchantCertFilePath"  clasS="width-90">
								<label id="merchantCertFilePathLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">商户证书公钥<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="merchantPubKey" id="merchantPubKey" rows="3" clasS="width-90"></textarea>  
								<label id="merchantPubKeyLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea name="comment" id="comment" rows="3" clasS="width-90"></textarea>  
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addMerchantBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<!-- 修改商户配置 -->  
	<div class="modal fade" id="updateMerchantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改商户配置</h4>
		         </div>
		          <div class="modal-body">
		          	<input type="hidden" id="custId" name="custId">
		         	<form action='<%=TdMerchantInControPath.BASE + TdMerchantInControPath.ADD %>' method="post" id="addadForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	
						<tr>	
							<td class="td-left" width="30%">商户<span style="color:red">*</span></td>
							<td class="td-right" width="70%">
								<sevenpay:selectMerchantTag id="custId" name ="custId"  merchants="${merchantList}"  clasS="width-90"/>
								<label id="custIdLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">交易类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="tradeType" name="tradeType" maxlength="5" clasS="width-90">
								<label id="tradeTypeLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">支持信用卡<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="radio" name="isSupportCreditCard" value="Y" checked="checked" />是 
								<input type="radio" name="isSupportCreditCard" value="N" />否
								<label id="isSupportCreditCardLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">绑定的商户端IP<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="bandIp"  id="bandIp"  rows="3" clasS="width-90"></textarea>  
								<label id="bandIpLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">存放路径<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="merchantCertFilePath" name="merchantCertFilePath" clasS="width-90" >
								<label id="merchantCertFilePathLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">商户证书公钥<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea name="merchantPubKey" id="merchantPubKey" rows="3" clasS="width-90"></textarea>  
								<label id="merchantPubKeyLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea name="comment" id="comment" rows="3" clasS="width-90"></textarea>  
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateMerchantBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteMerchantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除商户配置</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该商户[<span class="sureDel"></span>]网关维护么？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" name="custId" id="custId">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteMerchantBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>

