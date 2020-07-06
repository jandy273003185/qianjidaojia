<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgenReportPath" %>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代理商报表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style>
</head>

<body style="overflow-x:hidden;">
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
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=AgenReportPath.BASE + AgenReportPath.LIST %>"/>' method="post">
							<input type="hidden" name="isFirst" value="${isFirst}"> 
						
							<table class="search-table">
								<tr>
									<td class="td-left">代理商</td>
									<td class="td-right">
										<sevenpay:selectAgentMerchantTag name="agentCustId" id="agentCustId" defaultValue="${queryBean.agentCustId }"/>
									</td>
									<td class="td-left">客户经理：</td>
									<td class="td-right">
										<sevenpay:selectSysUserTag name="custManager" id="custManager" defaultValue="${queryBean.custManager}"/>
									</td>
									<td class="td-left" >账期</td>
									<td class="td-right" > 
									<input type="text" name="beginWorkDate"  id="beginWorkDate" readonly="readonly" value="${queryBean.beginWorkDate }"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									-
									<input type="text" name="endDate"  id="endDate" readonly="readonly" value="${queryBean.endDate }"  onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AgenReportPath.BASE + AgenReportPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110 "></i>
											</button>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
											</span> 
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">当前列表总交易笔数：${tradetotalCount},交易总额为：${tradeTotalAmt},退款总金额：${refundTotalAmt},有效总金额：${vaildTotalAmt},总结算金额：${commisionTotalAmt}</div>
						<div class="table-responsive" >
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr >
										<th>开始账期</th>
										<th>结束账期</th>
										<th>代理商编号</th>
										<th>代理商名称</th>
										<th>代理商产品及费率</th>
										<th>客户经理</th>
										<th>交易笔数</th>
										<th>交易金额</th>
										<th>退款笔数</th>
										<th>退款金额</th>
										<th>有效金额</th>
										<th>代理商结算金额</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${agencyReportList}" var="report" varStatus="i">
										<tr class="agency">
											<td>${report.beginWorkDate}</td>
											<td>${report.endDate}</td>
											<td>${report.agentMerchantId}</td>
											<td>${report.custName}</td>
											<td>
												${report.agentRate.split('/')[0]}
												<br/>
												${report.agentRate.split('/')[1]}
												<br/>
												${report.agentRate.split('/')[2]}
												<br/>
												${report.agentRate.split('/')[3]}
											</td>
											<td>${report.custManager}</td>
											<td>${report.receiveCount}</td>
											<td>${report.receiveTotalAmt}</td>
											<td>${report.refundCount}</td>
											<td>${report.refundTotalAmt}</td>
											<td>${report.validAmt}</td>
											<td>${report.commision}</td>
											<td>
												<input type="hidden" id="agentId" value="${report.agentId}" >
												<input type="hidden" id=beginWorkDate value="${report.beginWorkDate}" >
												<input type="hidden" id=endDate value="${report.endDate}" >
												<a  onclick="queryReport(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"   id="queryWaterBtn"  class="btn btn-primary btn-xs"  >商户交易详情</button>
												</a> 
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty agencyReportList}">
										<tr>
											<td colspan="13" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty agencyReportList}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
</body>

<script type="text/javascript">

var winChild ;
function queryReport(obj){
	var agency = $.data($(obj).parent().parent()[0],"agency");
	var agentId=agency.agentCustId;
	var beginWorkDate = agency.beginWorkDate;
	var endDate =agency.endDate;
	
	var url=window.Constants.ContextPath+"<%=AgenReportPath.BASE+ AgenReportPath.MERCHANTLIST%>?agentId="+agentId+"&beginWorkDate="+beginWorkDate+"&endDate="+endDate; 
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
 $.blockUI(); 


 winChild =  window.open(url, name,params); 
	/* $('#main-container').OpenDiv();
	$('#main-container').CloseDiv(); */
} 

$(function(){
	/** 缓存 **/
	var agencyReports = ${agencyReportList};
	var agencyReport = $("tr.agency");
	$.each(agencyReports,function(i,value){
		$.data(agencyReport[i],"agency",value);
	});
	
	$("#agentCustId").comboSelect();
	$("#custManager").comboSelect();
	$(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	
	var loop =	setInterval(function() {     
	    if(winChild.closed) { 
	    	$.unblockUI();
	    }    
	}, 200); 
});

/** 导出代理商报表 */
$(".exportBut").click(function(){
	var agentCustId = $('.search-table #agentCustId').val().trim();
	var beginWorkDate = $('.search-table #beginWorkDate').val().trim();
	var endDate = $('.search-table #endDate').val().trim();
	var custManager = $('.search-table #custManager').val().trim();
	var src ="<%= request.getContextPath() + AgenReportPath.BASE + AgenReportPath.EXPORT%>?agentCustId=" +
	agentCustId + "&beginWorkDate=" + beginWorkDate +"&endDate="+endDate+"&custManager="+custManager;
	$(".exportBut").attr("href",src);
}); 


function forCloseDiv(){
	 $.unblockUI();
} 
</script>
</html>