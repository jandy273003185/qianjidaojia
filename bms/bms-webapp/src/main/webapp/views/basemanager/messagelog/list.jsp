<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.messagelog.BmsMessageLogPath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>短信邮件日志</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadSchedulerLog()">
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
						
				         <input type="hidden"  name="msgType3"  id="msgType3" value="${queryBean.msgType}"/>
							<!-- 查询条件 -->
							<form  id="messageForm"  method="post" action='<c:url value="<%=BmsMessageLogPath.BASE + BmsMessageLogPath.LIST %>"/>' >
							<table class="search-table">	
								<tr>							    																																
									<td class="td-left" width="15%">发送时间</td>
									<td class="td-right" width="35%">					
								 		 <input type="text" name="beginTime"   id="beginTime" value="${queryBean.beginTime}" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								 		 	-
								 		 <input type="text" name="endTime"   id="endTime" value="${queryBean.endTime}" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left"  style="width:15%;">日志类型</td>
									<td class="td-right"  style="width:35%;">
										<select id="msgType"  name="msgType">
										  <option value ="">---请选择---</option>
										  <option value ="SMS">短信</option>
										  <option value="EMAIL">邮件</option>
										</select>
									</td>
								</tr>
								<tr>
									
									<td class="td-left">收件人</td>
									<td class="td-right">
										<span class="input-icon">
										 <input type="text" name="msgTo"   id="msgTo" value="${queryBean.msgTo}"/><i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">业务类型</td>
									<td class="td-right">
									    <input type="hidden" name="businessTypeTemp"   id="businessTypeTemp" value="${queryBean.businessType}"/>
										<select id="businessType"  name="businessType">
										  <option value ="">---请选择---</option>
										  <option value ="REGISTER_VERIFY">注册验证</option>
										  <option value ="FIND_LOGIN_PWD_VERIFY">登陆密码找回验证</option>
										  <option value ="EDIT_MOBILE_VERIFY">更换手机验证</option>
										  <option value ="FIND_TRADE_PWD_VERFIY">支付密码找回验证</option>
										  <option value ="ADD_QUESTION_VERFIY">添加安全问题验证</option>
										  <option value ="EDIT_LOGIN_PWD_VERIFY">修改登陆密码验证</option>
										  <option value ="EDIT_TRADE_PWD_VERIFY">修改支付密码验证</option>
										  <option value ="WITHDRAW_VERFIY">提现验证</option>
										  <option value ="CHANNEL_ALARM">渠道阀值预警</option>
										  <option value ="MANUAL_HANDLING">人工处理</option>
										  <option value ="RESET_TRADE_PWD">重置支付密码</option>
										  <option value ="MOBILE_LOGIN_CHECK">手机快捷登录</option>
										  <option value ="BUY_FILM">购买电影票</option>
										</select>
									</td>
								</tr>	
								<tr>
									<td colspan="4" align="center" >
										<gyzbadmin:function url="<%=BmsMessageLogPath.BASE + BmsMessageLogPath.LIST %>">		
										<button type="submit" id="serchSubmit" class="btn btn-purple btn-sm buttonSearch"  >
											查询
											<i class="icon-search icon-on-right bigger-110"></i>
										</button>
										</gyzbadmin:function>
									 	<button class="btn btn-purple btn-sm btn-margin clearschedulerLog" >
											清空
											<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
									</td>													
							   </tr>
							</table>
						</form>							
							<div class="list-table-header" >
								短信邮件日志
							</div>

						<div class="table-responsive">
						<table id="sample-table-2" class="list-table">
						  <thead>
							<tr>
								<th style="width:5%">编号</th>
								<th style="width:20%">标题</th>
								<th style="width:5%">类型</th>
								<th style="width:10%">收件人</th>
								<th style="width:12%">发送时间</th>
								<th style="width:8%">发送状态</th>
								<th style="width:12%">日志编号</th>
								<th style="width:8%">业务类型</th>	
								<th style="width:10%">发送主机</th>	
								<th style="width:10%">日志内容</th>								
							</tr>
						  </thead>
						<tbody>
							<c:forEach items="${messageList}" var="result" >
								<tr class="result" >
									<td>${result.msgId}</td>
									<td>${result.subject }</td>
									<td>
										<c:if test="${result.msgType =='SMS'}">
											短信
										</c:if>
										<c:if test="${result.msgType =='EMAIL'}">
											邮件
										</c:if>
									</td>	
									<td>${result.msgTo}</td>
									<td>${result.executeDatetime}
									<%-- <fmt:formatDate value="" pattern="yyyy-MM-dd HH:mm:ss"/> --%>										
									</td>
									<td>${result.state }</td>	
									<td>${result.logId }</td>	
									<td>
									<c:if test="${result.businessType =='REGISTER_VERIFY' }">注册验证</c:if>
										  <c:if test="${result.businessType =='FIND_LOGIN_PWD_VERIFY' }">登陆密码找回验证</c:if>
										  <c:if test="${result.businessType =='EDIT_MOBILE_VERIFY' }">更换手机验证</c:if>
										  <c:if test="${result.businessType =='FIND_TRADE_PWD_VERFIY' }">支付密码找回验证</c:if>
										  <c:if test="${result.businessType =='ADD_QUESTION_VERFIY' }">添加安全问题验证</c:if>
										  <c:if test="${result.businessType =='EDIT_LOGIN_PWD_VERIFY' }">修改登陆密码验证</c:if>
										  <c:if test="${result.businessType =='EDIT_TRADE_PWD_VERIFY' }">修改支付密码验证</c:if>
										  <c:if test="${result.businessType =='WITHDRAW_VERFIY' }">提现验证</c:if>
										  <c:if test="${result.businessType =='CHANNEL_ALARM' }">渠道阀值预警</c:if>
										  <c:if test="${result.businessType =='MANUAL_HANDLING' }">人工处理</c:if>
										  <c:if test="${result.businessType =='RESET_TRADE_PWD' }">重置支付密码</c:if>
										  <c:if test="${result.businessType =='MOBILE_LOGIN_CHECK' }">手机快捷登录</c:if>
										  <c:if test="${result.businessType =='BUY_FILM' }">购买电影票</c:if>
									</td>	
									<td>${result.clientIp }</td>	
									<td>
										<a href="#contentModal"  data-toggle='modal' class="tooltip-error contentBtn" data-rel="tooltip" title="查看内容">
											<button type="button"   id="contentBtn"  class="btn btn-primary btn-xs" >查看内容</button>	
										</a>
										<textarea id="messageContnet" name ="content" style="display:none">${result.content}</textarea>
									</td> 	
								</tr>											
							</c:forEach>
								<c:if test="${empty messageList}">
									<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
								</c:if>
							</tbody>
						</table>
						<!-- 分页 -->
						<c:if test="${not empty messageList}">
						<%@ include file="/include/page.jsp"%>
						</c:if>
						</div>
					  </div>
					</div>
				</div><!-- /.page-content -->
				
		<div class="modal fade" id="contentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">日志内容</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table">
		                <tr>
							<td class="td-right" >
								<textarea rows="20" cols="" name="content" id="content" clasS="width-100"></textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		           <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
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
<script type="text/javascript">
	function loadSchedulerLog(){
		$(".search-table #msgType").val($("#msgType3").val());
		$(".search-table #businessType").val($("#businessTypeTemp").val());
	}
	$(document).ready(function(){
		
		$('.clearschedulerLog').click(function(){
			$('.search-table #beginTime').val('');
			$('.search-table #endTime').val('');
			$('.search-table #msgTo').val('');
			$('.search-table #msgType').val('');
			$('.search-table #businessType').val('');
		}) 
		
		$('.buttonSearch').click(function(){
			var startDate =$("#beginTime").val();
			var endDate= $("#endTime").val();
			if("" != startDate && "" != endDate && startDate > endDate) 
			{
				$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
				return false;
			}
			var form = $('#messageForm');
			form.submit();
		});
		
		$('.contentBtn').click(function(){
			var messageContent = $(this).parent().parent().find("textarea[name='content']").val();
			$('#contentModal').on('show.bs.modal', function () {
				$('#contentModal #content').val(messageContent);
			});
			$('#contentModal').on('hide.bs.modal', function () {
				$('#contentModal #content').val('')
				
			});
		})
	});
</script>
</body>
</html>