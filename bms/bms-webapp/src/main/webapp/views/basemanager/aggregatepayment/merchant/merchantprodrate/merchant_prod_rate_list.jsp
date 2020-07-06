<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller.TdMerchantProdRatePath"%>
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
		var listJson= ${list};		
		var lists=$("tr.rate");
		$.each(listJson,function(i,value){
			$.data(lists[i],"rate",value);
		});
		

		$(".addBtn").click(function(){
			var merAgreementCode = $("#addModal #merAgreementCode").val();
			if(kong.test(merAgreementCode)) {
				$.gyzbadmin.alertFailure('合同编号不可为空');
				return;
			}
			var merCode = $("#addModal #merCode").val();
			if(kong.test(merCode)) {
				$.gyzbadmin.alertFailure('商户编号不可为空');
				return;
			}
			var prodCode = $("#addModal #prodCode").val();
			if(kong.test(prodCode)) {
				$.gyzbadmin.alertFailure('产品编号不可为空');
				return;
			}
			var merAgreeRate = $("#addModal #merAgreeRate").val();
			if(kong.test(merAgreeRate)) {
				$.gyzbadmin.alertFailure('费率不可为空');
				return;
			}
			
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.ADD %>',
				data:
				{
					"merAgreementCode" 		: merAgreementCode,
					"merCode" 		: merCode,
					"prodCode" 	: prodCode,
					"merAgreeRate" 	: merAgreeRate
				},
				success:function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
						$.gyzbadmin.alertSuccess("添加成功！",function(){
							$("#addModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("添加失败！"+data.messgage);
					}
				}
			});
		})
		

		$(".delBank").click(function(){
			var merchantRate = $.data($(this).parent().parent()[0],"rate");
			$("#deleteModal").find("input[name='merAgreementCode']").val(merchantRate.merAgreementCode);
			$("#deleteModal").find("input[name='merCode']").val(merchantRate.merCode);
			$("#deleteModal").find("input[name='prodCode']").val(merchantRate.prodCode);
			$("span.sureDel").text("合同编号："+merchantRate.merAgreementCode+",商户号："+merchantRate.merCode+",产品号："+merchantRate.prodCode);
		});
		
		$(".deleteBtn").click(function(){
			var merAgreementCode = $("#deleteModal #merAgreementCode").val();
			var merCode = $("#deleteModal #merCode").val();
			var prodCode = $("#deleteModal #prodCode").val();
			$.blockUI();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:window.Constants.ContextPath +'<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.DELETE %>',
				data:
				{
					"merAgreementCode" 		: merAgreementCode,
					"merCode" 		: merCode,
					"prodCode"	:prodCode
				},
				success:function(data){
					$.unblockUI();
					if(data.result = 'SUCCESS'){
						$.gyzbadmin.alertSuccess("删除成功！",function(){
							$("#deleteModal").modal("hide");
						},function(){
							window.location.reload();
						});
					}else{
						$.gyzbadmin.alertFailure("删除失败！"+data.messgage);
					}
				}
			});
		});
	});
		
</script>
<body>
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
							<form id="form_daily" action='<c:url value="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" >合同号（协议编号）</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merAgreementCode" id="merAgreementCode"  value="${bean.merAgreementCode}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商品编号</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="merCode" id="merCode"  value="${bean.merCode}">
											<i class="icon-leaf blue"></i>
										</span>   
									</td>
									<td class="td-left" >产品编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="prodCode" id="prodCode"  value="${bean.prodCode}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								
									
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button class="btn btn-purple btn-sm btn-margin clearBank" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											<gyzbadmin:function url="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button>
											</gyzbadmin:function>
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
											<th width="5%">合同号</th>
											<th width="5%">商户编号</th>
											<th width="5%">产品编号</th>
											<th width="5%">费率</th>
											<th width="5%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listBean}" var="varbean" varStatus="status">
											<tr class="rate" >
												<td>${varbean.merAgreementCode}</td>
												<td>${varbean.merCode}</td>
												<td>${varbean.prodCode}</td>
												<td>${varbean.merAgreeRate}</td>
												<td>
													<gyzbadmin:function url="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.UPDATE%>">
														<a href="#updateModal"  data-toggle='modal' class="tooltip-success updateChannel" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.DELETE%>">
														<a href="#deleteModal"  data-toggle='modal' class="tooltip-error delBank" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty listBean}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty listBean}">
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
	
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增商户产品费率</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<c:url value="<%=TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.ADD %>"/>' method="post" id="addForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">合同编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="merAgreementCode" name="merAgreementCode" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">商户编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="merCode" name="merCode" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">产品编号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="prodCode" name="prodCode" style="width:80%">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">费率<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="merAgreeRate" name="merAgreeRate" style="width:80%">
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
		
		<%-- <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
		</div><!-- /.modal --> --%>
		
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
					         	<input type="hidden" name="merAgreementCode" id="merAgreementCode">
					         	<input type="hidden" name="merCode" id="merCode">
					         	<input type="hidden" name="prodCode" id="prodCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal -->
</body>
</html>

