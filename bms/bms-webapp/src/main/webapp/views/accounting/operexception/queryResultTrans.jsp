<%@page import="com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenFreeze"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.clearbank.bean.ClearBank"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkj"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl"%>
<%@page import="com.qifenqian.bms.platform.common.utils.DateUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.TransAction"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.opercharge.bean.OperCharge"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.Operation"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.type.OperationStatus"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>交易结果自查</title>
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
<!-- 用户信息 -->
		<div class="main-container" id="main-container">
				<c:if test="${empty queryResult}">
				<table id="sample-table-2" class="list-table">
					<tr>
						<td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td>
					</tr>
				</table>
				</c:if>
				<c:forEach items="${queryResult}" var="trans" varStatus="status">
					<%
						TransAction trans = (TransAction)pageContext.findAttribute("trans");
					%>
					
					<%
						if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.UNIONPAY_PAYMENT||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.UNIONPAY_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.UNIONPAY_REFUND) {
					%>
						<div class="list-table-header">银联交易结果</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="10%">卡账号</th>
										<th width="5%">交易金额</th>
										<th width="10%">交易响应码</th>
										<th width="10%">交易响应信息</th>
										<th width="10%">商户订单号</th>
										<th width="10%">商户号</th>
										<th width="5%">清算金额</th>
										<th width="10%">清算日期</th>
										<th width="10%">平台交易日期</th>
										<th width="10%">系统跟踪号</th>
										<th width="10%">查询流水号</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									<td>${trans.acctNo}</td>
				            		<td>${trans.txnAmt}</td>
				            		<td>${trans.respCode}</td>
				            		<td>${trans.respMsg}</td>
				            		<td>${trans.orderId}</td>
				            		<td>${trans.merId}</td>
				            		<td>${trans.settleAmt}</td>
				            		<td>${trans.settleDate}</td>
				            		<td>${trans.txnTime}</td>
				            		<td>${trans.traceNo}</td>
				            		<td>${trans.queryId}</td>
									</tr>
								</tbody>
							</table>
						</div>
					<%
						} else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_RECHARGE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_RECHARGE_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_RECHARGE_REFUND||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_PAYMENT||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_PAYMENT_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_PAYMENT_REFUND||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER) {
						ClearJgkj clearJgkj = (ClearJgkj)trans;
					%>
						<div class="list-table-header">交广科技交易结果查询</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="15%">卡号</th>
										<th width="10%">交易代码</th>
										<th width="10%">交易结果</th>
										<th width="10%">返回信息</th>
										<th width="10%">交易金额</th>
										<th width="15%">平台流水号</th>
										<th width="15%">平台交易日期</th>
										<th width="15%">平台交易时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${trans.cardNo}</td>
										<td>${trans.transCode}</td>
										<td>${trans.rtnCode}</td>
										<td>${trans.rtnInfo}</td>
										<td>${trans.transAmt}</td>
										<td>${trans.channelSerialSeq}</td>
										<td>${trans.rtnDate}</td>
										<td>${trans.rtnTime}</td>
									</tr>
								</tbody>
							</table>
						</div>
					<% 	} else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_CARD_FREEZE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_CARD_UNFREEZE) {
						ClearJgkj clearJgkj = (ClearJgkj)trans;
					%>
						<div class="list-table-header">交广科技交易结果查询</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="20%">卡号</th>
										<th width="10%">交易代码</th>
										<th width="10%">查询结果</th>
										<th width="20%">查询返回信息</th>
										<th width="20%">卡状态</th>
										<th width="20%">卡类型</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${trans.cardNo}</td>
										<td>${trans.transCode}</td>
										<td>${trans.rtnCode}</td>
										<td>${trans.rtnInfo}</td>
										<td>
											<c:if test="${trans.JGKJCardStatus== '00' }">
												正常
											</c:if>
											<c:if test="${trans.JGKJCardStatus == '01' }">
												挂失
											</c:if>
											<c:if test="${trans.JGKJCardStatus == '02' }">
												冻结
											</c:if>
											<c:if test="${trans.JGKJCardStatus == '03' }">
												注销
											</c:if>
											<c:if test="${trans.JGKJCardStatus == '04' }">
												已换卡
											</c:if>
											<c:if test="${trans.JGKJCardStatus == '09' }">
												已退卡
											</c:if>
										</td>
										<td>
											<c:if test="${trans.JGKJCardType == '00' }">
												记名磁条卡
											</c:if>
											<c:if test="${trans.JGKJCardType == '01' }">
												非记名磁条卡
											</c:if>
											<c:if test="${trans.JGKJCardType == '10' }">
												IC卡
											</c:if>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					<% } else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECHARGE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECHARGE_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECHARGE_REFUND||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT ||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT_REVOKE ||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT_REFUND ||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_BUSS_RECEIVE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_BUSS_RECEIVE_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_BUSS_RECEIVE_REFUND||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_WITHDRAW_APPLY||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_WITHDRAW_REVOKE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_BUSS_SETTLE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.BUSS_SETTLE_APPLY_RECEIVE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.BUSS_SETTLE_REVOKE_RECEIVE||
						trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.BUSS_SETTLE_APPLY_PAY||
						trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.BUSS_SETTLE_REVOKE_PAY||
						trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_INNER_RECEIVE||
						trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECEIVE) {
							AcctSevenTrans sevenTrans = (AcctSevenTrans)trans;
					%>
						<div class="list-table-header">七分钱交易流水</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="15%">交易流水号</th>
										<th width="15%">账号</th>
										<th width="5%">交易前余额</th>
										<th width="5%">发生额</th>
										<th width="5%">在途金额</th>
										<th width="5%">可用金额</th>
										<th width="10%">会计日期</th>
										<th width="10%">记账日期</th>
										<th width="10%">记账时间</th>
										<th width="10%">业务类型</th>
										<th width="10%">摘要</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${trans.id}</td>
										<td>${trans.acctId}</td>
										<td>${trans.balance}</td>
										<td>${trans.transAmt}</td>
										<td>${trans.onwayAmt}</td>
										<td>${trans.usableAmt}</td>
										<td>${trans.workDate}</td>
										<td>${trans.instDate}</td>
										<td><fmt:formatDate value="${trans.instDatetime}" type="both"/> </td>
										<td>${trans.businessType}</td>
										<td>${trans.brief}</td>
									</tr>
								</tbody>
							</table>
						</div>
					<% }  else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_FULL_FREEZE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.SEVEN_CUST_FULL_UNFREEZE) {
						AcctSevenFreeze sevenTrans = (AcctSevenFreeze)trans;
					 %>  
						  <div class="list-table-header">七分钱冻结/解冻流水</div>
							<div>
							   <table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="20%">交易流水号</th>
											<th width="20%">账号</th>
											<th width="20%">会计日期</th>
											<th width="10%">业务类型</th>
											<th width="10%">卡状态</th>
											<th width="10%">摘要</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${trans.id}</td>
											<td>${trans.acctId}</td>
											<td>${trans.workDate}</td>
											<td>
												<% if(sevenTrans.getOperate()!=null){%><%= ReflectUtils.getDesc(sevenTrans.getOperate())%><%}%>
											</td>
											<td>
												<% if(sevenTrans.getStatus()!=null){%><%= ReflectUtils.getDesc(sevenTrans.getStatus())%><%}%>
											</td>
											<td>${trans.brief}</td>
										</tr>
									</tbody>
								</table>
							</div>
					<% }  else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.BANK_CLEAR) {
						  ClearBank clearBank = (ClearBank)trans;
					%>
						<div class="list-table-header">七分钱交易流水</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="10%">交易流水号</th>
										<th width="10%">业务类型</th>
										<th width="10%">客户类型</th>
										<th width="10%">收方银行卡号</th>
										<th width="10%">收方账户名</th>
										<th width="10%">交易金额</th>
										<th width="10%">会计日期</th>
										<th width="10%">记账日期</th>
										<th width="10%">记账时间</th>
										<th width="10%">摘要</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${trans.id}</td>
										<td>
											<c:if test="${trans.businessType == 'WITHDRAW'}">提现</c:if>
											<c:if test="${trans.businessType == 'SETTLE'}">退款</c:if>
										</td>
										<td>
											<c:if test="${trans.rcvCustType == 'CUSTOMER'}">客户</c:if>
											<c:if test="${trans.rcvCustType == 'BUSINESS'}">商户</c:if>
										</td>
										<td>${trans.rcvBankCardNo}</td>
										<td>${trans.rcvBankCardName}</td>
										<td>${trans.transAmt}</td>
										<td>${trans.workDate}</td>
										<td>${trans.instDate}</td>
										<td><fmt:formatDate value="${trans.instDatetime}" type="both"/> </td>
										<td>${trans.brief}</td>
									</tr>
								</tbody>
							</table>
						</div>
					<% }  else if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.KINGDEE_SETTLE||
						trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.KINGDEE_WITHDRAW) {
						KingdeeClear kingdeeClear = (KingdeeClear)trans;
					%>
						<div class="list-table-header">七分钱交易流水</div>
						<div>
						   <table id="sample-table-2" class="list-table">
								<thead>
									<tr>
										<th width="10%">交易流水号</th>
										<th width="5%">业务类型</th>
										<th width="10%">往来单位编码</th>
										<th width="10%">往来单位名称</th>
										<th width="10%">收款单位编码</th>
										<th width="10%">收款单位名称</th>
										<th width="10%">会计日期</th>
										<th width="10%">记账时间</th>
										<th width="5%">状态</th>
										<th width="10%">银行返回信息</th>
										<th width="10%">银行返回EB信息</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${trans.clearId}</td>
										<td>
											<c:if test="${trans.businessType == 'PERSONAL_WITHDRAW'}">个人提现</c:if>
											<c:if test="${trans.businessType == 'MERCHANT_SETTLE'}">商户结算</c:if>
										</td>
										<td>${trans.fcontactunitNumber}</td>
										<td>${trans.fcontactunitName}</td>
										<td>${trans.frectunitNumber}</td>
										<td>${trans.frectunitName}</td>
										<td>${trans.workDate}</td>
										<td><fmt:formatDate value="${trans.instDatetime}" type="both"/></td>
										<td>
											<c:if test="${trans.status == 'A'}">无返回结果</c:if>
											<c:if test="${trans.status == 'B'}">银行处理中</c:if>
											<c:if test="${trans.status == 'C'}">银行交易成功</c:if>
											<c:if test="${trans.status == 'D'}">银行交易失败</c:if>
											<c:if test="${trans.status == 'E'}">银行交易未确认</c:if>
										</td>
										<td>${trans.fBankMsg}</td>
										<td>${trans.fEbMsg}</td>
									</tr>
								</tbody>
							</table>
						</div>
					<% } %>
				</c:forEach>
		</div>
	<script type="text/javascript">
	</script>
</body>
</html>					