<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath" %>
<%@page import="com.seven.micropay.channel.enums.SumpayMerchantType" %>
<%@page import="com.seven.micropay.channel.enums.BestBankCode" %>
<%@page import="com.seven.micropay.channel.enums.YqbMerchantType" %>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath" %>
<%@ include file="/include/template.jsp" %>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.min.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />"/>
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>支付宝进件</title>
    <meta name="keywords" content="七分钱后台管理系统"/>
    <meta name="description" content="七分钱后台管理"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <style type="text/css">
        table tr td {
            word-wrap: break-word;
            word-break: break-all;
        }
    </style>
</head>
<body>
<!-- 用户信息 -->
<%@ include file="/include/top.jsp" %>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <%-- <!-- 菜单 -->
        <%@ include file="/include/left.jsp"%> --%>

        <div class="main-content">
            <!-- 路径 -->
            <%@ include file="/include/path.jsp" %>

            <!-- 主内容 -->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <input type="hidden" id="channlCode" name="channlCode" value="ALIPAY"/>
                        <input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
                        <input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
                        <input type="hidden" id="mccCodeQ" name="mccCodeQ" value="${merchantDetailInfoAliPay.mccCode }"/>
                        <div id="door_temp"></div>
                        <section class="aui-content">
                            <div class="aui-content-up">
                                <form id="merchantForm"
                                      action='<c:url value="<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.YQBMERCHANTREPORT %>"/>'
                                      method="post">
                                    <table id="merchant_table" class="list-table">
                                        <tbody>
                                        <tr>
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
                                        </tr>

                                        <tr>
                                            <td class="td-left" width="18%">商户编号：<span style="color:red;">(必填)</span>
                                            </td>
                                            <td class="td-right" width="32%">
                                                <input type="text"  id="merchantCode" name="merchantCode" readonly
                                                       maxlength="" value="${merchantDetailInfoAliPay.merchantCode }" style="width:90%">
                                                <label class="label-tips" id="merchantCodeLab"></label>
                                            </td>
                                            <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span>
                                            </td>
                                            <td class="td-right" width="32%">
                                                <input type="text" id="custName" name="custName" readonly  placeholder="请输入商户名称"
                                                       maxlength="" value="${merchantDetailInfoAliPay.custName }" style="width:90%">
                                                <label class="label-tips" id="custNameLab"></label>
                                            </td>
                                        </tr>
                                        <tr id="next_id">
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
                                        </tr>
                                        <tr>
                                            <td class="td-left">商户支付宝账号：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="account" name="account" readonly ` maxlength="100" value="${merchantDetailInfoAliPay.account }" 
                                                       placeholder="请输入支付宝账号" style="width:90%">
                                                <label id="accountLab" class="label-tips"></label>
                                            </td>
                                        </tr>
                                        <tr id="next_id">
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系人信息
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td-left">联系人名称：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="contactName" name="contactName" readonly maxlength="100"
                                                       placeholder="请输入联系人名字" value="${merchantDetailInfoAliPay.contactName }"
                                                       style="width:90%">
                                                <label id="contactNameLab" class="label-tips"></label>
                                            </td>
                                            <td class="td-left">联系人手机号码：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="contactMobile" readonly name="contactMobile"
                                                       maxlength="100" placeholder="请输入联系人手机号码"
                                                       value="${merchantDetailInfoAliPay.contactMobile }" style="width:90%">
                                                <label id="contactMobileLab" class="label-tips"></label>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td class="td-left">联系人邮箱：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="contactEmail" readonly name="contactEmail" maxlength="100"
                                                       placeholder="请输入联系人邮箱" value="${merchantDetailInfoAliPay.contactEmail }"
                                                       style="width:90%">
                                                <label id="contactEmailLab" class="label-tips"></label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
                                        </tr>

                                       
                                        <tr id="industryType">
                                            <td class="td-left">商户行业信息:<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="4">
                                               <%--  <select name="levelOne" id="levelOne" class="width-80"
                                                        onchange="getLevel('levelTwo',this.value)">
                                                    <option value="">--请选择商户经营类目--</option>
                                                    <c:if test="${not empty industryList }">
                                                        <c:forEach items="${industryList }" var="industry">
                                                            <option id="${industry.levelOne}" 
                                                                    value="${industry.levelOne}"  <c:if test="${industry.levelOne == merchantDetailInfoAliPay.mccCode}">selected</c:if>>${industry.levelOne}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select> --%>

                                                <label id="levelOneLabel" class="label-tips"></label>
                                             <!--    <select name="levelTwo"  id="levelTwo" class="width-80"
                                                        onchange="getLevel('levelThree',this.value)">
                                                    <option value="">--请选择二级经营类目--</option>
                                                </select> -->
                                                <label id="levelTwoLabel" class="label-tips"></label>
                                                <!-- <input type="hidden" id="specialCertificate" name="specialCertificate" /> -->
                                                <select name="levelThree" id="levelThree" class="width-80" disabled="disabled"
                                                        onchange="javascript:document.getElementById('specialCertificate').innerHTML = this.options[this.options.selectedIndex].getAttribute('data-special');">
                                                    
                                                </select>
                                                <label id="levelThreeLabel" class="label-tips"></label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td-left">营业执照：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       placeholder="请输入营业执照" maxlength=""
                                                       value="${merchantDetailInfoAliPay.businessLicense }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                            <td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" readonly name="businessEffectiveTerm"
                                                       id="businessEffectiveTerm" value="${custInfo.businessTermStart }"
                                                       style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                                ——
                                                <input type="text" readonly  name="businessTerm" id="businessTerm"
                                                       <c:if test="${custInfo.businessTermEnd == '长期' }">value="2099-12-31"</c:if>
                                                       <c:if test="${custInfo.businessTermEnd != '长期' }">value="${custInfo.businessTermEnd}"</c:if>
                                                     
                                                       style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                            </td>
                                        </tr>
                                        <tr id="businessPhotoType" style="display:">
                                            <td class="td-left">营业执照照片：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success businessPhotoClick"
                                                   data-target="#previewImageModal">
                                                    <label id="businessPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="businessPhotoImageDiv" value="${merchantDetailInfoAliPay.businessPhotoPath }" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;"/>
                                                    </label>
                                                </a>
                                                
                                            </td>
                                        </tr>
                                        <tr id="businessAuthPhotoType" style="display:">
                                            <td class="td-left">营业执照授权函照片：<span>(选填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success "
                                                   data-target="#previewImageModal">
                                                    <label id="businessAuthPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="businessAuthPhotoImageDiv" value="${merchantDetailInfoAliPay.businessaAuthPhotoPath }" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;"/>
                                                    </label>
                                                </a>
                                             
                                            </td>
                                        </tr>
                                        <tr id="qualificationType" style="display:">
                                            <td class="td-left">
                                                特殊行业资质照：<span>(特殊可选)</span>
                                                <p id="specialCertificate" style="font-size: 10px;color: red"></p>
                                            </td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success qualificationClick"
                                                   data-target="#previewImageModal">
                                                    <label id="qualificationDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="qualificationImageDiv" value="${merchantDetailInfoAliPay.qualificationPath }"  onclick="bigImg(this);"
                                                             style="width:100%;height:100%;"/>
                                                    </label>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr id="doorPhotoType" style="display:">
                                            <td class="td-left">门头照照片：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success doorPhotoClick"
                                                   data-target="#previewImageModal">
                                                    <label id="doorPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="doorPhotoImageDiv" value="${merchantDetailInfoAliPay.doorPhotoPath }"  onclick="bigImg(this);"
                                                             style="width:100%;height:100%;"/>
                                                    </label>
                                                </a>
                                               
                                            </td>
                                        </tr>
                                        <tr id="shopInteriorType" style="display:">
                                            <td class="td-left">店内照照片：<span>(可选)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success shopInteriorClick"
                                                   data-target="#previewImageModal">
                                                    <label id="shopInteriorDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="shopInteriorImageDiv" value="${merchantDetailInfoAliPay.shopInteriorPath }"  onclick="bigImg(this);"
                                                             style="width:100%;height:100%;"/>
                                                    </label>
                                                </a>
                                               
                                            </td>
                                        </tr>
                                        <tr>
                                        	 <td class="td-left">授权商户的appid：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbAuthAppId }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                             <td class="td-left">支付宝授权商户的user_id：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbUserId }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                            
                                        </tr>
                                          <tr>
                                        	 <td class="td-left">应用授权令牌：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbAppAuthToken }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                             <td class="td-left">刷新令牌：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbAppRefreshToken }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                            
                                        </tr>
                                        
                                         <tr>
                                        	 <td class="td-left">应用授权令牌的有效时间：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbExpiresIn }" style="width:90%">
                                            </td>
                                             <td class="td-left">刷新令牌的有效时间：</td>
                                            <td class="td-right">
                                                <input type="text" readonly id="businessLicense" name="businessLicense"
                                                       maxlength=""
                                                       value="${merchantDetailInfoAliPay.zfbExpiresIn }" style="width:90%">
                                            </td>
                                            
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div style="margin:50px 0 0 0;text-align:center">
                                <button type="button" class="btn btn-default" onclick="exit()">关闭</button>
                            </div>
                        </section>

                    </div>
                </div>
            </div><!-- /.modal -->
            <!-- 底部-->
            <%@ include file="/include/bottom.jsp" %>
        </div><!-- /.main-content -->
        <!-- 设置 -->
        <%@ include file="/include/setting.jsp" %>
    </div><!-- /.main-container-inner -->

    <!-- 向上置顶 -->
    <%@ include file="/include/up.jsp" %>
</div><!-- /.main-container -->
</div>

<!-- 图片预览 -->
<div class="modal fade" id="previewImageModal" aria-hidden="true">
    <div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
        <div id="showImageDiv" style="width:100%;height:100%;">
            <img id="showImage" style="width:100%;height:100%;">
        </div>
    </div>
</div>

<script type="text/javascript">


    //营业执照长期有效
    function businessForever() {
        /* $("input[name='businessTerm']").val("2099-12-31").focus(); */
        $("#businessTerm").val("2099-12-31");
    }

    //关闭窗口
    function exit() {
        if (confirm("您确定要关闭吗？")) {
            window.opener = null;

            window.open("", "_self");

            window.close();
        }
    };


    /** 点击预览大图 **/
    function bigImg(obj) {
        var realWidth;
        var realHeight;
        $('#showImageDiv #showImage').attr("src", obj.src).load(function () {
            realWidth = this.width;
            realHeight = this.height;
            var scale = realWidth / realHeight;
            if (realWidth > 800) {
                realWidth = 800;
                realHeight = realWidth / scale;
            }

            $("#imageDiv").css("width", realWidth + "px").css("height", realHeight + "px");
        });
    }


    /***获取行业信息***/
 
        //var levelOne = $("#levelOne").val().trim();
        $.post(window.Constants.ContextPath + "/common/info/getIndustrieInfo",
            {
                "channelCode": $("#channlCode").val(),
                "parentLevel": "levelFour",
                "parentText":  $("#mccCodeQ").val()
            },
            function (res) {
                if (res.result == "SUCCESS") {
                    var aliPayIndustryList = res.data;
                        for (var index in aliPayIndustryList) {
                            $("#levelThree").append(
                                "<option value='" + aliPayIndustryList[index].levelCode + "' data-special='" + aliPayIndustryList[index].specialCertificate + "'>"
                                + aliPayIndustryList[index].levelThree + "</option>");
                        }
                } 
            }, 'json'
        );



  
</script>
</body>
</html>