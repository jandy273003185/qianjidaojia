<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.dictData.controller.DictDataPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>数据字典管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	$(function(){
		var dictDatas= ${dictDataList};
		var dictDataList=$("tr.dictData");
		$.each(dictDatas,function(i,value){
			$.data(dictDataList[i],"dictData",value);
			
		});
		
		$(".updateDictData").click(function(){
			var dictData = $.data($(this).parent().parent()[0],"dictData");
			$("#updateDictDataModal #id").val(dictData.id);
			$("#updateDictDataModal #name").val(dictData.name);
			$("#updateDictDataModal #dictPath").val(dictData.dictPath);
			$("#updateDictDataModal #dictType").val(dictData.dictType);
			$("#updateDictDataModal #dataType").val(dictData.dataType);
			$("#updateDictDataModal #level").val(dictData.level);
			$("#updateDictDataModal #value").val(dictData.value);
			$("#updateDictDataModal #orderBy").val(dictData.orderBy);
			$("#updateDictDataModal #description").val(dictData.description);
			$("#updateDictDataModal #role").val(dictData.role);
			$("#updateDictDataModal #language").val(dictData.language);
			$("#updateDictDataModal #status").val(dictData.status);
		});
		
		
		$(".delDictData").click(function(){
			var dictData = $.data($(this).parent().parent()[0],"dictData");
			$("#deleteDictDataModal").find("input[name='id']").val(dictData.id);
			$("span.sureDel").text(dictData.id);
		});
		
		
		$('.clearDictDataForm').click(function(){
			$('.search-table #name').val('');
			$('.search-table #dictPath').val('');
		})
		
	});
	function delDictData(){
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=DictDataPath.BASE+DictDataPath.DELETE %>' ,
			data:"id="+$("#deleteDictDataModal #id").val(),
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteDictDataModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("删除失败！"+data.message);
				}
			}
		});
	
	}

	function addDictData(){
		
		var id = $("#addDictDataModal #id").val();
		if(kong.test(id)) {
			
			$.gyzbadmin.alertFailure("字典编号不可为空");
			$("#addDictDataModal #id").focus();
			return;
		}
		
		var name = $("#addDictDataModal #name").val();
		if(kong.test(name)) {
			$.gyzbadmin.alertFailure("字典名称不可为空");
			$("#addDictDataModal #name").focus();
			return;
		}
		
		var dictPath = $("#addDictDataModal #dictPath").val();
		if(kong.test(dictPath)) {
			$.gyzbadmin.alertFailure("字典路径不可为空");
			$("#addDictDataModal #dictPath").focus();
			return;
		}
		
		var dictType = $("#addDictDataModal #dictType").val();
		if(kong.test(dictType)) {
			$.gyzbadmin.alertFailure("字典类型不可为空");
			$("#addDictDataModal #dictType").focus();
			return;
		}
		
		var dataType = $("#addDictDataModal #dataType").val();
		if(kong.test(dataType)) {
			$.gyzbadmin.alertFailure("数据类型不可为空");
			$("#addDictDataModal #dataType").focus();
			return;
		}
		
		var level = $("#addDictDataModal #level").val();
		if(kong.test(level)) {
			$.gyzbadmin.alertFailure("级别不可为空");
			$("#addDictDataModal #level").focus();
			return;
		}
		var value = $("#addDictDataModal #value").val();
		if(kong.test(value)) {
			$.gyzbadmin.alertFailure("字典值不可为空");
			$("#addDictDataModal #value").focus();
			return;
		}
		
		var role = $("#addDictDataModal #role").val();
		if(kong.test(role)) {
			$.gyzbadmin.alertFailure("角色不可为空");
			$("#addDictDataModal #role").focus();
			return;
		}
		
		var status = $("#addDictDataModal #status").val();
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure("字典状态不可为空");
			$("#addDictDataModal #status").focus();
			return;
		}
		
		var language = $("#addDictDataModal #language").val();
		if(kong.test(language)) {
			$.gyzbadmin.alertFailure("语言不可为空");
			$("#addDictDataModal #language").focus();
			return;
		}
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath + '<%=DictDataPath.BASE+DictDataPath.ADD %>' ,
			data:
			{
				"id" 	: id,
				"name" 	: name,
				"dictPath" 	: dictPath,
				"dictType" 	: dictType,
				"dataType"	: dataType,
				"level"  	: level,
				"value"     : value,
				"role"      : role,
				"status"    : status,
				"language"  : language,
				"description"  : $("#addDictDataModal #description").val()
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addDictDataModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
				}
			}
		});
		
	}
	
	function updateDictData(){
		
		var id = $("#updateDictDataModal #id").val();
		
		var name = $("#updateDictDataModal #name").val();
		if(kong.test(name)) {
			$.gyzbadmin.alertFailure("字典名称不可为空");
			$("#updateDictDataModal #name").focus();
			return;
		}
		
		var dictPath = $("#updateDictDataModal #dictPath").val();
		if(kong.test(dictPath)) {
			$.gyzbadmin.alertFailure("字典路径不可为空");
			$("#updateDictDataModal #dictPath").focus();
			return;
		}
		
		var dictType = $("#updateDictDataModal #dictType").val();
		if(kong.test(dictType)) {
			$.gyzbadmin.alertFailure("字典类型不可为空");
			$("#updateDictDataModal #dictType").focus();
			return;
		}
		
		var dataType = $("#updateDictDataModal #dataType").val();
		if(kong.test(dataType)) {
			$.gyzbadmin.alertFailure("数据类型不可为空");
			$("#updateDictDataModal #dataType").focus();
			return;
		}
		
		var level = $("#updateDictDataModal #level").val();
		if(kong.test(level)) {
			$.gyzbadmin.alertFailure("级别不可为空");
			$("#updateDictDataModal #level").focus();
			return;
		}
		var value = $("#updateDictDataModal #value").val();
		if(kong.test(value)) {
			$.gyzbadmin.alertFailure("字典值不可为空");
			$("#updateDictDataModal #value").focus();
			return;
		}
		
		var role = $("#updateDictDataModal #role").val();
		if(kong.test(role)) {
			$.gyzbadmin.alertFailure("角色不可为空");
			$("#updateDictDataModal #role").focus();
			return;
		}
		
		var status = $("#updateDictDataModal #status").val();
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure("字典状态不可为空");
			$("#updateDictDataModal #status").focus();
			return;
		}
		
		var language = $("#updateDictDataModal #language").val();
		if(kong.test(language)) {
			$.gyzbadmin.alertFailure("语言不可为空");
			$("#updateDictDataModal #language").focus();
			return;
		}
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=DictDataPath.BASE+DictDataPath.EDIT %>',
			data:
			{
				"id" 	: id,
				"name" 	: name,
				"dictPath" 	: dictPath,
				"dictType" 	: dictType,
				"dataType"	: dataType,
				"level"  	: level,
				"value"     : value,
				"role"      : role,
				"status"    : status,
				"language"  : language,
				"description"  : $("#updateDictDataModal #description").val()
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("更新成功！",function(){
						$("#updateDictDataModal").modal("hide");
					},function(){
						window.location.reload();
					});
					
				}else{
					$.gyzbadmin.alertFailure("更新失败！："+data.message);
				}
			}
		});
		
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
							<form action='<c:url value="<%=DictDataPath.BASE + DictDataPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">字典路径</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="dictPath" id="dictPath"   value="${dictDataBean.dictPath }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >字典名称</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="name" id="name"  value="${dictDataBean.name }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn"> 
											<gyzbadmin:function url="<%=DictDataPath.BASE + DictDataPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button> 
											</gyzbadmin:function>
												<button type="button"  class="btn btn-purple btn-sm btn-margin clearDictDataForm" >
														清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzbadmin:function url="<%=DictDataPath.BASE + DictDataPath.ADD%>">
												<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addDictDataModal">
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
								数据字典
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="15%">字典名称</th>
											<th width="20%">字典路径</th>
											<th width="5%">数据类型</th>
											<th width="5%">级别</th>
											<th width="20%">字典值</th>
											<th width="5%">角色</th>
											<th width="5%">字典状态</th>
											<th width="8%">创建者</th>
											<th width="12%">创建时间</th>
											<th width="5%">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${dictDatas}" var="data" varStatus="status">
											<tr class="dictData" >
												<td>${data.name }</td>
												<td>${data.dictPath }</td>
												<td>${data.dataType }</td>
												<td>${data.level }</td>
												<td>${data.value }</td>
												<td>${data.role }</td>
												<td>
													<c:if test="${data.status =='1'}">有效</c:if>
													<c:if test="${data.status =='0'}">无效</c:if>
												</td>
												<td>${data.creator }</td>
												<td>${data.createTime }</td>
												<td>
													<gyzbadmin:function url="<%=DictDataPath.BASE + DictDataPath.EDIT%>">
														<a href="#updateDictDataModal"  data-toggle='modal' class="tooltip-success updateDictData" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty dictDatas}">
											<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty dictDatas}">
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
	
	<div class="modal fade" id="addDictDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增数据字典</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
						<tr>	
							<td class="td-left" width="20%">字典名称<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="text" id="name" name="name" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典路径<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dictPath" name="dictPath" style="width:90%">
							</td>
						<tr>
							<td class="td-left">字典类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dictType" name="dictType" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">数据类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dataType" name="dataType" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">级别<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="level" name="level" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典值<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="3"  id="value" name="value" style="width:90%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">角色<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="role" name="role" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典状态<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="status" name="status" style="width:90%">
									<option value="1">--有效--</option>
									<option value="0">--无效--</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">语言<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="language" name="language" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典描述</td>
							<td class="td-right">
								<textarea rows="3"  id="description" name="description" style="width:90%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBankBtn" onclick="addDictData()">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		 <div class="modal fade" id="updateDictDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新数据字典信息</h4>
		         </div>
		         <div class="modal-body">
		         
		           <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">字典编号<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="text" id="id" name="id" readonly="readonly" style="width:90%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">字典名称<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="name" name="name" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典路径<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dictPath" name="dictPath" style="width:90%">
							</td>
						<tr>
							<td class="td-left">字典类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dictType" name="dictType" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">数据类型<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dataType" name="dataType" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">级别<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="level" name="level" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典值<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="3" id="value" name="value" style="width:90%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">角色<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="role" name="role" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典状态<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="status" name="status" style="width:90%">
									<option value="1">--有效--</option>
									<option value="0">--无效--</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">语言<span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="language" name="language" style="width:90%">
							</td>
						</tr>
						<tr>
							<td class="td-left">字典描述</td>
							<td class="td-right">
								<textarea rows="3"  id="description" name="description" style="width:90%"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBankBtn" onclick="updateDictData()">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		
		<div class="modal fade" id="deleteDictDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">数据字典删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该数据字典[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" id="id">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBankBtn" onclick="delDictData()">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->  
		
</body>
</html>

