<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <link rel="stylesheet"  href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>

<html>
<head>
	<meta charset="utf-8" />
	<title>审核商户信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>

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
				
					<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- 查询条件 -->
							<form action='<c:url value="<%=AuditorPath.BASE + AuditorPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">开始日期：</td>
									<td class="td-right">
										<div id="datetimepicker1" class="input-append">
										    <input type="text"  data-format="yyyy-MM-dd" name="createTime"  id="createTime_01">
										    <span class="add-on">
										      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
										      </i>
										    </span>
										 </div>
									</td>
									<td class="td-left">结束日期：</td>
									<td class="td-right">
										<div id="datetimepicker2" class="input-append">
										    <input type="text"  data-format="yyyy-MM-dd" name="endCreateTime" >
										    <span class="add-on">
										      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
										      </i>
										    </span>
										 </div>
									</td>
									
									
									
								</tr>
									
								<tr>
									<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" placeholder="商户名称">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >账号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" placeholder="账号">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=AuditorPath.BASE + AuditorPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											
										</span>
									</td>
								</tr>
							</table>
							</form>

							<div class="list-table-header">审核商户</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr >
											<th>账号</th>
											<th>商户名称</th>
											<th>注册日期</th>	
											<th>创建人</th>
											<th>状态</th>									
											<th >操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${merchantList }" var="merchant">
										<tr class="merchant" id="merchant">
											<td>
												${merchant.email }
												
											</td>
											<td>${merchant.custName }</td>
											<td>${merchant.createTime }</td>
											<td>${merchant.createId }</td>
											<c:choose>   
									                        <c:when test="${merchant.state =='00'}">  									  
									                            <td>有效</td>  								  
									                        </c:when>  
									  						<c:when test="${merchant.state =='01'}">  									  
									                            <td> 待审核</td>  								  
									                        </c:when> 
									                        <c:when test="${merchant.state =='02'}">  									  
									                            <td> 注销</td>  								  
									                        </c:when>
									                        <c:when test="${merchant.state =='03'}">  									  
									                            <td>  冻结</td>  								  
									                        </c:when>
									                        <c:when test="${merchant.state =='04'}">  									  
									                            <td> 审核不通过</td>  								  
									                        </c:when>
									                        <c:otherwise>  								  
									                            <td>待激活</td>  									  
									                        </c:otherwise>    
                                               </c:choose>  
											
											<td>	
														<input type="hidden" name="custId_01" id="custId_01" value="${merchant.custId}">																																				
														<input type="hidden" name="emailMerchant" id="emailMerchant" value="${merchant.email}">	
														<input type="hidden" name="custName_01" id="custName_01" value="${merchant.custName}">																																																		
														<c:if test="${merchant.state=='01'}" >		<button type="button"    id="btnEmail2" onclick="auditorInfo(this)" data-toggle='modal'  class="btn btn-primary btn-xs" data-target="#auditorMachant"     >审核</button> </c:if>																																								
														<c:if test="${merchant.state!='01'}"><button type="button"  id="btnEmail" class="btn  btn-xs"  disabled="disabled" >审核</button> </c:if>														 																								
												</td>
										</tr>
									</c:forEach>
									<c:if test="${empty merchantList}">
										<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
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
	
	
	<div class="modal fade" id="auditorMachant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核商户</h4>
		         </div>
		         <div class="modal-body">
		         	
		         		
						<table id="sample-table-2" class="list-table" >
						    <tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">企业信息：</td>
								
							</tr>
							<tr>
								<td class="td-left" width="18%" >公司名称：</td>
								<td class="td-right" width="32%" > 
									<span class="custName"></span>
								</td>
						
								<td class="td-left" width="18%" >营业执照注册号：</td>
								<td class="td-right" width="32%"> 
									<span class="businessLicense"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >营业执照所在地：</td>
								<td class="td-right" >
									<input type="hidden" id="create_id" value="" />
									<input type="hidden"  id="email_02" value="" />
									<input type="hidden"  id="compAcctBank" value="" />
									<span class="businessRegAddr"></span>
								</td>
							
								<td class="td-left" >营业期限：</td>
								<td class="td-right" > 
									<span class="businessTerm"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >企业地址：</td>
								<td class="td-right" > 
									<span class="custAdd"></span>
								</td>
							
								<td class="td-left" >联系电话：</td>
								<td class="td-right" > 
									<span class="contactPhone"></span>
								</td>
							</tr>
							
							
							<tr>
									<td class="td-left">营业执照扫描件：</td>
									<td class="td-right">
										<a >									
										  <img  id="businessPhotoImage" src="" style="width:120px;height:100px;"   />										
										</a>
										
									</td>
									<td class="td-left">开户许可证：</td>
									<td class="td-right" > 
									<a >									
										  <img  id="certAttribute0Image" src=""  style="width:120px;height:100px;"  />										
									</a>
								</td>
								</tr>
								
							<tr>
							<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">银行信息：</td>
							</tr>
							<tr>
								<td class="td-left" >银行开户名：</td>
								<td class="td-right" > 
									<span class="openName"></span>
								</td>
							
								<td class="td-left" >开户银行：</td>
								<td class="td-right" > 
									<span class="bank"></span>
								</td>
							</tr>
							<tr>
								<td class="td-left" >银行账号：</td>
								<td class="td-right" colspan="3" > 
									<span class="bankCard"></span>
								</td>
							</tr>
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">法人信息：</td></tr>
							<tr>
								<td class="td-left" >法人真实姓名：</td>
								<td class="td-right" > 
									<span class="representativeName"></span>
								</td>
								<td class="td-left" >法人身份证号码：</td>
								<td class="td-right" > 
									<span class="representativeCertNo"></span>
								</td>
							</tr>
							<tr>
							   <td class="td-left" >身份证正面：</td>
								<td class="td-right" > 
									<a >									
										  <img  id="certAttribute1" src=""  style="width:120px;height:100px;"  />										
									</a>
									
								</td>
								<td class="td-left" >身份证背面：</td>
								<td class="td-right" > 
									<a >									
										  <img  id="certAttribute2" src=""  style="width:120px;height:100px;"  />										
									</a>
								</td>
							</tr>
							<tr>
								<td class="td-left" >法人手机号码：</td>
								<td class="td-right" colspan="3" > 
									<span class="representativeMobile"></span>
								</td>
							</tr>	
							
							<tr>
								<td class="td-right" colspan="4" style="background-color:#ffbf66; height:30px; font-weight:bold;">费率方式：</td></tr>
							<tr>
								<td class="td-left" >费率方式：</td>
								<td class="td-right" colspan="3"> 
									<span class="rule"></span>
								</td>
							</tr>
							<!-- <tr>
								<td colspan="4" align="center">
									<input type="button" value="提交"  class="submitbutton">
								</td>
							</tr> -->
						</table>

		         </div>
		         <div class="modal-footer">
		            
		            <button type="button" class="btn btn-primary addadBtn" onclick="noPassAuditor()"style="margin-right: 329px;">审核不通过</button>
		            <button type="button" class="btn btn-primary addadBtn" onclick="sureAuditor()">确定审核</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
	
		
		
</body>

<script type="text/javascript">
var custId;
function auditorInfo(obj){  	
	custId=$(obj).parent().find('#custId_01').val();
	
	var bl='02'
	$("#businessPhotoImage").attr("src","<%=url+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType="+bl+"");
	$("#certAttribute1").attr("src","<%=url+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=00&front=0");
	$("#certAttribute2").attr("src","<%=url+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=00&front=1");
	$("#certAttribute0Image").attr("src","<%=url+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03");
	
	//alert(window.Constants.ContextPath);
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath+'<%=AuditorPath.BASE+ AuditorPath.SELECTIDAUDITORINFO %>',
		data:
		{
			"custId" 	: custId
		},
		success:function(data){
			//$.unblockUI();	
		
		
		$("#email_02").val(data.merchantVo.email);
		$("#create_id").val(data.merchantVo.createId);
		$("#compAcctBank").val(data.merchantVo.compAcctBank);
		
		$(".custName").html(data.merchantVo.custName);
		$(".businessLicense").html(data.merchantVo.businessLicense);

		$(".businessRegAddr").html(data.merchantVo.businessRegAddr);
		/*  var businessRegAddr=data.merchantVo.businessRegAddr.split(",");
		alert(businessRegAddr[1]);
		$("#province").text(businessRegAddr[0]);
		$("#city").text(businessRegAddr[1]);  */
		$(".custAdd").html(data.merchantVo.custAdd);
		$(".businessTerm").html(data.merchantVo.businessTerm);
		$(".contactPhone").html(data.merchantVo.contactPhone);
		
		//alert(data.merchantVo.businessPhoto);
		//$("#businessPhoto").val("11.jpg");
		
		$(".orgInstCode").html(data.merchantVo.orgInstCode);
		//alert(data.merchantVo.representativeMobile);
		$(".openName").html(data.merchantVo.custName);
		$(".bank").html(data.merchantVo.bankName);
		$(".bankCard").html(data.merchantVo.compMainAcct);
		$(".representativeName").html(data.merchantVo.representativeName);
		$(".representativeCertNo").html(data.merchantVo.representativeCertNo);
		$(".representativeMobile").html(data.merchantVo.representativeMobile);
		$(".rule").html(data.merchantVo.feeCode);				
		}
	});
}

function sureAuditor(){
	var email=$("#email_02").val();
	var custName=$(".custName").text();
	var compMainAcct=$(".bankCard").text();
	var representativeMobile=$(".representativeMobile").text();
	var compAcctBank=$("#compAcctBank").val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=AuditorPath.BASE + AuditorPath.SUREAUDITOR %>',
		data:
		{
			"custId" :custId,
			"email":email,
			"custName":custName,
			"compMainAcct":compMainAcct,
			"representativeMobile":representativeMobile,
			"compAcctBank":compAcctBank
		},
		
		success:function(data){
			
			if(data.result=="success"){
				$("#auditorMachant").hide();
				$.gyzbadmin.alertSuccess('审核商户成功！', null, function(){
					window.location.href = window.Constants.ContextPath + '<%=AuditorPath.BASE+AuditorPath.LIST %>';
					//window.location.reload();
				});
			}else{
			
				$.gyzbadmin.alertFailure("审核商户失败！"+data.result);
			}
		}
	});
}

function noPassAuditor(){
	var email=$("#email_02").val();
	var createId=$("#create_id").val();
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=AuditorPath.BASE + AuditorPath.NOPASSAUDITOR %>',
		data:
		{
			"custId" :custId,
			"email":email,
			"createId":createId
		},
		
		success:function(data){
			
			if(data.result=="success"){
				
				$("#auditorMachant").hide();
				$.gyzbadmin.alertSuccess('审核邮件发送成功！', null, function(){
					window.location.href = window.Constants.ContextPath + '<%=AuditorPath.BASE+AuditorPath.LIST %>';
					//window.location.reload();
				});
			}else{
				
				$.gyzbadmin.alertFailure("审核邮件发送失败！"+data.message);
			}
		}
	});
}
	
</script>
</html>

