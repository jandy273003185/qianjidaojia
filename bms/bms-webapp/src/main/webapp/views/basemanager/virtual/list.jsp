<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户交易信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearVirtual').click(function(){
			$('#merchantId').val('');
			$('#beginMonth').val('');
			$('#endMonth').val('');
		})
		
	});
	
	function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyyMM', isShowToday: false, isShowClear: false });  
    }  
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
							<form action='<c:url value="/basemanager/virtual/list"/>' method="post">
							<input type="hidden" id="isFirst" name="isFirst" value="${isFirst}" />
							<table class="search-table">
								<tr>
									<td class="td-left" width="18%">商户编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantId" id="merchantId"  value="${queryBean.merchantId}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="18%">开始时间：</td>
									<td class="td-right" width="32%">
										     <input type="text" name="beginMonth"  id="beginMonth" readonly="readonly"  value="${queryBean.beginMonth }" onfocus="selectMonth()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
									<td class="td-left" width="18%">结束时间：</td>
									<td class="td-right">
										     <input type="text" name="endMonth" id="endMonth" readonly="readonly" value="${queryBean.endMonth }" onfocus="selectMonth()" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											<button class="btn btn-purple btn-sm btn-margin clearVirtual" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">
								总交易金额<span style="color: yellow;">${totalAmt }</span> 元,当前页面交易总金额<span style="color: yellow;">${allSum }</span>元
							</div> 
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="20%">商户编号</th>
											<th width="30%">商户名称</th>
											<th width="10%">交易金额</th>
											<th width="10%">交易状态</th>
											<th width="10%">交易月份</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${infos}" var="tradeInfo" varStatus="status">
											<tr class="bank" >
												<td>${tradeInfo.merchantId }</td>
												<td>${tradeInfo.merchantName }</td>
												<td>${tradeInfo.tradeAmt }</td>
												<td>
												成功
												 </td>
												<td>${tradeInfo.tradeTime }</td>
											</tr>
										</c:forEach>
										<c:if test="${empty infos}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty infos}">
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
	
	<%-- <div class="modal fade" id="addBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增银行</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<c:url value="<%=BankPath.BASE + BankPath.ADD %>"/>' method="post" id="addBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">支付系统行号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="bankCode" name="bankCode" style="width:80%" onkeyup="this.value=this.value.replace(/\D/g,'')">
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">支付系统行别<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="bankType" name="bankType" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="bankName" name="bankName" style="width:80%">
							</td>
						<tr>
							<td class="td-left">7分钱自定义行别</td>
							<td class="td-right">
								<input type="text" id="myBankType" name="myBankType" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">城市编码</td>
							<td class="td-right">
									<input type="text" id="cityCode" onkeyup="this.value=this.value.replace(/\D/g,'')" name="cityCode" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属CCPC代码</td>
							<td class="td-right">
									<input type="text" id="ccpc" name="ccpc" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">排序号</td>
							<td class="td-right">
									<input type="text" id="orderBy" name="orderBy" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBankBtn" >提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		
		<div class="modal fade" id="updateBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">更新银行信息</h4>
		         </div>
		         <div class="modal-body">
		         	<form action='<%=BankPath.BASE + BankPath.UPDATE %>' method="post" id="updateBankForm">
		            <table class="modal-input-table" style="width: 100%;">
		            	<tr>
							<td class="td-left" width="20%">支付系统行号<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
								<input type="text" id="bankCode" name="bankCode"  readonly="readonly" style="width:80%" >
							</td>
						</tr>
						<tr>	
							<td class="td-left" width="20%">支付系统行别<span style="color:red">*</span></td>
							<td class="td-right" width="30%">
									<input type="text" id="bankType" name="bankType"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">银行名称<span style="color:red">*</span></td>
							<td class="td-right">
									<input type="text" id="bankName" name="bankName" style="width:80%" >
							</td>
						<tr>
							<td class="td-left">7分钱自定义行别</td>
							<td class="td-right">
									<input type="text" id="myBankType" name="myBankType" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">城市编码</td>
							<td class="td-right">
									<input type="text" id="cityCode" name="cityCode" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">所属CCPC代码</td>
							<td class="td-right">
									<input type="text" id="ccpc" name="ccpc"  style="width:80%">
							</td>
						</tr>
						<tr>
							<td class="td-left">排序号</td>
							<td class="td-right">
									<input type="text" id="orderBy" name="orderBy" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:80%">
							</td>
						</tr>
		            </table>
		            </form>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBankBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<div class="modal fade" id="deleteBankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">删除银行信息</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该银行[<span class="sureDel"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="bankCode" id="delbankCode">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteBankBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
		</div><!-- /.modal --> --%>
</body>
</html>

