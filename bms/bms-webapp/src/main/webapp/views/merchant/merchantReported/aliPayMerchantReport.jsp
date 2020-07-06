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
                        <input type="hidden" id="status" name="status" value="${status}"/>
                        <input type="hidden" id="channlCode" name="channlCode" value="ALIPAY"/>
                        <input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
                        <input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
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
                                                <input type="text" id="merchantCode" name="merchantCode" readonly
                                                       maxlength="" value="${custInfo.merchantCode }" style="width:90%">
                                                <label class="label-tips" id="merchantCodeLab"></label>
                                            </td>
                                            <td class="td-left" width="18%">商户名称：<span style="color:red;">(必填)</span>
                                            </td>
                                            <td class="td-right" width="32%">
                                                <input type="text" id="custName" name="custName" placeholder="请输入商户名称"
                                                       maxlength="" value="${custInfo.custName }" style="width:90%">
                                                <label class="label-tips" id="custNameLab"></label>
                                            </td>
                                        </tr>
                                        <tr id="next_id">
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
                                        </tr>
                                        <tr>
                                            <td class="td-left">商户支付宝账号：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="account" name="account" maxlength="100"
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
                                                <input type="text" id="contactName" name="contactName" maxlength="100"
                                                       placeholder="请输入联系人名字" value="${custInfo.contactName }"
                                                       style="width:90%">
                                                <label id="contactNameLab" class="label-tips"></label>
                                            </td>
                                            <td class="td-left">联系人手机号码：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="contactMobile" name="contactMobile"
                                                       maxlength="100" placeholder="请输入联系人手机号码"
                                                       value="${custInfo.contactMobile }" style="width:90%">
                                                <label id="contactMobileLab" class="label-tips"></label>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td class="td-left">联系人邮箱：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="contactEmail" name="contactEmail" maxlength="100"
                                                       placeholder="请输入联系人邮箱" value="${custInfo.merchantEmail }"
                                                       style="width:90%">
                                                <label id="contactEmailLab" class="label-tips"></label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
                                        </tr>

                                        <%-- <tr id="merchantType1" style = "display:">
                                            <td class="td-left">商户类型：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                               <select name="merchantType" id="merchantType" style="width:250px;" onchange="getType();" >
                                                    <option value="">--请选择--</option>
                                                    <c:forEach items="<%=YqbMerchantType.MerchantType.values()%>" var="status">
                                                        <option value="${status.value}" <c:if test="${status == queryBean.merchantType}">selected</c:if>>
                                                            ${status.text}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <label class="label-tips" id="merchantTypeLab"></label>
                                            </td>
                                        </tr> --%>
                                        <tr id="industryType">
                                            <td class="td-left">商户行业信息:<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="4">
                                                <select name="levelOne" id="levelOne" class="width-80"
                                                        onchange="getLevel('levelTwo',this.value)">
                                                    <option value="">--请选择商户经营类目--</option>
                                                    <c:if test="${not empty industryList }">
                                                        <c:forEach items="${industryList }" var="industry">
                                                            <option id="${industry.levelOne}"
                                                                    value="${industry.levelOne}">${industry.levelOne}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>

                                                <label id="levelOneLabel" class="label-tips"></label>
                                                <select name="levelTwo" id="levelTwo" class="width-80"
                                                        onchange="getLevel('levelThree',this.value)">
                                                    <option value="">--请选择二级经营类目--</option>
                                                </select>
                                                <label id="levelTwoLabel" class="label-tips"></label>
                                                <!-- <input type="hidden" id="specialCertificate" name="specialCertificate" /> -->
                                                <select name="levelThree" id="levelThree" class="width-80"
                                                        onchange="javascript:document.getElementById('specialCertificate').innerHTML = this.options[this.options.selectedIndex].getAttribute('data-special');">
                                                    <option value="">--请选择三级经营类目--</option>
                                                </select>
                                                <label id="levelThreeLabel" class="label-tips"></label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td-left">营业执照：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" id="businessLicense" name="businessLicense"
                                                       placeholder="请输入营业执照" maxlength=""
                                                       value="${custInfo.businessLicense }" style="width:90%">
                                                <label id="businessLicenseLab" class="label-tips"></label>
                                            </td>
                                            <td class="td-left">营业执照有效期：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right">
                                                <input type="text" name="businessEffectiveTerm"
                                                       id="businessEffectiveTerm" value="${custInfo.businessTermStart }"
                                                       onfocus="WdatePicker({skin:'whyGreen'})"
                                                       style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                                ——
                                                <input type="text" name="businessTerm" id="businessTerm"
                                                       <c:if test="${custInfo.businessTermEnd == '长期' }">value="2099-12-31"</c:if>
                                                       <c:if test="${custInfo.businessTermEnd != '长期' }">value="${custInfo.businessTermEnd}"</c:if>
                                                       onfocus="WdatePicker({skin:'whyGreen'})"
                                                       style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
                                                <input type="button" onclick="businessForever()" value="长期"/>
                                            </td>
                                        </tr>
                                        <tr id="businessPhotoType" style="display:">
                                            <td class="td-left">营业执照照片：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success businessPhotoClick"
                                                   data-target="#previewImageModal">
                                                    <label id="businessPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="businessPhotoImageDiv" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;display:none"/>
                                                    </label>
                                                </a>
                                                <div class="updateImageDiv" style="float:left; margin-top:75">
                                                    <input type="hidden" id="businessPhotoPath"
                                                           name="businessPhotoPath"/>
                                                    <input type="hidden" id="businessPhotoImageVal02"/>
                                                    <input type="file" name="businessPhoto" id="businessPhoto"
                                                           onchange="showBusinessPhoto(this)"/>
                                                    <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr id="businessAuthPhotoType" style="display:">
                                            <td class="td-left">营业执照授权函照片：<span>(选填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success "
                                                   data-target="#previewImageModal">
                                                    <label id="businessAuthPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="businessAuthPhotoImageDiv" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;display:none"/>
                                                    </label>
                                                </a>
                                                <div class="updateImageDiv" style="float:left; margin-top:75">
                                                    <input type="hidden" id="businessAuthPhotoPath"
                                                           name="businessAuthPhotoPath"/>
                                                    <input type="file" name="businessAuthPhoto" id="businessAuthPhoto"
                                                           onchange="showBusinessAuthPhoto(this)"/>
                                                    <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                                </div>
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
                                                        <img id="qualificationImageDiv" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;display:none"/>
                                                    </label>
                                                </a>
                                                <div class="updateImageDiv" style="float:left; margin-top:75">
                                                    <input type="hidden" id="qualificationPath"
                                                           name="qualificationPath"/>
                                                    <input type="hidden" id="qualificationImageVal02"/>
                                                    <input type="file" name="qualification" id="qualification"
                                                           onchange="showQualification(this)"/>
                                                    <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr id="doorPhotoType" style="display:">
                                            <td class="td-left">门头照照片：<span style="color:red;">(必填)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success doorPhotoClick"
                                                   data-target="#previewImageModal">
                                                    <label id="doorPhotoDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="doorPhotoImageDiv" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;display:none"/>
                                                    </label>
                                                </a>
                                                <div class="updateImageDiv" style="float:left; margin-top:75">
                                                    <input type="hidden" id="doorPhotoPath" name="doorPhotoPath"/>
                                                    <input type="hidden" id="doorPhotoImageVal02"/>
                                                    <input type="file" name="doorPhoto" id="doorPhoto"
                                                           onchange="showDoorPhoto(this)"/>
                                                    <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr id="shopInteriorType" style="display:">
                                            <td class="td-left">店内照照片：<span>(可选)</span></td>
                                            <td class="td-right" colspan="3">
                                                <a data-toggle='modal' class="tooltip-success shopInteriorClick"
                                                   data-target="#previewImageModal">
                                                    <label id="shopInteriorDiv"
                                                           style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
                                                        <img id="shopInteriorImageDiv" onclick="bigImg(this);"
                                                             style="width:100%;height:100%;display:none"/>
                                                    </label>
                                                </a>
                                                <div class="updateImageDiv" style="float:left; margin-top:75">
                                                    <input type="hidden" id="shopInteriorPath" name="shopInteriorPath"/>
                                                    <input type="hidden" id="shopInteriorImageVal02"/>
                                                    <input type="file" name="shopInterior" id="shopInterior"
                                                           onchange="showShopInterior(this)"/>
                                                    <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div style="margin:50px 0 0 0;text-align:center">
                                <button type="button" class="btn btn-primary" id='submitData'>提交报备</button>
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

    //根据商户类型显示内容
    /* function getType(){
        var merchantType = $("#merchantType").val();
        if("12" == merchantType){
            $("#otherMaterialType").attr("style","display:");
            $("#mobileType").attr("style","display:");
            $("#bankCardPhotoType").attr("style","display:");
            $("#openType").attr("style","display:none");
            $("#businessPhotoType").attr("style","display:none");
        }else{
            $("#mobileType").attr("style","display:none");
            $("#bankCardPhotoType").attr("style","display:none");
            $("#openType").attr("style","display:");
            $("#otherMaterialType").attr("style","display:none");
            $("#businessPhotoType").attr("style","display:");
        }

    } */

    /*
    * file input file dom object
    * pathTarget 路径保存的input元素id
    * preView 图片预览dom id
    */
    function commonFileUpload(file, pathTarget, preView) {
        var formdata = new FormData();
        formdata.append("file", $(file).get(0).files[0]);
        $.ajax({
            url: '/common/files/upload',
            type: 'post',
            contentType: false,
            data: formdata,
            processData: false,
            async: false,
            success: function (info) {
                $('#' + pathTarget + '').val(info.path);
            },
            error: function (err) {
                console.log(err)
            }
        });

        var prevDiv = document.getElementById(preView);
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" onclick="bigImg(this);" style="width:120px;height:100px;" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }

    //营业执照上传
    function showBusinessPhoto(file) {
        commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
    }

    //营业执照授权函上传
    function showBusinessAuthPhoto(file) {
        commonFileUpload(file, 'businessAuthPhotoPath', 'businessAuthPhotoDiv');
    }

    //上传门头照
    function showDoorPhoto(file) {
        commonFileUpload(file, 'doorPhotoPath', 'doorPhotoDiv');
    }

    //上传店内照
    function showShopInterior(file) {
        commonFileUpload(file, 'shopInteriorPath', 'shopInteriorDiv');
    }

    //上传特殊行业资质照
    function showQualification(file) {
        commonFileUpload(file, 'qualificationPath', 'qualificationDiv');
    }


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
    function getLevel(parentLevel, parentText) {
        if (parentText == null || parentText == "") {
            $("#" + parentLevel + "").html("");
            if (parentLevel == "levelTwo") {
                $("#" + parentLevel + "").append("<option value=''>--请选择二级经营类目--</option>");
            } else {
                $("#" + parentLevel + "").append("<option value=''>--请选择三级经营类目--</option>");
            }
            return false;
        }
        //var levelOne = $("#levelOne").val().trim();
        $.post(window.Constants.ContextPath + "/common/info/getIndustrieInfo",
            {
                "channelCode": $("#channlCode").val(),
                "parentLevel": parentLevel,
                "parentText": parentText
            },
            function (res) {
                if (res.result == "SUCCESS") {
                    var aliPayIndustryList = res.data;
                    $("#" + parentLevel + "").html("");
                    if (parentLevel == "levelTwo") {
                        $("#" + parentLevel + "").append("<option value=''>--请选择二级经营类目--</option>");
                        for (var index in aliPayIndustryList) {
                            $("#" + parentLevel + "").append(
                                "<option >"
                                + aliPayIndustryList[index].levelTwo + "</option>");
                        }
                    } else {
                        $("#" + parentLevel + "").append("<option value=''>--请选择三级经营类目--</option>");
                        for (var index in aliPayIndustryList) {
                            $("#" + parentLevel + "").append(
                                "<option value='" + aliPayIndustryList[index].levelCode + "' data-special='" + aliPayIndustryList[index].specialCertificate + "'>"
                                + aliPayIndustryList[index].levelThree + "</option>");
                        }
                    }

                } else if (res.result == "FAIL") {
                    alert(res.result + ":" + res.message);
                }
            }, 'json'
        );
    }


    /** 照片点击预览 **/
    /* $('.shopInteriorClick').click(function(){
        var divObj = document.getElementById("showImageDiv");
        var imageObj = document.getElementById("showImage");
        var obj = document.getElementById("shopInteriorClick");
        return previewImage(divObj,imageObj,obj);
    }); */




    /* 校验渠道 */
    $(function () {


        //判定是新进件还是更新进件
        var status = $("#status").val();
        var custId = $("#custId").val();
        var authId = $("#authId").val();
        var picturePathVo = JSON.parse('${picturePathVo}');
        if ("" == custId) {
            alert(status);
        } else {

            $("#businessPhotoImageDiv").show();
            $("#doorPhotoImageDiv").show();
            $("#shopInteriorImageDiv").show();
            $("#qualificationImageDiv").show();
            $("#businessAuthPhotoImageDiv").show();
            $("#qualificationImageDiv").attr("src", picturePathVo.qualificationPath);
            $("#businessAuthPhotoImageDiv").attr("src", picturePathVo.webUrlPath);
            $("#shopInteriorImageDiv").attr("src", picturePathVo.shopInteriorPath);
            $("#businessPhotoImageDiv").attr("src", picturePathVo.bussinessPath);
            $("#doorPhotoImageDiv").attr("src", picturePathVo.doorPhotoPath);
        }


        //提交报备
        $("#submitData").click(function () {
            //渠道号
            var channelNo = $("#channlCode").val();
            var merchantCode = $("#merchantCode").val();
            var custName = $("#custName").val();
            //var merchantCodeType = $("#merchantType").val();
            //var custName = $("#custName").val();
            var account = $("#account").val();//支付宝账号
            var contactName = $("#contactName").val();//联系人名称
            var contactMobile = $("#contactMobile").val();//联系人手机号码
            var contactEmail = $("#contactEmail").val();//联系人邮箱
            //经营类目编码
            var mccCode = $("#levelThree").val();
            //特殊行业资质照
            var qualificationPath = $("#qualificationPath").val();
            if(qualificationPath == null || qualificationPath == '' || qualificationPath == void 0){
                qualificationPath = picturePathVo.qualificationPath;
            }

            var businessLicense = $("#businessLicense").val();//营业执照号
            //营业执照图片路径
            var businessPhotoPath = $("#businessPhotoPath").val();
            if(businessPhotoPath == null || businessPhotoPath == '' || businessPhotoPath == void 0){
                businessPhotoPath = picturePathVo.bussinessPath;
            }
            //营业执照授权函图片路径
            var businessAuthPhotoPath = $("#businessAuthPhotoPath").val();
            if(businessAuthPhotoPath == null || businessAuthPhotoPath == '' || businessAuthPhotoPath == void 0){
                businessAuthPhotoPath = picturePathVo.businessAuthPhotoPath;
            }

            var businessTerm = $("#businessTerm").val();//营业期限时间

            var shopInteriorPath = $("#shopInteriorPath").val();//店内照
            if(shopInteriorPath == null || shopInteriorPath == '' || shopInteriorPath == void 0){
                shopInteriorPath = picturePathVo.shopInteriorPath;
            }

            var doorPhotoPath = $("#doorPhotoPath").val();//门头照
            if(doorPhotoPath == null || doorPhotoPath == '' || doorPhotoPath == void 0 ){
                doorPhotoPath = picturePathVo.doorPhotoPath;
            }
            //var businessEffectiveTerm = $("#businessEffectiveTerm").val();//营业起始时间

            //支付宝渠道
            if ("ALIPAY" == channelNo) {
                //未报备
                if ("unReported" == status || "" == status) {

                    if ("" == merchantCode) {
                        $("#merchantCodeLab").text("商户编号不能为空");
                        $("#merchantCode").focus();
                        return false;
                    } else {
                        $("#merchantCodeLab").text('');
                    }

                    if ("" == account) {
                        $("#accountLab").text("支付宝账号不能为空");
                        $("#account").focus();
                        return false;
                    } else {
                        $("#accountLab").text('');
                    }

                    if ("" == contactName) {
                        $("#contactNameLab").text("联系人名称不能为空");
                        $("#contactName").focus();
                        return false;
                    } else {
                        $("#contactNameLab").text('');
                    }

                    if ("" == contactMobile) {
                        $("#contactMobileLab").text("联系人手机号码不能为空");
                        $("#contactMobile").focus();
                        return false;
                    } else {
                        $("#contactMobileLab").text('');
                    }

                    if ("" == contactEmail) {
                        $("#contactEmailLab").text("联系人邮箱不能为空");
                        $("#contactEmail").focus();
                        return false;
                    } else {
                        $("#contactEmailLab").text('');
                    }

                    if ("" == mccCode) {
                        $("#levelThreeLabel").text("商户经营类目不能为空");
                        $("#levelThree").focus();
                        return false;
                    } else {
                        $("#levelThreeLabel").text('');
                    }

                    if ("" == businessLicense) {
                        $("#businessLicenseLab").text("营业执照号不能为空");
                        $("#businessLicense").focus();
                        return false;
                    } else {
                        $("#businessLicenseLab").text('');
                    }


                    /* if("" == businessEffectiveTerm || "" == businessTerm){
                       $("#businessEffectiveTermLab").text("营业执照时间不能为空");
                       $("#businessEffectiveTerm").focus();
                       return false;
                   }else{
                       $("#businessEffectiveTermLab").text("");
                   } */

                    if ("" == businessPhotoPath || null == businessPhotoPath) {
                        alert("营业执照不能为空，请上传营业执照！");
                        return false;
                    }
                    //是否特殊行业资质
                    var isSpecial = $("#specialCertificate").html();
                    if (null != isSpecial && "" != isSpecial) {
                        if ("" == qualificationPath || null == qualificationPath) {
                            alert("特殊行业资质照不能为空，请上传特殊行业资质照！");
                            return false;
                        }
                    }

                    if ("" == doorPhotoPath || null == doorPhotoPath) {
                        alert("门头照不能为空，请上传门头照！");
                        return false;
                    }


                    //提交报备
                    $.post(window.Constants.ContextPath + "/merchant/merchantReported/aliPayReports",
                        {
                            "channelNo": channelNo,
                            "merchantCode": merchantCode,
                            "custName": custName,
                            "account": account,
                            "contactName": contactName,
                            "contactMobile": contactMobile,
                            "contactEmail": contactEmail,
                            "mccCode": mccCode,
                            "qualificationPath": qualificationPath,
                            "businessLicense": businessLicense,
                            "businessPhotoPath": businessPhotoPath,
                            "businessAuthPhotoPath": businessAuthPhotoPath,
                            "businessTerm": businessTerm,
                            "shopInteriorPath": shopInteriorPath,
                            "doorPhotoPath": doorPhotoPath
                        },
                        function (data) {
                            if (data.code == "SUCCESS") {
                                alert("提交报备成功");

                                window.opener = null;
                                window.open("", "_self");
                                window.close();
                            } else {
                                if (null == data.message || "" == data.message) {
                                    alert("进件失败");
                                } else {
                                    alert(data.message);
                                }
                            }
                        }, 'json'
                    );

                }

            }
        });
    });
</script>
</body>
</html>