<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.certify .CertifyPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath"%>
<base target="_self">  
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台商户注册审核列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- <style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style> -->
</head>
<script type="text/javascript" language="javascript">
$(function() {
	//清空查询框
	$('.clearMerchantSearch').click(function(){
	
		$('.search-table #fileType').val('');
		$('.search-table #workDate').val('');
		
	
	});
	
	
</script>
<body  >
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
						<form  id="agentForm" action='<c:url value="<%=CertifyPath.BASE + CertifyPath.FILE_LIST %>"/>' method="post">
							<table class="search-table">
								 <tr>
									<td class="td-left" >文件类型：</td>
									<td class="td-right" > 
										
										    <select name="fileType" id="fileType" >
												  <option  value="" > 请选择 </option>
												  <option  value="REQ">请求文件</option>
												  <option  value="RES">返回文件</option>
										 	</select>
									</td>

									<td class="td-left" >数据日期：</td>
									<td class="td-right" > 
								
										<span class="input-icon">
											<input type="text" name="workDate"   id="workDate" readonly="readonly" value="${queryBean.workDate }"  
											onfocus="AutoDatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CertifyPath.BASE + CertifyPath.FILE_LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">商户签约列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th>文件编号</th>
											<th>上传日期</th>
											<th>文件名</th>	
											<th>存储路径</th>
											<th>文件类型</th>
											<th>总记录数</th>
											<th>文件状态</th>		
											<th>写入日期</th>					
											<th>数据日期</th>
											<th>返回编号</th>
											<th>写入时间</th>							
											<th>备注</th>				
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${fileList }" var="bean">
										<tr class=sign id="sign">
											<td>${bean.fileId}</td>
											<td>
											 ${bean.uploadDate }
											</td>
											<td>${bean.fileName}</td>
											<td style="width:30">${bean.storagePath}</td>
											<td>
												<c:choose>   
							                        <c:when test="${bean.fileType =='REQ'}">  									  
							                           	 请求文件								  
							                        </c:when>  
							  						<c:when test="${bean.fileType =='RES'}">  									  
							                           	 返回文件								  
							                        </c:when>  
	                                             </c:choose>
											</td>
											<td>${bean.totalCount}</td>
											<td>
												<c:choose>   
							                        <c:when test="${bean.status =='VALIDATE_OVER'}">  									  
							                           	 验证完成								  
							                        </c:when>  
							  						<c:when test="${bean.status =='TRANSFER_OVER'}">  									  
							                           	 传输完成								  
							                        </c:when>  
							                        <c:when test="${bean.status =='INVALID'}">  									  
							                           	 无效								  
							                        </c:when>  
							                        <c:when test="${bean.status =='ERROR'}">  									  
							                           	 异常								  
							                        </c:when>
	                                             </c:choose>
											</td>
											<td>${bean.instDate }</td>
											<td>${bean.workDate }</td>
											<td>
											 <fmt:formatDate value="${bean.instDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td>${bean.receiptId}</td>
											<td>${bean.memo}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty fileList}">
										<tr><td colspan="12" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty fileList}">
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


</html>