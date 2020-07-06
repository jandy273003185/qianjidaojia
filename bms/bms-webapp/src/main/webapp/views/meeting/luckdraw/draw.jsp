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
	
	<style type="text/css">
		.float-left {float:left;}
		.float-none {}
	</style>
</head>
<body>
	<!-- 公共弹出层 -->
	<%@ include file="/include/alert.jsp"%>

	<div class="warp" ng-app="ngApp" ng-controller="drawCtrl">
		<!--  <div style="color: red;font-size: 100px;float:left;width: 30%; height: 100%; margin-top: 200px;z-index: 9999">
			<div style="color: blue;font-size: 100px;margin-top: 50px;">中奖列表</div>
			<div style="color: red;font-size: 20px">
				<div ng-repeat="win in winList" align="left" style="margin-left: 100px;">{{$index+1}}&nbsp;&nbsp;&nbsp;{{win.winCustPhone}}</div>
			</div>
		</div>-->
	
		<div class="cjbg cjbg_01"></div>
		<div class="cjbg cjbg_02"></div>
		<div class="cjbg cjbg_03">
		<div style="color: #fff;font-size: 100px; position:absolute; top:-140px; left:50%;margin-left:-150px;" align="center">
			<font ng-cloak>{{prizeWin.winAmount}}</font>
		</div>
		<div class="cj-level">
			<h4 class='level'>正在抽取<span ng-cloak class="ng-cloak">{{prize.prizeName}}&nbsp;&nbsp;&nbsp;已中{{prize.winNumber}}/共{{prize.quotaNumber}}</span></h4>
			<div class="zj-phone">
				<p ng-cloak class="ng-cloak">{{prizeWin.winCustPhone}}</p>
			</div>
			<div class="begin float-{{floatClass}}" ng-show="!isScrolling" ng-click="start()">
				<img src='<c:url value="/static/meeting/images/ks-btn.png"/>' alt="开始">
			</div>
			<div class="begin" ng-cloak ng-show="isShowConfrim" ng-click="confrim()" style="float: right">
				<img src='<c:url value="/static/meeting/images/qr-btn.png"/>' alt="确定">
			</div>
			<div class="begin" ng-cloak style="margin-top: 60px;" ng-show="isScrolling" ng-click="end()">
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
		    // 若是奖项信息为空，则说明全部已抽完,跳转至活动奖项列表页面
		    if(null == $scope.prize) {
		    	//$.gyzbadmin.alertFailure('活动奖项已全部抽奖完毕,自动跳到奖项列表页面！', null, function(){
		    		window.location.href = window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.PRIZE_LIST %>';
			    //});
			}
		    
		    // 客户号/中奖人
		    $scope.prizeWin = {winCustPhone:'***********',winAmount:''};
		    // 是否在滚动
		    $scope.isScrolling = false;
		    // 是否显示确定按钮
		    $scope.isShowConfrim = false;
		 	// 按钮样式
			$scope.floatClass = 'none';

			// 是否可以点击确定
			$scope.isConfrimBtnCanClick = true;

		    $document.bind("keypress", function(event) {
	            if(event.keyCode == 13){
		            if($scope.isScrolling) {
		            	$scope.$apply($scope.end());
			        } else {
	            		$scope.start();
				    }
	            }
		    });
		    // 开始，要校验是否抽奖完毕，抽奖完的话自动跳转下一个奖项
		    $scope.start = function() {
		    	// 是否可以点击确定
				$scope.isConfrimBtnCanClick = true;
			    if($scope.prize.winNumber >= $scope.prize.quotaNumber) {
			    	 // $.gyzbadmin.alertSuccess('该奖项已抽奖完毕，自动下一奖项！',null, );
		    		// 连接后台获取下一个待抽奖奖项
					$.post(window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.NEXT_PRIZE %>',{
							'activityId' : $scope.prize.activityId
							},function(data){
								if(data.result == 'SUCCESS'){
									// 
									$scope.$apply(function () {
								    	if(null == data.nextPrize) {
									    	// $.gyzbadmin.alertSuccess('活动奖项已全部抽奖完毕,自动跳到奖项列表页面！', null, );
								    		window.location.href = window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.PRIZE_LIST %>?activityId='+$scope.prize.activityId;
										}
								    	$scope.prize = data.nextPrize;
									});
								} else {
									$.gyzbadmin.alertFailure('获取下一奖项失败,自动刷新页面！'  + data.message, null, function(){
										window.location.href = window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.DRAW %>';
									});
								}
							},'json'
					);
			    	return false;
				}
		    	$scope.isScrolling = true;
		    	$scope.isShowConfrim = false;
		    	// 按钮样式
				$scope.floatClass = 'none';
		    	//每隔刷新
	            setInterval(function(){
		            if($scope.isScrolling) {
		            	$scope.$apply(function () {
		            		$scope.prizeWin = {winCustPhone: 
			            		'1' 
			            		+ Math.floor(Math.random()*10)+ ''
			            		+ Math.floor(Math.random()*10)+ '****'
			            		+ Math.floor(Math.random()*10)+ ''
			            		+ Math.floor(Math.random()*10)+ ''
			            		+ Math.floor(Math.random()*10)+ ''
			            		+ Math.floor(Math.random()*10)};
		            		
		            	});
			        }
	            },50);
		    }
		    $scope.end = function() {
		    	// 连接后台获取中奖人
				$.post(window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.LUCK_DRAW %>',{
						'prizeId' : $scope.prize.prizeId
						},function(data){
							if(data.result == 'SUCCESS'){
								$scope.$apply(function () {
							    	// 先停
						    		$scope.isScrolling = false;
						    		$scope.isShowConfrim = true;
						    		// 按钮样式
									$scope.floatClass = 'left';
									$scope.prizeWin = data.prizeWin;
								});
							} else {
								$.gyzbadmin.alertFailure('抽奖失败,自动刷新页面！' + data.message, null, function(){
									window.location.reload();
								});
							}
						},'json'
				);
		    }
		    $scope.confrim = function() {
			    if(!$scope.isConfrimBtnCanClick) {
				    return false;
				}
		    	$scope.isConfrimBtnCanClick = false;
		    	// 连接后台保存
				$.post(window.Constants.ContextPath + '<%=LuckDrawPath.BASE + LuckDrawPath.CONFRIM_DRAW %>',{
						'prizeId'   : $scope.prize.prizeId,
						'activityId': $scope.prize.activityId,
						'winAmount' : $scope.prizeWin.winAmount,
						'winCustId' : $scope.prizeWin.winCustId
						},function(data){
							if(data.result == 'SUCCESS'){
								// 刷新已中奖个数
								$scope.$apply(function () {
									$scope.prize.winNumber += 1;
		    						$scope.winList[$scope.winList.length] = $scope.prizeWin;
		    						$scope.isShowConfrim = false;
		    						// 按钮样式
		    						$scope.floatClass = 'none';
		    						$scope.isConfrimBtnCanClick = true;
								});
							} else {
								$.gyzbadmin.alertFailure('保存失败:' + data.message);
								$scope.isConfrimBtnCanClick = true;
							}
						},'json'
				);
            	
		    }
		    $scope.back = function() {
		    	$.blockUI();
		    	window.history.back();
		    }
		}]);
	</script>
  </body>	
</html>