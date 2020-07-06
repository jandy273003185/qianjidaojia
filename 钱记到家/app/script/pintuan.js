var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips center c_999">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
// 获取个人信息接口
function GetMemberInfo(successCallback) {
	var url = 'api/User/GetMemberInfo';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
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
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}
//拼团列表
function getGroupProductList(index, size, successCallback, fn) {
	var url = 'api/GroupBuy/GetGroupProductList';
	var callback = function(data) {
		//resultCallback(data);
		if(data.code == 0) {
			successCallback(data);
		} else {
			$(".dropload-down").remove();
			fn.lock();
			fn.resetload();
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var error = function(data) {
		$(".dropload-down").remove();
		fn.lock();
		fn.resetload();
	}
	sc.post(url, {
		"page": index,
		"pageSize": size
	}, callback, error);
}

//获取我的拼团列表
function getMyGroupRecordList(index, size, status, successCallback, fn) {
	var url = 'api/GroupBuy/GroupRecordList';
	var callback = function(data) {
		if(data.code == 0) {
			successCallback(data);
			console.log(data);
		} else {
			fn.resetload();
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var error = function(data) {

		fn.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": index,
		"pageSize": size,
		"GroupStatus": status
	}, callback, error);
}

//获取拼团详情
function getGroupProductInfo(groupId, successCallback) {
	var url = 'api/GroupBuy/GroupProductInfo';
	var callback = function(data) {
		if(data.code == 0) {
			successCallback(data);
		} else if(data.code == 1) {
			layer.open({
				content: '该产品已下架!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '服务器开了点小差，请重新刷新!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}

	sc.post(url, {
		"groupId": groupId,
		"UserId": userId,
		"Token": token
	}, callback);
}

//获取产品的拼团记录
function getGroupRecordList(groupId, topNum, successCallback) {
	var url = 'api/GroupBuy/GetGroupRecordList';
	var callback = function(data) {
		if(data.code == 0) {
			successCallback(data);
		} else {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}

	sc.post(url, {
		"groupId": groupId,
		"TopNum": topNum,
		"UserId": userId,
        "Token": token,
        "groupRecordId": groupRecordId
	}, callback);
}

//查看我的某一拼团记录详情
function getGroupMemberInfo(groupId, groupRecordId, successCallback) {
	var url = 'api/GroupBuy/GroupMemberInfo';
	var callback = function(data) {
		if(data.code == 0) {
			successCallback(data);
		}
	}
	sc.post(url, {
		"GroupId": groupId,
		"GroupRecordId": groupRecordId,
		"UserId": userId,
		"Token": token
	}, callback);
}

//列表倒计时
function GetRTime(obj) {
	var EndTime = new Date(obj.attr("data-endtime")); //结束时间
	var timer = setInterval(function() {
		var NowTime = new Date(); //当前时间
		var t = EndTime.getTime() - NowTime.getTime();
		if(t > 0) {
			var d = Math.floor(t / 1000 / 60 / 60 / 24); //天
			var h = Math.floor(t / 1000 / 60 / 60 % 24); //时
			var m = Math.floor(t / 1000 / 60 % 60); //分
			var s = Math.floor(t / 1000 % 60); //秒
			if(parseInt(d) < 1) {
				d = "";
			} else {
				d = d + "天";
			}
			if(parseInt(h) < 10) {
				h = "0" + h;
			}
			if(parseInt(m) < 10) {
				m = "0" + m;
			}
			if(parseInt(s) < 10) {
				s = "0" + s;
			}
			obj.text(d + h + ":" + m + ":" + s);
		} else {
			clearInterval(timer);
		}
	}, 1000);
}

//结束拼团
function operationCompleteGroup(groupId, groupRecordId, successCallback) {
	var url = 'api/GroupBuy/OperationCompleteGroup';
	var callback = function(data) {
		if(data.code === 0) {
			successCallback(data);
		} else {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"GroupId": groupId,
		"GroupRecordId": groupRecordId,
		"UserId": userId,
		"Token": token
	}, callback);
}



