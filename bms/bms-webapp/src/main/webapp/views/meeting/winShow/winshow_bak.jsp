<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.meeting.winShow.controller.WinShowPath"%>

<head>
	<meta charset="UTF-8">
	<title>2016年七分钱中奖名单</title>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/reset.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/static/meeting/css/style.css"/>'>
</head>


<body style="overflow: hidden;">
<div class="warp">
<div class="shadow" id="shadow"></div>
   <div class="cjbg kqbg" >
		<div class="cj-title">
			<h1>2016年七分钱网络科技有限公司</h1>
            <div class="huodong">
				<p>年会抽奖活动</p>
			</div>
		</div>
		<div class="zj-bg">
			<h2 class="firstH2"></h2>
			<div class="zj-people">
				<h3>中奖用户<img src='<c:url value="/static/meeting/images/11_03.png"/>' alt="中奖金额" /></h3>
				<div class="dowebok">
					<ul class="zjphone">
						
						
                    </ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script src='<c:url value="/static/js/jquery.liMarquee.js"/>'></script>
<script>
$(function(){
	
	$(".zj-bg").css("display","none"); 
	$(".shadow").css("display","none"); 
	
	
	   document.onkeydown=function(event){
	        var e = event || window.event || arguments.callee.caller.arguments[0];
	         if(e.keyCode == 49 || e.keyCode == 97){ // 按 1
	        	 $(".zj-bg").css("display","block"); 
	        	 $(".shadow").css("display","block"); 
	        	 
	        	 var $dowebok = $('.dowebok').liMarquee({
	      				direction: 'up',
	      				scrollamount: 100,   //滚动速度
	      				runshort: true     //内容未超过高度不滚动
	      			});

	        	 $.post( window.Constants.ContextPath + '<%=WinShowPath.BASE+WinShowPath.WININFO %>',{
	        		 'dataPath': 'meeting.redPacket.show.firstPrize'
	        	 },function(data){
	        		
	        	 //获取缓存数据
	        		var winLists =  $.data($(".firstH2")[0],"wins");
	        		var nowLists = data.winList;
	        		var appendWin = new Array;
	        		 if (typeof(winLists) != "undefined")
	        		{
	        			for(var i=0;i<nowLists.length;i++){
	        				var strA = nowLists[i];
	        				var count = 0;
	        				for(var j=0;j<winLists.length;j++){
	        					var strB = winLists[j];
	        					
	        					if(strA.winId == strB.winId){
	        						count ++;
	        					}
	        				}
	        				if(count == 0){
	        					appendWin.push(nowLists[i]);
	        				}
	        			}
	        		}else{
	        			appendWin = data.winList;
	        		}   
	        		  
	        		 $(".firstH2").text("一等奖中奖名单("+data.countWin+"人)");
	        		 
	        		 if(appendWin.length>0){
	        			 $.each(appendWin,function(key,val){
	        				 $(".zjphone")[0].innerHTML += '<li>'+key+'|'+val.winCustPhone.substring(3)+'</li>';
		        		 });
	        		 }
        			
	        	
	        	 //缓存数据
	        		 $.data($(".firstH2")[0],"wins",data.winList); 
	        		 
	        		
	        		 
	        		 	
	        	 },'json')
	        	
	        	
	     
	         setInterval(function(){
        		 $.post( window.Constants.ContextPath + '<%=WinShowPath.BASE+WinShowPath.WININFO %>',{
    	        		 'dataPath': 'meeting.redPacket.show.firstPrize'
    	        	 },function(data){
    	        		 
    	        			//获取缓存数据
    		        		var winLists =  $.data($(".firstH2")[0],"wins");
    		        		var nowLists = data.winList;
    		        		var appendWin = new Array;
    		        		 if (typeof(winLists) != "undefined")
    		        		{
    		        			for(var i=0;i<nowLists.length;i++){
    		        				var strA = nowLists[i];
    		        				var count = 0;
    		        				for(var j=0;j<winLists.length;j++){
    		        					var strB = winLists[j];
    		        					
    		        					if(strA.winId == strB.winId){
    		        						count ++;
    		        					}
    		        				}
    		        				if(count == 0){
    		        					appendWin.push(nowLists[i]);
    		        				}
    		        			}
    		        		}else{
    		        			appendWin = data.winList;
    		        		}   
    		             $.data($(".firstH2")[0],"wins",data.winList);
    	        		 $(".firstH2").text("一等奖中奖名单("+data.countWin+"人)");
    		             /* $(".zjphone").html(''); */
    		             if(appendWin.length > 0){
    		            	 $.each(appendWin,function(key,val){
    		            		 	alert(key);
    		   	        			//$(".zjphone").append('<li>'+key+'</li>');
    		   	        			 $(".zjphone")[0].innerHTML += '<li>'+key+'|'+val.winCustPhone.substring(3)+'</li>'; 
    		   	        		 }); 
    		             }
        		 	},'json');
	        	 	},5000); 
	        	  
	        	
	           }
	         
	    }; 
	    
	    function  showWinInfo(){
	    	
	    }
	 
});
</script>

</body>
</html>