<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>设备广告图片列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>
<script type="text/javascript">
jQuery(function($){
			
	// 为每个tr缓存数据
	 var merchantSigns= '<c:out value="${gyzb:toJSONString(adManagementAdvertList)}" escapeXml="false"/>';
	 var merchantSignList=$("tr.merchantSign");
	 $.each($.parseJSON(merchantSigns),function(i,value){		 
	 	$.data(merchantSignList[i],"merchantSign",value);
		}); 

	 

	
</script>	

<body>
	<!-- 物料信息 -->
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
							<form action='<c:url value="/basemanager/admanagement/machineAdvertList"/>' method="post" id="form">
							<table class="search-table">								
								<tr>
									<td class="td-left" >设备名称：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="custName"  id="custName"  value="${queryBean.custName }"><i class="icon-leaf blue"></i>
										</span>
									</td>				
									
									<td class="td-left" >商户编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="custId"  id="custId"  value="${queryBean.custId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
													
								</tr>
								<tr>
									<td class="td-left" >时间：</td>
									<td>
										<input type="text" id="startTime" name="startTime" value="${queryBean.startTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
										<label class="label-tips" id="startTimeLabStart"></label>
											-
										<input type="text" id="endTime" name="endTime" value="${queryBean.endTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
										<label class="label-tips" id="endTimeLabEnd"></label>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">																					
										
											<%-- <gyzb-admin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.LIST%>"> --%>
											<gyzb-admin:function url="/basemanager/admanagement/machineAdvertList">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
	
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户设备信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>商户名称</th>
											<th>商户编号	</th>
											<th>设备号</th>
											<th>设备类别	</th>
											<th  style="width:12%" >广告图片路径	</th>
											<th>广告图片顺序	</th>
											<th>创建时间</th>
										</tr>
									</thead>

									<tbody>

											<c:forEach items="${adManagementAdvertList}" var="madManagementAdvert">
											<tr class="merchantProduct">
												<td>${madManagementAdvert.custName}</td>
												<td>${madManagementAdvert.custId}</td>
												<td>${madManagementAdvert.terminalNo}</td>
												<td>
													<c:if test="${madManagementAdvert.machineType =='QINGTING'}">
														蜻蜓
													</c:if>
													<c:if test="${madManagementAdvert.machineType =='QINGWA'}">
														青蛙
													</c:if>
												</td>
							
												<td class="td-right" >
													<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
													<label id="businessPhotoDiv" class="uploadImage" >
														 <img  onClick="bigImg(this);" src="${madManagementAdvert.picture}"  style="width:100%;height:8%;"/>
													</label>
													</a>
												</td>
					
										<%-- 		<td>${madManagementAdvert.picture}</td> --%>
												<td>${madManagementAdvert.sequence}</td>
												<td>
													<fmt:formatDate value="${madManagementAdvert.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
																						
												
											</tr>
										</c:forEach>

										<c:if test="${empty adManagementAdvertList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty adManagementAdvertList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
				</div><!-- /.page-content -->
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				    <div class="modal-dialog showDiv" >
	         			<div id="showImageDiv" style="width:100%;height:100%;">
				           <img id="showImage" style="width:100%;height:100%;">
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
	<script type="text/javascript">
	 /** 点击预览大图 **/
	 function bigImg(obj){
	     var realWidth;
	 	var realHeight
	 	$('#showImageDiv #showImage').attr("src",obj.src).load(function(){
	 		realWidth = this.width;
	 		realHeight = this.height;
	 		var scale =  realWidth/realHeight;
	 		if(realWidth >800){
	 			realWidth = 800;
	 			realHeight = realWidth / scale;
	 		}
	 		$("#imageDiv").css("width",realWidth+"px").css("height",realHeight+"px");
	 	});
	 }
	</script>
				
  </body>
</html>