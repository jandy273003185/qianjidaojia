var pageNum = 1;
var pageSize = 9;
var addressId = 0;

var userId;
var token;

var proId;
var skuTxt = '';
var remarkTxt = ''; //商品详情页过来的商家留言
var detailProNum = 1;
var freightmoney = 0; //直接下单的邮费
var isWallet = 0; //是否使用余额
var balance = 0; //余额
var flagClick = false;
function checkClick() {       
	if(flagClick == true) {        
//		layer.open({
//			content: '订单提交中!',
//			type: 2
//		});        
		return false;      
	}      
	flagClick  = true;      
	setTimeout(function() {        
		flagClick = false;      
	}, 4000);      
	return true;   
} 
$(function() {
	proId = sc.utils.getQueryString("Pid");
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	if(sc.utils.getQueryString("num")) {
		detailProNum = Number(sc.utils.getQueryString("num"));
	}
	if(sc.utils.getQueryString("num")) {
		skuTxt = sc.utils.getQueryString("spec");
	}
	//获取余额
	//	
	GetMemberInfo(function(data) {
		$('#balance').text(data.data.Wallet);
		balance = data.data.Wallet;
	});

	hasDefaultAddress(); //获取默认地址
	$('.fixed__defaultPage .btn_back').click(function() {
		$(this).parents('.fixed__defaultPage').hide();
	});

	//没有地址的时候要新添加地址,新增地址
	$('#noAddress,#btnEadit').click(function() {
		addressId = 0;
		addrId =0;
		$('#pop-addrEadit').show();
		$('#pop-addrEadit .from .ipt0').val("");
		$('#address .selected-address li').text("请选择").removeClass('active');
		$('#address .address-content ul').html("");
		$('#Del_addr').hide();
		$('#pop-addrEadit .head .title').text('添加收货地址');
	});

	$('#HasAddressBox').click(function() { //显示地址列表页弹窗
		$('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
	});
	//地址列表
	AddressList(pageNum, pageSize,false, function(data) {
		var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
		laytpl(gettpl).render(data.data, function(html) {
			//得到的模板渲染到html
			$('#Addrlist_Div').append(html);
		});
	});

	$('#Addrlist_Div').on('click', '.addritem', function() {
		$('#pop-addrlist').hide();
		addressId = $(this).attr("data-id");		
		getProductInfo();
		var telTxt = $(this).find('.tel').text(); //手机
		var nameTxt = $(this).find('.name').text(); //姓名
		var addressTxt = $(this).find('.addrtxt').text(); //全称地址
		if($('.HasAddress').length > 0) {
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
	});
	$('#Addrlist_Div').on('click', '.editbtn', function(e) {
		e.stopPropagation();
		addrId = parseInt($(this).parents('.addritem').attr('data-id'));
		console.log("选中的地址id:" + addrId);		
		$('#pop-addrEadit').show().siblings('.fixed__defaultPage').hide();
		//编辑地址或者添加地址
		$('#pop-addrEadit .head .title').text('编辑收货地址');
		GetAddressInfo(addrId, function(data) {
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
			if(data.data.is_def === 1) {
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
		$('#Del_addr').click(function() {
			layer.open({
				content: '您确定要删除该地址吗？',
				btn: ['删除', '取消'],
				skin: 'footer',
				yes: function(index) {
					DeleteAddress(addrId, function(data) {
						layer.open({
							content: '删除地址成功！',
							skin: 'msg',
							time: 1 //1秒后自动关闭
						});
						
						$('#Addrlist_Div').find('.addritem').each(function() {
							if(parseInt($(this).attr('data-id')) === addrId) {
								$(this).remove();
								if(addrId === addressId) {
									addressId = 0;
								}
							}
						});
						if(!$('#Addrlist_Div').find('.addritem').length) {
							addressId = 0;
							$('#HasAddressBox').html("");
							$('#noAddress').show();
						}
						$('#pop-addrEadit').hide();
						$('#pop-addrlist').show();
						addrId = 0;
					});
				}
			});
		});
	});

	selectCity(); //弹出城市选择四级联动
	//		点击是否设置为默认
	$("#switch1").click(function() {
		var def = parseInt($(this).attr("data-def"));
		if(def === 0) {
			$(this).attr("data-def", "1");
		} else {
			$(this).attr("data-def", "0");
		}
	});
	$('#submitBtn').click(function() {
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
		if(valCreateAddress(txt_name, txt_tel, selectCityTxt, txt_fullAddress)) {
			if(addrId) { //表示修改地址
				UpdateAddress(addrId, txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			} else { //表示添加地址
				AddAddress(txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			}
		}
	});
	$("#use__usablePrice").click(function() {
		if($(this).is(':checked')) {
			isWallet = 1;			
		} else {
			isWallet = 0;
		}
		allPrice();
	});
	//去支付
	$('#btnPay').click(function() {
		addressId = $('.HasAddress').attr('data-id');
		if(sc.auth.isLogin()) { //如果已经登录的
			if(addressId <= 0) {
				layer.open({
					content: '必须选择收货地址',
					skin: 'msg',
					time: 3 //1秒后自动关闭
				});
				return false;
			}
			if(checkClick()) {
			BuyOnePurchaseCreateOrder();
			}
		} else {
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
		}

	});
});

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
            getProductInfo();

		} else if(data.code == 1) {
		
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

//获取购买的产品信息
function getProductInfo() {
	var url = "api/Goods/BuyNowInfo";
	var calback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
			return false;
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			return false;
		}
		if(data.code === 0) {
			$('#detailsSureOrder .m-pro .buyNum').text(detailProNum); //传过来的购买量
			if(data.data.ProductImage) {
				$("#detailsSureOrder .m-pro img").attr("src", data.data.ProductImage);
			} else {
				$("#detailsSureOrder .m-pro img").attr("src", '/images/noPic.jpg');
			}
			$("#detailsSureOrder .m-pro .name").text(data.data.ProductName);
			$("#detailsSureOrder .m-pro .price .num").text(data.data.Price);
			$('#proNum').text(detailProNum);
			allPrice();
			getFreight();
//			allPrice();
//			$("#use__usablePrice").change(function() {
//				if($(this).is(':checked')) {
//					isWallet = 1;
//				} else {
//					isWallet = 0;
//				}
//				allPrice();
//			});
		}
	};
	sc.post(url, {
		"userId": userId,
		"token": token,
		"proId": proId,
		"proSpecText": skuTxt,
		"productType": 2 //0代表普通，1代表特卖,2代表一元购
	}, calback)
}
//获取邮费
function getFreight() {
	var url = "/api/Order/BuyNowToFreight";
	var calback = function(data) {
		console.log(data)
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 0) {
			freightmoney = data.data;
			var freightText = freightmoney > 0 ? parseFloat(freightmoney).toFixed(2) : "包邮";
			$("#detailsSureOrder .shop_freight").html('￥'+freightText);
			allPrice();
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProId": proId,
		"AddressId": addressId,
		"Number": detailProNum,
		"SpecText":skuTxt
	}, calback)
}

//计算总价格
function allPrice() {
	var allPrice = 0;
	$('#detailsSureOrder .cartItem').each(function() {
		var that = $(this);
		var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
		var num = parseInt(that.find('.buyNum').text()); //数量
	    allPrice = Number(allPrice) + Number(unitPrice) * num;
	});
	//allPrice = parseFloat(allPrice).toFixed(2);
	$('#detailsSureOrder .orderTotal').text(allPrice);
	if($('#use__usablePrice').is(":checked")) {
		console.log("已经打开了余额按钮");
		isWallet = 1;
		if(balance > (allPrice + freightmoney)) {
			$("#total4").text(0);
			//$('#total').text(0);
			//$(".payText").text("使用余额")
		} else {
			$("#total4").text(parseFloat(allPrice + freightmoney - balance).toFixed(2));
			$('#total').text(parseFloat(allPrice + freightmoney - balance).toFixed(2));
		}
	} else {
		$("#total4").text(parseFloat(allPrice + freightmoney).toFixed(2));
		$('#total').text(parseFloat(allPrice + freightmoney).toFixed(2));
		//$(".payText").text("付款")
	}

	return allPrice;
}

//立即购买接口
function BuyOnePurchaseCreateOrder() {
	remarkTxt = $("#beizhu").val();
	var url = "/api/order/BuyOnePurchaseCreateOrder";
	var callback = function(data) {
		console.log(data);

		if(data.code === 200) {
			layer.open({
				content: '下单支付成功!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				window.location.href = "member/order.html?orderType=0";				
			}, 1000);
		} else {
			if(data.code === 2) {
				layer.open({
					content: '登录超时，请重新登录!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				window.location.href = "/login.html";
			} else if(data.code === 1) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			} else if(data.code === 99) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			} else {
				var urldata = encodeURIComponent("orderno=" + data.data+'&gotoType=0');
				window.location.href = "pay.html?" + urldata;
			}
		}
	}
	sc.post(url, {
		'UserId': userId,
		'Token': token,
		'ProId': proId,
		'Number': detailProNum,
		'AddressId': addressId,
		'Remark': remarkTxt,
		"IsPayWallet": isWallet
	}, callback);
}