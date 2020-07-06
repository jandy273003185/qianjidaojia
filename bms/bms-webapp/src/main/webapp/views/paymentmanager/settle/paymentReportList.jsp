<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgenReportPath" %>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
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
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTREPORTlIST %>"/>' method="post">
							<input type="hidden" name="isFirst" value="${isFirst}"> 
						
							<table class="search-table">
								<tr>
									<td class="td-left" >代付账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="paerAcctNo" id="paerAcctNo"  value="${queryBean.paerAcctNo}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">注册时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
									
									</tr>
									
									
									<tr>
									<td class="td-left">代付类型：</td>
									<td class="td-right">								   
										 <select name="toPayType" id="toPayType" >
										  <option  value="" > 请选择 </option>
										  <option  value="00">银行卡 </option>
										  <option  value="01">余额 </option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									
									<td class="td-left">代付方式：</td>
									<td class="td-right">								   
										 <select name="type" id="type" >
										  <option  value="" > 请选择 </option>
										  <option  value="0">批量 </option>
										  <option  value="1">单笔 </option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									</tr>
									
									
									
									
									
								
								<tr>
									<td colspan="8" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTREPORTlIST %>">
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
						
						<div class="list-table-header">当前列表代付总金额：${totalMoney },代付笔数：${sumCount },手续费：${FeeAmt}</div>
						<div class="table-responsive" >
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr >
									    <th>交易号</th>
										<th>代付账号</th>
										<th>名称</th>
										<th>代付方式</th>
										<th>代付类型</th>
										<th>代付笔数</th>
										<th>代付金额</th>
										<th>代付手续费</th>
										<th>成功笔数</th>
										<th>失败笔数</th>
										<th>成功金额</th>
										<th>失败金额</th>
										<th>代付时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${reportList}" var="report" varStatus="i">
										<tr class="agency">
										   <td>${report.batNo}</td>
											<td>${report.paerAcctNo}</td>
											<td>${report.custName}</td>
											<td><c:choose>
							              <c:when test="${report.toPayType =='00'}">  									  
							                                           银行卡       								  
							              </c:when>
						                 <c:when test="${report.toPayType =='01'}">  									  
							                                        余额              								  
							             </c:when>
								         </c:choose>
								         </td>
										<td><c:choose>
							              <c:when test="${report.type =='0'}">  									  
							                                                批量          								  
							              </c:when>
						                 <c:when test="${report.type =='1'}">  									  
							                                                单笔              								  
							              </c:when>
								         </c:choose>
								         </td>
											<td>${report.sumCount}</td>
											<td>${report.sumAmt}</td>
											<td>${report.serviceFee}</td>
										   <td>
                                           <c:choose>
							                <c:when test="${report.succCount!='000' }">  									  
							                ${report.succCount}           								  
							                </c:when>
						                  <c:when test="${report.succCount=='000'}">  									  
							              <c:if test="${report.tradeStatus=='00' }">1</c:if> 
							              <c:if test="${report.tradeStatus=='99' }">0</c:if> 
							              <c:if test="${report.tradeStatus=='08' }">0</c:if>                    								  
							              </c:when>
								          </c:choose>
								           </td>
											
										<td><c:choose>
							    <c:when test="${report.failCount!='failCount'}">  									  
							                ${report.failCount}           								  
							     </c:when>
						        <c:when test="${report.failCount=='failCount' || report.failCount==null }">  									  
				                      <c:if test="${report.tradeStatus=='00' }">0</c:if> 
							          <c:if test="${report.tradeStatus=='99' }">1</c:if> 
							          <c:if test="${report.tradeStatus=='08' }">0</c:if>                    								  
							     </c:when>
								</c:choose>
								</td>	
										<td><c:choose>
							    <c:when test="${report.succAmt!='succAmt'}">  									  
							                ${report.succAmt}           								  
							     </c:when>
						        <c:when test="${report.succAmt=='succAmt'}">  									  
							          <c:if test="${report.tradeStatus=='00' }">${report.sumAmt}</c:if> 
							          <c:if test="${report.tradeStatus=='99' }">0</c:if> 
							          <c:if test="${report.tradeStatus=='08' }">0</c:if>                    								  
							     </c:when>
								</c:choose>
								</td>	
											
								<td><c:choose>
							    <c:when test="${report.failAmt!='failAmt'}">  									  
							                ${report.failAmt}           								  
							     </c:when>
						        <c:when test="${report.failAmt=='failAmt' || report.failAmt==null }">  									  
							          <c:if test="${report.tradeStatus=='00' }">0</c:if> 
							          <c:if test="${report.tradeStatus=='99' }">${report.sumAmt}</c:if> 
							          <c:if test="${report.tradeStatus=='08' }">${report.sumAmt}</c:if>                    								  
							     </c:when>
								</c:choose>
								</td>		
											
											
											
							<td><fmt:formatDate value="${report.createTime }" pattern="yyyy-MM-dd"/></td>


											<td>
												<input type="hidden"  name="batNo" id="batNo" value="${report.batNo }">
												<input type="hidden"  name="type" id="type" value="${report.type}">
												<input type="hidden"  name="toPayType" id="toPayType" value="${report.toPayType}">
												<c:if test="${report.type =='0'}">
												<a  onclick="queryReport(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"   id="queryWaterBtn"  class="btn btn-primary btn-xs"  >详情</button>
												</a> 
												</c:if>
												
												<c:if test="${report.type =='1'}">
												<c:if test="${report.toPayType =='00'}">
												<a  onclick="queryReportOfSingle(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"    data-toggle='modal' data-target="#showSingleOfBank" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"  >详情</button>
												</a> 
												</c:if>
												<c:if test="${report.toPayType =='01'}">
												  <a  onclick="queryReportOfSingle(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"    data-toggle='modal' data-target="#showSingleOfBalance" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"  >详情</button>
												</a> 
												</c:if>
												
												
												
												</c:if>
												
												
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty reportList}">
										<tr>
											<td colspan="13" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty reportList}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
				
				
				
	<div class="modal fade" style="z-index:1040;" id="showSingleOfBank" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:60%;z-index:90;">
	<div class="modal-content" style="width:950px;" id="merchantDiv">
	<button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
	
	<div class="list-table-header">代付详情</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">名称</th>
		<th width="10%">银行信息</th>
		<th width="10%">银行卡号</th>
		<th width="12%">代付金额</th>
		<th width="12%">手续费</th>
		<th width="15%">状态</th>
		</tr>
		</thead>
       <tbody id="getSingleOfBank">
         
      </tbody>

	 </table>
	</div>
	</div>
	</div>
	</div>
				
	<div class="modal fade" style="z-index:1040;" id="showSingleOfBalance" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:60%;z-index:90;">
	<div class="modal-content" style="width:950px;" id="merchantDiv">
	<button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
	
	<div class="list-table-header">代付详情</div>
	<div class="modal-body">
	<table id="sample-table-5" class="list-table" >
	  <thead>
		<tr>
		<th width="10%">名称</th>
		<th width="10%">账号</th>
		<th width="10%">代付金额</th>
		<th width="12%">状态</th>
		</tr>
		</thead>
       <tbody id="getSingleOfBalance">
         
      </tbody>

	 </table>
	</div>
	</div>
	</div>
	</div>		
				
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
</body>

<script type="text/javascript">

var winChild ;
function queryReport(obj){
	 var batNo=$(obj).parent().find('#batNo').val();
	 var type=$(obj).parent().find('#type').val();
	 var toPayType=$(obj).parent().find('#toPayType').val();
	 var url=window.Constants.ContextPath+"<%=PaymentManagerPath.BASE+ PaymentManagerPath.PAYMENTREPORTINFOS%>?batNo="+batNo+"&type="+type+"&toPayType="+toPayType; 
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
	
} 

$(function(){

	var loop =	setInterval(function() {     
	    if(winChild.closed) { 
	    	$.unblockUI();
	    }    
	}, 200);
	
	/** 缓存 **/
	var agencyReports = ${reportList};
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
	
 
});

/** 导出报表 */
$(".exportBut").click(function(){
	var paerAcctNo = $('.search-table #paerAcctNo').val().trim();
	var startCreateTime = $('.search-table #startCreateTime').val();
	var endCreateTime = $('.search-table #endCreateTime').val();
	var toPayType = $('.search-table #toPayType').val().trim();
	var type = $('.search-table #type').val().trim();
	var src ="<%= request.getContextPath() + PaymentManagerPath.BASE + PaymentManagerPath.GETREPORTEXPORT%>?paerAcctNo=" +
	paerAcctNo + "&startCreateTime=" + startCreateTime +"&endCreateTime="+endCreateTime+"&toPayType="+toPayType+"&type="+type;
	$(".exportBut").attr("href",src);
}); 


function forCloseDiv(){
	 $.unblockUI();
} 


function queryReportOfSingle(obj) {
	 var batNo=$(obj).parent().find('#batNo').val();
	 var toPayType=$(obj).parent().find('#toPayType').val();
	$.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.GETSINGLEREPORT %>', {
		  
	 		"batNo" 	: batNo,
	 		 "toPayType" :toPayType
	 		},function(data){
	 			var json = eval('(' + data + ')'); 
	 			var columns=$("#sample-table-5 #getSingleOfBank");
	 			 $(columns).html('');
	 			var Balance=$("#sample-table-5 #getSingleOfBalance");
	 			 $(Balance).html('');
	 			 var infos='';
	 			if(json.toPayType=="00"){
	 				var singleInfos=json.singleInfo;
	 				var status=null;
	 				if(singleInfos.tradeStatus=="00"){
	 					status="付款成功";
	 				}
	 				if(singleInfos.tradeStatus=="99"){
	 					status="付款失败";
	 				}
		 			
  	 				$('#showSingleOfBank').on('show.bs.modal', function () {
  	 				 });
  	 			    infos+="<tr><td>"+singleInfos.custName+"</td><td>"+singleInfos.payeeAcctBankName+"</td></td><td>"+singleInfos.recAccountNo+"</td><td>"+singleInfos.sumAmt+"</td><td>"+2.00+"</td><td>"+status+"</td></tr>";
	 		        $(columns).append(infos);
	 			 }
	 			if(json.toPayType=="01"){
	 				var  singleInfos=json.singleInfo;
	 				var status=null;
	 				
	 				if(singleInfos.tradeStatus=="00"){
	 					status="付款成功";
	 				}
	 				if(singleInfos.tradeStatus=="99"){
	 					status="付款失败";
	 				}
		 			
     	         	$('#showSingleOfBalance').on('show.bs.modal', function () {
  	 				 });
  	 			    infos+="<tr><td>"+singleInfos.custName+"</td><td>"+singleInfos.recAccountNo+"</td></td><td>"+singleInfos.sumAmt+"</td><td>"+status+"</td></tr>";
	 		       $(Balance).append(infos);
	 			 }
	 			if(json.result=="FAILE"){
  	 				infos="<tr><td colspan='9' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>";
  	 			 }
	 	       }); 
              }




</script>
</html>