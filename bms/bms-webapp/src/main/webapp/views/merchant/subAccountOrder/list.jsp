<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.ChannelCodeType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatProfitSharingType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.WeChatRelationType"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.AliPayProfitSharingType"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户分账管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>


<body>
	<!-- 商户分账信息 -->
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
							<form action='<c:url value="/merchant/subAccountOrder/list"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >内部支付订单编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="orderId"  id="orderId"  value="${MerchantSubAccouontOrderBean.orderId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >渠道分账订单号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="channelSettleId"  id="channelSettleId"  value="${MerchantSubAccouontOrderBean.channelSettleId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >分账状态：</td>
									<td class="td-right" >
										  <select name="status" class="width-90" id="status" >
										  		<option value="">--请选择--</option>
												<option value="INITIAL">初始化</option>
												<option value="FAIL">受理失败</option>
												<option value="ACCEPTED">受理成功户</option>
												<option value="PROCESSING">处理中</option>
												<option value="FINISHED">处理完成</option>
												<option value="CLOSED">处理失败/已关单</option>
										  </select>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="/merchant/subAccountOrder/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<!-- <gyzb-admin:function url="/merchant/subAccount/add">
												<button class="btn btn-purple btn-sm btn-margin addMerchantSubAccount" data-toggle='modal' data-target="#addMerchantSubAccountModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function> -->
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户分账关系列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>内部支付订单编号</th>
											<th>分账渠道</th>
											<th>渠道分账订单号</th>
											
											<th>分账返回信息</th>
											<th>分账描述</th>
											<th>查询次数</th>		
											<th>分账状态</th>				
											<th>创建时间</th>								
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${merchantSubAccouontOrderList}" var="merchantSubAccouont">
											<tr class="merchantSubAccount">
												<td>${merchantSubAccouont.orderId}</td>
												<td>
													<c:if test="${merchantSubAccouont.channel eq 'wxpay'}">
														微信
													</c:if>
													<c:if test="${merchantSubAccouont.channel eq 'alipay'}">
														支付宝
													</c:if>
												</td>
												<td>${merchantSubAccouont.channelSettleId}</td>
												<td>${merchantSubAccouont.msg}</td>
												<td>${merchantSubAccouont.desc}</td>
												<td>${merchantSubAccouont.queryCount}</td>
												<td>
													<c:if test="${merchantSubAccouont.status eq 'INITIAL'}">
														初始化
													</c:if>
													<c:if test="${merchantSubAccouont.status eq 'FAIL'}">
														受理失败
													</c:if>
													<c:if test="${merchantSubAccouont.status eq 'ACCEPTED'}">
														受理成功
													</c:if>
													<c:if test="${merchantSubAccouont.status eq 'PROCESSING'}">
														处理中
													</c:if>
													<c:if test="${merchantSubAccouont.status eq 'FINISHED'}">
														处理完成
													</c:if>
													<c:if test="${merchantSubAccouont.status eq 'CLOSED'}">
														处理失败/已关单
													</c:if>
												</td>
												<td>
													<fmt:formatDate value="${merchantSubAccouont.inputTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<a href="#" class="tooltip-success deleteMerchantSubAccount" data-rel="tooltip" data-toggle='modal' data-target="#deleteMerchantSubAccountModal" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty merchantSubAccouontOrderList}">
											<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantSubAccouontOrderList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
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
							
					
					<!-- <div class="modal fade" id="deleteMerchantSubAccountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">确定要删除吗？</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<input type="hidden" id="relationId" name="relationId" >
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMerchantSubAccountBtn" >确定</button>
					         </div>
					      </div>/.modal-content
					 </div>
				</div>/.modal -->
<script type="text/javascript">

</script>				
  </body>
</html>