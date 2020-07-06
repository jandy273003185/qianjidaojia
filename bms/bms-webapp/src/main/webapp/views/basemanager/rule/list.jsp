<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.rule.RulePath" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>费率管理列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body onload="loadFee()">
		<!-- 费率列表 -->
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
						<input type="hidden"  id="feeType2"   value="${feeType }">
							<form action='<c:url value="<%=RulePath.BASE + RulePath.LIST %>"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" width="20%">费率编号</td>
									<td class="td-right" width="30%">
										<span class="input-icon">
											<input type="text" name="feeCode"   id="feeCode"   value="${feeCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="20%">费率类型</td>
									<td class="td-right" width="30%">
										
											<select  id="feeType"  name="feeType">
											  <option value ="">--请选择--</option>
											  <option value ="Fixed">固定费率</option>
											  <option value="Package">套餐费率</option>
											</select>																					
										
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=RulePath.BASE + RulePath.LIST %>">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
												<button class="btn btn-purple btn-sm btn-margin "  id="clearRule">
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
											<gyzbadmin:function url="<%=RulePath.BASE + RulePath.ADD%>">
												<button class="btn btn-purple btn-sm btn-margin addRule" data-toggle='modal' data-target="#addRuleModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzbadmin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
						<div class="list-table-header">费率列表</div>

							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th style="width:8%;">费率编号</th>
											<th style="width:12%;">费率名称</th>
											<th style="width:7%;">费率类型</th>
											<th style="width:18%;">费率详情</th>
											<th style="width:17%;">费率值</th>
											<th style="width:15%;">费率描述</th>
											<th style="width:10%;">创建人</th>
											<th style="width:5%;">状态</th>
											<th style="width:8%;">操作</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${ruleList }" var="rule">
										<tr class="rule">
											<td>${rule.feeCode }</td>
											<td>${rule.feeName }</td>
											<td>
												<c:if test="${rule.feeType=='Fixed'}">固定费率</c:if>
												<c:if test="${rule.feeType=='Package'}">套餐费率</c:if>
											</td>
											<td>${rule.ruleDesc }</td>
											<td>${rule.ruleValues }</td>
											<td>${rule.memo }</td>
											<td>${rule.instUser }</td>
											<td>
												<c:if test="${rule.status=='VALID'}">有效</c:if>
												<c:if test="${rule.status=='DISABLE'}">无效</c:if>
											</td>
											<td>
												<input type="hidden" name="feeCode" value="${rule.feeCode }"/>
												<a href="#"  data-toggle='modal' class="tooltip-error previewRule" data-rel="tooltip" title="预览" data-target="#previewRuleModal">
													<span class="green">
														<i class="iconfont icon-shenhe "></i>
													</span>
												</a>
												<gyzbadmin:function url="<%=RulePath.BASE + RulePath.EDIT%>">
													<a href="#" class="tooltip-success editRule" data-rel="tooltip" title="Edit" data-toggle='modal' data-target="#editRuleModal">
														<span class="green"><i class="icon-edit bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=RulePath.BASE + RulePath.DELETE%>">								
													<a href="#" class="tooltip-error deleteRule" data-rel="tooltip" title="Delete" data-toggle='modal' data-target="#deleteRuleModal">
														<span class="red"><i class="icon-trash bigger-120"></i></span>
													</a>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty ruleList}">
										<tr><td colspan="9" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty ruleList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
				</div>
					<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
	 		   <!-- ADD Qusetion -->
	           <div class="modal fade" id="addRuleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">费率新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
									<tr>
										<td class="td-left" width="20%">费率名称<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="feeName" name="feeName" style="width:70%">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率类型<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<span class="input-icon">
											<input type="radio" name="feeTypeRadio" checked="checked" id="package" value="Package"/> 套餐费率 &nbsp;&nbsp;
											<input type="radio" name="feeTypeRadio" id="fixed" value="Fixed"/> 固定费率
											</span>
										</td>
									</tr>
									 <tr class="fixedRule">
										<td class="td-left" width="20%">费率值<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleValues" name="ruleValues" style="width:20%">%
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间1<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											
											<input type="text" id="ruleMin1" name="ruleMin1" style="width:30%"> - <input type="text" id="ruleMax1" name="ruleMax1" style="width:30%">
											费率值:<input type="text" id="ruleValues1" name="ruleValues1" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间2<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin2" name="ruleMin2" style="width:30%"> - <input type="text" id="ruleMax2" name="ruleMax2" style="width:30%">
											费率值:<input type="text" id="ruleValues2" name="ruleValues2" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间3<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin3" name="ruleMin3" style="width:30%"> - <input type="text" id="ruleMax3" name="ruleMax3" style="width:30%">
											费率值:<input type="text" id="ruleValues3" name="ruleValues3" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间4<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleMin4" name="ruleMin4" style="width:30%"> - <input type="text" id="ruleMax4" name="ruleMax4" style="width:30%">
											费率值:<input type="text" id="ruleValues4" name="ruleValues4" style="width:10%">%
											
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率描述:</td>
										<td class="td-right" colspan="3">
											<textarea rows="2" Style="width:90%" id="memo" name="memo" maxlength="500"></textarea>
										</td>
									</tr>
									
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addRuleBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div><!-- /.modal -->
					
				<!-- Update Rule -->
				<div class="modal fade" id="editRuleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">费率修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="20%">费率编号<span style="color:red">*</span></td>
										<td class="td-right" width="30%">
											<input type="text" id=feeCode name="feeCode" readonly="readonly" style="background:#EEE">
										</td>
										<td class="td-left" width="20%">费率名称<span style="color:red">*</span></td>
										<td class="td-right" width="30%">
											<input type="text" id="feeName" name="feeName" readonly="readonly" style="background:#EEE">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率类型<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="feeType" name="feeType" readonly="readonly" style="background:#EEE">
										</td>
									</tr>
									 <tr class="fixedRule">
										<td class="td-left" width="20%">费率值<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleValues" name="ruleValues" style="width:20%">%
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间1<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											
											<input type="text" id="ruleMin1" name="ruleMin1" style="width:30%"> - <input type="text" id="ruleMax1" name="ruleMax1" style="width:30%">
											费率值:<input type="text" id="ruleValues1" name="ruleValues1" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间2<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin2" name="ruleMin2" style="width:30%"> - <input type="text" id="ruleMax2" name="ruleMax2" style="width:30%">
											费率值:<input type="text" id="ruleValues2" name="ruleValues2" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间3<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin3" name="ruleMin3" style="width:30%"> - <input type="text" id="ruleMax3" name="ruleMax3" style="width:30%">
											费率值:<input type="text" id="ruleValues3" name="ruleValues3" style="width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间4<span style="color:red">*</span></td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleMin4" name="ruleMin4" style="width:30%"> - <input type="text" id="ruleMax4" name="ruleMax4" style="width:30%">
											费率值:<input type="text" id="ruleValues4" name="ruleValues4" style="width:10%">%
											
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率描述:</td>
										<td class="td-right" colspan="3">
											<textarea rows="2" Style="width:90%" id="memo" name="memo" maxlength="500"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editRuleBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
					
					<div class="modal fade" id="previewRuleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">费率预览</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>
										<td class="td-left" width="20%">费率编号:</td>
										<td class="td-right" width="30%">
											<input type="text" id=feeCode name="feeCode" readonly="readonly" style="background:#EEE">
										</td>
										<td class="td-left" width="20%">费率名称:</td>
										<td class="td-right" width="30%">
											<input type="text" id="feeName" name="feeName" readonly="readonly" style="background:#EEE">
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率类型:</td>
										<td class="td-right" colspan="3">
											<input type="text" id="feeType" name="feeType" readonly="readonly" style="background:#EEE">
										</td>
									</tr>
									 <tr class="fixedRule">
										<td class="td-left" width="20%">费率值:</td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleValues" name="ruleValues" readonly="readonly" style="background:#EEE;width:20%">%
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间1:</td>
										<td class="td-right" colspan="3">
											
											<input type="text" id="ruleMin1" name="ruleMin1" readonly="readonly" style="background:#EEE;width:30%"> - <input type="text" id="ruleMax1" name="ruleMax1" readonly="readonly" style="background:#EEE; width:30%">
											费率值:<input type="text" id="ruleValues1" name="ruleValues1" readonly="readonly" style="background:#EEE; width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间2:</td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin2" name="ruleMin2" readonly="readonly" style="background:#EEE; width:30%"> - <input type="text" id="ruleMax2" name="ruleMax2" readonly="readonly" style="background:#EEE; width:30%">
											费率值:<input type="text" id="ruleValues2" name="ruleValues2" style="background:#EEE; width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间3:</td>
										<td class="td-right" colspan="3">
										
											<input type="text" id="ruleMin3" name="ruleMin3" readonly="readonly" style="background:#EEE; width:30%"> - <input type="text" id="ruleMax3" name="ruleMax3" readonly="readonly" style="background:#EEE; width:30%">
											费率值:<input type="text" id="ruleValues3" name="ruleValues3" style="background:#EEE; width:10%">%
											
										</td>
									</tr>
									<tr class="packageRule">
										<td class="td-left" width="20%">费率区间4:</td>
										<td class="td-right" colspan="3">
											<input type="text" id="ruleMin4" name="ruleMin4" readonly="readonly" style="background:#EEE; width:30%"> - <input type="text" id="ruleMax4" name="ruleMax4" readonly="readonly" style="background:#EEE; width:30%">
											费率值:<input type="text" id="ruleValues4" name="ruleValues4" readonly="readonly" style="background:#EEE; width:10%">%
											
										</td>
									</tr>
									<tr>
										<td class="td-left" width="20%">费率描述:</td>
										<td class="td-right" colspan="3">
											<textarea rows="2" readonly="readonly" style="background:#EEE; width:90%" id="memo" name="memo" maxlength="500"></textarea>
										</td>
									</tr>
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->
				<!-- Delete Rule -->
				<div class="modal fade" id="deleteRuleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">费率</h4>
					         </div>
					         <div class="modal-body" align="center">
					         	<font style="color: red; font-weight: bold; font-size: 15px;">您确定要删除该费率[<span class="feeCode"></span>]么？</font>
					         </div>
					         <div class="modal-footer">
					         	<input type="hidden" name="feeCode" class="feeCode" value=""/>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteRuleBtn">确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					    </div>
					</div>
					  
<script type="text/javascript">
	function loadFee(){
		$(".search-table #feeType").val($("#feeType2").val());
	}
	jQuery(function($){
		
		$('#clearRule').click(function(){			
			$(".search-table #feeCode").val("");
			$(".search-table #feeType").val("");
		})
		// 为每个tr缓存数据
		var rules= '<c:out value="${gyzb:toJSONString(ruleList)}" escapeXml="false"/>';
		var questList=$("tr.rule");
		$.each($.parseJSON(rules),function(i,value){
			$.data(questList[i],"rule",value);
		});
		
		$("#addRuleModal .fixedRule").hide();
		$("#addRuleModal #fixed").click(function(){
			$("#addRuleModal .fixedRule").show();
			$("#addRuleModal .packageRule").hide();
			$("#addRuleModal #ruleDesc").val('');
		});
		$("#addRuleModal #package").click(function(){
			$("#addRuleModal .packageRule").show();
			$("#addRuleModal .fixedRule").hide();
			$("#addRuleModal #ruleDesc").val('');
		});
		
		//新增
		$('.addRuleBtn').click(function(){
			
			// 费率名称
			var feeName = $('#addRuleModal #feeName').val();
			if(kong.test(feeName)) {
				$.gyzbadmin.alertFailure('费率名称不可为空');
				return;
			}
			//费率值
			var ruleValues;
			//费率详情
			var ruleDesc;
			
			var feeType = $("input[name='feeTypeRadio']:checked").val();
			
			if(feeType=='Package'){
				// 验证费率区间
				if(kong.test($("#addRuleModal #ruleMin1").val())||kong.test($("#addRuleModal #ruleMax1").val())||kong.test($("#addRuleModal #ruleValues1").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间1');
					return false;
				}
				if(!isNumber($("#addRuleModal #ruleMin1")[0])||!isNumber($("#addRuleModal #ruleMax1")[0])||!isPriceNumber($("#addRuleModal #ruleValues1").val())){
					$.gyzbadmin.alertFailure('费率区间1格式不正确');
					return false;
				}
				if(kong.test($("#addRuleModal #ruleMin2").val())||kong.test($("#addRuleModal #ruleMax2").val())||kong.test($("#addRuleModal #ruleValues2").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间2');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#addRuleModal #ruleMin2")[0])||!isNumber($("#addRuleModal #ruleMax2")[0])||!isPriceNumber($("#addRuleModal #ruleValues2").val())){
					$.gyzbadmin.alertFailure('费率区间2格式不正确');
					return false;
				}
				
				if(kong.test($("#addRuleModal #ruleMin3").val())||kong.test($("#addRuleModal #ruleMax3").val())||kong.test($("#addRuleModal #ruleValues3").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间3');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#addRuleModal #ruleMin3")[0])||!isNumber($("#addRuleModal #ruleMax3")[0])||!isPriceNumber($("#addRuleModal #ruleValues3").val())){
					$.gyzbadmin.alertFailure('费率区间3格式不正确');
					return false;
				}
				if(kong.test($("#addRuleModal #ruleMin4").val())||kong.test($("#addRuleModal #ruleMax4").val())||kong.test($("#addRuleModal #ruleValues4").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间4');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#addRuleModal #ruleMin4")[0])||!isNumber($("#addRuleModal #ruleMax4")[0])||!isPriceNumber($("#addRuleModal #ruleValues4").val())){
					$.gyzbadmin.alertFailure('费率区间4格式不正确');
					return false;
				}
				
				ruleDesc = "ruleMin1:"+$("#addRuleModal #ruleMin1").val()+",ruleMax1:"+$("#addRuleModal #ruleMax1").val()+";ruleMin2:"+$("#addRuleModal #ruleMin2").val()+",ruleMax2:"+$("#addRuleModal #ruleMax2").val()+";ruleMin3:"+$("#addRuleModal #ruleMin3").val()+",ruleMax3:"+$("#addRuleModal #ruleMax3").val()+";ruleMin4:"+$("#addRuleModal #ruleMin4").val()+",ruleMax4:"+$("#addRuleModal #ruleMax4").val();
				
				ruleValues="ruleValue1:"+$("#addRuleModal #ruleValues1").val()+ ",ruleValue2:"+$("#addRuleModal #ruleValues2").val()+",ruleValue3:"+$("#addRuleModal #ruleValues3").val()+",ruleValue4:"+$("#addRuleModal #ruleValues4").val();
				
			}else if(feeType=='Fixed'){
				if(kong.test($("#addRuleModal #ruleValues").val())){
					$.gyzbadmin.alertFailure('费率值不能为空');
					return false;
				}
				if(!isPriceNumber($("#addRuleModal #ruleValues").val())){
					$.gyzbadmin.alertFailure('费率值格式不正确');
					return false;
				}
				ruleDesc="Fixed Rule";
				ruleValues=$("#addRuleModal #ruleValues").val();
			}
			//费率描述
			var memo=$("#addRuleModal #memo").val();
			// 保存
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=RulePath.BASE + RulePath.ADD %>', 
			{
				 'feeName'	: feeName,
				 'ruleDesc' : ruleDesc,
				 'feeType'  : feeType,
				 'ruleValues': ruleValues,
					'memo'	:	memo
			}, function(data) {
					$.unblockUI();   
					if(data.result == 'SUCCESS'){
						$('#addRuleModal').modal('hide');
						$.gyzbadmin.alertSuccess('新增成功', null, function(){
							window.location.reload();
						});
					}else {
						$.gyzbadmin.alertFailure('保存失败:' + data.message);
					}
				}, 'json'
			);
		});
		
		//弹出修改框
	    $('.editRule').click(function(){
			var rule = $.data($(this).parent().parent()[0], 'rule');
	       $('#editRuleModal').on('show.bs.modal', function () {
				$('#editRuleModal #feeCode').val(rule.feeCode);
				$('#editRuleModal #feeName').val(rule.feeName);
				if(rule.feeType=='Fixed'){
					var ruleType="固定费率";	
					$("#editRuleModal .fixedRule").show();
					$("#editRuleModal .packageRule").hide();
					$('#editRuleModal #ruleValues').val(rule.ruleValues);
				}else if(rule.feeType=='Package'){
					var ruleType="套餐费率";
					$("#editRuleModal .fixedRule").hide();
					$("#editRuleModal .packageRule").show();
					// 费率详情 
					var ruleCount= rule.ruleDesc.split(";");
					 for(var i=0; i<ruleCount.length;i++){
						 var min="#editRuleModal #ruleMin"+(i+1).toString();
						 $(min).val(ruleCount[i].split(",")[0].split(":")[1]); 
						var max="#editRuleModal #ruleMax"+(i+1).toString();
						 $(max).val(ruleCount[i].split(",")[1].split(":")[1]);

					 }
					 // 费率值
					 var ruleValCount= rule.ruleValues.split(",");
					 for(var i=0; i<ruleValCount.length;i++){
						 var ruleVal="#editRuleModal #ruleValues"+(i+1).toString();
						 $(ruleVal).val(ruleValCount[i].split(":")[1]);
					 }
					 
				}
				$('#editRuleModal #feeType').val(ruleType);
				$('#editRuleModal #memo').val(rule.memo);
				
			});
	       $('#editRuleModal').on('hide.bs.modal', function () {
				// 清除
				$('#editRuleModal #feeCode').val('');
				$('#editRuleModal #feeName').val('');
				$('#editRuleModal #feeType').val('');
				$('#editRuleModal #memo').val('');
			});
		}); 
		
	    //预览 
	    $('.previewRule').click(function(){
			var rule = $.data($(this).parent().parent()[0], 'rule');
	      $('#previewRuleModal').on('show.bs.modal', function () {
				$('#previewRuleModal #feeCode').val(rule.feeCode);
				$('#previewRuleModal #feeName').val(rule.feeName);
				if(rule.feeType=='Fixed'){
					var ruleType="固定费率";	
					$("#previewRuleModal .fixedRule").show();
					$("#previewRuleModal .packageRule").hide();
					$('#previewRuleModal #ruleValues').val(rule.ruleValues);
				}else if(rule.feeType=='Package'){
					var ruleType="套餐费率";
					$("#previewRuleModal .fixedRule").hide();
					$("#previewRuleModal .packageRule").show();
					// 费率详情 
					var ruleCount= rule.ruleDesc.split(";");
					 for(var i=0; i<ruleCount.length;i++){
						 var min="#previewRuleModal #ruleMin"+(i+1).toString();
						 $(min).val(ruleCount[i].split(",")[0].split(":")[1]); 
						var max="#previewRuleModal #ruleMax"+(i+1).toString();
						 $(max).val(ruleCount[i].split(",")[1].split(":")[1]);

					 }
					 // 费率值
					 var ruleValCount= rule.ruleValues.split(",");
					 for(var i=0; i<ruleValCount.length;i++){
						 var ruleVal="#previewRuleModal #ruleValues"+(i+1).toString();
						 $(ruleVal).val(ruleValCount[i].split(":")[1]);
					 }
					 
				}
				$('#previewRuleModal #feeType').val(ruleType);
				$('#previewRuleModal #memo').val(rule.memo);
				
			});
	       $('#previewRuleModal').on('hide.bs.modal', function () {
				// 清除
				$('#previewRuleModal #feeCode').val('');
				$('#previewRuleModal #feeName').val('');
				$('#previewRuleModal #feeType').val('');
				$('#previewRuleModal #memo').val('');
			});
		}); 
		
	  //弹出修改框
	    $('.editRule').click(function(){
			var rule = $.data($(this).parent().parent()[0], 'rule');
	       $('#editRuleModal').on('show.bs.modal', function () {
				$('#editRuleModal #feeCode').val(rule.feeCode);
				$('#editRuleModal #feeName').val(rule.feeName);
				if(rule.feeType=='Fixed'){
					var ruleType="固定费率";	
					$("#editRuleModal .fixedRule").show();
					$("#editRuleModal .packageRule").hide();
					$('#editRuleModal #ruleValues').val(rule.ruleValues);
				}else if(rule.feeType=='Package'){
					var ruleType="套餐费率";
					$("#editRuleModal .fixedRule").hide();
					$("#editRuleModal .packageRule").show();
					// 费率详情 
					var ruleCount= rule.ruleDesc.split(";");
					 for(var i=0; i<ruleCount.length;i++){
						 var min="#editRuleModal #ruleMin"+(i+1).toString();
						 $(min).val(ruleCount[i].split(",")[0].split(":")[1]); 
						var max="#editRuleModal #ruleMax"+(i+1).toString();
						 $(max).val(ruleCount[i].split(",")[1].split(":")[1]);

					 }
					 // 费率值
					 var ruleValCount= rule.ruleValues.split(",");
					 for(var i=0; i<ruleValCount.length;i++){
						 var ruleVal="#editRuleModal #ruleValues"+(i+1).toString();
						 $(ruleVal).val(ruleValCount[i].split(":")[1]);
					 }
					 
				}
				$('#editRuleModal #feeType').val(ruleType);
				$('#editRuleModal #memo').val(rule.memo);
				
			});
	       $('#editRuleModal').on('hide.bs.modal', function () {
				// 清除
				$('#editRuleModal #feeCode').val('');
				$('#editRuleModal #feeName').val('');
				$('#editRuleModal #feeType').val('');
				$('#editRuleModal #memo').val('');
			});
		}); 
		
		
		
	  //保存修改
		$('.editRuleBtn').click(function(){
			var feeCode=$('#editRuleModal #feeCode').val();
			var feeName =$('#editRuleModal #feeName').val();
			var feeType =$('#editRuleModal #feeType').val();
			
			var ruleValues;
			var ruleDesc;
			if(feeType=="固定费率"){
				feeType="Fixed";
				ruleValues=$('#editRuleModal #ruleValues').val();
				ruleDesc="Fixed Rule";
			}else if(feeType=="套餐费率"){
				feeType="Package";
				// 验证费率区间
				if(kong.test($("#editRuleModal #ruleMin1").val())||kong.test($("#editRuleModal #ruleMax1").val())||kong.test($("#editRuleModal #ruleValues1").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间1');
					return false;
				}
				if(!isNumber($("#editRuleModal #ruleMin1")[0])||!isNumber($("#editRuleModal #ruleMax1")[0])||!isPriceNumber($("#editRuleModal #ruleValues1").val())){
					$.gyzbadmin.alertFailure('费率区间1格式不正确');
					return false;
				}
				if(kong.test($("#editRuleModal #ruleMin2").val())||kong.test($("#editRuleModal #ruleMax2").val())||kong.test($("#editRuleModal #ruleValues2").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间2');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#editRuleModal #ruleMin2")[0])||!isNumber($("#editRuleModal #ruleMax2")[0])||!isPriceNumber($("#editRuleModal #ruleValues2").val())){
					$.gyzbadmin.alertFailure('费率区间2格式不正确');
					return false;
				}
				
				if(kong.test($("#editRuleModal #ruleMin3").val())||kong.test($("#editRuleModal #ruleMax3").val())||kong.test($("#editRuleModal #ruleValues3").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间3');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#editRuleModal #ruleMin3")[0])||!isNumber($("#editRuleModal #ruleMax3")[0])||!isPriceNumber($("#editRuleModal #ruleValues3").val())){
					$.gyzbadmin.alertFailure('费率区间3格式不正确');
					return false;
				}
				if(kong.test($("#editRuleModal #ruleMin4").val())||kong.test($("#editRuleModal #ruleMax4").val())||kong.test($("#editRuleModal #ruleValues4").val())){
					$.gyzbadmin.alertFailure('请输入完整的费率区间4');
					return false;
				}
				// 验证费率区间
				if(!isNumber($("#editRuleModal #ruleMin4")[0])||!isNumber($("#editRuleModal #ruleMax4")[0])||!isPriceNumber($("#editRuleModal #ruleValues4").val())){
					$.gyzbadmin.alertFailure('费率区间4格式不正确');
					return false;
				}
				
				ruleDesc = "ruleMin1:"+$("#editRuleModal #ruleMin1").val()+",ruleMax1:"+$("#editRuleModal #ruleMax1").val()+";ruleMin2:"+$("#editRuleModal #ruleMin2").val()+",ruleMax2:"+$("#editRuleModal #ruleMax2").val()+";ruleMin3:"+$("#editRuleModal #ruleMin3").val()+",ruleMax3:"+$("#editRuleModal #ruleMax3").val()+";ruleMin4:"+$("#editRuleModal #ruleMin4").val()+",ruleMax4:"+$("#editRuleModal #ruleMax4").val();
				
				ruleValues="ruleValue1:"+$("#editRuleModal #ruleValues1").val()+ ",ruleValue2:"+$("#editRuleModal #ruleValues2").val()+",ruleValue3:"+$("#editRuleModal #ruleValues3").val()+",ruleValue4:"+$("#editRuleModal #ruleValues4").val();	
				
			}
			var memo =$('#editRuleModal #memo').val();
			// 保存修改
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=RulePath.BASE + RulePath.EDIT %>', {
			     'feeCode'  : feeCode,
				 'feeName'	: feeName,
				 'ruleDesc' : ruleDesc,
				 'feeType'  : feeType,
				 'ruleValues': ruleValues,
				 'memo'	:	memo
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#editRuleModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('保存修改失败:' + data.message);
					}
				}, 'json'
			);
		});
	  
		// 弹出删除层准备工作
		$('.deleteRule').click(function(){
			var feeCode = $(this).parent().find('input[name="feeCode"]').val();
			$('#deleteRuleModal').on('show.bs.modal', function () {
				// 赋值
				$('#deleteRuleModal span.feeCode').html(feeCode);
				$('#deleteRuleModal input.feeCode').val(feeCode);
			});
			$('#deleteRuleModal').on('hide.bs.modal', function () {
				// 清除
				$('#deleteRuleModal span.feeCode').empty();
				$('#deleteRuleModal input.feeCode').val('');
			});
		});
		
		// 删除角色
		$('.deleteRuleBtn').click(function(){
			var feeCode = $('#deleteRuleModal input.feeCode').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=RulePath.BASE + RulePath.DELETE %>', {
					'feeCode' 	: feeCode
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#deleteRuleModal').modal('hide');
						$.gyzbadmin.alertSuccess('删除成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('删除失败:' + data.message);
					}
				}, 'json'
			);
		});
	
	});
	
	
	
	/**校验费率值**/
	function isPriceNumber(_keyword){  
	    if(_keyword == "0" || _keyword == "0." || _keyword == "0.0" || _keyword == "0.00"){  
	        _keyword = "0"; return true;  
	    }else{  
	        var index = _keyword.indexOf("0");  
	        var length = _keyword.length;  
	        if(index == 0 && length>1){ /*0开头的数字串*/  
	            var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;  
	            if(!reg.test(_keyword)){  
	                return false;  
	            }else{  
	                return true;  
	            }  
	        }else{ /*非0开头的数字*/  
	            var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/;  
	            if(!reg.test(_keyword)){  
	                return false;  
	            }else{  
	                return true;  
	            }  
	        }             
	        return false;  
	    }  
	}  
</script>				  
</body>
</html>

