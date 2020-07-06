var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var clickStat = 0; //订单状态
var pageNum = 1; //当前页
var myDroploader;
$(function() {
	var GiforderType = sc.utils.getQueryString("GiforderType");
	if(GiforderType) {
		$('.dd_tabList li').each(function() {
			var atype = $(this).attr('data-type');
			if(atype == GiforderType) {
				$(this).addClass('active').siblings().removeClass('active');
			}
		})
		clickStat=GiforderType;
		OrderPage();
	} else {
		OrderPage();
	}		
});
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
//获取领取了的礼包订单列表
function GetGiftrecordList(successCallback, el) {
	var url = "api/Gift/GetGiftrecordList";
	var callback = function(data) {
		if(data.code === 0) {			
			successCallback(data);
		} else {
			el.resetload();
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	};
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Types": clickStat,
		"page": pageNum
	}, callback, error)
}

function OrderPage() {	
	$('#Orderlist_Div').html("");
	//初始化
	myDroploader = $('#Orderlist').dropload({
		scrollArea: window,
		domDown: {
			domClass: 'dropload-down',
			domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
			domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
			domNoData: '<div class="dropload-noData">已经到底了</div>'
		},
		loadDownFn: function(me) {
			GetGiftrecordList(function(data) {
				//console.log(data);
				if(data.data.length == 0) {
					me.lock();
					me.noData();
					me.resetload();
					if(pageNum ==1) {
						$(".dropload-down").remove();
						emptytpl($('#Orderlist_Div'));
					}
					
				} else {
					pageNum++;
					if(data.data.length < 6) {
						me.lock();                                 
						me.noData();                  
					}
					var gettpl = document.getElementById('Orderlist_Tpl').innerHTML;
					laytpl(gettpl).render(data, function(html) {
						//得到的模板渲染到html
						$('#Orderlist_Div').append(html);
					});
					me.resetload();
				}

			}, me)
		}

	});

}

//点击确认收货方法
function ReceiptBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var id = self.attr('data-Id');	
	layer.open({
		content: '是否确认收货？',
		btn: ['确定', '取消'],
		yes: function(index) {
			ConfirmReceipt(id,function(data){
				layer.open({
					content: '确认收货成功！',
					skin: 'msg',
					time: 1 //2秒后自动关闭
				});
				setTimeout(function() {					
					location.reload();
				}, 1000);
			});
		},
		no: function(index) {
			layer.close(index);
		}
	});
}
//确认收货接口
function ConfirmReceipt(id,successCallback) {
	var url = 'api/Gift/updateGift';
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
		"Id": id
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

//获取物流详细信息
function GetLogistics(No) {
	var url = 'api/Gift/GetLogistics';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var jsonData = JSON.parse(data.data);
			if(jsonData.com) {
				$('#expressNo').html('' + jsonData.com + ': ' + jsonData.nu + '');
			}
			var str = "";
			str += '<ul class="logisticsList">';
			if(jsonData.returnCode == 500 || jsonData.returnCode == 400) {
				str += '<li>';
				str += '<span class="dian"></span>';
				str += '<dl>';
				str += '<dt></dt>';
				str += '<dd>' + jsonData.message + '</dd>';
				str += '</dl>';
				str += '</li>';
			} else {
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
			}
			str += '</ul>';
			$('.logistics').append(str);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '商家还没有填写发货物流信息！',
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
		"Id": No
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

//删除订单
function DelOrderBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-Id');
	DeleteOrders(orderNo, function(data) {
		layer.open({
			content: '订单已删除！',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		if($('.footerBtn').length) {
			setTimeout(function() {
				location.href = "/member/order_fl.html";
			}, 1000);
		} else {
			self.remove();
		}
	});
}
//删除订单接口
function DeleteOrders(No, successCallback) {
	var url = 'api/Gift/DeleteGiftCode';
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
		"Id": No
	}, callback);
}

//获取订单详情
function OrderDetails(No, successCallback) {
	var url = 'api/Gift/GetGiftrecordDetail';
	var callback = function(data) {
		if(data.code == 0) { //
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
		"Id": No
	}, callback);
}