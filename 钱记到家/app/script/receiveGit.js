var receiveNo; //礼包领取码
var userId;
var token;
var pegeindex = 1;
var pageNum = 0;
var addressId; //地址id
var codeId; //礼包id
var thisUrl = window.location.href.split("//")[0] + "//" + window.location.href.split("//")[1].split("/")[0];
$(function () {
    var idx = layer.open({
        type: 2
        , content: 'loading...'
    });  
    var s = setInterval(function () {
        if (!iswx() || (iswx() && isWxLoginOver == true)) {
            clearInterval(s);
            layer.close(idx);
            setTimeout(function () {
                //登录过了            
                receiveNo = window.location.href.split("?")[1].split("&")[0].split("=")[1];
                codeId = window.location.href.split("?")[1].split("&")[1].split("=")[1];
                console.log(receiveNo + "——" + codeId)
                //判断是否登录           
                if (sc.auth.isLogin()) {
                    $("#tijiao").css("display", "block");
                    userId = sc.utils.getCookieValue("UserId");
                    token = sc.utils.getCookieValue("Token");
                    GetGiftOrderinfo(userId, token)
                }
                $("#tijiaDian").click(function () {
                    if ($("#pop-addrlist").css("display") === "none") {
                        $("#tijiao").css("display", "none");
                        $("#denglu").css("display", "block");
                    } else {
                        $("#pop-addrlist").css("display", "none")
                    }

                });

                $("#btnPay").click(function () {
                    ExchangeGift();
                })

                scLogin();
                hasDefaultAddress(); //获取默认地址

                cartString = sc.utils.getQueryString("cartItem"); //获取传进来的购物车id的string值
                orderSType = parseInt(sc.utils.getQueryString("smType"));
                //	proId = sc.utils.getQueryString("Pid");   
                userId = sc.utils.getCookieValue("UserId");
                token = sc.utils.getCookieValue("Token");
                if (sc.utils.getQueryString("spec")) {

                    skuTxt = sc.utils.getQueryString("spec").split("_").join(" ");
                    console.log(skuTxt);
                }
                if (sc.utils.getQueryString("num")) {
                    detailProNum = Number(sc.utils.getQueryString("num"));
                }
                if (sc.utils.getQueryString("Pid")) {
                    proId = sc.utils.getQueryString("Pid");
                }
                if (orderSType === 0) {
                    $('#detailsSureOrder').show();
                } else {
                    $('#detailsSureOrder').hide();
                }
                //	proId = 181;
                //	skuTxt = '湖北';
                //获取余额

                $('.fixed__defaultPage .btn_back').click(function () {
                    //		$(this).parents('.fixed__defaultPage').hide();
                    //		location.reload();
                    window.location.href = window.location.href + "?id=" + 10000 * Math.random();
                });

                //弹出优惠券
                $('#couponBtn').click(function () {
                    $('#pop-CouponTpl').show().siblings(".shadeAll").hide();
                });

                //没有地址的时候要新添加地址,新增地址
                $('#noAddress,#btnEadit').click(function () {
                    addressId = '';
                    $('#pop-addrEadit').show();
                    $('#pop-addrEadit .from .ipt0').val("");
                    $('#address .selected-address li').text("请选择").removeClass('active');
                    $('#address .address-content ul').html("");
                    $('#Del_addr').hide();
                    $('#pop-addrEadit .head .title').text('添加收货地址');
                });

                $('#HasAddressBox').click(function () { //显示地址列表页弹窗
                    $('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
                });
                //地址列表
                $('.Address').dropload({
                    scrollArea: window,
                    domDown: {
                        domClass: 'dropload-down',
                        domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
                        domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                        domNoData: '<div class="dropload-noData">已经到底了</div>'
                    },
                    loadDownFn: function (me) {
                        if (pageNum > 1) return;
                        pageNum++;
                        AddressList(pageNum, pageSize, true, function (data) {
                            var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
                            if (data.data.length === 0 && !data.isok) {
                                me.lock();
                                me.noData();
                                me.resetload();
                                if (pageNum === 1) {
                                    $(".dropload-down").remove();
                                    emptytpl($('.Address'));
                                }
                            } else if (data.data.length < 9) {
                                setTimeout(function () {
                                    laytpl(gettpl).render(data.data, function (html) {
                                        //得到的模板渲染到html
                                        $('#Addrlist_Div').append(html);
                                    });

                                    me.resetload();
                                }, 1000);
                                me.lock("down");
                                me.noData();
                            } else {
                                if (pageNum === 1) {
                                    $("#Addrlist_Div").html("");
                                }
                                setTimeout(function () {
                                    laytpl(gettpl).render(data.data, function (html) {
                                        //得到的模板渲染到html
                                        $('#Addrlist_Div').append(html);
                                    });
                                    me.resetload();
                                }, 1000);
                            }
                        }, me)
                    }
                })

                $('#Addrlist_Div').on('click', '.addritem', function () {
                    $('#pop-addrlist').hide();
                    addressId = $(this).attr("data-id");
                    $("input[id='use__usablePrice']").prop("checked", false);
                    if (orderSType === 1) {
                        payAllMoney = 0;
                        proNum = 0;
                        getCartsOrder();
                    }
                    if (orderSType === 0) {
                        getProductInfo();
                    }
                    var telTxt = $(this).find('.tel').text(); //手机
                    var nameTxt = $(this).find('.name').text(); //姓名
                    var addressTxt = $(this).find('.addrtxt').text(); //全称地址
                    if ($('.HasAddress').length > 0) {
                        $('.HasAddress .name').text(nameTxt); //拿到姓名
                        $('.HasAddress .tel').text(telTxt); //拿到电话
                        $('.HasAddress .weui-cell__bd .txt').text(addressTxt); //拿到地址
                    } else {
                        $('#noAddress').hide();
                        var addressJson = {};
                        addressJson["id"] = addressId;
                        addressJson["tel"] = telTxt.split("：")[1];
                        addressJson["name"] = nameTxt.split("：")[1];
                        addressJson["addressinfo"] = addressTxt;
                        romanceSmAddress(addressJson);
                    }

                })
                $('#Addrlist_Div').on('click', '.editbtn', function (e) {
                    e.stopPropagation();
                    var addrId = parseInt($(this).parents('.addritem').attr('data-id'));
                    $("input[id='use__usablePrice']").prop("checked", false);
                    $('#pop-addrEadit').show().siblings('.fixed__defaultPage').hide();
                    //编辑地址或者添加地址
                    $('#pop-addrEadit .head .title').text('编辑收货地址');
                    GetAddressInfo(addrId, function (data) {
                        var selectedName = [];
                        selectedName[0] = data.data.province;
                        selectedName[1] = data.data.city;
                        selectedName[2] = data.data.district;
                        selectedName[3] = data.data.street;
                        $('#name').val(data.data.name);
                        $('#tel').val(data.data.tel);
                        $('#selectCity').attr('data-code', selectedName.join(","));
                        $('#selectCity').val(data.data.addressstr);
                        $('#fullAddress').val(data.data.address);
                        $('#switch1').attr("data-def", data.data.is_def);
                        if (data.data.is_def === 1) {
                            $('#switch1').prop({
                                checked: true
                            });
                        } else {
                            $('#switch1').prop({
                                checked: false
                            });
                        }
                    });
                    $('#Del_addr').show();
                    $('#Del_addr').click(function () {
                        layer.open({
                            content: '您确定要删除该地址吗？',
                            btn: ['删除', '取消'],
                            skin: 'footer',
                            yes: function (index) {
                                DeleteAddress(addrId, function (data) {
                                    layer.open({
                                        content: '删除地址成功！',
                                        skin: 'msg',
                                        time: 1 //1秒后自动关闭
                                    });
                                    $('#Addrlist_Div').find('.addritem').each(function () {
                                        if (parseInt($(this).attr('data-id')) === addrId) {
                                            $(this).remove();
                                            if (addrId === addressId) {
                                                addressId = 0;
                                            }
                                            console.log("addressId假的：" + addressId);
                                        }
                                    });
                                    if (!$('#Addrlist_Div').find('.addritem').length) {
                                        addressId = 0;
                                        $('#HasAddressBox').html("");
                                        $('#noAddress').show();
                                    }
                                    $('#pop-addrEadit').hide();
                                    $('#pop-addrlist').show();
                                });
                            }
                        });
                    })

                });

                selectCity(); //弹出城市选择四级联动
                //		点击是否设置为默认
                $("#switch1").click(function () {
                    var def = parseInt($(this).attr("data-def"));
                    if (def === 0) {
                        $(this).attr("data-def", "1");
                    } else {
                        $(this).attr("data-def", "0");
                    }
                });
                $('#submitBtn').click(function () {
                    var txt_name = $('#name').val();
                    txt_tel = $('#tel').val(),
                        isDefault = $("#switch1").attr("data-def"),
                        txt_fullAddress = $('#fullAddress').val(),
                        txt_Zipcode = "",
                        selectCityTxt = $('#selectCity').val(),
                        selectCityCode = $('#selectCity').attr("data-code"),
                        selectedArr = [];
                    selectedArr.push(selectCityCode.split(","));
                    var txt_Province = selectedArr[0][0],
                        txt_City = selectedArr[0][1],
                        txt_District = selectedArr[0][2],
                        txt_Street = selectedArr[0][3];
                    if (valCreateAddress(txt_name, txt_tel, selectCityTxt, txt_fullAddress)) {
                        if (addressId) { //表示修改地址
                            UpdateAddress(addressId, txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
                        } else { //表示添加地址
                            AddAddress2(txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
                        }
                    }
                })
            }, 100);
        }
    },300);
});

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
			$('#sendCode').html(i + 's后再试');
			--i;
		}
	}, 1000);
}

function timerStart2() {
	var i = 119;
	var timer = "";
	timer = setInterval(function() {
		if(i == 0) {
			$('#sendCode2').html('获取验证码');
			has_click = false;
			clearInterval(timer);
		} else {
			$('#sendCode2').html(i + 's后再试');
			--i;
		}
	}, 1000);
}
//验证手机号
function valPhone() {
	var r_phone = /^[1][3,4,5,6,7,8][0-9]{9}$/;
	var phoneNumber = $.trim($('#numLogin').val());
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

function valPhone2() {
	var r_phone = /^[1][3,4,5,6,7,8][0-9]{9}$/;
	var phoneNumber = $.trim($('#phoneNumber1').val());
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

function valPhone3() {
	var r_phone = /^[1][3,4,5,6,7,8][0-9]{9}$/;
	var phoneNumber = $.trim($('#phoneNumber2').val());
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
	var pwd = $.trim($('#txtPwd').val());
	var pwd2 = $.trim($('#txtPwd2').val());
	var code = $.trim($('#txtCode').val());
	if(code == "") {
		layer.open({
			content: '验证码不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(pwd == "" || pwd2 == "") {
		layer.open({
			content: '密码不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(pwd != pwd2) {
		layer.open({
			content: '两次输入密码不同!',
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

function regResetPwdValOther2() {
	var pwd = $.trim($('#pwd1').val());
	var pwd2 = $.trim($('#pwd2').val());
	var code = $.trim($('#VerifyCode').val());
	if(code == "") {
		layer.open({
			content: '验证码不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(pwd == "" || pwd2 == "") {
		layer.open({
			content: '密码不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(pwd != pwd2) {
		layer.open({
			content: '两次输入密码不同!',
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
		var pwd = $.trim($('#pawLogin').val());
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
		var phoneNumber = $.trim($('#numLogin').val()); //输入的电话号码
		var pwd = $.trim($('#pawLogin').val()); //输入密码
		if(valPhone() && valOther()) {
			console.log("goType:" + goType);
			newLogin(phoneNumber, pwd);
		}
	});
}

//登录接口
function newLogin(username, password) {
	$.ajax({
		type: 'post',
		url: sc.serverUrl + 'api/Login/LoginByMobile',
		data: {
			"Mobile": username,
			"PassWord": password
		},
		cache: false,
		async: true,
		success: function(data) {
			console.log(data)
			if(data.code == 0) {
				sc.auth.setToken(data.data.Token);
				sc.auth.setUserId(data.data.UserId);
				userId = data.data.UserId
				token = data.data.Token
				GetGiftOrderinfo(userId, token);
				//				$("#denglu").css("display", "none");
				//				$("#tijiao").css("display", "block");
				location.reload();
			} else if(data.code == 1) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});

			}
		},
		error: function(data) {

		}
	})
}

/*******注册页面***********/

function scRegister() {
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
					content: '该手机号已经注册过啦，请换一个重试!',
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
				has_click = true;
				timerStart();
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
			"VerifyType": 0
		}, callback);
	}

	//注册接口
	function phoneNumberRegister(tel, r_password, code, inviteCode) {
		var url = 'api/Login/MobileRegister';
		var callback = function(data) {
			if(data.code == 0) {
				layer.open({
					content: '注册成功，请使用注册手机号登陆!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				setTimeout(function() {
					location.reload();
				}, 2000);
			} else if(data.code == 1) {
				layer.open({
					content: '短信验证码错误!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			} else if(data.code == 99) {
				layer.open({
					content: '系统错误!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}

		}
		sc.post(url, {
			"Mobile": tel,
			"Password": r_password,
			"VerifyCode": code,
			"InviteCode": inviteCode
		}, callback);
	}

	//点击发送验证码
	$('#sendCode').click(function() {
		var phoneNumber = $.trim($('#phoneNumber1').val());
		console.log(phoneNumber);
		if(valPhone2()) {
			if(!has_click) {
				sendCode(phoneNumber);
			}
		}

    });

	//点击注册按钮
    $('#btn_register').click(function () {
		var phoneNumber = $.trim($('#phoneNumber1').val());
		var pwd = $.trim($('#txtPwd').val());
		var code = $.trim($('#txtCode').val());
		var inviteCode = $.trim($('#inviteCode').val());
		if(valPhone2() && regResetPwdValOther()) {
			phoneNumberRegister(phoneNumber, pwd, code, inviteCode);
		}
    });

}

//忘记密码
function ForgetPassword(phonenumber, Code, newPwd) {
	var url = 'api/User/ForgetPassword';
	var callback = function(data) {
		console.log(data)
		if(data.code == 0) { //成功
			layer.open({
				content: '修改密码成功,请重新登录！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				$("#wangji").css("display", "none");
				$("#denglu").css("display", "block");
			}, 2000);
		}
		if(data.code == 1) { //参数有误
			layer.open({
				content: '修改失败，请稍后重试！',
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
			//			timerStart($('#sendCode'));
			layer.open({
				content: '发送成功，请注意查收！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			timerStart2();
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

//获取礼包信息
function GetGiftOrderinfo(userId, token) {
	var url = "api/Gift/GetGiftOrderinfo";
	var callback = function(data) {
		if(data.code===0){
			$(".shopStoreOrder .name a").text(data.data.Title);
			$(".shopStoreOrder .flexItem .num").text(data.data.Price)
			$(".orderTotal").text(data.data.Price);
			$("#total4").text(data.data.Price);
			$(".m-pro img").attr("src",data.data.PicNo);
			$(".storeName .name").text(data.data.ShopName);
			if(data.data.ReceiveStatus!=0){
                $("#btnPay").addClass('disable');
                $("#btnPay").html("已过期");
			}
		}else{
			$("#btnPay").addClass('disable');
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		UserId: userId,
		Token: token,
		ReceiveNo: receiveNo
	}, callback);
}

//领取礼包接口
function ExchangeGift() {
	if(addressId) {
		var url = "api/Gift/ExchangeGift"
		var callback = function(data) {
			if(data.code === 0) {
				layer.open({
					content: '领取成功！',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				$("#btnPay").addClass('disable');
				//$("#btnPay").css("background-color", "#c2c2c2");
				setTimeout(function(){
					window.location.href = thisUrl + "/member/order_fl.html";
				},2000)
				
			}
			else {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
//				$("#btnPay").addClass('disable');
				//$("#btnPay").css("background-color", "#c2c2c2");
			}
		}
		sc.post(url, {
			UserId: userId,
			Token: token,
			Remarks: $(".to_shop_msg").val(),
			ReceiveNo: receiveNo,
			AddressId: addressId,
			Id: codeId
		}, callback)
	}else{
		layer.open({
			content: "请添加地址",
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
	}

}
//查看是否有默认地址
function hasDefaultAddress() {
	var url = 'api/Address/defaultaddress_New';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			//成功
			if(data.data) {
				addressId = data.data.id;
				romanceSmAddress(data.data);
				$('#noAddress').hide();
			} else {
				//没有默认地址的时候
				$('#noAddress').show();
			}
		} else if(data.code == 1) {
			//			$('#noAddress').show();

			//1:失败
		} else if(data.code == 2) {
			//需要重新登录
		} else if(data.code == 99) {
			//系统错误
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}

//渲染已经选择地址的html,或是查出有默认地址的时候渲染
function romanceSmAddress(data) {
	console.log("ddd");
	if(data) {
		var strHtml = '';
		strHtml += '<div class="HasAddress" data-id="' + data.id + '">';
		strHtml += '<div class="flex nameBox">';
		strHtml += '<span class="icon"></span>';
		strHtml += '<div class="flex1 name">收件人：' + data.name + '</div>';
		strHtml += '<div class="flex1 tel">手机：' + data.tel + '</div>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell">';
		strHtml += '<div class="weui-cell__hd">';
		strHtml += '<span class="icon"><img src="images/icons/dd_addr.png" alt="" class="icon-addr" /></span>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell__bd">';
		strHtml += '<p class="txt">' + data.addressinfo + '</p>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell__ft">';
		strHtml += '<span class="icon-arrow icon-arrowRight"></span>';
		strHtml += '</div>';
		strHtml += '</div>';
		strHtml += '</div>';
		$('#HasAddressBox').append(strHtml);
	}

}

//添加收货地址
function AddAddress2(name, tel, isDefault, Province, City, District, Street, fullAddress, Zipcode, sourceType) {
	var url = 'api/Address/AddAddress';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '新增地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});

			if(sourceType == 1) {
				$('#Addrlist_Div').html("");
//				$('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
				//地址列表
				AddressList(1, pageSize,false,function(re) {
					var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
					laytpl(gettpl).render(re.data, function(html) {
						//得到的模板渲染到html
						document.getElementById('Addrlist_Div').innerHTML = html;
					});
				});
				//location.href = '/smOrder.html?addressId=' + data.data.id + '';
			} else {
//				setTimeout(function() {
//					location.href = '/member/address.html';
//				}, 2000);

			}
//			location.reload();
			window.location.href=window.location.href+"?id="+10000*Math.random();
			
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
		"Token": token,
		"Consignee": name,
		"Mobile": tel,
		"IsDefault": isDefault,
		"ProvinceCode": Province,
		"CityCode": City,
		"DistrictCode": District,
		"StreetCode": Street,
		"FullAddress": fullAddress,
		"PostCode": Zipcode
	}, callback);
}