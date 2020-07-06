<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>修改用户密码</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<script type="text/javascript">
 
 $(function(){
	 	
	$("#changePwdSbumit").click(function(){
		
		//原密码 
		var userPassword = $("#userPassword").val();
		if(kong.test(userPassword)){
			$(".updatePwdModel #userPasswordLab").text("请填写当前登录密码");
			$(".updatePwdModel #userPassword").focus();
			return false;
		}
		$(".updatePwdModel #userPasswordLab").text("");
		//新密码 
		var newPassword = $("#newPassword").val();
		if(kong.test(newPassword)){
			$(".updatePwdModel #newPasswordLab").text("请输入新密码");
			$(".updatePwdModel #newPassword").focus();
			return false;
		}
			$(".updatePwdModel #newPasswordLab").text("");
		if(!checkPwdPattern(newPassword)){
			$(".updatePwdModel #newPasswordLab").text("登录密码输入格式不正确，必须是6-20个英文字母、数字或符号，不能是纯数字"); 
			$(".updatePwdModel #newPassword").focus();
			return false;
		}
		$(".updatePwdModel #newPasswordLab").text("");
		//确认新密码 
		var confirmPassword = $("#confirmPassword").val();
		if(kong.test(confirmPassword)){
			$(".updatePwdModel #confirmPasswordLab").text("请输入确认密码");
			$(".updatePwdModel #confirmPassword").focus();
			return false;
		}
		$(".updatePwdModel #confirmPasswordLab").text("");
		
		if(newPassword!=confirmPassword){
			$(".updatePwdModel #confirmPasswordLab").text("两次输入的密码不一致");
			$(".updatePwdModel #confirmPassword").focus();
			return false;
		}
		$(".updatePwdModel #confirmPasswordLab").text("");
		
		if(userPassword == newPassword){
			$(".updatePwdModel #newPasswordLab").text("新登录密码必须与当前登录密码不一致");
			$(".updatePwdModel #newPassword").focus();
			return false;
		}
		$(".updatePwdModel #newPasswordLab").text("");
		
		var validateDate=false;
		$.blockUI();
		$.ajax({
			async:false,
			dataType:"json",
			url:window.Constants.ContextPath +'<%=LoginPath.UPDATEPWD%>',
	        data:{  userPassword:userPassword,
	        		newPassword:newPassword,
	        		confirmPassword:confirmPassword
	        	},
	        success:function(data){
	        	$.unblockUI();
	        	if(data.result=="userPasswordFail"){
	        		$(".updatePwdModel #userPasswordLab").text(data.message);
				}else if(data.result=="FAIL"){
					$.gyzbadmin.alertFailure(data.message);
				}else if(data.result=="SUCCESS"){
					$.gyzbadmin.alertSuccess(data.message,function(){},function(){
						$.post(window.Constants.ContextPath + '<%=LoginPath.LOGOUT %>', {
						}, function(data) {
							if(data.result == 'SUCCESS'){
								window.location.href = window.Constants.ContextPath + '<%=LoginPath.ROOT%>';
							} else {
								alert('返回登录页面失败:' + data.message);
							}
						}, 'json'
						);
					});
				}
			}
	    });
	});
}); 
</script>
<body>
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				<!-- 主内容 -->
			 <div class="page-content">
				<div class="updatePwdModel">
					<div class="list-table-header">修改登录密码</div>
					<div class="ar-tips">
						<form action='<c:url value="<%=LoginPath.UPDATEPWD %>"/>' method="post">
							<table class="search-table" style="font-size: 13px; line-height: 15px;">
								<tr style="line-height:25px;">
									<td style="text-align:left;" colspan="2">
										<span>温馨提示:</span>
											<br>
										<span>1.建议密码采用字母和数字混合，并且长度在6至20位之间。</span>
											<br>
										<span>2.如忘记登录密码，请联系后台管理员。</span>
									</td>
								</tr>
								 <tr>
									<td class="td-left" width="40%">当前用户账号:</td>
									<td class="td-right" width="60%">
											<span>${userCode}</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">当前登录密码:</td>
									<td class="td-right">
										<input type="password" name="userPassword" id="userPassword" placeholder="当前登录密码" style="width:35%" maxlength="30">
										<label id="userPasswordLab" style="font-size:12px; color:CC0000;"></label>
									</td>
									
								</tr>
								<tr>
									<td class="td-left">新的登录密码:</td>
									<td class="td-right">
										<input type="password" name="newPassword" id="newPassword" placeholder="新的登录密码" style="width:35%" maxlength="20" >
										<label id="newPasswordLab" style="font-size:12px; color:CC0000;"></label>
									</td>
									
								</tr>
								<tr>
									<td class="td-left">确认新的登录密码:</td>
									<td class="td-right">
										<input type="password" name="confirmPassword" id="confirmPassword" placeholder="确认新的登录密码" style="width:35%" maxlength="20">
										<label id="confirmPasswordLab" style="font-size:12px; color:CC0000;"></label>
									</td>
								</tr>
								<tr>
									<td class="combine" colspan="2"  align="center" >
										<input type="button" class="btn btn-purple btn-sm"  id="changePwdSbumit" value="确定修改"/>
										<input type="reset" class="btn btn-purple btn-sm btn-margin"  value="重置"/>
									</td>
								</tr>
							</table> 
						</form>
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
</body>
</html>