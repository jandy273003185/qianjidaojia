<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.qifenqian.bms.accounting.utils.CommonConfig"%>
<%
	CommonConfig commonConfig = CommonConfig.getInstance();
	String _jsVersion = commonConfig.getValue("VERSION");
	String _cssVersion = commonConfig.getValue("VERSION");
%>
<c:set var="_jsVersion" value="<%=_jsVersion%>" />
<c:set var="_cssVersion" value="<%=_cssVersion%>" />
<%-- <%@ include file="/include/template.jsp"%> --%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商户渠道编辑</title>
<script type="text/javascript" src="/assets/js/jquery-2.0.3.min.js?v=${_jsVersion}"></script>
<style>
* {
 padding: 0; margin: 0; list-style: none; font-size: 12px
}

.container {
 width: 1000px; margin: 0 auto; background: #f1f1f1;
}

ul.input-box, ul.add-box {
 float: left; width: 100%; border: 1px solid #ddd; background: #f1f1f1; padding: 30px 20px 0 20px; margin-top: 20px;
}

ul.input-box li, ul.add-box li {
 float: left; margin: 0 0 20px 0; width: 25%
}

ul.input-box li label, ul.add-box label {
 float: left; width: 45%; line-height: 25px; text-align: right; color: #555;
}

ul.input-box li input, ul.add-box input, ul.input-box li select, ul.add-box select {
 float: left; height: 24px; width: 54%; border: 1px solid #ddd
}

ul.input-box li:last-child {
 width: 100%; text-align: center
}

ul.input-box li button {
 width: 200px; height: 28px;
}

ul.add-box {
 border: 1px dashed #ddd; background: #f9f9f9;
}

ul.add-box li button {
 width: 24px; height: 24px; border-radius: 24px; border: 1px solid #ddd; background: none; color: #ddd; cursor: pointer; font-size: 14px
}

ul.add-box li button:hover {
 border: 1px solid #666; color: #666;
}

ul.add-box li:first-child {
 float: right; text-align: right; width: 100%; margin-top: -15px
}

.btn-submit {
 float: left; width: 100%; margin: 100px auto; text-align: center
}

.btn-submit button {
 width: 300px; height: 36px;
}

.image-item {
 float: left; width: 82px; height: 82px; text-align: center; margin: 20px 20px 0 0
}

.image-item .image-item-add {
 border: dashed 1px #ddd; padding: 30px 0 14px 0; font-size: 12px; line-height: 10px;
}

.image-item .image-item-add:hover {
 border: dashed 1px #999;
}

.image-item .image-item-add span {
 font-size: 60px; font-family: inherit; font-weight: lighter
}

.image-item img {
 width: 80px; height: 80px; border: 1px solid #ddd
}

.image-item a {
 color: #999; text-decoration: none
}

.image-item a:hover {
 color: #666
}
</style>
<script type="text/javascript">
	
	function initItem() {
		var item = "<ul class='add-box'>"
			+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
			+ "<li>"
			+ "		<label>通道：</label>"
			+ "		<select class='channel'>"
			+ "			<option value='WEIXIN' >微信</option>"
			+"				<option value='BESTPAY'>翼支付</option>"
			+ "			<option value='ALIPAY' selected='selected'>支付宝</option>"		
			+ "		</select>"
			+ "</li>"
			+ "<li>"
			+ "		<label>产品：</label>"
			+ "		<select class='prod'>"
			+ "			<option value='SM' selected='selected' >扫码</option>"
			+ "			<option value='SK'>刷卡</option>"
			+ "			<option value='GZH'>公众号(线下)</option>"
			+ "			<option value='OL_PUB'>公众号(线上)</option>"
			+ "			<option value='H5'>H5</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li><label>AppId：</label><input name='appId' type='text' value=''  class='wxAppId'></li>"
			+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret'></li>"
			+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
			+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
			+ "</ul>";
			
		var item1 = "<ul class='add-box'>"
			+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
			+ "<li>"
			+ "		<label>通道：</label>"
			+ "		<select class='channel'>"
			+ "			<option value='WEIXIN' >微信</option>"
			+"				<option value='BESTPAY'>翼支付</option>"
			+ "			<option value='ALIPAY' selected='selected'>支付宝</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li>"
			+ "		<label>产品：</label>"
			+ "		<select class='prod'>"
			+ "			<option value='SM'>扫码</option>"
			+ "			<option value='SK' selected='selected'>刷卡</option>"
			+ "			<option value='GZH'>公众号(线下)</option>"
			+ "			<option value='OL_PUB'>公众号(线上)</option>"
			+ "			<option value='H5'>H5</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li><label>AppId：</label><input name='appId' type='text' value=''  class='wxAppId'></li>"
			+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret'></li>"
			+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
			+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
			+ "</ul>";
			
			
		var item2 = "<ul class='add-box'>"
			+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
			+ "<li>"
			+ "		<label>通道：</label>"
			+ "		<select class='channel'>"
			+ "			<option value='WEIXIN' selected='selected'>微信</option>"
			+"				<option value='BESTPAY'>翼支付</option>"
			+ "			<option value='ALIPAY' >支付宝</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li>"
			+ "		<label>产品：</label>"
			+ "		<select class='prod'>"
			+ "			<option value='SM' selected='selected'>扫码</option>"
			+ "			<option value='SK'>刷卡</option>"
			+ "			<option value='GZH'>公众号(线下)</option>"
			+ "			<option value='OL_PUB'>公众号(线上)</option>"
			+ "			<option value='H5'>H5</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li><label>AppId：</label><input name='appId' type='text' value=''  class='wxAppId'></li>"
			+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret'></li>"
			+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
			+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
			+ "</ul>";
		
		
		var item3 = "<ul class='add-box'>"
			+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
			+ "<li>"
			+ "		<label>通道：</label>"
			+ "		<select class='channel'>"
			+ "			<option value='WEIXIN' selected='selected' >微信</option>"
			+"				<option value='BESTPAY'>翼支付</option>"
			+ "			<option value='ALIPAY' >支付宝</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li>"
			+ "		<label>产品：</label>"
			+ "		<select class='prod'>"
			+ "			<option value='SM'>扫码</option>"
			+ "			<option value='SK' selected='selected'>刷卡</option>"
			+ "			<option value='GZH'>公众号(线下)</option>"
			+ "			<option value='OL_PUB'>公众号(线上)</option>"
			+ "			<option value='H5'>H5</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li><label>AppId：</label><input name='appId' type='text' value=''  class='wxAppId'></li>"
			+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret'></li>"
			+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
			+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
			+ "</ul>";
			
			
		var item4 = "<ul class='add-box'>"
			+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
			+ "<li>"
			+ "		<label>通道：</label>"
			+ "		<select class='channel'>"
			+ "			<option value='WEIXIN' selected='selected' >微信</option>"
			+"				<option value='BESTPAY'>翼支付</option>"
			+ "			<option value='ALIPAY' >支付宝</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li>"
			+ "		<label>产品：</label>"
			+ "		<select class='prod'>"
			+ "			<option value='SM'>扫码</option>"
			+ "			<option value='SK'>刷卡</option>"
			+ "			<option value='GZH' selected='selected'>公众号(线下)</option>"
			+ "			<option value='OL_PUB'>公众号(线上)</option>"
			+ "			<option value='H5'>H5</option>"
			+ "		</select>"
			+ "</li>"
			+ "<li><label>AppId：</label><input name='appId' type='text' value='wx1fc84beff3d0eeb8'  class='wxAppId'></li>"
			+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret' value='055e6b98ac3b4b6d7b704a6c3e884d64'></li>"
			+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
			+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
			+ "</ul>";
			
		$("#info").after(item4);
		$("#info").after(item3);
		$("#info").after(item2);
		$("#info").after(item1);
		$("#info").after(item);
	}

	function addItem() {
		var item = "<ul class='add-box'>"
				+ "<li><button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button></li>"
				+ "<li>"
				+ "		<label>通道：</label>"
				+ "		<select class='channel'>"
				+ "			<option value='WEIXIN' >微信</option>"
				+"				<option value='BESTPAY'>翼支付</option>"
				+ "			<option value='ALIPAY' selected='selected'>支付宝</option>"
				+"            <option value='UNIONPAY'>云闪付</option>"
				+ "		</select>"
				+ "</li>"
				+ "<li>"
				+ "		<label>产品：</label>"
				+ "		<select class='prod'>"
				+ "			<option value='SM'>扫码</option>"
				+ "			<option value='SK'>刷卡</option>"
				+ "			<option value='GZH'>公众号(线下)</option>"
				+ "			<option value='OL_PUB'>公众号(线上)</option>"
				+ "			<option value='H5'>H5</option>"
				+ "		</select>"
				+ "</li>"
				+ "<li><label>AppId：</label><input name='appId' type='text' value=''  class='wxAppId'></li>"
				+ "<li><label>Appsecret：</label><input name='' type='text'  class='wxAppsecret'></li>"
				+ "<li><label>支付宝子商户号：</label><input name='' type='text'  class='zfbChildNo'></li>"
				+ "<li><label>微信子商户号：</label><input name='' type='text'  class='wxChildNo'></li>"
				+ "</ul>";

		$("#info").after(item);
	}

	function submit() {
		//得到参数
		var merCode = $("#merchantCode").val()
		var channelName = $("#channelname").val()
		var merchantChannelId = $("#merchantChannelId").val()
		var merchantChannelKey = $("#merchantChannelKey").val()
		var details = "";

		$(".add-box").each(
				function(index, item) {
					var ChannelCode = $(this).find(".channel").val();
					var subCode = $(this).find(".prod").val();
					var wxAppId = $(this).find(".wxAppId").val();
					var wxAppsecret = $(this).find(".wxAppsecret").val();
					var zfbChildNo = $(this).find(".zfbChildNo").val();
					var wxChildNo = $(this).find(".wxChildNo").val();
					details = details + '{"channelCode":"' + ChannelCode
							+ '","subCode":"' + subCode + '","wxAppId":"'
							+ wxAppId + '","wxAppsecret":"' + wxAppsecret + '","zfbChildNo":"' + zfbChildNo+ '","wxChildNo":"' + wxChildNo
							+ '"},';
				});

		//去掉detail最后的 ,
		details = details.substring(0, details.length - 1);
		//拼装请求json
		var param = '{"details":[' + details + '],"merchantCode":"' + merCode
				+ '","channelName":"' + channelName + '","merchantChannelId":"'
				+ merchantChannelId + '","merchantChannelKey":"'
				+ merchantChannelKey + '"}';
		
		//得到老的数据;
		var oldCustId = $("#oldCustId").val();
		var oldChannelName = $("#oldChannelname").val();
		var oldMerChannelId = $("#oldMerChannelId").val();		
		var oldChannel;
		
		if ( !(oldCustId == null || oldCustId == undefined || oldCustId == "") &&
				!(oldChannelName == null || oldChannelName == undefined || oldChannelName == "") &&
				!(oldMerChannelId == null || oldMerChannelId == undefined || oldMerChannelId == "")) {
			 oldChannel = '{"custId":"'+oldCustId+'","channelName":"'+oldChannelName+ '","merchantChannelId":"' +oldMerChannelId + '"}';
		}
		
		//验证参数
		if (!checkParam(merCode, '商户编号')) {
			return;
		}
		if (!checkParam(merchantChannelId, '商户渠道编号')) {
			return;
		}
		if (!checkParam(merchantChannelKey, '商户渠道KEY')) {
			return;
		}
		if (!checkParam(details, '商户产品')) {
			return;
		}

		$.ajax({
			type : "post",
			url : "/merchant/channel/save",
			data : {
				"data" : param,
				"oldData":oldChannel
			},
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				if ("true" == data.flag) {
					alert(data.data);
					window.close();
				} else {
					alert("ERROR ：" + data.data);
					return false;
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});

	}

	function checkParam(param, name) {

		if (param == null || param == undefined || param == "") {
			alert(name + '不能为空');
			return false;
		}

		return true;

	}
</script>
</head>
<body>
	<div class="container" id="container">
		<input type="hidden" value="${channel.custId}" id="oldCustId">
		<input type="hidden" value="${channel.channelName}" id="oldChannelname">
		<input type="hidden" value="${channel.merchantChannelId }" id="oldMerChannelId">
		<ul class="input-box" id="info">
			<li>
				<label>商户编号(七分钱)：</label>
				<input id="merchantCode" name="merchantCode" type="text" value="${channel.merchantCode}" <c:if test="${not empty channel}">disabled="disabled" </c:if>>
			</li>
			<li>
				<label>渠道：</label>
				<select name="channelname" id="channelname" <c:if test="${not empty channel}">disabled="disabled" </c:if>>
					<c:forEach items="${channelNames}" var="item">
						<option value="${item.code}" <c:if test="${channel.channelName == item}"> selected="selected"</c:if>>${item.text }</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>商户编号(渠道)：</label>
				<input id="merchantChannelId" name="merchantChannelId" type="text" value="${channel.merchantChannelId }">
			</li>
			<li>
				<label>商户KEY(渠道)：</label>
				<input id="merchantChannelKey" name="merchantChannelKey" type="text" value="${channel.merchantChannelKey }">
			</li>
			<li>
				<button type="button" id="add" onClick='addItem()'>增加选项</button>
			</li>
		</ul>
		<!-- 通道详情 -->
		<c:forEach items="${channel.details}" var="item" varStatus="status">
			<ul class='add-box'>
				<li>
					<button type='button' onClick='$(this).parent().parent().remove();' title='删除'>X</button>
				</li>
				<li>
					<label>通道：</label>
					<select class='channel'>
						<option value='WEIXIN' <c:if test="${item.channelCode=='WEIXIN'}">selected='selected'</c:if>>微信</option>
						<option value='ALIPAY' <c:if test="${item.channelCode=='ALIPAY'}">selected='selected'</c:if>>支付宝</option>
					</select>
				</li>
				<li>
					<label>产品：</label>
					<select class='prod'>
						<option value='SM' <c:if test="${item.subCode=='SM'}">selected='selected'</c:if>>扫码</option>
						<option value='SK' <c:if test="${item.subCode=='SK'}">selected='selected'</c:if>>刷卡</option>
						<option value='GZH' <c:if test="${item.subCode=='GZH'}">selected='selected'</c:if>>公众号(线下)</option>
						<option value='OL_PUB' <c:if test="${item.subCode=='OL_PUB'}">selected='selected'</c:if>>公众号(线上)</option>
						<option value='H5' <c:if test="${item.subCode=='H5'}">selected='selected'</c:if>>H5</option>
					</select>
				</li>
				<li>
					<label>wxAppId：</label>
					<input type='text' class='wxAppId' value="${item.wxAppId}">
				</li>
				<li>
					<label>wxAppsecret：</label>
					<input type='text' class='wxAppsecret' value="${item.wxAppsecret}">
				</li>
				<li>
					<label>wxChildNo：</label>
					<input type='text' class='wxChildNo' value="${item.wxChildNo}">
				</li>
				<li>
					<label>zfbChildNo：</label>
					<input type='text' class='zfbChildNo' value="${item.zfbChildNo}">
				</li>
			</ul>
		</c:forEach>
		<c:if test="${empty channel.details}">
			<script type="text/javascript">
				initItem();
			</script>
		</c:if>
		<div class="btn-submit">
			<button type="button" onclick="submit();">提交</button>
		</div>
	</div>
</body>
</html>