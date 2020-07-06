var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var has_click = false;
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips center c_999">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
$(function() {
	//var islogin = sc.utils.getQueryString("login");
	//if(!sc.auth.isLogin()&&!islogin) {
	//	layer.open({
	//		content: '您还没有登录，是否要登录？',
	//		btn: ['确定', '取消'],
	//		yes: function(index) {
	//			window.location.href = "/login.html";
	//		},
	//		no: function(index) {
	//			layer.close(index);
	//		}
	//	});
	//}
	
});

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

//个人中心主页
function MemberindexPage() {
	GetMemberInfo(function(data) {
		if(data.data.Avatar) {
			$('#Avatar').attr('src', data.data.Avatar);
		}
		$('.memberTop .gradeImg').addClass('level' + data.data.Level);
		if(data.data.NickName) {
			$('#NickName').text(data.data.NickName);
		} else if(data.data.Mobile) {
			$('#NickName').text(data.data.Mobile);
		} else {
			$('#NickName').text(data.data.UserName);
		}
		$('#MemberID').text(data.data.MemberID);
		$('#Wallet').text(data.data.Wallet);
		$('#CouponSum').text(data.data.CouponSum);
		$('#Score').text(data.data.Score);
		if(data.data.OwnerStatus === 0) {
			$('#Owner').attr('href', '/member/setUpShop.html');
		} else if(data.data.OwnerStatus === 1) {
			$('#Owner').attr('href', '/member/applysuccess.html');
		} else if(data.data.OwnerStatus === 2) {
			$('#Owner').attr('href', '/member/subShopIndex.html');
			$('#Owner').find('.title').text("我的小店");
		} else {
			$('#Owner').attr('href', '/member/applysuccess.html');
		}
	});
	GetMemberOrder();

	function GetMemberOrder() {
		var url = 'api/User/GetMemberOrder';
		var callback = function(data) {
			if(data.code == 0) { //成功
				$('.nav_ordertype li').each(function() {
					var ordertype = $(this).find('a').attr('data-type');
					if(ordertype == 1 && data.data.PendingPayment > 0) { //待付款
						$(this).find('img').after('<span class="circleNum">' + data.data.PendingPayment + '</span>');
					} else if(ordertype == 2 && data.data.PendingShipment > 0) { //待发货
						$(this).find('img').after('<span class="circleNum">' + data.data.PendingShipment + '</span>');
					} else if(ordertype == 3 && data.data.Shipped > 0) { //待收货
						$(this).find('img').after('<span class="circleNum">' + data.data.Shipped + '</span>');
					} else if(ordertype == 4 && data.data.Received > 0) { //待评价
						$(this).find('img').after('<span class="circleNum">' + data.data.Received + '</span>');
					} else if(ordertype == 5 && data.data.ReturnsAfterSale > 0) { //售后
						//						$(this).find('img').after('<span class="circleNum">' + data.data.ReturnsAfterSale + '</span>');
					}
				})
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
}

//修改个人信息
function UpdateMemberInfo(mybirthday, mysex, NickName) {
	var url = "api/User/UpdateMemberInfo";
	var callback = function(data) {
		if(data.code === 0) {
			layer.open({
				content: '保存成功!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/set.html";
			}, 1000);
		}
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新的登录!',
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
		"Token": token,
		"Birthday": mybirthday,
		"Sex": mysex,
		"NickName": NickName
	}, callback)
}

//修改头像

//预览头像
function changepic() {
	//var reads = new FileReader();
	//f = document.getElementById('fileImg').files[0];
	//reads.readAsDataURL(f);
	//reads.onload = function(e) {
	//	document.getElementById('Avatar').src = this.result;
	//};
}

function EditHeadImage(imgres) {
	var url = "api/User/EditHeadImage";
	var callback = function(data) {

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Avatar": imgres
	}, callback)
}

function gen_base64() {
    var file = document.getElementById("fileImg").files[0];
    lrz(file, { width: 100 }, function (results) {
        //alert(results.base64);
        EditHeadImage(results.base64);
        document.getElementById('Avatar').src = results.base64;
    });
	//r = new FileReader(); //本地预览
	//r.onload = function() {
	//	var imgres = r.result;
	//	EditHeadImage(imgres);
	//}
	//r.readAsDataURL(file); //Base64
}

/* 部分隐藏处理
 ** str 需要处理的字符串
 ** frontLen 保留的前几位
 ** endLen 保留的后几位
 ** cha 替换的字符串
 */
function plusXing(str, frontLen, endLen, cha) {
	var xing = cha + cha + cha + cha;
	return str.substring(0, frontLen) + xing + str.substring(str.length - endLen);
};

/*时间转换*/
function formatTime(obj) {
	var time1 = obj.text();
	var time2 = sc.utils.dateFormat(sc.utils.toDate(time1), "yyyy/MM/dd hh:mm:ss");
	obj.text(time2);
};

//获取验证码倒计时
function timerStart(btn) {
	var i = 119;
	var timer = "";
	timer = setInterval(function() {
		if(i == 0) {
			btn.removeClass("btn_disable");
			btn.html('获取验证码');
			has_click = false;
			clearInterval(timer);
		} else {
			btn.addClass("btn_disable");
			btn.html(i + 's后再试');
			--i;
		}
	}, 1000);
}

//绑定/更换手机号获取验证码
function GetBindTelCode(phonenumber, type) {
	var url = 'api/User/GetBindTelCode';
	var callback = function(data) {
		if(data.code == 0) { //成功
			timerStart($('#sendCode'));
			has_click = true;
			layer.open({
				content: '发送成功，请注意查收！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 1) { //参数有误
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
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
		"Token": token,
		"Mobile": phonenumber,
		"Type": type
	}, callback);
}

//提交绑定手机号
function UpdateMobile(phonenumber, Code, type) {
	var url = 'api/User/UpdateMobile';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '手机号码绑定成功!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/accountSafe.html";
			}, 2000);
		}
		if(data.code == 1) { //参数有误
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
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Mobile": phonenumber,
		"VerifyCode": Code,
		"Type": type
	}, callback);
}
//验证是否可以更换手机号码(验证码是否正确)
function VerifyUpdateCode(phonenumber, Code, successCallback) {
	var url = 'api/User/VerifyUpdateCode';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //参数有误
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
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Mobile": phonenumber,
		"VerifyCode": Code
	}, callback);
}

//验证手机号
function valPhone(Number) {
	var r_phone = /^[1][3,4,5,6,7,8][0-9]{9}$/;
	if(Number == "") {
		layer.open({
			content: '手机号不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(!r_phone.test(Number)) {
		layer.open({
			content: '请输入正确的手机格式!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

//修改登录/支付密码获取验证码
function GetUpdatePswCode(phonenumber, type) {
	var url = 'api/User/GetUpdatePswCode';
	var callback = function(data) {
		if(data.code == 0) { //成功
			timerStart($('#sendCode'));
			has_click = true;
			layer.open({
				content: '发送成功，请注意查收！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 1) { //参数有误
			layer.open({
				content: '短信发送失败，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
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
			has_click = false;
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Mobile": phonenumber,
		"Type": type //1登录密码 0支付密码
	}, callback);
}

//验证修改密码
function VerifyUpdatePsw(pwd1, pwd2, code) {
	var ret = /^[A-Za-z0-9]+$/;
	txt_pwd1 = $.trim(pwd1),
		txt_pwd2 = $.trim(pwd2),
		txt_code = $.trim(code);
	if(!txt_pwd1) {
		layer.open({
			content: '请输入新密码！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_pwd2) {
		layer.open({
			content: '请确认密码！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(txt_pwd1 != txt_pwd2) {
		layer.open({
			content: '两次输入不相同，请重新输入！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(ret.test(txt_pwd1) === false || ret.test(txt_pwd2) === false) {
		layer.open({
			content: '请输入数字与字母组合的新密码!',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		return false;
	}
	if(ret.test(txt_pwd1) === true && txt_pwd1.length > 18) {
		layer.open({
			content: '密码长度超过限制!',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		return false;
	}
	if(ret.test(txt_pwd1) === true && txt_pwd1.length < 6) {
		layer.open({
			content: '请输入至少6位密码!',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		return false;
	}
	if(!txt_code) {
		layer.open({
			content: '请确认验证码！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

//登录密码修改 
function UpdatePassword(phonenumber, Code, newPwd) {
	var url = 'api/User/UpdatePassword';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '修改密码成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html?gotoType=1";
			}, 2000);
		}
		if(data.code == 1) { //参数有误
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
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Mobile": phonenumber,
		"VerifyCode": Code,
		"SecondPassWord": newPwd
	}, callback);
}

//忘记密码
function ForgetPassword(phonenumber, Code, newPwd) {
	var url = 'api/User/ForgetPassword';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '修改密码成功,请重新登录！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html?gotoType=1";
			}, 2000);
		}
		if(data.code == 1) { //参数有误
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
	}
	sc.post(url, {
		"Mobile": phonenumber,
		"VerifyCode": Code,
		"SecondPassWord": newPwd
	}, callback);
}

//忘记密码发送验证码接口
function GetUserSms(tel) {
	var url = 'api/Login/GetUserSms';
	var callback = function(data) {
		if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
		} else if(data.code == 0) {
			has_click = true;
			timerStart($('#sendCode'));
			layer.open({
				content: '发送成功，请注意查收！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '系统错误！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
		}
	}

	sc.post(url, {
		"Mobile": tel,
		"VerifyType": 2
	}, callback);
}

//绑定银行卡
function AddBank(bankName, bankCardNo, bankCardName, bankAddress, tel, VerifyCode) {
	var url = 'api/Bank/AddBank';
	var callback = function(data) {
		if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 0) {
			layer.open({
				content: '绑定银行卡成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/bingCard.html";
			}, 1000);

		} else if(data.code == 99) {
			layer.open({
				content: '系统错误，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"BankName": bankName,
		"BankCardNo": bankCardNo,
		"BankCardName": bankCardName,
		"BankAddress": bankAddress,
		"Mobile": tel,
		"VerifyCode": VerifyCode
	}, callback);
}
//绑定银行卡获取验证码
function GetAddBankSms(tel) {
	var url = 'api/Login/GetUserSms';
	var callback = function(data) {
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
		} else if(data.code == 0) {
			timerStart($('#sendCode'));
			has_click = true;
			layer.open({
				content: '发送成功，请注意查收！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '系统错误！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			has_click = false;
		}
	}

	sc.post(url, {
		"Mobile": tel,
		"VerifyType": 11
	}, callback);
}

//删除绑定银行卡
function DeleteBank(id, item) {
	var url = 'api/Bank/DeleteBank';
	var callback = function(data) {
		if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 0) {
			item.remove();
			layer.open({
				content: '删除银行卡成功！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '系统错误，请重试！',
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

//我的银行卡列表
function BankList(pageNum, successCallback) {
	var url = 'api/Bank/BankList';
	var callback = function(data) {
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 0) { //成功
			successCallback(data);
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum
	}, callback);
}
//银行选择列表
function BankinfoList() {
	var url = 'api/Bank/BankinfoList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 0; i < data.data.length; i++) {
				opt += '<option value="">' + data.data[i].BankName + '</option>';
			}
			$('#selectBank').append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}
//格式化银行卡格式
function BankNostr(str) {
	return str.replace(/\s/g, '').replace(/(\d{4})\d+(\d{4})$/, "**** **** **** $2");
}
//添加银行卡验证
function VerifyAddBank(bankCardName, bankCardNo, bankName, VerifyCode) {
	var txt_CardName = $.trim(bankCardName),
		r_CardNo = /^([1-9]{1})(\d{15}|\d{16}|\d{18})$/;
	txt_CardNo = $.trim(bankCardNo),
		txt_Name = $.trim(bankName),
		txt_Code = $.trim(VerifyCode);
	if(!txt_CardName) {
		layer.open({
			content: '请输入持卡人姓名！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_CardNo) {
		layer.open({
			content: '请输入银行卡号！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!r_CardNo.test(txt_CardNo)) {
		layer.open({
			content: '请输入正确的卡号格式!',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(txt_Name == '请选择银行') {
		layer.open({
			content: '请选择银行！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_Code) {
		layer.open({
			content: '请输入验证码！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

//我的优惠券列表
function CouponList(status, pageNum, pageSize, successCallback, el) {
	var url = 'api/User/CouponList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			console.log(data);
			successCallback(data);
		}
		if(data.code == 1) { //参数有误
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
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Type": status,
		"page": pageNum,
		"pageSize": pageSize
	}, callback, error);
}

//关注店铺列表
function MemberCollectShop(pageNum, pageSize, successCallback, el) {
	var url = 'api/User/MemberCollectionsList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //参数有误
			layer.open({
				content: '数据异常，请重试！',
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
		if(data.code == 99) { //参数有误
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum,
		"pageSize": pageSize,
		"Type": 1
	}, callback, error);
}

//店中店申请
function MemberShopApply(name, tel, Weixin, addr, Remarks) {
	var url = 'api/User/MemberShopApply';
	var callback = function(data) {
		if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 0) {
			layer.open({
				content: '恭喜您申请成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/applysuccess.html";
			}, 1000);

		} else if(data.code == 99) {
			layer.open({
				content: '系统错误，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Name": name,
		"Mobile": tel,
		"Weixin": Weixin,
		"Address": addr,
		"Remarks": Remarks
	}, callback);
}
//店中店申请信息验证
function VerifyShopApply(name, tel, Weixin, addr) {
	var txt_name = $.trim(name),
		r_tel = /^[1][3,4,5,6,7,8,9][0-9]{9}$/,
		txt_tel = $.trim(tel),
		txt_Weixin = $.trim(Weixin),
		txt_addr = $.trim(addr);
	if(!txt_name) {
		layer.open({
			content: '申请人姓名不能为空！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_tel) {
		layer.open({
			content: '手机号不能为空！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!r_tel.test(txt_tel)) {
		layer.open({
			content: '请输入正确的手机格式!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
// 	if(!txt_Weixin) {
// 		layer.open({
// 			content: '微信号不能为空！',
// 			skin: 'msg',
// 			time: 1 //2秒后自动关闭
// 		});
// 		return false;
// 	}
// 	if(!txt_addr) {
// 		layer.open({
// 			content: '地址信息不能为空！',
// 			skin: 'msg',
// 			time: 1 //2秒后自动关闭
// 		});
// 		return false;
// 	}
	return true;
}
//我的小店首页
function MyShopProductlist(pageNum, pageSize, successCallback, el) {
	var url = 'api/User/MemberShopProductlist';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum,
		"pageSize": pageSize
	}, callback, error);
}


//小店产品绑定查询列表页
function MyGetProductlist(parentId, proName, pageNum, pageSize, successCallback, el) {
	var url = 'api/User/GetProductlist';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ClassId": parentId,
		"Name": proName,
		"page": pageNum,
		"pageSize": pageSize
	}, callback, error);
}
//小店产品绑定
function MyShopProductBinding(Pid, Shopid, successCallback) {
	var url = 'api/User/MemberShopProductBinding';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProductId": Pid,
		"ShopId": Shopid
	}, callback);
}
//小店产品一键绑定
function AllProductBinding(listItem, successCallback) {
	var url = 'api/User/MemberShopProductListBinding';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"list": listItem
	}, callback);
}

//小店产品移除
function ShopProductDelete(Pid, id, successCallback) {
	var url = 'api/User/ShopProductDelete';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProductId": Pid,
		"Id": id
	}, callback);
}

//提现银行卡查询
function memberBankList(successCallback) {
	var url = 'api/DrawMoney/memberBankList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}

//提现
function memberDrawMoneyApply(mountnumber, bankId) {
	var url = 'api/DrawMoney/memberDrawMoneyApply';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/withdraw_success.html";
			}, 1000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
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
		if(data.code == 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Amount": mountnumber,
		"BankId": bankId
	}, callback);
}
//提现验证
function VerifyDrawMoneyApply(mountnumber, bankId) {
	var txt_mount = $.trim(mountnumber),
		txt_bankId = bankId;
	if(txt_bankId == '') {
		layer.open({
			content: '请选择银行！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(txt_mount == '') {
		layer.open({
			content: '请输入提现金额！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}
//提现页面
function WithDrawPage() {
	var ww_Price = 0; //提现金额
	var Wallet = 0; //余额
	var bankid = ''; //银行卡id
	GetMemberInfo(function(data) {
		Wallet = data.data.Wallet;
		$('.ww_ky_price').text('零钱余额￥' + Wallet);
		if(Wallet < 100) {
			$('.tips').text('当前余额不能提现，每笔提现需满足100元');
		}
		memberBankList(function(data) {
			var strhtml = "";
			if(data.data.length) {
				$('.card_menu .weui-cell').attr('data-bankid', data.data[0].Id);
				$('.card_menu .name').text(data.data[0].BankName);
				$('.card_menu .bankno').text(BankNostr(data.data[0].BankCardNo));
			}
			$('.card_menu').append(strhtml);
		});
	})
	$('.card_menu').click(function() {
		$('.dd_waylist').html("");
		$('.defaultPage').fadeIn();
		memberBankList(function(data) {
			var strhtml = "";
			for(var i = 0; i < data.data.length; i++) {
				var bankno = data.data[i].BankCardNo;
				if(data.data[i].Id == $('.card_menu .weui-cell').attr('data-bankid')) {
					strhtml += '<li class="active" data-bankid="' + data.data[i].Id + '">';
				} else {
					strhtml += '<li data-bankid="' + data.data[i].Id + '">';
				}
				strhtml += '<i class="icon" style="display:none"><img src="../images/icons/dfbank.png" alt="" /></i>';
				strhtml += '<span class="recom banknane">' + data.data[i].BankName + '</span><span class="bankno">' + BankNostr(bankno) + '</span>';
				strhtml += '</li>';
			}
			$('.dd_waylist').append(strhtml);
			$('.dd_waylist li').click(function() {
				bankid = $(this).attr('data-bankid');
				$(this).addClass('active').siblings().removeClass('active');
				$('.card_menu .weui-cell').attr('data-bankid', bankid);
				$('.card_menu .name').text($(this).find('.banknane').text());
				$('.card_menu .bankno').text($(this).find('.bankno').text());
				closetbtn();
			})
		});
	})
	$('#submitBtn').click(function() {
		ww_Price = $('#ww_price').val();
		bankid = $('.card_menu .weui-cell').attr('data-bankid');
		if(ww_Price > Wallet) {
			$('.tips').text('余额不足，无法提现');
		}
		if(VerifyDrawMoneyApply(ww_Price, bankid)) {
			memberDrawMoneyApply(ww_Price, bankid);
		}
	})

}

function closetbtn() { //关闭层
	$('.defaultPage').fadeOut();
}

//	分类消息
function getNoticeTypeList() {
	var url = 'api/News/NoticeTypeList';
	var callback = function(data) {
		if(data.code === 0) {
			if(data.data.length){
				var interText = doT.template($("#NoticeTypeListTemp").text());
				$("#NoticeTypeList").html(interText(data.data));
			}else{
				emptytpl($("#NoticeTypeList"));
			}			
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
		}
		if(data.code === 2) {
			layer.open({
				content: data.msg,
				btn: ['登录', '取消'],
				yes: function(index) {
					location.href = '/login.html';
					layer.close(index);
				},
				no: function(index) {
					layer.close(index);
				}
			});

		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}

//获取未读消息的总数
function noReadNewNum() {
	var url = 'api/News/NewsCount';
	var callback = function(data) {
		console.log(data);
		if(data.code === 0) {
			if(data.count > 0) {
				$('.head .numSpan').show();
				$('#noReadNum').text(data.count);
			} else {
				$('.head .numSpan').hide();
			}
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}

//消息列表
function getNewsList(msgType, pageIndex, pageSize,Shopid, successCallback, el) {
	var url = 'api/News/MyNoticeList';
	var callback = function(data) {
		if(data.code === 0) {			
			successCallback(data);
		}
		if(data.code === 2) {
			layer.open({
				content: data.msg,
				btn: ['登录', '取消'],
				yes: function(index) {
					location.href = '/login.html';
					layer.close(index);
				},
				no: function(index) {
					layer.close(index);
				}
			});

		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"MsgType": msgType,
		"Page": pageIndex,
		"PageSize": pageSize,
		"ShopId":Shopid
	}, callback, error);
}
//标记已读
function MessgeContent(newsId) {
	var url = 'api/News/ReadNoticeInfo';
	var callback = function(data) {
		if(data.code == 0) {

		} else if(data.code == 1) {

		} else if(data.code == 2) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '服务器开了个小差!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"newsid": newsId
	}, callback);
}


















