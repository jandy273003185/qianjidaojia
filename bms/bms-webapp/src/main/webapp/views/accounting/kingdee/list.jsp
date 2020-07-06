<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.kingdee.controller.BmsClearKingdeePayPATH"%>
<%@page import="com.qifenqian.bms.accounting.accountMaintain.controller.BmsBaseBankAccountPath"%>
<%@page import="com.qifenqian.bms.accounting.kingdee.type.ClearKingdeeBusinessType"%>
<%@page import="com.qifenqian.bms.accounting.kingdee.type.ClearKingdeeBusinessBalStatusType"%>
<%@page import="com.qifenqian.bms.accounting.kingdee.type.ClearKingdeeBusinessBankStatusType"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>金蝶代付交易查询</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
</head>
<body onload="loadIn()">
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
							<ul id="myTab" class="nav nav-tabs">
						   		<li class="active"><a href="#kingdee" data-toggle="tab" id="kingdeeData">金蝶待付数据信息</a></li>
						   		<li><a href="#MyAccount"  data-toggle="tab"  id="MyAccountData">我方账户信息</a></li>
						    	<!-- <li><a href="#otherAccount"  data-toggle="tab"  id="otherAccountData">对方账户信息</a></li> -->
							</ul> 
							<div class="table-responsive">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="kingdee">
							   			<form action='<c:url value="<%=BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.LIST%>"/>'method="post" id="searchForm">
											<table class="search-table">
											<tr>
												<td class="td-left">清算编号</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="text" id="clearId" name="clearId" value="${bmsClearKingdeePayBean.clearId}">
														<i class="icon-leaf blue"></i>
													</span>
												</td>
												<td class="td-left">业务编号</td>
												<td class="td-right" colspan="3">
													<span class="input-icon" >
														<input type="text" name="operId"  id="operId" value="${bmsClearKingdeePayBean.operId }" />
														<i class="icon-leaf blue"></i>
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">业务类型</td>
												<td class="td-right">
													<span class="input-icon">
														<select id="businessType" name="businessType">
															<option value="">--请选择--</option>
															<c:forEach items="<%=ClearKingdeeBusinessType.values() %>" var="type">
																<option value="${type }" <c:if test="${type == bmsClearKingdeePayBean.businessType}">selected</c:if> >--${type.desc}--</option>						
															</c:forEach>
														</select>
													</span>
												</td>
												<td class="td-left">业务日期</td>
												<td class="td-right" colspan="3">
													<span class="input-icon">
														<input type="text" name="beginDate"  id="beginDate" value="${bmsClearKingdeePayBean.beginDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
														-
														<input type="text" name="endDate"  id="endDate" value="${bmsClearKingdeePayBean.endDate }" readonly="readonly" onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
													</span>
												</td>
											</tr>
											<tr>
												<td class="td-left">是否期初单据</td>
												<td class="td-right">
													<span class="input-icon">
														<input type="hidden" id="fisinithidden" value="${bmsClearKingdeePayBean.fisinit }"/>
														<select id="fisinit" name="fisinit">
															<option value="">--请选择--</option>
															<option value="true">是</option>
															<option value="false">否</option>
														</select>
													</span>
												</td>
												<td class="td-left">对账状态</td>
												<td class="td-right">
													<span class="input-icon">
														<select id="balStatus" name="balStatus">
															<option value="">--请选择--</option>
															<c:forEach items="<%=ClearKingdeeBusinessBalStatusType.values()%>" var="balStatus">
																<option value="${balStatus}"<c:if test="${balStatus==bmsClearKingdeePayBean.balStatus }">selected</c:if>  >--${balStatus.desc}--</option>
															</c:forEach>
														</select>
													</span>
												</td>
												<td class="td-left">银行处理状态</td>
												<td class="td-right">
													<span class="input-icon">
														<select id="bankStatus" name="bankStatus">
															<option value="">--请选择--</option>
															<c:forEach items="<%=ClearKingdeeBusinessBankStatusType.values()%>" var="bankStatus">
																<option value="${bankStatus}"<c:if test="${bankStatus==bmsClearKingdeePayBean.bankStatus }">selected</c:if>  >--${bankStatus.desc}--</option>
															</c:forEach>
														</select>
													</span>
												</td>
											</tr>
											<tr>
												<td colspan="6" align="center">
													<span class="input-group-btn">
														<gyzbadmin:function url="<%=BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.LIST %>">
															<button type="submit" id="searchSubmit" class="btn btn-purple btn-sm btn-margin" >
																查询
																<i class="icon-search icon-on-right bigger-110"></i>
															</button>
														</gyzbadmin:function>
														<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.LIST%>">
															<span class="input-group-btn" style="display:inline;">
																<a class="btn btn-purple btn-sm exportBut">
																	导出报表
																</a> 
															</span>
														</gyzbadmin:function>
													</span>
												</td>
											</tr>
										</table>
										</form>
										<div class="list-table-header">金蝶代付交易查询</div>
											<div class="table-responsive">
												<table id="sample-table-2" class="list-table">
													<thead>
														<tr>
															<th>清算编号</th>
															<th>业务类型</th>
															<th>业务编号</th>
															<th>业务日期</th>
															<th>是否期初单据</th>
															<th>写入日期</th>
															<th>交易提交状态</th>
															<th>返回信息</th>
															<th>对账状态</th>
															<th>银行处理状态</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${BmsClearKingdeePayList }" var="bmsClearKingdeePayBean">
														<tr class="statistic">
															<td>${bmsClearKingdeePayBean.clearId }</td>
															<td>
																${bmsClearKingdeePayBean.businessType}
															</td>
															<td>${bmsClearKingdeePayBean.operId }</td>
															<td>${bmsClearKingdeePayBean.date }</td>
															<td>${bmsClearKingdeePayBean.fisinit }</td>
															<td>${bmsClearKingdeePayBean.instDate }</td>
															<td>${bmsClearKingdeePayBean.rtnIsSuccess }</td>
															<td>${bmsClearKingdeePayBean.rtnMessage }</td>
															<td>${bmsClearKingdeePayBean.balStatus }</td>
															<td>${bmsClearKingdeePayBean.bankStatus}</td>
															<td>
																<input type="hidden" name="clearId" value="${bmsClearKingdeePayBean.clearId }" />
																<gyzbadmin:function url="<%=BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.KINGDEE_ENTRY_LIST%>">
																	<a href="#" class="" onclick="queryKingdeeEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
																		<button type="submit" class="btn btn-purple btn-sm">交易明细</button>
																	</a>
																</gyzbadmin:function>
															</td>
														</tr>
													</c:forEach>
													<c:if test="${empty BmsClearKingdeePayList}">
														<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
													</c:if>
													</tbody>
												</table>
												<!-- 分页 -->
												<c:if test="${not empty BmsClearKingdeePayList}">
												<%@ include file="/include/page.jsp"%>
												</c:if>
											</div>
									</div>
								</div>	
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
	<script type="text/javascript">
	
	/** 金蝶交易明细 **/
	 var winChild;
	function queryKingdeeEntry(obj){
		 var clearId = $(obj).parent().find('input[name="clearId"]').val();
		 var url=window.Constants.ContextPath+"<%=BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.KINGDEE_ENTRY_LIST%>?clearId="+clearId; 
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
	 
	
	$(function () {	
		$('.clearBtn').click(function(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		
		
		//按条件查询
		$("#searchSubmit").click(function(){
			
			var balDate =$("#beginDate").val();
			//alert(balDate)
			var endBalTime= $("#endDate").val();
			if(''!=balDate&&''!=endBalTime){
				if(balDate > endBalTime) 
				{
					$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
					return false;
				}
			}
		
		});

		$(".exportBut").click(function(){
			
			var beginDate = $('.search-table #beginDate').val();
			var endDate = $('.search-table #endDate').val();
			var clearId = $('.search-table #clearId').val();
			var businessType = $('.search-table #businessType').val();
			var operId = $('.search-table #operId').val();
			var fisinit = $('.search-table #fisinit').val();
			var balStatus = $('.search-table #balStatus').val();
			var bankStatus =  $('.search-table #bankStatus').val();
			var src ="<%= request.getContextPath()+ BmsClearKingdeePayPATH.BASE+BmsClearKingdeePayPATH.EXPORT%>?clearId="+
				clearId+
				"&beginDate="+
				beginDate+
				"&endDate="+
				endDate+
				"&businessType="+
				businessType+
				"&operId="+
				operId+
				"&fisinit="+
				fisinit+
				"&balStatus="+
				balStatus+
				"&bankStatus="+bankStatus;
			
			$(".exportBut").attr("href",src);
		}); 
		
		
		var flag2=true;
		 $("#kingdeeData").click(function(){
			 flag2=true;
				window.location.href="<%=request.getContextPath()+BmsClearKingdeePayPATH.BASE + BmsClearKingdeePayPATH.LIST%>"
			 });
		 $("#MyAccountData").click(function(){
				window.location.href="<%=request.getContextPath()+BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST%>";
			 });
		 $("#otherAccountData").click(function(){
				window.location.href="<%=request.getContextPath()+BmsBaseBankAccountPath.BASE + BmsBaseBankAccountPath.LIST%>";
			 });
		if(flag2==true){
	    	   $('#myTab li:eq(0) a').tab('show');	    
	       }
	    
	   });
	
	function loadIn(){
		$("#fisinit").val($("#fisinithidden").val());
	}
	</script>
</body>
</html>					