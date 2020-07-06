<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="com.qifenqian.bms.meeting.luckdraw.LuckDrawPath" %>

<script src='<c:url value="/static/js/angular.min.js"/>'></script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>用户管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	</style>
</head>
<body style="background-color:#1d2024">
	<!-- 公共弹出层 -->
	<%@ include file="/include/alert.jsp"%>
	
	<div id="main-container" align="center" ng-app="ngApp" ng-controller="prizeListCtrl">
		<input type="hidden" ng-model="prizeId"/>
		<ul style="color: red;font-size: 100px" ng-cloak>
			<li ng-repeat="prize in prizeList">
				<a href="#" ng-click="draw(prize)">{{prize.prizeName}}&nbsp;&nbsp;&nbsp;(已中{{prize.winNumber}}/共{{prize.quotaNumber}})</a>
			</li>
		</ul>

	</div><!-- /.main-container -->

	<script type="text/javascript">
		var app = angular.module('ngApp', []);
		// 初始
		app.controller('prizeListCtrl', function($scope) {
		    $scope.prizeList = <c:out value="${gyzb:toJSONString(prizeList)}" escapeXml="false"/>;
		    $scope.draw = function($prize) {
		    	$.blockUI();
				$.gyzbadmin.postForm('<c:url value="<%=LuckDrawPath.BASE + LuckDrawPath.DRAW %>" />',{
						'prizeId' : $prize.prizeId
					}
				); 
		    }
		});
	</script>
  </body>	
</html>