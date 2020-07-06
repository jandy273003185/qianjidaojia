<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.acctsevencust.AcctSevenCustPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>客户账号管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadStatus()">
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
				<form action='<c:url value="<%=AcctSevenCustPath.BASE+AcctSevenCustPath.SELECT %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">电话号码</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="mobile" name="mobile" maxlength="55" value="${queryBean.mobile }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">七分钱账号</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctId" name="acctId" maxlength="55" value="${queryBean.acctId }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">创建时间</td>
						<td class="td-right">
						    <input type="text"  name="createBeginTime" id="createBeginTime" value="${queryBean.createBeginTime}"readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" >
						    -
						    <input type="text" name="createEndTime" id="createEndTime" value="${queryBean.createEndTime}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
						</td>
					</tr>
					 <tr>
						<td class="td-left">客户姓名</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="acctName" name="acctName" maxlength="55" value="${queryBean.acctName }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">交广科技账号</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="jgkjCardNo" name="jgkjCardNo" maxlength="55" value="${queryBean.jgkjCardNo }">
									<i class="icon-leaf blue"></i>
							</span>
						</td>
						<td class="td-left">状态</td>
						<td class="td-right"> 
							<span class="input-icon">
								<input type="hidden" id="statusTemp" name="statusTemp" maxlength="55" value="${queryBean.status }">
							 	<select id="status" name="status" class="width-100">
									<option value="">- 请选择 -</option>
									<option value="NORMAL">可用 </option>
									<option value="FREEZE">冻结 </option>
									<option value="CANCEL">注销 </option>
								</select>
							</span>   
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<gyzbadmin:function url="<%=AcctSevenCustPath.BASE+AcctSevenCustPath.SELECT %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button  class="btn btn-purple btn-sm btn-margin clearAcctSevenCust" >
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
					<div class="list-table-header">客户账户列表</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>电话号码</th>
								<th>七分钱账号</th>
								<th>姓名</th>
								<th>交广科技账号</th>
								<th>科目编号</th>
								<th>余额</th>
								<th>余额方向</th>
								<th>冻结金额</th>
								<th>在途金额</th>
								<th>可用金额</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${acctCustList}" var="acctCust">
						<tr class="acctCust">
							<td>${acctCust.mobile }</td>
							<td>${acctCust.acctId }</td>
							<td>${acctCust.acctName }</td>
							<td>${acctCust.jgkjCardNo }</td>
							<td>${acctCust.subjectId }</td>
							<td>${acctCust.balance}</td>
							<td>
								<c:if test="${acctCust.balanceFlag == 'CREDIT' }">贷</c:if>
								<c:if test="${acctCust.balanceFlag == 'DEBIT' }">借</c:if>
							</td>
							<td>${acctCust.freezeAmt }</td>
							<td>${acctCust.onwayAmt }</td>
							<td>${acctCust.usableAmt }</td>
							<td>
								<c:if test="${acctCust.status == 'NORMAL'}">可用</c:if>
								<c:if test="${acctCust.status == 'FREEZE'}">冻结</c:if>
								<c:if test="${acctCust.status == 'CANCEL'}">注销</c:if>
							</td>
							<td>${acctCust.instDatetime }</td>
							<td> 	
								<gyzbadmin:function url="<%=AcctSevenCustPath.BASE + AcctSevenCustPath.EDIT%>">
									<a href="#updateAcctSevenCustModal"  data-toggle='modal' class="tooltip-success updateAcctSevenCust" data-rel="tooltip" title="Edit">
										<span class="green">
											<i class="icon-edit bigger-120"></i>
										</span>
									</a>
								</gyzbadmin:function>
								
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty acctCustList}">
							<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
				<!-- 分页 -->
				<c:if test="${not empty acctCustList}">
					<%@ include file="/include/page.jsp"%>
				</c:if>
			</div><!-- /.page-content -->
			
		<div class="modal fade" id="updateAcctSevenCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">客户账户信息修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		           		 <tr>
							<td class="td-left" width="30%">电话号码</td>
							<td class="td-right" width="70%">
									<input type="text" id="mobile" name="mobile" readonly="readonly" style="width:80%">
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="30%">七分钱账号</td>
							<td class="td-right" width="70%">
									<input type="text" id="acctId" name="acctId" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">名称</td>
							<td class="td-right">
									<input type="text" id="acctName" name="acctName" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">交广科技账号</td>
							<td class="td-right">
									<input type="hidden" id="custId" name="custId" readonly="readonly">
									<input type="text" id="jgkjCardNo" name="jgkjCardNo" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">余额</td>
							<td class="td-right">
									<input type="text" id="balance" name="balance" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">冻结余额</td>
							<td class="td-right">
									<input type="text" id="freezeAmt" name="freezeAmt" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">在途金额</td>
							<td class="td-right">
									<input type="text" id="onwayAmt" name="onwayAmt" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">可用金额</td>
							<td class="td-right">
									<input type="text" id="usableAmt" name="usableAmt" readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">状态</td>
							<td class="td-right">
							<input type="hidden" id="tempStatus" name="tempStatus">
								<select id="status" name="status" style="width:80%">
									<option value="NORMAL">-可用-</option>
									<option value="FREEZE">-冻结-</option>
								</select>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateAcctSevenCustBtn">提交</button>
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
	function loadStatus(){
		$(".search-table #status").val($(".search-table #statusTemp").val());
	}
	
	$(function(){
		var acctCustListJson = ${acctCustList};
		var acctCustTrList = $('tr.acctCust');
		$.each(acctCustListJson, function(idx, obj){
			$.data(acctCustTrList[idx], 'acctCust', obj);
		});
		
		$(".clearAcctSevenCust").click(function(){
			$(".search-table #mobile").val('');
			$(".search-table #acctId").val('');
			$(".search-table #status").val('');
			$(".search-table #createBeginTime").val('');
			$(".search-table #createEndTime").val('');
			$(".search-table #acctName").val('');
			$(".search-table #jgkjCardNo").val('');
		});
		
		
	/* 修改弹出层 */
	$(".updateAcctSevenCust").click(function(){
		var acctCust = $.data($(this).parent().parent()[0], 'acctCust');
		$('#updateAcctSevenCustModal').on('show.bs.modal', function () {
			// 赋值
			$("#updateAcctSevenCustModal #acctId").val(acctCust.acctId);
			$("#updateAcctSevenCustModal #acctName").val(acctCust.acctName);
			$("#updateAcctSevenCustModal #custId").val(acctCust.custId);
			$("#updateAcctSevenCustModal #mobile").val(acctCust.mobile);
			$("#updateAcctSevenCustModal #jgkjCardNo").val(acctCust.jgkjCardNo);
			$("#updateAcctSevenCustModal #balance").val(acctCust.balance);
			$("#updateAcctSevenCustModal #freezeAmt").val(acctCust.freezeAmt);
			$("#updateAcctSevenCustModal #onwayAmt").val(acctCust.onwayAmt);
			$("#updateAcctSevenCustModal #usableAmt").val(acctCust.usableAmt);
			$("#updateAcctSevenCustModal #status").val(acctCust.status);
			$("#updateAcctSevenCustModal #tempStatus").val(acctCust.status);
		});
		$('#updateAcctSevenCustModal').on('hide.bs.modal', function () {
			// 清除
			$("#updateAcctSevenCustModal #acctId").val('');
			$("#updateAcctSevenCustModal #acctName").val('');
			$("#updateAcctSevenCustModal #custId").val('');
			$("#updateAcctSevenCustModal #mobile").val('');
			$("#updateAcctSevenCustModal #jgkjCardNo").val('');
			$("#updateAcctSevenCustModal #balance").val('');
			$("#updateAcctSevenCustModal #freezeAmt").val('');
			$("#updateAcctSevenCustModal #onwayAmt").val('');
			$("#updateAcctSevenCustModal #usableAmt").val('');
			$("#updateAcctSevenCustModal #status").val('');
			$("#updateAcctSevenCustModal #tempStatus").val('');
		});
	});
	
	// 修改
	$('.updateAcctSevenCustBtn').click(function(){
		// 客户编号
		var custId = $('#updateAcctSevenCustModal #custId').val();
		if(kong.test(custId)) {
			$.gyzbadmin.alertFailure('客户号不可为空');
			return;
		}
		// 七分钱账号
		var acctId = $('#updateAcctSevenCustModal #acctId').val();
		if(kong.test(acctId)) {
			$.gyzbadmin.alertFailure('账号不可为空');
			return;
		}
		
		var status = $('#updateAcctSevenCustModal #status').val();
		if(kong.test(status)) {
			$.gyzbadmin.alertFailure('状态不可为空');
			return;
		}
		var tempStatus = $('#updateAcctSevenCustModal #tempStatus').val();
		if(tempStatus==status){
			if(status=='FREEZE'){
				$.gyzbadmin.alertFailure('账号已冻结');
				return;
			}
			if(status=='NORMAL'){
				$.gyzbadmin.alertFailure('账户已是正常状态');
				return;
			}
		}
		// 保存修改
		$.blockUI();
		$.post(window.Constants.ContextPath + '<%=AcctSevenCustPath.BASE + AcctSevenCustPath.EDIT %>', {
				'acctId'		: acctId,
				'custId'		: custId,
				'status'		: status
			}, function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#updateAcctSevenCustModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message, null, function(){
						window.location.reload();
					});
				}
			}, 'json'
		);
	});
});

</script>
</body>
</html>