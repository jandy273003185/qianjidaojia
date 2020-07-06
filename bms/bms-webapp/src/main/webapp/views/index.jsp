<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<style type="text/css">
	body,ul,li{margin:0;padding:0; list-style:none;}
	.bg img{width:100%;height:100%; position:absolute; z-index:-999;}
	.logo{clear:both;width:196px;height:113px; margin:15% auto 3% auto; background:url(<%=request.getContextPath() %>/static/images/logo_bg.png) no-repeat}
	ul.log_tex{width:70%;margin:0 auto; position:relative;height:300px;}
	ul.log_tex li{width:90%;height:50px;float:left; background:#fff;margin:10px 0;border-radius: 5px;margin-left:5%;transition-duration:.5s;}
	ul.log_tex li:hover{width:100%;height:50px;float:left; background:#fff;margin:10px 0;border-radius: 5px;}
	ul.log_tex li div{float:left;width:18%}
	ul.log_tex li div i.log-icon{float:left;width:30px;height:32px;margin:9px 30%;background: url(<%=request.getContextPath() %>/static/images/logo_bg.png) -200px 0; }
	ul.log_tex li div i.log-icon2{float:left;width:30px;height:32px;margin:9px 30%;background: url(<%=request.getContextPath() %>/static/images/logo_bg.png) -237px 0; }
	ul.log_tex li .wid300{float:left;width:80%;height:43px;margin-top:3px; border:none;color:#999; font-size:18px}
	ul.log_tex li .btnlog{width:100%;height:50px; background:#8f005c; border:none; color:#fff; cursor:pointer;border-radius: 5px; font-size:20px}
	ul.log_tex li .btnlog:hover{background:#8f005c;}
	ul.log_tex li .tips{display:none;color:#af2f23;padding-left:35px; background: url(<%=request.getContextPath() %>/static/images/logo_bg.png) -200px -37px no-repeat; }
	.copyright{width:100%;text-align:center; margin-top:18%; font-size:12px; clear:both;color:white; }
	.systemName{text-align:center; font-size:24px; font-weight:bold; color:#af2f23;}
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.platform.web.admin.login.LoginPath"%>
</head>

<body id="login-layout" onLoad="fnSetDefault();">
<!-- 公共弹出层 -->
<%@ include file="/include/alert.jsp"%>
<div class="bg">
<img src="<%=request.getContextPath() %>/static/images/bg.jpg" />
<div style="margin:0 auto;width:30%;">
      <div id="logBack02" style="float:left;width:100%;">
            <div class="logo"></div>
            <div class="systemName"><%=SpringUtils.getBean(SystemInfo.class).getSystemName().split(",")[0]%></div>
            <form action="" method="get">
               <ul class="log_tex">
                <li style=" background:none;height:20px;margin-top:40px;"><label class="tips">您输入的账号不存在!</label></li>
                <li><div><i class="log-icon"></i></div><input name="userCode"  id="userCode"  type="text" class="wid300" placeholder="请输入账号"></li>
                <li><div><i class="log-icon2"></i></div><input name="password" type="password" class="wid300" placeholder="请输入密码"></li>
                <li><button name="" type="button" value="登录"  class="btnlog" onclick="javascript:login();">登录</button></li>
               </ul>
               </form>
            <div class="copyright">版权所有 © 2016 深圳市国银证保信息技术有限公司 </div>
          </div>
        </div>
    </div>
<script type="text/javascript">
		jQuery(function(){
			$(document).keyup(function(e) {  
			    // 回车键事件  
			       if(e.which == 13) {  
			    	   login();
			       }  
			   });  
		});  
		
		/** 登陆*/
		function login() {
			
			var userCode = $('#logBack02 input[name=userCode]').val().trim();
			setCookie('web_user_name',userCode); 
			
			if(kong.test(userCode)) {
				$.gyzbadmin.alertFailure('用户编号不可为空');
				return;
			}
			var password = $('#logBack02 input[name=password]').val().trim();
			
			if(kong.test(password)){
				$.gyzbadmin.alertFailure('登陆密码不可为空');
				return;
			}
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=LoginPath.LOGIN %>', {
					'userCode' 	: userCode,
					'password'	: password
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						window.location.href = window.Constants.ContextPath + '<%=LoginPath.MAIN %>';
					} else {
						$.gyzbadmin.alertFailure('登陆失败:' + data.message);
					}
				}, 'json'
			);
	  };
			
	  function setCookie(key, value)
	  {
	        var date = new Date();
	        date.setTime(date.getTime() + 365 * 24 * 3600 * 1000);
	        var cookieInfo = key + "=" + escape(value) + "; expires=" + date.toGMTString(); 
	        document.cookie = cookieInfo;
	        
	  }
	  
	  function getCookie(key)
	  {
	        var result = document.cookie.match(new RegExp(key + "=([^;]*)"));
	        return result != null ? unescape(result[1]) : null;
	  }

	  function fnSetDefault(){
		    var temp = getCookie('web_user_name');
		  	if(temp!=null && temp!='' && 'web_user_name'!=temp)
		  	{
		  		document.getElementById("userCode").value=temp;
		  	};
		  	if(document.getElementById("userCode").value==""){
		  		document.getElementById("userCode").focus();
		  	}else{
		  		document.getElementById("password").focus();
		  	}
	  }
</script>
</body>
</html>