<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.qifenqian.bms.basemanager.photo.PhotoPath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>证件认证管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.uploadImage{ float:left; 
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:100px;
			height:80px;
			}
	</style>
</head>
<script type="text/javascript">
function loadPhoto(){
	$(".search-table #certificateState").val($(".search-table #certificateStateTemp").val());
}
 $(function(){
	var authList= ${authList};
	var authTrList=$("tr.auth");
	$.each(authList,function(i,value){
		$.data(authTrList[i],"auth",value);
	});
	
	$(".clearPhotoBtn").click(function(){
		$(".search-table #custName").val('');
		$(".search-table #mobile").val('');
		$(".search-table #certificateState").val('');
		
	});
	
$(".udpatePhotoBtn").click(function(){
		
		var authId = $("#updatePhotoModal #authId").val();
		
		if(kong.test(authId)) {
			$.gyzbadmin.alertFailure("认证编号不可为空");
			return;
		}
		var custId = $("#updatePhotoModal #custId").val();
		if(kong.test(custId)) {
			$.gyzbadmin.alertFailure("客户编号不可为空");
			return;
		}
		var mobile = $("#updatePhotoModal #mobile").val();
		if(kong.test(mobile)) {
			$.gyzbadmin.alertFailure("客户手机号码不可为空");
			return;
		}
		var custName = $("#updatePhotoModal #custName").val();
		if(kong.test(custName)) {
			$.gyzbadmin.alertFailure("客户姓名不可为空");
			return;
		}
		var createTime = $("#updatePhotoModal #createTime").val();
		if(kong.test(createTime)) {
			$.gyzbadmin.alertFailure("申请时间不可为空");
			return;
		}
		var certificateState = $("#updatePhotoModal #certificateState").val();
		if(certificateState=='1') {
			$.gyzbadmin.alertFailure("请选择实名认证状态");
			return;
		}
		
		$.blockUI();
		$.post(window.Constants.ContextPath +'<%=PhotoPath.BASE + PhotoPath.AUDIT%>',{
			'authId':authId,
			'custId':custId,
			'mobile':mobile,
			'custName':custName,
			'createTime':createTime,
			'certificateState':certificateState,
			'authResultDesc':$("#updatePhotoModal #authResultDesc").val()
			},function(data){
				$.unblockUI();
				if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess("认证成功！",function(){
						$("#updatePhotoModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("认证失败！"+data.message,function(){
						$("#updatePhotoModal").modal("hide");
					});
				}
			},'json'
		);
	});
});

 
 function udpatePhotoClick(obj,option){
		if(option=='edit'){
			$('#updatePhotoModal .udpatePhotoBtn').show();
		}
		if(option=='preview'){
			$('#updatePhotoModal .udpatePhotoBtn').hide();
		}

		var auth = $.data($(obj).parent().parent()[0],"auth");
		
/* 		 $('#updatePhotoModal').on('show.bs.modal', function () { */
			 $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PhotoPath.BASE + PhotoPath.QUERY_CERTIFICATE %>',
					data:
					{
						"authId"   : auth.authId,
						"custType" : auth.custType
					},
					success:function(data){
						$("#updatePhotoModal #policeAuthResult").empty();
						$("#updatePhotoModal #authId").val(data.result.authId);
						$("#updatePhotoModal #custId").val(data.result.custId);
						$("#updatePhotoModal #custName").val(data.result.custName);
						$("#updatePhotoModal #certifyType").val(data.result.certifyType);
						$("#updatePhotoModal #certifyNo").val(data.result.certifyNo);
						$("#updatePhotoModal #mobile").val(auth.mobile);
						$("#updatePhotoModal .existTr").remove();
						if(auth.status=='01'){
							$("#updatePhotoModal #policeAuthResult").val('当前用户信息已提交，等待公安验证;'+auth.rtnMsg);
						}else if(auth.status=='02'){
							$("#updatePhotoModal #policeAuthResult").val('验证不通过;'+auth.rtnMsg);
						}else if(auth.status=='00'){
							$("#updatePhotoModal #policeAuthResult").val('验证通过;'+auth.rtnMsg);
						}else{
							$("#updatePhotoModal #policeAuthResult").val('当前用户信息暂未提交公安认证;');
						}
						
						
						var path = data.result.scanCopyPath;
						var paths = path.split(";");
						var htm = '';
						for(var i=0;i<paths.length;i++){
							htm = htm + 
							'<tr class="existTr">'+
							'<td class="td-left">证件信息'+(i+1)+':</td>'+
							'<td id="audit" class="td-right"><a href="#previewImageModal" data-toggle="modal" class="tooltip-error"  data-rel="tooltip">'+
							'<img width="200" height="150" onclick="showPhotoInfo(this)"  src="'+window.Constants.ContextPath +'<%=PhotoPath.BASE+PhotoPath.SHOW%>?path='+paths[i]+ '" />'+
							'</a></td>'+
							'</tr>';
						}
						
						$("#updatePhotoModal .photo").after(htm);
						$("#updatePhotoModal #certificateState").val(auth.certificateState);
						$("#updatePhotoModal #createTime").val(auth.createTime);
						$("#updatePhotoModal #authResultDesc").val(auth.authResultDesc);
					}
				});
/* 		 }); */
		 
		 $('#updatePhotoModal').on('hide.bs.modal', function () { 
			 $("#updatePhotoModal #custName").val('');
				$("#updatePhotoModal #certifyType").val('');
				$("#updatePhotoModal #certifyNo").val('');
				$("#updatePhotoModal #mobile").val('');
				$("#updatePhotoModal #certificateState").val('');
				$("#updatePhotoModal #authResultDesc").val('');
				$("#updatePhotoModal #createTime").val('');
				$("#updatePhotoModal .existTr").remove();
				
		 });
	 };
	
 function showPhotoInfo(obj){
     $('#showImageDiv #showImage').attr("src",obj.src);
 };
</script>
<body  onload="loadPhoto()">
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
							<form action='<c:url value="<%=PhotoPath.BASE + PhotoPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">客户名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="custName" id="custName" value="${queryBean.custName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >手机号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile" value="${queryBean.mobile}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">实名认证状态</td>
									<td class="td-right">
										<input type="hidden"  name="certificateStateTemp" id="certificateStateTemp"  value="${queryBean.certificateState}">
										<select id="certificateState" name="certificateState">
											<option value="">- 请选择 -</option>
											<option value="1">待认证</option>
											<option value="0">认证通过</option>
											<option value="2">认证不通过</option>
											<option value="9">取消</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PhotoPath.BASE + PhotoPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>
											<button  class="btn btn-purple btn-sm btn-margin clearPhotoBtn" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								证件列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout:fixed;">
									<thead>
										<tr>
											<th>客户名称</th>
											<th>用户手机号</th>
											<th>创建时间</th>
											<th>用户类型</th>
											<th>实名认证状态</th>
											<th>认证人</th>
											<th>认证时间</th>
											<th>认证情况描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${authList}" var="auth">
											<tr class="auth" >
												<td>${auth.custName }</td>
												<td>${auth.mobile }</td>
												<td>${auth.createTime }</td>
												<td>
													<c:if test="${auth.custType =='0'}">
														客户
													</c:if>
													<c:if test="${auth.custType =='1'}">
														商户
													</c:if>
												</td>
												<td>
													<c:if test="${auth.certificateState=='0' }">
														认证通过
													</c:if>
													<c:if test="${auth.certificateState=='1' }">
														待认证
													</c:if>
													<c:if test="${auth.certificateState=='2' }">
														认证不通过
													</c:if>
													<c:if test="${auth.certificateState=='9' }">
														取消
													</c:if>
												</td>
												<td>${auth.certifyUser }</td>
												<td>${auth.certifyDatetime}</td>
												<td>${auth.authResultDesc }</td>
												<td>
													<gyzbadmin:function url="<%=PhotoPath.BASE + PhotoPath.AUDIT%>">
														<a href="#updatePhotoModal"  data-toggle='modal' class="tooltip-error" onclick="udpatePhotoClick(this,'preview')" data-rel="tooltip" title="查看认证结果">
															<span class="green">
																<i class="iconfont icon-shenhe "></i>
															</span>
														</a>
													</gyzbadmin:function>
													<c:if test="${auth.certificateState=='1'}">
														<gyzbadmin:function url="<%=PhotoPath.BASE + PhotoPath.AUDIT%>">
															<a href="#updatePhotoModal"  data-toggle='modal' class="tooltip-success" onclick="udpatePhotoClick(this,'edit')" data-rel="tooltip" title="认证">
																<span class="green">
																	<i class="icon-edit bigger-120"></i>
																</span>
															</a>
														</gyzbadmin:function>
													</c:if>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty authList}">
											<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty authList}">
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
		<div class="modal fade" id="updatePhotoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新认证信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">编号</td>
							<td class="td-right" width="70%">
								<input type="text" id="authId" name="authId" readonly="readonly" clasS="width-80">
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="30%">客户ID</td>
							<td class="td-right" width="70%">
								<input type="text" id="custId" name="custId" readonly="readonly" clasS="width-80">
							</td>
						</tr>
		            	<tr>
							<td class="td-left">客户名称</td>
							<td class="td-right">
								<input type="text" id="custName" name="custName" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left">用户手机号</td>
							<td class="td-right">
								<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-80">
								<input type="hidden" id="mobile2" name="mobile2"  >
							</td>
						</tr>
						<tr class="photo">
							<td class="td-left">证件号码</td>
							<td class="td-right">
								<input type="text" id="certifyNo" name="certifyNo" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>	
							<td class="td-left">扫描件类型</td>
							<td class="td-right">
								<select id="certifyType" name="certifyType" disabled="disabled" clasS="width-80">
									<option value="">- 请选择  -</option>
									<option value="00">身份证</option>
									<option value="01">税务登记证</option>
									<option value="02">营业执照</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">申请时间</td>
							<td class="td-right">
								<input type="text" id="createTime" name="createTime" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left">公安认证情况</td>
							<td class="td-right">
								<textarea  id="policeAuthResult" name="policeAuthResult" rows="4" clasS="width-80"> </textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">实名认证状态</td>
							<td class="td-right">
								<select id="certificateState" name="certificateState" clasS="width-80">
									<option value="1">待认证</option>
									<option value="0">认证通过</option>
									<option value="2">认证不通过</option>
									<option value="9">取消</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">认证情况描述</td>
							<td class="td-right">
								<textarea  id="authResultDesc" name="authResultDesc" rows="4" clasS="width-80"> </textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary udpatePhotoBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:60%;height:80%;">
		         <div id="showImageDiv" style="width:100%;height:100%;">
		           <!-- <!-- <input type="hidden" name="showImageInput" id="showImageInput" class="showImageInput"> -->
		           <img id="showImage" style="width:100%;height:100%;">
		         </div>
		     </div>
		</div>
</body>
</html>