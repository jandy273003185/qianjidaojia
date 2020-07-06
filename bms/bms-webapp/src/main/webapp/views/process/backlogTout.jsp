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

/* $(document).ready(function() { */
	/**
	 *前台缓存数据
	 * ${list} 后台传入的数据
	 */
	/* var myInitiates = ${myInitiateList};
	var merchant = $("tr.merchants");
 	$.each(myInitiates, function(i, value) {
		$.data(merchant[i], "merchants", value);
	}); 

}) */
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
                              <button type="button"  class="btn btn-primary" onclick = "myBacklog()">未处理(${numBack }) </button>
                               <button type="button"  class="btn btn-primary" onclick = "myBacklogTout()">超时(${numTimeOut })</button>
                            </div>
							<div class="list-table-header">待办流程超时</div>
                      	<div class="table-responsive">
						<table id="sample-table-2" class="list-table">
							<thead>
								<tr>
                                    <th><input name="" type="checkbox" value=""></th>
									<th>请求标题</th>
									<th>接收日期</th>
									<th>创建人</th>	
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${myInitiateTimeOutList }" var="merchant1">
                                   <tr class="merchants" >
                                	   <input name="merchant" type="hidden" value="${merchant1} ">
                                       <td><input name="" type="checkbox" value=""></td>
                                       <td>${merchant1.titleName }</td>
                                       
									   <td>
									   	   <c:if test="${merchant1.currentNode =='2'}">
										   	   ${merchant1.processTime }		
										   </c:if>
										   <c:if test="${merchant1.currentNode =='3'}">
										   	   ${merchant1.firstAuditTime }		
										   </c:if>
									   </td>
									   <td>${merchant1.userName }</td>
									   <td>
									   	   <input type="hidden" name="processId" id = "processId" value="${merchant1.processId }" />
                                           <input type="hidden" name="currentNode" id = "currentNode" value="${merchant1.currentNode }" />
                                           <!-- <button type="button" class="btn btn-primary" onclick = "getInitiate(this)" data-toggle='modal'  data-target="#processAuditModel" >审核</button> -->
									   	   <button type="button" class="btn btn-primary" onclick = "getApplyAudit(this);">流程审核</button>
									   </td>
									</tr>
								</c:forEach>
								<c:if test="${empty myInitiateTimeOutList}">
									<tr>
										<td colspan="15" align="center">
											<font style="color: red; font-weight: bold; font-size: 15px;">暂无数据</font>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- 分页 -->
						<c:if test="${not empty myInitiateTimeOutList}">
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
		<div class="modal fade" style="z-index:1040;" id="processAuditModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
							<input type="text" id="firstAuditRecord" name="firstAuditRecord" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	 </tr>
					<tr>
						<td class="td-left" width="18%">审核人：</td>
						<td class="td-right" width="32%">
							<input type="text" id="secondAuditId" name="secondAuditId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
						<td class="td-left" width="18%">审核记录：</td>
						<td class="td-right" width="32%">
							<input type="text" id="secondAuditRecord" name="secondAuditRecord" style="background-color: #EEEEEE;" clasS="width-70"/>
						</td>
				 	 </tr>
				 </table>
				 </div>
		         <div class="modal-footer">
		         	<button type="button" class="btn btn-primary" onclick="pass()">通过</button>
		            <button type="button" class="btn btn-primary" onclick="nopass()">不通过</button>
		            
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
			$('#processAuditModel').on('show.bs.modal', function () {
				$('#processAuditModel #titleName').val(processAudit.titleName);
				$('#processAuditModel #deptId').val(processAudit.deptId);
				$('#processAuditModel #userName').val(data.processAudit.userName);
				$('#processAuditModel #processTime').val(data.processAudit.processTime);
				$('#processAuditModel #emergencyDegree').val(data.processAudit.emergencyDegree);
				$('#processAuditModel #partnerCustId').val(data.processAudit.partnerCustId);
				$('#processAuditModel #custId').val(data.processAudit.custId);
				$('#processAuditModel #shopId').val(data.processAudit.shopId);
				$('#processAuditModel #model').val(data.processAudit.model);
				$('#processAuditModel #demandNum').val(data.processAudit.demandNum);
				$('#processAuditModel #receiveTime').val(data.processAudit.receiveTime);
				$('#processAuditModel #remittance').val(data.processAudit.remittance);
				$('#processAuditModel #remark').val(data.processAudit.remark);
				$('#processAuditModel #mail').val(data.processAudit.mail);
				$('#processAuditModel #consignee').val(data.processAudit.consignee);
				$('#processAuditModel #deliveryAddress').val(data.processAudit.deliveryAddress);
				$('#processAuditModel #contact').val(data.processAudit.contact);
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
function pass(){
        var processId = $("#processId").val();
        var currentNode = $("#currentNode").val();
        var firstAuditRecord = $('#processAuditModel #firstAuditRecord').val();
        var secondAuditRecord = $('#processAuditModel #secondAuditRecord').val();
        var status = "Y";
        $.ajax({
            type:"POST",
            dataType:"json",
            url:window.Constants.ContextPath +'<%="/process/audit" %>?status=' +status,
            data:
                {
                    "processId" 	: processId,
                    "currentNode" 	: currentNode,
                    "firstAuditRecord" 	: firstAuditRecord,
                    "secondAuditRecord" 	: secondAuditRecord
                },
            success:function(data){
                if(data.result=="SUCCESS"){
                    $.gyzbadmin.alertSuccess("审核完成！",null,function(){
                    	window.close();
                    	window.location.href = window.Constants.ContextPath + '<%="/process/backlog" %>';
                    });
                }else{
                    $.gyzbadmin.alertFailure("审核失败！"+data.message);
                }
            }
        })

}
function nopass(){
    var processId = $("#processId").val();
    var currentNode = $("#currentNode").val();
    var firstAuditRecord = $('#processAuditModel #firstAuditRecord').val();
    var secondAuditRecord = $('#processAuditModel #secondAuditRecord').val();
    var status = "N";
    $.ajax({
        type:"POST",
        dataType:"json",
        url:window.Constants.ContextPath +'<%="/process/audit" %>?status=' +status,
        data:
            {
                "processId" 	: processId,
                "currentNode" 	: currentNode,
                "firstAuditRecord" 	: firstAuditRecord,
                "secondAuditRecord" 	: secondAuditRecord
            },
        success:function(data){
            if(data.result=="SUCCESS"){
                $.gyzbadmin.alertSuccess("审核完成！",null,function(){
                	window.close();
                	window.location.href =window.Constants.ContextPath + '<%="/process/backlog" %>';
                });
            }else{
                $.gyzbadmin.alertFailure("审核失败！"+data.message);
            }
        }
    })

}
//待办流程
function myBacklog(){
	window.location.href =window.Constants.ContextPath + '<%="/process/backlog" %>';
}
//超时待办
function myBacklogTout(){
	window.location.href =window.Constants.ContextPath + '<%="/process/backlogTout" %>';
}

function getApplyAudit(obj){
	var merchantBean = $(obj).parent().parent().find('input[name="merchant"]').attr("value");
	  var merchant = $.data($(obj).parent().parent()[0],"merchant1");
	  var processId = $(obj).parent().find('input[name="processId"]').val();
	var url=window.Constants.ContextPath+"/process/applyAudit?processId="+processId;
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
  </script>
</html>