<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.city.CityPath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>城市管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearCity').click(function(){
			$('.search-table #cityName').val('');
			$('.search-table #provinceName').val('');
			$('.search-table #areaCode').val('');
			$('.search-table #areaName').val('');
		})
		
		var citys = ${cityList};
		var cityList = $("tr.city");
		$.each(citys,function(i,value){
			$.data(cityList[i],"city",value);
		});
		
		$("#addCityModal .cityLink").hide();
		$("#addCityModal .areaLink").hide();
		$("#addCityModal .selectTagLink").hide();
		
		$("#addCityModal #provinceRadio").click(function(){
			$("#addCityModal .provinceLink").show();
			$("#addCityModal .cityLink").hide();
			$("#addCityModal .areaLink").hide();
			$("#addCityModal .selectTagLink").hide();
			$("#addCityModal #cityName").val('');
			$("#addCityModal #areaName").val('');
		});
		
		$("#addCityModal #cityRadio").click(function(){
			$("#addCityModal .provinceLink").hide();
			$("#addCityModal .areaLink").hide();
			$("#addCityModal .selectTagLink").show();
			$(".province-city-area #cityId").hide();
			$(".province-city-area #areaId").hide();
			$("#addCityModal .cityLink").show();
			$("#addCityModal #provinceName").val('');
			$("#addCityModal #areaName").val('');
		});
		
		$("#addCityModal #areaRadio").click(function(){
			$("#addCityModal .provinceLink").hide();
			$("#addCityModal .cityLink").hide();
			$("#addCityModal #provinceName").val('');
			$("#addCityModal #cityName").val('');
			$("#addCityModal .areaLink").show();
			$("#addCityModal .selectTagLink").show();
			$(".province-city-area #cityId").show();
			$(".province-city-area #areaId").hide();
		});
		
		/*增加  */
		$(".addCityBtn").click(function(){
			var insertCode = $("input[name='insert_pro_city_area']:checked").val();
			var provinceId = '0';
			var provinceName = '';
			var cityId = '0';
			var cityName = '';
			var areaName = '';
			var areaCode = '';
			
			if(insertCode=='0'){
				provinceName = $("#addCityModal #provinceName").val();
				if(kong.test(provinceName)){
					$.gyzbadmin.alertFailure('省份名称不能为空');
					return;
				}
			}else if(insertCode=='1'){
				provinceId = $("#addCityModal #provinceId").val();
				if(kong.test(provinceId)){
					$.gyzbadmin.alertFailure('请选择省份');
					return;
				}
				cityName = $("#addCityModal #cityName").val();
				if(kong.test(cityName)){
					$.gyzbadmin.alertFailure('城市名称不能为空');
					return;
				}
			}else if(insertCode=='2'){
				provinceId = $("#addCityModal #provinceId").val();
				if(kong.test(provinceId)){
					$.gyzbadmin.alertFailure('请选择省份');
					return;
				}
				cityId = $("#addCityModal #cityId").val();
				if(kong.test(cityId)){
					$.gyzbadmin.alertFailure('请选择城市');
					return;
				}
				areaName = $("#addCityModal #areaName").val();
				if(kong.test(areaName)){
					$.gyzbadmin.alertFailure('区域名称不能为空');
					return;
				}
				areaCode = $("#addCityModal #areaCode").val();
				if(kong.test(areaCode)){
					$.gyzbadmin.alertFailure('区域代码不能为空');
					return;
				}
			}
				
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=CityPath.BASE + CityPath.ADD%>',{
			    'cityLvl'		:insertCode,
			    'provinceId'	:provinceId,
			    'provinceName'	:provinceName,
			    'cityId'		:cityId,
			    'cityName'		:cityName,
				'areaName'		:areaName,
				'areaCode'		:areaCode
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addCityModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败:"+data.message,function(){
							$("#addCityModal").modal("hide");
						});
					}
				},'json'
			);
		});
		
		/* 修改弹出层  */
		$(".updateCity").click(function(){
			var city = $.data($(this).parent().parent()[0],"city");
			 $('#updateCityModal').on('show.bs.modal', function () {
			 	$("#updateCityModal #cityId").val(city.cityId);
				$("#updateCityModal #cityName").val(city.cityName);
				$("#updateCityModal #provinceId").val(city.provinceId);
				$("#updateCityModal #provinceName").val(city.provinceName);
				$("#updateCityModal #areaId").val(city.areaId);
				$("#updateCityModal #areaCode").val(city.areaCode);
				$("#updateCityModal #areaName").val(city.areaName);
		       });
			 $('#updateCityModal').on('hide.bs.modal', function () {
				$("#updateCityModal #cityId").val('');
				$("#updateCityModal #cityName").val('');
				$("#updateCityModal #provinceId").val('');
				$("#updateCityModal #provinceName").val('');
				$("#updateCityModal #areaId").val('');
				$("#updateCityModal #areaCode").val('');
				$("#updateCityModal #areaName").val('');
		     });
		});
		
		/* 修改 */
		$(".updateCityBtn").click(function(){
			var update_code = $("input[name='update_pro_city_area']:checked").val();
			var provinceId = '0';
			var provinceName = '';
			var cityId = '0';	
			var cityName = '';
			var areaId = '0';
			var areaName = '';
			var areaCode = '';
			
			if(update_code=='0'){
				provinceId = $("#updateCityModal #provinceId").val();
				if(provinceId=='0'){
					$.gyzbadmin.alertFailure("省份编号不可为空");
					return;
				}
				provinceName = $("#updateCityModal #provinceName").val();
				if(kong.test(provinceName)){
					$.gyzbadmin.alertFailure("省份名称不可为空");
					return;
				}
			}else if(update_code=='1'){
				cityId = $("#updateCityModal #cityId").val();
				if(cityId=='0'){
					$.gyzbadmin.alertFailure("城市编号不可为空");
					return;
				}
				cityName = $("#updateCityModal #cityName").val();
				if(kong.test(cityName)){
					$.gyzbadmin.alertFailure("城市编号不可为空");
					return;
				}
			}else if(update_code=='2'){
				areaId = $("#updateCityModal #areaId").val();
				if(areaId=='0'){
					$.gyzbadmin.alertFailure("区域编号不可为空");
					return;
				}
				areaName = $("#updateCityModal #areaName").val();
				if(kong.test(areaName)){
					$.gyzbadmin.alertFailure("区域名称不可为空");
					return;
				}
				areaCode = $("#updateCityModal #areaCode").val();
				if(areaCode=='0'){
					$.gyzbadmin.alertFailure("区域代码不可为空");
					return;
				}
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=CityPath.BASE+CityPath.UPDATE%>',{
				'cityLvl'		:update_code,
				'provinceId'	:provinceId,
				'provinceName'	:provinceName,
				'cityId'		:cityId,
				'cityName'		:cityName,
				'areaId'		:areaId,
				'areaName'		:areaName,
				'areaCode'		:areaCode
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateCityModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("更新失败:"+data.message,function(){
							$("#updateCityModal").modal("hide");
						});
					}
				},'json'
			);
		});
		
		
		/* 删除弹出层  */
		$(".deleteCity").click(function(){
			var city = $.data($(this).parent().parent()[0],"city");
			 $('#deleteCityModal').on('show.bs.modal', function () {
			 	$("#deleteCityModal #cityId").val(city.cityId);
				$("#deleteCityModal #cityName").val(city.cityName);
				$("#deleteCityModal #provinceId").val(city.provinceId);
				$("#deleteCityModal #provinceName").val(city.provinceName);
				$("#deleteCityModal #areaId").val(city.areaId);
				$("#deleteCityModal #areaCode").val(city.areaCode);
				$("#deleteCityModal #areaName").val(city.areaName);
		       });
			 $('#deleteCityModal').on('hide.bs.modal', function () {
				$("#deleteCityModal #cityId").val('');
				$("#deleteCityModal #cityName").val('');
				$("#deleteCityModal #provinceId").val('');
				$("#deleteCityModal #provinceName").val('');
				$("#deleteCityModal #areaId").val('');
				$("#deleteCityModal #areaCode").val('');
				$("#deleteCityModal #areaName").val('');
		     });
		});
		
		/* 修改 */
		$(".deleteCityBtn").click(function(){
			var delete_code = $("input[name='delete_pro_city_area']:checked").val();
			var provinceId = '0';
			var cityId = '0';	
			var areaId = '0';
			
			if(delete_code=='0'){
				provinceId = $("#deleteCityModal #provinceId").val();
				if(provinceId=='0'){
					$.gyzbadmin.alertFailure("省份编号不可为空");
					return;
				}
			}else if(delete_code=='1'){
				cityId = $("#deleteCityModal #cityId").val();
				if(cityId=='0'){
					$.gyzbadmin.alertFailure("城市编号不可为空");
					return;
				}
			}else if(delete_code=='2'){
				areaId = $("#deleteCityModal #areaId").val();
				if(areaId=='0'){
					$.gyzbadmin.alertFailure("区域编号不可为空");
					return;
				}
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath +'<%=CityPath.BASE+CityPath.DELETE%>',{
				'cityLvl'		:delete_code,
				'provinceId'	:provinceId,
				'cityId'		:cityId,
				'areaId'		:areaId,
				},function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteCityModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						
						$.gyzbadmin.alertFailure("删除失败:"+data.message,function(){
							$("#deleteCityModal").modal("hide");
						});
					}
				},'json'
			);
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
							<form action='<c:url value="<%=CityPath.BASE + CityPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">省市名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="provinceName" placeholder="省市名称" value="${queryBean.provinceName }" id="provinceName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">城市名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="cityName" placeholder="城市名称" value="${queryBean.cityName}"  id="cityName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">区域代码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="areaCode" placeholder="区域代码" value="${queryBean.areaCode}"  id="areaName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">区域名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="areaName" placeholder="区域名称" value="${queryBean.areaName}"  id="areaName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CityPath.BASE + CityPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearCity" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=CityPath.BASE + CityPath.ADD%>">
												<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addCityModal">
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
								城市列表
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">省市编号</th>
											<th width="15%">省市名称</th>
											<th width="10%">城市编号</th>
											<th width="15%">城市名称</th>
											<th width="10%">区域编号</th>
											<th width="10%">区域代码</th>
											<th width="15%">区域名称</th>
											<th width="10%">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${cityList}" var="city">
											<tr class="city">
												<td>${city.provinceId }</td>
												<td>${city.provinceName }</td>
												<td>${city.cityId }</td>
												<td>${city.cityName }</td>
												<td>${city.areaId }</td>
												<td>${city.areaCode}</td>
												<td>${city.areaName }</td>
												<td>
													<gyzbadmin:function url="<%=CityPath.BASE + CityPath.UPDATE%>">
														<a href="#" class="tooltip-success updateCity" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateCityModal">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													&nbsp;
													<gyzbadmin:function url="<%=CityPath.BASE + CityPath.DELETE%>">
														<a href="#" class="tooltip-error deleteCity" data-rel="tooltip" title="Delete" data-toggle="modal" data-target="#deleteCityModal">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty cityList}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty cityList}">
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
	
	<div class="modal fade" id="addCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加省份/城市/区域</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" >添加类型<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<input type="radio" name="insert_pro_city_area" checked="checked" id="provinceRadio" value="0"/> 省份 &nbsp;&nbsp;
								<input type="radio" name="insert_pro_city_area"  id="cityRadio" value="1"/> 城市&nbsp;&nbsp;
								<input type="radio" name="insert_pro_city_area"  id="areaRadio" value="2"/> 区域
							</td>
						</tr>
						<tr class="selectTagLink">
							<td class="td-left">请选择 <span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<div class="province-city-area">
									<sevenpay:selectProvinceTag id="provinceId" name ="provinceId" />
									<select name="cityId" id="cityId"><option value=''>选择城市</option></select>
									<select name="areaId" id="areaId"><option value=''>选择区域</option></select>
								</div>
							</td>
						</tr>
						<tr class="provinceLink">
							<td class="td-left">省份名称<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<input type="text" id="provinceName" name="provinceName" style="width:60%" maxlength="30">
							</td>
						</tr>
						<tr class="citylink">
							<td class="td-left">城市名称<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
									<input type="text" id="cityName" name="cityName" style="width:60%" maxlength="30">
							</td>
						</tr>
						<tr class="areaLink">
							<td class="td-left" width="20%">区域名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="areaName" name="areaName" maxlength="30">
							</td>
							<td class="td-left" width="20%">区域代码<span style="color:red">*</span></td>
							<td class="td-right"width="30%">
								<input type="text" id="areaCode" name="areaCode" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="11">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addCityBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="updateCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新省份/城市/区域</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" >更新类型<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<input type="radio" name="update_pro_city_area" checked="checked" id="provinceRadio" value="0"/> 省份 &nbsp;&nbsp;
								<input type="radio" name="update_pro_city_area"  id="cityRadio" value="1"/> 城市&nbsp;&nbsp;
								<input type="radio" name="update_pro_city_area"  id="areaRadio" value="2"/> 区域
							</td>
						</tr>
						<tr>
							<input type="hidden" name="provinceId" id="provinceId">
							<td class="td-left" width="20%">省份名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" name="provinceName" id="provinceName" maxlength="30">
							</td>
							<input type="hidden" name="cityId" id="cityId">
							<td class="td-left" width="20%">城市名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="cityName" name="cityName"  maxlength="30">
							</td>
						</tr>
						<tr>
							<input type="hidden" name="areaId" id="areaId">
							<td class="td-left" width="20%">区域名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="areaName" name="areaName"  maxlength="30">
							</td>
							<td class="td-left" width="20%">区域代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" name="areaCode" id="areaCode" maxlength="11">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateCityBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除省份/城市/区域</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" >更新类型<span style="color:red">*</span></td>
							<td class="td-right" colspan="3">
								<input type="radio" name="delete_pro_city_area"  id="provinceRadio" value="0"/> 省份 &nbsp;&nbsp;
								<input type="radio" name="delete_pro_city_area"  id="cityRadio" value="1"/> 城市&nbsp;&nbsp;
								<input type="radio" name="delete_pro_city_area" checked="checked" id="areaRadio" value="2"/> 区域
							</td>
						</tr>
						<tr>
							<input type="hidden" name="provinceId" id="provinceId">
							<td class="td-left" width="20%">省份名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" name="provinceName" id="provinceName" disabled="disabled" maxlength="30">
							</td>
							<input type="hidden" name="cityId" id="cityId">
							<td class="td-left" width="20%">城市名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="cityName" name="cityName" disabled="disabled" maxlength="30">
							</td>
						</tr>
						<tr>
							<input type="hidden" name="areaId" id="areaId">
							<td class="td-left" width="20%">区域名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="areaName" name="areaName" disabled="disabled" maxlength="30">
							</td>
							<td class="td-left" width="20%">区域代码<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" name="areaCode" id="areaCode" disabled="disabled" maxlength="11">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary deleteCityBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>