<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.meeting.restrict.RestrictPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>注册红包基础控制</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="基础控制" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	$(function(){
		$('.clearBank').click(function(){
			$('#bankNameClr').val('');
			$('#bankCodeClr').val('');
		})
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
			$('.search-table #id').val('');
			$('.search-table #value').val('');
			$('.search-table #name').val('');
			$('.search-table #level').val('');
			$('.search-table #channelId').val('');
			$('.search-table #orderState').val('');
			$('.search-table #beginTime').val('');
			$('.search-table #endCreateTime').val('');
			$('.search-table #compBeginTime').val('');
			$('.search-table #compEndCreateTime').val('');
			$('.search-table #mobile').val('');
			$('.search-table #merchantCode').val('');
		})
		
	});
	
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
			url:window.Constants.ContextPath +'<%=RestrictPath.BASE+RestrictPath.EDIT %>',
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
					$.gyzbadmin.alertFailure("更新失败！",function(){
						$("#updateDictDataModal").modal("hide");
					},function(){
						window.location.reload();
					});
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
							<form action='<c:url value="<%=RestrictPath.BASE + RestrictPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >名称</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="name" id="name"  value="${queryBean.name }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">值</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="value" id="value"   value="${queryBean.value }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn"> 
											<gyzbadmin:function url="<%=RestrictPath.BASE + RestrictPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button> 
											</gyzbadmin:function>
											<button type="button"  class="btn btn-purple btn-sm btn-margin clearDictDataForm" >
													清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">
								年会基础控制
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th >编号</th>
											<th >名称</th>
											<th >路径</th>
											<th >数据类型</th>
											<th >值</th>
											<th >描述</th>
											<th >状态</th>
											<th >创建者</th>
											<th >创建时间</th>
											<th >最后更新人</th>
											<th >最后更新时间</th>
											<th >操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${dictDataList}" var="data" varStatus="status">
											<tr class="dictData" >
												<td>${data.id }</td>
												<td>${data.name }</td>
												<td>${data.dictPath }</td>
												<td>
												    <c:if test="${data.dataType =='number'}">Number</c:if>
													<c:if test="${data.dataType =='boolean'}">Boolean</c:if>
													<c:if test="${data.dataType =='string'}">String</c:if>
												</td>
												<td>${data.value }</td>
												<td>${data.description }</td>
												<td>
													<c:if test="${data.status =='1'}">有效</c:if>
													<c:if test="${data.status =='0'}">无效</c:if>
												</td>
												<td>${data.creator }</td>
												<td>${data.createTime }</td>
												<td>${data.lastupdateUser }</td>
												<td>${data.lastupdateTime }</td>
												<td>
													<gyzbadmin:function url="<%=RestrictPath.BASE + RestrictPath.EDIT%>">
														<a href="#updateDictDataModal"  data-toggle='modal' class="tooltip-success updateDictData" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty dictDataList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty dictDataList}">
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
		
		 <div class="modal fade" id="updateDictDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新基础控制</h4>
		         </div>
		         <div class="modal-body">
		         
		           <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">编号<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="text" id="id" name="id" readonly="readonly" style="width:80%">
								<input type="hidden" id="dictType" name="dictType" style="width:80%">
								<input type="hidden" id="level" name="level" style="width:80%">
								<input type="hidden" id="role" name="role" style="width:80%">
								<input type="hidden" id="language" name="language" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">路径 <span style="color:red">*</span></td>
							<td class="td-right">
								<input type="text" id="dictPath" name="dictPath" readonly="readonly"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="name" name="name" style="width:80%" maxlength="200">
							</td>
						</tr>
						<tr>
							<td class="td-left">数据类型<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="dataType" name="dataType" style="width:80%">
								  <option value="number">- Number -</option>
								  <option value="boolean">- Boolean -</option>
								  <option value="string">- String -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">值<span style="color:red">*</span></td>
							<td class="td-right">
								<textarea rows="2" id="value" name="value" maxlength="200" style="width:80%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">状态<span style="color:red">*</span></td>
							<td class="td-right">
								<select id="status" name="status" style="width:80%">
								  <option value="1">有效</option>
								  <option value="0">无效</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">描述</td>
							<td class="td-right">
								<textarea rows="2"  id="description" name="description" style="width:80%"></textarea>
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
</body>
</html>

