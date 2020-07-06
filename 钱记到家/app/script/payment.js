var userId = "";
var token = "";
var orderNo = "";
var wxopenid = ""; //微信id
var code = "";
var paymentType = 0;
var useBalance;
var isWallet = 0; //是否使用余额
var balance = 0; //余额
var sureAmount = 0;
var orderAmount = 0;
var gotoType = 0; // 0：代表是普通下单要跳转的；1：企业购下单跳转；2：拼团要团转的
var groupId; //拼团产品id
var groupRecordId; //拼团记录id
var isOkPay = false;
$(function() {
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	if(sc.utils.getDecodeQueryString('gotoType') !== "") {
		gotoType = parseInt(sc.utils.getDecodeQueryString('gotoType'));
	}
	if(sc.utils.getDecodeQueryString('groupId') !== "") {
		groupId = parseInt(sc.utils.getDecodeQueryString('groupId'));
	}
	if(sc.utils.getDecodeQueryString('groupRecordId') !== "") {
		groupRecordId = parseInt(sc.utils.getDecodeQueryString('groupRecordId'));
    }
    GetMemberInfo(function (data) {
        $('#balance').text(parseFloat(data.data.Wallet).toFixed(2));
        balance = data.data.Wallet;
    });

    $("#RollBack").click(function () {
        if (gotoType === 1) { //企业购过来的
            window.location.href = '/member/order_fl1.html?source=pay';
        }
        if (gotoType === 0) { //普通下单过来的
            window.location.href = '/member/order.html?source=pay';
        }
        if (gotoType === 2) { //拼团下单过来的
            window.location.href = '/pintuan.html?type=1&source=pay';
        }
    });

	if(!sc.auth.isLogin()) { //如果未登录的
		layer.open({
			content: '您还没有登录，是否要登录？',
			btn: ['确定', '取消'],
			yes: function(index) {
				window.location.href = "/login.html";
				layer.close(index);
			},
			no: function(index) {
				layer.close(index);
			}
		});
		return false;
	}
	orderNo = sc.utils.getDecodeQueryString("orderno");
	getOrdersMoney();
	$(".waylist li").click(function() {
		paymentType = $(this).index();
		$(this).addClass("active").siblings().removeClass("active");
	});

	code = sc.utils.getQueryString("code"); //授权code
	if(code) {
		//		layer.open({
		//			content: code,
		//			skin: 'msg',
		//			time: 2 //2秒后自动关闭
		//		});
		//requestWxPay();
	}

	$(".payment_order").on('click', function() {
		if(paymentType === 0) {
			//alert(paymentType);
			requestWxPay();
		}
    });
    $("#use__usablePrice").click(function () {
        if ($(this).is(':checked')) {
            isWallet = 1;
        } else {
            isWallet = 0;
        }
        CalculationAmount();
    });
});



function getOrdersMoney() {
    var url = "/api/Order/GetOrdersAmount";
	var calback = function(data) {

		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
		} else if(data.code === 0) {
            $(".ProductAmount").text(parseFloat(data.data.TotalPrice).toFixed(2));
            $(".discountedAmount").text(parseFloat(data.data.discountedAmount).toFixed(2));
            $(".freight").text(parseFloat(data.data.expressprice).toFixed(2));
            if (parseFloat(data.data.walletPayAmount) > 0) {
                $("#walletPayAmount").show();
                $(".walletPayAmount").text(parseFloat(data.data.walletPayAmount).toFixed(2));
            }
            orderAmount = (parseFloat(data.data.TotalPrice) - parseFloat(data.data.discountedAmount) + parseFloat(data.data.expressprice)).toFixed(2);
            $(".OrderAmount").text(orderAmount);
            sureAmount = orderAmount;
            $(".surePrice").text(sureAmount);
           
		} else {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": orderNo
	}, calback);
}


function CalculationAmount() {
    if ($('#use__usablePrice').is(":checked")) {
        if (parseFloat(balance) > parseFloat(orderAmount)) {
            sureAmount = 0;
            $('#balance').text(parseFloat(parseFloat(balance) - parseFloat(orderAmount)).toFixed(2));
            $(".surePrice").text("0.00");
            $(".payType").hide();
        } else {
            if (balance > 0) {
                sureAmount = parseFloat(parseFloat(orderAmount)-parseFloat(balance)).toFixed(2);
                $(".surePrice").text(sureAmount);
                $('#balance').text("0.00");
                if (sureAmount > 0) {
                    $(".payType").show();
                } else {
                    $(".payType").hide();
                }
            }
        }
    } else {
        $('#balance').text(parseFloat(balance).toFixed(2));
        $(".surePrice").text(parseFloat(orderAmount).toFixed(2));
        sureAmount = orderAmount;
        $(".payType").show();
    }
}

function requestWxPay() {
	var url = "/api/Order/ConfirmWeiXinPay";
	var calback = function(data) {
		//		layer.open({
		//			content: JSON.stringify(data),
		//			skin: 'msg',
		//			time: 60 //2秒后自动关闭
		//		});
        if (data.code === 2) {
            layer.open({
                content: '登录超时，请重新登录!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
        } else if (data.code === 201) {
            window.location.href = data.data;
        } else if (data.code == 5) {            
            if (!gotoType) { gotoType = 0; }
            if (gotoType === 1) { //企业购过来的
                window.location.href = '/member/order_fl1.html?source=pay';
            }
            if (gotoType === 0) { //普通下单过来的
                window.location.href = '/member/order.html?source=pay';
            }
            if (gotoType === 2) { //拼团下单过来的
                window.location.href = '/pintuan_info.html?groupId=' + groupId + '&id=' + groupRecordId +"&source=pay";
            }
        } else if (data.code === 0) {
			wxopenid = data.data.openid;
			var u = navigator.userAgent;
			var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
			var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
			//if(!isOkPay && isAndroid) {
			//	isOkPay = true;
			//	requestWxPay();
			//}
			callpay(data.data.JsParam);
		} else {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": orderNo,
		"WxOpenid": wxopenid,
        "WxCode": code,
        "isWallet": isWallet,
        "surePrice": sureAmount,
		"NewUrl": window.location.href
	}, calback);
}

//是否是微信（打开）
function is_weixn() {
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) === "micromessenger") {
		return true;
	} else {
		return false;
	}
}

function onBridgeReady(param) {
	var parameter = JSON.parse(param);
	WeixinJSBridge.invoke(
		'getBrandWCPayRequest', parameter,
		function(res) {
			if(res.err_msg === 'get_brand_wcpay_request:ok') {
				//layer.open({
				//	content: '支付成功',
				//	skin: 'msg',
				//	time: 1 //1秒后自动关闭
    //            });
                //返回支付成功，后端需验证支付结果

                layer.closeAll();
                if (!gotoType) { gotoType = 0;}
				if(gotoType === 1) { //企业购过来的
                    window.location.href = '/member/order_fl1.html?source=pay';
				}
				if(gotoType === 0) { //普通下单过来的
                    window.location.href = '/member/order.html?source=pay';
				}
				if(gotoType === 2) { //拼团下单过来的
                    window.location.href = '/pintuan_info.html?groupId=' + groupId + '&id=' + groupRecordId +"&source=pay";
				}
			} else {
				layer.open({
					content: '支付失败',
					skin: 'msg',
					time: 3 //2秒后自动关闭
                });
                if (!gotoType) { gotoType = 0; }
				if(gotoType === 1) { //企业购过来的
                    window.location.href = '/member/order_fl1.html?source=pay';
				}
				if(gotoType === 0) { //普通下单过来的
                    window.location.href = '/member/order.html?source=pay';
				}
				if(gotoType === 2) { //拼团下单过来的
                    window.location.href = '/pintuan.html?type=1&source=pay';
				}
			}
		}
	);
}

function callpay(param) {
	if(typeof WeixinJSBridge === 'undefined') {
		if(document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		} else if(document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady(param);
	}
}