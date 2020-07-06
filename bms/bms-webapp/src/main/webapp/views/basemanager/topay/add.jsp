<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.qifenqian.bms.basemanager.photo.PhotoPath"%>
<%@page import="com.qifenqian.bms.basemanager.toPay.controller.TopayPath"%>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>新增代付</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		.uploadImage{ float:left; 
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:100px;
			height:80px;
			}
		.noboder{
			border: 0px;
			outline:none;
			cursor: pointer;
		}
	</style>
</head>
<script type="text/javascript">

</script>
<body >
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
						<!-- 查询条件 -->
							<form  id="formData" method="post">
							<input type="hidden" name="dataTotal" id="dataTotal"></input>
							<table class="search-table">
								<tr>
									<td class="td-left">付款商户对应商户编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="paerMerchantCode" id="paerMerchantCode" >
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >单笔手续费（元）</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="singleFeeAmt" id="singleFeeAmt" value="2" >
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								
								</tr>
								
							</table>
							
							<div class="list-table-header">
								请填写代付详细信息
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table" style="table-layout:fixed;">
									<thead>
										<tr>
											<th>收款人名称</th>
											<th>收款银行名称</th>
											<th>收款银行编号</th>
											<th>收款人银行卡号</th>
											<th>收款人开户省份</th>
											<th>收款人开户城市</th>
											<th>金额</th>											
										</tr>
									</thead>
									<tbody id="toPayBody" >
										<tr class="info1" >
											<td><input class="noboder" name="recAccountName"></input></td>
											<td><input class="noboder" name="recCardName"></input></td>
											<td><input class="noboder" name="recBankCode"></input></td>
											<td><input class="noboder" name="recAccountNo"></input></td>
											<td><input class="noboder" name="recBankProvince" placeholder="xx省"></input></td>
											<td><input class="noboder" name="recBankCity" placeholder="xx市/县"></input></td>
											<!-- <td>
												<select " id="recBankProvince0" name="recBankProvince0" onchange="getCity();">
													<option value="">--请选择省--</option>
						                            <c:if test="${not empty provinceList }">
							                            <c:forEach items="${provinceList }" var="province">
							                            	<option id="${province.provinceId}" value="${province.provinceId}">${province.provinceName}</option>
							                            </c:forEach>
					                    		  	</c:if>
												</select>
											</td>
											<td>
												<select name="recBankCity" id="recBankCity">
													<option value="">--请选择市--</option>
												</select>
												<label id="cityLabel" class="label-tips"></label>
											</td> -->
											<td><input class="noboder" name="transAmt"></input></td>											
										</tr>
																																					
									</tbody>
									<tr>
										<td colspan="7">
											<button  type="button" class="btn btn-purple btn-sm btn-margin" onclick="addTr()" style="display:block;margin:0 auto">
												新增一行<i class=" icon-plus-sign icon-on-right bigger-110"></i>
											</button>
											
										</td>		
									</tr>
									
																		
								</table>
								<div class="modal-footer">
						            <button type="button" class="btn btn-primary updateAdBtn" onclick="submitData()">提交</button>
						        </div>
						        </form>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty authList}">
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
		<div class="modal fade" id="updatePhotoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新认证信息</h4>
		         </div>
		         <div class="modal-body">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="30%">编号</td>
							<td class="td-right" width="70%">
								<input type="text" id="authId" name="authId" readonly="readonly" clasS="width-80">
							</td>
						</tr>
		            	<tr>
							<td class="td-left" width="30%">客户ID</td>
							<td class="td-right" width="70%">
								<input type="text" id="custId" name="custId" readonly="readonly" clasS="width-80">
							</td>
						</tr>
		            	<tr>
							<td class="td-left">客户名称</td>
							<td class="td-right">
								<input type="text" id="custName" name="custName" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left">用户手机号</td>
							<td class="td-right">
								<input type="text" id="mobile" name="mobile" readonly="readonly" clasS="width-80">
								<input type="hidden" id="mobile2" name="mobile2"  >
							</td>
						</tr>
						<tr class="photo">
							<td class="td-left">证件号码</td>
							<td class="td-right">
								<input type="text" id="certifyNo" name="certifyNo" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>	
							<td class="td-left">扫描件类型</td>
							<td class="td-right">
								<select id="certifyType" name="certifyType" disabled="disabled" clasS="width-80">
									<option value="">- 请选择  -</option>
									<option value="00">身份证</option>
									<option value="01">税务登记证</option>
									<option value="02">营业执照</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">申请时间</td>
							<td class="td-right">
								<input type="text" id="createTime" name="createTime" readonly="readonly" clasS="width-80">
							</td>
						</tr>
						<tr>
							<td class="td-left">公安认证情况</td>
							<td class="td-right">
								<textarea  id="policeAuthResult" name="policeAuthResult" rows="4" clasS="width-80"> </textarea>
							</td>
						</tr>
						<tr>
							<td class="td-left">实名认证状态</td>
							<td class="td-right">
								<select id="certificateState" name="certificateState" clasS="width-80">
									<option value="1">待认证</option>
									<option value="0">认证通过</option>
									<option value="2">认证不通过</option>
									<option value="9">取消</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left">认证情况描述</td>
							<td class="td-right">
								<textarea  id="authResultDesc" name="authResultDesc" rows="4" clasS="width-80"> </textarea>
							</td>
						</tr>
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary udpatePhotoBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:60%;height:80%;">
		         <div id="showImageDiv" style="width:100%;height:100%;">
		           <!-- <!-- <input type="hidden" name="showImageInput" id="showImageInput" class="showImageInput"> -->
		           <img id="showImage" style="width:100%;height:100%;">
		         </div>
		     </div>
		</div>
<script>
var index = 1;
function submitData(){
	$("#dataTotal").val(index);
	
	var recAccountName = new Array();
	var recCardName = new Array();
	var recBankCode = new Array();
	var recAccountNo = new Array();
	var recBankProvince = new Array();
	var recBankCity = new Array();
	var recTransAmt = new Array();
	
	var trList = $("#toPayBody").children("tr")
	  for (var i=0;i<trList.length;i++) {
	    var tdArr = trList.eq(i).find("td");
	    
	    var accountName = tdArr.eq(0).find("input").val(); //收款人姓名
	    if(kong.test(accountName)){
			$.gyzbadmin.alertFailure("收款人姓名不能为空");
			return false;
		}
		
	    var cardName = tdArr.eq(1).find("input").val(); //收款银行名称 td_tyy_bank_info
	    if(kong.test(cardName)){
			$.gyzbadmin.alertFailure("收款银行名称不能为空");
			return false;
		}
		
	    var bankCode = tdArr.eq(2).find("input").val(); //收款银行编号 td_tyy_bank_info
	    
	    var accountNo = tdArr.eq(3).find("input").val(); //收款人银行卡号
	    var reg=/^[0-9]{15,20}$/; 
	    if(kong.test(accountNo)){
			$.gyzbadmin.alertFailure("收款银行卡号不能为空");
			return false;
		}else if(!reg.test(accountNo)){
			$.gyzbadmin.alertFailure("收款银行卡号有误,请重新检查");
			return false;
		}
		
	    var bankProvince = tdArr.eq(4).find("input").val();//收款人开户省份 td_zb_province_code
	    if(kong.test(bankProvince)){
			$.gyzbadmin.alertFailure("收款人开户省份不能为空");
			return false;
		}
		
	    var bankCity = tdArr.eq(5).find("input").val(); //收款人开户城市 td_zb_city_code
	    if(kong.test(bankCity)){
			$.gyzbadmin.alertFailure("收款人开户城市不能为空");
			return false;
		}
		
	    /* if(i == "0"){
	    	var bankProvince = $("#recBankProvince0").val(); 
		}else{
			//var bankProvince = $("#recBankProvince11").val(); 
			var bankProvince = tdArr.eq(4).find("input").val(); //收款人开户省份 td_zb_province_code
		}
	    if(i == "0"){
	    	var bankCity = $("#recBankCity").val(); 
		}else{
			var bankCity = tdArr.eq(5).find("input").val(); //收款人开户城市 td_zb_city_code
		} */
	    
	    var transAmt = tdArr.eq(6).find("input").val(); //金额
		if(kong.test(transAmt)){
			$.gyzbadmin.alertFailure("代付金额不能为空");
			return false;
		}
		if(transAmt > 50000){
			$.gyzbadmin.alertFailure("代付金额不能超过五万");
			return false;
		}
		
	    recAccountName[i] = accountName;
	    recCardName[i] = cardName;
	    recBankCode[i] = bankCode;
	    recAccountNo[i] = accountNo;
	    recBankProvince[i] = bankProvince;
	    recBankCity[i] = bankCity;
	    recTransAmt[i] = transAmt;
	}
	
	var paerMerchantCode = $("#paerMerchantCode").val();
	if(kong.test(paerMerchantCode)){
		$.gyzbadmin.alertFailure("商户编号不能为空");
		return false;
	}
	var singleFeeAmt = $("#singleFeeAmt").val();

	var topayData = $("#formData").serialize();
	$.blockUI();
	 $.ajax({
         url: "<%=request.getContextPath()+TopayPath.BASE+TopayPath.SAVE%>",
         type: "POST",
         dataType:"json",
         traditional: true,
         data:{
             "paerMerchantCode" : paerMerchantCode,
        	 "singleFeeAmt" : singleFeeAmt,
        	 "recAccountName" : recAccountName,
        	 "recCardName" : recCardName,
        	 "recBankCode" : recBankCode,
        	 "recAccountNo" : recAccountNo,
        	 "recBankProvince" : recBankProvince,
        	 "recBankCity" : recBankCity,
        	 "recTransAmt" : recTransAmt,
        	 "dataTotal" : index
         },
         success : function(data){
        	 $.unblockUI();
             if(data.result == 'SUCCESS'){
            	 $.gyzbadmin.alertSuccess("代付信息提交成功！",function(){
					},function(){
						window.location.reload();
					});
			 }else{
				$.gyzbadmin.alertFailure(data.message);
			 };
         }
     });
}

function addTr(){
	var trClass = "info"+index;
	nextIndex = index + 1;
	nextClass = "info" + nextIndex;
	recBankProvince11 = "recBankProvince" + index;
//	alert(nextClass);
	var currentRow = $("#toPayBody");
	var str = "<tr class='"+nextClass+"'>" + 
			  "<td><input class='noboder' name='recAccountName'></input></td>" +
			  "<td><input class='noboder' name='recCardName'></input></td>" +
			  "<td><input class='noboder' name='recBankCode'></input></td>" +
			  "<td><input class='noboder' name='recAccountNo'></input></td>" +
			  "<td><input class='noboder' name='recBankProvince' placeholder='xx省'></input></td>" +
			  /* "<td><input class='noboder' name='recBankProvince' id='"+recBankProvince11+"'></input></td>" +  */
			  /* "<td><select id='"+recBankProvince11+"'><option value=''>--请选择省--</option><c:if test='" +${not empty provinceList }+ "'>" 
			  for(var i=0;i<provinceList.length;i++){
					$("#recBankProvince11").append("<option value='"+provinceList.provinceCode+"'>"+provinceList[i].provinceName+"</option>");
				}
			  "</c:if></select></td>"+ */
			  "<td><input class='noboder' name='recBankCity' placeholder='xx市/县'></input></td>" +
			  "<td><input class='noboder' name='transAmt'></input></td></tr>";										
	currentRow.append(str);
	index++;
	
}


function getCity(){
	var provinceCode =$("#recBankProvince0").val().trim();

	$.ajax({
        url: "<%=request.getContextPath()+TopayPath.BASE+TopayPath.SELCITY%>",
        type: "POST",
        dataType:"json",
        data:{
           "provinceCode" :provinceCode
        },
        success: function(data){
            if(data.result == "SUCCESS"){
            	var cityList = data.cityList;
				$("#recBankCity").html("");
       			for ( var recBankCity in cityList) {
       				$("#recBankCity").append(
       						"<option value='"+ cityList[recBankCity].cityId +"'>"
       								+ cityList[recBankCity].cityName + "</option>"); 
       			}
			}else{
				alert("省份不能为空");
			}
        }
    });
}

</script>
</body>
</html>