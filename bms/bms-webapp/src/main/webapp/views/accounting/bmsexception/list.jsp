<%@page import="com.qifenqian.bms.accounting.bmsexception.BmsExceptionPath"%>
<%@page import="com.qifenqian.bms.accounting.bmsexception.type.BusType"%>
<%@page import="com.qifenqian.bms.platform.common.utils.ReflectUtils"%>
<%@page import="com.qifenqian.bms.accounting.bmsexception.bean.BmsException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>异常管理列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script type="text/javascript">

function loadBmsException(){
	$('.search-table #currentState').val($('.search-table #currentStateTemp').val());
	$('.search-table #model').val($('.search-table #modelTemp').val());
}
$(document).ready(function(){
	
	$('.clearBmsException').click(function(){
		$(':input','#bmsExceptionForm')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
	
	$(".buttonSearch").click(function(){
		var startDate = $("#beginCreateTime").val();
		var endDate= $("#endCreateTime").val();
		
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		
		var startUpdateDate = $("#beginUpdateTime").val();
		var endUpdateDate= $("#endUpdateTime").val();
		
		if("" != startUpdateDate && "" != endUpdateDate && startUpdateDate > endUpdateDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#bmsExceptionForm');
		form.submit();
	});
	
	$('.exDescBtn').click(function(){
		var messageExDesc = $(this).parent().parent().find("textarea[name='messageExDesc']").val();
		$('#exDescModal').on('show.bs.modal', function () {
			$('#exDescModal #exDesc').val(messageExDesc);
		});
		$('#exDescModal').on('hide.bs.modal', function () {
			$('#exDescModal #exDesc').val('');
		});
	});
});
</script>
<body onload="loadBmsException()">
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="main-container-inner">
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<%@ include file="/include/path.jsp"%>
				<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						  <form action='<c:url value="<%=BmsExceptionPath.BASE + BmsExceptionPath.LIST%>"/>' method="post" id="bmsExceptionForm">
						  <% BmsException queryBean = (BmsException)pageContext.findAttribute("queryBean"); %>
							<table class="search-table">
								<tr>
									<td class="td-left" >订单编号</td>
									<td class="td-right">
										<input type="text" id="orderId" name="orderId" value="${queryBean.orderId }"/>
									</td>
									<td class="td-left" >当前状态</td>
									<td class="td-right">
										<input type="hidden" id="currentStateTemp" name="currentStateTemp" value="${queryBean.currentState }"/>
										<select id="currentState" name="currentState">
											<option value="">- 请选择 -</option>
											<option value="CREATE">创建</option>
											<option value="UNDERWAY">处理中</option>
											<option value="END">已解决</option>
											<option value="PENDING">暂挂</option>
										</select>
									</td>
									<td class="td-left" >创建时间</td>
									<td class="td-right">
										 <input type="text" id="beginCreateTime" name="beginCreateTime" readonly="readonly" value="${queryBean.beginCreateTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" />
										 	-
										 <input type="text" id="endCreateTime" name="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" />
									</td>
								</tr>
								<tr>
									<td class="td-left" >模块</td>
									<td class="td-right">
										<input type="hidden" id="modelTemp" name="modelTemp" value="${queryBean.model }"/>
										<select id="model" name="model">
											<option value="">- 请选择 -</option>
											<option value="OPER">七分钱前端客户系统</option>
											<option value="CORE">七分钱核心系统</option>
											<option value="BMS">七分钱后台系统</option>
											<option value="GATEWAY">网关系统</option>
										</select>
									</td>
									<td class="td-left" >业务类型</td>
									<td class="td-right">
										<select id="busType" name="busType">
											<option value="">- 请选择 -</option>
											<% for(BusType busType : BusType.values()) { %>
												<option value="<%=busType.name() %>" <% if(busType == queryBean.getBusType()){ %>selected<% } %>> <%=ReflectUtils.getDesc(busType) %> </option>
											<% } %>
										</select>
									</td>
									<td class="td-left" >最后修改时间</td>
									<td class="td-right">
										 <input type="text" id="beginUpdateTime" name="beginUpdateTime" readonly="readonly" value="${queryBean.beginUpdateTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" />
										 	-
										 <input type="text" id="endUpdateTime" name="endUpdateTime" readonly="readonly" value="${queryBean.endUpdateTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" />
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=BmsExceptionPath.BASE + BmsExceptionPath.LIST%>">
											<button type="submit" class="btn btn-purple btn-sm buttonSearch">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBmsException"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								异常管理列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>订单编号</th>
											<th>异常类型</th>
											<th>级别</th>
											<th>模块</th>
											<th>业务类型</th>
											<th>异常码</th>
											<th>当前状态</th>
											<th>创建时间</th>
											<th>最后更新时间</th>
											<th>备注</th>
											<th>异常信息描述</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bmsExceptionList}" var="bmsException">
										<% BmsException bmsException = (BmsException)pageContext.findAttribute("bmsException"); %>
											<tr class="bmsException">
											    <td>${bmsException.orderId}</td>
												<td>
													<c:if test="${bmsException.exceType=='BUSINESS'}">业务</c:if>
													<c:if test="${bmsException.exceType=='SYSTEM'}">系统</c:if>
												</td>
												<td>
													<c:if test="${bmsException.level=='EXIGENCY'}">紧急</c:if>
													<c:if test="${bmsException.level=='HIGH'}">高</c:if>
													<c:if test="${bmsException.level=='GENERAL'}">一般</c:if>
												</td>
												<td>
													<c:if test="${bmsException.model=='OPER'}">七分钱前端客户系统</c:if>
													<c:if test="${bmsException.model=='CORE'}">七分钱核心系统</c:if>
													<c:if test="${bmsException.model=='BMS'}">七分钱后台系统</c:if>
													<c:if test="${bmsException.model=='GATEWAY'}">网关系统</c:if>
												</td>
												<td><%=ReflectUtils.getDesc(bmsException.getBusType()) %></td>
												<td>${bmsException.exCode}</td>
												<td>
													<c:if test="${bmsException.currentState=='CREATE'}">创建</c:if>
													<c:if test="${bmsException.currentState=='UNDERWAY'}">处理中</c:if>
													<c:if test="${bmsException.currentState=='END'}">已解决</c:if>
													<c:if test="${bmsException.currentState=='PENDING'}">暂挂</c:if>
												</td>
												<td>${bmsException.createDate}</td>
												<td>${bmsException.updateDate}</td>
												<td>${bmsException.description}</td> 
												<td>
													<a href="#exDescModal"  data-toggle='modal' class="tooltip-error exDescBtn" data-rel="tooltip" title="查看内容">
														<button type="button"   id="exDescBtn"  class="btn btn-primary btn-xs" >查看内容</button>	
													</a>
													<textarea id="messageContnet" name ="messageExDesc" style="display:none">${bmsException.exDesc}</textarea>
												</td> 
											</tr>
										</c:forEach>
										<c:if test="${empty bmsExceptionList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty bmsExceptionList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
					<div class="modal fade" id="exDescModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">异常信息描述</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					                <tr>
										<td class="td-right" >
											<textarea rows="20" cols="" name="exDesc" id="exDesc" clasS="width-100"></textarea>
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
				</div><!-- /.page-content -->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
</body>
</html>