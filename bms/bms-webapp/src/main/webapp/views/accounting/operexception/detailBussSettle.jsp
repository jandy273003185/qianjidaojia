<%@page import="com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.operabusssettle.bean.BussSettleBean"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.clearbank.bean.ClearBank"%>
<%@page import="com.qifenqian.bms.platform.common.utils.DateUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.TransAction"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.Operation"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.type.OperationStatus"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>
<%@page import="com.qifenqian.bms.accounting.exception.service.TransDealService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>业务异常处理明细页面</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadCustWithdraw()">
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
							<!-- 业务基本信息 -->
							<% BussSettleBean operation = (BussSettleBean)pageContext.findAttribute("operation"); %>
							<table class="search-table">
								<tr>
									<td class="bg-e" colspan="6" align="center" style="border-left:0px;">
										<input type="hidden" name="operId" id ="operId" value="${operation.operId}">
										<input type="hidden" name="operType" id ="operationType" value="${operation.operType}">
										<input type="hidden" name="operationStatus" id ="operationStatus" value="${operation.status}">
										<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CLOSURE_OPERATION %>">
											<a href="#operationBaseModal"  data-toggle='modal' class="tooltip-success closureOperation" data-rel="tooltip" title="结束流程" >
												<button type="submit" class="btn btn-purple btn-sm" >结束流程</button>
											</a>
										</gyzbadmin:function>
										&nbsp;
										<button type="button" class="btn btn-purple btn-sm backListBtn">返回列表</button>
										&nbsp;
										<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_OPERATION_RECORD%>">
											<a href="#" data-toggle='modal' onclick="queryOperationRecord(this)" data-rel="tooltip" title="查看操作日志" >
												<button type="submit" class="btn btn-primary btn-sm"> 操作日志</button>
											</a> 
										</gyzbadmin:function>
									</td>
								</tr>
								<tr>
								<td class="bg-e" colspan="6"  align="center" style="font: 16px;font-weight: bold;">业务基本信息</td>
								</tr>
								<tr>
									<td class="td-left bg-e" width="11%">业务编号</td>
									<td class="td-right" width="22%">${operation.operId}</td>
									<td class="td-left bg-e" width="11%">业务类型</td>
									<td class="td-right" width="22%"><%=ReflectUtils.getDesc(operation.getOperType())%></td>
									<td class="td-left bg-e" width="11%">状态</td>
									<td class="td-right" width="23%">
										<c:choose>
											<c:when test="${operation.settleStatus == 'INIT'}">初始化</c:when>
											<c:when test="${operation.settleStatus == 'AUDIT_PASS'}">审核通过</c:when>
											<c:when test="${operation.settleStatus == 'AUDIT_REFUSE'}">审批拒绝</c:when>
											<c:when test="${operation.settleStatus == 'SEND_INIT'}">发送金蝶</c:when>
											<c:when test="${operation.settleStatus == 'SEND_SUCC'}">发送金蝶成功</c:when>
											<c:when test="${operation.settleStatus == 'PAY_SUCC'}">金蝶付款成功</c:when>
											<c:when test="${operation.settleStatus == 'PAY_FAIL'}">金蝶付款失败</c:when>
											<c:when test="${operation.settleStatus == 'VERIFIED'}">核销完成</c:when>
											<c:when test="${operation.settleStatus == 'VERIFIED_EXCEPTION'}">核销异常</c:when>
											<c:when test="${operation.settleStatus == 'INVALID'}">无效</c:when>
											<c:otherwise>${operation.settleStatus}</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td class="td-left bg-e">操作时间</td>
									<td class="td-right"><fmt:formatDate value="${operation.auditDatetime}" type="both"/></td>
									<td class="td-left bg-e">结算金额</td>
									<td class="td-right" style="color:red">${operation.settleAmt}</td>
									<td class="td-left bg-e">结算申请时间</td>
									<td class="td-right"><fmt:formatDate value="${operation.instDatetime}" type="both"/></td>
								</tr>
								<tr>
								
									<td class="td-left bg-e">结算银行卡号</td>
									<td class="td-right">${operation.bankCardNo}</td>
									<td class="td-left bg-e">银行信息</td>
									<td class="td-right">${operation.bankName}</td>
									<td class="td-left bg-e">银行卡名</td>
									<td class="td-right">${operation.bankCardName}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">协议编号</td>
									<td class="td-right" >${operation.protocolId}</td>
									<td class="td-left bg-e">商户名称</td>
									<td class="td-right">${operation.merchantName}</td>
									<td class="td-left bg-e">执行日期</td>
									<td class="td-right">${operation.workDate}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">收款笔数</td>
									<td class="td-right ">${operation.receiveCount}</td>
				            		<td class="td-left bg-e">收款总额</td>
				            		<td class="td-right ">${operation.receiveTotalAmt}</td>
									<td class="td-left bg-e">收款费用</td>
									<td class="td-right ">${operation.receiveTotalFee}</td>
								</tr>
								<tr>
				            		<td class="td-left bg-e">撤销笔数</td>
				            		<td class="td-right ">${operation.revokeCount}</td>
									<td class="td-left bg-e">撤销总额</td>
									<td class="td-right ">${operation.revokeTotalAmt}</td>
				            		<td class="td-left bg-e">撤销费用</td>
				            		<td class="td-right ">${operation.revokeTotalFee}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">退款笔数</td>
									<td class="td-right ">${operation.refundCount}</td>
				            		<td class="td-left bg-e">退款总额</td>
				            		<td class="td-right ">${operation.refundTotalAmt}</td>
									<td class="td-left bg-e">退款费用</td>
									<td class="td-right ">${operation.refundTotalFee}</td>
								</tr>
								<tr>
				            		<td class="td-left bg-e">提现笔数</td>
				            		<td class="td-right ">${operation.withdrawCount}</td>
									<td class="td-left bg-e">提现总额</td>
									<td class="td-right ">${operation.withdrawTotalAmt}</td>
				            		<td class="td-left bg-e">提现费用</td>
				            		<td class="td-right ">${operation.withdrawTotalFee}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">应收总额</td>
									<td class="td-right ">${operation.merchantReceivable}</td>
				            		<td class="td-left bg-e">应付总额</td>
				            		<td class="td-right" colspan="3">${operation.merchantPayable}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">核心流水号</td>
									<td class="td-right">${operation.coreSn}</td>
									<td class="td-left bg-e">核心返回码</td>
									<td class="td-right">${operation.coreReturnCode}</td>
									<td class="td-left bg-e">核心返回信息</td>
									<td class="td-right">${operation.coreReturnMsg}</td>
								</tr>
							</table>
							<!-- 核心基本信息 -->
							<c:if test="${not empty transRecord }">
								<%
								  TransRecord transRecord = (TransRecord)pageContext.findAttribute("transRecord");
								%>
								<table class="search-table" style="margin-top: 10px;">
									<tr>
										<td class="bg-e" colspan="6" align="center" style="border-right:0px;font: 16px;font-weight: bold;">核心处理信息</td>
									</tr>
									<tr>
										<td class="td-left bg-e" width="11%">请求报文类型</td>
										<td class="td-right"  width="22%"><%=ReflectUtils.getDesc(transRecord.getMsgType())%></td>
										<td class="td-left bg-e"  width="11%">核心报文编号</td>
										<td class="td-right" colspan="3" width="56%">${transRecord.msgId}</td>
									</tr>
									<tr>
										<td class="td-left bg-e" width="11%">请求方系统</td>
										<td class="td-right" width="22%"><%=ReflectUtils.getDesc(transRecord.getReqSysId())%></td>
										<td class="td-left bg-e" width="11%">请求方流水号</td>
										<td class="td-right" width="22%">${transRecord.reqSerialId}</td>
										<td class="td-left bg-e" width="11%">请求方服务器</td>
										<td class="td-right" width="23%">${transRecord.reqServerIp}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">报文明细条数</td>
										<td class="td-right">${transRecord.reqMsgNum}</td>
										<td class="td-left bg-e">请求时间</td>
										<td class="td-right"><fmt:formatDate value="${transRecord.reqDatetime }" type="both"/> </td>
										<td class="td-left bg-e">处理状态</td>
										<td class="td-right"><%=ReflectUtils.getDesc(transRecord.getStatus())%></td>
									</tr>
									<tr>
										<td class="td-left bg-e">核心接收时间</td>
										<td class="td-right"><fmt:formatDate value="${transRecord.instDatetime }" type="both"/></td>
										<td class="td-left bg-e">请求报文存储</td>
										<td class="td-right" colspan="3">${transRecord.reqFilepath}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">核心处理服务器</td>
										<td class="td-right">${transRecord.recordIp }</td>
										<td class="td-left bg-e">核心处理结果</td>
										<td class="td-right" colspan="3">[${transRecord.rtnCode}]&nbsp;${transRecord.rtnInfo}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">核心响应时间</td>
										<td class="td-right"><fmt:formatDate value="${transRecord.rtnDatetime }" type="both"/></td>
										<td class="td-left bg-e">响应报文存储</td>
										<td class="td-right" colspan="3">${transRecord.rtnFilepath}</td>
									</tr>
								</table>
							   </c:if>
							   <c:if test="${not empty transList }">
								<!-- 交易流水信息 -->
								<table class="search-table" style="margin-top: 10px;">
									<tr>
										<td class="bg-e" colspan="6"  align="center" style="border-right:0px;font: 16px;font-weight: bold;">交易流水信息</td>
									</tr>
									<c:forEach items="${transList }" var="trans" varStatus="status">
										<!-- 标题 -->
										<%
										  TransAction trans = (TransAction)pageContext.findAttribute("trans");
										%>
										
										<%
																				  if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.BUSS_SETTLE_APPLY_RECEIVE||
																															trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.BUSS_SETTLE_REVOKE_RECEIVE||
																															trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_BUSS_SETTLE) {
																															AcctSevenTrans sevenTrans = (AcctSevenTrans)trans;
																				%>
										<tr>
											<td class="bg-e" colspan="2" width="33%" align="right" style="padding-right: 60px;border-right:0px;font-size: 15px;font-weight: bold;">
												<%=ReflectUtils.getDesc(trans.getTransFlowOperate())%>&nbsp;[<font color="red"><%=ReflectUtils.getDesc(trans.getResultStatus())%></font>]
											</td>
											<td class="bg-e" colspan="4" width="67%" align="right" style="padding-right: 50px;border-left:0px;">
												<input type="hidden" name="transFlowId" value="${trans.transRecordFlow.id}" />
												<input type="hidden" name="transFlowOperate" value="${trans.transFlowOperate}" />
												<input type="hidden" name="msgId" value="${trans.transRecordFlow.msgId}" />
												<%
												  if(trans.getResultStatus() == RequestColumnValues.TransStatus.EXCEPTION) {
												%>
													<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
														<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
															<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
														</a>
													</gyzbadmin:function>
													&nbsp;
													<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_SUCCESS_TRANS%>">
														<a href="#transBaseModal"  class="tooltip-success confirmSuccess" data-rel="tooltip"  data-toggle='modal' title="确认成功">
															<button type="submit" class="btn btn-purple btn-sm">确认成功</button>
														</a>
													</gyzbadmin:function>
													&nbsp;
													<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_FAILURE_TRANS%>">
														<a href="#transBaseModal"  class="tooltip-success confirmFailure" data-rel="tooltip"  data-toggle='modal' title="确认失败">
															<button type="submit" class="btn btn-purple btn-sm">确认失败</button>
														</a>
													</gyzbadmin:function>
												<%
												  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.FAILURE || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_FAILURE) {
												%>
													<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
														<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
															<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
														</a>
													</gyzbadmin:function>
												<%
												  }
												%>
											</td>
										</tr>
										<!-- 交易明细 -->
										<tr>
											<td class="td-left bg-e">交易流水号</td>
											<td class="td-right">${trans.transRecordFlow.id}</td>
											<td class="td-left bg-e">业务类型</td>
											<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getBusinessType())%></td>
											<td class="td-left bg-e">状态</td>
											<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getStatus())%></td>
										</tr>
										<tr>
											<td class="td-left bg-e">写入时间</td>
											<td class="td-right"><fmt:formatDate value="${trans.transRecordFlow.instDatetime}" type="both"/></td>
											<td class="td-left bg-e">交易金额</td>
											<td class="td-right" style="color: red;">${trans.transRecordFlow.transAmt}</td>
											<td class="td-left bg-e">交易币别</td>
											<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getCurrCode())%></td>
										</tr>
										<tr>
											<td class="td-left bg-e">核心报文编号</td>
											<td class="td-right" >${trans.transRecordFlow.msgId}</td>							
											<td class="td-left bg-e">原/对应编号</td>
											<td class="td-right">${trans.transRecordFlow.originId}</td>
											<td class="td-left bg-e">收支标记</td>
											<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getLoanFlag())%></td>
										</tr>
										<tr>
											<td class="td-left bg-e">客户号</td>
											<td class="td-right">${trans.transRecordFlow.custId}</td>
											<td class="td-left bg-e">账户类型</td>
											<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getAcctType())%></td>
											<td class="td-left bg-e">账号</td>
											<td class="td-right">${trans.transRecordFlow.acctId}</td>
										</tr>
										<tr>
											<td class="td-left bg-e">账号</td>
											<td class="td-right">${trans.acctId}</td>
											<td class="td-left bg-e">会计日期</td>
											<td class="td-right">${trans.workDate}</td>
											<td class="td-left bg-e">记账时间</td>
											<td class="td-right"><fmt:formatDate value="${trans.instDatetime}" type="both"/></td>
										</tr>
										<tr>
											<td class="td-left bg-e">返回编号</td>
											<td class="td-right">${trans.transRecordFlow.rtnId}</td>
											<td class="td-left bg-e">返回信息</td>
											<td class="td-right" >[${trans.transRecordFlow.rtnCode}]&nbsp;${trans.transRecordFlow.rtnInfo}</td>
										</tr>
										<tr>
											<td class="td-left bg-e">记账流水号</td>
											<td class="td-right" >${trans.id}</td>
											<td class="td-left bg-e">记账前余额</td>
											<td class="td-right">${trans.balance}</td>
											<td class="td-left bg-e">记账发生额</td>
											<td class="td-right">${trans.transAmt}</td>
										</tr>
										<tr>
											<td class="td-left bg-e">在途金额变动</td>
											<td class="td-right">${trans.onwayAmt}</td>
											<td class="td-left bg-e">可用余额变动</td>
											<td class="td-right">${trans.usableAmt}</td>
											<td class="td-left bg-e">摘要</td>
											<td class="td-right" >${trans.brief}</td>
										</tr>
								<%
								  } if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.BANK_CLEAR) {
																	ClearBank clearBank = (ClearBank)trans;
								%>
								<tr>
									<td class="bg-e" colspan="2" width="33%" align="right" style="padding-right: 60px;border-right:0px;font-size: 15px;font-weight: bold;">
										<%=ReflectUtils.getDesc(trans.getTransFlowOperate())%>&nbsp;[<font color="red"><%=ReflectUtils.getDesc(trans.getResultStatus())%></font>]
									</td>
									<td class="bg-e" colspan="4" width="67%" align="right" style="padding-right: 50px;border-left:0px;">
										<input type="hidden" name="transFlowId" value="${trans.transRecordFlow.id}" />
										<input type="hidden" name="transFlowOperate" value="${trans.transFlowOperate}" />
										<input type="hidden" name="msgId" value="${trans.transRecordFlow.msgId}" />
										<%
										  if(trans.getResultStatus() == RequestColumnValues.TransStatus.EXCEPTION) {
										%>
												<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
													<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
														<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
													</a>
												</gyzbadmin:function>
												&nbsp;
												<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_SUCCESS_TRANS%>">
													<a href="#transBaseModal"  class="tooltip-success confirmSuccess" data-rel="tooltip"  data-toggle='modal' title="确认成功">
														<button type="submit" class="btn btn-purple btn-sm">确认成功</button>
													</a>
												</gyzbadmin:function>
												&nbsp;
												<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_FAILURE_TRANS%>">
													<a href="#transBaseModal"  class="tooltip-success confirmFailure" data-rel="tooltip"  data-toggle='modal' title="确认失败">
														<button type="submit" class="btn btn-purple btn-sm">确认失败</button>
													</a>
												</gyzbadmin:function>
											<%
											  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.FAILURE || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_FAILURE) {
											%>
												<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
													<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
														<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
													</a>
												</gyzbadmin:function>
											<% } %>
									</td>
								</tr>
								<!-- 交易明细 -->
								<tr>
									<td class="td-left bg-e">交易流水号</td>
									<td class="td-right">${trans.transRecordFlow.id}</td>
									<td class="td-left bg-e">业务类型</td>
									<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getBusinessType()) %></td>
									<td class="td-left bg-e">状态</td>
									<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getStatus()) %></td>
								</tr>
								<tr>
								<td class="td-left bg-e">写入时间</td>
									<td class="td-right"><fmt:formatDate value="${trans.transRecordFlow.instDatetime}" type="both"/></td>
									<td class="td-left bg-e">交易金额</td>
									<td class="td-right" style="color: red;">${trans.transRecordFlow.transAmt}</td>
									<td class="td-left bg-e">交易币别</td>
									<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getCurrCode()) %></td>
								</tr>
								<tr>
									<td class="td-left bg-e">核心报文编号</td>
									<td class="td-right">${trans.transRecordFlow.msgId}</td>
									<td class="td-left bg-e">记账流水号</td>
									<td class="td-right">${trans.id}</td>
									<td class="td-left bg-e">收支标记</td>
									<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getLoanFlag()) %></td>
								</tr>
								<tr>
									<td class="td-left bg-e">客户号</td>
									<td class="td-right">${trans.transRecordFlow.custId}</td>
									<td class="td-left bg-e">账户类型</td>
									<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getAcctType()) %></td>
									<td class="td-left bg-e">账号</td>
									<td class="td-right">${trans.transRecordFlow.acctId}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">收方客户类型</td>
									<td class="td-right"><%=ReflectUtils.getDesc(clearBank.getRcvCustType()) %></td>
									<td class="td-left bg-e">收方内部编号</td>
									<td class="td-right" ><%=clearBank.getRcvAcctId() %></td>
									<td class="td-left bg-e">收方账户类型</td>
									<td class="td-right" ><%=ReflectUtils.getDesc(clearBank.getRcvAcctType()) %></td>
								</tr>
								<tr>
									<td class="td-left bg-e">收方银行账号</td>
									<td class="td-right"><%=clearBank.getRcvBankCardNo() %></td>
									<td class="td-left bg-e">收方账户名</td>
									<td class="td-right" ><%=clearBank.getRcvBankCardName() %></td>
									<td class="td-left bg-e">开户行联行号</td>
									<td class="td-right" ><%=clearBank.getRcvBankCode12()%></td>
								</tr>
								<tr>
									<td class="td-left bg-e">返回编号</td>
									<td class="td-right">${trans.transRecordFlow.rtnId}</td>
									<td class="td-left bg-e">返回信息</td>
									<td class="td-right" colspan="3">[${trans.transRecordFlow.rtnCode}]&nbsp;${trans.transRecordFlow.rtnInfo}</td>
								</tr>
							<% } %>
								</c:forEach>
							</table>
							</c:if>
						</div>
					</div>
					<div class="modal fade" id="operationBaseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:50%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title operationCls" id="myModalLabel"></h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
								<tr>
									<td class="td-left" style="width:15%">业务编号</td>
									<td class="td-right" style="width:35%">
										<input type="text" id="operId" name="operId" readonly="readonly" clasS="width-90">
										<input type="hidden" id="operType" name="operType" readonly="readonly" clasS="width-90">
									</td>
									<td class="td-left" style="width:15%">状态</td>
									<td class="td-right" style="width:35%">
										<select id="chargeNetpayState" name="chargeNetpayState" clasS="width-90">
										  <option value="VERIFIED">-核销完成-</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left">备注</td>
									<td class="td-right" colspan="3">
										<textarea  id="operationOpinion" name="operationOpinion" rows="2" maxlength="200" clasS="width-95"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer" id="operationChildBtn">
				            <button type="button" class="btn btn-default" id="operationBtn" data-dismiss="modal">关闭</button>
				            <button type='button' class='btn btn-primary' id='closureOperationBtn'>提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				
				<div class="modal fade" id="transBaseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:50%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title transCls" id="myModalLabel"></h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
								<tr>
									<td class="td-left">流程编号</td>
									<td class="td-right"> 
										<input type="text" id="transFlowId" name="transFlowId" readonly="readonly" clasS="width-90">
										<input type="hidden" id="transFlowOperate" name="transFlowOperate" readonly="readonly" clasS="width-90">
										<input type="hidden" id="msgId" name="msgId" readonly="readonly" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left">备注</td>
									<td class="td-right">
										<textarea  id="opinion" name="opinion" rows="2" maxlength="200" clasS="width-90"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer" id="transChildBtn">
				            <button type="button" class="btn btn-default" id="transBtn" data-dismiss="modal">关闭</button>
				            <button type='button' class="btn btn-primary" id="confirmSuccessBtn">确认成功</button>
				            <button type="button" class="btn btn-primary" id="confirmFailureBtn">确认失败</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	</div>
	<script type="text/javascript">
	function loadCustWithdraw(){
		var operationStatus =$("#operationStatus").val();
		if(operationStatus=='DEAL_SUCCESS'||operationStatus=='DEAL_CANCELLED'){
			$(".tooltip-success").hide();
		}else{
			$(".tooltip-success").show();
		}
	}
	function forCloseDiv(){
		 $.unblockUI();
	} 
	jQuery(function($){
		var loop =	setInterval(function() {     
		    if(winChild!=null&&winChild.closed) { 
		    	$.unblockUI();
		    }    
		}, 200); 
		
		$('.backListBtn').click(function(){
			$.blockUI();
			window.history.go(-1);
		});
		
		/** 加载结束流程  **/
		$(".closureOperation").click(function(){
			var operId= $(this).parent().find('input[name="operId"]').val();
			var operType= $(this).parent().find('input[name="operType"]').val();
			$("#operationBaseModal #operId").val(operId);	
			$("#operationBaseModal #operType").val(operType);	
			$('.operationCls').text("结束流程");
			$('#operationBtn').siblings().hide();
			$('#closureOperationBtn').show();
		});
		
		/**结束流程**/
		$("#closureOperationBtn").click(function(){
			var operId = $("#operationBaseModal #operId").val();	
			var operType = $("#operationBaseModal #operType").val();	
			var operationOpinion = $("#operationBaseModal #operationOpinion").val();	
			var chargeNetpayState = $("#operationBaseModal #chargeNetpayState").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CLOSURE_OPERATION%>', {
				'operId':operId,
				'operType':operType,
				'operationOpinion':operationOpinion,
				'chargeNetpayState' :chargeNetpayState
				}, function(data) {
					$.unblockUI();
					$('#operationBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('流程结束成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载确认成功  **/
		$(".confirmSuccess").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("确认成功");
			$('#transBtn').siblings().hide();
			$('#confirmSuccessBtn').show();
		});
		
		/**确认成功**/
		$("#confirmSuccessBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_SUCCESS_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('确认成功完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
		
		/** 加载确认失败 **/
		$(".confirmFailure").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("确认失败");
			$('#transBtn').siblings().hide();
			$('#confirmFailureBtn').show();
		});
		
		/**确认失败**/
		$("#confirmFailureBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.CONFIRM_FAILURE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('确认失败完成', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message,null,function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		});
	});
	
	/**交易结果自查**/
	 var winChild;
	function queryResultTrans(obj){
	 var transFlowId = $(obj).parent().find('input[name="transFlowId"]').val();
	 var transFlowOperate= $(obj).parent().find('input[name="transFlowOperate"]').val();
	 var url=window.Constants.ContextPath+"<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>?transFlowId="+transFlowId+"&transFlowOperate="+transFlowOperate; 
     var name="newwindow";                        
     var iWidth=1500;                          
     var iHeight=600;                      
     var iTop = (window.screen.availHeight-30-iHeight)/2;
     var iLeft = (window.screen.availWidth-10-iWidth)/2;
     var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
   	winChild = window.open(url, name,params);
	}
	
	/**查看操作记录**/
	function queryOperationRecord(obj){
		var operId= $(obj).parent().find('input[name="operId"]').val();
		 var url=window.Constants.ContextPath+"<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_OPERATION_RECORD%>?operId="+operId; 
	     var name="newwindow";                        
	     var iWidth=1500;                          
	     var iHeight=700;    
	     var iTop = (window.screen.availHeight-30-iHeight)/2;
	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     var params='width='+iWidth
                +',height='+iHeight
                +',top='+iTop
                +',left='+iLeft;
	   	winChild = window.open(url, name,params);
	}
	</script>
  </body>	
</html>