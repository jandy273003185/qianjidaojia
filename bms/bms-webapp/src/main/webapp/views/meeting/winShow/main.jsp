<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.meeting.winShow.controller.WinShowPath"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>2016年七分钱抽奖</title>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/reset.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/style.css"/>'>
</head>
<script type="text/javascript">
		document.onkeydown=function(event){
	            var e = event || window.event || arguments.callee.caller.arguments[0];
	             if(e.keyCode == 48 || e.keyCode == 96){ // 按 0
	            	 window.location.href = window.Constants.ContextPath + '<%=WinShowPath.BASE+WinShowPath.WINSHOW %>';
	               }
	             
	        }; 
</script>

<body style="overflow: hidden;">
<div class="warp">
   <div class="cjbg">
		<div class="download-qifenpay">
			<img src='<c:url value="/static/meeting/images/font.png"/>' alt="扫描二维码下载七分钱app" />
			<h1>开始发红包啦！！手机将收到红包消息，<br />快去点击“立即领取”吧！</h1>
			<p>（温馨提示：请务必打开七分钱APP主页才能“抢红包”哦！）</p>
		</div>
	</div>
</div>
</body>
</html>