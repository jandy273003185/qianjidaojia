<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="com.qifenqian.bms.meeting.luckdraw.LuckDrawPath" %>
<script src='<c:url value="/static/js/angular.min.js"/>'></script>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>2016年七分钱抽奖</title>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/reset.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/style.css"/>'>
</head>
<body>

	<div class="warp" ng-app="ngApp" ng-controller="drawCtrl">
		<!--  <div style="color: red;font-size: 100px;float:left;width: 30%; height: 100%; margin-top: 200px;z-index: 9999">
			<div style="color: blue;font-size: 100px;margin-top: 50px;">中奖列表</div>
			<div style="color: red;font-size: 20px">
				<div ng-repeat="win in winList" align="left" style="margin-left: 100px;">{{$index+1}}&nbsp;&nbsp;&nbsp;{{win.mobile}}</div>
			</div>
		</div>-->
	
		<div class="cjbg cjbg_01"></div>
		<div class="cjbg cjbg_02"></div>
		<div class="cjbg cjbg_03">
		<div class="cj-level">
			<h4 class='level'>正在抽取<span>{{prize.prizeName}}</span></h4>
			<div class="zj-phone">
				<p>{{cust.mobile}}</p>
			</div>
			<div class="begin" ng-show="!isScrolling" ng-click="start()">
				<img src='<c:url value="/static/meeting/images/ks-btn.png"/>' alt="开始">
			</div>
			<div class="begin" style="margin-top: 60px;" ng-show="isScrolling" ng-click="end()">
				<img src='<c:url value="/static/meeting/images/tz-btn.png"/>' alt="结束">
			</div>
		</div>
		</div>
	    <div class="cjbg cjbg_04"></div>
		<div class="cpname"><img src='<c:url value="/static/meeting/images/cp-name.png"/>' alt="2016年"></div>
	</div>

	<script type="text/javascript">
		var app = angular.module('ngApp', []);
		// 初始
		app.controller('drawCtrl', ['$scope','$document', function($scope,$document) {
			// 中奖列表
			$scope.winList = [];
			// 奖项信息
		    $scope.prize = <c:out value="${gyzb:toJSONString(prize)}" escapeXml="false"/>;
		    // 初始手机号
		    $scope.custList = <c:out value="${gyzb:toJSONString(custList)}" escapeXml="false"/>;
		    $scope.cust = {mobile:'137****8888'};
		    $scope.isScrolling = false;

		    $document.bind("keypress", function(event) {
	            if(event.keyCode == 13){
		            if($scope.isScrolling) {
		            	$scope.$apply($scope.end());
			        } else {
	            		$scope.start();
				    }
	            }
		    });
		    
		    $scope.start = function() {
		    	$scope.isScrolling = true;
		    	//每隔刷新
	            setInterval(function(){
		            if($scope.isScrolling) {
		            	$scope.$apply(function () {
		            		$scope.cust = $scope.custList[Math.floor(Math.random()*$scope.custList.length)];
		            	});
			        }
	            },50);
		    }
		    $scope.end = function() {
		    	$scope.isScrolling = false;
		    	$scope.$apply(function () {
		    		$scope.winList[$scope.winList.length] = $scope.cust;
            	});
		    }
		    $scope.back = function() {
		    	$.blockUI();
		    	window.history.back();
		    }
		}]);
	</script>
  </body>	
</html>