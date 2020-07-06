var thinklogin = false;
//获取验证码倒计时
var has_click = false;
function timerStart() {
	var i = 119;
	var timer = "";
	timer = setInterval(function() {
		if(i == 0) {
			$('#sendCode').html('获取验证码');
			has_click = false;
			clearInterval(timer);
		} else {
			$('#sendCode').html(i + '稍后再试');
			--i;
		}
	}, 1000);
}
//验证手机号
function valPhone() {
	var r_phone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
	var phoneNumber = $.trim($('#phoneNumber').val());
	if(phoneNumber == "") {
		layer.open({
			content: '手机号不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(!r_phone.test(phoneNumber)) {
		layer.open({
			content: '请输入正确的手机格式!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	return true;
}
//其他验证
function regResetPwdValOther() {
	var code = $.trim($('#txtCode').val());
	if(code == "") {
		layer.open({
			content: '验证码不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(!$('#agree').is(":checked")) {
		layer.open({
			content: '请勾选同意用户协议!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

/********登录页面********/
function scLogin() {
	var goType = sc.utils.getQueryString("gotoType");
	//睁眼闭眼
	$('.btn-isShowPwd').click(function() {
		if($(this).hasClass("btn-hidePwd")) {
			$(this).removeClass('btn-hidePwd').addClass("btn-showPwd");
			$(this).parents('.weui-cell').find('.weui-input').attr("type", 'text');
		} else {
			$(this).removeClass('btn-showPwd').addClass("btn-hidePwd");
			$(this).parents('.weui-cell').find('.weui-input').attr("type", 'password');
		}
	});
	//其他验证
	function valOther() {
		var pwd = $.trim($('#txtPwd').val());
		if(pwd == "") {
			layer.open({
				content: '密码不能为空!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			return false;
		}
		if(pwd.length < 6) {
			layer.open({
				content: '密码长度不能小于6个字符!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			return false;
		}
		return true;
	}
	//登录
	$('#login').click(function() {
		var phoneNumber = $.trim($('#phoneNumber').val()); //输入的电话号码
		var pwd = $.trim($('#txtPwd').val()); //输入密码
		if(valPhone() && valOther()) {
			console.log("goType:"+goType);
			sc.login(phoneNumber,pwd,goType);
		}
	});
}

/*******注册页面***********/
function getUrlParam(url, name) {
    if (url.indexOf("?") > -1) {
        url = url.split("?")[1];
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = url.match(reg);
        if (r != null) return unescape(r[2]);
    }
    return null;
}
function scRegister(_callback, proId, spreadcode) {
	var code = ""; //输入的验证码
	var timer = "";
	$('#agree').change(function() {
		if($(this).is(":checked")) {
			$(this).attr('checked', true);
			$(this).parents('.IconsCK').addClass('checked');
			console.log("全选");
		} else {
			$(this).attr('checked', false);
			$(this).parents('.IconsCK').removeClass('checked');
		}
	});
	//发送验证码接口
	function sendCode(tel) {
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
				layer.open({
					content: '发送成功，请注意查收！',
					skin: 'msg',
					time: 2 //2秒后自动关闭
                });
                if (data.data == "login") {
                    thinklogin = true;
                }
				has_click = true;
				timerStart();
			} else if(data.code == 99) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				has_click = false;
			}
		}

		sc.post(url, {
			"Mobile": tel,
			"VerifyType": 0
		}, callback);
	}

    //注册接口
    var _registerfay = true;
    function phoneNumberRegister(tel, code, inviteCode) {
        if (_registerfay == true) {
            _registerfay = false;
            var url = 'api/Login/MobileRegister';
            var callback = function (data) {
                _registerfay = true;
                if (data.code == 0) {
                    layer.open({
                        content: '注册成功',//，请使用注册手机号登陆!',
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                    sc.login(tel, "1qazXDR%", 1);
                    setTimeout(function () {
                        //if (_callback != null && _callback.indexOf("details.html") > -1) {
                        //    var reUrl = _callback;
                        //    var parms = getUrlParam(_callback, "parms");
                        //    if (parms) {
                        //        reUrl = window.location.protocol + "//" + window.location.host + "/activityJump.html?activity=1&proId=" + proId + "&spreadcode=" + spreadcode;
                        //    }
                        //    location.href = reUrl;
                        //}
                        //else
                            location.href = "/indexv.html";
                    }, 2000);
                } else if (data.code == 1) {
                    layer.open({
                        content: data.msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                } else if (data.code == 99) {
                    layer.open({
                        content: data.msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                }

            }
            sc.post(url, {
                "Mobile": tel,
                "VerifyCode": code,
                "InviteCode": inviteCode,
                "UserInfo": sc.auth.getUserInfo(),
                "ProductId": proId,
                "SpreadCode": spreadcode
            }, callback);
        }
    }

	//点击发送验证码
	$('#sendCode').click(function() {
		var phoneNumber = $.trim($('#phoneNumber').val());
		console.log(phoneNumber);
		if(valPhone()) {
			if(!has_click) {
				sendCode(phoneNumber);
			}
		}

	});
	//点击注册按钮
    $('#btn_register').click(function () {
        // 测试
		var phoneNumber = $.trim($('#phoneNumber').val());		
		var code = $.trim($('#txtCode').val());
		var inviteCode = $.trim($('#inviteCode').val());
        if (valPhone() && regResetPwdValOther()) {
            if (thinklogin == false)
                phoneNumberRegister(phoneNumber, code, inviteCode);
            else
                sc.login(phoneNumber, "1qazXDR%", 2);
		}
	});

}