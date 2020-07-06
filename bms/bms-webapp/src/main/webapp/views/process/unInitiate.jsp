<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>流程管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script type="text/javascript">

$(document).ready(function() {
	/**
	 *前台缓存数据
	 * ${list} 后台传入的数据
	 */
	var myInitiates = ${myUnInitiateList};
	var merchant = $("tr.merchants");
 	$.each(myInitiates, function(i, value) {
		$.data(merchant[i], "merchants", value);
	}); 

})
</script>	

<body>
	<!-- 流程信息 -->
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
				
						<div class="col-xs-12">
							<div style="margin:30px 0 10px 0">
							   <button type="button"  class="btn btn-primary" onclick = "myInitiate()" >全部(${num }) </button>
                               <button type="button"  class="btn btn-primary" onclick = "myArchivedInitiate()">已归档(${num1 })</button>
                               <button type="button"  class="btn btn-primary" onclick = "myUnInitiate()">未归档(${num2 }) </button>
                            </div>
							<div class="list-table-header">我的发起未归档列表</div>
                      	<div class="table-responsive">
						<table id="sample-table-2" class="list-table">
							<thead>
								<tr>
									<th>请求标题</th>
									<th>创建人</th>
									<th>发起日期</th>
									<th>当前节点</th>		
									<th>操作</th>
								</tr>
							</thead>
							<tbody id ="unInitiate" style:"display:none">
								<c:forEach items="${myUnInitiateList }" var="merchant1">
                                   <tr class="merchants" >
                                	   <input name="merchant" type="hidden" value="${merchant1} ">
                                       <td>${merchant1.titleName }</td>
                                       <td>${merchant1.userName }</td>
									   <td>${merchant1.processTime }</td>
									   <td>
									   	   <c:if test="${merchant1.currentNode =='2'}">
										   	   财务待审核		
										   </c:if>
										   <c:if test="${merchant1.currentNode =='3'}">
										   	   财务通过，总办待审核		
										   </c:if>
										   <c:if test="${merchant1.currentNode =='4'}">
										   	   财务审核不通过		
										   </c:if>
										   <c:if test="${merchant1.currentNode =='1'}">
										   	   审核通过		
										   </c:if>
										   <c:if test="${merchant1.currentNode =='0'}">
										   	   审核不通过		
										   </c:if>
									   </td>
									   <td>
									   	   <input type="hidden" name="processId" id = "processId2" value="${merchant1.processId }" />
                                           <!-- <button type="button" class="btn btn-primary" onclick = "getInitiate(this)" data-toggle='modal'  data-target="#processDetailModel" >查看详细</button> -->
									   	   <button type="button" class="btn btn-primary" onclick ="getApplyDetail(this);">流程详细</button>
									   </td>
									</tr>
								</c:forEach>
								<c:if test="${empty myUnInitiateList}">
									<tr>
										<td colspan="15" align="center">
											<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- 分页 -->
						<c:if test="${not empty myUnInitiateList}">
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
		<!-- 渠道弹框 -->
		<div class="modal fade" style="z-index:1040;" id="processDetailModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog" style="width:60%;z-index:90;">
		      <div class="modal-content" style="width:950px;">
		      	 <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        	 <h4 class="modal-title" id="myModalLabel">流程审核</h4>
		         </div>
		         <div class="modal-body">
		         <table id="sample-table-2" class="list-table" >
			         <tr>
					    <td class="td-left" width="18%"><span style="color:red;">*</span>标题：</td>
						<td class="td-right" width="32%">
							<input type="text" id="titleName" name="titleName" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/> 
						</td>
						<td class="td-left" width="18%">申请部门：</td>
						<td class="td-right" width="32%"> 
							<input type="text" id="deptId" name="deptId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
					</tr>
					<tr>
						<td class="td-left" width="18%">申请人：</td>
						<td class="td-right" width="32%">
							<input type="text" id="userName" name="userName" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">申请日期：</td>
						<td class="td-right" width="32%">
							<input type="text" id="processTime" name="processTime" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	</tr>
                       	<tr>
						<td class="td-left" width="18%">紧急程度：</td>
						<td class="td-right" width="32%">
							<input type="text" id="emergencyDegree" name="emergencyDegree" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
				        </td>
						<td class="td-left" width="18%">服务商名称：</td>
						<td class="td-right" width="32%">
							<input type="text" id="partnerCustId" name="partnerCustId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
					 </tr>
                        <tr>
						<td class="td-left" width="18%">商户名称：</td>
						<td class="td-right" width="32%">
							<input type="text" id="custId" name="custId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">门店名称：</td>
						<td class="td-right" width="32%">
							<input type="text" id="shopId" name="shopId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
					 </tr>
                        <tr>
						<td class="td-left" width="18%"><span style="color:red;">*</span>规格/型号： </td>
						<td class="td-right" width="32%">
							<input type="text" id="modelDef" name="modelDef" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%"><span style="color:red;">*</span>需求数量：</td>
						<td class="td-right" width="32%">
							<input type="text" id="demandNum" name="demandNum" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	</tr>
                       	<tr>
						<td class="td-left" width="18%">最迟领取日期：</td>
						<td class="td-right" width="32%">
							<input type="text" id="receiveTime" name="receiveTime" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%"><span style="color:red;">*</span>是否打款：</td>
						<td class="td-right" width="32%">
							<input type="text" id="remittance" name="remittance" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	</tr>
                        	<tr>
						<td class="td-left" width="18%">说明备注：</td>
						<td class="td-right" width="32%">
							<input type="text" id="remark" name="remark" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">是否邮寄：</td>
						<td class="td-right" width="32%">
							<input type="text" id="mail" name="mail" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
					 </tr>
                        <tr>
						<td class="td-left" width="18%">收货人：</td>
						<td class="td-right" width="32%">
							<input type="text" id="consignee" name="consignee" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">联系方式：</td>
						<td class="td-right" width="32%">
							<input type="text" id="contact" name="contact" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	 </tr>
				 	 <tr>
						<td class="td-left" width="18%">收货地址：</td>
						<td class="td-right" width="32%">
							<input type="text" id="deliveryAddress" name="deliveryAddress" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
					</tr>
					<tr id ="businessPhotoImg_" style= "display:">
						<td class="td-left">附件：</td>
						<td class="td-right">
							<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
								<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
									<img src="${processAudit.attachment }" style="width:100%;height:100%;"onclick="bigImg(this);" >
								</label>
							</a>
						</td>
					</tr>
					<tr>
						<td class="td-left" width="18%">审核人：</td>
						<td class="td-right" width="32%">
							<input type="text" id="firstAuditId" name="firstAuditId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">审核记录：</td>
						<td class="td-right" width="32%">
							<input type="text" id="firstAuditRecord" name="firstAuditRecord" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	 </tr>
					<tr>
						<td class="td-left" width="18%">审核人：</td>
						<td class="td-right" width="32%">
							<input type="text" id="secondAuditId" name="secondAuditId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">审核记录：</td>
						<td class="td-right" width="32%">
							<input type="text" id="secondAuditRecord" name="secondAuditRecord" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	 </tr>
				 </table>
				 </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default messageDefault" data-dismiss="modal">取消</button>
		            
		         </div>
		      </div><!-- /.modal-content -->
		   </div>
		</div><!-- /.modal -->
	
	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal"  aria-hidden="true">
		<div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
	    	<div id="showImageDiv" style="width:100%;height:100%;">
	        	<img id="showImage" style="width:100%;height:100%;">
	        </div>
	     </div>
	</div>     									
				
  </body>
  
  <script type="text/javascript">
	
 function getInitiate(obj){ 
	
	  var merchantBean = $(obj).parent().parent().find('input[name="merchant"]').attr("value");
	  var merchant = $.data($(obj).parent().parent()[0],"merchant1");
	  var processId = $(obj).parent().find('input[name="processId"]').val();
	  
	  $.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath+'<%="/process/apply/detail" %>',
			data:
			{
				"processId" 	: processId
			},
			success:function(data){
				$('#processDetailModel').on('show.bs.modal', function () {
					$('#processDetailModel #titleName').val(data.processAudit.titleName);
					$('#processDetailModel #deptId').val(data.processAudit.deptId);
					$('#processDetailModel #userName').val(data.processAudit.userName);
					$('#processDetailModel #processTime').val(data.processAudit.processTime);
					$('#processDetailModel #emergencyDegree').val(data.processAudit.emergencyDegree);
					$('#processDetailModel #partnerCustId').val(data.processAudit.partnerCustId);
					$('#processDetailModel #custId').val(data.processAudit.custId);
					$('#processDetailModel #shopId').val(data.processAudit.shopId);
					$('#processDetailModel #model').val(data.processAudit.model);
					$('#processDetailModel #demandNum').val(data.processAudit.demandNum);
					$('#processDetailModel #receiveTime').val(data.processAudit.receiveTime);
					$('#processDetailModel #remittance').val(data.processAudit.remittance);
					$('#processDetailModel #remark').val(data.processAudit.remark);
					$('#processDetailModel #mail').val(data.processAudit.mail);
					$('#processDetailModel #consignee').val(data.processAudit.consignee);
					$('#processDetailModel #deliveryAddress').val(data.processAudit.deliveryAddress);
					$('#processDetailModel #contact').val(data.processAudit.contact);
					if(null != data.processAudit.firstAuditId){
						$('#processAuditModel #firstAuditId').val(data.processAudit.firstAuditId + data.processAudit.firstAuditTime);
					}
					$('#processAuditModel #firstAuditRecord').val(data.processAudit.firstAuditRecord);
					if(null != data.processAudit.secondAuditId){
						$('#processAuditModel #secondAuditId').val(data.processAudit.secondAuditId + data.processAudit.secondAuditTime);
					}
					$('#processAuditModel #secondAuditRecord').val(data.processAudit.secondAuditRecord);
					
				});
			}
		});
 }
 function getApplyDetail(obj){
		var merchantBean = $(obj).parent().parent().find('input[name="merchant"]').attr("value");
		  var merchant = $.data($(obj).parent().parent()[0],"merchant1");
		  var processId = $(obj).parent().find('input[name="processId"]').val();
		var url=window.Constants.ContextPath+"/process/applyDetail?processId="+processId;
	    var name="window";                        //网页名称，可为空;
	    var iWidth=1200;                          //弹出窗口的宽度;
	    var iHeight=600;                       //弹出窗口的高度;
	    //获得窗口的垂直位置
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    //获得窗口的水平位置
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    var params='width='+iWidth
	        +',height='+iHeight
	        +',top='+iTop
	        +',left='+iLeft;
	    /*  $.blockUI();  */
	    winChild =  window.open(url, name,params);
	}
//我的发起
 function myInitiate(){
 	window.location.href =window.Constants.ContextPath + '<%="/process/initiate" %>';
 }
 //未归档
 function myUnInitiate(){
 	window.location.href =window.Constants.ContextPath + '<%="/process/unInitiate" %>';
 }
 //已归档
 function myArchivedInitiate(){
 	window.location.href =window.Constants.ContextPath + '<%="/process/archivedInitiate" %>';
 	
 }
  </script>
</html>