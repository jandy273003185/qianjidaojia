<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.qifenqian.bms.meeting.redPacket.controller.RedPacketPath"%>
<html>
<head>
<meta charset="utf-8" />
<title>红包查询</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}
</style>
</head>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script>
<script type="text/javascript">
function loadredPacket(){
	$(".search-table #status").val($(".search-table #statusTemp").val());
	$(".search-table #redPacketType").val($(".search-table #redPacketTypeTemp").val());
}

$(function() {
	
	var redPacketList = ${redPacketBillList};
	var redPacketTrList = $("tr.redPacket");
	
	$.each(redPacketList, function(i, value) {
		$.data(redPacketTrList[i], "redPacket", value);
	});
	
	$(".clearRedPacket").click(function(){
		$(".search-table #custName").val('');
		$(".search-table #redPacketName").val('');
		$(".search-table #redPacketType").val('');
		$(".search-table #status").val('');
		$(".search-table #sendBeginTime").val('');
		$(".search-table #sendEndTime").val('');
		$(".search-table #receiveBeginTime").val('');
		$(".search-table #receiveEndTime").val('');
		$(".search-table #redPacketSn").val('');
	});
	
	$(".searchButton").click(function(){
		var sendBeginTime =$("#sendBeginTime").val();
		var sendEndTime= $("#sendEndTime").val();
		if("" != sendBeginTime && "" != sendEndTime && sendBeginTime > sendEndTime) 
		{
			$.gyzbadmin.alertFailure("发送时间结束日期不能小于开始日期");
			return false;
		}
		
		var receiveBeginTime =$("#receiveBeginTime").val();
		var receiveEndTime= $("#receiveEndTime").val();
		if("" != receiveBeginTime && "" != receiveEndTime && receiveBeginTime > receiveEndTime) 
		{
			$.gyzbadmin.alertFailure("领取时间结束日期不能小于开始日期");
			return false;
		}
	});
	
	
	$(".exportBut").click(function(){
		
		var custName = $("#custName").val();
		var mobile = $("#mobile").val();
		var redPacketName = $("#redPacketName").val();
		var redPacketType = $("#redPacketType").val();
		var status = $("#status").val();
		var redPacketSn = $("#redPacketSn").val();
		var sendBeginTime = $("#sendBeginTime").val();
		var sendEndTime = $("#sendEndTime").val();
		var receiveBeginTime = $("#receiveBeginTime").val();
		var receiveEndTime = $("#receiveEndTime").val();
		
		var src ="<%= request.getContextPath()+ RedPacketPath.BASE+RedPacketPath.EXPORT%>?custName="+
			custName+
			"&mobile="+
			mobile+
			"&redPacketName="+
			redPacketName+
			"&redPacketType="+
			redPacketType+
			"&status="+
			status+
			"&redPacketSn="+
			redPacketSn+
			"&sendBeginTime="+
			sendBeginTime+
			"&sendEndTime="+
			sendEndTime+
			"&receiveBeginTime="+
			receiveBeginTime+
			"&receiveEndTime="+receiveEndTime;
		
		$(".exportBut").attr("href",src);
	});
	
	
});
</script>

<body onload="loadredPacket()">
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
							<form action='<c:url value="<%=RedPacketPath.BASE + RedPacketPath.LIST%>"/>' method="post">
								<table class="search-table">
									<tr>
										<td class="td-left">客户姓名</td>
										<td class="td-right">
										<span class="input-icon">
											<input type="text" id="custName" name="custName"  value="${redPacket.custName }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
										<td class="td-left">客户手机号码</td>
										<td class="td-right" >
										<span class="input-icon">
											<input type="text" id="mobile" name="mobile"  value="${redPacket.mobile }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
										<td class="td-left">红包名称</td>
										<td class="td-right">
										<span class="input-icon">
											<input type="text" id="redPacketName" name="redPacketName"  value="${redPacket.redPacketName }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
									</tr>
									<tr>
										<td class="td-left">红包类型</td>
										<td class="td-right">
											<input type="hidden" id="redPacketTypeTemp" name="redPacketTypeTemp" value="${redPacket.redPacketType }" /> 
											<select id="redPacketType" name="redPacketType">
												<option value="">- 请选择 -</option>
												<option value="REGISTER">注册红包</option>
												<option value="LOTTERY">抽奖红包</option>
											</select>
										</td>
										<td class="td-left">红包状态</td>
										<td class="td-right">
										    <input type="hidden" id="statusTemp" name="statusTemp"  value="${redPacket.status}"/>
											<select id="status" name="status">
												<option value="">- 请选择 -</option>
												<option value="0">- 未领取 -</option>
												<option value="1">- 已领取 -</option>
												<option value="2">- 已回收 -</option>
												<option value="3">- 领取中 -</option>
											</select>
										</td>
										<td class="td-left">红包流水号</td>
										<td class="td-right">
										<span class="input-icon">
											<input type="text" id="redPacketSn" name="redPacketSn"  value="${redPacket.redPacketSn }"/>
											<i class="icon-leaf blue"></i>
										</span> 
										</td>
									</tr>
									<tr>
									<td class="td-left">发送时间</td>
									<td class="td-right">
										 <input type="text" name="sendBeginTime"  id="sendBeginTime" readonly="readonly"  value="${redPacket.sendBeginTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="sendEndTime" id="sendEndTime" readonly="readonly" value="${redPacket.sendEndTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left">领取时间</td>
									<td class="td-right">
										 <input type="text" name="receiveBeginTime"  id="receiveBeginTime" readonly="readonly"  value="${redPacket.receiveBeginTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="receiveEndTime" id="receiveEndTime" readonly="readonly" value="${redPacket.receiveEndTime }" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
											<button type="submit" class="btn btn-purple btn-sm searchButton">
												查询 <i class="icon-search icon-on-right bigger-110"></i>
											</button>
											<button class="btn btn-purple btn-sm btn-margin clearRedPacket">
												清空 <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=RedPacketPath.BASE + RedPacketPath.EXPORT %>">
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span>
											</gyzbadmin:function>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">红包列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout: fixed;">
									<thead>
										<tr>
											<th>红包流水号</th>
											<th>客户名称</th>
											<th>客户手机号码</th>
											<th>红包类型</th>
											<th>红包名称</th>
											<th>红包金额</th>
											<th>红包状态</th>
											<th>备注</th>
											<th>发送时间</th>
											<th>领取时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${redPacketBillList}" var="redPacket">
											<tr class="redPacket">
												<td>${redPacket.redPacketSn }</td>
												<td>${redPacket.custName }</td>
												<td>${redPacket.mobile }</td>
												<td>
													<c:if test="${redPacket.redPacketType =='REGISTER' }">注册红包</c:if>
													<c:if test="${redPacket.redPacketType =='LOTTERY' }">抽奖红包</c:if>
												</td>
												<td>${redPacket.redPacketName }</td>
												<td>${redPacket.redPacketAmt }</td>
												<td>
													<c:if test="${redPacket.status =='0' }">未领取</c:if>
													<c:if test="${redPacket.status =='1' }">已领取</c:if>
													<c:if test="${redPacket.status =='2' }">已回收</c:if>
													<c:if test="${redPacket.status =='3' }">领取中</c:if>
												</td>
												<td>${redPacket.memo }</td>
												<td>${redPacket.createTime}</td>
												<td>
													${redPacket.modifyTime }
												</td>
												
											</tr>
										</c:forEach>
										<c:if test="${empty redPacketBillList}">
											<tr>
												<td colspan="9" align="center">
												<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty redPacketBillList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<%@ include file="/include/bottom.jsp"%>
			</div>
			<%@ include file="/include/setting.jsp"%>
		</div>
		<%@ include file="/include/up.jsp"%>
	</div>
	
</body>
</html>