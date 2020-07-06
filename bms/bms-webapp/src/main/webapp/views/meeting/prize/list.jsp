<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.meeting.prize.PrizePath" %> 

<html>
<link rel="stylesheet" href='<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>' />
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<head>
	<meta charset="utf-8" />
	<title>奖项管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href='<c:url value="/static/css/iconfont.css"/>' />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	function loadPrize(){
		$('.search-table #activityId').val($('.search-table #activityIdTemp').val());
	}
	$(function(){
		var prizeListJson = ${prizeList};
		var prizeTrList = $('tr.prize');
		$.each(prizeListJson, function(idx, obj){
			$.data(prizeTrList[idx], 'prize', obj);
		});	
		$('.addRandomAmount').hide();
		$('.clearPrize').click(function(){
			$('.search-table #activityId').val('');
			$('.search-table #prizeName').val('');
		}) ;

		// 弹出层准备工作
		$('.startPrizeLink').click(function(){
			var prize = $.data($(this).parent().parent()[0], 'prize');
			var prizeId = $(this).parent().find('input[name="prizeId"]').val();
			$('#startPrizeModal').on('show.bs.modal', function () {
				// 赋值
				$('#startPrizeModal span.prizeId').html(prize.prizeId);
				$('#startPrizeModal span.prizeName').html(prize.prizeName);
				$('#startPrizeModal input.prizeId').val(prizeId);
			});
			$('#startPrizeModal').on('hide.bs.modal', function () {
				// 清除
				$('#startPrizeModal span.prizeId').empty();
				$('#startPrizeModal span.prizeName').empty();
				$('#startPrizeModal input.prizeId').val('');
			});
		});

		// 确认
		$('.startBtn').click(function(){
			var prizeId = $('#startPrizeModal input.prizeId').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=PrizePath.BASE + PrizePath.START %>', {
					'prizeId' 	: prizeId
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#startPrizeModal').modal('hide');
						$.gyzbadmin.alertSuccess('开始成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('开始失败:' + data.message);
					}
				}, 'json'
			);
		});

		// 弹出层准备工作
		$('.resendPrizeLink').click(function(){
			var prize = $.data($(this).parent().parent()[0], 'prize');
			var prizeId = $(this).parent().find('input[name="prizeId"]').val();
			$('#resendPrizeModal').on('show.bs.modal', function () {
				// 赋值
				$('#resendPrizeModal span.prizeId').html(prize.prizeId);
				$('#resendPrizeModal span.prizeName').html(prize.prizeName);
				$('#resendPrizeModal input.prizeId').val(prizeId);
			});
			$('#resendPrizeModal').on('hide.bs.modal', function () {
				// 清除
				$('#resendPrizeModal span.prizeId').empty();
				$('#resendPrizeModal span.prizeName').empty();
				$('#resendPrizeModal input.prizeId').val('');
			});
		});

		// 确认
		$('.resendBtn').click(function(){
			var prizeId = $('#resendPrizeModal input.prizeId').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=PrizePath.BASE + PrizePath.RESEND %>', {
					'prizeId' 	: prizeId
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#resendPrizeModal').modal('hide');
						$.gyzbadmin.alertSuccess('重发成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('重发失败:' + data.message);
					}
				}, 'json'
			);
		});

		// 弹出层准备工作
		$('.endPrizeLink').click(function(){
			var prize = $.data($(this).parent().parent()[0], 'prize');
			var prizeId = $(this).parent().find('input[name="prizeId"]').val();
			$('#endPrizeModal').on('show.bs.modal', function () {
				// 赋值
				$('#endPrizeModal span.prizeId').html(prize.prizeId);
				$('#endPrizeModal span.prizeName').html(prize.prizeName);
				$('#endPrizeModal input.prizeId').val(prizeId);
			});
			$('#endPrizeModal').on('hide.bs.modal', function () {
				// 清除
				$('#endPrizeModal span.prizeId').empty();
				$('#endPrizeModal span.prizeName').empty();
				$('#endPrizeModal input.prizeId').val('');
			});
		});

		// 确认
		$('.endBtn').click(function(){
			var prizeId = $('#endPrizeModal input.prizeId').val();
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=PrizePath.BASE + PrizePath.END %>', {
					'prizeId' 	: prizeId
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#endPrizeModal').modal('hide');
						$.gyzbadmin.alertSuccess('结束成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('结束失败:' + data.message);
					}
				}, 'json'
			);
		});	
		 
     $('.addPrizeBtn').click(function(){
    	 
    	 /** 奖项中文名称 */
			var prizeName = $('#addPrizeModal #prizeName').val();
			if(kong.test(prizeName)) {
				$.gyzbadmin.alertFailure('奖项名称不可为空');
				return;
			}	
			
		/** 奖项索引编号 */
			var prizeNo = $('#addPrizeModal #prizeNo').val();
			if(kong.test(prizeNo)) {
				$.gyzbadmin.alertFailure('奖项编码不可为空');
				return;
			}
			
			var activityId = $('#addPrizeModal #activityId').val();
			
			if(kong.test(activityId)) {
				$.gyzbadmin.alertFailure('活动不可为空');
				return;
			}	
		
			/** 奖项英文编码:SPECIAL/FIRST/... */
			var prizeCode = $('#addPrizeModal #prizeCode').val();
			if(kong.test(prizeCode)) {
				$.gyzbadmin.alertFailure('奖项英文编码不可为空');
				return;
			}	
			var prizeType = $('#addPrizeModal #prizeType').val();
			if(kong.test(prizeType)) {
				$.gyzbadmin.alertFailure('奖项类型不可为空');
				return;
			}	
			var overallControlType = $('#addPrizeModal #overallControlType').val();
			if(kong.test(overallControlType)) {
				$.gyzbadmin.alertFailure('总控类型不可为空');
				return;
			}	
			/** 名额数量 */
			/** 名额数量 */
			var quotaAmount = $('#addPrizeModal #quotaAmount').val();
			var quotaNumber = $('#addPrizeModal #quotaNumber').val();
			//控制限制
			if(overallControlType=='JOINT'){
				if(kong.test(quotaNumber)|| quotaNumber=='0') {
					$.gyzbadmin.alertFailure('请输入总名额');
					return;
				}	
				if(kong.test(quotaAmount)||quotaAmount=='0') {
					$.gyzbadmin.alertFailure('请输入总金额');
					return;
				}	
			}else if(overallControlType=='NUMBER'){
				if(kong.test(quotaNumber)||quotaNumber=='0') {
					$.gyzbadmin.alertFailure('请输入总名额');
					return;
				}	
			}else if(overallControlType=='AMOUNT'){
				if(kong.test(quotaAmount)||quotaAmount =='0') {
					$.gyzbadmin.alertFailure('请输入总金额');
					return;
				}	
			}
			
			var oddsFactor = $('#addPrizeModal #oddsFactor').val();
			if(kong.test(oddsFactor)) {
				$.gyzbadmin.alertFailure('中奖概率因子不可为空');
				return;
			}	
			
			/** 金额设置类型：RANDOM随机/FIXED固定 */
			var amountType = $('#addPrizeModal #amountType').val();
			if(kong.test(amountType)) {
				$.gyzbadmin.alertFailure('金额设置类型不可为空');
				return;
			}
			/** 固定金额大小 */
			var fixedAmount = $('#addPrizeModal #fixedAmount').val();
			/** 随机金额下限 */
			var randomAmountMin = $('#addPrizeModal #randomAmountMin').val();
			/** 随机金额上限 */
			var randomAmountMax = $('#addPrizeModal #randomAmountMax').val();
			if(amountType=='FIXED'){
				if(kong.test(fixedAmount)||fixedAmount=='0') {
					$.gyzbadmin.alertFailure('请输入固定金额');
					return;
				}
			}else if (amountType=='RANDOM'){
				if(kong.test(randomAmountMin)||randomAmountMin=='0') {
					$.gyzbadmin.alertFailure('请输入随机金额下限');
					return;
				}
				if(kong.test(randomAmountMax)||randomAmountMax=='0') {
					$.gyzbadmin.alertFailure('请输入随机金额上限');
					return;
				}
				if(parseInt(randomAmountMin)>parseInt(randomAmountMax)){
					$.gyzbadmin.alertFailure('随机金额上限不能小于下限');
					return;
				}
			}
			/** 排序  */
			var prizeSort = $('#addPrizeModal #prizeSort').val();
			if(kong.test(prizeSort)) {
				$.gyzbadmin.alertFailure('抽奖顺序不可为空');
				return;
			}
			/** 中奖后有效天数 */
			var effectiveHours = $('#addPrizeModal #effectiveHours').val();
			if(kong.test(effectiveHours)) {
				$.gyzbadmin.alertFailure('有效小时不可为空');
				return;
			}
			/** 活动内是否可重复中奖：Y可以/N不刻意 */
			var isRepeatWin = $('#addPrizeModal #isRepeatWin').val();
			if(kong.test(isRepeatWin)) {
				$.gyzbadmin.alertFailure('是否可重复中奖不可为空');
				return;
			}
			/** 可抽奖客户范围：ALL所有/LIMIT有限制的 */
			var custScope = $('#addPrizeModal #custScope').val();
			if(kong.test(custScope)) {
				$.gyzbadmin.alertFailure('抽奖客户范围不可为空');
				return;
			}
			/** 性别限制：ALL所有/MAN男/WOMAN女（cust_scope有限制时填写） */
			var limitSex = $('#addPrizeModal #limitSex').val();
			/** 注册时间开始限制（cust_scope有限制时填写） */
			var limitRegisterDateStart = $('#addPrizeModal #limitRegisterDateStart').val();
			/** 注册时间结束限制（cust_scope有限制时填写） */
			var limitRegisterDateEnd = $('#addPrizeModal #limitRegisterDateEnd').val();
			
			if(custScope=='LIMIT'){
				if(kong.test(limitSex)) {
					$.gyzbadmin.alertFailure('性别限制不可为空');
					return;
				}
				if(kong.test(limitRegisterDateStart)) {
					$.gyzbadmin.alertFailure('注册时间开始限制不可为空');
					return;
				}
				
				if(kong.test(limitRegisterDateEnd)) {
					$.gyzbadmin.alertFailure('注册时间结束限制不可为空');
					return;
				}
			}
			/** 备注 */
			var memo = $('#addPrizeModal #memo').val();
			/** 状态：VALID有效/DISABLE失效 */
			var status = $('#addPrizeModal #status').val();
			if(kong.test(status)) {
				$.gyzbadmin.alertFailure('状态不可为空');
				return;
			}
			
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=PrizePath.BASE + PrizePath.ADD %>', {
				'prizeNo'			:prizeNo,
				'activityId'		:activityId,
				'prizeCode'			:prizeCode,
				'prizeName'			:prizeName,
				'prizeType'			:prizeType,
				'overallControlType':overallControlType,
				'quotaNumber'		:quotaNumber,
				'quotaAmount'		:quotaAmount,
				'oddsFactor'		:oddsFactor,
				'amountType'		:amountType,
				'fixedAmount'		:fixedAmount,
				'randomAmountMin'	:randomAmountMin,
				'randomAmountMax'	:randomAmountMax,
				'prizeSort'			:prizeSort,
				'effectiveHours'	:effectiveHours,
				'isRepeatWin'		:isRepeatWin,
				'custScope'			:custScope,
				'limitSex'			:limitSex,
				'limitRegisterDateStart':limitRegisterDateStart,
				'limitRegisterDateEnd'	:limitRegisterDateEnd,
				'memo'				:memo,
				'status'			:status
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#addPrizeModal').modal('hide');
						$.gyzbadmin.alertSuccess('新增成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure('新增失败:'+data.message, null, function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
			
		});
     /**加载明细**/
	 $('.detailPrizeLink').click(function(){
		 
	   var prize = $.data($(this).parent().parent()[0], 'prize');
	   
	   $('#detailPrizeModal').on('show.bs.modal', function () {
			$('#detailPrizeModal #prizeId').val(prize.prizeId);
			$('#detailPrizeModal #prizeNo').val(prize.prizeNo);
			$('#detailPrizeModal #activityId').val(prize.activityId);
			$('#detailPrizeModal #prizeCode').val(prize.prizeCode);
			$('#detailPrizeModal #prizeName').val(prize.prizeName);
			$('#detailPrizeModal #quotaNumber').val(prize.quotaNumber);
			$('#detailPrizeModal #prizeType').val(prize.prizeType);
			$('#detailPrizeModal #overallControlType').val(prize.overallControlType);
			$('#detailPrizeModal #winNumber').val(prize.winNumber);
			$('#detailPrizeModal #quotaAmount').val(prize.quotaAmount);
			$('#detailPrizeModal #winAmount').val(prize.winAmount);
			$('#detailPrizeModal #oddsFactor').val(prize.oddsFactor);
			$('#detailPrizeModal #activityId').attr("disabled",true);
			if(prize.amountType=='FIXED'){
				  $('.updateFixedAmount').show();
				  $('.updateRandomAmount').hide();
			}
			else if(prize.amountType=='RANDOM'){
				  $('.updateFixedAmount').hide();
				  $('.updateRandomAmount').show();
			}
			$('#detailPrizeModal #amountType').val(prize.amountType);
			$('#detailPrizeModal #fixedAmount').val(prize.fixedAmount);
			$('#detailPrizeModal #randomAmountMin').val(prize.randomAmountMin);
			$('#detailPrizeModal #randomAmountMax').val(prize.randomAmountMax);
			$('#detailPrizeModal #prizeSort').val(prize.prizeSort);
			$('#detailPrizeModal #effectiveHours').val(prize.effectiveHours);
			$('#detailPrizeModal #isRepeatWin').val(prize.isRepeatWin);
			$('#detailPrizeModal #custScope').val(prize.custScope);
			$('#detailPrizeModal #limitSex').val(prize.limitSex);
			$('#detailPrizeModal #limitRegisterDateStart').val(prize.limitRegisterDateStart);
			$('#detailPrizeModal #limitRegisterDateEnd').val(prize.limitRegisterDateEnd);
			$('#detailPrizeModal #memo').val(prize.memo);
			$('#detailPrizeModal #status').val(prize.status);
		});
       $('#detailPrizeModal').on('hide.bs.modal', function () {
    	    $('#detailPrizeModal #prizeId').val('');
			$('#detailPrizeModal #prizeNo').val('');
			$('#detailPrizeModal #activityId').val('');
			$('#detailPrizeModal #prizeCode').val('');
			$('#detailPrizeModal #prizeName').val('');
			$('#detailPrizeModal #quotaNumber').val('');
			$('#detailPrizeModal #prizeType').val('');
			$('#detailPrizeModal #overallControlType').val('');
			$('#detailPrizeModal #winNumber').val('');
			$('#detailPrizeModal #quotaAmount').val('');
			$('#detailPrizeModal #winAmount').val('');
			$('#detailPrizeModal #oddsFactor').val('');
			$('#detailPrizeModal #amountType').val('');
			$('#detailPrizeModal #fixedAmount').val('');
			$('#detailPrizeModal #randomAmountMin').val('');
			$('#detailPrizeModal #randomAmountMax').val('');
			$('#detailPrizeModal #prizeSort').val('');
			$('#detailPrizeModal #effectiveHours').val('');
			$('#detailPrizeModal #isRepeatWin').val('');
			$('#detailPrizeModal #custScope').val('');
			$('#detailPrizeModal #limitSex').val('');
			$('#detailPrizeModal #limitRegisterDateStart').val('');
			$('#detailPrizeModal #limitRegisterDateEnd').val('');
			$('#detailPrizeModal #memo').val('');
			$('#detailPrizeModal #status').val('');
		});
      
	}); 
		
		/**加载修改**/
		 $('.updatePrizeLink').click(function(){
			 
		   var prize = $.data($(this).parent().parent()[0], 'prize');
		   
		   $('#updatePrizeModal').on('show.bs.modal', function () {
				$('#updatePrizeModal #prizeId').val(prize.prizeId);
				$('#updatePrizeModal #prizeNo').val(prize.prizeNo);
				$('#updatePrizeModal #activityId').val(prize.activityId);
				$('#updatePrizeModal #prizeCode').val(prize.prizeCode);
				$('#updatePrizeModal #prizeName').val(prize.prizeName);
				$('#updatePrizeModal #quotaNumber').val(prize.quotaNumber);
				$('#updatePrizeModal #prizeType').val(prize.prizeType);
				$('#updatePrizeModal #overallControlType').val(prize.overallControlType);
				$('#updatePrizeModal #winNumber').val(prize.winNumber);
				$('#updatePrizeModal #quotaAmount').val(prize.quotaAmount);
				$('#updatePrizeModal #winAmount').val(prize.winAmount);
				$('#updatePrizeModal #oddsFactor').val(prize.oddsFactor);
				if(prize.amountType=='FIXED'){
					  $('.updateFixedAmount').show();
					  $('.updateRandomAmount').hide();
				}
				else if(prize.amountType=='RANDOM'){
					  $('.updateFixedAmount').hide();
					  $('.updateRandomAmount').show();
				}
				if(prize.overallTontrolType=='NUMBER'){
					  $('.addControlTypeNumber').show();
					  $('.addControlTypeAmount').hide();
				}
				else if(prize.overallTontrolType=='AMOUNT'){
					  $('.addControlTypeNumber').hide();
					  $('.addControlTypeAmount').show();
				}
				else if(prize.overallTontrolType=='JOINT'){
					  $('.addControlTypeAmount').show();
					  $('.addControlTypeAmount').show();
				}
				$('#updatePrizeModal #amountType').val(prize.amountType);
				$('#updatePrizeModal #fixedAmount').val(prize.fixedAmount);
				$('#updatePrizeModal #randomAmountMin').val(prize.randomAmountMin);
				$('#updatePrizeModal #randomAmountMax').val(prize.randomAmountMax);
				$('#updatePrizeModal #prizeSort').val(prize.prizeSort);
				$('#updatePrizeModal #effectiveHours').val(prize.effectiveHours);
				$('#updatePrizeModal #isRepeatWin').val(prize.isRepeatWin);
				$('#updatePrizeModal #custScope').val(prize.custScope);
				$('#updatePrizeModal #limitSex').val(prize.limitSex);
				$('#updatePrizeModal #limitRegisterDateStart').val(prize.limitRegisterDateStart);
				$('#updatePrizeModal #limitRegisterDateEnd').val(prize.limitRegisterDateEnd);
				$('#updatePrizeModal #memo').val(prize.memo);
				$('#updatePrizeModal #status').val(prize.status);
			});
	       $('#updatePrizeModal').on('hide.bs.modal', function () {
	    	    $('#updatePrizeModal #prizeId').val('');
				$('#updatePrizeModal #prizeNo').val('');
				$('#updatePrizeModal #activityId').val('');
				$('#updatePrizeModal #prizeCode').val('');
				$('#updatePrizeModal #prizeName').val('');
				$('#updatePrizeModal #quotaNumber').val('');
				$('#updatePrizeModal #prizeType').val('');
				$('#updatePrizeModal #overallControlType').val('');
				$('#updatePrizeModal #winNumber').val('');
				$('#updatePrizeModal #quotaAmount').val('');
				$('#updatePrizeModal #winAmount').val('');
				$('#updatePrizeModal #oddsFactor').val('');
				$('#updatePrizeModal #amountType').val('');
				$('#updatePrizeModal #fixedAmount').val('');
				$('#updatePrizeModal #randomAmountMin').val('');
				$('#updatePrizeModal #randomAmountMax').val('');
				$('#updatePrizeModal #prizeSort').val('');
				$('#updatePrizeModal #effectiveHours').val('');
				$('#updatePrizeModal #isRepeatWin').val('');
				$('#updatePrizeModal #custScope').val('');
				$('#updatePrizeModal #limitSex').val('');
				$('#updatePrizeModal #limitRegisterDateStart').val('');
				$('#updatePrizeModal #limitRegisterDateEnd').val('');
				$('#updatePrizeModal #memo').val('');
				$('#updatePrizeModal #status').val('');
			});
	      
		}); 
		
		/**奖项新增**/
		$('.updatePrizeBtn').click(function(){
			
			var prizeId = $('#updatePrizeModal #prizeId').val();
			if(kong.test(prizeId)) {
				$.gyzbadmin.alertFailure('奖项编号不可为空');
				return;
			}	
			/** 奖项中文名称 */
			var prizeName = $('#updatePrizeModal #prizeName').val();
			if(kong.test(prizeName)) {
				$.gyzbadmin.alertFailure('奖项中文名称不可为空');
				return;
			}
			var prizeNo = $('#updatePrizeModal #prizeNo').val();
			if(kong.test(prizeNo)) {
				$.gyzbadmin.alertFailure('奖项编码不可为空');
				return;
			}
			
			var activityId = $('#updatePrizeModal #activityId').val();
			if(kong.test(activityId)) {
				$.gyzbadmin.alertFailure('活动不可为空');
				return;
			}	
			/** 奖项英文编码:SPECIAL/FIRST/... */
			var prizeCode = $('#updatePrizeModal #prizeCode').val();
			if(kong.test(prizeCode)) {
				$.gyzbadmin.alertFailure('奖项英文编码不可为空');
				return;
			}	
			var prizeType = $('#updatePrizeModal #prizeType').val();
			if(kong.test(prizeType)) {
				$.gyzbadmin.alertFailure('奖项类型不可为空');
				return;
			}	
			var overallControlType = $('#updatePrizeModal #overallControlType').val();
			if(kong.test(overallControlType)) {
				$.gyzbadmin.alertFailure('总控类型不可为空');
				return;
			}	
			
			/** 名额数量 */
			var quotaNumber = $('#updatePrizeModal #quotaNumber').val();
			/** 名额数量 */
			var quotaAmount = $('#updatePrizeModal #quotaAmount').val();
			
			//控制限制
			if(overallControlType=='JOINT'){
				if(kong.test(quotaNumber)|| quotaNumber=='0') {
					$.gyzbadmin.alertFailure('请输入总名额');
					return;
				}	
				if(kong.test(quotaAmount)||quotaAmount=='0') {
					$.gyzbadmin.alertFailure('请输入总金额');
					return;
				}	
			}else if(overallControlType=='NUMBER'){
				if(kong.test(quotaNumber)||quotaNumber=='0') {
					$.gyzbadmin.alertFailure('请输入总名额');
					return;
				}	
			}else if(overallControlType=='AMOUNT'){
				if(kong.test(quotaAmount)||quotaAmount =='0') {
					$.gyzbadmin.alertFailure('请输入总金额');
					return;
				}	
			}
			
			var oddsFactor = $('#updatePrizeModal #oddsFactor').val();
			if(kong.test(oddsFactor)) {
				$.gyzbadmin.alertFailure('中奖概率因子不可为空');
				return;
			}	
			/** 金额设置类型：RANDOM随机/FIXED固定 */
			var amountType = $('#updatePrizeModal #amountType').val();
			if(kong.test(amountType)) {
				$.gyzbadmin.alertFailure('金额设置类型不可为空');
				return;
			}
			
			/** 固定金额大小 */
			var fixedAmount = $('#updatePrizeModal #fixedAmount').val();
			/** 随机金额下限 */
			var randomAmountMin = $('#updatePrizeModal #randomAmountMin').val();
			/** 随机金额上限 */
			var randomAmountMax = $('#updatePrizeModal #randomAmountMax').val();
			
			if(amountType=='FIXED'){
				if(kong.test(fixedAmount)||fixedAmount=='0') {
					$.gyzbadmin.alertFailure('请输入固定金额');
					return;
				}
			}else if (amountType=='RANDOM'){
				if(kong.test(randomAmountMin)||randomAmountMin=='0') {
					$.gyzbadmin.alertFailure('请输入随机金额下限');
					return;
				}
				if(kong.test(randomAmountMax)||randomAmountMax=='0') {
					$.gyzbadmin.alertFailure('请输入随机金额上限');
					return;
				}
				if(parseInt(randomAmountMin)>parseInt(randomAmountMax)){
					$.gyzbadmin.alertFailure('随机金额上限不能小于下限');
					return;
				}
			}
			/** 排序  */
			var prizeSort = $('#updatePrizeModal #prizeSort').val();
			if(kong.test(prizeSort)) {
				$.gyzbadmin.alertFailure('抽奖顺序不可为空');
				return;
			}
			/** 中奖后有效天数 */
			var effectiveHours = $('#updatePrizeModal #effectiveHours').val();
			if(kong.test(effectiveHours)) {
				$.gyzbadmin.alertFailure('有效天数不可为空');
				return;
			}
			/** 活动内是否可重复中奖：Y可以/N不刻意 */
			var isRepeatWin = $('#updatePrizeModal #isRepeatWin').val();
			if(kong.test(isRepeatWin)) {
				$.gyzbadmin.alertFailure('是否可重复中奖不可为空');
				return;
			}
			/** 可抽奖客户范围：ALL所有/LIMIT有限制的 */
			var custScope = $('#updatePrizeModal #custScope').val();
			if(kong.test(custScope)) {
				$.gyzbadmin.alertFailure('抽奖客户范围不可为空');
				return;
			}
			/** 性别限制：ALL所有/MAN男/WOMAN女（cust_scope有限制时填写） */
			var limitSex = $('#updatePrizeModal #limitSex').val();
			/** 注册时间开始限制（cust_scope有限制时填写） */
			var limitRegisterDateStart = $('#updatePrizeModal #limitRegisterDateStart').val();
			/** 注册时间结束限制（cust_scope有限制时填写） */
			var limitRegisterDateEnd = $('#updatePrizeModal #limitRegisterDateEnd').val();
			if(custScope=='LIMIT'){
				if(kong.test(limitSex)) {
					$.gyzbadmin.alertFailure('性别限制不可为空');
					return;
				}
				if(kong.test(limitRegisterDateStart)) {
					$.gyzbadmin.alertFailure('注册时间开始限制不可为空');
					return;
				}
				if(kong.test(limitRegisterDateEnd)) {
					$.gyzbadmin.alertFailure('注册时间结束限制不可为空');
					return;
				}
			}
			/** 备注 */
			var memo = $('#updatePrizeModal #memo').val();
			/** 状态：VALID有效/DISABLE失效 */
			var status = $('#updatePrizeModal #status').val();
			if(kong.test(status)) {
				$.gyzbadmin.alertFailure('状态不可为空');
				return;
			}
			$.blockUI();
			$.post(window.Constants.ContextPath + '<%=PrizePath.BASE + PrizePath.EDIT %>', {
				    'prizeId'			:prizeId,
				    'prizeNo'			:prizeNo,
					'activityId'		:activityId,
					'prizeCode'			:prizeCode,
					'prizeName'			:prizeName,
					'prizeType'			:prizeType,
					'overallControlType':overallControlType,
					'quotaNumber'		:quotaNumber,
					'quotaAmount'		:quotaAmount,
					'oddsFactor'		:oddsFactor,
					'amountType'		:amountType,
					'fixedAmount'		:fixedAmount,
					'randomAmountMin'	:randomAmountMin,
					'randomAmountMax'	:randomAmountMax,
					'prizeSort'			:prizeSort,
					'effectiveHours'	:effectiveHours,
					'isRepeatWin'		:isRepeatWin,
					'custScope'			:custScope,
					'limitSex'			:limitSex,
					'limitRegisterDateStart':limitRegisterDateStart,
					'limitRegisterDateEnd'	:limitRegisterDateEnd,
					'memo'				:memo,
					'status'			:status
				}, function(data) {
					$.unblockUI();
					if(data.result == 'SUCCESS'){
						$('#updatePrizeModal').modal('hide');
						$.gyzbadmin.alertSuccess('修改成功', null, function(){
							window.location.reload();
						});
					} else {
						$.gyzbadmin.alertFailure(data.message, null, function(){
							window.location.reload();
						});
					}
				}, 'json'
			);
		})
	})

	function addAmountTypeChange(obj){
		if($(obj).val()=='FIXED'){
		  $('#addPrizeModal #randomAmountMin').val('');
		  $('#addPrizeModal #randomAmountMax').val('');
		  $('.addFixedAmount').show(); 
		  $('.addRandomAmount').hide();
		}else if($(obj).val()=='RANDOM'){
		  $('#addPrizeModal #fixedAmount').val('');
		  $('.addFixedAmount').hide();
		  $('.addRandomAmount').show();
		}
	}

	function updateAmountTypeChange(obj){
		if($(obj).val()=='FIXED'){
		  $('.updateFixedAmount').show(); 
		  $('.updateRandomAmount').hide();
		}else if($(obj).val()=='RANDOM'){
		  $('.updateFixedAmount').hide();
		  $('.updateRandomAmount').show();
		}
	}
	
function addAverallControlType(obj){
	if($(obj).val()=='NUMBER'){
	  $('.addControlTypeNumber').show(); 
	  $('.addControlTypeAmount').hide();
	}else if($(obj).val()=='AMOUNT'){
	  $('.addControlTypeNumber').hide();
	  $('.addControlTypeAmount').show();
	}else if($(obj).val()=='JOINT'){
	  $('.addControlTypeNumber').show();
	  $('.addControlTypeAmount').show();
	}
}

function updateAverallControlType(obj){
	if($(obj).val()=='NUMBER'){
	  $('.updateControlTypeNumber').show(); 
	  $('.updateControlTypeAmount').hide();
	}else if($(obj).val()=='AMOUNT'){
	  $('.updateControlTypeNumber').hide();
	  $('.updateControlTypeAmount').show();
	}else if($(obj).val()=='JOINT'){
	  $('.updateControlTypeNumber').show();
	  $('.updateControlTypeAmount').show();
	}
}

</script>
<body onload="loadPrize()">
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
				
				<div class="row" id="selectCalendar">
					<div class="col-xs-12">
					<!-- 查询条件 -->
					<form  action='<c:url value="<%=PrizePath.BASE + PrizePath.LIST %>"/>' method="post">
						<table class="search-table" >
							<tr>
								<td class="td-left" >奖项名称</td>
								<td class="td-right"  >
									<span class="input-icon">
									 <input type="text" id="prizeName" name="prizeName" value="${queryBean.prizeName }" />
									 <i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left" >活动</td>
								<td class="td-right">
									<input type="hidden" id="activityIdTemp" value="${queryBean.activityId}">
									<sevenpay:selectActivityTag id="activityId" name="activityId" />
								</td>
								</tr>
								<tr>
								<td colspan="4" align="center" >
									<span class="input-group-btn">
										<gyzbadmin:function url="<%=PrizePath.BASE + PrizePath.LIST %>">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
										<button  class="btn btn-purple btn-sm btn-margin clearPrize" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
										</button>
										<gyzbadmin:function url="<%=PrizePath.BASE + PrizePath.ADD%>">
											<button class="btn btn-purple btn-sm btn-margin" data-toggle='modal' data-target="#addPrizeModal">
												新增
												<i class="icon-plus-sign icon-on-right bigger-110"></i>
											</button> 
										</gyzbadmin:function>
									</span>
								</td>
							</tr>
						</table>
						</form>
						<div class="list-table-header" >
							奖项列表
						</div>
						<div class="table-responsive">
							<table id="sample-table-2" class="list-table" >
								<thead>
									<tr>
										<th>编号</th>
										<th>奖项名称</th>
										<th>奖项编码</th>
										<th>奖项英文编码</th>
										<th>抽奖顺序</th>
										<th>活动</th>
										<th>名额数量</th>
										<th>金额设置类型</th>
										<th>固定金额</th>
										<th>随机金额下限</th>
										<th>随机金额上限</th>
										<th>状态</th>
										<th>创建人</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${prizeList}" var="prize">
										<tr class="prize">
											<td>${prize.prizeId}</td>
											<td>${prize.prizeName}</td>
											<td>${prize.prizeNo}</td>
											<td>${prize.prizeCode}</td>
											<td>${prize.prizeSort}</td>
											<td>${prize.activityName}</td>
											<td>${prize.quotaNumber}</td>
											<td>
												<c:if test="${prize.amountType =='FIXED'}">固定</c:if>
												<c:if test="${prize.amountType =='RANDOM'}">随机</c:if>	
											</td>
											<td>${prize.fixedAmount}</td>
											<td>${prize.randomAmountMin}</td>
											<td>${prize.randomAmountMax}</td>
											<td>
												<c:if test="${prize.status == 'INIT'}">初始</c:if>
												<c:if test="${prize.status == 'DRAW_IN'}">抽奖中</c:if>
												<c:if test="${prize.status == 'OVER'}">已抽完</c:if>	
												<c:if test="${prize.status == 'DISABLE'}">失效</c:if>	
											</td>
											<td>${prize.instUserName}</td>
											<td>${prize.instDatetime}</td>
											<td>
												<input type="hidden" name="prizeId" value="${prize.prizeId}"/>
												<c:if test="${prize.status == 'INIT'}">
													<gyzbadmin:function url="<%=PrizePath.BASE + PrizePath.START%>">
														<button type="button" class="btn btn-primary btn-xs startPrizeLink" title="Start" data-toggle='modal' data-target="#startPrizeModal">
															开始
														</button>
													</gyzbadmin:function>
												</c:if>
												<c:if test="${prize.status == 'DRAW_IN'}">
													<gyzbadmin:function url="<%=PrizePath.BASE + PrizePath.RESEND%>">
														<button type="button" class="btn btn-primary btn-xs resendPrizeLink" title="Resend" data-toggle='modal' data-target="#resendPrizeModal">
															重发
														</button>
													</gyzbadmin:function>
													<gyzbadmin:function url="<%=PrizePath.BASE + PrizePath.END%>">
														<button type="button" class="btn btn-primary btn-xs endPrizeLink" title="End" data-toggle='modal' data-target="#endPrizeModal">
															结束
														</button>
													</gyzbadmin:function>
												</c:if>	
													<button type="button" class="btn btn-primary btn-xs detailPrizeLink" title="Detail" data-toggle='modal' data-target="#detailPrizeModal">
														明细
													</button>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty prizeList}">
										<tr><td colspan="15" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
								</tbody>
							</table>
							<!-- 分页 -->
							<c:if test="${not empty prizeList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- 开始模态框（Modal） -->
				<div class="modal fade" id="startPrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">开始</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		奖项编号：<span class="prizeId"></span><br/>
				         		奖项名称：<span class="prizeName"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="prizeId" class="prizeId" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary startBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				<!-- 重发模态框（Modal） -->
				<div class="modal fade" id="resendPrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">重发</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		奖项编号：<span class="prizeId"></span><br/>
				         		奖项名称：<span class="prizeName"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="prizeId" class="prizeId" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary resendBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				<!-- 结束模态框（Modal） -->
				<div class="modal fade" id="endPrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content" style="width: 600px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">结束</h4>
				         </div>
				         <div class="modal-body" align="left" style="margin-left: 150px;">
				         	<font style="color: red; font-weight: bold; font-size: 15px;">
				         		奖项编号：<span class="prizeId"></span><br/>
				         		奖项名称：<span class="prizeName"></span><br/>
				         	</font>
				         </div>
				         <div class="modal-footer">
				         	<input type="hidden" name="prizeId" class="prizeId" value=""/>
				            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				            <button type="button" class="btn btn-primary endBtn">确认</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
				
				<!-- 新增 -->
				<div class="modal fade" id="addPrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:60%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">奖项新增</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="15%">奖项名称<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeName" name="prizeName" maxlength="200"  clasS="width-90">
									</td>
									<td class="td-left" width="15%">奖项编码<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeNo" name="prizeNo"  maxlength="20" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left" >活动<span style="color:red">*</span></td>
									<td class="td-right">
										<sevenpay:selectActivityTag id="activityId" name="activityId" clasS="width-90"/>
									</td>
									<td class="td-left">奖项英文编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeCode" name="prizeCode"  maxlength="50" clasS="width-90">
									</td>
								</tr>
									<td class="td-left">奖项类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="prizeType" name="prizeType" clasS="width-90">
										  <option value="LUCK_DRAW">抽奖</option>
										  <option value="FULL_AWARD">全员奖项</option>
										</select> 
									</td>
									<td class="td-left">总控类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="overallControlType" name="overallControlType" onchange="addAverallControlType(this)" clasS="width-90">
										  <option value="JOINT">额度数量联合控制</option>
										  <option value="AMOUNT">总金额</option>
										  <option value="NUMBER">总名额</option>
										  
										</select> 
									</td>
								</tr>
								<tr class="addControlTypeNumber">
									<td class="td-left" >总名额<span style="color:red">*</span></td>
									<td class="td-right" colspan="3">
										<input type="text" id="quotaNumber" name="quotaNumber" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  clasS="width-95">
									</td>
								</tr>
								<tr class="addControlTypeAmount">	
									<td class="td-left" >总金额<span style="color:red">*</span></td>
									<td class="td-right" colspan="3">
										<input type="text" id="quotaAmount" name="quotaAmount" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-95">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >随机因子<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="oddsFactor" name="oddsFactor" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left">金额设置类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="amountType" name="amountType" clasS="width-90" onchange="addAmountTypeChange(this)">
										  <option value="FIXED">固定</option>
										  <option value="RANDOM">随机</option>
										</select>  
									</td>
								</tr>
								<tr class="addFixedAmount">	
									<td class="td-left" >固定金额<span style="color:red">*</span></td>
									<td class="td-right" colspan="3">
										<input type="text" id="fixedAmount" name="fixedAmount" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-95">
									</td>
								</tr>
								<tr class="addRandomAmount">	
									<td class="td-left" >随机金额下限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMin" name="randomAmountMin" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"   clasS="width-90">
									</td>
									<td class="td-left" >随机金额上限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMax" name="randomAmountMax" maxlength="11"  onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								
								<!-- ######################################################################### -->
								<tr>	
									<td class="td-left" >抽奖顺序<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeSort" name="prizeSort" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left" >中奖后有效小时<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="effectiveHours" name="effectiveHours" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >是否可重复中奖<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="isRepeatWin" name="isRepeatWin" clasS="width-90">
										  <option value="N">否</option>
										  <option value="Y">是</option>
										</select> 
									</td>
									<td class="td-left" >抽奖客户范围<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="custScope" name="custScope" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="LIMIT">有限制</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left" >性别限制<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="limitSex" name="limitSex" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="MAN">男</option>
										  <option value="WOMAN">女</option>
										</select> 
									</td>
									<td class="td-left" >状态<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="status" name="status" clasS="width-90">
										  <option value="INIT">初始</option>
										  <option value="DRAW_IN">抽奖中</option>
										  <option value="OVER">已抽完</option>
										  <option value="DISABLE">失效</option>
										</select> 
									</td>
								</tr>
								<tr>
									<td class="td-left" >注册时间开始限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateStart" name="limitRegisterDateStart"  readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>	
									<td class="td-left" >注册时间结束限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateEnd" name="limitRegisterDateEnd"   readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >备注</td>
									<td class="td-right" colspan="3">
										<textarea  id="memo" name="memo" maxlength="200" rows="2" clasS="width-95"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary addPrizeBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
				     </div>
				</div><!-- /.modal -->
			</div><!-- /.page-content -->
			<!-- 明细 -->
				<div class="modal fade" id="detailPrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:60%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">奖项明细</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<tr>
									<td class="td-left" width="15%">奖项名称<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeName" name="prizeName" readonly="readonly" maxlength="200"  clasS="width-90">
									</td>
									<td class="td-left" width="15%">奖项编码<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeNo" name="prizeNo" readonly="readonly" maxlength="20" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left" >活动<span style="color:red">*</span></td>
									<td class="td-right">
										<sevenpay:selectActivityTag id="activityId"  name="activityId"  clasS="width-90"/>
									</td>
									<td class="td-left">奖项英文编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeCode" name="prizeCode" readonly="readonly"  maxlength="50" clasS="width-90">
									</td>
								</tr>
									<td class="td-left">奖项类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="prizeType" name="prizeType" disabled="disabled" clasS="width-90">
										  <option value="LUCK_DRAW">抽奖</option>
										  <option value="FULL_AWARD">全员奖项</option>
										</select> 
									</td>
									<td class="td-left">总控类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="overallControlType" name="overallControlType"  disabled="disabled" onchange="updateAverallControlType(this)"  clasS="width-90">
										  <option value="JOINT">额度数量联合控制</option>
										  <option value="AMOUNT">总金额</option>
										  <option value="NUMBER">总名额</option>
										</select> 
									</td>
								</tr>
								<tr class="updateControlTypeNumber">
									<td class="td-left" >总名额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="quotaNumber" name="quotaNumber" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  clasS="width-90">
									</td>
									<td class="td-left" >已中奖数量<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="winNumber" name="winNumber" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  clasS="width-90">
									</td>
								</tr>
								<tr class="updateControlTypeAmount">	
									<td class="td-left" >总金额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="quotaAmount" name="quotaAmount" readonly="readonly" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left" >已中奖金额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="winAmount" name="winAmount"  readonly="readonly" maxlength="20" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >随机因子<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="oddsFactor" name="oddsFactor" readonly="readonly" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left">金额设置类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="amountType" name="amountType" clasS="width-90" disabled="disabled" onchange="updateAmountTypeChange(this)">
										  <option value="FIXED">固定</option>
										  <option value="RANDOM">随机</option>
										</select>  
									</td>
								</tr>
								<tr class="updateFixedAmount">	
									<td class="td-left" >固定金额<span style="color:red">*</span></td>
									<td class="td-right" colspan="3">
										<input type="text" id="fixedAmount" name="fixedAmount" readonly="readonly" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-95">
									</td>
								</tr>
								<tr class="updateRandomAmount">	
									<td class="td-left" >随机金额下限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMin" name="randomAmountMin" readonly="readonly" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"   clasS="width-90">
									</td>
									<td class="td-left" >随机金额上限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMax" name="randomAmountMax" readonly="readonly" maxlength="11"  onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								
								<!-- ######################################################################### -->
								<tr>	
									<td class="td-left" >抽奖顺序<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeSort" name="prizeSort" readonly="readonly" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left" >中奖后有效小时<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="effectiveHours" name="effectiveHours" readonly="readonly" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >是否可重复中奖<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="isRepeatWin" name="isRepeatWin" disabled="disabled" clasS="width-90">
										  <option value="N">否</option>
										  <option value="Y">是</option>
										</select> 
									</td>
									<td class="td-left" >抽奖客户范围<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="custScope" name="custScope" disabled="disabled" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="LIMIT">有限制</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left" >性别限制<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="limitSex" name="limitSex" disabled="disabled" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="MAN">男</option>
										  <option value="WOMAN">女</option>
										</select> 
									</td>
									<td class="td-left" >状态<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="status" name="status" disabled="disabled" clasS="width-90">
										  <option value="INIT">初始</option>
										  <option value="DRAW_IN">抽奖中</option>
										  <option value="OVER">已抽完</option>
										  <option value="DISABLE">失效</option>
										</select> 
									</td>
								</tr>
								<tr>
									<td class="td-left" >注册时间开始限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateStart" disabled="disabled" name="limitRegisterDateStart"  readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>	
									<td class="td-left" >注册时间结束限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateEnd" disabled="disabled" name="limitRegisterDateEnd"   readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >备注</td>
									<td class="td-right" colspan="3">
										<textarea  id="memo" name="memo" maxlength="200" rows="2" readonly="readonly" clasS="width-95"></textarea>
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
					<!-- 修改 -->
				<div class="modal fade" id="updatePrizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="width:60%">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
				            <h4 class="modal-title" id="myModalLabel">奖项修改</h4>
				         </div>
				         <div class="modal-body">
				            <table class="modal-input-table" style="width: 100%;">
				            	<input type="hidden" id="prizeId" name="prizeId" readonly="readonly" clasS="width-90">
				            	<tr>
									<td class="td-left" width="15%">奖项名称<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeName" name="prizeName" maxlength="200"  clasS="width-90">
									</td>
									<td class="td-left" width="15%">奖项编码<span style="color:red">*</span></td>
									<td class="td-right" width="35%">
										<input type="text" id="prizeNo" name="prizeNo"  maxlength="20" clasS="width-90">
									</td>
								</tr>
								<tr>
									<td class="td-left" >活动<span style="color:red">*</span></td>
									<td class="td-right">
										<sevenpay:selectActivityTag id="activityId" name="activityId" clasS="width-90"/>
									</td>
									<td class="td-left">奖项英文编码<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeCode" name="prizeCode"  maxlength="50" clasS="width-90">
									</td>
								</tr>
									<td class="td-left">奖项类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="prizeType" name="prizeType" clasS="width-90">
										  <option value="LUCK_DRAW">抽奖</option>
										  <option value="FULL_AWARD">全员奖项</option>
										</select> 
									</td>
									<td class="td-left">总控类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="overallControlType" name="overallControlType" onchange="updateAverallControlType(this)"  clasS="width-90">
										  <option value="JOINT">额度数量联合控制</option>
										  <option value="AMOUNT">总金额</option>
										  <option value="NUMBER">总名额</option>
										</select> 
									</td>
								</tr>
								<tr class="updateControlTypeNumber">
									<td class="td-left" >总名额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="quotaNumber" name="quotaNumber" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  clasS="width-90">
									</td>
									<td class="td-left" >已中奖数量<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="winNumber" name="winNumber" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  clasS="width-90">
									</td>
								</tr>
								<tr class="updateControlTypeAmount">	
									<td class="td-left" >总金额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="quotaAmount" name="quotaAmount" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left" >已中奖金额<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="winAmount" name="winAmount" maxlength="20" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >随机因子<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="oddsFactor" name="oddsFactor" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left">金额设置类型<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="amountType" name="amountType" clasS="width-90" onchange="updateAmountTypeChange(this)">
										  <option value="FIXED">固定</option>
										  <option value="RANDOM">随机</option>
										</select>  
									</td>
								</tr>
								<tr class="updateFixedAmount">	
									<td class="td-left" >固定金额<span style="color:red">*</span></td>
									<td class="td-right" colspan="3">
										<input type="text" id="fixedAmount" name="fixedAmount" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-95">
									</td>
								</tr>
								<tr class="updateRandomAmount">	
									<td class="td-left" >随机金额下限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMin" name="randomAmountMin" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"   clasS="width-90">
									</td>
									<td class="td-left" >随机金额上限<span style="color:red">*</span></td>
									<td class="td-right" >
										<input type="text" id="randomAmountMax" name="randomAmountMax" maxlength="11"  onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								
								<!-- ######################################################################### -->
								<tr>	
									<td class="td-left" >抽奖顺序<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="prizeSort" name="prizeSort" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
									<td class="td-left" >中奖后有效小时<span style="color:red">*</span></td>
									<td class="td-right">
										<input type="text" id="effectiveHours" name="effectiveHours" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"  clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >是否可重复中奖<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="isRepeatWin" name="isRepeatWin" clasS="width-90">
										  <option value="N">否</option>
										  <option value="Y">是</option>
										</select> 
									</td>
									<td class="td-left" >抽奖客户范围<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="custScope" name="custScope" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="LIMIT">有限制</option>
										</select> 
									</td>
								</tr>
								<tr>	
									<td class="td-left" >性别限制<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="limitSex" name="limitSex" clasS="width-90">
										  <option value="ALL">无限制</option>
										  <option value="MAN">男</option>
										  <option value="WOMAN">女</option>
										</select> 
									</td>
									<td class="td-left" >状态<span style="color:red">*</span></td>
									<td class="td-right">
										<select id="status" name="status" clasS="width-90">
										  <option value="INIT">初始</option>
										  <option value="DRAW_IN">抽奖中</option>
										  <option value="OVER">已抽完</option>
										  <option value="DISABLE">失效</option>
										</select> 
									</td>
								</tr>
								<tr>
									<td class="td-left" >注册时间开始限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateStart" name="limitRegisterDateStart"  readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>	
									<td class="td-left" >注册时间结束限制</td>
									<td class="td-right">
										<input type="text" id="limitRegisterDateEnd" name="limitRegisterDateEnd"   readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;" clasS="width-90">
									</td>
								</tr>
								<tr>	
									<td class="td-left" >备注</td>
									<td class="td-right" colspan="3">
										<textarea  id="memo" name="memo" maxlength="200" rows="2" clasS="width-95"></textarea>
									</td>
								</tr>
				            </table>	             
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				            <button type="button" class="btn btn-primary updatePrizeBtn">提交</button>
				         </div>
				      </div><!-- /.modal-content -->
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
</body>
</html>