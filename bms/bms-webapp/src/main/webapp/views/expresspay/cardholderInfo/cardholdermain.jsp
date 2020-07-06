<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.expresspay.cardholderInfo.controller.CardholderPath"%>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>持卡人信息查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

$(function(){
	
	$(".clearCardholder").click(function(){
		$(".search-table #mobile").val('');
		$(".search-table #cardNo").val('');
	});
	
	var accountListJson = ${cardholderList};
	var accountTrList = $('tr.cardholder');
	$.each(accountListJson, function(idx, obj){
		$.data(accountTrList[idx], 'cardholder', obj);
	});
	
	$(".updateJgkjCard").click(function(){
		var cardholder = $.data($(this).parent().parent()[0], 'cardholder');
		
		$('#updateJgkjCardModal').on('show.bs.modal', function () {
			$("#updateJgkjCardModal #custId").val(cardholder.custId);
			$("#updateJgkjCardModal #cardNo").val(cardholder.cardNo);
			$("#updateJgkjCardModal #name").val(cardholder.name);
			$("#updateJgkjCardModal #idType").val(cardholder.idType);
			$("#updateJgkjCardModal #idCode").val(cardholder.idCode);
			$("#updateJgkjCardModal #oldMobile").val(cardholder.mobile);
			$("#updateJgkjCardModal #mobile").val(cardholder.mobile);
			$("#updateJgkjCardModal #email").val(cardholder.email);
			$("#updateJgkjCardModal #birthday").val(cardholder.birthday);
			$("#updateJgkjCardModal #addr").val(cardholder.addr);
			$("#updateJgkjCardModal #post").val(cardholder.post);
		});
		$('#updateJgkjCardModal').on('hide.bs.modal', function () {
			
			$("#updateJgkjCardModal #custId").val('');
			$("#updateJgkjCardModal #cardNo").val('');
			$("#updateJgkjCardModal #name").val('');
			$("#updateJgkjCardModal #idType").val('');
			$("#updateJgkjCardModal #idCode").val('');
			$("#updateJgkjCardModal #mobile").val('');
			$("#updateJgkjCardModal #email").val('');
			$("#updateJgkjCardModal #birthday").val('');
			$("#updateJgkjCardModal #addr").val('');
			$("#updateJgkjCardModal #post").val('');
		});
	});
	
	$('.updateJgkjCardBtn').click(function(){
		// 客户编号
		var custId = $('#updateJgkjCardModal #custId').val();
		if(kong.test(custId)) {
			$.gyzbadmin.alertFailure('客户编号不可为空');
			return;
		}
		// 交广科技卡号
		var cardNo = $('#updateJgkjCardModal #cardNo').val();
		if(kong.test(cardNo)) {
			$.gyzbadmin.alertFailure('交广科技账号不可为空');
			return;
		}
		// 姓名
		var name = $('#updateJgkjCardModal #name').val()
		if(kong.test(name)) {
			$.gyzbadmin.alertFailure('真实姓名不可为空');
			return;
		}
		// 证件类型
		var idType = $('#updateJgkjCardModal #idType').val();
		if(kong.test(idType)) {
			$.gyzbadmin.alertFailure('证件类型不可为空');
			return;
		}
		// 证件号码
		var idCode = $('#updateJgkjCardModal #idCode').val();
		if(kong.test(idCode)) {
			$.gyzbadmin.alertFailure('证件号码不可为空');
			return;
		}
		// 原手机号码
		var oldMobile = $('#updateJgkjCardModal #oldMobile').val();
		if(kong.test(oldMobile)) {
			$.gyzbadmin.alertFailure('原手机号码不可为空');
			return;
		}else if(!isPhoneNo($('#updateJgkjCardModal #oldMobile')[0])&&!isSetPhone($('#updateJgkjCardModal #oldMobile')[0])){
			$.gyzbadmin.alertFailure('填写11位正确的原手机号码');
			return;
		}
		// 手机号码
		var mobile = $('#updateJgkjCardModal #mobile').val();
		if(kong.test(mobile)) {
			$.gyzbadmin.alertFailure('手机号码不可为空');
			return;
		}else if(!isPhoneNo($('#updateJgkjCardModal #mobile')[0])&&!isSetPhone($('#updateJgkjCardModal #mobile')[0])){
			$.gyzbadmin.alertFailure('填写11位正确手机号码');
			return;
		}
		//邮箱
		var email = $("#updateJgkjCardModal #email").val()
		if(!kong.test(email)){
			if(!verifyEmailAddress($("#updateJgkjCardModal #email")[0])){
				$.gyzbadmin.alertFailure('邮箱格式不对,可使用字母、数字、下划线');
				return false;
			}
		}
		var birthday = $('#updateJgkjCardModal #birthday').val();
		if(birthday != ''){
			if(birthday.length != 8){
				$.gyzbadmin.alertFailure('生日格式不对,应为8位整数,如20150101');
				return false;
			}
		}
		
		var post = $('#updateJgkjCardModal #post').val();
		if(!kong.test(post)){
			if(!checkPost(post)){
				$.gyzbadmin.alertFailure('邮编格式不对,必须为非0开头的6位整数');
				return false;
			}
		}
		var addr = $('#updateJgkjCardModal #addr').val();
		  // 保存修改
		  $.post(window.Constants.ContextPath +'<%=CardholderPath.BASE+CardholderPath.EDIT%>',{
			'custId' 	: custId,
			'cardNo'	: cardNo,
			'name'		: name,
			'idType'	: idType,
			'idCode'	: idCode,
			'mobile'	: mobile,
			'oldMobile'	: oldMobile,
			'email'		: email,
			'birthday'	: birthday,
			'addr'		: addr,
			'post'		: post
			},function(data){
				if(data.result == 'SUCCESS'){
					$('#updateJgkjCardModal').modal('hide');
					$.gyzbadmin.alertSuccess('修改成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
		 );
	});
});

</script>
<body>
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
				<form action='<c:url value="<%=CardholderPath.BASE+CardholderPath.CARDHOLDERMAIN %>"/>' method="post">
					<table class="search-table">
					   <tr>
						<td class="td-left">电话号码：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="mobile" name="mobile" value="${queryBean.mobile}" maxlength="55" >
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="emailLab"></label>
							</span>
						</td>
						<td class="td-left">交广卡号：</td>
						<td class="td-right"> 
							<span class="input-icon">
									<input type="text" id="cardNo" name="cardNo" value="${queryBean.cardNo}" maxlength="55" >
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="emailLab"></label>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center" >
							<gyzbadmin:function url="<%=CardholderPath.BASE+CardholderPath.CARDHOLDERMAIN %>">
							<button type="submit" class="btn btn-purple btn-sm">
								查询
								<i class="icon-search icon-on-right bigger-110"></i>
							</button> 
							</gyzbadmin:function>
							<button class="btn btn-purple btn-sm btn-margin clearCardholder" >
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
					<div class="list-table-header">持卡人信息</div>
						<table id="sample-table-2" class="list-table">
						<thead>
							<tr>
								<th>客户号</th>
								<th>卡号</th>
								<th>姓名</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>电话号码</th>
								<th>邮箱</th>
								<th>生日</th>
								<th>地址</th>
								<th>邮编</th>
								<th>操作</th>
							</tr>
						</thead>
					<tbody>
					<c:forEach items="${cardholderList}" var="cardholder">
						<tr class="cardholder">
							<td>${cardholder.custId }</td>
							<td>${cardholder.cardNo }</td>
							<td>${cardholder.name }</td>
							<td>
								<c:if test="${cardholder.idType == '10'}">身份证</c:if>
								<c:if test="${cardholder.idType == '11'}">职工编号</c:if>
								<c:if test="${cardholder.idType == '09'}">客户号</c:if>
								<c:if test="${cardholder.idType == '01'}">香港身份证</c:if>
								<c:if test="${cardholder.idType == '02'}">护照</c:if>
								<c:if test="${cardholder.idType == '20'}">其它</c:if>
							</td>
							<td>${cardholder.idCode }</td>
							<td>${cardholder.mobile}</td>
							<td>${cardholder.email }</td>
							<td>${cardholder.birthday }</td>
							<td>${cardholder.addr }</td>
							<td>${cardholder.post}</td>
							<td> 	
								<gyzbadmin:function url="<%=CardholderPath.BASE + CardholderPath.EDIT%>">
									<a href="#updateJgkjCardModal"  data-toggle='modal' class="tooltip-success updateJgkjCard" data-rel="tooltip" title="Edit">
										<span class="green">
											<i class="icon-edit bigger-120"></i>
										</span>
									</a>
								</gyzbadmin:function>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty cardholderList}">
							<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				  </table>
				</div>
			</div><!-- /.page-content -->
			
		<div class="modal fade" id="updateJgkjCardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">交广科技卡信息修改</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="40%">客户号</td>
							<td class="td-right" width="60%">
								<input type="text" id="custId" name="custId" disabled="disabled"  style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left">交广科技卡号</td>
							<td class="td-right">
								<input type="text" id="cardNo" name="cardNo" disabled="disabled" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">原手机号码</td>
							<td class="td-right">
								<input type="text" id="oldMobile" name="oldMobile" disabled="disabled" style="width:80%">
							</td>
						</tr>
						
						<tr>
							<td class="td-left">手机号码</td>
							<td class="td-right">
								<input type="text" id="mobile" name="mobile"  style="width:80%">
							</td>
						</tr>
						
						<tr>
							<td class="td-left">证件类型</td>
							<td class="td-right">
								<select id="idType" name="idType"  disabled="disabled" >
									<option value="10">身份证</option>
									<option value="11">职工编号</option>
									<option value="09">客户号</option>
									<option value="01">香港身份证</option>
									<option value="02">护照</option>
									<option value="20">其他</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">证件号码</td>
							<td class="td-right">
								<input type="text" id="idCode" name="idCode" disabled="disabled" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">姓名</td>
							<td class="td-right">
								<input type="text" id="name" name="name" style="width:80%" >
							</td>
						</tr>
						<tr>
							<td class="td-left">生日</td>
							<td class="td-right">
								<input type="text" name="birthday" id="birthday"  style="width:80%" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
							</td>
						</tr>
						<tr>
							<td class="td-left">邮箱</td>
							<td class="td-right">
								<input type="text" id="email" name="email" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">地址</td>
							<td class="td-right">
								<input type="text" id="addr" name="addr" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">邮编</td>
							<td class="td-right">
								<input type="text" id="post" name="post" style="width:80%">
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateJgkjCardBtn">提交</button>
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
</body>
</html>