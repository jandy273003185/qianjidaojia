<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.certify.CertifyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台商户注册审核列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style>
</head>

<body  >
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
						<%-- <input id="state_02" value="${queryBean.applyStatus }"  type="hidden"> --%>
						
							<!-- 查询条件 -->																	
						<form  id="agentForm" action='<c:url value="<%=CertifyPath.BASE + CertifyPath.IDENTITY_DETAIL %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >文件编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="fileId" id="fileId"  value="${queryBean.fileId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">验证状态：</td>
									<td class="td-right">								   
										 <select name="status" id="status" value="${queryBean.status }">
										  <option  value=""> 请选择 </option>
										  <option  value="01" > 待验证 </option>
										  <option  value="02" > 验证不通过 </option>
										  <option  value="00" > 验证通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
								</tr>
								
								<tr>
								<td class="td-left" >手机号码：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile"  value="${queryBean.mobile }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">处理状态：</td>
									<td class="td-right">								   
										 <select name="dealStatus" id="dealStatus" >
										  <option  value="" > 请选择 </option>
										  <option  value="02"> 验证通过 </option>
										  <option  value="01"> 待处理 </option>
										  <option  value="00"> 处理完成 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
								</tr>
								
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.IDENTITY_DETAIL %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">验证明细列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th>文件编号</th>
											<th>客户编号</th>
											<th>手机号码</th>	
											<th>客户名称</th>
											<th>证件号码</th>
											<th>数据日期</th>
											<th>验证状态</th>	
											<th>写入日期</th>
											<th>返回信息</th>			
											<th>处理状态</th>					
											<th>处理人</th>
											<th>处理备注</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${detailList}" var="bean">
									<input id="validate_Id" value="${bean.validateId }"  type="hidden">
									<input id="file_Id" value="${bean.fileId}"  type="hidden">
										<tr class=bean id="bean">
											<td width="10%">${bean.fileId}</td>
						
											<td style="width: 150px; overflow: hidden; text-overflow:ellipsis; white-space: nowrap;">${bean.custId }</td>
											<td>${bean.mobile }</td>
											<td>${bean.custName }</td>
											<td>${bean.certifyNo }</td>
											<td>
											 ${bean.workDate }
											</td>
											
											<td>
												<c:choose>   
							                        <c:when test="${bean.status =='01'}">  									  
							                           	 待验证								  
							                        </c:when>  
							  						<c:when test="${bean.status =='02'}">  									  
							                           	 验证不通过								  
							                        </c:when>  
							                        <c:when test="${bean.status =='00'}">  									  
							                           	 验证通过								  
							                        </c:when>  
	                                             </c:choose>
											</td>
											<td>
											 ${bean.instDate }
											</td>
											<td>${bean.rtnMsg}</td>
											<td>
												<c:choose>   
							                        <c:when test="${bean.dealStatus =='01'}">  									  
							                           	 待处理								  
							                        </c:when>  
							  						<c:when test="${bean.dealStatus =='00'}">  									  
							                           	 处理完成							  
							                        </c:when>  
							                        <c:when test="${bean.dealStatus =='02'}">  									  
							                           	 验证通过								  
							                        </c:when>  
	                                             </c:choose>
											</td>
											<td>${bean.dealUserName}</td>
											<td>${bean.dealMemo}</td>
											<td>	
												<!-- 验证不通过且未处理的给出如下处理按钮 -->
												
													<c:if test="${bean.status=='02' && bean.dealStatus!='00'}">
														<button type="button" id="dealButton"  class="btn btn-primary btn-xs" >处理</button>
													</c:if>
											</td> 
										</tr>
									</c:forEach>
									<c:if test="${empty detailList}">
										<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty detailList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
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

	
		 
		 
	
	
	<!-- 处理信息弹出框 -->
	<div class="modal fade" style="z-index:1043;" id="dealMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:30%;z-index:99;">
		      <div class="modal-content" >
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核不通过</h4>
		         </div>
		         <div class="modal-body">
		            <table 	 >
						<tr>	
							<td >请输入处理备注：</td>
						</tr>
						<tr>	
							<td>
								<textarea rows="5" cols="40" id="dealMessage" maxleng th="500" placeholder="请填写处理备注！"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary addadBtn" onclick="deal()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div>      
</body>
<script type="text/javascript" language="javascript">
$(function(){
	$("#dealButton").click(function(){
		//清空输入框
		$("#dealMessageModel #dealMessage").val('');
		$('#dealMessageModel').modal({backdrop: 'static', keyboard: false});
	});
	
});
function deal(){
	if(!trim($("#dealMessageModel #dealMessage").val())){
		alert("处理备注不能为空！");
		$("#dealMessageModel #dealMessage").val('');
		return false;	
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=CertifyPath.BASE+ CertifyPath.DEAL %>',
		data:
		{
			"validateId" 	: $("#validate_Id").val(),
			"fileId"		: $("#file_Id").val(),
			"memo"			: $("#dealMessageModel #dealMessage").val()
		},
		success:function(data){
			
			if(data.result=="SUCCESS"){
				alert(data.result);
				$.gyzbadmin.alertSuccess("处理完成！",function(){
					$("#dealMessageModel").modal("hide");
				},function(){
					window.location.reload();
				});
			}else{
				$.gyzbadmin.alertFailure("处理失败！"+data.message);
			}
		}
	})
}
	//清空查询框
	$('.clearMerchantSearch').click(function(){
	
		$('.search-table #fileId').val('');
		$('.search-table #mobile').val('');
		$('.search-table #status').val('');
		$('.search-table #dealStatus').val('');
	});


</script>

</html>