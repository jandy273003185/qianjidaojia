<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.upgrade.merchant.MerchantListPath" %>

<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<!-- <script src="<c:url value='/static/js/bootstrap.min.js'/>"></script> -->

<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>商户申请</title>

    <!-- Bootstrap -->
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />" />
	<link rel="stylesheet" href="<c:url value='/static/css/mycss.css' />" />
	<script type="text/javascript">
	function returnList(){
		window.location.href = "<%=MerchantListPath.BASE + MerchantListPath.LIST%>";
	}
	<!--未通过审核-->
	function noPassAudi(){
		var merchantCode = $("#merchantCode").val();
		var box = document.getElementsByName("reason");
		var index = 1;
		var auditInfo = "";
		for (var i=0;i<box.length;i++){
			 if(box[i].checked){
				 auditInfo = auditInfo + index + "," + box[i].value + ";";
				 index++;
			 }
		}
		
		var otherReason = $("#otherReason").val().trim();
		if(otherReason !=null && otherReason !=''){
			 auditInfo = auditInfo + index + "," +otherReason;
		}
		auditInfo = auditInfo.trim();
		if(auditInfo == null || auditInfo ==''){
			alert("请选择或输入审核不通过的原因");
		}else{
			audit(merchantCode,"noPass",auditInfo);
		}
		
	}
	//大图
	function bigImg(obj){	
		 $('#showImageDiv #showImage').attr("src",obj.src);
		
	}
	function imgHidden(){
		window.location.reload(false);
		
		
		
	}
	function passAudit(merchantCode){	
		audit(merchantCode,"pass","审核通过");
	}
	
	function audit(merchantCode,result,auditInfo){	
		$.post(window.Constants.ContextPath +"<%=MerchantListPath.BASE + MerchantListPath.AUDIT %>",
				{
					"merchantId":merchantCode,
					"result" 	:result,
					"auditInfo" :auditInfo
				},
				function(data){
					if(data.result=="success"){
						alert("操作成功");
						window.location.href = "<%=MerchantListPath.BASE + MerchantListPath.LIST%>";  						
					}else{
						alert("操作失败");
					
					}
				},'json'
				);	
	}
		
	</script>
  </head>
  <body>
  
      
        <!--导航-->
        <nav>
            <div class="container-fluid">
                <div class="col-xs-4 navbar-title">     
                </div>  
                <div class="col-xs-2 navbar-name"><a href="#" onclick="returnList()">返回商户列表</a></div>
            </div>  
        </nav>
        <!--内容-->
        <div class="container pt10 pb50">   
                 <h4>商户审核</h4>
                 <div class="grey-line"></div> 
                      <!--联系人-->
                        <div class="form-horizontal mt30 border-grey wid9">
                          <div class="business-list-tit">
                              <h4>联系人</h4>
                          </div>
                         <div class="form-group">
                         	<input type="hidden" id="merchantCode" name="merchantCode" value="${merchantInfo.merchantCode }">
                            <div class="col-xs-4 control-label">联系人姓名：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.agentName }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">手机号码：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.mobile }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">常用邮箱：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.email }</div>
                            </div>
                          </div>
                       </div>
                           
                        <!--商户信息-->     
                        <div class="form-horizontal mt30 border-grey wid9">
                          <div class="business-list-tit">
                              <h4>商户信息</h4>
                          </div>
                          
                          <h5 class="business-list-h5">营业执照</h5>
                          <div class="form-group">
							<div class="col-xs-4 control-label">营业执照照片：</div>
							<div class="col-xs-4 pd0 mt10">
								<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
								     <img src="/upgrade/merchant/showImage?src=${merchantInfo.businessLicenseFPath}" onclick="bigImg(this)" width="150" height="120"> 
								</a>
								
							</div>
						</div>
                          
                          <div class="form-group">
                            <div class="col-xs-4 control-label">营业执照注册号：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.businessLicense }</div>
                            </div>
                          </div>

      					<div class="form-group">
                            <div class="col-xs-4 control-label">营业期限：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.businessTermStart }-
	                               <c:choose>
	                               		<c:when test="${merchantInfo.businessTermEnd == 'forever'}">
	                               			长期
	                               		</c:when>
	                               		<c:otherwise>
	                               			${merchantInfo.businessTermEnd}
	                               		</c:otherwise>
	                               </c:choose>
                               </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">经营范围：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.businessScope }</div>
                            </div>
                          </div>
                          
                          
                          <h5 class="business-list-h5">基本资料</h5>
                           <div class="form-group">
                            <div class="col-xs-4 control-label">商户名称：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.custName }</div>
                            </div>                                    
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">商户简称：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.shortName }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">注册地址：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.custAdd }</div>
                            </div>
                          </div>
                          
                          <h5 class="business-list-h5">企业法人</h5>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">证件持有人类型：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">企业法人</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">注册地址：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.custAdd }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">证件类型：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">
                               		<c:if test="${merchantInfo.representativeCertType == '00'}">
                               			大陆居民身份证
                               		</c:if>
                               		<c:if test="${merchantInfo.representativeCertType == '01'}">
                               			香港居民身份证
                               		</c:if>
                               		<c:if test="${merchantInfo.representativeCertType == '02'}">
                               			澳门居民身份证
                               		</c:if>
                               		<c:if test="${merchantInfo.representativeCertType == '03'}">
                               			台湾居民身份证
                               		</c:if>
                               
                               </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">身份证正面照：</div>
                            <div class="col-xs-4 pd0 mt10">
                           		<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
									<img src="/upgrade/merchant/showImage?src=${merchantInfo.idCardFPath}" onclick="bigImg(this)" width="150" height="120"> 								
								</a>
				
							</div>
                          </div>
                         <div class="form-group">
                            <div class="col-xs-4 control-label">身份证反面照：</div>
                            <div class="col-xs-4 pd0 mt10">
                            	<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
									<img src="/upgrade/merchant/showImage?src=${merchantInfo.idCardOPath}"  onclick="bigImg(this)" width="150" height="120"> 
								</a>
							</div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">证件号码：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.representativeCertNo }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">证件持有人姓名：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.representativeName }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">有效期限：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${idCardstartTime }-${idCardendtime}</div>
                            </div>
                          </div>
                           
                       </div>
                           
                           
                       <!--结算账户-->
                       <div class="form-horizontal mt30 border-grey wid9">
                          <div class="business-list-tit">
                              <h4>结算账户</h4>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">账户类型：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">
                               		对公账户
                               		<%-- <c:if test="${merchantInfo.compMainAcctType == '01'}">
                               			对公账户
                               		</c:if>
                               		<c:if test="${merchantInfo.compMainAcctType == '02'}">
                               			对私账户
                               		</c:if> --%>
                               </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">开户名称：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.bankAcctName }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">开户许可证：</div>
                            <div class="col-xs-4 pd0 mt10">
                            	<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
									<img src="/upgrade/merchant/showImage?src=${merchantInfo.openAccountPath}" onclick="bigImg(this)" width="150" height="120"> 
								</a>
							</div>
                          </div>
                          <c:if test="${merchantInfo.compMainAcctType == '02'}">
								<div class="form-group">
									<div class="col-xs-4 control-label">法人手持银行卡照：</div>
									<div class="col-xs-4 pd0 mt10">
										<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
											<img src="/upgrade/merchant/showImage?src=${merchantInfo.bankCardPhotoPath}"  width="150" height="120" onclick="bigImg(this)">
										</a>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-4 control-label">法人手持身份证照：</div>
									<div class="col-xs-4 pd0 mt10">
										<a data-toggle='modal' class="tooltip-success"  data-target="#previewImageModal" >
											<img src="/upgrade/merchant/showImage?src=${merchantInfo.idCardScPhotoPath}"width="150" height="120" onclick="bigImg(this)">
										</a>
									</div>
								</div>
						  </c:if>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">开户银行：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${bankName }</div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-xs-4 control-label">银行账户：</div>
                            <div class="col-xs-4 pd0">
                               <div class="form-control border-none">${merchantInfo.compMainAcct }</div>
                            </div>
                          </div>
                        </div>
                        <c:if test="${method=='audit'}">
					        <div class="form-horizontal mt30 border-grey wid9">
					         	<div class="modal-footer" >
									<a class="btn btn-default closeBtn" id="notPass" onclick="showReasonOfNotPass('${merchantInfo.merchantCode}')" data-toggle='modal'  data-target="#reasonOfNotPass" >审核不通过</a>
						          	<button type="button" class="btn btn-primary updateMerchantBtn" id="pass"  onclick="passAudit('${merchantInfo.merchantCode}')">审核通过</button>
						    	</div>    
					        </div>
				    	</c:if>	        
        </div>
        
	   <!-- 审核不过原因 -->
		<div class="modal fade" style="width:1350px;" id="reasonOfNotPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:100%;z-index:90;" >
		     <div class="modal-content" style="width:650px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">审核不通过原因</h4>
		         </div>
		         <div class="modal-body">
					<table   style="border-collapse:separate; border-spacing:0px 10px; font-size:15px;" >
						<tr>
							<td>
								<input type="checkbox" name="reason" value="身份证正面照片不清晰"></input><lable style="margin-left:5px;">身份证正面照片不清晰</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="身份证反面照片不清晰"></input><lable style="margin-left:5px;">身份证反面照片不清晰</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="身份证信息与填写身份证号码不符"></input><lable style="margin-left:5px;">身份证信息与填写身份证号码不符</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="银行卡卡号与开户行不匹配"></input><lable style="margin-left:5px;">银行卡卡号与开户行不匹配</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="营业执照已过期"></input><lable style="margin-left:5px;">营业执照已过期</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="营业执照不清晰"></input><lable style="margin-left:5px;">营业执照不清晰</lable>
							</td>
						</tr>
						<tr>
							<td>
							<input type="checkbox" name="reason" value="营业执照信息与填写的营业执照注册号不符"></input><lable style="margin-left:5px;">营业执照信息与填写的营业执照注册号不符</lable>
							</td>
						</tr>
						<tr>
							<td>
							<lable>其他原因</lable>
							</td>
						</tr>
					</table>
					<textarea class="form-control" rows="3" style="resize:none; vertical-align:top" id="otherReason" >
						
					</textarea>
					<div style="margin-top:15px; text-align:right;" >
						<button type="button" class="btn btn-default closeBtn"  id="close" data-dismiss="modal">取消</button>
			     	 	<button type="button" class="btn btn-primary " id="pass"  onclick="noPassAudi()">确定</button>
		     		</div>
		     	 </div><!-- /.modal-content -->
			    </div>
			</div>
		</div>
		
 		 <!-- 图片预览 -->
		<div class="modal fade" id="previewImageModal" style="z-index:1050; width:50%;overflow:visible"tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   	 	<div class="modal-dialog" style="width:100%;height:90%; ">
	         <div  id="showImageDiv" style="width:100%;height:100%; ">
	           <img id="showImage" style="width:100%;height:100%;" onclick="imgHidden()">
	         </div>
	     </div>
		</div> 



  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>