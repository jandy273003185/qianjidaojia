<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.subjectInfo.SubjectInfoPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>科目信息</title>
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
							<form action='<c:url value="<%=SubjectInfoPath.BASE + SubjectInfoPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
								
									<td class="td-left" width="18%">科目名称</td>
									<td class="td-right" width="32%">
										<span class="input-icon">
											<input type="text" name="subjectName"  id="subjectName"  value="${subjectName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="18%">科目代码</td>
									<td class="td-right" width="32%">
										<span class="input-icon">
											<input type="text" name="subjectCode"  id="subjectCode"  value="${subjectCode}"/>
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
												<gyzbadmin:function url="<%=SubjectInfoPath.BASE + SubjectInfoPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
												
												<button class="btn btn-purple btn-sm btn-margin "  id="clearSubjectInfo">
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=SubjectInfoPath.BASE + SubjectInfoPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addUserLink" data-toggle='modal' data-target="#addsubjectModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
												</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">科目列表</div>

							<div class="table-responsive">
								
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
										    <th>编号</th>
											<th>科目名称</th>
											<th>科目类型</th>
											<th>科目余额</th>
											<th>科目级别</th>
											<th>科目代码</th>
											<th>父级科目编号</th>
											<th>创建人</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${subList}" var="sub" varStatus="status">
											<tr class="sub" >
											    <td style="width:5%">${sub.id }</td>
												<td>${sub.subjectName }</td>
												<td>
												<c:if test="${sub.subjectType=='1001'}">
					    								<c:out value="资产类"/>
													</c:if>
												<c:if test="${sub.subjectType=='2002'}">
														<c:out value="负债类"/>
												</c:if>
												<c:if test="${sub.subjectType=='3003'}">
														<c:out value="所有者权益"/>
												</c:if>
												</td>
												<td>${sub.subjectAmt}</td>
												<td>${sub.subjectLevel }</td>
												<td>${sub.subjectCode }</td>
												<td>${sub.parentCode }</td>
												<td>${sub.creator }</td>
												<td>${sub.memo }</td>
												<td>
													<gyzbadmin:function url="<%=SubjectInfoPath.BASE + SubjectInfoPath.UPDATE%>">
														<a href="#" class="tooltip-success updateSubjectLink" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#updateadSubjectModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=SubjectInfoPath.BASE + SubjectInfoPath.DELETE%>">
														<a href="#deleteadModal"  data-toggle='modal' class="tooltip-error deleteSubjectLink" data-rel="tooltip" title="Delete">
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
														</a>	
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty subList}">
											<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								</div>	
								<!-- 分页 -->
								<c:if test="${not empty subList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				<div class="modal fade" id="addsubjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				            <h4 class="modal-title" id="myModalLabel">添加科目信息</h4>
				         </div>
				         <div class="modal-body">
				         	  <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="30%">科目名称<span style="color:red">*</span></td>
									<td class="td-right" width="70%">
										<input type="text" id="subjectName" name="subjectName" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">科目代码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectCode" name="subjectCode" clasS="width-60">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >科目类型<span style="color:red">*</span></td>
									<td class="td-right" >
										<select id="subjectType" name="subjectType">
											<option value="">-- 请选择 --</option>
											<option value="1001">资产类</option>
											<option value="2002">负债类</option>
											<option value="3003">所有者权益</option>
										</select>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">科目级别<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectLevel" name="subjectLevel" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">父级科目编号<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="parentCode" name="parentCode" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">币别<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="currCode" name="currCode"  value="CNY" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">科目余额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectAmt" name="subjectAmt" value='0.00' clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">备注</td>
									<td class="td-right">
										<textarea rows="2" id="memo" name="memo" maxlength="300" clasS="width-60"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer"  style="display:block">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary addSubjectBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				
				<div class="modal fade" id="updateadSubjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				            <h4 class="modal-title" id="myModalLabel">修改科目信息</h4>
				         </div>
				         <div class="modal-body">
				         	  <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="30%">科目名称<span style="color:red">*</span></td>
									<td class="td-right" width="70%">
										<input type="text" id="subjectName" name="subjectName" clasS="width-60">
										<input type="hidden" name="id" id="id">
									</td>
								</tr>
								<tr>
									<td class="td-left">科目代码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectCode" name="subjectCode" clasS="width-60">
										<input type="hidden" id="subjectCode02" name="subjectCode02">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >科目类型<span style="color:red">*</span></td>
									<td class="td-right" >
										<select id="subjectType" name="subjectType">
											<option value="">-- 请选择 --</option>
											<option value="1001">资产类</option>
											<option value="2002">负债类</option>
											<option value="3003">所有者权益</option>
										</select>
									</td>
								</tr>
								
								<tr>
									<td class="td-left">科目级别<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectLevel" name="subjectLevel" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">父级科目编号<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="parentCode" name="parentCode" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">币别<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="currCode" name="currCode" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">科目余额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="subjectAmt" name="subjectAmt" clasS="width-60">
									</td>
								</tr>
								<tr>
									<td class="td-left">备注</td>
									<td class="td-right">
										<textarea rows="2" id="memo" name="memo" maxlength="300" clasS="width-60"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer"  style="display:block">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary updateSubjectBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				<div class="modal fade" id="deleteadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">科目配置参数删除</h4>
				         </div>
				         <div class="modal-body" align="center">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该科目配置参数[<span class="sureDel"></span>]么？</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="id" id="id">
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary deleteSubjectBtn" >确定</button>
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
<script type="text/javascript">
	$(document).ready(function(){
		$("#clearSubjectInfo").click(function(){
			$(".search-table #subjectName").val("");
			$(".search-table #subjectCode").val("");
		});
		var subs= ${subList};
		var subList=$("tr.sub");
		$.each(subs,function(i,value){
			$.data(subList[i],"sub",value);
		});
		
		$(".updateSubjectLink").click(function(){
			var sub = $.data($(this).parent().parent()[0],"sub");
			$('#updateadSubjectModal').on('show.bs.modal', function () {
				$("#updateadSubjectModal #id").val(sub.id);
				$("#updateadSubjectModal #subjectName").val(sub.subjectName);
				$("#updateadSubjectModal #subjectType").val(sub.subjectType);
				$("#updateadSubjectModal #subjectAmt").val(sub.subjectAmt);
				$("#updateadSubjectModal #subjectLevel").val(sub.subjectLevel);
				$("#updateadSubjectModal #subjectCode").val(sub.subjectCode);
				$("#updateadSubjectModal #subjectCode02").val(sub.subjectCode);			
				$("#updateadSubjectModal #parentCode").val(sub.parentCode);
				$("#updateadSubjectModal #currCode").val(sub.currCode);
				$("#updateadSubjectModal #memo").val(sub.memo);
			})
			$('#updateadSubjectModal').on('hide.bs.modal', function () {
				$("#updateadSubjectModal #id").val('');
				$("#updateadSubjectModal #subjectName").val('');
				$("#updateadSubjectModal #subjectType").val('');
				$("#updateadSubjectModal #subjectAmt").val('');
				$("#updateadSubjectModal #subjectLevel").val('');
				$("#updateadSubjectModal #subjectCode").val('');
				$("#updateadSubjectModal #subjectCode02").val('');			
				$("#updateadSubjectModal #parentCode").val('');
				$("#updateadSubjectModal #currCode").val('');
				$("#updateadSubjectModal #memo").val('');
			})
		})
		
		$(".updateSubjectBtn").click(function(){
			var id= $("#updateadSubjectModal #id").val();
			var subjectName = $("#updateadSubjectModal #subjectName").val();
			if(kong.test(subjectName)) {
				$.gyzbadmin.alertFailure("科目名称不可为空");
				return;
			}
			var subjectCode = $("#updateadSubjectModal #subjectCode").val();
			if(kong.test(subjectCode)) {
				$.gyzbadmin.alertFailure("科目代码不可为空");
				return;
			}
			var subjectCode02 =$("#updateadSubjectModal #subjectCode02").val();
			//校验科目唯一性
			var validate =true ;
			if(subjectCode!=subjectCode02){
				$.ajax({
					async:false,
					dataType:"json",
					url:window.Constants.ContextPath +'<%=SubjectInfoPath.BASE+SubjectInfoPath.VALIDATE%>',
			        data:{"subjectCode":subjectCode},
			        success:function(data){
			        	if(data.result=="FAIL"){
							$.gyzbadmin.alertFailure("科目代码已存在");
							validate = false;
						}else{
							validate = true;
						}
						 }});
				if(!validate){
					return false;
				}
			}
			var subjectType = $("#updateadSubjectModal #subjectType").val();
			if(kong.test(subjectType)) {
				$.gyzbadmin.alertFailure("科目类型不可为空");
				return;
			}
			var subjectAmt = $("#updateadSubjectModal #subjectAmt").val();
			if(kong.test(subjectAmt)) {
				$.gyzbadmin.alertFailure("科目余额不可为空");
				return;
			}
			var subjectLevel = $("#updateadSubjectModal #subjectLevel").val();
			if(kong.test(subjectLevel)) {
				$.gyzbadmin.alertFailure("科目级别不可为空");
				return;
			}
			var parentCode = $("#updateadSubjectModal #parentCode").val();
			if(kong.test(parentCode)) {
				$.gyzbadmin.alertFailure("父级科目编号不可为空");
				return;
			}
			var currCode = $("#updateadSubjectModal #currCode").val();
			if(kong.test(currCode)) {
				$.gyzbadmin.alertFailure("币别不可为空");
				return;
			}
			var memo = $("#updateadSubjectModal #memo").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=SubjectInfoPath.BASE + SubjectInfoPath.UPDATE %>',
				data:
				{
					"id":id,
					"subjectName" : subjectName,
					"subjectLevel" 	: subjectLevel,
					"subjectCode" 	: subjectCode,
					"parentCode"	: parentCode,
					"subjectType":subjectType,
					"subjectAmt":subjectAmt,
					"currCode":currCode,
					"memo": memo
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#updateadSubjectModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("更新失败！"+data.message);
					}
				}
			});
		});
		
		$(".addSubjectBtn").click(function(){
			var subjectName = $("#addsubjectModal #subjectName").val();
			if(kong.test(subjectName)) {
				$.gyzbadmin.alertFailure("科目级别不可为空");
				return;
			}

			var subjectCode = $("#addsubjectModal #subjectCode").val();
			if(kong.test(subjectCode)) {
				$.gyzbadmin.alertFailure("科目代码不可为空");
				return;
			}
			
			var validate =true ;
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=SubjectInfoPath.BASE+SubjectInfoPath.VALIDATE%>',
		        data:{"subjectCode":subjectCode},
		        success:function(data){
		        	if(data.result=="FAIL"){
						$.gyzbadmin.alertFailure("科目代码已存在");
						validate = false;
					}else{
						validate = true;
					}
				  }
		        });
			if(!validate){
				return false;
			}
			var subjectType = $("#addsubjectModal #subjectType").val();
			if(kong.test(subjectType)) {
				$.gyzbadmin.alertFailure("科目类型不可为空");
				return;
			}
			var subjectAmt = $("#addsubjectModal #subjectAmt").val();
			var subjectLevel = $("#addsubjectModal #subjectLevel").val();
			if(kong.test(subjectLevel)) {
				$.gyzbadmin.alertFailure("科目级别不可为空");
				return;
			}
			var parentCode = $("#addsubjectModal #parentCode").val();
			if(kong.test(parentCode)) {
				$.gyzbadmin.alertFailure("父级科目编号不可为空");
				return;
			}
			var currCode = $("#addsubjectModal #currCode").val();
			if(kong.test(currCode)) {
				$.gyzbadmin.alertFailure("币别不可为空");
				return;
			}
			var memo = $("#addsubjectModal #memo").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=SubjectInfoPath.BASE + SubjectInfoPath.ADD %>',
				data:
				{
					"subjectName"   : subjectName,
					"subjectLevel" 	: subjectLevel,
					"subjectCode" 	: subjectCode,
					"parentCode"	: parentCode,
					"subjectType":subjectType,
					"subjectAmt":subjectAmt,
					"currCode":currCode,
					"memo": memo
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="success"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addsubjectModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.message);
					}
				}
			});
		})
		
		$(".deleteSubjectLink").click(function(){
			var sub = $.data($(this).parent().parent()[0],"sub");
			$("#deleteadModal").find("input[name='id']").val(sub.id);
			$("span.sureDel").text(sub.id);
		});
		
		// 删除
		$(".deleteSubjectBtn").click(function(){
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=SubjectInfoPath.BASE + SubjectInfoPath.DELETE %>',
				data:"id="+$("#deleteadModal #id").val(),
				success:function(data){
					$.unblockUI();
					if(data.result=='success'){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteadModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("删除失败！"+data.message);
					}
				}
			});
		})
	});
</script>
</body>
</html>					