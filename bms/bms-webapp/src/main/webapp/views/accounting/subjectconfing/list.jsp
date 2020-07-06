<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.subjectConfig.SubjectConfigPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>科目配置管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadSubjectConfig()">
	<!-- 科目配置信息 -->
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
				<div class="page-content"  style=" width: 100%;">
					<div class="row">
						<div class="col-xs-12">
							<!-- 查询条件 -->
							<form id="subjectConfigForm" action='<c:url value="<%=SubjectConfigPath.BASE + SubjectConfigPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
								<td class="td-left">编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" name="id" id="id"   value="${queryBean.id}"/>	
											<i class="icon-leaf blue"></i>
										</span>				
									</td>
									<td class="td-left">付方账户类型</td>
									<td class="td-right" >
										<input type="hidden" id="payAccTypeTemp" value="${queryBean.payAccType}">
										<sevenpay:selectAcountCustTypeTag name="payAccType" id="payAccType" />
									</td>
									<td class="td-left">借方科目</td>
									<td class="td-right" >
										<input type="hidden" id="subjectDTemp" value="${queryBean.subjectD}">
										<sevenpay:selectSubjecInfoIdTag name="subjectD" id="subjectD"  />
									</td>
								</tr>
								<tr>
								<td class="td-left">交易类型</td>
									<td class="td-right">
										<input type="hidden" id="tradTypeTemp" value="${queryBean.tradType}">
										<sevenpay:selectTransferTransTypeTag name="tradType" id="tradType"/>
									</td>
									<td class="td-left">收方账户类型</td>
									<td class="td-right" >
										<input type="hidden" id="rcvAccTypeTemp" value="${queryBean.rcvAccType}">
										<sevenpay:selectAcountCustTypeTag name="rcvAccType" id="rcvAccType"/>
									</td>
									
									<td class="td-left">贷方科目</td>
									<td class="td-right" >
										<input type="hidden" id="subjectCTemp" value="${queryBean.subjectC}">	
										<sevenpay:selectSubjecInfoIdTag name="subjectC" id="subjectC" />
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
												<gyzbadmin:function url="<%=SubjectConfigPath.BASE + SubjectConfigPath.LIST %>">
												<button  type="submit" class="btn btn-purple btn-sm btn-margin" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
												</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin "  id="clearSubjectConfig">
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
												<gyzbadmin:function url="<%=SubjectConfigPath.BASE + SubjectConfigPath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin" data-toggle='modal' data-target="#addsubjectConfigModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>	
												</gyzbadmin:function>											
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">科目配置列表</div>
							<div class="table-responsive">
								<table id="sample-table-2"  data-toggle="table" class="list-table">
									<thead>
										<tr>
										    <th width="5%">编号</th>
										    <th width="11%">交易类型</th>
											<th>付方账户类型</th>
											<th>借方科目</th>
											<th>收方账户类型</th>
											<th>贷方科目</th>
											<th>创建人</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${subConfigList}" var="sub" varStatus="status">
											<tr class="sub" >
												<td width="5%">${sub.id}</td>
												<td width="11%">${sub.tradType}</td>
												<td width="16%">
													<sevenpay:selectAcountCustTypeTag name="payAccType" id="payAccType" disabled="true"  defaultValue="${sub.payAccType}" />
												</td>
												<td width="16%">
													<sevenpay:selectSubjecInfoIdTag name="subjectD" id="subjectD" disabled="true"   defaultValue="${sub.subjectD}"/>
												</td>
												<td width="16%">
													<sevenpay:selectAcountCustTypeTag name="rcvAccType" id="rcvAccType" disabled="true"   defaultValue="${sub.rcvAccType}"/>
												</td>
												<td width="8%">
													<sevenpay:selectSubjecInfoIdTag name="subjectC" id="subjectC" disabled="true"    defaultValue="${sub.subjectC}"/>
												</td>
												<td width="5%">${sub.creator }</td>
												<td width="5%">${sub.memo }</td>
												<td width="10%">
												    <gyzbadmin:function url="<%=SubjectConfigPath.BASE + SubjectConfigPath.UPDATE%>">
														<a href="#" class="tooltip-success updateadSubjectModal" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#updateSubjectConfigModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
														</a>
													</gyzbadmin:function>
													
													<gyzbadmin:function url="<%=SubjectConfigPath.BASE + SubjectConfigPath.DELETE%>">
														<a href="#deleteadModal"  data-toggle='modal' class="tooltip-error deleteSubjectConfig" data-rel="tooltip" title="Delete">
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
														</a>	
													</gyzbadmin:function>									
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty subConfigList}">
											<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
								</div>	
								<!-- 分页 -->
								<c:if test="${not empty subConfigList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
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
	
	
	<div class="modal fade" id="addsubjectConfigModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加科目配置信息</h4>
		         </div>
		         <div class="modal-body">
		         	   <table id="subjectConfig" class="modal-input-table" >
		         	   <tr>
							<td class="td-left" width="20%">交易类型<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<sevenpay:selectTransferTransTypeTag name="tradType" id="tradType" clasS="width-80"/>
							</td>
						</tr>
		            	<tr>
							<td class="td-left">借方科目代码<span style="color:red">*</span></td>
							<td class="td-right">
								<sevenpay:selectSubjecInfoIdTag name="subjectD" id="subjectD" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">贷方科目代码<span style="color:red">*</span></td>
							<td class="td-right">
								<sevenpay:selectSubjecInfoIdTag name="subjectC" id="subjectC" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">付方账户类型<span style="color:red">*</span></td>
							<td class="td-right">
								<sevenpay:selectAcountCustTypeTag name="payAccType" id="payAccType" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">收方账户类型<span style="color:red">*</span></td>
							<td class="td-right">
								<sevenpay:selectAcountCustTypeTag name="rcvAccType" id="rcvAccType" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea rows="3" id="memo" name="memo" maxlength="300" clasS="width-80"></textarea>
							</td>
						</tr>				
		            </table>
		         	
						
		         </div>
		         <div class="modal-footer"  style="display:block">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button"  onclick="addSubjectConfig()"  class="btn btn-primary addfunctionBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		
		<div class="modal fade" id="updateSubjectConfigModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">修改科目配置信息</h4>
		         </div>
		         <div class="modal-body">
		         	 <table id="subjectConfig" class="modal-input-table" style="width: 100%;">
		         	   <tr>
							<td class="td-left" width="20%">交易类型<span style="color:red">*</span></td>
							<td class="td-right" width="80%">
								<input type="hidden" name="id" id="id">
								<input type="text" name="tradType" id="tradType" readonly="readonly" clasS="width-80"/>
							</td>
						</tr>
		            	<tr>
							<td class="td-left" >借方科目代码<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectSubjecInfoIdTag name="subjectD" id="subjectD" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >贷方科目代码<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectSubjecInfoIdTag name="subjectC" id="subjectC" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >付方账户类型<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectAcountCustTypeTag name="payAccType" id="payAccType" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left" >收方账户类型<span style="color:red">*</span></td>
							<td class="td-right" >
								<sevenpay:selectAcountCustTypeTag name="rcvAccType" id="rcvAccType" clasS="width-80"/>
							</td>
						</tr>
						<tr>
							<td class="td-left">备注</td>
							<td class="td-right">
								<textarea rows="3" id="memo" name="memo" maxlength="300" clasS="width-80"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer"  style="display:block">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button"  onclick="updateSubjectConfig()"  class="btn btn-primary addfunctionBtn">提交</button>
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
		            <button type="button" class="btn btn-primary deleteadBtn" onclick="delad()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
<script type="text/javascript">
	
	function loadSubjectConfig(){
		$(".search-table #tradType").val($(".search-table #tradTypeTemp").val());
		$(".search-table #payAccType").val($(".search-table #payAccTypeTemp").val());
		$(".search-table #rcvAccType").val($(".search-table #rcvAccTypeTemp").val());
		$(".search-table #subjectD").val($(".search-table #subjectDTemp").val());
		$(".search-table #subjectC").val($(".search-table #subjectCTemp").val());
	}

	$(document).ready(function(){
		$("#clearSubjectConfig").click(function(){
			$(".search-table #id").val('');
			$(".search-table #tradType").val('');
			$(".search-table #payAccType").val('');
			$(".search-table #rcvAccType").val('');
			$(".search-table #subjectD").val('');
			$(".search-table #subjectC").val('');
		});
		var subs= ${subConfigList};
		
		var subConfigList=$("tr.sub");
		$.each(subs,function(i,value){
			
			$.data(subConfigList[i],"sub",value);
			
		});

		$(".updateadSubjectModal").click(function(){
			var sub = $.data($(this).parent().parent()[0],"sub");
			
			$("#updateSubjectConfigModal #id").val(sub.id);
			$("#updateSubjectConfigModal #subjectD").val(sub.subjectD);
			$("#updateSubjectConfigModal #subjectC").val(sub.subjectC);
			$("#updateSubjectConfigModal #payAccType").val(sub.payAccType);
			$("#updateSubjectConfigModal #rcvAccType").val(sub.rcvAccType);
			$("#updateSubjectConfigModal #tradType").val(sub.tradType);
			$("#updateSubjectConfigModal #memo").val(sub.memo);

		})
		
		$(".deleteSubjectConfig").click(function(){
			var sub = $.data($(this).parent().parent()[0],"sub");
			$("#deleteadModal").find("input[name='id']").val(sub.id);
			$("span.sureDel").text(sub.id);
		});
	
	});
	
	//  删除
	function delad(){
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=SubjectConfigPath.BASE + SubjectConfigPath.DELETE %>',
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
	
	}
	//修改 
	function updateSubjectConfig(){
		
		var id= $("#updateSubjectConfigModal #id").val();
		
		var subjectD = $("#updateSubjectConfigModal #subjectD").val();
		if(kong.test(subjectD)) {
			$.gyzbadmin.alertFailure("借方科目编号不可为空");
			$("#updateSubjectConfigModal #subjectD").focus();
			return;
		}
		
		var subjectC = $("#updateSubjectConfigModal #subjectC").val();
		if(kong.test(subjectC)) {
			$.gyzbadmin.alertFailure("贷方科目编号不可为空");
			$("#updateSubjectConfigModal #subjectC").focus();
			return;
		}
		
		var payAccType = $("#updateSubjectConfigModal #payAccType").val();
		if(kong.test(payAccType)) {
			$.gyzbadmin.alertFailure("付方账户类型不可为空");
			$("#updateSubjectConfigModal #payAccType").focus();
			return;
		}
		var rcvAccType = $("#updateSubjectConfigModal #rcvAccType").val();
		if(kong.test(rcvAccType)) {
			$.gyzbadmin.alertFailure("收方账户类型不可为空");
			$("#updateSubjectConfigModal #rcvAccType").focus();
			return;
		}
		
		var state = $("#updateSubjectConfigModal #state").val();
		if(kong.test(state)) {
			$.gyzbadmin.alertFailure("科目配置状态不可为空");
			$("#updateSubjectConfigModal #state").focus();
			return;
		}
		var memo = $("#updateSubjectConfigModal #memo").val();
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=SubjectConfigPath.BASE + SubjectConfigPath.UPDATE %>',
			data:
			{
				"id":id,
				"subjectD" : subjectD,
				"subjectC" 	: subjectC,
				"payAccType" 	: payAccType,
				"rcvAccType" 	: rcvAccType,
				"memo": memo
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("更新成功！",function(){
						$("#updateSubjectConfigModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添更新失败！"+data.message);
				}
			}
		});
	}
	
	//新增 
	function addSubjectConfig(){
		
		var subjectD = $("#addsubjectConfigModal #subjectD").val();
		if(kong.test(subjectD)) {
			$.gyzbadmin.alertFailure("借方科目编号不可为空");
			$("#addsubjectConfigModal #subjectD").focus();
			return;
		}
		
		var subjectC = $("#addsubjectConfigModal #subjectC").val();
		if(kong.test(subjectC)) {
			$.gyzbadmin.alertFailure("贷方科目编号不可为空");
			$("#addsubjectConfigModal #subjectC").focus();
			return;
		}
		
		var payAccType = $("#addsubjectConfigModal #payAccType").val();
		if(kong.test(payAccType)) {
			$.gyzbadmin.alertFailure("付方账户类型不可为空");
			$("#addsubjectConfigModal #payAccType").focus();
			return;
		}
		var rcvAccType = $("#addsubjectConfigModal #rcvAccType").val();
		if(kong.test(rcvAccType)) {
			$.gyzbadmin.alertFailure("收方账户类型不可为空");
			$("#addsubjectConfigModal #rcvAccType").focus();
			return;
		}
		
		var tradType = $("#addsubjectConfigModal #tradType").val();
		if(kong.test(tradType)) {
			$.gyzbadmin.alertFailure("交易类型不可为空");
			$("#addsubjectConfigModal #tradType").focus();
			return;
		}
		
		var memo = $("#addsubjectConfigModal #memo").val();
		
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=SubjectConfigPath.BASE + SubjectConfigPath.ADD %>',
			data:
			{
				"subjectD" : subjectD,
				"subjectC" 	: subjectC,
				"payAccType" 	: payAccType,
				"rcvAccType" 	: rcvAccType,
				"tradType"	: tradType,
				"memo": memo
			},
			success:function(data){
				$.unblockUI();
				if(data.result=="success"){
					$.gyzbadmin.alertSuccess("添加成功！",function(){
						$("#addsubjectConfigModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("添加失败！"+data.message);
				}
			}
		});
	}
	
	</script>
</body>
</html>					