<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@page import="com.qifenqian.bms.basemanager.merchant.MerchantPath" %>
<%@ page import="com.qifenqian.bms.basemanager.city.CityPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<script src="<c:url value='/static/topayProfit/layui/layui.js'/>"></script>
<script src="<c:url value='/static/topayProfit/layui/layui.all.js'/>"></script>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
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
<script type="text/javascript">
$(function(){
	$("input[type=file]").each(
		function() {
			var _this = $(this);
			_this.localResizeIMG({
				quality : 0.8,
				success : function(result,file) {

					var att = pre.substr(pre.lastIndexOf("."));
					//压缩后图片的base64字符串
					var base64_string = result.clearBase64;

					$('#'+_this.attr('id')+'temp').val(att+","+base64_string);
					//图片预览
		            var imgObj = $('#'+_this.attr('id')+'ImageDiv');
		            imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show();
		            var width = result.width;
		            var height = result.height;
		            var scale =  width/height;
		            if(width >800){
		            width = 800;
		            height = width / scale;
		            }
		            $(".showDiv").width(width+"px");
		            $(".showDiv").height(height+"px");

		            //优图
		            var param = "{str:\""+base64_string+"\",flag:\""+_this.attr('id')+"\"}"
		    		$.ajax({
	    	   			async:false,
	    	   			type:"POST",
	    	   			contentType:"application/json;charset=utf-8",
	    	   			dataType:"text",
	    	   			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.YOUTU%>',
	    	   	        data:param,
	    	   	        success:function(data){
	    	   	      		var json = eval('(' + data + ')');
	    	   	        	if(json.result=="SUCCESS"){
	    	   	        		 if(_this.attr('id')=="certAttribute1"){                    //身份证
	    	       	  				$("#representativeName").val(json.cardName);
	    	       	  				$("#representativeCertNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="certAttribute2"){ 
	    	       	  				var ss = json.cardValidDate;
	    	       	  				$("#idTermStart").val(ss.substr(0,10));
	    	       	  				$("#idTermEnd").val(ss.substr(11,21));
	    	       	  			}
	    	   	        		 else if(_this.attr('id')=="businessPhoto"){                //营业执照
	    	       	  				$("#businessLicense").val(json.businessLicense);
	    	       	  				$("#businessTermStart").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("#businessTermEnd").val("长期");
	    	       	  				}else{
	    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#custAdd").val(json.legalAddress);
	    	       	  				$("#custName").val(json.companyName);
	    	       	  			}else if(_this.attr('id')=="bankCardPhoto"){                //银行卡
    	       	  					$("#compMainAcct").val(json.creditCardId);
    	       	  				
	    	       	  			}
	    	   				}
	    	   			}
		    	   	});
				}
			});
		}
	);
});



/** 点击预览大图 **/
function bigImg(obj){
    var realWidth;
	var realHeight
	$('#showImageDiv #showImage').attr("src",obj.src).load(function(){
		realWidth = this.width;
		realHeight = this.height;
		var scale =  realWidth/realHeight;
		if(realWidth >800){
			realWidth = 800;
			realHeight = realWidth / scale;
		}
		$("#imageDiv").css("width",realWidth+"px").css("height",realHeight+"px");
	});
}

/** 营业执照点击预览 **/
$('.businessPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("businessPhoto");
	return previewImage(divObj,imageObj,obj);
});

//营业执照上传
function showBusinessPhotoImage(file){
	commonFileUpload(file, 'businessPhotoPath', 'businessPhotoDiv');
}

function exit() {
	if (confirm("您确定要关闭吗？")) {
		window.opener=null;
	
		window.open("","_self");
	
		window.close();
	}
};


function pass(){
    var processId = $("#processId").val();
    var currentNode = $("#currentNode").val();
    var firstAuditRecord = $("#firstAuditRecord").val();
    var secondAuditRecord = $("#secondAuditRecord").val();
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
var firstAuditRecord = $("#firstAuditRecord").val();
var secondAuditRecord = $("#secondAuditRecord").val();
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
				
					<div class="row">
						<div class="col-xs-12">
							<input type="hidden" id="processId" value="${processAudit.processId}" />
							<input type="hidden" id="currentNode" value="${processAudit.currentNode}" />
							<table id="sample-table-2" class="list-table" >
							<tbody>
                        	<tr>
							    <td class="td-left" width="18%"><span style="color:red;">*</span>标题：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="titleName" value="${processAudit.titleName }" style="width:90%">
									<label class="label-tips" id="titleNameLab"></label>
								</td>
								<td class="td-left" width="18%">申请部门：</td>
								<td class="td-right" width="32%">
									<%--<input type="text" id="deptName" value="${processUser.deptName }" style="width:90%" readonly>--%>
									<%--<input type="text" id="deptId" value="${processAudit.deptId }" style="width:90%" readonly>--%>
									<input type="text" id="deptName" value="${processAudit.deptName }" style="width:90%" readonly>
								</td>
							</tr>
							<tr>
								<td class="td-left">申请人：</td>
								<td class="td-right">
									<input type="text" id="userName" value="${processAudit.userName }" style="width:90%" readonly>
								</td>
								<td class="td-left">申请日期：</td>
								<td class="td-right">
								<input type="text" id="processTime" readonly style="width:90%" value="${processAudit.processTime }" >
								</td>
						 	</tr>
                         	<tr>
								<td class="td-left">紧急程度：</td>
								<td class="td-right">
									<input type="text" id="emergencyDegree" readonly style="width:90%" value="${processAudit.emergencyDegree }" >
						        </td>
								<td class="td-left">服务商名称：</td>
								<td class="td-right">
									<input type="text" id="partnerCustId" value="${processAudit.partnerCustId }" style="width:90%">
								</td>
							 </tr>
	                         <tr>
								<td class="td-left">商户名称：</td>
								<td class="td-right">
									<input type="text" id="custId" value="${processAudit.custId }" style="width:90%">
								</td>
								<td class="td-left">门店名称：</td>
								<td class="td-right">
									<input type="text" id="shopId" value="${processAudit.shopId }"  style="width:90%">
								</td>
							 </tr>
	                         <tr>
								<td class="td-left"><span style="color:red;">*</span>规格/型号：</td>
								<td class="td-right">
									<input type="text" id="model" value="${processAudit.model }"  style="width:90%">
								</td>
								<td class="td-left"><span style="color:red;">*</span>需求数量：</td>
								<td class="td-right">
									<input type="text" id="demandNum" value="${processAudit.demandNum }"  style="width:90%">
								</td>
						 	</tr>
                         	<tr>
								<td class="td-left">最迟领取日期：</td>
								<td class="td-right">
									<input type="text" id="receiveTime" value="${processAudit.receiveTime }"  readonly="readonly" />
								</td>
								<td class="td-left"><span style="color:red;">*</span>是否打款：</td>
								<td class="td-right">
									<c:if test="${processAudit.remittance =='0'}">
										未打款
									</c:if>
									<c:if test="${processAudit.remittance =='1'}">
										已打款
									</c:if>
								</td>
						 	</tr>
                          	<tr>
								<td class="td-left">说明备注：</td>
								<td class="td-right">
									<input type="text" id="remark" value="${processAudit.remark }"  readonly="readonly" />
								</td>
								<td class="td-left">是否邮寄：</td>
								<td class="td-right">
									<c:if test="${processAudit.mail =='0'}">
										不邮寄
									</c:if>
									<c:if test="${processAudit.mail =='1'}">
										邮寄
									</c:if>
								</td>
							 </tr>
	                         <tr>
								<td class="td-left">收货人：</td>
								<td class="td-right">
									<input type="text" id="consignee" value="${processAudit.consignee }"  readonly="readonly" />
								</td>
								<td class="td-left">联系方式：</td>
								<td class="td-right">
									<input type="text" id="contact" value="${processAudit.contact }"  readonly="readonly" />
								</td>
						 	 </tr>
						 	 <tr>
								<td class="td-left">收货地址：</td>
								<td class="td-right" colspan="3">
									<input type="text" id="deliveryAddress" value="${processAudit.deliveryAddress }"  readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td class="td-left"><span style="color:red;">*</span>上传附件：</td>
								<td class="td-right">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img src="${processAudit.attachment }" style="width:100%;height:100%;"onclick="bigImg(this);" >
										</label>
									</a>
								</td>
							</tr>
							<tr>
								<c:if test="${processAudit.firstAuditId != null}">
								<td class="td-left" width="18%">审核人：</td>
								<td class="td-right" width="32%">
									<input type="text" id="firstAuditName" value="${processAudit.firstAuditName }"   readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
									<%--<input type="text" id="firstAuditId" value="${processAudit.firstAuditId }"   readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>--%>
								</td>
								<td class="td-left" width="18%">审核记录：</td>
								<td class="td-right" width="32%">
									<input type="text" id="firstAuditRecord" value="${processAudit.firstAuditRecord }" style="background-color: #EEEEEE;" clasS="width-70"/>
								</td>
								</c:if>
						 	 </tr>
							<tr>
								<c:if test="${processAudit.secondAuditId != null}">
									<td class="td-left" width="18%">审核人：</td>
									<td class="td-right" width="32%">
										<input type="text" id="secondAuditName" value="${processAudit.secondAuditName }"   readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>
										<%--<input type="text" id="secondAuditId" value="${processAudit.secondAuditId }"  readonly="readonly" style="background-color: #EEEEEE;" clasS="width-70"/>--%>
									</td>
									<td class="td-left" width="18%">审核记录：</td>
									<td class="td-right" width="32%">
										<input type="text" id="secondAuditRecord" value="${processAudit.secondAuditRecord }" style="background-color: #EEEEEE;" clasS="width-70"/>
									</td>
								</c:if>
						 	 </tr>
							</tbody>
							</table>
							<div style="margin:50px 0 0 0;text-align:center">
								<button type="button" class="btn btn-primary" onclick="pass()">通过</button>
		            			<button type="button" class="btn btn-primary" onclick="nopass()">不通过</button>
								<button type="button"  class="btn btn-default" onclick="exit()">关闭</button>  
							</div>
						</div>
					</div><!-- /.modal -->
				</div><!-- /.page-content -->
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				    <div class="modal-dialog showDiv" >
	         			<div id="showImageDiv" style="width:100%;height:100%;">
				           <img id="showImage" style="width:100%;height:100%;">
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
										
				
  </body>
</html>