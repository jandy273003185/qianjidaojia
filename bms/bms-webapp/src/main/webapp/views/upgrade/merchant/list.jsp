<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.upgrade.merchant.MerchantListPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商户列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
	
	.selector{
    height:100px;
    top:50%;
    margin-top:-50px;
    position: absolute;
    }
}
</style>
</head>
<script type="text/javascript">
	$(function(){
		//导出商户数据
		$('.exportBut').click(function(){
			var merchantCode = $('.search-table #merchantCode').val();
			var startCreateTime = $('.search-table #startCreateTime').val();
			var endCreateTime = $('.search-table #endCreateTime').val();
			var auditState = $('.search-table #auditState').val();
			var custName = $('.search-table #custName').val();
			var email = $('.search-table #email').val();
			var merchantState = $('.search-table #merchantState').val();
			
			var src ="<%= request.getContextPath()+ MerchantListPath.BASE+MerchantListPath.EXPORTMERCHANTINFO%>?merchantCode="+
			merchantCode+
			"&startCreateTime="+
			startCreateTime+
			"&endCreateTime="+
			endCreateTime+
			"&auditState="+
			auditState+
			"&custName="+
			custName+
			"&email="+
			email+
			"&merchantState="+
			merchantState;
			$(".exportBut").attr("href",src);
			
		});
	
	$('.clearMerchantSearch').click(function(){
		
		$('.search-table #startCreateTime').val('');
		$('.search-table #endCreateTime').val('');
		$('.search-table #merchantState').val('');
		$('.search-table #custName').val('');
		$('.search-table #email').val('');
		$('.search-table #mobile').val('');
		$('.search-table #auditState').val('');
		$('.search-table #merchantCode').val('');
	})
	});
	function openMerchentInfo(merchantCode){
		window.location.href="<%=MerchantListPath.BASE + MerchantListPath.PREVIEW%>?merchantCode="+merchantCode;
	}
	
	function loadMerchant(){
		$('.search-table #auditState').val($("#state_02").val());
		$('.search-table #merchantState').val($("#merchantState_02").val());
		
	}
	
</script>
<body  onload="loadMerchant()" style="overflow-x:hidden;">
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
						<input id="state_02" value="${queryBean.auditState }"  type="hidden">
						<input id="merchantState_02" value="${queryBean.merchantState }"  type="hidden">
						<input type="hidden" id="businessPhototemp"/>
						<input type="hidden" id="certAttribute0temp"/>
						<input type="hidden" id="certAttribute1temp" />
						<input type="hidden" id="certAttribute2temp" />
						<input type="hidden" id="doorPhototemp" />
							<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=MerchantListPath.BASE + MerchantListPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${queryBean.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">注册时间：</td>
									<td class="td-right">
									<input type="text" name="startCreateTime"   id="startCreateTime" readonly="readonly" value="${queryBean.startCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endCreateTime"   id="endCreateTime" readonly="readonly" value="${queryBean.endCreateTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									</td>
									<td class="td-left">商户状态：</td>
									<td class="td-right">								   
										 <select name="merchantState" id="merchantState" >
										  <option  value="" >请选择 </option>
										  <option  value="00"> 正常 </option>
										  <option  value="01"> 停用 </option>
										  <option  value="02"> 登录账户冻结 </option>
										  <option  value="03"> 注册待激活 </option>
										  <option  value="04"> 商户审核中 </option>
										  <option  value="05"> 商户协议待录入 </option>
										   <option  value="06"> 后台商户协议待录入 </option>
										   <option  value="07"> 审核不通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									<td class="td-left" >客户经理：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custManager" id="custManager"  placeholder="请输入客户经理名字" value="${queryBean.custManager }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${queryBean.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >邮箱账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" id="email"  value="${queryBean.email }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >手机账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile"  value="${queryBean.mobile }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">审核状态：</td>
									<td class="td-right">								   
										<select name="auditState" id="auditState" >
										  <option  value="" > 请选择 </option>
										  <option  value="00"> 审核通过 </option>
										  <option  value="01"> 待审核 </option>
										  <option  value="04"> 审核不通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									</tr>
									<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=MerchantListPath.BASE + MerchantListPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">
													导出报表
												</a> 
											</span> 
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">商户列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th width="7%">商户编号</th>
											<th width="7%">邮箱账号</th>
											<th width="7%">手机账号</th>
											<th width="7%">商户名称</th>
											<th width="7%">服务商名称</th>
											<th width="7%">客户经理</th>
											<th width="10%">注册时间</th>	
											<th width="10%">银行开户名</th>	
											<th width="10%">商户状态</th>								
											<th width="7%">审核状态</th>
											<th width="13%">审核信息</th>	
											<th width="5%">审核人</th>
											<th width="10%">操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${merchantList }" var="merchant">
										<tr class="merchant" id="merchant">
											<td>${merchant.merchantCode }</td>
											<td>${merchant.email }</td>
											<td>${merchant.mobile }</td>
											<td>${merchant.custName }</td>
											<td>${merchant.facilitatorName }</td>
											<td>${merchant.custManagerName }</td>
											<td>
											 <%-- <fmt:formatDate value="${merchant.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
											 ${merchant.createTime }
											</td>
											<td>${merchant.bankAcctName }</td>
											<td>
												<c:choose>   
							                        <c:when test="${merchant.merchantState =='00'}">  									  
							                           	 正常								  
							                        </c:when>  
							  						<c:when test="${merchant.merchantState =='01'}">  									  
							                           	停用							  
							                        </c:when> 
							                        <c:when test="${merchant.merchantState =='02'}">  									  
							                           	 登录账户冻结 								  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='03'}">  									  
							                           	注册待激活 								  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='04'}">  									  
							                           	商户审核中							  
							                        </c:when>
							                         <c:when test="${merchant.merchantState =='05'}">  									  
							                           	商户协议待录入							  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='06'}">  									  
							                           	后台商户协议待录入							  
							                        </c:when>
							                        <c:when test="${merchant.merchantState =='07'}">  									  
							                           	审核不通过							  
							                        </c:when>
	                                             </c:choose>
											</td>
										
											<td>
												<c:choose>   
							                          <c:when test="${merchant.auditState =='00'}">  									  
							                                                                        审核通过  								  
							                        </c:when>  
							  						<c:when test="${merchant.auditState =='01'}">  									  
							                           	 待审核  								  
							                        </c:when> 
							                        <c:when test="${merchant.auditState =='02'}">  									  
							                           	注销								  
							                        </c:when>
							                        <c:when test="${merchant.auditState =='03'}">  									  
							                           	冻结								  
							                        </c:when>
							                        <c:when test="${merchant.auditState =='04'}">  									  
							                           	审核不通过							  
							                        </c:when>
	                                             </c:choose>
											</td>
											<td>${merchant.aduitMessage}</td>
											<td>${merchant.aduitUserName}</td>
											<td style="word-break:keep-all;">	
												<c:choose>   
							  						<c:when test="${merchant.auditState=='01'}">  									  
							                          <button type="button"  id="btnEmail2" onclick="openMerchentInfo('${merchant.merchantCode}')" data-toggle='modal'  data-target="#" class="btn btn-primary btn-xs"  >审核</button>				  
							                        </c:when> 
							                        <c:otherwise>  								  
							                          <a href="#" onclick="openMerchentInfo('${merchant.merchantCode}')" data-toggle='modal'  data-target="#">预览</a> 
							                        </c:otherwise>    
	                                            </c:choose>  	 
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty merchantList}">
										<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty merchantList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
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
	
		</body>
</script>
</html>