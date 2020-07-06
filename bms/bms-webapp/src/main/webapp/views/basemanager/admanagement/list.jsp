<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/template.jsp" %>
<%@page import="com.qifenqian.bms.basemanager.admanage.AdManagementPath" %>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/laydate/laydate.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>


<html>
<head>
    <meta charset="utf-8"/>
    <title>首页广告维护</title>
    <meta name="keywords" content="七分钱后台管理系统"/>
    <meta name="description" content="七分钱后台管理"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <style type="text/css">
        table tr td {
            word-wrap: break-word;
            word-break: break-all;
        }

        .uploadImage {
            float: left;
            background: url(<%=request.getContextPath() %>/static/static/images/upload.jpg);
            background-size: 120px 100px;
            width: 120px;
            height: 100px;
            margin: 10 10 10 10;
        }

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

<body onload="loadAd()">
<!-- 用户信息 -->
<%@ include file="/include/top.jsp" %>

<div class="main-container" id="main-container" data-backdrop="static">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <!-- 菜单 -->
        <%@ include file="/include/left.jsp" %>

        <div class="main-content">
            <!-- 路径 -->
            <%@ include file="/include/path.jsp" %>

            <!-- 主内容 -->
            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">

                        <!-- 查询条件 -->
                        <form action='<c:url value="<%=AdManagementPath.BASE + AdManagementPath.LIST %>"/>'
                              method="post">
                            <table class="search-table">
                                <tr>
                                    <td class="td-left" width="10%">广告名</td>
                                    <td class="td-right" width="10%">
										<span class="input-icon">
											<input type="text" name="adName" id="adName" value="${queryBean.adName}"
                                                   size="35">
											<i class="icon-leaf blue"></i>
										</span>
                                    </td>
                                    <td class="td-left" width="10%">图片路径</td>
                                    <td class="td-right" width="20%">
										<span class="input-icon">
											<input type="text" name="imagePath" id="imagePath"
                                                   value="${queryBean.imagePath}" size="35">
											<i class="icon-leaf blue"></i>
										</span>
                                    </td>
                                    <td class="td-left" width="10%">广告类型</td>
                                    <td class="td-right" width="10%">
										<span class="input-icon">
											<input type="hidden" name="typeTemp" id="typeTemp"
                                                   value="${queryBean.type}">
											<select name="type" id="type">
												<option value="">-请选择-</option>
												<option value="1">-支付-</option>
												<option value="0">-轮播-</option>
											</select>
										</span>
                                    </td>
                                    <td class="td-left" width="10%">状态</td>
                                    <td class="td-right" width="10%">
										<span class="input-icon">
											<input type="hidden" name="isValidTemp" id="isValidTemp"
                                                   value="${queryBean.isValid}">
											<select name="isValid" id="isValid">
												<option value="">-请选择-</option>
												<option value="1">-有效-</option>
												<option value="0">-无效-</option>
											</select>
										</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="8" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function
                                                    url="<%=AdManagementPath.BASE + AdManagementPath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
                                            </gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin " id="clearAdvetis">
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=AdManagementPath.BASE + AdManagementPath.ADD%>">
												<button id="addModal" class="btn btn-purple btn-sm   addAdClick"
                                                        data-toggle='modal' data-target="#addAdModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
                                            </gyzbadmin:function>
                                            <gyzbadmin:function
                                                    url="<%=AdManagementPath.BASE + AdManagementPath.DISTRIBUTION%>">
												<button id="distributionModal"
                                                        class="btn btn-purple btn-sm   distributionAdButton"
                                                        data-toggle='modal' data-target="#distributionAdModal">
													发布广告
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
                                            </gyzbadmin:function>
											
										</span>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <div class="list-table-header">
                            广告列表
                        </div>
                        <div class="table-responsive">
                            <table id="sample-table-2" class="list-table">
                                <thead>
                                <tr>
                                    <th width="1%">
                                        <input type="checkbox" name="adManagementCheck" id="adManagementCheck"
                                               title="全选"/>
                                    </th>
                                    <th width="10%">广告名</th>
                                   <!--  <th width="10%">商户号</th> -->
                                    <th width="15%">图片路径</th>
                                    <th width="15%">链接地址</th>
                                    <th width="5%">类别</th>
                                    <%--<th width="5%">状态</th>--%>
                                    <th width="5%">显示时间</th>
                                    <th width="5%">创建人</th>
                                    <th width="12%">创建时间</th>
                                    <th width="5%">修改人</th>
                                    <th width="12%">修改时间</th>
                                    <th width="5%">是否有效</th>
                                    <th width="6%">操作</th>
                                </tr>
                                </thead>
                                <tbody id="adManagementCheckList">
                                <c:forEach items="${adList}" var="ad">
                                    <tr class="ad">
                                        <td>
                                            <input type="checkbox" name="adManagementDeposit" id="adManagementDeposit"
                                                   value="${ad.adId}#${ad.adName}#${ad.type}#${ad.showTime}#${ad.url}"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="adId" id="adId">
                                                ${ad.adName }
                                        </td>
                                        <%-- <td>${ad.custId }</td> --%>
                                        <td>
                                                ${ad.imagePath }
                                        </td>
                                        <td>${ad.url }</td>
                                        <td>
                                            <c:if test="${ad.type == '0'}">轮播</c:if>
                                            <c:if test="${ad.type == '1'}">支付</c:if>
                                        </td>
                                            <%--												<td>--%>
                                            <%--													<c:if test="${ad.status == '1'}">启用</c:if>--%>
                                            <%--													<c:if test="${ad.status == '0'}">停用</c:if>--%>
                                            <%--												</td>--%>
                                        <td>${ad.showTime } 秒</td>
                                        <td>${ad.createId }</td>
                                        <td>
                                            <fmt:formatDate value="${ad.createTime }" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                        <td>${ad.modifyId }</td>
                                        <td>
                                            <fmt:formatDate value="${ad.modifyTime }" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                        <td>
                                            <c:if test="${ad.isValid == '1'}">有效</c:if>
                                            <c:if test="${ad.isValid == '0'}">无效</c:if>
                                        </td>
                                        <td>
                                            <gyzbadmin:function
                                                    url="<%=AdManagementPath.BASE + AdManagementPath.UPDATE%>">
                                                <a href="#" class="tooltip-success updateAdClick" data-toggle='modal'
                                                   data-target="#updateAdModal" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
                                                </a>
                                            </gyzbadmin:function>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty adList}">
                                    <tr>
                                        <td colspan="12" align="center"><font
                                                style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                        <!-- 分页 -->
                        <c:if test="${not empty adList}">
                            <%@ include file="/include/page.jsp" %>
                        </c:if>
                    </div>
                </div>
            </div><!-- /.page-content -->

            <div class="modal fade" id="updateAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content" style="width: 600px;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">更新广告信息</h4>
                        </div>
                        <div class="modal-body">
                            <form action='<%=AdManagementPath.BASE + AdManagementPath.UPDATE %>' method="post"
                                  id="updateadForm">
                                <table class="modal-input-table" style="width: 100%;">
                                    <tr>
                                        <td class="td-left" width="20%">广告名<span style="color:red">*</span></td>
                                        <td class="td-right" width="80%">
                                            <input type="hidden" name="adId" id="adId">
                                            <input type="text" id="adName" name="adName" style="width:80%">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left">图片路径<span style="color:red">*</span></td>
                                        <td class="td-right">
                                            <textarea rows="2" cols="" id="editImagePath" name="imagePath"
                                                      style="width:80%"
                                                      readonly="readonly"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left">上传图片<span style="color:red">*</span></td>
                                        <td class="td-right">
                                            <a data-toggle='modal' class="tooltip-success"
                                               data-target="#previewImageModal">
                                                <label id="businessPhotoDiv"
                                                       style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
                                                    <img id="businessPhotoImageDiv" onclick="bigImg(this)" src=""
                                                         style="width:100%;height:100%;display:none"/>
                                                </label>
                                            </a>
                                            <div class="updateImageDiv" style="float:left; margin-top:75 ">
                                                <input type="file" name="businessPhoto" id="businessPhoto"
                                                       onchange="adImgUpdate(this)"/>
                                                <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left">超链接（URL）<span style="color:red">*</span></td>
                                        <td class="td-right">
                                            <textarea rows="2" cols="" id="editUrl" name="url" readonly="readonly"
                                                      style="width:80%"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left">广告类型<span style="color:red">*</span></td>
                                        <td class="td-right">
                                            <select id="type_" name="type_" style="width:80%">
                                                <option value="">-请选择-</option>
                                                <option value="1">-支付广告-</option>
                                                <option value="0">-轮播广告-</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left" width="20%">播放时长(秒)<span style="color:red">*</span></td>
                                        <td class="td-right" width="80%">
                                            <input type="text" id="showTime" name="showTime"
                                                   onkeyup="value=value.replace(/[^\d]/g,'')" style="width:80%"
                                                   value="4">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td-left">是否有效<span style="color:red">*</span></td>
                                        <td class="td-right">
                                            <select id="isValid" name="isValid" style="width:80%">
                                                <option value="">-请选择-</option>
                                                <option value="1">-有效-</option>
                                                <option value="0">-无效-</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary updateAdBtn">提交</button>
                        </div>
                    </div><!-- /.modal-content -->
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
<div class="modal fade" id="addAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:40%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增广告</h4>
            </div>
            <div class="modal-body">
                <form action='<%=AdManagementPath.BASE + AdManagementPath.ADD %>' method="post" id="addadForm">
                    <table class="modal-input-table" style="width: 100%;">
                        <tr>
                            <td class="td-left" width="20%">广告名<span style="color:red">*</span></td>
                            <td class="td-right" width="80%">
                                <input type="text" id="adName" name="adName" style="width:80%">
                            </td>
                        </tr>
                        <%-- <tr>
                            <td class="td-left" width="20%">商户号<span style="color:red">*</span></td>
                            <td class="td-right" width="80%">
                            	<select id="custId" name="custId">
									<option value="">输入商户名查询</option>
									<c:forEach items="${merchantList }" var="bean">
										<option value="${bean.custId }">${bean.custName }</option>
									</c:forEach>
								</select>
                            </td>
                        </tr> --%>
                        <tr>
                            <td class="td-left">图片上传<span style="color:red">*</span></td>
                            <td class="td-right">
                                <a data-toggle='modal' class="tooltip-success certAttribute1Click"
                                   data-target="#previewImageModal">
                                    <label id="certAttribute1Div" class="uploadImage">
                                        <img id="certAttribute1Image" style="width:100%;height:100%;display:none"/>
                                    </label>
                                </a>
                                <div style="float:left;margin-top:75">
                                    <input type="hidden" id="addImagePath" name="imagePath"/>
                                    <input type="hidden" id="addUrl" name="url"/>
                                    <input type="file" name="certAttribute1" id="certAttribute1"
                                           onchange="adImgAdd(this)"/>
                                    <p><span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">广告类型<span style="color:red">*</span></td>
                            <td class="td-right">
                                <select id="type_" name="type_" style="width:80%">
                                    <option value="">-请选择-</option>
                                    <option value="1">-支付广告-</option>
                                    <option value="0">-轮播广告-</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left" width="20%">播放时长(秒)<span style="color:red">*</span></td>
                            <td class="td-right" width="80%">
                                <input type="text" id="showTime" name="showTime"
                                       onkeyup="value=value.replace(/[^\d]/g,'')" style="width:80%" value="4">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary addAdBtn">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div><!-- /.modal -->

<div class="modal fade" id="distributionAdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:50%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">广告发布</h4>
            </div>
            <div class="modal-body">

                <div border="1" class="modal-input-table" style="width: 100%;">
                    <div id="distributionAdDIV">
                        <div style="font-size: 18px;background:rgb(67,142,185);color: #FFF">广告素材预览</div>
                        <div class="img-wrap" id="modalImg"></div>
                    </div>
                    <div id="distributionAdCustDIV">
                    	<!-- <div style="font-size: 18px;background:rgb(67,142,185);color: #FFF">图片类型选择
                            <input type="checkbox" checked="checked" id="defaultAllCust" onclick="defaultAllCust()"
                                   style="margin-right: 80px;float: right"><label
                                    style="font-size: 14px;float:right">是否默认图片</label>
                        </div> -->
                        <div style="font-size: 18px;background:rgb(67,142,185);color: #FFF">图片归属商户
                            <input type="checkbox" checked="checked" id="checkAllCust" onclick="checkAllCust()"
                                   style="margin-right: 80px;float: right"><label
                                    style="font-size: 14px;float:right">是否全选门店</label>
                        </div>
                        <div id="distributionAdCustListDIV" style="display: none">
                            <div>
                                <table class="search-table">
                                    <tr>
                                        <td class="td-left" width="10%">商户名称</td>
                                        <td class="td-right" width="10%">
                                                <span class="input-icon">
                                                    <input type="text" name="custName" id="custName" size="35">
                                                    <i class="icon-leaf blue"></i>
                                                </span>
                                        </td>
                                        <td class="td-left" width="10%">门店名称</td>
                                        <td class="td-right" width="10%">
                                                <span class="input-icon">
                                                    <input type="text" name="shopName" id="shopName" size="35">
                                                    <i class="icon-leaf blue"></i>
                                                </span>
                                        </td>
                                        <td align="center">
                                                <span class="input-group-btn">
                                                    <gyzbadmin:function
                                                            url="<%=AdManagementPath.BASE + AdManagementPath.ADALLCUSTOMLIST %>">
                                                        <button type="button" onclick="findCustInfoList()"
                                                                class="btn btn-purple btn-sm">
                                                            查询
                                                            <i class="icon-search icon-on-right bigger-110"></i>
                                                        </button>
                                                    </gyzbadmin:function>
                                                </span>
                                            <span class="input-group-btn">
                                                        <button type="button" onclick="findCustInfoListReset()"
                                                                class="btn btn-purple btn-sm">
                                                            清空
                                                            <i class="icon-search icon-on-right bigger-110"></i>
                                                        </button>
                                                </span>
                                        </td>
                                    </tr>
                                </table>

                                <%--                                <div>--%>
                                <%--                                    <table border class="list-table">--%>
                                <%--                                        --%>
                                <%--                                    </table>--%>
                                <%--                                </div>--%>
                                <div style="overflow-y: auto;height: 300px">
                                    <table id="distributionAdCustListTable" border class="list-table">
                                        <thead>
                                        <tr>
                                            <th width="1%">
                                                <input type="checkbox" name="distributionAdCustListTableCheck"
                                                       id="distributionAdCustListTableCheck"
                                                       title="全选"/>
                                            </th>
                                            <th width="10%">商户名称</th>
                                            <th width="15%">门店编号</th>
                                            <th width="15%">门店名称</th>
                                            <th width="25%">地址</th>
                                        </tr>
                                        </thead>
                                        <tbody id="distributionAdCustListTableBody"/>
                                    </table>
                                </div>

                            </div>
                            <ul class="custInfoUl"
                                style="overflow: auto;margin-left: 50px;margin-top: 5px">
                            </ul>
                        </div>
                    </div>
                </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary distributionAdSubmit">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div>
    </div><!-- /.modal -->
</div>

<!-- 图片预览 -->
<div class="modal fade" id="previewImageModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:60%;height:80%;">
        <div id="showImageDiv" style="width:100%;height:100%;">
            <img id="showImage" style="width:100%;height:100%;">
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    $(function () {
        /**
         *声明 广告发布的参数集合
         **/
        var adDOList = [];
        var mchShopDOList = [];
        /**
         * 取数据匹配
         **/
        var ads = ${adList};
        var adList = $("tr.ad");
        $.each(ads, function (i, value) {
            $.data(adList[i], "ad", value);
        });
        /**
         * 清除数据
         * */
        $('#clearAdvetis').click(function () {
            $(".search-table #adName").val('');
            $(".search-table #imagePath").val('');
            $(".search-table #isValid").val('');
        });

        /**
         * @Author LiBin
         * @Description 推送广告界面打开并赋值
         * @Param
         * @Return
         * @Date 2019/12/12 0012 18:10
         */
        $('.distributionAdButton').click(function () {
            $('#modalImg').html('');
            if ($("input[type='checkbox'][name='adManagementDeposit']").is(':checked') == false) {
                $.gyzbadmin.alertFailure("请选择广告");
                return false;
            }
            var settleObj = document.getElementsByName('adManagementDeposit');
            var num = 0;
            var payTypeNum = 0;
            for (var i = 0; i < settleObj.length; i++) {
                if (settleObj[i].checked) {
                    num++;
                    var values = settleObj[i].value.split('#');
                    var distributionAdType = values[2];
                    if (distributionAdType == '1') {
                        payTypeNum++;
                    }
                }
            }
            if (num > 4) {
                $.gyzbadmin.alertFailure("最多选择四条广告");
                return false;
            }
            if (payTypeNum > 1) {
                $.gyzbadmin.alertFailure("最多选一条支付广告");
                return false;
            }
            if (payTypeNum == 0 && num == 4) {
                $.gyzbadmin.alertFailure("最多选三条轮播广告和一条支付广告");
                return false;
            }

            var sortNo = 1;
            for (var i = 0; i < settleObj.length; i++) {
                if (settleObj[i].checked) {
                    var values = settleObj[i].value.split('#');
                    var distributionAdId = values[0];
                    var distributionAdName = values[1];
                    var distributionAdType = values[2];
                    var distributionAdShowTime = values[3];
                    var distributionAdUrl = values[4];
                    var sequence = i +'sequence';
                    var machineType = i +'machineType';
                    if (distributionAdType == '0') {
                        distributionAdType = '轮播';
                    } else if (distributionAdType == "1") {
                        distributionAdType = '支付';
                    }
                    distributionAdType = distributionAdType
                    var modalImgHtm = "<div class='td-left'>";
                    modalImgHtm += "<a data-toggle='modal' class='tooltip-success' data-target= '#previewImageModal'>";
                    modalImgHtm += "<img onclick='bigImg(this)' src=" + window.Constants.ContextPath + distributionAdUrl + ">";
                    modalImgHtm += "</a>";
                    modalImgHtm += "<div ><label>广告类型 : " + distributionAdType + "</label></div>";
                    modalImgHtm += "<div><label>广告名称 : " + distributionAdName + "</label></div>";
                    modalImgHtm += "<div><input type='text' id='"+sequence + "' placeholder='广告图片顺序' ></div>";
                    modalImgHtm += "<div><select class='form-control'  id='"+machineType +"'>";
                    modalImgHtm += "<option value='QINGWA'>"+ "青蛙" + "</option>";
                    modalImgHtm += "<option value='QINGWAPRO'>"+ "青蛙PRO" + "</option>";
                    modalImgHtm += "<option value='QINGTING'>"+ "蜻蜓" + "</option></select>";
                    modalImgHtm += "</div></div>";
                    $('#modalImg').append(modalImgHtm);
                    /**
                     * 赋值发布广告参数信息
                     **/
                    var adDO = {
                        adId: distributionAdId,
                        sortNo: sortNo
                    }
                    adDOList.push(adDO);
                    sortNo++;
                }
            }
            <%--var customName = "";--%>
            <%--$.ajax({--%>
            <%--    type: "POST",--%>
            <%--    dataType: "json",--%>
            <%--    url: window.Constants.ContextPath + '<%=AdManagementPath.BASE + AdManagementPath.ADCUSTOMLIST%>',--%>
            <%--    data: {--%>
            <%--        customName: customName--%>
            <%--    },--%>
            <%--    success: function (data) {--%>
            <%--        $.unblockUI();--%>
            <%--        if (data.result == "SUCCESS") {--%>
            <%--            var adCustomInfos = data.data;--%>

            <%--            /* 生成商户门店树 */--%>
            <%--            var htm = '';--%>
            <%--            htm = menu(adCustomInfos);--%>

            <%--            $("#distributionAdModal .custInfoUl").html(htm);--%>
            <%--            /* ul 样式*/--%>
            <%--            $("#distributionAdModal .custInfoUl ul").toggleClass("displayUl");--%>

            <%--            $("#distributionAdModal .custInfoUl > li .textClass").click(function () {--%>
            <%--                $(this).parent().children("ul").toggleClass("displayUl");--%>
            <%--            });--%>
            <%--            /* 级联选择 */--%>
            <%--            $("#distributionAdModal input[name='chk']").click(function () {--%>
            <%--                $(this).parent("li").find('input[name="chk"]').prop("checked", this.checked);--%>
            <%--            });--%>
            <%--        }--%>
            <%--    }--%>
            <%--});--%>

        });

        /**
         * 编辑点击赋值
         **/
        $('.updateAdClick').click(function () {
            var ad = $.data($(this).parent().parent()[0], "ad");
            $('#updateAdModal').on('show.bs.modal', function () {
                $("#updateAdModal #businessPhotoImageDiv").show();
                <%--$("#updateAdModal #businessPhotoImageDiv").attr("src", "<%=request.getContextPath()+AdManagementPath.BASE + AdManagementPath.IMAGE %>?adId=" + ad.adId + "");--%>
                $("#updateAdModal #businessPhotoImageDiv").attr("src", ad.url);
                $("#updateAdModal #adId").val(ad.adId);
                $("#updateAdModal #adName").val(ad.adName);
                $("#updateAdModal #editImagePath").val(ad.imagePath);
                $("#updateAdModal #editUrl").val(ad.url);
                $("#updateAdModal #isValid").val(ad.isValid);
                $("#updateAdModal #showTime").val(ad.showTime);
                $("#updateAdModal #type_").val(ad.type);
            })
            $('#updateAdModal').on('hide.bs.modal', function () {
                $("#updateAdModal #adId").val('');
                $("#updateAdModal #adName").val('');
                $("#updateAdModal #editImagePath").val('');
                $("#updateAdModal #editUrl").val('');
                $("#updateAdModal #isValid").val('');
                $("#updateAdModal #showTime").val("");
                $("#updateAdModal #type_").val("");
            })

        })


        /** 更新按钮提交 **/
        $('.updateAdBtn').click(function () {
            var adName = $("#updateAdModal #adName").val();
            if (kong.test(adName)) {
                $.gyzbadmin.alertFailure("广告名不可为空");
                $("#updateAdModal #adName").focus();
                return;
            }
            var isValid = $("#updateAdModal #isValid").val();
            if (kong.test(isValid)) {
                $.gyzbadmin.alertFailure("状态不可为空");
                return;
            }

            var type_ = $("#updateAdModal #type_").val();
            if (kong.test(type_)) {
                $.gyzbadmin.alertFailure("类型不可为空");
                return;
            }

            var showTime = $("#updateAdModal #showTime").val();
            if (kong.test(showTime)) {
                $.gyzbadmin.alertFailure("播放时长不可为空");
                return;
            }

            var adId = $("#updateAdModal #adId").val();
            var imagePath = $("#editImagePath").val();
            if (kong.test(imagePath)) {
                $.gyzbadmin.alertFailure("图片地址不可为空");
                return;
            }
            var editUrl = $("#editUrl").val();
            if (kong.test(editUrl)) {
                $.gyzbadmin.alertFailure("图片链接不可为空");
                return;
            }
            var businessPhoto = $("#updateAdModal #businessPhoto").val();
            $.blockUI();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: window.Constants.ContextPath + '<%=AdManagementPath.BASE + AdManagementPath.UPDATE %>',
                data:
                    {
                        "adId": adId,
                        "adName": adName,
                        "imagePath": imagePath,
                        "url": editUrl,
                        "type": type_,
                        "showTime": showTime,
                        "isValid": isValid
                    },
                success: function (data) {
                    $.unblockUI();
                    if (data.result == "SUCCESS") {
                        $.gyzbadmin.alertSuccess("更新成功！", function () {
                            $("#updateAdModal").modal("hide");
                        }, function () {
                            window.location.reload();
                        });

                    } else {
                        $.gyzbadmin.alertFailure("更新失败！：" + data.message);
                    }
                }
            })

        });


        /**新增**/
        $('.addAdBtn').click(function () {
            var adName = $("#addAdModal #adName").val();
            if (kong.test(adName)) {
                $.gyzbadmin.alertFailure("广告名不可为空");
                $("#addAdModal #adName").focus();
                return;
            }
            /* var custId = $("#addAdModal #custId").val();
            if (kong.test(custId)) {
                $.gyzbadmin.alertFailure("商户名不可为空");
                $("#addAdModal #custId").focus();
                return;
            } */
            
            var imagePath = $('#addImagePath').val();
            if (kong.test(imagePath)) {
                $.gyzbadmin.alertFailure("广告地址不可为空");
                $("#addAdModal #imagePath").focus();
                return;
            }
            var url = $("#addUrl").val();
            if (kong.test(url)) {
                $.gyzbadmin.alertFailure("url不可为空");
                $("#addadModal #url").focus();
                return;
            }
            var type_ = $("#addAdModal #type_").val();
            if (kong.test(type_)) {
                $.gyzbadmin.alertFailure("类型不可为空");
                $("#addadModal #type_").focus();
                return;
            }
            var showTime = $("#addAdModal #showTime").val();
            if (kong.test(showTime)) {
                $.gyzbadmin.alertFailure("播放时长不可为空");
                $("#addadModal #showTime").focus();
                return;
            }

            $.blockUI();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: window.Constants.ContextPath + '<%=AdManagementPath.BASE + AdManagementPath.ADD %>',
                data:
                    {
                        "adName": adName,
                        /* "custId": custId, */
                        "imagePath": imagePath,
                        "url": url,
                        "type": type_,
                        "showTime": showTime
                    },
                success: function (data) {
                    $.unblockUI();
                    if (data.result == "SUCCESS") {
                        $.gyzbadmin.alertSuccess("添加成功！", function () {
                            $("#addAdModal").modal("hide");
                        }, function () {
                            window.location.reload();
                        });
                    } else {
                        $.gyzbadmin.alertFailure("添加失败！" + data.message);
                    }
                }
            });

        });


        /**
         * @Author LiBin
         * @Description 发布广告提交
         * @Param
         * @Return
         * @Date 2019/12/12 0012 16:08
         */
        $('.distributionAdSubmit').click(function () {

            // $("#distributionAdModal .class_ad_shop input[name='chk']").each(function (index, item) {
            //     if (item.checked) {
            //         var mchShopDO = {
            //             shopId: item.value
            //         }
            //         mchShopDOList.push(mchShopDO);
            //     }
            // })
            
            var checkValue = $('#checkAllCust').prop('checked');
            if(checkValue){
            	var mchShopDO = {
                        mchId: "default",
                        shopId:"default"
                    }
                    mchShopDOList.push(mchShopDO);
            }else if (!checkValue) {
                $('#distributionAdCustListTableBody input[type="checkbox"]').each(function (index, item) {
                    if (item.checked) {
                        var values = item.value.split('#');
                        ;
                        var mchShopDO = {
                            mchId: values[0],
                            shopId: values[1]
                        }
                        mchShopDOList.push(mchShopDO);
                    }
                })
            }
            if (!checkValue && mchShopDOList.length < 1) {
                $.gyzbadmin.alertFailure("请选择商户门店不可为空");
                return false;
            }
            var a =0;
           	var settleObj = document.getElementsByName('adManagementDeposit');
           	for(var j=0;j<settleObj.length;j++){
           		if (settleObj[j].checked) {
               		adDOList[a].sequence = $("#" +j +"sequence").val();
               		adDOList[a].machineType= $("#" +j +"machineType").val();
               		a++;
               	}
           	}
            var shopAdDO = {
                adDOList: adDOList,
                mchShopDOList: mchShopDOList
            }
            $.ajax({
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                url: window.Constants.ContextPath + '<%=AdManagementPath.BASE + AdManagementPath.DISTRIBUTION %>',
                data: JSON.stringify(shopAdDO),
                success: function (data) {
                    $.unblockUI();
                    if (data.result == "SUCCESS") {
                        $.gyzbadmin.alertSuccess("发布成功！", function () {
                            $("#distributionAdModal").modal("hide");
                        }, function () {
                            window.location.reload();
                        });
                    } else {
                        $.gyzbadmin.alertFailure("发布失败！" + data.message);
                    }
                }
            });
        });

        /**
         * @Author LiBin
         * @Description 全选反选
         * @Param
         * @Return
         * @Date 2019/12/12 0012 21:56
         */
        $('#distributionAdCustListTableCheck').click(function () {
            var b = $('#distributionAdCustListTableBody input[type="checkbox"]');
            if ($(this).prop('checked')) {
                b.prop('checked', true);
            } else {
                b.prop('checked', false);
            }
        });
        $('#adManagementCheck').click(function () {
            var b = $('#adManagementCheckList input[type="checkbox"]');
            if ($(this).prop('checked')) {
                b.prop('checked', true);
            } else {
                b.prop('checked', false);
            }
        });
    });

    /**
     * @Author LiBin
     * @Description 门店判断是否选择
     * @Param
     * @Return
     * @Date 2019/12/12 0012 20:46
     */
    function checkAllCust() {
        mchShopDOList = [];
        var checkValue = $('#checkAllCust').prop('checked');
        if (checkValue) {
            $("#distributionAdCustListDIV").hide();
            $('#distributionAdCustListTableBody').html('');
        } else {
            $("#distributionAdCustListDIV").show();
        }
    }

    /**
     * @Author LiBin
     * @Description 重置
     * @Param
     * @Return
     * @Date 2019/12/12 0012 21:44
     */
    function findCustInfoListReset() {
        $('#distributionAdCustDIV #custName').val('');
        $('#distributionAdCustDIV #shopName').val('');
        $('#distributionAdCustListTableBody').html('');
    }

    /**
     * @Author LiBin
     * @Description 查询商户信息
     * @Param
     * @Return
     * @Date 2019/12/12 0012 20:46
     */
    function findCustInfoList() {
        var custName = $('#distributionAdCustDIV #custName').val();
        var shopName = $('#distributionAdCustDIV #shopName').val();
        if (kong.test(custName) && kong.test(shopName)) {
            $.gyzbadmin.alertFailure("商户和门店选一个查询!");
            return;
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            url: window.Constants.ContextPath + '<%=AdManagementPath.BASE + AdManagementPath.ADALLCUSTOMLIST%>',
            data: {
                customName: custName,
                shopName: shopName
            },
            success: function (data) {
                $.unblockUI();
                if (data.result == "SUCCESS") {
                    // console.log(JSON.stringify(data.data))

                    var str = '';
                    data.data.forEach(ele => {
                        str += "<tr>";
                        str += "<td><input type='checkbox' name = 'distributionAdCustListTableCheck' id='distributionAdCustListTableCheckValue' value='" + ele.custId + "#" + ele.shopId + "'/></td>";
                        str += "<td>" + ele.custName + "</td>";
                        str += "<td>" + ele.shopNo + "</td>";
                        str += "<td>" + ele.shopName + "</td>";
                        str += "<td>" + ele.addr + "</td>";
                        str += "/<tr>";
                    });
                    $('#distributionAdCustListTableBody').html(str);
                }
            }
        });
    }

    /**
     * @Author LiBin
     * @Description 动态渲染商户
     * @Param
     * @Return
     * @Date 2019/12/12 0012 16:08
     */
    function menu(object) {
        var htm = '';
        if (null != object && object.length > 0) {
            for (var i = 0; i < object.length; i++) {
                var adCustInfoVO = object[i];
                var adShopInfoVOList = adCustInfoVO.adShopInfoVOList;
                htm = htm + '<li><input type="checkbox" name="chk" value=' + adCustInfoVO.custId + '><h class="textClass">' + adCustInfoVO.custName + '</h>';
                if (adShopInfoVOList != null && adShopInfoVOList.length > 0) {
                    var subHtm = '<ul>';
                    for (var j = 0; j < adShopInfoVOList.length; j++) {
                        var adShopInfoVO = adShopInfoVOList[j];
                        if (adShopInfoVO == null || adShopInfoVO == void 0 || adShopInfoVO.shopId == null || adShopInfoVO.shopId == void 0) {
                            continue;
                        }
                        subHtm += '<li class="class_ad_shop"><input type="checkbox" name="chk" value=' + adShopInfoVO.shopId + '><h class="textClass">' + adShopInfoVO.shopName + '</h></li>'
                    }
                    subHtm += '</ul>';
                    htm = htm + subHtm;
                }
                htm = htm + '</li>';
            }
        }
        return htm;
    }

    function loadAd() {
        $(".search-table #isValid").val($(".search-table #isValidTemp").val());
    }

    function bigImg(obj) {
        $('#showImageDiv #showImage').attr("src", obj.src);
    };

    function adImgUpdate(obj) {
        var divObj = document.getElementById("businessPhotoDiv");
        var imageObj = document.getElementById("businessPhotoImageDiv");
        return previewImage(divObj, imageObj, obj);
    }

    function adImgUpdate(file) {
        commonFileUpload(file, 'editImagePath', 'editUrl', 'businessPhotoDiv');
    }


    function adImgAdd(obj) {
        var divObj = document.getElementById("certAttribute1Div");
        var imageObj = document.getElementById("certAttribute1Image");
        return previewImage(divObj, imageObj, obj);
    }

    //添加广告图片上传
    function adImgAdd(file) {
        commonFileUpload(file, 'addImagePath', 'addUrl', 'certAttribute1Div');
    }

    /*
    * file input file dom object
    * pathTarget 路径保存的input元素id
    * preView 图片预览dom id
    */
    function commonFileUpload(file, pathTarget, urlTarget, preView) {
        var formdata = new FormData();
        formdata.append("file", $(file).get(0).files[0]);
        $.ajax({
            url: window.Constants.ContextPath + '/common/files/uploadPic',
            type: 'post',
            contentType: false,
            data: formdata,
            processData: false,
            async: false,
            success: function (info) {
                $('#' + pathTarget + '').val(info.path);
                $('#' + urlTarget + '').val(info.url);
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

</script>
</html>
