var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
//拨打电话
function Call(obj) {
	var telnumber = $(obj).attr("data-tel");
	if(telnumber==""){
		layer.open({
			content: '该店铺暂无电话',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
	}else{
		layer.open({
			content: telnumber,
			btn: ['呼叫', '取消'],
			yes: function(index) {
				window.location.href = "tel:" + telnumber;
				layer.close(index);
			}
		})
	}
}
//普通订单
function OrderList(status, pageNum, pageSize, successCallback, el) {
	var url = 'api/Order/OrderList';
	var callback = function(data) {
		if(data.code == 0) { //成功			
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			el.resetload();
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			el.resetload();
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Status": status,
		"page": pageNum,
		"PageSize": pageSize
	}, callback, error);
}
//售后订单
function RefundOrderList(pageNum, pageSize, successCallback, el){
	var url = 'api/Order/RefundOrderList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			el.resetload();
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			el.resetload();
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum,
		"PageSize": pageSize
	}, callback, error);
	
}

function OrderPage(status) {
	var pageNum = 0;
	var pageSize = 10;
	//初始化
	$('#Orderlist_Div').html("");
	$(".dropload-down").remove();
	//dropload
	$('#Orderlist').dropload({
		scrollArea: window,
		domDown: {
			domClass: 'dropload-down',
			domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
			domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
			domNoData: '<div class="dropload-noData">已经到底了</div>'
		},
		loadDownFn: function(me) {
			pageNum++;
			OrderList(status, pageNum, pageSize, function(data) {
				var gettpl = document.getElementById('Orderlist_Tpl').innerHTML;
				if(data.data.length == 0 && !data.isok) {
					me.lock();
					me.noData();
					me.resetload();
					if(pageNum == 1) {
						$(".dropload-down").remove();
						emptytpl($("#Orderlist_Div"));

					}
				} else if(data.data.length < pageSize) {
					laytpl(gettpl).render(data.data, function(html) {
						//得到的模板渲染到html
						$('#Orderlist_Div').append(html);
					});
					$('.orderitem').each(function(){
						var orderno=$(this).attr('data-no');
						if(status!=5){
							$(this).find('.item-prolist').click(function(){					
								location.href='/member/orderDetail.html?OrderNo='+orderno;						
							})
						}
						
						$(this).find('.CanelRefundbtn').click(function(event){
							event.preventDefault();
							event.stopPropagation()
							CanelRefundBtnSingle($(this),orderno);
						})
					})
					me.lock("down");
					me.noData();
					me.resetload();				
				} else {
					if(pageNum == 1) {
						$("#Orderlist_Div").html("");
					}
					laytpl(gettpl).render(data.data, function(html) {
						//得到的模板渲染到html
						$('#Orderlist_Div').append(html);
					});
					$('.orderitem').each(function(){
						var orderno=$(this).attr('data-no');
						$(this).find('.item-prolist').click(function(){
							location.href='/member/orderDetail.html?OrderNo='+orderno;
						})
						$(this).find('.CanelRefundbtn').click(function(event){
							event.preventDefault();
							event.stopPropagation()
							CanelRefundBtnSingle($(this),orderno);
						})
					})
					me.resetload();
				}
			}, me)
		}

	});

    var source = sc.utils.getQueryString("source");
    if (!sc.utils.isNullOrEmpty(source) && source=="pay") {
        $(".fcrs").show();
    }
    $(".know").click(function () {
        $(".fcrs").hide();
    });
}

function OrderPageint() {
	//0全部 1 待付款；2 待发货 ,已付款，3 已发货；4 待评论，已收货；
    var orderType = sc.utils.getQueryString("orderType");
	if(orderType) {
		$('.dd_tabList li').each(function() {
			var atype = $(this).attr('data-type');
			if(atype == orderType) {
				$(this).addClass('active').siblings().removeClass('active');
			}
		})
		OrderPage(orderType);
	} else {
		OrderPage(0);
	}
//	$('.dd_tabList li').click(function() {
//		$('#Orderlist_Div').html("");
//		$(".dropload-down,.dropload-up").remove();
//		var status = $(this).attr('data-type');
//		$(this).addClass('active').siblings().removeClass('active');
//		OrderPage(status);
//	})
}
//获取订单详情
function OrderDetails(No, odId, successCallback) {
    var url = 'api/Order/OrderDetails';
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
        "OrderNo": No,
        "odId": odId
	}, callback);
}
//获取订单列表中Item项的详情
function GetOrderListItemDetail(No, PId, successCallback) {
	var url = 'api/Order/GetOrderListItemDetail';
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ProductId": PId
	}, callback);
}

//取消订单接口
function CancelOrders(No, Reason) {
	var url = 'api/Order/CancelOrders';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '订单已取消！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				layer.closeAll();
				location.reload();
			}, 2000);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ReMarks": Reason
	}, callback);
}
//取消订单原因，退款原因
function CancelReason(obj) {
	var url = 'api/Order/CancelReason';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 0; i < data.data.length; i++) {
				opt += '<li data-code="' + data.data[i].code + '">' + data.data[i].message + '</li>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//取消订单弹出
function CancelBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-no');
	var str = '<div class="pop-Reason">';
	str = '<div class="pop-hd center c_333">请选择取消订单原因</div>';
	str += '<ul class="wenList center reasonList">';
	str += '</ul>';
	str += '</div>';
	layer.open({
		type: 1,
		content: str,
		anim: 'up',
		style: 'position:fixed; bottom:0; left:0; width: 100%;'
	});
	CancelReason($('.reasonList'));
	$('.reasonList').on('click', 'li', function() {
		$(this).addClass('active').siblings().removeClass('active');
		var Reason = $(this).text();
		CancelOrders(orderNo, Reason);
		setTimeout(function() {
			layer.closeAll();
		}, 2000);
	})
}

//获取退货原因
function GetRefundReason(obj) {
	var url = 'api/Order/GetRefundReason';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 1; i < data.data.length; i++) {
				opt += '<li data-code="' + data.data[i].code + '">' + data.data[i].message + '</li>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//提交退货申请（整单退）
function SubmitRefund(No, ContentTxt, Reason) {
	var url = 'api/Order/SubmitRefund';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退货申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/aftersale/aftersaleorder.html";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}
//单个产品退货申请
function SubmitRefundSingle(No, Pids, ContentTxt, Reason) {
	var url = 'api/Order/SubmitRefundSingle';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退货申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/aftersale/aftersaleorder.html";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"OrderItemId": Pids,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}
//提交退款申请（已付款，商家未发货）
function SubmitReturn(No, ContentTxt, Reason) {
	var url = 'api/Order/SubmitReturn';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退款申请成功!!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/aftersale/aftersaleorder.html";
			}, 1100);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}

//单个产品退款申请
function SubmitReturnSingle(No, Pids, ContentTxt, Reason) {
	var url = 'api/Order/SubmitReturnSingle';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退款申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/aftersale/aftersaleorder.html";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"OrderItemId": Pids,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}

//撤销退货退款申请
function CanelRefund(No,ItemId) {
	var url = 'api/Order/CanelRefund';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '撤销申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.reload();
			}, 1000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"OrderItemId":ItemId
	}, callback);
}
//点击撤销退货退款申请
function CanelRefundBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	CanelRefund(orderNo,0);
}
//点击撤销退货退款申请（单个）
function CanelRefundBtnSingle(obj,orderNo) {
	var self = obj.parents('.proitem');
	var ItemId = self.attr('data-id');		
	CanelRefund(orderNo,ItemId);	
}

//删除订单接口
function DeleteOrders(No, successCallback) {
	var url = 'api/Order/DeleteOrders';
	var callback = function(data) {
		if(data.code == 0) { //成功			
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//删除订单
function DelOrderBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	layer.open({
		content: '您确定要删除该订单吗？',
		btn: ['删除', '取消'],
		skin: 'footer',
		yes: function(index) {
			DeleteOrders(orderNo, function(data) {
				layer.open({
					content: '订单已删除！',
					skin: 'msg',
					time: 1 //1秒后自动关闭
				});
				if($('.footerBtn').length) {
					setTimeout(function() {
						location.href = "/member/order.html?orderType=0";
					}, 1000);
				} else {
					self.remove();
				}
			});
		}
	});
}
//确认收货接口
function ConfirmReceipt(No,successCallback) {
	var url = 'api/Order/ConfirmReceipt';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '订单异常，请联系客服！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//点击确认收货方法
function ReceiptBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	layer.open({
		content: '是否确认收货？',
		btn: ['确定', '取消'],
		yes: function(index) {
			ConfirmReceipt(orderNo,function(data){
				layer.open({
					content: '确认收货成功！',
					skin: 'msg',
					time: 1 //2秒后自动关闭
				});
				if($('.footerBtn').length) {
					setTimeout(function() {					
						location.reload();
					}, 1000);
				} else {
					setTimeout(function() {
						location.href = "/member/order.html?orderType=4";
					}, 1000);
				}
				
			});
		},
		no: function(index) {
			layer.close(index);
		}
	});
}

//提醒发货接口
function Remind(No) {
	var url = 'api/Order/Remind';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '提醒卖家成功！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//提醒发货方法
function RemindBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	Remind(orderNo);
}

//获取物流详细信息
function GetLogistics(No,rId) {
	var url = 'api/Order/GetLogistics';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var jsonData = JSON.parse(data.data);
			var nu=jsonData.nu;
			if(nu==null){
                nu = jsonData.kuaidiNo;
			}
			if(jsonData.companyName) {
				
				$('#expressNo').html('' + jsonData.companyName + ': ' + nu + '');
			}
			var str = "";
			str += '<ul class="logisticsList">';
			if(jsonData.status == 200) {
				for(var i = 0; i < jsonData.data.length; i++) {
					if(i == 0) {
						str += '<li class="active">';
					} else {
						str += '<li>';
					}
					str += '<span class="dian"></span>';
					str += '<dl>';
					str += '<dt><span class="time">' + jsonData.data[i].time + '</span></dt>';
					str += '<dd>' + jsonData.data[i].context + '</dd>';
					str += '</dl>';
					str += '</li>';
				}
				
			} else {
				str += '<li>';
				str += '<span class="dian"></span>';
				str += '<dl>';
				str += '<dt></dt>';
				str += '<dd>' + jsonData.message + '</dd>';
				str += '</dl>';
				str += '</li>';
			}
			str += '</ul>';
			$('.logistics').append(str);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
        "OrderNo": No,
        "rId": rId
	}, callback);
}
//获取物流公司名称
function GetExpressCompanyList(obj) {
	var url = 'api/Order/GetExpressCompanyList';
	var callback = function(data) {
		var result = JSON.parse(data.data);
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 0; i < result.length; i++) {
				opt += '<option value="' + result[i].Id + '">' + result[i].company + '</option>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//提交退货快递信息
function SubmitRefundExpress(No, ExpressName, ExpressNo) {
	var url = 'api/Order/SubmitRefundExpress';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '寄回信息提交成功！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});			
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ExpressName": ExpressName,
		"ExpressNo": ExpressNo
	}, callback);
}

//提交退货快递信息验证
function VerifyExpress(ExpressName, ExpressNo) {
	var txt_ExpressName = $.trim(ExpressName),
		txt_ExpressNo = $.trim(ExpressNo);
	if(txt_ExpressName == '0') {
		layer.open({
			content: '请选择物流公司！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!ExpressNo) {
		layer.open({
			content: '请输入物流单号！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}
//评论商品
function CommentProduct(No, Pid, Piclist, Text, PicNo, Star,closeFun) {
	var url = 'api/Order/CommentProduct';
    var callback = function (data) {
        closeFun();
		if(data.code == 0) { //成功
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=4";
			}, 1100);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"Pid": Pid,
		"PicList": Piclist,
		"Content": Text,
		"PicNo": PicNo,
		"Starnum": Star
	}, callback);
}
//获取售后订单详情
function RefundOrderInfo(No, RefundId, successCallback) {
	var url = "api/Order/RefundOrderInfo";
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"RefundId":RefundId
	}, callback);
}