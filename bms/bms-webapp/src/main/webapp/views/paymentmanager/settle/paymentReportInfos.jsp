<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page
import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgenReportPath" %>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代理商商户报表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}

li {
	list-style-type: none;
}

.displayUl {
	display: none;
}
</style>
</head>
<body >
<%@ include file="/include/alert.jsp"%>
	<div class="modal-content" style="width: 100%;">
		<input type="hidden" name="idval" id="idval" />
		<div class="">
			
				
			<div class="list-table-header">代付详情</div>
			<div class="table-responsive" id="createtable">
				<input type="hidden" id="toPayType" value="${toPayType}">
				<table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th>名称</th>
							<th>银行信息</th>
							<th>银行卡号</th>
							<th>代付金额</th>
							<th>手续费</th>
							<th>状态</th>
							
						</tr>
					</thead>

					<tbody id="impeachData">
					  <c:if test="${toPayType=='00'}">
						<c:forEach items="${paymentList}" var="result">
							<tr class="result">
								<td>${result.recAccountName}</td>
								<td>${result.recCardName}</td>
								<td>${result.recAccountNo}</td>
								<td>${result.transAmt}</td>
								<td>${result.payFee}</td>
								<td>
								<c:choose>
							    <c:when test="${result.tradeStatus =='00'}">  									  
							                                     交易成功              								  
							     </c:when>
							     <c:when test="${result.tradeStatus =='04'}">  									  
							                                前台审核通过         								  
							     </c:when>
						        <c:when test="${result.tradeStatus =='99'}">  									  
							                           交易失败                  								  
							    </c:when>
							    <c:when test="${result.tradeStatus =='06'}">  									  
							                         发送银行成功                    								  
							    </c:when>
							    <c:when test="${result.tradeStatus =='08'}">  									  
							                        清洁算审核未通过             								  
							    </c:when>
							     <c:when test="${result.tradeStatus =='90'}">  									  
							                          交易异常              								  
							    </c:when>
							    <c:when test="${result.tradeStatus =='05'}">  									  
							                      清洁算审核通过	                    								  
							    </c:when>
							    <c:when test="${result.tradeStatus =='09'}">  									  
							                          财务审核不通过 	               								  
							    </c:when>
							    <c:when test="${result.tradeStatus =='94'}">  									  
							                        撤销失败       								  
							    </c:when>
							     <c:when test="${result.tradeStatus =='97'}">  									  
							                        撤销异常       								  
							    </c:when>
							    
								</c:choose>
								</td>
								
							</tr>
						</c:forEach>
						</c:if>
						 
						<c:if test="${empty paymentList}">
							<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<c:if test="${not empty paymentList}">														
				<%@ include file="/include/page.jsp"%>		
			</c:if>
		</div>
		
	</div>
	<!-- /.modal-content -->
	<script type="text/javascript">
	
	
	function closeWindow(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();
		 window.close(); 
	}
	    
     $(document).ready(function(){
		var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		  	 str=str.split("=")[1];		  	 
		  	 $("#idval").val(str);
		   }
	 });

	</script>
</body>
</html>
