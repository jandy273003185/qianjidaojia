<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller.WechatAudingPath" %>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgencyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAudingPath"%>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
<%@page import="com.qifenqian.bms.basemanager.toPayDetail.controller.ToPayDetailPath"%>
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
<title>代付详细列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=ToPayDetailPath.BASE + ToPayDetailPath.LIST %>"/>' method="post">
							<input type="hidden" name="count" id="count" value="">					
							<input type="hidden" name="trade_Status" id="trade_Status" value="${queryBean.tradeStatus }">
							<table class="search-table">
								<tr>
									<td class="td-left" >批次ID：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="orderNo" id="orderNo"  value="${queryBean.orderNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >详情ID：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="batNo" id="batNo"  value="${queryBean.batNo }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								<!--  	<td class="td-left" >创建人：</td>
									<td class="td-right" > 
										<span class="input-icon">
											 <select name="createId" id="createId" >
											 		<option value="">请选择</option>
												<c:forEach items="${createrList}" var="creater">
													<option value="${creater.userId}">${creater.userName}</option>
												</c:forEach>
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								-->	
									
							
									<td class="td-left" >付款商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="paerMerchantCode" id="paerMerchantCode"  value="${queryBean.paerMerchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">审核状态：</td>
									<td class="td-right" >								   
										 <select name="tradeStatus" id="tradeStatus" >
											  <option  value="" > 请选择 </option>
											  <option  value="01">待确认 </option>
											  <option  value="02">已确认  </option>
											  <option  value="03">已提交 </option>
											  <option  value="04">提交成功 </option>
											  <option  value="05">提交失败 </option>
											  <option  value="00">代付成功 </option>
											  <option  value="06">代付处理中</option>
											  <option  value="99">代付失败 </option>
										 </select>
									    <label class="label-tips" id=""></label>
									</td>
									
									<td class="td-left">创建时间：</td>
									 <td class="td-right">
									 <input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								    </td>
									
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=ToPayDetailPath.BASE + ToPayDetailPath.LIST%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=ToPayDetailPath.BASE + ToPayDetailPath.EXPORTDETAIL%>">
												<span class="input-group-btn" style="display:inline;">
													<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
												</span>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代付详细列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="7%">批次ID</th>
										<th width="7%">详情ID</th>
										<th width="7%">代付商户编号</th>
										<th width="10%">代付商户名称</th>									
										<th width="5%">代付金额</th>
										<th width="5%">手续费</th>
										<th width="10%">创建时间</th>
									    <th width="7%">交易状态</th>
										<!-- <th width="14%">操作</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${audingList}" var="payment" varStatus="i">
										<tr class="payment" id="payment">
											<td>${payment.orderNo }</td>
											<td>${payment.batNo }</td>
											<td>${payment.paerMerchantCode }</td>
											<td>${payment.custName }</td>																			
											<td>${payment.transAmt }</td>
											<td>${payment.singleFeeAmt }</td>
											<td><fmt:formatDate value="${payment.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
                                           	<td>
                                           		<c:if test="${payment.tradeStatus =='01'}">
													<font color="blue">待确认</font>	
												</c:if>
												<c:if test="${payment.tradeStatus =='02'}">
													<font color="green">已确认</font>		
												</c:if>
												<c:if test="${payment.tradeStatus =='03'}">
													<font color="red">已提交</font>
												</c:if>	
												<c:if test="${payment.tradeStatus =='04'}">
													<font color="red">提交成功</font>
												</c:if>
												<c:if test="${payment.tradeStatus =='05'}">
													<font color="red">提交失败</font>
												</c:if>
												<c:if test="${payment.tradeStatus =='06'}">
													<font color="green">代付处理中</font>
												</c:if>
												<c:if test="${payment.tradeStatus =='00'}">
													<font color="green">代付成功</font>
												</c:if>	
												<c:if test="${payment.tradeStatus =='99'}">
													<font color="red">代付失败</font>
												</c:if>												
										 	</td>
											<%-- <td>	
										 	    <input type="hidden" name="batNo" id="batNo" value="${payment.batNo}">
										 	    <button type="button" onclick="batchAuditPayment(this,'edit','Clste')"   class="btn btn-primary btn-xs qifenqian_view_tc updateMerchant1">查看详情</button>													                                       
											</td> --%>

										</tr>
									</c:forEach>
									<c:if test="${empty audingList}">
										<tr>
											<td colspan="13" align="center">
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

/** 清空按钮 **/
$(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
});

//导出数据
$('.exportBut').click(function(){
	var orderNo = $('.search-table #orderNo').val();
	var batNo = $('.search-table #batNo').val();
	var paerMerchantCode = $('.search-table #paerMerchantCode').val();
	var tradeStatus = $('.search-table #tradeStatus').val();
	var startCreateTime = $(".search-table #startCreateTime").val();
	var endCreateTime = $(".search-table #endCreateTime").val();
	var src ="<%= request.getContextPath()+ ToPayDetailPath.BASE+ToPayDetailPath.EXPORTDETAIL%>?batNo="+
	 batNo+
	"&orderNo="+
	orderNo+
    "&paerMerchantCode="+
    paerMerchantCode+
	"&tradeStatus="+
	tradeStatus+
	"&startCreateTime="+
	startCreateTime+
	"&endCreateTime="+
	endCreateTime;
	$(".exportBut").attr("href",src);
	
});
	
</script>
</html>