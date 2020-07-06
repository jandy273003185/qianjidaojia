<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js?v=${_jsVersion}"/>'></script>
<script src='<c:url value="/static/js/register.js?v=${_jsVersion}"/>'></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代付查询</title>
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
	.list-table2 tbody td{padding: 10px 8px;}
	
</style>
<script type="text/javascript">

function bigImg(obj){
    $('#showImageDiv #showImage').attr("src",obj.src);
};


function viewPaymet(obj,flag){
	//0 -- 详情   1--审核
	var custId=$(obj).parent().find('#custId').val();
	//后台查询数据
	$.post(window.Constants.ContextPath +'<%=PaymentManagerPath.BASE + PaymentManagerPath.SELCUSTINFO%>',{
		"custId":custId	
	},function(data){
		if("SUCCESS"==data.result){
			var authId = data.cust.authId;
			$("#merchantDiv #certAttribute0ImageDiv").show();
			$("#merchantDiv #certAttribute1ImageDiv").show();
			$("#merchantDiv #certAttribute2ImageDiv").show();
			$("#merchantDiv #certAttribute0ImageDiv").attr("src","<%=request.getContextPath()+PaymentManagerPath.BASE+ PaymentManagerPath.GETIMG %>?custId="+custId+"&certifyType=03");
			$("#merchantDiv #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+PaymentManagerPath.BASE+ PaymentManagerPath.GETIMG %>?custId="+custId+"&certifyType=04&front=0");
			$("#merchantDiv #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+PaymentManagerPath.BASE+ PaymentManagerPath.GETIMG %>?custId="+custId+"&certifyType=04&front=1");

			$("#auditModel #email").val(data.cust.email);
			$("#auditModel #custName").val(data.cust.custName);
			$("#auditModel #businessLicense").val(data.cust.businessLicense);
			$("#auditModel #businessRegAddr").val(data.cust.businessRegAddr);
			$("#auditModel #custAdd").val(data.cust.custAdd);
			
			if("forever"==data.cust.businessTerm){
				$("#auditModel #businessTermEnd").val("永久");
			}else{
				$("#auditModel #businessTermEnd").val(data.cust.businessTerm);
			}
			
			
			$("#auditModel #mobile").val(data.cust.mobile);
			$("#auditModel #orgInstCode").val(data.cust.orgInstCode);
			$("#auditModel #fcontactunitNumber").val(data.cust.fcontactunitNumber);
			if("00"==data.cust.state){
				$("#auditModel #state").val("正常");
			}else if("01"==data.cust.state){
				$("#auditModel #state").val("停用");
			}else if("02"==data.cust.state){
				$("#auditModel #state").val("登录账户冻结");
			}else if("03"==data.cust.state){
				$("#auditModel #state").val("注册待激活");
			}else if("04"==data.cust.state){
				$("#auditModel #state").val("商户审核中");
			}else if("05"==data.cust.state){
				$("#auditModel #state").val("前台 商户协议待录入");
			}else if("06"==data.cust.state){
				$("#auditModel #state").val("后台商户协议待录入");
			}else if("07"==data.cust.state){
				$("#auditModel #state").val("审核不通过");
			}
			
			
			$("#auditModel #compAcctBank").val(data.cust.compAcctBank);
			//$("#auditModel #bankName").val(data.cust.bankName);
			//$("#auditModel #bankCardName").val(data.cust.bankCardName);
			$("#auditModel #bankCardName").val(data.cust.bankAcctName);
			$("#auditModel #bankName").val(data.cust.branchBANK);
			$("#auditModel #compMainAcct").val(data.cust.compMainAcct);
			$("#auditModel #representativeName").val(data.cust.representativeName);
			$("#auditModel #representativeCertNo").val(data.cust.representativeCertNo);
			$("#auditModel #representativeMobile").val(data.cust.representativeMobile);
			$("#auditModel #cust_id").val(custId);
			
			if("0"==flag){
				//给代付商户费率赋值
				if(null!=data.pay){
					$("#auditModel #amtRate").val(parseFloat(data.pay.amtRate).toFixed(2));
					$("#auditModel #workRate").val(parseFloat(data.pay.workRate).toFixed(2));
					$("#auditModel #noWorkRate").val(parseFloat(data.pay.noWorkRate).toFixed(2));
					$("#auditModel #PayFee").val(parseFloat(data.pay.payFee).toFixed(2));
					
				}
				
				$("#auditModel #amtRate").attr("readOnly","true");
				$("#auditModel #workRate").attr("readOnly","true");
				$("#auditModel #noWorkRate").attr("readOnly","true");
				$("#auditModel #PayFee").attr("readOnly","true");
				
				$("#auditModel #toPay_audit").attr("style","display:none");
				$("#auditModel #toPay_detail").attr("style","display:block");				
			}else{
				if("1"==flag){
					$("#auditModel #amtRate").val(parseFloat(data.defaultAmtRate).toFixed(2));
					$("#auditModel #workRate").val(parseFloat(data.defaultWorkRate).toFixed(2));
					$("#auditModel #noWorkRate").val(parseFloat(data.defaultNoWorkRate).toFixed(2));
					$("#auditModel #PayFee").val(parseFloat(data.defaultPayFee).toFixed(2));
				}
				
				$("#auditModel #amtRate").attr("readOnly",false);
				$("#auditModel #workRate").attr("readOnly",false);
				$("#auditModel #noWorkRate").attr("readOnly",false);
				$("#auditModel #PayFee").attr("readOnly",false);
				
				$("#auditModel #toPay_audit").attr("style","display:block");
				$("#auditModel #toPay_detail").attr("style","display:none");	
			}
			
		}else{
			alert("获取代付商户信息失败");
		}
	},'json');
	
}

function firstAuditPassBtn(){
	var custId = $("#auditModel #cust_id").val();
	var amtRate = $("#auditModel #amtRate").val();
	var workRate = $("#auditModel #workRate").val();
	var noWorkRate = $("#auditModel #noWorkRate").val();
	var PayFee = $("#auditModel #PayFee").val();
	var email =  $("#auditModel #email").val();
	var custName =  $("#auditModel #custName").val();
	
	var reg =  /^\d+(\.\d{1,2})?$/;
	
	if(amtRate!=""){
		if(!reg.test(amtRate)){
			alert("待清算金额可用比例格式不正确(例:0.38)");
			return false;
		}
		
	}else{
		alert("待清算金额可用比例不能为空");
		return false;
	}
	
	if(workRate!=""){
		if(!reg.test(workRate)){
			alert("垫资费率（工作日）格式不正确(例:0.38)");
			return false;
		}
	}else{
		
		alert("垫资费率（工作日）不能为空");
		return false;
	}
	
	if(noWorkRate!=""){
		if(!reg.test(noWorkRate)){
			alert("垫资费率（非工作日）格式不正确(例:0.38)");
			return false;
		}
	}else{
		alert("垫资费率（非工作日）不能为空");
		return false;
	}
	
	if(PayFee!=""){
		if(!reg.test(PayFee)){
			alert("代付手续费格式不正确(例:2.00)");
			return false;
		}
	}else{
		alert("代付手续费不能为空");
		return false;
	}
	
	
	
	 $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE+PaymentManagerPath.AUDITPASS %>',
	{
 		"custId" 	: custId,
 		"amtRate":amtRate,
 		"workRate":workRate,
 		"noWorkRate":noWorkRate,
 		"PayFee":PayFee,
 		"email":email,
 		"custName":custName
 	},function(data){
 		if(data.result=="SUCCESS"){
			$.gyzbadmin.alertSuccess("审核完成！",function(){
				$("#auditModel").modal("hide");
			},function(){
				window.location.reload();
			});
		}else{
			$.gyzbadmin.alertFailure("审核失败！"+data.message);
		};
 		window.location.reload();
 	});
}


function firstAuditNotPassBtn(){
	
	var custId = $("#auditModel #cust_id").val();
	var email =  $("#auditModel #email").val();
	//var custName =  $("#auditModel #custName").val();
	var reason=$("#auditMessage").val(); 
	var obj = document.getElementsByName("test"); // 获取多选框数组
	    var objLen = obj.length;
	    var objYN = false; // 是否有选择
	    for (var i = 0; i < objLen; i++) {
	        if (obj [i].checked == true) {
	            objYN = true;
	            break;
	            
	          }
	        }
	    if (!objYN   &&  reason.length<=0) {
	    	$.gyzbadmin.alertFailure("请选择或填写不通过的理由！");
	        return false;
	    } 
	    
	    var chk_value =[]; 
	    $('input[name="test"]:checked').each(function(){ 
	        chk_value.push($(this).val()); 
	    });
	  
	   var messages; 
	   if(null!=chk_value && chk_value.length>=0 && 'null'!=chk_value){
		   messages=chk_value.join(',');
		   if(null!=reason && 'null'!=reason){
			   messages=chk_value.join(',')+","+reason;
		   }
	   }else{
		   messages=reason;
	   }
	   
	   $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE+PaymentManagerPath.AUDITNOTPASS %>',
		{
	 		"custId" 	: custId,
	 		"email":email,
	 		"comment":messages
	 	},function(data){
	 		window.location.reload();
	 	});
		
	}
	
	
	

function viewHistory(obj) {

	 var custId = $("#auditModel #cust_id").val();
	 var email = $(obj).parent().find('#email_').val();
	 $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.GETOPENHISTORY %>',
	   {
			"email" 	: email
			},function(data){				
				var json = eval('(' + data + ')'); 
				var columns=$("#sample-table-5 #getHistory");
				$(columns).html('');
				var infos='';
				if(json.result=="FAILE"){
		 				infos="<tr><td colspan='9' align='center'><font style='color: red; font-weight: bold;font-size: 15px;'>暂无数据</font></td></tr>";
		 			 }
				 if(json.list){
		 				var checkHistorys=json.list;
		 				$('#showHistory').on('show.bs.modal', function () {
		 				 });
		 				  for (var i = 0; i < checkHistorys.length; i++) {
		 					 infos+="<tr><td>"+
		 					    checkHistorys[i].custName+
			 					"</td><td>"+
			 					checkHistorys[i].auditTime+
			 					"</td><td>"+
			 					checkHistorys[i].memo+
			 					"</td></tr>";
		 				  }
		 					
			 } 
			 $(columns).append(infos);
			});
}	

</script>
</head>

<body style="overflow-x:hidden;">
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
						<input type="hidden" id="topay_status" value="${cust.topayStatue}"/>
						<!-- 查询条件 -->
						<form  id="merchantForm" action='<c:url value="<%=PaymentManagerPath.BASE + PaymentManagerPath.AUDITTOPAY%>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantCode" id="merchantCode"  value="${cust.merchantCode }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">手机号码：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="contactPhone" id="contactPhone"  value="${cust.contactPhone}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">时间：</td>
									 <td class="td-right">
									 <input type="text" name="startTime"   id="startTime" readonly="readonly" value="${cust.startTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									 <input type="text" name="endTime"   id="endTime" readonly="readonly" value="${cust.endTime }"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
								
								</tr>
								<tr>
								
								<td class="td-left" >商户名称：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="custName" id="custName"  value="${cust.custName }">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left">邮箱：</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="email" id="email"  value="${cust.email}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								<td class="td-left">状态：</td>
									     <td class="td-right">								   
										 <select name="topayStatue" id="topayStatue" >
											  <option  value="" >请选择 </option>
											  <option  value="YES">已开通</option>
											  <option  value="AUDIT">待审核</option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=PaymentManagerPath.BASE + PaymentManagerPath.SELCUSTINFO %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch" >
													查询<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<button id="clearMerchantSearch" class="btn btn-purple btn-sm btn-margin clearMerchantSearch" >
												清空<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<!-- <span class="input-group-btn" style="display:inline;">
												<a class="btn btn-purple btn-sm exportBut">导出报表</a> 
											</span>  -->
										</span>
									</td>
								</tr>
							</table>
						</form>
						
						<div class="list-table-header">代付列表</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" style="overflow:scroll;">
								<thead>
									<tr >
										<th width="9%">商户编号</th>
										<th width="9%">商户名称</th>
										<th width="9%">手机号码</th>
										<th width="9%">邮箱</th>
										<th width="9%">联系人</th>
										<th width="9%">提交时间</th>
										<th width="9%">状态</th>
										<th width="9%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="list" varStatus="i">
											<tr  class="list" id="list">
											<td>${list.merchantCode}</td>
											<td>${list.custName}</td>
											<td>${list.contactPhone}</td>
											<td>${list.email}</td>
											<td>${list.agentName}</td>
											<td>${list.modifyTime}</td>
											<td>
												<c:if test="${list.topayStatue=='YES' }">
													已开通
												</c:if>
												<c:if test="${list.topayStatue=='AUDIT' }">
													待审核
												</c:if>
												<c:if test="${list.topayStatue=='AUDIT_NO' }">
													审核不通过
												</c:if>
											</td>
											<td width="10%">	
												<input type="hidden" name="custId" id="custId" value="${list.custId}">
												<input type="hidden" name="email_" id="email_" value="${list.email}">
												<c:if test="${list.topayStatue=='YES' }">
													<button type="button" onclick="viewPaymet(this,'0')"   data-toggle='modal' data-target="#auditModel" class="btn btn-primary btn-xs qifenqian_view_tc" >详情</button> 
												</c:if>
												<c:if test="${list.topayStatue=='AUDIT' }">
													<button type="button" onclick="viewPaymet(this,'1')" data-toggle='modal' data-target="#auditModel" class="btn btn-primary btn-xs qifenqian_view_tc" >审核</button> 
												</c:if>
												<c:if test="${list.topayStatue=='AUDIT_NO' }">
													<button type="button" onclick="viewPaymet(this,'0')"  data-toggle='modal' data-target="#auditModel" class="btn btn-primary btn-xs qifenqian_view_tc" >详情</button> 
													<button type="button" onclick="viewHistory(this)" data-toggle='modal' data-target="#showHistory" class="btn btn-primary btn-xs qifenqian_view_tc" >查看审核记录</button> 
												</c:if>
											  
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty list}">
										<tr>
											<td colspan="8" align="center">
												<font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font>
											</td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty list}">
									<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div> <!-- /.page-content -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			  </div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
	
	        <!-- 待审核模态框 -->
			<div class="modal fade" style="z-index: 1040;" id="auditModel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 60%; z-index: 90;">
					<input id="cust_id" type="hidden" value=""/>
					<div class="modal-content" style="width: 950px;" id="merchantDiv">
						<div class="modal-header" style="background-color: 0099CC">
							
							<button type="button" class="close" data-dismiss="modal"
								id="updateMerchantClose" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">商户信息</h4>
						</div>
						<div class="modal-body">
							<table id="sample-table-2" class="list-table">
								<tr>
									<td colspan="4" class="headlerPreview">企业信息</td>
								</tr>
								<tr>
									<td class="td-left" width="18%">账号名称：</td>
									<td class="td-right" width="32%">
										<input type="text" id="email" readonly="readonly" name="email" maxlength="100" placeholder="账号名称" style="width: 90%">
									</td>
									<td class="td-left" width="18%">企业名称：</td>
									<td class="td-right" width="32%"><input type="text"
										id="custName" name="custName" placeholder="企业名称" readonly="readonly"
										style="width: 90%"></td>
								</tr>
								<tr>
									<td class="td-left">营业执照注册号：</td>
									<td class="td-right">
										<input type="text" id="businessLicense"
										name="businessLicense" placeholder="营业执照注册号" readonly="readonly"
										style="width: 90%"></td>
									<td class="td-left">营业执照所在地：</td>
									<td class="td-right">
										<input type="text" id="businessRegAddr"
										name="businessRegAddr" placeholder="营业执照所在地" readonly="readonly"
										style="width: 90%">
									</td>
								</tr>
								<tr>
									<td class="td-left">企业地址：</td>
									<td class="td-right">
										<input type="text" id="custAdd"
										name="custAdd" placeholder="企业地址" readonly="readonly"
										style="width: 90%"></td>
									<td class="td-left">营业期限：</td>
									<td class="td-right">
										<input type="text" id="businessTermEnd"
										name="businessTermEnd" placeholder="营业期限" readonly="readonly"
										style="width: 90%">
									</td>
								</tr>
								
								<tr>
									<td class="td-left">联系电话：</td>
									<td class="td-right">
										<input type="text" id="mobile"
										name="mobile" placeholder="联系电话" readonly="readonly"
										style="width: 90%"></td>
									<td class="td-left">组织机构代码：</td>
									<td class="td-right">
										<input type="text" id="orgInstCode"
										name="orgInstCode" placeholder="组织机构代码" readonly="readonly"
										style="width: 90%">
									</td>
								</tr>
								
								<tr>
									<td class="td-left">往来单位编号：</td>
									<td class="td-right">
										<input type="text" id="fcontactunitNumber"
										name="fcontactunitNumber" placeholder="往来单位编号" readonly="readonly"
										style="width: 90%"></td>
									<td class="td-left">商户登录状态：</td>
									<td class="td-right">
										<input type="text" id="state"
										name="state" placeholder="商户登录状态" readonly="readonly"
										style="width: 90%">
									</td>
								</tr>

							</table>

							<table id="sample-table-2" class="list-table">
								<tr>
									<td colspan="4" class="headlerPreview">银行信息</td>
								</tr>
								<tr>
									<td class="td-left" width="18%">银行开户名：</td>
									<td class="td-right" width="32%">
										<input type="text" id="bankCardName" readonly="readonly" name="bankCardName" maxlength="100" placeholder="银行开户名" style="width: 90%">
									</td>
									<td class="td-left" width="18%">开户银行：</td>
									<td class="td-right" width="32%"><input type="text"
										id="compAcctBank" name="compAcctBank" readonly="readonly"  placeholder="开户银行"
										style="width: 90%"></td>
								</tr>
								<tr>
									<td class="td-left">支行信息：</td>
									<td class="td-right">
										<input type="text" id="bankName"
										name="bankName" placeholder="支行信息" readonly="readonly"
										style="width: 90%"></td>
									<td class="td-left">公司对公账户：</td>
									<td class="td-right">
										<input type="text" id="compMainAcct"
										name="compMainAcct" placeholder="公司对公账户" readonly="readonly"
										style="width: 90%">
									</td>
								</tr>
								
								<tr>
									<td class="td-left" >开户许可证：</td>
									<td class="td-right" colspan="3">
										<a data-toggle='modal' class="tooltip-success certAttribute0Click"   data-target="#previewImageModal" >
										<label id="certAttribute0Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="certAttribute0ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
										</a>
										
									</td>
								</tr>

							</table>
							
							
							<table id="sample-table-2" class="list-table">
								<tr>
									<td colspan="4" class="headlerPreview">法人信息</td>
								</tr>
								<tr>
									<td class="td-left" width="18%">法人真是姓名：</td>
									<td class="td-right" width="32%">
										<input type="text" id="representativeName" readonly="readonly" name="representativeName" placeholder="法人真是姓名" style="width: 90%">
									</td>
									<td class="td-left" width="18%">法人身份证号码：</td>
									<td class="td-right" width="32%"><input type="text"
										id="representativeCertNo"  name="representativeCertNo" placeholder="法人身份证号码" readonly="readonly"
										style="width: 90%"></td>
								</tr>
								<tr>
									<td class="td-left">法人手机号码：</td>
									<td class="td-right">
										<input type="text" id="representativeMobile"
										name="representativeMobile" placeholder="法人手机号码" readonly="readonly"
										style="width: 90%"></td>
								</tr>
								<tr>
									<td class="td-left" >身份证图片正面：</td>
									<td class="td-right" colspan="3">
										<a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
										<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
										</a>
										
									</td>
								</tr>
								<tr>
									<td class="td-left" >身份证图片背面：</td>
									<td class="td-right" colspan="3"> 
										<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
											<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
											        <img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
											</label>
										</a>
										
									</td>
								</tr>

							</table>
							
							
							
							<table id="sample-table-2" class="list-table">
								<tr>
									<td colspan="4" class="headlerPreview">费率设定</td>
								</tr>
								<tr>
									<td class="td-left" width="18%">待清算金额可用比例：</td>
									<td class="td-right" width="32%">
										<input type="text" id="amtRate" name="amtRate" maxlength="100" placeholder="待清算金额可用比例" style="width: 90%">%
									</td>
								</tr>
								<tr>
									<td class="td-left">垫资费率（工作日）：</td>
									<td class="td-right">
										<input type="text" id="workRate"
										name="workRate" placeholder="垫资费率（工作日）" 
										style="width: 90%">%</td>
								</tr>
								<tr>
									<td class="td-left">垫资费率（非工作日）：</td>
									<td class="td-right">
										<input type="text" id="noWorkRate"
										name="noWorkRate" placeholder="垫资费率（非工作日）"
										style="width: 90%">%</td>
								</tr>
								<tr>
									<td class="td-left">代付手续费：</td>
									<td class="td-right">
										<input type="text" id="PayFee"
										name="PayFee" placeholder="代付手续费" 
										style="width: 90%">元</td>
								</tr>
								
							</table>
						</div>
						
						<div class="modal-footer" id="toPay_audit" >
							<button type="button"
								class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal'
								data-target="#firstAuditMessageModel">审核不通过</button>
							<button type="button" onclick="firstAuditPassBtn();" class="btn btn-primary firstAuditPassBtn">审核通过</button>
						</div>
						
						 <div class="modal-footer" id="toPay_detail" style="display: none">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					     </div>

					</div>
					<!-- /.modal-content -->

				</div>

			</div>

			<!-- 审核不通过模态框 -->
			<div class="modal fade" style="z-index: 1043;"
				id="firstAuditMessageModel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 30%; z-index: 99;">
					<div class="modal-content">
						<div class="modal-header" style="background-color: 0099CC">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">审核不通过</h4>
						</div>
						<div class="modal-body">

							<table id="selectCheckbox">

								<label><input name="test" type="checkbox"
									value="非企业类型，无法开通代付功能" />&nbsp;非企业类型，无法开通代付功能</label>
								</br>
								<label><input name="test" type="checkbox"
									value="资料填写不完整" />&nbsp;资料填写不完整</label>
								</br>
								
								</br> 其他原因
								<tr>
									<td><textarea rows="5" cols="40" id="auditMessage"></textarea>
									</td>
								</tr>
							</table>


						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default messageDefault"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary addadBtn"
								onclick="firstAuditNotPassBtn();">确定</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
			</div>
			<!-- /.modal -->
			
			<!-- 历史审核记录模态框 -->
			<div class="modal fade" style="z-index: 1040;" id="showHistory"
				tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" style="width: 60%; z-index: 90;">
					<div class="modal-content" style="width: 950px;" id="merchantDiv">
						<button type="button" class="close" data-dismiss="modal"
							id="updateMerchantClose" aria-hidden="true">&times;</button>

						<div class="list-table-header">商户代付审核记录</div>
						<div class="modal-body">
							<table id="sample-table-5" class="list-table">
								<thead>
									<tr>
										<th width="10%">商户名称</th>
										<th width="12%">审核时间</th>
										<th width="15%">原因</th>

									</tr>
								</thead>
								<tbody id="getHistory">

								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>

			<!-- 图片预览 -->
			<div class="modal fade" id="previewImageModal" aria-hidden="true">
				<div class="modal-dialog" id="imageDiv"
					style="width: 60%; height: 80%;">
					<div id="showImageDiv" style="width: 100%; height: 100%;">
						<img id="showImage" style="width: 100%; height: 100%;">
					</div>
				</div>
			</div>

		</div>
	 </div>
	       
</body>

<script type="text/javascript">
$(function(){
	/** 缓存 **/
	/* var custIds = ${list};
	var custId = $("tr.list");
	$.each(custIds,function(i,value){
		$.data(custId[i],"list",value);
	}); */
	
	var topayStstus = $("#topay_status").val();
	
	$("#topayStatue option[value='"+topayStstus+"']").removeAttr("selected");//根据值去除选中状态  
	$("#topayStatue option[value='"+topayStstus+"']").attr("selected","selected");
	
 })
 
 
 
</script>
</html>