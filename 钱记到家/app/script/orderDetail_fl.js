var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var orderId = sc.utils.getQueryString("Id");
var No = sc.utils.getQueryString("OrderNo");
	
GetGiftrecordDetail();	

//获取订单详情
function GetGiftrecordDetail(){
	var url = "api/Gift/GetGiftrecordDetail";
	var callback = function(data){
		console.log(data)
		if(data.code === 0){
			if(data.data[0].ReceiveStatus === 0){
				$(".transactionState").text("待领取");
				$(".dailing").css("display","inline-block");
			}
			else if(data.data[0].ReceiveStatus === 1){
				$(".transactionState").text("待发货")
				$(".daifa").css("display","inline-block");
				$(".orderContent .text2").css("display","none");
				$(".orderContent .text3").css("display","none");
			}
			else if(data.data[0].ReceiveStatus === 2){
				$(".transactionState").text("已发货");
				$(".yifa").css("display","inline-block");
				$(".orderContent .text3").css("display","none");
			}
			else if(data.data[0].ReceiveStatus === 3){
				$(".transactionState").text("已收货");
				$(".yishou").css("display","inline-block");
			}
			
			$(".persionMeg_text .name").text(data.data[0].Receiver);
			$(".phoneNum").text(data.data[0].Mobile);
			$(".xxdizhi").text(data.data[0].Address);
			$(".goodsImg img").attr("src",data.data[0].PicNo);
			$(".goodstit P").text(data.data[0].Title);
			$(".goodsPrice").text('￥'+data.data[0].Price);
			
			$(".needbackmoney").text(data.data[0].Price);
			$(".price_mesg2 .zongjia").text(data.data[0].Price);
			if(data.data[0].SubmitTime !==""){
				$('.orderContent .text1').show();
				$(".lingTime").text(data.data[0].SubmitTime);
			}
			else{
				$('.orderContent .text1').hide();
			}
			$(".orderNumber").text(data.data[0].OrderNo);
			$(".faTime").text(data.data[0].Fahuodate);
//			$(".queTime").text(data.data[0].Shouhuodate);
			$(".footerBtn").attr("data-No",data.data[0].OrderNo);
			$(".wuliubtn").click(function(){
				window.location.href = "logistics_fl.html?Id="+data.data[0].Id+"&No="+data.data[0].OrderNo
			})
			
		}
	}
	sc.post(url,{
		UserId:userId,
		Token:token,
		Id:orderId
	},callback)
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

//点击确认收货方法
function ReceiptBtn(obj) {
	if($('.footerBtn').length==0) {
		var self = $(obj).parents('.orderitem');
		orderId=self.attr('data-id');
	}
	layer.open({
		content: '是否确认收货？',
		btn: ['确定', '取消'],
		yes: function(index) {
			ConfirmReceipt(function(data){
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
function ConfirmReceipt(successCallback) {
	var url = 'api/Gift/updateGift';
	var callback = function(data) {		
		if(data.code == 0) { //成功
			successCallback(data);
			layer.open({
				content: '确认收货成功！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.reload();
			}, 1000);
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
		"Id": orderId
	}, callback);
}
//拨打电话
function Call(obj) {
	var telnumber = $(obj).attr("data-tel");
	layer.open({
		content: telnumber,
		btn: ['呼叫', '取消'],
		yes: function(index) {
			window.location.href = "tel:" + telnumber;
			layer.close(index);
		},
	})
}

//删除订单
function DelOrderBtn(obj) {
	if($('.footerBtn').length==0) {
		var self = $(obj).parents('.orderitem');
		orderId=self.attr('data-id');
	}		
	DeleteOrders(function(data) {
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
function DeleteOrders(successCallback) {
	var url = 'api/Gift/DeleteGiftCode';
	var callback = function(data) {
		console.log(data)
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
		"Id": orderId
	}, callback);
}