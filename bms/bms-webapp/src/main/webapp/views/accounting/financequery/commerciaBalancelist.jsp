<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.accounting.withdraw.WithdrawPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchantwithdraw.MerchantWithdrawPath"%>
<%@page import="com.qifenqian.bms.accounting.withdrawrevoke.WithdrawRevokePath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<%-- <link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<%-- <script src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/selectAll.js"/>'></script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户余额</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		ul.texlist{width:100%; text-align:center;}
		ul.texlist li{float:left;width:50%;border-bottom:1px solid #eee;line-height:30px;}
		.textlist-btn1,.textlist-btn2{padding:10px 20px;margin:0 10px;text-align:center; color:#fff; border:none;cursor:pointer;border-radius: 5px; }
		.textlist-btn1{background:#09F;}
		.textlist-btn2{background:#F00;}
	</style>
</head>
<body>
	<!-- 科目配置信息 -->
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
						    <li><a href="#balanceWhole"  data-toggle="tab"  id="balanceWhole">汇总余额</a></li>
							<li><a href="#changeBalance" data-toggle="tab" id="changeBalance">余额变动</a></li>						   
							<li><a href="#commerciaBalance" data-toggle="tab" id="commerciaBalance"> 商户余额</a></li>
							<li><a href="#userBalance" data-toggle="tab" id="userBalance">用户余额</a></li>
							<li><a href="#cashSettlement" data-toggle="tab" id="cashSettlement">客戶提现结算</a></li>
							<li><a href="#merchantSettlement" data-toggle="tab" id="merchantSettlement">商户提现结算</a></li>
							<li><a href="#withdrawrevoke" data-toggle="tab" id="withdrawrevoke">客户提现撤销列表</a></li>
						</ul>
						<div class="table-responsive">
							<div id="myTabContent" class="tab-content">
								   	<div class="table-responsive">
										<form id="tradeForm" action='<c:url value="<%=FinanceQueryPath.BASE + FinanceQueryPath.COMMERCIABALANCELIST %>"/>'  method="post">
											<table class="search-table"  >		
												<tr>	
												    <td class="td-left" width="20%">商户编号</td>
													<td class="td-right" width="30%">
													     <span class="input-icon">
														    <input type="text"   name="merchantCode" id="merchantCode" value="${queryBean.merchantCode}">
														    <i class="icon-leaf blue"></i>
														</span>
													</td>				    																			
													<td class="td-left" width="20%">商户名称</td>
													<td class="td-right" width="30%">
														<span class="input-icon">
														    <input type="text"   name="acctName" id="acctName" value="${queryBean.acctName }">
														    <i class="icon-leaf blue"></i>
														</span>
														 
													</td>
													
												</tr>	
												<tr>		
													<td colspan="4" align="center">
														<button type="submit" class="btn btn-purple btn-sm"  >
															查询
															<i class="icon-search icon-on-right bigger-110"></i>
														</button>
														<button class="btn btn-purple btn-sm btn-margin clearCommerciaForm" >
																清空
																<i class=" icon-move icon-on-right bigger-110"></i>
														</button>
														<gyzbadmin:function url="<%=FinanceQueryPath.BASE+FinanceQueryPath.EXPORTCOMMERCIABALANCE%>">
														<span class="input-group-btn" style="display:inline;">
															<a class="btn btn-purple btn-sm"  id="exportcommerciaBalance"  >
																导出报表
																<i class="icon-download bigger-110"></i> 
															</a> 
														</span>	
														</gyzbadmin:function>
														<gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.MERCHANTSWITHDRAWALBATCH%>">
														<span class="input-group-btn" style="display:inline;">
															<a class="btn btn-purple btn-sm batchWithdrawList"  href="#"  data-rel="tooltip"  data-toggle='modal' data-target="#batchModal">
																批量提现申请
																<i class="icon-download-alt bigger-110"></i> 
															</a> 
														</span>		
														</gyzbadmin:function>				
													</td>	
												</tr>
											</table>
										</form>
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th><label ><input type="checkbox" name="batchWithdrawDeposit"  id="batchWithdrawDeposit" title="全选"></label></th>
											<th>商户编号</th>
											<th>商户名称</th>
											<th>收款账号名</th>
											<th>提现银行卡号</th>
											<th>开户银行</th>
											<th>支行信息</th>
											<th width="6%">余额</th>	
											<th width="6%">可用余额</th>	
											<th width="6%">在途金额</th>	
											<th width="6%">可结算金额</th>
											<th width="6%">在途结算金额</th>
											<th width="6%">冻结金额</th>	
											<th>账户状态</th>	
											<th width="12%">操作</th>
										</tr>
									</thead>
									<tbody id="impeachData">
										<tr style="font-weight:bold">
											<td colspan="7">&nbsp;&nbsp;汇总：</td>		
											<td>${balanceCount.balanceCount}</td>							
											<td>${balanceCount.usableAmtCount}</td>	
											<td>${balanceCount.onwayAmtCount}</td>
											<td>${balanceCount.availableSettleAmtCount}</td>
											<td>${balanceCount.onwaySettleAmtCount}</td>
											<td>${balanceCount.freezeAmtCount}</td>
											<td colspan='2'></td>
										</tr>	
										<c:forEach items="${commerciaBalanceLists}" var="commercia" >
											<tr class="commercia" >
												<td>
													<c:if test="${commercia.usableAmt!='0.00'}">
													  <input name="checkDeposit" type="checkbox" value="${commercia.merchantCode}#${commercia.acctName}#${commercia.usableAmt}" >
													</c:if>
													<c:if test="${commercia.usableAmt=='0.00'}">
													  <input type="checkbox" disabled="disabled">
													</c:if>
												</td>
												<td>${commercia.merchantCode}</td>
												<td>${commercia.acctName}</td>
												<td>${commercia.bankCardName}</td>
												<td>${commercia.bankCardNo}</td>
												<td>${commercia.bankName}</td>
												<td>${commercia.branchBank}</td>	
												<td>${commercia.balance}</td>							
												<td>${commercia.usableAmt}</td>	
												<td>${commercia.onwayAmt}</td>
												
												<td>${commercia.availableSettleAmt}</td>
												<td>${commercia.onwaySettleAmt}</td>
												<td>${commercia.freezeAmt}</td>
												<td>${commercia.status}</td>
												<td>
													<gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.MERCHANTSWITHDRAWAL%>">
													    <c:if test="${commercia.usableAmt!='0.00'}">
														  <a data-toggle='modal' data-target="#addadModal"  onclick="loadWithdrawDeposit(this)" >
															<button type="button"   id="withdrawBtn"  class="btn btn-primary btn-xs"  >提现申请</button>
														 </a>
														</c:if>
														<c:if test="${commercia.usableAmt=='0.00'}">
														   <button type="button"  id="btnEmail" class="btn  btn-xs"  disabled="disabled" >提现申请</button>	
														</c:if>
													</gyzbadmin:function>
												    <gyzbadmin:function url="<%=FinanceQueryPath.BASE + FinanceQueryPath.REALTIMELIST%>">
														<input type="hidden" id="custId" value="${commercia.custId}">
														<a id="btnEmail2" onclick="queryWater(this)" data-toggle='modal'  class="tooltip-success detailLink" >
															<button type="button"   id="queryWaterBtn"  class="btn btn-primary btn-xs"  >查询流水</button>
														</a> 
													</gyzbadmin:function>
												</td>																		
											</tr>											
										</c:forEach>
										<c:if test="${empty commerciaBalanceLists}">
										<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody> 
								</table>
							</div>	
					</div>
				</div>	
					<!-- 分页 -->
				<c:if test="${not empty commerciaBalanceLists}">
					<%@ include file="/include/page.jsp"%>
				</c:if>												
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
	
		<div class="modal fade" id="addadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">单笔提现界面</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='' method="post" id="addadForm">
		              <table class="modal-input-table" style="width: 100%;">
						<tr>	
							<td class="td-left" width="30%">商户编号</td>
							<td class="td-right" width="70%">
								<input type="hidden" id="bankCardName" name="bankCardName"  >
								<input type="hidden" id="bankCardNo" name="bankCardNo"  >
								<input type="hidden" id="bankName" name="bankName"  >
								<input type="hidden" id="cacctId" name="cacctId"  >
								<input type="hidden" id="acctId" name="acctId">
								<input type="hidden" id="custId" name="custId">
								<input type="text" id="merchantCode" name="merchantCode"  readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">商户名称</td>
							<td class="td-right">
								<input type="text" id="acctName" name="acctName"  readonly="readonly" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">可提现余额</td>
							<td class="td-right">
								<input type="text" id="usableAmt" name="usableAmt"  readonly="readonly" style="width:80%">
								<label id="balanceLabel" class="label-tips" style="display: none"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">提现方式</td>
							<td class="td-right">
								 <select name="depositall" id="depositall" onchange="partWithdrawDeposit()" style="width:80%">
									  <option  value="ALLDEPOSITALL" >全部提现</option>
									  <option  value="PARTDEPOSITALL">部分提现 </option>
									 </select>
							</td>
						</tr>
						<tr>
							<td class="td-left">提现金额</td>
							<td class="td-right">
								<input type="text" id="partbalance" name="partbalance"  readonly="readonly" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		             <button type="button" class="btn btn-primary" onclick="withdrawDeposit()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
     <div class="modal fade" id="batchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 700px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">批量提现申请</h4>
		         </div>
		         <div class="modal-body">
		           <table class="list-table" id="tbPreviewBankCard">
			           <thead>
							<tr>
								<th>商户编号</th>
								<th>商户名称</th>
								<th>余额</th>
						    </tr>
					  </thead>
		            </table>	             
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary" onclick="batchWithdrawDeposit()">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div>
<script type="text/javascript">


/**控制只能输入金额**/
$("#partbalance").keyup(function () {
    var reg = $(this).val().match(/\d+\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    $(this).val(txt);
}).change(function () {
    $(this).keypress();
    var v = $(this).val();
    if (/\.$/.test(v))
    {
        $(this).val(v.substr(0, v.length - 1));
    }
});

function partWithdrawDeposit(){
	
	var depositall = $("#addadModal #depositall").val();
	var usableAmt = $("#addadModal #balanceLabel").text();
	var cObj = document.getElementById("partbalance");
	if(depositall=='ALLDEPOSITALL'){
		 cObj.value=usableAmt;
	     cObj.setAttribute("readOnly",'true');
	}
	if(depositall=='PARTDEPOSITALL'){
		cObj.value='';
		cObj.removeAttribute("readOnly");
	}
}
/**加载批量提现**/
var custIds="";
$(".batchWithdrawList").click(function(){	
	if($("input[type='checkbox'][name='checkDeposit']").is(':checked')==false){
		$.gyzbadmin.alertFailure("请选择提现选项");
		return false;
	}else{
		$("#tbPreviewBankCard tr:gt(0)").remove();
		var obj=document.getElementsByName('checkDeposit'); 
		var withdraws=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) {
				withdraws+=obj[i].value+'☺'; 
			}
		}
		withdraws=withdraws.substring(0,withdraws.length-1);
		
		var sArry=new Array();
		var sArry=withdraws.split("☺");
		var sContent=new Array()
		var str="";
		for(var j=0;j<sArry.length;j++){
			sContent=sArry[j].split("#");
			str+="<tr><td>"+sContent[0]+"</td><td>"+sContent[1]+"</td><td>"+sContent[2]+"</td></tr>"
		}
		$("#tbPreviewBankCard").append(str).append('<tr style="display:none"><td colspan="3"><input type="text" id="withdrawArrs" name="withdrawArrs" value="'+withdraws+'" style="width:100%"></td></tr>');	
	}
})

/**批量提现**/
function batchWithdrawDeposit(){		
	var withdrawArray=$("#withdrawArrs").val();
	$.blockUI();
	$.post(window.Constants.ContextPath + '<%=FinanceQueryPath.BASE + FinanceQueryPath.MERCHANTSWITHDRAWALBATCH%>', {
		"depositall":withdrawArray
		}, function(data) {
			$.unblockUI();
			if(data.result == 'success'){
				$('#batchModal').modal('hide');
				$.gyzbadmin.alertSuccess('批量提现申请成功', null, function(){
					window.location.reload();
				});
			} else {
				$.gyzbadmin.alertFailure(data.message);
			}
		}, 'json'
	);
}
/**加载提现**/			
function loadWithdrawDeposit(obj){
	
	 var result = $.data($(obj).parent().parent()[0],"commercia");
	 $('#addadModal').on('show.bs.modal', function () {
		 $("#addadModal #custId").val(result.custId);
		$("#addadModal #acctId").val(result.acctId);
		$("#addadModal #acctName").val(result.acctName);
		$("#addadModal #usableAmt").val(result.usableAmt);
		$("#addadModal #merchantCode").val(result.merchantCode);
		$("#addadModal #cacctId").val(result.cacctId);
		$("#addadModal #bankCardNo").val(result.bankCardNo);
		$("#addadModal #bankCardName").val(result.bankCardName);
		$("#addadModal #bankName").val(result.bankName);	
		$("#addadModal #partbalance").val(result.usableAmt);	
		$("#addadModal #balanceLabel").text(result.usableAmt);	
    })
	$('#addadModal').on('hide.bs.modal', function () {
		$("#addadModal #acctId").val('');
		$("#addadModal #acctName").val('');
		$("#addadModal #usableAmt").val('');
		$("#addadModal #merchantCode").val('');
		$("#addadModal #cacctId").val('');
		$("#addadModal #bankCardNo").val('');
		$("#addadModal #bankCardName").val('');
		$("#addadModal #bankName").val('');	
		$("#addadModal #partbalance").val('');	
		$("#addadModal #balanceLabel").text('');	
    })
}

/**提现**/	
function withdrawDeposit(){
	var usableAmt=$("#addadModal #usableAmt").val();
	var partbalance=$("#addadModal #partbalance").val();		
	var acctId=$("#addadModal #acctId").val();
	var acctName=$("#addadModal #acctName").val();
	var merchantCode=$("#addadModal #merchantCode").val();
	var bankCardNo=$("#addadModal #bankCardNo").val();
	var bankCardName=$("#addadModal #bankCardName").val();
	var cacctId=$("#addadModal #cacctId").val();
	var bankName=$("#addadModal #bankName").val();
	
	if(kong.test(partbalance)){
		$.gyzbadmin.alertFailure("请输入提现金额");
		$("#addadModal #partbalance").fous();
		return;
	}
	if(parseFloat(partbalance)<=0){			
		$.gyzbadmin.alertFailure("提现必须大于0.00元");
		$("#addadModal #partbalance").fous();
		return;
	}
	if(parseFloat(partbalance)>parseFloat(usableAmt)){	
		$.gyzbadmin.alertFailure("提现金额不能大于可提现余额");
		$("#addadModal #partbalance").fous();
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=FinanceQueryPath.BASE + FinanceQueryPath.MERCHANTSWITHDRAWAL %>',
		data:{
			"usableAmt":usableAmt,
			"acctId":acctId,
			"cacctId":cacctId,
			"acctName":acctName,
			"merchantCode":merchantCode,
			"bankName":bankName,
			"bankCardName":bankCardName,
			"bankCardNo":bankCardNo,
			"partbalance2":partbalance,
			"custId":$("#addadModal #custId").val()
		},
		success:function(data){
			$.unblockUI();
			if(data.result=='success'){
				$.gyzbadmin.alertSuccess("提现申请成功！",function(){
				$("#addadModal").hide();	
				},function(){
					window.location.reload();
				});
			}else{
				$.gyzbadmin.alertFailure(data.message);
			}
		}
	});
	 
}

var winChild;
function queryWater(obj){
	var custId=$(obj).prev().val();
	var url=window.Constants.ContextPath+"<%=FinanceQueryPath.BASE+ FinanceQueryPath.REALTIMELIST%>?custId="+custId+""; 
     var name="window";                        //网页名称，可为空;
     var iWidth=1200;                          //弹出窗口的宽度;
     var iHeight=600;                       //弹出窗口的高度;
     //获得窗口的垂直位置
     var iTop = (window.screen.availHeight-30-iHeight)/2;
     //获得窗口的水平位置
     var iLeft = (window.screen.availWidth-10-iWidth)/2;
     var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft;
	winChild = window.open(url, name,params);
	$.blockUI();
}

	function forCloseDiv(){
		 $.unblockUI();
	} 
	
	$(function () {	

		var loop =	setInterval(function() {     
		    if(winChild.closed) { 
		    	$.unblockUI();
		    }    
		}, 200); 
		
	  $.fn.check({ checkall_name: "batchWithdrawDeposit", checkbox_name: "checkDeposit" })	
      $('#myTab li:eq(2) a').tab('show');
        var resultList= ${commerciaBalanceLists};		    
	 	var commercias=$("tr.commercia");
		$.each(resultList,function(i,value){						
			$.data(commercias[i],"commercia",value);				
		});
		
		$(".clearCommerciaForm").click(function(){
			$(".search-table #acctName").val('');
			$(".search-table #merchantCode").val('');
		})
	});
	
	$("#exportcommerciaBalance").click(function(){
		var acctName =$(".search-table #acctName").val();
		var merchantCode =$(".search-table #merchantCode").val();
		var src="<%=request.getContextPath()+FinanceQueryPath.BASE+FinanceQueryPath.EXPORTCOMMERCIABALANCE%>?acctName="+acctName+"&merchantCode="+merchantCode;
		$("#exportcommerciaBalance").attr("href",src);
   })
  
    $("#balanceWhole").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.LIST%>";
	 });
	
	
	$("#changeBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.CHANGEBALANCELIST%>";
	 });
	
	$("#userBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.USERBALANCELIST%>";
	 });
	
	$("#cashSettlement").click(function(){
		window.location.href="<%=request.getContextPath()+WithdrawPath.BASE + WithdrawPath.WITHDRAWLIST%>";
	 });
	
	$("#commerciaBalance").click(function(){
		window.location.href="<%=request.getContextPath()+FinanceQueryPath.BASE + FinanceQueryPath.COMMERCIABALANCELIST%>";
	 });
	
	$("#merchantSettlement").click(function(){
		window.location.href="<%=request.getContextPath()+MerchantWithdrawPath.BASE + MerchantWithdrawPath.LIST%>";
	 });

	$("#withdrawrevoke").click(function(){
		window.location.href="<%=request.getContextPath()+WithdrawRevokePath.BASE + WithdrawRevokePath.WITHDRAWREVOKE%>";
	 });
</script>
</body>
</html>					