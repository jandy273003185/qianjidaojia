<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.merchant.settle.MerchantSettlePath"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<html>
<head>
    <meta charset="utf-8" />
    <title>商户报备列表</title>
    <meta name="keywords" content="七分钱后台管理系统" />
    <meta name="description" content="七分钱后台管理" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style type="text/css">
        table tr td{word-wrap:break-word;word-break:break-all;}
    </style>
</head>
<script type="text/javascript">

    $('.buttonSearch').click(function(){

        var form = $('#merchantForm');
        form.submit();
    });

    $(document).ready(function(){
        $('.clearMerchantSearch').click(function(){
            $('.search-table #merchantCode').val('');
            $('.search-table #custName').val('');
            $('.search-table #email').val('');
            $('.search-table #mobile').val('');
            $('.search-table #channelNo').val('');
            $('.search-table #detailStatus').val('');
            $('.search-table #startModifyTime').val('');
            $('.search-table #endModifyTime').val('');
        })
    })

    //子商户授权状态查询
    function getVerifiedAuthorize(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var custId = $(obj).parent().find('#custId_').val();

        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+'<%="/merchant/verified/authorizeMerchant" %>',
            data:
                {
                    "merchantCode" 	  : merchantCode,
                    "patchNo"         : patchNo,
                    "channelNo"       : channlCode,
                    "custId"          : custId,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    alert("子商户授权状态查询请求成功");
                }else{
                    if(null == data.message || "" ==data.message){
                    }else{
                        alert("子商户授权状态查询失败");
                        window.location.reload();
                    }
                }
            }
        });
    }
    //申请撤销
    function getVerifiedCancel(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var custId = $(obj).parent().find('#custId_').val();

        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+'<%="/merchant/verified/cancelMerchant" %>',
            data:
                {
                    "merchantCode" 	  : merchantCode,
                    "patchNo"         : patchNo,
                    "channelNo"       : channlCode,
                    "custId"          : custId,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    alert("实名认证申请撤销请求成功");
                }else{
                    if(null == data.message || "" ==data.message){
                    }else{
                        alert("实名认证申请撤销请求失败");
                        window.location.reload();
                    }
                }
            }
        });
    }

    //认证结果
    function getVerifiedQuery(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var custId = $(obj).parent().find('#custId_').val();

        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+'<%="/merchant/verified/queryMerchant" %>',
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "patchNo" : patchNo,
                    "channelNo" : channlCode,
                    "custId" : custId,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    alert("调用认证申请结果查询成功");
                }else{
                    if(null == data.message || "" ==data.message){
                    }else{
                        alert("认证申请结果查询失败");
                        window.location.reload();
                    }
                }
            }
        });
    }

    //实名认证
    function getVerified(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+'<%="/merchant/verified/merchantReportSubmit" %>',
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "patchNo" : patchNo,
                    "channelNo" : channlCode,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    alert("调用实名认证成功");
                }else{
                    if(null == data.message || "" ==data.message){
                    }else{
                        alert("实名认证失败");
                        window.location.reload();
                    }
                }
            }
        });
    }

    //微信升级
    function getWeChatUpdate(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var url=window.Constants.ContextPath+"<%="/merchant/merchantReported/weChatAppUpgradeMerchantReport"%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo;
        var name="window";                        //网页名称，可为空;
        var iWidth=1200;                          //弹出窗口的宽度;
        var iHeight=600;                          //弹出窗口的高度;
        //获得窗口的垂直位置
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        //获得窗口的水平位置
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        /*  $.blockUI();  */
        winChild =  window.open(url, name,params);

        window.location.reload();
    }



    //查看报备信息
    function getInfo(obj){
        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var custId = $(obj).parent().find('#custId_').val();
        var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.MERCHANTREPORTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo;
        var name="window";                        //网页名称，可为空;
        var iWidth=1200;                          //弹出窗口的宽度;
        var iHeight=600;                          //弹出窗口的高度;
        //获得窗口的垂直位置
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        //获得窗口的水平位置
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        /*  $.blockUI();  */
        winChild =  window.open(url, name,params);

        window.location.reload();
        
        
        <%--
          //微信 报备资料显示
		if("WX" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"/merchant/merchantReported/weChatAppMerchantReportShow?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo+" &custId="+custId; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                          //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	     	return;
   	   	}
        //随行付 报备资料显示
		if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"/merchant/merchantReported/suiXingMerchantReportShow?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo+" &custId="+custId; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                          //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	     	return;
   	   	}
		if("ALIPAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"/merchant/merchantReported/aliPayMerchantReportShow?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo+" &custId="+custId; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                          //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	     	return;
   	   	}
		
        if("BEST_PAY" == channlCode){

               var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.BESTPAYMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode;
             var name="window";                        //网页名称，可为空;
             var iWidth=1200;                          //弹出窗口的宽度;
             var iHeight=600;                       //弹出窗口的高度;
             //获得窗口的垂直位置
             var iTop = (window.screen.availHeight-30-iHeight)/2;
             //获得窗口的水平位置
             var iLeft = (window.screen.availWidth-10-iWidth)/2;
             var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
             /*  $.blockUI();  */
              winChild =  window.open(url, name,params);

              }
              if("SUIXING_PAY" == channlCode){

                  var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.SUIXINGMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode;
             var name="window";                        //网页名称，可为空;
             var iWidth=1200;                          //弹出窗口的宽度;
             var iHeight=600;                       //弹出窗口的高度;
             //获得窗口的垂直位置
             var iTop = (window.screen.availHeight-30-iHeight)/2;
             //获得窗口的水平位置
             var iLeft = (window.screen.availWidth-10-iWidth)/2;
             var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
             /*  $.blockUI();  */
              winChild =  window.open(url, name,params);
              }

        if("YQB" == channlCode){

               var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.YQBMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode;
             var name="window";                        //网页名称，可为空;
             var iWidth=1200;                          //弹出窗口的宽度;
             var iHeight=600;                       //弹出窗口的高度;
             //获得窗口的垂直位置
             var iTop = (window.screen.availHeight-30-iHeight)/2;
             //获得窗口的水平位置
             var iLeft = (window.screen.availWidth-10-iWidth)/2;
             var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
             /*  $.blockUI();  */
              winChild =  window.open(url, name,params);
           }

        if("KFT_PAY" == channlCode){

               var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.KFTMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode;
             var name="window";                        //网页名称，可为空;
             var iWidth=1200;                          //弹出窗口的宽度;
             var iHeight=600;                       //弹出窗口的高度;
             //获得窗口的垂直位置
             var iTop = (window.screen.availHeight-30-iHeight)/2;
             //获得窗口的水平位置
             var iLeft = (window.screen.availWidth-10-iWidth)/2;
             var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
             /*  $.blockUI();  */
              winChild =  window.open(url, name,params);
           }
        window.location.reload();
        --%>
    }

    //商户进件状态查询
    function getStatus(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+'<%=MerchantReportedPath.BASE+ MerchantReportedPath.SELECTMERCHANTREPORTSTATUS %>',
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "patchNo" : patchNo,
                    "channelNo" : channlCode,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    if(null == data.message || "" ==data.message){
                        alert("查询成功");
                        window.location.reload();
                    }else{
                        if("02" == data.message){
                            //进件成功后签约页面
                            alert("查询成功");
                            window.location.reload();

                        }else{
                            alert(data.message);
                            window.location.reload();
                        }
                    }
                }else{
                    if(null == data.message || "" ==data.message){
                        alert("查询失败");
                    }else{
                        alert(data.message);
                        window.location.reload();
                    }
                }
            }
        });
    }


    //商户更新进件
    function getUpdate(obj){
        var status ="reported";
        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        var detailStatus = $(obj).parent().find('#detailStatus_').val();

        if("WX" == channlCode){
            var url=window.Constants.ContextPath + "/merchant/merchantReported/weChatAppMerchantModify?merchantCode=" + merchantCode + "&patchNo=" + patchNo;
            var name="window";                        //网页名称，可为空;
            var iWidth=1200;                          //弹出窗口的宽度;
            var iHeight=600;                       //弹出窗口的高度;
            //获得窗口的垂直位置
            var iTop = (window.screen.availHeight-30-iHeight)/2;
            //获得窗口的水平位置
            var iLeft = (window.screen.availWidth-10-iWidth)/2;
            var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
            /*  $.blockUI();  */
            winChild =  window.open(url, name,params);
        }

        if("BEST_PAY" == channlCode){

            var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEBESTPAYREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode;
            var name="window";                        //网页名称，可为空;
            var iWidth=1200;                          //弹出窗口的宽度;
            var iHeight=600;                       //弹出窗口的高度;
            //获得窗口的垂直位置
            var iTop = (window.screen.availHeight-30-iHeight)/2;
            //获得窗口的水平位置
            var iLeft = (window.screen.availWidth-10-iWidth)/2;
            var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
            /*  $.blockUI();  */
            winChild =  window.open(url, name,params);

        }
        if("SUIXING_PAY" == channlCode){
            if(null == outMerchantCode){
                status ="unReported";
                var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.SUIXINGMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode;
                var name="window";                        //网页名称，可为空;
                var iWidth=1200;                          //弹出窗口的宽度;
                var iHeight=600;                       //弹出窗口的高度;
                //获得窗口的垂直位置
                var iTop = (window.screen.availHeight-30-iHeight)/2;
                //获得窗口的水平位置
                var iLeft = (window.screen.availWidth-10-iWidth)/2;
                var params='width='+iWidth
                    +',height='+iHeight
                    +',top='+iTop
                    +',left='+iLeft;
                /*  $.blockUI();  */
                winChild =  window.open(url, name,params);
            }else{

                var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATESUNXINGREPORT%>?merchantCode="+merchantCode+"&status="+status+"&detailStatus="+detailStatus+"&patchNo="+patchNo+"&channlCode="+channlCode;
                var name="window";                        //网页名称，可为空;
                var iWidth=1200;                          //弹出窗口的宽度;
                var iHeight=600;                       //弹出窗口的高度;
                //获得窗口的垂直位置
                var iTop = (window.screen.availHeight-30-iHeight)/2;
                //获得窗口的水平位置
                var iLeft = (window.screen.availWidth-10-iWidth)/2;
                var params='width='+iWidth
                    +',height='+iHeight
                    +',top='+iTop
                    +',left='+iLeft;
                /*  $.blockUI();  */
                winChild =  window.open(url, name,params);
            }

        }
        if("YQB" == channlCode){

            var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEYQBREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode;
            var name="window";                        //网页名称，可为空;
            var iWidth=1200;                          //弹出窗口的宽度;
            var iHeight=600;                       //弹出窗口的高度;
            //获得窗口的垂直位置
            var iTop = (window.screen.availHeight-30-iHeight)/2;
            //获得窗口的水平位置
            var iLeft = (window.screen.availWidth-10-iWidth)/2;
            var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
            /*  $.blockUI();  */
            winChild =  window.open(url, name,params);
        }
        if("KFT_PAY" == channlCode){

            var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEKFTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode;
            var name="window";                        //网页名称，可为空;
            var iWidth=1200;                          //弹出窗口的宽度;
            var iHeight=600;                       //弹出窗口的高度;
            //获得窗口的垂直位置
            var iTop = (window.screen.availHeight-30-iHeight)/2;
            //获得窗口的水平位置
            var iLeft = (window.screen.availWidth-10-iWidth)/2;
            var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
            /*  $.blockUI();  */
            winChild =  window.open(url, name,params);
        }
        window.location.reload();

    }

   //通联商户信息修改
    function allinPayEditReported(obj){
        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();

        if("ALLIN_PAY" == channlCode){
            var url=window.Constants.ContextPath + "/merchant/merchantReported/allinPayEditReported?merchantCode=" + merchantCode + "&patchNo=" + patchNo + "&channlCode=" + channlCode;
            var name="window";                        //网页名称，可为空;
            var iWidth=1200;                          //弹出窗口的宽度;
            var iHeight=600;                       //弹出窗口的高度;
            //获得窗口的垂直位置
            var iTop = (window.screen.availHeight-30-iHeight)/2;
            //获得窗口的水平位置
            var iLeft = (window.screen.availWidth-10-iWidth)/2;
            var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
            /*  $.blockUI();  */
            winChild =  window.open(url, name,params);
        }
    }
  
    //通联电子协议
    function getQueryElectUrl(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+"/merchant/merchantReported/allinPayMerchantReportQueryElectUrl",
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "patchNo" : patchNo,
                    "channelNo" : channlCode,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    if(null == data.message || "" ==data.message){
                        alert("查询成功");
                        window.location.reload();
                    }else{
                        if("02" == data.message){
                            //进件成功后签约页面
                            alert("查询成功");
                            window.location.reload();

                        }else{
                            alert(data.message);
                            window.location.reload();
                        }
                    }
                }else{
                    if(null == data.message || "" ==data.message){
                        alert("查询失败");
                    }else{
                        alert(data.message);
                        window.location.reload();
                    }
                }
            }
        });
    }
    
    //通联协议重发
    function getQueryElectSign(obj){

        var merchantCode=$(obj).parent().find('#merchantCode_').val();
        var patchNo = $(obj).parent().find("#patchNo_").val();
        var channlCode =$(obj).parent().find('#channelNo_').val();
        var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath+"/merchant/merchantReported/allinPayMerchantReportQueryElectSign",
            data:
                {
                    "merchantCode" 	: merchantCode,
                    "patchNo" : patchNo,
                    "channelNo" : channlCode,
                    "outMerchantCode" : outMerchantCode
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    if(null == data.message || "" ==data.message){
                        alert("查询成功");
                        window.location.reload();
                    }else{
                        if("02" == data.message){
                            //进件成功后签约页面
                            alert("查询成功");
                            window.location.reload();

                        }else{
                            alert(data.message);
                            window.location.reload();
                        }
                    }
                }else{
                    if(null == data.message || "" ==data.message){
                        alert("查询失败");
                    }else{
                        alert(data.message);
                        window.location.reload();
                    }
                }
            }
        });
    }
</script>

<body >
<!-- 用户信息 -->
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
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="nino" style="display:none; text-align: center; color: #438eb9;">
                            <h3 >商户行业类别信息：<span class="showInfo"></span></h3>
                        </div>
                        <!-- 报备 -->
                        <form id ="merchantForm" action='<c:url value="<%=MerchantEnterReportedPath.BASE +MerchantEnterReportedPath.LIST %>"/>' name="fileUpload" enctype="multipart/form-data" method="post" >
                            <input type="hidden" id="_token" name="_token"/>
                            <table class="search-table" style="margin: 40px 0;">
                                <tr>
                                    <td class="td-left">商户编号：</td>
                                    <td class="td-right">
									<span class="input-icon">
										<input type="text" name="merchantCode" id="merchantCode" value="${queryBean.merchantCode}">
										<i class="icon-leaf blue"></i>
									</span>
                                    </td>
                                    <td class="td-left">商户名称：</td>
                                    <td class="td-right">
                                   <span class="input-icon">
										<input type="text" name="custName" id="custName" value="${queryBean.custName}">
										<i class="icon-leaf blue"></i>
									</span>
                                    </td>
                                    <td class="td-left">邮箱账号：</td>
									<td class="td-right">
                                         <span class="input-icon">
											<input type="text" name="email" id="email" value="${queryBean.email }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">手机号：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" name="mobile" id="mobile" value="${queryBean.mobile }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
                                </tr>
                                <tr>
                                    <td class="td-left">报备渠道：</td>
                                    <td class="td-right">
                                        <select name="channelNo" id="channelNo">
                                            <option value="">请选择报备渠道 </option>
                                            <c:if test="${not empty infoList }">
                                                <c:forEach items="${infoList }" var="info">
                                                    <option id="${info.channlId}" value="${info.channlCode}">${info.channlName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                    <td class="td-left">报备状态：</td>
                                    <td class="td-right">
                                        <select name="detailStatus" id="detailStatus">
                                            <option value="">请选择 </option>
                                            <option value="00"> 报备成功     </option>
                                            <option value="99"> 报备失败     </option>
                                            <option value="0">  提交审核中 </option>
                                            <option value="1">  审核通过     </option>
                                            <option value="2">  审核不通过     </option>
                                        </select>
                                    </td>
                                    <td class="td-left">报备时间：</td>
                                    <td class="td-right">
                                        <input type="text" name="startModifyTime" id="startModifyTime" readonly value="" onFocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                        -
                                        <input type="text" name="endModifyTime" id="endModifyTime" readonly value="" onFocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="8" align="center">
									<span class="input-group-btn">
										<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch">
											查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
										<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch">
											清空
										<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
									</span>
                                    </td>
                                </tr>

                            </table>
                        </form>

                        <div class="list-table-header">商户报备数据列表</div>
                        <div class="table-responsive">
                            <table id="sample-table-2" class="list-table">
                                <thead>
                                <tr>
                                    <th width="7%">商户名称</th>
                                    <th width="6%">商户简称</th>
                                    <th width="7%">商户编号</th>
                                    <th width="10%">账号</th>
                                    <th width="5%">报备渠道</th>
                                    <th width="8%">渠道商户号</th>
                                    <!-- <th width="8%">微信申请单编号</th> -->
                                    <th width="6%">商户报备状态</th>
                                    <th width="10%">返回信息</th>
                                    <th width="9%">报备时间</th>
                                    <th width="20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${reportedList}" var="reported" varStatus="status">
                                    <tr class="reported" id="reported">
                                        <td>${reported.custName}</td>
                                        <td>${reported.shortName}</td>
                                        <td>${reported.merchantCode }</td>
                                        <td>
											<c:choose>
												<c:when test="${reported.email == null}">
													${reported.mobile }
												</c:when>
												<c:when test="${reported.mobile == null}">
													${reported.email }
												</c:when>
												<c:when test="${reported.mobile != null && reported.email != null }">
													${reported.email }/${reported.mobile }
												</c:when>
											</c:choose>
										</td>
                                        <td>
                                            <c:if test="${reported.channelNo == 'iCr'}">
                                                华润银行
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'CR'}">
                                                华润银行
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'HELIPAY'}">
                                                合利宝
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'KFT_PAY'}">
                                                快付通
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'FM_COMBINEDPAY'}">
                                                富民
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'WX'}">
                                                微信
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'ALIPAY'}">
                                                支付宝
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'BEST_PAY'}">
                                                翼支付
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'SUIXING_PAY'}">
                                                随行付
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'SUM_PAY'}">
                                                商盟支付
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'YQB'}">
                                                平安付
                                            </c:if>
                                            <c:if test="${reported.channelNo == 'ALLIN_PAY'}">
                                                通联
                                            </c:if>
                                        </td>
                                        <td>${reported.outMerchantCode}</td>
                                            <%-- <td>${reported.wxApplyNo}</td> --%>
                                        <td>
                                            <c:if test="${reported.detailStatus == '00'}">
                                                提交报备成功
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '99'}">
                                                报备失败
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '0'}">
                                                待审核
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '1'}">
                                                审核通过
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '2'}">
                                                审核失败
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '20'}">
                                                微信实名认证申请中
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '21'}">
                                                微信实名认证成功
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '22'}">
                                                微信实名认证失败
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '26'}">
                                                微信子商户授权查询申请
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '27'}">
                                                微信子商户授权成功
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '28'}">
                                                微信子商户授权失败
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${reported.detailStatus == '00'}">
                                                ${reported.resultMsg}
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '99'}">
                                                ${reported.resultMsg}
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '0'}">
                                                ${reported.resultMsg}
                                            </c:if>
                                            <c:if test="${reported.detailStatus == '2'}">
                                                ${reported.resultMsg}
                                            </c:if>
                                        </td>
                                        <td>
                                                ${reported.reportTime}
                                        </td>
                                        <td>
                                            <input type="hidden" id="merchantCode_" value="${reported.merchantCode }">
                                            <input type="hidden" id="custName_" value="${reported.custName }">
                                            <input type="hidden" id="patchNo_" value="${reported.patchNo }">
                                            <input type="hidden" id="channelNo_" value="${reported.channelNo }">
                                            <input type="hidden" id="outMerchantCode_" value="${reported.outMerchantCode }">
                                            <input type="hidden" id="detailStatus_" value="${reported.detailStatus }">
                                            <input type="hidden" id="custId_" value="${reported.custId }">

                                            <button type="button"  class="btn btn-primary btn-xs" onclick="getInfo(this);">查看资料</button>
                                            <c:if test="${reported.detailStatus =='0' ||reported.detailStatus =='00' }">
                                                <button type="button"  class="btn btn-primary btn-xs" onclick="getStatus(this);">刷新状态</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='99' || reported.reportStatus =='E' || reported.reportStatus =='F'}">
                                                <button type="button"  class="btn btn-primary btn-xs" onclick ="getUpdate(this);">报备更新</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='1' && reported.outMerchantCode != null &&  reported.channelNo =='SUIXING_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getVerified(this);">实名认证</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='24' && reported.outMerchantCode != null &&  reported.channelNo =='SUIXING_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getVerified(this);">实名认证</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='20' && reported.outMerchantCode != null &&  reported.channelNo =='SUIXING_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getVerifiedQuery(this);">认证结果</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus !='99' &&reported.detailStatus !='21' && reported.outMerchantCode != null &&  reported.channelNo =='SUIXING_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getVerifiedCancel(this);">申请撤销</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='21' && reported.outMerchantCode != null &&  reported.channelNo =='SUIXING_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getVerifiedAuthorize(this);">子商户授权</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='1' && reported.channelNo =='WX'}">
                                                <button type="button"  class="btn btn-primary btn-xs" onclick ="getWeChatUpdate(this);">微信升级</button>
                                            </c:if>
                                            <c:if test="${reported.channelNo =='ALLIN_PAY' && reported.detailStatus == '1'}">
                                                <button type="button" class="btn btn-primary btn-xs" onclick ="allinPayEditReported(this);" >商户信息修改</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='1' && reported.channelNo =='ALLIN_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getQueryElectUrl(this);">通联电子协议</button>
                                            </c:if>
                                            <c:if test="${reported.detailStatus =='1' && reported.channelNo =='ALLIN_PAY'}">
                                                <button type="button"  class="btn btn-primary btn-xs"  onclick ="getQueryElectSign(this);">通联协议重发</button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty reportedList}">
                                    <tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                        <!-- 分页 -->
                        <c:if test="${not empty reportedList}">
                            <%@ include file="/include/page.jsp"%>
                        </c:if>
                    </div>
                </div>
            </div>
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

