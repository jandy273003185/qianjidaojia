<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller.TdMerchantTdMerchantCollectDailyPath"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户每日汇集</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script type="text/javascript">
	$(function(){
		$('.clearBank').click(function(){
			$(':input','#form_daily')  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		});
		var listJson= ${querylists};		
		var lists=$("tr.collect");
		$.each(listJson,function(i,value){
			$.data(lists[i],"collect",value);
		});
	});
		
	function load(){
		$("#transType").val($("#transTypehidden").val());
		$("#status").val($("#statushidden").val());
	}
</script>
<body onload="load()">
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
							<form id="form_daily" action='<c:url value="<%=TdMerchantTdMerchantCollectDailyPath.BASE + TdMerchantTdMerchantCollectDailyPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >商户编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantId" id="merchantId"  value="${queryBean.merchantId}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">会计日期</td>
									<td class="td-right">
										 <input type="text" name="beginWorkDate"  id="beginWorkDate" readonly="readonly"  value="${queryBean.beginWorkDate}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
											-
										 <input type="text" name="endWorkDate" id="endWorkDate" readonly="readonly" value="${queryBean.endWorkDate}" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left">产品渠道码</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="channelCode" id="channelCode"  value="${queryBean.channelCode}">
											<i class="icon-leaf blue"></i>
										</span>   
									</td>
								</tr>
								<tr>
									<td class="td-left" >交易类型</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="hidden"  name="transTypehidden" id="transTypehidden"  value="${queryBean.transType}">
											<select id="transType" name="transType">
												<option value="">- 请选择 -</option>
											</select>
										</span>
									</td>
									<td class="td-left" >状态</td>
									<td class="td-right" colspan="3"> 
										<span class="input-icon">
											<input type="hidden"  name="statushidden" id="statushidden"  value="${queryBean.status}">
											<select id="status" name="status">
												<option value="">- 请选择 -</option>
												<option value="WAIT_SETTLE">待清算</option>
												<option value="SETTLE_ING">清算中</option>
												<option value="SETTLED">已清算</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TdMerchantTdMerchantCollectDailyPath.BASE + TdMerchantTdMerchantCollectDailyPath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							<div class="list-table-header">
								商户每日汇集
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="5%">编号</th>
											<th width="5%">商户编号</th>
											<th width="5%">会计日期</th>
											<th width="5%">产品渠道码</th>
											<th width="5%">交易类型</th>
											<th width="5%">笔数</th>
											<th width="5%">应收金额</th>
											<th width="5%">应付金额</th>
											<th width="5%">状态</th>
											<th width="5%">记账日期</th>
											<th width="5%">生成时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${querylist}" var="collect" varStatus="status">
											<tr class="collect" >
												<td>${collect.dailyId}</td>
												<td>${collect.merchantId}</td>
												<td>${collect.workDate}</td>
												<td>${collect.channelCode}</td>
												<td>${collect.transType}</td>
												<td>${collect.transCount}</td>
												<td>${collect.transTotalAmt}</td>
												<td>${collect.transTotalFee}</td>
												<td>${collect.status}</td>
												<td>${collect.instDate}</td>
												<td>${collect.instDatetime}</td>
												<%-- <td>
													<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.UPDATE %>">
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateChannel" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.DELETE %>">
														<a href="#deleteModal"  data-toggle='modal' class="tooltip-error delBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td> --%>
											</tr>
										</c:forEach>
										<c:if test="${empty querylist}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty querylist}">
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
	
	<%-- <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增商户渠道金额限制</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<c:url value="<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.ADD %>"/>' method="post" id="addBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">商户编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="mchId" name="mchId" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">渠道<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="chanel" name="chanel" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">渠道状态<span style="color:red">*</span></td>
							<td class="td-right">
									<select id="chanelStatus" name="chanelStatus" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="wx">微信</option>
												<option value="ali">支付宝</option>
												<option value="sevenpay">七分钱</option>
									</select>
							</td>
						<tr>
							<td class="td-left">每次交易限额</td>
							<td class="td-right">
								<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">交易总限额</td>
							<td class="td-right">
									<input type="text" id="limitTotAmt" name="limitTotAmt" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新商户渠道信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=TdMerchantChannelPath.BASE + TdMerchantChannelPath.UPDATE %>' method="post" id="updateBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">商户编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="mchId" name="mchId"  readonly="readonly" style="width:80%" >
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">渠道<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="chanel" name="chanel"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">渠道状态<span style="color:red">*</span></td>
							<td class="td-right">
									<select id="chanelStatus" name="chanelStatus" class="width-100">
												<option value="">- 请选择 -</option>
												<option value="wx">微信</option>
												<option value="ali">支付宝</option>
												<option value="sevenpay">七分钱</option>
									</select>
							</td>
						<tr>
							<td class="td-left">每次交易限额</td>
							<td class="td-right">
									<input type="text" id="limitPerAmt" name="limitPerAmt" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">总交易限额</td>
							<td class="td-right">
									<input type="text" id="limitTotAmt" name="limitTotAmt"  style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除商户渠道金额信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该商户金额限制[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="mchId" id="mchId">
					         	<input type="hidden" name="chanel" id="chanel">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal --> --%>
</body>
</html>

