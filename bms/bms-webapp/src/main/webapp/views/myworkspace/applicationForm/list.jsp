<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.myworkspace.applicationForm.ApplicationFormPath"%>
<%@page import="com.qifenqian.bms.platform.web.myWorkSpace.WorkSpacePath" %>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>

<html>
<head>
	<meta charset="utf-8" />
	<title>我的申请单</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

$(function(){
	$('.btn-sm').click(function(){
		var startDate =$("#applyBeginTime").val();
		var endDate= $("#applyEndTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			alert("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#applitionForm');
		form.submit();
	});
	
	$(".preview").click(function(){
		var value = $(this).prev().val();
		
		$.post(window.Constants.ContextPath + '<%=ApplicationFormPath.BASE + ApplicationFormPath.VIEW %>',{
			'bussessKey' :value
			},function(data){
				if(data.result=="SUCCESS"){
					$("#viewFormModal .custName").text(data.info.custName);
					$("#viewFormModal .businessLicense").text(data.info.businessLicense);
					 var regAddr=data.info.businessRegAddr.split(",");
					$("#viewFormModal #province").val(regAddr[0]);
					$("#viewFormModal .businessTerm").text(data.info.businessTerm);
					$("#viewFormModal .custAdd").text(data.info.custAdd);
					$("#viewFormModal .contactPhone").text(data.info.contactPhone);
					$("#viewFormModal .openName").text(data.info.custName);
					$("#viewFormModal #bank").val(data.info.compAcctBank);
					$("#viewFormModal .bankCard").text(data.info.compMainAcct);
					$("#viewFormModal .representativeName").text(data.info.representativeName);
					$("#viewFormModal .representativeCertNo").text(data.info.representativeCertNo);
					$("#viewFormModal .representativeMobile").text(data.info.representativeMobile);
					$("#viewFormModal .rule").text(data.info.feeCode);
					
					// 初始化城市
					$.getJSON(window.Constants.ContextPath +'/basemanager/city/getCitys', { 
							provinceId : $('#viewFormModal #province').val() 
						}, function(data) { 
							// 如果有子选项，则创建子下拉框 
							if(data != null){ 
								//清空子下拉框内容 
								$("#city").empty();  
								// 遍历json串，填充子下拉框 
								$.each(data.city, function(i, item) { 
									var isSelect = regAddr[1] == item.cityId ? 'selected' : '';   
									$("#city").append( 
									"<option class='cityOption' value='"+item.cityId+"' "+isSelect+">" + item.cityName 
									+ "</option>"); 
									
								}); 
							} 
						}); 
					
					var idCard1='<img onclick="bigImg(this)" style="width:120px;height:100px;" src="'+window.Constants.ContextPath +'<%=WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path='+data.idcard1+ '" />';
					var idCard2='<img onclick="bigImg(this)" style="width:120px;height:100px;" src="'+window.Constants.ContextPath +'<%=WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path='+data.idcard2+ '" />';
					var businessImage='<img onclick="bigImg(this)" style="width:120px;height:100px;" src="'+window.Constants.ContextPath +'<%=WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path='+data.businessImage+ '" />';
					alert(businessImage);
					var certAttribute='<img onclick="bigImg(this)" style="width:120px;height:100px;" src="'+window.Constants.ContextPath +'<%=WorkSpacePath.BASE+WorkSpacePath.SHOW%>?path='+data.certAttribute+ '" />';
					$("#viewFormModal .idcard1").html(idCard1);
					$("#viewFormModal .idCard2").html(idCard2);
					$("#viewFormModal .businessImage").html(businessImage);
					$("#viewFormModal .certAttribute").html(certAttribute);
				}
			},'json'
			);
	});
});

function bigImg(obj){
    $('#showImageDiv #showImage').attr("src",obj.src);
};
</script>
<body>
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
				
				<form action='<c:url value="<%=ApplicationFormPath.BASE+ApplicationFormPath.LIST%>"/>' method="post" id="applitionForm">
							<table class="search-table">
								<tr>
									<td class="td-left" >申请单编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="id" id="id" placeholder="申请单" value="${ids}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">申请人</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="startUserId" id="startUserId" placeholder="申请人"  value="${startUserId }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">申请开始日期</td>
									<td class="td-right">
										 <input type="text" name="applyBeginTime"  id="applyBeginTime" readonly="readonly" value="${applyBeginTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="applyEndTime" id="applyEndTime" readonly="readonly" value="${applyEndTime }" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center" >
										<span class="input-group-btn">
											
											<button type="button" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</span>
									</td>
								</tr>
							</table>
							</form>
				
					<div class="row">
						<div class="col-xs-12">
							<div class="list-table-header">
								申请单列表
							</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>申请单编号</th>
											<th>申请人</th>
											<th>任务流程名称</th>
											<th>当前审核人员</th>
											<th>当前审核任务</th>
											<th>申请时间</th>
											<th>是否结束</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${forms}" var="form">
											<tr >
												<td>
												<%-- <a href="<%=request.getContextPath()+ApplicationFormPath.BASE + ApplicationFormPath.URL%>?id=${form.id }&procDefId=${form.procDefId}">${form.id }</a>
												<td> --%>
												<a href="<%=request.getContextPath()%>${form.url }?processInstanceId=${form.id}">${form.id }</a>
												</td>
												<td>${form.startUserId }</td>
												<td>${form.proName }</td>
												<td>${form.realName }</td>
												<td>${form.taskName }</td>
												<td>
												<fmt:formatDate value="${form.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>${form.isOver }</td>
												<%-- <td>
												<input type="hidden" value="${form.businessKey }" class="businessKey"/>
												<a href="#"  data-toggle='modal' class="tooltip-error preview" data-rel="tooltip" title="预览" data-target="#viewFormModal">
														<span class="green">
															<i class="iconfont icon-shenhe "></i>
														</span>
												</a>
												</td> --%>
											</tr>
										</c:forEach>
										
										<c:if test="${empty forms}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty forms}">
								<%@ include file="/include/page.jsp"%>
							</c:if> 
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
	
	<div class="modal fade" id="viewFormModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 700px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">申请单</h4>
		         </div>
		         <div class="modal-body">
		           <table id="sample-table-2" class="list-table" >
							    <tr>
									<td class="td-right" colspan="2" style="background-color:#ffbf66; height:30px; font-weight:bold;">企业信息：</td>
									
								</tr>
								<tr>
									<td class="td-left" width="30%" >公司名称：</td>
									<td class="td-right" width="70%" > 
										<span class="custName"></span>
									</td>
							   </tr>
								<tr>
									<td class="td-left"  >营业执照注册号：</td>
									<td class="td-right" > 
										<span class="businessLicense"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >营业执照所在地：</td>
									<td class="td-right" >
										<label> 
											<select id="province" name="province"> 
												<option value="">省份</option> 
												<c:forEach items="${provincelist}" var="pro"> 
												<option value="${pro.provinceId}">${pro.provinceName}</option> 
												</c:forEach> 
											</select> 
										</label> 
										<label> 
											<select id="city" name="city" > 
												<option value="">城市</option> 
											</select> 
										</label> 
										
										<span class="businessRegAddr"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >营业期限：</td>
									<td class="td-right" > 
										<span class="businessTerm">
											
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >企业地址：</td>
									<td class="td-right" > 
										<span class="custAdd"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >联系电话：</td>
									<td class="td-right" > 
										<span class="contactPhone"></span>
									</td>
								</tr>
								
								
								<tr>
										<td class="td-left">营业执照扫描件：</td>
										<td class="td-right">
											<a data-toggle='modal' class="tooltip-success businessImage"  data-target="#previewImageModal">
											</a>
											
										</td>
								</tr>
								<tr>
										<td class="td-left">开户许可证：</td>
										<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success certAttribute"  data-target="#previewImageModal">
										</a>
									</td>
									</tr>
									
								<tr>
								<td class="td-right" colspan="2" style="background-color:#ffbf66; height:30px; font-weight:bold;">银行信息：</td>
								</tr>
								<tr>
									<td class="td-left" >银行开户名：</td>
									<td class="td-right" > 
										<span class="openName"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >开户银行：</td>
									<td class="td-right" > 
										  <sevenpay:selectBankTag id="bank" name ="bank"  banks="${banklist}" />
									</td>
								</tr>
								<tr>
									<td class="td-left" >银行账号：</td>
									<td class="td-right"> 
									
										<span class="bankCard"></span>
									</td>
								</tr>
								<tr>
									<td class="td-right" colspan="2" style="background-color:#ffbf66; height:30px; font-weight:bold;">法人信息：</td></tr>
								<tr>
									<td class="td-left" >法人真实姓名：</td>
									<td class="td-right" > 
										<span class="representativeName"></span>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人身份证号码：</td>
									<td class="td-right" > 
										<span class="representativeCertNo"></span>
									</td>
								</tr>
								<tr>
								   <td class="td-left" >身份证正面：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success idcard1"  data-target="#previewImageModal">
										</a>
										
									</td>
								</tr>
								<tr>
									<td class="td-left" >身份证背面：</td>
									<td class="td-right" > 
										<a data-toggle='modal' class="tooltip-success idcard2"  data-target="#previewImageModal">
										</a>
									</td>
								</tr>
								<tr>
									<td class="td-left" >法人手机号码：</td>
									<td class="td-right"  > 
										<span class="representativeMobile"></span>
									</td>
								</tr>	
								
								<tr>
									<td class="td-right" colspan="2" style="background-color:#ffbf66; height:30px; font-weight:bold;">费率方式：</td></tr>
								<tr>
									<td class="td-left" >费率方式：</td>
									<td class="td-right"> 
										<span class="rule"></span>
									</td>
								</tr>
							</table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		         <div id="showImageDiv" style="width:120%;height:60%;">
		           <img id="showImage" style="width:100%;height:100%;">
		         </div>
		     </div>
		</div>
</body>

<script type="text/javascript">
	
</script>
</html>

