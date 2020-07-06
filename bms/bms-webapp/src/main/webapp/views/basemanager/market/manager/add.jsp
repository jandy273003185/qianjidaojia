<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.market.controller.ManagerPath"%>
<%@page import="com.qifenqian.bms.basemanager.market.bean.Manager"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加市场经理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body >
<%@ include file="/include/alert.jsp"%>
		      <div class="modal-content" style="width: 60%;margin:0 auto">
		         <div class="modal-body">
		     	<form  id="bussBalanceWaterForm" action='<c:url value="<%=ManagerPath.BASE + ManagerPath.ADD %>"/>' method="post">
					<table class="search-table"  style="border:none;" >	
							<tr>
									<td class="td-left" >客户经理账号</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="userCode" id="userCode"  value="${manager.userCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >客户经理名称</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="text"  name="userName" id="userName"  value="${manager.userName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >创建时间</td>
									<td class="td-right" colspan="3"> 
										 <input type="text" name="createStartTime"  id="createStartTime" readonly="readonly"  value="${manager.createStartTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="createEndTime" id="createEndTime" readonly="readonly" value="${manager.createEndTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
							<tr>
								<td colspan="12" align="center">
									<button type="submit"  class="btn btn-purple btn-sm serchSubmit" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button class="btn btn-purple btn-sm btn-margin clearBank" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>	
								</td>													
							</tr>
						</table>
					</form>	
						<div class="list-table-header">市场经理列表</div>						
		         	<div class="table-responsive" id="createtable">
					 	<table id="sample-table-2" class="list-table">
							<thead>
								<tr>
									<th><label ><input type="checkbox" name="batchMessageDeposit"  id="batchMessageDeposit" title="全选"></label></th>
									<th>客户经理账号</th>
									<th>客户经理名称</th>
									<th>创建时间</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${addManagerList }"  var="manager" >
									<tr class=manager >
										<td>
										<input name="checkDeposit" type="checkbox" value="${manager.userCode}#${manager.userName}#${manager.createTime}" >
										</td>
										<td id="userCode">${manager.userCode}</td>
										<td id="userName">${manager.userName}</td>
										<td id="createTime">${manager.createTime}</td>
										
										<%-- <td width="30%"><fmt:formatDate value="${result.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>	
									</tr>											
								</c:forEach>
								<c:if test="${empty addManagerList}">
									<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody> 
						</table>
					</div>			
						<c:if test="${not empty addManagerList}">														
							<%@ include file="/include/page.jsp"%>		
						</c:if>	
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-primary addadBtn"  id="closeWindow">关闭</button>
		            <gyzbadmin:function url="<%=ManagerPath.BASE + ManagerPath.ADDBATCH%>">
							<a class="btn btn-primary batchWithdrawList"  href="#"  data-rel="tooltip"  data-toggle='modal' data-target="#batchModal">
								提交
							</a> 
					</gyzbadmin:function>
		         </div>
		      </div><!-- /.modal-content -->
		      
	 <div class="modal fade" id="batchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 700px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">添加市场经理分组</h4>
		         </div>
		         <div class="modal-body">
		         <form id="marketManageForm" action="#" method="post">
		           <table class="list-table" >
			           <thead>
							<tr>
								<th>市场经理账号</th>
								<th>市场经理名称</th>
								<th>创建时间</th>
						    </tr>
					  </thead>
					  <tbody id="tbPreviewBankCard">
					  </tbody>
		            </table>
		         </form>	             
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary" onclick="batchWithdrawDeposit()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div>
		
		
		
			<div class="modal fade" id="addManagerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">分组修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">客户经理账号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="userCode" name="userCode" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">客户经理名称<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="userName" name="userName" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left" width="20%">创建时间<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="createTime" name="createTime" readonly="readonly" clasS="width-80">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addManagerBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
	<script type="text/javascript">
	var details = new Array();
	/* function loadExportbalancewater(){
		$(".search-table #businessType").val($('.search-table #businessTypeTemp').val());
	} */
	$("#closeWindow").click(function(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();	
		window.close();
		});
	$("#batchSendBtn").click(function(){

		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();	
		window.close();
		});
	
	$('.clearBank').click(function(){
		$('.search-table #userCode').val('');
		$('.search-table #userName').val('');
		$('.search-table #createStartTime').val('');
		$('.search-table #createEndTime').val('');
	}) 
	 
	/* $.fn.check({ checkall_name: "batchMessageDeposit", checkbox_name: "messageDeposit"}); */
	$('#myTab li:eq(2) a').tab('show');
    var resultList= ${addManagerList};		    
	 	var managers=$("tr.manager");
		$.each(resultList,function(i,value){						
			$.data(managers[i],"manager",value);				
		});
	
	  
	$(".serchSubmit").click(function(){
		var startDate = $(".search-table #createStartTime").val();
		var endDate= $(".search-table #createEndTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#bussBalanceWaterForm');
		form.submit();
	});



	/* 添加市场经理  */
	$(".addManagerLink").click(function(){
		
		var manager = $.data($(this).parent().parent()[0],"manager");
		
		 $('#addManagerModal').on('show.bs.modal', function () {
		 	$("#addManagerModal #userCode").val(manager.userCode);
			$("#addManagerModal #userName").val(manager.userName);
			$("#addManagerModal #createTime").val(manager.createTime);
	     });
	     
		 $('#addManagerModal').on('hide.bs.modal', function () {
			$("#addManagerModal #userCode").val('');
			$("#addManagerModal #userName").val('');
			$("#addManagerModal #createTime").val('');
	     });
	});
	
	/* 添加市场经理 */
	 $(".addManagerBtn").click(function(){
		var userCode = $("#addManagerModal #userCode").val();
		var userName = $("#addManagerModal #userName").val();
		var createTime = $("#addManagerModal #createTime").val();
		
		$.blockUI();
		$.post(window.Constants.ContextPath +'<%=ManagerPath.BASE+ManagerPath.ADDBATCH%>',{
			'userCode'          :userCode,
			'userName'		    :userName,
			'createTime'		:createTime
			
			},function(data){
				$.unblockUI();
				if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess("更新成功！",null,function(){
						$("#addManagerModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("更新失败:"+data.message,function(){
						$("#addManagerModal").modal("hide");
					});
				}
			},'json'
		);
	}); 
	
	//选择客户经理信息
	$('.batchWithdrawList').click(function(){
		var box = document.getElementsByName("checkDeposit");
		
		for (var i=0;i<box.length;i++){
			 if(box[i].checked){ //判断复选框是否选中
				var info = box[i].value.split("#");
			 	userCode = info[0];
			 	userName = info[1];
			 	createTime = info[2];
				detail={
					"userCode":userCode,
					"userName":userName,
					"createTime":createTime
				};
				details.push(detail);
			 	$("#tbPreviewBankCard").append("<tr><td><input name='userCode' style='border:none' type='text' readonly='readonly' value='"+userCode+"'></input></td><td><input name='userName' type='text' style='border:none'  readonly='readonly' value='"+userName+"'></input></td><td><input name='createTime'type='text'style='border:none' readonly='readonly'  value='"+createTime+"'></input></td></tr>");	
			 }
		};
		$('#batchModal').on('hide.bs.modal',function(){
			$("#tbPreviewBankCard").empty();  
		});	
	});
	
	function batchWithdrawDeposit(){
		//alert(JSON.stringify($("#marketManageForm").serialize()));
		//var marketManages = decodeURIComponent($("#marketManageForm").serialize(),true);

		 $.ajax({ 
		     url: window.Constants.ContextPath +'<%=ManagerPath.BASE+ManagerPath.ADDBATCH%>', 
		     type: 'POST', 
		     data: {"marketManages":JSON.stringify(details)},
		     dataType:"json",
		     success: function(data) {
		    	 $.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("更新成功！",function(){
							$("#addManagerModal").modal("hide");
						},function(){
							window.location.reload();
							
						});
		    		 }
		     }, 
		     error: function(data) { 
		    	 $.gyzbadmin.alertFailure("更新失败:"+data.message,function(){
						$("#addManagerModal").modal("hide");
					});
		     }
		 });
		
	}
	
	$.fn.serializeObject = function() {
		var o = {};  
	    var a = this.serializeArray();  
	    $.each(a, function() {  
	        if (o[this.name]) {  
	            if (!o[this.name].push) {  
	                o[this.name] = [ o[this.name] ];  
	            }  
	            o[this.name].push(this.value || '');  
	        } else {  
	            o[this.name] = this.value || '';  
	        }  
	    });  
	    return o;  
	};
	</script>
</body>
</html>					