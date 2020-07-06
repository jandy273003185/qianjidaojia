<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.sns.redpacket.RedPacketInfoPath"%>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>红包明细查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body>
<%@ include file="/include/alert.jsp"%>
		      <div class="modal-content" style="width: 100%;">
		         <div class="modal-body">
		     	<form  id="tradeForm" action='<c:url value="<%=RedPacketInfoPath.BASE + RedPacketInfoPath.QUERY_REDPACKET_DETAIL %>"/>' method="post">
					<table class="search-table"  style="border:none;" >	
							<tr>							    																																
								<td class="td-left" >客户手机号</td>
								<td class="td-right">
									<span class="input-icon">
										<input type="hidden"  name="redEnvId" id="redEnvId" value="${queryBean.redEnvId}" >
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
									<th>客户手机号</th>
									<th>客户手机号</th>
									<th>抢红包时间</th>
									<th>金额</th>
									<th>是否手气最佳</th>	
									<th>入账状态</th>	
									<th>入账订单号</th>											
									<th>入账提交时间</th>	
									<th>最后更新时间</th>
								</tr>
							</thead>
							<tbody id="impeachData">
								<c:forEach items="${detailList}" var="detail" >
									<tr class="detail" >
										<td>${detail.custId}</td>
										<td>${detail.custName}</td>
										<td>${detail.pickTime}</td>
										<td>${detail.pickAmt}</td>	
										<td>${detail.isLucky}</td>
										<td>${detail.inOrderState}</td>
										<td>${detail.inOrderId}</td>
										<td>${detail.inSubmitTime}</td>
										<td>${detail.modifyTime}</td>
									</tr>											
								</c:forEach>
								<c:if test="${empty detailList}">
									<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody> 
						</table>
					</div>			
						<c:if test="${not empty detailList}">														
							<%@ include file="/include/page.jsp"%>		
						</c:if>	
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-primary addadBtn"  id="closeWindow02">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
<script type="text/javascript">
	$("#closeWindow02").click(function(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();	
		window.close();
		});
	
	$('.clearRedPacketDetail').click(function(){
		$('.search-table #custId').val('');
		$('.search-table #inOrderId').val('');
	});
</script>
</body>
</html>					