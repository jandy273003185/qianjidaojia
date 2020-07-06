<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller.WechatAudingPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAudingPath"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>微商审核列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.GETPAYMENTQUERY %>"/>' method="post">
							<input type="hidden" name="count" id="count" value="">
							<table class="search-table">
								<tr>
									<td class="td-left" >交易号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="batNo" id="batNo"  value="${queryBean.batNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >代付账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="paerAcctNo" id="paerAcctNo"  value="${queryBean.paerAcctNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									
								</tr>
								
							
								
								<tr>
									
									
									<td class="td-left">代付类型：</td>
									<td class="td-right">								   
										 <select name="type" id="type" >
										  <option  value="" >请选择 </option>
										  <option  value="0">批量 </option>
										  <option  value="1">单笔 </option>
										 
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									
									<td class="td-left">状态：</td>
									<td class="td-right">								   
										 <select name="tradeStatus" id="tradeStatus" >
										  <option  value="" > 请选择 </option>
										  <option  value="00">付款成功 </option>
										  <option  value="60">银行打款成功 </option>
										  <option  value="99">付款失败 </option>
										  <option  value="06">一级审核通过 </option>
										  <option  value="08">二级审核通过 </option>
										  <option  value="92">一级审核不通过  </option>
										  <option  value="93">二级审核不通过 </option>
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
								
								
								
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.GETPAYMENTQUERY %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<!-- <span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
											</span>  -->
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代付审核列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="8%">交易号</th>
										<th width="8%">代付账号</th>
										<th width="11%">商户名称</th>
										<th width="8%">代付类型</th>
										<th width="5%">代付笔数</th>
										<th width="8%">代付金额</th>
										<th width="6%">手续费</th>
										<th width="12%">时间</th>
									    <th width="10%">状态</th>
										<th width="15%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${audingList}" var="payment" varStatus="i">
										<tr class="payment" id="payment">
											
											<td>${payment.batNo }</td>
											<td>${payment.paerAcctNo }</td>
											<td>${payment.custName}</td>
											<td><c:choose>
							               <c:when test="${payment.type =='0'}">  									  
							                                                批量          								  
							              </c:when>
						                   <c:when test="${payment.type =='1'}">  									  
							                                                单笔              								  
							              </c:when>
								          </c:choose>
								         </td>
											
											<td>${payment.sumCount }</td>
											<td>${payment.sumAmt }</td>
											<td>${payment.serviceFee }</td>
											<td><fmt:formatDate value="${payment.createTime }" pattern="yyyy-mm-dd hh:mm:ss"/></td>


                                         <td>
										<c:if test="${payment.tradeStatus =='00'}">
											代付成功
										</c:if>
										<c:if test="${payment.tradeStatus =='04'}">
											前台审核通过
										</c:if>
										<c:if test="${payment.tradeStatus =='99'}">
											代付失败
										</c:if>
										<c:if test="${payment.tradeStatus =='06'}">
											一级审核通过	
										</c:if>
										<c:if test="${payment.tradeStatus =='08'}">
											二级审核通过	
										</c:if>
										 <c:if test="${payment.tradeStatus =='90'}">
											交易异常
										</c:if>
							            <c:if test="${payment.tradeStatus =='92'}">
											一级审核不通过 	
										</c:if>
										 <c:if test="${payment.tradeStatus =='93'}">
											二级审核不通过 	
										</c:if>
                                        <c:if test="${payment.tradeStatus =='60'}">
											银行打款成功
										</c:if>
										 <c:if test="${payment.tradeStatus =='07'}">
											已撤销
										</c:if>
										</td>
									
										
											
										       <td>
												<input type="hidden"  name="batNo" id="batNo" value="${payment.batNo }">
												<input type="hidden"  name="type" id="type" value="${payment.type}">
												<input type="hidden"  name="toPayType" id="toPayType" value="${payment.toPayType}">
												<c:if test="${payment.type =='0'}">
												<a  style="text-align: center" onclick="queryReport(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"   id="queryWaterBtn"  class="btn btn-primary btn-xs"  >批量详情</button>
												</a> 
												</c:if>
												<c:if test="${payment.type =='1'}">
												<c:if test="${payment.toPayType =='00'}">
												<a  onclick="queryReportOfSingle(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"    data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"  >单笔详情</button>
												</a> 
												</c:if>
												<c:if test="${payment.toPayType =='01'}">
												  <a  onclick="queryReportOfSingle(this)" data-toggle='modal'  class="tooltip-success detailLink">
													<button type="button"    data-toggle='modal' data-target="#updateMerchantModel" class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1"  >单笔详情</button>
												</a> 
												</c:if>
												</c:if>
											  </td>




										</tr>
									</c:forEach>
									<c:if test="${empty audingList}">
										<tr>
											<td colspan="9" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty audingList}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
			
			<div class="modal fade" style="z-index: 1040;"id="updateMerchantModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 60%; z-index: 90;">
						<div class="modal-content" style="width: 1000px;" id="merchantDiv">
							<div class="modal-header" style="background-color: 0099CC">
								<input type="hidden" id="authId_update" /> 
								<input type="hidden" id="single_flag" value="" />
								<input type="hidden" id="audit_flag" value="" />
								<button type="button" class="close" data-dismiss="modal"
									id="updateMerchantClose" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">代付详情</h4>
							</div>
							<div class="modal-body">
								<table id="sample-table-2" class="list-table">
									<tr>
										<td colspan="4" class="headlerPreview"></td>
									</tr>
									<tr>
										<td class="td-left" width="18%">商户名称：</td>
										<td class="td-right" width="32%"><input type="text"
											id="custName_audit" readonly="readonly" name="custName_audit"
											maxlength="100" placeholder="商户名称" style="width: 90%">

										</td>
										<td class="td-left" width="18%">代付金额：</td>
										<td class="td-right" width="32%"><input type="text"
											id="sumAmt" name="sumAmt" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>
										<td class="td-left">代付账号：</td>
										<td class="td-right"><input type="text" id="paerAcctNo_1"
											name="paerAcctNo_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付笔数：</td>
										<td class="td-right"><input type="text" id="sumCount_1"
											name="sumCount_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									<tr>
										<td class="td-left">手续费：</td>
										<td class="td-right"><input type="text" id="serviceFee_1"
											name="serviceFee_1" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付类型：</td>
										<td class="td-right">
											<input type="hidden" id="toPayType_1"
												name="toPayType_1" placeholder="代付类型" readonly="readonly"
												style="width: 90%">
												
												<input type="text" id="toPayType_2"
												name="toPayType_2" placeholder="代付类型" readonly="readonly"
												style="width: 90%">
										</td>


									</tr>
									<tr>


										<td class="td-left">代付方式：</td>
										<td class="td-right"><input type="text" id="pay_1"
											name="pay_1" placeholder="代付方式" readonly="readonly"
											style="width: 90%"></td>

										<td class="td-left">实付总额：</td>
										<td class="td-right"><input type="text" id="totalMoney"
											name="totalMoney" placeholder="实付总额" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>

										<td class="td-left">交易号：</td>
										<td class="td-right"><input type="text" id="bataNo_1"
											name="bataNo_1" placeholder="交易号" readonly="readonly"
											style="width: 90%"></td>

										<td class="td-left">时间：</td>
										<td class="td-right"><input type="text" id="createTime"
											name="createTime" placeholder="时间" readonly="readonly"
											style="width: 90%"></td>


									</tr>

									<tr >
										<td class="td-left">状态：</td>
										<td class="td-right"><input type="text" id="tradeStatus" name="tradeStatus" placeholder="状态" readonly="readonly" style="width: 90%">
									</td>

									</tr>


								</table>
								

								

							</div>
							
							
						</div>
						<!-- /.modal-content -->

					</div>

				</div>


	
	<!-- 底部-->
	<%@ include file="/include/bottom.jsp"%>
	</div><!-- /.main-content -->
	<!-- 设置 -->
	<%@ include file="/include/setting.jsp"%>
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog" id="imageDiv" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
	</div> 
	
	
	</div>
	
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
	
</script>


<script type="text/javascript">
$(function(){
	
	var loop =	setInterval(function() {     
	    if(winChild.closed) { 
	    	$.unblockUI();
	    }    
	}, 200);
	
	
	/** 缓存 **/
	var custIds = ${audingList};
	var custId = $("tr.payment");
	$.each(custIds,function(i,value){
		$.data(custId[i],"payment",value);
	});
  $(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
  
  /** 导出报表 */
  $(".exportBut").click(function(){
	var batNo = $('.search-table #batNo').val().trim();
  	var paerAcctNo = $('.search-table #paerAcctNo').val().trim();
  	var startCreateTime = $('.search-table #startCreateTime').val();
  	var endCreateTime = $('.search-table #endCreateTime').val();
  	var toPayType = $('.search-table #toPayType').val().trim();
  	var type = $('.search-table #type').val().trim();
  	var src ="<%= request.getContextPath() + PaymentManagerPath.BASE + PaymentManagerPath.GETREPORTEXPORT%>?paerAcctNo=" +
  	paerAcctNo + "&startCreateTime=" + startCreateTime +"&endCreateTime="+endCreateTime+"&toPayType="+toPayType+"&type="+type+"&batNo="+batNo;
  	$(".exportBut").attr("href",src);
  });	
	
});
 
function forCloseDiv(){
	 $.unblockUI();
} 
 
 
function queryReportOfSingle(obj) {
	   var batNo=$(obj).parent().find('#batNo').val();
	   var toPayType=$(obj).parent().find('#toPayType').val();
		$.post(window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.GETSINGLETOPAYINFO%>',{
	 			"batNo":batNo,
	 			"toPayType":toPayType
	 			
	 			
	 	},function(data){
	 		if(data.result=='SUCCESS'){
	 			   
					$("#updateMerchantModel #custName_audit").val(data.Info.custName);
					$("#updateMerchantModel #sumAmt").val(data.Info.sumAmt);
					$("#updateMerchantModel #paerAcctNo_1").val(data.Info.paerAcctNo);
					$("#updateMerchantModel #sumCount_1").val(1);
					$("#updateMerchantModel #serviceFee_1").val(data.Info.feeAmt);
					$("#updateMerchantModel #toPayType_1").val(data.Info.toPayType);
					if("00"==data.Info.toPayType){
						$("#updateMerchantModel #toPayType_2").val("银行卡");
					}else{
						$("#updateMerchantModel #toPayType_2").val("余额");
					}
					
					$("#updateMerchantModel #pay_1").val("单笔");
					$("#updateMerchantModel #bataNo_1").val(data.Info.batNo);
					$("#updateMerchantModel #createTime").val(data.Info.startCreateTime);
					var  serviceFee=Number(data.Info.feeAmt);
					var  sumAmt=Number(data.Info.sumAmt);
					$("#updateMerchantModel #totalMoney").val(serviceFee+sumAmt);
					$("#updateMerchantModel #tradeStatus").val(data.Info.tradeStatus);
					
					
					
	 		}else{
				$.gyzbadmin.alertFailure("数据查询失败！"+data.message);
			}
	 	},'json'); 
		    
	}	 
	 
</script>
</html>