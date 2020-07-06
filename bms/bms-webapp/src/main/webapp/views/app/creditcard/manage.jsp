<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.app.creditcard.CreditCardManagePath" %> 
<html>
<head>
	<meta charset="utf-8" />
	<title>信用卡管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
	<style type="text/css">
		.up_ico,.down_ico{
		    border: 1px solid rgb(67,142,185);
		    width:16px;height: 16px;
		    display: inline-block;
		    border-radius: 16px;
		    position:relative;
		    vertical-align: middle;
		}
		.up_ico:before{
		    content:'';
		    position: absolute;
		    width: 0;
		    height: 0;
		    border-left: 5px solid transparent;
		    border-right: 5px solid transparent;
		    border-bottom: 6px solid rgb(67,142,185);
		    top:1px;
		    left: 50%;
		    margin-left: -5px;
		}
		.up_ico:after{
		    content:'';
		    position: absolute;
		    width: 4px;
		    height: 8px;
		    background-color: rgb(67,142,185);
		    top:5px;
		    left: 50%;
		    margin-left: -2px;
		}
		.down_ico:before{
		    content:'';
		    position: absolute;
		    width: 4px;
		    height: 8px;
		    background-color: rgb(67,142,185);
		    top:2px;
		    left: 50%;
		    margin-left: -2px;
		}
		.down_ico:after{
		    content:'';
		    position: absolute;
		    width: 0;
		    height: 0;
		    border-left: 5px solid transparent;
		    border-right: 5px solid transparent;
		    border-top: 6px solid rgb(67,142,185);
		    top:8px;
		    left: 50%;
		    margin-left: -5px;
		}
	</style>
	<link rel="stylesheet" href="<c:url value='/static/css/combo.select.css' />" />
</head>
<%-- <script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">
$(function(){
	var seleSort;
	var cardLists = ${creditCardList};
	var cardList = $("tr.creditCard");
	$.each(cardLists,function(i,value){
		$.data(cardList[i],"creditCard",value);
	});
	//清除查询条件
	$(".clearCredCard").click(function(){
		$('.search-table #status').val("");
		$('.search-table #cardName').val("");
	});
	//新增信用卡提交按钮
	$(".addBtn").click(function(){
		
		var cardName=$("#addCardModal").find("#cardName").val();
		var linkUrl=$("#addCardModal").find("#linkUrl").val();
		if(kong.test(cardName)){
			$.gyzbadmin.alertFailure("信用卡名称不可为空");
			return;
		}
		if(kong.test(linkUrl)){
			$.gyzbadmin.alertFailure("链接地址不可为空");
			return;
		}
		if(!(linkUrl.substring(0,4)==="http")){
			$.gyzbadmin.alertFailure("请填写以http或https为开头的完整链接,例如 http://www.ccb.com");
			return;
		}
		$.post(window.Constants.ContextPath +"<%=CreditCardManagePath.BASE + CreditCardManagePath.ADD %>",
				{
					'cardName':cardName,
					'linkUrl':linkUrl,
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){

						$.gyzbadmin.alertSuccess("新增成功！",function(){
							$("#addCardModal").modal("hide");
						},function(){
							window.location.reload();
						});
							
						
					}else{
							$.gyzbadmin.alertFailure("新增失败！"+data.message,function(){
								$("#addCardModal").modal("hide");
							});
					}
				},'json'
				);
		
	});
	//编辑信用卡回显信息
	$(".updateCardLink").click(function(){
		 var creditCard = $.data($(this).parent().parent()[0],"creditCard");
	       $('#updateCardModal').on('show.bs.modal', function () {
	    	    $("#updateCardModal #cardId").val(creditCard.cardId);
	    	    $("#updateCardModal #cardName").val(creditCard.cardName);
				$("#updateCardModal #linkUrl").val(creditCard.linkUrl);
				$("#updateCardModal #sort").val(creditCard.sort);
				$("#updateCardModal #status").val(creditCard.status);
			
	       });
		  $('#updateCardModal').on('hide.bs.modal', function () {
			    $("#updateCardModal #cardId").val('');
			    $("#updateCardModal #cardName").val('');
				$("#updateCardModal #linkUrl").val('');
				$("#updateCardModal #sort").val('');
				$("#updateCardModal #status").val('');
			
	       });
		});
	//信用卡编辑提交更新
	$(".updateBtn").click(function(){
		
		var cardName=$("#updateCardModal").find("#cardName").val();
		
		if(kong.test(cardName)){
			$.gyzbadmin.alertFailure("信用卡名称不可为空");
			return;
		}
		
		var linkUrl=$("#updateCardModal").find("#linkUrl").val();
		if(kong.test(linkUrl)){
			$.gyzbadmin.alertFailure("链接地址不可为空");
			return;
		}
		var cardId=$("#updateCardModal").find("#cardId").val();
		var sort=$("#updateCardModal").find("#sort").val();
		var status=$("#updateCardModal").find("#status").val();
		
		
		$.post(window.Constants.ContextPath +"<%=CreditCardManagePath.BASE + CreditCardManagePath.UPDATE %>",
				{
					'cardId':cardId,
					'cardName':cardName,
					'linkUrl':linkUrl,
					'sort':sort,
					'status':status
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){

						$.gyzbadmin.alertSuccess("修改成功！",function(){
							$("#updateCardModal").modal("hide");
						},function(){
							window.location.reload();
						});		
					}else{
							$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
								$("#updateCardModal").modal("hide");
							});
					}
				},'json'
				);
		
	});
	//删除确认提示框
	$(".deleteCardLink").click(function(){
		var creditCard = $.data($(this).parent().parent()[0],"creditCard");		
		$("#deleteCardModal").find("input[name='cardId']").val(creditCard.cardId);
		var textInfo = "[信用卡名称为: " + creditCard.cardName + " ,申请链接为: " + creditCard.linkUrl +" ]";
		$("span.sureDel").text(textInfo);
		
	});
	
	//确认删除
	$(".deleteCardtBtn").click(function(){
		
		var cardId= $("#deleteCardModal #cardId").val();
		$.blockUI();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath +'<%=CreditCardManagePath.BASE + CreditCardManagePath.DELETE %>',
			data:{
				'cardId':cardId
			},
			success:function(data){
				$.unblockUI();
				
				if(data.result=='SUCCESS'){
					
					$.gyzbadmin.alertSuccess("删除成功！",function(){
						$("#deleteCardModal").modal("hide");
					},function(){
						window.location.reload();
					});
				}else{
					$.gyzbadmin.alertFailure("删除失败！"+data.message);
				}
			}
		});
	});
	//排序向上一位
	$(".up_sort").click(function(){
		var creditCard = $.data($(this).parent().parent()[0],"creditCard");
		var newSort = creditCard.sort - 1;
		if(newSort>0){
			changeSort(creditCard,newSort);
		}else{
			$.gyzbadmin.alertFailure("已经是最前排序了",function(){
				
			});
		}
		
	});
	//排序向下一位
	$(".down_sort").click(function(){
		var creditCard = $.data($(this).parent().parent()[0],"creditCard");
		var maxSort = ${count};
		var newSort = creditCard.sort + 1;
		if(newSort <= maxSort){
			changeSort(creditCard,newSort);
		}else{
			$.gyzbadmin.alertFailure("已经是最后排序了",function(){
				
			});
		}
	});
	//手动输入改变排序
	$(".changeSort").click(function(){
		var newSort = $(this).prev().val();
		var creditCard = $.data($(this).parent().parent()[0],"creditCard");	
		changeSort(creditCard,newSort);
	});
	
	//改变排序
	function changeSort(creditCard,newSort){
		$.post(window.Constants.ContextPath +"<%=CreditCardManagePath.BASE + CreditCardManagePath.UPDATE %>",
				{
					'cardId':creditCard.cardId,
					'cardName':creditCard.cardName,
					'linkUrl':creditCard.linkUrl,
					'sort':newSort,
					'status':creditCard.status,
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){

						$.gyzbadmin.alertSuccess("修改成功！",function(){
							window.location.reload();
						});		
					}else{
							$.gyzbadmin.alertFailure("修改失败！"+data.message,function(){
								
							});
					}
				},'json'
				);
		
	}
	
	
});
function onLoadStatus(){
	$('.search-table #status').val($("#searchStatus").val());

	
}
</script>
<body onload="onLoadStatus()">
	<!-- 信用卡申请链接管理-->
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
							<form action='<c:url value="<%=CreditCardManagePath.BASE + CreditCardManagePath.MANAGE %>"/>' method="post">
							<table class="search-table">
								<input type="hidden" value="${queryBean.status }"  id="searchStatus"/>
								<tr>
									<td class="td-left">信用卡名称</td>
									<td class="td-right">
										<span class="input-icon">
											<input type="text"  name="cardName" value="${queryBean.cardName }" id="cardName">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">状态：</td>
									<td class="td-right"> 
									<!-- 	<input type="hidden" id="status" name="status" value="${queryBean.status}" > -->
										<select id="status" name="status" >
											<option value = "">- 请选择  -</option>
										    <option value = "1">- 正常  -</option>
										    <option value = "0">- 隐藏 -</option>    
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">
											<gyzbadmin:function url="<%=CreditCardManagePath.BASE + CreditCardManagePath.MANAGE  %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											</gyzbadmin:function>
											<button  type="button" style="margin-left:10px;margin-right:10px;"class="btn btn-purple btn-sm  clearCredCard"  >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										
											<gyzbadmin:function url="<%=CreditCardManagePath.BASE + CreditCardManagePath.ADD%>">
											<button  class="btn btn-purple btn-sm" data-toggle='modal' data-target="#addCardModal">
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
								信用卡列表
							</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th>信用卡名称</th>
											<th>上线日期</th>
											<th>链接地址</th>
											<th>排序</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${creditCardList}" var="creditCard">
											<tr class="creditCard">
												<td>${creditCard.cardName }</td>
												<td>
													<fmt:formatDate value="${creditCard.createTime }" pattern="yyyy-MM-dd HH:mm"/>	
												</td>
												<td>${creditCard.linkUrl }</td>
												<td>
													<span>第 <c:if test="${creditCard.sort<10 }">0</c:if>${creditCard.sort }<span></span> 条，共 ${count} 条</span>
													<a class="up_sort"> <span class="up_ico"></span></a>
													<a class="down_sort"> <span class="down_ico"> </span></a>
													<select class="selectSort" id="selectSort" style="width:70px;" > 	
														<c:forEach var="i" begin="1" end="${count }">
															<option  value="${i}" <c:if test="${creditCard.sort==i }">selected="selected"</c:if>>${i}</option>
														</c:forEach>
													</select>
													<button type="button" id="changeSort" class="btn btn-primary btn-xs changeSort">修改</button>
												</td>
												<td>
													<c:if test="${creditCard.status==1}">正常</c:if>
													<c:if test="${creditCard.status==0}">隐藏</c:if>
												</td>															
																								
												<td>
													<gyzbadmin:function url="<%=CreditCardManagePath.BASE + CreditCardManagePath.UPDATE%>">
														<a href="#updateCardModal"  data-toggle='modal' class="tooltip-success updateCardLink"  data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=CreditCardManagePath.BASE + CreditCardManagePath.DELETE%>">
														<a href="#deleteCardModal" data-toggle='modal' class="tooltip-error deleteCardLink" data-rel="tooltip" title="Delete">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</gyzbadmin:function>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty creditCardList}">
											<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty creditCardList}">
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
	
	<!-- 添加信用卡链接-->  
	<div class="modal fade" style="margin-top:150px;" id="addCardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">新增信用卡</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
							<tr class="custIdCla">
								<td class="td-left" >信用卡名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="cardName" name="cardName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">链接地址<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="linkUrl" name="linkUrl"  style="width:80%"	>
								</td>
							</tr>
							
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary addBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
	
		<!-- 修改信用卡链接信息 -->  
		<div class="modal fade" style="margin-top:150px;" id="updateCardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">编辑信用卡</h4>
		         </div>
		          <div class="modal-body">
		            <table class="modal-input-table">
							<tr class="custIdCla">
								<input type="hidden" name="cardId" id="cardId" value="1">
								<td class="td-left" >信用卡名称<span style="color:red">*</span></td>
								<td class="td-right" >
									<input type="text" id="cardName" name="cardName"  style="width:80%"	>
								</td>
							</tr>
		            		<tr>
								<td class="td-left" width="30%">链接地址<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<input type="text" id="linkUrl" name="linkUrl"  style="width:80%"	>
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">排序<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<select name="sort" id="sort" style="width:70px;" > 
										<c:forEach var="i" begin="1" end="${count }">
											<option  value="${i}" >${i}</option>
										</c:forEach>
									 </select>		 
								</td>
							</tr>
							<tr>
								<td class="td-left" width="30%">状态<span style="color:red">*</span></td>
								<td class="td-right" width="70%">
									<select name="status" id="status" style="width:70px;">
										<option value="1">正常</option>
										<option value="0">隐藏</option> 
									</select>
								</td>
							</tr>
							
		            </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary updateBtn">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
		<!-- 删除确认 -->
		<div class="modal fade" id="deleteCardModal" style="margin-top:150px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 600px;">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
		            <h4 class="modal-title" id="myModalLabel">删除信用卡信息</h4>
		         </div>
		         <div class="modal-body" align="center">
		         	<font style="font-weight: bold; font-size: 15px;">您确定要删除<span style="color:red;" class="sureDel"></span>的信用卡信息吗？</font>
		         </div>
		         <div class="modal-footer">
		         	<input type="hidden" name="cardId" id="cardId">
		            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            <button type="button" class="btn btn-primary deleteCardtBtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		     </div>
		</div><!-- /.modal -->
</body>
</html>

