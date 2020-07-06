<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.seven.micropay.channel.enums.suixinpay.SuixinBankType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<script src="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css" rel="stylesheet">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>通联商户信息修改</title>
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
		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>
			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<input type="hidden" id="channelCode" name="channelCode" value="ALLIN_PAY"/>
					<input type="hidden" id="mchId" name="mchId" value="${merchantDetailInfo.mchId}" />
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    <form  id="merchantForm"  method="post">
					    <table id="merchant_table" class="list-table">
					    <tbody>
                        	<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
							</tr>
						    <tr>
							</tr>
                            <tr>
								<td class="td-left" width="18%">商户编号：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" data-validation="notnull" data-errMsg="商户编号不能为空" readonly placeholder="请输入商户编号" maxlength=""  value="${merchantDetailInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" placeholder="请输入商户名称" maxlength="" data-validation="notnull" data-errMsg="商户名称不能为空"  value="${merchantDetailInfo.custName }" style="width:90%">
								</td>
							</tr>
							<tr>
                                <td class="td-left" width="18%">商户简称：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="shortName" name="shortName" placeholder="请输入商户简称" maxlength="" data-validation="notnull" data-errMsg="商户简称不能为空"  value="${merchantDetailInfo.shortName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
								   <select  name="mecTypeFlag" id="mecTypeFlag" data-validation="notnull"  data-errMsg="商户类型不能为空"  onchange = "getMecType();" class="width-90" >
										<option value="">--商户性质--</option>
										<option value="1" <c:if test="${merchantDetailInfo.mecTypeFlag == '1'}">selected</c:if> >--企业--</option>
										<option value="3" <c:if test="${merchantDetailInfo.mecTypeFlag == '3'}">selected</c:if> >--个体户--</option>
										<option value="4" <c:if test="${merchantDetailInfo.mecTypeFlag == '4'}">selected</c:if> >--个人--</option>
									</select>
									<label class="label-tips" id="mecTypeFlagLab"></label>	
								</td>
							</tr>
	                        <tr>
								<td class="td-left" width="18%">客服号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right" width="32%"> 
									<input type="text" id="contactPhone" name="contactPhone" readonly placeholder="请输入商户编号" data-validation="notnull" data-errMsg="客服号码不能为空" maxlength=""  value="${merchantDetailInfo.contactPhone }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：<span style="color:red;">(必填)</span></td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
										<input type="hidden" id="allinPayMerchantProvince" name="allinPayMerchantProvince" value="${merchantDetailInfo.merchantProvince}" >
	                                    <select class="form-control" name="merchantProvince" data-validation="notnull" data-errMsg="商户注册省份不能为空" id="merchantProvince" >
	                                       	<option value="">--请选择省--</option>
		                                    <c:if test="${not empty allinPayAreaInfoList }">
		                                        <c:forEach items="${allinPayAreaInfoList }" var="merchantProvince">
		                                            <option id="${merchantProvince.provinceId}"
		                                                    value="${merchantProvince.provinceId}">
		                                                ${merchantProvince.provinceName}
		                                            </option>
		                                        </c:forEach>
		                                    </c:if>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
	                                	<input type="hidden" id="allinPayMerchantCity" name="allinPayMerchantCity" value="${merchantDetailInfo.merchantCity}" >
		                                <select class="form-control" name="merchantCity" data-validation="notnull" data-errMsg="商户注册城市不能为空" id="merchantCity">
		                                    <option value="" id="cityDef">--请选择市--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr"  placeholder="详细地址"  value="${merchantDetailInfo.cprRegAddr}" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
						 	<tr id="businessPhotoType1" style = "display:">
								<td class="td-left">营业执照名称：</td>
								<td class="td-right">
									<input type="text" name="cprRegNmCn" id="cprRegNmCn"  placeholder="请输入营业名称"  value="${merchantDetailInfo.cprRegNmCn}" style="width:90%">
								</td>
								<td class="td-left">营业执照有效期：</td>
								<td class="td-right">
									<input type="text" name="businessEffectiveTerm" id="businessEffectiveTerm" value="${merchantDetailInfo.businessEffectiveTerm}" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
                                    <input type="text" name="businessTerm" id="businessTerm" value="${merchantDetailInfo.businessTerm}" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                    <input type="button" onclick="businessForever()" value="长期" />
								</td>
							</tr>
							<tr id="businessPhotoType2" style = "display:">
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="businessLicense" id="businessLicense"  placeholder="请输入营业编号"  value="${merchantDetailInfo.businessLicense}" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">拓展人：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" id="expanduser" data-validation="notnull" data-errMsg="拓展人不能为空" name="expanduser" value="${merchantDetailInfo.expanduser}" placeholder="请输入拓展人姓名" maxlength="50" style="width:90%">
									
									<label class="label-tips" id="expanduserLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">线上线下业务场景：</td>
								<td class="td-right">
									<select  name="offlag" id="offlag"  class="width-90" >
										<option value="0" <c:if test="${merchantDetailInfo.offlag == '0'}">selected</c:if> >--线下--</option>
										<option value="1" <c:if test="${merchantDetailInfo.offlag == '1'}">selected</c:if> >--线上--</option>
									</select>
								</td>
								<td class="td-left">合同有效日期：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
                                    <input type="text" name="contractDate" id="contractDate" value="${merchantDetailInfo.contractDate}" data-validation="notnull" data-errMsg="合同有效日期不能为空" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" data-validation="notnull" data-errMsg="法人真实姓名不能为空" placeholder="请输入法人真实姓名"  value="${merchantDetailInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">法人证件类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="representativeCertType" data-validation="notnull" data-errMsg="法人证件类型不能为空" id="representativeCertType" style="width:90%;"  >
										<option value="01" <c:if test="${merchantDetailInfo.representativeCertType == '01'}">selected</c:if> >--身份证--</option>
										<option value="04" <c:if test="${merchantDetailInfo.representativeCertType == '04'}">selected</c:if> >--港澳居民往来内地通行证--</option>
										<option value="05" <c:if test="${merchantDetailInfo.representativeCertType == '05'}">selected</c:if> >--台湾居民来往大陆通行证--</option>
									</select>
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人身份证号码：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" data-validation="notnull" data-errMsg="法人身份证号码不能为空" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码"  value="${merchantDetailInfo.representativeCertNo }" style="width:90%">
								</td>
								<td class="td-left">身份证有效期：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<input type="text" data-validation="notnull" data-errMsg="身份证有效期起始值不能为空" name="idTermStart" id="idTermStart" value="${merchantDetailInfo.idTermStart }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> ——
                                    <input type="text" data-validation="notnull" data-errMsg="身份证有效期结束值不能为空" name="idTermEnd" id="idTermEnd" value="${merchantDetailInfo.idTermEnd }" onfocus="WdatePicker({skin:'whyGreen'})"  style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                    <input type="button" onclick="idTermForever()" value="长期" />
								</td>
							</tr>
							<tr>
								<td class="td-left">联系人姓名：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" data-validation="notnull" data-errMsg="联系人姓名不能为空" id="attentionName" name="attentionName" value="${merchantDetailInfo.attentionName}" placeholder="请输入联系人姓名" maxlength="50" style="width:90%">
									
									<label class="label-tips" id="attentionNameLab"></label>
								</td>
								<td class="td-left">联系人手机号码：<span style="color:red;">（必填)</span></td>
								<td class="td-right">
									<input type="text" data-validation="notnull" data-errMsg="联系人手机号码不能为空" name="attentionMobile" id="attentionMobile" value="${merchantDetailInfo.attentionMobile}" placeholder="请输入联系人手机号码" style="width:90%">
									
									<label class="label-tips" id="attentionMobileLab"></label>
								</td>
							</tr>
							
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" data-validation="notnull" data-errMsg="结算账户名称不能为空" id="accountNm" name="accountNm" maxlength="100" placeholder="请输入结算账户名称"  value="${merchantDetailInfo.accountNm}" style="width:90%">
								</td>
								<td class="td-left">结算账号：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="text" data-validation="notnull" data-errMsg="结算账号不能为空" id="accountNo" name="accountNo" maxlength="100" placeholder="请输入银行卡号"  value="${merchantDetailInfo.accountNo}" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">结算方式：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="clearMode" id="clearMode" data-validation="notnull" data-errMsg="结算方式不能为空" style="width:90%;"  >
										<option value="1" <c:if test="${merchantDetailInfo.clearMode == '1'}">selected</c:if> >--结算到银行卡--</option>
										<option value="0" <c:if test="${merchantDetailInfo.clearMode == '0'}">selected</c:if> >--自主提现--</option>
									</select>
								</td>
								<td class="td-left">卡折类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<select name="accttp" id="accttp" data-validation="notnull" data-errMsg="卡折类型不能为空" style="width:90%;"  >
										<option value="00" <c:if test="${merchantDetailInfo.accttp == '00'}">selected</c:if> >--借记卡--</option>
										<option value="01" <c:if test="${merchantDetailInfo.accttp == '01'}">selected</c:if> >--存折--</option>
									</select>
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="hidden" id="allinPayBankProvince" name="allinPayBankProvince" value="${merchantDetailInfo.bankProvince}" >
									<select name="bankProvince" id="bankProvince" data-validation="notnull" data-errMsg="开户省份不能为空" class="width-90" >
	                                    <option value="">--请选择省--</option>
	                                    <c:if test="${not empty allinPayAreaInfoList }">
	                                        <c:forEach items="${allinPayAreaInfoList }" var="province">
	                                            <option id="${province.provinceId}"
	                                                    value="${province.provinceId}">
	                                                ${province.provinceName}
	                                            </option>
	                                        </c:forEach>
	                                    </c:if>
	                                </select>
	                                <label id="bankProvinceLabel" class="label-tips"></label>
								</td>
								<td class="td-left">开户城市：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="hidden" id="allinPayBankCity" name="allinPayBankCity" value="${merchantDetailInfo.bankCity}" >
									<select name="bankCity" id="bankCity" data-validation="notnull" data-errMsg="开户城市不能为空" class="width-90" >
	                                    <option value="">--请选择市--</option>
	                                </select>
	                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：<span style="color:red;">(必填)</span></td>
								<td class="td-right"> 
									<input type="hidden" id="allinPayBranchBank" name="allinPayBranchBank" value="${merchantDetailInfo.branchBank}" >
									<select name="branchBank" id="branchBank" data-validation="notnull" data-errMsg="开户银行不能为空" style="width-90;" >
										<option value="">--请选择--</option>
										<c:if test="${not empty bankList }">
	                                        <c:forEach items="${bankList }" var="bank">
	                                            <option id="${bank.bankId}"
	                                                    value="${bank.bankId}">
	                                                ${bank.bankName}
	                                            </option>
	                                        </c:forEach>
	                                    </c:if>
									</select>
								</td>
	                            <td class="td-left">开户支行<span style="color:red;">(必填)</span></td>
	                            <td class="td-right"> 
	                            	<input type="hidden" id="allinPayInterBankCode" name="allinPayInterBankCode" value="${merchantDetailInfo.interBankCode}" >
									<select name="interBankCode" id="interBankCode" data-validation="notnull" data-errMsg="开户支行不能为空" class="width-90" >
	                                    <option value="">--请选择支行--</option>
	                                </select>
	                               	<label id="interBankCodeLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：<span style="color:red;">(必填)</span></td>
								<td class="td-right">
									<select name="perEntFlag" id="perEntFlag" data-validation="notnull" data-errMsg="结算类型不能为空" style="width-90" >
										<option value="">--请选择--</option>
										<option value="1" <c:if test="${merchantDetailInfo.perEntFlag == '1'}">selected</c:if> >--对公--</option>
										<option value="0" <c:if test="${merchantDetailInfo.perEntFlag == '0'}">selected</c:if> >--对私--</option>
									</select> 
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">产品信息</td>
							</tr>
							<tr>
                                <td name = "P0003">网上收银-微信支付</td>
							    <td >
							        <input type="text" id="wxOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="wxOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0003">网上收银-QQ手机支付</td>
							    <td >
							        <input type="text" id="qqOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="qqOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0003">网上收银-支付宝支付</td>
							    <td >
							        <input type="text" id="zfbOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="zfbOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0003">网上收银-银联扫码支付</td>
							    <td >
							        <input type="text" id="ylOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="ylOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0003">网上收银-快捷支付</td>
							    <td >
							        <input type="text" id="qpOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="qpOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0003">网上收银-网关支付</td>
							    <td >
							        <input type="text" id="gwOnline"  placeholder="千分比费率"  value="">
							        <input type="text" id="gwOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0001">收银宝-扫码预消费</td>
							    <td >
							        <input type="text" id="smCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="smCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0001">收银宝-微信支付</td>
							    <td >
							        <input type="text" id="wxCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="wxCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0001">收银宝-支付宝支付</td>
							    <td >
							        <input type="text" id="zfbCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="zfbCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0001">收银宝-通联钱包</td>
							    <td >
							        <input type="text" id="tlCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="tlCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0001">收银宝-Qq钱包支付</td>
							    <td >
							        <input type="text" id="qqCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="qqCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0001">收银宝-银联扫码支付</td>
							    <td >
							        <input type="text" id="ylCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="ylCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							<tr>
                                <td name = "P0001">收银宝-消费</td>
							    <td >
							        <input type="text" id="xfCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="xfCashier1"  placeholder="信用卡费率"  value="">
							    </td>
							    <td name = "P0001">收银宝-预授权</td>
							    <td >
							        <input type="text" id="ysqCashier"  placeholder="千分比费率"  value="">
							        <input type="text" id="qqOnline1"  placeholder="信用卡费率"  value="">
							    </td>
							</tr>
							
						</tbody>
						</table>
					    </form>
					    </div>
					    <div style="margin:50px 0 0 0;text-align:center">
					    	<button type="button"  class="btn btn-primary" id='submitData'>提交报备</button> 
					    	<button type="button"  class="btn btn-default" onclick="exit()">关闭</button> 
					    </div>
					</section>
					</div>
				</div>
			</div><!-- /.modal -->
			<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
		</div><!-- /.main-content -->
		<!-- 设置 -->
		<%@ include file="/include/setting.jsp"%>
	</div><!-- /.main-container-inner -->

	<!-- 向上置顶 -->
	<%@ include file="/include/up.jsp"%>
</div><!-- /.main-container -->
</div>

<!-- 图片预览 -->
<div class="modal fade" id="previewImageModal"  aria-hidden="true">
	<div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
    	<div id="showImageDiv" style="width:100%;height:100%;">
        	<img id="showImage" style="width:100%;height:100%;">
        </div>
     </div>
</div>   
<script type="text/javascript">
//表单验证组件
var checkFun = {
      		notnull : function(value){
      			if(null == value || "" == value) {
      				return false;
      			} else {
      				return true;
      			}
      		}
      	};
	
		//表单验证
		function formValidation(formId){
			var flag = false;
			var form = document.getElementById(formId);
			var attr = null;
			$.each(form, function(i, e) {
				attr = $(e).attr("data-validation");
				if(attr){
					if(checkFun[attr](e.value)){
						flag = true;
					} else {
						flag = false;
						alert($(e).attr("data-errMsg"));
						return false;
					}
				}
			})
			return flag;
		}
		
		
		$(function(){
			//注册省市
			var allinPayMerchantProvince = $("#allinPayMerchantProvince").val();
			var allinPayMerchantCity = $("#allinPayMerchantCity").val();
			$("#merchantProvince").val(allinPayMerchantProvince).trigger("change", allinPayMerchantCity);
			//结算信息
			var allinPayBankProvince = $("#allinPayBankProvince").val();
			var allinPayBankCity = $("#allinPayBankCity").val();
			$("#bankProvince").val(allinPayBankProvince).trigger("change", allinPayBankCity);
			var allinPayBranchBank = $("#allinPayBranchBank").val();
			var allinPayInterBankCode = $("#allinPayInterBankCode").val();
			$("#branchBank").val(allinPayBranchBank).trigger("change", allinPayInterBankCode);
		})
		

	   	/***获取银行所在地区***/
      	$("#bankProvince").change(function (event, value){
      		var provinceId = $("#bankProvince").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.ajax({
      			url : window.Constants.ContextPath +"/common/info/getCityInfo",
      			async : false,
      			type : "POST",
      			data : {
        			"provinceId":provinceId,
    				"channelCode":channelCode
        		},
        		dataType : "json",
        		success : function(data){
        			if(data.result=="SUCCESS"){
        				var cityList = data.cityList;
        				$("#bankCity").html("");
               			for ( var area in cityList) {
               				$("#bankCity").append(
               						"<option value='"+ cityList[area].areaCode +"'>"
               								+ cityList[area].areaName + "</option>"); 
               			}
               			//是否赋值
               			if(null != value && '' != value && undefined != value){
    		   				$("#bankCity").val(value);
    		   			}
        			}else{
        				alert("城市不能为空");
        			}
        		}
      		})
      	})
    	/***根据银行和地区获取支行***/
      	$("#branchBank").change(function(event, value){
      		var city = $("#bankCity").val().trim();
      		var branchBank = $("#branchBank").val();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/bankCnapsInfo",
    		{
      			"cityCode":city,
    			"bankCode": branchBank,
    			"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var branchBankList = data.branchBankList;
    				$("#interBankCode").html("");
           			for ( var branchBank in branchBankList) {
           				$("#interBankCode").append(
           						"<option value='"+ branchBankList[branchBank].branchBankCode +"'>"
           								+ branchBankList[branchBank].bankName + "</option>"); 
           			}
           			//是否赋值
           			if(null != value && '' != value && undefined != value){
		   				$("#interBankCode").val(value);
		   			}
    			}
    			else{
    				//alert("银行和开户城市不能为空");
    			}
    		},'json'
    		);	
      	})
      
      	/***获取注册所在城市***/
      	$("#merchantProvince").change(function(event, value) {
      		var provinceId = $("#merchantProvince").val().trim();
      		var channelCode =$("#channelCode").val();
      		$.post(window.Constants.ContextPath +"/common/info/getCityInfo",
    		{
				"provinceId":provinceId,
				"channelCode":channelCode
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#merchantCity").html("");
           			for ( var area in cityList) {
           				$("#merchantCity").append(
           						"<option value='"+ cityList[area].areaCode +"'>"
           								+ cityList[area].areaName + "</option>"); 
           			}
           		//是否赋值
		   			if(null != value && '' != value && undefined != value){
		   				$("#merchantCity").val(value);
		   			}
    			}else{
    				alert("省份不能为空");
    			}
    		},'json'
    		);	
        })
      	

		function businessForever(){
			$("input[name='businessTerm']").val("2099-12-31");
			$("#businessTerm").attr("value","2099-12-31");
		}
		function idTermForever(){
			$("input[name='idTermEnd']").val("2099-12-31");
			$("#idTermEnd").attr("value","2099-12-31");
		}
	   	function exit() {
	   		if (confirm("您确定要关闭吗？")) {
	   			window.opener=null;
	   		
	   			window.open("","_self");
	   		
	   			window.close();
	   		}
	   	};
	   	
	   	function getMecType(){
      		var mecTypeFlag = $("#mecTypeFlag").val()
      		if("4" == mecTypeFlag){
      			//个人
      			$("#businessPhotoType1").attr("style","display:none");
      			$("#businessPhotoType2").attr("style","display:none");
      			$("#businessLicense").removeAttr("data-validation").removeAttr("data-errMsg");
      			$("#cprRegNmCn").removeAttr("data-validation").removeAttr("data-errMsg");
      			$("#businessEffectiveTerm").removeAttr("data-validation").removeAttr("data-errMsg");
      			$("#businessTerm").removeAttr("data-validation").removeAttr("data-errMsg");
      		}else{
      			$("#businessPhotoType1").attr("style","display:");
      			$("#businessPhotoType2").attr("style","display:");
      			$("#businessLicense").attr("data-validation", "notnull").attr("data-errMsg", "营业执照编号不能为空");
      			$("#cprRegNmCn").attr("data-validation", "notnull").attr("data-errMsg", "营业执照名称不能为空");
      			$("#businessEffectiveTerm").attr("data-validation", "notnull").attr("data-errMsg", "营业执照有效期起始值不能为空");
      			$("#businessTerm").attr("data-validation", "notnull").attr("data-errMsg", "营业执照有效期结束值不能为空");
      		}
      	}
    	$("#submitData").click(function(){
    		
    		if(!formValidation('merchantForm')){
    			return false;
    		}
    		
        	
   			//渠道
   			var channelNo = $("#channelCode").val();
   			//mchId
   			var mchId = $("#mchId").val();
			//商户号
   			var merchantCode = $("#merchantCode").val();
			//客户名称
   			var custName = $("#custName").val();
			//客户简称
			var shortName = $("#shortName").val();
			//商户类型
   			var mecTypeFlag = $("#mecTypeFlag").val();
			//客服号码
			var contactPhone = $("#contactPhone").val();
			//商户地址省
   			var merchantProvince = $("#merchantProvince").val();
   			//商户地址市
   			var merchantCity = $("#merchantCity").val();
   			//商户详细地址
   			var cprRegAddr = $("#cprRegAddr").val();
   			//营业执照名称
   			var cprRegNmCn = $("#cprRegNmCn").val(); 
   			//营业执照有效起始日期
   			var businessEffectiveTerm = $("#businessEffectiveTerm").val(); 
   			//营业执照有效截止日期
   			var businessTerm = $("#businessTerm").val(); 
   			//营业执照号 
   			var businessLicense = $("#businessLicense").val(); 
			//拓展人
   			var expanduser = $("#expanduser").val(); 
   			//线上线下业务场景
			var offlag = $("#offlag").val();
			//合同有效日期
			var contractDate = $("#contractDate").val();
			//法人姓名
   			var representativeName = $("#representativeName").val();
   			//法人证件类型
   			var representativeCertType = $("#representativeCertType").val();
   			//法人证件号
   			var representativeCertNo = $("#representativeCertNo").val();
   			//身份证有效起始期
   			var idTermStart = $("#idTermStart").val();
   			//身份证有效截止期
   			var idTermEnd = $("#idTermEnd").val();
   			//联系人名称
   			var attentionName = $("#attentionName").val();
   			//联系人电话
   			var attentionMobile = $("#attentionMobile").val();
   			//结算账号名称
   			var accountNm = $("#accountNm").val();
   			//结算账号
   			var accountNo = $("#accountNo").val();
   			//结算方式
   			var clearMode = $("#clearMode").val();
   			//卡折类型
   			var accttp = $("#accttp").val();
   			//银行开户省
   			var bankProvince = $("#bankProvince").val();
   			//银行开户市
   			var bankCity = $("#bankCity").val();
   			//开户银行
   			var branchBank = $("#branchBank").val();
   			//开户支行号
   			var interBankCode = $("#interBankCode").val();
   			//开户支行名称
   			var interBankName = $("#interBankName").val();
   			//开户行联行号
   			var bankCode = $("#bankCode").val();
   			//对公对私标识
   			var perEntFlag = $("#perEntFlag").val();
   			//交易类型
   			var prodInfoList = [];
   			//网上收银-微信支付
   			rateType('P0003','VSP501','wxOnline');
   	    	//网上收银-QQ手机支付
   	    	rateType('P0003','VSP505','qqOnline');
			//网上收银-支付宝支付
			rateType('P0003','VSP511','zfbOnline');
			//网上收银-银联扫码支付
			rateType('P0003','VSP551','ylOnline');
			//网上收银-快捷支付
			rateType('P0003','VSP301','qpOnline');
			//网上收银-网关支付
			rateType('P0003','VSP531','gwOnline');
			//收银宝-扫码预消费
			rateType('P0001','VSP011','smCashier');
			//收银宝-微信支付
			rateType('P0001','VSP501','wxCashier');
			//收银宝-支付宝支付
			rateType('P0001','VSP511','zfbCashier');
			//收银宝-通联钱包
			rateType('P0001','VSP521','tlCashier');
			//收银宝-Qq钱包支付
			rateType('P0001','VSP505','qqCashier');
			//收银宝-银联扫码支付
			rateType('P0001','VSP551','ylCashier');
			//收银宝-消费
			rateType('P0001','VSP001','xfCashier');
			//收银宝-预授权
			rateType('P0001','VSP004','ysqCashier');
			
   	    	function rateType(pid,mtrxcode,rateType){
   		   		if("" != $('#' + rateType).val()){
   	   	    		var prodInfo={
   	   	    				pid 	   : pid,
   	   	    				mtrxcode   : mtrxcode,
   	   	    				feerate    : $('#' + rateType).val(),
   	   	    				creditrate : $('#' + rateType + '1').val()
   	   	    			}
   	   	    		prodInfoList.push(prodInfo);
   	   	    		
   	   	    	}
   		   	}
   	    	
   	    	//至少选择一个产品
   	    	if(prodInfoList.length < 1){
   	    		alert("请至少选择开通一个产品信息");
   	    		return false;
   	    	}
   	    	
   	    	
	                	$.ajax({
	  	   					type : "POST",
	  	   					url : window.Constants.ContextPath +"/merchant/merchantReported/allinPayEditReportedSubmit",
	  	   					data :{
	  	   						"channelNo"    			 : channelNo,
	  	   						"mchId"                  : mchId,
	  	   						"merchantCode"           : merchantCode,
	  	   					 	"custName"               : custName,
	  	   					 	"shortName"              : shortName,
	  	   						"mecTypeFlag"            : mecTypeFlag,
	  	   						"contactPhone"           : contactPhone,
		  	   					"merchantProvince"       : merchantProvince,
	  	   						"merchantCity"           : merchantCity,
	  	   						"cprRegAddr"             : cprRegAddr,
	  	   						"cprRegNmCn"             : cprRegNmCn,
	  	   						"businessEffectiveTerm"  : businessEffectiveTerm,
	  	   						"businessTerm"           : businessTerm,
	  	   						"businessLicense"        : businessLicense,
	  	   						"expanduser"             : expanduser,
	  	   						"offlag"                 : offlag,
	  	   						"contractDate"           : contractDate,
	  	   						"representativeName"     : representativeName,
	  	   						"representativeCertNo"   : representativeCertNo,
	  	   						"representativeCertType" : representativeCertType,
	  	   						"idTermStart"            : idTermStart,
	  	   						"idTermEnd"              : idTermEnd,
	  	   						"attentionName"          : attentionName,
	  	  						"attentionMobile"        : attentionMobile,
	  							"accountNm"              : accountNm,
	  							"accountNo"              : accountNo,
	  							"clearMode"              : clearMode,
	  							"accttp"                 : accttp,
	  							"bankProvince"           : bankProvince,
	  							"bankCity"               : bankCity,
	  							"branchBank"             : branchBank,
	  							"interBankCode"          : interBankCode,
	  							"interBankName"          : interBankName,
	  							"bankCode"               : bankCode,
	  							"perEntFlag"             : perEntFlag,
	  							"prodInfoList"           : prodInfoList
	  							
	  	   						
	  	   					},
	  	   					dataType : "json",
	  	   					success : function(data) {
	  	   						if(data.result=='SUCCESS'){							
	  	   							$.gyzbadmin.alertSuccess("提交报备成功！",function(){
										},function(){
											this.location.reload();
										}
									);
								}else{
									alert(data.message);
									this.location.reload();
								}
	  	   					}
	  	   				});
    	});

    	
</script>
</body>
</html>