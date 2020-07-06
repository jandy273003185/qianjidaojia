<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.agent.controller.AgentSettlePath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>协议管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	function loadQueryBean(){
		$("#status").val($("#hiddenStatus").val());
	}
	
	$(function(){
		$(".clearBtn").click(function(){
			$("#settleId,#agentId,#status").val("");
		});
		$("#detail").click(function(){
			$.post(window.Constants.ContextPath + '<%=AgentSettlePath.BASE + AgentSettlePath.DETAIL%>',{
				'settleId':$(this).parents("tr").find("#settleId").text()
			},function(data){
				$.unblockUI();
				
				if(data.result == 'SUCCESS'){
					var detailBean =  data.detailBean;
					
					$("#detailModal").find("#settleId").val(detailBean.settleId);
					$("#detailModal").find("#agentId").val(detailBean.agentId);
					$("#detailModal").find("#merchantId").val(detailBean.merchantId);
					$("#detailModal").find("#channelCode").val(detailBean.channelCode);
					$("#detailModal").find("#transTotalFee").val(detailBean.transTotalFee);
					$("#detailModal").find("#instDate").val(getMyDate(detailBean.instDate));
					$("#detailModal").find("#instDatetime").val(getMyDate(detailBean.instDatetime));
				} else {
					$('#detailModal').modal('hide');
					$.gyzbadmin.alertFailure('详情查询异常:' + data.message);
				}
			},'json'
			);
		});


		$(".exportBut").click(function(){
			var agentId = $("#agentId").val();
			var settleId = $("#settleId").val();
			var status = $("#status").val();
			var src ="<%= request.getContextPath()+ AgentSettlePath.BASE + AgentSettlePath.AGENTSETTLEEXPORT%>?agentId="+
			agentId+"&settleId="+settleId+"&status="+status;
			$(".exportBut").attr("href",src);
		});
	});
	function getMyDate(str){  
	
	    var oDate = new Date(str),
	  
	   
	    oYear = oDate.getFullYear(),  
	  	
	    oMonth = oDate.getMonth()+1,  
	    oDay = oDate.getDate(),  
	    oHour = oDate.getHours(),  
	    oMin = oDate.getMinutes(),  
	    oSen = oDate.getSeconds(),  
	    oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
	    return oTime;  
	};  
	 //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }  
</script>
<body onload="loadQueryBean()">
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
							<form action='<c:url value="<%=AgentSettlePath.BASE + AgentSettlePath.AGENTSETTLELIST%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">结算编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="settleId" name="settleId" value="${queryBean.settleId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">代理商编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text" id="agentId" name="agentId" value="${queryBean.agentId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="hidden" value="${queryBean.status }" id="hiddenStatus"/>
											<select id="status"  name = "status">
												<option value="">--请选择--</option>
												<option value="INIT">--初始化--</option>
												<option value="AUDIT_PASS">--审核通过--</option>
												<option value="AUDIT_REFUSE">--审批拒绝--</option>
												<option value="SEND_INIT">--发送金蝶--</option>
												<option value="SEND_SUCC">--发送金蝶成功--</option>
												<option value="PAY_SUCC">--付款成功--</option>
												<option value="PAY_FAIL">--付款失败--</option>
												<option value="VERIFIED">--核销完成--</option>
												<option value="VERIFIED_EXCEPTION">--核销异常--</option>
												<option value="INVALID">--无效--</option>
											</select>
										</span>
									</td>
								</tr>
								
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AgentSettlePath.BASE + AgentSettlePath.AGENTSETTLELIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBtn">
												清空
											    <i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AgentSettlePath.BASE + AgentSettlePath.AGENTSETTLEEXPORT%>">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								代理商结算列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:5%;">结算编号</th>
											<th style="width:5%;">代理商编号</th>
											<th style="width:10%;">协议编号</th>
											<th style="width:5%;">结算金额</th>
											<th style="width:5%;">完成日期</th>
											<th style="width:5%;">状态</th>
											<th style="width:5%;">备注</th>
											<th style="width:5%;">生成人</th>
											<th style="width:5%;">记账日期</th>
											<th style="width:5%;">生成时间</th>
											<th style="width:5%;">复核人</th>
											<th style="width:5%;">复核时间</th>
											<th style="width:5%;">核销人</th>
											<th style="width:5%;">核销时间</th>
											<th style="width:10%;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${agentSettleList}" var="bean">
											<tr >
												<td id="settleId">${bean.settleId}</td>
												<td id="agentId">${bean.agentId}</td>
												<td id="protocolId">${bean.protocolId}</td>
												<td id="settleAmt">${bean.settleAmt}</td>
												<td>${bean.finishDate}</td>
												<td >
													<c:choose>
														<c:when test="${bean.status=='INIT'}">初始化</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='AUDIT_PASS'}">审核通过</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='AUDIT_REFUSE'}">审批拒绝</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='SEND_INIT'}">发送金蝶</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='SEND_SUCC'}">发送金蝶成功</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='PAY_SUCC'}">付款成功</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='PAY_FAIL'}">付款失败</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='VERIFIED'}">核销完成</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='VERIFIED_EXCEPTION'}">核销异常</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${bean.status=='INVALID'}">无效</c:when>
													</c:choose>
												</td>
												
												<td id="memo">${bean.memo}</td>
												<td id="instUser">${bean.instUser}</td>
												<td>${bean.instDate}</td>
												<td id="instDatetime">${bean.instDatetime}</td>
												<td id="auditUser">${bean.auditUser}</td>
												<td id="auditDatetime">${bean.auditDatetime}</td>
												<td id="verifiedUser">${bean.verifiedUser}</td>
												<td id="verifiedDatetime">${bean.verifiedDatetime}</td>
												<td>
													<gyzbadmin:function url="<%=AgentSettlePath.BASE + AgentSettlePath.DETAIL%>">
														<button type="button" class="btn btn-primary" title="Detail" data-toggle='modal' data-target="#detailModal" id = "detail">
															明细
														</button>
													</gyzbadmin:function> 
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty agentSettleList}">
											<tr><td colspan="14" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty agentSettleList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
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
	 <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">结算详情</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
						<tr class="custIdCla">
							<td class="td-left" >结算编号<span style="color:red"></span></td>
							<td class="td-right" >
								<input type="text" id="settleId" name="settleId"  style="width:80%"	readonly="readonly">
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="30%">代理商编号<span style="color:red"></span></td>
							<td class="td-right" width="70%">
								<input type="text" id="agentId" name="agentId"  style="width:80%"	readonly="readonly">
							</td>
						</tr>
							<tr class="custIdCla">
								<td class="td-left" >商户编号<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="merchantId" name="merchantId" readonly="readonly" style="width:80%"	>
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >产品渠道<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="channelCode" name="channelCode" readonly="readonly" style="width:80%"	>
									
								</td>
							</tr>
		            		<tr class="protocolIdClass">
								<td class="td-left" >应付金额<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="transTotalFee" name="transTotalFee" style="width:80%" readonly="readonly" >
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >记账日期<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="instDate" name="instDate" style="width:80%" readonly="readonly" >
									
								</td>
							</tr>
							<tr class="protocolIdClass">
								<td class="td-left" >生成时间<span style="color:red"></span></td>
								<td class="td-right" >
									<input type="text" id="instDatetime" name="instDatetime" style="width:80%" readonly="readonly" >
								</td>
							</tr>
					
						
		            </table>
		         </div>
		         <div class="modal-footer">
		        	
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		           
		         </div>
		      </div>
		     </div>
		</div>
	
</body>
</html>