<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.qifenqian.bms.sms.message.BaseMessagePath"%>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/selectAll.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>短信管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="baseMessageLoad()">
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
							<form action='<c:url value="<%=BaseMessagePath.BASE + BaseMessagePath.LIST %>"/>' method="post" id="searchForm">
							<table class="search-table">
								<tr>
									<td class="td-left" width="15%">手机号码</td>
									<td class="td-right" width="35%">
										<span class="input-icon">
											<input type="text" name="mobile"  id="mobile" value="${queryBean.mobile }" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="15%">发送状态</td>
									<td class="td-right" width="35%">
									   <input type="hidden" name="statusTemp"  id="statusTemp" value="${queryBean.status}">
									   <span class="input-icon">
											<select name="status" id="status">
												<option value="">--请选择--</option>
												<option value="INIT" >-待发送-</option>
												<option value="SUCCESS" >-成功-</option>
												<option value="EXCEPTION" >-异常-</option>
												<option value="FAILURE" >-失败-</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" width="15%">姓名</td>
									<td class="td-right" width="35%">
										<span class="input-icon">
											<input type="text" name="custName"  id="custName" value="${queryBean.custName }" size="35">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="15%">数据日期</td>
									<td class="td-right" width="35%">
										<input type="text"  name="sendStartTime" id="sendStartTime" value="${queryBean.sendStartTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="sendEndTime" id="sendEndTime" value="${queryBean.sendEndTime }"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>										
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm searchBtn" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											&nbsp;
											<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											&nbsp;
											<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.IMPORT_EXCEL%>">
												<button class="btn btn-purple btn-sm btn-margin importExcelLink" data-toggle='modal' data-target="#importExcelModal">
													导入EXCEL
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											&nbsp;
											<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.BATCH_SEND%>">
												<a href="#"  data-toggle='modal' class="tooltip-error batchSendLink" data-rel="tooltip" title="批量发送" data-target="#batchSendModal">
													<button type="submit" class="btn btn-purple btn-sm">批量发送</button>
												</a> 
											</gyzbadmin:function>
											&nbsp;
											<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.BATCH_DELETE%>">
												<a href="#"  data-toggle='modal' class="tooltip-error batchDeletelink" data-rel="tooltip" title="批量删除" data-target="#batchDeleteModal">
													<button type="submit" class="btn btn-purple btn-sm">批量删除</button>
												</a>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">信息列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="4%"><label ><input type="checkbox" name="batchMessageDeposit"  id="batchMessageDeposit" title="全选" /></label></th>
											<th width="6%">编号</th>
											<th width="10%">手机号码</th>
											<th width="8%">姓名</th>
											<th width="8%">金额</th>
											<th width="8%">数据日期</th>
											<th width="8%">创建人</th>
											<th width="10%">创建时间</th>
											<th width="8%">发送状态</th>
											<th width="10%">发送时间</th>
											<th width="8%">短信详情</th>
											<th width="12%">操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${baseMessageList}" var="baseMessage">
										<tr class="baseMessage">
											<td>
												<c:if test="${baseMessage.status=='INIT'}">
													<input type="checkbox" name="messageDeposit"  id="messageDeposit" value="${baseMessage.id}#${baseMessage.custName}#${baseMessage.mobile}#${baseMessage.dateData}#${baseMessage.status}"/>
												</c:if>
											</td>
											<td>${baseMessage.id }</td>
											<td>${baseMessage.mobile }</td>
											<td>${baseMessage.custName }</td>
											<td>${baseMessage.custAmt }</td>
											<td>${baseMessage.dateData }</td>
											<td>${baseMessage.instUser }</td>
											<td>${baseMessage.instDatetime }</td>
											<td>
												<c:if test="${baseMessage.status =='INIT'}">待发送</c:if>
												<c:if test="${baseMessage.status =='SUCCESS'}">成功</c:if> 
												<c:if test="${baseMessage.status =='FAILURE'}">失败</c:if> 
												<c:if test="${baseMessage.status =='EXCEPTION'}">异常</c:if>  
											</td>
											<td>${baseMessage.sendTime }</td>
											<td>
												<a href="#contentModal"  data-toggle='modal' class="tooltip-success contentLink" data-rel="tooltip" title="查看详情" >
													<button type="submit" class="btn btn-purple btn-sm">查看详情</button>
												</a>
												<textarea id="messageContnet" name ="content" style="display:none">${baseMessage.content}</textarea>
											</td>
											<td>
												<c:if test="${baseMessage.status =='INIT'}">
													<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.SINGLE_SEND%>">
														<a href="#"  data-toggle='modal' class="tooltip-error singleSendlink" data-rel="tooltip" title="发送短信" data-target="#singleSendModal">
															<button type="submit" class="btn btn-purple btn-sm">发送短信</button>
														</a>
													</gyzbadmin:function>
												</c:if>
												&nbsp;
												<gyzbadmin:function url="<%=BaseMessagePath.BASE + BaseMessagePath.DELETE%>">
													<a href="#"  data-toggle='modal' class="tooltip-error deleteMessagelink" data-rel="tooltip" title="删除" data-target="#deleteMessageModal">
														<span class="red"><i class="icon-trash bigger-120"></i></span>
													</a>
													<input type ="hidden" name="id"  value="${baseMessage.id}"/>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty baseMessageList}">
										<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty baseMessageList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
				</div>
				<div class="modal fade" id="contentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">短信详情</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table">
				                <tr>
									<td class="td-right" >
										<textarea rows="5" cols="" name="content" id="content" clasS="width-100"></textarea>
									</td>
								</tr>
				            </table>
				         </div>
				         <div class="modal-footer">
				           <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				         </div>
				      </div><!-- /.modal-content -->
				    </div>
		        </div>
				<div class="modal fade" id="deleteMessageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除短信</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该短信[<span class="id"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="id" class="id" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMessageBtn">确定</button>
					         </div>
				      </div><!-- /.modal-content -->
				    </div>
				  </div><!-- /.modal -->
				  <div class="modal fade" id="singleSendModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog" style="width: 600px;">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title transCls" id="myModalLabel">发送短信</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table" style="width: 100%;">
									<tr>
										<td class="td-left" width="20%">编号</td>
										<td class="td-right">
											<input type="text" id="id" name="id" disabled="disabled" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">手机号码</td>
										<td class="td-right">
											<input type="text" id="mobile" name="mobile" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">数据日期</td>
										<td class="td-right">
											<input type="text" id="dateData" name="dateData" class="width-100" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">姓名</td>
										<td class="td-right">
											<input type="text" id="custName" name="custName" class="width-100"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">金额</td>
										<td class="td-right">
											<input type="text" id="custAmt" name="custNcustAmtame" class="width-100"/>
										</td>
									</tr>
									
									<tr>
										<td class="td-left">短信详情</td>
										<td class="td-right">
											<textarea rows="4" cols="" name ="content" id ="content"  maxlength="300" class="width-100"></textarea>
										</td>
									</tr>
					            </table>	             
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" id="transBtn" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary" id="singleSendBtn">发送</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
				<div class="modal fade" id="batchDeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">批量删除</h4>
				         </div>
				         <div class="modal-body" align="center" >
				         	  <table class="list-table" id="messageInfoS">
					           <thead>
									<tr>
										<th>编号</th>
										<th>客户名称</th>
										<th>手机号码</th>
										<th>数据日期</th>
								    </tr>
							  </thead>
				            </table>	  
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary batchDeleteBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
					</div><!-- /.modal -->
					<div class="modal fade" id="batchSendModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog" style="width: 600px;">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title transCls" id="myModalLabel">批量发送</h4>
					         </div>
					         <div class="modal-body" id="messageDiv">
					            <table class="list-table" id="messageInfoS">
									<thead>
										<tr>
											<th>编号</th>
											<th>客户名称</th>
											<th>手机号码</th>
											<th>数据日期</th>
									    </tr>
							  		</thead>
					            </table>	             
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" id="transBtn" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary" id="batchSendBtn">确认</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					<div class="modal fade" id="importExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">导入EXCEL</h4>
					         </div>
						         <div class="modal-body">
						            <table class="modal-input-table" id="fileTab">
										<tr>
											<td class="td-left">请选择文件：</td>
											<td class="td-right">	
												<input type ="file" name ="fileName" id="fileName" style="width:80%;">
											</td>
											</td>
										</tr>
										<tr>
											<td class="td-left">工作表选项：</td>
											<td class="td-right">	
												<select name="sheetIndex" id="sheetIndex">
													<option value="LAST_SHEET" >- 最后一个工作表 -</option>
													<option value="ALL_SHEET" >- 所有工作表 -</option>
													<option value="FIRST_SHEET" >- 第一个工作表 -</option>
												</select>
											</td>
											
										</tr>
										<tr>
											<td class="td-left">模板：</td>
											<td class="td-right">	
												<select name="dictpath" id="dictpath">
													<option value="send_batch_message" >- 批量发送短信 -</option>
													<option value="send_batch_message_mer" >- app升级短信模板 -</option>
												</select>
											</td>
											
										</tr>
						            </table>
						         </div>
						          <div class="modal-footer">
						            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						            <button type="submit" class="btn btn-primary importExcelBtn">提交</button> 
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
	</div>
   <script type="text/javascript">
	function baseMessageLoad(){
		$(".search-table #status").val($(".search-table #statusTemp").val());
	}
	
	jQuery(function($){
		var baseMessageListJson =${baseMessageList};
		var baseMessageTrList = $('tr.baseMessage');
		$.each(baseMessageListJson, function(idx, obj){
			$.data(baseMessageTrList[idx], 'baseMessage', obj);
		});	
		
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		$.fn.check({ checkall_name: "batchMessageDeposit", checkbox_name: "messageDeposit"});
		
		// 加载批量删除
		$(".batchDeletelink").click(function(){
			  if($("input[type='checkbox'][name='messageDeposit']").is(':checked')==false){
				  $.gyzbadmin.alertFailure("请选择要删除的数据!");
				  return false;
			  }
			  var deleteObj = document.getElementsByName('messageDeposit');
			  var deleteObjString = '';
			  for(var i=0;i<deleteObj.length;i++){
				 if(deleteObj[i].checked){
					 var statu = deleteObj[i].value.split('#')[4];
					 if(statu == 'INIT'){
						 deleteObjString += deleteObj[i].value+'@';
					 }
				 }
			  }
			  if(deleteObjString == ''){
				  $.gyzbadmin.alertFailure("选择列表无待处理记录！");
				  return false;
			  }else{
				 
				  $("#batchDeleteModal #messageInfoS tr:gt(0)").remove();
				  $("#batchDeleteModal #deleteIds").remove();
				 
				  deleteObjString = deleteObjString.substring (0,deleteObjString.length-1);
				 
				 var deleteArray = deleteObjString.split('@');
				 var str = '';
				 var deleteIds = '';
				 for(var j= 0 ;j<deleteArray.length;j++){
					 var id  = deleteArray[j].split('#')[0];
					 var custName  = deleteArray[j].split('#')[1];
					 var mobile  = deleteArray[j].split('#')[2];
					 var dateData = deleteArray[j].split('#')[3];
					 str+="<tr><td>"+id+"</td><td>"+custName+"</td><td>"+mobile+"</td><td>"+dateData+"</td></tr>";
					 deleteIds += id+'*';
				 }
				 deleteIds = deleteIds.substring(0,deleteIds.length-1);
				 var ipt = "<input type='hidden' id='deleteIds' value='"+deleteIds+"'/>";
				 $("#batchDeleteModal #messageInfoS").append(str).append(ipt);
			  }
		  });
		
		// 批量删除
		 $(".batchDeleteBtn").click(function(){
			  var deleteString = $("#batchDeleteModal #deleteIds").val();
			  $.blockUI();
			  $.post(window.Constants.ContextPath + '<%=BaseMessagePath.BASE + BaseMessagePath.BATCH_DELETE%>', {
					'deleteIds' 	: deleteString
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#batchDeleteModal').modal('hide');
						$.gyzbadmin.alertSuccess('删除成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('删除失败:' + data.message);
					}
				}, 'json'
			);
		  });
		
		// 加载批量发送
		$(".batchSendlink").click(function(){
			  if($("input[type='checkbox'][name='messageDeposit']").is(':checked')==false){
				  $("#batchSendModal #messageInfoS").css("display","none");
				  $("#batchSendModal #sendIds").remove();
				  $("#batchSendModal #resultMessage").remove();
				  var resultMessage ='发送所有待发送的数据?';
				  var str="<span id='resultMessage' style='color: red; font-weight: bold; font-size: 15px;'>"+resultMessage+"</span>";
				  var sendIds="000000";
				  var ipt = "<input type='hidden' id='sendIds' value='"+sendIds+"'/>";
				  $("#batchSendModal #messageDiv").append(str).append(ipt);
				  
			  }else{
				  var sendObj = document.getElementsByName('messageDeposit');
				  var sendObjString = '';
				  for(var i=0;i<sendObj.length;i++){
					 if(sendObj[i].checked){
						 var statu = sendObj[i].value.split('#')[4];
						 if(statu == 'INIT'){
							 sendObjString += sendObj[i].value+'@';
						 }
					 }
				  }
				  if(sendObjString == ''){
					  $.gyzbadmin.alertFailure("选择列表无待处理记录！");
					  return false;
					  
				  }else{
					  $("#batchSendModal #messageInfoS").css("display","block");
					  $("#batchSendModal #messageInfoS tr:gt(0)").remove();
					  $("#batchSendModal #sendIds").remove();
					  $("#batchSendModal #resultMessage").remove();
					 
					  sendObjString = sendObjString.substring (0,sendObjString.length-1);
					 
					 var sendArray = sendObjString.split('@');
					 var str = '';
					 var sendIds = '';
					 for(var j= 0 ;j<sendArray.length;j++){
						 var id  = sendArray[j].split('#')[0];
						 var custName  = sendArray[j].split('#')[1];
						 var mobile  = sendArray[j].split('#')[2];
						 var dateData = sendArray[j].split('#')[3];
						 str+="<tr><td>"+id+"</td><td>"+custName+"</td><td>"+mobile+"</td><td>"+dateData+"</td></tr>";
						 sendIds += id+'*';
					 }
					 sendIds = sendIds.substring(0,sendIds.length-1);
					 var ipt = "<input type='hidden' id='sendIds' value='"+sendIds+"'/>";
					 $("#batchSendModal #messageInfoS").append(str).append(ipt);
				  }
			  }
		  });
			
		 // 批量发送 
		 $("#batchSendBtn").click(function(){
			  var sendString = $("#batchSendModal #sendIds").val();
			  $.blockUI();
			  $.post(window.Constants.ContextPath + '<%=BaseMessagePath.BASE + BaseMessagePath.BATCH_SEND%>', {
					'sendIds' 	: sendString
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#batchSendModal').modal('hide');
						$.gyzbadmin.alertSuccess('发送完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('发送失败:' + data.message);
					}
				}, 'json'
			);
		  });
		
		//查看内容
		$('.contentlink').click(function(){
			var messageContent = $(this).parent().parent().find("textarea[name='content']").val();
			$('#contentModal').on('show.bs.modal', function () {
				$('#contentModal #content').val(messageContent);
			});
			$('#contentModal').on('hide.bs.modal', function () {
				$('#contentModal #content').val('')
				
			});
		})
		
		// 删除层准备
		$('.deleteMessagelink').click(function(){
			var id = $(this).parent().find('input[name="id"]').val();
			$('#deleteMessageModal').on('show.bs.modal', function () {
				// 赋值
				$('#deleteMessageModal span.id').html(id);
				$('#deleteMessageModal input.id').val(id);
			});
			$('#deleteMessageModal').on('hide.bs.modal', function () {
				$('#deleteMessageModal span.id').html('');
				$('#deleteMessageModal input.id').val('');
			});
		});
		
		// 删除
		$('.deleteMessageBtn').click(function(){
			var id = $('#deleteMessageModal input.id').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=BaseMessagePath.BASE + BaseMessagePath.DELETE %>', {
					'id' : id
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#deleteMessageModal').modal('hide');
						$.gyzbadmin.alertSuccess('删除成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('删除失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		/**加载单个发送 **/
		$('.singleSendlink').click(function(){
		    var baseMessage = $.data($(this).parent().parent()[0], 'baseMessage');
		      $('#singleSendModal').on('show.bs.modal', function () {
			   $('#singleSendModal #id').val(baseMessage.id);
			   $('#singleSendModal #mobile').val(baseMessage.mobile);
			   $('#singleSendModal #custName').val(baseMessage.custName);
			   $('#singleSendModal #dateData').val(baseMessage.dateData);
			   $('#singleSendModal #custAmt').val(baseMessage.custAmt);
			   $('#singleSendModal #content').val(baseMessage.content);
		   });
		   $('#singleSendModal').on('hide.bs.modal', function (){
			   $('#singleSendModal #id').val('');
			   $('#singleSendModal #mobile').val('');
			   $('#singleSendModal #custName').val('');
			   $('#singleSendModal #dateData').val('');
			   $('#singleSendModal #custAmt').val('');
			   $('#singleSendModal #content').val('');
		   }); 
		});
		
		/**单个发送 **/
		$('#singleSendBtn').click(function(){
			var id = $('#singleSendModal #id').val();
			if(kong.test(id)){
				$.gyzbadmin.alertFailure('编号为空');
				return;
			}
			var mobile = $('#singleSendModal #mobile').val();
			if(kong.test(mobile)){
				$.gyzbadmin.alertFailure('手机号为空');
				return;
			}
			var custName = $('#singleSendModal #custName').val();
			if(kong.test(custName)){
				$.gyzbadmin.alertFailure('姓名为空');
				return;
			}
			var dateData = $('#singleSendModal #dateData').val();
			if(kong.test(dateData)){
				$.gyzbadmin.alertFailure('数据日期为空');
				return;
			}
			var custAmt = $('#singleSendModal #custAmt').val();
			if(kong.test(custAmt)){
				$.gyzbadmin.alertFailure('金额为空');
				return;
			}
			var content = $('#singleSendModal #content').val();
			if(kong.test(content)){
				$.gyzbadmin.alertFailure('短信详情为空');
				return;
			}
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=BaseMessagePath.BASE + BaseMessagePath.SINGLE_SEND %>', {
				    'id'		:id,
				    'mobile'	:mobile,
				    'custName'	:custName,
				    'dateData'	:dateData,
				    'custAmt'	:custAmt,
				    'content'	:content
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#singleSendModal').modal('hide');
						$.gyzbadmin.alertSuccess('发送成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message, null, function(){
							window.location.reload();
						});
					};
				}, 'json'
			);
		});
		
	    /**上传**/
	 	$(".importExcelBtn").click(function(){
			var fileName = $('#importExcelModal #fileName').val();
			if(kong.test(fileName)) {
				$.gyzbadmin.alertFailure('请上传EXCEL文件');
				return;
			}
			var sheetIndex = $('#importExcelModal #sheetIndex').val();
			var table = document.getElementById("fileTab");
			table.rows[0].cells[1].getElementsByTagName("input")[0].name=sheetIndex;
			var dictpath  = $('#importExcelModal #dictpath').val();
			//alert(dictpath);
			
		    $.blockUI();
			$.ajaxFileUpload({  
		        url : window.Constants.ContextPath +'<%=BaseMessagePath.BASE + BaseMessagePath.IMPORT_EXCEL%>?dictpath='+dictpath,
		        secureuri : false,
		        fileElementId : ['fileName'],
		        dataType:'json', 
		        success : function(data, status) { 
		        	$.unblockUI();
		        	if(data.result=='SUCCESS'){
		        		$('#importExcelModal').modal('hide');
						$.gyzbadmin.alertSuccess('上传完成', null, function(){
							window.location.reload();
						});
		        		
		        	}else{
		        		$.gyzbadmin.alertFailure('上传失败:' + data.message);
		        	}
		        }
			}); 
	 	}); 
  });
   </script>
  </body>	
</html>