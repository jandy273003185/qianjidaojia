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
							<form action='<c:url value="/merchant/subAccount/list"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >客户号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="custId"  id="custId"  value="${merchantSubAccouontBean.custId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >外部商户号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="outMerchantCode"  id="outMerchantCode"  value="${merchantSubAccouontBean.outMerchantCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >分账方类型：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="subAccountType"  id="subAccountType"  value="${merchantSubAccouontBean.subAccountType }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="/merchant/subAccount/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="/merchant/subAccount/add">
												<button class="btn btn-purple btn-sm btn-margin addMerchantSubAccount" data-toggle='modal' data-target="#addMerchantSubAccountModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
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
											<th>客户号</th>
											<th>外部商户号</th>
											<th>分账方渠道</th>
											<th>分账方类型</th>
											<th>分账方账号</th>
											<th>分账方全称</th>
											<th>与分账方的关系类型</th>
											<th>微信自定义关系</th>
											<!-- <th>外部请求号</th> -->
											<th>分账描述</th>
											<th>分账比例</th>
											<th>备注</th>
											<th>添加分账方状态</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>修改时间</th>											
											<th>操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${merchantSubAccouontList}" var="merchantSubAccouont">
											<tr class="merchantSubAccount">
												<td>${merchantSubAccouont.custName}</td>
												<td>${merchantSubAccouont.outMerchantCode}</td>
												<td>
													<c:if test="${merchantSubAccouont.channelCode eq 'WX'}">
														微信
													</c:if>
													<c:if test="${merchantSubAccouont.channelCode eq 'ALIPAY'}">
														支付宝
													</c:if>
												</td>
												<td>
													<c:if test="${merchantSubAccouont.channelCode eq 'WX'}">
														<c:forEach items="<%=WeChatProfitSharingType.values()%>" var="status">
															<c:if test="${status eq merchantSubAccouont.subAccountType}">
																${status.text}
															</c:if>
														</c:forEach>
													</c:if>
													<c:if test="${merchantSubAccouont.channelCode eq 'ALIPAY'}">
														<c:forEach items="<%=AliPayProfitSharingType.values()%>" var="status">
															<c:if test="${status eq merchantSubAccouont.subAccountType}">
																${status.text}
															</c:if>
														</c:forEach>
													</c:if>
												</td>																				
												<td>${merchantSubAccouont.account}</td>										
												<td>${merchantSubAccouont.accountName}</td>
												<td>
													<c:if test="${merchantSubAccouont.channelCode eq 'WX'}">
														<c:forEach items="<%=WeChatRelationType.values()%>" var="status">
															<c:if test="${status eq merchantSubAccouont.relationType}">
																${status.text}
															</c:if>
														</c:forEach>
													</c:if>
												</td>
												<td>${merchantSubAccouont.customRelation}</td>											
												<%-- <td>${merchantSubAccouont.outRequestNo}</td> --%>
												<td>${merchantSubAccouont.desc}</td>	
												<td>${merchantSubAccouont.rate}</td>	
												<td>${merchantSubAccouont.remark}</td>
												<td>
													<c:choose>
														<c:when test="${merchantSubAccouont.reportStatus eq '0'}">
															<span class="label label-danger arrowed-in">失败</span>
														</c:when>
														<c:when test="${merchantSubAccouont.reportStatus eq '1'}">
															<span class="label label-success arrowed">成功</span>
														</c:when>
														<c:when test="${merchantSubAccouont.reportStatus eq '99'}">
															<span class="label label-info arrowed-in-right arrowed">提交待审核</span>
														</c:when>
														<c:otherwise>
															未知状态
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:if test="${merchantSubAccouont.status eq '1'}">
														<span class="label label-success arrowed">启用</span>
													</c:if>
													<c:if test="${merchantSubAccouont.status eq '0'}">
														<span class="label label-danger arrowed-in">停用</span>
													</c:if>
												</td>	
												<td>
												<fmt:formatDate value="${merchantSubAccouont.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
												<fmt:formatDate value="${merchantSubAccouont.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
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

										<c:if test="${empty merchantSubAccouontList}">
											<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantSubAccouontList}">
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
					<div class="modal fade" id="addMerchantSubAccountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户分账关系新增</h4>
					         </div>
					         <div class="modal-body">
					         	<form  method="post" id="addSubAccountForm">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">客户号<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select id="custId" name="custId" data-validation="notnull" data-errMsg="客户号不能为空">
												<option value="">输入客户号查询</option>
												<c:forEach items="${tdMerchantDetailInfoList}" var="bean">
													<option data-val="${bean.outMerchantCode }" value="${bean.custId}">${bean.custName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>	
										<td class="td-left" width="30%">分账渠道<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select name="channelCode" id="channelCode" style="width-90;" onchange="changeChannel(this.value)" data-validation="notnull" data-errMsg="分账渠道不能为空" >
												<option value="">--请选择--</option>
												<c:forEach items="<%=ChannelCodeType.values()%>" var="status">
													<option value="${status}">
														${status.text}
													</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>	
										<td class="td-left" width="30%">分账方类型<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select name="subAccountType" id="subAccountType" style="width-90;" data-validation="notnull" data-errMsg="分账方类型不能为空" >
												<option value="">--请先选择分账渠道--</option>
												
											</select>
											
											<div id="WeChatProfitSharingType" style="display: none;">
												<option value="">--请选择--</option>
												<c:forEach items="<%=WeChatProfitSharingType.values()%>" var="status">
													<option value="${status}">
														${status.text}
													</option>
												</c:forEach>
											</div>
											
											<div id="AliPayProfitSharingType" style="display: none;">
												<option value="">--请选择--</option>
												<c:forEach items="<%=AliPayProfitSharingType.values()%>" var="status">
													<option value="${status}">
														${status.text}
													</option>
												</c:forEach>
											</div>
										</td>
									</tr>
									<tr>
										<td class="td-left" >分账方账号<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="account" name="account" clasS="width-90" data-validation="notnull" data-errMsg="分账方账号不能为空" /> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >分账方全称<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="accountName" name="accountName" data-validation="notnull" data-errMsg="分账方全称不能为空" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr id="relationTypeContent">
										<td class="td-left" >与分账方的关系类型<span style="color:red">*</span></td>
										<td class="td-right" >
											<select name="relationType" id="relationType" style="width-90;"  >
												<option value="">--请选择--</option>
												<c:forEach items="<%=WeChatRelationType.values()%>" var="status">
													<option value="${status}">
														${status.text}
													</option>
												</c:forEach>
											</select>
											
								        </td>
									</tr>
									<tr id="customRelationContent">
										<td class="td-left" >微信自定义关系</td>
										<td class="td-right" >
											<input type="text" id="customRelation" name="customRelation" clasS="width-90"/> 										
								        </td>
									</tr>
									<!-- <tr>
										<td class="td-left" >外部请求号</td>
										<td class="td-right" >
											<input type="text" id="outRequestNo" name="outRequestNo" clasS="width-90"/> 										
								        </td>
									</tr> -->
									<tr>
										<td class="td-left" >分账描述<span class="wxRequiredField" style="color:red;display: none;" >*</span></td>
										<td class="td-right" >
											<input type="text" id="desc" name="desc" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >分账比例(百分数)<span style="color:red">*</span></td>
										<td class="td-right" >
											<input type="text" id="rate" name="rate" data-validation="notnull" data-errMsg="分账比例不能为空" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >备注</td>
										<td class="td-right" >
											<input type="text" id="remark" name="remark" clasS="width-90"/> 										
								        </td>
									</tr>
									
					            </table>
					            </form>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMerchantSubAccountBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			
					
					<div class="modal fade" id="deleteMerchantSubAccountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
<script type="text/javascript">
$(function(){
	// 为每个tr缓存数据
	 var merchantSubAccounts= '<c:out value="${gyzb:toJSONString(merchantSubAccouontList)}" escapeXml="false"/>';
	 var merchantSubAccountList=$("tr.merchantSubAccount");
	 $.each($.parseJSON(merchantSubAccounts),function(i,value){		 
	 	$.data(merchantSubAccountList[i],"merchantSubAccount",value);
		}); 
})

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

	//新增
	$('.addMerchantSubAccountBtn').click(function(){
		
		//debugger;
		if(!formValidation('addSubAccountForm')){
			return false;
		}
		
		
		var custId = $('#addMerchantSubAccountModal #custId').val();
		var outMerchantCode = $('#addMerchantSubAccountModal #custId').find("option:selected").attr("data-val");
		var channelCode = $('#addMerchantSubAccountModal #channelCode').val();
		var subAccountType = $('#addMerchantSubAccountModal #subAccountType').val();
		var account = $('#addMerchantSubAccountModal #account').val();
		var accountName = $('#addMerchantSubAccountModal #accountName').val();
		var relationType = $('#addMerchantSubAccountModal #relationType').val();
		var customRelation = $('#addMerchantSubAccountModal #customRelation').val();
		//var outRequestNo = $('#addMerchantSubAccountModal #outRequestNo').val();
		var desc = $('#addMerchantSubAccountModal #desc').val();
		var rate = $('#addMerchantSubAccountModal #rate').val();
		var remark = $('#addMerchantSubAccountModal #remark').val();
		
		if(!(/^\d+(\.\d+)?$/.test(rate))) {
			alert("分账比例必须为数字");
			return false;
		}
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/merchant/subAccount/add",
			data:
			{
				"custId" 	: custId,
				"outMerchantCode" : outMerchantCode,
				"channelCode" : channelCode,
				"subAccountType" : subAccountType,
				"account" : account,
				"accountName" : accountName,
				"relationType" : relationType,
				"customRelation" : customRelation,
				//"outRequestNo" : outRequestNo,
				"desc" : desc,
				"rate" : rate,
				"remark" : remark
			},
			success:function(data){
				$.unblockUI();
				$('#addMerchantSubAccountModal').hide();
				if(data.result == "SUCCESS"){
					alert("新增分账关系成功");
					window.location.reload();
				} else {
					alert(data.message);
				}
			}
		})
		
	})
	
	//弹出停用确认框
	$(".deleteMerchantSubAccount").click(function(){
		//debugger;
		var merchantSubAccount = $.data($(this).parent().parent()[0], 'merchantSubAccount');
		console.log(merchantSubAccount);
		$('#deleteMerchantSubAccountModal').on('show.bs.modal', function () {
			// 赋值
			$('#deleteMerchantSubAccountModal #relationId').val(merchantSubAccount.relationId);
		});
		$('#deleteMerchantSubAccountModal').on('hide.bs.modal', function () {
			// 清除
			$('#deleteMerchantSubAccountModal #relationId').val('');
		});
	})
	
	//删除
	$(".deleteMerchantSubAccountBtn").click(function(){
		var relationId = $('#deleteMerchantSubAccountModal #relationId').val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/merchant/subAccount/delete",
			data:
			{
				"relationId" : relationId
			},
			success:function(data){
				$.unblockUI();
				$('#deleteMerchantSubAccountModal').hide();
				if(data.result == "SUCCESS"){
					alert("停用分账关系成功");
					window.location.reload();
				} else {
					alert(data.message);
				}
			}
		})
	})
	
	function changeChannel(value){
		if(value == "ALIPAY"){
			$("#addMerchantSubAccountModal #subAccountType").html($("#AliPayProfitSharingType").html());
			$("#relationTypeContent").hide();
			$("#addMerchantSubAccountModal #relationType").removeAttr("data-validation").removeAttr("data-errMsg");
			$(".wxRequiredField").hide();
			$("#customRelationContent").hide();
			$("#addMerchantSubAccountModal #desc").removeAttr("data-validation").removeAttr("data-errMsg");
		} else if(value == "WX"){
			$("#addMerchantSubAccountModal #subAccountType").html($("#WeChatProfitSharingType").html());
			$("#relationTypeContent").show();
			$("#addMerchantSubAccountModal #relationType").attr("data-validation", "notnull").attr("data-errMsg", "与分账方的关系类型不能为空");
			$(".wxRequiredField").show();
			$("#customRelationContent").show();
			$("#addMerchantSubAccountModal #desc").attr("data-validation", "notnull").attr("data-errMsg", "分账描述不能为空");
		}
	}
	
	//清空
	$('#clearBtn').click(function(){
		$('#custId').val('');
		$('#outMerchantCode').val('');
		$('#subAccountType').val('');
	})
</script>				
  </body>
</html>