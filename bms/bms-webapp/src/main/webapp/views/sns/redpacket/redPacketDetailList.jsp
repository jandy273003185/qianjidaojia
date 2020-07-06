<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.sns.redpacket.RedPacketInfoPath" %> 
<html>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script>

<head>
	<meta charset="utf-8" />
	<title>红包明细列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
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
				 <div class="modal-body">
		     	<form id="searchForm" action='<c:url value="<%=RedPacketInfoPath.BASE + RedPacketInfoPath.REDPACKET_DETAIL_LIST %>"/>' method="post">
					<table class="search-table">	
							<tr>							    																																
								<td class="td-left">红包编号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="redEnvId" id="redEnvId" value="${queryBean.redEnvId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" width="18%">抢红包时间</td>
								<td class="td-right">
										<input type="text"  name="pickBeginTime" id="pickBeginTime" value="${queryBean.pickBeginTime}"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="pickEndTime" id="pickEndTime" value="${queryBean.pickEndTime}"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
							</tr>	
							<tr>
								<td class="td-left" width="18%">客户手机号</td>
								<td class="td-right" width="32%">
									<span class="input-icon">
										<input type="text"  name="custId" id="custId" value="${queryBean.custId}"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left">入账订单号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="inOrderId" id="inOrderId" value="${queryBean.inOrderId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
							</tr>	
							<tr>
								<td colspan="4" align="center">
									<button type="submit"  class="btn btn-purple btn-sm serchSubmit" >
										查询
										<i class="icon-search icon-on-right bigger-110"></i>
									</button>
									<button class="btn btn-purple btn-sm btn-margin clearRedPacketDetail" >
										清空
										<i class=" icon-move icon-on-right bigger-110"></i>
									</button>	
								</td>													
							</tr>
						</table>
					</form>	
					<div class="list-table-header">红包详细信息</div>						
		         	<div class="table-responsive" id="createtable">
					 	<table id="sample-table-2" class="list-table">
							<thead>
								<tr>
									<th>红包编号</th>
									<th>客户手机号</th>
									<th>客户姓名</th>
									<th>抢红包时间</th>
									<th>金额</th>
									<th>是否手气最佳</th>	
									<th>入账状态</th>	
									<th>入账订单号</th>	
									<th>入账信息</th>											
									<th>入账提交时间</th>	
									<th>最后更新时间</th>
								</tr>
							</thead>
							<tbody id="impeachData">
								<c:forEach items="${redDetailList}" var="redDetail" >
									<tr class="redDetail" >
									    <td>${redDetail.redEnvId}</td>
										<td>${redDetail.custId}</td>
										<td>${redDetail.custName}</td>
										<td>${redDetail.pickTime}</td>
										<td>${redDetail.pickAmt}</td>	
										<td>${redDetail.isLucky}</td>
										<td>${redDetail.inOrderState}</td>
										<td>${redDetail.inOrderId}</td>
										<td>${redDetail.inAccountFailInfo}</td>
										<td>${redDetail.inSubmitTime}</td>
										<td>${redDetail.modifyTime}</td>
									</tr>											
								</c:forEach>
								<c:if test="${empty redDetailList}">
									<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody> 
						</table>
						<c:if test="${not empty redDetailList}">														
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
<script type="text/javascript">
jQuery(function(){
	
	var redDetailListJson = ${redDetailList};
	var redDetailTrList = $('tr.redDetail');
	$.each(redDetailListJson, function(idx, obj){
		$.data(redDetailTrList[idx], 'redDetail', obj);
	});	
	
	$('.clearRedPacketDetail').click(function(){
		$(':input','#searchForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	
	$('.serchSubmit').click(function(){
		var startDate = $("#pickBeginTime").val();
		var endDate= $("#pickEndTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) {
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		$("#searchForm").submit();
	});
});
</script>
</html>