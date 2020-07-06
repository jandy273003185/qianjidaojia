<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>商户通道管理</title>
<style type="text/css">
table {
 text-align: center; vertical-align: middle;
}
</style>
<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/**
		 *前台缓存数据
		 * ${list} 后台传入的数据
		 */
		var channels = ${list};
		var channel = $("tr.result");
	 	$.each(channels, function(i, value) {
			$.data(channel[i], "result", value);
		}); 

		//激活商户详情回显信息
		$(".activate").click(function() {
			var channel = $.data($(this).parent().parent()[0], "result");
			

			$('#activeChannel').on('show.bs.modal', function() {
				$("#activeChannel .channelName").val(channel.channelName);
				$("#activeChannel .custId").val(channel.custId);
				$("#activeChannel .merchantChannelId").val(channel.merchantChannelId);

				$("span.ACustName").text(channel.custName + "-");
				$("span.AChannelName").text(channel.channelName +'('+channel.channelName+')');

			});
		});

		//通道下线商户详情回显信息
		$(".deactivate").click(function() {
			var channel = $.data($(this).parent().parent()[0], "result");
			$('#deactiveChannel').on('show.bs.modal', function() {
				$("#deactiveChannel .channelName").val(channel.channelName);
				$("#deactiveChannel .custId").val(channel.custId);
				$("#deactiveChannel .merchantChannelId").val(channel.merchantChannelId);

				$("span.deCustName").text(channel.custName + "-");
				$("span.deChannelName").text(channel.channelName+'('+channel.channelName+')');

			});
		});
		
		
		//清空搜索栏
		/* function cleanSearchBar() { */
		$('.cleanSearchBar').click(function(){
			//商户七分钱编号
			$(".page-content #merchantCode").val('');
			//商户七分钱名称
			$(".page-content #custName").val('');
			//商户渠道编号
			$(".page-content #merchantChannelId").val("");
			//渠道名
			$(".page-content #channelName").val("");
		});

	});

	

	
	/**
	 * 根据商户custId, 商户渠道编号, 渠道编码 才能确定某一条记录
	 *  custId 商户七分钱ID
	 *  merchantChannelCode 商户在渠道的ID
	 *  channelName 渠道编号
	 */
	function edit(url, custId, merchantChannelId, channelName) {
		var param = {
			'custId' : custId,
			'merchantChannelId' : merchantChannelId,
			'channelName' : channelName
		};

		post(url, param);
	}

	function post(URL, PARAMS) {
		var temp_form = document.createElement("form");
		temp_form.action = URL;
		temp_form.target = "_blank";
		temp_form.method = "post";
		temp_form.style.display = "none";
		for ( var x in PARAMS) {
			var opt = document.createElement("textarea");
			opt.name = x;
			opt.value = PARAMS[x];
			temp_form.appendChild(opt);
		}

		document.body.appendChild(temp_form);
		temp_form.submit();
	}

	function hiddenDetail(obj) {
		//alert($(obj).parent().parent().parent().parent().next().html());
		$(obj).parent().parent().parent().parent().next().css("display", "none");

		var html = '<a href="javascript:;" class="tooltip-error" title="显示详情"><span class="green">'
				+ '	<i class="icon-chevron-down bigger-120" onClick="showDetail(this);"></i></span></a>';

		$(obj).parent().parent().replaceWith(html);
		//$(obj).parent().parent().parent().html(html);
		//$(obj).parent().parent().remove();
	}

	function showDetail(obj) {
	
		//alert($(obj).parent().parent().parent().parent().next().html());
		$(obj).parent().parent().parent().parent().next().css("display", "table-row");

		 var html = '<a href="javascript:;" class="tooltip-error" title="隐藏详情"><span class="green">'
				+ '	<i class="icon-chevron-up bigger-120" onClick="hiddenDetail(this);"></i></span></a>';

		$(obj).parent().parent().replaceWith(html);
		//$(obj).parent().parent().parent().html(html);
		//$(obj).parent().parent().remove();
	}

	/**
	 * 激活通道
	 */
	function activeChannel() {

		//得到参数
		var channelName = $("#activeChannel .channelName").val();
		var custId = $("#activeChannel .custId").val();
		var merchantChannelId = $("#activeChannel .merchantChannelId").val();

		//拼装请求json
		var param = '{"custId":"' + custId + '","channelName":"' + channelName + '","merchantChannelId":"' + merchantChannelId + '"}';

		//发送ajax 请求激活通道
		$.ajax({
			type : "post",
			url : "/merchant/channel/activate",
			data : {
				"data" : param
			},
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				if ("true" == data.flag) {
					alert(data.data);
					return true;
				} else {
					alert("ERROR ：" + data.data);
					return false;
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});

		//隐藏modal
		$("#activeChannel").modal("hide");
		//刷新当前页面
		window.location.reload();
	}

	/**
	 * 通道下线
	 */
	function deactiveChannel() {

		//得到参数
		var channelName = $("#deactiveChannel .channelName").val();
		var custId = $("#deactiveChannel .custId").val();
		var merchantChannelId = $("#deactiveChannel .merchantChannelId").val();

		//拼装请求json
		var param = '{"custId":"' + custId + '","channelName":"' + channelName + '","merchantChannelId":"' + merchantChannelId + '"}';
		
		//发送ajax 请求激活通道
		$.ajax({
			type : "post",
			url : "/merchant/channel/deactivate",
			data : {
				"data" : param
			},
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				if ("true" == data.flag) {
					alert(data.data);
					return true;
				} else {
					alert("ERROR ：" + data.data);
					return false;
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});

		//隐藏modal
		$("#deactiveChannel").modal("hide");
		//刷新当前页面
		window.location.reload();
	}
</script>
<body>
	<!-- 商户网关维护-->
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<!-- <script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script> -->
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
							<form action='/merchant/channel/list' method="post">
								<table class="search-table">
									<tr>
										<td class="td-left">商户编号</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text" name="merchantCode" value="${queryBean.merchantCode}" id="merchantCode">
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										
										<td class="td-left">商户名称</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text" name="custName" value="${queryBean.custName}" id="custName">
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										
										<td class="td-left">商户渠道编号</td>
										<td class="td-right">
											<span class="input-icon">
												<input type="text" name="merchantChannelId" value="${queryBean.merchantChannelId}" id="merchantChannelId">
												<i class="icon-leaf blue"></i>
											</span>
										</td>
										<td class="td-left">通道名</td>
										<td class="td-right">
											<span class="input-icon">
												<select name="channelName" id="channelName">
													<option value="">--请选择--</option>
													<c:forEach items="${channelNames}" var="item">
														<option value="${item.code}" <c:if test="${queryBean.channelName == item}"> selected="selected"</c:if>>${item.text }</option>
													</c:forEach>
												</select>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
											<span class="input-group-btn">
												<gyzbadmin:function url="/merchant/channel/list">
													<button type="submit" class="btn btn-purple btn-sm">
														查询
														<i class="icon-search icon-on-right bigger-110"></i>
													</button>
												</gyzbadmin:function>
												<button class="btn btn-purple btn-sm btn-margin cleanSearchBar">
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
												<gyzbadmin:function url="/merchant/channel/edit">
													<button class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addMerchantModal" onClick="edit('/merchant/channel/edit','','','' );">
														新增
														<i class="icon-plus-sign icon-on-right bigger-110"></i>
													</button>
												</gyzbadmin:function>
											</span>
										</td>
									</tr>
								</table>
							</form>
							<div class="list-table-header">商户网关维护</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>商户名</th>
											<th>商户号</th>
											<th>通道名称</th>
											<th>商户通道编号</th>
											<th>商户通道KEY</th>
											<th>状态</th>
											<th>注释</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="item">
											<tr class="result">
												<td>${item.custName}</td>
												<td>${item.merchantCode}</td>
												<td>${item.channelName.text}</td>
												<td>${item.merchantChannelId}</td>
												<td>${item.merchantChannelKey}</td>
												<td>${item.status.describe}</td>
												<td>${item.comment }</td>
												<td>
													<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" />
												</td>
												<td>
													<fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm" />
												</td>
												<td>
													<a href="javascript:;" class="tooltip-error" title="查看详情">
														<span class="green">
															<i class="icon-chevron-down bigger-120" onClick="showDetail(this);"></i>
														</span>
													</a>
													<!-- 编辑 -->
													<c:if test="${item.status !='NORMAL'}">
														<gyzbadmin:function url="/merchant/channel/edit">
															<a href="javascript:;" class="tooltip-error" title="Edit">
																<span class="green">
																	<i class="icon-edit bigger-120" onClick="edit('/merchant/channel/edit','${item.custId}','${item.merchantChannelId }','${item.channelName}' );"></i>
																</span>
															</a>
														</gyzbadmin:function>
													</c:if>
													<!-- 激活 -->
													<c:if test="${item.status =='PENDING'}">
														<gyzbadmin:function url="/merchant/channel/activate">
															<a href="#activeChannel" data-toggle='modal' class="tooltip-error  activate" data-rel="tooltip" title="activate">
																<span class="green">
																	<i class="icon-repeat bigger-120"></i>
																</span>
															</a>
														</gyzbadmin:function>
													</c:if>
													<!-- 下线 -->
													<c:if test="${item.status =='NORMAL'}">
														<gyzbadmin:function url="/merchant/channel/deactivate">
															<a href="#deactiveChannel" data-toggle='modal' class="tooltip-error deactivate" data-rel="tooltip" title="deactivate">
																<span class="red">
																	<i class="icon-stop bigger-120"></i>
																</span>
															</a>
														</gyzbadmin:function>
													</c:if>
												</td>
											</tr>
											<!-- detail -->
											<tr style="display: none;">
												<td colspan="10" width="100%">
													<table width="100%" class="list-table">
														<thead>
															<tr>
																<th>支付类型</th>
																<th>支付产品</th>
																<th>微信AppID</th>
																<th>微信Appsecret</th>
																<th>微信子商户号</th>
																<th>支付宝子商户号</th>
																<th>状态</th>
																<th>注释</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${item.details}" var="detail">
																<tr>
																	<td>${detail.channelCode.text }</td>
																	<td>${detail.subCode.text }</td>
																	<td>${detail.wxAppId}</td>
																	<td>${detail.wxAppsecret}</td>
																	<td>${detail.wxChildNo}</td>
																	<td>${detail.zfbChildNo}</td>
																	<td>${detail.status.describe}</td>
																	<td>${detail.comment}</td>
																</tr>
															</c:forEach>
															<c:if test="${empty item.details}">
																<tr>
																	<td colspan="15" align="center">
																		<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font>
																	</td>
																</tr>
															</c:if>
														</tbody>
													</table>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty list}">
											<tr>
												<td colspan="15" align="center">
													<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font>
												</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty list}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div>
			<!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div>
		<!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div>
	<div class="modal fade" id="deactiveChannel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商户渠道下线</h4>
				</div>
				<div class="modal-body" align="center">
					<font style="color: red; font-weight: bold; font-size: 15px;">
						您确定要下线该商户号为[
						<span class="deCustName">-</span>
						<span class="deChannelName">-</span>
						]的商户信息吗？
					</font>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="channelName" class="channelName">
					<input type="hidden" name="custId" class="custId">
					<input type="hidden" name="merchantChannelId" class="merchantChannelId">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick="deactiveChannel()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
	<div class="modal fade" id="activeChannel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商户渠道激活</h4>
				</div>
				<div class="modal-body" align="center">
					<font style="color: red; font-weight: bold; font-size: 15px;">
						您确定要激活商户号为[
						<span class="ACustName">-</span>
						<span class="AChannelName"></span>
						]的商户信息吗？
					</font>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="channelName" class="channelName">
					<input type="hidden" name="custId" class="custId">
					<input type="hidden" name="merchantChannelId" class="merchantChannelId">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary " onClick="activeChannel()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
</body>
</html>
