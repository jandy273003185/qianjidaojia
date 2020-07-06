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
	<title>红包列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadRedPacket()">
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
					<!-- 查询条件 -->
					<form id="searchForm" action='<c:url value="<%=RedPacketInfoPath.BASE + RedPacketInfoPath.LIST %>"/>' method="post">
						<table class="search-table" >
							<tr>
								<td class="td-left" >红包编号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="redEnvId" id="redEnvId" value="${queryBean.redEnvId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left">支付订单号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="orderId" id="orderId" value="${queryBean.orderId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >红包种类</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="redEnvTypeTemp" value="${queryBean.redEnvType}">
											<select id="redEnvType" name="redEnvType">
												<option value="">- 请选择 -</option>
												<option value="0">- 随机金额 -</option>
												<option value="1">- 固定金额 -</option>
											</select>
									</span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >创建人手机号</td>
								<td class="td-right"  >
									<span class="input-icon">
										<input type="text"  name="createId" id="createId" value="${queryBean.createId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >支付状态</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="orderStateTemp" value="${queryBean.orderState}">
											<select id="orderState" name="orderState">
												<option value="">- 请选择 -</option>
												<option value="00">- 成功 -</option>
												<option value="01">- 待支付 -</option>
												<option value="02">- 提交处理 -</option>
												<option value="03">- 处理失败 -</option>
												<option value="99">- 取消 -</option>
												<option value="90">- 未明 -</option>
											</select>
									</span>
								</td>
								<td class="td-left" >创建时间</td>
								<td class="td-right">
										<input type="text"  name="createBeginTime" id="createBeginTime" value="${queryBean.createBeginTime}"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
										-
										<input type="text"  name="createEndTime" id="createEndTime" value="${queryBean.createEndTime}"  readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
							</tr>
							<tr>
								<td class="td-left">退款订单号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="text"  name="expiredBalProcOrderId" id="expiredBalProcOrderId" value="${queryBean.expiredBalProcOrderId }"  style="width:100%;">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >退款状态</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="expiredBalProcStatusTemp" value="${queryBean.expiredBalProcStatus}">
											<select id="expiredBalProcStatus" name="expiredBalProcStatus">
												<option value="">- 请选择 -</option>
												<option value="00">- 退款成功 -</option>
												<option value="01">- 待退款 -</option>
												<option value="02">- 退款失败 -</option>
											</select>
									</span>
								</td>
								<td class="td-left" >过期状态</td>
								<td class="td-right">
									<span class="input-icon">
											<input type="hidden" id="expiredStatusTemp" value="${queryBean.expiredStatus}">
											<select id="expiredStatus" name="expiredStatus">
												<option value="">- 请选择 -</option>
												<option value="0">- 未过期 -</option>
												<option value="1">- 已过期 -</option>
											</select>
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center" >
									<span class="input-group-btn" style="display:inline;">
										<gyzbadmin:function url="<%=RedPacketInfoPath.BASE + RedPacketInfoPath.LIST %>">
											<button type="button" class="btn btn-purple btn-sm buttonSearch">
													查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											<button class="btn btn-purple btn-sm btn-margin clearRedPacket" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</gyzbadmin:function>
									</span>
								</td>
							</tr>
						</table>
						</form>
						<div class="list-table-header" >
							红包列表
						</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th width="8%">红包编号</th>
										<th width="8%">支付订单号</th>
										<th width="6%">创建人</th>
										<th width="4%">总金额</th>
										<th width="2%">总个数</th>
										<th width="6%">红包种类</th>
										<th width="8%">创建时间</th>
										<th width="4%">支付状态</th>
										<th width="2%">已领个数</th>
										<th width="4%">剩余金额</th>
										<th width="6%">过期状态</th>
										<th width="8%">退款订单号</th>
										<th width="8%">退款提交时间</th>
										<th width="6%">红包退款状态</th>
										<th width="6%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${redEnvelopeList}" var="redEnvelope">
										<tr class="redEnvelope">
											<td>${redEnvelope.redEnvId}</td>
											<td>${redEnvelope.orderId}</td>
											<td>${redEnvelope.createId}</td>
											<td>${redEnvelope.redEnvAmt}</td>
											<td>${redEnvelope.redEnvCount}</td>
											<td>${redEnvelope.redEnvType}</td>
											<td>${redEnvelope.createTime}</td>
											<td>${redEnvelope.orderState}</td>
											<td>${redEnvelope.pickCount}</td>
											<td>${redEnvelope.expiredLeftAmt}</td>
											<td>${redEnvelope.expiredStatus}</td>
											<td>${redEnvelope.expiredBalProcOrderId}</td>
											<td>${redEnvelope.expiredBalSubmitTime}</td>
											<td>${redEnvelope.expiredBalProcStatus}</td>
											<td>
												<gyzbadmin:function url="<%=RedPacketInfoPath.BASE + RedPacketInfoPath.QUERY_REDPACKET_DETAIL%>">
												<input type="hidden" id="redEnvId" value="${redEnvelope.redEnvId}">
													<a href="btnEmail2" onclick="queryRedPacketDetail(this)"  data-toggle='modal' class="tooltip-success"  data-rel="tooltip" title="红包详情">
														<button type="button"   id="btnEmail2"  class="btn btn-primary btn-xs"  >红包详情</button>	
													</a>
												</gyzbadmin:function>	
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty redEnvelopeList}">
										<tr><td colspan="13" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty redEnvelopeList}">
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

function loadRedPacket(){
	$(".search-table #expiredBalProcStatus").val($(".search-table #expiredBalProcStatusTemp").val());
	$(".search-table #expiredStatus").val($(".search-table #expiredStatusTemp").val());
	$(".search-table #redEnvType").val($(".search-table #redEnvTypeTemp").val());
	$(".search-table #orderState").val($(".search-table #orderStateTemp").val());
}

jQuery(function(){
	
	var loop =	setInterval(function() {     
	    if(winChild.closed) { 
	    	$.unblockUI();
	    }    
	}, 200);
	
	var redEnvelopeListJson = ${redEnvelopeList};
	var redEnvelopeTrList = $('tr.redEnvelope');
	$.each(redEnvelopeListJson, function(idx, obj){
		$.data(redEnvelopeTrList[idx], 'redEnvelope', obj);
	});	
	
	$('.clearRedPacket').click(function(){
		$(':input','#searchForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	
	$('.buttonSearch').click(function(){
		var startDate = $("#createBeginTime").val();
		var endDate= $("#createEndTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) {
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		$("#searchForm").submit();
	});
});

 function queryRedPacketDetail(obj){
	var redEnvId = $(obj).prev().val();
	 var url=window.Constants.ContextPath+"<%=RedPacketInfoPath.BASE + RedPacketInfoPath.QUERY_REDPACKET_DETAIL%>?redEnvId="+redEnvId; 
     var name="newwindow";                        
     var iWidth=1200;                          
     var iHeight=500;    
     var iTop = (window.screen.availHeight-30-iHeight)/2;
     var iLeft = (window.screen.availWidth-10-iWidth)/2;
     var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
   	winChild = window.open(url, name,params);
   	$.blockUI();
  };
  
  function forCloseDiv(){
		 $.unblockUI();
	} 
</script>
</html>