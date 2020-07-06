<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page
import="com.qifenqian.bms.accounting.financequery.FinanceQueryPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgenReportPath" %>
<%@page import="com.qifenqian.bms.paymentmanager.PaymentManagerPath"%>
<%
	String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
%>
<%-- <link rel="stylesheet"
	href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script
	src='<c:url value="/static/js/bootstrap-datetimepicker.min.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/jquery.blockUI.js?v=${_jsVersion}"/>'/></script>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/AutoDatePicker.js"/>'></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>代付审核列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
table tr td {
	word-wrap: break-word;
	word-break: break-all;
}

li {
	list-style-type: none;
}

.displayUl {
	display: none;
}
</style>
</head>
<body >
<%@ include file="/include/alert.jsp"%>
	<div class="modal-content" style="width: 100%;">
		<input type="hidden" name="idval" id="idval" />
		<div class="">
			
				
			<div class="list-table-header">代付详情
			
			</div>
			
			
		  
			<div class="modal-body">
				<table id="sample-table-2" class="list-table">
						<tr>
										<td colspan="4" class="headlerPreview"></td>
									</tr>
									<tr>
										<td class="td-left" width="18%">商户名称：</td>
										<td class="td-right" width="32%"><input type="text" id="custName_audit" value="${auditPayment.custName }" readonly="readonly" name="custName_audit" maxlength="100" placeholder="商户名称" style="width: 90%">
                                        <input type="hidden" id="_token" name="_token"/>
										</td>
										<td class="td-left" width="18%">代付金额：</td>
										<td class="td-right" width="32%"><input type="text"
											id="sumAmt" name="sumAmt" value="${auditPayment.sumAmt}" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>
										<td class="td-left">代付账号：</td>
										<td class="td-right"><input type="text" id="paerAcctNo_1"
											name="paerAcctNo_1" value="${auditPayment.paerAcctNo }" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付笔数：</td>
										<td class="td-right"><input type="text" id="sumCount_1"
											name="sumCount_1"  value="${auditPayment.sumCount }" placeholder="身份证号" readonly="readonly"
											style="width: 90%"></td>


									</tr>
									<tr>
										<td class="td-left">手续费：</td>
										<td class="td-right"><input type="text" id="serviceFee_1"
											name="serviceFee_1" placeholder="身份证号" value="${auditPayment.feeAmt }" readonly="readonly"
											style="width: 90%"></td>
										<td class="td-left">代付类型：</td>
										<td class="td-right">
										<input type="text" id="topay_1" name="topay_1" value="${toPayType2}" placeholder="代付方式" readonly="readonly" style="width: 90%">
										<input type="hidden" id="custId" name="custId" value="${auditPayment.custId}" placeholder="代付方式" readonly="readonly" style="width: 90%">
										</td>



									</tr>
									<tr>


										<td class="td-left">代付方式：</td>
										<td class="td-right"><input type="text" id="pay_1" name="pay_1" value="批量" placeholder="代付方式" readonly="readonly" style="width: 90%"></td>

										<td class="td-left">实付总额：</td>
										<td class="td-right"><input type="text" id="totalMoney" value="${totalMoney }" name="totalMoney" placeholder="实付总额" readonly="readonly"
											style="width: 90%"></td>
									</tr>
									<tr>

										<td class="td-left">交易号：</td>
										<td class="td-right"><input type="text" id="bataNo_1"
											name="bataNo_1" placeholder="交易号" value="${auditPayment.batNo }" readonly="readonly"
											style="width: 90%"></td>

										<td class="td-left">时间：</td>
										<td class="td-right"><input type="text" id="createTime" name="createTime" placeholder="时间" value="${auditPayment.startCreateTime}" readonly="readonly" style="width: 90%">
										<input type="hidden"  id='audit_flag' name="audit_flag" value="${audit_type}">
										</td>


									</tr>
									
                                 <c:if test="${audit_type=='Finance' }">
                                 	<tr >
										<td class="td-left">代付渠道：</td>
										<td class="td-right">
											<select id="channelType" name="channelType">
												<!-- <option value="CR">华润</option>
												<option value="ECITIC">中信</option> -->
												<option value="Z_BANK">众邦</option>
											</select> 
											<select id="channelMerchantid" name="channelMerchantid">
												<!-- AUDITPAYMENTTINFOS -->
												<!-- <option value="channelMerchantid">channelMerchantidArray</option> -->
												<option value="Z0000842">Z0000842</option>
												<option value="Z0000841">Z0000841</option>
											</select>
										</td>
										
									</tr>
                                 </c:if>
								</table>
								<div id="">
									代付金额为：<span id="toPatSumAmt">${auditPayment.sumAmt}(元)</span>&nbsp;&nbsp; 代付笔数：${auditPayment.sumCount}<span
										id="toPayCount"></span>&nbsp;&nbsp;手续费：${auditPayment.feeAmt }<span id="toPayFee"></span>
									&nbsp;&nbsp;实付总额：${totalMoney}<span id='toPaySumMoney'></span>
								</div>
			
			
			
			
			
			
			
			
			
			<div class="table-responsive" id="createtable">
				<input type="hidden" id="toPayType" value="${toPayType}">
				<table id="sample-table-2" class="list-table">
					<thead>
						<tr>
							<th>名称</th>
							<th>银行信息</th>
							<th>银行卡号</th>
							<th>代付金额</th>
							<th>手续费</th>
						</tr>
					</thead>

					<tbody id="impeachData">
					  <c:if test="${toPayType=='00'}">
						<c:forEach items="${paymentList}" var="result">
							<tr class="result">
								<td>${result.recAccountName}</td>
								<td>${result.recCardName}</td>
								<td>${result.recAccountNo}</td>
								<td>${result.transAmt}</td>
								<td>${result.payFee}</td>
								<td>
								<c:choose>
							    <c:when test="${result.tradeStatus =='00'}">  									  
							               付款成功                           								  
							     </c:when>
						        <c:when test="${result.tradeStatus =='99'}">  									  
							                付款失败                      								  
							    </c:when>
								</c:choose>
								</td>
							</tr>
						</c:forEach>
						</c:if>
						 
						<c:if test="${empty paymentList}">
							<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
						</c:if>
					</tbody>
				</table>
				<c:if test="${audit_type=='Clste'}">
				<div id="hiddenCheck" class="modal-footer"> <button type="button" class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
				<button type="button" class="btn btn-primary firstAuditPassBtn">审核通过</button>
				</div>
				</c:if>
				<c:if test="${audit_type=='Finance' }">
				<div id="hiddenCheck_2" class="modal-footer">
				<button type="button" class="btn btn-primary secondAuditPassBtn"    data-toggle='modal'  data-target="#secondAuditMessageModel">确认打款并审核通过</button>
                <button type="button" class="btn btn-primary firstAuditNotPassBtn" data-toggle='modal' data-target="#firstAuditMessageModel">审核不通过</button>
				</div>
				
				</c:if>
				
				
				
				</div>
			</div>
			<c:if test="${not empty paymentList}">														
				<%@ include file="/include/page.jsp"%>		
			</c:if>
		</div>
		
	</div>
	
	<div class="modal fade" style="z-index: 1043;" id="firstAuditMessageModel" tabindex="-1" role="dialog"
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
										value="银行付款凭凭证号审核不通过" />&nbsp;银行付款凭证号审核不通过</label>
									</br>
									<!-- <label><input name="test" type="checkbox"
										value="银行卡卡号与开户行不匹配" />&nbsp;银行卡卡号与开户行不匹配</label>
									</br>
									<label><input name="test" type="checkbox"
										value="银行账户不存在" />&nbsp;银行账户不存在</label> -->
									
									</br> 其他原因
									<tr>
										<td><textarea rows="5" cols="40" id="auditMessage"></textarea>
										</td>
									</tr>


								</table>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn" onclick="firstAuditNotPassBtn();">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
	
				<!-- 财务打款静态框 -->
				<div class="modal fade" style="z-index: 1043;"
					id="secondAuditMessageModel" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 40%; z-index: 99;">
						<div class="modal-content">
							<div class="modal-header" style="background-color: 0099CC">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">确认打款并通过审核</h4>
							</div>
							
							<div class="modal-body">

								<div id="selectCheckbox">

									您确认完成代付审核？
								</div>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default messageDefault"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary addadBtn"
									onclick="secoundAuditPassBtn();">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
				<!-- /.modal -->   
	
	
	<!-- /.modal-content -->
	<script type="text/javascript">
	
	function closeWindow(){
		var _parentWin = window.opener;
		_parentWin.window.forCloseDiv();
		 window.close(); 
	   }
	
	function firstAuditNotPassBtn(){
		 var batNo=$('#bataNo_1').val();
		 var email=$('#paerAcctNo_1').val();
		 var custName=$('#custName_audit').val();
		 var reason=$("#auditMessage").val();
		 var audit_flag=$("#audit_flag").val();
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
		  
		      var messages=null;
			   if(null!=chk_value && chk_value.length>=1 && ""!=chk_value){
				   for (var j = 0; j < chk_value.length; j++) {
					 messages = chk_value[j];
				   }
				   if(chk_value.length>=2){
					   messages=chk_value.join();
				    }
				   if(null!=reason && 'null'!=reason && ""!=reason){
					   messages=chk_value.join()+","+reason;
				   }
			   }else{
				   messages=reason;
			   }
			   
	       $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITFRISTNOTPASS %>',
					data:
					{
						"batNo" 	:batNo,
						"message"	:messages,
						"email"     :email,
						"custName"  :custName,
						"audit_flag" :audit_flag
						
						
					},
					success:function(data){
						if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("审核完成！",function(){
								window.close();
								window.opener.location.reload(); 
							},function(){
								window.opener.location.reload(); 
							});
						}else{
							alert("审核失败！"+data.message);
							window.close();
							window.opener.location.reload(); 
						}
					}
				  }) 
		       }
	
	function secoundAuditPassBtn(){
		 $.blockUI();
		 var batNo=$('#bataNo_1').val();
		 var channelType=$('#channelType').val();
		 var custId=$("#custId").val();
		 //财务批量审核
		 var channelMerchantid=$("#channelMerchantid").val();
		      $.ajax({
					type:"POST",
					dataType:"json",
					url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.BATPAYMENTPASS %>',
					data:
					{
							"batNo" 	: batNo,
							"custId"    :custId,
							'_TOKEN_ID' :$("#_token").val(),
							'channelType':channelType,
							'channelMerchantid' :channelMerchantid
					},
					success:function(data){
							
							if(data.result=="SUCCESS"){
								$.gyzbadmin.alertSuccess("审核完成",function(){
									 window.close();
									 window.opener.location.reload(); 
								},function(){
									window.opener.location.reload(); 
								});
							}else{
								alert("审核失败！"+data.message);
								window.close();
								window.opener.location.reload(); 
								
							}
						}
					}) ;
	           }
	
	
     $(document).ready(function(){
    	 
    	//存token
		 $.post(window.Constants.ContextPath + '<%=PaymentManagerPath.BASE + PaymentManagerPath.SAVE_TOKEN_TOPAY %>',{
		 }, function(data) {
			var token = data.token;
			$("#_token").val(token);
		}, 'json'
	    );
    	 
    	 
    	    /** 审核通过 */
    		$(".firstAuditPassBtn").click(function(){
    			 var batNo=$('#bataNo_1').val();
    			 var email=$('#paerAcctNo_1').val();
    			 var custName=$('#custName_audit').val();
    			 
    				 $.ajax({
    						type:"POST",
    						dataType:"json",
    						url:window.Constants.ContextPath +'<%=PaymentManagerPath.BASE+ PaymentManagerPath.AUDITFRISTPASS %>',
    						data:
    						{
    							"batNo" 	:batNo,
    							"email"     :email,
    							"custName"  :custName
    							
    						},
    						dataType:"json",
    						success:function(data){
    							if(data.result=="SUCCESS"){
    								$.gyzbadmin.alertSuccess("审核完成！",function(){
    									window.close();
    									window.opener.location.reload(); 
    								},function(){
    									window.opener.location.reload(); 
    								});
    							}else{
    								alert("审核失败！"+data.message);
    								window.close();
    								window.opener.location.reload(); 
    							};
    						}
    					});
    	
                     });
   
	             });
         

	</script>
	
 
	
</body>
</html>
