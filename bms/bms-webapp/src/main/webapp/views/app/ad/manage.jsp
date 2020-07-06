<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.app.ad.AdManagePath" %> 
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>APP登陆页广告管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.uploadImage{ float:left; 
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
			}
	</style>
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
var pitureChange = false;
$(function(){
	var adLists = ${adManageList};
	var adList = $("tr.adManage");
	
	$.each(adLists,function(i,value){
		$.data(adList[i],"adManage",value);
	});
	//清除查询条件
	$(".clearAdManage").click(function(){
		$('.search-table #status').val("");
		$('.search-table #adName').val("");
	});
	
	$('.certAttribute1Click').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("certAttribute1");
		return previewImage(divObj,imageObj,obj); 
	});
	//新增广告提交按钮
	$(".addBtn").click(function(){
		
		var adName=$("#addAdModal").find("#adName").val();
		if(kong.test(adName)){
			$.gyzbadmin.alertFailure("广告名称不可为空");
			return;
		}
		var certAttribute1 = $("#addAdModal").find("#certAttribute1").val();
		
		if(kong.test(certAttribute1)) {
			$.gyzbadmin.alertFailure("广告图片不可为空");
			return;
		}
		var showTime=$("#addAdModal").find("#showTime").val();
		if(kong.test(showTime)){
			$.gyzbadmin.alertFailure("显示时间不可为空");
			return;
		}
		$.ajaxFileUpload({  
	        url :"http://192.168.1.155:8080/sevenpay-fileserver-web/app/imgupload.do",
	        secureuri : false,
	        fileElementId : ['certAttribute1'],
	        dataType : 'jsonp',
			jsonp:'callback', 
	        success : function(data, status) {  
	        	$.unblockUI();
	      		alert(data);
	        	if(data.result=='SUCCESS'){
	        		var imgPath = data.pathName;
	        		$.blockUI();
	        		$.post(window.Constants.ContextPath +"<%=AdManagePath.BASE + AdManagePath.ADD %>",
	        				{
	        					'adName':adName,
	        					'imgPath':imgPath,
	        					'showTime':showTime
	        				},
	        				function(data){
	        					$.unblockUI();
	        					if(data.result=="SUCCESS"){
	        						$.gyzbadmin.alertSuccess("新增成功！",function(){
	        							$("#addAdModal").modal("hide");
	        						},function(){
	        							window.location.reload();
	        						});      						
	        					}else{
	        							$.gyzbadmin.alertFailure("新增失败！"+data.message,function(){
	        								$("#addAdModal").modal("hide");
	        							});
	        					}
	        				},'json'
	        				);	      		
	    	 	}else{
	    	 		$.gyzbadmin.alertFailure('上传失败:' + data.message);	
	    	 	}
	        }
		}); 
		
		
	});

	
	//编辑广告回显信息
	$(".updateAdLink").click(function(){
		 var adManage = $.data($(this).parent().parent()[0],"adManage");
		 
	       $('#updateAdModal').on('show.bs.modal', function () {
	    	    $("#updateAdModal #businessPhotoImageDiv").show();
	   			$("#updateAdModal #businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AdManagePath.BASE + AdManagePath.IMAGE %>?imgPath="+adManage.imgPath+"");
	    	    $("#updateAdModal #adId").val(adManage.adId);
	    	    $("#updateAdModal #adName").val(adManage.adName);
	    	    $("#updateAdModal #imgPath").val(adManage.imgPath);
				$("#updateAdModal #showTime").val(adManage.showTime);
				$("#updateAdModal #status").val(adManage.status);
			
	       });
		  $('#updateAdModal').on('hide.bs.modal', function () {
			    $("#updateAdModal #adId").val('');
			    $("#updateAdModal #adName").val('');
			    $("#updateAdModal #imgPath").val('');
				$("#updateAdModal #showTime").val('');
				$("#updateAdModal #status").val('');
			
	       });
		});
	
	
	//广告编辑提交更新
	$(".updateBtn").click(function(){
		var adName=$("#updateAdModal").find("#adName").val();
		
		if(kong.test(adName)){
			$.gyzbadmin.alertFailure("广告名称不可为空");
			return;
		}
		
		var imgPath=$("#updateAdModal").find("#imgPath").val();
		if(kong.test(imgPath)){
			$.gyzbadmin.alertFailure("图片链接不可为空");
			return;
		}
		var showTime=$("#updateAdModal").find("#showTime").val();
		if(kong.test(showTime)){
			$.gyzbadmin.alertFailure("显示时间不可为空");
			return;
		}
		var adId=$("#updateAdModal").find("#adId").val();
		var Adstatus=$("#updateAdModal").find("#status").val();
		
		if(pitureChange == true){
			$.blockUI();
			$.ajaxFileUpload({  
		        url : window.Constants.ContextPath +"<%=AdManagePath.BASE + AdManagePath.FILEUPLOAD %>",
		        secureuri : false,
		        fileElementId : ['businessPhoto'],
		        dataType : 'json',  
		        success : function(data, status) {  
		        	$.unblockUI();
		        	if(data.result=='SUCCESS'){
		        		var newImagePath = data.pathName;
		        		$.blockUI();
		        		$.post(window.Constants.ContextPath +"<%=AdManagePath.BASE + AdManagePath.UPDATE %>",
		        				{
		        					'adId':adId,
		        					'adName':adName,
		        					'imgPath':newImagePath,
		        					'showTime':showTime,
		        					'status':Adstatus
		        				},
		        				function(data){
		        					$.unblockUI();
		        					if(data.result=="SUCCESS"){
		        						$.gyzbadmin.alertSuccess("修改成功！",function(){
		        							$("#updateAdModal").modal("hide");
		        						},function(){
		        							window.location.reload();
		        						});		
		        					}else{
		        						$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
		        							$("#updateAdModal").modal("hide");
		        						});
		        					}
		        				},'json'
		        				);
		        		
		    	 	}else{
		    	 		$.gyzbadmin.alertFailure('图片上传失败:' + data.message);	
		    	 	}
		        }
		   
			}); 	
		}
		
		if(pitureChange == false){
			$.post(window.Constants.ContextPath +"<%=AdManagePath.BASE + AdManagePath.UPDATE %>",
    				{
    					'adId':adId,
    					'adName':adName,
    					'imgPath':imgPath,
    					'showTime':showTime,
    					'status':Adstatus
    				},
    				function(data){
    					$.unblockUI();
    					if(data.result=="SUCCESS"){
    						$.gyzbadmin.alertSuccess("修改成功！",function(){
    							$("#updateAdModal").modal("hide");
    						},function(){
    							window.location.reload();
    						});		
    					}else{
    						$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
    							$("#updateAdModal").modal("hide");
    						});
    					}
    				},'json'
    		);
		}
		
	});
	
	
	//删除确认提示框
	$(".deleteAdLink").click(function(){
		var adManage = $.data($(this).parent().parent()[0],"adManage");		
		$("#deleteAdModal").find("input[name='adId']").val(adManage.adId);
		$("span.sureDel").text(adManage.adName);
		
	});
	
	
	//确认删除
	$(".deleteAdtBtn").click(function(){
		
		var adId= $("#deleteAdModal #adId").val();
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=AdManagePath.BASE + AdManagePath.DELETE %>',
			data:{
				'adId':adId
			},
			success:function(data){
				$.unblockUI();
				
				if(data.result=='SUCCESS'){
					
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteAdModal").modal("hide");
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

function showBusinessPhotoImage(obj){  
	 pitureChange = true;
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}
function bigImg(obj){
   $('#showImageDiv #showImage').attr("src",obj.src);
}

function onLoadStatus(){
	$('.search-table #status').val($("#searchStatus").val());

	
}
function showCertAttribute1Image(obj){  
	 var divObj = document.getElementById("certAttribute1Div");  
	 var imageObj = document.getElementById("certAttribute1Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	
	 return result1;  
}

</script>
<body onload="onLoadStatus()">
	<!-- APP登录页广告管理-->
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
							<form action='<c:url value="<%=AdManagePath.BASE + AdManagePath.MANAGE %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" value="${queryBean.status }"  id="searchStatus"/>
								<tr>
									<td class="td-left">广告名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="adName" value="${queryBean.adName }" id="adName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">状态：</td>
									<td class="td-right"> 
									<!-- 	<input type="hidden" id="status" name="status" value="${queryBean.status}" > -->
										<select id="status" name="status" >
											<option value = "">- 请选择  -</option>
										    <option value = "1">- 正常  -</option>
										    <option value = "0">- 无效 -</option>    
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AdManagePath.BASE + AdManagePath.MANAGE  %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button  type="button" style="margin-left:10px;margin-right:10px;"class="btn btn-purple btn-sm  clearAdManage"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										
											<gyzbadmin:function url="<%=AdManagePath.BASE + AdManagePath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addAdModal">
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
								信用卡列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>广告名称</th>						
											<th>图片链接</th>
											<th>显示时间（单位：秒）</th>
											<th>状态</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${adManageList}" var="adManage">
											<tr class="adManage">
												<td>${adManage.adName }</td>
												<td>${adManage.imgPath }</td>
												<td>${adManage.showTime }</td>
												<td>
													<c:if test="${adManage.status==1}">正常</c:if>
													<c:if test="${adManage.status==0}">无效</c:if>
												</td>	
												<td>${adManage.createUser }</td>	
												<td>
													<fmt:formatDate value="${adManage.createTime }" pattern="yyyy-MM-dd HH:mm"/>	
												</td>																	
												<td>
													<gyzbadmin:function url="<%=AdManagePath.BASE + AdManagePath.UPDATE%>">
														<a href="#updateAdModal"  data-toggle='modal' class="tooltip-success updateAdLink"  data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=AdManagePath.BASE + AdManagePath.DELETE%>">
														<a href="#deleteAdModal" data-toggle='modal' class="tooltip-error deleteAdLink" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty adManageList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty adManageList}">
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

	
	<div class="modal fade" id="addAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" style="width:40%">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">新增广告</h4>
	         </div>
	         <div class="modal-body">
	         	<form action='<%=AdManagePath.BASE + AdManagePath.ADD %>' method="post" id="addadForm">
	            <table class="modal-input-table" style="width: 100%;">
					<tr>	
						<td class="td-left" width="20%">广告名<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="adName" name="adName" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left">图片上传<span style="color:red">*</span></td>
						<td class="td-right">							
						  <a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
								<label id="certAttribute1Div" class="uploadImage">  
								        <img  id="certAttribute1Image" style="width:100%;height:100%;display:none"/>
								</label>
							</a>
							<div style="float:left;margin-top:75" >
							<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
							</div>
						</td>
					</tr>
					<tr>
							<td class="td-left" width="20%">显示时间/秒<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="text" id="showTime" name="showTime" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:80%"	>
							</td>
					</tr>
	            </table>
	            </form>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary addBtn">提交</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	<!-- 修改广告信息 -->  
	<div class="modal fade" id="updateAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" style="width:40%">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">编辑广告</h4>
	         </div>
	         <div class="modal-body">
	         	
	            <table class="modal-input-table" style="width: 100%;">
	            	<input type="hidden" id="adId" name="adId" >
					<tr>	
						<td class="td-left" width="20%">广告名称<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="adName" name="adName" style="width:80%">
						</td>
					</tr>
					<tr>	
						<td class="td-left" width="20%">图片路径<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="imgPath" name="imgPath" disabled="disabled" style="width:80%">
						</td>
					</tr>
					<tr>
						<td class="td-left">广告图片<span style="color:red">*</span></td>
						<td class="td-right">	
								<a data-toggle='modal' class="tooltip-success" data-target="#previewImageModal" >
									<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
									  <img  id="businessPhotoImageDiv" onclick="bigImg(this)" src=""  style="width:100%;height:100%;display:none"  />										  
									</label>
								</a>
								<div class="updateImageDiv" style="float:left; margin-top:75 " >
									<input type="file" name="businessPhoto" id="businessPhoto"  onchange="showBusinessPhotoImage(this)"  />
								 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>						
						</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">显示时间/秒<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<input type="text" id="showTime" name="showTime" onkeyup="value=value.replace(/[^\d]/g,'')" style="width:80%"	>
						</td>
					</tr>
					<tr>
						<td class="td-left" width="20%">状态<span style="color:red">*</span></td>
						<td class="td-right" width="80%">
							<select name="status" id="status" style="width:70px;">
								<option value="1">正常</option>
								<option value="0">无效</option> 
							</select>
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

	<!-- 删除确认 -->
	<div class="modal fade" id="deleteAdModal" style="margin-top:150px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" style="width: 600px;">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
	            <h4 class="modal-title" id="myModalLabel">删除广告信息</h4>
	         </div>
	         <div class="modal-body" align="center">
	         	<font style="font-weight: bold; font-size: 15px;">您确定要删除<span style="color:red;" class="sureDel"></span>的广告信息吗？</font>
	         </div>
	         <div class="modal-footer">
	         	<input type="hidden" name="adId" id="adId">
	            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn btn-primary deleteAdtBtn">确定</button>
	         </div>
	      </div><!-- /.modal-content -->
	     </div>
	</div><!-- /.modal -->
	
	<!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   	 	<div class="modal-dialog" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
		</div> 
</body>
</html>

