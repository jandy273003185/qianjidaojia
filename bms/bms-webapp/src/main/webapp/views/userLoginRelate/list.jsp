<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<script src="/static/topayProfit/layui/layui.js"></script>
<script src="/static/topayProfit/layui/layui.all.js"></script>
<link href="/static/topayProfit/layui/css/layui.css" rel="stylesheet">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>服务商的业务员信息</title>
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

jQuery(function($){
			
	// 为每个tr缓存数据
	 var merchantProducts= '<c:out value="${gyzb:toJSONString(userLoginRelateList)}" escapeXml="false"/>';
	 var merchantProductList=$("tr.merchantProduct");
	 $.each($.parseJSON(merchantProducts),function(i,value){		 
	 	$.data(merchantProductList[i],"merchantProduct",value);
		}); 

	});
	



</script>	

<body>
	<!-- 物料信息 -->
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
							<form action='<c:url value="/userLoginRelate/list"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >客户名：</td>
										<td class="td-right" > 
										<span class="input-icon">
	
										 	<select name="custId" id="custId" class="selectpicker show-tick form-control" data-width="250px"   data-live-search="true">
												<option value="">--输入商户名查询--</option>
												<c:if test="${not empty tdCustInfoList }">
													<c:forEach items="${tdCustInfoList }" var="bean">
														<option value="${bean.custId }" <c:if test='${bean.custId == userLoginRelate.custId}' > selected </c:if>   >${bean.custName }</option>
													</c:forEach>
												</c:if>
											</select>
										</span>
									</td> 
									
									<td class="td-left" >时间：</td>
									<td>
										<input type="text" id="startTime" name="startTime" value="${userLoginRelate.startTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:20%"/>
										<label class="label-tips" id="startTimeLabStart"></label>
											-
										<input type="text" id="endTime" name="endTime" value="${userLoginRelate.endTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:20%"/>
										<label class="label-tips" id="endTimeLabEnd"></label>
									</td>
								</tr>
								<%-- <tr>
									
									<td class="td-left" >时间：</td>
									<td>
										<input type="text" id="startTime" name="startTime" value="${userLoginRelate.startTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:15%"/>
										<label class="label-tips" id="startTimeLabStart"></label>
											-
										<input type="text" id="endTime" name="endTime" value="${userLoginRelate.endTime }" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:15%"/>
										<label class="label-tips" id="endTimeLabEnd"></label>
									</td>
								</tr> --%>

								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="/userLoginRelate/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin buttonSearch">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
								
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户产品信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>客户名称</th>
											<th>用户类型</th>
											<th>登陆类型</th>
											<th>类型值编号</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>业务员所属服务商</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${userLoginRelateList}" var="userLoginRelate">
											<tr class="merchantProduct">
											
												<td>${userLoginRelate.userName}</td>
												
												<td>
													<c:if test="${userLoginRelate.userType =='per'}">
														个人
													</c:if>
													<c:if test="${userLoginRelate.userType =='ent'}">
														企业
													</c:if>
													<c:if test="${userLoginRelate.userType =='agent'}">
														代理商
													</c:if>
													<c:if test="${userLoginRelate.userType =='cust'}">
														客户经理
													</c:if>
													<c:if test="${userLoginRelate.userType =='salesman'}">
														业务员
													</c:if>
													<c:if test="${userLoginRelate.userType =='cashier'}">
														收银员
													</c:if>
													<c:if test="${userLoginRelate.userType =='finance'}">
														财务员
													</c:if>
													<c:if test="${userLoginRelate.userType =='shopmanager'}">
														店长
													</c:if>
												</td>
												<td>
													<c:if test="${userLoginRelate.loginType =='1'}">
														微信
													</c:if>
													<c:if test="${userLoginRelate.loginType =='2'}">
														其他
													</c:if>
												 </td>
												<td>${userLoginRelate.openId}</td>	
												<td>
													<fmt:formatDate value="${userLoginRelate.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<fmt:formatDate value="${userLoginRelate.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>	
												<td>${userLoginRelate.custName}</td>	
																				
												<td>
													<input type="hidden" name="custId" value="${userLoginRelate.custId }"/>
													
													
													<a href="#" class="tooltip-success " onclick="del('${userLoginRelate.id}')" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>													
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty userLoginRelateList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty userLoginRelateList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
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
		<script type="text/javascript">
		$('.buttonSearch').click(function(){

	        var form = $('#merchantForm');
	        form.submit();
	    });
		
			$(document).ready(function(){
		        $('.clearBtn').click(function(){
		            $('.search-table #custId').val('');
		            $('.search-table #startTime').val('');
		            $('.search-table #endTime').val('');
		            window.location.href='/userLoginRelate/list';
		        })
		    })
			function del(id){
			
				
				layer.confirm('确定删除账户吗', {icon: 3, title:'提示'}, function(index){
					  layer.close(index);
						$.post(window.Constants.ContextPath + '/userLoginRelate/del', 
							{
								'id' 	: id
							}, function(data) {
								if(data.result == 'SUCCESS'){
									 layer.msg("删除成功");  
									window.location.reload();
								} else {
									$.gyzbadmin.alertFailure('删除失败:' + data.message);
								}
							}, 'json'
						);		
				});//layui 结束	
			}


		</script>	
			
						
					<!-- <div class="modal fade" id="deleteMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					            <table class="modal-input-table">
					             <tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="custId" name="custId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									
							
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMerchantProductBtn" >确定</button>
					         </div>
					      </div>/.modal-content
					 </div>
				</div>/.modal -->
				
  </body>
</html>