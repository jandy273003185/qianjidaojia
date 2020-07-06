<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.custInfo.TdCustInfoPath" %> 
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/objectStyle.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/writeObject.js"></script> --%>
<html>
<head>
	<meta charset="utf-8" />
	<title>客户管理列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	function showLoad(){
		var state2=$("#state2").val();
		$(".search-table #state").val(state2);
		var trustCertifyLvl2=$("#trustCertifyLvl2").val();
		$(".search-table #trustCertifyLvl").val(trustCertifyLvl2);
	}
	
	function resetType(type){
		
		$("#emailType").hide();
		$("#mobilType").hide();
		if(type == 'byUser'){
			$("#emailType").show();
			$("#mobilType").show();
		}
		
	}
	
	function readonly(value,type){
		$("#resetEmail").attr("readonly",false);
		$("#resetMobil").attr("readonly",false);
		if(value.length!=0){
			$("#resetEmail").attr("readonly",true);
			$("#resetMobil").attr("readonly",true);
			$('#'+type).attr("readonly",false);
		}
	}
	$(function(){
		
		/* 重置模板初拟化 */
		$(".passwordEdit").click(function(){
			var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
			$("#passwordEditModal").on("show.bs.modal",function(){
				$("#emailType").hide();
				$("#mobilType").hide();
				
				$("#passwordEditModal #custId").val(custInfo.custId);
			});
			
			$("#passwordEditModal").on("hide.bs.modal",function(){
				$("#passwordEditModal #inFormType").val('');
				$("#passwordEditModal #resetMobil").val('');
				$("#passwordEditModal #resetEmail").val('');
				 $("#passwordEditModal input").attr("readonly",false); 
			}); 
			
		
		});
		
		$("#passwordEditModal .editPayPassWordBtn").click(function(){
			var type = $("#passwordEditModal #inFormType").val();
			if(kong.test(type)){
				$.gyzbadmin.alertFailure('请选择通知类型');
				return;
			}
			if(type == 'byUser'){
				var mobile = $("#passwordEditModal #resetMobil").val();
				var	email = $("#passwordEditModal #resetEmail").val();
				
				if(kong.test(mobile) && kong.test(email)){
					$.gyzbadmin.alertFailure('手机号码与邮箱不能全空！');
					return;
				}
			}
			
			
			$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.PAYPASSWORDEDIT %>',{
				'custId' : $("#passwordEditModal #custId").val(),
				'email' :$("#passwordEditModal #resetEmail").val(),
				'mobile' : $("#passwordEditModal #resetMobil").val(),
				'type' :$("#passwordEditModal #inFormType").val()
			},function(data) {
				if(data.result == 'success'){
					$.gyzbadmin.alertSuccess('重置成功', function(){
						$("#passwordEditModal").modal("hide");
						}, 
						null);
					$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.SEND_MESSAGE_ASYN %>',{
						'custId' : $("#passwordEditModal #custId").val(),
						'tradeCode': data.tradeCode,
						'mobil' :data.mobile,
						'email' :data.email,
						'type' :data.type
					},'json');
				} else {
					$.gyzbadmin.alertFailure('重置失败:' + data.message);
				}
			}, 'json'
			);
			
		});
		
		<%-- $(".passwordEdit").click(function(){
			var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
			
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.PAYPASSWORDEDIT %>',{
				'custId' : custInfo.custId
			},function(data) {
				$.unblockUI();
				if(data.result == 'success'){
					$.gyzbadmin.alertSuccess('重置成功', null, null);
					$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.SEND_MESSAGE_ASYN %>',{
						'custId' : custInfo.custId,
						'tradeCode': data.tradeCode
					},'json');
					
				} else {
					$.gyzbadmin.alertFailure('重置失败:' + data.message);
				}
			}, 'json'
			);
		}); --%>
		
		var custs = ${custInfos};
		var custList = $("tr.custInfo");
		$.each(custs,function(i,value){
			$.data(custList[i],"custInfo",value);
		});
		
		$(".clearCustInfo").click(function(){
			$(".search-table #custName").val('');
			$(".search-table #mobile").val('');
			$(".search-table #startTime").val('');
			$(".search-table #endTime").val('');
			$(".search-table #custId").val('');
			$(".search-table #state").val('');
			$(".search-table #trustCertifyLvl").val('');
			$(".search-table #certifyNo").val('');
			
		})
		
		
	
		//按条件查询
		$('.buttonSearch').click(function(){
			
			var startRegistDate = $("#startTime").val();
			var endRegistDate= $("#endTime").val();
			if("" != startRegistDate && "" != endRegistDate && startRegistDate > endRegistDate) 
			{
				$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
				return false;
			}
			var form = $('#custInfoForm');
			form.submit();
		});
		
		// 弹出修改层准备工作
		$('.updateCustLink').click(function(){
			var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
			$('#updateCustModal').on('show.bs.modal', function () {
				// 赋值
				$('#updateCustModal #custId').val(custInfo.custId);
				$('#updateCustModal #custName').val(custInfo.custName);
				$('#updateCustModal #email').val(custInfo.email);
				$('#updateCustModal #mobile').val(custInfo.mobile);
				$('#updateCustModal #certifyName').val(custInfo.certifyName);
				$('#updateCustModal #certifyNo').val(custInfo.certifyNo	);
				$('#updateCustModal #state').val(custInfo.state);
				$('#updateCustModal #comment').val(custInfo.comment);
			});
			$('#updateCustModal').on('hide.bs.modal', function () {
				// 清除
				$('#updateCustModal #custId').val('');
				$('#updateCustModal #custName').val('');
				$('#updateCustModal #email').val('');
				$('#updateCustModal #mobile').val('');
				$('#updateCustModal #certifyName').val('');
				$('#updateCustModal #certifyNo').val('');
				$('#updateCustModal #state').val('');
				$('#updateCustModal #comment').val('');
			});
		});
		
		
		//导出用户数据
		
		$('.exportBut').click(function(){
			var custName = $('.search-table #custName').val();
			var mobile = $('.search-table #mobile').val();
			var certifyNo = $('.search-table #certifyNo').val();
			var state = $('.search-table #state').val();
			var trustCertifyLvl = $('.search-table #trustCertifyLvl').val();
			var startTime = $('.search-table #startTime').val();
			var endTime = $('.search-table #endTime').val();
			
			var src ="<%= request.getContextPath()+ TdCustInfoPath.BASE+TdCustInfoPath.EXPORTUSERINFO%>?custName="+
			custName+
			"&mobile="+
			mobile+
			"&certifyNo="+
			certifyNo+
			"&state="+
			state+
			"&trustCertifyLvl="+
			trustCertifyLvl+
			"&startTime="+
			startTime+
			"&endTime="+
			endTime;
			$(".exportBut").attr("href",src);
			
		});
		// 修改用户
		$('.editCustBtn').click(function(){
			
			var custId = $('#updateCustModal #custId').val();
			if(kong.test(custId)) {
				$.gyzbadmin.alertFailure('用户编号不可为空');
				return;
			}

			var mobile = $('#updateCustModal #mobile').val();
			if(kong.test(mobile)) {
				$.gyzbadmin.alertFailure('手机号码不可为空');
				return;
			}
			
			var custName = $('#updateCustModal #custName').val();
			if(kong.test(custName)) {
				$.gyzbadmin.alertFailure('客户姓名不可为空');
				return;
			}
			
			var certifyNo = $('#updateCustModal #certifyNo').val();
			if(!kong.test(certifyNo)){
				if(!isCertNo($("#updateCustModal #certifyNo")[0])){
					$.gyzbadmin.alertFailure("身份证号码不正确");
					return false;
				}
			}
			
			var state = $('#updateCustModal #state').val();
			if(kong.test(state)) {
				$.gyzbadmin.alertFailure('用户状态不可为空');
				return;
			}
			
			var email = $('#updateCustModal #email').val();
			if(!kong.test(mobile)){
				
				if(!isPhoneNo($("#updateCustModal #mobile")[0])){
					$.gyzbadmin.alertFailure("手机号码格式不对");
					return false;
				}
				
				var validateMobile =true ;		
				$.ajax({
					async:false,
					dataType:"json",
					url:window.Constants.ContextPath +'<%=TdCustInfoPath.BASE+TdCustInfoPath.VALIDATEMOBILE%>',
			        data:{mobile:mobile,custId:custId},
			        success:function(data){
			        	if(data.result=="FAIL"){
			        		$.gyzbadmin.alertFailure('该手机号已被使用');
			        		validateMobile = false;
						}else{
							validateMobile = true;
						}
						 }});
				if(!validateMobile){
					return false;
				}
			}
			
			if(!kong.test(email)){
				
				if(!verifyEmailAddress($("#updateCustModal #email")[0])){
					$.gyzbadmin.alertFailure("邮箱格式不对,可使用字母、数字、下划线");
					return false;
				}
				var validateEmail =true ;		
				$.ajax({
					async:false,
					dataType:"json",
					url:window.Constants.ContextPath +'<%=TdCustInfoPath.BASE+TdCustInfoPath.VALIDATEEMAIL%>',
			        data:{email:email,custId:custId},
			        success:function(data){
			        	if(data.result=="FAIL"){
			        		$.gyzbadmin.alertFailure('该邮箱已被使用');
			        		validateEmail = false;
						}else{
							validateEmail = true;
						}
						 }});
				if(!validateEmail){
					return false;
				}
			}
			
			// 保存修改
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.EDIT %>', {
					'custId' 	: custId,
					'mobile' 	: mobile,
					'email' 	: email,
					'custName' 	: custName,
					'state'		: state,
					'certifyNo'	: certifyNo,
					'comment' : $("#updateCustModal #comment").val()
				}, function(data) {
					$.unblockUI();
					if(data.result == 'success'){
						$('#updateCustModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		
		//查询余额 
		$('.queryCustLink').click(function(){
			
			var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
			
			$.ajax({
				async:false,
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdCustInfoPath.BASE+TdCustInfoPath.QUERYACCOUNT%>',
		        data:{custId:custInfo.custId},
		        success:function(data){
		        	if(data.result=="fail"){
		        		$("#queryCustModal #qfqAccId").text(data.message);
		        		$("#queryCustModal #qfqNumber").text('0.00');
		        	}else if(data.result="sucess"){
		        		$('#queryCustModal #qfqAccId').text(data.custInfo.qfqAccId);
		        		$("#queryCustModal #qfqNumber").text(data.custInfo.qfqTotalAmt);
		        		if(null==data.custInfo.qfqAccId||data.custInfo.qfqAccId==""){
		        			$('#queryCustModal #qfqAccId').text('未开通');
			        		$("#queryCustModal #qfqNumber").text('0.00');
		        		}
		        	}
				}
			});
			
			$('#queryCustModal').on('hide.bs.modal', function () {
				// 清除
				$('#queryCustModal #qfqAccId').text('');
				$('#queryCustModal #qfqNumber').text('');
			});
		});


//查询卡信息
$('.queryBankCard').click(function(){
	
	var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
	
	$("#tbPreviewBankCard tr:gt(0)").remove();
	
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=TdCustInfoPath.BASE+TdCustInfoPath.BANKCARD%>',
	        data:{custId:custInfo.custId},
	        success:function(data){
	        	if(data.result=="fail"){
	        		$('#tbPreviewBankCard').append("<tr><td colspan='3' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>");
	        	}else if(data.result=="success"){
	        		
	        		for(i in data.message){
	        			$('#tbPreviewBankCard').append("<tr><td>"+data.message[i].custId+"</td><td>"+data.message[i].bankName+"</td><td>"+data.message[i].bankCardNo+"</td></tr>");	
	        		}
	        	}
			}
		});
	});
	
	/**加载发送信息 **/
	$('.sendMessageLink').click(function(){
		var custInfo = $.data($(this).parent().parent()[0], 'custInfo');
		$('#sendMessageModal').on('show.bs.modal', function () {
			// 赋值
			$('#sendMessageModal #mobile').val(custInfo.mobile);
		});
		$('#sendMessageModal').on('hide.bs.modal', function () {
			// 清除
			$('#sendMessageModal #mobile').val('');
		});
	});
	
	/**发送信息 **/
	$('.sendMessageBtn').click(function(){
		
		var mobile = $('#sendMessageModal #mobile').val();
		if(kong.test(mobile)) {
			$.gyzbadmin.alertFailure('手机号码不可为空');
			return;
		}
		
		var content = $('#sendMessageModal #content').val();
		if(kong.test(content)) {
			$.gyzbadmin.alertFailure('信息内容不可为空');
			return;
		}
		$.blockUI();
			$.post(window.Constants.ContextPath + '<%=TdCustInfoPath.BASE + TdCustInfoPath.SEND_MESSAGE %>', {
				'mobile' 	: mobile,
				'content' 	: content
			},
			function(data) {
				$.unblockUI();
				if(data.result == 'SUCCESS'){
					$('#sendMessageModal').modal('hide');
					$.gyzbadmin.alertSuccess('发送成功', null, function(){
						window.location.reload();
					});
				} else {
					$.gyzbadmin.alertFailure(data.message);
				}
			}, 'json'
		);
		});
	
});
 
</script>
<body onload="showLoad()">
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
						<input type="hidden" id="state2" value="${custBean.state}">
						<input type="hidden" id="trustCertifyLvl2" value="${custBean.trustCertifyLvl}">
						<input type="hidden" id="editModalType" >
						<input type="hidden" id="editModalEmail" >
						<input type="hidden" id="editModalMobile" >
						<!-- 查询条件 -->
							<input type="hidden" id="loadPath" value="<%=request.getContextPath()%>/static/PowerEnterBOCEP.exe">
							<form id="custInfoForm" action='<c:url value="<%=TdCustInfoPath.BASE + TdCustInfoPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left">客户姓名</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="custName" id="custName" value="${custBean.custName}" placeholder="用户姓名">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">手机号码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="mobile" id="mobile" value="${custBean.mobile}" placeholder="手机号码">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">状态</td>
									<td class="td-right">
										<span class="input-icon">
										 <sevenpay:selectCustStateTag id="state" name ="state" custStates="${custStates}" clasS="width-100"/>
										</span>
									</td>
								</tr>
								<tr>
									<td class="td-left">身份证</td>
									<td class="td-right">
										    <span class="input-icon">
											<input type="text"  name="certifyNo" id="certifyNo" value="${custBean.certifyNo}" placeholder="身份证">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">注册时间</td>
									<td class="td-right">
										    <input type="text"  name="startTime" id="startTime" value="${custBean.startTime}"readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" >
										    -
										    <input type="text" name="endTime" id="endTime" value="${custBean.endTime}" readonly="readonly" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left">认证等级</td>
									<td class="td-right">
										 <span class="input-icon">
										 	<select id="trustCertifyLvl" name="trustCertifyLvl" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="0">未认证 </option>
												<option value="1">一级认证 </option>
												<option value="2">二级认证 </option>
												<option value="3">三级认证 </option>
											</select>
										</span>   
									</td>
									</tr>
									<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TdCustInfoPath.BASE + TdCustInfoPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm buttonSearch">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>	
											<button  class="btn btn-purple btn-sm btn-margin  clearCustInfo" >
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
							<div class="list-table-header">
								客户列表
							</div>
	
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">手机号码</th>
											<th width="10%">客户名称</th>
											<th width="10%">身份证号</th>
											<th width="18%">实名认证等级</th>
											<th width="10%">注册时间</th>
											<th width="10%">邮箱</th>
											<th width="7%">客户状态</th>
											<th style="width:25%">操作</th>
										</tr>
									</thead>
	
									<tbody>
										<c:forEach items="${custInfos}" var="custInfo">
											<tr class="custInfo">
												<td>${custInfo.mobile }</td>
												<td>${custInfo.custName }</td>
												<td>${custInfo.certifyNo }</td>
												<td>
													<c:if test="${custInfo.trustCertifyLvl =='0'}">
														未认证 <img title="未验证任何信息材料" width="15px" height="15px"  src="<%=request.getContextPath()%>/static/images/iconfont-wenhao.png" />
													</c:if>
													<c:if test="${custInfo.trustCertifyLvl =='1'}">
														一级认证 <img title="身份证验证" width="15px" height="15px"  src="<%=request.getContextPath()%>/static/images/iconfont-wenhao.png" >
													</c:if>
													<c:if test="${custInfo.trustCertifyLvl =='2'}">
														二级认证 <img title="身份证+银行卡验证" width="15px" height="15px"  src="<%=request.getContextPath()%>/static/images/iconfont-wenhao.png" >
													</c:if>
													<c:if test="${custInfo.trustCertifyLvl =='3'}">
														三级认证 <img title="身份证+银行卡+证件审核" width="15px" height="15px"  src="<%=request.getContextPath()%>/static/images/iconfont-wenhao.png" >
													</c:if>
												</td>
												<td>${custInfo.createTime }</td>
												<td>${custInfo.email }</td>
												<td>
													<c:if test="${custInfo.state =='00'}">正常</c:if>
													<c:if test="${custInfo.state =='01'}">停用</c:if>
													<c:if test="${custInfo.state =='02'}">登录账户冻结</c:if>
													<c:if test="${custInfo.state =='03'}">注册待激活</c:if>
												</td>
												<td>
													<input type="hidden" name="qfqAccId" value="${custInfo.qfqAccId }"/>
													<input type="hidden" name="certifyName" value="${custInfo.certifyName}"/>
													<input type="hidden" name="certifyNo" value="${custInfo.certifyNo }"/>
													<input type="hidden" name="custAdd" value="${custInfo.custAdd }"/>
													<input type="hidden" name="createBlackAndWhiterTime" value="${custInfo.createBlackAndWhiterTime }"/>
													<%-- <gyzbadmin:function url="<%=TdCustInfoPath.BASE + TdCustInfoPath.QUERYACCOUNT %>">
														<a href="#" class="tooltip-error queryCustLink" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryCustModal">
															<span class="red"><button type="button"  id="queryAccount"  class="btn btn-warning btn-xs">查询余额</button></span>
														</a>
													</gyzbadmin:function> --%>
													<gyzbadmin:function url="<%=TdCustInfoPath.BASE + TdCustInfoPath.BANKCARD%>">
														<a href="#" class="tooltip-success	 queryBankCard" data-rel="tooltip" title="Query" data-toggle='modal' data-target="#queryBankCardModal">
															<span class="red"><button type="button"  id="queryBankCard"  class="btn btn-primary btn-xs">查询卡信息</button></span>
														</a>
													</gyzbadmin:function>
														
													<gyzbadmin:function url="<%=TdCustInfoPath.BASE + TdCustInfoPath.EDIT%>">
														<a href="#" class="tooltip-success updateCustLink" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#updateCustModal">
															<button type="button"   id="btnInfoEdit"  class="btn btn-primary btn-xs"  >信息修改</button>	
														</a>
													</gyzbadmin:function>
													<a href="#passwordEditModal"  data-toggle='modal' class="tooltip-success passwordEdit" data-rel="tooltip" title="密码重置">
															<button type="button"   id="btnEmail2"  class="btn btn-warning btn-xs"  >密码重置</button>	
													</a>
													<gyzbadmin:function url="<%=TdCustInfoPath.BASE + TdCustInfoPath.SEND_MESSAGE%>">
														<a href="#" class="tooltip-success sendMessageLink" data-rel="tooltip" title="Edit" data-toggle="modal" data-target="#sendMessageModal">
															<button type="button"   id="btnInfoEdit"  class="btn btn-primary btn-xs"  >发送短信</button>	
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${empty custInfos}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							
							<!-- 分页 -->
							<c:if test="${not empty custInfos}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
					
					<!-- 发送信息 -->
					<div class="modal fade" id="sendMessageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">发送信息</h4>
					         </div>
					         <div class="modal-body">
					         	<input type="hidden" id="custId">
					           <table class="modal-input-table">
									<tr>
										<td class="td-left" width="20%">手机号码<span style="color:red">*</span></td>
										<td class="td-right">
											<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">内容<span style="color:red">*</span></td>
										<td class="td-right">
											<textarea rows="10" cols="60" name="content" id="content" clasS="width-90"></textarea>
										</td>
									</tr>
					            </table> 
					    	</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary sendMessageBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<!-- 密码重置 -->
					<div class="modal fade" id="passwordEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">重置消费者支付密码</h4>
					         </div>
					         <div class="modal-body">
					         	<input type="hidden" id="custId">
					           <table class="modal-input-table">
									<tr>
										<td class="td-left"><span>*</span>重置密码通知方式：</td>
										<td class="td-right">
											<select id="inFormType" name="inFormType" onchange="resetType(this.value);">
												<option value="">-- 请选择通知方式 --</option>
												<option value="bindingEmail">-- 已绑定邮箱 --</option>
												<option value="byUser">-- 客户提供 --</option>
											</select>
										</td>
									</tr>
									
									<tr id ="emailType">
										<td class="td-left">邮箱</td>
										<td class="td-right">
											<input type="text" id="resetEmail" name="resetEmail" clasS="width-60" onchange="readonly(this.value,'resetEmail')">
										</td>
									</tr>
									<tr id ="mobilType">
										<td class="td-left">手机</td>
										<td class="td-right">
											<input type="text" id="resetMobil" name="resetMobil" clasS="width-60" onchange="readonly(this.value,'resetMobil')">
										</td>
									</tr>
									
								
					            </table> 
					    	</div>
					         
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editPayPassWordBtn">重置</button>
					         </div>
					      </div>
					     </div>
					</div>
					
					<!-- 修改用户模态框（Modal） -->
					<div class="modal fade" id="updateCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">修改消费者信息</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
									<tr>
										<td class="td-left" width="25%">证件类型</td>
										<td class="td-right" width="75%">
										    <input type="hidden" id="custId" name="custId" readonly="readonly" style="background-color: #EEEEEE;" >
											<input type="text" id="certifyName" name="certifyName" readonly="readonly"  style="background-color: #EEEEEE;" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">证件号码</td>
										<td class="td-right">
											<input type="text" id="certifyNo" name="certifyNo" readonly="readonly" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" >客户姓名<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="custName" name="custName" readonly="readonly" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left" >邮箱地址</td>
										<td class="td-right" >
											<input type="text" id="email" name="email" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">手机号码<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="mobile" name="mobile" clasS="width-90">
										</td>
									</tr>
									<tr>
										<td class="td-left">客户状态<span style="color:red">*</span></td>
										<td  class="td-right" >
										 <sevenpay:selectCustStateTag id="state" name ="state" custStates="${custStates}" clasS="width-90"/>
										</td>
									</tr>
									<tr>
										<td class="td-left">修改备注:</td>
										<td class="td-right" > 
											<textarea rows="2" cols="60" name="comment" id="comment" clasS="width-90"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editCustBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					<!-- 余额（Modal） -->
					<div class="modal fade" id="queryCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">余额查询</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left">七分钱ID:</td>
										<td class="td-right">
											<span id ="qfqAccId"></span>
										</td>
									</tr>
									<tr>
										<td class="td-left">七分钱余额:</td>
										<td class="td-right">
											<span id="qfqNumber" style="color:#fc8936; font-size: 14px;font-weight:bold;"></span>
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
					
					<!-- 卡信息（Modal） -->
					<div class="modal fade" id="queryBankCardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 800px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">绑定卡信息</h4>
					         </div>
					         <div class="modal-body">
								<table class="list-table" id="tbPreviewBankCard">
									<thead>
										<tr>
											<th>客户编号</th>
											<th>银行名称</th>
											<th>银行卡号</th>
									    </tr>
								    </thead>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					         </div>
					      </div><!-- /.modal-content -->
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
</body>
</html>

