
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.advertisement.AdPath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>


<html>
<head>
	<meta charset="utf-8" />
	<title>首页广告维护</title>
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
</head>

<body onload="loadAd()">
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%> 

	<div class="main-container" id="main-container" data-backdrop="static">
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
							<form action='<c:url value="<%=AdPath.BASE + AdPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" width="10%">广告名</td>
									<td class="td-right" width="20%"> 
										<span class="input-icon">
											<input type="text"  name="adName"  id="adName"   value="${queryBean.adName}" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="10%">图片路径</td>
									<td class="td-right" width="30%">
										<span class="input-icon">
											<input type="text"  name="imagePath" id="imagePath"  value="${queryBean.imagePath}" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="10%">状态</td>
									<td class="td-right" width="20%">
										<span class="input-icon">
											<input type="hidden"  name="isValidTemp" id="isValidTemp"  value="${queryBean.isValid}" >
											<select name="isValid" id="isValid" >
												<option value = "">-请选择-</option>
												<option value = "1">-有效-</option>
												<option value = "0">-无效-</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AdPath.BASE + AdPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin "  id="clearAdvetis">
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AdPath.BASE + AdPath.ADD%>">
												<button id="addModal" class="btn btn-purple btn-sm   addAdClick" data-toggle='modal' data-target="#addAdModal">
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
								广告列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">广告名</th>										
											<th width="30%">图片路径</th>
											<th width="10%">创建人</th>
											<th width="12%">创建时间</th>
											<th width="10%">修改人</th>
											<th width="12%">修改时间</th>
											<th width="10%">是否有效</th>
											<th width="6%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${adList}" var="ad" >
											<tr class="ad" >
												<td>
													<input type="hidden" name="adId" id="adId">
													${ad.adName }
												</td>
												<td>
													${ad.imagePath }
												</td>
												<td>${ad.createId }</td>
												<td>${ad.createTime }</td>												
												<td>${ad.modifyId }</td>
												<td>${ad.modifyTime }</td>
												<td>
							                        <c:if test="${ad.isValid == '1'}">有效</c:if> 
							                        <c:if test="${ad.isValid == '0'}">无效</c:if>  
                                                </td>
												<td>
													<gyzbadmin:function url="<%=AdPath.BASE + AdPath.UPDATE%>">
														<a href="#"  class="tooltip-success updateAdClick" data-toggle='modal'  data-target="#updateAdModal"  data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty adList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty adList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div><!-- /.page-content -->
				
		<div class="modal fade" id="updateAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新广告信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=AdPath.BASE + AdPath.UPDATE %>' method="post" id="updateadForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>	
							<td class="td-left" width="20%">广告名<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="hidden" name="adId" id="adId">
								<input type="text" id="adName" name="adName" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">图片路径<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="2" cols="" id="imagePath" name="imagePath"  style="width:80%" readonly="readonly"></textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">上传图片<span style="color:red">*</span></td>
							<td class="td-right">	
									<a data-toggle='modal' class="tooltip-success" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img  id="businessPhotoImageDiv" onclick="bigImg(this)" src=""  style="width:100%;height:100%;display:none"  />										  
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>						
							</td>
						</tr>
						<tr>
							<td class="td-left">超链接（URL）<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="2" cols="" id="url" name="url" style="width:80%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">状态<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="isValid" name="isValid" style="width:80%">
									<option value="">-请选择-</option>
									<option value="1">-有效-</option>
									<option value="0">-无效-</option>
								</select>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateAdBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
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
		         	<form action='<%=AdPath.BASE + AdPath.ADD %>' method="post" id="addadForm">
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
							<td class="td-left">超链接（URL）<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="2" cols="" id="url" name="url" style="width:80%"></textarea>
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addAdBtn">提交</button>
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
<script type="text/javascript">
function loadAd(){
	$(".search-table #isValid").val($(".search-table #isValidTemp").val());
}

$(function(){
	
	$('#clearAdvetis').click(function(){			
		$(".search-table #adName").val('');
		$(".search-table #imagePath").val('');
		$(".search-table #isValid").val('');
	});
	
	var ads= ${adList};
	var adList=$("tr.ad");
	$.each(ads,function(i,value){
		$.data(adList[i],"ad",value);
	});
	
	$('.certAttribute1Click').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("certAttribute1");
		return previewImage(divObj,imageObj,obj); 
	});
	
	$('.updateAdClick').click(function(){
	
	    var ad = $.data($(this).parent().parent()[0],"ad");
		
       $('#updateAdModal').on('show.bs.modal', function () {
    	    $("#updateAdModal #businessPhotoImageDiv").show();
   			$("#updateAdModal #businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AdPath.BASE + AdPath.IMAGE %>?adId="+ad.adId+"");
    	    $("#updateAdModal #adId").val(ad.adId);
    	    $("#updateAdModal #adName").val(ad.adName);
    	    $("#updateAdModal #imagePath").val(ad.imagePath);
    	    $("#updateAdModal #url").val(ad.url);
    	    $("#updateAdModal #isValid").val(ad.isValid);
    	    
    	   
       })
		$('#updateAdModal').on('hide.bs.modal', function () {
			$("#updateAdModal #adId").val('');
    	    $("#updateAdModal #adName").val('');
    	    $("#updateAdModal #imagePath").val('');
    	    $("#updateAdModal #url").val('');
    	    $("#updateAdModal #isValid").val('');
       })
		
	})
	
	
	/**新增**/
	$('.updateAdBtn').click(function(){
		
		var adName = $("#updateAdModal #adName").val();
		if(kong.test(adName)) {
			$.gyzbadmin.alertFailure("广告名不可为空");
			$("#updateAdModal #adName").focus();
			return;
		}
		
		var url = $("#updateAdModal #url").val();
		if(kong.test(url)) {
			$.gyzbadmin.alertFailure("url不可为空");
			$("#updateAdModal #url").focus();
			return;
		}
		
		var isValid = $("#updateAdModal #isValid").val();
		if(kong.test(isValid)) {
			$.gyzbadmin.alertFailure("状态不可为空");
			return;
		}
		
		var adId = $("#updateAdModal #adId").val();
		var imagePath = $("#updateAdModal #imagePath").val();
		var businessPhoto = $("#updateAdModal #businessPhoto").val();
		
		if(kong.test(businessPhoto)){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=AdPath.BASE + AdPath.UPDATE %>',
				data:
				{
					"adId" 		:adId,
					"adName" 	:adName,
					"url" 		:url,
					"isValid" 	:isValid
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateAdModal").modal("hide");
						},function(){
							window.location.reload();
						});
						
					}else{
						$.gyzbadmin.alertFailure("更新失败！："+data.message);
					}
				}
			})
			
		}
		
		if(!kong.test(businessPhoto)){
			
			$.blockUI();
			$.ajaxFileUpload({  
		        url : window.Constants.ContextPath +'<%=AdPath.BASE+AdPath.FILEUPLOAD%>',
		        secureuri : false,
		        fileElementId : ['businessPhoto'],
		        dataType : 'json',  
		        success : function(data, status) {  
		        	$.unblockUI();
		        	if(data.result=='SUCCESS'){
		        		var newImagePath = data.pathName;
		        		$.blockUI();
		        		$.ajax({
		        			type:"POST",
		        			dataType:"json",
		        			url:window.Constants.ContextPath +'<%=AdPath.BASE + AdPath.UPDATE %>',
		        			data:
		        			{
		        				"adId" 	: adId,
		        				"adName" 	: adName,
		        				"imagePath" : newImagePath,
		        				"imagePath2" : imagePath,
		        				"url" 		: url,
		        				"isValid" 	:isValid
		  
		        			},
		        			success:function(data){
		        				$.unblockUI();
		        				if(data.result=="success"){
		        					$.gyzbadmin.alertSuccess("更新成功！",function(){
		        						$("#updateAdModal").modal("hide");
		        					},function(){
		        						window.location.reload();
		        					});
		        				}else{
		        					$.gyzbadmin.alertFailure("更新失败！"+data.message);
		        				}
		        			}
		        		});
		        		
		    	 	}else{
		    	 		$.gyzbadmin.alertFailure('图片上传失败:' + data.message);	
		    	 	}
		        }
			}); 	
			
		}
		
	});
	
	
	/**新增**/
	$('.addAdBtn').click(function(){
		var adName = $("#addAdModal #adName").val();
		if(kong.test(adName)) {
			$.gyzbadmin.alertFailure("广告名不可为空");
			$("#addAdModal #adName").focus();
			return;
		}
		
		var certAttribute1 = $("#addAdModal #certAttribute1").val();
		if(kong.test(certAttribute1)) {
			$.gyzbadmin.alertFailure("广告图片不可为空");
			$("#addAdModal #adName").focus();
			return;
		}
		
		var url = $("#addAdModal #url").val();
		if(kong.test(url)) {
			$.gyzbadmin.alertFailure("url不可为空");
			$("#addadModal #url").focus();
			return;
		}
		
		$.blockUI();
		$.ajaxFileUpload({  
	        url : window.Constants.ContextPath +'<%=AdPath.BASE+AdPath.FILEUPLOAD%>',
	        secureuri : false,
	        fileElementId : ['certAttribute1'],
	        dataType : 'json',  
	        success : function(data, status) {  
	        	$.unblockUI();
	        	if(data.result=='SUCCESS'){
	        		var imagePath = data.pathName;
	        		$.blockUI();
	        		$.ajax({
	        			type:"POST",
	        			dataType:"json",
	        			url:window.Constants.ContextPath +'<%=AdPath.BASE + AdPath.ADD %>',
	        			data:
	        			{
	        				"adName" 	: adName,
	        				"imagePath" : imagePath,
	        				"url" 		: url
	        			},
	        			success:function(data){
	        				$.unblockUI();
	        				if(data.result=="success"){
	        					$.gyzbadmin.alertSuccess("添加成功！",function(){
	        						$("#addAdModal").modal("hide");
	        					},function(){
	        						window.location.reload();
	        					});
	        				}else{
	        					$.gyzbadmin.alertFailure("添加失败！"+data.message);
	        				}
	        			}
	        		});
	        		
	    	 	}else{
	    	 		$.gyzbadmin.alertFailure('上传失败:' + data.message);	
	    	 	}
	        }
		}); 
	});
	
});	


function showCertAttribute1Image(obj){  
	 var divObj = document.getElementById("certAttribute1Div");  
	 var imageObj = document.getElementById("certAttribute1Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;  
}

function bigImg(obj){
    $('#showImageDiv #showImage').attr("src",obj.src);
};

function showBusinessPhotoImage(obj){  
	 var divObj = document.getElementById("businessPhotoDiv");  
	 var imageObj = document.getElementById("businessPhotoImageDiv");  
	 return previewImage(divObj,imageObj,obj);  
}

</script>
</html>
