<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.ChannelCodeType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatProfitSharingType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatRelationType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.AliPayProfitSharingType"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>APP版本管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>


<body>
	<!-- 商户分账信息 -->
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
							<form action='<c:url value="/appEditionManager/list"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >版本号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="editionId"  id="editionId"  value="${queryBean.editionId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >设备类别：</td>
									<td class="td-right" >
										<span class="input-icon">
											<select name="machineType" id="machineType" >
												<option value="">--请选择--</option>
												<option value="QINGTING" <c:if test="${queryBean.machineType == 'QINGTING'}">selected</c:if> >蜻蜓</option>
												<option value="QINGWA" <c:if test="${queryBean.machineType == 'QINGWA'}">selected</c:if> >青蛙</option>
											</select>
										</span>
									</td>
									
									<td class="td-left" >状态：</td>
									<td class="td-right" >
										<span class="input-icon">
											<select id="state" name="state" >
												<option value="">--请选择--</option>
												<option value="00" <c:if test="${queryBean.state == '00'}">selected</c:if> >可用</option>
												<option value="99" <c:if test="${queryBean.state == '99'}">selected</c:if> >失效</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="/appEditionManager/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="/appEditionManager/add">
												<button class="btn btn-purple btn-sm btn-margin " data-toggle='modal' data-target="#addAppEditionModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">APP版本列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>自增ID</th>
											<th>设备类别</th>
											<th>文件路径</th>
											<th>版本号</th>
											<th>创建人</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${listAppEditionControls}" var="appEditionControl">
											<tr class="appEditionControl">
												<td>${appEditionControl.id}</td>
												<td>
													<c:if test="${appEditionControl.machineType eq 'QINGTING'}">
														蜻蜓
													</c:if>
													<c:if test="${appEditionControl.machineType eq 'QINGWA'}">
														青蛙
													</c:if>
												</td>	
												<td>${appEditionControl.fileUrl}</td>
												<td>${appEditionControl.editionId}</td>
												<td>${appEditionControl.creator}</td>
												<td>
													<c:if test="${appEditionControl.state eq '00'}">
														<span class="label label-success arrowed">可用</span>
													</c:if>
													<c:if test="${appEditionControl.state eq '99'}">
														<span class="label label-danger arrowed-in">失效</span>
													</c:if>
												</td>
												<td>
													<fmt:formatDate value="${appEditionControl.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>${appEditionControl.memo}</td>
												<td>
													<c:if test="${appEditionControl.state eq '00'}">
														<a href="#" class="tooltip-success deleteAppEditionControl" data-rel="tooltip" data-toggle='modal' data-target="#deleteAppEditionControlModal" title="Delete" >
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</c:if>
													
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty listAppEditionControls}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty listAppEditionControls}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
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
					<div class="modal fade" id="addAppEditionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">APP版本新增</h4>
					         </div>
					         <div class="modal-body">
					         	<form  method="post" id="addAppEditionForm">
					            <table class="modal-input-table">
									<tr>
										<td class="td-left" ><span style="color:red;" >*</span>设备类型</td>
										<td class="td-right" >
											<select name="machineType" id="machineType" data-validation="notnull" data-errMsg="设备类型不能为空" >
												<option value="">--请选择--</option>
												<option value="QINGTING">蜻蜓</option>
												<option value="QINGWA">青蛙</option>
											</select>
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" ><span style="color:red;" >*</span>APP安装包上传</td>
										<td class="td-right" >
											<div class="updateImageDiv" >
												<input  type="hidden" id="fileUrl" name="fileUrl" data-validation="notnull" data-errMsg="请上传APP安装包" />
												<input type="file" name="uploadFile" id="uploadFile" onchange="upload(this)"/>
												<span style="color:gray">支持*apk、*ipa</span>
											</div>
										</td>
									</tr>
									
									<tr>
										<td class="td-left" ><span style="color:red;" >*</span>版本号</td>
										<td class="td-right" >
											<input type="text" id="editionId" name="editionId" data-validation="notnull" data-errMsg="版本号不能为空"  >
								        </td>
									</tr>
					            </table>
					            </form>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMerchantSubAccountBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			
					
					<div class="modal fade" id="deleteAppEditionControlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">停用升级包</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<input type="hidden" id="id" name="id" >
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要停用该升级包么？</font>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteAppEditionControlBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
<script type="text/javascript">
$(function(){
	// 为每个tr缓存数据
	 var listAppEditionControls = '<c:out value="${gyzb:toJSONString(listAppEditionControls)}" escapeXml="false"/>';
	 var appEditionControlList=$("tr.appEditionControl");
	 $.each($.parseJSON(listAppEditionControls),function(i,value){		 
	 	$.data(appEditionControlList[i],"appEditionControl",value);
		}); 
})

//表单验证组件
var checkFun = {
      		notnull : function(value){
      			if(null == value || "" == value) {
      				return false;
      			} else {
      				return true;
      			}
      		}
      	};
	
		//表单验证
		function formValidation(formId){
			var flag = false;
			var form = document.getElementById(formId);
			var attr = null;
			$.each(form, function(i, e) {
				attr = $(e).attr("data-validation");
				if(attr){
					if(checkFun[attr](e.value)){
						flag = true;
					} else {
						flag = false;
						alert($(e).attr("data-errMsg"));
						return false;
					}
				}
			})
			return flag;
		}
		
	//文件上传
	function upload(file){
		$.blockUI();
		var formdata = new FormData();
	    formdata.append("file",$(file).get(0).files[0]);
	    
	    $.ajax({
	        url:'/common/files/uploadPic',
	        type:'post',
	        contentType:false,
	        data:formdata,
	        processData:false,
	        async:false,
	        success:function(info){ 
	        	$.unblockUI();
	             //$('#fileUrl').val(info.path);
	             $('#fileUrl').val(info.imagePath);
	        	
	        },
	        error:function(err){
	            console.log(err)
	        }
	    });
	}

	//新增
	$('.addMerchantSubAccountBtn').click(function(){
		
		//debugger;
		/* if(!formValidation('addAppEditionForm')){
			return false;
		} */
		
		var machineType = $('#addAppEditionModal #machineType').val();
		var fileUrl = $('#addAppEditionModal #fileUrl').val();
		var editionId = $('#addAppEditionModal #editionId').val();
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/appEditionManager/add",
			data:
			{
				"machineType" 	: machineType,
				"fileUrl" : fileUrl,
				"editionId" : editionId
			},
			success:function(data){
				$.unblockUI();
				$('#addAppEditionModal').hide();
				if(data.result == "SUCCESS"){
					alert("新增成功");
					window.location.reload();
				} else {
					alert(data.message);
				}
			}
		})
		
	})
	
	//弹出停用确认框
	$(".deleteAppEditionControl").click(function(){
		//debugger;
		var appEditionControl = $.data($(this).parent().parent()[0], 'appEditionControl');
		$('#deleteAppEditionControlModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteAppEditionControlModal #id').val(appEditionControl.id);
		});
		$('#deleteAppEditionControlModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteAppEditionControlModal #id').val('');
		});
	})
	
	//删除
	$(".deleteAppEditionControlBtn").click(function(){
		var id = $('#deleteAppEditionControlModal #id').val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/appEditionManager/delete",
			data:
			{
				"id" : id
			},
			success:function(data){
				$.unblockUI();
				$('#deleteAppEditionControlModal').hide();
				if(data.result == "SUCCESS"){
					alert("停用成功");
					window.location.reload();
				} else {
					alert(data.message);
				}
			}
		})
	})
	
	
	//清空
	/* $('#clearBtn').click(function(){
		$('#editionId').val('');
		$('#machineType').val('');
		$('#subAccountType').val('');
	}) */
</script>				
  </body>
</html>