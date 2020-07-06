<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 失败 -->
<div class="modal fade" id="modal-container-common-alert-danger" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="position:fixed;z-index: 9999; margin-top: 100px;">
	<div class="modal-dialog" style="width: 400px;" align="center">
		<div class="alert alert-danger" align="center">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<br/>
			<strong>
				<i class="icon-remove"></i>
			</strong>
			<font id="alertMessage"></font>
			<br/><br/>
		</div>
	</div>
</div>
<!-- 成功 -->
<div class="modal fade" id="modal-container-common-alert-success" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="position:fixed;z-index: 9999;margin-top: 100px;">
	<div class="modal-dialog" style="width: 400px;" align="center">
		<div class="alert alert-block alert-success" align="center">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<br/>
			<strong>
				<i class="icon-ok"></i>	
			</strong>
			<font id="alertMessage"></font>
			<br/><br/>
		</div>
	</div>
</div>