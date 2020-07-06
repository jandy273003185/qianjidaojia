var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");

if(sc.auth.isLogin()) { //如果已经登录的
	//		isClick = true;
	//		GetGiftrecordList(clickStat, isClick);
} else { //未登录的
	layer.open({
		content: '登录超时，请重新登录!',
		skin: 'msg',
		time: 1 //1秒后自动关闭
	});
	setTimeout(function() {
		location.href = "/login.html";
	}, 1000);
}

//获取物流详细信息
function GetLogistics(orderId,No) {
	var url = 'api/Gift/GetLogistics';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var jsonData = JSON.parse(data.data);
			var nu=jsonData.nu;
			if(nu==null){
				nu="(空)";
			}
			if(jsonData.companyName) {
				$('#expressNo').html(jsonData.companyName+': ' + nu);
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
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id": orderId,
		"OrderNo": No
	}, callback);
}

//获取订单详情
function OrderDetails(orderId, successCallback) {
	var url = 'api/Gift/GetGiftrecordDetail';
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
		"Id": orderId
	}, callback);
}