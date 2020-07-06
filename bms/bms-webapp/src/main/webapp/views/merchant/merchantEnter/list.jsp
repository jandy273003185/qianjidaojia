<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<link rel="stylesheet" href="/static/css/font-awesome.min.css">
<!-- page specific plugin styles -->
<link rel="stylesheet" href="/static/css/dropzone.css">

<link rel="stylesheet" href="/static/css/ace.min.css">
<link rel="stylesheet" href="/static/css/ace-rtl.min.css">
<link rel="stylesheet" href="/static/css/ace-skins.min.css">
<link rel="stylesheet" href="/static/css/gyzb-admin.css">
<link href="/static/js/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/static/js/AutoDatePicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/static/css/bootstrap-datetimepicker.min.css">
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src="/static/js/ace-extra.min.js"></script>
<script src="/static/js/jquery-2.0.3.min.js"></script>
<script src="/static/js/jquery.blockUI.js"></script>
<script src="/static/js/jquery.pagination.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/typeahead-bs2.min.js"></script>
<script src="/static/js/dropzone.min.js"></script>
<script src="/static/js/ace-elements.min.js"></script>
<script src="/static/js/ace.min.js"></script>
<script src="/static/js/gyzb-admin.js"></script>
<script src="/static/js/checkRule_source.js"></script>
<script src="/static/js/service.js"></script>
<script src="/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/WdatePicker.js"></script>
<script src="/static/js/AutoDatePicker.js"></script>
<script src="/static/js/jquery-ui.min.js"></script>
<script src="/static/js/jquery.divbox.js"></script>
<script src="/static/js/ajaxfileupload.js"></script>
<script src="/static/js/mobileBUGFix.mini.js"></script>
<script src="/static/js/uploadCompress.js"></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>前台录入管理列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		.headlerPreview{
			background-color:#ffbf66;
			text-align:center;
			height:30px;
			font-weight:bold;
		}
	</style>
</head>
<script type="text/javascript">
    $(function(){
        //导出商户数据
        $('.exportBut').click(function(){
            var merchantCode = $('.search-table #merchantCode').val();
            var startCreateTime = $('.search-table #startCreateTime').val();
            var endCreateTime = $('.search-table #endCreateTime').val();
            var auditState = $('.search-table #auditState').val();
            var custName = $('.search-table #custName').val();
            var email = $('.search-table #email').val();
            var merchantState = $('.search-table #state').val();

            var src ="<%= request.getContextPath()+ MerchantEnterPath.BASE+MerchantEnterPath.PROEXPORTMERCHANTINFO%>?merchantCode="+
                merchantCode+
                "&startCreateTime="+
                startCreateTime+
                "&endCreateTime="+
                endCreateTime+
                "&auditState="+
                auditState+
                "&custName="+
                custName+
                "&email="+
                email+
                "&merchantState="+
                merchantState;
            $(".exportBut").attr("href",src);
        });


        $("input[type=file]").each(
            function() {
                var _this = $(this);
                _this.localResizeIMG({
                    quality : 0.8,
                    success : function(result,file) {

                        var att = pre.substr(pre.lastIndexOf("."));
                        //压缩后图片的base64字符串
                        var base64_string = result.clearBase64;

                        $('#'+_this.attr('id')+'temp').val(att+","+base64_string);
                        //图片预览
                        var imgObj = $('#'+_this.attr('id')+'Image');
                        imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show();

                        var imgObj2 = $('#'+_this.attr('id')+'Image2');
                        imgObj2.attr("src", "data:image/jpeg;base64," + base64_string).show();

                        var width = result.width;
                        var height = result.height;
                        var scale =  width/height;
                        if(width >800){
                            width = 800;
                            height = width / scale;
                        }
                        $(".showDiv").width(width+"px");
                        $(".showDiv").height(height+"px");

                        //优图
                        var param = "{str:\""+base64_string+"\",flag:\""+_this.attr('id')+"\"}"
                        $.ajax({
                            async:false,
                            type:"POST",
                            contentType:"application/json;charset=utf-8",
                            dataType:"text",
                            url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.YOUTU%>',
                            data:param,
                            success:function(data){
                                var json = eval('(' + data + ')');
                                if(json.result=="SUCCESS"){
                                    if(_this.attr('id')=="certAttribute1"){//身份證
                                        $("#representativeName").val(json.cardName);
                                        $("#representativeCertNo").val(json.cardId);
                                    }else if(_this.attr('id')=="businessPhoto"){//营业执照
                                        $("#businessLicense").val(json.businessLicense);
                                        //$("#businessTerm").val(json.businessTermStart);
                                        if("长期"==json.businessTermEnd){
                                            //$("input[value='forever']").click();
                                        }else{
                                            alert(json.businessTermEnd);
                                            $("#businessTerm").val(json.businessTermEnd);
                                            //$("#businessTermEnd").val(json.businessTermEnd);
                                        }
                                        //$("#custAdd").val(json.legalAddress);
                                        $("#businessRegAddr").val(json.legalAddress);
                                    }
                                }
                            }
                        });
                    }
                });
            });
        
        
        /**判断商户是否已经报备 **/
    	$("#choiceChannel").click(function(){
    		var channlCode = $("#channlCode").val();
    		var merchantCode= $("#merchantCode_1").val();
    		if(kong.test($("#channlCode").val())){
    			$("#channlCodeLab").text("请选择渠道提交");
    			return false;
    		}
    		var status ="unReported";
    		//查询出是否已报备
    		$.ajax({
    			type:"POST",
    			dataType:"json",
    			url:window.Constants.ContextPath+'<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.QUERYSTATUS %>',
    			data:
    			{
    				"merchantCode" 	: merchantCode,
    				"channelNo"    : channlCode
    			},
    			success:function(data){
    				if(data.result=="SUCCESS"){
    					$("#channlCodeLab").text("该商户该渠道已报备");
    					return false;
    				}else if(data.status =="01" && data.result =="FAIL"){
    					alert("商户未审核通过，请先审核");
    					var custId = data.custId;
    					var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.AUDITPAGE%>?custId="+custId;
    			        var name="newwindow";
    			        var iWidth=1500;
    			        var iHeight=600;
    			        var iTop = (window.screen.availHeight-30-iHeight)/2;
    			        var iLeft = (window.screen.availWidth-10-iWidth)/2;
    			        var params='width='+iWidth
    			            +',height='+iHeight
    			            +',top='+iTop
    			            +',left='+iLeft;
    			        winChild = window.open(url, name,params);
    				}else{

    					if("BEST_PAY" == channlCode){
    					   	   	
    				   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.BESTPAYMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				      	winChild =  window.open(url, name,params);
    				   		 
    			   	   	}
    			   	   	if("SUIXING_PAY" == channlCode){
    			   	   	   	
    			   	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.SUIXINGMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				      	winChild =  window.open(url, name,params);
    			   	   	}
    					 
    					if("YQB" == channlCode){
    				   	   	
    				   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.YQBMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				      	winChild =  window.open(url, name,params);
    				   	}
    					
    					if("ALIPAY" == channlCode){
    				   	   	
    				   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.ALIPAY_MERCHANT_REPORTS%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode;
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
    				      	winChild =  window.open(url, name,params);
    				   	}
    					
    					if("KFT_PAY" == channlCode){
    				   	   	
    				   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.KFTMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				   	}
						if("WX" == channlCode){
    				   		var url=window.Constants.ContextPath+"<%="/merchant/merchantReported/weChatAppMerchantReport"%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				   	}
						if("ALLIN_PAY" == channlCode){
    				   		var url=window.Constants.ContextPath+"<%="/merchant/merchantReported/allinPayMerchantReport"%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				   	}
						if("LKL" == channlCode){
    				   		var url=window.Constants.ContextPath+"<%="/merchant/reported/laKaLaPayMerchantReporteds"%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
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
    				   	}
    					window.location.reload();
    				}
    			}
    		});
    	});
    })


    $(document).ready(function(){
        var resultList= ${merchantList};
        var merchants=$("tr.merchant");
        $.each(resultList,function(i,value){
            $.data(merchants[i],"merchant",value);
        });

        $('.clearMerchantSearch').click(function(){
            $('.search-table #merchantCode').val('');
            $('.search-table #custName').val('');
            $('.search-table #email').val('');
            $('.search-table #mobile').val('');
            $('.search-table #state').val('');
            $('.search-table #auditState').val('');
            $('.search-table #startCreateTime').val('');
            $('.search-table #endCreateTime').val('');

        })

        $('.buttonSearch').click(function(){
            var startDate = $("#startCreateTime").val();
            var endDate= $("#endCreateTime").val();
            if("" != startDate && "" != endDate && startDate > endDate)
            {
                $.gyzbadmin.alertFailure("结束日期不能小于开始日期");
                return false;
            }
            var form = $('#merchantEnterForm');
            form.submit();
        });
    });

    var winChild;
    /** 跳转新增页面 **/
    function addMerchantEntry(){
        var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.ADD%>";
        var name="newwindow";
        var iWidth=1500;
        var iHeight=600;
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        winChild = window.open(url, name,params);
    }
    /** 跳转预览页面 **/
    function previewMerchantEntry(obj){
        var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
        var custId = $(obj).parent().find('input[name="custId"]').val();
        var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.PREVIEW%>?custId="+custId;
        var name="newwindow";
        var iWidth=1500;
        var iHeight=600;
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        winChild = window.open(url, name,params);
        window.location.reload();
    }
    /** 跳转修改页面 **/
    function upadteMerchantEntry(obj){
        var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
        var custId = $(obj).parent().find('input[name="custId"]').val();
        var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATEPAGE%>?custId="+custId;
        var name="newwindow";
        var iWidth=1500;
        var iHeight=600;
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        winChild = window.open(url, name,params);
        window.location.reload();
    }
    /** 跳转审核页面  **/
    function aduitMerchantEntry(obj){
        var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
        var custId = $(obj).parent().find('input[name="custId"]').val();
        var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.AUDITPAGE%>?custId="+custId;
        var name="newwindow";
        var iWidth=1500;
        var iHeight=600;
        var iTop = (window.screen.availHeight-30-iHeight)/2;
        var iLeft = (window.screen.availWidth-10-iWidth)/2;
        var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
        winChild = window.open(url, name,params);
        window.location.reload();
    };

    function channelMerchantEntry(obj){
    	var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
    	$("#channelListModel #merchantCode_1").val(merchantCode);
    	
    }
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
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div id="door_temp">
						</div>
						<!-- 查询条件 -->
						<form  id="merchantEnterForm" action='<c:url value="<%=MerchantEnterPath.BASE + MerchantEnterPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tbody>
								<tr>
									<td class="td-left">商户编号：</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" name="merchantCode" id="merchantCode" value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户名称：</td>
									<td class="td-right">
                                    <span class="input-icon">
											<input type="text" name="custName" id="custName" value="${queryBean.custName }">
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
									<td class="td-left">商户状态：</td>
									<td class="td-right">
										<select name="state" id="state">
											<option value="">请选择 </option>
											<option value="00"> 有效 </option>
											<option value="01"> 待审核 </option>
											<option value="02"> 注销 </option>
											<option value="03"> 冻结 </option>
											<option value="04"> 审核不通过 </option>
										</select>
										<label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									<td class="td-left">审核状态：</td>
									<td class="td-right">
										<select name="auditState" id="auditState">
											<option value="">请选择 </option>
											<option value="0"> 审核通过 </option>
											<option value="1"> 待审核 </option>
											<option value="2"> 审核不通过 </option>
										</select>
										<label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									<td class="td-left">录入时间：</td>
									<td class="td-right">
										<input type="text" name="startCreateTime" id="startCreateTime" readonly value="" onFocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text" name="endCreateTime" id="endCreateTime" readonly value="" onFocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											  <button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
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
								</tbody>
							</table>
						</form>

						<div style="margin:30px 0 10px 0">
							<a href="<%=request.getContextPath()+MerchantEnterPath.BASE + MerchantEnterPath.ADDPAGE%>"  class="btn btn-primary" >新增</a>
							<button type="button"  class="btn btn-primary" disabled="disabled">批量审核资料</button>
							<button type="button"  class="btn btn-primary" disabled="disabled">批量录入</button>
							<a class="btn btn-primary exportBut">导出</a>
						</div>

						<div class="list-table-header">商户列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table">
								<thead>
								<tr>
									<th width="10%">商户名称</th>
									<th width="10%">商户简称</th>
									<th width="11%">商户编号</th>
									<th width="11%">账号</th>
									<th width="12%">录入时间</th>
									<th width="7%">商户状态</th>
									<th width="7%">审核状态</th>
									<th width="5%">审核人</th>
									<th width="27%">操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${merchantList }" var="merchant">
									<tr class="merchant" id="merchant">
										<td>${merchant.custName }</td>
										<td>${merchant.shortName }</td>
										<td>${merchant.merchantCode }</td>
										<td>
											<c:choose>
												<c:when test="${merchant.email == null}">
													${merchant.mobile }
												</c:when>
												<c:when test="${merchant.mobile == null}">
													${merchant.email }
												</c:when>
												<c:when test="${merchant.mobile != null && merchant.email != null }">
													${merchant.email }/${merchant.mobile }
												</c:when>
											</c:choose>
										</td>
										<td>${merchant.createTime }</td>
										<td>
											<c:choose>
												<c:when test="${merchant.state =='00'}">
													有效
												</c:when>
												<c:when test="${merchant.state =='01'}">
													待审核
												</c:when>
												<c:when test="${merchant.state =='02'}">
													注销
												</c:when>
												<c:when test="${merchant.state =='03'}">
													冻结
												</c:when>
												<c:when test="${merchant.state =='04'}">
													审核不通过
												</c:when>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${merchant.auditState =='0'}">
													审核通过
												</c:when>
												<c:when test="${merchant.auditState =='1'}">
													待审核
												</c:when>
												<c:when test="${merchant.auditState =='2'}">
													审核不通过
												</c:when>
											</c:choose>
										</td>
										<td>${merchant.aduitUserName }</td>
										<td>
											<input type="hidden" name="merchantCode" id = "merchantCode" value="${merchant.merchantCode }" />
											<input type="hidden" name="custId"  id= "custId" value="${merchant.custId }" />
											<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.PREVIEW %>">
												<a href="#" class="" onclick="previewMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
													<button type="submit" class="btn btn-purple btn-sm">预览</button>
												</a>
											</gyzbadmin:function>
											<c:if test="${merchant.auditState !='0'}">
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATEPAGE %>">
													<a href="#" class="" onclick="upadteMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">商户更新</button>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.AUDITPAGE%>">
													<a href="#" class="" onclick="aduitMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">商户审核</button>
													</a>
												</gyzbadmin:function>
											</c:if>
											<c:if test="${merchant.auditState =='0'}">
												<button type="submit" class="btn btn-purple btn-sm" disabled>商户更新</button>
												<button type="submit" class="btn btn-purple btn-sm" disabled>商户审核</button>
											</c:if>
                                            <button type="hidden" type="button" onclick="channelMerchantEntry(this)" data-toggle='modal'  data-target="#channelListModel"  class="btn btn-purple btn-sm" >报备</button>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty merchantList}">
									<tr>
										<td colspan="20" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td>
									</tr>
								</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty merchantList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>

				<!-- 审核不通过弹框 -->
				<div class="modal fade" style="z-index:1043;" id="auditMessageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width:30%;z-index:99;">
						<div class="modal-content" >
							<div class="modal-header" style="background-color:0099CC">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">审核不通过</h4>
							</div>
							<div class="modal-body">
								<table>
									<tr>
										<td >请输入审核不通过理由：</td>
									</tr>
									<tr>
										<td>
											<textarea rows="5" cols="40" id="auditMessage" ></textarea>
										</td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn" onclick="notPass()">确定</button>
							</div>
						</div><!-- /.modal-content -->
					</div>
				</div><!-- /.modal -->
				
									
				<!-- 渠道弹框 -->
				<div class="modal fade" style="z-index:1043;" id="channelListModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:30%;z-index:99;">
				      <div class="modal-content" >
				      <div class="modal-header" style="background-color:0099CC">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				            <h4 class="modal-title" id="myModalLabel">报备渠道选择</h4>
				         </div>
				         <div class="modal-body">
				         <input  type="hidden"  name="merchantCode_1" id="merchantCode_1" value="">
				         <select name="channlCode" id="channlCode" style="width:300px;text-align: center;">
							<option value="">--请选择报备渠道--</option>
							 <c:if test="${not empty channlInfoList }">
                            <c:forEach items="${channlInfoList }" var="info">
                            	<option  value="${info.channlCode}">${info.channlName}</option>
                            </c:forEach>
                   		  </c:if>
                   		  
						 </select>
						 <label class="label-tips" id="channlCodeLab"></label>
						 </div>
				         <div class="modal-footer">
				         	<button type="button" id="choiceChannel" class="btn btn-primary addadBtn">确定</button>
				            <label class="label-tips" id="choiceChannelLab"></label>
				            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
				            
				         </div>
				      </div><!-- /.modal-content -->
				   </div>
				</div><!-- /.modal -->
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

<script type="text/javascript">
</script>
</html>