<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<script src='<c:url value="/static/js/angular.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.liMarquee.js"/>'></script>
<%@page import="com.qifenqian.bms.meeting.winShow.controller.WinShowPath"%>

<head>
	<meta charset="UTF-8">
	<title>2016年七分钱中奖名单</title>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/reset.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/style.css"/>'>
</head>


<body style="overflow: hidden;">
<div class="warp" ng-app="ngApp" ng-controller="drawCtrl">
<div class="shadow" id="shadow" ng-show="isScrolling"></div>
   <div class="cjbg kqbg" >
		<div class="cj-title">
			<h1>2016年七分钱网络科技有限公司</h1>
            <div class="huodong">
				<p>年会抽奖活动</p>
			</div>
		</div>
		<div class="zj-bg" ng-show="isScrolling">
			<h2 class="firstH2">{{scollTitle}}</h2>
			<div class="zj-people">
				<h3>中奖用户<img src='<c:url value="/static/meeting/images/{{imgpath}}"/>' alt="中奖金额" /></h3>
				<div class="dowebok">
					<ul id="zjphone" class="zjphone">
						<li ng-repeat="win in nowList">
							{{win.winCustPhone}}
						</li>
                    </ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
var app = angular.module('ngApp', []);
// 初始
app.controller('drawCtrl', ['$scope','$document', function($scope,$document) {
	// 当前
    $scope.nowList = [];
    // 追加
    $scope.appendList = [];
 	// 是否显示滚动信息
	$scope.isScrolling = false;
    $scope.scollTitle = '';
    $scope.path = '';
    $scope.imgpath = '';
    $scope.timer = null;
   
    $document.bind("keypress", function(event) {
        if(event.keyCode == 48 || event.keyCode == 96){
        	$scope.clear();
        	$scope.isScrolling = false;
        } else if(event.keyCode == 49 || event.keyCode == 97){
        	$scope.clear();
        	$scope.imgpath = "11_03.png"
        	
        	$scope.roll();
        	$scope.isScrolling = true;
        	$scope.path = 'meeting.redPacket.show.firstPrize';
        	$scope.flushInterval();
        } else if(event.keyCode == 50 || event.keyCode == 98){
        	$scope.clear();
        	$scope.imgpath = "22_03.png"
        	$scope.roll();
        	
        	$scope.isScrolling = true;
        	$scope.path = 'meeting.redPacket.show.secondPrize';
        	$scope.flushInterval();
        } else if(event.keyCode == 51 || event.keyCode == 99){
        	$scope.clear();
        	$scope.roll();
        	$scope.imgpath = "33.png"
        	$scope.isScrolling = true;
        	$scope.path = 'meeting.redPacket.show.thirdPrize';
        	$scope.flushInterval();
        }else if(event.keyCode == 52 || event.keyCode == 100){
        	$scope.clear();
        	window.location.href= window.Constants.ContextPath + '<%=WinShowPath.BASE + WinShowPath.MAIN%>';
        }  
        else {
        	$scope.clear();
        }
    });

    $scope.clear = function() {
    	$scope.$apply(function () {
    		clearInterval($scope.timer);
    		// 是否显示滚动信息
    		$scope.isScrolling = false;
	    	// 当前
	        $scope.nowList = [];
	        // 追加
	        $scope.appendList = [];
	        $scope.scollTitle = '';
	      
    	});
    }

    $scope.roll = function(){
    	$('.dowebok').liMarquee({
    		direction: 'up',
    		scrollamount: 100,   //滚动速度
    		runshort: true     //内容未超过高度不滚动
    	});
    } 
    $scope.flushInterval = function(){
    	$scope.flushSingle();
    	$scope.timer = setInterval(function(){
    		if($scope.isScrolling) {
    			$scope.flushSingle();
    		}
        },5000);
	}

    $scope.flushSingle = function(){
   		// 获取新的中奖人员
   		$.post(window.Constants.ContextPath + '<%=WinShowPath.BASE + WinShowPath.WININFO %>',{
   				'dataPath': $scope.path
   			},function(data){
   				if(data.result == 'SUCCESS'){
   					$scope.$apply(function () {
   	   					$scope.scollTitle = data.scollTitle;
   	   					$scope.nowList = data.winList;
   	   				});
   				}else{
   					window.location.href= window.Constants.ContextPath + '<%=WinShowPath.BASE + WinShowPath.MAIN%>';
   				}
   				
   			},'json'
   		);
	}
}]);
</script>

</body>
</html>