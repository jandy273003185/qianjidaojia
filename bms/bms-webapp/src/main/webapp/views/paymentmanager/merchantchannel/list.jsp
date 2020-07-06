<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
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
<title>商户渠道列表</title>
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
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.MERCHANTCHANNEL %>"/>' method="post">
							<input type="hidden" name="count" id="count" value="">					
							<table class="search-table">
								<tr>
									<td class="td-left" >渠道编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="channelId" id="channelId"  value="${queryBean.channelId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								
									<td class="td-left" >渠道名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="channelName" id="channelName"  value="${queryBean.channelName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
							
									
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.MERCHANTCHANNEL%>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											 
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代付商户渠道列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="5%">渠道编号</th>
										<th width="5%">渠道名称</th>
										<th width="5%">代付费率</th>									
										<th width="5%">充值费率</th>
										<th width="7%">充值扣费方式</th>
										<th width="5%">七分钱代付充值费率</th>
									    <th width="7%">七分钱代付充值收费方式</th>
									    <th width="5%"> 描述</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${merchantChannelList}" var="merchantChannel" varStatus="i">
										<tr class="merchantChannel" id="merchantChannel">
											
											<td>${merchantChannel.channelId }</td>
											<td>${merchantChannel.channelName }</td>	
											<td>${merchantChannel.channelTopayRate }</td>																		
											<td>${merchantChannel.channelRechargeRate }</td>
											<td>
												<c:if test="${merchantChannel.channelRechargeType =='fixed'}">
													 固定
												</c:if>
												<c:if test="${merchantChannel.channelRechargeType =='rate'}">
													 费率
												</c:if>
											</td>
											<td>${merchantChannel.sevenpayRechargeRate }</td>
											<td>
												<c:if test="${merchantChannel.sevenpayRechargeType =='fixed'}">
													 固定
												</c:if>
												<c:if test="${merchantChannel.sevenpayRechargeType =='rate'}">
													 费率
												</c:if>
											</td>
											<td>${merchantChannel.description }</td>
										</tr>
									</c:forEach>
									<c:if test="${empty merchantChannelList}">
										<tr>
											<td colspan="13" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty merchantChannelList}">
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

/** 清空按钮 **/
$(".clearMerchantSearch").click(function(){
		$(':input','#merchantForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
});


</script>

</html>