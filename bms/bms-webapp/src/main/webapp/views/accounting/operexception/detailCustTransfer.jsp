<%@page import="com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.opertrade.bean.OperTrade"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkjTransfer"%>
<%@page import="com.qifenqian.bms.platform.common.utils.DateUtils"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.TransAction"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.bean.Operation"%>
<%@page import="com.qifenqian.bms.accounting.exception.base.type.OperationStatus"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@page import="com.sevenpay.invoke.common.type.RequestColumnValues"%>
<%@page import="com.qifenqian.bms.accounting.exception.OperationExceptionPath"%>
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
<script type="text/javascript">

</script>
<body onload="loadBalancePayment()">
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
							<%
								OperTrade operation = (OperTrade)pageContext.findAttribute("operation");
							%>
							<table class="search-table">
									<tr>
								<td class="bg-e" colspan="6" align="center" style="border-left:0px;">
										<input type="hidden" name="operId" id ="operId" value="${operation.operId}">
										<input type="hidden" name="operType" id ="operationType" value="${operation.operType}">
										<input type="hidden" name="operationStatus" id ="operationStatus" value="${operation.status}">
										<c:if test="${operation.orderState != '99' and operation.chargeNetpayState != '00'}">
											<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.CLOSURE_OPERATION%>">
												<a href="#operationBaseModal"  data-toggle='modal' class="tooltip-success closureOperation" data-rel="tooltip" title="结束流程" >
													<button type="submit" class="btn btn-purple btn-sm" >结束流程</button>
												</a>
											</gyzbadmin:function>
										</c:if>
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
											<c:when test="${operation.orderState == '01'}">待提交</c:when>
											<c:when test="${operation.orderState == '02'}">提交核心</c:when>
											<c:when test="${operation.orderState == '03'}">核心处理中</c:when>
											<c:when test="${operation.orderState == '04'}">核心处理失败</c:when>
											<c:when test="${operation.orderState == '00'}">处理成功</c:when>
											<c:when test="${operation.orderState == '99'}">已取消</c:when>
											<c:when test="${operation.orderState == '90'}">未明</c:when>
											<c:otherwise>${operation.orderState}</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td class="td-left bg-e">订单生成时间</td>
									<td class="td-right"><fmt:formatDate value="${operation.orderCreateTime}" type="both"/></td>
									<td class="td-left bg-e">交易金额</td>
									<td class="td-right" style="color: red">${operation.sumAmt}</td>
									<td class="td-left bg-e">交易币别</td>
									<td class="td-right"><%=ReflectUtils.getDesc(operation.getCurrCode())%></td>
								</tr>
								<tr>
								<td class="td-left bg-e">订单名称</td>
									<td class="td-right">${operation.orderName}</td>
									<td class="td-left bg-e">订单描述</td>
									<td class="td-right">${operation.orderDesc}</td>
									<td class="td-left bg-e">发起方客户编号</td>
									<td class="td-right">${operation.srcCustId}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">付方客户编号</td>
									<td class="td-right">${operation.payerCustId}</td>
									<td class="td-left bg-e">付方客户名</td>
									<td class="td-right">${operation.payerCustName}</td>
									<td class="td-left bg-e">付方账户</td>
									<td class="td-right">${operation.payerAcctId}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">收方客户编号</td>
									<td class="td-right">${operation.payeeCustId}</td>
									<td class="td-left bg-e">收方客户名</td>
									<td class="td-right">${operation.payeeCustName}</td>
									<td class="td-left bg-e">收方账户</td>
									<td class="td-right">${operation.payeeAcctId}</td>
								</tr>
								<tr>
									<td class="td-left bg-e">操作终端</td>
									<td class="td-right">
										<c:choose>
											<c:when test="${operation.opTerm == 'P'}">PC端</c:when>
											<c:when test="${operation.opTerm == 'M'}">移动手机端</c:when>
											<c:otherwise>${operation.opTerm}</c:otherwise>
										</c:choose>
									</td>
									<td class="td-left bg-e">创建时间</td>
									<td class="td-right" colspan="3"><fmt:formatDate value="${operation.createTime}" type="both"/></td>
									
								</tr>
								<tr>
									<td class="td-left bg-e">核心流水号</td>
									<td class="td-right">${operation.coreSn}</td>
									<td class="td-left bg-e">核心返回时间</td>
									<td class="td-right"><fmt:formatDate value="${operation.orderCoreReturnTime}" type="both"/></td>
									<td class="td-left bg-e">核心返回信息</td>
									<td class="td-right">[${operation.orderCoreReturnCode}]&nbsp;${operation.orderCoreReturnMsg }</td>
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
									  if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER||
																										   trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER_REVERSAL) {
																											ClearJgkjTransfer clearJgkj = (ClearJgkjTransfer)trans;
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
															<button type="submit" class="btn btn-purple btn-sm"> 结果自查</button>
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
													  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.SUCCESS || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_SUCCESS) {
													%>
														<%
														  if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER){
														%>
															<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.SEQUEL_NEXT_STEP_OPERATION%>">
																<a href="#transBaseModal"  class="tooltip-success sequelNextStepOperation" data-rel="tooltip"  data-toggle='modal' title="续作下一步">
																	<button type="submit" class="btn btn-purple btn-sm">续作下一步</button>
																</a>
															</gyzbadmin:function>
															&nbsp;
															<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.ROLLBACK_OPERATION%>">
																<a href="#transBaseModal"  data-toggle='modal' class="tooltip-success rollbackOperation" data-rel="tooltip" title="退回当前步骤" >
																	<button type="submit" class="btn btn-purple btn-sm" >退回当前步骤(反向交易)</button>
																</a>
														    </gyzbadmin:function>
														<%
														  }
														%>
												<%
												  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.FAILURE || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_FAILURE) {
												%>
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
														<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
															<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
														</a>
														</gyzbadmin:function>
														<%
														  if(trans.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER){
														%>
															&nbsp;
															<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.REXECUTE_TRANS%>">
																<a href="#transBaseModal"  data-toggle='modal' class="tooltip-success rexecuteTrans" data-rel="tooltip" title="重新执行" >
																	<button type="submit" class="btn btn-purple btn-sm">重新执行</button>
																</a>
															</gyzbadmin:function>
															&nbsp;
															<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.RESTART_OPERATION%>">
																<a href="#transBaseModal"  data-toggle='modal' class="tooltip-success restartOperation" data-rel="tooltip" title="续作整个流程" >
																	<button type="submit" class="btn btn-purple btn-sm " >续作整个流程</button>
																</a>
															</gyzbadmin:function>
														<%
														  }
														%>
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
										<td class="td-left bg-e">创建时间</td>
										<td class="td-right"><fmt:formatDate value="${trans.transRecordFlow.instDatetime}" type="both"/></td>
										<td class="td-left bg-e">交易金额</td>
										<td class="td-right" style="color: red;">${trans.transRecordFlow.transAmt}</td>
										<td class="td-left bg-e">交易币别</td>
										<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getCurrCode())%></td>
									</tr>
									<tr>
										<td class="td-left bg-e">清算编号</td>
										<td class="td-right">${trans.id}</td>
										<td class="td-left bg-e">核心报文编号</td>
										<td class="td-right" colspan ="3">${trans.transRecordFlow.msgId}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">付方客户号</td>
										<td class="td-right">${trans.transRecordFlow.custId}</td>
										<td class="td-left bg-e">付方账户类型</td>
										<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getAcctType())%></td>
										<td class="td-left bg-e">付方账号</td>
										<td class="td-right">${trans.transRecordFlow.acctId}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">转出-交广科技卡号</td>
										<td class="td-right">${trans.debitCardNo}</td>
										<td class="td-left bg-e">交易码</td>
										<td class="td-right">${trans.transCode}</td>
										<td class="td-left bg-e">转入-交广科技卡号</td>
										<td class="td-right">${trans.creditCardNo}</td>
									</tr>
									 <tr>
									 	<td class="td-left bg-e">交广科技流水号</td>
										<td class="td-right">${trans.rtnSeq}</td>
										<td class="td-left bg-e">交易发送时间</td>
										<td class="td-right">${trans.sendDate}${trans.sendTime}</td>
										<td class="td-left bg-e">收支标记</td>
										<td class="td-right"><%=ReflectUtils.getDesc(trans.getTransRecordFlow().getLoanFlag())%></td>
									</tr> 
									<tr>
										<td class="td-left bg-e">交广科技返回时间</td>
										<td class="td-right">${trans.rtnDate}${trans.rtnTime}</td>
										<td class="td-left bg-e">交广科技返回信息</td>
										<td class="td-right">[${trans.rtnCode}]&nbsp;${trans.rtnInfo}</td>
										<td class="td-left bg-e">交广科技返回卡号</td>
										<td class="td-right">${trans.rtnCardNo}</td>
									</tr>
									<tr>
										<td class="td-left bg-e">交广科技返回金额</td>
										<td class="td-right">${trans.rtnAmt}</td>
										<td class="td-left bg-e">交广科技返回余额</td>
										<td class="td-right">${trans.rtnBalance}</td>
										<td class="td-left bg-e">会计日期</td>
										<td class="td-right">${trans.workDate}</td>
									</tr>
									<%
									  } if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT||
																			trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECEIVE||
																			trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT_REVOKE||
																			trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECEIVE_REVOKE) {
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
											  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.SUCCESS || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_SUCCESS) {
											%>
													<%
													  if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECEIVE){
													%>
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.REVOKE_TRANS%>">
															<a href="#transBaseModal"  class="tooltip-success revokeTrans" data-rel="tooltip"  data-toggle='modal' title="撤销当前步骤">
																<button type="submit" class="btn btn-purple btn-sm">撤销当前步骤</button>
															</a>
														</gyzbadmin:function>
													<%
													  }
													%>
													<%
													  if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT){
													%>
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.SEQUEL_NEXT_STEP_OPERATION%>">
															<a href="#transBaseModal"  class="tooltip-success sequelNextStepOperation" data-rel="tooltip"  data-toggle='modal' title="续作下一步">
																<button type="submit" class="btn btn-purple btn-sm">续作下一步</button>
															</a>
														</gyzbadmin:function>
														&nbsp;
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.REVOKE_TRANS%>">
															<a href="#transBaseModal"  class="tooltip-success revokeTrans" data-rel="tooltip"  data-toggle='modal' title="撤销当前步骤">
																<button type="submit" class="btn btn-purple btn-sm">撤销当前步骤</button>
															</a>
														</gyzbadmin:function>
													<%
													  }
													%>
												
											<%
																							  } else if(trans.getResultStatus() == RequestColumnValues.TransStatus.FAILURE || trans.getResultStatus() == RequestColumnValues.TransStatus.CONFIRM_FAILURE) {
																							%>
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.QUERY_RESULT_TRANS%>">
														<a href="#" class="tooltip-success" onclick="queryResultTrans(this)" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryResultModel">
															<button type="submit" class="btn btn-purple btn-sm">结果自查</button>
														</a>
													</gyzbadmin:function>
												    <%
												      if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT||
												    													    trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_RECEIVE){
												    %>
														&nbsp;
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.REXECUTE_TRANS%>">
															<a href="#transBaseModal"  data-toggle='modal' class="tooltip-success rexecuteTrans" data-rel="tooltip" title="重新执行" >
																<button type="submit" class="btn btn-purple btn-sm">重新执行</button>
															</a>
														</gyzbadmin:function>
													<%
													  }
													%>
													<%
													  if(trans.getTransFlowOperate()==RequestColumnValues.TransFlowOperate.SEVEN_CUST_PAYMENT){
													%>
													    &nbsp;
														<gyzbadmin:function url="<%=OperationExceptionPath.BASE + OperationExceptionPath.RESTART_OPERATION%>">
															<a href="#transBaseModal"  data-toggle='modal' class="tooltip-success restartOperation" data-rel="tooltip" title="续作整个流程" >
																<button type="submit" class="btn btn-purple btn-sm " >续作整个流程</button>
															</a>
														</gyzbadmin:function>
													<% }%>
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
											<td class="td-right" >${trans.transRecordFlow.msgId}</td>							
											<td class="td-left bg-e">原/对应编号</td>
											<td class="td-right">${trans.transRecordFlow.originId}</td>
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
										  <option value="99">-取消-</option>
										  <option value="00">-成功-</option>
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
				            <button type='button' class="btn btn-primary" id="revokeTransBtn">撤销当前步骤</button>
				            <button type="button" class="btn btn-primary" id="rollbackOperationBtn">退回当前步骤</button>
				            <button type="button" class="btn btn-primary" id="rexecuteTransBtn">重新执行</button>
				            <button type="button" class="btn btn-primary" id="sequelNextStepOperationBtn">续作下一步</button>
				            <button type="button" class="btn btn-primary" id="restartOperationBtn">续作整个流程</button>
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
	 function loadBalancePayment(){
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
		/** 加载撤销当前步骤**/
		$(".revokeTrans").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate = $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("撤销当前步骤");
			$('#transBtn').siblings().hide();
			$('#revokeTransBtn').show();
		});
		/**撤销当前步骤**/
		$("#revokeTransBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();	
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.REVOKE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('撤销当前步骤完成', null, function(){
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
		/** 加载退回当前步骤**/
		$(".rollbackOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("退回当前步骤");
			$('#transBtn').siblings().hide();
			$('#rollbackOperationBtn').show();
		});
		/**退回当前步骤**/
		$("#rollbackOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.ROLLBACK_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('退回当前步骤完成', null, function(){
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
		
		/** 加载重新执行**/
		$(".rexecuteTrans").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("重新执行");
			$('#transBtn').siblings().hide();
			$('#rexecuteTransBtn').show();
		});
		/**重新执行**/
		$("#rexecuteTransBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.REXECUTE_TRANS%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('重新执行完成', null, function(){
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
		
		/** 加载续作下一步**/
		$(".sequelNextStepOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("续作下一步");
			$('#transBtn').siblings().hide();
			$('#sequelNextStepOperationBtn').show();
		});
		/**续作下一步**/
		$("#sequelNextStepOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.SEQUEL_NEXT_STEP_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('续作下一步完成', null, function(){
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
		
		/** 加载续作整个流程**/
		$(".restartOperation").click(function(){
			var transFlowId = $(this).parent().find('input[name="transFlowId"]').val();
			var transFlowOperate= $(this).parent().find('input[name="transFlowOperate"]').val();
			var msgId = $(this).parent().find('input[name="msgId"]').val();
			$("#transBaseModal #msgId").val(msgId);	
			$("#transBaseModal #transFlowId").val(transFlowId);	
			$("#transBaseModal #transFlowOperate").val(transFlowOperate);	
			$('.transCls').text("续作整个流程");
			$('#transBtn').siblings().hide();
			$('#restartOperationBtn').show();
		});
		/**续作整个流程**/
		$("#restartOperationBtn").click(function(){
			var transFlowId = $("#transBaseModal #transFlowId").val();	
			var transFlowOperate = $("#transBaseModal #transFlowOperate").val();	
			var opinion = $("#transBaseModal #opinion").val();	
			var msgId = $("#transBaseModal #msgId").val();
		 	$.blockUI();
			$.post(window.Constants.ContextPath + '<%=OperationExceptionPath.BASE + OperationExceptionPath.RESTART_OPERATION%>', {
				'transFlowId':transFlowId,
				'transFlowOperate':transFlowOperate,
				'msgType': $('#operationType').val(),
				'msgId':msgId,
				'operationId':$('#operId').val(),
				'opinion': opinion
				}, function(data) {
					$.unblockUI();
					$('#transBaseModal').modal('hide');
					if(data.result == 'SUCCESS'){
						$.gyzbadmin.alertSuccess('续作整个流程完成', null, function(){
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