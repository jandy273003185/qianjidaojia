<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.acctsevenbuss.AcctSevenBussPath"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户账号管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body  onload = "loadAcctSevenBuss()">
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
			<div class="page-content" >
				<!-- 账户邮箱 -->
				<div >
				<form id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE+PaymentManagerPath.ACCTTOPAYBUSS %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">代付账号</td>
						<td class="td-right"> 
							<span class="input-icon">
							<input type="text" id="acctId" name="acctId" maxlength="55" value="${queryBean.email}" placeholder="代付账号">
							<i class="icon-leaf blue"></i>
							</span>
						</td>
						
					
					<td class="td-left">手机号码：</td>
					<td class="td-right" > 
						<span class="input-icon">
					   <input type="text"  name="mobile" id="mobile"  value="${queryBean.mobile}" placeholder="手机号码" >
						<i class="icon-leaf blue"></i>
						</span>
					</td>
					</tr>
					<tr>
					<td class="td-left">名称：</td>
						<td class="td-right" > 
						<span class="input-icon">
					   <input type="text"  name="custName" id="custName"  value="${queryBean.custName}" placeholder="商户名称">
						<i class="icon-leaf blue"></i>
						</span>
					</td>
					<td class="td-left" >邮箱地址：</td>
								<td class="td-right" > 
					  <span class="input-icon">
			             <input type="text"  name="email" id="email"  value="${queryBean.email }" placeholder="邮箱地址">
							<i class="icon-leaf blue"></i>
						</span>
						</td> 
						
						
						
					</tr>
					<tr>
						<td colspan="4" align="center">
							<gyzbadmin:function url="<%=PaymentManagerPath.BASE+PaymentManagerPath.ACCTTOPAYBUSS %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							
							<button  class="btn btn-purple btn-sm btn-margin clearAcctSevenBuss" >
								清空
								<i class=" icon-move icon-on-right bigger-110"></i>
							</button>	
						</td>
					  </tr>
					</table>
					</form>
				</div>
				<!-- 持卡人信息-->
				<div >
					<div class="list-table-header">商户账户列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>代付账号</th>
								<th>商户名称</th>
								<th>手机号码</th>
								<th>邮箱</th>
								<th>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${acctSevenBussList}" var="acctBuss">
						<tr class="acctBuss">
							<td>${acctBuss.email}</td>
							<td>${acctBuss.custName}</td>
							<td>${acctBuss.mobile}</td>
							<td>${acctBuss.email}</td>
							
							
							<td>
							
							  <button type="button"  data-toggle='modal' data-target="#updateAcctSevenBussModal" class="btn btn-primary btn-xs qifenqian_view_tc updateAcctSevenBuss" >编辑</button> 
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty acctSevenBussList}">
							<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty acctSevenBussList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
		<div class="modal fade" id="updateAcctSevenBussModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">编辑代付账户信息</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="20%">代付账号</td>
									<td class="td-right" width="80%">
									    <input type="hidden" id="cust_Id" name="cust_Id">
										<input type="text" id="account_1" name="account_1" readonly="readonly" clasS="width-80">
									</td>
								</tr>
								<tr>
									<td class="td-left">商户名称</td>
									<td class="td-right">
										<input type="text" id="acctName" name="acctName" readonly="readonly" clasS="width-80">
									</td>
								
								</tr>
								<tr>
								
									<td class="td-left">手机号码</td>
									<td class="td-right">
										<input type="text" id="phone_1" name="phone_1" readonly="readonly" maxlength="200" clasS="width-80">
									</td>
									</tr>
									<tr>
									<td class="td-left">邮箱</td>
									<td class="td-right">
										<input type="text" id="email_1" name="email_1" readonly="readonly" maxlength="200" clasS="width-80">
									</td>
									
									
								  </tr>
								<tr>
								
								   <td class="td-left" width="30%">代付账户余额</td>
									<td class="td-right">
										<input type="text" id="payment_Balance" name="payment_Balance" readonly="readonly" maxlength="200" clasS="width-80">
									</td>
									</tr>
								
								<tr>
								
									<td class="td-left">状态</td>
									<td class="td-right">
										<select name="status" id="status" clasS="width-80">
										  <option  value="NORMAL">- 可用 -</option>
										  <option  value="FREEZE">- 冻结 -</option>
										  <option  value="CANCEL">- 注销 -</option>
										</select>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary updateAcctSevenBussBtn">确定</button>
				            </div>
			      		</div>
			         </div>
			      </div><!-- /.modal-content -->
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
	function loadAcctSevenBuss(){
		$(".search-table #status").val($(".search-table #statusTemp").val());
	}
	
	$(function(){
		var acctBussListJson = ${acctSevenBussList};
		var acctBussTrList = $('tr.acctBuss');
		$.each(acctBussListJson, function(idx, obj){
			$.data(acctBussTrList[idx], 'acctBuss', obj);
		});
		
		$(".clearAcctSevenBuss").click(function(){
			$(':input','#merchantForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
	  $(".updateAcctSevenBuss").click(function(){
		 var acctBuss = $.data($(this).parent().parent()[0],"acctBuss");
		 var custId=acctBuss.custId;
		
		 $.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.GETPAYMENTBALANCE %>',
				data:
				{
					"custId" :custId
				},
				dataType:"json",
				success:function(data){
					if(data.result=="SUCCESS"){
					$("#status option[value='"+data.status+"']").removeAttr("selected");//根据值去除选中状态  
					$("#status option[value='"+data.status+"']").attr("selected","selected");
					$("#updateAcctSevenBussModal #payment_Balance").val(data.useBalance);
					
					}else{
						$.gyzbadmin.alertFailure("获取余额失败！"+data.message);
					};
				}
			}); 
		    $('#updateAcctSevenBussModal').on('show.bs.modal', function () {
	    	$("#updateAcctSevenBussModal #account_1").val(acctBuss.email);
	    	$("#updateAcctSevenBussModal #acctName").val(acctBuss.custName);
	    	$("#updateAcctSevenBussModal #phone_1").val(acctBuss.mobile);
	    	$("#updateAcctSevenBussModal #email_1").val(acctBuss.email); 
	    	$("#updateAcctSevenBussModal #cust_Id").val(acctBuss.custId);
	        })
		 $('#updateAcctSevenBussModal').on('hide.bs.modal', function () {
			$("#updateAcctSevenBussModal #merchantCode").val('');
    	    $("#updateAcctSevenBussModal #acctName").val('');
    	    $("#updateAcctSevenBussModal #acctId").val('');
    	    $("#updateAcctSevenBussModal #status").val('');
    	   
	     })
	  });
	  
	// 修改
	$('.updateAcctSevenBussBtn').click(function(){
		var cust_Id = $("#updateAcctSevenBussModal #cust_Id").val();
		var acctName = $("#updateAcctSevenBussModal #acctName").val();
		if(kong.test(cust_Id)){
			$.gyzbadmin.alertFailure("商户七分钱账号Id不能为空");
			return false;
		}
		if(kong.test(acctName)){
			$.gyzbadmin.alertFailure("商户七分钱账号名称不能为空");
			return false;
		}
		
		var status = $("#updateAcctSevenBussModal #status").val();
		if(kong.test(status)){
			$.gyzbadmin.alertFailure("请选择账户状态");
			return false;
		}
		// 保存修改
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE+PaymentManagerPath.UPDATEPAYMENTSTATUS%>', {
				'custId':cust_Id,
				'status':status,
				'acctName' :acctName
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#updateAcctSevenBussModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure('修改失败:' + data.message);
				}
			}, 'json'
		);
	});
});

</script>
</body>
</html>